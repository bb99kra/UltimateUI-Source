package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class Xqedii_3scfwds extends SimpleExpression<Number> {
   public Expression<Player> Xqedii_sqfwmf2;
   public int Xqedii_1cwed3e;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_sqfwmf2 = (Expression) var1[0];
         this.Xqedii_1cwed3e = var2;
         return true;
      }
   }

   public Number[] get(Event var1) {
      {
         UltimateUIAPI var2 = Xqedii_1uy3j6u.api();
         Player var3 = this.Xqedii_sqfwmf2 == null ? null : (Player)this.Xqedii_sqfwmf2.getSingle(var1);
         if (var2 != null && var3 != null && var2.isGuiOpen(var3)) {
            double var4 = switch (this.Xqedii_1cwed3e) {
               case 0 -> var2.getCursorX(var3);
               case 1 -> var2.getCursorY(var3);
               case 2 -> var2.getCursorPageX(var3);
               case 3 -> var2.getCursorPageY(var3);
               default -> Double.longBitsToDouble(9221120237041090560L);
            };
            Number[] var6;
            if (Double.isFinite(var4)) {
               var6 = new Number[1];
               var6[0] = var4;
            } else {
               var6 = null;
            }

            return var6;
         } else {
            return null;
         }
      }
   }

   public boolean isSingle() {
      {
         return true;
      }
   }

   public Class<? extends Number> getReturnType() {
      {
         return Number.class;
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return Xqedii_si37cfnvhzcu(this.Xqedii_1cwed3e);
      }
   }

   private static /* synthetic */ String Xqedii_si37cfnvhzcu(int n) {
        return "ultimateui player cursor " + n;
    }
}
