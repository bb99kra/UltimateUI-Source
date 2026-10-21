package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.gui.editor.shell.managers.EditorTimelineKeyframePart;
import java.lang.runtime.ObjectMethods;

public record Xqedii_3c3nhzv(String targetPath, String colorSetting, EditorTimelineKeyframePart.HoverEffectRuntimeConfig effect, double pivotX, double pivotY) {

   @Override

   public String colorSetting() {
      {
         return this.colorSetting;
      }
   }

   public EditorTimelineKeyframePart.HoverEffectRuntimeConfig effect() {
      {
         return this.effect;
      }
   }

   public double pivotX() {
      {
         return this.pivotX;
      }
   }

   public double pivotY() {
      {
         return this.pivotY;
      }
   }
}
