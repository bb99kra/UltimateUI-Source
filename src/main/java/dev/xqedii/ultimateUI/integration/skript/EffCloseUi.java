package dev.xqedii.ultimateUI.integration.skript;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class EffCloseUi extends Effect {
   private Expression<String> uiNameExpression;
   private Expression<Player> playersExpression;
   private boolean closeSpecificUi;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      if (var1.length == 1) {
         this.playersExpression = (Expression) (Object) var1[0];
         this.closeSpecificUi = false;
      } else {
         this.uiNameExpression = (Expression) (Object) var1[0];
         this.playersExpression = (Expression) (Object) var1[1];
         this.closeSpecificUi = true;
      }

      return true;
   }

   protected void execute(Event var1) {
      if (this.playersExpression != null && UltimateUiSkriptBridge.getPlugin() != null) {
         String var2 = null;
         if (this.closeSpecificUi && this.uiNameExpression != null) {
            var2 = (String)this.uiNameExpression.getSingle(var1);
         }

         for (Player var6 : (Player[])this.playersExpression.getArray(var1)) {
            if (var6 != null && var6.isOnline()) {
               if (this.closeSpecificUi && var2 != null && !var2.isBlank()) {
                  UltimateUiSkriptBridge.getPlugin().closeUiForPlayer(var6, var2);
               } else {
                  UltimateUiSkriptBridge.getPlugin().closeUiForPlayer(var6);
               }
            }
         }
      }
   }

   public String toString(Event var1, boolean var2) {
      return "close ui effect";
   }
}
