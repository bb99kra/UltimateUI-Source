package dev.xqedii.ultimateUI.service.gui.editor;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.gui.model.HoverElement;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.GuiService;
import dev.xqedii.ultimateUI.service.gui.model.EditorPropertyField;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSavePopupField;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.gui.model.EditorSidebarTab;
import dev.xqedii.ultimateUI.service.gui.model.EditorTool;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.service.resourcepack.UiImageAtlasService;
import dev.xqedii.ultimateUI.util.PlatformCompat;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public abstract class GuiServiceEditorSupportB extends GuiServiceEditorSupportA {
   protected abstract boolean isRuntimeHudStackPageMatch(Map<String, Object> var1, String var2);

   protected abstract void updateCursorPageDropdownHover(Player var1, EditorSession var2, double var3, double var5);

   protected abstract void updateCursorLayersDropdownHover(Player var1, EditorSession var2, double var3, double var5);

   protected abstract boolean handleActivePagesCursorMove(Player var1, EditorSession var2, double var3, double var5);

   protected abstract List<String> resolveOpenEditorPagesForPlayer(UUID var1, String var2);

   protected abstract List<Map<String, Object>> buildEditorShellBlocksWithActivePages(
      List<Map<String, Object>> var1, Map<String, YamlConfiguration> var2, List<String> var3, int var4, String var5
   );

   protected abstract boolean isActivePagesShellLogicalId(String var1);

   protected abstract void clearActivePagesRuntimeSlots(Player var1, Set<String> var2);

   protected abstract void syncActivePagesRenderedSlotStates(EditorSession var1);

   protected abstract void applyRuntimeHudNoTransition(Player var1, String var2);

   protected abstract void applyRuntimeHudTransitionTicks(Player var1, String var2, int var3, int var4);

   protected abstract void resetActivePagesDragState(EditorSession var1);

   protected abstract void clearActivePagesDragPreview(Player var1);

   protected abstract String resolveNextUntitledPageKey(Player var1, EditorSession var2);

   protected abstract String formatUntitledPageDisplayName(String var1);

   protected abstract void snapshotCurrentEditorPage(UUID var1, EditorSession var2);

   protected abstract void resetEditorTransientPanelsForPageSwitch(Player var1, EditorSession var2);

   protected abstract void rememberEditorWorkingPage(UUID var1, String var2, List<Map<String, Object>> var3);

   @Override
   protected abstract void rememberOpenedEditorPage(UUID var1, String var2);

   protected abstract int resolveInitialActivePagesScrollOffset(List<String> var1, String var2);

   protected abstract void rerenderActivePagesShell(Player var1, EditorSession var2);

   protected abstract void rerenderActivePagesShell(Player var1, EditorSession var2, boolean var3);

   protected abstract void rerenderActivePagesShell(Player var1, EditorSession var2, boolean var3, boolean var4);

   protected abstract void rerenderEditorShellForPageSwitch(Player var1, EditorSession var2);

   protected abstract void applyEditorDisplayPreferences(Player var1);

   protected abstract void applyEditorDisplayPreferences(Player var1, EditorSession var2);

   protected abstract void savePlayerColorPrefs(Player var1, EditorSession var2);

   protected abstract ItemStack resolveInventoryPickSource(ItemStack... var1);

   protected abstract Map<String, Integer> serializeItemSelectionEnchantments(ItemStack var1);

   protected GuiServiceEditorSupportB(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   @Override
   public boolean clickHoveredElement(Player var1, GuiService.ClickType var2) {
      if (var1 == null) {
         return false;
      } else {
         EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
         if (this.isEditorImageFilePopupVisible(var3)) {
            if (var2 == GuiService.ClickType.LEFT) {
               long var4 = (long)Bukkit.getCurrentTick();
               boolean var6 = var4 - var3.lastLeftClickTick <= 2L;
               var3.lastLeftClickTick = var4;
               if (!var6) {
                  double var7 = var3.cursorX + var3.hitboxOffsetX;
                  double var9 = var3.cursorY + var3.hitboxOffsetY;
                  this.handleEditorMenuFilePopupClick(var1, var3, var7, var9);
               }

               return true;
            } else {
               return var2 == GuiService.ClickType.RIGHT;
            }
         } else {
            return super.clickHoveredElement(var1, var2);
         }
      }
   }

   @Override
   protected boolean isAnyEditorPopupVisible(EditorSession var1) {
      return super.isAnyEditorPopupVisible(var1) || this.isEditorImageFilePopupVisible(var1);
   }

   protected boolean isEditorImageFilePopupMode(EditorSession var1) {
      return var1 != null && var1.editMode && var1.previewMode && var1.menuFilePopupImageMode;
   }

   protected boolean isEditorImageFilePopupVisible(EditorSession var1) {
      return this.isEditorImageFilePopupMode(var1) && var1.menuFilePopupVisible;
   }

   protected boolean isEditorMenuFilePopupRenderContext(EditorSession var1) {
      if (var1 == null) {
         return false;
      } else {
         return this.isEditorImageFilePopupMode(var1) ? true : !var1.editMode && this.isEditorMenuSession(var1);
      }
   }

   protected int resolveEditorMenuFilePopupMaxVisible(EditorSession var1) {
      return this.isEditorImageFilePopupMode(var1) ? 8 : 6;
   }

   protected int resolveEditorMenuFilePopupElementHeight(EditorSession var1) {
      return this.isEditorImageFilePopupMode(var1) ? 75 : 110;
   }

   protected int resolveEditorMenuFilePopupElementGap(EditorSession var1) {
      return this.isEditorImageFilePopupMode(var1) ? 20 : 20;
   }

   protected void startRuntimeOpenAnimations(Player var1, EditorSession var2, boolean var3) {
      this.startRuntimeOpenAnimations(var1, var2, var3, var2 == null ? "" : var2.pageName);
   }

   protected void startRuntimeOpenAnimations(Player var1, EditorSession var2, boolean var3, String var4) {
      if (var1 != null && var2 != null && !var2.editMode) {
         String var5 = this.normalizePageKey(var4, "");
         LinkedHashSet var6 = this.resolveAnimationTimelinePreviewTargetIds(var2, "");
         if (var2.hudTextOnlyMode && !var5.isBlank()) {
            var6 = this.filterRuntimeOpenAnimationTargetsByStackPage(var2, var6, var5);
         } else if (var2.hudTextOnlyMode && var5.isBlank()) {
            var6 = this.filterUnscopedRuntimeOpenAnimationTargets(var6);
         }

         String var7 = var2.hudTextOnlyMode ? var5 : "";
         LinkedHashSet var8 = this.scopeRuntimeOpenAnimationTargets(var6, var7);
         if (var8.isEmpty()) {
            if (var3) {
               this.closeRuntimeUiAfterAutoplay(var1, var2, var5);
            }
         } else {
            int var9 = this.resolveRuntimeOpenAnimationMaxTick(var2, var8);
            if (var9 < 0) {
               if (var3) {
                  this.closeRuntimeUiAfterAutoplay(var1, var2, var5);
               }
            } else {
               String var10 = this.normalizeRuntimeAnimationPageKey(var7);
               long var11 = this.reserveRuntimeOpenAnimationToken(var2, var10);
               if (this.isRuntimeOpenAnimationScopedPlayback(var8)) {
                  this.clearRuntimeOpenAnimationPreviewOffsets(var1, var2, var8);
               } else {
                  this.clearAnimationTimelinePreviewOffset(var1, var2);
               }

               this.applyRuntimeOpenAnimationTick(var1, var2, var8, 0.0);
               PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
                  if (var1.isOnline()) {
                     EditorSession var7x = this.editorSessions.get(var1.getUniqueId());
                     if (var7x != null && var7x == var2 && !var7x.editMode && this.isRuntimeOpenAnimationTokenActive(var7x, var11, var10)) {
                        this.applyRuntimeOpenAnimationTick(var1, var7x, var8, 0.0);
                     }
                  }
               }, 1L);
               int[] var13 = new int[]{1};
               PlatformCompat.runEntityTimer(this.plugin, var1, 1L, 1L, var11x -> {
                  if (!var1.isOnline()) {
                     var11x.cancel();
                  } else {
                     EditorSession var12 = this.editorSessions.get(var1.getUniqueId());
                     if (var12 != null && var12 == var2 && !var12.editMode && this.isRuntimeOpenAnimationTokenActive(var12, var11, var10)) {
                        if (var13[0] > var9) {
                           var11x.cancel();
                           if (var12.runtimeAnimationLoop) {
                              this.startRuntimeOpenAnimations(var1, var12, var3, var5);
                           } else {
                              if (var3) {
                                 this.closeRuntimeUiAfterAutoplay(var1, var12, var5);
                              }
                           }
                        } else {
                           this.applyRuntimeOpenAnimationTick(var1, var12, var8, (double)var13[0]);
                           var13[0]++;
                        }
                     } else {
                        var11x.cancel();
                     }
                  }
               });
            }
         }
      }
   }

   protected LinkedHashSet<String> filterRuntimeOpenAnimationTargetsByStackPage(EditorSession var1, LinkedHashSet<String> var2, String var3) {
      LinkedHashSet var4 = new LinkedHashSet();
      if (var1 != null && var2 != null && !var2.isEmpty()) {
         String var5 = this.normalizePageKey(var3, "");
         if (var5.isBlank()) {
            var4.addAll(var2);
            return var4;
         } else if (var1.renderBlocks != null && !var1.renderBlocks.isEmpty()) {
            LinkedHashSet var6 = new LinkedHashSet();

            for (Map var8 : var1.renderBlocks) {
               if (var8 != null && !var8.isEmpty() && this.isRuntimeHudStackPageMatch(var8, var5)) {
                  String var9 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var8, "__editor_target_id"))});
                  if (!var9.isBlank()) {
                     var6.add(var9);
                  }
               }
            }

            if (var6.isEmpty()) {
               var4.addAll(var2);
               return var4;
            } else {
               for (Object var14_raw : var2) {
                  String var14 = var14_raw != null ? var14_raw.toString() : null;
                  String var16 = this.firstNonBlank(new String[]{var14});
                  if (!var16.isBlank() && var6.contains(var16)) {
                     var4.add(var16);
                  }
               }

               if (!var4.isEmpty()) {
                  return var4;
               } else {
                  for (Object var15_raw : var2) {
                     String var15 = var15_raw != null ? var15_raw.toString() : null;
                     String var17 = this.firstNonBlank(new String[]{var15});
                     if (!var17.isBlank()) {
                        String var10 = this.composeRuntimeAnimationScopedTargetId(var17, var5);
                        String var11 = this.resolveRawTargetPathByTargetId(var1, var10);
                        if (!var11.isBlank() && this.doesTargetPathMatchRuntimeAnimationScope(var1, var11, var10)) {
                           var4.add(var17);
                        }
                     }
                  }

                  return var4;
               }
            }
         } else {
            var4.addAll(var2);
            return var4;
         }
      } else {
         return var4;
      }
   }

   protected LinkedHashSet<String> filterUnscopedRuntimeOpenAnimationTargets(LinkedHashSet<String> var1) {
      LinkedHashSet var2 = new LinkedHashSet();
      if (var1 != null && !var1.isEmpty()) {
         for (Object var4_raw : var1) {
            String var4 = var4_raw != null ? var4_raw.toString() : null;
            String var5 = this.firstNonBlank(new String[]{var4});
            if (!var5.isBlank() && this.resolveRuntimeAnimationTargetScopePageKey(var5).isBlank()) {
               var2.add(var5);
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   protected long reserveRuntimeOpenAnimationToken(EditorSession var1, String var2) {
      if (var1 == null) {
         return Long.MIN_VALUE;
      } else {
         String var3 = this.normalizeRuntimeAnimationPageKey(var2);
         if (!var3.isBlank()) {
            long var4 = var1.runtimeOpenAnimationTokensByPage.getOrDefault(var3, 0L);
            long var6 = var4 + 1L;
            var1.runtimeOpenAnimationTokensByPage.put(var3, Long.valueOf(var6));
            return var6;
         } else {
            return ++var1.runtimeOpenAnimationToken;
         }
      }
   }

   protected boolean isRuntimeOpenAnimationTokenActive(EditorSession var1, long var2, String var4) {
      if (var1 == null) {
         return false;
      } else {
         String var5 = this.normalizeRuntimeAnimationPageKey(var4);
         if (var5.isBlank()) {
            return var1.runtimeOpenAnimationToken == var2;
         } else {
            Long var6 = var1.runtimeOpenAnimationTokensByPage.get(var5);
            return var6 != null && var6 == var2;
         }
      }
   }

   protected void invalidateRuntimeOpenAnimationToken(EditorSession var1, String var2) {
      if (var1 != null) {
         String var3 = this.normalizeRuntimeAnimationPageKey(var2);
         if (!var3.isBlank()) {
            var1.runtimeOpenAnimationTokensByPage.remove(var3);
         } else {
            var1.runtimeOpenAnimationToken++;
         }
      }
   }

   protected LinkedHashSet<String> scopeRuntimeOpenAnimationTargets(LinkedHashSet<String> var1, String var2) {
      LinkedHashSet var3 = new LinkedHashSet();
      if (var1 != null && !var1.isEmpty()) {
         String var4 = this.normalizeRuntimeAnimationPageKey(var2);

         for (Object var6_raw : var1) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            String var7 = this.firstNonBlank(new String[]{var6});
            if (!var7.isBlank()) {
               if (var4.isBlank()) {
                  var3.add(var7);
               } else {
                  var3.add(this.composeRuntimeAnimationScopedTargetId(var7, var4));
               }
            }
         }

         return var3;
      } else {
         return var3;
      }
   }

   protected boolean isRuntimeOpenAnimationScopedPlayback(Set<String> var1) {
      if (var1 != null && !var1.isEmpty()) {
         for (Object var3_raw : var1) {
            String var3 = var3_raw != null ? var3_raw.toString() : null;
            if (!this.resolveRuntimeAnimationTargetScopePageKey(var3).isBlank()) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   protected String resolveRuntimeOpenAnimationScopePageKey(Set<String> var1) {
      if (var1 != null && !var1.isEmpty()) {
         for (Object var3_raw : var1) {
            String var3 = var3_raw != null ? var3_raw.toString() : null;
            String var4 = this.resolveRuntimeAnimationTargetScopePageKey(var3);
            if (!var4.isBlank()) {
               return var4;
            }
         }

         return "";
      } else {
         return "";
      }
   }

   protected void clearRuntimeOpenAnimationPreviewOffsets(Player var1, EditorSession var2, Set<String> var3) {
      if (var2 != null && var3 != null && !var3.isEmpty()) {
         String var4 = this.resolveRuntimeOpenAnimationScopePageKey(var3);
         if (var4.isBlank()) {
            this.clearAnimationTimelinePreviewOffset(var1, var2);
         } else {
            String var5 = this.firstNonBlank(new String[]{var2.animationPreviewTargetId});
            if (!var5.isBlank() && var4.equals(this.resolveRuntimeAnimationTargetScopePageKey(var5))) {
               this.clearAnimationTimelinePrimaryPreviewOffset(var1, var2);
            }

            LinkedHashSet var6 = new LinkedHashSet(var3);
            var6.addAll(var2.animationPreviewAdditionalBaseBoundsByTarget.keySet());
            var6.addAll(var2.animationPreviewAdditionalAppliedBoundsByTarget.keySet());
            var6.addAll(var2.animationPreviewAdditionalBaseRotationByTarget.keySet());
            var6.addAll(var2.animationPreviewAdditionalAppliedRotationByTarget.keySet());
            var6.addAll(var2.animationPreviewAdditionalBaseOpacityByTarget.keySet());
            var6.addAll(var2.animationPreviewAdditionalAppliedOpacityByTarget.keySet());

            for (Object var8_raw : var6) {
               String var8 = var8_raw != null ? var8_raw.toString() : null;
               String var9 = this.firstNonBlank(new String[]{var8});
               if (!var9.isBlank() && var4.equals(this.resolveRuntimeAnimationTargetScopePageKey(var9))) {
                  this.clearAnimationTimelineAdditionalPreviewOffset(var1, var2, var9);
               }
            }
         }
      }
   }

   protected void clearStaleScopedRuntimeAnimationPreviewOffsets(Player var1, EditorSession var2, String var3, Set<String> var4) {
      if (var2 != null && var3 != null && !var3.isBlank()) {
         LinkedHashSet<String> var5 = new LinkedHashSet<>();
         var5.addAll(var2.animationPreviewAdditionalBaseBoundsByTarget.keySet());
         var5.addAll(var2.animationPreviewAdditionalAppliedBoundsByTarget.keySet());
         var5.addAll(var2.animationPreviewAdditionalBaseRotationByTarget.keySet());
         var5.addAll(var2.animationPreviewAdditionalAppliedRotationByTarget.keySet());
         var5.addAll(var2.animationPreviewAdditionalBaseOpacityByTarget.keySet());
         var5.addAll(var2.animationPreviewAdditionalAppliedOpacityByTarget.keySet());
         var5.removeIf(var2x -> !var3.equals(this.resolveRuntimeAnimationTargetScopePageKey((String)var2x)));
         if (var4 != null && !var4.isEmpty()) {
            var5.removeAll(var4);
         }

         for (Object var7_raw : var5) {
            String var7 = var7_raw != null ? var7_raw.toString() : null;
            this.clearAnimationTimelineAdditionalPreviewOffset(var1, var2, var7);
         }

         String var8 = this.firstNonBlank(new String[]{var2.animationPreviewTargetId});
         if (!var8.isBlank() && var3.equals(this.resolveRuntimeAnimationTargetScopePageKey(var8)) && (var4 == null || !var4.contains(var8))) {
            this.clearAnimationTimelinePrimaryPreviewOffset(var1, var2);
         }
      }
   }

   protected void closeRuntimeUiAfterAutoplay(Player var1) {
      this.closeRuntimeUiAfterAutoplay(var1, null, "");
   }

   protected void closeRuntimeUiAfterAutoplay(Player var1, EditorSession var2, String var3) {
      if (var1 != null) {
         String var4 = this.normalizePageKey(var3, "");
         boolean var5 = var2 != null && var2.hudTextOnlyMode && !var2.editMode && !var4.isBlank();
         if (this.plugin != null) {
            if (var5) {
               this.plugin.closeUiForPlayer(var1, var4);
            } else {
               this.plugin.closeUiForPlayer(var1);
            }
         } else if (var5) {
            this.closeGui(var1, var4);
         } else {
            this.closeGui(var1);
         }
      }
   }

   protected int resolveRuntimeOpenAnimationMaxTick(EditorSession var1, LinkedHashSet<String> var2) {
      if (var1 != null && var2 != null && !var2.isEmpty()) {
         int var3 = -1;

         for (Object var5_raw : var2) {
            String var5 = var5_raw != null ? var5_raw.toString() : null;
            String var6 = this.firstNonBlank(new String[]{var5});
            if (!var6.isBlank()) {
               int var7 = this.resolveRuntimeOpenAnimationDelayTicks(var1, var6);

               for (int var9 : this.readAnimationTimelineTicks(var1, var6, "position")) {
                  var3 = Math.max(var3, var7 + this.clampAnimationTimelineTick(var9));
               }

               for (int var13 : this.readAnimationTimelineTicks(var1, var6, "scale")) {
                  var3 = Math.max(var3, var7 + this.clampAnimationTimelineTick(var13));
               }

               for (int var14 : this.readAnimationTimelineTicks(var1, var6, "rotation")) {
                  var3 = Math.max(var3, var7 + this.clampAnimationTimelineTick(var14));
               }

               for (int var15 : this.readAnimationTimelineTicks(var1, var6, "opacity")) {
                  var3 = Math.max(var3, var7 + this.clampAnimationTimelineTick(var15));
               }
            }
         }

         return var3;
      } else {
         return -1;
      }
   }

   protected void applyRuntimeOpenAnimationTick(Player var1, EditorSession var2, LinkedHashSet<String> var3, double var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isEmpty()) {
         boolean var6 = this.isRuntimeOpenAnimationScopedPlayback(var3);
         String var7 = var6 ? this.resolveRuntimeOpenAnimationScopePageKey(var3) : "";
         String var8 = var2.runtimeAnimationScopePageKey;
         if (var6) {
            var2.runtimeAnimationScopePageKey = var7;
         }

         try {
            String var9 = var6 ? "" : this.firstNonBlank(new String[]{var2.animationPreviewTargetId});
            if (!var6 && (var9.isBlank() || !var3.contains(var9))) {
               for (Object var11_raw : var3) {
                  String var11 = var11_raw != null ? var11_raw.toString() : null;
                  var9 = this.firstNonBlank(new String[]{var11});
                  if (!var9.isBlank()) {
                     break;
                  }
               }
            }

            boolean var30 = false;
            LinkedHashSet var31 = new LinkedHashSet();

            for (Object var13_raw : var3) {
               String var13 = var13_raw != null ? var13_raw.toString() : null;
               String var14 = this.firstNonBlank(new String[]{var13});
               if (!var14.isBlank()) {
                  EditorRect var15 = null;
                  EditorRect var16 = null;
                  if (this.shouldLogAnimationTimelineImageDebug(var2, var14)) {
                     var15 = this.resolveAnimationTimelineStableBaseBounds(var2, var14);
                     var16 = this.resolveAnimationTimelineCurrentBoundsForPreview(var2, var14);
                  }

                  String var17 = this.firstNonBlank(new String[]{this.resolveRuntimeAnimationBaseTargetId(var14), var14});
                  if (!this.isTargetVisible(var2, var17)) {
                     if (!var6 && this.equalsNullable(var14, var9)) {
                        this.clearAnimationTimelinePrimaryPreviewOffset(var1, var2);
                     } else {
                        this.clearAnimationTimelineAdditionalPreviewOffset(var1, var2, var14);
                     }
                  } else {
                     int var18 = this.resolveRuntimeOpenAnimationDelayTicks(var2, var14);
                     double var19 = var4 - (double)var18;
                     if (var19 < 0.0) {
                        if (this.shouldLogAnimationTimelineImageDebug(var2, var14)) {
                           this.logAnimationTimelineImageDebug(var2, "runtime-open-delay-wait", var14, var4, Double.valueOf(var19), var15, var16, null, -1, -1);
                        }

                        if (!var6 && this.equalsNullable(var14, var9)) {
                           this.clearAnimationTimelinePrimaryPreviewOffset(var1, var2);
                        } else {
                           this.clearAnimationTimelineAdditionalPreviewOffset(var1, var2, var14);
                        }
                     } else {
                        double[] var21 = this.resolveAnimationTimelinePreviewState(var2, var14, var19);
                        if (var21 != null && var21.length >= 4) {
                           if (this.shouldLogAnimationTimelineImageDebug(var2, var14)) {
                              this.logAnimationTimelineImageDebug(
                                 var2, "runtime-open-apply-before", var14, var4, Double.valueOf(var19), var15, var16, var21, -1, -1
                              );
                           }

                           if (!var6 && this.equalsNullable(var14, var9)) {
                              this.applyAnimationTimelinePreviewOffset(var1, var2, var14, var21[0], var21[1], var21[2], var21[3]);
                              double var32 = var21.length >= 5 ? var21[4] : this.readTargetRotation(var2, var14);
                              double var33 = var21.length >= 6 ? var21[5] : (double)this.readTargetOpacity(var2, var14);
                              this.applyAnimationTimelinePreviewRotation(var1, var2, var14, var32);
                              this.applyAnimationTimelinePreviewOpacity(var1, var2, var14, var33);
                              var30 = true;
                              if (this.shouldLogAnimationTimelineImageDebug(var2, var14)) {
                                 EditorRect var34 = this.resolveAnimationTimelineCurrentBoundsForPreview(var2, var14);
                                 this.logAnimationTimelineImageDebug(
                                    var2, "runtime-open-apply-after", var14, var4, Double.valueOf(var19), var15, var34, var21, -1, -1
                                 );
                              }
                           } else {
                              this.applyAnimationTimelineAdditionalPreviewOffset(var1, var2, var14, var21[0], var21[1], var21[2], var21[3]);
                              double var22 = var21.length >= 5 ? var21[4] : this.readTargetRotation(var2, var14);
                              double var24 = var21.length >= 6 ? var21[5] : (double)this.readTargetOpacity(var2, var14);
                              this.applyAnimationTimelineAdditionalPreviewRotation(var1, var2, var14, var22);
                              this.applyAnimationTimelineAdditionalPreviewOpacity(var1, var2, var14, var24);
                              var31.add(var14);
                              if (this.shouldLogAnimationTimelineImageDebug(var2, var14)) {
                                 EditorRect var26 = this.resolveAnimationTimelineCurrentBoundsForPreview(var2, var14);
                                 this.logAnimationTimelineImageDebug(
                                    var2, "runtime-open-apply-after", var14, var4, Double.valueOf(var19), var15, var26, var21, -1, -1
                                 );
                              }
                           }
                        } else if (!var6 && this.equalsNullable(var14, var9)) {
                           this.clearAnimationTimelinePrimaryPreviewOffset(var1, var2);
                        } else {
                           this.clearAnimationTimelineAdditionalPreviewOffset(var1, var2, var14);
                        }
                     }
                  }
               }
            }

            if (var6) {
               this.clearStaleScopedRuntimeAnimationPreviewOffsets(var1, var2, var7, var31);
               return;
            }

            if (!var30 && !var9.isBlank()) {
               this.clearAnimationTimelinePrimaryPreviewOffset(var1, var2);
            }

            this.clearStaleAnimationTimelineAdditionalPreviewOffsets(var1, var2, var31);
            this.clearStaleAnimationTimelineAdditionalPreviewRotations(var1, var2, var31);
            this.clearStaleAnimationTimelineAdditionalPreviewOpacities(var1, var2, var31);
         } finally {
            var2.runtimeAnimationScopePageKey = var8;
         }
      }
   }

   @Override
   protected void onCursorMove(Player var1, double var2, double var4) {
      EditorSession var6 = this.editorSessions.get(var1.getUniqueId());
      if (var6 != null && !var6.editMode) {
         var6.cursorX = var2;
         var6.cursorY = var4;
         if (!this.isEditorMenuSession(var6)) {
            double var7 = var2 + var6.hitboxOffsetX;
            double var9 = var4 + var6.hitboxOffsetY;
            HoverElement var11 = this.findHoveredRuntimeElement(var6, var7, var9);
            String var12 = var11 == null ? null : this.firstNonBlank(new String[]{this.targetIdOf(var11), var11.targetId});
            if (!this.equalsNullable(var6.hoveredElementId, var12)) {
               this.updateHoveredHighlight(var1, var6, var6.hoveredElementId, var12);
               var6.hoveredElementId = var12;
            }
         }
      }

      if (var6 != null && !var6.editMode && this.isEditorMenuSession(var6)) {
         var6.cursorX = var2;
         var6.cursorY = var4;
         double var15 = var2 + var6.hitboxOffsetX;
         double var17 = var4 + var6.hitboxOffsetY;
         if (Double.isFinite(var6.lastProcessedHitX)
            && Double.isFinite(var6.lastProcessedHitY)
            && Math.abs(var15 - var6.lastProcessedHitX) < 1.0E-4
            && Math.abs(var17 - var6.lastProcessedHitY) < 1.0E-4) {
            this.refreshCursorVisualState(var1, var6, var15, var17);
         } else {
            var6.lastProcessedHitX = var15;
            var6.lastProcessedHitY = var17;
            this.handleEditorMenuFilePopupCursorMove(var1, var6, var15, var17);
            this.updateEditorMenuHoverIcons(var1, var6, var15, var17);
            this.refreshCursorVisualState(var1, var6, var15, var17);
         }
      } else {
         if (var6 != null && var6.editMode && var6.previewMode) {
            var6.cursorX = var2;
            var6.cursorY = var4;
            double var13 = var2 + var6.hitboxOffsetX;
            double var16 = var4 + var6.hitboxOffsetY;
            boolean var18 = !Double.isFinite(var6.lastProcessedHitX)
               || !Double.isFinite(var6.lastProcessedHitY)
               || Math.abs(var13 - var6.lastProcessedHitX) >= 1.0E-4
               || Math.abs(var16 - var6.lastProcessedHitY) >= 1.0E-4;
            if (this.isEditorImageFilePopupVisible(var6)) {
               if (var18) {
                  var6.lastProcessedHitX = var13;
                  var6.lastProcessedHitY = var16;
                  this.handleEditorMenuFilePopupCursorMove(var1, var6, var13, var16);
                  String var19 = this.resolveEditorMenuFilePopupHoveredHitboxId(var6, var13, var16);
                  this.updateEditorMenuFilePopupActionIcons(var1, var6, var19);
               }

               this.refreshCursorVisualState(var1, var6, var13, var16);
               return;
            }

            if (var18) {
               this.updateCursorPageDropdownHover(var1, var6, var13, var16);
               this.updateCursorLayersDropdownHover(var1, var6, var13, var16);
            }

            if (this.handleActivePagesCursorMove(var1, var6, var13, var16)) {
               this.refreshCursorVisualState(var1, var6, var13, var16);
               return;
            }
         }

         super.onCursorMove(var1, var2, var4);
         EditorSession var14 = this.editorSessions.get(var1.getUniqueId());
         if (var14 != null) {
            double var8 = var2 + var14.hitboxOffsetX;
            double var10 = var4 + var14.hitboxOffsetY;
            this.refreshCursorVisualState(var1, var14, var8, var10);
         }
      }
   }

   protected void refreshCursorVisualState(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         int var7 = this.resolveCursorVisualStateHash(var2);
         String var8;
         if (this.isCursorVisualCacheHit(var2, var3, var5, var7)) {
            var8 = this.cursorUnicodeValue(var2.lastCursorVisualDesiredUnicode, this.cursorUnicodeValue(var2.cursorDefaultUnicode, "\ue67c"));
         } else {
            var8 = this.cursorUnicodeValue(this.resolveCursorVisualUnicode(var2, var3, var5), this.cursorUnicodeValue(var2.cursorDefaultUnicode, "\ue67c"));
            var2.lastCursorVisualHitX = var3;
            var2.lastCursorVisualHitY = var5;
            var2.lastCursorVisualStateHash = var7;
            var2.lastCursorVisualDesiredUnicode = var8;
         }

         if (!this.equalsNullable(var2.cursorRuntimeUnicode, var8)) {
            var2.cursorRuntimeUnicode = var8;
            this.applyCursorVisualUnicode(var1, var2, var8);
         }
      }
   }

   protected boolean isCursorVisualCacheHit(EditorSession var1, double var2, double var4, int var6) {
      return var1 != null
            && Double.isFinite(var1.lastCursorVisualHitX)
            && Double.isFinite(var1.lastCursorVisualHitY)
            && var1.lastCursorVisualDesiredUnicode != null
         ? Math.abs(var2 - var1.lastCursorVisualHitX) < 1.0E-4 && Math.abs(var4 - var1.lastCursorVisualHitY) < 1.0E-4 && var1.lastCursorVisualStateHash == var6
         : false;
   }

   protected int resolveCursorVisualStateHash(EditorSession var1) {
      if (var1 == null) {
         return 0;
      } else {
         int var2 = 17;
         var2 = 31 * var2 + Boolean.hashCode(var1.editMode);
         var2 = 31 * var2 + Boolean.hashCode(var1.previewMode);
         var2 = 31 * var2 + Boolean.hashCode(var1.welcomePopupVisible);
         var2 = 31 * var2 + Boolean.hashCode(var1.savePopupVisible);
         var2 = 31 * var2 + Boolean.hashCode(var1.preferencesPopupVisible);
         var2 = 31 * var2 + Boolean.hashCode(var1.menuFilePopupVisible);
         var2 = 31 * var2 + Boolean.hashCode(var1.menuFilePopupImageMode);
         var2 = 31 * var2 + Boolean.hashCode(var1.menuFilePopupEditAction);
         var2 = 31 * var2 + this.hashString(var1.menuFilePopupSelectedPage);
         var2 = 31 * var2 + var1.menuFilePopupScrollOffset;
         var2 = 31 * var2 + Boolean.hashCode(var1.menuFilePopupScrollbarDragActive);
         var2 = 31 * var2 + var1.menuFilePopupPageKeys.size();
         var2 = 31 * var2 + (var1.activeTool == null ? 0 : var1.activeTool.hashCode());
         var2 = 31 * var2 + this.hashString(var1.activeHandle);
         var2 = 31 * var2 + this.hashString(var1.hoveredHandle);
         var2 = 31 * var2 + this.hashString(var1.selectedElementId);
         var2 = 31 * var2 + Boolean.hashCode(var1.moveDragActive);
         var2 = 31 * var2 + Boolean.hashCode(var1.previewPanActive);
         var2 = 31 * var2 + Boolean.hashCode(var1.marqueeSelectActive);
         var2 = 31 * var2 + Boolean.hashCode(var1.layersReorderActive);
         var2 = 31 * var2 + Boolean.hashCode(var1.actionsReorderActive);
         var2 = 31 * var2 + Boolean.hashCode(var1.activePagesReorderActive);
         var2 = 31 * var2 + Boolean.hashCode(var1.opacitySliderDragActive);
         var2 = 31 * var2 + Boolean.hashCode(var1.sidebarFieldDragActive);
         var2 = 31 * var2 + Boolean.hashCode(var1.colorPickerGradDragActive);
         var2 = 31 * var2 + Boolean.hashCode(var1.colorPickerHueDragActive);
         var2 = 31 * var2 + Boolean.hashCode(var1.animationTimelineSliderDragActive);
         var2 = 31 * var2 + Boolean.hashCode(var1.animationTimelineKeyframeDragActive);
         var2 = 31 * var2 + Boolean.hashCode(var1.alignToolbarVisible);
         var2 = 31 * var2 + var1.activePagesVisibleHoveredSlot;
         var2 = 31 * var2 + this.hashString(var1.fileDropdownHoverTargetId);
         var2 = 31 * var2 + this.hashString(var1.editDropdownHoverTargetId);
         var2 = 31 * var2 + this.hashString(var1.selectionDropdownHoverTargetId);
         var2 = 31 * var2 + this.hashString(var1.layerDropdownHoverTargetId);
         var2 = 31 * var2 + this.hashString(var1.windowDropdownHoverTargetId);
         var2 = 31 * var2 + this.hashString(var1.keyframeDropdownHoverTargetId);
         var2 = 31 * var2 + this.hashString(var1.keyframeTimelineDropdownHoverTargetId);
         var2 = 31 * var2 + this.hashString(var1.cursorPageDropdownHoverTargetId);
         var2 = 31 * var2 + this.hashString(var1.cursorLayersDropdownHoverTargetId);
         var2 = 31 * var2 + this.hashString(var1.cursorActionsDropdownHoverTargetId);
         var2 = 31 * var2 + this.hashString(var1.cursorActionsEditDropdownHoverTargetId);
         var2 = 31 * var2 + var1.elements.size();
         return 31 * var2 + var1.shellRuntimeRects.size();
      }
   }

   protected int hashString(String var1) {
      return var1 == null ? 0 : var1.hashCode();
   }

   protected String resolveCursorVisualUnicode(EditorSession var1, double var2, double var4) {
      if (var1 == null) {
         return "\ue67c";
      } else if (var1.editMode && var1.previewMode) {
         return this.resolveEditorCursorVisualUnicode(var1, var2, var4);
      } else if (this.isEditorMenuSession(var1)) {
         String var6 = this.resolveEditorMenuHoveredHitboxId(var1, var2, var4);
         return var6 == null ? this.cursorUnicodeValue(var1.cursorDefaultUnicode, "\ue67c") : this.cursorUnicodeValue(var1.cursorHoverUnicode, "\ue67d");
      } else {
         return this.isRuntimeClickableHoverTarget(var1, var2, var4)
            ? this.cursorUnicodeValue(var1.cursorHoverUnicode, "\ue67d")
            : this.cursorUnicodeValue(var1.cursorDefaultUnicode, "\ue67c");
      }
   }

   protected String resolveEditorCursorVisualUnicode(EditorSession var1, double var2, double var4) {
      if (var1 == null) {
         return "\ue67c";
      } else if (this.isEditorSliderDragActive(var1)) {
         return this.cursorUnicodeValue(var1.cursorSliderDragUnicode, "\ue67f");
      } else {
         boolean var6 = var1.moveDragActive && var1.activeTool == EditorTool.SCALE && "move".equals(this.firstNonBlank(new String[]{var1.activeHandle}));
         boolean var7 = var1.moveDragActive && var1.selectedElementId != null && !var1.selectedElementId.isBlank() && (!var6 || var1.moveDragMutated);
         if (!var7 && !var1.layersReorderActive && !var1.actionsReorderActive && !var1.activePagesReorderActive) {
            String var8 = this.resolveResizeHandleCursorUnicode(var1, var1.activeHandle);
            if (!var8.isBlank()) {
               return var8;
            } else {
               String var9 = this.firstNonBlank(new String[]{var1.hoveredHandle});
               if (var9.isBlank() && var1.activeTool == EditorTool.SCALE) {
                  var9 = this.firstNonBlank(new String[]{this.findHoveredHandle(var1, var2, var4)});
               }

               String var10 = this.resolveResizeHandleCursorUnicode(var1, var9);
               if (!var10.isBlank()) {
                  return var10;
               } else {
                  return this.isEditorClickableHoverTarget(var1, var2, var4)
                     ? this.cursorUnicodeValue(var1.cursorHoverUnicode, "\ue67d")
                     : this.cursorUnicodeValue(var1.cursorDefaultUnicode, "\ue67c");
               }
            }
         } else {
            return this.cursorUnicodeValue(var1.cursorDragUnicode, "\ue67e");
         }
      }
   }

   protected boolean isEditorSliderDragActive(EditorSession var1) {
      return var1 != null && (var1.opacitySliderDragActive || var1.sidebarFieldDragActive || var1.colorPickerGradDragActive || var1.colorPickerHueDragActive);
   }

   protected String resolveResizeHandleCursorUnicode(EditorSession var1, String var2) {
      String var3 = this.firstNonBlank(new String[]{var2}).trim().toLowerCase(Locale.ROOT);
      if (var3.isBlank()) {
         return "";
      } else {
         return switch (var3) {
            case "move" -> this.cursorUnicodeValue(var1 == null ? null : var1.cursorHoverUnicode, "\ue67d");
            case "tr", "bl" -> this.cursorUnicodeValue(var1 == null ? null : var1.cursorResizeTrBlUnicode, "\ue680");
            case "tl", "br" -> this.cursorUnicodeValue(var1 == null ? null : var1.cursorResizeTlBrUnicode, "\ue681");
            case "n", "s" -> this.cursorUnicodeValue(var1 == null ? null : var1.cursorResizeVerticalUnicode, "\ue682");
            case "w", "e" -> this.cursorUnicodeValue(var1 == null ? null : var1.cursorResizeHorizontalUnicode, "\ue683");
            default -> "";
         };
      }
   }

   protected boolean isEditorClickableHoverTarget(EditorSession var1, double var2, double var4) {
      if (var1 == null || !var1.editMode || !var1.previewMode) {
         return false;
      } else if (this.isEditorImageFilePopupVisible(var1)) {
         return this.resolveEditorMenuFilePopupHoveredHitboxId(var1, var2, var4) != null;
      } else if (var1.welcomePopupVisible) {
         return this.isWelcomePopupClickableTarget(var1, var2, var4);
      } else if (var1.savePopupVisible) {
         return this.isSavePopupClickableTarget(var1, var2, var4);
      } else if (var1.preferencesPopupVisible) {
         return this.isPreferencesPopupClickableTarget(var1, var2, var4);
      } else if (!this.firstNonBlank(new String[]{var1.fileDropdownHoverTargetId}).isBlank()
         || !this.firstNonBlank(new String[]{var1.editDropdownHoverTargetId}).isBlank()
         || !this.firstNonBlank(new String[]{var1.selectionDropdownHoverTargetId}).isBlank()
         || !this.firstNonBlank(new String[]{var1.layerDropdownHoverTargetId}).isBlank()
         || !this.firstNonBlank(new String[]{var1.windowDropdownHoverTargetId}).isBlank()
         || !this.firstNonBlank(new String[]{var1.keyframeDropdownHoverTargetId}).isBlank()
         || !this.firstNonBlank(new String[]{var1.keyframeTimelineDropdownHoverTargetId}).isBlank()
         || !this.firstNonBlank(new String[]{var1.cursorPageDropdownHoverTargetId}).isBlank()
         || !this.firstNonBlank(new String[]{var1.cursorLayersDropdownHoverTargetId}).isBlank()
         || !this.firstNonBlank(new String[]{var1.cursorActionsDropdownHoverTargetId}).isBlank()
         || !this.firstNonBlank(new String[]{var1.cursorActionsEditDropdownHoverTargetId}).isBlank()) {
         return true;
      } else if (var1.activePagesVisibleHoveredSlot > 0) {
         return true;
      } else if (this.findHoveredToolbarTool(var1, var2, var4) != null) {
         return true;
      } else if (var1.activeTool == EditorTool.ALIGN && this.resolveHoveredAlignToolbarActionId(var1, var2, var4) != null) {
         return true;
      } else if (this.isInsideAnyEditorShellHitbox(var1, var2, var4)) {
         return true;
      } else {
         HoverElement var6 = this.findHoveredElement(var1, var2, var4);
         String var7 = var6 == null ? null : this.targetIdOf(var6);
         return var7 != null && !var7.isBlank() && this.isTargetInteractable(var1, var7);
      }
   }

   protected boolean isSavePopupClickableTarget(EditorSession var1, double var2, double var4) {
      return var1 != null && var1.savePopupVisible
         ? this.isInsideShellBlock(var1, "popup_save_textbox_name", var2, var4)
            || this.isInsideShellBlock(var1, "popup_save_textbox_desc", var2, var4)
            || this.isInsideShellBlock(var1, "popup_save_textbox_command", var2, var4)
            || this.isInsideShellBlock(var1, "popup_save_confirm_hitbox", var2, var4)
            || this.isInsideShellBlock(var1, "popup_save_cancel_hitbox", var2, var4)
         : false;
   }

   protected boolean isPreferencesPopupClickableTarget(EditorSession var1, double var2, double var4) {
      return var1 != null && var1.preferencesPopupVisible
         ? this.isInsideShellBlock(var1, "popup_preferences_hotbar_name", var2, var4)
            || this.isInsideShellBlock(var1, "popup_preferences_hand_name", var2, var4)
            || this.isInsideShellBlock(var1, "popup_preferences_optimized_name", var2, var4)
            || this.isInsideShellBlock(var1, "popup_preferences_sound_name", var2, var4)
            || this.isInsideShellBlock(var1, "popup_preferences_confirm_hitbox", var2, var4)
            || this.isInsideShellBlock(var1, "popup_preferences_cancel_hitbox", var2, var4)
         : false;
   }

   protected boolean isWelcomePopupClickableTarget(EditorSession var1, double var2, double var4) {
      return var1 != null && var1.welcomePopupVisible
         ? this.isInsideShellBlock(var1, "popup_welcome_confirm_hitbox", var2, var4) || this.isInsideShellBlock(var1, "popup_welcome_link_hitbox", var2, var4)
         : false;
   }

   protected boolean isInsideAnyEditorShellHitbox(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.shellRuntimeRects != null && !var1.shellRuntimeRects.isEmpty()) {
         for (String var7 : var1.shellRuntimeRects.keySet()) {
            String var8 = this.firstNonBlank(new String[]{var7}).toLowerCase(Locale.ROOT);
            if (!var8.isBlank() && var8.contains("hitbox") && this.isInsideShellBlock(var1, var7, var2, var4)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   protected boolean isRuntimeClickableHoverTarget(EditorSession var1, double var2, double var4) {
      if (var1 != null && !var1.editMode) {
         HoverElement var6 = this.findHoveredRuntimeElement(var1, var2, var4);
         if (var6 == null) {
            return false;
         } else {
            for (String var9 : this.resolveRuntimeActionTargetPathCandidates(var6)) {
               List var10 = this.resolveRuntimeActionsByTargetPath(var1, var9);
               if (var10 != null && !var10.isEmpty()) {
                  return true;
               }
            }

            for (String var14 : this.resolveRuntimeActionTargetCandidates(var1, var6)) {
               List var11 = this.resolveTargetActionsRawList(var1, var14, false);
               if (var11 != null && !var11.isEmpty()) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return false;
      }
   }

   protected void applyCursorVisualUnicode(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null) {
         Entity var4 = this.hudService.getHud(var1, "cursor");
         if (var4 != null) {
            String var5 = this.cursorUnicodeValue(var3, this.cursorUnicodeValue(var2.cursorDefaultUnicode, "\ue67c"));
            String var6 = this.firstNonBlank(new String[]{this.normalizeHexColor(var2.cursorColor), "ffffff"});
            String var7 = this.applyPreferredFont(this.withHexPrefix(var5, var6), null, true);
            String var8 = this.hudService.getHudText(var4, null);
            if (!this.equalsNullable(var8, var7)) {
               this.hudService.setHudText(var4, var7, null, false);
            }
         }
      }
   }

   protected String readCursorUnicodeConfig(YamlConfiguration var1, String var2, String... var3) {
      if (var1 != null && var3 != null) {
         for (Object var7_raw : var3) {
            String var7 = var7_raw != null ? var7_raw.toString() : null;
            if (var7 != null && !var7.isBlank()) {
               String var8 = this.firstNonBlank(new String[]{var1.getString(var7)});
               if (!var8.isBlank()) {
                  return var8;
               }
            }
         }
      }

      return this.firstNonBlank(new String[]{var2});
   }

   protected String cursorUnicodeValue(String var1, String var2) {
      return this.firstNonBlank(new String[]{var1, var2});
   }

   protected void syncSeamlessCursorHud(Player var1, double var2, double var4) {
      if (var1 != null) {
         this.cameraService.updateActiveCursorLayer(var1, var4);
         Entity var6 = this.hudService.getHud(var1, "cursor");
         if (var6 != null) {
            double var7 = Double.isFinite(var2) ? Math.max(1.0, var2) : 1.0;
            Vector var9 = this.hudService.getHudScale(var6);
            if (var9 == null || !(Math.abs(var9.getX() - var7) <= 1.0E-4) || !(Math.abs(var9.getY() - var7) <= 1.0E-4)) {
               this.hudService.setHudScale(var6, new Vector(var7, var7, 2.0), 0, false);
            }
         }
      }
   }

   @Override
   protected boolean handleEditorMenuClick(Player var1, EditorSession var2, GuiService.ClickType var3) {
      if (var1 != null && var2 != null) {
         double var4 = var2.cursorX + var2.hitboxOffsetX;
         double var6 = var2.cursorY + var2.hitboxOffsetY;
         long var8 = (long)Bukkit.getCurrentTick();
         boolean var10 = var8 - var2.lastLeftClickTick <= 2L;
         if (var2.menuFilePopupVisible) {
            if (var3 == GuiService.ClickType.LEFT) {
               var2.lastLeftClickTick = var8;
               if (!var10) {
                  this.handleEditorMenuFilePopupClick(var1, var2, var4, var6);
               }

               return true;
            } else {
               return var3 == GuiService.ClickType.RIGHT;
            }
         } else if (var3 == GuiService.ClickType.LEFT && this.isEditorMenuHitboxHovered(var2, "editormenu_navbar_close", var4, var6)) {
            var2.lastLeftClickTick = var8;
            if (var10) {
               return true;
            } else {
               this.plugin.closeUiForPlayer(var1);
               return true;
            }
         } else if (var3 == GuiService.ClickType.LEFT && this.isEditorMenuHitboxHovered(var2, "editor_menu_button_create_hitbox", var4, var6)) {
            var2.lastLeftClickTick = var8;
            return var10 ? true : this.openBlankEditorFromMenu(var1, var2);
         } else if (var3 == GuiService.ClickType.LEFT && this.isEditorMenuHitboxHovered(var2, "editor_menu_button_edit_hitbox", var4, var6)) {
            var2.lastLeftClickTick = var8;
            return var10 ? true : this.openEditorMenuFilePopup(var1, var2, true);
         } else if (var3 == GuiService.ClickType.LEFT && this.isEditorMenuHitboxHovered(var2, "editor_menu_button_open_hitbox", var4, var6)) {
            var2.lastLeftClickTick = var8;
            return var10 ? true : this.openEditorMenuFilePopup(var1, var2, false);
         } else {
            return var3 != GuiService.ClickType.RIGHT
               ? false
               : this.isEditorMenuHitboxHovered(var2, "editor_menu_button_edit_hitbox", var4, var6)
                  || this.isEditorMenuHitboxHovered(var2, "editor_menu_button_create_hitbox", var4, var6)
                  || this.isEditorMenuHitboxHovered(var2, "editor_menu_button_open_hitbox", var4, var6)
                  || this.isEditorMenuHitboxHovered(var2, "editormenu_navbar_close", var4, var6);
         }
      } else {
         return false;
      }
   }

   protected void updateEditorMenuHoverIcons(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         String var7 = this.resolveEditorMenuHoveredHitboxId(var2, var3, var5);
         this.updateEditorMenuFilePopupActionIcons(var1, var2, var7);
         if (!this.equalsNullable(var2.hoveredElementId, var7)) {
            var2.hoveredElementId = var7;
            this.setHudTextIfExists(var1, "editor_menu_button_edit", this.withEditorUiFont("editor_menu_button_edit_hitbox".equals(var7) ? "\ue619" : "\ue616"));
            this.setHudTextIfExists(
               var1, "editor_menu_button_create", this.withEditorUiFont("editor_menu_button_create_hitbox".equals(var7) ? "\ue61a" : "\ue617")
            );
            this.setHudTextIfExists(var1, "editor_menu_button_open", this.withEditorUiFont("editor_menu_button_open_hitbox".equals(var7) ? "\ue61b" : "\ue618"));
         }
      }
   }

   protected void updateEditorMenuFilePopupActionIcons(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var2.menuFilePopupVisible) {
         boolean var4 = this.hasEditorMenuFilePopupSelection(var2);
         boolean var5 = var4 && "popup_file_selector_confirm_hitbox".equals(var3);
         boolean var6 = "popup_file_selector_cancel_hitbox".equals(var3);
         String var7 = var4 ? (var5 ? "\ueea8" : "\ueea5") : "\ueeaa";
         if (this.isEditorImageFilePopupMode(var2)) {
            this.setShellText(var1, "popup_file_selector_confirm", this.withEditorUiFont(var7));
            this.setShellText(var1, "popup_file_selector_cancel", this.withEditorUiFont(var6 ? "\ueea9" : "\ueea6"));
         } else {
            this.setHudTextIfExists(var1, "popup_file_selector_confirm", this.withEditorUiFont(var7));
            this.setHudTextIfExists(var1, "popup_file_selector_cancel", this.withEditorUiFont(var6 ? "\ueea9" : "\ueea6"));
         }
      }
   }

   protected String resolveEditorMenuHoveredHitboxId(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.menuFilePopupVisible) {
         return this.resolveEditorMenuFilePopupHoveredHitboxId(var1, var2, var4);
      } else if (this.isEditorMenuHitboxHovered(var1, "editormenu_navbar_close", var2, var4)) {
         return "editormenu_navbar_close";
      } else if (this.isEditorMenuHitboxHovered(var1, "editor_menu_button_edit_hitbox", var2, var4)) {
         return "editor_menu_button_edit_hitbox";
      } else if (this.isEditorMenuHitboxHovered(var1, "editor_menu_button_create_hitbox", var2, var4)) {
         return "editor_menu_button_create_hitbox";
      } else {
         return this.isEditorMenuHitboxHovered(var1, "editor_menu_button_open_hitbox", var2, var4) ? "editor_menu_button_open_hitbox" : null;
      }
   }

   protected boolean isEditorMenuHitboxHovered(EditorSession var1, String var2, double var3, double var5) {
      if (var1 == null || var2 == null || var2.isBlank()) {
         return false;
      } else if (this.isEditorImageFilePopupVisible(var1)) {
         double var16 = var3 - var1.hitboxOffsetX;
         double var9 = var5 - var1.hitboxOffsetY;
         return this.isInsideShellBlock(var1, var2, var3, var5) || this.isInsideShellBlock(var1, var2, var16, var9);
      } else {
         EditorRect var7 = this.getEditorMenuHitboxBounds(var1, var2);
         if (var7 != null && !(var7.width <= 0.0) && !(var7.height <= 0.0)) {
            double var8 = this.toRenderX(var1, var7.x);
            double var10 = this.toRenderY(var1, var7.y);
            double var12 = var8 + this.toRenderSize(var1, var7.width);
            double var14 = var10 + this.toRenderSize(var1, var7.height);
            return var3 >= var8 && var3 <= var12 && var5 >= var10 && var5 <= var14;
         } else {
            return false;
         }
      }
   }

   protected EditorRect getEditorMenuHitboxBounds(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         if (this.isEditorImageFilePopupVisible(var1)) {
            return this.findShellBlockRect(var1, var2);
         } else {
            EditorRect var3 = null;

            for (Object var5_raw : var1.elements) {
               HoverElement var5 = (HoverElement)var5_raw;
               if (this.matchesEditorMenuHitbox(var1, var5, var2)) {
                  var3 = this.mergeBounds(var3, this.getElementBounds(var5));
               }
            }

            return var3;
         }
      } else {
         return null;
      }
   }

   protected boolean matchesEditorMenuHitbox(EditorSession var1, HoverElement var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String var4 = this.firstNonBlank(new String[]{var2.id});
         if (!var3.equals(var4) && !this.runtimeElementId(var1, var3).equals(var4)) {
            String var5 = this.firstNonBlank(new String[]{this.targetIdOf(var2)});
            return var3.equals(var5);
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   protected boolean isEditorMenuSession(EditorSession var1) {
      return var1 == null ? false : "editor_menu".equalsIgnoreCase(this.firstNonBlank(new String[]{var1.pageName}).trim());
   }

   protected boolean openBlankEditorFromMenu(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         boolean var3 = this.openGui(var1, "editor", true, true);
         if (!var3) {
            return false;
         } else {
            this.playEditorSfx(var1, "editor-opened");
            EditorSession var4 = this.editorSessions.get(var1.getUniqueId());
            if (var4 != null && var4.editMode && var4.previewMode) {
               this.createNewUiInEditor(var1, var4);
               return true;
            } else {
               return true;
            }
         }
      } else {
         return false;
      }
   }

   protected void initializeEditorMenuFilePopupState(EditorSession var1, Map<String, YamlConfiguration> var2) {
      if (var1 != null && !var1.editMode && this.isEditorMenuSession(var1)) {
         var1.menuTemplateBlocks = (List<Map<String, Object>>)(var1.rawBlocks == null ? new ArrayList<>() : this.copyBlocks(var1.rawBlocks));
         var1.menuFilePopupVisible = false;
         var1.menuFilePopupImageMode = false;
         var1.menuFilePopupEditAction = true;
         var1.menuFilePopupSelectedPage = "";
         var1.menuFilePopupScrollOffset = 0;
         var1.menuFilePopupScrollbarDragActive = false;
         var1.menuFilePopupScrollbarDragOffsetY = 0.0;
         var1.menuFilePopupScrollbarY = 0;
         this.populateEditorMenuFilePopupEntries(var1, var2);
         this.rebuildEditorMenuFilePopupBlocks(var1);
      }
   }

   protected void populateEditorMenuFilePopupEntries(EditorSession var1, Map<String, YamlConfiguration> var2) {
      if (var1 != null) {
         String var3 = this.normalizePageKey(var1.menuFilePopupSelectedPage, "");
         var1.menuFilePopupPageKeys.clear();
         var1.menuFilePopupNames.clear();
         var1.menuFilePopupDescriptions.clear();
         var1.menuFilePopupCommands.clear();
         if (var2 != null && !var2.isEmpty()) {
            LinkedHashMap var4 = new LinkedHashMap();

            for (Map.Entry<?, ?> var6 : ((Map<?, ?>)var2).entrySet()) {
               if (var6 != null) {
                  String var7 = this.normalizePageKey((String)var6.getKey(), "");
                  if (!var7.isBlank() && !this.isInternalEditorPageName(var7)) {
                     var4.putIfAbsent(var7, (YamlConfiguration)var6.getValue());
                  }
               }
            }

            ArrayList var13 = new ArrayList(var4.keySet());
            var13.sort(String.CASE_INSENSITIVE_ORDER);

            for (Object var15_raw : var13) {
               String var15 = var15_raw != null ? var15_raw.toString() : null;
               YamlConfiguration var8 = (YamlConfiguration)var4.get(var15);
               if (var8 == null || !var8.getBoolean("page-hidden", false)) {
                  String var9 = this.firstNonBlank(new String[]{var8 == null ? null : var8.getString("name"), var15}).trim();
                  if (var9.isBlank()) {
                     var9 = var15;
                  }

                  String var10 = this.firstNonBlank(
                        new String[]{var8 == null ? null : var8.getString("desc"), var8 == null ? null : var8.getString("description")}
                     )
                     .trim();
                  if (var10.isBlank()) {
                     var10 = "None";
                  }

                  String var11 = this.normalizeCustomCommandToken(var8 == null ? null : var8.getString("command"));
                  String var12 = var11.isBlank() ? "None" : "/" + var11;
                  var1.menuFilePopupPageKeys.add(var15);
                  var1.menuFilePopupNames.put(var15, var9);
                  var1.menuFilePopupDescriptions.put(var15, var10);
                  var1.menuFilePopupCommands.put(var15, var12);
               }
            }
         }

         if (!var3.isBlank() && var1.menuFilePopupPageKeys.contains(var3)) {
            var1.menuFilePopupSelectedPage = var3;
         }

         this.normalizeEditorMenuFilePopupSelection(var1);
         this.normalizeEditorMenuFilePopupScroll(var1);
      }
   }

   protected void populateEditorImageFilePopupEntries(EditorSession var1) {
      if (var1 != null) {
         String var2 = this.normalizePageKey(var1.menuFilePopupSelectedPage, "");
         var1.menuFilePopupPageKeys.clear();
         var1.menuFilePopupNames.clear();
         var1.menuFilePopupDescriptions.clear();
         var1.menuFilePopupCommands.clear();
         List var3 = this.plugin == null ? Collections.emptyList() : this.plugin.getGeneratedUiImagesForEditor();
         if (var3 != null && !var3.isEmpty()) {
            for (UiImageAtlasService.GeneratedImage var5 : (List<UiImageAtlasService.GeneratedImage>)(List<?>)var3) {
               if (var5 != null) {
                  String var6 = this.firstNonBlank(new String[]{var5.name()}).trim();
                  String var7 = this.normalizePageKey(var6, "");
                  if (!var7.isBlank() && !var1.menuFilePopupNames.containsKey(var7)) {
                     String var8 = var6 + ".png";
                     String var9 = var5.width() + ", " + var5.height() + "px";
                     var1.menuFilePopupPageKeys.add(var7);
                     var1.menuFilePopupNames.put(var7, var8);
                     var1.menuFilePopupDescriptions.put(var7, var9);
                     var1.menuFilePopupCommands.put(var7, var6);
                  }
               }
            }
         }

         if (!var2.isBlank() && var1.menuFilePopupPageKeys.contains(var2)) {
            var1.menuFilePopupSelectedPage = var2;
         }

         this.normalizeEditorMenuFilePopupSelection(var1);
         this.normalizeEditorMenuFilePopupScroll(var1);
      }
   }

   protected void normalizeEditorMenuFilePopupSelection(EditorSession var1) {
      if (var1 != null) {
         if (var1.menuFilePopupPageKeys.isEmpty()) {
            var1.menuFilePopupSelectedPage = "";
         } else {
            String var2 = this.normalizePageKey(var1.menuFilePopupSelectedPage, "");
            if (var2.isBlank()) {
               var1.menuFilePopupSelectedPage = "";
            } else if (!var1.menuFilePopupPageKeys.contains(var2)) {
               var1.menuFilePopupSelectedPage = "";
            } else {
               var1.menuFilePopupSelectedPage = var2;
            }
         }
      }
   }

   protected boolean hasEditorMenuFilePopupSelection(EditorSession var1) {
      if (var1 != null && !var1.menuFilePopupPageKeys.isEmpty()) {
         String var2 = this.normalizePageKey(var1.menuFilePopupSelectedPage, "");
         return !var2.isBlank() && var1.menuFilePopupPageKeys.contains(var2);
      } else {
         return false;
      }
   }

   protected void normalizeEditorMenuFilePopupScroll(EditorSession var1) {
      if (var1 != null) {
         int var2 = this.resolveEditorMenuFilePopupMaxOffset(var1);
         var1.menuFilePopupScrollOffset = this.normalizeEditorMenuFilePopupOffset(var1.menuFilePopupScrollOffset, var2);
         var1.menuFilePopupScrollbarY = this.clampEditorMenuFilePopupScrollbarY(var1.menuFilePopupScrollbarY);
      }
   }

   protected List<Map<String, Object>> buildEditorMenuFilePopupChildren(EditorSession var1) {
      ArrayList var2 = new ArrayList();
      if (var1 == null) {
         return var2;
      } else if (this.isEditorImageFilePopupMode(var1)) {
         return this.buildEditorImageFilePopupChildren(var1);
      } else {
         int var3 = this.resolveEditorMenuFilePopupVisibleCount(var1);

         for (int var4 = 1; var4 <= var3; var4++) {
            String var5 = this.resolveEditorMenuFilePopupPageAtSlot(var1, var4);
            if (var5 != null && !var5.isBlank()) {
               String var6 = this.firstNonBlank(new String[]{var1.menuFilePopupNames.get(var5), var5});
               String var7 = this.firstNonBlank(new String[]{var1.menuFilePopupDescriptions.get(var5), "None"});
               String[] var8 = this.splitEditorMenuFilePopupDescription(var7);
               String var9 = this.firstNonBlank(new String[]{var1.menuFilePopupCommands.get(var5), "None"});
               boolean var10 = this.normalizePageKey(var5, "").equalsIgnoreCase(this.normalizePageKey(var1.menuFilePopupSelectedPage, ""));
               LinkedHashMap var11 = new LinkedHashMap();
               var11.put("name", this.truncateEditorMenuFilePopupValue(var6, 29));
               var11.put("descl1", var8[0]);
               var11.put("descl2", var8[1]);
               var11.put("command", this.truncateEditorMenuFilePopupValue(var9, 30));
               var11.put("bg", var10 ? "1c1c1c" : "141414");
               var11.put("hitbox_id", this.buildEditorMenuFilePopupEntryHitboxId(var4));
               LinkedHashMap var12 = new LinkedHashMap();
               var12.put("component", "editor_popup_file_element");
               var12.put("params", var11);
               var2.add(var12);
            }
         }

         return var2;
      }
   }

   protected List<Map<String, Object>> buildEditorImageFilePopupChildren(EditorSession var1) {
      ArrayList var2 = new ArrayList();
      if (var1 == null) {
         return var2;
      } else {
         int var3 = this.resolveEditorMenuFilePopupVisibleCount(var1);

         for (int var4 = 1; var4 <= var3; var4++) {
            String var5 = this.resolveEditorMenuFilePopupPageAtSlot(var1, var4);
            if (var5 != null && !var5.isBlank()) {
               String var6 = this.firstNonBlank(new String[]{var1.menuFilePopupNames.get(var5), var5});
               String var7 = this.firstNonBlank(new String[]{var1.menuFilePopupDescriptions.get(var5), "0, 0px"});
               boolean var8 = this.normalizePageKey(var5, "").equalsIgnoreCase(this.normalizePageKey(var1.menuFilePopupSelectedPage, ""));
               LinkedHashMap var9 = new LinkedHashMap();
               var9.put("image", this.truncateEditorMenuFilePopupValue(var6, 29));
               var9.put("scale", this.truncateEditorMenuFilePopupValue(var7, 24));
               var9.put("bg", var8 ? "1c1c1c" : "141414");
               var9.put("hitbox_id", this.buildEditorMenuFilePopupEntryHitboxId(var4));
               LinkedHashMap var10 = new LinkedHashMap();
               var10.put("component", "editor_popup_image_element");
               var10.put("params", var9);
               var2.add(var10);
            }
         }

         return var2;
      }
   }

   protected String[] splitEditorMenuFilePopupDescription(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim();
      if (var2.isBlank()) {
         var2 = "None";
      }

      if (var2.length() <= 37) {
         return new String[]{var2, ""};
      } else {
         String var3 = var2.substring(0, 37);
         String var4 = var2.substring(37).trim();
         String var5 = this.truncateEditorMenuFilePopupValue(var4, 37);
         return new String[]{var3, var5};
      }
   }

   protected String truncateEditorMenuFilePopupValue(String var1, int var2) {
      String var3 = this.firstNonBlank(new String[]{var1}).trim();
      return !var3.isBlank() && var2 > 0 && var3.length() > var2 ? var3.substring(0, var2) + "..." : var3;
   }

   protected String buildEditorMenuFilePopupEntryHitboxId(int var1) {
      int var2 = Math.max(1, var1);
      return "popup_file_selector_entry_" + var2 + "_hitbox";
   }

   protected int resolveEditorMenuFilePopupVisibleCount(EditorSession var1) {
      if (var1 != null && !var1.menuFilePopupPageKeys.isEmpty()) {
         int var2 = this.resolveEditorMenuFilePopupMaxVisible(var1);
         int var3 = var1.menuFilePopupPageKeys.size() - Math.max(0, var1.menuFilePopupScrollOffset);
         return Math.max(0, Math.min(var2, var3));
      } else {
         return 0;
      }
   }

   protected String resolveEditorMenuFilePopupPageAtSlot(EditorSession var1, int var2) {
      if (var1 != null && var2 > 0 && !var1.menuFilePopupPageKeys.isEmpty()) {
         int var3 = var1.menuFilePopupScrollOffset + var2 - 1;
         return var3 >= 0 && var3 < var1.menuFilePopupPageKeys.size() ? var1.menuFilePopupPageKeys.get(var3) : null;
      } else {
         return null;
      }
   }

   protected int resolveEditorMenuFilePopupMaxOffset(EditorSession var1) {
      if (var1 != null && !var1.menuFilePopupPageKeys.isEmpty()) {
         int var2 = Math.max(1, 2);
         int var3 = this.resolveEditorMenuFilePopupMaxVisible(var1);
         int var4 = (var1.menuFilePopupPageKeys.size() + var2 - 1) / var2;
         int var5 = Math.max(1, var3 / var2);
         int var6 = Math.max(0, var4 - var5);
         return var6 * var2;
      } else {
         return 0;
      }
   }

   protected int normalizeEditorMenuFilePopupOffset(int var1, int var2) {
      if (var2 <= 0) {
         return 0;
      } else {
         int var3 = Math.max(0, Math.min(var2, var1));
         int var4 = Math.max(1, 2);
         int var5 = (int)Math.round((double)var3 / (double)var4) * var4;
         return Math.max(0, Math.min(var2, var5));
      }
   }

   protected int clampEditorMenuFilePopupScrollbarY(int var1) {
      return Math.max(0, Math.min(289, var1));
   }

   protected boolean isEditorMenuFilePopupScrollbarVisible(EditorSession var1) {
      return var1 != null && var1.menuFilePopupPageKeys.size() > this.resolveEditorMenuFilePopupMaxVisible(var1);
   }

   protected int resolveEditorMenuFilePopupOffsetFromScrollbarY(EditorSession var1, double var2) {
      if (var1 == null) {
         return 0;
      } else {
         int var4 = this.resolveEditorMenuFilePopupMaxOffset(var1);
         if (var4 <= 0) {
            return 0;
         } else {
            double var5 = Math.max(0.0, Math.min(289.0, var2));
            double var7 = var5 / 289.0;
            int var9 = (int)Math.round(var7 * (double)var4);
            return this.normalizeEditorMenuFilePopupOffset(var9, var4);
         }
      }
   }

   protected int resolveEditorMenuFilePopupScrollbarYFromOffset(EditorSession var1) {
      if (var1 == null) {
         return 0;
      } else {
         int var2 = this.resolveEditorMenuFilePopupMaxOffset(var1);
         if (var2 <= 0) {
            return 0;
         } else {
            int var3 = this.normalizeEditorMenuFilePopupOffset(var1.menuFilePopupScrollOffset, var2);
            double var4 = (double)var3 / (double)var2;
            return (int)Math.round(var4 * 289.0);
         }
      }
   }

   protected void rebuildEditorMenuFilePopupBlocks(EditorSession var1) {
      if (var1 != null && this.isEditorMenuFilePopupRenderContext(var1)) {
         if (this.isEditorImageFilePopupMode(var1)) {
            var1.menuTemplateBlocks = (List<Map<String, Object>>)(var1.rawBlocks == null ? new ArrayList<>() : this.copyBlocks(var1.rawBlocks));
         }

         if (var1.menuTemplateBlocks == null || var1.menuTemplateBlocks.isEmpty()) {
            var1.menuTemplateBlocks = (List<Map<String, Object>>)(var1.rawBlocks == null ? new ArrayList<>() : this.copyBlocks(var1.rawBlocks));
         }

         this.normalizeEditorMenuFilePopupSelection(var1);
         this.normalizeEditorMenuFilePopupScroll(var1);
         List var2 = this.copyBlocks(var1.menuTemplateBlocks);
         this.setEditorMenuManageFilePopupEnabled(var2, var1.menuFilePopupVisible);
         Map<String, YamlConfiguration> var3 = var1.components;
         if (var1.menuFilePopupVisible && var1.components != null && !var1.components.isEmpty()) {
            YamlConfiguration var4 = var1.components.get("editor_manage_file_popup");
            if (var4 != null) {
               YamlConfiguration var5 = this.cloneYamlConfiguration(var4);
               List var6 = this.copyBlocks(var5.getList("blocks"));
               List var7 = this.buildEditorMenuFilePopupChildren(var1);
               this.replaceEditorMenuFilePopupChildren(var6, var7);
               this.applyEditorMenuFilePopupGridLayout(var6, var1);
               boolean var8 = this.isEditorMenuFilePopupScrollbarVisible(var1);
               this.setEditorMenuFilePopupBlockEnabledById(var6, "scrollbar_bg", var8);
               this.setEditorMenuFilePopupBlockEnabledById(var6, "scrollbar_bar", var8);
               this.setEditorMenuFilePopupBlockEnabledById(var6, "editor_files_no_files", var1.menuFilePopupPageKeys.isEmpty());
               int var9 = this.clampEditorMenuFilePopupScrollbarY(var1.menuFilePopupScrollbarY);
               var1.menuFilePopupScrollbarY = var9;
               this.setEditorMenuFilePopupBlockYById(var6, "scrollbar_bar", var9);
               var5.set("blocks", var6);
               var3 = new HashMap<>(var1.components);
               var3.put("editor_manage_file_popup", var5);
            }
         }

         var1.rawBlocks = var2;
         var1.renderBlocks = this.resolveRenderableBlocks(var2, (Map<String, YamlConfiguration>)var3);
      }
   }

   protected void ensureEditorManageFilePopupHiddenByDefault(EditorSession var1) {
      if (var1 != null && var1.editMode) {
         var1.menuFilePopupVisible = false;
         var1.menuFilePopupImageMode = false;
         var1.menuFilePopupSelectedPage = "";
         var1.menuFilePopupScrollOffset = 0;
         var1.menuFilePopupScrollbarY = 0;
         var1.menuFilePopupScrollbarDragActive = false;
         var1.menuFilePopupScrollbarDragOffsetY = 0.0;
      }
   }

   protected boolean setEditorMenuManageFilePopupEnabled(List<Map<String, Object>> var1, boolean var2) {
      if (var1 != null && !var1.isEmpty()) {
         boolean var3 = false;

         for (Map var5 : var1) {
            if (var5 != null && !var5.isEmpty()) {
               String var6 = this.firstNonBlank(new String[]{this.stringValue(var5.get("component")), this.stringValue(var5.get("include"))}).trim();
               if (var6.equalsIgnoreCase("editor_manage_file_popup")) {
                  boolean var7 = this.parseBooleanFlag(var5.get("enabled"), true);
                  if (var7 != var2 || !var5.containsKey("enabled")) {
                     var5.put("enabled", var2);
                     var3 = true;
                  }
               }

               if (var5.get("children") instanceof List var8 && !var8.isEmpty()) {
                  ArrayList var9 = new ArrayList();

                  for (Object var11 : var8) {
                     if (var11 instanceof Map var12) {
                        var9.add(var12);
                     }
                  }

                  var3 |= this.setEditorMenuManageFilePopupEnabled(var9, var2);
               }
            }
         }

         return var3;
      } else {
         return false;
      }
   }

   protected boolean replaceEditorMenuFilePopupChildren(List<Map<String, Object>> var1, List<Map<String, Object>> var2) {
      if (var1 != null && !var1.isEmpty()) {
         for (Map var4 : var1) {
            if (var4 != null && !var4.isEmpty()) {
               String var5 = this.firstNonBlank(new String[]{this.stringValue(var4.get("id"))}).trim();
               if ("file_popup_files".equalsIgnoreCase(var5)) {
                  var4.put("children", var2 == null ? new ArrayList() : new ArrayList(var2));
                  return true;
               }

               Object var6 = var4.get("children");
               if (var6 instanceof List) {
                  List var7 = (List)var6;
                  if (!var7.isEmpty()) {
                     ArrayList var8 = new ArrayList();

                     for (Object var10 : var7) {
                        if (var10 instanceof Map var11) {
                           var8.add(var11);
                        }
                     }

                     if (this.replaceEditorMenuFilePopupChildren(var8, var2)) {
                        return true;
                     }
                  }
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   protected boolean setEditorMenuFilePopupBlockEnabledById(List<Map<String, Object>> var1, String var2, boolean var3) {
      if (var1 != null && !var1.isEmpty() && var2 != null && !var2.isBlank()) {
         boolean var4 = false;

         for (Map var6 : var1) {
            if (var6 != null && !var6.isEmpty()) {
               String var7 = this.firstNonBlank(new String[]{this.stringValue(var6.get("id"))}).trim();
               if (var2.equalsIgnoreCase(var7)) {
                  boolean var8 = this.parseBooleanFlag(var6.get("enabled"), true);
                  if (var8 != var3 || !var6.containsKey("enabled")) {
                     var6.put("enabled", var3);
                     var4 = true;
                  }
               }

               if (var6.get("children") instanceof List var9 && !var9.isEmpty()) {
                  ArrayList var10 = new ArrayList();

                  for (Object var12 : var9) {
                     if (var12 instanceof Map var13) {
                        var10.add(var13);
                     }
                  }

                  var4 |= this.setEditorMenuFilePopupBlockEnabledById(var10, var2, var3);
               }
            }
         }

         return var4;
      } else {
         return false;
      }
   }

   protected boolean setEditorMenuFilePopupBlockYById(List<Map<String, Object>> var1, String var2, int var3) {
      if (var1 != null && !var1.isEmpty() && var2 != null && !var2.isBlank()) {
         boolean var4 = false;

         for (Map var6 : var1) {
            if (var6 != null && !var6.isEmpty()) {
               String var7 = this.firstNonBlank(new String[]{this.stringValue(var6.get("id"))}).trim();
               if (var2.equalsIgnoreCase(var7)) {
                  Map var8 = this.toStringObjectMap(var6.get("position"));
                  if (var8 == null) {
                     var8 = new LinkedHashMap();
                  }

                  if (!(var8.get("y") instanceof Number var10) || Math.round(var10.doubleValue()) != (long)var3) {
                     var8.put("y", var3);
                     var6.put("position", var8);
                     var4 = true;
                  }
               }

               if (var6.get("children") instanceof List var15 && !var15.isEmpty()) {
                  ArrayList var16 = new ArrayList();

                  for (Object var12 : var15) {
                     if (var12 instanceof Map var13) {
                        var16.add(var13);
                     }
                  }

                  var4 |= this.setEditorMenuFilePopupBlockYById(var16, var2, var3);
               }
            }
         }

         return var4;
      } else {
         return false;
      }
   }

   protected void applyEditorMenuFilePopupGridLayout(List<Map<String, Object>> var1, EditorSession var2) {
      if (var1 != null && !var1.isEmpty() && var2 != null) {
         this.setEditorMenuFilePopupBlockNumericValueById(var1, "file_popup_files", "element_h", this.resolveEditorMenuFilePopupElementHeight(var2));
         this.setEditorMenuFilePopupBlockNumericValueById(var1, "file_popup_files", "gap", this.resolveEditorMenuFilePopupElementGap(var2));
      }
   }

   protected boolean setEditorMenuFilePopupBlockNumericValueById(List<Map<String, Object>> var1, String var2, String var3, int var4) {
      if (var1 != null && !var1.isEmpty() && var2 != null && !var2.isBlank() && var3 != null && !var3.isBlank()) {
         boolean var5 = false;

         for (Map var7 : var1) {
            if (var7 != null && !var7.isEmpty()) {
               String var8 = this.firstNonBlank(new String[]{this.stringValue(var7.get("id"))}).trim();
               if (var2.equalsIgnoreCase(var8) && (!(var7.get(var3) instanceof Number var10) || Math.round(var10.doubleValue()) != (long)var4)) {
                  var7.put(var3, var4);
                  var5 = true;
               }

               if (var7.get("children") instanceof List var16 && !var16.isEmpty()) {
                  ArrayList var11 = new ArrayList();

                  for (Object var13 : var16) {
                     if (var13 instanceof Map var14) {
                        var11.add(var14);
                     }
                  }

                  var5 |= this.setEditorMenuFilePopupBlockNumericValueById(var11, var2, var3, var4);
               }
            }
         }

         return var5;
      } else {
         return false;
      }
   }

   protected boolean openEditorMenuFilePopup(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null && !var2.editMode && this.isEditorMenuSession(var2)) {
         ArrayList var4 = new ArrayList();
         Map var5 = this.loadYamlFolder("contents/pages", var4);
         if (!var4.isEmpty()) {
            this.reportYamlIssues(var4);
            this.sendEditorPlayerMessage(
               var1,
               MM.deserialize("<red><bold>UltimateUI</bold></red> <#8a989c>»</#8a989c> <yellow>YAML syntax errors found. Check console for details.</yellow>")
            );
            return false;
         } else {
            if (var2.menuTemplateBlocks == null || var2.menuTemplateBlocks.isEmpty()) {
               var2.menuTemplateBlocks = (List<Map<String, Object>>)(var2.rawBlocks == null ? new ArrayList<>() : this.copyBlocks(var2.rawBlocks));
            }

            var2.menuFilePopupImageMode = false;
            var2.menuFilePopupEditAction = var3;
            var2.menuFilePopupVisible = true;
            var2.menuFilePopupSelectedPage = "";
            var2.menuFilePopupScrollbarDragActive = false;
            var2.menuFilePopupScrollbarDragOffsetY = 0.0;
            this.populateEditorMenuFilePopupEntries(var2, var5);
            this.rerenderEditorMenuFilePopup(var1, var2);
            return true;
         }
      } else {
         return false;
      }
   }

   protected boolean openEditorImageFilePopup(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         var2.menuFilePopupImageMode = true;
         var2.menuFilePopupEditAction = false;
         var2.menuFilePopupVisible = true;
         var2.menuFilePopupSelectedPage = "";
         var2.menuFilePopupScrollOffset = 0;
         var2.menuFilePopupScrollbarY = 0;
         var2.menuFilePopupScrollbarDragActive = false;
         var2.menuFilePopupScrollbarDragOffsetY = 0.0;
         this.populateEditorImageFilePopupEntries(var2);
         this.rerenderEditorMenuFilePopup(var1, var2);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public boolean handleRuntimeScrollClick(Player var1, int var2) {
      if (var1 != null && var2 != 0) {
         EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
         if (var3 != null && !var3.editMode && this.isEditorMenuSession(var3) && var3.menuFilePopupVisible) {
            double var4 = var3.cursorX + var3.hitboxOffsetX;
            double var6 = var3.cursorY + var3.hitboxOffsetY;
            return !this.isEditorMenuHitboxHovered(var3, "scroll_detection", var4, var6) ? true : this.handleEditorMenuFilePopupScroll(var1, var3, var2);
         } else {
            return super.handleRuntimeScrollClick(var1, var2);
         }
      } else {
         return false;
      }
   }

   protected boolean handleEditorImageFilePopupScroll(Player var1, EditorSession var2, int var3, double var4, double var6) {
      if (var1 == null || var2 == null || var3 == 0 || !this.isEditorImageFilePopupVisible(var2)) {
         return false;
      } else {
         return !this.isEditorMenuHitboxHovered(var2, "scroll_detection", var4, var6) ? true : this.handleEditorMenuFilePopupScroll(var1, var2, var3);
      }
   }

   protected boolean handleEditorMenuFilePopupScroll(Player var1, EditorSession var2, int var3) {
      if (var1 != null && var2 != null && var3 != 0 && var2.menuFilePopupVisible) {
         int var4 = this.resolveEditorMenuFilePopupMaxOffset(var2);
         if (var4 <= 0) {
            return true;
         } else {
            int var5 = Math.max(1, 2);
            int var6 = var3 > 0 ? var5 : -var5;
            int var7 = this.normalizeEditorMenuFilePopupOffset(var2.menuFilePopupScrollOffset + var6, var4);
            if (var7 == var2.menuFilePopupScrollOffset) {
               return true;
            } else {
               var2.menuFilePopupScrollbarDragActive = false;
               var2.menuFilePopupScrollbarDragOffsetY = 0.0;
               var2.menuFilePopupScrollOffset = var7;
               var2.menuFilePopupScrollbarY = this.resolveEditorMenuFilePopupScrollbarYFromOffset(var2);
               this.rerenderEditorMenuFilePopup(var1, var2, 1);
               return true;
            }
         }
      } else {
         return false;
      }
   }

   protected void closeEditorMenuFilePopup(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.menuFilePopupVisible) {
         boolean var3 = var2.menuFilePopupImageMode;
         var2.menuFilePopupVisible = false;
         var2.menuFilePopupScrollbarDragActive = false;
         var2.menuFilePopupScrollbarDragOffsetY = 0.0;
         this.rerenderEditorMenuFilePopup(var1, var2);
         if (var3) {
            var2.menuFilePopupImageMode = false;
            var2.menuFilePopupSelectedPage = "";
         }
      }
   }

   protected void rerenderEditorMenuFilePopup(Player var1, EditorSession var2) {
      this.rerenderEditorMenuFilePopup(var1, var2, 1);
   }

   protected void rerenderEditorMenuFilePopup(Player var1, EditorSession var2, int var3) {
      if (var1 != null && var2 != null) {
         if (var2.editMode && var2.previewMode && var2.menuFilePopupImageMode) {
            this.rerenderEditorImageFilePopup(var1, var2, var3);
         } else if (this.isEditorMenuFilePopupRenderContext(var2)) {
            int var4 = var2.menuFilePopupTransitionTicksOverride;
            var2.menuFilePopupTransitionTicksOverride = Math.max(0, var3);

            try {
               this.rebuildEditorMenuFilePopupBlocks(var2);
               var2.lastProcessedHitX = Double.NaN;
               var2.lastProcessedHitY = Double.NaN;
               this.rerenderEditableContentForTargets(var1, var2, this.resolveEditorMenuFilePopupRerenderTargetIds(var2), true, false);
               double var5 = var2.cursorX + var2.hitboxOffsetX;
               double var7 = var2.cursorY + var2.hitboxOffsetY;
               this.updateEditorMenuHoverIcons(var1, var2, var5, var7);
               this.refreshCursorVisualState(var1, var2, var5, var7);
            } finally {
               var2.menuFilePopupTransitionTicksOverride = var4;
            }
         }
      }
   }

   protected void rerenderEditorImageFilePopup(Player var1, EditorSession var2, int var3) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode && var2.menuFilePopupImageMode) {
         int var4 = var2.menuFilePopupTransitionTicksOverride;
         var2.menuFilePopupTransitionTicksOverride = Math.max(0, var3);

         try {
            this.normalizeEditorMenuFilePopupSelection(var2);
            this.normalizeEditorMenuFilePopupScroll(var2);
            this.updateEditorImageFilePopupComponent(var2);
            var2.lastProcessedHitX = Double.NaN;
            var2.lastProcessedHitY = Double.NaN;
            this.rerenderEditorShellAfterWindowToggle(var1, var2);
            double var5 = var2.cursorX + var2.hitboxOffsetX;
            double var7 = var2.cursorY + var2.hitboxOffsetY;
            String var9 = this.resolveEditorMenuFilePopupHoveredHitboxId(var2, var5, var7);
            this.updateEditorMenuFilePopupActionIcons(var1, var2, var9);
            this.refreshCursorVisualState(var1, var2, var5, var7);
         } finally {
            var2.menuFilePopupTransitionTicksOverride = var4;
         }
      }
   }

   protected void updateEditorImageFilePopupComponent(EditorSession var1) {
      if (var1 != null && var1.editMode && var1.previewMode && var1.components != null && !var1.components.isEmpty()) {
         YamlConfiguration var2 = var1.components.get("editor_manage_file_popup");
         if (var2 != null) {
            YamlConfiguration var3 = this.cloneYamlConfiguration(var2);
            List var4 = this.copyBlocks(var3.getList("blocks"));
            if (var4 != null && !var4.isEmpty()) {
               Object var5 = var1.menuFilePopupVisible ? this.buildEditorMenuFilePopupChildren(var1) : new ArrayList();
               this.replaceEditorMenuFilePopupChildren(var4, (List<Map<String, Object>>)var5);
               this.applyEditorMenuFilePopupGridLayout(var4, var1);
               boolean var6 = var1.menuFilePopupVisible && this.isEditorMenuFilePopupScrollbarVisible(var1);
               this.setEditorMenuFilePopupBlockEnabledById(var4, "scrollbar_bg", var6);
               this.setEditorMenuFilePopupBlockEnabledById(var4, "scrollbar_bar", var6);
               this.setEditorMenuFilePopupBlockEnabledById(var4, "editor_files_no_files", var1.menuFilePopupVisible && var1.menuFilePopupPageKeys.isEmpty());
               int var7 = var1.menuFilePopupVisible ? this.clampEditorMenuFilePopupScrollbarY(var1.menuFilePopupScrollbarY) : 0;
               var1.menuFilePopupScrollbarY = var7;
               this.setEditorMenuFilePopupBlockYById(var4, "scrollbar_bar", var7);
               var3.set("blocks", var4);
               var1.components.put("editor_manage_file_popup", var3);
            }
         }
      }
   }

   protected List<String> resolveEditorMenuFilePopupRerenderTargetIds(EditorSession var1) {
      ArrayList var2 = new ArrayList();
      if (var1 == null) {
         return var2;
      } else {
         if (var1.renderBlocks != null) {
            for (Map var4 : var1.renderBlocks) {
               if (var4 != null) {
                  Object var5 = var4.get("id");
                  if ("popup_file_selector_root".equals(var5)) {
                     if (var4.get("__editor_target_id") instanceof String var7 && !var7.isBlank()) {
                        var2.add(var7);
                     }
                     break;
                  }
               }
            }
         }

         if (var2.isEmpty()) {
            String var8 = this.firstNonBlank(new String[]{this.runtimeElementId(var1, "popup_file_selector_root")});
            if (!var8.isBlank()) {
               var2.add(var8);
            }
         }

         return var2;
      }
   }

   protected void handleEditorMenuFilePopupClick(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.menuFilePopupVisible) {
         if (this.isEditorMenuHitboxHovered(var2, "popup_file_selector_cancel_hitbox", var3, var5)) {
            this.closeEditorMenuFilePopup(var1, var2);
         } else if (!this.isEditorMenuHitboxHovered(var2, "popup_file_selector_confirm_hitbox", var3, var5)) {
            int var7 = this.resolveEditorMenuFilePopupVisibleCount(var2);

            for (int var8 = 1; var8 <= var7; var8++) {
               String var9 = this.buildEditorMenuFilePopupEntryHitboxId(var8);
               if (this.isEditorMenuHitboxHovered(var2, var9, var3, var5)) {
                  String var10 = this.resolveEditorMenuFilePopupPageAtSlot(var2, var8);
                  if (var10 != null && !var10.isBlank()) {
                     String var11 = this.normalizePageKey(var10, "");
                     if (!var11.equalsIgnoreCase(this.normalizePageKey(var2.menuFilePopupSelectedPage, ""))) {
                        var2.menuFilePopupSelectedPage = var11;
                        this.rerenderEditorMenuFilePopup(var1, var2);
                     }

                     return;
                  }

                  return;
               }
            }

            if (this.isEditorMenuFilePopupScrollbarVisible(var2)) {
               if (this.isEditorMenuHitboxHovered(var2, "scrollbar_bar", var3, var5)) {
                  EditorRect var21 = this.getEditorMenuHitboxBounds(var2, "scrollbar_bar");
                  if (var21 != null) {
                     double var23 = this.toLogicalCursorY(var2, var5);
                     var2.menuFilePopupScrollbarDragActive = true;
                     var2.menuFilePopupScrollbarDragOffsetY = var23 - var21.y;
                  }
               } else {
                  if (this.isEditorMenuHitboxHovered(var2, "scrollbar_bg", var3, var5)) {
                     EditorRect var20 = this.getEditorMenuHitboxBounds(var2, "scrollbar_bg");
                     EditorRect var22 = this.getEditorMenuHitboxBounds(var2, "scrollbar_bar");
                     if (var20 == null) {
                        return;
                     }

                     double var24 = this.toLogicalCursorY(var2, var5);
                     double var12 = var22 == null ? 0.0 : var22.height / 2.0;
                     double var14 = var24 - var20.y - var12;
                     int var16 = this.clampEditorMenuFilePopupScrollbarY((int)Math.round(var14));
                     int var17 = this.resolveEditorMenuFilePopupOffsetFromScrollbarY(var2, (double)var16);
                     boolean var18 = var17 != var2.menuFilePopupScrollOffset;
                     boolean var19 = var16 != var2.menuFilePopupScrollbarY;
                     var2.menuFilePopupScrollbarDragActive = true;
                     var2.menuFilePopupScrollbarDragOffsetY = var24 - var20.y - (double)var16;
                     if (var18 || var19) {
                        var2.menuFilePopupScrollbarY = var16;
                        if (var18) {
                           var2.menuFilePopupScrollOffset = var17;
                        }

                        this.rerenderEditorMenuFilePopup(var1, var2);
                     }
                  }
               }
            }
         } else if (this.hasEditorMenuFilePopupSelection(var2)) {
            this.handleEditorMenuFilePopupConfirm(var1, var2);
         }
      }
   }

   protected boolean handleEditorMenuFilePopupConfirm(Player var1, EditorSession var2) {
      if (var1 == null || var2 == null || !var2.menuFilePopupVisible) {
         return false;
      } else if (this.isEditorImageFilePopupMode(var2)) {
         return this.handleEditorImageFilePopupConfirm(var1, var2);
      } else {
         String var3 = this.normalizePageKey(var2.menuFilePopupSelectedPage, "");
         if (var3.isBlank()) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>No file selected.</yellow>"));
            return false;
         } else {
            boolean var4 = var2.menuFilePopupEditAction ? this.openGui(var1, var3, true, true) : this.openGui(var1, var3, false, true);
            if (!var4) {
               return false;
            } else {
               if (var2.menuFilePopupEditAction) {
                  this.playEditorSfx(var1, "editor-opened");
               }

               return true;
            }
         }
      }
   }

   protected boolean handleEditorImageFilePopupConfirm(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && this.isEditorImageFilePopupMode(var2)) {
         String var3 = this.normalizePageKey(var2.menuFilePopupSelectedPage, "");
         if (var3.isBlank()) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>No image selected.</yellow>"));
            return false;
         } else {
            String var4 = this.firstNonBlank(new String[]{var2.menuFilePopupCommands.get(var3), var3});
            UiImageAtlasService.GeneratedImage var5 = this.plugin == null ? null : this.plugin.resolveGeneratedUiImageForEditor(var4);
            if (var5 == null) {
               this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Image not found in contents/images: <white>" + var4 + "</white>.</yellow>"));
               return false;
            } else {
               boolean var6 = this.insertTemporaryImageElement(var1, var5.name(), var5.width(), var5.height(), var5.glyphMatrix());
               if (!var6) {
                  this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Unable to add image in the current editor session.</yellow>"));
                  return false;
               } else {
                  this.closeEditorMenuFilePopup(var1, var2);
                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   protected String resolveEditorMenuFilePopupHoveredHitboxId(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.menuFilePopupVisible) {
         if (this.isEditorMenuHitboxHovered(var1, "popup_file_selector_cancel_hitbox", var2, var4)) {
            return "popup_file_selector_cancel_hitbox";
         } else if (this.hasEditorMenuFilePopupSelection(var1) && this.isEditorMenuHitboxHovered(var1, "popup_file_selector_confirm_hitbox", var2, var4)) {
            return "popup_file_selector_confirm_hitbox";
         } else {
            int var6 = this.resolveEditorMenuFilePopupVisibleCount(var1);

            for (int var7 = 1; var7 <= var6; var7++) {
               String var8 = this.buildEditorMenuFilePopupEntryHitboxId(var7);
               if (this.isEditorMenuHitboxHovered(var1, var8, var2, var4)) {
                  return var8;
               }
            }

            if (!this.isEditorMenuFilePopupScrollbarVisible(var1)) {
               return null;
            } else if (this.isEditorMenuHitboxHovered(var1, "scrollbar_bar", var2, var4)) {
               return "scrollbar_bar";
            } else {
               return this.isEditorMenuHitboxHovered(var1, "scrollbar_bg", var2, var4) ? "scrollbar_bg" : null;
            }
         }
      } else {
         return null;
      }
   }

   protected void handleEditorMenuFilePopupCursorMove(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.menuFilePopupVisible) {
         if (var2.menuFilePopupScrollbarDragActive) {
            if (this.isLeftHeld(var2) && this.isEditorMenuFilePopupScrollbarVisible(var2)) {
               EditorRect var7 = this.getEditorMenuHitboxBounds(var2, "scrollbar_bg");
               if (var7 == null) {
                  var2.menuFilePopupScrollbarDragActive = false;
               } else {
                  double var8 = this.toLogicalCursorY(var2, var5);
                  double var10 = var8 - var7.y - var2.menuFilePopupScrollbarDragOffsetY;
                  int var12 = this.clampEditorMenuFilePopupScrollbarY((int)Math.round(var10));
                  int var13 = this.resolveEditorMenuFilePopupOffsetFromScrollbarY(var2, (double)var12);
                  boolean var14 = var13 != var2.menuFilePopupScrollOffset;
                  boolean var15 = var12 != var2.menuFilePopupScrollbarY;
                  if (var14 || var15) {
                     var2.menuFilePopupScrollbarY = var12;
                     if (var14) {
                        var2.menuFilePopupScrollOffset = var13;
                     }

                     this.rerenderEditorMenuFilePopup(var1, var2);
                  }
               }
            } else {
               var2.menuFilePopupScrollbarDragActive = false;
               var2.menuFilePopupScrollbarDragOffsetY = 0.0;
            }
         }
      } else {
         if (var2 != null) {
            var2.menuFilePopupScrollbarDragActive = false;
         }
      }
   }

   @Override
   protected int resolveEditorHudTransitionTicks(EditorSession var1) {
      return var1 != null && var1.menuFilePopupVisible && this.isEditorMenuFilePopupRenderContext(var1) && var1.menuFilePopupTransitionTicksOverride >= 0
         ? var1.menuFilePopupTransitionTicksOverride
         : super.resolveEditorHudTransitionTicks(var1);
   }

   @Override
   protected boolean handleEditorFileDropdownAction(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         return switch (var3) {
            case "dropdown_file_save" -> this.hasCurrentPageName(var2) ? this.saveEditorInPlace(var1) : this.openSavePopup(var1, var2);
            case "dropdown_file_save_as" -> this.openSavePopup(var1, var2);
            case "dropdown_file_close", "dropdown_file_close_all" -> this.closeEditorAndOpenMenu(var1);
            case "dropdown_file_new" -> this.createNewUiInEditor(var1, var2);
            case "dropdown_file_open" -> {
               boolean var6 = this.closeEditorAndOpenMenu(var1);
               if (var6) {
                  EditorSession var7 = this.editorSessions.get(var1.getUniqueId());
                  this.openEditorMenuFilePopup(var1, var7, false);
               }

               yield var6;
            }
            default -> false;
         };
      } else {
         return false;
      }
   }

   @Override
   protected boolean handleEditorEditDropdownAction(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         return switch (var3) {
            case "dropdown_edit_undo" -> this.performEditorHistoryUndo(var1, var2);
            case "dropdown_edit_redo" -> this.performEditorHistoryRedo(var1, var2);
            case "dropdown_edit_cut" -> this.handleEditorEditCut(var1, var2);
            case "dropdown_edit_copy" -> this.handleEditorEditCopy(var1, var2);
            case "dropdown_edit_paste" -> this.handleEditorEditPaste(var1, var2);
            case "dropdown_edit_preferences" -> this.openPreferencesPopup(var1, var2);
            case "dropdown_edit_shortcuts" -> true;
            default -> false;
         };
      } else {
         return false;
      }
   }

   @Override
   protected boolean handleEditorSelectionDropdownAction(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         return switch (var3) {
            case "dropdown_selection_all" -> this.handleEditorSelectionSelectAll(var1, var2);
            case "dropdown_selection_none" -> this.handleEditorSelectionClear(var1, var2);
            case "dropdown_selection_reverse" -> this.handleEditorSelectionReverse(var1, var2);
            case "dropdown_selection_hide" -> this.handleEditorSelectionVisibility(var1, var2, false);
            case "dropdown_selection_unhide" -> this.handleEditorSelectionVisibility(var1, var2, true);
            case "dropdown_selection_block" -> this.handleEditorSelectionLock(var1, var2, true);
            case "dropdown_selection_unblock" -> this.handleEditorSelectionLock(var1, var2, false);
            default -> false;
         };
      } else {
         return false;
      }
   }

   @Override
   protected boolean handleEditorLayerDropdownAction(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         return switch (var3) {
            case "dropdown_layer_newblock" -> this.handleEditorSelectionNewBlock(var1, var2);
            case "dropdown_layer_newtext" -> this.handleEditorSelectionNewText(var1, var2);
            case "dropdown_layer_newitem" -> this.handleEditorSelectionNewItem(var1, var2);
            case "dropdown_layer_deletelayer" -> this.handleEditorSelectionDeleteLayer(var1, var2);
            case "dropdown_layer_movedown" -> this.handleEditorSelectionMoveLayer(var1, var2, false);
            case "dropdown_layer_moveup" -> this.handleEditorSelectionMoveLayer(var1, var2, true);
            default -> false;
         };
      } else {
         return false;
      }
   }

   @Override
   protected boolean handleEditorWindowDropdownAction(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         return switch (var3) {
            case "dropdown_window_hidetools" -> this.toggleEditorShellBlockGroupEnabled(var1, var2, List.of("left_sidebar", "right_sidebar"));
            case "dropdown_window_hidepages" -> this.toggleEditorShellBlockGroupEnabled(var1, var2, List.of("active_pages"));
            case "dropdown_window_hidefooter" -> this.toggleEditorShellBlockGroupEnabled(var1, var2, List.of("footer"));
            default -> false;
         };
      } else {
         return false;
      }
   }

   protected boolean toggleEditorShellBlockGroupEnabled(Player var1, EditorSession var2, List<String> var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isEmpty()) {
         boolean var4 = false;
         boolean var5 = false;

         for (Object var7_raw : var3) {
            String var7 = var7_raw != null ? var7_raw.toString() : null;
            String var8 = this.firstNonBlank(new String[]{var7});
            if (!var8.isBlank()) {
               Boolean var9 = this.findShellBlockEnabledById(var2.editorShellTemplateBlocks, var8);
               if (var9 == null) {
                  var9 = this.findShellBlockEnabledById(var2.shellBlocks, var8);
               }

               if (var9 != null) {
                  var5 = true;
                  if (var9) {
                     var4 = true;
                  }
               }
            }
         }

         if (!var5) {
            return true;
         } else {
            boolean var11 = !var4;
            boolean var12 = false;

            for (Object var15_raw : var3) {
               String var15 = var15_raw != null ? var15_raw.toString() : null;
               String var10 = this.firstNonBlank(new String[]{var15});
               if (!var10.isBlank()) {
                  var12 |= this.setShellBlockEnabledById(var2.editorShellTemplateBlocks, var10, var11);
                  var12 |= this.setShellBlockEnabledById(var2.shellBlocks, var10, var11);
               }
            }

            if (!var12) {
               return true;
            } else {
               this.rerenderEditorShellAfterWindowToggle(var1, var2);
               return true;
            }
         }
      } else {
         return false;
      }
   }

   protected void rerenderEditorShellAfterWindowToggle(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         List var3 = var2.shellBlocks == null ? Collections.emptyList() : var2.shellBlocks;
         LinkedHashSet var4 = this.collectShellLogicalIds(var3);
         List var5 = this.resolveOpenEditorPagesForPlayer(var1.getUniqueId(), var2.pageName);
         var2.openEditorPages.clear();
         var2.openEditorPages.addAll(var5);
         int var6 = Math.max(0, var2.openEditorPages.size() - 7);
         var2.activePagesScrollOffset = Math.max(0, Math.min(var6, var2.activePagesScrollOffset));
         var2.shellBlocks = this.buildEditorShellBlocksWithActivePages(
            var2.editorShellTemplateBlocks, var2.components, var2.openEditorPages, var2.activePagesScrollOffset, var2.pageName
         );
         LinkedHashSet var7 = this.collectShellLogicalIds(var2.shellBlocks);
         var4.removeAll(var7);
         LinkedHashSet var8 = new LinkedHashSet();

         for (Object var10_raw : var4) {
            String var10 = var10_raw != null ? var10_raw.toString() : null;
            if (this.isActivePagesShellLogicalId(var10)) {
               var8.add(var10);
            }

            this.clearShellRuntimeHudByLogicalId(var1, var10);
         }

         this.clearActivePagesRuntimeSlots(var1, var8);
         var2.shellCacheBuilt = false;
         var2.shellSectionCache.clear();
         var2.shellBlockMapCache.clear();
         var2.shellStaticRectCache.clear();
         var2.shellRuntimeRects.clear();
         var2.sidebarPanelVisibilityStates.clear();
         var2.activePagesRenderedSlotStates.clear();
         this.renderEditorShell(var1, var2);
         this.renderEditorToolSidebar(var1, var2);
         this.updateEditorPropertiesSidebar(var1, var2);
         this.updateEditorNavbarHover(var1, var2, var2.cursorX + var2.hitboxOffsetX, var2.cursorY + var2.hitboxOffsetY);
         this.updatePageInfoReadout(var1, var2);
         this.refreshSavePopupFields(var1, var2);
         this.syncActivePagesRenderedSlotStates(var2);
      }
   }

   protected LinkedHashSet<String> collectShellLogicalIds(List<Map<String, Object>> var1) {
      LinkedHashSet var2 = new LinkedHashSet();
      if (var1 != null && !var1.isEmpty()) {
         HashMap var3 = new HashMap();
         int var4 = 0;

         for (Map var6 : var1) {
            var4++;
            ConfigurationSection var7 = this.mapToSection(var6);
            if (var7 != null) {
               String var8 = this.firstNonBlank(new String[]{var7.getString("type"), "block"}).toLowerCase(Locale.ROOT);
               if ("block".equals(var8) || "text".equals(var8) || this.isRoundedType(var8)) {
                  String var9 = this.resolveElementId(var7, var4, var3);
                  String var10 = this.firstNonBlank(new String[]{var9});
                  if (!var10.isBlank()) {
                     var2.add(var10);
                  }
               }
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   protected void clearShellRuntimeHudByLogicalId(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = "editor_shell_" + var2;
         this.clearOutlineHud(var1, var3);
         this.removeBaseHud(var1, var3);
         this.removeRoundedParts(var1, var3);
         this.removeHudById(var1, var3);
         this.clearOutlineHud(var1, var2);
         this.removeBaseHud(var1, var2);
         this.removeRoundedParts(var1, var2);
         this.removeHudById(var1, var2);
      }
   }

   protected Boolean findShellBlockEnabledById(List<Map<String, Object>> var1, String var2) {
      if (var1 != null && !var1.isEmpty() && var2 != null && !var2.isBlank()) {
         for (Map var4 : var1) {
            if (var4 != null && !var4.isEmpty()) {
               String var5 = this.firstNonBlank(new String[]{this.stringValue(var4.get("id"))});
               if (!var5.isBlank() && var2.equalsIgnoreCase(var5)) {
                  return this.parseBooleanFlag(var4.get("enabled"), true);
               }

               Object var6 = var4.get("children");
               if (var6 instanceof List) {
                  List var7 = (List)var6;
                  if (!var7.isEmpty()) {
                     ArrayList var8 = new ArrayList();

                     for (Object var10 : var7) {
                        if (var10 instanceof Map var11) {
                           var8.add(var11);
                        }
                     }

                     Boolean var12 = this.findShellBlockEnabledById(var8, var2);
                     if (var12 != null) {
                        return var12;
                     }
                  }
               }
            }
         }

         return null;
      } else {
         return null;
      }
   }

   protected boolean setShellBlockEnabledById(List<Map<String, Object>> var1, String var2, boolean var3) {
      if (var1 != null && !var1.isEmpty() && var2 != null && !var2.isBlank()) {
         boolean var4 = false;

         for (Map var6 : var1) {
            if (var6 != null && !var6.isEmpty()) {
               String var7 = this.firstNonBlank(new String[]{this.stringValue(var6.get("id"))});
               if (!var7.isBlank() && var2.equalsIgnoreCase(var7)) {
                  boolean var8 = this.parseBooleanFlag(var6.get("enabled"), true);
                  if (var8 != var3 || !var6.containsKey("enabled")) {
                     var6.put("enabled", var3);
                     var4 = true;
                  }
               }

               Object var14 = var6.get("children");
               if (var14 instanceof List) {
                  List var9 = (List)var14;
                  if (!var9.isEmpty()) {
                     ArrayList var10 = new ArrayList();

                     for (Object var12 : var9) {
                        if (var12 instanceof Map var13) {
                           var10.add(var13);
                        }
                     }

                     if (!var10.isEmpty()) {
                        var4 |= this.setShellBlockEnabledById(var10, var2, var3);
                     }
                  }
               }
            }
         }

         return var4;
      } else {
         return false;
      }
   }

   protected boolean handleEditorSelectionNewBlock(Player var1, EditorSession var2) {
      return this.handleEditorSelectionCreateElement(var1, var2, false, false);
   }

   protected boolean handleEditorSelectionNewText(Player var1, EditorSession var2) {
      return this.handleEditorSelectionCreateElement(var1, var2, true, false);
   }

   protected boolean handleEditorSelectionNewItem(Player var1, EditorSession var2) {
      return this.handleEditorSelectionCreateElement(var1, var2, false, true);
   }

   protected boolean handleEditorSelectionDeleteLayer(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         List var3 = this.getSelectedTargetIds(var2);
         List var4 = this.resolveEditorSelectedRawTargetIdentities(var2, var3);
         List var5 = this.resolveEditorSelectedRenderableRootTargetPaths(var2);
         if (var4.isEmpty() && var5.isEmpty()) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>No selected layer to delete.</gray>"));
            return true;
         } else {
            this.recordEditorMutation(var2);
            int var6 = this.removeEditorTargetsByIdentity(var2, var4);
            if (var6 <= 0 && !var5.isEmpty() && this.removeEditorTargetsByPath(var2, var5)) {
               var6 = var5.size();
            }

            if (var6 <= 0) {
               this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Failed to delete layer.</gray>"));
               return true;
            } else {
               this.clearSelectionForStructureEdit(var1, var2);
               this.rerenderEditableContentAfterDeletion(var1, var2, var5);
               this.renderLayersPanel(var1, var2);
               this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Deleted layers: <white>" + var6 + "</white>.</gray>"));
               return true;
            }
         }
      } else {
         return false;
      }
   }

   protected boolean handleEditorSelectionMoveLayer(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         List var4 = this.getSelectedTargetIds(var2);
         if (var4.isEmpty()) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>No selected layer.</gray>"));
            return true;
         } else {
            String var5 = this.firstNonBlank(new String[]{var2.selectedElementId, (String)var4.getFirst()});
            if (var5.isBlank()) {
               return true;
            } else {
               List var6 = this.collectLayerTreeRows(var2);
               if (var6.isEmpty()) {
                  return true;
               } else {
                  String var7 = this.resolveDirectLayerParentTargetId(var5);
                  List var8 = this.collectDirectSiblingTargetOrder(var6, var7);
                  int var9 = var8.indexOf(var5);
                  if (var9 < 0) {
                     return true;
                  } else {
                     int var10 = var3 ? var9 - 1 : var9 + 1;
                     if (var10 >= 0 && var10 < var8.size()) {
                        ArrayList var11 = new ArrayList(var8);
                        var11.remove(var9);
                        var11.add(var10, var5);
                        Map var12 = this.captureRawTargetsById(var2, var11);
                        if (var12.isEmpty()) {
                           return true;
                        } else {
                           this.recordEditorMutation(var2);
                           boolean var13 = this.applySiblingLayerOrderingOnRawTargets(var2, var12, var11);
                           if (!var13) {
                              return true;
                           } else {
                              List var14 = this.getSelectedTargetIds(var2);
                              List var15 = this.captureSelectionTargetPaths(var2, var14);
                              this.rerenderAfterLayersStructureChange(var1, var2, var14, var15);
                              this.renderLayersPanel(var1, var2);
                              return true;
                           }
                        }
                     } else {
                        return true;
                     }
                  }
               }
            }
         }
      } else {
         return false;
      }
   }

   @Override
   protected void rerenderAfterLayersStructureChange(Player var1, EditorSession var2, List<String> var3, List<String> var4) {
      if (var1 != null && var2 != null) {
         LinkedHashSet var5 = new LinkedHashSet();

         for (Object var7_raw : var2.elements) {
            HoverElement var7 = (HoverElement)var7_raw;
            if (var7 != null) {
               String var8 = this.firstNonBlank(new String[]{var7.id});
               if (!var8.isBlank() && var5.add(var8)) {
                  this.applyRuntimeHudNoTransition(var1, var8);
               }
            }
         }

         try {
            super.rerenderAfterLayersStructureChange(var1, var2, var3, var4);
         } finally {
            int var10 = this.resolveEditorHudTransitionTicks(var2);
            if (var10 <= 0) {
               var10 = this.resolveEditorCursorInterpolationDurationTicks();
            }

            var10 = Math.max(1, var10);
            Iterator var11 = var5.iterator();

            while (true) {
               if (!var11.hasNext()) {
                  ;
               } else {
                  String var12 = (String)var11.next();
                  if (this.hasRuntimeHudNoTransition(var1, var12)) {
                     this.applyRuntimeHudTransitionTicks(var1, var12, var10, var10);
                  }
               }
            }
         }
      }
   }

   protected boolean handleEditorSelectionCreateElement(Player var1, EditorSession var2, boolean var3, boolean var4) {
      if (var1 == null || var2 == null) {
         return false;
      } else if (var3 && var4) {
         return false;
      } else {
         if (var2.rawBlocks == null) {
            var2.rawBlocks = new ArrayList<>();
         }

         double var5 = var2.previewViewport == null ? 1920.0 : var2.previewViewport.pageWidth;
         double var7 = var2.previewViewport == null ? 1080.0 : var2.previewViewport.pageHeight;
         double var9 = 250.0;
         double var11 = 250.0;
         double var13 = var5 / 2.0 - var9 / 2.0;
         double var15 = var7 / 2.0 - var11 / 2.0;
         double var17 = this.resolveSelectionCreateLayer(var2);
         String var19 = this.buildSelectionCreatedElementId(var2, var3 ? "text" : (var4 ? "item" : "block"));
         String var20 = this.buildUniqueTargetDisplayName(var2, var3 ? "Enter text" : (var4 ? "Item" : "Block"), "");
         LinkedHashMap var21 = new LinkedHashMap();
         var21.put("type", var3 ? "text" : "block");
         var21.put("id", var19);
         var21.put("name", var20);
         var21.put("layer", var17);
         var21.put("opacity", 255);
         var21.put("color", "ffffff");
         if (var3) {
            var21.put("font", "default");
            var21.put("align", "left");
            var21.put("text", var20);
         } else if (var4) {
            var21.put("item", "DIAMOND");
            var21.put("unicode", "\ue67b");
         } else {
            var21.put("unicode", "\ue67b");
         }

         LinkedHashMap var22 = new LinkedHashMap();
         var22.put("x", var13);
         var22.put("y", var15);
         var21.put("position", var22);
         LinkedHashMap var23 = new LinkedHashMap();
         var23.put("width", var9);
         var23.put("height", var11);
         var21.put("size", var23);
         int var24 = this.resolveMaxPageElements();
         if (var2.rawBlocks.size() >= var24) {
            this.sendEditorPlayerMessage(
               var1,
               this.plugin != null
                  ? this.plugin.getLangMessageWithPlaceholders("gui.limit-reached", "&cReached page limit!", Map.of())
                  : MM.deserialize("<yellow>Page element limit reached (" + var24 + ").</yellow>")
            );
            return false;
         } else {
            this.recordEditorMutation(var2);
            int var25 = var2.rawBlocks.size();
            var2.rawBlocks.add(var21);
            String var26 = String.valueOf(var25);
            var2.renderBlocks = this.resolveRenderableBlocks(var2.rawBlocks, var2.components);
            this.pruneEditorLayerStateTargets(var2);
            this.clearSelectionForStructureEdit(var1, var2);
            this.rerenderEditableContentForTargets(var1, var2, List.of(var26), true, false);
            String var27 = this.firstNonBlank(new String[]{this.findTargetIdByPath(var2, var26), var26});
            var2.selectedElementId = var27;
            var2.additionalSelectedElementIds.clear();
            var2.selectionOutlineVisible = true;
            this.normalizeSelectionState(var2);
            this.rerenderEditableSelection(var1, var2);
            this.renderLayersPanel(var1, var2);
            this.updatePageInfoReadout(var1, var2);
            return true;
         }
      }
   }

   protected double resolveSelectionCreateLayer(EditorSession var1) {
      if (var1 == null) {
         return 1.0;
      } else {
         double var2 = Double.NEGATIVE_INFINITY;
         List var4 = this.getSelectedTargetIds(var1);
         if (var4 != null && !var4.isEmpty()) {
            for (Object var6_raw : var4) {
               String var6 = var6_raw != null ? var6_raw.toString() : null;
               if (var6 != null && !var6.isBlank()) {
                  var2 = Math.max(var2, this.readTargetLayer(var1, var6));
               }
            }
         }

         if (!Double.isFinite(var2)) {
            for (String var8 : this.resolveAllSelectionTargetIds(var1)) {
               if (var8 != null && !var8.isBlank()) {
                  var2 = Math.max(var2, this.readTargetLayer(var1, var8));
               }
            }
         }

         return !Double.isFinite(var2) ? 1.0 : var2 + 1.0;
      }
   }

   @Override
   protected String buildSelectionCreatedElementId(EditorSession var1, String var2) {
      String var3 = this.firstNonBlank(new String[]{var2}).trim().toLowerCase(Locale.ROOT);
      if (var3.isBlank()) {
         var3 = "block";
      }

      String var4 = var3;

      for (int var5 = 1; this.isReservedEditorTargetId(var1, var4, "") || this.hasConflictingContentTargetId(var1, var4, ""); var5++) {
         var4 = var3 + "_" + var5;
      }

      return var4;
   }

   protected boolean handleEditorSelectionSelectAll(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         List var3 = this.resolveAllSelectionTargetIds(var2);
         if (var3.isEmpty()) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>No elements to select.</gray>"));
            return true;
         } else {
            this.applySelectionTargetIds(var1, var2, var3);
            this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Selected elements: <white>" + var3.size() + "</white>.</gray>"));
            return true;
         }
      } else {
         return false;
      }
   }

   protected boolean handleEditorSelectionClear(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         this.clearSelectionState(var1, var2);
         this.renderLayersPanel(var1, var2);
         this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Odznaczono wszystkie elementy.</gray>"));
         return true;
      } else {
         return false;
      }
   }

   protected boolean handleEditorSelectionReverse(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         List var3 = this.resolveAllSelectionTargetIds(var2);
         if (var3.isEmpty()) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>No elements to select.</gray>"));
            return true;
         } else {
            LinkedHashSet var4 = new LinkedHashSet<>(this.getSelectedTargetIds(var2));
            ArrayList var5 = new ArrayList();

            for (Object var7_raw : var3) {
               String var7 = var7_raw != null ? var7_raw.toString() : null;
               if (!var4.contains(var7)) {
                  var5.add(var7);
               }
            }

            this.applySelectionTargetIds(var1, var2, var5);
            this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Selection inverted.</gray>"));
            return true;
         }
      } else {
         return false;
      }
   }

   protected boolean handleEditorSelectionVisibility(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         List var4 = this.getSelectedTargetIds(var2);
         if (var4.isEmpty()) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>No selection.</gray>"));
            return true;
         } else {
            LinkedHashSet var5 = new LinkedHashSet();

            for (Object var7_raw : var4) {
               String var7 = var7_raw != null ? var7_raw.toString() : null;
               var5.addAll(this.resolveVisibilityCascadeTargetIds(var2, var7));
            }

            boolean var10 = false;

            for (Object var8_raw : var5) {
               String var8 = var8_raw != null ? var8_raw.toString() : null;
               boolean var9 = var2.hiddenLayerTargetIds.contains(var8);
               if (var3 && var9) {
                  var10 = true;
                  break;
               }

               if (!var3 && !var9) {
                  var10 = true;
                  break;
               }
            }

            if (!var10) {
               return true;
            } else {
               this.recordEditorMutation(var2);
               if (var3) {
                  var2.hiddenLayerTargetIds.removeAll(var5);
               } else {
                  var2.hiddenLayerTargetIds.addAll(var5);
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
               return true;
            }
         }
      } else {
         return false;
      }
   }

   protected boolean handleEditorSelectionLock(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         List var4 = this.getSelectedTargetIds(var2);
         if (var4.isEmpty()) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>No selection.</gray>"));
            return true;
         } else {
            boolean var5 = false;

            for (Object var7_raw : var4) {
               String var7 = var7_raw != null ? var7_raw.toString() : null;
               boolean var8 = var2.lockedLayerTargetIds.contains(var7);
               if (var3 != var8) {
                  var5 = true;
                  break;
               }
            }

            if (!var5) {
               return true;
            } else {
               this.recordEditorMutation(var2);

               for (Object var10_raw : var4) {
                  String var10 = var10_raw != null ? var10_raw.toString() : null;
                  if (var3) {
                     var2.lockedLayerTargetIds.add(var10);
                  } else {
                     var2.lockedLayerTargetIds.remove(var10);
                  }
               }

               if (var3 && var4.contains(var2.hoveredElementId)) {
                  this.updateHoveredHighlight(var1, var2, var2.hoveredElementId, null);
                  var2.hoveredElementId = null;
               }

               this.refreshSelectionOutlineColorOnly(var1, var2);
               this.updateEditorPropertiesSidebar(var1, var2);
               this.renderLayersPanel(var1, var2);
               return true;
            }
         }
      } else {
         return false;
      }
   }

   protected List<String> resolveAllSelectionTargetIds(EditorSession var1) {
      if (var1 == null) {
         return Collections.emptyList();
      } else {
         var1.renderBlocks = this.resolveRenderableBlocks(var1.rawBlocks, var1.components);
         LinkedHashSet var2 = new LinkedHashSet();
         if (var1.renderBlocks != null && !var1.renderBlocks.isEmpty()) {
            HashMap var3 = new HashMap();
            int var4 = 0;

            for (Map var6 : var1.renderBlocks) {
               var4++;
               if (var6 != null && !var6.isEmpty()) {
                  String var7 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var6, "type")), "block"}).toLowerCase(Locale.ROOT);
                  if (this.isRenderableBlockType(var7)) {
                     String var8 = this.resolveElementId(var6, var4, var3);
                     String var9 = this.runtimeElementId(var1, var8);
                     String var10 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var6, "__editor_target_id")), var9});
                     if (!var10.isBlank()) {
                        var2.add(var10);
                     }
                  }
               }
            }

            return new ArrayList<>(var2);
         } else {
            return new ArrayList<>(var2);
         }
      }
   }

   protected void applySelectionTargetIds(Player var1, EditorSession var2, List<String> var3) {
      if (var1 != null && var2 != null) {
         this.finishLayersReorderDrag(var1, var2, false);
         this.stopCursorToolDrag(var2);
         this.stopMarqueeSelection(var1, var2);
         this.resetSidebarFieldDrag(var1, var2);
         this.clearEditorOverlaysOnly(var1, var2);
         var2.activeHandle = null;
         var2.resizeAnchorValid = false;
         var2.handlesCollapsed = false;
         var2.hoveredHandle = null;
         var2.selectedElementId = null;
         var2.additionalSelectedElementIds.clear();
         if (var3 != null) {
            for (Object var5_raw : var3) {
               String var5 = var5_raw != null ? var5_raw.toString() : null;
               this.appendSelectionTarget(var2, var5);
            }
         }

         this.normalizeSelectionState(var2);
         var2.selectionOutlineVisible = var2.selectedElementId != null;
         if (var2.selectionOutlineVisible) {
            this.showSelectionOverlay(var1, var2);
         }

         this.updateEditorPropertiesSidebar(var1, var2);
         this.renderLayersPanel(var1, var2);
      }
   }

   protected boolean handleEditorEditCopy(Player var1, EditorSession var2) {
      return this.handleEditorEditCopy(var1, var2, null);
   }

   protected boolean handleEditorEditCopy(Player var1, EditorSession var2, List<String> var3) {
      if (var1 != null && var2 != null) {
         List var4 = this.resolveEditorCutCopyTargetPaths(var2, var3);
         if (var4.isEmpty()) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>No selected element to copy.</gray>"));
            return true;
         } else {
            List var5 = this.copyEditorTargetsByPath(var2, var4);
            if (var5.isEmpty()) {
               this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Failed to copy selection.</gray>"));
               return true;
            } else {
               var2.editClipboardBlocks.clear();
               var2.editClipboardBlocks.addAll(var5);
               this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Copied elements: <white>" + var5.size() + "</white>.</gray>"));
               return true;
            }
         }
      } else {
         return false;
      }
   }

   protected boolean handleEditorEditCut(Player var1, EditorSession var2) {
      return this.handleEditorEditCut(var1, var2, null);
   }

   protected boolean handleEditorEditCut(Player var1, EditorSession var2, List<String> var3) {
      if (var1 != null && var2 != null) {
         List var4 = this.resolveEditorCutCopyTargetPaths(var2, var3);
         if (var4.isEmpty()) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>No selected element to cut.</gray>"));
            return true;
         } else {
            List var5 = this.copyEditorTargetsByPath(var2, var4);
            if (var5.isEmpty()) {
               this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Failed to cut selection.</gray>"));
               return true;
            } else {
               this.recordEditorMutation(var2);
               if (!this.removeEditorTargetsByPath(var2, var4)) {
                  this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Failed to remove selection.</gray>"));
                  return true;
               } else {
                  var2.editClipboardBlocks.clear();
                  var2.editClipboardBlocks.addAll(var5);
                  this.clearSelectionForStructureEdit(var1, var2);
                  this.rerenderEditableContentAfterDeletion(var1, var2, var4);
                  this.renderLayersPanel(var1, var2);
                  this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Wycieto elementy: <white>" + var5.size() + "</white>.</gray>"));
                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   protected boolean handleEditorEditPaste(Player var1, EditorSession var2) {
      if (var1 == null || var2 == null) {
         return false;
      } else if (var2.editClipboardBlocks != null && !var2.editClipboardBlocks.isEmpty()) {
         EditorRect var3 = this.getSelectedBounds(var2);
         double var4;
         double var6;
         if (var3 != null) {
            var4 = var3.x + var3.width / 2.0;
            var6 = var3.y + var3.height / 2.0;
         } else {
            double var8 = var2.previewViewport == null ? 1920.0 : var2.previewViewport.pageWidth;
            double var10 = var2.previewViewport == null ? 1080.0 : var2.previewViewport.pageHeight;
            var4 = var8 / 2.0;
            var6 = var10 / 2.0;
         }

         this.recordEditorMutation(var2);
         if (var2.rawBlocks == null) {
            var2.rawBlocks = new ArrayList<>();
         }

         int var24 = this.resolveMaxPageElements();
         if (var2.rawBlocks.size() >= var24) {
            this.sendEditorPlayerMessage(
               var1,
               this.plugin != null
                  ? this.plugin.getLangMessageWithPlaceholders("gui.limit-reached", "&cReached page limit!", Map.of())
                  : MM.deserialize("<yellow>Page element limit reached (" + var24 + ").</yellow>")
            );
            return true;
         } else {
            int var9 = var2.rawBlocks.size();
            int var25 = 0;

            for (Map var12 : var2.editClipboardBlocks) {
               if (var12 != null && !var12.isEmpty()) {
                  var2.rawBlocks.add(this.deepCopyMap(var12));
                  var25++;
               }
            }

            if (var25 <= 0) {
               this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>No data to paste.</gray>"));
               return true;
            } else {
               ArrayList var26 = new ArrayList(var25);

               for (int var27 = 0; var27 < var25; var27++) {
                  var26.add(String.valueOf(var9 + var27));
               }

               var2.renderBlocks = this.resolveRenderableBlocks(var2.rawBlocks, var2.components);
               this.pruneEditorLayerStateTargets(var2);
               EditorRect var28 = null;

               for (Object var14_raw : var26) {
                  String var14 = var14_raw != null ? var14_raw.toString() : null;
                  var28 = this.mergeBounds(var28, this.getTargetBounds(var2, var14));
               }

               if (var28 != null) {
                  double var29 = var28.x + var28.width / 2.0;
                  double var15 = var28.y + var28.height / 2.0;
                  double var17 = var4 - var29;
                  double var19 = var6 - var15;
                  if (Math.abs(var17) > 1.0E-4 || Math.abs(var19) > 1.0E-4) {
                     for (Object var22_raw : var26) {
                        String var22 = var22_raw != null ? var22_raw.toString() : null;
                        EditorRect var23 = this.getTargetBounds(var2, var22);
                        if (var23 != null) {
                           this.applyBoundsToTarget(var2, var22, new EditorRect(var23.x + var17, var23.y + var19, var23.width, var23.height));
                        }
                     }

                     var2.renderBlocks = this.resolveRenderableBlocks(var2.rawBlocks, var2.components);
                     this.pruneEditorLayerStateTargets(var2);
                  }
               }

               this.clearSelectionForStructureEdit(var1, var2);
               this.rerenderEditableContentForTargets(var1, var2, var26, true, false);
               ArrayList var30 = new ArrayList(var26.size());

               for (Object var33_raw : var26) {
                  String var33 = var33_raw != null ? var33_raw.toString() : null;
                  String var16 = this.firstNonBlank(new String[]{this.findTargetIdByPath(var2, var33), var33});
                  if (!var16.isBlank() && !var30.contains(var16)) {
                     var30.add(var16);
                  }
               }

               if (var30.isEmpty()) {
                  var30.addAll(var26);
               }

               var2.selectedElementId = (String)var30.getFirst();
               var2.additionalSelectedElementIds.clear();

               for (int var32 = 1; var32 < var30.size(); var32++) {
                  var2.additionalSelectedElementIds.add((String)var30.get(var32));
               }

               var2.selectionOutlineVisible = true;
               this.normalizeSelectionState(var2);
               var2.pasteSelectionClickGuardUntilTick = (long)Bukkit.getCurrentTick() + 4L;
               this.rerenderEditableSelection(var1, var2);
               this.renderLayersPanel(var1, var2);
               this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Wklejono elementy: <white>" + var30.size() + "</white>.</gray>"));
               return true;
            }
         }
      } else {
         this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Schowek jest pusty.</gray>"));
         return true;
      }
   }

   @Override
   protected void rerenderEditableContentForTargets(Player var1, EditorSession var2, List<String> var3, boolean var4) {
      this.rerenderEditableContentForTargets(var1, var2, var3, var4, true);
   }

   @Override
   protected void rerenderEditableContentForTargets(Player var1, EditorSession var2, List<String> var3, boolean var4, boolean var5) {
      if (var1 != null && var2 != null) {
         LinkedHashSet var6 = new LinkedHashSet();
         LinkedHashSet var7 = new LinkedHashSet();
         if (var3 != null) {
            for (Object var9_raw : var3) {
               String var9 = var9_raw != null ? var9_raw.toString() : null;
               String var10 = this.firstNonBlank(new String[]{var9});
               if (!var10.isBlank()) {
                  var6.add(var10);
                  String var11 = this.resolveEditorTargetPath(var2, var10);
                  if (!var11.isBlank()) {
                     var7.add(var11);
                  }
               }
            }
         }

         if (var6.isEmpty() && var7.isEmpty()) {
            this.rerenderEditableContent(var1, var2);
         } else {
            if (!var4) {
               var2.renderBlocks = this.resolveRenderableBlocks(var2.rawBlocks, var2.components);
            }

            ArrayList var24 = new ArrayList<>(var2.elements);
            HashMap var25 = new HashMap();

            for (Object var28_raw : var24) {
               HoverElement var28 = (HoverElement)var28_raw;
               if (var28 != null && var28.id != null && !var28.id.isBlank()) {
                  var25.put(var28.id, var28);
               }
            }

            HashSet var27 = new HashSet();
            ArrayList var29 = new ArrayList();
            HashMap var12 = new HashMap();
            int var13 = 0;

            for (Map var15 : var2.renderBlocks) {
               var13++;
               ConfigurationSection var16 = this.mapToSection(var15);
               if (var16 != null) {
                  String var17 = this.firstNonBlank(new String[]{var16.getString("type"), "block"}).toLowerCase(Locale.ROOT);
                  if (this.isRenderableBlockType(var17)) {
                     String var18 = this.resolveElementId(var16, var13, var12);
                     String var19 = this.runtimeElementId(var2, var18);
                     String var20 = this.firstNonBlank(new String[]{var16.getString("__editor_target_id"), var19});
                     String var21 = this.firstNonBlank(new String[]{var16.getString("__editor_target_path")});
                     if (!this.isTargetVisible(var2, var20)) {
                        HoverElement var32 = (HoverElement)var25.get(var19);
                        if (var32 != null) {
                           this.removeRenderedElementHud(var1, var32);
                        }
                     } else {
                        var27.add(var19);
                        boolean var22 = var6.contains(var20) || this.isDescendantOfAnyTargetPath(var21, var7);
                        HoverElement var23 = (HoverElement)var25.get(var19);
                        if (!var22 && var23 != null) {
                           var29.add(var23);
                        } else {
                           var29.add(this.renderResolvedElement(var1, var2, var16, var19, var17));
                        }
                     }
                  }
               }
            }

            for (Object var31_raw : var24) {
               HoverElement var31 = (HoverElement)var31_raw;
               if (var31 != null && var31.id != null && !var31.id.isBlank() && !var27.contains(var31.id)) {
                  this.removeRenderedElementHud(var1, var31);
               }
            }

            var2.elements.clear();
            var2.elements.addAll(var29);
            this.normalizeSelectionState(var2);
            if (var2.previewMode) {
               this.renderEditorTransparencyOverlay(var1, var2);
            }

            this.clearEditorOverlaysOnly(var1, var2);
            if (var5) {
               this.updateEditorPropertiesSidebar(var1, var2);
               this.updatePageInfoReadout(var1, var2);
            }
         }
      }
   }

   protected void rerenderEditableContentAfterDeletion(Player var1, EditorSession var2, List<String> var3) {
      if (var1 != null && var2 != null) {
         boolean var4 = var2.suppressHudTransitionDuringStructureDeletion;
         var2.suppressHudTransitionDuringStructureDeletion = true;
         List var5 = this.normalizeRootTargetPaths(var3);
         LinkedHashSet var6 = new LinkedHashSet();

         for (Object var8_raw : var2.elements) {
            HoverElement var8 = (HoverElement)var8_raw;
            if (var8 != null) {
               String var9 = this.firstNonBlank(new String[]{var8.id});
               if (!var9.isBlank() && var6.add(var9) && this.isElementAffectedByDeletedRoots(var8, var5)) {
                  this.applyRuntimeHudNoTransition(var1, var9);
               }
            }
         }

         try {
            this.rerenderEditableContent(var1, var2);
         } finally {
            var2.suppressHudTransitionDuringStructureDeletion = var4;
            int var12 = this.resolveEditorHudTransitionTicks(var2);
            if (var12 <= 0) {
               var12 = this.resolveEditorCursorInterpolationDurationTicks();
            }

            var12 = Math.max(1, var12);
            var6.clear();
            Iterator var13 = var2.elements.iterator();

            while (true) {
               if (!var13.hasNext()) {
                  ;
               } else {
                  HoverElement var14 = (HoverElement)var13.next();
                  if (var14 != null) {
                     String var15 = this.firstNonBlank(new String[]{var14.id});
                     if (!var15.isBlank() && var6.add(var15) && this.hasRuntimeHudNoTransition(var1, var15)) {
                        this.applyRuntimeHudTransitionTicks(var1, var15, var12, var12);
                     }
                  }
               }
            }
         }
      }
   }

   protected boolean isElementAffectedByDeletedRoots(HoverElement var1, List<String> var2) {
      if (var1 == null) {
         return false;
      } else if (var2 != null && !var2.isEmpty()) {
         String var3 = this.firstNonBlank(new String[]{var1.targetPath});
         if (var3.isBlank()) {
            return false;
         } else {
            for (Object var5_raw : var2) {
               String var5 = var5_raw != null ? var5_raw.toString() : null;
               String var6 = this.firstNonBlank(new String[]{var5});
               if (!var6.isBlank() && (var3.equals(var6) || this.isDescendantTargetPath(var3, var6))) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return true;
      }
   }

   protected boolean hasRuntimeHudNoTransition(Player var1, String var2) {
      if (var1 == null || var2 == null || var2.isBlank()) {
         return false;
      } else if (!this.hasHudNoTransitionIfExists(var1, var2)
         && !this.hasHudNoTransitionIfExists(var1, var2 + "_outline")
         && !this.hasHudNoTransitionIfExists(var1, var2 + "_r_core")
         && !this.hasHudNoTransitionIfExists(var1, var2 + "_r_top")
         && !this.hasHudNoTransitionIfExists(var1, var2 + "_r_bottom")
         && !this.hasHudNoTransitionIfExists(var1, var2 + "_r_left")
         && !this.hasHudNoTransitionIfExists(var1, var2 + "_r_right")
         && !this.hasHudNoTransitionIfExists(var1, var2 + "_r_tl")
         && !this.hasHudNoTransitionIfExists(var1, var2 + "_r_tr")
         && !this.hasHudNoTransitionIfExists(var1, var2 + "_r_bl")
         && !this.hasHudNoTransitionIfExists(var1, var2 + "_r_br")) {
         String var3 = var2 + "_outline";
         return this.hasHudNoTransitionIfExists(var1, var3 + "_r_core")
            || this.hasHudNoTransitionIfExists(var1, var3 + "_r_top")
            || this.hasHudNoTransitionIfExists(var1, var3 + "_r_bottom")
            || this.hasHudNoTransitionIfExists(var1, var3 + "_r_left")
            || this.hasHudNoTransitionIfExists(var1, var3 + "_r_right")
            || this.hasHudNoTransitionIfExists(var1, var3 + "_r_tl")
            || this.hasHudNoTransitionIfExists(var1, var3 + "_r_tr")
            || this.hasHudNoTransitionIfExists(var1, var3 + "_r_bl")
            || this.hasHudNoTransitionIfExists(var1, var3 + "_r_br");
      } else {
         return true;
      }
   }

   protected boolean hasHudNoTransitionIfExists(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Entity var3 = this.hudService.getHud(var1, var2);
         return var3 != null && this.hudService.hasHudNoTransition(var3);
      } else {
         return false;
      }
   }

   @Override
   protected void clearSelectionForStructureEdit(Player var1, EditorSession var2) {
      if (var2 != null) {
         this.finishLayersReorderDrag(var1, var2, false);
         this.stopCursorToolDrag(var2);
         this.stopMarqueeSelection(var1, var2);
         this.resetSidebarFieldDrag(var1, var2);
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
         this.stopOpacitySliderDrag(var2, true);
         var2.colorPickerGradDragActive = false;
         var2.colorPickerHueDragActive = false;
         this.clearAlignToolbarOverlay(var1, var2);
      }
   }

   protected List<String> resolveEditorSelectedRootTargetPaths(EditorSession var1) {
      if (var1 == null) {
         return Collections.emptyList();
      } else {
         ArrayList var2 = new ArrayList();

         for (String var4 : this.getSelectedTargetIds(var1)) {
            String var5 = this.resolveEditorTargetPath(var1, var4);
            if (!var5.isBlank()) {
               var2.add(var5);
            }
         }

         return this.normalizeRootTargetPaths(var2);
      }
   }

   protected List<String> resolveEditorCutCopyTargetPaths(EditorSession var1, List<String> var2) {
      if (var1 == null) {
         return Collections.emptyList();
      } else {
         List var3 = this.resolveEditorSelectedRenderableRootTargetPaths(var1);
         List var4 = this.normalizeRootTargetPaths(var2);
         if (!var4.isEmpty()) {
            ArrayList var5 = new ArrayList();

            for (Object var7_raw : var4) {
               String var7 = var7_raw != null ? var7_raw.toString() : null;
               String var8 = this.firstNonBlank(new String[]{var7});
               if (!var8.isBlank() && this.resolveRawMapAtPath(var1.rawBlocks, var8) != null) {
                  var5.add(var8);
               }
            }

            if (!var5.isEmpty()) {
               if (!var3.isEmpty() && var3.size() > 1) {
                  LinkedHashSet var10 = new LinkedHashSet(var3);
                  boolean var11 = true;

                  for (Object var9_raw : var5) {
                     String var9 = var9_raw != null ? var9_raw.toString() : null;
                     if (!var10.contains(var9)) {
                        var11 = false;
                        break;
                     }
                  }

                  if (var11) {
                     return var3;
                  }
               }

               return var5;
            }
         }

         return var3;
      }
   }

   protected List<String> resolveEditorSelectedRenderableRootTargetPaths(EditorSession var1) {
      if (var1 == null) {
         return Collections.emptyList();
      } else {
         List var2 = this.getSelectedTargetIds(var1);
         if (var2.isEmpty()) {
            return Collections.emptyList();
         } else {
            ArrayList var3 = new ArrayList();

            for (Object var5_raw : var2) {
               String var5 = var5_raw != null ? var5_raw.toString() : null;
               String var6 = this.resolveEditorTargetPath(var1, var5);
               if (var6.isBlank() && var1.renderBlocks != null) {
                  for (Map var8 : var1.renderBlocks) {
                     if (var8 != null && !var8.isEmpty()) {
                        String var9 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var8, "__editor_target_id"))});
                        if (var5.equals(var9)) {
                           String var10 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var8, "__editor_target_path"))});
                           if (!var10.isBlank() && this.resolveRawMapAtPath(var1.rawBlocks, var10) != null) {
                              var6 = var10;
                              break;
                           }
                        }
                     }
                  }
               }

               if (!var6.isBlank() && this.resolveRawMapAtPath(var1.rawBlocks, var6) == null) {
                  var6 = "";
               }

               if (!var6.isBlank()) {
                  var3.add(var6);
               }
            }

            return this.normalizeRootTargetPaths(var3);
         }
      }
   }

   protected String resolveEditorTargetPath(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         String var4 = this.firstNonBlank(new String[]{var3 == null ? null : var3.targetPath});
         if (!var4.isBlank()) {
            return var4;
         } else {
            return this.resolveRawMapAtPath(var1.rawBlocks, var2) == null ? "" : var2;
         }
      } else {
         return "";
      }
   }

   protected List<String> resolveEditorTargetPathsByIds(EditorSession var1, List<String> var2) {
      ArrayList var3 = new ArrayList();
      if (var1 != null && var2 != null && !var2.isEmpty()) {
         for (Object var5_raw : var2) {
            String var5 = var5_raw != null ? var5_raw.toString() : null;
            String var6 = this.resolveEditorTargetPath(var1, var5);
            if (!var6.isBlank()) {
               var3.add(var6);
            }
         }

         return var3;
      } else {
         return var3;
      }
   }

   protected void restoreSelectionAfterHistorySnapshot(EditorSession var1, List<String> var2, List<String> var3) {
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
               if (!var12.isBlank() && this.isKnownTargetId(var1, var12)) {
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
      }
   }

   protected List<Map<String, Object>> copyEditorTargetsByPath(EditorSession var1, List<String> var2) {
      ArrayList var3 = new ArrayList();
      if (var1 != null && var2 != null && !var2.isEmpty()) {
         for (String var5 : this.normalizeRootTargetPaths(var2)) {
            Map var6 = this.resolveRawMapAtPath(var1.rawBlocks, var5);
            if (var6 != null && !var6.isEmpty()) {
               var3.add(this.deepCopyMap(var6));
            }
         }

         return var3;
      } else {
         return var3;
      }
   }

   protected List<GuiServiceEditorSupportA.EditorRawTargetIdentity> resolveEditorSelectedRawTargetIdentities(EditorSession var1, List<String> var2) {
      if (var1 != null && var2 != null && !var2.isEmpty()) {
         LinkedHashMap var3 = new LinkedHashMap();

         for (Object var5_raw : var2) {
            String var5 = var5_raw != null ? var5_raw.toString() : null;
            GuiServiceEditorSupportA.EditorRawTargetIdentity var6 = this.resolveEditorRawTargetIdentity(var1, var5);
            if (var6 != null) {
               String var7 = this.firstNonBlank(new String[]{var6.targetPath, var6.targetId}) + "|" + var6.logicalId + "|" + var6.displayName;
               var3.putIfAbsent(var7, var6);
            }
         }

         return new ArrayList<>(var3.values());
      } else {
         return Collections.emptyList();
      }
   }

   protected GuiServiceEditorSupportA.EditorRawTargetIdentity resolveEditorRawTargetIdentity(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = this.resolveEditorTargetPath(var1, var2);
         Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3);
         if (var4 == null) {
            var4 = this.resolveRawTargetByTargetId(var1, var2);
            if (var4 == null) {
               return null;
            }
         }

         String var5 = this.readRawTargetLogicalId(var4);
         String var6 = this.readRawTargetDisplayName(var4);
         if (!var5.isBlank() && !var6.isBlank()) {
            if (var3.isBlank()) {
               HoverElement var7 = this.findFirstByTargetId(var1, var2);
               var3 = this.firstNonBlank(new String[]{var7 == null ? null : var7.targetPath});
            }

            return new GuiServiceEditorSupportA.EditorRawTargetIdentity(var2, var3, var5, var6);
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   protected int removeEditorTargetsByIdentity(EditorSession var1, List<GuiServiceEditorSupportA.EditorRawTargetIdentity> var2) {
      if (var1 != null && var1.rawBlocks != null && !var1.rawBlocks.isEmpty() && var2 != null && !var2.isEmpty()) {
         int var3 = 0;

         for (GuiServiceEditorSupportA.EditorRawTargetIdentity var5 : var2) {
            if (var5 != null) {
               Map var6 = this.removeRawTargetByIdentity(var1.rawBlocks, var5);
               if (var6 != null) {
                  var3++;
               }
            }
         }

         return var3;
      } else {
         return 0;
      }
   }

   protected Map<String, Object> removeRawTargetByIdentity(List<Map<String, Object>> var1, GuiServiceEditorSupportA.EditorRawTargetIdentity var2) {
      if (var1 != null && !var1.isEmpty() && var2 != null) {
         if (var2.targetPath != null && !var2.targetPath.isBlank()) {
            Map var3 = this.resolveRawMapAtPath(var1, var2.targetPath);
            if (this.rawTargetMatchesIdentity(var3, var2)) {
               return this.removeRawTargetAtPath(var1, var2.targetPath);
            }
         }

         return var2.logicalId != null && !var2.logicalId.isBlank() && var2.displayName != null && !var2.displayName.isBlank()
            ? this.removeFirstRawTargetByIdAndName(var1, var2.logicalId, var2.displayName)
            : null;
      } else {
         return null;
      }
   }

   protected Map<String, Object> removeFirstRawTargetByIdAndName(List<?> var1, String var2, String var3) {
      if (var1 != null && !var1.isEmpty() && var2 != null && !var2.isBlank() && var3 != null && !var3.isBlank()) {
         for (int var4 = 0; var4 < var1.size(); var4++) {
            if (var1.get(var4) instanceof Map var6) {
               String var8 = this.readRawTargetLogicalId(var6);
               String var9 = this.readRawTargetDisplayName(var6);
               if (var2.equals(var8) && var3.equals(var9)) {
                  var1.remove(var4);
                  return var6;
               }

               if (var6.get("children") instanceof List var11) {
                  Map var12 = this.removeFirstRawTargetByIdAndName(var11, var2, var3);
                  if (var12 != null) {
                     return var12;
                  }
               }
            }
         }

         return null;
      } else {
         return null;
      }
   }

   protected boolean rawTargetMatchesIdentity(Map<String, Object> var1, GuiServiceEditorSupportA.EditorRawTargetIdentity var2) {
      return var1 != null && var2 != null
         ? var2.logicalId.equals(this.readRawTargetLogicalId(var1)) && var2.displayName.equals(this.readRawTargetDisplayName(var1))
         : false;
   }

   protected String readRawTargetLogicalId(Map<String, Object> var1) {
      return var1 != null && !var1.isEmpty()
         ? this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "id")), this.stringValue(this.readMapPathValue(var1, "params.id"))})
         : "";
   }

   protected String readRawTargetDisplayName(Map<String, Object> var1) {
      return var1 != null && !var1.isEmpty()
         ? this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "name")), this.stringValue(this.readMapPathValue(var1, "params.name"))})
         : "";
   }

   protected boolean removeEditorTargetsByPath(EditorSession var1, List<String> var2) {
      if (var1 != null && var1.rawBlocks != null && !var1.rawBlocks.isEmpty() && var2 != null && !var2.isEmpty()) {
         ArrayList<String> var3 = new ArrayList<>(this.normalizeRootTargetPaths(var2));
         if (var3.isEmpty()) {
            return false;
         } else {
            var3.sort((String var1x, String var2x) -> {
               int var3x = Integer.compare(this.targetPathDepth(var2x), this.targetPathDepth(var1x));
               if (var3x != 0) {
                  return var3x;
               } else {
                  String[] var4x = var1x.split("\\.");
                  String[] var5 = var2x.split("\\.");
                  int var6x = Math.min(var4x.length, var5.length);

                  for (int var7x = 0; var7x < var6x; var7x++) {
                     int var8 = this.parsePathIndex(var4x[var7x]);
                     int var9 = this.parsePathIndex(var5[var7x]);
                     if (var8 >= 0 && var9 >= 0 && var8 != var9) {
                        return Integer.compare(var9, var8);
                     }

                     int var10 = var5[var7x].compareTo(var4x[var7x]);
                     if (var10 != 0) {
                        return var10;
                     }
                  }

                  return var2x.compareTo(var1x);
               }
            });
            boolean var4 = false;

            for (Object var6_raw : var3) {
               String var6 = var6_raw != null ? var6_raw.toString() : null;
               Map var7 = this.removeRawTargetAtPath(var1.rawBlocks, var6);
               if (var7 != null) {
                  var4 = true;
               }
            }

            return var4;
         }
      } else {
         return false;
      }
   }

   protected Map<String, Object> removeRawTargetAtPath(List<Map<String, Object>> var1, String var2) {
      if (var1 != null && !var1.isEmpty() && var2 != null && !var2.isBlank()) {
         String[] var3 = var2.split("\\.");
         if (var3.length == 0) {
            return null;
         } else {
            Object var4 = var1;

            for (int var5 = 0; var5 < var3.length - 1; var5++) {
               String var6 = var3[var5];
               if (var4 instanceof List var7) {
                  int var8 = this.parsePathIndex(var6);
                  if (var8 < 0 || var8 >= var7.size()) {
                     return null;
                  }

                  var4 = var7.get(var8);
               } else {
                  if (!(var4 instanceof Map var13)) {
                     return null;
                  }

                  var4 = var13.get(var6);
               }
            }

            String var10 = var3[var3.length - 1];
            if (var4 instanceof List var12) {
               int var14 = this.parsePathIndex(var10);
               if (var14 >= 0 && var14 < var12.size()) {
                  Object var9 = var12.remove(var14);
                  return this.toStringObjectMap(var9);
               }

               return null;
            }

            if (var4 instanceof Map var11) {
               Object var15 = var11.remove(var10);
               return this.toStringObjectMap(var15);
            }

            return null;
         }
      } else {
         return null;
      }
   }

   protected List<String> normalizeRootTargetPaths(List<String> var1) {
      if (var1 != null && !var1.isEmpty()) {
         LinkedHashSet var2 = new LinkedHashSet();

         for (Object var4_raw : var1) {
            String var4 = var4_raw != null ? var4_raw.toString() : null;
            String var5 = this.firstNonBlank(new String[]{var4});
            if (!var5.isBlank()) {
               var2.add(var5);
            }
         }

         ArrayList<String> var10 = new ArrayList<>(var2);
         var10.sort((String var1x, String var2x) -> {
            int var3 = Integer.compare(this.targetPathDepth(var1x), this.targetPathDepth(var2x));
            return var3 != 0 ? var3 : var1x.compareTo(var2x);
         });
         ArrayList var11 = new ArrayList();

         for (Object var6_raw : var10) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            boolean var7 = false;

            for (Object var9_raw : var11) {
               String var9 = var9_raw != null ? var9_raw.toString() : null;
               if (var6.equals(var9) || this.isDescendantTargetPath(var6, var9)) {
                  var7 = true;
                  break;
               }
            }

            if (!var7) {
               var11.add(var6);
            }
         }

         return var11;
      } else {
         return Collections.emptyList();
      }
   }

   protected boolean isDescendantTargetPath(String var1, String var2) {
      return var1 != null && var2 != null && !var1.isBlank() && !var2.isBlank() ? var1.startsWith(var2 + ".children.") : false;
   }

   protected int targetPathDepth(String var1) {
      return var1 != null && !var1.isBlank() ? var1.split("\\.").length : 0;
   }

   @Override
   protected void pruneEditorLayerStateTargets(EditorSession var1) {
      if (var1 != null) {
         LinkedHashSet var2 = new LinkedHashSet();
         if (var1.renderBlocks != null) {
            for (Map var4 : var1.renderBlocks) {
               if (var4 != null && !var4.isEmpty()) {
                  String var5 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var4, "__editor_target_id"))});
                  if (!var5.isBlank()) {
                     var2.add(var5);
                  }
               }
            }
         }

         var1.hiddenLayerTargetIds.retainAll(var2);
         var1.lockedLayerTargetIds.retainAll(var2);
         var1.collapsedLayerTargetIds.retainAll(var2);
      }
   }

   @Override
   protected boolean onEditorNavbarUndoClick(Player var1, EditorSession var2) {
      return this.performEditorHistoryUndo(var1, var2);
   }

   @Override
   protected boolean onEditorNavbarRedoClick(Player var1, EditorSession var2) {
      return this.performEditorHistoryRedo(var1, var2);
   }

   @Override
   protected boolean onEditorNavbarHomeClick(Player var1, EditorSession var2) {
      boolean var3 = this.handleEditorNavbarHomeOrExport(var1, var2, false);
      if (var3 && (var2 == null || var2.pageFile == null || !this.hasCurrentPageName(var2))) {
         this.playEditorSfx(var1, var2, "home");
      }

      return var3;
   }

   @Override
   protected boolean onEditorNavbarExportClick(Player var1, EditorSession var2) {
      return this.handleEditorNavbarHomeOrExport(var1, var2, true);
   }

   @Override
   protected boolean canEditorNavbarUndo(EditorSession var1) {
      return this.hasEditorHistoryEntries(var1, var1 == null ? null : var1.pageUndoSnapshots);
   }

   @Override
   protected boolean canEditorNavbarRedo(EditorSession var1) {
      return this.hasEditorHistoryEntries(var1, var1 == null ? null : var1.pageRedoSnapshots);
   }

   @Override
   protected void recordEditorMutation(EditorSession var1) {
      this.recordEditorMutationSnapshot(var1, this.captureEditorMutationSnapshot(var1));
   }

   @Override
   protected String captureEditorMutationSnapshot(EditorSession var1) {
      return this.buildEditorHistorySnapshot(var1);
   }

   @Override
   protected void recordEditorMutationSnapshot(EditorSession var1, String var2) {
      if (var1 != null && !var1.historyApplying && var1.editMode && var1.previewMode) {
         String var3 = this.resolveEditorHistoryPageKey(var1);
         if (!var3.isBlank() && !this.isInternalEditorPageName(var3)) {
            String var4 = var2 == null ? "" : var2;
            if (!var4.isBlank()) {
               List var5 = var1.pageUndoSnapshots.computeIfAbsent(var3, var0 -> new ArrayList<>());
               if (var5.isEmpty() || !this.equalsNullable((String)var5.get(var5.size() - 1), var4)) {
                  var5.add(var4);
                  this.trimEditorHistoryList(var5);
                  var1.pageRedoSnapshots.remove(var3);
               }
            }
         }
      }
   }

   protected boolean handleEditorNavbarHomeOrExport(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         boolean var4 = var2.pageFile == null;
         if (var3 || var4 || !this.hasCurrentPageName(var2)) {
            return this.openSavePopup(var1, var2);
         } else {
            return !this.saveEditorInPlace(var1) ? false : this.closeEditorAndOpenMenu(var1);
         }
      } else {
         return false;
      }
   }

   protected String resolveEditorHistoryPageKey(EditorSession var1) {
      return var1 == null ? "" : this.normalizePageKey(var1.pageName, var1.pageName);
   }

   protected String buildEditorHistorySnapshot(EditorSession var1) {
      if (var1 == null) {
         return "";
      } else {
         YamlConfiguration var2 = new YamlConfiguration();
         var2.set("blocks", this.copyBlocks(var1.rawBlocks));
         var2.set("hidden", new ArrayList<>(var1.hiddenLayerTargetIds));
         var2.set("locked", new ArrayList<>(var1.lockedLayerTargetIds));
         var2.set("collapsed", new ArrayList<>(var1.collapsedLayerTargetIds));
         return var2.saveToString();
      }
   }

   protected void trimEditorHistoryList(List<String> var1) {
      if (var1 != null) {
         int var2 = this.resolveEditorHistoryLimit();

         while (var1.size() > var2) {
            var1.removeFirst();
         }
      }
   }

   protected int resolveEditorHistoryLimit() {
      return this.plugin == null ? 50 : Math.max(1, this.plugin.getConfig().getInt("editor.history.undo-limit", 50));
   }

   protected boolean hasEditorHistoryEntries(EditorSession var1, Map<String, List<String>> var2) {
      if (var1 != null && var2 != null) {
         String var3 = this.resolveEditorHistoryPageKey(var1);
         if (!var3.isBlank() && !this.isInternalEditorPageName(var3)) {
            List var4 = (List)var2.get(var3);
            return var4 != null && !var4.isEmpty();
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected boolean performEditorHistoryUndo(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         String var3 = this.resolveEditorHistoryPageKey(var2);
         List var4 = var2.pageUndoSnapshots.get(var3);
         if (var4 != null && !var4.isEmpty()) {
            String var5 = this.buildEditorHistorySnapshot(var2);
            String var6 = (String)var4.remove(var4.size() - 1);
            List var7 = var2.pageRedoSnapshots.computeIfAbsent(var3, var0 -> new ArrayList<>());
            if (var7.isEmpty() || !this.equalsNullable((String)var7.get(var7.size() - 1), var5)) {
               var7.add(var5);
               this.trimEditorHistoryList(var7);
            }

            if (!this.applyEditorHistorySnapshot(var1, var2, var6)) {
               if (!var7.isEmpty() && this.equalsNullable((String)var7.get(var7.size() - 1), var5)) {
                  var7.remove(var7.size() - 1);
               }

               var4.add(var6);
               this.trimEditorHistoryList(var4);
               return false;
            } else {
               this.playEditorSfx(var1, var2, "undo");
               this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Undid change.</gray>"));
               return true;
            }
         } else {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>No changes to undo.</gray>"));
            return true;
         }
      } else {
         return false;
      }
   }

   protected boolean performEditorHistoryRedo(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         String var3 = this.resolveEditorHistoryPageKey(var2);
         List var4 = var2.pageRedoSnapshots.get(var3);
         if (var4 != null && !var4.isEmpty()) {
            String var5 = this.buildEditorHistorySnapshot(var2);
            String var6 = (String)var4.remove(var4.size() - 1);
            List var7 = var2.pageUndoSnapshots.computeIfAbsent(var3, var0 -> new ArrayList<>());
            if (var7.isEmpty() || !this.equalsNullable((String)var7.get(var7.size() - 1), var5)) {
               var7.add(var5);
               this.trimEditorHistoryList(var7);
            }

            if (!this.applyEditorHistorySnapshot(var1, var2, var6)) {
               if (!var7.isEmpty() && this.equalsNullable((String)var7.get(var7.size() - 1), var5)) {
                  var7.remove(var7.size() - 1);
               }

               var4.add(var6);
               this.trimEditorHistoryList(var4);
               return false;
            } else {
               this.playEditorSfx(var1, var2, "redo");
               this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Redid change.</gray>"));
               return true;
            }
         } else {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>No changes to redo.</gray>"));
            return true;
         }
      } else {
         return false;
      }
   }

   protected boolean applyEditorHistorySnapshot(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         YamlConfiguration var4 = new YamlConfiguration();

         try {
            var4.loadFromString(var3);
         } catch (Exception var11) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<red>Failed to restore history:</red> <white>" + var11.getMessage() + "</white>"));
            return false;
         }

         ArrayList var5 = new ArrayList<>(this.getSelectedTargetIds(var2));
         List var6 = this.resolveEditorTargetPathsByIds(var2, var5);
         var2.historyApplying = true;

         boolean var7;
         try {
            this.finishLayersReorderDrag(var1, var2, false);
            this.resetActivePagesDragState(var2);
            this.clearActivePagesDragPreview(var1);
            this.stopCursorToolDrag(var2);
            this.resetSidebarFieldDrag(var1, var2);
            var2.pendingPropertyField = null;
            var2.pendingSavePopupField = null;
            this.clearPendingTextToolEdit(var1, var2, true);
            this.stopOpacitySliderDrag(var2, true);
            var2.colorPickerGradDragActive = false;
            var2.colorPickerHueDragActive = false;
            var2.selectedElementId = null;
            var2.additionalSelectedElementIds.clear();
            var2.hoveredElementId = null;
            var2.rawBlocks = this.copyBlocks(var4.getList("blocks"));
            var2.renderBlocks = this.resolveRenderableBlocks(var2.rawBlocks, var2.components);
            var2.hiddenLayerTargetIds.clear();
            var2.hiddenLayerTargetIds.addAll(var4.getStringList("hidden"));
            var2.lockedLayerTargetIds.clear();
            var2.lockedLayerTargetIds.addAll(var4.getStringList("locked"));
            var2.collapsedLayerTargetIds.clear();
            var2.collapsedLayerTargetIds.addAll(var4.getStringList("collapsed"));
            this.clearEditorOverlaysOnly(var1, var2);
            this.rerenderEditableContent(var1, var2);
            this.restoreSelectionAfterHistorySnapshot(var2, var5, var6);
            this.normalizeSelectionState(var2);
            var2.selectionOutlineVisible = var2.selectedElementId != null;
            if (var2.selectionOutlineVisible) {
               this.showSelectionOverlay(var1, var2);
            }

            this.renderLayersPanel(var1, var2);
            this.updateEditorPropertiesSidebar(var1, var2);
            this.updatePageInfoReadout(var1, var2);
            var7 = true;
         } finally {
            var2.historyApplying = false;
         }

         return var7;
      } else {
         return false;
      }
   }

   protected boolean hasCurrentPageName(EditorSession var1) {
      if (var1 == null) {
         return false;
      } else {
         String var2 = this.firstNonBlank(new String[]{var1.pageName}).trim();
         return !var2.isEmpty() && !this.isInternalEditorPageName(var2) ? !this.isInternalEditorFile(var1.pageFile) : false;
      }
   }

   @Override
   protected boolean isInternalEditorPageName(String var1) {
      String var2 = this.normalizePageKey(var1, "");
      return var2.isEmpty() ? false : "editor".equals(var2) || "editor_empty".equals(var2) || "editor_menu".equals(var2);
   }

   protected boolean isInternalEditorFile(File var1) {
      return var1 == null ? false : this.isInternalEditorPageName(var1.getName());
   }

   public boolean openEditorMenu(Player var1) {
      if (var1 == null) {
         return false;
      } else {
         this.normalizeEditorStartPitch(var1);
         boolean var2 = this.openGui(var1, "editor_menu", false, true);
         if (var2) {
            this.playEditorSfx(var1, "editor-opened");
         }

         return var2;
      }
   }

   protected boolean closeEditorAndOpenMenu(Player var1) {
      if (var1 == null) {
         return false;
      } else {
         boolean var2 = this.openGui(var1, "editor_menu", false, true);
         if (!var2) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Failed to open editor_menu.</yellow>"));
         }

         return var2;
      }
   }

   protected boolean createNewUiInEditor(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         String var3 = this.resolveNextUntitledPageKey(var1, var2);
         String var4 = this.formatUntitledPageDisplayName(var3);
         this.snapshotCurrentEditorPage(var1.getUniqueId(), var2);
         if (var2.elements != null && !var2.elements.isEmpty()) {
            for (HoverElement var7 : new ArrayList<>(var2.elements)) {
               this.removeRenderedElementHud(var1, var7);
            }

            var2.elements.clear();
         }

         this.clearSelectionState(var1, var2);
         this.finishLayersReorderDrag(var1, var2, false);
         this.resetActivePagesDragState(var2);
         this.clearActivePagesDragPreview(var1);
         this.stopCursorToolDrag(var2);
         this.resetEditorTransientPanelsForPageSwitch(var1, var2);
         var2.pageName = var3;
         var2.pageFile = null;
         var2.yaml = new YamlConfiguration();
         var2.yaml.set("name", var4);
         var2.rawBlocks = new ArrayList<>();
         var2.renderBlocks = new ArrayList<>();
         var2.pendingPropertyField = null;
         var2.pendingSavePopupField = null;
         this.clearPendingTextToolEdit(var1, var2, true);
         var2.savePopupName = var4;
         var2.savePopupDescription = "";
         var2.savePopupCommand = "";
         var2.spawnOpacityRetoggledTargetIds.clear();
         var2.pendingSpawnOpacityRetoggleTargetIds.clear();
         var2.spawnOpacityRetoggleTaskQueued = false;
         this.rememberEditorWorkingPage(var1.getUniqueId(), var3, var2.rawBlocks);
         this.rememberOpenedEditorPage(var1.getUniqueId(), var3);
         List var10 = this.resolveOpenEditorPagesForPlayer(var1.getUniqueId(), var3);
         var2.openEditorPages.clear();
         var2.openEditorPages.addAll(var10);
         var2.activePagesScrollOffset = this.resolveInitialActivePagesScrollOffset(var10, var3);
         this.rerenderEditableContent(var1, var2);
         this.rerenderActivePagesShell(var1, var2);
         var2.rightSidebarTab = EditorSidebarTab.LAYERS;
         var2.rightSidebarHoverTab = null;
         this.rerenderEditorShellForPageSwitch(var1, var2);
         this.setRightSidebarTab(var1, var2, EditorSidebarTab.LAYERS, false);
         double var11 = var2.cursorX + var2.hitboxOffsetX;
         double var8 = var2.cursorY + var2.hitboxOffsetY;
         this.updateEditorNavbarHover(var1, var2, var11, var8);
         this.updateSidebarTabHover(var1, var2, var11, var8);
         this.updatePageInfoReadout(var1, var2);
         this.refreshSavePopupFields(var1, var2);
         this.sendEditorPlayerMessage(
            var1, MM.deserialize("<green><bold>UltimateUI</bold></green> <#8a989c>»</#8a989c> <white>Utworzono nowe UI:</white> <aqua>" + var4 + "</aqua>")
         );
         return true;
      } else {
         return false;
      }
   }

   protected boolean openSavePopup(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         var2.pendingPropertyField = null;
         var2.pendingSavePopupField = null;
         this.clearPendingTextToolEdit(var1, var2, true);
         this.stopOpacitySliderDrag(var2, true);
         this.resetSidebarFieldDrag(var1, var2);
         this.closeColorPicker(var1, var2);
         this.populateSavePopupDefaults(var2);
         this.refreshSavePopupFields(var1, var2);
         this.setWelcomePopupVisible(var1, var2, false);
         this.setPreferencesPopupVisible(var1, var2, false);
         this.setSavePopupVisible(var1, var2, true);
         double var3 = var2.cursorX + var2.hitboxOffsetX;
         double var5 = var2.cursorY + var2.hitboxOffsetY;
         this.updateSavePopupHover(var1, var2, var3, var5);
         return true;
      } else {
         return false;
      }
   }

   protected boolean openPreferencesPopup(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         var2.pendingPropertyField = null;
         var2.pendingSavePopupField = null;
         this.clearPendingTextToolEdit(var1, var2, true);
         this.stopOpacitySliderDrag(var2, true);
         this.resetSidebarFieldDrag(var1, var2);
         this.closeColorPicker(var1, var2);
         this.setWelcomePopupVisible(var1, var2, false);
         this.setSavePopupVisible(var1, var2, false);
         this.setPreferencesPopupVisible(var1, var2, true);
         this.refreshPreferencesPopupFields(var1, var2);
         double var3 = var2.cursorX + var2.hitboxOffsetX;
         double var5 = var2.cursorY + var2.hitboxOffsetY;
         this.updatePreferencesPopupHover(var1, var2, var3, var5);
         return true;
      } else {
         return false;
      }
   }

   protected List<Map<String, Object>> resolveBlocksToSave(EditorSession var1) {
      if (var1 == null) {
         return Collections.emptyList();
      } else {
         List<Map<String, Object>> var2 = var1.rawBlocks == null ? new ArrayList<>() : this.copyBlocks(var1.rawBlocks);
         if (var2.isEmpty() && var1.elements != null && !var1.elements.isEmpty()) {
            List var3 = this.expandCompactImageBlocks(this.copyBlocks(var1.yaml == null ? null : var1.yaml.getList("blocks")));
            this.applyImageLayoutOffsetFormatToLoadedBlocks(var1.yaml, var3);
            if (!var3.isEmpty()) {
               this.plugin.getLogger().warning("[UltimateUI] Prevented empty save for page '" + var1.pageName + "' because rendered elements still exist.");
               var1.rawBlocks = var3;
               var2 = this.copyBlocks(var3);
            }
         }

         this.applyEditorLayerStateMetadata(var1, (List<Map<String, Object>>)var2);
         this.applyImageLayoutOffsetToBlocks((List<Map<String, Object>>)var2, -29.0, -248.0);
         return this.compactImageBlocksForSave((List<Map<String, Object>>)var2);
      }
   }

   protected void applyEditorLayerStateMetadata(EditorSession var1, List<Map<String, Object>> var2) {
      if (var1 != null && var2 != null && !var2.isEmpty() && var1.components != null && !var1.components.isEmpty()) {
         for (Map var5 : this.resolveRenderableBlocks(var2, var1.components)) {
            if (var5 != null && !var5.isEmpty()) {
               String var6 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var5, "__editor_target_id"))});
               String var7 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var5, "__editor_target_path"))});
               if (!var6.isBlank() && !var7.isBlank()) {
                  Map var8 = this.resolveRawMapAtPath(var2, var7);
                  if (var8 != null) {
                     var8.put("visible", !var1.hiddenLayerTargetIds.contains(var6));
                     var8.put("locked", var1.lockedLayerTargetIds.contains(var6));
                  }
               }
            }
         }
      }
   }

   protected void restoreEditorLayerStateMetadata(EditorSession var1) {
      if (var1 != null) {
         var1.hiddenLayerTargetIds.clear();
         var1.lockedLayerTargetIds.clear();
         if (var1.rawBlocks != null && !var1.rawBlocks.isEmpty() && var1.renderBlocks != null && !var1.renderBlocks.isEmpty()) {
            for (Map var3 : var1.renderBlocks) {
               if (var3 != null && !var3.isEmpty()) {
                  String var4 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var3, "__editor_target_id"))});
                  String var5 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var3, "__editor_target_path"))});
                  if (!var4.isBlank() && !var5.isBlank()) {
                     Map var6 = this.resolveRawMapAtPath(var1.rawBlocks, var5);
                     if (var6 != null && !var6.isEmpty()) {
                        if (!this.parseEditorMetaBoolean(var6.get("visible"), true)) {
                           var1.hiddenLayerTargetIds.add(var4);
                        }

                        if (this.parseEditorMetaBoolean(var6.get("locked"), false)) {
                           var1.lockedLayerTargetIds.add(var4);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected boolean parseEditorMetaBoolean(Object var1, boolean var2) {
      if (var1 == null) {
         return var2;
      } else if (var1 instanceof Boolean var6) {
         return var6;
      } else {
         String var3 = var1.toString().trim().toLowerCase(Locale.ROOT);
         if (var3.isBlank()) {
            return var2;
         } else {
            return switch (var3) {
               case "true", "yes", "on", "1" -> true;
               case "false", "no", "off", "0" -> false;
               default -> var2;
            };
         }
      }
   }

   protected File ensureSessionPageFile(EditorSession var1) {
      if (var1 == null) {
         return null;
      } else if (var1.pageFile != null) {
         return var1.pageFile;
      } else {
         String var2 = this.normalizePageKey(var1.pageName, var1.pageName);
         if (var2.isBlank()) {
            return null;
         } else {
            File var3 = new File(this.plugin.getDataFolder(), "contents/pages");
            if (!var3.exists()) {
               var3.mkdirs();
            }

            File var4 = new File(var3, var2 + ".yml");
            var1.pageName = var2;
            var1.pageFile = var4;
            if (var1.yaml == null) {
               var1.yaml = new YamlConfiguration();
            }

            return var4;
         }
      }
   }

   protected boolean saveEditorInPlace(Player var1) {
      EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
      if (var2 == null || !var2.editMode) {
         this.sendEditorPlayerMessage(var1, MM.deserialize("<red><bold>UltimateUI</bold></red> <#8a989c>»</#8a989c> <yellow>No editor session.</yellow>"));
         return false;
      } else if (!this.hasCurrentPageName(var2)) {
         return this.openSavePopup(var1, var2);
      } else {
         try {
            File var3 = this.ensureSessionPageFile(var2);
            if (var3 == null) {
               return this.openSavePopup(var1, var2);
            } else if (this.isInternalEditorFile(var3)) {
               this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Cannot overwrite the internal editor page. Use Save As.</yellow>"));
               return this.openSavePopup(var1, var2);
            } else {
               List var4 = this.resolveBlocksToSave(var2);
               var2.yaml.set("blocks", var4);
               String var5 = this.firstNonBlank(new String[]{var2.yaml.getString("name"), var2.pageName});
               if (!var5.isBlank()) {
                  var2.yaml.set("name", var5);
               }

               this.markImageLayoutSaveFormats(var2.yaml);
               String var6 = var2.yaml.saveToString();
               Files.writeString(var3.toPath(), var6.replace("\n", System.lineSeparator()), StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var3);
               this.playEditorSfx(var1, var2, "save");
               this.sendEditorPlayerMessage(var1, MM.deserialize("<#e5f8fd>Saved UI <bold><aqua>" + var2.pageName + "</aqua></bold>"));
               return true;
            }
         } catch (Exception var7) {
            this.sendEditorPlayerMessage(
               var1,
               MM.deserialize("<red><bold>UltimateUI</bold></red> <#8a989c>»</#8a989c> <yellow>Save failed:</yellow> <white>" + var7.getMessage() + "</white>")
            );
            return false;
         }
      }
   }

   public boolean saveEditor(Player var1) {
      EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
      if (var2 != null && var2.editMode) {
         try {
            List var3 = this.resolveBlocksToSave(var2);
            var2.yaml.set("blocks", var3);
            String var4 = this.firstNonBlank(new String[]{var2.yaml.getString("name"), var2.pageName});
            if (!var4.isBlank()) {
               var2.yaml.set("name", var4);
            }

            this.markImageLayoutSaveFormats(var2.yaml);
            String var5 = var2.yaml.saveToString();
            Files.writeString(var2.pageFile.toPath(), var5.replace("\n", System.lineSeparator()), StandardCharsets.UTF_8);
            this.invalidateYamlFileCache(var2.pageFile);
            this.sendEditorPlayerMessage(var1, MM.deserialize("<#e5f8fd>Saved UI <bold><aqua>" + var2.pageName + "</aqua></bold>"));
            this.cameraService.setCameraActive(var1, false);
            this.hudService.clearHuds(var1);
            this.closeGui(var1);
            return true;
         } catch (Exception var6) {
            this.sendEditorPlayerMessage(
               var1,
               MM.deserialize("<red><bold>UltimateUI</bold></red> <#8a989c>»</#8a989c> <yellow>Save failed:</yellow> <white>" + var6.getMessage() + "</white>")
            );
            return false;
         }
      } else {
         this.sendEditorPlayerMessage(var1, MM.deserialize("<red><bold>UltimateUI</bold></red> <#8a989c>»</#8a989c> <yellow>No editor session.</yellow>"));
         return false;
      }
   }

   public void runEditorAutosave() {
      File var1 = new File(this.plugin.getDataFolder(), "autosave");
      if (!var1.exists() && !var1.mkdirs()) {
         this.plugin.getLogger().warning("[UltimateUI] Failed to create autosave folder: " + var1.getAbsolutePath());
      } else {
         String var2 = AUTOSAVE_TIMESTAMP_FORMAT.format(LocalDateTime.now());

         for (Map.Entry<?, ?> var4 : ((Map<?, ?>)this.editorSessions).entrySet()) {
            if (var4 != null) {
               UUID var5 = (UUID)var4.getKey();
               EditorSession var6 = (EditorSession)var4.getValue();
               if (var5 != null && var6 != null && var6.editMode && var6.previewMode) {
                  Player var7 = Bukkit.getPlayer(var5);
                  if (var7 != null && var7.isOnline()) {
                     this.writeEditorAutosaveSnapshot(var1, var6, var2);
                  }
               }
            }
         }
      }
   }

   protected void writeEditorAutosaveSnapshot(File var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String var4 = this.normalizePageKey(var2.pageName, "untitled");
         if (var4.isBlank() || this.isInternalEditorPageName(var4)) {
            var4 = "untitled";
         }

         try {
            YamlConfiguration var5 = this.cloneSessionYamlForAutosave(var2);
            List var6 = this.resolveBlocksToSave(var2);
            var5.set("blocks", var6);
            String var7 = this.firstNonBlank(new String[]{var5.getString("name"), var2.pageName, var4}).trim();
            if (!var7.isBlank()) {
               var5.set("name", var7);
            }

            this.markImageLayoutSaveFormats(var5);
            File var8 = new File(var1, var4 + "-" + var3 + ".yml");

            for (int var9 = 1; var8.exists(); var9++) {
               var8 = new File(var1, var4 + "-" + var3 + "-" + var9 + ".yml");
            }

            String var10 = var5.saveToString();
            Files.writeString(var8.toPath(), var10.replace("\n", System.lineSeparator()), StandardCharsets.UTF_8);
            this.invalidateYamlFileCache(var8);
         } catch (Exception var11) {
            this.plugin.getLogger().warning("[UltimateUI] Failed to autosave editor page '" + var4 + "': " + var11.getMessage());
         }
      }
   }

   protected YamlConfiguration cloneSessionYamlForAutosave(EditorSession var1) {
      YamlConfiguration var2 = new YamlConfiguration();
      if (var1 != null && var1.yaml != null) {
         try {
            var2.loadFromString(var1.yaml.saveToString());
         } catch (Exception var4) {
         }

         return var2;
      } else {
         return var2;
      }
   }

   @Override
   protected void startSavePopupInputPrompt(Player var1, EditorSession var2, EditorSavePopupField var3) {
      if (var1 != null && var2 != null && var3 != null) {
         if (var2.pendingSavePopupField != var3) {
            var2.pendingPropertyField = null;
            this.clearPendingItemDesignInventoryPick(var2);
            var2.pendingActionTargetId = null;
            var2.pendingActionIndex = -1;
            var2.pendingSavePopupField = var3;
            this.refreshSavePopupFields(var1, var2);

            String var4 = switch (var3) {
               case NAME -> "Name";
               case DESCRIPTION -> "Description";
               case COMMAND -> "Command";
            };
            this.sendEditorPlayerMessage(var1, MM.deserialize("<#84a0c8>" + var4 + " <gray>- type a new value in chat or <white>cancel</white>.</gray>"));
         }
      }
   }

   @Override
   protected boolean handleSavePopupConfirm(Player var1, EditorSession var2) {
      return this.saveEditorFromPopup(var1, var2);
   }

   @Override
   protected void refreshSavePopupFields(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         String var3 = this.formatSavePopupValue(var2.savePopupName, "Example name");
         String var4 = this.formatSavePopupValue(var2.savePopupDescription, "Example description");
         String var5 = this.formatSavePopupValue(var2.savePopupCommand, "<font:default>/<font:editor>example");
         if (var2.pendingSavePopupField != null) {
            String var6 = this.toSidebarDisplayValue("...");
            switch (var2.pendingSavePopupField) {
               case NAME:
                  var3 = var6;
                  break;
               case DESCRIPTION:
                  var4 = var6;
                  break;
               case COMMAND:
                  var5 = var6;
            }
         }

         this.setShellText(var1, "popup_save_name_value", var3);
         this.setShellText(var1, "popup_save_desc_value", var4);
         this.setShellText(var1, "popup_save_command_value", var5);
         double var10 = var2.cursorX + var2.hitboxOffsetX;
         double var8 = var2.cursorY + var2.hitboxOffsetY;
         this.updateSavePopupActionIcons(var1, var2, var10, var8);
      }
   }

   @Override
   protected boolean handlePreferencesPopupConfirm(Player var1, EditorSession var2) {
      return true;
   }

   @Override
   protected boolean handleWelcomePopupConfirm(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         var2.welcomePopupAcknowledged = true;
         this.savePlayerColorPrefs(var1, var2);
         return true;
      } else {
         return false;
      }
   }

   @Override
   protected boolean handlePreferencesPopupToggle(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         boolean var4 = false;
         switch (var3) {
            case "popup_preferences_hotbar_name":
               var2.displayHotbar = !var2.displayHotbar;
               break;
            case "popup_preferences_hand_name":
               var2.displayHand = !var2.displayHand;
               break;
            case "popup_preferences_optimized_name":
               var2.optimizedEditor = !var2.optimizedEditor;
               var4 = true;
               break;
            case "popup_preferences_sound_name":
               var2.editorSounds = !var2.editorSounds;
               break;
            default:
               return false;
         }

         this.applyEditorDisplayPreferences(var1, var2);
         if (var4) {
            this.rerenderEditableContent(var1, var2);
         }

         this.savePlayerColorPrefs(var1, var2);
         return true;
      } else {
         return false;
      }
   }

   @Override
   protected void refreshPreferencesPopupFields(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         this.refreshPreferencesPopupToggleRow(var1, var2, "popup_preferences_hotbar_value", "popup_preferences_hotbar_button", var2.displayHotbar);
         this.refreshPreferencesPopupToggleRow(var1, var2, "popup_preferences_hand_value", "popup_preferences_hand_button", var2.displayHand);
         this.refreshPreferencesPopupToggleRow(var1, var2, "popup_preferences_optimized_value", "popup_preferences_optimized_button", var2.optimizedEditor);
         this.refreshPreferencesPopupToggleRow(var1, var2, "popup_preferences_sound_value", "popup_preferences_sound_button", var2.editorSounds);
         double var3 = var2.cursorX + var2.hitboxOffsetX;
         double var5 = var2.cursorY + var2.hitboxOffsetY;
         this.updatePreferencesPopupActionIcons(var1, var2, var3, var5);
      }
   }

   protected void refreshPreferencesPopupToggleRow(Player var1, EditorSession var2, String var3, String var4, boolean var5) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var4 != null && !var4.isBlank()) {
         String var6 = var5 ? "Enabled" : "Disabled";
         String var7 = var5 ? "262626" : "141414";
         String var8 = var5 ? "919191" : "666666";
         this.setShellText(var1, var3, this.withHexPrefix(var6, var8));
         this.setShellColor(var1, var2, var4, var7);
      }
   }

   protected boolean saveEditorFromPopup(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode) {
         String var3 = this.firstNonBlank(new String[]{var2.savePopupName}).trim();
         if (var3.isEmpty()) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Provide a file name before saving.</yellow>"));
            this.refreshSavePopupFields(var1, var2);
            return false;
         } else {
            String var4 = this.normalizePageKey(var3, var2.pageName);
            if (var4.isBlank()) {
               this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Cannot create a file name from the provided name.</yellow>"));
               return false;
            } else if (this.isInternalEditorPageName(var4)) {
               this.sendEditorPlayerMessage(
                  var1, MM.deserialize("<yellow>This name is reserved. Choose a name other than editor/editor_empty/editor_menu.</yellow>")
               );
               this.refreshSavePopupFields(var1, var2);
               return false;
            } else {
               String var5 = this.firstNonBlank(new String[]{var2.savePopupDescription}).trim();
               String var6 = this.normalizeCustomCommandToken(var2.savePopupCommand);
               if (!var6.isBlank()) {
                  ArrayList var7 = new ArrayList();
                  Map var8 = this.loadYamlFolder("contents/pages", var7);

                  for (Map.Entry<?, ?> var10 : ((Map<?, ?>)var8).entrySet()) {
                     String var11 = this.normalizePageKey((String)var10.getKey(), "");
                     if (!var11.equalsIgnoreCase(var4)) {
                        String var12 = this.normalizeCustomCommandToken(
                           var10.getValue() == null ? null : ((YamlConfiguration)var10.getValue()).getString("command")
                        );
                        if (var6.equals(var12)) {
                           this.sendEditorPlayerMessage(
                              var1,
                              MM.deserialize(
                                 "<yellow>Command <white>/" + var6 + "</white> is already used by <white>" + (String)var10.getKey() + "</white>.</yellow>"
                              )
                           );
                           this.refreshSavePopupFields(var1, var2);
                           return false;
                        }
                     }
                  }
               }

               try {
                  List var14 = this.resolveBlocksToSave(var2);
                  YamlConfiguration var15 = this.cloneYamlConfiguration(var2.yaml);
                  var15.set("name", var3);
                  var15.set("desc", var5.isBlank() ? null : var5);
                  var15.set("command", var6.isBlank() ? null : var6);
                  var15.set("blocks", var14);
                  this.markImageLayoutSaveFormats(var15);
                  File var16 = new File(this.plugin.getDataFolder(), "contents/pages");
                  if (!var16.exists()) {
                     var16.mkdirs();
                  }

                  File var17 = new File(var16, var4 + ".yml");
                  String var18 = var15.saveToString();
                  Files.writeString(var17.toPath(), var18.replace("\n", System.lineSeparator()), StandardCharsets.UTF_8);
                  this.invalidateYamlFileCache(var17);
                  var2.pageName = var4;
                  var2.pageFile = var17;
                  var2.yaml = var15;
                  var2.pendingSavePopupField = null;
                  this.playEditorSfx(var1, var2, "save");
                  this.sendEditorPlayerMessage(var1, MM.deserialize("<#e5f8fd>Saved UI <bold><aqua>" + var4 + "</aqua></bold>"));
                  boolean var19 = this.closeEditorAndOpenMenu(var1);
                  if (!var19) {
                     this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Failed to open editor_menu.</yellow>"));
                  }

                  return true;
               } catch (Exception var13) {
                  this.sendEditorPlayerMessage(
                     var1,
                     MM.deserialize(
                        "<red><bold>UltimateUI</bold></red> <#8a989c>»</#8a989c> <yellow>Save failed:</yellow> <white>" + var13.getMessage() + "</white>"
                     )
                  );
                  return false;
               }
            }
         }
      } else {
         return false;
      }
   }

   protected void populateSavePopupDefaults(EditorSession var1) {
      if (var1 != null) {
         boolean var2 = this.isInternalEditorPageName(var1.pageName);
         String var3 = this.firstNonBlank(new String[]{var1.yaml == null ? null : var1.yaml.getString("name"), var1.pageName}).trim();
         if (var2 || this.isInternalEditorPageName(var3)) {
            var3 = "";
         }

         String var4 = this.formatUntitledPageDisplayName(var3);
         if (!var4.isBlank()) {
            var3 = var4;
         }

         String var5 = this.firstNonBlank(
               new String[]{var1.yaml == null ? null : var1.yaml.getString("desc"), var1.yaml == null ? null : var1.yaml.getString("description")}
            )
            .trim();
         String var6 = this.normalizeCustomCommandToken(var1.yaml == null ? null : var1.yaml.getString("command"));
         var1.savePopupName = var3;
         var1.savePopupDescription = var5;
         var1.savePopupCommand = var6.isBlank() ? "" : "/" + var6;
      }
   }

   protected String formatSavePopupValue(String var1, String var2) {
      String var3 = this.firstNonBlank(new String[]{var1}).trim();
      return var3.isEmpty() ? var2 : "<#999999>" + this.truncateSavePopupText(var3);
   }

   protected String truncateSavePopupText(String var1) {
      if (var1 == null) {
         return "";
      } else {
         return var1.length() <= 28 ? var1 : var1.substring(0, 28) + "...";
      }
   }

   protected String resolveExistingPageMapKey(Map<String, YamlConfiguration> var1, String var2) {
      if (var1 != null && !var1.isEmpty()) {
         String var3 = this.normalizePageKey(var2, "");
         if (var3.isBlank()) {
            return "";
         } else if (var1.containsKey(var2)) {
            return var2;
         } else {
            for (String var5 : var1.keySet()) {
               if (var3.equalsIgnoreCase(this.normalizePageKey(var5, ""))) {
                  return var5;
               }
            }

            return "";
         }
      } else {
         return "";
      }
   }

   @Override
   protected String normalizePageKey(String var1, String var2) {
      String var3 = this.firstNonBlank(new String[]{var1, var2}).trim();
      if (var3.toLowerCase(Locale.ROOT).endsWith(".yml")) {
         var3 = var3.substring(0, var3.length() - 4);
      }

      var3 = var3.replace('\\', '_').replace('/', '_');
      var3 = var3.replaceAll("\\s+", "_");
      var3 = var3.replaceAll("[^a-zA-Z0-9_-]", "");
      var3 = var3.replaceAll("_+", "_");
      var3 = var3.replaceAll("^-+", "");
      var3 = var3.replaceAll("-+$", "");
      return var3.isBlank() ? "" : var3.toLowerCase(Locale.ROOT);
   }

   protected String resolvePageOpenPermission(YamlConfiguration var1) {
      return var1 == null
         ? ""
         : this.firstNonBlank(
               new String[]{
                  var1.getString("permission"), var1.getString("open_permission"), var1.getString("open-permission"), var1.getString("openPermission")
               }
            )
            .trim();
   }

   protected boolean hasPageOpenPermission(Player var1, String var2) {
      if (var1 == null) {
         return false;
      } else {
         String var3 = this.firstNonBlank(new String[]{var2}).trim();
         return var3.isBlank() ? true : var1.hasPermission("ultimateui.admin") || var1.hasPermission(var3);
      }
   }

   public void openOnJoinForPlayer(Player var1) {
      if (var1 != null && var1.isOnline()) {
         ArrayList var2 = new ArrayList();
         Map var3 = this.loadYamlFolder("contents/pages", var2);
         if (!var3.isEmpty()) {
            for (Map.Entry<?, ?> var5 : ((Map<?, ?>)var3).entrySet()) {
               YamlConfiguration var6 = (YamlConfiguration)var5.getValue();
               if (var6 != null && (var6.getBoolean("open-on-join.enabled", false) || var6.getBoolean("openOnJoin.enabled", false))) {
                  String var7 = this.normalizePageKey((String)var5.getKey(), "");
                  if (!var7.isBlank()) {
                     String var8 = this.resolvePageOpenPermission(var6);
                     if (var8.isBlank() || this.hasPageOpenPermission(var1, var8)) {
                        String var9 = this.firstNonBlank(new String[]{var6.getString("open-on-join.type"), var6.getString("openOnJoin.type")})
                           .trim()
                           .toLowerCase(Locale.ROOT);
                        boolean var10 = var9.equals("hud");
                        int var11 = Math.max(0, firstNonZeroInt(var6.getInt("open-on-join.delay", 0), var6.getInt("openOnJoin.delay", 0)));
                        if (var11 <= 0) {
                           if (var10) {
                              this.startGuiHud(var1, var7, false);
                           } else {
                              this.startGui(var1, var7, false);
                           }
                        } else {
                           PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
                              if (var1.isOnline()) {
                                 if (var10) {
                                    this.startGuiHud(var1, var7, false);
                                 } else {
                                    this.startGui(var1, var7, false);
                                 }
                              }
                           }, (long)var11);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private static int firstNonZeroInt(int... var0) {
      for (Object var4_raw : var0) {
         int var4 = ((Number)var4_raw).intValue();
         if (var4 != 0) {
            return var4;
         }
      }

      return 0;
   }

   protected String normalizeCustomCommandToken(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim();
      if (var2.startsWith("/")) {
         var2 = var2.substring(1).trim();
      }

      int var3 = var2.indexOf(32);
      if (var3 >= 0) {
         var2 = var2.substring(0, var3);
      }

      var2 = var2.toLowerCase(Locale.ROOT);
      return var2.replaceAll("[^a-z0-9_:-]", "");
   }

   protected boolean hasDefinedPageBlocks(YamlConfiguration var1) {
      if (var1 == null) {
         return false;
      } else {
         List var2 = var1.getList("blocks");
         return var2 != null && !var2.isEmpty();
      }
   }

   protected void ensurePageDisplayName(EditorSession var1) {
      if (var1 != null && var1.yaml != null) {
         String var2 = this.firstNonBlank(new String[]{var1.yaml.getString("name")}).trim();
         if (var2.isBlank()) {
            String var3 = this.firstNonBlank(new String[]{var1.pageName}).trim();
            if (!var3.isBlank()) {
               var1.yaml.set("name", var3);
            }
         }
      }
   }

   public String resolveGuiByCustomCommand(String var1) {
      String var2 = this.normalizeCustomCommandToken(var1);
      if (!var2.isBlank() && !var2.equals("uui") && !var2.equals("ultimateui") && !var2.equals("UltimateUI") && !var2.equals("xqedit")) {
         ArrayList var3 = new ArrayList();
         Map var4 = this.loadYamlFolder("contents/pages", var3);
         if (var4.isEmpty()) {
            return null;
         } else {
            ArrayList var5 = new ArrayList(var4.keySet());
            var5.sort(String.CASE_INSENSITIVE_ORDER);
            String var6 = this.normalizePageKey(var2, "");
            String var7 = var2;
            int var8 = var2.indexOf(58);
            if (var8 >= 0 && var8 + 1 < var2.length()) {
               var7 = var2.substring(var8 + 1);
            }

            String var9 = this.normalizePageKey(var7, "");
            String var10 = null;
            String var11 = null;
            ArrayList var12 = new ArrayList();

            for (Object var14_raw : var5) {
               String var14 = var14_raw != null ? var14_raw.toString() : null;
               if (!this.isInternalEditorPageName(var14)) {
                  YamlConfiguration var15 = (YamlConfiguration)var4.get(var14);
                  if (var15 != null) {
                     String var16 = this.normalizeCustomCommandToken(var15.getString("command"));
                     if (!var16.isBlank() && var16.equals(var2)) {
                        var12.add(var14);
                        String var17 = this.normalizePageKey(var14, var14);
                        if (!var6.isBlank() && var17.equals(var6)) {
                           return var14;
                        }

                        if (!var9.isBlank() && var17.equals(var9)) {
                           return var14;
                        }

                        if (var11 == null && this.hasDefinedPageBlocks(var15)) {
                           var11 = var14;
                        }

                        if (var10 == null) {
                           var10 = var14;
                        }
                     }
                  }
               }
            }

            if (var12.size() > 1) {
               this.plugin.getLogger().warning("[UltimateUI] Duplicate custom command '/" + var2 + "' matched pages: " + String.join(", ", var12));
            }

            return var11 != null ? var11 : var10;
         }
      } else {
         return null;
      }
   }

   public boolean isAwaitingSidebarInput(Player var1) {
      if (var1 == null) {
         return false;
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         return var2 != null
            && var2.editMode
            && (
               var2.pendingPropertyField != null
                  || var2.pendingSavePopupField != null
                  || var2.colorPickerHexInputPending
                  || var2.pendingItemDesignInventoryTargetId != null && !var2.pendingItemDesignInventoryTargetId.isBlank()
                  || var2.pendingTextToolTargetId != null && !var2.pendingTextToolTargetId.isBlank()
                  || var2.pendingActionTargetId != null && !var2.pendingActionTargetId.isBlank() && var2.pendingActionIndex >= 0
            );
      }
   }

   public boolean handleSidebarChatInput(Player var1, String var2) {
      if (var1 == null) {
         return false;
      } else {
         EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
         if (var3 == null || !var3.editMode) {
            return false;
         } else if (var3.colorPickerHexInputPending) {
            String var23 = var2 == null ? "" : var2.trim();
            if (var23.equalsIgnoreCase("cancel")) {
               var3.colorPickerHexInputPending = false;
               String var28 = var3.colorPickerSlot == 1 ? var3.editorColor1 : var3.editorColor2;
               this.updateColorPickerDisplay(var1, var3, var28);
               this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Color editing cancelled.</gray>"));
               return true;
            } else {
               String var27 = this.parseSidebarHexColorInput(var23);
               if (var27.isBlank()) {
                  this.sendEditorPlayerMessage(var1, MM.deserialize("<red>Invalid HEX color.</red> <gray>Type e.g. #ff8800 or 'cancel'.</gray>"));
                  return true;
               } else {
                  var3.colorPickerHexInputPending = false;
                  if (var3.colorPickerSlot == 1) {
                     var3.editorColor1 = var27;
                  } else {
                     var3.editorColor2 = var27;
                  }

                  this.setShellColor(var1, var3, var3.colorPickerSlot == 1 ? "editor_color_1" : "editor_color_2", var27);
                  this.syncColorPickerToColor(var1, var3, var27);
                  this.notifyColorPickerColorsChanged(var1, var3);
                  return true;
               }
            }
         } else {
            String var4 = this.firstNonBlank(new String[]{var3.pendingTextToolTargetId});
            if (!var4.isBlank()) {
               String var26 = var3.pendingTextToolOriginalText == null ? "" : var3.pendingTextToolOriginalText;
               String var32 = var2 == null ? "" : var2;
               if (var32.trim().equalsIgnoreCase("cancel")) {
                  this.clearPendingTextToolEdit(var1, var3, true);
                  this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Text editing cancelled.</gray>"));
                  return true;
               } else {
                  this.clearPendingTextToolEdit(var1, var3, false);
                  if (!this.isTextTarget(var3, var4)) {
                     this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>This element is no longer text.</yellow>"));
                     return true;
                  } else {
                     boolean var35 = this.setTargetText(var3, var4, var32);
                     if (!var35) {
                        this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>No changes to apply.</yellow>"));
                        this.updateEditorPropertiesSidebar(var1, var3);
                        return true;
                     } else {
                        this.rerenderEditableSelection(var1, var3);
                        if (this.equalsNullable(var26, var32)) {
                           this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>No changes to apply.</yellow>"));
                           this.updateEditorPropertiesSidebar(var1, var3);
                           return true;
                        } else {
                           this.recordEditorMutation(var3);
                           return true;
                        }
                     }
                  }
               }
            } else if (var3.pendingActionTargetId != null && !var3.pendingActionTargetId.isBlank() && var3.pendingActionIndex >= 0) {
               String var25 = var2 == null ? "" : var2.trim();
               if (var25.equalsIgnoreCase("cancel")) {
                  var3.pendingActionTargetId = null;
                  var3.pendingActionIndex = -1;
                  this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Action editing cancelled.</gray>"));
                  return true;
               } else {
                  String var31 = var3.pendingActionTargetId;
                  int var34 = var3.pendingActionIndex;
                  var3.pendingActionTargetId = null;
                  var3.pendingActionIndex = -1;
                  List var46 = this.resolveTargetActionsRawList(var3, var31, false);
                  if (var46 != null && var34 >= 0 && var34 < var46.size()) {
                     Map var54 = (Map)var46.get(var34);
                     String var62 = this.normalizeActionType(this.stringValue(var54.get("type")));
                     if (!this.isEditableActionType(var62)) {
                        this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>This action type cannot be edited.</yellow>"));
                        this.updateEditorPropertiesSidebar(var1, var3);
                        return true;
                     } else {
                        String var71 = var25.isBlank() ? this.resolveDefaultActionValue(var62) : var25;
                        String var78 = this.resolveActionDisplayValue(var54, var62);
                        if (var78.equals(var71)) {
                           this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>No changes to apply.</yellow>"));
                           this.updateEditorPropertiesSidebar(var1, var3);
                           return true;
                        } else {
                           this.recordEditorMutation(var3);
                           var54.put("value", var71);
                           var54.put("name", var71);
                           var3.actionsSelectedIndex = var34;
                           this.updateEditorPropertiesSidebar(var1, var3);
                           return true;
                        }
                     }
                  } else {
                     this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>This action does not exist.</yellow>"));
                     this.updateEditorPropertiesSidebar(var1, var3);
                     return true;
                  }
               }
            } else if (var3.pendingSavePopupField != null) {
               String var24 = var2 == null ? "" : var2.trim();
               if (var24.equalsIgnoreCase("cancel")) {
                  var3.pendingSavePopupField = null;
                  this.refreshSavePopupFields(var1, var3);
                  this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Popup editing cancelled.</gray>"));
                  return true;
               } else {
                  EditorSavePopupField var30 = var3.pendingSavePopupField;
                  var3.pendingSavePopupField = null;
                  String var33 = var2 == null ? "" : var2.trim();
                  switch (var30) {
                     case NAME:
                        var3.savePopupName = var33;
                        break;
                     case DESCRIPTION:
                        var3.savePopupDescription = var33;
                        break;
                     case COMMAND:
                        var3.savePopupCommand = var33;
                  }

                  this.refreshSavePopupFields(var1, var3);
                  double var45 = var3.cursorX + var3.hitboxOffsetX;
                  double var61 = var3.cursorY + var3.hitboxOffsetY;
                  this.updateSavePopupHover(var1, var3, var45, var61);
                  return true;
               }
            } else {
               String var5 = this.firstNonBlank(new String[]{var3.pendingItemDesignInventoryTargetId});
               if (!var5.isBlank()) {
                  String var29 = var2 == null ? "" : var2.trim();
                  if (var29.equalsIgnoreCase("cancel")) {
                     this.clearPendingItemDesignInventoryPick(var3);
                     this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Inventory material picking cancelled.</gray>"));
                     return true;
                  } else {
                     this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Click an item in inventory or type <white>cancel</white>.</gray>"));
                     return true;
                  }
               } else if (var3.pendingPropertyField == null) {
                  return false;
               } else {
                  String var6 = var2 == null ? "" : var2.trim();
                  if (var6.equalsIgnoreCase("cancel")) {
                     var3.pendingPropertyField = null;
                     this.clearPendingItemDesignInventoryPick(var3);
                     this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Value editing cancelled.</gray>"));
                     return true;
                  } else {
                     EditorPropertyField var7 = var3.pendingPropertyField;
                     if (var7 == EditorPropertyField.ITEM_DESIGN_ITEM) {
                        if (var6.equalsIgnoreCase("inventory")) {
                           String var44 = this.firstNonBlank(new String[]{this.getSidebarEditableTargetId(var3)});
                           if (!var44.isBlank() && this.isSidebarItemDesignTarget(var3, var44)) {
                              var3.pendingPropertyField = null;
                              var3.pendingItemDesignInventoryTargetId = var44;
                              this.sendEditorPlayerMessage(
                                 var1,
                                 MM.deserialize("<#84a0c8>Item <gray>- inventory mode enabled. Click an inventory item or type <white>cancel</white>.</gray>")
                              );
                              return true;
                           } else {
                              this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Select an item element first.</yellow>"));
                              return true;
                           }
                        } else {
                           String var43 = this.normalizeSidebarItemTokenInput(var6);
                           if (var43.isBlank()) {
                              this.sendEditorPlayerMessage(
                                 var1,
                                 MM.deserialize(
                                    "<red>Invalid item.</red> <gray>Use e.g. diamond, DIAMOND#123, PLAYER_HEAD:Notch, PLAYER_HEAD:%player% or INVENTORY.</gray>"
                                 )
                              );
                              return true;
                           } else {
                              var3.pendingPropertyField = null;
                              this.clearPendingItemDesignInventoryPick(var3);
                              List var53 = this.getSidebarEditableTargetIds(var3);
                              if (var53.isEmpty()) {
                                 this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Select an element first.</yellow>"));
                                 return true;
                              } else {
                                 boolean var60 = false;
                                 boolean var70 = false;
                                 boolean var77 = false;

                                 for (Object var87_raw : var53) {
                                    String var87 = var87_raw != null ? var87_raw.toString() : null;
                                    if (this.isSidebarItemDesignTarget(var3, var87)) {
                                       var77 = true;
                                       boolean var91 = this.setTargetItemToken(var3, var87, var43);
                                       if (var91 && !var70) {
                                          this.recordEditorMutation(var3);
                                          var70 = true;
                                       }

                                       var60 |= var91;
                                    }
                                 }

                                 if (!var77) {
                                    this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Select an item element first.</yellow>"));
                                    this.updateEditorPropertiesSidebar(var1, var3);
                                    return true;
                                 } else if (!var60) {
                                    this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>No changes to apply.</yellow>"));
                                    this.updateEditorPropertiesSidebar(var1, var3);
                                    return true;
                                 } else {
                                    this.rerenderEditableSelection(var1, var3);
                                    return true;
                                 }
                              }
                           }
                        }
                     } else if (var7 == EditorPropertyField.ITEM_DESIGN_GLOWING) {
                        String var42 = var6.toLowerCase(Locale.ROOT);

                        Boolean var52 = switch (var42) {
                           case "enabled", "enable", "on", "true", "yes", "1" -> true;
                           case "disabled", "disable", "off", "false", "no", "0" -> false;
                           default -> null;
                        };
                        if (var52 == null) {
                           this.sendEditorPlayerMessage(var1, MM.deserialize("<red>Invalid value.</red> <gray>Use Enabled or Disabled.</gray>"));
                           return true;
                        } else {
                           var3.pendingPropertyField = null;
                           this.clearPendingItemDesignInventoryPick(var3);
                           List var59 = this.getSidebarEditableTargetIds(var3);
                           if (var59.isEmpty()) {
                              this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Select an element first.</yellow>"));
                              return true;
                           } else {
                              boolean var69 = false;
                              boolean var76 = false;
                              boolean var81 = false;

                              for (Object var90_raw : var59) {
                                 String var90 = var90_raw != null ? var90_raw.toString() : null;
                                 if (this.isSidebarItemDesignTarget(var3, var90)) {
                                    var81 = true;
                                    boolean var94 = this.setTargetItemGlowing(var3, var90, var52);
                                    if (var94 && !var76) {
                                       this.recordEditorMutation(var3);
                                       var76 = true;
                                    }

                                    var69 |= var94;
                                 }
                              }

                              if (!var81) {
                                 this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Select an item element first.</yellow>"));
                                 this.updateEditorPropertiesSidebar(var1, var3);
                                 return true;
                              } else if (!var69) {
                                 this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>No changes to apply.</yellow>"));
                                 this.updateEditorPropertiesSidebar(var1, var3);
                                 return true;
                              } else {
                                 this.rerenderEditableSelection(var1, var3);
                                 return true;
                              }
                           }
                        }
                     } else if (var7 == EditorPropertyField.DESIGN_TEXT_WRAP) {
                        var3.pendingPropertyField = null;
                        this.clearPendingItemDesignInventoryPick(var3);

                        int var41;
                        try {
                           var41 = Integer.parseInt(var6.trim());
                        } catch (NumberFormatException var22) {
                           var41 = -1;
                        }

                        if (var41 <= 0) {
                           this.sendEditorPlayerMessage(
                              var1, MM.deserialize("<red>Invalid value.</red> <gray>Type a positive integer (e.g. 200) or 'cancel'.</gray>")
                           );
                           return true;
                        } else {
                           boolean var51 = this.setSelectedTextElementLineWidth(var1, var41);
                           if (!var51) {
                              this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>No changes to apply.</yellow>"));
                              this.updateEditorPropertiesSidebar(var1, var3);
                              return true;
                           } else {
                              return true;
                           }
                        }
                     } else if (var7 == EditorPropertyField.DESIGN_HOVER_COLOR) {
                        var3.pendingPropertyField = null;
                        this.clearPendingItemDesignInventoryPick(var3);
                        List var40 = this.getSidebarEditableTargetIds(var3);
                        if (var40.isEmpty()) {
                           this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Select an element first.</yellow>"));
                           return true;
                        } else {
                           String var50 = this.firstNonBlank(new String[]{var6}).trim();
                           String var58;
                           if (var50.equalsIgnoreCase("none")) {
                              var58 = "none";
                           } else if (var50.equalsIgnoreCase("auto")) {
                              var58 = "auto";
                           } else {
                              String var66 = this.normalizeHexColor(var50);
                              if (var66.isBlank()) {
                                 this.sendEditorPlayerMessage(
                                    var1,
                                    MM.deserialize(
                                       "<red>Invalid color.</red> <gray>Type HEX (e.g. #ff8800), <white>Auto</white>, <white>None</white> or 'cancel'.</gray>"
                                    )
                                 );
                                 return true;
                              }

                              var58 = var66;
                           }

                           boolean var67 = false;
                           boolean var75 = false;

                           for (Object var85_raw : var40) {
                              String var85 = var85_raw != null ? var85_raw.toString() : null;
                              boolean var89 = this.setTargetHoverColor(var3, var85, var58);
                              if (var89 && !var75) {
                                 this.recordEditorMutation(var3);
                                 var75 = true;
                              }

                              var67 |= var89;
                           }

                           if (!var67) {
                              this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>No changes to apply.</yellow>"));
                              this.updateEditorPropertiesSidebar(var1, var3);
                              return true;
                           } else {
                              this.rerenderEditableSelection(var1, var3);
                              return true;
                           }
                        }
                     } else if (this.isSidebarHexField(var7)) {
                        if (var7 == EditorPropertyField.DESIGN_BORDER_COLOR && this.isSidebarAutoBorderColorInput(var6)) {
                           var3.pendingPropertyField = null;
                           boolean var39 = this.applySidebarBorderColorAuto(var3);
                           if (!var39) {
                              this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>No changes to apply.</yellow>"));
                              this.updateEditorPropertiesSidebar(var1, var3);
                              return true;
                           } else {
                              this.rerenderEditableSelection(var1, var3);
                              return true;
                           }
                        } else {
                           String var38 = this.parseSidebarHexColorInput(var6);
                           if (var38.isBlank()) {
                              if (var7 == EditorPropertyField.DESIGN_BORDER_COLOR) {
                                 this.sendEditorPlayerMessage(
                                    var1, MM.deserialize("<red>Invalid color.</red> <gray>Type HEX (e.g. #ff8800), <white>Auto</white> or 'cancel'.</gray>")
                                 );
                              } else {
                                 this.sendEditorPlayerMessage(var1, MM.deserialize("<red>Invalid HEX color.</red> <gray>Type e.g. #ff8800 or 'cancel'.</gray>"));
                              }

                              return true;
                           } else {
                              var3.pendingPropertyField = null;
                              boolean var49 = this.applySidebarHexPropertyValue(var3, var7, var38);
                              if (!var49) {
                                 this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>No changes to apply.</yellow>"));
                                 this.updateEditorPropertiesSidebar(var1, var3);
                                 return true;
                              } else {
                                 this.rerenderEditableSelection(var1, var3);
                                 return true;
                              }
                           }
                        }
                     } else if (var7 == EditorPropertyField.NAME) {
                        String var37 = var6.trim();
                        if (var37.isEmpty()) {
                           this.sendEditorPlayerMessage(var1, MM.deserialize("<red>Name cannot be empty.</red>"));
                           return true;
                        } else {
                           var3.pendingPropertyField = null;
                           List var48 = this.getSidebarEditableTargetIds(var3);
                           if (var48.isEmpty()) {
                              this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Select an element first.</yellow>"));
                              return true;
                           } else {
                              boolean var57 = false;
                              boolean var65 = false;

                              for (Object var79_raw : var48) {
                                 String var79 = var79_raw != null ? var79_raw.toString() : null;
                                 boolean var84 = this.setTargetName(var3, var79, var37);
                                 if (var84 && !var65) {
                                    this.recordEditorMutation(var3);
                                    var65 = true;
                                 }

                                 var57 |= var84;
                              }

                              if (!var57) {
                                 this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>No changes to apply.</yellow>"));
                                 this.updateEditorPropertiesSidebar(var1, var3);
                                 return true;
                              } else {
                                 this.rerenderEditableSelection(var1, var3);
                                 return true;
                              }
                           }
                        }
                     } else if (var7 == EditorPropertyField.ID) {
                        String var36 = var6.trim();
                        if (var36.isEmpty()) {
                           this.sendEditorPlayerMessage(var1, MM.deserialize("<red>ID cannot be empty.</red>"));
                           return true;
                        } else if (!var36.matches("[A-Za-z0-9_.-]+")) {
                           this.sendEditorPlayerMessage(var1, MM.deserialize("<red>Invalid ID.</red> <gray>Allowed: letters, digits, _, ., -</gray>"));
                           return true;
                        } else {
                           List var47 = this.getSidebarEditableTargetIds(var3);
                           if (var47.size() != 1) {
                              this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>ID change works only for one selected element.</yellow>"));
                              return true;
                           } else {
                              String var56 = (String)var47.get(0);
                              if (this.isReservedEditorTargetId(var3, var36, var56)) {
                                 this.sendEditorPlayerMessage(var1, MM.deserialize("<red>This ID is reserved by the editor UI.</red>"));
                                 return true;
                              } else if (this.hasConflictingContentTargetId(var3, var36, var56)) {
                                 this.sendEditorPlayerMessage(var1, MM.deserialize("<red>This ID already exists in the current GUI.</red>"));
                                 return true;
                              } else {
                                 HoverElement var64 = this.findFirstByTargetId(var3, var56);
                                 String var73 = var64 == null ? "" : this.firstNonBlank(new String[]{var64.targetPath});
                                 var3.pendingPropertyField = null;
                                 boolean var13 = this.setTargetLogicalId(var3, var56, var36);
                                 if (var13) {
                                    this.recordEditorMutation(var3);
                                 }

                                 if (!var13) {
                                    this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>No changes to apply.</yellow>"));
                                    this.updateEditorPropertiesSidebar(var1, var3);
                                    return true;
                                 } else {
                                    this.rerenderEditableSelection(var1, var3);
                                    if (!var73.isBlank()) {
                                       String var83 = this.findTargetIdByPath(var3, var73);
                                       if (!var83.isBlank()) {
                                          var3.selectedElementId = var83;
                                          var3.additionalSelectedElementIds.clear();
                                          this.normalizeSelectionState(var3);
                                          this.updateEditorPropertiesSidebar(var1, var3);
                                       }
                                    }

                                    return true;
                                 }
                              }
                           }
                        }
                     } else {
                        Double var8 = this.parseSidebarInputValue(var6, var7);
                        boolean var9 = var7 == EditorPropertyField.OPACITY
                           && var3.activeTool == EditorTool.ANIMATION
                           && this.isAnimationTimelineSelectedRowMatchingSidebarField(var3, var7);
                        if (var9) {
                           String var10 = var6 == null ? "" : var6.trim().toLowerCase(Locale.ROOT).replace(" ", "");
                           String var11 = var10.endsWith("%") ? var10.substring(0, var10.length() - 1) : var10;
                           double var12 = this.parseDouble(var11, Double.NaN);
                           var8 = Double.isFinite(var12) ? (double)this.opacityPercentToAnimationRaw(var12) : null;
                        }

                        if (var8 == null) {
                           this.sendEditorPlayerMessage(var1, MM.deserialize("<red>Invalid value.</red> <gray>Enter a number or 'cancel'.</gray>"));
                           return true;
                        } else {
                           List var55 = this.getSidebarEditableTargetIds(var3);
                           String var63 = var55.isEmpty() ? null : this.firstNonBlank(new String[]{(String)var55.get(0)});
                           if (this.isSidebarPositionField(var7)) {
                              var8 = this.toSidebarRawPositionValue(var3, var63, var7, var8);
                           }

                           var3.pendingPropertyField = null;
                           double var72 = var63 == null ? Double.NaN : this.resolveSidebarFieldDragStartValue(var3, var63, var7);
                           boolean var14 = this.applySidebarPropertyValue(var1, var3, var7, var8);
                           if (!var14) {
                              this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>No changes to apply.</yellow>"));
                              this.updateEditorPropertiesSidebar(var1, var3);
                              return true;
                           } else if (var7 == EditorPropertyField.OPACITY) {
                              int var88 = var9 ? this.clampAnimationTimelineOpacityRaw(var8) : this.clampSidebarOpacityRaw(var8);
                              this.applySelectedOpacityToRenderedElements(var1, var3, var55, var88);
                              if (var3.activeTool == EditorTool.ANIMATION) {
                                 this.trySelectAnimationTimelineKeyframeForSidebarField(var3, var7);
                                 if (this.isAnimationTimelineSelectedRowMatchingSidebarField(var3, var7)) {
                                    this.syncAnimationTimelineSelectedKeyframeValueFromTarget(var3, EditorPropertyField.OPACITY, var8);
                                    if (Double.isFinite(var72)) {
                                       double var93 = var9 ? (double)this.opacityPercentToAnimationRaw(var72) : (double)this.opacityPercentToRaw(var72);
                                       this.applyAnimationTimelineSidebarFieldDeltaToSelectedKeyframes(var3, var7, var8 - var93);
                                    }

                                    this.renderAnimationTimelineKeyframes(var1, var3, this.firstNonBlank(new String[]{var3.animationTimelineTargetId}));
                                 }
                              }

                              this.updateEditorPropertiesSidebar(var1, var3);
                              return true;
                           } else {
                              boolean var15 = this.isSidebarPositionField(var7);
                              if (var15 && Double.isFinite(var72)) {
                                 double var16 = var7 == EditorPropertyField.POSITION_X ? var8 - var72 : 0.0;
                                 double var18 = var7 == EditorPropertyField.POSITION_Y ? var8 - var72 : 0.0;
                                 if (Math.abs(var16) > 1.0E-4 || Math.abs(var18) > 1.0E-4) {
                                    for (Object var21_raw : var55) {
                                       String var21 = var21_raw != null ? var21_raw.toString() : null;
                                       this.translateTargetElements(var1, var3, var21, var16, var18);
                                    }

                                    this.translateAnimationSidebarPositionDescendants(var1, var3, var55, var16, var18);
                                    if (var3.selectedElementId != null && var3.selectionOutlineVisible) {
                                       this.translateSelectionOutline(var1, var3, var16, var18);
                                    }
                                 }
                              }

                              if (var3.activeTool == EditorTool.ANIMATION) {
                                 this.trySelectAnimationTimelineKeyframeForSidebarField(var3, var7);
                                 if (this.isAnimationTimelineSelectedRowMatchingSidebarField(var3, var7)) {
                                    EditorPropertyField var92 = null;
                                    Double var17 = null;
                                    if (var7 == EditorPropertyField.POSITION_X
                                       || var7 == EditorPropertyField.POSITION_Y
                                       || var7 == EditorPropertyField.WIDTH
                                       || var7 == EditorPropertyField.HEIGHT
                                       || var7 == EditorPropertyField.ROTATION) {
                                       var92 = var7;
                                       var17 = var7 == EditorPropertyField.ROTATION ? this.normalizeSidebarRotation(var8) : var8;
                                    }

                                    this.syncAnimationTimelineSelectedKeyframeValueFromTarget(var3, var92, var17);
                                    if (Double.isFinite(var72)) {
                                       this.applyAnimationTimelineSidebarFieldDeltaToSelectedKeyframes(var3, var7, var8 - var72);
                                    }

                                    this.renderAnimationTimelineKeyframes(var1, var3, this.firstNonBlank(new String[]{var3.animationTimelineTargetId}));
                                 }
                              }

                              if (var15) {
                                 if (var3.activeTool == EditorTool.ANIMATION && Double.isFinite(var72)) {
                                    this.renderAnimationPreviewMotionPath(var1, var3);
                                 }

                                 if (Double.isFinite(var72)) {
                                    this.updateEditorPropertiesSidebar(var1, var3);
                                 } else {
                                    this.rerenderEditableSelection(var1, var3);
                                 }

                                 return true;
                              } else if (var7 == EditorPropertyField.LAYER) {
                                 this.rerenderEditableContent(var1, var3);
                                 return true;
                              } else {
                                 this.rerenderEditableSelection(var1, var3);
                                 return true;
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

   public boolean handleItemDesignInventoryClick(Player var1, ItemStack var2, ItemStack var3, ItemStack var4) {
      if (var1 == null) {
         return false;
      } else {
         EditorSession var5 = this.editorSessions.get(var1.getUniqueId());
         if (var5 != null && var5.editMode) {
            String var6 = this.firstNonBlank(new String[]{var5.pendingItemDesignInventoryTargetId});
            if (var6.isBlank() && var5.pendingPropertyField == EditorPropertyField.ITEM_DESIGN_ITEM) {
               String var7 = this.firstNonBlank(new String[]{this.getSidebarEditableTargetId(var5)});
               if (var7.isBlank() || !this.isSidebarItemDesignTarget(var5, var7)) {
                  return false;
               }

               var5.pendingPropertyField = null;
               var5.pendingItemDesignInventoryTargetId = var7;
               var6 = var7;
            }

            if (var6.isBlank()) {
               return false;
            } else {
               ItemStack var16 = this.resolveInventoryPickSource(var2, var3, var4, var1.getItemOnCursor());
               if (var16 == null || var16.getType() == Material.AIR) {
                  this.sendEditorPlayerMessage(var1, MM.deserialize("<gray>Click an item in inventory or type <white>cancel</white>.</gray>"));
                  return true;
               } else if (!this.isSidebarItemDesignTarget(var5, var6)) {
                  this.clearPendingItemDesignInventoryPick(var5);
                  this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Selected target is no longer an item element.</yellow>"));
                  this.updateEditorPropertiesSidebar(var1, var5);
                  return true;
               } else {
                  Material var8 = var16.getType();
                  ItemMeta var9 = var16.getItemMeta();
                  Integer var10 = null;
                  if (var9 != null && var9.hasCustomModelData()) {
                     var10 = var9.getCustomModelData();
                  }

                  Map var11 = this.serializeItemSelectionEnchantments(var16);
                  String var12 = this.buildSidebarItemToken(var8, "", var10);
                  this.clearPendingItemDesignInventoryPick(var5);
                  PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
                     if (var1.isOnline()) {
                        var1.setItemOnCursor(new ItemStack(Material.AIR));
                        var1.closeInventory();
                     }
                  }, 1L);
                  boolean var13 = this.setTargetItemToken(var5, var6, var12);
                  boolean var14 = this.setTargetItemEnchantments(var5, var6, var11);
                  boolean var15 = var13 || var14;
                  if (!var15) {
                     this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>No changes to apply.</yellow>"));
                     this.updateEditorPropertiesSidebar(var1, var5);
                     return true;
                  } else {
                     this.recordEditorMutation(var5);
                     this.rerenderEditableSelection(var1, var5);
                     return true;
                  }
               }
            }
         } else {
            return false;
         }
      }
   }
}
