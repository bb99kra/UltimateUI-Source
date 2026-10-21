package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import dev.xqedii.ultimateUI.api.event.UltimateUIEvent;
import org.bukkit.event.Event;

public final class ExprUiEventCursorY extends SimpleExpression<Number> {

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         return true;
      }
   }

   public Number[] get(Event var1) {
      if (!(var1 instanceof UltimateUIEvent var2)) {
         return null;
      } else {
         Double[] var10000 = new Double[1];
         var10000[0] = var2.getCursorY();
         return (Number[])CollectionUtils.array(var10000);
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
         return "ultimateui event cursor y";
      }
   }
}
