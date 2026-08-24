package dev.xqedii.ultimateUI.service.gui.editor.shell.utils;

import java.util.Locale;

public final class ColorUtils {
   private ColorUtils() {
   }

   public static double normalizeHueDegrees(double var0) {
      if (!Double.isFinite(var0)) {
         return 0.0;
      } else {
         double var2 = var0 % 360.0;
         if (var2 < 0.0) {
            var2 += 360.0;
         }

         return var2;
      }
   }

   public static double hueFromHexColor(String var0) {
      String var1 = normalizeHex(var0);
      if (var1.length() != 6) {
         return 0.0;
      } else {
         int var2;
         try {
            var2 = Integer.parseInt(var1, 16);
         } catch (NumberFormatException var17) {
            return 0.0;
         }

         double var3 = (double)(var2 >> 16 & 0xFF) / 255.0;
         double var5 = (double)(var2 >> 8 & 0xFF) / 255.0;
         double var7 = (double)(var2 & 0xFF) / 255.0;
         double var9 = Math.max(var3, Math.max(var5, var7));
         double var11 = Math.min(var3, Math.min(var5, var7));
         double var13 = var9 - var11;
         if (var13 < 1.0E-6) {
            return 0.0;
         } else {
            double var15;
            if (Math.abs(var9 - var3) < 1.0E-6) {
               var15 = (var5 - var7) / var13 % 6.0;
            } else if (Math.abs(var9 - var5) < 1.0E-6) {
               var15 = (var7 - var3) / var13 + 2.0;
            } else {
               var15 = (var3 - var5) / var13 + 4.0;
            }

            return normalizeHueDegrees(var15 * 60.0);
         }
      }
   }

   public static String rainbowHexFromHue(double var0) {
      double var2 = normalizeHueDegrees(var0) / 60.0;
      int var4 = (int)Math.floor(var2) % 6;
      if (var4 < 0) {
         var4 += 6;
      }

      double var5 = var2 - Math.floor(var2);
      double var7;
      double var9;
      double var11;
      switch (var4) {
         case 0:
            var7 = 1.0;
            var9 = var5;
            var11 = 0.0;
            break;
         case 1:
            var7 = 1.0 - var5;
            var9 = 1.0;
            var11 = 0.0;
            break;
         case 2:
            var7 = 0.0;
            var9 = 1.0;
            var11 = var5;
            break;
         case 3:
            var7 = 0.0;
            var9 = 1.0 - var5;
            var11 = 1.0;
            break;
         case 4:
            var7 = var5;
            var9 = 0.0;
            var11 = 1.0;
            break;
         default:
            var7 = 1.0;
            var9 = 0.0;
            var11 = 1.0 - var5;
      }

      int var13 = MathUtils.clampInt((int)Math.round(var7 * 255.0), 0, 255);
      int var14 = MathUtils.clampInt((int)Math.round(var9 * 255.0), 0, 255);
      int var15 = MathUtils.clampInt((int)Math.round(var11 * 255.0), 0, 255);
      return String.format(Locale.ROOT, "%02x%02x%02x", var13, var14, var15);
   }

   public static double[] hexToHsv(String var0) {
      String var1 = normalizeHex(var0);
      if (var1.length() != 6) {
         return new double[]{0.0, 0.0, 1.0};
      } else {
         int var2;
         try {
            var2 = Integer.parseInt(var1, 16);
         } catch (NumberFormatException var21) {
            return new double[]{0.0, 0.0, 1.0};
         }

         double var3 = (double)(var2 >> 16 & 0xFF) / 255.0;
         double var5 = (double)(var2 >> 8 & 0xFF) / 255.0;
         double var7 = (double)(var2 & 0xFF) / 255.0;
         double var9 = Math.max(var3, Math.max(var5, var7));
         double var11 = Math.min(var3, Math.min(var5, var7));
         double var13 = var9 - var11;
         double var17 = var9 < 1.0E-6 ? 0.0 : var13 / var9;
         double var19 = 0.0;
         if (var13 > 1.0E-6) {
            if (Math.abs(var9 - var3) < 1.0E-6) {
               var19 = (var5 - var7) / var13 % 6.0;
            } else if (Math.abs(var9 - var5) < 1.0E-6) {
               var19 = (var7 - var3) / var13 + 2.0;
            } else {
               var19 = (var3 - var5) / var13 + 4.0;
            }

            var19 = (var19 * 60.0 + 360.0) % 360.0;
         }

         return new double[]{var19, var17, var9};
      }
   }

   public static String hsvToHex(double var0, double var2, double var4) {
      double var6 = normalizeHueDegrees(var0) / 60.0;
      double var8 = MathUtils.clamp01(var2);
      double var10 = MathUtils.clamp01(var4);
      int var12 = (int)var6;
      double var13 = var6 - (double)var12;
      double var15 = var10 * (1.0 - var8);
      double var17 = var10 * (1.0 - var8 * var13);
      double var19 = var10 * (1.0 - var8 * (1.0 - var13));
      double var21;
      double var23;
      double var25;
      switch (var12 % 6) {
         case 0:
            var21 = var10;
            var23 = var19;
            var25 = var15;
            break;
         case 1:
            var21 = var17;
            var23 = var10;
            var25 = var15;
            break;
         case 2:
            var21 = var15;
            var23 = var10;
            var25 = var19;
            break;
         case 3:
            var21 = var15;
            var23 = var17;
            var25 = var10;
            break;
         case 4:
            var21 = var19;
            var23 = var15;
            var25 = var10;
            break;
         default:
            var21 = var10;
            var23 = var15;
            var25 = var17;
      }

      int var27 = MathUtils.clampInt((int)Math.round(var21 * 255.0), 0, 255);
      int var28 = MathUtils.clampInt((int)Math.round(var23 * 255.0), 0, 255);
      int var29 = MathUtils.clampInt((int)Math.round(var25 * 255.0), 0, 255);
      return String.format(Locale.ROOT, "%02x%02x%02x", var27, var28, var29);
   }

   public static String normalizeHex(String var0) {
      if (var0 == null) {
         return "";
      } else {
         String var1 = var0.trim();
         if (var1.startsWith("#")) {
            var1 = var1.substring(1);
         }

         if (var1.length() != 6) {
            return "";
         } else {
            for (int var2 = 0; var2 < var1.length(); var2++) {
               char var3 = var1.charAt(var2);
               boolean var4 = var3 >= '0' && var3 <= '9';
               boolean var5 = var3 >= 'a' && var3 <= 'f';
               boolean var6 = var3 >= 'A' && var3 <= 'F';
               if (!var4 && !var5 && !var6) {
                  return "";
               }
            }

            return var1.toLowerCase(Locale.ROOT);
         }
      }
   }
}
