package dev.xqedii.ultimateUI;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class ExprUiInputValue extends SimpleExpression<String> {
   public Expression<String> Xqedii_3u9nmco;
   public Expression<Player> Xqedii_1g4eukl;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_3u9nmco = (Expression) var1[0];
         this.Xqedii_1g4eukl = (Expression) var1[1];
         return true;
      }
   }

   public String[] get(Event var1) {
      {
         UltimateUIAPI var2 = UltimateUiSkriptBridge.api();
         Player var3 = this.Xqedii_1g4eukl == null ? null : (Player)this.Xqedii_1g4eukl.getSingle(var1);
         String var4 = this.Xqedii_3u9nmco == null ? null : (String)this.Xqedii_3u9nmco.getSingle(var1);
         if (var2 != null && var3 != null && var4 != null && !var4.isBlank()) {
            String[] var10000 = new String[1];
            var10000[0] = var2.getInputValue(var3, var4);
            return var10000;
         } else {
            return null;
         }
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public Class<?>[] acceptChange(ChangeMode var1) {
      {
         Class[] var10000;
         switch (dev.xqedii.ultimateUI.Xqedii_173fyam.Xqedii_1kv4g94[var1.ordinal()]) {
            case 1:
            case 2:
            case 3:
               var10000 = new Class[1];
               var10000[0] = String.class;
               break;
            default:
               var10000 = null;
         }

         return var10000;
      }
   }

   public void change(Event var1, Object[] var2, ChangeMode var3) {
      {
         UltimateUIAPI var4 = UltimateUiSkriptBridge.api();
         Player var5 = this.Xqedii_1g4eukl == null ? null : (Player)this.Xqedii_1g4eukl.getSingle(var1);
         String var6 = this.Xqedii_3u9nmco == null ? null : (String)this.Xqedii_3u9nmco.getSingle(var1);
         if (var4 != null && var5 != null && var6 != null && !var6.isBlank()) {
            String var7 = "";
            if (var3 == ChangeMode.SET && var2 != null && var2.length > 0 && var2[0] != null) {
               var7 = String.valueOf(var2[0]);
            }

            var4.setInputValue(var5, var6, var7);
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
         return "ultimateui input value";
      }
   }
}
