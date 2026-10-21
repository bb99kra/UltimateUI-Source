package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.hud.HudService;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public final class Xqedii_3cny9rh {

   public static void Xqedii_1tc6luf(HudService var0, Player var1, String var2) {
      if (var0 != null && var1 != null && var2 != null && !var2.isBlank()) {
         Entity var3 = var0.getHud(var1, var2);
         if (var3 != null) {
            var0.setHudNoTransition(var3);
         }
      }
   }

   public static void Xqedii_21i7jf4(HudService var0, Player var1, String var2, int var3, int var4) {
      if (var0 != null && var1 != null && var2 != null && !var2.isBlank()) {
         Entity var5 = var0.getHud(var1, var2);
         if (var5 != null) {
            var0.setHudTransitionTicks(var5, var3, var4);
         }
      }
   }

   public static void Xqedii_2bwaggn(HudService var0, Player var1, String var2, int var3) {
      if (var0 != null && var1 != null && var2 != null && !var2.isBlank()) {
         Entity var4 = var0.getHud(var1, var2);
         if (var4 != null) {
            var0.setOpacity(var4, var3);
         }
      }
   }
}
