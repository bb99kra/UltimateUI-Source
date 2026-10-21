package dev.xqedii.ultimateUI;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public final class Xqedii_29ge1r6 {

   public static Map<String, Object> Xqedii_15tec8i() {
      {
         return new LinkedHashMap<>();
      }
   }

   public static List<Object> Xqedii_2fhwu22(Object... var0) {
      {
         ArrayList var1 = new ArrayList();
         if (var0 != null) {
            Object[] var2 = var0;
            int var3 = var0.length;

            for (int var4 = 0; var4 < var3; var4++) {
               Object var5 = var2[var4];
               var1.add(var5);
            }
         }

         return var1;
      }
   }

   public static String Xqedii_zaf20q7(Object var0) {
      {
         StringBuilder var1 = new StringBuilder(4096);
         Xqedii_8xm44ki(var0, var1, 0);
         var1.append((char)10);
         return var1.toString();
      }
   }

   public static void Xqedii_8xm44ki(Object var0, StringBuilder var1, int var2) {
      if (var0 == null) {
         var1.append("null");
      } else if (var0 instanceof String var7) {
         Xqedii_24y5shs(var7, var1);
      } else if (var0 instanceof Boolean var6) {
         var1.append(
            var6
               ? "true"
               : "false"
         );
      } else if (var0 instanceof Number var5) {
         Xqedii_6gqidxn(var5, var1);
      } else if (var0 instanceof Map var4) {
         writeObject(var4, var1, var2);
      } else if (var0 instanceof List var3) {
         Xqedii_3eacz4p(var3, var1, var2);
      } else {
         Xqedii_24y5shs(String.valueOf(var0), var1);
      }
   }

   public static void Xqedii_6gqidxn(Number var0, StringBuilder var1) {
      {
         double var2 = var0.doubleValue();
         if (var0 instanceof Integer || var0 instanceof Long || var0 instanceof Short) {
            var1.append(var0.longValue());
         } else if (var2 == Math.rint(var2) && !Double.isInfinite(var2)) {
            var1.append((long)var2).append(".0");
         } else {
            Locale var10001 = Locale.ROOT;
            String var10002 = "%.4f";
            Object[] var10003 = new Object[1];
            var10003[0] = var2;
            var1.append(String.format(var10001, var10002, var10003));
         }
      }
   }

   public static void writeObject(Map<?, ?> var0, StringBuilder var1, int var2) {
      if (var0.isEmpty()) {
         var1.append("{}");
      } else {
         var1.append("{\n");
         int var3 = var0.size();

         for (Entry var5 : var0.entrySet()) {
            Xqedii_2zbtpko(var1, var2 + 1);
            Xqedii_24y5shs(String.valueOf(var5.getKey()), var1);
            var1.append(": ");
            Xqedii_8xm44ki(var5.getValue(), var1, var2 + 1);
            if (--var3 > 0) {
               var1.append((char)44);
            }

            var1.append((char)10);
         }

         Xqedii_2zbtpko(var1, var2);
         var1.append((char)125);
      }
   }

   public static void Xqedii_3eacz4p(List<?> var0, StringBuilder var1, int var2) {
      if (var0.isEmpty()) {
         var1.append("[]");
      } else {
         int var3 = 1;

         for (Object var5 : var0) {
            if (var5 instanceof Map || var5 instanceof List) {
               var3 = 0;
               break;
            }
         }

         if (var3 != 0) {
            var1.append("[ ");

            for (int var7 = 0; var7 < var0.size(); var7++) {
               if (var7 > 0) {
                  var1.append(", ");
               }

               Xqedii_8xm44ki(var0.get(var7), var1, var2);
            }

            var1.append(" ]");
         } else {
            var1.append("[\n");

            for (int var6 = 0; var6 < var0.size(); var6++) {
               Xqedii_2zbtpko(var1, var2 + 1);
               Xqedii_8xm44ki(var0.get(var6), var1, var2 + 1);
               if (var6 < var0.size() - 1) {
                  var1.append((char)44);
               }

               var1.append((char)10);
            }

            Xqedii_2zbtpko(var1, var2);
            var1.append((char)93);
         }
      }
   }

   public static void Xqedii_2zbtpko(StringBuilder var0, int var1) {
      {
         var0.append(
            "  "
               .repeat(Math.max(0, var1))
         );
      }
   }

   public static void Xqedii_24y5shs(String var0, StringBuilder var1) {
      {
         var1.append((char)34);

         for (int var2 = 0; var2 < var0.length(); var2++) {
            char var3 = var0.charAt(var2);
            switch (var3) {
               case '\t':
                  var1.append("\\t");
                  break;
               case '\n':
                  var1.append("\\n");
                  break;
               case '\r':
                  var1.append("\\r");
                  break;
               case '"':
                  var1.append("\\\"");
                  break;
               case '\\':
                  var1.append("\\\\");
                  break;
               default:
                  if (var3 >= 32 && var3 <= 126) {
                     var1.append(var3);
                  } else {
                     String var10001 = "\\u%04x";
                     Object[] var10002 = new Object[1];
                     var10002[0] = Integer.valueOf(var3);
                     var1.append(String.format(var10001, var10002));
                  }
            }
         }

         var1.append((char)34);
      }
   }
}
