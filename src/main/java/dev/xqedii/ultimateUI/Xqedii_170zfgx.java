package dev.xqedii.ultimateUI;

import java.util.Locale;

public final class Xqedii_170zfgx {

   public static double Xqedii_5d87122(double var0) {
      if (!Double.isFinite(var0)) {
         return Double.longBitsToDouble(0L);
      } else {
         double var2 = var0 % Double.longBitsToDouble(4645040803167600640L);
         if (var2 < Double.longBitsToDouble(0L)) {
            var2 += Double.longBitsToDouble(4645040803167600640L);
         }

         return var2;
      }
   }

   public static double Xqedii_12k2o8j(String var0) {
      {
         String var1 = Xqedii_4ea1q5n(var0);
         if (var1.length() != 6) {
            return Double.longBitsToDouble(0L);
         } else {
            int var2;
            try {
               var2 = Integer.parseInt(var1, 16);
            } catch (NumberFormatException var17) {
               return Double.longBitsToDouble(0L);
            }

            double var3 = (var2 >> 16 & 255)
               / Double.longBitsToDouble(4643176031446892544L);
            double var5 = (var2 >> 8 & 255)
               / Double.longBitsToDouble(4643176031446892544L);
            double var7 = (var2 & 255)
               / Double.longBitsToDouble(4643176031446892544L);
            double var9 = Math.max(var3, Math.max(var5, var7));
            double var11 = Math.min(var3, Math.min(var5, var7));
            double var13 = var9 - var11;
            if (var13 < Double.longBitsToDouble(4517329193108106637L)) {
               return Double.longBitsToDouble(0L);
            } else {
               double var15;
               if (Math.abs(var9 - var3) < Double.longBitsToDouble(4517329193108106637L)) {
                  var15 = (var5 - var7) / var13 % Double.longBitsToDouble(4618441417868443648L);
               } else if (Math.abs(var9 - var5) < Double.longBitsToDouble(4517329193108106637L)) {
                  var15 = (var7 - var3) / var13 + Double.longBitsToDouble(4611686018427387904L);
               } else {
                  var15 = (var3 - var5) / var13 + Double.longBitsToDouble(4616189618054758400L);
               }

               return Xqedii_5d87122(var15 * Double.longBitsToDouble(4633641066610819072L));
            }
         }
      }
   }

