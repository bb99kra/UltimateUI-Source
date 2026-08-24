package dev.xqedii.ultimateUI.service.gui.editor;

import java.util.Set;
import java.util.Map;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.gui.model.EditorTool;
import dev.xqedii.ultimateUI.service.gui.render.GuiServiceRenderSupport;
import dev.xqedii.ultimateUI.service.hud.HudPositionCalculator;
import dev.xqedii.ultimateUI.service.hud.HudService;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay.TextAlignment;

public abstract class GuiServiceEditorSidebarSupport extends GuiServiceRenderSupport {
   private static final double EDITOR_TOOL_BG_LAYER = 9316.0;
   private static final double EDITOR_TOOL_ICON_LAYER = 9317.0;
   private static final String EDITOR_TOOL_ID_PREFIX = "tool_";
   private static final String EDITOR_TOOL_HITBOX_SUFFIX = "_hitbox";
   private static final String EDITOR_TOOL_BG_SUFFIX = "_bg";
   private static final String EDITOR_TOOL_ICON_SUFFIX = "_icon";
   private static final String EDITOR_TOOL_ACTIVE_BG_UNICODE = "\ue5f5";
   private static final String EDITOR_TOOL_INACTIVE_BG_UNICODE = "\ue5f6";

   protected GuiServiceEditorSidebarSupport(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   @Override
   protected boolean isEditorToolShellBlockId(String var1) {
      return this.resolveEditorToolFromBlockId(var1) != null;
   }

   protected void renderEditorToolSidebar(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         Map<EditorTool, GuiServiceEditorSidebarSupport.EditorToolShellDefinition> var3 = this.collectEditorToolShellDefinitions(var2);
         this.clearMissingEditorToolButtons(var1, var3);

         for (GuiServiceEditorSidebarSupport.EditorToolShellDefinition var5 : var3.values()) {
            if (var5.background != null || var5.icon != null) {
               this.renderEditorToolButton(var1, var5, var2.activeTool == var5.tool);
            }
         }
      }
   }

   protected EditorTool findHoveredToolbarTool(EditorSession var1, double var2, double var4) {
      String var6 = this.findHoveredToolbarHitboxId(var1, var2, var4);
      return var6 != null && !var6.isBlank() ? this.resolveEditorToolFromHitboxId(var6) : null;
   }

