package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;

public record ScriptErrorEntry(String file, ScriptSyntaxException error) {

   @Override

   public ScriptSyntaxException error() {
      {
         return this.error;
      }
   }
}
