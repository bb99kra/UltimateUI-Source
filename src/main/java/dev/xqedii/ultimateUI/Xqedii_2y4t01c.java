package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class Xqedii_2y4t01c extends Condition {
   public Expression<String> Xqedii_w9yhq25;
   public Expression<Player> Xqedii_3c8f0fi;
   public int Xqedii_57zvwdi;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_w9yhq25 = (Expression) var1[0];
         this.Xqedii_3c8f0fi = (Expression) var1[1];
         this.Xqedii_57zvwdi = var2 / 2;
         this.setNegated(
            ((var2 % 2 == 1
               ? 1
               : 0) != 0)
         );
         return true;
      }
   }

   public boolean check(Event var1) {
      {
         UltimateUIAPI var2 = Xqedii_1uy3j6u.api();
         String var3 = this.Xqedii_w9yhq25 == null ? null : (String)this.Xqedii_w9yhq25.getSingle(var1);
         return var2 != null && var3 != null && this.Xqedii_3c8f0fi != null ? this.Xqedii_3c8f0fi.check(var1, var3x -> {
            if (var3x != null && var3x.isOnline()) {
               return (switch (this.Xqedii_57zvwdi) {
                  case 0 -> var2.isElementVisible(var3x, var3);
                  case 1 -> var2.hasElement(var3x, var3);
                  case 2 -> var2.isElementDraggable(var3x, var3);
                  default -> false;
               });
            } else {
               return false;
            }
         }, this.isNegated()) : this.isNegated();
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return Xqedii_5aiiafvuj0wj(this.Xqedii_57zvwdi);
      }
   }

   private static /* synthetic */ String Xqedii_5aiiafvuj0wj(int n) {
        return "ultimateui element condition " + n;
    }
}
