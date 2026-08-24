package dev.xqedii.ultimateUI.service.gui.editor.shell.utils;

public final class AnimationMath {
   private AnimationMath() {
   }

   public static double applyInterpolation(String var0, double var1) {
      double var3 = MathUtils.clamp01(var1);
      if ("linear".equals(var0)) {
         return var3;
      } else if ("smooth".equals(var0)) {
         return var3 < 0.5 ? 4.0 * var3 * var3 * var3 : 1.0 - Math.pow(-2.0 * var3 + 2.0, 3.0) / 2.0;
      } else if ("ease-in".equals(var0)) {
         return var3 * var3 * var3;
      } else if ("ease-out".equals(var0)) {
         return 1.0 - Math.pow(1.0 - var3, 3.0);
      } else if ("bezier".equals(var0)) {
         return evaluateCubicBezier(var3, 0.25, 0.1, 0.25, 1.0);
      } else if ("bounce".equals(var0)) {
         return applyBounceInOut(var3);
      } else if ("bounce-in".equals(var0)) {
         return applyBounceIn(var3);
      } else if ("bounce-out".equals(var0)) {
         return applyBounceOut(var3);
      } else if ("back".equals(var0)) {
         return applyBackInOut(var3);
      } else if ("back-in".equals(var0)) {
         return applyBackIn(var3);
      } else {
         return "back-out".equals(var0) ? applyBackOut(var3) : var3;
      }
   }

   public static double applyBounceOut(double var0) {
      double var2 = 7.5625;
      double var4 = 2.75;
      if (var0 < 1.0 / var4) {
         return var2 * var0 * var0;
      } else if (var0 < 2.0 / var4) {
         double var9 = var0 - 1.5 / var4;
         return var2 * var9 * var9 + 0.75;
      } else if (var0 < 2.5 / var4) {
         double var8 = var0 - 2.25 / var4;
         return var2 * var8 * var8 + 0.9375;
      } else {
         double var6 = var0 - 2.625 / var4;
         return var2 * var6 * var6 + 0.984375;
      }
   }

   public static double applyBounceIn(double var0) {
      return 1.0 - applyBounceOut(1.0 - var0);
   }

   public static double applyBounceInOut(double var0) {
      return var0 < 0.5 ? (1.0 - applyBounceOut(1.0 - 2.0 * var0)) * 0.5 : (1.0 + applyBounceOut(2.0 * var0 - 1.0)) * 0.5;
   }

   public static double applyBackIn(double var0) {
      double var2 = 1.70158;
      double var4 = var2 + 1.0;
      return var4 * var0 * var0 * var0 - var2 * var0 * var0;
   }

   public static double applyBackOut(double var0) {
      double var2 = 1.70158;
      double var4 = var2 + 1.0;
      double var6 = var0 - 1.0;
      return 1.0 + var4 * var6 * var6 * var6 + var2 * var6 * var6;
   }

   public static double applyBackInOut(double var0) {
      double var2 = 1.70158;
      double var4 = var2 * 1.525;
      if (var0 < 0.5) {
         double var8 = 2.0 * var0;
         return var8 * var8 * ((var4 + 1.0) * var8 - var4) * 0.5;
      } else {
         double var6 = 2.0 * var0 - 2.0;
         return (var6 * var6 * ((var4 + 1.0) * var6 + var4) + 2.0) * 0.5;
      }
   }

   public static double evaluateCubicBezier(double var0, double var2, double var4, double var6, double var8) {
      double var10 = MathUtils.clamp01(var0);
      double var12 = var10;

      for (int var14 = 0; var14 < 6; var14++) {
         double var15 = cubicBezierValue(var12, var2, var6) - var10;
         double var17 = cubicBezierDerivative(var12, var2, var6);
         if (Math.abs(var17) < 1.0E-6) {
            break;
         }

         var12 -= var15 / var17;
         if (var12 <= 0.0 || var12 >= 1.0) {
            break;
         }
      }

      if (var12 <= 0.0 || var12 >= 1.0) {
         double var21 = 0.0;
         double var16 = 1.0;
         var12 = var10;

         for (int var18 = 0; var18 < 14; var18++) {
            double var19 = cubicBezierValue(var12, var2, var6);
            if (var19 < var10) {
               var21 = var12;
            } else {
               var16 = var12;
            }

            var12 = (var21 + var16) * 0.5;
         }
      }

      return cubicBezierValue(var12, var4, var8);
   }

   public static double cubicBezierValue(double var0, double var2, double var4) {
      double var6 = 1.0 - var0;
      return 3.0 * var6 * var6 * var0 * var2 + 3.0 * var6 * var0 * var0 * var4 + var0 * var0 * var0;
   }

   public static double cubicBezierDerivative(double var0, double var2, double var4) {
      double var6 = 1.0 - var0;
      return 3.0 * var6 * var6 * var2 + 6.0 * var6 * var0 * (var4 - var2) + 3.0 * var0 * var0 * (1.0 - var4);
   }
}
