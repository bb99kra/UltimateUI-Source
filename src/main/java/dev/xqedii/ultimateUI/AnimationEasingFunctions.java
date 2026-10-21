package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.api.UiElementWrite;
import java.util.List;
import java.util.Locale;

public final class AnimationEasingFunctions {
   public static final List<String> Xqedii_2v8qm2d;
   public static final List<String> Xqedii_1wy8z23;
   public final String Xqedii_2kw8m64;
   public final String Xqedii_2dwrrc7;
   public final double[] Xqedii_11dp9l9;
   public final double[] Xqedii_3jfsng7;
   public final long Xqedii_242dioy;
   public final long Xqedii_2sukvew;
   public final String Xqedii_29kj390;

   public AnimationEasingFunctions(String var1, String var2, double[] var3, double[] var4, long var5, long var7, String var9) {
      this.Xqedii_2kw8m64 = var1;
      this.Xqedii_2dwrrc7 = var2;
      this.Xqedii_11dp9l9 = var3;
      this.Xqedii_3jfsng7 = var4;
      this.Xqedii_242dioy = var5;
      this.Xqedii_2sukvew = Math.max(1L, var7);
      this.Xqedii_29kj390 = var9;
   }

   public static String Xqedii_1uh18ch(String var0) {
      {
         String var1 = var0 == null
            ? ""
            : var0.trim()
               .toLowerCase(Locale.ROOT)
               .replace((char)95, (char)45)
               .replace((char)32, (char)45);
         int var3 = -1;
         switch (var1.hashCode()) {
            case -2115337811:
               if (var1.equals("bouncein")) {
                  var3 = 13;
               }
               break;
            case -1965120668:
               if (var1.equals("ease-in")) {
                  var3 = 5;
               }
               break;
            case -1965056864:
               if (var1.equals("easeout")) {
                  var3 = 8;
               }
               break;
            case -1396673460:
               if (var1.equals("backin")) {
                  var3 = 19;
               }
               break;
            case -1392296225:
               if (var1.equals("bezier")) {
                  var3 = 9;
               }
               break;
            case -1383205240:
               if (var1.equals("bounce")) {
                  var3 = 10;
               }
               break;
            case -1321888119:
               if (var1.equals("bounce-out")) {
                  var3 = 14;
               }
               break;
            case -1310315117:
               if (var1.equals("easein")) {
                  var3 = 6;
               }
               break;
            case -1151020406:
               if (var1.equals("bounce-in")) {
                  var3 = 12;
               }
               break;
            case -1150956602:
               if (var1.equals("bounceout")) {
                  var3 = 15;
               }
               break;
            case -1102672091:
               if (var1.equals("linear")) {
                  var3 = 0;
               }
               break;
            case -898533970:
               if (var1.equals("smooth")) {
                  var3 = 3;
               }
               break;
            case -789192465:
               if (var1.equals("ease-out")) {
                  var3 = 7;
               }
               break;
            case -361990811:
               if (var1.equals("ease-in-out")) {
                  var3 = 1;
               }
               break;
            case -347262005:
               if (var1.equals("back-in")) {
                  var3 = 18;
               }
               break;
            case -347198201:
               if (var1.equals("backout")) {
                  var3 = 21;
               }
               break;
            case 3015911:
               if (var1.equals("back")) {
                  var3 = 16;
               }
               break;
            case 3105774:
               if (var1.equals("ease")) {
                  var3 = 4;
               }
               break;
            case 5939595:
               if (var1.equals("bounce-in-out")) {
                  var3 = 11;
               }
               break;
            case 1360213211:
               if (var1.equals("easeinout")) {
                  var3 = 2;
               }
               break;
            case 1455323724:
               if (var1.equals("back-in-out")) {
                  var3 = 17;
               }
               break;
            case 2119785832:
               if (var1.equals("back-out")) {
                  var3 = 20;
               }
         }
         return switch (var3) {
            case 0 -> "linear";
            case 1, 2, 3, 4 -> "smooth";
            case 5, 6 -> "ease-in";
            case 7, 8 -> "ease-out";
            case 9 -> "bezier";
            case 10, 11 -> "bounce";
            case 12, 13 -> "bounce-in";
            case 14, 15 -> "bounce-out";
            case 16, 17 -> "back";
            case 18, 19 -> "back-in";
            case 20, 21 -> "back-out";
            default -> null;
         };
      }
   }

   public static String Xqedii_pzxldhl(String var0) {
      {
         return Xqedii_kymbv2u17vqv(var0, String.join(", ", Xqedii_2v8qm2d));
      }
   }

   public boolean Xqedii_3oq00gt(long var1) {
      {
         return ((var1 - this.Xqedii_242dioy >= this.Xqedii_2sukvew
            ? 1
            : 0) != 0);
      }
   }

