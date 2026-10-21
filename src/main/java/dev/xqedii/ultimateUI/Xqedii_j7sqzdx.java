package dev.xqedii.ultimateUI;

import java.io.File;
import java.util.function.Consumer;

public abstract class Xqedii_j7sqzdx {
   public static final String Xqedii_19drtwf = "bedrock/pack";
   public static final String Xqedii_1otmwgy = "bedrock/generated";
   public static final String Xqedii_3g1rn1a = "UltimateUI-Bedrock.mcpack";
   public static final String Xqedii_28qtlsr = "pages.properties";
   public static final String Xqedii_dy8l5v1 = "pack-version.properties";
   public static final double Xqedii_obo6wdy = 240.0;
   public static final double Xqedii_3m9yl32 = 427.0;
   public static final double Xqedii_2fh37mo = 14.0;
   public static final String Xqedii_17uynnf = "form_buttons";
   public static final String Xqedii_3gmcg7n = "custom_form";
   public static final String Xqedii_hdzbzig = "#form_button_text";
   public static final String Xqedii_2hgzsaq = "#custom_text";
   public final File Xqedii_ef13m9a;
   public double Xqedii_rnovv49 = Double.longBitsToDouble(4646219479632576512L);
   public double Xqedii_s1m6hrv = Double.longBitsToDouble(4642648265865560064L);
   public double Xqedii_3b5g09f = Double.longBitsToDouble(4607182418800017408L);
   public boolean Xqedii_13gj34d;
   public boolean Xqedii_3ea0t6n = true;
   public boolean Xqedii_1j61zx3;
   public Consumer<String> Xqedii_31y3jzw = var0 -> {
      if ((427965745 * 427965745 + 427965745 & 1) != 0) {
         throw new IllegalStateException();
      }
   };
   public String Xqedii_291jtfy = "UltimateUI";
   public int[] Xqedii_3hhz4ql;
   public int[] Xqedii_3tmaf8b;

   public Xqedii_j7sqzdx(File var1) {
      int[] var10001 = new int[3];
      var10001[0] = 1;
      var10001[1] = 0;
      var10001[2] = 0;
      this.Xqedii_3hhz4ql = var10001;
      var10001 = new int[3];
      var10001[0] = 1;
      var10001[1] = 21;
      var10001[2] = 0;
      this.Xqedii_3tmaf8b = var10001;
      this.Xqedii_ef13m9a = var1;
   }

   public void Xqedii_3153b6r(String var1) {
      {
         if (this.Xqedii_1j61zx3) {
            this.Xqedii_31y3jzw.accept(var1);
         }
      }
   }
}
