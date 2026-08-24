package dev.xqedii.ultimateUI.service.gui.editor.shell.rendering;

import dev.xqedii.ultimateUI.service.hud.HudService;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public final class EditorHudRenderer {
   private EditorHudRenderer() {
   }

   public static void setHudNoTransitionIfExists(HudService var0, Player var1, String var2) {
      if (var0 != null && var1 != null && var2 != null && !var2.isBlank()) {
         Entity var3 = var0.getHud(var1, var2);
         if (var3 != null) {
            var0.setHudNoTransition(var3);
         }
      }
   }

   public static void setHudTransitionTicksIfExists(HudService var0, Player var1, String var2, int var3, int var4) {
      if (var0 != null && var1 != null && var2 != null && !var2.isBlank()) {
         Entity var5 = var0.getHud(var1, var2);
         if (var5 != null) {
            var0.setHudTransitionTicks(var5, var3, var4);
         }
      }
   }

   public static void setHudOpacityIfExists(HudService var0, Player var1, String var2, int var3) {
      if (var0 != null && var1 != null && var2 != null && !var2.isBlank()) {
         Entity var4 = var0.getHud(var1, var2);
         if (var4 != null) {
            var0.setOpacity(var4, var3);
         }
      }
   }
}
