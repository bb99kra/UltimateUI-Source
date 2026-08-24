package dev.xqedii.ultimateUI.service.gui.editor.shell.managers;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.gui.model.HoverElement;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.editor.interaction.GuiServiceEditorInteractionOverlaySupport;
import dev.xqedii.ultimateUI.service.gui.editor.shell.utils.AnimationMath;
import dev.xqedii.ultimateUI.service.gui.editor.shell.utils.ColorUtils;
import dev.xqedii.ultimateUI.service.gui.model.EditorPropertyField;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.gui.model.EditorSidebarTab;
import dev.xqedii.ultimateUI.service.gui.model.EditorTool;
import dev.xqedii.ultimateUI.service.hud.HudService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public abstract class AnimationTimelineOperationsManager extends AnimationTimelineOperationsManagerBase {
   protected AnimationTimelineOperationsManager(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   protected boolean shouldLogAnimationTimelineImageDebug(EditorSession var1, String var2) {
      return false;
   }

   protected String formatAnimationTimelineDebugNumber(double var1) {
      return !Double.isFinite(var1) ? "n/a" : String.format(Locale.ROOT, "%.3f", var1);
   }

   protected String formatAnimationTimelineDebugNumber(Double var1) {
      return var1 != null && Double.isFinite(var1) ? this.formatAnimationTimelineDebugNumber(var1.doubleValue()) : "n/a";
   }

   protected String formatAnimationTimelineDebugBounds(EditorRect var1) {
      return var1 == null
         ? "null"
         : "[x="
            + this.formatAnimationTimelineDebugNumber(var1.x)
            + " y="
            + this.formatAnimationTimelineDebugNumber(var1.y)
            + " w="
            + this.formatAnimationTimelineDebugNumber(var1.width)
            + " h="
            + this.formatAnimationTimelineDebugNumber(var1.height)
            + "]";
   }

   protected String formatAnimationTimelineDebugState(double[] var1) {
      if (var1 != null && var1.length >= 4) {
         StringBuilder var2 = new StringBuilder();
         var2.append("[x=").append(this.formatAnimationTimelineDebugNumber(var1[0]));
         var2.append(" y=").append(this.formatAnimationTimelineDebugNumber(var1[1]));
         var2.append(" w=").append(this.formatAnimationTimelineDebugNumber(var1[2]));
         var2.append(" h=").append(this.formatAnimationTimelineDebugNumber(var1[3]));
         var2.append(" rot=").append(var1.length >= 5 ? this.formatAnimationTimelineDebugNumber(var1[4]) : "n/a");
         var2.append(" opacity=").append(var1.length >= 6 ? this.formatAnimationTimelineDebugNumber(var1[5]) : "n/a");
         var2.append(']');
         return var2.toString();
      } else {
         return "null";
      }
   }

   protected boolean isAnimationTimelineDebugTick(EditorSession var1, double var2) {
      if (var1 != null && Double.isFinite(var2)) {
         double var4 = (double)this.clampAnimationTimelineTick(var1.animationTimelineTick);
         if (var1.animationTimelineSliderDragActive && var1.animationTimelineSliderDragTick != null && Double.isFinite(var1.animationTimelineSliderDragTick)) {
            var4 = Math.max(0.0, Math.min(400.0, var1.animationTimelineSliderDragTick));
         }

         if (Math.abs(var4 - var2) <= 0.001) {
            return true;
         } else if (this.isAnimationTimelineKeyframeSelected(var1)) {
            int var6 = this.clampAnimationTimelineTick(var1.animationTimelineSelectedTick);
            return Math.abs((double)var6 - var2) <= 0.001;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected void logAnimationTimelineImageDebug(
      EditorSession var1, String var2, String var3, double var4, Double var6, EditorRect var7, EditorRect var8, double[] var9, int var10, int var11
   ) {
      if (this.plugin != null && this.shouldLogAnimationTimelineImageDebug(var1, var3)) {
         String var12 = this.firstNonBlank(new String[]{var2, "unknown"});
         String var13 = this.firstNonBlank(new String[]{var3, "unknown"});
         String var14 = this.firstNonBlank(new String[]{this.resolveAnimationTimelineHierarchyTargetPath(var1, var13)});
         String var15 = var1 == null ? "n/a" : this.formatAnimationTimelineDebugNumber((double)this.clampAnimationTimelineTick(var1.animationTimelineTick));
         String var16 = var1 == null ? "n/a" : this.formatAnimationTimelineDebugNumber(var1.animationTimelineSliderDragTick);
         String var17 = var1 == null ? "n/a" : Integer.toString(this.clampAnimationTimelineTick(var1.animationTimelineSelectedTick));
         String var18 = var10 < 0 ? "n/a" : Integer.toString(var10);
         String var19 = var11 < 0 ? "n/a" : Integer.toString(var11);
         this.plugin
            .getLogger()
            .info(
               "[UltimateUI][AnimImageDebug] stage="
                  + var12
                  + " targetId="
                  + var13
                  + (var14.isBlank() ? "" : " targetPath=" + var14)
                  + " timelineTick="
                  + this.formatAnimationTimelineDebugNumber(var4)
                  + " delayedTick="
                  + this.formatAnimationTimelineDebugNumber(var6)
                  + " sessionTick="
                  + var15
                  + " sliderTick="
                  + var16
                  + " selectedTick="
                  + var17
                  + " base="
                  + this.formatAnimationTimelineDebugBounds(var7)
                  + " current="
                  + this.formatAnimationTimelineDebugBounds(var8)
                  + " desired="
                  + this.formatAnimationTimelineDebugState(var9)
                  + " posPoints="
                  + var18
                  + " scalePoints="
                  + var19
            );
      }
   }

   @Override
   protected boolean isAnimationTimelineForceableSidebarField(EditorPropertyField var1) {
      return var1 == EditorPropertyField.POSITION_X
         || var1 == EditorPropertyField.POSITION_Y
         || var1 == EditorPropertyField.WIDTH
         || var1 == EditorPropertyField.HEIGHT
         || var1 == EditorPropertyField.ROTATION
         || var1 == EditorPropertyField.OPACITY;
   }

   @Override
   protected String resolveAnimationTimelinePreservedInterpolationMode(Map<String, Object> var1, Object var2, int var3, int var4) {
      Object var5 = var2;
      if (!(var2 instanceof Map) && var3 != var4) {
         var5 = this.readAnimationTimelineTickValue(var1, var3);
      }

      if (var5 instanceof Map var6) {
         Map var7 = this.toStringObjectMap(var6);
         return var7 != null && !var7.isEmpty() ? this.normalizeAnimationTimelineInterpolationMode(this.stringValue(var7.get("interpolation"))) : "linear";
      } else {
         return "linear";
      }
   }

   @Override
   protected Object resolveAnimationTimelineForcedSidebarValue(
      EditorSession var1, String var2, String var3, int var4, EditorPropertyField var5, double var6, Object var8
   ) {
      if (var1 == null || var2 == null || var2.isBlank() || var3 == null || var3.isBlank() || var5 == null || !Double.isFinite(var6)) {
         return null;
      } else if ("rotation".equals(var3) && var5 == EditorPropertyField.ROTATION) {
         return this.snap1(this.normalizeSidebarRotation(var6));
      } else if ("opacity".equals(var3) && var5 == EditorPropertyField.OPACITY) {
         return this.clampAnimationTimelineOpacityRaw(var6);
      } else if (!"position".equals(var3) || var5 != EditorPropertyField.POSITION_X && var5 != EditorPropertyField.POSITION_Y) {
         if ("scale".equals(var3) && (var5 == EditorPropertyField.WIDTH || var5 == EditorPropertyField.HEIGHT)) {
            double var16 = Double.NaN;
            double var17 = Double.NaN;
            if (var8 instanceof Map var20) {
               Map var24 = this.toStringObjectMap(var20);
               if (var24 != null && !var24.isEmpty()) {
                  var16 = this.readMapPathDouble(var24, "width", Double.NaN);
                  var17 = this.readMapPathDouble(var24, "height", Double.NaN);
               }
            }

            if ((!Double.isFinite(var16) || !Double.isFinite(var17)) && this.buildAnimationTimelineScaleValue(var1, var2, var4) instanceof Map var25) {
               Map var27 = this.toStringObjectMap(var25);
               if (var27 != null && !var27.isEmpty()) {
                  if (!Double.isFinite(var16)) {
                     var16 = this.readMapPathDouble(var27, "width", Double.NaN);
                  }

                  if (!Double.isFinite(var17)) {
                     var17 = this.readMapPathDouble(var27, "height", Double.NaN);
                  }
               }
            }

            double var22 = Math.max(0.0, Math.abs(var6));
            if (var5 == EditorPropertyField.WIDTH) {
               var16 = var22;
            } else {
               var17 = var22;
            }

            if (!Double.isFinite(var16)) {
               var16 = 1.0;
            }

            if (!Double.isFinite(var17)) {
               var17 = 1.0;
            }

            LinkedHashMap var28 = new LinkedHashMap();
            var28.put("width", Math.max(0.0, this.snap1(var16)));
            var28.put("height", Math.max(0.0, this.snap1(var17)));
            return var28;
         } else {
            return null;
         }
      } else {
         double var9 = Double.NaN;
         double var11 = Double.NaN;
         if (var8 instanceof Map var13) {
            Map var14 = this.toStringObjectMap(var13);
            if (var14 != null && !var14.isEmpty()) {
               var9 = this.readMapPathDouble(var14, "x", Double.NaN);
               var11 = this.readMapPathDouble(var14, "y", Double.NaN);
            }
         }

         if ((!Double.isFinite(var9) || !Double.isFinite(var11)) && this.buildAnimationTimelineValueForRow(var1, var2, var3) instanceof Map var23) {
            Map var15 = this.toStringObjectMap(var23);
            if (var15 != null && !var15.isEmpty()) {
               if (!Double.isFinite(var9)) {
                  var9 = this.readMapPathDouble(var15, "x", Double.NaN);
               }

               if (!Double.isFinite(var11)) {
                  var11 = this.readMapPathDouble(var15, "y", Double.NaN);
               }
            }
         }

         double var19 = this.snap1(var6);
         if (var5 == EditorPropertyField.POSITION_X) {
            var9 = var19;
         } else {
            var11 = var19;
         }

         if (!Double.isFinite(var9)) {
            var9 = 0.0;
         }

         if (!Double.isFinite(var11)) {
            var11 = 0.0;
         }

         LinkedHashMap var26 = new LinkedHashMap();
         var26.put("x", this.snap1(var9));
         var26.put("y", this.snap1(var11));
         return var26;
      }
   }

   @Override
   protected void restoreAnimationTimelineRawTargetPositionToImplicitBase(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank() && var1.activeTool == EditorTool.ANIMATION) {
         EditorRect var3 = var1.animationTimelineImplicitBaseBoundsByTarget.get(var2);
         if (var3 != null) {
            EditorRect var4 = this.isAnimationTimelineImageGroupRoot(var1, var2) ? this.resolveAnimationTimelineImageGroupLogicalBounds(var1, var2) : null;
            if (var4 == null) {
               var4 = this.getTargetBounds(var1, var2);
            }

            if (var4 != null) {
               boolean var5 = Math.abs(var4.x - var3.x) <= 1.0E-4 && Math.abs(var4.y - var3.y) <= 1.0E-4;
               if (!var5) {
                  this.applyBoundsToTarget(var1, var2, new EditorRect(var3.x, var3.y, var4.width, var4.height));
               }
            }
         }
      }
   }

   @Override
   protected void restoreAnimationTimelineRawTargetToImplicitBase(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank() && var1.activeTool == EditorTool.ANIMATION) {
         EditorRect var3 = var1.animationTimelineImplicitBaseBoundsByTarget.get(var2);
         if (var3 != null) {
            EditorRect var4 = this.isAnimationTimelineImageGroupRoot(var1, var2) ? this.resolveAnimationTimelineImageGroupLogicalBounds(var1, var2) : null;
            if (var4 == null) {
               var4 = this.getTargetBounds(var1, var2);
            }

            if (var4 != null && !this.sameRect(var4, var3)) {
               this.applyBoundsToTarget(var1, var2, new EditorRect(var3.x, var3.y, var3.width, var3.height));
            }
         }
      }
   }

   @Override
   protected boolean animationTimelineValuesEqual(Object var1, Object var2) {
      if (var1 == var2) {
         return true;
      } else if (var1 != null && var2 != null) {
         if (var1 instanceof Number var3 && var2 instanceof Number var11) {
            return Math.abs(var3.doubleValue() - var11.doubleValue()) <= 1.0E-4;
         }

         if (var1 instanceof Map var10 && var2 instanceof Map var4) {
            Map var6 = var4;
            if (var10.size() != var4.size()) {
               return false;
            }

            for (Map.Entry<?, ?> var8 : ((Map<?, ?>)var10).entrySet()) {
               if (var8 != null) {
                  String var9 = (String)var8.getKey();
                  if (!var6.containsKey(var9)) {
                     return false;
                  }

                  if (!this.animationTimelineValuesEqual(var8.getValue(), var6.get(var9))) {
                     return false;
                  }
               }
            }

            return true;
         }

         return Objects.equals(var1, var2);
      } else {
         return false;
      }
   }

   @Override
   protected EditorRect resolveAnimationTimelineImplicitBaseBounds(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         EditorRect var3 = var1.animationTimelineImplicitBaseBoundsByTarget.get(var2);
         if (var3 == null) {
            List var4 = this.resolveAnimationTimelinePreviewTransformElements(var1, var2);
            if (var4 == null || var4.isEmpty()) {
               return null;
            }

            EditorRect var5 = this.isAnimationTimelineImageGroupRoot(var1, var2) ? this.resolveAnimationTimelineImageGroupLogicalBounds(var1, var2) : null;
            if (var5 == null) {
               var5 = this.getTargetBounds(var1, var2);
            }

            if (var5 == null) {
               return null;
            }

            var3 = new EditorRect(var5.x, var5.y, var5.width, var5.height);
            var1.animationTimelineImplicitBaseBoundsByTarget.put(var2, var3);
         }

         var1.animationTimelineImplicitBaseTargetId = var2;
         var1.animationTimelineImplicitBaseBounds = new EditorRect(var3.x, var3.y, var3.width, var3.height);
         return new EditorRect(var3.x, var3.y, var3.width, var3.height);
      } else {
         return null;
      }
   }

   @Override
   protected void applyAnimationTimelinePositionPreview(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.animationTimelinePanelVisible) {
         String var3 = this.firstNonBlank(new String[]{var2.animationTimelineTargetId});
         if (this.isAnimationTimelineTransformInteractionActive(var2)) {
            this.syncAnimationTimelinePreviewAppliedBoundsToCurrent(var2, var3);
         } else {
            double var4 = (double)this.clampAnimationTimelineTick(var2.animationTimelineTick);
            if (var2.animationTimelineSliderDragActive && var2.animationTimelineSliderDragTick != null && Double.isFinite(var2.animationTimelineSliderDragTick)
               )
             {
               var4 = Math.max(0.0, Math.min(400.0, var2.animationTimelineSliderDragTick));
            }

            LinkedHashSet var6 = this.resolveAnimationTimelinePreviewTargetIds(var2, var3);
            if (var6.isEmpty()) {
               this.clearAnimationTimelinePreviewOffset(var1, var2);
            } else {
               String var7 = this.firstNonBlank(new String[]{var3, var2.animationPreviewTargetId});
               if (var7.isBlank()) {
                  for (Object var9_raw : var6) {
                     String var9 = var9_raw != null ? var9_raw.toString() : null;
                     String var10 = this.firstNonBlank(new String[]{var9});
                     if (!var10.isBlank()) {
                        var7 = var10;
                        break;
                     }
                  }
               }

               this.syncAnimationTimelinePreviewAppliedBoundsToCurrent(var2, var7);
               boolean var21 = false;
               LinkedHashSet var22 = new LinkedHashSet();

               for (Object var11_raw : var6) {
                  String var11 = var11_raw != null ? var11_raw.toString() : null;
                  String var12 = this.firstNonBlank(new String[]{var11});
                  if (!var12.isBlank()) {
                     EditorRect var13 = null;
                     EditorRect var14 = null;
                     if (this.shouldLogAnimationTimelineImageDebug(var2, var12)) {
                        var13 = this.resolveAnimationTimelineStableBaseBounds(var2, var12);
                        var14 = this.resolveAnimationTimelineCurrentBoundsForPreview(var2, var12);
                     }

                     if (!this.isTargetVisible(var2, var12)) {
                        if (this.equalsNullable(var12, var7)) {
                           this.clearAnimationTimelinePrimaryPreviewOffset(var1, var2);
                        } else {
                           this.clearAnimationTimelineAdditionalPreviewOffset(var1, var2, var12);
                        }
                     } else {
                        double[] var15 = this.resolveAnimationTimelinePreviewState(var2, var12, var4);
                        if (var15 != null && var15.length >= 4) {
                           double var16 = var15.length >= 5 ? var15[4] : this.readTargetRotation(var2, var12);
                           double var18 = var15.length >= 6 ? var15[5] : (double)this.readTargetOpacity(var2, var12);
                           if (this.shouldLogAnimationTimelineImageDebug(var2, var12)) {
                              this.logAnimationTimelineImageDebug(var2, "editor-preview-apply-before", var12, var4, null, var13, var14, var15, -1, -1);
                           }

                           if (this.equalsNullable(var12, var7)) {
                              this.applyAnimationTimelinePreviewOffset(var1, var2, var12, var15[0], var15[1], var15[2], var15[3]);
                              this.applyAnimationTimelinePreviewRotation(var1, var2, var12, var16);
                              this.applyAnimationTimelinePreviewOpacity(var1, var2, var12, var18);
                              var21 = true;
                              if (this.shouldLogAnimationTimelineImageDebug(var2, var12)) {
                                 EditorRect var20 = this.resolveAnimationTimelineCurrentBoundsForPreview(var2, var12);
                                 this.logAnimationTimelineImageDebug(var2, "editor-preview-apply-after", var12, var4, null, var13, var20, var15, -1, -1);
                              }
                           } else {
                              this.applyAnimationTimelineAdditionalPreviewOffset(var1, var2, var12, var15[0], var15[1], var15[2], var15[3]);
                              this.applyAnimationTimelineAdditionalPreviewRotation(var1, var2, var12, var16);
                              this.applyAnimationTimelineAdditionalPreviewOpacity(var1, var2, var12, var18);
                              var22.add(var12);
                              if (this.shouldLogAnimationTimelineImageDebug(var2, var12)) {
                                 EditorRect var24 = this.resolveAnimationTimelineCurrentBoundsForPreview(var2, var12);
                                 this.logAnimationTimelineImageDebug(var2, "editor-preview-apply-after", var12, var4, null, var13, var24, var15, -1, -1);
                              }
                           }
                        } else if (this.equalsNullable(var12, var7)) {
                           this.clearAnimationTimelinePrimaryPreviewOffset(var1, var2);
                        } else {
                           this.clearAnimationTimelineAdditionalPreviewOffset(var1, var2, var12);
                        }
                     }
                  }
               }

               if (!var21 && !var7.isBlank()) {
                  this.clearAnimationTimelinePrimaryPreviewOffset(var1, var2);
               }

               this.clearStaleAnimationTimelineAdditionalPreviewOffsets(var1, var2, var22);
               this.clearStaleAnimationTimelineAdditionalPreviewRotations(var1, var2, var22);
               this.clearStaleAnimationTimelineAdditionalPreviewOpacities(var1, var2, var22);
            }
         }
      } else {
         this.clearAnimationTimelinePreviewOffset(var1, var2);
      }
   }

   @Override
   protected void syncAnimationTimelinePreviewAppliedBoundsToCurrent(EditorSession var1, String var2) {
      if (var1 != null) {
         String var3 = this.firstNonBlank(new String[]{var1.animationPreviewTargetId, var2});
         if (!var3.isBlank() && this.equalsNullable(var1.animationPreviewTargetId, var3)) {
            EditorRect var4 = this.resolveAnimationTimelineCurrentBoundsForPreview(var1, var3);
            if (var4 != null) {
               var1.animationPreviewAppliedBounds = var4;
               EditorRect var5 = var1.animationPreviewBaseBounds;
               if (var5 != null) {
                  var1.animationPreviewOffsetX = var4.x - var5.x;
                  var1.animationPreviewOffsetY = var4.y - var5.y;
               }
            }
         }

         if (!var1.animationPreviewAdditionalAppliedBoundsByTarget.isEmpty()) {
            for (String var6 : new LinkedHashSet<>(var1.animationPreviewAdditionalAppliedBoundsByTarget.keySet())) {
               EditorRect var7 = this.resolveAnimationTimelineCurrentBoundsForPreview(var1, var6);
               if (var7 != null) {
                  var1.animationPreviewAdditionalAppliedBoundsByTarget.put(var6, var7);
               }
            }
         }
      }
   }

   protected EditorRect resolveAnimationTimelineCurrentBoundsForPreview(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         boolean var3 = var1.activeTool == EditorTool.ANIMATION && var1.animationTimelinePanelVisible;
         String var4 = this.resolveSidebarImageGroupRootTarget(var1, var2);
         if (!var4.isBlank() && this.equalsNullable(var4, var2)) {
            String var14 = this.firstNonBlank(new String[]{this.resolveAnimationTimelineHierarchyTargetPath(var1, var4), var4});
            int var6 = var14.indexOf(".children.");
            if (var6 > 0) {
               var14 = var14.substring(0, var6);
            }

            EditorRect var7 = this.resolveAnimationTimelineImageGroupLiveRootBoundsFromElements(var1, var4, var14);
            if (var7 != null) {
               return var7;
            } else {
               if (!var3) {
                  EditorRect var8 = this.resolveAnimationTimelineImageGroupLiveOutlineBoundsFromElements(var1, var14);
                  if (var8 != null) {
                     Map var9 = this.resolveRawMapAtPath(var1.rawBlocks, var14);
                     double var10 = var9 == null ? 0.0 : this.readMapPathDouble(var9, "hitbox.x", 0.0);
                     if (!Double.isFinite(var10)) {
                        var10 = 0.0;
                     }

                     double var12 = this.resolveAnimationTimelineImageGroupOutlineToRootYOffset(var9, var8.height);
                     return new EditorRect(var8.x + var10, var8.y + var12, var8.width, var8.height);
                  }
               }

               EditorRect var15 = this.resolveAnimationTimelineImageGroupPreviewLogicalBounds(var1, var4);
               return var15 != null ? var15 : null;
            }
         } else {
            EditorRect var5 = this.getTargetBounds(var1, var2);
            return var5 == null ? null : new EditorRect(var5.x, var5.y, var5.width, var5.height);
         }
      } else {
         return null;
      }
   }

   protected EditorRect resolveAnimationTimelineImageGroupLiveRootBoundsFromElements(EditorSession var1, String var2, String var3) {
      if (var1 != null && var2 != null && !var2.isBlank() && var3 != null && !var3.isBlank() && var1.elements != null && !var1.elements.isEmpty()) {
         String var4 = var3 + ".children.";
         HoverElement var5 = null;
         double var6 = -1.0;

         for (Object var9_raw : var1.elements) {
            HoverElement var9 = (HoverElement)var9_raw;
            if (var9 != null && var9.baseScale != null) {
               String var10 = this.firstNonBlank(new String[]{var9.targetPath});
               String var11 = this.firstNonBlank(new String[]{this.targetIdOf(var9)});
               boolean var12 = !var10.isBlank() && this.equalsNullable(var10, var3);
               boolean var13 = this.equalsNullable(var11, var2) && (var10.isBlank() || !var10.startsWith(var4));
               if (var12 || var13) {
                  double var14 = Math.abs(var9.baseScale.getX()) * Math.abs(var9.baseScale.getY());
                  if (var14 > var6) {
                     var6 = var14;
                     var5 = var9;
                  }
               }
            }
         }

         return var5 == null ? null : this.resolveAnimationTimelineElementBounds(var5);
      } else {
         return null;
      }
   }

   protected EditorRect resolveAnimationTimelineImageGroupLiveOutlineBoundsFromElements(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank() && var1.elements != null && !var1.elements.isEmpty()) {
         EditorRect var3 = null;
         String var4 = var2 + ".children.";

         for (Object var6_raw : var1.elements) {
            HoverElement var6 = (HoverElement)var6_raw;
            String var7 = this.firstNonBlank(new String[]{var6 == null ? null : var6.targetPath});
            if (!var7.isBlank() && var7.startsWith(var4)) {
               var3 = this.mergeAnimationTimelineBounds(var3, this.resolveAnimationTimelineElementBounds(var6));
            }
         }

         return var3;
      } else {
         return null;
      }
   }

   protected EditorRect resolveAnimationTimelineElementBounds(HoverElement var1) {
      if (var1 != null && var1.baseLocation != null && var1.baseScale != null) {
         double var2 = var1.baseLocation.getX();
         double var4 = var1.baseLocation.getY();
         double var6 = Double.isFinite(var1.width) && var1.width > 0.0 ? var1.width : Math.abs(var1.baseScale.getX());
         double var8 = Double.isFinite(var1.height) && var1.height > 0.0 ? var1.height : Math.abs(var1.baseScale.getY());
         return Double.isFinite(var6) && Double.isFinite(var8) ? new EditorRect(var2, var4, Math.max(1.0E-4, var6), Math.max(1.0E-4, var8)) : null;
      } else {
         return null;
      }
   }

   protected EditorRect mergeAnimationTimelineBounds(EditorRect var1, EditorRect var2) {
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

   protected LinkedHashSet<String> resolveAnimationTimelinePreviewTargetIds(EditorSession var1, String var2) {
      LinkedHashSet var3 = new LinkedHashSet();
      if (var1 == null) {
         return var3;
      } else {
         String var4 = this.firstNonBlank(new String[]{var2});
         if (!var4.isBlank()) {
            var3.add(var4);
         }

         List var5 = var1.renderBlocks;
         if ((var5 == null || var5.isEmpty()) && var1.rawBlocks != null && !var1.rawBlocks.isEmpty()) {
            var5 = this.resolveRenderableBlocks(var1.rawBlocks, var1.components);
         }

         if (var5 != null && !var5.isEmpty()) {
            for (Map var7 : (List<Map>)(List<?>)var5) {
               if (var7 != null && !var7.isEmpty()) {
                  String var8 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var7, "__editor_target_id"))});
                  if (!var8.isBlank() && !var3.contains(var8)) {
                     Object var10;
                     Object var11;
                     Object var12;
                     boolean var10000;
                     label93: {
                        Object var9 = this.readMapPathValue(var7, "editor_animation.keyframes.position");
                        var10 = this.readMapPathValue(var7, "editor_animation.keyframes.scale");
                        var11 = this.readMapPathValue(var7, "editor_animation.keyframes.rotation");
                        var12 = this.readMapPathValue(var7, "editor_animation.keyframes.opacity");
                        if (var9 instanceof Map var14 && !var14.isEmpty()) {
                           var10000 = true;
                           break label93;
                        }

                        var10000 = false;
                     }

                     boolean var13;
                     label98: {
                        var13 = var10000;
                        if (var10 instanceof Map var15 && !var15.isEmpty()) {
                           var10000 = true;
                           break label98;
                        }

                        var10000 = false;
                     }

                     boolean var21;
                     label103: {
                        var21 = var10000;
                        if (var11 instanceof Map var16 && !var16.isEmpty()) {
                           var10000 = true;
                           break label103;
                        }

                        var10000 = false;
                     }

                     boolean var22;
                     label108: {
                        var22 = var10000;
                        if (var12 instanceof Map var17 && !var17.isEmpty()) {
                           var10000 = true;
                           break label108;
                        }

                        var10000 = false;
                     }

                     boolean var23 = var10000;
                     if ((var13 || var21 || var22 || var23) && (var4.isBlank() || !this.isAnimationTimelineHierarchyDescendantTarget(var1, var4, var8))) {
                        boolean var24 = false;

                        for (Object var19_raw : var3) {
                           String var19 = var19_raw != null ? var19_raw.toString() : null;
                           if (this.isAnimationTimelineHierarchyDescendantTarget(var1, var8, var19)) {
                              var24 = true;
                              break;
                           }
                        }

                        if (!var24) {
                           LinkedHashSet var25 = new LinkedHashSet();

                           for (Object var20_raw : var3) {
                              String var20 = var20_raw != null ? var20_raw.toString() : null;
                              if (!this.equalsNullable(var20, var4) && this.isAnimationTimelineHierarchyDescendantTarget(var1, var20, var8)) {
                                 var25.add(var20);
                              }
                           }

                           if (!var25.isEmpty()) {
                              var3.removeAll(var25);
                           }

                           var3.add(var8);
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

   protected boolean isAnimationTimelineHierarchyDescendantTarget(EditorSession var1, String var2, String var3) {
      String var4 = this.firstNonBlank(new String[]{var2});
      String var5 = this.firstNonBlank(new String[]{var3});
      if (var4.isBlank() || var5.isBlank() || this.equalsNullable(var4, var5)) {
         return false;
      } else if (var4.startsWith(var5 + ".children.")) {
         return true;
      } else {
         String var6 = this.resolveAnimationTimelineHierarchyTargetPath(var1, var4);
         String var7 = this.resolveAnimationTimelineHierarchyTargetPath(var1, var5);
         return !var6.isBlank() && !var7.isBlank() && !this.equalsNullable(var6, var7) ? var6.startsWith(var7 + ".children.") : false;
      }
   }

   @Override
   protected String resolveAnimationTimelineHierarchyTargetPath(EditorSession var1, String var2) {
      String var3 = this.firstNonBlank(new String[]{var2});
      if (!var3.isBlank() && var1 != null) {
         String var4 = this.resolveRawTargetPathByTargetId(var1, var3);
         if (!var4.isBlank()) {
            return var4;
         } else {
            HoverElement var5 = this.findFirstByTargetId(var1, var3);
            String var6 = this.firstNonBlank(new String[]{var5 == null ? null : var5.targetPath});
            if (!var6.isBlank()) {
               return var6;
            } else {
               if (var1.renderBlocks != null) {
                  for (Map var8 : var1.renderBlocks) {
                     if (var8 != null && !var8.isEmpty()) {
                        String var9 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var8, "__editor_target_id"))});
                        if (this.equalsNullable(var9, var3)) {
                           String var10 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var8, "__editor_target_path"))});
                           if (!var10.isBlank()) {
                              return var10;
                           }
                        }
                     }
                  }
               }

               return this.resolveRawMapAtPath(var1.rawBlocks, var3) != null ? var3 : "";
            }
         }
      } else {
         return "";
      }
   }

   @Override
   protected EditorRect resolveAnimationTimelineImageGroupLogicalBounds(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = this.firstNonBlank(new String[]{this.resolveSidebarImageGroupRootTarget(var1, var2)});
         if (var3.isBlank() && this.isAnimationTimelineImageGroupRoot(var1, var2)) {
            var3 = this.firstNonBlank(new String[]{var2});
         }

         if (var3.isBlank()) {
            return null;
         } else {
            EditorRect var4 = this.resolveAnimationTimelineCurrentBoundsForPreview(var1, var3);
            if (var4 != null) {
               return new EditorRect(var4.x, var4.y, var4.width, var4.height);
            } else {
               EditorRect var5 = this.resolveAnimationTimelineImageGroupPreviewLogicalBounds(var1, var3);
               return var5 != null ? new EditorRect(var5.x, var5.y, var5.width, var5.height) : null;
            }
         }
      } else {
         return null;
      }
   }

   protected EditorRect resolveAnimationTimelineImageGroupPreviewLogicalBounds(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         EditorRect var3 = null;
         if (this.equalsNullable(var2, var1.animationPreviewTargetId)) {
            var3 = var1.animationPreviewAppliedBounds;
         }

         if (var3 == null) {
            var3 = var1.animationPreviewAdditionalAppliedBoundsByTarget.get(var2);
         }

         return var3 == null ? null : new EditorRect(var3.x, var3.y, var3.width, var3.height);
      } else {
         return null;
      }
   }

   protected double resolveAnimationTimelineImageGroupOutlineToRootYOffset(Map<String, Object> var1, double var2) {
      if (!Double.isFinite(var2) || var2 <= 0.0) {
         return 250.0;
      } else if (this.isAnimationTimelineCompactGlyphMatrixImageGroup(var1)) {
         return 0.0;
      } else {
         int var4 = Math.max(1, this.countAnimationTimelineImageGroupFrameRows(var1));
         double var5 = var2 / (double)var4;
         return Double.isFinite(var5) && !(var5 <= 0.0) ? var5 : 250.0;
      }
   }

   protected boolean isAnimationTimelineCompactGlyphMatrixImageGroup(Map<String, Object> var1) {
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

   protected int countAnimationTimelineImageGroupFrameRows(Map<String, Object> var1) {
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
                           var4.add(Math.rint(var13 * 10000.0) / 10000.0);
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

   protected double[] resolveAnimationTimelinePreviewState(EditorSession var1, String var2, double var3) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         EditorRect var5 = this.resolveAnimationTimelineStableBaseBounds(var1, var2);
         if (var5 == null) {
            return null;
         } else {
            TreeMap var6 = this.readAnimationTimelinePositionPoints(var1, var2);
            TreeMap var7 = this.readAnimationTimelineScalePoints(var1, var2);
            TreeMap var8 = this.readAnimationTimelineRotationPoints(var1, var2);
            TreeMap var9 = this.readAnimationTimelineOpacityPoints(var1, var2);
            Map var10 = this.readAnimationTimelineInterpolationModes(var1, var2, "position");
            Map var11 = this.readAnimationTimelineInterpolationModes(var1, var2, "scale");
            Map var12 = this.readAnimationTimelineInterpolationModes(var1, var2, "rotation");
            Map var13 = this.readAnimationTimelineInterpolationModes(var1, var2, "opacity");
            if (var6.isEmpty() && var7.isEmpty() && var8.isEmpty() && var9.isEmpty()) {
               return null;
            } else {
               boolean var14 = !var6.isEmpty() || !var7.isEmpty();
               boolean var15 = this.isAnimationTimelineImageGroupRoot(var1, var2);
               double var16 = var15 ? 1.0E-4 : 1.0;
               double var18 = var5.x;
               double var20 = var5.y;
               double var22 = this.resolveAnimationTimelineStableBaseRotation(var1, var2);
               double var24 = Math.max(var16, var5.width);
               double var26 = Math.max(var16, var5.height);
               double var28 = var22;
               double var30 = Double.NaN;
               boolean var32 = false;
               HoverElement var33 = this.findFirstByTargetId(var1, var2);
               if (var33 != null) {
                  String var34 = this.firstNonBlank(new String[]{var33.type}).toLowerCase(Locale.ROOT);
                  var32 = "text".equals(var34);
               }

               if (!var32) {
                  Map var49 = this.resolveRawTargetByTargetId(var1, var2);
                  String var35 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var49, "type"))}).toLowerCase(Locale.ROOT);
                  var32 = "text".equals(var35);
               }

               if (!var14) {
                  EditorRect var50 = this.resolveAnimationTimelineCurrentBoundsForPreview(var1, var2);
                  if (var50 != null) {
                     var18 = var50.x;
                     var20 = var50.y;
                     var24 = Math.max(var16, var50.width);
                     var26 = Math.max(var16, var50.height);
                  }
               }

               if (!var6.isEmpty()) {
                  double[] var51 = this.interpolateAnimationTimelineVector2(var6, var3, var5.x, var5.y, var10);
                  var18 = var51[0];
                  var20 = var51[1];
               }

               double var52 = var18;
               double var36 = var20;
               if (!var7.isEmpty()) {
                  double var38 = Math.max(1.0E-4, 0.11666666666666667);
                  TreeMap var40 = new TreeMap();
                  TreeMap var41 = new TreeMap();

                  for (Map.Entry<?, ?> var43 : ((Map<?, ?>)var7).entrySet()) {
                     if (var43 != null && var43.getValue() != null && ((double[])var43.getValue()).length >= 4) {
                        double var44 = Math.max(var16, Math.abs(((double[])var43.getValue())[2]));
                        double var46 = Math.max(var16, Math.abs(((double[])var43.getValue())[3]));
                        if (var32) {
                           var46 = Math.max(var16, var46 * var38);
                        }

                        var40.put((Integer)var43.getKey(), var44);
                        var41.put((Integer)var43.getKey(), var46);
                     }
                  }

                  var24 = Math.max(var16, this.interpolateAnimationTimelineChannel(var40, var3, var5.width, var11));
                  var26 = Math.max(var16, this.interpolateAnimationTimelineChannel(var41, var3, var5.height, var11));
                  TreeMap var55 = this.extractAnimationTimelineScalePositionPoints(var7);
                  if (!var55.isEmpty()) {
                     double[] var56 = this.interpolateAnimationTimelineVector2(var55, var3, 0.0, 0.0, var11);
                     var52 = var18 + var56[0];
                     var36 = var20 + var56[1];
                  }
               }

               if (!var8.isEmpty()) {
                  var28 = this.normalizeSidebarRotation(this.interpolateAnimationTimelineChannel(var8, var3, var22, var12));
               }

               if (!var9.isEmpty()) {
                  double var53 = this.resolveAnimationTimelineStableBaseOpacity(var1, var2);
                  var30 = this.interpolateAnimationTimelineChannel(var9, var3, var53, var13);
                  var30 = (double)this.clampAnimationTimelineOpacityRaw(var30);
               }

               double[] var54 = new double[]{var52, var36, var24, var26, var28, var30};
               if (this.shouldLogAnimationTimelineImageDebug(var1, var2) && this.isAnimationTimelineDebugTick(var1, var3)) {
                  EditorRect var39 = this.resolveAnimationTimelineCurrentBoundsForPreview(var1, var2);
                  this.logAnimationTimelineImageDebug(var1, "editor-preview-state", var2, var3, null, var5, var39, var54, var6.size(), var7.size());
               }

               return var54;
            }
         }
      } else {
         return null;
      }
   }

   @Override
   protected EditorRect resolveAnimationTimelineStableBaseBounds(EditorSession var1, String var2) {
      if (var1 == null || var2 == null || var2.isBlank()) {
         return null;
      } else if (this.equalsNullable(var1.animationPreviewTargetId, var2) && var1.animationPreviewBaseBounds != null) {
         EditorRect var4 = var1.animationPreviewBaseBounds;
         return new EditorRect(var4.x, var4.y, var4.width, var4.height);
      } else {
         EditorRect var3 = var1.animationPreviewAdditionalBaseBoundsByTarget.get(var2);
         return var3 != null ? new EditorRect(var3.x, var3.y, var3.width, var3.height) : this.resolveAnimationTimelineImplicitBaseBounds(var1, var2);
      }
   }

   @Override
   protected double resolveAnimationTimelineStableBaseRotation(EditorSession var1, String var2) {
      if (var1 == null || var2 == null || var2.isBlank()) {
         return 0.0;
      } else if (this.equalsNullable(var1.animationPreviewTargetId, var2) && var1.animationPreviewBaseRotation != null) {
         return this.normalizeSidebarRotation(var1.animationPreviewBaseRotation);
      } else {
         Double var3 = var1.animationPreviewAdditionalBaseRotationByTarget.get(var2);
         return var3 != null ? this.normalizeSidebarRotation(var3) : this.normalizeSidebarRotation(this.readTargetRotation(var1, var2));
      }
   }

   @Override
   protected double resolveAnimationTimelineStableBaseOpacity(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Double var3 = this.resolveAnimationTimelineExplicitTickZeroOpacity(var1, var2);
         return var3 != null ? (double)this.clampAnimationTimelineOpacityRaw(var3) : 255.0;
      } else {
         return 255.0;
      }
   }

   @Override
   protected Double resolveAnimationTimelineExplicitTickZeroOpacity(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var3 = this.resolveRawTargetByTargetId(var1, var2);
         Map var4 = this.readAnimationTimelineRowMap(var3, "opacity");
         Object var5 = this.readAnimationTimelineTickValue(var4, 0);
         if (var5 == null) {
            return null;
         } else if (var5 instanceof Number var12) {
            return (double)this.clampAnimationTimelineOpacityRaw(var12.doubleValue());
         } else if (var5 instanceof Map var6) {
            if (var6.isEmpty()) {
               return null;
            } else {
               double var8 = this.readMapPathDouble(var6, "value", this.readMapPathDouble(var6, "opacity", Double.NaN));
               if (Double.isFinite(var8)) {
                  return (double)this.clampAnimationTimelineOpacityRaw(var8);
               } else {
                  double var10 = this.parseDouble(
                     var6.get("add"), this.parseDouble(var6.get("addOpacity"), this.parseDouble(var6.get("addopacity"), Double.NaN))
                  );
                  return Double.isFinite(var10) ? (double)this.clampAnimationTimelineOpacityRaw(255.0 + var10) : null;
               }
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   protected double readRenderedTargetRotation(EditorSession var1, String var2) {
      HoverElement var3 = this.findFirstByTargetId(var1, var2);
      return var3 != null && Double.isFinite(var3.rotationDeg)
         ? this.normalizeSidebarRotation(var3.rotationDeg)
         : this.normalizeSidebarRotation(this.readTargetRotation(var1, var2));
   }

   protected int readRenderedTargetOpacity(EditorSession var1, String var2) {
      HoverElement var3 = this.findFirstByTargetId(var1, var2);
      return var3 != null
         ? this.clampAnimationTimelineOpacityRaw((double)var3.opacity)
         : this.clampAnimationTimelineOpacityRaw((double)this.readTargetOpacity(var1, var2));
   }

   protected void applyAnimationTimelinePreviewRotationDelta(Player var1, EditorSession var2, String var3, double var4, double var6) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && Double.isFinite(var4) && Double.isFinite(var6)) {
         double var8 = var6 - var4;
         if (!(Math.abs(var8) <= 1.0E-4)) {
            List var10 = this.findTargetElements(var2, var3);
            if (var10 != null && !var10.isEmpty()) {
               boolean var11 = false;

               for (Object var13_raw : var10) {
                  HoverElement var13 = (HoverElement)var13_raw;
                  if (var13 != null) {
                     var13.rotationDeg = this.normalizeSidebarRotation(var13.rotationDeg + var8);
                     this.updateElementHud(var1, var13);
                     var11 = true;
                  }
               }

               if (var11 && this.isSelectedTarget(var2, var3) && var2.selectionOutlineVisible) {
                  this.renderSelectionOverlay(var1, var2);
               }
            }
         }
      }
   }

   protected void applyAnimationTimelinePreviewRotation(Player var1, EditorSession var2, String var3, double var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         if (!Double.isFinite(var4)) {
            this.clearAnimationTimelinePrimaryPreviewRotation(var1, var2, var3);
         } else {
            if (var2.animationPreviewBaseRotation == null || var2.animationPreviewAppliedRotation == null) {
               double var6 = this.readRenderedTargetRotation(var2, var3);
               var2.animationPreviewBaseRotation = var6;
               var2.animationPreviewAppliedRotation = var6;
            }

            double var10 = this.normalizeSidebarRotation(var4);
            double var8 = this.normalizeSidebarRotation(var2.animationPreviewAppliedRotation);
            if (!(Math.abs(var10 - var8) <= 1.0E-4)) {
               this.applyAnimationTimelinePreviewRotationDelta(var1, var2, var3, var8, var10);
               var2.animationPreviewAppliedRotation = var10;
            }
         }
      }
   }

   protected void applyAnimationTimelineAdditionalPreviewRotation(Player var1, EditorSession var2, String var3, double var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         if (!Double.isFinite(var4)) {
            this.clearAnimationTimelineAdditionalPreviewRotation(var1, var2, var3);
         } else {
            Double var6 = var2.animationPreviewAdditionalBaseRotationByTarget.get(var3);
            Double var7 = var2.animationPreviewAdditionalAppliedRotationByTarget.get(var3);
            if (var6 == null || var7 == null) {
               double var8 = this.readRenderedTargetRotation(var2, var3);
               var6 = var8;
               var7 = var8;
               var2.animationPreviewAdditionalBaseRotationByTarget.put(var3, var6);
               var2.animationPreviewAdditionalAppliedRotationByTarget.put(var3, var7);
            }

            double var13 = this.normalizeSidebarRotation(var4);
            double var10 = this.normalizeSidebarRotation(var7);
            if (!(Math.abs(var13 - var10) <= 1.0E-4)) {
               this.applyAnimationTimelinePreviewRotationDelta(var1, var2, var3, var10, var13);
               var2.animationPreviewAdditionalAppliedRotationByTarget.put(var3, Double.valueOf(var13));
            }
         }
      }
   }

   protected void clearAnimationTimelinePrimaryPreviewRotation(Player var1, EditorSession var2, String var3) {
      if (var2 != null && var3 != null && !var3.isBlank()) {
         Double var4 = var2.animationPreviewBaseRotation;
         Double var5 = var2.animationPreviewAppliedRotation;
         if (var1 != null && var4 != null && var5 != null) {
            double var6 = this.normalizeSidebarRotation(var4);
            double var8 = this.normalizeSidebarRotation(var5);
            if (Math.abs(var6 - var8) > 1.0E-4) {
               this.applyAnimationTimelinePreviewRotationDelta(var1, var2, var3, var8, var6);
            }
         }

         var2.animationPreviewBaseRotation = null;
         var2.animationPreviewAppliedRotation = null;
      }
   }

   protected void clearAnimationTimelineAdditionalPreviewRotation(Player var1, EditorSession var2, String var3) {
      if (var2 != null && var3 != null && !var3.isBlank()) {
         Double var4 = var2.animationPreviewAdditionalBaseRotationByTarget.get(var3);
         Double var5 = var2.animationPreviewAdditionalAppliedRotationByTarget.get(var3);
         if (var1 != null && var4 != null && var5 != null) {
            double var6 = this.normalizeSidebarRotation(var4);
            double var8 = this.normalizeSidebarRotation(var5);
            if (Math.abs(var6 - var8) > 1.0E-4) {
               this.applyAnimationTimelinePreviewRotationDelta(var1, var2, var3, var8, var6);
            }
         }

         var2.animationPreviewAdditionalBaseRotationByTarget.remove(var3);
         var2.animationPreviewAdditionalAppliedRotationByTarget.remove(var3);
      }
   }

   protected void clearStaleAnimationTimelineAdditionalPreviewRotations(Player var1, EditorSession var2, Set<String> var3) {
      if (var2 != null) {
         LinkedHashSet var4 = new LinkedHashSet<>(var2.animationPreviewAdditionalBaseRotationByTarget.keySet());
         var4.addAll(var2.animationPreviewAdditionalAppliedRotationByTarget.keySet());
         if (var3 != null && !var3.isEmpty()) {
            var4.removeAll(var3);
         }

         for (Object var6_raw : var4) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            this.clearAnimationTimelineAdditionalPreviewRotation(var1, var2, var6);
         }
      }
   }

   protected void applyAnimationTimelinePreviewOpacity(Player var1, EditorSession var2, String var3, double var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         if (!Double.isFinite(var4)) {
            this.clearAnimationTimelinePrimaryPreviewOpacity(var1, var2, var3);
         } else {
            if (var2.animationPreviewBaseOpacity == null || var2.animationPreviewAppliedOpacity == null) {
               int var6 = this.readRenderedTargetOpacity(var2, var3);
               var2.animationPreviewBaseOpacity = var6;
               var2.animationPreviewAppliedOpacity = var6;
            }

            int var8 = this.clampAnimationTimelineOpacityRaw(var4);
            int var7 = this.clampAnimationTimelineOpacityRaw((double)var2.animationPreviewAppliedOpacity.intValue());
            if (var8 != var7) {
               this.applyTargetOpacityToRenderedElements(var1, var2, var3, var8);
               var2.animationPreviewAppliedOpacity = var8;
            }
         }
      }
   }

   protected void applyAnimationTimelineAdditionalPreviewOpacity(Player var1, EditorSession var2, String var3, double var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         if (!Double.isFinite(var4)) {
            this.clearAnimationTimelineAdditionalPreviewOpacity(var1, var2, var3);
         } else {
            Integer var6 = var2.animationPreviewAdditionalBaseOpacityByTarget.get(var3);
            Integer var7 = var2.animationPreviewAdditionalAppliedOpacityByTarget.get(var3);
            if (var6 == null || var7 == null) {
               int var8 = this.readRenderedTargetOpacity(var2, var3);
               var6 = var8;
               var7 = var8;
               var2.animationPreviewAdditionalBaseOpacityByTarget.put(var3, var6);
               var2.animationPreviewAdditionalAppliedOpacityByTarget.put(var3, var7);
            }

            int var11 = this.clampAnimationTimelineOpacityRaw(var4);
            int var9 = this.clampAnimationTimelineOpacityRaw((double)var7.intValue());
            if (var11 != var9) {
               this.applyTargetOpacityToRenderedElements(var1, var2, var3, var11);
               var2.animationPreviewAdditionalAppliedOpacityByTarget.put(var3, Integer.valueOf(var11));
            }
         }
      }
   }

   protected void clearAnimationTimelinePrimaryPreviewOpacity(Player var1, EditorSession var2, String var3) {
      if (var2 != null && var3 != null && !var3.isBlank()) {
         Integer var4 = var2.animationPreviewBaseOpacity;
         Integer var5 = var2.animationPreviewAppliedOpacity;
         if (var1 != null && var4 != null && var5 != null) {
            int var6 = this.clampAnimationTimelineOpacityRaw((double)var4.intValue());
            int var7 = this.clampAnimationTimelineOpacityRaw((double)var5.intValue());
            if (var6 != var7) {
               this.applyTargetOpacityToRenderedElements(var1, var2, var3, var6);
            }
         }

         var2.animationPreviewBaseOpacity = null;
         var2.animationPreviewAppliedOpacity = null;
      }
   }

   protected void clearAnimationTimelineAdditionalPreviewOpacity(Player var1, EditorSession var2, String var3) {
      if (var2 != null && var3 != null && !var3.isBlank()) {
         Integer var4 = var2.animationPreviewAdditionalBaseOpacityByTarget.get(var3);
         Integer var5 = var2.animationPreviewAdditionalAppliedOpacityByTarget.get(var3);
         if (var1 != null && var4 != null && var5 != null) {
            int var6 = this.clampAnimationTimelineOpacityRaw((double)var4.intValue());
            int var7 = this.clampAnimationTimelineOpacityRaw((double)var5.intValue());
            if (var6 != var7) {
               this.applyTargetOpacityToRenderedElements(var1, var2, var3, var6);
            }
         }

         var2.animationPreviewAdditionalBaseOpacityByTarget.remove(var3);
         var2.animationPreviewAdditionalAppliedOpacityByTarget.remove(var3);
      }
   }

   protected void clearStaleAnimationTimelineAdditionalPreviewOpacities(Player var1, EditorSession var2, Set<String> var3) {
      if (var2 != null) {
         LinkedHashSet var4 = new LinkedHashSet<>(var2.animationPreviewAdditionalBaseOpacityByTarget.keySet());
         var4.addAll(var2.animationPreviewAdditionalAppliedOpacityByTarget.keySet());
         if (var3 != null && !var3.isEmpty()) {
            var4.removeAll(var3);
         }

         for (Object var6_raw : var4) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            this.clearAnimationTimelineAdditionalPreviewOpacity(var1, var2, var6);
         }
      }
   }

   protected void applyAnimationTimelineAdditionalPreviewOffset(
      Player var1, EditorSession var2, String var3, double var4, double var6, double var8, double var10
   ) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         boolean var12 = this.isAnimationTimelineImageGroupRoot(var2, var3);
         double var13 = var12 ? 1.0E-4 : 1.0;
         EditorRect var15 = var2.animationPreviewAdditionalBaseBoundsByTarget.get(var3);
         EditorRect var16 = var2.animationPreviewAdditionalAppliedBoundsByTarget.get(var3);
         if (var15 == null || var16 == null) {
            List var17 = this.resolveAnimationTimelinePreviewTransformElements(var2, var3);
            if (var17 == null || var17.isEmpty()) {
               this.clearAnimationTimelineAdditionalPreviewOffset(var1, var2, var3);
               return;
            }

            EditorRect var18 = this.resolveAnimationTimelineImplicitBaseBounds(var2, var3);
            EditorRect var19 = this.resolveAnimationTimelineCurrentBoundsForPreview(var2, var3);
            if (var19 == null) {
               var19 = this.getTargetBounds(var2, var3);
            }

            if (var19 == null) {
               return;
            }

            EditorRect var20 = var18 != null ? var18 : var19;
            var15 = new EditorRect(var20.x, var20.y, var20.width, var20.height);
            var16 = new EditorRect(var19.x, var19.y, var19.width, var19.height);
            var2.animationPreviewAdditionalBaseBoundsByTarget.put(var3, var15);
            var2.animationPreviewAdditionalAppliedBoundsByTarget.put(var3, var16);
         }

         EditorRect var22 = new EditorRect(var4, var6, Math.max(var13, var8), Math.max(var13, var10));
         if (!this.sameRect(var16, var22)) {
            this.transformAnimationTimelinePreviewBounds(var1, var2, var3, var16, var22);
            EditorRect var23 = this.resolveAnimationTimelineCurrentBoundsForPreview(var2, var3);
            if (var23 == null) {
               var23 = var12 ? var22 : this.getTargetBounds(var2, var3);
            }

            if (var23 == null) {
               var23 = var22;
            }

            var2.animationPreviewAdditionalAppliedBoundsByTarget.put(var3, var23);
         }
      }
   }

   protected void clearStaleAnimationTimelineAdditionalPreviewOffsets(Player var1, EditorSession var2, Set<String> var3) {
      if (var2 != null) {
         LinkedHashSet var4 = new LinkedHashSet<>(var2.animationPreviewAdditionalBaseBoundsByTarget.keySet());
         var4.addAll(var2.animationPreviewAdditionalAppliedBoundsByTarget.keySet());
         if (var3 != null && !var3.isEmpty()) {
            var4.removeAll(var3);
         }

         for (Object var6_raw : var4) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            this.clearAnimationTimelineAdditionalPreviewOffset(var1, var2, var6);
         }
      }
   }

   protected void clearAnimationTimelineAdditionalPreviewOffset(Player var1, EditorSession var2, String var3) {
      if (var2 != null && var3 != null && !var3.isBlank()) {
         EditorRect var4 = var2.animationPreviewAdditionalBaseBoundsByTarget.get(var3);
         EditorRect var5 = this.resolveAnimationTimelineImplicitBaseBounds(var2, var3);
         EditorRect var6 = var5 != null ? var5 : var4;
         EditorRect var7 = var2.animationPreviewAdditionalAppliedBoundsByTarget.get(var3);
         EditorRect var8 = this.resolveAnimationTimelineCurrentBoundsForPreview(var2, var3);
         if (var8 != null) {
            var7 = var8;
         }

         if (var1 != null && var6 != null && var7 != null && !this.sameRect(var6, var7)) {
            this.transformAnimationTimelinePreviewBounds(var1, var2, var3, var7, var6);
         }

         this.clearAnimationTimelineAdditionalPreviewRotation(var1, var2, var3);
         this.clearAnimationTimelineAdditionalPreviewOpacity(var1, var2, var3);
         var2.animationPreviewAdditionalBaseBoundsByTarget.remove(var3);
         var2.animationPreviewAdditionalAppliedBoundsByTarget.remove(var3);
      }
   }

   protected boolean isAnimationTimelineTransformInteractionActive(EditorSession var1) {
      if (var1 == null || var1.activeTool != EditorTool.ANIMATION) {
         return false;
      } else if (!var1.animationTimelineSliderDragActive && !var1.animationTimelineKeyframeDragActive) {
         EditorTool var2 = this.resolveAnimationTimelineSelectedTool(var1);
         if (var2 != EditorTool.CURSOR && var2 != EditorTool.SCALE) {
            return false;
         } else if (var1.moveDragActive || var1.sidebarFieldDragActive) {
            return true;
         } else if (var2 != EditorTool.SCALE) {
            return false;
         } else {
            boolean var3 = (long)Bukkit.getCurrentTick() - var1.lastLeftClickTick <= 2L;
            return var3 && var1.activeHandle != null;
         }
      } else {
         return false;
      }
   }

   protected void applyAnimationTimelinePreviewOffset(Player var1, EditorSession var2, String var3, double var4, double var6, double var8, double var10) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         boolean var12 = this.isAnimationTimelineImageGroupRoot(var2, var3);
         double var13 = var12 ? 1.0E-4 : 1.0;
         if (!this.equalsNullable(var2.animationPreviewTargetId, var3)) {
            this.clearAnimationTimelinePrimaryPreviewOffset(var1, var2);
            var2.animationPreviewTargetId = var3;
         }

         if (var2.animationPreviewBaseBounds == null || var2.animationPreviewAppliedBounds == null) {
            List var15 = this.resolveAnimationTimelinePreviewTransformElements(var2, var3);
            if (var15 == null || var15.isEmpty()) {
               this.clearAnimationTimelinePrimaryPreviewOffset(var1, var2);
               return;
            }

            EditorRect var16 = this.resolveAnimationTimelineImplicitBaseBounds(var2, var3);
            EditorRect var17 = this.resolveAnimationTimelineCurrentBoundsForPreview(var2, var3);
            if (var17 == null) {
               var17 = this.getTargetBounds(var2, var3);
            }

            if (var17 == null) {
               return;
            }

            EditorRect var18 = var16 != null ? var16 : var17;
            var2.animationPreviewBaseBounds = new EditorRect(var18.x, var18.y, var18.width, var18.height);
            var2.animationPreviewAppliedBounds = new EditorRect(var17.x, var17.y, var17.width, var17.height);
         }

         EditorRect var19 = var2.animationPreviewAppliedBounds;
         if (var19 != null) {
            EditorRect var20 = new EditorRect(var4, var6, Math.max(var13, var8), Math.max(var13, var10));
            if (!this.sameRect(var19, var20)) {
               this.transformAnimationTimelinePreviewBounds(var1, var2, var3, var19, var20);
               EditorRect var21 = this.resolveAnimationTimelineCurrentBoundsForPreview(var2, var3);
               if (var21 == null) {
                  var21 = var12 ? var20 : this.getTargetBounds(var2, var3);
               }

               if (var21 == null) {
                  var21 = var20;
               }

               var2.animationPreviewAppliedBounds = var21;
               EditorRect var22 = var2.animationPreviewBaseBounds;
               if (var22 != null) {
                  var2.animationPreviewOffsetX = var21.x - var22.x;
                  var2.animationPreviewOffsetY = var21.y - var22.y;
               }
            }
         }
      }
   }

   protected void transformAnimationTimelinePreviewBounds(Player var1, EditorSession var2, String var3, EditorRect var4, EditorRect var5) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var4 != null && var5 != null && !this.sameRect(var4, var5)) {
         EditorRect var6 = var4;
         EditorRect var7 = this.resolveAnimationTimelineCurrentBoundsForPreview(var2, var3);
         if (var7 == null) {
            var7 = this.getTargetBounds(var2, var3);
         }

         if (var7 != null) {
            var6 = var7;
         }

         double var8 = var6.width < 1.0E-4 ? 1.0 : var5.width / var6.width;
         double var10 = var6.height < 1.0E-4 ? 1.0 : var5.height / var6.height;
         int var12 = 0;

         for (HoverElement var14 : this.resolveAnimationTimelinePreviewTransformElements(var2, var3)) {
            if (var14 != null && var14.baseLocation != null && var14.baseScale != null) {
               double var15 = var14.baseLocation.getX();
               double var17 = var14.baseLocation.getY();
               double var19 = var14.baseScale.getX();
               double var21 = var14.baseScale.getY();
               double var23 = var6.width < 1.0E-4 ? var5.x + (var15 - var6.x) : var5.x + (var15 - var6.x) / var6.width * var5.width;
               double var25 = var6.height < 1.0E-4 ? var5.y + (var17 - var6.y) : var5.y + (var17 - var6.y) / var6.height * var5.height;
               double var27 = var6.width < 1.0E-4 ? var19 : var19 * var8;
               double var29 = var6.height < 1.0E-4 ? var21 : var21 * var10;
               var14.baseLocation.setX(var23);
               var14.baseLocation.setY(var25);
               var14.baseScale.setX(var27);
               var14.baseScale.setY(var29);
               var14.width = Math.max(1.0, Math.abs(var27));
               var14.height = Math.max(1.0, Math.abs(var29));
               var14.centerX = var23 + var27 / 2.0;
               var14.centerY = var25 + var29 / 2.0;
               this.updateElementHud(var1, var14);
               var12++;
            }
         }

         this.reanchorAnimationImageGroupGlyphTiles(var1, var2, var3);
         if (var12 == 0 && this.plugin != null && this.shouldLogAnimationTimelineImageDebug(var2, var3)) {
            this.plugin
               .getLogger()
               .info(
                  "[UltimateUI][AnimImageDebug] stage=editor-preview-transform-empty targetId="
                     + this.firstNonBlank(new String[]{var3, "unknown"})
                     + " from="
                     + this.formatAnimationTimelineDebugBounds(var6)
                     + " to="
                     + this.formatAnimationTimelineDebugBounds(var5)
               );
         }

         if (this.isSelectedTarget(var2, var3) && var2.selectionOutlineVisible) {
            boolean var31 = Math.abs(var5.width - var6.width) <= 1.0E-4 && Math.abs(var5.height - var6.height) <= 1.0E-4;
            if (var31) {
               double var32 = var5.x - var6.x;
               double var16 = var5.y - var6.y;
               if (Math.abs(var32) > 1.0E-4 || Math.abs(var16) > 1.0E-4) {
                  this.translateSelectionOutline(var1, var2, var32, var16);
               }
            } else {
               this.renderSelectionOverlay(var1, var2);
            }
         }
      }
   }

   protected void reanchorAnimationImageGroupGlyphTiles(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         if (this.isAnimationTimelineImageGroupRoot(var2, var3)) {
            List var4 = this.resolveAnimationTimelinePreviewTransformElements(var2, var3);
            if (var4 != null && !var4.isEmpty()) {
               HoverElement var5 = null;
               double var6 = -1.0;

               for (Object var9_raw : var4) {
                  HoverElement var9 = (HoverElement)var9_raw;
                  if (var9 != null && var9.baseLocation != null && var9.baseScale != null) {
                     double var10 = Math.abs(var9.baseScale.getX()) * Math.abs(var9.baseScale.getY());
                     if (var10 > var6) {
                        var6 = var10;
                        var5 = var9;
                     }
                  }
               }

               if (var5 != null) {
                  double var37 = var5.baseLocation.getX() - 29.0;
                  double var38 = var5.baseLocation.getY();
                  double var12 = 0.45281250000000006;
                  ArrayList var14 = new ArrayList();
                  double var15 = Double.POSITIVE_INFINITY;
                  double var17 = Double.NaN;
                  double var19 = Double.POSITIVE_INFINITY;
                  double var21 = Double.NaN;

                  for (Object var24_raw : var4) {
                     HoverElement var24 = (HoverElement)var24_raw;
                     if (var24 != null && var24 != var5 && var24.baseLocation != null && var24.baseScale != null && !"hitbox".equals(var24.type)) {
                        var14.add(var24);
                        double var25 = var24.baseLocation.getY();
                        double var27 = var24.baseLocation.getX();
                        if (var25 < var15) {
                           var15 = var25;
                           var17 = Math.abs(var24.baseScale.getY());
                        }

                        if (var27 < var19) {
                           var19 = var27;
                           var21 = Math.abs(var24.baseScale.getX());
                        }
                     }
                  }

                  if (!var14.isEmpty() && Double.isFinite(var15) && Double.isFinite(var19) && Double.isFinite(var17) && Double.isFinite(var21)) {
                     double var39 = var38
                        + GuiServiceEditorInteractionOverlaySupport.IMAGE_GLYPH_SCALE_DRIFT_FACTOR * (var17 - 64.0)
                        - var17 * 0.8833333333333333;
                     double var40 = var37 + var21 * 0.45281250000000006;
                     double var41 = var39 - var15;
                     double var29 = var40 - var19;
                     if (Double.isFinite(var29) && !(Math.abs(var29) < 1.0E-5) || Double.isFinite(var41) && !(Math.abs(var41) < 1.0E-5)) {
                        for (Object var32_raw : var14) {
                           HoverElement var32 = (HoverElement)var32_raw;
                           double var33 = var32.baseLocation.getX() + (Double.isFinite(var29) ? var29 : 0.0);
                           double var35 = var32.baseLocation.getY() + (Double.isFinite(var41) ? var41 : 0.0);
                           var32.baseLocation.setX(var33);
                           var32.baseLocation.setY(var35);
                           var32.centerX = var33 + var32.baseScale.getX() / 2.0;
                           var32.centerY = var35 + var32.baseScale.getY() / 2.0;
                           this.updateElementHud(var1, var32);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected List<HoverElement> resolveAnimationTimelinePreviewTransformElements(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank() && var1.elements != null && !var1.elements.isEmpty()) {
         String var3 = this.firstNonBlank(new String[]{var2});
         LinkedHashSet<HoverElement> var4 = new LinkedHashSet<>();
         if (this.isAnimationTimelineImageGroupRoot(var1, var3)) {
            String var5 = this.firstNonBlank(new String[]{this.resolveAnimationTimelineHierarchyTargetPath(var1, var3), var3});
            int var6 = var5.indexOf(".children.");
            if (var6 > 0) {
               var5 = var5.substring(0, var6);
            }

            String var7 = var5 + ".children.";

            for (Object var9_raw : var1.elements) {
               HoverElement var9 = (HoverElement)var9_raw;
               if (var9 != null) {
                  String var10 = this.firstNonBlank(new String[]{var9.targetPath});
                  String var11 = this.firstNonBlank(new String[]{this.targetIdOf(var9)});
                  boolean var12 = !var10.isBlank() && (this.equalsNullable(var10, var5) || var10.startsWith(var7));
                  boolean var13 = this.equalsNullable(var11, var3);
                  if (var12 || var13) {
                     var4.add(var9);
                  }
               }
            }
         }

         if (var4.isEmpty()) {
            for (String var15 : this.resolveAnimationPreviewHierarchyTargetIds(var1, var3)) {
               var4.addAll(this.findTargetElements(var1, var15));
            }
         }

         return (List<HoverElement>)(var4.isEmpty() ? Collections.emptyList() : new ArrayList<>(var4));
      } else {
         return Collections.emptyList();
      }
   }

   @Override
   protected double interpolateAnimationTimelineChannel(TreeMap<Integer, Double> var1, double var2, double var4) {
      return this.interpolateAnimationTimelineChannel(var1, var2, var4, null);
   }

   @Override
   protected double interpolateAnimationTimelineChannel(TreeMap<Integer, Double> var1, double var2, double var4, Map<Integer, String> var6) {
      return this.animationTimelineManager.interpolateChannel(var1, var2, var4, var6, this::normalizeAnimationTimelineInterpolationMode, "linear", 400);
   }

   @Override
   protected double[] interpolateAnimationTimelineVector2(TreeMap<Integer, double[]> var1, double var2, double var4, double var6) {
      return this.interpolateAnimationTimelineVector2(var1, var2, var4, var6, null);
   }

   @Override
   protected double[] interpolateAnimationTimelineVector2(TreeMap<Integer, double[]> var1, double var2, double var4, double var6, Map<Integer, String> var8) {
      return this.animationTimelineManager.interpolateVector2(var1, var2, var4, var6, var8, this::normalizeAnimationTimelineInterpolationMode, "linear", 400);
   }

   protected String resolveAnimationTimelineSegmentInterpolationMode(Map<Integer, String> var1, Integer var2, Integer var3) {
      return this.animationTimelineManager.resolveSegmentInterpolationMode(var1, var2, var3, this::normalizeAnimationTimelineInterpolationMode, "linear");
   }

   protected double applyAnimationTimelineInterpolation(String var1, double var2) {
      String var4 = this.normalizeAnimationTimelineInterpolationMode(var1);
      return AnimationMath.applyInterpolation(var4, var2);
   }

   protected double applyAnimationTimelineBounceOut(double var1) {
      return AnimationMath.applyBounceOut(var1);
   }

   protected double applyAnimationTimelineBounceIn(double var1) {
      return AnimationMath.applyBounceIn(var1);
   }

   protected double applyAnimationTimelineBounceInOut(double var1) {
      return AnimationMath.applyBounceInOut(var1);
   }

   protected double applyAnimationTimelineBackIn(double var1) {
      return AnimationMath.applyBackIn(var1);
   }

   protected double applyAnimationTimelineBackOut(double var1) {
      return AnimationMath.applyBackOut(var1);
   }

   protected double applyAnimationTimelineBackInOut(double var1) {
      return AnimationMath.applyBackInOut(var1);
   }

   protected double evaluateAnimationTimelineCubicBezier(double var1, double var3, double var5, double var7, double var9) {
      return AnimationMath.evaluateCubicBezier(var1, var3, var5, var7, var9);
   }

   protected double cubicBezierValue(double var1, double var3, double var5) {
      return AnimationMath.cubicBezierValue(var1, var3, var5);
   }

   protected double cubicBezierDerivative(double var1, double var3, double var5) {
      return AnimationMath.cubicBezierDerivative(var1, var3, var5);
   }

   @Override
   protected TreeMap<Integer, double[]> extractAnimationTimelineScalePositionPoints(TreeMap<Integer, double[]> var1) {
      TreeMap var2 = new TreeMap();
      if (var1 != null && !var1.isEmpty()) {
         for (Map.Entry<?, ?> var4 : ((Map<?, ?>)var1).entrySet()) {
            if (var4 != null && var4.getValue() != null && ((double[])var4.getValue()).length >= 4) {
               double var5 = ((double[])var4.getValue())[0];
               double var7 = ((double[])var4.getValue())[1];
               if (Double.isFinite(var5) && Double.isFinite(var7)) {
                  var2.put((Integer)var4.getKey(), new double[]{var5, var7});
               }
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   protected void clearAnimationTimelinePrimaryPreviewOffset(Player var1, EditorSession var2) {
      if (var2 != null) {
         String var3 = this.firstNonBlank(new String[]{var2.animationPreviewTargetId});
         EditorRect var4 = var2.animationPreviewBaseBounds;
         boolean var5 = var2.animationTimelinePanelVisible && var2.activeTool == EditorTool.ANIMATION;
         EditorRect var6 = !var3.isBlank() && var5 ? this.resolveAnimationTimelineImplicitBaseBounds(var2, var3) : null;
         EditorRect var7 = var4 != null ? var4 : var6;
         EditorRect var8 = var2.animationPreviewAppliedBounds;
         if (!var3.isBlank()) {
            EditorRect var9 = this.resolveAnimationTimelineCurrentBoundsForPreview(var2, var3);
            if (var9 != null) {
               var8 = var9;
            }
         }

         if (var8 == null && !var3.isBlank()) {
            var8 = this.resolveAnimationTimelineCurrentBoundsForPreview(var2, var3);
         }

         if (var1 != null && !var3.isBlank() && var7 != null && var8 != null && !this.sameRect(var7, var8)) {
            this.transformAnimationTimelinePreviewBounds(var1, var2, var3, var8, var7);
         }

         if (!var3.isBlank()) {
            this.clearAnimationTimelinePrimaryPreviewRotation(var1, var2, var3);
            this.clearAnimationTimelinePrimaryPreviewOpacity(var1, var2, var3);
         } else {
            var2.animationPreviewBaseRotation = null;
            var2.animationPreviewAppliedRotation = null;
            var2.animationPreviewBaseOpacity = null;
            var2.animationPreviewAppliedOpacity = null;
         }

         var2.animationPreviewTargetId = null;
         var2.animationPreviewOffsetX = 0.0;
         var2.animationPreviewOffsetY = 0.0;
         var2.animationPreviewBaseBounds = null;
         var2.animationPreviewAppliedBounds = null;
      }
   }

   @Override
   protected void clearAnimationTimelinePreviewOffset(Player var1, EditorSession var2) {
      if (var2 != null) {
         this.clearAnimationTimelinePrimaryPreviewOffset(var1, var2);
         LinkedHashSet var3 = new LinkedHashSet<>(var2.animationPreviewAdditionalBaseBoundsByTarget.keySet());
         var3.addAll(var2.animationPreviewAdditionalAppliedBoundsByTarget.keySet());
         var3.addAll(var2.animationPreviewAdditionalBaseRotationByTarget.keySet());
         var3.addAll(var2.animationPreviewAdditionalAppliedRotationByTarget.keySet());
         var3.addAll(var2.animationPreviewAdditionalBaseOpacityByTarget.keySet());
         var3.addAll(var2.animationPreviewAdditionalAppliedOpacityByTarget.keySet());

         for (Object var5_raw : var3) {
            String var5 = var5_raw != null ? var5_raw.toString() : null;
            this.clearAnimationTimelineAdditionalPreviewOffset(var1, var2, var5);
         }

         var2.animationPreviewAdditionalBaseBoundsByTarget.clear();
         var2.animationPreviewAdditionalAppliedBoundsByTarget.clear();
         var2.animationPreviewAdditionalBaseRotationByTarget.clear();
         var2.animationPreviewAdditionalAppliedRotationByTarget.clear();
         var2.animationPreviewAdditionalBaseOpacityByTarget.clear();
         var2.animationPreviewAdditionalAppliedOpacityByTarget.clear();
      }
   }

   protected EditorPropertyField resolveSidebarValueHitboxField(EditorSession var1, double var2, double var4) {
      if (var1 == null || var1.rightSidebarTab == null) {
         return null;
      } else if (var1.rightSidebarTab == EditorSidebarTab.PROPERTIES) {
         if (this.isKeyframePropertiesSidebarMode(var1)) {
            if (this.isAnimationTimelineOpacityKeyframeSelected(var1)) {
               return !this.isInsideShellBlock(var1, "editor_val_keyframe_opacity_hitbox", var2, var4)
                     && !this.isInsideShellBlock(var1, "keyframe_opacity_hitbox", var2, var4)
                     && !this.isInsideShellBlock(var1, "editor_val_keyframe_opacity", var2, var4)
                     && !this.isInsideShellBlock(var1, "keyframe_opacity", var2, var4)
                  ? null
                  : EditorPropertyField.OPACITY;
            } else if (!this.isAnimationTimelineRotationKeyframeSelected(var1)) {
               EditorPropertyField var6 = this.isAnimationTimelineScaleKeyframeSelected(var1) ? EditorPropertyField.WIDTH : EditorPropertyField.POSITION_X;
               EditorPropertyField var7 = this.isAnimationTimelineScaleKeyframeSelected(var1) ? EditorPropertyField.HEIGHT : EditorPropertyField.POSITION_Y;
               if (this.isInsideShellBlock(var1, "editor_val_keyframe_x_hitbox", var2, var4) || this.isInsideShellBlock(var1, "keyframe_x_hitbox", var2, var4)) {
                  return var6;
               } else {
                  return !this.isInsideShellBlock(var1, "editor_val_keyframe_y_hitbox", var2, var4)
                        && !this.isInsideShellBlock(var1, "keyframe_y_hitbox", var2, var4)
                     ? null
                     : var7;
               }
            } else {
               return !this.isInsideShellBlock(var1, "editor_val_keyframe_rotation_hitbox", var2, var4)
                     && !this.isInsideShellBlock(var1, "keyframe_rotation_hitbox", var2, var4)
                     && !this.isInsideShellBlock(var1, "editor_val_keyframe_rotation", var2, var4)
                     && !this.isInsideShellBlock(var1, "keyframe_rotation", var2, var4)
                  ? null
                  : EditorPropertyField.ROTATION;
            }
         } else {
            if (!this.isActionsSidebarMode(var1)) {
               if (this.isInsideShellBlock(var1, "editor_val_width_hitbox", var2, var4)) {
                  return EditorPropertyField.WIDTH;
               }

               if (this.isInsideShellBlock(var1, "editor_val_height_hitbox", var2, var4)) {
                  return EditorPropertyField.HEIGHT;
               }

               if (this.isInsideShellBlock(var1, "editor_val_x_hitbox", var2, var4)) {
                  return EditorPropertyField.POSITION_X;
               }

               if (this.isInsideShellBlock(var1, "editor_val_y_hitbox", var2, var4)) {
                  return EditorPropertyField.POSITION_Y;
               }

               if (this.isInsideShellBlock(var1, "editor_val_rotation_hitbox", var2, var4)) {
                  return EditorPropertyField.ROTATION;
               }

               if (this.isInsideShellBlock(var1, "editor_val_layer_hitbox", var2, var4)) {
                  return EditorPropertyField.LAYER;
               }

               if (this.isInsideShellBlock(var1, "editor_val_name_hitbox", var2, var4)) {
                  return EditorPropertyField.NAME;
               }

               if (this.isInsideShellBlock(var1, "editor_val_id_hitbox", var2, var4)) {
                  return EditorPropertyField.ID;
               }

               if (this.isInsideShellBlock(var1, "editor_val_visible_hitbox", var2, var4)) {
                  return EditorPropertyField.VISIBLE;
               }

               if (this.isInsideShellBlock(var1, "editor_val_locked_hitbox", var2, var4)) {
                  return EditorPropertyField.LOCKED;
               }

               if (this.isInsideShellBlock(var1, "editor_val_anchor_hitbox", var2, var4)) {
                  return EditorPropertyField.ANCHOR;
               }
            }

            return null;
         }
      } else {
         if (var1.rightSidebarTab == EditorSidebarTab.DESIGN) {
            if (this.isItemDesignSidebarMode(var1)) {
               if (this.isInsideShellBlock(var1, "editor_val_item_design_hovereffect_hitbox", var2, var4)) {
                  return null;
               }

               if (this.isInsideShellBlock(var1, "editor_val_item_design_item_hitbox", var2, var4)) {
                  return EditorPropertyField.ITEM_DESIGN_ITEM;
               }

               if (this.isInsideShellBlock(var1, "editor_val_item_design_glowing_hitbox", var2, var4)) {
                  return EditorPropertyField.ITEM_DESIGN_GLOWING;
               }

               return null;
            }

            if (this.isInsideShellBlock(var1, "editor_val_design_fill_style_hitbox", var2, var4)
               || this.isInsideShellBlock(var1, "editor_val_design_border_radius_hitbox", var2, var4)
               || this.isInsideShellBlock(var1, "editor_val_design_hover_color_hitbox", var2, var4)
               || this.isInsideShellBlock(var1, "editor_val_design_hover_effect_hitbox", var2, var4)) {
               return null;
            }

            if (this.isInsideShellBlock(var1, "editor_val_design_fill_color_hitbox", var2, var4)) {
               return EditorPropertyField.DESIGN_COLOR;
            }

            if (this.isInsideShellBlock(var1, "editor_val_design_fill_opacity_hitbox", var2, var4)) {
               return EditorPropertyField.OPACITY;
            }

            if (this.isInsideShellBlock(var1, "editor_val_design_border_color_hitbox", var2, var4)) {
               return EditorPropertyField.DESIGN_BORDER_COLOR;
            }
         }

         return null;
      }
   }

   protected boolean handleKeyframeInterpolationSidebarClick(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && this.isKeyframePropertiesSidebarMode(var2)) {
         boolean var7 = this.isAnimationTimelineRotationKeyframeSelected(var2);
         boolean var8 = this.isAnimationTimelineOpacityKeyframeSelected(var2);
         boolean var9 = this.isInsideShellBlock(var2, "editor_val_keyframe_interpolation_hitbox", var3, var5)
            || this.isInsideShellBlock(var2, "keyframe_interpolation_hitbox", var3, var5)
            || this.isInsideShellBlock(var2, "editor_val_keyframe_interpolation", var3, var5)
            || this.isInsideShellBlock(var2, "keyframe_interpolation", var3, var5);
         if (var7) {
            var9 = var9
               || this.isInsideShellBlock(var2, "editor_val_keyframe_interpolation_rotation_hitbox", var3, var5)
               || this.isInsideShellBlock(var2, "keyframe_interpolation_rotation_hitbox", var3, var5)
               || this.isInsideShellBlock(var2, "editor_val_keyframe_interpolation_rotation", var3, var5)
               || this.isInsideShellBlock(var2, "keyframe_interpolation_rotation", var3, var5);
         }

         if (var8) {
            var9 = var9
               || this.isInsideShellBlock(var2, "editor_val_keyframe_interpolation_opacity_hitbox", var3, var5)
               || this.isInsideShellBlock(var2, "keyframe_interpolation_opacity_hitbox", var3, var5)
               || this.isInsideShellBlock(var2, "editor_val_keyframe_interpolation_opacity", var3, var5)
               || this.isInsideShellBlock(var2, "keyframe_interpolation_opacity", var3, var5);
         }

         if (!var9) {
            return false;
         } else if (!this.isAnimationTimelineKeyframeSelected(var2)) {
            return true;
         } else {
            String var10 = this.firstNonBlank(new String[]{var2.animationTimelineSelectedTargetId});
            String var11 = this.firstNonBlank(new String[]{var2.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT);
            int var12 = this.clampAnimationTimelineTick(var2.animationTimelineSelectedTick);
            if (!var10.isBlank() && ("position".equals(var11) || "scale".equals(var11) || "rotation".equals(var11) || "opacity".equals(var11))) {
               Map var13 = this.resolveRawTargetByTargetId(var2, var10);
               Map var14 = this.readAnimationTimelineRowMap(var13, var11);
               Object var15 = this.readAnimationTimelineTickValue(var14, var12);
               String var16;
               if (var15 instanceof Map var17) {
                  var16 = this.normalizeAnimationTimelineInterpolationMode(this.stringValue(var17.get("interpolation")));
               } else {
                  if (!"rotation".equals(var11) && !"opacity".equals(var11) || !(var15 instanceof Number)) {
                     return true;
                  }

                  var16 = "linear";
               }

               String var30 = this.nextAnimationTimelineInterpolationMode(var16);
               LinkedHashMap var18 = this.resolveAnimationTimelineSelectedTicksByRow(var2, var10);
               if (var18.isEmpty()) {
                  var18.put(var11, List.of(var12));
               }

               boolean var19 = false;

               for (Map.Entry<?, ?> var21 : ((Map<?, ?>)var18).entrySet()) {
                  if (var21 != null && var21.getKey() != null && var21.getValue() != null && !((List)var21.getValue()).isEmpty()) {
                     String var22 = this.firstNonBlank(new String[]{(String)var21.getKey()}).toLowerCase(Locale.ROOT);
                     if ("position".equals(var22) || "scale".equals(var22) || "rotation".equals(var22) || "opacity".equals(var22)) {
                        Map var23 = this.readAnimationTimelineRowMap(var13, var22);
                        if (var23 != null && !var23.isEmpty()) {
                           for (int var25 : (List<Integer>)(List<?>)var21.getValue()) {
                              Object var26 = this.readAnimationTimelineTickValue(var23, var25);
                              if (("rotation".equals(var22) || "opacity".equals(var22)) && var26 instanceof Number) {
                                 Number var31 = (Number)var26;
                                 if (!"linear".equals(var30)) {
                                    LinkedHashMap var28 = new LinkedHashMap();
                                    if ("rotation".equals(var22)) {
                                       var28.put("value", this.normalizeSidebarRotation(var31.doubleValue()));
                                    } else {
                                       var28.put("value", this.clampAnimationTimelineOpacityRaw(var31.doubleValue()));
                                    }

                                    var28.put("interpolation", var30);
                                    this.removeAnimationTimelineTickValue(var23, var25);
                                    var23.put(Integer.toString(this.clampAnimationTimelineTick(var25)), var28);
                                    var19 = true;
                                 }
                              } else if (var26 instanceof Map) {
                                 Map var27 = (Map)var26;
                                 String var29 = this.normalizeAnimationTimelineInterpolationMode(this.stringValue(var27.get("interpolation")));
                                 if (!this.equalsNullable(var29, var30)) {
                                    if ("linear".equals(var30)) {
                                       var27.remove("interpolation");
                                    } else {
                                       var27.put("interpolation", var30);
                                    }

                                    var19 = true;
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               if (!var19) {
                  return true;
               } else {
                  var2.animationTimelineRenderedSignature = null;
                  this.recordEditorMutation(var2);
                  this.renderAnimationTimelineKeyframes(var1, var2, var10);
                  this.applyAnimationTimelinePositionPreview(var1, var2);
                  this.refreshSidebarAfterTimelineSelectionChange(var1, var2);
                  return true;
               }
            } else {
               return true;
            }
         }
      } else {
         return false;
      }
   }

   protected String nextAnimationTimelineInterpolationMode(String var1) {
      String var2 = this.normalizeAnimationTimelineInterpolationMode(var1);

      return switch (var2) {
         case "linear" -> "smooth";
         case "smooth" -> "ease-in";
         case "ease-in" -> "ease-out";
         case "ease-out" -> "bezier";
         case "bezier" -> "bounce";
         case "bounce" -> "bounce-in";
         case "bounce-in" -> "bounce-out";
         case "bounce-out" -> "back";
         case "back" -> "back-in";
         case "back-in" -> "back-out";
         default -> "linear";
      };
   }

   @Override
   protected String normalizeAnimationTimelineInterpolationMode(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim().toLowerCase(Locale.ROOT).replace('_', '-').replace(' ', '-');

      return switch (var2) {
         case "ease-in-out", "easeinout", "ease-out-in", "easeoutin" -> "smooth";
         case "smooth" -> "smooth";
         case "ease-in", "easein", "smooth-in", "smoothin" -> "ease-in";
         case "ease-out", "easeout", "smooth-out", "smoothout" -> "ease-out";
         case "bezier" -> "bezier";
         case "bounce" -> "bounce";
         case "bounce-in", "bouncein" -> "bounce-in";
         case "bounce-out", "bounceout" -> "bounce-out";
         case "back" -> "back";
         case "back-in", "backin" -> "back-in";
         case "back-out", "backout" -> "back-out";
         default -> "linear";
      };
   }

   protected String formatAnimationTimelineInterpolationMode(String var1) {
      String var2 = this.normalizeAnimationTimelineInterpolationMode(var1);

      return switch (var2) {
         case "smooth" -> "Smooth";
         case "ease-in" -> "Ease-In";
         case "ease-out" -> "Ease-Out";
         case "bezier" -> "Bezier";
         case "bounce" -> "Bounce";
         case "bounce-in" -> "Bounce-In";
         case "bounce-out" -> "Bounce-Out";
         case "back" -> "Back";
         case "back-in" -> "Back-In";
         case "back-out" -> "Back-Out";
         default -> "Linear";
      };
   }

   protected String resolveAnimationTimelineKeyframeInterpolationMode(Map<String, Object> var1, int var2) {
      return this.readAnimationTimelineTickValue(var1, var2) instanceof Map var4
         ? this.normalizeAnimationTimelineInterpolationMode(this.stringValue(var4.get("interpolation")))
         : "linear";
   }

   protected String resolveAnimationTimelineKeyframeInterpolationMode(EditorSession var1, String var2, String var3, int var4) {
      if (var1 != null && var2 != null && !var2.isBlank() && var3 != null && !var3.isBlank()) {
         Map var5 = this.resolveRawTargetByTargetId(var1, var2);
         Map var6 = this.readAnimationTimelineRowMap(var5, var3);
         return this.resolveAnimationTimelineKeyframeInterpolationMode(var6, var4);
      } else {
         return "linear";
      }
   }

   @Override
   protected String resolveAnimationTimelineKeyframeGlyph(EditorSession var1, String var2, String var3, int var4) {
      String var5 = this.resolveAnimationTimelineKeyframeInterpolationMode(var1, var2, var3, var4);

      return switch (var5) {
         case "smooth" -> "\uef35";
         case "ease-in" -> "\uef35";
         case "ease-out" -> "\uef35";
         case "bezier" -> "\uef36";
         case "bounce" -> "\uef35";
         case "bounce-in" -> "\uef35";
         case "bounce-out" -> "\uef35";
         case "back" -> "\uef35";
         case "back-in" -> "\uef35";
         case "back-out" -> "\uef35";
         default -> "\uef31";
      };
   }

   @Override
   protected Map<Integer, String> readAnimationTimelineInterpolationModes(EditorSession var1, String var2, String var3) {
      if (var1 != null && var2 != null && !var2.isBlank() && var3 != null && !var3.isBlank()) {
         Map var4 = this.resolveRawTargetByTargetId(var1, var2);
         Map var5 = this.readAnimationTimelineRowMap(var4, var3);
         if (var5 != null && !var5.isEmpty()) {
            TreeMap var6 = new TreeMap();

            for (Map.Entry<?, ?> var8 : ((Map<?, ?>)var5).entrySet()) {
               if (var8 != null && var8.getKey() != null) {
                  double var9 = this.parseDouble(var8.getKey(), Double.NaN);
                  if (Double.isFinite(var9)) {
                     int var11 = this.clampAnimationTimelineTick((int)Math.round(var9));
                     Object var13 = var8.getValue();
                     if (var13 instanceof Map) {
                        Map var12 = (Map)var13;
                        Map var13Map = this.toStringObjectMap(var12);
                        if (var13Map != null && !var13Map.isEmpty()) {
                           String var14 = this.normalizeAnimationTimelineInterpolationMode(this.stringValue(var13Map.get("interpolation")));
                           if (!"linear".equals(var14)) {
                              var6.put(var11, var14);
                           }
                        }
                     }
                  }
               }
            }

            return var6;
         } else {
            return Collections.emptyMap();
         }
      } else {
         return Collections.emptyMap();
      }
   }

   @Override
   protected void setAnimationTimelineKeyframeDropdownVisible(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         if (var3 && var2.keyframeTimelineDropdownVisible) {
            this.setAnimationTimelineTimelineDropdownVisible(var1, var2, false);
         }

         if (var2.keyframeDropdownVisible == var3) {
            if (var3) {
               this.updateAnimationTimelineKeyframeDropdownHover(var1, var2, var2.cursorX + var2.hitboxOffsetX, var2.cursorY + var2.hitboxOffsetY);
            }
         } else {
            double var4 = var2.cursorX + var2.hitboxOffsetX;
            double var6 = var2.cursorY + var2.hitboxOffsetY;
            if (var3) {
               this.moveAnimationTimelineContextDropdownTo(var1, var2, "keyframe_dropdown", var4, var6);
            }

            var2.keyframeDropdownVisible = var3;
            if (!var3) {
               var2.keyframeDropdownHoverTargetId = null;
            } else {
               this.playEditorSfx(var1, var2, "dropdown-opened");
            }

            this.beginShellOpacityBatch(var2);

            try {
               this.setSidebarPanelVisible(var1, var2, "keyframe_dropdown", var3);
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }

            if (var3) {
               this.updateAnimationTimelineKeyframeDropdownHover(var1, var2, var4, var6);
            } else {
               for (Object var9_raw : KEYFRAME_DROPDOWN_ITEM_IDS) {
                  String var9 = var9_raw != null ? var9_raw.toString() : null;
                  this.setAnimationTimelineKeyframeDropdownItemVisual(var1, var2, var9, false);
               }
            }
         }
      }
   }

   @Override
   protected void setAnimationTimelineTimelineDropdownVisible(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         if (var3 && var2.keyframeDropdownVisible) {
            this.setAnimationTimelineKeyframeDropdownVisible(var1, var2, false);
         }

         if (var2.keyframeTimelineDropdownVisible == var3) {
            if (var3) {
               this.updateAnimationTimelineTimelineDropdownHover(var1, var2, var2.cursorX + var2.hitboxOffsetX, var2.cursorY + var2.hitboxOffsetY);
            }
         } else {
            double var4 = var2.cursorX + var2.hitboxOffsetX;
            double var6 = var2.cursorY + var2.hitboxOffsetY;
            if (var3) {
               this.moveAnimationTimelineContextDropdownTo(var1, var2, "keyframe_timeline_dropdown", var4, var6);
            }

            var2.keyframeTimelineDropdownVisible = var3;
            if (!var3) {
               var2.keyframeTimelineDropdownHoverTargetId = null;
            } else {
               this.playEditorSfx(var1, var2, "dropdown-opened");
            }

            this.beginShellOpacityBatch(var2);

            try {
               this.setSidebarPanelVisible(var1, var2, "keyframe_timeline_dropdown", var3);
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }

            if (var3) {
               this.updateAnimationTimelineTimelineDropdownHover(var1, var2, var4, var6);
            } else {
               for (Object var9_raw : KEYFRAME_TIMELINE_DROPDOWN_ITEM_IDS) {
                  String var9 = var9_raw != null ? var9_raw.toString() : null;
                  this.setAnimationTimelineTimelineDropdownItemVisual(var1, var2, var9, false);
               }
            }
         }
      }
   }

   @Override
   protected void updateAnimationTimelineKeyframeDropdownHover(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         String var7 = var2.keyframeDropdownVisible ? this.resolveHoveredAnimationTimelineKeyframeDropdownTargetId(var2, var3, var5) : "";
         if (!this.equalsNullable(var2.keyframeDropdownHoverTargetId, var7)) {
            var2.keyframeDropdownHoverTargetId = var7;

            for (Object var9_raw : KEYFRAME_DROPDOWN_ITEM_IDS) {
               String var9 = var9_raw != null ? var9_raw.toString() : null;
               this.setAnimationTimelineKeyframeDropdownItemVisual(var1, var2, var9, this.equalsNullable(var9, var7));
            }
         }
      }
   }

   @Override
   protected void updateAnimationTimelineTimelineDropdownHover(Player var1, EditorSession var2, double var3, double var5) {
      if (var2 != null) {
         String var7 = var2.keyframeTimelineDropdownVisible ? this.resolveHoveredAnimationTimelineTimelineDropdownTargetId(var2, var3, var5) : "";
         if (!this.equalsNullable(var2.keyframeTimelineDropdownHoverTargetId, var7)) {
            var2.keyframeTimelineDropdownHoverTargetId = var7;

            for (Object var9_raw : KEYFRAME_TIMELINE_DROPDOWN_ITEM_IDS) {
               String var9 = var9_raw != null ? var9_raw.toString() : null;
               this.setAnimationTimelineTimelineDropdownItemVisual(var1, var2, var9, this.equalsNullable(var9, var7));
            }
         }
      }
   }

   protected void updateAnimationTimelineTimelineDropdownHover(EditorSession var1, double var2, double var4) {
      this.updateAnimationTimelineTimelineDropdownHover(null, var1, var2, var4);
   }

   protected boolean isInsideAnimationTimelineDropdownRect(EditorRect var1, double var2, double var4) {
      return var1 != null && var2 >= var1.x && var2 <= var1.maxX() && var4 >= var1.y && var4 <= var1.maxY();
   }

   @Override
   protected String resolveHoveredAnimationTimelineKeyframeDropdownTargetId(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.keyframeDropdownVisible) {
         double var6 = var2 - var1.hitboxOffsetX;
         double var8 = var4 - var1.hitboxOffsetY;
         int var10 = 0;

         for (Object var12_raw : KEYFRAME_DROPDOWN_ITEM_IDS) {
            String var12 = var12_raw != null ? var12_raw.toString() : null;
            String var13 = var12 + "_hitbox";
            EditorRect var14 = this.findShellBlockRect(var1, var13);
            if (var14 != null) {
               var10++;
               if (this.isInsideAnimationTimelineDropdownRect(var14, var6, var8)) {
                  return var12;
               }
            }
         }

         if (var10 == KEYFRAME_DROPDOWN_ITEM_IDS.size()) {
            return "";
         } else {
            EditorRect var15 = this.findShellBlockRect(var1, "keyframe_dropdown");
            if (var15 != null) {
               double var16 = 32.0;
               if (this.isInsideAnimationTimelineDropdownRect(var15, var6, var8)) {
                  int var19 = (int)Math.floor((var8 - var15.y) / 32.0);
                  if (var19 >= 0 && var19 < KEYFRAME_DROPDOWN_ITEM_IDS.size()) {
                     return KEYFRAME_DROPDOWN_ITEM_IDS.get(var19);
                  }
               }
            }

            for (Object var18_raw : KEYFRAME_DROPDOWN_ITEM_IDS) {
               String var18 = var18_raw != null ? var18_raw.toString() : null;
               if (this.isInsideShellBlock(var1, var18, var6, var8)) {
                  return var18;
               }
            }

            return "";
         }
      } else {
         return "";
      }
   }

   @Override
   protected String resolveHoveredAnimationTimelineTimelineDropdownTargetId(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.keyframeTimelineDropdownVisible) {
         double var6 = var2 - var1.hitboxOffsetX;
         double var8 = var4 - var1.hitboxOffsetY;
         int var10 = 0;

         for (Object var12_raw : KEYFRAME_TIMELINE_DROPDOWN_ITEM_IDS) {
            String var12 = var12_raw != null ? var12_raw.toString() : null;
            String var13 = var12 + "_hitbox";
            EditorRect var14 = this.findShellBlockRect(var1, var13);
            if (var14 != null) {
               var10++;
               if (this.isInsideAnimationTimelineDropdownRect(var14, var6, var8)) {
                  return var12;
               }
            }
         }

         if (var10 == KEYFRAME_TIMELINE_DROPDOWN_ITEM_IDS.size()) {
            return "";
         } else {
            EditorRect var15 = this.findShellBlockRect(var1, "keyframe_timeline_dropdown");
            if (var15 != null) {
               double var16 = 32.0;
               if (this.isInsideAnimationTimelineDropdownRect(var15, var6, var8)) {
                  int var19 = (int)Math.floor((var8 - var15.y) / 32.0);
                  if (var19 >= 0 && var19 < KEYFRAME_TIMELINE_DROPDOWN_ITEM_IDS.size()) {
                     return KEYFRAME_TIMELINE_DROPDOWN_ITEM_IDS.get(var19);
                  }
               }
            }

            for (Object var18_raw : KEYFRAME_TIMELINE_DROPDOWN_ITEM_IDS) {
               String var18 = var18_raw != null ? var18_raw.toString() : null;
               if (this.isInsideShellBlock(var1, var18, var6, var8)) {
                  return var18;
               }
            }

            return "";
         }
      } else {
         return "";
      }
   }

   protected void setAnimationTimelineKeyframeDropdownItemVisual(Player var1, EditorSession var2, String var3, boolean var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String[] var5 = KEYFRAME_DROPDOWN_ITEM_ICONS.get(var3);
         if (var5 != null && var5.length >= 2) {
            this.setShellText(var1, var3 + "_text", var4 ? var5[1] : var5[0]);
         }

         this.setShellColor(var1, var2, var3, var4 ? "141414" : "0f0f0f");
      }
   }

   protected void setAnimationTimelineTimelineDropdownItemVisual(Player var1, EditorSession var2, String var3, boolean var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String[] var5 = KEYFRAME_TIMELINE_DROPDOWN_ITEM_ICONS.get(var3);
         if (var5 != null && var5.length >= 2) {
            this.setShellText(var1, var3 + "_text", var4 ? var5[1] : var5[0]);
         }

         this.setShellColor(var1, var2, var3, var4 ? "141414" : "0f0f0f");
      }
   }

   @Override
   protected void moveAnimationTimelineContextDropdownTo(Player var1, EditorSession var2, String var3, double var4, double var6) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         ConfigurationSection var8 = this.findShellBlockSection(var2, var3);
         EditorRect var9 = this.findShellBlockRect(var2, var3);
         if (var8 != null && var9 != null) {
            double var10 = var4 - var2.hitboxOffsetX;
            double var12 = var6 - var2.hitboxOffsetY;
            EditorRect var18 = this.findShellBlockRect(var2, "animation_timeline");
            double var14;
            double var16;
            if (var18 != null) {
               double var19 = var18.maxX() - var9.width;
               double var21 = var18.maxY() - var9.height;
               boolean var23 = var10 > var19;
               var14 = Math.max(var18.x, Math.min(var19, var10));
               var16 = Math.max(var18.y, Math.min(var21, var12));
               if (var23) {
                  var14 = Math.max(var18.x, var14 - 11.0);
               }
            } else {
               double var32 = var2.yaml == null ? 1920.0 : var2.yaml.getDouble("screen.width", 1920.0);
               double var34 = var2.yaml == null ? 1080.0 : var2.yaml.getDouble("screen.height", 1080.0);
               double var36 = Math.max(0.0, var32 - var9.width);
               double var25 = Math.max(0.0, var34 - var9.height);
               boolean var27 = var10 > var36;
               var14 = Math.max(0.0, Math.min(var36, var10));
               var16 = Math.max(0.0, Math.min(var25, var12));
               if (var27) {
                  var14 = Math.max(0.0, var14 - 11.0);
               }
            }

            double var33 = var14 - var9.x;
            double var35 = var16 - var9.y;
            if (!(Math.abs(var33) < 1.0E-4) || !(Math.abs(var35) < 1.0E-4)) {
               String var37 = this.firstNonBlank(new String[]{var8.getString("__editor_target_path")});
               if (var37.isBlank()) {
                  this.moveShellElement(var1, var2, var3, var14, var16, var9.width, var9.height);
               } else {
                  HashMap var24 = new HashMap();
                  int var38 = 0;

                  for (Map var39 : var2.shellBlocks) {
                     var38++;
                     ConfigurationSection var28 = this.mapToSection(var39);
                     if (var28 != null) {
                        String var29 = this.resolveElementId(var28, var38, var24);
                        if (!var29.isBlank()) {
                           String var30 = this.firstNonBlank(new String[]{var28.getString("__editor_target_path")});
                           if (this.belongsToSidebarPanel(var37, var30)) {
                              EditorRect var31 = this.findShellBlockRect(var2, var29);
                              if (var31 != null) {
                                 this.moveShellElement(var1, var2, var29, var31.x + var33, var31.y + var35, var31.width, var31.height);
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

   protected void startSidebarInputPrompt(Player var1, EditorSession var2, EditorPropertyField var3) {
      if (var1 != null && var2 != null && var3 != null) {
         if (this.getSidebarEditableTargetId(var2) == null) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Select an element first.</yellow>"));
         } else {
            var2.pendingPropertyField = var3;
            this.clearPendingItemDesignInventoryPick(var2);
            var2.pendingActionTargetId = null;
            var2.pendingActionIndex = -1;
            this.stopOpacitySliderDrag(var2, true);
            this.resetSidebarFieldDrag(var1, var2);
            this.updateEditorPropertiesSidebar(var1, var2);

            String var4 = switch (var3) {
               case NAME -> "Name";
               case ID -> "ID";
               case VISIBLE -> "Visibility";
               case LOCKED -> "Blocked";
               case ANCHOR -> "Anchor";
               case WIDTH -> "Width";
               case HEIGHT -> "Height";
               case POSITION_X -> "Position X";
               case POSITION_Y -> "Position Y";
               case ROTATION -> "Rotation";
               case LAYER -> "Layer";
               case OPACITY -> "Opacity";
               case DESIGN_COLOR -> "Color";
               case DESIGN_BORDER_COLOR -> "Border Color";
               case DESIGN_HOVER_COLOR -> "Hover Color";
               case DESIGN_TEXT_WRAP -> "Text Wrapping";
               case ITEM_DESIGN_ITEM -> "Item";
               case ITEM_DESIGN_GLOWING -> "Glowing";
            };
            String var5;
            if (var3 == EditorPropertyField.DESIGN_HOVER_COLOR) {
               var5 = "HEX, Auto or None (e.g. #ff8800)";
            } else if (var3 == EditorPropertyField.DESIGN_BORDER_COLOR) {
               var5 = "HEX or Auto (e.g. #ff8800)";
            } else if (this.isSidebarHexField(var3)) {
               var5 = "HEX (e.g. #ff8800)";
            } else if (var3 == EditorPropertyField.NAME) {
               var5 = "text (e.g. Header)";
            } else if (var3 == EditorPropertyField.ID) {
               var5 = "id (letters/digits/_/./-)";
            } else if (var3 == EditorPropertyField.OPACITY) {
               var5 = "0-100 (e.g. 70)";
            } else if (var3 == EditorPropertyField.ITEM_DESIGN_ITEM) {
               var5 = "item (e.g. diamond, DIAMOND#123, PLAYER_HEAD:Notch, PLAYER_HEAD:%player%) or click an inventory item";
            } else if (var3 == EditorPropertyField.ITEM_DESIGN_GLOWING) {
               var5 = "enabled/disabled";
            } else if (var3 == EditorPropertyField.ANCHOR) {
               var5 = "left/center/right";
            } else if (var3 == EditorPropertyField.DESIGN_TEXT_WRAP) {
               var5 = "positive integer (e.g. 200)";
            } else {
               var5 = "number (e.g. 120, 120px, 45deg)";
            }

            this.sendEditorPlayerMessage(
               var1, MM.deserialize("<#84a0c8>" + var4 + " <gray>- type a new value in chat (<white>" + var5 + "</white>) or <white>cancel</white>.</gray>")
            );
         }
      }
   }

   protected boolean isSidebarHexField(EditorPropertyField var1) {
      return var1 == EditorPropertyField.DESIGN_COLOR || var1 == EditorPropertyField.DESIGN_BORDER_COLOR;
   }

   protected String parseSidebarHexColorInput(String var1) {
      return var1 != null && !var1.isBlank() ? this.normalizeHexColor(var1.trim()) : "";
   }

   protected boolean isSidebarAutoBorderColorInput(String var1) {
      return var1 != null && var1.trim().equalsIgnoreCase("Auto");
   }

   protected Double parseSidebarInputValue(String var1, EditorPropertyField var2) {
      if (var1 != null && !var1.isBlank() && var2 != null) {
         String var3 = var1.trim().toLowerCase(Locale.ROOT).replace(" ", "");
         if (var3.isBlank()) {
            return null;
         } else if (var2 == EditorPropertyField.OPACITY) {
            String var7 = var3.endsWith("%") ? var3.substring(0, var3.length() - 1) : var3;
            double var8 = this.parseDouble(var7, Double.NaN);
            return !Double.isFinite(var8) ? null : (double)this.opacityPercentToRaw(var8);
         } else {
            String var4;
            if (var2 == EditorPropertyField.ROTATION) {
               var4 = var3.replace("deg", "").replace("°", "");
            } else {
               var4 = var3.replace("px", "");
            }

            double var5 = this.parseDouble(var4, Double.NaN);
            return !Double.isFinite(var5) ? null : var5;
         }
      } else {
         return null;
      }
   }

   protected boolean applySidebarPropertyValue(Player var1, EditorSession var2, EditorPropertyField var3, double var4) {
      if (var2 != null && var3 != null && Double.isFinite(var4)) {
         List var6 = this.getSidebarEditableTargetIds(var2);
         if (var6.isEmpty()) {
            return false;
         } else {
            String var7 = (String)var6.get(0);
            boolean var8 = false;
            boolean var9 = false;
            switch (var3) {
               case NAME:
               case ID:
               case VISIBLE:
               case LOCKED:
               case ANCHOR:
               case DESIGN_HOVER_COLOR:
               case ITEM_DESIGN_ITEM:
               case ITEM_DESIGN_GLOWING:
                  return false;
               case WIDTH:
                  double var29 = Math.max(0.0, Math.abs(var4));
                  EditorRect var41 = this.resolveSidebarEditableBounds(var2, var7);
                  if (var41 != null) {
                     double var48 = var29 - var41.width;

                     for (Object var62_raw : var6) {
                        String var62 = var62_raw != null ? var62_raw.toString() : null;
                        EditorRect var65 = this.resolveSidebarEditableBounds(var2, var62);
                        if (var65 != null) {
                           EditorRect var68 = new EditorRect(var65.x, var65.y, Math.max(0.0, var65.width + var48), var65.height);
                           if (!this.sameRect(var65, var68)) {
                              if (!var9) {
                                 this.recordEditorMutationForSidebarInteraction(var2);
                                 var9 = true;
                              }

                              this.applyBoundsToTarget(var2, var62, var68);
                              var8 = true;
                           }
                        }
                     }
                  }
                  break;
               case HEIGHT:
                  double var28 = Math.max(0.0, Math.abs(var4));
                  EditorRect var40 = this.resolveSidebarEditableBounds(var2, var7);
                  if (var40 != null) {
                     double var47 = var28 - var40.height;

                     for (Object var61_raw : var6) {
                        String var61 = var61_raw != null ? var61_raw.toString() : null;
                        EditorRect var64 = this.resolveSidebarEditableBounds(var2, var61);
                        if (var64 != null) {
                           EditorRect var67 = new EditorRect(var64.x, var64.y, var64.width, Math.max(0.0, var64.height + var47));
                           if (!this.sameRect(var64, var67)) {
                              if (!var9) {
                                 this.recordEditorMutationForSidebarInteraction(var2);
                                 var9 = true;
                              }

                              this.applyBoundsToTarget(var2, var61, var67);
                              var8 = true;
                           }
                        }
                     }
                  }
                  break;
               case POSITION_X:
                  EditorRect var27 = this.resolveSidebarEditableBounds(var2, var7);
                  if (var27 != null) {
                     double var34 = var4 - var27.x;

                     for (Object var52_raw : var6) {
                        String var52 = var52_raw != null ? var52_raw.toString() : null;
                        EditorRect var54 = this.resolveSidebarEditableBounds(var2, var52);
                        if (var54 != null) {
                           EditorRect var60 = new EditorRect(var54.x + var34, var54.y, var54.width, var54.height);
                           if (!this.sameRect(var54, var60)) {
                              if (!var9) {
                                 this.recordEditorMutationForSidebarInteraction(var2);
                                 var9 = true;
                              }

                              this.applyBoundsToTarget(var2, var52, var60);
                              var8 = true;
                           }
                        }
                     }
                  }
                  break;
               case POSITION_Y:
                  EditorRect var26 = this.resolveSidebarEditableBounds(var2, var7);
                  if (var26 != null) {
                     double var33 = var4 - var26.y;

                     for (Object var51_raw : var6) {
                        String var51 = var51_raw != null ? var51_raw.toString() : null;
                        EditorRect var53 = this.resolveSidebarEditableBounds(var2, var51);
                        if (var53 != null) {
                           EditorRect var59 = new EditorRect(var53.x, var53.y + var33, var53.width, var53.height);
                           if (!this.sameRect(var53, var59)) {
                              if (!var9) {
                                 this.recordEditorMutationForSidebarInteraction(var2);
                                 var9 = true;
                              }

                              this.applyBoundsToTarget(var2, var51, var59);
                              var8 = true;
                           }
                        }
                     }
                  }
                  break;
               case ROTATION:
                  double var25 = this.normalizeSidebarRotation(var4);
                  double var39 = this.readTargetRotation(var2, var7);
                  double var50 = var25 - var39;

                  for (Object var63_raw : var6) {
                     String var63 = var63_raw != null ? var63_raw.toString() : null;
                     double var66 = this.equalsNullable(var63, var7) ? var25 : this.normalizeSidebarRotation(this.readTargetRotation(var2, var63) + var50);
                     boolean var69 = this.setTargetRotation(var2, var63, var66);
                     if (var69 && !var9) {
                        this.recordEditorMutationForSidebarInteraction(var2);
                        var9 = true;
                     }

                     var8 |= var69;
                  }
                  break;
               case LAYER:
                  double var24 = this.snap1(var4);
                  double var38 = this.readTargetLayer(var2, var7);
                  double var49 = var24 - var38;

                  for (Object var17_raw : var6) {
                     String var17 = var17_raw != null ? var17_raw.toString() : null;
                     double var18 = this.readTargetLayer(var2, var17);
                     boolean var20 = this.setTargetLayer(var2, var17, var18 + var49);
                     if (var20 && !var9) {
                        this.recordEditorMutationForSidebarInteraction(var2);
                        var9 = true;
                     }

                     var8 |= var20;
                  }
                  break;
               case OPACITY:
                  int var23 = this.isAnimationTimelineOpacitySidebarMode(var2)
                     ? this.clampAnimationTimelineOpacityRaw(var4)
                     : this.clampSidebarOpacityRaw(var4);

                  for (Object var37_raw : var6) {
                     String var37 = var37_raw != null ? var37_raw.toString() : null;
                     boolean var44 = this.setTargetOpacity(var2, var37, var23);
                     if (var44 && !var9) {
                        this.recordEditorMutationForSidebarInteraction(var2);
                        var9 = true;
                     }

                     var8 |= var44;
                  }
                  break;
               case DESIGN_COLOR:
                  String var22 = this.rainbowHexFromHue(var4);

                  for (Object var36_raw : var6) {
                     String var36 = var36_raw != null ? var36_raw.toString() : null;
                     boolean var43 = this.setTargetColor(var2, var36, var22);
                     if (var43 && !var9) {
                        this.recordEditorMutationForSidebarInteraction(var2);
                        var9 = true;
                     }

                     var8 |= var43;
                  }
                  break;
               case DESIGN_BORDER_COLOR:
                  String var21 = this.rainbowHexFromHue(var4);

                  for (Object var35_raw : var6) {
                     String var35 = var35_raw != null ? var35_raw.toString() : null;
                     boolean var42 = this.setTargetOutlineColor(var2, var35, var21);
                     if (var42 && !var9) {
                        this.recordEditorMutationForSidebarInteraction(var2);
                        var9 = true;
                     }

                     var8 |= var42;
                  }
                  break;
               case DESIGN_TEXT_WRAP:
                  int var10 = (int)Math.max(1.0, (double)Math.round(var4));

                  for (Object var12_raw : var6) {
                     String var12 = var12_raw != null ? var12_raw.toString() : null;
                     Map var13 = this.resolveRawTargetByTargetId(var2, var12);
                     if (var13 != null) {
                        String var14 = "text-wrap";
                        if (this.hasMapPath(var13, "textWrap")) {
                           var14 = "textWrap";
                        } else if (this.hasMapPath(var13, "text.wrap")) {
                           var14 = "text.wrap";
                        } else if (this.hasMapPath(var13, "params.text-wrap")) {
                           var14 = "params.text-wrap";
                        }

                        Object var15 = this.readMapPathValue(var13, var14);
                        int var16 = this.normalizeTextWrapLineWidth(var15);
                        if (var16 != var10) {
                           if (!var9) {
                              this.recordEditorMutationForSidebarInteraction(var2);
                              var9 = true;
                           }

                           this.setMapPathValue(var13, var14, Integer.valueOf(var10));
                           var8 = true;
                        }
                     }
                  }
            }

            return var8;
         }
      } else {
         return false;
      }
   }

   protected boolean applySidebarHexPropertyValue(EditorSession var1, EditorPropertyField var2, String var3) {
      if (var1 != null && var2 != null && this.isSidebarHexField(var2)) {
         String var4 = this.normalizeHexColor(var3);
         if (var4.isBlank()) {
            return false;
         } else {
            List var5 = this.getSidebarEditableTargetIds(var1);
            if (var5.isEmpty()) {
               return false;
            } else {
               boolean var6 = false;
               boolean var7 = false;

               for (Object var9_raw : var5) {
                  String var9 = var9_raw != null ? var9_raw.toString() : null;
                  if (var2 == EditorPropertyField.DESIGN_COLOR) {
                     boolean var10 = this.setTargetColor(var1, var9, var4);
                     if (var10 && !var7) {
                        this.recordEditorMutationForSidebarInteraction(var1);
                        var7 = true;
                     }

                     var6 |= var10;
                  } else if (var2 == EditorPropertyField.DESIGN_BORDER_COLOR) {
                     boolean var11 = this.setTargetOutlineColor(var1, var9, var4);
                     if (var11 && !var7) {
                        this.recordEditorMutationForSidebarInteraction(var1);
                        var7 = true;
                     }

                     var6 |= var11;
                  }
               }

               return var6;
            }
         }
      } else {
         return false;
      }
   }

   protected boolean applySidebarBorderColorAuto(EditorSession var1) {
      if (var1 == null) {
         return false;
      } else {
         List var2 = this.getSidebarEditableTargetIds(var1);
         if (var2.isEmpty()) {
            return false;
         } else {
            boolean var3 = false;
            boolean var4 = false;

            for (Object var6_raw : var2) {
               String var6 = var6_raw != null ? var6_raw.toString() : null;
               boolean var7 = this.setTargetOutlineColorAuto(var1, var6);
               if (var7 && !var4) {
                  this.recordEditorMutationForSidebarInteraction(var1);
                  var4 = true;
               }

               var3 |= var7;
            }

            return var3;
         }
      }
   }

   protected boolean applyFlipSelection(EditorSession var1, boolean var2) {
      if (var1 == null) {
         return false;
      } else {
         String var3 = this.getSidebarEditableTargetId(var1);
         if (var3 != null && !var3.isBlank()) {
            this.recordEditorMutation(var1);
            return this.toggleTargetMirror(var1, var3, var2);
         } else {
            return false;
         }
      }
   }

   protected EditorRect resolveFlippedBounds(EditorSession var1, EditorRect var2, boolean var3) {
      if (var1 != null && var2 != null) {
         double var4 = var1.previewViewport == null ? 1920.0 : var1.previewViewport.pageWidth;
         double var6 = var1.previewViewport == null ? 1080.0 : var1.previewViewport.pageHeight;
         double var8 = var3 ? this.snapToGrid(var1, var4 - (var2.x + var2.width)) : var2.x;
         double var10 = var3 ? var2.y : this.snapToGrid(var1, var6 - (var2.y + var2.height));
         return new EditorRect(var8, var10, var2.width, var2.height);
      } else {
         return null;
      }
   }

   protected void applySidebarFlipVisualMove(Player var1, EditorSession var2, String var3, EditorRect var4, EditorRect var5) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var4 != null && var5 != null) {
         double var6 = var5.x - var4.x;
         double var8 = var5.y - var4.y;
         if (!(Math.abs(var6) < 1.0E-4) || !(Math.abs(var8) < 1.0E-4)) {
            this.translateTargetElements(var1, var2, var3, var6, var8);
            if (var2.selectedElementId != null && var2.selectionOutlineVisible) {
               if (var2.additionalSelectedElementIds.isEmpty() && var3.equals(var2.selectedElementId)) {
                  this.translateSelectionOutline(var1, var2, var6, var8);
               } else {
                  this.renderSelectionOverlay(var1, var2);
               }
            }
         }
      }
   }

   protected boolean toggleTargetMirror(EditorSession var1, String var2, boolean var3) {
      HoverElement var4 = this.findFirstByTargetId(var1, var2);
      if (var4 != null && var4.targetPath != null && !var4.targetPath.isBlank()) {
         Map var5 = this.resolveRawMapAtPath(var1.rawBlocks, var4.targetPath);
         if (var5 == null) {
            return false;
         } else {
            EditorRect var6 = this.getTargetBounds(var1, var2);
            if (var6 == null) {
               return false;
            } else {
               EditorRect var7 = this.resolveFlippedBounds(var1, var6, var3);
               if (var7 == null) {
                  return false;
               } else {
                  boolean var8 = this.normalizeTargetMirrorSigns(var5, var4);
                  if (!var8 && this.sameRect(var6, var7)) {
                     return false;
                  } else {
                     this.applyBoundsToTarget(var1, var2, var7);
                     return true;
                  }
               }
            }
         }
      } else {
         return false;
      }
   }

   protected boolean normalizeTargetMirrorSigns(Map<String, Object> var1, HoverElement var2) {
      if (var1 != null && var2 != null) {
         boolean var3 = false;
         if ("component".equalsIgnoreCase(var2.targetKind)) {
            var3 |= this.normalizeMapPathAbsolute(var1, var2.bindingWidth);
            return var3 | this.normalizeMapPathAbsolute(var1, var2.bindingHeight);
         } else {
            var3 |= this.normalizeMapPathAbsolute(var1, "size.width");
            var3 |= this.normalizeMapPathAbsolute(var1, "size.height");
            var3 |= this.normalizeMapPathAbsolute(var1, "width");
            return var3 | this.normalizeMapPathAbsolute(var1, "height");
         }
      } else {
         return false;
      }
   }

   protected boolean normalizeMapPathAbsolute(Map<String, Object> var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank() && this.hasMapPath(var1, var2)) {
         double var3 = this.readMapPathDouble(var1, var2, Double.NaN);
         if (!Double.isFinite(var3)) {
            return false;
         } else {
            double var5 = Math.abs(var3);
            if (Math.abs(var3 - var5) < 1.0E-4) {
               return false;
            } else {
               this.setMapPathValue(var1, var2, Double.valueOf(var5));
               return true;
            }
         }
      } else {
         return false;
      }
   }

   protected void startSidebarFieldDrag(Player var1, EditorSession var2, EditorPropertyField var3, double var4) {
      if (var2 != null && var3 != null) {
         String var6 = this.getSidebarEditableTargetId(var2);
         if (var6 != null && !var6.isBlank()) {
            var2.sidebarFieldDragActive = true;
            var2.sidebarFieldDragField = var3;
            var2.sidebarFieldDragStartCursorX = var4;
            var2.sidebarFieldDragStartValue = this.resolveSidebarFieldDragStartValue(var2, var6, var3);
            var2.sidebarFieldDragLastValue = null;
            var2.sidebarOpacityRefreshTriggered = false;
            this.beginSidebarFieldDragHistory(var2);
            if (this.isDimensionSidebarField(var3)) {
               this.cameraService.lockCursorVisual(var1, var2.cursorX, var2.cursorY);
            } else {
               this.cameraService.unlockCursorVisual(var1);
            }
         }
      }
   }

   protected double resolveSidebarFieldDragStartValue(EditorSession var1, String var2, EditorPropertyField var3) {
      if (var1 != null && var2 != null && !var2.isBlank() && var3 != null) {
         EditorRect var4 = this.resolveSidebarEditableBounds(var1, var2);

         return switch (var3) {
            case NAME, ID, VISIBLE, LOCKED, ANCHOR, DESIGN_HOVER_COLOR, ITEM_DESIGN_ITEM, ITEM_DESIGN_GLOWING -> 0.0;
            case WIDTH -> var4 == null ? 0.0 : var4.width;
            case HEIGHT -> var4 == null ? 0.0 : var4.height;
            case POSITION_X -> var4 == null ? 0.0 : var4.x;
            case POSITION_Y -> var4 == null ? 0.0 : var4.y;
            case ROTATION -> this.readTargetRotation(var1, var2);
            case LAYER -> this.readTargetLayer(var1, var2);
            case OPACITY -> this.isAnimationTimelineOpacitySidebarMode(var1)
            ? this.opacityRawToAnimationPercent((double)this.readTargetOpacity(var1, var2))
            : this.opacityRawToPercent((double)this.readTargetOpacity(var1, var2));
            case DESIGN_COLOR -> this.hueFromHexColor(this.readTargetColor(var1, var2));
            case DESIGN_BORDER_COLOR -> this.hueFromHexColor(this.readTargetOutlineColor(var1, var2));
            case DESIGN_TEXT_WRAP -> {
               Map var5 = this.resolveRawTargetByTargetId(var1, var2);
               if (var5 == null) {
                  yield 200.0;
               } else {
                  Object var6 = this.readMapPathValue(var5, "text-wrap");
                  if (var6 == null) {
                     var6 = this.readMapPathValue(var5, "textWrap");
                  }

                  if (var6 == null) {
                     var6 = this.readMapPathValue(var5, "text.wrap");
                  }

                  if (var6 == null) {
                     var6 = this.readMapPathValue(var5, "params.text-wrap");
                  }

                  yield (double)this.normalizeTextWrapLineWidth(var6);
               }
            }
         };
      } else {
         return 0.0;
      }
   }

   @Override
   protected void resetSidebarFieldDrag(Player var1, EditorSession var2) {
      if (var2 != null) {
         this.finishSidebarFieldDragHistory(var2);
         this.cameraService.unlockCursorVisual(var1);
         var2.sidebarFieldDragActive = false;
         var2.sidebarFieldDragField = null;
         var2.sidebarFieldDragStartCursorX = 0.0;
         var2.sidebarFieldDragStartValue = 0.0;
         var2.sidebarFieldDragLastValue = null;
         var2.sidebarOpacityRefreshTriggered = false;
      }
   }

   protected void updateSidebarFieldDrag(Player var1, EditorSession var2, double var3) {
      if (var1 != null && var2 != null && var2.sidebarFieldDragActive && var2.sidebarFieldDragField != null) {
         List var5 = this.getSidebarEditableTargetIds(var2);
         if (var5.isEmpty()) {
            this.resetSidebarFieldDrag(var1, var2);
         } else {
            double var6 = var3 - var2.sidebarFieldDragStartCursorX;
            if (!this.isSidebarColorField(var2.sidebarFieldDragField) || var2.sidebarFieldDragLastValue != null || !(Math.abs(var6) < 6.0)) {
               if (this.isSidebarColorField(var2.sidebarFieldDragField) || var2.sidebarFieldDragLastValue != null || !(Math.abs(var6) < 1.0)) {
                  double var8 = this.sidebarFieldDragSensitivity(var2.sidebarFieldDragField);
                  double var10 = var2.sidebarFieldDragStartValue + var6 * var8;
                  var10 = this.normalizeSidebarFieldDragValue(var2.sidebarFieldDragField, var10);
                  if (var2.sidebarFieldDragField == EditorPropertyField.OPACITY) {
                     this.reanchorSidebarOpacityDragAtBounds(var2, var3, var10);
                  }

                  double var12 = var2.sidebarFieldDragLastValue == null ? var2.sidebarFieldDragStartValue : var2.sidebarFieldDragLastValue;
                  if (var2.sidebarFieldDragLastValue == null || !(Math.abs(var2.sidebarFieldDragLastValue - var10) < 1.0E-4)) {
                     var2.sidebarFieldDragLastValue = var10;
                     boolean var14 = var2.sidebarFieldDragField == EditorPropertyField.OPACITY && this.isAnimationTimelineOpacitySidebarMode(var2);
                     double var15 = var2.sidebarFieldDragField == EditorPropertyField.OPACITY
                        ? (double)(var14 ? this.opacityPercentToAnimationRaw(var10) : this.opacityPercentToRaw(var10))
                        : var10;
                     boolean var17 = this.applySidebarPropertyValue(var1, var2, var2.sidebarFieldDragField, var15);
                     if (var2.sidebarFieldDragField == EditorPropertyField.OPACITY) {
                        double var25 = var14 ? (double)this.opacityPercentToAnimationRaw(var12) : (double)this.opacityPercentToRaw(var12);
                        int var26 = var14 ? this.clampAnimationTimelineOpacityRaw(var15) : this.clampSidebarOpacityRaw(var15);
                        if (var17) {
                           if (!var2.sidebarOpacityRefreshTriggered) {
                              var2.sidebarOpacityRefreshTriggered = true;
                              this.rerenderEditableSelection(var1, var2);
                           } else {
                              this.applySelectedOpacityToRenderedElements(var1, var2, var5, var26);
                           }
                        }

                        if (var2.activeTool == EditorTool.ANIMATION) {
                           this.trySelectAnimationTimelineKeyframeForSidebarField(var2, var2.sidebarFieldDragField);
                           if (this.isAnimationTimelineSelectedRowMatchingSidebarField(var2, var2.sidebarFieldDragField)) {
                              this.syncAnimationTimelineSelectedKeyframeValueFromTarget(var2);
                              this.applyAnimationTimelineSidebarFieldDeltaToSelectedKeyframes(var2, var2.sidebarFieldDragField, var15 - var25);
                              this.renderAnimationTimelineKeyframes(var1, var2, this.firstNonBlank(new String[]{var2.animationTimelineTargetId}));
                           }
                        }

                        this.updateEditorPropertiesSidebar(var1, var2);
                     } else if (!var17) {
                        this.updateEditorPropertiesSidebar(var1, var2);
                     } else if (this.isSidebarPositionField(var2.sidebarFieldDragField)) {
                        double var18 = var2.sidebarFieldDragField == EditorPropertyField.POSITION_X ? var10 - var12 : 0.0;
                        double var20 = var2.sidebarFieldDragField == EditorPropertyField.POSITION_Y ? var10 - var12 : 0.0;
                        if (Math.abs(var18) > 1.0E-4 || Math.abs(var20) > 1.0E-4) {
                           for (Object var23_raw : var5) {
                              String var23 = var23_raw != null ? var23_raw.toString() : null;
                              this.translateTargetElements(var1, var2, var23, var18, var20);
                           }

                           this.translateAnimationSidebarPositionDescendants(var1, var2, var5, var18, var20);
                           if (var2.selectedElementId != null && var2.selectionOutlineVisible) {
                              this.translateSelectionOutline(var1, var2, var18, var20);
                           }
                        }

                        if (var2.activeTool == EditorTool.ANIMATION) {
                           this.trySelectAnimationTimelineKeyframeForSidebarField(var2, var2.sidebarFieldDragField);
                           if (this.isAnimationTimelineSelectedRowMatchingSidebarField(var2, var2.sidebarFieldDragField)) {
                              this.syncAnimationTimelineSelectedKeyframeValueFromTarget(var2);
                              this.applyAnimationTimelineSidebarFieldDeltaToSelectedKeyframes(var2, var2.sidebarFieldDragField, var10 - var12);
                              this.renderAnimationTimelineKeyframes(var1, var2, this.firstNonBlank(new String[]{var2.animationTimelineTargetId}));
                           }

                           this.renderSelectionOverlay(var1, var2);
                        }

                        this.updateEditorPropertiesSidebar(var1, var2);
                     } else {
                        if (var2.activeTool == EditorTool.ANIMATION) {
                           this.trySelectAnimationTimelineKeyframeForSidebarField(var2, var2.sidebarFieldDragField);
                           if (this.isAnimationTimelineSelectedRowMatchingSidebarField(var2, var2.sidebarFieldDragField)) {
                              this.syncAnimationTimelineSelectedKeyframeValueFromTarget(var2);
                              this.applyAnimationTimelineSidebarFieldDeltaToSelectedKeyframes(var2, var2.sidebarFieldDragField, var10 - var12);
                              this.renderAnimationTimelineKeyframes(var1, var2, this.firstNonBlank(new String[]{var2.animationTimelineTargetId}));
                           }
                        }

                        if (var2.sidebarFieldDragField != EditorPropertyField.LAYER && var2.sidebarFieldDragField != EditorPropertyField.DESIGN_TEXT_WRAP) {
                           this.rerenderEditableSelection(var1, var2);
                        } else {
                           this.rerenderEditableContent(var1, var2);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected void translateAnimationSidebarPositionDescendants(Player var1, EditorSession var2, List<String> var3, double var4, double var6) {
      if (var1 != null
         && var2 != null
         && var2.activeTool == EditorTool.ANIMATION
         && this.isAnimationTimelinePositionKeyframeSelected(var2)
         && var3 != null
         && !var3.isEmpty()) {
         if (!(Math.abs(var4) < 1.0E-4) || !(Math.abs(var6) < 1.0E-4)) {
            HashSet var8 = new HashSet(var3);
            HashSet var9 = new HashSet();

            for (Object var11_raw : var3) {
               String var11 = var11_raw != null ? var11_raw.toString() : null;
               for (String var13 : this.resolveDescendantLayerTargetIds(var2, var11)) {
                  if (!var8.contains(var13) && var9.add(var13)) {
                     this.translateTargetElements(var1, var2, var13, var4, var6);
                  }
               }
            }
         }
      }
   }

   protected boolean isSidebarPositionField(EditorPropertyField var1) {
      return var1 == EditorPropertyField.POSITION_X || var1 == EditorPropertyField.POSITION_Y;
   }

   @Override
   protected String resolveAnimationTimelineRowForSidebarField(EditorPropertyField var1) {
      if (var1 == null) {
         return "";
      } else {
         return switch (var1) {
            case WIDTH, HEIGHT -> "scale";
            case POSITION_X, POSITION_Y -> "position";
            case ROTATION -> "rotation";
            default -> "";
            case OPACITY -> "opacity";
         };
      }
   }

   protected boolean isAnimationTimelineSelectedRowMatchingSidebarField(EditorSession var1, EditorPropertyField var2) {
      if (var1 != null && var2 != null && this.isAnimationTimelineKeyframeSelected(var1)) {
         String var3 = this.firstNonBlank(new String[]{this.resolveAnimationTimelineRowForSidebarField(var2)}).toLowerCase(Locale.ROOT);
         if (var3.isBlank()) {
            return false;
         } else {
            String var4 = this.firstNonBlank(new String[]{var1.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT);
            if (!this.equalsNullable(var4, var3)) {
               return false;
            } else {
               String var5 = this.firstNonBlank(new String[]{var1.animationTimelineSelectedTargetId});
               String var6 = this.firstNonBlank(new String[]{this.resolveAnimationTimelineEditableTargetId(var1)});
               return !var5.isBlank() && !var6.isBlank() && this.equalsNullable(var5, var6);
            }
         }
      } else {
         return false;
      }
   }

   protected boolean applyAnimationTimelineSidebarFieldDeltaToSelectedKeyframes(EditorSession var1, EditorPropertyField var2, double var3) {
      if (var1 == null || var2 == null || !Double.isFinite(var3) || Math.abs(var3) < 1.0E-4) {
         return false;
      } else if (!this.isAnimationTimelineKeyframeSelected(var1)) {
         return false;
      } else {
         String var5 = this.firstNonBlank(new String[]{var1.animationTimelineSelectedTargetId});
         String var6 = this.firstNonBlank(new String[]{var1.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT);
         String var7 = this.resolveAnimationTimelineRowForSidebarField(var2);
         if (!var5.isBlank() && !var6.isBlank() && !var7.isBlank() && this.equalsNullable(var6, var7)) {
            Map var8 = this.resolveRawTargetByTargetId(var1, var5);
            Map var9 = this.readAnimationTimelineRowMap(var8, var6);
            if (var9 != null && !var9.isEmpty()) {
               List var10 = this.resolveAnimationTimelineSelectedTicks(var1, var5, var6);
               if (var10.size() <= 1) {
                  return false;
               } else {
                  int var11 = this.clampAnimationTimelineTick(var1.animationTimelineSelectedTick);
                  boolean var12 = false;

                  for (Object var14_raw : var10) {
                     int var14 = ((Number)var14_raw).intValue();
                     int var15 = this.clampAnimationTimelineTick(var14);
                     if (var15 != var11) {
                        Object var16 = this.readAnimationTimelineTickValue(var9, var15);
                        if ("rotation".equals(var6) && var2 == EditorPropertyField.ROTATION && var16 instanceof Number) {
                           Number var23 = (Number)var16;
                           double var24 = var23.doubleValue();
                           double var25 = this.snap1(this.normalizeSidebarRotation(var24 + var3));
                           if (!(Math.abs(var24 - var25) < 1.0E-4)) {
                              this.removeAnimationTimelineTickValue(var9, var15);
                              var9.put(Integer.toString(var15), var25);
                              var12 = true;
                           }
                        } else if ("opacity".equals(var6) && var2 == EditorPropertyField.OPACITY && var16 instanceof Number) {
                           Number var22 = (Number)var16;
                           double var18 = var22.doubleValue();
                           int var20 = this.clampAnimationTimelineOpacityRaw(var18 + var3);
                           if (!(Math.abs(var18 - (double)var20) < 1.0E-4)) {
                              this.removeAnimationTimelineTickValue(var9, var15);
                              var9.put(Integer.toString(var15), var20);
                              var12 = true;
                           }
                        } else if (var16 instanceof Map var17) {
                           var12 |= this.applyAnimationTimelineFieldDeltaToValueMap(var6, var17, var2, var3);
                        }
                     }
                  }

                  if (!var12) {
                     return false;
                  } else {
                     var1.animationTimelineRenderedSignature = null;
                     this.recordEditorMutationForSidebarInteraction(var1);
                     return true;
                  }
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   protected boolean applyAnimationTimelineFieldDeltaToValueMap(String var1, Map<String, Object> var2, EditorPropertyField var3, double var4) {
      if (var1 == null || var1.isBlank() || var2 == null || var2.isEmpty() || var3 == null || !Double.isFinite(var4)) {
         return false;
      } else if ("rotation".equals(var1) && var3 == EditorPropertyField.ROTATION) {
         boolean var18 = var2.containsKey("value")
            || var2.containsKey("rotation")
            || !var2.containsKey("add") && !var2.containsKey("addRotation") && !var2.containsKey("addrotation");
         if (!var18) {
            String var23 = var2.containsKey("add") ? "add" : (var2.containsKey("addRotation") ? "addRotation" : "addrotation");
            double var28 = this.parseDouble(var2.get(var23), 0.0);
            double var32 = this.snap1(var28 + var4);
            if (Math.abs(var28 - var32) < 1.0E-4) {
               return false;
            } else {
               var2.put(var23, var32);
               return true;
            }
         } else {
            double var22 = this.readMapPathDouble(var2, "value", this.readMapPathDouble(var2, "rotation", 0.0));
            double var31 = this.snap1(this.normalizeSidebarRotation(var22 + var4));
            if (!(Math.abs(var22 - var31) < 1.0E-4) || !var2.containsKey("value") && !var2.containsKey("rotation")) {
               var2.remove("rotation");
               var2.put("value", var31);
               return true;
            } else {
               return false;
            }
         }
      } else if ("opacity".equals(var1) && var3 == EditorPropertyField.OPACITY) {
         boolean var17 = var2.containsKey("value")
            || var2.containsKey("opacity")
            || !var2.containsKey("add") && !var2.containsKey("addOpacity") && !var2.containsKey("addopacity");
         if (!var17) {
            String var21 = var2.containsKey("add") ? "add" : (var2.containsKey("addOpacity") ? "addOpacity" : "addopacity");
            double var27 = this.parseDouble(var2.get(var21), 0.0);
            double var10 = this.snap1(var27 + var4);
            if (Math.abs(var27 - var10) < 1.0E-4) {
               return false;
            } else {
               var2.put(var21, var10);
               return true;
            }
         } else {
            double var20 = this.readMapPathDouble(var2, "value", this.readMapPathDouble(var2, "opacity", 255.0));
            int var30 = this.clampAnimationTimelineOpacityRaw(var20 + var4);
            if (!(Math.abs(var20 - (double)var30) < 1.0E-4) || !var2.containsKey("value") && !var2.containsKey("opacity")) {
               var2.remove("opacity");
               var2.put("value", var30);
               return true;
            } else {
               return false;
            }
         }
      } else {
         if ("position".equals(var1)) {
            if (var3 == EditorPropertyField.POSITION_X) {
               if (var2.containsKey("x") || !var2.containsKey("addx") && !var2.containsKey("addX")) {
                  double var16 = this.parseDouble(var2.get("x"), 0.0);
                  double var26 = this.snap1(var16 + var4);
                  if (Math.abs(var16 - var26) < 1.0E-4 && var2.containsKey("x")) {
                     return false;
                  }

                  var2.put("x", var26);
                  return true;
               }

               String var15 = var2.containsKey("addx") ? "addx" : "addX";
               double var19 = this.parseDouble(var2.get(var15), 0.0);
               double var29 = this.snap1(var19 + var4);
               if (Math.abs(var19 - var29) < 1.0E-4) {
                  return false;
               }

               var2.put(var15, var29);
               return true;
            }

            if (var3 == EditorPropertyField.POSITION_Y) {
               if (var2.containsKey("y") || !var2.containsKey("addy") && !var2.containsKey("addY")) {
                  double var14 = this.parseDouble(var2.get("y"), 0.0);
                  double var25 = this.snap1(var14 + var4);
                  if (Math.abs(var14 - var25) < 1.0E-4 && var2.containsKey("y")) {
                     return false;
                  }

                  var2.put("y", var25);
                  return true;
               }

               String var13 = var2.containsKey("addy") ? "addy" : "addY";
               double var7 = this.parseDouble(var2.get(var13), 0.0);
               double var9 = this.snap1(var7 + var4);
               if (Math.abs(var7 - var9) < 1.0E-4) {
                  return false;
               }

               var2.put(var13, var9);
               return true;
            }
         }

         if ("scale".equals(var1)) {
            if (var3 == EditorPropertyField.WIDTH) {
               double var12 = this.parseDouble(var2.get("width"), 0.0);
               double var24 = Math.max(0.0, this.snap1(var12 + var4));
               if (Math.abs(var12 - var24) < 1.0E-4 && var2.containsKey("width")) {
                  return false;
               }

               var2.put("width", var24);
               return true;
            }

            if (var3 == EditorPropertyField.HEIGHT) {
               double var6 = this.parseDouble(var2.get("height"), 0.0);
               double var8 = Math.max(0.0, this.snap1(var6 + var4));
               if (Math.abs(var6 - var8) < 1.0E-4 && var2.containsKey("height")) {
                  return false;
               }

               var2.put("height", var8);
               return true;
            }
         }

         return false;
      }
   }

   protected boolean trySelectAnimationTimelineKeyframeForSidebarField(EditorSession var1, EditorPropertyField var2) {
      String var3 = this.resolveAnimationTimelineRowForSidebarField(var2);
      return var3.isBlank() ? false : this.trySelectAnimationTimelineKeyframeAtCurrentTick(var1, var3);
   }

   protected boolean trySelectAnimationTimelineKeyframeAtCurrentTick(EditorSession var1, String var2) {
      if (var1 != null && var1.activeTool == EditorTool.ANIMATION && var2 != null && !var2.isBlank()) {
         String var3 = var2.toLowerCase(Locale.ROOT);
         if (!"position".equals(var3) && !"scale".equals(var3) && !"rotation".equals(var3) && !"opacity".equals(var3)) {
            return false;
         } else {
            String var4 = this.firstNonBlank(new String[]{this.resolveAnimationTimelineEditableTargetId(var1)});
            if (var4.isBlank()) {
               return false;
            } else {
               int var5 = this.clampAnimationTimelineTick(var1.animationTimelineTick);
               if (this.equalsNullable(this.firstNonBlank(new String[]{var1.animationTimelineSelectedTargetId}), var4)
                  && this.equalsNullable(this.firstNonBlank(new String[]{var1.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT), var3)
                  && var1.animationTimelineSelectedTick == var5) {
                  return true;
               } else {
                  Map var6 = this.resolveRawTargetByTargetId(var1, var4);
                  Map var7 = this.readAnimationTimelineRowMap(var6, var3);
                  if (!this.containsAnimationTimelineTick(var7, var5)) {
                     return false;
                  } else {
                     var1.animationTimelineSelectedTargetId = var4;
                     var1.animationTimelineSelectedRow = var3;
                     var1.animationTimelineSelectedTick = var5;
                     this.clearAnimationTimelineAdditionalSelection(var1);
                     var1.animationTimelineTick = var5;
                     var1.animationTimelineRenderedSignature = null;
                     var1.animationTimelineKeyframeDragRuntimeTick = -1;
                     return true;
                  }
               }
            }
         }
      } else {
         return false;
      }
   }

   protected boolean isSidebarColorField(EditorPropertyField var1) {
      return var1 == EditorPropertyField.DESIGN_COLOR || var1 == EditorPropertyField.DESIGN_BORDER_COLOR;
   }

   protected boolean isDimensionSidebarField(EditorPropertyField var1) {
      return var1 == EditorPropertyField.WIDTH
         || var1 == EditorPropertyField.HEIGHT
         || var1 == EditorPropertyField.POSITION_X
         || var1 == EditorPropertyField.POSITION_Y
         || var1 == EditorPropertyField.ROTATION
         || var1 == EditorPropertyField.LAYER
         || var1 == EditorPropertyField.OPACITY
         || var1 == EditorPropertyField.DESIGN_COLOR
         || var1 == EditorPropertyField.DESIGN_BORDER_COLOR
         || var1 == EditorPropertyField.DESIGN_TEXT_WRAP;
   }

   protected double sidebarFieldDragSensitivity(EditorPropertyField var1) {
      if (var1 == null) {
         return 0.25;
      } else {
         return switch (var1) {
            case ROTATION -> 0.35;
            case LAYER -> 0.04;
            case OPACITY -> 0.2;
            case DESIGN_COLOR, DESIGN_BORDER_COLOR -> 0.25;
            default -> 0.25;
         };
      }
   }

   protected double normalizeSidebarFieldDragValue(EditorPropertyField var1, double var2) {
      if (Double.isFinite(var2) && var1 != null) {
         return switch (var1) {
            case NAME, ID, VISIBLE, LOCKED, ANCHOR, DESIGN_HOVER_COLOR, ITEM_DESIGN_ITEM, ITEM_DESIGN_GLOWING -> 0.0;
            case WIDTH, HEIGHT -> Math.max(0.0, this.snap1(var2));
            case POSITION_X, POSITION_Y -> this.snap1(var2);
            case ROTATION -> this.normalizeSidebarRotation(var2);
            case LAYER -> this.snap1(var2);
            case OPACITY -> Math.max(0.0, Math.min(100.0, this.snap1(var2)));
            case DESIGN_COLOR, DESIGN_BORDER_COLOR -> this.normalizeHueDegrees(var2);
            case DESIGN_TEXT_WRAP -> Math.max(1.0, this.snap1(var2));
         };
      } else {
         return 0.0;
      }
   }

   @Override
   protected double normalizeSidebarRotation(double var1) {
      if (!Double.isFinite(var1)) {
         return 0.0;
      } else {
         double var3 = (double)Math.round(var1);
         double var5 = var3 % 360.0;
         return Math.abs(var5) < 1.0E-4 ? 0.0 : var5;
      }
   }

   protected double normalizeHueDegrees(double var1) {
      return ColorUtils.normalizeHueDegrees(var1);
   }

   protected double hueFromHexColor(String var1) {
      return ColorUtils.hueFromHexColor(this.normalizeHexColor(var1));
   }

   protected String rainbowHexFromHue(double var1) {
      return ColorUtils.rainbowHexFromHue(var1);
   }

   protected void reanchorSidebarOpacityDragAtBounds(EditorSession var1, double var2, double var4) {
      if (var1 != null) {
         if (var4 >= 100.0 && var2 > var1.sidebarFieldDragStartCursorX) {
            var1.sidebarFieldDragStartCursorX = var2;
            var1.sidebarFieldDragStartValue = 100.0;
         } else {
            if (var4 <= 0.0 && var2 < var1.sidebarFieldDragStartCursorX) {
               var1.sidebarFieldDragStartCursorX = var2;
               var1.sidebarFieldDragStartValue = 0.0;
            }
         }
      }
   }

   protected int clampSidebarOpacityRaw(double var1) {
      return !Double.isFinite(var1) ? 255 : Math.max(25, Math.min(255, (int)Math.round(var1)));
   }

   @Override
   protected int clampAnimationTimelineOpacityRaw(double var1) {
      return !Double.isFinite(var1) ? 255 : Math.max(5, Math.min(255, (int)Math.round(var1)));
   }

   protected int opacityPercentToRaw(double var1) {
      if (!Double.isFinite(var1)) {
         return 255;
      } else {
         double var3 = Math.max(0.0, Math.min(100.0, var1));
         double var5 = 230.0;
         double var7 = 25.0 + var3 / 100.0 * var5;
         return this.clampSidebarOpacityRaw(var7);
      }
   }

   protected int opacityPercentToAnimationRaw(double var1) {
      if (!Double.isFinite(var1)) {
         return 255;
      } else {
         double var3 = Math.max(0.0, Math.min(100.0, var1));
         double var5 = 250.0;
         double var7 = 5.0 + var3 / 100.0 * var5;
         return this.clampAnimationTimelineOpacityRaw(var7);
      }
   }

   protected double opacityRawToPercent(double var1) {
      if (!Double.isFinite(var1)) {
         return 100.0;
      } else {
         double var3 = Math.max(25.0, Math.min(255.0, var1));
         double var5 = 230.0;
         return var5 <= 1.0E-4 ? 100.0 : (var3 - 25.0) / var5 * 100.0;
      }
   }

   protected double opacityRawToAnimationPercent(double var1) {
      if (!Double.isFinite(var1)) {
         return 100.0;
      } else {
         double var3 = Math.max(5.0, Math.min(255.0, var1));
         double var5 = 250.0;
         return var5 <= 1.0E-4 ? 100.0 : (var3 - 5.0) / var5 * 100.0;
      }
   }

   protected boolean isAnimationTimelineOpacitySidebarMode(EditorSession var1) {
      return var1 != null
         && var1.activeTool == EditorTool.ANIMATION
         && this.isAnimationTimelineSelectedRowMatchingSidebarField(var1, EditorPropertyField.OPACITY);
   }

   protected void updateOpacitySliderDrag(Player var1, EditorSession var2, double var3) {
      if (var1 != null && var2 != null) {
         EditorRect var5 = this.findShellBlockRect(var2, "opacity_slider_track");
         if (var5 == null) {
            this.stopOpacitySliderDrag(var2, true);
         } else {
            double var6 = Math.max(1.0, Math.min(var5.width, 135.0));
            double var8 = var5.x + var6;
            double var10 = Math.max(var5.x, Math.min(var8, var3));
            double var12 = var6 <= 1.0E-4 ? 0.0 : (var10 - var5.x) / var6;
            boolean var14 = this.isAnimationTimelineOpacitySidebarMode(var2);
            int var15 = var14 ? this.opacityPercentToAnimationRaw(var12 * 100.0) : this.opacityPercentToRaw(var12 * 100.0);
            if (var2.opacitySliderLastValue == null || var2.opacitySliderLastValue != var15) {
               var2.opacitySliderLastValue = var15;
               List var16 = this.getSidebarEditableTargetIds(var2);
               boolean var17 = false;

               for (Object var19_raw : var16) {
                  String var19 = var19_raw != null ? var19_raw.toString() : null;
                  var17 |= this.setTargetOpacity(var2, var19, var15);
               }

               if (var17) {
                  this.recordEditorMutationForSidebarInteraction(var2);
                  if (!var2.sidebarOpacityRefreshTriggered) {
                     var2.sidebarOpacityRefreshTriggered = true;
                     this.rerenderEditableSelection(var1, var2);
                  } else {
                     this.applySelectedOpacityToRenderedElements(var1, var2, var16, var15);
                  }

                  this.updateEditorPropertiesSidebar(var1, var2);
               } else {
                  this.updateEditorPropertiesSidebar(var1, var2);
               }
            }
         }
      }
   }

   protected String getSidebarEditableTargetId(EditorSession var1) {
      if (var1 == null) {
         return null;
      } else if (var1.selectedElementId != null && !var1.selectedElementId.isBlank()) {
         return var1.selectedElementId;
      } else {
         for (Object var3_raw : var1.additionalSelectedElementIds) {
            String var3 = var3_raw != null ? var3_raw.toString() : null;
            if (var3 != null && !var3.isBlank()) {
               return var3;
            }
         }

         return null;
      }
   }

   protected List<String> getSidebarEditableTargetIds(EditorSession var1) {
      List var2 = this.getSelectedTargetIds(var1);
      if (!var2.isEmpty()) {
         return var2;
      } else {
         String var3 = this.getSidebarEditableTargetId(var1);
         if (var3 != null && !var3.isBlank()) {
            ArrayList var4 = new ArrayList();
            var4.add(var3);
            return var4;
         } else {
            return Collections.emptyList();
         }
      }
   }

   protected String resolveSidebarImageGroupRootTarget(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = this.firstNonBlank(new String[]{this.resolveAnimationTimelineHierarchyTargetPath(var1, var2), var2});
         int var4 = var3.indexOf(".children.");
         if (var4 > 0) {
            var3 = var3.substring(0, var4);
         }

         if (!var3.isBlank() && this.isAnimationTimelineImageGroupRoot(var1, var3)) {
            return var3;
         } else {
            return this.isAnimationTimelineImageGroupRoot(var1, var2) ? var2 : "";
         }
      } else {
         return "";
      }
   }

   protected boolean isSidebarImagePositionOffsetTarget(EditorSession var1, String var2) {
      return !this.resolveSidebarImageGroupRootTarget(var1, var2).isBlank();
   }

   protected double resolveSidebarImagePositionOffset(EditorPropertyField var1) {
      if (var1 == EditorPropertyField.POSITION_X) {
         return 29.0;
      } else {
         return var1 == EditorPropertyField.POSITION_Y ? 248.0 : 0.0;
      }
   }

   protected double toSidebarDisplayPositionValue(EditorSession var1, String var2, EditorPropertyField var3, double var4) {
      return Double.isFinite(var4) && this.isSidebarPositionField(var3) && this.isSidebarImagePositionOffsetTarget(var1, var2)
         ? var4 - this.resolveSidebarImagePositionOffset(var3)
         : var4;
   }

   protected double toSidebarRawPositionValue(EditorSession var1, String var2, EditorPropertyField var3, double var4) {
      return Double.isFinite(var4) && this.isSidebarPositionField(var3) && this.isSidebarImagePositionOffsetTarget(var1, var2)
         ? var4 + this.resolveSidebarImagePositionOffset(var3)
         : var4;
   }

   protected EditorRect resolveSidebarEditableBounds(EditorSession var1, String var2) {
      EditorRect var3 = this.getTargetBounds(var1, var2);
      if (var1 != null && var2 != null && !var2.isBlank()) {
         boolean var4 = this.isAnimationTimelineImageGroupRoot(var1, var2);
         String var5 = this.firstNonBlank(new String[]{this.resolveAnimationTimelineHierarchyTargetPath(var1, var2), var2});
         int var6 = var5.indexOf(".children.");
         if (var6 > 0) {
            var5 = var5.substring(0, var6);
         }

         if (!var4 && !var5.isBlank()) {
            var4 = this.isAnimationTimelineImageGroupRoot(var1, var5);
         }

         if (var4) {
            EditorRect var7 = this.resolveAnimationTimelineImageGroupLiveOutlineBoundsFromElements(var1, var5);
            if (var7 != null) {
               return var7;
            }

            if (var3 != null) {
               return var3;
            }
         }

         HoverElement var33 = this.findFirstByTargetId(var1, var2);
         if (var33 != null && var33.itemDisplayBlock && var33.targetPath != null && !var33.targetPath.isBlank()) {
            Map var8 = this.resolveRawMapAtPath(var1.rawBlocks, var33.targetPath);
            if (var8 != null && !var8.isEmpty()) {
               double var9 = var3 == null ? 0.0 : var3.x;
               double var11 = var3 == null ? 0.0 : var3.y;
               double var13 = var3 == null ? 1.0 : var3.width;
               double var15 = var3 == null ? 1.0 : var3.height;
               double var17 = this.readMapPathDouble(var8, "position.x", this.readMapPathDouble(var8, "x", var9));
               double var19 = this.readMapPathDouble(var8, "position.y", this.readMapPathDouble(var8, "y", var11));
               double var21 = this.readMapPathDouble(
                  var8, "size.width", this.readMapPathDouble(var8, "width", this.readMapPathDouble(var8, "scale.width", var13))
               );
               double var23 = this.readMapPathDouble(
                  var8, "size.height", this.readMapPathDouble(var8, "height", this.readMapPathDouble(var8, "scale.height", var15))
               );
               Object var25 = this.readMapPathValue(var8, "__editor_inherit_target_to_children");
               boolean var26 = this.parseBooleanFlag(var25, false) || this.isAnimationTimelineImageGroupRoot(var1, var2);
               double var27 = var26 ? 1.0E-4 : 1.0;
               double var29 = Math.max(var27, Math.abs(var21));
               double var31 = Math.max(var27, Math.abs(var23));
               return new EditorRect(var17, var19, var29, var31);
            } else {
               return var3;
            }
         } else {
            return var3;
         }
      } else {
         return var3;
      }
   }

   protected void rerenderEditableTarget(Player var1, EditorSession var2, String var3) {
      if (var2 != null && var3 != null && !var3.isBlank()) {
         String var4 = var2.selectedElementId;
         LinkedHashSet var5 = new LinkedHashSet<>(var2.additionalSelectedElementIds);
         var2.selectedElementId = var3;
         var2.additionalSelectedElementIds.clear();
         this.rerenderEditableSelection(var1, var2);
         var2.selectedElementId = var4;
         var2.additionalSelectedElementIds.clear();
         var2.additionalSelectedElementIds.addAll(var5);
         this.normalizeSelectionState(var2);
         this.clearEditorOverlaysOnly(var1, var2);
         if (var2.selectedElementId != null && var2.selectionOutlineVisible) {
            this.renderSelectionOverlay(var1, var2);
         }

         this.updateEditorPropertiesSidebar(var1, var2);
      } else {
         this.rerenderEditableSelection(var1, var2);
      }
   }

   protected void applyTargetOpacityToRenderedElements(Player var1, EditorSession var2, String var3, int var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         int var5 = Math.max(0, Math.min(255, var4));

         for (HoverElement var7 : this.findTargetElements(var2, var3)) {
            var7.opacity = var5;
            if (!this.isRoundedType(var7.type)) {
               this.setHudOpacityIfExists(var1, var7.id, var5);
               this.setHudOpacityIfExists(var1, var7.id + "_outline", var5);
            } else {
               this.setHudOpacityIfExists(var1, var7.id, var5);
               this.setHudOpacityIfExists(var1, var7.id + "_r_core", var5);
               this.setHudOpacityIfExists(var1, var7.id + "_r_top", var5);
               this.setHudOpacityIfExists(var1, var7.id + "_r_bottom", var5);
               this.setHudOpacityIfExists(var1, var7.id + "_r_left", var5);
               this.setHudOpacityIfExists(var1, var7.id + "_r_right", var5);
               this.setHudOpacityIfExists(var1, var7.id + "_r_tl", var5);
               this.setHudOpacityIfExists(var1, var7.id + "_r_tr", var5);
               this.setHudOpacityIfExists(var1, var7.id + "_r_bl", var5);
               this.setHudOpacityIfExists(var1, var7.id + "_r_br", var5);
               String var8 = var7.id + "_outline";
               this.setHudOpacityIfExists(var1, var8, var5);
               this.setHudOpacityIfExists(var1, var8 + "_r_core", var5);
               this.setHudOpacityIfExists(var1, var8 + "_r_top", var5);
               this.setHudOpacityIfExists(var1, var8 + "_r_bottom", var5);
               this.setHudOpacityIfExists(var1, var8 + "_r_left", var5);
               this.setHudOpacityIfExists(var1, var8 + "_r_right", var5);
               this.setHudOpacityIfExists(var1, var8 + "_r_tl", var5);
               this.setHudOpacityIfExists(var1, var8 + "_r_tr", var5);
               this.setHudOpacityIfExists(var1, var8 + "_r_bl", var5);
               this.setHudOpacityIfExists(var1, var8 + "_r_br", var5);
            }
         }
      }
   }

   protected void applySelectedOpacityToRenderedElements(Player var1, EditorSession var2, List<String> var3, int var4) {
      if (var3 != null && !var3.isEmpty()) {
         for (String var7 : new LinkedHashSet<String>(var3)) {
            this.applyTargetOpacityToRenderedElements(var1, var2, var7, var4);
         }
      }
   }
}
