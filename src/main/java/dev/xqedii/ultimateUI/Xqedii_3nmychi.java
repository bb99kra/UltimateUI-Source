package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;

public record Xqedii_3nmychi(String text, String imageUrl) {

   public static Xqedii_3nmychi text(String var0) {
      {
         return new Xqedii_3nmychi(var0 == null ? "" : var0, null);
      }
   }

   public static Xqedii_3nmychi image(String var0) {
      {
         return new Xqedii_3nmychi("", var0);
      }
   }

   @Override

   public String imageUrl() {
      {
         return this.imageUrl;
      }
   }
}
