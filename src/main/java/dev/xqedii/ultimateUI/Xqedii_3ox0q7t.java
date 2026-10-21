package dev.xqedii.ultimateUI;

public final class Xqedii_3ox0q7t extends Xqedii_ev3jp8w {
   public final String Xqedii_3aepu2p;
   public final Xqedii_ev3jp8w[] Xqedii_1tnml5z;

   public Xqedii_3ox0q7t(String var1, Xqedii_ev3jp8w[] var2, int var3) {
      super(var3);
      this.Xqedii_3aepu2p = var1;
      this.Xqedii_1tnml5z = var2;
   }

   public Xqedii_2x2kkmf Xqedii_huubs12(Xqedii_y5pr2ec var1) {
      {
         return var1.Xqedii_3a9kp91.Xqedii_2q97y6i.Xqedii_3j5g4ep.get(this.Xqedii_3aepu2p);
      }
   }

   public Object[] Xqedii_3fcv7sv(Xqedii_y5pr2ec var1) {
      {
         Object[] var2 = new Object[this.Xqedii_1tnml5z.length];

         for (int var3 = 0; var3 < this.Xqedii_1tnml5z.length; var3++) {
            var2[var3] = this.Xqedii_1tnml5z[var3].Xqedii_28g36j3(var1);
         }

         return var2;
      }
   }

   @Override
   public Object Xqedii_28g36j3(Xqedii_y5pr2ec var1) {
      {
         Xqedii_2x2kkmf var2 = this.Xqedii_huubs12(var1);
         Object[] var3 = this.Xqedii_3fcv7sv(var1);
         return var2 != null
            ? var1.Xqedii_1kj5m0d(var2, var3, this.Xqedii_3dzdvck)
            : Xqedii_1iw7g71.Xqedii_2xeqrwc(var1, this.Xqedii_3aepu2p, var3, this.Xqedii_3dzdvck);
      }
   }
}
