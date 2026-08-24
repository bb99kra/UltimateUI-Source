package dev.xqedii.ultimateUI.service.gui.editor.shell.utils;

public final class MathUtils {
   private MathUtils() {
   }

   public static double clamp(double var0, double var2, double var4) {
      return Math.max(var2, Math.min(var4, var0));
   }

   public static double clamp01(double var0) {
      return Math.max(0.0, Math.min(1.0, var0));
   }

   public static int clampInt(int var0, int var1, int var2) {
      return Math.max(var1, Math.min(var2, var0));
   }
}
