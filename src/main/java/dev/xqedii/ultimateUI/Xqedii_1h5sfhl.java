package dev.xqedii.ultimateUI;

public final class Xqedii_1h5sfhl extends Xqedii_ev3jp8w {
   public final Object[] Xqedii_syideh7;

   public Xqedii_1h5sfhl(Object[] var1, int var2) {
      super(var2);
      this.Xqedii_syideh7 = var1;
   }

   @Override
   public Object Xqedii_28g36j3(Xqedii_y5pr2ec var1) {
      {
         StringBuilder var2 = new StringBuilder();
         Object[] var3 = this.Xqedii_syideh7;
         int var4 = var3.length;

         for (int var5 = 0; var5 < var4; var5++) {
            Object var6 = var3[var5];
            if (var6 instanceof Xqedii_ev3jp8w var7) {
               var2.append(Xqedii_z4wg071.Xqedii_2mlipa7(var7.Xqedii_28g36j3(var1)));
            } else {
               var2.append((String)var6);
            }
         }

         return var1.Xqedii_3a9kp91.Xqedii_2fi2lg1 == null
            ? var2.toString()
            : var1.Xqedii_3a9kp91.Xqedii_2fi2lg1.Xqedii_14ikmfz(var1.Xqedii_3a9kp91.Xqedii_1v4ue4a, var2.toString());
      }
   }
}
