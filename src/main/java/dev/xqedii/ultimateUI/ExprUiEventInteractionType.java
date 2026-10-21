package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import dev.xqedii.ultimateUI.api.event.UltimateUIBlockClickEvent;
import org.bukkit.event.Event;

public final class ExprUiEventInteractionType extends SimpleExpression<String> {

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         return true;
      }
   }

   public String[] get(Event var1) {
      if (var1 instanceof UltimateUIBlockClickEvent var2) {
         String[] var10000 = new String[1];
         var10000[0] = var2.getClickType().name().toLowerCase();
         return (String[])CollectionUtils.array(var10000);
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
         return "ultimateui event interaction type";
      }
   }
}
