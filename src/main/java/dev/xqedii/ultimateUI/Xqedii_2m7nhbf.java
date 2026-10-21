package dev.xqedii.ultimateUI;

import java.util.ArrayList;

public final class Xqedii_2m7nhbf extends Xqedii_ev3jp8w {
   public final Xqedii_ev3jp8w[] Xqedii_w7rakxn;

   public Xqedii_2m7nhbf(Xqedii_ev3jp8w[] var1, int var2) {
      super(var2);
      this.Xqedii_w7rakxn = var1;
   }

   @Override
   public Object Xqedii_28g36j3(Xqedii_y5pr2ec var1) {
      {
         ArrayList var2 = new ArrayList(this.Xqedii_w7rakxn.length);
         Xqedii_ev3jp8w[] var3 = this.Xqedii_w7rakxn;
         int var4 = var3.length;

         for (int var5 = 0; var5 < var4; var5++) {
            Xqedii_ev3jp8w var6 = var3[var5];
            var2.add(var6.Xqedii_28g36j3(var1));
         }

         return var2;
      }
   }
}
