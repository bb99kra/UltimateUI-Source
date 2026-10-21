package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.bossbar.BossbarEntityRect;
import dev.xqedii.ultimateUI.service.bossbar.BossbarHudSnapshot;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class Xqedii_500t03m {
   public static final int Xqedii_25ndeq5 = 48;

   public static Set<String> Xqedii_l2kdved(List<BossbarHudSnapshot> var0, List<Xqedii_1il8kkh> var1, Map<Integer, double[]> var2, List<BossbarEntityRect> var3) {
      if (var0 != null && !var0.isEmpty()) {
         int var4 = var0.size();
         double[][] var5 = new double[var4][];
         if (var1 != null) {
            for (Xqedii_1il8kkh var7 : var1) {
               if (var7.Xqedii_1177et8 >= 0 && var7.Xqedii_1177et8 < var4) {
                  double[] var8 = var5[var7.Xqedii_1177et8];
                  if (var8 == null) {
                     int var10001 = var7.Xqedii_1177et8;
                     double[] var10002 = new double[4];
                     var10002[0] = var7.Xqedii_edx81yn;
                     var10002[1] = var7.Xqedii_2wzxbdl;
                     var10002[2] = var7.Xqedii_25pg3mz;
                     var10002[3] = var7.Xqedii_yoez7kl;
                     var5[var10001] = var10002;
                  } else {
                     var8[0] = Math.min(
                        var8[0], var7.Xqedii_edx81yn
                     );
                     var8[1] = Math.min(
                        var8[1], var7.Xqedii_2wzxbdl
                     );
                     var8[2] = Math.max(
                        var8[2], var7.Xqedii_25pg3mz
                     );
                     var8[3] = Math.max(
                        var8[3], var7.Xqedii_yoez7kl
                     );
                  }
               }
            }
         }

         boolean[] var13 = new boolean[var4];
         ArrayDeque var14 = new ArrayDeque();
         if (var3 != null) {
            var14.addAll(var3);
         }

         for (int var15 = 0; var15 < var4; var15++) {
            BossbarHudSnapshot var9 = (BossbarHudSnapshot)var0.get(var15);
            double[] var10 = var2 == null ? null : (double[])var2.get(var15);
            int var11 = var2 != null && var2.containsKey(var15)
               ? 1
               : 0;
            if (var9.entityOnly || var11 != 0) {
               var13[var15] = true;
               double[] var12 = var5[var15] != null ? var5[var15] : var10;
               if (var12 != null) {
                  var14.add(Xqedii_3av9z0j(var12, var9.layer));
               }
            }
         }

         if (var14.isEmpty()) {
            return Collections.emptySet();
         } else {
            while (!var14.isEmpty()) {
               BossbarEntityRect var16 = (BossbarEntityRect)var14.poll();

               for (int var18 = 0; var18 < var4; var18++) {
                  double[] var20 = var5[var18];
                  if (!var13[var18] && var20 != null) {
                     BossbarHudSnapshot var21 = (BossbarHudSnapshot)var0.get(var18);
                     if (!(var21.layer > var16.layer)
                        && var16.Xqedii_2m8wxtt(
                           var20[0],
                           var20[1],
                           var20[2],
                           var20[3]
                        )) {
                        var13[var18] = true;
                        var14.add(
                           new BossbarEntityRect(
                              var20[0],
                              var20[1],
                              var20[2],
                              var20[3],
                              var21.layer
                           )
                        );
                     }
                  }
               }
            }

            HashSet var17 = new HashSet();

            for (int var19 = 0; var19 < var4; var19++) {
               if (var13[var19]) {
                  var17.add(((BossbarHudSnapshot)var0.get(var19)).id);
               }
            }

            return var17;
         }
      } else {
         return Collections.emptySet();
      }
   }

   public static Xqedii_2rmjlt7 Xqedii_2jtypg6(
      List<BossbarHudSnapshot> var0, List<Xqedii_1il8kkh> var1, Map<Integer, double[]> var2, List<BossbarEntityRect> var3, Set<String> var4
   ) {
      if (var0 != null && !var0.isEmpty() && var1 != null && var4 != null && !var4.isEmpty()) {
         int var5 = var0.size();
         HashSet var6 = new HashSet();

         for (int var7 = 0; var7 < var5; var7++) {
            BossbarHudSnapshot var8 = (BossbarHudSnapshot)var0.get(var7);
            if (var4.contains(var8.id) && !var8.entityOnly && (var2 == null || !var2.containsKey(var7))) {
               var6.add(var7);
            }
         }

         if (var6.isEmpty()) {
            return new Xqedii_2rmjlt7(var4, var1);
         } else {
            for (Xqedii_1il8kkh var19 : var1) {
               if (var19.Xqedii_2d73yig != 0) {
                  var6.remove(var19.Xqedii_1177et8);
               }
            }

            if (var6.isEmpty()) {
               return new Xqedii_2rmjlt7(var4, var1);
            } else {
               ArrayList var18 = new ArrayList();
               if (var3 != null) {
                  var18.addAll(var3);
               }

               double[][] var20 = new double[var5][];

               for (Xqedii_1il8kkh var10 : var1) {
                  if (var10.Xqedii_1177et8 >= 0 && var10.Xqedii_1177et8 < var5) {
                     double[] var11 = var20[var10.Xqedii_1177et8];
                     if (var11 == null) {
                        int var10001 = var10.Xqedii_1177et8;
                        double[] var10002 = new double[4];
                        var10002[0] = var10.Xqedii_edx81yn;
                        var10002[1] = var10.Xqedii_2wzxbdl;
                        var10002[2] = var10.Xqedii_25pg3mz;
                        var10002[3] = var10.Xqedii_yoez7kl;
                        var20[var10001] = var10002;
                     } else {
                        var11[0] = Math.min(
                           var11[0], var10.Xqedii_edx81yn
                        );
                        var11[1] = Math.min(
                           var11[1], var10.Xqedii_2wzxbdl
                        );
                        var11[2] = Math.max(
                           var11[2], var10.Xqedii_25pg3mz
                        );
                        var11[3] = Math.max(
                           var11[3], var10.Xqedii_yoez7kl
                        );
                     }
                  }
               }

               for (int var21 = 0; var21 < var5; var21++) {
                  if (!var6.contains(var21) && var4.contains(((BossbarHudSnapshot)var0.get(var21)).id)) {
                     double[] var23 = var20[var21];
                     if (var23 != null) {
                        var18.add(
                           new BossbarEntityRect(
                              var23[0],
                              var23[1],
                              var23[2],
                              var23[3],
                              ((BossbarHudSnapshot)var0.get(var21)).layer
                           )
                        );
                     }
                  }
               }

               if (var18.isEmpty()) {
                  return new Xqedii_2rmjlt7(var4, var1);
               } else {
                  HashSet var22 = new HashSet();
                  HashMap var24 = new HashMap();

                  for (int var12 : (Iterable<Integer>) (var6)) {

                     BossbarHudSnapshot var13 = (BossbarHudSnapshot)var0.get(var12);
                     ArrayList var14 = new ArrayList();

                     for (Xqedii_1il8kkh var16 : var1) {
                        if (var16.Xqedii_1177et8 == var12) {
                           var14.add(var16);
                        }
                     }

                     List var32 = Xqedii_13u07rr(var14, var13, var18);
                     if (var32 != null) {
                        var22.add(var12);
                        var24.put(var12, var32);
                     }
                  }

                  if (var22.isEmpty()) {
                     return new Xqedii_2rmjlt7(var4, var1);
                  } else {
                     HashSet var26 = new HashSet(var4);

                     for (int var29 : (Iterable<Integer>) (var22)) {

                        var26.remove(((BossbarHudSnapshot)var0.get(var29)).id);
                     }

                     ArrayList var28 = new ArrayList(var1.size());
                     HashSet var30 = new HashSet();

                     for (Xqedii_1il8kkh var33 : var1) {
                        if (!var22.contains(var33.Xqedii_1177et8)) {
                           var28.add(var33);
                        } else if (var30.add(var33.Xqedii_1177et8)) {
                           var28.addAll((Collection)var24.get(var33.Xqedii_1177et8));
                        }
                     }

                     return new Xqedii_2rmjlt7(var26, var28);
                  }
               }
            }
         }
      } else {
         return new Xqedii_2rmjlt7(var4 == null ? Collections.emptySet() : var4, var1);
      }
   }

   public static List<Xqedii_1il8kkh> Xqedii_13u07rr(List<Xqedii_1il8kkh> var0, BossbarHudSnapshot var1, List<BossbarEntityRect> var2) {
      {
         ArrayList var3 = new ArrayList();

         for (Xqedii_1il8kkh var5 : var0) {
            ArrayList var6 = new ArrayList();
            int[] var10001 = new int[4];
            var10001[0] = var5.Xqedii_1t89g9g;
            var10001[1] = var5.Xqedii_qw73drj;
            var10001[2] = var5.Xqedii_1t89g9g + var5.Xqedii_3kc0ndq;
            var10001[3] = var5.Xqedii_qw73drj + var5.Xqedii_19an9ij;
            var6.add(var10001);

            for (BossbarEntityRect var8 : var2) {
               if (!(var8.layer < var1.layer)) {
                  ArrayList var9 = new ArrayList();

                  for (int[] var11 : (Iterable<int[]>) (var6)) {

                     Xqedii_1afw8s1(var11, var8, var9);
                  }

                  var6 = var9;
                  if (var9.size() > 48) {
                     return null;
                  }
               }
            }

            for (int[] var13 : (Iterable<int[]>) (var6)) {

               int var14 = var13[2] - var13[0];
               int var15 = var13[3] - var13[1];
               if (var14 > 0 && var15 > 0) {
                  var3.add(
                     Xqedii_1il8kkh.Xqedii_1jrrn9i(
                        var5.Xqedii_1177et8,
                        var5.Xqedii_25muecf,
                        var5.Xqedii_f5uv5g9,
                        var13[0],
                        var13[1],
                        var14,
                        var15,
                        var5.Xqedii_m3xyrif,
                        var5.Xqedii_2sb63xq,
                        var5.Xqedii_22jt9mc
                     )
                  );
               }
            }
         }

         return var3;
      }
   }

   public static void Xqedii_1afw8s1(int[] var0, BossbarEntityRect var1, List<int[]> var2) {
      {
         int var3 = (int)Math.floor(var1.left);
         int var4 = (int)Math.floor(var1.top);
         int var5 = (int)Math.ceil(var1.right);
         int var6 = (int)Math.ceil(var1.bottom);
         if (var5 > var0[0]
            && var3 < var0[2]
            && var6 > var0[1]
            && var4 < var0[3]) {
            if (var4 > var0[1]) {
               int[] var10001 = new int[4];
               var10001[0] = var0[0];
               var10001[1] = var0[1];
               var10001[2] = var0[2];
               var10001[3] = var4;
               var2.add(var10001);
            }

            if (var6 < var0[3]) {
               int[] var9 = new int[4];
               var9[0] = var0[0];
               var9[1] = var6;
               var9[2] = var0[2];
               var9[3] = var0[3];
               var2.add(var9);
            }

            int var7 = Math.max(var0[1], var4);
            int var8 = Math.min(var0[3], var6);
            if (var8 > var7) {
               if (var3 > var0[0]) {
                  int[] var10 = new int[4];
                  var10[0] = var0[0];
                  var10[1] = var7;
                  var10[2] = var3;
                  var10[3] = var8;
                  var2.add(var10);
               }

               if (var5 < var0[2]) {
                  int[] var11 = new int[4];
                  var11[0] = var5;
                  var11[1] = var7;
                  var11[2] = var0[2];
                  var11[3] = var8;
                  var2.add(var11);
               }
            }
         } else {
            var2.add(var0);
         }
      }
   }

   public static BossbarEntityRect Xqedii_3av9z0j(double[] var0, double var1) {
      {
         double var3 = Math.hypot(
            var0[2] - var0[0],
            var0[3] - var0[1]
         );
         double var5 = (var0[0] + var0[2])
            / Double.longBitsToDouble(4611686018427387904L);
         double var7 = (var0[1] + var0[3])
            / Double.longBitsToDouble(4611686018427387904L);
         return new BossbarEntityRect(var5 - var3, var7 - var3, var5 + var3, var7 + var3, var1);
      }
   }
}
