package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;

public record Xqedii_25ri1e7(Xqedii_1p9lu7f type, String text, int line, int column) {

   public boolean is(Xqedii_1p9lu7f var1) {
      {
         return ((this.type == var1 ? 1 : 0) != 0);
      }
   }

   public boolean isSymbol(String var1) {
      {
         return ((this.type == Xqedii_1p9lu7f.SYMBOL && this.text.equals(var1)
            ? 1
            : 0) != 0);
      }
   }

   public boolean isWord(String var1) {
      {
         return ((this.type == Xqedii_1p9lu7f.NAME && this.text.equalsIgnoreCase(var1)
            ? 1
            : 0) != 0);
      }
   }

   public String describe() {
      {
         return switch (this.type) {
            case STRING -> Xqedii_11gok8wevvvrw(this.text);
            default -> Xqedii_3i4zk8wh0va64(this.text);
            case NEWLINE -> "end of line";
            case INDENT -> "indentation";
            case DEDENT -> "end of block";
            case EOF -> "end of file";
         };
      }
   }

   @Override

   public String text() {
      {
         return this.text;
      }
   }

   public int line() {
      {
         return this.line;
      }
   }

   public int column() {
      {
         return this.column;
      }
   }

   private static /* synthetic */ String Xqedii_11gok8wevvvrw(String string) {
        return "\"" + string + "\"";
    }

    private static /* synthetic */ String Xqedii_3i4zk8wh0va64(String string) {
        return "'" + string + "'";
    }
}
