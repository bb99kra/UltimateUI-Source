package dev.xqedii.ultimateUI;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Xqedii_1wyzus8 {
   public static final String Xqedii_38br1rf = "if (make_hud(";
   public static final String Xqedii_24jddqr = "void main()";
   public static final String Xqedii_e122qep = "if (color.a < 0.1)";
   public static final Pattern Xqedii_6srikao;

   public static boolean Xqedii_1br3yjd(String var0) {
      {
         return ((var0 != null
               && var0.contains(
                  "GL_ARB_separate_shader_objects"
               )
            ? 1
            : 0) != 0);
      }
   }

   public static int Xqedii_23ul7l2(String var0, String var1) {
      {
         return Math.max(Xqedii_3mk38d4(var0), Xqedii_3mk38d4(var1)) + 1;
      }
   }

   public static String Xqedii_2e9uhb4(String var0, String var1, boolean var2, int var3) {
      {
         int var4 = var0.lastIndexOf("void main()");
         int var5 = var0.indexOf("if (make_hud(");
         if (var4 >= 0 && var5 >= 0 && var5 >= var4) {
            StringBuilder var6 = new StringBuilder(var1.length() + 256);
            var6.append((char)10);
            var6.append(
               var2
                  ? Xqedii_chkx0ogazp4a(var3)
                  : "out vec3 uuiBbCorner;\n"
            );
            var6.append(
                  "#define UUI_BB_VERTEX_ID "
               )
               .append(
                  var2
                     ? "gl_VertexIndex"
                     : "gl_VertexID"
               )
               .append((char)10);
            var6.append(var1).append((char)10);
            int var7 = var0.lastIndexOf(10, var5);
            String var8 = var7 < 0
               ? "    "
               : var0.substring(var7 + 1, var5);
            StringBuilder var9 = new StringBuilder(256);
            var9.append(var8).append("if (uui_bb()) {\n");
            if (var0.contains(
               "out float sphericalVertexDistance"
            )) {
               var9.append(
                  "#if !defined(IS_GUI) && !defined(IS_SEE_THROUGH)\n"
               );
               var9.append(var8)
                  .append(
                     "    sphericalVertexDistance = -1.0e6;\n"
                  );
               var9.append(var8)
                  .append(
                     "    cylindricalVertexDistance = -1.0e6;\n"
                  );
               var9.append("#endif\n");
            } else if (var0.contains(
               "out float vertexDistance"
            )) {
               var9.append(var8)
                  .append(
                     "    vertexDistance = -1.0e6;\n"
                  );
            }

            var9.append(var8).append("    return;\n");
            var9.append(var8).append("}\n");
            int var10 = var0.indexOf(123, var4);
            if (var10 >= 0 && var10 <= var7) {
               StringBuilder var11 = new StringBuilder(var0.length() + var6.length() + var9.length());
               var11.append(var0, 0, var4);
               var11.append((CharSequence)var6);
               var11.append(var0, var4, var10 + 1);
               var11.append("\n")
                  .append(var8)
                  .append(
                     "uuiBbCorner = vec3(-1.0, -1.0, -1.0);"
                  );
               var11.append(var0, var10 + 1, var7 + 1);
               var11.append((CharSequence)var9);
               var11.append(var0, var7 + 1, var0.length());
               return var11.toString();
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   public static String Xqedii_20ik0bw(String var0, String var1, double[] var2, double[] var3, boolean var4, int var5) {
      {
         int var6 = var0.lastIndexOf("void main()");
         int var7 = var0.indexOf("if (color.a < 0.1)");
         if (var6 >= 0 && var7 >= 0 && var7 >= var6) {
            StringBuilder var8 = new StringBuilder(var1.length() + 1024);
            var8.append((char)10);
            var8.append(
               var4
                  ? Xqedii_30ez9qzbwopo4(var5)
                  : "in vec3 uuiBbCorner;\n"
            );
            var8.append(
               Xqedii_2hum3m6("UUI_BB_MASK_LEFT", var2)
            );
            var8.append(
               Xqedii_2hum3m6("UUI_BB_MASK_RIGHT", var3)
            );
            var8.append(var1).append((char)10);
            int var9 = var0.lastIndexOf(10, var7);
            String var10 = var9 < 0
               ? "    "
               : var0.substring(var9 + 1, var7);
            String var11 = Xqedii_12b24obe0m5b5(var10, var10, var10);
            StringBuilder var12 = new StringBuilder(var0.length() + var8.length() + var11.length());
            var12.append(var0, 0, var6);
            var12.append((CharSequence)var8);
            var12.append(var0, var6, var9 + 1);
            var12.append(var11);
            var12.append(var0, var9 + 1, var0.length());
            return var12.toString();
         } else {
            return null;
         }
      }
   }

   public static String Xqedii_2hum3m6(String var0, double[] var1) {
      {
         StringBuilder var2 = new StringBuilder(
            var1.length * 10 + 64
         );
         var2.append("const float ")
            .append(var0)
            .append((char)91)
            .append(var1.length)
            .append("] = float[")
            .append(var1.length)
            .append("](");

         for (int var3 = 0; var3 < var1.length; var3++) {
            if (var3 > 0) {
               var2.append(", ");
            }

            Locale var10001 = Locale.ROOT;
            String var10002 = "%.5f";
            Object[] var10003 = new Object[1];
            var10003[0] = var1[var3];
            var2.append(String.format(var10001, var10002, var10003));
         }

         var2.append(");\n");
         return var2.toString();
      }
   }

   public static int Xqedii_3mk38d4(String var0) {
      {
         int var1 = -1;
         if (var0 == null) {
            return var1;
         } else {
            Matcher var2 = Xqedii_6srikao.matcher(var0);

            while (var2.find()) {
               try {
                  var1 = Math.max(var1, Integer.parseInt(var2.group(1)));
               } catch (NumberFormatException var4) {
               }
            }

            return var1;
         }
      }
   }

   static {
      {
         Xqedii_6srikao = Pattern.compile(
            "layout\\s*\\(\\s*location\\s*=\\s*(\\d+)\\s*\\)"
         );
      }
   }

   private static /* synthetic */ String Xqedii_chkx0ogazp4a(int n) {
        return "layout(location = " + n + ") out vec3 uuiBbCorner;\n";
    }

    private static /* synthetic */ String Xqedii_30ez9qzbwopo4(int n) {
        return "layout(location = " + n + ") in vec3 uuiBbCorner;\n";
    }

    private static /* synthetic */ String Xqedii_12b24obe0m5b5(String string, String string2, String string3) {
        return string + "if (uuiBbCorner.x >= 0.0) {\n" + string2 + "    color.a *= uui_bb_corner_alpha(uuiBbCorner);\n" + string3 + "}\n";
    }
}
