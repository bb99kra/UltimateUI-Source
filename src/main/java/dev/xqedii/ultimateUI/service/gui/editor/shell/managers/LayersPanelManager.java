package dev.xqedii.ultimateUI.service.gui.editor.shell.managers;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.gui.model.HoverElement;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.gui.model.EditorSidebarTab;
import dev.xqedii.ultimateUI.service.gui.model.EditorTool;
import dev.xqedii.ultimateUI.service.hud.HudPositionCalculator;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.util.PlatformCompat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay.TextAlignment;

public abstract class LayersPanelManager extends SidebarLegacyTimelineUtilityManager {
   protected static final String SIDEBAR_LAYERS_PANEL_ID = "layers";
   protected static final String SIDEBAR_LAYERS_BOX_ID = "layers_box";
   protected static final String SIDEBAR_LAYERS_HITBOX_ID = "layers_hitbox";
   protected static final String SIDEBAR_LAYERS_HITBOX_LEGACY_ID = "layer_hitbox";
   protected static final String SIDEBAR_LAYERS_TEXT_ID = "layers_text";
   protected static final String SIDEBAR_EMPTY_VALUE = "---";
   protected static final String SIDEBAR_TEXT_ACTIVE = "<#ffffff>";
   protected static final String SIDEBAR_TEXT_INACTIVE = "<#bdbdbd>";
   protected static final int SIDEBAR_VALUE_MAX_LENGTH = 18;
   protected static final int SIDEBAR_VALUE_VISIBLE_PREFIX_LENGTH = 17;
   protected static final int LAYERS_VISIBLE_ROW_COUNT = 17;
   protected static final double LAYERS_ROW_START_X_OFFSET = 24.0;
   protected static final double LAYERS_ROW_START_Y_OFFSET = 130.0;
   protected static final double LAYERS_ROW_WIDTH = 289.0;
   protected static final double LAYERS_ROW_HEIGHT = 45.0;
   protected static final double LAYERS_DYNAMIC_GRID_GAP = -51.0;
   protected static final String LAYERS_RUNTIME_ID_PREFIX = "layers_runtime_";
   protected static final String LAYERS_SLOT_ID_PREFIX = "layer_slot_";
   protected static final String LAYERS_ROW_ID_SUFFIX = "_row";
   protected static final String LAYERS_NAME_ID_SUFFIX = "_name";
   protected static final String LAYERS_DROPDOWN_HITBOX_SUFFIX = "_dropdownhitbox";
   protected static final String LAYERS_DROPDOWN_ICON_SUFFIX = "_dropdown_icon";
   protected static final String LAYERS_STATIC_BOX1_SUFFIX = "_box1";
   protected static final String LAYERS_STATIC_BOX2_SUFFIX = "_box2";
   protected static final String LAYERS_STATIC_OPACITY_SUFFIX = "_opacity";
   protected static final String LAYERS_STATIC_OPACITY_HITBOX_SUFFIX = "_opacityhitbox";
   protected static final String LAYERS_STATIC_DIVIDER_SUFFIX = "_divider";
   protected static final String LAYERS_STATIC_HITBOX_SUFFIX = "_hitbox";
   protected static final String LAYERS_SCROLL_TOP_ID = "scroll_top";
   protected static final String LAYERS_SCROLL_BOTTOM_ID = "scroll_bottom";
   protected static final int LAYERS_SCROLL_EDGE_FLASH_TICKS = 3;
   protected static final double LAYERS_REORDER_DRAG_THRESHOLD_PX = 5.0;
   protected static final double LAYERS_REORDER_EDGE_ZONE_PX = 18.0;
   protected static final int LAYERS_REORDER_AUTOSCROLL_PERIOD_TICKS = 4;
   protected static final String LAYERS_REORDER_GHOST_RUNTIME_PREFIX = "layers_drag_runtime_";
   protected static final String LAYERS_REORDER_GHOST_SLOT_BASE = "layer_drag_slot";
   protected static final int LAYER_TEXT_LABEL_MAX_CHARS = 26;
   protected static final int LAYER_CHILD_LABEL_DEPTH_PENALTY_CHARS = 3;

