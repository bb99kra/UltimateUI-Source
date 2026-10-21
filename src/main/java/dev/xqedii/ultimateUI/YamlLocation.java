package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;

public record YamlLocation(String name, int line, int column) {

   @Override

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
}
