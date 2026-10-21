package dev.xqedii.ultimateUI;

public final class Xqedii_2ntqrxd {

   public static double Xqedii_388ntc2(String var0, double var1) {
      {
         double var3 = Xqedii_1ud4czg.Xqedii_gu7txdi(var1);
         if ("linear".equals(var0)) {
            return var3;
         } else if ("smooth".equals(var0)) {
            return var3 < Double.longBitsToDouble(4602678819172646912L)
               ? Double.longBitsToDouble(4616189618054758400L) * var3 * var3 * var3
               : Double.longBitsToDouble(4607182418800017408L)
                  - Math.pow(
                        Double.longBitsToDouble(-4611686018427387904L) * var3
                           + Double.longBitsToDouble(4611686018427387904L),
                        Double.longBitsToDouble(4613937818241073152L)
                     )
                     / Double.longBitsToDouble(4611686018427387904L);
         } else if ("ease-in".equals(var0)) {
            return var3 * var3 * var3;
         } else if ("ease-out".equals(var0)) {
            return Double.longBitsToDouble(4607182418800017408L)
               - Math.pow(
                  Double.longBitsToDouble(4607182418800017408L) - var3,
                  Double.longBitsToDouble(4613937818241073152L)
               );
         } else if ("bezier".equals(var0)) {
            return Xqedii_28k1pw0(
               var3,
               Double.longBitsToDouble(4598175219545276416L),
               Double.longBitsToDouble(4591870180066957722L),
               Double.longBitsToDouble(4598175219545276416L),
               Double.longBitsToDouble(4607182418800017408L)
            );
         } else if ("bounce".equals(var0)) {
            return Xqedii_39nja1n(var3);
         } else if ("bounce-in".equals(var0)) {
            return Xqedii_1r6jxs8(var3);
         } else if ("bounce-out".equals(var0)) {
            return Xqedii_za8bpjg(var3);
         } else if ("back".equals(var0)) {
            return Xqedii_15825lu(var3);
         } else if ("back-in".equals(var0)) {
            return Xqedii_32ods7p(var3);
         } else {
            return "back-out".equals(var0)
               ? Xqedii_16xwht7(var3)
               : var3;
         }
      }
   }

   public static double Xqedii_za8bpjg(double var0) {
      {
         double var2 = Double.longBitsToDouble(4620200636472885248L);
         double var4 = Double.longBitsToDouble(4613374868287651840L);
         if (var0 < Double.longBitsToDouble(4607182418800017408L) / var4) {
            return var2 * var0 * var0;
         } else if (var0 < Double.longBitsToDouble(4611686018427387904L) / var4) {
            double var9 = var0 - Double.longBitsToDouble(4609434218613702656L) / var4;
            return var2 * var9 * var9 + Double.longBitsToDouble(4604930618986332160L);
         } else if (var0 < Double.longBitsToDouble(4612811918334230528L) / var4) {
            double var8 = var0 - Double.longBitsToDouble(4612248968380809216L) / var4;
            return var2 * var8 * var8 + Double.longBitsToDouble(4606619468846596096L);
         } else {
            double var6 = var0 - Double.longBitsToDouble(4613093393310941184L) / var4;
            return var2 * var6 * var6 + Double.longBitsToDouble(4607041681311662080L);
         }
      }
   }

   public static double Xqedii_1r6jxs8(double var0) {
      {
         return Double.longBitsToDouble(4607182418800017408L)
            - Xqedii_za8bpjg(Double.longBitsToDouble(4607182418800017408L) - var0);
      }
   }

   public static double Xqedii_39nja1n(double var0) {
      {
         return var0 < Double.longBitsToDouble(4602678819172646912L)
            ? (
                  Double.longBitsToDouble(4607182418800017408L)
                     - Xqedii_za8bpjg(
                        Double.longBitsToDouble(4607182418800017408L)
                           - Double.longBitsToDouble(4611686018427387904L) * var0
                     )
               )
               * Double.longBitsToDouble(4602678819172646912L)
            : (
                  Double.longBitsToDouble(4607182418800017408L)
                     + Xqedii_za8bpjg(
                        Double.longBitsToDouble(4611686018427387904L) * var0
                           - Double.longBitsToDouble(4607182418800017408L)
                     )
               )
               * Double.longBitsToDouble(4602678819172646912L);
      }
   }

