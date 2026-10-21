package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class ExprUiElementIdsOrPages extends SimpleExpression<String> {
   public Expression<Player> Xqedii_14tsbbk;
   public int Xqedii_7ay35r1;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_7ay35r1 = var2;
         if (var2 == 0) {
            this.Xqedii_14tsbbk = (Expression) var1[0];
         }

         return true;
      }
   }

   public String[] get(Event var1) {
      {
         UltimateUIAPI var2 = UltimateUiSkriptBridge.api();
         if (var2 == null) {
            return null;
         } else {
            List var3;
            if (this.Xqedii_7ay35r1 == 0) {
               Player var4 = this.Xqedii_14tsbbk == null ? null : (Player)this.Xqedii_14tsbbk.getSingle(var1);
               if (var4 == null) {
                  return null;
               }

               var3 = var2.getElementIds(var4);
            } else {
               var3 = var2.listGuis();
            }

            return (String[]) var3.toArray(new String[0]);
         }
      }
   }

   public boolean isSingle() {
      {
         return false;
      }
   }

   public Class<? extends String> getReturnType() {
      {
         return String.class;
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return this.Xqedii_7ay35r1 == 0
            ? "ultimateui element ids"
            : "ultimateui pages";
      }
   }
}
