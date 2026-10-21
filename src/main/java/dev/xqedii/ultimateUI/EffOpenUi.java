package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class EffOpenUi extends Effect {
   public Expression<String> Xqedii_3nnevpb;
   public Expression<Player> Xqedii_3esd7br;
   public boolean Xqedii_1k1m95i;
   public boolean Xqedii_3hn3nj0;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_3nnevpb = (Expression) var1[0];
         this.Xqedii_3esd7br = (Expression) var1[1];
         switch (var2) {
            case 1:
               this.Xqedii_1k1m95i = true;
               break;
            case 2:
               this.Xqedii_3hn3nj0 = true;
               break;
            case 3:
            case 4:
               this.Xqedii_3hn3nj0 = true;
               this.Xqedii_1k1m95i = true;
               break;
            default:
               this.Xqedii_1k1m95i = false;
               this.Xqedii_3hn3nj0 = false;
         }

         return true;
      }
   }

   public void execute(Event var1) {
      if (this.Xqedii_3nnevpb != null && this.Xqedii_3esd7br != null) {
         String var2 = (String)this.Xqedii_3nnevpb.getSingle(var1);
         if (var2 != null && !var2.isBlank()) {
            Player[] var3 = (Player[])this.Xqedii_3esd7br.getArray(var1);
            int var4 = var3.length;

            for (int var5 = 0; var5 < var4; var5++) {
               Player var6 = var3[var5];
               if (var6 != null && var6.isOnline() && UltimateUiSkriptBridge.api() != null) {
                  UltimateUiSkriptBridge.api().openGui(var6, var2, this.Xqedii_1k1m95i, this.Xqedii_3hn3nj0);
               }
            }
         }
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return "open ui effect";
      }
   }
}
