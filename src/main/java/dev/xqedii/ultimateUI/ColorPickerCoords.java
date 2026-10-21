package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;

public record ColorPickerCoords(double gradAbsX, double gradAbsY, double hueAbsY) {

   @Override

   public double gradAbsY() {
      {
         return this.gradAbsY;
      }
   }

   public double hueAbsY() {
      {
         return this.hueAbsY;
      }
   }
}
