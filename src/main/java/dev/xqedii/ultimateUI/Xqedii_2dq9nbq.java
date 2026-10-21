package dev.xqedii.ultimateUI;

public final class Xqedii_2dq9nbq {

   public static int Xqedii_2sc5lbd(int var0, int var1, boolean var2) {
      {
         int var3 = (var0 * 63 + (var1 - 1))
            * 2;
         return 196608
            + var3
            + (var2 ? 1 : 0);
      }
   }

   public static boolean Xqedii_gkeh9a2(StringBuilder var0, long var1) {
      {
         long var3 = var1;

         for (int var5 = 3; var5 >= 0; var5--) {
            long var6 = 1L;

            for (int var8 = 0; var8 < var5; var8++) {
               var6 *= 64L;
            }

            long var10 = var3 / var6;
            if (var10 > 63L) {
               var10 = 63L;
            } else if (var10 < -63L) {
               var10 = -63L;
            }

            if (var10 != 0L) {
               var0.appendCodePoint(
                  Xqedii_2sc5lbd(
                     var5,
                     (int)Math.abs(var10),
                     ((var10 < 0L
                        ? 1
                        : 0) != 0)
                  )
               );
               var3 -= var10 * var6;
            }
         }

         return ((var3 == 0L
            ? 1
            : 0) != 0);
      }
   }
}
