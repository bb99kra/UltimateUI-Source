package dev.xqedii.ultimateUI;

public final class Xqedii_3cpwyhm extends Xqedii_3nlck21 {
   public final Xqedii_ev3jp8w[] Xqedii_58y2yio;
   public final Xqedii_3nlck21[][] Xqedii_h64xmxi;
   public final Xqedii_3nlck21[] Xqedii_38owvar;

   public Xqedii_3cpwyhm(Xqedii_ev3jp8w[] var1, Xqedii_3nlck21[][] var2, Xqedii_3nlck21[] var3, int var4) {
      super(var4);
      this.Xqedii_58y2yio = var1;
      this.Xqedii_h64xmxi = var2;
      this.Xqedii_38owvar = var3;
   }

   @Override
   public void Xqedii_3egc8pa(Xqedii_y5pr2ec var1) {
      {
         for (int var2 = 0; var2 < this.Xqedii_58y2yio.length; var2++) {
            if (Xqedii_z4wg071.Xqedii_2od1ceu(this.Xqedii_58y2yio[var2].Xqedii_28g36j3(var1))) {
               var1.Xqedii_158uidw(this.Xqedii_h64xmxi[var2]);
               return;
            }
         }

         if (this.Xqedii_38owvar != null) {
            var1.Xqedii_158uidw(this.Xqedii_38owvar);
         }
      }
   }
}
