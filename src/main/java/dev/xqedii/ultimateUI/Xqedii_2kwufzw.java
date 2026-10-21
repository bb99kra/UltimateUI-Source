package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class Xqedii_2kwufzw extends SimpleExpression<String> {
   public Expression<Player> Xqedii_2sifsf7;
   public int Xqedii_178b1yk;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_2sifsf7 = (Expression) var1[0];
         this.Xqedii_178b1yk = var2;
         return true;
      }
   }

   public String[] get(Event var1) {
      {
         UltimateUIAPI var2 = Xqedii_1uy3j6u.api();
         Player var3 = this.Xqedii_2sifsf7 == null ? null : (Player)this.Xqedii_2sifsf7.getSingle(var1);
         if (var2 != null && var3 != null) {
            String var4 = switch (this.Xqedii_178b1yk) {
               case 0 -> var2.getHoveredElement(var3);
               case 1 -> var2.getDraggedElement(var3);
               case 2 -> var2.getOpenGuiName(var3);
               default -> null;
            };
            String[] var5;
            if (var4 != null && !var4.isEmpty()) {
               var5 = new String[1];
               var5[0] = var4;
            } else {
               var5 = null;
            }

            return var5;
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

   public Class<? extends String> getReturnType() {
      {
         return String.class;
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return Xqedii_18jpqerdl7411(this.Xqedii_178b1yk);
      }
   }

   private static /* synthetic */ String Xqedii_18jpqerdl7411(int n) {
        return "ultimateui player text " + n;
    }
}
