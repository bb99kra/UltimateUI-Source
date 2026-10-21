package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;

public record AnvilInputState(String inputId, String variable, String text, long token) {

   @Override

   public String variable() {
      {
         return this.variable;
      }
   }

   public String text() {
      {
         return this.text;
      }
   }

   public long token() {
      {
         return this.token;
      }
   }
}
