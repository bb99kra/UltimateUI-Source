package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;
import org.bukkit.entity.Player;

public record Xqedii_g4i3bi9(Player targetPlayer, boolean hudMode, boolean autoClose) {

   @Override

   public boolean hudMode() {
      {
         return this.hudMode;
      }
   }

   public boolean autoClose() {
      {
         return this.autoClose;
      }
   }
}
