package dev.xqedii.ultimateUI.integration.skript;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class EffOpenUi extends Effect {
   private Expression<String> uiNameExpression;
   private Expression<Player> playersExpression;
   private boolean hudMode;
   private boolean autoClose;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      this.uiNameExpression = (Expression) (Object) var1[0];
      this.playersExpression = (Expression) (Object) var1[1];
      switch (var2) {
         case 1:
            this.hudMode = true;
            break;
         case 2:
            this.autoClose = true;
            break;
         case 3:
         case 4:
            this.autoClose = true;
            this.hudMode = true;
            break;
         default:
            this.hudMode = false;
            this.autoClose = false;
      }

      return true;
   }

   protected void execute(Event var1) {
      if (this.uiNameExpression != null && this.playersExpression != null) {
         String var2 = (String)this.uiNameExpression.getSingle(var1);
         if (var2 != null && !var2.isBlank()) {
            for (Player var6 : (Player[])this.playersExpression.getArray(var1)) {
               if (var6 != null && var6.isOnline() && UltimateUiSkriptBridge.getPlugin() != null) {
                  UltimateUiSkriptBridge.getPlugin().openUiForPlayer(var6, var2, this.hudMode, this.autoClose);
               }
            }
         }
      }
   }

   public String toString(Event var1, boolean var2) {
      return "open ui effect";
   }
}
