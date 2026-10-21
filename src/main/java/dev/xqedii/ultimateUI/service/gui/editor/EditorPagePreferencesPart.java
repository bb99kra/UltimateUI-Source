package dev.xqedii.ultimateUI.service.gui.editor;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.Xqedii_10gtlsk;
import dev.xqedii.ultimateUI.UiRefreshConfig;
import dev.xqedii.ultimateUI.Xqedii_30g0ulj;
import dev.xqedii.ultimateUI.Xqedii_38o2zes;
import dev.xqedii.ultimateUI.Xqedii_3uxgwyg;
import dev.xqedii.ultimateUI.Xqedii_l2une6g;
import dev.xqedii.ultimateUI.Xqedii_nd6rkr3;
import dev.xqedii.ultimateUI.api.UiSession;
import dev.xqedii.ultimateUI.gui.model.HoverElement;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.GuiService;
import dev.xqedii.ultimateUI.service.gui.editor.shell.managers.EditorTimelineKeyframePart;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.gui.model.EditorSidebarTab;
import dev.xqedii.ultimateUI.service.gui.model.EditorTool;
import dev.xqedii.ultimateUI.service.gui.model.PreviewViewport;
import dev.xqedii.ultimateUI.service.gui.model.RoundedBox;
import dev.xqedii.ultimateUI.service.gui.model.RoundedCornerGlyphs;
import dev.xqedii.ultimateUI.service.gui.model.RoundedCornerOffsets;
import dev.xqedii.ultimateUI.service.gui.model.RoundedTransform;
import dev.xqedii.ultimateUI.service.hud.HudPositionCalculator;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.service.keybind.KeybindService;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.PacketEvents;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.util.SpigotConversionUtil;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.server.WrapperPlayServerSetSlot;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.util.Vector;

public abstract class EditorPagePreferencesPart extends EditorPopupPagePart {
   public static final String Xqedii_2qgsq67sy63dx = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";
   public final ConcurrentHashMap<UUID, Double> Xqedii_12w2nt3 = new ConcurrentHashMap<>();
   public final ConcurrentHashMap<UUID, Optional<Boolean>> Xqedii_yxcgamd = new ConcurrentHashMap<>();
   public final ConcurrentHashMap<UUID, Optional<Boolean>> Xqedii_18x15ls = new ConcurrentHashMap<>();

   public EditorPagePreferencesPart(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   @Override
   public void applyEditorDisplayPreferences(Player var1) {
      if ((1873220284 ^ 1873220284) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && var2.editMode && var2.previewMode) {
            this.applyEditorDisplayPreferences(var1, var2);
         } else {
            this.setClientHotbarVisibleAsAir(var1);
         }
      }
   }

