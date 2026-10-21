package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import dev.xqedii.ultimateUI.api.event.UltimateUICursorMoveEvent;
import dev.xqedii.ultimateUI.api.event.UltimateUIDragEvent;
import dev.xqedii.ultimateUI.api.event.UltimateUIEvent;
import org.bukkit.event.Event;

public final class Xqedii_3gtkd0c extends SimpleExpression<Number> {
   public int Xqedii_355fqh9;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_355fqh9 = var2;
         return true;
      }
   }

   public Number[] get(Event var1) {
      if (var1 instanceof UltimateUIEvent var2) {
         Double var3 = switch (this.Xqedii_355fqh9) {
            case 0 -> var2.getPageX();
            case 1 -> var2.getPageY();
            case 2 -> var1 instanceof UltimateUIDragEvent var10 ? var10.getStartX() : null;
            case 3 -> var1 instanceof UltimateUIDragEvent var9 ? var9.getStartY() : null;
            case 4 -> var1 instanceof UltimateUIDragEvent var8 ? var8.getOffsetX() : null;
            case 5 -> var1 instanceof UltimateUIDragEvent var7 ? var7.getOffsetY() : null;
            case 6 -> var1 instanceof UltimateUIDragEvent var11 ? var11.getStepX() : (var1 instanceof UltimateUICursorMoveEvent var6 ? var6.getDeltaX() : null);
            case 7 -> var1 instanceof UltimateUIDragEvent var5 ? var5.getStepY() : (var1 instanceof UltimateUICursorMoveEvent var4 ? var4.getDeltaY() : null);
            default -> null;
         };
         Number[] var12;
         if (var3 == null) {
            var12 = null;
         } else {
            var12 = new Number[1];
            var12[0] = var3;
         }

         return var12;
      } else {
         return null;
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
         return Xqedii_1jyj1wpf9fj3i(this.Xqedii_355fqh9);
      }
   }

   private static /* synthetic */ String Xqedii_1jyj1wpf9fj3i(int n) {
        return "ultimateui event number " + n;
    }
}