   public static double Xqedii_32ods7p(double var0) {
      {
         double var2 = Double.longBitsToDouble(4610342054226588001L);
         double var4 = var2 + Double.longBitsToDouble(4607182418800017408L);
         return var4 * var0 * var0 * var0 - var2 * var0 * var0;
      }
   }

   public static double Xqedii_16xwht7(double var0) {
      {
         double var2 = Double.longBitsToDouble(4610342054226588001L);
         double var4 = var2 + Double.longBitsToDouble(4607182418800017408L);
         double var6 = var0 - Double.longBitsToDouble(4607182418800017408L);
         return Double.longBitsToDouble(4607182418800017408L) + var4 * var6 * var6 * var6 + var2 * var6 * var6;
      }
   }

   public static double Xqedii_15825lu(double var0) {
      {
         double var2 = Double.longBitsToDouble(4610342054226588001L);
         double var4 = var2 * Double.longBitsToDouble(4609546808604386918L);
         if (var0 < Double.longBitsToDouble(4602678819172646912L)) {
            double var8 = Double.longBitsToDouble(4611686018427387904L) * var0;
            return var8
               * var8
               * ((var4 + Double.longBitsToDouble(4607182418800017408L)) * var8 - var4)
               * Double.longBitsToDouble(4602678819172646912L);
         } else {
            double var6 = Double.longBitsToDouble(4611686018427387904L) * var0
               - Double.longBitsToDouble(4611686018427387904L);
            return (
                  var6 * var6 * ((var4 + Double.longBitsToDouble(4607182418800017408L)) * var6 + var4)
                     + Double.longBitsToDouble(4611686018427387904L)
               )
               * Double.longBitsToDouble(4602678819172646912L);
         }
      }
   }

   public static double Xqedii_28k1pw0(double var0, double var2, double var4, double var6, double var8) {
      {
         double var10 = Xqedii_1ud4czg.Xqedii_gu7txdi(var0);
         double var12 = var10;

         for (int var14 = 0; var14 < 6; var14++) {
            double var15 = Xqedii_34dex8y(var12, var2, var6) - var10;
            double var17 = Xqedii_4jpfc2a(var12, var2, var6);
            if (Math.abs(var17) < Double.longBitsToDouble(4517329193108106637L)) {
               break;
            }

            var12 -= var15 / var17;
            if (var12 <= Double.longBitsToDouble(0L)
               || var12 >= Double.longBitsToDouble(4607182418800017408L)) {
               break;
            }
         }

         if (var12 <= Double.longBitsToDouble(0L)
            || var12 >= Double.longBitsToDouble(4607182418800017408L)) {
            double var21 = Double.longBitsToDouble(0L);
            double var16 = Double.longBitsToDouble(4607182418800017408L);
            var12 = var10;

            for (int var18 = 0; var18 < 14; var18++) {
               double var19 = Xqedii_34dex8y(var12, var2, var6);
               if (var19 < var10) {
                  var21 = var12;
               } else {
                  var16 = var12;
               }

               var12 = (var21 + var16) * Double.longBitsToDouble(4602678819172646912L);
            }
         }

         return Xqedii_34dex8y(var12, var4, var8);
      }
   }

   public static double Xqedii_34dex8y(double var0, double var2, double var4) {
      {
         double var6 = Double.longBitsToDouble(4607182418800017408L) - var0;
         return Double.longBitsToDouble(4613937818241073152L) * var6 * var6 * var0 * var2
            + Double.longBitsToDouble(4613937818241073152L) * var6 * var0 * var0 * var4
            + var0 * var0 * var0;
      }
   }

   public static double Xqedii_4jpfc2a(double var0, double var2, double var4) {
      {
         double var6 = Double.longBitsToDouble(4607182418800017408L) - var0;
         return Double.longBitsToDouble(4613937818241073152L) * var6 * var6 * var2
            + Double.longBitsToDouble(4618441417868443648L) * var6 * var0 * (var4 - var2)
            + Double.longBitsToDouble(4613937818241073152L)
               * var0
               * var0
               * (Double.longBitsToDouble(4607182418800017408L) - var4);
      }
   }
}
