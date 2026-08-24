package dev.xqedii.ultimateUI.integration.skript;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.service.gui.GuiService;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class EffSetUiElement extends Effect {
   private Expression<String> elementIdExpression;
   private Expression<String> stringValueExpression;
   private Expression<String> pathExpression;
   private Expression<Number> firstNumberExpression;
   private Expression<Number> secondNumberExpression;
   private Expression<Boolean> booleanValueExpression;
   private Expression<Player> playersExpression;
   private int matchedPattern;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      this.matchedPattern = var2;
      this.elementIdExpression = (Expression) (Object) var1[0];
      switch (var2) {
         case 0:
         case 1:
         case 4:
            this.stringValueExpression = (Expression) (Object) var1[1];
            this.playersExpression = (Expression) (Object) var1[2];
            break;
         case 2:
         case 3:
            this.firstNumberExpression = (Expression) (Object) var1[1];
            this.secondNumberExpression = (Expression) (Object) var1[2];
            this.playersExpression = (Expression) (Object) var1[3];
            break;
         case 5:
            this.pathExpression = (Expression) (Object) var1[1];
            this.stringValueExpression = (Expression) (Object) var1[2];
            this.playersExpression = (Expression) (Object) var1[3];
            break;
         case 6:
            this.pathExpression = (Expression) (Object) var1[1];
            this.firstNumberExpression = (Expression) (Object) var1[2];
            this.playersExpression = (Expression) (Object) var1[3];
            break;
         case 7:
            this.pathExpression = (Expression) (Object) var1[1];
            this.booleanValueExpression = (Expression) (Object) var1[2];
            this.playersExpression = (Expression) (Object) var1[3];
            break;
         default:
            return false;
      }

      return true;
   }

   protected void execute(Event var1) {
      if (this.elementIdExpression != null && this.playersExpression != null) {
         UltimateUI var2 = UltimateUiSkriptBridge.getPlugin();
         if (var2 != null) {
            GuiService var3 = var2.getGuiService();
            if (var3 != null) {
               String var4 = (String)this.elementIdExpression.getSingle(var1);
               if (var4 != null && !var4.isBlank()) {
                  String var5 = this.stringValueExpression == null ? null : (String)this.stringValueExpression.getSingle(var1);
                  String var6 = this.pathExpression == null ? null : (String)this.pathExpression.getSingle(var1);
                  Number var7 = this.firstNumberExpression == null ? (Number)null : (Number)this.firstNumberExpression.getSingle(var1);
                  Number var8 = this.secondNumberExpression == null ? (Number)null : (Number)this.secondNumberExpression.getSingle(var1);
                  Boolean var9 = this.booleanValueExpression == null ? null : (Boolean)this.booleanValueExpression.getSingle(var1);

                  for (Player var13 : (Player[])this.playersExpression.getArray(var1)) {
                     if (var13 != null && var13.isOnline()) {
                        switch (this.matchedPattern) {
                           case 0:
                              var3.setOpenUiElementText(var13, var4, var5);
                              break;
                           case 1:
                              var3.setOpenUiElementColor(var13, var4, var5);
                              break;
                           case 2:
                              if (var7 != null && var8 != null) {
                                 var3.setOpenUiElementPosition(var13, var4, var7.doubleValue(), var8.doubleValue());
                              }
                              break;
                           case 3:
                              if (var7 != null && var8 != null) {
                                 var3.setOpenUiElementScale(var13, var4, var7.doubleValue(), var8.doubleValue());
                              }
                              break;
                           case 4:
                              var3.setOpenUiElementItem(var13, var4, var5);
                              break;
                           case 5:
                              if (var6 != null && !var6.isBlank()) {
                                 var3.setOpenUiElementValue(var13, var4, var6, var5);
                              }
                              break;
                           case 6:
                              if (var6 != null && !var6.isBlank() && var7 != null) {
                                 var3.setOpenUiElementValue(var13, var4, var6, Double.valueOf(var7.doubleValue()));
                              }
                              break;
                           case 7:
                              if (var6 != null && !var6.isBlank() && var9 != null) {
                                 var3.setOpenUiElementValue(var13, var4, var6, var9);
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
   }

   public String toString(Event var1, boolean var2) {
      return "set ui element effect";
   }
}
