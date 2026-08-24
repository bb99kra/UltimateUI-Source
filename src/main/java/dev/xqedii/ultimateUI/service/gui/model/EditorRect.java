package dev.xqedii.ultimateUI.service.gui.model;

public final class EditorRect {
   public final double x;
   public final double y;
   public final double width;
   public final double height;

   public EditorRect(double var1, double var3, double var5, double var7) {
      this.x = var1;
      this.y = var3;
      this.width = Math.max(0.0, var5);
      this.height = Math.max(0.0, var7);
   }

   public double maxX() {
      return this.x + this.width;
   }

   public double maxY() {
      return this.y + this.height;
   }
}
