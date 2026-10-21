package dev.xqedii.ultimateUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.runtime.ObjectMethods;

public record ImageCropTask(BufferedImage source, int sourceX, int sourceY, int copyWidth, int copyHeight, File destination) {

   @Override

   public int sourceX() {
      {
         return this.sourceX;
      }
   }

   public int sourceY() {
      {
         return this.sourceY;
      }
   }

   public int copyWidth() {
      {
         return this.copyWidth;
      }
   }

   public int copyHeight() {
      {
         return this.copyHeight;
      }
   }

   public File destination() {
      {
         return this.destination;
      }
   }
}
