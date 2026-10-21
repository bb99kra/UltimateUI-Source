package dev.xqedii.ultimateUI;

import java.util.LinkedHashMap;

public final class Xqedii_1i9eosk extends Xqedii_ev3jp8w {
   public final String[] Xqedii_3nt983w;
   public final Xqedii_ev3jp8w[] Xqedii_2bpzrzr;

   public Xqedii_1i9eosk(String[] var1, Xqedii_ev3jp8w[] var2, int var3) {
      super(var3);
      this.Xqedii_3nt983w = var1;
      this.Xqedii_2bpzrzr = var2;
   }

   @Override
   public Object Xqedii_28g36j3(Xqedii_y5pr2ec var1) {
      {
         LinkedHashMap var2 = new LinkedHashMap();

         for (int var3 = 0; var3 < this.Xqedii_3nt983w.length; var3++) {
            var2.put(this.Xqedii_3nt983w[var3], this.Xqedii_2bpzrzr[var3].Xqedii_28g36j3(var1));
         }

         return var2;
      }
   }
}
