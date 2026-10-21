package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import dev.xqedii.ultimateUI.api.event.UltimateUIEvent;
import org.bukkit.event.Event;

public final class Xqedii_jd5mxl2 extends SimpleExpression<String> {

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         return true;
      }
   }

   public String[] get(Event var1) {
      if (!(var1 instanceof UltimateUIEvent var2)) {
         return null;
      } else {
         String[] var10000 = new String[1];
         var10000[0] = var2.getPageName();
         return (String[])CollectionUtils.array(var10000);
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
         return "ultimateui event page";
      }
   }
}
