package dev.xqedii.ultimateUI;

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class Xqedii_2olys1t extends Xqedii_j7sqzdx {

   public Xqedii_2olys1t(File var1) {
      super(var1);
   }

   public static double Xqedii_1vomtf5(double var0) {
      {
         return Math.round(var0 * Double.longBitsToDouble(4636737291354636288L))
            / Double.longBitsToDouble(4636737291354636288L);
      }
   }

   public static String Xqedii_2164tae(double var0) {
      {
         Locale var10000 = Locale.ROOT;
         String var10001 = "%.1f";
         Object[] var10002 = new Object[1];
         var10002[0] = var0;
         return String.format(var10000, var10001, var10002);
      }
   }

   public static String Xqedii_28xaqz0(String var0) {
      {
         return var0 == null
            ? ""
            : var0.replace(
               "\u00a7",
               "\\u00a7"
            );
      }
   }

   public double Xqedii_1qnyo4j(Xqedii_2kb1ns6 var1, Xqedii_v7p1hqx var2) {
      {
         double var3 = var2.Xqedii_wnnmnvv > Double.longBitsToDouble(0L)
            ? var2.Xqedii_wnnmnvv
            : var2.Xqedii_zgu3xeu;
         double var5 = var3
            * (this.Xqedii_rnovv49 / Math.max(Double.longBitsToDouble(4607182418800017408L), var1.Xqedii_12g62h3))
            / Double.longBitsToDouble(4624070917402656768L);
         return Math.max(Double.longBitsToDouble(4587366580439587226L), var5 * this.Xqedii_3b5g09f);
      }
   }

   public static String Xqedii_rejug23(double var0) {
      {
         Locale var10000 = Locale.ROOT;
         String var10001 = "%.4f%%";
         Object[] var10002 = new Object[1];
         var10002[0] = var0;
         return String.format(var10000, var10001, var10002);
      }
   }

   public static String Xqedii_2jjyraz(double var0, String var2) {
      {
         Locale var10000 = Locale.ROOT;
         String var10001 = "%.4f%%%s";
         Object[] var10002 = new Object[2];
         var10002[0] = var0;
         var10002[1] = var2;
         return String.format(var10000, var10001, var10002);
      }
   }

   public static List<Object> Xqedii_q9nfioh(String var0) {
      {
         int var1 = Integer.parseInt(
            var0.substring(0, 2),
            16
         );
         int var2 = Integer.parseInt(
            var0.substring(2, 4),
            16
         );
         int var3 = Integer.parseInt(
            var0.substring(4, 6),
            16
         );
         Object[] var10000 = new Object[3];
         var10000[0] = var1
            / Double.longBitsToDouble(4643176031446892544L);
         var10000[1] = var2
            / Double.longBitsToDouble(4643176031446892544L);
         var10000[2] = var3
            / Double.longBitsToDouble(4643176031446892544L);
         return Xqedii_29ge1r6.Xqedii_2fhwu22(var10000);
      }
   }

   public static String Xqedii_2d62dgj(String var0) {
      {
         String var1 = var0 == null ? "" : var0.toLowerCase(Locale.ROOT);
         int var2 = -1;
         switch (var1.hashCode()) {
            case -1364013995:
               if (var1.equals("center")) {
                  var2 = 0;
               }
               break;
            case -1364013605:
               if (var1.equals("centre")) {
                  var2 = 1;
               }
               break;
            case -1074341483:
               if (var1.equals("middle")) {
                  var2 = 2;
               }
               break;
            case 108511772:
               if (var1.equals("right")) {
                  var2 = 3;
               }
         }
         return switch (var2) {
            case 0, 1, 2 -> "center";
            case 3 -> "right";
            default -> "left";
         };
      }
   }

   public static String Xqedii_1yvyns7(String var0) {
      {
         String var1 = var0 == null ? "" : var0.toLowerCase(Locale.ROOT);
         int var2 = -1;
         switch (var1.hashCode()) {
            case -898533970:
               if (var1.equals("smooth")) {
                  var2 = 0;
               }
               break;
            case -287016227:
               if (var1.equals("unicode")) {
                  var2 = 4;
               }
               break;
            case 114717:
               if (var1.equals("ten")) {
                  var2 = 2;
               }
               break;
            case 3512122:
               if (var1.equals("rune")) {
                  var2 = 1;
               }
               break;
            case 888392528:
               if (var1.equals("minecraftten")) {
                  var2 = 3;
               }
         }
         return switch (var2) {
            case 0 -> "smooth";
            case 1 -> "rune";
            case 2, 3 -> "MinecraftTen";
            case 4 -> "unicode";
            default -> "default";
         };
      }
   }

   public static Map<String, Object> Xqedii_3sbajo5() {
      {
         Map var0 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var0.put(
            "binding_name",
            "#title_text"
         );
         var0.put(
            "binding_type",
            "global"
         );
         return var0;
      }
   }

   public static Map<String, Object> Xqedii_1x5x1vv(String var0, String var1) {
      {
         Map var2 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var2.put(
            "binding_type",
            "view"
         );
         var2.put("source_property_name", var0);
         var2.put("target_property_name", var1);
         return var2;
      }
   }

   public static Map<String, Object> Xqedii_31egszk(String var0) {
      {
         return Xqedii_12kquew("form_buttons", var0);
      }
   }

   public static Map<String, Object> Xqedii_12kquew(String var0, String var1) {
      {
         Map var2 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var2.put("binding_name", var1);
         var2.put(
            "binding_type",
            "collection"
         );
         var2.put(
            "binding_collection_name", var0
         );
         return var2;
      }
   }

   public static Map<String, Object> Xqedii_3r2co6p(String var0, String var1) {
      {
         Map var2 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var2.put("binding_name", var0);
         var2.put("binding_name_override", var1);
         var2.put(
            "binding_type",
            "collection"
         );
         var2.put(
            "binding_collection_name",
            "form_buttons"
         );
         return var2;
      }
   }

   public static Map<String, Object> Xqedii_2jx1t4g(String var0, boolean var1) {
      {
         String var2 = Xqedii_fzjzcxyguu3k(var0);
         return Xqedii_1x5x1vv(
            var1 ? Xqedii_yuec6xerau8x(var2) : var2, "#visible"
         );
      }
   }

   public static Map<String, Object> Xqedii_gwex9af() {
      {
         return Xqedii_35q5x1t("form_buttons");
      }
   }

   public static Map<String, Object> Xqedii_35q5x1t(String var0) {
      {
         Map var1 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var1.put(
            "binding_type",
            "collection_details"
         );
         var1.put(
            "binding_collection_name", var0
         );
         return var1;
      }
   }

   public static Map<String, Object> Xqedii_1qdin93(String var0, String var1, String var2) {
      {
         Map var3 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var3.put("from_button_id", var0);
         var3.put("to_button_id", var1);
         var3.put("mapping_type", var2);
         return var3;
      }
   }

   public static Map<String, Object> Xqedii_2dr0g9h(String var0, Map<String, Object> var1) {
      {
         Map var2 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var2.put(var0, var1 == null ? Xqedii_29ge1r6.Xqedii_15tec8i() : var1);
         return var2;
      }
   }

   private static /* synthetic */ String Xqedii_fzjzcxyguu3k(String string) {
        return "((#title_text - '" + string + "') = #title_text)";
    }

    private static /* synthetic */ String Xqedii_yuec6xerau8x(String string) {
        return "(not " + string + ")";
    }
}
