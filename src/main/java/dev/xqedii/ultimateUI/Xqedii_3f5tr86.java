package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;

public record Xqedii_3f5tr86(String targetPath, double minX, double minY, double maxX, double maxY, double layer, int order) {

   public boolean contains(double var1, double var3) {
      {
         return ((var1 >= this.minX && var1 <= this.maxX && var3 >= this.minY && var3 <= this.maxY
            ? 1
            : 0) != 0);
      }
   }

   @Override

   public double minX() {
      {
         return this.minX;
      }
   }

   public double minY() {
      {
         return this.minY;
      }
   }

   public double maxX() {
      {
         return this.maxX;
      }
   }

   public double maxY() {
      {
         return this.maxY;
      }
   }

   public double layer() {
      {
         return this.layer;
      }
   }

   public int order() {
      {
         return this.order;
      }
   }
}
