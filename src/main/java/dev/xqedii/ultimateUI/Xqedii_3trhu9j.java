package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class Xqedii_3trhu9j extends SimpleExpression<Object> {
   public Expression<String> Xqedii_22fmk7q;
   public Expression<Player> Xqedii_3lhpczu;
   public int Xqedii_20muzvm;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_20muzvm = var2;
         this.Xqedii_22fmk7q = (Expression) var1[0];
         this.Xqedii_3lhpczu = (Expression) var1[1];
         return true;
      }
   }

   public Object[] get(Event var1) {
      {
         UltimateUIAPI var2 = Xqedii_1uy3j6u.api();
         String var3 = this.Xqedii_22fmk7q == null ? null : (String)this.Xqedii_22fmk7q.getSingle(var1);
         Player var4 = this.Xqedii_3lhpczu == null ? null : (Player)this.Xqedii_3lhpczu.getSingle(var1);
         if (var2 != null && var3 != null && var4 != null) {
            Object[] var10000;
            switch (this.Xqedii_20muzvm) {
               case 0:
                  String var7 = var2.getElementParent(var4, var3);
                  if (var7 == null) {
                     var10000 = null;
                  } else {
                     var10000 = new Object[1];
                     var10000[0] = var7;
                  }
                  break;
               case 1:
                  var10000 = var2.getElementChildren(var4, var3).toArray(new Object[0]);
                  break;
               case 2:
                  int var6 = var2.getElementIndex(var4, var3);
                  if (var6 < 0) {
                     var10000 = null;
                  } else {
                     var10000 = new Object[1];
                     var10000[0] = var6 + 1;
                  }
                  break;
               case 3:
                  String var5 = var2.getListContainer(var4, var3);
                  if (var5 == null) {
                     var10000 = null;
                  } else {
                     var10000 = new Object[1];
                     var10000[0] = var5;
                  }
                  break;
               default:
                  var10000 = null;
            }

            return var10000;
         } else {
            return null;
         }
      }
   }

   public boolean isSingle() {
      {
         return ((this.Xqedii_20muzvm != 1
            ? 1
            : 0) != 0);
      }
   }

   public Class<?> getReturnType() {
      {
         return this.Xqedii_20muzvm == 2 ? Number.class : String.class;
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return Xqedii_2lwdsoun71iyz(this.Xqedii_20muzvm);
      }
   }

   private static /* synthetic */ String Xqedii_2lwdsoun71iyz(int n) {
        return "ultimateui element tree " + n;
    }
}