   @Override
   public void applyEditorDisplayPreferences(Player var1, EditorSession var2) {
      if (((264210389 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         if (var2.displayHotbar) {
            this.restoreClientHotbarFromServerInventory(var1);
         } else {
            this.setClientHotbarVisibleAsAir(var1);
         }

         if (var2.displayHand) {
            this.restoreHeldHotbarSlotFromServerInventory(var1);
         } else {
            this.setHeldHotbarSlotAsAir(var1);
         }

         this.syncPreviewUiAddonsVisibility(var1, var2);
      }
   }

   public void scheduleClientHotbarMaskRefresh(Player var1, EditorSession var2) {
      if (((-1836376307 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         UUID var3 = var1.getUniqueId();
         long[] var4 = Xqedii_l2une6g.Xqedii_1tcwp05;
         int var5 = var4.length;

         for (int var6 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1975164662, 15); var6 < var5; var6++) {
            long var7 = var4[var6];
            if (var7 > Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444750515892723142L, 13)) {
               Xqedii_38o2zes.Xqedii_2sj63w1(this.plugin, var1, () -> {
                  if ((1254200194 * 1254200194 + 1254200194 & 1) != 0) {
                     throw new IllegalStateException();
                  } else if (var1.isOnline()) {
                     EditorSession var4x = this.editorSessions.get(var3);
                     if (var4x != null && var4x == var2) {
                        this.applyEditorDisplayPreferences(var1, var4x);
                     }
                  }
               }, var7);
            }
         }
      }
   }

   public void syncPreviewUiAddonsVisibility(Player var1, EditorSession var2) {
      if (((-975756442 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         int var3 = !var2.displayHand && !var2.displayHotbar
            ? Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 19)
            : Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1280454409, 10);
         ConfigurationSection var4 = this.findShellBlockSection(
            var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("HyVC9OGPItrbcere4wke6ABmM9dthabLP3ydKu2GuxmRZShzTzj+QtGMH3hb4euDn8w8t28RRw9HieXXiiA=")
         );
         this.beginShellOpacityBatch(var2);

         try {
            if (var3 != 0 && var4 != null) {
               this.spawnShellPanelHud(
                  var1, var2, var4, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("HyVC9OGPItrbcere4wke6ABmM9dthabLP3ydKu2GuxmRZShzTzj+QtGMH3hb4euDn8w8t28RRw9HieXXiiA=")
               );
            }

            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("HyVC9OGPItrbcere4wke6ABmM9dthabLP3ydKu2GuxmRZShzTzj+QtGMH3hb4euDn8w8t28RRw9HieXXiiA="),
               (var3 != 0)
            );
            if (var3 == 0) {
               return;
            }

            this.setShellOpacity(
               var1,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                  "5zJh5hjw1D0KrJAvJSEN8HTrwbgX0CYEU5XJ188Wv+cGQWiDnD6K/BNFxmc+5He+hiLotS0cfTn+HEcsuBP2sr4KQQTURCVabdjQe6clNr4XxA=="
               ),
               var2.displayHand ? Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1025627782, 5) : Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-139219500, 2)
            );
            this.setShellOpacity(
               var1,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                  "7GeTyvMshikDbva/DjneSxFwBBoiG/YOAlMn2wB/I7C2N1Wko+LL8bD9lRLYQUzZViW9eZ8AT0d37TYKY+qoibqZ6OJx5SB7qNVw0od0y7JP0Uqi6LM="
               ),
               var2.displayHotbar ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1026133415, 30) : Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 25)
            );
            this.setPreviewUiAddonGlyphOpacity(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("7dZW9QFGZ1QgU535NsmkXxy3mm7eM7QX6BokLVLc"),
               var2.displayHand ? Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-384912335, 8) : Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-2032494348, 15)
            );
            this.setPreviewUiAddonGlyphOpacity(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("xfBrptJm2vzfJTINBreOEPF5NghFeaAIQ9ZuVVT4"),
               var2.displayHotbar ? Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-2024138508, 15) : Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 16)
            );
         } finally {
            this.endShellOpacityBatch(var1, var2);
         }

         if (var3 != 0) {
            this.updatePreviewUiAddonsAnchor(var1, var2);
            this.schedulePreviewUiAddonsAnchorStabilization(var1, var2);
         }
      }
   }

   public void schedulePreviewUiAddonsAnchorStabilization(Player var1, EditorSession var2) {
      if ((-152944623 * -152944623 + -152944623 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         UUID var3 = var1.getUniqueId();
         long[] var4 = Xqedii_l2une6g.Xqedii_2ty0i96;
         int var5 = var4.length;

         for (int var6 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-826846242, 12); var6 < var5; var6++) {
            long var7 = var4[var6];
            if (var7 > Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149447249402688283L, 50)) {
               Xqedii_38o2zes.Xqedii_2sj63w1(this.plugin, var1, () -> {
                  if ((50910210 * 50910210 - 50910210 & 1) != 0) {
                     throw new IllegalStateException();
                  } else if (var1.isOnline()) {
                     EditorSession var4x = this.editorSessions.get(var3);
                     if (var4x != null && var4x == var2 && var4x.editMode && var4x.previewMode) {
                        if (var4x.displayHand || var4x.displayHotbar) {
                           this.updatePreviewUiAddonsAnchor(var1, var4x);
                        }
                     }
                  }
               }, var7);
            }
         }
      }
   }

   public void setPreviewUiAddonGlyphOpacity(Player var1, EditorSession var2, String var3, int var4) {
      if ((-164760302 * -164760302 + -164760302 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         ConfigurationSection var5 = this.findShellBlockSection(
            var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("HyVC9OGPItrbcere4wke6ABmM9dthabLP3ydKu2GuxmRZShzTzj+QtGMH3hb4euDn8w8t28RRw9HieXXiiA=")
         );
         String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1201039027, 24)];
         var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 5)] = var5 == null
            ? null
            : var5.getString(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("VqgeM2Aq+uqqBybLCsnUpFvJa3h/nu8/EwJzKL21riXD7S8+edRWl8UzSMdspTbZeVA5WYottRYf8tYUeJSCSuyVVV0=")
            );
         String var6 = this.firstNonBlank(var10001);
         if (!var6.isBlank()) {
            int var7 = Math.max(Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1734060527, 11), Math.min(Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350046, 2), var4));
            HashMap var8 = new HashMap();
            int var9 = Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 0);

            for (Map var11 : var2.shellBlocks) {
               var9++;
               ConfigurationSection var12 = this.mapToSection(var11);
               if (var12 != null) {
                  var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1452697267, 28)];
                  var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-384977615, 8)] = var12.getString(
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("VqgeM2Aq+uqqBybLCsnUpFvJa3h/nu8/EwJzKL21riXD7S8+edRWl8UzSMdspTbZeVA5WYottRYf8tYUeJSCSuyVVV0=")
                  );
                  String var13 = this.firstNonBlank(var10001);
                  if (this.belongsToSidebarPanel(var6, var13)) {
                     var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1318479539, 26)];
                     var10001[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 18)] = var12.getString(
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("67bwboawFEWbkseanySzvg1QFeC0Xdd4zDW6+CtiAYQDT+ro")
                     );
                     var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 11)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "BD52BFHhkJOFBAfs8EKh+2AXSNN6Pr0OfcwazKku2VNZ8JVcJ5Y="
                     );
                     String var14 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
                     if (Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("Qh7aR7gBntvpdL5W0IWxucVTclLRizkKvAPuhkfF+yDwsnXO").equals(var14)) {
                        var10001 = new String[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-407003794, 29)];
                        var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 2)] = var12.getString(
                           Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("Qh7aR7gBntvpdL5W0IWxucVTclLRizkKvAPuhkfF+yDwsnXO")
                        );
                        var10001[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-139219504, 2)] = var12.getString(
                           Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("xZKZOCQJ1lau7obN8EfZRKJQhIKeljBsV8pElyrlSE6pWop+MxqLYY5l")
                        );
                        String var15 = this.firstNonBlank(var10001);
                        if (var3.equals(var15)) {
                           String var16 = this.resolveElementId(var12, var9, var8);
                           if (var16 != null && !var16.isBlank()) {
                              this.setShellOpacity(var1, var16, var7);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void applyEditorHeldToolMask(Player var1) {
      if (((1815351727 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && var2.editMode && var2.previewMode && var2.displayHand) {
            this.restoreHeldHotbarSlotFromServerInventory(var1);
         } else {
            this.setHeldHotbarSlotAsAir(var1);
         }
      }
   }

   public void setClientHotbarVisibleAsAir(Player var1) {
      if ((466325055 ^ 466325055) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         ItemStack var2 = new ItemStack(Material.AIR);

         for (int var3 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 2); var3 < Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184262035, 5); var3++) {
            this.sendClientHotbarSlot(var1, var3, var2);
         }
      }
   }

   public void setHeldHotbarSlotAsAir(Player var1) {
      if (((-1168766772 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         int var2 = var1.getInventory().getHeldItemSlot();
         if (var2 >= 0 && var2 < Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(-694786381, 28)) {
            ItemStack var3 = new ItemStack(Material.AIR);
            this.sendClientHotbarSlot(var1, var2, var3);
         }
      }
   }

   public void restoreHeldHotbarSlotFromServerInventory(Player var1) {
      if (((1647954095 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         int var2 = var1.getInventory().getHeldItemSlot();
         if (var2 >= 0 && var2 < Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121415782, 13)) {
            ItemStack var3 = var1.getInventory().getItem(var2);
            this.sendClientHotbarSlot(var1, var2, var3);
         }
      }
   }

   public void restoreClientHotbarFromServerInventory(Player var1) {
      if ((2091416131 * 2091416131 - 2091416131 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         for (int var2 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1891078743, 3); var2 < Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-552012666, 23); var2++) {
            ItemStack var3 = var1.getInventory().getItem(var2);
            this.sendClientHotbarSlot(var1, var2, var3);
         }
      }
   }

   public void sendClientHotbarSlot(Player var1, int var2, ItemStack var3) {
      if ((-373207168 ^ -373207168) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 >= 0 && var2 < Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350262, 1)) {
         ItemStack var4 = var3 == null ? new ItemStack(Material.AIR) : var3.clone();
         dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.item.ItemStack var5 = SpigotConversionUtil.fromBukkitItemStack(var4);
         int var6 = Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261363, 4) + var2;
         WrapperPlayServerSetSlot var7 = new WrapperPlayServerSetSlot(
            Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 17), Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 21), var6, var5
         );

         try {
            PacketEvents.getAPI().getPlayerManager().sendPacket(var1, var7);
         } catch (Throwable var9) {
            Logger var10000 = this.plugin.getLogger();
            Locale var10001 = Locale.ROOT;
            String var10002 = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
               "3MKHCAAkdO3Ijc4wefTI2fTnlTs+0XnO5YK4g6lNnJDMSfuck51quSHJ+AqBt/i/7wInzrfP0cqFq9WIMBn+ea0sPaECMGctTi3DLO5iFuKdUu4kJ+qc40OCjfe7FEiGlwIa9HVVGwdJKRXwnIjfp8iLJXMwt53M9vOI0W/m1+QywQkVYu/JOYtbz/57PnZPWxk="
            );
            Object[] var10003 = new Object[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1131285626, 14)];
            var10003[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1734060527, 11)] = var2;
            var10003[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261819, 3)] = var1.getName();
            var10003[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184786099, 18)] = var9.getMessage();
            var10000.warning(String.format(var10001, var10002, var10003));
         }
      }
   }

   public List<String> getGuiNames() {
      if (((1314960061 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         File var1 = new File(
            this.plugin.getDataFolder(), Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("mR+wgJPFzfhM3VHNmOv51W98mNSlnmnRame0olfzd/Hlu6/XZI2W7YkkFx4nZl2FsuKddIzM9mA=")
         );
         if (var1.exists() && var1.isDirectory()) {
            File[] var2 = var1.listFiles((var0, var1x) -> {
               if ((-1081310448 * -1081310448 + -1081310448 & 1) != 0) {
                  throw new IllegalStateException();
               } else {
                  return var1x.toLowerCase().endsWith(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("80ljh/3cUK9dBzxkS5XckA1r6QoAw2/Ti6IF6szY3nup+0JH"));
               }
            });
            if (var2 == null) {
               return Collections.emptyList();
            } else {
               ArrayList var3 = new ArrayList();
               File[] var4 = var2;
               int var5 = var2.length;

               for (int var6 = Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 14); var6 < var5; var6++) {
                  File var7 = var4[var6];
                  String var8 = var7.getName();
                  var3.add(
                     var8.substring(Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-945539372, 2), var8.length() - Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350630, 7))
                  );
               }

               return var3;
            }
         } else {
            return Collections.emptyList();
         }
      }
   }

   public boolean hasOpenSession(Player var1) {
      if ((-1268242904 * -1268242904 - -1268242904 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return var1 != null && this.editorSessions.containsKey(var1.getUniqueId());
      }
   }

   public boolean isHudTextOnlySession(Player var1) {
      if (((72565716 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 25) != 0);
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         return ((var2 != null && !var2.editMode && var2.hudTextOnlyMode
            ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 0)
            : Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1163987339, 23)) != 0);
      }
   }

   public void restoreRuntimeHudSession(Player var1) {
      if ((1148033340 * 1148033340 + 1148033340 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && !var2.editMode && var2.hudTextOnlyMode) {
            this.cameraService.forceStop(var1);
            this.hudService.clearHuds(var1);
            this.hudService.setLegacyHudSession(var1, (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184260787, 10) != 0));
            this.restoreClientHotbarFromServerInventory(var1);
            this.restoreHeldHotbarSlotFromServerInventory(var1);
            this.renderPage(var1, var2, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(459957202, 17) != 0));
         }
      }
   }

   @Override
   public void refreshRenderedPageForHeadSkin(Player var1) {
      if (((-786766228 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.isOnline()) {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null) {
            this.renderPage(var1, var2, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1975164662, 15) != 0));
         }
      }
   }

   public void reanchorRuntimeHudSession(Player var1) {
      if (((-822531219 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && !var2.editMode && var2.hudTextOnlyMode) {
            this.hudService.setLegacyHudSession(var1, (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 23) != 0));
            this.hudService.reanchorRuntimeHuds(var1);
         }
      }
   }

   public String getActiveGuiName(Player var1) {
      if ((1113475261 ^ 1113475261) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return null;
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         return var2 != null ? var2.pageName : null;
      }
   }

   public UiSession getUiSession(Player var1) {
      if ((-811609110 ^ -811609110) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return null;
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         return var2 == null
            ? null
            : new UiSession(
               var2.pageName != null ? var2.pageName : Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="),
               ((var2.hudTextOnlyMode && !var2.editMode
                  ? Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-2017616530, 29)
                  : Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-627510138, 23)) != 0),
               var2.editMode,
               var2.runtimeAutoCloseAfterAnimations
            );
      }
   }

   @Override
   public boolean closeGui(Player var1, String var2) {
      if ((836300729 * 836300729 - 836300729 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 24) != 0);
      } else {
         String var3 = this.normalizePageKey(var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
         if (var3.isBlank()) {
            return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(512809811, 4) != 0);
         } else {
            EditorSession var4 = this.editorSessions.get(var1.getUniqueId());
            if (var4 == null) {
               return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 17) != 0);
            } else if (var4.hudTextOnlyMode && !var4.editMode) {
               boolean var7 = this.removeRuntimeHudStackPage(var4, var3);
               if (!var7) {
                  String var6 = this.normalizePageKey(var4.pageName, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
                  if (!var3.equalsIgnoreCase(var6)) {
                     return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 20) != 0);
                  } else {
                     this.closeGui(var1);
                     return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 5) != 0);
                  }
               } else {
                  this.invalidateOpenAnimationToken(var4, var3);
                  if (var4.rawBlocks != null && !var4.rawBlocks.isEmpty() && var4.renderBlocks != null && !var4.renderBlocks.isEmpty()) {
                     this.renderPage(var1, var4, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(512809811, 4) != 0));
                     return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1578145957, 27) != 0);
                  } else {
                     this.closeGui(var1);
                     return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(460088274, 17) != 0);
                  }
               }
            } else {
               String var5 = this.normalizePageKey(var4.pageName, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
               if (!var3.equalsIgnoreCase(var5)) {
                  return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 26) != 0);
               } else {
                  this.closeGui(var1);
                  return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 20) != 0);
               }
            }
         }
      }
   }

   public boolean removeRuntimeHudStackPage(EditorSession var1, String var2) {
      if ((-815192310 ^ -815192310) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 25) != 0);
      } else {
         String var3 = this.normalizePageKey(var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
         if (var3.isBlank()) {
            return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-826846242, 12) != 0);
         } else {
            ArrayList var4 = new ArrayList();
            ArrayList var5 = new ArrayList();
            List var6 = this.copyBlocks(var1.rawBlocks);

            for (int var7 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-615309680, 20); var7 < var6.size(); var7++) {
               Map var8 = (Map)var6.get(var7);
               if (this.isRuntimeHudStackPageMatch(var8, var3)) {
                  var5.add(var7);
               } else {
                  var4.add(var8);
               }
            }

            int var12 = !var5.isEmpty() ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 19) : Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 14);
            ArrayList var13 = new ArrayList();
            int var9 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-615309680, 20);

            for (Map var11 : this.copyBlocks(var1.renderBlocks)) {
               if (!this.isRuntimeHudStackPageMatch(var11, var3) && !this.isRenderBlockFromRawIndices(var11, var5)) {
                  var13.add(var11);
               } else {
                  var9 = Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184260787, 10);
               }
            }

            if (var12 == 0 && var9 == 0) {
               return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1025619622, 5) != 0);
            } else {
               this.reindexRuntimeHudStackAfterRawRemoval(var1, var13, var5);
               var1.rawBlocks = var4;
               var1.renderBlocks = var13;
               var1.runtimePageKeepOpen.remove(var3);
               var1.runtimePageReopen.remove(var3);
               var1.runtimePageCloseOnDeath.remove(var3);
               var1.runtimePageCloseOnDamage.remove(var3);
               var1.runtimePageCloseOnF.remove(var3);
               var1.runtimePageCloseOnShiftF.remove(var3);
               return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1183213235, 20) != 0);
            }
         }
      }
   }

   public void syncKeybindRegistration(Player var1, EditorSession var2) {
      if (((1441144088 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && this.plugin != null) {
         try {
            KeybindService var3 = this.plugin.getKeybindService();
            if (var3 != null) {
               var3.setKeybindsActive(
                  var1,
                  ((var2 != null ? Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-614261104, 20) : Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1025619622, 5)) != 0),
                  ((var2 != null && !var2.hudTextOnlyMode
                     ? Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1201039027, 24)
                     : Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1283081069, 30)) != 0),
                  var2 == null ? null : var2.runtimeKeybinds.keySet()
               );
            }
         } catch (Throwable var4) {
         }
      }
   }

   @Override
   public void closeGui(Player var1) {
      if ((547226947 * (547226947 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         this.syncKeybindRegistration(var1, null);
         this.clearPersistentVariables(var1);
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null) {
            this.stopRuntimeFrameAnimations(var2);
         }

         if (var2 != null && var2.editMode) {
            this.clearTimelinePreviewOffset(var1, var2);
            this.clearTimelineRuntimeKeyframes(var1, var2);
         }

         if (var2 == null || var2.editMode || !this.applyPageCloseEffect(var1, var2)) {
            this.cameraService.forceStop(var1);
            EditorSession var3 = this.editorSessions.remove(var1.getUniqueId());
            this.playerEditorOpenPages.remove(var1.getUniqueId());
            this.playerEditorWorkingPages.remove(var1.getUniqueId());
            this.clearActivePagesDragPreview(var1);
            if (var3 != null) {
               if (var3.editMode) {
                  this.savePlayerColorPrefs(var1, var3);
               }

               this.clearEditorOverlays(var1);
               this.removeHudById(
                  var1,
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("pzJQUqkZmXZ+4mQMy4LrC5NRHndVzr1Hr/Vtw7oivrmc0ijaDhjCkUDg9MygRWH+ernjER4M66J7aDhx+rzlwyzWbDhWhinBJJo=")
               );
               this.removeHudById(
                  var1,
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "tq8SUAHcl0kWH6Q0QuotRvl5laisX2AHRzGWVJ5mkRxrXUWfms69YqhPgKgJZw9PzitppyoWz0/MZJN7Gc1PsFit0OPYIfH+OhKAZlF28fc="
                  )
               );
               this.removeHudById(
                  var1,
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "whT2CMnxRYIFJStbfFJxDaI3+Xe99S/R5SjsJ6CmHcTwsFg6w0KeS9xff/rSuf1tzJxpK8aopnXa8l7iH027FZB5KPCEVoKC8o3XEg=="
                  )
               );
               this.removeHudById(
                  var1,
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "fIqQq9ZGUfmdwIdU8h2GoZxpL8hrVp6f3pZ5qHIEutLMmrg8jbjspX/5aGLEh9sH5+8hhrWWH/04XWHtCCNWoEyylQMYBTi8licpri3j"
                  )
               );
               this.clearLayersRuntimeHud(var1, var3);
            }

            this.hudService.clearHuds(var1);
            this.restoreClientHotbarFromServerInventory(var1);
         }
      }
   }

   @Override
   public void rememberOpenedEditorPage(UUID var1, String var2) {
      if ((-549960105 ^ -549960105) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && this.isEligibleEditorTabPage(var2)) {
         String var3 = this.normalizePageKey(var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
         if (!var3.isBlank()) {
            List var4 = this.playerEditorOpenPages.computeIfAbsent(var1, var0 -> {
               if ((1899445477 * 1899445477 + 1899445477 & 1) != 0) {
                  throw new IllegalStateException();
               } else {
                  return new ArrayList<>();
               }
            });
            var4.removeIf(var1x -> {
               if ((-550691071 ^ -550691071) != 0) {
                  throw new IllegalStateException();
               } else {
                  return var3.equalsIgnoreCase((String)var1x);
               }
            });
            var4.add(var3);
         }
      }
   }

   @Override
   public void rememberEditorWorkingPage(UUID var1, String var2, List<Map<String, Object>> var3) {
      if ((-2020177197 * (-2020177197 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && this.isEligibleEditorTabPage(var2)) {
         String var4 = this.normalizePageKey(var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
         if (!var4.isBlank()) {
            Map var5 = this.playerEditorWorkingPages.computeIfAbsent(var1, var0 -> {
               if ((537674654 * (537674654 + 1) & 1) != 0) {
                  throw new IllegalStateException();
               } else {
                  return new HashMap<>();
               }
            });
            var5.put(var4, this.copyBlocks(var3));
         }
      }
   }

   public List<Map<String, Object>> resolveEditorWorkingPageSnapshot(UUID var1, String var2) {
      if ((-818540387 * -818540387 + -818540387 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && this.isEligibleEditorTabPage(var2)) {
         String var3 = this.normalizePageKey(var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
         if (var3.isBlank()) {
            return null;
         } else {
            Map var4 = this.playerEditorWorkingPages.get(var1);
            if (var4 != null && !var4.isEmpty()) {
               List var5 = (List)var4.get(var3);
               return var5 == null ? null : this.copyBlocks(var5);
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   public void forgetEditorWorkingPage(UUID var1, String var2) {
      if ((1622883988 * 1622883988 - 1622883988 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         Map<String, ?> var3 = this.playerEditorWorkingPages.get(var1);
         if (var3 != null && !var3.isEmpty()) {
            String var4 = this.normalizePageKey(var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
            if (!var4.isBlank()) {
               var3.remove(var4);
               if (var3.isEmpty()) {
                  this.playerEditorWorkingPages.remove(var1);
               }
            }
         }
      }
   }

   public void syncEditorWorkingPagesToOpenPages(UUID var1, List<String> var2) {
      if ((1774538704 * (1774538704 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         Map<String, ?> var3 = this.playerEditorWorkingPages.get(var1);
         if (var3 != null && !var3.isEmpty()) {
            if (var2 != null && !var2.isEmpty()) {
               HashSet var4 = new HashSet();

               for (String var6 : var2) {
                  if (this.isEligibleEditorTabPage(var6)) {
                     String var7 = this.normalizePageKey(var6, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
                     if (!var7.isBlank()) {
                        var4.add(var7);
                     }
                  }
               }

               var3.entrySet()
                  .removeIf(
                     var1x -> {
                        if ((781173995 ^ 781173995) != 0) {
                           throw new IllegalStateException();
                        } else {
                           return ((!var4.contains(var1x.getKey())
                              ? Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1578145957, 27)
                              : Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1443928229, 27)) != 0);
                        }
                     }
                  );
               if (var3.isEmpty()) {
                  this.playerEditorWorkingPages.remove(var1);
               }
            } else {
               var3.clear();
               this.playerEditorWorkingPages.remove(var1);
            }
         }
      }
   }

   public boolean shouldPersistCurrentPageSnapshot(UUID var1, EditorSession var2) {
      if (((-1850623437 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         String var3 = this.normalizePageKey(var2.pageName, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
         if (!var3.isBlank() && !this.isInternalEditorPageName(var3)) {
            List var4 = this.playerEditorOpenPages.get(var1);
            if (var4 != null && !var4.isEmpty()) {
               for (String var6 : (Iterable<String>) (var4)) {

                  if (var3.equalsIgnoreCase(this.normalizePageKey(var6, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ==")))) {
                     return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(2077873896, 1) != 0);
                  }
               }

               return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 22) != 0);
            } else {
               return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 1) != 0);
            }
         } else {
            return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 25) != 0);
         }
      } else {
         return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 17) != 0);
      }
   }

   @Override
   public void snapshotCurrentEditorPage(UUID var1, EditorSession var2) {
      if (((1969358199 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (this.shouldPersistCurrentPageSnapshot(var1, var2)) {
         this.rememberEditorWorkingPage(var1, var2.pageName, var2.rawBlocks);
      }
   }

   @Override
   public List<String> resolveOpenEditorPagesForPlayer(UUID var1, String var2) {
      if ((-2102449261 * (-2102449261 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return new ArrayList<>();
      } else {
         List var3 = this.playerEditorOpenPages.computeIfAbsent(var1, var0 -> {
            if ((769892162 * 769892162 - 769892162 & 1) != 0) {
               throw new IllegalStateException();
            } else {
               return new ArrayList<>();
            }
         });
         ArrayList var4 = new ArrayList();

         for (String var6 : (Iterable<String>) (var3)) {

            String var7 = this.normalizePageKey(var6, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
            if (!var7.isBlank() && !this.isInternalEditorPageName(var7) && !var4.contains(var7)) {
               var4.add(var7);
            }
         }

         if (this.isEligibleEditorTabPage(var2)) {
            String var9 = this.normalizePageKey(var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
            int var10 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-826846242, 12);

            for (String var8 : (Iterable<String>) (var4)) {

               if (var9.equalsIgnoreCase(var8)) {
                  var10 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1238243059, 24);
                  break;
               }
            }

            if (var10 == 0) {
               var4.add(var9);
            }
         }

         var3.clear();
         var3.addAll(var4);
         return new ArrayList<>(var3);
      }
   }

   public boolean isEligibleEditorTabPage(String var1) {
      if ((290420639 ^ 290420639) != 0) {
         throw new IllegalStateException();
      } else {
         String var2 = this.normalizePageKey(var1, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
         return ((!var2.isBlank() && !this.isInternalEditorPageName(var2)
            ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 27)
            : Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 5)) != 0);
      }
   }

   @Override
   public int resolveInitialActivePagesScrollOffset(List<String> var1, String var2) {
      if ((-1824852514 * -1824852514 + -1824852514 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         int var3 = Math.max(Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 22), var1.size() - Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1863088707, 22));
         String var4 = this.normalizePageKey(var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
         int var5 = var4.isBlank() ? Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(360982057, 25) : var1.indexOf(var4);
         if (var5 < 0) {
            return var3;
         } else {
            return var5 < Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1653717059, 13)
               ? Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(565618237, 13)
               : Math.max(Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 31), Math.min(var3, var5 - Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1221337394, 20)));
         }
      } else {
         return Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 24);
      }
   }

   @Override
   public List<Map<String, Object>> buildShellBlocksWithActivePages(
      List<Map<String, Object>> var1, Map<String, YamlConfiguration> var2, List<String> var3, int var4, String var5
   ) {
      if ((2032616690 * (2032616690 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         List var6 = this.copyBlocks(var1);
         ArrayList var7 = var3 == null ? new ArrayList() : new ArrayList(var3);
         int var8 = Math.max(Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-556877997, 4), var7.size() - Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1843498824, 19));
         int var9 = Math.max(Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(512809811, 4), Math.min(var8, var4));
         ArrayList var10 = new ArrayList();
         String var11 = this.normalizePageKey(var5, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
         LinkedHashMap var12 = this.resolveActivePageDisplayNames(var7);
         int var13 = Math.min(
            Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(2123785907, 27), Math.max(Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(512809811, 4), var7.size() - var9)
         );

         for (int var14 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1975131894, 15); var14 <= var13; var14++) {
            String var15 = (String)var7.get(var9 + var14 - Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(512809827, 4));
            String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1840877384, 19)];
            var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 15)] = (String)var12.get(
               this.normalizePageKey(var15, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="))
            );
            var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-384977359, 8)] = var15;
            String var16 = this.firstNonBlank(var10001);
            var10.add(this.buildActivePageComponentEntry(var14, var15, var16, var15.equalsIgnoreCase(var11)));
         }

         this.replaceActivePagesChildren(var6, var10);
         return this.resolveRenderableBlocksNoCopy(var6, var2);
      }
   }

   public Map<String, Object> buildActivePageComponentEntry(int var1, String var2, String var3, boolean var4) {
      if ((-2073830626 * (-2073830626 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         HashMap var5 = new HashMap();
         var5.put(
            Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("IISHul9KKD/CzQ2r2X/Kn+oIQ15+sA6PfHvcRo0q3l0DbYW8El+Y+pe13byiQQ=="),
            Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("/wm/XtrpV0ywTxaIYY4PZUnbhWU8SBKVxK6e6Kf0Cyzr71QbvfykZfbmYsba6NpW0nsPdTuxdBqC+NBUHBw=")
         );
         var5.put(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("d56YQdh6CWwACRPLaSV4WkUJWbdVKvrbmatzX9J013Y="), Integer.toString(var1));
         var5.put(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("JcL+l6vxGOxLrrMC/9t+8AM42SxI2Ch3p2ndTe5baGJk7gsbyDJ12w=="), var4);
         String var10001 = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("dEPMbWSX7qGCGxadTDrd52fzchRsEFcdQK2KnFC1IYN5H5c7YcNIP0rrcx4=");
         String[] var10004 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-613212528, 20)];
         var10004[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 2)] = var3;
         var10004[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 11)] = var2;
         var5.put(var10001, this.formatActivePageDisplayName(this.firstNonBlank(var10004)));
         return var5;
      }
   }

   public LinkedHashMap<String, String> resolveActivePageDisplayNames(List<String> var1) {
      if (((-1465238658 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         LinkedHashMap var2 = new LinkedHashMap();
         if (var1 != null && !var1.isEmpty()) {
            Map var3 = this.loadYamlFolder(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("mR+wgJPFzfhM3VHNmOv51W98mNSlnmnRame0olfzd/Hlu6/XZI2W7YkkFx4nZl2FsuKddIzM9mA="), new ArrayList<>()
            );

            for (String var5 : var1) {
               String var6 = this.normalizePageKey(var5, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
               if (!var6.isBlank() && !var2.containsKey(var6)) {
                  var2.put(var6, this.resolveActivePageDisplayName(var6, var3));
               }
            }

            return var2;
         } else {
            return var2;
         }
      }
   }

   public String resolveActivePageDisplayName(String var1, Map<String, YamlConfiguration> var2) {
      if ((-128754249 * -128754249 + -128754249 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var3 = this.normalizePageKey(var1, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
         if (var3.isBlank()) {
            return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ==");
         } else {
            String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1186358963, 20)];
            var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(459957202, 17)] = this.formatUntitledPageDisplayName(var3);
            var10001[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1188456115, 22)] = var3;
            String var4 = this.firstNonBlank(var10001).trim();
            if (var4.isBlank()) {
               var4 = var3;
            }

            String var5 = this.resolveExistingPageMapKey(var2, var3);
            if (var5.isBlank()) {
               return var4;
            } else {
               YamlConfiguration var6 = (YamlConfiguration)var2.get(var5);
               var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184245427, 14)];
               var10001[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 19)] = var6 == null
                  ? null
                  : var6.getString(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("GBzRZjyGCADtgwiihPuU2YVcbwa66JR3ng2fLLCdPsRvxS/4"));
               String var7 = this.firstNonBlank(var10001).trim();
               return var7.isBlank() ? var4 : var7;
            }
         }
      }
   }

   public void replaceActivePagesChildren(List<Map<String, Object>> var1, List<Map<String, Object>> var2) {
      if ((-127400747 * -127400747 - -127400747 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         for (Map var4 : var1) {
            if (var4 != null && !var4.isEmpty()) {
               String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261683, 7)];
               var10001[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(2067455311, 6)] = this.stringValue(
                  var4.get(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("d56YQdh6CWwACRPLaSV4WkUJWbdVKvrbmatzX9J013Y="))
               );
               String var5 = this.firstNonBlank(var10001);
               if (Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("xqDbb8FU8334rgQalHAngCe271fu8oblrxOdeIuADgpY/HuYBHhS7/oJNSLJWtcCy6J7qQ==").equalsIgnoreCase(var5)) {
                  var4.put(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("vMmtaWmVfs/9cYdhwkLrfrB820yiSUZVaE9wC6a63oNMi4DaZnH0neHlWFQ="), new ArrayList(var2));
                  return;
               }

               if (var4.get(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("vMmtaWmVfs/9cYdhwkLrfrB820yiSUZVaE9wC6a63oNMi4DaZnH0neHlWFQ=")) instanceof List var7) {
                  ArrayList var8 = new ArrayList();

                  for (Object var10 : var7) {
                     if (var10 instanceof Map var11) {
                        var8.add(var11);
                     }
                  }

                  this.replaceActivePagesChildren(var8, var2);
               }
            }
         }
      }
   }

   public String formatActivePageDisplayName(String var1) {
      if ((1613942365 * 1613942365 + 1613942365 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1628015173, 31)];
         var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-627510138, 23)] = this.formatUntitledPageDisplayName(var1);
         var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-2032461580, 15)] = var1;
         String var2 = this.firstNonBlank(var10001).trim();
         return var2.length() <= Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1628015182, 31)
            ? var2
            : Xqedii_207o52np10lcx(var2.substring(Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 4), Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(2067455631, 6)));
      }
   }

   @Override
   public String resolveNextUntitledPageKey(Player var1, EditorSession var2) {
      if ((-1275529447 * -1275529447 + -1275529447 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         LinkedHashSet var3 = new LinkedHashSet();

         for (String var5 : this.getGuiNames()) {
            String var6 = this.normalizePageKey(var5, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
            if (!var6.isBlank()) {
               var3.add(var6);
            }
         }

         if (var1 != null) {
            for (String var12 : this.resolveOpenEditorPagesForPlayer(var1.getUniqueId(), var2 == null ? null : var2.pageName)) {
               String var7 = this.normalizePageKey(var12, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
               if (!var7.isBlank()) {
                  var3.add(var7);
               }
            }
         }

         String var9 = this.normalizePageKey(
            var2 == null ? null : var2.pageName, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ==")
         );
         if (!var9.isBlank()) {
            var3.add(var9);
         }

         for (int var11 = Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 15); var11 < Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(697256358, 27); var11++) {
            String var13 = var11 == 0
               ? Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("bmiGcVDS2LjTenodXTzQQZ8g13ZHNzev8PplHul+kg0JzAnBQaCHssyqKhQ=")
               : Xqedii_3dfnjlxpz1k41(var11);
            if (!this.isInternalEditorPageName(var13) && !this.containsPageKeyIgnoreCase(var3, var13)) {
               return var13;
            }
         }

         return Xqedii_2gqdoxx2e5o2d(System.currentTimeMillis());
      }
   }

   public boolean containsPageKeyIgnoreCase(Set<String> var1, String var2) {
      if (((227776702 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty() && var2 != null && !var2.isBlank()) {
         for (String var4 : var1) {
            String[] var10002 = new String[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1578145957, 27)];
            var10002[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 0)] = var4;
            if (var2.equalsIgnoreCase(this.firstNonBlank(var10002))) {
               return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184262067, 8) != 0);
            }
         }

         return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 6) != 0);
      } else {
         return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1891078743, 3) != 0);
      }
   }

   @Override
   public String formatUntitledPageDisplayName(String var1) {
      if ((-1279820683 * -1279820683 - -1279820683 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var2 = this.normalizePageKey(var1, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
         if (var2.isBlank()) {
            return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ==");
         } else {
            Matcher var3 = Xqedii_l2une6g.Xqedii_1fih21c.matcher(var2);
            if (!var3.matches()) {
               return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ==");
            } else {
               String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 24)];
               var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1505943114, 29)] = var3.group(Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(2051239308, 6));
               String var4 = this.firstNonBlank(var10001);
               if (var4.isBlank()) {
                  return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("jJ1QJVIMB3qvmTup6DjPxJoJ6ZfC8iJ8HiUJFCeZUaoNAT9Gvv60JfcUA00=");
               } else {
                  try {
                     int var5 = Integer.parseInt(var4);
                     return var5 <= 0
                        ? Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("jJ1QJVIMB3qvmTup6DjPxJoJ6ZfC8iJ8HiUJFCeZUaoNAT9Gvv60JfcUA00=")
                        : Xqedii_126rs2jz70sg8(var5);
                  } catch (NumberFormatException var6) {
                     return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("jJ1QJVIMB3qvmTup6DjPxJoJ6ZfC8iJ8HiUJFCeZUaoNAT9Gvv60JfcUA00=");
                  }
               }
            }
         }
      }
   }

   public boolean isUntitledPageKey(String var1) {
      if (((767631245 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String var2 = this.normalizePageKey(var1, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
         return !var2.isBlank() && Xqedii_l2une6g.Xqedii_1fih21c.matcher(var2).matches();
      }
   }

   @Override
   public boolean handleActivePagesClick(Player var1, EditorSession var2, GuiService.ClickType var3, double var4, double var6) {
      if ((1293129061 * (1293129061 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var2 == null || !var2.editMode || !var2.previewMode) {
         return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 3) != 0);
      } else if (this.isPreviewPointerInteractionActive(var2)) {
         return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1283081069, 30) != 0);
      } else {
         int var8 = this.resolveHoveredActivePageCloseSlot(var2, var4, var6);
         int var9 = this.resolveHoveredActivePageSlot(var2, var4, var6);
         if (var3 == GuiService.ClickType.RIGHT) {
            return ((var8 <= 0 && var9 <= 0 ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 6) : Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261683, 7)) != 0);
         } else if (var3 != GuiService.ClickType.LEFT) {
            return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 14) != 0);
         } else {
            int var10 = var2.activePagesArmedPageKey != null && !var2.activePagesArmedPageKey.isBlank()
               ? Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1938144403, 30)
               : Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 28);
            if (!var2.activePagesReorderActive && (var10 == 0 || !this.isLeftHeld(var2))) {
               if (var10 != 0) {
                  this.resetActivePagesDragState(var2);
               }

               int var11 = var8 <= 0 && var9 <= 0 ? Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 15) : Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1818481179, 25);
               if (var11 == 0) {
                  return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-360982058, 25) != 0);
               } else {
                  var2.lastLeftClickTick = Xqedii_38o2zes.Xqedii_316oplq();
                  this.resetActivePagesDragState(var2);
                  if (var8 > 0) {
                     return this.handleActivePageCloseClick(var1, var2, var8);
                  } else {
                     String var12 = this.resolveOpenPageAtVisibleSlot(var2, var9);
                     if (var12 != null && !var12.isBlank()) {
                        var2.activePagesArmedPageKey = var12;
                        var2.activePagesReorderPageKey = var12;
                        var2.activePagesArmedStartHitX = var4;
                        var2.activePagesArmedStartHitY = var6;
                        var2.activePagesVisibleHoveredSlot = var9;
                        var2.activePagesReorderInsertIndex = Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(-1184261812, 25);
                        return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1183213235, 20) != 0);
                     } else {
                        return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 24) != 0);
                     }
                  }
               }
            } else {
               var2.lastLeftClickTick = Xqedii_38o2zes.Xqedii_316oplq();
               return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(460088274, 17) != 0);
            }
         }
      }
   }

   @Override
   public boolean handleActivePagesCursorMove(Player var1, EditorSession var2, double var3, double var5) {
      if (((-1807538657 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var2 == null || !var2.editMode || !var2.previewMode) {
         return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 9) != 0);
      } else if (this.isPreviewPointerInteractionActive(var2)) {
         if (var2.activePagesReorderActive || var2.activePagesArmedPageKey != null && !var2.activePagesArmedPageKey.isBlank()) {
            this.clearActivePagesDragPreview(var1);
            this.resetActivePagesDragState(var2);
         }

         return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 20) != 0);
      } else {
         int var7 = this.resolveHoveredActivePageSlot(var2, var3, var5);
         var2.activePagesVisibleHoveredSlot = var7;
         int var8 = var2.activePagesArmedPageKey != null && !var2.activePagesArmedPageKey.isBlank()
            ? Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1734058479, 11)
            : Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 24);
         boolean var9 = this.isLeftHeld(var2);
         if (!var9) {
            if (var2.activePagesReorderActive) {
               this.applyActivePagesReorderDrop(var1, var2);
               this.clearActivePagesDragPreview(var1);
               this.resetActivePagesDragState(var2);
               return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1316009875, 27) != 0);
            } else if (var8 != 0 && !var2.activePagesReorderActive) {
               String var15 = var2.activePagesArmedPageKey;
               this.clearActivePagesDragPreview(var1);
               this.resetActivePagesDragState(var2);
               return this.openActiveEditorPage(var1, var15);
            } else if (var8 != 0) {
               this.clearActivePagesDragPreview(var1);
               this.resetActivePagesDragState(var2);
               return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184294579, 15) != 0);
            } else {
               this.clearActivePagesDragPreview(var1);
               return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 9) != 0);
            }
         } else if (var8 == 0) {
            return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-360982058, 25) != 0);
         } else {
            if (!var2.activePagesReorderActive) {
               double var10 = Math.abs(var3 - var2.activePagesArmedStartHitX);
               double var12 = Math.abs(var5 - var2.activePagesArmedStartHitY);
               if (var10 >= Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(218967722789828770L, 36))
                  || var12 >= Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(335064554431403233L, 14))) {
                  var2.activePagesReorderActive = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261683, 7) != 0);
                  var2.activePagesReorderInsertIndex = this.resolveActivePagesDropTarget(var2, var3).Xqedii_13fclmd;
                  this.startActivePagesReorderReleaseWatch(var1, var2);
               }
            }

            if (!var2.activePagesReorderActive) {
               return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 20) != 0);
            } else {
               EditorPagePreferencesPart.ActivePagesDropTarget var14 = this.resolveActivePagesDropTarget(var2, var3);
               var2.activePagesReorderInsertIndex = var14.Xqedii_13fclmd;
               this.renderActivePagesDragPreview(var1, var2, var3, var5, var14);
               return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(230044137, 16) != 0);
            }
         }
      }
   }

   public boolean handleActivePagesScroll(Player var1, EditorSession var2, double var3, double var5, int var7) {
      if (((164663454 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var7 != 0 && var2.editMode && var2.previewMode) {
         int var8 = this.resolveHoveredActivePageSlot(var2, var3, var5);
         int var9 = this.resolveHoveredActivePageCloseSlot(var2, var3, var5);
         if (var8 <= 0 && var9 <= 0) {
            return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-160056674, 7) != 0);
         } else {
            List var10 = this.resolveOpenEditorPagesForPlayer(var1.getUniqueId(), var2.pageName);
            if (var10.size() <= Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1183475379, 18)) {
               return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-360982058, 25) != 0);
            } else {
               int var11 = Math.max(Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1864674530, 12), var10.size() - Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1183475379, 18));
               int var12 = var7 > 0 ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 7) : Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(160056673, 7);
               int var13 = Math.max(Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 24), Math.min(var11, var2.activePagesScrollOffset + var12));
               if (var13 == var2.activePagesScrollOffset) {
                  return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1038936948, 0) != 0);
               } else {
                  var2.activePagesScrollOffset = var13;
                  var2.openEditorPages.clear();
                  var2.openEditorPages.addAll(var10);
                  this.rerenderActivePagesShell(var1, var2);
                  return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261819, 3) != 0);
               }
            }
         }
      } else {
         return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-2032494348, 15) != 0);
      }
   }

   public int resolveHoveredActivePageSlot(EditorSession var1, double var2, double var4) {
      if (((-495833864 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         int var6 = this.resolveVisibleActivePagesCount(var1);

         for (int var7 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(519468474, 31); var7 <= var6; var7++) {
            String var8 = Xqedii_pc6ttxv9rjcp(var7);
            if (this.isInsideShellBlock(var1, var8, var2, var4)) {
               return var7;
            }
         }

         return Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-192488936, 7);
      }
   }

   public int resolveHoveredActivePageCloseSlot(EditorSession var1, double var2, double var4) {
      if ((1276211495 * (1276211495 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         int var6 = this.resolveVisibleActivePagesCount(var1);

         for (int var7 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-614261104, 20); var7 <= var6; var7++) {
            String var8 = Xqedii_225iwv8xx4bji(var7);
            if (this.isInsideShellBlock(var1, var8, var2, var4)) {
               return var7;
            }
         }

         return Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(-1184261812, 4);
      }
   }

   public int resolveVisibleActivePagesCount(EditorSession var1) {
      if ((1099249985 * (1099249985 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.openEditorPages != null && !var1.openEditorPages.isEmpty()) {
         int var2 = var1.openEditorPages.size() - Math.max(Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 0), var1.activePagesScrollOffset);
         return Math.max(Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 25), Math.min(Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(921749412, 18), var2));
      } else {
         return Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 26);
      }
   }

   public String resolveOpenPageAtVisibleSlot(EditorSession var1, int var2) {
      if (((-42592513 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.openEditorPages != null && var2 > 0) {
         int var3 = var1.activePagesScrollOffset + var2 - Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1854389661, 21);
         return var3 >= 0 && var3 < var1.openEditorPages.size() ? var1.openEditorPages.get(var3) : null;
      } else {
         return null;
      }
   }

   public boolean handleActivePageCloseClick(Player var1, EditorSession var2, int var3) {
      if (((1433487839 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         this.clearActivePagesDragPreview(var1);
         this.resetActivePagesDragState(var2);
         UUID var4 = var1.getUniqueId();
         String var5 = this.resolveOpenPageAtVisibleSlot(var2, var3);
         if (var5 != null && !var5.isBlank()) {
            List var6 = this.resolveOpenEditorPagesForPlayer(var4, var2.pageName);
            int var7 = var6.indexOf(var5);
            if (var7 < 0) {
               return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 11) != 0);
            } else {
               boolean var8 = var5.equalsIgnoreCase(
                  this.normalizePageKey(var2.pageName, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="))
               );
               var6.remove(var7);
               this.playerEditorOpenPages.put(var4, new ArrayList<>(var6));
               this.forgetEditorWorkingPage(var4, var5);
               if (var6.isEmpty()) {
                  this.playerEditorOpenPages.remove(var4);
                  this.playerEditorWorkingPages.remove(var4);
                  return this.openGui(
                     var1,
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("r1w46I6vPG2hztYyiUeJq3xOspPJmAxTWcURcsSo4+5lIYboG7zuEVhLXV+ikRVytBE="),
                     (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 0) != 0),
                     (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1215148431, 11) != 0)
                  );
               } else {
                  this.syncEditorWorkingPagesToOpenPages(var4, var6);
                  if (var8) {
                     int var11 = Math.max(Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1283081069, 30), var7 - Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(2077873896, 1));
                     String var10 = (String)var6.get(var11);
                     return this.switchActiveEditorPageInPlace(var1, var2, var10);
                  } else {
                     var2.openEditorPages.clear();
                     var2.openEditorPages.addAll(var6);
                     int var9 = Math.max(
                        Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1407110839, 28), var6.size() - Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1379338317, 18)
                     );
                     var2.activePagesScrollOffset = Math.max(Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 11), Math.min(var9, var2.activePagesScrollOffset));
                     this.rerenderActivePagesShell(var1, var2);
                     return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(512809827, 4) != 0);
                  }
               }
            }
         } else {
            return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 12) != 0);
         }
      }
   }

   public boolean openActiveEditorPage(Player var1, String var2) {
      if ((1435051912 ^ 1435051912) != 0) {
         throw new IllegalStateException();
      } else {
         String var3 = this.normalizePageKey(var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
         if (!var3.isBlank() && !this.isInternalEditorPageName(var3)) {
            EditorSession var4 = this.editorSessions.get(var1.getUniqueId());
            if (var4 != null && var4.editMode && var4.previewMode) {
               return var3.equalsIgnoreCase(
                     this.normalizePageKey(var4.pageName, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="))
                  ) || this.switchActiveEditorPageInPlace(var1, var4, var3);
            } else {
               return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1505943114, 29) != 0);
            }
         } else {
            return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 17) != 0);
         }
      }
   }

   public boolean switchActiveEditorPageInPlace(Player var1, EditorSession var2, String var3) {
      if ((735269497 ^ 735269497) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         String var4 = this.normalizePageKey(var3, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
         if (!var4.isBlank() && !this.isInternalEditorPageName(var4)) {
            UUID var5 = this.findActiveEditorOwnerForPage(var4, var1.getUniqueId());
            if (var5 != null) {
               return this.notifyEditorPageLocked(var1, var4, var5);
            } else {
               UUID var6 = var1.getUniqueId();
               this.snapshotCurrentEditorPage(var6, var2);
               ArrayList var7 = new ArrayList();
               Map var8 = this.loadYamlFolder(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("mR+wgJPFzfhM3VHNmOv51W98mNSlnmnRame0olfzd/Hlu6/XZI2W7YkkFx4nZl2FsuKddIzM9mA="), var7
               );
               if (!var7.isEmpty()) {
                  this.reportYamlIssues(var7);
                  this.sendEditorError(
                     var1,
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "AjWv5/Ac2NJwDBXKWO9dh0lbeqivvmELrt+hqgApwXH0+5fnUhB+S85X5c/LQgRiA5wKJFdnISktTC+TYO5/ecO80spu6s5CGLhIADgeDPFTNS/p/ljb4I/Vtp2GS8Xa10Qj1TIh15tUkKWg+34CAX4oHmtgLZKophIDhE9qpu2KH5bIx2V5vpnTvOL1Vnql"
                     ),
                     new Object[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(565618237, 13)]
                  );
                  return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 25) != 0);
               } else {
                  String var9 = this.resolveExistingPageMapKey(var8, var4);
                  YamlConfiguration var10 = var9.isBlank() ? null : (YamlConfiguration)var8.get(var9);
                  List var11 = this.resolveEditorWorkingPageSnapshot(var6, var4);
                  if (var10 == null && var11 == null && !this.isUntitledPageKey(var4)) {
                     String var10002 = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "XH8S+LjS6SEqgxKN6vhL1yz9jTNa0vLQoSflYzB3zKrNKOmLS5gp9JWreTjX36yIa+nuQT6cWRhp1LTlrYfYj+UzX60Vps2LGigWqw=="
                     );
                     Object[] var10003 = new Object[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261683, 7)];
                     var10003[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 5)] = var4;
                     this.sendEditorError(var1, var10002, var10003);
                     return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-556877997, 4) != 0);
                  } else {
                     this.finishLayersReorderDrag(var1, var2, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-721964115, 26) != 0));
                     this.resetActivePagesDragState(var2);
                     this.clearActivePagesDragPreview(var1);
                     this.stopCursorToolDrag(var2);
                     this.resetTransientPanelsForPageSwitch(var1, var2);
                     var2.scrollOffsets.clear();
                     var2.scrollGlides.clear();
                     var2.pageName = var4;
                     var2.pageFile = var10 == null
                        ? null
                        : new File(
                           new File(
                              this.plugin.getDataFolder(),
                              Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("mR+wgJPFzfhM3VHNmOv51W98mNSlnmnRame0olfzd/Hlu6/XZI2W7YkkFx4nZl2FsuKddIzM9mA=")
                           ),
                           Xqedii_3u977853ym54p(var4)
                        );
                     var2.yaml = var10 == null ? new YamlConfiguration() : this.cloneYamlConfiguration(var10);
                     if (var10 == null) {
                        var2.yaml
                           .set(
                              Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("GBzRZjyGCADtgwiihPuU2YVcbwa66JR3ng2fLLCdPsRvxS/4"), this.formatUntitledPageDisplayName(var4)
                           );
                     }

                     this.ensurePageDisplayName(var2);
                     int var12 = var11 == null ? Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1038936948, 0) : Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 1);
                     var2.rawBlocks = (List<Map<String, Object>>)(var12 != 0
                        ? (
                           var10 == null
                              ? new ArrayList<>()
                              : this.copyBlocks(var10.getList(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("lTEZfjwUp/iz3Rn6YvBHWgk26wqC/MAkkDG/luUkff7LlA+DwDHwZg==")))
                        )
                        : var11);
                     var2.rawBlocks = this.expandCompactImageBlocks(var2.rawBlocks);
                     if (var12 != 0) {
                        this.applyImageLayoutOffsetFormatToLoadedBlocks(var10, var2.rawBlocks);
                     }

                     var2.yaml
                        .set(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("lTEZfjwUp/iz3Rn6YvBHWgk26wqC/MAkkDG/luUkff7LlA+DwDHwZg=="), this.copyBlocks(var2.rawBlocks));
                     var2.renderBlocks = this.resolveRenderableBlocks(var2.rawBlocks, var2.components, var2);
                     this.rememberEditorWorkingPage(var6, var4, var2.rawBlocks);
                     this.restoreEditorLayerStateMetadata(var2);
                     var2.spawnOpacityRetoggledTargetIds.clear();
                     var2.pendingSpawnOpacityRetoggleTargetIds.clear();
                     var2.spawnOpacityRetoggleTaskQueued = (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1215146383, 11) != 0);
                     List var13 = this.resolveOpenEditorPagesForPlayer(var6, var4);
                     var2.openEditorPages.clear();
                     var2.openEditorPages.addAll(var13);
                     int var14 = Math.max(
                        Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-615309680, 20), var13.size() - Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1129738854, 22)
                     );
                     int var15 = var13.indexOf(var4);
                     if (var15 >= 0) {
                        if (var15 < var2.activePagesScrollOffset) {
                           var2.activePagesScrollOffset = var15;
                        } else if (var15 >= var2.activePagesScrollOffset + Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(519468473, 31)) {
                           var2.activePagesScrollOffset = var15 - Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121153638, 16);
                        }
                     }

                     var2.activePagesScrollOffset = Math.max(Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1215146383, 11), Math.min(var14, var2.activePagesScrollOffset));
                     this.rerenderActivePagesShell(
                        var1, var2, (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 25) != 0), (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1854389661, 21) != 0)
                     );
                     this.renderPreviewPageOutline(var1, var2);
                     this.rerenderEditableContent(var1, var2);
                     var2.rightSidebarTab = EditorSidebarTab.LAYERS;
                     var2.rightSidebarHoverTab = null;
                     this.rerenderEditorShellForPageSwitch(var1, var2);
                     this.setRightSidebarTab(var1, var2, EditorSidebarTab.LAYERS, (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 23) != 0));
                     this.renderEditorTransparencyOverlay(var1, var2);
                     this.updatePageInfoReadout(var1, var2);
                     double var16 = var2.cursorX + var2.hitboxOffsetX;
                     double var18 = var2.cursorY + var2.hitboxOffsetY;
                     this.updateEditorNavbarHover(var1, var2, var16, var18);
                     this.updateSidebarTabHover(var1, var2, var16, var18);
                     this.updateCursorPositionReadout(var1, var2, var16, var18);
                     return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 5) != 0);
                  }
               }
            }
         } else {
            return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-627510138, 23) != 0);
         }
      } else {
         return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 28) != 0);
      }
   }

   public void applyActivePagesReorderDrop(Player var1, EditorSession var2) {
      if ((-43897243 ^ -43897243) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.activePagesReorderActive) {
         List var3 = this.resolveOpenEditorPagesForPlayer(var1.getUniqueId(), var2.pageName);
         if (var3.size() > Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184263859, 11)) {
            String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 11)];
            var10001[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 24)] = var2.activePagesReorderPageKey;
            String var4 = this.firstNonBlank(var10001);
            if (!var4.isBlank()) {
               int var5 = var3.indexOf(var4);
               if (var5 >= 0) {
                  int var6 = var2.activePagesReorderInsertIndex;
                  if (var6 < 0) {
                     var6 = var5;
                  }

                  var6 = Math.max(Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 29), Math.min(var3.size(), var6));
                  if (var6 > var5) {
                     var6--;
                  }

                  if (var6 != var5) {
                     String var7 = (String)var3.remove(var5);
                     var3.add(var6, var7);
                     this.playerEditorOpenPages.put(var1.getUniqueId(), new ArrayList<>(var3));
                     var2.openEditorPages.clear();
                     var2.openEditorPages.addAll(var3);
                     int var8 = Math.max(Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1833728579, 22), var3.size() - Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350245, 31));
                     var2.activePagesScrollOffset = Math.max(Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 24), Math.min(var8, var2.activePagesScrollOffset));
                     this.rerenderActivePagesShell(var1, var2);
                  }
               }
            }
         }
      }
   }

   public void startActivePagesReorderReleaseWatch(Player var1, EditorSession var2) {
      if ((197401348 ^ 197401348) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.activePagesReorderActive) {
         int var3 = var2.activePagesReorderReleaseWatchToken = var2.activePagesReorderReleaseWatchToken + Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1038936948, 0);
         Xqedii_38o2zes.Xqedii_j3mggbv(
            this.plugin,
            var1,
            Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(5149447249402688282L, 19),
            Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4462764914402205126L, 54),
            var4 -> {
               if ((-800992057 * (-800992057 + 1) & 1) != 0) {
                  throw new IllegalStateException();
               } else if (!var1.isOnline()) {
                  var4.cancel();
               } else {
                  EditorSession var5 = this.editorSessions.get(var1.getUniqueId());
                  if (var5 == null || var5 != var2 || var3 != var5.activePagesReorderReleaseWatchToken) {
                     var4.cancel();
                  } else if (!var5.activePagesReorderActive) {
                     var4.cancel();
                  } else if (!this.isLeftHeld(var5)) {
                     this.applyActivePagesReorderDrop(var1, var5);
                     this.clearActivePagesDragPreview(var1);
                     this.resetActivePagesDragState(var5);
                     var4.cancel();
                  }
               }
            }
         );
      }
   }

   public EditorPagePreferencesPart.ActivePagesDropTarget resolveActivePagesDropTarget(EditorSession var1, double var2) {
      if ((-1590795701 * -1590795701 - -1590795701 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         ArrayList var4 = new ArrayList();
         int var5 = this.resolveVisibleActivePagesCount(var1);

         for (int var6 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 31); var6 <= var5; var6++) {
            EditorRect var7 = this.findShellBlockRect(var1, Xqedii_25zngpotijaal(var6));
            if (var7 != null) {
               var4.add(var7);
            }
         }

         if (var4.isEmpty()) {
            return new EditorPagePreferencesPart.ActivePagesDropTarget(
               Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 17),
               var2,
               Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444750515892723142L, 63)),
               Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149447249402688283L, 33))
            );
         } else {
            int var11 = var4.size();

            for (int var12 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 17); var12 < var4.size(); var12++) {
               EditorRect var8 = (EditorRect)var4.get(var12);
               double var9 = var8.x + var8.width / Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149447249402786587L, 17));
               if (var2 < var9) {
                  var11 = var12;
                  break;
               }
            }

            double var13;
            if (var11 <= 0) {
               var13 = ((EditorRect)var4.getFirst()).x;
            } else if (var11 >= var4.size()) {
               EditorRect var14 = (EditorRect)var4.getLast();
               var13 = var14.x + var14.width;
            } else {
               EditorRect var15 = (EditorRect)var4.get(var11 - Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1201039027, 24));
               EditorRect var10 = (EditorRect)var4.get(var11);
               var13 = (var15.x + var15.width + var10.x) / Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149376880658510619L, 48));
            }

            EditorRect var16 = (EditorRect)var4.getFirst();
            int var17 = Math.max(
               Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 25), Math.min(var1.openEditorPages.size(), var1.activePagesScrollOffset + var11)
            );
            return new EditorPagePreferencesPart.ActivePagesDropTarget(var17, var13, var16.y, var16.height);
         }
      }
   }

   public void renderActivePagesDragPreview(Player var1, EditorSession var2, double var3, double var5, EditorPagePreferencesPart.ActivePagesDropTarget var7) {
      if (((-1011471276 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var7 != null && var2.activePagesReorderActive) {
         String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261819, 3)];
         var10001[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 12)] = var2.activePagesReorderPageKey;
         String var8 = this.firstNonBlank(var10001);
         if (var8.isBlank()) {
            this.clearActivePagesDragPreview(var1);
         } else {
            boolean var9 = var8.equalsIgnoreCase(
               this.normalizePageKey(var2.pageName, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="))
            );
            String var10 = var9
               ? Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("vFKbdC+trMfMvTdpemUT5SaLMmCun2FdAp8tbt0I4twdGWMVCU2goA==")
               : Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("/PxdxVUeN3+036BgV6VC9G/t84rfe4wnAyTGA5d+vfjOdx84GkND8w==");
            String var11 = var9
               ? Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("eckXbwmMImS9GFDtXxMyj083x+bnrE/TWBO4dhrnnszPyLQvPUWvFg==")
               : Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("XIb/t3L1J52KO/GaqUrsFHe9rWHmjko/PkvPIT3vX5YEEI5ewJPS6w==");
            double var12 = var3 - Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(4639056983768301332L, 33));
            double var14 = var5 - Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-5192691419721870543L, 37));
            String var16 = this.withEditorUiFont(this.withHexPrefix(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("1csdZdlqGZsBgfoKsc+A0wPe51uaJXZSdd9F5xKx"), var10));
            this.renderRoundedBlock(
               var1,
               new RoundedBox(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "A1M7laYUxLlARVkKEgpYnjis8WzR5dLtaAh6YeodRJ6Rk1asCpD095NTjd9Th4oQ4HSYLfaripLzYNCaV78JP+7nwiAhxfojwtLASyN5vcXmTjNLsZ7e9Uxln0RpR9wzMgPzBg=="
                  ),
                  var12,
                  var14,
                  Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149446856587211547L, 39)),
                  Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444750532980186566L, 36)),
                  Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(6379767796665779998L, 12))
               ),
               var16,
               Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-682460199, 17),
               Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(5532355205492490249L, 23)),
               RoundedCornerGlyphs.uniform(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("fF0YQ/DJWhkbpYTcMDNZn3JDAtVPUdqBcc5QX+Uh")),
               new RoundedCornerOffsets(
                  Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(1751741919992464656L, 39)),
                  Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(6892726828661192703L, 6)),
                  Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444581122382568902L, 48)),
                  Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(5896321611620146168L, 5)),
                  Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(-3625700016355205767L, 8)),
                  Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-4432809726308374400L, 42)),
                  Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(8899101844914602178L, 50)),
                  Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149447249427845915L, 25))
               ),
               new RoundedTransform(
                  Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149447249402688283L, 56)),
                  (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 26) != 0),
                  (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 25) != 0),
                  var12 + Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(6034949504510164693L, 0)),
                  var14 + Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(3544030590418623958L, 6))
               ),
               TextAlignment.CENTER
            );
            double var17 = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(-7732982876517098170L, 9));
            double var19 = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4435701810074033606L, 55));
            double var21 = this.toInternalTextTopY(var14 + Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-3077655495611650565L, 4)), var19);
            HudPositionCalculator.Placement var23 = this.positionCalculator
               .calculateBoxPlacement(
                  this.applyTextAlignmentOffset(
                     var12 + Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-168138279568073858L, 49)), var17, TextAlignment.LEFT
                  ),
                  var21,
                  Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444768316156091846L, 46)),
                  var17,
                  var19
               );
            this.upsertHud(
               var1,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                  "EC/D07zA+u283BSAQyWqkmH5Hw+4eWhCqblmQdiYEm/s8zMMoHN1wPG+82PWPEwHHpnsZJ29ZUA45tJghSBGYyuXmEvYc+R7G5kQUTWs08lDWt6muAEo/arV6Sa3o1hwpcNiFc5eejiWUDWxi3P4HA=="
               ),
               var23.location(),
               var23.scale(),
               this.withEditorUiFont(this.withHexPrefix(this.formatActivePageDisplayName(var8), var11)),
               Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261455, 2),
               TextAlignment.LEFT
            );
            double var24 = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149467100741530395L, 45));
            double var26 = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4445031166235712966L, 50));
            double var28 = this.toInternalTextTopY(var14 + Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(8061361234249033315L, 38)), var26);
            HudPositionCalculator.Placement var30 = this.positionCalculator
               .calculateBoxPlacement(
                  this.applyTextAlignmentOffset(
                     var12 + Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4588541348038385094L, 59)), var24, TextAlignment.LEFT
                  ),
                  var28,
                  Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-7479604323896612780L, 17)),
                  var24,
                  var26
               );
            this.upsertHud(
               var1,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                  "0uAJCqF8vzoteZ5wvja3RxfB82GewgLi+XKjFwACRNNSkhENByZHrEcG2qpe42c6WMI5Xw6YeTiB/oZGpA4WJ+HBVycDYx5OsEj51YgDNcTecCZrm9wlVibKEy2Yc4g5QjtQ8dX0Xtdy6Q8p+AZDMhngzvbhouGGJIY="
               ),
               var30.location(),
               var30.scale(),
               this.withEditorUiFont(
                  this.withHexPrefix(
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("BgJfoyXejGVWwBZDMU1Uc1lUIb5bhabBTpo5eNTu"),
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("eckXbwmMImS9GFDtXxMyj083x+bnrE/TWBO4dhrnnszPyLQvPUWvFg==")
                  )
               ),
               Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121290854, 10),
               TextAlignment.LEFT
            );
            this.removeHudById(
               var1,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                  "QCG3gI2AZ9ea0JXH1HSi7Nz/6oznc/I4j/7guctIt13Ukd60ilN2lWtIhZDWSQ99sGk26m9kuJSwfkHJsuHaGwdwlaypwcDeggHTELB3wV1Eqg1g/i7qkV7xPxD5pgnKt88Uc75p"
               )
            );
         }
      } else {
         this.clearActivePagesDragPreview(var1);
      }
   }

   @Override
   public void clearActivePagesDragPreview(Player var1) {
      if ((-354369510 * -354369510 + -354369510 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         this.clearOutlineHud(
            var1,
            Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
               "A1M7laYUxLlARVkKEgpYnjis8WzR5dLtaAh6YeodRJ6Rk1asCpD095NTjd9Th4oQ4HSYLfaripLzYNCaV78JP+7nwiAhxfojwtLASyN5vcXmTjNLsZ7e9Uxln0RpR9wzMgPzBg=="
            )
         );
         this.removeBaseHud(
            var1,
            Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
               "A1M7laYUxLlARVkKEgpYnjis8WzR5dLtaAh6YeodRJ6Rk1asCpD095NTjd9Th4oQ4HSYLfaripLzYNCaV78JP+7nwiAhxfojwtLASyN5vcXmTjNLsZ7e9Uxln0RpR9wzMgPzBg=="
            )
         );
         this.removeRoundedParts(
            var1,
            Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
               "A1M7laYUxLlARVkKEgpYnjis8WzR5dLtaAh6YeodRJ6Rk1asCpD095NTjd9Th4oQ4HSYLfaripLzYNCaV78JP+7nwiAhxfojwtLASyN5vcXmTjNLsZ7e9Uxln0RpR9wzMgPzBg=="
            )
         );
         this.removeHudById(
            var1,
            Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
               "EC/D07zA+u283BSAQyWqkmH5Hw+4eWhCqblmQdiYEm/s8zMMoHN1wPG+82PWPEwHHpnsZJ29ZUA45tJghSBGYyuXmEvYc+R7G5kQUTWs08lDWt6muAEo/arV6Sa3o1hwpcNiFc5eejiWUDWxi3P4HA=="
            )
         );
         this.removeHudById(
            var1,
            Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
               "0uAJCqF8vzoteZ5wvja3RxfB82GewgLi+XKjFwACRNNSkhENByZHrEcG2qpe42c6WMI5Xw6YeTiB/oZGpA4WJ+HBVycDYx5OsEj51YgDNcTecCZrm9wlVibKEy2Yc4g5QjtQ8dX0Xtdy6Q8p+AZDMhngzvbhouGGJIY="
            )
         );
         this.removeHudById(
            var1,
            Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
               "QCG3gI2AZ9ea0JXH1HSi7Nz/6oznc/I4j/7guctIt13Ukd60ilN2lWtIhZDWSQ99sGk26m9kuJSwfkHJsuHaGwdwlaypwcDeggHTELB3wV1Eqg1g/i7qkV7xPxD5pgnKt88Uc75p"
            )
         );
      }
   }

   public boolean isPreviewPointerInteractionActive(EditorSession var1) {
      if ((-2118093070 * -2118093070 + -2118093070 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1219240242, 20) != 0);
      } else {
         return ((!var1.moveDragActive && !var1.previewPanActive && !var1.marqueeSelectActive
            ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 19)
            : Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1238243059, 24)) != 0);
      }
   }

   @Override
   public void resetTransientPanelsForPageSwitch(Player var1, EditorSession var2) {
      if (((-1224090068 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         var2.fileDropdownVisible = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 20) != 0);
         var2.fileDropdownHoverTargetId = null;
         var2.fileNavbarVisualActive = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 6) != 0);
         var2.editDropdownVisible = (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1505943114, 29) != 0);
         var2.editDropdownHoverTargetId = null;
         var2.editNavbarVisualActive = (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-2032494348, 15) != 0);
         var2.selectionDropdownVisible = (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1537863527, 19) != 0);
         var2.selectionDropdownHoverTargetId = null;
         var2.selectionNavbarVisualActive = (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(2077873898, 1) != 0);
         var2.layerDropdownVisible = (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(2067455311, 6) != 0);
         var2.layerDropdownHoverTargetId = null;
         var2.layerNavbarVisualActive = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 23) != 0);
         var2.windowDropdownVisible = (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1628015174, 31) != 0);
         var2.windowDropdownHoverTargetId = null;
         var2.windowNavbarVisualActive = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 0) != 0);
         var2.cursorPageDropdownVisible = (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-627510138, 23) != 0);
         var2.cursorPageDropdownHoverTargetId = null;
         var2.cursorPageDropdownOpenToken = var2.cursorPageDropdownOpenToken + Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1201039027, 24);
         var2.cursorLayersDropdownVisible = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 31) != 0);
         var2.cursorLayersDropdownHoverTargetId = null;
         var2.cursorLayersDropdownContextTargetId = null;
         var2.cursorLayersDropdownContextTargetPath = null;
         var2.cursorLayersDropdownOpenToken = var2.cursorLayersDropdownOpenToken + Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(565626429, 13);
         var2.cursorActionsDropdownVisible = (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-2032494348, 15) != 0);
         var2.cursorActionsDropdownHoverTargetId = null;
         var2.cursorActionsEditDropdownVisible = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 17) != 0);
         var2.cursorActionsEditDropdownHoverTargetId = null;
         var2.welcomePopupVisible = (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-192488808, 7) != 0);
         var2.savePopupVisible = (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 19) != 0);
         var2.preferencesPopupVisible = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 9) != 0);
         var2.pendingSavePopupField = null;
         this.clearPendingTextToolEdit(var1, var2, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-614261104, 20) != 0));
         var2.colorPickerVisible = (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1450227603, 27) != 0);
         var2.colorPickerGradDragActive = (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1728805158, 31) != 0);
         var2.colorPickerHueDragActive = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 5) != 0);
         var2.colorPickerHexInputPending = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 26) != 0);
         var2.pendingPropertyField = null;
         this.clearPendingItemDesignInventoryPick(var2);
         this.stopOpacitySliderDrag(var2, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1662947547, 28) != 0));
         var2.rightSidebarHoverTab = null;
         this.clearAlignToolbarOverlay(var1, var2);
         this.resetSidebarFieldDrag(var1, var2);
         this.beginShellOpacityBatch(var2);

         try {
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("1KM/xiOqk9A8ARTemVyIRsn2UFE7sFb9h5PJTFT1ZxRgeBjlygOsHuC6JcZ7E9FFnn9QjMyu"),
               (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1856486813, 21) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("R/cOi9eGn7+ubtgr//9IqNuFw33UBEeOLi3K+lWplh/OVkYyyD2gZrAMV2oN4ufJ1KlqGaBy"),
               (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-769955229, 9) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("0w6sTacL/sj9i1xCVM0Wn568ujAfZH/oq24GsKs6y+lfyzuwgU17UBel2ieCkJXoslLVVHTks/qF2Z+2WGU8lw=="),
               (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 19) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("xnKYXpRQUq5XpGMKxI8tv4GK1JOmmRcn8JdolAN+Zq5vpV+B/vlZ6tsutUxBG2xuCX/VIyubkrA="),
               (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 23) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("MM4+tu1gHEtLnpyzC2BZkASltKVIyRwvBb7eMfN7HvZ05Yo95UxdkpW2Cqo6Oi/HcS7GfEpEaO6xSA=="),
               (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 29) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("PBePdxnwzsvIURkeg/XnYpeGrSNDp5d6OSNU6lyVMERqpi5M3tWYjzRcsmHJRfHXL0cVl2dcYUqDdxVawgxQNemNBT8="),
               (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 3) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("UjX3PGHOTATRVmlJvHULE5jTXanQqhQKOhWpXnUkJ7YL7FUOOqouiXTMXXLxT+8JBVFcjLr1sY3AEajr2jLc3NbxAk5zc8Jf"),
               (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 18) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("HdkW6RDniZDkF5Q2gzLoPXZ6HSM44JDiilvfND+Hk6uIOVjaq0vQ4A3baKTuV1yhozxKQ2KcDaReMG/3nTqNEGYEI0pn/thJCtw="),
               (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 21) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                  "/w8BqeqpPnju79otlgHWJVM0+l+FVmpbtThKtYShnUt147JnI1PWvByuwczQF/49AQC1AgxeKxtn7reRvZGfqW2esG5cIISdgncPhterKhlvcMxQ"
               ),
               (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 13) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("NY1aBsOwejJnYx40G3UOikg9nh3dG59SeEO190B5u/h1j/O4mYFEXWv5NhqvIisAbo6sjmzd4HrdVYz0fJie+ilX0yNW0Zy8"),
               (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 7) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("ZnKewYNKveko29TUapHj4INPCEEmiytKBupZoH+FXTs0iVn3n76lX/Q1pY5yuHUBtkKr3WvXwAvUqog8E4oNdQ=="),
               (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 31) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("r2VEmYYYv+M62gVcJIWbLpI09LxjcUonyMtCttSsN1PH/ncw8Va3AU8IDU4SS1Jlbse9wnolfYS8yw=="),
               (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-945539372, 2) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("99N7hEyU2vh/2O/Lfl6zgIGfo3TnjexgbHR0ABnc5n1vBH/kGPkJE2QXw/V3z/jOQZ7PhQbX+V1rxb4RHVagh9WG/miQH5X2"),
               (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-640226693, 9) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("AO4gQzMMcz3QnoqBbwJsiEajNBCezH2I/qkpZqW6utaY7k994OHNR1cPMMoI7NNQH+oDC7P2XtM="),
               (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 21) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("E8w5axmLNijBtr4N2TsDh49Qemvx8AzbnhL5LyjQPiOKis8w1d3q4/vUrrfPX8d4"),
               (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 14) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("VDWDEsYP5PKMJq+x5iHd7um0yReOPKIKGpc2ldQZUP5odPvw4NIwx+3f"),
               (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1856486813, 21) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("lh+5QOD0q6xuW5VHzvVSAT4EdOYWlxc7F+ks9R5pEh0aggWgVnMcXQ=="),
               (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 23) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("6VixCuuLbxBu7WbT8ZW1tY4lKEGxEu3nFV833kEwkzfGxKPQd9Bsw9HZ43WB4YKJRY4="),
               (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 19) != 0)
            );
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("LEjE2nzV2jSPk8YTGnRnSBycb6ZUJsfA9AFRMa5lUrms03AVoV7DgA=="),
               (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 27) != 0)
            );
         } finally {
            this.endShellOpacityBatch(var1, var2);
         }
      }
   }

   public List<Map<String, Object>> collectPageSwitchShellRefreshBlocks(List<Map<String, Object>> var1) {
      if ((-538698735 ^ -538698735) != 0) {
         throw new IllegalStateException();
      } else {
         ArrayList var2 = new ArrayList();
         if (var1 != null && !var1.isEmpty()) {
            HashMap var3 = new HashMap();
            int var4 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(2051239244, 6);

            for (Map var6 : var1) {
               var4++;
               ConfigurationSection var7 = this.mapToSection(var6);
               if (var7 != null) {
                  String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1674713966, 1)];
                  var10001[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-360982058, 25)] = var7.getString(
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("67bwboawFEWbkseanySzvg1QFeC0Xdd4zDW6+CtiAYQDT+ro")
                  );
                  var10001[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1113756025, 5)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "BD52BFHhkJOFBAfs8EKh+2AXSNN6Pr0OfcwazKku2VNZ8JVcJ5Y="
                  );
                  String var8 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
                  if (Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("BD52BFHhkJOFBAfs8EKh+2AXSNN6Pr0OfcwazKku2VNZ8JVcJ5Y=").equals(var8)
                     || Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("Qh7aR7gBntvpdL5W0IWxucVTclLRizkKvAPuhkfF+yDwsnXO").equals(var8)
                     || this.isRoundedType(var8)) {
                     String var9 = this.resolveElementId(var7, var4, var3);
                     var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184786099, 19)];
                     var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-945539372, 2)] = var9;
                     String var10 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
                     if (!Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("AyJiySCMMVdnvQTwy2GdV/8HvS8nxh+ERn8dr5sKIdxBil+Fg4+vzHcyRnheby295lahkQ==").equals(var10)) {
                        var2.add(var6);
                     }
                  }
               }
            }

            return var2;
         } else {
            return var2;
         }
      }
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public void rerenderEditorShellForPageSwitch(Player var1, EditorSession var2) {
      if (((-80184015 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         if (var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
            List var3 = var2.shellBlocks;
            List var4 = this.collectPageSwitchShellRefreshBlocks(var3);
            if (!var4.isEmpty()) {
               var2.shellCacheBuilt = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 29) != 0);
               var2.shellSectionCache.clear();
               var2.shellStaticRectCache.clear();
               var2.shellRuntimeRects.clear();
               var2.shellBlocks = var4;
               boolean var7 = false /* VF: Semaphore variable */;

               try {
                  var7 = true;
                  this.renderEditorShell(var1, var2);
                  var7 = false;
               } finally {
                  if (var7) {
                     var2.shellBlocks = var3;
                     var2.shellCacheBuilt = (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(2051239244, 6) != 0);
                     var2.shellSectionCache.clear();
                     var2.shellStaticRectCache.clear();
                     var2.shellRuntimeRects.clear();
                  }
               }

               var2.shellBlocks = var3;
               var2.shellCacheBuilt = (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1215146383, 11) != 0);
               var2.shellSectionCache.clear();
               var2.shellStaticRectCache.clear();
               var2.shellRuntimeRects.clear();
            }
         }
      }
   }

   public LinkedHashSet<String> collectActivePagesShellLogicalIds(List<Map<String, Object>> var1) {
      if ((601108515 * (601108515 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         LinkedHashSet var2 = new LinkedHashSet();
         this.collectActivePagesShellLogicalIds(var1, (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 27) != 0), var2);
         return var2;
      }
   }

   public void collectActivePagesShellLogicalIds(List<Map<String, Object>> var1, boolean var2, Set<String> var3) {
      if (((-1576000631 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty() && var3 != null) {
         for (Map var5 : var1) {
            if (var5 != null && !var5.isEmpty()) {
               String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261299, 9)];
               var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 25)] = this.stringValue(
                  var5.get(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("d56YQdh6CWwACRPLaSV4WkUJWbdVKvrbmatzX9J013Y="))
               );
               String var6 = this.firstNonBlank(var10001);
               int var7 = !var2
                     && !Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("xqDbb8FU8334rgQalHAngCe271fu8oblrxOdeIuADgpY/HuYBHhS7/oJNSLJWtcCy6J7qQ==").equalsIgnoreCase(var6)
                  ? Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 22)
                  : Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-2032461580, 15);
               if (var7 != 0 && !var6.isBlank()) {
                  var3.add(var6);
               }

               List var8 = this.childBlocksOf(var5);
               if (var8 != null) {
                  this.collectActivePagesShellLogicalIds(var8, (var7 != 0), var3);
               }
            }
         }
      }
   }

   public void clearActivePagesHudByLogicalId(Player var1, String var2) {
      if ((-46630824 ^ -46630824) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = Xqedii_3lwh5w77depfo(var2);
         this.clearOutlineHud(var1, var3);
         this.removeBaseHud(var1, var3);
         this.removeRoundedParts(var1, var3);
         this.clearOutlineHud(var1, var2);
         this.removeBaseHud(var1, var2);
         this.removeRoundedParts(var1, var2);
      }
   }

   @Override
   public void clearActivePagesRuntimeSlots(Player var1, Set<String> var2) {
      if ((1139332443 * 1139332443 - 1139332443 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         if (var2 != null && !var2.isEmpty()) {
            LinkedHashSet var3 = new LinkedHashSet();

            for (String var5 : var2) {
               this.clearActivePagesHudByLogicalId(var1, var5);
               int var6 = this.resolveActivePageSlotFromLogicalId(var5);
               if (var6 > 0) {
                  var3.add(var6);
               }
            }

            this.clearActivePagesSlotsByIndex(var1, var3);
         }
      }
   }

   public boolean isActivePagesRuntimeHudId(String var1) {
      if ((-1674903573 * -1674903573 + -1674903573 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return var1 != null && !var1.isBlank() && Xqedii_l2une6g.Xqedii_9rnklfl.matcher(var1).matches();
      }
   }

   public int resolveActivePageSlotFromHudId(String var1) {
      if (((1733450607 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isBlank()) {
         Matcher var2 = Xqedii_l2une6g.Xqedii_9rnklfl.matcher(var1);
         if (!var2.matches()) {
            return Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1123447399, 21);
         } else {
            try {
               String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1674713966, 1)];
               var10001[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 0)] = var2.group(Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1721132723, 29));
               var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 19)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                  "L3td+GZqipT2IAKlaAgwQAwpSTJVHiggvCKv8SRSNm0="
               );
               return Integer.parseInt(this.firstNonBlank(var10001));
            } catch (NumberFormatException var4) {
               return Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1219240241, 20);
            }
         }
      } else {
         return Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-945539376, 2);
      }
   }

   public void clearActivePagesSlotsByIndex(Player var1, Set<Integer> var2) {
      if ((-565107781 ^ -565107781) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isEmpty()) {
         LinkedHashSet var3 = new LinkedHashSet();

         for (int var5 : var2) {
            if (var5 > 0) {
               var3.add(var5);
            }
         }

         if (!var3.isEmpty()) {
            for (int var11 : (Iterable<Integer>) (var3)) {

               this.clearActivePagesHudByLogicalId(var1, Xqedii_2zg2411alqkiq(var11));
               this.clearActivePagesHudByLogicalId(var1, Xqedii_1x9fdm49dc3pg(var11));
               this.clearActivePagesHudByLogicalId(var1, Xqedii_nf7q6o7zatxh(var11));
               this.clearActivePagesHudByLogicalId(var1, Xqedii_qwmphia9qccb(var11));
            }

            ArrayList var10 = new ArrayList();

            for (Entity var6 : this.hudService.getAllHuds(var1)) {
               if (var6 != null && var6.hasMetadata(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("d56YQdh6CWwACRPLaSV4WkUJWbdVKvrbmatzX9J013Y="))) {
                  String var7 = ((MetadataValue)var6.getMetadata(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("d56YQdh6CWwACRPLaSV4WkUJWbdVKvrbmatzX9J013Y="))
                        .get(Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1734060527, 11)))
                     .asString();
                  int var8 = this.resolveActivePageSlotFromHudId(var7);
                  if (var8 > 0 && var3.contains(var8)) {
                     var10.add(var6);
                  }
               }
            }

            if (!var10.isEmpty()) {
               this.hudService.removeHudX(var10, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1784926747, 25) != 0));
            }
         }
      }
   }

   @Override
   public boolean isActivePagesShellLogicalId(String var1) {
      if ((870080175 * 870080175 - 870080175 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261815, 2)];
         var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1025619622, 5)] = var1;
         String var2 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
         if (!var2.isBlank() && var2.startsWith(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("2MtlyoTJ0vNXnpaE+HPx7mFYookptSkbrdjky69RrSJRnJUKN7I="))) {
            return ((!var2.endsWith(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("NjFxRno1yJiSbo1zhTqfyUQHG+fHNSW8m3UTtz6o1H/BeeQONTT65riblHDrkj3UgOfkfg=="))
                  && !var2.endsWith(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("cYSE+yGodNPqDG4Bb5ob8MET/PLl43x0Urb80GB8xe91o2Ufh3Z11wBfWydUEwKT89zr3gN+"))
                  && !var2.endsWith(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("bgoJYBLBXebL4QEslmxXCMwxLvsZfZfjdSS0q7sMQL/hCuFxWd7Dpg=="))
                  && !var2.endsWith(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("w9kqG1tgbPsHsFgQzvw+2xGt62k1fSGZ7wXUic5SNl4nzvygRt/QAamY2C7yv8tO7oE="))
               ? Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 20)
               : Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1025619654, 5)) != 0);
         } else {
            return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-2032494348, 15) != 0);
         }
      }
   }

   public int resolveActivePageSlotFromLogicalId(String var1) {
      if ((-674586242 * -674586242 + -674586242 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-969072202, 29)];
         var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 25)] = var1;
         String var2 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
         if (!var2.isBlank() && var2.startsWith(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("2MtlyoTJ0vNXnpaE+HPx7mFYookptSkbrdjky69RrSJRnJUKN7I="))) {
            int var3 = var2.indexOf(
               Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1123644006, 15),
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("2MtlyoTJ0vNXnpaE+HPx7mFYookptSkbrdjky69RrSJRnJUKN7I=").length()
            );
            if (var3 <= Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("2MtlyoTJ0vNXnpaE+HPx7mFYookptSkbrdjky69RrSJRnJUKN7I=").length()) {
               return Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(-1184261812, 27);
            } else {
               String var4 = var2.substring(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("2MtlyoTJ0vNXnpaE+HPx7mFYookptSkbrdjky69RrSJRnJUKN7I=").length(), var3);

               try {
                  return Integer.parseInt(var4);
               } catch (NumberFormatException var6) {
                  return Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1188459111, 26);
               }
            }
         } else {
            return Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(-1184261812, 9);
         }
      }
   }

   public LinkedHashMap<Integer, String> resolveVisibleActivePageSlotStates(List<String> var1, int var2, String var3) {
      if ((1678001755 * (1678001755 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         LinkedHashMap var4 = new LinkedHashMap();
         if (var1 != null && !var1.isEmpty()) {
            int var5 = Math.max(Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 8), var1.size() - Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1189504691, 20));
            int var6 = Math.max(Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 12), Math.min(var5, var2));
            String var7 = this.normalizePageKey(var3, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
            int var8 = Math.min(
               Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-344834580, 16), Math.max(Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1653692483, 13), var1.size() - var6)
            );

            for (int var9 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1316009875, 27); var9 <= var8; var9++) {
               String var10 = (String)var1.get(var6 + var9 - Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261815, 2));
               int var11 = !var7.isBlank() && var7.equalsIgnoreCase(var10)
                  ? Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(460088274, 17)
                  : Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1394512091, 28);
               var4.put(var9, this.encodeActivePageSlotState(var10, (var11 != 0)));
            }

            return var4;
         } else {
            return var4;
         }
      }
   }

   public String encodeActivePageSlotState(String var1, boolean var2) {
      if ((335815010 * (335815010 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var3 = this.normalizePageKey(var1, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ==")).toLowerCase(Locale.ROOT);
         return var3.isBlank()
            ? Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ==")
            : Xqedii_1pwlkknl82xgc(
               var3,
               var2
                  ? Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("eN/R2KV0BYK35aoZmGFKvb+i+BV3PtuuGEDKQqjG")
                  : Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("jgkYiZNX+jOLh1GyUI+fSkPMOR/I+r4OTpJPRL09")
            );
      }
   }

   public LinkedHashSet<Integer> resolveChangedActivePageSlots(Map<Integer, String> var1, Map<Integer, String> var2) {
      if ((7471174 * 7471174 - 7471174 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         LinkedHashSet var3 = new LinkedHashSet();

         for (int var4 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(920176548, 18); var4 <= Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184266931, 10); var4++) {
            String var5 = var1 == null ? null : (String)var1.get(var4);
            String var6 = var2 == null ? null : (String)var2.get(var4);
            if (!this.equalsNullable(var5, var6)) {
               var3.add(var4);
            }
         }

         return var3;
      }
   }

   @Override
   public void syncActivePagesRenderedSlotStates(EditorSession var1) {
      if ((916628503 * 916628503 - 916628503 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         var1.activePagesRenderedSlotStates.clear();
         var1.activePagesRenderedSlotStates.putAll(this.resolveVisibleActivePageSlotStates(var1.openEditorPages, var1.activePagesScrollOffset, var1.pageName));
      }
   }

   public List<Map<String, Object>> collectActivePagesRenderableShellBlocks(List<Map<String, Object>> var1) {
      if (((126183591 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return this.collectActivePagesRenderableShellBlocks(var1, null);
      }
   }

   public List<Map<String, Object>> collectActivePagesRenderableShellBlocks(List<Map<String, Object>> var1, Set<Integer> var2) {
      if ((-1718312902 ^ -1718312902) != 0) {
         throw new IllegalStateException();
      } else {
         ArrayList var3 = new ArrayList();
         if (var1 != null && !var1.isEmpty()) {
            HashMap var4 = new HashMap();
            int var5 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(512809811, 4);

            for (Map var7 : var1) {
               var5++;
               ConfigurationSection var8 = this.mapToSection(var7);
               if (var8 != null) {
                  String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261683, 6)];
                  var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 20)] = var8.getString(
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("67bwboawFEWbkseanySzvg1QFeC0Xdd4zDW6+CtiAYQDT+ro")
                  );
                  var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 5)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "BD52BFHhkJOFBAfs8EKh+2AXSNN6Pr0OfcwazKku2VNZ8JVcJ5Y="
                  );
                  String var9 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
                  if (Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("BD52BFHhkJOFBAfs8EKh+2AXSNN6Pr0OfcwazKku2VNZ8JVcJ5Y=").equals(var9)
                     || Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("Qh7aR7gBntvpdL5W0IWxucVTclLRizkKvAPuhkfF+yDwsnXO").equals(var9)
                     || this.isRoundedType(var9)) {
                     String var10 = this.resolveElementId(var8, var5, var4);
                     if (this.isActivePagesShellLogicalId(var10)) {
                        int var11 = this.resolveActivePageSlotFromLogicalId(var10);
                        if (var2 == null || var2.isEmpty() || var2.contains(var11)) {
                           var3.add(var7);
                        }
                     }
                  }
               }
            }

            return var3;
         } else {
            return var3;
         }
      }
   }

   public void clearActivePagesRuntimeRectCache(EditorSession var1) {
      if ((290343013 * 290343013 + 290343013 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.shellRuntimeRects != null && !var1.shellRuntimeRects.isEmpty()) {
         var1.shellRuntimeRects.entrySet().removeIf(var1x -> {
            if ((-1359103539 * -1359103539 + -1359103539 & 1) != 0) {
               throw new IllegalStateException();
            } else {
               return this.isActivePagesShellLogicalId(var1x.getKey());
            }
         });
      }
   }

   @Override
   public void rerenderActivePagesShell(Player var1, EditorSession var2) {
      if ((-804517443 ^ -804517443) != 0) {
         throw new IllegalStateException();
      } else {
         this.rerenderActivePagesShell(
            var1, var2, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1280453385, 10) != 0), (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1443928229, 27) != 0)
         );
      }
   }

   @Override
   public void rerenderActivePagesShell(Player var1, EditorSession var2, boolean var3) {
      if ((2102703956 * 2102703956 - 2102703956 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         this.rerenderActivePagesShell(var1, var2, var3, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-640226693, 9) != 0));
      }
   }

   @Override
   public void rerenderActivePagesShell(Player var1, EditorSession var2, boolean var3, boolean var4) {
      if (((-1290340898 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         LinkedHashMap var5 = new LinkedHashMap<>(var2.activePagesRenderedSlotStates);
         LinkedHashSet var6 = this.collectActivePagesShellLogicalIds(var2.shellBlocks);
         List var7 = this.resolveOpenEditorPagesForPlayer(var1.getUniqueId(), var2.pageName);
         var2.openEditorPages.clear();
         var2.openEditorPages.addAll(var7);
         int var8 = Math.max(Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 6), var7.size() - Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1104573030, 23));
         var2.activePagesScrollOffset = Math.max(Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 13), Math.min(var8, var2.activePagesScrollOffset));
         var2.shellBlocks = this.buildShellBlocksWithActivePages(
            var2.editorShellTemplateBlocks, var2.components, var2.openEditorPages, var2.activePagesScrollOffset, var2.pageName
         );
         LinkedHashSet var9 = this.collectActivePagesShellLogicalIds(var2.shellBlocks);
         var6.removeAll(var9);
         LinkedHashMap var10 = this.resolveVisibleActivePageSlotStates(var2.openEditorPages, var2.activePagesScrollOffset, var2.pageName);
         LinkedHashSet var11 = this.resolveChangedActivePageSlots(var5, var10);
         this.clearActivePagesRuntimeRectCache(var2);
         var2.shellCacheBuilt = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 21) != 0);
         var2.shellSectionCache.clear();
         var2.shellStaticRectCache.clear();
         this.clearActivePagesRuntimeSlots(var1, var6);
         this.clearActivePagesSlotsByIndex(var1, var11);
         if (!var11.isEmpty()) {
            List var12 = var2.shellBlocks;
            List var13 = this.collectActivePagesRenderableShellBlocks(var12, var11);
            var2.shellBlocks = var13;

            try {
               this.renderEditorShell(var1, var2);
            } finally {
               var2.shellBlocks = var12;
            }
         }

         var2.activePagesRenderedSlotStates.clear();
         var2.activePagesRenderedSlotStates.putAll(var10);
         if (var3) {
            this.renderEditorToolSidebar(var1, var2);
         }

         if (var4) {
            this.updateEditorPropertiesSidebar(var1, var2);
         }

         this.updatePageInfoReadout(var1, var2);
         this.setShellColor(
            var1, var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("RJyryw03cqJtWdLXyhCBl6Fv7Ya1kS6jah/DFf6AqmpDZlNsBeKVF08yR1oVHnJDTMmOANKJrNE="), var2.editorColor1
         );
         this.setShellColor(
            var1, var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("j0gx4JRjqUlygEWtA0y7AXs4LV7p36Temq7Ek5zOOQQtJDytO7+L63/E1qMeHjPFdX9srFluKpU="), var2.editorColor2
         );
      }
   }

   @Override
   public void resetActivePagesDragState(EditorSession var1) {
      if ((-305059179 ^ -305059179) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         var1.activePagesReorderReleaseWatchToken = var1.activePagesReorderReleaseWatchToken + Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 15);
         var1.activePagesArmedPageKey = null;
         var1.activePagesReorderPageKey = null;
         var1.activePagesReorderActive = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 4) != 0);
         var1.activePagesReorderInsertIndex = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(2051239180, 6);
         var1.activePagesArmedStartHitX = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149447249402688283L, 53));
         var1.activePagesArmedStartHitY = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(9139232356527869375L, 48));
      }
   }

   @Override
   public boolean handleCursorLayersDropdownClick(Player var1, EditorSession var2, GuiService.ClickType var3, double var4, double var6) {
      if ((-472877883 * -472877883 - -472877883 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var2 == null || !var2.editMode || !var2.previewMode || var3 == null) {
         return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 10) != 0);
      } else if (!var2.welcomePopupVisible && !var2.savePopupVisible && !var2.preferencesPopupVisible) {
         double var8 = var2.shellHitX(var4);
         double var10 = var2.shellHitY(var6);
         boolean var12 = var2.cursorLayersDropdownVisible;
         int var13 = !var12
               || !this.isInsideShellBlock(
                     var2,
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("UjX3PGHOTATRVmlJvHULE5jTXanQqhQKOhWpXnUkJ7YL7FUOOqouiXTMXXLxT+8JBVFcjLr1sY3AEajr2jLc3NbxAk5zc8Jf"),
                     var8,
                     var10
                  )
                  && !this.isInsideShellBlock(
                     var2,
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("UjX3PGHOTATRVmlJvHULE5jTXanQqhQKOhWpXnUkJ7YL7FUOOqouiXTMXXLxT+8JBVFcjLr1sY3AEajr2jLc3NbxAk5zc8Jf"),
                     var4,
                     var6
                  )
            ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 4)
            : Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1837922883, 22);
         if (var3 == GuiService.ClickType.LEFT) {
            if (!var12) {
               this.cancelCursorLayersDropdownPendingOpen(var2);
               return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 7) != 0);
            } else if (var13 == 0) {
               this.cancelCursorLayersDropdownPendingOpen(var2);
               this.setCursorLayersDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 24) != 0));
               return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 9) != 0);
            } else {
               String var20 = this.resolveHoveredCursorLayersDropdownTargetId(var2, var4, var6);
               if (var20 != null) {
                  String var21 = this.stripDuplicateRuntimeSuffix(var20);
                  long var22 = Xqedii_38o2zes.Xqedii_316oplq();
                  if (var21.equals(var2.cursorLayersDropdownLastActionId)
                     && var22 - var2.cursorLayersDropdownLastActionTick <= Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(5149447249402688306L, 3)) {
                     return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 19) != 0);
                  } else {
                     String[] var28 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261809, 1)];
                     var28[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 24)] = var2.cursorLayersDropdownContextTargetId;
                     String var25 = this.firstNonBlank(var28);
                     var28 = new String[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-689407015, 17)];
                     var28[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-615309680, 20)] = var2.cursorLayersDropdownContextTargetPath;
                     String var26 = this.firstNonBlank(var28);
                     var2.cursorLayersDropdownLastActionId = var21;
                     var2.cursorLayersDropdownLastActionTick = var22;
                     this.cancelCursorLayersDropdownPendingOpen(var2);
                     this.setCursorLayersDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-556877997, 4) != 0));
                     this.playEditorSfx(
                        var1,
                        var2,
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("iPHAX316MwVkWeIiBB+x5tpgdhfrAL2TOv5svokvdJzVIJ10O5tvjMo9Qucl5JmsReRpkEU9BzytpjUB7AicofLV22hytw==")
                     );
                     this.handleCursorLayersDropdownAction(var1, var2, var20, var25, var26);
                     return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 20) != 0);
                  }
               } else {
                  return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1038936948, 0) != 0);
               }
            }
         } else if (var3 != GuiService.ClickType.RIGHT) {
            return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-139219500, 2) != 0);
         } else if (this.handleActionsPanelRightClick(var1, var2, var4, var6)) {
            this.cancelCursorLayersDropdownPendingOpen(var2);
            if (var12 && var13 == 0) {
               this.setCursorLayersDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-556877997, 4) != 0));
            }

            return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1316009875, 27) != 0);
         } else {
            int var14 = var2.rightSidebarTab == EditorSidebarTab.LAYERS
               ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 14)
               : Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 22);
            String var15 = var14 != 0
               ? this.resolveLayerSelectionTargetByHit(var2, var4, var6, (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1117152947, 26) != 0))
               : null;
            String var16 = var14 != 0
               ? this.resolveLayerSelectionTargetPathByHit(var2, var4, var6, (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 28) != 0))
               : Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ==");
            if (var15 == null || var15.isBlank()) {
               var15 = this.resolvePreviewContextTargetByHit(var2, var4, var6);
               var16 = this.resolveEditorTargetPath(var2, var15);
            }

            int var17 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1674713962, 1);
            if (var14 != 0 && (var15 == null || var15.isBlank())) {
               EditorRect var18 = this.resolveLayersListRect(var2);
               if (var18 != null && this.isInsideRect(var18, var4, var6)) {
                  var15 = this.resolveLayerSelectionTargetByHit(
                     var2,
                     var18.x + Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149447249335644955L, 28)),
                     var6,
                     (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 5) != 0)
                  );
                  var16 = this.resolveLayerSelectionTargetPathByHit(
                     var2,
                     var18.x + Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-2596345709930141288L, 36)),
                     var6,
                     (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-586187974, 22) != 0)
                  );
                  var17 = var15 != null && !var15.isBlank()
                     ? Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 19)
                     : Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 19);
               }
            }

            if ((var15 == null || var15.isBlank()) && var13 != 0 && var12) {
               this.cancelCursorLayersDropdownPendingOpen(var2);
               this.setCursorLayersDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 30) != 0));
               var12 = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 16) != 0);
               var13 = Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 3);
               var15 = this.resolvePreviewContextTargetByHit(var2, var4, var6);
               var16 = this.resolveEditorTargetPath(var2, var15);
            }

            if (var15 != null && !var15.isBlank()) {
               this.finishLayersReorderDrag(var1, var2, (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 8) != 0));
               this.stopCursorToolDrag(var2);
               if (this.isSelectedTarget(var2, var15) && var2.selectedElementId != null && !var2.selectedElementId.isBlank()) {
                  this.normalizeSelectionState(var2);
               } else {
                  this.selectLayerTargetForContextDropdown(var1, var2, var15);
               }

               String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1226425055, 21)];
               var10001[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 1)] = var15;
               var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 20)] = var2.selectedElementId;
               String var24 = this.firstNonBlank(var10001);
               var10001 = new String[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-278438983, 3)];
               var10001[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-360982058, 25)] = var16;
               var10001[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1201039027, 24)] = this.resolveEditorTargetPath(var2, var24);
               String var19 = this.firstNonBlank(var10001);
               var2.cursorLayersDropdownContextTargetId = var24.isBlank() ? null : var24;
               var2.cursorLayersDropdownContextTargetPath = var19.isBlank() ? null : var19;
               if (var2.fileDropdownVisible) {
                  this.setFileDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-320113347, 8) != 0));
               }

               if (var2.editDropdownVisible) {
                  this.setEditDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 10) != 0));
               }

               if (var2.selectionDropdownVisible) {
                  this.setSelectionDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1025619622, 5) != 0));
               }

               if (var2.layerDropdownVisible) {
                  this.setLayerDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 21) != 0));
               }

               if (var2.windowDropdownVisible) {
                  this.setWindowDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(512809811, 4) != 0));
               }

               this.cancelCursorPageDropdownPendingOpen(var2);
               if (var2.cursorPageDropdownVisible) {
                  this.setCursorPageDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 13) != 0));
               }

               this.cancelCursorLayersDropdownPendingOpen(var2);
               this.moveCursorLayersDropdownTo(var1, var2, var4, var6);
               if (!var2.cursorLayersDropdownVisible) {
                  this.setCursorLayersDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 2) != 0));
                  this.moveCursorLayersDropdownTo(var1, var2, var4, var6);
                  this.updateCursorLayersDropdownActionVisibility(var1, var2);
                  this.updateCursorLayersDropdownHover(var1, var2, var4, var6);
               } else {
                  this.updateCursorLayersDropdownActionVisibility(var1, var2);
                  this.updateCursorLayersDropdownHover(var1, var2, var4, var6);
               }

               return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-556878013, 4) != 0);
            } else {
               int var23 = var17 == 0
                     && (
                        var14 == 0
                           || !this.isInsideShellBlock(
                                 var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("LEjE2nzV2jSPk8YTGnRnSBycb6ZUJsfA9AFRMa5lUrms03AVoV7DgA=="), var8, var10
                              )
                              && !this.isInsideShellBlock(
                                 var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("LEjE2nzV2jSPk8YTGnRnSBycb6ZUJsfA9AFRMa5lUrms03AVoV7DgA=="), var4, var6
                              )
                     )
                  ? Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(565618237, 13)
                  : Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 17);
               this.cancelCursorLayersDropdownPendingOpen(var2);
               if (var12 && var13 == 0) {
                  this.setCursorLayersDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(919914404, 18) != 0));
               }

               return ((var13 == 0 && var23 == 0
                  ? Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(2051239244, 6)
                  : Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1228522207, 21)) != 0);
            }
         }
      } else {
         return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 19) != 0);
      }
   }

   public void cancelCursorLayersDropdownPendingOpen(EditorSession var1) {
      if (((-317957591 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         var1.cursorLayersDropdownOpenToken = var1.cursorLayersDropdownOpenToken + Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261779, 5);
      }
   }

   public void scheduleCursorLayersDropdownOpen(Player var1, EditorSession var2) {
      if (((1401293473 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         int var3 = var2.cursorLayersDropdownOpenToken = var2.cursorLayersDropdownOpenToken + Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 23);
         Xqedii_38o2zes.Xqedii_2sj63w1(this.plugin, var1, () -> {
            if ((71177673 * 71177673 + 71177673 & 1) != 0) {
               throw new IllegalStateException();
            } else if (var1.isOnline()) {
               EditorSession var4 = this.editorSessions.get(var1.getUniqueId());
               if (var4 != null && var4 == var2 && var3 == var4.cursorLayersDropdownOpenToken) {
                  if (var4.editMode && var4.previewMode) {
                     this.setCursorLayersDropdownVisible(var1, var4, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-394536490, 25) != 0));
                  }
               }
            }
         }, Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444768108078767558L, 44));
      }
   }

   public boolean handleCursorLayersDropdownAction(Player var1, EditorSession var2, String var3) {
      if ((-446049899 * -446049899 - -446049899 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.handleCursorLayersDropdownAction(var1, var2, var3, null, null);
      }
   }

   public boolean handleCursorLayersDropdownAction(Player var1, EditorSession var2, String var3, String var4, String var5) {
      if ((-1343644719 ^ -1343644719) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String var6 = this.stripDuplicateRuntimeSuffix(var3);
         String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1147210123, 23)];
         var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 16)] = var4;
         var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1539909433, 10)] = var2.cursorLayersDropdownContextTargetId;
         String var7 = this.firstNonBlank(var10001);
         var10001 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-610732922, 23)];
         var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-384977615, 8)] = var5;
         var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 12)] = var2.cursorLayersDropdownContextTargetPath;
         String var8 = this.firstNonBlank(var10001);
         List var9 = this.resolveCursorLayersDropdownContextTargetPaths(var2, var8, var7);
         if (this.shouldApplyCursorLayersDropdownContextSelection(var6) && !var7.isBlank()) {
            this.selectLayerTargetForContextDropdown(var1, var2, var7);
         }

         int var12 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-635898746, 23);
         switch (var6.hashCode()) {
            case -1824732003:
               if (var6.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "8jaiSbDrXX4N+1xfg+MOG+Adrz4iPxOMk0fIceTjcuVNQTZllRiXoe8uEUxtPvDNNEWR31DeYDXWonfsdH0bExs7HSUvMn2isTMcIEoZqssrgeQuKvwwJhE7Ziw="
                  )
               )) {
                  var12 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1891078679, 3);
               }
               break;
            case -1427245493:
               if (var6.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "ziZAo/BLY9d2oDj40APdEiA1N+zIUVFdVDJx+P9fsnpAxoe2F8dk6liYq41QrBmyVle2zbCbSIbrn0rt6d/Nc0Vtzjd1cYwacTTPEfTWnf2etJzfSXD3TRqnthHPrWgw6TnSLw=="
                  )
               )) {
                  var12 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1224483122, 20);
               }
               break;
            case -1426931611:
               if (var6.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "7CB6ad5DQrVHfhOguq5WlAoTmtPb1C3GbiUHC5OlwKL3pG5MvCxA2qWbELw3tWf0dmIUBI9efBAOaDnvs83xQsQ6ctBdZEW6BZZhuWd9cIhktgzyqVVYru5w9HG+raFzKij5OQ=="
                  )
               )) {
                  var12 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1926656614, 28);
               }
               break;
            case -1301630891:
               if (var6.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "EjAGB+gxjwnyWRLpjyOFvUwk84eYtFLNd+5lROyZIbjKOt9H/EqpHpNT8rPRVJN31Spo/TZ1hq2lM0l2JoLkFDHpLJ6xo1axJWNpdb9HBS3rr96eaZBPipXyYMmoESfPD1Mu3yFe"
                  )
               )) {
                  var12 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350214, 4);
               }
               break;
            case -1295149789:
               if (var6.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "1UAeTq37PPB/GmiLICGKpY0uxvU8UMH89shWLQlWsa03yq4LD0Kaya51vbnPgMjjDiEVR24JPgnJTKXTsDphneSH6VZGtQ9Eg3UJxHeBkYwEt2c94tpFjX+LkNn8SwKdczcJmZa7"
                  )
               )) {
                  var12 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-769952157, 9);
               }
               break;
            case -737558382:
               if (var6.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "p8Uu3WWSxFBTXcGuaknqnpl9YlX9hVEuTjDEJJ7fp0bpL3g+bCnVURa5kLMpdyBrwH9/2flxf9eI3vgk1dPLBQ1UCojISQkJKw6Nr9btJ3kqlb6hZwpiulGnauLwwA=="
                  )
               )) {
                  var12 = Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184065203, 15);
               }
               break;
            case -713721797:
               if (var6.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "pM35MgWFT/QAGEM/kGI2AlzJ1F8AtkN70pyakCWmdkDCexPCzCAmtSsDBypQQkanUgUVdnYTdpZAROAG74PzzHJMIF4swHUjN0KrGyTO"
                  )
               )) {
                  var12 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 20);
               }
               break;
            case -638947699:
               if (var6.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "6cCzJjRYqv8bqHdxRylcpBIaQZHqZB/McsXLzhgvukgMlv5eIxsVWk0L8d5Zx5rLpl9U8GyT5xurGXcP/Syz5npv3eNUyEvu++9zoz2f4sA="
                  )
               )) {
                  var12 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1138127462, 24);
               }
               break;
            case 341842692:
               if (var6.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "8oic3T+7PByZXyQaGTJkhZ4Nr5DUZ+I2Hs8F7fi0wUBHdiartHO3MTbctrXU0FF+UoGL/acPU/XGj3PneoPOGxknhDhLfPvlyQDHDNLZlF/kzatFJOHHVQ=="
                  )
               )) {
                  var12 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350758, 8);
               }
               break;
            case 1778092220:
               if (var6.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "ftniDRPp5244B4EZcLmkW7ytqCrBbp5gXJOB6f0wsfklPNWdPruUsdJaY1mX87/XH2eQ/qOY7eBhW9xrI5bBofj5+oWIfwhucBV+CQ=="
                  )
               )) {
                  var12 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1025619622, 5);
               }
               break;
            case 2001747723:
               if (var6.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "rRZ3mk+ceMIntuRlDPrAsk5Nw6F5rUrmR1LRrwdA/JdRVr8xyg2TbDVEspJHRSVw6+q7xht/jHJC4kqXFO2oQPrXUoUtcyMbVGJXq96DZQS1KyAR+qd61sjp"
                  )
               )) {
                  var12 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(565691965, 13);
               }
         }
         boolean var10 = switch (var12) {
            case 0 -> this.handleEditorEditCut(var1, var2, var9);
            case 1 -> this.handleEditorEditCopy(var1, var2, var9);
            case 2 -> this.handleEditorEditPaste(var1, var2);
            case 3 -> this.handleEditorSelectionNewBlock(var1, var2);
            case 4 -> this.handleEditorSelectionNewText(var1, var2);
            case 5 -> this.handleEditorSelectionNewItem(var1, var2);
            case 6 -> this.openEditorImageFilePopup(var1, var2);
            case 7 -> this.handleEditorSelectionVisibility(var1, var2, true);
            case 8 -> this.handleEditorSelectionVisibility(var1, var2, false);
            case 9 -> this.handleEditorSelectionLock(var1, var2, true);
            case 10 -> this.handleEditorSelectionLock(var1, var2, false);
            default -> false;
         };
         var2.cursorLayersDropdownContextTargetId = null;
         var2.cursorLayersDropdownContextTargetPath = null;
         return var10;
      } else {
         return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 6) != 0);
      }
   }

   public List<String> resolveCursorLayersDropdownContextTargetPaths(EditorSession var1, String var2, String var3) {
      if ((-24967707 * (-24967707 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Collections.emptyList();
      } else {
         String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-658004938, 26)];
         var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 24)] = var2;
         String var4 = this.firstNonBlank(var10001);
         if (!var4.isBlank()) {
            return Collections.singletonList(var4);
         } else {
            var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1185310387, 20)];
            var10001[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 21)] = var3;
            var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 27)] = var1.cursorLayersDropdownContextTargetId;
            var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1221465843, 24)] = var1.selectedElementId;
            String var5 = this.firstNonBlank(var10001);
            String var6 = this.resolveEditorTargetPath(var1, var5);
            return !var6.isBlank() ? Collections.singletonList(var6) : Collections.emptyList();
         }
      }
   }

   public boolean shouldApplyCursorLayersDropdownContextSelection(String var1) {
      if ((533396112 * (533396112 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isBlank()) {
         int var3 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-2067455312, 6);
         switch (var1.hashCode()) {
            case -1824732003:
               if (var1.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "8jaiSbDrXX4N+1xfg+MOG+Adrz4iPxOMk0fIceTjcuVNQTZllRiXoe8uEUxtPvDNNEWR31DeYDXWonfsdH0bExs7HSUvMn2isTMcIEoZqssrgeQuKvwwJhE7Ziw="
                  )
               )) {
                  var3 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1734062575, 11);
               }
               break;
            case -737558382:
               if (var1.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "p8Uu3WWSxFBTXcGuaknqnpl9YlX9hVEuTjDEJJ7fp0bpL3g+bCnVURa5kLMpdyBrwH9/2flxf9eI3vgk1dPLBQ1UCojISQkJKw6Nr9btJ3kqlb6hZwpiulGnauLwwA=="
                  )
               )) {
                  var3 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350262, 2);
               }
               break;
            case -713721797:
               if (var1.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "pM35MgWFT/QAGEM/kGI2AlzJ1F8AtkN70pyakCWmdkDCexPCzCAmtSsDBypQQkanUgUVdnYTdpZAROAG74PzzHJMIF4swHUjN0KrGyTO"
                  )
               )) {
                  var3 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(460088274, 17);
               }
               break;
            case 341842692:
               if (var1.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "8oic3T+7PByZXyQaGTJkhZ4Nr5DUZ+I2Hs8F7fi0wUBHdiartHO3MTbctrXU0FF+UoGL/acPU/XGj3PneoPOGxknhDhLfPvlyQDHDNLZlF/kzatFJOHHVQ=="
                  )
               )) {
                  var3 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-432201290, 29);
               }
               break;
            case 1778092220:
               if (var1.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "ftniDRPp5244B4EZcLmkW7ytqCrBbp5gXJOB6f0wsfklPNWdPruUsdJaY1mX87/XH2eQ/qOY7eBhW9xrI5bBofj5+oWIfwhucBV+CQ=="
                  )
               )) {
                  var3 = Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 28);
               }
               break;
            case 2001747723:
               if (var1.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "rRZ3mk+ceMIntuRlDPrAsk5Nw6F5rUrmR1LRrwdA/JdRVr8xyg2TbDVEspJHRSVw6+q7xht/jHJC4kqXFO2oQPrXUoUtcyMbVGJXq96DZQS1KyAR+qd61sjp"
                  )
               )) {
                  var3 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1187911411, 24);
               }
         }
         return (switch (var3) { case 0, 1, 2, 3, 4, 5 -> true; default -> false; });
      } else {
         return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1215146383, 11) != 0);
      }
   }

   public void setCursorLayersDropdownVisible(Player var1, EditorSession var2, boolean var3) {
      if ((355006133 * 355006133 + 355006133 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         if (var2.cursorLayersDropdownVisible == var3) {
            if (var3) {
               this.updateCursorLayersDropdownActionVisibility(var1, var2);
               this.updateCursorLayersDropdownHover(var1, var2, var2.cursorX + var2.hitboxOffsetX, var2.cursorY + var2.hitboxOffsetY);
            }
         } else {
            double var4 = var2.cursorX + var2.hitboxOffsetX;
            double var6 = var2.cursorY + var2.hitboxOffsetY;
            String var8 = var2.cursorLayersDropdownHoverTargetId;
            var2.cursorLayersDropdownVisible = var3;
            if (!var3) {
               var2.cursorLayersDropdownHoverTargetId = null;
               var2.cursorLayersDropdownContextTargetId = null;
               var2.cursorLayersDropdownContextTargetPath = null;
            } else {
               this.playEditorSfx(
                  var1, var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("V2dIqjrne+J1bLpBa2OhpuW2JeocY1Edm3tPk8HlD4X6SKPjU3iMNmPLjBtV/ha4F+53AqiV5ISzXw==")
               );
            }

            this.beginShellOpacityBatch(var2);

            try {
               this.setSidebarPanelVisible(
                  var1,
                  var2,
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("UjX3PGHOTATRVmlJvHULE5jTXanQqhQKOhWpXnUkJ7YL7FUOOqouiXTMXXLxT+8JBVFcjLr1sY3AEajr2jLc3NbxAk5zc8Jf"),
                  var3
               );
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }

            if (!var3) {
               if (var8 != null && !var8.isBlank()) {
                  this.setCursorLayersDropdownItemVisual(var1, var2, var8, (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 14) != 0));
               }
            } else {
               this.updateCursorLayersDropdownActionVisibility(var1, var2);
               this.updateCursorLayersDropdownHover(var1, var2, var4, var6);
            }
         }
      }
   }

   @Override
   public void updateCursorLayersDropdownHover(Player var1, EditorSession var2, double var3, double var5) {
      if ((222553271 * 222553271 + 222553271 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         if (!var2.cursorLayersDropdownVisible) {
            if (var2.cursorLayersDropdownHoverTargetId != null) {
               var2.cursorLayersDropdownHoverTargetId = null;
            }
         } else {
            List var7 = this.resolveCursorLayersDropdownItemIds(var2);
            List var8 = this.resolveVisibleCursorLayersDropdownItemIds(var2);
            String var9 = this.resolveHoveredCursorLayersDropdownTargetId(var2, var3, var5, var7, var8);
            if (var9 != null && !var8.contains(var9)) {
               var9 = null;
            }

            String var10 = var2.cursorLayersDropdownHoverTargetId;
            if (!this.equalsNullable(var10, var9)) {
               var2.cursorLayersDropdownHoverTargetId = var9;
               if (var10 != null && !var10.isBlank()) {
                  this.setCursorLayersDropdownItemVisual(var1, var2, var10, (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 1) != 0));
               }

               if (var9 != null && !var9.isBlank()) {
                  this.setCursorLayersDropdownItemVisual(var1, var2, var9, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1938144403, 30) != 0));
               }
            }
         }
      }
   }

   public void setCursorLayersDropdownItemVisual(Player var1, EditorSession var2, String var3, boolean var4) {
      if (((-951007985 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         Boolean var5 = var2.cursorLayersDropdownItemActiveStates.get(var3);
         if (var5 == null || var5 != var4) {
            var2.cursorLayersDropdownItemActiveStates.put(var3, var4);
            String var6 = this.stripDuplicateRuntimeSuffix(var3);
            String[] var7 = Xqedii_l2une6g.Xqedii_19bkmu4.get(var6);
            if (var7 != null && var7.length >= Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350242, 2)) {
               this.setShellText(
                  var1,
                  Xqedii_xcy2qen7amaf(var3),
                  var4 ? var7[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 22)] : var7[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1784926747, 25)]
               );
            }

            this.setShellColor(
               var1,
               var2,
               var3,
               var4
                  ? Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("vFKbdC+trMfMvTdpemUT5SaLMmCun2FdAp8tbt0I4twdGWMVCU2goA==")
                  : Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("/PxdxVUeN3+036BgV6VC9G/t84rfe4wnAyTGA5d+vfjOdx84GkND8w==")
            );
         }
      }
   }

   public String resolveHoveredCursorLayersDropdownTargetId(EditorSession var1, double var2, double var4) {
      if (((31520646 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return null;
      } else {
         List var6 = this.resolveCursorLayersDropdownItemIds(var1);
         List var7 = this.resolveVisibleCursorLayersDropdownItemIds(var1);
         return this.resolveHoveredCursorLayersDropdownTargetId(var1, var2, var4, var6, var7);
      }
   }

   public String resolveHoveredCursorLayersDropdownTargetId(EditorSession var1, double var2, double var4, List<String> var6, List<String> var7) {
      if ((-1598347706 ^ -1598347706) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.cursorLayersDropdownVisible) {
         if (var7.isEmpty()) {
            return null;
         } else {
            double var8 = var1.shellHitX(var2);
            double var10 = var1.shellHitY(var4);
            int var12 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1378551885, 18);

            for (String var14 : var7) {
               String var15 = Xqedii_4fhxzwgoqm0u(var14);
               EditorRect var16 = this.findShellBlockRect(var1, var15);
               if (var16 != null) {
                  var12++;
                  if (var16.contains(var8, var10)) {
                     return var14;
                  }
               }
            }

            if (var12 == var7.size()) {
               return null;
            } else {
               EditorRect var18 = this.findShellBlockRect(
                  var1, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("UjX3PGHOTATRVmlJvHULE5jTXanQqhQKOhWpXnUkJ7YL7FUOOqouiXTMXXLxT+8JBVFcjLr1sY3AEajr2jLc3NbxAk5zc8Jf")
               );
               if (var18 != null && var18.contains(var8, var10)) {
                  double var19 = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-9168630106166755800L, 34));
                  int var22 = (int)Math.floor((var10 - var18.y) / Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444750515892657350L, 18)));
                  if (var22 >= 0 && var22 < var6.size()) {
                     String var17 = (String)var6.get(var22);
                     if (var7.contains(var17)) {
                        return var17;
                     }
                  }
               }

               for (String var21 : var7) {
                  if (this.isInsideShellBlock(var1, var21, var8, var10)) {
                     return var21;
                  }
               }

               return null;
            }
         }
      } else {
         return null;
      }
   }

   public List<String> resolveVisibleCursorLayersDropdownItemIds(EditorSession var1) {
      if ((1544433591 * (1544433591 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.resolveCursorLayersDropdownItemIds(var1);
      }
   }

   public List<String> resolveCursorLayersDropdownItemIds(EditorSession var1) {
      if (((-1497927813 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Collections.emptyList();
      } else {
         this.ensureCursorLayersDropdownCache(var1);
         return var1.cursorLayersDropdownResolvedItemIds;
      }
   }

   public List<String> resolveCursorLayersDropdownDynamicItemIds(EditorSession var1) {
      if ((164728851 * 164728851 - 164728851 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Collections.emptyList();
      } else {
         this.ensureCursorLayersDropdownCache(var1);
         return var1.cursorLayersDropdownResolvedDynamicItemIds;
      }
   }

   public Map<String, String> resolveCursorLayersDropdownIds(EditorSession var1) {
      if ((486304149 ^ 486304149) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Collections.emptyMap();
      } else {
         this.ensureCursorLayersDropdownCache(var1);
         return var1.cursorLayersDropdownIdsByBase;
      }
   }

   public void ensureCursorLayersDropdownCache(EditorSession var1) {
      if ((48084271 * 48084271 + 48084271 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         int var2 = var1.shellBlocks == null ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 7) : System.identityHashCode(var1.shellBlocks);
         int var3 = var1.shellBlocks == null ? Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 3) : var1.shellBlocks.size();
         ConfigurationSection var4 = this.findShellBlockSection(
            var1, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("UjX3PGHOTATRVmlJvHULE5jTXanQqhQKOhWpXnUkJ7YL7FUOOqouiXTMXXLxT+8JBVFcjLr1sY3AEajr2jLc3NbxAk5zc8Jf")
         );
         String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1186358963, 21)];
         var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1539910457, 10)] = var4 == null
            ? null
            : var4.getString(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("VqgeM2Aq+uqqBybLCsnUpFvJa3h/nu8/EwJzKL21riXD7S8+edRWl8UzSMdspTbZeVA5WYottRYf8tYUeJSCSuyVVV0=")
            );
         String var5 = this.firstNonBlank(var10001);
         var1.cursorLayersDropdownResolvedShellIdentity = var2;
         var1.cursorLayersDropdownResolvedShellSize = var3;
         var1.cursorLayersDropdownResolvedPanelPath = var5;
         var1.cursorLayersDropdownIdsByBase.clear();
         var1.cursorLayersDropdownResolvedItemIds.clear();
         var1.cursorLayersDropdownResolvedDynamicItemIds.clear();
         var1.cursorLayersDropdownResolvedPanelMemberIds.clear();
         var1.cursorLayersDropdownActionVisibilityStates.clear();
         var1.cursorLayersDropdownItemActiveStates.clear();
         if (!var5.isBlank() && var1.shellBlocks != null && !var1.shellBlocks.isEmpty()) {
            HashMap var6 = new HashMap();
            int var7 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 5);

            for (Map var9 : var1.shellBlocks) {
               var7++;
               ConfigurationSection var10 = this.mapToSection(var9);
               if (var10 != null) {
                  String var11 = this.resolveElementId(var10, var7, var6);
                  if (!var11.isBlank()) {
                     var10001 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1864670434, 12)];
                     var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 25)] = var10.getString(
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("VqgeM2Aq+uqqBybLCsnUpFvJa3h/nu8/EwJzKL21riXD7S8+edRWl8UzSMdspTbZeVA5WYottRYf8tYUeJSCSuyVVV0=")
                     );
                     String var12 = this.firstNonBlank(var10001);
                     if (this.belongsToSidebarPanel(var5, var12)) {
                        var1.cursorLayersDropdownResolvedPanelMemberIds.add(var11);
                        String var13 = this.stripDuplicateRuntimeSuffix(var11);
                        if (Xqedii_l2une6g.Xqedii_1sxm1ta.contains(var13) && !var1.cursorLayersDropdownIdsByBase.containsKey(var13)) {
                           var1.cursorLayersDropdownIdsByBase.put(var13, var11);
                        }
                     }
                  }
               }
            }
         }

         for (String var16 : Xqedii_l2une6g.Xqedii_1sxm1ta) {
            List var10000 = var1.cursorLayersDropdownResolvedItemIds;
            String[] var10002 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1025619686, 5)];
            var10002[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 18)] = var1.cursorLayersDropdownIdsByBase.get(var16);
            var10002[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 11)] = var16;
            var10000.add(this.firstNonBlank(var10002));
         }

         for (String var17 : Xqedii_l2une6g.Xqedii_16ey7rm) {
            List var18 = var1.cursorLayersDropdownResolvedDynamicItemIds;
            String[] var20 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184253619, 12)];
            var20[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 2)] = var1.cursorLayersDropdownIdsByBase.get(var17);
            var20[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-658004938, 26)] = var17;
            var18.add(this.firstNonBlank(var20));
         }
      }
   }

   public String stripDuplicateRuntimeSuffix(String var1) {
      if (((-278578328 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 7)];
         var10001[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1113755993, 5)] = var1;
         String var2 = this.firstNonBlank(var10001).trim();
         if (var2.isBlank()) {
            return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ==");
         } else {
            int var3 = var2.lastIndexOf(Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-896566470, 22));
            if (var3 > 0 && var3 < var2.length() - Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1150707379, 25)) {
               String var4 = var2.substring(var3 + Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 25));

               for (int var5 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1833728579, 22); var5 < var4.length(); var5++) {
                  if (!Character.isDigit(var4.charAt(var5))) {
                     return var2;
                  }
               }

               return var2.substring(Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 13), var3);
            } else {
               return var2;
            }
         }
      }
   }

   public void updateCursorLayersDropdownActionVisibility(Player var1, EditorSession var2) {
      if ((1844490336 * (1844490336 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         List var3 = this.resolveVisibleCursorLayersDropdownItemIds(var2);
         List var4 = this.resolveCursorLayersDropdownDynamicItemIds(var2);
         LinkedHashMap var5 = new LinkedHashMap();

         for (String var7 : (Iterable<String>) (var4)) {

            boolean var8 = var3.contains(var7);
            Boolean var9 = var2.cursorLayersDropdownActionVisibilityStates.get(var7);
            if (var9 == null || var9 != var8) {
               var5.put(var7, var8);
            }
         }

         if (!var5.isEmpty()) {
            this.beginShellOpacityBatch(var2);

            try {
               for (Entry var14 : (Iterable<Entry>) (var5.entrySet())) {

                  String var15 = (String)var14.getKey();
                  boolean var16 = (Boolean)var14.getValue();
                  this.setCursorLayersDropdownActionVisible(var1, var2, var15, var16);
                  var2.cursorLayersDropdownActionVisibilityStates.put(var15, var16);
               }
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }
         }

         if (var2.cursorLayersDropdownHoverTargetId != null && !var3.contains(var2.cursorLayersDropdownHoverTargetId)) {
            this.setCursorLayersDropdownItemVisual(
               var1, var2, var2.cursorLayersDropdownHoverTargetId, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1113755993, 5) != 0)
            );
            var2.cursorLayersDropdownHoverTargetId = null;
         }
      }
   }

   public void setCursorLayersDropdownActionVisible(Player var1, EditorSession var2, String var3, boolean var4) {
      if ((1944613720 * 1944613720 - 1944613720 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         this.setSidebarPanelVisible(var1, var2, var3, var4);
      }
   }

   public void moveCursorLayersDropdownTo(Player var1, EditorSession var2, double var3, double var5) {
      if ((1744325682 * 1744325682 + 1744325682 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         this.ensureCursorLayersDropdownCache(var2);
         ConfigurationSection var7 = this.findShellBlockSection(
            var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("UjX3PGHOTATRVmlJvHULE5jTXanQqhQKOhWpXnUkJ7YL7FUOOqouiXTMXXLxT+8JBVFcjLr1sY3AEajr2jLc3NbxAk5zc8Jf")
         );
         EditorRect var8 = this.findShellBlockRect(
            var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("UjX3PGHOTATRVmlJvHULE5jTXanQqhQKOhWpXnUkJ7YL7FUOOqouiXTMXXLxT+8JBVFcjLr1sY3AEajr2jLc3NbxAk5zc8Jf")
         );
         if (var7 != null && var8 != null) {
            double var9 = var2.shellHitX(var3);
            double var11 = var2.shellHitY(var5);
            double var13 = var9;
            double var15 = var11;
            boolean var17 = this.isInsidePreviewArea(var2, var3, var5);
            if (var17) {
               EditorRect var18 = this.findShellBlockRect(var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("L8qhl2r+PT2fF6nJJpDVZYJQu4YRtlFk69rD4GBBEGDzk42bJYOaTG5B"));
               if (var18 != null) {
                  double var19 = var18.maxX() - var8.width;
                  int var21 = var9 > var19 ? Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1113756025, 5) : Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-344637972, 16);
                  var13 = Math.max(var18.x, Math.min(var19, var9));
                  var15 = Math.max(var18.y, Math.min(var18.maxY() - var8.height, var11));
                  if (var21 != 0) {
                     var13 = Math.max(var18.x, var13 - Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444750790133095878L, 40)));
                  }
               }
            } else {
               double var34 = var2.yaml == null
                  ? Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-2382316495081812113L, 63))
                  : var2.yaml
                     .getDouble(
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("JbY0W4/p63VZlyGFBlLANCjyoMcyPJbm2E6BfNmIV/UjNgHsoK5oRQ4GS8m9rrYMdyImOw=="),
                        Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(9148924646383469179L, 58))
                     );
               double var20 = var2.yaml == null
                  ? Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(9092465331339074756L, 10))
                  : var2.yaml
                     .getDouble(
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("Q46DoFqAKh5Bc4sQk+pLBJLheHKNi9uddvs/w7BeBKqBEyquk0Y3H8xgAjUwnjEypbADNNpd"),
                        Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(-2472778511748342442L, 16))
                     );
               double var22 = Math.max(Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-11283219965867539L, 60)), var34 - var8.width);
               double var24 = Math.max(Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(9124163240935219197L, 8)), var20 - var8.height);
               int var26 = var9 > var22 ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 0) : Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(2067455311, 6);
               var13 = Math.max(Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444750515892723142L, 23)), Math.min(var22, var9));
               var15 = Math.max(Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149447249402688283L, 11)), Math.min(var24, var11));
               if (var26 != 0) {
                  var13 = Math.max(
                     Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-793670367356452885L, 11)),
                     var13 - Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(3866952757449924038L, 61))
                  );
               }
            }

            double var35 = var13 - var8.x;
            double var36 = var15 - var8.y;
            if (!(Math.abs(var35) < Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(-5331527451552418531L, 26)))
               || !(Math.abs(var36) < Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(3271199709779044097L, 46)))) {
               String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1316009875, 27)];
               var10001[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(2077873898, 1)] = var7.getString(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("VqgeM2Aq+uqqBybLCsnUpFvJa3h/nu8/EwJzKL21riXD7S8+edRWl8UzSMdspTbZeVA5WYottRYf8tYUeJSCSuyVVV0=")
               );
               String var37 = this.firstNonBlank(var10001);
               if (var37.isBlank()) {
                  this.applyCursorPageDropdownTeleportDurationZero(
                     var1,
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("UjX3PGHOTATRVmlJvHULE5jTXanQqhQKOhWpXnUkJ7YL7FUOOqouiXTMXXLxT+8JBVFcjLr1sY3AEajr2jLc3NbxAk5zc8Jf")
                  );
                  this.moveShellElement(
                     var1,
                     var2,
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("UjX3PGHOTATRVmlJvHULE5jTXanQqhQKOhWpXnUkJ7YL7FUOOqouiXTMXXLxT+8JBVFcjLr1sY3AEajr2jLc3NbxAk5zc8Jf"),
                     var13,
                     var15,
                     var8.width,
                     var8.height
                  );
               } else {
                  EditorRect var23 = var2.shellStaticRectCache == null
                     ? null
                     : var2.shellStaticRectCache
                        .get(
                           Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                              "UjX3PGHOTATRVmlJvHULE5jTXanQqhQKOhWpXnUkJ7YL7FUOOqouiXTMXXLxT+8JBVFcjLr1sY3AEajr2jLc3NbxAk5zc8Jf"
                           )
                        );

                  for (String var25 : var2.cursorLayersDropdownResolvedPanelMemberIds) {
                     if (var25 != null && !var25.isBlank()) {
                        ConfigurationSection var39 = this.findShellBlockSection(var2, var25);
                        var10001 = new String[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1854389661, 21)];
                        var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 5)] = var39 == null
                           ? null
                           : var39.getString(
                              Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                                 "VqgeM2Aq+uqqBybLCsnUpFvJa3h/nu8/EwJzKL21riXD7S8+edRWl8UzSMdspTbZeVA5WYottRYf8tYUeJSCSuyVVV0="
                              )
                           );
                        String var27 = this.firstNonBlank(var10001);
                        if (this.belongsToSidebarPanel(var37, var27)) {
                           EditorRect var28 = this.findShellBlockRect(var2, var25);
                           if (var28 != null) {
                              double var29 = var28.x + var35;
                              double var31 = var28.y + var36;
                              if (var23 != null && var2.shellStaticRectCache != null) {
                                 EditorRect var33 = var2.shellStaticRectCache.get(var25);
                                 if (var33 != null) {
                                    var29 = var13 + (var33.x - var23.x);
                                    var31 = var15 + (var33.y - var23.y);
                                 }
                              }

                              this.applyCursorPageDropdownTeleportDurationZero(var1, var25);
                              this.moveShellElement(var1, var2, var25, var29, var31, var28.width, var28.height);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public boolean selectLayerTargetForContextDropdown(Player var1, EditorSession var2, String var3) {
      if ((342797539 * 342797539 - 342797539 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var2 == null || var3 == null || var3.isBlank()) {
         return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1163987339, 23) != 0);
      } else if (this.isSelectedTarget(var2, var3)) {
         return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 7) != 0);
      } else {
         int var4 = this.equalsNullable(var2.selectedElementId, var3) && var2.additionalSelectedElementIds.isEmpty()
            ? Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1480745618, 29)
            : Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1228522207, 21);
         if (var4 == 0) {
            return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 10) != 0);
         } else {
            var2.selectedElementId = var3;
            var2.additionalSelectedElementIds.clear();
            this.normalizeSelectionState(var2);
            var2.activeHandle = null;
            var2.resizeAnchorValid = (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-192488808, 7) != 0);
            var2.handlesCollapsed = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 31) != 0);
            this.updateHoveredHighlight(var1, var2, var2.hoveredElementId, null);
            var2.hoveredElementId = null;
            this.clearEditorOverlaysOnly(var1, var2);
            if (var2.selectedElementId != null) {
               this.showSelectionOverlay(var1, var2);
            }

            this.updateEditorPropertiesSidebar(var1, var2);
            this.renderLayersPanel(var1, var2);
            return (var4 != 0);
         }
      }
   }

   public void applyCursorPageDropdownTeleportDurationZero(Player var1, String var2) {
      if ((306138276 * (306138276 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = Xqedii_20ks58huv110f(var2);
         this.applyRuntimeHudNoTransition(var1, var3);
      }
   }

   @Override
   public boolean handleCursorPageDropdownClick(Player var1, EditorSession var2, GuiService.ClickType var3, double var4, double var6) {
      if ((-260702862 * -260702862 + -260702862 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var2 == null || !var2.editMode || !var2.previewMode || var3 == null) {
         return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 27) != 0);
      } else if (var2.welcomePopupVisible || var2.savePopupVisible || var2.preferencesPopupVisible) {
         return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-627510138, 23) != 0);
      } else if (var2.activeTool == EditorTool.ZOOM && var3 == GuiService.ClickType.RIGHT) {
         this.cancelCursorPageDropdownPendingOpen(var2);
         if (var2.cursorPageDropdownVisible) {
            this.setCursorPageDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(229978601, 16) != 0));
         }

         return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 5) != 0);
      } else {
         double var8 = var2.shellHitX(var4);
         double var10 = var2.shellHitY(var6);
         boolean var12 = var2.cursorPageDropdownVisible;
         int var13 = !this.isInsideShellBlock(
                  var2,
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("PBePdxnwzsvIURkeg/XnYpeGrSNDp5d6OSNU6lyVMERqpi5M3tWYjzRcsmHJRfHXL0cVl2dcYUqDdxVawgxQNemNBT8="),
                  var8,
                  var10
               )
               && !this.isInsideShellBlock(
                  var2,
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("PBePdxnwzsvIURkeg/XnYpeGrSNDp5d6OSNU6lyVMERqpi5M3tWYjzRcsmHJRfHXL0cVl2dcYUqDdxVawgxQNemNBT8="),
                  var4,
                  var6
               )
            ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 27)
            : Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1653700675, 13);
         if (var3 == GuiService.ClickType.LEFT) {
            if (!var12) {
               this.cancelCursorPageDropdownPendingOpen(var2);
               return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 13) != 0);
            } else {
               String var16 = this.resolveHoveredCursorPageDropdownTargetId(var2, var4, var6);
               if (var16 != null) {
                  this.cancelCursorPageDropdownPendingOpen(var2);
                  this.setCursorPageDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 19) != 0));
                  this.playEditorSfx(
                     var1,
                     var2,
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("iPHAX316MwVkWeIiBB+x5tpgdhfrAL2TOv5svokvdJzVIJ10O5tvjMo9Qucl5JmsReRpkEU9BzytpjUB7AicofLV22hytw==")
                  );
                  this.handleCursorPageDropdownAction(var1, var2, var16);
                  return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 23) != 0);
               } else if (var13 != 0) {
                  return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 20) != 0);
               } else {
                  this.cancelCursorPageDropdownPendingOpen(var2);
                  this.setCursorPageDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1728805158, 31) != 0));
                  return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-320113347, 8) != 0);
               }
            }
         } else if (var3 != GuiService.ClickType.RIGHT) {
            return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(2067455311, 6) != 0);
         } else {
            if (var2.activeTool == EditorTool.ANIMATION) {
               int var14 = !this.isInsideShellBlock(
                        var2,
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("KZgSDetJjQqGKAWvae4PfZMQDWVICDFxB/sind0J6BHvfQbm/qyWvZ0/jXPQx+NaUo/bXIJVQ7eetJ1PbUir4Q=="),
                        var4,
                        var6
                     )
                     && !this.isInsideShellBlock(
                        var2,
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("KZgSDetJjQqGKAWvae4PfZMQDWVICDFxB/sind0J6BHvfQbm/qyWvZ0/jXPQx+NaUo/bXIJVQ7eetJ1PbUir4Q=="),
                        var8,
                        var10
                     )
                     && !this.isInsideShellBlock(
                        var2,
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("+qRgTYnchhvoLiqkwqKTT7q5NPU8RdEL/vJaDE+Xp6/39ZKvpYcnCdWRsjiukHr4FFguuXCIURCqneDgkow="),
                        var4,
                        var6
                     )
                     && !this.isInsideShellBlock(
                        var2,
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("+qRgTYnchhvoLiqkwqKTT7q5NPU8RdEL/vJaDE+Xp6/39ZKvpYcnCdWRsjiukHr4FFguuXCIURCqneDgkow="),
                        var8,
                        var10
                     )
                     && !this.isInsideShellBlock(
                        var2,
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                           "tYZIPHEbqrV/tsEpKtl7HeAYmBHe13PrRE/Z/EmWUMkogC8y5dl77+MOjlboZpGdUyz+5Cyg4JLvTHs7nv/eoPIeU7BmGyESm31q4IgEo+k="
                        ),
                        var4,
                        var6
                     )
                     && !this.isInsideShellBlock(
                        var2,
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                           "tYZIPHEbqrV/tsEpKtl7HeAYmBHe13PrRE/Z/EmWUMkogC8y5dl77+MOjlboZpGdUyz+5Cyg4JLvTHs7nv/eoPIeU7BmGyESm31q4IgEo+k="
                        ),
                        var8,
                        var10
                     )
                  ? Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 1)
                  : Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1186358963, 21);
               if (var14 != 0) {
                  this.cancelCursorPageDropdownPendingOpen(var2);
                  if (var12 && var13 == 0) {
                     this.setCursorPageDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1443928229, 27) != 0));
                  }

                  return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-320113347, 8) != 0);
               }
            }

            if (!this.isInsidePreviewArea(var2, var4, var6)) {
               this.cancelCursorPageDropdownPendingOpen(var2);
               if (var12) {
                  this.setCursorPageDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 6) != 0));
               }

               return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 23) != 0);
            } else if (!var2.cursorLayersDropdownVisible
               || !this.isInsideShellBlock(
                     var2,
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("UjX3PGHOTATRVmlJvHULE5jTXanQqhQKOhWpXnUkJ7YL7FUOOqouiXTMXXLxT+8JBVFcjLr1sY3AEajr2jLc3NbxAk5zc8Jf"),
                     var8,
                     var10
                  )
                  && !this.isInsideShellBlock(
                     var2,
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("UjX3PGHOTATRVmlJvHULE5jTXanQqhQKOhWpXnUkJ7YL7FUOOqouiXTMXXLxT+8JBVFcjLr1sY3AEajr2jLc3NbxAk5zc8Jf"),
                     var4,
                     var6
                  )) {
               String var15 = this.resolvePreviewContextTargetByHit(var2, var4, var6);
               if (var15 != null && !var15.isBlank()) {
                  this.cancelCursorPageDropdownPendingOpen(var2);
                  if (var2.cursorPageDropdownVisible) {
                     this.setCursorPageDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1653692483, 13) != 0));
                  }

                  return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 17) != 0);
               } else {
                  this.setFileDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 12) != 0));
                  this.setEditDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1891078743, 3) != 0));
                  this.setSelectionDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(2077873898, 1) != 0));
                  this.setLayerDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1628015174, 31) != 0));
                  this.setWindowDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1891078743, 3) != 0));
                  this.cancelCursorLayersDropdownPendingOpen(var2);
                  if (var2.cursorLayersDropdownVisible) {
                     this.setCursorLayersDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 10) != 0));
                  }

                  this.cancelCursorPageDropdownPendingOpen(var2);
                  if (var2.cursorPageDropdownVisible) {
                     this.setCursorPageDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 26) != 0));
                  }

                  this.moveCursorPageDropdownTo(var1, var2, var4, var6);
                  this.scheduleCursorPageDropdownOpen(var1, var2, var4, var6);
                  return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 2) != 0);
               }
            } else {
               this.cancelCursorPageDropdownPendingOpen(var2);
               if (var12) {
                  this.setCursorPageDropdownVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 19) != 0));
               }

               return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 10) != 0);
            }
         }
      }
   }

   public String resolvePreviewContextTargetByHit(EditorSession var1, double var2, double var4) {
      if ((1409134946 * (1409134946 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.previewMode) {
         if (!this.isInsideEditorChromeBlock(var1, var2, var4) && this.isInsidePreviewArea(var1, var2, var4)) {
            HoverElement var6 = null;

            for (HoverElement var8 : var1.elements) {
               String var9 = this.targetIdOf(var8);
               if (var9 != null && !var9.isBlank()) {
                  EditorRect var10 = this.getHoverBounds(var1, var8);
                  if (var10 != null) {
                     double var11 = var10.x;
                     double var13 = var10.y;
                     double var15 = var10.maxX();
                     double var17 = var10.maxY();
                     if (var2 >= var11 && var2 <= var15 && var4 >= var13 && var4 <= var17 && (var6 == null || var8.runtimeZ >= var6.runtimeZ)) {
                        var6 = var8;
                     }
                  }
               }
            }

            return var6 == null ? null : this.targetIdOf(var6);
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public void cancelCursorPageDropdownPendingOpen(EditorSession var1) {
      if ((1235795791 * 1235795791 + 1235795791 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         var1.cursorPageDropdownOpenToken = var1.cursorPageDropdownOpenToken + Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1578145957, 27);
      }
   }

   public void scheduleCursorPageDropdownOpen(Player var1, EditorSession var2, double var3, double var5) {
      if (((1418142432 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         int var7 = var2.cursorPageDropdownOpenToken = var2.cursorPageDropdownOpenToken + Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1653700675, 13);
         Xqedii_38o2zes.Xqedii_2sj63w1(this.plugin, var1, () -> {
            if (((-1333829929 << 1 | 1) & 1) == 0) {
               throw new IllegalStateException();
            } else if (var1.isOnline()) {
               EditorSession var8 = this.editorSessions.get(var1.getUniqueId());
               if (var8 != null && var8 == var2 && var7 == var8.cursorPageDropdownOpenToken) {
                  if (var8.editMode && var8.previewMode) {
                     this.moveCursorPageDropdownTo(var1, var8, var3, var5);
                     this.setCursorPageDropdownVisible(var1, var8, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1378814029, 18) != 0));
                     this.moveCursorPageDropdownTo(var1, var8, var3, var5);
                     this.updateCursorPageDropdownHover(var1, var8, var3, var5);
                  }
               }
            }
         }, Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-581701890137810725L, 44));
      }
   }

   public boolean handleCursorPageDropdownAction(Player var1, EditorSession var2, String var3) {
      if ((-814480899 * (-814480899 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         int var5 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1480745617, 29);
         switch (var3.hashCode()) {
            case -1309729674:
               if (var3.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "GsIutyAp0jncFXHCX3vFqeDLw3KqGLPneCegRqk+lcRO7SZ3SX4M0EF2Jx91Q92KqDSQjYC1T0t/bGgE86c2xmNPmX1p51A2a62O7NyTiP77L8bnLOyTbg=="
                  )
               )) {
                  var5 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(565618237, 13);
               }
               break;
            case -1303248572:
               if (var3.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "yoGuNb77pKIBIK/WCAekwdqd3qnWyk1/w/gfG9eX9OeFQjQrbKyUfc1QKPTnJN7zZaCWJcPNyBv/RIXormDHRlafV7HnRlAHUTZa8bsMxTsi1I0vep4g5w=="
                  )
               )) {
                  var5 = Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1989568179, 28);
               }
               break;
            case -1303203917:
               if (var3.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "LUU/4sW9YB7VZCWvcl19S9dIWXOVQEvnxvix+qiMwFuq8t3zE+VB03FgYOrD45zKmd7uzjY1bU/p7G6Io1xTbZvBVso0nFz+QEecSNzNQEisi8kNO27uCA=="
                  )
               )) {
                  var5 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-2115016869, 27);
               }
               break;
            case 789250570:
               if (var3.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "f6RVh9rWJlodiqMSL7phPxzV/kfmjvMv6KD9udQu0U7vIaWVeu2CPv7dhIpUYQNqbTFSquzsKo8kr1Q7YhFgqsgRnVNtNiMkcFrmiA3WaZ/OSbrY5QI="
                  )
               )) {
                  var5 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-814007587, 30);
               }
               break;
            case 789539956:
               if (var3.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "VnlrWjaSdgllTKPlKmW15U+g/9APaCE/yc73nLLdPdCzbYiQSmYnQ/VBHL/ik88PA2DGz5uzm+SLyv+s94NQXvY41jZSet54Htv2DfjCJGjh55hKa90="
                  )
               )) {
                  var5 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-278439031, 3);
               }
               break;
            case 789564452:
               if (var3.equals(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "GdBqu7iB5M23Hgz6SnacRsUWLIAon2jJrjKDuRedxba6XGG9E205pzZ4YSEWS4Pu1cwuW0JzTKMgBPKz28KV8aIXnid8i6I12sZxPit4Qn7jsDWIaC8="
                  )
               )) {
                  var5 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-586187974, 22);
               }
         }
         return (switch (var5) {
            case 0 -> this.handleEditorSelectionNewBlock(var1, var2);
            case 1 -> this.handleEditorSelectionNewText(var1, var2);
            case 2 -> this.handleEditorSelectionNewItem(var1, var2);
            case 3 -> this.openEditorImageFilePopup(var1, var2);
            case 4 -> this.handleEditorSelectionNewSkin(var1, var2);
            case 5 -> this.handleEditorSelectionNewInput(var1, var2);
            default -> (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 22) != 0);
         });
      } else {
         return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1537863527, 19) != 0);
      }
   }

   public void setCursorPageDropdownVisible(Player var1, EditorSession var2, boolean var3) {
      if ((-680665573 * -680665573 - -680665573 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         if (var2.cursorPageDropdownVisible == var3) {
            if (var3) {
               double var12 = var2.cursorX + var2.hitboxOffsetX;
               double var13 = var2.cursorY + var2.hitboxOffsetY;
               this.moveCursorPageDropdownTo(var1, var2, var12, var13);
               this.updateCursorPageDropdownHover(var1, var2, var12, var13);
            }
         } else {
            double var4 = var2.cursorX + var2.hitboxOffsetX;
            double var6 = var2.cursorY + var2.hitboxOffsetY;
            var2.cursorPageDropdownVisible = var3;
            if (!var3) {
               var2.cursorPageDropdownHoverTargetId = null;
            } else {
               this.playEditorSfx(
                  var1, var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("V2dIqjrne+J1bLpBa2OhpuW2JeocY1Edm3tPk8HlD4X6SKPjU3iMNmPLjBtV/ha4F+53AqiV5ISzXw==")
               );
            }

            this.beginShellOpacityBatch(var2);

            try {
               this.setSidebarPanelVisible(
                  var1,
                  var2,
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("PBePdxnwzsvIURkeg/XnYpeGrSNDp5d6OSNU6lyVMERqpi5M3tWYjzRcsmHJRfHXL0cVl2dcYUqDdxVawgxQNemNBT8="),
                  var3
               );
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }

            if (var3) {
               this.updateCursorPageDropdownHover(var1, var2, var4, var6);
            } else {
               for (String var9 : Xqedii_l2une6g.Xqedii_22b64r8) {
                  this.setCursorPageDropdownItemVisual(var1, var2, var9, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1394512091, 28) != 0));
               }
            }
         }
      }
   }

   @Override
   public void updateCursorPageDropdownHover(Player var1, EditorSession var2, double var3, double var5) {
      if (((1196061329 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         String var7 = var2.cursorPageDropdownVisible ? this.resolveHoveredCursorPageDropdownTargetId(var2, var3, var5) : null;
         if (!this.equalsNullable(var2.cursorPageDropdownHoverTargetId, var7)) {
            var2.cursorPageDropdownHoverTargetId = var7;

            for (String var9 : Xqedii_l2une6g.Xqedii_22b64r8) {
               this.setCursorPageDropdownItemVisual(var1, var2, var9, this.equalsNullable(var9, var7));
            }
         }
      }
   }

   public void setCursorPageDropdownItemVisual(Player var1, EditorSession var2, String var3, boolean var4) {
      if ((-1038418576 * -1038418576 - -1038418576 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String[] var5 = Xqedii_l2une6g.Xqedii_38s2h9s.get(var3);
         if (var5 != null && var5.length >= Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1860681117, 21)) {
            this.setShellText(
               var1,
               Xqedii_37tf8ha6yrc87(var3),
               var4 ? var5[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-394536490, 25)] : var5[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(987582331, 14)]
            );
         }

         this.setShellColor(
            var1,
            var2,
            var3,
            var4
               ? Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("vFKbdC+trMfMvTdpemUT5SaLMmCun2FdAp8tbt0I4twdGWMVCU2goA==")
               : Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("/PxdxVUeN3+036BgV6VC9G/t84rfe4wnAyTGA5d+vfjOdx84GkND8w==")
         );
      }
   }

   public String resolveHoveredCursorPageDropdownTargetId(EditorSession var1, double var2, double var4) {
      if ((1820258765 ^ 1820258765) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.cursorPageDropdownVisible) {
         double var6 = var1.shellHitX(var2);
         double var8 = var1.shellHitY(var4);
         int var10 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1230619359, 21);

         for (String var12 : Xqedii_l2une6g.Xqedii_22b64r8) {
            String var13 = Xqedii_343ia2njxyk9x(var12);
            EditorRect var14 = this.findShellBlockRect(var1, var13);
            if (var14 != null) {
               var10++;
               if (var14.contains(var6, var8)) {
                  return var12;
               }
            }
         }

         if (var10 == Xqedii_l2une6g.Xqedii_22b64r8.size()) {
            return null;
         } else {
            EditorRect var15 = this.findShellBlockRect(
               var1, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("PBePdxnwzsvIURkeg/XnYpeGrSNDp5d6OSNU6lyVMERqpi5M3tWYjzRcsmHJRfHXL0cVl2dcYUqDdxVawgxQNemNBT8=")
            );
            if (var15 != null && var15.contains(var6, var8)) {
               double var16 = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149447249403154203L, 20));
               int var19 = (int)Math.floor((var8 - var15.y) / Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444750515859299782L, 27)));
               if (var19 >= 0 && var19 < Xqedii_l2une6g.Xqedii_22b64r8.size()) {
                  return Xqedii_l2une6g.Xqedii_22b64r8.get(var19);
               }
            }

            for (String var18 : Xqedii_l2une6g.Xqedii_22b64r8) {
               if (this.isInsideShellBlock(var1, var18, var6, var8)) {
                  return var18;
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   public void moveCursorPageDropdownTo(Player var1, EditorSession var2, double var3, double var5) {
      if ((863100070 * 863100070 + 863100070 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         ConfigurationSection var7 = this.findShellBlockSection(
            var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("PBePdxnwzsvIURkeg/XnYpeGrSNDp5d6OSNU6lyVMERqpi5M3tWYjzRcsmHJRfHXL0cVl2dcYUqDdxVawgxQNemNBT8=")
         );
         EditorRect var8 = this.findShellBlockRect(
            var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("PBePdxnwzsvIURkeg/XnYpeGrSNDp5d6OSNU6lyVMERqpi5M3tWYjzRcsmHJRfHXL0cVl2dcYUqDdxVawgxQNemNBT8=")
         );
         if (var7 != null && var8 != null) {
            double var9 = var2.shellHitX(var3);
            double var11 = var2.shellHitY(var5);
            double var13 = var9;
            double var15 = var11;
            EditorRect var17 = this.findShellBlockRect(var2, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("L8qhl2r+PT2fF6nJJpDVZYJQu4YRtlFk69rD4GBBEGDzk42bJYOaTG5B"));
            if (var17 != null) {
               var13 = Math.max(var17.x, Math.min(var17.maxX() - var8.width, var9));
               var15 = Math.max(var17.y, Math.min(var17.maxY() - var8.height, var11));
            }

            double var18 = var13 - var8.x;
            double var20 = var15 - var8.y;
            if (!(Math.abs(var18) < Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-954089492278727459L, 56)))
               || !(Math.abs(var20) < Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(-2976124924430701788L, 32)))) {
               String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-619121530, 23)];
               var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1230619359, 21)] = var7.getString(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("VqgeM2Aq+uqqBybLCsnUpFvJa3h/nu8/EwJzKL21riXD7S8+edRWl8UzSMdspTbZeVA5WYottRYf8tYUeJSCSuyVVV0=")
               );
               String var22 = this.firstNonBlank(var10001);
               if (var22.isBlank()) {
                  this.applyCursorPageDropdownTeleportDurationZero(
                     var1, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("PBePdxnwzsvIURkeg/XnYpeGrSNDp5d6OSNU6lyVMERqpi5M3tWYjzRcsmHJRfHXL0cVl2dcYUqDdxVawgxQNemNBT8=")
                  );
                  this.moveShellElement(
                     var1,
                     var2,
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("PBePdxnwzsvIURkeg/XnYpeGrSNDp5d6OSNU6lyVMERqpi5M3tWYjzRcsmHJRfHXL0cVl2dcYUqDdxVawgxQNemNBT8="),
                     var13,
                     var15,
                     var8.width,
                     var8.height
                  );
               } else {
                  EditorRect var23 = var2.shellStaticRectCache == null
                     ? null
                     : var2.shellStaticRectCache
                        .get(
                           Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("PBePdxnwzsvIURkeg/XnYpeGrSNDp5d6OSNU6lyVMERqpi5M3tWYjzRcsmHJRfHXL0cVl2dcYUqDdxVawgxQNemNBT8=")
                        );
                  HashMap var24 = new HashMap();
                  int var25 = Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 29);

                  for (Map var27 : var2.shellBlocks) {
                     var25++;
                     ConfigurationSection var28 = this.mapToSection(var27);
                     if (var28 != null) {
                        var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184263859, 11)];
                        var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 3)] = var28.getString(
                           Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("VqgeM2Aq+uqqBybLCsnUpFvJa3h/nu8/EwJzKL21riXD7S8+edRWl8UzSMdspTbZeVA5WYottRYf8tYUeJSCSuyVVV0=")
                        );
                        String var29 = this.firstNonBlank(var10001);
                        if (this.belongsToSidebarPanel(var22, var29)) {
                           String var30 = this.resolveElementId(var28, var25, var24);
                           if (!var30.isBlank()) {
                              EditorRect var31 = this.findShellBlockRect(var2, var30);
                              if (var31 != null) {
                                 double var32 = var31.x + var18;
                                 double var34 = var31.y + var20;
                                 if (var23 != null && var2.shellStaticRectCache != null) {
                                    EditorRect var36 = var2.shellStaticRectCache.get(var30);
                                    if (var36 != null) {
                                       var32 = var13 + (var36.x - var23.x);
                                       var34 = var15 + (var36.y - var23.y);
                                    }
                                 }

                                 this.applyCursorPageDropdownTeleportDurationZero(var1, var30);
                                 this.moveShellElement(var1, var2, var30, var32, var34, var31.width, var31.height);
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

   public boolean handleScroll(Player var1, int var2) {
      if ((-341689289 * (-341689289 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
         if (var3 != null && var3.editMode && var3.previewMode && var2 != 0) {
            double var4 = var3.cursorX + var3.hitboxOffsetX;
            double var6 = var3.cursorY + var3.hitboxOffsetY;
            if (this.isEditorImageFilePopupVisible(var3)) {
               return this.handleEditorImageFilePopupScroll(var1, var3, var2, var4, var6);
            } else if (this.isAnyEditorPopupVisible(var3)) {
               return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1854389661, 21) != 0);
            } else if (this.handleActivePagesScroll(var1, var3, var4, var6, var2)) {
               return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1840353096, 19) != 0);
            } else if (var3.rightSidebarTab == EditorSidebarTab.PROPERTIES
               && this.isActionsSidebarMode(var3)
               && this.handleActionsScroll(var1, var3, var4, var6, var2)) {
               return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 18) != 0);
            } else if (var3.rightSidebarTab == EditorSidebarTab.LAYERS && this.handleLayersScroll(var1, var3, var4, var6, var2)) {
               return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 20) != 0);
            } else if (this.isAnyEditorDropdownVisible(var3)) {
               return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1150707379, 25) != 0);
            } else if (this.isInsidePreviewArea(var3, var4, var6) && this.handleRuntimeScrollBox(var1, var3, var2, var4, var6)) {
               return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(920176548, 18) != 0);
            } else {
               double var8 = Math.pow(Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(1098465782632976244L, 24)), Math.abs(var2));
               if (var2 > 0) {
                  var8 = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-6153394195530908682L, 59)) / var8;
               }

               double var10 = var4;
               double var12 = var6;
               Entity var14 = this.hudService.getHud(var1, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("41fWlxmS7Pziz3gGxFD5OeLGBndPMng3ifJxLIlvFqMGzvHiMzgtcQ=="));
               if (var14 != null) {
                  Vector var15 = this.hudService.getHudLocation(var14);
                  if (var15 != null) {
                     var10 = var15.getX() + var3.hitboxOffsetX;
                     var12 = var15.getY() + var3.hitboxOffsetY;
                     var3.cursorX = var15.getX();
                     var3.cursorY = var15.getY();
                  }
               }

               boolean var16 = this.applyPreviewZoom(var3, var10, var12, var8);
               if (!var16) {
                  return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-619121530, 23) != 0);
               } else if (var3.activeHandle != null && var3.selectedElementId != null) {
                  var3.resizeLastCursorX = this.toLogicalCursorX(var3, var10);
                  var3.resizeLastCursorY = this.toLogicalCursorY(var3, var12);
                  this.renderPreviewPageOutline(var1, var3);
                  this.rerenderEditableContent(var1, var3);
                  this.renderEditorTransparencyOverlay(var1, var3);
                  this.updatePageInfoReadout(var1, var3);
                  this.updateCursorPositionReadout(var1, var3, var10, var12);
                  this.updateAlignToolbarOverlay(var1, var3, var10, var12, (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 10) != 0));
                  return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1891078735, 3) != 0);
               } else {
                  this.refreshPreviewProjection(var1, var3);
                  this.updateCursorPositionReadout(var1, var3, var10, var12);
                  this.updateAlignToolbarOverlay(var1, var3, var10, var12, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1220288818, 20) != 0));
                  return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 26) != 0);
               }
            }
         } else {
            return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 31) != 0);
         }
      }
   }

   public boolean handleEditorMovementShortcut(Player var1, double var2, double var4, float var6) {
      if ((252129324 * (252129324 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 24) != 0);
      } else {
         EditorSession var7 = this.editorSessions.get(var1.getUniqueId());
         if (var7 == null || !var7.editMode || !var7.previewMode) {
            return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 11) != 0);
         } else if (this.isAnyEditorDropdownVisible(var7)) {
            return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 2) != 0);
         } else if (var7.activeTool == EditorTool.COMMANDS) {
            return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-278438999, 3) != 0);
         } else {
            double var8 = Math.abs(var2) + Math.abs(var4);
            if (var8 < Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-8378298183225250602L, 15))) {
               return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1628015174, 31) != 0);
            } else {
               double var10 = Math.toRadians(var6);
               double var12 = -Math.sin(var10);
               double var14 = Math.cos(var10);
               double var16 = Math.cos(var10);
               double var18 = Math.sin(var10);
               double var20 = var2 * var12 + var4 * var14;
               double var22 = var2 * var16 + var4 * var18;
               double var24 = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4800237111224970994L, 1));
               if (Math.abs(var20) >= Math.abs(var22)) {
                  if (var20 > var24) {
                     this.setActiveTool(var1, var7, EditorTool.CURSOR);
                     return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 25) != 0);
                  } else if (var20 < -var24) {
                     this.setActiveTool(var1, var7, EditorTool.SCALE);
                     return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261299, 9) != 0);
                  } else {
                     return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1539910457, 10) != 0);
                  }
               } else if (var22 > var24) {
                  this.setRightSidebarTab(var1, var7, EditorSidebarTab.LAYERS, (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184245427, 14) != 0));
                  return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1316009875, 27) != 0);
               } else if (var22 < -var24) {
                  this.setRightSidebarTab(var1, var7, EditorSidebarTab.PROPERTIES, (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261810, 0) != 0));
                  return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(460088274, 17) != 0);
               } else {
                  return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 24) != 0);
               }
            }
         }
      }
   }

   public void handleRuntimeHudFollowMove(Player var1, double var2, double var4, double var6) {
      if (((-1507647295 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         if (Double.isFinite(var2) && Double.isFinite(var4) && Double.isFinite(var6)) {
            if (!(Math.abs(var2) + Math.abs(var4) + Math.abs(var6) <= Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-5699699160124162677L, 62)))) {
               EditorSession var8 = this.editorSessions.get(var1.getUniqueId());
               if (var8 != null && !var8.editMode && var8.hudTextOnlyMode) {
                  LinkedHashSet var9 = this.collectRuntimeHudFollowRuntimeIds(var8);
                  int var10 = this.resolveEditorHudTransitionTicks(var8);
                  if (var10 <= 0) {
                     var10 = Math.max(Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1537339239, 19), this.resolveCursorInterpDurationTicks());
                  }

                  this.hudService
                     .refreshRuntimeHudAnchoring(
                        var1, new ArrayList<>(var9), new Vector(var2, var4, var6), var10, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-556878013, 4) != 0)
                     );
               }
            }
         }
      }
   }

   public void handleRuntimeHudSneakToggle(Player var1, boolean var2) {
      if ((-1584668620 ^ -1584668620) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
         if (var3 != null && !var3.editMode && var3.hudTextOnlyMode) {
            double var4 = var2
               ? Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(-8791300739006009128L, 22))
               : Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149447249402688283L, 63));
            this.applyRuntimeHudSneakVisualOffset(var1, var3, var4);
         }
      }
   }

   public void syncRuntimeHudSneakVisualOffset(Player var1, EditorSession var2) {
      if ((-1587308491 * (-1587308491 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.editMode && var2.hudTextOnlyMode) {
         double var3 = var1.isSneaking()
            ? Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(6824461298964940579L, 22))
            : Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(7006967679969858624L, 41));
         this.applyRuntimeHudSneakVisualOffset(var1, var2, var3);
      }
   }

   public void applyRuntimeHudSneakVisualOffset(Player var1, EditorSession var2, double var3) {
      if (((-1974546174 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         double var5 = Double.isFinite(var2.runtimeHudSneakVisualOffsetY)
            ? var2.runtimeHudSneakVisualOffsetY
            : Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-22566439931735077L, 61));
         double var7 = Double.isFinite(var3) ? var3 : Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444750515892723142L, 7));
         double var9 = var7 - var5;
         if (!(Math.abs(var9) <= Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-477044746139363730L, 55)))) {
            LinkedHashSet var11 = this.collectRuntimeHudFollowRuntimeIds(var2);
            if (!var11.isEmpty()) {
               int var12 = this.resolveEditorHudTransitionTicks(var2);
               if (var12 <= 0) {
                  var12 = Math.max(Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(920176548, 18), this.resolveCursorInterpDurationTicks());
               }

               int var13 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 25);

               for (String var15 : (Iterable<String>) (var11)) {

                  if (var15 != null && !var15.isBlank()) {
                     Entity var16 = this.hudService.getHud(var1, var15);
                     if (var16 != null) {
                        Vector var17 = this.hudService.getHudLocation(var16);
                        if (var17 != null) {
                           Vector var18 = var17.clone();
                           var18.setY(var18.getY() + var9);
                           this.hudService.moveHud(var16, var18, var12);
                           var13 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1113756025, 5);
                        }
                     }
                  }
               }

               if (var13 != 0) {
                  var2.runtimeHudSneakVisualOffsetY = var7;
               }
            }
         }
      }
   }

   public LinkedHashSet<String> collectRuntimeHudFollowRuntimeIds(EditorSession var1) {
      if ((328585850 * 328585850 + 328585850 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         LinkedHashSet var2 = new LinkedHashSet();
         if (var1 != null && var1.elements != null && !var1.elements.isEmpty()) {
            for (HoverElement var4 : var1.elements) {
               if (var4 != null) {
                  String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1378814029, 18)];
                  var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1891078743, 3)] = var4.id;
                  String var5 = this.firstNonBlank(var10001);
                  if (!var5.isBlank()) {
                     this.appendRuntimeHudFollowIds(var2, var5);
                  }
               }
            }

            return var2;
         } else {
            return var2;
         }
      }
   }

   public void appendRuntimeHudFollowIds(LinkedHashSet<String> var1, String var2) {
      if ((131276561 * 131276561 - 131276561 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         var1.add(var2);
         var1.add(Xqedii_1hkmcjq9x1pcm(var2));
         var1.add(Xqedii_347skoa07ev6r(var2));
         this.appendRoundedRuntimeHudFollowIds(var1, var2);
         String var3 = Xqedii_2n63d6c2lhkbh(var2);
         this.appendRoundedRuntimeHudFollowIds(var1, var3);
      }
   }

   public void appendRoundedRuntimeHudFollowIds(LinkedHashSet<String> var1, String var2) {
      if (((-477306034 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         var1.add(Xqedii_2er5wicv2vwvt(var2));
         var1.add(Xqedii_28ktklb5bamob(var2));
         var1.add(Xqedii_1lc72mbooh2fs(var2));
         var1.add(Xqedii_1ab6sxj9ooe5a(var2));
         var1.add(Xqedii_2epsc92qf1vhi(var2));
         var1.add(Xqedii_1tqgryhamx42y(var2));
         var1.add(Xqedii_4853dr9tcgwb(var2));
         var1.add(Xqedii_2ldefpltyvjgo(var2));
         var1.add(Xqedii_3c4ef9zukixjq(var2));
      }
   }

   @Override
   public void renderPage(Player var1, EditorSession var2) {
      if ((1173880080 ^ 1173880080) != 0) {
         throw new IllegalStateException();
      } else {
         this.renderPage(var1, var2, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-789072979, 26) != 0));
      }
   }

   @Override
   public void renderPage(Player var1, EditorSession var2, boolean var3) {
      if ((952397587 ^ 952397587) != 0) {
         throw new IllegalStateException();
      } else {
         if (var2.previewMode) {
            this.renderEditorShell(var1, var2);
            this.renderEditorToolSidebar(var1, var2);
            this.renderPreviewPageOutline(var1, var2);
            this.setWelcomePopupVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 26) != 0));
            this.setSavePopupVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 15) != 0));
            this.setPreferencesPopupVisible(var1, var2, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-581993670, 22) != 0));
            this.refreshSavePopupFields(var1, var2);
            this.refreshPreferencesPopupFields(var1, var2);
            this.setShellColor(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("RJyryw03cqJtWdLXyhCBl6Fv7Ya1kS6jah/DFf6AqmpDZlNsBeKVF08yR1oVHnJDTMmOANKJrNE="),
               var2.editorColor1
            );
            this.setShellColor(
               var1,
               var2,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("j0gx4JRjqUlygEWtA0y7AXs4LV7p36Temq7Ek5zOOQQtJDytO7+L63/E1qMeHjPFdX9srFluKpU="),
               var2.editorColor2
            );
            this.syncActivePagesRenderedSlotStates(var2);
         }

         if (var3 || var2.renderBlocks == null) {
            var2.renderBlocks = this.resolveRenderableBlocks(var2.rawBlocks, var2.components, var2);
         }

         this.renderResolvedContent(var1, var2);
         this.updatePageInfoReadout(var1, var2);
         this.updateSidebarTabVisualState(
            var1, var2, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1833728579, 22) != 0), (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 7) != 0)
         );
         this.updateEditorPropertiesSidebar(var1, var2);
         this.queueShellSpawnOpacityRetoggles(var1, var2);
         if (var2.hudTextOnlyMode && !var2.editMode) {
            this.syncRuntimeHudSneakVisualOffset(var1, var2);
         }
      }
   }

   public void schedulePreviewSizeToastHide(Player var1, EditorSession var2) {
      if (((-530159811 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         int var3 = var2.previewSizeToastToken = var2.previewSizeToastToken + Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 24);
         var2.previewSizeToastVisible = (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 19) != 0);
         var2.previewSizeToastPendingReveal = (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 13) != 0);
         this.schedulePreviewSizeToastAnchorStabilization(var1, var2, var3);
         Xqedii_38o2zes.Xqedii_2sj63w1(
            this.plugin,
            var1,
            () -> {
               if ((-1865667845 ^ -1865667845) != 0) {
                  throw new IllegalStateException();
               } else if (var1.isOnline()) {
                  EditorSession var4 = this.editorSessions.get(var1.getUniqueId());
                  if (var4 != null && var4 == var2 && var3 == var4.previewSizeToastToken) {
                     if (var4.previewSizeToastPendingReveal) {
                        this.updatePageInfoReadout(var1, var4);
                        var4.previewSizeToastVisible = (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(2077873896, 1) != 0);
                        var4.previewSizeToastPendingReveal = (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1674713962, 1) != 0);
                        this.beginShellOpacityBatch(var4);

                        try {
                           this.setSidebarPanelVisible(
                              var1,
                              var4,
                              Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("Fm7lilE0DWLWwKIMNGmv2Y1gVupBparpvHmSghZVgi70loRGc6DfQNDjI83ynEr0rBZNzw=="),
                              (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-320113603, 8) != 0)
                           );
                        } finally {
                           this.endShellOpacityBatch(var1, var4);
                        }

                        this.updatePageInfoReadout(var1, var4);
                        Xqedii_38o2zes.Xqedii_2sj63w1(
                           this.plugin,
                           var1,
                           () -> {
                              if ((1510707541 * 1510707541 - 1510707541 & 1) != 0) {
                                 throw new IllegalStateException();
                              } else if (var1.isOnline()) {
                                 EditorSession var4x = this.editorSessions.get(var1.getUniqueId());
                                 if (var4x != null && var4x == var4 && var3 == var4x.previewSizeToastToken) {
                                    if (var4x.previewSizeToastVisible) {
                                       var4x.previewSizeToastVisible = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 16) != 0);
                                       var4x.previewSizeToastPendingReveal = (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1255020275, 24) != 0);
                                       this.beginShellOpacityBatch(var4x);

                                       try {
                                          this.setSidebarPanelVisible(
                                             var1,
                                             var4x,
                                             Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("Fm7lilE0DWLWwKIMNGmv2Y1gVupBparpvHmSghZVgi70loRGc6DfQNDjI83ynEr0rBZNzw=="),
                                             (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 29) != 0)
                                          );
                                       } finally {
                                          this.endShellOpacityBatch(var1, var4x);
                                       }
                                    }
                                 }
                              }
                           },
                           Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-290244014650373011L, 43)
                        );
                     }
                  }
               }
            },
            Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(2319528488668667786L, 32)
         );
      }
   }

   public void schedulePreviewSizeToastAnchorStabilization(Player var1, EditorSession var2, int var3) {
      if ((350660824 * 350660824 + 350660824 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         long[] var4 = Xqedii_l2une6g.Xqedii_1bahg8m;
         int var5 = var4.length;

         for (int var6 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1407110839, 28); var6 < var5; var6++) {
            long var7 = var4[var6];
            if (var7 > Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-22566439931735077L, 61)) {
               Xqedii_38o2zes.Xqedii_2sj63w1(this.plugin, var1, () -> {
                  if ((1973774780 ^ 1973774780) != 0) {
                     throw new IllegalStateException();
                  } else if (var1.isOnline()) {
                     EditorSession var4x = this.editorSessions.get(var1.getUniqueId());
                     if (var4x != null && var4x == var2 && var3 == var4x.previewSizeToastToken) {
                        if (var4x.previewSizeToastVisible || var4x.previewSizeToastPendingReveal) {
                           this.updatePageInfoReadout(var1, var4x);
                        }
                     }
                  }
               }, var7);
            }
         }
      }
   }

   public void queueShellSpawnOpacityRetoggles(Player var1, EditorSession var2) {
      if (((-578944836 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         if (var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
            HashMap var3 = new HashMap();
            int var4 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1394512091, 28);

            for (Map var6 : var2.shellBlocks) {
               var4++;
               ConfigurationSection var7 = this.mapToSection(var6);
               if (var7 != null) {
                  String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1864666338, 12)];
                  var10001[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 27)] = var7.getString(
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("67bwboawFEWbkseanySzvg1QFeC0Xdd4zDW6+CtiAYQDT+ro")
                  );
                  var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 5)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "BD52BFHhkJOFBAfs8EKh+2AXSNN6Pr0OfcwazKku2VNZ8JVcJ5Y="
                  );
                  String var8 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
                  if (Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("BD52BFHhkJOFBAfs8EKh+2AXSNN6Pr0OfcwazKku2VNZ8JVcJ5Y=").equals(var8)
                     || Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("Qh7aR7gBntvpdL5W0IWxucVTclLRizkKvAPuhkfF+yDwsnXO").equals(var8)
                     || this.isRoundedType(var8)) {
                     String var9 = this.resolveElementId(var7, var4, var3);
                     if (!this.isEditorToolShellBlockId(var9)) {
                        int var10 = this.readOpacity(var7, Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(2102817382, 22));
                        int var11 = this.resolveShellBlockInitialOpacity(var2, var9, var7, var10);
                        String var12 = this.shellSpawnOpacityRetoggleTargetId(var9);
                        if (!var12.isBlank()) {
                           boolean var13 = this.readRefreshOpacityFlag(var7);
                           this.queueSpawnOpacityRetoggle(var1, var2, var12, var9, var11, var13);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void clearGuiRideActionBar(Player var1) {
      if (((-1455947655 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         Xqedii_nd6rkr3.Xqedii_2n2p2n9(
            var1,
            Xqedii_3uxgwyg.Xqedii_2p0af0b
               .deserialize(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("d7ZnGCrfB/aKdEtNV0MbldQk/MbTRH5T+Mh/6qPuov5q2Qwk+nPOYYWEf71+zcQTRzk93+MVQKw="))
         );
      }
   }

   public void renderStackedPageElements(Player var1, EditorSession var2, String var3) {
      if ((-1569349703 ^ -1569349703) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String var4 = this.normalizePageKey(var3, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ=="));
         if (!var4.isBlank()) {
            ArrayList var5 = new ArrayList<>(var2.elements);
            HashSet var6 = new HashSet();

            for (HoverElement var8 : (Iterable<HoverElement>) (var5)) {

               if (var8 != null && var8.id != null && !var8.id.isBlank()) {
                  var6.add(var8.id);
               }
            }

            HashSet var23 = new HashSet();
            HashSet var24 = new HashSet();
            ArrayList var9 = new ArrayList();
            HashMap var10 = new HashMap();
            int var11 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1864674530, 12);

            for (Map var13 : var2.renderBlocks) {
               var11++;
               ConfigurationSection var14 = this.mapToSection(var13);
               if (var14 != null) {
                  String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184260787, 9)];
                  var10001[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 17)] = var14.getString(
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("67bwboawFEWbkseanySzvg1QFeC0Xdd4zDW6+CtiAYQDT+ro")
                  );
                  var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 18)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "BD52BFHhkJOFBAfs8EKh+2AXSNN6Pr0OfcwazKku2VNZ8JVcJ5Y="
                  );
                  String var15 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
                  if (this.isRenderableBlockType(var15)) {
                     String var16 = this.resolveElementId(var14, var11, var10);
                     String var17 = this.runtimeElementId(var2, var16);
                     boolean var18 = this.isRuntimeHudStackPageMatch(var13, var4);
                     if (var18) {
                        var23.add(var17);
                     }

                     if (var18) {
                        var10001 = new String[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350244, 1)];
                        var10001[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 22)] = var14.getString(
                           Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("UvOSeulVjsgk9sEUg1wxmXselQOrcvOYwZ8UyvnLUsMpTX3zAB2m7gZrjue+qsALzV4Hdolz58PXPrhJ7MJxZQ==")
                        );
                        var10001[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-789072979, 26)] = var17;
                        String var19 = this.firstNonBlank(var10001);
                        if (this.isTargetVisible(var2, var19)) {
                           int var20 = !var6.contains(var17)
                              ? Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1938144403, 30)
                              : Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 18);
                           HoverElement var21 = this.renderResolvedElement(var1, var2, var14, var17, var15);
                           var24.add(var17);
                           var9.add(var21);
                           if (var20 != 0) {
                              boolean var22 = this.readRefreshOpacityFlag(var14);
                              this.queueSpawnOpacityRetoggle(var1, var2, var19, var16, var21.opacity, var22);
                           }
                        }
                     }
                  }
               }
            }

            ArrayList var25 = new ArrayList();

            for (HoverElement var27 : (Iterable<HoverElement>) (var5)) {

               if (var27 != null && !var24.contains(var27.id)) {
                  if (var23.contains(var27.id)) {
                     this.removeRenderedElementHud(var1, var27);
                  } else {
                     var25.add(var27);
                  }
               }
            }

            var25.addAll(var9);
            var2.elements.clear();
            var2.elements.addAll(var25);
            this.normalizeSelectionState(var2);
            if (var2.hudTextOnlyMode && !var2.editMode) {
               this.syncRuntimeHudSneakVisualOffset(var1, var2);
            }
         }
      }
   }

   @Override
   public void renderResolvedContent(Player var1, EditorSession var2) {
      if ((-235092035 * (-235092035 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1539909433, 10)];
         var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 14)] = var2.hoveredElementId;
         String var3 = this.firstNonBlank(var10001);
         ArrayList var4 = new ArrayList<>(var2.elements);
         HashSet var5 = new HashSet();

         for (HoverElement var7 : (Iterable<HoverElement>) (var4)) {

            if (var7 != null && var7.id != null && !var7.id.isBlank()) {
               var5.add(var7.id);
            }
         }

         HashSet var20 = new HashSet();
         ArrayList var21 = new ArrayList();
         HashMap var8 = new HashMap();
         int var9 = Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 10);

         for (Map var11 : var2.renderBlocks) {
            var9++;
            ConfigurationSection var12 = this.mapToSection(var11);
            if (var12 != null) {
               var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(-963221837, 30)];
               var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 21)] = var12.getString(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("67bwboawFEWbkseanySzvg1QFeC0Xdd4zDW6+CtiAYQDT+ro")
               );
               var10001[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-139219504, 2)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                  "BD52BFHhkJOFBAfs8EKh+2AXSNN6Pr0OfcwazKku2VNZ8JVcJ5Y="
               );
               String var13 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
               if (this.isRenderableBlockType(var13)) {
                  String var14 = this.resolveElementId(var12, var9, var8);
                  String var15 = this.runtimeElementId(var2, var14);
                  var10001 = new String[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(47608422, 30)];
                  var10001[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 4)] = var12.getString(
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("UvOSeulVjsgk9sEUg1wxmXselQOrcvOYwZ8UyvnLUsMpTX3zAB2m7gZrjue+qsALzV4Hdolz58PXPrhJ7MJxZQ==")
                  );
                  var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 2)] = var15;
                  String var16 = this.firstNonBlank(var10001);
                  if (this.isTargetVisible(var2, var16)) {
                     int var17 = !var5.contains(var15)
                        ? Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1837922883, 22)
                        : Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 19);
                     HoverElement var18 = this.renderResolvedElement(var1, var2, var12, var15, var13);
                     var20.add(var15);
                     var21.add(var18);
                     if (var17 != 0) {
                        boolean var19 = this.readRefreshOpacityFlag(var12);
                        this.queueSpawnOpacityRetoggle(var1, var2, var16, var14, var18.opacity, var19);
                     }
                  }
               }
            }
         }

         if (var2.previewMode) {
            this.renderEditorTransparencyOverlay(var1, var2);
         }

         for (HoverElement var24 : (Iterable<HoverElement>) (var4)) {

            if (!var20.contains(var24.id)) {
               this.removeRenderedElementHud(var1, var24);
            }
         }

         var2.elements.clear();
         var2.elements.addAll(var21);
         this.normalizeSelectionState(var2);
         var10001 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1938144403, 30)];
         var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1255020275, 24)] = var2.hoveredElementId;
         String var23 = this.firstNonBlank(var10001);
         if (!var3.isBlank() && this.equalsNullable(var3, var23)) {
            this.updateHoveredHighlight(var1, var2, null, var23);
         }
      }
   }

   public void queueSpawnOpacityRetoggle(Player var1, EditorSession var2, String var3, String var4, int var5, boolean var6) {
      if ((970894639 * 970894639 - 970894639 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.editMode && var2.previewMode && var3 != null && !var3.isBlank()) {
         if (!var2.spawnOpacityRetoggledTargetIds.contains(var3) && this.isTargetVisible(var2, var3)) {
            if (var6 || var5 < Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1183948467, 11)) {
               var2.spawnOpacityRetoggledTargetIds.add(var3);
               var2.pendingSpawnOpacityRetoggleTargetIds.add(var3);
               if (!var2.spawnOpacityRetoggleTaskQueued) {
                  var2.spawnOpacityRetoggleTaskQueued = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1721132723, 29) != 0);
                  Xqedii_38o2zes.Xqedii_2sj63w1(this.plugin, var1, () -> {
                     if (((-698870712 << 1 | 1) & 1) == 0) {
                        throw new IllegalStateException();
                     } else {
                        this.runSpawnOpacityRetoggleBatch(var1, var2);
                     }
                  }, Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-396835183678227467L, 10));
               }
            }
         }
      }
   }

   public boolean readRefreshOpacityFlag(ConfigurationSection var1) {
      if ((253030945 * 253030945 + 253030945 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 8) != 0);
      } else {
         Object var2 = var1.get(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("nn6do+c8izw29chCEN3le/88dBy/HrT4qGM7qf9nsIOtZdRdg+qfIqIXZRB7qIBrl37MAcY3WtM="));
         if (var2 == null) {
            var2 = var1.get(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("j4urW7t1LVq22CtLBG+RwNbOwaQJjwuiDXBfMJVTyBi8PHHFsRr9nDHFz+bI95Hsvh59cUpG+oI="));
         }

         if (var2 == null) {
            var2 = var1.get(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("wk72OmEgIDoWXgke/57dKDNiZN4bfaWcpBaVk1jV+K9db+7CrYhWVpuocvWcnJyYNDeaXqi/jMi7+A=="));
         }

         if (var2 == null) {
            var2 = var1.get(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("NoFcXWr0o4dNsb7nrQo2BQ3RW4QB4dbTNVFSHKsUr9Oh5NkdzNf84i0ETed2Lze6X2wJmWoql55ixw=="));
         }

         return this.parseBooleanFlag(var2, (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 29) != 0));
      }
   }

   public void runSpawnOpacityRetoggleBatch(Player var1, EditorSession var2) {
      if (((-229317220 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
         if (var3 != null && var3 == var2 && var1.isOnline()) {
            var3.spawnOpacityRetoggleTaskQueued = (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-344637972, 16) != 0);
            if (!var3.pendingSpawnOpacityRetoggleTargetIds.isEmpty()) {
               LinkedHashSet var4 = new LinkedHashSet<>(var3.pendingSpawnOpacityRetoggleTargetIds);
               var3.pendingSpawnOpacityRetoggleTargetIds.clear();
               LinkedHashSet var5 = new LinkedHashSet();

               for (String var7 : (Iterable<String>) (var4)) {

                  if (var7 != null && !var7.isBlank() && this.isTargetVisible(var3, var7)) {
                     var5.addAll(this.resolveVisibilityCascadeTargetIds(var3, var7));
                  }
               }

               if (!var5.isEmpty()) {
                  LinkedHashSet var8 = new LinkedHashSet<>(var3.hiddenLayerTargetIds);
                  var3.hiddenLayerTargetIds.addAll(var5);
                  this.Xqedii_lugjo5i(var1, var3);
                  var3.hiddenLayerTargetIds.clear();
                  var3.hiddenLayerTargetIds.addAll(var8);
                  this.Xqedii_lugjo5i(var1, var3);
               }

               if (!var3.pendingSpawnOpacityRetoggleTargetIds.isEmpty() && !var3.spawnOpacityRetoggleTaskQueued) {
                  var3.spawnOpacityRetoggleTaskQueued = (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1864670434, 12) != 0);
                  Xqedii_38o2zes.Xqedii_2sj63w1(this.plugin, var1, () -> {
                     if ((796005491 * 796005491 - 796005491 & 1) != 0) {
                        throw new IllegalStateException();
                     } else {
                        this.runSpawnOpacityRetoggleBatch(var1, var3);
                     }
                  }, Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(1340258217725662056L, 16));
               }
            }
         } else {
            var2.spawnOpacityRetoggleTaskQueued = (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-139219500, 2) != 0);
         }
      }
   }

   public void Xqedii_lugjo5i(Player var1, EditorSession var2) {
      if ((926105806 * (926105806 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         boolean var3 = var2.moveDragActive;

         try {
            var2.moveDragActive = (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 24) != 0);
            this.rerenderEditableSelection(var1, var2);
            if (var2.previewMode) {
               this.renderEditorShell(var1, var2);
               this.updatePageInfoReadout(var1, var2);
               this.setShellColor(
                  var1,
                  var2,
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("RJyryw03cqJtWdLXyhCBl6Fv7Ya1kS6jah/DFf6AqmpDZlNsBeKVF08yR1oVHnJDTMmOANKJrNE="),
                  var2.editorColor1
               );
               this.setShellColor(
                  var1,
                  var2,
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("j0gx4JRjqUlygEWtA0y7AXs4LV7p36Temq7Ek5zOOQQtJDytO7+L63/E1qMeHjPFdX9srFluKpU="),
                  var2.editorColor2
               );
            }
         } finally {
            var2.moveDragActive = var3;
         }
      }
   }

   @Override
   public void renderEditorTransparencyOverlay(Player var1, EditorSession var2) {
      if (((597436814 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.previewMode) {
         double var3 = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-168137719074841730L, 49));
         double var5 = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149446447718363931L, 40));
         double var7 = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-336275073614334211L, 50));
         double var9 = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(7001295166248932670L, 12));
         double var11 = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444890707651796422L, 49));
         int var13 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1511584903, 23);
         double var14 = this.toRenderX(var2, var3);
         double var16 = this.toRenderY(var2, this.toInternalTextTopY(var5, var9));
         double var18 = Math.max(Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444791747578764742L, 51)), this.toRenderSize(var2, var7));
         double var20 = Math.max(Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(7925199181889705164L, 35)), this.toRenderSize(var2, var9));
         double var22 = this.toRuntimeLayer(var2, var11);
         TextAlignment var24 = TextAlignment.LEFT;
         double var25 = this.applyTextAlignmentOffset(var14, var18, var24);
         HudPositionCalculator.Placement var27 = this.positionCalculator.calculateBoxPlacement(var25, var16, var22, var18, var20);
         this.upsertHud(
            var1,
            Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
               "7G7Ajt2U7ozQf91iRAXz73Grm5mrCFQyfJDbcWMIKHO4hzQg3DKcWSPJf/7nwlXPwXJeqGzIuByQQEbChBq2nxUaI/lEtokY5xv6IyoR0TViZnTh32ojCqkLkzjcRCaGqH8="
            ),
            var27.location(),
            var27.scale(),
            this.ensureRoundedCornerEditorFont(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("bkGcEjjO1T+bXBF/wzDOrQMH0HVtJ1dxZc4wGyw6tZyuLyW8vnbQhpk3WLxshxXm")),
            var13,
            var24
         );
      }
   }

   public void loadPlayerColorPrefs(Player var1, EditorSession var2) {
      if ((2038935682 * 2038935682 - 2038935682 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         File var3 = this.resolveUserDataFolder((Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-627510138, 23) != 0));
         File var4 = new File(var3, Xqedii_gbdemu4sy1wu(String.valueOf(var1.getUniqueId())));
         if (!var4.exists()) {
            File var5 = this.resolveLegacyPlayerDataFile(var1);
            if (var5 == null || !var5.exists()) {
               return;
            }

            var4 = var5;
         }

         try {
            YamlConfiguration var9 = YamlConfiguration.loadConfiguration(var4);
            String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1117155942, 22)];
            var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1839828808, 19)] = var9.getString(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("kEAca52iPiCSmi2qF8JFEQKRU325XcMuPpOS6him2CgsjfWnopGLpynCmzj4QyfVcHuBCw==")
            );
            var10001[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1188456115, 22)] = var2.editorColor1;
            String var6 = this.firstNonBlank(var10001);
            var10001 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1842117187, 22)];
            var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 7)] = var9.getString(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("NCI4FASkjJd9e9KrFZ5oMd8tWuFXtnKFWIMiNswaGV/14NAdAFIMDrup4ahHsKr2VNp2Jw==")
            );
            var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1025619654, 5)] = var2.editorColor2;
            String var7 = this.firstNonBlank(var10001);
            var6 = this.normalizeHexColor(var6);
            var7 = this.normalizeHexColor(var7);
            if (var6.length() == Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350258, 2)) {
               var2.editorColor1 = var6;
            }

            if (var7.length() == Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1192650419, 22)) {
               var2.editorColor2 = var7;
            }

            var2.displayHotbar = var9.getBoolean(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("h74Iw/pUmh+MHMSmRd3J7aDziPiChPh51E59abkFdvvbXbEltawf3XKZcnT4EJHaYyOCAzxfZ2C2CNm8dniIjHCcBlqepLlFCB576+wj"),
               var9.getBoolean(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("YmgbH+kYXFUkirLrHlSnQDfpQ70BOjbuUURSkVRjycRBOB0R0TwnL7RnjPbJnP/BwJMqarH8"), var2.displayHotbar
               )
            );
            var2.displayHand = var9.getBoolean(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("DH3ZFPdGSKrSgHzSCSMSTmjCOn6TKFELcU2oMJ4bJHVlYn3UUvWgIRvgpZy4IWzfTIRVyEOCUYE5IR5dX5/X6LtKDKOWlgxAHVM="),
               var9.getBoolean(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("0aAkjolwcg3MbzOOBexd/Bu+PdsNY6QtFfr09GBUJJfGavLI3S83TN+/N5DskkkxNZc="), var2.displayHand)
            );
            var2.optimizedEditor = var9.getBoolean(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                  "ZgbjmOnDk6cVwxBSANxsayD4ELD9PUAf1GheBk7OgrkrLRYtDLzowwf192SBP+iE25d5DtRre2ExHnRY8I1hIcKCreoAZV2gM9oCcQvhI3e+uQ=="
               ),
               var9.getBoolean(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("nopOCJlP9CHvLNbRu0fE10qP/1eB75nh3ClZdWIl7G5q2mG73jy0PMIhPEyyT9Oxcz8FebJPZhlUcQ=="), var2.optimizedEditor
               )
            );
            var2.editorSounds = var9.getBoolean(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("2mp0Z+BiXZP+x/gKmcY11zZh/XRIvHm5QQHTMf4tquQnL48k/urzcwcRFAW1VIGrWczRZxG23w9OH7dnVkFZLc56oIX3fYNyFTNJlw=="),
               var9.getBoolean(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("pHpqiuSt34gFS9pqoqWO+KqdW8PZogVoqUmYy7XGptsf5t4wu3BMWnA+gghGcaQkv8Fo5Q=="), var2.editorSounds
               )
            );
            var2.welcomePopupAcknowledged = var9.getBoolean(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                  "JZEQyWmQ8VTHmEPQeHCthtaNDtFU5rVlaTWfWGMGziL6mnd/I0V3tfqFh99xEHXIA8ZmpczXAsQD+PmKaklwiMPb9ym+N6DAY8loEz3MzykYUTijXdc="
               ),
               var9.getBoolean(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("/jokmPR8xeFMnBKPC3ugSfjamTU0Yf/7enFgfKuudhBMquDOrzOtTsRRtiP441uKQG7LJ/DwqYtLPxbU5O8="),
                  var2.welcomePopupAcknowledged
               )
            );
         } catch (Exception var8) {
            this.plugin.getLogger().warning(Xqedii_3pv47k8v25n41(var1.getName(), var8.getMessage()));
         }
      }
   }

   @Override
   public void notifyColorPickerColorsChanged(Player var1, EditorSession var2) {
      if ((-1194455473 * (-1194455473 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         this.savePlayerColorPrefs(var1, var2);
      }
   }

   @Override
   public void savePlayerColorPrefs(Player var1, EditorSession var2) {
      if ((-176204638 * -176204638 + -176204638 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         try {
            File var3 = this.resolveUserDataFolder((Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 30) != 0));
            if (!var3.exists()) {
               var3.mkdirs();
            }

            File var4 = new File(var3, Xqedii_3pbng2jqzze2w(String.valueOf(var1.getUniqueId())));
            YamlConfiguration var5 = var4.exists() ? YamlConfiguration.loadConfiguration(var4) : new YamlConfiguration();
            var5.set(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("kEAca52iPiCSmi2qF8JFEQKRU325XcMuPpOS6him2CgsjfWnopGLpynCmzj4QyfVcHuBCw=="), var2.editorColor1);
            var5.set(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("NCI4FASkjJd9e9KrFZ5oMd8tWuFXtnKFWIMiNswaGV/14NAdAFIMDrup4ahHsKr2VNp2Jw=="), var2.editorColor2);
            var5.set(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("h74Iw/pUmh+MHMSmRd3J7aDziPiChPh51E59abkFdvvbXbEltawf3XKZcnT4EJHaYyOCAzxfZ2C2CNm8dniIjHCcBlqepLlFCB576+wj"),
               var2.displayHotbar
            );
            var5.set(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("DH3ZFPdGSKrSgHzSCSMSTmjCOn6TKFELcU2oMJ4bJHVlYn3UUvWgIRvgpZy4IWzfTIRVyEOCUYE5IR5dX5/X6LtKDKOWlgxAHVM="),
               var2.displayHand
            );
            var5.set(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                  "ZgbjmOnDk6cVwxBSANxsayD4ELD9PUAf1GheBk7OgrkrLRYtDLzowwf192SBP+iE25d5DtRre2ExHnRY8I1hIcKCreoAZV2gM9oCcQvhI3e+uQ=="
               ),
               var2.optimizedEditor
            );
            var5.set(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("2mp0Z+BiXZP+x/gKmcY11zZh/XRIvHm5QQHTMf4tquQnL48k/urzcwcRFAW1VIGrWczRZxG23w9OH7dnVkFZLc56oIX3fYNyFTNJlw=="),
               var2.editorSounds
            );
            var5.set(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                  "JZEQyWmQ8VTHmEPQeHCthtaNDtFU5rVlaTWfWGMGziL6mnd/I0V3tfqFh99xEHXIA8ZmpczXAsQD+PmKaklwiMPb9ym+N6DAY8loEz3MzykYUTijXdc="
               ),
               var2.welcomePopupAcknowledged
            );
            String var6 = var5.saveToString();
            Files.writeString(
               var4.toPath(),
               var6.replace(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("r1R4Z42ATPszbL6kL1khfHQzhiSsPy3PiqnBluz5"), System.lineSeparator()),
               StandardCharsets.UTF_8
            );
         } catch (Exception var7) {
            this.plugin.getLogger().warning(Xqedii_18fbdxc0txw5w(var1.getName(), var7.getMessage()));
         }
      }
   }

   public File resolveUserDataFolder(boolean var1) {
      if (((1255051358 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         File var2 = new File(this.plugin.getDataFolder(), Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hWMDC47PZFOtgcjDBbgTgqvS0HnK7dTipnH9SnSi6WzmxMUqsbc/jE9RM3s="));
         if (var1 && !var2.exists()) {
            var2.mkdirs();
         }

         this.migrateLegacyUserDataFolder(var2);
         return var2;
      }
   }

   public static double clampCursorSensitivity(double var0) {
      if ((-2083807816 * -2083807816 - -2083807816 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return Double.isNaN(var0)
            ? Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-7724678331904648512L, 19))
            : Math.min(
               Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4399714519619018180L, 3)),
               Math.max(Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-7405385895356330655L, 27)), var0)
            );
      }
   }

   public double resolvePlayerCursorSensitivity(Player var1) {
      if (((143636435 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444510547479961030L, 50));
      } else {
         Double var2 = this.Xqedii_12w2nt3.get(var1.getUniqueId());
         if (var2 != null) {
            return var2;
         } else {
            double var3 = this.Xqedii_3t5pr73(var1);
            this.Xqedii_12w2nt3.put(var1.getUniqueId(), var3);
            return var3;
         }
      }
   }

   public double Xqedii_3t5pr73(Player var1) {
      if (((2109042484 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         File var2 = this.resolveUserDataFolder((Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1833728579, 22) != 0));
         File var3 = new File(var2, Xqedii_3r0suxkwq5ubb(String.valueOf(var1.getUniqueId())));
         if (!var3.exists()) {
            File var4 = this.resolveLegacyPlayerDataFile(var1);
            if (var4 == null || !var4.exists()) {
               return Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4202119085968137670L, 61));
            }

            var3 = var4;
         }

         try {
            YamlConfiguration var6 = YamlConfiguration.loadConfiguration(var3);
            return clampCursorSensitivity(
               var6.getDouble(
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "ebGfukGdGwVhvgFS9/E0R8PayTa0Wl7NOrZgpQGJHhD2cpH+TqgB6Ucyp6kUbbLCTewJLbHUK2EusjS4UwvgBZIy4oUodAWUltHSQHxWOd7sofBRVHk="
                  ),
                  Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444750515892723257L, 12))
               )
            );
         } catch (Exception var5) {
            return Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-8607085863316386587L, 62));
         }
      }
   }

   public void setPlayerCursorSensitivity(Player var1, double var2) {
      if ((1193397559 * (1193397559 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         double var4 = clampCursorSensitivity(var2);
         this.Xqedii_12w2nt3.put(var1.getUniqueId(), var4);

         try {
            File var6 = this.resolveUserDataFolder((Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 25) != 0));
            File var7 = new File(var6, Xqedii_1o4x6y22thf7c(String.valueOf(var1.getUniqueId())));
            YamlConfiguration var8 = var7.exists() ? YamlConfiguration.loadConfiguration(var7) : new YamlConfiguration();
            var8.set(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                  "ebGfukGdGwVhvgFS9/E0R8PayTa0Wl7NOrZgpQGJHhD2cpH+TqgB6Ucyp6kUbbLCTewJLbHUK2EusjS4UwvgBZIy4oUodAWUltHSQHxWOd7sofBRVHk="
               ),
               var4
            );
            String var9 = var8.saveToString();
            Files.writeString(
               var7.toPath(),
               var9.replace(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("r1R4Z42ATPszbL6kL1khfHQzhiSsPy3PiqnBluz5"), System.lineSeparator()),
               StandardCharsets.UTF_8
            );
         } catch (Exception var10) {
            this.plugin.getLogger().warning(Xqedii_2wi8s2eph81y4(var1.getName(), var10.getMessage()));
         }
      }
   }

   public Boolean resolvePlayerFixShadersOverride(Player var1) {
      if ((-1008780879 * -1008780879 + -1008780879 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && this.hudService != null) {
         Optional var2 = this.Xqedii_yxcgamd.get(var1.getUniqueId());
         return var2 != null ? (Boolean)var2.orElse(null) : this.hudService.getFixShadersOverride(var1.getUniqueId());
      } else {
         return null;
      }
   }

   public boolean flushPendingFixShadersOverride(Player var1) {
      if (((1247454387 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && this.hudService != null) {
         Optional var2 = this.Xqedii_yxcgamd.remove(var1.getUniqueId());
         if (var2 == null) {
            return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 19) != 0);
         } else {
            boolean var3 = this.isFixShadersEffective(var1);
            this.hudService.setFixShadersOverride(var1.getUniqueId(), (Boolean)var2.orElse(null));
            return ((var3 != this.isFixShadersEffective(var1)
               ? Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-320113603, 8)
               : Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1784926747, 25)) != 0);
         }
      } else {
         return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 11) != 0);
      }
   }

   public boolean isFixShadersEffective(Player var1) {
      if ((-1584478348 * -1584478348 + -1584478348 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Boolean var2 = this.resolvePlayerFixShadersOverride(var1);
         return var2 != null ? var2 : this.isFixShadersEnabledGlobally();
      }
   }

   public Boolean resolvePlayerInstantCursorOverride(Player var1) {
      if ((-1536591895 * -1536591895 + -1536591895 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && this.cameraService != null) {
         Optional var2 = this.Xqedii_18x15ls.get(var1.getUniqueId());
         return var2 != null ? (Boolean)var2.orElse(null) : this.cameraService.getShaderCursorOverride(var1.getUniqueId());
      } else {
         return null;
      }
   }

   public boolean isInstantCursorEnabledGlobally() {
      if ((-1835866344 * -1835866344 + -1835866344 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return ((this.cameraService != null && this.cameraService.isShaderCursorEnabled()
            ? Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261819, 3)
            : Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(987582331, 14)) != 0);
      }
   }

   public boolean isInstantCursorEffective(Player var1) {
      if ((899732763 * 899732763 - 899732763 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Boolean var2 = this.resolvePlayerInstantCursorOverride(var1);
         return var2 != null ? var2 : this.isInstantCursorEnabledGlobally();
      }
   }

   public boolean flushPendingInstantCursorOverride(Player var1) {
      if (((541508841 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && this.cameraService != null) {
         Optional var2 = this.Xqedii_18x15ls.remove(var1.getUniqueId());
         if (var2 == null) {
            return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 31) != 0);
         } else {
            boolean var3 = this.cameraService.isShaderCursorWanted(var1);
            this.cameraService.setShaderCursorOverride(var1.getUniqueId(), (Boolean)var2.orElse(null));
            return ((var3 != this.cameraService.isShaderCursorWanted(var1)
               ? Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184262067, 8)
               : Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 4)) != 0);
         }
      } else {
         return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 25) != 0);
      }
   }

   public void applyStoredInstantCursorOverride(Player var1) {
      if ((-20782137 ^ -20782137) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && this.cameraService != null) {
         this.Xqedii_18x15ls.remove(var1.getUniqueId());
         this.cameraService
            .setShaderCursorOverride(
               var1.getUniqueId(),
               this.Xqedii_50awnkl(
                  var1,
                  Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                     "kzJ9WpasALazRR2IIZlLmaOetqiYUycOkOWgcl/x1ij7gEhs+MZPv5FVuumOrNcNWYk10zcMeftWEhtgpOnOLfQMtaZk59mALAqBDdEC"
                  )
               )
            );
      }
   }

   public void forgetPlayerInstantCursorOverride(Player var1) {
      if ((2016008147 * 2016008147 + 2016008147 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && this.cameraService != null) {
         this.Xqedii_18x15ls.remove(var1.getUniqueId());
         this.cameraService.setShaderCursorOverride(var1.getUniqueId(), null);
      }
   }

   public void setPlayerInstantCursorOverride(Player var1, Boolean var2) {
      if ((-434245150 * (-434245150 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         if (this.cameraService != null) {
            if (this.hasOpenSession(var1)) {
               this.Xqedii_18x15ls.put(var1.getUniqueId(), Optional.ofNullable(var2));
            } else {
               this.Xqedii_18x15ls.remove(var1.getUniqueId());
               this.cameraService.setShaderCursorOverride(var1.getUniqueId(), var2);
            }
         }

         this.Xqedii_4lhs6hc(
            var1,
            Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("kzJ9WpasALazRR2IIZlLmaOetqiYUycOkOWgcl/x1ij7gEhs+MZPv5FVuumOrNcNWYk10zcMeftWEhtgpOnOLfQMtaZk59mALAqBDdEC"),
            var2,
            Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
               "YSxBnddkZNZsHhzU06EnXqOAIQk2800Mn6XGYsYekOKR14U0DJQTZLjpGrDzCv7Vwr6Uxx3kO+2r4PlhXQxp+7yeG7hGdNGC15nQi6LUyQwCKveUVWac8g=="
            )
         );
      }
   }

   public Boolean Xqedii_50awnkl(Player var1, String var2) {
      if ((-902774901 ^ -902774901) != 0) {
         throw new IllegalStateException();
      } else {
         File var3 = this.resolveUserDataFolder((Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 13) != 0));
         File var4 = new File(var3, Xqedii_kqdjnm8fmuwo(String.valueOf(var1.getUniqueId())));
         if (!var4.exists()) {
            File var5 = this.resolveLegacyPlayerDataFile(var1);
            if (var5 == null || !var5.exists()) {
               return null;
            }

            var4 = var5;
         }

         try {
            YamlConfiguration var7 = YamlConfiguration.loadConfiguration(var4);
            return !var7.isBoolean(var2) ? null : var7.getBoolean(var2);
         } catch (Exception var6) {
            return null;
         }
      }
   }

   public void Xqedii_4lhs6hc(Player var1, String var2, Object var3, String var4) {
      if ((-516555310 * -516555310 - -516555310 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         try {
            File var5 = this.resolveUserDataFolder((Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-826850338, 12) != 0));
            File var6 = new File(var5, Xqedii_1wk984k576q1c(String.valueOf(var1.getUniqueId())));
            YamlConfiguration var7 = var6.exists() ? YamlConfiguration.loadConfiguration(var6) : new YamlConfiguration();
            var7.set(var2, var3);
            String var8 = var7.saveToString();
            Files.writeString(
               var6.toPath(),
               var8.replace(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("r1R4Z42ATPszbL6kL1khfHQzhiSsPy3PiqnBluz5"), System.lineSeparator()),
               StandardCharsets.UTF_8
            );
         } catch (Exception var9) {
            this.plugin.getLogger().warning(Xqedii_xc4e39ymzmnt(var4, var1.getName(), var9.getMessage()));
         }
      }
   }

   @Override
   public void applyRuntimePlayerToggleAction(Player var1, EditorSession var2, String var3, String var4) {
      if ((981274674 ^ 981274674) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         boolean var5 = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("41fWlxmS7Pziz3gGxFD5OeLGBndPMng3ifJxLIlvFqMGzvHiMzgtcQ==").equals(var3);
         boolean var6 = var5 ? this.isInstantCursorEffective(var1) : this.isFixShadersEffective(var1);
         String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261779, 5)];
         var10001[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1038936949, 0)] = var4;
         String var7 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
         int var10 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350215, 5);
         switch (var7.hashCode()) {
            case -1609594047:
               if (var7.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("IagVy5OtPy0LQrSqZIEbJO2z2+D8q3ektDBwx/TLg3DZv3t2GZB8zTK3"))) {
                  var10 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121088102, 17);
               }
               break;
            case -1298848381:
               if (var7.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("a/YBNEqKBE2isLC6za6ER/4zuikdZFAx7FJ34nDPO2YZeoxiFkKEHw=="))) {
                  var10 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350374, 7);
               }
               break;
            case 3521:
               if (var7.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("331BcjS9C8w5DEI3KElaDGnnGrjJoGKKMwi2mJVDQ1A="))) {
                  var10 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350214, 2);
               }
               break;
            case 3551:
               if (var7.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("FH50A9IpJdW4zoena82HfraX/ijFQ0SlJ2J1cMYTiS8="))) {
                  var10 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 0);
               }
               break;
            case 109935:
               if (var7.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("7Y3IZsckTAB8xOkklrC2hsVKNLE9OPuPiOufN6Uzr1FMlw=="))) {
                  var10 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350214, 3);
               }
               break;
            case 119527:
               if (var7.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("L+Ry3ZevrhqLp4ZC2Vlgom99PyjpMb0erxxTe3lOMHL8sw=="))) {
                  var10 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-2099875226, 30);
               }
               break;
            case 3569038:
               if (var7.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("qyXmgKCI768DKHZiNh3XIy5WSeP5Xw7EVZRKb771skSnY77G"))) {
                  var10 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(2067455247, 6);
               }
               break;
            case 94746189:
               if (var7.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("0qLjrOgec/kCiauWiJgfSbHPO91qVsdndpiimp89xlst78p5TZ0="))) {
                  var10 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1196847718, 23);
               }
               break;
            case 97196323:
               if (var7.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("5If5KLiMK+KLzG+D357T96MDZimpTr/XjS/m+g0iixn2PuFBgrs="))) {
                  var10 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-644921235, 27);
               }
               break;
            case 108404047:
               if (var7.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hlVIaZR+aDWUGe6asubIiZ7m3aK6nZx9pNH8HFj2L/9M/tiFjp4="))) {
                  var10 = Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184249523, 11);
               }
               break;
            case 270940796:
               if (var7.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("6yH+SCnBtE474UpolpaoMtrTye41iXyDKttB5twRKc7tvCiSU6/MMX+cf2A="))) {
                  var10 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1120563814, 18);
               }
               break;
            case 1544803905:
               if (var7.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("MXvlbRWuj+A/wwRYlxvCtV+QZAIAabIigXa+/wDWa7fxc1xQ7zjPCZMb"))) {
                  var10 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1222013542, 24);
               }
               break;
            case 1671308008:
               if (var7.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("H/zrqsaMjvmodgipyU26dBATYAUOn6dVKlucj1qsxhLCg0KpvN2wvgR9"))) {
                  var10 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1215160719, 11);
               }
         }
         Boolean var8 = switch (var10) {
            case 0, 1, 2, 3, 4 -> Boolean.TRUE;
            case 5, 6, 7, 8, 9 -> Boolean.FALSE;
            case 10, 11, 12 -> null;
            default -> Boolean.valueOf(
               ((!var6 ? Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184263859, 11) : Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 3)) != 0)
            );
         };
         if (var5) {
            this.setPlayerInstantCursorOverride(var1, var8);
         } else {
            this.setPlayerFixShadersOverride(var1, var8);
         }

         boolean var9 = var5 ? this.isInstantCursorEffective(var1) : this.isFixShadersEffective(var1);
         if (var9 != var6 && var2 != null && !var2.editMode && !var2.hudTextOnlyMode) {
            Xqedii_38o2zes.Xqedii_2sj63w1(
               this.plugin,
               var1,
               () -> {
                  if (((-1470862105 | 1) & 1) == 0) {
                     throw new IllegalStateException();
                  } else if (var1.isOnline() && this.editorSessions.get(var1.getUniqueId()) == var2) {
                     this.openGui(
                        var1,
                        var2.pageName,
                        (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1443928229, 27) != 0),
                        (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 29) != 0)
                     );
                  }
               },
               Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444187565939301830L, 49)
            );
         }
      }
   }

   @Override
   public String applyPlayerPreferencePlaceholders(Player var1, String var2) {
      if ((398240213 ^ 398240213) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.indexOf(Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-640245637, 9)) >= 0) {
         String var3 = var2;
         if (var2.contains(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("lalW4NKpNuNcivI0rW2ULYxGNI1c/FENhkJrpQ5mMTclXqyqFSMY2V1JDDBmA9WZxMALWQsS"))) {
            var3 = var2.replace(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("lalW4NKpNuNcivI0rW2ULYxGNI1c/FENhkJrpQ5mMTclXqyqFSMY2V1JDDBmA9WZxMALWQsS"),
               this.isFixShadersEffective(var1)
                  ? Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("FH50A9IpJdW4zoena82HfraX/ijFQ0SlJ2J1cMYTiS8=")
                  : Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("7Y3IZsckTAB8xOkklrC2hsVKNLE9OPuPiOufN6Uzr1FMlw==")
            );
         }

         if (var3.contains(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("kut/QSPemxce+xDI7T+6F9Y4o4qLTyFDoCVciS283xXmN7JFIc3O1sg8SYIcSE0tULT/ww=="))) {
            var3 = var3.replace(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("kut/QSPemxce+xDI7T+6F9Y4o4qLTyFDoCVciS283xXmN7JFIc3O1sg8SYIcSE0tULT/ww=="),
               this.isInstantCursorEffective(var1)
                  ? Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("FH50A9IpJdW4zoena82HfraX/ijFQ0SlJ2J1cMYTiS8=")
                  : Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("7Y3IZsckTAB8xOkklrC2hsVKNLE9OPuPiOufN6Uzr1FMlw==")
            );
         }

         return var3;
      } else {
         return var2;
      }
   }

   public boolean isFixShadersEnabledGlobally() {
      if (((61076332 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return ((this.hudService != null && this.hudService.isFixShadersEnabled()
            ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 22)
            : Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 17)) != 0);
      }
   }

   public void applyStoredFixShadersOverride(Player var1) {
      if ((-1617141939 * -1617141939 + -1617141939 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && this.hudService != null) {
         this.Xqedii_yxcgamd.remove(var1.getUniqueId());
         this.hudService.setFixShadersOverride(var1.getUniqueId(), this.Xqedii_1s93k2q(var1));
      }
   }

   public void forgetPlayerFixShadersOverride(Player var1) {
      if ((993670893 * 993670893 - 993670893 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && this.hudService != null) {
         this.Xqedii_yxcgamd.remove(var1.getUniqueId());
         this.hudService.setFixShadersOverride(var1.getUniqueId(), null);
      }
   }

   public Boolean Xqedii_1s93k2q(Player var1) {
      if ((-784504018 * -784504018 + -784504018 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_50awnkl(
            var1, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("7nKuNdz5piXjoZR/nK+F7nJYJPldXTvr/pY8oi25iwBGmZcxM1LfrVqnmhxf+NACA9fjGj799OmkAAX107xNm0QnKPTjVJBp")
         );
      }
   }

   public void setPlayerFixShadersOverride(Player var1, Boolean var2) {
      if (((673092196 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         if (this.hudService != null) {
            if (this.hasOpenSession(var1)) {
               this.Xqedii_yxcgamd.put(var1.getUniqueId(), Optional.ofNullable(var2));
            } else {
               this.Xqedii_yxcgamd.remove(var1.getUniqueId());
               this.hudService.setFixShadersOverride(var1.getUniqueId(), var2);
            }
         }

         this.Xqedii_4lhs6hc(
            var1,
            Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("7nKuNdz5piXjoZR/nK+F7nJYJPldXTvr/pY8oi25iwBGmZcxM1LfrVqnmhxf+NACA9fjGj799OmkAAX107xNm0QnKPTjVJBp"),
            var2,
            Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("PbZu4tL6Q8R/ALQ6Wum8W1KjBkipZzkifi11Apa31wrRVXvUfdFuHXtAji3iFD4oE5+rHjeVkk8U3ITJbfdeEKWUMY22+A==")
         );
      }
   }

   public File resolveLegacyPlayerDataFolder() {
      if (((-103675136 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return new File(
            this.plugin.getDataFolder(), Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("YL8aLW/LyaqhyrPUGFCuI+l3ml5V8hG2GwPlWP5tUrhGIHJMNtOacuX2EfGK/sGfgO0=")
         );
      }
   }

   public File resolveLegacyPlayerDataFile(Player var1) {
      if ((1776185275 * 1776185275 + 1776185275 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return null;
      } else {
         File var2 = this.resolveLegacyPlayerDataFolder();
         return new File(var2, Xqedii_26axjpsryuakk(String.valueOf(var1.getUniqueId())));
      }
   }

   public void migrateLegacyUserDataFolder(File var1) {
      if ((-963865593 ^ -963865593) != 0) {
         throw new IllegalStateException();
      } else {
         File var2 = this.resolveLegacyPlayerDataFolder();
         if (var2.exists() && var2.isDirectory()) {
            if (var1 != null && !var1.exists()) {
               var1.mkdirs();
            }

            if (var1 != null && var1.exists()) {
               File[] var3 = var2.listFiles((var0, var1x) -> {
                  if ((988396294 ^ 988396294) != 0) {
                     throw new IllegalStateException();
                  } else {
                     return var1x.toLowerCase(Locale.ROOT).endsWith(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("80ljh/3cUK9dBzxkS5XckA1r6QoAw2/Ti6IF6szY3nup+0JH"));
                  }
               });
               if (var3 != null && var3.length != 0) {
                  File[] var4 = var3;
                  int var5 = var3.length;

                  for (int var6 = Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-689275943, 17); var6 < var5; var6++) {
                     File var7 = var4[var6];
                     File var8 = new File(var1, var7.getName());
                     if (!var8.exists() && !var7.renameTo(var8)) {
                        try {
                           Files.copy(var7.toPath(), var8.toPath());
                        } catch (IOException var10) {
                           this.plugin.getLogger().warning(Xqedii_1p1a4t8snlknn(var7.getName(), var10.getMessage()));
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public boolean resolvePageShowNpc(YamlConfiguration var1) {
      if ((1695939354 * (1695939354 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         boolean var2 = this.plugin
            .getConfig()
            .getBoolean(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("lpsFzZzZPY1CDNZy2JV5s9A+2W4j1BD2U9m80d9tMDcVAz83i3Fgc4zNA4BNLQk3MsjdMZr8yzztrxcpDlpNXU8+Ol52iiLSDwg="),
               this.plugin
                  .getConfig()
                  .getBoolean(
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "EBru1tJV8i3LeANbdMiL0C0bOmJMW/38+KIo6zvlfkGJtnZwU4wpY13PoNU9vmMK/zkUWXyPke+wfer89bQjJJkl8v7GC98LL/optuIw"
                     ),
                     (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-556877997, 4) != 0)
                  )
            );
         if (var1 == null) {
            return var2;
         } else {
            String[] var10000 = new String[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1197541771, 23)];
            var10000[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1230619359, 21)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
               "hAErKlO4nL91YdIHGZY7R0O4kgsqpTQWp2w9eNMn/f+PFfPFASQ3/c25HqQ1NilXDEcVDcyFzPPddADrx0U="
            );
            var10000[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 7)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
               "pW+1VvR+ZAT+NEZpJ5rUFX5f3+sMGCQLz/V0j8+fj2TmkvBuXqbn2spEjfVCstIo7sPDuj+KcX8C/2Lj"
            );
            var10000[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1147210123, 23)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
               "VtTw7qvrmGCsOs6OKLwT6ceejPbVjxoaX6wKSBdY2vshNxUMobXF6Qv1TW57gjafVwSJ9PzDRiMv8VNT8pY="
            );
            var10000[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1138127462, 23)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
               "OCqTcSr7Fkgxow7f/2NIHv9y1vjzqxSMTslC8uww2O6rAeMlJG15KxGzdhM="
            );
            String[] var3 = var10000;
            int var4 = var3.length;

            for (int var5 = Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 21); var5 < var4; var5++) {
               String var6 = var3[var5];
               Object var7 = var1.get(var6);
               if (var7 instanceof Boolean var10) {
                  return var10;
               }

               if (var7 instanceof String var8 && !var8.isBlank()) {
                  String var9 = var8.trim().toLowerCase(Locale.ROOT);
                  if (var9.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("qyXmgKCI768DKHZiNh3XIy5WSeP5Xw7EVZRKb771skSnY77G"))
                     || var9.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("L+Ry3ZevrhqLp4ZC2Vlgom99PyjpMb0erxxTe3lOMHL8sw=="))
                     || var9.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("FH50A9IpJdW4zoena82HfraX/ijFQ0SlJ2J1cMYTiS8="))) {
                     return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1578145957, 27) != 0);
                  }

                  if (var9.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("5If5KLiMK+KLzG+D357T96MDZimpTr/XjS/m+g0iixn2PuFBgrs="))
                     || var9.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("331BcjS9C8w5DEI3KElaDGnnGrjJoGKKMwi2mJVDQ1A="))
                     || var9.equals(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("7Y3IZsckTAB8xOkklrC2hsVKNLE9OPuPiOufN6Uzr1FMlw=="))) {
                     return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 23) != 0);
                  }
               }
            }

            return var2;
         }
      }
   }

   @Override
   public boolean openGui(Player var1, String var2, boolean var3) {
      if ((-1420236291 * -1420236291 - -1420236291 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.openGui(var1, var2, var3, (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 7) != 0));
      }
   }

   @Override
   public boolean openGui(Player var1, String var2, boolean var3, boolean var4) {
      if ((-1392343755 * -1392343755 + -1392343755 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.openGui(
            var1, var2, var3, var4, (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 9) != 0), (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 11) != 0)
         );
      }
   }

   @Override
   public boolean openGui(Player var1, String var2, boolean var3, boolean var4, boolean var5) {
      if (((-518285337 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return this.openGui(var1, var2, var3, var4, var5, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1283081069, 30) != 0));
      }
   }

   @Override
   public boolean openGui(Player var1, String var2, boolean var3, boolean var4, boolean var5, boolean var6) {
      if ((-1742211116 * -1742211116 + -1742211116 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         boolean var7 = this.flushPendingFixShadersOverride(var1);
         var7 |= this.flushPendingInstantCursorOverride(var1);
         ArrayList var8 = new ArrayList();
         Map var9 = this.loadYamlFolder(
            Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("mR+wgJPFzfhM3VHNmOv51W98mNSlnmnRame0olfzd/Hlu6/XZI2W7YkkFx4nZl2FsuKddIzM9mA="), var8
         );
         Map var10 = this.expandComponentImageBlocks(
            this.loadYamlFolder(
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("Z1XNSbP7neksNKSDW6VMcbxtdW4oBnjmwT/Dy/OqeGu5DIk17Is08myPu8w3n3bgLgmjAWjfJsxw29bkisMuIt3A"), var8
            )
         );
         if (!var8.isEmpty()) {
            this.reportYamlIssues(var8);
            this.sendEditorError(
               var1,
               Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                  "AjWv5/Ac2NJwDBXKWO9dh0lbeqivvmELrt+hqgApwXH0+5fnUhB+S85X5c/LQgRiA5wKJFdnISktTC+TYO5/ecO80spu6s5CGLhIADgeDPFTNS/p/ljb4I/Vtp2GS8Xa10Qj1TIh15tUkKWg+34CAX4oHmtgLZKophIDhE9qpu2KH5bIx2V5vpnTvOL1Vnql"
               ),
               new Object[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 1)]
            );
            return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 30) != 0);
         } else {
            String var11 = this.normalizePageKey(var2, var2);
            String var12 = this.resolveExistingPageMapKey(var9, var11);
            YamlConfiguration var13 = var12.isBlank() ? null : (YamlConfiguration)var9.get(var12);
            if (var13 == null) {
               String var91 = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                  "XH8S+LjS6SEqgxKN6vhL1yz9jTNa0vLQoSflYzB3zKrNKOmLS5gp9JWreTjX36yIa+nuQT6cWRhp1LTlrYfYj+UzX60Vps2LGigWqw=="
               );
               Object[] var101 = new Object[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1238243059, 24)];
               var101[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 27)] = var2;
               this.sendEditorError(var1, var91, var101);
               return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 24) != 0);
            } else {
               String var14 = this.normalizePageKey(var12, var11);
               if (!var3) {
                  String var15 = this.resolvePageOpenPermission(var13);
                  if (!var15.isBlank() && !this.hasPageOpenPermission(var1, var15)) {
                     this.sendEditorPlayerMessage(
                        var1,
                        this.plugin
                           .getLangMessageWithPlaceholders(
                              Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("jxQNCbjCpq9K/5ikeoubv3dJ7TruvaxRzcgnzhhNbhSjJU+EWp129xB3z0y8e0+JG0g="),
                              Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                                 "n7f4lCov173NLMBmh9Kt9aqGvOFx7SZcitzeqtMAVgtB4qCkxH6A4NqpicqiPoXD9FpaLj3Rgj1RFUNnmIsZBiJ3Ltj7Dd1hKevFXbsjbqy6o+poCAV9bz1hyD8dCD/a6Hqa4n2Gcj+5HVbSv80IkRBxI1jTxGkXEX0BrR50YREkSfOe"
                              ),
                              Map.of(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("7R+Ek9Iia3WrfUe99lQWffLwb9PojQF7ZmRFoG4TlfYauwME2jLFCdVDyHz3CxwO"), var15)
                           )
                     );
                     return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-2032494348, 15) != 0);
                  }
               }

               if (var3
                  && var13.getBoolean(
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("CcrBP6dU7kIfTL3SFi61R/u1PjHtkCuyYI7PkFBaJQiTGwua32+ELumQPdMcq9J/6es="),
                     (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 11) != 0)
                  )) {
                  this.sendEditorError(
                     var1,
                     Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "P8IuqdkW1o10FU0u3RCH+QJU0Oz01FXTygHLXzcm30PB9K1ZQ+6q5+ZdsE6Ow1lqVlKSGXKQREnB6+c1krhZ/MMaKag8yCtNaO+wg7Uhpw9Pjw=="
                     ),
                     new Object[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1537863527, 19)]
                  );
                  return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 18) != 0);
               } else {
                  File var63 = new File(
                     new File(
                        this.plugin.getDataFolder(),
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("mR+wgJPFzfhM3VHNmOv51W98mNSlnmnRame0olfzd/Hlu6/XZI2W7YkkFx4nZl2FsuKddIzM9mA=")
                     ),
                     Xqedii_1c1mh4zxxki23(var14)
                  );
                  if (var3) {
                     UUID var16 = this.findActiveEditorOwnerForPage(var14, var1.getUniqueId());
                     if (var16 != null) {
                        return this.notifyEditorPageLocked(var1, var14, var16);
                     }
                  }

                  if (var3) {
                     EditorSession var64 = this.editorSessions.get(var1.getUniqueId());
                     if (var64 != null && var64.editMode && var64.previewMode) {
                        this.snapshotCurrentEditorPage(var1.getUniqueId(), var64);
                     }
                  }

                  if (!var3) {
                     EditorSession var65 = this.editorSessions.get(var1.getUniqueId());
                     if (var65 != null && !var65.editMode && var65.pendingCloseEffect) {
                        var65.pendingCloseEffect = (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 14) != 0);
                     }
                  }

                  YamlConfiguration var66 = var13;
                  List var17 = this.expandCompactImageBlocks(
                     this.copyBlocks(var13.getList(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("lTEZfjwUp/iz3Rn6YvBHWgk26wqC/MAkkDG/luUkff7LlA+DwDHwZg==")))
                  );
                  this.applyImageLayoutOffsetFormatToLoadedBlocks(var13, var17);
                  List var18 = this.resolveRenderableBlocks(var17, var10);
                  if (!var3 && var5) {
                     this.markRuntimeHudStackPage(var17, var14);
                     this.markRuntimeHudStackPage(var18, var14);
                  }

                  EditorSession var19 = this.resolveStackableRuntimeHudSession(var1, var3, var5);
                  boolean var20 = (!var3
                        && var5
                        && var13.getBoolean(
                           Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("UW69Z99dAn08+7rmzFk7mQXj+cJZhCLHqZu4zWROd1Pqk5fHvqLrpynAjN8gJQOyL0r95sHA7rMJusB8vhxvNg=="),
                           (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 27) != 0)
                        )
                     ? Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1038936948, 0)
                     : Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-384977615, 8)) != 0;
                  if (var19 == null && var20) {
                     EditorSession var21 = this.editorSessions.get(var1.getUniqueId());
                     if (var21 != null && !var21.editMode) {
                        var19 = var21;
                     }
                  }

                  EditorSession var67 = var19;
                  if (var67 != null) {
                     UiRefreshConfig var68 = this.isInternalEditorPageName(var14)
                        ? UiRefreshConfig.editor(this.isShadersEditorEnabled())
                        : UiRefreshConfig.read(var13);
                     if (var67.hudTextOnlyMode && var68.enabled()) {
                        int var69 = this.hudService.isBossbarSession(var1)
                           ? Xqedii_30g0ulj.Xqedii_3bciqcb(this.hudService.bossbarUpdateTicks(var1), var68.updateTicks())
                           : var68.updateTicks();
                        this.hudService.beginBossbarSession(var1, var69);
                     }

                     this.stackRuntimeHudSession(var67, var17, var18, var14);
                     var67.runtimeKeybinds.putAll(this.parsePageKeybinds(var13));
                     var67.runtimeVariables.putAll(this.parsePageVariables(var13));
                     var67.runtimeFunctions.putAll(this.parsePageFunctions(var13));
                     this.syncKeybindRegistration(var1, var67);
                     this.scheduleNumericPlaceholderRefresh(var1, var67);
                     this.scheduleHudTextPlaceholderRefresh(var1, var67);
                     Xqedii_38o2zes.Xqedii_2sj63w1(
                        this.plugin,
                        var1,
                        () -> {
                           if ((1342040208 * 1342040208 + 1342040208 & 1) != 0) {
                              throw new IllegalStateException();
                           } else if (var1.isOnline()) {
                              EditorSession var7x = this.editorSessions.get(var1.getUniqueId());
                              if (var7x != null && var7x == var67 && !var7x.editMode && (var7x.hudTextOnlyMode || var20)) {
                                 this.renderStackedPageElements(var1, var7x, var14);
                                 this.syncRuntimeFrameAnimations(var1, var7x);
                                 if (var13.getBoolean(
                                       Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("7Yr3sTaHyRvuPbXz/dRBuwTgwDbNT5COzYfj5BdGadRU+7MLX4ZGJWSMdhzAwZZHdf0O2Rnk+EI="),
                                       (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(565618237, 13) != 0)
                                    )
                                    || var13.getBoolean(
                                       Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("+1YC0CJKBUHdvvGDjmxsPjnKhPVFveyWqq/zpCI7CCeR361bxrTovKVaFjG/JruSDQQOvuOg"),
                                       (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1280453385, 10) != 0)
                                    )) {
                                    var7x.runtimeAnimationLoop = (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 28) != 0);
                                 }

                                 var7x.runtimePageKeepOpen.put(var14, var20);
                                 var7x.runtimePageReopen
                                    .put(
                                       var14,
                                       var13.getBoolean(
                                          Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                                             "7RjgBlNNX7yLTTj57mKDHHlBrIVsCBtiXSkzTLhhk38cIM+8Hdt7Mexf3oS9Nuz0HK8LMLJcrjHvqg=="
                                          ),
                                          (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 25) != 0)
                                       )
                                    );
                                 var7x.runtimePageCloseOnDeath
                                    .put(
                                       var14,
                                       var13.getBoolean(
                                          Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                                             "I/Evl7cyttc2sUs3vm9nSBNNggX6gwL25GBaE6a6ZlGitvjM20ZRY4YO/fclNrI+r1JsSY/MtKsVxFCRMUuLZedzJLs7c6nvNp0="
                                          ),
                                          (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1378551885, 18) != 0)
                                       )
                                    );
                                 var7x.runtimePageCloseOnDamage
                                    .put(
                                       var14,
                                       var13.getBoolean(
                                          Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                                             "1T4Pi6jqhf9IiARCZwsYx+wlA4fhuGfAXxogRBnSSEbABGDoTLQthNN05u3vXe3gHKrbuScof0wnUamhWfjtFc3OLqjtLlbOSuw3mw=="
                                          ),
                                          (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1728805158, 31) != 0)
                                       )
                                    );
                                 var7x.runtimePageCloseOnF
                                    .put(
                                       var14,
                                       var13.getBoolean(
                                          Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                                             "ce/NhbYF9Ux5IKtqwttPfgYiTyZuLksir5mJXeswbcSh3zs1ZWOsa+e+uLcvkSHTDmtC6dWEi0smgiXZkVuJL1nv"
                                          ),
                                          (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1113755993, 5) != 0)
                                       )
                                    );
                                 var7x.runtimePageCloseOnShiftF
                                    .put(
                                       var14,
                                       var13.getBoolean(
                                          Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                                             "uWLw+n7fXB0wFWNqwNnJZN/XEllyK+DIsbFZBhsnCOpUB/STC+KsIIHH+CK/bT/py5hRtX3uEYwIqq2JjPlehEJB01PiQqjAuBix3Gp6"
                                          ),
                                          (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1163987339, 23) != 0)
                                       )
                                    );
                                 this.startRuntimeOpenAnimations(var1, var7x, var6, var14);
                              }
                           }
                        },
                        Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(109483835639790673L, 35)
                     );
                     return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 14) != 0);
                  } else {
                     List var22 = Collections.emptyList();
                     PreviewViewport var23 = null;
                     double var24 = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(-7326383994518600609L, 61));
                     if (var3) {
                        YamlConfiguration var26 = (YamlConfiguration)var9.get(
                           Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("3KKkI0SPjnVa8q7FBE+AF0ja18/K6/2Za+dE1xVVEOA5oUlL4/ROVw==")
                        );
                        if (var26 == null) {
                           String var90 = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                              "Nn90bkBmScoRNo92x2o9kA7heG+H9C8DvH5SPtFkLi73gQNEq56ml02lyeC8XHZBOn4S3d8l4QzDXYMkNOg="
                           );
                           Object[] var100 = new Object[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-614261104, 20)];
                           var100[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1394512091, 28)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                              "o3twoXnF7ut7ES0c+3kemmyxOIhFIeoXUTrJtSdQ53HaQ/dHvQQ7TrsQ33epDi1m"
                           );
                           this.sendEditorError(var1, var90, var100);
                           return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 16) != 0);
                        }

                        var66 = var26;
                        List var27 = this.copyBlocks(
                           var26.getList(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("lTEZfjwUp/iz3Rn6YvBHWgk26wqC/MAkkDG/luUkff7LlA+DwDHwZg=="))
                        );
                        List var28 = this.resolveOpenEditorPagesForPlayer(var1.getUniqueId(), var14);
                        int var29 = this.resolveInitialActivePagesScrollOffset(var28, var14);
                        var22 = this.buildShellBlocksWithActivePages(var27, var10, var28, var29, var14);
                        var24 = this.readDouble(
                           var26,
                           Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                              "ibSsyolgEPkiABu/M4Y3tuYxFiKjrLHHU2murxcEq0oD4drjhBfDkxQMhg0wL0MC9uGyLO+UkUy5Co6v0OMPx9UhT9Ye6jU2Ws/V5jE0jBE="
                           ),
                           Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                              "lxusfeHs7dDZ3yGij2e9BhyrM4BvXH4IBiZIVj90YuT8LmFnvjSUrLh8UzM8h8p1Kk5drKr4yBYyDTRsrxJpki9FJzyUkIS16XresxHW"
                           ),
                           Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(3840996973700495909L, 4))
                        );
                        var23 = this.resolvePreviewViewport(var22, var24);
                        if (var23 == null) {
                           String var89 = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                              "D1TOkTpGK7DxIUPMdPrJsMFgqxKKHMWIBCAfD2+4C5Iq6rFGpsYfJJF1x24YStzrVBbYFTZFqxh/5T7ASVOvWEPmBa3Lekeh40Dq4XlBNcne/vt6HnIKiTBHN2aTMbGAgEIRuFXC9mq9OPGYSKIXdjFcCX+oteAyQbkjH6+odD3m80NjYmY7xa6QOrvgm0uP"
                           );
                           Object[] var99 = new Object[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 28)];
                           var99[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 21)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                              "L8qhl2r+PT2fF6nJJpDVZYJQu4YRtlFk69rD4GBBEGDzk42bJYOaTG5B"
                           );
                           this.sendEditorError(var1, var89, var99);
                           return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-945539372, 2) != 0);
                        }
                     }

                     double var70 = var66.getDouble(
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("JbY0W4/p63VZlyGFBlLANCjyoMcyPJbm2E6BfNmIV/UjNgHsoK5oRQ4GS8m9rrYMdyImOw=="),
                        Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(6125322818368298933L, 62))
                     );
                     double var71 = var66.getDouble(
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("Q46DoFqAKh5Bc4sQk+pLBJLheHKNi9uddvs/w7BeBKqBEyquk0Y3H8xgAjUwnjEypbADNNpd"),
                        Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149447249259168795L, 28))
                     );
                     double var30 = var66.getDouble(
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("6poaPH477II9uH12vIqkU+GHtv2q5FthtE+aGT0ocfSLMURcGaA3dFNwOM2FAMRHV3BEYtlL1gA="),
                        Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149447249402688283L, 29))
                     );
                     double var32 = var66.getDouble(
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("KIpZ+WghstQXkmyChaSzQTHYXC81oRARYOP4BDzkijFa34GYoG4pRbfdHgmP9QlNylALoF21ACo="),
                        Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-265429232447002028L, 19))
                     );
                     double var34 = var66.getDouble(
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("H+q01szixp0pITxun3NmQFI8rpMja5pslCzBkLQVOA6oW8NS9yf8hUUuWv2ZOy/TcmVGR+O3dysvyTDom2c="),
                        var66.getDouble(
                           Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("g7jqLPFIq8Tb+vbPmV5gecfeAYaZH4v8c3zHl6UT+DBhvk0GN0CguXf/Nb/1xpBshiKOQCZTqWyf+dbsnQ27QA=="),
                           Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149443974221689627L, 42))
                        )
                     );
                     double var36 = var66.getDouble(
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("sxgfpdSGdsh6AuK05k7D918bTxRAIjMI0a9Dl1VOX81Nea3ZVRE7c15SuwAgOBwDnQidl511svLEDTSgNaN2SA=="),
                        var66.getDouble(
                           Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("BiFWLgdxmgd/3XHnKKzqwG2xcC4iXEDHS6SK4a8FGISrCQRZ0ZKK+7lwRDxL96fSoIxHbRRyWpWYyIXMWsCZVvJr"),
                           Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444750520221244870L, 37))
                        )
                     );
                     String var10002 = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("nde23UhmIW6+j3881R3ZAooAWibBpHk+KjLnLZE+");
                     String[] var10003 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1183213235, 19)];
                     var10003[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 26)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "t6GYO9p/fsB4EuWRPiDEbw1aCVNI1YHIYQWzqpgUB4V0f3sJin1NFRmhVG2GyqzgyNlKakPkMc1/RGsx4VGiJFfbevw="
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1183213235, 20)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "k4u20S0DAm5zrR0vib0NM5cUJuM9I51AcmN10V2HfjhKWrVsobAgvv59nhhyaAgYif02CSMWIcDGMR6tCNLKPiJdkweeyQ=="
                     );
                     String var38 = this.readCursorUnicodeConfig(var66, var10002, var10003);
                     var10002 = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("DYe1ZwgukJ4eNrzRRwuHNdZTbBxFivBxDptjRuxz");
                     var10003 = new String[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1038936945, 0)];
                     var10003[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(2051239244, 6)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "UF3X6Hfc8STss/0Sdh4IA6HXkf1Ojwd1WPsPLima1Kyhrsa9rLfc9yuWQer6t24wa7q96I/netIjfE8z/cEmnn0/hkKkq3GbIjA6UYS8"
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1653700675, 13)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "uT2M+RXNrYqVqDTROX6SEdiEtIc8uivUmXlAsX7YfUsLyX119KgQXUFaOON81BQzVYcrpAa1ARz9rBLT8Poy0RA4acWt1CwFl/4CccXM"
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-864402579, 30)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "28FM0uJ00z/DUS7dUrDaDDRSvNH6wtlOnu+292uSquN9zXI04a1Oa+QygRwG4MqlfeoPK+m2SphAY2HAh5iOTOT2IXy29K8ETc3w55hJYS0="
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1190553267, 21)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "cJNM6Cwm/Dz3yxf53XjIoHlEg1iRq+xTX45Oc3gcDDKBie5sV9gwfWh7g23BjGC0V533fV7bBtz3sGKDpetPS5xZ6pRF3wAOm0tNqaz8ogM="
                     );
                     String var39 = this.readCursorUnicodeConfig(var66, var10002, var10003);
                     var10002 = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("vffXhOsL3Zpy8D599ZhJG1c1kncZXe7kbb7CQURN");
                     var10003 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1850505795, 22)];
                     var10003[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 21)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "ctAg35bN8oGBMt+aPFpH+Xk+sylAB5hZ8I8DD1lheeflk6ZvXk/mFRSr2EspLOxI34sgBBF5xFzQbwL6GNM1V0ytAbc8GXmLc0cASQ=="
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(837356982, 0)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "ihVr53dyaiqnI0wU5JywQ3jMGL9HG4tY89+X+WklQpr1Tiq/nlWShNgh/L8Unci7rgXeC1aQfNXHItDC1v6sInxI+Q1QPp7KEc4hXA=="
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-384977103, 8)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "jhW0xyuxDS6PRFPar7YMqPfvQCsDNPBxnSRma4L5zTMDioDb7EEPhHujsdommiI7aVtlDHHWPJ3YlUwuFtJ92DqCuQAxs4UyVTc2mk9/"
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-160056546, 7)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "+autVzl+zpD3Wn2NwCxep0REPlbqNaeVWIugE0cVCN31nYefuvSIU7w+is49InO+fNTOGHrmnaGic/LLjTEW8JtVEmiEShXwsa9wG8Fu"
                     );
                     String var40 = this.readCursorUnicodeConfig(var66, var10002, var10003);
                     var10002 = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("1w+uaOImoZ0uiOFfna6wmUDHuJVSl2qOGwOuSxMf");
                     var10003 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(647390899, 28)];
                     var10003[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1230619359, 21)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "uTb/HVsC43/mn9E7FL9jVC0gs/vcGKxkXzKaSXfKDO1GkxgrGxHztbN9DSuJmSo3qxxYWUN2zf2NjdlnLm2ij0FzIbRUkagzdhiR8TwOlgz9fIlecw70cQ=="
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1188456115, 22)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "6C1pwzm9E+qVJYKd0b5tAlUplgP4IfpIdwvjp+lCSlYQTOsGrpPAuLy1ykpJV4HdlNuXaRxzWra58YU9qcAPPd8u6tRAukeAZHo6BXswRRNrxIWEeAVrZw=="
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350214, 5)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "0CoCF1XhatnergRjzCb5A0si5ad3wR8tbKFsg3q1mdCPrRPonlxV1IlwabkNPdfJSIbC4pyw+iS7SE0IubiFR0LdJtAFUXtvYzXM/rf9kpg="
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1155598731, 23)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "MwWDps1oAoWlRbCH4iuP6E1wi1RgjLGcXdHVKahHClS97x8G6m8g3crVyIDA93Hi2MIUDZ9gMnkWtegAqqWb0y+uGM2r+u2YHncX0nCNhBw="
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1122398822, 20)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "pYktQwDW1W8Gg5F6bkZa7aj/8uV2qoernW/KmfYy6nYq5E2ENz2dV18+HisYU0yY80TN84/0hMtpRLM/9OU7WL78wCNy73aUr2od26TVJoTn10iMNmpVX/TN"
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(230306281, 16)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "KKsRt6pxmeT7+Enewd3YSI0mE2f4BHvWAMGkd1fkmHZdTrrLMHlXx5/RARWo0IuOgtnaQwzvH3xCaCPlvHhxsZ2FvVgungSX+GH21sQDpZ/Apw=="
                     );
                     String var41 = this.readCursorUnicodeConfig(var66, var10002, var10003);
                     var10002 = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("Wdjw+RrHNQm5+YkxZFzRA9TFTamrd5UT/ps/y1WN");
                     var10003 = new String[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(2067455055, 6)];
                     var10003[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-615309680, 20)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "CpGhaxB1lRqrX/aK2nEfVHvAOK9sne08zjdSD1W8q6aMgsYK7U5qGC/cvEnvMoocGF7UJ6IJvLnNn2hoNcm2D0oUURL1ddgG6eg84g5B0oqU4kgh6P8xEw=="
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 23)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "iSg9brOCc+gy7H7Uh0rYDRIG+oDZ47AD8TJrk9k5FTnnzlDS/a6MUtggyI6yVCpDnf5qGJTAEMMBXEUY2qen/RRM4hGgSXIRmxMMkJsMoSEIOg8OyHWoTw=="
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-826838050, 12)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "ljghgsjQznNkOB+Bn8S5QrWfhvI++zWEkt6YR5hiIZl2cYN4sNihprLOQjq/7OMQeHYR6ymuGiC/uT1UIYjgPXsxeZiLGBm+6unyjh882YzBxCOxFUsDoD9y"
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1891078719, 3)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "rbNzYHq6Nb3H+ABDHnivBnzRxouT4JdYR+4PwwDg869VAZrDd4iOLDtIl/Gh68GXW0+LpamsakacDY8aXgDdrqgEst3+aBGoShaoXxPYUu8WVfrtO+noDzfG"
                     );
                     String var42 = this.readCursorUnicodeConfig(var66, var10002, var10003);
                     var10002 = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("PJN8IOQp9j5Fp4qL7z6TDWqn04vej+W5IeoHHQlF");
                     var10003 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-2032363276, 15)];
                     var10003[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1219240242, 20)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "bcMNS+KQjxW1MaxQLB/dov14Mn6nOVe9TRnadTOWSTs5JRE1wKq5mtjR9K0SCftNPRkI3adjV6a1hTBZArU3KJeWDkl6ZWNHE0D1vvNCYLWuyLf6yHzCvA=="
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 21)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "t+OaPFgogGS5R4V+PgiPP4bKxbVh/KH9Ct5OFhEAK4bRikYFNCn/J+9hOxAAvU3hlCEZ9KUp7+lqtrvDOmbO1XRSTQ5e5zawxjFttp8BkXSmpk+E0s3CeA=="
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121352294, 11)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "wdea8mf8ypV9/JTR4uhcocwRs1bDWIH8r2JEUWn/O2qcXnkwf5Un1uJiRVDxlKFRo/p08KkFbOFOW3o1QNalX2qmOydjBXYjWnnon7QiNVWN0K6yd6dp+P56"
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1864662242, 12)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "EKp5sWEJzIJLyASWdMtd6S0bbzLKw7pf20GoJm55qRWwtYEqWvumaa8KxGvO/W7Pjzwf4m2hsWUwVA4zOxdzuqCmsd3YEJ6rAp0q7Txmbby+lyEivJx85PQx"
                     );
                     String var43 = this.readCursorUnicodeConfig(var66, var10002, var10003);
                     var10002 = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("Gz4QaVsWUg1ENpY9bcBKYp188Ih2QqSN06Z7Eogn");
                     var10003 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(460481490, 17)];
                     var10003[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1219240242, 20)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "r+8B8TMnjeJ0DcKy/n+/mzreql6j96Wc47l3/xkq0RanRdTsOv68cxoZciaShKaOSTARDEsYi1jgKqUXxQHV9cFOOPJr/40GU7xuWSb6EUhmUDFc2LLyOpV+ku9uWHoz"
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-614261104, 20)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "2I7cK5OOTNeGsWbM+Zf7SCPM2vxUAcS736kDL/om3Pvsxs29vv7FuJe1QNfl2rgn9GGl5K4mcelpyz1b+PxRKSk3dkdy696vgDLB1HuR0CRO1mJQeuvru124WRGCoHWr"
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1113755929, 5)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "DfLf6+1YfWLbOu9zpKRkuHq5hV5ixKV9faz8QOrAgLQRqDIjT3WVTuESBOWT+JxwCdlJ6FTiH7Bq+yZ/Oc00FfeNL2TEeWf2lzYomNCHjiObQusScA/Nu2yG0/kFQo7mWOM="
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1891078719, 3)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "NqNx/WzpMzevbSu+61+w5C/zNw1stzCRxje3n4+I4f9odPfVRv8a9X1K+sqEwHDGJrwW6tj8NO2feKNDytCPmF9nonMezL1shXEMa1SY9yPEIIvbHKFTstIxNH465Hjiu4s="
                     );
                     String var44 = this.readCursorUnicodeConfig(var66, var10002, var10003);
                     var10002 = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("QCI43i4rT8rZ7u1IkeSiG8Q5Un6j7id+lnGuRLFo");
                     var10003 = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-192488296, 7)];
                     var10003[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(2077873898, 1)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "oZI19Ke9U8n5vOWFqq2kyiUmQzRofI+8uHMmduDaA7Pfk3WHrF+T9s8LwFL/X7AbxDo3ngC8cZYEcxJbL9bLbLEEX8vU2zex1Yq5Z3ucp5S7SZWgLu4SA4tan2pdejsgdIEU2g=="
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-640227205, 9)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "cUfn7cJY9Shj631Cqp8/ss/loz09+RCk1cVpBc82GiO+Re9DNK1PxG4geS3QdK1d41ZE89bU29SS1Pp+9uugEqlYl4GI81RQtvZ1UlukH5mc0xZvQLfZmwRk2js3EhHd3t+EFw=="
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350247, 0)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "JGRU2Q0bK4mR9joDL3AxHT6TC6xfNWSclDNIxhW4LF+FYgQQe7h7w1za9kDwFbAFaf5OZnMTMpxvhySi9M+M2S4sX9RiOOmP42tamMH2Q3LS42km0Z+8K9eX14mg/FF7uQ2metIA"
                     );
                     var10003[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(104669623, 29)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "XXeNerEnPDGpmKzPS/qKpvjlWpYy9Zt8tw4M3loRJZobzKDfiwxjUN8SY+cT5pyMgdAiHIULjpvp2OZSMhSYGUbfY92J+EsXszb5Ho8vfzSorqzxL73w8YziYzYEa+AlDpO7YfLe"
                     );
                     String var45 = this.readCursorUnicodeConfig(var66, var10002, var10003);
                     String[] var10001 = new String[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(-963221838, 31)];
                     var10001[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1728805158, 31)] = var66.getString(
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("JGY7v0e5MVg+5/im8Xb90Pm8lxccN5kJK2yTrSysNLlgkP+S5TEinX8gusmGWpfDYqjAJdMqPpi+Fn6ejL+OGA==")
                     );
                     var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 10)] = var66.getString(
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("a9fZO30EHUgx5hl6Ew3EFy61+R2HuHH00LyJ+rQwU+mDIT04y4aEKVygWsUOYd6nsH7Q+IAsCujbB10M1/FN0x1I")
                     );
                     var10001[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121342054, 13)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "eckXbwmMImS9GFDtXxMyj083x+bnrE/TWBO4dhrnnszPyLQvPUWvFg=="
                     );
                     String var46 = this.firstNonBlank(var10001);
                     var10001 = new String[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121342054, 13)];
                     var10001[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-360982058, 25)] = this.normalizeHexColor(var46);
                     var10001[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184265907, 12)] = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                        "eckXbwmMImS9GFDtXxMyj083x+bnrE/TWBO4dhrnnszPyLQvPUWvFg=="
                     );
                     String var47 = this.firstNonBlank(var10001);
                     String var48 = this.applyPreferredFont(
                        this.withHexPrefix(var38, var47), null, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-826850338, 12) != 0)
                     );
                     double var49 = this.readDouble(
                        var66,
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("zM6J5vuN9gAt1cDQDOYopLl1tO0dm7Qu9GMUHUWSkMn4ZhW/RYCF3DQjZpJo8W2pd7v70OOYekcNmkFFKu7DOA=="),
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("x1XE7gWd4Dcqsp0zOdOVdb5hgtHpqhA87dcmImdfv6g36JfzS+3YY33xE+0Y3OptV2EBPTgxeYbTfuh4O75Aoxw5"),
                        Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444750511629213126L, 34))
                     );
                     double var52 = this.findMaxLayer(var18) + Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-299513596117579283L, 60));
                     if (var3 && var23 != null) {
                        double var54 = this.findMaxLayer(var22);
                        double var56 = var23.layerBase + this.findMaxLayer(var18);
                        var52 = Math.max(var54, var56) + Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-581715084277344037L, 44));
                     }

                     double var72 = var52;
                     if (Double.isFinite(var49)) {
                        var72 = Math.max(var49, var52);
                     }

                     UiRefreshConfig var73 = !var3 && !this.isInternalEditorPageName(var14)
                        ? UiRefreshConfig.read(var66)
                        : UiRefreshConfig.editor(this.isShadersEditorEnabled());
                     int var57 = !var5
                           && var4
                           && !var7
                           && this.hudService.bossbarSessionMatches(var1, var73.enabled())
                           && this.cameraService.isCameraActive(var1)
                           && this.cameraService.isShaderCursorActive(var1) == this.cameraService.isShaderCursorWanted(var1)
                        ? Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1539909433, 10)
                        : Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 1);
                     if (var57 != 0) {
                        Entity var58 = this.hudService
                           .getHud(var1, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("41fWlxmS7Pziz3gGxFD5OeLGBndPMng3ifJxLIlvFqMGzvHiMzgtcQ=="));
                        Vector var59 = this.hudService.getHudLocation(var58);
                        if (var59 == null) {
                           var57 = Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1394512091, 28);
                        }
                     }

                     int var74 = !var3 && !var5 && this.resolvePageShowNpc(var13)
                        ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 15)
                        : Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-721964115, 26);
                     if (var5) {
                        this.hudService.clearHuds(var1);
                        this.hudService.setLegacyHudSession(var1, (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184294579, 15) != 0));
                        this.cameraService.setCameraActive(var1, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(229978601, 16) != 0));
                        this.cameraService.forceStop(var1);
                        this.hudService.applyBossbarRendering(var1, var73.enabled(), var73.updateTicks());
                     } else if (var57 == 0) {
                        this.hudService.clearHuds(var1);
                        this.hudService.setLegacyHudSession(var1, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-725113802, 26) != 0));
                        this.cameraService.setCameraActive(var1, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-627510138, 23) != 0));
                        this.hudService.applyBossbarRendering(var1, var73.enabled(), var73.updateTicks());
                        this.cameraService.start(var1, var70, var71, var30, var32, var34, var36, var48, var72, (var74 != 0), () -> {
                           if ((954854147 ^ 954854147) != 0) {
                              throw new IllegalStateException();
                           } else {
                              return this.resolvePlayerCursorSensitivity(var1);
                           }
                        });
                     } else {
                        this.hudService.setLegacyHudSession(var1, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1113755993, 5) != 0));
                        this.hudService.applyBossbarRendering(var1, var73.enabled(), var73.updateTicks());
                        this.syncSeamlessCursorHud(var1, var34, var72);
                        this.cameraService.syncCursorNpc(var1, (var74 != 0));
                     }

                     if (!var5) {
                        this.clearGuiRideActionBar(var1);
                        Xqedii_38o2zes.Xqedii_2sj63w1(this.plugin, var1, () -> {
                           if ((-1017810747 * -1017810747 + -1017810747 & 1) != 0) {
                              throw new IllegalStateException();
                           } else {
                              this.clearGuiRideActionBar(var1);
                           }
                        }, Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-2948898901902639234L, 56));
                        Xqedii_38o2zes.Xqedii_2sj63w1(this.plugin, var1, () -> {
                           if ((-1465009114 ^ -1465009114) != 0) {
                              throw new IllegalStateException();
                           } else {
                              this.clearGuiRideActionBar(var1);
                           }
                        }, Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-7382033662594553838L, 24));
                     }

                     int var75 = var57;
                     EditorSession var60 = new EditorSession();
                     var60.pageName = var14;
                     var60.pageFile = var63;
                     var60.yaml = var3 ? this.cloneYamlConfiguration(var13) : var13;
                     if (var3) {
                        this.ensurePageDisplayName(var60);
                     }

                     var60.rawBlocks = var17;
                     var60.renderBlocks = var18;
                     var60.components = var10;
                     var60.shellBlocks = var22;
                     var60.previewViewport = var23;
                     if (!var3 && !var5) {
                        PreviewViewport var61 = this.configureRuntimeZoom(var60, var13, var70, var71);
                        if (var61 != null) {
                           var60.previewViewport = var61;
                        }
                     }

                     var60.previewDefaultZoom = var24;
                     var60.previewMode = ((var3 && var23 != null
                        ? Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(460088274, 17)
                        : Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-640226693, 9)) != 0);
                     var60.editMode = var3;
                     var60.hudTextOnlyMode = ((var5 && !var3
                        ? Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-614261104, 20)
                        : Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 26)) != 0);
                     var60.runtimeAutoCloseAfterAnimations = ((!var3 && var6
                        ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 11)
                        : Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 5)) != 0);
                     var60.runtimeAnimationLoop = ((var3
                           || !var13.getBoolean(
                                 Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("7Yr3sTaHyRvuPbXz/dRBuwTgwDbNT5COzYfj5BdGadRU+7MLX4ZGJWSMdhzAwZZHdf0O2Rnk+EI="),
                                 (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 18) != 0)
                              )
                              && !var13.getBoolean(
                                 Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("+1YC0CJKBUHdvvGDjmxsPjnKhPVFveyWqq/zpCI7CCeR361bxrTovKVaFjG/JruSDQQOvuOg"),
                                 (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1539910457, 10) != 0)
                              )
                        ? Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1653692483, 13)
                        : Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1113756025, 5)) != 0);
                     if (!var3) {
                        int var76 = !var5 ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 11) : Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1230619359, 21);
                        var60.runtimePageKeepOpen
                           .put(
                              var14,
                              Boolean.valueOf(
                                 ((var5
                                       && var13.getBoolean(
                                          Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                                             "UW69Z99dAn08+7rmzFk7mQXj+cJZhCLHqZu4zWROd1Pqk5fHvqLrpynAjN8gJQOyL0r95sHA7rMJusB8vhxvNg=="
                                          ),
                                          (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 9) != 0)
                                       )
                                    ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 18)
                                    : Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 0)) != 0)
                              )
                           );
                        var60.runtimePageReopen
                           .put(
                              var14,
                              Boolean.valueOf(
                                 ((var5
                                       && var13.getBoolean(
                                          Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                                             "7RjgBlNNX7yLTTj57mKDHHlBrIVsCBtiXSkzTLhhk38cIM+8Hdt7Mexf3oS9Nuz0HK8LMLJcrjHvqg=="
                                          ),
                                          (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(2051239244, 6) != 0)
                                       )
                                    ? Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 11)
                                    : Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-344637972, 16)) != 0)
                              )
                           );
                        var60.runtimePageCloseOnDeath
                           .put(
                              var14,
                              var13.getBoolean(
                                 Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                                    "I/Evl7cyttc2sUs3vm9nSBNNggX6gwL25GBaE6a6ZlGitvjM20ZRY4YO/fclNrI+r1JsSY/MtKsVxFCRMUuLZedzJLs7c6nvNp0="
                                 ),
                                 (var76 != 0)
                              )
                           );
                        var60.runtimePageCloseOnDamage
                           .put(
                              var14,
                              var13.getBoolean(
                                 Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                                    "1T4Pi6jqhf9IiARCZwsYx+wlA4fhuGfAXxogRBnSSEbABGDoTLQthNN05u3vXe3gHKrbuScof0wnUamhWfjtFc3OLqjtLlbOSuw3mw=="
                                 ),
                                 (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 15) != 0)
                              )
                           );
                        var60.runtimePageCloseOnF
                           .put(
                              var14,
                              var13.getBoolean(
                                 Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("ce/NhbYF9Ux5IKtqwttPfgYiTyZuLksir5mJXeswbcSh3zs1ZWOsa+e+uLcvkSHTDmtC6dWEi0smgiXZkVuJL1nv"),
                                 (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1163987339, 23) != 0)
                              )
                           );
                        var60.runtimePageCloseOnShiftF
                           .put(
                              var14,
                              var13.getBoolean(
                                 Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                                    "uWLw+n7fXB0wFWNqwNnJZN/XEllyK+DIsbFZBhsnCOpUB/STC+KsIIHH+CK/bT/py5hRtX3uEYwIqq2JjPlehEJB01PiQqjAuBix3Gp6"
                                 ),
                                 (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 29) != 0)
                              )
                           );
                        String[] var87 = new String[Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350374, 7)];
                        var87[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 12)] = var13.getString(
                           Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                              "YckiJOQmdX7TRNzAVCHDtH0VSuP89Bt4E1GpfvuBm5aTZ0T5MHtvD/GdPEo88QG/Loj41ULE6E/A+q43Lp/pExCLGjFa4g=="
                           )
                        );
                        var87[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1280454409, 10)] = var13.getString(
                           Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("vPVrF8Sm5MOZHucU28g1w3XISVLUCGDlFCG6jgqqqS4vM1EC1M2knjmWbJCR+rU0H7uYBMheZEEM74xVn1Ucq4y4Ymo=")
                        );
                        var60.pageOpenEffect = this.firstNonBlank(var87).trim();
                        String[] var88 = new String[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(2077873902, 1)];
                        var88[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-581993670, 22)] = var13.getString(
                           Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                              "BKd+QkWmCz9UcHW6uQ9G76dYYAjGtlTWFMMt2danS2+eErgi25/0lmdxLeUTbAruf3/QeGqHgFt0vYuCbAEbbMlsDLXXOQPI"
                           )
                        );
                        var88[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(-963221837, 31)] = var13.getString(
                           Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                              "l1EQC4mLlRNyG7+8kRbPS9BkzQ6WFrN8VrGWl3LUwj5C6QWqmBxUyY82dIh84Aulpo10dWED+CKv5se8z0XRYqW7G+bEeg=="
                           )
                        );
                        var60.pageCloseEffect = this.firstNonBlank(var88).trim();
                        var60.pageCloseUseHud = var13.getBoolean(
                           Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                              "M9oL493wH0JXqsVGrgkhupkI5n5514yhSv/WpIXB2QiMLYXQUoMJF6kIme4RePY1aLcelPPHObj5Wjzs7h5F9V2VGx8sq/+Z"
                           ),
                           (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-384977615, 8) != 0)
                        );
                     }

                     var60.previewSizeToastVisible = (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 23) != 0);
                     var60.previewSizeToastPendingReveal = ((var60.editMode && var60.previewMode
                        ? Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-278439007, 3)
                        : Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 1)) != 0);
                     var60.hitboxOffsetX = this.readDouble(
                        var66,
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("OkHunK/vzsCSjT02pnZNu3V6Na0IRm1W01h0+34KdejW1kDXg7SLrV1RrXRi/rSCBICUslk52jHp3TYLKx6aFqs2JUQ="),
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("OkHunK/vzsCSjT02pnZNu3V6Na0IRm1W01h0+34KdejW1kDXg7SLrV1RrXRi/rSCBICUslk52jHp3TYLKx6aFqs2JUQ="),
                        Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149447249402688283L, 5))
                     );
                     var60.hitboxOffsetY = this.readDouble(
                        var66,
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("SnAuG5LS3+UfEi0+tCU1HyIDOLsjO/1cjPDJT16n9qarHuCrgf8TrwgVqXJifjxu+koUVH1QcNz6wnR410E6o/UmHqU="),
                        Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("SnAuG5LS3+UfEi0+tCU1HyIDOLsjO/1cjPDJT16n9qarHuCrgf8TrwgVqXJifjxu+koUVH1QcNz6wnR410E6o/UmHqU="),
                        Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-3475353840848494937L, 15))
                     );
                     if (this.cameraService != null && this.cameraService.isShaderCursorWanted(var1)) {
                        var60.hitboxLiftY = this.cameraService.getShaderCursorHitboxLift();
                        var60.hitboxOffsetY = var60.hitboxOffsetY + var60.hitboxLiftY;
                     }

                     var60.cursorColor = var47;
                     var60.cursorDefaultUnicode = this.cursorUnicodeValue(
                        var38, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("nde23UhmIW6+j3881R3ZAooAWibBpHk+KjLnLZE+")
                     );
                     var60.cursorHoverUnicode = this.cursorUnicodeValue(var39, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("DYe1ZwgukJ4eNrzRRwuHNdZTbBxFivBxDptjRuxz"));
                     var60.cursorDragUnicode = this.cursorUnicodeValue(var40, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("vffXhOsL3Zpy8D599ZhJG1c1kncZXe7kbb7CQURN"));
                     var60.cursorSliderDragUnicode = this.cursorUnicodeValue(
                        var41, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("1w+uaOImoZ0uiOFfna6wmUDHuJVSl2qOGwOuSxMf")
                     );
                     var60.cursorResizeTrBlUnicode = this.cursorUnicodeValue(
                        var42, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("Wdjw+RrHNQm5+YkxZFzRA9TFTamrd5UT/ps/y1WN")
                     );
                     var60.cursorResizeTlBrUnicode = this.cursorUnicodeValue(
                        var43, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("PJN8IOQp9j5Fp4qL7z6TDWqn04vej+W5IeoHHQlF")
                     );
                     var60.cursorResizeVerticalUnicode = this.cursorUnicodeValue(
                        var44, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("Gz4QaVsWUg1ENpY9bcBKYp188Ih2QqSN06Z7Eogn")
                     );
                     var60.cursorResizeHorizontalUnicode = this.cursorUnicodeValue(
                        var45, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("QCI43i4rT8rZ7u1IkeSiG8Q5Un6j7id+lnGuRLFo")
                     );
                     var60.cursorRuntimeUnicode = var60.cursorDefaultUnicode;
                     var60.savePopupVisible = (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1025619622, 5) != 0);
                     var60.preferencesPopupVisible = (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1833728579, 22) != 0);
                     var60.welcomePopupVisible = (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1219240242, 20) != 0);
                     var60.welcomePopupAcknowledged = (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-945539372, 2) != 0);
                     if (var3) {
                        this.ensureManageFilePopupHiddenByDefault(var60);
                     }

                     if (!var3
                        && Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("r1w46I6vPG2hztYyiUeJq3xOspPJmAxTWcURcsSo4+5lIYboG7zuEVhLXV+ikRVytBE=").equalsIgnoreCase(var14)) {
                        this.initializeMenuFilePopupState(var60, var9);
                     }

                     if (var3) {
                        this.populateSavePopupDefaults(var60);
                        this.restoreEditorLayerStateMetadata(var60);
                        var60.editorShellTemplateBlocks = this.copyBlocks(
                           var66.getList(Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("lTEZfjwUp/iz3Rn6YvBHWgk26wqC/MAkkDG/luUkff7LlA+DwDHwZg=="))
                        );
                        var60.openEditorPages.clear();
                        var60.openEditorPages.addAll(this.resolveOpenEditorPagesForPlayer(var1.getUniqueId(), var14));
                        var60.activePagesScrollOffset = this.resolveInitialActivePagesScrollOffset(var60.openEditorPages, var14);
                        var60.shellBlocks = this.buildShellBlocksWithActivePages(
                           var60.editorShellTemplateBlocks, var60.components, var60.openEditorPages, var60.activePagesScrollOffset, var14
                        );
                     }

                     if (!var3) {
                        var60.runtimeKeybinds.putAll(this.parsePageKeybinds(var13));
                        var60.runtimeVariables.putAll(this.parsePageVariables(var13));
                        this.seedPersistentVariables(var1, this.parsePagePersistentVariables(var13));
                        var60.runtimeFunctions.putAll(this.parsePageFunctions(var13));
                        this.debugScript(
                           Xqedii_335let45r31j3(
                              var14,
                              String.valueOf(var60.runtimeVariables),
                              String.valueOf(this.persistentVariables.getOrDefault(var1.getUniqueId(), Map.of())),
                              String.valueOf(var60.runtimeFunctions.keySet()),
                              String.valueOf(var60.runtimeKeybinds.keySet())
                           )
                        );
                        this.applyOpenInitialZeroTickPose(var60);
                     }

                     this.syncKeybindRegistration(var1, var60);
                     this.editorSessions.put(var1.getUniqueId(), var60);
                     if (var3) {
                        Map var77 = this.playerEditorWorkingPages.computeIfAbsent(var1.getUniqueId(), var0 -> {
                           if ((1745246055 * 1745246055 + 1745246055 & 1) != 0) {
                              throw new IllegalStateException();
                           } else {
                              return new HashMap<>();
                           }
                        });
                        this.rememberEditorWorkingPage(var1.getUniqueId(), var14, var60.rawBlocks);
                        this.syncEditorWorkingPagesToOpenPages(var1.getUniqueId(), var60.openEditorPages);
                     } else {
                        this.playerEditorWorkingPages.remove(var1.getUniqueId());
                     }

                     this.applyCursorVisualUnicode(var1, var60, var60.cursorRuntimeUnicode);
                     if (var3) {
                        this.loadPlayerColorPrefs(var1, var60);
                        this.applyEditorDisplayPreferences(var1, var60);
                     } else if (!var5) {
                        this.setClientHotbarVisibleAsAir(var1);
                     } else {
                        this.restoreClientHotbarFromServerInventory(var1);
                        this.restoreHeldHotbarSlotFromServerInventory(var1);
                     }

                     if (!var5) {
                        this.scheduleClientHotbarMaskRefresh(var1, var60);
                     }

                     Runnable var78 = () -> {
                        if ((1924005046 * 1924005046 - 1924005046 & 1) != 0) {
                           throw new IllegalStateException();
                        } else {
                           if (var75 != 0) {
                              this.hudService.clearHudsKeepCursor(var1);
                           }

                           if (!var60.editMode) {
                              String var5x = var60.pageOpenEffect;
                              if (var5x != null && !var5x.isBlank()) {
                                 EditorTimelineKeyframePart.HoverEffectRuntimeConfig var6x = this.resolveHoverEffectRuntimeConfig(var5x);
                                 if (var6x != null
                                    && Math.abs(
                                          var6x.startScaleXMultiplier()
                                             - Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(3582595751037567571L, 29))
                                       )
                                       < Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-2174221010119473846L, 10))
                                    && Math.abs(
                                          var6x.startScaleYMultiplier()
                                             - Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-4653720708578490657L, 47))
                                       )
                                       < Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-119302627413073992L, 29))) {
                                    var60.pageOpenSpawnOffsetX = var6x.startOffsetX();
                                    var60.pageOpenSpawnOffsetY = var6x.startOffsetY();
                                 }
                              }
                           }

                           this.renderPage(var1, var60, this.pageUsesViewFeatures(var60.rawBlocks));
                           var60.pageOpenSpawnOffsetX = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149447249402688283L, 40));
                           var60.pageOpenSpawnOffsetY = Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-1163438964647710281L, 45));
                           if (var60.editMode && var60.previewMode) {
                              LinkedHashSet var10x = this.resolveTimelinePreviewTargetIds(
                                 var60, Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("hVg2cf+43wL5jXuMinCPOe2Eq5KgHyhcRfDiHQ==")
                              );
                              if (!var10x.isEmpty()) {
                                 LinkedHashSet var12x = new LinkedHashSet(var10x);
                                 this.applyRuntimeOpenAnimationTick(
                                    var1, var60, var12x, Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149447249402688283L, 28))
                                 );
                                 Xqedii_38o2zes.Xqedii_2sj63w1(
                                    this.plugin,
                                    var1,
                                    () -> {
                                       if ((-1526516201 * (-1526516201 + 1) & 1) != 0) {
                                          throw new IllegalStateException();
                                       } else {
                                          EditorSession var4xx = this.editorSessions.get(var1.getUniqueId());
                                          if (var4xx != null && var4xx == var60 && var4xx.editMode && var4xx.previewMode) {
                                             this.applyRuntimeOpenAnimationTick(
                                                var1, var4xx, var12x, Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-8493735438299857249L, 24))
                                             );
                                          }
                                       }
                                    },
                                    Xqedii_10gtlsk.Xqedii_1cb2p02j4g544(4444750515892722886L, 8)
                                 );
                                 Xqedii_38o2zes.Xqedii_2sj63w1(
                                    this.plugin,
                                    var1,
                                    () -> {
                                       if ((1700904541 ^ 1700904541) != 0) {
                                          throw new IllegalStateException();
                                       } else {
                                          EditorSession var4xx = this.editorSessions.get(var1.getUniqueId());
                                          if (var4xx != null && var4xx == var60 && var4xx.editMode && var4xx.previewMode) {
                                             this.applyRuntimeOpenAnimationTick(
                                                var1, var4xx, var12x, Double.longBitsToDouble(Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(-5149447249402688283L, 27))
                                             );
                                          }
                                       }
                                    },
                                    Xqedii_10gtlsk.Xqedii_19ozx3tsmonje(-6349362938851672225L, 14)
                                 );
                              }

                              this.schedulePreviewSizeToastHide(var1, var60);
                              this.startEditorShellOpenAnimations(var1, var60);
                              if (!var60.welcomePopupAcknowledged) {
                                 this.setSavePopupVisible(var1, var60, (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(2067455311, 6) != 0));
                                 this.setPreferencesPopupVisible(var1, var60, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1505943114, 29) != 0));
                                 this.setWelcomePopupVisible(var1, var60, (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1662947547, 28) != 0));
                                 double var13x = var60.cursorX + var60.hitboxOffsetX;
                                 double var8x = var60.cursorY + var60.hitboxOffsetY;
                                 this.updateWelcomePopupHover(var1, var60, var13x, var8x);
                              }
                           }

                           if (!var60.editMode) {
                              this.startRuntimeOpenAnimations(var1, var60, var6);
                              this.applyPageOpenEffect(var1, var60);
                              this.scheduleNumericPlaceholderRefresh(var1, var60);
                              this.scheduleHudTextPlaceholderRefresh(var1, var60);
                              this.syncRuntimeFrameAnimations(var1, var60);
                           } else if (this.isPlaceholderResolutionEnabled()) {
                              this.scheduleNumericPlaceholderRefresh(var1, var60);
                              this.scheduleHudTextPlaceholderRefresh(var1, var60);
                           }

                           this.scheduleInventorySlotItemRefresh(var1, var60);
                           if (var60.editMode) {
                              String[] var10001x = new String[Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1215148431, 11)];
                              var10001x[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-689275943, 17)] = var60.pageName;
                              String var11x = this.firstNonBlank(var10001x).trim();
                              if (!var11x.isEmpty()) {
                                 String var10002x = Xqedii_10gtlsk.Xqedii_302i5pu5xixp6(
                                    "2mmDepnKRd0y4eyRta9qksZR2ehzUp5E6Fnd9ttut9z/bIH9OpbxSVN9UN1276OmKXoUtirgQgnp4878UAvH68huhRWXPuUz0t046w=="
                                 );
                                 Object[] var10003x = new Object[Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1452697267, 28)];
                                 var10003x[Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(1407110839, 28)] = var11x;
                                 this.sendEditorInfo(var1, var10002x, var10003x);
                              }
                           }
                        }
                     };
                     if (var75 != 0) {
                        Xqedii_38o2zes.Xqedii_2sj63w1(this.plugin, var1, var78, Xqedii_10gtlsk.Xqedii_1ymrvgol6og2d(5149447249402688282L, 12));
                     } else {
                        Xqedii_38o2zes.Xqedii_2sj63w1(this.plugin, var1, var78, Xqedii_10gtlsk.Xqedii_1dzmpigs0rgc9(-4648040187537745523L, 40));
                     }

                     return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(418678491, 31) != 0);
                  }
               }
            }
         }
      }
   }

   public List<String> getKeepOpenPageKeys(Player var1) {
      if ((1473984385 * (1473984385 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Collections.emptyList();
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && !var2.editMode && var2.hudTextOnlyMode) {
            ArrayList var3 = new ArrayList();

            for (Entry var5 : var2.runtimePageKeepOpen.entrySet()) {
               if (Boolean.TRUE.equals(var5.getValue())) {
                  var3.add((String)var5.getKey());
               }
            }

            return var3;
         } else {
            return Collections.emptyList();
         }
      }
   }

   public List<String> getReopenPageKeys(Player var1) {
      if (((1235229799 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Collections.emptyList();
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && !var2.editMode && var2.hudTextOnlyMode) {
            ArrayList var3 = new ArrayList();

            for (Entry var5 : var2.runtimePageReopen.entrySet()) {
               if (Boolean.TRUE.equals(var5.getValue())) {
                  var3.add((String)var5.getKey());
               }
            }

            return var3;
         } else {
            return Collections.emptyList();
         }
      }
   }

   public boolean isAnyPageCloseOnDeath(Player var1) {
      if ((570826683 * 570826683 - 570826683 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-278438999, 3) != 0);
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && !var2.editMode) {
            for (Boolean var4 : var2.runtimePageCloseOnDeath.values()) {
               if (Boolean.TRUE.equals(var4)) {
                  return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-320113603, 8) != 0);
               }
            }

            return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 1) != 0);
         } else {
            return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(837356981, 0) != 0);
         }
      }
   }

   public boolean isAnyPageCloseOnDamage(Player var1) {
      if ((-1966176144 * (-1966176144 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1025619622, 5) != 0);
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && !var2.editMode) {
            for (Boolean var4 : var2.runtimePageCloseOnDamage.values()) {
               if (Boolean.TRUE.equals(var4)) {
                  return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(418678491, 31) != 0);
               }
            }

            return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1728805158, 31) != 0);
         } else {
            return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 27) != 0);
         }
      }
   }

   public boolean isAnyPageCloseOnF(Player var1) {
      if ((1203168321 ^ 1203168321) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-344637972, 16) != 0);
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && !var2.editMode) {
            for (Boolean var4 : var2.runtimePageCloseOnF.values()) {
               if (Boolean.TRUE.equals(var4)) {
                  return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(1121350246, 25) != 0);
               }
            }

            return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1184261811, 3) != 0);
         } else {
            return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(1674713962, 1) != 0);
         }
      }
   }

   public boolean isAnyPageCloseOnShiftF(Player var1) {
      if ((456211054 * (456211054 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_10gtlsk.Xqedii_2x79tkun8ntt(-1864674530, 12) != 0);
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && !var2.editMode) {
            for (Boolean var4 : var2.runtimePageCloseOnShiftF.values()) {
               if (Boolean.TRUE.equals(var4)) {
                  return (Xqedii_10gtlsk.Xqedii_3cn5x8p7pgynt(1150707379, 25) != 0);
               }
            }

            return (Xqedii_10gtlsk.Xqedii_3ex0s9k0othba(-1121350247, 20) != 0);
         } else {
            return (Xqedii_10gtlsk.Xqedii_1pnnkrghdxm4k(-1480745618, 29) != 0);
         }
      }
   }


   private static /* synthetic */ String Xqedii_207o52np10lcx(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("Y6SYdvkZuZrjZyinD9Q8CZzfx7/h3NmeC7GdsgrjFzOcmA==");
    }

    private static /* synthetic */ String Xqedii_3dfnjlxpz1k41(int n) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("XLVjhZHVbYKeUagKv/8Tw5lCtlw0C/fYLuI70zkdmHatMdtoEl5xHexVJusotw==") + n;
    }

    private static /* synthetic */ String Xqedii_2gqdoxx2e5o2d(long l) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("XLVjhZHVbYKeUagKv/8Tw5lCtlw0C/fYLuI70zkdmHatMdtoEl5xHexVJusotw==") + l;
    }

    private static /* synthetic */ String Xqedii_126rs2jz70sg8(int n) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("H0d2Lgubr7LNT9nroRCUIAFMJgENZUujicsOHVfW/DitjOSFO4y+ZPc0upb79AyJ") + n;
    }

    private static /* synthetic */ String Xqedii_pc6ttxv9rjcp(int n) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("2MtlyoTJ0vNXnpaE+HPx7mFYookptSkbrdjky69RrSJRnJUKN7I=") + n + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("NjFxRno1yJiSbo1zhTqfyUQHG+fHNSW8m3UTtz6o1H/BeeQONTT65riblHDrkj3UgOfkfg==");
    }

    private static /* synthetic */ String Xqedii_225iwv8xx4bji(int n) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("2MtlyoTJ0vNXnpaE+HPx7mFYookptSkbrdjky69RrSJRnJUKN7I=") + n + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("cYSE+yGodNPqDG4Bb5ob8MET/PLl43x0Urb80GB8xe91o2Ufh3Z11wBfWydUEwKT89zr3gN+");
    }

    private static /* synthetic */ String Xqedii_3u977853ym54p(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("80ljh/3cUK9dBzxkS5XckA1r6QoAw2/Ti6IF6szY3nup+0JH");
    }

    private static /* synthetic */ String Xqedii_25zngpotijaal(int n) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("2MtlyoTJ0vNXnpaE+HPx7mFYookptSkbrdjky69RrSJRnJUKN7I=") + n + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("NjFxRno1yJiSbo1zhTqfyUQHG+fHNSW8m3UTtz6o1H/BeeQONTT65riblHDrkj3UgOfkfg==");
    }

    private static /* synthetic */ String Xqedii_3lwh5w77depfo(String string) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("I4pmBpDzM+ZVEZO6gRLgcEOCovuXYnx5kmhK1LEOh6s/3jcnuwLfWN/wFBsl8hKwHMMbL8xQ") + string;
    }

    private static /* synthetic */ String Xqedii_2zg2411alqkiq(int n) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("2MtlyoTJ0vNXnpaE+HPx7mFYookptSkbrdjky69RrSJRnJUKN7I=") + n + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("NjFxRno1yJiSbo1zhTqfyUQHG+fHNSW8m3UTtz6o1H/BeeQONTT65riblHDrkj3UgOfkfg==");
    }

    private static /* synthetic */ String Xqedii_1x9fdm49dc3pg(int n) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("2MtlyoTJ0vNXnpaE+HPx7mFYookptSkbrdjky69RrSJRnJUKN7I=") + n + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("cYSE+yGodNPqDG4Bb5ob8MET/PLl43x0Urb80GB8xe91o2Ufh3Z11wBfWydUEwKT89zr3gN+");
    }

    private static /* synthetic */ String Xqedii_nf7q6o7zatxh(int n) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("2MtlyoTJ0vNXnpaE+HPx7mFYookptSkbrdjky69RrSJRnJUKN7I=") + n + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("bgoJYBLBXebL4QEslmxXCMwxLvsZfZfjdSS0q7sMQL/hCuFxWd7Dpg==");
    }

    private static /* synthetic */ String Xqedii_qwmphia9qccb(int n) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("2MtlyoTJ0vNXnpaE+HPx7mFYookptSkbrdjky69RrSJRnJUKN7I=") + n + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("w9kqG1tgbPsHsFgQzvw+2xGt62k1fSGZ7wXUic5SNl4nzvygRt/QAamY2C7yv8tO7oE=");
    }

    private static /* synthetic */ String Xqedii_1pwlkknl82xgc(String string, String string2) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("ffCkP1blTeo+bKymbdfFLncky0MJGBUxGXDxyxHi") + string2;
    }

    private static /* synthetic */ String Xqedii_xcy2qen7amaf(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("tOA33KG3Xrp2+w9bjw5wQuS6U8ZrgMm03ioGhi2lGXkEQ1sCZbc=");
    }

    private static /* synthetic */ String Xqedii_4fhxzwgoqm0u(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("K2+52Y7nVhElEGx49gB+rWHe2//e9E1TwSRXPk593KNVSWnU1kGxc2ol");
    }

    private static /* synthetic */ String Xqedii_20ks58huv110f(String string) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("I4pmBpDzM+ZVEZO6gRLgcEOCovuXYnx5kmhK1LEOh6s/3jcnuwLfWN/wFBsl8hKwHMMbL8xQ") + string;
    }

    private static /* synthetic */ String Xqedii_37tf8ha6yrc87(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("tOA33KG3Xrp2+w9bjw5wQuS6U8ZrgMm03ioGhi2lGXkEQ1sCZbc=");
    }

    private static /* synthetic */ String Xqedii_343ia2njxyk9x(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("K2+52Y7nVhElEGx49gB+rWHe2//e9E1TwSRXPk593KNVSWnU1kGxc2ol");
    }

    private static /* synthetic */ String Xqedii_1hkmcjq9x1pcm(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("ehVtx3qDh5ZR1oW1YSp27SXQSHr7V7uJIjltaWSZdSbLdbVGlRgkzn2NqkY=");
    }

    private static /* synthetic */ String Xqedii_347skoa07ev6r(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("v0PD5QnpBHr7qDtRJ1YBgdG9AwChTi03c+B4oQ3XQAaW1nSb/GlJpyRDnS2YuNHXs6bP9MLV");
    }

    private static /* synthetic */ String Xqedii_2n63d6c2lhkbh(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("ehVtx3qDh5ZR1oW1YSp27SXQSHr7V7uJIjltaWSZdSbLdbVGlRgkzn2NqkY=");
    }

    private static /* synthetic */ String Xqedii_2er5wicv2vwvt(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("WINyuQidsPLp6iU8hBq9B9gLje5IIAGhbSUuIeLOv4G9KG7aTRZuRgFB");
    }

    private static /* synthetic */ String Xqedii_28ktklb5bamob(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("WM907Nw1V1Pz5SiB/s01Rnh/a6uR+SvGGw1a+8i1ohMAj+ldOgVCdw==");
    }

    private static /* synthetic */ String Xqedii_1lc72mbooh2fs(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("ju1ukPHSg7YI9NyZQ/LKb3ucS/ly6Ros+0pdTFL//i95Uaoy/sXvoKVKhv+DIA==");
    }

    private static /* synthetic */ String Xqedii_1ab6sxj9ooe5a(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("LT+BRIwyfyTm7/gNVr4uRyzTxC8yYog37qS7QX+z6bLgzlmeJNL8h2gm");
    }

    private static /* synthetic */ String Xqedii_2epsc92qf1vhi(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("+9RJlaoUEo8odBU9OQuYSqSHncopP0hmNkWNEunEywuHXCJn0H/W/esiFfM=");
    }

    private static /* synthetic */ String Xqedii_1tqgryhamx42y(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("v6Nl/h5mXIqnvOhEdDlf5gLv/0TBT48vXSsjOryyasg6KoMmaSY=");
    }

    private static /* synthetic */ String Xqedii_4853dr9tcgwb(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("m+d2SlmAe1GOKKLoq5pdHxyDHyvr5XCDggUAGLzOBajgyhhTOIc=");
    }

    private static /* synthetic */ String Xqedii_2ldefpltyvjgo(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("mThaBjDyRHfHIhGCCRbCB8I9Q1w3v8JhrbIfZIKrdTH2txfsOmo=");
    }

    private static /* synthetic */ String Xqedii_3c4ef9zukixjq(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("oHHJZiYjJgqfne0Hvu0BBoFX3GwBC44Vdea1U2+cgNqIJ59iSuE=");
    }

    private static /* synthetic */ String Xqedii_gbdemu4sy1wu(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("80ljh/3cUK9dBzxkS5XckA1r6QoAw2/Ti6IF6szY3nup+0JH");
    }

    private static /* synthetic */ String Xqedii_3pv47k8v25n41(String string, String string2) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("huiGUqeYaY7rnwnLgzcGLsfkDNZUs7XLP2zrH2v6RqSN3/T03uJxR+cy5NOU9/GzTXeZhAe8yiEaLhuXK03inA20+gBl55S9AenYKzF+0qfkEVjzvNxSG93jVDQFREgajMOAPbs0Bqr59MP5+ZrxQKfISoYiUBqNemqHG9+Efn8tCw==") + string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("POniGE6u6Qa7RaWEeBHmUirLOEDKQHBUAZ1awM4Fcqc=") + string2;
    }

    private static /* synthetic */ String Xqedii_3pbng2jqzze2w(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("80ljh/3cUK9dBzxkS5XckA1r6QoAw2/Ti6IF6szY3nup+0JH");
    }

    private static /* synthetic */ String Xqedii_18fbdxc0txw5w(String string, String string2) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("Cx+oDl82ja387YiPxQejmqqtHZ4jrAxRoLf4C0nkcGkbaXty+KbjvmXtlnG/Bq1KV7q5VJdgTPaZwPt/wPKxeLSbXr0jQ4hDRp1Rtt12E3jDfKcKqufQ2fUvEzaiHw5q71HWe29BaQc0N26i20J06zrtR+egTrMCs+t1D3B/JAZ4Og==") + string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("POniGE6u6Qa7RaWEeBHmUirLOEDKQHBUAZ1awM4Fcqc=") + string2;
    }

    private static /* synthetic */ String Xqedii_3r0suxkwq5ubb(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("80ljh/3cUK9dBzxkS5XckA1r6QoAw2/Ti6IF6szY3nup+0JH");
    }

    private static /* synthetic */ String Xqedii_1o4x6y22thf7c(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("80ljh/3cUK9dBzxkS5XckA1r6QoAw2/Ti6IF6szY3nup+0JH");
    }

    private static /* synthetic */ String Xqedii_2wi8s2eph81y4(String string, String string2) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("24Py+laupFmUE+Nbo7HAb24n0Yo+KFAwGAW1hEK18ANrnlep9sE90S6AbmsFchkvc0OX7O/OL/5khFw5iI7lUaowHMUeHCcJmfUwrns6lIReRTyyseTeEDnMeFAsqFiPtW801zLXFL/JwCqr+Xxhx2ZlXwQBvLFG9zSP2EAVsGP73A==") + string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("POniGE6u6Qa7RaWEeBHmUirLOEDKQHBUAZ1awM4Fcqc=") + string2;
    }

    private static /* synthetic */ String Xqedii_kqdjnm8fmuwo(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("80ljh/3cUK9dBzxkS5XckA1r6QoAw2/Ti6IF6szY3nup+0JH");
    }

    private static /* synthetic */ String Xqedii_1wk984k576q1c(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("80ljh/3cUK9dBzxkS5XckA1r6QoAw2/Ti6IF6szY3nup+0JH");
    }

    private static /* synthetic */ String Xqedii_xc4e39ymzmnt(String string, String string2, String string3) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("6yca9BhnW0DoMPAKtPBFwxHvM77mYw+1WZ0mOukHlL78fIMif1dAjfPUPQQWrJcWy22iqDTeNjKn22bRug/fT5bGlzP+eLh30Y4F6UwYFD7blqrQ") + string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("vYXdBDCoOItjeO4uh/wtQhszHwsXJukpgofGxtQ0+KKnGMIk8Go=") + string2 + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("POniGE6u6Qa7RaWEeBHmUirLOEDKQHBUAZ1awM4Fcqc=") + string3;
    }

    private static /* synthetic */ String Xqedii_26axjpsryuakk(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("80ljh/3cUK9dBzxkS5XckA1r6QoAw2/Ti6IF6szY3nup+0JH");
    }

    private static /* synthetic */ String Xqedii_1p1a4t8snlknn(String string, String string2) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("KyWKwzgELaXuz0ry01YyMb0W2h95SuXdW2SHC3QXV1woUxKlCkO6pDENVDIrEustuI42rCsXJK3IpnO/PC2535eZuyjDnKuqpSh/0ast5HnbfpU/RpD0j0A3b03qOBCs6Q4R+0Dl0QEx40T1h0ue13c+pbiMHHpX2jIEFRoA") + string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("105VvR7Zfk/jrRiIxK6A8BlCJguQ6VNVBwGHbeVZcMdBBg==") + string2;
    }

    private static /* synthetic */ String Xqedii_1c1mh4zxxki23(String string) {
        return string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("80ljh/3cUK9dBzxkS5XckA1r6QoAw2/Ti6IF6szY3nup+0JH");
    }

    private static /* synthetic */ String Xqedii_335let45r31j3(String string, String string2, String string3, String string4, String string5) {
        return Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("T+H5Y8q8oEi6Q895GissTeEVCVvK1YSx0rHg0tZO4qoiWGtF1Qa/mr5opkpwabkC/k1Rgw==") + string + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("anhL/PJVaybzlLQJMUPHvUCohp57gV7nd4m6LCwobQPQ2m6yi+wn0JwbsGaKoyW7cGmpQg==") + string2 + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("VGdctUiY4p/OLF6HTfXJQqXYPa4tv16cW+4sr3YM2jh6KJFxDjOfcK6tqiHOTopqBOS4qg==") + string3 + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("xagxpPvgKb5wdRi5BgI3G9/pV0pjS4pG1/VQdody+SAK4vkY6cVueDbOJ7lbSX7Dg8M=") + string4 + Xqedii_10gtlsk.Xqedii_302i5pu5xixp6("YM+IVXU6mtiCeV8XNQixuWberu6aC1cElQtgBuccIx9O9ltOEaZXwx4M1jmEzfPi") + string5;
    }

   public static final class ActivePagesDropTarget {
      public static final String Xqedii_3a8tjjwq1pslf = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";
      public final int Xqedii_13fclmd;
      public final double Xqedii_2e0216b;
      public final double Xqedii_1u79v9q;
      public final double Xqedii_mfzxnnc;

      public ActivePagesDropTarget(int var1, double var2, double var4, double var6) {
         this.Xqedii_13fclmd = var1;
         this.Xqedii_2e0216b = var2;
         this.Xqedii_1u79v9q = var4;
         this.Xqedii_mfzxnnc = var6;
      }
   }
}
