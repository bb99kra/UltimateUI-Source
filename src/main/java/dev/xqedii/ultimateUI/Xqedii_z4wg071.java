package dev.xqedii.ultimateUI;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.entity.Player;

public final class Xqedii_z4wg071 {

   public static Object Xqedii_1w9icqw(double var0) {
      {
         return var0;
      }
   }

   public static String Xqedii_2mlipa7(Object var0) {
      if (var0 == null) {
         return "none";
      } else if (var0 instanceof String var12) {
         return var12;
      } else if (var0 instanceof Double var11) {
         return Xqedii_1vloe0f(var11);
      } else if (var0 instanceof Number var10) {
         return Xqedii_1vloe0f(var10.doubleValue());
      } else if (var0 instanceof Boolean var9) {
         return var9
            ? "true"
            : "false";
      } else if (var0 instanceof UiElementId var8) {
         return var8.id();
      } else if (var0 instanceof Player var7) {
         return var7.getName();
      } else if (var0 instanceof List var6) {
         StringBuilder var13 = new StringBuilder("[");

         for (int var14 = 0; var14 < var6.size(); var14++) {
            if (var14 > 0) {
               var13.append(", ");
            }

            var13.append(Xqedii_2mlipa7(var6.get(var14)));
         }

         return var13.append((char)93).toString();
      } else if (var0 instanceof Map var1) {
         StringBuilder var2 = new StringBuilder("{");
         int var3 = 1;

         for (Entry var5 : (Iterable<Entry>) (var1.entrySet())) {

            if (var3 == 0) {
               var2.append(", ");
            }

            var3 = 0;
            var2.append(var5.getKey())
               .append(": ")
               .append(Xqedii_2mlipa7(var5.getValue()));
         }

         return var2.append((char)125).toString();
      } else {
         return String.valueOf(var0);
      }
   }

