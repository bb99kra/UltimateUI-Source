package dev.xqedii.ultimateUI;

import java.util.ArrayList;
import java.util.List;

public final class Xqedii_13ezdyt extends Xqedii_3nlck21 {
   public final boolean Xqedii_5ak6wpd;
   public final Xqedii_ev3jp8w Xqedii_2qxtnhn;
   public final Xqedii_ev3jp8w Xqedii_2xcaz6m;

   public Xqedii_13ezdyt(boolean var1, Xqedii_ev3jp8w var2, Xqedii_ev3jp8w var3, int var4) {
      super(var4);
      this.Xqedii_5ak6wpd = var1;
      this.Xqedii_2qxtnhn = var2;
      this.Xqedii_2xcaz6m = var3;
   }

   @Override
   public void Xqedii_3egc8pa(Xqedii_y5pr2ec var1) {
      {
         Object var2 = this.Xqedii_2qxtnhn.Xqedii_28g36j3(var1);
         Object var3 = this.Xqedii_2xcaz6m.Xqedii_28g36j3(var1);
         if (!(var3 instanceof List var4)) {
            if (var3 == null && this.Xqedii_5ak6wpd && !(var2 instanceof Number)) {
               ArrayList var8 = new ArrayList();
               var8.add(var2);
               this.Xqedii_2xcaz6m.Xqedii_2uxio2i(var1, var8);
            } else {
               Object var7 = this.Xqedii_5ak6wpd
                  ? Xqedii_z4wg071.Xqedii_hbc7zbp(
                     var3 == null ? Double.longBitsToDouble(0L) : var3, var2, this.Xqedii_2lldvpl
                  )
                  : Xqedii_z4wg071.Xqedii_3c9epia(var3, this.Xqedii_2lldvpl) - Xqedii_z4wg071.Xqedii_3c9epia(var2, this.Xqedii_2lldvpl);
               this.Xqedii_2xcaz6m.Xqedii_2uxio2i(var1, var7);
            }
         } else {
            List var5 = var4;
            if (this.Xqedii_5ak6wpd) {
               var4.add(var2);
            } else {
               for (int var6 = 0; var6 < var5.size(); var6++) {
                  if (Xqedii_z4wg071.Xqedii_zxgkysn(var5.get(var6), var2)) {
                     var5.remove(var6);
                     break;
                  }
               }
            }
         }
      }
   }
}
