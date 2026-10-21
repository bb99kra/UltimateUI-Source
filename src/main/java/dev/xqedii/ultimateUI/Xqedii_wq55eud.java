package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.gui.parser.GuiTemplateResolver;
import dev.xqedii.ultimateUI.gui.parser.ScrollViewState;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public final class Xqedii_wq55eud {
   public static final String Xqedii_3af4uem = "contents";
   public static final String Xqedii_2r4qu9v = "contents/pages";
   public static final String Xqedii_1dfz1t6 = "contents/components";
   public static final String Xqedii_18gv4wk = "contents/effects";
   public static final List<String> Xqedii_13xok5r;
   public static final Pattern Xqedii_xyhkbtz;
   public static final Pattern Xqedii_27ehu24;
   public static final String Xqedii_33yeu5j = "%player_head";
   public static final String[] Xqedii_2cjopzz;
   public static final double[] Xqedii_p4ue641;
   public static final double Xqedii_2sig16m = 64.0;
   public static final double Xqedii_3uul0fs = 2.0;
   public static final double Xqedii_2bgop7d = 8.0;
   public static final String Xqedii_2p2ie5u = "ffffff";
   public static final String Xqedii_1pkh7j5 = "7a7a7a";
   public final File Xqedii_2ap3dg4;
   public final GuiTemplateResolver Xqedii_1clqv5d = new GuiTemplateResolver();
   public final Map<String, Xqedii_4xfqqc9> Xqedii_1fy2tdo = new LinkedHashMap<>();
   public static final double Xqedii_52niqrz = 0.11666666666666667;
   public double Xqedii_19ioi04 = Double.longBitsToDouble(4656159064747671552L);
   public double Xqedii_1zjx2xb = Double.longBitsToDouble(4652376744748122112L);
   public double Xqedii_2skqlwh = Double.longBitsToDouble(4628011567076605952L);
   public double Xqedii_wrlou90 = Double.longBitsToDouble(4622382067542392832L);
   public double Xqedii_166lwat = Double.longBitsToDouble(4634204016564240384L);

   public Xqedii_wq55eud(File var1) {
      this.Xqedii_2ap3dg4 = var1;
   }

   public Xqedii_wq55eud Xqedii_22sacy4(double var1, double var3) {
      {
         if (var1 > Double.longBitsToDouble(0L)) {
            this.Xqedii_19ioi04 = var1;
         }

         if (var3 > Double.longBitsToDouble(0L)) {
            this.Xqedii_1zjx2xb = var3;
         }

         return this;
      }
   }

   public Xqedii_wq55eud Xqedii_12rq19l(double var1) {
      {
         if (var1 >= Double.longBitsToDouble(0L)) {
            this.Xqedii_2skqlwh = var1;
         }

         return this;
      }
   }

   public Xqedii_wq55eud Xqedii_5gekgz5(double var1, double var3) {
      {
         if (var1 > Double.longBitsToDouble(0L)) {
            this.Xqedii_wrlou90 = var1;
         }

         if (var3 > Double.longBitsToDouble(0L)) {
            this.Xqedii_166lwat = var3;
         }

         return this;
      }
   }

   public List<Xqedii_2kb1ns6> Xqedii_2s9ccrs(List<String> var1) {
      {
         Map var2 = this.Xqedii_31j3l8o(
            "contents/components"
         );
         Map var3 = this.Xqedii_31j3l8o("contents/pages");
         ArrayList var4 = new ArrayList();
         int var5 = 0;

         for (Entry var7 : (Iterable<Entry>) (var3.entrySet())) {

            String var8 = (String)var7.getKey();
            if (!this.Xqedii_brmfmy8(var8)) {
               YamlConfiguration var9 = (YamlConfiguration)var7.getValue();
               if (var9 != null
                  && !var9.getBoolean(
                     "page-hidden",
                     false
                  )) {
                  if (var5 >= 4096) {
                     var1.add(Xqedii_2fz2bgmpc7dji(var8));
                     break;
                  }

                  Xqedii_2kb1ns6 var10 = this.Xqedii_9bs7l7r(var8, var5, var9, var2);
                  if (var10 != null) {
                     var4.add(var10);
                     var1.addAll(var10.Xqedii_1z8s0ib);
                     var5++;
                  }
               }
            }
         }

         return var4;
      }
   }

   public boolean Xqedii_brmfmy8(String var1) {
      {
         String var2 = var1 == null ? "" : var1.toLowerCase(Locale.ROOT);

         for (String var4 : Xqedii_13xok5r) {
            if (var2.equals(var4) || var2.startsWith(Xqedii_3ae8pa1vbanbq(var4))) {
               return true;
            }
         }

         return false;
      }
   }

   public Xqedii_2kb1ns6 Xqedii_9bs7l7r(String var1, int var2, YamlConfiguration var3, Map<String, YamlConfiguration> var4) {
      {
         Xqedii_2kb1ns6 var5 = new Xqedii_2kb1ns6();
         var5.Xqedii_3j6s705 = var1;
         var5.Xqedii_2kz3xjg = var2;
         var5.Xqedii_12g62h3 = var3.getDouble(
            "screen.width", this.Xqedii_19ioi04
         );
         var5.Xqedii_r5a1fhj = var3.getDouble(
            "screen.height", this.Xqedii_1zjx2xb
         );
         var5.Xqedii_2g46lck = var3.getDouble(
            "screen.offsetX",
            Double.longBitsToDouble(0L)
         );
         var5.Xqedii_1m61jit = var3.getDouble(
            "screen.offsetY",
            Double.longBitsToDouble(4626322717216342016L)
         );
         if (var5.Xqedii_12g62h3 <= Double.longBitsToDouble(0L)) {
            var5.Xqedii_12g62h3 = this.Xqedii_19ioi04;
         }

         if (var5.Xqedii_r5a1fhj <= Double.longBitsToDouble(0L)) {
            var5.Xqedii_r5a1fhj = this.Xqedii_1zjx2xb;
         }

         List var6;
         try {
            var6 = this.Xqedii_1clqv5d
               .resolveBlocks(
                  var3.getList("blocks"),
                  var4,
                  ScrollViewState.exportAll()
               );
         } catch (RuntimeException var16) {
            var5.Xqedii_1z8s0ib.add(Xqedii_vp4hn1nmzmul(var1, var16.getMessage()));
            return null;
         }

         this.Xqedii_1asn20f(var3, var5);
         Set var7 = this.Xqedii_3owxj9n(var6, var5.Xqedii_p5zrifg);
         ArrayList<Xqedii_v7p1hqx> var8 = new ArrayList<>();

         for (Map var10 : (Iterable<Map>) (var6)) {

            if (var10 != null && !var10.isEmpty()) {
               Xqedii_v7p1hqx var11 = this.Xqedii_qdkw6si(var5, var10, var5.Xqedii_1z8s0ib);
               if (var11 != null) {
                  var8.add(var11);
               }
            }
         }

         var8.sort((Xqedii_v7p1hqx var0, Xqedii_v7p1hqx var1x) -> {
            {
               return Double.compare(var0.Xqedii_prfwkef, var1x.Xqedii_prfwkef);
            }
         });
         int var17 = 0;

         for (Xqedii_v7p1hqx var20 : (Iterable<Xqedii_v7p1hqx>) (var8)) {

            var20.Xqedii_3mzx85f = ++var17;
         }

         HashSet var19 = new HashSet();

         for (Xqedii_v7p1hqx var12 : (Iterable<Xqedii_v7p1hqx>) (var8)) {

            if (!var12.Xqedii_oruxzxc.isBlank() && var7.contains(var12.Xqedii_oruxzxc) && !var12.Xqedii_11ww0b3.isBlank()) {
               var19.add(var12.Xqedii_11ww0b3);
            }
         }

         ArrayList var22 = new ArrayList();

         for (Xqedii_v7p1hqx var13 : (Iterable<Xqedii_v7p1hqx>) (var8)) {

            int var14 = !var7.contains(var13.Xqedii_oruxzxc) && !Xqedii_3lmqang(var13.Xqedii_11ww0b3, var19)
               ? 0
               : 1;
            var13.Xqedii_33uodds = ((var14 == 0 && var13.Xqedii_3mv9hk2.isBlank() && var13.Xqedii_2v720ms.isBlank()
               ? 0
               : 1) != 0);
            if (!var13.Xqedii_3mu4xzh || var13.Xqedii_33uodds) {
               var22.add(var13);
               if (!var13.Xqedii_2v720ms.isBlank() && var13.Xqedii_1tl99gi >= 0) {
                  Xqedii_1tuv3qh var15 = var5.Xqedii_i5hlcvn.get(var13.Xqedii_2v720ms);
                  if (var15 != null) {
                     var15.Xqedii_xafwbzr(var13.Xqedii_1tl99gi).Xqedii_bn1fk8k.add(var13);
                  }
               }
            }
         }

         this.Xqedii_fsb4826(var5, var22);
         var5.Xqedii_ggpkjmc.addAll(var22);
         return var5;
      }
   }

   public void Xqedii_fsb4826(Xqedii_2kb1ns6 var1, List<Xqedii_v7p1hqx> var2) {
      {
         int var3 = 0;

         for (Xqedii_v7p1hqx var5 : var2) {
            if (var5.Xqedii_pxr9qfs()) {
               var5.Xqedii_2sh7mex = var3++;
            }
         }

         var1.Xqedii_b0fv9fz = var3;
         int var10 = var3;
         int var12 = 0;

         for (Xqedii_v7p1hqx var7 : var2) {
            if (var7.Xqedii_5oo0vc1()) {
               var7.Xqedii_21n4stt = var10++;
            }

            if (var7.Xqedii_221jxyb()) {
               var7.Xqedii_21n4stt = var10++;
               var7.Xqedii_2hjfin7 = var10++;
               var7.Xqedii_3ma30bi = var12 < 256 ? var12++ : -1;
            }
         }

         var1.Xqedii_1ei4hmh = var10 - var3;
         var1.Xqedii_3omdmsa = var12;
         int var13 = var10;

         for (Xqedii_v7p1hqx var8 : var2) {
            if (var8.Xqedii_323l2jb()) {
               var8.Xqedii_3rdwuj0 = var13++;
            }
         }

         var1.Xqedii_1jqflox = var13 - var10;
         int var15 = 0;

         for (Xqedii_v7p1hqx var9 : var2) {
            var15 |= var9.Xqedii_33uodds ? 1 : 0;
         }

         var1.Xqedii_sytzb79 = var15 != 0 ? var13 : -1;
      }
   }

   public static boolean Xqedii_3lmqang(String var0, Set<String> var1) {
      if (var0 != null && !var0.isBlank() && !var1.isEmpty()) {
         for (String var3 : var1) {
            if (var0.equals(var3) || var0.startsWith(Xqedii_z080dd5n5ms8(var3))) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public void Xqedii_1asn20f(YamlConfiguration var1, Xqedii_2kb1ns6 var2) {
      {
         ConfigurationSection var3 = var1.getConfigurationSection(
            "functions"
         );
         if (var3 != null) {
            for (String var5 : var3.getKeys(false)) {
               if (var5 != null && !var5.isBlank()) {
                  LinkedHashMap var6 = new LinkedHashMap();
                  ConfigurationSection var7 = var3.getConfigurationSection(var5);
                  if (var7 != null) {
                     ConfigurationSection var8 = var7.getConfigurationSection(
                        "params"
                     );
                     var6.put(
                        "params",
                        var8 != null
                           ? var8.getValues(false)
                           : var7.get("params")
                     );
                     var6.put(
                        "actions",
                        var7.getList("actions")
                     );
                  }

                  if (var6.get("actions") instanceof List var14
                     && !var14.isEmpty()) {
                     var2.Xqedii_p5zrifg.put(var5, var6);
                  }
               }
            }
         }

         ConfigurationSection var10 = var1.getConfigurationSection(
            "variables"
         );
         if (var10 != null) {
            for (String var12 : var10.getKeys(false)) {
               if (var12 != null && !var12.isBlank()) {
                  String var13 = var12.trim();
                  int var15 = !var13.startsWith("*")
                        && !var13.startsWith("$")
                     ? 0
                     : 1;
                  if (var15 != 0) {
                     var13 = var13.substring(1).trim();
                  }

                  if (!var13.isBlank()) {
                     String[] var10000 = new String[2];
                     var10000[0] = Xqedii_1qe7ua6(
                        var10.get(var12), ""
                     );
                     var10000[1] = "0";
                     String var16 = Xqedii_39mdkmx(var10000);
                     (var15 != 0 ? var2.Xqedii_2gjw6qt : var2.Xqedii_du21gc3).put(var13, var16);
                  }
               }
            }
         }
      }
   }

   public Set<String> Xqedii_3owxj9n(List<Map<String, Object>> var1, Map<String, Map<String, Object>> var2) {
      {
         HashSet var3 = new HashSet();

         for (Map var5 : var1) {
            if (var5 != null) {
               this.Xqedii_3cq8jto(
                  var5.get("actions"),
                  var3,
                  0
               );
               this.Xqedii_3cq8jto(
                  var5.get("on-submit"),
                  var3,
                  0
               );
               this.Xqedii_3cq8jto(
                  var5.get("on-cancel"),
                  var3,
                  0
               );
            }
         }

         for (Map var7 : var2.values()) {
            this.Xqedii_3cq8jto(
               var7.get("actions"),
               var3,
               0
            );
         }

         return var3;
      }
   }

   public void Xqedii_3cq8jto(Object var1, Set<String> var2, int var3) {
      if (var1 != null && var3 <= 16) {
         if (var1 instanceof ConfigurationSection var4) {
            var1 = var4.getValues(false);
         }

         if (var1 instanceof List) {
            for (Object var14 : (List)var1) {
               this.Xqedii_3cq8jto(var14, var2, var3 + 1);
            }
         } else {
            Map var10 = Xqedii_30dd4qv(var1);
            if (var10 != null) {
               String var5 = Xqedii_1qe7ua6(
                     var10.get("type"),
                     ""
                  )
                  .trim()
                  .toLowerCase(Locale.ROOT);
               if ("visibility".equals(var5)) {
                  String[] var10000 = new String[5];
                  var10000[0] = "element";
                  var10000[1] = "elements";
                  var10000[2] = "target";
                  var10000[3] = "targets";
                  var10000[4] = "id";
                  String[] var6 = var10000;
                  int var7 = var6.length;

                  for (int var8 = 0; var8 < var7; var8++) {
                     String var9 = var6[var8];
                     this.Xqedii_25mrutu(var10.get(var9), var2);
                  }
               } else if ("toggle".equals(var5)) {
                  this.Xqedii_25mrutu(var10.get("hide"), var2);
                  this.Xqedii_25mrutu(var10.get("show"), var2);
               }

               for (Object var15 : var10.values()) {
                  if (var15 instanceof List || var15 instanceof Map || var15 instanceof ConfigurationSection) {
                     this.Xqedii_3cq8jto(var15, var2, var3 + 1);
                  }
               }
            }
         }
      }
   }

   public void Xqedii_25mrutu(Object var1, Set<String> var2) {
      if (var1 instanceof List) {
         for (Object var5 : (List)var1) {
            String var6 = Xqedii_1qe7ua6(var5, "").trim();
            if (!var6.isBlank()) {
               var2.add(var6);
            }
         }
      } else {
         String var3 = Xqedii_1qe7ua6(var1, "").trim();
         if (!var3.isBlank()) {
            var2.add(var3);
         }
      }
   }

   public Xqedii_v7p1hqx Xqedii_qdkw6si(Xqedii_2kb1ns6 var1, Map<String, Object> var2, List<String> var3) {
      {
         String var4 = var1.Xqedii_3j6s705;
         String var5 = Xqedii_1qe7ua6(
               var2.get("type"),
               "block"
            )
            .toLowerCase(Locale.ROOT);
         Xqedii_v7p1hqx var6 = new Xqedii_v7p1hqx();
         var6.Xqedii_oruxzxc = Xqedii_1qe7ua6(
            var2.get("id"),
            ""
         );
         var6.Xqedii_272zqk2 = this.Xqedii_3eva3om(var5);
         var6.Xqedii_11ww0b3 = Xqedii_1qe7ua6(
            var2.get("__editor_block_path"),
            ""
         );
         var6.Xqedii_76cq2mh = Xqedii_1qe7ua6(
            var2.get("__uui_scroll_box"),
            ""
         );
         var6.Xqedii_2v720ms = Xqedii_1qe7ua6(
            var2.get("__uui_filter_box"),
            ""
         );
         var6.Xqedii_1tl99gi = (int)Xqedii_ulr0k7d(
            var2.get("__uui_filter_group"),
            Double.longBitsToDouble(-4616189618054758400L)
         );
         Xqedii_3duv2su var7 = this.Xqedii_hnlsyy6(var2, var6.Xqedii_76cq2mh);
         if (var7 != null) {
            var1.Xqedii_3ow3583.put(var7.Xqedii_2h2vht7, var7);
            var6.Xqedii_350upak = var7;
         }

         this.Xqedii_22jicqr(var1, var2);
         this.Xqedii_3c8rob5(var1, var6, var2);
         String var8 = Xqedii_25do604(var2, "item".equals(var6.Xqedii_272zqk2));
         if ("block".equals(var6.Xqedii_272zqk2) && !var8.isBlank()) {
            var6.Xqedii_272zqk2 = "item";
         }

         if ("item".equals(var6.Xqedii_272zqk2)) {
            String[] var9 = Xqedii_3d82szb(var8);
            if (var9 != null) {
               var6.Xqedii_272zqk2 = "player_head";
               var6.Xqedii_20r4t4p = var9[0];
            } else {
               var6.Xqedii_36gp88t = var8;
               var6.Xqedii_3gjia31 = Xqedii_ogx1u5p(var2);
            }
         }

         var6.Xqedii_2bczw3w = Xqedii_1pwj2j6(
            var2.get("visible"),
            true
         );
         var6.Xqedii_3mu4xzh = ((!var6.Xqedii_2bczw3w
            ? 1
            : 0) != 0);
         var6.Xqedii_prfwkef = Xqedii_ulr0k7d(
            var2.get("layer"),
            Double.longBitsToDouble(0L)
         );
         this.Xqedii_1cmbrk8(var2, var6);
         Map var15 = Xqedii_30dd4qv(var2.get("position"));
         Map var10 = Xqedii_30dd4qv(var2.get("size"));
         var6.Xqedii_3l6f7ti = var15 == null
            ? Double.longBitsToDouble(0L)
            : Xqedii_ulr0k7d(
               var15.get("x"),
               Double.longBitsToDouble(0L)
            );
         var6.Xqedii_10bvdlm = var15 == null
            ? Double.longBitsToDouble(0L)
            : Xqedii_ulr0k7d(
               var15.get("y"),
               Double.longBitsToDouble(0L)
            );
         var6.Xqedii_35yqe6o = var10 == null
            ? Double.longBitsToDouble(0L)
            : Xqedii_ulr0k7d(
               var10.get("width"),
               Double.longBitsToDouble(0L)
            );
         var6.Xqedii_zgu3xeu = var10 == null
            ? Double.longBitsToDouble(0L)
            : Xqedii_ulr0k7d(
               var10.get("height"),
               Double.longBitsToDouble(0L)
            );
         if (var6.Xqedii_35yqe6o <= Double.longBitsToDouble(0L)) {
            var6.Xqedii_35yqe6o = Double.longBitsToDouble(4607182418800017408L);
         }

         if (var6.Xqedii_zgu3xeu <= Double.longBitsToDouble(0L)) {
            var6.Xqedii_zgu3xeu = Double.longBitsToDouble(4607182418800017408L);
         }

         if (var6.Xqedii_221jxyb()) {
            this.Xqedii_2zqs0e6(var2);
         }

         var6.Xqedii_2lnnf2d = Xqedii_23ctyx4(
            Xqedii_1qe7ua6(
               var2.get("color"),
               "ffffff"
            )
         );
         var6.Xqedii_3dznsnb = this.Xqedii_29azhaf(var6.Xqedii_272zqk2, var2);
         this.Xqedii_2mecxa7(var2, var6);
         this.Xqedii_38z544e(var2, var6, var3, var4);
         String var11 = var6.Xqedii_272zqk2;
         int var12 = -1;
         switch (var11.hashCode()) {
            case 3532157:
               if (var11.equals("skin")) {
                  var12 = 4;
               }
               break;
            case 3556653:
               if (var11.equals("text")) {
                  var12 = 0;
               }
               break;
            case 100313435:
               if (var11.equals("image")) {
                  var12 = 1;
               }
               break;
            case 100358090:
               if (var11.equals("input")) {
                  var12 = 3;
               }
               break;
            case 1385468589:
               if (var11.equals("rounded")) {
                  var12 = 2;
               }
         }

         switch (var12) {
            case 0:
               String[] var10000 = new String[2];
               var10000[0] = Xqedii_1qe7ua6(
                  var2.get("text"),
                  ""
               );
               var10000[1] = Xqedii_1qe7ua6(
                  var2.get("unicode"),
                  ""
               );
               String var13 = Xqedii_39mdkmx(var10000);
               String var14 = Xqedii_2usvjac(var13);
               if (var14 != null) {
                  var6.Xqedii_272zqk2 = "player_head";
                  var6.Xqedii_20r4t4p = var14;
                  var6.Xqedii_zgu3xeu = var6.Xqedii_35yqe6o;
               } else {
                  var6.Xqedii_2zm1if4 = Xqedii_do9n6op(Xqedii_1594xsq(var13));
                  var6.Xqedii_34ivekx = Xqedii_1qe7ua6(
                        var2.get("align"),
                        "left"
                     )
                     .toLowerCase(Locale.ROOT);
                  var6.Xqedii_2xkehob = Xqedii_1qe7ua6(
                     var2.get("font"),
                     "default"
                  );
                  var6.Xqedii_1kwgg41 = ((!var6.Xqedii_2zm1if4.isBlank()
                     ? 1
                     : 0) != 0);
                  this.Xqedii_11e67eq(var6);
               }
               break;
            case 1:
               var6.Xqedii_3ouyp5a = Xqedii_2xjes65(
                  Xqedii_1qe7ua6(
                     var2.get("image"),
                     ""
                  )
               );
               if (var6.Xqedii_3ouyp5a.isBlank()) {
                  var3.add(Xqedii_2r5alt2o5ml1q(var4, var6.Xqedii_oruxzxc));
                  return null;
               }
               break;
            case 2:
               this.Xqedii_1hzg3ze(var2, var6);
               break;
            case 3:
               this.Xqedii_1ijxo7j(var2, var6);
               break;
            case 4:
               this.Xqedii_31mui1x(var2, var6);
         }

         return "hitbox".equals(var6.Xqedii_272zqk2)
               && !var6.Xqedii_pxr9qfs()
               && !var6.Xqedii_5ommxdp
               && var6.Xqedii_350upak == null
            ? null
            : var6;
      }
   }

   public static String Xqedii_25do604(Map<String, Object> var0, boolean var1) {
      {
         ArrayList var2 = new ArrayList<>(
            List.of(
               "item",
               "item.material",
               "params.item",
               "params.item.material"
            )
         );
         if (var1) {
            var2.add("material");
            var2.add("params.material");
         }

         for (String var4 : (Iterable<String>) (var2)) {

            Object var5 = var4.contains(".")
               ? Xqedii_1pszdex(var0, var4)
               : var0.get(var4);
            if (var5 != null && !(var5 instanceof Map) && !(var5 instanceof ConfigurationSection) && !(var5 instanceof List)) {
               String var6 = String.valueOf(var5).trim();
               if (!var6.isBlank()) {
                  return var6;
               }
            }
         }

         return "";
      }
   }

   public static boolean Xqedii_ogx1u5p(Map<String, Object> var0) {
      {
         String[] var10000 = new String[6];
         var10000[0] = "glowing";
         var10000[1] = "item.glowing";
         var10000[2] = "item.glint";
         var10000[3] = "params.glowing";
         var10000[4] = "params.item.glowing";
         var10000[5] = "params.item.glint";
         String[] var1 = var10000;
         int var2 = var1.length;

         for (int var3 = 0; var3 < var2; var3++) {
            String var4 = var1[var3];
            Object var5 = var4.contains(".")
               ? Xqedii_1pszdex(var0, var4)
               : var0.get(var4);
            if (var5 != null) {
               return ((!Xqedii_1pwj2j6(var5, false)
                     && !"enabled"
                        .equalsIgnoreCase(String.valueOf(var5).trim())
                  ? 0
                  : 1) != 0);
            }
         }

         return false;
      }
   }

   public Xqedii_3duv2su Xqedii_hnlsyy6(Map<String, Object> var1, String var2) {
      {
         Map var3 = Xqedii_30dd4qv(
            var1.get("__uui_scroll_view")
         );
         if (var3 == null) {
            return null;
         } else {
            Xqedii_3duv2su var4 = new Xqedii_3duv2su();
            var4.Xqedii_2h2vht7 = Xqedii_1qe7ua6(
               var3.get("id"),
               ""
            );
            if (var4.Xqedii_2h2vht7.isBlank()) {
               return null;
            } else {
               var4.Xqedii_b1uaqqt = var2 == null ? "" : var2;
               var4.Xqedii_366js72 = Xqedii_ulr0k7d(
                  var3.get("x"),
                  Double.longBitsToDouble(0L)
               );
               var4.Xqedii_1zzzeuc = Xqedii_ulr0k7d(
                  var3.get("y"),
                  Double.longBitsToDouble(0L)
               );
               var4.Xqedii_3ihceot = Math.max(
                  Double.longBitsToDouble(4607182418800017408L),
                  Xqedii_ulr0k7d(
                     var3.get("width"),
                     Double.longBitsToDouble(4607182418800017408L)
                  )
               );
               var4.Xqedii_232d414 = Math.max(
                  Double.longBitsToDouble(4607182418800017408L),
                  Xqedii_ulr0k7d(
                     var3.get("height"),
                     Double.longBitsToDouble(4607182418800017408L)
                  )
               );
               var4.Xqedii_14byvw9 = Math.max(
                  Double.longBitsToDouble(0L),
                  Xqedii_ulr0k7d(
                     var3.get("contentWidth"),
                     var4.Xqedii_3ihceot
                  )
               );
               var4.Xqedii_15r0oze = Math.max(
                  Double.longBitsToDouble(0L),
                  Xqedii_ulr0k7d(
                     var3.get("contentHeight"),
                     var4.Xqedii_232d414
                  )
               );
               var4.Xqedii_2zaoqj1 = Xqedii_1pwj2j6(
                  var3.get("vertical"),
                  true
               );
               var4.Xqedii_20znvyd = Xqedii_1pwj2j6(
                  var3.get("horizontal"),
                  false
               );
               var4.Xqedii_37hovis = Xqedii_1pwj2j6(
                  var3.get("scrollbar"),
                  true
               );
               var4.Xqedii_1k53pgm = Math.max(
                  Double.longBitsToDouble(4607182418800017408L),
                  Xqedii_ulr0k7d(
                     var3.get("scrollbarWidth"),
                     Double.longBitsToDouble(4618441417868443648L)
                  )
               );
               var4.Xqedii_2gekh4q = Xqedii_23ctyx4(
                  Xqedii_1qe7ua6(
                     var3.get("scrollbarColor"),
                     "ffffff"
                  )
               );
               var4.Xqedii_rlw6fkn = Xqedii_23ctyx4(
                  Xqedii_1qe7ua6(
                     var3.get("scrollbarBackground"),
                     "000000"
                  )
               );
               return var4;
            }
         }
      }
   }

   public void Xqedii_22jicqr(Xqedii_2kb1ns6 var1, Map<String, Object> var2) {
      {
         Map var3 = Xqedii_30dd4qv(
            var2.get("__uui_filter_view")
         );
         if (var3 != null) {
            Xqedii_1tuv3qh var4 = new Xqedii_1tuv3qh();
            var4.Xqedii_rr7d9qc = Xqedii_1qe7ua6(
               var3.get("id"),
               ""
            );
            var4.input = Xqedii_1qe7ua6(
                  var3.get("input"),
                  ""
               )
               .trim();
            if (!var4.Xqedii_rr7d9qc.isBlank() && !var4.input.isBlank()) {
               var4.Xqedii_u0seest = Xqedii_1qe7ua6(
                  var3.get("mode"),
                  "contains"
               );
               var4.Xqedii_1a7k3gv = Xqedii_1pwj2j6(
                  var3.get("caseSensitive"),
                  false
               );
               var4.Xqedii_4lseoco = Xqedii_1pwj2j6(
                  var3.get("deep"),
                  true
               );
               var4.Xqedii_2nfdm8k = Xqedii_1pwj2j6(
                  var3.get("emptyShowsAll"),
                  true
               );
               Object var6 = var3.get("keys");
               if (var6 instanceof List) {
                  for (Object var7 : (List)var6) {
                     String var8 = Xqedii_1qe7ua6(var7, "").trim();
                     if (!var8.isBlank()) {
                        var4.Xqedii_2v5s1tx.add(var8);
                     }
                  }
               }

               if (var4.Xqedii_2v5s1tx.isEmpty()) {
                  var4.Xqedii_2v5s1tx
                     .addAll(
                        List.of(
                           "name",
                           "text",
                           "title",
                           "label"
                        )
                     );
               }

               var1.Xqedii_i5hlcvn.put(var4.Xqedii_rr7d9qc, var4);
            }
         }
      }
   }

   public void Xqedii_3c8rob5(Xqedii_2kb1ns6 var1, Xqedii_v7p1hqx var2, Map<String, Object> var3) {
      if (!var2.Xqedii_2v720ms.isBlank() && var2.Xqedii_1tl99gi >= 0) {
         Xqedii_1tuv3qh var4 = var1.Xqedii_i5hlcvn.get(var2.Xqedii_2v720ms);
         if (var4 != null) {
            Xqedii_148j5b1 var5 = var4.Xqedii_xafwbzr(var2.Xqedii_1tl99gi);
            if (var4.Xqedii_4lseoco || var5.Xqedii_3pjwd90.length() <= 0) {
               for (String var7 : var4.Xqedii_2v5s1tx) {
                  Object var8 = Xqedii_1pszdex(var3, var7);
                  if (var8 != null && !(var8 instanceof Map) && !(var8 instanceof List)) {
                     String var9 = Xqedii_3bdzawe(Xqedii_do9n6op(String.valueOf(var8)));
                     if (!var9.isBlank()) {
                        var5.Xqedii_3pjwd90.append(var9).append((char)10);
                     }
                  }
               }
            }
         }
      }
   }

   public static Object Xqedii_1pszdex(Map<String, Object> var0, String var1) {
      {
         Object var2 = var0;
         String[] var3 = var1.split("\\.");
         int var4 = var3.length;

         for (int var5 = 0; var5 < var4; var5++) {
            String var6 = var3[var5];
            Map var7 = Xqedii_30dd4qv(var2);
            if (var7 == null) {
               return null;
            }

            var2 = var7.get(var6);
            if (var2 == null) {
               return null;
            }
         }

         return var2;
      }
   }

   public static String Xqedii_3bdzawe(String var0) {
      {
         StringBuilder var1 = new StringBuilder(var0.length());

         for (int var2 = 0; var2 < var0.length(); var2++) {
            char var3 = var0.charAt(var2);
            if (var3 == 167 && var2 + 1 < var0.length()) {
               var2++;
            } else {
               var1.append(var3);
            }
         }

         return var1.toString().trim();
      }
   }

   public void Xqedii_1cmbrk8(Map<String, Object> var1, Xqedii_v7p1hqx var2) {
      {
         Map var3 = Xqedii_30dd4qv(var1.get("visibility"));
         if (var3 != null) {
            String var4 = Xqedii_1qe7ua6(
                  var3.get("placeholder"),
                  ""
               )
               .trim();
            if (!var4.isBlank()) {
               var2.Xqedii_3mv9hk2 = var4;
               var2.Xqedii_1q4a4o6 = Xqedii_1pwj2j6(
                  var3.get("visible"),
                  true
               );
            }
         }
      }
   }

   public void Xqedii_1ijxo7j(Map<String, Object> var1, Xqedii_v7p1hqx var2) {
      {
         var2.Xqedii_o5zonyl.putAll(var1);
         String[] var10001 = new String[6];
         var10001[0] = Xqedii_1qe7ua6(
            var1.get("input-id"),
            ""
         );
         var10001[1] = Xqedii_1qe7ua6(
            var1.get("inputId"),
            ""
         );
         var10001[2] = var2.Xqedii_oruxzxc;
         var10001[3] = Xqedii_1qe7ua6(
            var1.get("variable"),
            ""
         );
         var10001[4] = Xqedii_1qe7ua6(
            var1.get("name"),
            ""
         );
         var10001[5] = "input";
         var2.Xqedii_2okaiwk = Xqedii_39mdkmx(var10001).trim();
         var10001 = new String[2];
         var10001[0] = Xqedii_1qe7ua6(
            var1.get("variable"),
            ""
         );
         var10001[1] = var2.Xqedii_2okaiwk;
         var2.Xqedii_2ub1lix = Xqedii_39mdkmx(var10001).trim();
         var10001 = new String[3];
         var10001[0] = Xqedii_1qe7ua6(
            var1.get("placeholder"),
            ""
         );
         var10001[1] = Xqedii_1qe7ua6(
            Xqedii_1pszdex(var1, "text.placeholder"),
            ""
         );
         var10001[2] = Xqedii_1qe7ua6(
            var1.get("hint"),
            ""
         );
         var2.Xqedii_19ezjp5 = Xqedii_do9n6op(Xqedii_39mdkmx(var10001));
         var10001 = new String[2];
         var10001[0] = Xqedii_1qe7ua6(
            var1.get("default"),
            ""
         );
         var10001[1] = Xqedii_1qe7ua6(
            var1.get("value"),
            ""
         );
         var2.Xqedii_23ednv5 = Xqedii_39mdkmx(var10001);
         String[] var10000 = new String[2];
         var10000[0] = Xqedii_1qe7ua6(
            var1.get("max-length"),
            ""
         );
         var10000[1] = Xqedii_1qe7ua6(
            var1.get("maxLength"),
            ""
         );
         double var3 = Xqedii_ulr0k7d(Xqedii_39mdkmx(var10000), Double.longBitsToDouble(4632233691727265792L));
         var2.Xqedii_3k742qq = (int)Math.max(
            1L,
            Math.min(50L, Math.round(var3))
         );
         var10001 = new String[4];
         var10001[0] = Xqedii_1qe7ua6(
            var1.get("text-color"),
            ""
         );
         var10001[1] = Xqedii_1qe7ua6(
            Xqedii_1pszdex(var1, "text.color"),
            ""
         );
         var10001[2] = Xqedii_1qe7ua6(
            var1.get("textColor"),
            ""
         );
         var10001[3] = "ffffff";
         var2.Xqedii_1vycaiv = Xqedii_23ctyx4(Xqedii_39mdkmx(var10001));
         var10001 = new String[4];
         var10001[0] = Xqedii_1qe7ua6(
            var1.get("placeholder-color"),
            ""
         );
         var10001[1] = Xqedii_1qe7ua6(
            Xqedii_1pszdex(
               var1, "text.placeholder-color"
            ),
            ""
         );
         var10001[2] = Xqedii_1qe7ua6(
            var1.get("placeholderColor"),
            ""
         );
         var10001[3] = "7a7a7a";
         var2.Xqedii_2cab797 = Xqedii_23ctyx4(Xqedii_39mdkmx(var10001));
         var10001 = new String[4];
         var10001[0] = Xqedii_1qe7ua6(
            Xqedii_1pszdex(var1, "text.align"),
            ""
         );
         var10001[1] = Xqedii_1qe7ua6(
            var1.get("text-align"),
            ""
         );
         var10001[2] = Xqedii_1qe7ua6(
            var1.get("align"),
            ""
         );
         var10001[3] = "left";
         var2.Xqedii_34ivekx = Xqedii_39mdkmx(var10001).toLowerCase(Locale.ROOT);
         var10001 = new String[3];
         var10001[0] = Xqedii_1qe7ua6(
            Xqedii_1pszdex(var1, "text.font"),
            ""
         );
         var10001[1] = Xqedii_1qe7ua6(
            var1.get("font"),
            ""
         );
         var10001[2] = "default";
         var2.Xqedii_2xkehob = Xqedii_39mdkmx(var10001);
         double var22 = Double.longBitsToDouble(4634204016564240384L);
         String[] var10002 = new String[5];
         var10002[0] = "text.size";
         var10002[1] = "text-size";
         var10002[2] = "textSize";
         var10002[3] = "font-size";
         var10002[4] = "fontSize";
         double var5 = Xqedii_3mg4cv7(var1, var22, var10002) * Double.longBitsToDouble(4611686018427387904L);
         double var23 = Double.longBitsToDouble(4620693217682128896L);
         var10002 = new String[4];
         var10002[0] = "text.padding";
         var10002[1] = "text-padding";
         var10002[2] = "textPadding";
         var10002[3] = "padding";
         double var7 = Xqedii_3mg4cv7(var1, var23, var10002);
         double var9 = var5 * this.Xqedii_wrlou90 / this.Xqedii_166lwat;
         double var11 = var5 * Double.longBitsToDouble(4593071139967589854L);
         double var13 = var2.Xqedii_10bvdlm
            + (var2.Xqedii_zgu3xeu - var11) / Double.longBitsToDouble(4611686018427387904L)
            + var11;
         var2.Xqedii_wnnmnvv = var9;
         var2.Xqedii_16l0t04 = var2.Xqedii_3l6f7ti + var7;
         var2.Xqedii_3b8346h = Math.max(
            Double.longBitsToDouble(4607182418800017408L),
            var2.Xqedii_35yqe6o - Double.longBitsToDouble(4611686018427387904L) * var7
         );
         var2.Xqedii_3bpv3yh = var13 - var9;
         if (this.Xqedii_2ck9uvy(var1)) {
            this.Xqedii_1hzg3ze(var1, var2);
         }
      }
   }

   public boolean Xqedii_2ck9uvy(Map<String, Object> var1) {
      if (var1.containsKey("rounded")) {
         return Xqedii_1pwj2j6(
            var1.get("rounded"),
            false
         );
      } else {
         Object var2 = var1.get("rounding");
         if (var2 != null) {
            if (Xqedii_30dd4qv(var2) != null) {
               return true;
            } else {
               String var3 = Xqedii_1qe7ua6(var2, "")
                  .trim()
                  .toLowerCase(Locale.ROOT);
               return ((!var3.isBlank()
                     && !"none".equals(var3)
                     && !"false".equals(var3)
                  ? 1
                  : 0) != 0);
            }
         } else {
            return var1.containsKey("radius");
         }
      }
   }

   public void Xqedii_2zqs0e6(Map<String, Object> var1) {
      if (!var1.containsKey("color")) {
         String[] var10000 = new String[4];
         var10000[0] = Xqedii_1qe7ua6(
            Xqedii_1pszdex(var1, "background.color"),
            ""
         );
         var10000[1] = Xqedii_1qe7ua6(
            var1.get("background-color"),
            ""
         );
         var10000[2] = Xqedii_1qe7ua6(
            var1.get("backgroundColor"),
            ""
         );
         var10000[3] = Xqedii_30dd4qv(
                  var1.get("background")
               )
               == null
            ? Xqedii_1qe7ua6(
               var1.get("background"),
               ""
            )
            : "";
         String var2 = Xqedii_39mdkmx(var10000);
         if (!var2.isBlank()) {
            var1.put("color", var2);
         }
      }
   }

   public static double Xqedii_3mg4cv7(Map<String, Object> var0, double var1, String... var3) {
      {
         String[] var4 = var3;
         int var5 = var3.length;

         for (int var6 = 0; var6 < var5; var6++) {
            String var7 = var4[var6];
            Object var8 = var7.contains(".")
               ? Xqedii_1pszdex(var0, var7)
               : var0.get(var7);
            double var9 = Xqedii_ulr0k7d(var8, Double.longBitsToDouble(9221120237041090560L));
            if (Double.isFinite(var9) && var9 >= Double.longBitsToDouble(0L)) {
               return var9;
            }
         }

         return var1;
      }
   }

   public void Xqedii_31mui1x(Map<String, Object> var1, Xqedii_v7p1hqx var2) {
      {
         String[] var10000 = new String[2];
         var10000[0] = Xqedii_1qe7ua6(
            var1.get("skin"),
            ""
         );
         var10000[1] = "%player%";
         String var3 = Xqedii_39mdkmx(var10000).trim();
         var2.Xqedii_687jy1w = var3;
         String var4 = var3.toLowerCase(Locale.ROOT);
         var2.Xqedii_2fywzck = ((!var4.isBlank()
               && !var4.equals("%player%")
               && !var4.equals("%player_name%")
               && !var4.equals("%playername%")
            ? 0
            : 1) != 0);
         Map var5 = Xqedii_30dd4qv(var1.get("spin"));
         var2.Xqedii_3c7r0v5 = ((var5 != null
               && Xqedii_1pwj2j6(
                  var5.get("mouse"),
                  false
               )
            ? 1
            : 0) != 0);
      }
   }

   public void Xqedii_11e67eq(Xqedii_v7p1hqx var1) {
      {
         double var2 = var1.Xqedii_zgu3xeu;
         double var4 = var2 * this.Xqedii_wrlou90 / this.Xqedii_166lwat;
         double var6 = var1.Xqedii_10bvdlm + var2 * Double.longBitsToDouble(4593071139967589854L);
         var1.Xqedii_wnnmnvv = var4;
         var1.Xqedii_10bvdlm = var6 - var4;
         var1.Xqedii_zgu3xeu = Math.max(Double.longBitsToDouble(4607182418800017408L), var4);
      }
   }

   public static String Xqedii_2usvjac(String var0) {
      {
         String var1 = var0 == null ? "" : var0.trim();
         if (!var1.toLowerCase(Locale.ROOT)
            .startsWith("%player_head")) {
            return null;
         } else {
            int var2 = "%player_head".length();
            if (var2 >= var1.length()) {
               return null;
            } else {
               char var3 = var1.charAt(var2);
               if (var3 == 37) {
                  return var2 + 1 == var1.length()
                     ? "%player%"
                     : null;
               } else if (var3 != 58) {
                  return null;
               } else {
                  int var4 = Xqedii_27btmw1(var1, var2 + 1);
                  if (var4 != var1.length() - 1) {
                     return null;
                  } else {
                     String var5 = var1.substring(var2 + 1, var4).trim();
                     return var5.isBlank() ? "%player%" : var5;
                  }
               }
            }
         }
      }
   }

   public static String Xqedii_1594xsq(String var0) {
      if (var0 != null
         && var0.toLowerCase(Locale.ROOT)
            .contains("%player_head")) {
         String var1 = var0.toLowerCase(Locale.ROOT);
         StringBuilder var2 = new StringBuilder(var0.length());
         int var3 = 0;
         int var4 = 0;

         while (true) {
            int var5 = var1.indexOf("%player_head", var4);
            if (var5 < 0) {
               break;
            }

            int var6 = var5 + "%player_head".length();
            if (var6 >= var0.length()) {
               break;
            }

            char var7 = var0.charAt(var6);
            int var8;
            if (var7 == 37) {
               var8 = var6 + 1;
            } else {
               if (var7 != 58) {
                  var4 = var6;
                  continue;
               }

               int var9 = Xqedii_27btmw1(var0, var6 + 1);
               if (var9 < 0) {
                  var4 = var6;
                  continue;
               }

               var8 = var9 + 1;
            }

            var2.append(var0, var3, var5);
            var3 = var8;
            var4 = var8;
         }

         var2.append(var0, var3, var0.length());
         return var2.toString();
      } else {
         return var0 == null ? "" : var0;
      }
   }

   public static int Xqedii_27btmw1(String var0, int var1) {
      {
         int var2 = var1;

         while (var2 < var0.length()) {
            if (var0.charAt(var2) == 37) {
               int var3 = var0.indexOf(37, var2 + 1);
               if (var3 <= var2 + 1
                  || !Xqedii_26baxgl(var0, var2 + 1, var3)
                  || var0.indexOf(37, var3 + 1) < 0) {
                  return var2;
               }

               var2 = var3 + 1;
            } else {
               var2++;
            }
         }

         return -1;
      }
   }

   public static boolean Xqedii_26baxgl(String var0, int var1, int var2) {
      {
         for (int var3 = var1; var3 < var2; var3++) {
            char var4 = var0.charAt(var3);
            if (!Character.isLetterOrDigit(var4)
               && var4 != 95
               && var4 != 45
               && var4 != 46
               && var4 != 58) {
               return false;
            }
         }

         return true;
      }
   }

   public static String[] Xqedii_3d82szb(String var0) {
      {
         String var1 = var0 == null ? "" : var0.trim();
         if (var1.isEmpty()) {
            return null;
         } else {
            if (var1.toLowerCase(Locale.ROOT)
               .startsWith("minecraft:")) {
               var1 = var1.substring("minecraft:".length());
            }

            int var2 = var1.indexOf(58);
            String var3 = (var2 >= 0 ? var1.substring(0, var2) : var1).trim().toLowerCase(Locale.ROOT);
            if (!"player_head".equals(var3)
               && !"skull_item".equals(var3)) {
               return null;
            } else {
               String var4 = var2 >= 0
                  ? var1.substring(var2 + 1).trim()
                  : "";
               String[] var10000 = new String[1];
               var10000[0] = var4.isBlank()
                  ? "%player%"
                  : var4;
               return var10000;
            }
         }
      }
   }

   public String Xqedii_3eva3om(String var1) {
      {
         int var3 = -1;
         switch (var1.hashCode()) {
            case -1217012392:
               if (var1.equals("hitbox")) {
                  var3 = 3;
               }
               break;
            case 3242771:
               if (var1.equals("item")) {
                  var3 = 4;
               }
               break;
            case 3532157:
               if (var1.equals("skin")) {
                  var3 = 9;
               }
               break;
            case 3556653:
               if (var1.equals("text")) {
                  var3 = 2;
               }
               break;
            case 100313435:
               if (var1.equals("image")) {
                  var3 = 1;
               }
               break;
            case 100358090:
               if (var1.equals("input")) {
                  var3 = 5;
               }
               break;
            case 470718913:
               if (var1.equals("inputbox")) {
                  var3 = 8;
               }
               break;
            case 1385468589:
               if (var1.equals("rounded")) {
                  var3 = 0;
               }
               break;
            case 1705792840:
               if (var1.equals("input-box")) {
                  var3 = 6;
               }
               break;
            case 1707282390:
               if (var1.equals("input_box")) {
                  var3 = 7;
               }
         }
         return switch (var3) {
            case 0 -> "rounded";
            case 1 -> "image";
            case 2 -> "text";
            case 3 -> "hitbox";
            case 4 -> "item";
            case 5, 6, 7, 8 -> "input";
            case 9 -> "skin";
            default -> "block";
         };
      }
   }

   public double Xqedii_29azhaf(String var1, Map<String, Object> var2) {
      if (!"image".equals(var1)
         && !"hitbox".equals(var1)) {
         double var3 = Xqedii_ulr0k7d(
            var2.get("opacity"),
            Double.longBitsToDouble(4643176031446892544L)
         );
         return Math.max(
            Double.longBitsToDouble(0L),
            Math.min(
               Double.longBitsToDouble(4607182418800017408L),
               var3 / Double.longBitsToDouble(4643176031446892544L)
            )
         );
      } else {
         return Double.longBitsToDouble(4607182418800017408L);
      }
   }

   public void Xqedii_1hzg3ze(Map<String, Object> var1, Xqedii_v7p1hqx var2) {
      {
         var2.Xqedii_21vvlvv = this.Xqedii_2skqlwh;
         double var3 = Xqedii_ulr0k7d(
            var1.get("radius"),
            Double.longBitsToDouble(9221120237041090560L)
         );
         Object var5 = var1.get("rounding");
         if (var5 instanceof String var10) {
            String var11 = var10.trim().toLowerCase(Locale.ROOT);
            if (!"none".equals(var11)
               && !"false".equals(var11)) {
               for (int var8 = 0; var8 < Xqedii_2cjopzz.length; var8++) {
                  if (Xqedii_2cjopzz[var8].equals(var11)) {
                     var2.Xqedii_21vvlvv = Xqedii_p4ue641[var8];
                  }
               }

               if (Double.isFinite(var3) && var3 > Double.longBitsToDouble(0L)) {
                  var2.Xqedii_21vvlvv = var3;
               }

               Xqedii_3gz1zos(var2);
            }
         } else {
            Map var6 = Xqedii_30dd4qv(var5);
            if (var6 == null) {
               if (Double.isFinite(var3) && var3 > Double.longBitsToDouble(0L)) {
                  var2.Xqedii_21vvlvv = var3;
               }

               Xqedii_3gz1zos(var2);
            } else {
               double var7 = Xqedii_ulr0k7d(var6.get("radius"), var3);
               if (Double.isFinite(var7) && var7 > Double.longBitsToDouble(0L)) {
                  var2.Xqedii_21vvlvv = var7;
               }

               Map var9 = Xqedii_30dd4qv(var6.get("unicode"));
               if (var9 == null) {
                  Xqedii_3gz1zos(var2);
               } else {
                  var2.Xqedii_2wmm1xa = ((var9.get("tl") != null
                     ? 1
                     : 0) != 0);
                  var2.Xqedii_19ovobf = ((var9.get("tr") != null
                     ? 1
                     : 0) != 0);
                  var2.Xqedii_3qu3hp4 = ((var9.get("br") != null
                     ? 1
                     : 0) != 0);
                  var2.Xqedii_2y1xcjw = ((var9.get("bl") != null
                     ? 1
                     : 0) != 0);
               }
            }
         }
      }
   }

   public static void Xqedii_3gz1zos(Xqedii_v7p1hqx var0) {
      {
         var0.Xqedii_2wmm1xa = true;
         var0.Xqedii_19ovobf = true;
         var0.Xqedii_3qu3hp4 = true;
         var0.Xqedii_2y1xcjw = true;
      }
   }

   public void Xqedii_2mecxa7(Map<String, Object> var1, Xqedii_v7p1hqx var2) {
      {
         Object var3 = var1.get("actions");
         if (var3 instanceof ConfigurationSection var4) {
            var3 = var4.getList("actions");
         }

         if (var3 instanceof List) {
            for (Object var6 : (List)var3) {
               Map var7 = Xqedii_30dd4qv(var6);
               if (var7 != null && !var7.isEmpty()) {
                  var2.Xqedii_3o2c6jz.add(new LinkedHashMap<>(var7));
               }
            }
         }
      }
   }

   public void Xqedii_38z544e(Map<String, Object> var1, Xqedii_v7p1hqx var2, List<String> var3, String var4) {
      {
         Map var5 = Xqedii_30dd4qv(var1.get("hover"));
         if (var5 != null && !var5.isEmpty()) {
            String var6 = Xqedii_1qe7ua6(
               var5.get("color"),
               ""
            );
            if (!var6.isBlank() && !"none".equalsIgnoreCase(var6.trim())) {
               var2.Xqedii_1sxdt6k = Xqedii_23ctyx4(var6);
               var2.Xqedii_5ommxdp = true;
            }

            String var7 = Xqedii_2xjes65(
               Xqedii_1qe7ua6(
                  var5.get("image"),
                  ""
               )
            );
            if (!var7.isBlank()) {
               var2.Xqedii_29vci0a = var7;
               var2.Xqedii_5ommxdp = true;
            }

            String var8 = Xqedii_1qe7ua6(
               var5.get("effect"),
               ""
            );
            if (!var8.isBlank()) {
               Xqedii_4xfqqc9 var9 = this.Xqedii_2qmls65(var8);
               if (var9 == null) {
                  var3.add(Xqedii_qq5lz6s3zk5l(var4, var2.Xqedii_oruxzxc, var8));
               } else {
                  var2.Xqedii_3qyfu7f = var9.Xqedii_3ok0n10;
                  var2.Xqedii_39mt8ms = var9.Xqedii_21vfply;
                  var2.Xqedii_2ia1vvf = var9.Xqedii_3vrbexf;
                  var2.Xqedii_277q0eq = var9.Xqedii_3f9luab;
                  var2.Xqedii_2re3coj = var9.Xqedii_k040o9p / Double.longBitsToDouble(4643176031446892544L);
                  var2.Xqedii_5ommxdp = true;
                  if (Math.abs(var9.Xqedii_p1mac8o) > Double.longBitsToDouble(4547007122018943789L)) {
                     var3.add(Xqedii_13vzzcxa5l9iz(var4, var8, var9.Xqedii_p1mac8o));
                  }
               }
            }
         }
      }
   }

   public Xqedii_4xfqqc9 Xqedii_2qmls65(String var1) {
      {
         String var2 = var1.toLowerCase(Locale.ROOT).trim();
         if (this.Xqedii_1fy2tdo.containsKey(var2)) {
            return this.Xqedii_1fy2tdo.get(var2);
         } else {
            File var3 = new File(
               new File(
                  this.Xqedii_2ap3dg4, "contents/effects"
               ),
               Xqedii_1agcketwflzdc(var2)
            );
            Xqedii_4xfqqc9 var4 = null;
            if (var3.isFile()) {
               YamlConfiguration var5 = YamlConfiguration.loadConfiguration(var3);
               var4 = new Xqedii_4xfqqc9();
               var4.Xqedii_3ok0n10 = var5.getDouble(
                  "move-x",
                  Double.longBitsToDouble(0L)
               );
               var4.Xqedii_21vfply = var5.getDouble(
                  "move-y",
                  Double.longBitsToDouble(0L)
               );
               var4.Xqedii_3vrbexf = Double.longBitsToDouble(4607182418800017408L)
                  + this.Xqedii_bor2o9h(
                     var5.getString("scale-x"),
                     Double.longBitsToDouble(0L)
                  );
               var4.Xqedii_3f9luab = Double.longBitsToDouble(4607182418800017408L)
                  + this.Xqedii_bor2o9h(
                     var5.getString("scale-y"),
                     Double.longBitsToDouble(0L)
                  );
               var4.Xqedii_k040o9p = var5.getDouble(
                  "opacity-delta",
                  Double.longBitsToDouble(0L)
               );
               var4.Xqedii_p1mac8o = var5.getDouble(
                  "rotation-deg",
                  Double.longBitsToDouble(0L)
               );
            }

            this.Xqedii_1fy2tdo.put(var2, var4);
            return var4;
         }
      }
   }

   public double Xqedii_bor2o9h(String var1, double var2) {
      if (var1 != null && !var1.isBlank()) {
         String var4 = var1.trim();
         boolean var5 = var4.endsWith("%");
         if (var5) {
            var4 = var4.substring(0, var4.length() - 1)
               .trim();
         }

         try {
            double var6 = Double.parseDouble(var4);
            return var5 ? var6 / Double.longBitsToDouble(4636737291354636288L) : var6;
         } catch (NumberFormatException var8) {
            return var2;
         }
      } else {
         return var2;
      }
   }

   public Map<String, YamlConfiguration> Xqedii_31j3l8o(String var1) {
      {
         TreeMap var2 = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
         File var3 = new File(this.Xqedii_2ap3dg4, var1);
         File[] var4 = var3.listFiles();
         if (var4 == null) {
            return var2;
         } else {
            ArrayList<File> var5 = new ArrayList<>();
            File[] var6 = var4;
            int var7 = var4.length;

            for (int var8 = 0; var8 < var7; var8++) {
               File var9 = var6[var8];
               if (var9 != null
                  && var9.isFile()
                  && var9.getName().toLowerCase(Locale.ROOT).endsWith(".yml")) {
                  var5.add(var9);
               }
            }

            var5.sort((File var0, File var1x) -> {
               {
                  return var0.getName().compareToIgnoreCase(var1x.getName());
               }
            });

            for (File var12 : (Iterable<File>) (var5)) {

               String var13 = var12.getName();
               var13 = var13.substring(0, var13.length() - 4);

               try {
                  var2.put(var13, YamlConfiguration.loadConfiguration(var12));
               } catch (RuntimeException var10) {
               }
            }

            return var2;
         }
      }
   }

   public static Map<String, Object> Xqedii_30dd4qv(Object var0) {
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

   public static String Xqedii_1qe7ua6(Object var0, String var1) {
      if (var0 == null) {
         return var1;
      } else {
         String var2 = String.valueOf(var0);
         return var2.isEmpty() ? var1 : var2;
      }
   }

   public static double Xqedii_ulr0k7d(Object var0, double var1) {
      if (var0 instanceof Number var3) {
         return var3.doubleValue();
      } else if (var0 == null) {
         return var1;
      } else {
         try {
            return Double.parseDouble(String.valueOf(var0).trim());
         } catch (NumberFormatException var4) {
            return var1;
         }
      }
   }

   public static boolean Xqedii_1pwj2j6(Object var0, boolean var1) {
      if (var0 instanceof Boolean var3) {
         return var3;
      } else if (var0 == null) {
         return var1;
      } else {
         String var2 = String.valueOf(var0).trim();
         if ("true".equalsIgnoreCase(var2)) {
            return true;
         } else {
            return ("false".equalsIgnoreCase(var2)
               ? false
               : var1);
         }
      }
   }

   public static String Xqedii_39mdkmx(String... var0) {
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

   public static String Xqedii_2xjes65(String var0) {
      if (var0 != null && !var0.isBlank()) {
         StringBuilder var1 = new StringBuilder(var0.length());
         int var2 = 0;
         char[] var3 = var0.toCharArray();
         int var4 = var3.length;

         for (int var5 = 0; var5 < var4; var5++) {
            char var6 = var3[var5];
            if (!Character.isWhitespace(var6)
               && var6 != 95
               && var6 != 45) {
               char var7 = Character.toLowerCase(var6);
               if (var7 >= 97 && var7 <= 122
                  || Character.isDigit(var7)) {
                  var1.append(var7);
                  var2 = 0;
               }
            } else if (var2 == 0) {
               var1.append((char)95);
               var2 = 1;
            }
         }

         String var8 = var1.toString();

         while (var8.startsWith("_")) {
            var8 = var8.substring(1);
         }

         while (var8.endsWith("_")) {
            var8 = var8.substring(0, var8.length() - 1);
         }

         return var8;
      } else {
         return "";
      }
   }

   public static String Xqedii_23ctyx4(String var0) {
      if (var0 == null) {
         return "ffffff";
      } else {
         String var1 = var0.trim();
         if (var1.startsWith("<")
            && var1.endsWith(">")) {
            var1 = var1.substring(1, var1.length() - 1);
         }

         Matcher var2 = Xqedii_27ehu24.matcher(var1);
         return var2.matches()
            ? var2.group(1).toLowerCase(Locale.ROOT)
            : "ffffff";
      }
   }

   public static String Xqedii_do9n6op(String var0) {
      if (var0 != null && !var0.isEmpty()) {
         String var1 = Xqedii_xyhkbtz.matcher(var0).replaceAll("");
         StringBuilder var2 = new StringBuilder(var1.length());

         for (int var3 = 0; var3 < var1.length(); var3++) {
            char var4 = var1.charAt(var3);
            if (var4 == 38
               && var3 + 1 < var1.length()
               && Xqedii_3r3zph3(var1.charAt(var3 + 1))) {
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

   public static boolean Xqedii_3r3zph3(char var0) {
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
         Xqedii_13xok5r = List.of("editor");
         Xqedii_xyhkbtz = Pattern.compile(
            "</?[a-zA-Z0-9_#:/.-]+>"
         );
         Xqedii_27ehu24 = Pattern.compile(
            "(?i)^#?([0-9a-f]{6})$"
         );
         String[] var10000 = new String[4];
         var10000[0] = "small";
         var10000[1] = "regular";
         var10000[2] = "medium";
         var10000[3] = "large";
         Xqedii_2cjopzz = var10000;
         double[] var0 = new double[4];
         var0[0] = Double.longBitsToDouble(4613937818241073152L);
         var0[1] = Double.longBitsToDouble(4620693217682128896L);
         var0[2] = Double.longBitsToDouble(4623507967449235456L);
         var0[3] = Double.longBitsToDouble(4626322717216342016L);
         Xqedii_p4ue641 = var0;
      }
   }

   private static /* synthetic */ String Xqedii_2fz2bgmpc7dji(String string) {
        return Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"ESNdo4zGxtIWac67ULTe7H1IKkB6voRktC8kK/firsjZnn6xVlHBw+NqBTE7u2RebBg4BWMMyI8gthDJPdqxlnybc1HWxo/LpjDK52nrXvas637a") + string + Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"B+PUByqVX/o+xnOJtTVgnmlH+GpdS2yi1OSxsCZVk0YZLuQcThQEoEMoxlqFt8T7h51zzmvXZMPUCcVg5OkKvkmiDnSnIOdkuCXlkloTYc5Nn7vUeQsQu2cfD9Nkb01gt4IjaYGaJhA=");
    }

    private static /* synthetic */ String Xqedii_3ae8pa1vbanbq(String string) {
        return string + Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"6l4GzrUGEjvGeXtyMYryVDTTtSHR3d0HdUMuVGKQ");
    }

    private static /* synthetic */ String Xqedii_vp4hn1nmzmul(String string, String string2) {
        return Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"BoeSi/bDYChZw1HaWySA6UvvV9qvRNztHb/56mMi") + string + Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"jgoKEr/GrVgcaqKCuv2tPiFdnS5IAp2b/vmVmJ764UqO06F1hZGMorf++VzKdoBEG8KJleu1Tlx2f7MV8u8M1fKqqkXd13zAouO/7JEVSXN5biWQkvrJLA==") + string2;
    }

    private static /* synthetic */ String Xqedii_z080dd5n5ms8(String string) {
        return string + Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"tj30YtFn6F6XSPTmqvhD5NIfXpq+bxFja1g/UXbj");
    }

    private static /* synthetic */ String Xqedii_2r5alt2o5ml1q(String string, String string2) {
        return Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"BoeSi/bDYChZw1HaWySA6UvvV9qvRNztHb/56mMi") + string + Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"dxsIhPotro7Hfh/ENLyEcccf5IpEhM50PDA1+qN3EClTzjxOjCvJhkVgGRUR4/gqpZssWO49L/X2V4I9Tvs=") + string2 + Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"72LZgfGMl19Iy88xgCKT85K5IpfNd2i79do/tX8P1XxbCYDyGVJNCcciHFTEpY62uQ2L8x+HWoE1OFMetYeJV4fIk6SMhglcYwjhcL3qR5TBJ+BfW9mu8yqBevw8pCIaCjy6p0YpAy5N+pGC");
    }

    private static /* synthetic */ String Xqedii_qq5lz6s3zk5l(String string, String string2, String string3) {
        return Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"BoeSi/bDYChZw1HaWySA6UvvV9qvRNztHb/56mMi") + string + Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"BMKVJ0sT5GUp5yriXzVq9rzChgoMUIOIfDAK4OuxrAYLQetvDbqtfj0a1ZTSlRgW1Es=") + string2 + Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"kOKwLXakeQROyvQZY8UXlqvTReYg8P2GBPfxEQzRHF+Swe5eb73rjsa78u4xuq0/Fbv/LMgKkoqaFJbSfEwkzlxnpVMtMrJGc6DeOgLgo5kPmg==") + string3 + Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"0WteJo28+BoXEJdIhDoTOXOETmG4HY4BgBi1Ln3xhqQmuJ4JtG6w0SErtBgcGsXALyC0eQlOAz0HV2v7DdBEdAzWgE2h4vhcM/fVl49egR3uQym+rvVyIEPoF8IRofSWV+qLDed+G0cQjUKS/oEj7w==");
    }

    private static /* synthetic */ String Xqedii_13vzzcxa5l9iz(String string, String string2, double d) {
        return Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"BoeSi/bDYChZw1HaWySA6UvvV9qvRNztHb/56mMi") + string + Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"8UqNk5SgdN9UdVtNJSwclPB3A6n+L7nx6JoOISD73jXfZn+XGjTOwSeRLKJHO82s8/kI/4Hg+mCmkD30") + string2 + Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"DAvdhrq4h/W3HFsuT72P1V33bneQIokuaE1LY+szdhKuxFNGVPmy3CYbn53Is/qU8yDLwnDX") + d + Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"PJ0GS/zQg6+uxGvP9aJyXnAyOMBh8J1Mvj4xMdFRIJtSZTRk16rGblbvZCoWaBBVo/3cHhVLw9HiK6j4rPX3GcWFYxp8/JXRFeqZ/NdjRH9VXgpY0ftjTAiDvxzDV2ys++ZkiiHiLJDEujQnFelUyNgtyta2hESODfZHHAL78LlinwYx8/ZUnx6Fm2l18iSLlhNWfhgfDoUfOwj68dkQqjdaoKtBn+ss");
    }

    private static /* synthetic */ String Xqedii_1agcketwflzdc(String string) {
        return string + Xqedii_2fdvn86.Xqedii_nes6jgy0yask((String)"RsCK2hSVONExupln2/1vg4N+wf8vVL/cxVY5o04r8y2ATff1");
    }
}
