package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class Xqedii_42544e5 extends Effect {
   public Expression<String> Xqedii_2jov3kg;
   public Expression<Player> Xqedii_1m1u24g;
   public boolean Xqedii_26fdiph;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         if (var1.length == 1) {
            this.Xqedii_1m1u24g = (Expression) var1[0];
            this.Xqedii_26fdiph = false;
         } else {
            this.Xqedii_2jov3kg = (Expression) var1[0];
            this.Xqedii_1m1u24g = (Expression) var1[1];
            this.Xqedii_26fdiph = true;
         }

         return true;
      }
   }

   public void execute(Event var1) {
      if (this.Xqedii_1m1u24g != null && Xqedii_1uy3j6u.api() != null) {
         String var2 = null;
         if (this.Xqedii_26fdiph && this.Xqedii_2jov3kg != null) {
            var2 = (String)this.Xqedii_2jov3kg.getSingle(var1);
         }

         Player[] var3 = (Player[])this.Xqedii_1m1u24g.getArray(var1);
         int var4 = var3.length;

         for (int var5 = 0; var5 < var4; var5++) {
            Player var6 = var3[var5];
            if (var6 != null && var6.isOnline()) {
               if (this.Xqedii_26fdiph && var2 != null && !var2.isBlank()) {
                  Xqedii_1uy3j6u.api().closeGui(var6, var2);
               } else {
                  Xqedii_1uy3j6u.api().closeGui(var6);
               }
            }
         }
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return "close ui effect";
      }
   }
}