   public static String Xqedii_2p150sg(double var0) {
      {
         double var2 = Xqedii_5d87122(var0) / Double.longBitsToDouble(4633641066610819072L);
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
               var7 = Double.longBitsToDouble(4607182418800017408L);
               var9 = var5;
               var11 = Double.longBitsToDouble(0L);
               break;
            case 1:
               var7 = Double.longBitsToDouble(4607182418800017408L) - var5;
               var9 = Double.longBitsToDouble(4607182418800017408L);
               var11 = Double.longBitsToDouble(0L);
               break;
            case 2:
               var7 = Double.longBitsToDouble(0L);
               var9 = Double.longBitsToDouble(4607182418800017408L);
               var11 = var5;
               break;
            case 3:
               var7 = Double.longBitsToDouble(0L);
               var9 = Double.longBitsToDouble(4607182418800017408L) - var5;
               var11 = Double.longBitsToDouble(4607182418800017408L);
               break;
            case 4:
               var7 = var5;
               var9 = Double.longBitsToDouble(0L);
               var11 = Double.longBitsToDouble(4607182418800017408L);
               break;
            default:
               var7 = Double.longBitsToDouble(4607182418800017408L);
               var9 = Double.longBitsToDouble(0L);
               var11 = Double.longBitsToDouble(4607182418800017408L) - var5;
         }

         int var13 = Xqedii_1ud4czg.Xqedii_3773gw7(
            (int)Math.round(var7 * Double.longBitsToDouble(4643176031446892544L)),
            0,
            255
         );
         int var14 = Xqedii_1ud4czg.Xqedii_3773gw7(
            (int)Math.round(var9 * Double.longBitsToDouble(4643176031446892544L)),
            0,
            255
         );
         int var15 = Xqedii_1ud4czg.Xqedii_3773gw7(
            (int)Math.round(var11 * Double.longBitsToDouble(4643176031446892544L)),
            0,
            255
         );
         Locale var10000 = Locale.ROOT;
         String var10001 = "%02x%02x%02x";
         Object[] var10002 = new Object[3];
         var10002[0] = var13;
         var10002[1] = var14;
         var10002[2] = var15;
         return String.format(var10000, var10001, var10002);
      }
   }

   public static double[] Xqedii_3c7y13v(String var0) {
      {
         String var1 = Xqedii_4ea1q5n(var0);
         if (var1.length() != 6) {
            double[] var24 = new double[3];
            var24[0] = Double.longBitsToDouble(0L);
            var24[1] = Double.longBitsToDouble(0L);
            var24[2] = Double.longBitsToDouble(4607182418800017408L);
            return var24;
         } else {
            int var2;
            try {
               var2 = Integer.parseInt(var1, 16);
            } catch (NumberFormatException var21) {
               double[] var10000 = new double[3];
               var10000[0] = Double.longBitsToDouble(
                  0L
               );
               var10000[1] = Double.longBitsToDouble(
                  0L
               );
               var10000[2] = Double.longBitsToDouble(
                  4607182418800017408L
               );
               return var10000;
            }

            double var3 = (var2 >> 16 & 255)
               / Double.longBitsToDouble(4643176031446892544L);
            double var5 = (var2 >> 8 & 255)
               / Double.longBitsToDouble(4643176031446892544L);
            double var7 = (var2 & 255)
               / Double.longBitsToDouble(4643176031446892544L);
            double var9 = Math.max(var3, Math.max(var5, var7));
            double var11 = Math.min(var3, Math.min(var5, var7));
            double var13 = var9 - var11;
            double var17 = var9 < Double.longBitsToDouble(4517329193108106637L)
               ? Double.longBitsToDouble(0L)
               : var13 / var9;
            double var19 = Double.longBitsToDouble(0L);
            if (var13 > Double.longBitsToDouble(4517329193108106637L)) {
               if (Math.abs(var9 - var3) < Double.longBitsToDouble(4517329193108106637L)) {
                  var19 = (var5 - var7) / var13 % Double.longBitsToDouble(4618441417868443648L);
               } else if (Math.abs(var9 - var5) < Double.longBitsToDouble(4517329193108106637L)) {
                  var19 = (var7 - var3) / var13 + Double.longBitsToDouble(4611686018427387904L);
               } else {
                  var19 = (var3 - var5) / var13 + Double.longBitsToDouble(4616189618054758400L);
               }

               var19 = (
                     var19 * Double.longBitsToDouble(4633641066610819072L)
                        + Double.longBitsToDouble(4645040803167600640L)
                  )
                  % Double.longBitsToDouble(4645040803167600640L);
            }

            double[] var23 = new double[3];
            var23[0] = var19;
            var23[1] = var17;
            var23[2] = var9;
            return var23;
         }
      }
   }

   public static String Xqedii_1273r4j(double var0, double var2, double var4) {
      {
         double var6 = Xqedii_5d87122(var0) / Double.longBitsToDouble(4633641066610819072L);
         double var8 = Xqedii_1ud4czg.Xqedii_gu7txdi(var2);
         double var10 = Xqedii_1ud4czg.Xqedii_gu7txdi(var4);
         int var12 = (int)var6;
         double var13 = var6 - var12;
         double var15 = var10 * (Double.longBitsToDouble(4607182418800017408L) - var8);
         double var17 = var10 * (Double.longBitsToDouble(4607182418800017408L) - var8 * var13);
         double var19 = var10
            * (
               Double.longBitsToDouble(4607182418800017408L)
                  - var8 * (Double.longBitsToDouble(4607182418800017408L) - var13)
            );
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

         int var27 = Xqedii_1ud4czg.Xqedii_3773gw7(
            (int)Math.round(var21 * Double.longBitsToDouble(4643176031446892544L)),
            0,
            255
         );
         int var28 = Xqedii_1ud4czg.Xqedii_3773gw7(
            (int)Math.round(var23 * Double.longBitsToDouble(4643176031446892544L)),
            0,
            255
         );
         int var29 = Xqedii_1ud4czg.Xqedii_3773gw7(
            (int)Math.round(var25 * Double.longBitsToDouble(4643176031446892544L)),
            0,
            255
         );
         Locale var10000 = Locale.ROOT;
         String var10001 = "%02x%02x%02x";
         Object[] var10002 = new Object[3];
         var10002[0] = var27;
         var10002[1] = var28;
         var10002[2] = var29;
         return String.format(var10000, var10001, var10002);
      }
   }

   public static String Xqedii_4ea1q5n(String var0) {
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
               int var4 = var3 >= 48 && var3 <= 57
                  ? 1
                  : 0;
               int var5 = var3 >= 97 && var3 <= 102
                  ? 1
                  : 0;
               int var6 = var3 >= 65 && var3 <= 70
                  ? 1
                  : 0;
               if (var4 == 0 && var5 == 0 && var6 == 0) {
                  return "";
               }
            }

            return var1.toLowerCase(Locale.ROOT);
         }
      }
   }
}
