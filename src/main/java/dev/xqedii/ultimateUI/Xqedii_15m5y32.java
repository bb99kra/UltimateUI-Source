package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import java.util.List;
import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class Xqedii_15m5y32 extends SimpleExpression<Object> {
   public Expression<String> Xqedii_3mgpmev;
   public Expression<String> Xqedii_13yuvza;
   public Expression<Player> Xqedii_2yqihnm;
   public int Xqedii_7d1kq2t;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_7d1kq2t = var2;
         this.Xqedii_3mgpmev = (Expression) var1[0];
         if (var2 == 0) {
            this.Xqedii_13yuvza = (Expression) var1[1];
            this.Xqedii_2yqihnm = (Expression) var1[2];
         } else {
            this.Xqedii_2yqihnm = (Expression) var1[1];
         }

         return true;
      }
   }

   public Object[] get(Event var1) {
      {
         UltimateUIAPI var2 = Xqedii_1uy3j6u.api();
         String var3 = this.Xqedii_3mgpmev == null ? null : (String)this.Xqedii_3mgpmev.getSingle(var1);
         Player var4 = this.Xqedii_2yqihnm == null ? null : (Player)this.Xqedii_2yqihnm.getSingle(var1);
         if (var2 != null && var3 != null && var4 != null) {
            Object var5 = switch (this.Xqedii_7d1kq2t) {
               case 0 -> {
                  String var6 = this.Xqedii_13yuvza == null ? null : (String)this.Xqedii_13yuvza.getSingle(var1);
                  yield var6 == null ? null : var2.getElementValue(var4, var3, var6);
               }
               case 1 -> var2.getElementText(var4, var3);
               case 2 -> Xqedii_2d7x8df(var2.getElementPosition(var4, var3), 0);
               case 3 -> Xqedii_2d7x8df(var2.getElementPosition(var4, var3), 1);
               case 4 -> Xqedii_2d7x8df(var2.getElementSize(var4, var3), 0);
               case 5 -> Xqedii_2d7x8df(var2.getElementSize(var4, var3), 1);
               default -> null;
            };
            if (var5 != null && !(var5 instanceof Map) && !(var5 instanceof List)) {
               Object[] var8 = new Object[1];
               var8[0] = var5;
               return var8;
            } else {
               Object[] var7;
               if (var5 == null) {
                  var7 = null;
               } else {
                  var7 = new Object[1];
                  var7[0] = String.valueOf(var5);
               }

               return var7;
            }
         } else {
            return null;
         }
      }
   }

   public static Object Xqedii_2d7x8df(double[] var0, int var1) {
      {
         return var0 != null && var0.length > var1 ? var0[var1] : null;
      }
   }

   public boolean isSingle() {
      {
         return true;
      }
   }

   public Class<?> getReturnType() {
      {
         return this.Xqedii_7d1kq2t == 1
            ? String.class
            : (this.Xqedii_7d1kq2t >= 2 ? Number.class : Object.class);
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return Xqedii_370wm483a2umo(this.Xqedii_7d1kq2t);
      }
   }

   private static /* synthetic */ String Xqedii_370wm483a2umo(int n) {
        return "ultimateui element value " + n;
    }
}
