package dev.xqedii.ultimateUI.service.hud;

import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.util.Vector;
import org.joml.Vector3f;

public class HudPositionCalculator {
   public static final double YAML_TO_HUD_SIZE_FACTOR = 1.25;
   private static final double UNIT_X = 1.0;
   private static final double UNIT_Y = 1.0;
   private static final double SCREEN_X = 1920.0;
   private static final double SCREEN_Y = 1080.0;
   private static final double BOX_LEFT_COMP_DIVISOR = 160.0;
   private static final double BOX_TOP_COMP_DIVISOR = 36.0;
   private static final double BOX_TOP_DRIFT_START_HEIGHT = 200.0;
   private static final double BOX_TOP_DRIFT_PER_HEIGHT = 0.0032;
   private static final double BOX_LEFT_STEP_START_WIDTH = 2876.0;
   private static final double BOX_LEFT_STEP_PERIOD = 1920.0;
   private static final double DISPLAY_THICKNESS = 1.0;
   private static final int ALIGNED_LEFT_OFFSET = 10000;
   private static final int ALIGNED_RIGHT_OFFSET = 30000;

   public HudPositionCalculator.Placement calculateBoxPlacement(double var1, double var3, double var5, double var7, double var9) {
      return this.calculateBoxPlacement(var1, var3, var5, var7, var9, true);
   }

   public HudPositionCalculator.Placement calculateBoxPlacement(double var1, double var3, double var5, double var7, double var9, boolean var11) {
      double var12 = Math.max(1.0, var7);
      double var14 = Math.max(1.0, var9);
      double var16 = var12 * 1.25;
      double var18 = var14 * 1.25;
      double var20 = var16 / 160.0;
      double var22 = var18 / 36.0;
      double var24 = var11 ? Math.max(0.0, (var14 - 200.0) * 0.0032) : 0.0;
      double var26 = this.computeLeftStepCorrection(var12);
      double var28 = var1 + var12 / 2.0 - var20 + var26;
      double var30 = var3 + var14 + var22 - var24;
      Vector var32 = new Vector(var28, var30, var5);
      Vector var33 = new Vector(var16 / 2.0, var18 / 2.0, 1.0);
      return new HudPositionCalculator.Placement(var32, var33);
   }

   private double computeLeftStepCorrection(double var1) {
      if (var1 <= 2876.0) {
         return 0.0;
      } else {
         double var3 = Math.floor((var1 - 2876.0) / 1920.0) + 1.0;
         return Math.max(0.0, var3);
      }
   }

   public Vector3f toDisplayTranslation(Vector var1, Vector var2, Entity var3, int var4) {
      float var5 = 0.0F;
      if (var3 instanceof ItemDisplay var6) {
         if (var6.getItemStack() != null && var6.getItemStack().getType().isBlock()) {
            var5 = (float)(var2.getZ() / 2.0);
         } else {
            var5 = (float)(var2.getZ() / 32.0);
         }
      }

      double var11;
      if (var4 == 10000) {
         var11 = 0.0;
      } else if (var4 == 30000) {
         var11 = 0.0;
      } else {
         var11 = 0.0;
      }

      float var8 = (float)((960.0 - var1.getX() - var11) * 1.0);
      float var9 = (float)(-15000.178 + (540.0 - var1.getY()) * 1.0 - (double)var4);
      float var10 = (float)(1.0 * (double)var5 - var1.getZ() * 100.0);
      return new Vector3f(var8, var9, var10);
   }

   public static final class Placement {
      private final Vector location;
      private final Vector scale;

      public Placement(Vector var1, Vector var2) {
         this.location = var1;
         this.scale = var2;
      }

      public Vector location() {
         return this.location;
      }

      public Vector scale() {
         return this.scale;
      }
   }
}
