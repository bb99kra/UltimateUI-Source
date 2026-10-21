package dev.xqedii.ultimateUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public final class Xqedii_1tuv3qh {
   public String Xqedii_rr7d9qc = "";
   public String input = "";
   public String Xqedii_u0seest = "contains";
   public boolean Xqedii_1a7k3gv;
   public boolean Xqedii_4lseoco = true;
   public boolean Xqedii_2nfdm8k = true;
   public final List<String> Xqedii_2v5s1tx = new ArrayList<>();
   public final Map<Integer, Xqedii_148j5b1> Xqedii_21whwet = new TreeMap<>();

   public Xqedii_148j5b1 Xqedii_xafwbzr(int var1) {
      {
         return this.Xqedii_21whwet.computeIfAbsent(var1, var0 -> {
            {
               return new Xqedii_148j5b1();
            }
         });
      }
   }

   public boolean Xqedii_2j1j3tr(Xqedii_148j5b1 var1, String var2) {
      {
         String var3 = var2 == null ? "" : var2.trim();
         if (var3.isEmpty()) {
            return this.Xqedii_2nfdm8k;
         } else {
            String var4 = var1 == null ? "" : var1.Xqedii_3pjwd90.toString();
            if (var4.isEmpty()) {
               return false;
            } else {
               String var5 = this.Xqedii_1a7k3gv ? var4 : var4.toLowerCase(Locale.ROOT);
               String var6 = this.Xqedii_1a7k3gv ? var3 : var3.toLowerCase(Locale.ROOT);
               String var7 = this.Xqedii_u0seest;
               int var8 = -1;
               switch (var7.hashCode()) {
                  case -1295482945:
                     if (var7.equals("equals")) {
                        var8 = 2;
                     }
                     break;
                  case -892483503:
                     if (var7.equals("starts")) {
                        var8 = 0;
                     }
                     break;
                  case 3117816:
                     if (var7.equals("ends")) {
                        var8 = 1;
                     }
                     break;
                  case 108392519:
                     if (var7.equals("regex")) {
                        var8 = 3;
                     }
                     break;
                  case 113318569:
                     if (var7.equals("words")) {
                        var8 = 4;
                     }
               }
               return switch (var8) {
                  case 0 -> this.Xqedii_1ptkb60(
                     var5, var6, true, false
                  );
                  case 1 -> this.Xqedii_1ptkb60(
                     var5, var6, false, true
                  );
                  case 2 -> this.Xqedii_1ptkb60(
                     var5, var6, true, true
                  );
                  case 3 -> this.Xqedii_1frtblh(var5, var6);
                  case 4 -> this.Xqedii_wzllfrg(var5, var6);
                  default -> var5.contains(var6);
               };
            }
         }
      }
   }

   public boolean Xqedii_1ptkb60(String var1, String var2, boolean var3, boolean var4) {
      {
         String[] var5 = var1.split("\n");
         int var6 = var5.length;

         for (int var7 = 0; var7 < var6; var7++) {
            String var8 = var5[var7];
            String var9 = var8.trim();
            if (!var9.isEmpty() && (var3 && var4 ? var9.equals(var2) : (var3 ? var9.startsWith(var2) : var9.endsWith(var2)))) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean Xqedii_1frtblh(String var1, String var2) {
      {
         try {
            return Pattern.compile(
                  var2, this.Xqedii_1a7k3gv ? 0 : 2
               )
               .matcher(var1)
               .find();
         } catch (PatternSyntaxException var4) {
            return var1.contains(var2);
         }
      }
   }

   public boolean Xqedii_wzllfrg(String var1, String var2) {
      {
         String[] var3 = var2.split("\\s+");
         int var4 = var3.length;

         for (int var5 = 0; var5 < var4; var5++) {
            String var6 = var3[var5];
            if (!var6.isEmpty() && !var1.contains(var6)) {
               return false;
            }
         }

         return true;
      }
   }
}
