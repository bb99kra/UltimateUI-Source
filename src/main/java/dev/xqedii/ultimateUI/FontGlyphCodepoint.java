package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;

public record FontGlyphCodepoint(String fileName, int codepoint) {

   @Override

   public int codepoint() {
      {
         return this.codepoint;
      }
   }
}
