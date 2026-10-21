package dev.xqedii.ultimateUI;

public final class Xqedii_2hd31nw {
   public final int Xqedii_3u2rq5h;
   public final int Xqedii_1kayom0;
   public final int Xqedii_1t1x42s;

   public Xqedii_2hd31nw(int var1, int var2, int var3) {
      this.Xqedii_3u2rq5h = var1;
      this.Xqedii_1kayom0 = var2;
      this.Xqedii_1t1x42s = var3;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof Xqedii_2hd31nw var2)) {
         return false;
      } else {
         return ((this.Xqedii_3u2rq5h == var2.Xqedii_3u2rq5h
               && this.Xqedii_1kayom0 == var2.Xqedii_1kayom0
               && this.Xqedii_1t1x42s == var2.Xqedii_1t1x42s
            ? 1
            : 0) != 0);
      }
   }

   @Override
   public int hashCode() {
      {
         int var1 = Integer.hashCode(this.Xqedii_3u2rq5h);
         var1 = 31 * var1 + Integer.hashCode(this.Xqedii_1kayom0);
         return 31 * var1 + Integer.hashCode(this.Xqedii_1t1x42s);
      }
   }
}
