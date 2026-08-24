package dev.xqedii.ultimateUI.service.gui.editor.interaction;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.gui.model.HoverElement;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.editor.GuiServiceEditorShellSupport;
import dev.xqedii.ultimateUI.service.gui.editor.shell.managers.AnimationTimelineOperationsManagerBase;
import dev.xqedii.ultimateUI.service.gui.model.EditorPropertyField;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.gui.model.EditorTool;
import dev.xqedii.ultimateUI.service.gui.model.PreviewViewport;
import dev.xqedii.ultimateUI.service.hud.HudPositionCalculator;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.util.PlatformCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.bukkit.util.Vector;

public abstract class GuiServiceEditorInteractionOverlaySupport extends GuiServiceEditorShellSupport {
   protected static final String SELECTION_OUTLINE_COLOR_DEFAULT = "<#2f7dff>";
   protected static final String SELECTION_OUTLINE_COLOR_INVISIBLE = "<#42aaff>";
   protected static final String SELECTION_OUTLINE_COLOR_LOCKED = "<#30ff98>";
   protected static final String SELECTION_OUTLINE_COLOR_INVISIBLE_LOCKED = "<#21ff4a>";
   protected static final String SELECTION_OUTLINE_GLYPH = "\ue67b";
   protected static final String PREVIEW_SIZE_PANEL_ID = "preview_size";
   protected static final String PREVIEW_UI_ADDONS_PANEL_ID = "preview_ui_addons";
   protected static final String PREVIEW_SIZE_TEXT_ID = "page_info_text";
   protected static final double PREVIEW_SIZE_OUTLINE_BOTTOM_INSET = 12.0;
   protected static final double PREVIEW_EMPTY_INFO_OFFSET_X_RATIO = -0.0264;
   protected static final double PREVIEW_EMPTY_INFO_OFFSET_Y_RATIO = 0.0433;
   protected static final long TIMELINE_DRAG_LEFT_HOLD_GRACE_TICKS = 0L;
   protected static final String ANIMATION_PREVIEW_PATH_COLOR = "<#2f7dff>";
   protected static final String ANIMATION_PREVIEW_PATH_GLYPH = "\uef39";
   protected static final String ANIMATION_PREVIEW_KEYFRAME_GLYPH = "\uef38";
   protected static final String ANIMATION_PREVIEW_KEYFRAME_SELECTED_GLYPH = "\uef37";
   protected static final String ANIMATION_PREVIEW_PATH_RUNTIME_PREFIX = "editor_animation_preview_path_";
   protected static final String ANIMATION_PREVIEW_KEYFRAME_RUNTIME_PREFIX = "editor_animation_preview_keyframe_";
   protected static final double ANIMATION_PREVIEW_PATH_LAYER = 8993.6;
   protected static final double ANIMATION_PREVIEW_KEYFRAME_LAYER = 8993.7;
   protected static final double ANIMATION_PREVIEW_PATH_SIZE = 24.0;
   protected static final double ANIMATION_PREVIEW_KEYFRAME_SIZE = 32.0;
   protected static final int ANIMATION_PREVIEW_PATH_MIN_DOTS = 3;
   protected static final int ANIMATION_PREVIEW_PATH_MAX_DOTS = 220;
   protected static final double ANIMATION_PREVIEW_PATH_LENGTH_PER_DOT = 130.0;
   protected static final double ANIMATION_PREVIEW_PATH_LENGTH_EPSILON = 1.0E-4;
   protected static final double ANIMATION_PREVIEW_PATH_ZOOM_DENSITY_GAIN = 0.1;
   protected static final double ANIMATION_PREVIEW_PATH_ZOOM_OUT_MAX_FACTOR = 1.08;
   protected static final double ANIMATION_PREVIEW_PATH_ZOOM_MORE_DOTS_THRESHOLD = 0.8;
   protected static final double ANIMATION_PREVIEW_PATH_ZOOM_IN_DENSITY_GAIN = 0.6;
   protected static final double ANIMATION_PREVIEW_PATH_ZOOM_SMOOTH_DOT_BIAS = 2.2;
   protected static final double ANIMATION_PREVIEW_PATH_ZOOM_SIZE_GAIN = 0.32;
   protected static final String ANIMATION_PREVIEW_ROW_POSITION = "position";
   protected static final double IMAGE_GROUP_FRAME_BLOCK_SIZE = 250.0;
   protected static final double IMAGE_GROUP_FRAME_COORDINATE_PRECISION = 1000000.0;
   protected static final String IMAGE_GROUP_FRAME_SHIFT_Y_KEY = "__editor_frame_shift_y";
   protected static final double INHERITED_ROOT_MIN_SIZE = 1.0E-4;
   protected static final double INHERITED_CHILD_MIN_SIZE = 1.0E-9;
   protected static final double IMAGE_GROUP_MIN_WIDTH = 8.0;
   protected static final double IMAGE_GROUP_MIN_HEIGHT = 8.0;
   protected static final double IMAGE_GROUP_OUTLINE_VISUAL_X_ADJUST = 0.0;
   protected static final double IMAGE_GROUP_OUTLINE_VISUAL_Y_ADJUST = 0.0;
   protected static final double IMAGE_GLYPH_LEFT_ANCHOR_FACTOR = 0.45281250000000006;
   protected static final double IMAGE_GROUP_SIDEBAR_OFFSET_X = 29.0;
   protected static final double IMAGE_GROUP_SIDEBAR_OFFSET_Y = 248.0;
   protected static final double IMAGE_GROUP_NATIVE_TILE_WIDTH = 64.0;
   protected static final double IMAGE_GROUP_ATLAS_TILE_SIZE = 256.0;
   public static double IMAGE_PARTIAL_GLYPH_X_SLOPE = 0.5;
   public static double IMAGE_PARTIAL_GLYPH_X_CAP = 29.0;
   public static final double IMAGE_GROUP_NATIVE_TILE_HEIGHT = 64.0;
   public static double IMAGE_GLYPH_SCALE_DRIFT_FACTOR = 3.88;
   private final Map<String, GuiServiceEditorInteractionOverlaySupport.HoverEffectState> hoverEffectBaseStates = new HashMap<>();
   private final Map<String, GuiServiceEditorInteractionOverlaySupport.HoverEffectState> hoverEffectAppliedStates = new HashMap<>();
   private final Map<String, Integer> hoverEffectAnimationTokens = new HashMap<>();
   private final Set<String> hoverEffectRestoringIds = new HashSet<>();
   private int hoverEffectAnimationTokenCounter;
   private static final String[] ROUNDED_PART_SUFFIXES = new String[]{
      "_r_core", "_r_top", "_r_bottom", "_r_left", "_r_right", "_r_tl", "_r_tr", "_r_bl", "_r_br"
   };

   protected GuiServiceEditorInteractionOverlaySupport(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   protected abstract void renderResolvedContent(Player var1, EditorSession var2);

   protected abstract void renderEditorTransparencyOverlay(Player var1, EditorSession var2);

   protected abstract void startMoveDrag(EditorSession var1, double var2, double var4);

   protected abstract void stopCursorToolDrag(EditorSession var1);

   @Override
   protected void translateTargetElements(Player var1, EditorSession var2, String var3, double var4, double var6) {
      if (var2 != null && var3 != null && !var3.isBlank()) {
         if (!(Math.abs(var4) < 1.0E-4) || !(Math.abs(var6) < 1.0E-4)) {
            for (HoverElement var9 : this.findTargetElements(var2, var3)) {
               if (var9.baseLocation != null) {
                  var9.baseLocation.setX(var9.baseLocation.getX() + var4);
                  var9.baseLocation.setY(var9.baseLocation.getY() + var6);
                  var9.centerX += var4;
                  var9.centerY += var6;
                  this.updateElementHud(var1, var9);
               }
            }
         }
      }
   }

   @Override
   protected void translateSelectionOutline(Player var1, EditorSession var2, double var3, double var5) {
      if (var2 != null && var2.selectedElementId != null) {
         double var7 = this.toRenderSize(var2, var3);
         double var9 = this.toRenderSize(var2, var5);
         if (!(Math.abs(var7) < 1.0E-4) || !(Math.abs(var9) < 1.0E-4)) {
            boolean var11 = false;
            var11 |= this.moveOverlayHudBy(var1, "editor_top", var7, var9);
            var11 |= this.moveOverlayHudBy(var1, "editor_bottom", var7, var9);
            var11 |= this.moveOverlayHudBy(var1, "editor_left", var7, var9);
            var11 |= this.moveOverlayHudBy(var1, "editor_right", var7, var9);
            if (this.isEffectiveScaleTool(var2)) {
               var11 |= this.moveOverlayHudBy(var1, "editor_handle_tl", var7, var9);
               var11 |= this.moveOverlayHudBy(var1, "editor_handle_n", var7, var9);
               var11 |= this.moveOverlayHudBy(var1, "editor_handle_tr", var7, var9);
               var11 |= this.moveOverlayHudBy(var1, "editor_handle_w", var7, var9);
               var11 |= this.moveOverlayHudBy(var1, "editor_handle_e", var7, var9);
               var11 |= this.moveOverlayHudBy(var1, "editor_handle_bl", var7, var9);
               var11 |= this.moveOverlayHudBy(var1, "editor_handle_s", var7, var9);
               var11 |= this.moveOverlayHudBy(var1, "editor_handle_br", var7, var9);
               var11 |= this.moveOverlayHudBy(var1, "editor_handle_move_tl", var7, var9);
               var11 |= this.moveOverlayHudBy(var1, "editor_handle_move_tr", var7, var9);
               var11 |= this.moveOverlayHudBy(var1, "editor_handle_move_bl", var7, var9);
               var11 |= this.moveOverlayHudBy(var1, "editor_handle_move_br", var7, var9);

               for (Vector var13 : var2.handleCenters.values()) {
                  if (var13 != null) {
                     var13.setX(var13.getX() + var7);
                     var13.setY(var13.getY() + var9);
                  }
               }
            }

            if (!var11) {
               this.renderSelectionOverlay(var1, var2);
            }
         }
      }
   }

   protected boolean moveOverlayHudBy(Player var1, String var2, double var3, double var5) {
      Entity var7 = this.hudService.getHud(var1, var2);
      if (var7 == null) {
         return false;
      } else {
         Vector var8 = this.hudService.getHudLocation(var7);
         if (var8 == null) {
            return false;
         } else {
            EditorSession var9 = this.editorSessions.get(var1.getUniqueId());
            int var10 = this.resolveEditorHudTransitionTicks(var9);
            this.hudService.moveHud(var7, var8.clone().add(new Vector(var3, var5, 0.0)), var10, var10);
            return true;
         }
      }
   }

   @Override
   protected boolean sameRect(EditorRect var1, EditorRect var2) {
      return var1 != null && var2 != null
         ? Math.abs(var1.x - var2.x) < 1.0E-4
            && Math.abs(var1.y - var2.y) < 1.0E-4
            && Math.abs(var1.width - var2.width) < 1.0E-4
            && Math.abs(var1.height - var2.height) < 1.0E-4
         : var1 == null && var2 == null;
   }

   protected void updatePreviewPan(Player var1, EditorSession var2, double var3, double var5) {
      if (var2 != null && var2.previewViewport != null) {
         double var7 = var2.previewPanStartX + (var3 - var2.previewPanStartCursorX);
         double var9 = var2.previewPanStartY + (var5 - var2.previewPanStartCursorY);
         double[] var11 = this.clampPreviewPanToVisibleRange(var2, var7, var9);
         var7 = this.softClampToBounds(var7, var11[0], 0.35);
         var9 = this.softClampToBounds(var9, var11[1], 0.35);
         if (!(Math.abs(var7 - var2.previewViewport.panX) < 1.0E-4) || !(Math.abs(var9 - var2.previewViewport.panY) < 1.0E-4)) {
            var2.previewViewport.panX = var7;
            var2.previewViewport.panY = var9;
            this.refreshPreviewProjection(var1, var2);
            this.updateCursorPositionReadout(var1, var2, var3, var5);
         }
      } else {
         this.stopCursorToolDrag(var2);
      }
   }

   protected void animatePreviewPanBackToBounds(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.previewViewport != null) {
         double var7 = var2.previewViewport.panX;
         double var9 = var2.previewViewport.panY;
         double[] var11 = this.clampPreviewPanToVisibleRange(var2, var7, var9);
         double var12 = var11[0];
         double var14 = var11[1];
         if (!(Math.abs(var12 - var7) < 1.0E-4) || !(Math.abs(var14 - var9) < 1.0E-4)) {
            int[] var16 = new int[]{0};
            byte var17 = 5;
            PlatformCompat.runEntityTimer(this.plugin, var1, 1L, 1L, var16x -> {
               if (var1.isOnline() && !var2.previewPanActive) {
                  var16[0]++;
                  double var17x = Math.min(1.0, (double)var16[0] / 5.0);
                  double var19 = 1.0 - Math.pow(1.0 - var17x, 2.0);
                  var2.previewViewport.panX = var7 + (var12 - var7) * var19;
                  var2.previewViewport.panY = var9 + (var14 - var9) * var19;
                  this.refreshPreviewProjection(var1, var2);
                  this.updateCursorPositionReadout(var1, var2, var3, var5);
                  if (var16[0] >= 5) {
                     var16x.cancel();
                  }
               } else {
                  var16x.cancel();
               }
            });
         }
      }
   }