   public UiElementWrite Xqedii_s2v6yoo(long var1) {
      {
         double var3 = Math.min(
            Double.longBitsToDouble(4607182418800017408L),
            Math.max(
               Double.longBitsToDouble(0L),
               (double)(var1 - this.Xqedii_242dioy) / this.Xqedii_2sukvew
            )
         );
         double var5 = var3 >= Double.longBitsToDouble(4607182418800017408L)
            ? Double.longBitsToDouble(4607182418800017408L)
            : Xqedii_2ntqrxd.Xqedii_388ntc2(this.Xqedii_29kj390, var3);
         double[] var7 = new double[this.Xqedii_11dp9l9.length];

         for (int var8 = 0; var8 < this.Xqedii_11dp9l9.length; var8++) {
            var7[var8] = this.Xqedii_11dp9l9[var8] + (this.Xqedii_3jfsng7[var8] - this.Xqedii_11dp9l9[var8]) * var5;
         }

         String var10 = this.Xqedii_2dwrrc7;
         int var9 = -1;
         switch (var10.hashCode()) {
            case -1267206133:
               if (var10.equals("opacity")) {
                  var9 = 0;
               }
               break;
            case -40300674:
               if (var10.equals("rotation")) {
                  var9 = 1;
               }
               break;
            case 94842723:
               if (var10.equals("color")) {
                  var9 = 2;
               }
         }
         return switch (var9) {
            case 0 -> UiElementWrite.value(
               this.Xqedii_2kw8m64,
               "opacity",
               (int)Math.max(
                  0L,
                  Math.min(255L, Math.round(var7[0]))
               )
            );
            case 1 -> UiElementWrite.value(
               this.Xqedii_2kw8m64,
               "rotation",
               var7[0]
            );
            case 2 -> {
               String var11 = this.Xqedii_2kw8m64;
               Locale var10001 = Locale.ROOT;
               String var10002 = "#%02x%02x%02x";
               Object[] var10003 = new Object[3];
               var10003[0] = Xqedii_2sujxtl(var7[0]);
               var10003[1] = Xqedii_2sujxtl(var7[1]);
               var10003[2] = Xqedii_2sujxtl(var7[2]);
               yield UiElementWrite.color(var11, String.format(var10001, var10002, var10003));
            }
            default -> new UiElementWrite(
               this.Xqedii_2kw8m64,
               this.Xqedii_2dwrrc7,
               Math.round(
                     var7[0]
                        * Double.longBitsToDouble(4636737291354636288L)
                  )
                  / Double.longBitsToDouble(4636737291354636288L),
               null
            );
         };
      }
   }

   public static int Xqedii_2sujxtl(double var0) {
      {
         return (int)Math.max(
            0L,
            Math.min(255L, Math.round(var0))
         );
      }
   }

   public static double[] Xqedii_1ugf7tn(String var0, int var1) {
      {
         String var2 = var0 == null ? "" : var0.trim();
         if (var2.startsWith("#")) {
            var2 = var2.substring(1);
         }

         if (var2.length() == 3) {
            var2 = Xqedii_122e9ufu6pxrx(
               var2.charAt(0),
               var2.charAt(0),
               var2.charAt(1),
               var2.charAt(1),
               var2.charAt(2),
               var2.charAt(2)
            );
         }

         if (var2.length() != 6) {
            throw new ScriptSyntaxException(Xqedii_3ummwv3a1jrjt(var0), var1);
         } else {
            try {
               int var3 = Integer.parseInt(var2, 16);
               double[] var10000 = new double[3];
               var10000[0] = var3 >> 16
                  & 255;
               var10000[1] = var3 >> 8
                  & 255;
               var10000[2] = var3 & 255;
               return var10000;
            } catch (NumberFormatException var4) {
               throw new ScriptSyntaxException(Xqedii_37ru909u0dpkt(var0), var1);
            }
         }
      }
   }

   static {
      {
         String[] var10000 = new String[12];
         var10000[0] = "linear";
         var10000[1] = "ease-in-out";
         var10000[2] = "ease-in";
         var10000[3] = "ease-out";
         var10000[4] = "smooth";
         var10000[5] = "bezier";
         var10000[6] = "bounce";
         var10000[7] = "bounce-in";
         var10000[8] = "bounce-out";
         var10000[9] = "back";
         var10000[10] = "back-in";
         var10000[11] = "back-out";
         Xqedii_2v8qm2d = List.of(var10000);
         Xqedii_1wy8z23 = List.of(
            "x",
            "y",
            "width",
            "height",
            "opacity",
            "rotation",
            "color"
         );
      }
   }

   private static /* synthetic */ String Xqedii_kymbv2u17vqv(String string, String string2) {
        return "Unknown animation '" + string + "'. Use: " + string2;
    }

    private static /* synthetic */ String Xqedii_122e9ufu6pxrx(char c, char c2, char c3, char c4, char c5, char c6) {
        return "" + c + c2 + c3 + c4 + c5 + c6;
    }

    private static /* synthetic */ String Xqedii_3ummwv3a1jrjt(String string) {
        return "Expected a colour like \"#ff8800\" but got \"" + string + "\"";
    }

    private static /* synthetic */ String Xqedii_37ru909u0dpkt(String string) {
        return "Expected a colour like \"#ff8800\" but got \"" + string + "\"";
    }
}
