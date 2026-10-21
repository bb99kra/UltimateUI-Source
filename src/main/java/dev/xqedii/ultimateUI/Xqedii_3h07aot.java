package dev.xqedii.ultimateUI;

public final class Xqedii_3h07aot extends Xqedii_1wtiniq {
   public final Xqedii_3nlck21[] Xqedii_236rf6i;
   public int Xqedii_skvaocs;

   public Xqedii_3h07aot(Xqedii_3nlck21[] var1) {
      this.Xqedii_236rf6i = var1;
   }

   @Override
   public boolean Xqedii_j55az06(Xqedii_y5pr2ec var1) {
      if (this.Xqedii_skvaocs >= this.Xqedii_236rf6i.length) {
         return false;
      } else {
         Xqedii_3nlck21[] var10000 = this.Xqedii_236rf6i;
         int var10001 = this.Xqedii_skvaocs;
         this.Xqedii_skvaocs = this.Xqedii_skvaocs + 1;
         Xqedii_3nlck21 var2 = var10000[var10001];
         var1.Xqedii_18k2h4u = var2.Xqedii_2lldvpl;
         var2.Xqedii_3egc8pa(var1);
         return true;
      }
   }
}
