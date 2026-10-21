package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class EffSetUiElement extends Effect {
   public Expression<String> Xqedii_35isgox;
   public Expression<String> Xqedii_10anufm;
   public Expression<String> Xqedii_2rfh5ra;
   public Expression<Number> Xqedii_2zs91zt;
   public Expression<Number> Xqedii_3a8jg9j;
   public Expression<Boolean> Xqedii_1esq729;
   public Expression<Player> Xqedii_1sf1bv2;
   public int Xqedii_2fzlmqs;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_2fzlmqs = var2;
         this.Xqedii_35isgox = (Expression) var1[0];
         switch (var2) {
            case 0:
            case 1:
            case 4:
               this.Xqedii_10anufm = (Expression) var1[1];
               this.Xqedii_1sf1bv2 = (Expression) var1[2];
               break;
            case 2:
            case 3:
               this.Xqedii_2zs91zt = (Expression) var1[1];
               this.Xqedii_3a8jg9j = (Expression) var1[2];
               this.Xqedii_1sf1bv2 = (Expression) var1[3];
               break;
            case 5:
               this.Xqedii_2rfh5ra = (Expression) var1[1];
               this.Xqedii_10anufm = (Expression) var1[2];
               this.Xqedii_1sf1bv2 = (Expression) var1[3];
               break;
            case 6:
               this.Xqedii_2rfh5ra = (Expression) var1[1];
               this.Xqedii_2zs91zt = (Expression) var1[2];
               this.Xqedii_1sf1bv2 = (Expression) var1[3];
               break;
            case 7:
               this.Xqedii_2rfh5ra = (Expression) var1[1];
               this.Xqedii_1esq729 = (Expression) var1[2];
               this.Xqedii_1sf1bv2 = (Expression) var1[3];
               break;
            default:
               return false;
         }

         return true;
      }
   }

   public void execute(Event var1) {
      if (this.Xqedii_35isgox != null && this.Xqedii_1sf1bv2 != null) {
         UltimateUIAPI var2 = UltimateUiSkriptBridge.api();
         if (var2 != null) {
            String var3 = (String)this.Xqedii_35isgox.getSingle(var1);
            if (var3 != null && !var3.isBlank()) {
               String var4 = this.Xqedii_10anufm == null ? null : (String)this.Xqedii_10anufm.getSingle(var1);
               String var5 = this.Xqedii_2rfh5ra == null ? null : (String)this.Xqedii_2rfh5ra.getSingle(var1);
               Number var6 = this.Xqedii_2zs91zt == null ? (Number)null : (Number)this.Xqedii_2zs91zt.getSingle(var1);
               Number var7 = this.Xqedii_3a8jg9j == null ? (Number)null : (Number)this.Xqedii_3a8jg9j.getSingle(var1);
               Boolean var8 = this.Xqedii_1esq729 == null ? null : (Boolean)this.Xqedii_1esq729.getSingle(var1);
               Player[] var9 = (Player[])this.Xqedii_1sf1bv2.getArray(var1);
               int var10 = var9.length;

               for (int var11 = 0; var11 < var10; var11++) {
                  Player var12 = var9[var11];
                  if (var12 != null && var12.isOnline()) {
                     switch (this.Xqedii_2fzlmqs) {
                        case 0:
                           var2.setElementText(var12, var3, var4);
                           break;
                        case 1:
                           var2.setElementColor(var12, var3, var4);
                           break;
                        case 2:
                           if (var6 != null && var7 != null) {
                              var2.setElementPosition(var12, var3, var6.doubleValue(), var7.doubleValue());
                           }
                           break;
                        case 3:
                           if (var6 != null && var7 != null) {
                              var2.setElementScale(var12, var3, var6.doubleValue(), var7.doubleValue());
                           }
                           break;
                        case 4:
                           var2.setElementItem(var12, var3, var4);
                           break;
                        case 5:
                           if (var5 != null && !var5.isBlank()) {
                              var2.setElementValue(var12, var3, var5, var4);
                           }
                           break;
                        case 6:
                           if (var5 != null && !var5.isBlank() && var6 != null) {
                              var2.setElementValue(var12, var3, var5, var6.doubleValue());
                           }
                           break;
                        case 7:
                           if (var5 != null && !var5.isBlank() && var8 != null) {
                              var2.setElementValue(var12, var3, var5, var8);
                           }
                           break;
                        default:
                           return;
                     }
                  }
               }
            }
         }
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return "set ui element effect";
      }
   }
}
