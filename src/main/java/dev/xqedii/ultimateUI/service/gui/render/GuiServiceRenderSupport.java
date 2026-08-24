package dev.xqedii.ultimateUI.service.gui.render;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.gui.model.HoverElement;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.gui.model.PreviewViewport;
import dev.xqedii.ultimateUI.service.gui.util.GuiServiceUtilitySupport;
import dev.xqedii.ultimateUI.service.hud.HudPositionCalculator;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.service.resourcepack.UiImageAtlasService;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.util.Vector;

public abstract class GuiServiceRenderSupport extends GuiServiceUtilitySupport {
   protected static final String HITBOX_DEBUG_HEX_COLOR = "ff0000";
   protected static final double IMAGE_GROUP_SIDEBAR_OFFSET_X_RENDER = 29.0;
   protected static final double IMAGE_GROUP_SIDEBAR_OFFSET_Y_RENDER = 248.0;
   protected static final double IMAGE_GLYPH_BASELINE_TILE_MULTIPLIER = 3.0;
   public static boolean IMAGE_SCALE_DEBUG_LOGGING = false;
   protected static final String IMAGE_TILE_HOVER_SOURCE_KEY = "hover_image_source";
   protected static final String IMAGE_TILE_HOVER_ROW_KEY = "hover_image_row";
   protected static final String IMAGE_TILE_HOVER_COL_KEY = "hover_image_col";
   protected static final int TEXT_WRAP_DEFAULT_LINE_WIDTH = 200;
   protected static final double ROUNDED_TALL_PART_SHIFT_Y = 1.0;
   protected static final double ROUNDED_RUNTIME_EDGE_SHIFT_HEIGHT_THRESHOLD = 195.0;
   protected static final double DEFAULT_PIVOT_RATIO_X = 0.5075;
   protected static final double DEFAULT_PIVOT_RATIO_Y = 0.469;
   protected static final double ROTATION_VISUAL_SHIFT_EPSILON = 1.0E-4;
   protected static final double ROTATION_VISUAL_X_BASE_FACTOR = 0.5;
   protected static final double ROTATION_VISUAL_X_EXTRA_FACTOR = 0.0617;
   protected static final double ROTATION_VISUAL_Y_DIRECTIONAL_FACTOR = 0.0158;
   protected static final double ITEM_DISPLAY_TOP_SHIFT_FACTOR = 0.56;
   protected static final double ITEM_DISPLAY_SCALE_FACTOR = 1.6666666666666667;
   protected static final double ITEM_DISPLAY_NON_BLOCK_ROTATION_90_X_FACTOR = 0.02208835341365462;
   protected static final double ITEM_DISPLAY_NON_BLOCK_ROTATION_90_Y_FACTOR = 0.040160642570281124;
   protected static final double ITEM_DISPLAY_NON_BLOCK_ROTATION_180_X_FACTOR = -0.012048192771084338;
   protected static final double ITEM_DISPLAY_NON_BLOCK_ROTATION_180_Y_FACTOR = 0.060240963855421686;
   protected static final double ITEM_DISPLAY_NON_BLOCK_ROTATION_270_X_FACTOR = -0.03413654618473896;
   protected static final double ITEM_DISPLAY_NON_BLOCK_ROTATION_270_Y_FACTOR = 0.02208835341365462;
   protected static final double ITEM_DISPLAY_BLOCK_OUTLINE_SIZE_FACTOR = 1.024;
   protected static final double ITEM_DISPLAY_BLOCK_OUTLINE_LEFT_EXPAND_FACTOR = 0.014;
   protected static final double ITEM_DISPLAY_BLOCK_OUTLINE_UP_SHIFT_FACTOR = 0.018;
   protected static final double ITEM_DISPLAY_BLOCK_OUTLINE_TOP_EXPAND_FACTOR = 0.014;
   protected static final String[] PIVOT_X_PATHS = new String[]{
      "pivot.x", "pivotX", "rotationPivot.x", "rotation_pivot.x", "params.pivot.x", "params.pivotX", "params.rotationPivot.x", "params.rotation_pivot.x"
   };
   protected static final String[] PIVOT_Y_PATHS = new String[]{
      "pivot.y", "pivotY", "rotationPivot.y", "rotation_pivot.y", "params.pivot.y", "params.pivotY", "params.rotationPivot.y", "params.rotation_pivot.y"
   };
   protected static final String[] PIVOT_NORMALIZED_PATHS = new String[]{
      "pivot.normalized",
      "pivotNormalized",
      "rotationPivot.normalized",
      "rotation_pivot.normalized",
      "params.pivot.normalized",
      "params.pivotNormalized",
      "params.rotationPivot.normalized",
      "params.rotation_pivot.normalized"
   };
   protected static final String[] PIVOT_MODE_PATHS = new String[]{
      "pivot.mode",
      "pivot.units",
      "pivot.unit",
      "rotationPivot.mode",
      "rotation_pivot.mode",
      "params.pivot.mode",
      "params.pivot.units",
      "params.pivot.unit",
      "params.rotationPivot.mode",
      "params.rotation_pivot.mode"
   };
   protected static final Pattern RUNTIME_NUMERIC_PLACEHOLDER_PATTERN = Pattern.compile("%([^%]+)%");
   protected static final Pattern RUNTIME_NUMERIC_PLACEHOLDER_OPTION_START_PATTERN = Pattern.compile("(?i)\\b(?:MIN|MAX|DEFAULT)\\s*=");
   protected static final Pattern RUNTIME_NUMERIC_PLACEHOLDER_OPTION_PATTERN = Pattern.compile("(?i)\\b(MIN|MAX|DEFAULT)\\s*=\\s*([-+]?\\d+(?:[\\.,]\\d+)?)");
   protected static final Pattern FONT_OPEN_TAG_PATTERN = Pattern.compile("(?i)<font:[^>]+>");
   protected static final Pattern VISIBILITY_OPERATOR_VALUE_PATTERN = Pattern.compile("^(<=|=<|=>|>=|=)([-+]?\\d+(?:[\\.,]\\d+)?)(.*)$", 32);
   private final Map<String, PlayerProfile> resolvedHeadProfileCache = new ConcurrentHashMap<>();
   private final Set<String> pendingHeadProfileLookups = ConcurrentHashMap.newKeySet();

   protected GuiServiceRenderSupport(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   protected boolean isRenderableBlockType(String var1) {
      return "block".equals(var1) || "item".equals(var1) || "text".equals(var1) || this.isRoundedType(var1) || "hitbox".equals(var1);
   }

   protected ItemStack resolveItemDisplayStack(Player var1, ConfigurationSection var2, String var3) {
      if (var2 != null && ("block".equals(var3) || "item".equals(var3))) {
         String var4 = this.firstNonBlank(
            new String[]{
               var2.getString("item"),
               var2.getString("item.material"),
               var2.getString("material"),
               var2.getString("params.item"),
               var2.getString("params.item.material"),
               var2.getString("params.material")
            }
         );
         if (var4.isBlank()) {
            return null;
         } else {
            String var5 = this.resolveItemDisplayToken(var1, var4);
            String var6 = this.resolveItemDisplayMaterialToken(var5);
            if (this.isCustomItemToken(var6)) {
               ItemStack var10 = this.resolveCustomItemStack(var6);
               if (var10 == null) {
                  return null;
               } else {
                  this.applyItemDisplayEnchantments(var10, this.readItemDisplayEnchantments(var2));
                  this.applyItemDisplayGlowingState(var10, this.readItemDisplayGlowing(var2));
                  return var10;
               }
            } else {
               Material var7 = this.resolveItemDisplayMaterial(var6);
               if (var7 != null && var7 != Material.AIR) {
                  ItemStack var8 = new ItemStack(var7);
                  if (var7 == Material.PLAYER_HEAD) {
                     this.applyItemDisplayHeadOwner(var8, this.resolvePlayerHeadOwnerToken(var5));
                  }

                  Integer var9 = this.resolveItemDisplayCustomModelData(var5);
                  if (var9 == null) {
                     var9 = this.readItemDisplayCustomModelData(var2);
                  }

                  this.applyItemDisplayCustomModelData(var8, var9);
                  this.applyItemDisplayEnchantments(var8, this.readItemDisplayEnchantments(var2));
                  this.applyItemDisplayGlowingState(var8, this.readItemDisplayGlowing(var2));
                  return var8;
               } else {
                  return null;
               }
            }
         }
      } else {
         return null;
      }
   }

   protected String resolveItemDisplayToken(Player var1, String var2) {
      String var3 = this.firstNonBlank(new String[]{var2}).trim();
      if (var3.isBlank()) {
         return "";
      } else {
         String var4 = this.resolveItemDisplayMaterialToken(var3);
         if (!this.isItemDisplayPlayerHeadToken(var4)) {
            return this.firstNonBlank(new String[]{this.applyPlaceholderApi(var1, var3), var3}).trim();
         } else {
            String var5 = var3;
            if (var1 != null && var3.contains("%player%")) {
               var5 = var3.replace("%player%", var1.getName());
            }

            String var6 = this.applyPlaceholderApiIgnoringEditorMode(var1, var5);
            return this.firstNonBlank(new String[]{var6, var5}).trim();
         }
      }
   }

   protected String applyPlaceholderApiIgnoringEditorMode(Player var1, String var2) {
      return this.resolvePlaceholders(var1, var2, false);
   }

   protected String resolveBlockPlaceholders(Player var1, EditorSession var2, ConfigurationSection var3, String var4) {
      return this.shouldResolvePopupPlaceholdersInEditor(var2, var3)
         ? this.applyPlaceholderApiIgnoringEditorMode(var1, var4)
         : this.applyPlaceholderApi(var1, var4);
   }

   protected boolean shouldResolvePopupPlaceholdersInEditor(EditorSession var1, ConfigurationSection var2) {
      if (var2 != null && var1 != null && var1.editMode) {
         String var3 = this.firstNonBlank(new String[]{var2.getString("__editor_component_name")});
         if (!var3.isBlank() && var3.toLowerCase(Locale.ROOT).contains("popup")) {
            return true;
         } else {
            String var4 = this.firstNonBlank(new String[]{var2.getString("id"), var2.getString("__editor_target_id")});
            return !var4.isBlank() && var4.toLowerCase(Locale.ROOT).contains("popup");
         }
      } else {
         return false;
      }
   }

   protected double readRuntimeNumericValue(Player var1, EditorSession var2, ConfigurationSection var3, String var4, String var5, double var6) {
      if (var3 == null) {
         return var6;
      } else if (var3.contains(var4)) {
         return this.parseRuntimeNumericValue(var1, var2, var3.get(var4), var6);
      } else {
         return var3.contains(var5) ? this.parseRuntimeNumericValue(var1, var2, var3.get(var5), var6) : var6;
      }
   }

   protected double parseRuntimeNumericValue(Player var1, EditorSession var2, Object var3, double var4) {
      if (var3 == null) {
         return var4;
      } else if (var3 instanceof Number var9) {
         return var9.doubleValue();
      } else {
         String var6 = var3.toString();
         String var7 = this.firstNonBlank(new String[]{var6});
         if (var7.isBlank()) {
            return var4;
         } else {
            var7 = this.applyConstrainedRuntimeNumericPlaceholders(var1, var2, var7);
            if (var7.contains("%") && var1 != null) {
               String var8 = this.isEditorContextSession(var2) ? this.applyPlaceholderApi(var1, var7) : this.applyPlaceholderApiIgnoringEditorMode(var1, var7);
               var7 = this.firstNonBlank(new String[]{var8, var7});
            }

            return this.parseDouble(var7, var4);
         }
      }
   }

   protected String applyConstrainedRuntimeNumericPlaceholders(Player var1, EditorSession var2, String var3) {
      if (var3 != null && !var3.isBlank() && var3.indexOf(37) >= 0) {
         Matcher var4 = RUNTIME_NUMERIC_PLACEHOLDER_PATTERN.matcher(var3);
         StringBuffer var5 = new StringBuffer(var3.length());
         boolean var6 = false;

         while (var4.find()) {
            String var7 = var4.group(0);
            GuiServiceRenderSupport.RuntimeNumericPlaceholderConstraints var8 = this.parseRuntimeNumericPlaceholderConstraints(var4.group(1));
            if (var8 != null) {
               double var9 = this.resolveRuntimeNumericPlaceholderWithConstraints(var1, var2, var8);
               if (Double.isFinite(var9)) {
                  var7 = this.toPlainNumericLiteral(var9);
                  var6 = true;
               }
            }

            var4.appendReplacement(var5, Matcher.quoteReplacement(var7));
         }

         if (!var6) {
            return var3;
         } else {
            var4.appendTail(var5);
            return var5.toString();
         }
      } else {
         return var3;
      }
   }

   protected GuiServiceRenderSupport.RuntimeNumericPlaceholderConstraints parseRuntimeNumericPlaceholderConstraints(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim();
      if (var2.isBlank()) {
         return null;
      } else {
         Matcher var3 = RUNTIME_NUMERIC_PLACEHOLDER_OPTION_START_PATTERN.matcher(var2);
         if (!var3.find()) {
            return null;
         } else {
            int var4 = var3.start();
            String var5 = this.normalizeRuntimeNumericPlaceholderId(var2.substring(0, var4));
            if (var5.isBlank()) {
               return null;
            } else {
               Matcher var6 = RUNTIME_NUMERIC_PLACEHOLDER_OPTION_PATTERN.matcher(var2.substring(var4));
               boolean var7 = false;
               double var8 = Double.NaN;
               double var10 = Double.NaN;
               double var12 = Double.NaN;

               while (var6.find()) {
                  var7 = true;
                  String var14 = this.firstNonBlank(new String[]{var6.group(1)}).toUpperCase(Locale.ROOT);
                  double var15 = this.parseDouble(var6.group(2), Double.NaN);
                  if (Double.isFinite(var15)) {
                     switch (var14) {
                        case "MIN":
                           var8 = var15;
                           break;
                        case "MAX":
                           var10 = var15;
                           break;
                        case "DEFAULT":
                           var12 = var15;
                     }
                  }
               }

               if (!var7) {
                  return null;
               } else {
                  if (Double.isFinite(var8) && Double.isFinite(var10) && var8 > var10) {
                     double var19 = var8;
                     var8 = var10;
                     var10 = var19;
                  }

                  return new GuiServiceRenderSupport.RuntimeNumericPlaceholderConstraints(var5, var8, var10, var12);
               }
            }
         }
      }
   }

   protected String normalizeRuntimeNumericPlaceholderId(String var1) {
      String var2;
      for (var2 = this.firstNonBlank(new String[]{var1}).trim(); !var2.isEmpty(); var2 = var2.substring(0, var2.length() - 1).trim()) {
         char var3 = var2.charAt(var2.length() - 1);
         if (var3 != ',' && var3 != ';') {
            break;
         }
      }

      return var2;
   }

   protected double resolveRuntimeNumericPlaceholderWithConstraints(
      Player var1, EditorSession var2, GuiServiceRenderSupport.RuntimeNumericPlaceholderConstraints var3
   ) {
      if (var3 != null && var3.placeholderId() != null && !var3.placeholderId().isBlank()) {
         String var4 = "%" + var3.placeholderId() + "%";
         String var5 = var4;
         if (var1 != null) {
            var5 = this.isEditorContextSession(var2) ? this.applyPlaceholderApi(var1, var4) : this.applyPlaceholderApiIgnoringEditorMode(var1, var4);
         }

         double var6 = this.parseDouble(var5, Double.NaN);
         if (!Double.isFinite(var6)) {
            var6 = var3.defaultValue();
         }

         if (!Double.isFinite(var6)) {
            return Double.NaN;
         } else {
            if (Double.isFinite(var3.minValue())) {
               var6 = Math.max(var6, var3.minValue());
            }

            if (Double.isFinite(var3.maxValue())) {
               var6 = Math.min(var6, var3.maxValue());
            }

            return var6;
         }
      } else {
         return Double.NaN;
      }
   }

   protected String toPlainNumericLiteral(double var1) {
      if (!Double.isFinite(var1)) {
         return "0";
      } else {
         BigDecimal var3 = BigDecimal.valueOf(var1).stripTrailingZeros();
         String var4 = var3.toPlainString();
         return "-0".equals(var4) ? "0" : var4;
      }
   }

   protected int findItemDisplayCustomModelDataSeparator(String var1) {
      if (var1 == null) {
         return -1;
      } else {
         String var2 = var1.trim();
         if (var2.isBlank()) {
            return -1;
         } else {
            int var3 = var2.lastIndexOf(35);
            return var3 > 0 && var3 < var2.length() - 1 ? var3 : -1;
         }
      }
   }

   protected String resolveItemDisplayMaterialToken(String var1) {
      if (var1 == null) {
         return "";
      } else {
         String var2 = var1.trim();
         if (var2.isBlank()) {
            return "";
         } else {
            int var3 = this.findItemDisplayCustomModelDataSeparator(var2);
            return var3 < 0 ? var2 : var2.substring(0, var3).trim();
         }
      }
   }

   protected Integer parseItemDisplayCustomModelDataValue(Object var1) {
      if (var1 == null) {
         return null;
      } else {
         double var2 = this.parseDouble(var1, Double.NaN);
         if (!Double.isFinite(var2)) {
            return null;
         } else {
            long var4 = Math.round(var2);
            if (var4 >= 0L && var4 <= 2147483647L) {
               return Math.abs(var2 - (double)var4) > 1.0E-4 ? null : (int)var4;
            } else {
               return null;
            }
         }
      }
   }

   protected Integer resolveItemDisplayCustomModelData(String var1) {
      if (var1 == null) {
         return null;
      } else {
         int var2 = this.findItemDisplayCustomModelDataSeparator(var1);
         if (var2 < 0) {
            return null;
         } else {
            String var3 = var1.trim();
            String var4 = var3.substring(var2 + 1).trim();
            return this.parseItemDisplayCustomModelDataValue(var4);
         }
      }
   }

   protected Integer readItemDisplayCustomModelData(ConfigurationSection var1) {
      if (var1 == null) {
         return null;
      } else {
         String[] var2 = new String[]{
            "custom_model_data",
            "customModelData",
            "item.custom_model_data",
            "item.customModelData",
            "params.custom_model_data",
            "params.customModelData",
            "params.item.custom_model_data",
            "params.item.customModelData"
         };

         for (Object var6_raw : var2) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            if (var1.contains(var6)) {
               Integer var7 = this.parseItemDisplayCustomModelDataValue(var1.get(var6));
               if (var7 != null) {
                  return var7;
               }
            }
         }

         return null;
      }
   }

