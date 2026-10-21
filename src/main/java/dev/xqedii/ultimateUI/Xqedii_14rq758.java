package dev.xqedii.ultimateUI;

import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public final class Xqedii_14rq758 {
   public static final MiniMessage Xqedii_369g29m;
   public static final Map<Character, String> Xqedii_1xbmm5i;

   public static Component Xqedii_g673f8i(String var0) {
      {
         String var1 = Xqedii_1dorkas(var0 == null ? "" : var0);

         try {
            return Xqedii_369g29m.deserialize(var1);
         } catch (Exception var3) {
            return Component.text(var0 == null ? "" : var0);
         }
      }
   }

   public static String Xqedii_1dorkas(String var0) {
      if (var0.indexOf(38) < 0
         && var0.indexOf(167) < 0) {
         return var0;
      } else {
         StringBuilder var1 = new StringBuilder(var0.length() + 16);

         for (int var2 = 0; var2 < var0.length(); var2++) {
            char var3 = var0.charAt(var2);
            if ((var3 == 38 || var3 == 167)
               && var2 + 1 < var0.length()) {
               char var4 = Character.toLowerCase(var0.charAt(var2 + 1));
               if (var4 == 35
                  && var2 + 7 < var0.length()
                  && Xqedii_mb90a9e(var0, var2 + 2, 6)) {
                  var1.append("<#")
                     .append(var0, var2 + 2, var2 + 8)
                     .append((char)62);
                  var2 += 7;
                  continue;
               }

               String var5 = Xqedii_1xbmm5i.get(var4);
               if (var5 != null) {
                  var1.append(var5);
                  var2++;
                  continue;
               }
            }

            var1.append(var3);
         }

         return var1.toString();
      }
   }

   public static boolean Xqedii_mb90a9e(String var0, int var1, int var2) {
      {
         for (int var3 = var1; var3 < var1 + var2; var3++) {
            if (Character.digit(var0.charAt(var3), 16) < 0) {
               return false;
            }
         }

         return true;
      }
   }

   public static String Xqedii_jsswoik(String var0) {
      {
         return var0 == null
            ? ""
            : var0.replaceAll(
                  "(?i)[&\u00a7][0-9a-fk-or]",
                  ""
               )
               .toLowerCase(Locale.ROOT);
      }
   }

   static {
      {
         Xqedii_369g29m = MiniMessage.miniMessage();
         Entry[] var10000 = new Entry[22];
         var10000[0] = Map.entry(
            Character.valueOf((char)48),
            "<black>"
         );
         var10000[1] = Map.entry(
            Character.valueOf((char)49),
            "<dark_blue>"
         );
         var10000[2] = Map.entry(
            Character.valueOf((char)50),
            "<dark_green>"
         );
         var10000[3] = Map.entry(
            Character.valueOf((char)51),
            "<dark_aqua>"
         );
         var10000[4] = Map.entry(
            Character.valueOf((char)52),
            "<dark_red>"
         );
         var10000[5] = Map.entry(
            Character.valueOf((char)53),
            "<dark_purple>"
         );
         var10000[6] = Map.entry(
            Character.valueOf((char)54),
            "<gold>"
         );
         var10000[7] = Map.entry(
            Character.valueOf((char)55),
            "<gray>"
         );
         var10000[8] = Map.entry(
            Character.valueOf((char)56),
            "<dark_gray>"
         );
         var10000[9] = Map.entry(
            Character.valueOf((char)57),
            "<blue>"
         );
         var10000[10] = Map.entry(
            Character.valueOf((char)97),
            "<green>"
         );
         var10000[11] = Map.entry(
            Character.valueOf((char)98),
            "<aqua>"
         );
         var10000[12] = Map.entry(
            Character.valueOf((char)99),
            "<red>"
         );
         var10000[13] = Map.entry(
            Character.valueOf((char)100),
            "<light_purple>"
         );
         var10000[14] = Map.entry(
            Character.valueOf((char)101),
            "<yellow>"
         );
         var10000[15] = Map.entry(
            Character.valueOf((char)102),
            "<white>"
         );
         var10000[16] = Map.entry(
            Character.valueOf((char)108),
            "<bold>"
         );
         var10000[17] = Map.entry(
            Character.valueOf((char)111),
            "<italic>"
         );
         var10000[18] = Map.entry(
            Character.valueOf((char)110),
            "<underlined>"
         );
         var10000[19] = Map.entry(
            Character.valueOf((char)109),
            "<strikethrough>"
         );
         var10000[20] = Map.entry(
            Character.valueOf((char)107),
            "<obfuscated>"
         );
         var10000[21] = Map.entry(
            Character.valueOf((char)114),
            "<reset>"
         );
         Xqedii_1xbmm5i = Map.ofEntries(var10000);
      }
   }
}