   protected String findHoveredToolbarHitboxId(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.editMode && var1.previewMode && var1.shellBlocks != null && !var1.shellBlocks.isEmpty()) {
         this.ensureSidebarShellBlockCache(var1);
         String var6 = null;
         double var7 = Double.NEGATIVE_INFINITY;

         for (Entry var10 : var1.shellSectionCache.entrySet()) {
            if (var10 != null && var10.getValue() != null) {
               ConfigurationSection var11 = (ConfigurationSection)var10.getValue();
               String var12 = this.firstNonBlank(new String[]{var11.getString("id")});
               if (this.isToolHitboxBlockId(var12)) {
                  double var13 = this.readDouble(var11, "position.x", "x", 0.0);
                  double var15 = this.readDouble(var11, "position.y", "y", 0.0);
                  double var17 = Math.max(1.0, Math.abs(this.readDouble(var11, "size.width", "width", this.readDouble(var11, "scale.width", "width", 1.0))));
                  double var19 = Math.max(1.0, Math.abs(this.readDouble(var11, "size.height", "height", this.readDouble(var11, "scale.height", "height", 1.0))));
                  if (this.isInsideEditorToolButton(var2, var4, var13, var15, var17, var19)) {
                     double var21 = this.readDouble(var11, "layer", "layer", 9316.0);
                     if (var21 >= var7) {
                        var6 = var12;
                        var7 = var21;
                     }
                  }
               }
            }
         }

         return var6;
      } else {
         return null;
      }
   }

   protected boolean isToolHitboxBlockId(String var1) {
      if (var1 != null && !var1.isBlank()) {
         String var2 = var1.trim().toLowerCase(Locale.ROOT);
         return var2.startsWith("tool_") && var2.contains("_hitbox");
      } else {
         return false;
      }
   }

   protected void renderEditorToolButton(Player var1, GuiServiceEditorSidebarSupport.EditorToolShellDefinition var2, boolean var3) {
      if (var2.background != null) {
         ConfigurationSection var4 = var2.background;
         String var5 = this.firstNonBlank(new String[]{var4.getString("id"), this.editorToolBackgroundId(var2.tool)});
         String var6 = this.firstNonBlank(
            new String[]{
               var4.getString("editorInactiveUnicode"), var4.getString("inactiveUnicode"), var4.getString("text"), var4.getString("unicode"), "\ue5f6"
            }
         );
         String var7 = this.firstNonBlank(new String[]{var4.getString("editorActiveUnicode"), var4.getString("activeUnicode"), "\ue5f5"});
         this.renderEditorToolTextSection(var1, var4, var5, var3 ? var7 : var6, 9316.0, 64.0, 64.0);
      } else {
         this.clearEditorToolBackground(var1, this.editorToolBackgroundId(var2.tool));
      }

      this.renderEditorToolIcon(var1, var2, var3);
   }

   protected void renderEditorToolIcon(Player var1, GuiServiceEditorSidebarSupport.EditorToolShellDefinition var2, boolean var3) {
      if (var2.icon == null) {
         this.removeHudById(var1, "editor_shell_" + this.editorToolIconId(var2.tool));
      } else {
         ConfigurationSection var4 = var2.icon;
         String var5 = this.firstNonBlank(new String[]{var4.getString("id"), this.editorToolIconId(var2.tool)});
         String var6 = this.firstNonBlank(
            new String[]{var4.getString("editorInactiveUnicode"), var4.getString("inactiveUnicode"), var4.getString("text"), var4.getString("unicode")}
         );
         String var7 = this.firstNonBlank(
            new String[]{var4.getString("editorActiveUnicode"), var4.getString("activeUnicode"), this.resolveActiveToolIconUnicode(var6)}
         );
         this.renderEditorToolTextSection(var1, var4, var5, var3 ? var7 : var6, 9317.0, 64.0, 64.0);
      }
   }

   protected void renderEditorToolTextSection(Player var1, ConfigurationSection var2, String var3, String var4, double var5, double var7, double var9) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var4 != null && !var4.isBlank()) {
         String var11 = this.firstNonBlank(new String[]{var2.getString("type"), "text"}).toLowerCase(Locale.ROOT);
         double var12 = this.readDouble(var2, "position.x", "x", 0.0);
         double var14 = this.readDouble(var2, "position.y", "y", 0.0);
         double var16 = this.readDouble(var2, "layer", "layer", var5);
         double var18 = this.readDouble(var2, "size.width", "width", var7);
         double var20 = this.readDouble(var2, "size.height", "height", var9);
         boolean var22 = var18 < 0.0;
         boolean var23 = var20 < 0.0;
         double var24 = Math.max(1.0, Math.abs(var18));
         double var26 = Math.max(1.0, Math.abs(var20));
         int var28 = this.readOpacity(var2, 255);
         TextAlignment var29 = "text".equals(var11) ? this.readTextAlignment(var2) : TextAlignment.CENTER;
         String var30 = this.firstNonBlank(new String[]{var2.getString("color"), var2.getString("style.color"), "ffffff"});
         String var31 = this.applyPreferredFont(this.withHexPrefix(var4, var30), var2, true);
         double var32 = "text".equals(var11) ? this.toInternalTextTopY(var14, var26) : var14;
         double var34 = "text".equals(var11) ? this.applyTextAlignmentOffset(var12, var24, var29) : var12;
         HudPositionCalculator.Placement var36 = this.positionCalculator.calculateBoxPlacement(var34, var32, var16, var24, var26);
         String var37 = "editor_shell_" + var3;
         this.upsertHud(var1, var37, var36.location(), var36.scale(), var31, var28, var29);
         this.applyElementTransformById(var1, var37, this.readDouble(var2, "rotation", "rotate", 0.0), var22, var23);
      }
   }

   protected String resolveActiveToolIconUnicode(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1});

      return switch (var2) {
         case "\ue5e1" -> "\ue5eb";
         case "\ue5e2" -> "\ue5ec";
         case "\ue5e3" -> "\ue5ed";
         case "\ue5e4" -> "\ue5ee";
         case "\ue5e5" -> "\ue5ef";
         case "\ue5e6" -> "\ue5f0";
         case "\ue5e8" -> "\ue5f2";
         case "\ue5e9" -> "\ue5f3";
         case "\ue5ea" -> "\ue5f4";
         default -> this.firstNonBlank(new String[]{var1});
      };
   }

   protected Map<EditorTool, GuiServiceEditorSidebarSupport.EditorToolShellDefinition> collectEditorToolShellDefinitions(EditorSession var1) {
      LinkedHashMap<EditorTool, GuiServiceEditorSidebarSupport.EditorToolShellDefinition> var2 = new LinkedHashMap<>();
      if (var1 != null && var1.shellBlocks != null) {
         this.ensureSidebarShellBlockCache(var1);

         for (java.util.Map.Entry var4 : (Set<java.util.Map.Entry>)(Set)var1.shellSectionCache.entrySet()) {
            if (var4 != null && var4.getValue() != null) {
               ConfigurationSection var5 = (ConfigurationSection)var4.getValue();
               String var6 = this.firstNonBlank(new String[]{var5.getString("id")});
               EditorTool var7 = this.resolveEditorToolFromBlockId(var6);
               if (var7 != null) {
                  GuiServiceEditorSidebarSupport.EditorToolShellDefinition var8 = var2.computeIfAbsent(
                     var7, var0 -> new GuiServiceEditorSidebarSupport.EditorToolShellDefinition((EditorTool)var0)
                  );
                  String var9 = var6.toLowerCase(Locale.ROOT);
                  if (var9.contains("_hitbox")) {
                     var8.hitbox = var5;
                  } else if (var9.endsWith("_bg")) {
                     var8.background = var5;
                  } else if (var9.endsWith("_icon")) {
                     var8.icon = var5;
                  }
               }
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   protected void ensureSidebarShellBlockCache(EditorSession var1) {
      if (var1 != null && !var1.shellCacheBuilt) {
         var1.shellSectionCache.clear();
         var1.shellBlockMapCache.clear();
         var1.shellStaticRectCache.clear();
         var1.sidebarPanelVisibilityStates.clear();
         if (var1.shellBlocks != null && !var1.shellBlocks.isEmpty()) {
            HashMap var2 = new HashMap();
            int var3 = 0;

            for (Map var5 : var1.shellBlocks) {
               var3++;
               ConfigurationSection var6 = this.mapToSection(var5);
               if (var6 != null) {
                  String var7 = this.resolveElementId(var6, var3, var2);
                  if (!var7.isBlank() && !var1.shellSectionCache.containsKey(var7)) {
                     var1.shellSectionCache.put(var7, var6);
                     var1.shellBlockMapCache.put(var7, var5);
                     double var8 = this.readDouble(var6, "position.x", "x", 0.0);
                     double var10 = this.readDouble(var6, "position.y", "y", 0.0);
                     double var12 = Math.max(1.0, Math.abs(this.readDouble(var6, "size.width", "width", this.readDouble(var6, "scale.width", "width", 1.0))));
                     double var14 = Math.max(
                        1.0, Math.abs(this.readDouble(var6, "size.height", "height", this.readDouble(var6, "scale.height", "height", 1.0)))
                     );
                     var1.shellStaticRectCache.put(var7, new EditorRect(var8, var10, var12, var14));
                  }
               }
            }

            var1.shellCacheBuilt = true;
         } else {
            var1.shellCacheBuilt = true;
         }
      }
   }

   protected void clearMissingEditorToolButtons(Player var1, Map<EditorTool, GuiServiceEditorSidebarSupport.EditorToolShellDefinition> var2) {
      for (EditorTool var6 : EditorTool.values()) {
         GuiServiceEditorSidebarSupport.EditorToolShellDefinition var7 = (GuiServiceEditorSidebarSupport.EditorToolShellDefinition)var2.get(var6);
         if (var7 == null || var7.background == null) {
            this.clearEditorToolBackground(var1, this.editorToolBackgroundId(var6));
         }

         if (var7 == null || var7.icon == null) {
            this.removeHudById(var1, "editor_shell_" + this.editorToolIconId(var6));
         }
      }
   }

   protected void clearEditorToolBackground(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = "editor_shell_" + var2;
         this.removeBaseHud(var1, var3);
         this.removeRoundedParts(var1, var3);
         this.clearOutlineHud(var1, var3);
      }
   }

   protected EditorTool resolveEditorToolFromHitboxId(String var1) {
      if (var1 != null && !var1.isBlank()) {
         String var2 = var1.trim().toLowerCase(Locale.ROOT);
         if (var2.startsWith("tool_") && var2.contains("_hitbox")) {
            int var3 = var2.indexOf("_hitbox");
            if (var3 <= "tool_".length()) {
               return null;
            } else {
               String var4 = var2.substring("tool_".length(), var3);
               if (var4.isBlank()) {
                  return null;
               } else {
                  try {
                     return EditorTool.valueOf(var4.replace('-', '_').toUpperCase(Locale.ROOT));
                  } catch (IllegalArgumentException var6) {
                     return null;
                  }
               }
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   protected EditorTool resolveEditorToolFromBlockId(String var1) {
      if (var1 != null && !var1.isBlank()) {
         EditorTool var2 = this.resolveEditorToolFromHitboxId(var1);
         if (var2 != null) {
            return var2;
         } else {
            String var3 = var1.trim().toLowerCase(Locale.ROOT);
            if (!var3.startsWith("tool_")) {
               return null;
            } else {
               String var4;
               if (var3.endsWith("_bg")) {
                  var4 = var3.substring("tool_".length(), var3.length() - "_bg".length());
               } else {
                  if (!var3.endsWith("_icon")) {
                     return null;
                  }

                  var4 = var3.substring("tool_".length(), var3.length() - "_icon".length());
               }

               if (var4.isBlank()) {
                  return null;
               } else {
                  try {
                     return EditorTool.valueOf(var4.replace('-', '_').toUpperCase(Locale.ROOT));
                  } catch (IllegalArgumentException var6) {
                     return null;
                  }
               }
            }
         }
      } else {
         return null;
      }
   }

   protected String editorToolBaseId(EditorTool var1) {
      return "tool_" + var1.name().toLowerCase(Locale.ROOT);
   }

   protected String editorToolBackgroundId(EditorTool var1) {
      return this.editorToolBaseId(var1) + "_bg";
   }

   protected String editorToolIconId(EditorTool var1) {
      return this.editorToolBaseId(var1) + "_icon";
   }

   protected String editorToolHitboxId(EditorTool var1) {
      return this.editorToolBaseId(var1) + "_hitbox";
   }

   private boolean isInsideEditorToolButton(double var1, double var3, double var5, double var7, double var9, double var11) {
      return var1 >= var5 && var1 <= var5 + var9 && var3 >= var7 && var3 <= var7 + var11;
   }

   protected static final class EditorToolShellDefinition {
      private final EditorTool tool;
      private ConfigurationSection hitbox;
      private ConfigurationSection background;
      private ConfigurationSection icon;

      private EditorToolShellDefinition(EditorTool var1) {
         this.tool = var1;
      }
   }
}