   protected boolean isItemDisplayPlayerHeadToken(String var1) {
      if (var1 == null) {
         return false;
      } else {
         String var2 = var1.trim();
         if (var2.isBlank()) {
            return false;
         } else {
            return var2.equalsIgnoreCase("PLAYER_HEAD")
               ? true
               : var2.regionMatches(true, 0, "PLAYER_HEAD:", 0, "PLAYER_HEAD:".length())
                  || var2.regionMatches(true, 0, "PLAYER_HEAD=", 0, "PLAYER_HEAD=".length());
         }
      }
   }

   protected String resolvePlayerHeadOwnerToken(String var1) {
      String var2 = this.resolveItemDisplayMaterialToken(var1);
      if (!this.isItemDisplayPlayerHeadToken(var2)) {
         return "";
      } else if (var2.regionMatches(true, 0, "PLAYER_HEAD:", 0, "PLAYER_HEAD:".length())) {
         return var2.substring("PLAYER_HEAD:".length()).trim();
      } else {
         return var2.regionMatches(true, 0, "PLAYER_HEAD=", 0, "PLAYER_HEAD=".length()) ? var2.substring("PLAYER_HEAD=".length()).trim() : "";
      }
   }

   protected boolean isCustomItemToken(String var1) {
      if (var1 != null && !var1.isBlank()) {
         String var2 = var1.trim().toLowerCase(Locale.ROOT);
         return var2.startsWith("itemsadder:")
            || var2.startsWith("ia:")
            || var2.startsWith("nexo:")
            || var2.startsWith("oraxen:")
            || var2.startsWith("craftengine:")
            || var2.startsWith("ce:");
      } else {
         return false;
      }
   }

   protected ItemStack resolveCustomItemStack(String var1) {
      if (var1 != null && !var1.isBlank()) {
         String var2 = var1.trim();
         String var3 = var2.toLowerCase(Locale.ROOT);
         if (var3.startsWith("itemsadder:")) {
            return this.resolveItemsAdderItemStack(var2.substring("itemsadder:".length()));
         } else if (var3.startsWith("ia:")) {
            return this.resolveItemsAdderItemStack(var2.substring("ia:".length()));
         } else if (var3.startsWith("nexo:")) {
            return this.resolveNexoItemStack(var2.substring("nexo:".length()));
         } else if (var3.startsWith("oraxen:")) {
            return this.resolveOraxenItemStack(var2.substring("oraxen:".length()));
         } else if (var3.startsWith("craftengine:")) {
            return this.resolveCraftEngineItemStack(var2.substring("craftengine:".length()));
         } else {
            return var3.startsWith("ce:") ? this.resolveCraftEngineItemStack(var2.substring("ce:".length())) : null;
         }
      } else {
         return null;
      }
   }

