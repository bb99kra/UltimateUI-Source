package dev.xqedii.ultimateUI.service.gui.editor.shell.managers;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.gui.model.EditorTool;
import dev.xqedii.ultimateUI.service.hud.HudService;
import java.util.Locale;
import java.util.Map;

public abstract class AnimationTimelineSelectionManager extends EditorMessagingHistoryManager {
   private static final String ANIMATION_ROW_ROTATION = "rotation";
   private static final String ANIMATION_ROW_POSITION = "position";
   private static final String ANIMATION_ROW_SCALE = "scale";
   private static final String ANIMATION_ROW_OPACITY = "opacity";
   private static final String ANIMATION_ROW_ROTATION_BOX_ID = "animation_box_rotation";
   private static final String ANIMATION_ROW_POSITION_BOX_ID = "animation_box_position";
   private static final String ANIMATION_ROW_SCALE_BOX_ID = "animation_box_scale";
   private static final String ANIMATION_ROW_OPACITY_BOX_ID = "animation_box_row4";
   private static final Map<String, String> ANIMATION_ROW_TO_BOX_ID = Map.of(
      "rotation", "animation_box_rotation", "position", "animation_box_position", "scale", "animation_box_scale", "opacity", "animation_box_row4"
   );

   protected AnimationTimelineSelectionManager(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   protected abstract int clampAnimationTimelineTick(int var1);

   protected String buildAnimationTimelineKeyframeRef(String var1, int var2) {
      String var3 = this.firstNonBlank(new String[]{var1}).toLowerCase(Locale.ROOT);
      return !var3.isBlank() && ANIMATION_ROW_TO_BOX_ID.containsKey(var3) ? var3 + ":" + this.clampAnimationTimelineTick(var2) : "";
   }

   protected AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef parseAnimationTimelineSelectionRef(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim().toLowerCase(Locale.ROOT);
      if (var2.isBlank()) {
         return null;
      } else {
         int var3 = var2.indexOf(58);
         if (var3 > 0 && var3 < var2.length() - 1) {
            String var4 = var2.substring(0, var3);
            if (!ANIMATION_ROW_TO_BOX_ID.containsKey(var4)) {
               return null;
            } else {
               double var5 = this.parseDouble(var2.substring(var3 + 1), Double.NaN);
               if (!Double.isFinite(var5)) {
                  return null;
               } else {
                  int var7 = this.clampAnimationTimelineTick((int)Math.round(var5));
                  return new AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef(var4, var7);
               }
            }
         } else {
            return null;
         }
      }
   }

   protected void addAnimationTimelineAdditionalSelectionRef(EditorSession var1, String var2, int var3) {
      if (var1 != null) {
         String var4 = this.buildAnimationTimelineKeyframeRef(var2, var3);
         if (!var4.isBlank()) {
            var1.animationTimelineAdditionalSelectedKeyframeRefs.add(var4);
         }
      }
   }

   protected void removeAnimationTimelineAdditionalSelectionRef(EditorSession var1, String var2, int var3) {
      if (var1 != null) {
         String var4 = this.buildAnimationTimelineKeyframeRef(var2, var3);
         if (!var4.isBlank()) {
            var1.animationTimelineAdditionalSelectedKeyframeRefs.remove(var4);
         }
      }
   }

   protected boolean containsAnimationTimelineAdditionalSelectionRef(EditorSession var1, String var2, int var3) {
      if (var1 == null) {
         return false;
      } else {
         String var4 = this.buildAnimationTimelineKeyframeRef(var2, var3);
         return !var4.isBlank() && var1.animationTimelineAdditionalSelectedKeyframeRefs.contains(var4);
      }
   }

   protected void movePrimaryAnimationTimelineSelectionIntoAdditionalRefs(EditorSession var1) {
      if (var1 != null && this.isAnimationTimelineKeyframeSelected(var1)) {
         String var2 = this.firstNonBlank(new String[]{var1.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT);
         if (!var2.isBlank() && ANIMATION_ROW_TO_BOX_ID.containsKey(var2)) {
            int var3 = this.clampAnimationTimelineTick(var1.animationTimelineSelectedTick);
            this.addAnimationTimelineAdditionalSelectionRef(var1, var2, var3);

            for (Object var5_raw : var1.animationTimelineAdditionalSelectedTicks) {
               int var5 = ((Number)var5_raw).intValue(); {
                  this.addAnimationTimelineAdditionalSelectionRef(var1, var2, var5);
               }
            }

            var1.animationTimelineAdditionalSelectedTicks.clear();
         } else {
            this.clearAnimationTimelineAdditionalSelection(var1);
         }
      }
   }

   protected boolean isAnimationTimelineKeyframeSelected(EditorSession var1) {
      if (var1 != null && var1.activeTool == EditorTool.ANIMATION) {
         String var2 = this.firstNonBlank(new String[]{var1.animationTimelineSelectedTargetId});
         String var3 = this.firstNonBlank(new String[]{var1.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT);
         return !var2.isBlank()
            && ("rotation".equals(var3) || "position".equals(var3) || "scale".equals(var3) || "opacity".equals(var3))
            && var1.animationTimelineSelectedTick >= 0;
      } else {
         return false;
      }
   }

   protected boolean isAnimationTimelinePositionKeyframeSelected(EditorSession var1) {
      return this.isAnimationTimelineKeyframeSelected(var1)
         && "position".equals(this.firstNonBlank(new String[]{var1.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT));
   }

   protected boolean isAnimationTimelineRotationKeyframeSelected(EditorSession var1) {
      return this.isAnimationTimelineKeyframeSelected(var1)
         && "rotation".equals(this.firstNonBlank(new String[]{var1.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT));
   }

   protected boolean isAnimationTimelineScaleKeyframeSelected(EditorSession var1) {
      return this.isAnimationTimelineKeyframeSelected(var1)
         && "scale".equals(this.firstNonBlank(new String[]{var1.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT));
   }

   protected boolean isAnimationTimelineOpacityKeyframeSelected(EditorSession var1) {
      return this.isAnimationTimelineKeyframeSelected(var1)
         && "opacity".equals(this.firstNonBlank(new String[]{var1.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT));
   }

   protected EditorTool resolveAnimationTimelineSelectedTool(EditorSession var1) {
      if (var1 != null && var1.activeTool == EditorTool.ANIMATION) {
         if (this.isAnimationTimelinePositionKeyframeSelected(var1)) {
            return EditorTool.CURSOR;
         } else {
            return this.isAnimationTimelineScaleKeyframeSelected(var1) ? EditorTool.SCALE : EditorTool.ANIMATION;
         }
      } else {
         return var1 == null ? EditorTool.ANIMATION : var1.activeTool;
      }
   }

   protected static record AnimationTimelineKeyframeRef(String rowKey, int tick) {
   }
}
