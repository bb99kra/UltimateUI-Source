package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.gui.model.EditorSession;

public final class Xqedii_25i7cuk {

   public ColorPickerCoords Xqedii_g5n84hy(String var1) {
      {
         String var2 = Xqedii_170zfgx.Xqedii_4ea1q5n(var1);
         if (var2.length() != 6) {
            var2 = "ffffff";
         }

         double[] var3 = Xqedii_170zfgx.Xqedii_3c7y13v(var2);
         double var4 = var3[0];
         double var6 = var3[1];
         double var8 = var3[2];
         double var10 = Double.longBitsToDouble(4648902288004349952L)
            + (
                  Double.longBitsToDouble(4607182418800017408L)
                     - var4 / Double.longBitsToDouble(4645040803167600640L)
               )
               * Double.longBitsToDouble(4643070478330626048L);
         double var12 = Double.longBitsToDouble(4637159503819702272L)
            + var6 * Double.longBitsToDouble(4641170522237829120L);
         double var14 = Double.longBitsToDouble(4648937472376438784L)
            + (Double.longBitsToDouble(4607182418800017408L) - var8)
               * Double.longBitsToDouble(4643211215818981376L);
         return new ColorPickerCoords(
            var12 - Double.longBitsToDouble(-4600427019358961664L),
            var14 - Double.longBitsToDouble(4613937818241073152L),
            var10 - Double.longBitsToDouble(-4607182418800017408L)
         );
      }
   }

   public double Xqedii_3qh3fkc(double var1) {
      {
         double var3 = var1 + Double.longBitsToDouble(-4607182418800017408L);
         double var5 = Double.longBitsToDouble(4643070478330626048L);
         double var7 = var5 <= Double.longBitsToDouble(0L)
            ? Double.longBitsToDouble(0L)
            : (var3 - Double.longBitsToDouble(4648902288004349952L)) / var5;
         return Xqedii_1ud4czg.Xqedii_2zxxv1t(
            (Double.longBitsToDouble(4607182418800017408L) - var7)
               * Double.longBitsToDouble(4645040803167600640L),
            Double.longBitsToDouble(0L),
            Double.longBitsToDouble(4645040803167600640L)
         );
      }
   }

   public String Xqedii_27vc477(double var1) {
      {
         return Xqedii_170zfgx.Xqedii_2p150sg(this.Xqedii_3qh3fkc(var1));
      }
   }

   public double Xqedii_2eyc1hk(double var1) {
      {
         double var3 = Double.longBitsToDouble(4638003928749834240L);
         double var5 = Double.longBitsToDouble(4644266746981646336L);
         return Xqedii_1ud4czg.Xqedii_2zxxv1t(var1, var3, var5);
      }
   }

   public double Xqedii_30c6las(double var1) {
      {
         double var3 = Double.longBitsToDouble(4648911084097372160L);
         double var5 = Double.longBitsToDouble(4651162883911057408L);
         return Xqedii_1ud4czg.Xqedii_2zxxv1t(var1, var3, var5);
      }
   }

   public double Xqedii_2whi73a(double var1) {
      {
         double var3 = Double.longBitsToDouble(4648937472376438784L);
         double var5 = Double.longBitsToDouble(4651154087818035200L);
         return Xqedii_1ud4czg.Xqedii_2zxxv1t(var1, var3, var5);
      }
   }

   public boolean Xqedii_reufgab(double var1, double var3) {
      {
         return ((var1 >= Double.longBitsToDouble(4637159503819702272L)
               && var1 <= Double.longBitsToDouble(4644055640749113344L)
               && var3 >= Double.longBitsToDouble(4648937472376438784L)
               && var3 <= Double.longBitsToDouble(4651189272190124032L)
            ? 1
            : 0) != 0);
      }
   }

   public boolean Xqedii_3w514cm(double var1, double var3) {
      {
         return ((var1 >= Double.longBitsToDouble(4644425076656046080L)
               && var1 <= Double.longBitsToDouble(4645550976562888704L)
               && var3 >= Double.longBitsToDouble(4648902288004349952L)
               && var3 <= Double.longBitsToDouble(4651118903445946368L)
            ? 1
            : 0) != 0);
      }
   }

   public String Xqedii_orkmhot(EditorSession var1) {
      if (var1 == null) {
         return "ffffff";
      } else {
         double var2 = this.Xqedii_3qh3fkc(var1.colorPickerHueAbsY);
         double var4 = var1.colorPickerGradAbsX + Double.longBitsToDouble(-4600427019358961664L);
         double var6 = var1.colorPickerGradAbsY + Double.longBitsToDouble(4613937818241073152L);
         double var8 = Double.longBitsToDouble(4641170522237829120L);
         double var10 = Double.longBitsToDouble(4643211215818981376L);
         double var12 = var8 <= Double.longBitsToDouble(0L)
            ? Double.longBitsToDouble(0L)
            : (var4 - Double.longBitsToDouble(4637159503819702272L)) / var8;
         double var14 = var10 <= Double.longBitsToDouble(0L)
            ? Double.longBitsToDouble(4607182418800017408L)
            : Double.longBitsToDouble(4607182418800017408L)
               - (var6 - Double.longBitsToDouble(4648937472376438784L)) / var10;
         var12 = Xqedii_1ud4czg.Xqedii_gu7txdi(var12);
         var14 = Xqedii_1ud4czg.Xqedii_gu7txdi(var14);
         return Xqedii_170zfgx.Xqedii_1273r4j(var2, var12, var14);
      }
   }
}