   protected ItemStack resolveItemsAdderItemStack(String var1) {
      if (var1 != null && !var1.isBlank()) {
         Plugin var2 = Bukkit.getPluginManager().getPlugin("ItemsAdder");
         if (var2 != null && var2.isEnabled()) {
            try {
               Class var3 = Class.forName("dev.lone.itemsadder.api.CustomStack");
               Method var4 = var3.getMethod("getInstance", String.class);
               Object var5 = var4.invoke(null, var1);
               if (var5 == null) {
                  return null;
               } else {
                  Method var6 = var3.getMethod("getItemStack");
                  return var6.invoke(var5) instanceof ItemStack var8 ? var8.clone() : null;
               }
            } catch (Throwable var9) {
               return null;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   protected ItemStack resolveNexoItemStack(String var1) {
      if (var1 != null && !var1.isBlank()) {
         Plugin var2 = Bukkit.getPluginManager().getPlugin("Nexo");
         if (var2 != null && var2.isEnabled()) {
            try {
               Class var3 = Class.forName("com.nexomc.nexo.api.NexoItems");
               Method var4 = var3.getMethod("itemFromId", String.class);
               Object var5 = var4.invoke(null, var1);
               if (var5 == null) {
                  return null;
               } else {
                  Method var6 = var5.getClass().getMethod("build");
                  return var6.invoke(var5) instanceof ItemStack var8 ? var8.clone() : null;
               }
            } catch (Throwable var9) {
               return null;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   protected ItemStack resolveOraxenItemStack(String var1) {
      if (var1 != null && !var1.isBlank()) {
         Plugin var2 = Bukkit.getPluginManager().getPlugin("Oraxen");
         if (var2 != null && var2.isEnabled()) {
            try {
               Class var3 = Class.forName("io.th0rgal.oraxen.api.OraxenItems");
               Method var4 = var3.getMethod("getItemById", String.class);
               Object var5 = var4.invoke(null, var1);
               if (var5 == null) {
                  return null;
               } else {
                  Method var6 = var5.getClass().getMethod("build");
                  return var6.invoke(var5) instanceof ItemStack var8 ? var8.clone() : null;
               }
            } catch (Throwable var9) {
               return null;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   protected ItemStack resolveCraftEngineItemStack(String var1) {
      if (var1 != null && !var1.isBlank()) {
         Plugin var2 = Bukkit.getPluginManager().getPlugin("CraftEngine");
         if (var2 != null && var2.isEnabled()) {
            try {
               Class var3 = Class.forName("net.momirealms.craftengine.bukkit.api.CraftEngineItems");
               Method var4 = var3.getMethod("byId", String.class);
               Object var5 = var4.invoke(null, var1.trim());
               if (var5 == null) {
                  return null;
               } else {
                  Method var6 = var5.getClass().getMethod("buildBukkitItem");
                  var6.setAccessible(true);
                  return var6.invoke(var5) instanceof ItemStack var8 ? var8.clone() : null;
               }
            } catch (Throwable var9) {
               return null;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   protected Material resolveItemDisplayMaterial(String var1) {
      if (var1 != null && !var1.isBlank()) {
         String var2 = var1.trim();
         if (this.isItemDisplayPlayerHeadToken(var2)) {
            return Material.PLAYER_HEAD;
         } else {
            Material var3 = Material.matchMaterial(var2);
            if (var3 == null && var2.contains(":")) {
               var3 = Material.matchMaterial(var2.substring(var2.indexOf(58) + 1));
            }

            if (var3 == null) {
               var3 = Material.matchMaterial(var2.toUpperCase(Locale.ROOT));
            }

            return var3;
         }
      } else {
         return null;
      }
   }

   protected void applyItemDisplayHeadOwner(ItemStack var1, String var2) {
      if (var1 != null && var1.getType() == Material.PLAYER_HEAD) {
         String var3 = this.firstNonBlank(new String[]{var2});
         if (!var3.isBlank() && !var3.contains("%")) {
            if (var1.getItemMeta() instanceof SkullMeta var5) {
               Player var6 = Bukkit.getPlayerExact(var3);
               if (var6 != null) {
                  var5.setOwnerProfile(var6.getPlayerProfile());
                  var1.setItemMeta(var5);
               } else {
                  PlayerProfile var7 = this.resolvedHeadProfileCache.get(var3.toLowerCase(Locale.ROOT));
                  if (var7 != null) {
                     var5.setOwnerProfile(var7);
                     var1.setItemMeta(var5);
                  } else {
                     var5.setOwningPlayer(Bukkit.getOfflinePlayer(var3));
                     var1.setItemMeta(var5);
                     this.resolveHeadProfileAsync(var3);
                  }
               }
            }
         }
      }
   }

   protected void resolveHeadProfileAsync(String var1) {
      if (this.plugin != null) {
         String var2 = var1.toLowerCase(Locale.ROOT);
         if (this.pendingHeadProfileLookups.add(var2)) {
            Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
               try {
                  PlayerProfile var3 = Bukkit.createPlayerProfile(var1);
                  PlayerProfile var4 = (PlayerProfile)var3.update().join();
                  if (var4 != null && var4.getTextures() != null && var4.getTextures().getSkin() != null) {
                     this.resolvedHeadProfileCache.put(var2, var4);
                  }
               } catch (Throwable var8) {
               } finally {
                  this.pendingHeadProfileLookups.remove(var2);
               }
            });
         }
      }
   }

   protected void applyItemDisplayCustomModelData(ItemStack var1, Integer var2) {
      if (var1 != null && var2 != null && var2 >= 0) {
         ItemMeta var3 = var1.getItemMeta();
         if (var3 != null) {
            var3.setCustomModelData(var2);
            var1.setItemMeta(var3);
         }
      }
   }

   protected Integer parseItemDisplayEnchantmentLevel(Object var1) {
      double var2 = this.parseDouble(var1, Double.NaN);
      if (!Double.isFinite(var2)) {
         return null;
      } else {
         int var4 = (int)Math.round(var2);
         return var4 <= 0 ? null : var4;
      }
   }

   protected Enchantment resolveItemDisplayEnchantment(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim();
      if (var2.isBlank()) {
         return null;
      } else {
         NamespacedKey var3 = NamespacedKey.fromString(var2.toLowerCase(Locale.ROOT));
         if (var3 == null && !var2.contains(":")) {
            var3 = NamespacedKey.minecraft(var2.toLowerCase(Locale.ROOT));
         }

         Enchantment var4 = var3 == null ? null : Enchantment.getByKey(var3);
         if (var4 == null) {
            var4 = Enchantment.getByName(var2.toUpperCase(Locale.ROOT));
         }

         if (var4 == null && var2.contains(":")) {
            String var5 = var2.substring(var2.indexOf(58) + 1).toUpperCase(Locale.ROOT);
            var4 = Enchantment.getByName(var5);
         }

         return var4;
      }
   }

   protected Map<Enchantment, Integer> parseItemDisplayEnchantments(Object var1) {
      if (var1 instanceof Map var2) {
         LinkedHashMap var3 = new LinkedHashMap();

         for (Map.Entry var5 : (Set<Map.Entry>)(Set)((Map)var2).entrySet()) {
            String var6 = var5.getKey() == null ? "" : var5.getKey().toString();
            Enchantment var7 = this.resolveItemDisplayEnchantment(var6);
            if (var7 != null) {
               Integer var8 = this.parseItemDisplayEnchantmentLevel(var5.getValue());
               if (var8 != null) {
                  var3.put(var7, var8);
               }
            }
         }

         return var3;
      } else {
         return null;
      }
   }

   protected Map<Enchantment, Integer> readItemDisplayEnchantments(ConfigurationSection var1) {
      if (var1 == null) {
         return Collections.emptyMap();
      } else {
         String[] var2 = new String[]{
            "item.enchants",
            "enchants",
            "params.item.enchants",
            "params.enchants",
            "item.enchantments",
            "enchantments",
            "params.item.enchantments",
            "params.enchantments"
         };

         for (Object var6_raw : var2) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            if (var1.contains(var6)) {
               Map var7 = this.parseItemDisplayEnchantments(var1.get(var6));
               if (var7 != null) {
                  return var7;
               }
            }
         }

         return Collections.emptyMap();
      }
   }

   protected void applyItemDisplayEnchantments(ItemStack var1, Map<Enchantment, Integer> var2) {
      if (var1 != null && var2 != null && !var2.isEmpty()) {
         ItemMeta var3 = var1.getItemMeta();
         if (var3 != null) {
            boolean var4 = false;

            for (Entry var6 : var2.entrySet()) {
               Enchantment var7 = (Enchantment)var6.getKey();
               Integer var8 = (Integer)var6.getValue();
               if (var7 != null && var8 != null && var8 > 0) {
                  var3.addEnchant(var7, var8, true);
                  var4 = true;
               }
            }

            if (var4) {
               var1.setItemMeta(var3);
            }
         }
      }
   }

   protected boolean readItemDisplayGlowing(ConfigurationSection var1) {
      if (var1 == null) {
         return false;
      } else {
         String[] var2 = new String[]{"glowing", "item.glowing", "item.glint", "params.glowing", "params.item.glowing", "params.item.glint"};

         for (Object var6_raw : var2) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            if (var1.contains(var6)) {
               return this.parseBooleanFlag(var1.get(var6), false);
            }
         }

         return false;
      }
   }

   protected void applyItemDisplayGlowingState(ItemStack var1, boolean var2) {
      if (var1 != null && var1.getType() != Material.AIR) {
         ItemMeta var3 = var1.getItemMeta();
         if (var3 != null) {
            var3.setEnchantmentGlintOverride(var2 ? Boolean.TRUE : null);
            var1.setItemMeta(var3);
         }
      }
   }

   protected boolean shouldSpawnOpacityVisual(String var1, int var2) {
      return var2 > 0;
   }

   protected int applyVisibilityConditionOpacity(Player var1, EditorSession var2, ConfigurationSection var3, int var4) {
      if (var3 != null && var1 != null) {
         ConfigurationSection var5 = var3.getConfigurationSection("visibility");
         if (var5 == null) {
            return var4;
         } else {
            String var6 = this.firstNonBlank(new String[]{var5.getString("placeholder")});
            if (var6.isEmpty()) {
               return var4;
            } else {
               boolean var7 = var5.getBoolean("visible", true);
               int var8 = var6.lastIndexOf(37);
               if (var8 >= 0 && var8 != var6.length() - 1) {
                  String var9 = var6.substring(0, var8 + 1);
                  String var10 = var6.substring(var8 + 1).trim();
                  Matcher var11 = VISIBILITY_OPERATOR_VALUE_PATTERN.matcher(var10);
                  if (!var11.matches()) {
                     return var4;
                  } else {
                     String var12 = var11.group(1);
                     double var13 = this.parseDouble(var11.group(2), Double.NaN);
                     if (!Double.isFinite(var13)) {
                        return var4;
                     } else {
                        String var15 = this.firstNonBlank(new String[]{var11.group(3)});
                        double var16 = Double.NaN;
                        double var18 = Double.NaN;
                        double var20 = Double.NaN;
                        if (!var15.isEmpty()) {
                           Matcher var22 = RUNTIME_NUMERIC_PLACEHOLDER_OPTION_PATTERN.matcher(var15);

                           while (var22.find()) {
                              String var23 = var22.group(1).toUpperCase(Locale.ROOT);
                              double var24 = this.parseDouble(var22.group(2), Double.NaN);
                              if (Double.isFinite(var24)) {
                                 switch (var23) {
                                    case "MIN":
                                       var16 = var24;
                                       break;
                                    case "MAX":
                                       var18 = var24;
                                       break;
                                    case "DEFAULT":
                                       var20 = var24;
                                 }
                              }
                           }
                        }

                        String var28 = this.applyConstrainedRuntimeNumericPlaceholders(var1, var2, var9);
                        if (var28.contains("%")) {
                           String var29 = this.isEditorContextSession(var2)
                              ? this.applyPlaceholderApi(var1, var28)
                              : this.applyPlaceholderApiIgnoringEditorMode(var1, var28);
                           var28 = this.firstNonBlank(new String[]{var29, var28});
                        }

                        double var30 = this.parseDouble(var28, Double.isFinite(var20) ? var20 : Double.NaN);
                        if (!Double.isFinite(var30)) {
                           return var4;
                        } else {
                           if (Double.isFinite(var16) && var30 < var16) {
                              var30 = var16;
                           }

                           if (Double.isFinite(var18) && var30 > var18) {
                              var30 = var18;
                           }
                           boolean var26 = var7 == switch (var12) {
                              case ">=", "=>" -> var30 >= var13 - 1.0E-4;
                              case "<=", "=<" -> var30 <= var13 + 1.0E-4;
                              default -> Math.abs(var30 - var13) < 1.0E-4;
                           };
                           return var26 ? var4 : 0;
                        }
                     }
                  }
               } else {
                  return var4;
               }
            }
         }
      } else {
         return var4;
      }
   }

   protected boolean isEditorToolShellBlockId(String var1) {
      return false;
   }

   protected int resolveShellBlockInitialOpacity(EditorSession var1, String var2, ConfigurationSection var3, int var4) {
      return var4;
   }

   protected boolean shouldSkipEditorShellBlockForSpawnRetoggle(EditorSession var1, String var2) {
      return false;
   }

   protected boolean shouldRenderEditorShellBlock(EditorSession var1, String var2, ConfigurationSection var3) {
      return true;
   }

   protected boolean shouldKeepHiddenShellBlockSpawned(EditorSession var1, String var2, ConfigurationSection var3, String var4) {
      return false;
   }

   protected boolean readHitboxShow(ConfigurationSection var1) {
      return var1 != null && var1.contains("show") ? this.parseBooleanFlag(var1.get("show"), false) : false;
   }

   protected boolean parseBooleanFlag(Object var1, boolean var2) {
      if (var1 == null) {
         return var2;
      } else if (var1 instanceof Boolean var9) {
         return var9;
      } else if (var1 instanceof Number var8) {
         return var8.doubleValue() != 0.0;
      } else {
         String var3 = var1.toString().trim();
         if (var3.isEmpty()) {
            return var2;
         } else {
            String var4 = var3.toLowerCase(Locale.ROOT);

            return switch (var4) {
               case "true", "yes", "on", "enabled" -> true;
               case "false", "no", "off", "disabled", "none", "null" -> false;
               default -> {
                  double var6 = this.parseDouble(var3, Double.NaN);
                  yield Double.isFinite(var6) ? var6 != 0.0 : var2;
               }
            };
         }
      }
   }

   protected double readPivotOffsetX(ConfigurationSection var1, double var2) {
      return this.readPivotOffset(var1, var2, true);
   }

   protected double readPivotOffsetY(ConfigurationSection var1, double var2) {
      return this.readPivotOffset(var1, var2, false);
   }

   protected double readPivotOffset(ConfigurationSection var1, double var2, boolean var4) {
      double var5 = Math.max(1.0, var2);
      double var7 = var5 * (var4 ? 0.5075 : 0.469);
      if (var1 == null) {
         return var7;
      } else {
         Object var9 = this.readPivotRawValue(var1, var4);
         double var10 = this.parseDouble(var9, var7);
         if (!Double.isFinite(var10)) {
            var10 = var7;
         }

         boolean var12 = this.hasPivotRawValue(var1, var4);
         Boolean var13 = this.readPivotNormalizedSetting(var1);
         boolean var14 = this.isPivotAxisNormalized(var10, var12, var13);
         return var14 ? var10 * var5 : var10;
      }
   }

   protected Object readPivotRawValue(ConfigurationSection var1, boolean var2) {
      if (var1 == null) {
         return null;
      } else {
         String[] var3 = var2 ? PIVOT_X_PATHS : PIVOT_Y_PATHS;

         for (Object var7_raw : var3) {
            String var7 = var7_raw != null ? var7_raw.toString() : null;
            if (var1.contains(var7)) {
               return var1.get(var7);
            }
         }

         return null;
      }
   }

   protected boolean hasPivotRawValue(ConfigurationSection var1, boolean var2) {
      if (var1 == null) {
         return false;
      } else {
         String[] var3 = var2 ? PIVOT_X_PATHS : PIVOT_Y_PATHS;

         for (Object var7_raw : var3) {
            String var7 = var7_raw != null ? var7_raw.toString() : null;
            if (var1.contains(var7)) {
               return true;
            }
         }

         return false;
      }
   }

   protected Boolean readPivotNormalizedSetting(ConfigurationSection var1) {
      if (var1 == null) {
         return null;
      } else {
         for (Object var5_raw : PIVOT_NORMALIZED_PATHS) {
            String var5 = var5_raw != null ? var5_raw.toString() : null;
            if (var1.contains(var5)) {
               return this.parseBooleanFlag(var1.get(var5), false);
            }
         }

         for (Object var10_raw : PIVOT_MODE_PATHS) {
            String var10 = var10_raw != null ? var10_raw.toString() : null;
            if (var1.contains(var10)) {
               String var6 = this.firstNonBlank(new String[]{var1.getString(var10)}).trim().toLowerCase(Locale.ROOT);
               if (!var6.isBlank()) {
                  if (var6.contains("norm") || var6.contains("ratio") || var6.contains("percent")) {
                     return true;
                  }

                  if (var6.contains("abs") || var6.contains("px") || var6.contains("pixel") || var6.contains("offset")) {
                     return false;
                  }
               }
            }
         }

         return null;
      }
   }

   protected boolean isPivotAxisNormalized(double var1, boolean var3, Boolean var4) {
      if (var4 != null) {
         return var4;
      } else {
         return var3 && Double.isFinite(var1) ? var1 >= 0.0 && var1 <= 1.0 : false;
      }
   }

   protected double[] resolvePivotAwareTopLeft(double var1, double var3, double var5, double var7, double var9, double var11, double var13) {
      double var15 = Double.isFinite(var9) ? var9 : var1 + var5 / 2.0;
      double var17 = Double.isFinite(var11) ? var11 : var3 + var7 / 2.0;
      return this.rotateTopLeftAroundPivot(var1, var3, var5, var7, var15, var17, this.normalizeRenderRotation(var13));
   }

   protected HoverElement renderResolvedElement(Player var1, EditorSession var2, ConfigurationSection var3, String var4, String var5) {
      boolean var6 = "text".equals(var5);
      boolean var7 = this.isDirectionalRoundedType(var5);
      boolean var8 = this.isRoundedType(var5);
      boolean var9 = var8 && var2 != null && var2.editMode && var2.optimizedEditor;
      if (var9) {
         var8 = false;
         var7 = false;
      }

      boolean var10 = "hitbox".equals(var5);
      ItemStack var11 = this.resolveItemDisplayStack(var1, var3, var5);
      boolean var12 = var11 != null && !var10 && !var6 && !var8;
      double var13 = this.readRuntimeNumericValue(var1, var2, var3, "position.x", "x", 0.0);
      double var15 = this.readRuntimeNumericValue(var1, var2, var3, "position.y", "y", 0.0);
      double var17 = this.readRuntimeNumericValue(
         var1, var2, var3, "layer", "layer", this.readRuntimeNumericValue(var1, var2, var3, "size.depth", "depth", 0.0)
      );
      double var19 = this.readRuntimeNumericValue(
         var1, var2, var3, "size.width", "width", this.readRuntimeNumericValue(var1, var2, var3, "scale.width", "width", 20.0)
      );
      double var21 = this.readRuntimeNumericValue(
         var1, var2, var3, "size.height", "height", this.readRuntimeNumericValue(var1, var2, var3, "scale.height", "height", 20.0)
      );
      boolean var23 = var19 < 0.0;
      boolean var24 = var21 < 0.0;
      double var25 = Math.max(1.0, Math.abs(var19));
      double var27 = Math.max(1.0, Math.abs(var21));
      double var29 = this.readRuntimeNumericValue(var1, var2, var3, "rotation", "rotate", 0.0);
      int var31 = this.readOpacity(var3, 255);
      var31 = this.applyVisibilityConditionOpacity(var1, var2, var3, var31);
      boolean var32 = this.shouldSpawnOpacityVisual(var5, var31);
      String var33 = this.firstNonBlank(new String[]{var3.getString("__editor_target_path")});
      TextAlignment var34 = this.readHudAlignment(var3);
      TextAlignment var35 = this.resolveEffectiveHudAlignment(var2, var34, var33);
      TextAlignment var36 = var6 ? this.readTextAlignment(var3) : TextAlignment.CENTER;
      int var37 = var6 ? this.readTextWrapLineWidth(var3) : 200;
      double var38 = var6 ? this.toInternalTextTopY(var15, var27) : var15;
      double var40 = this.resolveHudAlignmentOffsetX(var25, var35);
      double var42 = var2 != null && !var2.editMode ? var2.pageOpenSpawnOffsetX : 0.0;
      double var44 = var2 != null && !var2.editMode ? var2.pageOpenSpawnOffsetY : 0.0;
      double var46 = this.toRenderX(var2, var13 + var42);
      double var48 = this.toRenderY(var2, var38 + var44);
      double var50 = this.toRuntimeLayer(var2, var17);
      double var52 = this.toRenderSize(var2, var25);
      double var54 = this.toRenderSize(var2, var27);
      double var56 = var6 ? this.applyTextAlignmentOffset(var46, var52, var36) : var46;
      double var58 = this.resolveHudAlignmentOffsetX(var52, var35);
      double var60 = var56 + var58;
      double var62 = this.readPivotOffsetX(var3, var25);
      double var64 = this.readPivotOffsetY(var3, var27);
      double var66 = var56 + this.toRenderSize(var2, var62);
      double var68 = var60 + this.toRenderSize(var2, var62);
      double var70 = var48 + this.toRenderSize(var2, var64);
      String var72;
      if ("text".equals(var5)) {
         var72 = this.firstNonBlank(new String[]{var3.getString("text"), ""});
      } else {
         var72 = this.firstNonBlank(new String[]{var3.getString("unicode"), var3.getString("text"), "█"});
      }

      String var73 = this.firstNonBlank(new String[]{var3.getString("color"), var3.getString("style.color")});
      var72 = this.withHexPrefix(var72, var73);
      String var74 = this.resolveBlockPlaceholders(var1, var2, var3, var72);
      var74 = this.applyPreferredFont(var74, var3, !var6, !var6 || this.isEditorContextSession(var2));
      if (var10) {
         var74 = this.applyOutlineColor(var74, "ff0000");
      }

      if (IMAGE_SCALE_DEBUG_LOGGING && this.plugin != null) {
         String var75 = this.firstNonBlank(new String[]{var3.getString("id")});
         boolean var76 = var74 != null && var74.contains("uiimages");
         boolean var77 = var10 && var75 != null && var75.toLowerCase(Locale.ROOT).contains("img");
         if (var76) {
            this.plugin
               .getLogger()
               .info(
                  String.format(
                     Locale.ROOT,
                     "[ImgScaleDebug][TILE] id=%s logX=%.2f w=%.2f renderX=%.2f alignedX=%.2f | logY=%.2f h=%.2f renderY=%.2f",
                     var75,
                     var13,
                     var25,
                     var46,
                     var56,
                     var15,
                     var27,
                     var48
                  )
               );
         } else if (var77) {
            this.plugin
               .getLogger()
               .info(
                  String.format(
                     Locale.ROOT,
                     "[ImgScaleDebug][BOX]  id=%s logX=%.2f w=%.2f boxLeft=%.2f boxRight=%.2f renderX=%.2f | logY=%.2f h=%.2f",
                     var75,
                     var13,
                     var25,
                     var13,
                     var13 + var25,
                     var46,
                     var15,
                     var27
                  )
               );
         }
      }

      double var108 = !var6 && !var10 ? this.readOutlineSize(var3) : 0.0;
      double var109 = this.toRenderSize(var2, var108);
      String var79 = !var6 && !var10 ? this.readOutlineColor(var3) : "";
      String var80 = !var6 && !var10 && var109 > 1.0E-4 ? this.applyOutlineColor(var74, var79) : null;
      boolean var81 = !var10 && var32;
      if (var8 && var81) {
         double var110 = this.readRoundedRadius(var3, var52, var54);
         String var84 = this.readRoundedCornerUnicode(var3);
         String var115 = var7 ? this.readRoundedCornerUnicodeTopLeft(var3) : "";
         String var86 = var7 ? this.readRoundedCornerUnicodeTopRight(var3) : "";
         String var119 = var7 ? this.readRoundedCornerUnicodeBottomRight(var3) : "";
         String var88 = var7 ? this.readRoundedCornerUnicodeBottomLeft(var3) : "";
         double var124 = this.readRoundedTopLeftOffsetX(var3);
         double var126 = this.readRoundedTopLeftOffsetY(var3);
         double var93 = this.readRoundedTopRightOffsetX(var3);
         double var95 = this.readRoundedTopRightOffsetY(var3);
         double var97 = this.readRoundedBottomRightOffsetX(var3);
         double var99 = this.readRoundedBottomRightOffsetY(var3);
         double var101 = this.readRoundedBottomLeftOffsetX(var3);
         double var103 = this.readRoundedBottomLeftOffsetY(var3);
         this.renderRoundedOutline(
            var1,
            var4,
            var60,
            var48,
            var50,
            var52,
            var54,
            var80,
            var74,
            var31,
            var110,
            var84,
            var115,
            var86,
            var119,
            var88,
            var124,
            var126,
            var93,
            var95,
            var97,
            var99,
            var101,
            var103,
            var29,
            var23,
            var24,
            var109,
            var68,
            var70,
            var35
         );
         this.renderRoundedBlock(
            var1,
            var4,
            var60,
            var48,
            var50,
            var52,
            var54,
            var74,
            var31,
            var110,
            var84,
            var115,
            var86,
            var119,
            var88,
            var124,
            var126,
            var93,
            var95,
            var97,
            var99,
            var101,
            var103,
            var29,
            var23,
            var24,
            var68,
            var70,
            var35
         );
      } else if (var81) {
         this.removeRoundedParts(var1, var4);
         this.removeRoundedParts(var1, var4 + "_outline");
         double[] var82 = this.resolvePivotAwareTopLeft(var56, var48, var52, var54, var66, var70, var29);
         double var83 = var6 ? 0.0 : this.resolveRotationVisualXOffset(var29, var52);
         double var85 = var6 ? 0.0 : this.resolveRotationVisualYOffset(var29, var54);
         double var87 = var82[0] + var83;
         double var89 = var82[1] + var85;
         if (var12) {
            var89 -= var54 * 0.56;
            double[] var91 = this.resolveItemDisplayRotationOffset(var29, var52, var54);
            var87 += var91[0];
            var89 += var91[1];
            double[] var92 = this.resolveItemDisplayNonBlockRotationCompensation(var29, var52, var54);
            var87 += var92[0];
            var89 += var92[1];
         }

         HudPositionCalculator.Placement var125 = this.positionCalculator.calculateBoxPlacement(var87, var89, var50, var52, var54);
         if (var12) {
            this.upsertItemHud(var1, var4, var125.location(), var125.scale(), var11, var31, var35);
         } else {
            this.upsertHud(var1, var4, var125.location(), var125.scale(), var74, var31, var35, var36, var37);
         }

         this.applyElementTransformById(var1, var4, var29, var23, var24);
         if (var6) {
            this.clearOutlineHud(var1, var4);
         } else {
            this.renderSimpleOutline(var1, var4, var56, var48, var50, var52, var54, var80, var31, var35, var36, var29, var23, var24, var109, var66, var70);
         }
      } else {
         this.clearOutlineHud(var1, var4);
         this.removeBaseHud(var1, var4);
         this.removeRoundedParts(var1, var4);
      }

      HoverElement var111 = new HoverElement();
      var111.id = var4;
      var111.type = var9 ? "block" : var5;
      var111.itemDisplay = var12;
      var111.itemDisplayBlock = var12 && var11 != null && var11.getType().isBlock();
      var111.targetId = this.firstNonBlank(new String[]{var3.getString("__editor_target_id"), var4});
      var111.targetKind = this.firstNonBlank(new String[]{var3.getString("__editor_target_kind"), "block"});
      var111.targetPath = var33;
      var111.componentName = this.firstNonBlank(new String[]{var3.getString("__editor_component_name")});
      var111.bindingX = this.firstNonBlank(new String[]{var3.getString("__editor_binding_x")});
      var111.bindingY = this.firstNonBlank(new String[]{var3.getString("__editor_binding_y")});
      var111.bindingWidth = this.firstNonBlank(new String[]{var3.getString("__editor_binding_width")});
      var111.bindingHeight = this.firstNonBlank(new String[]{var3.getString("__editor_binding_height")});
      var111.centerX = var13 + var40 + var25 / 2.0;
      var111.centerY = var38 + var27 / 2.0;
      var111.z = var17;
      var111.runtimeZ = var50;
      var111.width = var25;
      var111.height = var27;
      var111.rotationDeg = var29;
      var111.pivotOffsetX = var62;
      var111.pivotOffsetY = var64;
      var111.mirrorX = var23;
      var111.mirrorY = var24;
      var111.visible = var81;
      var111.opacity = var31;
      var111.text = var74;
      String var112 = var3.getString("hover_text");
      if (var112 != null && !var112.isBlank()) {
         var111.hoverText = this.withHexPrefix(var112, var73);
      }

      if (var111.hoverText == null || var111.hoverText.isBlank()) {
         String var113 = var3.getString("hover_image_source", "");
         if (var113 != null && !var113.isBlank()) {
            int var116 = var3.getInt("hover_image_row", -1);
            int var117 = var3.getInt("hover_image_col", -1);
            if (var116 >= 0 && var117 >= 0) {
               UiImageAtlasService.GeneratedImage var120 = this.plugin.resolveGeneratedUiImageForEditor(var113);
               if (var120 != null) {
                  String var121 = this.extractGlyphAtRC(this.firstNonBlank(new String[]{var120.glyphMatrix()}), var116, var117);
                  if (var121 != null) {
                     var111.hoverText = this.withHexPrefix("<font:uiimages>" + var121, var73);
                  }
               }
            }
         }
      }

      var111.outlineSize = var108;
      var111.outlineColor = var79;
      var111.outlineText = var80;
      var111.baseLocation = new Vector(var13 + var40, var38, var17);
      var111.baseScale = new Vector(var25, var27, 0.0);
      var111.roundedCornerUnicode = var8 ? this.readRoundedCornerUnicode(var3) : null;
      var111.roundedCornerUnicodeTopLeft = var8 && var7 ? this.readRoundedCornerUnicodeTopLeft(var3) : "";
      var111.roundedCornerUnicodeTopRight = var8 && var7 ? this.readRoundedCornerUnicodeTopRight(var3) : "";
      var111.roundedCornerUnicodeBottomRight = var8 && var7 ? this.readRoundedCornerUnicodeBottomRight(var3) : "";
      var111.roundedCornerUnicodeBottomLeft = var8 && var7 ? this.readRoundedCornerUnicodeBottomLeft(var3) : "";
      var111.roundedTopLeftOffsetX = var8 ? this.readRoundedTopLeftOffsetX(var3) : 0.0;
      var111.roundedTopLeftOffsetY = var8 ? this.readRoundedTopLeftOffsetY(var3) : 0.0;
      var111.roundedTopRightOffsetX = var8 ? this.readRoundedTopRightOffsetX(var3) : 0.0;
      var111.roundedTopRightOffsetY = var8 ? this.readRoundedTopRightOffsetY(var3) : 0.0;
      var111.roundedBottomRightOffsetX = var8 ? this.readRoundedBottomRightOffsetX(var3) : 0.0;
      var111.roundedBottomRightOffsetY = var8 ? this.readRoundedBottomRightOffsetY(var3) : 0.0;
      var111.roundedBottomLeftOffsetX = var8 ? this.readRoundedBottomLeftOffsetX(var3) : 0.0;
      var111.roundedBottomLeftOffsetY = var8 ? this.readRoundedBottomLeftOffsetY(var3) : 0.0;
      var111.hudAlignment = var35;
      var111.textAlignment = var36;
      var111.interactive = true;
      var111.onClickAction = var111.targetPath;
      String var114 = this.firstNonBlank(new String[]{var3.getString("click.effect"), var3.getString("clickEffect")}).trim();
      if (!var114.isBlank()) {
         var111.clickEffect = var114;
      }

      var111.hitboxOffsetX = this.readDouble(var3, "hitbox.x", "hitbox.x", 0.0);
      var111.hitboxOffsetY = this.readDouble(var3, "hitbox.y", "hitbox.y", 0.0);
      var111.disableHitbox = var3.getBoolean("disable-hitbox", var3.getBoolean("disableHitbox", false));
      if (var10 && var2 != null && var2.editMode && (var3.contains("glyph_matrix") || var3.contains("glyphMatrix"))) {
         var111.hitboxOffsetX -= 29.0;
         var111.hitboxOffsetY -= 248.0;
      }

      return var111;
   }

   protected void renderEditorShell(Player var1, EditorSession var2) {
      if (var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         HashMap var3 = new HashMap();
         int var4 = 0;

         for (Map var6 : var2.shellBlocks) {
            var4++;
            ConfigurationSection var7 = this.mapToSection(var6);
            if (var7 != null) {
               String var8 = this.firstNonBlank(new String[]{var7.getString("type"), "block"}).toLowerCase(Locale.ROOT);
               if (this.isRoundedType(var8) && var2.optimizedEditor) {
                  var8 = "block";
               }

               if (this.isRenderableBlockType(var8)) {
                  String var9 = this.resolveElementId(var7, var4, var3);
                  if (!this.isEditorToolShellBlockId(var9)) {
                     String var10 = "editor_shell_" + var9;
                     if (this.shouldSkipEditorShellBlockForSpawnRetoggle(var2, var9)) {
                        this.clearOutlineHud(var1, var10);
                        this.removeBaseHud(var1, var10);
                        this.removeRoundedParts(var1, var10);
                        if (var2.shellRuntimeRects != null) {
                           var2.shellRuntimeRects.remove(var9);
                        }
                     } else if (!this.shouldRenderEditorShellBlock(var2, var9, var7)) {
                        this.clearOutlineHud(var1, var10);
                        this.removeBaseHud(var1, var10);
                        this.removeRoundedParts(var1, var10);
                        if (var2.shellRuntimeRects != null) {
                           var2.shellRuntimeRects.remove(var9);
                        }
                     } else {
                        double var11 = this.readDouble(var7, "position.x", "x", 0.0);
                        double var13 = this.readDouble(var7, "position.y", "y", 0.0);
                        double var15 = this.readDouble(var7, "layer", "layer", this.readDouble(var7, "size.depth", "depth", 0.0));
                        double var17 = this.readDouble(var7, "size.width", "width", this.readDouble(var7, "scale.width", "width", 20.0));
                        double var19 = this.readDouble(var7, "size.height", "height", this.readDouble(var7, "scale.height", "height", 20.0));
                        boolean var21 = var17 < 0.0;
                        boolean var22 = var19 < 0.0;
                        double var23 = Math.max(1.0, Math.abs(var17));
                        double var25 = Math.max(1.0, Math.abs(var19));
                        double var27 = this.readDouble(var7, "rotation", "rotate", 0.0);
                        int var29 = this.readOpacity(var7, 255);
                        var29 = this.resolveShellBlockInitialOpacity(var2, var9, var7, var29);
                        String var30 = this.firstNonBlank(new String[]{var7.getString("__editor_target_path")});
                        TextAlignment var31 = this.readHudAlignment(var7);
                        TextAlignment var32 = var31 == null ? TextAlignment.CENTER : var31;
                        TextAlignment var33 = "text".equals(var8) ? this.readTextAlignment(var7) : TextAlignment.CENTER;
                        int var34 = "text".equals(var8) ? this.readTextWrapLineWidth(var7) : 200;
                        boolean var35 = "text".equals(var8) && "opacity_circle".equals(var9);
                        double var36 = "text".equals(var8) && !var35 ? this.toInternalTextTopY(var13, var25) : var13;
                        if ("hitbox".equals(var8)) {
                           this.clearOutlineHud(var1, var10);
                           this.removeBaseHud(var1, var10);
                           this.removeRoundedParts(var1, var10);
                           if (var2.shellRuntimeRects != null) {
                              var2.shellRuntimeRects.put(var9, new EditorRect(var11, var13, var23, var25));
                           }
                        } else {
                           boolean var38 = this.shouldSpawnOpacityVisual(var8, var29);
                           boolean var39 = !var38 && this.shouldKeepHiddenShellBlockSpawned(var2, var9, var7, var8);
                           if (!var38 && !var39) {
                              this.clearOutlineHud(var1, var10);
                              this.removeBaseHud(var1, var10);
                              this.removeRoundedParts(var1, var10);
                              if (var2.shellRuntimeRects != null) {
                                 var2.shellRuntimeRects.put(var9, new EditorRect(var11, var13, var23, var25));
                              }
                           } else {
                              double var40 = "text".equals(var8) ? this.applyTextAlignmentOffset(var11, var23, var33) : var11;
                              double var42 = this.resolveHudAlignmentOffsetX(var23, var32);
                              double var44 = var40 + var42;
                              double var46 = var40 + var23 / 2.0;
                              double var48 = var44 + var23 / 2.0;
                              double var50 = var36 + var25 / 2.0;
                              HudPositionCalculator.Placement var52 = this.positionCalculator.calculateBoxPlacement(var40, var36, var15, var23, var25);
                              Vector var53 = var52.location();
                              Vector var54 = var52.scale();
                              String var55;
                              if ("text".equals(var8)) {
                                 var55 = this.firstNonBlank(new String[]{var7.getString("text"), ""});
                              } else {
                                 var55 = this.firstNonBlank(new String[]{var7.getString("unicode"), var7.getString("text"), "█"});
                              }

                              String var56 = this.firstNonBlank(new String[]{var7.getString("color"), var7.getString("style.color")});
                              if ("editor_color_1".equalsIgnoreCase(var9)) {
                                 var56 = this.firstNonBlank(new String[]{var2.editorColor1, var56});
                              } else if ("editor_color_2".equalsIgnoreCase(var9)) {
                                 var56 = this.firstNonBlank(new String[]{var2.editorColor2, var56});
                              }

                              var55 = this.withHexPrefix(var55, var56);
                              String var57 = this.resolveBlockPlaceholders(var1, var2, var7, var55);
                              var57 = this.applyPreferredFont(var57, var7, !"text".equals(var8));
                              double var58 = "text".equals(var8) ? 0.0 : this.readOutlineSize(var7);
                              String var60 = "text".equals(var8) ? "" : this.readOutlineColor(var7);
                              String var61 = !"text".equals(var8) && var58 > 1.0E-4 ? this.applyOutlineColor(var57, var60) : null;
                              if (this.isRoundedType(var8)) {
                                 boolean var62 = this.isDirectionalRoundedType(var8);
                                 double var63 = this.readRoundedRadius(var7, var23, var25);
                                 String var65 = this.readRoundedCornerUnicode(var7);
                                 String var66 = var62 ? this.readRoundedCornerUnicodeTopLeft(var7) : "";
                                 String var67 = var62 ? this.readRoundedCornerUnicodeTopRight(var7) : "";
                                 String var68 = var62 ? this.readRoundedCornerUnicodeBottomRight(var7) : "";
                                 String var69 = var62 ? this.readRoundedCornerUnicodeBottomLeft(var7) : "";
                                 double var70 = this.readRoundedTopLeftOffsetX(var7);
                                 double var72 = this.readRoundedTopLeftOffsetY(var7);
                                 double var74 = this.readRoundedTopRightOffsetX(var7);
                                 double var76 = this.readRoundedTopRightOffsetY(var7);
                                 double var78 = this.readRoundedBottomRightOffsetX(var7);
                                 double var80 = this.readRoundedBottomRightOffsetY(var7);
                                 double var82 = this.readRoundedBottomLeftOffsetX(var7);
                                 double var84 = this.readRoundedBottomLeftOffsetY(var7);
                                 this.renderRoundedOutline(
                                    var1,
                                    var10,
                                    var44,
                                    var36,
                                    var15,
                                    var23,
                                    var25,
                                    var61,
                                    var57,
                                    var29,
                                    var63,
                                    var65,
                                    var66,
                                    var67,
                                    var68,
                                    var69,
                                    var70,
                                    var72,
                                    var74,
                                    var76,
                                    var78,
                                    var80,
                                    var82,
                                    var84,
                                    var27,
                                    var21,
                                    var22,
                                    var58,
                                    var48,
                                    var50,
                                    var32
                                 );
                                 this.renderRoundedBlock(
                                    var1,
                                    var10,
                                    var44,
                                    var36,
                                    var15,
                                    var23,
                                    var25,
                                    var57,
                                    var29,
                                    var63,
                                    var65,
                                    var66,
                                    var67,
                                    var68,
                                    var69,
                                    var70,
                                    var72,
                                    var74,
                                    var76,
                                    var78,
                                    var80,
                                    var82,
                                    var84,
                                    var27,
                                    var21,
                                    var22,
                                    var48,
                                    var50,
                                    var32
                                 );
                              } else {
                                 this.upsertHud(var1, var10, var53, var54, var57, var29, var32, var33, var34);
                                 this.applyElementTransformById(var1, var10, var27, var21, var22);
                                 if ("text".equals(var8)) {
                                    this.clearOutlineHud(var1, var10);
                                 } else {
                                    this.renderSimpleOutline(
                                       var1, var10, var40, var36, var15, var23, var25, var61, var29, var32, var33, var27, var21, var22, var58, var46, var50
                                    );
                                 }
                              }

                              if (var2.shellRuntimeRects != null) {
                                 var2.shellRuntimeRects.put(var9, new EditorRect(var11, var13, var23, var25));
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected TextAlignment readTextAlignment(ConfigurationSection var1) {
      String var2 = this.firstNonBlank(
         new String[]{
            var1 == null ? null : var1.getString("align"),
            var1 == null ? null : var1.getString("textAlign"),
            var1 == null ? null : var1.getString("text.align")
         }
      );
      if (var2.isBlank()) {
         return TextAlignment.CENTER;
      } else {
         String var3 = var2.toLowerCase(Locale.ROOT);

         return switch (var3) {
            case "left", "start" -> TextAlignment.LEFT;
            case "right", "end" -> TextAlignment.RIGHT;
            default -> TextAlignment.CENTER;
         };
      }
   }

   protected TextAlignment readHudAlignment(ConfigurationSection var1) {
      String var2 = this.firstNonBlank(
         new String[]{
            var1 == null ? null : var1.getString("aligned"),
            var1 == null ? null : var1.getString("position.align"),
            var1 == null ? null : var1.getString("hudAligned"),
            var1 == null ? null : var1.getString("hud.aligned"),
            var1 == null ? null : var1.getString("params.aligned"),
            var1 == null ? null : var1.getString("params.hudAligned"),
            var1 == null ? null : var1.getString("params.hud.aligned"),
            var1 == null ? null : var1.getString("anchor"),
            var1 == null ? null : var1.getString("hud.anchor"),
            var1 == null ? null : var1.getString("params.anchor"),
            var1 == null ? null : var1.getString("params.hud.anchor")
         }
      );
      return this.parseHudAlignment(var2);
   }

   protected TextAlignment readHudAlignment(Map<String, Object> var1) {
      String var2 = this.firstNonBlank(
         new String[]{
            this.readRawMapString(var1, "aligned"),
            this.readRawMapString(var1, "position.align"),
            this.readRawMapString(var1, "hudAligned"),
            this.readRawMapString(var1, "hud.aligned"),
            this.readRawMapString(var1, "params.aligned"),
            this.readRawMapString(var1, "params.hudAligned"),
            this.readRawMapString(var1, "params.hud.aligned"),
            this.readRawMapString(var1, "anchor"),
            this.readRawMapString(var1, "hud.anchor"),
            this.readRawMapString(var1, "params.anchor"),
            this.readRawMapString(var1, "params.hud.anchor")
         }
      );
      return this.parseHudAlignment(var2);
   }

   protected TextAlignment parseHudAlignment(String var1) {
      if (var1.isBlank()) {
         return TextAlignment.CENTER;
      } else {
         String var2 = var1.trim().toLowerCase(Locale.ROOT).replace('_', ' ').replace('-', ' ');
         if (var2.contains("left")) {
            return TextAlignment.LEFT;
         } else if (var2.contains("right")) {
            return TextAlignment.RIGHT;
         } else if (!var2.contains("center") && !var2.contains("middle")) {
            try {
               int var3 = (int)Math.round(Double.parseDouble(var2));
               if (var3 == 10000) {
                  return TextAlignment.LEFT;
               }

               if (var3 == 30000) {
                  return TextAlignment.RIGHT;
               }
            } catch (NumberFormatException var4) {
            }

            return TextAlignment.CENTER;
         } else {
            return TextAlignment.CENTER;
         }
      }
   }

   protected TextAlignment resolveEffectiveHudAlignment(EditorSession var1, TextAlignment var2, String var3) {
      if (var1 != null && var1.editMode) {
         return TextAlignment.CENTER;
      } else if (this.hasLeftAlignedAncestor(var1, var3)) {
         return TextAlignment.LEFT;
      } else {
         return var2 == null ? TextAlignment.CENTER : var2;
      }
   }

   protected boolean hasLeftAlignedAncestor(EditorSession var1, String var2) {
      if (var1 != null && var1.rawBlocks != null && !var1.rawBlocks.isEmpty()) {
         String var3 = this.firstNonBlank(new String[]{var2});

         while (!var3.isBlank()) {
            String var4 = this.resolveParentRawTargetPath(var1.rawBlocks, var3);
            if (var4.isBlank()) {
               return false;
            }

            Map var5 = this.resolveRawMapAtPath(var1.rawBlocks, var4);
            if (this.readHudAlignment(var5) == TextAlignment.LEFT) {
               return true;
            }

            var3 = var4;
         }

         return false;
      } else {
         return false;
      }
   }

   protected String resolveParentRawTargetPath(List<Map<String, Object>> var1, String var2) {
      if (var1 != null && !var1.isEmpty()) {
         String var3 = this.firstNonBlank(new String[]{var2});

         while (!var3.isBlank()) {
            int var4 = var3.lastIndexOf(46);
            if (var4 < 0) {
               return "";
            }

            var3 = var3.substring(0, var4);
            if (this.resolveRawMapAtPath(var1, var3) != null) {
               return var3;
            }
         }

         return "";
      } else {
         return "";
      }
   }

   protected String readRawMapString(Map<String, Object> var1, String var2) {
      if (var1 != null && !var1.isEmpty() && var2 != null && !var2.isBlank()) {
         Object var3 = var1.get(var2);
         if (var3 != null) {
            return var3.toString();
         } else {
            Object var4 = var1;
            String[] var5 = var2.split("\\.");

            for (Object var9_raw : var5) {
               String var9 = var9_raw != null ? var9_raw.toString() : null;
               if (!(var4 instanceof Map var10)) {
                  return "";
               }

               var4 = var10.get(var9);
               if (var4 == null) {
                  return "";
               }
            }

            return var4.toString();
         }
      } else {
         return "";
      }
   }

   protected double resolveHudAlignmentOffsetX(double var1, TextAlignment var3) {
      TextAlignment var4 = var3 == null ? TextAlignment.CENTER : var3;
      if (var4 == TextAlignment.LEFT) {
         return 0.0;
      } else {
         return var4 == TextAlignment.RIGHT ? 0.0 : 0.0;
      }
   }

   protected int readTextWrapLineWidth(ConfigurationSection var1) {
      Object var2 = this.readFirstPresentValue(var1, "text-wrap", "textWrap", "text.wrap");
      return this.normalizeTextWrapLineWidth(var2);
   }

   protected int normalizeTextWrapLineWidth(Object var1) {
      if (var1 == null) {
         return 200;
      } else if (var1 instanceof Number var6) {
         int var7 = (int)Math.round(var6.doubleValue());
         return var7 > 0 ? var7 : 200;
      } else {
         String var2 = var1.toString().trim();
         if (var2.isEmpty()) {
            return 200;
         } else {
            double var3 = this.parseDouble(var2, Double.NaN);
            if (!Double.isFinite(var3)) {
               return 200;
            } else {
               int var5 = (int)Math.round(var3);
               return var5 > 0 ? var5 : 200;
            }
         }
      }
   }

   private Object readFirstPresentValue(ConfigurationSection var1, String... var2) {
      if (var1 != null && var2 != null) {
         for (Object var6_raw : var2) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            if (var6 != null && !var6.isBlank() && var1.contains(var6)) {
               return var1.get(var6);
            }
         }

         return null;
      } else {
         return null;
      }
   }

   protected double applyTextAlignmentOffset(double var1, double var3, TextAlignment var5) {
      TextAlignment var6 = var5 == null ? TextAlignment.CENTER : var5;
      if (var6 == TextAlignment.LEFT) {
         return var1 + var3 * 1.055;
      } else {
         return var6 == TextAlignment.RIGHT ? var1 - var3 * 1.055 : var1;
      }
   }

   protected String runtimeElementId(EditorSession var1, String var2) {
      return var1.previewMode ? "preview_content_" + var2 : var2;
   }

   protected String resolveElementId(ConfigurationSection var1, int var2, Map<String, Integer> var3) {
      String var4 = this.firstNonBlank(new String[]{var1.getString("id")});
      if (var4.isBlank()) {
         String var5 = this.firstNonBlank(new String[]{var1.getString("__editor_block_path")});
         if (!var5.isBlank()) {
            var4 = "path_" + this.sanitizeGeneratedRuntimeId(var5);
         }
      }

      String var8 = var4.isBlank() ? "block_" + var2 : var4;
      Integer var6 = (Integer)var3.get(var8);
      if (var6 == null) {
         var3.put(var8, 1);
         return var8;
      } else {
         int var7 = var6 + 1;
         var3.put(var8, var7);
         return var8 + "_" + var7;
      }
   }

   protected String sanitizeGeneratedRuntimeId(String var1) {
      if (var1 != null && !var1.isBlank()) {
         StringBuilder var2 = new StringBuilder(var1.length() + 8);

         for (char var6 : var1.toCharArray()) {
            if (!Character.isLetterOrDigit(var6) && var6 != '_' && var6 != '-') {
               if (var2.length() == 0 || var2.charAt(var2.length() - 1) != '_') {
                  var2.append('_');
               }
            } else {
               var2.append(var6);
            }
         }

         while (var2.length() > 0 && var2.charAt(var2.length() - 1) == '_') {
            var2.setLength(var2.length() - 1);
         }

         return var2.toString();
      } else {
         return "";
      }
   }

   protected void updateElementHud(Player var1, HoverElement var2) {
      EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
      if (var3 != null) {
         if (var2 != null) {
            if ("hitbox".equals(var2.type)) {
               this.clearOutlineHud(var1, var2.id);
               this.removeBaseHud(var1, var2.id);
               this.removeRoundedParts(var1, var2.id);
            } else if (!var2.visible) {
               if (var2 != null) {
                  this.clearOutlineHud(var1, var2.id);
                  this.removeBaseHud(var1, var2.id);
                  this.removeRoundedParts(var1, var2.id);
               }
            } else if (this.isRoundedType(var2.type)) {
               this.renderRoundedBlockForElement(var1, var2);
            } else {
               double var4 = this.toRenderSize(var3, var2.baseScale.getX());
               double var6 = this.toRenderSize(var3, var2.baseScale.getY());
               double var8 = this.applyTextAlignmentOffset(this.toRenderX(var3, var2.baseLocation.getX()), var4, var2.textAlignment);
               double var10 = this.toRenderY(var3, var2.baseLocation.getY());
               double var12 = var8 + this.toRenderSize(var3, var2.pivotOffsetX);
               double var14 = var10 + this.toRenderSize(var3, var2.pivotOffsetY);
               double[] var16 = this.resolvePivotAwareTopLeft(var8, var10, var4, var6, var12, var14, var2.rotationDeg);
               double var17 = "text".equals(var2.type) ? 0.0 : this.resolveRotationVisualXOffset(var2.rotationDeg, var4);
               double var19 = "text".equals(var2.type) ? 0.0 : this.resolveRotationVisualYOffset(var2.rotationDeg, var6);
               Entity var21 = this.hudService.getHud(var1, var2.id);
               boolean var22 = var21 instanceof ItemDisplay;
               double var23 = var16[0] + var17;
               double var25 = var16[1] + var19;
               if (var22) {
                  var25 -= var6 * 0.56;
                  double[] var27 = this.resolveItemDisplayRotationOffset(var2.rotationDeg, var4, var6);
                  var23 += var27[0];
                  var25 += var27[1];
                  double[] var28 = this.resolveItemDisplayNonBlockRotationCompensation(var2.rotationDeg, var4, var6);
                  var23 += var28[0];
                  var25 += var28[1];
               }

               HudPositionCalculator.Placement var35 = this.positionCalculator.calculateBoxPlacement(var23, var25, var2.runtimeZ, var4, var6);
               int var36 = this.resolveEditorHudTransitionTicks(var3);
               if (var21 != null) {
                  Vector var29 = var35.scale();
                  if (var22) {
                     var29 = this.resolveItemDisplayScale(var29);
                  }

                  this.hudService.moveHud(var21, var35.location(), var36, var36);
                  this.hudService.setHudScale(var21, var29, var36, false);
                  this.applyElementTransform(var21, var2.rotationDeg, var2.mirrorX, var2.mirrorY);
               }

               double var37 = this.toRenderSize(var3, Math.max(0.0, var2.outlineSize));
               if (!"text".equals(var2.type) && var37 > 1.0E-4) {
                  String var31 = var2.outlineText;
                  if (var31 == null || var31.isBlank()) {
                     var31 = this.applyOutlineColor(var2.text, var2.outlineColor);
                  }

                  this.renderSimpleOutline(
                     var1,
                     var2.id,
                     var8,
                     var10,
                     var2.runtimeZ,
                     var4,
                     var6,
                     var31,
                     var2.opacity,
                     TextAlignment.CENTER,
                     var2.textAlignment,
                     var2.rotationDeg,
                     var2.mirrorX,
                     var2.mirrorY,
                     var37,
                     var12,
                     var14
                  );
               } else {
                  this.clearOutlineHud(var1, var2.id);
               }
            }
         }
      }
   }

   protected void renderSimpleOutline(
      Player var1,
      String var2,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      String var13,
      int var14,
      TextAlignment var15,
      double var16,
      boolean var18,
      boolean var19,
      double var20,
      double var22,
      double var24
   ) {
      this.renderSimpleOutline(var1, var2, var3, var5, var7, var9, var11, var13, var14, TextAlignment.CENTER, var15, var16, var18, var19, var20, var22, var24);
   }

   protected void renderSimpleOutline(
      Player var1,
      String var2,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      String var13,
      int var14,
      TextAlignment var15,
      TextAlignment var16,
      double var17,
      boolean var19,
      boolean var20,
      double var21,
      double var23,
      double var25
   ) {
      if (var13 != null && !var13.isBlank() && !(var21 <= 1.0E-4)) {
         double var27 = var3;
         double var29 = var5;
         double var31 = Math.max(1.0, var9);
         double var33 = Math.max(1.0, var11);
         if (this.hudService.getHud(var1, var2) instanceof ItemDisplay var36) {
            ItemStack var37 = var36.getItemStack();
            if (var37 != null && var37.getType().isBlock()) {
               double var38 = Math.max(1.0, var31 * 1.024);
               double var40 = Math.max(1.0, var33 * 1.024);
               double var42 = var38 * 0.014;
               double var44 = var40 * 0.014;
               double var46 = var40 * 0.018;
               var27 = var3 - (var38 - var31) / 2.0;
               var29 = var5 - (var40 - var33) / 2.0;
               var27 -= var42;
               var29 -= var44 + var46;
               var31 = Math.max(1.0, var38 + var42);
               var33 = Math.max(1.0, var40 + var44);
            }
         }

         double var51 = var27 - var21;
         double var52 = var29 - var21;
         double var53 = Math.max(1.0, var31 + var21 * 2.0);
         double var54 = Math.max(1.0, var33 + var21 * 2.0);
         double var55 = var7 - 0.01;
         double[] var56 = this.resolvePivotAwareTopLeft(var51, var52, var53, var54, var23, var25, var17);
         HudPositionCalculator.Placement var47 = this.positionCalculator.calculateBoxPlacement(var56[0], var56[1], var55, var53, var54);
         String var48 = var2 + "_outline";
         this.upsertHud(var1, var48, var47.location(), var47.scale(), var13, var14, var15, var16, 200);
         this.applyElementTransformById(var1, var48, var17, var19, var20);
      } else {
         this.clearOutlineHud(var1, var2);
      }
   }

   protected void renderRoundedOutline(
      Player var1,
      String var2,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      String var13,
      String var14,
      int var15,
      double var16,
      String var18,
      double var19,
      double var21,
      double var23,
      double var25,
      double var27,
      double var29,
      double var31,
      double var33,
      double var35,
      boolean var37,
      boolean var38,
      double var39,
      double var41,
      double var43
   ) {
      this.renderRoundedOutline(
         var1,
         var2,
         var3,
         var5,
         var7,
         var9,
         var11,
         var13,
         var14,
         var15,
         var16,
         var18,
         var19,
         var21,
         var23,
         var25,
         var27,
         var29,
         var31,
         var33,
         var35,
         var37,
         var38,
         var39,
         var41,
         var43,
         TextAlignment.CENTER
      );
   }

   protected void renderRoundedOutline(
      Player var1,
      String var2,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      String var13,
      String var14,
      int var15,
      double var16,
      String var18,
      double var19,
      double var21,
      double var23,
      double var25,
      double var27,
      double var29,
      double var31,
      double var33,
      double var35,
      boolean var37,
      boolean var38,
      double var39,
      double var41,
      double var43,
      TextAlignment var45
   ) {
      this.renderRoundedOutline(
         var1,
         var2,
         var3,
         var5,
         var7,
         var9,
         var11,
         var13,
         var14,
         var15,
         var16,
         var18,
         "",
         "",
         "",
         "",
         var19,
         var21,
         var23,
         var25,
         var27,
         var29,
         var31,
         var33,
         var35,
         var37,
         var38,
         var39,
         var41,
         var43,
         var45
      );
   }

   protected void renderRoundedOutline(
      Player var1,
      String var2,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      String var13,
      String var14,
      int var15,
      double var16,
      String var18,
      String var19,
      String var20,
      String var21,
      String var22,
      double var23,
      double var25,
      double var27,
      double var29,
      double var31,
      double var33,
      double var35,
      double var37,
      double var39,
      boolean var41,
      boolean var42,
      double var43,
      double var45,
      double var47
   ) {
      this.renderRoundedOutline(
         var1,
         var2,
         var3,
         var5,
         var7,
         var9,
         var11,
         var13,
         var14,
         var15,
         var16,
         var18,
         var19,
         var20,
         var21,
         var22,
         var23,
         var25,
         var27,
         var29,
         var31,
         var33,
         var35,
         var37,
         var39,
         var41,
         var42,
         var43,
         var45,
         var47,
         TextAlignment.CENTER
      );
   }

   protected void renderRoundedOutline(
      Player var1,
      String var2,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      String var13,
      String var14,
      int var15,
      double var16,
      String var18,
      String var19,
      String var20,
      String var21,
      String var22,
      double var23,
      double var25,
      double var27,
      double var29,
      double var31,
      double var33,
      double var35,
      double var37,
      double var39,
      boolean var41,
      boolean var42,
      double var43,
      double var45,
      double var47,
      TextAlignment var49
   ) {
      if (var13 != null && !var13.isBlank() && !(var43 <= 1.0E-4)) {
         double var50 = var3 - var43;
         double var52 = var5 - var43;
         double var54 = Math.max(1.0, var9 + var43 * 2.0);
         double var56 = Math.max(1.0, var11 + var43 * 2.0);
         double var58 = var7 - 0.01;
         double var60 = this.resolveRoundedRadiusScale(var1, var2);
         double var62 = var16 + var43 / var60;
         String var64 = this.withRoundedCornerBaseColor(var13, var18);
         String var65 = this.firstNonBlank(new String[]{var19}).isBlank() ? "" : this.withRoundedCornerBaseColor(var13, var19);
         String var66 = this.firstNonBlank(new String[]{var20}).isBlank() ? "" : this.withRoundedCornerBaseColor(var13, var20);
         String var67 = this.firstNonBlank(new String[]{var21}).isBlank() ? "" : this.withRoundedCornerBaseColor(var13, var21);
         String var68 = this.firstNonBlank(new String[]{var22}).isBlank() ? "" : this.withRoundedCornerBaseColor(var13, var22);
         this.renderRoundedBlock(
            var1,
            var2 + "_outline",
            var50,
            var52,
            var58,
            var54,
            var56,
            var13,
            var15,
            var62,
            var64,
            var65,
            var66,
            var67,
            var68,
            var23,
            var25,
            var27,
            var29,
            var31,
            var33,
            var35,
            var37,
            var39,
            var41,
            var42,
            var45,
            var47,
            var49
         );
      } else {
         this.clearOutlineHud(var1, var2);
      }
   }

   protected void clearOutlineHud(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = var2 + "_outline";
         this.removeBaseHud(var1, var3);
         this.removeRoundedParts(var1, var3);
      }
   }

   protected double getInteractiveWidth(HoverElement var1) {
      if (var1 != null && var1.baseScale != null) {
         double var2 = Math.max(1.0, var1.baseScale.getX());
         if (var1.itemDisplayBlock) {
            double var4 = Math.max(1.0, var2 * 1.024);
            double var6 = var4 * 0.014;
            return Math.max(1.0, var4 + var6);
         } else {
            return var2;
         }
      } else {
         return 1.0;
      }
   }

   protected double getInteractiveHeight(HoverElement var1) {
      if (var1 != null && var1.baseScale != null) {
         double var2 = Math.max(1.0, var1.baseScale.getY());
         if (var1.itemDisplay) {
            if (var1.itemDisplayBlock) {
               double var4 = Math.max(1.0, var2 * 1.024);
               double var6 = var4 * 0.014;
               return Math.max(1.0, var4 + var6);
            } else {
               return var2;
            }
         } else {
            return !"text".equals(var1.type) ? var2 : Math.max(1.0, var2 * 0.11666666666666667);
         }
      } else {
         return 1.0;
      }
   }

   protected double getInteractiveLeftX(HoverElement var1) {
      if (var1 != null && var1.baseLocation != null && var1.baseScale != null) {
         double var2 = var1.baseLocation.getX();
         if (!var1.itemDisplay) {
            return var2 + var1.hitboxOffsetX;
         } else {
            double var4 = Math.max(1.0, var1.baseScale.getX());
            double var6 = Math.max(1.0, var1.baseScale.getY());
            double[] var8 = this.resolveItemDisplayRotationOffset(var1.rotationDeg, var4, var6);
            double var9 = var2 + var8[0];
            double[] var11 = this.resolveItemDisplayNonBlockRotationCompensation(var1.rotationDeg, var4, var6);
            var9 += var11[0];
            if (var1.itemDisplayBlock) {
               double var12 = Math.max(1.0, var4 * 1.024);
               var9 -= (var12 - var4) / 2.0;
               var9 -= var12 * 0.014;
            }

            return var9 + var1.hitboxOffsetX;
         }
      } else {
         return 0.0;
      }
   }

   protected double getInteractiveTopY(HoverElement var1) {
      if (var1 != null && var1.baseLocation != null && var1.baseScale != null) {
         double var2 = var1.baseLocation.getY();
         double var4 = Math.max(1.0, var1.baseScale.getY());
         if (var1.itemDisplay) {
            double[] var12 = this.resolveItemDisplayRotationOffset(var1.rotationDeg, Math.max(1.0, var1.baseScale.getX()), var4);
            double var7 = var2 + var12[1];
            double[] var9 = this.resolveItemDisplayNonBlockRotationCompensation(var1.rotationDeg, Math.max(1.0, var1.baseScale.getX()), var4);
            var7 += var9[1];
            if (var1.itemDisplayBlock) {
               double var10 = Math.max(1.0, var4 * 1.024);
               var7 -= (var10 - var4) / 2.0;
               var7 -= var10 * 0.014;
               var7 -= var10 * 0.018;
            }

            return var7 + var1.hitboxOffsetY;
         } else if (!"text".equals(var1.type)) {
            return var2 + var1.hitboxOffsetY;
         } else {
            double var6 = this.getInteractiveHeight(var1);
            return var2 + (var4 - var6) + var1.hitboxOffsetY;
         }
      } else {
         return 0.0;
      }
   }

   protected double toInternalTextTopY(double var1, double var3) {
      double var5 = Math.max(1.0, var3);
      double var7 = Math.max(1.0, var5 * 0.11666666666666667);
      return var1 - (var5 - var7);
   }

   protected PreviewViewport resolvePreviewViewport(List<Map<String, Object>> var1, double var2) {
      if (var1 != null && !var1.isEmpty()) {
         double var4 = 1920.0;
         double var6 = 1080.0;

         for (Map var9 : var1) {
            ConfigurationSection var10 = this.mapToSection(var9);
            if (var10 != null) {
               String var11 = this.firstNonBlank(new String[]{var10.getString("id")});
               if ("preview".equalsIgnoreCase(var11)) {
                  double var12 = this.readDouble(var10, "position.x", "x", 0.0);
                  double var14 = this.readDouble(var10, "position.y", "y", 0.0);
                  double var16 = Math.max(1.0, this.readDouble(var10, "size.width", "width", this.readDouble(var10, "scale.width", "width", 1.0)));
                  double var18 = Math.max(1.0, this.readDouble(var10, "size.height", "height", this.readDouble(var10, "scale.height", "height", 1.0)));
                  double var20 = this.readDouble(var10, "layer", "layer", this.readDouble(var10, "size.depth", "depth", 0.0));
                  double var22 = Math.min(var16 / var4, var18 / var6);
                  if (!(var22 <= 0.0) && Double.isFinite(var22)) {
                     double var24 = var2;
                     if (Double.isFinite(var2)) {
                        var24 = Math.max(0.1, Math.min(6.0, var2));
                     }

                     return new PreviewViewport(var12, var14, var16, var18, var4, var6, var22, var20 + 5.0, var24);
                  }

                  return null;
               }
            }
         }

         return null;
      } else {
         return null;
      }
   }

   protected boolean isInsidePreviewArea(EditorSession var1, double var2, double var4) {
      if (var1.previewMode && var1.previewViewport != null) {
         PreviewViewport var6 = var1.previewViewport;
         return var2 >= var6.previewX && var2 <= var6.previewX + var6.previewWidth && var4 >= var6.previewY && var4 <= var6.previewY + var6.previewHeight;
      } else {
         return true;
      }
   }

   protected double toRenderX(EditorSession var1, double var2) {
      return var1.previewMode && var1.previewViewport != null ? this.getPreviewOriginX(var1) + var2 * this.getPreviewScale(var1) : var2;
   }

   protected double toRenderY(EditorSession var1, double var2) {
      return var1.previewMode && var1.previewViewport != null ? this.getPreviewOriginY(var1) + var2 * this.getPreviewScale(var1) : var2;
   }

   protected double toRenderSize(EditorSession var1, double var2) {
      return var1.previewMode && var1.previewViewport != null ? var2 * this.getPreviewScale(var1) : var2;
   }

   protected double toLogicalCursorX(EditorSession var1, double var2) {
      return var1.previewMode && var1.previewViewport != null ? (var2 - this.getPreviewOriginX(var1)) / this.getPreviewScale(var1) : var2;
   }

   protected double toLogicalCursorY(EditorSession var1, double var2) {
      return var1.previewMode && var1.previewViewport != null ? (var2 - this.getPreviewOriginY(var1)) / this.getPreviewScale(var1) : var2;
   }

   protected double toRuntimeLayer(EditorSession var1, double var2) {
      if (var1.previewMode && var1.previewViewport != null) {
         double var4 = 8600.0 + var2;
         return !Double.isFinite(var4) ? 8600.0 : Math.max(8000.0, Math.min(8992.0, var4));
      } else {
         return var2;
      }
   }

   protected double defaultRuntimeLayer(EditorSession var1, HoverElement var2) {
      return var2 == null ? 0.0 : this.toRuntimeLayer(var1, var2.z);
   }

   protected double getPreviewScale(EditorSession var1) {
      PreviewViewport var2 = var1.previewViewport;
      return var2.baseScale * var2.zoom;
   }

   protected double getPreviewOriginX(EditorSession var1) {
      PreviewViewport var2 = var1.previewViewport;
      double var3 = this.getPreviewScale(var1);
      double var5 = var2.previewX + (var2.previewWidth - var2.pageWidth * var3) / 2.0;
      return var5 + var2.panX;
   }

   protected double getPreviewOriginY(EditorSession var1) {
      PreviewViewport var2 = var1.previewViewport;
      double var3 = this.getPreviewScale(var1);
      double var5 = var2.previewY + (var2.previewHeight - var2.pageHeight * var3) / 2.0;
      return var5 + var2.panY;
   }

   protected double[] clampPreviewPanToVisibleRange(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.previewMode && var1.previewViewport != null) {
         PreviewViewport var6 = var1.previewViewport;
         double var7 = this.getPreviewScale(var1);
         double var9 = var6.previewX + (var6.previewWidth - var6.pageWidth * var7) / 2.0;
         double var11 = var6.previewY + (var6.previewHeight - var6.pageHeight * var7) / 2.0;
         double var13 = var6.pageWidth * var7;
         double var15 = var6.pageHeight * var7;
         double var17 = 150.0;
         double var19 = var6.previewX + var17 - var9 - var13;
         double var21 = var6.previewX + var6.previewWidth - var17 - var9;
         double var23 = var6.previewY + var17 - var11 - var15;
         double var25 = var6.previewY + var6.previewHeight - var17 - var11;
         return new double[]{Math.max(var19, Math.min(var21, var2)), Math.max(var23, Math.min(var25, var4))};
      } else {
         return new double[]{var2, var4};
      }
   }

   protected double softClampToBounds(double var1, double var3, double var5) {
      return Math.abs(var1 - var3) < 1.0E-4 ? var3 : var1 + (var3 - var1) * var5;
   }

   protected boolean applyPreviewZoom(EditorSession var1, double var2, double var4, double var6) {
      if (!var1.previewMode || var1.previewViewport == null || var6 <= 0.0 || !Double.isFinite(var6)) {
         return false;
      } else if (!this.isInsidePreviewArea(var1, var2, var4)) {
         return false;
      } else {
         PreviewViewport var8 = var1.previewViewport;
         double var9 = this.getPreviewScale(var1);
         double var11 = this.getPreviewOriginX(var1);
         double var13 = this.getPreviewOriginY(var1);
         double var15 = (var2 - var11) / var9;
         double var17 = (var4 - var13) / var9;
         double var19 = var8.zoom * var6;
         var19 = Math.max(0.1, Math.min(6.0, var19));
         if (Double.isFinite(var19) && !(Math.abs(var19 - var8.zoom) < 1.0E-4)) {
            var8.zoom = var19;
            double var21 = this.getPreviewScale(var1);
            double var23 = var8.previewX + (var8.previewWidth - var8.pageWidth * var21) / 2.0;
            double var25 = var8.previewY + (var8.previewHeight - var8.pageHeight * var21) / 2.0;
            double var27 = var2 - var15 * var21;
            double var29 = var4 - var17 * var21;
            var8.panX = var27 - var23;
            var8.panY = var29 - var25;
            return true;
         } else {
            return false;
         }
      }
   }

   protected void renderRoundedBlockForElement(Player var1, HoverElement var2) {
      EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
      if (var3 != null && var2 != null) {
         TextAlignment var4 = var2.hudAlignment == null ? TextAlignment.CENTER : var2.hudAlignment;
         double var5 = this.applyTextAlignmentOffset(
            this.toRenderX(var3, var2.baseLocation.getX()), this.toRenderSize(var3, var2.baseScale.getX()), var2.textAlignment
         );
         double var7 = this.toRenderY(var3, var2.baseLocation.getY());
         double var9 = this.toRenderSize(var3, var2.baseScale.getX());
         double var11 = this.toRenderSize(var3, var2.baseScale.getY());
         double var13 = var5 + this.toRenderSize(var3, var2.pivotOffsetX);
         double var15 = var7 + this.toRenderSize(var3, var2.pivotOffsetY);
         double var17 = this.readRoundedRadius(null, var9, var11);
         double var19 = this.toRenderSize(var3, Math.max(0.0, var2.outlineSize));
         if (var19 > 1.0E-4) {
            String var21 = var2.outlineText;
            if (var21 == null || var21.isBlank()) {
               var21 = this.applyOutlineColor(var2.text, var2.outlineColor);
            }

            this.renderRoundedOutline(
               var1,
               var2.id,
               var5,
               var7,
               var2.runtimeZ,
               var9,
               var11,
               var21,
               var2.text,
               var2.opacity,
               var17,
               var2.roundedCornerUnicode,
               var2.roundedCornerUnicodeTopLeft,
               var2.roundedCornerUnicodeTopRight,
               var2.roundedCornerUnicodeBottomRight,
               var2.roundedCornerUnicodeBottomLeft,
               var2.roundedTopLeftOffsetX,
               var2.roundedTopLeftOffsetY,
               var2.roundedTopRightOffsetX,
               var2.roundedTopRightOffsetY,
               var2.roundedBottomRightOffsetX,
               var2.roundedBottomRightOffsetY,
               var2.roundedBottomLeftOffsetX,
               var2.roundedBottomLeftOffsetY,
               var2.rotationDeg,
               var2.mirrorX,
               var2.mirrorY,
               var19,
               var13,
               var15,
               var4
            );
         } else {
            this.clearOutlineHud(var1, var2.id);
         }

         this.renderRoundedBlock(
            var1,
            var2.id,
            var5,
            var7,
            var2.runtimeZ,
            var9,
            var11,
            var2.text,
            var2.opacity,
            var17,
            var2.roundedCornerUnicode,
            var2.roundedCornerUnicodeTopLeft,
            var2.roundedCornerUnicodeTopRight,
            var2.roundedCornerUnicodeBottomRight,
            var2.roundedCornerUnicodeBottomLeft,
            var2.roundedTopLeftOffsetX,
            var2.roundedTopLeftOffsetY,
            var2.roundedTopRightOffsetX,
            var2.roundedTopRightOffsetY,
            var2.roundedBottomRightOffsetX,
            var2.roundedBottomRightOffsetY,
            var2.roundedBottomLeftOffsetX,
            var2.roundedBottomLeftOffsetY,
            var2.rotationDeg,
            var2.mirrorX,
            var2.mirrorY,
            var13,
            var15,
            var4
         );
      }
   }

   protected void renderRoundedBlock(
      Player var1,
      String var2,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      String var13,
      int var14,
      double var15,
      String var17,
      double var18,
      double var20,
      double var22,
      double var24,
      double var26,
      double var28,
      double var30,
      double var32,
      double var34,
      boolean var36,
      boolean var37,
      double var38,
      double var40
   ) {
      this.renderRoundedBlock(
         var1,
         var2,
         var3,
         var5,
         var7,
         var9,
         var11,
         var13,
         var14,
         var15,
         var17,
         var18,
         var20,
         var22,
         var24,
         var26,
         var28,
         var30,
         var32,
         var34,
         var36,
         var37,
         var38,
         var40,
         TextAlignment.CENTER
      );
   }

   protected void renderRoundedBlock(
      Player var1,
      String var2,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      String var13,
      int var14,
      double var15,
      String var17,
      double var18,
      double var20,
      double var22,
      double var24,
      double var26,
      double var28,
      double var30,
      double var32,
      double var34,
      boolean var36,
      boolean var37,
      double var38,
      double var40,
      TextAlignment var42
   ) {
      this.renderRoundedBlock(
         var1,
         var2,
         var3,
         var5,
         var7,
         var9,
         var11,
         var13,
         var14,
         var15,
         var17,
         "",
         "",
         "",
         "",
         var18,
         var20,
         var22,
         var24,
         var26,
         var28,
         var30,
         var32,
         var34,
         var36,
         var37,
         var38,
         var40,
         var42
      );
   }

   protected void renderRoundedBlock(
      Player var1,
      String var2,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      String var13,
      int var14,
      double var15,
      String var17,
      String var18,
      String var19,
      String var20,
      String var21,
      double var22,
      double var24,
      double var26,
      double var28,
      double var30,
      double var32,
      double var34,
      double var36,
      double var38,
      boolean var40,
      boolean var41,
      double var42,
      double var44
   ) {
      this.renderRoundedBlock(
         var1,
         var2,
         var3,
         var5,
         var7,
         var9,
         var11,
         var13,
         var14,
         var15,
         var17,
         var18,
         var19,
         var20,
         var21,
         var22,
         var24,
         var26,
         var28,
         var30,
         var32,
         var34,
         var36,
         var38,
         var40,
         var41,
         var42,
         var44,
         TextAlignment.CENTER
      );
   }

   protected void renderRoundedBlock(
      Player var1,
      String var2,
      double var3,
      double var5,
      double var7,
      double var9,
      double var11,
      String var13,
      int var14,
      double var15,
      String var17,
      String var18,
      String var19,
      String var20,
      String var21,
      double var22,
      double var24,
      double var26,
      double var28,
      double var30,
      double var32,
      double var34,
      double var36,
      double var38,
      boolean var40,
      boolean var41,
      double var42,
      double var44,
      TextAlignment var46
   ) {
      TextAlignment var47 = var46 == null ? TextAlignment.CENTER : var46;
      double var48 = this.resolveRoundedRadiusScale(var1, var2);
      double var50 = Math.min(Math.max(0.0, var15 * var48), Math.min(var9, var11) / 2.0);
      double var52 = this.shouldApplyRotationVisualShift(var2) ? this.resolveRotationVisualXOffset(var38, var9) : 0.0;
      double var54 = this.shouldApplyRotationVisualShift(var2) ? this.resolveRotationVisualYOffset(var38, var11) : 0.0;
      if (var50 <= 0.5) {
         this.removeRoundedParts(var1, var2);
         double var120 = Math.max(1.0, var9);
         double var121 = Math.max(1.0, var11);
         double[] var122 = this.resolvePivotAwareTopLeft(var3, var5, var120, var121, var42, var44, var38);
         HudPositionCalculator.Placement var61 = this.positionCalculator.calculateBoxPlacement(var122[0] + var52, var122[1] + var54, var7, var120, var121);
         this.upsertHud(var1, var2, var61.location(), var61.scale(), var13, var14, var47, TextAlignment.CENTER, 200);
         this.applyElementTransformById(var1, var2, var38, var40, var41);
      } else {
         this.removeBaseHud(var1, var2);
         double var56 = this.normalizeRenderRotation(var38);
         double var58 = var48 > 1.0E-4 ? var11 / var48 : var11;
         double var60 = var58 > 245.0 ? 2.0 : 0.0;
         double var62 = this.shouldApplyEditorShellRoundedTopShift(var1, var2) ? 1.0 : 0.0;
         double var64 = this.shouldApplyRuntimeRoundedEdgeYOffset(var1, var2, var58) ? 1.0 : 0.0;
         double var66 = Math.max(0.0, var9 - 2.0 * var50);
         double var68 = var5 + var50 + var60;
         double var70 = var5 + var11 - var50 + var60;
         double var72 = Math.max(0.0, var70 - var68);
         double var74 = Double.isFinite(var42) ? var42 : var3 + var9 / 2.0;
         double var76 = Double.isFinite(var44) ? var44 : var5 + var11 / 2.0;
         double[] var78 = this.rotateTopLeftAroundPivot(var3 + var50, var68, var66, var72, var74, var76, var56);
         double[] var79 = this.rotateTopLeftAroundPivot(var3 + var50, var5 + var60 + var62 + var64, var66, var50, var74, var76, var56);
         double[] var80 = this.rotateTopLeftAroundPivot(var3 + var50, var70 + var64, var66, var50, var74, var76, var56);
         double[] var81 = this.rotateTopLeftAroundPivot(var3, var68, var50, var72, var74, var76, var56);
         double[] var82 = this.rotateTopLeftAroundPivot(var3 + var9 - var50, var68, var50, var72, var74, var76, var56);
         boolean var83 = Math.abs(var56) < 1.0E-4;
         this.upsertRoundedPart(
            var1, var2 + "_r_core", var78[0] + var52, var78[1] + var54, var66, var14 >= 255 ? var72 + 1.0 : var72, var7, var13, var14, var83, var47
         );
         this.upsertRoundedPart(var1, var2 + "_r_top", var79[0] + var52, var79[1] + var54, var66, var50, var7, var13, var14, var83, var47);
         this.upsertRoundedPart(var1, var2 + "_r_bottom", var80[0] + var52, var80[1] + var54, var66, var50, var7, var13, var14, var83, var47);
         this.upsertRoundedPart(var1, var2 + "_r_left", var81[0] + var52, var81[1] + var54, var50, var72, var7, var13, var14, var83, var47);
         this.upsertRoundedPart(var1, var2 + "_r_right", var82[0] + var52, var82[1] + var54, var50, var72, var7, var13, var14, var83, var47);
         this.applyElementTransformById(var1, var2 + "_r_core", var38, var40, var41);
         this.applyElementTransformById(var1, var2 + "_r_top", var38, var40, var41);
         this.applyElementTransformById(var1, var2 + "_r_bottom", var38, var40, var41);
         this.applyElementTransformById(var1, var2 + "_r_left", var38, var40, var41);
         this.applyElementTransformById(var1, var2 + "_r_right", var38, var40, var41);
         double var84 = var50 * 0.5;
         boolean var86 = !this.firstNonBlank(new String[]{var18, var19, var20, var21}).isBlank();
         boolean var87 = this.isPreviewContentRuntimeId(var2);
         double var88;
         double var90;
         double var92;
         double var94;
         double var96;
         double var98;
         double var100;
         double var102;
         if (var86) {
            var88 = var3 + var22;
            var90 = var5 + var60 + var62 + var64 + var24;
            var92 = var3 + var9 - var50 + var26;
            var94 = var5 + var60 + var62 + var64 + var28;
            var96 = var3 + var9 - var50 + var30;
            var98 = var70 + var64 + var32;
            var100 = var3 + var34;
            var102 = var70 + var64 + var36;
         } else {
            var88 = var3 + var22;
            var90 = var5 + var60 + var62 + var64 + var24;
            var92 = var3 + var9 - var50 - var84 - 1.0 + var26;
            var94 = var5 + var60 + var62 + var64 - var84 - (var87 ? 2.0 : 1.0) + var28;
            var96 = var3 + var9 - var50 + var30;
            var98 = var70 + var64 - var50 - (var87 ? 3.0 : 2.0) + var32;
            var100 = var3 + var84 + (var87 ? 2.0 : 1.0) + var34;
            var102 = var70 + var64 - var84 - 1.0 + var36;
            if (var87) {
               var92 += this.resolvePreviewCornerZoomCompensation(var48, -0.5, 1.0);
               var94 += this.resolvePreviewCornerZoomCompensation(var48, -1.0, 2.0);
               var96 += this.resolvePreviewCornerZoomCompensation(var48, 0.25, 0.0);
               var98 += this.resolvePreviewCornerZoomCompensation(var48, -1.0, 1.0);
               var100 += this.resolvePreviewCornerZoomCompensation(var48, 0.5, -1.0);
               var102 += this.resolvePreviewCornerZoomCompensation(var48, -0.5, -1.0);
            }
         }

         double[] var104 = this.rotateTopLeftAroundPivot(var88, var90, var50, var50, var74, var76, var56);
         double[] var105 = this.rotateTopLeftAroundPivot(var92, var94, var50, var50, var74, var76, var56);
         double[] var106 = this.rotateTopLeftAroundPivot(var96, var98, var50, var50, var74, var76, var56);
         double[] var107 = this.rotateTopLeftAroundPivot(var100, var102, var50, var50, var74, var76, var56);
         this.logRoundedCornerPosition(var2 + "_r_tl", var104[0], var104[1]);
         this.logRoundedCornerPosition(var2 + "_r_tr", var105[0], var105[1]);
         this.logRoundedCornerPosition(var2 + "_r_br", var106[0], var106[1]);
         this.logRoundedCornerPosition(var2 + "_r_bl", var107[0], var107[1]);
         String var108 = this.roundedCornerTextFromBody(var13, this.firstNonBlank(new String[]{var18, var17}));
         String var109 = this.roundedCornerTextFromBody(var13, this.firstNonBlank(new String[]{var19, var17}));
         String var110 = this.roundedCornerTextFromBody(var13, this.firstNonBlank(new String[]{var20, var17}));
         String var111 = this.roundedCornerTextFromBody(var13, this.firstNonBlank(new String[]{var21, var17}));
         double var112;
         double var114;
         double var116;
         double var118;
         if (var86) {
            var112 = 0.0;
            var114 = 0.0;
            var116 = 0.0;
            var118 = 0.0;
         } else {
            var112 = 0.0 + var56;
            var114 = -90.0 + var56;
            var116 = 180.0 + var56;
            var118 = 90.0 + var56;
         }

         this.upsertRoundedCorner(var1, var2 + "_r_tl", var104[0] + var52, var104[1] + var54, var50, var7, var14, var112, var108, var83, var47);
         this.upsertRoundedCorner(var1, var2 + "_r_tr", var105[0] + var52, var105[1] + var54, var50, var7, var14, var114, var109, var83, var47);
         this.upsertRoundedCorner(var1, var2 + "_r_br", var106[0] + var52, var106[1] + var54, var50, var7, var14, var116, var110, var83, var47);
         this.upsertRoundedCorner(var1, var2 + "_r_bl", var107[0] + var52, var107[1] + var54, var50, var7, var14, var118, var111, var83, var47);
         this.applyHudMirrorById(var1, var2 + "_r_tl", var40, var41);
         this.applyHudMirrorById(var1, var2 + "_r_tr", var40, var41);
         this.applyHudMirrorById(var1, var2 + "_r_br", var40, var41);
         this.applyHudMirrorById(var1, var2 + "_r_bl", var40, var41);
      }
   }

   protected boolean shouldApplyRotationVisualShift(String var1) {
      return var1 == null || var1.isBlank() ? false : !var1.endsWith("_outline");
   }

   protected boolean shouldApplyEditorShellRoundedTopShift(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank() && var2.startsWith("editor_shell_")) {
         EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
         return var3 != null && var3.editMode && var3.previewMode;
      } else {
         return false;
      }
   }

   protected boolean isPreviewContentRuntimeId(String var1) {
      return var1 != null && !var1.isBlank() ? var1.startsWith("preview_content_") : false;
   }

   protected boolean shouldApplyRuntimeRoundedEdgeYOffset(Player var1, String var2, double var3) {
      if (!Double.isFinite(var3) || var3 < 195.0) {
         return false;
      } else if (var1 != null && !this.isPreviewContentRuntimeId(var2)) {
         EditorSession var5 = this.editorSessions.get(var1.getUniqueId());
         return var5 != null && !var5.editMode;
      } else {
         return false;
      }
   }

   protected double resolveRoundedRadiusScale(Player var1, String var2) {
      if (var1 != null && this.isPreviewContentRuntimeId(var2)) {
         EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
         if (var3 != null && var3.previewMode && var3.previewViewport != null) {
            double var4 = this.getPreviewScale(var3);
            return Double.isFinite(var4) && !(var4 <= 0.0) ? var4 : 1.0;
         } else {
            return 1.0;
         }
      } else {
         return 1.0;
      }
   }

   protected double resolvePreviewCornerZoomCompensation(double var1, double var3, double var5) {
      if (!Double.isFinite(var1)) {
         return 0.0;
      } else if (var1 >= 1.0) {
         double var13 = Math.max(1.0E-4, 5.0);
         double var14 = Math.min(1.0, Math.max(0.0, (var1 - 1.0) / var13));
         return var3 * var14;
      } else {
         double var7 = 0.28;
         double var9 = Math.max(1.0E-4, 1.0 - var7);
         double var11 = Math.min(1.0, Math.max(0.0, (1.0 - var1) / var9));
         return var5 * var11;
      }
   }

   protected double resolveRotationVisualYOffset(double var1, double var3) {
      if (!Double.isFinite(var1)) {
         return 0.0;
      } else {
         double var5 = this.normalizeRotationForComparison(var1);
         double var7 = Math.abs(var5);
         if (var7 <= 1.0E-4) {
            return 0.0;
         } else {
            double var9 = Math.max(1.0, var3);
            double var11 = Math.min(1.0, var7 / 180.0);
            double var13 = -var9 * var11;
            double var15 = -var9 * 0.0158 * Math.sin(Math.toRadians(var5));
            return var13 + var15;
         }
      }
   }

   protected double resolveRotationVisualXOffset(double var1, double var3) {
      if (!Double.isFinite(var1)) {
         return 0.0;
      } else {
         double var5 = this.normalizeRotationForComparison(var1);
         if (Math.abs(var5) <= 1.0E-4) {
            return 0.0;
         } else {
            double var7 = Math.max(1.0, var3) * 0.5617;
            return -var7 * Math.sin(Math.toRadians(var5));
         }
      }
   }

   protected double[] resolveItemDisplayRotationOffset(double var1, double var3, double var5) {
      if (!Double.isFinite(var1)) {
         return new double[]{0.0, 0.0};
      } else {
         double var7 = var1 % 360.0;
         if (var7 < 0.0) {
            var7 += 360.0;
         }

         double var9 = Math.max(1.0, var3);
         double var11 = Math.max(1.0, var5);
         double var13 = 0.0;
         double var15 = 0.0;
         double var17 = var9 * 0.5;
         double var19 = var11 * 0.5;
         double var21 = 0.0;
         double var25 = -var9 * 0.5;
         double var27 = var11 * 0.5;
         if (var7 < 90.0) {
            double var33 = var7 / 90.0;
            return new double[]{this.lerp(var13, var17, var33), this.lerp(var15, var19, var33)};
         } else if (var7 < 180.0) {
            double var32 = (var7 - 90.0) / 90.0;
            return new double[]{this.lerp(var17, var21, var32), this.lerp(var19, var11, var32)};
         } else if (var7 < 270.0) {
            double var31 = (var7 - 180.0) / 90.0;
            return new double[]{this.lerp(var21, var25, var31), this.lerp(var11, var27, var31)};
         } else {
            double var29 = (var7 - 270.0) / 90.0;
            return new double[]{this.lerp(var25, var13, var29), this.lerp(var27, var15, var29)};
         }
      }
   }

   protected double[] resolveItemDisplayNonBlockRotationCompensation(double var1, double var3, double var5) {
      if (!Double.isFinite(var1)) {
         return new double[]{0.0, 0.0};
      } else {
         double var7 = var1 % 360.0;
         if (var7 < 0.0) {
            var7 += 360.0;
         }

         double var9 = Math.max(1.0, var3);
         double var11 = Math.max(1.0, var5);
         double var13 = 0.0;
         double var15 = 0.0;
         double var17 = var9 * 0.02208835341365462;
         double var19 = var11 * 0.040160642570281124;
         double var21 = var9 * -0.012048192771084338;
         double var23 = var11 * 0.060240963855421686;
         double var25 = var9 * -0.03413654618473896;
         double var27 = var11 * 0.02208835341365462;
         if (var7 < 90.0) {
            double var33 = var7 / 90.0;
            return new double[]{this.lerp(var13, var17, var33), this.lerp(var15, var19, var33)};
         } else if (var7 < 180.0) {
            double var32 = (var7 - 90.0) / 90.0;
            return new double[]{this.lerp(var17, var21, var32), this.lerp(var19, var23, var32)};
         } else if (var7 < 270.0) {
            double var31 = (var7 - 180.0) / 90.0;
            return new double[]{this.lerp(var21, var25, var31), this.lerp(var23, var27, var31)};
         } else {
            double var29 = (var7 - 270.0) / 90.0;
            return new double[]{this.lerp(var25, var13, var29), this.lerp(var27, var15, var29)};
         }
      }
   }

   protected double lerp(double var1, double var3, double var5) {
      return var1 + (var3 - var1) * var5;
   }

   protected double normalizeRotationForComparison(double var1) {
      double var3 = var1 % 360.0;
      if (var3 <= -180.0) {
         var3 += 360.0;
      }

      if (var3 > 180.0) {
         var3 -= 360.0;
      }

      return var3;
   }

   protected double[] rotateTopLeftAroundPivot(double var1, double var3, double var5, double var7, double var9, double var11, double var13) {
      if (Double.isFinite(var13) && !(Math.abs(var13) < 1.0E-4)) {
         double var15 = var1 + var5 / 2.0;
         double var17 = var3 + var7 / 2.0;
         double var19 = Math.toRadians(var13);
         double var21 = Math.cos(var19);
         double var23 = Math.sin(var19);
         double var25 = var15 - var9;
         double var27 = var17 - var11;
         double var29 = var9 + var25 * var21 - var27 * var23;
         double var31 = var11 + var25 * var23 + var27 * var21;
         return new double[]{var29 - var5 / 2.0, var31 - var7 / 2.0};
      } else {
         return new double[]{var1, var3};
      }
   }

   protected boolean upsertRoundedPart(
      Player var1, String var2, double var3, double var5, double var7, double var9, double var11, String var13, int var14, boolean var15
   ) {
      return this.upsertRoundedPart(var1, var2, var3, var5, var7, var9, var11, var13, var14, var15, TextAlignment.CENTER);
   }

   protected boolean upsertRoundedPart(
      Player var1, String var2, double var3, double var5, double var7, double var9, double var11, String var13, int var14, boolean var15, TextAlignment var16
   ) {
      if (!(var7 <= 0.5) && !(var9 <= 0.5)) {
         TextAlignment var17 = var16 == null ? TextAlignment.CENTER : var16;
         double var18 = var3;
         double var20 = var5;
         double var22 = var7;
         double var24 = var9;
         if (var15) {
            double var26 = (double)Math.round(var3);
            double var28 = (double)Math.round(var5);
            double var30 = (double)Math.round(var3 + var7);
            double var32 = (double)Math.round(var5 + var9);
            if (var30 <= var26) {
               var30 = var26 + 1.0;
            }

            if (var32 <= var28) {
               var32 = var28 + 1.0;
            }

            var18 = var26;
            var20 = var28;
            var22 = var30 - var26;
            var24 = var32 - var28;
         }

         HudPositionCalculator.Placement var34 = this.positionCalculator.calculateBoxPlacement(var18, var20, var11, var22, var24);
         return this.upsertHud(var1, var2, var34.location(), var34.scale(), var13, var14, var17, TextAlignment.CENTER, 200);
      } else {
         this.removeHudById(var1, var2);
         return true;
      }
   }

   protected void upsertRoundedCorner(
      Player var1, String var2, double var3, double var5, double var7, double var9, int var11, double var12, String var14, boolean var15
   ) {
      this.upsertRoundedCorner(var1, var2, var3, var5, var7, var9, var11, var12, var14, var15, TextAlignment.CENTER);
   }

   protected void upsertRoundedCorner(
      Player var1, String var2, double var3, double var5, double var7, double var9, int var11, double var12, String var14, boolean var15, TextAlignment var16
   ) {
      boolean var17 = this.upsertRoundedPart(var1, var2, var3, var5, var7, var7, var9, var14, var11, var15, var16);
      Entity var18 = this.hudService.getHud(var1, var2);
      if (var18 != null) {
         Double var19 = this.getRoundedRotation(var18);
         if (var17 || var19 == null || Math.abs(var19 - var12) > 1.0E-4) {
            this.hudService.rotateHud(var18, new Vector(0.0, 0.0, var12), 1, "left");
            var18.setMetadata("xqgui_rounded_rotation_z", new FixedMetadataValue(this.plugin, var12));
         }
      }
   }

   protected void logRoundedCornerPosition(String var1, double var2, double var4) {
   }

   protected boolean upsertHud(Player var1, String var2, Vector var3, Vector var4, String var5, int var6) {
      return this.upsertHud(var1, var2, var3, var4, var5, var6, TextAlignment.CENTER, 200);
   }

   protected Vector resolveItemDisplayScale(Vector var1) {
      return var1 == null
         ? null
         : new Vector(Math.max(1.0E-4, var1.getX() * 1.6666666666666667), Math.max(1.0E-4, var1.getY() * 1.6666666666666667), var1.getZ());
   }

   protected boolean upsertItemHud(Player var1, String var2, Vector var3, Vector var4, ItemStack var5, int var6) {
      return this.upsertItemHud(var1, var2, var3, var4, var5, var6, TextAlignment.CENTER);
   }

   protected boolean upsertItemHud(Player var1, String var2, Vector var3, Vector var4, ItemStack var5, int var6, TextAlignment var7) {
      if (var5 == null) {
         return false;
      } else {
         TextAlignment var8 = var7 == null ? TextAlignment.CENTER : var7;
         Vector var9 = this.resolveItemDisplayScale(var4);
         if (var9 == null) {
            return false;
         } else {
            EditorSession var10 = null;
            int var11 = 0;
            boolean var12 = false;
            if (var1 != null) {
               var10 = this.editorSessions.get(var1.getUniqueId());
               var11 = this.resolveEditorHudTransitionTicks(var10);
               var12 = var10 != null && var10.suppressHudTransitionDuringStructureDeletion;
            }

            Entity var13 = this.hudService.getHud(var1, var2);
            if (var13 instanceof ItemDisplay var14) {
               boolean var20 = false;
               if (this.hudService.setHudAligned(var14, var8)) {
                  var20 = true;
               }

               Vector var16 = this.hudService.getHudScale(var14);
               if (!this.sameVector(var16, var9)) {
                  this.hudService.setHudScale(var14, var9, var11, false);
                  var20 = true;
               }

               Vector var17 = this.hudService.getHudLocation(var14);
               if (!this.sameVector(var17, var3)) {
                  this.hudService.moveHud(var14, var3, var11, var11);
                  var20 = true;
               }

               ItemStack var18 = var14.getItemStack();
               if (var18 == null || !var18.equals(var5)) {
                  var14.setItemStack(var5.clone());
                  var20 = true;
               }

               if (this.hudService.getHudOpacity(var14) != var6) {
                  this.hudService.setOpacity(var14, var6);
                  var20 = true;
               }

               return var20;
            } else {
               if (var13 != null) {
                  this.removeBaseHud(var1, var2);
               }

               this.hudService.addHud(var1, var2, var3, var9, var5.clone(), var8, TextAlignment.CENTER, false, var6);
               Entity var15 = this.hudService.getHud(var1, var2);
               if (var15 != null && var12) {
                  this.hudService.setHudNoTransition(var15);
                  this.hudService.setHudScale(var15, var9, 0, false);
                  this.hudService.moveHud(var15, var3, 0, 0);
               }

               return true;
            }
         }
      }
   }

   protected boolean upsertHud(Player var1, String var2, Vector var3, Vector var4, String var5, int var6, TextAlignment var7) {
      return this.upsertHud(var1, var2, var3, var4, var5, var6, TextAlignment.CENTER, var7, 200);
   }

   protected boolean upsertHud(Player var1, String var2, Vector var3, Vector var4, String var5, int var6, TextAlignment var7, int var8) {
      return this.upsertHud(var1, var2, var3, var4, var5, var6, TextAlignment.CENTER, var7, var8);
   }

   protected boolean upsertHud(Player var1, String var2, Vector var3, Vector var4, String var5, int var6, TextAlignment var7, TextAlignment var8, int var9) {
      TextAlignment var10 = var7 == null ? TextAlignment.CENTER : var7;
      EditorSession var11 = null;
      int var12 = 0;
      boolean var13 = false;
      if (var1 != null) {
         var11 = this.editorSessions.get(var1.getUniqueId());
         var12 = this.resolveEditorHudTransitionTicks(var11);
         var13 = var11 != null && var11.suppressHudTransitionDuringStructureDeletion;
      }

      Entity var14 = this.hudService.getHud(var1, var2);
      if (var14 == null) {
         this.hudService.addHud(var1, var2, var3, var4, var5, var10, var8, false, var6);
         Entity var20 = this.hudService.getHud(var1, var2);
         if (var20 != null) {
            if (var13) {
               this.hudService.setHudNoTransition(var20);
               this.hudService.setHudScale(var20, var4, 0, false);
               this.hudService.moveHud(var20, var3, 0, 0);
            }

            this.hudService.setTextWrap(var20, var9);
         }

         return true;
      } else {
         boolean var15 = false;
         if (this.hudService.setHudAligned(var14, var10)) {
            var15 = true;
         }

         if (this.hudService.setTextAlignment(var14, var8)) {
            var15 = true;
         }

         if (this.hudService.setTextWrap(var14, var9)) {
            var15 = true;
         }

         Vector var16 = this.hudService.getHudScale(var14);
         if (!this.sameVector(var16, var4)) {
            this.hudService.setHudScale(var14, var4, var12, false);
            var15 = true;
         }

         Vector var17 = this.hudService.getHudLocation(var14);
         if (!this.sameVector(var17, var3)) {
            this.hudService.moveHud(var14, var3, var12, var12);
            var15 = true;
         }

         String var18 = this.hudService.getHudText(var14, null);
         if (!this.equalsNullable(var18, var5)) {
            this.hudService.setHudText(var14, var5, null);
            var15 = true;
         }

         if (this.hudService.getHudOpacity(var14) != var6) {
            this.hudService.setOpacity(var14, var6);
            var15 = true;
         }

         return var15;
      }
   }

   protected Double getRoundedRotation(Entity var1) {
      return var1 != null && var1.hasMetadata("xqgui_rounded_rotation_z")
         ? ((MetadataValue)var1.getMetadata("xqgui_rounded_rotation_z").get(0)).asDouble()
         : null;
   }

   protected void applyElementTransformById(Player var1, String var2, double var3, boolean var5, boolean var6) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Entity var7 = this.hudService.getHud(var1, var2);
         if (var7 != null) {
            this.applyElementTransform(var7, var3, var5, var6);
         }
      }
   }

   protected void applyHudMirrorById(Player var1, String var2, boolean var3, boolean var4) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Entity var5 = this.hudService.getHud(var1, var2);
         if (var5 != null) {
            this.applyHudMirror(var5, var3, var4);
         }
      }
   }

   protected void applyElementTransform(Entity var1, double var2, boolean var4, boolean var5) {
      if (var1 != null) {
         this.applyHudMirror(var1, var4, var5);
         double var6 = this.normalizeRenderRotation(var2);
         Double var8 = this.getRoundedRotation(var1);
         if (var8 == null || Math.abs(var8 - var6) > 1.0E-4) {
            this.hudService.rotateHud(var1, new Vector(0.0, 0.0, var6), 1, "left");
            var1.setMetadata("xqgui_rounded_rotation_z", new FixedMetadataValue(this.plugin, var6));
         }
      }
   }

   protected double normalizeRenderRotation(double var1) {
      return !Double.isFinite(var1) ? 0.0 : -var1;
   }

   protected void applyHudMirror(Entity var1, boolean var2, boolean var3) {
      Vector var4 = this.hudService.getHudScale(var1);
      if (var4 != null) {
         double var5 = Math.abs(var4.getX());
         double var7 = Math.abs(var4.getY());
         double var9 = Math.abs(var4.getZ());
         Vector var11 = new Vector(var2 ? -var5 : var5, var3 ? -var7 : var7, var9);
         if (!this.sameVector(var4, var11)) {
            this.hudService.setHudScale(var1, var11, 0, false);
         }
      }
   }

   protected boolean sameVector(Vector var1, Vector var2) {
      return var1 != null && var2 != null
         ? Math.abs(var1.getX() - var2.getX()) < 1.0E-4 && Math.abs(var1.getY() - var2.getY()) < 1.0E-4 && Math.abs(var1.getZ() - var2.getZ()) < 1.0E-4
         : var1 == null && var2 == null;
   }

   protected void setElementText(Player var1, HoverElement var2, String var3) {
      this.setElementText(var1, var2, var3, false);
   }

   protected void setElementText(Player var1, HoverElement var2, String var3, boolean var4) {
      if (var2 != null) {
         if ("hitbox".equals(var2.type)) {
            var2.text = this.firstNonBlank(new String[]{var3, var2.text, ""});
            this.clearOutlineHud(var1, var2.id);
            this.removeBaseHud(var1, var2.id);
            this.removeRoundedParts(var1, var2.id);
         } else {
            String var5 = this.preserveElementFontTag(var2.text, this.firstNonBlank(new String[]{var3, var2.text, ""}));
            if (!this.isRoundedType(var2.type)) {
               Entity var20 = this.hudService.getHud(var1, var2.id);
               if (var20 != null) {
                  this.hudService.setHudText(var20, var5, null, false);
               }

               if (var2.outlineSize > 1.0E-4) {
                  String var21 = this.applyOutlineColor(var5, var2.outlineColor);
                  var2.outlineText = var21;
                  this.setHudTextIfExists(var1, var2.id + "_outline", var21);
               }
            } else {
               this.setHudTextIfExists(var1, var2.id, var5);
               this.setHudTextIfExists(var1, var2.id + "_r_core", var5);
               this.setHudTextIfExists(var1, var2.id + "_r_top", var5);
               this.setHudTextIfExists(var1, var2.id + "_r_bottom", var5);
               this.setHudTextIfExists(var1, var2.id + "_r_left", var5);
               this.setHudTextIfExists(var1, var2.id + "_r_right", var5);
               String var6 = this.firstNonBlank(new String[]{var2.roundedCornerUnicodeTopLeft, var2.roundedCornerUnicode});
               String var7 = this.firstNonBlank(new String[]{var2.roundedCornerUnicodeTopRight, var2.roundedCornerUnicode});
               String var8 = this.firstNonBlank(new String[]{var2.roundedCornerUnicodeBottomLeft, var2.roundedCornerUnicode});
               String var9 = this.firstNonBlank(new String[]{var2.roundedCornerUnicodeBottomRight, var2.roundedCornerUnicode});
               String var10 = var4 ? this.withRoundedCornerBaseColor(var5, var6) : this.roundedCornerTextFromBody(var5, var6);
               String var11 = var4 ? this.withRoundedCornerBaseColor(var5, var7) : this.roundedCornerTextFromBody(var5, var7);
               String var12 = var4 ? this.withRoundedCornerBaseColor(var5, var8) : this.roundedCornerTextFromBody(var5, var8);
               String var13 = var4 ? this.withRoundedCornerBaseColor(var5, var9) : this.roundedCornerTextFromBody(var5, var9);
               this.setHudTextIfExists(var1, var2.id + "_r_tl", var10);
               this.setHudTextIfExists(var1, var2.id + "_r_tr", var11);
               this.setHudTextIfExists(var1, var2.id + "_r_bl", var12);
               this.setHudTextIfExists(var1, var2.id + "_r_br", var13);
               if (var2.outlineSize > 1.0E-4) {
                  String var14 = var2.id + "_outline";
                  String var15 = this.applyOutlineColor(var5, var2.outlineColor);
                  var2.outlineText = var15;
                  this.setHudTextIfExists(var1, var14, var15);
                  this.setHudTextIfExists(var1, var14 + "_r_core", var15);
                  this.setHudTextIfExists(var1, var14 + "_r_top", var15);
                  this.setHudTextIfExists(var1, var14 + "_r_bottom", var15);
                  this.setHudTextIfExists(var1, var14 + "_r_left", var15);
                  this.setHudTextIfExists(var1, var14 + "_r_right", var15);
                  String var16 = var4 ? this.withRoundedCornerBaseColor(var15, var6) : this.roundedCornerTextFromBody(var15, var6);
                  String var17 = var4 ? this.withRoundedCornerBaseColor(var15, var7) : this.roundedCornerTextFromBody(var15, var7);
                  String var18 = var4 ? this.withRoundedCornerBaseColor(var15, var8) : this.roundedCornerTextFromBody(var15, var8);
                  String var19 = var4 ? this.withRoundedCornerBaseColor(var15, var9) : this.roundedCornerTextFromBody(var15, var9);
                  this.setHudTextIfExists(var1, var14 + "_r_tl", var16);
                  this.setHudTextIfExists(var1, var14 + "_r_tr", var17);
                  this.setHudTextIfExists(var1, var14 + "_r_bl", var18);
                  this.setHudTextIfExists(var1, var14 + "_r_br", var19);
               }
            }
         }
      }
   }

   private String extractGlyphAtRC(String var1, int var2, int var3) {
      String var4 = this.firstNonBlank(new String[]{var1}).replace("\r\n", "\n").replace('\r', '\n');
      if (var4.isBlank()) {
         return null;
      } else {
         String[] var5 = var4.split("\n", -1);
         ArrayList var6 = new ArrayList();
         boolean var7 = false;
         ArrayList var8 = new ArrayList();

         for (Object var12_raw : var5) {
            String var12 = var12_raw != null ? var12_raw.toString() : null;
            if (var12 != null && !var12.isEmpty()) {
               boolean var13 = false;
               int var14 = 0;

               while (var14 < var12.length()) {
                  int var15 = var12.codePointAt(var14);
                  if (!new String(Character.toChars(var15)).isBlank()) {
                     var13 = true;
                     break;
                  }

                  var14 += Character.charCount(var15);
               }

               if (var13) {
                  var6.addAll(var8);
                  var8.clear();
                  var6.add(var12);
                  var7 = true;
               } else if (var7) {
                  var8.add(var12);
               }
            }
         }

         if (var2 >= var6.size()) {
            return null;
         } else {
            String var16 = (String)var6.get(var2);
            int var17 = 0;
            int var18 = 0;

            while (var18 < var16.length()) {
               int var19 = var16.codePointAt(var18);
               if (var17 == var3) {
                  String var20 = new String(Character.toChars(var19));
                  return var20.isBlank() ? null : var20;
               }

               var17++;
               var18 += Character.charCount(var19);
            }

            return null;
         }
      }
   }

   protected String preserveElementFontTag(String var1, String var2) {
      String var3 = this.firstNonBlank(new String[]{var2, ""});
      if (!var3.isBlank() && !var3.contains("<font:")) {
         String var4 = this.firstNonBlank(new String[]{var1});
         if (var4.isBlank()) {
            return var3;
         } else {
            Matcher var5 = FONT_OPEN_TAG_PATTERN.matcher(var4);
            if (!var5.find()) {
               return var3;
            } else {
               String var6 = var5.group();
               boolean var7 = var4.toLowerCase(Locale.ROOT).contains("</font>");
               return var7 ? var6 + var3 + "</font>" : var6 + var3;
            }
         }
      } else {
         return var3;
      }
   }

   protected void removeRoundedParts(Player var1, String var2) {
      this.removeHudById(var1, var2 + "_r_core");
      this.removeHudById(var1, var2 + "_r_top");
      this.removeHudById(var1, var2 + "_r_bottom");
      this.removeHudById(var1, var2 + "_r_left");
      this.removeHudById(var1, var2 + "_r_right");
      this.removeHudById(var1, var2 + "_r_tl");
      this.removeHudById(var1, var2 + "_r_tr");
      this.removeHudById(var1, var2 + "_r_bl");
      this.removeHudById(var1, var2 + "_r_br");
   }

   protected void removeBaseHud(Player var1, String var2) {
      this.removeHudById(var1, var2);
   }

   protected void removeHudById(Player var1, String var2) {
      Entity var3 = this.hudService.getHud(var1, var2);
      if (var3 != null) {
         this.hudService.removeHudX(Collections.singletonList(var3), false);
      }

      var1.removeMetadata("hud_" + var2, this.plugin);
   }

   protected void setHudTextIfExists(Player var1, String var2, String var3) {
      Entity var4 = this.hudService.getHud(var1, var2);
      if (var4 != null) {
         this.hudService.setHudText(var4, var3, null, false);
      }
   }

   protected void removeRenderedElementHud(Player var1, HoverElement var2) {
      if (var2 != null) {
         this.clearOutlineHud(var1, var2.id);
         if ("hitbox".equals(var2.type)) {
            this.removeBaseHud(var1, var2.id);
            this.removeRoundedParts(var1, var2.id);
         } else if (this.isRoundedType(var2.type)) {
            this.removeBaseHud(var1, var2.id);
            this.removeRoundedParts(var1, var2.id);
         } else {
            this.removeBaseHud(var1, var2.id);
         }
      }
   }

   protected static record RuntimeNumericPlaceholderConstraints(String placeholderId, double minValue, double maxValue, double defaultValue) {
   }
}
