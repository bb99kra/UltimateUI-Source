package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.bossbar.BossbarAssets;
import dev.xqedii.ultimateUI.service.bossbar.BossbarHudSnapshot;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.format.Style.Merge.Strategy;
import net.kyori.adventure.text.format.TextDecoration.State;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public final class Xqedii_3ss2v1m {
   public static final MiniMessage Xqedii_7wvmt1t;
   public static final int Xqedii_1gcofrs = 10;
   public static final int Xqedii_10fke03 = -9;
   public static final int Xqedii_107lbcy = 200;
   public static final double Xqedii_1dsw8p9 = 4.0;
   public static final double Xqedii_2cvzrj0 = 1.0E-4;
   public static final int Xqedii_20ag3bx = 8192;
   public static final String Xqedii_3tvqlb5 = "cursor";
   public static final int Xqedii_3j180m9 = -1;
   public static final int Xqedii_1dbfctg = 0;
   public static final int Xqedii_34a21yo = 1;
   public static final int Xqedii_30mugo0 = 2;
   public static final int Xqedii_r9e4x3i = 3;
   public static final int Xqedii_12nofu9 = 4;
   public static final int Xqedii_291m0xk = 0;
   public static final int Xqedii_2no6ghv = 1;
   public static final int Xqedii_3v4hv3t = 2;
   public static final int Xqedii_33f303l = 3;
   public static final int Xqedii_3pkith0 = 4;
   public static final int Xqedii_3m92yuk = 16777215;
   public final BossbarAssets Xqedii_2oupt0w;
   public final Map<String, List<Xqedii_2q1o8r1>> Xqedii_wlo6exy = new ConcurrentHashMap<>();

   public Xqedii_3ss2v1m(BossbarAssets var1) {
      this.Xqedii_2oupt0w = var1;
   }

   public List<Xqedii_1il8kkh> Xqedii_1fbh4q0(List<BossbarHudSnapshot> var1, double var2) {
      {
         return this.Xqedii_21bfw7p(var1, var2).Xqedii_2p2y2tq;
      }
   }

   public Xqedii_2fhrc2q Xqedii_21bfw7p(List<BossbarHudSnapshot> var1, double var2) {
      {
         ArrayList var4 = new ArrayList();
         HashMap var5 = new HashMap();
         if (var1 != null && !var1.isEmpty() && this.Xqedii_2oupt0w.isReady()) {
            ArrayList var6 = new ArrayList(var1.size());

            for (int var7 = 0; var7 < var1.size(); var7++) {
               var6.add(var7);
            }

            var6.sort(Comparator.comparingDouble((Integer var1x) -> {
               {
                  return ((BossbarHudSnapshot)var1.get(var1x)).layer;
               }
            }));

            for (int var8 : (Iterable<Integer>) (var6)) {

               this.Xqedii_317p83t(var8, (BossbarHudSnapshot)var1.get(var8), var2, var4, var5);
            }

            return new Xqedii_2fhrc2q(var4, var5);
         } else {
            return new Xqedii_2fhrc2q(var4, var5);
         }
      }
   }

   public void Xqedii_317p83t(int var1, BossbarHudSnapshot var2, double var3, List<Xqedii_1il8kkh> var5, Map<Integer, double[]> var6) {
      {
         double var7 = var2.scaleY / Double.longBitsToDouble(4630826316843712512L);
         double var9 = var2.scaleX / Double.longBitsToDouble(4630826316843712512L);
         if (var7 > Double.longBitsToDouble(4547007122018943789L)
            && var9 > Double.longBitsToDouble(4547007122018943789L)) {
            if (var2.text != null && !var2.text.isEmpty()) {
               List var11 = this.Xqedii_16qvyx5(var2.text);
               if (!var11.isEmpty()) {
                  int var12 = var2.lineWidth > 0 ? var2.lineWidth : 200;
                  List var13 = this.Xqedii_1ex2kko(var11, var12);
                  double var14 = this.Xqedii_emb7cp4(var12);

                  for (List var17 : (Iterable<List>) (var13)) {

                     var14 = Math.max(var14, this.Xqedii_3vlyygu(var17));
                  }

                  ArrayList var30 = new ArrayList();
                  int var31 = var13.size();

                  for (int var18 = 0; var18 < var31; var18++) {
                     List var19 = (List)var13.get(var18);
                     double var20 = Double.longBitsToDouble(-4602115869219225600L)
                        - (var31 - 1 - var18)
                           * Double.longBitsToDouble(4621819117588971520L);

                     double var22 = switch (var2.alignment) {
                        case 0 -> Double.longBitsToDouble(0L);
                        case 2 -> var14 - this.Xqedii_3vlyygu(var19);
                        default -> (var14 - this.Xqedii_3vlyygu(var19))
                           / Double.longBitsToDouble(4611686018427387904L);
                     };
                     double var24 = var22;

                     for (Xqedii_2q1o8r1 var27 : (Iterable<Xqedii_2q1o8r1>) (var19)) {

                        double var28 = Double.longBitsToDouble(4607182418800017408L)
                           - var14 / Double.longBitsToDouble(4611686018427387904L)
                           + var24;
                        this.Xqedii_3t5btl1(var2, var27, var28, var20, var9, var7, var30);
                        var24 += var27.Xqedii_1q7ydj6;
                     }
                  }

                  int var32 = var2.quarterTurns & 3;
                  if (var32 != 0) {
                     for (Xqedii_3tb6tlp var37 : (Iterable<Xqedii_3tb6tlp>) (var30)) {

                        if (var37.Xqedii_15q7uiy == 3
                           || var37.Xqedii_15q7uiy == 4
                           || var37.Xqedii_15q7uiy == 2) {
                           var6.put(var1, Xqedii_1phsaxs(var30));
                           return;
                        }
                     }

                     for (Xqedii_3tb6tlp var38 : (Iterable<Xqedii_3tb6tlp>) (var30)) {

                        Xqedii_1rg6vy0(var38, var32, var2.anchorX, var2.anchorY);
                     }
                  }

                  for (Xqedii_3tb6tlp var39 : (Iterable<Xqedii_3tb6tlp>) (var30)) {

                     if (var39.Xqedii_15q7uiy == 1
                        && !Xqedii_3c2h973(var39, 256)) {
                        if (var32 != 0 || var39.Xqedii_2kjvjpk == null) {
                           var6.put(var1, Xqedii_1phsaxs(var30));
                           return;
                        }

                        var39.Xqedii_15q7uiy = 2;
                        var39.Xqedii_afvuuz5 = var39.Xqedii_287sji6;
                        var39.Xqedii_toc5rf5 = var39.Xqedii_1vj9m67;
                        var39.Xqedii_2voqgr2 = var39.Xqedii_2rbzow1;
                        var39.Xqedii_6aej5v1 = var39.Xqedii_4dwpawt;
                     }

                     if (var39.Xqedii_15q7uiy == 2
                        && !Xqedii_3c2h973(var39, 1024)) {
                        var6.put(var1, Xqedii_1phsaxs(var30));
                        return;
                     }
                  }

                  int var36 = "cursor".equalsIgnoreCase(var2.id)
                     ? -1
                     : 0;

                  for (Xqedii_3tb6tlp var21 : (Iterable<Xqedii_3tb6tlp>) (var30)) {

                     this.Xqedii_9lvb14k(var1, var2, var21, var36, var3, var5);
                  }
               }
            }
         }
      }
   }

   public void Xqedii_3t5btl1(BossbarHudSnapshot var1, Xqedii_2q1o8r1 var2, double var3, double var5, double var7, double var9, List<Xqedii_3tb6tlp> var11) {
      {
         double var12 = var1.anchorX + var3 * var7;
         double var14 = var1.anchorY + var5 * var9;
         switch (var2.Xqedii_2f1zu9z) {
            case 1:
               this.Xqedii_16vfeau(
                  var2,
                  var12,
                  var14,
                  var7,
                  var9,
                  (((var1.quarterTurns & 3) != 0
                     ? 1
                     : 0) != 0),
                  var11
               );
               break;
            case 2:
               this.Xqedii_133l7ng(var2, var12, var14, var7, var9, var11);
               break;
            case 3:
               this.Xqedii_2ohybb1(var2, var12, var14, var7, var9, var11);
         }
      }
   }

   public void Xqedii_16vfeau(Xqedii_2q1o8r1 var1, double var2, double var4, double var6, double var8, boolean var10, List<Xqedii_3tb6tlp> var11) {
      {
         BossbarAssets.Sprite var12 = var1.Xqedii_2bcofvn;
         double var13 = var4 + (7 - var12.ascent) * var8;
         double var15 = var12.quadWidthFont * var6;
         double var17 = var12.quadHeightFont * var8;
         Xqedii_3tb6tlp var19 = new Xqedii_3tb6tlp();
         var19.Xqedii_2q1klot = var1.Xqedii_xeufmmf;
         if (var12.fill) {
            var19.Xqedii_15q7uiy = 0;
            var19.Xqedii_afvuuz5 = var2;
            var19.Xqedii_toc5rf5 = var13;
            var19.Xqedii_2voqgr2 = var2 + var15;
            var19.Xqedii_6aej5v1 = var13 + var17;
            var11.add(var19);
         } else {
            var19.Xqedii_2kjvjpk = var12;
            var19.Xqedii_287sji6 = var2;
            var19.Xqedii_1vj9m67 = var13;
            var19.Xqedii_2rbzow1 = var2 + var15 * var12.padScaleX;
            var19.Xqedii_4dwpawt = var13 + var17 * var12.padScaleY;
            var19.Xqedii_3q4hzk8 = var2 + var12.opaqueLeft * var15;
            var19.Xqedii_3olagji = var13 + var12.opaqueTop * var17;
            var19.Xqedii_h7shh3s = var2 + var12.opaqueRight * var15;
            var19.Xqedii_1xwisjg = var13 + var12.opaqueBottom * var17;
            BossbarAssets.Mask var20 = var12.mask();
            int var21 = (var1.Xqedii_xeufmmf & 16777215) != 16777215
               ? 1
               : 0;
            if (var20 == null || !var20.builtin && var21 == 0 && !var10) {
               var19.Xqedii_15q7uiy = 2;
               var19.Xqedii_afvuuz5 = var19.Xqedii_287sji6;
               var19.Xqedii_toc5rf5 = var19.Xqedii_1vj9m67;
               var19.Xqedii_2voqgr2 = var19.Xqedii_2rbzow1;
               var19.Xqedii_6aej5v1 = var19.Xqedii_4dwpawt;
            } else {
               var19.Xqedii_15q7uiy = 1;
               var19.Xqedii_afvuuz5 = var2 + var20.left * var15;
               var19.Xqedii_toc5rf5 = var13 + var20.top * var17;
               var19.Xqedii_2voqgr2 = var2 + var20.right * var15;
               var19.Xqedii_6aej5v1 = var13 + var20.bottom * var17;
               var19.Xqedii_1z62bwh = var20.set;
               var19.Xqedii_bpg2gyb = var20.rotation;
            }

            var11.add(var19);
         }
      }
   }

   public static int Xqedii_2lh3rqm(double var0, double var2) {
      {
         return (int)Math.round(var2) - (int)Math.round(var0);
      }
   }

   public static boolean Xqedii_3c2h973(Xqedii_3tb6tlp var0, int var1) {
      {
         return ((Xqedii_2lh3rqm(var0.Xqedii_afvuuz5, var0.Xqedii_2voqgr2) <= var1 && Xqedii_2lh3rqm(var0.Xqedii_toc5rf5, var0.Xqedii_6aej5v1) <= var1
            ? 1
            : 0) != 0);
      }
   }

   public void Xqedii_133l7ng(Xqedii_2q1o8r1 var1, double var2, double var4, double var6, double var8, List<Xqedii_3tb6tlp> var10) {
      {
         BossbarAssets.TextFace var11 = var1.Xqedii_3tj78o2;
         int var12 = var11.resolveClass(var11.providerSize * var8);
         if (var12 >= 0) {
            double var13 = var4
               + Double.longBitsToDouble(4619567317775286272L) * var8
               - Double.longBitsToDouble(4619567317775286272L);
            double var15 = var2 + Math.max(Double.longBitsToDouble(4607182418800017408L), var1.Xqedii_1q7ydj6 * var6);
            double var17 = var4 + Double.longBitsToDouble(4621819117588971520L) * var8;
            String var19 = var11.fontKey(var12);
            this.Xqedii_5pfj2a2(
               var10,
               3,
               var1,
               var19,
               0,
               var2,
               var13,
               var2 - Double.longBitsToDouble(4607182418800017408L),
               var4,
               var15 + Double.longBitsToDouble(4607182418800017408L),
               var17
            );
            if (var1.Xqedii_27itpdq) {
               double var20 = var2 + Math.max(Double.longBitsToDouble(4607182418800017408L), (double)Math.round(var6));
               this.Xqedii_5pfj2a2(
                  var10,
                  3,
                  var1,
                  var19,
                  0,
                  var20,
                  var13,
                  var2 - Double.longBitsToDouble(4607182418800017408L),
                  var4,
                  var15 + Double.longBitsToDouble(4607182418800017408L),
                  var17
               );
            }
         }
      }
   }

   public void Xqedii_2ohybb1(Xqedii_2q1o8r1 var1, double var2, double var4, double var6, double var8, List<Xqedii_3tb6tlp> var10) {
      {
         BossbarAssets.VanillaGlyph var11 = var1.Xqedii_27at3tw;
         int var12 = this.Xqedii_2oupt0w.resolveVanillaClass(var8);
         if (var12 >= 0) {
            int var13 = this.Xqedii_2oupt0w.vanillaClassHeight(var12);
            BossbarAssets.VanillaProvider var14 = var11.provider;
            double var15 = var4 + (7 - var14.ascent) * var8;
            double var17 = var15 - Double.longBitsToDouble(4619567317775286272L) + var14.scaledAscent(var13);
            double var19 = var15 + var14.scaledHeight(var13);
            double var21 = var2 + var11.scaledWidth(var13);
            String var23 = Xqedii_30g0ulj.Xqedii_3u38bb9(var12);
            int var24 = var11.scaledAdvance(var13);
            this.Xqedii_5pfj2a2(var10, 4, var1, var23, var24, var2, var17, var2, var15, var21, var19);
            if (var1.Xqedii_27itpdq) {
               double var25 = var2 + Math.max(Double.longBitsToDouble(4607182418800017408L), (double)Math.round(var6));
               this.Xqedii_5pfj2a2(
                  var10,
                  4,
                  var1,
                  var23,
                  var24,
                  var25,
                  var17,
                  var2,
                  var15,
                  var21 + Double.longBitsToDouble(4607182418800017408L),
                  var19
               );
            }
         }
      }
   }

   public void Xqedii_5pfj2a2(
      List<Xqedii_3tb6tlp> var1,
      int var2,
      Xqedii_2q1o8r1 var3,
      String var4,
      int var5,
      double var6,
      double var8,
      double var10,
      double var12,
      double var14,
      double var16
   ) {
      {
         Xqedii_3tb6tlp var18 = new Xqedii_3tb6tlp();
         var18.Xqedii_15q7uiy = var2;
         var18.Xqedii_2q1klot = var3.Xqedii_xeufmmf;
         var18.Xqedii_1tn3rkq = var3.Xqedii_2zyef1q;
         var18.Xqedii_2jbr678 = var3.Xqedii_3kr6lzk;
         var18.Xqedii_1kuf0l5 = var4;
         var18.Xqedii_qxqxmxp = var5;
         var18.Xqedii_31odcf6 = var6;
         var18.Xqedii_2ljrj1c = var8;
         var18.Xqedii_afvuuz5 = var10;
         var18.Xqedii_toc5rf5 = var12;
         var18.Xqedii_2voqgr2 = var14;
         var18.Xqedii_6aej5v1 = var16;
         var1.add(var18);
      }
   }

   public static double[] Xqedii_1phsaxs(List<Xqedii_3tb6tlp> var0) {
      {
         double[] var1 = null;

         for (Xqedii_3tb6tlp var3 : var0) {
            if (var1 == null) {
               double[] var10000 = new double[4];
               var10000[0] = var3.Xqedii_afvuuz5;
               var10000[1] = var3.Xqedii_toc5rf5;
               var10000[2] = var3.Xqedii_2voqgr2;
               var10000[3] = var3.Xqedii_6aej5v1;
               var1 = var10000;
            } else {
               var1[0] = Math.min(
                  var1[0], var3.Xqedii_afvuuz5
               );
               var1[1] = Math.min(
                  var1[1], var3.Xqedii_toc5rf5
               );
               var1[2] = Math.max(
                  var1[2], var3.Xqedii_2voqgr2
               );
               var1[3] = Math.max(
                  var1[3], var3.Xqedii_6aej5v1
               );
            }
         }

         return var1;
      }
   }

   public static void Xqedii_1rg6vy0(Xqedii_3tb6tlp var0, int var1, double var2, double var4) {
      {
         double var6 = var0.Xqedii_afvuuz5;
         double var8 = var0.Xqedii_toc5rf5;
         double var10 = var0.Xqedii_2voqgr2;
         double var12 = var0.Xqedii_6aej5v1;
         switch (var1) {
            case 1:
               var0.Xqedii_afvuuz5 = var2 - (var12 - var4);
               var0.Xqedii_2voqgr2 = var2 - (var8 - var4);
               var0.Xqedii_toc5rf5 = var4 + (var6 - var2);
               var0.Xqedii_6aej5v1 = var4 + (var10 - var2);
               break;
            case 2:
               var0.Xqedii_afvuuz5 = Double.longBitsToDouble(4611686018427387904L) * var2 - var10;
               var0.Xqedii_2voqgr2 = Double.longBitsToDouble(4611686018427387904L) * var2 - var6;
               var0.Xqedii_toc5rf5 = Double.longBitsToDouble(4611686018427387904L) * var4 - var12;
               var0.Xqedii_6aej5v1 = Double.longBitsToDouble(4611686018427387904L) * var4 - var8;
               break;
            case 3:
               var0.Xqedii_afvuuz5 = var2 + (var8 - var4);
               var0.Xqedii_2voqgr2 = var2 + (var12 - var4);
               var0.Xqedii_toc5rf5 = var4 - (var10 - var2);
               var0.Xqedii_6aej5v1 = var4 - (var6 - var2);
         }

         if (var0.Xqedii_15q7uiy == 1) {
            var0.Xqedii_bpg2gyb = var0.Xqedii_bpg2gyb + var1 & 3;
         }
      }
   }

   public void Xqedii_9lvb14k(int var1, BossbarHudSnapshot var2, Xqedii_3tb6tlp var3, int var4, double var5, List<Xqedii_1il8kkh> var7) {
      {
         int var8 = var2.anchor;
         switch (var3.Xqedii_15q7uiy) {
            case 0:
               this.Xqedii_1o5g7p9(var1, var2, var3, var8, var5, var7);
               break;
            case 1:
               if (!this.Xqedii_3lc09uv(var2.clip, var5, var3.Xqedii_afvuuz5, var3.Xqedii_toc5rf5, var3.Xqedii_2voqgr2, var3.Xqedii_6aej5v1)) {
                  return;
               }

               int var15 = (int)Math.round(var3.Xqedii_afvuuz5);
               int var17 = (int)Math.round(var3.Xqedii_toc5rf5);
               int var19 = (int)Math.round(var3.Xqedii_2voqgr2) - var15;
               int var21 = (int)Math.round(var3.Xqedii_6aej5v1) - var17;
               if (var19 <= 0 || var21 <= 0 || !Xqedii_30g0ulj.Xqedii_2mxnz7n(var15) || !Xqedii_30g0ulj.Xqedii_140bbt1(var17)) {
                  return;
               }

               var7.add(
                  Xqedii_1il8kkh.Xqedii_3o2rcrl(
                     var1,
                     var2.layer,
                     var4,
                     var15,
                     var17,
                     Math.min(256, var19),
                     Math.min(256, var21),
                     var3.Xqedii_2q1klot,
                     var2.opacity,
                     var3.Xqedii_bpg2gyb,
                     var3.Xqedii_1z62bwh,
                     var8
                  )
               );
               break;
            case 2:
               if (!this.Xqedii_3lc09uv(var2.clip, var5, var3.Xqedii_afvuuz5, var3.Xqedii_toc5rf5, var3.Xqedii_2voqgr2, var3.Xqedii_6aej5v1)) {
                  return;
               }

               int var14 = (int)Math.round(var3.Xqedii_afvuuz5);
               int var16 = (int)Math.round(var3.Xqedii_toc5rf5);
               int var18 = (int)Math.round(var3.Xqedii_2voqgr2) - var14;
               int var20 = (int)Math.round(var3.Xqedii_6aej5v1) - var16;
               if (var18 <= 0 || var20 <= 0 || !Xqedii_30g0ulj.Xqedii_2mxnz7n(var14) || !Xqedii_30g0ulj.Xqedii_140bbt1(var16)) {
                  return;
               }

               BossbarAssets.Sprite var13 = var3.Xqedii_2kjvjpk;
               var7.add(
                  Xqedii_1il8kkh.Xqedii_2669ep6(
                     var1,
                     var2.layer,
                     var4,
                     var14,
                     var16,
                     Math.min(1024, var18),
                     Math.min(1024, var20),
                     var2.opacity,
                     var13.codepoint,
                     Xqedii_30g0ulj.Xqedii_ss1kifv(var13.source),
                     var13.advance,
                     var8,
                     var3.Xqedii_3q4hzk8,
                     var3.Xqedii_3olagji,
                     var3.Xqedii_h7shh3s,
                     var3.Xqedii_1xwisjg
                  )
               );
               break;
            case 3:
            case 4:
               if (!this.Xqedii_3lc09uv(var2.clip, var5, var3.Xqedii_afvuuz5, var3.Xqedii_toc5rf5, var3.Xqedii_2voqgr2, var3.Xqedii_6aej5v1)) {
                  return;
               }

               int var9 = (int)Math.round(var3.Xqedii_31odcf6);
               int var10 = (int)Math.round(var3.Xqedii_2ljrj1c);
               if (var9 < -896
                  || var9 > 2047
                  || var10 < -512
                  || var10 > 1528) {
                  return;
               }

               int var11 = var4 == -1
                  ? -1
                  : 1000;
               int var12 = var3.Xqedii_15q7uiy == 3
                  ? 3
                  : 4;
               var7.add(
                  Xqedii_1il8kkh.Xqedii_1luk51d(
                     var1,
                     var2.layer,
                     var11,
                     var12,
                     var9,
                     var10,
                     var3.Xqedii_2q1klot,
                     var2.opacity,
                     var3.Xqedii_1tn3rkq,
                     var3.Xqedii_1kuf0l5,
                     var3.Xqedii_2jbr678,
                     var3.Xqedii_qxqxmxp,
                     var8,
                     var3.Xqedii_afvuuz5,
                     var3.Xqedii_toc5rf5,
                     var3.Xqedii_2voqgr2,
                     var3.Xqedii_6aej5v1
                  )
               );
         }
      }
   }

   public void Xqedii_1o5g7p9(int var1, BossbarHudSnapshot var2, Xqedii_3tb6tlp var3, int var4, double var5, List<Xqedii_1il8kkh> var7) {
      {
         double[] var8 = this.Xqedii_3dg24no(var2.clip, var5, var3.Xqedii_afvuuz5, var3.Xqedii_toc5rf5, var3.Xqedii_2voqgr2, var3.Xqedii_6aej5v1);
         if (var8 != null) {
            int var9 = Xqedii_30g0ulj.Xqedii_3s169nw(var4);
            int var10 = Math.max(Xqedii_30g0ulj.Xqedii_kq5v7n6(var4), (int)Math.round(var8[0]));
            int var11 = Math.max(-512, (int)Math.round(var8[1]));
            int var12 = Math.min(
               var9 + 2048, (int)Math.round(var8[2])
            );
            int var13 = Math.min(2559, (int)Math.round(var8[3]));
            if (var12 > var10 && var13 > var11) {
               for (int var14 = var11; var14 < var13 && var14 <= 1535; var14 += 1024) {
                  int var15 = Math.min(1024, var13 - var14);

                  for (int var16 = var10; var16 < var12 && var16 <= var9; var16 += 2048) {
                     int var17 = Math.min(2048, var12 - var16);
                     var7.add(Xqedii_1il8kkh.Xqedii_y3daijy(var1, var2.layer, var16, var14, var17, var15, var3.Xqedii_2q1klot, var2.opacity, var4));
                  }
               }
            }
         }
      }
   }

   public boolean Xqedii_3lc09uv(int var1, double var2, double var4, double var6, double var8, double var10) {
      {
         return ((this.Xqedii_3dg24no(var1, var2, var4, var6, var8, var10) != null
            ? 1
            : 0) != 0);
      }
   }

   public double[] Xqedii_3dg24no(int var1, double var2, double var4, double var6, double var8, double var10) {
      {
         double var12 = var4;
         double var14 = var6;
         double var16 = var8;
         double var18 = var10;
         if (var1 > 0) {
            int var20 = var1 - 1 & 4095;
            int var21 = var1 - 1 >> 12
               & 1;
            int var22 = var20 >> 11 & 1;
            double var23 = var20 & 2047;
            if (var21 == 0) {
               double var25 = var23 + var2;
               if (var22 == 0) {
                  var14 = Math.max(var6, var25);
               } else {
                  var18 = Math.min(var10, var25);
               }
            } else if (var22 == 0) {
               var12 = Math.max(var4, var23);
            } else {
               var16 = Math.min(var8, var23);
            }
         }

         if (!(var16 - var12 <= Double.longBitsToDouble(0L))
            && !(var18 - var14 <= Double.longBitsToDouble(0L))) {
            double[] var10000 = new double[4];
            var10000[0] = var12;
            var10000[1] = var14;
            var10000[2] = var16;
            var10000[3] = var18;
            return var10000;
         } else {
            return null;
         }
      }
   }

   public double Xqedii_emb7cp4(int var1) {
      {
         double var2 = Math.floor(var1 / Double.longBitsToDouble(4616189618054758400L))
            * Double.longBitsToDouble(4616189618054758400L);
         double var4 = Double.longBitsToDouble(4650248090236747776L);
         return Math.min(var4, Math.max(Double.longBitsToDouble(4616189618054758400L), var2));
      }
   }

   public double Xqedii_3vlyygu(List<Xqedii_2q1o8r1> var1) {
      {
         double var2 = Double.longBitsToDouble(0L);

         for (Xqedii_2q1o8r1 var5 : var1) {
            var2 += var5.Xqedii_1q7ydj6;
         }

         return var2;
      }
   }

   public List<List<Xqedii_2q1o8r1>> Xqedii_1ex2kko(List<Xqedii_2q1o8r1> var1, int var2) {
      {
         ArrayList var3 = new ArrayList();
         ArrayList var4 = new ArrayList();
         double var5 = Double.longBitsToDouble(0L);
         int var7 = -1;
         double var8 = Double.longBitsToDouble(0L);

         for (Xqedii_2q1o8r1 var11 : var1) {
            if (var11.Xqedii_2zyef1q == 10) {
               var3.add(var4);
               var4 = new ArrayList();
               var5 = Double.longBitsToDouble(0L);
               var7 = -1;
            } else {
               if (var5 + var11.Xqedii_1q7ydj6 > var2 && !var4.isEmpty()) {
                  if (var7 >= 0 && var7 < var4.size()) {
                     ArrayList var12 = new ArrayList(var4.subList(var7 + 1, var4.size()));
                     ArrayList var13 = new ArrayList(var4.subList(0, var7));
                     var3.add(var13);
                     var4 = var12;
                     var5 -= var8;
                  } else {
                     var3.add(var4);
                     var4 = new ArrayList();
                     var5 = Double.longBitsToDouble(0L);
                  }

                  var7 = -1;
               }

               if (var11.Xqedii_2zyef1q == 32) {
                  var7 = var4.size();
                  var8 = var5 + var11.Xqedii_1q7ydj6;
               }

               var4.add(var11);
               var5 += var11.Xqedii_1q7ydj6;
            }
         }

         var3.add(var4);
         if (var3.size() > 1) {
            ArrayList var14 = new ArrayList();

            for (int var15 = 0; var15 < var3.size(); var15++) {
               if (var15 != var3.size() - 1 || !((List)var3.get(var15)).isEmpty()) {
                  var14.add((List)var3.get(var15));
               }
            }

            if (!var14.isEmpty()) {
               return var14;
            }
         }

         return var3;
      }
   }

   public List<Xqedii_2q1o8r1> Xqedii_16qvyx5(String var1) {
      {
         List var2 = this.Xqedii_wlo6exy.get(var1);
         if (var2 != null) {
            return var2;
         } else {
            List var3 = Collections.unmodifiableList(this.Xqedii_1cb2n7m(var1));
            if (this.Xqedii_wlo6exy.size() >= 8192) {
               this.Xqedii_wlo6exy.clear();
            }

            this.Xqedii_wlo6exy.put(var1, var3);
            return var3;
         }
      }
   }

   public List<Xqedii_2q1o8r1> Xqedii_1cb2n7m(String var1) {
      {
         ArrayList var2 = new ArrayList();

         Object var3;
         try {
            var3 = Xqedii_7wvmt1t.deserialize(var1);
         } catch (Exception var5) {
            var3 = Component.text(var1);
         }

         this.Xqedii_2x2pei6((Component)var3, Style.empty(), var2);
         return var2;
      }
   }

   public void Xqedii_2x2pei6(Component var1, Style var2, List<Xqedii_2q1o8r1> var3) {
      {
         Style var4 = var1.style().merge(var2, Strategy.IF_ABSENT_ON_TARGET);
         if (var1 instanceof TextComponent var5) {
            this.Xqedii_27baddb(var5.content(), var4, var3);
         } else {
            String var6 = PlainTextComponentSerializer.plainText().serialize(var1.children(List.of()));
            this.Xqedii_27baddb(var6, var4, var3);
         }

         for (Component var8 : var1.children()) {
            this.Xqedii_2x2pei6(var8, var4, var3);
         }
      }
   }

   public void Xqedii_27baddb(String var1, Style var2, List<Xqedii_2q1o8r1> var3) {
      if (var1 != null && !var1.isEmpty()) {
         TextColor var4 = var2.color();
         int var5 = var4 == null ? NamedTextColor.WHITE.value() : var4.value();
         int var6 = var2.decoration(TextDecoration.BOLD) == State.TRUE
            ? 1
            : 0;
         int var7 = var2.decoration(TextDecoration.ITALIC) == State.TRUE
            ? 1
            : 0;
         Key var8 = var2.font();
         String var9 = var8 == null ? null : var8.asString();
         int var10 = 0;

         while (var10 < var1.length()) {
            int var11 = var1.codePointAt(var10);
            var10 += Character.charCount(var11);
            Xqedii_2q1o8r1 var12 = new Xqedii_2q1o8r1();
            var12.Xqedii_2zyef1q = var11;
            var12.Xqedii_xeufmmf = var5;
            var12.Xqedii_27itpdq = (var6 != 0);
            var12.Xqedii_3kr6lzk = (var7 != 0);
            this.Xqedii_g4hwjd7(var12, var9);
            var3.add(var12);
         }
      }
   }

   public void Xqedii_g4hwjd7(Xqedii_2q1o8r1 var1, String var2) {
      {
         int var3 = var1.Xqedii_2zyef1q;
         double var4 = var1.Xqedii_27itpdq
            ? Double.longBitsToDouble(4607182418800017408L)
            : Double.longBitsToDouble(0L);
         if (var3 == 10) {
            var1.Xqedii_2f1zu9z = 0;
            var1.Xqedii_1q7ydj6 = Double.longBitsToDouble(0L);
         } else {
            BossbarAssets.Sprite var6 = this.Xqedii_2oupt0w.sprite(var2, var3);
            if (var6 != null) {
               var1.Xqedii_2f1zu9z = 1;
               var1.Xqedii_2bcofvn = var6;
               var1.Xqedii_1q7ydj6 = var6.layoutAdvance + var4;
            } else {
               boolean var7 = BossbarAssets.isDefaultFont(var2);
               BossbarAssets.TextFace var8 = var7 ? null : this.Xqedii_2oupt0w.face(var2);
               if (var8 != null && var8.ttf.hasCodepoint(var3)) {
                  var1.Xqedii_2f1zu9z = var3 == 32
                     ? 4
                     : 2;
                  var1.Xqedii_3tj78o2 = var8;
                  var1.Xqedii_1q7ydj6 = var8.advanceFont(var3) + var4;
               } else {
                  if (var7 || var8 == null) {
                     Double var9 = this.Xqedii_2oupt0w.vanillaSpace(var3);
                     if (var9 != null) {
                        var1.Xqedii_2f1zu9z = 4;
                        var1.Xqedii_1q7ydj6 = var9 + var4;
                        return;
                     }

                     BossbarAssets.VanillaGlyph var10 = this.Xqedii_2oupt0w.vanillaGlyph(var3);
                     if (var10 != null) {
                        var1.Xqedii_2f1zu9z = 3;
                        var1.Xqedii_27at3tw = var10;
                        var1.Xqedii_1q7ydj6 = var10.layoutAdvance() + var4;
                        return;
                     }
                  }

                  BossbarAssets.TextFace var11 = this.Xqedii_2oupt0w.fallbackFace();
                  if (var11 != null && var11.ttf.hasCodepoint(var3)) {
                     var1.Xqedii_2f1zu9z = var3 == 32
                        ? 4
                        : 2;
                     var1.Xqedii_3tj78o2 = var11;
                     var1.Xqedii_1q7ydj6 = var11.advanceFont(var3) + var4;
                  } else {
                     var1.Xqedii_2f1zu9z = 0;
                     var1.Xqedii_1q7ydj6 = Double.longBitsToDouble(0L);
                  }
               }
            }
         }
      }
   }

   static {
      {
         Xqedii_7wvmt1t = MiniMessage.miniMessage();
      }
   }
}