   protected void updateCursorPositionReadout(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.editMode) {
         Entity var7 = this.hudService.getHud(var1, "editor_shell_cursor_position");
         if (var7 != null) {
            double var8 = this.toLogicalCursorX(var2, var3);
            double var10 = this.toLogicalCursorY(var2, var5);
            double var12 = var2.previewViewport == null ? 1920.0 : var2.previewViewport.pageWidth;
            double var14 = var2.previewViewport == null ? 1080.0 : var2.previewViewport.pageHeight;
            boolean var16 = var8 < 0.0 || var8 > var12 || var10 < 0.0 || var10 > var14;
            String var17 = var16 ? "<#bdbdbd>" : "<#ffffff>";
            String var18 = var17 + Math.round(var8) + ", " + Math.round(var10) + "px";
            ConfigurationSection var19 = this.findShellBlockSection(var2, "cursor_position");
            String var20 = this.applyPreferredFont(var18, var19, false);
            if (!var20.equals(this.hudService.getHudText(var7, null))) {
               this.hudService.setHudText(var7, var20, null, false);
            }
         }
      }
   }

   protected void updatePageInfoReadout(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode) {
         this.updatePreviewSizeToastAnchor(var1, var2);
         this.updatePreviewUiAddonsAnchor(var1, var2);
         this.updatePreviewEmptyInfoOverlay(var1, var2);
         Entity var3 = this.hudService.getHud(var1, "editor_shell_page_info");
         if (var3 != null) {
            int var4 = var2.previewViewport == null ? 100 : (int)Math.round(Math.max(1.0, var2.previewViewport.zoom * 100.0));
            int[] var5 = new int[]{0, 0};
            this.countLayerAndGroupStats(var2.rawBlocks, var5);
            ConfigurationSection var6 = this.findShellBlockSection(var2, "page_info");
            String var7 = this.normalizeHexColor(var6 == null ? null : var6.getString("color"));
            if (var7.isBlank()) {
               var7 = "969696";
            }

            String var8 = "<#" + var7 + ">" + var4 + "%  |  " + var5[0] + " layers, " + var5[1] + " groups";
            String var9 = this.applyPreferredFont(var8, var6, false);
            if (!var9.equals(this.hudService.getHudText(var3, null))) {
               this.hudService.setHudText(var3, var9, null, false);
            }

            Entity var10 = this.hudService.getHud(var1, "editor_shell_page_info_text");
            if (var10 != null) {
               int var11 = Math.max(1, (int)Math.round(var2.previewViewport == null ? 1920.0 : var2.previewViewport.pageWidth));
               int var12 = Math.max(1, (int)Math.round(var2.previewViewport == null ? 1080.0 : var2.previewViewport.pageHeight));
               String var13 = var11 + "x" + var12;
               ConfigurationSection var14 = this.findShellBlockSection(var2, "page_info_text");
               String var15 = this.normalizeHexColor(var14 == null ? null : var14.getString("color"));
               String var16 = var15.isBlank() ? var13 : "<#" + var15 + ">" + var13;
               String var17 = this.applyPreferredFont(var16, var14, false);
               if (!var17.equals(this.hudService.getHudText(var10, null))) {
                  this.hudService.setHudText(var10, var17, null, false);
               }
            }
         }
      }
   }

   protected void updatePreviewSizeToastAnchor(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode && (var2.previewSizeToastVisible || var2.previewSizeToastPendingReveal)) {
         PreviewViewport var3 = var2.previewViewport;
         if (var3 != null) {
            ConfigurationSection var4 = this.findShellBlockSection(var2, "preview_size");
            EditorRect var5 = this.findShellBlockRect(var2, "preview_size");
            if (var4 != null && var5 != null) {
               double var6 = var3.previewX + (var3.previewWidth - var5.width) / 2.0;
               double var8 = var3.previewY + var3.previewHeight - var5.height - 12.0;
               double var10 = var6 - var5.x;
               double var12 = var8 - var5.y;
               if (!(Math.abs(var10) < 0.01) || !(Math.abs(var12) < 0.01)) {
                  String var14 = this.firstNonBlank(new String[]{var4.getString("__editor_target_path")});
                  if (var14.isBlank()) {
                     this.moveShellElement(var1, var2, "preview_size", var6, var8, var5.width, var5.height);
                  } else {
                     HashMap var15 = new HashMap();
                     int var16 = 0;

                     for (Map var18 : var2.shellBlocks) {
                        var16++;
                        ConfigurationSection var19 = this.mapToSection(var18);
                        if (var19 != null) {
                           String var20 = this.firstNonBlank(new String[]{var19.getString("__editor_target_path")});
                           if (this.belongsToSidebarPanel(var14, var20)) {
                              String var21 = this.resolveElementId(var19, var16, var15);
                              if (!var21.isBlank()) {
                                 EditorRect var22 = this.findShellBlockRect(var2, var21);
                                 if (var22 != null) {
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
   }

   protected void updatePreviewUiAddonsAnchor(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         PreviewViewport var3 = var2.previewViewport;
         if (var3 != null) {
            ConfigurationSection var4 = this.findShellBlockSection(var2, "preview_ui_addons");
            if (var4 != null) {
               this.ensureShellBlockCache(var2);
               double var5 = var2.previewDefaultZoom;
               if (!Double.isFinite(var5) || var5 <= 1.0E-4) {
                  var5 = 1.0;
               }

               double var7 = var3.baseScale * var5;
               if (Double.isFinite(var7) && !(var7 <= 1.0E-4)) {
                  double var9 = this.getPreviewScale(var2);
                  if (Double.isFinite(var9) && !(var9 <= 1.0E-4)) {
                     double var11 = var9 / var7;
                     if (Double.isFinite(var11) && !(var11 <= 0.0)) {
                        double var13 = var3.pageWidth * var7;
                        double var15 = var3.pageHeight * var7;
                        double var17 = var3.previewX + (var3.previewWidth - var13) / 2.0;
                        double var19 = var3.previewY + (var3.previewHeight - var15) / 2.0;
                        double var21 = this.toRenderX(var2, 0.0);
                        double var23 = this.toRenderY(var2, 0.0);
                        String var25 = this.firstNonBlank(new String[]{var4.getString("__editor_target_path")});
                        if (var25.isBlank()) {
                           EditorRect var42 = var2.shellStaticRectCache.get("preview_ui_addons");
                           if (var42 == null) {
                              var42 = this.findShellBlockRect(var2, "preview_ui_addons");
                           }

                           if (var42 != null) {
                              double var43 = var21 + (var42.x - var17) * var11;
                              double var44 = var23 + (var42.y - var19) * var11;
                              double var45 = Math.max(1.0, var42.width * var11);
                              double var46 = Math.max(1.0, var42.height * var11);
                              this.moveShellElement(var1, var2, "preview_ui_addons", var43, var44, var45, var46);
                           }
                        } else {
                           HashMap var26 = new HashMap();
                           int var27 = 0;

                           for (Map var29 : var2.shellBlocks) {
                              var27++;
                              ConfigurationSection var30 = this.mapToSection(var29);
                              if (var30 != null) {
                                 String var31 = this.firstNonBlank(new String[]{var30.getString("__editor_target_path")});
                                 if (this.belongsToSidebarPanel(var25, var31)) {
                                    String var32 = this.resolveElementId(var30, var27, var26);
                                    if (!var32.isBlank()) {
                                       EditorRect var33 = var2.shellStaticRectCache.get(var32);
                                       if (var33 != null) {
                                          double var34 = var21 + (var33.x - var17) * var11;
                                          double var36 = var23 + (var33.y - var19) * var11;
                                          double var38 = Math.max(1.0, var33.width * var11);
                                          double var40 = Math.max(1.0, var33.height * var11);
                                          this.moveShellElement(var1, var2, var32, var34, var36, var38, var40);
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

   protected void updatePreviewEmptyInfoOverlay(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         PreviewViewport var3 = var2.previewViewport;
         if (var3 != null) {
            ConfigurationSection var4 = this.findShellBlockSection(var2, "addelements_info");
            if (var4 != null) {
               boolean var5 = this.shouldShowPreviewEmptyInfo(var2);
               this.setShellOpacity(var1, "addelements_info", var5 ? 255 : 5);
               if (var5) {
                  this.ensureShellBlockCache(var2);
                  double var6 = var2.previewDefaultZoom;
                  if (!Double.isFinite(var6) || var6 <= 1.0E-4) {
                     var6 = 1.0;
                  }

                  double var8 = var3.baseScale * var6;
                  if (Double.isFinite(var8) && !(var8 <= 1.0E-4)) {
                     double var10 = this.getPreviewScale(var2);
                     if (Double.isFinite(var10) && !(var10 <= 1.0E-4)) {
                        double var12 = var10 / var8;
                        if (Double.isFinite(var12) && !(var12 <= 0.0)) {
                           double var14 = var3.pageWidth * var8;
                           double var16 = var3.pageHeight * var8;
                           double var18 = var3.previewX + (var3.previewWidth - var14) / 2.0;
                           double var20 = var3.previewY + (var3.previewHeight - var16) / 2.0;
                           double var22 = this.toRenderX(var2, 0.0);
                           double var24 = this.toRenderY(var2, 0.0);
                           double var26 = var3.pageWidth * var10;
                           double var28 = var3.pageHeight * var10;
                           EditorRect var30 = var2.shellStaticRectCache.get("addelements_info");
                           if (var30 == null) {
                              var30 = this.findShellBlockRect(var2, "addelements_info");
                           }

                           if (var30 != null) {
                              double var31 = var22 + (var30.x - var18) * var12;
                              double var33 = var24 + (var30.y - var20) * var12;
                              double var35 = Math.max(1.0, var30.width * var12);
                              double var37 = Math.max(1.0, var30.height * var12);
                              var31 += var26 * -0.0264;
                              var33 += var28 * 0.0433;
                              this.moveShellElement(var1, var2, "addelements_info", var31, var33, var35, var37);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected void countLayerAndGroupStats(List<Map<String, Object>> var1, int[] var2) {
      if (var1 != null && var2 != null && var2.length >= 2) {
         for (Map var4 : var1) {
            if (var4 != null) {
               if (var4.get("children") instanceof List var6 && !var6.isEmpty()) {
                  var2[1]++;
                  ArrayList var7 = new ArrayList();

                  for (Object var9 : var6) {
                     if (var9 instanceof Map var10) {
                        var7.add(var10);
                     }
                  }

                  this.countLayerAndGroupStats(var7, var2);
                  continue;
               }

               var2[0]++;
            }
         }
      }
   }

   protected boolean isLeftHeld(EditorSession var1) {
      if (var1 == null) {
         return false;
      } else {
         long var2 = (long)Bukkit.getCurrentTick() - var1.lastLeftClickTick;
         if (var2 <= 2L) {
            return true;
         } else {
            boolean var4 = var1.animationTimelineSliderDragActive || var1.animationTimelineKeyframeDragActive;
            return var4 && var2 <= 2L;
         }
      }
   }

   protected void toggleScalingHandle(Player var1, EditorSession var2, String var3, long var4) {
      if (this.isEffectiveScaleTool(var2)) {
         if ("move".equals(var3)) {
            var2.activeHandle = "move";
            var2.resizeAnchorValid = false;
            var2.handlesCollapsed = false;
            this.startMoveDrag(var2, var2.cursorX + var2.hitboxOffsetX, var2.cursorY + var2.hitboxOffsetY);
            var2.lastScaleToggleTick = var4;
            this.renderSelectionOverlay(var1, var2);
         } else if (var4 - var2.lastScaleToggleTick >= 3L) {
            if (var3.equals(var2.activeHandle)) {
               var2.activeHandle = null;
               var2.resizeAnchorValid = false;
               var2.handlesCollapsed = false;
               this.stopCursorToolDrag(var2);

               for (String var7 : this.getSelectedTargetIds(var2)) {
                  this.restoreElementLayer(var1, var2, var7);
               }
            } else {
               var2.activeHandle = var3;
               if ("move".equals(var3)) {
                  var2.handlesCollapsed = false;
                  var2.resizeAnchorValid = false;
                  this.startMoveDrag(var2, var2.cursorX + var2.hitboxOffsetX, var2.cursorY + var2.hitboxOffsetY);
               } else {
                  this.captureResizeAnchor(var2);
                  var2.handlesCollapsed = true;
                  this.stopCursorToolDrag(var2);

                  for (String var9 : this.getSelectedTargetIds(var2)) {
                     this.bringElementToFront(var1, var2, var9);
                  }
               }
            }

            var2.lastScaleToggleTick = var4;
            this.renderSelectionOverlay(var1, var2);
         }
      }
   }

   protected void captureResizeAnchor(EditorSession var1) {
      if (var1 != null) {
         var1.resizeStartBounds.clear();
         var1.resizeInheritedRootStartBoundsByPath.clear();
         var1.resizeInheritedChildrenSnapshotByPath.clear();
         EditorRect var2 = null;

         for (String var4 : this.resolveResizeScaleTargetIds(var1)) {
            boolean var6 = var1.activeTool == EditorTool.ANIMATION && this.isAnimationTimelineScaleKeyframeSelected(var1);
            String var7 = this.firstNonBlank(new String[]{this.resolveInheritedTargetRootPath(var1, var4)});
            boolean var8 = !var7.isBlank() && this.isImageGroupRootPath(var1, var7);
            EditorRect var5;
            if (var6 && !var8) {
               var5 = this.resolveAnimationTimelineLiveOutlineBoundsForTarget(var1, var4);
               if (var5 == null) {
                  var5 = this.resolveAnimationTimelineImageGroupLogicalBounds(var1, var4);
               }

               if (var5 == null) {
                  var5 = this.getTargetBounds(var1, var4);
               }

               if (var5 == null) {
                  var5 = this.resolveSidebarEditableBounds(var1, var4);
               }
            } else {
               var5 = this.resolveSidebarEditableBounds(var1, var4);
            }

            if (var5 != null) {
               var1.resizeStartBounds.put(var4, var5);
               HoverElement var9 = this.findFirstByTargetId(var1, var4);
               String var10 = this.firstNonBlank(new String[]{this.resolveInheritedTargetRootPath(var1, var4)});
               String var11 = this.firstNonBlank(new String[]{var10, var9 == null ? null : var9.targetPath});
               if (!var11.isBlank()) {
                  Map var12 = this.resolveRawMapAtPath(var1.rawBlocks, var11);
                  if (this.shouldInheritTargetToChildren(var12)) {
                     boolean var13 = this.isImageGroupRoot(var12);
                     double var14 = var13 ? 1.0E-4 : 1.0;
                     double var16;
                     double var18;
                     if (var13) {
                        var16 = Math.max(var14, var5.width);
                        var18 = Math.max(var14, var5.height);
                     } else {
                        double var20 = Math.abs(this.readMapPathDouble(var12, "size.width", this.readMapPathDouble(var12, "width", var5.width)));
                        double var22 = Math.abs(this.readMapPathDouble(var12, "size.height", this.readMapPathDouble(var12, "height", var5.height)));
                        var16 = Double.isFinite(var20) ? Math.max(var14, var20) : Math.max(var14, var5.width);
                        var18 = Double.isFinite(var22) ? Math.max(var14, var22) : Math.max(var14, var5.height);
                     }

                     var1.resizeInheritedRootStartBoundsByPath.put(var11, new EditorRect(var5.x, var5.y, var16, var18));
                     var1.resizeInheritedChildrenSnapshotByPath.put(var11, this.deepCopyValue(var12.get("children")));
                  }
               }

               var2 = this.mergeBounds(var2, var5);
            }
         }

         if (var2 == null) {
            var2 = this.getSelectedBounds(var1);
         }

         if (var2 == null) {
            var1.resizeAnchorValid = false;
         } else {
            var1.resizeSelectionStartX = var2.x;
            var1.resizeSelectionStartY = var2.y;
            var1.resizeSelectionStartW = var2.width;
            var1.resizeSelectionStartH = var2.height;
            var1.resizeStartCursorX = this.toLogicalCursorX(var1, var1.cursorX + var1.hitboxOffsetX);
            var1.resizeStartCursorY = this.toLogicalCursorY(var1, var1.cursorY + var1.hitboxOffsetY);
            var1.resizeLastCursorX = var1.resizeStartCursorX;
            var1.resizeLastCursorY = var1.resizeStartCursorY;
            var1.resizeStepReady = true;
            var1.resizeAnchorValid = true;
         }
      }
   }

   protected LinkedHashSet<String> resolveResizeScaleTargetIds(EditorSession var1) {
      LinkedHashSet<String> var2 = new LinkedHashSet<>();
      if (var1 == null) {
         return var2;
      } else {
         var2.addAll(this.getSelectedTargetIds(var1));
         if (!var2.isEmpty() && this.isEffectiveScaleTool(var1)) {
            for (String var5 : new LinkedHashSet<String>(var2)) {
               if (var5 != null && !var5.isBlank()) {
                  Map var6 = this.resolveRawTargetByTargetId(var1, var5);
                  if (!this.shouldInheritTargetToChildren(var6)) {
                     var2.addAll(this.resolveDescendantLayerTargetIds(var1, var5));
                  }
               }
            }

            return var2;
         } else {
            return var2;
         }
      }
   }

   @Override
   protected void updateHoveredHighlight(Player var1, EditorSession var2, String var3, String var4) {
      if (var3 != null) {
         this.restoreElementText(var1, var2, var3);
         this.restoreHoverEffectForTarget(var1, var2, var3);
      }

      if (var4 != null && !this.isSelectedTarget(var2, var4)) {
         String var5 = this.firstNonBlank(new String[]{this.readTargetHoverColor(var2, var4)});
         boolean var6 = var5.equalsIgnoreCase("none") || var5.equalsIgnoreCase("None");
         String var7 = this.normalizeHexColor(var5);

         for (HoverElement var9 : this.findTargetElements(var2, var4)) {
            boolean var10 = var9.hoverText != null && !var9.hoverText.isBlank();
            String var11 = var10 ? var9.hoverText : var9.text;
            if (!var6) {
               if (var7.isBlank()) {
                  this.setElementText(var1, var9, this.brightenOrDarken(var11), true);
               } else {
                  this.setElementText(var1, var9, this.applyHoverColorToText(var11, var7), true);
               }
            } else if (var10) {
               this.setElementText(var1, var9, var9.hoverText, true);
            }
         }

         this.applyHoverEffectForTarget(var1, var2, var4);
      }
   }

   protected void applyHoverEffectForTarget(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && !var2.editMode) {
         AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig var4 = this.resolveHoverEffectRuntimeConfig(this.readTargetHoverEffect(var2, var3));
         this.applyHoverEffectForTargetWithConfig(var1, var2, var3, var4);
      }
   }

   private List<String> collectExistingRoundedPartIds(Player var1, String var2) {
      ArrayList var3 = new ArrayList();

      for (Object var7_raw : ROUNDED_PART_SUFFIXES) {
         String var7 = var7_raw != null ? var7_raw.toString() : null;
         if (this.hudService.getHud(var1, var2 + var7) != null) {
            var3.add(var2 + var7);
         }
      }

      String var9 = var2 + "_outline";

      for (Object var8_raw : ROUNDED_PART_SUFFIXES) {
         String var8 = var8_raw != null ? var8_raw.toString() : null;
         if (this.hudService.getHud(var1, var9 + var8) != null) {
            var3.add(var9 + var8);
         }
      }

      return var3;
   }

   protected void applyHoverEffectForTargetWithConfig(
      Player var1, EditorSession var2, String var3, AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig var4
   ) {
      if (var4 != null) {
         List var5 = this.findTargetElements(var2, var3);
         LinkedHashMap var6 = new LinkedHashMap();
         double var7 = 0.0;
         double var9 = 0.0;
         int var11 = 0;

         for (Object var13_raw : var5) {
            HoverElement var13 = (HoverElement)var13_raw;
            String var14 = this.firstNonBlank(new String[]{var13 == null ? null : var13.id});
            if (!var14.isBlank()) {
               Entity var15 = this.hudService.getHud(var1, var14);
               if (var15 != null) {
                  this.hoverEffectRestoringIds.remove(var14);
                  GuiServiceEditorInteractionOverlaySupport.HoverEffectState var27 = this.hoverEffectBaseStates
                     .computeIfAbsent(var14, var3x -> this.resolveHoverEffectBaseState(var13, var15));
                  var6.put(var14, var27);
                  if (var13 != null && !"hitbox".equals(var13.type) && var27.location() != null) {
                     var7 += var27.location().getX();
                     var9 += var27.location().getY();
                     var11++;
                  }
               } else {
                  List var16 = this.collectExistingRoundedPartIds(var1, var14);
                  if (!var16.isEmpty()) {
                     for (Object var18_raw : var16) {
                        String var18 = var18_raw != null ? var18_raw.toString() : null;
                        Entity var19 = this.hudService.getHud(var1, var18);
                        if (var19 != null) {
                           this.hoverEffectRestoringIds.remove(var18);
                           GuiServiceEditorInteractionOverlaySupport.HoverEffectState var20 = this.hoverEffectBaseStates
                              .computeIfAbsent(var18, var3x -> this.resolveHoverEffectBaseState(var13, var19));
                           var6.put(var18, var20);
                           if (var13 != null && !"hitbox".equals(var13.type) && var20.location() != null) {
                              var7 += var20.location().getX();
                              var9 += var20.location().getY();
                              var11++;
                           }
                        }
                     }
                  }

                  this.hoverEffectBaseStates.remove(var14);
                  this.hoverEffectAppliedStates.remove(var14);
                  this.hoverEffectAnimationTokens.remove(var14);
                  this.hoverEffectRestoringIds.remove(var14);
               }
            }
         }

         boolean var24 = var11 > 1 && (Math.abs(var4.scaleXMultiplier() - 1.0) > 1.0E-4 || Math.abs(var4.scaleYMultiplier() - 1.0) > 1.0E-4);
         double var25 = var24 ? var7 / (double)var11 : 0.0;
         double var26 = var24 ? var9 / (double)var11 : 0.0;

         for (Map.Entry<?, ?> var29 : ((Map<?, ?>)var6).entrySet()) {
            String var30 = (String)var29.getKey();
            GuiServiceEditorInteractionOverlaySupport.HoverEffectState var31 = (GuiServiceEditorInteractionOverlaySupport.HoverEffectState)var29.getValue();
            Entity var21 = this.hudService.getHud(var1, var30);
            if (var21 != null) {
               GuiServiceEditorInteractionOverlaySupport.HoverEffectState var22 = this.resolveCurrentHoverEffectState(var1, var30, var31);
               GuiServiceEditorInteractionOverlaySupport.HoverEffectState var23 = var24
                  ? this.buildHoverEffectTargetStateWithPivot(var31, var4, var25, var26)
                  : this.buildHoverEffectTargetState(var31, var4);
               this.animateHoverHudEffect(var1, var2, var30, var22, var23, var4.durationTicks(), var4.interpolationMode());
            }
         }
      }
   }

   protected void applyHoverEffectForTargetWithStartPosition(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && !var2.editMode) {
         AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig var4 = this.resolveHoverEffectRuntimeConfig(this.readTargetHoverEffect(var2, var3));
         if (var4 != null) {
            boolean var5 = Math.abs(var4.startOffsetX()) > 0.001
               || Math.abs(var4.startOffsetY()) > 0.001
               || Math.abs(var4.startScaleXMultiplier() - 1.0) > 0.001
               || Math.abs(var4.startScaleYMultiplier() - 1.0) > 0.001;
            if (var5) {
               AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig var6 = this.buildStartPositionConfig(var4);
               this.applyHoverEffectForTargetWithConfig(var1, var2, var3, var6);
            }
         }
      }
   }

   protected void applyOpenAnimationForTarget(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && !var2.editMode) {
         AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig var4 = this.resolveHoverEffectRuntimeConfig(this.readTargetHoverEffect(var2, var3));
         if (var4 != null) {
            List var5 = this.findTargetElements(var2, var3);

            for (Object var7_raw : var5) {
               HoverElement var7 = (HoverElement)var7_raw;
               String var8 = this.firstNonBlank(new String[]{var7 == null ? null : var7.id});
               if (!var8.isBlank()) {
                  this.hoverEffectBaseStates.remove(var8);

                  for (String[] var12 : new String[][]{{var8}, {var8 + "_outline"}}) {
                     for (Object var16_raw : ROUNDED_PART_SUFFIXES) {
                        String var16 = var16_raw != null ? var16_raw.toString() : null;
                        this.hoverEffectBaseStates.remove(var12[0] + var16);
                     }
                  }
               }
            }

            this.applyHoverEffectForTargetWithConfig(var1, var2, var3, var4);

            for (Object var21_raw : var5) {
               HoverElement var21 = (HoverElement)var21_raw;
               String var22 = this.firstNonBlank(new String[]{var21 == null ? null : var21.id});
               if (!var22.isBlank()) {
                  GuiServiceEditorInteractionOverlaySupport.HoverEffectState var23 = this.hoverEffectBaseStates.get(var22);
                  if (var23 != null) {
                     this.hoverEffectBaseStates.put(var22, this.buildHoverEffectTargetState(var23, var4));
                  }

                  for (String[] var27 : new String[][]{{var22}, {var22 + "_outline"}}) {
                     for (Object var17_raw : ROUNDED_PART_SUFFIXES) {
                        String var17 = var17_raw != null ? var17_raw.toString() : null;
                        String var18 = var27[0] + var17;
                        GuiServiceEditorInteractionOverlaySupport.HoverEffectState var19 = this.hoverEffectBaseStates.get(var18);
                        if (var19 != null) {
                           this.hoverEffectBaseStates.put(var18, this.buildHoverEffectTargetState(var19, var4));
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected void restoreHoverEffectForTarget(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig var4 = this.resolveHoverEffectRuntimeConfig(this.readTargetHoverEffect(var2, var3));
         int var5 = var4 == null ? 1 : var4.durationTicks();
         String var6 = var4 == null ? "linear" : var4.interpolationMode();

         for (HoverElement var8 : this.findTargetElements(var2, var3)) {
            String var9 = this.firstNonBlank(new String[]{var8 == null ? null : var8.id});
            if (!var9.isBlank()) {
               GuiServiceEditorInteractionOverlaySupport.HoverEffectState var10 = this.hoverEffectBaseStates.get(var9);
               if (var10 == null) {
                  for (String var13 : this.collectExistingRoundedPartIds(var1, var9)) {
                     GuiServiceEditorInteractionOverlaySupport.HoverEffectState var14 = this.hoverEffectBaseStates.get(var13);
                     if (var14 != null) {
                        Entity var15 = this.hudService.getHud(var1, var13);
                        if (var15 == null) {
                           this.hoverEffectBaseStates.remove(var13);
                        } else {
                           this.hoverEffectRestoringIds.add(var13);
                           GuiServiceEditorInteractionOverlaySupport.HoverEffectState var16 = this.resolveCurrentHoverEffectState(var1, var13, var14);
                           this.animateHoverHudEffect(var1, var2, var13, var16, var14, var5, var6);
                        }
                     }
                  }

                  this.hoverEffectAppliedStates.remove(var9);
                  this.hoverEffectAnimationTokens.remove(var9);
               } else {
                  Entity var11 = this.hudService.getHud(var1, var9);
                  if (var11 == null) {
                     this.hoverEffectBaseStates.remove(var9);
                     this.hoverEffectAppliedStates.remove(var9);
                     this.hoverEffectAnimationTokens.remove(var9);
                     this.hoverEffectRestoringIds.remove(var9);
                  } else {
                     this.hoverEffectRestoringIds.add(var9);
                     GuiServiceEditorInteractionOverlaySupport.HoverEffectState var12 = this.resolveCurrentHoverEffectState(var1, var9, var10);
                     this.animateHoverHudEffect(var1, var2, var9, var12, var10, var5, var6);
                  }
               }
            }
         }
      }
   }

   protected GuiServiceEditorInteractionOverlaySupport.HoverEffectState resolveHoverEffectBaseState(HoverElement var1, Entity var2) {
      Vector var3 = this.hudService.getHudLocation(var2);
      if (var3 == null && var1 != null && var1.baseLocation != null) {
         var3 = var1.baseLocation.clone();
      }

      if (var3 == null) {
         var3 = new Vector(0.0, 0.0, 0.0);
      }

      Vector var4 = this.hudService.getHudScale(var2);
      if (var4 == null && var1 != null && var1.baseScale != null) {
         var4 = var1.baseScale.clone();
      }

      if (var4 == null) {
         var4 = new Vector(1.0, 1.0, 1.0);
      }

      boolean var5 = var4.getX() < 0.0;
      boolean var6 = var4.getY() < 0.0;
      if (var1 != null && var1.baseScale != null) {
         var5 = var1.baseScale.getX() < 0.0;
         var6 = var1.baseScale.getY() < 0.0;
      }

      int var7 = this.clampHoverEffectOpacity(this.hudService.getHudOpacity(var2));
      if (var7 <= 0 && var1 != null && var1.opacity > 0) {
         var7 = this.clampHoverEffectOpacity(var1.opacity);
      }

      double var8 = var1 != null && Double.isFinite(var1.rotationDeg) ? var1.rotationDeg : 0.0;
      return new GuiServiceEditorInteractionOverlaySupport.HoverEffectState(var3.clone(), var4.clone(), var7, var8, var5, var6);
   }

   protected GuiServiceEditorInteractionOverlaySupport.HoverEffectState buildHoverEffectTargetState(
      GuiServiceEditorInteractionOverlaySupport.HoverEffectState var1, AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig var2
   ) {
      if (var1 != null && var2 != null) {
         Vector var3 = var1.location() == null ? new Vector(0.0, 0.0, 0.0) : var1.location();
         Vector var4 = var1.scale() == null ? new Vector(1.0, 1.0, 1.0) : var1.scale();
         Vector var5 = var3.clone().add(new Vector(var2.offsetX(), var2.offsetY(), 0.0));
         Vector var6 = new Vector(var4.getX() * var2.scaleXMultiplier(), var4.getY() * var2.scaleYMultiplier(), var4.getZ());
         int var7 = var2.opacityAbsolute() ? var2.opacityAbsoluteValue() : var1.opacity();
         var7 += var2.opacityDelta();
         var7 += (int)Math.round((double)var1.opacity() * (var2.opacityPercentDelta() / 100.0));
         var7 = this.clampHoverEffectOpacity(var7);
         double var8 = var1.rotationDeg() + var2.rotationDeltaDeg();
         return new GuiServiceEditorInteractionOverlaySupport.HoverEffectState(var5, var6, var7, var8, var1.mirrorX(), var1.mirrorY());
      } else {
         return var1;
      }
   }

   protected GuiServiceEditorInteractionOverlaySupport.HoverEffectState buildHoverEffectTargetStateWithPivot(
      GuiServiceEditorInteractionOverlaySupport.HoverEffectState var1,
      AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig var2,
      double var3,
      double var5
   ) {
      if (var1 != null && var2 != null) {
         Vector var7 = var1.location() == null ? new Vector(0.0, 0.0, 0.0) : var1.location();
         Vector var8 = var1.scale() == null ? new Vector(1.0, 1.0, 1.0) : var1.scale();
         double var9 = (var7.getX() - var3) * (var2.scaleXMultiplier() - 1.0);
         double var11 = (var7.getY() - var5) * (var2.scaleYMultiplier() - 1.0);
         Vector var13 = var7.clone().add(new Vector(var2.offsetX() + var9, var2.offsetY() + var11, 0.0));
         Vector var14 = new Vector(var8.getX() * var2.scaleXMultiplier(), var8.getY() * var2.scaleYMultiplier(), var8.getZ());
         int var15 = var2.opacityAbsolute() ? var2.opacityAbsoluteValue() : var1.opacity();
         var15 += var2.opacityDelta();
         var15 += (int)Math.round((double)var1.opacity() * (var2.opacityPercentDelta() / 100.0));
         var15 = this.clampHoverEffectOpacity(var15);
         double var16 = var1.rotationDeg() + var2.rotationDeltaDeg();
         return new GuiServiceEditorInteractionOverlaySupport.HoverEffectState(var13, var14, var15, var16, var1.mirrorX(), var1.mirrorY());
      } else {
         return var1;
      }
   }

   protected GuiServiceEditorInteractionOverlaySupport.HoverEffectState resolveCurrentHoverEffectState(
      Player var1, String var2, GuiServiceEditorInteractionOverlaySupport.HoverEffectState var3
   ) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Entity var4 = this.hudService.getHud(var1, var2);
         if (var4 == null) {
            return var3;
         } else {
            Vector var5 = this.hudService.getHudLocation(var4);
            Vector var6 = this.hudService.getHudScale(var4);
            int var7 = this.clampHoverEffectOpacity(this.hudService.getHudOpacity(var4));
            GuiServiceEditorInteractionOverlaySupport.HoverEffectState var8 = this.hoverEffectAppliedStates.get(var2);
            Vector var9 = var5 != null ? var5 : (var8 != null && var8.location() != null ? var8.location() : (var3 == null ? null : var3.location()));
            Vector var10 = var6 != null ? var6 : (var8 != null && var8.scale() != null ? var8.scale() : (var3 == null ? null : var3.scale()));
            if (var9 == null) {
               var9 = new Vector(0.0, 0.0, 0.0);
            }

            if (var10 == null) {
               var10 = new Vector(1.0, 1.0, 1.0);
            }

            double var11 = var8 == null ? (var3 == null ? 0.0 : var3.rotationDeg()) : var8.rotationDeg();
            boolean var13 = var3 != null && var3.mirrorX();
            boolean var14 = var3 != null && var3.mirrorY();
            return new GuiServiceEditorInteractionOverlaySupport.HoverEffectState(var9.clone(), var10.clone(), var7, var11, var13, var14);
         }
      } else {
         return var3;
      }
   }

   protected void capturePreOpenBaseStates(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.elements != null) {
         HashSet var7 = new HashSet();

         for (Object var9_raw : var2.elements) {
            HoverElement var9 = (HoverElement)var9_raw;
            String var10 = this.firstNonBlank(new String[]{var9 == null ? null : var9.id});
            if (!var10.isBlank() && var7.add(var10)) {
               Entity var11 = this.hudService.getHud(var1, var10);
               if (var11 != null) {
                  GuiServiceEditorInteractionOverlaySupport.HoverEffectState var17 = this.resolveHoverEffectBaseState(var9, var11);
                  Vector var18 = var17.location().clone().subtract(new Vector(var3, var5, 0.0));
                  this.hoverEffectBaseStates
                     .put(
                        var10,
                        new GuiServiceEditorInteractionOverlaySupport.HoverEffectState(
                           var18, var17.scale(), var17.opacity(), var17.rotationDeg(), var17.mirrorX(), var17.mirrorY()
                        )
                     );
                  this.hoverEffectRestoringIds.remove(var10);
               } else {
                  this.hoverEffectBaseStates.remove(var10);

                  for (String var13 : this.collectExistingRoundedPartIds(var1, var10)) {
                     Entity var14 = this.hudService.getHud(var1, var13);
                     if (var14 != null) {
                        GuiServiceEditorInteractionOverlaySupport.HoverEffectState var15 = this.resolveHoverEffectBaseState(var9, var14);
                        Vector var16 = var15.location().clone().subtract(new Vector(var3, var5, 0.0));
                        this.hoverEffectBaseStates
                           .put(
                              var13,
                              new GuiServiceEditorInteractionOverlaySupport.HoverEffectState(
                                 var16, var15.scale(), var15.opacity(), var15.rotationDeg(), var15.mirrorX(), var15.mirrorY()
                              )
                           );
                        this.hoverEffectRestoringIds.remove(var13);
                     }
                  }
               }
            }
         }
      }
   }

   protected void animateHoverHudEffect(
      Player var1,
      EditorSession var2,
      String var3,
      GuiServiceEditorInteractionOverlaySupport.HoverEffectState var4,
      GuiServiceEditorInteractionOverlaySupport.HoverEffectState var5,
      int var6,
      String var7
   ) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var4 != null && var5 != null) {
         int var8 = ++this.hoverEffectAnimationTokenCounter;
         this.hoverEffectAnimationTokens.put(var3, var8);
         if (var6 <= 1) {
            Entity var13 = this.hudService.getHud(var1, var3);
            if (var13 != null) {
               this.applyHoverEffectState(var13, var5, 0);
               this.hoverEffectAppliedStates.put(var3, var5.copy());
            }

            if (this.hoverEffectRestoringIds.remove(var3)) {
               this.hoverEffectBaseStates.remove(var3);
            }
         } else {
            int var9 = Math.max(1, var6);
            GuiServiceEditorInteractionOverlaySupport.HoverEffectState var10 = var4.copy();
            GuiServiceEditorInteractionOverlaySupport.HoverEffectState var11 = var5.copy();
            int[] var12 = new int[]{0};
            PlatformCompat.runEntityTimer(this.plugin, var1, 0L, 1L, var10x -> {
               if (!var1.isOnline()) {
                  var10x.cancel();
               } else {
                  EditorSession var11x = this.editorSessions.get(var1.getUniqueId());
                  if (var11x != var2) {
                     var10x.cancel();
                  } else {
                     Integer var12x = this.hoverEffectAnimationTokens.get(var3);
                     if (var12x != null && var12x == var8) {
                        Entity var13x = this.hudService.getHud(var1, var3);
                        if (var13x == null) {
                           this.hoverEffectAnimationTokens.remove(var3);
                           this.hoverEffectAppliedStates.remove(var3);
                           if (this.hoverEffectRestoringIds.remove(var3)) {
                              this.hoverEffectBaseStates.remove(var3);
                           }

                           var10x.cancel();
                        } else {
                           var12[0]++;
                           double var14 = Math.min(1.0, (double)var12[0] / (double)var9);
                           double var16 = this.applyAnimationTimelineInterpolation(var7, var14);
                           GuiServiceEditorInteractionOverlaySupport.HoverEffectState var18 = this.interpolateHoverEffectState(var10, var11, var16);
                           this.applyHoverEffectState(var13x, var18, 1);
                           this.hoverEffectAppliedStates.put(var3, var18);
                           if (var12[0] >= var9) {
                              this.applyHoverEffectState(var13x, var11, 0);
                              this.hoverEffectAppliedStates.put(var3, var11);
                              if (this.hoverEffectRestoringIds.remove(var3)) {
                                 this.hoverEffectBaseStates.remove(var3);
                              }

                              var10x.cancel();
                           }
                        }
                     } else {
                        var10x.cancel();
                     }
                  }
               }
            });
         }
      }
   }

   protected GuiServiceEditorInteractionOverlaySupport.HoverEffectState interpolateHoverEffectState(
      GuiServiceEditorInteractionOverlaySupport.HoverEffectState var1, GuiServiceEditorInteractionOverlaySupport.HoverEffectState var2, double var3
   ) {
      if (var1 != null && var2 != null) {
         double var5 = Math.max(0.0, Math.min(1.0, var3));
         Vector var7 = var1.location() == null ? new Vector(0.0, 0.0, 0.0) : var1.location();
         Vector var8 = var2.location() == null ? new Vector(0.0, 0.0, 0.0) : var2.location();
         Vector var9 = var1.scale() == null ? new Vector(1.0, 1.0, 1.0) : var1.scale();
         Vector var10 = var2.scale() == null ? new Vector(1.0, 1.0, 1.0) : var2.scale();
         Vector var11 = new Vector(
            var7.getX() + (var8.getX() - var7.getX()) * var5,
            var7.getY() + (var8.getY() - var7.getY()) * var5,
            var7.getZ() + (var8.getZ() - var7.getZ()) * var5
         );
         Vector var12 = new Vector(
            var9.getX() + (var10.getX() - var9.getX()) * var5,
            var9.getY() + (var10.getY() - var9.getY()) * var5,
            var9.getZ() + (var10.getZ() - var9.getZ()) * var5
         );
         int var13 = this.clampHoverEffectOpacity((int)Math.round((double)var1.opacity() + (double)(var2.opacity() - var1.opacity()) * var5));
         double var14 = var1.rotationDeg() + (var2.rotationDeg() - var1.rotationDeg()) * var5;
         return new GuiServiceEditorInteractionOverlaySupport.HoverEffectState(var11, var12, var13, var14, var2.mirrorX(), var2.mirrorY());
      } else {
         return var1;
      }
   }

   protected void applyHoverEffectState(Entity var1, GuiServiceEditorInteractionOverlaySupport.HoverEffectState var2, int var3) {
      if (var1 != null && var2 != null) {
         Vector var4 = var2.location() == null ? new Vector(0.0, 0.0, 0.0) : var2.location();
         Vector var5 = var2.scale() == null ? new Vector(1.0, 1.0, 1.0) : var2.scale();
         int var6 = Math.max(0, var3);
         this.hudService.setHudScale(var1, var5, var6, false);
         this.hudService.moveHud(var1, var4, var6, 1);
         this.hudService.setOpacity(var1, this.clampHoverEffectOpacity(var2.opacity()));
         this.applyElementTransform(var1, var2.rotationDeg(), var2.mirrorX(), var2.mirrorY());
      }
   }

   protected String applyHoverColorToText(String var1, String var2) {
      if (var1 != null && !var1.isBlank()) {
         String var3 = this.normalizeHexColor(var2);
         if (var3.isBlank()) {
            return var1;
         } else {
            String var4 = "<#" + var3.toLowerCase(Locale.ROOT) + ">";
            Matcher var5 = Pattern.compile("<#[0-9a-fA-F]{6}>").matcher(var1);
            if (!var5.find()) {
               Matcher var6 = Pattern.compile("(?i)^\\s*<font:[^>]+>").matcher(var1);
               return var6.find() ? var1.substring(0, var6.end()) + var4 + var1.substring(var6.end()) : var4 + var1;
            } else {
               return var1.substring(0, var5.start()) + var4 + var1.substring(var5.end());
            }
         }
      } else {
         return var1;
      }
   }

   protected String brightenOrDarken(String var1) {
      if (var1 != null && !var1.isBlank()) {
         Matcher var2 = Pattern.compile("<#[0-9a-fA-F]{6}>").matcher(var1);
         if (!var2.find()) {
            return var1;
         } else {
            String var3 = var2.group();
            String var4 = var3.substring(2, 8);
            int var5 = Integer.parseInt(var4.substring(0, 2), 16);
            int var6 = Integer.parseInt(var4.substring(2, 4), 16);
            int var7 = Integer.parseInt(var4.substring(4, 6), 16);
            int var8 = (var5 + var6 + var7) / 3;
            if (var8 < 200) {
               var5 = this.clampColor((int)Math.round((double)var5 + (double)(255 - var5) * 0.1));
               var6 = this.clampColor((int)Math.round((double)var6 + (double)(255 - var6) * 0.1));
               var7 = this.clampColor((int)Math.round((double)var7 + (double)(255 - var7) * 0.1));
            } else {
               var5 = this.clampColor((int)Math.round((double)var5 * 0.9));
               var6 = this.clampColor((int)Math.round((double)var6 * 0.9));
               var7 = this.clampColor((int)Math.round((double)var7 * 0.9));
            }

            String var9 = String.format("<#%02x%02x%02x>", var5, var6, var7);
            return var1.substring(0, var2.start()) + var9 + var1.substring(var2.end());
         }
      } else {
         return var1;
      }
   }

   protected int clampColor(int var1) {
      return Math.max(0, Math.min(255, var1));
   }

   protected HoverElement findHoveredElement(EditorSession var1, double var2, double var4) {
      if (var1.previewMode && this.isInsideEditorChromeBlock(var1, var2, var4)) {
         return null;
      } else if (var1.previewMode && !this.isInsidePreviewArea(var1, var2, var4)) {
         return null;
      } else {
         HoverElement var6 = null;

         for (Object var8_raw : var1.elements) {
            HoverElement var8 = (HoverElement)var8_raw;
            String var9 = this.targetIdOf(var8);
            if (this.isTargetInteractable(var1, var9)) {
               EditorRect var10 = this.getHoverBounds(var1, var8);
               if (var10 != null
                  && var2 >= var10.x
                  && var2 <= var10.maxX()
                  && var4 >= var10.y
                  && var4 <= var10.maxY()
                  && (var6 == null || var8.runtimeZ >= var6.runtimeZ)) {
                  var6 = var8;
               }
            }
         }

         return var6;
      }
   }

   @Override
   protected String targetIdOf(HoverElement var1) {
      if (var1 == null) {
         return null;
      } else {
         String var2 = this.firstNonBlank(new String[]{var1.targetId, var1.id});
         return var2.isBlank() ? var1.id : var2;
      }
   }

   @Override
   protected HoverElement findFirstByTargetId(EditorSession var1, String var2) {
      String var3 = this.firstNonBlank(new String[]{this.resolveRuntimeAnimationBaseTargetId(var2)});
      if (var3.isBlank()) {
         return null;
      } else {
         for (Object var5_raw : var1.elements) {
            HoverElement var5 = (HoverElement)var5_raw;
            if (var3.equals(this.targetIdOf(var5))
               && this.doesTargetPathMatchRuntimeAnimationScope(var1, this.firstNonBlank(new String[]{var5 == null ? null : var5.targetPath}), var2)) {
               return var5;
            }
         }

         return null;
      }
   }

   protected EditorRect getHoverBounds(EditorSession var1, HoverElement var2) {
      if (var1 != null && var2 != null) {
         String var3 = this.targetIdOf(var2);
         String var4 = this.resolveInheritedTargetRootPath(var1, var3);
         if (this.isImageGroupRootPath(var1, var4)) {
            EditorRect var5 = this.resolveImageGroupVisualBoundsForRootPath(var1, var4);
            if (var5 != null) {
               Map var6 = this.resolveRawMapAtPath(var1.rawBlocks, var4);
               double var20 = var6 != null ? this.readMapPathDouble(var6, "hitbox.x", 0.0) : 0.0;
               double var21 = var6 != null ? this.readMapPathDouble(var6, "hitbox.y", 0.0) : 0.0;
               double var22 = this.toRenderX(var1, var5.x + var20 + var1.runtimeHitboxOffsetX);
               double var13 = this.toRenderY(var1, var5.y + var21 + var1.runtimeHitboxOffsetY);
               double var15 = this.toRenderSize(var1, var5.width);
               double var17 = this.toRenderSize(var1, var5.height);
               return new EditorRect(var22, var13, var15, var17);
            }
         }

         double var19 = this.toRenderX(var1, this.getInteractiveLeftX(var2) + var1.runtimeHitboxOffsetX);
         double var7 = this.toRenderY(var1, this.getInteractiveTopY(var2) + var1.runtimeHitboxOffsetY);
         double var9 = this.toRenderSize(var1, this.getInteractiveWidth(var2));
         double var11 = this.toRenderSize(var1, this.getInteractiveHeight(var2));
         return new EditorRect(var19, var7, var9, var11);
      } else {
         return null;
      }
   }

   @Override
   protected List<HoverElement> findTargetElements(EditorSession var1, String var2) {
      ArrayList var3 = new ArrayList();
      String var4 = this.firstNonBlank(new String[]{this.resolveRuntimeAnimationBaseTargetId(var2)});
      if (var4.isBlank()) {
         return var3;
      } else {
         for (Object var6_raw : var1.elements) {
            HoverElement var6 = (HoverElement)var6_raw;
            if (var4.equals(this.targetIdOf(var6))
               && this.doesTargetPathMatchRuntimeAnimationScope(var1, this.firstNonBlank(new String[]{var6 == null ? null : var6.targetPath}), var2)) {
               var3.add(var6);
            }
         }

         return var3;
      }
   }

   @Override
   protected List<String> getSelectedTargetIds(EditorSession var1) {
      ArrayList var2 = new ArrayList();
      if (var1 == null) {
         return var2;
      } else {
         if (var1.selectedElementId != null && !var1.selectedElementId.isBlank() && this.isKnownTargetId(var1, var1.selectedElementId)) {
            var2.add(var1.selectedElementId);
         }

         for (Object var4_raw : var1.additionalSelectedElementIds) {
            String var4 = var4_raw != null ? var4_raw.toString() : null;
            if (var4 != null && !var4.isBlank() && !var4.equals(var1.selectedElementId) && this.isKnownTargetId(var1, var4)) {
               var2.add(var4);
            }
         }

         return var2;
      }
   }

   protected EditorRect resolveImageGroupVisualBoundsFromElements(EditorSession var1) {
      if (var1 != null && var1.elements != null && !var1.elements.isEmpty()) {
         for (String var3 : this.getSelectedTargetIds(var1)) {
            String var4 = this.firstNonBlank(new String[]{this.resolveInheritedTargetRootPath(var1, var3)});
            if (!var4.isBlank() && this.isImageGroupRootPath(var1, var4)) {
               EditorRect var5 = this.resolveImageGroupVisualBoundsForRootPath(var1, var4);
               if (var5 != null) {
                  return var5;
               }
            }
         }

         return null;
      } else {
         return null;
      }
   }

   protected EditorRect resolveImageGroupVisualBoundsForRootPath(EditorSession var1, String var2) {
      if (var1 != null && var1.elements != null && !var1.elements.isEmpty() && var2 != null && !var2.isBlank()) {
         String var3 = var2 + ".children.";
         HoverElement var4 = null;
         double var5 = -1.0;

         for (Object var8_raw : var1.elements) {
            HoverElement var8 = (HoverElement)var8_raw;
            if (var8 != null && var8.baseLocation != null && var8.baseScale != null) {
               String var9 = this.firstNonBlank(new String[]{var8.targetPath});
               boolean var10 = var9.equals(var2) || var9.startsWith(var3);
               if (var10) {
                  double var11 = Math.abs(var8.baseScale.getX()) * Math.abs(var8.baseScale.getY());
                  if (var11 > var5) {
                     var5 = var11;
                     var4 = var8;
                  }
               }
            }
         }

         if (var4 == null) {
            return null;
         } else {
            double var15 = Math.max(1.0, var4.baseScale.getX());
            double var16 = Math.max(1.0, var4.baseScale.getY());
            double var17 = var4.baseLocation.getX() - 29.0 + 0.0;
            double var13 = var4.baseLocation.getY() - 248.0 + 0.0;
            return new EditorRect(var17, var13, var15, var16);
         }
      } else {
         return null;
      }
   }

   protected boolean hasSelectedImageGroupTarget(EditorSession var1) {
      if (var1 == null) {
         return false;
      } else {
         for (String var3 : this.getSelectedTargetIds(var1)) {
            String var4 = this.resolveInheritedTargetRootPath(var1, var3);
            if (this.isImageGroupRootPath(var1, var4)) {
               return true;
            }
         }

         return false;
      }
   }

   protected double[] resolveSelectedImageGroupHitboxOffset(EditorSession var1) {
      if (var1 == null) {
         return new double[]{0.0, 0.0};
      } else {
         for (String var3 : this.getSelectedTargetIds(var1)) {
            String var4 = this.firstNonBlank(new String[]{this.resolveInheritedTargetRootPath(var1, var3)});
            if (!var4.isBlank() && this.isImageGroupRootPath(var1, var4)) {
               Map var5 = this.resolveRawMapAtPath(var1.rawBlocks, var4);
               if (var5 != null) {
                  double var6 = this.readMapPathDouble(var5, "hitbox.x", 0.0);
                  double var8 = this.readMapPathDouble(var5, "hitbox.y", 0.0);
                  return new double[]{var6, var8};
               }
            }
         }

         return new double[]{0.0, 0.0};
      }
   }

   protected boolean isKnownTargetId(EditorSession var1, String var2) {
      if (var1 == null || var2 == null || var2.isBlank()) {
         return false;
      } else if (this.findFirstByTargetId(var1, var2) != null) {
         return true;
      } else {
         return this.resolveRawMapAtPath(var1.rawBlocks, var2) != null ? true : this.hasLayerEntryTargetId(var1, var2);
      }
   }

   @Override
   protected boolean isSelectedTarget(EditorSession var1, String var2) {
      return var1 != null && var2 != null && !var2.isBlank() ? var2.equals(var1.selectedElementId) || var1.additionalSelectedElementIds.contains(var2) : false;
   }

   protected void toggleSelectedTarget(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         if (this.isTargetInteractable(var1, var2)) {
            this.appendSelectionTarget(var1, var2);
         }
      }
   }

   @Override
   protected void normalizeSelectionState(EditorSession var1) {
      if (var1 != null) {
         if (!this.isKnownTargetId(var1, var1.selectedElementId)) {
            var1.selectedElementId = null;
         }

         LinkedHashSet var2 = new LinkedHashSet();

         for (Object var4_raw : var1.additionalSelectedElementIds) {
            String var4 = var4_raw != null ? var4_raw.toString() : null;
            if (var4 != null && !var4.isBlank() && !this.equalsNullable(var4, var1.selectedElementId) && this.isKnownTargetId(var1, var4)) {
               var2.add(var4);
            }
         }

         var1.additionalSelectedElementIds.clear();
         var1.additionalSelectedElementIds.addAll(var2);
         if (var1.selectedElementId != null && !this.isKnownTargetId(var1, var1.selectedElementId)) {
            var1.selectedElementId = null;
         }

         if (var1.selectedElementId == null && !var1.additionalSelectedElementIds.isEmpty()) {
            String var5 = var1.additionalSelectedElementIds.iterator().next();
            var1.additionalSelectedElementIds.remove(var5);
            var1.selectedElementId = var5;
         }

         if (this.findFirstByTargetId(var1, var1.hoveredElementId) == null) {
            var1.hoveredElementId = null;
         }

         if (var1.selectedElementId == null) {
            var1.activeHandle = null;
            var1.resizeAnchorValid = false;
            var1.handlesCollapsed = false;
         }
      }
   }

   protected EditorRect getElementBounds(HoverElement var1) {
      if (var1 == null) {
         return null;
      } else {
         double var2 = var1.itemDisplay ? this.getSelectionOutlineLeftX(var1) : this.getInteractiveLeftX(var1);
         double var4 = var1.itemDisplay ? this.getSelectionOutlineTopY(var1) : this.getInteractiveTopY(var1);
         return new EditorRect(var2, var4, this.getInteractiveWidth(var1), this.getInteractiveHeight(var1));
      }
   }

   protected double getSelectionOutlineLeftX(HoverElement var1) {
      if (var1 != null && var1.baseLocation != null && var1.baseScale != null && var1.itemDisplay) {
         double var2 = var1.baseLocation.getX();
         if (var1.itemDisplayBlock) {
            double var4 = Math.max(1.0, var1.baseScale.getX());
            double var6 = Math.max(1.0, var4 * 1.024);
            var2 -= (var6 - var4) / 2.0;
            var2 -= var6 * 0.014;
         }

         return var2;
      } else {
         return this.getInteractiveLeftX(var1);
      }
   }

   protected double getSelectionOutlineTopY(HoverElement var1) {
      if (var1 != null && var1.baseLocation != null && var1.baseScale != null && var1.itemDisplay) {
         double var2 = var1.baseLocation.getY();
         if (var1.itemDisplayBlock) {
            double var4 = Math.max(1.0, var1.baseScale.getY());
            double var6 = Math.max(1.0, var4 * 1.024);
            var2 -= (var6 - var4) / 2.0;
            var2 -= var6 * 0.014;
            var2 -= var6 * 0.018;
         }

         return var2;
      } else {
         return this.getInteractiveTopY(var1);
      }
   }

   protected EditorRect mergeBounds(EditorRect var1, EditorRect var2) {
      if (var1 == null) {
         return var2;
      } else if (var2 == null) {
         return var1;
      } else {
         double var3 = Math.min(var1.x, var2.x);
         double var5 = Math.min(var1.y, var2.y);
         double var7 = Math.max(var1.maxX(), var2.maxX());
         double var9 = Math.max(var1.maxY(), var2.maxY());
         return new EditorRect(var3, var5, var7 - var3, var9 - var5);
      }
   }

   protected String formatAnimationOutlineDebugNumber(double var1) {
      return !Double.isFinite(var1) ? "n/a" : String.format(Locale.ROOT, "%.3f", var1);
   }

   protected String formatAnimationOutlineDebugBounds(EditorRect var1) {
      return var1 == null
         ? "null"
         : "[x="
            + this.formatAnimationOutlineDebugNumber(var1.x)
            + " y="
            + this.formatAnimationOutlineDebugNumber(var1.y)
            + " w="
            + this.formatAnimationOutlineDebugNumber(var1.width)
            + " h="
            + this.formatAnimationOutlineDebugNumber(var1.height)
            + "]";
   }

   protected boolean shouldLogAnimationOutlineDebug(EditorSession var1, String var2, String var3) {
      return false;
   }

   protected EditorRect resolveAnimationTimelineOutlinePreviewLogicalFallback(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         LinkedHashSet var3 = new LinkedHashSet();
         String var4 = this.firstNonBlank(new String[]{var2});
         if (!var4.isBlank()) {
            var3.add(var4);
         }

         String var5 = this.firstNonBlank(new String[]{this.resolveSidebarImageGroupRootTarget(var1, var4)});
         if (!var5.isBlank()) {
            var3.add(var5);
         }

         String var6 = this.firstNonBlank(new String[]{this.resolveAnimationTimelineHierarchyTargetPath(var1, var4)});
         if (!var6.isBlank()) {
            var3.add(var6);
            int var7 = var6.indexOf(".children.");
            if (var7 > 0) {
               var3.add(var6.substring(0, var7));
            }
         }

         String var11 = this.firstNonBlank(new String[]{this.resolveInheritedTargetRootPath(var1, var4)});
         if (!var11.isBlank()) {
            var3.add(var11);
         }

         for (Object var9_raw : var3) {
            String var9 = var9_raw != null ? var9_raw.toString() : null;
            EditorRect var10 = this.resolveAnimationTimelineImageGroupPreviewLogicalBounds(var1, var9);
            if (var10 != null) {
               return new EditorRect(var10.x, var10.y, var10.width, var10.height);
            }
         }

         return null;
      } else {
         return null;
      }
   }

   protected void logAnimationOutlineDebug(
      EditorSession var1, String var2, String var3, String var4, EditorRect var5, EditorRect var6, double var7, double var9, double var11, double var13
   ) {
      if (this.plugin != null && var1 != null) {
         String var15 = this.firstNonBlank(new String[]{var4, "unknown"});
         String var16 = this.firstNonBlank(new String[]{var3, "unknown"});
         String var17 = this.formatAnimationOutlineDebugNumber((double)this.clampAnimationTimelineTick(var1.animationTimelineTick));
         String var18 = this.formatAnimationOutlineDebugNumber(var1.animationTimelineSliderDragTick == null ? Double.NaN : var1.animationTimelineSliderDragTick);
         this.plugin
            .getLogger()
            .info(
               "[UltimateUI][AnimImageDebug] stage="
                  + this.firstNonBlank(new String[]{var2, "editor-outline"})
                  + " source="
                  + var16
                  + " targetId="
                  + var15
                  + " sessionTick="
                  + var17
                  + " sliderTick="
                  + var18
                  + " selected="
                  + this.formatAnimationOutlineDebugBounds(var5)
                  + " previewLogical="
                  + this.formatAnimationOutlineDebugBounds(var6)
                  + " render=[x="
                  + this.formatAnimationOutlineDebugNumber(var7)
                  + " y="
                  + this.formatAnimationOutlineDebugNumber(var9)
                  + " w="
                  + this.formatAnimationOutlineDebugNumber(var11)
                  + " h="
                  + this.formatAnimationOutlineDebugNumber(var13)
                  + "]"
            );
      }
   }

   protected void logAnimationOutlineLineHeights(EditorSession var1, String var2, boolean var3, boolean var4, boolean var5, boolean var6, double var7) {
      if (this.plugin != null && var1 != null) {
         String var9 = this.firstNonBlank(new String[]{var2, "unknown"});
         String var10 = this.formatAnimationOutlineDebugNumber((double)this.clampAnimationTimelineTick(var1.animationTimelineTick));
         String var11 = this.formatAnimationOutlineDebugNumber(var1.animationTimelineSliderDragTick == null ? Double.NaN : var1.animationTimelineSliderDragTick);
         double var12 = Math.max(1.0, 4.0);
         double var14 = Math.max(1.0, 4.0);
         double var16 = Math.max(1.0, var7);
         double var18 = Math.max(1.0, var7);
         this.plugin
            .getLogger()
            .info(
               "[UltimateUI][AnimImageDebug] stage=editor-outline-line-heights targetId="
                  + var9
                  + " sessionTick="
                  + var10
                  + " sliderTick="
                  + var11
                  + " topH="
                  + (var3 ? "frozen" : this.formatAnimationOutlineDebugNumber(var12))
                  + " bottomH="
                  + (var4 ? "frozen" : this.formatAnimationOutlineDebugNumber(var14))
                  + " leftH="
                  + (var5 ? "frozen" : this.formatAnimationOutlineDebugNumber(var16))
                  + " rightH="
                  + (var6 ? "frozen" : this.formatAnimationOutlineDebugNumber(var18))
                  + " renderH="
                  + this.formatAnimationOutlineDebugNumber(var7)
            );
      }
   }

   protected void logAnimationOutlineBoundsSource(EditorSession var1, String var2, String var3, EditorRect var4) {
      if (var1 != null && var2 != null && !var2.isBlank() && var4 != null) {
         String var5 = this.resolveInheritedTargetRootPath(var1, var2);
         if (this.shouldLogAnimationOutlineDebug(var1, var2, var5)) {
            this.logAnimationOutlineDebug(var1, "editor-outline-source", var3, var2, var4, null, Double.NaN, Double.NaN, Double.NaN, Double.NaN);
         }
      }
   }

   protected String formatAnimationOutlineRuntimeHudSnapshot(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Entity var3 = this.hudService.getHud(var1, var2);
         if (var3 == null) {
            return var2 + "[missing]";
         } else {
            Vector var4 = this.hudService.getHudScale(var3);
            Vector var5 = this.hudService.getHudLocation(var3);
            return var2
               + "[h="
               + this.formatAnimationOutlineDebugNumber(var4 == null ? Double.NaN : var4.getY())
               + " y="
               + this.formatAnimationOutlineDebugNumber(var5 == null ? Double.NaN : var5.getY())
               + "]";
         }
      } else {
         return this.firstNonBlank(new String[]{var2, "unknown"}) + "[missing]";
      }
   }

   protected void logAnimationSelectionOverlayRuntimeHudHeights(Player var1, EditorSession var2, String var3) {
      if (this.plugin != null && var2 != null && var1 != null) {
         String var4 = this.firstNonBlank(new String[]{var3, "unknown"});
         String var5 = this.formatAnimationOutlineDebugNumber((double)this.clampAnimationTimelineTick(var2.animationTimelineTick));
         String var6 = this.formatAnimationOutlineDebugNumber(var2.animationTimelineSliderDragTick == null ? Double.NaN : var2.animationTimelineSliderDragTick);
         this.plugin
            .getLogger()
            .info(
               "[UltimateUI][AnimImageDebug] stage=editor-outline-runtime-bars targetId="
                  + var4
                  + " sessionTick="
                  + var5
                  + " sliderTick="
                  + var6
                  + " top="
                  + this.formatAnimationOutlineRuntimeHudSnapshot(var1, "editor_top")
                  + " bottom="
                  + this.formatAnimationOutlineRuntimeHudSnapshot(var1, "editor_bottom")
                  + " left="
                  + this.formatAnimationOutlineRuntimeHudSnapshot(var1, "editor_left")
                  + " right="
                  + this.formatAnimationOutlineRuntimeHudSnapshot(var1, "editor_right")
            );
      }
   }

   protected void logAnimationTargetOutlineRuntimeHudHeights(Player var1, EditorSession var2, String var3) {
      if (this.plugin != null && var2 != null && var1 != null && var3 != null && !var3.isBlank()) {
         LinkedHashSet var4 = new LinkedHashSet();

         for (HoverElement var6 : this.findTargetElements(var2, var3)) {
            String var7 = this.firstNonBlank(new String[]{var6 == null ? null : var6.id});
            if (!var7.isBlank()) {
               String var8 = var7 + "_outline";
               var4.add(var8);
               if (this.isRoundedType(var6 == null ? null : var6.type)) {
                  var4.add(var8 + "_r_core");
                  var4.add(var8 + "_r_top");
                  var4.add(var8 + "_r_bottom");
                  var4.add(var8 + "_r_left");
                  var4.add(var8 + "_r_right");
                  var4.add(var8 + "_r_tl");
                  var4.add(var8 + "_r_tr");
                  var4.add(var8 + "_r_bl");
                  var4.add(var8 + "_r_br");
               }
            }
         }

         if (!var4.isEmpty()) {
            int var10 = 0;
            ArrayList var11 = new ArrayList();

            for (Object var14_raw : var4) {
               String var14 = var14_raw != null ? var14_raw.toString() : null;
               Entity var9 = this.hudService.getHud(var1, var14);
               if (var9 != null) {
                  var10++;
                  if (var11.size() < 12) {
                     var11.add(this.formatAnimationOutlineRuntimeHudSnapshot(var1, var14));
                  }
               }
            }

            String var13 = this.firstNonBlank(new String[]{var3, "unknown"});
            String var15 = this.formatAnimationOutlineDebugNumber((double)this.clampAnimationTimelineTick(var2.animationTimelineTick));
            String var16 = this.formatAnimationOutlineDebugNumber(
               var2.animationTimelineSliderDragTick == null ? Double.NaN : var2.animationTimelineSliderDragTick
            );
            this.plugin
               .getLogger()
               .info(
                  "[UltimateUI][AnimImageDebug] stage=editor-outline-runtime-target-huds targetId="
                     + var13
                     + " sessionTick="
                     + var15
                     + " sliderTick="
                     + var16
                     + " existing="
                     + var10
                     + " sample="
                     + (var11.isEmpty() ? "none" : String.join(" ", var11))
               );
         }
      }
   }

   protected EditorRect resolveAnimationTimelineLiveOutlineBoundsForTarget(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         LinkedHashSet var3 = new LinkedHashSet();
         this.addAnimationTimelineOutlineRootPathCandidate(var3, this.resolveInheritedTargetRootPath(var1, var2));
         this.addAnimationTimelineOutlineRootPathCandidate(var3, this.resolveRawTargetPathByTargetId(var1, var2));
         this.addAnimationTimelineOutlineRootPathCandidate(var3, this.resolveAnimationTimelineHierarchyTargetPath(var1, var2));

         for (String var6 : this.collectRawTargetCandidatePathsByTargetId(var1, var2)) {
            this.addAnimationTimelineOutlineRootPathCandidate(var3, this.resolveRawTargetInheritedRootPath(var1, var6));
            this.addAnimationTimelineOutlineRootPathCandidate(var3, var6);
         }

         String var17 = this.firstNonBlank(new String[]{this.resolveSidebarImageGroupRootTarget(var1, var2)});
         if (var17.isBlank() && this.isAnimationTimelineImageGroupRoot(var1, var2)) {
            var17 = this.firstNonBlank(new String[]{var2});
         }

         if (!var17.isBlank()) {
            this.addAnimationTimelineOutlineRootPathCandidate(var3, this.resolveRawTargetPathByTargetId(var1, var17));
            this.addAnimationTimelineOutlineRootPathCandidate(var3, this.resolveAnimationTimelineHierarchyTargetPath(var1, var17));
            this.addAnimationTimelineOutlineRootPathCandidate(var3, var17);
         }

         if (var3.isEmpty()) {
            this.logAnimationTimelineLiveOutlineResolver(var1, var2, var17, Collections.emptyList(), null, null, "none");
            return null;
         } else {
            String var18 = null;
            String var7 = "none";
            EditorRect var8 = null;
            ArrayList var9 = new ArrayList();

            for (Object var11_raw : var3) {
               String var11 = var11_raw != null ? var11_raw.toString() : null;
               int var12 = this.countAnimationTimelineLiveOutlineElementsByPath(var1, var11);
               EditorRect var13 = this.resolveAnimationTimelineImageGroupLiveOutlineBoundsFromElements(var1, var11);
               String var14 = "path";
               int var15 = 0;
               if (var13 == null) {
                  var15 = this.countAnimationTimelineLiveOutlineElementsByDescendantTargetIds(var1, var11);
                  var13 = this.resolveAnimationTimelineLiveOutlineBoundsByDescendantTargetIds(var1, var11);
                  if (var13 != null) {
                     var14 = "descendant-target-id";
                  }
               }

               var9.add(
                  var11 + "{pathMatches=" + var12 + " descendantIdMatches=" + var15 + " resolved=" + (var13 == null ? "no" : "yes") + " source=" + var14 + "}"
               );
               if (var8 == null && var13 != null) {
                  var8 = var13;
                  var18 = var11;
                  var7 = var14;
               }
            }

            if (var8 == null) {
               String var19 = this.firstNonBlank(new String[]{var17, var2});
               EditorRect var21 = this.resolveAnimationTimelineLiveOutlineBoundsFromPreviewElements(var1, var19, var9);
               if (var21 != null) {
                  var8 = var21;
                  var18 = var19;
                  var7 = "preview-elements";
               }
            }

            if (var8 != null && !var17.isBlank()) {
               for (Object var22_raw : var3) {
                  String var22 = var22_raw != null ? var22_raw.toString() : null;
                  EditorRect var23 = this.resolveAnimationTimelineImageGroupLiveRootBoundsFromElements(var1, var17, var22);
                  if (var23 != null) {
                     double var24 = Math.min(var8.x, var23.x);
                     double var25 = Math.max(var8.maxX(), var23.maxX());
                     if (var25 - var24 > var8.width + 0.001) {
                        var8 = new EditorRect(var24, var8.y, var25 - var24, var8.height);
                     }
                     break;
                  }
               }
            }

            this.logAnimationTimelineLiveOutlineResolver(var1, var2, var17, var9, var18, var8, var7);
            return var8;
         }
      } else {
         return null;
      }
   }

   protected void addAnimationTimelineOutlineRootPathCandidate(Set<String> var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = var2;
         int var4 = var2.indexOf(".children.");
         if (var4 > 0) {
            var3 = var2.substring(0, var4);
         }

         if (!var3.isBlank()) {
            var1.add(var3);
         }
      }
   }

   protected int countAnimationTimelineLiveOutlineElementsByPath(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank() && var1.elements != null && !var1.elements.isEmpty()) {
         int var3 = 0;
         String var4 = var2 + ".children.";

         for (Object var6_raw : var1.elements) {
            HoverElement var6 = (HoverElement)var6_raw;
            String var7 = this.firstNonBlank(new String[]{var6 == null ? null : var6.targetPath});
            if (!var7.isBlank() && var7.startsWith(var4)) {
               var3++;
            }
         }

         return var3;
      } else {
         return 0;
      }
   }

   protected int countAnimationTimelineLiveOutlineElementsByDescendantTargetIds(EditorSession var1, String var2) {
      LinkedHashSet var3 = this.collectAnimationTimelineDescendantTargetIdsByPath(var1, var2);
      if (!var3.isEmpty() && var1 != null && var1.elements != null && !var1.elements.isEmpty()) {
         int var4 = 0;

         for (Object var6_raw : var1.elements) {
            HoverElement var6 = (HoverElement)var6_raw;
            String var7 = this.firstNonBlank(new String[]{this.targetIdOf(var6), var6 == null ? null : var6.targetId});
            if (!var7.isBlank()) {
               String var8 = this.firstNonBlank(new String[]{this.resolveRuntimeAnimationBaseTargetId(var7), var7});
               if (var3.contains(var7) || var3.contains(var8)) {
                  var4++;
               }
            }
         }

         return var4;
      } else {
         return 0;
      }
   }

   protected EditorRect resolveAnimationTimelineLiveOutlineBoundsByDescendantTargetIds(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank() && var1.elements != null && !var1.elements.isEmpty()) {
         LinkedHashSet var3 = this.collectAnimationTimelineDescendantTargetIdsByPath(var1, var2);
         if (var3.isEmpty()) {
            return null;
         } else {
            EditorRect var4 = null;

            for (Object var6_raw : var1.elements) {
               HoverElement var6 = (HoverElement)var6_raw;
               String var7 = this.firstNonBlank(new String[]{this.targetIdOf(var6), var6 == null ? null : var6.targetId});
               if (!var7.isBlank()) {
                  String var8 = this.firstNonBlank(new String[]{this.resolveRuntimeAnimationBaseTargetId(var7), var7});
                  if (var3.contains(var7) || var3.contains(var8)) {
                     var4 = this.mergeBounds(var4, this.getElementBounds(var6));
                  }
               }
            }

            return var4;
         }
      } else {
         return null;
      }
   }

   protected EditorRect resolveAnimationTimelineLiveOutlineBoundsFromPreviewElements(EditorSession var1, String var2, List<String> var3) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         List var4 = this.resolveAnimationTimelinePreviewTransformElements(var1, var2);
         if (var4 != null && !var4.isEmpty()) {
            String var5 = this.firstNonBlank(
               new String[]{this.resolveAnimationTimelineHierarchyTargetPath(var1, var2), this.resolveRawTargetPathByTargetId(var1, var2), var2}
            );
            EditorRect var6 = null;
            int var7 = 0;

            for (Object var9_raw : var4) {
               HoverElement var9 = (HoverElement)var9_raw;
               if (var9 != null) {
                  String var10 = this.firstNonBlank(new String[]{var9.type}).toLowerCase(Locale.ROOT);
                  if (!"hitbox".equals(var10)) {
                     EditorRect var11 = this.getElementBounds(var9);
                     if (var11 != null) {
                        var6 = this.mergeBounds(var6, var11);
                        var7++;
                     }
                  }
               }
            }

            if (var6 != null) {
               if (var3 != null) {
                  var3.add("preview-elements{count=" + var4.size() + " nonHitbox=" + var7 + " resolved=yes source=non-hitbox}");
               }

               return var6;
            } else {
               EditorRect var20 = null;
               int var21 = 0;
               String var22 = this.firstNonBlank(new String[]{var5});

               for (Object var12_raw : var4) {
                  HoverElement var12 = (HoverElement)var12_raw;
                  String var13 = this.firstNonBlank(new String[]{var12 == null ? null : var12.targetPath});
                  if (!var13.isBlank() && !this.equalsNullable(var13, var22)) {
                     EditorRect var14 = this.getElementBounds(var12);
                     if (var14 != null) {
                        var20 = this.mergeBounds(var20, var14);
                        var21++;
                     }
                  }
               }

               if (var20 != null) {
                  if (var3 != null) {
                     var3.add("preview-elements{count=" + var4.size() + " nonRootPath=" + var21 + " resolved=yes source=non-root-path}");
                  }

                  return var20;
               } else {
                  ArrayList var24 = new ArrayList();
                  double var25 = Double.NEGATIVE_INFINITY;
                  int var26 = -1;

                  for (Object var16_raw : var4) {
                     HoverElement var16 = (HoverElement)var16_raw;
                     EditorRect var17 = this.getElementBounds(var16);
                     if (var17 != null) {
                        var24.add(var17);
                        double var18 = Math.abs(var17.width) * Math.abs(var17.height);
                        if (var18 > var25) {
                           var25 = var18;
                           var26 = var24.size() - 1;
                        }
                     }
                  }

                  if (var24.size() > 1 && var26 >= 0) {
                     EditorRect var27 = null;

                     for (int var28 = 0; var28 < var24.size(); var28++) {
                        if (var28 != var26) {
                           var27 = this.mergeBounds(var27, (EditorRect)var24.get(var28));
                        }
                     }

                     if (var27 != null) {
                        if (var3 != null) {
                           var3.add("preview-elements{count=" + var4.size() + " viable=" + var24.size() + " resolved=yes source=without-largest}");
                        }

                        return var27;
                     }
                  }

                  if (var3 != null) {
                     var3.add("preview-elements{count=" + var4.size() + " viable=" + var24.size() + " resolved=no source=none}");
                  }

                  return null;
               }
            }
         } else {
            if (var3 != null) {
               var3.add("preview-elements{count=0 resolved=no source=none}");
            }

            return null;
         }
      } else {
         return null;
      }
   }

   protected LinkedHashSet<String> collectAnimationTimelineDescendantTargetIdsByPath(EditorSession var1, String var2) {
      LinkedHashSet var3 = new LinkedHashSet();
      if (var1 != null && var2 != null && !var2.isBlank()) {
         List var4 = var1.renderBlocks;
         if ((var4 == null || var4.isEmpty()) && var1.rawBlocks != null && !var1.rawBlocks.isEmpty()) {
            var4 = this.resolveRenderableBlocks(var1.rawBlocks, var1.components);
         }

         if (var4 != null && !var4.isEmpty()) {
            String var5 = var2 + ".children.";

            for (Map var7 : (List<Map>)(List<?>)var4) {
               if (var7 != null && !var7.isEmpty()) {
                  String var8 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var7, "__editor_target_path"))});
                  if (!var8.isBlank() && var8.startsWith(var5)) {
                     String var9 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var7, "__editor_target_id"))});
                     if (!var9.isBlank()) {
                        var3.add(var9);
                        String var10 = this.firstNonBlank(new String[]{this.resolveRuntimeAnimationBaseTargetId(var9)});
                        if (!var10.isBlank()) {
                           var3.add(var10);
                        }
                     }
                  }
               }
            }

            return var3;
         } else {
            return var3;
         }
      } else {
         return var3;
      }
   }

   protected void logAnimationTimelineLiveOutlineResolver(
      EditorSession var1, String var2, String var3, List<String> var4, String var5, EditorRect var6, String var7
   ) {
      if (this.plugin != null && var1 != null) {
         String var8 = this.resolveInheritedTargetRootPath(var1, var2);
         if (this.shouldLogAnimationOutlineDebug(var1, var2, var8)) {
            String var9 = this.formatAnimationOutlineDebugNumber((double)this.clampAnimationTimelineTick(var1.animationTimelineTick));
            String var10 = this.formatAnimationOutlineDebugNumber(
               var1.animationTimelineSliderDragTick == null ? Double.NaN : var1.animationTimelineSliderDragTick
            );
            this.plugin
               .getLogger()
               .info(
                  "[UltimateUI][AnimImageDebug] stage=editor-outline-live-resolver targetId="
                     + this.firstNonBlank(new String[]{var2, "unknown"})
                     + " rootTargetId="
                     + this.firstNonBlank(new String[]{var3, "none"})
                     + " inheritedRootPath="
                     + this.firstNonBlank(new String[]{var8, "none"})
                     + " resolvedRootPath="
                     + this.firstNonBlank(new String[]{var5, "none"})
                     + " resolvedSource="
                     + this.firstNonBlank(new String[]{var7, "none"})
                     + " sessionTick="
                     + var9
                     + " sliderTick="
                     + var10
                     + " selected="
                     + this.formatAnimationOutlineDebugBounds(var6)
                     + " candidates="
                     + (var4 != null && !var4.isEmpty() ? String.join(" | ", var4) : "none")
               );
         }
      }
   }

   @Override
   protected EditorRect getTargetBounds(EditorSession var1, String var2) {
      String var3 = this.resolveInheritedTargetRootPath(var1, var2);
      boolean var4 = this.shouldLogAnimationOutlineDebug(var1, var2, var3);
      EditorRect var5 = null;

      for (HoverElement var7 : this.findTargetElements(var1, var2)) {
         var5 = this.mergeBounds(var5, this.getElementBounds(var7));
      }

      boolean var13 = var1 != null && var1.activeTool == EditorTool.ANIMATION && var1.animationTimelinePanelVisible;
      if (var13) {
         EditorRect var14 = this.resolveAnimationTimelineLiveOutlineBoundsForTarget(var1, var2);
         if (var14 != null) {
            this.logAnimationOutlineBoundsSource(var1, var2, "timeline-live-outline", var14);
            return var14;
         }

         EditorRect var8 = this.resolveAnimationTimelineImageGroupLogicalBounds(var1, var2);
         if (var8 != null) {
            this.logAnimationOutlineBoundsSource(var1, var2, "timeline-logical", var8);
            return var8;
         }

         EditorRect var9 = this.resolveAnimationTimelineOutlinePreviewLogicalFallback(var1, var2);
         if (var9 != null) {
            if (var4) {
               this.logAnimationOutlineDebug(
                  var1, "editor-outline-fallback", "timeline-preview-logical", var2, var9, var9, Double.NaN, Double.NaN, Double.NaN, Double.NaN
               );
            }

            this.logAnimationOutlineBoundsSource(var1, var2, "timeline-preview-logical", var9);
            return var9;
         }

         if (var4) {
            this.logAnimationOutlineDebug(
               var1, "editor-outline-fallback", "no-timeline-logical", var2, var5, null, Double.NaN, Double.NaN, Double.NaN, Double.NaN
            );
         }

         if (var5 != null) {
            if (var4) {
               this.logAnimationOutlineDebug(
                  var1, "editor-outline-fallback", "target-elements", var2, var5, null, Double.NaN, Double.NaN, Double.NaN, Double.NaN
               );
            }

            this.logAnimationOutlineBoundsSource(var1, var2, "target-elements", var5);
            return var5;
         }
      }

      if (var3 != null && !var3.isBlank()) {
         if (!var13) {
            EditorRect var15 = this.resolveAnimationTimelineImageGroupPreviewOutlineBounds(var1, var2);
            if (var15 != null) {
               if (var4) {
                  EditorRect var19 = this.resolveAnimationTimelineImageGroupPreviewLogicalBounds(var1, var2);
                  if (var19 == null && var3 != null && !var3.isBlank()) {
                     var19 = this.resolveAnimationTimelineImageGroupPreviewLogicalBounds(var1, var3);
                  }

                  this.logAnimationOutlineDebug(
                     var1, "editor-outline-fallback", "preview-outline", var2, var15, var19, Double.NaN, Double.NaN, Double.NaN, Double.NaN
                  );
               }

               this.logAnimationOutlineBoundsSource(var1, var2, "preview-outline", var15);
               return var15;
            }
         }

         EditorRect var16 = this.resolveImageGroupRootBoundsFromRaw(var1, var3);
         if (var16 != null) {
            this.logAnimationOutlineBoundsSource(var1, var2, "raw-root-derived", var16);
            return var16;
         }

         EditorRect var18 = null;
         if (var1 != null && var1.elements != null) {
            String var20 = var3 + ".children.";

            for (Object var11_raw : var1.elements) {
               HoverElement var11 = (HoverElement)var11_raw;
               String var12 = this.firstNonBlank(new String[]{var11 == null ? null : var11.targetPath});
               if (!var12.isBlank() && (var3.equals(var12) || var12.startsWith(var20))) {
                  var18 = this.mergeBounds(var18, this.getElementBounds(var11));
               }
            }
         }

         if (var18 != null) {
            this.logAnimationOutlineBoundsSource(var1, var2, "inherited-elements", var18);
            return var18;
         }

         EditorRect var21 = this.resolveTargetBoundsFromRawByPathPrefix(var1, var3);
         if (var21 != null) {
            this.logAnimationOutlineBoundsSource(var1, var2, "inherited-raw", var21);
            return var21;
         }
      }

      if (var5 != null) {
         this.logAnimationOutlineBoundsSource(var1, var2, "target-elements", var5);
         return var5;
      } else {
         EditorRect var17 = this.resolveTargetBoundsFromRaw(var1, var2);
         this.logAnimationOutlineBoundsSource(var1, var2, "raw-target", var17);
         return var17;
      }
   }

   protected EditorRect resolveImageGroupRootBoundsFromRaw(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var3 = this.resolveRawMapAtPath(var1.rawBlocks, var2);
         if (var3 != null && !var3.isEmpty() && this.isImageGroupRoot(var3)) {
            double var4 = this.readMapPathDouble(var3, "size.width", this.readMapPathDouble(var3, "width", Double.NaN));
            double var6 = this.readMapPathDouble(var3, "size.height", this.readMapPathDouble(var3, "height", Double.NaN));
            if (Double.isFinite(var4) && Double.isFinite(var6) && !(var4 <= 0.0) && !(var6 <= 0.0)) {
               double var8 = Math.abs(var4);
               double var10 = Math.abs(var6);
               Vector var12 = this.resolveParentAbsoluteOffset(var1, var2);
               double var13 = this.readMapPathDouble(var3, "position.x", this.readMapPathDouble(var3, "x", 0.0));
               double var15 = this.readMapPathDouble(var3, "position.y", this.readMapPathDouble(var3, "y", 0.0));
               double var17 = var12.getX() + var13;
               double var19 = var12.getY() + var15;
               return new EditorRect(var17, var19, var8, var10);
            } else {
               return null;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   protected EditorRect resolveAnimationTimelineImageGroupPreviewOutlineBounds(EditorSession var1, String var2) {
      if (var1 == null || var2 == null || var2.isBlank()) {
         return null;
      } else if (var1.activeTool != EditorTool.ANIMATION || !var1.animationTimelinePanelVisible) {
         return null;
      } else if (this.isAnimationTimelineTransformInteractionActive(var1)) {
         return null;
      } else {
         String var3 = this.firstNonBlank(new String[]{this.resolveInheritedTargetRootPath(var1, var2)});
         if (var3.isBlank()) {
            return null;
         } else {
            EditorRect var4 = this.resolveAnimationTimelineImageGroupLiveOutlineBoundsFromElements(var1, var3);
            if (var4 != null) {
               return var4;
            } else {
               EditorRect var5 = null;
               if (this.equalsNullable(var2, var1.animationPreviewTargetId)) {
                  var5 = var1.animationPreviewAppliedBounds;
               }

               if (var5 == null) {
                  var5 = var1.animationPreviewAdditionalAppliedBoundsByTarget.get(var2);
               }

               if (var5 == null && this.equalsNullable(var3, var1.animationPreviewTargetId)) {
                  var5 = var1.animationPreviewAppliedBounds;
               }

               if (var5 == null) {
                  var5 = var1.animationPreviewAdditionalAppliedBoundsByTarget.get(var3);
               }

               if (var5 == null) {
                  int var6 = var2.indexOf(".children.");
                  if (var6 > 0) {
                     String var7 = var2.substring(0, var6);
                     if (this.equalsNullable(var7, var1.animationPreviewTargetId)) {
                        var5 = var1.animationPreviewAppliedBounds;
                     }

                     if (var5 == null) {
                        var5 = var1.animationPreviewAdditionalAppliedBoundsByTarget.get(var7);
                     }
                  }
               }

               return var5 == null ? null : new EditorRect(var5.x, var5.y, var5.width, var5.height);
            }
         }
      }
   }

   @Override
   protected EditorRect resolveAnimationTimelineImageGroupLiveOutlineBoundsFromElements(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank() && var1.elements != null && !var1.elements.isEmpty()) {
         EditorRect var3 = null;
         String var4 = var2 + ".children.";

         for (Object var6_raw : var1.elements) {
            HoverElement var6 = (HoverElement)var6_raw;
            String var7 = this.firstNonBlank(new String[]{var6 == null ? null : var6.targetPath});
            if (!var7.isBlank() && var7.startsWith(var4)) {
               var3 = this.mergeBounds(var3, this.getElementBounds(var6));
            }
         }

         return var3;
      } else {
         return null;
      }
   }

   protected EditorRect resolveImageGroupOutlineBoundsFromFrames(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var3 = this.resolveRawMapAtPath(var1.rawBlocks, var2);
         if (var3 != null && !var3.isEmpty()) {
            if (var3.get("children") instanceof List var5 && !var5.isEmpty()) {
               Vector var6 = this.resolveParentAbsoluteOffset(var1, var2);
               double var7 = this.readMapPathDouble(var3, "position.x", this.readMapPathDouble(var3, "x", 0.0)) + var6.getX();
               double var9 = this.readMapPathDouble(var3, "position.y", this.readMapPathDouble(var3, "y", 0.0)) + var6.getY();
               double var11 = Double.POSITIVE_INFINITY;
               HashSet var13 = new HashSet();
               HashSet var14 = new HashSet();

               for (Object var16 : var5) {
                  if (var16 instanceof Map) {
                     Map var17 = (Map)var16;
                     if (!var17.isEmpty()) {
                        double var19 = this.readMapPathDouble(var17, "position.x", this.readMapPathDouble(var17, "x", Double.NaN));
                        double var21 = this.readMapPathDouble(var17, "position.y", this.readMapPathDouble(var17, "y", Double.NaN));
                        if (Double.isFinite(var19) && Double.isFinite(var21)) {
                           double var23 = this.readMapPathDouble(var17, "__editor_frame_shift_y", 0.0);
                           if (!Double.isFinite(var23)) {
                              var23 = 0.0;
                           }

                           double var25 = var21 - var23;
                           var11 = Math.min(var11, var19);
                           var13.add(this.normalizeImageGroupFrameCoordinate(var19));
                           var14.add(this.normalizeImageGroupFrameCoordinate(var25));
                        }
                     }
                  }
               }

               if (Double.isFinite(var11) && !var13.isEmpty() && !var14.isEmpty()) {
                  int var45 = Math.max(1, var13.size());
                  int var46 = Math.max(1, var14.size());
                  double var47 = (double)var45 * 250.0;
                  double var48 = (double)var46 * 250.0;
                  double var49 = Math.abs(this.readMapPathDouble(var3, "size.width", this.readMapPathDouble(var3, "width", var47)));
                  double var50 = Math.abs(this.readMapPathDouble(var3, "size.height", this.readMapPathDouble(var3, "height", var48)));
                  double var51 = Double.isFinite(var49) && !(var47 <= 1.0E-4) ? Math.max(1.0E-4, var49 / var47) : 1.0;
                  double var27 = Double.isFinite(var50) && !(var48 <= 1.0E-4) ? Math.max(1.0E-4, var50 / var48) : 1.0;
                  double var29 = 250.0 * var51;
                  double var31 = 250.0 * var27;
                  double var33 = Math.max(var29, (double)var45 * var29);
                  double var35 = Math.max(var31, (double)var46 * var31);
                  double var37 = var7 + var11;
                  double var39 = Math.max(0.0, (double)(var46 - 1) * var31);
                  double var41 = var9 + var39 + var31;
                  double var43 = var41 - var35 - var31;
                  return new EditorRect(var37, var43, var33, var35);
               }

               return null;
            }

            return null;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   protected double normalizeImageGroupFrameCoordinate(double var1) {
      return !Double.isFinite(var1) ? var1 : Math.rint(var1 * 1000000.0) / 1000000.0;
   }

   protected EditorRect removeImageGroupOutlineCompensation(EditorRect var1) {
      return var1 == null ? null : new EditorRect(var1.x, var1.y + 250.0, var1.width, var1.height);
   }

   protected EditorRect convertImageGroupOutlineToRootBounds(Map<String, Object> var1, EditorRect var2) {
      if (var2 == null) {
         return null;
      } else {
         double var3 = this.resolveImageGroupOutlineToRootYOffset(var1, var2.height);
         return new EditorRect(var2.x, var2.y + var3, var2.width, var2.height);
      }
   }

   @Override
   protected EditorRect resolveSidebarEditableBounds(EditorSession var1, String var2) {
      EditorRect var3 = super.resolveSidebarEditableBounds(var1, var2);
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var4 = this.firstNonBlank(new String[]{this.resolveAnimationTimelineHierarchyTargetPath(var1, var2), var2});
         int var5 = var4.indexOf(".children.");
         if (var5 > 0) {
            var4 = var4.substring(0, var5);
         }

         if (var4.isBlank()) {
            return var3;
         } else {
            EditorRect var6 = this.resolveImageGroupRootBoundsFromRaw(var1, var4);
            return var6 != null ? var6 : var3;
         }
      } else {
         return var3;
      }
   }

   protected double resolveImageGroupOutlineToRootYOffset(Map<String, Object> var1, double var2) {
      return 0.0;
   }

   protected boolean isCompactGlyphMatrixImageGroup(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         String var2 = this.firstNonBlank(
               new String[]{this.stringValue(this.readMapPathValue(var1, "glyph_matrix")), this.stringValue(this.readMapPathValue(var1, "glyphMatrix"))}
            )
            .trim();
         if (!var2.isBlank()) {
            return true;
         } else {
            String var3 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "type"))}).trim().toLowerCase(Locale.ROOT);
            if ("image".equals(var3)) {
               String var4 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "image"))}).trim();
               return !var4.isBlank();
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   protected boolean hasImageGroupFrameShiftMetadata(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         if (var1.get("children") instanceof List var3 && !var3.isEmpty()) {
            for (Object var5 : var3) {
               if (var5 instanceof Map var6 && var6.containsKey("__editor_frame_shift_y")) {
                  return true;
               }
            }

            return false;
         }

         return false;
      } else {
         return false;
      }
   }

   protected String resolveInheritedTargetRootPath(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         String var4 = this.resolveInheritedTargetRootPathByPath(var1, var3 == null ? null : var3.targetPath);
         if (var4 != null && !var4.isBlank()) {
            return var4;
         } else {
            for (HoverElement var6 : this.findTargetElements(var1, var2)) {
               String var7 = this.resolveInheritedTargetRootPathByPath(var1, var6 == null ? null : var6.targetPath);
               if (var7 != null && !var7.isBlank()) {
                  return var7;
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   protected String resolveInheritedTargetRootPathByPath(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = var2;

         while (var3 != null && !var3.isBlank()) {
            Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3);
            if (this.shouldInheritTargetToChildren(var4)) {
               return var3;
            }

            int var5 = var3.lastIndexOf(".children.");
            if (var5 < 0) {
               break;
            }

            var3 = var3.substring(0, var5);
         }

         return null;
      } else {
         return null;
      }
   }

   protected boolean shouldInheritTargetToChildren(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         Object var2 = this.readMapPathValue(var1, "__editor_inherit_target_to_children");
         return this.parseBooleanFlag(var2, false) ? true : this.isImageGroupRoot(var1);
      } else {
         return false;
      }
   }

   protected boolean isImageGroupRootPath(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var3 = this.resolveRawMapAtPath(var1.rawBlocks, var2);
         return this.isImageGroupRoot(var3);
      } else {
         return false;
      }
   }

   protected boolean hasDirectImageGroupGlyphChild(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         if (this.readMapPathValue(var1, "children") instanceof List var3 && !var3.isEmpty()) {
            for (Object var5 : var3) {
               if (var5 instanceof Map) {
                  Map var6 = (Map)var5;
                  if (!var6.isEmpty()) {
                     String var8 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var6, "text"))}).toLowerCase(Locale.ROOT);
                     String var9 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var6, "id"))}).toLowerCase(Locale.ROOT);
                     if (!var8.isBlank() && (var8.contains("<font:uiimages>") || var8.contains("%img_")) || !var9.isBlank() && var9.startsWith("img_")) {
                        return true;
                     }
                  }
               }
            }

            return false;
         }

         return false;
      } else {
         return false;
      }
   }

   protected boolean isImageGroupRoot(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         String var2;
         boolean var10000;
         label27: {
            var2 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "id"))}).toLowerCase(Locale.ROOT);
            if (this.readMapPathValue(var1, "children") instanceof List var5 && !var5.isEmpty()) {
               var10000 = true;
               break label27;
            }

            var10000 = false;
         }

         boolean var4 = var10000;
         if (!var4) {
            return false;
         } else if (var2.startsWith("img_")) {
            return true;
         } else {
            String var6 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "type"))}).toLowerCase(Locale.ROOT);
            return !"hitbox".equals(var6) ? false : this.hasDirectImageGroupGlyphChild(var1);
         }
      } else {
         return false;
      }
   }

   protected EditorRect resolveTargetBoundsFromRaw(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank() && var1.renderBlocks != null && !var1.renderBlocks.isEmpty()) {
         EditorRect var3 = null;

         for (Map var5 : var1.renderBlocks) {
            if (var5 != null && !var5.isEmpty()) {
               String var6 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var5, "__editor_target_id"))});
               if (var2.equals(var6)) {
                  String var7 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var5, "__editor_target_path"))});
                  if (!var7.isBlank()) {
                     Map var8 = this.resolveRawMapAtPath(var1.rawBlocks, var7);
                     if (var8 != null && !var8.isEmpty()) {
                        double var9 = this.readMapPathDouble(var8, "position.x", this.readMapPathDouble(var8, "x", 0.0));
                        double var11 = this.readMapPathDouble(var8, "position.y", this.readMapPathDouble(var8, "y", 0.0));
                        Vector var13 = this.resolveParentAbsoluteOffset(var1, var7);
                        var9 += var13.getX();
                        var11 += var13.getY();
                        double var14 = this.readMapPathDouble(
                           var8, "size.width", this.readMapPathDouble(var8, "width", this.readMapPathDouble(var8, "scale.width", 1.0))
                        );
                        double var16 = this.readMapPathDouble(
                           var8, "size.height", this.readMapPathDouble(var8, "height", this.readMapPathDouble(var8, "scale.height", 1.0))
                        );
                        double var18 = Math.max(1.0, Math.abs(var14));
                        double var20 = Math.max(1.0, Math.abs(var16));
                        String var22 = this.firstNonBlank(
                              new String[]{this.stringValue(this.readMapPathValue(var8, "type")), this.stringValue(this.readMapPathValue(var5, "type"))}
                           )
                           .toLowerCase(Locale.ROOT);
                        if ("text".equals(var22)) {
                           double var23 = Math.max(1.0, var20 * 0.11666666666666667);
                           var11 += var20 - var23;
                           var20 = var23;
                        }

                        var3 = this.mergeBounds(var3, new EditorRect(var9, var11, var18, var20));
                     }
                  }
               }
            }
         }

         return var3;
      } else {
         return null;
      }
   }

   protected EditorRect resolveTargetBoundsFromRawByPathPrefix(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank() && var1.renderBlocks != null && !var1.renderBlocks.isEmpty()) {
         String var3 = var2 + ".children.";
         EditorRect var4 = null;

         for (Map var6 : var1.renderBlocks) {
            if (var6 != null && !var6.isEmpty()) {
               String var7 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var6, "__editor_target_path"))});
               if (!var7.isBlank() && (var2.equals(var7) || var7.startsWith(var3))) {
                  Map var8 = this.resolveRawMapAtPath(var1.rawBlocks, var7);
                  if (var8 != null && !var8.isEmpty()) {
                     double var9 = this.readMapPathDouble(var8, "position.x", this.readMapPathDouble(var8, "x", 0.0));
                     double var11 = this.readMapPathDouble(var8, "position.y", this.readMapPathDouble(var8, "y", 0.0));
                     Vector var13 = this.resolveParentAbsoluteOffset(var1, var7);
                     var9 += var13.getX();
                     var11 += var13.getY();
                     double var14 = this.readMapPathDouble(
                        var8, "size.width", this.readMapPathDouble(var8, "width", this.readMapPathDouble(var8, "scale.width", 1.0))
                     );
                     double var16 = this.readMapPathDouble(
                        var8, "size.height", this.readMapPathDouble(var8, "height", this.readMapPathDouble(var8, "scale.height", 1.0))
                     );
                     double var18 = Math.max(1.0, Math.abs(var14));
                     double var20 = Math.max(1.0, Math.abs(var16));
                     String var22 = this.firstNonBlank(
                           new String[]{this.stringValue(this.readMapPathValue(var8, "type")), this.stringValue(this.readMapPathValue(var6, "type"))}
                        )
                        .toLowerCase(Locale.ROOT);
                     if ("text".equals(var22)) {
                        double var23 = Math.max(1.0, var20 * 0.11666666666666667);
                        var11 += var20 - var23;
                        var20 = var23;
                     }

                     var4 = this.mergeBounds(var4, new EditorRect(var9, var11, var18, var20));
                  }
               }
            }
         }

         return var4;
      } else {
         return null;
      }
   }

   protected EditorRect getSelectedBounds(EditorSession var1) {
      EditorRect var2 = null;
      String var3 = null;

      for (String var5 : this.getSelectedTargetIds(var1)) {
         EditorRect var6 = this.getTargetBounds(var1, var5);
         if (var3 == null && var5 != null && !var5.isBlank()) {
            var3 = var5;
         }

         this.logAnimationOutlineBoundsSource(var1, var5, "selected-target", var6);
         var2 = this.mergeBounds(var2, var6);
      }

      this.logAnimationOutlineBoundsSource(var1, this.firstNonBlank(new String[]{var3}), "selected-merged", var2);
      return var2;
   }

   protected EditorRect resizeRect(EditorSession var1, EditorRect var2, String var3, double var4, double var6, boolean var8) {
      if (var2 != null && var3 != null && !var3.isBlank()) {
         String var9 = this.normalizeCornerHandleAlias(var3);
         double var10 = var4 - var1.resizeStartCursorX;
         double var12 = var6 - var1.resizeStartCursorY;
         boolean var14 = "tl".equals(var9) || "tr".equals(var9) || "bl".equals(var9) || "br".equals(var9);
         boolean var15 = "w".equals(var9) || "tl".equals(var9) || "bl".equals(var9);
         boolean var16 = "e".equals(var9) || "tr".equals(var9) || "br".equals(var9);
         boolean var17 = "n".equals(var9) || "tl".equals(var9) || "tr".equals(var9);
         boolean var18 = "s".equals(var9) || "bl".equals(var9) || "br".equals(var9);
         double var19 = var2.x;
         double var21 = var2.y;
         double var23 = var2.width;
         double var25 = var2.height;
         double var27 = var19;
         double var29 = var21;
         double var31 = var19 + var23;
         double var33 = var21 + var25;
         switch (var9) {
            case "n":
               var21 += var12;
               var25 -= var12;
               break;
            case "s":
               var25 += var12;
               break;
            case "w":
               var19 += var10;
               var23 -= var10;
               break;
            case "e":
               var23 += var10;
               break;
            case "tl":
               var19 += var10;
               var23 -= var10;
               var21 += var12;
               var25 -= var12;
               break;
            case "tr":
               var23 += var10;
               var21 += var12;
               var25 -= var12;
               break;
            case "bl":
               var19 += var10;
               var23 -= var10;
               var25 += var12;
               break;
            case "br":
               var23 += var10;
               var25 += var12;
         }

         if (var23 < 0.0) {
            if (var15) {
               var19 += var23;
            }

            var23 = 0.0;
         }

         if (var25 < 0.0) {
            if (var17) {
               var21 += var25;
            }

            var25 = 0.0;
         }

         if (var8 && var14) {
            EditorRect var35 = this.createProportionalCornerRect(var1, var2, var9, var27, var29, var31, var33, var23, var25, false);
            var19 = var35.x;
            var21 = var35.y;
            var23 = var35.width;
            var25 = var35.height;
         }

         if (var15) {
            var19 = this.snapToGrid(var1, var19);
            var23 = Math.max(0.0, this.snapToGrid(var1, var31) - var19);
         } else if (var16) {
            var19 = this.snapToGrid(var1, var27);
            var23 = Math.max(0.0, this.snapToGrid(var1, var19 + var23) - var19);
         } else {
            var19 = this.snapToGrid(var1, var19);
            var23 = Math.max(0.0, this.snapToGrid(var1, var23));
         }

         if (var17) {
            var21 = this.snapToGrid(var1, var21);
            var25 = Math.max(0.0, this.snapToGrid(var1, var33) - var21);
         } else if (var18) {
            var21 = this.snapToGrid(var1, var29);
            var25 = Math.max(0.0, this.snapToGrid(var1, var21 + var25) - var21);
         } else {
            var21 = this.snapToGrid(var1, var21);
            var25 = Math.max(0.0, this.snapToGrid(var1, var25));
         }

         if (var8 && var14) {
            EditorRect var41 = this.createProportionalCornerRect(var1, var2, var9, var27, var29, var31, var33, var23, var25, true);
            var19 = var41.x;
            var21 = var41.y;
            var23 = var41.width;
            var25 = var41.height;
         }

         return new EditorRect(var19, var21, var23, var25);
      } else {
         return var2;
      }
   }

   protected EditorRect createProportionalCornerRect(
      EditorSession var1, EditorRect var2, String var3, double var4, double var6, double var8, double var10, double var12, double var14, boolean var16
   ) {
      String var17 = this.normalizeCornerHandleAlias(var3);
      double var18 = this.resolveProportionalScaleFactor(var2, var12, var14);
      double var20 = Math.max(0.0, var2.width * var18);
      double var22 = Math.max(0.0, var2.height * var18);
      if (var16) {
         var20 = Math.max(0.0, this.snapToGrid(var1, var20));
         var22 = Math.max(0.0, this.snapToGrid(var1, var22));
      }

      double var24;
      double var26;
      if (var16) {
         double var28 = this.snapToGrid(var1, var4);
         double var30 = this.snapToGrid(var1, var6);
         double var32 = this.snapToGrid(var1, var8);
         double var34 = this.snapToGrid(var1, var10);
         switch (var17) {
            case "tl":
               var24 = var32 - var20;
               var26 = var34 - var22;
               break;
            case "tr":
               var24 = var28;
               var26 = var34 - var22;
               break;
            case "bl":
               var24 = var32 - var20;
               var26 = var30;
               break;
            case "br":
               var24 = var28;
               var26 = var30;
               break;
            default:
               var24 = var28;
               var26 = var30;
         }
      } else {
         switch (var17) {
            case "tl":
               var24 = var8 - var20;
               var26 = var10 - var22;
               break;
            case "tr":
               var24 = var4;
               var26 = var10 - var22;
               break;
            case "bl":
               var24 = var8 - var20;
               var26 = var6;
               break;
            case "br":
               var24 = var4;
               var26 = var6;
               break;
            default:
               var24 = var4;
               var26 = var6;
         }
      }

      return new EditorRect(var24, var26, var20, var22);
   }

   protected String normalizeCornerHandleAlias(String var1) {
      if (var1 != null && !var1.isBlank()) {
         return switch (var1) {
            case "nw" -> "tl";
            case "ne" -> "tr";
            case "sw" -> "bl";
            case "se" -> "br";
            default -> var1;
         };
      } else {
         return var1;
      }
   }

   protected double resolveProportionalScaleFactor(EditorRect var1, double var2, double var4) {
      double var6 = var1.width <= 1.0E-4 ? 1.0 : var2 / var1.width;
      double var8 = var1.height <= 1.0E-4 ? 1.0 : var4 / var1.height;
      double var10 = Math.abs(var6 - 1.0);
      double var12 = Math.abs(var8 - 1.0);
      double var14 = var10 >= var12 ? var6 : var8;
      return !Double.isFinite(var14) ? 1.0 : Math.max(0.0, var14);
   }

   protected EditorRect transformBounds(EditorSession var1, EditorRect var2, EditorRect var3, EditorRect var4) {
      if (var2 != null && var3 != null && var4 != null) {
         double var5;
         double var7;
         if (var3.width <= 1.0E-4) {
            var5 = var4.x;
            var7 = var4.x + var2.width;
         } else {
            double var9 = (var2.x - var3.x) / var3.width;
            double var11 = (var2.maxX() - var3.x) / var3.width;
            var5 = var4.x + var9 * var4.width;
            var7 = var4.x + var11 * var4.width;
         }

         double var21;
         double var22;
         if (var3.height <= 1.0E-4) {
            var21 = var4.y;
            var22 = var4.y + var2.height;
         } else {
            double var13 = (var2.y - var3.y) / var3.height;
            double var15 = (var2.maxY() - var3.y) / var3.height;
            var21 = var4.y + var13 * var4.height;
            var22 = var4.y + var15 * var4.height;
         }

         double var23 = this.snapToGrid(var1, var5);
         double var24 = this.snapToGrid(var1, var21);
         double var17 = this.snapToGrid(var1, var7);
         double var19 = this.snapToGrid(var1, var22);
         return new EditorRect(var23, var24, Math.max(0.0, var17 - var23), Math.max(0.0, var19 - var24));
      } else {
         return var2;
      }
   }

   @Override
   protected void applyBoundsToTarget(EditorSession var1, String var2, EditorRect var3) {
      HoverElement var4 = this.findFirstByTargetId(var1, var2);
      if (var4 != null && var4.targetPath != null && !var4.targetPath.isBlank() && var3 != null) {
         String var5 = this.firstNonBlank(new String[]{this.resolveInheritedTargetRootPath(var1, var2)});
         String var6 = this.firstNonBlank(new String[]{var5, var4.targetPath});
         boolean var7 = var1 != null && var1.activeTool == EditorTool.ANIMATION;
         EditorRect var8 = var3;
         Map var9 = this.resolveRawMapAtPath(var1.rawBlocks, var6);
         if (var9 != null) {
            boolean var10 = this.isImageGroupRoot(var9);
            if (var7) {
               var8 = new EditorRect(this.snap1(var3.x), this.snap1(var3.y), Math.max(0.0, var3.width), Math.max(0.0, var3.height));
            }

            EditorRect var11 = var10 ? this.convertImageGroupOutlineToRootBounds(var9, var8) : var8;
            Vector var12 = this.resolveParentAbsoluteOffset(var1, var6);
            double var13 = var11.x - var12.getX();
            double var15 = var11.y - var12.getY();
            if (var7) {
               var13 = this.snap1(var13);
               var15 = this.snap1(var15);
            }

            if (this.shouldInheritTargetToChildren(var9)) {
               double var52 = this.resolveStoredSign(var9, new String[]{"size.width", "width"});
               double var54 = this.resolveStoredSign(var9, new String[]{"size.height", "height"});
               double var56 = var11.width;
               double var58 = var11.height;
               if (var10) {
                  var56 = Math.max(8.0, var56);
                  var58 = Math.max(8.0, var58);
               } else {
                  var56 = Math.max(1.0, var56);
                  var58 = Math.max(1.0, var58);
               }

               if (var10) {
                  double var25 = this.resolveImageGroupOutlineToRootYOffset(var9, var58);
                  var11 = new EditorRect(var11.x, var8.y + var25, var56, var58);
                  var13 = var11.x - var12.getX();
                  var15 = var11.y - var12.getY();
                  if (var7) {
                     var13 = this.snap1(var13);
                     var15 = this.snap1(var15);
                  }
               }

               boolean var60 = var1 != null && var1.resizeAnchorValid && var1.activeHandle != null;
               EditorRect var26 = var60 ? var1.resizeInheritedRootStartBoundsByPath.get(var6) : null;
               Object var27 = var60 ? var1.resizeInheritedChildrenSnapshotByPath.get(var6) : null;
               double var28 = var10 ? 1.0E-4 : 1.0;
               double var30;
               double var32;
               if (var26 != null && var27 != null) {
                  var30 = Math.max(var28, var26.width);
                  var32 = Math.max(var28, var26.height);
               } else {
                  EditorRect var34 = this.resolveSidebarEditableBounds(var1, var2);
                  var30 = var34 == null
                     ? Math.max(var28, Math.abs(this.readMapPathDouble(var9, "size.width", this.readMapPathDouble(var9, "width", var28))))
                     : Math.max(var28, var34.width);
                  var32 = var34 == null
                     ? Math.max(var28, Math.abs(this.readMapPathDouble(var9, "size.height", this.readMapPathDouble(var9, "height", var28))))
                     : Math.max(var28, var34.height);
               }

               double var61 = var56 / var30;
               double var36 = var58 / var32;
               if (Double.isFinite(var61) && Double.isFinite(var36) && (Math.abs(var61 - 1.0) > 1.0E-5 || Math.abs(var36 - 1.0) > 1.0E-5)) {
                  boolean var38 = var7 && !var10;
                  Object var39 = var9.get("children");
                  if (var26 != null && var27 != null) {
                     Object var40 = this.deepCopyValue(var27);
                     var9.put("children", var40);
                     var39 = var40;
                  }

                  if (var10) {
                     this.unshiftImageGroupFramesVertically(var39);
                  }

                  this.scaleChildBoundsRecursively(var39, var61, var36, var38);
                  if (var10) {
                     double[] var63 = this.resolveImageGroupTopTile(var9.get("children"));
                     if (var63 != null) {
                        double var41 = var63[0];
                        double var43 = var63[1];
                        double var45 = Double.isFinite(var43) ? IMAGE_GLYPH_SCALE_DRIFT_FACTOR * (var43 - 64.0) : 0.0;
                        double var47 = var45 - var41;
                        if (Double.isFinite(var47) && Math.abs(var47) > 1.0E-5) {
                           this.shiftImageGroupFramesVertically(var9.get("children"), var47, var7);
                        }
                     }

                     double[] var65 = this.resolveImageGroupLeftTile(var9.get("children"));
                     if (var65 != null) {
                        double var42 = var65[0];
                        double var44 = var65[1];
                        double var46 = var44 * 0.45281250000000006 - 29.0;
                        var46 -= this.resolveImageGroupSingleTilePartialShiftX(var56, var44);
                        double var48 = var46 - var42;
                        if (Double.isFinite(var48) && Math.abs(var48) > 1.0E-5) {
                           this.shiftImageGroupFramesHorizontally(var9.get("children"), var48, var7);
                        }
                     }
                  }
               }

               this.setNested(var9, "position", "x", var13);
               this.setNested(var9, "position", "y", var15);
               double var62 = var56 * var52;
               double var64 = var58 * var54;
               if (var7 && !var10) {
                  var62 = this.snap1(var62);
                  var64 = this.snap1(var64);
               }

               this.setNested(var9, "size", "width", var62);
               this.setNested(var9, "size", "height", var64);
               var9.put("layer", var4.z);
            } else if (!"component".equalsIgnoreCase(var4.targetKind)) {
               double var51 = this.resolveStoredSign(var9, new String[]{"size.width", "width"});
               double var53 = this.resolveStoredSign(var9, new String[]{"size.height", "height"});
               this.setNested(var9, "position", "x", var13);
               if ("text".equals(var4.type)) {
                  double var55 = Math.max(1.0E-4, 0.11666666666666667);
                  double var23 = var8.height / var55;
                  if (var7) {
                     var23 = this.snap1(var23);
                  }

                  this.setNested(var9, "position", "y", var15);
                  this.setNested(var9, "size", "width", var7 ? this.snap1(var8.width * var51) : var8.width * var51);
                  this.setNested(var9, "size", "height", var7 ? this.snap1(var23 * var53) : var23 * var53);
               } else {
                  this.setNested(var9, "position", "y", var15);
                  this.setNested(var9, "size", "width", var7 ? this.snap1(var8.width * var51) : var8.width * var51);
                  this.setNested(var9, "size", "height", var7 ? this.snap1(var8.height * var53) : var8.height * var53);
               }

               var9.put("layer", var4.z);
            } else {
               double var17 = this.resolveStoredSign(var9, new String[]{var4.bindingWidth});
               double var19 = this.resolveStoredSign(var9, new String[]{var4.bindingHeight});
               boolean var21 = var4.bindingX != null && !var4.bindingX.isBlank();
               boolean var22 = var4.bindingY != null && !var4.bindingY.isBlank();
               this.setBindingValue(var9, var4.bindingX, var13);
               this.setBindingValue(var9, var4.bindingY, var15);
               this.setBindingValue(var9, var4.bindingWidth, var7 ? this.snap1(var8.width * var17) : var8.width * var17);
               this.setBindingValue(var9, var4.bindingHeight, var7 ? this.snap1(var8.height * var19) : var8.height * var19);
               if (!var21) {
                  var9.put("__editor_move_x", var8.x);
               }

               if (!var22) {
                  var9.put("__editor_move_y", var8.y);
               }
            }
         }
      }
   }

   protected void scaleChildBoundsRecursively(Object var1, double var2, double var4, boolean var6) {
      if (var1 instanceof List var7 && !var7.isEmpty()) {
         for (Object var9 : var7) {
            if (var9 instanceof Map) {
               Map var10 = (Map)var9;
               if (!var10.isEmpty()) {
                  double var12 = this.readMapPathDouble(var10, "position.x", this.readMapPathDouble(var10, "x", 0.0));
                  double var14 = this.readMapPathDouble(var10, "position.y", this.readMapPathDouble(var10, "y", 0.0));
                  double var16 = this.readMapPathDouble(
                     var10, "size.width", this.readMapPathDouble(var10, "width", this.readMapPathDouble(var10, "scale.width", 1.0))
                  );
                  double var18 = this.readMapPathDouble(
                     var10, "size.height", this.readMapPathDouble(var10, "height", this.readMapPathDouble(var10, "scale.height", 1.0))
                  );
                  double var20 = this.resolveStoredSign(var10, new String[]{"size.width", "width"});
                  double var22 = this.resolveStoredSign(var10, new String[]{"size.height", "height"});
                  double var24 = var12 * var2;
                  double var26 = var14 * var4;
                  double var28 = Math.max(1.0E-9, Math.abs(var16) * Math.abs(var2));
                  double var30 = Math.max(1.0E-9, Math.abs(var18) * Math.abs(var4));
                  if (var6) {
                     var24 = this.snap1(var24);
                     var26 = this.snap1(var26);
                  }

                  this.setNested(var10, "position", "x", var24);
                  this.setNested(var10, "position", "y", var26);
                  this.setNested(var10, "size", "width", var6 ? this.snap1(var28 * var20) : var28 * var20);
                  this.setNested(var10, "size", "height", var6 ? this.snap1(var30 * var22) : var30 * var22);
                  Object var32 = var10.get("children");
                  if (var32 instanceof List) {
                     this.scaleChildBoundsRecursively(var32, var2, var4, var6);
                  }
               }
            }
         }

         return;
      }
   }

   protected void shiftImageGroupFramesVertically(Object var1, double var2, boolean var4) {
      if (var1 instanceof List var5 && !var5.isEmpty()) {
         for (Object var7 : var5) {
            if (var7 instanceof Map) {
               Map var8 = (Map)var7;
               if (!var8.isEmpty()) {
                  double var10 = this.readMapPathDouble(var8, "position.y", this.readMapPathDouble(var8, "y", 0.0));
                  double var12 = this.readMapPathDouble(var8, "__editor_frame_shift_y", 0.0);
                  if (!Double.isFinite(var12)) {
                     var12 = 0.0;
                  }

                  double var14 = var10 + var2;
                  double var16 = var12 + var2;
                  if (var4) {
                     var14 = this.snap1(var14);
                     var16 = this.snap1(var16);
                  }

                  this.setNested(var8, "position", "y", var14);
                  var8.put("__editor_frame_shift_y", var16);
               }
            }
         }

         return;
      }
   }

   protected double[] resolveImageGroupBottomTile(Object var1) {
      if (var1 instanceof List var2 && !var2.isEmpty()) {
         double var3 = Double.NEGATIVE_INFINITY;
         double var5 = Double.NaN;

         for (Object var8 : var2) {
            if (var8 instanceof Map) {
               Map var9 = (Map)var8;
               if (!var9.isEmpty()) {
                  double var11 = this.readMapPathDouble(var9, "position.y", this.readMapPathDouble(var9, "y", Double.NaN));
                  if (Double.isFinite(var11)) {
                     double var13 = Math.abs(this.readMapPathDouble(var9, "size.height", this.readMapPathDouble(var9, "height", Double.NaN)));
                     if (var11 > var3) {
                        var3 = var11;
                        var5 = var13;
                     }
                  }
               }
            }
         }

         if (Double.isFinite(var3) && Double.isFinite(var5)) {
            return new double[]{var3, var5};
         }

         return null;
      }

      return null;
   }

   protected int countImageGroupGlyphTiles(Object var1) {
      if (var1 instanceof List var2 && !var2.isEmpty()) {
         int var3 = 0;

         for (Object var5 : var2) {
            if (var5 instanceof Map) {
               Map var6 = (Map)var5;
               Object var7 = var6.get("text");
               if (var7 != null && var7.toString().contains("uiimages")) {
                  var3++;
               }
            }
         }

         return var3;
      }

      return 0;
   }

   protected double resolveImageGroupSingleTilePartialShiftX(double var1, double var3) {
      if (var1 > 0.0 && var3 > 0.0) {
         double var5 = var1 * 64.0 / var3;
         double var7 = Math.min(IMAGE_PARTIAL_GLYPH_X_CAP, Math.max(0.0, IMAGE_PARTIAL_GLYPH_X_SLOPE * (256.0 - var5)));
         return var7 * var3 / 64.0;
      } else {
         return 0.0;
      }
   }

   protected void applyImageGroupSingleTilePartialCorrection(Object var1, double var2, double var4) {
      if (var1 instanceof List var6) {
         double var7 = Double.POSITIVE_INFINITY;
         double var9 = Double.NaN;
         boolean var11 = false;

         for (Object var13 : var6) {
            if (var13 instanceof Map) {
               Map var14 = (Map)var13;
               Object var15 = var14.get("text");
               if (var15 != null && var15.toString().contains("uiimages")) {
                  double var17 = this.readMapPathDouble(var14, "position.x", this.readMapPathDouble(var14, "x", Double.NaN));
                  if (Double.isFinite(var17)) {
                     if (var17 < var7) {
                        var7 = var17;
                        var9 = Math.abs(this.readMapPathDouble(var14, "size.width", this.readMapPathDouble(var14, "width", 64.0)));
                     }

                     var11 = true;
                  }
               }
            }
         }

         if (var11 && Double.isFinite(var9)) {
            double var21 = this.resolveImageGroupSingleTilePartialShiftX(var2, var9);
            if (!(Math.abs(var21) < 1.0E-5)) {
               for (Object var23 : var6) {
                  if (var23 instanceof Map) {
                     Map var16 = (Map)var23;
                     Object var18 = var16.get("text");
                     if (var18 != null && var18.toString().contains("uiimages")) {
                        double var19 = this.readMapPathDouble(var16, "position.x", this.readMapPathDouble(var16, "x", Double.NaN));
                        if (Double.isFinite(var19)) {
                           this.setNested(var16, "position", "x", var19 - var21);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected double[] resolveImageGroupTopTile(Object var1) {
      if (var1 instanceof List var2 && !var2.isEmpty()) {
         double var3 = Double.POSITIVE_INFINITY;
         double var5 = Double.NaN;

         for (Object var8 : var2) {
            if (var8 instanceof Map) {
               Map var9 = (Map)var8;
               if (!var9.isEmpty()) {
                  double var11 = this.readMapPathDouble(var9, "position.y", this.readMapPathDouble(var9, "y", Double.NaN));
                  if (Double.isFinite(var11)) {
                     double var13 = Math.abs(this.readMapPathDouble(var9, "size.height", this.readMapPathDouble(var9, "height", Double.NaN)));
                     if (var11 < var3) {
                        var3 = var11;
                        var5 = var13;
                     }
                  }
               }
            }
         }

         if (!Double.isFinite(var3)) {
            return null;
         }

         return new double[]{var3, var5};
      }

      return null;
   }

   protected double[] resolveImageGroupLeftTile(Object var1) {
      if (var1 instanceof List var2 && !var2.isEmpty()) {
         double var3 = Double.POSITIVE_INFINITY;
         double var5 = Double.NaN;

         for (Object var8 : var2) {
            if (var8 instanceof Map) {
               Map var9 = (Map)var8;
               if (!var9.isEmpty()) {
                  double var11 = this.readMapPathDouble(var9, "position.x", this.readMapPathDouble(var9, "x", Double.NaN));
                  if (Double.isFinite(var11)) {
                     double var13 = Math.abs(this.readMapPathDouble(var9, "size.width", this.readMapPathDouble(var9, "width", Double.NaN)));
                     if (var11 < var3) {
                        var3 = var11;
                        var5 = var13;
                     }
                  }
               }
            }
         }

         if (Double.isFinite(var3) && Double.isFinite(var5)) {
            return new double[]{var3, var5};
         }

         return null;
      }

      return null;
   }

   protected double readImageGroupChildrenShiftY(Object var1) {
      if (var1 instanceof List var2 && !var2.isEmpty()) {
         for (Object var4 : var2) {
            if (var4 instanceof Map) {
               Map var5 = (Map)var4;
               if (!var5.isEmpty() && var5.containsKey("__editor_frame_shift_y")) {
                  double var7 = this.readMapPathDouble(var5, "__editor_frame_shift_y", 0.0);
                  if (Double.isFinite(var7)) {
                     return var7;
                  }
               }
            }
         }

         return 0.0;
      }

      return 0.0;
   }

   protected void unshiftImageGroupFramesVertically(Object var1) {
      if (var1 instanceof List var2 && !var2.isEmpty()) {
         for (Object var4 : var2) {
            if (var4 instanceof Map) {
               Map var5 = (Map)var4;
               if (!var5.isEmpty()) {
                  double var7 = this.readMapPathDouble(var5, "__editor_frame_shift_y", 0.0);
                  if (Double.isFinite(var7) && !(Math.abs(var7) < 1.0E-5)) {
                     double var9 = this.readMapPathDouble(var5, "position.y", this.readMapPathDouble(var5, "y", 0.0));
                     this.setNested(var5, "position", "y", var9 - var7);
                     var5.remove("__editor_frame_shift_y");
                  } else {
                     var5.remove("__editor_frame_shift_y");
                  }
               }
            }
         }

         return;
      }
   }

   protected void shiftImageGroupFramesHorizontally(Object var1, double var2, boolean var4) {
      if (var1 instanceof List var5 && !var5.isEmpty()) {
         for (Object var7 : var5) {
            if (var7 instanceof Map) {
               Map var8 = (Map)var7;
               if (!var8.isEmpty()) {
                  double var10 = this.readMapPathDouble(var8, "position.x", this.readMapPathDouble(var8, "x", 0.0));
                  double var12 = var10 + var2;
                  if (var4) {
                     var12 = this.snap1(var12);
                  }

                  this.setNested(var8, "position", "x", var12);
               }
            }
         }

         return;
      }
   }

   protected EditorRect resolveImageGroupChildrenVisualBounds(Map<String, Object> var1, double var2, double var4) {
      if (var1 != null && !var1.isEmpty()) {
         if (var1.get("children") instanceof List var7 && !var7.isEmpty()) {
            EditorRect var8 = null;

            for (Object var10 : var7) {
               if (var10 instanceof Map) {
                  Map var11 = (Map)var10;
                  if (!var11.isEmpty()) {
                     double var13 = this.readMapPathDouble(var11, "position.x", this.readMapPathDouble(var11, "x", Double.NaN));
                     double var15 = this.readMapPathDouble(var11, "position.y", this.readMapPathDouble(var11, "y", Double.NaN));
                     if (Double.isFinite(var13) && Double.isFinite(var15)) {
                        double var17 = this.readMapPathDouble(
                           var11, "size.width", this.readMapPathDouble(var11, "width", this.readMapPathDouble(var11, "scale.width", 1.0E-9))
                        );
                        double var19 = this.readMapPathDouble(
                           var11, "size.height", this.readMapPathDouble(var11, "height", this.readMapPathDouble(var11, "scale.height", 1.0E-9))
                        );
                        double var21 = Math.max(1.0E-9, Math.abs(var17));
                        double var23 = Math.max(1.0E-9, Math.abs(var19));
                        EditorRect var25 = new EditorRect(var2 + var13, var4 + var15, var21, var23);
                        var8 = this.mergeBounds(var8, var25);
                     }
                  }
               }
            }

            return var8;
         }

         return null;
      } else {
         return null;
      }
   }

   protected int countImageGroupFrameRows(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         if (var1.get("children") instanceof List var3 && !var3.isEmpty()) {
            HashSet var4 = new HashSet();

            for (Object var6 : var3) {
               if (var6 instanceof Map) {
                  Map var7 = (Map)var6;
                  if (!var7.isEmpty()) {
                     double var9 = this.readMapPathDouble(var7, "position.y", this.readMapPathDouble(var7, "y", Double.NaN));
                     if (Double.isFinite(var9)) {
                        double var11 = this.readMapPathDouble(var7, "__editor_frame_shift_y", 0.0);
                        if (!Double.isFinite(var11)) {
                           var11 = 0.0;
                        }

                        double var13 = var9 - var11;
                        if (Double.isFinite(var13)) {
                           var4.add(this.normalizeImageGroupFrameCoordinate(var13));
                        }
                     }
                  }
               }
            }

            return Math.max(1, var4.size());
         }

         return 1;
      } else {
         return 1;
      }
   }

   protected Vector resolveParentAbsoluteOffset(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         double var3 = 0.0;
         double var5 = 0.0;
         String var7 = var2;

         while (true) {
            int var8 = var7.lastIndexOf(".children.");
            if (var8 < 0) {
               break;
            }

            String var9 = var7.substring(0, var8);
            if (var9.isBlank()) {
               break;
            }

            Map var10 = this.resolveRawMapAtPath(var1.rawBlocks, var9);
            if (var10 != null) {
               var3 += this.readMapPathDouble(var10, "position.x", this.readMapPathDouble(var10, "x", 0.0));
               var5 += this.readMapPathDouble(var10, "position.y", this.readMapPathDouble(var10, "y", 0.0));
            }

            var7 = var9;
         }

         return new Vector(var3, var5, 0.0);
      } else {
         return new Vector(0.0, 0.0, 0.0);
      }
   }

   @Override
   protected void rerenderEditableContent(Player var1, EditorSession var2) {
      if (var2 != null) {
         boolean var3 = !this.isTransformDragInProgress(var2);
         boolean var4 = this.isSidebarFieldDragInProgress(var2);
         var2.renderBlocks = this.resolveRenderableBlocks(var2.rawBlocks, var2.components);
         this.renderResolvedContent(var1, var2);
         if (var2.activeHandle != null) {
            if (var2.selectedElementId != null && var2.selectionOutlineVisible) {
               this.renderSelectionOverlay(var1, var2);
            }

            if (var3) {
               this.updateEditorPropertiesSidebar(var1, var2);
            }
         } else {
            if (!var4 || var2.selectedElementId == null || !var2.selectionOutlineVisible) {
               this.clearEditorOverlaysOnly(var1, var2);
            }

            if (var2.selectedElementId != null && var2.selectionOutlineVisible) {
               this.renderSelectionOverlay(var1, var2);
            }

            if (var3) {
               this.updateEditorPropertiesSidebar(var1, var2);
            }

            this.updatePageInfoReadout(var1, var2);
         }
      }
   }

   @Override
   protected void rerenderEditableSelection(Player var1, EditorSession var2) {
      if (var2 != null) {
         boolean var3 = !this.isTransformDragInProgress(var2);
         boolean var4 = this.isSidebarFieldDragInProgress(var2);
         EditorRect var5 = this.getSelectedBounds(var2);
         String var6 = this.resolveSelectionOutlineColor(var2);
         List var7 = this.getSelectedTargetIds(var2);
         if (var7.isEmpty()) {
            this.rerenderEditableContent(var1, var2);
         } else {
            var2.renderBlocks = this.resolveRenderableBlocks(var2.rawBlocks, var2.components);
            ArrayList var8 = new ArrayList<>(var2.elements);
            HashMap var9 = new HashMap();

            for (Object var11_raw : var8) {
               HoverElement var11 = (HoverElement)var11_raw;
               var9.put(var11.id, var11);
            }

            LinkedHashSet var25 = new LinkedHashSet(var7);
            LinkedHashSet var26 = new LinkedHashSet();

            for (Object var13_raw : var7) {
               String var13 = var13_raw != null ? var13_raw.toString() : null;
               HoverElement var14 = this.findFirstByTargetId(var2, var13);
               String var15 = var14 == null ? "" : this.firstNonBlank(new String[]{var14.targetPath});
               if (!var15.isBlank()) {
                  var26.add(var15);
               }

               var25.addAll(this.resolveDescendantLayerTargetIds(var2, var13));
            }

            HashSet var27 = new HashSet();
            ArrayList var28 = new ArrayList();
            HashMap var29 = new HashMap();
            int var30 = 0;

            for (Map var17 : var2.renderBlocks) {
               var30++;
               ConfigurationSection var18 = this.mapToSection(var17);
               if (var18 != null) {
                  String var19 = this.firstNonBlank(new String[]{var18.getString("type"), "block"}).toLowerCase(Locale.ROOT);
                  if (this.isRenderableBlockType(var19)) {
                     String var20 = this.resolveElementId(var18, var30, var29);
                     String var21 = this.runtimeElementId(var2, var20);
                     String var22 = this.firstNonBlank(new String[]{var18.getString("__editor_target_id"), var21});
                     String var23 = this.firstNonBlank(new String[]{var18.getString("__editor_target_path")});
                     if (!this.isTargetVisible(var2, var22)) {
                        HoverElement var38 = (HoverElement)var9.get(var21);
                        if (var38 != null) {
                           this.removeRenderedElementHud(var1, var38);
                        }
                     } else {
                        var27.add(var21);
                        if (!var25.contains(var22) && !this.isDescendantOfAnyTargetPath(var23, var26)) {
                           HoverElement var24 = (HoverElement)var9.get(var21);
                           if (var24 != null) {
                              var28.add(var24);
                           } else {
                              var28.add(this.renderResolvedElement(var1, var2, var18, var21, var19));
                           }
                        } else {
                           var28.add(this.renderResolvedElement(var1, var2, var18, var21, var19));
                        }
                     }
                  }
               }
            }

            for (Object var33_raw : var8) {
               HoverElement var33 = (HoverElement)var33_raw;
               if (var33 != null && var33.id != null && !var27.contains(var33.id)) {
                  this.removeRenderedElementHud(var1, var33);
               }
            }

            var2.elements.clear();
            var2.elements.addAll(var28);
            this.normalizeSelectionState(var2);
            EditorRect var32 = this.getSelectedBounds(var2);
            String var34 = this.resolveSelectionOutlineColor(var2);
            boolean var35 = var5 != null && var32 != null && this.sameRect(var5, var32);
            boolean var36 = !var6.equals(var34);
            if (var2.selectedElementId != null && var2.selectionOutlineVisible && var35 && var36) {
               this.refreshSelectionOutlineColorOnly(var1, var2);
            }

            boolean var37 = var2.selectedElementId != null && var2.selectionOutlineVisible && var35;
            if (var2.activeHandle != null) {
               if (var2.selectedElementId != null && var2.selectionOutlineVisible) {
                  this.renderSelectionOverlay(var1, var2);
               }

               if (var3) {
                  this.updateEditorPropertiesSidebar(var1, var2);
               }
            } else {
               if ((!var4 || var2.selectedElementId == null || !var2.selectionOutlineVisible) && !var37) {
                  this.clearEditorOverlaysOnly(var1, var2);
               }

               if (var2.selectedElementId != null && var2.selectionOutlineVisible && !var37) {
                  this.renderSelectionOverlay(var1, var2);
               }

               if (var3) {
                  this.updateEditorPropertiesSidebar(var1, var2);
               }

               this.updatePageInfoReadout(var1, var2);
            }
         }
      }
   }

   protected boolean isDescendantOfAnyTargetPath(String var1, Set<String> var2) {
      if (var1 != null && !var1.isBlank() && var2 != null && !var2.isEmpty()) {
         for (Object var4_raw : var2) {
            String var4 = var4_raw != null ? var4_raw.toString() : null;
            if (var4 != null && !var4.isBlank() && var1.startsWith(var4 + ".children.")) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   protected boolean isTransformDragInProgress(EditorSession var1) {
      if (var1 == null) {
         return false;
      } else {
         return var1.moveDragActive ? true : this.isEffectiveScaleTool(var1) && var1.activeHandle != null && this.isLeftHeld(var1);
      }
   }

   protected boolean isEffectiveScaleTool(EditorSession var1) {
      return var1 == null
         ? false
         : var1.activeTool == EditorTool.SCALE || var1.activeTool == EditorTool.ANIMATION && this.isAnimationTimelineScaleKeyframeSelected(var1);
   }

   protected boolean isSidebarFieldDragInProgress(EditorSession var1) {
      return var1 != null && var1.sidebarFieldDragActive && this.isLeftHeld(var1);
   }

   @Override
   protected void showSelectionOverlay(Player var1, EditorSession var2) {
      if (var2 != null) {
         var2.selectionOutlineVisible = true;
         this.renderSelectionOverlay(var1, var2);
      }
   }

   protected void hideSelectionOverlay(Player var1, EditorSession var2) {
      if (var2 != null) {
         var2.selectionOutlineVisible = false;
         this.clearEditorOverlaysOnly(var1, var2);
      }
   }

   protected void restoreElementText(Player var1, EditorSession var2, String var3) {
      if (var3 != null) {
         for (HoverElement var5 : this.findTargetElements(var2, var3)) {
            this.setElementText(var1, var5, var5.text);
         }
      }
   }

   @Override
   protected void renderSelectionOverlay(Player var1, EditorSession var2) {
      if (var2 != null && var2.selectionOutlineVisible) {
         EditorRect var3 = this.getSelectedBounds(var2);
         if (var3 == null) {
            this.clearAnimationPreviewMotionPath(var1, var2);
         } else {
            var2.handleCenters.clear();
            double var4 = this.toRenderX(var2, var3.x);
            double var6 = this.toRenderY(var2, var3.y);
            double var8 = this.toRenderSize(var2, var3.width);
            double var10 = this.toRenderSize(var2, var3.height);
            if (this.hasSelectedImageGroupTarget(var2)) {
               EditorRect var12 = this.resolveImageGroupVisualBoundsFromElements(var2);
               if (var12 != null) {
                  var4 = this.toRenderX(var2, var12.x);
                  var6 = this.toRenderY(var2, var12.y);
                  var8 = this.toRenderSize(var2, var12.width);
                  var10 = this.toRenderSize(var2, var12.height);
               }
            }

            String var25 = "";
            boolean var13 = false;
            if (var2.activeTool == EditorTool.ANIMATION && var2.animationTimelinePanelVisible) {
               var25 = this.firstNonBlank(new String[]{this.resolveAnimationTimelineEditableTargetId(var2), var2.selectedElementId});
               String var14 = this.resolveInheritedTargetRootPath(var2, var25);
               var13 = this.shouldLogAnimationOutlineDebug(var2, var25, var14);
               if (var13) {
                  EditorRect var15 = null;
                  if (this.equalsNullable(var25, var2.animationPreviewTargetId)) {
                     var15 = var2.animationPreviewAppliedBounds;
                  }

                  if (var15 == null) {
                     var15 = var2.animationPreviewAdditionalAppliedBoundsByTarget.get(var25);
                  }

                  boolean var16 = var15 != null && (Math.abs(var3.width - var15.width) > 0.001 || Math.abs(var3.height - var15.height) > 0.001);
                  if (var16) {
                     this.logAnimationOutlineDebug(var2, "editor-outline-mismatch", "selection-vs-preview-logical", var25, var3, var15, var4, var6, var8, var10);
                  }
               }
            }

            double var26 = 8994.0;
            double var27 = 8995.0;
            String var18 = this.resolveSelectionOutlineColor(var2);
            boolean var19 = this.hasSelectedImageGroupTarget(var2);
            boolean var20 = !var19 && this.shouldFreezeOverlaySide(var2, "top");
            boolean var21 = !var19 && this.shouldFreezeOverlaySide(var2, "bottom");
            boolean var22 = !var19 && this.shouldFreezeOverlaySide(var2, "left");
            boolean var23 = !var19 && this.shouldFreezeOverlaySide(var2, "right");
            if (!var20) {
               this.addOverlayBar(var1, "editor_top", var4 - 4.0, var6 - 4.0 + 1.0, var8 + 8.0, 4.0, var26, var18);
            }

            if (!var21) {
               this.addOverlayBar(var1, "editor_bottom", var4 - 4.0, var6 + var10, var8 + 8.0, 4.0, var26, var18);
            }

            if (!var22) {
               this.addOverlayBar(var1, "editor_left", var4 - 4.0, var6, 4.0, var10, var26, var18);
            }

            if (!var23) {
               this.addOverlayBar(var1, "editor_right", var4 + var8, var6, 4.0, var10, var26, var18);
            }

            if (var13) {
               this.logAnimationOutlineLineHeights(var2, var25, var20, var21, var22, var23, var10);
               this.logAnimationSelectionOverlayRuntimeHudHeights(var1, var2, var25);
               this.logAnimationTargetOutlineRuntimeHudHeights(var1, var2, var25);
            }

            this.renderAnimationPreviewMotionPath(var1, var2);
            if (!this.isEffectiveScaleTool(var2)) {
               this.clearHandleOverlays(var1, var2);
            } else {
               String var24 = this.resolveSelectionHandleColor(var2);
               this.addHandle(var1, var2, "tl", var4, var6, var27, var24);
               this.addHandle(var1, var2, "n", var4 + var8 / 2.0, var6, var27, var24);
               this.addHandle(var1, var2, "tr", var4 + var8, var6, var27, var24);
               this.addHandle(var1, var2, "w", var4, var6 + var10 / 2.0, var27, var24);
               this.addHandle(var1, var2, "e", var4 + var8, var6 + var10 / 2.0, var27, var24);
               this.addHandle(var1, var2, "bl", var4, var6 + var10, var27, var24);
               this.addHandle(var1, var2, "s", var4 + var8 / 2.0, var6 + var10, var27, var24);
               this.addHandle(var1, var2, "br", var4 + var8, var6 + var10, var27, var24);
               this.addMoveCenterHandle(var1, var2, var4 + var8 / 2.0, var6 + var10 / 2.0, var27, var24);
               this.updateHandleHover(var1, var2, var2.hoveredHandle);
            }
         }
      } else {
         this.clearAnimationPreviewMotionPath(var1, var2);
      }
   }

   protected void renderAnimationPreviewMotionPath(Player var1, EditorSession var2) {
      if (var2 != null) {
         if (var1 != null && var2.previewMode && var2.activeTool == EditorTool.ANIMATION && var2.selectionOutlineVisible) {
            String var3 = this.firstNonBlank(new String[]{this.resolveAnimationTimelineEditableTargetId(var2)});
            if (var3.isBlank()) {
               this.clearAnimationPreviewMotionPath(var1, var2);
            } else {
               TreeSet var4 = new TreeSet();
               var4.addAll(this.readAnimationTimelineTicks(var2, var3, "position"));
               if (var4.isEmpty()) {
                  this.clearAnimationPreviewMotionPath(var1, var2);
               } else {
                  var4.add(0);
                  double var5 = this.resolveAnimationPreviewZoom(var2);
                  double var7 = this.resolveAnimationPreviewPathDotSpacing(var5);
                  double var9 = this.isSidebarImagePositionOffsetTarget(var2, var3)
                     ? this.resolveSidebarImagePositionOffset(EditorPropertyField.POSITION_X)
                     : 0.0;
                  double var11 = this.isSidebarImagePositionOffsetTarget(var2, var3)
                     ? this.resolveSidebarImagePositionOffset(EditorPropertyField.POSITION_Y)
                     : 0.0;
                  String var13 = this.firstNonBlank(new String[]{var2.animationTimelineSelectedTargetId});
                  int var14 = this.equalsNullable(var13, var3) ? this.clampAnimationTimelineTick(var2.animationTimelineSelectedTick) : -1;
                  LinkedHashSet var15 = new LinkedHashSet();
                  ArrayList var16 = new ArrayList(var4);

                  for (int var17 = 0; var17 < var16.size() - 1; var17++) {
                     int var18 = this.clampAnimationTimelineTick((Integer)var16.get(var17));
                     int var19 = this.clampAnimationTimelineTick((Integer)var16.get(var17 + 1));
                     if (var19 > var18) {
                        List var20 = this.buildAnimationPreviewPathSamples(var2, var3, var18, var19);
                        double var21 = this.resolveAnimationPreviewPathLength(var20);
                        int var23 = this.resolveAnimationPreviewPathDotCount(var19 - var18, var21, var5, var7);
                        boolean var24 = false;

                        for (int var25 = 1; var25 < var23 - 1; var25++) {
                           double var26 = (double)var25 * var7;
                           if (var26 >= var21 - 1.0E-4) {
                              break;
                           }

                           double[] var28 = this.resolveAnimationPreviewPathCenterAtDistance(var20, var26);
                           if (var28 != null && var28.length >= 2) {
                              String var29 = "editor_animation_preview_path_seg_" + var18 + "_" + var25;
                              this.upsertAnimationPreviewMotionGlyphAtCenter(var1, var2, var29, var28[0] - var9, var28[1] - var11, 24.0, 8993.6, "\uef39", var5);
                              var15.add(var29);
                              var24 = true;
                           }
                        }

                        if (!var24 && var21 > 1.0E-4 && var23 > 2) {
                           double[] var35 = this.resolveAnimationPreviewPathCenterAtDistance(var20, var21 * 0.5);
                           if (var35 != null && var35.length >= 2) {
                              String var36 = "editor_animation_preview_path_seg_" + var18 + "_mid";
                              this.upsertAnimationPreviewMotionGlyphAtCenter(var1, var2, var36, var35[0] - var9, var35[1] - var11, 24.0, 8993.6, "\uef39", var5);
                              var15.add(var36);
                           }
                        }
                     }
                  }

                  for (Object var31_raw : var4) {
                     int var31 = ((Number)var31_raw).intValue();
                     double[] var32 = this.resolveAnimationTimelinePreviewState(var2, var3, (double)var31);
                     if (var32 != null && var32.length >= 4) {
                        String var33 = "editor_animation_preview_keyframe_" + var31;
                        String var34 = var31 == var14 ? "\uef37" : "\uef38";
                        double[] var22 = (double[])var32.clone();
                        var22[0] -= var9;
                        var22[1] -= var11;
                        this.upsertAnimationPreviewMotionGlyph(var1, var2, var33, var22, 32.0, 8993.7, var34, var5);
                        var15.add(var33);
                     }
                  }

                  this.clearStaleAnimationPreviewMotionPathHuds(var1, var2, var15);
               }
            }
         } else {
            this.clearAnimationPreviewMotionPath(var1, var2);
         }
      }
   }

   protected double calculateAnimationPreviewPathLength(EditorSession var1, String var2, int var3) {
      return this.resolveAnimationPreviewPathLength(this.buildAnimationPreviewPathSamples(var1, var2, var3));
   }

   protected List<double[]> buildAnimationPreviewPathSamples(EditorSession var1, String var2, int var3) {
      return this.buildAnimationPreviewPathSamples(var1, var2, 0, var3);
   }

   protected List<double[]> buildAnimationPreviewPathSamples(EditorSession var1, String var2, int var3, int var4) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         int var5 = this.clampAnimationTimelineTick(var3);
         int var6 = this.clampAnimationTimelineTick(var4);
         if (var6 < var5) {
            return Collections.emptyList();
         } else {
            ArrayList var7 = new ArrayList();
            Double var8 = null;
            Double var9 = null;
            double var10 = 0.0;

            for (int var12 = var5; var12 <= var6; var12++) {
               double[] var13 = this.resolveAnimationTimelinePreviewState(var1, var2, (double)var12);
               if (var13 != null && var13.length >= 4) {
                  double var14 = var13[0] + var13[2] / 2.0;
                  double var16 = var13[1] + var13[3] / 2.0;
                  if (Double.isFinite(var14) && Double.isFinite(var16)) {
                     if (var8 != null && var9 != null) {
                        var10 += Math.hypot(var14 - var8, var16 - var9);
                     }

                     var7.add(new double[]{(double)var12, var14, var16, var10});
                     var8 = var14;
                     var9 = var16;
                  }
               }
            }

            return var7;
         }
      } else {
         return Collections.emptyList();
      }
   }

   protected double resolveAnimationPreviewPathLength(List<double[]> var1) {
      if (var1 != null && !var1.isEmpty()) {
         double[] var2 = (double[])var1.get(var1.size() - 1);
         if (var2 != null && var2.length >= 4) {
            double var3 = var2[3];
            return Double.isFinite(var3) ? Math.max(0.0, var3) : 0.0;
         } else {
            return 0.0;
         }
      } else {
         return 0.0;
      }
   }

   protected double[] resolveAnimationPreviewPathCenterAtDistance(List<double[]> var1, double var2) {
      if (var1 != null && !var1.isEmpty()) {
         double[] var4 = (double[])var1.get(0);
         if (var4 != null && var4.length >= 4) {
            double var5 = this.resolveAnimationPreviewPathLength(var1);
            double var7 = Double.isFinite(var2) ? Math.max(0.0, Math.min(var5, var2)) : 0.0;
            if (!(var7 <= 1.0E-4) && !(var5 <= 1.0E-4)) {
               double[] var9 = var4;

               for (int var10 = 1; var10 < var1.size(); var10++) {
                  double[] var11 = (double[])var1.get(var10);
                  if (var11 != null && var11.length >= 4) {
                     double var12 = var9[3];
                     double var14 = var11[3];
                     if (Double.isFinite(var12) && Double.isFinite(var14)) {
                        if (!(var7 > var14 + 1.0E-4)) {
                           double var16 = Math.max(1.0E-4, var14 - var12);
                           double var18 = Math.max(0.0, Math.min(1.0, (var7 - var12) / var16));
                           double var20 = var9[1] + (var11[1] - var9[1]) * var18;
                           double var22 = var9[2] + (var11[2] - var9[2]) * var18;
                           return new double[]{var20, var22};
                        }

                        var9 = var11;
                     } else {
                        var9 = var11;
                     }
                  }
               }

               double[] var24 = (double[])var1.get(var1.size() - 1);
               return var24 != null && var24.length >= 2 ? new double[]{var24[1], var24[2]} : null;
            } else {
               return new double[]{var4[1], var4[2]};
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   protected int resolveAnimationPreviewPathDotCount(int var1, double var2, double var4) {
      double var6 = this.resolveAnimationPreviewPathDotSpacing(var4);
      return this.resolveAnimationPreviewPathDotCount(var1, var2, var4, var6);
   }

   protected int resolveAnimationPreviewPathDotCount(int var1, double var2, double var4, double var6) {
      if (var1 <= 0) {
         return 1;
      } else if (Double.isFinite(var2) && !(var2 <= 1.0E-4)) {
         double var8 = Double.isFinite(var6) && var6 > 1.0E-4 ? var6 : 130.0;
         int var10 = (int)Math.floor(var2 / var8) + 1;
         var10 = Math.max(2, var10);
         return Math.max(3, Math.min(220, var10));
      } else {
         return 2;
      }
   }

   protected double resolveAnimationPreviewPathDotSpacing(double var1) {
      double var3 = 1.0;
      if (Double.isFinite(var1) && var1 > 0.0 && var1 < 1.0) {
         double var5 = 1.0 - var1;
         var3 = 1.0 + var5 * 0.1;
         var3 = Math.min(1.08, var3);
      }

      if (Double.isFinite(var1) && var1 > 0.8) {
         double var11 = Math.max(1.0E-4, 5.2);
         double var7 = Math.min(1.0, (var1 - 0.8) / var11);
         var3 += var7 * 0.6;
      }

      if (Double.isFinite(var1)) {
         double var12 = Math.max(1.0E-4, 5.9);
         double var13 = Math.min(1.0, Math.max(0.0, (var1 - 0.1) / var12));
         var3 += var13 * 0.22000000000000003;
      }

      var3 = Math.max(0.6, Math.min(2.8, var3));
      return Math.max(8.0, 130.0 / var3);
   }

   protected double resolveAnimationPreviewZoom(EditorSession var1) {
      if (var1 != null && var1.previewViewport != null) {
         double var2 = var1.previewViewport.zoom;
         return !Double.isFinite(var2) ? 1.0 : Math.max(0.1, Math.min(6.0, var2));
      } else {
         return 1.0;
      }
   }

   protected void upsertAnimationPreviewMotionGlyph(
      Player var1, EditorSession var2, String var3, double[] var4, double var5, double var7, String var9, double var10
   ) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var4 != null && var4.length >= 4) {
         double var12 = var4[0] + var4[2] / 2.0;
         double var14 = var4[1] + var4[3] / 2.0;
         this.upsertAnimationPreviewMotionGlyphAtCenter(var1, var2, var3, var12, var14, var5, var7, var9, var10);
      }
   }

   protected void upsertAnimationPreviewMotionGlyphAtCenter(
      Player var1, EditorSession var2, String var3, double var4, double var6, double var8, double var10, String var12, double var13
   ) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && Double.isFinite(var4) && Double.isFinite(var6)) {
         double var15 = this.toRenderX(var2, var4);
         double var17 = this.toRenderY(var2, var6);
         double var19 = 1.0;
         if (Double.isFinite(var13) && var13 > 1.0) {
            var19 += (var13 - 1.0) * 0.32;
         }

         double var21 = Math.max(1.0, var8) * var19;
         double var23 = var15 - var21 / 2.0;
         double var25 = var17 - var21 / 2.0;
         HudPositionCalculator.Placement var27 = this.positionCalculator.calculateBoxPlacement(var23, var25, var10, var21, var21);
         this.upsertOverlayHud(var1, var3, var27.location(), var27.scale(), "<#2f7dff>" + this.firstNonBlank(new String[]{var12, "\uef39"}));
      }
   }

   protected void clearStaleAnimationPreviewMotionPathHuds(Player var1, EditorSession var2, Set<String> var3) {
      if (var2 != null) {
         LinkedHashSet var4 = new LinkedHashSet<>(var2.runtimeAnimationPreviewPathIds);
         if (var3 != null && !var3.isEmpty()) {
            var4.removeAll(var3);
         }

         if (var1 != null) {
            for (Object var6_raw : var4) {
               String var6 = var6_raw != null ? var6_raw.toString() : null;
               this.removeOverlayHud(var1, var6);
            }
         }

         var2.runtimeAnimationPreviewPathIds.clear();
         if (var3 != null && !var3.isEmpty()) {
            var2.runtimeAnimationPreviewPathIds.addAll(var3);
         }
      }
   }

   protected void clearAnimationPreviewMotionPath(Player var1, EditorSession var2) {
      if (var2 != null) {
         if (var1 != null && !var2.runtimeAnimationPreviewPathIds.isEmpty()) {
            for (String var4 : new ArrayList<>(var2.runtimeAnimationPreviewPathIds)) {
               this.removeOverlayHud(var1, var4);
            }
         }

         var2.runtimeAnimationPreviewPathIds.clear();
      }
   }

   protected boolean shouldFreezeOverlaySide(EditorSession var1, String var2) {
      if (var1 == null || var1.activeHandle == null) {
         return false;
      } else if (this.hasSelectedItemDisplayBlock(var1)) {
         return false;
      } else {
         String var3 = var1.activeHandle;

         return switch (var3) {
            case "n" -> "bottom".equals(var2);
            case "s" -> "top".equals(var2);
            case "w" -> "right".equals(var2);
            case "e" -> "left".equals(var2);
            default -> false;
         };
      }
   }

   protected boolean hasSelectedItemDisplayBlock(EditorSession var1) {
      if (var1 == null) {
         return false;
      } else {
         for (String var3 : this.getSelectedTargetIds(var1)) {
            for (HoverElement var5 : this.findTargetElements(var1, var3)) {
               if (var5 != null && var5.itemDisplayBlock) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   protected void restoreHandlesOnly(Player var1, EditorSession var2) {
      if (!this.isEffectiveScaleTool(var2)) {
         this.clearHandleOverlays(var1, var2);
      } else {
         EditorRect var3 = this.getSelectedBounds(var2);
         if (var3 != null) {
            var2.handleCenters.clear();
            double var4 = this.toRenderX(var2, var3.x);
            double var6 = this.toRenderY(var2, var3.y);
            double var8 = this.toRenderSize(var2, var3.width);
            double var10 = this.toRenderSize(var2, var3.height);
            if (this.hasSelectedImageGroupTarget(var2)) {
               EditorRect var12 = this.resolveImageGroupVisualBoundsFromElements(var2);
               if (var12 != null) {
                  var4 = this.toRenderX(var2, var12.x);
                  var6 = this.toRenderY(var2, var12.y);
                  var8 = this.toRenderSize(var2, var12.width);
                  var10 = this.toRenderSize(var2, var12.height);
               }
            }

            double var15 = 8995.0;
            String var14 = this.resolveSelectionHandleColor(var2);
            this.addHandle(var1, var2, "tl", var4, var6, var15, var14);
            this.addHandle(var1, var2, "n", var4 + var8 / 2.0, var6, var15, var14);
            this.addHandle(var1, var2, "tr", var4 + var8, var6, var15, var14);
            this.addHandle(var1, var2, "w", var4, var6 + var10 / 2.0, var15, var14);
            this.addHandle(var1, var2, "e", var4 + var8, var6 + var10 / 2.0, var15, var14);
            this.addHandle(var1, var2, "bl", var4, var6 + var10, var15, var14);
            this.addHandle(var1, var2, "s", var4 + var8 / 2.0, var6 + var10, var15, var14);
            this.addHandle(var1, var2, "br", var4 + var8, var6 + var10, var15, var14);
            this.addMoveCenterHandle(var1, var2, var4 + var8 / 2.0, var6 + var10 / 2.0, var15, var14);
            this.updateHandleHover(var1, var2, var2.hoveredHandle);
         }
      }
   }

   protected String resolveSelectionOutlineColor(EditorSession var1) {
      if (var1 == null) {
         return "<#2f7dff>";
      } else {
         boolean var2 = false;
         boolean var3 = false;
         boolean var4 = false;

         for (String var6 : this.getSelectedTargetIds(var1)) {
            if (var6 != null && !var6.isBlank()) {
               boolean var7 = this.isTargetVisible(var1, var6);
               boolean var8 = this.isTargetLocked(var1, var6);
               if (!var7 && var8) {
                  var4 = true;
                  break;
               }

               if (!var7) {
                  var2 = true;
               }

               if (var8) {
                  var3 = true;
               }
            }
         }

         if (var4) {
            return "<#21ff4a>";
         } else if (var2) {
            return "<#42aaff>";
         } else {
            return var3 ? "<#30ff98>" : "<#2f7dff>";
         }
      }
   }

   @Override
   protected void refreshSelectionOutlineColorOnly(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.selectionOutlineVisible && var2.selectedElementId != null) {
         String var3 = this.resolveSelectionOutlineColor(var2);
         this.recolorExistingOutlineHud(var1, "editor_top", var3);
         this.recolorExistingOutlineHud(var1, "editor_bottom", var3);
         this.recolorExistingOutlineHud(var1, "editor_left", var3);
         this.recolorExistingOutlineHud(var1, "editor_right", var3);
         String var4 = this.resolveSelectionHandleColor(var2);
         this.recolorExistingOutlineHud(var1, "editor_handle_tl", var4);
         this.recolorExistingOutlineHud(var1, "editor_handle_n", var4);
         this.recolorExistingOutlineHud(var1, "editor_handle_tr", var4);
         this.recolorExistingOutlineHud(var1, "editor_handle_w", var4);
         this.recolorExistingOutlineHud(var1, "editor_handle_e", var4);
         this.recolorExistingOutlineHud(var1, "editor_handle_bl", var4);
         this.recolorExistingOutlineHud(var1, "editor_handle_s", var4);
         this.recolorExistingOutlineHud(var1, "editor_handle_br", var4);
         this.recolorExistingOutlineHud(var1, "editor_handle_move_tl", var4);
         this.recolorExistingOutlineHud(var1, "editor_handle_move_tr", var4);
         this.recolorExistingOutlineHud(var1, "editor_handle_move_bl", var4);
         this.recolorExistingOutlineHud(var1, "editor_handle_move_br", var4);
      }
   }

   protected String resolveSelectionHandleColor(EditorSession var1) {
      return this.resolveSelectionOutlineColor(var1);
   }

   protected String resolveSelectionHandleHoverColor(EditorSession var1) {
      return this.adjustHexColor(this.resolveSelectionHandleColor(var1), 0.14);
   }

   protected String adjustHexColor(String var1, double var2) {
      String var4 = this.firstNonBlank(new String[]{var1, "<#2f7dff>"}).trim();
      if (var4.startsWith("<#") && var4.length() >= 9 && var4.charAt(8) == '>') {
         String var5 = var4.substring(2, 8);
         int var6 = Integer.parseInt(var5.substring(0, 2), 16);
         int var7 = Integer.parseInt(var5.substring(2, 4), 16);
         int var8 = Integer.parseInt(var5.substring(4, 6), 16);
         double var9 = Math.max(0.0, Math.min(1.0, var2));
         var6 = this.clampColor((int)Math.round((double)var6 + (double)(255 - var6) * var9));
         var7 = this.clampColor((int)Math.round((double)var7 + (double)(255 - var7) * var9));
         var8 = this.clampColor((int)Math.round((double)var8 + (double)(255 - var8) * var9));
         return String.format("<#%02x%02x%02x>", var6, var7, var8);
      } else {
         return this.firstNonBlank(new String[]{var1, "<#2f7dff>"});
      }
   }

   protected void recolorExistingOutlineHud(Player var1, String var2, String var3) {
      Entity var4 = this.hudService.getHud(var1, var2);
      if (var4 != null) {
         String var5 = this.firstNonBlank(new String[]{this.hudService.getHudText(var4, null)});
         String var6 = this.recolorHudText(var5, this.firstNonBlank(new String[]{var3, "<#2f7dff>"}), "\ue67b");
         if (!this.equalsNullable(var5, var6)) {
            this.hudService.setHudText(var4, var6, null, false);
         }
      }
   }

   protected String recolorHudText(String var1, String var2, String var3) {
      String var4 = this.firstNonBlank(new String[]{var1}).trim();
      if (var4.startsWith("<font:") && var4.endsWith("</font>")) {
         int var5 = var4.indexOf(62);
         if (var5 > 0) {
            var4 = var4.substring(var5 + 1, var4.length() - 7).trim();
         }
      }

      while (var4.startsWith("<#") && var4.length() >= 9 && var4.charAt(8) == '>') {
         var4 = var4.substring(9).trim();
      }

      if (var4.isBlank()) {
         var4 = this.firstNonBlank(new String[]{var3});
      }

      return this.ensureRoundedCornerEditorFont(this.firstNonBlank(new String[]{var2, "<#2f7dff>"}) + var4);
   }

   protected void addOverlayBar(Player var1, String var2, double var3, double var5, double var7, double var9, double var11, String var13) {
      HudPositionCalculator.Placement var14 = this.positionCalculator.calculateBoxPlacement(var3, var5, var11, Math.max(1.0, var7), Math.max(1.0, var9));
      this.upsertOverlayHud(var1, var2, var14.location(), var14.scale(), this.firstNonBlank(new String[]{var13, "<#2f7dff>"}) + "\ue67b");
   }

   protected void addHandle(Player var1, EditorSession var2, String var3, double var4, double var6, double var8, String var10) {
      if (var2.handlesCollapsed && var2.activeHandle != null && !var2.activeHandle.equals(var3)) {
         this.removeOverlayHud(var1, "editor_handle_" + var3);
      } else {
         double var11 = 15.0;
         double var13 = var4 - var11 / 2.0;
         double var15 = var6 - var11 / 2.0;
         String var17 = "editor_handle_" + var3;
         HudPositionCalculator.Placement var18 = this.positionCalculator.calculateBoxPlacement(var13, var15, var8, var11, var11);
         this.upsertOverlayHud(var1, var17, var18.location(), var18.scale(), this.firstNonBlank(new String[]{var10, "<#639eff>"}) + "\ue67b");
         var2.handleCenters.put(var3, new Vector(var4, var6, 0.0));
      }
   }

   protected void addMoveCenterHandle(Player var1, EditorSession var2, double var3, double var5, double var7, String var9) {
      if (var2.handlesCollapsed && var2.activeHandle != null && !"move".equals(var2.activeHandle)) {
         this.removeOverlayHud(var1, "editor_handle_move_tl");
         this.removeOverlayHud(var1, "editor_handle_move_tr");
         this.removeOverlayHud(var1, "editor_handle_move_bl");
         this.removeOverlayHud(var1, "editor_handle_move_br");
      } else {
         double var10 = 6.0;
         double var12 = 4.0;
         double var14 = var12 / 2.0;
         this.addMoveHandleCell(var1, "editor_handle_move_tl", var3 - var14 - var10, var5 - var14 - var10, var10, var7, var9);
         this.addMoveHandleCell(var1, "editor_handle_move_tr", var3 + var14, var5 - var14 - var10, var10, var7, var9);
         this.addMoveHandleCell(var1, "editor_handle_move_bl", var3 - var14 - var10, var5 + var14, var10, var7, var9);
         this.addMoveHandleCell(var1, "editor_handle_move_br", var3 + var14, var5 + var14, var10, var7, var9);
         var2.handleCenters.put("move", new Vector(var3, var5, 0.0));
      }
   }

   protected void addMoveHandleCell(Player var1, String var2, double var3, double var5, double var7, double var9, String var11) {
      HudPositionCalculator.Placement var12 = this.positionCalculator.calculateBoxPlacement(var3, var5, var9, var7, var7);
      this.upsertOverlayHud(var1, var2, var12.location(), var12.scale(), this.firstNonBlank(new String[]{var11, "<#639eff>"}) + "\ue67b");
   }

   protected void clearHandleOverlays(Player var1, EditorSession var2) {
      this.removeOverlayHud(var1, "editor_handle_tl");
      this.removeOverlayHud(var1, "editor_handle_tr");
      this.removeOverlayHud(var1, "editor_handle_bl");
      this.removeOverlayHud(var1, "editor_handle_br");
      this.removeOverlayHud(var1, "editor_handle_nw");
      this.removeOverlayHud(var1, "editor_handle_n");
      this.removeOverlayHud(var1, "editor_handle_ne");
      this.removeOverlayHud(var1, "editor_handle_w");
      this.removeOverlayHud(var1, "editor_handle_e");
      this.removeOverlayHud(var1, "editor_handle_sw");
      this.removeOverlayHud(var1, "editor_handle_s");
      this.removeOverlayHud(var1, "editor_handle_se");
      this.removeOverlayHud(var1, "editor_handle_move_tl");
      this.removeOverlayHud(var1, "editor_handle_move_tr");
      this.removeOverlayHud(var1, "editor_handle_move_bl");
      this.removeOverlayHud(var1, "editor_handle_move_br");
      if (var2 != null) {
         var2.handleCenters.clear();
         var2.hoveredHandle = null;
      }
   }

   protected void updateHandleHover(Player var1, EditorSession var2, String var3) {
      String var4 = var2.hoveredHandle;
      String var5 = this.resolveSelectionHandleColor(var2);
      String var6 = this.resolveSelectionHandleHoverColor(var2);
      if (var4 != null && !var4.equals(var3)) {
         if ("move".equals(var4)) {
            this.setMoveHandleClusterColor(var1, var5);
         } else {
            Entity var7 = this.hudService.getHud(var1, "editor_handle_" + var4);
            if (var7 != null) {
               this.hudService.setHudText(var7, this.ensureRoundedCornerEditorFont(var5 + "\ue67b"), null, false);
            }
         }
      }

      if (var3 != null) {
         if ("move".equals(var3)) {
            this.setMoveHandleClusterColor(var1, var6);
         } else {
            Entity var8 = this.hudService.getHud(var1, "editor_handle_" + var3);
            if (var8 != null) {
               this.hudService.setHudText(var8, this.ensureRoundedCornerEditorFont(var6 + "\ue67b"), null, false);
            }
         }
      }
   }

   protected void setMoveHandleClusterColor(Player var1, String var2) {
      String var3 = this.ensureRoundedCornerEditorFont(var2 + "\ue67b");
      this.setHudTextIfExists(var1, "editor_handle_move_tl", var3);
      this.setHudTextIfExists(var1, "editor_handle_move_tr", var3);
      this.setHudTextIfExists(var1, "editor_handle_move_bl", var3);
      this.setHudTextIfExists(var1, "editor_handle_move_br", var3);
   }

   protected void upsertOverlayHud(Player var1, String var2, Vector var3, Vector var4, String var5) {
      EditorSession var6 = this.editorSessions.get(var1.getUniqueId());
      int var7 = this.resolveEditorHudTransitionTicks(var6);
      String var8 = this.ensureRoundedCornerEditorFont(var5);
      Entity var9 = this.hudService.getHud(var1, var2);
      if (var9 == null) {
         this.hudService.addHud(var1, var2, var3, var4, var8, TextAlignment.CENTER, TextAlignment.CENTER, false, 255);
      } else {
         Vector var10 = this.hudService.getHudLocation(var9);
         if (!this.sameVector(var10, var3)) {
            this.hudService.moveHud(var9, var3, var7, var7);
         }

         Vector var11 = this.hudService.getHudScale(var9);
         if (!this.sameVector(var11, var4)) {
            this.hudService.setHudScale(var9, var4, var7, false);
         }

         String var12 = this.hudService.getHudText(var9, null);
         if (!this.equalsNullable(var12, var8)) {
            this.hudService.setHudText(var9, var8, null, false);
         }
      }
   }

   protected String findHoveredHandle(EditorSession var1, double var2, double var4) {
      if (!this.isEffectiveScaleTool(var1)) {
         return null;
      } else if (this.isInsideMoveHandleCell(var1, var2, var4)) {
         return "move";
      } else {
         String var6 = null;
         double var7 = Double.MAX_VALUE;

         for (Map.Entry<?, ?> var10 : ((Map<?, ?>)var1.handleCenters).entrySet()) {
            if (!"move".equals(var10.getKey())) {
               Vector var11 = (Vector)var10.getValue();
               if (var11 != null) {
                  double var12 = 7.5;
                  if (var2 >= var11.getX() - var12 && var2 <= var11.getX() + var12 && var4 >= var11.getY() - var12 && var4 <= var11.getY() + var12) {
                     double var14 = var2 - var11.getX();
                     double var16 = var4 - var11.getY();
                     double var18 = var14 * var14 + var16 * var16;
                     if (var6 == null || var18 < var7) {
                        var6 = (String)var10.getKey();
                        var7 = var18;
                     }
                  }
               }
            }
         }

         return var6;
      }
   }

   protected boolean isInsideMoveHandleCell(EditorSession var1, double var2, double var4) {
      if (var1 == null) {
         return false;
      } else {
         Vector var6 = var1.handleCenters.get("move");
         if (var6 == null) {
            return false;
         } else {
            double var7 = 6.0;
            double var9 = 2.0;
            double var11 = var6.getX();
            double var13 = var6.getY();
            double var15 = var7 + var9;
            double var17 = var15 * 2.0;
            return this.isInsideRect(var2, var4, var11 - var15, var13 - var15, var17, var17);
         }
      }
   }

   protected boolean isInsideRect(double var1, double var3, double var5, double var7, double var9, double var11) {
      return var1 >= var5 && var1 <= var5 + var9 && var3 >= var7 && var3 <= var7 + var11;
   }

   protected void clearEditorOverlays(Player var1) {
      for (Object var5_raw : EDITOR_OVERLAY_IDS) {
         String var5 = var5_raw != null ? var5_raw.toString() : null;
         this.removeOverlayHud(var1, var5);
      }

      EditorSession var6 = this.editorSessions.get(var1.getUniqueId());
      if (var6 != null) {
         this.clearAnimationPreviewMotionPath(var1, var6);

         for (String var8 : this.getSelectedTargetIds(var6)) {
            this.restoreElementLayer(var1, var6, var8);
         }

         var6.handleCenters.clear();
         var6.hoveredHandle = null;
         var6.handlesCollapsed = false;
      }
   }

   @Override
   protected void clearEditorOverlaysOnly(Player var1, EditorSession var2) {
      for (Object var6_raw : EDITOR_OVERLAY_IDS) {
         String var6 = var6_raw != null ? var6_raw.toString() : null;
         this.removeOverlayHud(var1, var6);
      }

      if (var2 != null) {
         this.clearAnimationPreviewMotionPath(var1, var2);
         var2.handleCenters.clear();
         var2.hoveredHandle = null;
      }
   }

   protected void bringElementToFront(Player var1, EditorSession var2, String var3) {
      if (var3 != null) {
         List var4 = this.findTargetElements(var2, var3);
         if (!var4.isEmpty()) {
            double var5 = 8993.0;
            double var7 = Double.NEGATIVE_INFINITY;

            for (Object var10_raw : var4) {
               HoverElement var10 = (HoverElement)var10_raw;
               var7 = Math.max(var7, this.defaultRuntimeLayer(var2, var10));
            }

            for (Object var14_raw : var4) {
               HoverElement var14 = (HoverElement)var14_raw;
               double var11 = var5 + (this.defaultRuntimeLayer(var2, var14) - var7);
               if (Double.compare(var14.runtimeZ, var11) != 0) {
                  var14.runtimeZ = var11;
                  this.updateElementHud(var1, var14);
               }
            }
         }
      }
   }

   protected void restoreElementLayer(Player var1, EditorSession var2, String var3) {
      if (var3 != null) {
         for (HoverElement var5 : this.findTargetElements(var2, var3)) {
            double var6 = this.defaultRuntimeLayer(var2, var5);
            if (Double.compare(var5.runtimeZ, var6) != 0) {
               var5.runtimeZ = var6;
               this.updateElementHud(var1, var5);
            }
         }
      }
   }

   protected void removeOverlayHud(Player var1, String var2) {
      Entity var3 = this.hudService.getHud(var1, var2);
      if (var3 != null) {
         this.hudService.removeHudX(Collections.singletonList(var3), false);
      }

      var1.removeMetadata("hud_" + var2, this.plugin);
   }

   protected void refreshPreviewProjection(Player var1, EditorSession var2) {
      if (var2.previewMode) {
         this.renderPreviewPageOutline(var1, var2);
         this.renderEditorTransparencyOverlay(var1, var2);

         for (Object var4_raw : var2.elements) {
            HoverElement var4 = (HoverElement)var4_raw;
            this.updateElementHud(var1, var4);
         }

         if (var2.selectedElementId != null && var2.selectionOutlineVisible) {
            this.renderSelectionOverlay(var1, var2);
         } else {
            this.clearEditorOverlays(var1);
         }

         this.updatePageInfoReadout(var1, var2);
      }
   }

   protected void renderPreviewPageOutline(Player var1, EditorSession var2) {
      if (var2.previewMode && var2.previewViewport != null) {
         PreviewViewport var3 = var2.previewViewport;
         double var4 = this.toRenderX(var2, 0.0);
         double var6 = this.toRenderY(var2, 0.0);
         double var8 = this.toRenderSize(var2, var3.pageWidth);
         double var10 = this.toRenderSize(var2, var3.pageHeight);
         double var12 = 8993.8;
         this.upsertPreviewOutlineBar(var1, "editor_page_outline_top", var4, var6, var8, 1.0, var12);
         this.upsertPreviewOutlineBar(var1, "editor_page_outline_bottom", var4, var6 + var10 - 1.0, var8, 1.0, var12);
         this.upsertPreviewOutlineBar(var1, "editor_page_outline_left", var4, var6, 1.0, var10, var12);
         this.upsertPreviewOutlineBar(var1, "editor_page_outline_right", var4 + var8 - 1.0, var6, 1.0, var10, var12);
      } else {
         this.removeHudById(var1, "editor_page_outline_top");
         this.removeHudById(var1, "editor_page_outline_bottom");
         this.removeHudById(var1, "editor_page_outline_left");
         this.removeHudById(var1, "editor_page_outline_right");
      }
   }

   protected void upsertPreviewOutlineBar(Player var1, String var2, double var3, double var5, double var7, double var9, double var11) {
      HudPositionCalculator.Placement var13 = this.positionCalculator.calculateBoxPlacement(var3, var5, var11, Math.max(1.0, var7), Math.max(1.0, var9));
      this.upsertOverlayHud(var1, var2, var13.location(), var13.scale(), "<#191919>\ue67b");
   }

   protected static record HoverEffectState(Vector location, Vector scale, int opacity, double rotationDeg, boolean mirrorX, boolean mirrorY) {
      protected GuiServiceEditorInteractionOverlaySupport.HoverEffectState copy() {
         Vector var1 = this.location == null ? null : this.location.clone();
         Vector var2 = this.scale == null ? null : this.scale.clone();
         return new GuiServiceEditorInteractionOverlaySupport.HoverEffectState(var1, var2, this.opacity, this.rotationDeg, this.mirrorX, this.mirrorY);
      }
   }
}
