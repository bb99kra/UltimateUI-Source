package dev.xqedii.ultimateUI;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public final class Xqedii_too58w6 {
   public static final int Xqedii_2oc3n3e = 64;
   public final UltimateUI Xqedii_1wn2twf;
   public final Xqedii_1nc5hgr Xqedii_scp8re9;
   public final BedrockFormHandler Xqedii_1mu1bzp;

   public Xqedii_too58w6(UltimateUI var1, Xqedii_1nc5hgr var2, BedrockFormHandler var3) {
      this.Xqedii_1wn2twf = var1;
      this.Xqedii_scp8re9 = var2;
      this.Xqedii_1mu1bzp = var3;
   }

   public void run(Player var1, List<Map<String, Object>> var2, Xqedii_2vhb27t var3) {
      {
         Xqedii_1kyumv5 var4 = new Xqedii_1kyumv5(var3);
         if (var1 != null && var2 != null && !var2.isEmpty()) {
            Xqedii_38o2zes.Xqedii_1avvt9e(
               this.Xqedii_1wn2twf,
               var1,
               () -> {
                  {
                     this.Xqedii_5pigmzt(
                        var1,
                        var4,
                        new ArrayList<>(var2),
                        0,
                        0
                     );
                  }
               }
            );
         } else {
            var4.Xqedii_2e4gjmy();
         }
      }
   }

   public void Xqedii_5pigmzt(Player var1, Xqedii_1kyumv5 var2, List<Map<String, Object>> var3, int var4, int var5) {
      if (var1 == null || !var1.isOnline()) {
         var2.Xqedii_2s94agv = true;
         var2.Xqedii_2e4gjmy();
      } else if (var3 == null || var4 < 0 || var4 >= var3.size()) {
         var2.Xqedii_2e4gjmy();
      } else if (var5 > 64) {
         this.Xqedii_1wn2twf
            .getLogger()
            .warning(
               "[UltimateUI/Bedrock] Action chain exceeded 64 nested branches and was stopped."
            );
         var2.Xqedii_2e4gjmy();
      } else {
         Map var6 = (Map)var3.get(var4);
         String var7 = Xqedii_2h5h2wz(
            Xqedii_pcn4k1b(var6 == null ? null : var6.get("type"))
         );
         String var8 = this.Xqedii_scp8re9.Xqedii_1kcsuri(var1, Xqedii_1c3n8qa(var6, var7));
         int var10 = -1;
         switch (var7.hashCode()) {
            case -1360201941:
               if (var7.equals("teleport")) {
                  var10 = 5;
               }
               break;
            case -1249586564:
               if (var7.equals("variable")) {
                  var10 = 7;
               }
               break;
            case -868304044:
               if (var7.equals("toggle")) {
                  var10 = 13;
               }
               break;
            case -861311717:
               if (var7.equals("condition")) {
                  var10 = 8;
               }
               break;
            case -776144932:
               if (var7.equals("redirect")) {
                  var10 = 14;
               }
               break;
            case -517618225:
               if (var7.equals("permission")) {
                  var10 = 9;
               }
               break;
            case 3173137:
               if (var7.equals("give")) {
                  var10 = 6;
               }
               break;
            case 94756344:
               if (var7.equals("close")) {
                  var10 = 15;
               }
               break;
            case 95467907:
               if (var7.equals("delay")) {
                  var10 = 0;
               }
               break;
            case 100358090:
               if (var7.equals("input")) {
                  var10 = 11;
               }
               break;
            case 109627663:
               if (var7.equals("sound")) {
                  var10 = 4;
               }
               break;
            case 950394699:
               if (var7.equals("command")) {
                  var10 = 1;
               }
               break;
            case 951510359:
               if (var7.equals("console")) {
                  var10 = 2;
               }
               break;
            case 954925063:
               if (var7.equals("message")) {
                  var10 = 3;
               }
               break;
            case 1380938712:
               if (var7.equals("function")) {
                  var10 = 10;
               }
               break;
            case 1941332754:
               if (var7.equals("visibility")) {
                  var10 = 12;
               }
         }

         switch (var10) {
            case 0:
               long var20 = Xqedii_99xk8ej(var8);
               if (var20 <= 0L) {
                  this.Xqedii_5pigmzt(var1, var2, var3, var4 + 1, var5);
                  return;
               }

               Xqedii_38o2zes.Xqedii_2sj63w1(this.Xqedii_1wn2twf, var1, () -> {
                  {
                     this.Xqedii_5pigmzt(var1, var2, var3, var4 + 1, var5);
                  }
               }, var20);
               return;
            case 1:
               this.Xqedii_1nvegcr(var1, var8);
               break;
            case 2:
               this.Xqedii_2sxcrkf(var8);
               break;
            case 3:
               this.Xqedii_1x4qo2x(var1, var8);
               break;
            case 4:
               this.Xqedii_th3t7zd(var1, var8);
               break;
            case 5:
               this.Xqedii_uavdoue(var1, var8);
               break;
            case 6:
               this.Xqedii_1h6il00(var1, var6, var8);
               break;
            case 7:
               this.Xqedii_9ehyewc(var1, var6, var8);
               break;
            case 8:
               Xqedii_1nc5hgr var27 = this.Xqedii_scp8re9;
               String[] var28 = new String[2];
               var28[0] = Xqedii_pcn4k1b(
                  var6.get("condition")
               );
               var28[1] = Xqedii_pcn4k1b(
                  var6.get("value")
               );
               String var19 = var27.Xqedii_1kcsuri(var1, Xqedii_1jjjldv(var28));
               List var23 = Xqedii_3r36mrz(
                  var6,
                  Xqedii_2stul12(var19)
                     ? "then"
                     : "else"
               );
               this.Xqedii_5pigmzt(
                  var1,
                  var2,
                  Xqedii_1z0mtdp(var23, var3, var4),
                  0,
                  var5 + 1
               );
               return;
            case 9:
               Xqedii_1nc5hgr var26 = this.Xqedii_scp8re9;
               String[] var10002 = new String[2];
               var10002[0] = Xqedii_pcn4k1b(
                  var6.get("permission")
               );
               var10002[1] = Xqedii_pcn4k1b(
                  var6.get("value")
               );
               String var18 = var26.Xqedii_1kcsuri(var1, Xqedii_1jjjldv(var10002)).trim();
               int var22 = !var18.isBlank() && var1.hasPermission(var18)
                  ? 1
                  : 0;
               List var24 = Xqedii_3r36mrz(
                  var6,
                  var22 != 0
                     ? "then"
                     : "else"
               );
               this.Xqedii_5pigmzt(
                  var1,
                  var2,
                  Xqedii_1z0mtdp(var24, var3, var4),
                  0,
                  var5 + 1
               );
               return;
            case 10:
               String[] var25 = new String[3];
               var25[0] = Xqedii_pcn4k1b(
                  var6.get("function")
               );
               var25[1] = Xqedii_pcn4k1b(
                  var6.get("name")
               );
               var25[2] = var8;
               String var17 = Xqedii_1jjjldv(var25).trim();
               List var21 = this.Xqedii_23n0kpg(var1, var6, var17);
               this.Xqedii_5pigmzt(
                  var1,
                  var2,
                  Xqedii_1z0mtdp(var21, var3, var4),
                  0,
                  var5 + 1
               );
               return;
            case 11:
               this.Xqedii_1mu1bzp
                  .input(
                     var1,
                     var6,
                     var7x -> {
                        {
                           if (Boolean.TRUE.equals(var7x)) {
                              this.Xqedii_5pigmzt(
                                 var1,
                                 var2,
                                 Xqedii_1z0mtdp(
                                    Xqedii_3r36mrz(
                                       var6, "on-submit"
                                    ),
                                    var3,
                                    var4
                                 ),
                                 0,
                                 var5 + 1
                              );
                           } else if (Boolean.FALSE.equals(var7x)) {
                              this.Xqedii_5pigmzt(
                                 var1,
                                 var2,
                                 Xqedii_3r36mrz(var6, "on-cancel"),
                                 0,
                                 var5 + 1
                              );
                           } else {
                              var2.Xqedii_2e4gjmy();
                           }
                        }
                     }
                  );
               return;
            case 12:
               Boolean var11 = Xqedii_2aap865(var8);
               if (var11 != null) {
                  ArrayList var12 = new ArrayList();
                  String[] var10000 = new String[5];
                  var10000[0] = "element";
                  var10000[1] = "elements";
                  var10000[2] = "target";
                  var10000[3] = "targets";
                  var10000[4] = "id";
                  String[] var13 = var10000;
                  int var14 = var13.length;

                  for (int var15 = 0; var15 < var14; var15++) {
                     String var16 = var13[var15];
                     var12.addAll(Xqedii_3jt84zv(var6.get(var16)));
                  }

                  if (!var12.isEmpty()) {
                     this.Xqedii_1mu1bzp.Xqedii_1ob6onh(var1, var12, var11);
                  }
               }
               break;
            case 13:
               this.Xqedii_1mu1bzp
                  .toggle(
                     var1,
                     Xqedii_3jt84zv(var6.get("hide")),
                     Xqedii_3jt84zv(var6.get("show"))
                  );
               break;
            case 14:
               if (!var8.isBlank()) {
                  var2.Xqedii_2s94agv = true;
                  this.Xqedii_1mu1bzp.Xqedii_m5xpibc(var1, var8);
                  var2.Xqedii_2e4gjmy();
                  return;
               }
               break;
            case 15:
               var2.Xqedii_2s94agv = true;
               this.Xqedii_1mu1bzp.close(var1);
         }

         this.Xqedii_5pigmzt(var1, var2, var3, var4 + 1, var5);
      }
   }

   public void Xqedii_9ehyewc(Player var1, Map<String, Object> var2, String var3) {
      {
         String[] var10000 = new String[3];
         var10000[0] = Xqedii_pcn4k1b(
            var2.get("variable")
         );
         var10000[1] = Xqedii_pcn4k1b(
            var2.get("name")
         );
         var10000[2] = Xqedii_pcn4k1b(
            var2.get("key")
         );
         String var4 = Xqedii_1jjjldv(var10000).trim();
         if (!var4.isBlank()) {
            var10000 = new String[2];
            var10000[0] = Xqedii_pcn4k1b(
               var2.get("operation")
            );
            var10000[1] = "set";
            String var5 = Xqedii_1jjjldv(var10000).trim().toLowerCase(Locale.ROOT);
            if ("set".equals(var5)) {
               this.Xqedii_scp8re9.Xqedii_1dxyjeo(var1, var4, var3);
            } else {
               String var6 = this.Xqedii_scp8re9.Xqedii_15lmxhq(var1, var4);
               Double var7 = Xqedii_37t0mn6(var6.isBlank() ? "0" : var6);
               Double var8 = Xqedii_37t0mn6(var3);
               if (var7 != null && var8 != null) {
                  int var12 = -1;
                  switch (var5.hashCode()) {
                     case -2060248300:
                        if (var5.equals("subtract")) {
                           var12 = 3;
                        }
                        break;
                     case -1339651217:
                        if (var5.equals("increment")) {
                           var12 = 1;
                        }
                        break;
                     case -1331463047:
                        if (var5.equals("divide")) {
                           var12 = 8;
                        }
                        break;
                     case -934610812:
                        if (var5.equals("remove")) {
                           var12 = 4;
                        }
                        break;
                     case 42:
                        if (var5.equals("*")) {
                           var12 = 7;
                        }
                        break;
                     case 43:
                        if (var5.equals("+")) {
                           var12 = 2;
                        }
                        break;
                     case 45:
                        if (var5.equals("-")) {
                           var12 = 5;
                        }
                        break;
                     case 47:
                        if (var5.equals("/")) {
                           var12 = 9;
                        }
                        break;
                     case 96417:
                        if (var5.equals("add")) {
                           var12 = 0;
                        }
                        break;
                     case 653829668:
                        if (var5.equals("multiply")) {
                           var12 = 6;
                        }
                  }
                  double var9 = switch (var12) {
                     case 0, 1, 2 -> var7 + var8;
                     case 3, 4, 5 -> var7 - var8;
                     case 6, 7 -> var7 * var8;
                     case 8, 9 -> var8 == Double.longBitsToDouble(0L) ? var7 : var7 / var8;
                     default -> Double.longBitsToDouble(9221120237041090560L);
                  };
                  if (!Double.isNaN(var9) && !Double.isInfinite(var9)) {
                     this.Xqedii_scp8re9.Xqedii_1dxyjeo(var1, var4, Xqedii_1fh7gam(var9));
                  }
               }
            }
         }
      }
   }

   public static String Xqedii_1fh7gam(double var0) {
      {
         return var0 == Math.rint(var0) && !Double.isInfinite(var0) ? String.valueOf((long)var0) : String.valueOf(var0);
      }
   }

   public List<Map<String, Object>> Xqedii_23n0kpg(Player var1, Map<String, Object> var2, String var3) {
      {
         Map var4 = this.Xqedii_1mu1bzp.Xqedii_69nk63w(var1, var3);
         if (var4 == null) {
            this.Xqedii_1wn2twf.getLogger().warning(Xqedii_36g6qjqzg0yql(var3));
            return new ArrayList<>();
         } else {
            ArrayList var5 = new ArrayList();
            Object var7 = var4.get("actions");
            if (var7 instanceof List) {
               for (Object var8 : (List)var7) {
                  Map var9 = Xqedii_3ldjm3c(var8);
                  if (var9 != null) {
                     var5.add(var9);
                  }
               }
            }

            LinkedHashMap var13 = new LinkedHashMap();
            var7 = var4.get("params");
            if (var7 instanceof Map var16) {
               for (Entry var11 : (Iterable<Entry>) (var16.entrySet())) {

                  var13.put(String.valueOf(var11.getKey()), this.Xqedii_scp8re9.Xqedii_1kcsuri(var1, Xqedii_pcn4k1b(var11.getValue())));
               }
            } else if (var7 instanceof List) {
               for (Object var23 : (List)var7) {
                  String var12 = Xqedii_pcn4k1b(var23).trim();
                  if (!var12.isBlank()) {
                     var13.put(var12, "");
                  }
               }
            }

            Object var17 = var2.get("arguments") == null
               ? var2.get("args")
               : var2.get("arguments");
            Map var19 = Xqedii_3ldjm3c(var17);
            if (var19 != null) {
               for (Entry var24 : (Iterable<Entry>) (var19.entrySet())) {

                  var13.put((String)var24.getKey(), this.Xqedii_scp8re9.Xqedii_1kcsuri(var1, Xqedii_pcn4k1b(var24.getValue())));
               }
            }

            if (var13.isEmpty()) {
               return var5;
            } else {
               ArrayList var22 = new ArrayList(var5.size());

               for (Map var26 : (Iterable<Map>) (var5)) {

                  var22.add((Map)this.Xqedii_1aor5hy(var26, var13));
               }

               return var22;
            }
         }
      }
   }

   public Object Xqedii_1aor5hy(Object var1, Map<String, String> var2) {
      if (var1 instanceof String var8) {
         String var10 = var8;

         for (Entry var14 : var2.entrySet()) {
            var10 = var10.replace(Xqedii_1vrxsaew83kzr((String)var14.getKey()), (CharSequence)var14.getValue());
         }

         return var10;
      } else {
         Map var3 = Xqedii_3ldjm3c(var1);
         if (var3 != null) {
            LinkedHashMap var9 = new LinkedHashMap();

            for (Entry var13 : (Iterable<Entry>) (var3.entrySet())) {

               var9.put((String)var13.getKey(), this.Xqedii_1aor5hy(var13.getValue(), var2));
            }

            return var9;
         } else if (!(var1 instanceof List var4)) {
            return var1;
         } else {
            ArrayList var5 = new ArrayList(var4.size());

            for (Object var7 : var4) {
               var5.add(this.Xqedii_1aor5hy(var7, var2));
            }

            return var5;
         }
      }
   }

   public void Xqedii_1nvegcr(Player var1, String var2) {
      {
         String var3 = Xqedii_2ls1g7l(var2);
         if (!var3.isBlank()) {
            try {
               var1.performCommand(var3);
            } catch (RuntimeException var5) {
               this.Xqedii_1wn2twf.getLogger().warning(Xqedii_2b6la0qbcq2zu(var5.getMessage()));
            }
         }
      }
   }

   public void Xqedii_2sxcrkf(String var1) {
      {
         String var2 = Xqedii_2ls1g7l(var1);
         if (!var2.isBlank()) {
            try {
               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), var2);
            } catch (RuntimeException var4) {
               this.Xqedii_1wn2twf.getLogger().warning(Xqedii_1sp5iyi5bthfs(var4.getMessage()));
            }
         }
      }
   }

   public void Xqedii_1x4qo2x(Player var1, String var2) {
      if (var2 != null && !var2.isBlank()) {
         var1.sendMessage(Xqedii_1nc5hgr.Xqedii_2jht1ka(var2));
      }
   }

   public void Xqedii_th3t7zd(Player var1, String var2) {
      {
         String var3 = var2 == null ? "" : var2.trim();
         if (!var3.isBlank()) {
            float var4 = Float.intBitsToFloat(1065353216);
            float var5 = Float.intBitsToFloat(1065353216);
            String[] var6 = var3.split("[\\s,]+");
            var3 = var6[0];
            if (var6.length > 1) {
               var4 = (float)Xqedii_33pg7ez(
                  var6[1],
                  Double.longBitsToDouble(4607182418800017408L)
               );
            }

            if (var6.length > 2) {
               var5 = (float)Xqedii_33pg7ez(
                  var6[2],
                  Double.longBitsToDouble(4607182418800017408L)
               );
            }

            try {
               var1.playSound(var1.getLocation(), var3.toLowerCase(Locale.ROOT), SoundCategory.MASTER, var4, var5);
            } catch (RuntimeException var8) {
            }
         }
      }
   }

   public void Xqedii_uavdoue(Player var1, String var2) {
      {
         String[] var3 = var2 == null
            ? new String[0]
            : var2.trim().split("[\\s,]+");
         if (var3.length >= 3) {
            try {
               World var4 = var1.getWorld();
               int var5 = 0;
               if (var3.length >= 4
                  && Bukkit.getWorld(var3[0]) != null) {
                  var4 = Bukkit.getWorld(var3[0]);
                  var5 = 1;
               }

               double var6 = Double.parseDouble(var3[var5]);
               double var8 = Double.parseDouble(var3[var5 + 1]);
               double var10 = Double.parseDouble(var3[var5 + 2]);
               float var12 = var3.length > var5 + 3
                  ? (float)Xqedii_33pg7ez(var3[var5 + 3], var1.getLocation().getYaw())
                  : var1.getLocation().getYaw();
               float var13 = var3.length > var5 + 4
                  ? (float)Xqedii_33pg7ez(var3[var5 + 4], var1.getLocation().getPitch())
                  : var1.getLocation().getPitch();
               Xqedii_38o2zes.Xqedii_fxq2htx(var1, new Location(var4, var6, var8, var10, var12, var13));
            } catch (RuntimeException var14) {
               this.Xqedii_1wn2twf.getLogger().warning(Xqedii_8i707qcg9e9j(var14.getMessage()));
            }
         }
      }
   }

   public void Xqedii_1h6il00(Player var1, Map<String, Object> var2, String var3) {
      {
         String[] var10000 = new String[2];
         var10000[0] = Xqedii_pcn4k1b(
            var2.get("item")
         );
         var10000[1] = Xqedii_pcn4k1b(
            var2.get("material")
         );
         String var4 = Xqedii_1jjjldv(var10000).trim();
         String var5 = var4.isBlank() ? (var3 == null ? "" : var3.trim()) : var4;
         if (!var5.isBlank()) {
            String[] var6 = var5.split("[\\s,]+");
            Material var7 = Material.matchMaterial(var6[0]);
            if (var7 != null && !var7.isAir()) {
               String var8 = this.Xqedii_scp8re9
                  .Xqedii_1kcsuri(
                     var1, Xqedii_pcn4k1b(var2.get("amount"))
                  );
               int var9;
               if (!var8.isBlank()) {
                  var9 = (int)Math.round(Xqedii_33pg7ez(var8, Double.longBitsToDouble(4607182418800017408L)));
               } else {
                  var9 = var6.length > 1
                     ? (int)Xqedii_33pg7ez(
                        var6[1],
                        Double.longBitsToDouble(4607182418800017408L)
                     )
                     : 1;
               }

               if (var9 > 0) {
                  PlayerInventory var10 = var1.getInventory();
                  ItemStack[] var10001 = new ItemStack[1];
                  var10001[0] = new ItemStack(
                     var7, Math.min(var9, var7.getMaxStackSize() * 36)
                  );
                  var10.addItem(var10001);
               }
            } else {
               this.Xqedii_1wn2twf.getLogger().warning(Xqedii_34921d2x1cw8q(var6[0]));
            }
         }
      }
   }

   public static Boolean Xqedii_2aap865(String var0) {
      {
         String var1 = var0 == null ? "" : var0.trim().toLowerCase(Locale.ROOT);
         int var3 = -1;
         switch (var1.hashCode()) {
            case -1901805651:
               if (var1.equals("invisible")) {
                  var3 = 10;
               }
               break;
            case -1217487446:
               if (var1.equals("hidden")) {
                  var3 = 9;
               }
               break;
            case 48:
               if (var1.equals("0")) {
                  var3 = 13;
               }
               break;
            case 49:
               if (var1.equals("1")) {
                  var3 = 6;
               }
               break;
            case 3521:
               if (var1.equals("no")) {
                  var3 = 11;
               }
               break;
            case 3551:
               if (var1.equals("on")) {
                  var3 = 5;
               }
               break;
            case 109935:
               if (var1.equals("off")) {
                  var3 = 12;
               }
               break;
            case 119527:
               if (var1.equals("yes")) {
                  var3 = 4;
               }
               break;
            case 3202370:
               if (var1.equals("hide")) {
                  var3 = 8;
               }
               break;
            case 3529469:
               if (var1.equals("show")) {
                  var3 = 1;
               }
               break;
            case 3569038:
               if (var1.equals("true")) {
                  var3 = 0;
               }
               break;
            case 97196323:
               if (var1.equals("false")) {
                  var3 = 7;
               }
               break;
            case 109413649:
               if (var1.equals("shown")) {
                  var3 = 2;
               }
               break;
            case 466743410:
               if (var1.equals("visible")) {
                  var3 = 3;
               }
         }
         return switch (var3) {
            case 0, 1, 2, 3, 4, 5, 6 -> Boolean.TRUE;
            case 7, 8, 9, 10, 11, 12, 13 -> Boolean.FALSE;
            default -> null;
         };
      }
   }

   public static List<String> Xqedii_3jt84zv(Object var0) {
      {
         ArrayList var1 = new ArrayList();
         if (var0 instanceof List) {
            for (Object var4 : (List)var0) {
               String var5 = Xqedii_pcn4k1b(var4).trim();
               if (!var5.isBlank()) {
                  var1.add(var5);
               }
            }
         } else {
            String var6 = Xqedii_pcn4k1b(var0).trim();
            if (!var6.isBlank()) {
               var1.add(var6);
            }
         }

         return var1;
      }
   }

   public static boolean Xqedii_2stul12(String var0) {
      {
         String var1 = var0 == null ? "" : var0.trim();
         if (var1.isEmpty()) {
            return false;
         } else {
            String[] var10000 = new String[7];
            var10000[0] = ">=";
            var10000[1] = "<=";
            var10000[2] = "!=";
            var10000[3] = "==";
            var10000[4] = "=";
            var10000[5] = ">";
            var10000[6] = "<";
            String[] var2 = var10000;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; var4++) {
               String var5 = var2[var4];
               int var6 = var1.indexOf(var5);
               if (var6 > 0) {
                  String var7 = var1.substring(0, var6).trim();
                  String var8 = var1.substring(var6 + var5.length()).trim();
                  Double var9 = Xqedii_37t0mn6(var7);
                  Double var10 = Xqedii_37t0mn6(var8);
                  if (var9 != null && var10 != null) {
                     int var14 = -1;
                     switch (var5.hashCode()) {
                        case 60:
                           if (var5.equals("<")) {
                              var14 = 3;
                           }
                           break;
                        case 62:
                           if (var5.equals(">")) {
                              var14 = 2;
                           }
                           break;
                        case 1084:
                           if (var5.equals("!=")) {
                              var14 = 4;
                           }
                           break;
                        case 1921:
                           if (var5.equals("<=")) {
                              var14 = 1;
                           }
                           break;
                        case 1983:
                           if (var5.equals(">=")) {
                              var14 = 0;
                           }
                     }
                     return ((switch (var14) {
                        case 0 -> var9 >= var10 ? 1 : 0;
                        case 1 -> var9 <= var10 ? 1 : 0;
                        case 2 -> var9 > var10 ? 1 : 0;
                        case 3 -> var9 < var10 ? 1 : 0;
                        case 4 -> var9 != var10 ? 1 : 0;
                        default -> var9 == var10 ? 1 : 0;
                     }) != 0);
                  }

                  int var12 = -1;
                  switch (var5.hashCode()) {
                     case 61:
                        if (var5.equals("=")) {
                           var12 = 2;
                        }
                        break;
                     case 1084:
                        if (var5.equals("!=")) {
                           var12 = 0;
                        }
                        break;
                     case 1952:
                        if (var5.equals("==")) {
                           var12 = 1;
                        }
                  }
                  return switch (var12) {
                     case 0 -> !var7.equalsIgnoreCase(var8);
                     case 1, 2 -> var7.equalsIgnoreCase(var8);
                     default -> false;
                  };
               }
            }

            if ("true".equalsIgnoreCase(var1)) {
               return true;
            } else if ("false".equalsIgnoreCase(var1)) {
               return false;
            } else {
               Double var13 = Xqedii_37t0mn6(var1);
               return ((var13 != null && var13 == Double.longBitsToDouble(0L)
                  ? 0
                  : 1) != 0);
            }
         }
      }
   }

   public static Double Xqedii_37t0mn6(String var0) {
      {
         try {
            return Double.valueOf(var0.trim());
         } catch (RuntimeException var2) {
            return null;
         }
      }
   }

   public static double Xqedii_33pg7ez(String var0, double var1) {
      {
         try {
            return Double.parseDouble(var0.trim());
         } catch (RuntimeException var4) {
            return var1;
         }
      }
   }

   public static long Xqedii_99xk8ej(String var0) {
      {
         String var1 = var0 == null ? "" : var0.trim().toLowerCase(Locale.ROOT);
         if (var1.isEmpty()) {
            return 0L;
         } else {
            try {
               if (var1.endsWith("ms")) {
                  return Math.round(
                     Double.parseDouble(
                           var1.substring(
                              0, var1.length() - 2
                           )
                        )
                        / Double.longBitsToDouble(4632233691727265792L)
                  );
               } else if (var1.endsWith("s")) {
                  return Math.round(
                     Double.parseDouble(
                           var1.substring(
                              0, var1.length() - 1
                           )
                        )
                        * Double.longBitsToDouble(4626322717216342016L)
                  );
               } else {
                  return var1.endsWith("t")
                     ? Math.round(
                        Double.parseDouble(
                           var1.substring(
                              0, var1.length() - 1
                           )
                        )
                     )
                     : Math.round(Double.parseDouble(var1));
               }
            } catch (NumberFormatException var3) {
               return 0L;
            }
         }
      }
   }

   public static List<Map<String, Object>> Xqedii_3r36mrz(Map<String, Object> var0, String var1) {
      {
         ArrayList var2 = new ArrayList();
         if (var0 == null) {
            return var2;
         } else {
            Object var3 = var0.get(var1);
            Map var4 = Xqedii_3ldjm3c(var3);
            if (var4 != null) {
               var3 = var4.get("actions");
            }

            if (var3 instanceof ConfigurationSection var5) {
               var3 = var5.getList("actions");
            }

            if (var3 instanceof List) {
               for (Object var7 : (List)var3) {
                  Map var8 = Xqedii_3ldjm3c(var7);
                  if (var8 != null) {
                     var2.add(var8);
                  }
               }
            }

            return var2;
         }
      }
   }

   public static List<Map<String, Object>> Xqedii_1z0mtdp(List<Map<String, Object>> var0, List<Map<String, Object>> var1, int var2) {
      {
         ArrayList var3 = new ArrayList(var0);

         for (int var4 = var2 + 1; var4 < var1.size(); var4++) {
            var3.add((Map)var1.get(var4));
         }

         return var3;
      }
   }

   public static Map<String, Object> Xqedii_3ldjm3c(Object var0) {
      if (var0 instanceof Map var5) {
         LinkedHashMap var2 = new LinkedHashMap();

         for (Entry var4 : (Iterable<Entry>) (var5.entrySet())) {

            if (var4.getKey() != null) {
               var2.put(String.valueOf(var4.getKey()), var4.getValue());
            }
         }

         return var2;
      } else {
         return var0 instanceof ConfigurationSection var1
            ? new LinkedHashMap<>(var1.getValues(false))
            : null;
      }
   }

   public static String Xqedii_1c3n8qa(Map<String, Object> var0, String var1) {
      if (var0 == null) {
         return "";
      } else {
         String[] var10000 = new String[3];
         var10000[0] = Xqedii_pcn4k1b(
            var0.get("value")
         );
         var10000[1] = Xqedii_pcn4k1b(
            var0.get("name")
         );
         var10000[2] = Xqedii_pcn4k1b(
            var0.get("data")
         );
         String var2 = Xqedii_1jjjldv(var10000).trim();
         return !var2.isBlank() ? var2 : Xqedii_pcn4k1b(var0.get(var1)).trim();
      }
   }

   public static String Xqedii_2h5h2wz(String var0) {
      {
         return var0 == null ? "" : var0.trim().toLowerCase(Locale.ROOT);
      }
   }

   public static String Xqedii_2ls1g7l(String var0) {
      {
         String var1 = var0 == null ? "" : var0.trim();
         return var1.startsWith("/")
            ? var1.substring(1)
            : var1;
      }
   }

   public static String Xqedii_pcn4k1b(Object var0) {
      {
         return var0 == null ? "" : String.valueOf(var0);
      }
   }

   public static String Xqedii_1jjjldv(String... var0) {
      if (var0 == null) {
         return "";
      } else {
         String[] var1 = var0;
         int var2 = var0.length;

         for (int var3 = 0; var3 < var2; var3++) {
            String var4 = var1[var3];
            if (var4 != null && !var4.isBlank()) {
               return var4;
            }
         }

         return "";
      }
   }

   private static /* synthetic */ String Xqedii_36g6qjqzg0yql(String string) {
        return Xqedii_1gibrpy.Xqedii_2wvag7qh4vnxt((String)"b+6mPdt+USn6W9lddk0vYfWO6jKX9HQPtA735vv/a01SOg39HQn3Uu7GK7Ktt6jSetoT92X1jgHZ/rkHOJ/BX0L18MLf5l03hMN8hgKPI39urB0cixg33UwQ/EIt61LtqLhRHRbi92vpgBYGWO3Ou8ULnhEGRewxPwYPsbBZ") + string + Xqedii_1gibrpy.Xqedii_2wvag7qh4vnxt((String)"oDpT++I8lPl5cKctuh4v2g7/M6C0peq5XV6IJg4cVqhIudn1kQ3n+KXJDn/oaJxogAlwulTOL56V/e9ExaOrHmyvFf6FDkVkK/NXkzBPSzABJbMONT5gL79y8sVqIw==");
    }

    private static /* synthetic */ String Xqedii_1vrxsaew83kzr(String string) {
        return Xqedii_1gibrpy.Xqedii_2wvag7qh4vnxt((String)"IkrcVfapdwJHCwdSIYnGoAnLr3AF/a+qkdjIcIfR") + string + Xqedii_1gibrpy.Xqedii_2wvag7qh4vnxt((String)"PYq9j+fG2a90IdnyWDVvz5sCXQT3bu+VPuOy33fL");
    }

    private static /* synthetic */ String Xqedii_2b6la0qbcq2zu(String string) {
        return Xqedii_1gibrpy.Xqedii_2wvag7qh4vnxt((String)"ViAYMtVtM/uzHvNkisxiRwquNI76N8HJRG/UEnp/Wc07ARYNCO1oUUQkWJ+XSdajf1NFbeD07Yed2T+byRXe6sxnNLtbzpV7ESRVZ3KazguojWyUeTdeAkOlct6rXxdtcFUSwswhpqNBqUl4kW0qZyotokg=") + string;
    }

    private static /* synthetic */ String Xqedii_1sp5iyi5bthfs(String string) {
        return Xqedii_1gibrpy.Xqedii_2wvag7qh4vnxt((String)"h51CG+E53r3YmfVmJCN2pViHScVrnBF0hOcJd/BgXrRI3kTxGOwN8FBnX6Nq56fF+d17Yf0qY71n06bkBjtKO/oGq+tt+3WlRNcWtPrUIciqunTVzD4l/BGShNOo0EGGjfXl9CoWa+MioZlhuu0wG5rNsys=") + string;
    }

    private static /* synthetic */ String Xqedii_8i707qcg9e9j(String string) {
        return Xqedii_1gibrpy.Xqedii_2wvag7qh4vnxt((String)"Hzi6UGVEaX9O4ZS/pDAOyfmsxfhx/1lD5usqj2V4APjSaZPhQln0nUYYO2RvFY6XPpS0HwZePH6SR9Hl6qKe626wreZCjWncK2J8rlNYzwY2IAafwnAkwsYbEVJRn6mFj6TmEwCxa9X01mqKodJRNE/xLPIVJA==") + string;
    }

    private static /* synthetic */ String Xqedii_34921d2x1cw8q(String string) {
        return Xqedii_1gibrpy.Xqedii_2wvag7qh4vnxt((String)"bYfO0n1FUedBqQ9THVbWbx2T9K+8ERXgaCh7e3aHtcFP2RLD1WOxGsAnDeUqm5lEoBryouPZ2W7aa+Cr4huewNTep70bB8wCFVkOPd+X2v5LHAx292CPeuH29wi0OweJpIAGWVgOJgwitZ6RJNbQziLb1y3VrCifB6YdWJ5HMaTXehrq37pRErxCnfSz3z9YkEs3x0wIKQ4=") + string + Xqedii_1gibrpy.Xqedii_2wvag7qh4vnxt((String)"f/lMcqm1HmU3D19QNwO+PTUvF9e/8hQPIxXynQx7vHQ=");
    }
}
