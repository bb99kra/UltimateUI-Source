package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;
import java.util.List;

public record ScriptCompilationResult(int scripts, int handlers, List<ScriptErrorEntry> errors) {

   @Override

   public int handlers() {
      {
         return this.handlers;
      }
   }

   public List<ScriptErrorEntry> errors() {
      {
         return this.errors;
      }
   }
}
