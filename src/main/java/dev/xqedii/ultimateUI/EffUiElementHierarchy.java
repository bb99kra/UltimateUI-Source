package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public final class EffUiElementHierarchy extends Effect {
   public Expression<String> Xqedii_3jsjghv;
   public Expression<String> Xqedii_33s251c;
   public Expression<String> Xqedii_20coy2e;
   public Expression<Number> Xqedii_3pkie0t;
   public Expression<Player> Xqedii_2duch9h;
   public int Xqedii_347pt65;

   public boolean init(Expression<?>[] var1, int var2, Kleenean var3, ParseResult var4) {
      {
         this.Xqedii_347pt65 = var2;
         this.Xqedii_3jsjghv = (Expression) var1[0];
         switch (var2) {
            case 1:
            case 3:
               this.Xqedii_33s251c = (Expression) var1[1];
               this.Xqedii_2duch9h = (Expression) var1[2];
               break;
            case 2:
            default:
               this.Xqedii_2duch9h = (Expression) var1[1];
               break;
            case 4:
               this.Xqedii_33s251c = (Expression) var1[1];
               this.Xqedii_20coy2e = (Expression) var1[2];
               this.Xqedii_2duch9h = (Expression) var1[3];
               break;
            case 5:
            case 6:
               this.Xqedii_20coy2e = (Expression) var1[1];
               this.Xqedii_3pkie0t = (Expression) var1[2];
               this.Xqedii_2duch9h = (Expression) var1[3];
         }

         return true;
      }
   }

   public void execute(Event var1) {
      {
         UltimateUIAPI var2 = UltimateUiSkriptBridge.api();
         String var3 = this.Xqedii_3jsjghv == null ? null : (String)this.Xqedii_3jsjghv.getSingle(var1);
         if (var2 != null && var3 != null && !var3.isBlank() && this.Xqedii_2duch9h != null) {
            String var4 = this.Xqedii_33s251c == null ? null : (String)this.Xqedii_33s251c.getSingle(var1);
            String var5 = this.Xqedii_20coy2e == null ? null : (String)this.Xqedii_20coy2e.getSingle(var1);
            Number var6 = this.Xqedii_3pkie0t == null ? null : (Number)this.Xqedii_3pkie0t.getSingle(var1);
            int var7 = var6 == null
               ? -1
               : Math.max(0, var6.intValue());
            Map var8 = this.Xqedii_347pt65 == 6 ? Xqedii_36e4obe(var3) : null;
            if (this.Xqedii_347pt65 != 6 || var8 != null) {
               Player[] var9 = (Player[])this.Xqedii_2duch9h.getArray(var1);
               int var10 = var9.length;

               for (int var11 = 0; var11 < var10; var11++) {
                  Player var12 = var9[var11];
                  if (var12 != null && var12.isOnline()) {
                     switch (this.Xqedii_347pt65) {
                        case 0:
                           var2.removeElement(var12, var3);
                           break;
                        case 1:
                           if (var4 != null) {
                              var2.moveElement(var12, var3, var4);
                           }
                           break;
                        case 2:
                           var2.moveElement(var12, var3, "");
                           break;
                        case 3:
                           if (var4 != null) {
                              var2.cloneElement(var12, var3, var4);
                           }
                           break;
                        case 4:
                           if (var4 != null && var5 != null) {
                              var2.cloneElement(var12, var3, var4, var5);
                           }
                           break;
                        case 5:
                           if (var5 != null) {
                              var2.moveElement(var12, var3, var5, var7);
                           }
                           break;
                        case 6:
                           var2.addElement(var12, var5, var8, var7);
                     }
                  }
               }
            }
         }
      }
   }

   public static Map<String, Object> Xqedii_36e4obe(String var0) {
      {
         YamlConfiguration var1 = new YamlConfiguration();

         try {
            var1.loadFromString(var0);
         } catch (InvalidConfigurationException var3) {
            if (UltimateUiSkriptBridge.Xqedii_1xlslkp() != null) {
               UltimateUiSkriptBridge.Xqedii_1xlslkp().getLogger().warning(Xqedii_16ksdjotq4qkh(var3.getMessage()));
            }

            return null;
         }

         Map var2 = Xqedii_2zwsujj(var1);
         return var2.isEmpty() ? null : var2;
      }
   }

   public static Map<String, Object> Xqedii_2zwsujj(ConfigurationSection var0) {
      {
         LinkedHashMap var1 = new LinkedHashMap();

         for (String var3 : var0.getKeys(false)) {
            var1.put(var3, Xqedii_3e3ujas(var0.get(var3)));
         }

         return var1;
      }
   }

   public static Object Xqedii_3e3ujas(Object var0) {
      if (var0 instanceof ConfigurationSection var6) {
         return Xqedii_2zwsujj(var6);
      } else if (var0 instanceof List var5) {
         ArrayList var7 = new ArrayList(var5.size());

         for (Object var9 : var5) {
            var7.add(Xqedii_3e3ujas(var9));
         }

         return var7;
      } else if (!(var0 instanceof Map var1)) {
         return var0;
      } else {
         LinkedHashMap var2 = new LinkedHashMap();

         for (Entry var4 : (Iterable<Entry>) (var1.entrySet())) {

            var2.put(String.valueOf(var4.getKey()), Xqedii_3e3ujas(var4.getValue()));
         }

         return var2;
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return Xqedii_50yr4kaa610m(this.Xqedii_347pt65);
      }
   }

   private static /* synthetic */ String Xqedii_16ksdjotq4qkh(String string) {
        return "Invalid element YAML in 'add ui element': " + string;
    }

    private static /* synthetic */ String Xqedii_50yr4kaa610m(int n) {
        return "ultimateui element tree " + n;
    }
}
