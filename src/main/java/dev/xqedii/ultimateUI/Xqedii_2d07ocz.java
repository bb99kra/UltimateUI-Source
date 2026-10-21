package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.api.UiElementCreate;
import dev.xqedii.ultimateUI.api.UiElementWrite;
import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import dev.xqedii.ultimateUI.api.event.UltimateUIInputSubmitEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public final class Xqedii_2d07ocz {
   public static final Set<String> Xqedii_2pbhzqx;
   public static final String Xqedii_2uy90gt = "\ue67b";
   public static final Set<String> Xqedii_1g7vacg;

   public static UltimateUIAPI api(int var0) {
      if (!UltimateUIAPI.isAvailable()) {
         throw new ScriptSyntaxException(
            "UltimateUI is not ready yet",
            var0
         );
      } else {
         return UltimateUIAPI.get();
      }
   }

   public static boolean Xqedii_2b8p7zx(String var0) {
      {
         return Xqedii_2pbhzqx.contains(var0);
      }
   }

   public static Object Xqedii_2pszqr1(ScriptExecutionContext var0, String var1, int var2) {
      {
         ScriptScopeContext var3 = var0.Xqedii_3a9kp91;
         int var5 = -1;
         switch (var1.hashCode()) {
            case -1349119146:
               if (var1.equals("cursor")) {
                  var5 = 3;
               }
               break;
            case -1243020381:
               if (var1.equals("global")) {
                  var5 = 6;
               }
               break;
            case -985752863:
               if (var1.equals("player")) {
                  var5 = 0;
               }
               break;
            case -905962955:
               if (var1.equals("sender")) {
                  var5 = 10;
               }
               break;
            case -905826493:
               if (var1.equals("server")) {
                  var5 = 9;
               }
               break;
            case -493567566:
               if (var1.equals("players")) {
                  var5 = 8;
               }
               break;
            case 3577:
               if (var1.equals("pi")) {
                  var5 = 11;
               }
               break;
            case 3002589:
               if (var1.equals("args")) {
                  var5 = 7;
               }
               break;
            case 3076010:
               if (var1.equals("data")) {
                  var5 = 5;
               }
               break;
            case 3433103:
               if (var1.equals("page")) {
                  var5 = 2;
               }
               break;
            case 3556308:
               if (var1.equals("temp")) {
                  var5 = 4;
               }
               break;
            case 96891546:
               if (var1.equals("event")) {
                  var5 = 1;
               }
         }

         switch (var5) {
            case 0:
               return var3.Xqedii_1v4ue4a;
            case 1:
               if (var3.Xqedii_34yybpr == null) {
                  throw new ScriptSyntaxException(
                     "There is no event here",
                     var2
                  );
               }

               return var3.Xqedii_34yybpr;
            case 2:
               return var3.Xqedii_1r9cm9n();
            case 3:
               Player var9 = var3.Xqedii_25ge4f1(var2);
               LinkedHashMap var10 = new LinkedHashMap();
               var10.put("x", api(var2).getCursorPageX(var9));
               var10.put("y", api(var2).getCursorPageY(var9));
               var10.put(
                  "hovered",
                  Xqedii_2e6m2vk(api(var2).getHoveredElement(var9))
               );
               return var10;
            case 4:
               return var3.Xqedii_2fi2lg1.Xqedii_1u89z52(var3.Xqedii_25ge4f1(var2));
            case 5:
               return var3.Xqedii_2fi2lg1.Xqedii_nm0vadd(var3.Xqedii_25ge4f1(var2));
            case 6:
               return var3.Xqedii_2fi2lg1.Xqedii_2e6s6rg();
            case 7:
               return var3.Xqedii_1v9t8lx == null ? new ArrayList() : var3.Xqedii_1v9t8lx;
            case 8:
               return new ArrayList(Bukkit.getOnlinePlayers());
            case 9:
               LinkedHashMap var8 = new LinkedHashMap();
               var8.put(
                  "online", (double)Bukkit.getOnlinePlayers().size()
               );
               var8.put("max", (double)Bukkit.getMaxPlayers());
               var8.put("version", Bukkit.getBukkitVersion());
               var8.put("ticks", (double)Xqedii_38o2zes.Xqedii_316oplq());
               return var8;
            case 10:
               return var3.Xqedii_3cp8md6 instanceof Player var6 ? var6 : (var3.Xqedii_3cp8md6 == null ? null : var3.Xqedii_3cp8md6.getName());
            case 11:
               return Double.longBitsToDouble(4614256656552045848L);
            default:
               throw new ScriptSyntaxException(Xqedii_32wj88dr8h8rr(var1, var1), var2);
         }
      }
   }

   public static String Xqedii_2e6m2vk(String var0) {
      {
         return var0 == null ? "" : var0;
      }
   }

   public static Object Xqedii_207muaz(Object var0) {
      {
         return var0 instanceof Number var1 && !(var0 instanceof Double) ? var1.doubleValue() : var0;
      }
   }

   public static Object get(ScriptExecutionContext var0, Object var1, String var2, int var3) {
      {
         String var4 = var2.toLowerCase(Locale.ROOT);
         if (var1 instanceof UiElementId var12) {
            return Xqedii_1javj6e(var0, var12.id(), var2, var3);
         } else if (!(var1 instanceof Map var5)) {
            if (var1 instanceof List var11) {
               int var13 = -1;
               switch (var4.hashCode()) {
                  case -1106363674:
                     if (var4.equals("length")) {
                        var13 = 1;
                     }
                     break;
                  case 3314326:
                     if (var4.equals("last")) {
                        var13 = 4;
                     }
                     break;
                  case 3530753:
                     if (var4.equals("size")) {
                        var13 = 0;
                     }
                     break;
                  case 94851343:
                     if (var4.equals("count")) {
                        var13 = 2;
                     }
                     break;
                  case 96634189:
                     if (var4.equals("empty")) {
                        var13 = 5;
                     }
                     break;
                  case 97440432:
                     if (var4.equals("first")) {
                        var13 = 3;
                     }
                     break;
                  case 2087592547:
                     if (var4.equals("isempty")) {
                        var13 = 6;
                     }
               }
               return switch (var13) {
                  case 0, 1, 2 -> (double)var11.size();
                  case 3 -> var11.isEmpty() ? null : var11.get(0);
                  case 4 -> var11.isEmpty() ? null : var11.get(var11.size() - 1);
                  case 5, 6 -> var11.isEmpty();
                  default -> throw new ScriptSyntaxException(Xqedii_2k9w5y9stitdd(var2), var3);
               };
            } else if (var1 instanceof String var10) {
               int var7 = -1;
               switch (var4.hashCode()) {
                  case -1106363674:
                     if (var4.equals("length")) {
                        var7 = 0;
                     }
                     break;
                  case 3530753:
                     if (var4.equals("size")) {
                        var7 = 1;
                     }
                     break;
                  case 96634189:
                     if (var4.equals("empty")) {
                        var7 = 2;
                     }
                     break;
                  case 103164673:
                     if (var4.equals("lower")) {
                        var7 = 5;
                     }
                     break;
                  case 111499426:
                     if (var4.equals("upper")) {
                        var7 = 4;
                     }
                     break;
                  case 2087592547:
                     if (var4.equals("isempty")) {
                        var7 = 3;
                     }
               }
               return switch (var7) {
                  case 0, 1 -> (double)var10.length();
                  case 2, 3 -> var10.isEmpty();
                  case 4 -> var10.toUpperCase(Locale.ROOT);
                  case 5 -> var10.toLowerCase(Locale.ROOT);
                  default -> Xqedii_1javj6e(var0, var10, var2, var3);
               };
            } else if (var1 instanceof Player var9) {
               return Xqedii_266bjwx(var0, var9, var4, var3);
            } else if (var1 instanceof ScriptEventContext var8) {
               return Xqedii_2j653hv(var8, var4, var3);
            } else if (var1 == null) {
               throw new ScriptSyntaxException(Xqedii_3en6apxrvep60(var2), var3);
            } else {
               throw new ScriptSyntaxException(Xqedii_27orwbso07q2y(Xqedii_z4wg071.Xqedii_28xo6uq(var1), var2), var3);
            }
         } else {
            Object var6 = var5.get(var2);
            if (var6 == null && !var5.containsKey(var2)) {
               if (var4.equals("size")
                  || var4.equals("length")) {
                  return (double)var5.size();
               }

               if (var4.equals("keys")) {
                  return new ArrayList(var5.keySet());
               }

               if (var4.equals("values")) {
                  return new ArrayList(var5.values());
               }
            }

            return var6;
         }
      }
   }

   public static void Xqedii_2a9tfw6(ScriptExecutionContext var0, Object var1, String var2, Object var3, int var4) {
      if (var1 instanceof UiElementId var12) {
         Xqedii_3j2drjj(var0, var12.id(), var2, var3, var4);
      } else if (var1 instanceof Map var11) {
         var11.put(var2, var3);
      } else if (var1 instanceof String var10) {
         Xqedii_3j2drjj(var0, var10, var2, var3, var4);
      } else if (var1 instanceof Player var9) {
         Xqedii_23nr37w(var9, var2.toLowerCase(Locale.ROOT), var3, var4);
      } else {
         if (var1 instanceof ScriptEventContext var5) {
            String var6 = var2.toLowerCase(Locale.ROOT);
            if ((
                  var6.equals("text")
                     || var6.equals("value")
               )
               && var5.Xqedii_11wuen3 instanceof UltimateUIInputSubmitEvent var7) {
               var7.setValue(Xqedii_z4wg071.Xqedii_2mlipa7(var3));
               var5.Xqedii_a9in0yy = Xqedii_z4wg071.Xqedii_2mlipa7(var3);
               return;
            }

            if (var6.equals("cancelled")) {
               var5.cancel(Xqedii_z4wg071.Xqedii_2od1ceu(var3));
               return;
            }
         }

         throw new ScriptSyntaxException(Xqedii_13qgd86wp1cq2(var2, Xqedii_z4wg071.Xqedii_28xo6uq(var1)), var4);
      }
   }

   public static Object Xqedii_1sr2hld(ScriptExecutionContext var0, Object var1, String var2, Object[] var3, int var4) {
      {
         String var5 = var2.toLowerCase(Locale.ROOT);
         if (var1 instanceof UiElementId var15) {
            return Xqedii_200p8tk(var0, var15.id(), var5, var3, var4);
         } else if (var1 instanceof String var14) {
            return Xqedii_2q74yw2(var0, var14, var5, var3, var4);
         } else if (var1 instanceof List var13) {
            return Xqedii_306mg5g(var13, var5, var3, var4);
         } else {
            if (var1 instanceof Map var6) {
               int var9 = -1;
               switch (var5.hashCode()) {
                  case -1335458389:
                     if (var5.equals("delete")) {
                        var9 = 5;
                     }
                     break;
                  case -934610812:
                     if (var5.equals("remove")) {
                        var9 = 4;
                     }
                     break;
                  case -823812830:
                     if (var5.equals("values")) {
                        var9 = 8;
                     }
                     break;
                  case -567445985:
                     if (var5.equals("contains")) {
                        var9 = 1;
                     }
                     break;
                  case 102230:
                     if (var5.equals("get")) {
                        var9 = 2;
                     }
                     break;
                  case 103066:
                     if (var5.equals("has")) {
                        var9 = 0;
                     }
                     break;
                  case 113762:
                     if (var5.equals("set")) {
                        var9 = 3;
                     }
                     break;
                  case 3288564:
                     if (var5.equals("keys")) {
                        var9 = 7;
                     }
                     break;
                  case 3530753:
                     if (var5.equals("size")) {
                        var9 = 9;
                     }
                     break;
                  case 94746189:
                     if (var5.equals("clear")) {
                        var9 = 6;
                     }
               }

               switch (var9) {
                  case 0:
                  case 1:
                     return var6.containsKey(Xqedii_1k661t2(var3, 0, var4));
                  case 2:
                     return var6.getOrDefault(
                        Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_1k661t2(var3, 0, var4)),
                        var3.length > 1 ? var3[1] : null
                     );
                  case 3:
                     var6.put(
                        Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_1k661t2(var3, 0, var4)),
                        var3.length > 1 ? var3[1] : null
                     );
                     return null;
                  case 4:
                  case 5:
                     return var6.remove(Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_1k661t2(var3, 0, var4)));
                  case 6:
                     var6.clear();
                     return null;
                  case 7:
                     return new ArrayList(var6.keySet());
                  case 8:
                     return new ArrayList(var6.values());
                  case 9:
                     return (double)var6.size();
               }
            }

            if (var1 instanceof Number var12) {
               return Xqedii_1iw7g71.Xqedii_2xeqrwc(var0, var5, Xqedii_4a5wk96(var12, var3), var4);
            } else {
               if (var1 instanceof Player var10) {
                  int var8 = -1;
                  switch (var5.hashCode()) {
                     case -1821614103:
                        if (var5.equals("haspermission")) {
                           var8 = 0;
                        }
                        break;
                     case 98256:
                        if (var5.equals("can")) {
                           var8 = 2;
                        }
                        break;
                     case 103066:
                        if (var5.equals("has")) {
                           var8 = 1;
                        }
                        break;
                     case 3417674:
                        if (var5.equals("open")) {
                           var8 = 6;
                        }
                        break;
                     case 3526536:
                        if (var5.equals("send")) {
                           var8 = 3;
                        }
                        break;
                     case 94756344:
                        if (var5.equals("close")) {
                           var8 = 7;
                        }
                        break;
                     case 198298141:
                        if (var5.equals("actionbar")) {
                           var8 = 5;
                        }
                        break;
                     case 954925063:
                        if (var5.equals("message")) {
                           var8 = 4;
                        }
                  }

                  switch (var8) {
                     case 0:
                     case 1:
                     case 2:
                        return var10.hasPermission(
                           Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_1k661t2(var3, 0, var4))
                        );
                     case 3:
                     case 4:
                        Xqedii_2ol0jxi(var10, Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_1k661t2(var3, 0, var4)));
                        return null;
                     case 5:
                        Xqedii_nd6rkr3.Xqedii_2n2p2n9(
                           var10,
                           Xqedii_14rq758.Xqedii_g673f8i(
                              Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_1k661t2(var3, 0, var4))
                           )
                        );
                        return null;
                     case 6:
                        api(var4)
                           .openGui(var10, Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_1k661t2(var3, 0, var4)));
                        return null;
                     case 7:
                        api(var4).closeGui(var10);
                        return null;
                  }
               }

               if (var1 instanceof ScriptEventContext var11) {
                  if (var5.equals("cancel")) {
                     var0.Xqedii_3a9kp91.Xqedii_3dy3w3c = true;
                     var11.cancel(true);
                     return null;
                  }

                  if (var5.equals("uncancel")) {
                     var0.Xqedii_3a9kp91.Xqedii_3dy3w3c = false;
                     var11.cancel(false);
                     return null;
                  }
               }

               if (var1 == null) {
                  throw new ScriptSyntaxException(Xqedii_1l750eeybvls4(var2), var4);
               } else {
                  throw new ScriptSyntaxException(Xqedii_ej4oup67z9in(Xqedii_z4wg071.Xqedii_28xo6uq(var1), var2), var4);
               }
            }
         }
      }
   }

   public static Object[] Xqedii_4a5wk96(Object var0, Object[] var1) {
      {
         Object[] var2 = new Object[var1.length + 1];
         var2[0] = var0;
         System.arraycopy(var1, 0, var2, 1, var1.length);
         return var2;
      }
   }

   public static Object Xqedii_1k661t2(Object[] var0, int var1, int var2) {
      if (var1 >= var0.length) {
         throw new ScriptSyntaxException(Xqedii_b0t15qhwpot6(var1 + 1), var2);
      } else {
         return var0[var1];
      }
   }

   public static Object Xqedii_1javj6e(ScriptExecutionContext var0, String var1, String var2, int var3) {
      {
         ScriptScopeContext var4 = var0.Xqedii_3a9kp91;
         Player var5 = var4.Xqedii_25ge4f1(var3);
         var4.Xqedii_3u9m9cl();
         UltimateUIAPI var6 = api(var3);
         String var7 = var2.toLowerCase(Locale.ROOT);
         int var9 = -1;
         switch (var7.hashCode()) {
            case -1289358244:
               if (var7.equals("exists")) {
                  var9 = 1;
               }
               break;
            case -1221029593:
               if (var7.equals("height")) {
                  var9 = 11;
               }
               break;
            case -1217487446:
               if (var7.equals("hidden")) {
                  var9 = 15;
               }
               break;
            case -995424086:
               if (var7.equals("parent")) {
                  var9 = 18;
               }
               break;
            case -304480883:
               if (var7.equals("draggable")) {
                  var9 = 16;
               }
               break;
            case 120:
               if (var7.equals("x")) {
                  var9 = 3;
               }
               break;
            case 121:
               if (var7.equals("y")) {
                  var9 = 4;
               }
               break;
            case 3355:
               if (var7.equals("id")) {
                  var9 = 0;
               }
               break;
            case 2987590:
               if (var7.equals("absx")) {
                  var9 = 6;
               }
               break;
            case 2987591:
               if (var7.equals("absy")) {
                  var9 = 8;
               }
               break;
            case 3530753:
               if (var7.equals("size")) {
                  var9 = 12;
               }
               break;
            case 3556653:
               if (var7.equals("text")) {
                  var9 = 2;
               }
               break;
            case 100346066:
               if (var7.equals("index")) {
                  var9 = 20;
               }
               break;
            case 100358090:
               if (var7.equals("input")) {
                  var9 = 21;
               }
               break;
            case 106426313:
               if (var7.equals("pagex")) {
                  var9 = 7;
               }
               break;
            case 106426314:
               if (var7.equals("pagey")) {
                  var9 = 9;
               }
               break;
            case 109413649:
               if (var7.equals("shown")) {
                  var9 = 14;
               }
               break;
            case 111972721:
               if (var7.equals("value")) {
                  var9 = 22;
               }
               break;
            case 113126854:
               if (var7.equals("width")) {
                  var9 = 10;
               }
               break;
            case 466743410:
               if (var7.equals("visible")) {
                  var9 = 13;
               }
               break;
            case 747804969:
               if (var7.equals("position")) {
                  var9 = 5;
               }
               break;
            case 1101035227:
               if (var7.equals("hovered")) {
                  var9 = 17;
               }
               break;
            case 1435972967:
               if (var7.equals("maxscrollx")) {
                  var9 = 25;
               }
               break;
            case 1435972968:
               if (var7.equals("maxscrolly")) {
                  var9 = 26;
               }
               break;
            case 1659526655:
               if (var7.equals("children")) {
                  var9 = 19;
               }
               break;
            case 1926689611:
               if (var7.equals("scrollx")) {
                  var9 = 23;
               }
               break;
            case 1926689612:
               if (var7.equals("scrolly")) {
                  var9 = 24;
               }
         }

         switch (var9) {
            case 0:
               return var1;
            case 1:
               return var6.hasElement(var5, var1);
            case 2:
               return Xqedii_2e6m2vk(var6.getElementText(var5, var1));
            case 3:
            case 4:
               double[] var20 = var6.getElementPosition(var5, var1);
               return var20 == null
                  ? null
                  : var20[var7.equals("x")
                     ? 0
                     : 1];
            case 5:
               double[] var19 = var6.getElementPosition(var5, var1);
               return var19 == null
                  ? null
                  : new ArrayList<>(
                     List.of(var19[0], var19[1])
                  );
            case 6:
            case 7:
            case 8:
            case 9:
               double[] var18 = var6.getElementAbsolutePosition(var5, var1);
               return var18 == null
                  ? null
                  : var18[var7.endsWith("x")
                     ? 0
                     : 1];
            case 10:
            case 11:
               double[] var17 = var6.getElementSize(var5, var1);
               return var17 == null
                  ? null
                  : var17[var7.equals("width")
                     ? 0
                     : 1];
            case 12:
               double[] var16 = var6.getElementSize(var5, var1);
               return var16 == null
                  ? null
                  : new ArrayList<>(
                     List.of(var16[0], var16[1])
                  );
            case 13:
            case 14:
               return var6.isElementVisible(var5, var1);
            case 15:
               return Boolean.valueOf(
                  ((!var6.isElementVisible(var5, var1)
                     ? 1
                     : 0) != 0)
               );
            case 16:
               return var6.isElementDraggable(var5, var1);
            case 17:
               return var6.isHovering(var5, var1);
            case 18:
               String var15 = var6.getElementParent(var5, var1);
               return var15 != null && !var15.isEmpty() ? new UiElementId(var15) : null;
            case 19:
               ArrayList var14 = new ArrayList();

               for (String var12 : var6.getElementChildren(var5, var1)) {
                  var14.add(new UiElementId(var12));
               }

               return var14;
            case 20:
               return (double)var6.getElementIndex(var5, var1);
            case 21:
            case 22:
               return Xqedii_2e6m2vk(var6.getInputValue(var5, var1));
            case 23:
            case 24:
            case 25:
            case 26:
               double[] var10 = var6.getScroll(var5, var1);
               if (var10 == null) {
                  return Double.longBitsToDouble(0L);
               }

               int var13 = -1;
               switch (var7.hashCode()) {
                  case 1435972967:
                     if (var7.equals("maxscrollx")) {
                        var13 = 2;
                     }
                     break;
                  case 1926689611:
                     if (var7.equals("scrollx")) {
                        var13 = 0;
                     }
                     break;
                  case 1926689612:
                     if (var7.equals("scrolly")) {
                        var13 = 1;
                     }
               }
               int var11 = switch (var13) {
                  case 0 -> 0;
                  case 1 -> 1;
                  case 2 -> 2;
                  default -> 3;
               };
               return var10[var11];
            default:
               return Xqedii_207muaz(var6.getElementValue(var5, var1, var2));
         }
      }
   }

   public static void Xqedii_3j2drjj(ScriptExecutionContext var0, String var1, String var2, Object var3, int var4) {
      {
         ScriptScopeContext var5 = var0.Xqedii_3a9kp91;
         Player var6 = var5.Xqedii_25ge4f1(var4);
         String var7 = var2.toLowerCase(Locale.ROOT);
         if (AnimationEasingFunctions.Xqedii_1wy8z23.contains(var7)) {
            String[] var10002 = new String[1];
            var10002[0] = var7;
            Xqedii_1qsxv8z(var5, var1, var10002);
         } else if (var7.equals("position")) {
            String[] var20 = new String[2];
            var20[0] = "x";
            var20[1] = "y";
            Xqedii_1qsxv8z(var5, var1, var20);
         } else if (var7.equals("size")) {
            String[] var21 = new String[2];
            var21[0] = "width";
            var21[1] = "height";
            Xqedii_1qsxv8z(var5, var1, var21);
         }

         int var9 = -1;
         switch (var7.hashCode()) {
            case -1267206133:
               if (var7.equals("opacity")) {
                  var9 = 8;
               }
               break;
            case -1221029593:
               if (var7.equals("height")) {
                  var9 = 5;
               }
               break;
            case -1217487446:
               if (var7.equals("hidden")) {
                  var9 = 14;
               }
               break;
            case -304480883:
               if (var7.equals("draggable")) {
                  var9 = 15;
               }
               break;
            case -40300674:
               if (var7.equals("rotation")) {
                  var9 = 9;
               }
               break;
            case 120:
               if (var7.equals("x")) {
                  var9 = 2;
               }
               break;
            case 121:
               if (var7.equals("y")) {
                  var9 = 3;
               }
               break;
            case 3242771:
               if (var7.equals("item")) {
                  var9 = 11;
               }
               break;
            case 3530753:
               if (var7.equals("size")) {
                  var9 = 7;
               }
               break;
            case 3556653:
               if (var7.equals("text")) {
                  var9 = 0;
               }
               break;
            case 94842723:
               if (var7.equals("color")) {
                  var9 = 1;
               }
               break;
            case 100358090:
               if (var7.equals("input")) {
                  var9 = 16;
               }
               break;
            case 102749521:
               if (var7.equals("layer")) {
                  var9 = 10;
               }
               break;
            case 109413649:
               if (var7.equals("shown")) {
                  var9 = 13;
               }
               break;
            case 111972721:
               if (var7.equals("value")) {
                  var9 = 17;
               }
               break;
            case 113126854:
               if (var7.equals("width")) {
                  var9 = 4;
               }
               break;
            case 466743410:
               if (var7.equals("visible")) {
                  var9 = 12;
               }
               break;
            case 747804969:
               if (var7.equals("position")) {
                  var9 = 6;
               }
               break;
            case 1926689611:
               if (var7.equals("scrollx")) {
                  var9 = 18;
               }
               break;
            case 1926689612:
               if (var7.equals("scrolly")) {
                  var9 = 19;
               }
         }

         switch (var9) {
            case 0:
               var5.Xqedii_x5301uw(UiElementWrite.text(var1, Xqedii_z4wg071.Xqedii_2mlipa7(var3)));
               break;
            case 1:
               var5.Xqedii_x5301uw(UiElementWrite.color(var1, Xqedii_z4wg071.Xqedii_2mlipa7(var3)));
               break;
            case 2:
            case 3:
            case 4:
            case 5:
               var5.Xqedii_x5301uw(new UiElementWrite(var1, var7, Xqedii_z4wg071.Xqedii_3c9epia(var3, var4), null));
               break;
            case 6:
               double[] var19 = Xqedii_1qq2p2k(var3, var4);
               var5.Xqedii_x5301uw(
                  UiElementWrite.position(
                     var1, var19[0], var19[1]
                  )
               );
               break;
            case 7:
               double[] var18 = Xqedii_1qq2p2k(var3, var4);
               var5.Xqedii_x5301uw(
                  UiElementWrite.size(var1, var18[0], var18[1])
               );
               break;
            case 8:
               var5.Xqedii_x5301uw(
                  UiElementWrite.value(
                     var1,
                     "opacity",
                     (int)Math.max(
                        0L,
                        Math.min(255L, Math.round(Xqedii_z4wg071.Xqedii_3c9epia(var3, var4)))
                     )
                  )
               );
               break;
            case 9:
            case 10:
               var5.Xqedii_x5301uw(UiElementWrite.value(var1, var7, Xqedii_z4wg071.Xqedii_3c9epia(var3, var4)));
               break;
            case 11:
               var5.Xqedii_3u9m9cl();
               api(var4).setElementItem(var6, var1, Xqedii_z4wg071.Xqedii_2mlipa7(var3));
               break;
            case 12:
            case 13:
            case 14:
               var5.Xqedii_3u9m9cl();
               int var17 = Xqedii_z4wg071.Xqedii_2od1ceu(var3)
                     != var7.equals("hidden")
                  ? 1
                  : 0;
               api(var4).setElementVisible(var6, var1, (var17 != 0));
               break;
            case 15:
               var5.Xqedii_3u9m9cl();
               api(var4).setElementDraggable(var6, var1, Xqedii_z4wg071.Xqedii_2od1ceu(var3));
               break;
            case 16:
            case 17:
               var5.Xqedii_3u9m9cl();
               api(var4).setInputValue(var6, var1, Xqedii_z4wg071.Xqedii_2mlipa7(var3));
               break;
            case 18:
            case 19:
               var5.Xqedii_3u9m9cl();
               double[] var10 = api(var4).getScroll(var6, var1);
               double var11 = Xqedii_z4wg071.Xqedii_3c9epia(var3, var4);
               double var13 = var7.equals("scrollx")
                  ? var11
                  : (
                     var10 == null
                        ? Double.longBitsToDouble(0L)
                        : var10[0]
                  );
               double var15 = var7.equals("scrolly")
                  ? var11
                  : (
                     var10 == null
                        ? Double.longBitsToDouble(0L)
                        : var10[1]
                  );
               api(var4).setScroll(var6, var1, var13, var15);
               break;
            default:
               var5.Xqedii_x5301uw(UiElementWrite.value(var1, var2, Xqedii_tydjfwf(var3)));
         }
      }
   }

   public static Object Xqedii_tydjfwf(Object var0) {
      {
         return var0 instanceof Double var1
               && var1 == Math.rint(var1)
               && Math.abs(var1) < Double.longBitsToDouble(4741671816366391296L)
            ? (int)Math.round(var1)
            : Xqedii_z4wg071.Xqedii_3ujolw5(var0);
      }
   }

   public static double[] Xqedii_1qq2p2k(Object var0, int var1) {
      if (var0 instanceof List var2 && var2.size() >= 2) {
         double[] var10000 = new double[2];
         var10000[0] = Xqedii_z4wg071.Xqedii_3c9epia(
            var2.get(0), var1
         );
         var10000[1] = Xqedii_z4wg071.Xqedii_3c9epia(
            var2.get(1), var1
         );
         return var10000;
      } else {
         throw new ScriptSyntaxException(Xqedii_3eufpwv72dpmh(Xqedii_z4wg071.Xqedii_yl2qf35(var0)), var1);
      }
   }

   public static Object Xqedii_200p8tk(ScriptExecutionContext var0, String var1, String var2, Object[] var3, int var4) {
      {
         ScriptScopeContext var5 = var0.Xqedii_3a9kp91;
         int var7 = -1;
         switch (var2.hashCode()) {
            case -1335458389:
               if (var2.equals("delete")) {
                  var7 = 10;
               }
               break;
            case -1068263448:
               if (var2.equals("moveby")) {
                  var7 = 5;
               }
               break;
            case -1068262900:
               if (var2.equals("moveto")) {
                  var7 = 4;
               }
               break;
            case -934610812:
               if (var2.equals("remove")) {
                  var7 = 9;
               }
               break;
            case -934437708:
               if (var2.equals("resize")) {
                  var7 = 6;
               }
               break;
            case -868304044:
               if (var2.equals("toggle")) {
                  var7 = 2;
               }
               break;
            case -402164764:
               if (var2.equals("scrollby")) {
                  var7 = 11;
               }
               break;
            case -402164216:
               if (var2.equals("scrollto")) {
                  var7 = 12;
               }
               break;
            case -86611405:
               if (var2.equals("scrolltobottom")) {
                  var7 = 14;
               }
               break;
            case 102230:
               if (var2.equals("get")) {
                  var7 = 18;
               }
               break;
            case 113762:
               if (var2.equals("set")) {
                  var7 = 17;
               }
               break;
            case 3202370:
               if (var2.equals("hide")) {
                  var7 = 1;
               }
               break;
            case 3357649:
               if (var2.equals("move")) {
                  var7 = 3;
               }
               break;
            case 3529469:
               if (var2.equals("show")) {
                  var7 = 0;
               }
               break;
            case 94750088:
               if (var2.equals("click")) {
                  var7 = 7;
               }
               break;
            case 94756189:
               if (var2.equals("clone")) {
                  var7 = 8;
               }
               break;
            case 97604824:
               if (var2.equals("focus")) {
                  var7 = 16;
               }
               break;
            case 1555402688:
               if (var2.equals("openinput")) {
                  var7 = 15;
               }
               break;
            case 2084712013:
               if (var2.equals("scrolltotop")) {
                  var7 = 13;
               }
         }

         switch (var7) {
            case 0:
               int var13 = 1;
               Object[] var19 = new Object[1];
               var19[0] = var1;
               Xqedii_37nn7q1(var0, var13, var19, var4);
               break;
            case 1:
               int var12 = 2;
               Object[] var18 = new Object[1];
               var18[0] = var1;
               Xqedii_37nn7q1(var0, var12, var18, var4);
               break;
            case 2:
               int var11 = 3;
               Object[] var17 = new Object[1];
               var17[0] = var1;
               Xqedii_37nn7q1(var0, var11, var17, var4);
               break;
            case 3:
            case 4:
               Xqedii_37nn7q1(var0, 4, Xqedii_4a5wk96(var1, var3), var4);
               break;
            case 5:
               Xqedii_37nn7q1(var0, 5, Xqedii_4a5wk96(var1, var3), var4);
               break;
            case 6:
               Xqedii_37nn7q1(var0, 6, Xqedii_4a5wk96(var1, var3), var4);
               break;
            case 7:
               int var10 = 23;
               Object[] var16 = new Object[1];
               var16[0] = var1;
               Xqedii_37nn7q1(var0, var10, var16, var4);
               break;
            case 8:
               Xqedii_37nn7q1(var0, 22, Xqedii_4a5wk96(var1, var3), var4);
               break;
            case 9:
            case 10:
               Xqedii_1rx201x(var0, var1, var4);
               break;
            case 11:
               Xqedii_37nn7q1(var0, 18, Xqedii_4a5wk96(var1, var3), var4);
               break;
            case 12:
               if (var3.length == 1) {
                  Xqedii_37nn7q1(var0, 30, Xqedii_4a5wk96(var1, var3), var4);
               } else {
                  Xqedii_37nn7q1(var0, 19, Xqedii_4a5wk96(var1, var3), var4);
               }
               break;
            case 13:
               int var9 = 20;
               Object[] var15 = new Object[1];
               var15[0] = var1;
               Xqedii_37nn7q1(var0, var9, var15, var4);
               break;
            case 14:
               int var8 = 21;
               Object[] var14 = new Object[1];
               var14[0] = var1;
               Xqedii_37nn7q1(var0, var8, var14, var4);
               break;
            case 15:
            case 16:
               int var10001 = 17;
               Object[] var10002 = new Object[1];
               var10002[0] = var1;
               Xqedii_37nn7q1(var0, var10001, var10002, var4);
               break;
            case 17:
               Xqedii_3j2drjj(
                  var0,
                  var1,
                  Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_1k661t2(var3, 0, var4)),
                  var3.length > 1 ? var3[1] : null,
                  var4
               );
               break;
            case 18:
               return Xqedii_1javj6e(
                  var0, var1, Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_1k661t2(var3, 0, var4)), var4
               );
            default:
               throw new ScriptSyntaxException(Xqedii_cphtb7fherrv(var2), var4);
         }

         var5.Xqedii_3u9m9cl();
         return null;
      }
   }

   public static Object Xqedii_2q74yw2(ScriptExecutionContext var0, String var1, String var2, Object[] var3, int var4) {
      {
         int var6 = -1;
         switch (var2.hashCode()) {
            case -1765638420:
               if (var2.equals("capitalize")) {
                  var6 = 12;
               }
               break;
            case -1554585449:
               if (var2.equals("startswith")) {
                  var6 = 4;
               }
               break;
            case -1034364087:
               if (var2.equals("number")) {
                  var6 = 13;
               }
               break;
            case -1003167356:
               if (var2.equals("tonumber")) {
                  var6 = 14;
               }
               break;
            case -934531685:
               if (var2.equals("repeat")) {
                  var6 = 11;
               }
               break;
            case -567445985:
               if (var2.equals("contains")) {
                  var6 = 3;
               }
               break;
            case 3568674:
               if (var2.equals("trim")) {
                  var6 = 2;
               }
               break;
            case 103164673:
               if (var2.equals("lower")) {
                  var6 = 1;
               }
               break;
            case 109526418:
               if (var2.equals("slice")) {
                  var6 = 9;
               }
               break;
            case 109648666:
               if (var2.equals("split")) {
                  var6 = 7;
               }
               break;
            case 111499426:
               if (var2.equals("upper")) {
                  var6 = 0;
               }
               break;
            case 530542161:
               if (var2.equals("substring")) {
                  var6 = 8;
               }
               break;
            case 1094496948:
               if (var2.equals("replace")) {
                  var6 = 6;
               }
               break;
            case 1744111550:
               if (var2.equals("endswith")) {
                  var6 = 5;
               }
               break;
            case 1943292457:
               if (var2.equals("indexof")) {
                  var6 = 10;
               }
         }

         switch (var6) {
            case 0:
               return var1.toUpperCase(Locale.ROOT);
            case 1:
               return var1.toLowerCase(Locale.ROOT);
            case 2:
               return var1.trim();
            case 3:
               return var1.toLowerCase(Locale.ROOT)
                  .contains(
                     Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_1k661t2(var3, 0, var4)).toLowerCase(Locale.ROOT)
                  );
            case 4:
               return var1.toLowerCase(Locale.ROOT)
                  .startsWith(
                     Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_1k661t2(var3, 0, var4)).toLowerCase(Locale.ROOT)
                  );
            case 5:
               return var1.toLowerCase(Locale.ROOT)
                  .endsWith(
                     Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_1k661t2(var3, 0, var4)).toLowerCase(Locale.ROOT)
                  );
            case 6:
               return var1.replace(
                  Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_1k661t2(var3, 0, var4)),
                  Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_1k661t2(var3, 1, var4))
               );
            case 7:
               String var13 = var3.length > 0
                  ? Xqedii_z4wg071.Xqedii_2mlipa7(var3[0])
                  : " ";
               ArrayList var14 = new ArrayList();
               String[] var9 = var1.split(Pattern.quote(var13), -1);
               int var10 = var9.length;

               for (int var11 = 0; var11 < var10; var11++) {
                  String var12 = var9[var11];
                  var14.add(var12);
               }

               return var14;
            case 8:
            case 9:
               int var7 = Xqedii_uza1kxc(
                  Xqedii_z4wg071.Xqedii_1x518al(Xqedii_1k661t2(var3, 0, var4), var4), var1.length()
               );
               int var8 = var3.length > 1
                  ? Xqedii_uza1kxc(Xqedii_z4wg071.Xqedii_1x518al(var3[1], var4), var1.length())
                  : var1.length();
               return var8 <= var7 ? "" : var1.substring(var7, var8);
            case 10:
               return (double)var1.indexOf(Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_1k661t2(var3, 0, var4)));
            case 11:
               return var1.repeat(
                  Math.max(
                     0,
                     Xqedii_z4wg071.Xqedii_1x518al(Xqedii_1k661t2(var3, 0, var4), var4)
                  )
               );
            case 12:
               return var1.isEmpty()
                  ? var1
                  : Xqedii_3177aa0o9dmnd(
                     var1.substring(0, 1)
                        .toUpperCase(Locale.ROOT),
                     var1.substring(1)
                  );
            case 13:
            case 14:
               return Xqedii_z4wg071.Xqedii_3c9epia(var1, var4);
            default:
               return Xqedii_200p8tk(var0, var1, var2, var3, var4);
         }
      }
   }

   public static int Xqedii_uza1kxc(int var0, int var1) {
      {
         int var2 = var0 < 0 ? var1 + var0 : var0;
         return Math.max(0, Math.min(var1, var2));
      }
   }

   public static Object Xqedii_306mg5g(List<Object> var0, String var1, Object[] var2, int var3) {
      {
         int var5 = -1;
         switch (var1.hashCode()) {
            case -1183792455:
               if (var1.equals("insert")) {
                  var5 = 2;
               }
               break;
            case -938285885:
               if (var1.equals("random")) {
                  var5 = 11;
               }
               break;
            case -934610812:
               if (var1.equals("remove")) {
                  var5 = 3;
               }
               break;
            case -567445985:
               if (var1.equals("contains")) {
                  var5 = 6;
               }
               break;
            case -512822345:
               if (var1.equals("removeat")) {
                  var5 = 4;
               }
               break;
            case 96417:
               if (var1.equals("add")) {
                  var5 = 0;
               }
               break;
            case 102230:
               if (var1.equals("get")) {
                  var5 = 9;
               }
               break;
            case 103066:
               if (var1.equals("has")) {
                  var5 = 7;
               }
               break;
            case 111185:
               if (var1.equals("pop")) {
                  var5 = 5;
               }
               break;
            case 114251:
               if (var1.equals("sum")) {
                  var5 = 17;
               }
               break;
            case 3059573:
               if (var1.equals("copy")) {
                  var5 = 13;
               }
               break;
            case 3267882:
               if (var1.equals("join")) {
                  var5 = 10;
               }
               break;
            case 3452698:
               if (var1.equals("push")) {
                  var5 = 1;
               }
               break;
            case 3536286:
               if (var1.equals("sort")) {
                  var5 = 15;
               }
               break;
            case 94746189:
               if (var1.equals("clear")) {
                  var5 = 12;
               }
               break;
            case 109526418:
               if (var1.equals("slice")) {
                  var5 = 14;
               }
               break;
            case 1099846370:
               if (var1.equals("reverse")) {
                  var5 = 16;
               }
               break;
            case 1943292457:
               if (var1.equals("indexof")) {
                  var5 = 8;
               }
               break;
            case 2072332025:
               if (var1.equals("shuffle")) {
                  var5 = 18;
               }
         }

         switch (var5) {
            case 0:
            case 1:
               Collections.addAll(var0, var2);
               return null;
            case 2:
               var0.add(
                  Xqedii_uza1kxc(
                     Xqedii_z4wg071.Xqedii_1x518al(Xqedii_1k661t2(var2, 0, var3), var3), var0.size()
                  ),
                  Xqedii_1k661t2(var2, 1, var3)
               );
               return null;
            case 3:
               Object var13 = Xqedii_1k661t2(var2, 0, var3);

               for (int var17 = 0; var17 < var0.size(); var17++) {
                  if (Xqedii_z4wg071.Xqedii_zxgkysn(var0.get(var17), var13)) {
                     return var0.remove(var17);
                  }
               }

               return null;
            case 4:
               int var12 = Xqedii_uza1kxc(
                  Xqedii_z4wg071.Xqedii_1x518al(Xqedii_1k661t2(var2, 0, var3), var3), var0.size()
               );
               return var12 < var0.size() ? var0.remove(var12) : null;
            case 5:
               return var0.isEmpty() ? null : var0.remove(var0.size() - 1);
            case 6:
            case 7:
               return Xqedii_z4wg071.Xqedii_1lmv6tt(var0, Xqedii_1k661t2(var2, 0, var3));
            case 8:
               Object var11 = Xqedii_1k661t2(var2, 0, var3);

               for (int var16 = 0; var16 < var0.size(); var16++) {
                  if (Xqedii_z4wg071.Xqedii_zxgkysn(var0.get(var16), var11)) {
                     return (double)var16;
                  }
               }

               return Double.longBitsToDouble(-4616189618054758400L);
            case 9:
               int var10 = Xqedii_z4wg071.Xqedii_1x518al(Xqedii_1k661t2(var2, 0, var3), var3);
               int var15 = var10 < 0 ? var0.size() + var10 : var10;
               return var15 >= 0 && var15 < var0.size() ? var0.get(var15) : null;
            case 10:
               String var9 = var2.length > 0
                  ? Xqedii_z4wg071.Xqedii_2mlipa7(var2[0])
                  : ", ";
               StringBuilder var14 = new StringBuilder();

               for (int var8 = 0; var8 < var0.size(); var8++) {
                  if (var8 > 0) {
                     var14.append(var9);
                  }

                  var14.append(Xqedii_z4wg071.Xqedii_2mlipa7(var0.get(var8)));
               }

               return var14.toString();
            case 11:
               return var0.isEmpty() ? null : var0.get(ThreadLocalRandom.current().nextInt(var0.size()));
            case 12:
               var0.clear();
               return null;
            case 13:
               return new ArrayList(var0);
            case 14:
               int var6 = Xqedii_uza1kxc(
                  Xqedii_z4wg071.Xqedii_1x518al(Xqedii_1k661t2(var2, 0, var3), var3), var0.size()
               );
               int var7 = var2.length > 1
                  ? Xqedii_uza1kxc(Xqedii_z4wg071.Xqedii_1x518al(var2[1], var3), var0.size())
                  : var0.size();
               return var7 <= var6 ? new ArrayList() : new ArrayList(var0.subList(var6, var7));
            case 15:
            case 16:
            case 17:
            case 18:
               Object[] var10002 = new Object[1];
               var10002[0] = var0;
               return Xqedii_1iw7g71.Xqedii_2xeqrwc(null, var1, var10002, var3);
            default:
               throw new ScriptSyntaxException(Xqedii_2fymnobm4f2w3(var1), var3);
         }
      }
   }

   public static Object Xqedii_266bjwx(ScriptExecutionContext var0, Player var1, String var2, int var3) {
      {
         int var5 = -1;
         switch (var2.hashCode()) {
            case -1768407915:
               if (var2.equals("gamemode")) {
                  var5 = 15;
               }
               break;
            case -1271344497:
               if (var2.equals("flying")) {
                  var5 = 17;
               }
               break;
            case -1221262756:
               if (var2.equals("health")) {
                  var5 = 3;
               }
               break;
            case -1206104397:
               if (var2.equals("hunger")) {
                  var5 = 5;
               }
               break;
            case -1012222381:
               if (var2.equals("online")) {
                  var5 = 20;
               }
               break;
            case 120:
               if (var2.equals("x")) {
                  var5 = 9;
               }
               break;
            case 121:
               if (var2.equals("y")) {
                  var5 = 10;
               }
               break;
            case 122:
               if (var2.equals("z")) {
                  var5 = 11;
               }
               break;
            case 3553:
               if (var2.equals("op")) {
                  var5 = 16;
               }
               break;
            case 3732:
               if (var2.equals("ui")) {
                  var5 = 24;
               }
               break;
            case 3832:
               if (var2.equals("xp")) {
                  var5 = 8;
               }
               break;
            case 100893:
               if (var2.equals("exp")) {
                  var5 = 7;
               }
               break;
            case 119407:
               if (var2.equals("yaw")) {
                  var5 = 12;
               }
               break;
            case 3076010:
               if (var2.equals("data")) {
                  var5 = 21;
               }
               break;
            case 3148894:
               if (var2.equals("food")) {
                  var5 = 4;
               }
               break;
            case 3373707:
               if (var2.equals("name")) {
                  var5 = 0;
               }
               break;
            case 3433103:
               if (var2.equals("page")) {
                  var5 = 23;
               }
               break;
            case 3556308:
               if (var2.equals("temp")) {
                  var5 = 22;
               }
               break;
            case 3601339:
               if (var2.equals("uuid")) {
                  var5 = 1;
               }
               break;
            case 102865796:
               if (var2.equals("level")) {
                  var5 = 6;
               }
               break;
            case 106677056:
               if (var2.equals("pitch")) {
                  var5 = 13;
               }
               break;
            case 113318802:
               if (var2.equals("world")) {
                  var5 = 14;
               }
               break;
            case 385300558:
               if (var2.equals("sneaking")) {
                  var5 = 18;
               }
               break;
            case 1429809032:
               if (var2.equals("sprinting")) {
                  var5 = 19;
               }
               break;
            case 1715102285:
               if (var2.equals("displayname")) {
                  var5 = 2;
               }
         }

         switch (var5) {
            case 0:
               return var1.getName();
            case 1:
               return var1.getUniqueId().toString();
            case 2:
               return var1.getDisplayName();
            case 3:
               return var1.getHealth();
            case 4:
            case 5:
               return (double)var1.getFoodLevel();
            case 6:
               return (double)var1.getLevel();
            case 7:
            case 8:
               return (double)var1.getExp();
            case 9:
               return var1.getLocation().getX();
            case 10:
               return var1.getLocation().getY();
            case 11:
               return var1.getLocation().getZ();
            case 12:
               return (double)var1.getLocation().getYaw();
            case 13:
               return (double)var1.getLocation().getPitch();
            case 14:
               return var1.getWorld().getName();
            case 15:
               return var1.getGameMode().name().toLowerCase(Locale.ROOT);
            case 16:
               return var1.isOp();
            case 17:
               return var1.isFlying();
            case 18:
               return var1.isSneaking();
            case 19:
               return var1.isSprinting();
            case 20:
               return var1.isOnline();
            case 21:
               return var0.Xqedii_3a9kp91.Xqedii_2fi2lg1.Xqedii_nm0vadd(var1);
            case 22:
               return var0.Xqedii_3a9kp91.Xqedii_2fi2lg1.Xqedii_1u89z52(var1);
            case 23:
            case 24:
               String var6 = api(var3).getOpenGuiName(var1);
               return var6 == null ? "" : var6;
            default:
               throw new ScriptSyntaxException(Xqedii_38ghdp1cxzx5b(var2), var3);
         }
      }
   }

   public static void Xqedii_23nr37w(Player var0, String var1, Object var2, int var3) {
      {
         int var5 = -1;
         switch (var1.hashCode()) {
            case -1768407915:
               if (var1.equals("gamemode")) {
                  var5 = 6;
               }
               break;
            case -1271344497:
               if (var1.equals("flying")) {
                  var5 = 7;
               }
               break;
            case -1221262756:
               if (var1.equals("health")) {
                  var5 = 0;
               }
               break;
            case -1206104397:
               if (var1.equals("hunger")) {
                  var5 = 2;
               }
               break;
            case 3832:
               if (var1.equals("xp")) {
                  var5 = 5;
               }
               break;
            case 100893:
               if (var1.equals("exp")) {
                  var5 = 4;
               }
               break;
            case 3148894:
               if (var1.equals("food")) {
                  var5 = 1;
               }
               break;
            case 102865796:
               if (var1.equals("level")) {
                  var5 = 3;
               }
         }

         switch (var5) {
            case 0:
               var0.setHealth(
                  Math.max(
                     Double.longBitsToDouble(0L),
                     Math.min(var0.getMaxHealth(), Xqedii_z4wg071.Xqedii_3c9epia(var2, var3))
                  )
               );
               break;
            case 1:
            case 2:
               var0.setFoodLevel(
                  Math.max(
                     0,
                     Math.min(20, Xqedii_z4wg071.Xqedii_1x518al(var2, var3))
                  )
               );
               break;
            case 3:
               var0.setLevel(Math.max(0, Xqedii_z4wg071.Xqedii_1x518al(var2, var3)));
               break;
            case 4:
            case 5:
               var0.setExp(
                  (float)Math.max(
                     Double.longBitsToDouble(0L),
                     Math.min(Double.longBitsToDouble(4607182418800017408L), Xqedii_z4wg071.Xqedii_3c9epia(var2, var3))
                  )
               );
               break;
            case 6:
               try {
                  var0.setGameMode(GameMode.valueOf(Xqedii_z4wg071.Xqedii_2mlipa7(var2).trim().toUpperCase(Locale.ROOT)));
                  break;
               } catch (IllegalArgumentException var7) {
                  throw new ScriptSyntaxException(Xqedii_1n98m4mgetpvl(Xqedii_z4wg071.Xqedii_2mlipa7(var2)), var3);
               }
            case 7:
               boolean var6 = Xqedii_z4wg071.Xqedii_2od1ceu(var2);
               if (var6) {
                  var0.setAllowFlight(true);
               }

               var0.setFlying(var6);
               break;
            default:
               throw new ScriptSyntaxException(Xqedii_1xd0w9g2p5mj4(var1), var3);
         }
      }
   }

   public static Object Xqedii_2j653hv(ScriptEventContext var0, String var1, int var2) {
      {
         int var4 = -1;
         switch (var1.hashCode()) {
            case -1662836996:
               if (var1.equals("element")) {
                  var4 = 2;
               }
               break;
            case -1377687758:
               if (var1.equals("button")) {
                  var4 = 17;
               }
               break;
            case -1273775369:
               if (var1.equals("previous")) {
                  var4 = 22;
               }
               break;
            case -1068263387:
               if (var1.equals("movedx")) {
                  var4 = 13;
               }
               break;
            case -1068263386:
               if (var1.equals("movedy")) {
                  var4 = 15;
               }
               break;
            case -892483498:
               if (var1.equals("startx")) {
                  var4 = 9;
               }
               break;
            case -892483497:
               if (var1.equals("starty")) {
                  var4 = 10;
               }
               break;
            case -880905839:
               if (var1.equals("target")) {
                  var4 = 3;
               }
               break;
            case -867922508:
               if (var1.equals("totalx")) {
                  var4 = 14;
               }
               break;
            case -867922507:
               if (var1.equals("totaly")) {
                  var4 = 16;
               }
               break;
            case -283739534:
               if (var1.equals("dropcontainer")) {
                  var4 = 24;
               }
               break;
            case 120:
               if (var1.equals("x")) {
                  var4 = 5;
               }
               break;
            case 121:
               if (var1.equals("y")) {
                  var4 = 6;
               }
               break;
            case 3220:
               if (var1.equals("dx")) {
                  var4 = 11;
               }
               break;
            case 3221:
               if (var1.equals("dy")) {
                  var4 = 12;
               }
               break;
            case 3355:
               if (var1.equals("id")) {
                  var4 = 1;
               }
               break;
            case 3707:
               if (var1.equals("to")) {
                  var4 = 27;
               }
               break;
            case 103671:
               if (var1.equals("hud")) {
                  var4 = 28;
               }
               break;
            case 106079:
               if (var1.equals("key")) {
                  var4 = 19;
               }
               break;
            case 3092207:
               if (var1.equals("drop")) {
                  var4 = 23;
               }
               break;
            case 3151786:
               if (var1.equals("from")) {
                  var4 = 26;
               }
               break;
            case 3433103:
               if (var1.equals("page")) {
                  var4 = 4;
               }
               break;
            case 3556653:
               if (var1.equals("text")) {
                  var4 = 20;
               }
               break;
            case 3575610:
               if (var1.equals("type")) {
                  var4 = 0;
               }
               break;
            case 106629499:
               if (var1.equals("phase")) {
                  var4 = 25;
               }
               break;
            case 109407362:
               if (var1.equals("shift")) {
                  var4 = 18;
               }
               break;
            case 111972721:
               if (var1.equals("value")) {
                  var4 = 21;
               }
               break;
            case 476588369:
               if (var1.equals("cancelled")) {
                  var4 = 29;
               }
               break;
            case 1126979554:
               if (var1.equals("cursorx")) {
                  var4 = 7;
               }
               break;
            case 1126979555:
               if (var1.equals("cursory")) {
                  var4 = 8;
               }
         }
         return switch (var4) {
            case 0 -> var0.Xqedii_1x9ad7s;
            case 1, 2 -> var0.Xqedii_lc1i2g8;
            case 3 -> var0.Xqedii_1z2rxkd.isEmpty() ? var0.Xqedii_lc1i2g8 : var0.Xqedii_1z2rxkd;
            case 4 -> var0.Xqedii_3rt5h0f;
            case 5 -> var0.Xqedii_vkzqb40;
            case 6 -> var0.Xqedii_yhwhrno;
            case 7 -> var0.Xqedii_2rqqsu9;
            case 8 -> var0.Xqedii_2qxc6az;
            case 9 -> var0.Xqedii_pxsnga0;
            case 10 -> var0.Xqedii_25xm6yv;
            case 11 -> var0.Xqedii_jq9ubxh;
            case 12 -> var0.Xqedii_1t9vphc;
            case 13, 14 -> Double.isNaN(var0.Xqedii_pxsnga0)
               ? Double.longBitsToDouble(0L)
               : var0.Xqedii_vkzqb40 - var0.Xqedii_pxsnga0;
            case 15, 16 -> Double.isNaN(var0.Xqedii_25xm6yv)
               ? Double.longBitsToDouble(0L)
               : var0.Xqedii_yhwhrno - var0.Xqedii_25xm6yv;
            case 17 -> var0.Xqedii_16npco2;
            case 18 -> var0.Xqedii_19v5iy3;
            case 19 -> var0.Xqedii_2afs9at;
            case 20, 21 -> var0.Xqedii_a9in0yy;
            case 22 -> var0.Xqedii_1p91ipf;
            case 23 -> var0.Xqedii_3464tdf;
            case 24 -> var0.Xqedii_35rn1ti;
            case 25 -> var0.Xqedii_17k9l69;
            case 26 -> var0.Xqedii_12aqqzl;
            case 27 -> var0.Xqedii_2f19s3n;
            case 28 -> var0.Xqedii_1zx8kgx;
            case 29 -> var0.Xqedii_2edjbb0();
            default -> throw new ScriptSyntaxException(Xqedii_1vg1j9tmk6knx(var1), var2);
         };
      }
   }

   public static void Xqedii_biyycmb(ScriptScopeContext var0, String var1, String var2, double[] var3, Object var4, Object var5, int var6) {
      {
         Player var7 = var0.Xqedii_25ge4f1(var6);
         String var8 = var5 == null
            ? "smooth"
            : AnimationEasingFunctions.Xqedii_1uh18ch(Xqedii_z4wg071.Xqedii_2mlipa7(var5));
         if (var8 == null) {
            throw new ScriptSyntaxException(AnimationEasingFunctions.Xqedii_pzxldhl(Xqedii_z4wg071.Xqedii_2mlipa7(var5)), var6);
         } else {
            long var9 = Math.max(
               1L,
               Math.round(
                  var4 == null
                     ? Double.longBitsToDouble(4621819117588971520L)
                     : Xqedii_z4wg071.Xqedii_3c9epia(var4, var6)
               )
            );
            var0.Xqedii_3u9m9cl();
            double[] var11 = Xqedii_17ofwhd(var7, var1, var2, var6);
            if (var0.Xqedii_2fi2lg1 != null) {
               var0.Xqedii_2fi2lg1.Xqedii_3uyxvgz(var7, new AnimationEasingFunctions(var1, var2, var11, var3, var0.Xqedii_2fi2lg1.Xqedii_3b3clqv(), var9, var8));
            }
         }
      }
   }

   public static double[] Xqedii_17ofwhd(Player var0, String var1, String var2, int var3) {
      {
         UltimateUIAPI var4 = api(var3);
         int var6 = -1;
         switch (var2.hashCode()) {
            case -1267206133:
               if (var2.equals("opacity")) {
                  var6 = 4;
               }
               break;
            case -1221029593:
               if (var2.equals("height")) {
                  var6 = 3;
               }
               break;
            case -40300674:
               if (var2.equals("rotation")) {
                  var6 = 5;
               }
               break;
            case 120:
               if (var2.equals("x")) {
                  var6 = 0;
               }
               break;
            case 121:
               if (var2.equals("y")) {
                  var6 = 1;
               }
               break;
            case 94842723:
               if (var2.equals("color")) {
                  var6 = 6;
               }
               break;
            case 113126854:
               if (var2.equals("width")) {
                  var6 = 2;
               }
         }

         switch (var6) {
            case 0:
            case 1:
               double[] var12 = var4.getElementPosition(var0, var1);
               if (var12 == null) {
                  throw new ScriptSyntaxException(Xqedii_3m0c77eyl2f6c(var1), var3);
               }

               double[] var14 = new double[1];
               var14[0] = var12[var2.equals(
                     "x"
                  )
                  ? 0
                  : 1];
               return var14;
            case 2:
            case 3:
               double[] var11 = var4.getElementSize(var0, var1);
               if (var11 == null) {
                  throw new ScriptSyntaxException(Xqedii_12gp7dojfpzr2(var1), var3);
               }

               double[] var13 = new double[1];
               var13[0] = var11[var2.equals(
                     "width"
                  )
                  ? 0
                  : 1];
               return var13;
            case 4:
            case 5:
               Object var10 = var4.getElementValue(var0, var1, var2);
               double var8 = var2.equals("opacity")
                  ? Double.longBitsToDouble(4643176031446892544L)
                  : Double.longBitsToDouble(0L);
               double[] var10000 = new double[1];
               var10000[0] = var10 == null ? var8 : Xqedii_z4wg071.Xqedii_3pdnyon(var10, var8);
               return var10000;
            case 6:
               Object var7 = var4.getElementValue(var0, var1, "color");
               return AnimationEasingFunctions.Xqedii_1ugf7tn(
                  var7 == null ? "ffffff" : String.valueOf(var7), var3
               );
            default:
               throw new ScriptSyntaxException(
                  Xqedii_1oguuispvnqzj(
                     var2, String.join(", ", AnimationEasingFunctions.Xqedii_1wy8z23)
                  ),
                  var3
               );
         }
      }
   }

   public static void Xqedii_1qsxv8z(ScriptScopeContext var0, String var1, String... var2) {
      {
         if (var0.Xqedii_2fi2lg1 != null && var0.Xqedii_1v4ue4a != null) {
            var0.Xqedii_2fi2lg1.Xqedii_3032p5i(var0.Xqedii_1v4ue4a, var1, var2);
         }
      }
   }

   public static void Xqedii_1b5cndg(ScriptScopeContext var0, Object[] var1, int var2) {
      {
         String var3 = Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var1, 0)).trim().toLowerCase(Locale.ROOT);
         String var4 = UiElementId.idOf(Xqedii_25b6ies(var1, 1), var2);
         if (var4.isBlank()) {
            throw new ScriptSyntaxException(
               "create needs an element id, like: create block \"my_box\"",
               var2
            );
         } else {
            boolean var5 = var3.equals("text");
            LinkedHashMap var6 = new LinkedHashMap();
            var6.put("type", var3);
            var6.put("id", var4);
            var6.put(
               "opacity",
               255
            );
            var6.put(
               "color",
               "ffffff"
            );
            LinkedHashMap var7 = new LinkedHashMap();
            var7.put(
               "x",
               Double.longBitsToDouble(0L)
            );
            var7.put(
               "y",
               Double.longBitsToDouble(0L)
            );
            var6.put("position", var7);
            LinkedHashMap var8 = new LinkedHashMap();
            var8.put(
               "width",
               var5
                  ? Double.longBitsToDouble(4643000109586448384L)
                  : Double.longBitsToDouble(4636737291354636288L)
            );
            var8.put(
               "height",
               var5
                  ? Double.longBitsToDouble(4643000109586448384L)
                  : Double.longBitsToDouble(4636737291354636288L)
            );
            var6.put("size", var8);
            if (var5) {
               var6.put(
                  "font",
                  "default"
               );
               var6.put(
                  "align",
                  "left"
               );
               var6.put(
                  "text",
                  ""
               );
            } else if (Xqedii_1g7vacg.contains(var3)) {
               var6.put(
                  "unicode",
                  "\ue67b"
               );
            }

            if (var3.equals("rounded")) {
               var6.put(
                  "rounding",
                  "regular"
               );
            }

            String var9 = null;

            for (int var10 = 2;
               var10 + 1 < var1.length;
               var10 += 2
            ) {
               String var11 = String.valueOf(var1[var10]);
               Object var12 = var1[var10 + 1];
               int var14 = -1;
               switch (var11.hashCode()) {
                  case -1267206133:
                     if (var11.equals("opacity")) {
                        var14 = 5;
                     }
                     break;
                  case -1221029593:
                     if (var11.equals("height")) {
                        var14 = 3;
                     }
                     break;
                  case -1217487446:
                     if (var11.equals("hidden")) {
                        var14 = 8;
                     }
                     break;
                  case 120:
                     if (var11.equals("x")) {
                        var14 = 0;
                     }
                     break;
                  case 121:
                     if (var11.equals("y")) {
                        var14 = 1;
                     }
                     break;
                  case 3365:
                     if (var11.equals("in")) {
                        var14 = 9;
                     }
                     break;
                  case 3556653:
                     if (var11.equals("text")) {
                        var14 = 7;
                     }
                     break;
                  case 3649734:
                     if (var11.equals("with")) {
                        var14 = 10;
                     }
                     break;
                  case 94842723:
                     if (var11.equals("color")) {
                        var14 = 6;
                     }
                     break;
                  case 102749521:
                     if (var11.equals("layer")) {
                        var14 = 4;
                     }
                     break;
                  case 113126854:
                     if (var11.equals("width")) {
                        var14 = 2;
                     }
               }

               switch (var14) {
                  case 0:
                  case 1:
                     var7.put(var11, Xqedii_z4wg071.Xqedii_3c9epia(var12, var2));
                     break;
                  case 2:
                  case 3:
                     var8.put(var11, Xqedii_z4wg071.Xqedii_3c9epia(var12, var2));
                     break;
                  case 4:
                     var6.put(
                        "layer", Xqedii_z4wg071.Xqedii_3c9epia(var12, var2)
                     );
                     break;
                  case 5:
                     var6.put(
                        "opacity",
                        (int)Math.max(
                           0L,
                           Math.min(255L, Math.round(Xqedii_z4wg071.Xqedii_3c9epia(var12, var2)))
                        )
                     );
                     break;
                  case 6:
                     String var21 = Xqedii_z4wg071.Xqedii_2mlipa7(var12).trim();
                     var6.put(
                        "color",
                        var21.startsWith("#")
                           ? var21.substring(1)
                           : var21
                     );
                     break;
                  case 7:
                     var6.put("text", Xqedii_z4wg071.Xqedii_2mlipa7(var12));
                     break;
                  case 8:
                     if (Xqedii_z4wg071.Xqedii_2od1ceu(var12)) {
                        var6.put(
                           "visible",
                           Boolean.valueOf(false)
                        );
                     }
                     break;
                  case 9:
                     var9 = var12 == null ? null : UiElementId.idOf(var12, var2);
                     break;
                  case 10:
                     if (!(var12 instanceof Map var15)) {
                        throw new ScriptSyntaxException(
                           "create ... with needs a { key: value } map",
                           var2
                        );
                     }

                     for (Entry var17 : (Iterable<Entry>) (var15.entrySet())) {

                        String var18 = String.valueOf(var17.getKey());
                        Object var19 = Xqedii_tydjfwf(var17.getValue());
                        if ((
                              var18.equals("position")
                                 || var18.equals("size")
                           )
                           && var19 instanceof Map var20) {
                           ((Map)var6.get(var18)).putAll(var20);
                        } else {
                           var6.put(var18, var19);
                        }
                     }
                     break;
                  default:
                     var6.put(var11, Xqedii_tydjfwf(var12));
               }
            }

            var0.Xqedii_2504ge3(new UiElementCreate(var9, var6));
         }
      }
   }

   public static void Xqedii_1rx201x(ScriptExecutionContext var0, String var1, int var2) {
      {
         ScriptScopeContext var3 = var0.Xqedii_3a9kp91;
         var3.Xqedii_25ge4f1(var2);
         var3.Xqedii_o19wdzr(var1);
      }
   }

   public static void Xqedii_2ol0jxi(CommandSender var0, String var1) {
      if (var0 != null) {
         Component var2 = Xqedii_14rq758.Xqedii_g673f8i(var1);
         if (var0 instanceof Player var3) {
            Xqedii_nd6rkr3.Xqedii_3s7zz34(var3, var2);
         } else {
            var0.sendMessage(PlainTextComponentSerializer.plainText().serialize(var2));
         }
      }
   }

   public static List<CommandSender> Xqedii_33lch1h(ScriptScopeContext var0, Object var1, int var2) {
      {
         ArrayList var3 = new ArrayList();
         if (var1 == null) {
            if (var0.Xqedii_1v4ue4a != null) {
               var3.add(var0.Xqedii_1v4ue4a);
            } else if (var0.Xqedii_3cp8md6 != null) {
               var3.add(var0.Xqedii_3cp8md6);
            }

            return var3;
         } else if (!(var1 instanceof List)) {
            if (var1 instanceof Player var8) {
               var3.add(var8);
               return var3;
            } else {
               Player var7 = Bukkit.getPlayerExact(Xqedii_z4wg071.Xqedii_2mlipa7(var1));
               if (var7 == null) {
                  throw new ScriptSyntaxException(Xqedii_3uqj3wah1qjf0(Xqedii_z4wg071.Xqedii_2mlipa7(var1)), var2);
               } else {
                  var3.add(var7);
                  return var3;
               }
            }
         } else {
            for (Object var6 : (List)var1) {
               var3.addAll(Xqedii_33lch1h(var0, var6, var2));
            }

            return var3;
         }
      }
   }

   public static List<Player> Xqedii_3671f3l(ScriptScopeContext var0, Object var1, int var2) {
      if (var1 == null) {
         return List.of(var0.Xqedii_25ge4f1(var2));
      } else {
         ArrayList var3 = new ArrayList();

         for (CommandSender var5 : Xqedii_33lch1h(var0, var1, var2)) {
            if (var5 instanceof Player var6) {
               var3.add(var6);
            }
         }

         return var3;
      }
   }

   public static Player Xqedii_18aycvq(ScriptScopeContext var0, Object var1, int var2) {
      if (var1 == null) {
         return var0.Xqedii_25ge4f1(var2);
      } else {
         for (CommandSender var4 : Xqedii_33lch1h(var0, var1, var2)) {
            if (var4 instanceof Player var5) {
               return var5;
            }
         }

         throw new ScriptSyntaxException(
            "Expected a player", var2
         );
      }
   }

   public static Object Xqedii_25b6ies(Object[] var0, int var1) {
      {
         return var1 < var0.length ? var0[var1] : null;
      }
   }

   public static void Xqedii_37nn7q1(ScriptExecutionContext var0, int var1, Object[] var2, int var3) {
      {
         ScriptScopeContext var4 = var0.Xqedii_3a9kp91;
         switch (var1) {
            case 1:
            case 2:
            case 3:
               Player var42 = var4.Xqedii_25ge4f1(var3);
               var4.Xqedii_3u9m9cl();
               String var59 = UiElementId.idOf(Xqedii_25b6ies(var2, 0), var3);
               int var72 = var1 != 1
                     && (var1 != 3 || api(var3).isElementVisible(var42, var59))
                  ? 0
                  : 1;
               api(var3).setElementVisible(var42, var59, (var72 != 0));
               break;
            case 4:
               var4.Xqedii_25ge4f1(var3);
               String var41 = UiElementId.idOf(Xqedii_25b6ies(var2, 0), var3);
               double var58 = Xqedii_z4wg071.Xqedii_3c9epia(Xqedii_25b6ies(var2, 1), var3);
               double var82 = Xqedii_z4wg071.Xqedii_3c9epia(Xqedii_25b6ies(var2, 2), var3);
               if (Xqedii_25b6ies(var2, 3) != null) {
                  String var104 = "x";
                  double[] var115 = new double[1];
                  var115[0] = var58;
                  Xqedii_biyycmb(
                     var4,
                     var41,
                     var104,
                     var115,
                     Xqedii_25b6ies(var2, 3),
                     Xqedii_25b6ies(var2, 4),
                     var3
                  );
                  var104 = "y";
                  var115 = new double[1];
                  var115[0] = var82;
                  Xqedii_biyycmb(
                     var4,
                     var41,
                     var104,
                     var115,
                     Xqedii_25b6ies(var2, 3),
                     Xqedii_25b6ies(var2, 4),
                     var3
                  );
               } else {
                  String[] var106 = new String[2];
                  var106[0] = "x";
                  var106[1] = "y";
                  Xqedii_1qsxv8z(var4, var41, var106);
                  var4.Xqedii_x5301uw(UiElementWrite.position(var41, var58, var82));
               }
               break;
            case 5:
               Player var40 = var4.Xqedii_25ge4f1(var3);
               String var57 = UiElementId.idOf(Xqedii_25b6ies(var2, 0), var3);
               var4.Xqedii_3u9m9cl();
               double[] var71 = api(var3).getElementPosition(var40, var57);
               if (var71 == null) {
                  throw new ScriptSyntaxException(Xqedii_155kzkdiknxdf(var57), var3);
               }

               double var81 = var71[0]
                  + Xqedii_z4wg071.Xqedii_3c9epia(Xqedii_25b6ies(var2, 1), var3);
               double var93 = var71[1]
                  + Xqedii_z4wg071.Xqedii_3c9epia(Xqedii_25b6ies(var2, 2), var3);
               if (Xqedii_25b6ies(var2, 3) != null) {
                  String var101 = "x";
                  double[] var113 = new double[1];
                  var113[0] = var81;
                  Xqedii_biyycmb(
                     var4,
                     var57,
                     var101,
                     var113,
                     Xqedii_25b6ies(var2, 3),
                     Xqedii_25b6ies(var2, 4),
                     var3
                  );
                  var101 = "y";
                  var113 = new double[1];
                  var113[0] = var93;
                  Xqedii_biyycmb(
                     var4,
                     var57,
                     var101,
                     var113,
                     Xqedii_25b6ies(var2, 3),
                     Xqedii_25b6ies(var2, 4),
                     var3
                  );
               } else {
                  String[] var103 = new String[2];
                  var103[0] = "x";
                  var103[1] = "y";
                  Xqedii_1qsxv8z(var4, var57, var103);
                  var4.Xqedii_x5301uw(UiElementWrite.position(var57, var81, var93));
               }
               break;
            case 6:
               var4.Xqedii_25ge4f1(var3);
               String var39 = UiElementId.idOf(Xqedii_25b6ies(var2, 0), var3);
               double var56 = Xqedii_z4wg071.Xqedii_3c9epia(Xqedii_25b6ies(var2, 1), var3);
               double var80 = Xqedii_z4wg071.Xqedii_3c9epia(Xqedii_25b6ies(var2, 2), var3);
               if (Xqedii_25b6ies(var2, 3) != null) {
                  String var98 = "width";
                  double[] var111 = new double[1];
                  var111[0] = var56;
                  Xqedii_biyycmb(
                     var4,
                     var39,
                     var98,
                     var111,
                     Xqedii_25b6ies(var2, 3),
                     Xqedii_25b6ies(var2, 4),
                     var3
                  );
                  var98 = "height";
                  var111 = new double[1];
                  var111[0] = var80;
                  Xqedii_biyycmb(
                     var4,
                     var39,
                     var98,
                     var111,
                     Xqedii_25b6ies(var2, 3),
                     Xqedii_25b6ies(var2, 4),
                     var3
                  );
               } else {
                  String[] var100 = new String[2];
                  var100[0] = "width";
                  var100[1] = "height";
                  Xqedii_1qsxv8z(var4, var39, var100);
                  var4.Xqedii_x5301uw(UiElementWrite.size(var39, var56, var80));
               }
               break;
            case 7:
               String var38 = Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 0));

               for (CommandSender var70 : Xqedii_33lch1h(var4, Xqedii_25b6ies(var2, 1), var3)) {
                  Xqedii_2ol0jxi(var70, var38);
               }
               break;
            case 8:
               String var37 = Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 0));

               for (Player var69 : Bukkit.getOnlinePlayers()) {
                  Xqedii_2ol0jxi(var69, var37);
               }

               Xqedii_2ol0jxi(Bukkit.getConsoleSender(), var37);
               break;
            case 9:
               Component var36 = Xqedii_14rq758.Xqedii_g673f8i(
                  Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 0))
               );

               for (CommandSender var68 : Xqedii_33lch1h(var4, Xqedii_25b6ies(var2, 1), var3)) {
                  if (var68 instanceof Player var79) {
                     Xqedii_nd6rkr3.Xqedii_2n2p2n9(var79, var36);
                  }
               }
               break;
            case 10:
               Component var35 = Xqedii_14rq758.Xqedii_g673f8i(
                  Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 0))
               );
               Object var52 = Xqedii_25b6ies(var2, 1) == null
                  ? Component.empty()
                  : Xqedii_14rq758.Xqedii_g673f8i(Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 1)));
               Times var67 = Times.times(
                  Duration.ofMillis(250L),
                  Duration.ofMillis(2500L),
                  Duration.ofMillis(500L)
               );

               for (CommandSender var89 : Xqedii_33lch1h(var4, Xqedii_25b6ies(var2, 2), var3)) {
                  if (var89 instanceof Player var92) {
                     Xqedii_nd6rkr3.Xqedii_303uu75(var92, Title.title(var35, (Component)var52, var67));
                  }
               }
               break;
            case 11:
               Player var34 = var4.Xqedii_25ge4f1(var3);
               String var51 = Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 0)).trim();
               float var66 = Xqedii_25b6ies(var2, 1) == null
                  ? Float.intBitsToFloat(1065353216)
                  : (float)Xqedii_z4wg071.Xqedii_3c9epia(Xqedii_25b6ies(var2, 1), var3);
               float var77 = Xqedii_25b6ies(var2, 2) == null
                  ? Float.intBitsToFloat(1065353216)
                  : (float)Xqedii_z4wg071.Xqedii_3c9epia(Xqedii_25b6ies(var2, 2), var3);
               Sound var88 = var51.equals(var51.toUpperCase(Locale.ROOT)) ? Xqedii_38o2zes.Xqedii_ruc2zei(var51) : null;
               if (var88 != null) {
                  var34.playSound(var34.getLocation(), var88, var66, var77);
               } else {
                  var34.playSound(var34.getLocation(), var51.toLowerCase(Locale.ROOT), var66, var77);
               }
               break;
            case 12:
               Player var33 = var4.Xqedii_25ge4f1(var3);
               String var50 = Xqedii_3niuv0w(Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 0)));
               var33.performCommand(var50);
               break;
            case 13:
               String var32 = Xqedii_3niuv0w(Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 0)));
               Xqedii_38o2zes.Xqedii_34mipix(var4.Xqedii_2fi2lg1.Xqedii_3892wz7(), () -> {
                  {
                     Bukkit.dispatchCommand(Bukkit.getConsoleSender(), var32);
                  }
               });
               break;
            case 14:
            case 15:
               var4.Xqedii_3u9m9cl();
               String var31 = Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 0));

               for (Player var65 : Xqedii_3671f3l(var4, Xqedii_25b6ies(var2, 1), var3)) {
                  if (var1 == 15) {
                     api(var3).openGuiHud(var65, var31);
                  } else {
                     api(var3).openGui(var65, var31);
                  }
               }
               break;
            case 16:
               var4.Xqedii_3u9m9cl();
               String var30 = Xqedii_25b6ies(var2, 1) == null
                  ? null
                  : Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 1));

               for (Player var64 : Xqedii_3671f3l(var4, Xqedii_25b6ies(var2, 0), var3)) {
                  if (var30 == null) {
                     api(var3).closeGui(var64);
                  } else {
                     api(var3).closeGui(var64, var30);
                  }
               }
               break;
            case 17:
               Player var29 = var4.Xqedii_25ge4f1(var3);
               var4.Xqedii_3u9m9cl();
               api(var3).openInput(var29, UiElementId.idOf(Xqedii_25b6ies(var2, 0), var3));
               break;
            case 18:
               Player var28 = var4.Xqedii_25ge4f1(var3);
               var4.Xqedii_3u9m9cl();
               api(var3)
                  .scrollBox(
                     var28,
                     UiElementId.idOf(Xqedii_25b6ies(var2, 0), var3),
                     Xqedii_z4wg071.Xqedii_3c9epia(Xqedii_25b6ies(var2, 1), var3),
                     Xqedii_z4wg071.Xqedii_3c9epia(Xqedii_25b6ies(var2, 2), var3)
                  );
               break;
            case 19:
               Player var27 = var4.Xqedii_25ge4f1(var3);
               var4.Xqedii_3u9m9cl();
               api(var3)
                  .setScroll(
                     var27,
                     UiElementId.idOf(Xqedii_25b6ies(var2, 0), var3),
                     Xqedii_z4wg071.Xqedii_3c9epia(Xqedii_25b6ies(var2, 1), var3),
                     Xqedii_z4wg071.Xqedii_3c9epia(Xqedii_25b6ies(var2, 2), var3)
                  );
               break;
            case 20:
               Player var26 = var4.Xqedii_25ge4f1(var3);
               var4.Xqedii_3u9m9cl();
               api(var3).scrollToTop(var26, UiElementId.idOf(Xqedii_25b6ies(var2, 0), var3));
               break;
            case 21:
               Player var25 = var4.Xqedii_25ge4f1(var3);
               var4.Xqedii_3u9m9cl();
               api(var3).scrollToBottom(var25, UiElementId.idOf(Xqedii_25b6ies(var2, 0), var3));
               break;
            case 22:
               Player var24 = var4.Xqedii_25ge4f1(var3);
               var4.Xqedii_3u9m9cl();
               String var47 = UiElementId.idOf(Xqedii_25b6ies(var2, 0), var3);
               String var63 = UiElementId.idOf(Xqedii_25b6ies(var2, 1), var3);
               String var76 = Xqedii_25b6ies(var2, 2) == null
                  ? null
                  : UiElementId.idOf(Xqedii_25b6ies(var2, 2), var3);
               int var87 = Xqedii_25b6ies(var2, 3) == null
                  ? -1
                  : Xqedii_z4wg071.Xqedii_1x518al(Xqedii_25b6ies(var2, 3), var3);
               if (var76 == null) {
                  api(var3).cloneElement(var24, var47, var63);
               } else {
                  api(var3).cloneElement(var24, var47, var63, var76, var87);
               }
               break;
            case 23:
               Player var23 = var4.Xqedii_25ge4f1(var3);
               var4.Xqedii_3u9m9cl();
               api(var3).clickElement(var23, UiElementId.idOf(Xqedii_25b6ies(var2, 0), var3));
               break;
            case 24:
               Player var22 = var4.Xqedii_25ge4f1(var3);
               var4.Xqedii_3u9m9cl();
               Object var46 = Xqedii_25b6ies(var2, 1);
               if (Xqedii_25b6ies(var2, 0) instanceof List var62) {
                  ArrayList var84 = new ArrayList();

                  for (Object var11 : var62) {
                     if (!(var11 instanceof Map var12)) {
                        throw new ScriptSyntaxException(
                           "An action list must only contain { type: ..., value: ... } maps",
                           var3
                        );
                     }

                     LinkedHashMap var13 = new LinkedHashMap();

                     for (Entry var15 : (Iterable<Entry>) (var12.entrySet())) {

                        var13.put(String.valueOf(var15.getKey()), Xqedii_z4wg071.Xqedii_3ujolw5(var15.getValue()));
                     }

                     var84.add(var13);
                  }

                  api(var3).runActions(var22, var84);
               } else if (Xqedii_25b6ies(var2, 0) instanceof Map var75) {
                  LinkedHashMap var86 = new LinkedHashMap();

                  for (Entry var94 : (Iterable<Entry>) (var75.entrySet())) {

                     var86.put(String.valueOf(var94.getKey()), Xqedii_z4wg071.Xqedii_3ujolw5(var94.getValue()));
                  }

                  api(var3).runAction(var22, var86);
               } else {
                  api(var3)
                     .runAction(
                        var22,
                        Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 0)),
                        var46 == null ? null : Xqedii_z4wg071.Xqedii_2mlipa7(var46)
                     );
               }
               break;
            case 25:
               Player var21 = var4.Xqedii_25ge4f1(var3);
               World var45 = Xqedii_25b6ies(var2, 3) == null
                  ? var21.getWorld()
                  : Bukkit.getWorld(Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 3)));
               if (var45 == null) {
                  throw new ScriptSyntaxException(
                     Xqedii_14881br2ajd2g(Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 3))), var3
                  );
               }

               Location var61 = new Location(
                  var45,
                  Xqedii_z4wg071.Xqedii_3c9epia(Xqedii_25b6ies(var2, 0), var3),
                  Xqedii_z4wg071.Xqedii_3c9epia(Xqedii_25b6ies(var2, 1), var3),
                  Xqedii_z4wg071.Xqedii_3c9epia(Xqedii_25b6ies(var2, 2), var3),
                  var21.getLocation().getYaw(),
                  var21.getLocation().getPitch()
               );
               Xqedii_38o2zes.Xqedii_fxq2htx(var21, var61);
               break;
            case 26:
               Player var20 = var4.Xqedii_25ge4f1(var3);
               String var44 = Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 0))
                  .trim()
                  .toUpperCase(Locale.ROOT)
                  .replace((char)32, (char)95)
                  .replace(
                     "MINECRAFT:",
                     ""
                  );
               Material var60 = Material.matchMaterial(var44);
               if (var60 == null || var60.isAir()) {
                  throw new ScriptSyntaxException(
                     Xqedii_22t96nuch9ayo(Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 0))), var3
                  );
               }

               int var74 = Xqedii_25b6ies(var2, 1) == null
                  ? 1
                  : Math.max(
                     1,
                     Xqedii_z4wg071.Xqedii_1x518al(Xqedii_25b6ies(var2, 1), var3)
                  );
               PlayerInventory var10000 = var20.getInventory();
               ItemStack[] var10001 = new ItemStack[1];
               var10001[0] = new ItemStack(var60, var74);

               for (ItemStack var10 : var10000.addItem(var10001).values()) {
                  var20.getWorld().dropItemNaturally(var20.getLocation(), var10);
               }
               break;
            case 27:
               var4.Xqedii_2fi2lg1
                  .Xqedii_34ytbys(
                     var4.Xqedii_2q97y6i, var3, Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 0))
                  );
               break;
            case 28:
            case 29:
               int var19 = var1 == 28
                  ? 1
                  : 0;
               var4.Xqedii_3dy3w3c = (var19 != 0);
               if (var4.Xqedii_34yybpr != null) {
                  var4.Xqedii_34yybpr.cancel((var19 != 0));
               }
               break;
            case 30:
               Player var18 = var4.Xqedii_25ge4f1(var3);
               var4.Xqedii_3u9m9cl();
               api(var3)
                  .scrollToElement(
                     var18,
                     UiElementId.idOf(Xqedii_25b6ies(var2, 0), var3),
                     UiElementId.idOf(Xqedii_25b6ies(var2, 1), var3)
                  );
               break;
            case 31:
               var4.Xqedii_25ge4f1(var3);
               Xqedii_1b5cndg(var4, var2, var3);
               break;
            case 32:
               Player var17 = var4.Xqedii_25ge4f1(var3);
               var4.Xqedii_3u9m9cl();
               String var43 = UiElementId.idOf(Xqedii_25b6ies(var2, 0), var3);
               String var7 = Xqedii_25b6ies(var2, 1) == null
                  ? ""
                  : UiElementId.idOf(Xqedii_25b6ies(var2, 1), var3);
               int var73 = Xqedii_25b6ies(var2, 2) == null
                  ? -1
                  : Xqedii_z4wg071.Xqedii_1x518al(Xqedii_25b6ies(var2, 2), var3);
               api(var3).moveElement(var17, var43, var7, var73);
               break;
            case 33:
               var4.Xqedii_25ge4f1(var3);
               String var16 = UiElementId.idOf(Xqedii_25b6ies(var2, 0), var3);
               String var6 = Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 1));
               int var8 = -1;
               switch (var6.hashCode()) {
                  case 3530753:
                     if (var6.equals("size")) {
                        var8 = 1;
                     }
                     break;
                  case 94842723:
                     if (var6.equals("color")) {
                        var8 = 2;
                     }
                     break;
                  case 747804969:
                     if (var6.equals("position")) {
                        var8 = 0;
                     }
               }

               switch (var8) {
                  case 0:
                     String var96 = "x";
                     double[] var109 = new double[1];
                     var109[0] = Xqedii_z4wg071.Xqedii_3c9epia(
                        Xqedii_25b6ies(var2, 2), var3
                     );
                     Xqedii_biyycmb(
                        var4,
                        var16,
                        var96,
                        var109,
                        Xqedii_25b6ies(var2, 4),
                        Xqedii_25b6ies(var2, 5),
                        var3
                     );
                     var96 = "y";
                     var109 = new double[1];
                     var109[0] = Xqedii_z4wg071.Xqedii_3c9epia(
                        Xqedii_25b6ies(var2, 3), var3
                     );
                     Xqedii_biyycmb(
                        var4,
                        var16,
                        var96,
                        var109,
                        Xqedii_25b6ies(var2, 4),
                        Xqedii_25b6ies(var2, 5),
                        var3
                     );
                     return;
                  case 1:
                     String var10002 = "width";
                     double[] var107 = new double[1];
                     var107[0] = Xqedii_z4wg071.Xqedii_3c9epia(
                        Xqedii_25b6ies(var2, 2), var3
                     );
                     Xqedii_biyycmb(
                        var4,
                        var16,
                        var10002,
                        var107,
                        Xqedii_25b6ies(var2, 4),
                        Xqedii_25b6ies(var2, 5),
                        var3
                     );
                     var10002 = "height";
                     var107 = new double[1];
                     var107[0] = Xqedii_z4wg071.Xqedii_3c9epia(
                        Xqedii_25b6ies(var2, 3), var3
                     );
                     Xqedii_biyycmb(
                        var4,
                        var16,
                        var10002,
                        var107,
                        Xqedii_25b6ies(var2, 4),
                        Xqedii_25b6ies(var2, 5),
                        var3
                     );
                     return;
                  case 2:
                     Xqedii_biyycmb(
                        var4,
                        var16,
                        "color",
                        AnimationEasingFunctions.Xqedii_1ugf7tn(
                           Xqedii_z4wg071.Xqedii_2mlipa7(Xqedii_25b6ies(var2, 2)), var3
                        ),
                        Xqedii_25b6ies(var2, 4),
                        Xqedii_25b6ies(var2, 5),
                        var3
                     );
                     return;
                  default:
                     double[] var10003 = new double[1];
                     var10003[0] = Xqedii_z4wg071.Xqedii_3c9epia(
                        Xqedii_25b6ies(var2, 2), var3
                     );
                     Xqedii_biyycmb(
                        var4,
                        var16,
                        var6,
                        var10003,
                        Xqedii_25b6ies(var2, 4),
                        Xqedii_25b6ies(var2, 5),
                        var3
                     );
                     return;
               }
            case 34:
               Player var5 = var4.Xqedii_25ge4f1(var3);
               if (var4.Xqedii_2fi2lg1 != null) {
                  var4.Xqedii_2fi2lg1
                     .Xqedii_3032p5i(
                        var5,
                        Xqedii_25b6ies(var2, 0) == null
                           ? null
                           : UiElementId.idOf(Xqedii_25b6ies(var2, 0), var3)
                     );
               }
               break;
            default:
               throw new ScriptSyntaxException(
                  "Unknown command", var3
               );
         }
      }
   }

   public static String Xqedii_3niuv0w(String var0) {
      {
         String var1 = var0.trim();
         return var1.startsWith("/")
            ? var1.substring(1)
            : var1;
      }
   }

   static {
      {
         String[] var10000 = new String[12];
         var10000[0] = "player";
         var10000[1] = "event";
         var10000[2] = "page";
         var10000[3] = "cursor";
         var10000[4] = "temp";
         var10000[5] = "data";
         var10000[6] = "global";
         var10000[7] = "args";
         var10000[8] = "players";
         var10000[9] = "server";
         var10000[10] = "sender";
         var10000[11] = "pi";
         Xqedii_2pbhzqx = Set.of(var10000);
         Xqedii_1g7vacg = Set.of(
            "block",
            "rounded",
            "item",
            "input",
            "head",
            "button"
         );
      }
   }

   private static /* synthetic */ String Xqedii_32wj88dr8h8rr(String string, String string2) {
        return "Unknown variable '" + string + "'. Create it first with: let " + string2 + " = ...";
    }

    private static /* synthetic */ String Xqedii_2k9w5y9stitdd(String string) {
        return "A list has no '" + string + "' (try size, first or last)";
    }

    private static /* synthetic */ String Xqedii_3en6apxrvep60(String string) {
        return "Can't read '" + string + "' of none";
    }

    private static /* synthetic */ String Xqedii_27orwbso07q2y(String string, String string2) {
        return string + " has no '" + string2 + "'";
    }

    private static /* synthetic */ String Xqedii_13qgd86wp1cq2(String string, String string2) {
        return "You can't change '" + string + "' of " + string2;
    }

    private static /* synthetic */ String Xqedii_1l750eeybvls4(String string) {
        return "Can't use ." + string + "() on none";
    }

    private static /* synthetic */ String Xqedii_ej4oup67z9in(String string, String string2) {
        return string + " has no ." + string2 + "()";
    }

    private static /* synthetic */ String Xqedii_b0t15qhwpot6(int n) {
        return "Missing value number " + n;
    }

    private static /* synthetic */ String Xqedii_3eufpwv72dpmh(String string) {
        return "Expected two numbers like [x, y] but got " + string;
    }

    private static /* synthetic */ String Xqedii_cphtb7fherrv(String string) {
        return "An element has no ." + string + "()";
    }

    private static /* synthetic */ String Xqedii_3177aa0o9dmnd(String string, String string2) {
        return string + string2;
    }

    private static /* synthetic */ String Xqedii_2fymnobm4f2w3(String string) {
        return "A list has no ." + string + "()";
    }

    private static /* synthetic */ String Xqedii_38ghdp1cxzx5b(String string) {
        return "A player has no '" + string + "'";
    }

    private static /* synthetic */ String Xqedii_1n98m4mgetpvl(String string) {
        return "Unknown game mode " + string;
    }

    private static /* synthetic */ String Xqedii_1xd0w9g2p5mj4(String string) {
        return "You can't change '" + string + "' of a player";
    }

    private static /* synthetic */ String Xqedii_1vg1j9tmk6knx(String string) {
        return "An event has no '" + string + "'";
    }

    private static /* synthetic */ String Xqedii_3m0c77eyl2f6c(String string) {
        return "The element " + string + " is not open for this player";
    }

    private static /* synthetic */ String Xqedii_12gp7dojfpzr2(String string) {
        return "The element " + string + " is not open for this player";
    }

    private static /* synthetic */ String Xqedii_1oguuispvnqzj(String string, String string2) {
        return "Cannot animate '" + string + "'. Use: " + string2 + ", position or size";
    }

    private static /* synthetic */ String Xqedii_3uqj3wah1qjf0(String string) {
        return "The player " + string + " is not online";
    }

    private static /* synthetic */ String Xqedii_155kzkdiknxdf(String string) {
        return "The element " + string + " is not open for this player";
    }

    private static /* synthetic */ String Xqedii_14881br2ajd2g(String string) {
        return "There is no world called " + string;
    }

    private static /* synthetic */ String Xqedii_22t96nuch9ayo(String string) {
        return "Unknown item " + string;
    }
}
