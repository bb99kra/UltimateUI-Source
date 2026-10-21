package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class EffUiElementVisibility extends Effect {
   public Expression<String> Xqedii_2iq8412;
   public Expression<Player> Xqedii_2ertb3c;
   public int Xqedii_2zi86wf;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_2iq8412 = (Expression) var1[0];
         this.Xqedii_2ertb3c = (Expression) var1[1];
         this.Xqedii_2zi86wf = var2;
         return true;
      }
   }

   public void execute(Event var1) {
      {
         UltimateUIAPI var2 = UltimateUiSkriptBridge.api();
         String var3 = this.Xqedii_2iq8412 == null ? null : (String)this.Xqedii_2iq8412.getSingle(var1);
         if (var2 != null && var3 != null && !var3.isBlank() && this.Xqedii_2ertb3c != null) {
            Player[] var4 = (Player[])this.Xqedii_2ertb3c.getArray(var1);
            int var5 = var4.length;

            for (int var6 = 0; var6 < var5; var6++) {
               Player var7 = var4[var6];
               if (var7 != null && var7.isOnline()) {
                  switch (this.Xqedii_2zi86wf) {
                     case 0:
                        var2.showElement(var7, var3);
                        break;
                     case 1:
                        var2.hideElement(var7, var3);
                        break;
                     case 2:
                        var2.setElementDraggable(var7, var3, true);
                        break;
                     case 3:
                        var2.setElementDraggable(var7, var3, false);
                        break;
                     case 4:
                        var2.clickElement(var7, var3);
                  }
               }
            }
         }
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return Xqedii_12i7o833bb92(this.Xqedii_2zi86wf);
      }
   }

   private static /* synthetic */ String Xqedii_12i7o833bb92(int n) {
        return "ultimateui element control " + n;
    }
}
