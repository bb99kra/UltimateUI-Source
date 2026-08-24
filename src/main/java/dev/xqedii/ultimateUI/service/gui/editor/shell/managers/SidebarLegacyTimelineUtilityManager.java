package dev.xqedii.ultimateUI.service.gui.editor.shell.managers;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.hud.HudService;
import java.util.Locale;
import java.util.Map;
import org.bukkit.configuration.ConfigurationSection;

public abstract class SidebarLegacyTimelineUtilityManager extends AnimationTimelineSelectionManager {
   private static final String SIDEBAR_CONTAINER_ID = "rightsidebar";
   protected static final String SIDEBAR_RIGHT_CONTAINER_ID = "right_sidebar";
   private static final String SIDEBAR_PROPERTIES_HITBOX_ID = "properties_hitbox";
   private static final String SIDEBAR_DESIGN_HITBOX_ID = "design_hitbox";
   private static final String SIDEBAR_LAYERS_HITBOX_ID = "layers_hitbox";
   private static final String SIDEBAR_LAYERS_HITBOX_LEGACY_ID = "layer_hitbox";
   private static final String SIDEBAR_LAYERS_BOX_ID = "layers_box";
   private static final String SIDEBAR_WIDTH_BOX_ID = "prop_w_box";
   private static final String SIDEBAR_HEIGHT_BOX_ID = "prop_h_box";
   private static final String SIDEBAR_X_BOX_ID = "prop_x_box";
   private static final String SIDEBAR_Y_BOX_ID = "prop_y_box";
   private static final String SIDEBAR_ROTATION_BOX_ID = "prop_r_box";
   private static final String SIDEBAR_OPACITY_BOX_ID = "prop_opacity_box";
   private static final String ANIMATION_ROW_ROTATION = "rotation";
   private static final String ANIMATION_ROW_POSITION = "position";
   private static final String ANIMATION_ROW_SCALE = "scale";
   private static final String ANIMATION_ROW_OPACITY = "opacity";
   private static final String ANIMATION_ROW_ROTATION_BOX_ID = "animation_box_rotation";
   private static final String ANIMATION_ROW_POSITION_BOX_ID = "animation_box_position";
   private static final String ANIMATION_ROW_SCALE_BOX_ID = "animation_box_scale";
   private static final String ANIMATION_ROW_OPACITY_BOX_ID = "animation_box_row4";

   protected SidebarLegacyTimelineUtilityManager(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   protected abstract ConfigurationSection findShellBlockSection(EditorSession var1, String var2);

   protected boolean isInsideSidebarLayersHitbox(EditorSession var1, double var2, double var4) {
      return this.isInsideShellBlock(var1, "layers_hitbox", var2, var4) || this.isInsideShellBlock(var1, "layer_hitbox", var2, var4);
   }

   protected boolean hasLegacySidebarUi(EditorSession var1) {
      return var1 == null
         ? false
         : this.findShellBlockSection(var1, "rightsidebar") != null
            || this.findShellBlockSection(var1, "right_sidebar") != null
            || this.findShellBlockSection(var1, "properties_hitbox") != null
            || this.findShellBlockSection(var1, "design_hitbox") != null
            || this.findShellBlockSection(var1, "layers_hitbox") != null
            || this.findShellBlockSection(var1, "layer_hitbox") != null
            || this.findShellBlockSection(var1, "layers_box") != null
            || this.findShellBlockSection(var1, "prop_w_box") != null
            || this.findShellBlockSection(var1, "prop_h_box") != null
            || this.findShellBlockSection(var1, "prop_x_box") != null
            || this.findShellBlockSection(var1, "prop_y_box") != null
            || this.findShellBlockSection(var1, "prop_r_box") != null
            || this.findShellBlockSection(var1, "prop_opacity_box") != null;
   }

   protected String resolveAnimationTimelineContextRowByHit(EditorSession var1, double var2, double var4) {
      if (var1 == null) {
         return "";
      } else {
         double var6 = var2 - var1.hitboxOffsetX;
         double var8 = var4 - var1.hitboxOffsetY;
         if (this.isInsideShellBlock(var1, "animation_box_rotation", var2, var4) || this.isInsideShellBlock(var1, "animation_box_rotation", var6, var8)) {
            return "rotation";
         } else if (this.isInsideShellBlock(var1, "animation_box_position", var2, var4) || this.isInsideShellBlock(var1, "animation_box_position", var6, var8)) {
            return "position";
         } else if (this.isInsideShellBlock(var1, "animation_box_scale", var2, var4) || this.isInsideShellBlock(var1, "animation_box_scale", var6, var8)) {
            return "scale";
         } else {
            return !this.isInsideShellBlock(var1, "animation_box_row4", var2, var4) && !this.isInsideShellBlock(var1, "animation_box_row4", var6, var8)
               ? ""
               : "opacity";
         }
      }
   }

   protected String formatAnimationTimelineRowLabel(String var1) {
      if ("rotation".equals(var1)) {
         return "Rotation";
      } else if ("position".equals(var1)) {
         return "Position";
      } else if ("scale".equals(var1)) {
         return "Scale";
      } else {
         return "opacity".equals(var1) ? "Opacity" : this.firstNonBlank(new String[]{var1});
      }
   }

   protected String formatAnimationTimelineKeyframeValue(String var1, Object var2) {
      if (var2 == null) {
         return "null";
      } else if (var2 instanceof Number var13) {
         return this.formatAnimationTimelineNumber(var13.doubleValue());
      } else {
         if (var2 instanceof Map var3) {
            if ("position".equals(var1)) {
               double var5 = this.parseDouble(var3.get("x"), Double.NaN);
               double var7 = this.parseDouble(var3.get("y"), Double.NaN);
               if (Double.isFinite(var5) && Double.isFinite(var7)) {
                  return "x=" + this.formatAnimationTimelineNumber(var5) + ", y=" + this.formatAnimationTimelineNumber(var7);
               }
            }

            if ("scale".equals(var1)) {
               double var14 = this.parseDouble(var3.get("x"), Double.NaN);
               double var15 = this.parseDouble(var3.get("y"), Double.NaN);
               double var9 = this.parseDouble(var3.get("width"), Double.NaN);
               double var11 = this.parseDouble(var3.get("height"), Double.NaN);
               if (Double.isFinite(var9) && Double.isFinite(var11)) {
                  if (Double.isFinite(var14) && Double.isFinite(var15)) {
                     return "x="
                        + this.formatAnimationTimelineNumber(var14)
                        + ", y="
                        + this.formatAnimationTimelineNumber(var15)
                        + ", width="
                        + this.formatAnimationTimelineNumber(var9)
                        + ", height="
                        + this.formatAnimationTimelineNumber(var11);
                  }

                  return "width=" + this.formatAnimationTimelineNumber(var9) + ", height=" + this.formatAnimationTimelineNumber(var11);
               }
            }
         }

         return String.valueOf(var2);
      }
   }

   protected String formatAnimationTimelineNumber(double var1) {
      if (!Double.isFinite(var1)) {
         return "0";
      } else {
         String var3 = String.format(Locale.ROOT, "%.3f", var1);
         int var4 = var3.length();

         while (var4 > 0 && var3.charAt(var4 - 1) == '0') {
            var4--;
         }

         if (var4 > 0 && var3.charAt(var4 - 1) == '.') {
            var4--;
         }

         return var4 <= 0 ? "0" : var3.substring(0, var4);
      }
   }
}
