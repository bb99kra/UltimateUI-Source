package dev.xqedii.ultimateUI.integration.skript;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class CondUltimateUiOpen extends Condition {
   private Expression<Player> playersExpression;
   private Expression<String> uiNameExpression;
   private boolean checkSpecific;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      this.playersExpression = (Expression) (Object) var1[0];
      boolean var5 = this.checkSpecific = var2 >= 2;
      if (this.checkSpecific) {
         this.uiNameExpression = (Expression) (Object) var1[1];
      }

      this.setNegated(var2 == 1 || var2 == 3);
      return true;
   }

   public boolean check(Event var1) {
      if (this.playersExpression != null && UltimateUiSkriptBridge.getPlugin() != null) {
         String var2 = this.checkSpecific && this.uiNameExpression != null ? (String)this.uiNameExpression.getSingle(var1) : null;

         for (Player var6 : (Player[])this.playersExpression.getArray(var1)) {
            if (var6 != null && var6.isOnline()) {
               boolean var7;
               if (this.checkSpecific) {
                  if (var2 != null && !var2.isBlank()) {
                     String var8 = UltimateUiSkriptBridge.getPlugin().getGuiService() != null
                        ? UltimateUiSkriptBridge.getPlugin().getGuiService().getActiveGuiName(var6)
                        : null;
                     var7 = var8 != null && var8.equalsIgnoreCase(var2.trim());
                  } else {
                     var7 = false;
                  }
               } else {
                  var7 = UltimateUiSkriptBridge.getPlugin().getGuiService() != null && UltimateUiSkriptBridge.getPlugin().getGuiService().hasOpenSession(var6);
               }

               if (this.isNegated() != var7) {
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
      return "ultimateui ui open condition";
   }
}
