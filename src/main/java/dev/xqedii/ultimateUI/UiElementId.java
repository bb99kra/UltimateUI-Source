package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;

public record UiElementId(String id) {

   public static String idOf(Object var0, int var1) {
      if (var0 instanceof UiElementId var3) {
         return var3.id();
      } else if (var0 instanceof String var2 && !var2.isBlank()) {
         return var2.trim();
      } else if (var0 instanceof Number) {
         return Xqedii_z4wg071.Xqedii_2mlipa7(var0);
      } else {
         throw new ScriptSyntaxException(Xqedii_s892yzoojzvk(Xqedii_z4wg071.Xqedii_yl2qf35(var0)), var1);
      }
   }

   @Override
   public String toString() {
      {
         return this.id;
      }
   }

   @Override

   public String id() {
      {
         return this.id;
      }
   }

   private static /* synthetic */ String Xqedii_s892yzoojzvk(String string) {
        return "Expected an element id but got " + string;
    }
}
