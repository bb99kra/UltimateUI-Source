package dev.xqedii.ultimateUI;

import java.util.Locale;

public enum Xqedii_hme1l2t {
   ITEMSADDER("ItemsAdder"),
   NEXO("Nexo"),
   ORAXEN("Oraxen");

   public final String pluginName;

   Xqedii_hme1l2t(String var3) {
      this.pluginName = var3;
   }

   public String Xqedii_lho1nuv() {
      {
         return this.pluginName;
      }
   }

   public static Xqedii_hme1l2t Xqedii_3mduzsu(String var0) {
      if (var0 == null) {
         return null;
      } else {
         String var1 = var0.trim().toLowerCase(Locale.ROOT);
         Xqedii_hme1l2t[] var2 = values();
         int var3 = var2.length;

         for (int var4 = 0; var4 < var3; var4++) {
            Xqedii_hme1l2t var5 = var2[var4];
            if (var5.pluginName.toLowerCase(Locale.ROOT).equals(var1)) {
               return var5;
            }
         }

         return null;
      }
   }

}
