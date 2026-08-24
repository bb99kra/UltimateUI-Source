package dev.xqedii.ultimateUI.service.gui.editor;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.gui.model.HoverElement;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.GuiService;
import dev.xqedii.ultimateUI.service.gui.editor.interaction.GuiServiceEditorInteractionOverlaySupport;
import dev.xqedii.ultimateUI.service.gui.editor.shell.managers.AnimationTimelineOperationsManagerBase;
import dev.xqedii.ultimateUI.service.gui.model.EditorPropertyField;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.gui.model.EditorSidebarTab;
import dev.xqedii.ultimateUI.service.gui.model.EditorTool;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.util.PlatformCompat;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public abstract class GuiServiceEditorInteractionSupport extends GuiServiceEditorInteractionOverlaySupport {
   private static final String ALIGN_PANEL_ID = "block_align";
   private static final String ALIGN_LR_LEFT_ID = "align_lr_left";
   private static final String ALIGN_LR_CENTER_ID = "align_lr_center";
   private static final String ALIGN_LR_RIGHT_ID = "align_lr_right";
   private static final String ALIGN_TB_TOP_ID = "align_tb_top";
   private static final String ALIGN_TB_CENTER_ID = "align_tb_center";
   private static final String ALIGN_TB_BOTTOM_ID = "align_tb_bottom";
   private static final List<String> ALIGN_TOOL_ACTION_IDS = List.of(
      "align_lr_left", "align_lr_center", "align_lr_right", "align_tb_top", "align_tb_center", "align_tb_bottom"
   );
   private static final Map<String, String[]> ALIGN_TOOL_ACTION_GLYPHS = Map.of(
      "align_lr_left",
      new String[]{"\ueed0", "\ueed6"},
      "align_lr_center",
      new String[]{"\ueed1", "\ueed7"},
      "align_lr_right",
      new String[]{"\ueed2", "\ueed8"},
      "align_tb_top",
      new String[]{"\ueed3", "\ueed9"},
      "align_tb_center",
      new String[]{"\ueed4", "\ueeda"},
      "align_tb_bottom",
      new String[]{"\ueed5", "\ueedb"}
   );
   private static final String ALIGN_TOOL_HITBOX_SUFFIX = "_hitbox";
   private static final double ALIGN_TOOL_OFFSET_Y = 7.5;
   private static final double ALIGN_TOOL_CLOSER_BY_AT_ZOOM_100 = 40.0;
   protected static final long PASTE_SELECTION_CLICK_GUARD_TICKS = 4L;

   protected GuiServiceEditorInteractionSupport(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
      this.cameraService.setCursorListener(this::onCursorMove);
   }

   @Override
   protected abstract void renderResolvedContent(Player var1, EditorSession var2);

   @Override
   protected abstract void renderEditorTransparencyOverlay(Player var1, EditorSession var2);

   protected void beginMoveDragHistory(EditorSession var1) {
      if (var1 != null) {
         var1.moveDragStartSnapshot = this.captureEditorMutationSnapshot(var1);
         var1.moveDragMutated = false;
      }
   }

   protected void finishMoveDragHistory(EditorSession var1) {
      if (var1 != null) {
         String var2 = var1.moveDragStartSnapshot;
         boolean var3 = var1.moveDragMutated;
         var1.moveDragStartSnapshot = null;
         var1.moveDragMutated = false;
         if (var3 && var2 != null && !var2.isBlank()) {
            this.recordEditorMutationSnapshot(var1, var2);
         }
      }
   }

   protected void beginResizeDragHistory(EditorSession var1) {
      if (var1 != null) {
         if (var1.resizeDragStartSnapshot == null || var1.resizeDragStartSnapshot.isBlank()) {
            var1.resizeDragStartSnapshot = this.captureEditorMutationSnapshot(var1);
            var1.resizeDragMutated = false;
         }
      }
   }

   protected void finishResizeDragHistory(EditorSession var1) {
      if (var1 != null) {
         String var2 = var1.resizeDragStartSnapshot;
         boolean var3 = var1.resizeDragMutated;
         var1.resizeDragStartSnapshot = null;
         var1.resizeDragMutated = false;
         if (var3 && var2 != null && !var2.isBlank()) {
            this.recordEditorMutationSnapshot(var1, var2);
         }
      }
   }

   public boolean clickHoveredElement(Player var1, GuiService.ClickType var2) {
      EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
      if (var3 != null && var3.editMode) {
         if (!this.isLeftHeld(var3)) {
            this.finishResizeDragHistory(var3);
         }

         long var4 = (long)Bukkit.getCurrentTick();
         if (var2 == GuiService.ClickType.LEFT && var4 <= var3.pasteSelectionClickGuardUntilTick) {
            var3.lastLeftClickTick = var4;
            return true;
         } else {
            boolean var6 = var4 - var3.lastLeftClickTick <= 2L;
            boolean var7 = var1.isSneaking();
            double var8 = var3.cursorX + var3.hitboxOffsetX;
            double var10 = var3.cursorY + var3.hitboxOffsetY;
            boolean var12 = this.hasLegacySidebarUi(var3);
            if (var3.welcomePopupVisible) {
               if (var2 == GuiService.ClickType.LEFT) {
                  var3.lastLeftClickTick = var4;
                  if (!var6) {
                     this.handleWelcomePopupClick(var1, var3, var8, var10);
                  }
               }

               this.clearAlignToolbarOverlay(var1, var3);
               return true;
            } else if (var3.savePopupVisible) {
               if (var2 == GuiService.ClickType.LEFT) {
                  this.handleSavePopupClick(var1, var3, var8, var10);
               }

               this.clearAlignToolbarOverlay(var1, var3);
               return true;
            } else if (var3.preferencesPopupVisible) {
               if (var2 == GuiService.ClickType.LEFT) {
                  var3.lastLeftClickTick = var4;
                  if (!var6) {
                     this.handlePreferencesPopupClick(var1, var3, var8, var10);
                  }
               }

               this.clearAlignToolbarOverlay(var1, var3);
               return true;
            } else {
               if (var3.activeTool == EditorTool.ALIGN && var2 == GuiService.ClickType.LEFT && var3.alignToolbarConsumeHoldClick) {
                  if (var6 || var4 <= var3.alignToolbarClickGuardUntilTick) {
                     var3.alignToolbarConsumeHoldClick = false;
                     return true;
                  }

                  var3.alignToolbarConsumeHoldClick = false;
               }

               if (var3.activeTool == EditorTool.ALIGN && this.handleAlignToolbarClick(var1, var3, var2, var8, var10)) {
                  return true;
               } else if (var3.activeTool == EditorTool.ZOOM
                  && (var2 == GuiService.ClickType.LEFT || var2 == GuiService.ClickType.RIGHT)
                  && this.isInsidePreviewArea(var3, var8, var10)) {
                  return this.handleZoomToolClick(var1, var3, var2, var8, var10);
               } else if (var2 == GuiService.ClickType.RIGHT && this.handleAnimationTimelineContextClick(var1, var3, var8, var10)) {
                  return true;
               } else if (var2 == GuiService.ClickType.RIGHT) {
                  this.setFileDropdownVisible(var1, var3, false);
                  this.setEditDropdownVisible(var1, var3, false);
                  this.setSelectionDropdownVisible(var1, var3, false);
                  this.setLayerDropdownVisible(var1, var3, false);
                  this.setWindowDropdownVisible(var1, var3, false);
                  if (var3.keyframeDropdownVisible) {
                     this.setAnimationTimelineKeyframeDropdownVisible(var1, var3, false);
                  }

                  if (var3.keyframeTimelineDropdownVisible) {
                     this.setAnimationTimelineTimelineDropdownVisible(var1, var3, false);
                  }

                  if (var3.cursorActionsDropdownVisible) {
                     this.setCursorActionsDropdownVisible(var1, var3, false);
                  }

                  if (var3.cursorActionsEditDropdownVisible) {
                     this.setCursorActionsEditDropdownVisible(var1, var3, false);
                  }

                  this.clearActionsReorderState(var3);
                  this.clearActionsReorderGhostHud(var1, var3);
                  this.updateEditorNavbarHover(var1, var3, var8, var10);
                  this.finishLayersReorderDrag(var1, var3, false);
                  this.stopCursorToolDrag(var3);
                  this.clearSelectionState(var1, var3);
                  this.clearAlignToolbarOverlay(var1, var3);
                  return true;
               } else {
                  EditorTool var13 = this.resolveAnimationTimelineSelectedTool(var3);
                  boolean var14 = var3.activeTool == EditorTool.ANIMATION && (var13 == EditorTool.CURSOR || var13 == EditorTool.SCALE);
                  boolean var15 = var3.activeTool == EditorTool.ANIMATION && this.isInsideShellBlock(var3, "animation_timeline", var8, var10);
                  EditorTool var16 = var3.activeTool;
                  if (var3.activeTool == EditorTool.ANIMATION) {
                     if (var14) {
                        if (!var15) {
                           this.normalizeAnimationPreviewForTransform(var1, var3);
                        }

                        var16 = var13;
                     } else {
                        var16 = EditorTool.COMMANDS;
                     }
                  }

                  if (var2 == GuiService.ClickType.LEFT && (var3.animationTimelineSliderDragActive || var3.animationTimelineKeyframeDragActive)) {
                     boolean var17 = var3.activeTool == EditorTool.ANIMATION
                        && (
                           this.isInsideShellBlock(var3, "animation_timeline", var8, var10)
                              || this.isInsideShellBlock(var3, "animation_slider_hitbox", var8, var10)
                              || this.isInsideShellBlock(var3, "animation_accurate_slider_hitbox", var8, var10)
                              || this.isInsideShellBlock(var3, "animation_accurate_slider_element", var8, var10)
                        );
                     boolean var18 = var3.animationTimelineKeyframeDragActive;
                     if (var6 && var17) {
                        var3.lastLeftClickTick = var4;
                        return true;
                     }

                     this.stopAnimationTimelineSliderDrag(var3);
                     this.stopAnimationTimelineKeyframeDrag(var3, true);
                     if (var18) {
                        String var19 = this.firstNonBlank(new String[]{var3.animationTimelineTargetId, var3.animationTimelineSelectedTargetId});
                        if (!var19.isBlank()) {
                           this.renderAnimationTimelineKeyframes(var1, var3, var19);
                           this.applyAnimationTimelinePositionPreview(var1, var3);
                           this.updateEditorPropertiesSidebar(var1, var3);
                        }
                     }
                  }

                  if (var2 == GuiService.ClickType.LEFT) {
                     var3.lastLeftClickTick = var4;
                  }

                  boolean var24 = var2 == GuiService.ClickType.LEFT && !var6 && this.isValidActionsSecondClickEditAttempt(var3, var8, var10, var4);
                  boolean var25 = false;
                  if (var7 && var6) {
                     HoverElement var26 = this.findHoveredElement(var3, var8, var10);
                     String var20 = var26 == null ? null : this.targetIdOf(var26);
                     String var21 = this.resolveLayerSelectionTargetByHit(var3, var8, var10);
                     var25 = var20 != null && !var20.isBlank() && !this.isSelectedTarget(var3, var20) && this.isTargetInteractable(var3, var20)
                        || var21 != null && !var21.isBlank() && !this.isSelectedTarget(var3, var21);
                  }

                  if (this.isCursorLikeTool(var16) && (var3.moveDragActive || var3.previewPanActive || var3.marqueeSelectActive)) {
                     return true;
                  } else if (var6 && !var25 && !var24) {
                     return true;
                  } else {
                     EditorTool var27 = this.findHoveredToolbarTool(var3, var8, var10);
                     String var28 = this.findHoveredHandle(var3, var8, var10);
                     HoverElement var29 = var28 != null ? this.findFirstByTargetId(var3, var3.selectedElementId) : this.findHoveredElement(var3, var8, var10);
                     String var22 = var29 == null ? null : this.targetIdOf(var29);
                     if (var27 != null) {
                        this.finishLayersReorderDrag(var1, var3, false);
                        this.setActiveTool(var1, var3, var27);
                        return true;
                     } else if (var2 == GuiService.ClickType.LEFT && this.handleEditorNavbarClick(var1, var3, var8, var10)) {
                        EditorSession var30 = this.editorSessions.get(var1.getUniqueId());
                        if (var30 == var3) {
                           this.updatePageInfoReadout(var1, var3);
                           this.updateSidebarTabVisualState(var1, var3, false, false);
                        }

                        return true;
                     } else if (var2 == GuiService.ClickType.LEFT && var12 && this.handleSidebarClick(var1, var3, var8, var10)) {
                        return true;
                     } else if (var2 == GuiService.ClickType.LEFT && this.isInsideEditorChromeBlock(var3, var8, var10)) {
                        this.finishLayersReorderDrag(var1, var3, false);
                        return true;
                     } else if (this.isCursorLikeTool(var16)) {
                        boolean var23 = this.handleCursorToolClick(var1, var3, var22, var7, var8, var10);
                        if (var3.activeTool == EditorTool.ALIGN) {
                           this.updateAlignToolbarOverlay(var1, var3, var8, var10);
                        }

                        return var23;
                     } else if (var16 == EditorTool.COMMANDS) {
                        return this.handleCommandsToolClick(var1, var3, var22, var7, var8, var10);
                     } else if (var16 == EditorTool.SCALE) {
                        return this.handleScaleToolClick(var1, var3, var28, var22, var6, var7, var4);
                     } else if (var3.activeTool == EditorTool.COLORCHANGER) {
                        return this.handleBucketFillToolClick(var1, var3, var2, var22);
                     } else if (var3.activeTool == EditorTool.COLOR_PICKER) {
                        return this.handleEyedropperToolClick(var1, var3, var2, var22);
                     } else if (var3.activeTool == EditorTool.TEXT) {
                        return this.handleTextToolClick(var1, var3, var2, var22);
                     } else {
                        return var3.activeTool == EditorTool.ZOOM ? this.handleZoomToolClick(var1, var3, var2, var8, var10) : true;
                     }
                  }
               }
            }
         }
      } else {
         return false;
      }
   }

   protected boolean handleScaleToolClick(Player var1, EditorSession var2, String var3, String var4, boolean var5, boolean var6, long var7) {
      if (var2.activeHandle != null && var5 && !"move".equals(var2.activeHandle)) {
         return true;
      } else if (var3 != null && var2.selectedElementId != null) {
         this.toggleScalingHandle(var1, var2, var3, var7);
         return true;
      } else if (var2.activeHandle != null && !"move".equals(var2.activeHandle)) {
         return true;
      } else if (var4 != null) {
         if (var6) {
            this.toggleSelectedTarget(var2, var4);
         } else {
            if (var4.equals(var2.selectedElementId) && var2.additionalSelectedElementIds.isEmpty()) {
               this.clearSelectionState(var1, var2);
               return true;
            }

            var2.selectedElementId = var4;
            var2.additionalSelectedElementIds.clear();
         }

         this.normalizeSelectionState(var2);
         var2.activeHandle = null;
         var2.resizeAnchorValid = false;
         var2.handlesCollapsed = false;
         this.updateHoveredHighlight(var1, var2, var2.hoveredElementId, null);
         var2.hoveredElementId = null;
         this.clearEditorOverlaysOnly(var1, var2);
         if (var2.selectedElementId != null) {
            this.showSelectionOverlay(var1, var2);
         }

         if (var2.selectedElementId != null || !var2.additionalSelectedElementIds.isEmpty()) {
            this.playEditorSfx(var1, var2, "object-selected");
         }

         this.refreshSidebarAfterSelectionChange(var1, var2);
         return true;
      } else if (var2.selectedElementId == null && var2.additionalSelectedElementIds.isEmpty()) {
         return false;
      } else {
         this.clearSelectionState(var1, var2);
         return true;
      }
   }

   protected boolean handleCursorToolClick(Player var1, EditorSession var2, String var3, boolean var4, double var5, double var7) {
      if (var3 == null) {
         if (var4 && this.isInsidePreviewArea(var2, var5, var7)) {
            this.startMarqueeSelection(var2, var5, var7);
            return true;
         } else if (var2.selectedElementId == null && var2.additionalSelectedElementIds.isEmpty()) {
            this.stopCursorToolDrag(var2);
            if (this.isInsidePreviewArea(var2, var5, var7)) {
               this.startPreviewPan(var2, var5, var7);
               return true;
            } else {
               return false;
            }
         } else if (var2.activeTool == EditorTool.ALIGN && !var4 && (long)Bukkit.getCurrentTick() <= var2.alignToolbarClickGuardUntilTick) {
            return true;
         } else {
            this.clearSelectionState(var1, var2);
            this.stopCursorToolDrag(var2);
            if (this.isInsidePreviewArea(var2, var5, var7)) {
               this.startPreviewPan(var2, var5, var7);
            }

            return true;
         }
      } else if (!var4 && this.isSelectedTarget(var2, var3)) {
         if (!var2.moveDragActive) {
            this.showSelectionOverlay(var1, var2);
            this.startMoveDrag(var2, var5, var7);
         }

         return true;
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
         var2.resizeAnchorValid = false;
         var2.handlesCollapsed = false;
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
            this.playEditorSfx(var1, var2, "object-selected");
         }

         this.refreshSidebarAfterSelectionChange(var1, var2);
         return true;
      }
   }

   protected void normalizeAnimationPreviewForTransform(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.activeTool == EditorTool.ANIMATION) {
         EditorTool var3 = this.resolveAnimationTimelineSelectedTool(var2);
         if (var3 == EditorTool.CURSOR || var3 == EditorTool.SCALE) {
            if (!this.isAnimationTimelineKeyframeSelected(var2) || var2.animationTimelineSelectedTick <= 0) {
               String var4 = this.firstNonBlank(new String[]{var2.animationPreviewTargetId});
               if (!var4.isBlank()) {
                  EditorRect var5 = var2.animationPreviewAppliedBounds;
                  EditorRect var6;
                  if (var5 != null) {
                     var6 = new EditorRect(var5.x, var5.y, var5.width, var5.height);
                  } else {
                     double var7 = var2.animationPreviewOffsetX;
                     double var9 = var2.animationPreviewOffsetY;
                     if (Math.abs(var7) < 1.0E-4 && Math.abs(var9) < 1.0E-4) {
                        return;
                     }

                     EditorRect var11 = this.getTargetBounds(var2, var4);
                     if (var11 == null) {
                        return;
                     }

                     var6 = new EditorRect(var11.x + var7, var11.y + var9, var11.width, var11.height);
                  }

                  this.clearAnimationTimelinePreviewOffset(var1, var2);
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

   protected boolean handleCommandsToolClick(Player var1, EditorSession var2, String var3, boolean var4, double var5, double var7) {
      if (var1 == null || var2 == null) {
         return false;
      } else if (var3 != null && !var3.isBlank()) {
         if (var4) {
            this.toggleSelectedTarget(var2, var3);
         } else {
            var2.selectedElementId = var3;
            var2.additionalSelectedElementIds.clear();
         }

         this.normalizeSelectionState(var2);
         var2.activeHandle = null;
         var2.resizeAnchorValid = false;
         var2.handlesCollapsed = false;
         this.updateHoveredHighlight(var1, var2, var2.hoveredElementId, null);
         var2.hoveredElementId = null;
         this.clearEditorOverlaysOnly(var1, var2);
         if (var2.selectedElementId != null) {
            this.showSelectionOverlay(var1, var2);
         }

         if (var2.selectedElementId != null || !var2.additionalSelectedElementIds.isEmpty()) {
            this.playEditorSfx(var1, var2, "object-selected");
         }

         this.refreshSidebarAfterSelectionChange(var1, var2);
         return true;
      } else if (!this.isInsidePreviewArea(var2, var5, var7)) {
         if (var2.selectedElementId == null && var2.additionalSelectedElementIds.isEmpty()) {
            return false;
         } else {
            this.clearSelectionState(var1, var2);
            return true;
         }
      } else {
         if (var2.selectedElementId != null || !var2.additionalSelectedElementIds.isEmpty()) {
            this.clearSelectionState(var1, var2);
         }

         this.startPreviewPan(var2, var5, var7);
         return true;
      }
   }

   protected void clearSelectionState(Player var1, EditorSession var2) {
      if (var2 != null) {
         this.finishLayersReorderDrag(var1, var2, false);
         this.stopMarqueeSelection(var1, var2);
         List var3 = this.getSelectedTargetIds(var2);
         boolean var4 = var2.activeHandle != null || var2.handlesCollapsed;
         this.updateHoveredHighlight(var1, var2, var2.hoveredElementId, null);
         var2.hoveredElementId = null;
         var2.selectedElementId = null;
         var2.additionalSelectedElementIds.clear();
         var2.activeHandle = null;
         var2.resizeAnchorValid = false;
         var2.handlesCollapsed = false;
         var2.hoveredHandle = null;
         var2.selectionOutlineVisible = false;
         var2.pendingPropertyField = null;
         this.clearPendingTextToolEdit(var1, var2, true);
         var2.pendingActionTargetId = null;
         var2.pendingActionIndex = -1;
         this.clearActionsReorderState(var2);
         this.clearActionsReorderGhostHud(var1, var2);
         this.stopOpacitySliderDrag(var2, true);
         this.stopAnimationTimelineSliderDrag(var2);
         this.stopAnimationTimelineKeyframeDrag(var2, true);
         this.clearAnimationTimelineSelectedKeyframe(var2);
         var2.alignToolbarClickGuardUntilTick = -2305843009213693952L;
         var2.alignToolbarConsumeHoldClick = false;
         var2.colorPickerGradDragActive = false;
         var2.colorPickerHueDragActive = false;
         this.resetSidebarFieldDrag(var1, var2);

         for (Object var6_raw : var3) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            if (var4) {
               this.restoreElementLayer(var1, var2, var6);
            }
         }

         this.clearEditorOverlaysOnly(var1, var2);
         this.clearAlignToolbarOverlay(var1, var2);
         var2.rightSidebarHoverTab = null;
         this.updateSidebarTabVisualState(var1, var2, false, false);
         this.updateEditorPropertiesSidebar(var1, var2);
         this.renderLayersPanel(var1, var2);
      }
   }

   protected void refreshSidebarAfterSelectionChange(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         this.updateEditorPropertiesSidebar(var1, var2);
         this.renderLayersPanel(var1, var2);
      }
   }

   @Override
   protected void setActiveEditorTool(Player var1, EditorSession var2, EditorTool var3) {
      this.setActiveTool(var1, var2, var3);
   }

   protected void setActiveTool(Player var1, EditorSession var2, EditorTool var3) {
      if (var2 != null && var3 != null && var2.activeTool != var3) {
         this.finishLayersReorderDrag(var1, var2, false);
         EditorTool var4 = var2.activeTool;
         boolean var5 = var2.selectedElementId != null || !var2.additionalSelectedElementIds.isEmpty();
         boolean var6 = (this.isCursorLikeTool(var4) || var4 == EditorTool.SCALE) && (this.isCursorLikeTool(var3) || var3 == EditorTool.SCALE);
         boolean var7 = (this.isCursorLikeTool(var4) || var4 == EditorTool.SCALE) && var3 == EditorTool.ANIMATION
            || var4 == EditorTool.ANIMATION && (this.isCursorLikeTool(var3) || var3 == EditorTool.SCALE);
         boolean var8 = this.isCursorLikeTool(var4) && var3 == EditorTool.COMMANDS || var4 == EditorTool.COMMANDS && this.isCursorLikeTool(var3);
         boolean var9 = var5 && (var6 || var7 || var8 || this.getSelectedTargetIds(var2).size() > 1);
         if (!var9 && var5) {
            this.clearSelectionState(var1, var2);
         }

         boolean var10 = var2.activeHandle != null || var2.handlesCollapsed;

         for (String var12 : this.getSelectedTargetIds(var2)) {
            if (var10) {
               this.restoreElementLayer(var1, var2, var12);
            }
         }

         this.stopCursorToolDrag(var2);
         this.stopMarqueeSelection(var1, var2);
         this.resetSidebarFieldDrag(var1, var2);
         this.stopAnimationTimelineSliderDrag(var2);
         this.stopAnimationTimelineKeyframeDrag(var2, true);
         this.clearAnimationTimelineSelectedKeyframe(var2);
         var2.alignToolbarClickGuardUntilTick = -2305843009213693952L;
         var2.alignToolbarConsumeHoldClick = false;
         this.clearPendingTextToolEdit(var1, var2, true);
         var2.pendingActionTargetId = null;
         var2.pendingActionIndex = -1;
         var2.activeTool = var3;
         this.playEditorSfx(var1, var2, "tool-selected");
         if (var3 == EditorTool.COMMANDS) {
            var2.rightSidebarTab = EditorSidebarTab.PROPERTIES;
            var2.rightSidebarHoverTab = null;
         }

         var2.activeHandle = null;
         var2.resizeAnchorValid = false;
         var2.handlesCollapsed = false;
         var2.hoveredHandle = null;
         this.renderEditorToolSidebar(var1, var2);
         this.clearEditorOverlaysOnly(var1, var2);
         if (var9 && var5) {
            this.showSelectionOverlay(var1, var2);
         }

         this.beginShellOpacityBatch(var2);

         try {
            this.updateSidebarTabVisualState(var1, var2, false, false);
            this.updateEditorPropertiesSidebar(var1, var2);
         } finally {
            this.endShellOpacityBatch(var1, var2);
         }

         if (var3 == EditorTool.ANIMATION) {
            var2.animationTimelineTick = 0;
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

   @Override
   protected void startMoveDrag(EditorSession var1, double var2, double var4) {
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
            var1.moveDragActive = true;
         }
      }
   }

   protected LinkedHashSet<String> resolveMoveDragTargetIds(EditorSession var1) {
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
               EditorTool var4 = this.resolveAnimationTimelineSelectedTool(var1);
               if (var4 == EditorTool.CURSOR || var4 == EditorTool.SCALE) {
                  return var2;
               }
            }

            for (Object var5_raw : var3) {
               String var5 = var5_raw != null ? var5_raw.toString() : null;
               if (var5 != null && !var5.isBlank()) {
                  var2.addAll(this.resolveDescendantLayerTargetIds(var1, var5));
               }
            }

            return var2;
         }
      }
   }

   protected void translateAnimationMoveDragDescendants(Player var1, EditorSession var2, String var3, double var4, double var6) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         if (!(Math.abs(var4) < 1.0E-4) || !(Math.abs(var6) < 1.0E-4)) {
            if (var2.activeTool == EditorTool.ANIMATION) {
               EditorTool var8 = this.resolveAnimationTimelineSelectedTool(var2);
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

   protected void startPreviewPan(EditorSession var1, double var2, double var4) {
      this.stopCursorToolDrag(var1);
      if (var1 != null && var1.previewMode && var1.previewViewport != null && this.isInsidePreviewArea(var1, var2, var4)) {
         var1.previewPanActive = true;
         var1.previewPanStartCursorX = var2;
         var1.previewPanStartCursorY = var4;
         var1.previewPanStartX = var1.previewViewport.panX;
         var1.previewPanStartY = var1.previewViewport.panY;
      }
   }

   @Override
   protected void stopCursorToolDrag(EditorSession var1) {
      if (var1 != null) {
         if (var1.moveDragActive) {
            this.finishMoveDragHistory(var1);
         } else {
            var1.moveDragStartSnapshot = null;
            var1.moveDragMutated = false;
         }

         var1.moveDragActive = false;
         var1.previewPanActive = false;
         var1.moveStartBounds.clear();
      }
   }

   protected void startMarqueeSelection(EditorSession var1, double var2, double var4) {
      if (var1 != null) {
         this.stopCursorToolDrag(var1);
         var1.marqueeSelectActive = true;
         var1.marqueeStartCursorX = var2;
         var1.marqueeStartCursorY = var4;
      }
   }

   protected void stopMarqueeSelection(Player var1, EditorSession var2) {
      if (var2 != null) {
         var2.marqueeSelectActive = false;
         this.removeOverlayHud(var1, "editor_marquee_top");
         this.removeOverlayHud(var1, "editor_marquee_bottom");
         this.removeOverlayHud(var1, "editor_marquee_left");
         this.removeOverlayHud(var1, "editor_marquee_right");
      }
   }

   protected void updateMarqueeSelectionOverlay(Player var1, EditorSession var2, double var3, double var5) {
      EditorRect var7 = this.getMarqueeLogicalRect(var2, var3, var5);
      if (var7 == null) {
         this.stopMarqueeSelection(var1, var2);
      } else {
         double var8 = this.toRenderX(var2, var7.x);
         double var10 = this.toRenderY(var2, var7.y);
         double var12 = this.toRenderSize(var2, var7.width);
         double var14 = this.toRenderSize(var2, var7.height);
         double var16 = 8994.0;
         this.upsertOverlayHud(
            var1,
            "editor_marquee_top",
            this.positionCalculator.calculateBoxPlacement(var8, var10, var16, Math.max(1.0, var12), 4.0).location(),
            this.positionCalculator.calculateBoxPlacement(var8, var10, var16, Math.max(1.0, var12), 4.0).scale(),
            "<#84a0c8>\ue67b"
         );
         this.upsertOverlayHud(
            var1,
            "editor_marquee_bottom",
            this.positionCalculator.calculateBoxPlacement(var8, var10 + var14 - 4.0, var16, Math.max(1.0, var12), 4.0).location(),
            this.positionCalculator.calculateBoxPlacement(var8, var10 + var14 - 4.0, var16, Math.max(1.0, var12), 4.0).scale(),
            "<#84a0c8>\ue67b"
         );
         this.upsertOverlayHud(
            var1,
            "editor_marquee_left",
            this.positionCalculator.calculateBoxPlacement(var8, var10, var16, 4.0, Math.max(1.0, var14)).location(),
            this.positionCalculator.calculateBoxPlacement(var8, var10, var16, 4.0, Math.max(1.0, var14)).scale(),
            "<#84a0c8>\ue67b"
         );
         this.upsertOverlayHud(
            var1,
            "editor_marquee_right",
            this.positionCalculator.calculateBoxPlacement(var8 + var12 - 4.0, var10, var16, 4.0, Math.max(1.0, var14)).location(),
            this.positionCalculator.calculateBoxPlacement(var8 + var12 - 4.0, var10, var16, 4.0, Math.max(1.0, var14)).scale(),
            "<#84a0c8>\ue67b"
         );
      }
   }

   protected void finalizeMarqueeSelection(Player var1, EditorSession var2, double var3, double var5) {
      EditorRect var7 = this.getMarqueeLogicalRect(var2, var3, var5);
      this.stopMarqueeSelection(var1, var2);
      if (var7 != null) {
         LinkedHashSet var8 = new LinkedHashSet();

         for (Object var10_raw : var2.elements) {
            HoverElement var10 = (HoverElement)var10_raw;
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
            this.refreshSidebarAfterSelectionChange(var1, var2);
         } else {
            this.clearSelectionState(var1, var2);
         }
      }
   }

   protected EditorRect getMarqueeLogicalRect(EditorSession var1, double var2, double var4) {
      if (var1 == null) {
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
         return new EditorRect(var14, var16, Math.max(0.0, var18 - var14), Math.max(0.0, var20 - var16));
      }
   }

   protected boolean rectsIntersect(EditorRect var1, EditorRect var2) {
      return var1 != null && var2 != null ? var1.x <= var2.maxX() && var1.maxX() >= var2.x && var1.y <= var2.maxY() && var1.maxY() >= var2.y : false;
   }

   protected void onCursorMove(Player var1, double var2, double var4) {
      EditorSession var6 = this.editorSessions.get(var1.getUniqueId());
      if (var6 != null && var6.editMode) {
         var6.cursorX = var2;
         var6.cursorY = var4;
         boolean var7 = var1.isSneaking();
         double var8 = var2 + var6.hitboxOffsetX;
         double var10 = var4 + var6.hitboxOffsetY;
         EditorTool var12 = this.resolveAnimationTimelineSelectedTool(var6);
         boolean var13 = var6.activeTool == EditorTool.ANIMATION && var12 == EditorTool.CURSOR;
         boolean var14 = var6.activeTool == EditorTool.ANIMATION && var12 == EditorTool.SCALE;
         boolean var15 = var6.opacitySliderDragActive
            || var6.animationTimelineSliderDragActive
            || var6.animationTimelineKeyframeDragActive
            || var6.sidebarFieldDragActive
            || var6.colorPickerGradDragActive
            || var6.colorPickerHueDragActive
            || var6.moveDragActive
            || var6.previewPanActive
            || var6.marqueeSelectActive
            || var6.activeHandle != null
            || var6.layersReorderActive
            || var6.layersReorderArmedTargetId != null && !var6.layersReorderArmedTargetId.isBlank()
            || var6.actionsReorderArmedIndex >= 0
            || var6.actionsReorderCurrentIndex >= 0;
         if (var15
            || !Double.isFinite(var6.lastProcessedHitX)
            || !Double.isFinite(var6.lastProcessedHitY)
            || !(Math.abs(var8 - var6.lastProcessedHitX) < 1.0E-4)
            || !(Math.abs(var10 - var6.lastProcessedHitY) < 1.0E-4)) {
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
               this.updateAnimationTimelineKeyframeDropdownHover(var1, var6, var8, var10);
               this.updateAnimationTimelineTimelineDropdownHover(var1, var6, var8, var10);
               this.updateCursorActionsDropdownHover(var1, var6, var8, var10);
               this.updateCursorActionsEditDropdownHover(var1, var6, var8, var10);
               if (!this.isLeftHeld(var6)) {
                  this.finishResizeDragHistory(var6);
               }

               if (!this.isLeftHeld(var6) && var6.opacitySliderDragActive) {
                  this.stopOpacitySliderDrag(var6, true);
               }

               if (var6.opacitySliderDragActive && this.isLeftHeld(var6)) {
                  this.updateOpacitySliderDrag(var1, var6, var8);
               } else {
                  if (!this.isLeftHeld(var6) && var6.animationTimelineSliderDragActive) {
                     this.stopAnimationTimelineSliderDrag(var6);
                     this.applyAnimationTimelinePositionPreview(var1, var6);
                     this.updateEditorPropertiesSidebar(var1, var6);
                  }

                  if (var6.animationTimelineSliderDragActive && this.isLeftHeld(var6)) {
                     this.updateAnimationTimelineSliderDrag(var1, var6, var8);
                  } else {
                     if (!this.isLeftHeld(var6) && var6.animationTimelineKeyframeDragActive) {
                        this.stopAnimationTimelineKeyframeDrag(var6, true);
                        String var16 = this.firstNonBlank(new String[]{var6.animationTimelineTargetId, var6.animationTimelineSelectedTargetId});
                        if (!var16.isBlank()) {
                           this.renderAnimationTimelineKeyframes(var1, var6, var16);
                           this.applyAnimationTimelinePositionPreview(var1, var6);
                           this.updateEditorPropertiesSidebar(var1, var6);
                        }
                     }

                     if (var6.animationTimelineKeyframeDragActive && this.isLeftHeld(var6)) {
                        this.updateAnimationTimelineKeyframeDrag(var1, var6, var8);
                     } else {
                        if (!this.isLeftHeld(var6) && var6.colorPickerGradDragActive) {
                           var6.colorPickerGradDragActive = false;
                        }

                        if (var6.colorPickerGradDragActive && this.isLeftHeld(var6)) {
                           this.updateColorPickerGradientDrag(var1, var6, var8, var10);
                        } else {
                           if (!this.isLeftHeld(var6) && var6.colorPickerHueDragActive) {
                              var6.colorPickerHueDragActive = false;
                           }

                           if (var6.colorPickerHueDragActive && this.isLeftHeld(var6)) {
                              this.updateColorPickerHueDrag(var1, var6, var8, var10);
                           } else {
                              if (!this.isLeftHeld(var6) && var6.sidebarFieldDragActive) {
                                 EditorPropertyField var29 = var6.sidebarFieldDragField;
                                 boolean var17 = var29 != null
                                    && this.isDimensionSidebarField(var29)
                                    && var6.sidebarFieldDragLastValue == null
                                    && !var6.sidebarFieldDragMutated;
                                 this.resetSidebarFieldDrag(var1, var6);
                                 if (var17) {
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
                                       this.stopCursorToolDrag(var6);
                                       if (var18) {
                                          this.finalizeMarqueeSelection(var1, var6, var8, var10);
                                       } else if (!var30 || !this.isCursorLikeTool(var6.activeTool) && !var13) {
                                          if (var30 && (var6.activeTool == EditorTool.SCALE || var14) && "move".equals(var6.activeHandle)) {
                                             var6.activeHandle = null;
                                             var6.resizeAnchorValid = false;
                                             var6.handlesCollapsed = false;
                                             this.renderSelectionOverlay(var1, var6);
                                             if (var14) {
                                                this.syncAnimationTimelineSelectedKeyframeValueFromTarget(var6);
                                                this.renderAnimationTimelineKeyframes(
                                                   var1, var6, this.firstNonBlank(new String[]{var6.animationTimelineTargetId})
                                                );
                                                this.applyAnimationTimelinePositionPreview(var1, var6);
                                             }

                                             this.updateEditorPropertiesSidebar(var1, var6);
                                          } else if (var35) {
                                             this.animatePreviewPanBackToBounds(var1, var6, var8, var10);
                                          }
                                       } else {
                                          this.renderSelectionOverlay(var1, var6);
                                          if (var13) {
                                             String var19 = this.firstNonBlank(
                                                new String[]{var6.animationTimelineTargetId, var6.animationTimelineSelectedTargetId}
                                             );
                                             this.syncAnimationTimelinePreviewAppliedBoundsToCurrent(var6, var19);
                                             this.syncAnimationTimelineSelectedKeyframeValueFromTarget(var6);
                                             this.renderAnimationTimelineKeyframes(var1, var6, this.firstNonBlank(new String[]{var6.animationTimelineTargetId}));
                                          }

                                          this.updateEditorPropertiesSidebar(var1, var6);
                                       }
                                    }

                                    if (!this.isLeftHeld(var6)
                                       && (var6.activeTool == EditorTool.SCALE || var14)
                                       && var6.activeHandle != null
                                       && var6.resizeStepReady) {
                                       if (var14 && !var6.handlesCollapsed) {
                                          this.syncAnimationTimelineSelectedKeyframeValueFromTarget(var6);
                                          this.renderAnimationTimelineKeyframes(var1, var6, this.firstNonBlank(new String[]{var6.animationTimelineTargetId}));
                                          this.applyAnimationTimelinePositionPreview(var1, var6);
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
                                       var6.resizeAnchorValid = false;
                                       var6.handlesCollapsed = false;
                                       if (var14) {
                                          this.renderSelectionOverlay(var1, var6);
                                          this.updateEditorPropertiesSidebar(var1, var6);
                                          return;
                                       }

                                       this.restoreHandlesOnly(var1, var6);
                                    }

                                    if (!this.isLeftHeld(var6)
                                       && var14
                                       && var6.activeHandle == null
                                       && !var6.handlesCollapsed
                                       && var6.resizeStepReady
                                       && !this.isLayersLeftHeld(var6)) {
                                       this.syncAnimationTimelineSelectedKeyframeValueFromTarget(var6);
                                       this.renderAnimationTimelineKeyframes(var1, var6, this.firstNonBlank(new String[]{var6.animationTimelineTargetId}));
                                       this.applyAnimationTimelinePositionPreview(var1, var6);
                                       this.updateEditorPropertiesSidebar(var1, var6);
                                       var6.resizeStepReady = false;
                                    }

                                    if ((this.isCursorLikeTool(var6.activeTool) || var13) && this.isLeftHeld(var6)) {
                                       if (var6.marqueeSelectActive) {
                                          this.updateMarqueeSelectionOverlay(var1, var6, var8, var10);
                                          return;
                                       }

                                       if (var6.moveDragActive && var6.selectedElementId != null) {
                                          this.updateMoveDrag(var1, var6, var8, var10, var7);
                                          if (var13) {
                                             String var34 = this.firstNonBlank(
                                                new String[]{var6.animationTimelineTargetId, var6.animationTimelineSelectedTargetId}
                                             );
                                             this.syncAnimationTimelinePreviewAppliedBoundsToCurrent(var6, var34);
                                             this.syncAnimationTimelineSelectedKeyframeValueFromTarget(var6);
                                             this.renderAnimationTimelineKeyframes(var1, var6, this.firstNonBlank(new String[]{var6.animationTimelineTargetId}));
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
                                       if ((var6.activeTool == EditorTool.SCALE || var14)
                                          && var6.activeHandle != null
                                          && var6.selectedElementId != null
                                          && this.isLeftHeld(var6)) {
                                          if ("move".equals(var6.activeHandle)) {
                                             if (var6.moveDragActive) {
                                                this.updateMoveDrag(var1, var6, var8, var10, var7);
                                                if (var14) {
                                                   this.syncAnimationTimelineSelectedKeyframeValueFromTarget(var6);
                                                   this.renderAnimationTimelineKeyframes(
                                                      var1, var6, this.firstNonBlank(new String[]{var6.animationTimelineTargetId})
                                                   );
                                                }
                                             }

                                             return;
                                          }

                                          boolean var32 = var7 != var6.lastShiftHeld;
                                          if (!var6.resizeAnchorValid) {
                                             this.captureResizeAnchor(var6);
                                          }

                                          EditorRect var37 = new EditorRect(
                                             var6.resizeSelectionStartX, var6.resizeSelectionStartY, var6.resizeSelectionStartW, var6.resizeSelectionStartH
                                          );
                                          if (var37.width > 0.0 || var37.height > 0.0) {
                                             double var41 = this.toLogicalCursorX(var6, var8);
                                             double var20 = this.toLogicalCursorY(var6, var10);
                                             if (!var32 && var6.resizeStepReady) {
                                                double var22 = Math.abs(var41 - var6.resizeLastCursorX);
                                                double var24 = Math.abs(var20 - var6.resizeLastCursorY);
                                                if (var22 < 1.0 && var24 < 1.0) {
                                                   return;
                                                }
                                             }

                                             EditorRect var23 = this.resizeRect(var6, var37, var6.activeHandle, var41, var20, var7);

                                             for (Map.Entry<?, ?> var25 : ((Map<?, ?>)var6.resizeStartBounds).entrySet()) {
                                                String var26 = (String)var25.getKey();
                                                EditorRect var27 = (EditorRect)var25.getValue();
                                                EditorRect var28 = this.transformBounds(var6, var27, var37, var23);
                                                if (var27 != null && var28 != null && !this.sameRect(var27, var28)) {
                                                   this.beginResizeDragHistory(var6);
                                                   var6.resizeDragMutated = true;
                                                }

                                                this.applyBoundsToTarget(var6, var26, var28);
                                             }

                                             if (var14) {
                                                this.syncAnimationTimelineSelectedKeyframeValueFromTarget(var6);
                                                this.renderAnimationTimelineKeyframes(
                                                   var1, var6, this.firstNonBlank(new String[]{var6.animationTimelineTargetId})
                                                );
                                             }

                                             var6.lastShiftHeld = var7;
                                             var6.resizeLastCursorX = var41;
                                             var6.resizeLastCursorY = var20;
                                             var6.resizeStepReady = true;
                                             this.rerenderEditableSelection(var1, var6);
                                             this.updateEditorPropertiesSidebar(var1, var6);
                                             return;
                                          }
                                       }

                                       boolean var33 = var6.activeTool == EditorTool.ALIGN
                                          && var6.alignToolbarVisible
                                          && this.isInsideAlignToolbar(var6, var8, var10);
                                       String var38 = var33 ? null : this.findHoveredHandle(var6, var8, var10);
                                       Object var39 = null;
                                       Object var42 = null;
                                       if (var33) {
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

   protected boolean isCursorLikeTool(EditorTool var1) {
      return var1 == EditorTool.CURSOR || var1 == EditorTool.ALIGN;
   }

   protected boolean isAnyEditorDropdownVisible(EditorSession var1) {
      return var1 == null
         ? false
         : var1.fileDropdownVisible
            || var1.editDropdownVisible
            || var1.selectionDropdownVisible
            || var1.layerDropdownVisible
            || var1.windowDropdownVisible
            || var1.keyframeDropdownVisible
            || var1.keyframeTimelineDropdownVisible
            || var1.cursorPageDropdownVisible
            || var1.cursorLayersDropdownVisible
            || var1.cursorActionsDropdownVisible
            || var1.cursorActionsEditDropdownVisible;
   }

   protected boolean isAnyEditorPopupVisible(EditorSession var1) {
      return var1 == null ? false : var1.welcomePopupVisible || var1.savePopupVisible || var1.preferencesPopupVisible;
   }

   protected boolean handleZoomToolClick(Player var1, EditorSession var2, GuiService.ClickType var3, double var4, double var6) {
      if (var1 == null || var2 == null || var3 == null || !var2.previewMode) {
         return false;
      } else if (var3 != GuiService.ClickType.LEFT && var3 != GuiService.ClickType.RIGHT) {
         return false;
      } else if (!this.isInsidePreviewArea(var2, var4, var6)) {
         return false;
      } else if (this.isAnyEditorPopupVisible(var2)) {
         return true;
      } else if (this.isAnyEditorDropdownVisible(var2)) {
         return true;
      } else {
         double var8 = var3 == GuiService.ClickType.LEFT ? 1.12 : 0.8928571428571428;
         return this.applyPreviewZoomAtCursor(var1, var2, var8, var4, var6);
      }
   }

   protected boolean applyPreviewZoomAtCursor(Player var1, EditorSession var2, double var3, double var5, double var7) {
      if (var1 != null && var2 != null && Double.isFinite(var3) && !(var3 <= 0.0)) {
         double var9 = var5;
         double var11 = var7;
         Entity var13 = this.hudService.getHud(var1, "cursor");
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
            return true;
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
            this.updateAlignToolbarOverlay(var1, var2, var9, var11, true);
            return true;
         }
      } else {
         return false;
      }
   }

   protected boolean handleBucketFillToolClick(Player var1, EditorSession var2, GuiService.ClickType var3, String var4) {
      if (var1 == null || var2 == null || var3 != GuiService.ClickType.LEFT) {
         return false;
      } else if (var4 != null && !var4.isBlank()) {
         String var5 = this.normalizeHexColor(var2.editorColor1);
         if (var5.isBlank()) {
            var5 = "ffffff";
         }

         if (!this.setTargetColor(var2, var4, var5)) {
            return true;
         } else {
            this.playEditorSfx(var1, var2, "bucket-painted");
            this.recordEditorMutation(var2);
            this.rerenderEditableSelection(var1, var2);
            this.updateAlignToolbarOverlay(var1, var2, var2.cursorX + var2.hitboxOffsetX, var2.cursorY + var2.hitboxOffsetY);
            return true;
         }
      } else {
         return false;
      }
   }

   protected boolean handleEyedropperToolClick(Player var1, EditorSession var2, GuiService.ClickType var3, String var4) {
      if (var1 == null || var2 == null || var3 != GuiService.ClickType.LEFT) {
         return false;
      } else if (var4 != null && !var4.isBlank()) {
         String var5 = this.normalizeHexColor(this.readTargetColor(var2, var4));
         if (var5.isBlank()) {
            var5 = "ffffff";
         }

         var2.editorColor1 = var5;
         this.setShellColor(var1, var2, "editor_color_1", var5);
         if (var2.colorPickerVisible && var2.colorPickerSlot == 1) {
            this.syncColorPickerToColor(var1, var2, var5);
         }

         this.notifyColorPickerColorsChanged(var1, var2);
         this.playEditorSfx(var1, var2, "color-picked");
         return true;
      } else {
         return false;
      }
   }

   protected boolean handleTextToolClick(Player var1, EditorSession var2, GuiService.ClickType var3, String var4) {
      if (var1 == null || var2 == null || var3 != GuiService.ClickType.LEFT) {
         return false;
      } else if (var4 == null || var4.isBlank()) {
         return false;
      } else if (!this.isTextTarget(var2, var4)) {
         return true;
      } else {
         if (!this.equalsNullable(var2.selectedElementId, var4) || !var2.additionalSelectedElementIds.isEmpty()) {
            var2.selectedElementId = var4;
            var2.additionalSelectedElementIds.clear();
            this.normalizeSelectionState(var2);
            var2.activeHandle = null;
            var2.resizeAnchorValid = false;
            var2.handlesCollapsed = false;
            this.updateHoveredHighlight(var1, var2, var2.hoveredElementId, null);
            var2.hoveredElementId = null;
            this.clearEditorOverlaysOnly(var1, var2);
            this.showSelectionOverlay(var1, var2);
            this.updateEditorPropertiesSidebar(var1, var2);
         }

         var2.pendingPropertyField = null;
         var2.pendingSavePopupField = null;
         var2.pendingActionTargetId = null;
         var2.pendingActionIndex = -1;
         var2.colorPickerHexInputPending = false;
         this.beginPendingTextToolEdit(var1, var2, var4);
         this.sendEditorPlayerMessage(var1, MM.deserialize("<#84a0c8>Text tool <gray>- type text in chat or <white>cancel</white>.</gray>"));
         return true;
      }
   }

   protected void beginPendingTextToolEdit(Player var1, EditorSession var2, String var3) {
      if (var2 != null && var3 != null && !var3.isBlank()) {
         this.clearPendingTextToolEdit(var1, var2, true);
         String var4 = this.readTextTargetRawValue(var2, var3);
         var2.pendingTextToolTargetId = var3;
         var2.pendingTextToolOriginalText = var4;
         var2.pendingTextToolPlaceholderActive = false;
         boolean var5 = this.setTargetText(var2, var3, "Enter text on chat...");
         var2.pendingTextToolPlaceholderActive = var5 && !this.equalsNullable(var4, "Enter text on chat...");
         if (var5) {
            this.rerenderEditableSelection(var1, var2);
         }
      }
   }

   protected void clearPendingTextToolEdit(Player var1, EditorSession var2, boolean var3) {
      if (var2 != null) {
         String var4 = this.firstNonBlank(new String[]{var2.pendingTextToolTargetId});
         String var5 = var2.pendingTextToolOriginalText == null ? "" : var2.pendingTextToolOriginalText;
         boolean var6 = var2.pendingTextToolPlaceholderActive;
         var2.pendingTextToolTargetId = null;
         var2.pendingTextToolOriginalText = null;
         var2.pendingTextToolPlaceholderActive = false;
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

   protected String readTextTargetRawValue(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
            if (!"text".equalsIgnoreCase(this.firstNonBlank(new String[]{var3.type}))) {
               return "";
            } else {
               Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
               if (var4 == null) {
                  return "";
               } else {
                  String var5;
                  if (this.hasMapPath(var4, "text")) {
                     var5 = "text";
                  } else if (this.hasMapPath(var4, "params.text")) {
                     var5 = "params.text";
                  } else {
                     var5 = var4.containsKey("params") ? "params.text" : "text";
                  }

                  return this.stringValue(this.readMapPathValue(var4, var5));
               }
            }
         } else {
            return "";
         }
      } else {
         return "";
      }
   }

   protected boolean isTextTarget(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         return var3 == null ? false : "text".equalsIgnoreCase(this.firstNonBlank(new String[]{var3.type}));
      } else {
         return false;
      }
   }

   protected boolean handleAlignToolbarClick(Player var1, EditorSession var2, GuiService.ClickType var3, double var4, double var6) {
      if (var1 == null || var2 == null || var2.activeTool != EditorTool.ALIGN || var3 == null) {
         return false;
      } else if (!var2.alignToolbarVisible) {
         return false;
      } else {
         boolean var8 = this.isInsideAlignToolbar(var2, var4, var6);
         String var9 = this.resolveHoveredAlignToolbarActionId(var2, var4, var6);
         if (var3 == GuiService.ClickType.RIGHT) {
            return var8 || var9 != null;
         } else if (var3 != GuiService.ClickType.LEFT) {
            return false;
         } else if (var9 == null) {
            return var8;
         } else {
            var2.alignToolbarClickGuardUntilTick = (long)Bukkit.getCurrentTick() + 2L + 2L;
            var2.alignToolbarConsumeHoldClick = true;
            this.applyAlignToolbarAction(var1, var2, var9);
            return true;
         }
      }
   }

   protected void updateAlignToolbarOverlay(Player var1, EditorSession var2, double var3, double var5) {
      this.updateAlignToolbarOverlay(var1, var2, var3, var5, false);
   }

   protected void updateAlignToolbarOverlay(Player var1, EditorSession var2, double var3, double var5, boolean var7) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode && var2.activeTool == EditorTool.ALIGN) {
         EditorRect var8 = this.getSelectedBounds(var2);
         if (var8 == null) {
            this.clearAlignToolbarOverlay(var1, var2);
         } else {
            EditorRect var9 = this.findShellBlockRect(var2, "block_align");
            if (var9 == null) {
               this.clearAlignToolbarOverlay(var1, var2);
            } else {
               double var10 = this.toRenderX(var2, var8.x);
               double var12 = this.toRenderY(var2, var8.y);
               double var14 = this.toRenderSize(var2, var8.width);
               double var16 = var10 + (var14 - var9.width) / 2.0;
               double var18 = var12 - 7.5 - var9.height + this.toRenderSize(var2, 40.0);
               if (var2.previewViewport != null) {
                  double var20 = var2.previewViewport.previewX;
                  double var22 = var2.previewViewport.previewX + var2.previewViewport.previewWidth - var9.width;
                  var16 = Math.max(var20, Math.min(var22, var16));
                  var18 = Math.max(var2.previewViewport.previewY, var18);
               }

               this.setAlignToolbarVisible(var1, var2, true);
               this.moveAlignToolbarTo(var1, var2, var16, var18, var7);
               EditorRect var24 = this.findShellBlockRect(var2, "block_align");
               var2.alignToolbarBounds = var24 == null ? new EditorRect(var16, var18, var9.width, var9.height) : var24;
               var2.alignToolbarActionRects.clear();
               String var21 = this.resolveHoveredAlignToolbarActionId(var2, var3, var5);
               if (!this.equalsNullable(var2.alignToolbarHoverActionId, var21)) {
                  var2.alignToolbarHoverActionId = var21;

                  for (Object var23_raw : ALIGN_TOOL_ACTION_IDS) {
                     String var23 = var23_raw != null ? var23_raw.toString() : null;
                     this.setAlignToolbarActionVisual(var1, var23, this.equalsNullable(var23, var21));
                  }
               }
            }
         }
      } else {
         this.clearAlignToolbarOverlay(var1, var2);
      }
   }

   protected void clearAlignToolbarOverlay(Player var1, EditorSession var2) {
      if (var1 != null) {
         for (Object var4_raw : ALIGN_TOOL_ACTION_IDS) {
            String var4 = var4_raw != null ? var4_raw.toString() : null;
            this.setAlignToolbarActionVisual(var1, var4, false);
         }

         this.removeOverlayHud(var1, "block_align");
         this.removeOverlayHud(var1, "block_align_separator");

         for (Object var6_raw : ALIGN_TOOL_ACTION_IDS) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            this.removeOverlayHud(var1, var6);
         }
      }

      if (var2 != null) {
         if (var1 != null) {
            this.setAlignToolbarVisible(var1, var2, false);
         } else {
            var2.alignToolbarVisible = false;
            var2.alignToolbarHoverActionId = null;
            var2.alignToolbarBounds = null;
            var2.alignToolbarActionRects.clear();
         }
      }
   }

   protected boolean isInsideAlignToolbar(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.alignToolbarVisible) {
         double var6 = var2 - var1.hitboxOffsetX;
         double var8 = var4 - var1.hitboxOffsetY;
         if (!this.isInsideShellBlock(var1, "block_align", var6, var8) && !this.isInsideShellBlock(var1, "block_align_separator", var6, var8)) {
            for (Object var11_raw : ALIGN_TOOL_ACTION_IDS) {
               String var11 = var11_raw != null ? var11_raw.toString() : null;
               if (this.isInsideShellBlock(var1, var11, var6, var8) || this.isInsideShellBlock(var1, var11 + "_hitbox", var6, var8)) {
                  return true;
               }
            }

            EditorRect var12 = this.findShellBlockRect(var1, "block_align");
            if (var12 == null) {
               var12 = var1.alignToolbarBounds;
            }

            return var12 == null ? false : var6 >= var12.x && var6 <= var12.maxX() && var8 >= var12.y && var8 <= var12.maxY();
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   protected String resolveHoveredAlignToolbarActionId(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.alignToolbarVisible) {
         double var6 = var2 - var1.hitboxOffsetX;
         double var8 = var4 - var1.hitboxOffsetY;

         for (Object var11_raw : ALIGN_TOOL_ACTION_IDS) {
            String var11 = var11_raw != null ? var11_raw.toString() : null;
            EditorRect var12 = this.findShellBlockRect(var1, var11 + "_hitbox");
            if (var12 != null && var6 >= var12.x && var6 <= var12.maxX() && var8 >= var12.y && var8 <= var12.maxY()) {
               return var11;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   protected void setAlignToolbarActionVisual(Player var1, String var2, boolean var3) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String[] var4 = ALIGN_TOOL_ACTION_GLYPHS.get(var2);
         if (var4 != null && var4.length >= 2) {
            String var5 = var3 ? this.firstNonBlank(new String[]{var4[1]}) : this.firstNonBlank(new String[]{var4[0]});
            this.setShellText(var1, var2, var5);
         }
      }
   }

   protected void setAlignToolbarVisible(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         if (var2.alignToolbarVisible != var3) {
            var2.alignToolbarVisible = var3;
            this.beginShellOpacityBatch(var2);

            try {
               this.setSidebarPanelVisible(var1, var2, "block_align", var3);
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

   protected void moveAlignToolbarTo(Player var1, EditorSession var2, double var3, double var5, boolean var7) {
      if (var1 != null && var2 != null && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         ConfigurationSection var8 = this.findShellBlockSection(var2, "block_align");
         EditorRect var9 = this.findShellBlockRect(var2, "block_align");
         if (var8 != null && var9 != null) {
            double var10 = var3 - var9.x;
            double var12 = var5 - var9.y;
            if (!(Math.abs(var10) < 1.0E-4) || !(Math.abs(var12) < 1.0E-4)) {
               String var14 = this.firstNonBlank(new String[]{var8.getString("__editor_target_path")});
               if (var14.isBlank()) {
                  if (var7) {
                     this.applyAlignToolbarTransitionOneTick(var1, "block_align");
                  } else {
                     this.applyAlignToolbarTeleportDurationZero(var1, "block_align");
                  }

                  this.moveShellElement(var1, var2, "block_align", var3, var5, var9.width, var9.height);
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

   protected void applyAlignToolbarTransitionOneTick(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = "editor_shell_" + var2;
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var3);
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var3 + "_outline");
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var3 + "_r_core");
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var3 + "_r_top");
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var3 + "_r_bottom");
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var3 + "_r_left");
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var3 + "_r_right");
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var3 + "_r_tl");
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var3 + "_r_tr");
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var3 + "_r_bl");
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var3 + "_r_br");
         String var4 = var3 + "_outline";
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var4 + "_r_core");
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var4 + "_r_top");
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var4 + "_r_bottom");
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var4 + "_r_left");
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var4 + "_r_right");
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var4 + "_r_tl");
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var4 + "_r_tr");
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var4 + "_r_bl");
         this.setAlignToolbarHudTransitionOneTickIfExists(var1, var4 + "_r_br");
      }
   }

   protected void applyAlignToolbarTeleportDurationZero(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = "editor_shell_" + var2;
         this.setAlignToolbarHudNoTransitionIfExists(var1, var3);
         this.setAlignToolbarHudNoTransitionIfExists(var1, var3 + "_outline");
         this.setAlignToolbarHudNoTransitionIfExists(var1, var3 + "_r_core");
         this.setAlignToolbarHudNoTransitionIfExists(var1, var3 + "_r_top");
         this.setAlignToolbarHudNoTransitionIfExists(var1, var3 + "_r_bottom");
         this.setAlignToolbarHudNoTransitionIfExists(var1, var3 + "_r_left");
         this.setAlignToolbarHudNoTransitionIfExists(var1, var3 + "_r_right");
         this.setAlignToolbarHudNoTransitionIfExists(var1, var3 + "_r_tl");
         this.setAlignToolbarHudNoTransitionIfExists(var1, var3 + "_r_tr");
         this.setAlignToolbarHudNoTransitionIfExists(var1, var3 + "_r_bl");
         this.setAlignToolbarHudNoTransitionIfExists(var1, var3 + "_r_br");
         String var4 = var3 + "_outline";
         this.setAlignToolbarHudNoTransitionIfExists(var1, var4 + "_r_core");
         this.setAlignToolbarHudNoTransitionIfExists(var1, var4 + "_r_top");
         this.setAlignToolbarHudNoTransitionIfExists(var1, var4 + "_r_bottom");
         this.setAlignToolbarHudNoTransitionIfExists(var1, var4 + "_r_left");
         this.setAlignToolbarHudNoTransitionIfExists(var1, var4 + "_r_right");
         this.setAlignToolbarHudNoTransitionIfExists(var1, var4 + "_r_tl");
         this.setAlignToolbarHudNoTransitionIfExists(var1, var4 + "_r_tr");
         this.setAlignToolbarHudNoTransitionIfExists(var1, var4 + "_r_bl");
         this.setAlignToolbarHudNoTransitionIfExists(var1, var4 + "_r_br");
      }
   }

   protected void setAlignToolbarHudNoTransitionIfExists(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Entity var3 = this.hudService.getHud(var1, var2);
         if (var3 != null) {
            this.hudService.setHudTransitionTicks(var3, this.resolveEditorCursorInterpolationDurationTicks(), this.resolveEditorCursorTeleportDurationTicks());
         }
      }
   }

   protected void setAlignToolbarHudTransitionOneTickIfExists(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Entity var3 = this.hudService.getHud(var1, var2);
         if (var3 != null) {
            this.hudService.setHudTransitionTicks(var3, this.resolveEditorCursorInterpolationDurationTicks(), this.resolveEditorCursorTeleportDurationTicks());
         }
      }
   }

   protected boolean applyAlignToolbarAction(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         EditorRect var4 = this.getSelectedAlignToolbarBounds(var2);
         if (var4 == null) {
            return false;
         } else {
            double var5 = var2.previewViewport == null ? 1920.0 : var2.previewViewport.pageWidth;
            double var7 = var2.previewViewport == null ? 1080.0 : var2.previewViewport.pageHeight;
            double var9 = 0.0;
            double var11 = 0.0;
            switch (var3) {
               case "align_lr_left":
                  var9 = 0.0 - var4.x;
                  break;
               case "align_lr_center":
                  var9 = (var5 - var4.width) / 2.0 - var4.x;
                  break;
               case "align_lr_right":
                  var9 = var5 - var4.width - var4.x;
                  break;
               case "align_tb_top":
                  var11 = 0.0 - var4.y;
                  break;
               case "align_tb_center":
                  var11 = (var7 - var4.height) / 2.0 - var4.y;
                  break;
               case "align_tb_bottom":
                  var11 = var7 - var4.height - var4.y;
                  break;
               default:
                  return false;
            }

            if (Math.abs(var9) < 1.0E-4 && Math.abs(var11) < 1.0E-4) {
               return true;
            } else {
               boolean var13 = false;
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
                        var13 = true;
                     }
                  }
               }

               if (!var13) {
                  return true;
               } else {
                  this.rerenderEditableSelection(var1, var2);
                  this.updateAlignToolbarOverlay(var1, var2, var2.cursorX + var2.hitboxOffsetX, var2.cursorY + var2.hitboxOffsetY);
                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   protected EditorRect getSelectedAlignToolbarBounds(EditorSession var1) {
      if (var1 == null) {
         return null;
      } else {
         EditorRect var2 = null;

         for (String var4 : this.getSelectedTargetIds(var1)) {
            var2 = this.mergeBounds(var2, this.resolveAlignToolbarTargetBounds(var1, var4));
         }

         return var2;
      }
   }

   protected EditorRect resolveAlignToolbarTargetBounds(EditorSession var1, String var2) {
      EditorRect var3 = this.resolveSidebarEditableBounds(var1, var2);
      if (var3 != null) {
         double var4 = this.toSidebarDisplayPositionValue(var1, var2, EditorPropertyField.POSITION_X, var3.x);
         double var6 = this.toSidebarDisplayPositionValue(var1, var2, EditorPropertyField.POSITION_Y, var3.y);
         return new EditorRect(var4, var6, var3.width, var3.height);
      } else {
         return this.getTargetBounds(var1, var2);
      }
   }

   protected void updateMoveDrag(Player var1, EditorSession var2, double var3, double var5, boolean var7) {
      if (var2 != null && !var2.moveStartBounds.isEmpty()) {
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

         boolean var32 = false;

         for (Map.Entry<?, ?> var20 : ((Map<?, ?>)var2.moveStartBounds).entrySet()) {
            EditorRect var21 = (EditorRect)var20.getValue();
            if (var21 != null) {
               double var22 = this.snapToGrid(var2, var21.x + var14);
               double var24 = this.snapToGrid(var2, var21.y + var16);
               EditorRect var26 = new EditorRect(var22, var24, var21.width, var21.height);
               EditorRect var27 = this.getTargetBounds(var2, (String)var20.getKey());
               if (!this.sameRect(var27, var26) && var27 != null) {
                  var2.moveDragMutated = true;
                  this.applyBoundsToTarget(var2, (String)var20.getKey(), var26);
                  double var28 = var26.x - var27.x;
                  double var30 = var26.y - var27.y;
                  this.translateTargetElements(var1, var2, (String)var20.getKey(), var28, var30);
                  this.translateAnimationMoveDragDescendants(var1, var2, (String)var20.getKey(), var28, var30);
                  var32 = true;
               }
            }
         }

         if (var32) {
            EditorRect var33 = this.getSelectedBounds(var2);
            if (var8 != null && var33 != null) {
               this.translateSelectionOutline(var1, var2, var33.x - var8.x, var33.y - var8.y);
               this.updateEditorPropertiesSidebar(var1, var2);
               this.updateAlignToolbarOverlay(var1, var2, var2.cursorX + var2.hitboxOffsetX, var2.cursorY + var2.hitboxOffsetY, true);
               if (var2.activeTool == EditorTool.ANIMATION) {
                  this.renderAnimationPreviewMotionPath(var1, var2);
               }
            } else {
               this.renderSelectionOverlay(var1, var2);
               this.updateEditorPropertiesSidebar(var1, var2);
               this.updateAlignToolbarOverlay(var1, var2, var2.cursorX + var2.hitboxOffsetX, var2.cursorY + var2.hitboxOffsetY, true);
            }
         }
      } else {
         this.stopCursorToolDrag(var2);
      }
   }

   protected EditorRect getMoveStartSelectionBounds(EditorSession var1) {
      if (var1 != null && !var1.moveStartBounds.isEmpty()) {
         EditorRect var2 = null;

         for (EditorRect var4 : var1.moveStartBounds.values()) {
            var2 = this.mergeBounds(var2, var4);
         }

         return var2;
      } else {
         return null;
      }
   }

   protected Vector resolveMoveSnapOffset(EditorSession var1, EditorRect var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         EditorRect var7 = new EditorRect(var2.x + var3, var2.y + var5, var2.width, var2.height);
         LinkedHashSet var8 = new LinkedHashSet<>(var1.moveStartBounds.keySet());
         if (var8.isEmpty()) {
            var8.addAll(this.getSelectedTargetIds(var1));
         }

         LinkedHashSet var9 = new LinkedHashSet();
         Double var10 = null;
         Double var11 = null;

         for (Object var13_raw : var1.elements) {
            HoverElement var13 = (HoverElement)var13_raw;
            String var14 = this.targetIdOf(var13);
            if (var14 != null && !var14.isBlank() && !var8.contains(var14) && var9.add(var14)) {
               EditorRect var15 = this.getTargetBounds(var1, var14);
               if (var15 != null) {
                  var10 = this.pickBetterSnapDelta(
                     var10, this.resolveAxisSnapDelta(var7.x, var7.x + var7.width / 2.0, var7.maxX(), var15.x, var15.x + var15.width / 2.0, var15.maxX())
                  );
                  var11 = this.pickBetterSnapDelta(
                     var11, this.resolveAxisSnapDelta(var7.y, var7.y + var7.height / 2.0, var7.maxY(), var15.y, var15.y + var15.height / 2.0, var15.maxY())
                  );
               }
            }
         }

         double var20 = var1.previewViewport == null ? 1920.0 : var1.previewViewport.pageWidth;
         double var21 = var1.previewViewport == null ? 1080.0 : var1.previewViewport.pageHeight;
         var10 = this.pickBetterSnapDelta(var10, this.snapDelta(var7.x, 0.0));
         var10 = this.pickBetterSnapDelta(var10, this.snapDelta(var7.maxX(), var20));
         var11 = this.pickBetterSnapDelta(var11, this.snapDelta(var7.y, 0.0));
         var11 = this.pickBetterSnapDelta(var11, this.snapDelta(var7.maxY(), var21));
         return new Vector(var10 == null ? 0.0 : var10, var11 == null ? 0.0 : var11, 0.0);
      } else {
         return new Vector(0.0, 0.0, 0.0);
      }
   }

   protected Double resolveAxisSnapDelta(double var1, double var3, double var5, double var7, double var9, double var11) {
      Object var13 = null;
      var13 = this.pickBetterSnapDelta((Double)var13, this.snapDelta(var1, var7));
      var13 = this.pickBetterSnapDelta((Double)var13, this.snapDelta(var1, var11));
      var13 = this.pickBetterSnapDelta((Double)var13, this.snapDelta(var5, var7));
      var13 = this.pickBetterSnapDelta((Double)var13, this.snapDelta(var5, var11));
      return this.pickBetterSnapDelta((Double)var13, this.snapDelta(var3, var9));
   }

   protected Double snapDelta(double var1, double var3) {
      double var5 = var3 - var1;
      return Math.abs(var5) > 20.0 ? null : var5;
   }

   protected Double pickBetterSnapDelta(Double var1, Double var2) {
      if (var2 == null) {
         return var1;
      } else {
         return var1 != null && !(Math.abs(var2) < Math.abs(var1)) ? var1 : var2;
      }
   }

   protected void applyPageOpenEffect(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && !var2.editMode) {
         String var3 = var2.pageOpenEffect;
         if (var3 != null && !var3.isBlank()) {
            AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig var4 = this.resolveHoverEffectRuntimeConfig(var3);
            boolean var5 = var4 != null
               && (
                  Math.abs(var4.startOffsetX()) > 0.001
                     || Math.abs(var4.startOffsetY()) > 0.001
                     || Math.abs(var4.startScaleXMultiplier() - 1.0) > 0.001
                     || Math.abs(var4.startScaleYMultiplier() - 1.0) > 0.001
               );
            if (var5) {
               boolean var6 = Math.abs(var4.startScaleXMultiplier() - 1.0) < 0.001 && Math.abs(var4.startScaleYMultiplier() - 1.0) < 0.001;
               if (var6) {
                  this.capturePreOpenBaseStates(var1, var2, var4.startOffsetX(), var4.startOffsetY());
               } else {
                  var2.activeEffectOverride = var3;

                  try {
                     for (String var8 : this.resolveAllUniqueSessionTargetIds(var2)) {
                        this.applyHoverEffectForTargetWithStartPosition(var1, var2, var8);
                     }
                  } finally {
                     var2.activeEffectOverride = null;
                  }
               }

               var2.activeEffectOverride = var3;

               try {
                  for (String var27 : this.resolveAllUniqueSessionTargetIds(var2)) {
                     this.restoreHoverEffectForTarget(var1, var2, var27);
                  }
               } finally {
                  var2.activeEffectOverride = null;
               }
            } else {
               var2.runtimeHitboxOffsetX = 0.0;
               var2.runtimeHitboxOffsetY = 0.0;
               if (var4 != null) {
                  var2.runtimeHitboxOffsetX = var4.offsetX();
                  var2.runtimeHitboxOffsetY = var4.offsetY();
               }

               var2.activeEffectOverride = var3;

               try {
                  for (String var26 : this.resolveAllUniqueSessionTargetIds(var2)) {
                     this.applyOpenAnimationForTarget(var1, var2, var26);
                  }
               } finally {
                  var2.activeEffectOverride = null;
               }
            }
         }
      }
   }

   protected boolean applyPageCloseEffect(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && !var2.editMode && !var2.pendingCloseEffect) {
         String var3 = var2.pageCloseEffect;
         if (var3 != null && !var3.isBlank()) {
            AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig var4 = this.resolveHoverEffectRuntimeConfig(var3);
            int var5 = var4 == null ? 1 : Math.max(1, var4.durationTicks());
            if (var2.pageCloseUseHud && !var2.hudTextOnlyMode) {
               var2.hudTextOnlyMode = true;
               this.cameraService.setCameraActive(var1, false);
               this.cameraService.forceStop(var1);
               this.removeHudById(var1, "cursor");
               var1.sendActionBar(MM.deserialize("<gray> </gray>"));
               this.plugin.teleportToReturnLocationAndClear(var1);
            }

            boolean var6 = var4 != null && var4.closeReversed();
            var2.pendingCloseEffect = true;
            if (var6) {
               AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig var7 = this.buildReversedCloseConfig(var4);

               for (String var9 : this.resolveAllUniqueSessionTargetIds(var2)) {
                  this.applyHoverEffectForTargetWithConfig(var1, var2, var9, var7);
               }
            } else {
               var2.activeEffectOverride = var3;

               try {
                  for (String var14 : this.resolveAllUniqueSessionTargetIds(var2)) {
                     this.applyHoverEffectForTarget(var1, var2, var14);
                  }
               } finally {
                  var2.activeEffectOverride = null;
               }
            }

            PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
               EditorSession var3x = this.editorSessions.get(var1.getUniqueId());
               if (var3x != null && var3x == var2) {
                  if (var2.pendingCloseEffect) {
                     var2.pendingCloseEffect = false;
                     this.plugin.closeUiForPlayerImmediate(var1);
                  }
               }
            }, (long)var5 + 1L);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected void applyClickEffectForTarget(Player var1, EditorSession var2, String var3, String var4) {
      if (var1 != null && var2 != null && !var2.editMode) {
         String var5 = this.firstNonBlank(new String[]{var3});
         if (!var5.isBlank() && var4 != null && !var4.isBlank()) {
            AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig var6 = this.resolveHoverEffectRuntimeConfig(var4);
            int var7 = var6 == null ? 1 : Math.max(1, var6.durationTicks());
            var2.activeEffectOverride = var4;

            try {
               this.applyHoverEffectForTarget(var1, var2, var5);
            } finally {
               var2.activeEffectOverride = null;
            }

            PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
               EditorSession var4x = this.editorSessions.get(var1.getUniqueId());
               if (var4x != null && var4x == var2) {
                  this.restoreHoverEffectForTarget(var1, var4x, var5);
               }
            }, (long)var7 + 2L);
         }
      }
   }

   private LinkedHashSet<String> resolveAllUniqueSessionTargetIds(EditorSession var1) {
      LinkedHashSet var2 = new LinkedHashSet();
      if (var1 != null && var1.elements != null) {
         for (Object var4_raw : var1.elements) {
            HoverElement var4 = (HoverElement)var4_raw;
            String var5 = this.firstNonBlank(new String[]{this.targetIdOf(var4), var4 == null ? null : var4.targetId});
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
