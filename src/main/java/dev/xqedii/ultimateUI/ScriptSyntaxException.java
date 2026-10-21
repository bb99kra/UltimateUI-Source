package dev.xqedii.ultimateUI;

public final class ScriptSyntaxException extends RuntimeException {
   public final int Xqedii_38o5jfr;
   public final int Xqedii_3tzsvzs;

   public ScriptSyntaxException(String var1, int var2, int var3) {
      super(var1, null, false, false);
      this.Xqedii_38o5jfr = var2;
      this.Xqedii_3tzsvzs = var3;
   }

   public ScriptSyntaxException(String var1, int var2) {
      this(var1, var2, 0);
   }

   public int Xqedii_2ei61uy() {
      {
         return this.Xqedii_38o5jfr;
      }
   }

   public int Xqedii_1rkxiyu() {
      {
         return this.Xqedii_3tzsvzs;
      }
   }

   public String Xqedii_qiai955(String var1) {
      {
         return Xqedii_1vrq1iaelcri2(
            var1,
            this.Xqedii_38o5jfr,
            this.Xqedii_3tzsvzs > 0 ? Xqedii_isyttq4brzrt(this.Xqedii_3tzsvzs) : "",
            this.getMessage()
         );
      }
   }

   private static /* synthetic */ String Xqedii_isyttq4brzrt(int n) {
        return ":" + n;
    }

    private static /* synthetic */ String Xqedii_1vrq1iaelcri2(String string, int n, String string2, String string3) {
        return string + ":" + n + string2 + " " + string3;
    }
}
