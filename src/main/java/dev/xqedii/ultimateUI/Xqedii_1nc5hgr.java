package dev.xqedii.ultimateUI;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;

public final class Xqedii_1nc5hgr {
   public static final Pattern Xqedii_375l6vf;
   public static final Pattern Xqedii_16fy7xn;
   public static final Pattern Xqedii_28kmq82;
   public final Map<UUID, Map<String, String>> Xqedii_318l4nb = new ConcurrentHashMap<>();
   public final Map<UUID, Map<String, String>> Xqedii_2u0o0nz = new ConcurrentHashMap<>();
   public volatile Boolean Xqedii_2119p6e;
   public volatile Method Xqedii_osks008;

   public void Xqedii_1anj7is(Player var1, Map<String, String> var2) {
      if (var1 != null) {
         ConcurrentHashMap var3 = new ConcurrentHashMap();
         if (var2 != null) {
            var3.putAll(var2);
         }

         this.Xqedii_318l4nb.put(var1.getUniqueId(), var3);
      }
   }

   public void Xqedii_1smopsn(Player var1, Map<String, String> var2) {
      if (var1 != null && var2 != null && !var2.isEmpty()) {
         Map var3 = this.Xqedii_2u0o0nz.computeIfAbsent(var1.getUniqueId(), var0 -> {
            {
               return new ConcurrentHashMap<>();
            }
         });

         for (Entry var5 : var2.entrySet()) {
            var3.putIfAbsent(
               (String)var5.getKey(),
               var5.getValue() == null ? "" : (String)var5.getValue()
            );
         }
      }
   }

   public void Xqedii_1dxyjeo(Player var1, String var2, String var3) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var4 = var2.trim();
         int var5 = !var4.startsWith("*")
               && !var4.startsWith("$")
            ? 0
            : 1;
         if (var5 != 0) {
            var4 = var4.substring(1).trim();
         }

