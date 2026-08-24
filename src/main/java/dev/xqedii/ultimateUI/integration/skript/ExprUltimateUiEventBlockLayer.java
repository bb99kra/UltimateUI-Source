package dev.xqedii.ultimateUI.integration.skript;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import dev.xqedii.ultimateUI.api.event.UltimateUIBlockClickEvent;
import org.bukkit.event.Event;

public final class ExprUltimateUiEventBlockLayer extends SimpleExpression<Number> {
   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      return true;
   }

   protected Number[] get(Event var1) {
      return !(var1 instanceof UltimateUIBlockClickEvent var2) ? null : (Number[])CollectionUtils.array(new Double[]{var2.getBlockLayer()});
   }

   public boolean isSingle() {
      return true;
   }

   public Class<? extends Number> getReturnType() {
      return Number.class;
   }

   public String toString(Event var1, boolean var2) {
      return "ultimateui event block layer";
   }
}
