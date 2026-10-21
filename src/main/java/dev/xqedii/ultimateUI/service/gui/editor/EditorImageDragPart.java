package dev.xqedii.ultimateUI.service.gui.editor;

import dev.xqedii.ultimateUI.service.resourcepack.UiImageAtlasService.GeneratedImage;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.Xqedii_14y6uzm;
import dev.xqedii.ultimateUI.Xqedii_2lun6e9;
import dev.xqedii.ultimateUI.Xqedii_2t253qr;
import dev.xqedii.ultimateUI.Xqedii_38o2zes;
import dev.xqedii.ultimateUI.ChatColorUtil;
import dev.xqedii.ultimateUI.Xqedii_3uxgwyg;
import dev.xqedii.ultimateUI.FontAtlasGlyphs;
import dev.xqedii.ultimateUI.Xqedii_l2une6g;
import dev.xqedii.ultimateUI.Xqedii_nd6rkr3;
import dev.xqedii.ultimateUI.Xqedii_p92kfr8;
import dev.xqedii.ultimateUI.api.UiBuilder;
import dev.xqedii.ultimateUI.gui.model.HoverElement;
import dev.xqedii.ultimateUI.gui.parser.TemplateListLayout;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.GuiService.ClickType;
import dev.xqedii.ultimateUI.service.gui.editor.interaction.EditorOverlayPreviewPart;
import dev.xqedii.ultimateUI.service.gui.editor.shell.managers.EditorTimelineKeyframePart.HoverEffectRuntimeConfig;
import dev.xqedii.ultimateUI.service.gui.model.EditorPropertyField;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.gui.model.EditorSidebarTab;
import dev.xqedii.ultimateUI.service.gui.model.EditorTool;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.service.hud.HudPositionCalculator.Placement;
import dev.xqedii.ultimateUI.service.resourcepack.UiImageAtlasService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.runtime.ObjectMethods;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

public abstract class EditorImageDragPart extends EditorOverlayPreviewPart {
   public static final String Xqedii_kml7csh2cttk = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";
   public final Map<UUID, List<String>> playerEditorOpenPages = new HashMap<>();
   public final Map<UUID, Map<String, List<Map<String, Object>>>> playerEditorWorkingPages = new HashMap<>();
   public final Map<YamlConfiguration, YamlConfiguration> Xqedii_2e4261v = new WeakHashMap<>();

   public EditorImageDragPart(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
      this.cameraService.setCursorListener(this::onCursorMove);
   }

   @Override
   public abstract void renderResolvedContent(Player var1, EditorSession var2);

   @Override
   public abstract void renderEditorTransparencyOverlay(Player var1, EditorSession var2);

   public void beginMoveDragHistory(EditorSession var1) {
      if (((1209152762 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         var1.moveDragStartSnapshot = this.captureEditorMutationSnapshot(var1);
         var1.moveDragMutated = (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 21) != 0);
      }
   }

   public void finishMoveDragHistory(EditorSession var1) {
      if ((319341995 * 319341995 - 319341995 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         String var2 = var1.moveDragStartSnapshot;
         boolean var3 = var1.moveDragMutated;
         var1.moveDragStartSnapshot = null;
         var1.moveDragMutated = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-244536126, 17) != 0);
         if (var3 && var2 != null && !var2.isBlank()) {
            this.recordEditorMutationSnapshot(var1, var2);
         }
      }
   }

   public void beginResizeDragHistory(EditorSession var1) {
      if ((-156486888 * (-156486888 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         if (var1.resizeDragStartSnapshot == null || var1.resizeDragStartSnapshot.isBlank()) {
            var1.resizeDragStartSnapshot = this.captureEditorMutationSnapshot(var1);
            var1.resizeDragMutated = (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(366593208, 26) != 0);
         }
      }
   }

   public void finishResizeDragHistory(EditorSession var1) {
      if (((1783930378 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         String var2 = var1.resizeDragStartSnapshot;
         boolean var3 = var1.resizeDragMutated;
         var1.resizeDragStartSnapshot = null;
         var1.resizeDragMutated = (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 0) != 0);
         if (var3 && var2 != null && !var2.isBlank()) {
            this.recordEditorMutationSnapshot(var1, var2);
         }
      }
   }

   public boolean handleAlignToolbarClick(Player var1, EditorSession var2, ClickType var3, double var4, double var6) {
      if ((-630250848 * -630250848 + -630250848 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var2 == null || var2.activeTool != EditorTool.ALIGN || var3 == null) {
         return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1296888891, 27) != 0);
      } else if (!var2.alignToolbarVisible) {
         return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 4) != 0);
      } else {
         boolean var8 = this.isInsideAlignToolbar(var2, var4, var6);
         String var9 = this.resolveHoveredAlignToolbarActionId(var2, var4, var6);
         if (var3 == ClickType.RIGHT) {
            return ((!var8 && var9 == null ? Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 13) : Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 19)) != 0);
         } else if (var3 != ClickType.LEFT) {
            return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1235852936, 24) != 0);
         } else if (var9 == null) {
            return var8;
         } else {
            var2.alignToolbarClickGuardUntilTick = Xqedii_38o2zes.Xqedii_316oplq()
               + Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556910853376682995L, 43)
               + Xqedii_p92kfr8.Xqedii_274j413le3omf(5251109032773370778L, 7);
            var2.alignToolbarConsumeHoldClick = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1291074118, 3) != 0);
            this.applyAlignToolbarAction(var1, var2, var9);
            return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1064382939, 21) != 0);
         }
      }
   }

   public void updateAlignToolbarOverlay(Player var1, EditorSession var2, double var3, double var5) {
      if ((1246101389 ^ 1246101389) != 0) {
         throw new IllegalStateException();
      } else {
         this.updateAlignToolbarOverlay(var1, var2, var3, var5, (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1529557180, 23) != 0));
      }
   }

   public void updateAlignToolbarOverlay(Player var1, EditorSession var2, double var3, double var5, boolean var7) {
      if ((1364115474 * (1364115474 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.editMode && var2.previewMode && var2.activeTool == EditorTool.ALIGN) {
         EditorRect var8 = this.getSelectedBounds(var2);
         if (var8 == null) {
            this.clearAlignToolbarOverlay(var1, var2);
         } else {
            EditorRect var9 = this.findShellBlockRect(
               var2, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("C5lL7tk0hguqRwFrIZ8rT4lJAxsjV+mWngExkVlOJCkPPDJDBN+eqJomdxdJvSJO4Ks=")
            );
            if (var9 == null) {
               this.clearAlignToolbarOverlay(var1, var2);
            } else {
               double var10 = this.toRenderX(var2, var8.x);
               double var12 = this.toRenderY(var2, var8.y);
               double var14 = this.toRenderSize(var2, var8.width);
               double var16 = var10 + (var14 - var9.width) / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773362459L, 15));
               double var18 = var12
                  - Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(4257777376299573255L, 14))
                  - var9.height
                  + this.toRenderSize(var2, Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(3637757171759557603L, 6)));
               if (var2.previewViewport != null) {
                  double var20 = var2.previewViewport.previewX;
                  double var22 = var2.previewViewport.previewX + var2.previewViewport.previewWidth - var9.width;
                  var16 = Math.max(var20, Math.min(var22, var16));
                  var18 = Math.max(var2.previewViewport.previewY, var18);
               }

               this.setAlignToolbarVisible(var1, var2, (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 14) != 0));
               this.moveAlignToolbarTo(var1, var2, var16, var18, var7);
               EditorRect var24 = this.findShellBlockRect(
                  var2, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("C5lL7tk0hguqRwFrIZ8rT4lJAxsjV+mWngExkVlOJCkPPDJDBN+eqJomdxdJvSJO4Ks=")
               );
               var2.alignToolbarBounds = var24 == null ? new EditorRect(var16, var18, var9.width, var9.height) : var24;
               var2.alignToolbarActionRects.clear();
               String var21 = this.resolveHoveredAlignToolbarActionId(var2, var3, var5);
               if (!this.equalsNullable(var2.alignToolbarHoverActionId, var21)) {
                  var2.alignToolbarHoverActionId = var21;

                  for (String var23 : FontAtlasGlyphs.Xqedii_32g98ba) {
                     this.setAlignToolbarActionVisual(var1, var23, this.equalsNullable(var23, var21));
                  }
               }
            }
         }
      } else {
         this.clearAlignToolbarOverlay(var1, var2);
      }
   }

   public void clearAlignToolbarOverlay(Player var1, EditorSession var2) {
      if ((1448105655 * (1448105655 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         if (var1 != null) {
            for (String var4 : FontAtlasGlyphs.Xqedii_32g98ba) {
               this.setAlignToolbarActionVisual(var1, var4, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(949420731, 7) != 0));
            }

            this.removeOverlayHud(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("C5lL7tk0hguqRwFrIZ8rT4lJAxsjV+mWngExkVlOJCkPPDJDBN+eqJomdxdJvSJO4Ks="));
            this.removeOverlayHud(
               var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("aDpjrzzFClSVWWQYVIQpWy0U/8gTmLkt1sP/78NrC/crz8tiFuN9sp2+t0JJf1dh6LS7rpuYuZrWnWc1LSV7K6ChbPPCwQ==")
            );

            for (String var6 : FontAtlasGlyphs.Xqedii_32g98ba) {
               this.removeOverlayHud(var1, var6);
            }
         }

         if (var2 != null) {
            if (var1 != null) {
               this.setAlignToolbarVisible(var1, var2, (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(382389295, 21) != 0));
            } else {
               var2.alignToolbarVisible = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-978144501, 19) != 0);
               var2.alignToolbarHoverActionId = null;
               var2.alignToolbarBounds = null;
               var2.alignToolbarActionRects.clear();
            }
         }
      }
   }

   public boolean isInsideAlignToolbar(EditorSession var1, double var2, double var4) {
      if ((1695684171 * 1695684171 + 1695684171 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.alignToolbarVisible) {
         double var6 = var1.shellHitX(var2);
         double var8 = var1.shellHitY(var4);
         if (!this.isInsideShellBlock(
               var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("C5lL7tk0hguqRwFrIZ8rT4lJAxsjV+mWngExkVlOJCkPPDJDBN+eqJomdxdJvSJO4Ks="), var6, var8
            )
            && !this.isInsideShellBlock(
               var1,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("aDpjrzzFClSVWWQYVIQpWy0U/8gTmLkt1sP/78NrC/crz8tiFuN9sp2+t0JJf1dh6LS7rpuYuZrWnWc1LSV7K6ChbPPCwQ=="),
               var6,
               var8
            )) {
            for (String var11 : FontAtlasGlyphs.Xqedii_32g98ba) {
               if (this.isInsideShellBlock(var1, var11, var6, var8) || this.isInsideShellBlock(var1, Xqedii_2k09xutsx76k9(var11), var6, var8)) {
                  return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596659717, 18) != 0);
               }
            }

            EditorRect var12 = this.findShellBlockRect(
               var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("C5lL7tk0hguqRwFrIZ8rT4lJAxsjV+mWngExkVlOJCkPPDJDBN+eqJomdxdJvSJO4Ks=")
            );
            if (var12 == null) {
               var12 = var1.alignToolbarBounds;
            }

            return var12 != null && var12.contains(var6, var8);
         } else {
            return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(299484344, 26) != 0);
         }
      } else {
         return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 16) != 0);
      }
   }

   public String resolveHoveredAlignToolbarActionId(EditorSession var1, double var2, double var4) {
      if ((1828177573 ^ 1828177573) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.alignToolbarVisible) {
         double var6 = var1.shellHitX(var2);
         double var8 = var1.shellHitY(var4);

         for (String var11 : FontAtlasGlyphs.Xqedii_32g98ba) {
            EditorRect var12 = this.findShellBlockRect(var1, Xqedii_1y4oz9jctfxko(var11));
            if (var12 != null && var12.contains(var6, var8)) {
               return var11;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public void setAlignToolbarActionVisual(Player var1, String var2, boolean var3) {
      if (((2139729159 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         String[] var4 = (String[])FontAtlasGlyphs.Xqedii_2ecdw82.get(var2);
         if (var4 != null && var4.length >= Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(250405468, 25)) {
            String var10000;
            if (var3) {
               String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(768972894, 22)];
               var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-2048730791, 10)] = var4[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1738658347, 6)];
               var10000 = this.firstNonBlank(var10001);
            } else {
               String[] var6 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1987128836, 0)];
               var6[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 30)] = var4[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 24)];
               var10000 = this.firstNonBlank(var6);
            }

            String var5 = var10000;
            this.setShellText(var1, var2, var5);
         }
      }
   }

   public void setAlignToolbarVisible(Player var1, EditorSession var2, boolean var3) {
      if ((-2006759086 * -2006759086 + -2006759086 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         if (var2.alignToolbarVisible != var3) {
            var2.alignToolbarVisible = var3;
            this.beginShellOpacityBatch(var2);

            try {
               this.setSidebarPanelVisible(
                  var1, var2, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("C5lL7tk0hguqRwFrIZ8rT4lJAxsjV+mWngExkVlOJCkPPDJDBN+eqJomdxdJvSJO4Ks="), var3
               );
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }

            if (!var3) {
               var2.alignToolbarHoverActionId = null;
               var2.alignToolbarBounds = null;
               var2.alignToolbarActionRects.clear();
            }
         }
      }
   }

   public void moveAlignToolbarTo(Player var1, EditorSession var2, double var3, double var5, boolean var7) {
      if (((-1683478444 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         ConfigurationSection var8 = this.findShellBlockSection(
            var2, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("C5lL7tk0hguqRwFrIZ8rT4lJAxsjV+mWngExkVlOJCkPPDJDBN+eqJomdxdJvSJO4Ks=")
         );
         EditorRect var9 = this.findShellBlockRect(
            var2, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("C5lL7tk0hguqRwFrIZ8rT4lJAxsjV+mWngExkVlOJCkPPDJDBN+eqJomdxdJvSJO4Ks=")
         );
         if (var8 != null && var9 != null) {
            double var10 = var3 - var9.x;
            double var12 = var5 - var9.y;
            if (!(Math.abs(var10) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-5028923529714410668L, 30)))
               || !(Math.abs(var12) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(1556279284483921129L, 16)))) {
               String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(149742172, 25)];
               var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 9)] = var8.getString(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("KUuWP/zv6CtaCFF3AR8ALjbSV7tbM1AKLSJ5tdsGn6lhXKnO4DHGt9aqLml7VMxWsRMvxQnnPzvBIR83SRGCvhhHNNw=")
               );
               String var14 = this.firstNonBlank(var10001);
               if (var14.isBlank()) {
                  if (var7) {
                     this.applyAlignToolbarTransitionOneTick(
                        var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("C5lL7tk0hguqRwFrIZ8rT4lJAxsjV+mWngExkVlOJCkPPDJDBN+eqJomdxdJvSJO4Ks=")
                     );
                  } else {
                     this.applyAlignToolbarTeleportDurationZero(
                        var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("C5lL7tk0hguqRwFrIZ8rT4lJAxsjV+mWngExkVlOJCkPPDJDBN+eqJomdxdJvSJO4Ks=")
                     );
                  }

                  this.moveShellElement(
                     var1,
                     var2,
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("C5lL7tk0hguqRwFrIZ8rT4lJAxsjV+mWngExkVlOJCkPPDJDBN+eqJomdxdJvSJO4Ks="),
                     var3,
                     var5,
                     var9.width,
                     var9.height
                  );
               } else {
                  HashMap var15 = new HashMap();
                  int var16 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 27);

                  for (Map var18 : var2.shellBlocks) {
                     var16++;
                     ConfigurationSection var19 = this.mapToSection(var18);
                     if (var19 != null) {
                        var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-488810107, 18)];
                        var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1956289001, 20)] = var19.getString(
                           Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("KUuWP/zv6CtaCFF3AR8ALjbSV7tbM1AKLSJ5tdsGn6lhXKnO4DHGt9aqLml7VMxWsRMvxQnnPzvBIR83SRGCvhhHNNw=")
                        );
                        String var20 = this.firstNonBlank(var10001);
                        if (this.belongsToSidebarPanel(var14, var20)) {
                           String var21 = this.resolveElementId(var19, var16, var15);
                           if (!var21.isBlank()) {
                              EditorRect var22 = this.findShellBlockRect(var2, var21);
                              if (var22 != null) {
                                 if (var7) {
                                    this.applyAlignToolbarTransitionOneTick(var1, var21);
                                 } else {
                                    this.applyAlignToolbarTeleportDurationZero(var1, var21);
                                 }

                                 this.moveShellElement(var1, var2, var21, var22.x + var10, var22.y + var12, var22.width, var22.height);
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

   public void applyAlignToolbarTransitionOneTick(Player var1, String var2) {
      if ((1181966121 ^ 1181966121) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = Xqedii_cjac1mdnfcgd(var2);
         this.setAlignToolbarHudTransitionOneTick(var1, var3);
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_efcwtpphrlrm(var3));
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_37j9d2a1b7mlp(var3));
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_3etieby4zamu6(var3));
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_22uh6qqkt9ro2(var3));
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_2euo1xv8nrfod(var3));
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_3fv6moeemdlfx(var3));
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_j0kzbxxuqgyu(var3));
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_3imlqin88ynwo(var3));
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_3qbb3hj9in12n(var3));
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_o8e159w7lisi(var3));
         String var4 = Xqedii_bnai3a0ogmjl(var3);
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_1c6ns9ump1ptq(var4));
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_lbtgr38hia1a(var4));
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_3ddkpqcjejm54(var4));
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_3k5opl78an3aa(var4));
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_h2lh8c7x7d08(var4));
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_32q87n91nrbs(var4));
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_13irmj5ikeg16(var4));
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_1bgif8etm3pbk(var4));
         this.setAlignToolbarHudTransitionOneTick(var1, Xqedii_21hpevuo887im(var4));
      }
   }

   public void applyAlignToolbarTeleportDurationZero(Player var1, String var2) {
      if (((1118063472 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = Xqedii_irclssoa0pyk(var2);
         this.setAlignToolbarHudNoTransition(var1, var3);
         this.setAlignToolbarHudNoTransition(var1, Xqedii_f5ciuehl2gn9(var3));
         this.setAlignToolbarHudNoTransition(var1, Xqedii_3kjcxhz5u83mz(var3));
         this.setAlignToolbarHudNoTransition(var1, Xqedii_1o0circjjfyw4(var3));
         this.setAlignToolbarHudNoTransition(var1, Xqedii_oshcuf0k39s2(var3));
         this.setAlignToolbarHudNoTransition(var1, Xqedii_1rwdryg5sjwy8(var3));
         this.setAlignToolbarHudNoTransition(var1, Xqedii_2rls4gdufjn9q(var3));
         this.setAlignToolbarHudNoTransition(var1, Xqedii_3eovxbrquah2b(var3));
         this.setAlignToolbarHudNoTransition(var1, Xqedii_1a9pxbjovcbqe(var3));
         this.setAlignToolbarHudNoTransition(var1, Xqedii_20tbe7qey5wz8(var3));
         this.setAlignToolbarHudNoTransition(var1, Xqedii_30613ysnl9tbg(var3));
         String var4 = Xqedii_3g6jgqzva943s(var3);
         this.setAlignToolbarHudNoTransition(var1, Xqedii_224k2e47zh5t2(var4));
         this.setAlignToolbarHudNoTransition(var1, Xqedii_15yvyr3f09zd3(var4));
         this.setAlignToolbarHudNoTransition(var1, Xqedii_2hh402agz85os(var4));
         this.setAlignToolbarHudNoTransition(var1, Xqedii_5s8wj71nk0fm(var4));
         this.setAlignToolbarHudNoTransition(var1, Xqedii_12s512zfhfa3n(var4));
         this.setAlignToolbarHudNoTransition(var1, Xqedii_3po698pprq1ec(var4));
         this.setAlignToolbarHudNoTransition(var1, Xqedii_2x5lhrusgcsay(var4));
         this.setAlignToolbarHudNoTransition(var1, Xqedii_2i969p0l6o0tz(var4));
         this.setAlignToolbarHudNoTransition(var1, Xqedii_f9a6fc1zs49f(var4));
      }
   }

   public void setAlignToolbarHudNoTransition(Player var1, String var2) {
      if ((-901973142 * -901973142 - -901973142 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         Entity var3 = this.hudService.getHud(var1, var2);
         if (var3 != null) {
            this.hudService.setHudTransitionTicks(var3, this.resolveCursorInterpDurationTicks(), this.resolveCursorTeleportDurationTicks());
         }
      }
   }

   public void setAlignToolbarHudTransitionOneTick(Player var1, String var2) {
      if ((-302229832 * -302229832 - -302229832 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         Entity var3 = this.hudService.getHud(var1, var2);
         if (var3 != null) {
            this.hudService.setHudTransitionTicks(var3, this.resolveCursorInterpDurationTicks(), this.resolveCursorTeleportDurationTicks());
         }
      }
   }

   public boolean applyAlignToolbarAction(Player var1, EditorSession var2, String var3) {
      if ((-627349932 * (-627349932 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         EditorRect var4 = this.getSelectedAlignToolbarBounds(var2);
         if (var4 == null) {
            return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 27) != 0);
         } else {
            double var5 = var2.previewViewport == null
               ? Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(359136643033836515L, 6))
               : var2.previewViewport.pageWidth;
            double var7 = var2.previewViewport == null
               ? Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(1083080682828638711L, 46))
               : var2.previewViewport.pageHeight;
            double var9 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(2796172106418827391L, 13));
            double var11 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(4020801858015067869L, 47));
            int var14 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1466372833, 28);
            switch (var3.hashCode()) {
               case -1540850050:
                  if (var3.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("HUgqPzUPoHtvR2/yKr/MbGqJgQ77jxAo3fAv3ulKt/U+n6c06HzEljB8eSGcqvdOmpUc+w=="))) {
                     var14 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596922245, 7);
                  }
                  break;
               case 45672564:
                  if (var3.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("T+M+k06N8du3pjaWVNsnIff+V0WXt7zgkZmTY3LRxaCPXN06vHsEr/kd3p0o1jPfR+RYKzKFv6819Q=="))) {
                     var14 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 10);
                  }
                  break;
               case 631529570:
                  if (var3.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("0gXMX5A+w6P0bZ38TbsWZolYrnrdVzmzF7qEJlF8KWNGSqEmm75ZRiPDbu3bkP1jT00SpA2S/ybfuw=="))) {
                     var14 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921901, 3);
                  }
                  break;
               case 650744460:
                  if (var3.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("0ykIjnU0lcWUqNEqatVkqz4ynbwNocLIqm8DfHPRcFEC9cbzpB7NcDH75mIqqhgjzp0BWZamxd6Zwg=="))) {
                     var14 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767677, 1);
                  }
                  break;
               case 1262364189:
                  if (var3.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+fR5/U5PKXcw24Mo7q05nUN4DlBb5frmXnhK+HSvm+17RdM9X/0vM/2YjyeemAQi73TbS12VI8Y="))) {
                     var14 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1243029819, 18);
                  }
                  break;
               case 1426012134:
                  if (var3.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("BPf50sChX4GnazT33vb98yrRdQjWEbuu7XdFNAW5H884M/7RPEvBz6orsUF72U2zEraGYmg5"))) {
                     var14 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 30);
                  }
            }

            switch (var14) {
               case 0:
                  var9 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-4965594660555198464L, 14)) - var4.x;
                  break;
               case 1:
                  var9 = (var5 - var4.width) / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032776516379L, 22)) - var4.x;
                  break;
               case 2:
                  var9 = var5 - var4.width - var4.x;
                  break;
               case 3:
                  var11 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773370651L, 39)) - var4.y;
                  break;
               case 4:
                  var11 = (var7 - var4.height) / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(4531150073049342677L, 35)) - var4.y;
                  break;
               case 5:
                  var11 = var7 - var4.height - var4.y;
                  break;
               default:
                  return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 13) != 0);
            }

            if (Math.abs(var9) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-115406725977171584L, 49))
               && Math.abs(var11) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-6646208059060816392L, 32))) {
               return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-731139589, 27) != 0);
            } else {
               int var13 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 8);
               this.recordEditorMutation(var2);

               for (String var15 : this.getSelectedTargetIds(var2)) {
                  EditorRect var16 = this.resolveAlignToolbarTargetBounds(var2, var15);
                  EditorRect var17 = this.getTargetBounds(var2, var15);
                  if (var16 != null && var17 != null) {
                     double var18 = var17.x - var16.x;
                     double var20 = var17.y - var16.y;
                     EditorRect var22 = new EditorRect(var16.x + var9 + var18, var16.y + var11 + var20, var17.width, var17.height);
                     if (!this.sameRect(var17, var22)) {
                        this.applyBoundsToTarget(var2, var15, var22);
                        var13 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 12);
                     }
                  }
               }

               if (var13 == 0) {
                  return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 4) != 0);
               } else {
                  this.rerenderEditableSelection(var1, var2);
                  this.updateAlignToolbarOverlay(var1, var2, var2.cursorX + var2.hitboxOffsetX, var2.cursorY + var2.hitboxOffsetY);
                  return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921869, 3) != 0);
               }
            }
         }
      } else {
         return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 0) != 0);
      }
   }

   public EditorRect getSelectedAlignToolbarBounds(EditorSession var1) {
      if ((166558266 * 166558266 - 166558266 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return null;
      } else {
         EditorRect var2 = null;

         for (String var4 : this.getSelectedTargetIds(var1)) {
            var2 = this.mergeBounds(var2, this.resolveAlignToolbarTargetBounds(var1, var4));
         }

         return var2;
      }
   }

   public EditorRect resolveAlignToolbarTargetBounds(EditorSession var1, String var2) {
      if (((-1274983052 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         EditorRect var3 = this.resolveSidebarEditableBounds(var1, var2);
         if (var3 != null) {
            double var4 = this.toSidebarDisplayPositionValue(var1, var2, EditorPropertyField.POSITION_X, var3.x);
            double var6 = this.toSidebarDisplayPositionValue(var1, var2, EditorPropertyField.POSITION_Y, var3.y);
            return new EditorRect(var4, var6, var3.width, var3.height);
         } else {
            return this.getTargetBounds(var1, var2);
         }
      }
   }

   public void applyPageOpenEffect(Player var1, EditorSession var2) {
      if ((-639991561 * -639991561 - -639991561 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.editMode) {
         String var3 = var2.pageOpenEffect;
         if (var3 != null && !var3.isBlank()) {
            HoverEffectRuntimeConfig var4 = this.resolveHoverEffectRuntimeConfig(var3);
            int var5 = var4 == null
                  || !(Math.abs(var4.startOffsetX()) > Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(5969906409845350775L, 36)))
                     && !(Math.abs(var4.startOffsetY()) > Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(6569564377955708650L, 41)))
                     && !(
                        Math.abs(var4.startScaleXMultiplier() - Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261190550643L, 19)))
                           > Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-3822993506154333546L, 47))
                     )
                     && !(
                        Math.abs(var4.startScaleYMultiplier() - Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-5662538274044903477L, 18)))
                           > Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-2354573304329776780L, 35))
                     )
               ? Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 4)
               : Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 18);
            if (var5 != 0) {
               int var6 = Math.abs(var4.startScaleXMultiplier() - Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251108207871214363L, 40)))
                        < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(3100276928731819892L, 38))
                     && Math.abs(var4.startScaleYMultiplier() - Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(5744473540982593717L, 33)))
                        < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5082781771092783416L, 40))
                  ? Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-588533253, 23)
                  : Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 10);
               if (var6 != 0) {
                  this.capturePreOpenBaseStates(var1, var2, var4.startOffsetX(), var4.startOffsetY());
               } else {
                  var2.activeEffectOverride = var3;

                  try {
                     for (String var8 : this.Xqedii_1b2p2a8(var2)) {
                        this.applyHoverEffectForTargetWithStartPosition(var1, var2, var8);
                     }
                  } finally {
                     var2.activeEffectOverride = null;
                  }
               }

               var2.activeEffectOverride = var3;

               try {
                  for (String var27 : this.Xqedii_1b2p2a8(var2)) {
                     this.restoreHoverEffectForTarget(var1, var2, var27);
                  }
               } finally {
                  var2.activeEffectOverride = null;
               }
            } else {
               var2.runtimeHitboxOffsetX = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773370651L, 36));
               var2.runtimeHitboxOffsetY = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773370651L, 63));
               if (var4 != null) {
                  var2.runtimeHitboxOffsetX = var4.offsetX();
                  var2.runtimeHitboxOffsetY = var4.offsetY();
               }

               var2.activeEffectOverride = var3;

               try {
                  for (String var26 : this.Xqedii_1b2p2a8(var2)) {
                     this.applyOpenAnimationForTarget(var1, var2, var26);
                  }
               } finally {
                  var2.activeEffectOverride = null;
               }
            }
         }
      }
   }

   public boolean applyPageCloseEffect(Player var1, EditorSession var2) {
      if (((1813763578 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.editMode && !var2.pendingCloseEffect) {
         String var3 = var2.pageCloseEffect;
         if (var3 != null && !var3.isBlank()) {
            HoverEffectRuntimeConfig var4 = this.resolveHoverEffectRuntimeConfig(var3);
            int var5 = var4 == null
               ? Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(197507763, 11)
               : Math.max(Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1449228471, 0), var4.durationTicks());
            if (var2.pageCloseUseHud && !var2.hudTextOnlyMode) {
               var2.hudTextOnlyMode = (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(633376462, 13) != 0);
               this.cameraService.setCameraActive(var1, (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 27) != 0));
               this.cameraService.forceStop(var1);
               this.removeHudById(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("CDJhik5v05Px0E/gY3bwCGZwBT9y9shiE4prtuqhz/UxM5bDryUt4A=="));
               Xqedii_nd6rkr3.Xqedii_2n2p2n9(
                  var1,
                  Xqedii_3uxgwyg.Xqedii_2p0af0b
                     .deserialize(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("b9/gndH984XQxU8hjSxa6wJhQ72oqqdj9lMmDaap/gxSRh3Jal/AJbGygazltB2/KicUSfa6J3o="))
               );
               this.plugin.teleportToReturnLocationAndClear(var1);
            }

            int var6 = var4 != null && var4.closeReversed()
               ? Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 10)
               : Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 30);
            var2.pendingCloseEffect = (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 5) != 0);
            if (var6 != 0) {
               HoverEffectRuntimeConfig var7 = this.buildReversedCloseConfig(var4);

               for (String var9 : this.Xqedii_1b2p2a8(var2)) {
                  this.applyHoverEffectForTargetWithConfig(var1, var2, var9, var7);
               }
            } else {
               var2.activeEffectOverride = var3;

               try {
                  for (String var14 : this.Xqedii_1b2p2a8(var2)) {
                     this.applyHoverEffectForTarget(var1, var2, var14);
                  }
               } finally {
                  var2.activeEffectOverride = null;
               }
            }

            Xqedii_38o2zes.Xqedii_2sj63w1(this.plugin, var1, () -> {
               if (((-1443629140 | 1) & 1) == 0) {
                  throw new IllegalStateException();
               } else {
                  EditorSession var3x = (EditorSession)this.editorSessions.get(var1.getUniqueId());
                  if (var3x != null && var3x == var2) {
                     if (var2.pendingCloseEffect) {
                        var2.pendingCloseEffect = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-892588266, 29) != 0);
                        this.plugin.closeUiForPlayerImmediate(var1);
                     }
                  }
               }
            }, var5 + Xqedii_p92kfr8.Xqedii_274j413le3omf(5251109032773370650L, 38));
            return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(299484344, 26) != 0);
         } else {
            return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 9) != 0);
         }
      } else {
         return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1449228470, 0) != 0);
      }
   }

   public void applyClickEffectForTarget(Player var1, EditorSession var2, String var3, String var4) {
      if (((-180492574 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.editMode) {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1206789692, 18)];
         var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1729290327, 4)] = var3;
         String var5 = this.firstNonBlank(var10001);
         if (!var5.isBlank() && var4 != null && !var4.isBlank()) {
            HoverEffectRuntimeConfig var6 = this.resolveHoverEffectRuntimeConfig(var4);
            int var7 = var6 == null
               ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1672773347, 6)
               : Math.max(Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-532191470, 20), var6.durationTicks());
            var2.activeEffectOverride = var4;

            try {
               this.applyHoverEffectForTarget(var1, var2, var5);
            } finally {
               var2.activeEffectOverride = null;
            }

            Xqedii_38o2zes.Xqedii_2sj63w1(this.plugin, var1, () -> {
               if (((1036430007 | 1) & 1) == 0) {
                  throw new IllegalStateException();
               } else {
                  EditorSession var4x = (EditorSession)this.editorSessions.get(var1.getUniqueId());
                  if (var4x != null && var4x == var2) {
                     this.restoreHoverEffectForTarget(var1, var4x, var5);
                  }
               }
            }, var7 + Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-4340946672465630595L, 44));
         }
      }
   }

   public LinkedHashSet<String> Xqedii_1b2p2a8(EditorSession var1) {
      if ((841061053 * 841061053 + 841061053 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         LinkedHashSet var2 = new LinkedHashSet();
         if (var1 != null && var1.elements != null) {
            for (HoverElement var4 : var1.elements) {
               String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1246961979, 22)];
               var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1501946585, 2)] = this.targetIdOf(var4);
               var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1282838493, 3)] = var4 == null ? null : var4.targetId;
               String var5 = this.firstNonBlank(var10001);
               if (!var5.isBlank()) {
                  var2.add(var5);
               }
            }

            return var2;
         } else {
            return var2;
         }
      }
   }

   public boolean isAnyEditorDropdownVisible(EditorSession var1) {
      if ((-537775371 * (-537775371 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 6) != 0);
      } else {
         return ((!var1.fileDropdownVisible
               && !var1.editDropdownVisible
               && !var1.selectionDropdownVisible
               && !var1.layerDropdownVisible
               && !var1.windowDropdownVisible
               && !var1.keyframeDropdownVisible
               && !var1.keyframeTimelineDropdownVisible
               && !var1.cursorPageDropdownVisible
               && !var1.cursorLayersDropdownVisible
               && !var1.cursorActionsDropdownVisible
               && !var1.cursorActionsEditDropdownVisible
            ? Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 17)
            : Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-836386674, 5)) != 0);
      }
   }

   public boolean isAnyEditorPopupVisible(EditorSession var1) {
      if ((-1346511264 * (-1346511264 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(197505715, 11) != 0);
      } else {
         return ((!var1.welcomePopupVisible && !var1.savePopupVisible && !var1.preferencesPopupVisible
            ? Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1291074126, 3)
            : Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-597052933, 17)) != 0);
      }
   }

   public boolean handleZoomToolClick(Player var1, EditorSession var2, ClickType var3, double var4, double var6) {
      if (((1149631967 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var2 == null || var3 == null || !var2.previewMode) {
         return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1729290327, 4) != 0);
      } else if (var3 != ClickType.LEFT && var3 != ClickType.RIGHT) {
         return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1449228470, 0) != 0);
      } else if (!this.isInsidePreviewArea(var2, var4, var6)) {
         return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1291074126, 3) != 0);
      } else if (this.isAnyEditorPopupVisible(var2)) {
         return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1197937376, 28) != 0);
      } else if (this.isAnyEditorDropdownVisible(var2)) {
         return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-994569767, 10) != 0);
      } else {
         double var8 = var3 == ClickType.LEFT
            ? Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-375912824507965072L, 6))
            : Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-3102980444035084999L, 50));
         return this.applyPreviewZoomAtCursor(var1, var2, var8, var4, var6);
      }
   }

   public boolean applyPreviewZoomAtCursor(Player var1, EditorSession var2, double var3, double var5, double var7) {
      if ((-1985578348 * -1985578348 - -1985578348 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null
         && var2 != null
         && Double.isFinite(var3)
         && !(var3 <= Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773370651L, 18)))) {
         double var9 = var5;
         double var11 = var7;
         Entity var13 = this.hudService.getHud(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("CDJhik5v05Px0E/gY3bwCGZwBT9y9shiE4prtuqhz/UxM5bDryUt4A=="));
         if (var13 != null) {
            Vector var14 = this.hudService.getHudLocation(var13);
            if (var14 != null) {
               var9 = var14.getX() + var2.hitboxOffsetX;
               var11 = var14.getY() + var2.hitboxOffsetY;
               var2.cursorX = var14.getX();
               var2.cursorY = var14.getY();
            }
         }

         boolean var15 = this.applyPreviewZoom(var2, var9, var11, var3);
         if (!var15) {
            return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921989, 7) != 0);
         } else {
            if (var2.activeHandle != null && var2.selectedElementId != null) {
               var2.resizeLastCursorX = this.toLogicalCursorX(var2, var9);
               var2.resizeLastCursorY = this.toLogicalCursorY(var2, var11);
               this.renderPreviewPageOutline(var1, var2);
               this.rerenderEditableContent(var1, var2);
               this.renderEditorTransparencyOverlay(var1, var2);
               this.updatePageInfoReadout(var1, var2);
            } else {
               this.refreshPreviewProjection(var1, var2);
            }

            this.updateCursorPositionReadout(var1, var2, var9, var11);
            this.updateAlignToolbarOverlay(var1, var2, var9, var11, (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596905477, 14) != 0));
            return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1712819061, 4) != 0);
         }
      } else {
         return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(366593208, 26) != 0);
      }
   }

   public boolean handleBucketFillToolClick(Player var1, EditorSession var2, ClickType var3, String var4) {
      if ((1100668517 * 1100668517 + 1100668517 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var2 == null || var3 != ClickType.LEFT) {
         return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 19) != 0);
      } else if (var4 != null && !var4.isBlank()) {
         String var5 = this.normalizeHexColor(var2.editorColor1);
         if (var5.isBlank()) {
            var5 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+iCnY/QyesslwHgeITWu0HwICtKVhKKadomSvNax0ZeoJW1jiRdK2A==");
         }

         if (!this.setTargetColor(var2, var4, var5)) {
            return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596925957, 12) != 0);
         } else {
            this.playEditorSfx(var1, var2, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("uCh9Z5j6ieMIXJMojvHDGWQw16GOvKOXAamOSQScE4m29O2GRjbRakJiryqNyZmYYX54xliY7BM="));
            this.recordEditorMutation(var2);
            this.rerenderEditableSelection(var1, var2);
            this.updateAlignToolbarOverlay(var1, var2, var2.cursorX + var2.hitboxOffsetX, var2.cursorY + var2.hitboxOffsetY);
            return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 29) != 0);
         }
      } else {
         return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 20) != 0);
      }
   }

   public boolean handleEyedropperToolClick(Player var1, EditorSession var2, ClickType var3, String var4) {
      if (((-432681807 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var2 == null || var3 != ClickType.LEFT) {
         return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 5) != 0);
      } else if (var4 != null && !var4.isBlank()) {
         String var5 = this.normalizeHexColor(this.readTargetColor(var2, var4));
         if (var5.isBlank()) {
            var5 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+iCnY/QyesslwHgeITWu0HwICtKVhKKadomSvNax0ZeoJW1jiRdK2A==");
         }

         var2.editorColor1 = var5;
         this.setShellColor(
            var1, var2, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("6sG2ARo4rzb7tY51oxX0FX0Xpf6WdTk379esvHjF05GcHwadSjDyq4iEcGITHC74L9AFWBk269E="), var5
         );
         if (var2.colorPickerVisible && var2.colorPickerSlot == Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1537945788, 23)) {
            this.syncColorPickerToColor(var1, var2, var5);
         }

         this.notifyColorPickerColorsChanged(var1, var2);
         this.playEditorSfx(var1, var2, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("1y1cQX99jllkDPY23WzaucA2tMuUguYdpP3Cw+yqrDilexWXmg9kUKkFDf1dxZ4MvpnE8g=="));
         return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921863, 1) != 0);
      } else {
         return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 4) != 0);
      }
   }

   public boolean handleTextToolClick(Player var1, EditorSession var2, ClickType var3, String var4) {
      if (((770554767 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var2 == null || var3 != ClickType.LEFT) {
         return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 13) != 0);
      } else if (var4 == null || var4.isBlank()) {
         return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 29) != 0);
      } else if (this.isTextTarget(var2, var4) && !this.isEditorTextPromptCoolingDown(var2)) {
         if (!this.equalsNullable(var2.selectedElementId, var4) || !var2.additionalSelectedElementIds.isEmpty()) {
            var2.selectedElementId = var4;
            var2.additionalSelectedElementIds.clear();
            this.normalizeSelectionState(var2);
            var2.activeHandle = null;
            var2.resizeAnchorValid = (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1672773283, 6) != 0);
            var2.handlesCollapsed = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-489072251, 18) != 0);
            this.updateHoveredHighlight(var1, var2, var2.hoveredElementId, null);
            var2.hoveredElementId = null;
            this.clearEditorOverlaysOnly(var1, var2);
            this.showSelectionOverlay(var1, var2);
            this.updateEditorPropertiesSidebar(var1, var2);
         }

         var2.pendingPropertyField = null;
         var2.pendingSavePopupField = null;
         var2.pendingActionTargetId = null;
         var2.pendingActionIndex = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1789706993, 25);
         var2.colorPickerHexInputPending = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(382389295, 21) != 0);
         this.beginPendingTextToolEdit(var1, var2, var4);
         if (!this.openEditorTextPrompt(
            var1, var2, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("SFUimsDb3G2w0LxZTZUxQXTS53n23CahS6wuB3atW+UMg6V+"), this.resolveTextToolPromptInitial(var2)
         )) {
            String var10002 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
               "lglREBJCqUONrbTJXg6fskzV+zr8X4cxSkGtULXAy8MzGCz+QVG3g2Do/ElJYDfph9FuD9WuT1XqzYmb9mYJdKepMIX2n2HXDagjUYTChPPDkcFikFg1N6LEx+g/V5cffgIcLpS0"
            );
            Object[] var10003 = new Object[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(598968688, 27)];
            var10003[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-994568743, 10)] = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
               "QvKFrbcyvjBuvURhC9bRfzx5Luy4fVRl2HUeSeuR1DAXbW2aBjoYEg=="
            );
            this.sendEditorInfo(var1, var10002, var10003);
         }

         return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-580144645, 24) != 0);
      } else {
         return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1396510354, 1) != 0);
      }
   }

   public String resolveTextToolPromptInitial(EditorSession var1) {
      if (((-943232294 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String var2 = var1.pendingTextToolOriginalText == null
            ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==")
            : var1.pendingTextToolOriginalText;
         String var3 = var2.trim();
         return !var3.isEmpty()
               && var2.length() <= Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1224941883, 20)
               && !Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("hOu9eh2zLTBMy3TkAr8agJ1KbuKI5xkU9R24M8fevZRBo6Jz+H/5jrF3VtAZcFespZ5T82r1").equals(var3)
               && !Xqedii_l2une6g.Xqedii_zm25fbt.matcher(var3).matches()
            ? var2
            : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
      }
   }

   public void beginPendingTextToolEdit(Player var1, EditorSession var2, String var3) {
      if ((-561938217 * (-561938217 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var2 != null && var3 != null && !var3.isBlank()) {
         this.clearPendingTextToolEdit(var1, var2, (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596925957, 12) != 0));
         String var4 = this.readTextTargetRawValue(var2, var3);
         var2.pendingTextToolTargetId = var3;
         var2.pendingTextToolOriginalText = var4;
         var2.pendingTextToolPlaceholderActive = (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 12) != 0);
         boolean var5 = this.setTargetText(
            var2, var3, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("hOu9eh2zLTBMy3TkAr8agJ1KbuKI5xkU9R24M8fevZRBo6Jz+H/5jrF3VtAZcFespZ5T82r1")
         );
         var2.pendingTextToolPlaceholderActive = ((var5
               && !this.equalsNullable(var4, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("hOu9eh2zLTBMy3TkAr8agJ1KbuKI5xkU9R24M8fevZRBo6Jz+H/5jrF3VtAZcFespZ5T82r1"))
            ? Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 27)
            : Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 19)) != 0);
         if (var5) {
            this.rerenderEditableSelection(var1, var2);
         }
      }
   }

   public void clearPendingTextToolEdit(Player var1, EditorSession var2, boolean var3) {
      if (((-1442125415 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var2 != null) {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(817650603, 7)];
         var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 23)] = var2.pendingTextToolTargetId;
         String var4 = this.firstNonBlank(var10001);
         String var5 = var2.pendingTextToolOriginalText == null
            ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==")
            : var2.pendingTextToolOriginalText;
         boolean var6 = var2.pendingTextToolPlaceholderActive;
         var2.pendingTextToolTargetId = null;
         var2.pendingTextToolOriginalText = null;
         var2.pendingTextToolPlaceholderActive = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(724614235, 31) != 0);
         if (var3 && !var4.isBlank() && var6) {
            if (this.isTextTarget(var2, var4)) {
               if (this.setTargetText(var2, var4, var5)) {
                  this.rerenderEditableSelection(var1, var2);
               } else {
                  this.updateEditorPropertiesSidebar(var1, var2);
               }
            }
         }
      }
   }

   public String readTextTargetRawValue(EditorSession var1, String var2) {
      if (((1289991113 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
            String var10000 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u6bFgC+JuJg6jwfnb8XcyG0rItjOwDbvVzEPAU72DfOh1JIG");
            String[] var10002 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 24)];
            var10002[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1062285787, 21)] = var3.type;
            if (!var10000.equalsIgnoreCase(this.firstNonBlank(var10002))) {
               return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
            } else {
               Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
               if (var4 == null) {
                  return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
               } else {
                  String var5;
                  if (this.hasMapPath(var4, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u6bFgC+JuJg6jwfnb8XcyG0rItjOwDbvVzEPAU72DfOh1JIG"))) {
                     var5 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u6bFgC+JuJg6jwfnb8XcyG0rItjOwDbvVzEPAU72DfOh1JIG");
                  } else if (this.hasMapPath(var4, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8uCzdjNzsVbVdHK0YbFR7P492zYhvUP3JZL+NtiEJqH1JGvYlTkdjiYOSfQde2aGlko="))
                     )
                   {
                     var5 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8uCzdjNzsVbVdHK0YbFR7P492zYhvUP3JZL+NtiEJqH1JGvYlTkdjiYOSfQde2aGlko=");
                  } else {
                     var5 = var4.containsKey(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("bJOeuZboreAkb2vED2EcJaFEOvsBp43s0DzWceduR53MTaGD3AssVA=="))
                        ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8uCzdjNzsVbVdHK0YbFR7P492zYhvUP3JZL+NtiEJqH1JGvYlTkdjiYOSfQde2aGlko=")
                        : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u6bFgC+JuJg6jwfnb8XcyG0rItjOwDbvVzEPAU72DfOh1JIG");
                  }

                  return this.stringValue(this.readMapPathValue(var4, var5));
               }
            }
         } else {
            return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
         }
      } else {
         return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
      }
   }

   public boolean isTextTarget(EditorSession var1, String var2) {
      if ((843711282 * 843711282 - 843711282 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         if (var3 == null) {
            return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1987128837, 0) != 0);
         } else {
            String var10000 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u6bFgC+JuJg6jwfnb8XcyG0rItjOwDbvVzEPAU72DfOh1JIG");
            String[] var10002 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1282838493, 3)];
            var10002[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 13)] = var3.type;
            return var10000.equalsIgnoreCase(this.firstNonBlank(var10002));
         }
      } else {
         return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 14) != 0);
      }
   }

   public void updateMoveDrag(Player var1, EditorSession var2, double var3, double var5, boolean var7) {
      if ((387408319 * (387408319 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var2 != null && !var2.moveStartBounds.isEmpty()) {
         EditorRect var8 = this.getSelectedBounds(var2);
         EditorRect var9 = this.getMoveStartSelectionBounds(var2);
         double var10 = this.toLogicalCursorX(var2, var3);
         double var12 = this.toLogicalCursorY(var2, var5);
         double var14 = var10 - var2.dragStartCursorX;
         double var16 = var12 - var2.dragStartCursorY;
         if (var7 && var9 != null) {
            Vector var18 = this.resolveMoveSnapOffset(var2, var9, var14, var16);
            var14 += var18.getX();
            var16 += var18.getY();
         }

         int var35 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 20);
         LinkedHashMap var19 = new LinkedHashMap();

         for (Entry var21 : var2.moveStartBounds.entrySet()) {
            EditorRect var22 = (EditorRect)var21.getValue();
            if (var22 != null) {
               double var23 = this.snapToGrid(var2, var22.x + var14);
               double var25 = this.snapToGrid(var2, var22.y + var16);
               EditorRect var27 = new EditorRect(var23, var25, var22.width, var22.height);
               EditorRect var28 = this.getTargetBounds(var2, (String)var21.getKey());
               if (this.sameRect(var28, var27)) {
                  if (this.translateInheritedRootWithMovedAncestor(var1, var2, (String)var21.getKey(), var19)) {
                     var35 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1501946589, 2);
                  }
               } else if (var28 != null) {
                  var2.moveDragMutated = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-581335582, 26) != 0);
                  this.applyBoundsToTarget(var2, (String)var21.getKey(), var27);
                  double var29 = var27.x - var28.x;
                  double var31 = var27.y - var28.y;
                  this.translateTargetElements(var1, var2, (String)var21.getKey(), var29, var31);
                  this.translateAnimationMoveDragChildren(var1, var2, (String)var21.getKey(), var29, var31);
                  HoverElement var33 = this.findFirstByTargetId(var2, (String)var21.getKey());
                  String var10000;
                  if (var33 == null) {
                     var10000 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
                  } else {
                     String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1282838493, 3)];
                     var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1134875856, 15)] = var33.targetPath;
                     var10000 = this.firstNonBlank(var10001);
                  }

                  String var34 = var10000;
                  if (!var34.isBlank()) {
                     double[] var10002 = new double[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1544482018, 17)];
                     var10002[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1235852936, 24)] = var29;
                     var10002[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921862, 0)] = var31;
                     var19.put(var34, var10002);
                  }

                  var35 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(768972894, 22);
               }
            }
         }

         if (var35 != 0) {
            EditorRect var36 = this.getSelectedBounds(var2);
            if (var8 != null && var36 != null) {
               this.translateSelectionOutline(var1, var2, var36.x - var8.x, var36.y - var8.y);
               this.updateEditorPropertiesSidebar(var1, var2);
               this.updateAlignToolbarOverlay(
                  var1,
                  var2,
                  var2.cursorX + var2.hitboxOffsetX,
                  var2.cursorY + var2.hitboxOffsetY,
                  (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 25) != 0)
               );
               if (var2.activeTool == EditorTool.ANIMATION) {
                  this.renderAnimationPreviewMotionPath(var1, var2);
               }
            } else {
               this.renderSelectionOverlay(var1, var2);
               this.updateEditorPropertiesSidebar(var1, var2);
               this.updateAlignToolbarOverlay(
                  var1, var2, var2.cursorX + var2.hitboxOffsetX, var2.cursorY + var2.hitboxOffsetY, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-641419247, 2) != 0)
               );
            }
         }
      } else {
         this.stopCursorToolDrag(var2);
      }
   }

   public boolean snapListLayoutMoveDragBack(Player var1, EditorSession var2) {
      if ((-886301581 ^ -886301581) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.moveStartBounds.isEmpty() && var2.moveDragMutated && var2.activeTool != EditorTool.ANIMATION) {
         int var3 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1672773283, 6);

         for (String var5 : this.getSelectedTargetIds(var2)) {
            if (this.isListLayoutChildPath(var2, this.resolveMoveDragTargetPath(var2, var5))) {
               var3 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(633376462, 13);
               break;
            }
         }

         if (var3 == 0) {
            return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 30) != 0);
         } else {
            String var12 = var2.moveDragStartSnapshot;
            if (var12 != null && !var12.isBlank()) {
               YamlConfiguration var13 = new YamlConfiguration();

               try {
                  var13.loadFromString(var12);
               } catch (Exception var11) {
                  return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1701189515, 28) != 0);
               }

               LinkedHashMap var6 = new LinkedHashMap();

               for (Entry var8 : var2.moveStartBounds.entrySet()) {
                  EditorRect var9 = (EditorRect)var8.getValue();
                  EditorRect var10 = this.getTargetBounds(var2, (String)var8.getKey());
                  if (var9 != null && var10 != null) {
                     String var10001 = (String)var8.getKey();
                     double[] var10002 = new double[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242636603, 17)];
                     var10002[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1712819045, 4)] = var9.x - var10.x;
                     var10002[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(395015526, 12)] = var9.y - var10.y;
                     var6.put(var10001, var10002);
                  }
               }

               var2.rawBlocks = this.copyBlocks(var13.getList(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("jZMD11okutCwYth/L0tcF13dPIn7mr8xL1PzklWh78l8xYo5VAOl5g==")));
               var2.renderBlocks = this.resolveRenderableBlocks(var2.rawBlocks, var2.components, var2);

               for (Entry var15 : (Iterable<Entry>) (var6.entrySet())) {

                  double[] var16 = (double[])var15.getValue();
                  this.translateTargetElements(
                     var1,
                     var2,
                     (String)var15.getKey(),
                     var16[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1898841462, 8)],
                     var16[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 24)]
                  );
               }

               var2.moveDragMutated = (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-641419243, 2) != 0);
               return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1134843088, 15) != 0);
            } else {
               return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 24) != 0);
            }
         }
      } else {
         return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 9) != 0);
      }
   }

   public String resolveMoveDragTargetPath(EditorSession var1, String var2) {
      if (((878682706 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1729290311, 4)];
         var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-2048730791, 10)] = this.resolveInheritedTargetRootPath(var1, var2);
         String var3 = this.firstNonBlank(var10001);
         if (!var3.isBlank()) {
            return var3;
         } else {
            HoverElement var4 = this.findFirstByTargetId(var1, var2);
            String var10000;
            if (var4 == null) {
               var10000 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
            } else {
               var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596925957, 12)];
               var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1761428680, 15)] = var4.targetPath;
               var10000 = this.firstNonBlank(var10001);
            }

            return var10000;
         }
      }
   }

   public boolean isListLayoutChildPath(EditorSession var1, String var2) {
      if (((-983699147 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         int var3 = var2.lastIndexOf(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("SKT2z2K3gBM5oazG/53+1yzXwMi+KrA/o2UTnPiLTex09ee5SYcy0HaVch+3xlLw"));
         if (var3 <= 0) {
            return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(395011430, 12) != 0);
         } else {
            Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var2.substring(Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1785176531, 30), var3));
            return TemplateListLayout.hasListLayout(var4);
         }
      } else {
         return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1987128837, 0) != 0);
      }
   }

   public boolean translateInheritedRootWithMovedAncestor(Player var1, EditorSession var2, String var3, Map<String, double[]> var4) {
      if ((-1169729599 * (-1169729599 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var4.isEmpty()) {
         return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1529557180, 23) != 0);
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(993564418, 31)];
         var10001[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 3)] = this.resolveInheritedTargetRootPath(var2, var3);
         String var5 = this.firstNonBlank(var10001);
         if (var5.isBlank()) {
            return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 29) != 0);
         } else {
            for (Entry var7 : var4.entrySet()) {
               if (var5.startsWith(Xqedii_32qthpmvqknu6((String)var7.getKey()))) {
                  double[] var8 = (double[])var7.getValue();
                  this.translateTargetElements(
                     var1, var2, var3, var8[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-836386642, 5)], var8[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 8)]
                  );
                  return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1266752924, 14) != 0);
               }
            }

            return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 26) != 0);
         }
      }
   }

   public EditorRect getMoveStartSelectionBounds(EditorSession var1) {
      if ((-1362601538 * (-1362601538 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.moveStartBounds.isEmpty()) {
         EditorRect var2 = null;

         for (EditorRect var4 : var1.moveStartBounds.values()) {
            var2 = this.mergeBounds(var2, var4);
         }

         return var2;
      } else {
         return null;
      }
   }

   public Vector resolveMoveSnapOffset(EditorSession var1, EditorRect var2, double var3, double var5) {
      if ((-1305049449 * -1305049449 - -1305049449 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         EditorRect var7 = new EditorRect(var2.x + var3, var2.y + var5, var2.width, var2.height);
         LinkedHashSet var8 = new LinkedHashSet(var1.moveStartBounds.keySet());
         if (var8.isEmpty()) {
            var8.addAll(this.getSelectedTargetIds(var1));
         }

         LinkedHashSet var9 = new LinkedHashSet();
         Double var10 = null;
         Double var11 = null;

         for (HoverElement var13 : var1.elements) {
            String var14 = this.targetIdOf(var13);
            if (var14 != null && !var14.isBlank() && !var8.contains(var14) && var9.add(var14)) {
               EditorRect var15 = this.getTargetBounds(var1, var14);
               if (var15 != null) {
                  var10 = this.pickBetterSnapDelta(
                     var10,
                     this.resolveAxisSnapDelta(
                        var7.x,
                        var7.x + var7.width / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(1633107983827206331L, 22)),
                        var7.maxX(),
                        var15.x,
                        var15.x + var15.width / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(7682983667525749681L, 26)),
                        var15.maxX()
                     )
                  );
                  var11 = this.pickBetterSnapDelta(
                     var11,
                     this.resolveAxisSnapDelta(
                        var7.y,
                        var7.y + var7.height / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032723039003L, 26)),
                        var7.maxY(),
                        var15.y,
                        var15.y + var15.height / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556822892446460915L, 48)),
                        var15.maxY()
                     )
                  );
               }
            }
         }

         double var20 = var1.previewViewport == null
            ? Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-1288575124756877997L, 39))
            : var1.previewViewport.pageWidth;
         double var21 = var1.previewViewport == null
            ? Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-8406818044941168063L, 21))
            : var1.previewViewport.pageHeight;
         var10 = this.pickBetterSnapDelta(var10, this.snapDelta(var7.x, Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(9062300163278554538L, 36))));
         var10 = this.pickBetterSnapDelta(var10, this.snapDelta(var7.maxX(), var20));
         var11 = this.pickBetterSnapDelta(
            var11, this.snapDelta(var7.y, Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773370651L, 48)))
         );
         var11 = this.pickBetterSnapDelta(var11, this.snapDelta(var7.maxY(), var21));
         return new Vector(
            var10 == null ? Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261190638579L, 17)) : var10,
            var11 == null ? Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261190638579L, 1)) : var11,
            Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773370651L, 33))
         );
      } else {
         return new Vector(
            Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261190638579L, 20)),
            Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-2363536641649280140L, 49)),
            Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261190638579L, 3))
         );
      }
   }

   public Double resolveAxisSnapDelta(double var1, double var3, double var5, double var7, double var9, double var11) {
      if ((1531828445 ^ 1531828445) != 0) {
         throw new IllegalStateException();
      } else {
         Object var13 = null;
         var13 = this.pickBetterSnapDelta((Double)var13, this.snapDelta(var1, var7));
         var13 = this.pickBetterSnapDelta((Double)var13, this.snapDelta(var1, var11));
         var13 = this.pickBetterSnapDelta((Double)var13, this.snapDelta(var5, var7));
         var13 = this.pickBetterSnapDelta((Double)var13, this.snapDelta(var5, var11));
         return this.pickBetterSnapDelta((Double)var13, this.snapDelta(var3, var9));
      }
   }

   public Double snapDelta(double var1, double var3) {
      if ((-1421562573 * (-1421562573 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         double var5 = var3 - var1;
         return Math.abs(var5) > Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-3683356689320452809L, 55)) ? null : var5;
      }
   }

   public Double pickBetterSnapDelta(Double var1, Double var2) {
      if ((1857868434 * (1857868434 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var2 == null) {
         return var1;
      } else {
         return var1 != null && !(Math.abs(var2) < Math.abs(var1)) ? var1 : var2;
      }
   }

   public void onCursorMove(Player var1, double var2, double var4) {
      if (((-813955991 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         EditorSession var6 = (EditorSession)this.editorSessions.get(var1.getUniqueId());
         if (var6 != null && var6.editMode) {
            var6.cursorX = var2;
            var6.cursorY = var4;
            boolean var7 = var1.isSneaking();
            double var8 = var2 + var6.hitboxOffsetX;
            double var10 = var4 + var6.hitboxOffsetY;
            EditorTool var12 = this.resolveTimelineSelectedTool(var6);
            int var13 = var6.activeTool == EditorTool.ANIMATION && var12 == EditorTool.CURSOR
               ? Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 8)
               : Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(91648302, 24);
            int var14 = var6.activeTool == EditorTool.ANIMATION && var12 == EditorTool.SCALE
               ? Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(384486447, 21)
               : Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1761428680, 15);
            int var15 = !var6.opacitySliderDragActive
                  && !var6.animationTimelineSliderDragActive
                  && !var6.timelineKeyframeDragActive
                  && !var6.sidebarFieldDragActive
                  && !var6.colorPickerGradDragActive
                  && !var6.colorPickerHueDragActive
                  && !var6.moveDragActive
                  && !var6.previewPanActive
                  && !var6.marqueeSelectActive
                  && var6.activeHandle == null
                  && !var6.layersReorderActive
                  && (var6.layersReorderArmedTargetId == null || var6.layersReorderArmedTargetId.isBlank())
                  && var6.actionsReorderArmedIndex < 0
                  && var6.actionsReorderCurrentIndex < 0
               ? Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 8)
               : Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(299484344, 26);
            if (var15 != 0
               || !Double.isFinite(var6.lastProcessedHitX)
               || !Double.isFinite(var6.lastProcessedHitY)
               || !(Math.abs(var8 - var6.lastProcessedHitX) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(-4511901871892546389L, 43)))
               || !(Math.abs(var10 - var6.lastProcessedHitY) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(572854292116636876L, 44)))) {
               var6.lastProcessedHitX = var8;
               var6.lastProcessedHitY = var10;
               this.updateCursorPositionReadout(var1, var6, var8, var10);
               if (var6.welcomePopupVisible) {
                  this.updateWelcomePopupHover(var1, var6, var8, var10);
                  this.clearAlignToolbarOverlay(var1, var6);
               } else if (var6.savePopupVisible) {
                  this.updateSavePopupHover(var1, var6, var8, var10);
                  this.clearAlignToolbarOverlay(var1, var6);
               } else if (var6.preferencesPopupVisible) {
                  this.updatePreferencesPopupHover(var1, var6, var8, var10);
                  this.clearAlignToolbarOverlay(var1, var6);
               } else {
                  this.updateSidebarTabHover(var1, var6, var8, var10);
                  this.updateEditorNavbarHover(var1, var6, var8, var10);
                  this.updateTimelineKeyframeDropdownHover(var1, var6, var8, var10);
                  this.updateTimelineTimelineDropdownHover(var1, var6, var8, var10);
                  this.updateCursorActionsDropdownHover(var1, var6, var8, var10);
                  this.updateCursorActionsEditDropdownHover(var1, var6, var8, var10);
                  if (!this.isLeftHeld(var6)) {
                     this.finishResizeDragHistory(var6);
                  }

                  if (!this.isLeftHeld(var6) && var6.opacitySliderDragActive) {
                     this.stopOpacitySliderDrag(var6, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(633376462, 13) != 0));
                  }

                  if (var6.opacitySliderDragActive && this.isLeftHeld(var6)) {
                     this.updateOpacitySliderDrag(var1, var6, var8);
                  } else {
                     if (!this.isLeftHeld(var6) && var6.animationTimelineSliderDragActive) {
                        this.stopAnimationTimelineSliderDrag(var6);
                        this.applyTimelinePositionPreview(var1, var6);
                        this.updateEditorPropertiesSidebar(var1, var6);
                     }

                     if (var6.animationTimelineSliderDragActive && this.isLeftHeld(var6)) {
                        this.updateAnimationTimelineSliderDrag(var1, var6, var8);
                     } else {
                        if (!this.isLeftHeld(var6) && var6.timelineKeyframeDragActive) {
                           this.stopAnimationTimelineKeyframeDrag(var6, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1064382939, 21) != 0));
                           String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-865357317, 27)];
                           var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1580045720, 14)] = var6.animationTimelineTargetId;
                           var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(817650603, 7)] = var6.animationTimelineSelectedTargetId;
                           String var16 = this.firstNonBlank(var10001);
                           if (!var16.isBlank()) {
                              this.renderAnimationTimelineKeyframes(var1, var6, var16);
                              this.applyTimelinePositionPreview(var1, var6);
                              this.updateEditorPropertiesSidebar(var1, var6);
                           }
                        }

                        if (var6.timelineKeyframeDragActive && this.isLeftHeld(var6)) {
                           this.updateTimelineKeyframeDrag(var1, var6, var8);
                        } else {
                           if (!this.isLeftHeld(var6) && var6.colorPickerGradDragActive) {
                              var6.colorPickerGradDragActive = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-648444446, 26) != 0);
                           }

                           if (var6.colorPickerGradDragActive && this.isLeftHeld(var6)) {
                              this.updateColorPickerGradientDrag(var1, var6, var8, var10);
                           } else {
                              if (!this.isLeftHeld(var6) && var6.colorPickerHueDragActive) {
                                 var6.colorPickerHueDragActive = (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 5) != 0);
                              }

                              if (var6.colorPickerHueDragActive && this.isLeftHeld(var6)) {
                                 this.updateColorPickerHueDrag(var1, var6, var8, var10);
                              } else {
                                 if (!this.isLeftHeld(var6) && var6.sidebarFieldDragActive) {
                                    EditorPropertyField var29 = var6.sidebarFieldDragField;
                                    int var17 = var29 != null
                                          && this.isDimensionSidebarField(var29)
                                          && var6.sidebarFieldDragLastValue == null
                                          && !var6.sidebarFieldDragMutated
                                       ? Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596954629, 15)
                                       : Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 27);
                                    this.resetSidebarFieldDrag(var1, var6);
                                    if (var17 != 0) {
                                       this.startSidebarInputPrompt(var1, var6, var29);
                                    }

                                    this.updateEditorPropertiesSidebar(var1, var6);
                                 }

                                 if (var6.sidebarFieldDragActive && this.isLeftHeld(var6)) {
                                    this.updateSidebarFieldDrag(var1, var6, var8);
                                 } else if (!this.handleActionsReorderCursorMove(var1, var6, var8, var10)) {
                                    if (!this.handleLayersReorderCursorMove(var1, var6, var8, var10)) {
                                       if (!this.isLeftHeld(var6) && (var6.moveDragActive || var6.previewPanActive || var6.marqueeSelectActive)) {
                                          boolean var30 = var6.moveDragActive;
                                          boolean var35 = var6.previewPanActive;
                                          boolean var18 = var6.marqueeSelectActive;
                                          if (var30) {
                                             this.snapListLayoutMoveDragBack(var1, var6);
                                          }

                                          this.stopCursorToolDrag(var6);
                                          if (var18) {
                                             this.finalizeMarqueeSelection(var1, var6, var8, var10);
                                          } else if (!var30 || !this.isCursorLikeTool(var6.activeTool) && var13 == 0) {
                                             if (var30
                                                && (var6.activeTool == EditorTool.SCALE || var14 != 0)
                                                && Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("/rMLmVIz9pTX2yEUhU/QMyI2dKWW6n8aVTkkEd1IgYePQHfG")
                                                   .equals(var6.activeHandle)) {
                                                var6.activeHandle = null;
                                                var6.resizeAnchorValid = (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 10) != 0);
                                                var6.handlesCollapsed = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(197505715, 11) != 0);
                                                this.renderSelectionOverlay(var1, var6);
                                                if (var14 != 0) {
                                                   this.syncTimelineSelectedKeyframeValueFromTarget(var6);
                                                   String[] var47 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596925957, 12)];
                                                   var47[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 30)] = var6.animationTimelineTargetId;
                                                   this.renderAnimationTimelineKeyframes(var1, var6, this.firstNonBlank(var47));
                                                   this.applyTimelinePositionPreview(var1, var6);
                                                }

                                                this.updateEditorPropertiesSidebar(var1, var6);
                                             } else if (var35) {
                                                this.animatePreviewPanBackToBounds(var1, var6, var8, var10);
                                             }
                                          } else {
                                             this.renderSelectionOverlay(var1, var6);
                                             if (var13 != 0) {
                                                String[] var45 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767676, 0)];
                                                var45[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 1)] = var6.animationTimelineTargetId;
                                                var45[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-1670663685, 30)] = var6.animationTimelineSelectedTargetId;
                                                String var19 = this.firstNonBlank(var45);
                                                this.syncTimelinePreviewAppliedBoundsToCurrent(var6, var19);
                                                this.syncTimelineSelectedKeyframeValueFromTarget(var6);
                                                String[] var10004 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 12)];
                                                var10004[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 9)] = var6.animationTimelineTargetId;
                                                this.renderAnimationTimelineKeyframes(var1, var6, this.firstNonBlank(var10004));
                                             }

                                             this.updateEditorPropertiesSidebar(var1, var6);
                                          }
                                       }

                                       if (!this.isLeftHeld(var6)
                                          && (var6.activeTool == EditorTool.SCALE || var14 != 0)
                                          && var6.activeHandle != null
                                          && var6.resizeStepReady) {
                                          if (var14 != 0 && !var6.handlesCollapsed) {
                                             this.syncTimelineSelectedKeyframeValueFromTarget(var6);
                                             String[] var48 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1899092544, 29)];
                                             var48[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-641419243, 2)] = var6.animationTimelineTargetId;
                                             this.renderAnimationTimelineKeyframes(var1, var6, this.firstNonBlank(var48));
                                             this.applyTimelinePositionPreview(var1, var6);
                                          }

                                          this.updateEditorPropertiesSidebar(var1, var6);
                                       }

                                       if (var6.activeHandle == null) {
                                          var6.lastShiftHeld = var7;
                                       }

                                       if (var6.handlesCollapsed && !this.isLeftHeld(var6)) {
                                          for (String var36 : this.getSelectedTargetIds(var6)) {
                                             this.restoreElementLayer(var1, var6, var36);
                                          }

                                          var6.activeHandle = null;
                                          var6.resizeAnchorValid = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-869329206, 5) != 0);
                                          var6.handlesCollapsed = (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 22) != 0);
                                          if (var14 != 0) {
                                             this.renderSelectionOverlay(var1, var6);
                                             this.updateEditorPropertiesSidebar(var1, var6);
                                             return;
                                          }

                                          this.restoreHandlesOnly(var1, var6);
                                       }

                                       if (!this.isLeftHeld(var6)
                                          && var14 != 0
                                          && var6.activeHandle == null
                                          && !var6.handlesCollapsed
                                          && var6.resizeStepReady
                                          && !this.isLayersLeftHeld(var6)) {
                                          this.syncTimelineSelectedKeyframeValueFromTarget(var6);
                                          String[] var49 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1580062104, 14)];
                                          var49[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 1)] = var6.animationTimelineTargetId;
                                          this.renderAnimationTimelineKeyframes(var1, var6, this.firstNonBlank(var49));
                                          this.applyTimelinePositionPreview(var1, var6);
                                          this.updateEditorPropertiesSidebar(var1, var6);
                                          var6.resizeStepReady = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(724614235, 31) != 0);
                                       }

                                       if ((this.isCursorLikeTool(var6.activeTool) || var13 != 0) && this.isLeftHeld(var6)) {
                                          if (var6.marqueeSelectActive) {
                                             this.updateMarqueeSelectionOverlay(var1, var6, var8, var10);
                                             return;
                                          }

                                          if (var6.moveDragActive && var6.selectedElementId != null) {
                                             this.updateMoveDrag(var1, var6, var8, var10, var7);
                                             if (var13 != 0) {
                                                String[] var46 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596925957, 11)];
                                                var46[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1785176531, 30)] = var6.animationTimelineTargetId;
                                                var46[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1729290311, 4)] = var6.animationTimelineSelectedTargetId;
                                                String var34 = this.firstNonBlank(var46);
                                                this.syncTimelinePreviewAppliedBoundsToCurrent(var6, var34);
                                                this.syncTimelineSelectedKeyframeValueFromTarget(var6);
                                                String[] var52 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-244405054, 17)];
                                                var52[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 7)] = var6.animationTimelineTargetId;
                                                this.renderAnimationTimelineKeyframes(var1, var6, this.firstNonBlank(var52));
                                             }

                                             return;
                                          }

                                          if (var6.previewPanActive) {
                                             this.updatePreviewPan(var1, var6, var8, var10);
                                             return;
                                          }
                                       }

                                       if ((var6.activeTool == EditorTool.COMMANDS || var6.activeTool == EditorTool.ANIMATION)
                                          && this.isLeftHeld(var6)
                                          && var6.previewPanActive) {
                                          this.updatePreviewPan(var1, var6, var8, var10);
                                       } else {
                                          if ((var6.activeTool == EditorTool.SCALE || var14 != 0)
                                             && var6.activeHandle != null
                                             && var6.selectedElementId != null
                                             && this.isLeftHeld(var6)) {
                                             if (Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("/rMLmVIz9pTX2yEUhU/QMyI2dKWW6n8aVTkkEd1IgYePQHfG")
                                                .equals(var6.activeHandle)) {
                                                if (var6.moveDragActive) {
                                                   this.updateMoveDrag(var1, var6, var8, var10, var7);
                                                   if (var14 != 0) {
                                                      this.syncTimelineSelectedKeyframeValueFromTarget(var6);
                                                      String[] var51 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-641419247, 2)];
                                                      var51[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1266769308, 14)] = var6.animationTimelineTargetId;
                                                      this.renderAnimationTimelineKeyframes(var1, var6, this.firstNonBlank(var51));
                                                   }
                                                }

                                                return;
                                             }

                                             int var32 = var7 != var6.lastShiftHeld
                                                ? Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1712819061, 4)
                                                : Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1785176531, 30);
                                             if (!var6.resizeAnchorValid) {
                                                this.captureResizeAnchor(var6);
                                             }

                                             EditorRect var37 = new EditorRect(
                                                var6.resizeSelectionStartX, var6.resizeSelectionStartY, var6.resizeSelectionStartW, var6.resizeSelectionStartH
                                             );
                                             if (var37.width > Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773370651L, 32))
                                                || var37.height > Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-81847865655387337L, 31))) {
                                                double var41 = this.toLogicalCursorX(var6, var8);
                                                double var20 = this.toLogicalCursorY(var6, var10);
                                                if (var32 == 0 && var6.resizeStepReady) {
                                                   double var22 = Math.abs(var41 - var6.resizeLastCursorX);
                                                   double var24 = Math.abs(var20 - var6.resizeLastCursorY);
                                                   if (var22 < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(7845265555282167762L, 17))
                                                      && var24 < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5395224220849226499L, 5))) {
                                                      return;
                                                   }
                                                }

                                                EditorRect var23 = this.resizeRect(var6, var37, var6.activeHandle, var41, var20, var7);

                                                for (Entry var25 : var6.resizeStartBounds.entrySet()) {
                                                   String var26 = (String)var25.getKey();
                                                   EditorRect var27 = (EditorRect)var25.getValue();
                                                   EditorRect var28 = this.transformBounds(var6, var27, var37, var23);
                                                   if (var27 != null && var28 != null && !this.sameRect(var27, var28)) {
                                                      this.beginResizeDragHistory(var6);
                                                      var6.resizeDragMutated = (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1881387913, 19) != 0);
                                                   }

                                                   this.applyBoundsToTarget(var6, var26, var28);
                                                }

                                                if (var14 != 0) {
                                                   this.syncTimelineSelectedKeyframeValueFromTarget(var6);
                                                   String[] var50 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1856815857, 25)];
                                                   var50[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(764778590, 22)] = var6.animationTimelineTargetId;
                                                   this.renderAnimationTimelineKeyframes(var1, var6, this.firstNonBlank(var50));
                                                }

                                                var6.lastShiftHeld = var7;
                                                var6.resizeLastCursorX = var41;
                                                var6.resizeLastCursorY = var20;
                                                var6.resizeStepReady = (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 9) != 0);
                                                this.rerenderEditableSelection(var1, var6);
                                                this.updateEditorPropertiesSidebar(var1, var6);
                                                return;
                                             }
                                          }

                                          int var33 = var6.activeTool == EditorTool.ALIGN
                                                && var6.alignToolbarVisible
                                                && this.isInsideAlignToolbar(var6, var8, var10)
                                             ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(993564418, 31)
                                             : Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 10);
                                          String var38 = var33 != 0 ? null : this.findHoveredHandle(var6, var8, var10);
                                          Object var39 = null;
                                          Object var42 = null;
                                          if (var33 != 0) {
                                             var42 = null;
                                          } else if (var38 != null) {
                                             var42 = var6.selectedElementId;
                                          } else if (var6.activeHandle != null && var6.selectedElementId != null) {
                                             var42 = var6.selectedElementId;
                                          } else {
                                             var39 = this.findHoveredElement(var6, var8, var10);
                                             var42 = var39 == null ? null : this.targetIdOf((HoverElement)var39);
                                          }

                                          if (!this.equalsNullable(var6.hoveredElementId, (String)var42)) {
                                             this.updateHoveredHighlight(var1, var6, var6.hoveredElementId, (String)var42);
                                             var6.hoveredElementId = (String)var42;
                                          }

                                          if (!this.equalsNullable(var6.hoveredHandle, var38)) {
                                             this.updateHandleHover(var1, var6, var38);
                                             var6.hoveredHandle = var38;
                                          }

                                          this.updateAlignToolbarOverlay(var1, var6, var8, var10);
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
         }
      }
   }

   public boolean isCursorLikeTool(EditorTool var1) {
      if (((887073710 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return ((var1 != EditorTool.CURSOR && var1 != EditorTool.ALIGN
            ? Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-489072251, 18)
            : Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-595873285, 20)) != 0);
      }
   }

   @Override
   public void startMoveDrag(EditorSession var1, double var2, double var4) {
      if ((1075718884 * 1075718884 - 1075718884 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         this.stopCursorToolDrag(var1);
         if (var1 != null) {
            for (String var7 : this.resolveMoveDragTargetIds(var1)) {
               EditorRect var8 = this.getTargetBounds(var1, var7);
               if (var8 != null) {
                  var1.moveStartBounds.put(var7, var8);
               }
            }

            if (!var1.moveStartBounds.isEmpty()) {
               this.beginMoveDragHistory(var1);
               var1.dragStartCursorX = this.toLogicalCursorX(var1, var2);
               var1.dragStartCursorY = this.toLogicalCursorY(var1, var4);
               var1.moveDragActive = (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 25) != 0);
            }
         }
      }
   }

   public LinkedHashSet<String> resolveMoveDragTargetIds(EditorSession var1) {
      if ((-1581434536 * -1581434536 - -1581434536 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         LinkedHashSet var2 = new LinkedHashSet();
         if (var1 == null) {
            return var2;
         } else {
            List var3 = this.getSelectedTargetIds(var1);
            var2.addAll(var3);
            if (var3.isEmpty()) {
               return var2;
            } else {
               if (var1.activeTool == EditorTool.ANIMATION) {
                  EditorTool var4 = this.resolveTimelineSelectedTool(var1);
                  if (var4 == EditorTool.CURSOR || var4 == EditorTool.SCALE) {
                     return var2;
                  }
               }

               for (String var5 : (Iterable<String>) (var3)) {

                  if (var5 != null && !var5.isBlank()) {
                     var2.addAll(this.resolveDescendantLayerTargetIds(var1, var5));
                  }
               }

               return var2;
            }
         }
      }
   }

   public void translateAnimationMoveDragChildren(Player var1, EditorSession var2, String var3, double var4, double var6) {
      if ((-1163995803 * (-1163995803 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         if (!(Math.abs(var4) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-3513677373080941697L, 61)))
            || !(Math.abs(var6) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(-2372496140983949497L, 2)))) {
            if (var2.activeTool == EditorTool.ANIMATION) {
               EditorTool var8 = this.resolveTimelineSelectedTool(var2);
               if (var8 == EditorTool.CURSOR || var8 == EditorTool.SCALE) {
                  for (String var10 : this.resolveDescendantLayerTargetIds(var2, var3)) {
                     if (var10 != null && !var10.isBlank() && !var2.moveStartBounds.containsKey(var10)) {
                        this.translateTargetElements(var1, var2, var10, var4, var6);
                     }
                  }
               }
            }
         }
      }
   }

   public void startPreviewPan(EditorSession var1, double var2, double var4) {
      if ((-1311385455 * (-1311385455 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         this.stopCursorToolDrag(var1);
         if (var1 != null && var1.previewMode && var1.previewViewport != null && this.isInsidePreviewArea(var1, var2, var4)) {
            var1.previewPanActive = (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(1550561787, 31) != 0);
            var1.previewPanStartCursorX = var2;
            var1.previewPanStartCursorY = var4;
            var1.previewPanStartX = var1.previewViewport.panX;
            var1.previewPanStartY = var1.previewViewport.panY;
         }
      }
   }

   @Override
   public void stopCursorToolDrag(EditorSession var1) {
      if (((512140330 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         if (var1.moveDragActive) {
            this.finishMoveDragHistory(var1);
         } else {
            var1.moveDragStartSnapshot = null;
            var1.moveDragMutated = (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 21) != 0);
         }

         var1.moveDragActive = (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 18) != 0);
         var1.previewPanActive = (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 10) != 0);
         var1.moveStartBounds.clear();
      }
   }

   public void startMarqueeSelection(EditorSession var1, double var2, double var4) {
      if ((-835706594 * (-835706594 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         this.stopCursorToolDrag(var1);
         var1.marqueeSelectActive = (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921869, 3) != 0);
         var1.marqueeStartCursorX = var2;
         var1.marqueeStartCursorY = var4;
      }
   }

   public void stopMarqueeSelection(Player var1, EditorSession var2) {
      if (((1418286643 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var2 != null) {
         var2.marqueeSelectActive = (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 15) != 0);
         this.removeOverlayHud(
            var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Dl08l5jpsAMummA1ofSytz5cu0EbmypwD0ieMeNXBEBqCHcP8MYgm5ouLzoWgFRBlMhtf2V6LHOBDyGge68zqw==")
         );
         this.removeOverlayHud(
            var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("xo1um0MtJOKxPjFOt+hbqx05osryNeD5HHsZQzAQa/ck1oG7fZ9jKnHsHb9RiNVueoGnjtGp+Tetffb8mX+MnI9145lQNg==")
         );
         this.removeOverlayHud(
            var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("o2hQ7Mt/oMiRELgZBScG8J3QCwpwNWmR1YLV5O3julFJzFhnFk2qVzHV3/1z1B2czsJjWKvTMOP9b0ZFKYo6To8+")
         );
         this.removeOverlayHud(
            var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ONLpQbhVwxOa5GDgmUHZL08ujBNztUU4krlY2hGa10PVBM9vZZ4tT+kBypfbVp8yWiPwEYMszTe/q5b04+5Kb93zjyw=")
         );
      }
   }

   public void updateMarqueeSelectionOverlay(Player var1, EditorSession var2, double var3, double var5) {
      if (((492933548 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         EditorRect var7 = this.getMarqueeLogicalRect(var2, var3, var5);
         if (var7 == null) {
            this.stopMarqueeSelection(var1, var2);
         } else {
            double var8 = this.toRenderX(var2, var7.x);
            double var10 = this.toRenderY(var2, var7.y);
            double var12 = this.toRenderSize(var2, var7.width);
            double var14 = this.toRenderSize(var2, var7.height);
            double var16 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(5664490564065869337L, 10));
            this.upsertOverlayHud(
               var1,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Dl08l5jpsAMummA1ofSytz5cu0EbmypwD0ieMeNXBEBqCHcP8MYgm5ouLzoWgFRBlMhtf2V6LHOBDyGge68zqw=="),
               this.positionCalculator
                  .calculateBoxPlacement(
                     var8,
                     var10,
                     var16,
                     Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-235569636256577537L, 6)), var12),
                     Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261257681907L, 28))
                  )
                  .location(),
               this.positionCalculator
                  .calculateBoxPlacement(
                     var8,
                     var10,
                     var16,
                     Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773894427L, 21)), var12),
                     Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556329761481403379L, 51))
                  )
                  .scale(),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3i4g+OJ8wXvGEAYOZQSyoUmyaEppqc0UqBfFjl664CR6l7KrJfHudPNZMlghiYgZ")
            );
            this.upsertOverlayHud(
               var1,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("xo1um0MtJOKxPjFOt+hbqx05osryNeD5HHsZQzAQa/ck1oG7fZ9jKnHsHb9RiNVueoGnjtGp+Tetffb8mX+MnI9145lQNg=="),
               this.positionCalculator
                  .calculateBoxPlacement(
                     var8,
                     var10 + var14 - Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-3593791467347389572L, 59)),
                     var16,
                     Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556891354225159155L, 46)), var12),
                     Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(5454257856749534720L, 10))
                  )
                  .location(),
               this.positionCalculator
                  .calculateBoxPlacement(
                     var8,
                     var10 + var14 - Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251056616992490267L, 46)),
                     var16,
                     Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(8069811095801686326L, 24)), var12),
                     Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-6351135268508865446L, 32))
                  )
                  .scale(),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3i4g+OJ8wXvGEAYOZQSyoUmyaEppqc0UqBfFjl664CR6l7KrJfHudPNZMlghiYgZ")
            );
            this.upsertOverlayHud(
               var1,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("o2hQ7Mt/oMiRELgZBScG8J3QCwpwNWmR1YLV5O3julFJzFhnFk2qVzHV3/1z1B2czsJjWKvTMOP9b0ZFKYo6To8+"),
               this.positionCalculator
                  .calculateBoxPlacement(
                     var8,
                     var10,
                     var16,
                     Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2844842162365639667L, 60)),
                     Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-4340907094341997955L, 44)), var14)
                  )
                  .location(),
               this.positionCalculator
                  .calculateBoxPlacement(
                     var8,
                     var10,
                     var16,
                     Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-81847865117992137L, 31)),
                     Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(7845265555282167762L, 17)), var14)
                  )
                  .scale(),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3i4g+OJ8wXvGEAYOZQSyoUmyaEppqc0UqBfFjl664CR6l7KrJfHudPNZMlghiYgZ")
            );
            this.upsertOverlayHud(
               var1,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ONLpQbhVwxOa5GDgmUHZL08ujBNztUU4krlY2hGa10PVBM9vZZ4tT+kBypfbVp8yWiPwEYMszTe/q5b04+5Kb93zjyw="),
               this.positionCalculator
                  .calculateBoxPlacement(
                     var8 + var12 - Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251096186526188315L, 44)),
                     var10,
                     var16,
                     Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(4020837076746895069L, 47)),
                     Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773362451L, 15)), var14)
                  )
                  .location(),
               this.positionCalculator
                  .calculateBoxPlacement(
                     var8 + var12 - Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893260115848179L, 32)),
                     var10,
                     var16,
                     Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773372134L, 12)),
                     Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-639423014345981979L, 10)), var14)
                  )
                  .scale(),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3i4g+OJ8wXvGEAYOZQSyoUmyaEppqc0UqBfFjl664CR6l7KrJfHudPNZMlghiYgZ")
            );
         }
      }
   }

   public void finalizeMarqueeSelection(Player var1, EditorSession var2, double var3, double var5) {
      if (((746620297 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         EditorRect var7 = this.getMarqueeLogicalRect(var2, var3, var5);
         this.stopMarqueeSelection(var1, var2);
         if (var7 != null) {
            LinkedHashSet var8 = new LinkedHashSet();

            for (HoverElement var10 : var2.elements) {
               String var11 = this.targetIdOf(var10);
               if (var11 != null && !var11.isBlank() && !var8.contains(var11) && this.isTargetInteractable(var2, var11)) {
                  EditorRect var12 = this.getTargetBounds(var2, var11);
                  if (var12 != null && this.rectsIntersect(var7, var12)) {
                     var8.add(var11);
                  }
               }
            }

            var2.selectedElementId = null;
            var2.additionalSelectedElementIds.clear();
            if (!var8.isEmpty()) {
               String var13 = (String)var8.iterator().next();
               var2.selectedElementId = var13;
               var8.remove(var13);
               var2.additionalSelectedElementIds.addAll(var8);
               this.normalizeSelectionState(var2);
               this.updateHoveredHighlight(var1, var2, var2.hoveredElementId, null);
               var2.hoveredElementId = null;
               this.showSelectionOverlay(var1, var2);
               this.refreshAfterSelectionChange(var1, var2);
            } else {
               this.clearSelectionState(var1, var2);
            }
         }
      }
   }

   public EditorRect getMarqueeLogicalRect(EditorSession var1, double var2, double var4) {
      if ((1879701137 * (1879701137 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return null;
      } else {
         double var6 = this.toLogicalCursorX(var1, var1.marqueeStartCursorX);
         double var8 = this.toLogicalCursorY(var1, var1.marqueeStartCursorY);
         double var10 = this.toLogicalCursorX(var1, var2);
         double var12 = this.toLogicalCursorY(var1, var4);
         double var14 = Math.min(var6, var10);
         double var16 = Math.min(var8, var12);
         double var18 = Math.max(var6, var10);
         double var20 = Math.max(var8, var12);
         return new EditorRect(
            var14,
            var16,
            Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773370651L, 40)), var18 - var14),
            Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(2287831824860699708L, 56)), var20 - var16)
         );
      }
   }

   public boolean rectsIntersect(EditorRect var1, EditorRect var2) {
      if ((-469003834 * -469003834 + -469003834 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         return ((var1.x <= var2.maxX() && var1.maxX() >= var2.x && var1.y <= var2.maxY() && var1.maxY() >= var2.y
            ? Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(790031052, 13)
            : Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-978144501, 19)) != 0);
      } else {
         return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 2) != 0);
      }
   }

   public void clearSelectionState(Player var1, EditorSession var2) {
      if (((-664466325 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var2 != null) {
         this.finishLayersReorderDrag(var1, var2, (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 24) != 0));
         this.stopMarqueeSelection(var1, var2);
         List var3 = this.getSelectedTargetIds(var2);
         int var4 = var2.activeHandle == null && !var2.handlesCollapsed
            ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1544219874, 17)
            : Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 8);
         this.updateHoveredHighlight(var1, var2, var2.hoveredElementId, null);
         var2.hoveredElementId = null;
         var2.selectedElementId = null;
         var2.additionalSelectedElementIds.clear();
         var2.activeHandle = null;
         var2.resizeAnchorValid = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-489072251, 18) != 0);
         var2.handlesCollapsed = (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 20) != 0);
         var2.hoveredHandle = null;
         var2.selectionOutlineVisible = (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1881912201, 19) != 0);
         var2.pendingPropertyField = null;
         this.clearPendingTextToolEdit(var1, var2, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(598968688, 27) != 0));
         var2.pendingActionTargetId = null;
         var2.pendingActionIndex = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(596921860, 3);
         this.clearActionsReorderState(var2);
         this.clearActionsReorderGhostHud(var1, var2);
         this.stopOpacitySliderDrag(var2, (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1134843088, 15) != 0));
         this.stopAnimationTimelineSliderDrag(var2);
         this.stopAnimationTimelineKeyframeDrag(var2, (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1024364884, 9) != 0));
         this.clearTimelineSelectedKeyframe(var2);
         var2.alignToolbarClickGuardUntilTick = Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556900957772033011L, 43);
         var2.alignToolbarConsumeHoldClick = (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 17) != 0);
         var2.colorPickerGradDragActive = (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 26) != 0);
         var2.colorPickerHueDragActive = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1024365396, 9) != 0);
         this.resetSidebarFieldDrag(var1, var2);

         for (String var6 : (Iterable<String>) (var3)) {

            if (var4 != 0) {
               this.restoreElementLayer(var1, var2, var6);
            }
         }

         this.clearEditorOverlaysOnly(var1, var2);
         this.clearAlignToolbarOverlay(var1, var2);
         var2.rightSidebarHoverTab = null;
         this.updateSidebarTabVisualState(
            var1, var2, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(183296604, 25) != 0), (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 8) != 0)
         );
         this.updateEditorPropertiesSidebar(var1, var2);
         this.renderLayersPanel(var1, var2);
      }
   }

   public void refreshAfterSelectionChange(Player var1, EditorSession var2) {
      if ((1030876816 * 1030876816 + 1030876816 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         this.updateEditorPropertiesSidebar(var1, var2);
         this.renderLayersPanel(var1, var2);
      }
   }

   public void setActiveTool(Player var1, EditorSession var2, EditorTool var3) {
      if ((-394739060 * (-394739060 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var2 != null && var3 != null && var2.activeTool != var3) {
         this.finishLayersReorderDrag(var1, var2, (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 16) != 0));
         EditorTool var4 = var2.activeTool;
         int var5 = var2.selectedElementId == null && var2.additionalSelectedElementIds.isEmpty()
            ? Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(724614235, 31)
            : Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1738658347, 6);
         int var6 = !this.isCursorLikeTool(var4) && var4 != EditorTool.SCALE || !this.isCursorLikeTool(var3) && var3 != EditorTool.SCALE
            ? Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 20)
            : Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921349, 9);
         int var7 = (!this.isCursorLikeTool(var4) && var4 != EditorTool.SCALE || var3 != EditorTool.ANIMATION)
               && (var4 != EditorTool.ANIMATION || !this.isCursorLikeTool(var3) && var3 != EditorTool.SCALE)
            ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(91648302, 24)
            : Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(496782209, 30);
         int var8 = (!this.isCursorLikeTool(var4) || var3 != EditorTool.COMMANDS) && (var4 != EditorTool.COMMANDS || !this.isCursorLikeTool(var3))
            ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1282838485, 3)
            : Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921869, 3);
         int var9 = var5 == 0
               || var6 == 0 && var7 == 0 && var8 == 0 && this.getSelectedTargetIds(var2).size() <= Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(949420603, 7)
            ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-836386642, 5)
            : Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(1550561787, 31);
         if (var9 == 0 && var5 != 0) {
            this.clearSelectionState(var1, var2);
         }

         int var10 = var2.activeHandle == null && !var2.handlesCollapsed
            ? Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1712819045, 4)
            : Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1969624971, 28);

         for (String var12 : this.getSelectedTargetIds(var2)) {
            if (var10 != 0) {
               this.restoreElementLayer(var1, var2, var12);
            }
         }

         this.stopCursorToolDrag(var2);
         this.stopMarqueeSelection(var1, var2);
         this.resetSidebarFieldDrag(var1, var2);
         this.stopAnimationTimelineSliderDrag(var2);
         this.stopAnimationTimelineKeyframeDrag(var2, (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 23) != 0));
         this.clearTimelineSelectedKeyframe(var2);
         var2.alignToolbarClickGuardUntilTick = Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556896009969708019L, 42);
         var2.alignToolbarConsumeHoldClick = (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 19) != 0);
         this.clearPendingTextToolEdit(var1, var2, (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 2) != 0));
         var2.pendingActionTargetId = null;
         var2.pendingActionIndex = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(2124571572, 22);
         var2.activeTool = var3;
         this.playEditorSfx(var1, var2, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("hQiq2X+lbL7AzLcqwoN16l1hcVqmnV6PhZLUwtPquakGFGY43ycm+lJv681aS8OfSaRW7NZU"));
         if (var3 == EditorTool.COMMANDS) {
            var2.rightSidebarTab = EditorSidebarTab.PROPERTIES;
            var2.rightSidebarHoverTab = null;
         }

         var2.activeHandle = null;
         var2.resizeAnchorValid = (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 12) != 0);
         var2.handlesCollapsed = (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 24) != 0);
         var2.hoveredHandle = null;
         this.renderEditorToolSidebar(var1, var2);
         this.clearEditorOverlaysOnly(var1, var2);
         if (var9 != 0 && var5 != 0) {
            this.showSelectionOverlay(var1, var2);
         }

         this.beginShellOpacityBatch(var2);

         try {
            this.updateSidebarTabVisualState(
               var1, var2, (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 23) != 0), (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-648444446, 26) != 0)
            );
            this.updateEditorPropertiesSidebar(var1, var2);
         } finally {
            this.endShellOpacityBatch(var1, var2);
         }

         if (var3 == EditorTool.ANIMATION) {
            var2.animationTimelineTick = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(45824151, 23);
            var2.animationTimelineSliderDragTick = null;
         }

         if (var4 == EditorTool.ANIMATION || var3 == EditorTool.ANIMATION) {
            this.syncAnimationTimelineUi(var1, var2);
         }

         double var17 = var2.cursorX + var2.hitboxOffsetX;
         double var13 = var2.cursorY + var2.hitboxOffsetY;
         if (var3 == EditorTool.ALIGN) {
            this.updateAlignToolbarOverlay(var1, var2, var17, var13);
         } else {
            this.clearAlignToolbarOverlay(var1, var2);
         }
      }
   }

   public boolean clickHoveredElement(Player var1, ClickType var2) {
      if ((1758672111 * 1758672111 + 1758672111 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         EditorSession var3 = (EditorSession)this.editorSessions.get(var1.getUniqueId());
         if (var3 != null && var3.editMode) {
            if (!this.isLeftHeld(var3)) {
               this.finishResizeDragHistory(var3);
            }

            long var4 = Xqedii_38o2zes.Xqedii_316oplq();
            if (var2 == ClickType.LEFT && var4 <= var3.pasteSelectionClickGuardUntilTick) {
               var3.lastLeftClickTick = var4;
               return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 21) != 0);
            } else {
               int var6 = var4 - var3.lastLeftClickTick <= Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(3965404546351199455L, 57)
                  ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(633376462, 13)
                  : Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1701189515, 28);
               boolean var7 = var1.isSneaking();
               double var8 = var3.cursorX + var3.hitboxOffsetX;
               double var10 = var3.cursorY + var3.hitboxOffsetY;
               boolean var12 = this.hasLegacySidebarUi(var3);
               if (var3.welcomePopupVisible) {
                  if (var2 == ClickType.LEFT) {
                     var3.lastLeftClickTick = var4;
                     if (var6 == 0) {
                        this.handleWelcomePopupClick(var1, var3, var8, var10);
                     }
                  }

                  this.clearAlignToolbarOverlay(var1, var3);
                  return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 1) != 0);
               } else if (var3.savePopupVisible) {
                  if (var2 == ClickType.LEFT) {
                     this.handleSavePopupClick(var1, var3, var8, var10);
                  }

                  this.clearAlignToolbarOverlay(var1, var3);
                  return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-320709624, 1) != 0);
               } else if (var3.preferencesPopupVisible) {
                  if (var2 == ClickType.LEFT) {
                     var3.lastLeftClickTick = var4;
                     if (var6 == 0) {
                        this.handlePreferencesPopupClick(var1, var3, var8, var10);
                     }
                  }

                  this.clearAlignToolbarOverlay(var1, var3);
                  return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-994569767, 10) != 0);
               } else {
                  if (var3.activeTool == EditorTool.ALIGN && var2 == ClickType.LEFT && var3.alignToolbarConsumeHoldClick) {
                     if (var6 != 0 || var4 <= var3.alignToolbarClickGuardUntilTick) {
                        var3.alignToolbarConsumeHoldClick = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1134875856, 15) != 0);
                        return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1197937376, 28) != 0);
                     }

                     var3.alignToolbarConsumeHoldClick = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1580045720, 14) != 0);
                  }

                  if (var3.activeTool == EditorTool.ALIGN && this.handleAlignToolbarClick(var1, var3, var2, var8, var10)) {
                     return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-994569767, 10) != 0);
                  } else if (var3.activeTool == EditorTool.ZOOM
                     && (var2 == ClickType.LEFT || var2 == ClickType.RIGHT)
                     && this.isInsidePreviewArea(var3, var8, var10)) {
                     return this.handleZoomToolClick(var1, var3, var2, var8, var10);
                  } else if (var2 == ClickType.RIGHT && this.handleTimelineContextClick(var1, var3, var8, var10)) {
                     return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1989139533, 11) != 0);
                  } else if (var2 == ClickType.RIGHT) {
                     this.setFileDropdownVisible(var1, var3, (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 17) != 0));
                     this.setEditDropdownVisible(var1, var3, (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-978144501, 19) != 0));
                     this.setSelectionDropdownVisible(var1, var3, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(733186416, 27) != 0));
                     this.setLayerDropdownVisible(var1, var3, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1898841462, 8) != 0));
                     this.setWindowDropdownVisible(var1, var3, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1062285787, 21) != 0));
                     if (var3.keyframeDropdownVisible) {
                        this.setTimelineKeyframeDropdownVisible(var1, var3, (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1529557180, 23) != 0));
                     }

                     if (var3.keyframeTimelineDropdownVisible) {
                        this.setTimelineTimelineDropdownVisible(var1, var3, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1672773283, 6) != 0));
                     }

                     if (var3.cursorActionsDropdownVisible) {
                        this.setCursorActionsDropdownVisible(var1, var3, (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 27) != 0));
                     }

                     if (var3.cursorActionsEditDropdownVisible) {
                        this.setCursorActionsEditDropdownVisible(var1, var3, (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 24) != 0));
                     }

                     this.clearActionsReorderState(var3);
                     this.clearActionsReorderGhostHud(var1, var3);
                     this.updateEditorNavbarHover(var1, var3, var8, var10);
                     this.finishLayersReorderDrag(var1, var3, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(949420731, 7) != 0));
                     this.stopCursorToolDrag(var3);
                     this.clearSelectionState(var1, var3);
                     this.clearAlignToolbarOverlay(var1, var3);
                     return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-2048729767, 10) != 0);
                  } else {
                     EditorTool var13 = this.resolveTimelineSelectedTool(var3);
                     int var14 = var3.activeTool != EditorTool.ANIMATION || var13 != EditorTool.CURSOR && var13 != EditorTool.SCALE
                        ? Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(790022860, 13)
                        : Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(395015526, 12);
                     int var15 = var3.activeTool == EditorTool.ANIMATION
                           && this.isInsideShellBlock(
                              var3,
                              Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("biyMea37BisqbgjdL11dlf7YGKzJD48W8r2to7pqgNR6bI4dI3H/VTAnv9n1tJZFvAC1ntYLDekrtsFzmDwhow=="),
                              var8,
                              var10
                           )
                        ? Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596925957, 12)
                        : Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 8);
                     EditorTool var16 = var3.activeTool;
                     if (var3.activeTool == EditorTool.ANIMATION) {
                        if (var14 != 0) {
                           if (var15 == 0) {
                              this.normalizePreviewForTransform(var1, var3);
                           }

                           var16 = var13;
                        } else {
                           var16 = EditorTool.COMMANDS;
                        }
                     }

                     if (var2 == ClickType.LEFT && (var3.animationTimelineSliderDragActive || var3.timelineKeyframeDragActive)) {
                        int var17 = var3.activeTool != EditorTool.ANIMATION
                              || !this.isInsideShellBlock(
                                    var3,
                                    Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                                       "biyMea37BisqbgjdL11dlf7YGKzJD48W8r2to7pqgNR6bI4dI3H/VTAnv9n1tJZFvAC1ntYLDekrtsFzmDwhow=="
                                    ),
                                    var8,
                                    var10
                                 )
                                 && !this.isInsideShellBlock(
                                    var3,
                                    Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                                       "TSa+6DMCueD2JyKJMcu5eLU+O4qtAMqpMq6qRJEIukt7VUZBzygcEL0sCTvvvzPQHZzJ2PPdt6Ug8APcxKqXXfOvvQxs+RyMbDI="
                                    ),
                                    var8,
                                    var10
                                 )
                                 && !this.isInsideShellBlock(
                                    var3,
                                    Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                                       "2+FzmcJOePpm3UUaT0XtLz4eQzFEwnN9Rbx5ZySD811TJFPhfcI2JoM21XQiR0mMHVbvHbkWNOR9o3tHGKXiUVGnY8nTvoUVU8cYsvoK14MAPxESXvRByOQ/FOY="
                                    ),
                                    var8,
                                    var10
                                 )
                                 && !this.isInsideShellBlock(
                                    var3,
                                    Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                                       "tP53vMHvP8I2o38UgyEIeY3BwkG8QPK94zhdTXBUXkbGvJny7vZ90rU0Q3HB73sV5Evysa1uKspYi3m3+RJaVuy2bxtOzNBRT/nFDfKToBCj1S1+iQ416bVFCTOEow=="
                                    ),
                                    var8,
                                    var10
                                 )
                           ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1989137485, 11)
                           : Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1738658347, 6);
                        boolean var18 = var3.timelineKeyframeDragActive;
                        if (var6 != 0 && var17 != 0) {
                           var3.lastLeftClickTick = var4;
                           return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(197507763, 11) != 0);
                        }

                        this.stopAnimationTimelineSliderDrag(var3);
                        this.stopAnimationTimelineKeyframeDrag(var3, (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(395015526, 12) != 0));
                        if (var18) {
                           String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596659717, 17)];
                           var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 8)] = var3.animationTimelineTargetId;
                           var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596922117, 8)] = var3.animationTimelineSelectedTargetId;
                           String var19 = this.firstNonBlank(var10001);
                           if (!var19.isBlank()) {
                              this.renderAnimationTimelineKeyframes(var1, var3, var19);
                              this.applyTimelinePositionPreview(var1, var3);
                              this.updateEditorPropertiesSidebar(var1, var3);
                           }
                        }
                     }

                     if (var2 == ClickType.LEFT) {
                        var3.lastLeftClickTick = var4;
                     }

                     int var24 = var2 == ClickType.LEFT && var6 == 0 && this.isValidActionsSecondClickEditAttempt(var3, var8, var10, var4)
                        ? Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596954629, 15)
                        : Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 13);
                     int var25 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 0);
                     if (var7 && var6 != 0) {
                        HoverElement var26 = this.findHoveredElement(var3, var8, var10);
                        String var20 = var26 == null ? null : this.targetIdOf(var26);
                        String var21 = this.resolveLayerSelectionTargetByHit(var3, var8, var10);
                        var25 = (var20 == null || var20.isBlank() || this.isSelectedTarget(var3, var20) || !this.isTargetInteractable(var3, var20))
                              && (var21 == null || var21.isBlank() || this.isSelectedTarget(var3, var21))
                           ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1672773283, 6)
                           : Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 5);
                     }

                     if (this.isCursorLikeTool(var16) && (var3.moveDragActive || var3.previewPanActive || var3.marqueeSelectActive)) {
                        return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921925, 6) != 0);
                     } else if (var6 != 0 && var25 == 0 && var24 == 0) {
                        return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(149742172, 25) != 0);
                     } else {
                        EditorTool var27 = this.findHoveredToolbarTool(var3, var8, var10);
                        String var28 = this.findHoveredHandle(var3, var8, var10);
                        HoverElement var29 = var28 != null
                           ? this.findFirstByTargetId(var3, var3.selectedElementId)
                           : this.findHoveredElement(var3, var8, var10);
                        String var22 = var29 == null ? null : this.targetIdOf(var29);
                        if (var27 != null) {
                           this.finishLayersReorderDrag(var1, var3, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-497284372, 9) != 0));
                           this.setActiveTool(var1, var3, var27);
                           return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(395015526, 12) != 0);
                        } else if (var2 == ClickType.LEFT && this.handleEditorNavbarClick(var1, var3, var8, var10)) {
                           EditorSession var30 = (EditorSession)this.editorSessions.get(var1.getUniqueId());
                           if (var30 == var3) {
                              this.updatePageInfoReadout(var1, var3);
                              this.updateSidebarTabVisualState(
                                 var1,
                                 var3,
                                 (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 15) != 0),
                                 (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1235852936, 24) != 0)
                              );
                           }

                           return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 0) != 0);
                        } else if (var2 == ClickType.LEFT && var12 && this.handleSidebarClick(var1, var3, var8, var10)) {
                           return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(496782209, 30) != 0);
                        } else if (var2 == ClickType.LEFT && this.isInsideEditorChromeBlock(var3, var8, var10)) {
                           this.finishLayersReorderDrag(var1, var3, (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(197505715, 11) != 0));
                           return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(949420603, 7) != 0);
                        } else if (this.isCursorLikeTool(var16)) {
                           boolean var23 = this.handleCursorToolClick(var1, var3, var22, var7, var8, var10);
                           if (var3.activeTool == EditorTool.ALIGN) {
                              this.updateAlignToolbarOverlay(var1, var3, var8, var10);
                           }

                           return var23;
                        } else if (var16 == EditorTool.COMMANDS) {
                           return this.handleCommandsToolClick(var1, var3, var22, var7, var8, var10);
                        } else if (var16 == EditorTool.SCALE) {
                           return this.handleScaleToolClick(var1, var3, var28, var22, (var6 != 0), var7, var4);
                        } else if (var3.activeTool == EditorTool.COLORCHANGER) {
                           return this.handleBucketFillToolClick(var1, var3, var2, var22);
                        } else if (var3.activeTool == EditorTool.COLOR_PICKER) {
                           return this.handleEyedropperToolClick(var1, var3, var2, var22);
                        } else if (var3.activeTool == EditorTool.TEXT) {
                           return this.handleTextToolClick(var1, var3, var2, var22);
                        } else {
                           return var3.activeTool == EditorTool.ZOOM
                              && this.handleZoomToolClick(var1, var3, var2, var8, var10);
                        }
                     }
                  }
               }
            }
         } else {
            return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 24) != 0);
         }
      }
   }

   public boolean handleScaleToolClick(Player var1, EditorSession var2, String var3, String var4, boolean var5, boolean var6, long var7) {
      if ((1463692635 ^ 1463692635) != 0) {
         throw new IllegalStateException();
      } else if (var2.activeHandle != null
         && var5
         && !Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("/rMLmVIz9pTX2yEUhU/QMyI2dKWW6n8aVTkkEd1IgYePQHfG").equals(var2.activeHandle)) {
         return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-836386674, 5) != 0);
      } else if (var3 != null && var2.selectedElementId != null) {
         this.toggleScalingHandle(var1, var2, var3, var7);
         return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 13) != 0);
      } else if (var2.activeHandle != null
         && !Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("/rMLmVIz9pTX2yEUhU/QMyI2dKWW6n8aVTkkEd1IgYePQHfG").equals(var2.activeHandle)) {
         return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(1550561787, 31) != 0);
      } else if (var4 != null) {
         if (var6) {
            this.toggleSelectedTarget(var2, var4);
         } else {
            if (var4.equals(var2.selectedElementId) && var2.additionalSelectedElementIds.isEmpty()) {
               this.clearSelectionState(var1, var2);
               return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 5) != 0);
            }

            var2.selectedElementId = var4;
            var2.additionalSelectedElementIds.clear();
         }

         this.normalizeSelectionState(var2);
         var2.activeHandle = null;
         var2.resizeAnchorValid = (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 16) != 0);
         var2.handlesCollapsed = (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 24) != 0);
         this.updateHoveredHighlight(var1, var2, var2.hoveredElementId, null);
         var2.hoveredElementId = null;
         this.clearEditorOverlaysOnly(var1, var2);
         if (var2.selectedElementId != null) {
            this.showSelectionOverlay(var1, var2);
         }

         if (var2.selectedElementId != null || !var2.additionalSelectedElementIds.isEmpty()) {
            this.playEditorSfx(
               var1, var2, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("mPZpVD1t5Jd8vVK5K3HsofhxGMYjsOPU5yFlz1JJ1Mb705vaxw5yNCvdCJXMkdTQlYORRf/rbPF0rQ==")
            );
         }

         this.refreshAfterSelectionChange(var1, var2);
         return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(395015526, 12) != 0);
      } else if (var2.selectedElementId == null && var2.additionalSelectedElementIds.isEmpty()) {
         return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 0) != 0);
      } else {
         this.clearSelectionState(var1, var2);
         return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-588533253, 23) != 0);
      }
   }

   public boolean handleCursorToolClick(Player var1, EditorSession var2, String var3, boolean var4, double var5, double var7) {
      if ((-454209648 * -454209648 - -454209648 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var3 == null) {
         if (var4 && this.isInsidePreviewArea(var2, var5, var7)) {
            this.startMarqueeSelection(var2, var5, var7);
            return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1206789692, 18) != 0);
         } else if (var2.selectedElementId == null && var2.additionalSelectedElementIds.isEmpty()) {
            this.stopCursorToolDrag(var2);
            if (this.isInsidePreviewArea(var2, var5, var7)) {
               this.startPreviewPan(var2, var5, var7);
               return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-597446149, 19) != 0);
            } else {
               return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 25) != 0);
            }
         } else if (var2.activeTool == EditorTool.ALIGN && !var4 && Xqedii_38o2zes.Xqedii_316oplq() <= var2.alignToolbarClickGuardUntilTick) {
            return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 29) != 0);
         } else {
            this.clearSelectionState(var1, var2);
            this.stopCursorToolDrag(var2);
            if (this.isInsidePreviewArea(var2, var5, var7)) {
               this.startPreviewPan(var2, var5, var7);
            }

            return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 29) != 0);
         }
      } else if (!var4 && this.isSelectedTarget(var2, var3)) {
         if (!var2.moveDragActive) {
            this.showSelectionOverlay(var1, var2);
            this.startMoveDrag(var2, var5, var7);
         }

         return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1266752924, 14) != 0);
      } else {
         if (var4) {
            this.toggleSelectedTarget(var2, var3);
            this.stopCursorToolDrag(var2);
         } else {
            var2.selectedElementId = var3;
            var2.additionalSelectedElementIds.clear();
         }

         this.normalizeSelectionState(var2);
         var2.activeHandle = null;
         var2.resizeAnchorValid = (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 17) != 0);
         var2.handlesCollapsed = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-489072251, 18) != 0);
         this.updateHoveredHighlight(var1, var2, var2.hoveredElementId, null);
         var2.hoveredElementId = null;
         this.clearEditorOverlaysOnly(var1, var2);
         if (var2.selectedElementId != null) {
            this.showSelectionOverlay(var1, var2);
            if (!var4) {
               this.startMoveDrag(var2, var5, var7);
            }
         }

         if (var2.selectedElementId != null || !var2.additionalSelectedElementIds.isEmpty()) {
            this.playEditorSfx(
               var1, var2, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("mPZpVD1t5Jd8vVK5K3HsofhxGMYjsOPU5yFlz1JJ1Mb705vaxw5yNCvdCJXMkdTQlYORRf/rbPF0rQ==")
            );
         }

         this.refreshAfterSelectionChange(var1, var2);
         return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596919813, 11) != 0);
      }
   }

   public void normalizePreviewForTransform(Player var1, EditorSession var2) {
      if (((743479050 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.activeTool == EditorTool.ANIMATION) {
         EditorTool var3 = this.resolveTimelineSelectedTool(var2);
         if (var3 == EditorTool.CURSOR || var3 == EditorTool.SCALE) {
            if (!this.isTimelineKeyframeSelected(var2) || var2.animationTimelineSelectedTick <= 0) {
               String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-2048729767, 10)];
               var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(949420731, 7)] = var2.animationPreviewTargetId;
               String var4 = this.firstNonBlank(var10001);
               if (!var4.isBlank()) {
                  EditorRect var5 = var2.animationPreviewAppliedBounds;
                  EditorRect var6;
                  if (var5 != null) {
                     var6 = new EditorRect(var5.x, var5.y, var5.width, var5.height);
                  } else {
                     double var7 = var2.animationPreviewOffsetX;
                     double var9 = var2.animationPreviewOffsetY;
                     if (Math.abs(var7) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(8720618910603121525L, 57))
                        && Math.abs(var9) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(-508831240331241573L, 18))) {
                        return;
                     }

                     EditorRect var11 = this.getTargetBounds(var2, var4);
                     if (var11 == null) {
                        return;
                     }

                     var6 = new EditorRect(var11.x + var7, var11.y + var9, var11.width, var11.height);
                  }

                  this.clearTimelinePreviewOffset(var1, var2);
                  EditorRect var12 = this.getTargetBounds(var2, var4);
                  if (var12 != null) {
                     if (!this.sameRect(var12, var6)) {
                        this.applyBoundsToTarget(var2, var4, var6);
                        this.rerenderEditableSelection(var1, var2);
                        if (this.isSelectedTarget(var2, var4)) {
                           this.renderSelectionOverlay(var1, var2);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public boolean handleCommandsToolClick(Player var1, EditorSession var2, String var3, boolean var4, double var5, double var7) {
      if ((-1632829875 * -1632829875 + -1632829875 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var2 == null) {
         return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 21) != 0);
      } else if (var3 != null && !var3.isBlank()) {
         if (var4) {
            this.toggleSelectedTarget(var2, var3);
         } else {
            var2.selectedElementId = var3;
            var2.additionalSelectedElementIds.clear();
         }

         this.normalizeSelectionState(var2);
         var2.activeHandle = null;
         var2.resizeAnchorValid = (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-320709622, 1) != 0);
         var2.handlesCollapsed = (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1738658411, 6) != 0);
         this.updateHoveredHighlight(var1, var2, var2.hoveredElementId, null);
         var2.hoveredElementId = null;
         this.clearEditorOverlaysOnly(var1, var2);
         if (var2.selectedElementId != null) {
            this.showSelectionOverlay(var1, var2);
         }

         if (var2.selectedElementId != null || !var2.additionalSelectedElementIds.isEmpty()) {
            this.playEditorSfx(
               var1, var2, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("mPZpVD1t5Jd8vVK5K3HsofhxGMYjsOPU5yFlz1JJ1Mb705vaxw5yNCvdCJXMkdTQlYORRf/rbPF0rQ==")
            );
         }

         this.refreshAfterSelectionChange(var1, var2);
         return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 28) != 0);
      } else if (!this.isInsidePreviewArea(var2, var5, var7)) {
         if (var2.selectedElementId == null && var2.additionalSelectedElementIds.isEmpty()) {
            return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 26) != 0);
         } else {
            this.clearSelectionState(var1, var2);
            return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-488810107, 18) != 0);
         }
      } else {
         if (var2.selectedElementId != null || !var2.additionalSelectedElementIds.isEmpty()) {
            this.clearSelectionState(var1, var2);
         }

         this.startPreviewPan(var2, var5, var7);
         return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(149742172, 25) != 0);
      }
   }

   public void setActiveEditorTool(Player var1, EditorSession var2, EditorTool var3) {
      if ((-485176125 * (-485176125 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         this.setActiveTool(var1, var2, var3);
      }
   }

   public abstract void executeRuntimeTargetActions(Player var1, EditorSession var2, long var3, List<Map<String, Object>> var5, int var6);

   public abstract String resolveRuntimeActionValue(Player var1, String var2);

   public abstract String normalizePageKey(String var1, String var2);

   public abstract boolean isInternalEditorPageName(String var1);

   public abstract boolean isEditorMenuSession(EditorSession var1);

   public abstract boolean handleEditorMenuClick(Player var1, EditorSession var2, ClickType var3);

   public abstract void rerenderEditableContentForTargets(Player var1, EditorSession var2, List<String> var3, boolean var4);

   public abstract void rerenderEditableContentForTargets(Player var1, EditorSession var2, List<String> var3, boolean var4, boolean var5);

   public abstract boolean openGui(Player var1, String var2, boolean var3);

   public abstract boolean openGui(Player var1, String var2, boolean var3, boolean var4);

   public abstract boolean openGui(Player var1, String var2, boolean var3, boolean var4, boolean var5);

   public abstract boolean openGui(Player var1, String var2, boolean var3, boolean var4, boolean var5, boolean var6);

   public abstract double resolveSessionPageWidth(EditorSession var1);

   public abstract double resolveSessionPageHeight(EditorSession var1);

   public abstract boolean handleCursorPageDropdownClick(Player var1, EditorSession var2, ClickType var3, double var4, double var6);

   public abstract boolean handleCursorLayersDropdownClick(Player var1, EditorSession var2, ClickType var3, double var4, double var6);

   public abstract boolean handleActivePagesClick(Player var1, EditorSession var2, ClickType var3, double var4, double var6);

   public abstract boolean closeGui(Player var1, String var2);

   public abstract void closeGui(Player var1);

   public abstract String buildSelectionCreatedElementId(EditorSession var1, String var2);

   public abstract void renderPage(Player var1, EditorSession var2);

   public abstract void renderPage(Player var1, EditorSession var2, boolean var3);

   public abstract void pruneEditorLayerStateTargets(EditorSession var1);

   public abstract void clearSelectionForStructureEdit(Player var1, EditorSession var2);

   public abstract void rememberOpenedEditorPage(UUID var1, String var2);

   public int resolveMaxPageElements() {
      if (((-781159057 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return this.plugin == null
            ? Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596315653, 12)
            : Math.max(
               Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 25),
               this.plugin
                  .getConfig()
                  .getInt(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                        "0XpBpGPIhDCYuCogyXYfN6DygKudE/dnVzvFifdHw0kaCd/98dbnFbiq1sFJ46t/69y64uxXQa3RcArSOaLFv3UtMSylU9kUeUU2kg=="
                     ),
                     Xqedii_p92kfr8.Xqedii_vjb2772t99mu(904715836, 31)
                  )
            );
      }
   }

   public void ensureGuiFoldersAndExample() {
      if (((369723502 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         if (!this.plugin.getDataFolder().exists()) {
            this.plugin.getDataFolder().mkdirs();
         }

         File var1 = new File(this.plugin.getDataFolder(), Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("FDKQxaAum3yBA9m426E828YXH62OuSVo++u8WpvJRLjdwTjw3WggJP0AGEo="));
         if (!var1.exists()) {
            var1.mkdirs();
         }

         File var2 = new File(
            this.plugin.getDataFolder(), Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("zpzmjbRCq6VqCHJx7JOyiF+CFdzRUyin/A+ywR40S1z0uQAE9UHdrDAQEg+LGiQ706+lbwaV9kw=")
         );
         if (!var2.exists()) {
            var2.mkdirs();
         }

         File var3 = new File(
            this.plugin.getDataFolder(),
            Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("jUSeu3mUZa28iV4/EVOo0BvYDQmhA6MXTygu9T0cjqSL24WqOAJeYtTMf3FJoUb5OHZX+PXE76Kgil7z1G60gZPG")
         );
         if (!var3.exists()) {
            var3.mkdirs();
         }

         File var4 = new File(
            this.plugin.getDataFolder(),
            Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("F+4Okk9cZeLoWwPGHZyAt2Id7f3ck9GrJxs4dL6fYx/z3K+8iaQvmcYS+qS/KGWGYoIzr1tXdWqO/B6o")
         );
         if (!var4.exists()) {
            var4.mkdirs();
         }

         this.migrateLegacyContentFolder(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("jWIRjnqRSaxxgKQu1OMbuUaQxIR7NKZW96oTJr3eq26cw5Jqyc0="), var2);
         this.migrateLegacyContentFolder(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("2BkKjie6SDBoMVBIx6R4uUAh1F0ocTKVkLi8VOx2HK2GB43NgUhB5+se6HjcCtVJ"), var3);
         this.migrateLegacyContentFolder(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3oUxz0VBB1bUMbpfUqmOgm1gAZdKyZnJvaC+RkS0yjQvNuH36AmEwakv"), var4);
         File var5 = new File(this.plugin.getDataFolder(), Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Ua2/AdNWAongbeRxg3xlfh951B7b5oC8ZzjwDSdr5fNHy43H"));
         if (var5.exists() && var5.isDirectory()) {
            File[] var6 = var5.listFiles((var0, var1x) -> {
               if ((1171781060 * (1171781060 + 1) & 1) != 0) {
                  throw new IllegalStateException();
               } else {
                  return var1x.toLowerCase().endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("TVlrjWusmMjoiVgx7hMuh6TAT5pwNJh02JSh/buX7iviaExM"));
               }
            });
            if (var6 != null) {
               File[] var7 = var6;
               int var8 = var6.length;

               for (int var9 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1701189515, 28); var9 < var8; var9++) {
                  File var10 = var7[var9];
                  File var11 = new File(var2, var10.getName());
                  if (!var11.exists()) {
                     var10.renameTo(var11);
                  }
               }
            }
         }

         this.saveDefaultResource(
            Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
               "KMpYymJsxBkqdV4hpbTu/F/u0OlWtBmdIoA1wbSAzj0UBoto32dMLp1tD76Y+FnKDo9F8CUiJGQ4ML74mxhQJS8eDsy6iqj2X+G3TdoZVxP8CpdBwfCFGA=="
            )
         );
         this.saveDefaultResource(
            Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
               "6guoxXb5Hi1ljPkP0ChTmCpnafiBcFpGuD0gEH8kg8nzeXP5kfMHhtoD5NdoHeTePLGal8K2qT91/YqjNgrmIzvvkn7dIMuuJ/DaEwjJyp/CT3CYX9w="
            )
         );
         this.saveDefaultResource(
            Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("HSNTTiJCxPEzq/C5gg+jHn+YI63tr0yj4JfKaM37s6yWWxdJwOLBcAGKF4RiL2/2Tvs/y2f6gLvn1+/+8USZYqHGMEinKVzeYmv7pk2oMec=")
         );
         this.saveDefaultResource(
            Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
               "4gdWdcjXV2IGVMC+ck4vJS72MgoZnTpmCYYRDCE/thjhuSsqNFxSFQF+sy29IdNXwK0jvCmYkeEjZEe3VqXwlkdEDwcFDCSG1eEkNyBTDHFLHlMrzBGGOA=="
            )
         );
         boolean var12 = this.plugin
            .getConfig()
            .getBoolean(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8h5x9vR51xeMbBHw0gzvnFH+YKgy+Xlge5uHeoyAYEniNQIzX75FrWxDqJguHew/7t52oCquwE5VcQfahaxG4Zs0DDFUNNaia0ZTYg=="),
               (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 25) != 0)
            );
         File var13 = new File(
            this.plugin.getDataFolder(),
            Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("xriz17d02udUjtEHWAwsBChT8cJRtMKxAC1Ywcg43gY+sNl3+aNB0XQ/RoEd6kKgb2zQ0CdlWWkC3FrZQQGlmBCbN9ZTWA==")
         );
         File var14 = new File(
            this.plugin.getDataFolder(),
            Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Q3DKx+EoUVAozMVYxI9rypScYLsf7xR5Bzk6ucsbghE0qR3lP3gOfrKvnz5k62VOsVu2PcNlefqzdmqquDMVKWK/J+E6nq7eDQVeiJyV0FY=")
         );
         this.Xqedii_3is3bcq(var12, var2, var3, var13, var14);
         File var15 = var12 ? var2 : var13;
         File var16 = var12 ? var3 : var14;
         this.migrateEditorLayerComponent(
            new File(var16, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("MAq80IhY8AQnBwUhEmkDtIFUYjOOdqI/7qPrJx07wR4mI2JDcy+C3s7nrO76570/uUSDrB3bQ2+KEyDV"))
         );
         this.migrateEditorValueComponent(
            new File(var16, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("SE1Wsz6PVh31Q38X2qm3MmJLPW5oHH6iwnnBJqRs17jeW+1PVBgdQE9VT7T2pbS9X7yCMCEFSkS9vQ5V"))
         );
         this.migrateNavbarItemComponent(
            new File(
               var16, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("KZh71lswNw0iPZ/Ldm6bY/IgjqvWo4Pl7KNBjAG0AO82w26hbTZThEx6Ix1cYYJRvrqfBiuVrRKTA8yWyU+ggQVnh5QHXnJR")
            )
         );
         this.migrateDropdownValueComponent(
            new File(
               var16,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("0288nadxq+MiVUHe/xFd7XC1YqHpbaJ59oWpy4k/dPh42eapR4JuQDlHgFz9BcIIqwbObED2lN3MvZNxIwXXCO0euUbchw2fYfy6VrBD")
            )
         );
         this.migrateEditorSavePopupComponent(
            new File(
               var16, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("B9pgKAoGi+/rCUgeH3vnCcf9WpTtcGjd6Asc3ej3tJcJM+dfDXzMJZgVHh+v3wMWlzrBNc7yDP/ycz0JUYO8ztkplsaBqw==")
            )
         );
         this.migrateManageFilePopupComponent(
            new File(
               var16,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "Ss5ul0rM9dFTYQDdLM1KI/Kf0w6XOy91sTn7G2EaXvp+zZMjKnF6lnkwJfTJAQyrHffChgz02eypBxydv+Wtco7pv9+FLfsHkfaw8DutbnQkVFfY"
               )
            )
         );
         this.migratePopupFileElementComponent(
            new File(
               var16,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "EaFRt/s6zVXSV+pa0iaDyhemkXwbAJCxb0KBm4377Aurez/OBEPatC6DVaoCJc4RBOAmKl9/j5xKLhdPs8UOPcx+n9qUtEQ9Ekqn3mb8VLXoEazXdUs="
               )
            )
         );
         this.migrateEditorPageFile(new File(var15, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("GrPojtvpZfkfQBXurQaY+nEWuXYLmVuOIrgLC202p9gu1S2RgdSHE7PUYRK99I2Y")));
         this.migrateEditorMenuPageFile(
            new File(var15, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ANZ8PGJaSU0+4cHluAs+ZP7isMGS7wfI4EehSUDA36nvuqxZsbudXX0hGGMA/VuiBdyZ2bHddu/faQ=="))
         );
         this.sanitizeYamlTabs(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("zpzmjbRCq6VqCHJx7JOyiF+CFdzRUyin/A+ywR40S1z0uQAE9UHdrDAQEg+LGiQ706+lbwaV9kw="));
         this.sanitizeYamlTabs(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("jUSeu3mUZa28iV4/EVOo0BvYDQmhA6MXTygu9T0cjqSL24WqOAJeYtTMf3FJoUb5OHZX+PXE76Kgil7z1G60gZPG"));
         this.sanitizeYamlTabs(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("F+4Okk9cZeLoWwPGHZyAt2Id7f3ck9GrJxs4dL6fYx/z3K+8iaQvmcYS+qS/KGWGYoIzr1tXdWqO/B6o"));
         if (!var12) {
            this.sanitizeYamlTabs(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("xriz17d02udUjtEHWAwsBChT8cJRtMKxAC1Ywcg43gY+sNl3+aNB0XQ/RoEd6kKgb2zQ0CdlWWkC3FrZQQGlmBCbN9ZTWA==")
            );
            this.sanitizeYamlTabs(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "Q3DKx+EoUVAozMVYxI9rypScYLsf7xR5Bzk6ucsbghE0qR3lP3gOfrKvnz5k62VOsVu2PcNlefqzdmqquDMVKWK/J+E6nq7eDQVeiJyV0FY="
               )
            );
         }
      }
   }

   public void Xqedii_3is3bcq(boolean var1, File var2, File var3, File var4, File var5) {
      if (((-1858280550 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1) {
         this.Xqedii_chapbnd(var4, Xqedii_3uxgwyg.Xqedii_u593x1w);
         this.Xqedii_chapbnd(var5, Xqedii_3uxgwyg.Xqedii_33y2wvi);
         this.Xqedii_tchlw9h(var4);
         this.Xqedii_tchlw9h(var5);
         this.Xqedii_tchlw9h(
            new File(
               this.plugin.getDataFolder(),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("SPg3VzA0+V51PmWrqTwBzjTWI/UMtg7K7vLXqGJPtkt5CZVAjt9dwjDfheegXfJ7rHSJ5OO++/q8iQ==")
            )
         );
         String[] var11 = Xqedii_3uxgwyg.Xqedii_u593x1w;
         int var14 = var11.length;

         for (int var17 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1729290327, 4); var17 < var14; var17++) {
            String var20 = var11[var17];
            this.saveDefaultResource(Xqedii_2lk3pcxewh73c(var20));
         }

         var11 = Xqedii_3uxgwyg.Xqedii_33y2wvi;
         var14 = var11.length;

         for (int var18 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 25); var18 < var14; var18++) {
            String var21 = var11[var18];
            this.saveDefaultResource(Xqedii_1men99pr0i32m(var21));
         }
      } else {
         this.Xqedii_chapbnd(var2, Xqedii_3uxgwyg.Xqedii_u593x1w);
         this.Xqedii_chapbnd(var3, Xqedii_3uxgwyg.Xqedii_33y2wvi);
         if (!var4.exists()) {
            var4.mkdirs();
         }

         if (!var5.exists()) {
            var5.mkdirs();
         }

         String[] var6 = Xqedii_3uxgwyg.Xqedii_u593x1w;
         int var7 = var6.length;

         for (int var8 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1296888891, 27); var8 < var7; var8++) {
            String var9 = var6[var8];
            this.Xqedii_2tk7swv(Xqedii_1qv8zshh99qi0(var9), new File(var4, var9));
         }

         var6 = Xqedii_3uxgwyg.Xqedii_33y2wvi;
         var7 = var6.length;

         for (int var16 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1266769308, 14); var16 < var7; var16++) {
            String var19 = var6[var16];
            this.Xqedii_2tk7swv(Xqedii_143ubywj47rtd(var19), new File(var5, var19));
         }
      }
   }

   public void Xqedii_chapbnd(File var1, String[] var2) {
      if (((-1767547676 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var1.exists() && var1.isDirectory()) {
         String[] var3 = var2;
         int var4 = var2.length;

         for (int var5 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 24); var5 < var4; var5++) {
            String var6 = var3[var5];
            if (var6 != null && !var6.isBlank()) {
               File var7 = new File(var1, var6);
               if (var7.exists() && var7.isFile()) {
                  if (!var7.delete()) {
                     this.plugin.getLogger().warning(Xqedii_1leeh0c7nh5bk(var7.getAbsolutePath()));
                  } else {
                     this.invalidateYamlFileCache(var7);
                  }
               }
            }
         }
      }
   }

   public void Xqedii_tchlw9h(File var1) {
      if ((203081476 * 203081476 + 203081476 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.exists() && var1.isDirectory()) {
         String[] var2 = var1.list();
         if (var2 == null || var2.length == 0) {
            var1.delete();
         }
      }
   }

   public void Xqedii_2tk7swv(String var1, File var2) {
      if ((517781121 ^ 517781121) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         File var3 = var2.getParentFile();
         if (var3 != null && !var3.exists()) {
            var3.mkdirs();
         }

         try {
            try (InputStream var4 = this.plugin.getResource(var1)) {
               if (var4 != null) {
                  Path var10001 = var2.toPath();
                  CopyOption[] var10002 = new CopyOption[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 31)];
                  var10002[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(183296604, 25)] = StandardCopyOption.REPLACE_EXISTING;
                  Files.copy(var4, var10001, var10002);
                  this.invalidateYamlFileCache(var2);
                  return;
               }

               this.plugin.getLogger().warning(Xqedii_1igsqaqi2x0bs(var1));
            }
         } catch (IOException var9) {
            String var5 = var9 instanceof AccessDeniedException ? Xqedii_x4atuxi96zf8(var2.getAbsolutePath()) : var9.getMessage();
            this.plugin.getLogger().warning(Xqedii_255vzb852e860(var1, var5));
         }
      }
   }

   public void migrateLegacyContentFolder(String var1, File var2) {
      if ((-28243476 * (-28243476 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isBlank() && var2 != null) {
         File var3 = new File(this.plugin.getDataFolder(), var1);
         if (var3.exists() && var3.isDirectory()) {
            if (!var2.exists()) {
               var2.mkdirs();
            }

            File[] var4 = var3.listFiles((var0, var1x) -> {
               if ((1544755497 ^ 1544755497) != 0) {
                  throw new IllegalStateException();
               } else {
                  return var1x.toLowerCase(Locale.ROOT).endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("TVlrjWusmMjoiVgx7hMuh6TAT5pwNJh02JSh/buX7iviaExM"));
               }
            });
            if (var4 != null && var4.length != 0) {
               File[] var5 = var4;
               int var6 = var4.length;

               for (int var7 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(316692327, 12); var7 < var6; var7++) {
                  File var8 = var5[var7];
                  File var9 = new File(var2, var8.getName());
                  if (!var9.exists() && !var8.renameTo(var9)) {
                     try {
                        Files.copy(var8.toPath(), var9.toPath());
                     } catch (IOException var11) {
                        this.plugin.getLogger().warning(Xqedii_2ce8u6gzyemlc(var8.getName(), var1, var2.getName(), var11.getMessage()));
                     }
                  }
               }
            }
         }
      }
   }

   public void migrateEditorLayerComponent(File var1) {
      if (((-537640007 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = var2.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("GSFWSScM7oA9kwTEElnWlLe59xD3HXnTXLJ0jcbMWRHyibRTMkehjanr/5NLQeKO4gRNsw=="),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("NeR2mA2jBkap2EKnHnri7GBn9JS5ditoIunihEM4qJQuAMYqZhE5+LESWsB6wBDUYuZHDdVBvA8=")
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("4r8YIzjP6Id6J49rj7pLV7qcpHxob4wVSfMc22MMgBP+a/xkbx76yd9LegOo0XEZ"),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("uL3Y4ki4m+JUwmwBSKdjG5wRXvj9pE5Eyah2QGzGboAen3eVyCOcGOTXx7lccFtkD4Ytbw==")
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8CpGVEyMTaf/SU+E8L2o+SLyJzHSC18u/Mo78mdv//t1wXbsnHn5ZubiwnKYa77k"),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("tkzK1j3d3RaHsuw0ptbCqcW2YlYtuP801Z/xiU0jXI/bIGA0dIyw4nrIsxyDJB9WIdKfYg==")
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("pxVy2Wx6YXL3dghIlIDv+AglXtLTzobQEEpfnwgLMwNPYj3qMuiLzrgwH+306eUUM8nk63zE"),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("oqzsgih9WGYK8T3u6z4xf1fVB7ZCofzzPRIMy7hPGo9MaL631F0wOwvvY7jYah41NKCiE47nEA5d2g==")
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("mTl8clo91eFive6MeCcHfhCRLLiPMm1fAfbh0XJTf4blwmfInoXcxpHCjVOvSCltsl95mQEb"),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("w8hjpdwKysHhztmvP5GLy7QWlCk7B91+AHuit8hH7NNsJmwWn4gTrbn1t8qcW4PlWrOCL40UpD7Oiw==")
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("BEma18vm8567WRemFZpN00QBu9U+zEC5LrnSarSO9YuxoG8TYkDyUvlVYAwsadum"),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("6vDhzX3vXGZhGD8Tc8IKrj0KIvKrGARxjnC736oVlhyZ+gN4O/p6YQQ3hBL83/ekgMCQhA==")
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("AVQRNaSBy08TgaYl97YigmaXoLo0McFPHKQ4sLXXF7MTd7atzTWA2TI5Sgz+WwdeXS0pwkrfziicxE7Qlo0hNPQL"),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("IOS1UaqgHlHqT9Yi32XdrJUluwlDFsiLfsLY210OXCPITFR0DJ7+LydXNtMfHJxbjm31LVBydc+GaJIYK/zesLoTkrq84g==")
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("m8yLcZmQz1Y1aBK3SDT5nneX8bznC6p36Mpp3wemPAhHFfuX0evqk9ndI013TShqlfREmRMeBvNIVDAf30yUqwNt1VM="),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8LezWE+CSMlmTUzRUNXZn02F52kISTC0joyHQ+LEwil+LDZ6pBrDwOaeAbXSxcGIrBwuSQEvnerEPZYkdKfhPV3+2TSF1/eq")
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("hJAMl38uk5UAYefzEstHimTqVHwmkQSzngcNgJtRg6RnlOf62tp+rY/B"),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("oIDioyUbWDwG8iTM/AGOgXn4HVJTu+AHIEVNig+ugFoHoWjMfPkmu9fF9CVaYZKyo8oLlg4ZhDNpImK7a0LR/g==")
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Q16dkRMimGqHSJp4cCGiLyeOgF1vW0qSV48iSvdgJya1hh17M/HQTbAy"),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("T0NGNZRmOt8isnK7QAydSZ5XyvloduSUJfbIZUk0I1cxAuPI+/+qBYdOtrylnP4sc51ECK4KMWKAp50rSfLpQw==")
            );
            if (!var3.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("dgiyx8Spz4kN/k88hGxTD0U1OHqy2jOs0vmwPkR8Wd1FweGkiw1uvJaCsjE="))) {
               if (var3.contains(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("YIdSrJSUKFArsFbqeircoVodGhzwsXzCCUN/3rVrvueAkiExCJh66uGWbPxwEUCh91fOCxLnIx7jZqYCECu/qBp9")
               )) {
                  var3 = var3.replace(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("YIdSrJSUKFArsFbqeircoVodGhzwsXzCCUN/3rVrvueAkiExCJh66uGWbPxwEUCh91fOCxLnIx7jZqYCECu/qBp9"),
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                        "r3aU4iIAh0xqnATeokjM8e7XNBYrTcIMW4ukgch7Ngu5QOHdc9wdff2kvmHxs8UTO5akC9w55MA/VX1mecKtA6qr25f8Z19gk+gk8V63EogK7Xr5rYaOEoXq"
                     )
                  );
               } else if (var3.contains(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("NnYEXlYVMyVAQKI9GnHa+iRiNTGMitUsS8x6eALVqYEqrjhCXuxmS0+SFJHd+nxFjpi6q4eaB+eNOZhewEE=")
               )) {
                  var3 = var3.replace(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("NnYEXlYVMyVAQKI9GnHa+iRiNTGMitUsS8x6eALVqYEqrjhCXuxmS0+SFJHd+nxFjpi6q4eaB+eNOZhewEE="),
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                        "foNavNKwLwDiODOoOAZU6JYHhIR3TOjfSaJFiAmR2YjmvhR+tZKAmObNbYS4m7+jUkxJdUKi5eHKOO/4uK3qYeDgQ+cC0auJALmlkbqHgRhPZQKx"
                     )
                  );
               }
            }

            if (!var3.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("UHNCbr6C0qGC9D8rzZxRpvn1uW8HkfbsxXyruet8ooYDOHOKMLp0IibEcdrlMVvp"))) {
               if (var3.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("/D12N+YQGpraEFUlht9jcAZIPTvfWRZ6wHIi3gQ7jQTlfbrMjWuqbayepk60gZxNrSoGmQ=="))) {
                  var3 = var3.replace(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("/D12N+YQGpraEFUlht9jcAZIPTvfWRZ6wHIi3gQ7jQTlfbrMjWuqbayepk60gZxNrSoGmQ=="),
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                        "kX2P83rxha8o+qCQIuvk1DGWe3NrDXmZa+UurhUh+khfudAL3ZbNguBiL9/rHhJAoI9GpRU3zezsOoDsTnDibhvTTBd0QYoR/yrSEjrbuEMbx/sAxRYbMfvd/ROhZns2gpmsWbIr+2aRkwYmTWgiwHM++OZlZgZmZnGnpg=="
                     )
                  );
               } else if (var3.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("j7AQaxMkq9Lh3d8r5jGw1TG/bpUyaJLSGfFBLHoUHidg1WYLhCnA3aTPhKkCRtesF+4="))) {
                  var3 = var3.replace(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("j7AQaxMkq9Lh3d8r5jGw1TG/bpUyaJLSGfFBLHoUHidg1WYLhCnA3aTPhKkCRtesF+4="),
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                        "+Yi8BKwfetmRI9Oe76fgL24SIezuCRdDkpjNvdZHs8L07oJgLFQmiZf+d3wHuyYyr3ru/SbhnZvDiWPsR0K7409PhdKEZUJb+qczTTmKlbdiUUl9mpdZWcjuYymRm+cabIe6Av1+X/QpXxntgOyJ3FHLI/ovcw=="
                     )
                  );
               }
            }

            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var4) {
            this.plugin.getLogger().warning(Xqedii_19h4knjbf5kuf(var4.getMessage()));
         }
      }
   }

   public void migrateEditorValueComponent(File var1) {
      if (((1473872549 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = var2;
            if (!var2.contains(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("s1j76k/AmJw9RMgf/dUDFerXJ2YAD33fh0Ko42tSs9mg+hd11FmVVy+P3CPYUk+cPMMa7X5KgQsAfOOinQI7fsp2GOoFtE2808g=")
            )) {
               var3 = var2.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "3WTfeLBwB53aA8xRFvUWcZtIEuxsJlQF3EapMj/VV+J8lu+/HbBsJW9ONzZv/0M7syDL0JHAFxRq1NF+MduXpE9ElLb3YJGIwu4e3uqS"
                  ),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "kJKtnjmOiMJWf0BTcNm66Ks3y5Ki+irMDwM90A6wdbJhr8uJ7zu+cJ8Cp0L6grAVNDWWXknmysqpEsWu0OeGghcEmtUyPmkdfJ9LHYhOjD9A2FIzpegIroJzc0XXW/djh1V7TzRkeSOGQWcPD1J6F4LqqTrOaXhpW4jPR942hA7oOol5+ht22amxYZTycPmLTb2mNKSmBnzJeheMxUhWVxEV/scGV3I98GBHhovZJ+tLDq/HUe3fi1GIZ+DQ6Ucauw8cDuau1MDu9qeSyahKsmV2ZuPdaLLe1cJGOdwhFg5Ha+tP58q0wDB7KM442G7uXGgXPSyKZf9FQtH2X+HUfdgZuHHrYhpG8r1CdVDU20ctw3ogaOi1ZqWOcaliOivhEEwlO+4OLvnQdrXpbGH9IJ+lUUIhvE8KzjZ5K5eLo1B/5ohfOMxPrMXwOR/GEJGjrSlEoX/ElzqE8x19u0HT8qhtcJiI2dFzAPFN2SwrJwb9iJJ/jQv9vArobtsuI+e+UKpDWpY7lwgDKs33f4avjlF128Q0aJLkg4iee7aXp69Zw6hAlIlH8cWotK0lfYhv/5KWq010ngNAPPingGZp3XKEsc11b3UGrbdcQFDkhTHDy2UeUOKCjS+ON8Iw9mithzkHzPYEgnwTp4LhACOJzcjeCrZXHY7vTaCIMmtb368rXAeFyJonfdwKBkdxq6EGAX4S+g=="
                  )
               );
               var3 = var3.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("D5T1fNj5HsOca5pzYYVQj1IvW6i0tf4kxwY8u6v+OCukM0txp95ACEK1F7zLXRj+BIaLvB+4TU0fmEF4ZyEgitiYk0j7unr1BV4="),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "WXlMFHq4sIYylWFwkQl0TZ22eC57Pjl27jT93Yn23CqX0X46T7LeTKUg0Tn89snQX4HQdQgP4UykHYJifAFR9s5BGoEbCSMjlcBpCOBCFpj6ZLYbER1k1uYSjZCM2IQMhzoaTGUlRymiyNxDPEwXTgJqHh+EDtR3X05jD4zct3CKvn49N5B2MFLNdXxmc2Sbh+H53+SQ+RSv9IO6gl1x9nv+dgeOF5LVIdJOoukMiZwKlwc1P8zvCAGMebxZmMHVp6GShlEoyy0b8BeHPBx8TTHp8bAZhsqsWCPVaNMJjslts/HH3exT+ye/+IThoGCFJMlB4KCEVtj8HKPV+FD6QLoyYiuFJ8F+4gtpoWUKKRIjstZX+Am/drMNPxIjPjtxwwngzwkxSHgm76e9fCeDwwr8v9aDTisvatNxr+XkPPvf5bHDoI9VlD5+VSe1DGfNpWqzLhdZ/Cbl7lVtSg3vDuXFJ0TnVo2mqRhRUkMtv/ojyGNzt4sNDO0WOXWnZqSNIOy5wxXqqcFEMljXtKZJ95BhKmjD4hGhBzADueGAbam2QuUSUzZSfAC1VBi/ZaBE/IMtaovE8Xoi+QV8CMAbpgxg1aws2c1zfkGSwj/vQ6qZ7MKizHuuI8rcuyPxi4b0rkX90sZgDoVYnRBhGROEklXEqQ83/w=="
                  )
               );
            }

            if (!var3.contains(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("NUeyauDSCc+gKoBo4nge6OvoaMIYPYZEDzYWTo14CbDojIoLOkvORA1moHC6QX88qRprfyU7v/wy55FpQREjKqc2PYgbl6Oc")
            )) {
               var3 = var3.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "aAxYE/6F7jkaarEN5sc7ClAlznigRIjKHcqW6KZKzKZyCX3hfQ/ouQeqJU8QMqFi52WZLfyuIYSyIEXnjYVVXdx5MqRiCc97oUJroEmHgV/+uWU1e/gCqwug"
                  ),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "9QzbbVn01cU4bh3nLsLjxjvnojay3Ugmm46ijX6azUiv0FvPlTJqSRgDkfvaISTcCnyCmdbaj82AojjVJ5z1VKSxkTPqNnt/IW+DHFAN8jUMtCRQT7Eci5ATOPa8QBut+Yse29YQINTw4Jl0s5Dyu56eYcwrI89bncEvpm1WoDSB6+wuwiD1CCvg3LnP8417aZFgDuaPoHK1Qw=="
                  )
               );
               var3 = var3.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "ll4LGdcpDBl8/WmavmTzvlCxNLppY4OVqej2Ie9bTuaWnRBXh4yQcPqmVUL+cP0HG9oK3L9GMHs36BH2kZl8KP9FFm0yzCpMmd4JjzVItA7r6x+zj/w="
                  ),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "7EecCrHVqX7B8vELmrquCGARtcXUHmdFxlSKQNz9fMsAHB+ueJaX+7VbrQHTmklYA/Qu9rSxy+8gv/nuygpT6HOaiTxgSGdY7d9rAhiySnt88tA598bWuDcbecWouPNMaPmVy+qoKUvSJ80Poe8BRpTyhERflJdZg5hvzqjtMM0M5gz6ZJJCClc1KeDrnCf+j7P6yw=="
                  )
               );
            }

            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var4) {
            this.plugin.getLogger().warning(Xqedii_cfty768uujj7(var4.getMessage()));
         }
      }
   }

   public void migrateNavbarItemComponent(File var1) {
      if ((1951342878 ^ 1951342878) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = var2;
            if (!var2.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("JTACXbbSHfH4pVyP8MmDMpVqXHsOYhZl6F0kfx0ow+kOg45RjAv5zg1axZLH5KQ1"))) {
               var3 = var2.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "YVuf+lWZAP3/lRci482C2TzgX3i5W6bKPEnaxo08wGlpg0MBDTizKcNn69TWM83+5asrVhZzBftEfj+h8fI0rCkYrj3rv5OpPbpNn+x5St7KIVlNeTr1WjEw"
                  ),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "azh0FX2plKy0j3/XBpGn02NiqymAu7yce4ej9RWmDUWGZ0/bXV9/KULxIrMoyEroxx5BrKU5WxN7eM+N5iRCk4LG7o7y/tLwc1EX09jq9LoudSYkwtDPuZxBbkxnssSBFVCsJLx97PZEny1gbfzd6Yvdvxv6DzT/6VHbIgzv/GieFaBvugsB2HcM"
                  )
               );
               var3 = var3.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "bgh3+G2rOjCDXCZm86+HUlFgoeT7YoKmrY6QR4VSKZm14ulGQgHSQ9RHvssIu+usVgOwcTqRTcwcBhtN5vpOy4nPTENxdTCxWE1227ux6HS+ShhqV+Q="
                  ),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "XnYMB1HgbdpMpWcA1x65dH+TTyriz6CB9yzCuxWlBQaDW55JGCZSx8FTk78C+RsY7oFEsApIj6nr42IWM2aWosCnypoamYduYWZ1PPg/IWxFM11Pc7SRI0XzmzK2uH+JzKoI91YwQXKWbm3DW4rUJzBzqkylBJLF+1RlGC9/HnMJ6XtA"
                  )
               );
               var3 = var3.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "e65sOpxkyqx1KWLpf/JWXIxckqy+UO126a1aPQjJ+MCOmasD7PSTYKUsF5Nf1PS2S+oXRn2skvyligrdSRhi1cj2eHyEbzyFTY48D1sUI4erjttl2/WUVmGkLNA="
                  ),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "BjUuFJsh4fQM+Qy3iEyqfn9IE8eBsOha1Mk1KYXVg6rGWdBFoKYE7+SZpBdHIUSo2z4oGb/U5eSr8JJ9j/sKIXMnZ0gOXV3VQ0YyMmxpJsTFAS4edkjBx8Dh/LoP3vedbTypbYCH+mpZd5PoldrsBSPKtyy0CNm2m8i1wu8iZiUH7B9t2766qrQ3PI0="
                  )
               );
               var3 = var3.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "qs198975vvB4zXvRYsa3WvkBx4FJf5dWjaQ5gWHJET94+gE2gLBtb7Ugv2QYIKn0UDKlGWFS9jvm8Zxs9W3xuFiyixKieecZCKGpW1GeXMLQhd9qxfkhWg=="
                  ),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "RnDLyfupImYisgbVmjAIMYYjTnYIqJO/EX52cJol+M2P01Sgxe3Iyiz9QuBXUNGkwnQxoXa2BJuBDY233S3O3vzRISB2kISF3rGpm80xZm7G5i6JxFFyX9wTeABKOGhbdnF7f6xrWDPTMK16InKE85X0HCRTRdJukHXgPazO5Oem8Ox00u4="
                  )
               );
            }

            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var4) {
            this.plugin.getLogger().warning(Xqedii_2bfq6ltl0kr3d(var4.getMessage()));
         }
      }
   }

   public void migrateDropdownValueComponent(File var1) {
      if ((-1937797447 * -1937797447 - -1937797447 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = var2;
            if (!var2.contains(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("XXHtDtKsSjUBiI0xY2NLrbpBUhvN544HzE4Ec2Msp7lX2wyuFyakk9AS50yv0NEqJVBN155XrDEcO/rTMr7Rupl75YFsuw==")
            )) {
               var3 = var2.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "lJ90OYNG9eOk1A+cA7e4a9eqyOqwe8juHsZjGm9y+wf2vzkS2sq1osQe9D5nNaFLyiZTokirk7yTl4h7I1DOz4Vv/BXtQLFUkiQJZ7pQnGgHropgPF7bw/pV"
                  ),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "6cpWX1syINNDDJhdBSbYRGl02XuYrYgOQhYMTqLPsh4oyw/5cTY+PFGDPKDq4e6yatlW/DUO/4mRSSF7it62nsm9J/HSv3C1ZtEo5MEBWtbuwIWgODkfAhl8ZYdxSizDsX4a0scBGRKj0w2tml0AMx8isjP4ze707R074ngc/e/S7DfYDgCi1FJcX/CaawZeUmbf8NsFJOI3LtQzvs8AJTFdCuFTVyKSMVS4WaJXAFieEeHNgkRvi2D2XLJDzor5Ljq754sTkKVkmKux0OMZ4wCNP2cdVVsGFoGtd9eWK1c+v8Sm/G5mDnbZCCbzEEin6Ryd2pQ6/pyjMtCQaN5Cuo9Hw9/jeE16HuCxfAsftHli+fgcwEb1L7rDo0rCZrX7kD3+Xpy348WLo++zzZzJ3XB3LRlKZeSow+G2nzmx56s/2Fco7ufesKdyeSP4Dozyem5QERs3rVa+6E54+2kka+x1XN1N4cTnQJ2/sls8eniEILK4Br6eFYyLTNzDIRe/xpLdU3jir/4KZwtheJCWqygx6JHW8uhSBOfn1IAsPx+jqqxYu/6GhmvoOPQJ+3wKhfnJCBSOVJyw+VMurnZuorKgagdgEsdWk5Yr1ZZ9ri8of+/iG85LPiCl/2tPgbPk+AKiAD+4tW+4afdFKpD9XG/sZwQ="
                  )
               );
               var3 = var3.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "VgXqSoCEvp0RnW6nb9gW+sllU8Cc/7XoZ5GrRQzqMYahkzY7mazBEbt4/OOb6Jy9cImYw/3am4F+l8OTvzpzu2FIAlC8tXfgJBwwC4or6P1lepGwe7I="
                  ),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "5xCpHPILKQlHpj0cYWUzDaXwATGD6dr74Q9hSaYgZFBPvQhR+TFzAcgv8MrvzvO6vLCoDRiU6n2xCR6XA2u5z0c5Yyc72TmfKkaonUk71Z7I3nOWO1y+iKqu3eVRtC876m2vbK2Cvy2k3KfBnCt7HlKIdoIt7qlImUQnQyrkBPScH9i34CEG5xw/+wuWmetZLQifh43rBLzrnxxPM/vp2uiiquQ+c3yfgVX0pu0IQpO38uQ/BL1fa++Zpkv4MGQb+voQhxTwtw3WCicHdCF6fuCfXYlbSi1C6dX3jWjxJu/SU+hId32zU6Rk5x5fy17JcutQvp3x96C0WpxDP5sjEurTbmCMu0YVF6dT1vUtCSYF7Vg3p6HzCPKDbB5iCStQJW24vGS+JxaaMemiOQW98e1e2a2lHD50/EQeBIy5E38IhE9PxgLwg/Xy2Qq7XP0L8mj+0RoV4pDQAuOwmXl7GumoTQYw3QROho+c3dnYfom0cwSFBLbBMEtCxz1Wk/0XMa05c/eGUaoML4Fibs48polrqYwdqzwtlmWDLOvXWSKXkI0vBFMIfI8YUdfTxhs8JKL1NajFYM+jpbfo+aB8yotDRJNGc5ZowaywRWenUJA9cVvm85UTWQ=="
                  )
               );
            }

            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "GH2FRriFx5YQglbVPc5bKxbD0ciglTc5RusaBMrTAO1Fcgc/EEUZ6hu+stoEzWDuSW2QJut5bCdlZiZnbiqfwADyll24EQWjYNdF6XfK87bTLCfRQFFtGY4q3A0fkZwjKDjv57niXH0FNUNBoJzA+YURkoRI+/QHxNhUY5QoLkCknHFzH2bm+DAsadHYbuOEq6I7XlPZ2FtWMt67U2LQP0QIHB0E5oIRL02fuxIM4xEC81tFpX+DHAZadgJqnjAjpTnInOup9pv8/kG7Z0cLOFlmR51KSXPYem7mSETD5RvRVZmqnazw6/wICsuFrIjslJhu95TPsHvEtGNoDcHwTzI7aDG0WSlRAw2BLQ=="
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "kADcCCf2pSlpc5ejrkDX6yBDChByNBtOOtQhWNqnf2/A3VUaJWlosCnr5Wj75R1GFBGZZM0TQVsei51FLfjYAJtgdhbeTsvq6n9KSGvIIg9Txofalw0QhKOwpdP2w7xmUfktAxQuN9aHYRXpGGWZwnKJ03xuv/OAKoCANbXhrFqRM06Gj4746/VSX0mkJca1u7DzcwaRrP3CdMD9fLVvJdnNYGFVA02ejwfTJJTJcCFj1odxKjxhD30sHoudWObK9O8/fU8UH7NjVlqhOwkTxvWoDTlAwTEoau6lnpiLfcG3BbwNi9H04+wLouyc2/RvByd322KLZcY+CQ9PZrAgqXCjCO4hPmOqqDU20JIe"
               )
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "Pu97+mnMiIuyETiJzdTtx7waj7GVWQQs1ZqQtct7RIfSdj2+Ar7kzeFhI7q6O8bt0c6DvBOK6rftKg5fxxySY74NP519t9Gp1qKJ8OpFxq57XBbghYtP/NpcJtff9OhxqJQQ/hb8PBUVt9A9cPYv2+RhHdCcUkM7D6Id8GZ6wWQZorLBW3Jm7wW29WySXcKynBAOQ+zBaccDa5mO4u+G9wiqaR+ZUocQS7xsVm9Oy1/8vUXEPf0osS9aqIMYAMb+s8dE3O3aBAlvcGhDyCbLmVGdXyNB3TzHt3fuwbRnGSrVB7fej6bw0nfdDEWxNQtbGmBRBy4f7b2z29pI6GT+ungRfFnllOSHu3vAJtJ0"
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "kADcCCf2pSlpc5ejrkDX6yBDChByNBtOOtQhWNqnf2/A3VUaJWlosCnr5Wj75R1GFBGZZM0TQVsei51FLfjYAJtgdhbeTsvq6n9KSGvIIg9Txofalw0QhKOwpdP2w7xmUfktAxQuN9aHYRXpGGWZwnKJ03xuv/OAKoCANbXhrFqRM06Gj4746/VSX0mkJca1u7DzcwaRrP3CdMD9fLVvJdnNYGFVA02ejwfTJJTJcCFj1odxKjxhD30sHoudWObK9O8/fU8UH7NjVlqhOwkTxvWoDTlAwTEoau6lnpiLfcG3BbwNi9H04+wLouyc2/RvByd322KLZcY+CQ9PZrAgqXCjCO4hPmOqqDU20JIe"
               )
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "t0apB35jjiecY8UKj1j14oDbCZ8+G6l6WHoeXGtXbJxc/phajsE+6cSjGWWDMHmdCTbUji8JqN/v/5bXb+lnNa57ZID3sWOp+spUb0o9oWMwUYBVVbR3N+Zwj7qmYvMZK2r9p31LaeuTgEB3dX53IEgB9CcbW89EA7QDyonqpqNiJd2e6WFuxatg986rY1RSqZ9K86atXVISbh9N4MdAFgjE+5lBpOgDra4qNC9a0nbubdCzQCkNApRSrOqjA0FujwTSqIRVyGdiiQklOO01MsNzuegWVV0aXjFA+ROifcFj8L28z99E9QTtpqw58qzDlgZ4Yp0UxLN6+J4Sovk="
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "ZEvntfZak2mtuo07j39hQVgdvo1uBWF61LyiyiX5ZSWExo2cA/wbMvI2zPWiY9EivLDyNbRMZHL3ITPtcZaxQ+B0UVSTRZBVH5E6SfMU6G6nrnDlfu264p2J2kK0bHKA2UIm6D+hh15KCfNWJfIqMpIhH2+ZRIqC/SeXmK85meKUGEQJvCrhdzs8eg/UjgF5sSpKx+IkP+0VtPze9je1vanSZ1VopC5ZtrYMLVDZ/c9jawunK4nG2RG9U24viub+2PJSzrHDon/qf/oBaMvHX43f0pVubQEiRh6DdRe+Ztr3+ZCTdrzhhvDIzCqNYfYdVSrsxrL2Zq6YbgWxLtAMjQ=="
               )
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "Bud42Ipg3lHw12eIRemBquvSBEoCYOPbdhH+1msyzG4LQnU9CJuTiZ6TKZLAexmO4TyVok7QBPKhKO38JhEN2TdTgc9aR1x1cQZzpQOdh6bFyqxR5lm+06ZeUGphNt8L9rd0Z/TX8Q6B3a/3YNW/hVX8LgRXbQD+mo6C/lVTRfeMRLM4hy87TUJ8zhGgLwbEX4dydZxQ85f7LOU1HbRMQUjR+Kma2QHno+1kom9ZCtu0n3gogeaYQRwMOUCEVt27DkonriUvJdE4u3WgvzK9wx5V73YiESjUVfO+LBRhj/gFGAXiM3wZQjgzyEgesW/cf/s9jjxhS6/JpQeP4sSmzw=="
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "ZEvntfZak2mtuo07j39hQVgdvo1uBWF61LyiyiX5ZSWExo2cA/wbMvI2zPWiY9EivLDyNbRMZHL3ITPtcZaxQ+B0UVSTRZBVH5E6SfMU6G6nrnDlfu264p2J2kK0bHKA2UIm6D+hh15KCfNWJfIqMpIhH2+ZRIqC/SeXmK85meKUGEQJvCrhdzs8eg/UjgF5sSpKx+IkP+0VtPze9je1vanSZ1VopC5ZtrYMLVDZ/c9jawunK4nG2RG9U24viub+2PJSzrHDon/qf/oBaMvHX43f0pVubQEiRh6DdRe+Ztr3+ZCTdrzhhvDIzCqNYfYdVSrsxrL2Zq6YbgWxLtAMjQ=="
               )
            );
            if (!var3.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("f5wSzZJyqLgHSS4rxelbtJtkrS+Duzb4ZcskagBEc/w7icYlzhJ2KlGS5QM7qMaSkjZQFXEDvmFuqT+ZyadeLJnw"))
               )
             {
               var3 = var3.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "+1YABLyL21jo/f21+M5Qhcwdf6lX600K3sQVl7faNA6rxtdSswd0isgNTzlB19B1gkB4UXfaWRFoOtlTxPRSD7CWYPiTVl4uzkQutoJhZz4vWxZehupWJWkrx2FCUK3mcJzIum6a9D+ETve5bZSOACXFJKI0G3ARW+ZUcAUtwCDTNQ=="
                  ),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "9KN72HwkUuTjbOWkPoVEg5OMajFZk4ifA/YnGKWRIGL7aoBrO5jYuxxeN1narnm7Ogw5RwygTQIpZugBMOwPIfxDFf9tfSjOIEMq0rSWWR0z1m250ycgOf2waCDK/yoXhvz87bJp/OXm3Y0s4nMZh/r3mgocnHrfKT/6lK6ol6r8PlLYG6E2C53QvU9LCI8U0rIemK+YKrzM1iDgmvNfWH4P+919kyqBPW39YR+Ibcqk7M/QZkBZ78Ruy1JdSiG6bU3v3wIHvjA/DU5mQ4hy/V/4X+E="
                  )
               );
               var3 = var3.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "zYbMfNjN+d3rvlSz4TAcJQieYYcAxR5rksm+3NpG73u4PwfhDi5swxKUC0TmPzceakqmTGWDATJ95fRL5Mb2CnakP8y6sldm5g8leomGCSCg2xc3FmnvPU76Tmj/g79dNASmVKxYeiQSIlzKm0ox5AJ2PnHbFW93FMwjXwSv"
                  ),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "veLtCMmXoTvLymY+Xz4nMNKYe/JAPohK03yG6zb9n1pf0O2WLfR+BJbV9inZj3oLnBlz4ZAFxBYdb+WqcxU1VPeh/Yji1C39WpvOt3HxwpZJJJ8/2p5iggVTXfKabgh3hnrSg8k9JIFIMsCx3H/Ard1FG/oKoeef/lmuz2cjORwcAaOFU9irvbAz3xe25BBAqf81mtTwtuv9WfAfYr772D+k0y1yJhgllWvGPX5SPaQqmp/AnsgnMbR1vsWt1RWur51ldQXfYOcOV+V1hys="
                  )
               );
               var3 = var3.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "gYWCdJoeg9ViuW6aP0KA98ohJYtGWtkUrGY0bqV03eZW2CNgsA16I6r0O+eQ0dy+GbRN7BJHaRcaUerJ4F/m5cl+BvSQEhnzYzWkihMdZuYN6ZRG/OUEoz1DGc5AMRpKyQh4jvw/9FfWWyNgok4QdYFQ"
                  ),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "np/DZzCZSJMLpafqEnliITf8c/Q4SXhwexwyrMTkq0wmf3LjJApOV4OefYdLoBFfo/8OCqq2JDbMDAM4jAQvI35heHhA7ZDWC0muq07NXYK1T1QxeyuUIXGlpKWWkb15iqwltCicKHVZSdUoMU+lNackuXkdEyzfXNRhNhfI3ODnKwY8JAK5BK9ffj45AIiWwopYKmBIVK/Gkx1saaGIDnDyOGOnLFKgOq07AToqC9CkCBaKqAJ8dFMLsgU="
                  )
               );
               var3 = var3.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "JemubbgJiaXOfzN7PWYT8YrDR/I6p9lht1N+iTBP7K/IgfzJ3YIkCBfsB5IqrI8ZGgwPwV622GJS3KdiXYYzWZ85cdwPENARV0TTioEfRp8R/bpit3wjbSUpmGOFk05/jkvlCuIDx7QEqqHIfHE="
                  ),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "X2XvE/phZaovp8m+8I+X8rEazvLogkVcGpOsUT4rlhXZL/vN9L35DeYjKRFELpjzMjJeN9IVJW6PrCk2MsAa6rqw01KgFUHQKGPCdfEE1xu9bEv1sdxwjqcLi4hS+O35G30n0+gk7PB0N/svE5qSPTh8FPzSOzy5tXelaAM3nA3l5QHs2frUzvHjSMNU+ILT/YV0a1osz87UuhBUffnhmPuaHplItLdln2SthxcSnV5sKqq2Hnw="
                  )
               );
            }

            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var4) {
            this.plugin.getLogger().warning(Xqedii_t9355lxonr4l(var4.getMessage()));
         }
      }
   }

   public void migrateEditorSavePopupComponent(File var1) {
      if ((1268859536 * 1268859536 + 1268859536 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = var2.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("fBlBUrliZZ/PS4r4s+Si6CIgYX0251uu3CJmrtg3DXscYbauwAUOr7ej7uyqmo3mwE3eX9SdqHfIretueN8="),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Fv6yubWmtLHrWqIUpi8SdfiDwBizPMZ8GMvVwyo7QDinoFWsvtvEABTSvLih7xl31Qk/B6T4+JLbkAwULkMXviUD")
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "sEAQ407eCPAweZlrlH4SH7by/wrki4zc2T9COfj9Gp7rhhJofTQvMOExWVLERX7L9MTitd1/CMTe43+fO024lSAE4/hlEf8veaNNVA0NuudEPVO3UERk5pdFDGKvbe0Yl3at7J8yuPwiF7VXXXdvucKFVNCA3amFW7G/ek4L8Qcsp4PqmRKfCPw5Jn/GHS+6MmZuB2d9xJUBlsCKzFd8xHuVPzdExeHhlMhVQ6Q1husqrzU6DR0JQ836kmrqB0kguPC3LjQSVt/Uys6qFw8ncq0sE9fFs77WszS/CRf449ps1Fb+P0JFlmJIgizHF6mPR/AyCbZJuZxMgzMyq21yi7cb4NUXkvNYMQcjqomJYmYWRkHWmHuGsTmpBmGjgFqTH4sP3wn4Z9XoeOfvT/qwDVTRqCcdifHXY0E505vsmjycCQFB4i5CZX2reJO0+OjsQDxTkJ3RlCi6JuiSZsoqUKfbHzMWljQ2bs7YebC/oZkOsbLgBh/otUJWovb215gJAfjkQCCNentQVXaU4pyHjpyVoSwEoT5sxNVVtDy25MAyO4gy9hYup+110VXyVIDh4tMwtI/TdGTBU30NYTVE8swoaFcWyL959suWVqaV+vL8Go5z+zx+yPgKDGOwXLpkImnWVf1FUB0gjipyiEpd5wnKg1MaYF3DB/2ukdp8oPTRSJe9oMp3z5HOJsZUQPXjKwv+IQVCRGmrFd8lpo35Q43QZ01sBWlSDYtDYd0UXkCpjKl7cDO7UxJCJVcVsV5Wqha28L+PWlCyEAReaYvvv75FPhZk9HXv1ZKUXPKVYSJGx6R0FgGhK0P3nAJFShdkWRv13ZFu1bvWfa67QvZwUq5cUWYNBvoJ8KSyfF8Ynekj5r6Ft3/A8EeVauNSJLDz5hYg9NOBE6HzcUSLi3oVqHuZpXP4KkJk2CCkG5XSSHYC9fll5lizJBRFYx3bgDRdFF/ZvIF5EDHzvCEVE/fH8T6MZf+PBNQqPqH0oy9GL+Vy4G1V3gQD4zBD2YQdrEnTgWnSjZsp49jtK1rLbM1e+q7jw1w="
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "QGnqIAW50WOT7YcFGC4lDLb5RtiYgg9rmE5MPPsmnETJwM50Z7f+G8KAJLfbB7jzZ7GcOyoJF6JEG1l9HkgZFwulJyX2nYAS74CpcnPo57Yu+ea8klS9OBDWf5wGXBpILmlg8dYvczKNRYgVU1qlua/SOemZB+LsrzhRso67hWhnv0AkiaLg8VtJpLlN6cOZvn8OPGBaX+BjqtwUZmyblX4xCm9SO9qRfRZbFRTdAMAUvdYwY2jrBChMtaYT9lq2DN1QP2S/fjOO/ZdQSb7Xdz0bBFdmKUrP6JbReY6ceGEuMtT1Z81yC7yANw+aNLr9r2cKydvdpBRyVTBkxmsJRKOjDfJPRiTnwfn0gyOmnsqgw8EQd74tIih+x5SKWkGu0xGMU1poOekvxLHBS31/9cTAcplxvAmUw8msXR6LYUJ3kJWVZbkFVSrhBwUjLyq8a+9EkhfqqnobBGt9ky9zPDpm/ofU8XcPrImpJ1flK4bLD4B72IxWxO0N9NInh00q3Gic2OT4BsRma4pgezZ0HmyT7WnH2yfbKqwFca1m7NFFF9Bpu7T5qzmBhLHlkasCci/i2oTvJrjxq6tBw4Q9aDytjELk4vxCga9A+5be0MAu1Kvxfm00ujVgoJ0TFVGIqcAwnTARqnaBLESNZuZ7QkTsnJ2TMI12V+MRfS3gjuRjCAG2fqHYJnjsBrL1ye+9zaB6wDT9PJiki9G49tIJxGdinsWQaNvG1wb7Yn/O3LwOdIp+EVT7eHyCkQhODiBUn38WrvLP6QWlVlAKSDz2lwS1JcX5aScxxRQzEkjp6w5x6mrakcqaAW2DV3LWJqwfP9W/87bHOF1U0tuRq+qNxYgdvRjvPrDBw/ZmT6RvKfgVX3HTSTytRkKzFtWRtuXuIH9eqg1yWU3QKtyRK0jLNrWNcfk9nNQhnnVZhVR/yU+Ln8Da1qKn3m61qWzveyR1YrdLF/MgSe8vz6TWnZKff3sjP/jEaTBgAURPsB7VSAZq0HEiJ6dAffUWwO+XD/tOVpSd1bzLCFRUxYW2IwJGeVDVMGV+jjthvL09N48f7gXX9C0+7HPaJu24JwnUrKnqCPIvItKa6Kvo/UJ4/XYaVTkRKQvOnt8pIY8mYVOuGlt/BC2zeu0/AxxDk+uTNlCzfUjB52fPhEM0NA=="
               )
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "apmKSYOsNZwJPQs4O75F+8awmB/w0ihbRUOSfLREDY2rXnrfZERMNUg1u67fvqoAqGDmvcwHyj3C6eqpy7rNBAwjXnJpEaKkKzizGXU55LRqzGZ3HGUKuV8KYEIRGuOUJgLuJwQyS9BAmDnslcAsIh90S9M9flUj4qdxmV/s7hu/UA2cp+twMorjVMRL+tXnq8P75X23T/l9TUkVS+t5GTXKeP6rQkV2i91xWvMeoU8ggxivhZfMabobmTIdbxeY/Tjb8bwyPhOG1aZbsP3Ll8Mhu2EbzPkhZ1kxw1oKCIuk04KQ3nGjKOzD0LuAtKvrOry8lNWxCKrEuqTJVrKdRuwu9nKb8YspWQgXvlmoUkK2d4IUOccBDzex3tD06+jXguilxmEanNmAj7pvTa6D9sfx22L4fc2aWAw5LQOFvHySONHyHfwwK7cMGsyXTFjtcnk7/hxT7/Afa7YvwPRhHsalG5ZFNX9T0CRTJdKxHaBWCC2Z8tB7Tx2jseRi4Pn/b1+T2a4bwe9SAKSDR7afkM2J2bGemNHTXzOX+irkoarTJeNngkgSmFCOA7+bvyazstw/O7v5pLBVQKETKqZs1UjtLVvYjjd9kVlB9DbSdchPrvlIhQGHRH6WAWpN8ftZgW0sbtZUDE+9tajcu5UWM5ka0Wda65PUAepO9i/ykb4sgogK7Xb4C00XWzRZaIcBOqWjT7Bp7QDMcNlnL2OmDa5DDr6foigli6hOrn9bzRRVMDGG1f+dzsjprOsVfXitS/MrPE2OM1FOjTBdr3uVoLlAxeN77Ue2Hb3OkoajnTBgvhQnDT3cRMUT7oRo6pnwBa/2Yh4FQbfdhP6cTR4Hhb1a4RASSQ61gUqIZpqg+0gXbLQ1DyB1Bk/K8LUsN+1GRoJYfvZmsNGguvN4BP83Py/+B0WqlCq2jhjMB/JlJFxRnrRa7AwIlLJgIMBimeYPP24bWvnlLglfomxv32gjHUTadAbcYlNUvvUneO0b1zG8eshWlE6ejLCy"
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "h1JXuz9Vw3NnAQY0C8ik3ehkqGNpZqq96rL0NtR0VS3wclivKaCsCev0uHZrJQWzecnDK1YTQhYWtXgS8/j8FgfqywFJGiIXIfMxI9svSgeW5JLHOvmj6hOOANHR0MUuACB6P9E+vmyD6ufYBDK+kLdudHaBDovEWglyhhToqb87/bRREfTU8zBeXR/kyaRX/sxMV/72/2rXwH0DXQM9hblIft3q/ZFmfI1pxuLIb7ZqTpEaDPwY+wCE8ODwyDMeFdhrij9RfdpP29g0hB64e7TrhueUGX1LOfRDFaWtQEu49VtJwsLYExuIu1mPLfp5e9/chUltWcfcwCz5ite7GHl96eIFQ03fpupBhVqjE1sXv1nUsTDvSXLnFwDTtR+emgkwmP5JCnEipUxjZ9C3TbgCHsUuCQrSZrTPi0WNr94EsAyhzbBYE5ddiIiqcC0RxS2EvYGbZachevgjmtP/7SK39acQbJbIAfxqk2Morr40bdFDPBRwfyLmVsWszYVXY47C7XXaKcQCgT2npCGL8A95bVk8r3d/qy9H5nPibzczitT+uYJhcUA1VKYXkerFhPLJYYWHp5AAVRtqI+soIiuAaFpCBFzMM136R2vP5i4yWeR5ZVTPQlfLq7kjVRlNelNslvYnoAqBHAj+mUJ3I0006MhkiWR5gzCwKXaxhnRXhEoeytVAvQIwJ6+JU/iDlAm7X211USFrvVH/+/zQDaE3luuP9Sz//sAmdklAnt5r8mhVkaCRKMiQlSwGYhTtlXZYK0d/bmNWsk0V7MNzFZaxcXtigdSvR2TCJ3WiazNivWRBO1nkkz2+Rv6NESyYmMiKpKHZLlQ3ZTTJFEXJmMxuqfxC0BCk7NE90NwVN3YVNi74Yy4PzYPqKkKvsjW6BjDs8jHNlhf6+LrwEli5YFdee0SBC85RMeD7aIakhV5/W7G/weoUMzte5RvI40rlzJQgiaGb3DDBoYn1EPbLeSJxT8zxgMs+v2wvlySbO2/wPvSf2aqcDEAiqgfypy3cNPxm4Pc3kdMZoEE1zK2claHZTGbtza/IrQwE9CyR+t9bAQCNNTf28Ap2AQbyuJhEgY74EpqhFAaXEp7pz8FIUWDZenksNWoBKeHeF0/l"
               )
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "omS8bO/jYWWzIIJF3unO8IgaYoaLHuyrjEZEpRZH1+PuGwbehcWBZ2AlzfF+IBxVqAvkNYfStT01gtdYFPnwVDbYy+AgLl4DIrI+3wq24LkJdnP/j6lT7ST7FcHKIkbsDyIGZTQoTXIEY5QGE1AJ8q9hx9bayvVzRk1qGQPxcTGJZeI52kCt3A64Pn7uaasXAecs+dr6lHlwym/cioug5VWVeuSSHWmVwMyfeGq+JOYrW0i02H/zUN19006xM/PyxIlfLqRBYpmWUDjj4BhRW4knRN0jCaOM08HMz3dARelCn0HvjR4TG/wqIb2yhPggeSms9R5mUkIzB4PTIdvzIcA+JSuZKxaVxv8om219GaZDPkqTW7RVDqe/Z4HLWvG6PmpEz9amaksnSgivSCOSNFvTM9RPR/VanoZ90MQPOrm+NgUZSpqA3Ux9H3HETffLBH/1dI5eiIXadUDL3cD7JbEgaYq3DsGbdF5TJtwtLBODCYz2M5G0z6ggbt6RdC0kVsKK2iUHiS1sNuKTZN22wiJYFy0SHy4TZTTq0GkfjEujuKYISfOQyIsqezeeqfX9FoCEXNPwgkYhzqESR50I+l5tp4Eds+QZBMs0XoOmrCai6RNo2KDSoRL4OrkuBbBQ9iZZBYPmwxUcv2vODxf9/nz2Klea90asxvpWM+ePBiBozjsslIcaDr65rHa2YPl+C2Q5MXxoMWxs1CyNFxoelca9o7kpdAvGahbeHOd4VDZRX9Se/tYYI+m/QXRFgohVJQ7z93YRX2NYvNRNrlkOM3fh8Ru9AaHYdd+/72wxb2sxMm1kMFqXh+sBYi0HWAzAXtJJs2oRFo66yQAbWQmvJ1iwDvJ536F4SqezqfYgj6qciDVzO2aIlYs10qPWQQK04Z3m9kCmFjfiHpX5/oIydIOEjrHzIIYXLLKBeFOrxKNB27FDg6EqiDgX7A6qBfoP7uL0bZ0NcIjBx+C+IQKqP83mmOzTI6GGgBuxajDLnVshugpVX/ET+9+C9+hOMA+0o6dCmv0akpLyc8grXKp2KNhK3EaHbk4TBxZ0UmQ+d/ySQw=="
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "JvdubJZZ1XeNWbjkL2EIL6nz/3u+t5fM8wb98eQ3E0YwBhP04y/2+vUfwusvgjkApNvPK9j3rXNoT+MET6r+u/brK9+ZWZAhFgH6dIWYMvTutDEvpEZvBufDenfW3gTvV3LfllydUbKdGA3YInCRSLCTeyaUHhu8wo2KjPBgxpViulIJOJosjhN5ms3d3ulGp8W7qXJNUFSl84ZsGi6d9Mr3dDaz9M2DjK9u06zXGj46yuY+XNLHYfAa0/bQYmllak+6h5MI86XYrDZtYyp5hYLPIyXz4qjTQk7Y+quAeqeNgLqFm2VvxSkdV3Mu+MTtcmyC1RBtduc1tqqm8sOquJ/B3EYzYXZlCEoieoch7/vYa91KmRJrWB7a5zgxrnPx25xEkZtMim2/TSKPU9Bmd7zDQT5Ra9fTlZ+fRGDYhqhsvuN1O0Seg4hJhjjCy7QjwInp3tHBaqH1g8ol/PXUzQMEzS6zWmHVZyC9BLjLO1Rtq4AFI8dGUVy31wPzSWDIAZOytomqRgDJjWTc9QR7jMHkh8fe1NVFORwPgng6IiPiBrMo3Uixk2bZ0I3Cbgcekdk3Qgd/esArOttUkhyMusfeoveE77glkVCMawHONbmbEwfjD8ok6pcENom/uqo/hluukN8qS9AeJO9iXQerH/QLXAe2wm7cHE8AhSn/V+qz5J0aHMh/sQPihBPoIVJuRD0Gn4R2fNxJpVjiBt1SZMDDYNBERjTZkUo4rfdHt/NJWj5aaUAmaNJj5gGp2lAWpMgBvpkrjQG6V5KSL7j7ikckWRnlnD6/mDJJhGodtTnsRsDA+YPecCuck0alapCt/Yf1DdrkyumfYL2LfFR7dwk2dm5icBRzlEZlMvatkFB0L9CzUgMP2UfkTzs65xfh2X/i6ttT3u5HeInkn1VxiaIf+OGGYRcw8dGAtXBOZWWQAKEL+X304MGdDf0UWJZYzONo0FOqj9s2nYgbubRiJIeux4qJ8gFscJWhcBWCFYIHdARBTNjBdENhieXLP/bBWxhLc2OMWJ7OZyzDYgGRBw1Jlwmeb6gGpACzvRAcRUyG10NBelH1iZEToKeQIg8QIR2EZv94CRxrtKs1uKXQPl2PUODBn/BKw/ueZgdy543uTqO65DYtyOQApxRxrVzbBr09gmsa35WtinyXtuShpovP6mpcOU/l"
               )
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "daLgipwcD4H+RDYBkBBf2k1xtQ2UBMWLrICj+GPCHKYN3mgIKwjmYlEhkkgiCGNszbMieNFUHxkWUnChYQeRIb6nKYggrSqTIZjBuO/naKS6Ocw5Fe46Ancl+mxLOXWA2V9j1mEaIoH60lSdWRArfkqmpaszuDY06ON8+gF/p0p2WT60Prj07gmYRphOmOdyF8uUFSGj3Tjypmss6G1BjCrJoQvtPyQWjaGLWXhJsA9ZmGws5aMWEuKsgs1us/xOk94pk+QK4Na5SdibCyA7qC8oYcYMQ76txa6vD/4kp63GHaABjoJcdu7NCSwjoOIzJT5gI9ToLuhgX67d8us10sdEOH/knsWh9vwEcmmx8/k31tXu7gxwfFAGtSfpXTkJt3/lpNL1KESQateaLdQD0yHC+A5AOW+CPmU3tCw1URJarxGHECjkcrk4pJzhSg+OIy4NaiJeZlziof0ZGwr5gZvZA9ZwMXh9ZiZv+L0rNgCE84Y/71NJpFcW5KxKbThqIejyKKVBXqwIu6yYYKlVdcI+oBVHDIIPA41Ok8Pc5F5GVUq9m7A5bqqNGvR3m8Lk6HQWNGoF96dm62ollp+NfBjWkyVqZtGuMIHTfzAFmJJmO/vGixQVDoJjxrkBpTd4h1rbplQyzrNU1i6PbpT5XR+5ywB2Ih4Dvbxs2xnaPfMqoKslgMRvPMn7x+FjIVToKqqZyHgD6KMO2jL1xspzOedvt36bj++PtjLda5P1Yg0xWtRh+J+R1hURSPw6pOELcG72PgjeG4v5TTX4OGB0b4nrd5EAu2XpXeChqIAcplwwYY3hewezyWuHC6lR7nDiUCIIwDWjlbBCyNmya/GZwPbWxoMRtCO0V2lQs1IfTVvfrdBps6ehPl/3EwoWV+EbBmDU5ZzMD6+pHwbX4WBj3/dAgI+WVPS9RO8MrLfphqLWv8dJLPis/F0MmuupLqMwRn2PpvSW7YFVo1h519ex/Sbl0UkV+IUgLlTDamWDuC6urTNboCexGUR86YJYoy9ipSL8PpUgAIM="
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "glOSBPVuod5NGRk922MYrUEHEmxlbqMFp1BBsSBu3aZBLWfhvwgy/GEDDHDxc9Zp6oOd4J2Iz+PeBIpZ1GsuOe+Ck1pL4XpkeDCWB4Q74bJXPIINCkKGUXhLwJ/KgPdgrFsnnE0+zBQ7swuRLXAgyVgjIzBZ9emBTL71AjL6TgpRRMHT2oF8FGtqGPiUeQTwG6IBavsNrsT54WsG9Uutkg+tOCmbG7sWeNs8fxwppdciMheUn8CTkOTXHeeT0ePuPGAUcLZHwy6+vvewuJd7UT01NOeOrxLigEfcSDrnpJBcNICQTNOX+G/JXxDz0wb+PlP3thsJsKe5e5M4xKaP6GqD60emQ0FXSqJvVFAznT3foOOZ2uJL8/XMypFkcrOe4YuxZ62AjsDLYpKdcN08qTRJxUkN7R3EDQgFIEYvXjaQhujIG2TNQBP1YAWrZO3mu/WSi9vgsChEgcGn10+uxHdYbR/IclMl8oAllIsjsr5Ivitru7iuaxe+eZENf5lVPjPg7qks1+kPHRGtxryy8fz5DDOURt7yvWnLGZ122jw7z72spXF1AOrJL3JG93ZNQ44bt0nkl5EGvpO6Fqm+gftbsRIuofpH7nscplw2gScbN2WpuoyyxqyS3Xn8ADhrFvDsNlxr4WjAu7n3SlgAkY1YdK8XreBGkPr1yvvHXkt3s5oHnUPVCkOF6HlRSrVG/wq88UoaHXRDfr8PBSbAMsFc4mdS1eVN92unshjntqT2J8bzDEjoQsunCfwWGvfZ0TBL9/2IQGa34CkiKTWIazSevqKs102z91PQGNySd3h1G6n53d8tYi+uciot4wuRaOKaHWLG2PtbUymzOR/XJip5qBRZFLeWbu0yYvPplCjOBKsHCvZFgq9ZpsMvPJlxWcWpaFJ1Pxi/PDB/bzHFcLmFhrJKmbZmjWAShH4Ansg/QgK/8gwySegzJbSCeXCtMjGGEMlNsRSj4lItBaVOm/xvBdwNkZqOFWS95s8ay0ka/pIOKbgpZteMMfndFX9H+Ln0VmUjmxE1QfymsPzc2RueEV1BkvHEZlzwhMYi6rudsO6LVf3WvYXESzfqv2sXGU8AE0pDIiJUph1NIi0iflG+Os+65w+fq3joUOc97QaNWg5ohKmZubbCIWI="
               )
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "x7yyeneTbPswH2VuUhYKyMZciSRoKiCrqUjwY7iNuuV8EjcVAEpf5kTACKd3GN0+d3xsSdZIkmwVxiGnTzlXbynp5L/RLDaRLkixxWO+b83/GTmsizUMuwOYDVsQLyg8ZhlT48zkUK2TdNnmg3D4WSa4zj2tuOCp/HwQXTQZtjCp1mVXXLxlwLWOl6wggORCfzXFgdpJh2ztAWI8+hjmmCppJrcZ2i3EmGP+rbwvXKugPud7NcGJVXNxToyZzrAkm8gRQ46wZdSmd9ZGF21xhhGau534Ej1rR1aAm4/7/ppN0B2628nW0PzIuu3MqMiJH7/ac2dh+0g76UfX7GdJqemjZ5tB0BbULWDZkltYrVaFfbOu7QnSg4UFgYr1rThIWbL5OvBRd/1zYmyo4hbt1Vrbg50lBdfp3KUprkrpHNvIROK9GT2yn7TZLEfs3nKGb7Ni6G11mA61gYAyfTna30AdN8IkfdydpbdTao5XkQoBj1HEZi+7nhXKdhPcoA6R7noYutSavyOqLMqeF1jtHuH2n7rcgEQslQrP0053vG2oUu3/510hEmQ9DJl96XK1xlAjP1HaGWnwy3Cn1BE0Fao7OMzlNf5tuBxzDqhmQSwMEdXBOODRvQ0H13tJeBqMTWUAdP4wrt7Fu6W8jEp9MPSuEUqrfSVqAx/g+nPnQTxX3JfyLhDg5qDlAq/9BbNN3A9yg+RmWLgYUJa0/UNBTo/BdsYLK73obP/ApV1GOgSQZAa67Ear0NcWtT/3e76EaOUpFTVXhhBtYLoxI7K7gWSQSt9f6QPlaVWHZ9DCvTRoeU/hQMK0GQEFRA4R++kIkKzujNUPwAVcrlLLQQ2nUWD4w21ajsL/zKjgBipN2H8PsO3QKeH4D0oEO/zaGeV+nE/pS27oep4hnp3k0zGXLbryUm+DwfAWuKjSHgxDB6ftX30/dqbUDQDIt0OK0aTn7osDxjRXBrmna//GUQTtWcmpaTThlZjtRFBntQPjh7fcXFTwJdaBmLhliOj0jtJKPuNNnHsdexuv09lFEWjB2j8ux8MW5uazCpVRS0HpNFHYjuJIOpXVYMpz/PMUX/3DOGPdMqmJFmY6KsZc4ucnWssp"
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "EhAPxAc+AlumekcPT9o0Prmq7I/L9uLFhJM8YcU1TOTwn/KfrbWpm6uObhg2q8rv+padOIBvJWJc5IdmSI5C7+QWtt4XnJf6/vibkzQJW5ftUJ6Rokkec0EIzTVYq8psChWXNFt+cKNz5oXJjAwrWwgrSlfLBHl2Epc+ObPztSvT7EV4M03E/CQDaPU3Y1xNBlKSDsaqBS+/RsnbLbSj7f/NhIXUssTnY+H24PR1xYSndvdv+TvTXbCSaR4H8wvXE4yzlEnKxRtmz69Hb5OYxSMHUbcXBbKJLL73zzypTdfILWCTA8rH6eEzuQEPloqAQ+f6V94NqosrqDxh4/tjYKZ/SHvyW4oUgMUaE0SM5ivwSVCsHitjWN2EM/mkkzJ1Fo6RqeTHr/0JpsltAG1dB/szSR3/y3QZbQugqA8IUopZc6eMGvJbBfyZxH1x800tA4vcEVaiuTgwcfLGyCLXiK9We2V7zkPHNsmwp6b9XP2ssZ5lKF9qNLDkYSOHXYjFZcg2c2E2O1STeDyK7ZVfYM/mf+/0DQsoXKN1XzEK/BZCCLKcx6sGchIoix1NW+FY1PN3+2K35eK7MtwtL55ZzuARvzRJbcNs50a6X2unCYKAc2yV3+teaBL2BdAi2uriWs2S9LfK2cJwbsZ+d4VX6gVGh0UZyqgHqjeMRFh3YfyT++JaTW+OjIuaNbRH4OF943aCA0C0VQEws+LkH2/I5SPU6/8f5QyfMLpamUZHCi8zIh8pegXk9hD0Ycly7Lc/XD0jj6ED2vUnJp2Q/z1G9EnX9ivDne67eJ3wQRyF/SxNcqDvqYu91wdEIbmgF5ZnS1TtYm/wd92F/aGCePIFUi2uxpGhaPY+mE8vy2RlV1V8eT90EGqutFayUNpBsR9aNglbKUhlh+20jsKdlGe/2XGOJwco6XQhj827tyL4dFrtXEfUUG+SLv8Q2+1hvaci4F/gwfTa7CT/vEi8CRcYnXGJc/HxrIb8BJlE9f+u9n3dEd26NQ94Odyz7V8kP0ZR1HKjZIJ78N8xJ1Q199WenWSZo9L4rqvqdZMidmcQMeJn8jjBmLkWxTfb4jcmbAP050doIoz7O2KMMSH8dp6glNGhDQ3dwrBazUMWEWSMvm0NRl93mp1+AnnTwgmHi+a4NEXvV/fW4jbFz0YIHidncVdKpLVfOj1kJF6rFsauL9rrzeEJDS0SEKsnI0kBxWbGCPGjzQSjpHamL6qrS9I="
               )
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "DOSD/XrxUnYP4GP6adyVWUZ/ewMsrRWGto3Qa39yH6Ki/0G9RjgKqeGjkON33t91AHon7DEqUZyQi3xUIebO7Wnaa6VIQpiszM57m3z3KmEpMQCRZh1VB68lISNR+m+Sqj3YNKYu1XSnc1kG19H00W+rXiGcBZ9laGoiWG3nRr9ZBlotJ2EQinLzJRir8zTaq8QhJU1fWkNOHgBmHvWkkuPRGy5j3yOKVBABpm2Z35AtsE4I0gz6DeLCkmq7qTpO0CInPQMGemsPFNUEIVLMkAcKtxcqwNJHFcUcbb7QkN0F7iiaigcAQH6hVJfPQyGxgiZC+fTNbJJFXIdKaAB4e7KRX+az/rhnbpPDQWHqIoluF2a0+lOUuyad9qugOuzpctehjCTzbfAT1GP/pcCcGnXvAfRxfOmt7h3RMIMzaexmqxyMHKmWO9xW3aw/qd1WYayxMRBF5Adp//mjwrx8LJEB8ePkoKMFtwSI2g5InG2I0tNW1y4wmEZLzo++V5h7+gf0PpZBAg47//xnWVkMFEnwWuIClSnUnaA+i2ERLjppWxnSgp3KXF+tr7M7L/cS4HqUJIjqOhIYzfngeZ5mXxNkUUNW8L/sW4V3LKmxxsJ4YTfHzCb0tMBPlxKi2DbjbUWm2RJSOUnJheAfR2i1axKKkO+bSofm2gykLm8E8UH/duB0aCj0vsRUio+3LInTN7gc/pG3LQ97epCPkQqBSsR3GKeXm+lTNwj1LIu+nmWoyE7wAMhmYLoDvRHEDH7MWUFSVjbJiLWON55Xsd9bYymF4n1TuEJw8Ikuo0yrFbTvMfnxinCCqiN15VpU8JZ132BvbCG7BkfDjPssW9qnUXXBzU0sumfX52+14TY8Omygf7A9chtiEIDV2XOJ8ZVx0XWbVp6VosmZuOPeHpJsA+tACLFwSwy3CFymxEvGnPQVlvHBEeJysu4YZMWEz9cM2bn25jva+iXvl2rlvdxMyn5rhdgg18IJTtWt201Kt56FN9J0AVm/6uceOjGtSnaNFhCoS9EP1rClP9HlRDb1cFXoCFuxwyN46YjQUh++Mz/kJt6srxdKRg=="
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "kg3aP7zHyhAzLa7r2yPslhdbep2BbD35p8qJTzQWfAtCagtHyytc6tAYhU1E/LaP9e/sjSm2EF74UjpDYwgh51ShuDfcvIsSTFktTJQxKuVXOaGjmbsUKgi2gbkAhcuXYUzO1uFz+7NSuqsdZC7OWtscc4LU9gXDeDR45S/nhbAiv+6vxRpBowpbwSd14CgqSU34ra9Vy0AMOoQGQeKqEjbr38cztGiqZeidB6/pEvyVzaFL3j+Bqy4Onl5h0JF8PootZlxSXffUH3YASRFP2yd8TVwdU5euf08SdznryseoW7vCWes05j4/3rgzC2e07264ZaYdZgCrTJ/EgZUV9GjyntSflxaIUrEGa22GmDrHD2mAiwqnZaPm8mCn00FcZg+ttdJq6fXtU+Wyqt4ZuwBzdv5nlMyXGg59hLO4kd0W0Sne9AJ/Sso6Lq4K3AZXBNZAmaVUW0GiSGK2RLoCbedzVOd7nnJW0NwqhX4hFSSLNz8Ize83KY7c6cf03297taBFvdEvIeu0BzDu/PeJKQb64v/lZ/IOyjOiwxZ3Xb3ZbfUQtLvoPThbNYArOhtG4LUnfW7QkKZIcd8q5W/nYUYsN/aEurNf6jTKFTV7UGneBHd6xIlRza2rSuNM80R0ekZkj3EzxYU+Hq7EqPF30zLExU35NUhOpmHN0ryuNdSDCP6rE8Uo8J9wz7kCWm4u59FLdDw2x35eETqnU2DfetlfIiAdXxWnGpZMmCF/7kBlQ2WyK1HUJqrNhV8axtdpxKyNQdx4cO8P/BCzfOT1gS645nuW6+t8O4nw8PxUDZo4KqgWq+NOKYdhp4VPF9lJ1YDa8N0+3E4hq/k4jKapOBpbDj8bzUZ/bYEZc9+0HiiCFE24i5SDTfzpzGLZ3FwVjdE0XXMZpHzhw8Hdyp3UZ0ZZI/ORgrg3m5PmucKdaS1KnG22A9d3ib/fwvH6UXlBGXMSXltIzOtkCLsQcjyNVClR/KtBW4Grag3KkWI8rL3lsAgXNl4ID00jU3n6B3FVQeSSGUaPUyWSd+WpF30+jxmcUBwLrRPCwIxrS/ZkWJ4jxbBLaFABSFYuUENM1YIYa9DoUREIGQ2IOJKNf1idxsAmwugvemapI2N5TN++3XbOU4D+GQwdWSeywQOTEghYLSVyusQh8LOcNXzkZpyJaMrOGZiv+c7tCcr8YnDqr/B8Tg=="
               )
            );
            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var4) {
            this.plugin.getLogger().warning(Xqedii_kde4gubkp0hb(var4.getMessage()));
         }
      }
   }

   public void migrateManageFilePopupComponent(File var1) {
      if ((2028158384 * 2028158384 - 2028158384 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = var2.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "VFoJZsdcWTX2pqvpQv2Ma/NvlZVravz3KNYjEbHNOI9SWufmQ55z+QA+lfgNqK2ly0mGPXZ5mpgrWqqAGs2WwAjZpAkgBjUZyOPm0l8BUJs="
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "tc1b5fsayBZ6X2De2WeDQh+UHOdJoSmHv74wkwhYCheHYB6pQW7aq+hu2E7O4EcsrkGvSbLJLpaZus6+YIrvwKlbkpgy2JVD6jEFVeiiHFYtzlQJ"
               )
            );
            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var4) {
            this.plugin.getLogger().warning(Xqedii_1j6vuftrvmapa(var4.getMessage()));
         }
      }
   }

   public void migratePopupFileElementComponent(File var1) {
      if ((-662850011 * -662850011 - -662850011 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = var2;
            if (!var2.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("NDt3sMCC8mB3m8GyEYre4xJLVAbsBzoP0c554gCJ/gHt/9j+hKMfDNOePpAo9TaY"))) {
               var3 = var2.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("yRpjxRZi89bMzRMIe/PVj+/apuw6rZiwsoEA6d+6gI8xjCKtzRjN8ouYuovG5nFVRuQz9B9vzrs8YJst"),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "81rrsuH9tiXppaaZsHnJjhyw+fWtZWK8LzNp/CPdfuWomnDhOFqycouFv8scea3pI/XmdqH3AbT33/O2l1jNuNPLI4s8lUI1jlCEAc6IAMrW013S2+LWXcBk78x/2aV9nURNMlAoYvRnjXqXROu+GRGsLhFZAizrMrWwRQUAafk3M8GRwh6pw/0U/DMffPiaSrYpbxecNgBxvQt7uZ0="
                  )
               );
               var3 = var3.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u7dumcw4/DrFIsAQwzV3ItEJ7dkDfjTyjCLtI2a0CyHzNrOhbZ3hA1iCYapW9e9WKiIvCNPEWzy7Hg=="),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "7d5tzkSSx+HAZZz1XNG3bIcv9xls1IY66mTxAyyhVcr0xpWeuyQIeMB1zq+4aM19KdA5yJ6abM/4dE5Jcx/wotV+pSc/XYHfxaHLbkzX/5MjkuumMG1UaaERNffEfYR1iq3korHnkGGTj8lP4fGsAh1yjNvQKgtndvCccpzBaef2x4xknEhslUCZxL4BE0sGoT+DaIUYzEB+9Q=="
                  )
               );
            }

            if (!var3.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("A3D/P69un1VRKUpjZDdBQryOcSiUDAFNYW9HA+PlPbcyvPlWeeFcTXmsWGth5+qgVSjFeg=="))) {
               String var4 = this.detectLineSeparator(var3);
               if (!var3.endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Cpcxs10et5jAh9jF96VeYSUuoW/36H9KnlgAhDmG"))
                  && !var3.endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("eZ0PbuwFtDZpj+G/n+BRZZGhCqgoc8gClUOTXE5r"))) {
                  var3 = Xqedii_x9xil6i22sbw(var3, var4);
               }

               var3 = Xqedii_biotc5f0hjn8(var3, var4, var4, var4, var4, var4, var4, var4, var4, var4, var4, var4, var4, var4);
            }

            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var5) {
            this.plugin.getLogger().warning(Xqedii_16xkvi9glm09m(var5.getMessage()));
         }
      }
   }

   public void migrateEditorPageFile(File var1) {
      if ((984648027 ^ 984648027) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = var2.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "yOhAf4cPrrNWMhPAqxQORjyDUzo12vJqZxgg5VJBMATAQPM7/ZFfaAHP8OIbs32Amp+vuYuZW+YnMW3K+Y3a+oa/pgzGpmdbrBbbiRbfTUQZHGLD8myPs9g8IYPRecE4ju6pah11JCcebl4vUhvjTBsAmS/whpf17wYKYzDEW18zXDqB/kIOWUv3uPU6zjtwI+6db4E5u4+JMcApKcBjvpAaiEGYOqQq/Ee7R4qz6s+WPA=="
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "TQakCXyzZYvND8AooG7i1p4DIqZ/JLgGfv1WjhrBoyGh7KmCtQR7GRKQobqXhuJm3giKMAOWMsWDpiI2MZRsV40KTsa8KX5bR71cC0ddPcbou1ZmsS+EOGeIDmCtvxXmDJY8Kg97vD1X5hXnaJJ79qURHGEdxoCiuFnyfTWSp0mraKD7FGbinOjoDaXaBKx04l850Y3souJ+xI/dPVF1sL9lpQPcipGVP/WqpyE7t8s+mWAIwtQ="
               )
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "o9e0kWZ9MeC4F1SmGPybf9ohrbo4Dv0Ct0l9x8w8Eg28ABTO/sfEclqyqJjSUkRTzNxpjK6D3z22WDQPqfGODub8DuCwpl+kjGjszLo1O4jerA57UshuH1rnvH31JI2x58lhOeMd3RAPSDTwqMCie8i1WCyNRInlQSMaWRALyhazxJzx/vBSbip33Evwb06JWfCLbUfWElZDrRIxn6VAtcNXwg9KxwOz6cs="
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "EN9jm5oxEy4XQEn1bmGbDWi3BZ3r37xJq8uDWP7k1Jp+XQWXtFYnCU2Pk0HZVn/FJ2nui5+ODnEM8jQPIxzNEmecYpcS6Ok9BN1vYtws7YDNQ7y9844SVBzzI9MzxU2qrlQI0vIiERrkTiVzcAfqWZtZTsyWFHtNH5EssBTnV23ArSjbcXhIG4IidIKtvM6YPFhMVjqmplSI8aBAUOuCSqYSrphPqBsqPJ1z6Pdt"
               )
            );
            var3 = this.removeEditorPanelBlock(
               var3, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("a+VWAWwNpiMSrTAtAZlKyNr/Wt6xQ3ED3bSXloQPiknqDO7dA9ATC8G4Nihq2YPQOD+swA==")
            );
            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var4) {
            this.plugin.getLogger().warning(Xqedii_n6cgjj35jc4s(var4.getMessage()));
         }
      }
   }

   public void migrateEditorMenuPageFile(File var1) {
      if ((-347355565 * -347355565 - -347355565 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = this.ensureEditorMenuButtonTextId(
               var2,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("4OIv7C8dHyvAZ0pvMfwL45KQqpvQSMQkdZ4YGNby"),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("QjOhZilpX+9fgO8aTqQaV+zXOeyo4BAAFNixK/7z2GFE3rWBIIoyfQX7yc0TOjWXKkD3pGIOBZLP84UkpqaEtm5S2wnOGc3J0bQ=")
            );
            var3 = this.ensureEditorMenuButtonTextId(
               var3,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("vQMEaRvuygK7HWABD4vfELsFev4O/1KFyr3U3K0w"),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("9FQGtj6hqO7gUAyKlbpvQz01JFbKyLaFT5lO/qZd0IW+J0Bqc5RxQeYExXZADhnOPzQkQmWHy+xp5RTOWKtpW9lv15SVQPwlGOpMFCYv")
            );
            var3 = this.ensureEditorMenuButtonTextId(
               var3,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("YGO0Bdp7MNqSlmHpge/VdEvo839YjhIV3zF/ZYnU"),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("DMvbWLvT/aF+YxoiY4Qrs3sui3AiOIkNCSFaweDwNsFEbCw8qdK+EBZy6gJ/g56NQxXPzDB23ZM/wAuQxAtsf1XySfOVL5Q4nBE=")
            );
            var3 = this.ensureEditorMenuButtonHitbox(
               var3,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("4OIv7C8dHyvAZ0pvMfwL45KQqpvQSMQkdZ4YGNby"),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "thzuFxaOTIWr0jSZ6dKGA9pGjUJqVujRRWAR0k62O40vT1NfJlCfVwXXiUNdzVN8Vk1eBn4kmmLl8np5ngEubiStRLgBu1e5+ZLBkiEo4prIW04ELRce6g=="
               )
            );
            var3 = this.ensureEditorMenuButtonHitbox(
               var3,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("vQMEaRvuygK7HWABD4vfELsFev4O/1KFyr3U3K0w"),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "fExCI5RDBQb1Rde4v+XqFOCx4gXqVMkzu2ExpexB68FjG9/1IQzFXQ8W3t/cw5riNnReAJChyJ0PT9XTAJ7jhLYqcCQpDBc5SysZCvpBY3DuC/kku1Id3LpTHQ0="
               )
            );
            var3 = this.ensureEditorMenuButtonHitbox(
               var3,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("YGO0Bdp7MNqSlmHpge/VdEvo839YjhIV3zF/ZYnU"),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "kKXGN6omqdKikWg8EHhO7z8tIzJueGC948Wf0zTeWd3yVemEP0JVJrtK6/wx7xaJvP2OrVANEwZ5lFJDNo1/juBDKieMjCEqgkIb9jtn0m06nD7MKCoMtw=="
               )
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "ReJoLc29BXaVOYhpUy6fLrzIaBHP8x1I/ox9IxzuOhQO15Ph7B7jV8qZ4Eg9k99FAGyX6UceGoHE850oaKA13mohfVPVtNx/twQ10Xb+j8uLUsqdKKxP/aI5NniUAq14VLI8ADzAWwU="
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==")
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "FCsZXt341Tg/iLTOO1XgcXfDufbRMFwhZnHpiI4+fOtk2iftAU7aFLqejlSgupJHLzlrRvip29Kgkr/qchwQRw436rClTj/5DoTM4tge4zO6ekZvGCwA4lPG1s1RpkN9hcSvSedb"
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==")
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "j+jjL2WpyQ0sNEFMo0nF8LyV3kA4tKzj7JPC8gnxqZYNcYD+oyVFPP9KV2aJGFTFgdMkTeu7Gsud+HBLH/WBUpDAgB1Ge0WEys588DGLDaRuAQBtlCpbswUbNkFEOpQzysdmCQ=="
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==")
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "pb0mRk6C/daCF1n1819/WlJ7HA7+oYO5Bg+UkD3gstxrJdGB3NpR/5HGtjn7ZHiNrYfJMLZz+tFfjQHRIinsSy4FkqD+kGymsYBssZBCYEtu1J8+nEjxEOJ9flDPCuPQIsuCbsHWz4+oUZxTqaQzbYYb2uVeKXhgM2nj2XLFawzsGdjmtM0ygjjk4J4="
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "r9DBfU+oRnNuq9yAYxRJuTCDrd5S1Us+AzcKSSYESuyFXxb47QlptC4RUi6j5z7MQhxfOx24bS8gCH0YvZx+eoeFmWiCpPukc5LaBGNSIik5wpdBppVl8CDuIxIeahhoORTA7GO56cZn+VRjuTyy83uLF5IQCi/rfiXxGTvifPJsuzTMWH6JQMmITVhV8w=="
               )
            );
            var3 = var3.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "UB85+bU87ctqJvF7z5ylyUNDqb+5KoJ9ntG6QjJxHtk9DH/2RgAc7FWy71LbOfnd0qWA4oETV5hN4cKtYBNx5nTNoOrQ2ntusSgVGKJXmZy8PT9ATUU6q1mEhZmtKHLq6j9UaHmDb98fEHP7MxE="
               ),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "r9DBfU+oRnNuq9yAYxRJuTCDrd5S1Us+AzcKSSYESuyFXxb47QlptC4RUi6j5z7MQhxfOx24bS8gCH0YvZx+eoeFmWiCpPukc5LaBGNSIik5wpdBppVl8CDuIxIeahhoORTA7GO56cZn+VRjuTyy83uLF5IQCi/rfiXxGTvifPJsuzTMWH6JQMmITVhV8w=="
               )
            );
            if (!var3.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("luEulzIKaVgQeNACHGHrLrOAsKgmSkQ4SuckUWtIQ5wVM4NbfiWUwejJV/lu2wo1w1dWVQ=="))) {
               var3 = var3.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+RH80cPD2bodnQwvLWTLXCFoFJ3HGB3HfNY0FYYiHYDN9EwvOE3Vw7qzULwbcrS3VoyTyGlGfj3KUfBhQQ8AJjJBQdw="),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "jLyv+x9ZcrQQJmWqIgL4kd4UdqsYO6fNIUBHGnahdV/TaLERtp3lHjsIewO3Kl4vyNI33ZEy7RmZQiqRyJwOn0VQuW/KXKHyKs4Z0FYn94kGsawcBDE2cuYYwk//Ayny9cU2rGyAKG59WokujZI="
                  )
               );
               var3 = var3.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("vHykT8XlwyAKeY+7b5Yi7JS/MBV6droLonVmmSsMBsrC81a/pLb2EefmxOxHbBsdWHduM2V+Z3oocPa8o9Op/wtF"),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "sSv4IS7waLgnH0nFILlVkCHTvfOKE8aUZ9QGP4KFkanf9vcMh3+PZuOoiIPGI47OOmv/mf+QiEDlRydLbkqQkTHg1DeiULYxhZqGvY9MsKtWnsBBbDoxCtYJRCQTOSMjfQChcPZx1/X1Ow=="
                  )
               );
            }

            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var4) {
            this.plugin.getLogger().warning(Xqedii_3ujm1jl1i6qi1(var4.getMessage()));
         }
      }
   }

   public String ensureEditorMenuButtonTextId(String var1, String var2, String var3) {
      if (((1313379432 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var1.isBlank() || var2 == null || var2.isBlank() || var3 == null || var3.isBlank()) {
         return var1;
      } else if (var1.contains(Xqedii_1uiz7gcobas0n(var3))) {
         return var1;
      } else {
         int var4 = var1.indexOf(Xqedii_39otv7bikdz89(var2));
         if (var4 < 0) {
            return var1;
         } else {
            int var5 = var1.lastIndexOf(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("pVnJsvxRDWKhl7UdhtEyv5foVUozqqel6iOIa/KycVbdHvBAQMEz73dHI8prFYl/zP6uvw=="), var4);
            if (var5 < 0) {
               return var1;
            } else {
               int var6 = var1.lastIndexOf(Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-520657134, 20), var5);
               var6 = var6 < 0 ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1570524033, 30) : var6 + Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 8);
               String var7 = var1.substring(var6, var5);
               int var8 = var1.indexOf(Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1779638588, 29), var5);
               if (var8 < 0) {
                  var8 = var1.length();
               }

               String var9 = this.detectLineSeparator(var1);
               int var10 = var8 < var1.length() ? var8 + Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 23) : var8;
               String var11 = Xqedii_2n68napi9kqha(var7, var3, var9);
               return Xqedii_wjg40xafzewn(var1.substring(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 29), var10), var11, var1.substring(var10));
            }
         }
      }
   }

   public String ensureEditorMenuButtonHitbox(String var1, String var2, String var3) {
      if ((1933071652 ^ 1933071652) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var1.isBlank() || var2 == null || var2.isBlank() || var3 == null || var3.isBlank()) {
         return var1;
      } else if (var1.contains(Xqedii_2hu9awjmkipln(var3))) {
         return var1;
      } else {
         int var4 = var1.indexOf(Xqedii_2cqczlm9g6ube(var2));
         if (var4 < 0) {
            return var1;
         } else {
            int var5 = var1.lastIndexOf(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("pVnJsvxRDWKhl7UdhtEyv5foVUozqqel6iOIa/KycVbdHvBAQMEz73dHI8prFYl/zP6uvw=="), var4);
            if (var5 < 0) {
               return var1;
            } else {
               int var6 = var1.lastIndexOf(Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-497279252, 9), var5);
               var6 = var6 < 0 ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-836386642, 5) : var6 + Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 22);
               String var7 = var1.substring(var6, var5);
               String var8 = Xqedii_nypqmlc1om7w(var7);
               int var9 = var1.indexOf(Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242760507, 10), var4);
               int var10 = var9 >= 0 ? Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(384486447, 21) : Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1024365396, 9);
               if (var10 == 0) {
                  var9 = var1.length();
               }

               String var11 = this.detectLineSeparator(var1);
               String var12 = Xqedii_1btsh5xl39xaj(
                  var7,
                  var11,
                  var8,
                  var3,
                  var11,
                  var8,
                  var11,
                  var8,
                  var11,
                  var8,
                  var11,
                  var8,
                  var11,
                  var8,
                  var11,
                  var8,
                  var11,
                  var8,
                  var11,
                  var8,
                  var11,
                  var8,
                  var11,
                  var8,
                  var11,
                  var8
               );
               int var13 = var10 != 0 ? var9 + Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-711434707, 30) : var9;
               return Xqedii_7dzilksqgoai(var1.substring(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 15), var13), var12, var1.substring(var13));
            }
         }
      }
   }

   public String detectLineSeparator(String var1) {
      if ((-2123490131 ^ -2123490131) != 0) {
         throw new IllegalStateException();
      } else {
         return var1 != null && var1.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("YyMRV1uP2jsZxADGwlrAbki5M5ukpBLmhRCjJKJkiOw="))
            ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("YyMRV1uP2jsZxADGwlrAbki5M5ukpBLmhRCjJKJkiOw=")
            : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Cpcxs10et5jAh9jF96VeYSUuoW/36H9KnlgAhDmG");
      }
   }

   public String removeEditorPanelBlock(String var1, String var2) {
      if (((1689303938 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         ArrayList var3 = new ArrayList<>(
            List.of(
               var1.split(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("00gIatwATdymk3rZEtgic75wHVJXGpXTMltML+WBkZrNdg=="), Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1466372833, 28)
               )
            )
         );
         int var4 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(497284371, 9);

         for (int var5 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-892588266, 29); var5 < var3.size() && var4 < 0; var5++) {
            if (((String)var3.get(var5)).trim().equals(Xqedii_2mhuf7n6702ap(var2))) {
               var4 = var5;
            }
         }

         if (var4 < 0) {
            return var1;
         } else {
            int var9 = Xqedii_b44dtge((String)var3.get(var4)) - Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921857, 1);
            int var6 = var4 - Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1955240425, 20);

            while (var6 >= 0 && (((String)var3.get(var6)).isBlank() || Xqedii_b44dtge((String)var3.get(var6)) > var9)) {
               var6--;
            }

            if (var6 >= 0
               && Xqedii_b44dtge((String)var3.get(var6)) == var9
               && ((String)var3.get(var6)).trim().startsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("1r7RpZsfzGlN8olhryOZPWKpTb4wnC4sPhUlbpMb1mA="))) {
               int var7 = var1.endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Cpcxs10et5jAh9jF96VeYSUuoW/36H9KnlgAhDmG"))
                  ? var3.size() - Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1672773347, 6)
                  : var3.size();
               int var8 = var4 + Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1898841206, 8);

               while (var8 < var7 && (((String)var3.get(var8)).isBlank() || Xqedii_b44dtge((String)var3.get(var8)) > var9)) {
                  var8++;
               }

               var3.subList(var6, var8).clear();
               return String.join(this.detectLineSeparator(var1), var3);
            } else {
               return var1;
            }
         }
      } else {
         return var1;
      }
   }

   public static int Xqedii_b44dtge(String var0) {
      if ((-697211598 * -697211598 + -697211598 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         int var1 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(733186416, 27);

         while (var1 < var0.length() && var0.charAt(var1) == Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1989071949, 11)) {
            var1++;
         }

         return var1;
      }
   }

   public List<List<String>> parseGlyphMatrixRows(String var1) {
      if (((-322645273 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1580062104, 14)];
         var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1449228470, 0)] = var1;
         String var2 = this.firstNonBlank(var10001)
            .replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("YyMRV1uP2jsZxADGwlrAbki5M5ukpBLmhRCjJKJkiOw="),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Cpcxs10et5jAh9jF96VeYSUuoW/36H9KnlgAhDmG")
            )
            .replace((char)Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1255350587, 20), (char)Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1396510336, 1));
         if (var2.isBlank()) {
            return Collections.emptyList();
         } else {
            ArrayList var3 = new ArrayList();
            ArrayList var4 = new ArrayList();
            String[] var5 = var2.split(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Cpcxs10et5jAh9jF96VeYSUuoW/36H9KnlgAhDmG"), Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(596921860, 25)
            );
            String[] var6 = var5;
            int var7 = var5.length;

            for (int var8 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 21); var8 < var7; var8++) {
               String var9 = var6[var8];
               if (var9 != null && !var9.isEmpty()) {
                  ArrayList var10 = new ArrayList();
                  int var11 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 5);
                  int var12 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 2);

                  while (var12 < var9.length()) {
                     int var13 = var9.codePointAt(var12);
                     String var14 = new String(Character.toChars(var13));
                     var10.add(var14);
                     if (!var14.isBlank()) {
                        var11 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1738658347, 6);
                     }

                     var12 += Character.charCount(var13);
                  }

                  if (!var10.isEmpty()) {
                     var3.add(var10);
                     var4.add(Boolean.valueOf((var11 != 0)));
                  }
               }
            }

            if (var3.isEmpty()) {
               return var3;
            } else {
               int var15 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 15);

               while (var15 < var4.size() && !Boolean.TRUE.equals(var4.get(var15))) {
                  var15++;
               }

               if (var15 >= var4.size()) {
                  return Collections.emptyList();
               } else {
                  var7 = var4.size() - Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 25);

                  while (var7 >= var15 && !Boolean.TRUE.equals(var4.get(var7))) {
                     var7--;
                  }

                  return var15 == 0 && var7 == var3.size() - Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-1670663685, 30)
                     ? var3
                     : new ArrayList<>(var3.subList(var15, var7 + Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(817650603, 7)));
               }
            }
         }
      }
   }

   public int resolveGlyphMatrixMaxColumns(List<List<String>> var1) {
      if ((850133109 * 850133109 - 850133109 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         int var2 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1282838485, 3);

         for (List var4 : var1) {
            if (var4 != null && !var4.isEmpty()) {
               var2 = Math.max(var2, var4.size());
            }
         }

         return var2;
      } else {
         return Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-244536126, 17);
      }
   }

   public double normalizeImageLayoutOffsetY(double var1, double var3) {
      if (((-2048152176 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (!Double.isFinite(var1)) {
         return Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-4965594660555198464L, 14));
      } else {
         double var5 = Math.abs(var1);
         double var7 = Math.abs(var3);
         if (!(var5 <= Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-6081702782614296931L, 5)))
            && !(var7 <= Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-7489514355787344119L, 37)))) {
            double var9 = var5 / var7;
            double var11 = Math.rint(var9);
            return var11 >= Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893246107921395L, 36))
                  && Math.abs(var9 - var11) <= Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-4547420895239656073L, 33))
               ? Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(5592344212837654782L, 14))
               : var1;
         } else {
            return var1;
         }
      }
   }

   public String extractUiImageGlyphToken(String var1) {
      if ((1917515133 * 1917515133 + 1917515133 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-320709624, 1)];
         var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1153919230, 31)] = var1;
         String var2 = this.firstNonBlank(var10001)
            .replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("YyMRV1uP2jsZxADGwlrAbki5M5ukpBLmhRCjJKJkiOw="),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Cpcxs10et5jAh9jF96VeYSUuoW/36H9KnlgAhDmG")
            )
            .replace((char)Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596942341, 12), (char)Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1250107707, 20))
            .trim();
         if (var2.isBlank()) {
            return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
         } else {
            String var3 = var2.toLowerCase(Locale.ROOT);
            int var4 = var3.lastIndexOf(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Mk4aB45IjXxFDXUlRAwhS6+/GSW7oHNFVhNpHRZkXQUCw6PeEKKYIuAsyHjgBoSxMCq421qZ67Y0YA=="));
            if (var4 >= 0) {
               var2 = var2.substring(
                     var4 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Mk4aB45IjXxFDXUlRAwhS6+/GSW7oHNFVhNpHRZkXQUCw6PeEKKYIuAsyHjgBoSxMCq421qZ67Y0YA==").length()
                  )
                  .trim();
            }

            int var5 = var2.indexOf(Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-836386322, 5));
            if (var5 >= 0) {
               var2 = var2.substring(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 15), var5).trim();
            }

            if (var2.isBlank()) {
               return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
            } else if (var2.toLowerCase(Locale.ROOT).startsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("x9d+aLXJst8JJAQrlqy/80aL4lRqrXO5yc5RVs3wNHGnh73vqXI="))) {
               return var2;
            } else {
               int var6 = var2.codePointAt(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 26));
               return new String(Character.toChars(var6));
            }
         }
      }
   }

   public String deriveImageBlockName(Map<String, Object> var1) {
      if ((-925338555 ^ -925338555) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 11)];
         var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-320709622, 1)] = this.stringValue(
            this.readMapPathValue(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ummW4uCQefOrIoCjc2WM6svA2tPPvwrXJKxwHKlJxLoPdIO6ycs="))
         );
         String var2 = this.firstNonBlank(var10001).trim();
         if (!var2.isBlank()) {
            return var2;
         } else {
            var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(633376462, 13)];
            var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-994568743, 10)] = this.stringValue(
               this.readMapPathValue(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Eaqx4dmxZEVb7SW51kr176NkLI4AG9nq1i0WyIXUMoayER3i"))
            );
            String var3 = this.firstNonBlank(var10001).trim();
            if (!var3.isBlank()) {
               return var3;
            } else {
               var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(772044401, 16)];
               var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 5)] = this.stringValue(
                  this.readMapPathValue(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Xrrvs81HHFD0cotBejhfhuT0G1swAMAermn9c5HkW6Q="))
               );
               String var4 = this.firstNonBlank(var10001).trim();
               if (var4.toLowerCase(Locale.ROOT).startsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("RE/bqmL+20Bn0VmggC1Yb7nvkD6B4dWoFAP/Y+NqrYbwD7zK"))) {
                  var4 = var4.substring(Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1249059131, 21));
               }

               return var4;
            }
         }
      } else {
         return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
      }
   }

   public List<Map<String, Object>> compactImageBlocksForSave(List<Map<String, Object>> var1) {
      if ((751730504 * 751730504 - 751730504 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         ArrayList var2 = new ArrayList();

         for (Map var4 : var1) {
            if (var4 != null && !var4.isEmpty()) {
               var2.add(this.compactImageBlockForSave(this.deepCopyMap(var4)));
            }
         }

         return var2;
      } else {
         return Collections.emptyList();
      }
   }

   public Map<String, Object> compactImageBlockForSave(Map<String, Object> var1) {
      if (((1225367227 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         if (var1.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("UVREse9krg9Ztxi40WBaLWzZ9MMEG3xGE4ValqIoO/Iu7YfshoCwCrjkfpI=")) instanceof List var3
            && !var3.isEmpty()) {
            ArrayList var4 = new ArrayList();

            for (Object var6 : var3) {
               Map var7 = this.toStringObjectMap(var6);
               if (var7 != null && !var7.isEmpty()) {
                  var4.add(this.compactImageBlockForSave(this.deepCopyMap(var7)));
               }
            }

            var1.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("UVREse9krg9Ztxi40WBaLWzZ9MMEG3xGE4ValqIoO/Iu7YfshoCwCrjkfpI="), var4);
         }

         Map var8 = this.buildCompactImageBlockFromLegacy(var1);
         return var8 == null ? var1 : var8;
      } else {
         return var1;
      }
   }

   public Map<String, Object> buildCompactImageBlockFromLegacy(Map<String, Object> var1) {
      if ((1898643755 * (1898643755 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty() && this.isImageGroupRoot(var1)) {
         if (var1.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("UVREse9krg9Ztxi40WBaLWzZ9MMEG3xGE4ValqIoO/Iu7YfshoCwCrjkfpI=")) instanceof List var3
            && !var3.isEmpty()) {
            String var4 = this.buildGlyphMatrixFromLegacyChildren(var3);
            if (var4.isBlank()) {
               return null;
            } else {
               ArrayList var5 = new ArrayList();

               for (Object var7 : var3) {
                  Map var8 = this.toStringObjectMap(var7);
                  if (var8 != null && !var8.isEmpty()) {
                     String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921862, 0)];
                     var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1635300950, 8)] = this.stringValue(
                        this.readMapPathValue(var8, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u6bFgC+JuJg6jwfnb8XcyG0rItjOwDbvVzEPAU72DfOh1JIG"))
                     );
                     String var9 = this.firstNonBlank(var10001).trim();
                     if (this.extractUiImageGlyphToken(var9).isBlank()) {
                        var5.add(this.deepCopyMap(var8));
                     }
                  }
               }

               Map var10 = this.deepCopyMap(var1);
               var10.put(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("gbUJ1zxEZwa5Yx4VEpUowsMsGAWnuMEPVXkWG3e79XHTrXiF"),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ummW4uCQefOrIoCjc2WM6svA2tPPvwrXJKxwHKlJxLoPdIO6ycs=")
               );
               var10.remove(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("MQoiGbqUnZCFpk5iBsi88KzIYYvh1UHubYBFUn5NSibYAtb+tO963Mfzq9e7oMd7vA1v5w=="));
               var10.remove(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("kj/CSq8d8CDLrBkGHgGHWEZpDm+S5w7BreaFzQzKSytg8SY8HYDp/3zMcgdA3KmTGKI="));
               var10.remove(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("7QWtD9WFqA9TC+qHbfrDr8O4LgBaP9sHoNa9T79YjxQHyrH0r0Hs0w=="));
               var10.remove(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("UVREse9krg9Ztxi40WBaLWzZ9MMEG3xGE4ValqIoO/Iu7YfshoCwCrjkfpI="));
               var10.remove(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "Xrxnn4jLLGtxw3543YPQNLGHYv5z53WdIJQsNNNNbDAoWqKPMKpZT2OY1bhT/+54Q4ZDUbvci2FsYU00+bAROrrQhvRLmYEAZIbU4seZzkFde8AZ/x+gTuPnMcVh1U5Ulr0="
                  )
               );
               String var11 = this.deriveImageBlockName(var1);
               if (!var11.isBlank()) {
                  var10.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ummW4uCQefOrIoCjc2WM6svA2tPPvwrXJKxwHKlJxLoPdIO6ycs="), var11);
               }

               this.applyCompactImageLayoutMetadata(var10, var3);
               if (!var5.isEmpty()) {
                  var10.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("UVREse9krg9Ztxi40WBaLWzZ9MMEG3xGE4ValqIoO/Iu7YfshoCwCrjkfpI="), var5);
               }

               return var10;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   public void applyCompactImageLayoutMetadata(Map<String, Object> var1, List<?> var2) {
      if ((-31226435 * (-31226435 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty() && var2 != null && !var2.isEmpty()) {
         double var3 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-4026348137807490180L, 59));
         double var5 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(8069811095789105462L, 24));
         double var7 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(-2554692315874874381L, 1));
         double var9 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(9062300163270165930L, 36));
         long var11 = Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-2831269137022550011L, 17);
         TreeMap<Long, List<Double>> var13 = new TreeMap<>();

         for (Object var15 : var2) {
            Map var16 = this.toStringObjectMap(var15);
            if (var16 != null && !var16.isEmpty()) {
               String var17 = this.extractUiImageGlyphToken(
                  this.stringValue(this.readMapPathValue(var16, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u6bFgC+JuJg6jwfnb8XcyG0rItjOwDbvVzEPAU72DfOh1JIG")))
               );
               if (!var17.isBlank()) {
                  double var18 = this.parseDouble(
                     this.readMapPathValue(var16, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Bb1stb4HQ6HihYXcPSol4rQI6RS61HINzXliyJVegflT2kp7VwZOOLeLV+BYMB4M")),
                     this.parseDouble(
                        this.readMapPathValue(var16, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("r/G5c9QLrSfSj2hOM0lnfxyrHNJzXLyGEj8ZnYnG")),
                        Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(7421892221127900947L, 20))
                     )
                  );
                  double var20 = this.parseDouble(
                     this.readMapPathValue(var16, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("H4boOHXP9f3KMY2tAMho2IhW/7xykghhkCdayf0lgv1ntl3nezSe0XY9cRZ/4fMA")),
                     this.parseDouble(
                        this.readMapPathValue(var16, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("aY9NJhHvVgN3vcQ5Po4KwYUAwkMwXAjYDseqfM6E")),
                        Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(1398086053209411647L, 12))
                     )
                  );
                  if (Double.isFinite(var18) && Double.isFinite(var20)) {
                     double var22 = Math.abs(
                        this.parseDouble(
                           this.readMapPathValue(var16, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u2uvOjAqt0jIbg10PMdewJSqVEHCbdZqMUeQP+TthXkQFA0phBEWU0ELj9tSIF3f")),
                           this.parseDouble(
                              this.readMapPathValue(var16, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("S3kypXDHnXjWCOPx7JIbhp+C+DZ75n4lGC7SqIJB2SsypMkAQGY=")),
                              Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(4692838944460684530L, 5))
                           )
                        )
                     );
                     double var24 = Math.abs(
                        this.parseDouble(
                           this.readMapPathValue(
                              var16, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("vTDTfgcyXN+4Nu4RRZjf8UxPLjjr9KqRI1a1RT2HnGSTjc8S1npvrB3uwzQy5aT5qBU=")
                           ),
                           this.parseDouble(
                              this.readMapPathValue(var16, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8GWrnWfnnEO6nW4nK/mTpZKpZCKFZc4oviEoHTqZhlBPfomPstDNCw==")),
                              Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(2637763574528199906L, 7))
                           )
                        )
                     );
                     long var26 = Math.round(var20 * Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-81847865113822409L, 31)));
                     var13.computeIfAbsent(var26, var0 -> {
                        if (((693861362 | 1) & 1) == 0) {
                           throw new IllegalStateException();
                        } else {
                           return new ArrayList<>();
                        }
                     }).add(var18);
                     if (!Double.isFinite(var5)
                        || var20 < var5
                        || Math.abs(var20 - var5) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-6182118936688830164L, 24)) && var18 < var3) {
                        var3 = var18;
                        var5 = var20;
                        var7 = Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-15490787828778037L, 19)), var22);
                        var9 = Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(-5955308742613345658L, 39)), var24);
                        var11 = var26;
                     }
                  }
               }
            }
         }

         if (Double.isFinite(var3) && Double.isFinite(var5)) {
            double var28 = var7 / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893399300680691L, 39));
            double var30 = var9 / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-4469816931722297917L, 52));
            if (!Double.isFinite(var28) || Math.abs(var28) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-4124431473477888334L, 56))) {
               var28 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(8041674016054836666L, 48));
            }

            if (!Double.isFinite(var30) || Math.abs(var30) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(1773128076259333686L, 3))) {
               var30 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032672740123L, 27));
            }

            var28 = Math.abs(var28);
            var30 = Math.abs(var30);
            double var32 = this.normalizeImageLayoutOffsetY(
               var5, Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-2363394942088250508L, 49)) * var30
            );
            int var33 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 26);
            List<Double> var21 = var13.get(var11);
            if (var21 != null && var21.size() > Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(384486447, 21)) {
               var21.sort(Double::compareTo);
               double var34 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(3704185266263272435L, 62)) * var28;
               double var35 = var3 + (var21.size() - Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596930053, 13)) * var34;
               double var36 = (Double)var21.get(var21.size() - Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(768972894, 22));
               var33 = Math.abs(var36 - var35) > Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-7825285983645361091L, 12))
                  ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1898841206, 8)
                  : Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 10);
            }

            if (Math.abs(var28 - Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-3680775566509025L, 0)))
               > Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(3444995016614741890L, 50))) {
               var1.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("CCWBGWQn4usWBKL7pBgbRbXDn17kskNCXfP5IT9UYbqd5mJ19/VXKDxcJ22WctROdahhottT"), var28);
            } else {
               var1.remove(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("CCWBGWQn4usWBKL7pBgbRbXDn17kskNCXfP5IT9UYbqd5mJ19/VXKDxcJ22WctROdahhottT"));
               var1.remove(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("YmDX24FxUzwFw+/+BDcCAfF5smJD7aFvINsFCQgiRF7Kn3X1J8eW1vJzkdxWeFmzJF4="));
            }

            if (Math.abs(var30 - Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-230048472906815L, 60)))
               > Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-4124431473477888334L, 56))) {
               var1.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("aFZhimdmj7rVQ6Vp+hKWXaaCXUtIvnbqnogNW8qGq2+/+vcL3miX7/jist66EhqQK1B5Wrye"), var30);
            } else {
               var1.remove(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("aFZhimdmj7rVQ6Vp+hKWXaaCXUtIvnbqnogNW8qGq2+/+vcL3miX7/jist66EhqQK1B5Wrye"));
               var1.remove(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("cWSJXS/vwZJmvdWRTVQx9ybOxt+TNHGpyO0pqf6sLnz7ou+CPCNNqbHBO3OYj6YMbqc="));
            }

            if (Math.abs(var3) > Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-2395876739806343877L, 55))) {
               var1.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ACsveKgTUWkEhi5z1eifJRXF2b4BCWtTZrD8A3yIw4ChLWhfwEfhAotdrDkbNpAAYln2sLQ0I1I="), var3);
            } else {
               var1.remove(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ACsveKgTUWkEhi5z1eifJRXF2b4BCWtTZrD8A3yIw4ChLWhfwEfhAotdrDkbNpAAYln2sLQ0I1I="));
               var1.remove(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("pulGqQxS5eXngSLrV0QLerWUw1MgyOKZi9A+R//aTWC90pJcsmY8HAEyhegiP6TaCcbT3A=="));
            }

            if (Math.abs(var32) > Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(6346873771675482589L, 46))) {
               var1.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("6V3g6nP5FeKb52+EC0wwO/zevGCWcwjXevqb9TouUMJTp3TnQkEk+ejJw+Rpy8RTjBPoRAYhFhM="), var32);
            } else {
               var1.remove(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("6V3g6nP5FeKb52+EC0wwO/zevGCWcwjXevqb9TouUMJTp3TnQkEk+ejJw+Rpy8RTjBPoRAYhFhM="));
               var1.remove(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("OX9yFo8E9e+/Efq7LQV+ajoCPNipvVN4pfEwqQAAD0QlfoJlU66f1A0d0nQ7u+3n9d5OAw=="));
            }

            if (var33 != 0) {
               var1.put(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "8L/juUOq0dV+vvX9dtf41Dl43B7JaBHUkMZ+5duluhL7EI4am88ETqIEtg+1lIOOz8WcaBJ2ZsKv699TG++wyFrFGQaKfl3TMZXEhDW5"
                  ),
                  Boolean.valueOf((Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 13) != 0))
               );
            } else {
               var1.remove(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "8L/juUOq0dV+vvX9dtf41Dl43B7JaBHUkMZ+5duluhL7EI4am88ETqIEtg+1lIOOz8WcaBJ2ZsKv699TG++wyFrFGQaKfl3TMZXEhDW5"
                  )
               );
               var1.remove(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ecbkJU6AvnS9koIuMOcX0rQhhgK+EiFFEOACE7NAoj646oEIAURS5sl+0ab5ZWXf5NO0oT5EdmZFQka544Qpg6xMKdbNg7KB")
               );
            }
         }
      }
   }

   public String buildGlyphMatrixFromLegacyChildren(List<?> var1) {
      if ((-1012350547 ^ -1012350547) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         TreeMap<Long, List<Object[]>> var2 = new TreeMap<>();

         for (Object var4 : var1) {
            Map var5 = this.toStringObjectMap(var4);
            if (var5 != null && !var5.isEmpty()) {
               String var6 = this.extractUiImageGlyphToken(
                  this.stringValue(this.readMapPathValue(var5, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u6bFgC+JuJg6jwfnb8XcyG0rItjOwDbvVzEPAU72DfOh1JIG")))
               );
               if (!var6.isBlank()) {
                  double var7 = this.parseDouble(
                     this.readMapPathValue(var5, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Bb1stb4HQ6HihYXcPSol4rQI6RS61HINzXliyJVegflT2kp7VwZOOLeLV+BYMB4M")),
                     this.parseDouble(
                        this.readMapPathValue(var5, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("r/G5c9QLrSfSj2hOM0lnfxyrHNJzXLyGEj8ZnYnG")),
                        Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-3738047392911600772L, 59))
                     )
                  );
                  double var9 = this.parseDouble(
                     this.readMapPathValue(var5, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("H4boOHXP9f3KMY2tAMho2IhW/7xykghhkCdayf0lgv1ntl3nezSe0XY9cRZ/4fMA")),
                     this.parseDouble(
                        this.readMapPathValue(var5, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("aY9NJhHvVgN3vcQ5Po4KwYUAwkMwXAjYDseqfM6E")),
                        Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-2506309534069606402L, 39))
                     )
                  );
                  long var11 = Math.round(var9 * Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(638039633271439864L, 4)));
                  List<Object[]> var13 = var2.computeIfAbsent(var11, var0 -> {
                     if ((-1189404862 * -1189404862 + -1189404862 & 1) != 0) {
                        throw new IllegalStateException();
                     } else {
                        return new ArrayList();
                     }
                  });
                  Object[] var10001 = new Object[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-994566695, 10)];
                  var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 14)] = var7;
                  var10001[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 13)] = var6;
                  var13.add(var10001);
               }
            }
         }

         if (var2.isEmpty()) {
            return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
         } else {
            StringBuilder var14 = new StringBuilder();
            int var15 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 28);

            for (List<Object[]> var17 : var2.values()) {

               if (var17 != null && !var17.isEmpty()) {
                  var17.sort(
                     (Object[] var1x, Object[] var2x) -> {
                        if ((1260746725 * 1260746725 + 1260746725 & 1) != 0) {
                           throw new IllegalStateException();
                        } else {
                           return Double.compare(
                              this.parseDouble(
                                 var1x[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1062285787, 21)],
                                 Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773370651L, 53))
                              ),
                              this.parseDouble(
                                 var2x[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-489072251, 18)],
                                 Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-3080776738624497822L, 27))
                              )
                           );
                        }
                     }
                  );
                  StringBuilder var18 = new StringBuilder();

                  for (Object[] var19 : (Iterable<Object[]>) (var17)) {

                     String var10 = var19[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 20)] == null
                        ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==")
                        : var19[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 18)].toString();
                     if (!var10.isBlank()) {
                        var18.append(var10);
                     }
                  }

                  if (var18.length() != 0) {
                     if (var15 > 0) {
                        var14.append((char)Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1846747451, 26));
                     }

                     var14.append((CharSequence)var18);
                     var15++;
                  }
               }
            }

            return var14.toString();
         }
      } else {
         return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
      }
   }

   public void applyImageGroupFrameOffsetToBlock(Map<String, Object> var1, double var2) {
      if ((36441221 * (36441221 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         if (this.isImageGroupRoot(var1)) {
            double var4 = Math.abs(
               this.parseDouble(
                  this.readMapPathValue(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("vTDTfgcyXN+4Nu4RRZjf8UxPLjjr9KqRI1a1RT2HnGSTjc8S1npvrB3uwzQy5aT5qBU=")),
                  this.parseDouble(
                     this.readMapPathValue(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8GWrnWfnnEO6nW4nK/mTpZKpZCKFZc4oviEoHTqZhlBPfomPstDNCw==")),
                     Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-10230983206923418L, 28))
                  )
               )
            );
            if (!Double.isFinite(var4) || var4 <= Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773370651L, 34))) {
               var4 = this.resolveImageSizeDimension(
                  var1,
                  (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 19) != 0),
                  Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556822686288030707L, 48))
               );
            }

            double var6 = this.resolveGroupOutlineToRootYOffset(var1, var4);
            if (Double.isFinite(var6) && Math.abs(var6) > Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(7092512305037334744L, 5))) {
               double var8 = this.readRawTargetLocalX(var1);
               double var10 = this.readRawTargetLocalY(var1);
               double var12 = var6 * var2;
               this.setRawTargetLocalPosition(var1, var8, var10 + var12);
               this.applyImageLayoutOffsetToPositionRow(var1, Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-4614243764229118759L, 26)), var12);
            }
         }

         if (var1.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("UVREse9krg9Ztxi40WBaLWzZ9MMEG3xGE4ValqIoO/Iu7YfshoCwCrjkfpI=")) instanceof List var5
            && !var5.isEmpty()) {
            for (Object var7 : var5) {
               if (var7 instanceof Map var16) {
                  this.applyImageGroupFrameOffsetToBlock(var16, var2);
               }
            }
         }
      }
   }

   public void applyImageLayoutOffsetToBlocks(List<Map<String, Object>> var1, double var2, double var4) {
      if ((1967418235 ^ 1967418235) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty() && (Double.isFinite(var2) || Double.isFinite(var4))) {
         for (Map var7 : var1) {
            this.applyImageLayoutOffsetToBlock(var7, var2, var4);
         }
      }
   }

   public void applyImageLayoutOffsetToBlock(Map<String, Object> var1, double var2, double var4) {
      if (((1501068596 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         if (this.isImageGroupRoot(var1)) {
            double var6 = this.readRawTargetLocalX(var1);
            double var8 = this.readRawTargetLocalY(var1);
            this.setRawTargetLocalPosition(var1, var6 + var2, var8 + var4);
            this.applyImageLayoutOffsetToPositionRow(var1, var2, var4);
         }

         if (var1.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("UVREse9krg9Ztxi40WBaLWzZ9MMEG3xGE4ValqIoO/Iu7YfshoCwCrjkfpI=")) instanceof List var7
            && !var7.isEmpty()) {
            for (Object var9 : var7) {
               if (var9 instanceof Map var10) {
                  this.applyImageLayoutOffsetToBlock(var10, var2, var4);
               }
            }
         }
      }
   }

   public void applyImageLayoutOffsetToPositionRow(Map<String, Object> var1, double var2, double var4) {
      if (((-1000249450 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Map var6 = this.readAnimationTimelineRowMap(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("c63EUYU/P5Jwb4wYiYNV3LxmMpg79cbbiiNmLYmSlJtMvT5V3PaHiEL7cLo="));
         if (var6 != null && !var6.isEmpty()) {
            for (Entry var8 : (Iterable<Entry>) (var6.entrySet())) {

               if (var8 != null && var8.getValue() instanceof Map var9) {
                  double var11 = this.parseDouble(
                     var9.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("r/G5c9QLrSfSj2hOM0lnfxyrHNJzXLyGEj8ZnYnG")),
                     Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(6740574706577176575L, 13))
                  );
                  double var13 = this.parseDouble(
                     var9.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("aY9NJhHvVgN3vcQ5Po4KwYUAwkMwXAjYDseqfM6E")),
                     Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773354271L, 15))
                  );
                  if (Double.isFinite(var11)) {
                     var9.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("r/G5c9QLrSfSj2hOM0lnfxyrHNJzXLyGEj8ZnYnG"), var11 + var2);
                  }

                  if (Double.isFinite(var13)) {
                     var9.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("aY9NJhHvVgN3vcQ5Po4KwYUAwkMwXAjYDseqfM6E"), var13 + var4);
                  }
               }
            }
         }
      }
   }

   public Map<String, Object> expandCompactImageBlock(Map<String, Object> var1) {
      if (((-339663607 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         if (var1.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("UVREse9krg9Ztxi40WBaLWzZ9MMEG3xGE4ValqIoO/Iu7YfshoCwCrjkfpI=")) instanceof List var3
            && !var3.isEmpty()) {
            ArrayList var4 = new ArrayList();

            for (Object var6 : var3) {
               Map var7 = this.toStringObjectMap(var6);
               if (var7 != null && !var7.isEmpty()) {
                  var4.add(this.expandCompactImageBlock(var7));
               }
            }

            var1.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("UVREse9krg9Ztxi40WBaLWzZ9MMEG3xGE4ValqIoO/Iu7YfshoCwCrjkfpI="), var4);
         }

         if (!this.isCompactImageBlock(var1)) {
            return var1;
         } else {
            Map var8 = this.buildLegacyImageBlockFromCompact(var1);
            return var8 == null ? var1 : var8;
         }
      } else {
         return var1;
      }
   }

   public boolean isCompactImageBlock(Map<String, Object> var1) {
      if (((-1470339683 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1422869413, 31)];
         var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1235852936, 24)] = this.stringValue(
            this.readMapPathValue(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("gbUJ1zxEZwa5Yx4VEpUowsMsGAWnuMEPVXkWG3e79XHTrXiF"))
         );
         String var2 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
         return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ummW4uCQefOrIoCjc2WM6svA2tPPvwrXJKxwHKlJxLoPdIO6ycs=").equals(var2);
      } else {
         return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1635300950, 8) != 0);
      }
   }

   public List<EditorImageDragPart.RuntimeFrameAnimationTile> collectRuntimeFrameAnimationTiles(EditorSession var1) {
      if (((1021491558 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.renderBlocks != null && !var1.renderBlocks.isEmpty()) {
         ArrayList var2 = new ArrayList();
         HashMap var3 = new HashMap();
         int var4 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-497284372, 9);

         for (Map var6 : var1.renderBlocks) {
            var4++;
            if (var6 != null && !var6.isEmpty()) {
               String var7 = this.resolveElementId(var6, var4, var3);
               if (var6.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("0SXx9EHjlauZcvJSW4uXA7K4GI9cWCgqov8iW4NX6CAmpoAszbEA3GUiVFzDJRyK+zGJRzMU4xX6xXgFhk+mlw==")) instanceof List var9
                  && var9.size() >= Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(197509811, 11)) {
                  ArrayList var10 = new ArrayList(var9.size());

                  for (Object var12 : var9) {
                     String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(316688231, 12)];
                     var10001[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 11)] = this.stringValue(var12);
                     String var13 = this.firstNonBlank(var10001);
                     if (var13.isBlank()) {
                        var10.clear();
                        break;
                     }

                     var10.add(var13);
                  }

                  if (var10.size() >= Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1672773155, 6)) {
                     int var14 = this.resolveRuntimeFrameTileIntervalMs(var6);
                     boolean var15 = this.parseBooleanFlag(
                        var6.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("spELMeXIdaRIUymON0L2UsCKjhpVpjtE5RfsyMrTLC7jm4DSxk/dt9+FscFzNM5R2VHKT+4PWnRvwk1Z")),
                        (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-711434707, 30) != 0)
                     );
                     var2.add(new EditorImageDragPart.RuntimeFrameAnimationTile(this.runtimeElementId(var1, var7), var10, var14, var15));
                  }
               }
            }
         }

         return var2;
      } else {
         return Collections.emptyList();
      }
   }

   public int resolveRuntimeFrameTileIntervalMs(Map<String, Object> var1) {
      if ((-330138442 * -330138442 + -330138442 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return (int)Math.max(
            Xqedii_p92kfr8.Xqedii_274j413le3omf(639423014345982742L, 62),
            Math.round(
               this.parseDouble(
                  var1.get(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("/v3miXHUABe1LdatKGlLw8alOCwoqJOD5mEltiTdfKlFcJnCWoAhwSJahbjqTQ0SOvq4FNNozXIBnONenJttRBpyBydHyAxlQvM=")
                  ),
                  Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(-6666478775664145449L, 15))
               )
            )
         );
      }
   }

   public String buildRuntimeFrameAnimationSignature(EditorSession var1) {
      if ((398755684 ^ 398755684) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.renderBlocks != null && !var1.renderBlocks.isEmpty()) {
         StringBuilder var2 = new StringBuilder();
         HashMap var3 = new HashMap();
         int var4 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(817650475, 7);

         for (Map var6 : var1.renderBlocks) {
            var4++;
            if (var6 != null && !var6.isEmpty()) {
               String var7 = this.resolveElementId(var6, var4, var3);
               if (var6.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("0SXx9EHjlauZcvJSW4uXA7K4GI9cWCgqov8iW4NX6CAmpoAszbEA3GUiVFzDJRyK+zGJRzMU4xX6xXgFhk+mlw==")) instanceof List var9
                  && var9.size() >= Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-1670663685, 29)) {
                  var2.append(this.runtimeElementId(var1, var7))
                     .append((char)Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1153919201, 31))
                     .append(var9.size())
                     .append((char)Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1064726830, 24))
                     .append(this.resolveRuntimeFrameTileIntervalMs(var6))
                     .append((char)Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1738654699, 6))
                     .append(
                        this.parseBooleanFlag(
                           var6.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("spELMeXIdaRIUymON0L2UsCKjhpVpjtE5RfsyMrTLC7jm4DSxk/dt9+FscFzNM5R2VHKT+4PWnRvwk1Z")),
                           (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-2048729767, 10) != 0)
                        )
                     )
                     .append((char)Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1967977529, 27));
               }
            }
         }

         return var2.toString();
      } else {
         return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
      }
   }

   public void stopRuntimeFrameAnimations(EditorSession var1) {
      if (((-1691255820 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         if (var1.runtimeFrameAnimationTask != null) {
            try {
               var1.runtimeFrameAnimationTask.cancel();
            } catch (Exception var3) {
            }
         }

         var1.runtimeFrameAnimationTask = null;
         var1.runtimeFrameAnimationSignature = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
         var1.runtimeFrameAnimationFinished = (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 17) != 0);
      }
   }

   public void syncRuntimeFrameAnimations(Player var1, EditorSession var2) {
      if ((-746515079 * -746515079 + -746515079 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         String var3 = this.buildRuntimeFrameAnimationSignature(var2);
         if (var3.isEmpty()) {
            this.stopRuntimeFrameAnimations(var2);
         } else if (!var3.equals(var2.runtimeFrameAnimationSignature)
            || !var2.runtimeFrameAnimationFinished && (var2.runtimeFrameAnimationTask == null || var2.runtimeFrameAnimationTask.isCancelled())) {
            List var4 = this.collectRuntimeFrameAnimationTiles(var2);
            if (var4.isEmpty()) {
               this.stopRuntimeFrameAnimations(var2);
            } else {
               this.stopRuntimeFrameAnimations(var2);
               var2.runtimeFrameAnimationSignature = var3;
               var2.runtimeFrameAnimationStartMs = System.currentTimeMillis();
               UUID var5 = var1.getUniqueId();
               var2.runtimeFrameAnimationTask = Xqedii_38o2zes.Xqedii_j3mggbv(
                  this.plugin,
                  var1,
                  Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-3161586640608177284L, 59),
                  Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556822892446460915L, 46),
                  var5x -> {
                     if ((-1209865755 * -1209865755 + -1209865755 & 1) != 0) {
                        throw new IllegalStateException();
                     } else {
                        Player var6 = Bukkit.getPlayer(var5);
                        EditorSession var7 = (EditorSession)this.editorSessions.get(var5);
                        if (var6 != null && var6.isOnline() && var7 == var2 && var3.equals(var2.runtimeFrameAnimationSignature)) {
                           long var8 = Math.max(
                              Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773370651L, 7), System.currentTimeMillis() - var2.runtimeFrameAnimationStartMs
                           );
                           int var10 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 2);

                           for (EditorImageDragPart.RuntimeFrameAnimationTile var12 : (Iterable<RuntimeFrameAnimationTile>) (var4)) {

                              int var13 = var12.Xqedii_3daxkae.size();
                              long var14 = var8 / Math.max(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-563367429, 25), var12.Xqedii_ow714yo);
                              int var16;
                              if (var14 < var13) {
                                 var16 = (int)var14;
                                 var10 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1969624971, 28);
                              } else if (var12.Xqedii_1mwmkf3) {
                                 var16 = (int)(var14 % var13);
                                 var10 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1291074118, 3);
                              } else {
                                 var16 = var13 - Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596919813, 11);
                              }

                              if (var12.Xqedii_9w6178v != var16 || var8 - var12.Xqedii_3rr7prv >= Xqedii_p92kfr8.Xqedii_274j413le3omf(5251109032772689690L, 10)
                                 )
                               {
                                 var12.Xqedii_9w6178v = var16;
                                 var12.Xqedii_3rr7prv = var8;
                                 this.setHudTextIfExists(var6, var12.Xqedii_1mlytkm, Xqedii_3dvfw9qxp3w(var12.Xqedii_3daxkae.get(var16)));
                              }
                           }

                           if (var10 == 0) {
                              var2.runtimeFrameAnimationTask = null;
                              var2.runtimeFrameAnimationFinished = (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(1550561787, 31) != 0);
                              var5x.cancel();
                           }
                        } else {
                           var5x.cancel();
                        }
                     }
                  }
               );
            }
         }
      }
   }

   public List<String> resolveImageFrameSourceNames(String var1) {
      if ((-1339288589 ^ -1339288589) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(496782209, 30)];
         var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 27)] = var1;
         String var2 = this.firstNonBlank(var10001)
            .trim()
            .replace((char)Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596922229, 2), (char)Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242677563, 12));

         while (var2.startsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+oNInG5v7VThFHYMImjbXkdLssa06EvDlOZW9gOD"))) {
            var2 = var2.substring(Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1712819061, 4));
         }

         while (var2.endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+oNInG5v7VThFHYMImjbXkdLssa06EvDlOZW9gOD"))) {
            var2 = var2.substring(Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1729290327, 4), var2.length() - Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-597052933, 17));
         }

         if (!var2.isBlank() && !var2.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("/OQGjaJm3z1rgYKluJ0Iezh+luLnmgqPqQM+2OmuQ0w="))) {
            File var3 = new File(
               new File(this.plugin.getDataFolder(), Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("FDKQxaAum3yBA9m426E828YXH62OuSVo++u8WpvJRLjdwTjw3WggJP0AGEo=")),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("judNKd8/ZW2Jj+et86Erq4NVZZgNet2aMuzJfuaWVaDknSMD5qEwNA==")
            );
            File var4 = new File(var3, var2);
            if (!var4.isDirectory()) {
               return Collections.emptyList();
            } else {
               File[] var5 = var4.listFiles();
               if (var5 != null && var5.length != 0) {
                  ArrayList var6 = new ArrayList();
                  File[] var7 = var5;
                  int var8 = var5.length;

                  for (int var9 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1881912201, 19); var9 < var8; var9++) {
                     File var10 = var7[var9];
                     if (var10 != null && var10.isFile()) {
                        String var11 = var10.getName().toLowerCase(Locale.ROOT);
                        int var12 = var11.lastIndexOf(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-1402228230, 27));
                        if (var12 > 0
                           && var12 < var11.length() - Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921893, 5)
                           && Xqedii_l2une6g.Xqedii_2pejrmf.contains(var11.substring(var12 + Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1712819061, 4)))) {
                           var6.add(var10);
                        }
                     }
                  }

                  var6.sort((var1x, var2x) -> {
                     if ((-86218607 * -86218607 + -86218607 & 1) != 0) {
                        throw new IllegalStateException();
                     } else {
                        return this.compareImageFrameFileNames(((File)var1x).getName(), ((File)var2x).getName());
                     }
                  });
                  ArrayList var13 = new ArrayList();

                  for (File var15 : (Iterable<File>) (var6)) {

                     String var16 = var15.getName();
                     int var17 = var16.lastIndexOf(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921899, 0));
                     var13.add(Xqedii_2t253qr.Xqedii_376339b(var17 > 0 ? var16.substring(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 26), var17) : var16));
                  }

                  return var13;
               } else {
                  return Collections.emptyList();
               }
            }
         } else {
            return Collections.emptyList();
         }
      }
   }

   public int compareImageFrameFileNames(String var1, String var2) {
      if (((1224083610 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         long var3 = this.extractTrailingFrameIndex(var1);
         long var5 = this.extractTrailingFrameIndex(var2);
         if (var3 >= Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(4581118495053773208L, 4)
            && var5 >= Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261190638579L, 33)
            && var3 != var5) {
            return Long.compare(var3, var5);
         } else {
            Comparator var10000 = String.CASE_INSENSITIVE_ORDER;
            String[] var10002 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 17)];
            var10002[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 4)] = var1;
            String var10001 = this.firstNonBlank(var10002);
            String[] var10003 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-2048729767, 10)];
            var10003[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1823261425, 25)] = var2;
            return var10000.compare(var10001, this.firstNonBlank(var10003));
         }
      }
   }

   public long extractTrailingFrameIndex(String var1) {
      if ((1491518779 ^ 1491518779) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 21)];
         var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 18)] = var1;
         String var2 = this.firstNonBlank(var10001);
         int var3 = var2.lastIndexOf(Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242988859, 13));
         if (var3 > 0) {
            var2 = var2.substring(Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-2124571573, 22), var3);
         }

         int var4 = var2.length();
         int var5 = var4;

         while (var5 > 0 && Character.isDigit(var2.charAt(var5 - Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 12)))) {
            var5--;
         }

         if (var5 < var4 && var4 - var5 <= Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(559515107, 26)) {
            try {
               return Long.parseLong(var2.substring(var5, var4));
            } catch (NumberFormatException var7) {
               return Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-2506310083825420290L, 39);
            }
         } else {
            return Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(9221726388733052864L, 1);
         }
      }
   }

   public List<List<List<String>>> resolveImageFrameGlyphRows(String var1) {
      if ((-1696785237 * -1696785237 + -1696785237 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         List var2 = this.resolveImageFrameSourceNames(var1);
         if (var2.size() < Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-244273982, 17)) {
            return Collections.emptyList();
         } else {
            ArrayList var3 = new ArrayList();

            for (String var5 : (Iterable<String>) (var2)) {

               GeneratedImage var6 = this.plugin.resolveGeneratedUiImageForEditor(var5);
               if (var6 != null) {
                  String[] var10002 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 1)];
                  var10002[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1501946585, 2)] = var6.glyphMatrix();
                  List var7 = this.parseGlyphMatrixRows(this.firstNonBlank(var10002).trim());
                  if (!var7.isEmpty()) {
                     var3.add(var7);
                  }
               }
            }

            return (List)(var3.size() < Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(949420987, 7) ? Collections.emptyList() : var3);
         }
      }
   }

   public int resolveImageFrameIntervalMs(Map<String, Object> var1) {
      if ((1024142376 * 1024142376 + 1024142376 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         double var2 = this.parseDouble(
            this.readMapPathValue(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("PWyLtMhnTe7TzbA3yRbOdqer+amy1QKLqW2+/AC45RzOf+D2Eeadm6GRQRhnL8e8vtQ=")),
            Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032772846491L, 20))
         );
         if (!Double.isFinite(var2)) {
            var2 = this.parseDouble(
               this.readMapPathValue(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("LG9vgRf1/wvfPghFqnLCr1AaLUsY9MWcf1MJ2fNhyjYl2rFk5JbUrJSKNycxv1Rk")),
               Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(8069811095789105462L, 24))
            );
         }

         return Double.isFinite(var2) && !(var2 <= Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773370651L, 24)))
            ? (int)Math.max(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261190640371L, 7), Math.round(var2))
            : Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(930509102, 24);
      }
   }

   public boolean resolveImageFrameLoop(Map<String, Object> var1) {
      if ((-383938732 * -383938732 - -383938732 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Object var2 = this.readMapPathValue(
            var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("39YlhSnV+fheCxn4Fd+hx0WbP5HF0hyOxilc3KArbeA+vPhWuzWsmG9w1V9mRs0PVIMku6WUmzs=")
         );
         if (var2 == null) {
            var2 = this.readMapPathValue(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("iAb9rBdqDRB4LOoidEpMFpthe/xbu+bWv6sq7Fx/SpJJsKTQSIVkfqcWcc1t9bJf6CR/ziaN"));
         }

         return var2 != null && !this.parseBooleanFlag(var2, false);
      }
   }

   public String glyphAtRowColumn(List<List<String>> var1, int var2, int var3) {
      if (((2123758189 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 >= 0 && var2 < var1.size()) {
         List var4 = (List)var1.get(var2);
         if (var4 != null && var3 >= 0 && var3 < var4.size()) {
            String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921989, 7)];
            var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1989137485, 11)] = (String)var4.get(var3);
            return this.firstNonBlank(var10001);
         } else {
            return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
         }
      } else {
         return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
      }
   }

   public Map<String, Object> buildLegacyImageBlockFromCompact(Map<String, Object> var1) {
      if ((2015996657 ^ 2015996657) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(790031052, 13)];
         var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1956289001, 20)] = this.stringValue(
            this.readMapPathValue(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ZO0fvtGGVgiITP1yJT5FbmE6w26k5aJ4E2ZMCIg8KF3JS6zsbKG+/Q=="))
         );
         String var2 = this.firstNonBlank(var10001).trim();
         List var3 = var2.isBlank() ? Collections.emptyList() : this.resolveImageFrameGlyphRows(var2);
         int var4 = var3.isEmpty() ? Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767539, 3) : this.resolveImageFrameIntervalMs(var1);
         int var5 = !var3.isEmpty() && !this.resolveImageFrameLoop(var1)
            ? Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 30)
            : Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1282838493, 3);
         String var6 = this.resolveCompactImageGlyphMatrix(var1);
         List var7 = this.parseGlyphMatrixRows(var6);
         if (var7.isEmpty() && !var3.isEmpty()) {
            var7 = (List)var3.get(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 29));
            StringBuilder var8 = new StringBuilder();

            for (int var9 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 3); var9 < var7.size(); var9++) {
               if (var9 > 0) {
                  var8.append((char)Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(197526195, 11));
               }

               for (String var11 : (Iterable<String>) ((List)var7.get(var9))) {

                  var8.append(var11);
               }
            }

            var6 = var8.toString();
         }

         int var52 = this.resolveGlyphMatrixMaxColumns(var7);
         if (!var7.isEmpty() && var52 > 0) {
            var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 12)];
            var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1291074126, 3)] = this.stringValue(
               this.readMapPathValue(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("CRrPhhLN0dEhwL4Ug2s5xrGFKD/p7jggvAjP3gQm8X69WtaU5afqrK5bsTqdHrYsAcs="))
            );
            String var54 = this.firstNonBlank(var10001).trim();
            List var55 = Collections.emptyList();
            if (!var54.isBlank()) {
               GeneratedImage var56 = this.plugin.resolveGeneratedUiImageForEditor(var54);
               if (var56 != null) {
                  var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 10)];
                  var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 11)] = var56.glyphMatrix();
                  String var12 = this.firstNonBlank(var10001)
                     .trim()
                     .replace(
                        Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("YyMRV1uP2jsZxADGwlrAbki5M5ukpBLmhRCjJKJkiOw="),
                        Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Cpcxs10et5jAh9jF96VeYSUuoW/36H9KnlgAhDmG")
                     )
                     .replace((char)Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1276322107, 23), (char)Xqedii_p92kfr8.Xqedii_vjb2772t99mu(904715969, 31));
                  if (!var12.isBlank()) {
                     var55 = this.parseGlyphMatrixRows(var12);
                  }
               }
            }

            Map var57 = this.deepCopyMap(var1);
            var57.put(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("gbUJ1zxEZwa5Yx4VEpUowsMsGAWnuMEPVXkWG3e79XHTrXiF"),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("mnuqijQcsEO9baV6LuWmEEAwH+3kU5iJLdkf3788gkH8ewhsDvcBAw==")
            );
            var57.put(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "Xrxnn4jLLGtxw3543YPQNLGHYv5z53WdIJQsNNNNbDAoWqKPMKpZT2OY1bhT/+54Q4ZDUbvci2FsYU00+bAROrrQhvRLmYEAZIbU4seZzkFde8AZ/x+gTuPnMcVh1U5Ulr0="
               ),
               Boolean.valueOf((Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 4) != 0))
            );
            var57.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("MQoiGbqUnZCFpk5iBsi88KzIYYvh1UHubYBFUn5NSibYAtb+tO963Mfzq9e7oMd7vA1v5w=="), var6);
            if (!var57.containsKey(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("7bvT8lLivbQYN66hQBY8vwnN1CvXa4mWPiccA+OO4vT0fbzZ/71b9bDr"))) {
               var57.put(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("7bvT8lLivbQYN66hQBY8vwnN1CvXa4mWPiccA+OO4vT0fbzZ/71b9bDr"),
                  Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 12)
               );
            }

            if (!var57.containsKey(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("sZxozrTG5NIzw01w8P62PgQbJ0vF3qR5SHzBxDSJClNs5PFOSWU="))) {
               var57.put(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("sZxozrTG5NIzw01w8P62PgQbJ0vF3qR5SHzBxDSJClNs5PFOSWU="),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+iCnY/QyesslwHgeITWu0HwICtKVhKKadomSvNax0ZeoJW1jiRdK2A==")
               );
            }

            if (!var57.containsKey(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("edUIgOfboF3Ox+hsJzNM79hY4iDIPX3hW6D/8hbWgNSJZXQsPZk="))) {
               var57.put(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("edUIgOfboF3Ox+hsJzNM79hY4iDIPX3hW6D/8hbWgNSJZXQsPZk="),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("nsDOiOPa3UrrHFqZ8DKCHGKcgGylpGvQG1/9oESR1MGlKadb")
               );
            }

            if (!var57.containsKey(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("w40deIU0QsXj4GSjzJdDObjINo90LBn/BudzbKBL4qaYibH6Iq0fNLo0"))) {
               var57.put(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("w40deIU0QsXj4GSjzJdDObjINo90LBn/BudzbKBL4qaYibH6Iq0fNLo0"),
                  Boolean.valueOf((Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 13) != 0))
               );
            }

            if (!var57.containsKey(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("lDOKtbnUh0brnw7cWwBYDmm47m8Sn7ovfXn3r6lXoGjtH74sg3vUxw=="))) {
               var57.put(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("lDOKtbnUh0brnw7cWwBYDmm47m8Sn7ovfXn3r6lXoGjtH74sg3vUxw=="),
                  Boolean.valueOf((Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 26) != 0))
               );
            }

            String var58 = this.deriveImageBlockName(var1);
            if (!var58.isBlank()) {
               var57.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ummW4uCQefOrIoCjc2WM6svA2tPPvwrXJKxwHKlJxLoPdIO6ycs="), var58);
            }

            var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921862, 0)];
            var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 12)] = this.stringValue(
               this.readMapPathValue(var57, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Xrrvs81HHFD0cotBejhfhuT0G1swAMAermn9c5HkW6Q="))
            );
            String var13 = this.firstNonBlank(var10001).trim();
            if (var13.isBlank()) {
               String var14 = this.sanitizeGeneratedRuntimeId(var58.toLowerCase(Locale.ROOT));
               var13 = var14.isBlank()
                  ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("KAOOobe+Ua1/9aP29N+j69V90VjD460gBDslIt7xFaAqKHtpeS3M829Tm8VC0g==")
                  : Xqedii_1n9mv8c40qdys(var14);
               var57.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Xrrvs81HHFD0cotBejhfhuT0G1swAMAermn9c5HkW6Q="), var13);
            }

            var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767803, 7)];
            var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1987128837, 0)] = this.stringValue(
               this.readMapPathValue(var57, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Eaqx4dmxZEVb7SW51kr176NkLI4AG9nq1i0WyIXUMoayER3i"))
            );
            var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921925, 6)] = var58;
            String var59 = this.firstNonBlank(var10001).trim();
            if (!var59.isBlank()) {
               var57.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Eaqx4dmxZEVb7SW51kr176NkLI4AG9nq1i0WyIXUMoayER3i"), var59);
            }

            double var15 = this.resolveImageSizeDimension(
               var57,
               (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(790031052, 13) != 0),
               Math.max(
                  Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5244351434309059355L, 53)),
                  (double)(var52 * Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1838520414, 22))
               )
            );
            double var17 = this.resolveImageSizeDimension(
               var57,
               (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1729290327, 4) != 0),
               Math.max(
                  Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-6161553477181952315L, 28)),
                  (double)(var7.size() * Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1729294423, 4))
               )
            );
            Map var19 = this.toStringObjectMap(var57.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3IEyfHoKfu9Qe1f0+6fUyMQhK++UE1bYzb2kWzvTMO26afnE")));
            if (var19 == null) {
               var19 = new LinkedHashMap();
            }

            var19.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("S3kypXDHnXjWCOPx7JIbhp+C+DZ75n4lGC7SqIJB2SsypMkAQGY="), var15);
            var19.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8GWrnWfnnEO6nW4nK/mTpZKpZCKFZc4oviEoHTqZhlBPfomPstDNCw=="), var17);
            var57.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3IEyfHoKfu9Qe1f0+6fUyMQhK++UE1bYzb2kWzvTMO26afnE"), var19);
            double var20 = this.parseDouble(
               this.readMapPathValue(var57, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("4AXAC7HjtyRGjRU4MwpLuP2JRDj0fO57mRCsEbqxR9nVx1TNokk=")),
               Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261190636559L, 14))
            );
            double var22 = this.resolveCompactImageLayoutScale(var1, (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1501946589, 2) != 0));
            double var24 = this.resolveCompactImageLayoutScale(var1, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(316692327, 12) != 0));
            double var26 = this.resolveCompactImageLayoutOffset(var1, (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596954629, 15) != 0));
            double var28 = this.resolveCompactImageLayoutOffset(var1, (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1449228470, 0) != 0));
            boolean var30 = this.resolveCompactImageHasPartialLastColumn(var1, var15 / var22);
            double var31 = Math.max(
               Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(-1113994730222751807L, 60)),
               Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261192725491L, 23)) * var22
            );
            double var33 = Math.max(
               Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-4195984163533433595L, 7)),
               Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893244061101043L, 36)) * var24
            );
            double var35 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(258784171619639548L, 3)) * var22;
            double var37 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-6414924367277916197L, 44)) * var24;
            var28 = this.normalizeImageLayoutOffsetY(var28, var37);
            double var39 = this.resolveLastPartialTileShiftX(var15 / var22, var22);
            ArrayList var41 = new ArrayList();
            int var42 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(949420731, 7);

            for (int var43 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1396510356, 1); var43 < var7.size(); var43++) {
               List var44 = (List)var7.get(var43);
               if (var44 != null && !var44.isEmpty()) {
                  for (int var45 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 22); var45 < var44.size(); var45++) {
                     String var46 = (String)var44.get(var45);
                     if (var46 != null && !var46.isBlank()) {
                        var42++;
                        LinkedHashMap var47 = new LinkedHashMap();
                        var47.put(
                           Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("gbUJ1zxEZwa5Yx4VEpUowsMsGAWnuMEPVXkWG3e79XHTrXiF"),
                           Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u6bFgC+JuJg6jwfnb8XcyG0rItjOwDbvVzEPAU72DfOh1JIG")
                        );
                        var47.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Xrrvs81HHFD0cotBejhfhuT0G1swAMAermn9c5HkW6Q="), Xqedii_tiiir4isgzpj(var13, var42));
                        String var76 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Eaqx4dmxZEVb7SW51kr176NkLI4AG9nq1i0WyIXUMoayER3i");
                        String[] var10003 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1266785692, 14)];
                        var10003[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1544219874, 17)] = var59;
                        var10003[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1987128836, 0)] = var58;
                        var10003[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1989133389, 11)] = var13;
                        var47.put(var76, Xqedii_cayu6mz9yyn5(this.firstNonBlank(var10003), var42));
                        var47.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("4AXAC7HjtyRGjRU4MwpLuP2JRDj0fO57mRCsEbqxR9nVx1TNokk="), var20);
                        var47.put(
                           Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("7bvT8lLivbQYN66hQBY8vwnN1CvXa4mWPiccA+OO4vT0fbzZ/71b9bDr"),
                           Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-844451061, 19)
                        );
                        var47.put(
                           Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("sZxozrTG5NIzw01w8P62PgQbJ0vF3qR5SHzBxDSJClNs5PFOSWU="),
                           Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+iCnY/QyesslwHgeITWu0HwICtKVhKKadomSvNax0ZeoJW1jiRdK2A==")
                        );
                        var47.put(
                           Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("edUIgOfboF3Ox+hsJzNM79hY4iDIPX3hW6D/8hbWgNSJZXQsPZk="),
                           Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("nsDOiOPa3UrrHFqZ8DKCHGKcgGylpGvQG1/9oESR1MGlKadb")
                        );
                        var47.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u6bFgC+JuJg6jwfnb8XcyG0rItjOwDbvVzEPAU72DfOh1JIG"), Xqedii_b1emktbl3o4(var46));
                        var47.put(
                           Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("w40deIU0QsXj4GSjzJdDObjINo90LBn/BudzbKBL4qaYibH6Iq0fNLo0"),
                           Boolean.valueOf((Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-2048729767, 10) != 0))
                        );
                        var47.put(
                           Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("lDOKtbnUh0brnw7cWwBYDmm47m8Sn7ovfXn3r6lXoGjtH74sg3vUxw=="),
                           Boolean.valueOf((Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1206527548, 18) != 0))
                        );
                        if (!var3.isEmpty()) {
                           ArrayList var48 = new ArrayList(var3.size());

                           for (List var50 : (Iterable<List>) (var3)) {

                              String var51 = this.glyphAtRowColumn(var50, var43, var45);
                              var48.add(var51.isBlank() ? var46 : var51);
                           }

                           var47.put(
                              Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("0SXx9EHjlauZcvJSW4uXA7K4GI9cWCgqov8iW4NX6CAmpoAszbEA3GUiVFzDJRyK+zGJRzMU4xX6xXgFhk+mlw=="),
                              var48
                           );
                           var47.put(
                              Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                                 "/v3miXHUABe1LdatKGlLw8alOCwoqJOD5mEltiTdfKlFcJnCWoAhwSJahbjqTQ0SOvq4FNNozXIBnONenJttRBpyBydHyAxlQvM="
                              ),
                              var4
                           );
                           var47.put(
                              Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("spELMeXIdaRIUymON0L2UsCKjhpVpjtE5RfsyMrTLC7jm4DSxk/dt9+FscFzNM5R2VHKT+4PWnRvwk1Z"),
                              Boolean.valueOf((var5 != 0))
                           );
                        }

                        if (!var54.isBlank()) {
                           var47.put(
                              Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("DZnMEnbYEiXCx0+bXojnT3tLDBL0sLjbqVAZI6Emmu2nh4OxYnemxY88WEflO7mhAeH6HdTyfYl3k1DL9lQnAw=="),
                              var54
                           );
                           var47.put(
                              Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("DLSV4tEMxTXnaEE/tlP8BGbQtiKybkPutuFMcj8RIa3iVc9oBgMVTZbkRUfr57pmkHg1W9kLUiIMqA=="), var43
                           );
                           var47.put(
                              Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("5k0wom4dPWksvsJg6QvyOJmmtddeQKkcX0mDyieDMSGOGtIaQA5Rjv/H1N+9yrWXDlkyaZ2NZwHXzA=="), var45
                           );
                           if (!var55.isEmpty() && var43 < var55.size()) {
                              List var66 = (List)var55.get(var43);
                              if (var66 != null && var45 < var66.size()) {
                                 String var69 = (String)var66.get(var45);
                                 var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-355717354, 29)];
                                 var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1898841462, 8)] = var69;
                                 if (!this.firstNonBlank(var10001).isBlank()) {
                                    var47.put(
                                       Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("rcKMrI790hpVOpccu4PoUbwfmSbghg4LfOyngNUi86RJxUuQHQ6kLJjAUPL+FuE6"),
                                       Xqedii_z8lc50aijzq9(var69)
                                    );
                                 }
                              }
                           }
                        }

                        LinkedHashMap var67 = new LinkedHashMap();
                        double var70 = var45 * var35;
                        if (var30
                           && var44.size() > Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1580062104, 14)
                           && var45 == var44.size() - Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-581335582, 26)) {
                           var70 -= var39;
                        }

                        var67.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("r/G5c9QLrSfSj2hOM0lnfxyrHNJzXLyGEj8ZnYnG"), var26 + var70);
                        var67.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("aY9NJhHvVgN3vcQ5Po4KwYUAwkMwXAjYDseqfM6E"), var28 + var43 * var37);
                        var47.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("c63EUYU/P5Jwb4wYiYNV3LxmMpg79cbbiiNmLYmSlJtMvT5V3PaHiEL7cLo="), var67);
                        LinkedHashMap var71 = new LinkedHashMap();
                        var71.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("S3kypXDHnXjWCOPx7JIbhp+C+DZ75n4lGC7SqIJB2SsypMkAQGY="), var31);
                        var71.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8GWrnWfnnEO6nW4nK/mTpZKpZCKFZc4oviEoHTqZhlBPfomPstDNCw=="), var33);
                        var47.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3IEyfHoKfu9Qe1f0+6fUyMQhK++UE1bYzb2kWzvTMO26afnE"), var71);
                        var41.add(var47);
                     }
                  }
               }
            }

            Object var61 = var1.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("UVREse9krg9Ztxi40WBaLWzZ9MMEG3xGE4ValqIoO/Iu7YfshoCwCrjkfpI="));
            if (var61 instanceof List) {
               for (Object var64 : (List)var61) {
                  Map var65 = this.toStringObjectMap(var64);
                  if (var65 != null && !var65.isEmpty()) {
                     var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 25)];
                     var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(772109937, 16)] = this.stringValue(
                        this.readMapPathValue(var65, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u6bFgC+JuJg6jwfnb8XcyG0rItjOwDbvVzEPAU72DfOh1JIG"))
                     );
                     String var68 = this.firstNonBlank(var10001).trim();
                     if (this.extractUiImageGlyphToken(var68).isBlank()) {
                        var41.add(this.deepCopyMap(var65));
                     }
                  }
               }
            }

            if (!var41.isEmpty()) {
               this.anchorGroupChildrenVerticallyOnLoad(var41, var17);
               this.anchorGroupChildrenHorizontallyOnLoad(var41);
               this.applyGroupSingleTilePartialCorrection(var41, var15, var17);
               var57.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("UVREse9krg9Ztxi40WBaLWzZ9MMEG3xGE4ValqIoO/Iu7YfshoCwCrjkfpI="), var41);
            }

            return var57;
         } else {
            Map var53 = this.deepCopyMap(var1);
            var53.put(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("gbUJ1zxEZwa5Yx4VEpUowsMsGAWnuMEPVXkWG3e79XHTrXiF"),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("mnuqijQcsEO9baV6LuWmEEAwH+3kU5iJLdkf3788gkH8ewhsDvcBAw==")
            );
            var53.put(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "Xrxnn4jLLGtxw3543YPQNLGHYv5z53WdIJQsNNNNbDAoWqKPMKpZT2OY1bhT/+54Q4ZDUbvci2FsYU00+bAROrrQhvRLmYEAZIbU4seZzkFde8AZ/x+gTuPnMcVh1U5Ulr0="
               ),
               Boolean.valueOf((Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-60050949, 29) != 0))
            );
            if (!var53.containsKey(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("7bvT8lLivbQYN66hQBY8vwnN1CvXa4mWPiccA+OO4vT0fbzZ/71b9bDr"))) {
               var53.put(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("7bvT8lLivbQYN66hQBY8vwnN1CvXa4mWPiccA+OO4vT0fbzZ/71b9bDr"),
                  Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 2)
               );
            }

            if (!var53.containsKey(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("w40deIU0QsXj4GSjzJdDObjINo90LBn/BudzbKBL4qaYibH6Iq0fNLo0"))) {
               var53.put(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("w40deIU0QsXj4GSjzJdDObjINo90LBn/BudzbKBL4qaYibH6Iq0fNLo0"),
                  Boolean.valueOf((Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1206789692, 18) != 0))
               );
            }

            if (!var53.containsKey(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("lDOKtbnUh0brnw7cWwBYDmm47m8Sn7ovfXn3r6lXoGjtH74sg3vUxw=="))) {
               var53.put(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("lDOKtbnUh0brnw7cWwBYDmm47m8Sn7ovfXn3r6lXoGjtH74sg3vUxw=="),
                  Boolean.valueOf((Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1761428680, 15) != 0))
               );
            }

            return var53;
         }
      } else {
         return null;
      }
   }

   public void anchorGroupChildrenHorizontallyOnLoad(List<Map<String, Object>> var1) {
      if ((128936846 * (128936846 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         double var2 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5395294589593404187L, 58));
         double var4 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2559197837562457075L, 57));
         int var6 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 18);

         for (Map var8 : var1) {
            if (var8 != null && !var8.isEmpty()) {
               String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-320709624, 1)];
               var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(197505715, 11)] = this.stringValue(
                  this.readMapPathValue(var8, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u6bFgC+JuJg6jwfnb8XcyG0rItjOwDbvVzEPAU72DfOh1JIG"))
               );
               String var9 = this.firstNonBlank(var10001).trim();
               if (!this.extractUiImageGlyphToken(var9).isBlank()) {
                  double var10 = this.parseDouble(
                     this.readMapPathValue(var8, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Bb1stb4HQ6HihYXcPSol4rQI6RS61HINzXliyJVegflT2kp7VwZOOLeLV+BYMB4M")),
                     this.parseDouble(
                        this.readMapPathValue(var8, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("r/G5c9QLrSfSj2hOM0lnfxyrHNJzXLyGEj8ZnYnG")),
                        Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(571955757191919375L, 54))
                     )
                  );
                  if (Double.isFinite(var10)) {
                     double var12 = Math.abs(
                        this.parseDouble(
                           this.readMapPathValue(var8, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u2uvOjAqt0jIbg10PMdewJSqVEHCbdZqMUeQP+TthXkQFA0phBEWU0ELj9tSIF3f")),
                           this.parseDouble(
                              this.readMapPathValue(var8, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("S3kypXDHnXjWCOPx7JIbhp+C+DZ75n4lGC7SqIJB2SsypMkAQGY=")),
                              Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(8515554752599154685L, 15))
                           )
                        )
                     );
                     if (var10 < var2) {
                        var2 = var10;
                        var4 = var12;
                     }

                     var6 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 3);
                  }
               }
            }
         }

         if (var6 != 0 && Double.isFinite(var2) && Double.isFinite(var4)) {
            double var18 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-6929471204129194499L, 19));
            double var19 = var4 * Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(1189716089029404845L, 45))
               - Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261173841907L, 26));
            double var11 = var19 - var2;
            if (Double.isFinite(var11) && !(Math.abs(var11) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2134442797077938946L, 0)))) {
               for (Map var14 : var1) {
                  if (var14 != null && !var14.isEmpty()) {
                     String[] var20 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 15)];
                     var20[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1362221632, 29)] = this.stringValue(
                        this.readMapPathValue(var14, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u6bFgC+JuJg6jwfnb8XcyG0rItjOwDbvVzEPAU72DfOh1JIG"))
                     );
                     String var15 = this.firstNonBlank(var20).trim();
                     if (!this.extractUiImageGlyphToken(var15).isBlank()) {
                        double var16 = this.parseDouble(
                           this.readMapPathValue(var14, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Bb1stb4HQ6HihYXcPSol4rQI6RS61HINzXliyJVegflT2kp7VwZOOLeLV+BYMB4M")),
                           this.parseDouble(
                              this.readMapPathValue(var14, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("r/G5c9QLrSfSj2hOM0lnfxyrHNJzXLyGEj8ZnYnG")),
                              Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(1633107983828254395L, 22))
                           )
                        );
                        if (Double.isFinite(var16)) {
                           this.setMapPathValue(
                              var14, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Bb1stb4HQ6HihYXcPSol4rQI6RS61HINzXliyJVegflT2kp7VwZOOLeLV+BYMB4M"), var16 + var11
                           );
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void anchorGroupChildrenVerticallyOnLoad(List<Map<String, Object>> var1, double var2) {
      if (((-281329182 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         double var4 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-3710415670480020169L, 55));
         double var6 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032706245403L, 27));
         int var8 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1989137485, 11);

         for (Map var10 : var1) {
            if (var10 != null && !var10.isEmpty()) {
               String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 9)];
               var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-641419243, 2)] = this.stringValue(
                  this.readMapPathValue(var10, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u6bFgC+JuJg6jwfnb8XcyG0rItjOwDbvVzEPAU72DfOh1JIG"))
               );
               String var11 = this.firstNonBlank(var10001).trim();
               if (!this.extractUiImageGlyphToken(var11).isBlank()) {
                  double var12 = this.parseDouble(
                     this.readMapPathValue(var10, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("H4boOHXP9f3KMY2tAMho2IhW/7xykghhkCdayf0lgv1ntl3nezSe0XY9cRZ/4fMA")),
                     this.parseDouble(
                        this.readMapPathValue(var10, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("aY9NJhHvVgN3vcQ5Po4KwYUAwkMwXAjYDseqfM6E")),
                        Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(2341351522479590899L, 61))
                     )
                  );
                  if (Double.isFinite(var12)) {
                     if (var12 < var4) {
                        var4 = var12;
                        var6 = Math.abs(
                           this.parseDouble(
                              this.readMapPathValue(
                                 var10, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("vTDTfgcyXN+4Nu4RRZjf8UxPLjjr9KqRI1a1RT2HnGSTjc8S1npvrB3uwzQy5aT5qBU=")
                              ),
                              this.parseDouble(
                                 this.readMapPathValue(var10, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8GWrnWfnnEO6nW4nK/mTpZKpZCKFZc4oviEoHTqZhlBPfomPstDNCw==")),
                                 Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(1136272424508702308L, 2))
                              )
                           )
                        );
                     }

                     var8 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1729290311, 4);
                  }
               }
            }
         }

         if (var8 != 0 && Double.isFinite(var4)) {
            double var18 = Double.isFinite(var6)
               ? Xqedii_14y6uzm.Xqedii_2j0x561 * (var6 - Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-2300007803664265465L, 62)))
               : Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773370651L, 3));
            double var19 = var18 - var4;
            if (Double.isFinite(var19) && !(Math.abs(var19) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(8005715208440562413L, 11)))) {
               for (Map var14 : var1) {
                  if (var14 != null && !var14.isEmpty()) {
                     String[] var20 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921863, 1)];
                     var20[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 22)] = this.stringValue(
                        this.readMapPathValue(var14, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u6bFgC+JuJg6jwfnb8XcyG0rItjOwDbvVzEPAU72DfOh1JIG"))
                     );
                     String var15 = this.firstNonBlank(var20).trim();
                     if (!this.extractUiImageGlyphToken(var15).isBlank()) {
                        double var16 = this.parseDouble(
                           this.readMapPathValue(var14, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("H4boOHXP9f3KMY2tAMho2IhW/7xykghhkCdayf0lgv1ntl3nezSe0XY9cRZ/4fMA")),
                           this.parseDouble(
                              this.readMapPathValue(var14, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("aY9NJhHvVgN3vcQ5Po4KwYUAwkMwXAjYDseqfM6E")),
                              Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773403411L, 16))
                           )
                        );
                        if (Double.isFinite(var16)) {
                           this.setMapPathValue(
                              var14, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("H4boOHXP9f3KMY2tAMho2IhW/7xykghhkCdayf0lgv1ntl3nezSe0XY9cRZ/4fMA"), var16 + var19
                           );
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public String resolveCompactImageGlyphMatrix(Map<String, Object> var1) {
      if ((-1799423081 * -1799423081 + -1799423081 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921893, 5)];
         var10001[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 6)] = this.stringValue(
            this.readMapPathValue(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("MQoiGbqUnZCFpk5iBsi88KzIYYvh1UHubYBFUn5NSibYAtb+tO963Mfzq9e7oMd7vA1v5w=="))
         );
         String var2 = this.firstNonBlank(var10001).trim();
         if (var2.isBlank()) {
            var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-2128765877, 22)];
            var10001[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 19)] = this.stringValue(
               this.readMapPathValue(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("kj/CSq8d8CDLrBkGHgGHWEZpDm+S5w7BreaFzQzKSytg8SY8HYDp/3zMcgdA3KmTGKI="))
            );
            var2 = this.firstNonBlank(var10001).trim();
         }

         if (var2.isBlank()) {
            var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 14)];
            var10001[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 13)] = this.stringValue(
               this.readMapPathValue(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("7QWtD9WFqA9TC+qHbfrDr8O4LgBaP9sHoNa9T79YjxQHyrH0r0Hs0w=="))
            );
            var2 = this.firstNonBlank(var10001).trim();
         }

         if (var2.isBlank()) {
            var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921893, 5)];
            var10001[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 0)] = this.stringValue(
               this.readMapPathValue(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ummW4uCQefOrIoCjc2WM6svA2tPPvwrXJKxwHKlJxLoPdIO6ycs="))
            );
            String var3 = this.firstNonBlank(var10001).trim();
            if (!var3.isBlank()) {
               GeneratedImage var4 = this.plugin.resolveGeneratedUiImageForEditor(var3);
               if (var4 != null) {
                  var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-488810107, 18)];
                  var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 11)] = var4.glyphMatrix();
                  String var5 = this.firstNonBlank(var10001).trim();
                  if (!var5.isBlank()) {
                     var2 = var5;
                  }
               }
            }
         }

         return var2.replace(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("YyMRV1uP2jsZxADGwlrAbki5M5ukpBLmhRCjJKJkiOw="),
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Cpcxs10et5jAh9jF96VeYSUuoW/36H9KnlgAhDmG")
            )
            .replace((char)Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1205741116, 18), (char)Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767657, 1));
      } else {
         return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
      }
   }

   public double resolveCompactImageLayoutScale(Map<String, Object> var1, boolean var2) {
      if (((-840075101 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         String var3 = var2
            ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("CCWBGWQn4usWBKL7pBgbRbXDn17kskNCXfP5IT9UYbqd5mJ19/VXKDxcJ22WctROdahhottT")
            : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("aFZhimdmj7rVQ6Vp+hKWXaaCXUtIvnbqnogNW8qGq2+/+vcL3miX7/jist66EhqQK1B5Wrye");
         String var4 = var2
            ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("YmDX24FxUzwFw+/+BDcCAfF5smJD7aFvINsFCQgiRF7Kn3X1J8eW1vJzkdxWeFmzJF4=")
            : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("cWSJXS/vwZJmvdWRTVQx9ybOxt+TNHGpyO0pqf6sLnz7ou+CPCNNqbHBO3OYj6YMbqc=");
         double var5 = this.parseDouble(
            this.readMapPathValue(var1, var3),
            this.parseDouble(this.readMapPathValue(var1, var4), Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-1642001878584682045L, 8)))
         );
         return Double.isFinite(var5) && !(Math.abs(var5) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-566390271804100074L, 58)))
            ? Math.abs(var5)
            : Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(4020837008027418333L, 47));
      } else {
         return Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261195422707L, 28));
      }
   }

   public double resolveCompactImageLayoutOffset(Map<String, Object> var1, boolean var2) {
      if ((227761203 * (227761203 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         String var3 = var2
            ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ACsveKgTUWkEhi5z1eifJRXF2b4BCWtTZrD8A3yIw4ChLWhfwEfhAotdrDkbNpAAYln2sLQ0I1I=")
            : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("6V3g6nP5FeKb52+EC0wwO/zevGCWcwjXevqb9TouUMJTp3TnQkEk+ejJw+Rpy8RTjBPoRAYhFhM=");
         String var4 = var2
            ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("pulGqQxS5eXngSLrV0QLerWUw1MgyOKZi9A+R//aTWC90pJcsmY8HAEyhegiP6TaCcbT3A==")
            : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("OX9yFo8E9e+/Efq7LQV+ajoCPNipvVN4pfEwqQAAD0QlfoJlU66f1A0d0nQ7u+3n9d5OAw==");
         double var5 = this.parseDouble(
            this.readMapPathValue(var1, var3),
            this.parseDouble(this.readMapPathValue(var1, var4), Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(3677174170199487711L, 57)))
         );
         return !Double.isFinite(var5) ? Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261190638579L, 41)) : var5;
      } else {
         return Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773370651L, 38));
      }
   }

   public boolean resolveCompactImageHasPartialLastColumn(Map<String, Object> var1, double var2) {
      if ((1674759710 ^ 1674759710) != 0) {
         throw new IllegalStateException();
      } else {
         if (var1 != null && !var1.isEmpty()) {
            Object var4 = this.readMapPathValue(
               var1,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8L/juUOq0dV+vvX9dtf41Dl43B7JaBHUkMZ+5duluhL7EI4am88ETqIEtg+1lIOOz8WcaBJ2ZsKv699TG++wyFrFGQaKfl3TMZXEhDW5")
            );
            if (var4 != null) {
               return this.parseBooleanFlag(var4, (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 25) != 0));
            }

            Object var5 = this.readMapPathValue(
               var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ecbkJU6AvnS9koIuMOcX0rQhhgK+EiFFEOACE7NAoj646oEIAURS5sl+0ab5ZWXf5NO0oT5EdmZFQka544Qpg6xMKdbNg7KB")
            );
            if (var5 != null) {
               return this.parseBooleanFlag(var5, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(45824151, 23) != 0));
            }
         }

         int var6 = Math.max(Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 8), (int)Math.round(var2) % Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1898906998, 8));
         return ((var6 > 0 ? Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 2) : Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-836386642, 5)) != 0);
      }
   }

   public double resolveImageSizeDimension(Map<String, Object> var1, boolean var2, double var3) {
      if ((-2077222327 * (-2077222327 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         String var5 = var2
            ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("S3kypXDHnXjWCOPx7JIbhp+C+DZ75n4lGC7SqIJB2SsypMkAQGY=")
            : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8GWrnWfnnEO6nW4nK/mTpZKpZCKFZc4oviEoHTqZhlBPfomPstDNCw==");
         String var6 = var2
            ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ecSpp+SVicqQhqLxyjB/mUTzEKGOErk+KKujhDH0JnK0Kc76yCSKZoH4u0Hnuvlt7t8=")
            : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("AKe02R3CLV6OwQ1UKhz2MuEBWvbVE4x+sIbEhOhC4F50WU/JupaatsS8UvcXnPHqeE2fDg==");
         String var7 = var2
            ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+iG27LBTf4lLujV87fs1PXOYKxIVsJUcqOy5PyjgwkJSwdDcB1CWjXj4Byxjk/m4")
            : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("MU1cWKvaKGAXrqt8U1CJqOdT+Tv3X5DmzkKNTow4dY34re5Xny4EbLVUu8q70Vv7has=");
         double var8 = this.parseDouble(
            this.readMapPathValue(var1, Xqedii_3b2tw9ccigmis(var5)), Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109067141497627L, 36))
         );
         if (!Double.isFinite(var8)) {
            var8 = this.parseDouble(this.readMapPathValue(var1, var5), Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(2341351522479590899L, 61)));
         }

         if (!Double.isFinite(var8)) {
            var8 = this.parseDouble(this.readMapPathValue(var1, var6), Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5260114033004856091L, 54)));
         }

         if (!Double.isFinite(var8)) {
            var8 = this.parseDouble(this.readMapPathValue(var1, var7), Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-4965594660555198466L, 14)));
         }

         if (!Double.isFinite(var8)) {
            var8 = var3;
         }

         return Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-3594072942324100228L, 59)), var8);
      } else {
         return Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(3922632777641083881L, 16)), var3);
      }
   }

   public double resolveLastPartialTileShiftX(double var1, double var3) {
      if ((1741423314 ^ 1741423314) != 0) {
         throw new IllegalStateException();
      } else {
         int var5 = Math.max(Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(45824151, 23), (int)Math.round(var1) % Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1838520414, 22));
         if (var5 <= 0) {
            return Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-8937393058747188345L, 53));
         } else {
            double var6 = Double.isFinite(var3) && var3 > Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261190638579L, 37))
               ? var3
               : Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251108895200199451L, 39));
            return imageGroupSingleTilePartialShiftX(
               var5 * var6, Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-5381880203091860008L, 25)) * var6
            );
         }
      }
   }

   public boolean insertTemporaryImageElement(Player var1, String var2, int var3, int var4, String var5) {
      if ((544599170 * 544599170 + 544599170 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 5) != 0);
      } else {
         EditorSession var6 = (EditorSession)this.editorSessions.get(var1.getUniqueId());
         if (var6 != null && var6.rawBlocks != null) {
            String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 26)];
            var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-978144501, 19)] = var2;
            String var7 = this.firstNonBlank(var10001).trim();
            if (var7.isBlank()) {
               var7 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ummW4uCQefOrIoCjc2WM6svA2tPPvwrXJKxwHKlJxLoPdIO6ycs=");
            }

            var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596922117, 8)];
            var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1672773283, 6)] = var5;
            String var8 = this.firstNonBlank(var10001);
            if (var8.isBlank()) {
               return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 22) != 0);
            } else {
               String var9 = this.sanitizeGeneratedRuntimeId(var7.toLowerCase(Locale.ROOT));
               if (var9.isBlank()) {
                  var9 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ummW4uCQefOrIoCjc2WM6svA2tPPvwrXJKxwHKlJxLoPdIO6ycs=");
               }

               String var10 = var8.replace(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("YyMRV1uP2jsZxADGwlrAbki5M5ukpBLmhRCjJKJkiOw="),
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Cpcxs10et5jAh9jF96VeYSUuoW/36H9KnlgAhDmG")
                  )
                  .replace((char)Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(817652139, 7), (char)Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596909573, 11));
               List var11 = this.parseGlyphMatrixRows(var10);
               int var12 = this.resolveGlyphMatrixMaxColumns(var11);
               if (!var11.isEmpty() && var12 > 0) {
                  double var13 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-6414924375867850789L, 44));
                  double var15 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2552411651795823603L, 54));
                  double var17 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-2297756003850580217L, 62));
                  double var19 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261190654851L, 16));
                  int var21 = Math.max(Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 1), var3 % Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-262707438, 20));
                  int var22 = var21 > 0 ? Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-580144645, 24) : Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1266769308, 14);
                  double var23 = this.resolveSessionPageWidth(var6);
                  double var25 = this.resolveSessionPageHeight(var6);
                  double var27 = Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-3284003757169364089L, 9)), (double)var3);
                  double var29 = Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261189108723L, 23)), (double)var4);
                  double var31 = Math.max(
                     Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(4034905547898748059L, 23)),
                     var23 / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261224193011L, 27))
                        - var27 / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(4020837042387156701L, 47))
                  );
                  double var33 = Math.max(
                     Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(6047804403136689709L, 31)),
                     var25 / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773436187L, 18))
                        - var29 / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032777564955L, 24))
                  );
                  double var35 = this.findMaxLayer(var6.renderBlocks) + Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(-2054792757236749556L, 10));
                  double var37 = var35;
                  String var39 = this.buildSelectionCreatedElementId(var6, Xqedii_1c1elc7sa1qi0(var9));
                  String var40 = this.buildUniqueTargetDisplayName(var6, var7, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ=="));
                  LinkedHashMap var41 = new LinkedHashMap();
                  var41.put(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("gbUJ1zxEZwa5Yx4VEpUowsMsGAWnuMEPVXkWG3e79XHTrXiF"),
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("mnuqijQcsEO9baV6LuWmEEAwH+3kU5iJLdkf3788gkH8ewhsDvcBAw==")
                  );
                  var41.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Xrrvs81HHFD0cotBejhfhuT0G1swAMAermn9c5HkW6Q="), var39);
                  var41.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Eaqx4dmxZEVb7SW51kr176NkLI4AG9nq1i0WyIXUMoayER3i"), var40);
                  var41.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("4AXAC7HjtyRGjRU4MwpLuP2JRDj0fO57mRCsEbqxR9nVx1TNokk="), var35);
                  var41.put(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("7bvT8lLivbQYN66hQBY8vwnN1CvXa4mWPiccA+OO4vT0fbzZ/71b9bDr"),
                     Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 22)
                  );
                  var41.put(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("sZxozrTG5NIzw01w8P62PgQbJ0vF3qR5SHzBxDSJClNs5PFOSWU="),
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+iCnY/QyesslwHgeITWu0HwICtKVhKKadomSvNax0ZeoJW1jiRdK2A==")
                  );
                  var41.put(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("edUIgOfboF3Ox+hsJzNM79hY4iDIPX3hW6D/8hbWgNSJZXQsPZk="),
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("nsDOiOPa3UrrHFqZ8DKCHGKcgGylpGvQG1/9oESR1MGlKadb")
                  );
                  var41.put(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("w40deIU0QsXj4GSjzJdDObjINo90LBn/BudzbKBL4qaYibH6Iq0fNLo0"),
                     Boolean.valueOf((Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 17) != 0))
                  );
                  var41.put(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("lDOKtbnUh0brnw7cWwBYDmm47m8Sn7ovfXn3r6lXoGjtH74sg3vUxw=="),
                     Boolean.valueOf((Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 12) != 0))
                  );
                  var41.put(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                        "Xrxnn4jLLGtxw3543YPQNLGHYv5z53WdIJQsNNNNbDAoWqKPMKpZT2OY1bhT/+54Q4ZDUbvci2FsYU00+bAROrrQhvRLmYEAZIbU4seZzkFde8AZ/x+gTuPnMcVh1U5Ulr0="
                     ),
                     Boolean.valueOf((Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596920837, 10) != 0))
                  );
                  var41.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ummW4uCQefOrIoCjc2WM6svA2tPPvwrXJKxwHKlJxLoPdIO6ycs="), var7);
                  var41.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("MQoiGbqUnZCFpk5iBsi88KzIYYvh1UHubYBFUn5NSibYAtb+tO963Mfzq9e7oMd7vA1v5w=="), var10);
                  LinkedHashMap var42 = new LinkedHashMap();
                  var42.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("r/G5c9QLrSfSj2hOM0lnfxyrHNJzXLyGEj8ZnYnG"), var31);
                  var42.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("aY9NJhHvVgN3vcQ5Po4KwYUAwkMwXAjYDseqfM6E"), var33);
                  var41.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("c63EUYU/P5Jwb4wYiYNV3LxmMpg79cbbiiNmLYmSlJtMvT5V3PaHiEL7cLo="), var42);
                  LinkedHashMap var43 = new LinkedHashMap();
                  var43.put(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("S3kypXDHnXjWCOPx7JIbhp+C+DZ75n4lGC7SqIJB2SsypMkAQGY="),
                     Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773436251L, 18)), var27)
                  );
                  var43.put(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8GWrnWfnnEO6nW4nK/mTpZKpZCKFZc4oviEoHTqZhlBPfomPstDNCw=="),
                     Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032777569051L, 24)), var29)
                  );
                  var41.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3IEyfHoKfu9Qe1f0+6fUyMQhK++UE1bYzb2kWzvTMO26afnE"), var43);
                  ArrayList var44 = new ArrayList();
                  if (var6.editMode) {
                     this.recordEditorMutation(var6);
                  }

                  int var45 = var6.rawBlocks.size();
                  int var46 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1153919230, 31);

                  for (int var47 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1153919230, 31); var47 < var11.size(); var47++) {
                     List var48 = (List)var11.get(var47);
                     if (var48 != null && !var48.isEmpty()) {
                        for (int var49 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 1); var49 < var48.size(); var49++) {
                           String var50 = (String)var48.get(var49);
                           if (var50 != null && !var50.isBlank()) {
                              var46++;
                              LinkedHashMap var51 = new LinkedHashMap();
                              var51.put(
                                 Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("gbUJ1zxEZwa5Yx4VEpUowsMsGAWnuMEPVXkWG3e79XHTrXiF"),
                                 Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u6bFgC+JuJg6jwfnb8XcyG0rItjOwDbvVzEPAU72DfOh1JIG")
                              );
                              var51.put(
                                 Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Xrrvs81HHFD0cotBejhfhuT0G1swAMAermn9c5HkW6Q="),
                                 this.buildSelectionCreatedElementId(var6, Xqedii_vyb8xys67v16(var9, var46))
                              );
                              var51.put(
                                 Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Eaqx4dmxZEVb7SW51kr176NkLI4AG9nq1i0WyIXUMoayER3i"),
                                 this.buildUniqueTargetDisplayName(
                                    var6, Xqedii_2pmi6itqo479k(var7, var46), Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==")
                                 )
                              );
                              var51.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("4AXAC7HjtyRGjRU4MwpLuP2JRDj0fO57mRCsEbqxR9nVx1TNokk="), var37);
                              var51.put(
                                 Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("7bvT8lLivbQYN66hQBY8vwnN1CvXa4mWPiccA+OO4vT0fbzZ/71b9bDr"),
                                 Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-320709132, 1)
                              );
                              var51.put(
                                 Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("sZxozrTG5NIzw01w8P62PgQbJ0vF3qR5SHzBxDSJClNs5PFOSWU="),
                                 Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+iCnY/QyesslwHgeITWu0HwICtKVhKKadomSvNax0ZeoJW1jiRdK2A==")
                              );
                              var51.put(
                                 Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("edUIgOfboF3Ox+hsJzNM79hY4iDIPX3hW6D/8hbWgNSJZXQsPZk="),
                                 Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("nsDOiOPa3UrrHFqZ8DKCHGKcgGylpGvQG1/9oESR1MGlKadb")
                              );
                              var51.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("u6bFgC+JuJg6jwfnb8XcyG0rItjOwDbvVzEPAU72DfOh1JIG"), Xqedii_1p1fvkiiux6ds(var50));
                              var51.put(
                                 Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("w40deIU0QsXj4GSjzJdDObjINo90LBn/BudzbKBL4qaYibH6Iq0fNLo0"),
                                 Boolean.valueOf((Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(2025281121, 16) != 0))
                              );
                              var51.put(
                                 Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("lDOKtbnUh0brnw7cWwBYDmm47m8Sn7ovfXn3r6lXoGjtH74sg3vUxw=="),
                                 Boolean.valueOf((Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-648444446, 26) != 0))
                              );
                              LinkedHashMap var52 = new LinkedHashMap();
                              double var53 = var49 * Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(5085521653319268764L, 4));
                              if (var22 != 0
                                 && var48.size() > Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596954629, 15)
                                 && var49 == var48.size() - Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1537945788, 23)) {
                                 var53 -= this.resolveLastPartialTileShiftX(
                                    var3, Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-7556952041987064475L, 9))
                                 );
                              }

                              var52.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("r/G5c9QLrSfSj2hOM0lnfxyrHNJzXLyGEj8ZnYnG"), var53);
                              var52.put(
                                 Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("aY9NJhHvVgN3vcQ5Po4KwYUAwkMwXAjYDseqfM6E"),
                                 var47 * Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251056651352228635L, 46))
                              );
                              var51.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("c63EUYU/P5Jwb4wYiYNV3LxmMpg79cbbiiNmLYmSlJtMvT5V3PaHiEL7cLo="), var52);
                              LinkedHashMap var55 = new LinkedHashMap();
                              var55.put(
                                 Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("S3kypXDHnXjWCOPx7JIbhp+C+DZ75n4lGC7SqIJB2SsypMkAQGY="),
                                 Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-6957797000309132950L, 34))
                              );
                              var55.put(
                                 Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8GWrnWfnnEO6nW4nK/mTpZKpZCKFZc4oviEoHTqZhlBPfomPstDNCw=="),
                                 Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(7682983667525831601L, 26))
                              );
                              var51.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3IEyfHoKfu9Qe1f0+6fUyMQhK++UE1bYzb2kWzvTMO26afnE"), var55);
                              var44.add(var51);
                           }
                        }
                     }
                  }

                  if (var44.isEmpty()) {
                     return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 25) != 0);
                  } else {
                     this.applyGroupSingleTilePartialCorrection(var44, var27, var29);
                     int var56 = this.resolveMaxPageElements();
                     if (var6.rawBlocks.size() >= var56) {
                        this.sendEditorLimitReached(var1, var56);
                        return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1570524033, 30) != 0);
                     } else {
                        var41.put(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("UVREse9krg9Ztxi40WBaLWzZ9MMEG3xGE4ValqIoO/Iu7YfshoCwCrjkfpI="), var44);
                        var6.rawBlocks.add(var41);
                        var6.renderBlocks = this.resolveRenderableBlocks(var6.rawBlocks, var6.components, var6);
                        if (!var6.editMode) {
                           this.renderPage(var1, var6, (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 7) != 0));
                           return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 24) != 0);
                        } else {
                           this.pruneEditorLayerStateTargets(var6);
                           this.clearSelectionForStructureEdit(var1, var6);
                           String var57 = String.valueOf(var45);
                           this.rerenderEditableContentForTargets(
                              var1,
                              var6,
                              List.of(var57),
                              (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 8) != 0),
                              (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1153919230, 31) != 0)
                           );
                           var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(2025346657, 16)];
                           var10001[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 16)] = this.findTargetIdByPath(var6, var57);
                           var10001[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 29)] = var39;
                           String var58 = this.firstNonBlank(var10001);
                           var6.selectedElementId = var58;
                           var6.additionalSelectedElementIds.clear();
                           var6.selectionOutlineVisible = (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1672773347, 6) != 0);
                           this.normalizeSelectionState(var6);
                           this.rerenderEditableSelection(var1, var6);
                           this.renderLayersPanel(var1, var6);
                           this.updatePageInfoReadout(var1, var6);
                           return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921989, 7) != 0);
                        }
                     }
                  }
               } else {
                  return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1729290327, 4) != 0);
               }
            }
         } else {
            return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1396510356, 1) != 0);
         }
      }
   }

   public Map<String, YamlConfiguration> expandComponentImageBlocks(Map<String, YamlConfiguration> var1) {
      if ((553712175 ^ 553712175) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         HashMap var2 = null;

         for (Entry var4 : var1.entrySet()) {
            YamlConfiguration var5 = (YamlConfiguration)var4.getValue();
            if (var5 != null
               && this.containsCompactImageBlock(var5.getList(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("jZMD11okutCwYth/L0tcF13dPIn7mr8xL1PzklWh78l8xYo5VAOl5g==")))
               )
             {
               YamlConfiguration var6;
               synchronized (this.Xqedii_2e4261v) {
                  var6 = this.Xqedii_2e4261v.get(var5);
                  if (var6 == null) {
                     var6 = this.cloneYamlConfiguration(var5);
                     List var8 = this.expandCompactImageBlocks(
                        this.copyBlocks(var5.getList(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("jZMD11okutCwYth/L0tcF13dPIn7mr8xL1PzklWh78l8xYo5VAOl5g==")))
                     );
                     this.applyImageLayoutOffsetFormatToLoadedBlocks(var6, var8);
                     var6.set(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("jZMD11okutCwYth/L0tcF13dPIn7mr8xL1PzklWh78l8xYo5VAOl5g=="), var8);
                     if (!this.containsCompactImageBlock(var8)) {
                        this.Xqedii_2e4261v.put(var5, var6);
                     }
                  }
               }

               if (var2 == null) {
                  var2 = new HashMap(var1);
               }

               var2.put((String)var4.getKey(), var6);
            }
         }

         return (Map<String, YamlConfiguration>)(var2 == null ? var1 : var2);
      } else {
         return var1;
      }
   }

   public boolean containsCompactImageBlock(List<?> var1) {
      if ((563844966 * 563844966 + 563844966 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         for (Object var3 : var1) {
            Map var4 = this.toStringObjectMap(var3);
            if (var4 != null && !var4.isEmpty()) {
               if (this.isCompactImageBlock(var4)) {
                  return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(633376462, 13) != 0);
               }

               if (var4.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("UVREse9krg9Ztxi40WBaLWzZ9MMEG3xGE4ValqIoO/Iu7YfshoCwCrjkfpI=")) instanceof List var6
                  && this.containsCompactImageBlock(var6)) {
                  return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1635301206, 8) != 0);
               }
            }
         }

         return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(395011430, 12) != 0);
      } else {
         return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 23) != 0);
      }
   }

   public List<Map<String, Object>> expandCompactImageBlocks(List<Map<String, Object>> var1) {
      if ((-1914250671 * -1914250671 + -1914250671 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         for (int var2 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 6); var2 < var1.size(); var2++) {
            Map var3 = (Map)var1.get(var2);
            if (var3 != null && !var3.isEmpty()) {
               var1.set(var2, this.expandCompactImageBlock(var3));
            }
         }

         return var1;
      } else {
         return (List<Map<String, Object>>)(var1 == null ? new ArrayList<>() : var1);
      }
   }

   public boolean hasImageLayoutOffsetSaveFormat(YamlConfiguration var1) {
      if ((-503187976 * (-503187976 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1422869413, 31) != 0);
      }
   }

   public boolean hasImageLayoutFrameOffsetSaveFormat(YamlConfiguration var1) {
      if (((-687775271 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(633376462, 13) != 0);
      }
   }

   public void markImageLayoutOffsetSaveFormat(YamlConfiguration var1) {
      if ((-705376160 ^ -705376160) != 0) {
         throw new IllegalStateException();
      }
   }

   public void markImageLayoutFrameOffsetSaveFormat(YamlConfiguration var1) {
      if (((1708008563 | 1) & 1) == 0) {
         throw new IllegalStateException();
      }
   }

   public void markImageLayoutSaveFormats(YamlConfiguration var1) {
      if ((-481257892 * -481257892 + -481257892 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         this.markImageLayoutOffsetSaveFormat(var1);
         this.markImageLayoutFrameOffsetSaveFormat(var1);
      }
   }

   public void applyImageLayoutOffsetFormatToLoadedBlocks(YamlConfiguration var1, List<Map<String, Object>> var2) {
      if ((2114531452 * 2114531452 - 2114531452 & 1) != 0) {
         throw new IllegalStateException();
      } else if (this.hasImageLayoutOffsetSaveFormat(var1)) {
         this.applyImageLayoutOffsetToBlocks(
            var2,
            Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261190654926L, 16)),
            Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251179708607709979L, 47))
         );
      }
   }

   public void applyGroupFrameOffsetToBlocks(List<Map<String, Object>> var1, double var2) {
      if ((1223003706 * (1223003706 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null
         && !var1.isEmpty()
         && Double.isFinite(var2)
         && !(Math.abs(var2) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-3564649804694252254L, 49)))) {
         for (Map var5 : var1) {
            this.applyImageGroupFrameOffsetToBlock(var5, var2);
         }
      }
   }

   public boolean setTargetText(EditorSession var1, String var2, String var3) {
      if ((1389545641 * 1389545641 - 1389545641 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var4 = this.normalizeInlineHexGradientInput(var3);
         boolean var5 = super.setTargetText(var1, var2, var4);
         boolean var6 = this.ensureTextTargetDefaultFont(var1, var2);
         return ((!var5 && !var6 ? Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 20) : Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-597052933, 17)) != 0);
      }
   }

   public String normalizeInlineHexGradientInput(String var1) {
      if (((769879415 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String var2 = var1 == null ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==") : var1;
         Matcher var3 = Xqedii_l2une6g.Xqedii_1wqn1wj.matcher(var2);
         if (!var3.find()) {
            return var2;
         } else {
            var3.reset();
            StringBuffer var4 = new StringBuffer(var2.length() + Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1248796987, 18));

            while (var3.find()) {
               String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1396510354, 1)];
               var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1544219874, 17)] = var3.group(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921877, 4));
               String var5 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
               var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1672773347, 6)];
               var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1823261425, 25)] = var3.group(Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1580078488, 14));
               String var6 = this.firstNonBlank(var10001);
               var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-532191470, 20)];
               var10001[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 20)] = var3.group(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921865, 2));
               String var7 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
               String var8 = Xqedii_2mkn5c3tbsnbq(var5, var7, var6);
               var3.appendReplacement(var4, Matcher.quoteReplacement(var8));
            }

            var3.appendTail(var4);
            return var4.toString();
         }
      }
   }

   public boolean ensureTextTargetDefaultFont(EditorSession var1, String var2) {
      if ((1964393293 * (1964393293 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Map var3 = this.resolveRawTargetByTargetId(var1, var2);
         if (var3 != null && !var3.isEmpty()) {
            String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(633368270, 13)];
            var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 30)] = this.stringValue(
               this.readMapPathValue(var3, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("T6cxqL6A1ccXT+65TMbwXhJgL77b3eXbym57Yn2Owb9p6gYK"))
            );
            var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-2048729767, 10)] = this.stringValue(
               this.readMapPathValue(var3, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("GthDDs/3c8NdwLuQ6NRoi+BI+iR3n5qLDMNd49V54hMeUYHlRkfkxPluSmRq55dj"))
            );
            var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(316700519, 12)] = this.stringValue(
               this.readMapPathValue(var3, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("d6U0PKsy42TQoL40M4bN2qTXTboRMuB3P7PMyGZStnJKz6NdAi6qEN4lqkormg=="))
            );
            var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(433702072, 26)] = this.stringValue(
               this.readMapPathValue(var3, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("A4yZCSy+UP9C+8MI2aZFn3VHKPtkB1kGzcLIMJwYTYgAv3ksRDconNZm7z7bbRWwk3c="))
            );
            var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596925957, 10)] = this.stringValue(
               this.readMapPathValue(
                  var3, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Lx+lsIrgdJVf8SlGx/4COr4tqidSzB2nGrfFsZVBb74xnlOqlpRGTfoL0qIO/vVQXejR+Fu/kRDxcEalKRY=")
               )
            );
            var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(3881111, 23)] = this.stringValue(
               this.readMapPathValue(
                  var3, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("MpvrzXhnu47rkHfbkXnbvv3lFnSbT+QwEtTD40HkmKJ56jmRoEE8uP1YTFV0aEyyy5m8Gb+oMffsqTsU")
               )
            );
            String var4 = this.firstNonBlank(var10001);
            if (!var4.isBlank()) {
               return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(395011430, 12) != 0);
            } else {
               String var5 = var3.containsKey(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("bJOeuZboreAkb2vED2EcJaFEOvsBp43s0DzWceduR53MTaGD3AssVA=="))
                  ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("A4yZCSy+UP9C+8MI2aZFn3VHKPtkB1kGzcLIMJwYTYgAv3ksRDconNZm7z7bbRWwk3c=")
                  : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("T6cxqL6A1ccXT+65TMbwXhJgL77b3eXbym57Yn2Owb9p6gYK");
               this.setMapPathValue(var3, var5, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ZyXE+YL/cEZlw8AxitYIRdiL8a83TSOKwRUrqyWtp3J79t/1U9HogE3T"));
               return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 14) != 0);
            }
         } else {
            return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1580045720, 14) != 0);
         }
      }
   }

   public boolean startGui(Player var1, String var2) {
      if (((-261973315 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return this.startGui(var1, var2, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(366593208, 26) != 0));
      }
   }

   public boolean startGui(Player var1, String var2, boolean var3) {
      if ((339850755 ^ 339850755) != 0) {
         throw new IllegalStateException();
      } else {
         return this.openGui(
            var1,
            var2,
            (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-497284372, 9) != 0),
            (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596920837, 10) != 0),
            (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 24) != 0),
            var3
         );
      }
   }

   public boolean startGuiHud(Player var1, String var2) {
      if (((-857226765 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return this.startGuiHud(var1, var2, (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 3) != 0));
      }
   }

   public boolean startGuiHud(Player var1, String var2, boolean var3) {
      if ((-314249047 * -314249047 - -314249047 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.openGui(
            var1,
            var2,
            (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(382389295, 21) != 0),
            (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 8) != 0),
            (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 7) != 0),
            var3
         );
      }
   }

   public boolean createGuiAndStartEditor(Player var1, String var2) {
      if (((-121762233 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1134875856, 15) != 0);
      } else {
         String var3 = this.normalizePageKey(var2, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ=="));
         if (var3.isBlank()) {
            this.sendEditorError(
               var1,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("o5OIg/WPu+j+Va+vt0QQXvETw8AoRWve7ImdJhJFuoM19b1oGEMYni0WEm/bLBjUOlHUUlpSwHZfDSv184dKykD5XJx9oDbckyTe5g=="),
               new Object[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 12)]
            );
            return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-869329206, 5) != 0);
         } else if (this.isInternalEditorPageName(var3)) {
            this.sendEditorError(
               var1,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "9HTOWMdjDVVoe9pNeQC63SMvbb9ZMSaZgq7IOMGrSi/ZXn+JBwO01QRsxJLEMo89BX86fSbTxm0cLvi/wjZ97E3bZ0EOW8Dl4bs/ADT/xFLxeIotL6mcJe2mYU43O5gdGqx2pUE6RhY2hkPDb+G3lsuLbo1ze9G4"
               ),
               new Object[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 24)]
            );
            return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1712819045, 4) != 0);
         } else {
            File var4 = new File(
               this.plugin.getDataFolder(), Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("zpzmjbRCq6VqCHJx7JOyiF+CFdzRUyin/A+ywR40S1z0uQAE9UHdrDAQEg+LGiQ706+lbwaV9kw=")
            );
            if (!var4.exists() && !var4.mkdirs()) {
               this.sendEditorError(
                  var1,
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "KCmqXDqnHjcwrgWxnsGZSROLVCHyucVhgIUkMYnFsRwC3LzRGicCsIr3qppO6DcoAlcCtMVImzPGWqpL+gw+kv3mw6K+CXv2U/q8CHvhDKOf23qzqg93AjbLUA6zbmOz"
                  ),
                  new Object[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1466372832, 28)]
               );
               return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(382389295, 21) != 0);
            } else {
               File var5 = new File(var4, Xqedii_3gy1wfh87kn26(var3));
               if (var5.exists()) {
                  String var13 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "YAcvOlPvwArtqZEvMP9Lj4wsf6qfTBYa4nYbnx+5Z8JBqCklQxW8PGpz9wHxyKsfbhLTAbt9k4oup70FTPJYRzp8sligKPstuTdEeA=="
                  );
                  Object[] var14 = new Object[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(37435543, 23)];
                  var14[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1153919230, 31)] = var3;
                  this.sendEditorError(var1, var13, var14);
                  return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1987128837, 0) != 0);
               } else {
                  ArrayList var6 = new ArrayList();
                  Map var7 = this.loadYamlFolder(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("zpzmjbRCq6VqCHJx7JOyiF+CFdzRUyin/A+ywR40S1z0uQAE9UHdrDAQEg+LGiQ706+lbwaV9kw="), var6
                  );
                  if (!var6.isEmpty()) {
                     this.reportYamlIssues(var6);
                     this.sendEditorError(
                        var1,
                        Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                           "FTgiRjgxc+5mE84yygYtTeJ3BKsq8yd3RQnIiiqyKThLR43YpuylN/456LpxN7FFQLn25xQglCrbWYWDffCKooXd3Rl6kRnVBTGu2pnuyzbOI3YkLh/6yVmbgvAwHypH/v1LrjMJ+kWF9xyfSvSc9XqO8ckol46Vo6Fzu2zpXHXiSO/7QdrLbQYxh/CY3qKo"
                        ),
                        new Object[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 9)]
                     );
                     return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1396510356, 1) != 0);
                  } else {
                     List var8 = Collections.emptyList();
                     YamlConfiguration var9 = (YamlConfiguration)var7.get(
                        Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("mb3Rpv55XaI9NDMoyCNO/Y0hTd8zVpyd/YvlHs789QzVVWaHK3ZAfoEzecqi6T6uIO9WTA==")
                     );
                     if (var9 != null) {
                        var8 = this.copyBlocks(var9.getList(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("jZMD11okutCwYth/L0tcF13dPIn7mr8xL1PzklWh78l8xYo5VAOl5g==")));
                     }

                     YamlConfiguration var10 = new YamlConfiguration();
                     var10.set(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Eaqx4dmxZEVb7SW51kr176NkLI4AG9nq1i0WyIXUMoayER3i"), var3);
                     var10.set(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("jZMD11okutCwYth/L0tcF13dPIn7mr8xL1PzklWh78l8xYo5VAOl5g=="), var8);

                     try {
                        var10.save(var5);
                        this.invalidateYamlFileCache(var5);
                     } catch (IOException var12) {
                        this.plugin.getLogger().warning(Xqedii_1k9i9nn3k6f71(var3, var12.getMessage()));
                        String var10002 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                           "CNKGNaZytMRPE/N/ncZ6rOiDavSF+tYjQz7tET6r03atmI3cygOgdq11Q66M31i79XUmMQUXh+XaR/4DMnba9ujdbnkjHjwgbPtvfuAZuu0="
                        );
                        Object[] var10003 = new Object[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-994569767, 10)];
                        var10003[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 16)] = var3;
                        this.sendEditorError(var1, var10002, var10003);
                        return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(366593208, 26) != 0);
                     }

                     return this.startEditor(var1, var3);
                  }
               }
            }
         }
      }
   }

   public boolean saveBuiltGui(UiBuilder var1) {
      if ((-2071155202 * -2071155202 - -2071155202 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 2) != 0);
      } else {
         String var2 = this.normalizePageKey(var1.getName(), Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ=="));
         if (!var2.isBlank() && !this.isInternalEditorPageName(var2)) {
            File var3 = new File(
               this.plugin.getDataFolder(), Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("zpzmjbRCq6VqCHJx7JOyiF+CFdzRUyin/A+ywR40S1z0uQAE9UHdrDAQEg+LGiQ706+lbwaV9kw=")
            );
            if (!var3.exists() && !var3.mkdirs()) {
               return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(45824151, 23) != 0);
            } else {
               File var4 = new File(var3, Xqedii_1q6btyady1452(var2));
               YamlConfiguration var5 = new YamlConfiguration();
               var5.set(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Eaqx4dmxZEVb7SW51kr176NkLI4AG9nq1i0WyIXUMoayER3i"), var2);
               if (var1.hasScreenSize()) {
                  var5.set(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("QOqbqiBovG4BXCKombW4Y/VHSR8O+anLdw6SK7LjRMhB0LV2IbtRm/eEuitQd4vOYAtQVg=="), var1.getScreenWidth()
                  );
                  var5.set(
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("rkdkxK7LauNnf9BDzliXpmakttgDotZoAEyYxyUxiRfWM57DDy0NVsL7P6ZrwHbCdFGPcAXa"), var1.getScreenHeight()
                  );
               }

               var5.set(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("jZMD11okutCwYth/L0tcF13dPIn7mr8xL1PzklWh78l8xYo5VAOl5g=="), var1.buildBlocks());

               try {
                  var5.save(var4);
                  this.invalidateYamlFileCache(var4);
                  return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 13) != 0);
               } catch (IOException var7) {
                  this.plugin.getLogger().warning(Xqedii_2yt5ntof97in4(var2, var7.getMessage()));
                  return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 15) != 0);
               }
            }
         } else {
            return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 11) != 0);
         }
      }
   }

   public boolean deleteGui(Player var1, String var2) {
      if (((-1212336968 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(316692327, 12) != 0);
      } else {
         String var3 = this.normalizePageKey(var2, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ=="));
         if (var3.isBlank()) {
            this.sendEditorError(
               var1,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("o5OIg/WPu+j+Va+vt0QQXvETw8AoRWve7ImdJhJFuoM19b1oGEMYni0WEm/bLBjUOlHUUlpSwHZfDSv184dKykD5XJx9oDbckyTe5g=="),
               new Object[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 31)]
            );
            return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 22) != 0);
         } else if (this.isInternalEditorPageName(var3)) {
            this.sendEditorError(
               var1,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "RjJTI29drAZ3OrDNTbtPmXalGYiDhJID6O246x13UHptM/C09CVADauIgXNC6PQkFjYt6Q+qp1Ft5ndaPUw0EewVovh3qri3t7sQZXF105VrfoltsQt47HBcGh5oTkjMm0n35prevURPl6N1"
               ),
               new Object[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(817650475, 7)]
            );
            return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 27) != 0);
         } else {
            for (EditorSession var5 : this.editorSessions.values()) {
               if (var5 != null && var5.editMode) {
                  String var6 = this.normalizePageKey(var5.pageName, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ=="));
                  if (var3.equalsIgnoreCase(var6)) {
                     this.sendEditorError(
                        var1,
                        Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                           "h1KAYEV/qK/FlQ7J48YV0C/sm3U5cT4rP2URWorHa4iXY6CGxLpfumZxpS4pV/65iQ2jY/39lBpi0VDgE1VwRZ4b98E915P9wB5VdNN8YqYcsnAb5DWC84kV5GfsphpzpACpL5NlPUD5Wp6kgdJKSk0CUVoiU+QEv6YEFw=="
                        ),
                        new Object[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-489072251, 18)]
                     );
                     return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 25) != 0);
                  }
               }
            }

            File var8 = new File(
               new File(
                  this.plugin.getDataFolder(),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("zpzmjbRCq6VqCHJx7JOyiF+CFdzRUyin/A+ywR40S1z0uQAE9UHdrDAQEg+LGiQ706+lbwaV9kw=")
               ),
               Xqedii_1qv0cqpgkqr0j(var3)
            );
            if (var8.exists() && var8.isFile()) {
               try {
                  Files.delete(var8.toPath());
                  this.invalidateYamlFileCache(var8);

                  for (List var10 : this.playerEditorOpenPages.values()) {
                     var10.removeIf(
                        var2x -> var3.equalsIgnoreCase(
                           this.normalizePageKey((String) var2x, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ=="))
                        )
                     );
                  }

                  String var12 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "GAhS+I7wj62U/oXID8ZofbpQk6UCftq6q44rpVj4jzeBCnTfRxq3O429fB9HrjxeauSBoU+l4RD1JBhTR8K/+1nn4oeUr72r4aTUr80FOrc="
                  );
                  Object[] var14 = new Object[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 23)];
                  var14[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1544219874, 17)] = var3;
                  this.sendEditorRemoved(var1, var12, var14);
                  return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1989139533, 11) != 0);
               } catch (IOException var7) {
                  this.plugin.getLogger().warning(Xqedii_71sr2km80v7b(var3, var7.getMessage()));
                  String var11 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "VVAIfxR76o+zrQWtKHoSn5Yd9CAq46p/wQQ5aJOF9eDFI6lq5/tBnHl+ZWnnRGIzdHz8i63dlxIJRl+PPixi8834hBFDbS4twu/wV/o6YJc="
                  );
                  Object[] var13 = new Object[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 22)];
                  var13[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(764778590, 22)] = var3;
                  this.sendEditorError(var1, var11, var13);
                  return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1823261425, 25) != 0);
               }
            } else {
               String var10002 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "L0cc8O9S1cJsgIqDpNjbhd/UXKyyeNRiKljvJr+3LX4Jgc+kM/HhdQPs6kFL+nM6S/mEAobDIcbPaUEiqVKgGyO9kEbB75xAH0SiGQ=="
               );
               Object[] var10003 = new Object[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 13)];
               var10003[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1153919230, 31)] = var3;
               this.sendEditorError(var1, var10002, var10003);
               return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1823261425, 25) != 0);
            }
         }
      }
   }

   public boolean startEditor(Player var1, String var2) {
      if ((676140402 * 676140402 + 676140402 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         if (var1 != null) {
            UUID var3 = this.findActiveEditorOwnerForPage(var2, var1.getUniqueId());
            if (var3 != null) {
               return this.notifyEditorPageLocked(var1, var2, var3);
            }
         }

         if (var1 != null && this.isEditorOpen(var1) && this.cameraService.isCameraActive(var1)) {
            Location var7 = this.cameraService.getCameraBaseLocation(var1);
            this.cameraService.forceStop(var1);
            if (var7 != null && var7.getWorld() != null) {
               this.plugin.suppressEditorTeleportClose(var1, Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261190441971L, 15));
               Xqedii_38o2zes.Xqedii_fxq2htx(var1, var7);
            }
         }

         this.normalizeEditorStartPitch(var1);
         int var8 = var1 != null && this.isEditorOpen(var1) && this.cameraService.isCameraActive(var1)
            ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1672773347, 6)
            : Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 24);
         boolean var4 = this.openGui(var1, var2, (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 1) != 0), (var8 != 0));
         if (var4 && var1 != null) {
            EditorSession var5 = (EditorSession)this.editorSessions.get(var1.getUniqueId());
            String var6 = var5 == null ? this.normalizePageKey(var2, var2) : var5.pageName;
            this.rememberOpenedEditorPage(var1.getUniqueId(), var6);
            this.playEditorSfx(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("VzenC4SWrlSIhsHMknTmbsZ5RSkcn5PL/JOlQE6/btsvontuHgLdchu1BVvsuX6qscmiXs19"));
         }

         return var4;
      }
   }

   public void normalizeEditorStartPitch(Player var1) {
      if ((-1745270745 * -1745270745 + -1745270745 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         Location var2 = var1.getLocation();
         if (var2 != null && var2.getWorld() != null) {
            float var3 = var2.getPitch();
            if (Float.isFinite(var3) && !(var3 <= Float.intBitsToFloat(Xqedii_p92kfr8.Xqedii_vjb2772t99mu(351609146, 31)))) {
               Xqedii_38o2zes.Xqedii_1clfsd0(var1, var2.getYaw(), Float.intBitsToFloat(Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242716922, 17)));
            }
         }
      }
   }

   public boolean isEditorOpen(Player var1) {
      if (((-1758471848 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1785176531, 30) != 0);
      } else {
         EditorSession var2 = (EditorSession)this.editorSessions.get(var1.getUniqueId());
         return ((var2 != null && var2.editMode && var2.previewMode
            ? Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596925957, 12)
            : Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(316692327, 12)) != 0);
      }
   }

   public UUID findActiveEditorOwnerForPage(String var1, UUID var2) {
      if ((15110764 * 15110764 - 15110764 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var3 = this.normalizePageKey(var1, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ=="));
         if (!var3.isBlank() && !this.isInternalEditorPageName(var3) && !this.editorSessions.isEmpty()) {
            for (Entry var5 : this.editorSessions.entrySet()) {
               UUID var6 = (UUID)var5.getKey();
               if (var6 != null && (var2 == null || !var2.equals(var6))) {
                  EditorSession var7 = (EditorSession)var5.getValue();
                  if (var7 != null && var7.editMode && var7.previewMode) {
                     String var8 = this.normalizePageKey(var7.pageName, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ=="));
                     if (var3.equalsIgnoreCase(var8)) {
                        return var6;
                     }
                  }
               }
            }

            return null;
         } else {
            return null;
         }
      }
   }

   public String resolveActiveEditorOwnerName(UUID var1) {
      if (((-1753546290 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("VtnX1zUatGZ0HnNQDeOeTZ9M7ZkU5Ho8HCmdiZ/as9Wblw+gGNTs5Y9CSpiHKo7sqy+xhv/3ykw=");
      } else {
         Player var2 = Bukkit.getPlayer(var1);
         String var10000;
         if (var2 == null) {
            var10000 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
         } else {
            String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921893, 5)];
            var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1989137485, 11)] = var2.getName();
            var10000 = this.firstNonBlank(var10001).trim();
         }

         String var3 = var10000;
         return var3.isBlank() ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("VtnX1zUatGZ0HnNQDeOeTZ9M7ZkU5Ho8HCmdiZ/as9Wblw+gGNTs5Y9CSpiHKo7sqy+xhv/3ykw=") : var3;
      }
   }

   public String buildEditorPageLockedMessage(String var1, UUID var2) {
      if ((578955092 * (578955092 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var3 = this.normalizePageKey(var1, var1);
         String var4 = this.resolveActiveEditorOwnerName(var2);
         String var10000 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
            "a63tLqpg8qYmAqwPXjeiBAzQylQqqT19bylMEf5CQ65gu0Dtvy0epzQ0XKBwsLoV8g7P2maYKsDlnIyWsjpJ2aLhnv3syKKN1JrHl5O1io9XJD1/FggP3TlscnKFw4MfE9vPBAyDpeDKF9z1"
         );
         Object[] var10001 = new Object[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1501946593, 2)];
         var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1501946585, 2)] = var3;
         var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-320709624, 1)] = var4;
         return ChatColorUtil.Xqedii_1t2nnko(var10000, var10001);
      }
   }

   public boolean sendPageLockedMessageIfPresent(Player var1, String var2) {
      if ((-833736200 ^ -833736200) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(733186416, 27) != 0);
      } else {
         UUID var3 = this.findActiveEditorOwnerForPage(var2, var1.getUniqueId());
         if (var3 == null) {
            return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1266769308, 14) != 0);
         } else {
            String var4 = this.buildEditorPageLockedMessage(var2, var3);
            ChatColorUtil.send(var1, Xqedii_3uxgwyg.Xqedii_2p0af0b.deserialize(var4));
            return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(768972894, 22) != 0);
         }
      }
   }

   public boolean notifyEditorPageLocked(Player var1, String var2, UUID var3) {
      if ((1404871217 * (1404871217 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 22) != 0);
      } else {
         this.sendEditorPlayerMessage(var1, Xqedii_3uxgwyg.Xqedii_2p0af0b.deserialize(this.buildEditorPageLockedMessage(var2, var3)));
         return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1024365396, 9) != 0);
      }
   }

   public boolean showSelectedPivot(Player var1) {
      if (((59096971 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-2124571573, 22) != 0);
      } else {
         EditorSession var2 = (EditorSession)this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && var2.editMode) {
            String var3 = this.resolvePivotCommandTargetId(var2);
            if (var3.isBlank()) {
               this.removeHudById(
                  var1,
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "8Yhd/mjRUkPmm2Vj1DbDv1ECs7yrU96nmIoZTMwAKvu0JxILkMDzGr1dhcs8VeGbUTDAEBpEYKrKE2a9G1DQKME4ujDeYnMl+GkAsLp+GlQtcW66lmHp6d+k"
                  )
               );
               this.sendEditorError(
                  var1,
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "8TMAb4F0R54PV6TxMjT549i735tvGuvgEgN1ZwbZHOiF68FXQ79tANsuov3OVGAS3zDV0EOdDp5B2fm7bjtRermPCBX9kmBajXYL7A=="
                  ),
                  new Object[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 22)]
               );
               return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1899092544, 29) != 0);
            } else {
               EditorImageDragPart.PivotTargetData var4 = this.resolvePivotTargetData(var2, var3);
               if (var4 == null) {
                  this.removeHudById(
                     var1,
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                        "8Yhd/mjRUkPmm2Vj1DbDv1ECs7yrU96nmIoZTMwAKvu0JxILkMDzGr1dhcs8VeGbUTDAEBpEYKrKE2a9G1DQKME4ujDeYnMl+GkAsLp+GlQtcW66lmHp6d+k"
                     )
                  );
                  this.sendEditorError(
                     var1,
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                        "ZRt17sNuMZ4t3N2X22F+Jruo/YrCe69+FPPS/EuLHI3h+BkyjOLqQPxzMFaGQ/gHZrmrWGxHyPn0/BfzOjb4XQUIF2dwvo8vzYDZ7XSTdLkA+zudGclXhOQ0E3lSQfnu0b53uF7iMQIvbPaydpM="
                     ),
                     new Object[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 20)]
                  );
                  return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 21) != 0);
               } else {
                  this.renderPivotDebugMarker(var1, var2, var4.Xqedii_1sksyxo, var4.Xqedii_2sc9vea);
                  String var5 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "J78R9d20HG4H6G5oFydoY3GNrjDlXrU/vkrIRVjQvcSM9eAKrO9EV7L8G8PEJ4lEa0KlwO90tbcyWZHzclnN4dcswM0xFZWYfASEWQ2uGKkNPhEc4GBbnmjYPQaode6sbQ1gCjvGms+xBVd7Yg+LkQ=="
                  );
                  Object[] var6 = new Object[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1267933499, 23)];
                  var6[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 10)] = Xqedii_11jyyg9tm1vyu(
                     this.formatPivotRatio(var4.Xqedii_2cx2nc5), this.formatPivotRatio(var4.Xqedii_39wfhog)
                  );
                  var6[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(1550561787, 31)] = Xqedii_sx8pc51x9y03(
                     this.formatPx(var4.Xqedii_3qpdp0j), this.formatPx(var4.Xqedii_3e650be)
                  );
                  var6[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1251156283, 23)] = Xqedii_1n0tfvi7yzs59(
                     this.formatPx(var4.Xqedii_1sksyxo), this.formatPx(var4.Xqedii_2sc9vea)
                  );
                  var6[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(633392846, 13)] = var4.Xqedii_293ks5z
                     ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("B9uSAXFw3enWKxaAUhOJL1NR44ZoDI7s25lfTVCkqEo+H2H4NSedmCSyF7g8XpgU")
                     : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("6GLE5Dr9LU7l+3Gg9R7X7WnYO0wTrTfmWmVZD669F3N6EZ0AUpRaAPQ/ZXk=");
                  this.sendEditorInfo(var1, var5, var6);
                  return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1987128836, 0) != 0);
               }
            }
         } else {
            this.removeHudById(
               var1,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "8Yhd/mjRUkPmm2Vj1DbDv1ECs7yrU96nmIoZTMwAKvu0JxILkMDzGr1dhcs8VeGbUTDAEBpEYKrKE2a9G1DQKME4ujDeYnMl+GkAsLp+GlQtcW66lmHp6d+k"
               )
            );
            String var10002 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
               "QXIAJJssOK+KmfKhjWncWWawyp+dL6xqAEPJvKXjuE4PjWAw9sbz9PE9fP2yrHsG7P4Fh1QUh72UOc3sJ63cre7T3FwrQVlEHh2pmfOlOpLvgUlFRtLneR2q"
            );
            Object[] var10003 = new Object[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921989, 7)];
            var10003[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 3)] = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
               "NTK2BQRO5Rhct7ZBeVMSF0vsrSg4PT/YtkCmUIDNfJw45lQh6MTCH462PaaxUzK54NbR/xM5wkWltfO3"
            );
            this.sendEditorError(var1, var10002, var10003);
            return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-497284884, 9) != 0);
         }
      }
   }

   public boolean setSelectedPivot(Player var1, String[] var2) {
      if ((1741168783 * 1741168783 - 1741168783 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(316692327, 12) != 0);
      } else {
         EditorSession var3 = (EditorSession)this.editorSessions.get(var1.getUniqueId());
         if (var3 != null && var3.editMode) {
            String var4 = this.resolvePivotCommandTargetId(var3);
            if (var4.isBlank()) {
               this.removeHudById(
                  var1,
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "8Yhd/mjRUkPmm2Vj1DbDv1ECs7yrU96nmIoZTMwAKvu0JxILkMDzGr1dhcs8VeGbUTDAEBpEYKrKE2a9G1DQKME4ujDeYnMl+GkAsLp+GlQtcW66lmHp6d+k"
                  )
               );
               this.sendEditorError(
                  var1,
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                     "8TMAb4F0R54PV6TxMjT549i735tvGuvgEgN1ZwbZHOiF68FXQ79tANsuov3OVGAS3zDV0EOdDp5B2fm7bjtRermPCBX9kmBajXYL7A=="
                  ),
                  new Object[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 18)]
               );
               return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 14) != 0);
            } else {
               EditorImageDragPart.PivotTargetData var5 = this.resolvePivotTargetData(var3, var4);
               if (var5 == null) {
                  this.removeHudById(
                     var1,
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                        "8Yhd/mjRUkPmm2Vj1DbDv1ECs7yrU96nmIoZTMwAKvu0JxILkMDzGr1dhcs8VeGbUTDAEBpEYKrKE2a9G1DQKME4ujDeYnMl+GkAsLp+GlQtcW66lmHp6d+k"
                     )
                  );
                  this.sendEditorError(
                     var1,
                     Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                        "ZRt17sNuMZ4t3N2X22F+Jruo/YrCe69+FPPS/EuLHI3h+BkyjOLqQPxzMFaGQ/gHZrmrWGxHyPn0/BfzOjb4XQUIF2dwvo8vzYDZ7XSTdLkA+zudGclXhOQ0E3lSQfnu0b53uF7iMQIvbPaydpM="
                     ),
                     new Object[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 25)]
                  );
                  return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596659717, 18) != 0);
               } else {
                  EditorImageDragPart.ParsedPivotInput var6 = this.parsePivotInputFromArgs(var2);
                  if (var6 == null) {
                     this.sendEditorUsage(
                        var1,
                        Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("7uySm6j4tqkVZCZ28SJNtu4pA/5SEH6N0KjG2iw7veLBonBQ0jrZ8P8ZtkHGZ4Kiuq3QbA4g"),
                        Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                           "wNAi5hFWRAjRtBY8tDTJiHAvWV/okG1xzhDyMTMdR3ql+M6gC2jt9Atp/1uNZJwcdN8BgXdwLvBFgeQzY/mTqb5OBSkIVW8LrrZfi+lpG7GvRXMZQe6ZAiJawSPj3VFWZ3oFETVBEg8="
                        )
                     );
                     return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-977620213, 19) != 0);
                  } else {
                     double var7 = var6.Xqedii_md2611d ? var6.Xqedii_1qdc7cz * var5.Xqedii_3pgugus : var6.Xqedii_1qdc7cz;
                     double var9 = var6.Xqedii_md2611d ? var6.Xqedii_19ds0pz * var5.Xqedii_dy0gqnx : var6.Xqedii_19ds0pz;
                     if (Double.isFinite(var7) && Double.isFinite(var9)) {
                        Map var11 = this.resolveRawTargetByTargetId(var3, var4);
                        if (var11 == null) {
                           this.sendEditorError(
                              var1,
                              Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                                 "eZ+CuxFt1FKf9dR6075Bb3OYk2pBDFn+Ft2Z6V1VNrdiQHR4Q6SHiHD7CrJ848musbZUzwMEKPfVAYDtv4fcCzx0oCGPd9gmDyuAnq9yuvEMB9LaO9KdWzRzeL68Vz/IeVXexgio"
                              ),
                              new Object[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 31)]
                           );
                           return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(149742172, 25) != 0);
                        } else if (Math.abs(var5.Xqedii_3qpdp0j - var7)
                              < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-4261719463634882128L, 6))
                           && Math.abs(var5.Xqedii_3e650be - var9) < Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(-5881532156580577940L, 7))) {
                           this.renderPivotDebugMarker(var1, var3, var5.Xqedii_1sksyxo, var5.Xqedii_2sc9vea);
                           this.sendEditorError(
                              var1,
                              Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                                 "t0nke6fLTDHwlb8Mm2FQSaXgubr+naOh6JrKSWC7rgjAokAJiSbnNGyHd11c0TcXB24tOSbMDkegNskXCFcXckMmf8L5odYvQCrF0ep3C+0OjvWknyDDMg=="
                              ),
                              new Object[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 0)]
                           );
                           return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-601116165, 22) != 0);
                        } else {
                           this.recordEditorMutation(var3);
                           this.setMapPathValue(
                              var11, this.resolvePivotWritePath(var11, (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1580062104, 14) != 0)), var6.Xqedii_1qdc7cz
                           );
                           this.setMapPathValue(
                              var11, this.resolvePivotWritePath(var11, (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 31) != 0)), var6.Xqedii_19ds0pz
                           );
                           this.setMapPathValue(var11, this.resolvePivotNormalizedWritePath(var11), var6.Xqedii_md2611d);
                           this.rerenderEditableSelection(var1, var3);
                           EditorImageDragPart.PivotTargetData var12 = this.resolvePivotTargetData(var3, var4);
                           if (var12 != null) {
                              this.renderPivotDebugMarker(var1, var3, var12.Xqedii_1sksyxo, var12.Xqedii_2sc9vea);
                              String var16 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                                 "DtAkXS1hURV3t+H0xqu32rQF4FWHCMnbRpZatXWN8BHpZEf8XVtdWfJict1r7KeTUQpwE6AwDytVPi5TQzQqdBlnhmnGJc1rYw4c7uVv473VVbdr45FcV9m1zkzr9LN7HOHqjkS11CAtaY4d3YLDB/ZQJRYGB/wpGo9FOBHB"
                              );
                              Object[] var18 = new Object[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1898840438, 8)];
                              var18[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1635300950, 8)] = Xqedii_383sz7xo3wy44(
                                 this.formatPivotRatio(var12.Xqedii_2cx2nc5), this.formatPivotRatio(var12.Xqedii_39wfhog)
                              );
                              var18[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1024364884, 9)] = Xqedii_klq7277hcekz(
                                 this.formatPx(var12.Xqedii_3qpdp0j), this.formatPx(var12.Xqedii_3e650be)
                              );
                              var18[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596925957, 11)] = Xqedii_3ib8evgawn89i(
                                 this.formatPx(var12.Xqedii_1sksyxo), this.formatPx(var12.Xqedii_2sc9vea)
                              );
                              var18[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1244864827, 20)] = var12.Xqedii_293ks5z
                                 ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("B9uSAXFw3enWKxaAUhOJL1NR44ZoDI7s25lfTVCkqEo+H2H4NSedmCSyF7g8XpgU")
                                 : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("6GLE5Dr9LU7l+3Gg9R7X7WnYO0wTrTfmWmVZD669F3N6EZ0AUpRaAPQ/ZXk=");
                              this.sendEditorSuccess(var1, var16, var18);
                              return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1197937376, 28) != 0);
                           } else {
                              String var13 = this.formatPivotRatio(
                                 var5.Xqedii_3pgugus <= Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(-3253702359248824032L, 4))
                                    ? Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251105732091003675L, 42))
                                    : var7 / var5.Xqedii_3pgugus
                              );
                              String var14 = this.formatPivotRatio(
                                 var5.Xqedii_dy0gqnx <= Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(-2372496140983949497L, 2))
                                    ? Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-2167186655870634036L, 63))
                                    : var9 / var5.Xqedii_dy0gqnx
                              );
                              String var15 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                                 "8rYFvGDto299jIoUBk0z9K07QtK/0HZlofayxxgfthWv7JAEW/BWb1c+KIJ6QizVK8vqJKMrxTBlXFCzVLRI7HoDGdSJZfze6P+KlYEoTdmZ94fpV9jKSA53vZoumQWdHGg="
                              );
                              Object[] var17 = new Object[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(817650859, 7)];
                              var17[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 24)] = Xqedii_36ki05lkvd4x3(var13, var14);
                              var17[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(2025281121, 16)] = Xqedii_1dzll8epa676t(this.formatPx(var7), this.formatPx(var9));
                              var17[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(2025346657, 16)] = var6.Xqedii_md2611d
                                 ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("B9uSAXFw3enWKxaAUhOJL1NR44ZoDI7s25lfTVCkqEo+H2H4NSedmCSyF7g8XpgU")
                                 : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("6GLE5Dr9LU7l+3Gg9R7X7WnYO0wTrTfmWmVZD669F3N6EZ0AUpRaAPQ/ZXk=");
                              this.sendEditorSuccess(var1, var15, var17);
                              return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-601116165, 22) != 0);
                           }
                        }
                     } else {
                        this.sendEditorError(
                           var1,
                           Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                              "O+E1GAvfbhpq1fTMXbRudGmEf6IK1gDoOkMCRmomOJ6MEFu3jQXmGw6GtiRe/zbAY425Mx0EhRANXlHW3pZvmQuijTac9zMn6q5AlrjEDjo="
                           ),
                           new Object[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 26)]
                        );
                        return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921925, 6) != 0);
                     }
                  }
               }
            }
         } else {
            this.removeHudById(
               var1,
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "8Yhd/mjRUkPmm2Vj1DbDv1ECs7yrU96nmIoZTMwAKvu0JxILkMDzGr1dhcs8VeGbUTDAEBpEYKrKE2a9G1DQKME4ujDeYnMl+GkAsLp+GlQtcW66lmHp6d+k"
               )
            );
            String var10002 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
               "QXIAJJssOK+KmfKhjWncWWawyp+dL6xqAEPJvKXjuE4PjWAw9sbz9PE9fP2yrHsG7P4Fh1QUh72UOc3sJ63cre7T3FwrQVlEHh2pmfOlOpLvgUlFRtLneR2q"
            );
            Object[] var10003 = new Object[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(299484344, 26)];
            var10003[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 15)] = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
               "NTK2BQRO5Rhct7ZBeVMSF0vsrSg4PT/YtkCmUIDNfJw45lQh6MTCH462PaaxUzK54NbR/xM5wkWltfO3"
            );
            this.sendEditorError(var1, var10002, var10003);
            return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596925957, 12) != 0);
         }
      }
   }

   public String resolvePivotCommandTargetId(EditorSession var1) {
      if (((-2030050253 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1219075720, 24)];
         var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 27)] = this.getSidebarEditableTargetId(var1);
         String var2 = this.firstNonBlank(var10001);
         if (!var2.isBlank()) {
            return var2;
         } else {
            List var3 = this.getSelectedTargetIds(var1);
            if (var3.isEmpty()) {
               return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
            } else {
               var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 29)];
               var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1881912201, 19)] = (String)var3.getFirst();
               return this.firstNonBlank(var10001);
            }
         }
      }
   }

   public EditorImageDragPart.PivotTargetData resolvePivotTargetData(EditorSession var1, String var2) {
      if (((957977903 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         double var3 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-1288574988609770157L, 39));
         double var5 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-10230983206923418L, 28));
         double var7 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893194551536627L, 38));
         double var9 = Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893263069686771L, 40));
         HoverElement var11 = this.findFirstByTargetId(var1, var2);
         if (var11 != null && var11.baseLocation != null && var11.baseScale != null) {
            var3 = var11.baseLocation.getX();
            var5 = var11.baseLocation.getY();
            var7 = Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5250264332965331739L, 50)), var11.baseScale.getX());
            var9 = Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-20461966279760179L, 29)), var11.baseScale.getY());
         } else {
            EditorRect var12 = this.getTargetBounds(var1, var2);
            if (var12 == null) {
               return null;
            }

            var3 = var12.x;
            var5 = var12.y;
            var7 = Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-1840387783254513L, 63)), var12.width);
            var9 = Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-654782920952325697L, 34)), var12.height);
         }

         Map var38 = this.resolveRawTargetByTargetId(var1, var2);
         double var13 = var7 * Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-1388814780311213215L, 7));
         double var15 = var9 * Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-972325310721732781L, 39));
         boolean var17 = this.hasPivotRawValue(var38, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-994569767, 10) != 0));
         boolean var18 = this.hasPivotRawValue(var38, (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-869329206, 5) != 0));
         double var19 = this.readPivotValueFromRaw(var38, var13, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1729290311, 4) != 0));
         double var21 = this.readPivotValueFromRaw(var38, var15, (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 2) != 0));
         Boolean var23 = this.readPivotNormalizedSettingFromRaw(var38);
         boolean var24 = this.isPivotAxisNormalized(var19, var17, var23);
         boolean var25 = this.isPivotAxisNormalized(var21, var18, var23);
         double var26 = var24 ? var19 * var7 : var19;
         double var28 = var25 ? var21 * var9 : var21;
         double var30 = var7 <= Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(7092512305037334744L, 5))
            ? Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-1112876935155633404L, 23))
            : var26 / var7;
         double var32 = var9 <= Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-3744757177893672060L, 36))
            ? Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(4699007232214619107L, 26))
            : var28 / var9;
         return new EditorImageDragPart.PivotTargetData(
            var7,
            var9,
            var19,
            var21,
            ((!var24 && !var25 ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(183296604, 25) : Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 7)) != 0),
            var30,
            var32,
            var26,
            var28,
            var3 + var26,
            var5 + var28
         );
      } else {
         return null;
      }
   }

   public double readPivotValueFromRaw(Map<String, Object> var1, double var2, boolean var4) {
      if ((-350121674 ^ -350121674) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return var2;
      } else {
         String[] var5 = var4 ? Xqedii_l2une6g.Xqedii_1lmy1tb : Xqedii_l2une6g.Xqedii_211b382;
         String[] var6 = var5;
         int var7 = var5.length;

         for (int var8 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 30); var8 < var7; var8++) {
            String var9 = var6[var8];
            if (this.hasMapPath(var1, var9)) {
               double var10 = this.readMapPathDouble(var1, var9, Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(2166196225490899950L, 47)));
               if (Double.isFinite(var10)) {
                  return var10;
               }
            }
         }

         return var2;
      }
   }

   public boolean hasPivotRawValue(Map<String, Object> var1, boolean var2) {
      if ((-620397757 ^ -620397757) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 3) != 0);
      } else {
         String[] var3 = var2 ? Xqedii_l2une6g.Xqedii_1lmy1tb : Xqedii_l2une6g.Xqedii_211b382;
         String[] var4 = var3;
         int var5 = var3.length;

         for (int var6 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(382389295, 21); var6 < var5; var6++) {
            String var7 = var4[var6];
            if (this.hasMapPath(var1, var7)) {
               return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-865357317, 28) != 0);
            }
         }

         return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 22) != 0);
      }
   }

   public Boolean readPivotNormalizedSettingFromRaw(Map<String, Object> var1) {
      if ((-311309746 * (-311309746 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return null;
      } else {
         String[] var2 = Xqedii_l2une6g.Xqedii_1lfh5rh;
         int var3 = var2.length;

         for (int var4 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 13); var4 < var3; var4++) {
            String var5 = var2[var4];
            if (this.hasMapPath(var1, var5)) {
               Object var6 = this.readMapPathValue(var1, var5);
               return this.parseBooleanFlag(var6, (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 29) != 0));
            }
         }

         var2 = Xqedii_l2une6g.Xqedii_26iw361;
         var3 = var2.length;

         for (int var9 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1134875856, 15); var9 < var3; var9++) {
            String var10 = var2[var9];
            if (this.hasMapPath(var1, var10)) {
               String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-597446149, 19)];
               var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 9)] = this.stringValue(this.readMapPathValue(var1, var10));
               String var11 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
               if (!var11.isBlank()) {
                  if (var11.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("rUVlB4MEJq1TfuGPo9x1ooLem/WxzVBg6RrE0MN4NpYhjrnz"))
                     || var11.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("kaTnNpd+1Rweprcu9ssFED53UAmrwLnpe7YeYNhw85DSPGeok18="))
                     || var11.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("PiXyCoN+rfRAtOzJOvA5KvZKG+lacZ43u3AMzs3DwiQTgPi2zbqcmHs1"))) {
                     return Boolean.valueOf((Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(790031052, 13) != 0));
                  }

                  if (var11.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("lc8Yhismdh/9Ft9flIaQNz4lVw/qHovwkVBCi4h585J1VA=="))
                     || var11.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("9I2HTfibM7fRiYTXDaJD9ESMTRCDpFa2wfzIbnPmpGk="))
                     || var11.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("LZOnZghHT+PJ6edHZdys7EdAC1NRomm0JyZGnTvHoqFSjZC7M+0="))
                     || var11.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+VTmM6EySGVn7Z6Wl+Ifkl2ZUFpPMpi6CAxefGKq6nDVMPMnkIHEhA=="))) {
                     return Boolean.valueOf((Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 27) != 0));
                  }
               }
            }
         }

         return null;
      }
   }

   public boolean isPivotAxisNormalized(double var1, boolean var3, Boolean var4) {
      if ((-1872232204 * -1872232204 - -1872232204 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var4 != null) {
         return var4;
      } else if (var3 && Double.isFinite(var1)) {
         return ((var1 >= Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-4468696529373594173L, 52))
               && var1 <= Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-8406818044940124579L, 21))
            ? Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(1550561787, 31)
            : Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(366593208, 26)) != 0);
      } else {
         return (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 3) != 0);
      }
   }

   public String resolvePivotWritePath(Map<String, Object> var1, boolean var2) {
      if ((1315962032 * 1315962032 + 1315962032 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return var2
            ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("WYBuvkfMyNNHbQDrqObBkRax3Nx+QKp4TWT0cMvO/HpwU/hewVvJQUFK")
            : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("grjDrroFIBOODm2i+jSzpkEhJ4zhRuklwqyElgVdZe2xcnA4wC8LeF3y");
      } else {
         String[] var3 = var2 ? Xqedii_l2une6g.Xqedii_1lmy1tb : Xqedii_l2une6g.Xqedii_211b382;
         String[] var4 = var3;
         int var5 = var3.length;

         for (int var6 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 22); var6 < var5; var6++) {
            String var7 = var4[var6];
            if (this.hasMapPath(var1, var7)) {
               return var7;
            }
         }

         if (var1.containsKey(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("bJOeuZboreAkb2vED2EcJaFEOvsBp43s0DzWceduR53MTaGD3AssVA=="))) {
            return var2
               ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("fkPggxiUXiq0/SaMFaqUXIZ6OFGHjxX0/XnkUukcFMypBCLyqX9x6hy6/ljspiQ0LBge2Ek2JSA=")
               : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Kp/LaLwYJSNe3IUI3pwmBNg5q3OzADNr7q/1fCUkaFbqTSaayaHUXKzmWaKeWwQGEJLiYDm76jI=");
         } else {
            return var2
               ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("WYBuvkfMyNNHbQDrqObBkRax3Nx+QKp4TWT0cMvO/HpwU/hewVvJQUFK")
               : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("grjDrroFIBOODm2i+jSzpkEhJ4zhRuklwqyElgVdZe2xcnA4wC8LeF3y");
         }
      }
   }

   public String resolvePivotNormalizedWritePath(Map<String, Object> var1) {
      if ((-1904370353 * -1904370353 - -1904370353 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ABBFvxc4dUOreZFs3yy+3eCieTXDwFMonx49EWbr4o7dmGn8yVBmZc659BYZ8w2O056Bf3axXfdr9V1p");
      } else {
         String[] var2 = Xqedii_l2une6g.Xqedii_1lfh5rh;
         int var3 = var2.length;

         for (int var4 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-648444446, 26); var4 < var3; var4++) {
            String var5 = var2[var4];
            if (this.hasMapPath(var1, var5)) {
               return var5;
            }
         }

         return var1.containsKey(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("bJOeuZboreAkb2vED2EcJaFEOvsBp43s0DzWceduR53MTaGD3AssVA=="))
            ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("rjIliQzwxxttfqye03EVJK8ucn4ZogBYbmESRao5SEzjFA2Knk8faC1HuS7qjol33FpseioM6bkqeSJGt9x7VxUAlnE+qyoXn6Q=")
            : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ABBFvxc4dUOreZFs3yy+3eCieTXDwFMonx49EWbr4o7dmGn8yVBmZc659BYZ8w2O056Bf3axXfdr9V1p");
      }
   }

   public EditorImageDragPart.ParsedPivotInput parsePivotInputFromArgs(String[] var1) {
      if (((-1999021715 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.length >= Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1898841974, 8)) {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921869, 3)];
         var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1570524033, 30)] = var1[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 8)];
         String var2 = this.firstNonBlank(var10001).trim();
         if (var2.isBlank()) {
            return null;
         } else if (var2.equalsIgnoreCase(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("SRESUnKOTLpk9vMDUx1kiYXT52P/m5t8yovPY0iYxan4/0WdR87zig=="))
            || var2.equalsIgnoreCase(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ZyXE+YL/cEZlw8AxitYIRdiL8a83TSOKwRUrqyWtp3J79t/1U9HogE3T"))
            || var2.equalsIgnoreCase(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("xwdXGkGBr/dECK/76Coz6hDCdptH0/o9PhLtlPRfsb8cJ5mgDGc="))) {
            return new EditorImageDragPart.ParsedPivotInput(
               Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-1485646118710622020L, 55)),
               Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-7131372403907706885L, 13)),
               (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-60050949, 29) != 0)
            );
         } else if (var2.equalsIgnoreCase(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("B9uSAXFw3enWKxaAUhOJL1NR44ZoDI7s25lfTVCkqEo+H2H4NSedmCSyF7g8XpgU"))
            || var2.equalsIgnoreCase(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("kaTnNpd+1Rweprcu9ssFED53UAmrwLnpe7YeYNhw85DSPGeok18="))
            || var2.equalsIgnoreCase(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+pt8WjTPtsidMv63ncX1NodKgHR80OxGkMoHK1Lygf/4iw=="))) {
            double[] var6 = this.parsePivotPairFromArgs(var1, Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1251156283, 23));
            return var6 == null
               ? null
               : new EditorImageDragPart.ParsedPivotInput(
                  var6[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-869329206, 5)],
                  var6[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1206789692, 18)],
                  (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-836386674, 5) != 0)
               );
         } else if (!var2.equalsIgnoreCase(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("lc8Yhismdh/9Ft9flIaQNz4lVw/qHovwkVBCi4h585J1VA=="))
            && !var2.equalsIgnoreCase(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("6GLE5Dr9LU7l+3Gg9R7X7WnYO0wTrTfmWmVZD669F3N6EZ0AUpRaAPQ/ZXk="))
            && !var2.equalsIgnoreCase(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("9I2HTfibM7fRiYTXDaJD9ESMTRCDpFa2wfzIbnPmpGk="))
            && !var2.equalsIgnoreCase(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+VTmM6EySGVn7Z6Wl+Ifkl2ZUFpPMpi6CAxefGKq6nDVMPMnkIHEhA=="))) {
            double[] var5 = this.parsePivotPairFromArgs(var1, Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1898841206, 8));
            if (var5 == null) {
               return null;
            } else {
               boolean var4 = this.shouldTreatPivotInputAsNormalized(
                  var1,
                  Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-731139589, 27),
                  var5[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1761428680, 15)],
                  var5[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(598968688, 27)]
               );
               return new EditorImageDragPart.ParsedPivotInput(
                  var5[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 15)], var5[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 24)], var4
               );
            }
         } else {
            double[] var3 = this.parsePivotPairFromArgs(var1, Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1779638587, 29));
            return var3 == null
               ? null
               : new EditorImageDragPart.ParsedPivotInput(
                  var3[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 24)],
                  var3[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-595873285, 20)],
                  (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 18) != 0)
               );
         }
      } else {
         return null;
      }
   }

   public double[] parsePivotPairFromArgs(String[] var1, int var2) {
      if ((1748669135 * (1748669135 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 < var1.length) {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-2048729767, 10)];
         var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1761428680, 15)] = var1[var2];
         String var3 = this.firstNonBlank(var10001).trim();
         if (var3.isBlank()) {
            return null;
         } else {
            if (var1.length > var2 + Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 24)) {
               var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(37435543, 23)];
               var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 3)] = var1[var2 + Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-836386674, 5)];
               String var4 = this.firstNonBlank(var10001).trim();
               if (!var4.isBlank()) {
                  double var5 = this.parsePivotInputNumber(var3);
                  double var7 = this.parsePivotInputNumber(var4);
                  if (Double.isFinite(var5) && Double.isFinite(var7)) {
                     double[] var16 = new double[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-514226718, 26)];
                     var16[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 5)] = var5;
                     var16[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-601116165, 22)] = var7;
                     return var16;
                  }
               }
            }

            String var10 = var3.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("70W66AzJvZds4bb+hfwv+iZUp4huZi5EHoPLPgMQ"),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==")
               )
               .replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("oSkn1VNnlFsX9XLEm1qkZz+6fHlXTOFTlh/4BvHW"),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==")
               )
               .replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("jqAUhsnAIXmmFX2fwotzXJePsqPvaQFCfAP+KDlf"),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8iW2UH7kttkRaHbAG/0TR2SSbkR2JAhj7lskLn0z")
               )
               .replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("IUVhyt/g2jI7XhlB9RGjklxzRVa6/2FU7N+SgcWO"),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8iW2UH7kttkRaHbAG/0TR2SSbkR2JAhj7lskLn0z")
               );
            if (var10.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8iW2UH7kttkRaHbAG/0TR2SSbkR2JAhj7lskLn0z"))) {
               String[] var11 = var10.split(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8iW2UH7kttkRaHbAG/0TR2SSbkR2JAhj7lskLn0z"), Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1246961978, 22)
               );
               if (var11.length == Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1251156283, 23)) {
                  double var6 = this.parsePivotInputNumber(var11[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 5)]);
                  double var8 = this.parsePivotInputNumber(var11[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(74871086, 24)]);
                  if (Double.isFinite(var6) && Double.isFinite(var8)) {
                     double[] var15 = new double[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1202298504, 24)];
                     var15[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 31)] = var6;
                     var15[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-581335582, 26)] = var8;
                     return var15;
                  }
               }
            }

            String[] var12 = var3.split(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("BrZSJmwioToCK9aLROqqkT3Fkzm/4mQyjOuO1+HZt6Pnag=="));
            if (var12.length == Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-597446149, 18)) {
               double var13 = this.parsePivotInputNumber(var12[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1291074126, 3)]);
               double var14 = this.parsePivotInputNumber(var12[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 14)]);
               if (Double.isFinite(var13) && Double.isFinite(var14)) {
                  double[] var10000 = new double[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(633401038, 13)];
                  var10000[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 30)] = var13;
                  var10000[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596930053, 13)] = var14;
                  return var10000;
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   public boolean shouldTreatPivotInputAsNormalized(String[] var1, int var2, double var3, double var5) {
      if ((-853347966 * -853347966 - -853347966 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         if (var1 != null && var1.length > var2) {
            String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(768972894, 22)];
            var10001[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 29)] = var1[var2];
            if (this.firstNonBlank(var10001).contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Oo50V0fqnAigEGpJ5/eqIE76K8ujthWH1fGFVvW6"))) {
               return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 20) != 0);
            }
         }

         if (var1 != null && var1.length > var2 + Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 29)) {
            String[] var7 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-599019013, 21)];
            var7[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 21)] = var1[var2 + Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 5)];
            if (this.firstNonBlank(var7).contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Oo50V0fqnAigEGpJ5/eqIE76K8ujthWH1fGFVvW6"))) {
               return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 20) != 0);
            }
         }

         if (Double.isFinite(var3) && Double.isFinite(var5)) {
            return ((var3 >= Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(8515554752599154689L, 15))
                  && var3 <= Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(8588452622136163557L, 0))
                  && var5 >= Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773370651L, 61))
                  && var5 <= Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-2831269137022451739L, 17))
               ? Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921925, 6)
               : Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1785176531, 30)) != 0);
         } else {
            return (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-489072251, 18) != 0);
         }
      }
   }

   public double parsePivotInputNumber(String var1) {
      if ((233736834 * 233736834 - 233736834 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921862, 0)];
         var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 30)] = var1;
         String var2 = this.firstNonBlank(var10001).trim();
         if (var2.isEmpty()) {
            return Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5395224220849226555L, 6));
         } else {
            boolean var3 = var2.endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Oo50V0fqnAigEGpJ5/eqIE76K8ujthWH1fGFVvW6"));
            if (var3) {
               var2 = var2.substring(Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 0), var2.length() - Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(496782209, 30))
                  .trim();
            }

            double var4 = this.parseDouble(var2, Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-4674507542981591835L, 60)));
            if (!Double.isFinite(var4)) {
               return Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-2831269137022484491L, 17));
            } else {
               return var3 ? var4 / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(4020837233513201373L, 47)) : var4;
            }
         }
      }
   }

   public String formatPivotRatio(double var1) {
      if ((-686499573 * -686499573 - -686499573 & 1) != 0) {
         throw new IllegalStateException();
      } else if (!Double.isFinite(var1)) {
         return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("UMA8ELVJQ8Gshqk9iacCZ/qRZeHV7q5E0x09tK77cGQYbmIrFSI=");
      } else {
         Locale var10000 = Locale.ROOT;
         String var10001 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("BCPZwyg0E/14LvslLI9pSdsjTzDITeZywL9iNiaVKFHjK6H+");
         Object[] var10002 = new Object[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-836386674, 5)];
         var10002[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-869329206, 5)] = var1;
         return String.format(var10000, var10001, var10002);
      }
   }

   public void renderPivotDebugMarker(Player var1, EditorSession var2, double var3, double var5) {
      if ((-653480701 * -653480701 + -653480701 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && Double.isFinite(var3) && Double.isFinite(var5)) {
         double var7 = this.toRenderSize(var2, Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(5413414884839963890L, 5)));
         double var9 = this.toRenderX(var2, var3) - var7 / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(105321479790268352L, 7));
         double var11 = this.toRenderY(var2, var5) - var7 / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(8664644233194721208L, 49));
         double var13 = this.toRuntimeLayer(var2, Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(3133354013494028918L, 17)));
         Placement var15 = this.positionCalculator.calculateBoxPlacement(var9, var11, var13, var7, var7);
         this.upsertHud(
            var1,
            Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
               "8Yhd/mjRUkPmm2Vj1DbDv1ECs7yrU96nmIoZTMwAKvu0JxILkMDzGr1dhcs8VeGbUTDAEBpEYKrKE2a9G1DQKME4ujDeYnMl+GkAsLp+GlQtcW66lmHp6d+k"
            ),
            var15.location(),
            var15.scale(),
            Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("zN82fwfSq29XL3Ppw/zKjZB8q2ZxRgC2erB9GP6nBJdTXndh6x3q6wKQ3YghMjpVgi3PHvS+m3YTXhhOS7B87msOwmQ="),
            Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596882949, 11),
            TextAlignment.CENTER
         );
         int var16 = var2.pivotDebugMarkerToken = var2.pivotDebugMarkerToken + Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1422869413, 31);
         Xqedii_38o2zes.Xqedii_2sj63w1(
            this.plugin,
            var1,
            () -> {
               if ((1469279295 * 1469279295 + 1469279295 & 1) != 0) {
                  throw new IllegalStateException();
               } else if (var1.isOnline()) {
                  EditorSession var4 = (EditorSession)this.editorSessions.get(var1.getUniqueId());
                  if (var4 != null && var4 == var2 && var4.pivotDebugMarkerToken == var16) {
                     this.removeHudById(
                        var1,
                        Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                           "8Yhd/mjRUkPmm2Vj1DbDv1ECs7yrU96nmIoZTMwAKvu0JxILkMDzGr1dhcs8VeGbUTDAEBpEYKrKE2a9G1DQKME4ujDeYnMl+GkAsLp+GlQtcW66lmHp6d+k"
                        )
                     );
                  }
               }
            },
            Xqedii_p92kfr8.Xqedii_274j413le3omf(5251109032713601818L, 20)
         );
      }
   }

   public void executeRuntimeVariableAction(Player var1, Map<String, Object> var2, String var3) {
      if ((-1408270197 * -1408270197 - -1408270197 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         EditorSession var4 = (EditorSession)this.editorSessions.get(var1.getUniqueId());
         if (var4 != null) {
            String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921869, 2)];
            var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 20)] = this.stringValue(
               var2.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("mTubPwsLsWOwjTOSThOBZ3bENgUQOO0EB/Zd/oSuavTipuSIA7xyspxtSQo="))
            );
            var10001[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 26)] = this.stringValue(
               var2.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Eaqx4dmxZEVb7SW51kr176NkLI4AG9nq1i0WyIXUMoayER3i"))
            );
            String var5 = this.firstNonBlank(var10001).trim();
            String var6 = this.Xqedii_15vycvb(var5);
            if (!var6.isBlank()) {
               int var7 = !var6.equals(var5) ? Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 23) : Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1544219874, 17);
               Map var8 = (Map)this.persistentVariables.get(var1.getUniqueId());
               Map var9;
               if (var7 != 0) {
                  var9 = this.persistentVariables.computeIfAbsent(var1.getUniqueId(), var0 -> {
                     if ((1764655475 * 1764655475 + 1764655475 & 1) != 0) {
                        throw new IllegalStateException();
                     } else {
                        return new LinkedHashMap();
                     }
                  });
               } else if (var8 != null && var8.containsKey(var6)) {
                  var9 = var8;
               } else {
                  var9 = var4.runtimeVariables;
               }

               var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(773167198, 22)];
               var10001[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 3)] = this.stringValue(
                  var2.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("FNnJ2keZ6OxTHXbIVjAqMwAv+qRpW9YLF4Xa1rtZ2D15hnFpLqgNSNFPbBjPjg=="))
               );
               var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1449228471, 0)] = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                  "D172SiazfC7Pg9o+DVpqUtoLbnOFxItN15LKwyRSQoY9BQ=="
               );
               String var10 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
               String var11 = (String)var9.getOrDefault(var6, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("/xo3NVQk1Pf9pLnfu5Af3iPYi6Wn+zASQ2F0w3AE"));
               String var12;
               if (Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("D172SiazfC7Pg9o+DVpqUtoLbnOFxItN15LKwyRSQoY9BQ==").equals(var10)) {
                  var12 = var3;
               } else {
                  Double var13 = this.Xqedii_3vdzquo(var11);
                  Double var14 = this.Xqedii_3vdzquo(var3);
                  if (var13 == null || var14 == null) {
                     return;
                  }

                  int var18 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-904715974, 31);
                  switch (var10.hashCode()) {
                     case -2060248300:
                        if (var10.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Xe/9tJ6HDOfKME6IsrbdhsYG2N6mIXmS8ll0FqxH0483K8aobkAMZ+ipQQU="))) {
                           var18 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1396510350, 1);
                        }
                        break;
                     case -1339651217:
                        if (var10.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("D+Las2AZCLmwk047oi2zm2dM/JRSLmnmlgsI+aPe0jfjtZUjXMDWwB7Gy7gvvw=="))) {
                           var18 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(316688231, 12);
                        }
                        break;
                     case -1331463047:
                        if (var10.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("9KKll3Pl5a5fi4sd+LDeyXNkwUiRen//2AkIRxmCDgaEBfNxa7yUoQ=="))) {
                           var18 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-865357317, 25);
                        }
                        break;
                     case -934610812:
                        if (var10.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ZgDsIaR2t6W1cXY4+Fjd91Lz78maTUrIA9+sz3gKCMF1LzVcMlJt1g=="))) {
                           var18 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-994572839, 10);
                        }
                        break;
                     case 42:
                        if (var10.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("VmVdQuUaDeuLl2zvVbdyquMClbytLaYDA8+vU+dc"))) {
                           var18 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242898747, 16);
                        }
                        break;
                     case 43:
                        if (var10.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("/PCTLl3rkv1R+49kqetxxzN4Kbb+tlGpM7aJDw1z"))) {
                           var18 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596954629, 14);
                        }
                        break;
                     case 45:
                        if (var10.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("qTnCsp97SB1lqTKq8neaTwQ+nuIet0cerRdYnd07"))) {
                           var18 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596962821, 13);
                        }
                        break;
                     case 47:
                        if (var10.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+oNInG5v7VThFHYMImjbXkdLssa06EvDlOZW9gOD"))) {
                           var18 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242702139, 13);
                        }
                        break;
                     case 96417:
                        if (var10.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("yc6/lVTyatuUflcTMCzRokJqrNwkuOh3wHkVm2k1UboOUw=="))) {
                           var18 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1449228470, 0);
                        }
                        break;
                     case 653829668:
                        if (var10.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("dUuiXI/OUGfqRWLeW9IcSck9K4+rCAOhfxutb17enMUpXmbe/cxh++2R9NU="))) {
                           var18 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-597184005, 17);
                        }
                  }
                  double var15 = switch (var18) {
                     case 0, 1, 2 -> var13 + var14;
                     case 3, 4, 5 -> var13 - var14;
                     case 6, 7 -> var13 * var14;
                     case 8, 9 -> var14 == Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-5381880203083512360L, 25)) ? var13 : var13 / var14;
                     default -> Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-91454448180721681L, 6));
                  };
                  if (Double.isNaN(var15) || Double.isInfinite(var15)) {
                     return;
                  }

                  var12 = this.Xqedii_1n5mt70(var15);
               }

               this.debugScript(
                  Xqedii_2laq9582k8nvi(
                     var6,
                     var10,
                     var3,
                     var11,
                     var12,
                     var9 == var4.runtimeVariables
                        ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==")
                        : Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("SsQt0ZofwhIu6xj5dN+/hsNFb8p5+jW38hv4ywncAVwW+7EdLnkBUgEENX+GT2u4oE6oX9QR")
                  )
               );
               if (!var12.equals(var9.get(var6))) {
                  var9.put(var6, var12);
                  this.renderPage(var1, var4, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-320709622, 1) != 0));
               }
            }
         }
      }
   }

   public void executeRuntimeGiveAction(Player var1, Map<String, Object> var2, String var3) {
      if ((447679699 * (447679699 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(388680751, 21)];
         var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 5)] = this.stringValue(
            var2.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("y5QRdbCHuGF5M48mJt0ps9JIfxRstLBD14oCOwQFMcjHQIsE"))
         );
         var10001[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 15)] = this.stringValue(
            var2.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("QVGLM9j0BxN33TpX7q3EVV+viPT2kVmvxHrM3RvLPVqApFZaqjyxOr6adgc="))
         );
         var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-320709618, 1)] = var3;
         String var4 = this.firstNonBlank(var10001).trim().toUpperCase(Locale.ROOT);
         if (!var4.isBlank()) {
            Material var5 = Material.matchMaterial(var4);
            if (var5 != null && !var5.isAir()) {
               Double var6 = this.Xqedii_3vdzquo(
                  this.resolveRuntimeActionValue(
                     var1, this.stringValue(var2.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Caa/omn33Db6Egm3R86t3Jm/alt80z/OxjZMaQ+a3TRAj0c72aD6RQ==")))
                  )
               );
               int var7 = var6 == null ? Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-869329174, 5) : (int)Math.round(var6);
               if (var7 > 0) {
                  PlayerInventory var10000 = var1.getInventory();
                  ItemStack[] var8 = new ItemStack[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(772044401, 16)];
                  var8[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 1)] = new ItemStack(
                     var5, Math.min(var7, var5.getMaxStackSize() * Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1259020603, 19))
                  );
                  var10000.addItem(var8);
               }
            }
         }
      }
   }

   public boolean evaluateRuntimeCondition(String var1) {
      if ((1754225798 * 1754225798 + 1754225798 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 13)];
         var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-2124571573, 22)] = var1;
         String var2 = this.firstNonBlank(var10001).trim();
         if (var2.isBlank()) {
            return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(633384654, 13) != 0);
         } else {
            String[] var10000 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(904715972, 30)];
            var10000[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 14)] = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("us+gqvS/QY8pEAY61Wf/1wT61QJXNWFfOVSWTj1nPm0=");
            var10000[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(395015526, 12)] = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("A8Zg+YG83hmgtCPAQQCZSIKjbDnAihOrMoE5cpYtJlg=");
            var10000[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(181153559, 29)] = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("SyurrNqvBHQG7iq5CuR0/AYjbUhv/5nq2xk/6Rfc4tw=");
            var10000[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596922053, 6)] = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("yQmQzKWrlAjWq0ZQ5cjcXWLcgb8PK2llZpLPictMHzw=");
            var10000[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596930053, 11)] = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("/qGDW6ySb6r9ELY3tGPsINPQmQ02pQ74p50Xasbe");
            var10000[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(904715973, 29)] = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ZCT+fI3fnfQbicxRMaHCsBW9bYtuQo2rPUl1F4c5");
            var10000[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(362307119, 30)] = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("I/Wcue7xUSQzHSlYmI8sAdH5zrCr9X40giQ45llv");
            String[] var3 = var10000;
            int var4 = var3.length;

            for (int var5 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1701189515, 28); var5 < var4; var5++) {
               String var6 = var3[var5];
               int var7 = var2.indexOf(var6);
               if (var7 > 0) {
                  String var8 = var2.substring(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 9), var7).trim();
                  String var9 = var2.substring(var7 + var6.length()).trim();
                  Double var10 = this.Xqedii_3vdzquo(var8);
                  Double var11 = this.Xqedii_3vdzquo(var9);
                  if (var10 != null && var11 != null) {
                     int var13 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(596921860, 22);
                     switch (var6.hashCode()) {
                        case 60:
                           if (var6.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("I/Wcue7xUSQzHSlYmI8sAdH5zrCr9X40giQ45llv"))) {
                              var13 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1134679248, 15);
                           }
                           break;
                        case 61:
                           if (var6.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("/qGDW6ySb6r9ELY3tGPsINPQmQ02pQ74p50Xasbe"))) {
                              var13 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242769211, 9);
                           }
                           break;
                        case 62:
                           if (var6.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ZCT+fI3fnfQbicxRMaHCsBW9bYtuQo2rPUl1F4c5"))) {
                              var13 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596962821, 13);
                           }
                           break;
                        case 1084:
                           if (var6.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("SyurrNqvBHQG7iq5CuR0/AYjbUhv/5nq2xk/6Rfc4tw="))) {
                              var13 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242636603, 17);
                           }
                           break;
                        case 1921:
                           if (var6.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("A8Zg+YG83hmgtCPAQQCZSIKjbDnAihOrMoE5cpYtJlg="))) {
                              var13 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 21);
                           }
                           break;
                        case 1952:
                           if (var6.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("yQmQzKWrlAjWq0ZQ5cjcXWLcgb8PK2llZpLPictMHzw="))) {
                              var13 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(633392846, 13);
                           }
                           break;
                        case 1983:
                           if (var6.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("us+gqvS/QY8pEAY61Wf/1wT61QJXNWFfOVSWTj1nPm0="))) {
                              var13 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 29);
                           }
                     }
                     return ((switch (var13) {
                        case 0 -> var10 >= var11 ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1672773347, 6) : Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 26);
                        case 1 -> var10 <= var11 ? Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1396510354, 1) : Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 5);
                        case 2 -> var10 != var11 ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1672773347, 6) : Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(949420731, 7);
                        case 3, 4 -> var10 == var11 ? Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 27) : Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 18);
                        case 5 -> var10 > var11 ? Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596922117, 8) : Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 14);
                        case 6 -> var10 < var11 ? Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 16) : Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 13);
                        default -> Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(724614235, 31);
                     }) != 0);
                  }

                  boolean var12 = var8.equalsIgnoreCase(var9);
                  int var14 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-772109938, 16);
                  switch (var6.hashCode()) {
                     case 61:
                        if (var6.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("/qGDW6ySb6r9ELY3tGPsINPQmQ02pQ74p50Xasbe"))) {
                           var14 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242769723, 11);
                        }
                        break;
                     case 1084:
                        if (var6.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("SyurrNqvBHQG7iq5CuR0/AYjbUhv/5nq2xk/6Rfc4tw="))) {
                           var14 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 22);
                        }
                        break;
                     case 1952:
                        if (var6.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("yQmQzKWrlAjWq0ZQ5cjcXWLcgb8PK2llZpLPictMHzw="))) {
                           var14 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-580144645, 24);
                        }
                  }
                  return switch (var14) {
                     case 0 -> !var12;
                     case 1, 2 -> var12;
                     default -> false;
                  };
               }
            }

            if (var2.equalsIgnoreCase(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("imh4BoiQJehKnQJgfF18w5Wmf5jDvSfairWwI+VFOjIV8O2q"))) {
               return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 6) != 0);
            } else if (var2.equalsIgnoreCase(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("VEfGHX10AYNWmPUStFp1jJFCpoY3l4MaFP5Zvd6lBL3rLUJiZTw="))) {
               return (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-320709622, 1) != 0);
            } else {
               Double var15 = this.Xqedii_3vdzquo(var2);
               return ((var15 == null
                  ? (!var2.isBlank() ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-641419247, 2) : Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(91648302, 24))
                  : (
                     var15 != Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773370651L, 40))
                        ? Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 1)
                        : Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 15)
                  )) != 0);
            }
         }
      }
   }

   public Double Xqedii_3vdzquo(String var1) {
      if ((564137803 * (564137803 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1396510354, 1)];
         var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-978144501, 19)] = var1;
         String var2 = this.firstNonBlank(var10001).trim();
         if (var2.isBlank()) {
            return null;
         } else {
            try {
               return Double.valueOf(var2);
            } catch (NumberFormatException var4) {
               return null;
            }
         }
      }
   }

   public String Xqedii_1n5mt70(double var1) {
      if ((-1496288222 * (-1496288222 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return var1 == Math.rint(var1) && !Double.isInfinite(var1) ? String.valueOf((long)var1) : String.valueOf(var1);
      }
   }

   public Map<String, String> parsePageVariables(YamlConfiguration var1) {
      if (((-1193743559 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_2orffyj(var1, (Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-978144501, 19) != 0));
      }
   }

   public Map<String, String> parsePagePersistentVariables(YamlConfiguration var1) {
      if ((341194001 ^ 341194001) != 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_2orffyj(var1, (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 6) != 0));
      }
   }

   public Map<String, String> Xqedii_2orffyj(YamlConfiguration var1, boolean var2) {
      if ((480538305 ^ 480538305) != 0) {
         throw new IllegalStateException();
      } else {
         LinkedHashMap var3 = new LinkedHashMap();
         if (var1 == null) {
            return var3;
         } else {
            ConfigurationSection var4 = var1.getConfigurationSection(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("MRDqe1KPktnoPIWMahrXi1pfGkHpOlN7OZ7xmpx9L4MdB/Y+GqLqW6sUxaKStA==")
            );
            if (var4 == null) {
               return var3;
            } else {
               for (String var6 : var4.getKeys((Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 12) != 0))) {
                  if (var6 != null && !var6.isBlank()) {
                     String var7 = this.Xqedii_15vycvb(var6);
                     boolean var8 = !var7.equals(var6);
                     if (var8 == var2 && !var7.isBlank()) {
                        String[] var10003 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767667, 3)];
                        var10003[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(316692327, 12)] = this.stringValue(var4.get(var6));
                        var10003[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(949420603, 7)] = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma(
                           "/xo3NVQk1Pf9pLnfu5Af3iPYi6Wn+zASQ2F0w3AE"
                        );
                        var3.put(var7, this.firstNonBlank(var10003));
                     }
                  }
               }

               return var3;
            }
         }
      }
   }

   public String Xqedii_15vycvb(String var1) {
      if (((259508272 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String var2 = var1 == null ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==") : var1.trim();
         return !var2.startsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("VmVdQuUaDeuLl2zvVbdyquMClbytLaYDA8+vU+dc"))
               && !var2.startsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("pY7N4+FgrL5iqwlGE41iq6+nfqeKl3pfPMzTUkHN"))
            ? var2
            : var2.substring(Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1898841206, 8)).trim();
      }
   }

   public void seedPersistentVariables(Player var1, Map<String, String> var2) {
      if (((1209743305 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var2.isEmpty()) {
         Map var3 = this.persistentVariables.computeIfAbsent(var1.getUniqueId(), var0 -> {
            if ((1173873031 * 1173873031 - 1173873031 & 1) != 0) {
               throw new IllegalStateException();
            } else {
               return new LinkedHashMap();
            }
         });

         for (Entry var5 : var2.entrySet()) {
            var3.putIfAbsent((String)var5.getKey(), (String)var5.getValue());
         }
      }
   }

   public void clearPersistentVariables(Player var1) {
      if ((-994843842 * -994843842 + -994843842 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         if (var1 != null) {
            this.persistentVariables.remove(var1.getUniqueId());
         }
      }
   }

   public Map<String, Map<String, Object>> parsePageFunctions(YamlConfiguration var1) {
      if ((-1930938131 * (-1930938131 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         LinkedHashMap var2 = new LinkedHashMap();
         if (var1 == null) {
            return var2;
         } else {
            ConfigurationSection var3 = var1.getConfigurationSection(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("F/uS9e1sT5wcLxdUuqm1RsyOttZjePywKafaMf5fnYdkYO9n5zinx2WgzCO4hw==")
            );
            if (var3 == null) {
               return var2;
            } else {
               for (String var5 : var3.getKeys((Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(790022860, 13) != 0))) {
                  if (var5 != null && !var5.isBlank()) {
                     LinkedHashMap var6 = new LinkedHashMap();
                     ConfigurationSection var7 = var3.getConfigurationSection(var5);
                     if (var7 != null) {
                        var6.put(
                           Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("bJOeuZboreAkb2vED2EcJaFEOvsBp43s0DzWceduR53MTaGD3AssVA=="),
                           var7.getConfigurationSection(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("bJOeuZboreAkb2vED2EcJaFEOvsBp43s0DzWceduR53MTaGD3AssVA=="))
                                 != null
                              ? var7.getConfigurationSection(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("bJOeuZboreAkb2vED2EcJaFEOvsBp43s0DzWceduR53MTaGD3AssVA=="))
                                 .getValues((Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 31) != 0))
                              : var7.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("bJOeuZboreAkb2vED2EcJaFEOvsBp43s0DzWceduR53MTaGD3AssVA=="))
                        );
                        var6.put(
                           Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("iNLFbXeQvADRz3/ps/grDepckaZcD6S0jpY+V5yDh/9mrwjLYrprvOvm"),
                           var7.getList(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("iNLFbXeQvADRz3/ps/grDepckaZcD6S0jpY+V5yDh/9mrwjLYrprvOvm"))
                        );
                     } else if (var3.get(var5) instanceof Map var8) {
                        var6.put(
                           Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("bJOeuZboreAkb2vED2EcJaFEOvsBp43s0DzWceduR53MTaGD3AssVA=="),
                           var8.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("bJOeuZboreAkb2vED2EcJaFEOvsBp43s0DzWceduR53MTaGD3AssVA=="))
                        );
                        var6.put(
                           Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("iNLFbXeQvADRz3/ps/grDepckaZcD6S0jpY+V5yDh/9mrwjLYrprvOvm"),
                           var8.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("iNLFbXeQvADRz3/ps/grDepckaZcD6S0jpY+V5yDh/9mrwjLYrprvOvm"))
                        );
                     }

                     if (var6.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("iNLFbXeQvADRz3/ps/grDepckaZcD6S0jpY+V5yDh/9mrwjLYrprvOvm")) instanceof List var10
                        && !var10.isEmpty()) {
                        var2.put(var5, var6);
                     }
                  }
               }

               return var2;
            }
         }
      }
   }

   public void executeRuntimeVisibilityAction(Player var1, Map<String, Object> var2, String var3) {
      if (((184586169 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         EditorSession var4 = (EditorSession)this.editorSessions.get(var1.getUniqueId());
         if (var4 != null) {
            Object[] var10002 = new Object[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1712819125, 4)];
            var10002[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(197505715, 11)] = var2.get(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("CwzWupQ94/MMwUgQ3w82s4DEITtMvNSwQwH4HvX3FYywwBsZUmKspu1l")
            );
            var10002[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1635301206, 8)] = var2.get(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("tSu3NHpLTETb5npTM4/Qb896iNATC/APWhibcRcYI1FZ9OeOY13oqepOlI8=")
            );
            var10002[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(500810936, 26)] = var2.get(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("BVnt8db+/qlEnbf66a02pwDwbdlMBbXhAz9RnJ3S7V0H2Er2dOl4Ug==")
            );
            var10002[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1501946597, 2)] = var2.get(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("7gcWBa4mOfGVxSjx5SbUMRj2G1peugqbjEg1aGyxyD+kSmyVvffdc3lR")
            );
            var10002[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-994572839, 10)] = var2.get(
               Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Xrrvs81HHFD0cotBejhfhuT0G1swAMAermn9c5HkW6Q=")
            );
            List var5 = this.Xqedii_2ai5745(this.Xqedii_3rriqa2(var10002));
            if (!var5.isEmpty()) {
               Boolean var6 = this.Xqedii_e5t4ty6(var3);
               if (var6 != null) {
                  boolean var7 = false;

                  for (String var9 : (Iterable<String>) (var5)) {

                     for (String var11 : this.Xqedii_1aafjs3(var4, var9)) {
                        LinkedHashSet var12 = this.resolveVisibilityCascadeTargetIds(var4, var11);
                        var7 |= var6 ? var4.hiddenLayerTargetIds.removeAll(var12) : var4.hiddenLayerTargetIds.addAll(var12);
                     }
                  }

                  if (var7) {
                     this.renderPage(var1, var4, (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 25) != 0));
                  }
               }
            }
         }
      }
   }

   public Object Xqedii_3rriqa2(Object... var1) {
      if ((274787577 ^ 274787577) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return null;
      } else {
         Object[] var2 = var1;
         int var3 = var1.length;

         for (int var4 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1206527548, 18); var4 < var3; var4++) {
            Object var5 = var2[var4];
            if (var5 != null) {
               return var5;
            }
         }

         return null;
      }
   }

   public Boolean Xqedii_e5t4ty6(String var1) {
      if (((-184354005 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596922117, 8)];
         var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(45824151, 23)] = var1;
         String var2 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
         int var4 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767642, 5);
         switch (var2.hashCode()) {
            case -1901805651:
               if (var2.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("U9MTRfbKTR7QHS9ukmPz/Omv/3PQXGEprZZazohC3bHBxZtMbZQvCjkGDeKV5g=="))) {
                  var4 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-576959613, 30);
               }
               break;
            case -1217487446:
               if (var2.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ZuENpfqpg7ZXBBHVffna5KhQnqETV13oIYraF9NxWW0lp0dS3dEZTg=="))) {
                  var4 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242769723, 8);
               }
               break;
            case 48:
               if (var2.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("/xo3NVQk1Pf9pLnfu5Af3iPYi6Wn+zASQ2F0w3AE"))) {
                  var4 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-485664379, 18);
               }
               break;
            case 49:
               if (var2.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("j+nosUT3jp3b5i6+9SNZRssR8QGszNOk7F4jQbM5"))) {
                  var4 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-974998773, 19);
               }
               break;
            case 3521:
               if (var2.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("1u4wyhs3nHIWy7BBZUHAL9y/MjfaZFpZnpPsNG1JG8g="))) {
                  var4 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921897, 2);
               }
               break;
            case 3551:
               if (var2.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ZngR1YkDQ/Pd+F4wUQFXcUcR3ab8bVNeY4EwX8A/t1A="))) {
                  var4 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1422869411, 31);
               }
               break;
            case 109935:
               if (var2.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("wrRCf8lbf+KuTRryEEE8/8Qqdfb/PTsDYmUx0XawXDdI0A=="))) {
                  var4 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-2048718503, 10);
               }
               break;
            case 119527:
               if (var2.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("avWjTpsmWtmgzQ9bopKhy8nwFW4CwY1F2JU5ZV8OYkxhRQ=="))) {
                  var4 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1879815049, 19);
               }
               break;
            case 3202370:
               if (var2.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("X9INqPqvvitep8hvAm2jw6Vt5gB0E84to7Fjviu1mMTalOpU"))) {
                  var4 = Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1987128845, 0);
               }
               break;
            case 3529469:
               if (var2.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Gzrb9OMRjHQ6FXmEHQ4ML0ucuJYZJItjplT3xeo/FxJQcPSt"))) {
                  var4 = Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921925, 6);
               }
               break;
            case 3569038:
               if (var2.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("imh4BoiQJehKnQJgfF18w5Wmf5jDvSfairWwI+VFOjIV8O2q"))) {
                  var4 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1529557180, 23);
               }
               break;
            case 97196323:
               if (var2.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("VEfGHX10AYNWmPUStFp1jJFCpoY3l4MaFP5Zvd6lBL3rLUJiZTw="))) {
                  var4 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1436048943, 30);
               }
               break;
            case 109413649:
               if (var2.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("a5tk8zDgmPU/wm1JUXphnbrZQ0yWTCz0ufaNTkYWazcqO9e3jFs="))) {
                  var4 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-869329142, 5);
               }
               break;
            case 466743410:
               if (var2.equals(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("w40deIU0QsXj4GSjzJdDObjINo90LBn/BudzbKBL4qaYibH6Iq0fNLo0"))) {
                  var4 = Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767676, 31);
               }
         }
         return switch (var4) {
            case 0, 1, 2, 3, 4, 5, 6 -> Boolean.TRUE;
            case 7, 8, 9, 10, 11, 12, 13 -> Boolean.FALSE;
            default -> null;
         };
      }
   }

   public void executeRuntimeToggleAction(Player var1, Map<String, Object> var2) {
      if ((239131569 ^ 239131569) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         EditorSession var3 = (EditorSession)this.editorSessions.get(var1.getUniqueId());
         if (var3 != null) {
            List var4 = this.Xqedii_2ai5745(var2.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("X9INqPqvvitep8hvAm2jw6Vt5gB0E84to7Fjviu1mMTalOpU")));
            List var5 = this.Xqedii_2ai5745(var2.get(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Gzrb9OMRjHQ6FXmEHQ4ML0ucuJYZJItjplT3xeo/FxJQcPSt")));
            if (!var4.isEmpty() || !var5.isEmpty()) {
               boolean var6 = false;

               for (String var8 : (Iterable<String>) (var4)) {

                  for (String var10 : this.Xqedii_1aafjs3(var3, var8)) {
                     var6 |= var3.hiddenLayerTargetIds.addAll(this.resolveVisibilityCascadeTargetIds(var3, var10));
                  }
               }

               for (String var12 : (Iterable<String>) (var5)) {

                  for (String var14 : this.Xqedii_1aafjs3(var3, var12)) {
                     var6 |= var3.hiddenLayerTargetIds.removeAll(this.resolveVisibilityCascadeTargetIds(var3, var14));
                  }
               }

               if (var6) {
                  this.renderPage(var1, var3, (Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(949420731, 7) != 0));
               }
            }
         }
      }
   }

   public List<String> Xqedii_2ai5745(Object var1) {
      if ((-1599210351 ^ -1599210351) != 0) {
         throw new IllegalStateException();
      } else {
         ArrayList var2 = new ArrayList();
         if (var1 instanceof List) {
            for (Object var5 : (List)var1) {
               String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1134843088, 15)];
               var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 5)] = this.stringValue(var5);
               String var6 = this.firstNonBlank(var10001).trim();
               if (!var6.isBlank()) {
                  var2.add(var6);
               }
            }
         } else {
            String[] var8 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1987128836, 0)];
            var8[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1544219874, 17)] = this.stringValue(var1);
            String var7 = this.firstNonBlank(var8).trim();
            if (!var7.isBlank()) {
               var2.add(var7);
            }
         }

         return var2;
      }
   }

   public LinkedHashSet<String> Xqedii_1aafjs3(EditorSession var1, String var2) {
      if ((-1477870413 * -1477870413 - -1477870413 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         LinkedHashSet var3 = new LinkedHashSet();
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 13)];
         var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1449228470, 0)] = var2;
         String var4 = this.firstNonBlank(var10001).trim();
         if (var4.isBlank()) {
            return var3;
         } else {
            var3.add(var4);
            if (var1.renderBlocks != null) {
               for (Map var6 : var1.renderBlocks) {
                  ConfigurationSection var7 = this.mapToSection(var6);
                  if (var7 != null) {
                     String[] var10002 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(768972894, 22)];
                     var10002[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 1)] = var7.getString(
                        Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Xrrvs81HHFD0cotBejhfhuT0G1swAMAermn9c5HkW6Q=")
                     );
                     if (var4.equals(this.firstNonBlank(var10002))) {
                        var10002 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921877, 3)];
                        var10002[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 28)] = var7.getString(
                           Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("DtZ+Hbi9b70BFs7n2YgU+QYgQBwMp0xmYtpxSf2fVdwpg3IBZbIMfZ9DQUxedqMWqOSl1MWwy6Ng0/evzSCK3w==")
                        );
                        var10002[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1266752924, 14)] = var4;
                        var3.add(this.firstNonBlank(var10002));
                     }
                  }
               }
            }

            return var3;
         }
      }
   }

   public void executeRuntimeCommandAction(Player var1, String var2) {
      if ((1673210483 * (1673210483 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1544088802, 17)];
         var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 2)] = var2;
         String var3 = this.firstNonBlank(var10001).trim();
         if (var3.startsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+oNInG5v7VThFHYMImjbXkdLssa06EvDlOZW9gOD"))) {
            var3 = var3.substring(Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-977620213, 19)).trim();
         }

         if (!var3.isBlank()) {
            var1.performCommand(var3);
         }
      }
   }

   public void executeRuntimeConsoleAction(Player var1, String var2) {
      if ((-824478644 * (-824478644 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-244405054, 17)];
         var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 16)] = var2;
         String var3 = this.firstNonBlank(var10001).trim();
         if (var3.startsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+oNInG5v7VThFHYMImjbXkdLssa06EvDlOZW9gOD"))) {
            var3 = var3.substring(Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(2025281121, 16)).trim();
         }

         if (!var3.isBlank()) {
            try {
               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), var3);
            } catch (RuntimeException var5) {
            }
         }
      }
   }

   public void executeRuntimeMessageAction(Player var1, String var2) {
      if ((2122818564 * 2122818564 + 2122818564 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 24)];
         var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(764778590, 22)] = var2;
         String var3 = this.firstNonBlank(var10001);
         if (!var3.isBlank()) {
            Component var4 = this.resolveRuntimeMessageComponent(var3);
            if (var4 != null) {
               Xqedii_nd6rkr3.Xqedii_3s7zz34(var1, var4);
            }
         }
      }
   }

   public Component resolveRuntimeMessageComponent(String var1) {
      if ((267738626 * 267738626 + 267738626 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 19)];
         var10001[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 23)] = var1;
         String var2 = this.firstNonBlank(var10001);
         if (var2.isBlank()) {
            return Component.empty();
         } else {
            Matcher var3 = Xqedii_l2une6g.Xqedii_1obpk80.matcher(var2);
            if (!var3.find()) {
               return this.deserializeRuntimeMessageFragment(var2);
            } else {
               var3.reset();
               Component var4 = Component.empty();

               int var5;
               for (var5 = Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1635300950, 8); var3.find(); var5 = var3.end()) {
                  if (var3.start() > var5) {
                     String var6 = var2.substring(var5, var3.start());
                     var4 = var4.append(this.deserializeRuntimeMessageFragment(var6));
                  }

                  var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 8)];
                  var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1062285787, 21)] = var3.group(Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1162671163, 27));
                  String var13 = this.firstNonBlank(var10001);
                  var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(37435543, 23)];
                  var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1989137485, 11)] = var3.group(Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-488547963, 18));
                  String var7 = this.firstNonBlank(var10001).trim();
                  var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1449228471, 0)];
                  var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(183296604, 25)] = var3.group(Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1282838477, 3));
                  String var8 = this.firstNonBlank(var10001);
                  Component var9 = this.deserializeRuntimeMessageFragment(var8);
                  Component var10 = this.deserializeTooltipComponent(var13);
                  if (var10 != null) {
                     var9 = var9.hoverEvent(HoverEvent.showText(var10));
                  }

                  if (!var7.isBlank()) {
                     try {
                        var9 = var9.clickEvent(ClickEvent.openUrl(var7));
                     } catch (IllegalArgumentException var12) {
                     }
                  }

                  var4 = var4.append(var9);
               }

               if (var5 < var2.length()) {
                  var4 = var4.append(this.deserializeRuntimeMessageFragment(var2.substring(var5)));
               }

               return (Component)var4;
            }
         }
      }
   }

   public Component deserializeTooltipComponent(String var1) {
      if (((352835324 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 25)];
         var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1738658411, 6)] = var1;
         String var2 = this.firstNonBlank(var10001);
         if (var2.isBlank()) {
            return Component.empty();
         } else {
            return (Component)(var2.indexOf(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596922165, 3)) >= 0
               ? Xqedii_l2une6g.Xqedii_3joiadk.deserialize(var2)
               : this.deserializeRuntimeMessageFragment(var2));
         }
      }
   }

   public Component deserializeRuntimeMessageFragment(String var1) {
      if (((1889641170 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String var2 = var1 == null ? Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==") : var1;
         if (var2.isBlank()) {
            return Component.empty();
         } else {
            int var3 = var2.indexOf(Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1486037307, 23)) < 0
                  || var2.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("I/Wcue7xUSQzHSlYmI8sAdH5zrCr9X40giQ45llv"))
                     && var2.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ZCT+fI3fnfQbicxRMaHCsBW9bYtuQo2rPUl1F4c5"))
               ? Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 5)
               : Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1537945788, 23);
            if (var3 != 0) {
               return Xqedii_l2une6g.Xqedii_3joiadk.deserialize(var2);
            } else {
               try {
                  return Xqedii_3uxgwyg.Xqedii_2p0af0b.deserialize(var2);
               } catch (Exception var5) {
                  return Xqedii_l2une6g.Xqedii_3joiadk.deserialize(var2);
               }
            }
         }
      }
   }

   public void executeRuntimeSoundAction(Player var1, String var2) {
      if ((-1421566728 * -1421566728 + -1421566728 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         EditorImageDragPart.RuntimeSoundSpec var3 = this.resolveRuntimeSoundSpec(var2);
         if (var3 != null && !var3.soundToken().isBlank()) {
            float var4 = (float)Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(9218256545251314099L, 27)), var3.volume());
            float var5 = (float)Math.max(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5251109032773370651L, 52)), var3.pitch());
            String var6 = var3.soundToken();
            String var7 = var6.trim()
               .replace((char)Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1153919211, 31), (char)Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1024316756, 9))
               .replace((char)Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596977157, 11), (char)Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-877940229, 22))
               .replace((char)Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1570524041, 30), (char)Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(777680497, 16))
               .toUpperCase(Locale.ROOT);

            try {
               Sound var8 = Xqedii_38o2zes.Xqedii_ruc2zei(var7);
               if (var8 != null) {
                  var1.playSound(var1.getLocation(), var8, SoundCategory.MASTER, var4, var5);
                  return;
               }
            } catch (RuntimeException var12) {
               return;
            }

            String var13 = var6.trim()
               .replace((char)Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767654, 0), (char)Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767443, 3))
               .replace((char)Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1672767843, 6), (char)Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-571679036, 27))
               .toLowerCase(Locale.ROOT);

            while (var13.contains(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("/OQGjaJm3z1rgYKluJ0Iezh+luLnmgqPqQM+2OmuQ0w="))) {
               var13 = var13.replace(
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("/OQGjaJm3z1rgYKluJ0Iezh+luLnmgqPqQM+2OmuQ0w="),
                  Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("7ZFwje9KhIMcAKrjCrIDQlVCcuKLUWoPTAPCy11j")
               );
            }

            if (var13.startsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("7ZFwje9KhIMcAKrjCrIDQlVCcuKLUWoPTAPCy11j"))) {
               var13 = var13.substring(Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-532191470, 20));
            }

            if (var13.endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("7ZFwje9KhIMcAKrjCrIDQlVCcuKLUWoPTAPCy11j"))) {
               var13 = var13.substring(Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-978144501, 19), var13.length() - Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1282838493, 3));
            }

            if (!var13.isBlank()) {
               try {
                  var1.playSound(var1.getLocation(), var13, SoundCategory.MASTER, var4, var5);
               } catch (RuntimeException var11) {
                  try {
                     var1.playSound(var1.getLocation(), var6, SoundCategory.MASTER, var4, var5);
                  } catch (RuntimeException var10) {
                  }
               }
            }
         }
      }
   }

   public EditorImageDragPart.RuntimeSoundSpec resolveRuntimeSoundSpec(String var1) {
      if (((-1751621124 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921869, 3)];
         var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1266769308, 14)] = var1;
         String var2 = this.firstNonBlank(var10001).trim();
         if (var2.isBlank()) {
            return null;
         } else {
            Matcher var3 = Xqedii_l2une6g.Xqedii_3aau2gg.matcher(var2);
            if (!var3.matches()) {
               return new EditorImageDragPart.RuntimeSoundSpec(
                  var2,
                  Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(5744473540982593717L, 33)),
                  Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-40923932559520357L, 30))
               );
            } else {
               var10001 = new String[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 12)];
               var10001[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 9)] = var3.group(Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 20));
               String var4 = this.firstNonBlank(var10001).trim();
               Double var5 = this.tryParseRuntimeFlexibleDouble(var3.group(Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1729290359, 4)));
               Double var6 = this.tryParseRuntimeFlexibleDouble(var3.group(Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1436048942, 30)));
               double var7 = var5 == null ? Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893125764951027L, 39)) : var5;
               double var9 = var6 == null ? Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-7262055648034233868L, 15)) : var6;
               return new EditorImageDragPart.RuntimeSoundSpec(var4, var7, var9);
            }
         }
      }
   }

   public void executeRuntimeTeleportAction(Player var1, String var2) {
      if ((-620335530 * -620335530 - -620335530 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         EditorImageDragPart.RuntimeTeleportSpec var3 = this.parseRuntimeTeleportSpec(var2);
         if (var3 != null) {
            Location var4 = var1.getLocation();
            if (var4.getWorld() != null) {
               World var5 = var4.getWorld();
               String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921893, 5)];
               var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(733186416, 27)] = var3.worldName();
               String var6 = this.firstNonBlank(var10001).trim();
               if (!var6.isBlank()) {
                  World var7 = Bukkit.getWorld(var6);
                  if (var7 == null) {
                     return;
                  }

                  var5 = var7;
               }

               Location var11 = new Location(var5, var3.x(), var3.y(), var3.z(), var4.getYaw(), var4.getPitch());
               if (this.plugin != null) {
                  this.plugin.closeUiForPlayerAndTeleport(var1, var11);
               } else {
                  this.cameraService.forceStop(var1);
                  this.closeGui(var1);
                  this.hudService.clearHuds(var1);
                  Location var8 = var11;

                  try {
                     Xqedii_38o2zes.Xqedii_fxq2htx(var1, var8);
                  } catch (RuntimeException var10) {
                  }
               }
            }
         }
      }
   }

   public EditorImageDragPart.RuntimeTeleportSpec parseRuntimeTeleportSpec(String var1) {
      if (((-1858209233 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596954629, 15)];
         var10001[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-836386642, 5)] = var1;
         String var2 = this.firstNonBlank(var10001).trim();
         if (var2.isBlank()) {
            return null;
         } else {
            String var3 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ==");
            String var4 = var2;
            Matcher var5 = Xqedii_l2une6g.Xqedii_2dvg53x.matcher(var2);
            if (var5.find()) {
               var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-869329174, 5)];
               var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1529557180, 23)] = var5.group(Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-581335582, 26));
               var3 = this.firstNonBlank(var10001).trim();
               var4 = var5.replaceFirst(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("a9ZTyMp55DQgcx7aRKwyXh7564aimltI2QWAlqml"));
            } else {
               String[] var6 = var2.split(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("BrZSJmwioToCK9aLROqqkT3Fkzm/4mQyjOuO1+HZt6Pnag=="));
               if (var6.length >= Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1712819109, 4)) {
                  var10001 = new String[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-497284884, 9)];
                  var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1580045720, 14)] = var6[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 14)];
                  String var7 = this.firstNonBlank(var10001).trim();
                  if (!var7.isBlank() && this.tryParseRuntimeFlexibleDouble(var7) == null) {
                     var3 = var7;
                     var4 = var2.substring(var2.indexOf(var7) + var7.length()).trim();
                  }
               }
            }

            double[] var8 = this.parseRuntimeTeleportCoordinates(var4);
            return var8 != null && var8.length >= Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(433702072, 26)
               ? new EditorImageDragPart.RuntimeTeleportSpec(
                  var3,
                  var8[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(949420731, 7)],
                  var8[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-664030725, 26)],
                  var8[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921989, 6)]
               )
               : null;
         }
      }
   }

   public double[] parseRuntimeTeleportCoordinates(String var1) {
      if ((1472084262 * 1472084262 - 1472084262 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(817650603, 7)];
         var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 21)] = var1;
         String var2 = this.firstNonBlank(var10001).trim();
         if (var2.isBlank()) {
            return null;
         } else {
            Matcher var3 = Xqedii_l2une6g.Xqedii_2nzbwpf.matcher(var2);
            Matcher var4 = Xqedii_l2une6g.Xqedii_276g5ep.matcher(var2);
            Matcher var5 = Xqedii_l2une6g.Xqedii_2bycz7l.matcher(var2);
            if (var3.find() && var4.find() && var5.find()) {
               Double var6 = this.tryParseRuntimeFlexibleDouble(var3.group(Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1537945788, 23)));
               Double var7 = this.tryParseRuntimeFlexibleDouble(var4.group(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-595873285, 20)));
               Double var8 = this.tryParseRuntimeFlexibleDouble(var5.group(Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 27)));
               if (var6 != null && var7 != null && var8 != null) {
                  double[] var12 = new double[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-571756037, 23)];
                  var12[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1672773283, 6)] = var6;
                  var12[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1197937376, 28)] = var7;
                  var12[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1309876539, 26)] = var8;
                  return var12;
               }
            }

            ArrayList var9 = new ArrayList(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596790789, 17));
            Matcher var10 = Xqedii_l2une6g.Xqedii_1pita34.matcher(var2);

            while (var10.find()) {
               Double var11 = this.tryParseRuntimeFlexibleDouble(var10.group());
               if (var11 != null) {
                  var9.add(var11);
                  if (var9.size() >= Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242771771, 11)) {
                     break;
                  }
               }
            }

            if (var9.size() < Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767739, 5)) {
               return null;
            } else {
               double[] var10000 = new double[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921909, 4)];
               var10000[Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 29)] = (Double)var9.get(Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(1881912201, 19));
               var10000[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-599019013, 21)] = (Double)var9.get(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921877, 4));
               var10000[Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(250405468, 25)] = (Double)var9.get(Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(817650731, 7));
               return var10000;
            }
         }
      }
   }

   public Double tryParseRuntimeFlexibleDouble(String var1) {
      if (((802860230 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1024364884, 9)];
         var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 3)] = var1;
         String var2 = this.firstNonBlank(var10001).trim();
         if (var2.isBlank()) {
            return null;
         } else {
            String var3 = var2.replace((char)Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-238768958, 17), (char)Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(817656363, 7));
            if (var3.isBlank()) {
               return null;
            } else {
               try {
                  return Double.parseDouble(var3);
               } catch (NumberFormatException var5) {
                  return null;
               }
            }
         }
      }
   }

   public boolean executeRuntimeRedirectAction(Player var1, String var2) {
      if (((2004211686 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 26) != 0);
      } else {
         String var3 = this.normalizePageKey(var2, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3D/sonvPW7RsYcnzFOvekZBlcyCG20wbGAZAcQ=="));
         return !var3.isBlank() && this.openGui(
               var1, var3, (Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 13) != 0), (Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-865357317, 28) != 0)
         );
      }
   }

   public void executeRuntimeCloseAction(Player var1) {
      if ((-637397690 ^ -637397690) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         if (this.plugin != null) {
            this.plugin.closeUiForPlayer(var1);
         } else {
            this.cameraService.forceStop(var1);
            this.closeGui(var1);
            this.hudService.clearHuds(var1);
         }
      }
   }

   public long parseRuntimeActionDelayTicks(String var1) {
      if ((1233112505 ^ 1233112505) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-711434707, 30)];
         var10001[Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1024365396, 9)] = var1;
         String var2 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
         if (var2.isBlank()) {
            var2 = Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+S2g83jt8In/zACPxUhG0pGt35bgEX4T/OETO9fp04iktb/oXlwQ+w==");
         }

         try {
            if (var2.endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("NWNjpXtusFO2a2kHAJlVicyczxnn5rNUblByyYOoKtGt+wfj1Ec="))) {
               double var10 = Double.parseDouble(
                  var2.substring(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 20), var2.length() - Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-869329046, 5))
                     .trim()
               );
               return Math.max(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-2363536641649280140L, 49), Math.round(var10));
            } else if (var2.endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("vFROufuz6llVFzbB2X0OAEfQTU1tE7fj3yvF6hAQpOrYLpHE"))) {
               double var9 = Double.parseDouble(
                  var2.substring(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 18), var2.length() - Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(790055628, 13))
                     .trim()
               );
               return Math.max(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(1685143676644294144L, 11), Math.round(var9));
            } else if (var2.endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("WnYS5nZ4UVCYfiwRmpmvYBzP27Q4062kUAodkYLc"))) {
               double var8 = Double.parseDouble(
                  var2.substring(Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 22), var2.length() - Xqedii_p92kfr8.Xqedii_vjb2772t99mu(-1242767675, 17))
                     .trim()
               );
               return Math.max(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-327391462621549345L, 33), Math.round(var8));
            } else if (var2.endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Mx7uUzTmJtHHUOgShCcV/mzxKcMoQYQ48shE4XpSYY4="))) {
               double var7 = Double.parseDouble(
                  var2.substring(Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-1989137485, 11), var2.length() - Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-244273982, 17))
                     .trim()
               );
               return Math.max(
                  Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(2290559247526886604L, 3),
                  Math.round(var7 / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(251050251976942586L, 13)))
               );
            } else if (var2.endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("K+KWYZeyCBI7X8MyRBbDxXibFF8o+OMpEDObMewY"))) {
               double var6 = Double.parseDouble(
                  var2.substring(Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(91648302, 24), var2.length() - Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-355717354, 29)).trim()
               );
               return Math.max(
                  Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261190638579L, 48),
                  Math.round(
                     var6
                        * Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(567492495897392911L, 54))
                        / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_274j413le3omf(-5305453494487827227L, 57))
                  )
               );
            } else {
               double var3 = Double.parseDouble(var2);
               return Math.max(
                  Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261190638579L, 0),
                  Math.round(var3 / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-3080776738657902750L, 27)))
               );
            }
         } catch (NumberFormatException var5) {
            return Math.max(
               Xqedii_p92kfr8.Xqedii_d6u0z0eajmc9(2556893261190638579L, 34),
               Math.round(Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(3713317316427740383L, 57)))
            );
         }
      }
   }

   public int resolveOpenAnimationDelayTicks(EditorSession var1, String var2) {
      if (((-1858716908 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var3 = this.resolveRawTargetByTargetId(var1, var2);
         if (var3 != null && !var3.isEmpty()) {
            Object var4 = this.readMapPathValue(
               var3, Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Ukf2e6Rr9f5DQRzkGX2ghFENpOc2+vllbaxs0gyVdAD5ASNz9BgQBCHDtNBxut6WedTjC176VBqOukfYS9ttZAYS5GUyj2F3")
            );
            return this.parseOpenAnimationDelayTicks(var4);
         } else {
            return Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-2124571573, 22);
         }
      } else {
         return Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-1396510356, 1);
      }
   }

   public int parseOpenAnimationDelayTicks(Object var1) {
      if ((1228670906 * (1228670906 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-648444446, 26);
      } else if (var1 instanceof Number var6) {
         double var3 = var6.doubleValue();
         return !Double.isFinite(var3)
            ? Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(366593208, 26)
            : Math.max(Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1712819045, 4), (int)Math.round(var3));
      } else {
         String[] var10001 = new String[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-588533253, 23)];
         var10001[Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 28)] = var1.toString();
         String var2 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
         if (var2.isBlank()) {
            return Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1501946585, 2);
         } else {
            try {
               if (var2.endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("NWNjpXtusFO2a2kHAJlVicyczxnn5rNUblByyYOoKtGt+wfj1Ec="))) {
                  return Math.max(
                     Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 4),
                     (int)Math.round(
                        Double.parseDouble(
                           var2.substring(
                                 Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-836386642, 5), var2.length() - Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(2087432699, 29)
                              )
                              .trim()
                        )
                     )
                  );
               } else if (var2.endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("vFROufuz6llVFzbB2X0OAEfQTU1tE7fj3yvF6hAQpOrYLpHE"))) {
                  return Math.max(
                     Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-641419243, 2),
                     (int)Math.round(
                        Double.parseDouble(
                           var2.substring(
                                 Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 17), var2.length() - Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(-976047349, 19)
                              )
                              .trim()
                        )
                     )
                  );
               } else if (var2.endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("WnYS5nZ4UVCYfiwRmpmvYBzP27Q4062kUAodkYLc"))) {
                  return Math.max(
                     Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(-994568743, 10),
                     (int)Math.round(
                        Double.parseDouble(
                           var2.substring(
                                 Xqedii_p92kfr8.Xqedii_vjb2772t99mu(1242767674, 10), var2.length() - Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921893, 5)
                              )
                              .trim()
                        )
                     )
                  );
               } else if (var2.endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Mx7uUzTmJtHHUOgShCcV/mzxKcMoQYQ48shE4XpSYY4="))) {
                  return Math.max(
                     Xqedii_p92kfr8.Xqedii_lmv0hkpfqrnh(733186416, 27),
                     (int)Math.round(
                        Double.parseDouble(
                              var2.substring(
                                    Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(790022860, 13), var2.length() - Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1890370289, 25)
                                 )
                                 .trim()
                           )
                           / Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_1uyxzw93yu3um(-644287562718177623L, 38))
                     )
                  );
               } else {
                  return var2.endsWith(Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("K+KWYZeyCBI7X8MyRBbDxXibFF8o+OMpEDObMewY"))
                     ? Math.max(
                        Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1501946585, 2),
                        (int)Math.round(
                           Double.parseDouble(
                                 var2.substring(
                                       Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1449228470, 0), var2.length() - Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(384486447, 21)
                                    )
                                    .trim()
                              )
                              * Double.longBitsToDouble(Xqedii_p92kfr8.Xqedii_12um4tc7nzty0(-3683356689320452809L, 55))
                        )
                     )
                     : Math.max(Xqedii_p92kfr8.Xqedii_15ae4j3j41jaq(1449228470, 0), (int)Math.round(Double.parseDouble(var2)));
               }
            } catch (NumberFormatException var5) {
               return Xqedii_p92kfr8.Xqedii_1lu9r5ulbypuc(-596921861, 21);
            }
         }
      }
   }


   private static /* synthetic */ String Xqedii_2k09xutsx76k9(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("t8V5Z4G/HBgz1XnDfSsYEalmXOB1qZefXDnS9yl0Lzmz06KEJtpKfp2y");
    }

    private static /* synthetic */ String Xqedii_1y4oz9jctfxko(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("t8V5Z4G/HBgz1XnDfSsYEalmXOB1qZefXDnS9yl0Lzmz06KEJtpKfp2y");
    }

    private static /* synthetic */ String Xqedii_cjac1mdnfcgd(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("M8ze5WMEH8tjE1uQbs9Gsh8IF1IrNpe9hh66PTOyaNohWfAVZ7iH0j2acCCoU2K6GtGN+lyC") + string;
    }

    private static /* synthetic */ String Xqedii_efcwtpphrlrm(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("LZWqXhPufrZWzDnlRQDlh+UjXWO8Wp6ugwFy4Al361rH9Ck8ZIln2aP0xkM=");
    }

    private static /* synthetic */ String Xqedii_37j9d2a1b7mlp(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("c2b6+t3fJ7EyhSTMdyHvSmb6ZKPD53WXYuy6/5/qV1dnsC5WKgofXRiD");
    }

    private static /* synthetic */ String Xqedii_3etieby4zamu6(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Gly5Fkg+eo4PCbx84fZzyQWVaxGLw+PBxpRwDaKEWx/5i9eXPWs9Sg==");
    }

    private static /* synthetic */ String Xqedii_22uh6qqkt9ro2(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("dfk8VXStqkjXuIefu8hXoBA0l1Y2hEgMLlGkxoiHfR/4QcKwIWie6xRKL/crzw==");
    }

    private static /* synthetic */ String Xqedii_2euo1xv8nrfod(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("OJgephOkieKQwbBeqeuIgkTgF4Nuj8ZdkDT4vSFP23/9M1efA7hfm7qe");
    }

    private static /* synthetic */ String Xqedii_3fv6moeemdlfx(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("NqZJh6Obd+9ghHzDp56aXFN3pRnrVr6fN462FEohPrrOZJdUtThmVzgmulQ=");
    }

    private static /* synthetic */ String Xqedii_j0kzbxxuqgyu(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("5FoyILRGnbK088MSHGpCiGWehOX1fZPWZ6lGOzrbm8oCHihK/oE=");
    }

    private static /* synthetic */ String Xqedii_3imlqin88ynwo(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("KbtTKG3U2hVjbrf2gP3TP9+Oxy0CxNVqZIjiAvs3A/dKh/k6Uew=");
    }

    private static /* synthetic */ String Xqedii_3qbb3hj9in12n(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("fH53ze446I9FgXMoDeIeKLXRtKxcTmEa0Y5DhJvKPN8N6OXUjvc=");
    }

    private static /* synthetic */ String Xqedii_o8e159w7lisi(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Y8iGdgAChwbo4btsUG4O9DL2MfAqsRzlyezqE87HJu5HeITaFx8=");
    }

    private static /* synthetic */ String Xqedii_bnai3a0ogmjl(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("LZWqXhPufrZWzDnlRQDlh+UjXWO8Wp6ugwFy4Al361rH9Ck8ZIln2aP0xkM=");
    }

    private static /* synthetic */ String Xqedii_1c6ns9ump1ptq(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("c2b6+t3fJ7EyhSTMdyHvSmb6ZKPD53WXYuy6/5/qV1dnsC5WKgofXRiD");
    }

    private static /* synthetic */ String Xqedii_lbtgr38hia1a(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Gly5Fkg+eo4PCbx84fZzyQWVaxGLw+PBxpRwDaKEWx/5i9eXPWs9Sg==");
    }

    private static /* synthetic */ String Xqedii_3ddkpqcjejm54(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("dfk8VXStqkjXuIefu8hXoBA0l1Y2hEgMLlGkxoiHfR/4QcKwIWie6xRKL/crzw==");
    }

    private static /* synthetic */ String Xqedii_3k5opl78an3aa(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("OJgephOkieKQwbBeqeuIgkTgF4Nuj8ZdkDT4vSFP23/9M1efA7hfm7qe");
    }

    private static /* synthetic */ String Xqedii_h2lh8c7x7d08(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("NqZJh6Obd+9ghHzDp56aXFN3pRnrVr6fN462FEohPrrOZJdUtThmVzgmulQ=");
    }

    private static /* synthetic */ String Xqedii_32q87n91nrbs(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("5FoyILRGnbK088MSHGpCiGWehOX1fZPWZ6lGOzrbm8oCHihK/oE=");
    }

    private static /* synthetic */ String Xqedii_13irmj5ikeg16(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("KbtTKG3U2hVjbrf2gP3TP9+Oxy0CxNVqZIjiAvs3A/dKh/k6Uew=");
    }

    private static /* synthetic */ String Xqedii_1bgif8etm3pbk(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("fH53ze446I9FgXMoDeIeKLXRtKxcTmEa0Y5DhJvKPN8N6OXUjvc=");
    }

    private static /* synthetic */ String Xqedii_21hpevuo887im(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Y8iGdgAChwbo4btsUG4O9DL2MfAqsRzlyezqE87HJu5HeITaFx8=");
    }

    private static /* synthetic */ String Xqedii_irclssoa0pyk(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("M8ze5WMEH8tjE1uQbs9Gsh8IF1IrNpe9hh66PTOyaNohWfAVZ7iH0j2acCCoU2K6GtGN+lyC") + string;
    }

    private static /* synthetic */ String Xqedii_f5ciuehl2gn9(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("LZWqXhPufrZWzDnlRQDlh+UjXWO8Wp6ugwFy4Al361rH9Ck8ZIln2aP0xkM=");
    }

    private static /* synthetic */ String Xqedii_3kjcxhz5u83mz(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("c2b6+t3fJ7EyhSTMdyHvSmb6ZKPD53WXYuy6/5/qV1dnsC5WKgofXRiD");
    }

    private static /* synthetic */ String Xqedii_1o0circjjfyw4(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Gly5Fkg+eo4PCbx84fZzyQWVaxGLw+PBxpRwDaKEWx/5i9eXPWs9Sg==");
    }

    private static /* synthetic */ String Xqedii_oshcuf0k39s2(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("dfk8VXStqkjXuIefu8hXoBA0l1Y2hEgMLlGkxoiHfR/4QcKwIWie6xRKL/crzw==");
    }

    private static /* synthetic */ String Xqedii_1rwdryg5sjwy8(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("OJgephOkieKQwbBeqeuIgkTgF4Nuj8ZdkDT4vSFP23/9M1efA7hfm7qe");
    }

    private static /* synthetic */ String Xqedii_2rls4gdufjn9q(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("NqZJh6Obd+9ghHzDp56aXFN3pRnrVr6fN462FEohPrrOZJdUtThmVzgmulQ=");
    }

    private static /* synthetic */ String Xqedii_3eovxbrquah2b(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("5FoyILRGnbK088MSHGpCiGWehOX1fZPWZ6lGOzrbm8oCHihK/oE=");
    }

    private static /* synthetic */ String Xqedii_1a9pxbjovcbqe(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("KbtTKG3U2hVjbrf2gP3TP9+Oxy0CxNVqZIjiAvs3A/dKh/k6Uew=");
    }

    private static /* synthetic */ String Xqedii_20tbe7qey5wz8(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("fH53ze446I9FgXMoDeIeKLXRtKxcTmEa0Y5DhJvKPN8N6OXUjvc=");
    }

    private static /* synthetic */ String Xqedii_30613ysnl9tbg(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Y8iGdgAChwbo4btsUG4O9DL2MfAqsRzlyezqE87HJu5HeITaFx8=");
    }

    private static /* synthetic */ String Xqedii_3g6jgqzva943s(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("LZWqXhPufrZWzDnlRQDlh+UjXWO8Wp6ugwFy4Al361rH9Ck8ZIln2aP0xkM=");
    }

    private static /* synthetic */ String Xqedii_224k2e47zh5t2(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("c2b6+t3fJ7EyhSTMdyHvSmb6ZKPD53WXYuy6/5/qV1dnsC5WKgofXRiD");
    }

    private static /* synthetic */ String Xqedii_15yvyr3f09zd3(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Gly5Fkg+eo4PCbx84fZzyQWVaxGLw+PBxpRwDaKEWx/5i9eXPWs9Sg==");
    }

    private static /* synthetic */ String Xqedii_2hh402agz85os(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("dfk8VXStqkjXuIefu8hXoBA0l1Y2hEgMLlGkxoiHfR/4QcKwIWie6xRKL/crzw==");
    }

    private static /* synthetic */ String Xqedii_5s8wj71nk0fm(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("OJgephOkieKQwbBeqeuIgkTgF4Nuj8ZdkDT4vSFP23/9M1efA7hfm7qe");
    }

    private static /* synthetic */ String Xqedii_12s512zfhfa3n(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("NqZJh6Obd+9ghHzDp56aXFN3pRnrVr6fN462FEohPrrOZJdUtThmVzgmulQ=");
    }

    private static /* synthetic */ String Xqedii_3po698pprq1ec(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("5FoyILRGnbK088MSHGpCiGWehOX1fZPWZ6lGOzrbm8oCHihK/oE=");
    }

    private static /* synthetic */ String Xqedii_2x5lhrusgcsay(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("KbtTKG3U2hVjbrf2gP3TP9+Oxy0CxNVqZIjiAvs3A/dKh/k6Uew=");
    }

    private static /* synthetic */ String Xqedii_2i969p0l6o0tz(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("fH53ze446I9FgXMoDeIeKLXRtKxcTmEa0Y5DhJvKPN8N6OXUjvc=");
    }

    private static /* synthetic */ String Xqedii_f9a6fc1zs49f(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Y8iGdgAChwbo4btsUG4O9DL2MfAqsRzlyezqE87HJu5HeITaFx8=");
    }

    private static /* synthetic */ String Xqedii_32qthpmvqknu6(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("SKT2z2K3gBM5oazG/53+1yzXwMi+KrA/o2UTnPiLTex09ee5SYcy0HaVch+3xlLw");
    }

    private static /* synthetic */ String Xqedii_2lk3pcxewh73c(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("BwNh81dsMrghodTMDF8NAlx82QwlfFLVqFTdaLOWfl8ZD3rtVmEyXhxl4yZSVmL7MD4HVniPuMPEkA==") + string;
    }

    private static /* synthetic */ String Xqedii_1men99pr0i32m(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("4AgOXhiEDBA0QyuA8qPF06mlve/l+GhYCzwHsHYHzeOTH7IXZB8WIp9Y6k8SslPLxU77SDBUFvzNAL55EdGHTJihdis=") + string;
    }

    private static /* synthetic */ String Xqedii_1qv8zshh99qi0(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("OHzgv9WROPuEPSea8ORPSf8YpWzJc1dH1RT2uu4TVtHsHk0uo47ZzQ==") + string;
    }

    private static /* synthetic */ String Xqedii_143ubywj47rtd(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3ES2GpB8LrpmjEJVS8ITjvxswtSFbW1EvpXIhGGwWLCYskWNxcYwbhEVNqF5dqRE6m8=") + string;
    }

    private static /* synthetic */ String Xqedii_1leeh0c7nh5bk(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("QviKtWQpCFZu2idQtd1ZgMDU560NmSzbjdrSmdxy+DYGwl/q0W80MFKHybS+uRKYEdS9cevMvCY9JPKckX4Z9cHuZbSbm+cgXN7ZhOFIzyst/zgDus7f4qW7TkQaJNtSCzBIMWOJI+9RYIl5W2VZmK/S2WhmfYVsQP80PLeSszAUtA==") + string;
    }

    private static /* synthetic */ String Xqedii_1igsqaqi2x0bs(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("hANKm7ZfIZ14VDHJQjeYMMitjCKXXrStk66FkH9rSOPeT/XLFEK7oj/i4iF6MmfFso3jgc0oIPvriqcAhtbCzQfWI04YUIX23pwuCXGCrYj0Di2l5LRR/BbdCeVenqbd0NI44hszfx4jhck6dRngBwA/roPL5o4D") + string;
    }

    private static /* synthetic */ String Xqedii_x4atuxi96zf8(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("qmFr72RqlZaKTgpBNMptaFHrWXPCClY4nIMAyqA7niNlE93c/+uaTgZIy1dShOp530r3ZdTXZ0Bb2D9O3odBj+a553GOQkxtOYItr7bMX6UfWntu7ymQ/Wi+EZaQL7fq33KRntpoAEI7FtooU5IA+iwJypG7KoN5qd1D4Q6TaFnULxRT81y/lvhlw5E=") + string;
    }

    private static /* synthetic */ String Xqedii_255vzb852e860(String string, String string2) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("NiPcPb+FXD5cSUZQr/I8tLaaSI70Ebktw8jfgOzmCZi4WUxH/m0XlzKqGO4K01esiP1bod2ScbhQo1fedBZExIgreQFgzhyTu87mWcjBqnyRAOSDukKHuU9A9uwIiZbU11JhJ3r0B8sMa4EcTDwaUv1TioNdCwYx8XA=") + string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("DpgWNrGPkCmDug26b2RKHwYfj+NX0WN5jBXblAVlvH4qYg==") + string2;
    }

    private static /* synthetic */ String Xqedii_2ce8u6gzyemlc(String string, String string2, String string3, String string4) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("YqTVXCb6cEuzI4ZIW3m00gAZskZY99phmk6EB8HKeoK6WT2juzjlf5DM3OABSSXsVedNSR1cHHoAVNEMb0PI0SNEgECA6cfCY2arI1sfC9R3kGyvY8RNzdbEy6I=") + string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3URQhI4pGEIf9cWxI5TQYANPsRTh8e3XKfC22qleqsB5S68WBebPPEtz") + string2 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("CpLxhVWF/GF5C6X4Jucj7O0vWrYYUjcYxRKEHrZqG4kTpjns") + string3 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("4C2nKufrNafSDtppjcz8sWNS5B0FpkCAmiY5CvfR+bM=") + string4;
    }

    private static /* synthetic */ String Xqedii_19h4knjbf5kuf(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("AVzvgTngHtAd329ChJmTCsbGeUTvT7BkOxtQWDZNyUXYuVWW7j5A0opTX2jaJ3Sh1H2tEUG7qAqrHp0FIivcT9FPs/hUOOK36Xx911HjG23BCyJ9ivjfPpSLgOXLjRdilVFpG+CcLg6vSoTNxK4lhKOM00s0j8B9gWU=") + string;
    }

    private static /* synthetic */ String Xqedii_cfty768uujj7(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("5oOfTnopihC+RHNAD5Yd2L5pa3smngHWCY/dRPIgHwfMRfG0ID9MHj4FdyC4skBvAHzlLTTV3JU7gZpqZhO8v2aN7HYDMSV8bDPis++7ZKnKbj79ffaNgcfDJWGjSuW474CqI10SKZTAFwmoK/dScTbgmfAyQ/C3dgU=") + string;
    }

    private static /* synthetic */ String Xqedii_2bfq6ltl0kr3d(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3C5lv8oua6//icCiowdiPwrkVOVyPSoOGWIh46hwDkNg80Ki3bANXW2ZxG8PEKdip1aD/JenLv8N0iEi0/NUgC6ERgVKR2H07c/fwQ4lKrd3F2cz7W3JLqQMygABriZoFTnHHeK8L3OXXprh5UZYPJhfuVemljCMtv0Fbxen/rFjUgI+SB0=") + string;
    }

    private static /* synthetic */ String Xqedii_t9355lxonr4l(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8fhSVq6l2JYVxn9xaO3x4Y5zI8N5MsB9NWj7Wojzl3GQQGLq6gzzbRiBEeUu6AYTK5w1dP0cb1aiSv2CQ4+or+KU57equCMOzNszVM3CANXql5twDdjPGKqHSMvPyVsZZxeC5Pf/xY99mP+6M0PxUVGyqFPFv0GrfrV3Z66CWORFhXb88ve84iaY85k=") + string;
    }

    private static /* synthetic */ String Xqedii_kde4gubkp0hb(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("X2gWffmSryBVBhmWSIfgfMHvzi0Y/2uaOw9aiVLt3pGUnpBfW3lnJW9EWuCamUgKCvZHHrL2j0arABJBiWHb0tp/SEkBRuCk0y3mO4U6Jng+wdAyPcpu9X0d6Txtyd6BbblfZt9gIFAu9+YjvifhuJEUJJ/YGLRvseofTuh5I8U7O9HF") + string;
    }

    private static /* synthetic */ String Xqedii_1j6vuftrvmapa(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("80nfGO/kTmE/t1oT47GUL8mQyR3O5DStf5lWKQWPwknorWxEaZVny1EjykFYTnLsghvyXt9UwUxh3/j5xpDPYBCVPiWRYoixErv3ZW/yiziBPNUBMy4l0Cw5YJQMBvM/F/K/eg/Z4/E7NcebeMEPewCx6x/LqFTa7NBj3Hd5rheXuf/K9+9V2fP6cosCfZtlxP0=") + string;
    }

    private static /* synthetic */ String Xqedii_x9xil6i22sbw(String string, String string2) {
        return string + string2;
    }

    private static /* synthetic */ String Xqedii_biotc5f0hjn8(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11, String string12, String string13, String string14) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("l7Zv92kARN9SHC68DrkJJFzvyXI7aaL519rXKHW1u4fYq3iLE6uzkJCRYAasxQzjc9wlzO7sldms3VZHY/EKiMxfau4=") + string2 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("SXQusTcc4lABnaN2DXHwpR+QxHyoLYowQwCcrPUcfMcT6desuV0tzVEXsG1LyVP9gI5IqP6wwe4bhjxOK1TdS1FHvcwLcRbp1P9b7Q==") + string3 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("YlVr3pciACAIzCbQpWKTXGJe/uBBjQoSdnFkxv/O2/02pmpfMyP1GXnac9Bp9g8dO+tLCaOWUvSuVV2afjYPVlxl") + string4 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("wJzUzywJoGzJknebVerIeDZUll6L7Y3sa3SX3THzecgQGPzFGi+HoYb1QdId+99LHXXC42QZ0F0ph3qShRWq3g==") + string5 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("3pFTlT8O3BryO/CDmt/VljJBT9qyRWW6QO4ABYOtBoPa9sSG3co/qiTorxzCux/inZN5U6lnpoC7cDOLAba36lIfgzfUhg==") + string6 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("tl/5G31EWkPwS7nKj+JIiPCvTgLCl8g9ERHS3Cn34hLFlB/dTzhgXe5tKiV35LT65fd97l3AxBG95yYFUtviuory") + string7 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("bFahN92jmeaou44heeIDckmbBg8lfEe0UjKNyGdyfH0q0k4CcNZNsysaycwAyjHuTthh6kQYp9qDK1FDsA4=") + string8 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("UMPdSZPNCSHd+lQwJBzqSUECjaC0fWdEWuYGxIwgI8iayLYqEBbXmqu78vQsv/MOZ9LZFNcC58Y=") + string9 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("BGsVfOlwRo0dRwKBgGWztMPnWHBjlI+fdVrVIPlIwO9SZkqT1z/QQV7X3U0cTl1cdeNwLcy1OhU=") + string10 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("pbRuFhplwNiqXIXFfMklMg3ER2DQmk4ud0jukeGdgrxoYVevQMmuEw855T0LMKEY+V+qQ0I5") + string11 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("qVd+reuTZt5oMxhHUybpob5dE6AyD8sdV/ddvZmPyhnxAm1XMC/+BbWhoud4EnwozdLENV+mm55E/dGoMyMckV4S+cI=") + string12 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("itfxS5nfceoamOTM0ErtIoNKYe9+ATPs2vOcb8jtle+rWl8vojLs5FLl0jI+/2iRJa95wbN+EEVscsS4Uom+TLam4I9Z0w==") + string13 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ZpPWUex4zrNro2YKIDwEUp3zSM6xcN5OTK7tUNWEAwp8iNfJ9pQ/wyOzKPqSu5y6Sh9/7iUFjG4hXqbHVykgqg==") + string14;
    }

    private static /* synthetic */ String Xqedii_16xkvi9glm09m(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("//3okoTf+Qh4/4my0NpwIARW1pAl5a+qSaJRIuYwTmrR/cGw+C9nCrl7Dds6ZeV+nKZeRETUDJ4oMvVXAvEY9CQBwsMQEljZZpQLja9TfYPV/HBTZ1YfEvOr9xrdz2ZO/Dvglq/db5xk+lXHijzl3AhQ/daxNrpXpRQF7tKWrOEHxgGBGt1EGSmCdkIkZ9ilvhnJuA==") + string;
    }

    private static /* synthetic */ String Xqedii_n6cgjj35jc4s(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Njd68u2B/EWKvA/GTY7No130RBHJEZVH+rS4n+frU6CW+wR8Lw4Cqt9hQwLN0L19FVYCeaZaIvIaw+7/Txe+Y7pD1r/Yq8hHHkc9hGEOAjJyq/DuoihTjFri54c0mH4gAgyKNw==") + string;
    }

    private static /* synthetic */ String Xqedii_3ujm1jl1i6qi1(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("xaiIe7MLJ6XBlZ53OhF350QDWs2ln+tIWd7XRqaMQC7BNengIDXOAlIHyW34U2AaKvtgtR6WvokEKid0VjZNRjZA3G0qGyylamQnH4o17mU6viFAOMX6KTJngF7rh/Ll/HG06b3zWIedxT+Q4Ig=") + string;
    }

    private static /* synthetic */ String Xqedii_1uiz7gcobas0n(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("tApVS+PagpvkuVVqDfrBE4yyQ6h6zaiJ1aWGDYEgG/jpmfs7") + string;
    }

    private static /* synthetic */ String Xqedii_39otv7bikdz89(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("q+Kpihnh5Qs7mvNfn5RDpE5ZUdjipq7V6nURTRwruOsVB9mwrTog2Q==") + string;
    }

    private static /* synthetic */ String Xqedii_2n68napi9kqha(String string, String string2, String string3) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("HwPcaHY4Q+vofVZkiKBIe6OV7omGC3BSvVNZVw65AKA2Tx2Oh2fJ6A==") + string2 + string3;
    }

    private static /* synthetic */ String Xqedii_wjg40xafzewn(String string, String string2, String string3) {
        return string + string2 + string3;
    }

    private static /* synthetic */ String Xqedii_2hu9awjmkipln(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("tApVS+PagpvkuVVqDfrBE4yyQ6h6zaiJ1aWGDYEgG/jpmfs7") + string;
    }

    private static /* synthetic */ String Xqedii_2cqczlm9g6ube(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("q+Kpihnh5Qs7mvNfn5RDpE5ZUdjipq7V6nURTRwruOsVB9mwrTog2Q==") + string;
    }

    private static /* synthetic */ String Xqedii_nypqmlc1om7w(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("m+svjFGwSAAChwK9uMQQk7GsNMWe8jOU5/h2xnsTI6U=");
    }

    private static /* synthetic */ String Xqedii_1btsh5xl39xaj(String string, String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, String string10, String string11, String string12, String string13, String string14, String string15, String string16, String string17, String string18, String string19, String string20, String string21, String string22, String string23, String string24, String string25, String string26) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("LRfyYXs6fWHdux9wsWqdVknqspZuy5edWHUs5UHqRiVjqCq1BKwMdJd12FnO6DqAgtWvAm/2") + string2 + string3 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("tApVS+PagpvkuVVqDfrBE4yyQ6h6zaiJ1aWGDYEgG/jpmfs7") + string4 + string5 + string6 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("NZViDB19Ds2/i1Fp5j3boJJab73dumXaLCBF/E+7y4fyTOghhzii/ys/M/z5HNtOTZxs3rg+") + string7 + string8 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("nrN+FUnkdLqO7mqvdGAfEGUQwHowGSkKncqzPmvs/ANho0C/erbd2k8o6llNqL2j") + string9 + string10 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("lWewqaFNpGEi+V6eDG0sWEIWKo+bXT2dj1akI0LgmcT8KQnI3VZ6PPvlk7BdC/EDblLHbhi1") + string11 + string12 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("76IjoaO2bGhfGhzbn+WMjOT4TB0hPG1E19UeyNhXv2Eq4NPa7Z+yeUxGUcI/rU+wLJE=") + string13 + string14 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("sanAfVPY+NTcjImSDxQTEi7pGwMKviNUZQA7wH0xkK2CkIfKTByNjJEvEOmbiQ==") + string15 + string16 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("2c9aRz6sX6mHnPCF8rcOk89Crq5RzH7AOcHTOOSc4RFOFjqcAW3C9AAm") + string17 + string18 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("xfJ2bKwP4N6Df/3udw6PY7YXLrH8y9XSHjvbAS/7AXicp1clshwLHUtDorw=") + string19 + string20 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("2o4bwfCnab624rdEDNRtb5IO4ocaimeSYqpKj49fe4v6myR2ExA=") + string21 + string22 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("VBzh1kKiT7s2gz0fYwyZY0M9GWc/Nv6Mf/F2ndXy/6BLH9GTWCu5Le9nt9NlNguFtbUwjQ==") + string23 + string24 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("H76ryHRg2WJ6DEN+d7hjD6UZQCTjMyb2EU/m1AVdURk50ApYfrpYY4NdUffIImleRbR53g==") + string25 + string26 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Zm4EpxYz+/attL66OyKQxGAptTLwHgpylRkDe0klEzCiRgkRjRaD5ld4");
    }

    private static /* synthetic */ String Xqedii_7dzilksqgoai(String string, String string2, String string3) {
        return string + string2 + string3;
    }

    private static /* synthetic */ String Xqedii_2mhuf7n6702ap(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("tApVS+PagpvkuVVqDfrBE4yyQ6h6zaiJ1aWGDYEgG/jpmfs7") + string;
    }

    private static /* synthetic */ String Xqedii_1n9mv8c40qdys(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("RE/bqmL+20Bn0VmggC1Yb7nvkD6B4dWoFAP/Y+NqrYbwD7zK") + string;
    }

    private static /* synthetic */ String Xqedii_tiiir4isgzpj(String string, int n) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("A+bgzf2PktwnPG+R0TWSaM2AfGgZBNkA0JAu2FiO") + n;
    }

    private static /* synthetic */ String Xqedii_cayu6mz9yyn5(String string, int n) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("a9ZTyMp55DQgcx7aRKwyXh7564aimltI2QWAlqml") + n;
    }

    private static /* synthetic */ String Xqedii_b1emktbl3o4(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Mk4aB45IjXxFDXUlRAwhS6+/GSW7oHNFVhNpHRZkXQUCw6PeEKKYIuAsyHjgBoSxMCq421qZ67Y0YA==") + string;
    }

    private static /* synthetic */ String Xqedii_z8lc50aijzq9(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Mk4aB45IjXxFDXUlRAwhS6+/GSW7oHNFVhNpHRZkXQUCw6PeEKKYIuAsyHjgBoSxMCq421qZ67Y0YA==") + string;
    }

    private static /* synthetic */ String Xqedii_3b2tw9ccigmis(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("zK3TYQxYutJBhTyn4EXx0D5zW+f78s+3SMab3neCdB36TeBIzJA=") + string;
    }

    private static /* synthetic */ String Xqedii_1c1elc7sa1qi0(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("RE/bqmL+20Bn0VmggC1Yb7nvkD6B4dWoFAP/Y+NqrYbwD7zK") + string;
    }

    private static /* synthetic */ String Xqedii_vyb8xys67v16(String string, int n) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("RE/bqmL+20Bn0VmggC1Yb7nvkD6B4dWoFAP/Y+NqrYbwD7zK") + string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("A+bgzf2PktwnPG+R0TWSaM2AfGgZBNkA0JAu2FiO") + n;
    }

    private static /* synthetic */ String Xqedii_2pmi6itqo479k(String string, int n) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("a9ZTyMp55DQgcx7aRKwyXh7564aimltI2QWAlqml") + n;
    }

    private static /* synthetic */ String Xqedii_1p1fvkiiux6ds(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Mk4aB45IjXxFDXUlRAwhS6+/GSW7oHNFVhNpHRZkXQUCw6PeEKKYIuAsyHjgBoSxMCq421qZ67Y0YA==") + string;
    }

    private static /* synthetic */ String Xqedii_2mkn5c3tbsnbq(String string, String string2, String string3) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("zDImd76pNyA4wgPz8g9mjzFRo+pS5YNySk4IGKZ/dwVE/NvXtfKxwYdDYet4spUE+NM=") + string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ipVAuNGWWosZeYrngK1BfigKEkzESd+BYagY4lTjkO4=") + string2 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("ZCT+fI3fnfQbicxRMaHCsBW9bYtuQo2rPUl1F4c5") + string3 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("o3NoHASyLd8kT0yL6zB1wgZP9IonMVviBwq7zyOAmDnP+V3n3tLTwzdeRzsjDUywqw0=");
    }

    private static /* synthetic */ String Xqedii_3gy1wfh87kn26(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("TVlrjWusmMjoiVgx7hMuh6TAT5pwNJh02JSh/buX7iviaExM");
    }

    private static /* synthetic */ String Xqedii_1k9i9nn3k6f71(String string, String string2) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("wWFjS5xbBPXcW3ZHCEKIAI3Th9mIuZKwxRAeERaYAcrAlnXX6jWIShu+8mkmrdgLk8vrYs+8iRPQl0SF7HF0jjaksCskpKL4Jw8=") + string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("DpgWNrGPkCmDug26b2RKHwYfj+NX0WN5jBXblAVlvH4qYg==") + string2;
    }

    private static /* synthetic */ String Xqedii_1q6btyady1452(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("TVlrjWusmMjoiVgx7hMuh6TAT5pwNJh02JSh/buX7iviaExM");
    }

    private static /* synthetic */ String Xqedii_2yt5ntof97in4(String string, String string2) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("aHXiXJEPF40LjPBGkRNR6x+6BVp2rzU3xRpteBModESgRpKOzUp392IwMxmP4eY5SZf9Ve3iPnMglW7R75vRaGMEMNQncZHqSsHN38aubf88SZFo1s8CncP3IYp83VwrcR0tbzmJG9pA/g==") + string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("DpgWNrGPkCmDug26b2RKHwYfj+NX0WN5jBXblAVlvH4qYg==") + string2;
    }

    private static /* synthetic */ String Xqedii_1qv0cqpgkqr0j(String string) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("TVlrjWusmMjoiVgx7hMuh6TAT5pwNJh02JSh/buX7iviaExM");
    }

    private static /* synthetic */ String Xqedii_71sr2km80v7b(String string, String string2) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("WDZ+qllfR5v23a00NhAGITI7YeYJAvW2uGAAM4D7RnHHUjbCHbSXtbd1QxwmrIon7HWdjxp6L3LsfbIC6hUuu+U+FFZaAyH1NY0=") + string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("DpgWNrGPkCmDug26b2RKHwYfj+NX0WN5jBXblAVlvH4qYg==") + string2;
    }

    private static /* synthetic */ String Xqedii_11jyyg9tm1vyu(String string, String string2) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+NaziuEyjU1mMDAMYA83LDeIFD2IYm7Ad/ZEAXrCipo=") + string2;
    }

    private static /* synthetic */ String Xqedii_sx8pc51x9y03(String string, String string2) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+NaziuEyjU1mMDAMYA83LDeIFD2IYm7Ad/ZEAXrCipo=") + string2;
    }

    private static /* synthetic */ String Xqedii_1n0tfvi7yzs59(String string, String string2) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+NaziuEyjU1mMDAMYA83LDeIFD2IYm7Ad/ZEAXrCipo=") + string2;
    }

    private static /* synthetic */ String Xqedii_383sz7xo3wy44(String string, String string2) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+NaziuEyjU1mMDAMYA83LDeIFD2IYm7Ad/ZEAXrCipo=") + string2;
    }

    private static /* synthetic */ String Xqedii_klq7277hcekz(String string, String string2) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+NaziuEyjU1mMDAMYA83LDeIFD2IYm7Ad/ZEAXrCipo=") + string2;
    }

    private static /* synthetic */ String Xqedii_3ib8evgawn89i(String string, String string2) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+NaziuEyjU1mMDAMYA83LDeIFD2IYm7Ad/ZEAXrCipo=") + string2;
    }

    private static /* synthetic */ String Xqedii_36ki05lkvd4x3(String string, String string2) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+NaziuEyjU1mMDAMYA83LDeIFD2IYm7Ad/ZEAXrCipo=") + string2;
    }

    private static /* synthetic */ String Xqedii_1dzll8epa676t(String string, String string2) {
        return string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("+NaziuEyjU1mMDAMYA83LDeIFD2IYm7Ad/ZEAXrCipo=") + string2;
    }

    private static /* synthetic */ String Xqedii_2laq9582k8nvi(String string, String string2, String string3, String string4, String string5, String string6) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("g1H3UmAa9ZSpN4y0F78+p8UQ+2fxSSqYQ0ca7ryRS5RxwJQazL+s/2iyoVF6gcvk") + string + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("a9ZTyMp55DQgcx7aRKwyXh7564aimltI2QWAlqml") + string2 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("WhQxq4JKXWN2AV2LFoCVHck/cafFbccvlwfM7F7z6Q0=") + string3 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("zzvXP1RtUYEl6fPu/k4RfPTeUne5x3O3+LyOqCFByHwSpA/gVHE=") + string4 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Yd1JWGsUE9f0ydp8ggLYt40aWWEB7moe01zwPUt9v+9VMb8Xa7P3Fw==") + string5 + Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("8K3C4RwTZfhqdr9WDgkYumEz465zMK21FoATzfst") + string6;
    }

    private static /* synthetic */ String Xqedii_3dvfw9qxp3w(String string) {
        return Xqedii_p92kfr8.Xqedii_2bk7y6qkjw7ma("Mk4aB45IjXxFDXUlRAwhS6+/GSW7oHNFVhNpHRZkXQUCw6PeEKKYIuAsyHjgBoSxMCq421qZ67Y0YA==") + string;
    }

   public static final class ParsedPivotInput {
      public static final String Xqedii_qm4at7mclrw5 = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";
      public final double Xqedii_1qdc7cz;
      public final double Xqedii_19ds0pz;
      public final boolean Xqedii_md2611d;

      public ParsedPivotInput(double var1, double var3, boolean var5) {
         this.Xqedii_1qdc7cz = var1;
         this.Xqedii_19ds0pz = var3;
         this.Xqedii_md2611d = var5;
      }
   }

   public static final class PivotTargetData {
      public static final String Xqedii_17kpzbgukb2n = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";
      public final double Xqedii_3pgugus;
      public final double Xqedii_dy0gqnx;
      public final double Xqedii_37nwtcg;
      public final double Xqedii_21w515y;
      public final boolean Xqedii_293ks5z;
      public final double Xqedii_2cx2nc5;
      public final double Xqedii_39wfhog;
      public final double Xqedii_3qpdp0j;
      public final double Xqedii_3e650be;
      public final double Xqedii_1sksyxo;
      public final double Xqedii_2sc9vea;

      public PivotTargetData(
         double var1, double var3, double var5, double var7, boolean var9, double var10, double var12, double var14, double var16, double var18, double var20
      ) {
         this.Xqedii_3pgugus = var1;
         this.Xqedii_dy0gqnx = var3;
         this.Xqedii_37nwtcg = var5;
         this.Xqedii_21w515y = var7;
         this.Xqedii_293ks5z = var9;
         this.Xqedii_2cx2nc5 = var10;
         this.Xqedii_39wfhog = var12;
         this.Xqedii_3qpdp0j = var14;
         this.Xqedii_3e650be = var16;
         this.Xqedii_1sksyxo = var18;
         this.Xqedii_2sc9vea = var20;
      }
   }

   public static final class RuntimeFrameAnimationTile {
      public static final String Xqedii_25jsca9ux98z9 = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";
      public final String Xqedii_1mlytkm;
      public final List<String> Xqedii_3daxkae;
      public final int Xqedii_ow714yo;
      public final boolean Xqedii_1mwmkf3;
      public int Xqedii_9w6178v = Xqedii_2lun6e9.Xqedii_1408ammsxycfh(-1555134230, 7);
      public long Xqedii_3rr7prv = Xqedii_2lun6e9.Xqedii_vnljktpat6b7(-4325394570001336501L, 4);

      public RuntimeFrameAnimationTile(String var1, List<String> var2, int var3, boolean var4) {
         this.Xqedii_1mlytkm = var1;
         this.Xqedii_3daxkae = var2;
         this.Xqedii_ow714yo = var3;
         this.Xqedii_1mwmkf3 = var4;
      }
   }

   public record RuntimeSoundSpec(String soundToken, double volume, double pitch) {
      public static final String Xqedii_kqp4zoazadnp = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";

      public String soundToken() {
         if ((1903421634 * (1903421634 + 1) & 1) != 0) {
            throw new IllegalStateException();
         } else {
            return this.soundToken;
         }
      }

      public double volume() {
         if ((-636420352 * -636420352 + -636420352 & 1) != 0) {
            throw new IllegalStateException();
         } else {
            return this.volume;
         }
      }

      public double pitch() {
         if ((1013580638 ^ 1013580638) != 0) {
            throw new IllegalStateException();
         } else {
            return this.pitch;
         }
      }
   }

   public record RuntimeTeleportSpec(String worldName, double x, double y, double z) {
      public static final String Xqedii_2y44fzm6syo4y = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";

      public String worldName() {
         if (((864069395 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
         } else {
            return this.worldName;
         }
      }

      public double x() {
         if ((-976511994 * (-976511994 + 1) & 1) != 0) {
            throw new IllegalStateException();
         } else {
            return this.x;
         }
      }

      public double y() {
         if ((2013018995 * 2013018995 - 2013018995 & 1) != 0) {
            throw new IllegalStateException();
         } else {
            return this.y;
         }
      }

      public double z() {
         if (((-100411872 | 1) & 1) == 0) {
            throw new IllegalStateException();
         } else {
            return this.z;
         }
      }
   }
}
