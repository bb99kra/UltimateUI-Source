package dev.xqedii.ultimateUI;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class Xqedii_3ul4hqj {
   public static final String Xqedii_3qsbcgb = "<#fbb724><bold>UltimateUI</bold>";
   public static final String Xqedii_2cy65kl = "<gray>";
   public static final String Xqedii_2xr5bac = "<white>";
   public static final String Xqedii_2qngjkz = "<#ffbe54>";
   public static final String Xqedii_captcr4 = "<#8a989c>\u00bb";
   public static final String Xqedii_19v365c = "<red>";
   public static final String Xqedii_1m0xcyu = "<#ff5f5c>";
   public static final String Xqedii_30yr00v = "<#ff9391>";
   public static final String Xqedii_3gbh8yk = "<#8bf800>";
   public static final String Xqedii_2givc6j = "<#b8fb62>";
   public static final String Xqedii_1bk481m = "<#ceff8f>";
   public static final String Xqedii_2ujfr0a = "<#d1ff96>";
   public static final String Xqedii_2t8y7jv = "<#8dfc00>";
   public static final String Xqedii_sqxedp9 = "<#bbff63>";
   public static final String Xqedii_2eyrvx1 = "<yellow>";
   public static final String Xqedii_35ze87k = "<gold>";
   public static final String Xqedii_3rdl2av = "<#ff1f1f>";
   public static final String Xqedii_132n5z4 = "<#ff4f4f>";
   public static final String Xqedii_29evyik = "<#ff3d3d>";
   public static final String Xqedii_8iyi9im = "<#ff7a7a>";
   public static final String Xqedii_21u2t0l = "<#ffb0b0>";
   public static final String Xqedii_3fkc5er = "<dark_aqua>-";
   public static final String Xqedii_2vwneop = "<gray>";
   public static final MiniMessage Xqedii_1xf01n6;
   public static final Pattern Xqedii_a6gwqw9;
   public static final Pattern Xqedii_a3rwliv;

   public static String Xqedii_1t2nnko(String var0, Object... var1) {
      {
         return Xqedii_12ukznmnnhdw3(
            Xqedii_3ck0mct(
               var0,
               "<#ff9391>",
               "<red>",
               var1
            )
         );
      }
   }

   public static String Xqedii_1wtk0rm(String var0, String var1) {
      {
         String var2 = var1 == null ? "" : var1.trim();
         return Xqedii_gq77ff0nfkc2(
            Xqedii_1u4gly2(var0),
            var2.isEmpty() ? "" : Xqedii_3s5smxf8mz45e(Xqedii_1u4gly2(var2))
         );
      }
   }

   public static String Xqedii_33qmw8k(String var0, Object... var1) {
      {
         return Xqedii_1ytlef5gbxiji(Xqedii_2d5sc5r(var0, var1));
      }
   }

   public static String Xqedii_9qy68nj(String var0, Object... var1) {
      {
         return Xqedii_1pmxym4lkd2o3(Xqedii_2d5sc5r(var0, var1));
      }
   }

   public static String Xqedii_2d5sc5r(String var0, Object... var1) {
      {
         return Xqedii_3ck0mct(
            var0,
            "<#ceff8f><bold>",
            "</bold><#b8fb62>",
            var1
         );
      }
   }

   public static String Xqedii_2iiyx7k(String var0, Object... var1) {
      {
         return Xqedii_1btp05xrgfsq7(Xqedii_28d1izo(var0, var1));
      }
   }

   public static String Xqedii_28d1izo(String var0, Object... var1) {
      {
         return Xqedii_3ck0mct(
            var0,
            "<#bbff63>",
            "<#8dfc00>",
            var1
         );
      }
   }

   public static String Xqedii_39ytrlf(String var0, Object... var1) {
      {
         return Xqedii_2oeqyjzg2c24q(
            Xqedii_3ck0mct(
               var0,
               "<gold>",
               "<yellow>",
               var1
            )
         );
      }
   }

   public static String Xqedii_l0arb10(String var0, Object... var1) {
      {
         return Xqedii_3ck0mct(
            var0,
            "<#ffb0b0>",
            "<#ff7a7a>",
            var1
         );
      }
   }

   public static String Xqedii_1oxsv35(String var0, String var1) {
      {
         return Xqedii_381q7nlq2p3il(Xqedii_1u4gly2(var0), var1 == null ? "" : var1);
      }
   }

   public static String Xqedii_15zzeiq(String var0, String var1, String var2) {
      {
         String var3 = var1 == null ? "" : var1.trim();
         return Xqedii_8tjdnpso8yf7(
            Xqedii_1u4gly2(var0),
            var3.isEmpty() ? "" : Xqedii_3tr45ird3s89c(Xqedii_1u4gly2(var3)),
            Xqedii_1u4gly2(var2)
         );
      }
   }

   public static String Xqedii_2133eaa(String var0) {
      {
         return Xqedii_dinuxvlounm(var0 == null ? "" : var0);
      }
   }

   public static void Xqedii_2dtmver(CommandSender var0, String var1, Object... var2) {
      {
         Xqedii_uawesb0(var0, Xqedii_1t2nnko(var1, var2));
      }
   }

   public static void Xqedii_2olsc6h(CommandSender var0, String var1, String var2) {
      {
         Xqedii_uawesb0(var0, Xqedii_1wtk0rm(var1, var2));
      }
   }

   public static void Xqedii_2pfjv2g(CommandSender var0, String var1, Object... var2) {
      {
         Xqedii_uawesb0(var0, Xqedii_33qmw8k(var1, var2));
      }
   }

   public static void Xqedii_3458djb(CommandSender var0, String var1, Object... var2) {
      {
         Xqedii_uawesb0(var0, Xqedii_9qy68nj(var1, var2));
      }
   }

   public static void Xqedii_3m0zjou(CommandSender var0, String var1, Object... var2) {
      {
         Xqedii_uawesb0(var0, Xqedii_2iiyx7k(var1, var2));
      }
   }

   public static void Xqedii_1o0kice(CommandSender var0, String var1, Object... var2) {
      {
         Xqedii_uawesb0(var0, Xqedii_39ytrlf(var1, var2));
      }
   }

   public static void Xqedii_2g7xq9b(CommandSender var0, boolean var1, String var2, List<String> var3) {
      {
         ArrayList var4 = new ArrayList();
         var4.add("");
         var4.add(
            Xqedii_21smlc7tmfjrm(
               var1
                  ? "\u2714 "
                  : "\u274c ",
               var2
            )
         );
         if (var3 != null) {
            for (String var6 : var3) {
               var4.add(Xqedii_3bxo4q2r6qdlp(var6));
            }
         }

         var4.add("");
         Xqedii_28gzcxq(var0, var4);
      }
   }

   public static void Xqedii_wmipi15(CommandSender var0, String var1, List<String> var2) {
      {
         ArrayList var3 = new ArrayList();
         var3.add("");
         var3.add(Xqedii_1ifzj8n5pkiiy(var1));
         if (var2 != null) {
            for (String var5 : var2) {
               var3.add(Xqedii_3e6hggsaawfk5(var5));
            }
         }

         var3.add("");
         Xqedii_28gzcxq(var0, var3);
      }
   }

   public static void Xqedii_3crurrx(CommandSender var0, String var1, List<String> var2) {
      {
         ArrayList var3 = new ArrayList();
         var3.add("");
         var3.add(Xqedii_2doybf64ejo1s(Xqedii_1u4gly2(var1)));
         var3.add("");
         if (var2 != null) {
            var3.addAll(var2);
         }

         var3.add("");
         Xqedii_28gzcxq(var0, var3);
      }
   }

   public static void Xqedii_uawesb0(CommandSender var0, String var1) {
      {
         Xqedii_1cxddb0(var0, Xqedii_1xf01n6.deserialize(var1 == null ? "" : var1));
      }
   }

   public static void Xqedii_1cxddb0(CommandSender var0, Component var1) {
      if (var0 != null && var1 != null) {
         if (!(var0 instanceof Player)) {
            var0.sendMessage(PlainTextComponentSerializer.plainText().serialize(var1));
         } else {
            Xqedii_nd6rkr3.Xqedii_3s7zz34(var0, var1);
         }
      }
   }

   public static void send(CommandSender var0, Component var1) {
      {
         Xqedii_1cxddb0(var0, var1);
      }
   }

   public static void send(CommandSender var0, List<Component> var1) {
      if (var0 != null && var1 != null && !var1.isEmpty()) {
         if (var0 instanceof Player) {
            Xqedii_nd6rkr3.Xqedii_3s7zz34(var0, Component.empty());
            Xqedii_nd6rkr3.Xqedii_3s7zz34(
               var0,
               Xqedii_1xf01n6.deserialize(
                  " <#fbb724><bold>UltimateUI</bold>"
               )
            );
            Xqedii_nd6rkr3.Xqedii_3s7zz34(var0, Component.empty());

            for (Component var5 : var1) {
               Xqedii_nd6rkr3.Xqedii_3s7zz34(
                  var0,
                  Component.text(" ")
                     .append((Component)(var5 == null ? Component.empty() : var5))
               );
            }

            Xqedii_nd6rkr3.Xqedii_3s7zz34(var0, Component.empty());
         } else {
            for (Component var3 : var1) {
               var0.sendMessage(PlainTextComponentSerializer.plainText().serialize(var3));
            }
         }
      }
   }

   public static void Xqedii_28gzcxq(CommandSender var0, List<String> var1) {
      if (var0 != null && var1 != null) {
         boolean var2 = var0 instanceof Player;

         for (String var4 : var1) {
            Component var5 = Xqedii_1xf01n6.deserialize(var4 == null ? "" : var4);
            if (var2) {
               Xqedii_nd6rkr3.Xqedii_3s7zz34(var0, var5);
            } else {
               String var6 = PlainTextComponentSerializer.plainText().serialize(var5);
               if (!var6.isBlank()) {
                  var0.sendMessage(var6.trim());
               }
            }
         }
      }
   }

   public static String Xqedii_3ck0mct(String var0, String var1, String var2, Object... var3) {
      {
         String var4 = var0 == null ? "" : var0;
         if (var3 == null) {
            return var4;
         } else {
            for (int var5 = 0; var5 < var3.length; var5++) {
               var4 = var4.replace(Xqedii_35euv518d4pug(var5), Xqedii_1yk7ezerwh4um(var1, Xqedii_1u4gly2(String.valueOf(var3[var5])), var2));
            }

            return var4;
         }
      }
   }

   public static String Xqedii_2emodor(String var0) {
      if (var0 != null && !var0.isEmpty()) {
         Matcher var1 = Xqedii_a3rwliv.matcher(var0);
         StringBuilder var2 = new StringBuilder(var0.length() + 16);

         while (var1.find()) {
            var1.appendReplacement(
               var2,
               Matcher.quoteReplacement(
                  Xqedii_y6c5i7x(
                     Character.toLowerCase(
                        var1.group(1).charAt(0)
                     )
                  )
               )
            );
         }

         var1.appendTail(var2);
         return var2.toString();
      } else {
         return "";
      }
   }

   public static String Xqedii_y6c5i7x(char var0) {
      {
         return switch (var0) {
            case '0' -> "<reset><black>";
            case '1' -> "<reset><dark_blue>";
            case '2' -> "<reset><dark_green>";
            case '3' -> "<reset><dark_aqua>";
            case '4' -> "<reset><dark_red>";
            case '5' -> "<reset><dark_purple>";
            case '6' -> "<reset><gold>";
            case '7' -> "<reset><gray>";
            case '8' -> "<reset><dark_gray>";
            case '9' -> "<reset><blue>";
            default -> "<reset>";
            case 'a' -> "<reset><green>";
            case 'b' -> "<reset><aqua>";
            case 'c' -> "<reset><red>";
            case 'd' -> "<reset><light_purple>";
            case 'e' -> "<reset><yellow>";
            case 'f' -> "<reset><white>";
            case 'k' -> "<obfuscated>";
            case 'l' -> "<bold>";
            case 'm' -> "<strikethrough>";
            case 'n' -> "<underlined>";
            case 'o' -> "<italic>";
         };
      }
   }

   public static String Xqedii_1u4gly2(String var0) {
      {
         return var0 == null ? "" : Xqedii_1xf01n6.escapeTags(var0);
      }
   }

   public static String Xqedii_2qnwa4c(String var0) {
      {
         return var0 == null
            ? ""
            : Xqedii_a6gwqw9.matcher(var0).replaceFirst("");
      }
   }

   static {
      {
         Xqedii_1xf01n6 = MiniMessage.miniMessage();
         Xqedii_a6gwqw9 = Pattern.compile(
            "^\\s*(?:[&\u00a7][0-9a-fk-or])*\\s*\\[UltimateUI[^\\]]*]\\s*",
            2
         );
         Xqedii_a3rwliv = Pattern.compile(
            "[&\u00a7]([0-9a-fk-orA-FK-OR])"
         );
      }
   }

   private static /* synthetic */ String Xqedii_12ukznmnnhdw3(String string) {
        return "<red>" + string;
    }

    private static /* synthetic */ String Xqedii_3s5smxf8mz45e(String string) {
        return " <#ff9391>" + string;
    }

    private static /* synthetic */ String Xqedii_gq77ff0nfkc2(String string, String string2) {
        return "<red>Correct usage: <#ff5f5c>" + string + string2;
    }

    private static /* synthetic */ String Xqedii_1ytlef5gbxiji(String string) {
        return "<#8bf800>\u2714 <#b8fb62>" + string;
    }

    private static /* synthetic */ String Xqedii_1pmxym4lkd2o3(String string) {
        return "<#8bf800>\u274c <#b8fb62>" + string;
    }

    private static /* synthetic */ String Xqedii_1btp05xrgfsq7(String string) {
        return "<#8dfc00>" + string;
    }

    private static /* synthetic */ String Xqedii_2oeqyjzg2c24q(String string) {
        return "<yellow>" + string;
    }

    private static /* synthetic */ String Xqedii_381q7nlq2p3il(String string, String string2) {
        return " <#8dfc00>" + string + ": <#bbff63>" + string2;
    }

    private static /* synthetic */ String Xqedii_3tr45ird3s89c(String string) {
        return " <#bbff63>" + string;
    }

    private static /* synthetic */ String Xqedii_8tjdnpso8yf7(String string, String string2, String string3) {
        return " <#8dfc00>" + string + string2 + " <dark_aqua>- <gray>" + string3;
    }

    private static /* synthetic */ String Xqedii_dinuxvlounm(String string) {
        return " <#8dfc00>\u25cf <#bbff63>" + string;
    }

    private static /* synthetic */ String Xqedii_21smlc7tmfjrm(String string, String string2) {
        return " <#8bf800>" + string + "<#b8fb62>" + string2;
    }

    private static /* synthetic */ String Xqedii_3bxo4q2r6qdlp(String string) {
        return "  <#8bf800>\u25cf <#b8fb62>" + string;
    }

    private static /* synthetic */ String Xqedii_1ifzj8n5pkiiy(String string) {
        return " <#ff1f1f>\u26a0 <#ff4f4f>" + string;
    }

    private static /* synthetic */ String Xqedii_3e6hggsaawfk5(String string) {
        return "  <#ff3d3d>\u25cf <#ff7a7a>" + string;
    }

    private static /* synthetic */ String Xqedii_2doybf64ejo1s(String string) {
        return " <#8dfc00><bold>" + string + "</bold>";
    }

    private static /* synthetic */ String Xqedii_35euv518d4pug(int n) {
        return "{" + n + "}";
    }

    private static /* synthetic */ String Xqedii_1yk7ezerwh4um(String string, String string2, String string3) {
        return string + string2 + string3;
    }
}
