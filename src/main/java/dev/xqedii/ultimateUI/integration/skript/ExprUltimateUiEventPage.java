package dev.xqedii.ultimateUI.integration.skript;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import dev.xqedii.ultimateUI.api.event.UltimateUIBlockClickEvent;
import org.bukkit.event.Event;

public final class ExprUltimateUiEventPage extends SimpleExpression<String> {
   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      return true;
   }

   protected String[] get(Event var1) {
      return !(var1 instanceof UltimateUIBlockClickEvent var2) ? null : (String[])CollectionUtils.array(new String[]{var2.getPageName()});
   }

   public boolean isSingle() {
      return true;
   }

   public Class<? extends String> getReturnType() {
      return String.class;
   }

   public String toString(Event var1, boolean var2) {
      return "ultimateui event page";
   }
}
