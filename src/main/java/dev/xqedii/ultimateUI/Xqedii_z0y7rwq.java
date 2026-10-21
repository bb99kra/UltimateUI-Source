package dev.xqedii.ultimateUI;

public final class Xqedii_z0y7rwq extends Xqedii_9x9yoki {
   public final long Xqedii_25d71xt;
   public final Xqedii_3nlck21[] Xqedii_2f2pkfe;
   public long Xqedii_3pbk4j3;

   public Xqedii_z0y7rwq(long var1, Xqedii_3nlck21[] var3) {
      this.Xqedii_25d71xt = var1;
      this.Xqedii_2f2pkfe = var3;
   }

   @Override
   public boolean Xqedii_j55az06(Xqedii_y5pr2ec var1) {
      if (this.Xqedii_3pbk4j3 >= this.Xqedii_25d71xt) {
         return false;
      } else {
         this.Xqedii_3pbk4j3 = this.Xqedii_3pbk4j3 + 1L;
         var1.Xqedii_3a9kp91
            .Xqedii_107f1nz
            .put("loop", (double)this.Xqedii_3pbk4j3);
         var1.Xqedii_158uidw(this.Xqedii_2f2pkfe);
         return true;
      }
   }
}
