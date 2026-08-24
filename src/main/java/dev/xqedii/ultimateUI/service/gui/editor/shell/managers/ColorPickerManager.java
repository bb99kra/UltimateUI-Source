package dev.xqedii.ultimateUI.service.gui.editor.shell.managers;

import dev.xqedii.ultimateUI.service.gui.editor.shell.utils.ColorUtils;
import dev.xqedii.ultimateUI.service.gui.editor.shell.utils.MathUtils;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;

public final class ColorPickerManager {
   public ColorPickerManager.PickerCoordinates resolvePickerCoordinatesFromHex(String var1) {
      String var2 = ColorUtils.normalizeHex(var1);
      if (var2.length() != 6) {
         var2 = "ffffff";
      }

      double[] var3 = ColorUtils.hexToHsv(var2);
      double var4 = var3[0];
      double var6 = var3[1];
      double var8 = var3[2];
      double var10 = 647.0 + (1.0 - var4 / 360.0) * 252.0;
      double var12 = 106.0 + var6 * 198.0;
      double var14 = 651.0 + (1.0 - var8) * 256.0;
      return new ColorPickerManager.PickerCoordinates(var12 - -12.0, var14 - 3.0, var10 - -4.0);
   }

   public double resolveHueDegreesFromIndicator(double var1) {
      double var3 = var1 + -4.0;
      double var5 = 252.0;
      double var7 = var5 <= 0.0 ? 0.0 : (var3 - 647.0) / var5;
      return MathUtils.clamp((1.0 - var7) * 360.0, 0.0, 360.0);
   }

   public String resolveHueHex(double var1) {
      return ColorUtils.rainbowHexFromHue(this.resolveHueDegreesFromIndicator(var1));
   }

   public double clampGradientLogicalX(double var1) {
      double var3 = 118.0;
      double var5 = 316.0;
      return MathUtils.clamp(var1, var3, var5);
   }

   public double clampGradientLogicalY(double var1) {
      double var3 = 648.0;
      double var5 = 904.0;
      return MathUtils.clamp(var1, var3, var5);
   }

   public double clampHueLogicalY(double var1) {
      double var3 = 651.0;
      double var5 = 903.0;
      return MathUtils.clamp(var1, var3, var5);
   }

   public boolean isInsideGradientArea(double var1, double var3) {
      return var1 >= 106.0 && var1 <= 304.0 && var3 >= 651.0 && var3 <= 907.0;
   }

   public boolean isInsideHueArea(double var1, double var3) {
      return var1 >= 325.0 && var1 <= 389.0 && var3 >= 647.0 && var3 <= 899.0;
   }

   public String computeColorFromIndicators(EditorSession var1) {
      if (var1 == null) {
         return "ffffff";
      } else {
         double var2 = this.resolveHueDegreesFromIndicator(var1.colorPickerHueAbsY);
         double var4 = var1.colorPickerGradAbsX + -12.0;
         double var6 = var1.colorPickerGradAbsY + 3.0;
         double var8 = 198.0;
         double var10 = 256.0;
         double var12 = var8 <= 0.0 ? 0.0 : (var4 - 106.0) / var8;
         double var14 = var10 <= 0.0 ? 1.0 : 1.0 - (var6 - 651.0) / var10;
         var12 = MathUtils.clamp01(var12);
         var14 = MathUtils.clamp01(var14);
         return ColorUtils.hsvToHex(var2, var12, var14);
      }
   }

   public static record PickerCoordinates(double gradAbsX, double gradAbsY, double hueAbsY) {
   }
}
