package dev.xqedii.ultimateUI;

public final class Xqedii_1a78ac9 extends Xqedii_ev3jp8w {
   public final boolean Xqedii_5ufsb02;
   public final Xqedii_ev3jp8w Xqedii_3r8cou5;
   public final Xqedii_ev3jp8w Xqedii_38d5afm;

   public Xqedii_1a78ac9(boolean var1, Xqedii_ev3jp8w var2, Xqedii_ev3jp8w var3, int var4) {
      super(var4);
      this.Xqedii_5ufsb02 = var1;
      this.Xqedii_3r8cou5 = var2;
      this.Xqedii_38d5afm = var3;
   }

   @Override
   public Object Xqedii_28g36j3(Xqedii_y5pr2ec var1) {
      {
         Object var2 = this.Xqedii_3r8cou5.Xqedii_28g36j3(var1);
         boolean var3 = Xqedii_z4wg071.Xqedii_2od1ceu(var2);
         if (this.Xqedii_5ufsb02) {
            return var3 ? this.Xqedii_38d5afm.Xqedii_28g36j3(var1) : var2;
         } else {
            return var3 ? var2 : this.Xqedii_38d5afm.Xqedii_28g36j3(var1);
         }
      }
   }
}
