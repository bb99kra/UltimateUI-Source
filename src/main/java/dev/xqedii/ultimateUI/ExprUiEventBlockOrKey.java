package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import dev.xqedii.ultimateUI.api.event.UltimateUIDragEvent;
import dev.xqedii.ultimateUI.api.event.UltimateUIHoverEvent;
import dev.xqedii.ultimateUI.api.event.UltimateUIKeyEvent;
import java.util.Locale;
import org.bukkit.event.Event;

public final class ExprUiEventBlockOrKey extends SimpleExpression<String> {
   public int Xqedii_2nklj0r;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_2nklj0r = var2;
         return true;
      }
   }

   public String[] get(Event var1) {
      {
         String var2 = switch (this.Xqedii_2nklj0r) {
            case 0 -> var1 instanceof UltimateUIDragEvent var9 ? var9.getDropBlockId() : null;
            case 1 -> var1 instanceof UltimateUIHoverEvent var8 ? var8.getPreviousBlockId() : null;
            case 2 -> var1 instanceof UltimateUIHoverEvent var7 ? var7.getNextBlockId() : null;
            case 3 -> var1 instanceof UltimateUIKeyEvent var6 ? var6.getKey() : null;
            case 4 -> var1 instanceof UltimateUIDragEvent var5 ? var5.getPhase().name().toLowerCase(Locale.ROOT) : null;
            case 5 -> var1 instanceof UltimateUIHoverEvent var4 ? var4.getAction().name().toLowerCase(Locale.ROOT) : null;
            case 6 -> var1 instanceof UltimateUIDragEvent var3 ? var3.getDropContainerId() : null;
            default -> null;
         };
         String[] var10;
         if (var2 == null) {
            var10 = null;
         } else {
            var10 = new String[1];
            var10[0] = var2;
         }

         return var10;
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
         return Xqedii_1773b8u0twvl0(this.Xqedii_2nklj0r);
      }
   }

   private static /* synthetic */ String Xqedii_1773b8u0twvl0(int n) {
        return "ultimateui event text " + n;
    }
}