         if (!var4.isBlank()) {
            Map var6 = this.Xqedii_2u0o0nz.get(var1.getUniqueId());
            Map var7;
            if (var5 == 0 && (var6 == null || !var6.containsKey(var4))) {
               var7 = this.Xqedii_318l4nb.computeIfAbsent(var1.getUniqueId(), var0 -> {
                  {
                     return new ConcurrentHashMap<>();
                  }
               });
            } else {
               var7 = this.Xqedii_2u0o0nz.computeIfAbsent(var1.getUniqueId(), var0 -> {
                  {
                     return new ConcurrentHashMap<>();
                  }
               });
            }

            var7.put(var4, var3 == null ? "" : var3);
         }
      }
   }

   public boolean Xqedii_1fgvpt1(Player var1, String var2) {
      if (var1 != null && var2 != null) {
         String var3 = Xqedii_1t9ph7e(var2);
         Map var4 = this.Xqedii_318l4nb.get(var1.getUniqueId());
         Map var5 = this.Xqedii_2u0o0nz.get(var1.getUniqueId());
         return (((var4 == null || !var4.containsKey(var3)) && (var5 == null || !var5.containsKey(var3))
            ? 0
            : 1) != 0);
      } else {
         return false;
      }
   }

   public String Xqedii_15lmxhq(Player var1, String var2) {
      if (var1 != null && var2 != null) {
         String var3 = Xqedii_1t9ph7e(var2);
         Map var4 = this.Xqedii_318l4nb.get(var1.getUniqueId());
         if (var4 != null && var4.containsKey(var3)) {
            return (String)var4.get(var3);
         } else {
            Map var5 = this.Xqedii_2u0o0nz.get(var1.getUniqueId());
            return var5 != null && var5.containsKey(var3)
               ? (String)var5.get(var3)
               : "";
         }
      } else {
         return "";
      }
   }

   public static String Xqedii_1t9ph7e(String var0) {
      {
         String var1 = var0.trim();
         return !var1.startsWith("*")
               && !var1.startsWith("$")
            ? var1
            : var1.substring(1).trim();
      }
   }

   public void Xqedii_z4f1nd9(UUID var1) {
      {
         if (var1 != null) {
            this.Xqedii_318l4nb.remove(var1);
            this.Xqedii_2u0o0nz.remove(var1);
         }
      }
   }

   public void Xqedii_3czb6zz(UUID var1) {
      {
         if (var1 != null) {
            this.Xqedii_318l4nb.remove(var1);
         }
      }
   }

   public String Xqedii_1kcsuri(Player var1, String var2) {
      if (var2 != null && !var2.isEmpty()) {
         String var3 = this.Xqedii_3gtcmss(var2);
         var3 = this.Xqedii_33cck0y(var1, var3);
         var3 = this.Xqedii_3ptslxz(var1, var3);
         if (var3.indexOf(37) >= 0) {
            var3 = this.Xqedii_3mj6713(var1, var3);
         }

         return var3;
      } else {
         return var2 == null ? "" : var2;
      }
   }

   public String Xqedii_3gtcmss(String var1) {
      if (!var1.contains("%%")) {
         return var1;
      } else {
         Matcher var2 = Xqedii_375l6vf.matcher(var1);
         StringBuilder var3 = new StringBuilder();

         while (var2.find()) {
            var2.appendReplacement(var3, Matcher.quoteReplacement(Xqedii_3cy3gr8bnubyz(var2.group(1))));
         }

         var2.appendTail(var3);
         return var3.toString();
      }
   }

   public String Xqedii_33cck0y(Player var1, String var2) {
      if (var1 != null
         && (var2.indexOf(37) >= 0 || var2.indexOf(123) >= 0)) {
         String var3 = var2;

         for (Map var5 : List.of(this.Xqedii_318l4nb.getOrDefault(var1.getUniqueId(), Map.of()), this.Xqedii_2u0o0nz.getOrDefault(var1.getUniqueId(), Map.of()))) {
            for (Entry var7 : (Iterable<Entry>) (var5.entrySet())) {

               String var8 = var7.getValue() == null
                  ? ""
                  : (String)var7.getValue();
               String var9 = (String)var7.getKey();
               var3 = var3.replace(Xqedii_25fhet77fvsr6(var9), var8).replace(Xqedii_2hfovvgx4634u(var9), var8).replace(Xqedii_1nhxja6p1k5dk(var9), var8);
            }
         }

         return var3;
      } else {
         return var2;
      }
   }

   public String Xqedii_3ptslxz(Player var1, String var2) {
      if (var1 != null && var2.indexOf(37) >= 0) {
         String var3 = var2.replace("%player%", var1.getName());
         var3 = var3.replace("%player_name%", var1.getName());
         var3 = var3.replace("%playername%", var1.getName());
         var3 = var3.replace("%uuid%", var1.getUniqueId().toString());
         var3 = var3.replace(
            "%player_uuid%", var1.getUniqueId().toString()
         );
         var3 = var3.replace("%world%", var1.getWorld().getName());
         return this.Xqedii_ildkt08(var1, var3);
      } else {
         return var2;
      }
   }

   public String Xqedii_ildkt08(Player var1, String var2) {
      if (var2.indexOf(37) < 0) {
         return var2;
      } else {
         Matcher var3 = Xqedii_28kmq82.matcher(var2);
         StringBuilder var4 = new StringBuilder();
         int var5 = 0;

         while (var3.find()) {
            var5 = 1;
            int var6 = Xqedii_fzj0fe5(var3.group(2));
            String var7;
            if (var6 < 0) {
               var7 = var3.group();
            } else {
               PlayerInventory var8 = var1.getInventory();
               int var9 = var6 == 41 ? var8.getHeldItemSlot() : var6;
               ItemStack var10 = var8.getItem(var9);
               int var11 = var10 != null && !var10.getType().isAir() ? var10.getAmount() : 0;
               boolean var12 = "count"
                  .equalsIgnoreCase(var3.group(1));
               var7 = var12 && var11 <= 1
                  ? ""
                  : Integer.toString(var11);
            }

            var3.appendReplacement(var4, Matcher.quoteReplacement(var7));
         }

         if (var5 == 0) {
            return var2;
         } else {
            var3.appendTail(var4);
            return var4.toString();
         }
      }
   }

   public static int Xqedii_fzj0fe5(String var0) {
      if (((-1664238085 | 1) & 1) == 0) {
         throw new IllegalStateException();
      }
      String var1 = var0 == null ? "" : var0.trim().toLowerCase(Locale.ROOT);
      switch (var1) {
         case "hand":
         case "mainhand":
         case "main_hand":
            return 41;
         case "offhand":
         case "off_hand":
            return 40;
         case "helmet":
         case "head":
            return 39;
         case "chestplate":
         case "chest":
            return 38;
         case "leggings":
         case "legs":
            return 37;
         case "boots":
         case "feet":
            return 36;
         default:
            try {
               int var5 = Integer.parseInt(var1);
               if (var5 >= 0 && var5 <= 40) {
                  return var5;
               }
               return -1;
            } catch (Throwable var6) {
               return -1;
            }
      }
   }

   public String Xqedii_3mj6713(Player var1, String var2) {
      {
         Boolean var3 = this.Xqedii_2119p6e;
         if (var3 == null) {
            Plugin var4 = Bukkit.getPluginManager()
               .getPlugin("PlaceholderAPI");
            var3 = Boolean.valueOf(
               ((var4 != null && var4.isEnabled()
                  ? 1
                  : 0) != 0)
            );
            if (var3) {
               try {
                  Class var10001 = Class.forName(
                     "me.clip.placeholderapi.PlaceholderAPI"
                  );
                  String var10002 = "setPlaceholders";
                  Class[] var10003 = new Class[2];
                  var10003[0] = Player.class;
                  var10003[1] = String.class;
                  this.Xqedii_osks008 = var10001.getMethod(var10002, var10003);
               } catch (Throwable var7) {
                  var3 = Boolean.valueOf(false);
               }
            }

            this.Xqedii_2119p6e = var3;
         }

         Method var8 = this.Xqedii_osks008;
         if (var3 && var8 != null) {
            try {
               Object[] var9 = new Object[2];
               var9[0] = var1;
               var9[1] = var2;
               Object var5 = var8.invoke(null, var9);
               return var5 == null ? var2 : var5.toString();
            } catch (Throwable var6) {
               return var2;
            }
         } else {
            return var2;
         }
      }
   }

   public static String Xqedii_2jht1ka(String var0) {
      if (var0 != null && !var0.isEmpty()) {
         String var1 = Xqedii_16fy7xn.matcher(var0).replaceAll("");
         StringBuilder var2 = new StringBuilder(var1.length());

         for (int var3 = 0; var3 < var1.length(); var3++) {
            char var4 = var1.charAt(var3);
            if (var4 == 38
               && var3 + 1 < var1.length()
               && Xqedii_2pc5uej(var1.charAt(var3 + 1))) {
               var2.append((char)167);
            } else {
               var2.append(var4);
            }
         }

         return var2.toString();
      } else {
         return "";
      }
   }

   public static boolean Xqedii_2pc5uej(char var0) {
      {
         char var1 = Character.toLowerCase(var0);
         return (((var1 < 48 || var1 > 57)
               && (var1 < 97 || var1 > 102)
               && var1 != 107
               && var1 != 108
               && var1 != 109
               && var1 != 110
               && var1 != 111
               && var1 != 114
            ? 0
            : 1) != 0);
      }
   }

   static {
      {
         Xqedii_375l6vf = Pattern.compile("%%([^%]+)%%");
         Xqedii_16fy7xn = Pattern.compile(
            "</?[a-zA-Z0-9_#:/.-]+>"
         );
         Xqedii_28kmq82 = Pattern.compile(
            "(?i)%slot_(amount|count):([a-z0-9_]+)%"
         );
      }
   }

   private static /* synthetic */ String Xqedii_3cy3gr8bnubyz(String string) {
        return "%" + string + "%";
    }

    private static /* synthetic */ String Xqedii_25fhet77fvsr6(String string) {
        return "${" + string + "}";
    }

    private static /* synthetic */ String Xqedii_2hfovvgx4634u(String string) {
        return "{" + string + "}";
    }

    private static /* synthetic */ String Xqedii_1nhxja6p1k5dk(String string) {
        return "%" + string + "%";
    }
}
