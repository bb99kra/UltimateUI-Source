package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;

public record LexerToken(LexerTokenType type, String text, int line, int column) {

   public boolean is(LexerTokenType var1) {
      {
         return ((this.type == var1 ? 1 : 0) != 0);
      }
   }

   public boolean isSymbol(String var1) {
      {
         return ((this.type == LexerTokenType.SYMBOL && this.text.equals(var1)
            ? 1
            : 0) != 0);
      }
   }

   public boolean isWord(String var1) {
      {
         return ((this.type == LexerTokenType.NAME && this.text.equalsIgnoreCase(var1)
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
