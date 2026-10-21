package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;

public record UiImageLabel(String text, String imageUrl) {

   public static UiImageLabel text(String var0) {
      {
         return new UiImageLabel(var0 == null ? "" : var0, null);
      }
   }

   public static UiImageLabel image(String var0) {
      {
         return new UiImageLabel("", var0);
      }
   }

   @Override

   public String imageUrl() {
      {
         return this.imageUrl;
      }
   }
}
