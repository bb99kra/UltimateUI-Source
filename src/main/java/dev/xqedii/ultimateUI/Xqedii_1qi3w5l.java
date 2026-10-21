package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import dev.xqedii.ultimateUI.api.event.UltimateUIInputSubmitEvent;
import org.bukkit.event.Event;

public final class Xqedii_1qi3w5l extends SimpleExpression<String> {
   public int Xqedii_qssm567;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_qssm567 = var2;
         return true;
      }
   }

   public String[] get(Event var1) {
      if (var1 instanceof UltimateUIInputSubmitEvent var2) {
         String var3 = switch (this.Xqedii_qssm567) {
            case 0 -> var2.getValue();
            case 1 -> var2.getInputId();
            case 2 -> var2.getPreviousValue();
            case 3 -> var2.getVariable();
            default -> null;
         };
         String[] var4;
         if (var3 == null) {
            var4 = null;
         } else {
            var4 = new String[1];
            var4[0] = var3;
         }

         return var4;
      } else {
         return null;
      }
   }

   public boolean isSingle() {
      {
         return true;
      }
   }

   public Class<? extends String> getReturnType() {
      {
         return String.class;
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return Xqedii_1x7gt2gutd1yn(this.Xqedii_qssm567);
      }
   }

   private static /* synthetic */ String Xqedii_1x7gt2gutd1yn(int n) {
        return "ultimateui input event value " + n;
    }
}
