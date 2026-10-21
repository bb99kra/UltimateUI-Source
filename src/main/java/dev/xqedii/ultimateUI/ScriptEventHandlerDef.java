package dev.xqedii.ultimateUI;

import java.util.Locale;
import java.util.regex.Pattern;

public final class ScriptEventHandlerDef {
   public final CompiledScript Xqedii_1swqvpn;
   public final String Xqedii_27kq3c6;
   public final String Xqedii_2t32j8y;
   public final String Xqedii_nyxmub2;
   public final int Xqedii_3e9f81u;
   public StatementAstNode[] Xqedii_fw60fwr = new StatementAstNode[0];
   public long Xqedii_3gg5za6;
   public String Xqedii_ne1a52s = "";
   public String Xqedii_3al7qe9 = "";
   public final Pattern Xqedii_3pka17o;

   public ScriptEventHandlerDef(CompiledScript var1, String var2, String var3, String var4, int var5) {
      this.Xqedii_1swqvpn = var1;
      this.Xqedii_27kq3c6 = var2;
      this.Xqedii_2t32j8y = var3 != null && !var3.isBlank() ? var3.trim() : null;
      this.Xqedii_nyxmub2 = var4 != null && !var4.isBlank() ? var4.trim() : null;
      this.Xqedii_3e9f81u = var5;
      this.Xqedii_3pka17o = this.Xqedii_2t32j8y != null && this.Xqedii_2t32j8y.indexOf(42) >= 0
         ? Xqedii_176qhx5(this.Xqedii_2t32j8y)
         : null;
   }

   public static Pattern Xqedii_176qhx5(String var0) {
      {
         StringBuilder var1 = new StringBuilder();
         String[] var2 = var0.split(
            "\\*", -1
         );
         int var3 = var2.length;

         for (int var4 = 0; var4 < var3; var4++) {
            String var5 = var2[var4];
            if (!var1.isEmpty()) {
               var1.append(".*");
            }

            var1.append(Pattern.quote(var5));
         }

         return Pattern.compile(var1.toString(), 2);
      }
   }

   public boolean Xqedii_21qp861(String var1) {
      {
         return ((this.Xqedii_nyxmub2 != null
               && !this.Xqedii_nyxmub2.equalsIgnoreCase(var1 == null ? "" : var1)
            ? 0
            : 1) != 0);
      }
   }

   public boolean Xqedii_2f871ze(String var1) {
      if (this.Xqedii_2t32j8y == null) {
         return true;
      } else if (var1 == null || var1.isEmpty()) {
         return false;
      } else {
         return this.Xqedii_3pka17o != null ? this.Xqedii_3pka17o.matcher(var1).matches() : this.Xqedii_2t32j8y.equalsIgnoreCase(var1);
      }
   }

   public String Xqedii_2no2w4r() {
      {
         return Xqedii_ryvfag8apnbd(
            this.Xqedii_1swqvpn.Xqedii_1gnbwtp,
            this.Xqedii_3e9f81u,
            this.Xqedii_27kq3c6,
            this.Xqedii_2t32j8y == null
               ? ""
               : Xqedii_28yz1e2bwld57(this.Xqedii_2t32j8y)
         );
      }
   }

   public String Xqedii_dg8qjon() {
      {
         return this.Xqedii_27kq3c6.toLowerCase(Locale.ROOT);
      }
   }

   private static /* synthetic */ String Xqedii_28yz1e2bwld57(String string) {
        return " \"" + string + "\"";
    }

    private static /* synthetic */ String Xqedii_ryvfag8apnbd(String string, int n, String string2, String string3) {
        return string + ":" + n + " (on " + string2 + string3 + ")";
    }
}
