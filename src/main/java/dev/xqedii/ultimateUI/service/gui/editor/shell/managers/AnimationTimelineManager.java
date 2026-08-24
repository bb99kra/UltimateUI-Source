package dev.xqedii.ultimateUI.service.gui.editor.shell.managers;

import dev.xqedii.ultimateUI.service.gui.editor.shell.utils.AnimationMath;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.function.Function;

public final class AnimationTimelineManager {
   public String resolveSegmentInterpolationMode(Map<Integer, String> var1, Integer var2, Integer var3, Function<String, String> var4, String var5) {
      String var6 = var5 != null && !var5.isBlank() ? var5 : "linear";
      if (var1 != null && !var1.isEmpty() && var3 != null) {
         String var7 = (String)var1.get(var3);
         if (var4 == null) {
            return var7 != null && !var7.isBlank() ? var7 : var6;
         } else {
            String var8 = (String)var4.apply(var7);
            return var8 != null && !var8.isBlank() ? var8 : var6;
         }
      } else {
         return var6;
      }
   }

   public double interpolateChannel(
      TreeMap<Integer, Double> var1, double var2, double var4, Map<Integer, String> var6, Function<String, String> var7, String var8, int var9
   ) {
      if (var1 != null && !var1.isEmpty()) {
         int var10 = Math.max(0, (int)Math.floor(var2));
         int var11 = Math.min(Math.max(0, var9), Math.max(0, (int)Math.ceil(var2)));
         Entry var12 = var1.floorEntry(var10);
         Entry var13 = var1.ceilingEntry(var11);
         if (var12 == null && var13 == null) {
            return var4;
         } else if (var12 == null && var13 != null) {
            int var21 = Math.max(0, (Integer)var13.getKey());
            if (var21 <= 0) {
               return (Double)var13.getValue();
            } else {
               double var15 = var2 / (double)var21;
               String var17 = this.resolveSegmentInterpolationMode(var6, null, var21, var7, var8);
               double var22 = AnimationMath.applyInterpolation(var17, var15);
               return var4 + ((Double)var13.getValue() - var4) * var22;
            }
         } else if (var12 != null && var13 == null) {
            return (Double)var12.getValue();
         } else if (var12 != null && var13 != null && (Integer)var12.getKey() == (Integer)var13.getKey()) {
            return (Double)var12.getValue();
         } else if (var12 != null && var13 != null) {
            double var14 = Math.max(1.0, (double)((Integer)var13.getKey() - (Integer)var12.getKey()));
            double var16 = (var2 - (double)((Integer)var12.getKey()).intValue()) / var14;
            String var18 = this.resolveSegmentInterpolationMode(var6, (Integer)var12.getKey(), (Integer)var13.getKey(), var7, var8);
            double var19 = AnimationMath.applyInterpolation(var18, var16);
            return (Double)var12.getValue() + ((Double)var13.getValue() - (Double)var12.getValue()) * var19;
         } else {
            return var4;
         }
      } else {
         return var4;
      }
   }

   public double[] interpolateVector2(
      TreeMap<Integer, double[]> var1, double var2, double var4, double var6, Map<Integer, String> var8, Function<String, String> var9, String var10, int var11
   ) {
      if (var1 != null && !var1.isEmpty()) {
         TreeMap var12 = new TreeMap();
         TreeMap var13 = new TreeMap();

         for (Entry var15 : var1.entrySet()) {
            if (var15 != null && var15.getValue() != null && ((double[])var15.getValue()).length >= 2) {
               var12.put((Integer)var15.getKey(), ((double[])var15.getValue())[0]);
               var13.put((Integer)var15.getKey(), ((double[])var15.getValue())[1]);
            }
         }

         return new double[]{
            this.interpolateChannel(var12, var2, var4, var8, var9, var10, var11), this.interpolateChannel(var13, var2, var6, var8, var9, var10, var11)
         };
      } else {
         return new double[]{var4, var6};
      }
   }
}