   public static String Xqedii_1vloe0f(double var0) {
      if (Double.isNaN(var0)) {
         return "NaN";
      } else if (Double.isInfinite(var0)) {
         return var0 > Double.longBitsToDouble(0L)
            ? "infinity"
            : "-infinity";
      } else {
         return var0 == Math.rint(var0) && Math.abs(var0) < Double.longBitsToDouble(4831355200913801216L)
            ? Long.toString((long)var0)
            : BigDecimal.valueOf(var0).setScale(6, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
      }
   }

   public static double Xqedii_3c9epia(Object var0, int var1) {
      if (var0 instanceof Number var7) {
         return var7.doubleValue();
      } else if (var0 instanceof Boolean var6) {
         return var6
            ? Double.longBitsToDouble(4607182418800017408L)
            : Double.longBitsToDouble(0L);
      } else if (var0 == null) {
         return Double.longBitsToDouble(0L);
      } else if (var0 instanceof String var2) {
         String var3 = var2.trim();
         if (var3.isEmpty()) {
            return Double.longBitsToDouble(0L);
         } else {
            try {
               return Double.parseDouble(
                  var3.replace((char)44, (char)46)
               );
            } catch (NumberFormatException var5) {
               throw new ScriptSyntaxException(Xqedii_1dah1d1cgaw0h(var2), var1);
            }
         }
      } else {
         throw new ScriptSyntaxException(Xqedii_h925o1abzntk(Xqedii_yl2qf35(var0)), var1);
      }
   }

   public static double Xqedii_3pdnyon(Object var0, double var1) {
      if (var0 instanceof Number var7) {
         return var7.doubleValue();
      } else if (var0 instanceof String var6) {
         try {
            return Double.parseDouble(
               var6.trim().replace((char)44, (char)46)
            );
         } catch (NumberFormatException var5) {
            return var1;
         }
      } else if (var0 instanceof Boolean var3) {
         return var3
            ? Double.longBitsToDouble(4607182418800017408L)
            : Double.longBitsToDouble(0L);
      } else {
         return var1;
      }
   }

   public static int Xqedii_1x518al(Object var0, int var1) {
      {
         double var2 = Xqedii_3c9epia(var0, var1);
         return (int)Math.max(
            Double.longBitsToDouble(-4476578029606273024L),
            Math.min(Double.longBitsToDouble(4746794007244308480L), Math.floor(var2))
         );
      }
   }

   public static boolean Xqedii_2od1ceu(Object var0) {
      if (var0 == null) {
         return false;
      } else if (var0 instanceof Boolean var7) {
         return var7;
      } else if (var0 instanceof Number var6) {
         double var2 = var6.doubleValue();
         return ((var2 != Double.longBitsToDouble(0L) && !Double.isNaN(var2)
            ? 1
            : 0) != 0);
      } else if (!(var0 instanceof String var1)) {
         if (var0 instanceof Collection var5) {
            return ((!var5.isEmpty() ? 1 : 0) != 0);
         } else if (var0 instanceof Map var4) {
            return ((!var4.isEmpty() ? 1 : 0) != 0);
         } else {
            return true;
         }
      } else {
         return ((!var1.isEmpty() && !var1.equalsIgnoreCase("false")
            ? 1
            : 0) != 0);
      }
   }

   public static boolean Xqedii_zxgkysn(Object var0, Object var1) {
      if (var0 == var1) {
         return true;
      } else if (var0 != null && var1 != null) {
         if (var0 instanceof Number var2 && var1 instanceof Number var3) {
            return ((var2.doubleValue() == var3.doubleValue()
               ? 1
               : 0) != 0);
         } else if (var0 instanceof UiElementId || var1 instanceof UiElementId || var0 instanceof Player || var1 instanceof Player) {
            return Xqedii_2mlipa7(var0).equals(Xqedii_2mlipa7(var1));
         } else if (var0 instanceof Number && var1 instanceof String var5) {
            return Xqedii_2mlipa7(var0).equals(var5);
         } else {
            return var0 instanceof String var4 && var1 instanceof Number ? var4.equals(Xqedii_2mlipa7(var1)) : var0.equals(var1);
         }
      } else {
         return false;
      }
   }

   public static boolean Xqedii_2lutf1l(Object var0, Object var1) {
      if (var0 instanceof String var2 && var1 instanceof String var3) {
         return var2.equalsIgnoreCase(var3);
      } else {
         return !(var0 instanceof String) && !(var1 instanceof String)
            ? Xqedii_zxgkysn(var0, var1)
            : Xqedii_2mlipa7(var0).equalsIgnoreCase(Xqedii_2mlipa7(var1));
      }
   }

   public static int Xqedii_eqxwxh9(Object var0, Object var1, int var2) {
      {
         return var0 instanceof String var3 && var1 instanceof String var4
            ? var3.compareToIgnoreCase(var4)
            : Double.compare(Xqedii_3c9epia(var0, var2), Xqedii_3c9epia(var1, var2));
      }
   }

   public static Object Xqedii_hbc7zbp(Object var0, Object var1, int var2) {
      if (var0 instanceof String || var1 instanceof String || var0 instanceof UiElementId || var1 instanceof UiElementId) {
         return Xqedii_2v540j9kktkzw(Xqedii_2mlipa7(var0), Xqedii_2mlipa7(var1));
      } else if (var0 instanceof List var3) {
         ArrayList var4 = new ArrayList(var3);
         if (var1 instanceof List var5) {
            var4.addAll(var5);
         } else {
            var4.add(var1);
         }

         return var4;
      } else {
         return Xqedii_3c9epia(var0, var2) + Xqedii_3c9epia(var1, var2);
      }
   }

   public static boolean Xqedii_1lmv6tt(Object var0, Object var1) {
      if (var0 == null) {
         return false;
      } else if (var0 instanceof Collection) {
         for (Object var4 : (Collection)var0) {
            if (Xqedii_zxgkysn(var4, var1)) {
               return true;
            }
         }

         return false;
      } else {
         return var0 instanceof Map var2
            ? var2.containsKey(Xqedii_2mlipa7(var1))
            : Xqedii_2mlipa7(var0).toLowerCase(Locale.ROOT).contains(Xqedii_2mlipa7(var1).toLowerCase(Locale.ROOT));
      }
   }

   public static String Xqedii_yl2qf35(Object var0) {
      if (var0 == null) {
         return "none";
      } else {
         return var0 instanceof String var1 ? Xqedii_2vns54m9uglrg(var1) : Xqedii_3ck2y1a5g43qo(Xqedii_28xo6uq(var0), Xqedii_2mlipa7(var0));
      }
   }

   public static String Xqedii_28xo6uq(Object var0) {
      if (var0 == null) {
         return "none";
      } else if (var0 instanceof Number) {
         return "number";
      } else if (var0 instanceof String) {
         return "text";
      } else if (var0 instanceof Boolean) {
         return "boolean";
      } else if (var0 instanceof List) {
         return "list";
      } else if (var0 instanceof Map) {
         return "object";
      } else if (var0 instanceof UiElementId) {
         return "element";
      } else if (var0 instanceof Player) {
         return "player";
      } else {
         return var0 instanceof ScriptEventContext
            ? "event"
            : "value";
      }
   }

   public static Object Xqedii_3ujolw5(Object var0) {
      if (var0 instanceof Number var6) {
         return var6.doubleValue();
      } else if (var0 instanceof String || var0 instanceof Boolean || var0 == null) {
         return var0;
      } else if (var0 instanceof List var5) {
         ArrayList var7 = new ArrayList(var5.size());

         for (Object var9 : var5) {
            var7.add(Xqedii_3ujolw5(var9));
         }

         return var7;
      } else if (!(var0 instanceof Map var1)) {
         return Xqedii_2mlipa7(var0);
      } else {
         LinkedHashMap var2 = new LinkedHashMap();

         for (Entry var4 : (Iterable<Entry>) (var1.entrySet())) {

            var2.put(String.valueOf(var4.getKey()), Xqedii_3ujolw5(var4.getValue()));
         }

         return var2;
      }
   }

   private static /* synthetic */ String Xqedii_1dah1d1cgaw0h(String string) {
        return "\"" + string + "\" is not a number";
    }

    private static /* synthetic */ String Xqedii_h925o1abzntk(String string) {
        return string + " is not a number";
    }

    private static /* synthetic */ String Xqedii_2v540j9kktkzw(String string, String string2) {
        return string + string2;
    }

    private static /* synthetic */ String Xqedii_2vns54m9uglrg(String string) {
        return "the text \"" + string + "\"";
    }

    private static /* synthetic */ String Xqedii_3ck2y1a5g43qo(String string, String string2) {
        return string + " " + string2;
    }
}