   protected LayersPanelManager(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   protected abstract void applyBoundsToTarget(EditorSession var1, String var2, EditorRect var3);

   protected abstract void clearEditorOverlaysOnly(Player var1, EditorSession var2);

   protected abstract void setActiveEditorTool(Player var1, EditorSession var2, EditorTool var3);

   protected abstract HoverElement findFirstByTargetId(EditorSession var1, String var2);

   protected abstract List<HoverElement> findTargetElements(EditorSession var1, String var2);

   protected abstract List<String> getSelectedTargetIds(EditorSession var1);

   protected abstract EditorRect getTargetBounds(EditorSession var1, String var2);

   protected abstract boolean isSelectedTarget(EditorSession var1, String var2);

   protected abstract void normalizeSelectionState(EditorSession var1);

   protected abstract void renderSelectionOverlay(Player var1, EditorSession var2);

   protected abstract void refreshSelectionOutlineColorOnly(Player var1, EditorSession var2);

   protected abstract void rerenderEditableContent(Player var1, EditorSession var2);

   protected abstract void rerenderEditableSelection(Player var1, EditorSession var2);

   protected abstract boolean sameRect(EditorRect var1, EditorRect var2);

   protected abstract void showSelectionOverlay(Player var1, EditorSession var2);

   protected abstract String targetIdOf(HoverElement var1);

   protected abstract void translateSelectionOutline(Player var1, EditorSession var2, double var3, double var5);

   protected abstract void translateTargetElements(Player var1, EditorSession var2, String var3, double var4, double var6);

   protected abstract void updateHoveredHighlight(Player var1, EditorSession var2, String var3, String var4);

   protected abstract boolean hasMapPath(Map<String, Object> var1, String var2);

   protected abstract double readMapPathDouble(Map<String, Object> var1, String var2, double var3);

   protected abstract Object readMapPathValue(Map<String, Object> var1, String var2);

   protected abstract String toSidebarTypeLabel(String var1);

   protected abstract Map<String, Object> resolveRawTargetByTargetId(EditorSession var1, String var2);

   protected abstract String readTargetCustomName(EditorSession var1, String var2);

   protected abstract String findTargetIdByPath(EditorSession var1, String var2);

   protected abstract double readTargetLayer(EditorSession var1, String var2);

   protected abstract String resolveAnimationTimelineHierarchyTargetPath(EditorSession var1, String var2);

   @Override
   protected abstract EditorRect findShellBlockRect(EditorSession var1, String var2);

   @Override
   protected abstract ConfigurationSection findShellBlockSection(EditorSession var1, String var2);

   protected abstract void ensureShellBlockCache(EditorSession var1);

   protected abstract void moveShellElement(Player var1, EditorSession var2, String var3, double var4, double var6, double var8, double var10);

   @Override
   protected abstract void setShellText(Player var1, String var2, String var3);

   protected abstract void cacheShellTextForDeferredSpawn(EditorSession var1, String var2, String var3);

   @Override
   protected abstract void setShellColor(Player var1, EditorSession var2, String var3, String var4);

   protected abstract void setShellOpacity(Player var1, String var2, int var3);

   protected abstract void applyShellOpacityNow(Player var1, String var2, int var3);

   protected abstract void setHudOpacityIfExists(Player var1, String var2, int var3);

   protected abstract int getShellOpacity(Player var1, EditorSession var2, String var3, int var4);

   @Override
   protected abstract void beginShellOpacityBatch(EditorSession var1);

   @Override
   protected abstract void endShellOpacityBatch(Player var1, EditorSession var2);

   @Override
   protected abstract void setSidebarPanelVisible(Player var1, EditorSession var2, String var3, boolean var4);

   protected abstract boolean setSidebarPanelVisibleTracked(Player var1, EditorSession var2, String var3, boolean var4);

   protected abstract void spawnShellPanelHud(Player var1, EditorSession var2, ConfigurationSection var3, String var4);

   protected abstract void clearShellElementHud(Player var1, EditorSession var2, String var3);

   protected abstract boolean isShellPanelSpawned(Player var1, EditorSession var2, ConfigurationSection var3, String var4);

   protected abstract boolean isShellElementHudPresent(Player var1, String var2);

   @Override
   protected abstract boolean isInsideShellBlock(EditorSession var1, String var2, double var3, double var5);

   protected abstract boolean belongsToSidebarPanel(String var1, String var2);

   protected abstract void updateEditorPropertiesSidebar(Player var1, EditorSession var2);

   protected abstract void updateEditorKeyframeSidebar(Player var1, EditorSession var2);

   protected abstract String stringValue(Object var1);

   protected boolean handleLayersScroll(Player var1, EditorSession var2, double var3, double var5, int var7) {
      if (var1 != null && var2 != null && var7 != 0) {
         List var8 = this.collectLayerTreeRows(var2);
         EditorRect var9 = this.resolveLayersListRect(var2, var8);
         if (!this.isInsideRect(var9, var3, var5)) {
            return false;
         } else {
            int var10 = var7 > 0 ? 1 : -1;
            int var11 = Math.max(0, var8.size() - 17);
            if (var11 <= 0) {
               var2.layersScrollOffset = 0;
               var2.layersScrollPosition = 0.0;
               var2.layersScrollTarget = 0.0;
               var2.layersScrollVelocity = 0.0;
               var2.layersScrollAnimationToken++;
               this.flashLayersScrollEdge(var1, var2, var10 < 0);
               return true;
            } else {
               byte var12 = 1;
               int var13 = (int)Math.floor(this.clampLayersScrollValue((double)var2.layersScrollOffset, var11) + 1.0E-4);
               int var14 = (int)Math.floor(this.clampLayersScrollValue((double)(var13 + var10 * var12), var11) + 1.0E-4);
               if (var14 == var13) {
                  this.flashLayersScrollEdge(var1, var2, var10 < 0);
                  return true;
               } else {
                  var2.layersScrollOffset = var14;
                  var2.layersScrollPosition = (double)var14;
                  var2.layersScrollTarget = (double)var14;
                  var2.layersScrollVelocity = 0.0;
                  var2.layersScrollAnimationToken++;
                  this.queueLayersPanelRender(var1, var2);
                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   protected void flashLayersScrollEdge(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         if (this.isLayersReorderActive(var2)) {
            this.setShellOpacity(var1, "scroll_top", 0);
            this.setShellOpacity(var1, "scroll_bottom", 0);
         } else {
            String var4 = var3 ? "scroll_top" : "scroll_bottom";
            int var5 = this.getShellOpacity(var1, var2, var4, 0);
            int var6 = var3 ? ++var2.layersScrollTopFlashToken : ++var2.layersScrollBottomFlashToken;
            int[] var7 = new int[]{0};
            PlatformCompat.runEntityTimer(this.plugin, var1, 0L, 1L, var8 -> {
               if (!var1.isOnline()) {
                  var8.cancel();
               } else {
                  EditorSession var9 = this.editorSessions.get(var1.getUniqueId());
                  if (var9 != var2) {
                     var8.cancel();
                  } else {
                     int var10 = var3 ? var2.layersScrollTopFlashToken : var2.layersScrollBottomFlashToken;
                     if (var10 != var6) {
                        var8.cancel();
                     } else if (var2.rightSidebarTab != EditorSidebarTab.LAYERS) {
                        this.setShellOpacity(var1, var4, 0);
                        var8.cancel();
                     } else {
                        var7[0]++;
                        int var11;
                        if (var7[0] <= 3) {
                           double var12 = (double)var7[0] / 3.0;
                           var11 = (int)Math.round((double)var5 + (255.0 - (double)var5) * var12);
                        } else {
                           int var14 = var7[0] - 3;
                           var11 = (int)Math.round(255.0 * (1.0 - (double)var14 / 3.0));
                        }

                        this.setShellOpacity(var1, var4, Math.max(0, Math.min(255, var11)));
                        if (var7[0] >= 6) {
                           this.setShellOpacity(var1, var4, 0);
                           var8.cancel();
                        }
                     }
                  }
               }
            });
         }
      }
   }

   protected void queueLayersPanelRender(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && !var2.layersRenderQueued) {
         var2.layersRenderQueued = true;
         PlatformCompat.runEntityTask(this.plugin, var1, () -> {
            EditorSession var2x = this.editorSessions.get(var1.getUniqueId());
            if (var2x != null) {
               var2x.layersRenderQueued = false;
               this.renderLayersPanel(var1, var2x);
            }
         });
      }
   }

   protected double clampLayersScrollValue(double var1, int var3) {
      return !Double.isFinite(var1) ? 0.0 : Math.max(0.0, Math.min((double)var3, var1));
   }

   protected void armLayersReorderDrag(EditorSession var1, String var2, double var3, double var5) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         var1.layersReorderArmedTargetId = var2;
         var1.layersReorderArmedStartHitX = var3;
         var1.layersReorderArmedStartHitY = var5;
         var1.layersReorderLastHitX = var3;
         var1.layersReorderLastHitY = var5;
      } else {
         this.clearLayersReorderArm(var1);
      }
   }

   protected void clearLayersReorderArm(EditorSession var1) {
      if (var1 != null) {
         var1.layersReorderArmedTargetId = null;
         var1.layersReorderArmedStartHitX = 0.0;
         var1.layersReorderArmedStartHitY = 0.0;
      }
   }

   protected boolean isLayersReorderArmed(EditorSession var1) {
      return var1 != null && var1.layersReorderArmedTargetId != null && !var1.layersReorderArmedTargetId.isBlank();
   }

   protected boolean isLayersReorderActive(EditorSession var1) {
      return var1 != null && var1.layersReorderActive && var1.layersReorderTargetId != null && !var1.layersReorderTargetId.isBlank();
   }

   protected boolean handleLayersReorderCursorMove(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         boolean var7 = this.isLayersReorderArmed(var2);
         boolean var8 = this.isLayersReorderActive(var2);
         if (!var7 && !var8) {
            return false;
         } else {
            var2.layersReorderLastHitX = var3;
            var2.layersReorderLastHitY = var5;
            if (var2.rightSidebarTab != EditorSidebarTab.LAYERS) {
               this.finishLayersReorderDrag(var1, var2, false);
               return false;
            } else if (!this.isLayersLeftHeld(var2)) {
               this.finishLayersReorderDrag(var1, var2, var8);
               return var8 || var7;
            } else {
               if (!var8) {
                  String var9 = this.firstNonBlank(new String[]{var2.layersReorderArmedTargetId});
                  if (var9.isBlank()) {
                     this.clearLayersReorderArm(var2);
                     return false;
                  }

                  double var10 = Math.hypot(var3 - var2.layersReorderArmedStartHitX, var5 - var2.layersReorderArmedStartHitY);
                  if (var10 <= 5.0) {
                     return true;
                  }

                  if (!this.beginLayersReorderDrag(var1, var2, var9, var3, var5)) {
                     this.finishLayersReorderDrag(var1, var2, false);
                     return true;
                  }

                  this.queueLayersPanelRender(var1, var2);
               }

               int var12 = var2.layersReorderPreviewInsertIndex;
               String var13 = var2.layersReorderHoverTargetId;
               this.updateLayersReorderDropPreview(var2, var3, var5);
               this.updateLayersReorderHoverTarget(var2, var3, var5);
               if (var2.layersReorderPreviewInsertIndex != var12 || !this.equalsNullable(var13, var2.layersReorderHoverTargetId)) {
                  this.queueLayersPanelRender(var1, var2);
               }

               this.updateLayersReorderAutoScroll(var1, var2, var3, var5);
               this.renderLayersReorderGhostHud(var1, var2, var3, var5);
               return true;
            }
         }
      } else {
         return false;
      }
   }

   protected boolean beginLayersReorderDrag(Player var1, EditorSession var2, String var3, double var4, double var6) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         List var8 = this.collectLayerTreeRows(var2);
         if (var8.size() <= 1) {
            return false;
         } else {
            int var9 = this.findLayerRowIndexByTargetId(var8, var3);
            if (var9 < 0) {
               return false;
            } else {
               LayersPanelManager.LayerTreeRow var10 = (LayersPanelManager.LayerTreeRow)var8.get(var9);
               var2.layersReorderActive = true;
               var2.layersReorderTargetId = var3;
               var2.layersReorderGhostLabel = var10 != null && var10.entry != null ? this.firstNonBlank(new String[]{var10.entry.displayName, var3}) : var3;
               var2.layersReorderPreviewInsertIndex = Math.max(0, Math.min(var8.size() - 1, var9));
               var2.layersReorderHoverTargetId = null;
               var2.layersReorderLastHitX = var4;
               var2.layersReorderLastHitY = var6;
               this.startLayersReorderReleaseWatch(var1, var2);
               return true;
            }
         }
      } else {
         return false;
      }
   }

   protected void startLayersReorderReleaseWatch(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && this.isLayersReorderActive(var2)) {
         int var3 = ++var2.layersReorderReleaseWatchToken;
         PlatformCompat.runEntityTimer(this.plugin, var1, 1L, 1L, var4 -> {
            if (!var1.isOnline()) {
               var4.cancel();
            } else {
               EditorSession var5 = this.editorSessions.get(var1.getUniqueId());
               if (var5 == null || var5 != var2 || var3 != var5.layersReorderReleaseWatchToken) {
                  var4.cancel();
               } else if (!this.isLayersReorderActive(var5)) {
                  var4.cancel();
               } else if (!this.isLayersLeftHeld(var5)) {
                  this.finishLayersReorderDrag(var1, var5, true);
                  var4.cancel();
               }
            }
         });
      }
   }

   protected void finishLayersReorderDrag(Player var1, EditorSession var2, boolean var3) {
      if (var2 != null) {
         boolean var4 = this.isLayersReorderActive(var2);
         var2.layersReorderReleaseWatchToken++;
         this.stopLayersReorderAutoScroll(var2);
         if (var1 != null) {
            this.clearLayersReorderGhostHud(var1, var2);
         }

         if (var3 && var4 && var1 != null) {
            this.applyLayersReorderDrop(var1, var2);
         }

         this.clearLayersReorderArm(var2);
         var2.layersReorderActive = false;
         var2.layersReorderTargetId = null;
         var2.layersReorderHoverTargetId = null;
         var2.layersReorderGhostLabel = null;
         var2.layersReorderPreviewInsertIndex = -1;
         var2.layersReorderAutoScrollDirection = 0;
         if (var4 && var1 != null) {
            this.queueLayersPanelRender(var1, var2);
         }
      }
   }

   protected void updateLayersReorderDropPreview(EditorSession var1, double var2, double var4) {
      if (this.isLayersReorderActive(var1)) {
         List var6 = this.collectLayerTreeRows(var1);
         int var7 = this.findLayerRowIndexByTargetId(var6, var1.layersReorderTargetId);
         if (var7 >= 0) {
            var1.layersReorderPreviewInsertIndex = this.resolveLayersReorderPreviewInsertIndex(var1, var6, var7, var4);
         }
      }
   }

   protected void updateLayersReorderHoverTarget(EditorSession var1, double var2, double var4) {
      if (!this.isLayersReorderActive(var1)) {
         if (var1 != null) {
            var1.layersReorderHoverTargetId = null;
         }
      } else {
         List var6 = this.collectLayerTreeRows(var1);
         int var7 = this.findLayerRowIndexByTargetId(var6, var1.layersReorderTargetId);
         if (var7 < 0) {
            var1.layersReorderHoverTargetId = null;
         } else {
            var1.layersReorderHoverTargetId = this.resolveLayersReorderHoverTargetId(var1, var6, var7, var2, var4);
         }
      }
   }

   protected String resolveLayersReorderHoverTargetId(EditorSession var1, List<LayersPanelManager.LayerTreeRow> var2, int var3, double var4, double var6) {
      if (var1 != null && var2 != null && !var2.isEmpty() && var3 >= 0 && var3 < var2.size()) {
         List var8 = this.buildLayersRowsWithReorderPlaceholder(var1, var2);
         int var9 = Math.max(0, var8.size() - 17);
         int var10 = (int)Math.floor(this.clampLayersScrollValue((double)var1.layersScrollOffset, var9) + 1.0E-4);
         int var11 = Math.max(0, Math.min(17, Math.max(0, var8.size() - var10)));
         if (var11 <= 0) {
            return null;
         } else {
            int var12 = this.resolveLayerSlotByHitStrict(var1, var11, var4, var6);
            if (var12 >= 1 && var12 <= var11) {
               int var13 = var10 + (var12 - 1);
               if (var13 >= 0 && var13 < var8.size()) {
                  LayersPanelManager.LayerTreeRow var14 = (LayersPanelManager.LayerTreeRow)var8.get(var13);
                  if (var14 != null && !var14.dragPlaceholder && var14.entry != null) {
                     boolean var15 = this.isInsideShellBlock(var1, this.layerDropdownHitboxId(var12), var4, var6);
                     boolean var16 = false;
                     boolean var17 = false;
                     if (!var15 && this.isInsideShellBlock(var1, this.layerSlotId(var12), var4, var6)) {
                        EditorRect var18 = this.findShellBlockRect(var1, this.layerSlotId(var12));
                        if (var18 != null) {
                           var16 = var4 <= var18.x + 74.0;
                           double var19 = var18.y + var18.height * 0.25;
                           double var21 = var18.maxY() - var18.height * 0.25;
                           var17 = var6 >= var19 && var6 <= var21;
                        }
                     }

                     if (!var15 && !var16 && !var17) {
                        return null;
                     } else {
                        String var23 = this.firstNonBlank(new String[]{var14.entry.targetId});
                        String var24 = this.firstNonBlank(new String[]{var1.layersReorderTargetId});
                        return !var23.isBlank() && !var24.isBlank() && !var23.equals(var24) && !this.isLayerTargetDescendantOf(var23, var24) ? var23 : null;
                     }
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
      } else {
         return null;
      }
   }

   protected int resolveLayerSlotByHitStrict(EditorSession var1, int var2, double var3, double var5) {
      if (var1 != null && var2 > 0) {
         int var7 = -1;
         double var8 = Double.MAX_VALUE;

         for (int var10 = 1; var10 <= var2; var10++) {
            if (this.isInsideShellBlock(var1, this.layerSlotId(var10), var3, var5)
               || this.isInsideShellBlock(var1, this.layerDropdownHitboxId(var10), var3, var5)
               || this.isInsideShellBlock(var1, this.layerOpacityHitboxId(var10), var3, var5)) {
               EditorRect var11 = this.findShellBlockRect(var1, this.layerSlotId(var10));
               double var12 = var11 == null ? 0.0 : Math.abs(var5 - (var11.y + var11.height / 2.0));
               if (var12 < var8) {
                  var8 = var12;
                  var7 = var10;
               }
            }
         }

         return var7;
      } else {
         return -1;
      }
   }

   protected int snapLayersReorderInsertIndexToSiblingBoundary(EditorSession var1, List<LayersPanelManager.LayerTreeRow> var2, int var3, int var4) {
      if (var1 != null && var2 != null && !var2.isEmpty() && var3 >= 0 && var3 < var2.size()) {
         LayersPanelManager.LayerTreeRow var5 = (LayersPanelManager.LayerTreeRow)var2.get(var3);
         if (var5 != null && var5.entry != null) {
            String var20 = this.firstNonBlank(new String[]{var5.entry.targetId});
            List<LayersPanelManager.LayerTreeRow> var7 = var20.isBlank() ? new ArrayList<>(var2) : this.buildRowsWithoutSourceSubtree(var2, var20);
            if (var20.isBlank()) {
               var7.remove(var3);
            }

            String var8 = this.resolveDirectLayerParentTargetId(var5.entry.targetId);
            ArrayList var9 = new ArrayList();

            for (int var10 = 0; var10 < var7.size(); var10++) {
               LayersPanelManager.LayerTreeRow var11 = (LayersPanelManager.LayerTreeRow)var7.get(var10);
               if (var11 != null && var11.entry != null) {
                  String var12 = this.resolveDirectLayerParentTargetId(var11.entry.targetId);
                  if (this.equalsNullable(var12, var8)) {
                     var9.add(var10);
                  }
               }
            }

            if (var9.isEmpty()) {
               return Math.max(0, Math.min(var7.size(), var4));
            } else {
               LinkedHashSet var21 = new LinkedHashSet();
               var21.add((Integer)var9.get(0));

               for (int var22 = 1; var22 < var9.size(); var22++) {
                  var21.add((Integer)var9.get(var22));
               }

               int var23 = (Integer)var9.get(var9.size() - 1);
               LayersPanelManager.LayerTreeRow var24 = (LayersPanelManager.LayerTreeRow)var7.get(var23);
               String var13 = var24 != null && var24.entry != null ? this.firstNonBlank(new String[]{var24.entry.targetId}) : "";
               int var14 = var23 + 1;

               while (var14 < var7.size()) {
                  LayersPanelManager.LayerTreeRow var15 = (LayersPanelManager.LayerTreeRow)var7.get(var14);
                  if (var15 != null && var15.entry != null) {
                     String var16 = this.firstNonBlank(new String[]{var15.entry.targetId});
                     if (!this.isLayerTargetDescendantOf(var16, var13)) {
                        break;
                     }

                     var14++;
                  } else {
                     var14++;
                  }
               }

               var21.add(Integer.valueOf(var14));
               int var25 = Math.max(0, Math.min(var7.size(), var4));
               ArrayList<Integer> var26 = new ArrayList<>(var21);
               var26.sort(Integer::compareTo);

               for (Object var18_raw : var26) {
                  int var18 = ((Number)var18_raw).intValue(); {
                     int var19 = Math.max(0, Math.min(var7.size(), var18));
                     if (var19 >= var25) {
                        return var19;
                     }
                  }
               }

               return var26.isEmpty() ? var25 : Math.max(0, Math.min(var7.size(), (Integer)var26.get(var26.size() - 1)));
            }
         } else {
            ArrayList var6 = new ArrayList(var2);
            var6.remove(var3);
            return Math.max(0, Math.min(var6.size(), var4));
         }
      } else {
         return Math.max(0, var4);
      }
   }

   protected boolean isLayerTargetDescendantOf(String var1, String var2) {
      String var3 = this.firstNonBlank(new String[]{var1});
      String var4 = this.firstNonBlank(new String[]{var2});
      return !var3.isBlank() && !var4.isBlank() ? var3.startsWith(var4 + ".children.") : false;
   }

   protected List<LayersPanelManager.LayerTreeRow> buildRowsWithoutSourceSubtree(List<LayersPanelManager.LayerTreeRow> var1, String var2) {
      if (var1 != null && !var1.isEmpty()) {
         String var3 = this.firstNonBlank(new String[]{var2});
         if (var3.isBlank()) {
            return new ArrayList<>(var1);
         } else {
            ArrayList var4 = new ArrayList(var1.size());

            for (LayersPanelManager.LayerTreeRow var6 : var1) {
               if (var6 != null && var6.entry != null) {
                  String var7 = this.firstNonBlank(new String[]{var6.entry.targetId});
                  if (var7.isBlank()) {
                     var4.add(var6);
                  } else if (!var7.equals(var3) && !this.isLayerTargetDescendantOf(var7, var3)) {
                     var4.add(var6);
                  }
               } else {
                  var4.add(var6);
               }
            }

            return var4;
         }
      } else {
         return Collections.emptyList();
      }
   }

   protected int resolveLayersReorderPreviewInsertIndex(EditorSession var1, List<LayersPanelManager.LayerTreeRow> var2, int var3, double var4) {
      if (var1 != null && var2 != null && !var2.isEmpty() && var3 >= 0 && var3 < var2.size()) {
         LayersPanelManager.LayerTreeRow var6 = (LayersPanelManager.LayerTreeRow)var2.get(var3);
         String var7 = var6 != null && var6.entry != null ? this.firstNonBlank(new String[]{var6.entry.targetId}) : "";
         List<LayersPanelManager.LayerTreeRow> var8 = var7.isBlank() ? new ArrayList<>(var2) : this.buildRowsWithoutSourceSubtree(var2, var7);
         if (var7.isBlank()) {
            var8.remove(var3);
         }

         int var9 = var8.size();
         if (var9 <= 0) {
            return 0;
         } else {
            int var10 = var2.size();
            if (var10 <= 1) {
               return 0;
            } else {
               EditorRect var11 = this.resolveLayersListRect(var1, var2);
               int var12 = Math.max(0, var10 - 17);
               int var13 = (int)Math.floor(this.clampLayersScrollValue((double)var1.layersScrollOffset, var12) + 1.0E-4);
               int var14 = Math.max(1, Math.min(17, Math.max(0, var10 - var13)));
               int var15;
               if (var11 == null) {
                  var15 = var3;
               } else if (var4 <= var11.y) {
                  var15 = var13;
               } else if (var4 >= var11.maxY()) {
                  var15 = var13 + var14;
               } else {
                  double var16 = Math.max(1.0, this.resolveLayersRowStep(var1));
                  double var18 = (var4 - var11.y) / var16;
                  int var20 = (int)Math.floor(var18 + 0.5);
                  var20 = Math.max(0, Math.min(var14, var20));
                  var15 = var13 + var20;
               }

               var15 = Math.max(0, Math.min(var10, var15));
               int var22 = 0;
               int var17 = Math.max(0, Math.min(var10, var15));
               if (!var7.isBlank()) {
                  for (int var23 = 0; var23 < var17; var23++) {
                     LayersPanelManager.LayerTreeRow var19 = (LayersPanelManager.LayerTreeRow)var2.get(var23);
                     if (var19 != null && var19.entry != null) {
                        String var26 = this.firstNonBlank(new String[]{var19.entry.targetId});
                        if (!var26.isBlank() && (var26.equals(var7) || this.isLayerTargetDescendantOf(var26, var7))) {
                           var22++;
                        }
                     }
                  }
               } else if (var15 > var3) {
                  var22 = 1;
               }

               int var24 = var15 - var22;
               return Math.max(0, Math.min(var9, var24));
            }
         }
      } else {
         return 0;
      }
   }

   protected void updateLayersReorderAutoScroll(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         byte var7 = 0;
         EditorRect var8 = this.resolveLayersListRect(var2);
         if (var8 != null && var3 >= var8.x && var3 <= var8.maxX()) {
            if (var5 <= var8.y + 18.0) {
               var7 = -1;
            } else if (var5 >= var8.maxY() - 18.0) {
               var7 = 1;
            }
         }

         if (var2.layersReorderAutoScrollDirection != var7) {
            var2.layersReorderAutoScrollDirection = var7;
            if (var7 == 0) {
               this.stopLayersReorderAutoScroll(var2);
            } else {
               this.startLayersReorderAutoScroll(var1, var2);
            }
         }
      }
   }

   protected void startLayersReorderAutoScroll(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.layersReorderAutoScrollDirection != 0) {
         int var3 = ++var2.layersReorderAutoScrollToken;
         PlatformCompat.runEntityTimer(
            this.plugin,
            var1,
            4L,
            4L,
            var4 -> {
               if (!var1.isOnline()) {
                  var4.cancel();
               } else {
                  EditorSession var5 = this.editorSessions.get(var1.getUniqueId());
                  if (var5 == null || var5 != var2) {
                     var4.cancel();
                  } else if (var3 == var5.layersReorderAutoScrollToken
                     && var5.layersReorderAutoScrollDirection != 0
                     && this.isLayersReorderActive(var5)
                     && var5.rightSidebarTab == EditorSidebarTab.LAYERS
                     && this.isLayersLeftHeld(var5)) {
                     List var6 = this.collectLayerTreeRows(var5);
                     int var7 = Math.max(0, var6.size() - 17);
                     int var8 = (int)Math.floor(this.clampLayersScrollValue((double)var5.layersScrollOffset, var7) + 1.0E-4);
                     int var9 = (int)Math.floor(this.clampLayersScrollValue((double)(var8 + var5.layersReorderAutoScrollDirection), var7) + 1.0E-4);
                     if (var9 == var8) {
                        this.flashLayersScrollEdge(var1, var5, var5.layersReorderAutoScrollDirection < 0);
                     } else {
                        var5.layersScrollOffset = var9;
                        var5.layersScrollPosition = (double)var9;
                        var5.layersScrollTarget = (double)var9;
                        var5.layersScrollVelocity = 0.0;
                        int var10 = var5.layersReorderPreviewInsertIndex;
                        String var11 = var5.layersReorderHoverTargetId;
                        this.updateLayersReorderDropPreview(var5, var5.layersReorderLastHitX, var5.layersReorderLastHitY);
                        this.updateLayersReorderHoverTarget(var5, var5.layersReorderLastHitX, var5.layersReorderLastHitY);
                        if (var5.layersReorderPreviewInsertIndex == var10 && this.equalsNullable(var11, var5.layersReorderHoverTargetId)) {
                           this.queueLayersPanelRender(var1, var5);
                        } else {
                           this.queueLayersPanelRender(var1, var5);
                        }

                        this.renderLayersReorderGhostHud(var1, var5, var5.layersReorderLastHitX, var5.layersReorderLastHitY);
                     }
                  } else {
                     var4.cancel();
                  }
               }
            }
         );
      }
   }

   protected void stopLayersReorderAutoScroll(EditorSession var1) {
      if (var1 != null) {
         var1.layersReorderAutoScrollToken++;
         var1.layersReorderAutoScrollDirection = 0;
      }
   }

   protected void renderLayersReorderGhostHud(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && this.isLayersReorderActive(var2)) {
         List var7 = this.collectLayerTreeRows(var2);
         int var8 = this.findLayerRowIndexByTargetId(var7, var2.layersReorderTargetId);
         if (var8 < 0) {
            this.clearLayersReorderGhostHud(var1, var2);
         } else {
            LayersPanelManager.LayerTreeRow var9 = (LayersPanelManager.LayerTreeRow)var7.get(var8);
            EditorRect var10 = this.resolveLayersListRect(var2, var7);
            if (var10 == null) {
               this.clearLayersReorderGhostHud(var1, var2);
            } else {
               double var11 = Math.max(1.0, this.resolveLayersRowStep(var2));
               double var13 = Math.max(1.0, var11);
               double var15 = var5 - var13 / 2.0;
               double var17 = var10.y - var13 * 0.1;
               double var19 = var10.maxY() - var13 * 0.9;
               var15 = Math.max(var17, Math.min(var19, var15));
               List var21 = this.buildLayersReorderGhostRuntimeBlocks(var2, var9, var10.x, var15);
               List var22 = this.resolveRenderableBlocksNoCopy(var21, var2.components);
               HashMap var23 = new HashMap();
               LinkedHashSet var24 = new LinkedHashSet();
               int var25 = 0;

               for (Map var27 : (List<Map>)(List<?>)var22) {
                  var25++;
                  String var28 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var27, "type")), "block"}).toLowerCase(Locale.ROOT);
                  if (this.isRenderableBlockType(var28)) {
                     String var29 = this.resolveElementId(var27, var25, var23);
                     String var30 = this.layerRuntimeLogicalId("layers_drag_runtime_" + var29);
                     if (this.canUseFastLayerRuntimeRender(var27, var28)) {
                        this.renderLayerRuntimeShellElementFast(var1, var2, var27, var30, var28, false);
                     } else {
                        ConfigurationSection var31 = this.mapToSection(var27);
                        if (var31 == null) {
                           continue;
                        }

                        this.renderLayerRuntimeShellElement(var1, var2, var31, var30, var28, false);
                     }

                     var24.add(var30);
                  }
               }

               this.clearStaleLayersReorderGhostShellIds(var1, var2, var24);
               var2.runtimeLayerReorderGhostShellIds.clear();
               var2.runtimeLayerReorderGhostShellIds.addAll(var24);
            }
         }
      } else {
         this.clearLayersReorderGhostHud(var1, var2);
      }
   }

   protected void clearLayersReorderGhostHud(Player var1) {
      EditorSession var2 = var1 == null ? null : this.editorSessions.get(var1.getUniqueId());
      this.clearLayersReorderGhostHud(var1, var2);
   }

   protected void clearLayersReorderGhostHud(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && !var2.runtimeLayerReorderGhostShellIds.isEmpty()) {
         for (String var5 : new ArrayList<>(var2.runtimeLayerReorderGhostShellIds)) {
            this.removeLayerRuntimeShellElement(var1, var5);
            var2.shellRuntimeRects.remove(var5);
         }

         var2.runtimeLayerReorderGhostShellIds.clear();
      }
   }

   protected void clearStaleLayersReorderGhostShellIds(Player var1, EditorSession var2, LinkedHashSet<String> var3) {
      if (var1 != null && var2 != null && !var2.runtimeLayerReorderGhostShellIds.isEmpty()) {
         LinkedHashSet var4 = new LinkedHashSet<>(var2.runtimeLayerReorderGhostShellIds);
         var4.removeAll(var3);

         for (Object var6_raw : var4) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            this.removeLayerRuntimeShellElement(var1, var6);
            var2.shellRuntimeRects.remove(var6);
         }
      }
   }

   protected List<Map<String, Object>> buildLayersReorderGhostRuntimeBlocks(EditorSession var1, LayersPanelManager.LayerTreeRow var2, double var3, double var5) {
      ArrayList var7 = new ArrayList();
      if (var2 != null) {
         LinkedHashMap var8 = new LinkedHashMap();
         var8.put("id", "layer_drag_slot");
         var8.put("slot", "layer_drag_slot");
         var8.put("name", this.formatLayerRuntimeNameForRow(var2, this.firstNonBlank(new String[]{var1.layersReorderGhostLabel, "Layer"})));
         var8.put("dropdown", var2.dropdown);
         var8.put("children", var2.children);
         var8.put("under", var2.under);
         var8.put("open", var2.open);
         var8.put("parent", var2.parent);
         var8.put("end", var2.end);
         var8.put("val", var2.depthValue);
         var8.put("extralines", var2.extraLines);
         var8.put("divider_opacity", 255);
         boolean var9 = var2.entry != null && this.isTargetVisible(var1, var2.entry.targetId);
         boolean var10 = var2.entry != null && this.isSelectedTarget(var1, var2.entry.targetId);
         var8.put("box1_icon", var10 ? "\ue63c" : "\ue638");
         var8.put("box2_icon", var10 ? "\ue63d" : "\ue639");
         if (!var9) {
            var8.put("opacity_icon", "");
         } else {
            var8.put("opacity_icon", var10 ? "\ue635" : "\ue634");
         }

         LinkedHashMap var11 = new LinkedHashMap();
         var11.put("component", "editor_layer");
         var11.put("params", var8);
         var7.add(var11);
      }

      LinkedHashMap var14 = new LinkedHashMap();
      var14.put("x", var3);
      var14.put("y", var5);
      double var15 = this.resolveLayersGridGap(var1);
      Double var16 = this.resolveLayersGridElementHeight(var1);
      LinkedHashMap var12 = new LinkedHashMap();
      var12.put("type", "grid_block");
      var12.put("direction", "column");
      var12.put("gap", var15);
      if (var16 != null) {
         var12.put("element_h", var16);
      }

      var12.put("position", var14);
      var12.put("children", var7);
      ArrayList var13 = new ArrayList();
      var13.add(var12);
      return var13;
   }

   protected int findLayerRowIndexByTargetId(List<LayersPanelManager.LayerTreeRow> var1, String var2) {
      if (var1 != null && !var1.isEmpty() && var2 != null && !var2.isBlank()) {
         for (int var3 = 0; var3 < var1.size(); var3++) {
            LayersPanelManager.LayerTreeRow var4 = (LayersPanelManager.LayerTreeRow)var1.get(var3);
            if (var4 != null && var4.entry != null && var2.equals(var4.entry.targetId)) {
               return var3;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }

   protected List<LayersPanelManager.LayerTreeRow> buildLayersRowsWithReorderPlaceholder(EditorSession var1, List<LayersPanelManager.LayerTreeRow> var2) {
      if (this.isLayersReorderActive(var1) && var2 != null && !var2.isEmpty()) {
         int var3 = this.findLayerRowIndexByTargetId(var2, var1.layersReorderTargetId);
         if (var3 < 0) {
            return var2;
         } else {
            LayersPanelManager.LayerTreeRow var4 = (LayersPanelManager.LayerTreeRow)var2.get(var3);
            String var5 = var4 != null && var4.entry != null ? this.firstNonBlank(new String[]{var4.entry.targetId}) : "";
            List<LayersPanelManager.LayerTreeRow> var6 = var5.isBlank() ? new ArrayList<>(var2) : this.buildRowsWithoutSourceSubtree(var2, var5);
            if (var5.isBlank()) {
               var6.remove(var3);
            }

            int var7 = Math.max(0, Math.min(var6.size(), var1.layersReorderPreviewInsertIndex));
            ArrayList var8 = new ArrayList(var2.size());
            var8.addAll(var6.subList(0, var7));
            var8.add(this.createLayersDragPlaceholderRow(var4));
            var8.addAll(var6.subList(var7, var6.size()));
            return var8;
         }
      } else {
         return var2;
      }
   }

   protected LayersPanelManager.LayerTreeRow createLayersDragPlaceholderRow(LayersPanelManager.LayerTreeRow var1) {
      return var1 == null
         ? null
         : new LayersPanelManager.LayerTreeRow(
            var1.entry, var1.dropdown, var1.open, var1.children, var1.parent, var1.under, var1.end, var1.depthValue, var1.extraLines, true
         );
   }

   protected void applyLayersReorderDrop(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && this.isLayersReorderActive(var2)) {
         List var3 = this.collectLayerTreeRows(var2);
         int var4 = this.findLayerRowIndexByTargetId(var3, var2.layersReorderTargetId);
         if (var4 >= 0 && var3.size() > 1) {
            String var5 = this.firstNonBlank(new String[]{var2.layersReorderTargetId});
            if (!var5.isBlank()) {
               ArrayList var6 = new ArrayList<>(this.getSelectedTargetIds(var2));
               List var7 = this.captureSelectionTargetPaths(var2, var6);
               this.updateLayersReorderHoverTarget(var2, var2.layersReorderLastHitX, var2.layersReorderLastHitY);
               String var8 = this.firstNonBlank(new String[]{var2.layersReorderHoverTargetId});
               if (!var8.isBlank() && !var8.equals(var5) && !this.isLayerTargetDescendantOf(var8, var5)) {
                  Map var9 = this.resolveRawMapAtPath(var2.rawBlocks, var8);
                  boolean var10 = var9 != null && this.parseBooleanFlag(this.readMapPathValue(var9, "__editor_inherit_target_to_children"), false);
                  if (!var10 && this.applyLayersReorderDropAsChild(var2, var3, var4, var5, var8)) {
                     this.rerenderAfterLayersStructureChange(var1, var2, var6, var7);
                     return;
                  }
               }

               List var22 = this.buildRowsWithoutSourceSubtree(var3, var5);
               this.updateLayersReorderDropPreview(var2, var2.layersReorderLastHitX, var2.layersReorderLastHitY);
               int var23 = Math.max(0, Math.min(var22.size(), var2.layersReorderPreviewInsertIndex));
               String var11 = this.resolveDirectLayerParentTargetId(var5);
               LayersPanelManager.LayerTreeRow var12 = var23 < var22.size() ? (LayersPanelManager.LayerTreeRow)var22.get(var23) : null;
               String var13 = "";
               if (var12 != null && var12.entry != null) {
                  var13 = this.resolveDirectLayerParentTargetId(var12.entry.targetId);
               } else if (!var22.isEmpty()) {
                  LayersPanelManager.LayerTreeRow var14 = (LayersPanelManager.LayerTreeRow)var22.get(var22.size() - 1);
                  if (var14 != null && var14.entry != null) {
                     var13 = this.resolveDirectLayerParentTargetId(var14.entry.targetId);
                  }
               }

               List var24 = this.collectDirectSiblingTargetOrder(var22, var13);
               int var15;
               if (var12 != null && var12.entry != null) {
                  int var16 = var24.indexOf(var12.entry.targetId);
                  var15 = var16 < 0 ? var24.size() : var16;
               } else {
                  var15 = var24.size();
               }

               var15 = Math.max(0, Math.min(var24.size(), var15));
               ArrayList var26 = new ArrayList(var24);
               var26.add(var15, var5);
               boolean var17 = !this.equalsNullable(var11, var13);
               if (!var17) {
                  List var18 = this.collectDirectSiblingTargetOrder(var3, var11);
                  if (var26.equals(var18)) {
                     return;
                  }
               }

               List var27 = var17 ? this.collectDirectSiblingTargetOrder(var22, var11) : Collections.emptyList();
               LinkedHashSet var19 = new LinkedHashSet();
               var19.add(var5);
               var19.addAll(var26);
               var19.addAll(var27);
               Map var20 = this.captureRawTargetsById(var2, var19);
               this.recordEditorMutation(var2);
               if (var17) {
                  Map var21 = this.moveRawTargetToParent(var2, var5, var13);
                  if (var21 == null) {
                     return;
                  }

                  var20.put(var5, var21);
               }

               this.applySiblingLayerOrderingOnRawTargets(var2, var20, var26);
               if (var17) {
                  this.rerenderAfterLayersStructureChange(var1, var2, var6, var7);
               } else {
                  this.rerenderAfterLayersStructureChange(var1, var2, var6, var7);
               }
            }
         }
      }
   }

   protected boolean applyLayersReorderDropAsChild(EditorSession var1, List<LayersPanelManager.LayerTreeRow> var2, int var3, String var4, String var5) {
      if (var1 != null
         && var2 != null
         && !var2.isEmpty()
         && var3 >= 0
         && var3 < var2.size()
         && var4 != null
         && !var4.isBlank()
         && var5 != null
         && !var5.isBlank()) {
         String var6 = this.resolveDirectLayerParentTargetId(var4);
         boolean var7 = !this.equalsNullable(var6, var5);
         List var8 = this.buildRowsWithoutSourceSubtree(var2, var4);
         List var9 = this.collectDirectSiblingTargetOrder(var8, var5);
         var9.add(var4);
         if (!var7) {
            List var10 = this.collectDirectSiblingTargetOrder(var2, var6);
            if (var9.equals(var10)) {
               return false;
            }
         }

         List var14 = var7 ? this.collectDirectSiblingTargetOrder(var8, var6) : Collections.emptyList();
         LinkedHashSet var11 = new LinkedHashSet();
         var11.add(var4);
         var11.addAll(var9);
         var11.addAll(var14);
         Map var12 = this.captureRawTargetsById(var1, var11);
         this.recordEditorMutation(var1);
         if (var7) {
            Map var13 = this.moveRawTargetToParent(var1, var4, var5);
            if (var13 == null) {
               return false;
            }

            var12.put(var4, var13);
         }

         boolean var15 = this.applySiblingLayerOrderingOnRawTargets(var1, var12, var9);
         return var15 || var7;
      } else {
         return false;
      }
   }

   protected List<String> collectDirectSiblingTargetOrder(List<LayersPanelManager.LayerTreeRow> var1, String var2) {
      ArrayList var3 = new ArrayList();
      if (var1 != null && !var1.isEmpty()) {
         for (LayersPanelManager.LayerTreeRow var5 : var1) {
            if (var5 != null && var5.entry != null && var5.entry.targetId != null && !var5.entry.targetId.isBlank()) {
               String var6 = this.resolveDirectLayerParentTargetId(var5.entry.targetId);
               if (this.equalsNullable(var6, var2)) {
                  var3.add(var5.entry.targetId);
               }
            }
         }

         return var3;
      } else {
         return var3;
      }
   }

   protected List<String> captureSelectionTargetPaths(EditorSession var1, List<String> var2) {
      ArrayList var3 = new ArrayList();
      if (var1 != null && var2 != null && !var2.isEmpty()) {
         for (Object var5_raw : var2) {
            String var5 = var5_raw != null ? var5_raw.toString() : null;
            String var6 = this.firstNonBlank(new String[]{var5});
            if (!var6.isBlank()) {
               HoverElement var7 = this.findFirstByTargetId(var1, var6);
               String var8 = this.firstNonBlank(new String[]{var7 == null ? null : var7.targetPath});
               if (var8.isBlank() && this.resolveRawMapAtPath(var1.rawBlocks, var6) != null) {
                  var8 = var6;
               }

               if (!var8.isBlank()) {
                  var3.add(var8);
               }
            }
         }

         return var3;
      } else {
         return var3;
      }
   }

   protected boolean isKnownSelectionTargetId(EditorSession var1, String var2) {
      if (var1 == null || var2 == null || var2.isBlank()) {
         return false;
      } else {
         return this.findFirstByTargetId(var1, var2) != null ? true : this.hasLayerEntryTargetId(var1, var2);
      }
   }

   protected void restoreSelectionAfterLayersStructureChange(EditorSession var1, List<String> var2, List<String> var3) {
      if (var1 != null) {
         LinkedHashSet var4 = new LinkedHashSet();
         if (var3 != null) {
            for (Object var6_raw : var3) {
               String var6 = var6_raw != null ? var6_raw.toString() : null;
               String var7 = this.findTargetIdByPath(var1, var6);
               if (!var7.isBlank()) {
                  var4.add(var7);
               }
            }
         }

         if (var4.isEmpty() && var2 != null) {
            for (Object var10_raw : var2) {
               String var10 = var10_raw != null ? var10_raw.toString() : null;
               String var12 = this.firstNonBlank(new String[]{var10});
               if (!var12.isBlank() && this.isKnownSelectionTargetId(var1, var12)) {
                  var4.add(var12);
               }
            }
         }

         var1.selectedElementId = null;
         var1.additionalSelectedElementIds.clear();

         for (Object var11_raw : var4) {
            String var11 = var11_raw != null ? var11_raw.toString() : null;
            this.appendSelectionTarget(var1, var11);
         }

         this.normalizeSelectionState(var1);
         var1.selectionOutlineVisible = var1.selectedElementId != null;
      }
   }

   protected void rerenderAfterLayersStructureChange(Player var1, EditorSession var2, List<String> var3, List<String> var4) {
      if (var1 != null && var2 != null) {
         var2.spawnOpacityRetoggledTargetIds.clear();
         var2.pendingSpawnOpacityRetoggleTargetIds.clear();
         var2.spawnOpacityRetoggleTaskQueued = false;
         this.rerenderEditableContent(var1, var2);
         this.restoreSelectionAfterLayersStructureChange(var2, var3, var4);
         this.rerenderEditableSelection(var1, var2);
      }
   }

   protected void rerenderSelectionAfterLayersStructureChange(Player var1, EditorSession var2, List<String> var3, List<String> var4) {
      if (var1 != null && var2 != null) {
         this.rerenderEditableSelection(var1, var2);
         this.restoreSelectionAfterLayersStructureChange(var2, var3, var4);
         this.rerenderEditableSelection(var1, var2);
      }
   }

   protected Map<String, Map<String, Object>> captureRawTargetsById(EditorSession var1, Collection<String> var2) {
      LinkedHashMap var3 = new LinkedHashMap();
      if (var1 != null && var2 != null && !var2.isEmpty()) {
         for (Object var5_raw : var2) {
            String var5 = var5_raw != null ? var5_raw.toString() : null;
            String var6 = this.firstNonBlank(new String[]{var5});
            if (!var6.isBlank() && !var3.containsKey(var6)) {
               Map var7 = this.resolveRawMapAtPath(var1.rawBlocks, var6);
               if (var7 == null) {
                  HoverElement var8 = this.findFirstByTargetId(var1, var6);
                  if (var8 != null && var8.targetPath != null && !var8.targetPath.isBlank()) {
                     var7 = this.resolveRawMapAtPath(var1.rawBlocks, var8.targetPath);
                  }
               }

               if (var7 != null) {
                  var3.put(var6, var7);
               }
            }
         }

         return var3;
      } else {
         return var3;
      }
   }

   protected Map<String, Object> moveRawTargetToParent(EditorSession var1, String var2, String var3) {
      if (var1 != null && var1.rawBlocks != null && var2 != null && !var2.isBlank()) {
         Map var4 = null;
         if (var3 != null && !var3.isBlank()) {
            var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3);
            if (var4 == null) {
               return null;
            }
         }

         Map var5 = this.resolveRawMapAtPath(var1.rawBlocks, var2);
         if (var5 == null) {
            return null;
         } else {
            double[] var6 = this.resolveRawTargetAbsolutePosition(var1, var2, var5);
            double[] var7 = var3 != null && !var3.isBlank() ? this.resolveRawTargetAbsolutePosition(var1, var3, var4) : new double[]{0.0, 0.0};
            String[] var8 = var2.split("\\.");
            if (var8.length == 0) {
               return null;
            } else {
               Object var9 = var1.rawBlocks;

               for (int var10 = 0; var10 < var8.length - 1; var10++) {
                  String var11 = var8[var10];
                  if (var9 instanceof List var12) {
                     int var13 = this.parsePathIndex(var11);
                     if (var13 < 0 || var13 >= var12.size()) {
                        return null;
                     }

                     var9 = var12.get(var13);
                  } else {
                     if (!(var9 instanceof Map var23)) {
                        return null;
                     }

                     var9 = var23.get(var11);
                  }
               }

               if (!(var9 instanceof List var21)) {
                  return null;
               }

               int var22 = this.parsePathIndex(var8[var8.length - 1]);
               if (var22 >= 0 && var22 < var21.size()) {
                  Object var24 = var21.remove(var22);
                  if (!(var24 instanceof Map var14)) {
                     var21.add(Math.max(0, Math.min(var21.size(), var22)), var24);
                     return null;
                  }

                  List var15;
                  if (var3 != null && !var3.isBlank()) {
                     if (var4 == null) {
                        var21.add(Math.max(0, Math.min(var21.size(), var22)), var14);
                        return null;
                     }

                     if (var4.get("children") instanceof List var17) {
                        var15 = var17;
                     } else {
                        var15 = new ArrayList();
                        var4.put("children", var15);
                     }
                  } else {
                     List var16 = var1.rawBlocks;
                     var15 = var16;
                  }

                  var15.add(var14);
                  double var26 = var6[0] - var7[0];
                  double var19 = var6[1] - var7[1];
                  this.setRawTargetLocalPosition(var14, var26, var19);
                  return var14;
               } else {
                  return null;
               }
            }
         }
      } else {
         return null;
      }
   }

   protected double[] resolveRawTargetAbsolutePosition(EditorSession var1, String var2, Map<String, Object> var3) {
      if (var1 == null) {
         return new double[]{0.0, 0.0};
      } else {
         double var4 = 0.0;
         double var6 = 0.0;
         String var8 = this.firstNonBlank(new String[]{var2});
         Map var9 = var3;
         if (var3 == null && !var8.isBlank()) {
            var9 = this.resolveRawMapAtPath(var1.rawBlocks, var8);
         }

         if (var9 != null) {
            var4 += this.readRawTargetLocalX(var9);
            var6 += this.readRawTargetLocalY(var9);
         }

         while (true) {
            int var10 = var8.lastIndexOf(".children.");
            if (var10 < 0) {
               break;
            }

            String var11 = var8.substring(0, var10);
            if (var11.isBlank()) {
               break;
            }

            Map var12 = this.resolveRawMapAtPath(var1.rawBlocks, var11);
            if (var12 != null) {
               var4 += this.readRawTargetLocalX(var12);
               var6 += this.readRawTargetLocalY(var12);
            }

            var8 = var11;
         }

         return new double[]{var4, var6};
      }
   }

   protected double readRawTargetLocalX(Map<String, Object> var1) {
      return var1 == null
         ? 0.0
         : this.readMapPathDouble(
            var1,
            "position.x",
            this.readMapPathDouble(var1, "x", this.readMapPathDouble(var1, "params.x", this.readMapPathDouble(var1, "params.position.x", 0.0)))
         );
   }

   protected double readRawTargetLocalY(Map<String, Object> var1) {
      return var1 == null
         ? 0.0
         : this.readMapPathDouble(
            var1,
            "position.y",
            this.readMapPathDouble(var1, "y", this.readMapPathDouble(var1, "params.y", this.readMapPathDouble(var1, "params.position.y", 0.0)))
         );
   }

   protected void setRawTargetLocalPosition(Map<String, Object> var1, double var2, double var4) {
      if (var1 != null) {
         if (this.hasMapPath(var1, "position.x") || this.hasMapPath(var1, "position.y")) {
            this.setMapPathValue(var1, "position.x", Double.valueOf(var2));
            this.setMapPathValue(var1, "position.y", Double.valueOf(var4));
         } else if (this.hasMapPath(var1, "x") || this.hasMapPath(var1, "y")) {
            this.setMapPathValue(var1, "x", Double.valueOf(var2));
            this.setMapPathValue(var1, "y", Double.valueOf(var4));
         } else if (this.hasMapPath(var1, "params.x") || this.hasMapPath(var1, "params.y")) {
            this.setMapPathValue(var1, "params.x", Double.valueOf(var2));
            this.setMapPathValue(var1, "params.y", Double.valueOf(var4));
         } else if (this.hasMapPath(var1, "params.position.x") || this.hasMapPath(var1, "params.position.y")) {
            this.setMapPathValue(var1, "params.position.x", Double.valueOf(var2));
            this.setMapPathValue(var1, "params.position.y", Double.valueOf(var4));
         } else if (var1.containsKey("params")) {
            this.setMapPathValue(var1, "params.x", Double.valueOf(var2));
            this.setMapPathValue(var1, "params.y", Double.valueOf(var4));
         } else {
            this.setMapPathValue(var1, "position.x", Double.valueOf(var2));
            this.setMapPathValue(var1, "position.y", Double.valueOf(var4));
         }
      }
   }

   protected boolean applySiblingLayerOrderingOnRawTargets(EditorSession var1, Map<String, Map<String, Object>> var2, List<String> var3) {
      if (var1 != null && var3 != null && !var3.isEmpty()) {
         double var4 = Double.NEGATIVE_INFINITY;
         if (var2 != null && !var2.isEmpty()) {
            for (Object var7_raw : var3) {
               String var7 = var7_raw != null ? var7_raw.toString() : null;
               Map var8 = (Map)var2.get(var7);
               if (var8 != null) {
                  var4 = Math.max(var4, this.readRawTargetLayer(var8));
               }
            }
         }

         if (!Double.isFinite(var4)) {
            for (Object var15_raw : var3) {
               String var15 = var15_raw != null ? var15_raw.toString() : null;
               var4 = Math.max(var4, this.readTargetLayer(var1, var15));
            }
         }

         if (!Double.isFinite(var4)) {
            var4 = (double)var3.size();
         }

         boolean var14 = false;
         double var16 = var4;

         for (Object var10_raw : var3) {
            String var10 = var10_raw != null ? var10_raw.toString() : null;
            boolean var11 = this.setTargetLayer(var1, var10, var16);
            if (!var11 && var2 != null) {
               Map var12 = (Map)var2.get(var10);
               if (var12 != null) {
                  var11 = this.setRawTargetLayer(var12, var16);
               }
            }

            var14 |= var11;
            var16--;
         }

         return var14;
      } else {
         return false;
      }
   }

   protected String resolveRawTargetLayerPath(Map<String, Object> var1) {
      if (var1 == null) {
         return "";
      } else if (this.hasMapPath(var1, "layer")) {
         return "layer";
      } else if (this.hasMapPath(var1, "size.depth")) {
         return "size.depth";
      } else if (this.hasMapPath(var1, "depth")) {
         return "depth";
      } else if (this.hasMapPath(var1, "params.layer")) {
         return "params.layer";
      } else {
         return this.hasMapPath(var1, "params.depth") ? "params.depth" : "";
      }
   }

   protected void collectRawLayerTargetsRecursive(Map<String, Object> var1, LinkedHashSet<Map<String, Object>> var2) {
      if (var1 != null && var2 != null && var2.add(var1)) {
         if (this.readMapPathValue(var1, "children") instanceof List var4 && !var4.isEmpty()) {
            for (Object var6 : var4) {
               if (var6 instanceof Map) {
                  Map var7 = (Map)var6;
                  if (!var7.isEmpty()) {
                     this.collectRawLayerTargetsRecursive(var7, var2);
                  }
               }
            }

            return;
         }
      }
   }

   protected boolean isRawUiImageGlyphBlock(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         String var2 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "text"))}).toLowerCase(Locale.ROOT);
         if (var2.isBlank() || !var2.contains("<font:uiimages>") && !var2.contains("%img_")) {
            String var3 = this.firstNonBlank(
                  new String[]{this.stringValue(this.readMapPathValue(var1, "id")), this.stringValue(this.readMapPathValue(var1, "params.id"))}
               )
               .toLowerCase(Locale.ROOT);
            return !var3.isBlank() && var3.startsWith("img_");
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   protected boolean hasDirectRawUiImageGlyphChild(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         if (this.readMapPathValue(var1, "children") instanceof List var3 && !var3.isEmpty()) {
            for (Object var5 : var3) {
               if (var5 instanceof Map var6 && !var6.isEmpty() && this.isRawUiImageGlyphBlock(var6)) {
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

   protected boolean isRawImageGroupRoot(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         String var2;
         boolean var10000;
         label29: {
            var2 = this.firstNonBlank(
                  new String[]{this.stringValue(this.readMapPathValue(var1, "id")), this.stringValue(this.readMapPathValue(var1, "params.id"))}
               )
               .toLowerCase(Locale.ROOT);
            if (this.readMapPathValue(var1, "children") instanceof List var5 && !var5.isEmpty()) {
               var10000 = true;
               break label29;
            }

            var10000 = false;
         }

         boolean var4 = var10000;
         if (!var4) {
            return false;
         } else if (!var2.isBlank() && var2.startsWith("img_")) {
            return true;
         } else {
            String var6 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "type"))}).toLowerCase(Locale.ROOT);
            return !"hitbox".equals(var6) ? false : this.hasDirectRawUiImageGlyphChild(var1);
         }
      } else {
         return false;
      }
   }

   protected Map<String, Object> resolveImageGroupRootRawTarget(EditorSession var1, String var2, Map<String, Object> var3, String var4) {
      if (this.isRawImageGroupRoot(var3)) {
         return var3;
      } else if (var1 == null) {
         return null;
      } else {
         String var5 = this.firstNonBlank(new String[]{var4, this.resolveAnimationTimelineHierarchyTargetPath(var1, var2)});

         while (!var5.isBlank()) {
            int var6 = var5.lastIndexOf(".children.");
            if (var6 < 0) {
               break;
            }

            var5 = var5.substring(0, var6);
            if (var5.isBlank()) {
               break;
            }

            Map var7 = this.resolveRawMapAtPath(var1.rawBlocks, var5);
            if (this.isRawImageGroupRoot(var7)) {
               return var7;
            }
         }

         return null;
      }
   }

   protected double readRawTargetLayer(Map<String, Object> var1) {
      String var2 = this.resolveRawTargetLayerPath(var1);
      return var2.isBlank() ? 0.0 : this.readMapPathDouble(var1, var2, 0.0);
   }

   protected boolean setRawTargetLayer(Map<String, Object> var1, double var2) {
      if (var1 == null) {
         return false;
      } else {
         String var4;
         if (this.hasMapPath(var1, "layer")) {
            var4 = "layer";
         } else if (this.hasMapPath(var1, "size.depth")) {
            var4 = "size.depth";
         } else if (this.hasMapPath(var1, "depth")) {
            var4 = "depth";
         } else if (this.hasMapPath(var1, "params.layer")) {
            var4 = "params.layer";
         } else if (this.hasMapPath(var1, "params.depth")) {
            var4 = "params.depth";
         } else {
            var4 = var1.containsKey("params") ? "params.layer" : "layer";
         }

         double var5 = this.readMapPathDouble(var1, var4, 0.0);
         if (Math.abs(var5 - var2) < 1.0E-4) {
            return false;
         } else {
            this.setMapPathValue(var1, var4, Double.valueOf(var2));
            return true;
         }
      }
   }

   protected void applySiblingLayerOrdering(EditorSession var1, List<String> var2) {
      if (var1 != null && var2 != null && !var2.isEmpty()) {
         double var3 = Double.NEGATIVE_INFINITY;

         for (Object var6_raw : var2) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            var3 = Math.max(var3, this.readTargetLayer(var1, var6));
         }

         if (!Double.isFinite(var3)) {
            var3 = (double)var2.size();
         }

         double var9 = var3;

         for (Object var8_raw : var2) {
            String var8 = var8_raw != null ? var8_raw.toString() : null;
            this.setTargetLayer(var1, var8, var9);
            var9--;
         }
      }
   }

   protected boolean setTargetLayer(EditorSession var1, String var2, double var3) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         LinkedHashMap<String, Map<String, Object>> var5 = new LinkedHashMap<>();
         List var6 = var1.renderBlocks;
         if (var6 == null || var6.isEmpty()) {
            var6 = this.resolveRenderableBlocks(var1.rawBlocks, var1.components);
         }

         if (var6 != null) {
            for (Map var8 : (List<Map>)(List)var6) {
               if (var8 != null && !var8.isEmpty()) {
                  String var9 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var8, "__editor_target_id"))});
                  if (this.equalsNullable(var9, var2)) {
                     String var10 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var8, "__editor_target_path"))});
                     if (!var10.isBlank() && !var5.containsKey(var10)) {
                        Map var11 = this.resolveRawMapAtPath(var1.rawBlocks, var10);
                        if (var11 != null) {
                           var5.put(var10, var11);
                        }
                     }
                  }
               }
            }
         }

         if (var5.isEmpty()) {
            Map var27 = this.resolveRawTargetByTargetId(var1, var2);
            if (var27 == null) {
               return false;
            }

            String var29 = this.resolveAnimationTimelineHierarchyTargetPath(var1, var2);
            if (var29.isBlank()) {
               var29 = var2;
            }

            var5.put(var29, var27);
         }

         String var28 = "";
         int var30 = Integer.MAX_VALUE;

         for (String var33 : var5.keySet()) {
            if (var33 != null && !var33.isBlank()) {
               int var35 = this.resolveLayerTargetDepth(var33);
               if (var28.isBlank() || var35 < var30 || var35 == var30 && var33.length() < var28.length()) {
                  var28 = var33;
                  var30 = var35;
               }
            }
         }

         if (var28.isBlank()) {
            var28 = (String)var5.keySet().iterator().next();
         }

         Map var32 = (Map)var5.get(var28);
         String var34 = this.resolveRawTargetLayerPath(var32);
         if (var32 != null && !var34.isBlank()) {
            double var36 = this.readMapPathDouble(var32, var34, 0.0);
            double var13 = var3 - var36;
            LinkedHashSet var15 = new LinkedHashSet(var5.values());
            String var16 = this.firstNonBlank(new String[]{var28, this.resolveAnimationTimelineHierarchyTargetPath(var1, var2)});
            Map var17 = this.resolveImageGroupRootRawTarget(var1, var2, var32, var16);
            boolean var18 = false;
            if (var17 == null && var5.size() > 1) {
               int var19 = 0;
               int var20 = 0;

               for (Map var22 : var5.values()) {
                  if (var22 != null && !var22.isEmpty()) {
                     String var23 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var22, "type"))}).toLowerCase(Locale.ROOT);
                     if (!"hitbox".equals(var23)) {
                        var19++;
                        if (this.isRawUiImageGlyphBlock(var22)) {
                           var20++;
                        }
                     }
                  }
               }

               var18 = var19 > 1 && var20 == var19;
            }

            boolean var37 = var17 != null || var18;
            if (!var37 && Math.abs(var13) < 1.0E-4) {
               return false;
            } else {
               if (var17 != null) {
                  this.collectRawLayerTargetsRecursive(var17, var15);
               }

               Object var38 = this.readMapPathValue(var32, "__editor_inherit_target_to_children");
               if (!var37 && this.parseBooleanFlag(var38, false)) {
                  this.collectRawLayerTargetsRecursive(var32, var15);
               }

               boolean var39 = false;

               for (Map var41 : (Collection<Map>)(Collection)var15) {
                  String var24 = this.resolveRawTargetLayerPath(var41);
                  if (!var24.isBlank()) {
                     if (var37) {
                        var39 |= this.setRawTargetLayer(var41, var3);
                     } else {
                        double var25 = this.readMapPathDouble(var41, var24, 0.0);
                        var39 |= this.setRawTargetLayer(var41, var25 + var13);
                     }
                  }
               }

               return var39;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected String resolveDirectLayerParentTargetId(String var1) {
      if (var1 != null && !var1.isBlank()) {
         int var2 = var1.lastIndexOf(".children.");
         if (var2 < 0) {
            return "";
         } else {
            String var3 = var1.substring(0, var2);
            return var3 == null ? "" : var3;
         }
      } else {
         return "";
      }
   }

   protected boolean isLayersLeftHeld(EditorSession var1) {
      return var1 == null ? false : (long)Bukkit.getCurrentTick() - var1.lastLeftClickTick <= 2L;
   }

   protected boolean handleLayersPanelClick(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         List var7 = this.collectLayerTreeRows(var2);
         EditorRect var8 = this.resolveLayersListRect(var2, var7);
         if (var7.isEmpty()) {
            return this.isInsideRect(var8, var3, var5);
         } else {
            int var9 = Math.max(0, var7.size() - 17);
            int var10 = (int)Math.floor(this.clampLayersScrollValue((double)var2.layersScrollOffset, var9) + 1.0E-4);
            var2.layersScrollOffset = var10;
            var2.layersScrollPosition = (double)var10;
            var2.layersScrollTarget = (double)var10;
            var2.layersScrollVelocity = 0.0;
            int var11 = this.resolveLayerSlotByHit(var2, var3, var5);
            if (var11 >= 1 && var11 <= 17) {
               int var12 = var10 + (var11 - 1);
               if (var12 >= 0 && var12 < var7.size()) {
                  LayersPanelManager.LayerTreeRow var13 = (LayersPanelManager.LayerTreeRow)var7.get(var12);
                  LayersPanelManager.LayerEntry var14 = var13.entry;
                  if (this.isLayerDropdownHit(var2, var10, var11, var3, var5) && var13.dropdown) {
                     this.finishLayersReorderDrag(var1, var2, false);
                     var2.layersScrollAnimationToken++;
                     var2.layersScrollVelocity = 0.0;
                     var2.layersScrollTarget = var2.layersScrollPosition;
                     this.recordEditorMutation(var2);
                     if (var2.collapsedLayerTargetIds.contains(var14.targetId)) {
                        var2.collapsedLayerTargetIds.remove(var14.targetId);
                     } else {
                        var2.collapsedLayerTargetIds.add(var14.targetId);
                     }

                     this.renderLayersPanel(var1, var2);
                     return true;
                  } else if (!this.isInsideShellBlock(var2, this.layerOpacityHitboxId(var11), var3, var5)
                     && !this.isInsideShellBlock(var2, this.layerOpacityId(var11), var3, var5)) {
                     boolean var15 = var1.isSneaking();
                     if (var15) {
                        this.toggleLayerSelectionTarget(var2, var14.targetId);
                        this.finishLayersReorderDrag(var1, var2, false);
                     } else {
                        var2.selectedElementId = var14.targetId;
                        var2.additionalSelectedElementIds.clear();
                        this.armLayersReorderDrag(var2, var14.targetId, var3, var5);
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
                        this.playEditorSfx(var1, var2, "layer-selected");
                     }

                     this.updateEditorPropertiesSidebar(var1, var2);
                     this.renderLayersPanel(var1, var2);
                     return true;
                  } else {
                     this.finishLayersReorderDrag(var1, var2, false);
                     this.toggleLayerVisibility(var1, var2, var14.targetId);
                     return true;
                  }
               } else {
                  return true;
               }
            } else {
               return this.isInsideRect(var8, var3, var5);
            }
         }
      } else {
         return false;
      }
   }

   protected String resolveLayerSelectionTargetByHit(EditorSession var1, double var2, double var4) {
      return this.resolveLayerSelectionTargetByHit(var1, var2, var4, false);
   }

   protected String resolveLayerSelectionTargetByHit(EditorSession var1, double var2, double var4, boolean var6) {
      if (var1 == null) {
         return null;
      } else {
         List var7 = this.collectLayerTreeRows(var1);
         if (var7.isEmpty()) {
            return null;
         } else {
            int var8 = Math.max(0, var7.size() - 17);
            int var9 = (int)Math.floor(this.clampLayersScrollValue((double)var1.layersScrollOffset, var8) + 1.0E-4);
            int var10 = this.resolveLayerSlotByHit(var1, var2, var4);
            if (var10 >= 1 && var10 <= 17) {
               int var11 = var9 + (var10 - 1);
               if (var11 >= 0 && var11 < var7.size()) {
                  LayersPanelManager.LayerTreeRow var12 = (LayersPanelManager.LayerTreeRow)var7.get(var11);
                  LayersPanelManager.LayerEntry var13 = var12 == null ? null : var12.entry;
                  if (var13 != null && var13.targetId != null && !var13.targetId.isBlank()) {
                     if (!var6) {
                        if (this.isLayerDropdownHit(var1, var9, var10, var2, var4) && var12.dropdown) {
                           return null;
                        }

                        if (this.isInsideShellBlock(var1, this.layerOpacityHitboxId(var10), var2, var4)
                           || this.isInsideShellBlock(var1, this.layerOpacityId(var10), var2, var4)) {
                           return null;
                        }
                     }

                     return var13.targetId;
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
      }
   }

   protected String resolveLayerSelectionTargetPathByHit(EditorSession var1, double var2, double var4, boolean var6) {
      if (var1 == null) {
         return "";
      } else {
         List var7 = this.collectLayerTreeRows(var1);
         if (var7.isEmpty()) {
            return "";
         } else {
            int var8 = Math.max(0, var7.size() - 17);
            int var9 = (int)Math.floor(this.clampLayersScrollValue((double)var1.layersScrollOffset, var8) + 1.0E-4);
            int var10 = this.resolveLayerSlotByHit(var1, var2, var4);
            if (var10 >= 1 && var10 <= 17) {
               int var11 = var9 + (var10 - 1);
               if (var11 >= 0 && var11 < var7.size()) {
                  LayersPanelManager.LayerTreeRow var12 = (LayersPanelManager.LayerTreeRow)var7.get(var11);
                  LayersPanelManager.LayerEntry var13 = var12 == null ? null : var12.entry;
                  if (var13 != null && var13.targetPath != null && !var13.targetPath.isBlank()) {
                     if (!var6) {
                        if (this.isLayerDropdownHit(var1, var9, var10, var2, var4) && var12.dropdown) {
                           return "";
                        }

                        if (this.isInsideShellBlock(var1, this.layerOpacityHitboxId(var10), var2, var4)
                           || this.isInsideShellBlock(var1, this.layerOpacityId(var10), var2, var4)) {
                           return "";
                        }
                     }

                     return var13.targetPath;
                  } else {
                     return "";
                  }
               } else {
                  return "";
               }
            } else {
               return "";
            }
         }
      }
   }

   protected void renderLayersPanel(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         if (!this.isLayersPanelRuntimeAvailable(var2)) {
            this.clearLayersReorderGhostHud(var1);
            this.clearLayersRuntimeHud(var1);
         } else if (var2.rightSidebarTab != EditorSidebarTab.LAYERS) {
            this.clearLayersReorderGhostHud(var1);
            this.clearLayersRuntimeHud(var1);
         } else {
            List var3 = this.collectLayerTreeRows(var2);
            List var4 = this.buildLayersRowsWithReorderPlaceholder(var2, var3);
            int var5 = Math.max(0, var4.size() - 17);
            int var6 = (int)Math.floor(this.clampLayersScrollValue((double)var2.layersScrollOffset, var5) + 1.0E-4);
            int var7 = Math.min(var4.size(), var6 + 17);
            long var8 = this.buildLayersWindowSignature(var2, var4, var6, var7);
            boolean var10 = var4.size() > 17;
            boolean var11 = var2.runtimeLayerShellIds.isEmpty()
               || var2.layersRenderedStart != var6
               || var2.layersRenderedCount != var7 - var6
               || var2.layersRenderedSignature != var8;
            if (var11) {
               List var12 = var4.subList(var6, var7);
               this.renderLayersRuntimeRows(var1, var2, var12, 0.0, var6, var10);
               var2.layersRenderedStart = var6;
               var2.layersRenderedCount = var7 - var6;
               var2.layersRenderedFraction = 0.0;
               var2.layersRenderedSignature = var8;
            }

            var2.layersRenderedFraction = 0.0;
            var2.layersScrollOffset = var6;
            var2.layersScrollPosition = (double)var6;
            var2.layersScrollTarget = (double)var6;
            var2.layersScrollVelocity = 0.0;
            if (!this.isLayersReorderActive(var2)) {
               this.clearLayersReorderGhostHud(var1);
            }
         }
      }
   }

   protected boolean isLayersPanelRuntimeAvailable(EditorSession var1) {
      return var1 == null ? false : this.findShellBlockSection(var1, "layers") != null || this.findShellBlockSection(var1, "layers_box") != null;
   }

   protected void renderLayersRuntimeRows(Player var1, EditorSession var2, List<LayersPanelManager.LayerTreeRow> var3, double var4, int var6, boolean var7) {
      List var8 = this.buildLayersRuntimeGridBlocks(var2, var3, var4, var6);
      List var9 = this.resolveRenderableBlocksNoCopy(var8, var2.components);
      HashMap var10 = new HashMap();
      LinkedHashSet var11 = new LinkedHashSet();
      int var12 = 0;

      for (Map var14 : (List<Map>)(List)var9) {
         var12++;
         String var15 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var14, "type")), "block"}).toLowerCase(Locale.ROOT);
         if (this.isRenderableBlockType(var15)) {
            String var16 = this.resolveElementId(var14, var12, var10);
            String var17 = this.layerRuntimeLogicalId(var16);
            if (this.canUseFastLayerRuntimeRender(var14, var15)) {
               this.renderLayerRuntimeShellElementFast(var1, var2, var14, var17, var15, var7);
            } else {
               ConfigurationSection var18 = this.mapToSection(var14);
               if (var18 == null) {
                  continue;
               }

               this.renderLayerRuntimeShellElement(var1, var2, var18, var17, var15, var7);
            }

            var11.add(var17);
         }
      }

      this.clearStaleLayerRuntimeShellIds(var1, var2, var11);
      var2.runtimeLayerShellIds.clear();
      var2.runtimeLayerShellIds.addAll(var11);
   }

   protected String resolveElementId(Map<String, Object> var1, int var2, Map<String, Integer> var3) {
      String var4 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "id"))});
      if (var4.isBlank()) {
         String var5 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "__editor_block_path"))});
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

   @Override
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

   protected boolean canUseFastLayerRuntimeRender(Map<String, Object> var1, String var2) {
      if (var1 != null && var2 != null) {
         String var3 = var2.toLowerCase(Locale.ROOT);
         return !"text".equals(var3) && !"block".equals(var3)
            ? false
            : !this.hasMapPath(var1, "outline.size")
               && !this.hasMapPath(var1, "outline.width")
               && !this.hasMapPath(var1, "outline.thickness")
               && !this.hasMapPath(var1, "outline.stroke")
               && !this.hasMapPath(var1, "stroke.size")
               && !this.hasMapPath(var1, "stroke.width")
               && !this.hasMapPath(var1, "font")
               && !this.hasMapPath(var1, "style.font")
               && !this.hasMapPath(var1, "text.font");
      } else {
         return false;
      }
   }

   protected TextAlignment readTextAlignmentFromMap(Map<String, Object> var1) {
      String var2 = this.firstNonBlank(
         new String[]{
            this.stringValue(this.readMapPathValue(var1, "align")),
            this.stringValue(this.readMapPathValue(var1, "text.align")),
            this.stringValue(this.readMapPathValue(var1, "style.align"))
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

   protected int readTextWrapLineWidthFromMap(Map<String, Object> var1) {
      Object var2 = this.readMapPathValue(var1, "text-wrap");
      if (var2 == null) {
         var2 = this.readMapPathValue(var1, "textWrap");
      }

      if (var2 == null) {
         var2 = this.readMapPathValue(var1, "text.wrap");
      }

      return this.normalizeTextWrapLineWidth(var2);
   }

   protected void renderLayerRuntimeShellElementFast(Player var1, EditorSession var2, Map<String, Object> var3, String var4, String var5, boolean var6) {
      if (var1 != null && var2 != null && var3 != null && var4 != null && !var4.isBlank()) {
         String var7 = "editor_shell_" + var4;
         double var8 = this.readMapPathDouble(var3, "position.x", this.readMapPathDouble(var3, "x", 0.0));
         double var10 = this.readMapPathDouble(var3, "position.y", this.readMapPathDouble(var3, "y", 0.0));
         double var12 = this.readMapPathDouble(var3, "layer", this.readMapPathDouble(var3, "size.depth", this.readMapPathDouble(var3, "depth", 0.0)));
         double var14 = this.readMapPathDouble(var3, "size.width", this.readMapPathDouble(var3, "width", this.readMapPathDouble(var3, "scale.width", 20.0)));
         double var16 = this.readMapPathDouble(var3, "size.height", this.readMapPathDouble(var3, "height", this.readMapPathDouble(var3, "scale.height", 20.0)));
         boolean var18 = var14 < 0.0;
         boolean var19 = var16 < 0.0;
         double var20 = Math.max(1.0, Math.abs(var14));
         double var22 = Math.max(1.0, Math.abs(var16));
         EditorRect var24 = new EditorRect(var8, var10, var20, var22);
         boolean var25 = "text".equals(var5) && this.isLayerSwappableTextRuntimeId(var4);
         if (this.shouldSkipLayerStaticMove(var1, var2, var4, var24, var6) && !var25) {
            var2.shellRuntimeRects.put(var4, var24);
         } else {
            double var26 = this.readMapPathDouble(var3, "rotation", this.readMapPathDouble(var3, "rotate", 0.0));
            int var28 = Math.max(0, Math.min(255, (int)Math.round(this.readMapPathDouble(var3, "opacity", 255.0))));
            TextAlignment var29 = "text".equals(var5) ? this.readTextAlignmentFromMap(var3) : TextAlignment.CENTER;
            int var30 = "text".equals(var5) ? this.readTextWrapLineWidthFromMap(var3) : 200;
            double var31 = "text".equals(var5) ? this.toInternalTextTopY(var10, var22) : var10;
            double var33 = "text".equals(var5) ? this.applyTextAlignmentOffset(var8, var20, var29) : var8;
            HudPositionCalculator.Placement var35 = this.positionCalculator.calculateBoxPlacement(var33, var31, var12, var20, var22);
            String var36;
            if ("text".equals(var5)) {
               var36 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var3, "text")), ""});
            } else {
               var36 = this.firstNonBlank(
                  new String[]{this.stringValue(this.readMapPathValue(var3, "unicode")), this.stringValue(this.readMapPathValue(var3, "text")), "█"}
               );
            }

            String var37 = this.firstNonBlank(
               new String[]{this.stringValue(this.readMapPathValue(var3, "color")), this.stringValue(this.readMapPathValue(var3, "style.color"))}
            );
            var36 = this.withHexPrefix(var36, var37);
            String var38 = this.applyPlaceholderApi(var1, var36);
            var38 = this.applyPreferredFont(var38, null, !"text".equals(var5));
            if ("text".equals(var5) && this.trySwapLayerTextWithoutMove(var1, var2, var4, var24, var6, var29, var30, var38, var28, var26, var18, var19)) {
               var2.shellRuntimeRects.put(var4, var24);
            } else {
               Entity var39 = this.hudService.getHud(var1, var7);
               if (var39 != null) {
                  EditorRect var40 = var2.shellRuntimeRects.get(var4);
                  boolean var41 = var40 != null && !this.sameRect(var40, var24);
                  if (var41) {
                     this.hudService.setHudTransitionTicks(var39, 1, 1);
                  } else {
                     this.hudService.setHudNoTransition(var39);
                  }
               }

               this.upsertHud(var1, var7, var35.location(), var35.scale(), var38, var28, var29, var30);
               this.setLayerTextInterpolationInstant(var1, var4);
               this.applyElementTransformById(var1, var7, var26, var18, var19);
               this.clearOutlineHud(var1, var7);
               var2.shellRuntimeRects.put(var4, var24);
            }
         }
      }
   }

   protected long buildLayersWindowSignature(EditorSession var1, List<LayersPanelManager.LayerTreeRow> var2, int var3, int var4) {
      if (var2 != null && !var2.isEmpty()) {
         int var5 = Math.max(0, Math.min(var2.size(), var3));
         int var6 = Math.max(var5, Math.min(var2.size(), var4));
         long var7 = 1469598103934665603L;
         var7 = this.mixLayersSignature(var7, var5);
         var7 = this.mixLayersSignature(var7, var6);

         for (int var9 = var5; var9 < var6; var9++) {
            LayersPanelManager.LayerTreeRow var10 = (LayersPanelManager.LayerTreeRow)var2.get(var9);
            var7 = this.mixLayersSignature(var7, var10.entry.targetId == null ? 0 : var10.entry.targetId.hashCode());
            var7 = this.mixLayersSignature(var7, var10.entry.displayName == null ? 0 : var10.entry.displayName.hashCode());
            var7 = this.mixLayersSignature(var7, var10.depthValue);
            var7 = this.mixLayersSignature(var7, var10.dropdown ? 1 : 0);
            var7 = this.mixLayersSignature(var7, var10.open ? 1 : 0);
            var7 = this.mixLayersSignature(var7, var10.children ? 1 : 0);
            var7 = this.mixLayersSignature(var7, var10.parent ? 1 : 0);
            var7 = this.mixLayersSignature(var7, var10.under ? 1 : 0);
            var7 = this.mixLayersSignature(var7, var10.end ? 1 : 0);
            var7 = this.mixLayersSignature(var7, var10.dragPlaceholder ? 1 : 0);
            var7 = this.mixLayersSignature(var7, this.isTargetVisible(var1, var10.entry.targetId) ? 1 : 0);
            var7 = this.mixLayersSignature(var7, this.isSelectedTarget(var1, var10.entry.targetId) ? 1 : 0);
            var7 = this.mixLayersSignature(
               var7, this.isLayersReorderActive(var1) && this.equalsNullable(var10.entry.targetId, var1.layersReorderHoverTargetId) ? 1 : 0
            );
            var7 = this.mixLayersSignature(var7, var10.extraLines.size());

            for (Object var12_raw : var10.extraLines) {
               int var12 = ((Number)var12_raw).intValue();
               var7 = this.mixLayersSignature(var7, var12);
            }
         }

         return var7;
      } else {
         return 0L;
      }
   }

   protected long mixLayersSignature(long var1, int var3) {
      long var4 = var1 ^ (long)var3 & 4294967295L;
      return var4 * 1099511628211L;
   }

   protected void renderLayerRuntimeShellElement(Player var1, EditorSession var2, ConfigurationSection var3, String var4, String var5, boolean var6) {
      if (var1 != null && var2 != null && var3 != null && var4 != null && !var4.isBlank()) {
         String var7 = "editor_shell_" + var4;
         double var8 = this.readDouble(var3, "position.x", "x", 0.0);
         double var10 = this.readDouble(var3, "position.y", "y", 0.0);
         double var12 = this.readDouble(var3, "layer", "layer", this.readDouble(var3, "size.depth", "depth", 0.0));
         double var14 = this.readDouble(var3, "size.width", "width", this.readDouble(var3, "scale.width", "width", 20.0));
         double var16 = this.readDouble(var3, "size.height", "height", this.readDouble(var3, "scale.height", "height", 20.0));
         boolean var18 = var14 < 0.0;
         boolean var19 = var16 < 0.0;
         double var20 = Math.max(1.0, Math.abs(var14));
         double var22 = Math.max(1.0, Math.abs(var16));
         EditorRect var24 = new EditorRect(var8, var10, var20, var22);
         boolean var25 = "text".equals(var5) && this.isLayerSwappableTextRuntimeId(var4);
         if (this.shouldSkipLayerStaticMove(var1, var2, var4, var24, var6) && !var25) {
            var2.shellRuntimeRects.put(var4, var24);
         } else {
            double var26 = this.readDouble(var3, "rotation", "rotate", 0.0);
            int var28 = this.readOpacity(var3, 255);
            TextAlignment var29 = "text".equals(var5) ? this.readTextAlignment(var3) : TextAlignment.CENTER;
            int var30 = "text".equals(var5) ? this.readTextWrapLineWidth(var3) : 200;
            double var31 = "text".equals(var5) ? this.toInternalTextTopY(var10, var22) : var10;
            double var33 = "text".equals(var5) ? this.applyTextAlignmentOffset(var8, var20, var29) : var8;
            HudPositionCalculator.Placement var35 = this.positionCalculator.calculateBoxPlacement(var33, var31, var12, var20, var22);
            String var36;
            if ("text".equals(var5)) {
               var36 = this.firstNonBlank(new String[]{var3.getString("text"), ""});
            } else {
               var36 = this.firstNonBlank(new String[]{var3.getString("unicode"), var3.getString("text"), "█"});
            }

            String var37 = this.firstNonBlank(new String[]{var3.getString("color"), var3.getString("style.color")});
            var36 = this.withHexPrefix(var36, var37);
            String var38 = this.applyPlaceholderApi(var1, var36);
            var38 = this.applyPreferredFont(var38, var3, !"text".equals(var5));
            if ("text".equals(var5) && this.trySwapLayerTextWithoutMove(var1, var2, var4, var24, var6, var29, var30, var38, var28, var26, var18, var19)) {
               var2.shellRuntimeRects.put(var4, var24);
            } else {
               double var39 = "text".equals(var5) ? 0.0 : this.readOutlineSize(var3);
               String var41 = "text".equals(var5) ? "" : this.readOutlineColor(var3);
               String var42 = !"text".equals(var5) && var39 > 1.0E-4 ? this.applyOutlineColor(var38, var41) : null;
               if (this.isRoundedType(var5)) {
                  boolean var43 = this.isDirectionalRoundedType(var5);
                  double var44 = this.readRoundedRadius(var3, var20, var22);
                  String var46 = this.readRoundedCornerUnicode(var3);
                  String var47 = var43 ? this.readRoundedCornerUnicodeTopLeft(var3) : "";
                  String var48 = var43 ? this.readRoundedCornerUnicodeTopRight(var3) : "";
                  String var49 = var43 ? this.readRoundedCornerUnicodeBottomRight(var3) : "";
                  String var50 = var43 ? this.readRoundedCornerUnicodeBottomLeft(var3) : "";
                  double var51 = this.readRoundedTopLeftOffsetX(var3);
                  double var53 = this.readRoundedTopLeftOffsetY(var3);
                  double var55 = this.readRoundedTopRightOffsetX(var3);
                  double var57 = this.readRoundedTopRightOffsetY(var3);
                  double var59 = this.readRoundedBottomRightOffsetX(var3);
                  double var61 = this.readRoundedBottomRightOffsetY(var3);
                  double var63 = this.readRoundedBottomLeftOffsetX(var3);
                  double var65 = this.readRoundedBottomLeftOffsetY(var3);
                  double var67 = var8 + var20 / 2.0;
                  double var69 = var31 + var22 / 2.0;
                  this.renderRoundedOutline(
                     var1,
                     var7,
                     var8,
                     var31,
                     var12,
                     var20,
                     var22,
                     var42,
                     var38,
                     var28,
                     var44,
                     var46,
                     var47,
                     var48,
                     var49,
                     var50,
                     var51,
                     var53,
                     var55,
                     var57,
                     var59,
                     var61,
                     var63,
                     var65,
                     var26,
                     var18,
                     var19,
                     var39,
                     var67,
                     var69
                  );
                  this.renderRoundedBlock(
                     var1,
                     var7,
                     var8,
                     var31,
                     var12,
                     var20,
                     var22,
                     var38,
                     var28,
                     var44,
                     var46,
                     var47,
                     var48,
                     var49,
                     var50,
                     var51,
                     var53,
                     var55,
                     var57,
                     var59,
                     var61,
                     var63,
                     var65,
                     var26,
                     var18,
                     var19,
                     var67,
                     var69
                  );
               } else {
                  Entity var73 = this.hudService.getHud(var1, var7);
                  if (var73 != null) {
                     EditorRect var74 = var2.shellRuntimeRects.get(var4);
                     boolean var45 = var74 != null && !this.sameRect(var74, var24);
                     if (var45) {
                        this.hudService.setHudTransitionTicks(var73, 1, 1);
                     } else {
                        this.hudService.setHudNoTransition(var73);
                     }
                  }

                  this.upsertHud(var1, var7, var35.location(), var35.scale(), var38, var28, var29, var30);
                  this.setLayerTextInterpolationInstant(var1, var4);
                  this.applyElementTransformById(var1, var7, var26, var18, var19);
                  if ("text".equals(var5)) {
                     this.clearOutlineHud(var1, var7);
                  } else {
                     double var75 = var33 + var20 / 2.0;
                     double var76 = var31 + var22 / 2.0;
                     this.renderSimpleOutline(var1, var7, var33, var31, var12, var20, var22, var42, var28, var29, var26, var18, var19, var39, var75, var76);
                  }
               }

               var2.shellRuntimeRects.put(var4, var24);
            }
         }
      }
   }

   protected List<Map<String, Object>> buildLayersRuntimeGridBlocks(EditorSession var1, List<LayersPanelManager.LayerTreeRow> var2, double var3, int var5) {
      ArrayList var6 = new ArrayList();
      if (var2 != null) {
         for (int var7 = 1; var7 <= var2.size(); var7++) {
            LayersPanelManager.LayerTreeRow var8 = (LayersPanelManager.LayerTreeRow)var2.get(var7 - 1);
            LinkedHashMap var9 = new LinkedHashMap();
            var9.put("id", this.layerSlotBaseId(var7));
            var9.put("slot", this.layerSlotBaseId(var7));
            var9.put("name", this.formatLayerRuntimeNameForRow(var8, "Layer"));
            var9.put("dropdown", var8.dropdown);
            var9.put("children", var8.children);
            var9.put("under", var8.under);
            var9.put("open", var8.open);
            var9.put("parent", var8.parent);
            var9.put("end", var8.end);
            var9.put("val", var8.depthValue);
            var9.put("extralines", var8.extraLines);
            var9.put("name_color", "666666");
            var9.put("row_color", "66b9ff");
            var9.put("row_opacity", 0);
            var9.put("divider_opacity", 255);
            boolean var10 = this.isLayersReorderActive(var1)
               && !var8.dragPlaceholder
               && var8.entry != null
               && this.equalsNullable(var8.entry.targetId, var1.layersReorderHoverTargetId);
            if (var10) {
               var9.put("row_opacity", 64);
            }

            if (var8.dragPlaceholder) {
               var9.put("name", " ");
               var9.put("dropdown", false);
               var9.put("children", false);
               var9.put("under", false);
               var9.put("open", false);
               var9.put("parent", false);
               var9.put("end", false);
               var9.put("val", 0);
               var9.put("extralines", Collections.emptyList());
               var9.put("box1_icon", "");
               var9.put("box2_icon", "");
               var9.put("opacity_icon", "");
               var9.put("name_color", "666666");
               var9.put("row_opacity", 0);
               var9.put("divider_opacity", 0);
            } else {
               boolean var11 = this.isTargetVisible(var1, var8.entry.targetId);
               boolean var12 = this.isSelectedTarget(var1, var8.entry.targetId);
               boolean var13 = var12 || var10;
               var9.put("box1_icon", var13 ? "\ue63c" : "\ue638");
               var9.put("box2_icon", var13 ? "\ue63d" : "\ue639");
               var9.put("name_color", var13 ? "ffffff" : "666666");
               if (!var11) {
                  var9.put("opacity_icon", "");
               } else {
                  var9.put("opacity_icon", var13 ? "\ue635" : "\ue634");
               }
            }

            LinkedHashMap var21 = new LinkedHashMap();
            var21.put("component", "editor_layer");
            var21.put("params", var9);
            var6.add(var21);
         }
      }

      double var19 = this.resolveLayersGridStartX(var1);
      double var20 = this.resolveLayersRowStep(var1);
      double var22 = this.resolveLayersGridStartY(var1) - Math.max(0.0, var3) * var20;
      double var23 = this.resolveLayersGridGap(var1);
      Double var15 = this.resolveLayersGridElementHeight(var1);
      LinkedHashMap var16 = new LinkedHashMap();
      var16.put("x", var19);
      var16.put("y", var22);
      LinkedHashMap var17 = new LinkedHashMap();
      var17.put("type", "grid_block");
      var17.put("direction", "column");
      var17.put("gap", var23);
      if (var15 != null) {
         var17.put("element_h", var15);
      }

      var17.put("position", var16);
      var17.put("children", var6);
      ArrayList var18 = new ArrayList();
      var18.add(var17);
      return var18;
   }

   protected String formatLayerRuntimeNameForRow(LayersPanelManager.LayerTreeRow var1, String var2) {
      String var3 = var1 != null && var1.entry != null
         ? this.firstNonBlank(new String[]{var1.entry.displayName, var2, "Layer"})
         : this.firstNonBlank(new String[]{var2, "Layer"});
      int var4 = var1 != null && var1.entry != null ? this.resolveLayerTargetDepth(var1.entry.targetId) : 0;
      return this.truncateLayerDisplayNameForDepth(var3, var4);
   }

   protected String truncateLayerDisplayNameForDepth(String var1, int var2) {
      String var3 = this.firstNonBlank(new String[]{var1, "Layer"});
      int var4 = Math.max(0, var2);
      int var5 = Math.max(4, 26 - var4 * 3);
      return var3.length() <= var5 ? var3 : var3.substring(0, var5) + "...";
   }

   protected int resolveLayerTargetDepth(String var1) {
      if (var1 != null && !var1.isBlank()) {
         int var2 = 0;
         int var3 = 0;
         String var4 = ".children.";

         while (true) {
            int var5 = var1.indexOf(var4, var3);
            if (var5 < 0) {
               return var2;
            }

            var2++;
            var3 = var5 + var4.length();
         }
      } else {
         return 0;
      }
   }

   protected void clearStaleLayerRuntimeShellIds(Player var1, EditorSession var2, LinkedHashSet<String> var3) {
      if (var1 != null && var2 != null && !var2.runtimeLayerShellIds.isEmpty()) {
         LinkedHashSet var4 = new LinkedHashSet<>(var2.runtimeLayerShellIds);
         var4.removeAll(var3);

         for (Object var6_raw : var4) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            this.removeLayerRuntimeShellElement(var1, var6);
            var2.shellRuntimeRects.remove(var6);
         }
      }
   }

   protected void removeLayerRuntimeShellElement(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = "editor_shell_" + var2;
         this.clearOutlineHud(var1, var3);
         this.removeBaseHud(var1, var3);
         this.removeRoundedParts(var1, var3);
      }
   }

   protected int resolveLayerSlotByHit(EditorSession var1, double var2, double var4) {
      if (var1 == null) {
         return -1;
      } else {
         List var6 = this.collectLayerTreeRows(var1);
         int var7 = Math.max(0, var6.size() - 17);
         int var8 = (int)Math.floor(this.clampLayersScrollValue((double)var1.layersScrollOffset, var7) + 1.0E-4);
         int var9 = Math.max(0, Math.min(17, var6.size() - var8));
         if (var9 == 0) {
            return -1;
         } else {
            int var10 = -1;
            double var11 = Double.MAX_VALUE;

            for (int var13 = 1; var13 <= var9; var13++) {
               if (this.isInsideShellBlock(var1, this.layerSlotId(var13), var2, var4)
                  || this.isInsideShellBlock(var1, this.layerDropdownHitboxId(var13), var2, var4)
                  || this.isInsideShellBlock(var1, this.layerOpacityHitboxId(var13), var2, var4)) {
                  EditorRect var14 = this.findShellBlockRect(var1, this.layerSlotId(var13));
                  double var15 = var14 == null ? 0.0 : Math.abs(var4 - (var14.y + var14.height / 2.0));
                  if (var15 < var11) {
                     var11 = var15;
                     var10 = var13;
                  }
               }
            }

            if (var10 != -1) {
               return var10;
            } else {
               EditorRect var26 = this.findShellBlockRect(var1, this.layerSlotId(1));
               if (var26 == null) {
                  return -1;
               } else {
                  double var27 = Math.max(1.0, var26.height);
                  EditorRect var16 = this.resolveLayersListRect(var1, var6);
                  double var17 = var16 == null ? var26.x : var16.x;
                  double var19 = var16 == null ? var26.maxX() : var16.maxX();
                  double var21 = var26.y - var27 * 0.35;
                  double var23 = var26.y + var27 * (double)var9;
                  if (!(var2 < var17) && !(var2 > var19) && !(var4 < var21) && !(var4 > var23)) {
                     int var25 = (int)Math.floor((var4 - var26.y) / var27) + 1;
                     return Math.max(1, Math.min(var9, var25));
                  } else {
                     return -1;
                  }
               }
            }
         }
      }
   }

   protected boolean isLayerDropdownHit(EditorSession var1, int var2, int var3, double var4, double var6) {
      return var1 != null && var3 >= 1 ? this.isInsideShellBlockPadded(var1, this.layerDropdownHitboxId(var3), var4, var6, 8.0, 8.0) : false;
   }

   protected boolean isInsideShellBlockPadded(EditorSession var1, String var2, double var3, double var5, double var7, double var9) {
      EditorRect var11 = this.findShellBlockRect(var1, var2);
      if (var11 == null) {
         return false;
      } else {
         double var12 = Math.max(0.0, var7);
         double var14 = Math.max(0.0, var9);
         return var3 >= var11.x - var12 && var3 <= var11.maxX() + var12 && var5 >= var11.y - var14 && var5 <= var11.maxY() + var14;
      }
   }

   protected void clearLayersRuntimeHud(Player var1) {
      EditorSession var2 = var1 == null ? null : this.editorSessions.get(var1.getUniqueId());
      this.clearLayersRuntimeHud(var1, var2);
   }

   protected void clearLayersRuntimeHud(Player var1, EditorSession var2) {
      if (var2 != null) {
         var2.layersRenderQueued = false;
         this.stopLayersReorderAutoScroll(var2);
         this.clearLayersReorderArm(var2);
         var2.layersReorderActive = false;
         var2.layersReorderTargetId = null;
         var2.layersReorderHoverTargetId = null;
         var2.layersReorderGhostLabel = null;
         var2.layersReorderPreviewInsertIndex = -1;
         var2.layersReorderLastHitX = Double.NaN;
         var2.layersReorderLastHitY = Double.NaN;
      }

      if (var1 != null) {
         this.clearLayersReorderGhostHud(var1);
      }

      if (var1 != null && var2 != null && !var2.runtimeLayerShellIds.isEmpty()) {
         var2.layersScrollAnimationToken++;
         var2.layersScrollVelocity = 0.0;
         var2.layersRenderedStart = -1;
         var2.layersRenderedCount = 0;
         var2.layersRenderedFraction = 0.0;
         var2.layersRenderedSignature = 0L;

         for (String var5 : new ArrayList<>(var2.runtimeLayerShellIds)) {
            this.removeLayerRuntimeShellElement(var1, var5);
            var2.shellRuntimeRects.remove(var5);
         }

         var2.runtimeLayerShellIds.clear();
      }
   }

   protected EditorRect resolveLayersListRect(EditorSession var1) {
      return this.resolveLayersListRect(var1, this.collectLayerTreeRows(var1));
   }

   protected EditorRect resolveLayersListRect(EditorSession var1, List<LayersPanelManager.LayerTreeRow> var2) {
      List var3 = var2 == null ? Collections.emptyList() : var2;
      int var4 = Math.max(0, var3.size() - 17);
      int var5 = (int)Math.floor(this.clampLayersScrollValue(var1 == null ? 0.0 : (double)var1.layersScrollOffset, var4) + 1.0E-4);
      int var6 = Math.max(1, Math.min(17, Math.max(0, var3.size() - var5)));
      EditorRect var7 = this.findShellBlockRect(var1, this.layerSlotId(1));
      double var8 = this.resolveLayersGridStartY(var1);
      double var10 = this.resolveLayersRowStep(var1);
      return var7 != null
         ? new EditorRect(var7.x, var8, var7.width, (double)var6 * Math.max(1.0, var10))
         : new EditorRect(this.resolveLayersGridStartX(var1), var8, 289.0, (double)var6 * Math.max(1.0, var10));
   }

   protected double resolveLayersRowStep(EditorSession var1) {
      EditorRect var2 = this.findShellBlockRect(var1, this.layerSlotId(1));
      EditorRect var3 = this.findShellBlockRect(var1, this.layerSlotId(2));
      if (var2 != null && var3 != null) {
         double var4 = Math.abs(var3.y - var2.y);
         if (Double.isFinite(var4) && var4 > 1.0E-4) {
            return var4;
         }
      }

      Double var7 = this.resolveLayersGridElementHeight(var1);
      if (var7 != null) {
         double var5 = Math.abs(var7 + this.resolveLayersGridGap(var1));
         if (Double.isFinite(var5) && var5 > 1.0E-4) {
            return var5;
         }

         if (var7 > 1.0E-4) {
            return var7;
         }
      }

      return var2 != null && Double.isFinite(var2.height) && var2.height > 1.0E-4 ? var2.height : 45.0;
   }

   protected double resolveLayersGridStartX(EditorSession var1) {
      Map var2 = this.findLayersGridBlockMap(var1);
      if (var2 != null) {
         return this.readMapPathDouble(var2, "position.x", this.readMapPathDouble(var2, "x", 24.0));
      } else {
         EditorRect var3 = this.findShellBlockRect(var1, "layers");
         if (var3 != null) {
            return var3.x + 24.0;
         } else {
            EditorRect var4 = this.findShellBlockRect(var1, "right_sidebar");
            return var4 != null ? var4.x + 24.0 : 24.0;
         }
      }
   }

   protected double resolveLayersGridStartY(EditorSession var1) {
      Map var2 = this.findLayersGridBlockMap(var1);
      if (var2 != null) {
         return this.readMapPathDouble(var2, "position.y", this.readMapPathDouble(var2, "y", 130.0));
      } else {
         EditorRect var3 = this.findShellBlockRect(var1, "layers");
         if (var3 != null) {
            return var3.y + 130.0;
         } else {
            EditorRect var4 = this.findShellBlockRect(var1, "right_sidebar");
            return var4 != null ? var4.y + 130.0 : 130.0;
         }
      }
   }

   protected double resolveLayersGridGap(EditorSession var1) {
      Map var2 = this.findLayersGridBlockMap(var1);
      return var2 != null ? this.readMapPathDouble(var2, "gap", this.readMapPathDouble(var2, "spacing", -51.0)) : -51.0;
   }

   protected Double resolveLayersGridElementHeight(EditorSession var1) {
      Map var2 = this.findLayersGridBlockMap(var1);
      if (var2 == null) {
         return null;
      } else {
         double var3 = this.readMapPathDouble(
            var2,
            "element_h",
            this.readMapPathDouble(
               var2, "elementHeight", this.readMapPathDouble(var2, "element_height", this.readMapPathDouble(var2, "element-height", Double.NaN))
            )
         );
         return !Double.isFinite(var3) ? null : Math.max(0.0, var3);
      }
   }

   protected Map<String, Object> findLayersGridBlockMap(EditorSession var1) {
      if (var1 != null && var1.shellBlocks != null && !var1.shellBlocks.isEmpty()) {
         ConfigurationSection var2 = this.findShellBlockSection(var1, "layers");
         String var3 = this.firstNonBlank(new String[]{var2 == null ? null : var2.getString("__editor_target_path")});
         if (var3.isBlank()) {
            return null;
         } else {
            for (Map var5 : var1.shellBlocks) {
               if (var5 != null && !var5.isEmpty()) {
                  String var6 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var5, "type")), "block"}).toLowerCase(Locale.ROOT);
                  if ("grid_block".equals(var6)) {
                     String var7 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var5, "__editor_target_path"))});
                     if (this.belongsToSidebarPanel(var3, var7)) {
                        return var5;
                     }
                  }
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   protected boolean isInsideRect(EditorRect var1, double var2, double var4) {
      return var1 == null ? false : var2 >= var1.x && var2 <= var1.maxX() && var4 >= var1.y && var4 <= var1.maxY();
   }

   protected List<LayersPanelManager.LayerTreeRow> collectLayerTreeRows(EditorSession var1) {
      List var2 = this.collectLayerEntries(var1);
      if (var2.isEmpty()) {
         return Collections.emptyList();
      } else {
         LinkedHashMap<String, LayersPanelManager.LayerEntry> var3 = new LinkedHashMap<>();

         for (Object var5_raw : var2) {
            LayersPanelManager.LayerEntry var5 = (LayersPanelManager.LayerEntry)var5_raw;
            var5.children.clear();
            var3.put(var5.targetId, var5);
         }

         ArrayList var9 = new ArrayList();

         for (LayersPanelManager.LayerEntry var6 : var3.values()) {
            String var7 = this.resolveLayerParentTargetId(var6.targetId, var3);
            if (var7 == null) {
               var9.add(var6);
            } else {
               LayersPanelManager.LayerEntry var8 = (LayersPanelManager.LayerEntry)var3.get(var7);
               if (var8 != null && var8 != var6) {
                  var8.children.add(var6);
               } else {
                  var9.add(var6);
               }
            }
         }

         this.sortLayerTreeEntries(var9, this.layerEntryComparator());
         var1.collapsedLayerTargetIds.retainAll(var3.keySet());
         ArrayList var11 = new ArrayList();

         for (int var12 = 0; var12 < var9.size(); var12++) {
            LayersPanelManager.LayerEntry var13 = (LayersPanelManager.LayerEntry)var9.get(var12);
            boolean var14 = var12 < var9.size() - 1;
            this.appendLayerTreeRows(var13, 0, var14, var1, var11, Collections.emptyList());
         }

         return var11;
      }
   }

   protected void appendLayerTreeRows(
      LayersPanelManager.LayerEntry var1, int var2, boolean var3, EditorSession var4, List<LayersPanelManager.LayerTreeRow> var5, List<Integer> var6
   ) {
      if (var1 != null && var4 != null && var5 != null && var6 != null) {
         boolean var7 = !var1.children.isEmpty();
         boolean var8 = var7 && !var4.collapsedLayerTargetIds.contains(var1.targetId);
         boolean var9 = var2 > 0;
         ArrayList var10 = new ArrayList(var6);
         if (var3 && var2 > 0) {
            var10.add(var2);
         }

         var5.add(new LayersPanelManager.LayerTreeRow(var1, var7, var8, var9, false, var3, var9, this.toLayerTreeDepthValue(var2, var7), var10, false));
         if (var8) {
            for (int var11 = 0; var11 < var1.children.size(); var11++) {
               boolean var12 = var11 < var1.children.size() - 1;
               this.appendLayerTreeRows(var1.children.get(var11), var2 + 1, var12, var4, var5, var10);
            }
         }
      }
   }

   protected String resolveLayerParentTargetId(String var1, Map<String, LayersPanelManager.LayerEntry> var2) {
      if (var1 != null && !var1.isBlank() && var2 != null && !var2.isEmpty()) {
         String var3 = var1;

         while (true) {
            int var4 = var3.lastIndexOf(".children.");
            if (var4 < 0) {
               return null;
            }

            String var5 = var3.substring(0, var4);
            if (var5.isBlank()) {
               return null;
            }

            if (var2.containsKey(var5)) {
               return var5;
            }

            var3 = var5;
         }
      } else {
         return null;
      }
   }

   protected void sortLayerTreeEntries(List<LayersPanelManager.LayerEntry> var1, Comparator<LayersPanelManager.LayerEntry> var2) {
      if (var1 != null && !var1.isEmpty() && var2 != null) {
         var1.sort(var2);

         for (Object var4_raw : var1) {
            LayersPanelManager.LayerEntry var4 = (LayersPanelManager.LayerEntry)var4_raw;
            this.sortLayerTreeEntries(var4.children, var2);
         }
      }
   }

   protected Comparator<LayersPanelManager.LayerEntry> layerEntryComparator() {
      return (var0, var1) -> {
         int var2 = Double.compare(var1.layer, var0.layer);
         if (var2 != 0) {
            return var2;
         } else {
            int var3 = var0.displayName.compareToIgnoreCase(var1.displayName);
            return var3 != 0 ? var3 : var0.targetId.compareToIgnoreCase(var1.targetId);
         }
      };
   }

   protected int toLayerTreeDepthValue(int var1, boolean var2) {
      if (var1 <= 0) {
         return 1;
      } else {
         int var3 = var2 ? var1 + 1 : var1;
         return Math.max(1, var3);
      }
   }

   protected List<LayersPanelManager.LayerEntry> collectLayerEntries(EditorSession var1) {
      ArrayList<LayersPanelManager.LayerEntry> var2 = new ArrayList<>();
      if (var1 != null && var1.renderBlocks != null && !var1.renderBlocks.isEmpty()) {
         HashMap var3 = new HashMap();
         HashMap var4 = new HashMap();
         int var5 = 0;

         for (Map var7 : (List<Map>)(List<?>)var1.renderBlocks) {
            var5++;
            if (var7 != null && !var7.isEmpty()) {
               String var8 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var7, "type")), "block"}).toLowerCase(Locale.ROOT);
               if (this.isRenderableBlockType(var8)) {
                  String var9 = this.resolveElementId(var7, var5, var4);
                  String var10 = this.runtimeElementId(var1, var9);
                  String var11 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var7, "__editor_target_id")), var10});
                  if (var11 != null && !var11.isBlank()) {
                     String var12 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var7, "__editor_target_path"))});
                     String var13 = this.readTargetCustomName(var1, var12);
                     double var14 = this.readMapPathDouble(var7, "layer", this.readMapPathDouble(var7, "size.depth", 0.0));
                     LayersPanelManager.LayerEntry var16 = (LayersPanelManager.LayerEntry)var3.get(var11);
                     if (var16 != null) {
                        if (var14 > var16.layer) {
                           var16.layer = var14;
                        }
                     } else {
                        String var17 = this.toSidebarTypeLabel(var8);
                        if ("text".equals(var8)) {
                           String var18 = var13.isBlank() ? this.resolveTextLayerLabel(var7) : var13;
                           var3.put(var11, new LayersPanelManager.LayerEntry(var11, var12, var17, var14, var18, false));
                        } else {
                           String var23 = var13.isBlank() ? this.resolveDefaultLayerDisplayName(var7, var17, var9, var11, var12) : var13;
                           var3.put(var11, new LayersPanelManager.LayerEntry(var11, var12, var17, var14, var23, false));
                        }
                     }
                  }
               }
            }
         }

         var2.addAll(var3.values());
         var2.sort((LayersPanelManager.LayerEntry var0, LayersPanelManager.LayerEntry var1x) -> {
            int var2x = Double.compare(var1x.layer, var0.layer);
            return var2x != 0 ? var2x : var0.typeLabel.compareToIgnoreCase(var1x.typeLabel);
         });
         HashMap var19 = new HashMap();

         for (Object var21_raw : var2) {
            LayersPanelManager.LayerEntry var21 = (LayersPanelManager.LayerEntry)var21_raw;
            if (var21.numbered) {
               int var22 = ((Number)var19.getOrDefault(var21.typeLabel, 0)).intValue() + 1;
               var19.put(var21.typeLabel, var22);
               var21.displayName = var21.typeLabel + " #" + var22;
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   protected String resolveDefaultLayerDisplayName(Map<String, Object> var1, String var2, String var3, String var4, String var5) {
      String var6 = this.firstNonBlank(
            new String[]{this.stringValue(this.readMapPathValue(var1, "id")), this.stringValue(this.readMapPathValue(var1, "params.id"))}
         )
         .trim();
      if (!var6.isBlank()) {
         return var6;
      } else {
         String var7 = this.sanitizeGeneratedRuntimeId(this.firstNonBlank(new String[]{var5}));
         return !var7.isBlank() ? this.firstNonBlank(new String[]{var2, "Block"}) + " " + var7 : this.firstNonBlank(new String[]{var3, var4, var2, "Block"});
      }
   }

   protected String resolveTextLayerLabel(ConfigurationSection var1) {
      if (var1 == null) {
         return "Text";
      } else {
         String var2 = this.firstNonBlank(new String[]{var1.getString("text"), var1.getString("unicode"), ""});
         String var3 = var2.replace('\n', ' ')
            .replace('\r', ' ')
            .replaceAll("<[^>]*>", "")
            .replaceAll("§[0-9A-FK-ORa-fk-or]", "")
            .replaceAll("\\s+", " ")
            .trim();
         if (var3.isBlank()) {
            return "Text";
         } else {
            return var3.length() > 26 ? var3.substring(0, 26) + "..." : var3;
         }
      }
   }

   protected String resolveTextLayerLabel(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         String var2 = this.firstNonBlank(
            new String[]{this.stringValue(this.readMapPathValue(var1, "text")), this.stringValue(this.readMapPathValue(var1, "unicode")), ""}
         );
         String var3 = var2.replace('\n', ' ')
            .replace('\r', ' ')
            .replaceAll("<[^>]*>", "")
            .replaceAll("§[0-9A-FK-ORa-fk-or]", "")
            .replaceAll("\\s+", " ")
            .trim();
         if (var3.isBlank()) {
            return "Text";
         } else {
            return var3.length() > 26 ? var3.substring(0, 26) + "..." : var3;
         }
      } else {
         return "Text";
      }
   }

   protected void toggleLayerVisibility(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         this.recordEditorMutation(var2);
         LinkedHashSet var4 = this.resolveVisibilityCascadeTargetIds(var2, var3);
         if (var2.hiddenLayerTargetIds.contains(var3)) {
            var2.hiddenLayerTargetIds.removeAll(var4);
         } else {
            var2.hiddenLayerTargetIds.addAll(var4);
         }

         if (var4.contains(var2.hoveredElementId)) {
            this.updateHoveredHighlight(var1, var2, var2.hoveredElementId, null);
            var2.hoveredElementId = null;
         }

         this.normalizeSelectionState(var2);
         this.rerenderEditableSelection(var1, var2);
         this.refreshSelectionOutlineColorOnly(var1, var2);
         this.updateEditorPropertiesSidebar(var1, var2);
         this.renderLayersPanel(var1, var2);
      }
   }

   protected LinkedHashSet<String> resolveVisibilityCascadeTargetIds(EditorSession var1, String var2) {
      LinkedHashSet var3 = new LinkedHashSet();
      if (var2 != null && !var2.isBlank()) {
         var3.add(var2);
         if (var1 == null) {
            return var3;
         } else {
            String var4 = var2 + ".children.";

            for (LayersPanelManager.LayerEntry var6 : this.collectLayerEntries(var1)) {
               if (var6 != null && var6.targetId != null && !var6.targetId.isBlank() && (var6.targetId.equals(var2) || var6.targetId.startsWith(var4))) {
                  var3.add(var6.targetId);
               }
            }

            return var3;
         }
      } else {
         return var3;
      }
   }

   protected LinkedHashSet<String> resolveDescendantLayerTargetIds(EditorSession var1, String var2) {
      LinkedHashSet var3 = new LinkedHashSet();
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var4 = var2 + ".children.";
         String var5 = this.resolveAnimationTimelineHierarchyTargetPath(var1, var2);
         String var6 = var5.isBlank() ? "" : var5 + ".children.";

         for (LayersPanelManager.LayerEntry var8 : this.collectLayerEntries(var1)) {
            if (var8 != null
               && var8.targetId != null
               && !var8.targetId.isBlank()
               && (var8.targetId.startsWith(var4) || !var6.isBlank() && var8.targetPath != null && var8.targetPath.startsWith(var6))) {
               var3.add(var8.targetId);
            }
         }

         if (var1.renderBlocks != null) {
            for (Map var13 : var1.renderBlocks) {
               if (var13 != null && !var13.isEmpty()) {
                  String var9 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var13, "__editor_target_id"))});
                  String var10 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var13, "__editor_target_path"))});
                  if (!var9.isBlank() && (var9.startsWith(var4) || !var6.isBlank() && !var10.isBlank() && var10.startsWith(var6))) {
                     var3.add(var9);
                  }
               }
            }
         }

         if (var1.elements != null) {
            for (Object var14_raw : var1.elements) {
               HoverElement var14 = (HoverElement)var14_raw;
               String var15 = this.firstNonBlank(new String[]{var14 == null ? null : var14.targetId, var14 == null ? null : var14.id});
               String var16 = this.firstNonBlank(new String[]{var14 == null ? null : var14.targetPath});
               if (!var15.isBlank() && (var15.startsWith(var4) || !var6.isBlank() && !var16.isBlank() && var16.startsWith(var6))) {
                  var3.add(var15);
               }
            }
         }

         return var3;
      } else {
         return var3;
      }
   }

   protected LinkedHashSet<String> resolveAnimationPreviewHierarchyTargetIds(EditorSession var1, String var2) {
      LinkedHashSet var3 = new LinkedHashSet();
      String var4 = this.firstNonBlank(new String[]{var2});
      if (var4.isBlank()) {
         return var3;
      } else {
         var3.add(var4);
         var3.addAll(this.resolveDescendantLayerTargetIds(var1, var4));
         return var3;
      }
   }

   protected void toggleLayerLock(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         this.recordEditorMutation(var2);
         boolean var4;
         if (var2.lockedLayerTargetIds.contains(var3)) {
            var2.lockedLayerTargetIds.remove(var3);
            var4 = false;
         } else {
            var2.lockedLayerTargetIds.add(var3);
            var4 = true;
         }

         if (var4 && var3.equals(var2.hoveredElementId)) {
            this.updateHoveredHighlight(var1, var2, var2.hoveredElementId, null);
            var2.hoveredElementId = null;
         }

         this.refreshSelectionOutlineColorOnly(var1, var2);
         this.updateEditorPropertiesSidebar(var1, var2);
         this.renderLayersPanel(var1, var2);
      }
   }

   protected void toggleSidebarSelectionVisibility(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         List var3 = this.getSelectedTargetIds(var2);
         if (!var3.isEmpty()) {
            this.recordEditorMutation(var2);
            boolean var4 = !this.isTargetVisible(var2, (String)var3.get(0));
            LinkedHashSet var5 = new LinkedHashSet();

            for (Object var7_raw : var3) {
               String var7 = var7_raw != null ? var7_raw.toString() : null;
               LinkedHashSet var8 = this.resolveVisibilityCascadeTargetIds(var2, var7);
               var5.addAll(var8);
               if (var4) {
                  var2.hiddenLayerTargetIds.removeAll(var8);
               } else {
                  var2.hiddenLayerTargetIds.addAll(var8);
               }
            }

            if (var5.contains(var2.hoveredElementId)) {
               this.updateHoveredHighlight(var1, var2, var2.hoveredElementId, null);
               var2.hoveredElementId = null;
            }

            this.normalizeSelectionState(var2);
            this.rerenderEditableSelection(var1, var2);
            this.refreshSelectionOutlineColorOnly(var1, var2);
            this.updateEditorPropertiesSidebar(var1, var2);
            this.renderLayersPanel(var1, var2);
         }
      }
   }

   protected void toggleSidebarSelectionLocked(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         List var3 = this.getSelectedTargetIds(var2);
         if (!var3.isEmpty()) {
            this.recordEditorMutation(var2);
            boolean var4 = !this.isTargetLocked(var2, (String)var3.get(0));

            for (Object var6_raw : var3) {
               String var6 = var6_raw != null ? var6_raw.toString() : null;
               if (var4) {
                  var2.lockedLayerTargetIds.add(var6);
               } else {
                  var2.lockedLayerTargetIds.remove(var6);
               }
            }

            if (var4 && var3.contains(var2.hoveredElementId)) {
               this.updateHoveredHighlight(var1, var2, var2.hoveredElementId, null);
               var2.hoveredElementId = null;
            }

            this.refreshSelectionOutlineColorOnly(var1, var2);
            this.updateEditorPropertiesSidebar(var1, var2);
            this.renderLayersPanel(var1, var2);
         }
      }
   }

   protected void cycleSidebarSelectionAnchor(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         List var3 = this.getSelectedTargetIds(var2);
         if (var3.isEmpty()) {
            this.updateEditorPropertiesSidebar(var1, var2);
         } else {
            TextAlignment var4 = null;
            boolean var5 = false;
            boolean var6 = false;

            for (Object var8_raw : var3) {
               String var8 = var8_raw != null ? var8_raw.toString() : null;
               Map var9 = this.resolveRawTargetByTargetId(var2, var8);
               if (var9 != null && !var9.isEmpty()) {
                  var6 = true;
                  TextAlignment var10 = this.readHudAlignment(var9);
                  if (var4 == null) {
                     var4 = var10;
                  } else if (var4 != var10) {
                     var5 = true;
                  }
               }
            }

            if (!var6) {
               this.updateEditorPropertiesSidebar(var1, var2);
            } else {
               TextAlignment var14;
               if (!var5 && var4 != null) {
                  var14 = switch (var4) {
                     case LEFT -> TextAlignment.CENTER;
                     case CENTER -> TextAlignment.RIGHT;
                     case RIGHT -> TextAlignment.LEFT;
                     default -> throw new MatchException(null, null);
                  };
               } else {
                  var14 = TextAlignment.CENTER;
               }
               String var15 = switch (var14) {
                  case LEFT -> "left";
                  case RIGHT -> "right";
                  default -> "center";
               };
               boolean var16 = false;
               boolean var17 = false;

               for (Object var12_raw : var3) {
                  String var12 = var12_raw != null ? var12_raw.toString() : null;
                  Map var13 = this.resolveRawTargetByTargetId(var2, var12);
                  if (var13 != null && !var13.isEmpty() && this.readHudAlignment(var13) != var14) {
                     if (!var17) {
                        this.recordEditorMutation(var2);
                        var17 = true;
                     }

                     this.setMapPathValue(var13, this.resolveTargetHudAlignmentWritePath(var13), var15);
                     var16 = true;
                  }
               }

               if (!var16) {
                  this.updateEditorPropertiesSidebar(var1, var2);
               } else {
                  this.rerenderEditableContent(var1, var2);
                  this.renderLayersPanel(var1, var2);
               }
            }
         }
      }
   }

   protected TextAlignment readTargetHudAlignment(EditorSession var1, String var2) {
      Map var3 = this.resolveRawTargetByTargetId(var1, var2);
      return var3 != null && !var3.isEmpty() ? this.readHudAlignment(var3) : TextAlignment.CENTER;
   }

   protected String formatSidebarHudAlignment(TextAlignment var1) {
      TextAlignment var2 = var1 == null ? TextAlignment.CENTER : var1;

      return switch (var2) {
         case LEFT -> "Left";
         case RIGHT -> "Right";
         default -> "Center";
      };
   }

   protected String resolveTargetHudAlignmentWritePath(Map<String, Object> var1) {
      if (var1 == null || var1.isEmpty()) {
         return "anchor";
      } else if (this.hasMapPath(var1, "anchor")) {
         return "anchor";
      } else if (this.hasMapPath(var1, "hud.anchor")) {
         return "hud.anchor";
      } else if (this.hasMapPath(var1, "params.anchor")) {
         return "params.anchor";
      } else if (this.hasMapPath(var1, "params.hud.anchor")) {
         return "params.hud.anchor";
      } else if (this.hasMapPath(var1, "aligned")) {
         return "aligned";
      } else if (this.hasMapPath(var1, "position.align")) {
         return "position.align";
      } else if (this.hasMapPath(var1, "hudAligned")) {
         return "hudAligned";
      } else if (this.hasMapPath(var1, "hud.aligned")) {
         return "hud.aligned";
      } else if (this.hasMapPath(var1, "params.aligned")) {
         return "params.aligned";
      } else if (this.hasMapPath(var1, "params.hudAligned")) {
         return "params.hudAligned";
      } else if (this.hasMapPath(var1, "params.hud.aligned")) {
         return "params.hud.aligned";
      } else {
         return var1.containsKey("params") ? "params.anchor" : "anchor";
      }
   }

   protected boolean isTargetVisible(EditorSession var1, String var2) {
      if (var1 == null || var2 == null || var2.isBlank()) {
         return false;
      } else if (var1.hiddenLayerTargetIds.contains(var2)) {
         return false;
      } else {
         String var3 = var2;

         do {
            int var4 = var3.lastIndexOf(".children.");
            if (var4 < 0) {
               return true;
            }

            var3 = var3.substring(0, var4);
            if (var3.isBlank()) {
               return true;
            }
         } while (!var1.hiddenLayerTargetIds.contains(var3));

         return false;
      }
   }

   protected boolean isTargetLocked(EditorSession var1, String var2) {
      return var1 != null && var2 != null && !var2.isBlank() ? var1.lockedLayerTargetIds.contains(var2) : false;
   }

   protected boolean isTargetInteractable(EditorSession var1, String var2) {
      return this.isTargetVisible(var1, var2) && !this.isTargetLocked(var1, var2);
   }

   protected void appendSelectionTarget(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         if (var1.selectedElementId == null || var1.selectedElementId.isBlank()) {
            var1.selectedElementId = var2;
            var1.additionalSelectedElementIds.clear();
         } else if (!var2.equals(var1.selectedElementId) && !var1.additionalSelectedElementIds.contains(var2)) {
            var1.additionalSelectedElementIds.add(var2);
         }
      }
   }

   protected void toggleLayerSelectionTarget(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         if (this.isTargetInteractable(var1, var2)) {
            if (var2.equals(var1.selectedElementId)) {
               if (var1.additionalSelectedElementIds.isEmpty()) {
                  var1.selectedElementId = null;
               } else {
                  String var3 = var1.additionalSelectedElementIds.iterator().next();
                  var1.additionalSelectedElementIds.remove(var3);
                  var1.selectedElementId = var3;
               }
            } else if (!var1.additionalSelectedElementIds.remove(var2)) {
               this.appendSelectionTarget(var1, var2);
            }
         }
      }
   }

   protected boolean hasLayerEntryTargetId(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         for (LayersPanelManager.LayerEntry var4 : this.collectLayerEntries(var1)) {
            if (var4 != null && var2.equals(var4.targetId)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   protected String layerRuntimeLogicalId(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1});
      return var2.startsWith("layers_runtime_") ? var2 : "layers_runtime_" + var2;
   }

   protected String layerRuntimeRowBaseId(int var1) {
      return "layers_runtime_layer_row_" + Math.max(0, var1);
   }

   protected String layerRuntimeRowId(int var1) {
      return this.layerRuntimeRowBaseId(var1) + "_row";
   }

   protected String layerRuntimeRowNameId(int var1) {
      return this.layerRuntimeRowBaseId(var1) + "_name";
   }

   protected String layerRuntimeRowDropdownHitboxId(int var1) {
      return this.layerRuntimeRowBaseId(var1) + "_dropdownhitbox";
   }

   protected boolean shouldSkipLayerStaticMove(Player var1, EditorSession var2, String var3, EditorRect var4, boolean var5) {
      if (var5 && var1 != null && var2 != null && var3 != null && !var3.isBlank() && var4 != null) {
         String var6 = this.firstNonBlank(new String[]{var3}).toLowerCase(Locale.ROOT);
         if (this.isLayersReorderActive(var2) && var6.endsWith("_divider")) {
            return false;
         } else if (!this.isLayerStaticRuntimeId(var3)) {
            return false;
         } else {
            EditorRect var7 = var2.shellRuntimeRects.get(var3);
            return var7 != null && this.sameRect(var7, var4) ? this.hudService.getHud(var1, "editor_shell_" + var3) != null : false;
         }
      } else {
         return false;
      }
   }

   protected boolean isLayerStaticRuntimeId(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).toLowerCase(Locale.ROOT);
      return var2.endsWith("_box1") || var2.endsWith("_box2") || var2.endsWith("_opacity") || var2.endsWith("_opacityhitbox") || var2.endsWith("_hitbox");
   }

   protected boolean trySwapLayerTextWithoutMove(
      Player var1,
      EditorSession var2,
      String var3,
      EditorRect var4,
      boolean var5,
      TextAlignment var6,
      int var7,
      String var8,
      int var9,
      double var10,
      boolean var12,
      boolean var13
   ) {
      if (!var5 || var1 == null || var2 == null || var3 == null || var3.isBlank() || var4 == null) {
         return false;
      } else if (!this.isLayerSwappableTextRuntimeId(var3)) {
         return false;
      } else {
         EditorRect var14 = var2.shellRuntimeRects.get(var3);
         if (var14 != null && this.sameRect(var14, var4)) {
            String var15 = "editor_shell_" + var3;
            Entity var16 = this.hudService.getHud(var1, var15);
            if (var16 == null) {
               return false;
            } else {
               this.hudService.setTextAlignment(var16, var6);
               this.hudService.setTextWrap(var16, var7);
               String var17 = this.hudService.getHudText(var16, null);
               if (!this.equalsNullable(var17, var8)) {
                  this.hudService.setHudText(var16, var8, null, false);
               }

               if (this.hudService.getHudOpacity(var16) != var9) {
                  this.hudService.setOpacity(var16, var9);
               }

               this.hudService.setHudNoTransition(var16);
               this.applyElementTransform(var16, var10, var12, var13);
               this.clearOutlineHud(var1, var15);
               return true;
            }
         } else {
            return false;
         }
      }
   }

   protected boolean isLayerSwappableTextRuntimeId(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).toLowerCase(Locale.ROOT);
      return !var2.startsWith("layers_runtime_layer_slot_".toLowerCase(Locale.ROOT))
         ? false
         : var2.endsWith("_name") || var2.endsWith("_dropdown_icon") || var2.endsWith("_box1") || var2.endsWith("_box2") || var2.endsWith("_opacity");
   }

   protected void setLayerTextInterpolationInstant(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = var2.toLowerCase(Locale.ROOT);
         if (var3.startsWith("layers_runtime_layer_slot_".toLowerCase(Locale.ROOT))) {
            Entity var4 = this.hudService.getHud(var1, "editor_shell_" + var2);
            if (var4 != null) {
               this.hudService.setHudNoTransition(var4);
            }
         }
      }
   }

   protected String layerSlotBaseId(int var1) {
      return "layers_runtime_layer_slot_" + var1;
   }

   protected String layerSlotId(int var1) {
      return this.layerSlotBaseId(var1) + "_row";
   }

   protected String layerOpacityId(int var1) {
      return this.layerSlotBaseId(var1) + "_opacity";
   }

   protected String layerOpacityHitboxId(int var1) {
      return this.layerSlotBaseId(var1) + "_opacityhitbox";
   }

   protected String layerNameId(int var1) {
      return this.layerSlotBaseId(var1) + "_name";
   }

   protected String layerDropdownHitboxId(int var1) {
      return this.layerSlotBaseId(var1) + "_dropdownhitbox";
   }

   protected static final class LayerEntry {
      protected final String targetId;
      private final String targetPath;
      private final String typeLabel;
      protected String displayName;
      private double layer;
      private final boolean numbered;
      private final List<LayersPanelManager.LayerEntry> children = new ArrayList<>();

      private LayerEntry(String var1, String var2, String var3, double var4) {
         this(var1, var2, var3, var4, var3, true);
      }

      private LayerEntry(String var1, String var2, String var3, double var4, String var6, boolean var7) {
         this.targetId = var1;
         this.targetPath = var2 != null && !var2.isBlank() ? var2 : "";
         this.typeLabel = var3;
         this.displayName = var6 != null && !var6.isBlank() ? var6 : var3;
         this.layer = var4;
         this.numbered = var7;
      }
   }

   protected static final class LayerTreeRow {
      private final LayersPanelManager.LayerEntry entry;
      private final boolean dropdown;
      private final boolean open;
      private final boolean children;
      private final boolean parent;
      private final boolean under;
      private final boolean end;
      private final int depthValue;
      private final List<Integer> extraLines;
      private final boolean dragPlaceholder;

      private LayerTreeRow(
         LayersPanelManager.LayerEntry var1,
         boolean var2,
         boolean var3,
         boolean var4,
         boolean var5,
         boolean var6,
         boolean var7,
         int var8,
         List<Integer> var9,
         boolean var10
      ) {
         this.entry = var1;
         this.dropdown = var2;
         this.open = var3;
         this.children = var4;
         this.parent = var5;
         this.under = var6;
         this.end = var7;
         this.depthValue = var8;
         this.extraLines = var9 == null ? Collections.emptyList() : List.copyOf(var9);
         this.dragPlaceholder = var10;
      }
   }
}
