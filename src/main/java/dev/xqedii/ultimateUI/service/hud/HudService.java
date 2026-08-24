package dev.xqedii.ultimateUI.service.hud;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.util.PlatformCompat;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketListenerCommon;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.entity.data.EntityData;
import com.github.retrooper.packetevents.protocol.entity.data.EntityDataTypes;
import com.github.retrooper.packetevents.protocol.entity.type.EntityTypes;
import com.github.retrooper.packetevents.protocol.packettype.PacketType.Play.Server;
import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import com.github.retrooper.packetevents.util.Quaternion4f;
import com.github.retrooper.packetevents.wrapper.PacketWrapper;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerDestroyEntities;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerEntityMetadata;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSetPassengers;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSpawnEntity;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.Display.Brightness;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class HudService {
   private static final String HUD_PREFIX = "hud_";
   private static final String HUD_OWNER_META = "hud_owner";
   private static final String TEXT_LAYOUT_PREFIX = " ".repeat(200) + "\n";
   private static final String TEXT_ALIGNMENT_META = "text_alignment";
   private static final String VIRTUAL_TEXT_META = "virtual_text_hud";
   private static final String NO_TRANSITION_META = "hud_no_transition";
   private static final MiniMessage MM = MiniMessage.miniMessage();
   private static final GsonComponentSerializer GSON_COMPONENT = GsonComponentSerializer.gson();
   private static final Pattern INLINE_HEX_GRADIENT_PATTERN = Pattern.compile("<#([0-9a-fA-F]{6})>(.*?)</#([0-9a-fA-F]{6})>", 32);
   private static final int META_DISPLAY_INTERPOLATION_DELAY = 8;
   private static final int META_DISPLAY_INTERPOLATION_DURATION = 9;
   private static final int META_DISPLAY_TRANSFORMATION = 10;
   private static final int META_DISPLAY_TRANSLATION = 11;
   private static final int META_DISPLAY_SCALE = 12;
   private static final int META_DISPLAY_LEFT_ROTATION = 13;
   private static final int META_DISPLAY_RIGHT_ROTATION = 14;
   private static final int META_DISPLAY_BRIGHTNESS = 16;
   private static final int META_TEXT_DISPLAY_TEXT = 23;
   private static final int META_TEXT_DISPLAY_LINE_WIDTH = 24;
   private static final int META_TEXT_DISPLAY_BACKGROUND = 25;
   private static final int META_TEXT_DISPLAY_OPACITY = 26;
   private static final int META_TEXT_DISPLAY_FLAGS = 27;
   private static final int DISPLAY_BRIGHTNESS_FULL = 15728880;
   private static final int HUD_BRIGHTNESS_LEVEL = 15;
   private static final Brightness HUD_FULL_BRIGHTNESS = new Brightness(15, 15);
   private static final int DEFAULT_TEXT_WRAP_LINE_WIDTH = 200;
   private static final int TEXT_BACKGROUND_DEFAULT = 1073741824;
   private static final byte TEXT_FLAG_DEFAULT_BACKGROUND = 4;
   private static final byte TEXT_FLAG_ALIGN_LEFT = 8;
   private static final byte TEXT_FLAG_ALIGN_RIGHT = 16;
   private static final Method DISPLAY_SET_TELEPORT_DURATION_METHOD = resolveDisplaySetTeleportDurationMethod();
   private static final double LEGACY_1_21_TEXT_BASELINE_SHIFT_PER_HEIGHT = 0.015025041736227046;
   private final UltimateUI plugin;
   private final HudPositionCalculator positionCalculator = new HudPositionCalculator();
   private final AtomicInteger virtualEntityIds = new AtomicInteger(2000000000);
   private final AtomicInteger virtualHandleEntityIds = new AtomicInteger(-1);
   private final Map<UUID, Map<String, HudService.ClientTextHud>> virtualTextHuds = new ConcurrentHashMap<>();
   private final Map<UUID, HudService.VirtualHudRef> virtualHudRefs = new HashMap<>();
   private final LinkedHashSet<UUID> queuedPassengerSyncPlayers = new LinkedHashSet<>();
   private final Map<UUID, int[]> externalPassengerCache = new ConcurrentHashMap<>();
   private PacketListenerCommon virtualHudPassengerGuard;

   public HudService(UltimateUI var1) {
      this.plugin = var1;
      this.registerVirtualHudPassengerGuard();
   }

   public void registerVirtualHudPassengerGuard() {
      if (this.virtualHudPassengerGuard == null) {
         this.virtualHudPassengerGuard = new PacketListenerAbstract(PacketListenerPriority.HIGHEST) {
            public void onPacketSend(PacketSendEvent var1) {
               if (var1 != null && var1.getPacketType() == Server.SET_PASSENGERS) {
                  if (var1.getPlayer() instanceof Player var3) {
                     WrapperPlayServerSetPassengers var4 = new WrapperPlayServerSetPassengers(var1);
                     if (var4.getEntityId() == var3.getEntityId()) {
                        UUID var5 = var3.getUniqueId();
                        Map<String, HudService.ClientTextHud> var6 = HudService.this.virtualTextHuds.get(var5);
                        LinkedHashSet var7 = new LinkedHashSet();
                        if (var6 != null) {
                           for (HudService.ClientTextHud var9 : var6.values()) {
                              var7.add(Integer.valueOf(var9.clientEntityId));
                           }
                        }

                        int[] var14 = var4.getPassengers();
                        LinkedHashSet var15 = new LinkedHashSet();
                        if (var14 != null) {
                           for (Object var13_raw : var14) {
                              int var13 = ((Number)var13_raw).intValue();
                              if (!var7.contains(Integer.valueOf(var13))) {
                                 var15.add(Integer.valueOf(var13));
                              }
                           }
                        }

                        HudService.this.externalPassengerCache.put(var5, HudService.toIntArray(var15));
                        if (!var7.isEmpty()) {
                           LinkedHashSet var16 = new LinkedHashSet(var15);
                           var16.addAll(var7);
                           var4.setPassengers(HudService.toIntArray(var16));
                           var1.markForReEncode(true);
                        }
                     }
                  }
               }
            }
         };

         try {
            PacketEvents.getAPI().getEventManager().registerListener(this.virtualHudPassengerGuard);
         } catch (Throwable var2) {
            this.virtualHudPassengerGuard = null;
         }
      }
   }

   public void unregisterVirtualHudPassengerGuard() {
      if (this.virtualHudPassengerGuard != null) {
         try {
            PacketEvents.getAPI().getEventManager().unregisterListener(this.virtualHudPassengerGuard);
         } catch (Throwable var2) {
         }

         this.virtualHudPassengerGuard = null;
      }
   }

   public Entity addHud(Player var1, String var2, Vector var3, Vector var4, Object var5, TextAlignment var6, TextAlignment var7, boolean var8, int var9) {
      Entity var10 = this.getHud(var1, var2);
      if (var10 != null) {
         this.removeHudX(Collections.singletonList(var10), false);
      }

      TextAlignment var11 = this.normalizeTextAlignment(var6);
      TextAlignment var12 = this.normalizeTextAlignment(var7);
      int var13 = this.resolveAlignedOffset(var11);
      if (var5 instanceof String var24) {
         int var26 = this.clampOpacity(var9);
         Vector var17 = this.normalizeHudScale(var4);
         Entity var18 = this.spawnVirtualHandle(var1, var2, var3, var17, var13, var26, var12);
         Quaternionf var19 = new Quaternionf().rotationXYZ(0.0F, (float)Math.toRadians(180.0), 0.0F);
         Quaternionf var20 = new Quaternionf(0.0F, 0.0F, 0.0F, 1.0F);
         HudService.ClientTextHud var21 = new HudService.ClientTextHud(
            this.nextVirtualEntityId(),
            UUID.randomUUID(),
            var18,
            var2,
            var3.clone(),
            var17.clone(),
            var24 == null ? "" : var24,
            var12,
            var13,
            var26,
            200,
            var19,
            var20,
            1,
            0
         );
         this.registerVirtualHud(var1, var21);
         this.spawnVirtualTextHud(var1, var21);
         this.syncClientPassengers(var1);
         if (var8) {
            Vector3f var22 = this.getScaleVector(var17);
            this.animScale(var18, (double)var22.x, (double)var22.y, (double)var22.z, false);
         }

         return var18;
      } else {
         ItemDisplay var16 = (ItemDisplay)var1.getWorld().spawn(var1.getLocation(), ItemDisplay.class);
         var16.setItemStack((ItemStack)var5);
         ItemDisplay var14 = var16;
         var1.addPassenger(var16);
         var16.setRotation(0.0F, 0.0F);
         var16.setMetadata("id", new FixedMetadataValue(this.plugin, var2));
         var16.setMetadata("hud", new FixedMetadataValue(this.plugin, true));
         var16.setMetadata("hud_owner", new FixedMetadataValue(this.plugin, var1.getUniqueId().toString()));
         var16.setMetadata("location", new FixedMetadataValue(this.plugin, var3));
         var16.setMetadata("scale", new FixedMetadataValue(this.plugin, var4));
         var16.setMetadata("opacity", new FixedMetadataValue(this.plugin, this.clampOpacity(var9)));
         var1.setMetadata("hud_" + var2, new FixedMetadataValue(this.plugin, var16));

         for (Player var25 : Bukkit.getOnlinePlayers()) {
            if (!var25.equals(var1)) {
               var25.hideEntity(this.plugin, var14);
            }
         }

         var14.setMetadata("aligned", new FixedMetadataValue(this.plugin, var13));
         this.applyFullBrightness(var14);
         Vector3f var23 = this.getScaleVector(var4);
         this.moveHud(var14, var3, 1);
         if (var8) {
            this.animScale(var14, (double)var23.x, (double)var23.y, (double)var23.z, false);
         } else {
            var14.setTransformation(
               new Transformation(
                  var14.getTransformation().getTranslation(), var14.getTransformation().getLeftRotation(), var23, var14.getTransformation().getRightRotation()
               )
            );
         }

         this.setOpacity(var14, var9);
         this.syncClientPassengers(var1);
         return var14;
      }
   }

   public void setTextWrap(TextDisplay var1, boolean var2, int var3) {
      if (var2) {
         var1.setLineWidth(Math.max(1, var3));
      } else {
         var1.setLineWidth(20000);
      }
   }

   public boolean setTextWrap(Entity var1, int var2) {
      if (var1 == null) {
         return false;
      } else {
         int var3 = var2 > 0 ? var2 : 200;
         HudService.ClientTextHud var4 = this.getVirtualHud(var1);
         if (var4 != null) {
            if (var4.lineWidth == var3) {
               return false;
            } else {
               var4.lineWidth = var3;
               Player var6 = this.getVirtualHudOwner(var1);
               if (var6 != null) {
                  this.sendVirtualHudLineWidthMetadata(var6, var4);
               }

               return true;
            }
         } else if (var1 instanceof TextDisplay var5) {
            if (var5.getLineWidth() == var3) {
               return false;
            } else {
               var5.setLineWidth(var3);
               return true;
            }
         } else {
            return false;
         }
      }
   }

   public void setOpacity(Entity var1, int var2) {
      if (var1 != null) {
         int var3 = this.clampOpacity(var2);
         HudService.ClientTextHud var4 = this.getVirtualHud(var1);
         if (var4 != null) {
            if (this.clampOpacity(var4.opacity) != var3) {
               var1.setMetadata("opacity", new FixedMetadataValue(this.plugin, var3));
               var4.opacity = var3;
               Player var8 = this.getVirtualHudOwner(var1);
               if (var8 != null) {
                  this.sendVirtualHudOpacityMetadata(var8, var4);
               }
            }
         } else {
            int var5 = 255;
            if (var1.hasMetadata("opacity")) {
               var5 = this.clampOpacity(((MetadataValue)var1.getMetadata("opacity").get(0)).asInt());
            } else if (var1 instanceof TextDisplay var6) {
               short var7 = var6.getTextOpacity();
               if (var7 < 0) {
                  var7 += 256;
               }

               var5 = this.clampOpacity(var7);
            }

            if (var5 != var3) {
               var1.setMetadata("opacity", new FixedMetadataValue(this.plugin, var3));
               if (var1 instanceof TextDisplay var9) {
                  var9.setTextOpacity((byte)var3);
               }
            }
         }
      }
   }

   public int getHudOpacity(Entity var1) {
      if (var1 != null && var1.hasMetadata("opacity")) {
         return this.clampOpacity(((MetadataValue)var1.getMetadata("opacity").get(0)).asInt());
      } else {
         HudService.ClientTextHud var2 = this.getVirtualHud(var1);
         return var2 == null ? 255 : this.clampOpacity(var2.opacity);
      }
   }

   public Vector getHudLocation(Entity var1) {
      HudService.ClientTextHud var2 = this.getVirtualHud(var1);
      if (var2 != null) {
         return var2.location.clone();
      } else {
         return var1 != null && var1.hasMetadata("location") ? ((Vector)((MetadataValue)var1.getMetadata("location").get(0)).value()).clone() : null;
      }
   }

   public Vector getHudScale(Entity var1) {
      HudService.ClientTextHud var2 = this.getVirtualHud(var1);
      if (var2 != null) {
         return var2.scale.clone();
      } else {
         return var1 != null && var1.hasMetadata("scale") ? ((Vector)((MetadataValue)var1.getMetadata("scale").get(0)).value()).clone() : null;
      }
   }

   private int clampOpacity(int var1) {
      return Math.max(5, Math.min(255, var1));
   }

   private TextAlignment normalizeTextAlignment(TextAlignment var1) {
      return var1 == null ? TextAlignment.CENTER : var1;
   }

   private void applyFullBrightness(Display var1) {
      if (var1 != null) {
         var1.setBrightness(HUD_FULL_BRIGHTNESS);
      }
   }

   public void enforceFullBrightness(Entity var1) {
      if (var1 instanceof Display var2) {
         this.applyFullBrightness(var2);
      }
   }

   private int resolveAlignedOffset(TextAlignment var1) {
      TextAlignment var2 = this.normalizeTextAlignment(var1);
      if (var2 == TextAlignment.LEFT) {
         return 10000;
      } else {
         return var2 == TextAlignment.RIGHT ? 30000 : 20000;
      }
   }

   private int resolveHudAlignedOffset(Entity var1) {
      if (var1 != null && var1.hasMetadata("aligned") && !var1.getMetadata("aligned").isEmpty()) {
         return ((MetadataValue)var1.getMetadata("aligned").get(0)).asInt();
      } else {
         HudService.ClientTextHud var2 = this.getVirtualHud(var1);
         return var2 != null ? var2.alignedOffset : 20000;
      }
   }

   private String formatHudText(String var1) {
      String var2 = var1 == null ? "" : var1;
      String var3 = this.normalizeLegacyAmpersandColors(var2.indexOf(167) >= 0 ? var2.replace('§', '&') : var2);
      return TEXT_LAYOUT_PREFIX + this.normalizeInlineHexGradient(var3);
   }

   private String normalizeLegacyAmpersandColors(String var1) {
      if (var1 != null && !var1.isBlank() && var1.indexOf(38) >= 0) {
         StringBuilder var2 = new StringBuilder(var1.length() + 24);

         for (int var3 = 0; var3 < var1.length(); var3++) {
            char var4 = var1.charAt(var3);
            if (var4 == '&' && var3 + 1 < var1.length()) {
               char var5 = Character.toLowerCase(var1.charAt(var3 + 1));
               if (var5 == '#' && var3 + 7 < var1.length()) {
                  String var6 = var1.substring(var3 + 2, var3 + 8);
                  boolean var7 = true;

                  for (int var8 = 0; var8 < 6; var8++) {
                     char var9 = var6.charAt(var8);
                     if ((var9 < '0' || var9 > '9') && (var9 < 'a' || var9 > 'f') && (var9 < 'A' || var9 > 'F')) {
                        var7 = false;
                        break;
                     }
                  }

                  if (var7) {
                     var2.append("<#").append(var6.toLowerCase(Locale.ROOT)).append('>');
                     var3 += 7;
                     continue;
                  }
               }
               String var10 = switch (var5) {
                  case '0' -> "<#000000>";
                  case '1' -> "<#0000aa>";
                  case '2' -> "<#00aa00>";
                  case '3' -> "<#00aaaa>";
                  case '4' -> "<#aa0000>";
                  case '5' -> "<#aa00aa>";
                  case '6' -> "<#ffaa00>";
                  case '7' -> "<#aaaaaa>";
                  case '8' -> "<#555555>";
                  case '9' -> "<#5555ff>";
                  default -> null;
                  case 'a' -> "<#55ff55>";
                  case 'b' -> "<#55ffff>";
                  case 'c' -> "<#ff5555>";
                  case 'd' -> "<#ff55ff>";
                  case 'e' -> "<#ffff55>";
                  case 'f' -> "<#ffffff>";
                  case 'k' -> "<obfuscated>";
                  case 'l' -> "<bold>";
                  case 'm' -> "<strikethrough>";
                  case 'n' -> "<underlined>";
                  case 'o' -> "<italic>";
                  case 'r' -> "<reset>";
               };
               if (var10 == null) {
                  var2.append(var4);
               } else {
                  var2.append(var10);
                  var3++;
               }
            } else {
               var2.append(var4);
            }
         }

         return var2.toString();
      } else {
         return var1;
      }
   }

   private String normalizeInlineHexGradient(String var1) {
      if (var1 != null && !var1.isBlank()) {
         Matcher var2 = INLINE_HEX_GRADIENT_PATTERN.matcher(var1);
         if (!var2.find()) {
            return var1;
         } else {
            var2.reset();
            StringBuffer var3 = new StringBuffer(var1.length() + 32);

            while (var2.find()) {
               String var4 = var2.group(1).toLowerCase(Locale.ROOT);
               String var5 = var2.group(2);
               String var6 = var2.group(3).toLowerCase(Locale.ROOT);
               String var7 = "<gradient:#" + var4 + ":#" + var6 + ">" + var5 + "</gradient>";
               var2.appendReplacement(var3, Matcher.quoteReplacement(var7));
            }

            var2.appendTail(var3);
            return var3.toString();
         }
      } else {
         return var1;
      }
   }

   private String stripHudTextPrefix(String var1) {
      if (var1 == null || var1.isEmpty()) {
         return "";
      } else if (var1.startsWith(TEXT_LAYOUT_PREFIX)) {
         return var1.substring(TEXT_LAYOUT_PREFIX.length());
      } else {
         int var2 = var1.indexOf(10);
         if (var2 >= 0 && var2 + 1 < var1.length()) {
            return var1.substring(var2 + 1);
         } else {
            return var2 >= 0 ? "" : var1;
         }
      }
   }

   public TextAlignment getTextAlignment(Entity var1) {
      HudService.ClientTextHud var2 = this.getVirtualHud(var1);
      if (var2 != null) {
         return var2.textAlignment;
      } else if (!(var1 instanceof TextDisplay)) {
         return TextAlignment.CENTER;
      } else if (!var1.hasMetadata("text_alignment")) {
         return TextAlignment.CENTER;
      } else {
         String var3 = ((MetadataValue)var1.getMetadata("text_alignment").get(0)).asString();

         try {
            return TextAlignment.valueOf(var3);
         } catch (IllegalArgumentException var5) {
            return TextAlignment.CENTER;
         }
      }
   }

   public boolean setHudAligned(Entity var1, TextAlignment var2) {
      if (var1 == null) {
         return false;
      } else {
         int var3 = this.resolveAlignedOffset(var2);
         int var4 = this.resolveHudAlignedOffset(var1);
         if (var4 == var3) {
            return false;
         } else {
            var1.setMetadata("aligned", new FixedMetadataValue(this.plugin, var3));
            HudService.ClientTextHud var5 = this.getVirtualHud(var1);
            if (var5 != null) {
               var5.alignedOffset = var3;
            }

            Vector var6 = this.getHudLocation(var1);
            if (var6 != null) {
               this.moveHud(var1, var6, 0, 0);
            }

            return true;
         }
      }
   }

   public boolean setTextAlignment(Entity var1, TextAlignment var2) {
      TextAlignment var3 = this.normalizeTextAlignment(var2);
      HudService.ClientTextHud var4 = this.getVirtualHud(var1);
      if (var4 != null) {
         if (var4.textAlignment == var3) {
            return false;
         } else {
            var4.textAlignment = var3;
            var1.setMetadata("text_alignment", new FixedMetadataValue(this.plugin, var3.name()));
            Player var6 = this.getVirtualHudOwner(var1);
            if (var6 != null) {
               this.sendVirtualHudFlagsMetadata(var6, var4);
            }

            return true;
         }
      } else if (var1 instanceof TextDisplay var5) {
         if (this.getTextAlignment(var1) == var3) {
            return false;
         } else {
            var5.setAlignment(var3);
            var1.setMetadata("text_alignment", new FixedMetadataValue(this.plugin, var3.name()));
            return true;
         }
      } else {
         return false;
      }
   }

   public void animScale(Entity var1, double var2, double var4, double var6, boolean var8) {
      HudService.ClientTextHud var9 = this.getVirtualHud(var1);
      if (var9 != null) {
         Double[] var15 = new Double[]{0.25, 0.45, 0.7, 0.9, 1.02, 1.08, 1.1, 1.09, 1.07, 1.05, 1.03, 1.01, 1.0};
         ArrayList var16 = new ArrayList<>(Arrays.asList(var15));
         if (var8) {
            Collections.reverse(var16);
         }

         Player var17 = this.getVirtualHudOwner(var1);
         if (var17 != null && var17.isOnline()) {
            int[] var18 = new int[]{0};
            PlatformCompat.runEntityTimer(this.plugin, var17, 0L, 1L, var11x -> {
               HudService.ClientTextHud var12x = this.getVirtualHud(var1);
               Player var13x = this.getVirtualHudOwner(var1);
               if (var12x != null && var13x != null && var13x.isOnline()) {
                  if (var18[0] >= var16.size()) {
                     if (var8) {
                        this.removeVirtualHud(var13x, var12x.id, true);
                     }

                     var11x.cancel();
                  } else {
                     double var14x = (Double)var16.get(var18[0]);
                     Vector var16x = this.normalizeHudScale(new Vector(var2 * var14x, var4 * var14x, var6 * var14x));
                     boolean var17x = this.isHudNoTransition(var1);
                     int var18x = var17x ? 0 : 1;
                     var12x.scale = var16x;
                     var12x.interpolationDuration = var18x;
                     var12x.interpolationDelay = 0;
                     var1.setMetadata("scale", new FixedMetadataValue(this.plugin, var16x));
                     this.sendVirtualHudTransformMetadata(var13x, var12x);
                     var18[0]++;
                  }
               } else {
                  var11x.cancel();
               }
            });
         }
      } else if (var1 instanceof Display var10) {
         Double[] var11 = new Double[]{0.25, 0.45, 0.7, 0.9, 1.02, 1.08, 1.1, 1.09, 1.07, 1.05, 1.03, 1.01, 1.0};
         ArrayList var12 = new ArrayList<>(Arrays.asList(var11));
         if (var8) {
            Collections.reverse(var12);
         }

         Player var13 = this.resolveHudOwner(var1);
         if (var13 != null && var13.isOnline()) {
            int[] var14 = new int[]{0};
            PlatformCompat.runEntityTimer(
               this.plugin,
               var13,
               0L,
               1L,
               var12x -> {
                  if (var14[0] >= var12.size()) {
                     if (var8) {
                        this.removeHudX(Collections.singletonList(var1), false);
                     }

                     var12x.cancel();
                  } else {
                     double var13x = (Double)var12.get(var14[0]);
                     Vector var15x = this.normalizeHudScale(new Vector(var2 * var13x, var4 * var13x, var6 * var13x));
                     boolean var16x = this.isHudNoTransition(var10);
                     int var17x = var16x ? 0 : 1;
                     var10.setTransformation(
                        new Transformation(
                           var10.getTransformation().getTranslation(),
                           var10.getTransformation().getLeftRotation(),
                           this.getScaleVector(var15x),
                           var10.getTransformation().getRightRotation()
                        )
                     );
                     var10.setMetadata("scale", new FixedMetadataValue(this.plugin, var15x));
                     this.moveHud(var10, (Vector)((MetadataValue)var1.getMetadata("location").get(0)).value(), 0);
                     var10.setInterpolationDuration(var17x);
                     var10.setInterpolationDelay(0);
                     this.setDisplayTeleportDuration(var10, this.resolveSynchronizedTeleportDurationTicks(var17x, var16x));
                     var14[0]++;
                  }
               }
            );
         } else {
            if (var8) {
               this.removeHudX(Collections.singletonList(var1), false);
            }
         }
      }
   }

   public void moveHud(Player var1, String var2, Vector var3, int var4) {
      this.moveHud(this.getHud(var1, var2), var3, var4);
   }

   public void moveHud(Entity var1, Vector var2, int var3) {
      this.moveHud(var1, var2, var3, var3);
   }

   public void moveHud(Entity var1, Vector var2, int var3, int var4) {
      this.moveHud(var1, var2, var3, var4, false);
   }

   public void moveHud(Entity var1, Vector var2, int var3, int var4, boolean var5) {
      if (var1 != null) {
         boolean var6 = var5 || this.isHudNoTransition(var1);
         int var7 = var6 ? 0 : Math.max(1, var3);
         int var8 = this.resolveSynchronizedTeleportDurationTicks(var7, var6);
         HudService.ClientTextHud var9 = this.getVirtualHud(var1);
         if (var9 != null) {
            var9.location = var2.clone();
            var9.interpolationDuration = var7;
            var9.interpolationDelay = 0;
            var1.setMetadata("location", new FixedMetadataValue(this.plugin, var2));
            Player var13 = this.getVirtualHudOwner(var1);
            if (var13 != null) {
               this.sendVirtualHudTransformMetadata(var13, var9);
            }
         } else if (var1 instanceof Display var10) {
            this.applyFullBrightness(var10);
            var10.setInterpolationDuration(var7);
            var10.setInterpolationDelay(0);
            this.setDisplayTeleportDuration(var10, var8);
            int var11 = ((MetadataValue)var1.getMetadata("aligned").get(0)).asInt();
            Vector3f var12 = this.getLocationVector(var2, (Vector)((MetadataValue)var1.getMetadata("scale").get(0)).value(), var1, var11);
            var10.setTransformation(
               new Transformation(
                  var12, var10.getTransformation().getLeftRotation(), var10.getTransformation().getScale(), var10.getTransformation().getRightRotation()
               )
            );
            var1.setMetadata("location", new FixedMetadataValue(this.plugin, var2));
         }
      }
   }

   public void setHudTransitionTicks(Entity var1, int var2, int var3) {
      if (var1 != null) {
         var1.removeMetadata("hud_no_transition", this.plugin);
         int var4 = Math.max(1, var2);
         HudService.ClientTextHud var6 = this.getVirtualHud(var1);
         if (var6 != null) {
            if (var6.interpolationDelay != 0 || var6.interpolationDuration != var4 || var4 > 1) {
               var6.interpolationDelay = 0;
               var6.interpolationDuration = var4;
               Player var8 = this.getVirtualHudOwner(var1);
               if (var8 != null) {
                  this.sendVirtualHudTransformMetadata(var8, var6);
               }
            }
         } else {
            if (var1 instanceof Display var7) {
               this.applyFullBrightness(var7);
               var7.setInterpolationDelay(0);
               var7.setInterpolationDuration(var4);
               this.setDisplayTeleportDuration(var7, var4);
            }
         }
      }
   }

   public void setHudNoTransition(Entity var1) {
      if (var1 != null) {
         var1.setMetadata("hud_no_transition", new FixedMetadataValue(this.plugin, true));
         HudService.ClientTextHud var2 = this.getVirtualHud(var1);
         if (var2 != null) {
            if (var2.interpolationDelay != 0 || var2.interpolationDuration != 0) {
               var2.interpolationDelay = 0;
               var2.interpolationDuration = 0;
               Player var4 = this.getVirtualHudOwner(var1);
               if (var4 != null) {
                  this.sendVirtualHudTransformMetadata(var4, var2);
               }
            }
         } else {
            if (var1 instanceof Display var3) {
               this.applyFullBrightness(var3);
               var3.setInterpolationDelay(0);
               var3.setInterpolationDuration(0);
               this.setDisplayTeleportDuration(var3, 0);
            }
         }
      }
   }

   private boolean isHudNoTransition(Entity var1) {
      return var1 != null && var1.hasMetadata("hud_no_transition") && ((MetadataValue)var1.getMetadata("hud_no_transition").get(0)).asBoolean();
   }

   private int resolveSynchronizedTeleportDurationTicks(int var1, boolean var2) {
      if (var2) {
         return 0;
      } else {
         return var1 <= 0 ? 0 : Math.max(1, var1);
      }
   }

   public boolean hasHudNoTransition(Entity var1) {
      return this.isHudNoTransition(var1);
   }

   public Vector3f getLocationVector(Vector var1, Vector var2, Entity var3, int var4) {
      return this.positionCalculator.toDisplayTranslation(var1, var2, var3, var4);
   }

   public Vector3f getScaleVector(Vector var1) {
      return new Vector3f((float)var1.getX(), (float)var1.getY(), (float)var1.getZ());
   }

   private Vector normalizeHudScale(Vector var1) {
      return var1 == null ? new Vector(1.0E-4, 1.0E-4, 0.0) : new Vector(Math.max(1.0E-4, var1.getX()), Math.max(1.0E-4, var1.getY()), var1.getZ());
   }

   public void moveHuds(Entity var1, List<Entity> var2, Vector var3, int var4) {
      Vector var5 = var3.clone().subtract((Vector)((MetadataValue)var1.getMetadata("location").get(0)).value());
      var5.setZ(0);
      ArrayList<Entity> var6 = new ArrayList<>(var2);
      var6.add(var1);

      for (Entity var8 : var6) {
         Vector var9 = ((Vector)((MetadataValue)var8.getMetadata("location").get(0)).value()).clone().add(var5);
         this.moveHud(var8, var9, var4);
      }
   }

   public void setHudScale(Entity var1, Vector var2, int var3, boolean var4) {
      if (var1 != null) {
         var2 = this.normalizeHudScale(var2);
         boolean var5 = this.isHudNoTransition(var1);
         int var6 = var5 ? 0 : Math.max(0, var3);
         int var7 = this.resolveSynchronizedTeleportDurationTicks(var6, var5);
         HudService.ClientTextHud var8 = this.getVirtualHud(var1);
         if (var8 != null) {
            Vector var14 = (Vector)((MetadataValue)var1.getMetadata("location").get(0)).value();
            Vector var15 = (Vector)((MetadataValue)var1.getMetadata("scale").get(0)).value();
            var1.setMetadata("scale", new FixedMetadataValue(this.plugin, var2));
            var8.scale = var2.clone();
            if (!var4) {
               this.moveHud(var1, var14, var6);
            } else {
               Vector var16 = var15.clone().subtract(var2).multiply(0.5).add(var14);
               var1.setMetadata("location", new FixedMetadataValue(this.plugin, var16));
               var8.location = var16;
               var8.interpolationDelay = 0;
               var8.interpolationDuration = var6;
            }

            Player var17 = this.getVirtualHudOwner(var1);
            if (var17 != null && var4) {
               this.sendVirtualHudTransformMetadata(var17, var8);
            }
         } else {
            Display var9 = (Display)var1;
            this.applyFullBrightness(var9);
            Vector var10 = (Vector)((MetadataValue)var1.getMetadata("location").get(0)).value();
            Vector var11 = (Vector)((MetadataValue)var1.getMetadata("scale").get(0)).value();
            var9.setTransformation(
               new Transformation(
                  var9.getTransformation().getTranslation(),
                  var9.getTransformation().getLeftRotation(),
                  this.getScaleVector(var2),
                  var9.getTransformation().getRightRotation()
               )
            );
            var1.setMetadata("scale", new FixedMetadataValue(this.plugin, var2));
            if (!var4) {
               this.moveHud(var1, var10, var6);
            } else {
               Vector var12 = var11.clone().subtract(var2).multiply(0.5).add(var10);
               var1.setMetadata("location", new FixedMetadataValue(this.plugin, var12));
               var9.setInterpolationDelay(0);
               var9.setInterpolationDuration(var6);
               this.setDisplayTeleportDuration(var9, var7);
            }
         }
      }
   }

   public String getHudText(Entity var1, Integer var2) {
      HudService.ClientTextHud var3 = this.getVirtualHud(var1);
      if (var3 != null) {
         String var7 = var3.text == null ? "" : var3.text;
         if (var2 != null) {
            String[] var8 = var7.split("\\n");
            return var2 <= var8.length ? var8[var2 - 1] : "";
         } else {
            return var7;
         }
      } else if (var1 instanceof TextDisplay var4) {
         String var5 = this.stripHudTextPrefix(var4.getText());
         if (var2 != null) {
            String[] var6 = var5.split("\n");
            return var2 <= var6.length ? var6[var2 - 1] : "";
         } else {
            return var5;
         }
      } else {
         return "";
      }
   }

   public void setHudText(Entity var1, String var2, Integer var3) {
      this.setHudText(var1, var2, var3, true);
   }

   public void setHudText(Entity var1, String var2, Integer var3, boolean var4) {
      HudService.ClientTextHud var5 = this.getVirtualHud(var1);
      if (var5 != null) {
         String var12;
         if (var3 != null) {
            String var13 = this.getHudText(var1, null);
            String[] var15 = var13.split("\\n");
            StringBuilder var16 = new StringBuilder();

            for (int var17 = 1; var17 <= Math.max(var15.length, var3); var17++) {
               if (var17 > 1) {
                  var16.append("\n");
               }

               if (var17 == var3) {
                  var16.append(var2);
               } else if (var17 <= var15.length) {
                  var16.append(var15[var17 - 1]);
               }
            }

            var12 = var16.toString();
         } else {
            var12 = var2 == null ? "" : var2;
         }

         if (Objects.equals(var5.text, var12)) {
            if (var4) {
               this.moveHud(var1, var5.location, 0);
            }
         } else {
            var5.text = var12;
            Player var14 = this.getVirtualHudOwner(var1);
            if (var14 != null) {
               this.sendVirtualHudTextMetadata(var14, var5);
            }

            if (var4) {
               this.moveHud(var1, var5.location, 0);
            }
         }
      } else if (var1 instanceof TextDisplay var6) {
         if (var3 != null) {
            String var7 = this.getHudText(var1, null);
            String[] var8 = var7.split("\n");
            StringBuilder var9 = new StringBuilder();
            boolean var10 = false;

            for (int var11 = 1; var11 <= Math.max(var8.length, var3); var11++) {
               if (var11 > 1) {
                  var9.append("\n");
               }

               if (var11 == var3) {
                  var9.append(var2);
               } else if (var11 <= var8.length) {
                  var9.append(var8[var11 - 1]);
               }

               if (var11 > var8.length && var11 < var3) {
                  var9.append("");
               }

               if (var11 == var3 && var11 > var8.length) {
                  var10 = true;
               }
            }

            var6.text(MiniMessage.miniMessage().deserialize(this.formatHudText(var9.toString())));
            if (var10 && var4) {
               this.moveHud(var1, (Vector)((MetadataValue)var1.getMetadata("location").get(0)).value(), 0);
            }
         } else {
            var6.text(MiniMessage.miniMessage().deserialize(this.formatHudText(var2)));
            if (var4) {
               this.moveHud(var1, (Vector)((MetadataValue)var1.getMetadata("location").get(0)).value(), 0);
            }
         }
      }
   }

   public void rotateHud(Entity var1, Vector var2, int var3, String var4) {
      if (var1 != null) {
         boolean var5 = this.isHudNoTransition(var1);
         int var6 = var5 ? 0 : Math.max(0, var3);
         int var7 = this.resolveSynchronizedTeleportDurationTicks(var6, var5);
         HudService.ClientTextHud var8 = this.getVirtualHud(var1);
         if (var8 != null) {
            double var17 = Math.toRadians(var2.getX());
            double var11 = Math.toRadians(var2.getY()) + Math.toRadians(180.0);
            double var13 = Math.toRadians(var2.getZ());
            Quaternionf var15 = new Quaternionf().rotationXYZ((float)var17, (float)var11, (float)var13);
            if (var4.equalsIgnoreCase("right")) {
               var8.rightRotation = var15;
            } else {
               var8.leftRotation = var15;
            }

            var8.interpolationDelay = 0;
            var8.interpolationDuration = var6;
            Player var18 = this.getVirtualHudOwner(var1);
            if (var18 != null) {
               this.sendVirtualHudTransformMetadata(var18, var8);
            }
         } else if (var1 instanceof Display var9) {
            this.applyFullBrightness(var9);
            double var10 = Math.toRadians(var2.getX());
            double var12 = Math.toRadians(var2.getY());
            double var14 = Math.toRadians(var2.getZ());
            if (var1 instanceof TextDisplay) {
               var12 += Math.toRadians(180.0);
            }

            Quaternionf var16 = new Quaternionf().rotationXYZ((float)var10, (float)var12, (float)var14);
            if (var4.equalsIgnoreCase("right")) {
               var9.setTransformation(
                  new Transformation(
                     var9.getTransformation().getTranslation(), var9.getTransformation().getLeftRotation(), var9.getTransformation().getScale(), var16
                  )
               );
            } else {
               var9.setTransformation(
                  new Transformation(
                     var9.getTransformation().getTranslation(), var16, var9.getTransformation().getScale(), var9.getTransformation().getRightRotation()
                  )
               );
            }

            var9.setInterpolationDelay(0);
            var9.setInterpolationDuration(var6);
            this.setDisplayTeleportDuration(var9, var7);
         }
      }
   }

   public Entity getHud(Player var1, String var2) {
      if (!var1.hasMetadata("hud_" + var2)) {
         HudService.ClientTextHud var4 = this.getVirtualHud(var1, var2);
         if (var4 != null && var4.handle != null && var4.handle.isValid() && !var4.handle.isDead()) {
            var1.setMetadata("hud_" + var2, new FixedMetadataValue(this.plugin, var4.handle));
            return var4.handle;
         } else {
            return null;
         }
      } else {
         Entity var3 = (Entity)((MetadataValue)var1.getMetadata("hud_" + var2).get(0)).value();
         if (var3 != null && var3.isValid() && !var3.isDead()) {
            return var3;
         } else {
            this.removeVirtualHud(var1, var2, false);
            var1.removeMetadata("hud_" + var2, this.plugin);
            return null;
         }
      }
   }

   public List<Entity> getAllHuds(Player var1) {
      if (var1 == null) {
         return new ArrayList<>();
      } else {
         LinkedHashSet var2 = new LinkedHashSet();

         for (Entity var4 : var1.getPassengers()) {
            if (var4 != null && var4.hasMetadata("hud") && this.isHudOwnedByPlayer(var4, var1)) {
               var2.add(var4);
            }
         }

         for (Entity var6 : var1.getWorld().getEntities()) {
            if (var6 != null && var6.hasMetadata("hud") && !var2.contains(var6) && this.isHudOwnedByPlayer(var6, var1)) {
               var2.add(var6);
            }
         }

         return new ArrayList<>(var2);
      }
   }

   private boolean isHudOwnedByPlayer(Entity var1, Player var2) {
      if (var1 != null && var2 != null && var1.hasMetadata("hud")) {
         Player var3 = this.resolveHudOwner(var1);
         if (var3 != null) {
            return var3.getUniqueId().equals(var2.getUniqueId());
         } else {
            String var4 = this.readHudId(var1);
            if (!var4.isBlank() && var2.hasMetadata("hud_" + var4)) {
               Entity var5 = this.readTrackedHudEntity(var2, var4);
               return var5 == null || var5.equals(var1);
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   private Entity readTrackedHudEntity(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank() && var1.hasMetadata("hud_" + var2)) {
         List var3 = var1.getMetadata("hud_" + var2);
         if (var3 != null && !var3.isEmpty() && var3.get(0) != null) {
            return ((MetadataValue)var3.get(0)).value() instanceof Entity var5 ? var5 : null;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   private String readHudId(Entity var1) {
      if (var1 != null && var1.hasMetadata("id")) {
         List var2 = var1.getMetadata("id");
         if (var2 != null && !var2.isEmpty() && var2.get(0) != null) {
            String var3 = ((MetadataValue)var2.get(0)).asString();
            return var3 == null ? "" : var3.trim();
         } else {
            return "";
         }
      } else {
         return "";
      }
   }

   private Player resolveHudOwner(Entity var1) {
      if (var1 == null) {
         return null;
      } else {
         Entity var3 = var1.getVehicle();
         if (var3 instanceof Player) {
            return (Player)var3;
         } else {
            if (var1.hasMetadata("hud_owner")) {
               List var2 = var1.getMetadata("hud_owner");
               if (var2 != null && !var2.isEmpty() && var2.get(0) != null) {
                  String var8 = ((MetadataValue)var2.get(0)).asString();
                  if (var8 != null && !var8.isBlank()) {
                     try {
                        Player var4 = Bukkit.getPlayer(UUID.fromString(var8));
                        if (var4 != null) {
                           return var4;
                        }
                     } catch (IllegalArgumentException var6) {
                     }
                  }
               }
            }

            String var7 = this.readHudId(var1);
            if (var7.isBlank()) {
               return null;
            } else {
               for (Player var10 : Bukkit.getOnlinePlayers()) {
                  if (var10.hasMetadata("hud_" + var7)) {
                     Entity var5 = this.readTrackedHudEntity(var10, var7);
                     if (var5 == null || var5.equals(var1)) {
                        return var10;
                     }
                  }
               }

               return null;
            }
         }
      }
   }

   public void refreshRuntimeHudAnchoring(Player var1, List<String> var2, Vector var3, int var4) {
      this.refreshRuntimeHudAnchoring(var1, var2, var3, var4, true);
   }

   public void refreshRuntimeHudAnchoring(Player var1, List<String> var2, Vector var3, int var4, boolean var5) {
      if (var1 != null && var1.isOnline()) {
         if (var2 != null && !var2.isEmpty()) {
            boolean var6 = var3 != null && Double.isFinite(var3.getX()) && Double.isFinite(var3.getY()) && Double.isFinite(var3.getZ());
            boolean var7 = var6 && Math.abs(var3.getX()) + Math.abs(var3.getY()) + Math.abs(var3.getZ()) > 1.0E-5;

            for (Object var9_raw : var2) {
               String var9 = var9_raw != null ? var9_raw.toString() : null;
               if (var9 != null && !var9.isBlank()) {
                  Entity var10 = this.getHud(var1, var9);
                  if (var10 != null && var10.isValid() && !var10.isDead() && !var10.hasMetadata("virtual_text_hud")) {
                     boolean var11 = var10.getVehicle() == var1 || var1.getPassengers().contains(var10);
                     if (var5 && !var11) {
                        try {
                           var1.addPassenger(var10);
                        } catch (Throwable var15) {
                        }

                        var11 = var10.getVehicle() == var1 || var1.getPassengers().contains(var10);
                     }

                     if (!var11 && var7) {
                        Location var12 = var1.getLocation();
                        if (var12 != null && var12.getWorld() != null) {
                           PlatformCompat.teleportSafely(var10, var12);
                        }

                        if (var5) {
                           try {
                              var1.addPassenger(var10);
                           } catch (Throwable var14) {
                           }
                        }
                     }
                  }
               }
            }

            this.syncClientPassengers(var1);
         } else {
            this.syncClientPassengers(var1);
         }
      }
   }

   public void resendAllHudPositions(Player var1) {
      if (var1 != null && var1.isOnline()) {
         for (Entity var3 : this.getAllHuds(var1)) {
            Vector var4 = this.getHudLocation(var3);
            if (var4 != null) {
               this.moveHud(var3, var4, 1);
            }
         }

         Map var5 = this.virtualTextHuds.get(var1.getUniqueId());
         if (var5 != null) {
            for (HudService.ClientTextHud var7 : new ArrayList<HudService.ClientTextHud>(var5.values())) {
               this.sendVirtualHudTransformMetadata(var1, var7);
            }
         }
      }
   }

   public void removeHudX(List<Entity> var1, boolean var2) {
      for (Entity var4 : var1) {
         if (var4 != null) {
            HudService.ClientTextHud var5 = this.getVirtualHud(var4);
            if (var5 != null) {
               Player var6 = this.getVirtualHudOwner(var4);
               if (var6 != null) {
                  if (var2) {
                     Vector var7 = var5.scale;
                     this.animScale(var4, var7.getX(), var7.getY(), var7.getZ(), true);
                  } else {
                     this.removeVirtualHud(var6, var5.id, true);
                  }
               } else if (var4.isValid() && !var4.isDead()) {
                  var4.remove();
               }
            } else if (var2) {
               Vector var8 = (Vector)((MetadataValue)var4.getMetadata("scale").get(0)).value();
               this.animScale(var4, var8.getX(), var8.getY(), var8.getZ(), true);
            } else {
               String var9 = this.readHudId(var4);
               Player var10 = this.resolveHudOwner(var4);
               if (var10 != null && !var9.isBlank()) {
                  var10.removeMetadata("hud_" + var9, this.plugin);
               }

               var4.remove();
               if (var10 != null) {
                  this.syncClientPassengers(var10);
               }
            }
         }
      }
   }

   public void clearHuds(Player var1) {
      this.removeHudX(this.getAllHuds(var1), false);
      Map var2 = this.virtualTextHuds.get(var1.getUniqueId());
      if (var2 != null && !var2.isEmpty()) {
         for (String var5 : new ArrayList<String>(var2.keySet())) {
            this.removeVirtualHud(var1, var5, true);
         }
      }

      this.syncClientPassengers(var1);
   }

   public void clearHudsKeepCursor(Player var1) {
      if (var1 != null) {
         ArrayList var2 = new ArrayList();

         for (Entity var4 : this.getAllHuds(var1)) {
            if (var4 != null && var4.hasMetadata("id")) {
               String var5 = ((MetadataValue)var4.getMetadata("id").get(0)).asString();
               if (!"cursor".equalsIgnoreCase(var5)) {
                  var2.add(var4);
               }
            } else {
               var2.add(var4);
            }
         }

         this.removeHudX(var2, false);
         Map var7 = this.virtualTextHuds.get(var1.getUniqueId());
         if (var7 != null && !var7.isEmpty()) {
            for (String var6 : new ArrayList<String>(var7.keySet())) {
               if (!"cursor".equalsIgnoreCase(var6)) {
                  this.removeVirtualHud(var1, var6, true);
               }
            }
         }

         this.syncClientPassengers(var1);
      }
   }

   private Entity spawnVirtualHandle(Player var1, String var2, Vector var3, Vector var4, int var5, int var6, TextAlignment var7) {
      Entity var8 = this.createVirtualHandle(var1, var2, this.nextVirtualHandleEntityId());
      var8.setMetadata("text_alignment", new FixedMetadataValue(this.plugin, this.normalizeTextAlignment(var7).name()));
      var8.setRotation(0.0F, 0.0F);
      var8.setMetadata("id", new FixedMetadataValue(this.plugin, var2));
      var8.setMetadata("hud", new FixedMetadataValue(this.plugin, true));
      var8.setMetadata("hud_owner", new FixedMetadataValue(this.plugin, var1.getUniqueId().toString()));
      var8.setMetadata("location", new FixedMetadataValue(this.plugin, var3.clone()));
      var8.setMetadata("scale", new FixedMetadataValue(this.plugin, var4.clone()));
      var8.setMetadata("opacity", new FixedMetadataValue(this.plugin, var6));
      var8.setMetadata("aligned", new FixedMetadataValue(this.plugin, var5));
      var8.setMetadata("virtual_text_hud", new FixedMetadataValue(this.plugin, true));
      var1.setMetadata("hud_" + var2, new FixedMetadataValue(this.plugin, var8));
      return var8;
   }

   private int nextVirtualHandleEntityId() {
      int var1 = this.virtualHandleEntityIds.getAndDecrement();
      if (var1 == Integer.MIN_VALUE) {
         this.virtualHandleEntityIds.set(-1);
         var1 = this.virtualHandleEntityIds.getAndDecrement();
      }

      return var1;
   }

   private int nextVirtualEntityId() {
      int var1 = this.virtualEntityIds.getAndIncrement();
      if (var1 == Integer.MAX_VALUE) {
         this.virtualEntityIds.set(2000000000);
         var1 = this.virtualEntityIds.getAndIncrement();
      }

      return var1;
   }

   private HudService.ClientTextHud getVirtualHud(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var3 = this.virtualTextHuds.get(var1.getUniqueId());
         return var3 == null ? null : (HudService.ClientTextHud)var3.get(var2);
      } else {
         return null;
      }
   }

   private HudService.ClientTextHud getVirtualHud(Entity var1) {
      if (var1 == null) {
         return null;
      } else {
         HudService.VirtualHudRef var2 = this.virtualHudRefs.get(var1.getUniqueId());
         if (var2 == null) {
            return null;
         } else {
            Map var3 = this.virtualTextHuds.get(var2.playerId());
            return var3 == null ? null : (HudService.ClientTextHud)var3.get(var2.id());
         }
      }
   }

   private Player getVirtualHudOwner(Entity var1) {
      if (var1 == null) {
         return null;
      } else {
         HudService.VirtualHudRef var2 = this.virtualHudRefs.get(var1.getUniqueId());
         return var2 == null ? null : Bukkit.getPlayer(var2.playerId());
      }
   }

   private void registerVirtualHud(Player var1, HudService.ClientTextHud var2) {
      this.virtualTextHuds.computeIfAbsent(var1.getUniqueId(), var0 -> new ConcurrentHashMap<>()).put(var2.id, var2);
      this.virtualHudRefs.put(var2.handle.getUniqueId(), new HudService.VirtualHudRef(var1.getUniqueId(), var2.id));
      var2.handle.setMetadata("text_alignment", new FixedMetadataValue(this.plugin, var2.textAlignment.name()));
   }

   private void removeVirtualHud(Player var1, String var2, boolean var3) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var4 = this.virtualTextHuds.get(var1.getUniqueId());
         if (var4 == null) {
            var1.removeMetadata("hud_" + var2, this.plugin);
         } else {
            HudService.ClientTextHud var5 = (HudService.ClientTextHud)var4.remove(var2);
            if (var5 == null) {
               var1.removeMetadata("hud_" + var2, this.plugin);
               if (var4.isEmpty()) {
                  this.virtualTextHuds.remove(var1.getUniqueId());
               }
            } else {
               this.virtualHudRefs.remove(var5.handle.getUniqueId());
               this.sendPacket(var1, new WrapperPlayServerDestroyEntities(var5.clientEntityId));
               if (var3 && var5.handle != null && var5.handle.isValid() && !var5.handle.isDead()) {
                  var5.handle.remove();
               }

               var1.removeMetadata("hud_" + var2, this.plugin);
               if (var4.isEmpty()) {
                  this.virtualTextHuds.remove(var1.getUniqueId());
               }

               this.syncClientPassengers(var1);
            }
         }
      }
   }

   private void spawnVirtualTextHud(Player var1, HudService.ClientTextHud var2) {
      Location var3 = var1.getLocation();
      WrapperPlayServerSpawnEntity var4 = new WrapperPlayServerSpawnEntity(
         var2.clientEntityId,
         var2.clientEntityUuid,
         EntityTypes.TEXT_DISPLAY,
         new com.github.retrooper.packetevents.protocol.world.Location(var3.getX(), var3.getY(), var3.getZ(), 0.0F, 0.0F),
         0.0F,
         0,
         null
      );
      this.sendPacket(var1, var4);
      this.sendVirtualHudMetadata(var1, var2);
   }

   private void sendVirtualHudMetadata(Player var1, HudService.ClientTextHud var2) {
      if (var1 != null && var2 != null) {
         List var3 = this.buildVirtualHudMetadata(var1, var2);
         this.sendPacket(var1, new WrapperPlayServerEntityMetadata(var2.clientEntityId, var3));
      }
   }

   private void sendVirtualHudTransformMetadata(Player var1, HudService.ClientTextHud var2) {
      if (var1 != null && var2 != null) {
         List var3 = this.buildVirtualHudTransformMetadata(var1, var2);
         this.sendPacket(var1, new WrapperPlayServerEntityMetadata(var2.clientEntityId, var3));
      }
   }

   private void sendVirtualHudTextMetadata(Player var1, HudService.ClientTextHud var2) {
      if (var1 != null && var2 != null) {
         ArrayList var3 = new ArrayList(1);
         var3.add(new EntityData(23, EntityDataTypes.COMPONENT, this.getSerializedHudTextJson(var2)));
         this.sendPacket(var1, new WrapperPlayServerEntityMetadata(var2.clientEntityId, var3));
      }
   }

   private void sendVirtualHudOpacityMetadata(Player var1, HudService.ClientTextHud var2) {
      if (var1 != null && var2 != null) {
         ArrayList var3 = new ArrayList(1);
         var3.add(new EntityData(26, EntityDataTypes.BYTE, (byte)this.clampOpacity(var2.opacity)));
         this.sendPacket(var1, new WrapperPlayServerEntityMetadata(var2.clientEntityId, var3));
      }
   }

   private void sendVirtualHudLineWidthMetadata(Player var1, HudService.ClientTextHud var2) {
      if (var1 != null && var2 != null) {
         ArrayList var3 = new ArrayList(1);
         var3.add(new EntityData(24, EntityDataTypes.INT, var2.lineWidth > 0 ? var2.lineWidth : 200));
         this.sendPacket(var1, new WrapperPlayServerEntityMetadata(var2.clientEntityId, var3));
      }
   }

   private void sendVirtualHudFlagsMetadata(Player var1, HudService.ClientTextHud var2) {
      if (var1 != null && var2 != null) {
         ArrayList var3 = new ArrayList(1);
         var3.add(new EntityData(27, EntityDataTypes.BYTE, this.buildTextDisplayFlags(this.normalizeTextAlignment(var2.textAlignment))));
         this.sendPacket(var1, new WrapperPlayServerEntityMetadata(var2.clientEntityId, var3));
      }
   }

   private String getSerializedHudTextJson(HudService.ClientTextHud var1) {
      String var2 = var1.text == null ? "" : var1.text;
      if (var1.cachedTextJson != null && var2.equals(var1.cachedText)) {
         return var1.cachedTextJson;
      } else {
         String var3 = (String)GSON_COMPONENT.serialize(MM.deserialize(this.formatHudText(var2)));
         var1.cachedText = var2;
         var1.cachedTextJson = var3;
         return var3;
      }
   }

   private List<EntityData> buildVirtualHudTransformMetadata(Player var1, HudService.ClientTextHud var2) {
      Quaternionf var3 = var2.leftRotation == null
         ? new Quaternionf().rotationXYZ(0.0F, (float)Math.toRadians(180.0), 0.0F)
         : new Quaternionf(var2.leftRotation);
      Quaternionf var4 = var2.rightRotation == null ? new Quaternionf(0.0F, 0.0F, 0.0F, 1.0F) : new Quaternionf(var2.rightRotation);
      Vector var5 = var2.location == null ? new Vector() : var2.location;
      if (this.shouldApplyLegacy121TextBaselineCompensation(var1)) {
         var5 = this.applyLegacy121TextBaselineCompensation(var5, var2.scale);
      }

      Vector3f var6 = this.getLocationVector(var5, var2.scale, null, var2.alignedOffset);
      Vector3f var7 = this.getScaleVector(var2.scale);
      ArrayList var8 = new ArrayList();
      var8.add(new EntityData(8, EntityDataTypes.INT, Math.max(0, var2.interpolationDelay)));
      var8.add(new EntityData(9, EntityDataTypes.INT, Math.max(0, var2.interpolationDuration)));
      var8.add(new EntityData(10, EntityDataTypes.INT, 0));
      var8.add(new EntityData(11, EntityDataTypes.VECTOR3F, new com.github.retrooper.packetevents.util.Vector3f(var6.x, var6.y, var6.z)));
      var8.add(new EntityData(12, EntityDataTypes.VECTOR3F, new com.github.retrooper.packetevents.util.Vector3f(var7.x, var7.y, var7.z)));
      var8.add(new EntityData(13, EntityDataTypes.QUATERNION, new Quaternion4f(var3.x, var3.y, var3.z, var3.w)));
      var8.add(new EntityData(14, EntityDataTypes.QUATERNION, new Quaternion4f(var4.x, var4.y, var4.z, var4.w)));
      var8.add(new EntityData(16, EntityDataTypes.INT, 15728880));
      return var8;
   }

   private boolean shouldApplyLegacy121TextBaselineCompensation(Player var1) {
      if (var1 == null) {
         return false;
      } else {
         try {
            ClientVersion var2 = PacketEvents.getAPI().getPlayerManager().getClientVersion(var1);
            return var2 == ClientVersion.V_1_21;
         } catch (Throwable var3) {
            return false;
         }
      }
   }

   private Vector applyLegacy121TextBaselineCompensation(Vector var1, Vector var2) {
      if (var1 == null) {
         return new Vector();
      } else {
         double var3 = var2 == null ? 0.0 : Math.max(0.0, var2.getY() * 2.0);
         double var5 = var3 / 1.25;
         double var7 = var5 * 0.015025041736227046;
         if (Double.isFinite(var7) && !(var7 <= 0.0)) {
            Vector var9 = var1.clone();
            var9.setY(var9.getY() + var7);
            return var9;
         } else {
            return var1;
         }
      }
   }

   private List<EntityData> buildVirtualHudMetadata(Player var1, HudService.ClientTextHud var2) {
      TextAlignment var3 = this.normalizeTextAlignment(var2.textAlignment);
      int var4 = this.clampOpacity(var2.opacity);
      List var5 = this.buildVirtualHudTransformMetadata(var1, var2);
      var5.add(new EntityData(23, EntityDataTypes.COMPONENT, this.getSerializedHudTextJson(var2)));
      var5.add(new EntityData(24, EntityDataTypes.INT, var2.lineWidth > 0 ? var2.lineWidth : 200));
      var5.add(new EntityData(25, EntityDataTypes.INT, 1073741824));
      var5.add(new EntityData(26, EntityDataTypes.BYTE, (byte)var4));
      var5.add(new EntityData(27, EntityDataTypes.BYTE, this.buildTextDisplayFlags(var3)));
      return var5;
   }

   private byte buildTextDisplayFlags(TextAlignment var1) {
      byte var2 = 4;
      if (var1 == TextAlignment.LEFT) {
         var2 = (byte)(var2 | 8);
      } else if (var1 == TextAlignment.RIGHT) {
         var2 = (byte)(var2 | 16);
      }

      return var2;
   }

   private Entity createVirtualHandle(Player var1, String var2, int var3) {
      UUID var4 = UUID.randomUUID();
      HashMap var5 = new HashMap();
      AtomicBoolean var6 = new AtomicBoolean(true);
      InvocationHandler var7 = (var7x, var8, var9) -> {
         String var10 = var8.getName();

         return switch (var10) {
            case "setMetadata" -> {
               if (var9 != null && var9.length == 2 && var9[0] instanceof String var18 && var9[1] instanceof MetadataValue var22) {
                  var5.put(var18, var22);
               }

               yield null;
            }
            case "getMetadata" -> {
               if (var9 != null && var9.length == 1 && var9[0] instanceof String var17) {
                  MetadataValue var21 = (MetadataValue)var5.get(var17);
                  yield var21 == null ? Collections.emptyList() : Collections.singletonList(var21);
               }

               yield Collections.emptyList();
            }
            case "hasMetadata" -> var9 != null && var9.length == 1 && var9[0] instanceof String var16 && var5.containsKey(var16);
            case "removeMetadata" -> {
               if (var9 != null && var9.length == 2 && var9[0] instanceof String var13 && var9[1] instanceof Plugin) {
                  var5.remove(var13);
               }

               yield null;
            }
            case "getUniqueId" -> var4;
            case "getEntityId" -> var3;
            case "isValid" -> var6.get();
            case "isDead" -> !var6.get();
            case "remove" -> {
               var6.set(false);
               yield null;
            }
            case "setRotation" -> null;
            case "getType" -> EntityType.MARKER;
            case "getWorld" -> var1.getWorld();
            case "getVehicle" -> null;
            case "isInsideVehicle" -> false;
            case "getPassengers" -> Collections.emptyList();
            case "addPassenger", "removePassenger", "eject" -> false;
            case "getLocation" -> var1.getLocation().clone();
            case "toString" -> "VirtualHudHandle{" + var2 + "," + var4 + "}";
            case "hashCode" -> var4.hashCode();
            case "equals" -> var7x == (var9 != null && var9.length != 0 ? var9[0] : null);
            default -> this.defaultValue(var8.getReturnType());
         };
      };
      return (Entity)Proxy.newProxyInstance(Entity.class.getClassLoader(), new Class[]{Entity.class}, var7);
   }

   private Object defaultValue(Class<?> var1) {
      if (!var1.isPrimitive()) {
         return null;
      } else if (var1 == boolean.class) {
         return false;
      } else if (var1 == char.class) {
         return '\u0000';
      } else if (var1 == byte.class) {
         return (byte)0;
      } else if (var1 == short.class) {
         return (short)0;
      } else if (var1 == int.class) {
         return 0;
      } else if (var1 == long.class) {
         return 0L;
      } else if (var1 == float.class) {
         return 0.0F;
      } else {
         return var1 == double.class ? 0.0 : null;
      }
   }

   private static Method resolveDisplaySetTeleportDurationMethod() {
      try {
         return Display.class.getMethod("setTeleportDuration", int.class);
      } catch (Throwable var1) {
         return null;
      }
   }

   private void setDisplayTeleportDuration(Display var1, int var2) {
      if (var1 != null && DISPLAY_SET_TELEPORT_DURATION_METHOD != null) {
         try {
            DISPLAY_SET_TELEPORT_DURATION_METHOD.invoke(var1, Math.max(0, var2));
         } catch (Throwable var4) {
         }
      }
   }

   public void clearPassengerQueue(Player var1) {
      if (var1 != null) {
         this.queuedPassengerSyncPlayers.remove(var1.getUniqueId());
         this.externalPassengerCache.remove(var1.getUniqueId());
      }
   }

   private void syncClientPassengers(Player var1) {
      if (var1 != null && var1.isOnline()) {
         UUID var2 = var1.getUniqueId();
         if (this.queuedPassengerSyncPlayers.add(var2)) {
            PlatformCompat.runEntityTask(this.plugin, var1, () -> {
               this.queuedPassengerSyncPlayers.remove(var2);
               Player var2x = Bukkit.getPlayer(var2);
               if (var2x != null && var2x.isOnline()) {
                  this.syncClientPassengersNow(var2x);
               }
            });
         }
      }
   }

   private void syncClientPassengersNow(Player var1) {
      if (var1 != null && var1.isOnline()) {
         LinkedHashSet var2 = new LinkedHashSet();

         for (Entity var4 : var1.getPassengers()) {
            if (var4 != null && var4.isValid() && !var4.isDead()) {
               var2.add(Integer.valueOf(var4.getEntityId()));
            }
         }

         int[] var8 = this.externalPassengerCache.get(var1.getUniqueId());
         if (var8 != null) {
            for (Object var7_raw : var8) {
               int var7 = ((Number)var7_raw).intValue();
               var2.add(Integer.valueOf(var7));
            }
         }

         Map<String, HudService.ClientTextHud> var10 = this.virtualTextHuds.get(var1.getUniqueId());
         if (var10 != null) {
            for (HudService.ClientTextHud var12 : var10.values()) {
               var2.add(Integer.valueOf(var12.clientEntityId));
            }
         }

         this.sendPacket(var1, new WrapperPlayServerSetPassengers(var1.getEntityId(), toIntArray(var2)));
      }
   }

   private static int[] toIntArray(Collection<Integer> var0) {
      int[] var1 = new int[var0.size()];
      int var2 = 0;

      for (Object var4_raw : var0) {
         int var4 = ((Number)var4_raw).intValue();
         var1[var2++] = var4;
      }

      return var1;
   }

   private void sendPacket(Player var1, PacketWrapper<?> var2) {
      if (var1 != null && var1.isOnline() && var2 != null) {
         try {
            PacketEvents.getAPI().getPlayerManager().sendPacket(var1, var2);
         } catch (Throwable var4) {
            this.plugin
               .getLogger()
               .warning(
                  String.format(
                     Locale.ROOT,
                     "[UltimateUI] Failed to send PacketEvents packet %s to %s: %s",
                     var2.getClass().getSimpleName(),
                     var1.getName(),
                     var4.getMessage()
                  )
               );
         }
      }
   }

   private static final class ClientTextHud {
      private final int clientEntityId;
      private final UUID clientEntityUuid;
      private final Entity handle;
      private final String id;
      private Vector location;
      private Vector scale;
      private String text;
      private TextAlignment textAlignment;
      private int alignedOffset;
      private int opacity;
      private int lineWidth;
      private Quaternionf leftRotation;
      private Quaternionf rightRotation;
      private int interpolationDuration;
      private int interpolationDelay;
      private String cachedText;
      private String cachedTextJson;

      private ClientTextHud(
         int var1,
         UUID var2,
         Entity var3,
         String var4,
         Vector var5,
         Vector var6,
         String var7,
         TextAlignment var8,
         int var9,
         int var10,
         int var11,
         Quaternionf var12,
         Quaternionf var13,
         int var14,
         int var15
      ) {
         this.clientEntityId = var1;
         this.clientEntityUuid = var2;
         this.handle = var3;
         this.id = var4;
         this.location = var5;
         this.scale = var6;
         this.text = var7;
         this.textAlignment = var8;
         this.alignedOffset = var9;
         this.opacity = var10;
         this.lineWidth = var11;
         this.leftRotation = var12;
         this.rightRotation = var13;
         this.interpolationDuration = var14;
         this.interpolationDelay = var15;
      }
   }

   private static record VirtualHudRef(UUID playerId, String id) {
   }
}
