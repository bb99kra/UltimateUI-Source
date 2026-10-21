package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class Xqedii_1iw7g71 {
   public static final Set<String> Xqedii_1cqvf8d;

   public static boolean Xqedii_2ln2q92(String var0) {
      {
         return Xqedii_1cqvf8d.contains(var0.toLowerCase(Locale.ROOT));
      }
   }

   public static double Xqedii_3a2hke1(Object[] var0, int var1, int var2) {
      {
         return Xqedii_z4wg071.Xqedii_3c9epia(Xqedii_2d07ocz.Xqedii_1k661t2(var0, var1, var2), var2);
      }
   }

   public static String Xqedii_2coa7to(Object[] var0, int var1, int var2) {
      {
         return Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_2d07ocz.Xqedii_1k661t2(var0, var1, var2));
      }
   }

   public static List<Object> Xqedii_2juewiy(Object[] var0, int var1, int var2) {
      {
         Object var3 = Xqedii_2d07ocz.Xqedii_1k661t2(var0, var1, var2);
         if (var3 instanceof List var4) {
            return var4;
         } else {
            throw new ScriptSyntaxException(Xqedii_18d8jher4vgib(Xqedii_z4wg071.Xqedii_yl2qf35(var3)), var2);
         }
      }
   }

   public static Object Xqedii_2xeqrwc(ScriptExecutionContext var0, String var1, Object[] var2, int var3) {
      {
         String var4 = var1.toLowerCase(Locale.ROOT);
         int var6 = -1;
         switch (var4.hashCode()) {
            case -1765638420:
               if (var4.equals("capitalize")) {
                  var6 = 30;
               }
               break;
            case -1699012487:
               if (var4.equals("isdragging")) {
                  var6 = 61;
               }
               break;
            case -1662836996:
               if (var4.equals("element")) {
                  var6 = 57;
               }
               break;
            case -1554585449:
               if (var4.equals("startswith")) {
                  var6 = 36;
               }
               break;
            case -1361636556:
               if (var4.equals("chance")) {
                  var6 = 15;
               }
               break;
            case -1289358244:
               if (var4.equals("exists")) {
                  var6 = 58;
               }
               break;
            case -1276252356:
               if (var4.equals("presskey")) {
                  var6 = 70;
               }
               break;
            case -1268779017:
               if (var4.equals("format")) {
                  var6 = 41;
               }
               break;
            case -1179283852:
               if (var4.equals("isopen")) {
                  var6 = 60;
               }
               break;
            case -1106363674:
               if (var4.equals("length")) {
                  var6 = 26;
               }
               break;
            case -1102508299:
               if (var4.equals("listof")) {
                  var6 = 69;
               }
               break;
            case -1034364087:
               if (var4.equals("number")) {
                  var6 = 21;
               }
               break;
            case -1012222381:
               if (var4.equals("online")) {
                  var6 = 74;
               }
               break;
            case -985752863:
               if (var4.equals("player")) {
                  var6 = 73;
               }
               break;
            case -938285885:
               if (var4.equals("random")) {
                  var6 = 14;
               }
               break;
            case -934531685:
               if (var4.equals("repeat")) {
                  var6 = 33;
               }
               break;
            case -858798729:
               if (var4.equals("typing")) {
                  var6 = 64;
               }
               break;
            case -823812830:
               if (var4.equals("values")) {
                  var6 = 51;
               }
               break;
            case -678927291:
               if (var4.equals("percent")) {
                  var6 = 18;
               }
               break;
            case -631448035:
               if (var4.equals("average")) {
                  var6 = 46;
               }
               break;
            case -567445985:
               if (var4.equals("contains")) {
                  var6 = 35;
               }
               break;
            case -503819303:
               if (var4.equals("openpage")) {
                  var6 = 72;
               }
               break;
            case -8339209:
               if (var4.equals("elements")) {
                  var6 = 59;
               }
               break;
            case 96370:
               if (var4.equals("abs")) {
                  var6 = 3;
               }
               break;
            case 98695:
               if (var4.equals("cos")) {
                  var6 = 10;
               }
               break;
            case 103195:
               if (var4.equals("hex")) {
                  var6 = 76;
               }
               break;
            case 104431:
               if (var4.equals("int")) {
                  var6 = 23;
               }
               break;
            case 107029:
               if (var4.equals("len")) {
                  var6 = 27;
               }
               break;
            case 107876:
               if (var4.equals("max")) {
                  var6 = 1;
               }
               break;
            case 108114:
               if (var4.equals("min")) {
                  var6 = 0;
               }
               break;
            case 109270:
               if (var4.equals("now")) {
                  var6 = 53;
               }
               break;
            case 109446:
               if (var4.equals("num")) {
                  var6 = 22;
               }
               break;
            case 110739:
               if (var4.equals("pad")) {
                  var6 = 42;
               }
               break;
            case 111192:
               if (var4.equals("pow")) {
                  var6 = 8;
               }
               break;
            case 112845:
               if (var4.equals("rgb")) {
                  var6 = 75;
               }
               break;
            case 113880:
               if (var4.equals("sin")) {
                  var6 = 9;
               }
               break;
            case 114225:
               if (var4.equals("str")) {
                  var6 = 20;
               }
               break;
            case 114251:
               if (var4.equals("sum")) {
                  var6 = 45;
               }
               break;
            case 114593:
               if (var4.equals("tan")) {
                  var6 = 11;
               }
               break;
            case 3029738:
               if (var4.equals("bool")) {
                  var6 = 25;
               }
               break;
            case 3049733:
               if (var4.equals("ceil")) {
                  var6 = 6;
               }
               break;
            case 3076014:
               if (var4.equals("date")) {
                  var6 = 54;
               }
               break;
            case 3267882:
               if (var4.equals("join")) {
                  var6 = 40;
               }
               break;
            case 3288564:
               if (var4.equals("keys")) {
                  var6 = 50;
               }
               break;
            case 3318135:
               if (var4.equals("lerp")) {
                  var6 = 16;
               }
               break;
            case 3322014:
               if (var4.equals("list")) {
                  var6 = 43;
               }
               break;
            case 3530173:
               if (var4.equals("sign")) {
                  var6 = 13;
               }
               break;
            case 3536286:
               if (var4.equals("sort")) {
                  var6 = 47;
               }
               break;
            case 3538208:
               if (var4.equals("sqrt")) {
                  var6 = 7;
               }
               break;
            case 3556653:
               if (var4.equals("text")) {
                  var6 = 19;
               }
               break;
            case 3560141:
               if (var4.equals("time")) {
                  var6 = 52;
               }
               break;
            case 3568674:
               if (var4.equals("trim")) {
                  var6 = 31;
               }
               break;
            case 3575610:
               if (var4.equals("type")) {
                  var6 = 77;
               }
               break;
            case 66669991:
               if (var4.equals("scrollable")) {
                  var6 = 68;
               }
               break;
            case 93133970:
               if (var4.equals("atan2")) {
                  var6 = 12;
               }
               break;
            case 94742715:
               if (var4.equals("clamp")) {
                  var6 = 2;
               }
               break;
            case 97526796:
               if (var4.equals("floor")) {
                  var6 = 5;
               }
               break;
            case 103164673:
               if (var4.equals("lower")) {
                  var6 = 29;
               }
               break;
            case 106426308:
               if (var4.equals("pages")) {
                  var6 = 66;
               }
               break;
            case 108280125:
               if (var4.equals("range")) {
                  var6 = 44;
               }
               break;
            case 108398153:
               if (var4.equals("remap")) {
                  var6 = 17;
               }
               break;
            case 108704142:
               if (var4.equals("round")) {
                  var6 = 4;
               }
               break;
            case 109648666:
               if (var4.equals("split")) {
                  var6 = 34;
               }
               break;
            case 110355062:
               if (var4.equals("ticks")) {
                  var6 = 55;
               }
               break;
            case 111499426:
               if (var4.equals("upper")) {
                  var6 = 28;
               }
               break;
            case 356212403:
               if (var4.equals("focusedinput")) {
                  var6 = 65;
               }
               break;
            case 530542161:
               if (var4.equals("substring")) {
                  var6 = 38;
               }
               break;
            case 555802867:
               if (var4.equals("isnumber")) {
                  var6 = 24;
               }
               break;
            case 586953050:
               if (var4.equals("mouseheld")) {
                  var6 = 63;
               }
               break;
            case 598246771:
               if (var4.equals("placeholder")) {
                  var6 = 56;
               }
               break;
            case 1094496948:
               if (var4.equals("replace")) {
                  var6 = 32;
               }
               break;
            case 1099846370:
               if (var4.equals("reverse")) {
                  var6 = 48;
               }
               break;
            case 1744111550:
               if (var4.equals("endswith")) {
                  var6 = 37;
               }
               break;
            case 1805905835:
               if (var4.equals("pageexists")) {
                  var6 = 67;
               }
               break;
            case 1846033455:
               if (var4.equals("newpage")) {
                  var6 = 71;
               }
               break;
            case 1912530322:
               if (var4.equals("dragged")) {
                  var6 = 62;
               }
               break;
            case 1943292457:
               if (var4.equals("indexof")) {
                  var6 = 39;
               }
               break;
            case 2072332025:
               if (var4.equals("shuffle")) {
                  var6 = 49;
               }
         }

         switch (var6) {
            case 0:
               if (var2.length == 1
                  && var2[0] instanceof List var49) {
                  return var49.stream().mapToDouble(var1x -> {
                     {
                        return Xqedii_z4wg071.Xqedii_3c9epia(var1x, var3);
                     }
                  }).min().orElse(Double.longBitsToDouble(0L));
               }

               double var48 = Xqedii_3a2hke1(var2, 0, var3);

               for (int var80 = 1; var80 < var2.length; var80++) {
                  var48 = Math.min(var48, Xqedii_3a2hke1(var2, var80, var3));
               }

               return var48;
            case 1:
               if (var2.length == 1
                  && var2[0] instanceof List var47) {
                  return var47.stream().mapToDouble(var1x -> {
                     {
                        return Xqedii_z4wg071.Xqedii_3c9epia(var1x, var3);
                     }
                  }).max().orElse(Double.longBitsToDouble(0L));
               }

               double var46 = Xqedii_3a2hke1(var2, 0, var3);

               for (int var79 = 1; var79 < var2.length; var79++) {
                  var46 = Math.max(var46, Xqedii_3a2hke1(var2, var79, var3));
               }

               return var46;
            case 2:
               double var45 = Xqedii_3a2hke1(var2, 1, var3);
               double var78 = Xqedii_3a2hke1(var2, 2, var3);
               return Math.max(
                  Math.min(var45, var78), Math.min(Math.max(var45, var78), Xqedii_3a2hke1(var2, 0, var3))
               );
            case 3:
               return Math.abs(Xqedii_3a2hke1(var2, 0, var3));
            case 4:
               double var44 = Xqedii_3a2hke1(var2, 0, var3);
               if (var2.length < 2) {
                  return (double)Math.round(var44);
               }

               int var77 = Math.max(
                  0,
                  Math.min(
                     10,
                     Xqedii_z4wg071.Xqedii_1x518al(var2[1], var3)
                  )
               );
               return BigDecimal.valueOf(var44).setScale(var77, RoundingMode.HALF_UP).doubleValue();
            case 5:
               return Math.floor(Xqedii_3a2hke1(var2, 0, var3));
            case 6:
               return Math.ceil(Xqedii_3a2hke1(var2, 0, var3));
            case 7:
               return Math.sqrt(Xqedii_3a2hke1(var2, 0, var3));
            case 8:
               return Math.pow(
                  Xqedii_3a2hke1(var2, 0, var3),
                  Xqedii_3a2hke1(var2, 1, var3)
               );
            case 9:
               return Math.sin(Math.toRadians(Xqedii_3a2hke1(var2, 0, var3)));
            case 10:
               return Math.cos(Math.toRadians(Xqedii_3a2hke1(var2, 0, var3)));
            case 11:
               return Math.tan(Math.toRadians(Xqedii_3a2hke1(var2, 0, var3)));
            case 12:
               return Math.toDegrees(
                  Math.atan2(
                     Xqedii_3a2hke1(var2, 0, var3),
                     Xqedii_3a2hke1(var2, 1, var3)
                  )
               );
            case 13:
               return Math.signum(Xqedii_3a2hke1(var2, 0, var3));
            case 14:
               ThreadLocalRandom var43 = ThreadLocalRandom.current();
               if (var2.length == 0) {
                  return var43.nextDouble();
               } else {
                  if (var2.length == 1
                     && var2[0] instanceof List var66) {
                     return var66.isEmpty() ? null : var66.get(var43.nextInt(var66.size()));
                  }

                  long var65 = (long)Math.ceil(
                     var2.length == 1
                        ? Double.longBitsToDouble(4607182418800017408L)
                        : Xqedii_3a2hke1(var2, 0, var3)
                  );
                  long var86 = (long)Math.floor(
                     var2.length == 1
                        ? Xqedii_3a2hke1(var2, 0, var3)
                        : Xqedii_3a2hke1(var2, 1, var3)
                  );
                  if (var86 < var65) {
                     long var90 = var65;
                     var65 = var86;
                     var86 = var90;
                  }

                  return (double)var43.nextLong(var65, var86 + 1L);
               }
            case 15:
               return Boolean.valueOf(
                  ((ThreadLocalRandom.current().nextDouble() * Double.longBitsToDouble(4636737291354636288L)
                        < Xqedii_3a2hke1(var2, 0, var3)
                     ? 1
                     : 0) != 0)
               );
            case 16:
               return Xqedii_3a2hke1(var2, 0, var3)
                  + (
                        Xqedii_3a2hke1(var2, 1, var3)
                           - Xqedii_3a2hke1(var2, 0, var3)
                     )
                     * Xqedii_3a2hke1(var2, 2, var3);
            case 17:
               double var42 = Xqedii_3a2hke1(var2, 1, var3);
               double var75 = Xqedii_3a2hke1(var2, 2, var3);
               double var89 = var75 == var42
                  ? Double.longBitsToDouble(0L)
                  : (Xqedii_3a2hke1(var2, 0, var3) - var42) / (var75 - var42);
               return Xqedii_3a2hke1(var2, 3, var3)
                  + (
                        Xqedii_3a2hke1(var2, 4, var3)
                           - Xqedii_3a2hke1(var2, 3, var3)
                     )
                     * var89;
            case 18:
               return Xqedii_3a2hke1(var2, 1, var3)
                     == Double.longBitsToDouble(0L)
                  ? Double.longBitsToDouble(0L)
                  : Xqedii_3a2hke1(var2, 0, var3)
                     / Xqedii_3a2hke1(var2, 1, var3)
                     * Double.longBitsToDouble(4636737291354636288L);
            case 19:
            case 20:
               return var2.length == 0
                  ? ""
                  : Xqedii_z4wg071.Xqedii_2mlipa7(var2[0]);
            case 21:
            case 22:
               return var2.length == 0
                  ? Double.longBitsToDouble(0L)
                  : Xqedii_z4wg071.Xqedii_3pdnyon(
                     var2[0],
                     var2.length > 1
                        ? Xqedii_3a2hke1(var2, 1, var3)
                        : Double.longBitsToDouble(0L)
                  );
            case 23:
               return (double)(
                  (long)Xqedii_z4wg071.Xqedii_3pdnyon(
                     Xqedii_2d07ocz.Xqedii_1k661t2(var2, 0, var3),
                     Double.longBitsToDouble(0L)
                  )
               );
            case 24:
               return Boolean.valueOf(
                  ((!Double.isNaN(
                        Xqedii_z4wg071.Xqedii_3pdnyon(
                           Xqedii_2d07ocz.Xqedii_1k661t2(var2, 0, var3),
                           Double.longBitsToDouble(9221120237041090560L)
                        )
                     )
                     ? 1
                     : 0) != 0)
               );
            case 25:
               return Xqedii_z4wg071.Xqedii_2od1ceu(Xqedii_2d07ocz.Xqedii_1k661t2(var2, 0, var3));
            case 26:
            case 27:
               Object var41 = Xqedii_2d07ocz.Xqedii_1k661t2(var2, 0, var3);
               if (var41 instanceof List var64) {
                  return (double)var64.size();
               } else {
                  if (var41 instanceof Map var63) {
                     return (double)var63.size();
                  }

                  return (double)Xqedii_z4wg071.Xqedii_2mlipa7(var41).length();
               }
            case 28:
               return Xqedii_2coa7to(var2, 0, var3).toUpperCase(Locale.ROOT);
            case 29:
               return Xqedii_2coa7to(var2, 0, var3).toLowerCase(Locale.ROOT);
            case 30:
               String var40 = Xqedii_2coa7to(var2, 0, var3);
               return var40.isEmpty()
                  ? var40
                  : Xqedii_1gxx04hq45d94(
                     var40.substring(0, 1)
                        .toUpperCase(Locale.ROOT),
                     var40.substring(1)
                  );
            case 31:
               return Xqedii_2coa7to(var2, 0, var3).trim();
            case 32:
               return Xqedii_2coa7to(var2, 0, var3)
                  .replace(
                     Xqedii_2coa7to(var2, 1, var3),
                     Xqedii_2coa7to(var2, 2, var3)
                  );
            case 33:
               return Xqedii_2coa7to(var2, 0, var3)
                  .repeat(
                     Math.max(
                        0,
                        Xqedii_z4wg071.Xqedii_1x518al(Xqedii_2d07ocz.Xqedii_1k661t2(var2, 1, var3), var3)
                     )
                  );
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
               Object var39 = Xqedii_2d07ocz.Xqedii_1k661t2(var2, 0, var3);
               Object[] var62 = new Object[var2.length - 1];
               System.arraycopy(
                  var2, 1, var62, 0, var62.length
               );
               return Xqedii_2d07ocz.Xqedii_1sr2hld(var0, var39, var4, var62, var3);
            case 40:
               List var38 = Xqedii_2juewiy(var2, 0, var3);
               String var61 = var2.length > 1
                  ? Xqedii_2coa7to(var2, 1, var3)
                  : ", ";
               StringBuilder var74 = new StringBuilder();

               for (int var85 = 0; var85 < var38.size(); var85++) {
                  if (var85 > 0) {
                     var74.append(var61);
                  }

                  var74.append(Xqedii_z4wg071.Xqedii_2mlipa7(var38.get(var85)));
               }

               return var74.toString();
            case 41:
               double var37 = Xqedii_3a2hke1(var2, 0, var3);
               int var73 = var2.length > 1
                  ? Math.max(
                     0,
                     Math.min(
                        10,
                        Xqedii_z4wg071.Xqedii_1x518al(var2[1], var3)
                     )
                  )
                  : 0;
               BigDecimal var84 = BigDecimal.valueOf(var37).setScale(var73, RoundingMode.HALF_UP);
               String var88 = var84.toPlainString();
               String[] var12 = var88.split("\\.");
               String var91 = var12[0];
               boolean var92 = var91.startsWith("-");
               if (var92) {
                  var91 = var91.substring(1);
               }

               StringBuilder var15 = new StringBuilder();

               for (int var16 = 0; var16 < var91.length(); var16++) {
                  if (var16 > 0 && (var91.length() - var16) % 3 == 0) {
                     var15.append((char)44);
                  }

                  var15.append(var91.charAt(var16));
               }

               return Xqedii_1jj4qydus8kzk(
                  var92
                     ? "-"
                     : "",
                  String.valueOf(var15),
                  var12.length > 1
                     ? Xqedii_r7ex55vboi06(var12[1])
                     : ""
               );
            case 42:
               String var36 = Xqedii_2coa7to(var2, 0, var3);
               int var60 = Xqedii_z4wg071.Xqedii_1x518al(Xqedii_2d07ocz.Xqedii_1k661t2(var2, 1, var3), var3);
               String var72 = var2.length > 2
                  ? Xqedii_2coa7to(var2, 2, var3)
                  : " ";
               if (!var72.isEmpty() && var36.length() < var60) {
                  StringBuilder var83 = new StringBuilder();

                  while (var83.length() + var36.length() < var60) {
                     var83.append(var72);
                  }

                  return Xqedii_3ul6rh2pto9k9(var83.substring(0, var60 - var36.length()), var36);
               }

               return var36;
            case 43:
               ArrayList var35 = new ArrayList(var2.length);
               Collections.addAll(var35, var2);
               return var35;
            case 44:
               double var34 = var2.length > 1
                  ? Xqedii_3a2hke1(var2, 0, var3)
                  : Double.longBitsToDouble(4607182418800017408L);
               double var71 = var2.length > 1
                  ? Xqedii_3a2hke1(var2, 1, var3)
                  : Xqedii_3a2hke1(var2, 0, var3);
               double var87 = var2.length > 2
                  ? Xqedii_3a2hke1(var2, 2, var3)
                  : (var71 >= var34 ? 1 : -1);
               if (var87 != Double.longBitsToDouble(0L)
                  && !(Math.abs((var71 - var34) / var87) > Double.longBitsToDouble(4681608360884174848L))) {
                  ArrayList var13 = new ArrayList();

                  for (double var14 = var34;
                     var87 > Double.longBitsToDouble(0L)
                        ? var14 <= var71 + Double.longBitsToDouble(4472406533629990549L)
                        : var14 >= var71 - Double.longBitsToDouble(4472406533629990549L);
                     var14 += var87
                  ) {
                     var13.add(var14);
                  }

                  return var13;
               }

               throw new ScriptSyntaxException(
                  "This range is too big", var3
               );
            case 45:
               double var33 = Double.longBitsToDouble(0L);

               for (Object var82 : Xqedii_2juewiy(var2, 0, var3)) {
                  var33 += Xqedii_z4wg071.Xqedii_3c9epia(var82, var3);
               }

               return var33;
            case 46:
               List var32 = Xqedii_2juewiy(var2, 0, var3);
               double var59 = Double.longBitsToDouble(0L);

               for (Object var11 : var32) {
                  var59 += Xqedii_z4wg071.Xqedii_3c9epia(var11, var3);
               }

               return var32.isEmpty() ? Double.longBitsToDouble(0L) : var59 / var32.size();
            case 47:
               ArrayList var31 = new ArrayList<>(Xqedii_2juewiy(var2, 0, var3));
               var31.sort((var1x, var2x) -> {
                  {
                     return Xqedii_z4wg071.Xqedii_eqxwxh9(var1x, var2x, var3);
                  }
               });
               return var31;
            case 48:
               if (Xqedii_2d07ocz.Xqedii_1k661t2(var2, 0, var3) instanceof String var58) {
                  return new StringBuilder(var58).reverse().toString();
               }

               ArrayList var57 = new ArrayList<>(Xqedii_2juewiy(var2, 0, var3));
               Collections.reverse(var57);
               return var57;
            case 49:
               ArrayList var29 = new ArrayList<>(Xqedii_2juewiy(var2, 0, var3));
               Collections.shuffle(var29);
               return var29;
            case 50:
            case 51:
               Object var28 = Xqedii_2d07ocz.Xqedii_1k661t2(var2, 0, var3);
               if (var28 instanceof Map var56) {
                  return new ArrayList(
                     (Collection)(var4.equals("keys")
                        ? var56.keySet()
                        : var56.values())
                  );
               }

               throw new ScriptSyntaxException(Xqedii_1fsc2mwflwvoe(Xqedii_z4wg071.Xqedii_yl2qf35(var28)), var3);
            case 52:
            case 53:
               return (double)System.currentTimeMillis();
            case 54:
               String var27 = var2.length > 0
                  ? Xqedii_2coa7to(var2, 0, var3)
                  : "yyyy-MM-dd HH:mm";

               try {
                  return new SimpleDateFormat(var27).format(new Date());
               } catch (IllegalArgumentException var17) {
                  throw new ScriptSyntaxException(Xqedii_d6634fmwsb2i(var27), var3);
               }
            case 55:
               return (double)Xqedii_38o2zes.Xqedii_316oplq();
            case 56:
               Player var26 = var2.length > 1
                  ? Xqedii_2snbogb(var2[1], var3)
                  : (var0 == null ? null : var0.Xqedii_3a9kp91.Xqedii_1v4ue4a);
               String var55 = Xqedii_2coa7to(var2, 0, var3);
               return var0 == null ? var55 : var0.Xqedii_3a9kp91.Xqedii_2fi2lg1.Xqedii_14ikmfz(var26, var55);
            case 57:
               return new UiElementId(
                  UiElementId.idOf(Xqedii_2d07ocz.Xqedii_1k661t2(var2, 0, var3), var3)
               );
            case 58:
               return Boolean.valueOf(
                  ((var0 != null
                        && var0.Xqedii_3a9kp91.Xqedii_1v4ue4a != null
                        && Xqedii_2d07ocz.api(var3)
                           .hasElement(
                              var0.Xqedii_3a9kp91.Xqedii_1v4ue4a,
                              UiElementId.idOf(Xqedii_2d07ocz.Xqedii_1k661t2(var2, 0, var3), var3)
                           )
                     ? 1
                     : 0) != 0)
               );
            case 59:
               ArrayList var25 = new ArrayList();
               if (var0 != null && var0.Xqedii_3a9kp91.Xqedii_1v4ue4a != null) {
                  var0.Xqedii_3a9kp91.Xqedii_3u9m9cl();

                  for (String var69 : Xqedii_2d07ocz.api(var3).getElementIds(var0.Xqedii_3a9kp91.Xqedii_1v4ue4a)) {
                     var25.add(new UiElementId(var69));
                  }
               }

               return var25;
            case 60:
               Player var24 = var2.length > 1
                  ? Xqedii_2snbogb(var2[1], var3)
                  : (var0 == null ? null : var0.Xqedii_3a9kp91.Xqedii_1v4ue4a);
               if (var24 == null) {
                  return Boolean.valueOf(false);
               }

               return var2.length == 0
                  ? Xqedii_2d07ocz.api(var3).isGuiOpen(var24)
                  : Xqedii_2d07ocz.api(var3).isGuiOpen(var24, Xqedii_2coa7to(var2, 0, var3));
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
               Player var23 = var2.length > 0
                  ? Xqedii_2snbogb(var2[0], var3)
                  : (var0 == null ? null : var0.Xqedii_3a9kp91.Xqedii_1v4ue4a);
               if (var23 != null) {
                  if (var0 != null) {
                     var0.Xqedii_3a9kp91.Xqedii_3u9m9cl();
                  }

                  UltimateUIAPI var53 = Xqedii_2d07ocz.api(var3);
                  int var10 = -1;
                  switch (var4.hashCode()) {
                     case -1699012487:
                        if (var4.equals("isdragging")) {
                           var10 = 0;
                        }
                        break;
                     case -858798729:
                        if (var4.equals("typing")) {
                           var10 = 3;
                        }
                        break;
                     case 586953050:
                        if (var4.equals("mouseheld")) {
                           var10 = 2;
                        }
                        break;
                     case 1912530322:
                        if (var4.equals("dragged")) {
                           var10 = 1;
                        }
                  }
                  return switch (var10) {
                     case 0 -> var53.isDragging(var23);
                     case 1 -> Xqedii_3gttr8f(var53.getDraggedElement(var23));
                     case 2 -> var53.isLeftMouseHeld(var23);
                     case 3 -> var53.isTypingIntoInput(var23);
                     default -> Xqedii_3gttr8f(var53.getFocusedInput(var23));
                  };
               }

               return !var4.equals("dragged")
                     && !var4.equals("focusedinput")
                  ? Boolean.valueOf(false)
                  : "";
            case 66:
               return new ArrayList<>(Xqedii_2d07ocz.api(var3).listGuis());
            case 67:
               return Xqedii_2d07ocz.api(var3).guiExists(Xqedii_2coa7to(var2, 0, var3));
            case 68:
            case 69:
               if (var0 != null && var0.Xqedii_3a9kp91.Xqedii_1v4ue4a != null) {
                  var0.Xqedii_3a9kp91.Xqedii_3u9m9cl();
                  String var22 = UiElementId.idOf(Xqedii_2d07ocz.Xqedii_1k661t2(var2, 0, var3), var3);
                  if (var4.equals("scrollable")) {
                     return Xqedii_2d07ocz.api(var3).isScrollable(var0.Xqedii_3a9kp91.Xqedii_1v4ue4a, var22);
                  }

                  String var52 = Xqedii_2d07ocz.api(var3).getListContainer(var0.Xqedii_3a9kp91.Xqedii_1v4ue4a, var22);
                  return var52 != null && !var52.isEmpty() ? new UiElementId(var52) : null;
               }

               return var4.equals("scrollable")
                  ? Boolean.valueOf(false)
                  : null;
            case 70:
               Player var21 = var2.length > 1
                  ? Xqedii_2snbogb(var2[1], var3)
                  : (var0 == null ? null : var0.Xqedii_3a9kp91.Xqedii_25ge4f1(var3));
               if (var21 == null) {
                  return Boolean.valueOf(false);
               }

               if (var0 != null) {
                  var0.Xqedii_3a9kp91.Xqedii_3u9m9cl();
               }

               return Xqedii_2d07ocz.api(var3).pressKey(var21, Xqedii_2coa7to(var2, 0, var3));
            case 71:
               String var20 = Xqedii_2coa7to(var2, 0, var3).trim();
               if (var20.isEmpty()) {
                  throw new ScriptSyntaxException(
                     "newpage needs a page name, like: newpage(\"menu\")",
                     var3
                  );
               }

               UltimateUIAPI var51 = Xqedii_2d07ocz.api(var3);
               return Boolean.valueOf(
                  ((!var51.guiExists(var20) && !var51.saveGui(var51.createGui(var20))
                     ? 0
                     : 1) != 0)
               );
            case 72:
               Player var19 = var2.length > 0
                  ? Xqedii_2snbogb(var2[0], var3)
                  : (var0 == null ? null : var0.Xqedii_3a9kp91.Xqedii_1v4ue4a);
               String var50 = var19 == null ? null : Xqedii_2d07ocz.api(var3).getOpenGuiName(var19);
               return var50 == null ? "" : var50;
            case 73:
               return Bukkit.getPlayerExact(Xqedii_2coa7to(var2, 0, var3));
            case 74:
               return (double)Bukkit.getOnlinePlayers().size();
            case 75:
               int var18 = (int)Math.max(
                  0L,
                  Math.min(
                     255L,
                     Math.round(Xqedii_3a2hke1(var2, 0, var3))
                  )
               );
               int var8 = (int)Math.max(
                  0L,
                  Math.min(
                     255L,
                     Math.round(Xqedii_3a2hke1(var2, 1, var3))
                  )
               );
               int var9 = (int)Math.max(
                  0L,
                  Math.min(
                     255L,
                     Math.round(Xqedii_3a2hke1(var2, 2, var3))
                  )
               );
               Locale var10000 = Locale.ROOT;
               String var10001 = "#%02x%02x%02x";
               Object[] var10002 = new Object[3];
               var10002[0] = var18;
               var10002[1] = var8;
               var10002[2] = var9;
               return String.format(var10000, var10001, var10002);
            case 76:
               String var7 = Xqedii_2coa7to(var2, 0, var3).trim();
               return var7.startsWith("#")
                  ? var7.toLowerCase(Locale.ROOT)
                  : Xqedii_2x4lm6pt0u6ql(var7.toLowerCase(Locale.ROOT));
            case 77:
               return Xqedii_z4wg071.Xqedii_28xo6uq(var2.length == 0 ? null : var2[0]);
            default:
               throw new ScriptSyntaxException(Xqedii_4pic1rqvmrfr(var1), var3);
         }
      }
   }

   public static String Xqedii_3gttr8f(String var0) {
      {
         return var0 == null ? "" : var0;
      }
   }

   public static Player Xqedii_2snbogb(Object var0, int var1) {
      if (var0 instanceof Player var3) {
         return var3;
      } else {
         Player var2 = Bukkit.getPlayerExact(Xqedii_z4wg071.Xqedii_2mlipa7(var0));
         if (var2 == null) {
            throw new ScriptSyntaxException(Xqedii_2nemum0fnv7jx(Xqedii_z4wg071.Xqedii_2mlipa7(var0)), var1);
         } else {
            return var2;
         }
      }
   }

   static {
      {
         String[] var10000 = new String[78];
         var10000[0] = "min";
         var10000[1] = "max";
         var10000[2] = "clamp";
         var10000[3] = "abs";
         var10000[4] = "round";
         var10000[5] = "floor";
         var10000[6] = "ceil";
         var10000[7] = "sqrt";
         var10000[8] = "pow";
         var10000[9] = "sin";
         var10000[10] = "cos";
         var10000[11] = "tan";
         var10000[12] = "atan2";
         var10000[13] = "sign";
         var10000[14] = "random";
         var10000[15] = "chance";
         var10000[16] = "lerp";
         var10000[17] = "remap";
         var10000[18] = "text";
         var10000[19] = "str";
         var10000[20] = "number";
         var10000[21] = "num";
         var10000[22] = "int";
         var10000[23] = "bool";
         var10000[24] = "length";
         var10000[25] = "len";
         var10000[26] = "upper";
         var10000[27] = "lower";
         var10000[28] = "trim";
         var10000[29] = "replace";
         var10000[30] = "split";
         var10000[31] = "join";
         var10000[32] = "contains";
         var10000[33] = "startswith";
         var10000[34] = "endswith";
         var10000[35] = "substring";
         var10000[36] = "indexof";
         var10000[37] = "format";
         var10000[38] = "pad";
         var10000[39] = "list";
         var10000[40] = "range";
         var10000[41] = "sum";
         var10000[42] = "average";
         var10000[43] = "sort";
         var10000[44] = "reverse";
         var10000[45] = "shuffle";
         var10000[46] = "keys";
         var10000[47] = "values";
         var10000[48] = "time";
         var10000[49] = "ticks";
         var10000[50] = "placeholder";
         var10000[51] = "element";
         var10000[52] = "exists";
         var10000[53] = "elements";
         var10000[54] = "isopen";
         var10000[55] = "player";
         var10000[56] = "rgb";
         var10000[57] = "hex";
         var10000[58] = "type";
         var10000[59] = "percent";
         var10000[60] = "capitalize";
         var10000[61] = "repeat";
         var10000[62] = "isnumber";
         var10000[63] = "now";
         var10000[64] = "date";
         var10000[65] = "online";
         var10000[66] = "openpage";
         var10000[67] = "isdragging";
         var10000[68] = "dragged";
         var10000[69] = "mouseheld";
         var10000[70] = "typing";
         var10000[71] = "focusedinput";
         var10000[72] = "pages";
         var10000[73] = "pageexists";
         var10000[74] = "scrollable";
         var10000[75] = "listof";
         var10000[76] = "presskey";
         var10000[77] = "newpage";
         Xqedii_1cqvf8d = Set.of(var10000);
      }
   }

   private static /* synthetic */ String Xqedii_18d8jher4vgib(String string) {
        return "Expected a list but got " + string;
    }

    private static /* synthetic */ String Xqedii_1gxx04hq45d94(String string, String string2) {
        return string + string2;
    }

    private static /* synthetic */ String Xqedii_r7ex55vboi06(String string) {
        return "." + string;
    }

    private static /* synthetic */ String Xqedii_1jj4qydus8kzk(String string, String string2, String string3) {
        return string + string2 + string3;
    }

    private static /* synthetic */ String Xqedii_3ul6rh2pto9k9(String string, String string2) {
        return string + string2;
    }

    private static /* synthetic */ String Xqedii_1fsc2mwflwvoe(String string) {
        return "Expected an object but got " + string;
    }

    private static /* synthetic */ String Xqedii_d6634fmwsb2i(String string) {
        return "Invalid date pattern " + string;
    }

    private static /* synthetic */ String Xqedii_2x4lm6pt0u6ql(String string) {
        return "#" + string;
    }

    private static /* synthetic */ String Xqedii_4pic1rqvmrfr(String string) {
        return "Unknown function " + string + "()";
    }

    private static /* synthetic */ String Xqedii_2nemum0fnv7jx(String string) {
        return "The player " + string + " is not online";
    }
}
