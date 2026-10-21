package dev.xqedii.ultimateUI;

import java.util.Map;

public final class Xqedii_v1f4ltd extends Xqedii_ev3jp8w {
   public final Xqedii_ev3jp8w Xqedii_37tblme;
   public final String Xqedii_2su690q;

   public Xqedii_v1f4ltd(Xqedii_ev3jp8w var1, String var2, int var3) {
      super(var3);
      this.Xqedii_37tblme = var1;
      this.Xqedii_2su690q = var2;
   }

   @Override
   public Object Xqedii_28g36j3(Xqedii_y5pr2ec var1) {
      {
         return Xqedii_2d07ocz.get(var1, this.Xqedii_37tblme.Xqedii_28g36j3(var1), this.Xqedii_2su690q, this.Xqedii_3dzdvck);
      }
   }

   @Override
   public boolean Xqedii_25ddebp() {
      {
         return true;
      }
   }

   @Override
   public void Xqedii_2uxio2i(Xqedii_y5pr2ec var1, Object var2) {
      {
         Xqedii_2d07ocz.Xqedii_2a9tfw6(var1, this.Xqedii_37tblme.Xqedii_28g36j3(var1), this.Xqedii_2su690q, var2, this.Xqedii_3dzdvck);
      }
   }

   @Override
   public void Xqedii_2dz6h65(Xqedii_y5pr2ec var1) {
      {
         Object var2 = this.Xqedii_37tblme.Xqedii_28g36j3(var1);
         if (var2 instanceof Map var3) {
            var3.remove(this.Xqedii_2su690q);
         } else {
            Xqedii_2d07ocz.Xqedii_2a9tfw6(var1, var2, this.Xqedii_2su690q, null, this.Xqedii_3dzdvck);
         }
      }
   }
}
