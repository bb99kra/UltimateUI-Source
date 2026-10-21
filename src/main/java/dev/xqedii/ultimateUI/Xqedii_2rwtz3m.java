package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class Xqedii_2rwtz3m extends Condition {
   public Expression<Player> Xqedii_julas6i;
   public Expression<String> Xqedii_2d43alw;
   public int Xqedii_30waat9;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_julas6i = (Expression) var1[0];
         this.Xqedii_30waat9 = var2 / 2;
         if (var1.length > 1) {
            this.Xqedii_2d43alw = (Expression) var1[1];
         }

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
         if (var2 != null && this.Xqedii_julas6i != null) {
            String var3 = this.Xqedii_2d43alw == null ? null : (String)this.Xqedii_2d43alw.getSingle(var1);
            return this.Xqedii_julas6i
               .check(
                  var1,
                  var3x -> {
                     if (var3x != null && var3x.isOnline()) {
                        return switch (this.Xqedii_30waat9) {
                            case 0 -> var3 != null && var2.isHovering(var3x, var3);
                            case 1 -> var2.getHoveredElement(var3x) != null;
                            case 2 -> var3 != null && var2.isDragging(var3x) && var3.equals(var2.getDraggedElement(var3x));
                            case 3 -> var2.isDragging(var3x);
                            case 4 -> var2.isLeftMouseHeld(var3x);
                            default -> false;
                         };
                     } else {
                        return false;
                     }
                  },
                  this.isNegated()
               );
         } else {
            return this.isNegated();
         }
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return Xqedii_2j9l3epve9r77(this.Xqedii_30waat9);
      }
   }

   private static /* synthetic */ String Xqedii_2j9l3epve9r77(int n) {
        return "ultimateui pointer condition " + n;
    }
}
