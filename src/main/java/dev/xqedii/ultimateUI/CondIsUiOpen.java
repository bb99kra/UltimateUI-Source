package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class CondIsUiOpen extends Condition {
   public Expression<Player> Xqedii_12ixhnj;
   public Expression<String> Xqedii_2k49jsl;
   public boolean Xqedii_307e12p;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_12ixhnj = (Expression) var1[0];
         boolean var5 = this.Xqedii_307e12p = var2 >= 2;
         if (this.Xqedii_307e12p) {
            this.Xqedii_2k49jsl = (Expression) var1[1];
         }

         this.setNegated(
            ((var2 != 1 && var2 != 3
               ? 0
               : 1) != 0)
         );
         return true;
      }
   }

   public boolean check(Event var1) {
      if (this.Xqedii_12ixhnj != null && UltimateUiSkriptBridge.Xqedii_1xlslkp() != null) {
         String var2 = this.Xqedii_307e12p && this.Xqedii_2k49jsl != null ? (String)this.Xqedii_2k49jsl.getSingle(var1) : null;
         Player[] var3 = (Player[])this.Xqedii_12ixhnj.getArray(var1);
         int var4 = var3.length;

         for (int var5 = 0; var5 < var4; var5++) {
            Player var6 = var3[var5];
            if (var6 != null && var6.isOnline()) {
               int var7;
               if (this.Xqedii_307e12p) {
                  if (var2 != null && !var2.isBlank()) {
                     String var8 = UltimateUiSkriptBridge.Xqedii_1xlslkp().getGuiService() != null
                        ? UltimateUiSkriptBridge.Xqedii_1xlslkp().getGuiService().getActiveGuiName(var6)
                        : null;
                     var7 = var8 != null && var8.equalsIgnoreCase(var2.trim())
                        ? 1
                        : 0;
                  } else {
                     var7 = 0;
                  }
               } else {
                  var7 = UltimateUiSkriptBridge.Xqedii_1xlslkp().getGuiService() != null && UltimateUiSkriptBridge.Xqedii_1xlslkp().getGuiService().hasOpenSession(var6)
                     ? 1
                     : 0;
               }

               if (this.isNegated() != (var7 != 0)) {
                  return true;
               }
            }
         }

         return false;
      } else {
         return this.isNegated();
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return "ultimateui ui open condition";
      }
   }
}
