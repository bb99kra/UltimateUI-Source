package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class EffRunUiAction extends Effect {
   public Expression<String> Xqedii_nhpf0d9;
   public Expression<String> Xqedii_18ilr9l;
   public Expression<Player> Xqedii_2b97xc6;
   public int Xqedii_3vshgam;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_3vshgam = var2;
         this.Xqedii_nhpf0d9 = (Expression) var1[0];
         if (var2 == 0) {
            this.Xqedii_18ilr9l = (Expression) var1[1];
            this.Xqedii_2b97xc6 = (Expression) var1[2];
         } else {
            this.Xqedii_2b97xc6 = (Expression) var1[1];
         }

         return true;
      }
   }

   public void execute(Event var1) {
      {
         UltimateUIAPI var2 = UltimateUiSkriptBridge.api();
         String var3 = this.Xqedii_nhpf0d9 == null ? null : (String)this.Xqedii_nhpf0d9.getSingle(var1);
         if (var2 != null && var3 != null && !var3.isBlank() && this.Xqedii_2b97xc6 != null) {
            String var4 = this.Xqedii_18ilr9l == null ? null : (String)this.Xqedii_18ilr9l.getSingle(var1);
            Player[] var5 = (Player[])this.Xqedii_2b97xc6.getArray(var1);
            int var6 = var5.length;

            for (int var7 = 0; var7 < var6; var7++) {
               Player var8 = var5[var7];
               if (var8 != null && var8.isOnline()) {
                  switch (this.Xqedii_3vshgam) {
                     case 0:
                     case 1:
                        var2.runAction(var8, var3, var4);
                        break;
                     case 2:
                        var2.pressKey(var8, var3);
                  }
               }
            }
         }
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return this.Xqedii_3vshgam == 2
            ? "ultimateui press key"
            : "ultimateui run action";
      }
   }
}
