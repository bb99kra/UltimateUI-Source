package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class EffOpenUiInput extends Effect {
   public Expression<String> Xqedii_37nfx5h;
   public Expression<Player> Xqedii_2eyb9im;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_37nfx5h = (Expression) var1[0];
         this.Xqedii_2eyb9im = (Expression) var1[1];
         return true;
      }
   }

   public void execute(Event var1) {
      if (this.Xqedii_2eyb9im != null && this.Xqedii_37nfx5h != null && UltimateUiSkriptBridge.api() != null) {
         String var2 = (String)this.Xqedii_37nfx5h.getSingle(var1);
         if (var2 != null && !var2.isBlank()) {
            Player[] var3 = (Player[])this.Xqedii_2eyb9im.getArray(var1);
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; var5++) {
               Player var6 = var3[var5];
               if (var6 != null && var6.isOnline()) {
                  UltimateUiSkriptBridge.api().openInput(var6, var2);
               }
            }
         }
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return "open ui input effect";
      }
   }
}
