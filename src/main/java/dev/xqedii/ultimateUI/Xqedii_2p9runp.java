package dev.xqedii.ultimateUI;

import java.util.List;
import java.util.Map;

public final class Xqedii_2p9runp extends Xqedii_ev3jp8w {
   public final Xqedii_ev3jp8w Xqedii_3pyh46j;
   public final Xqedii_ev3jp8w Xqedii_qct7qsp;

   public Xqedii_2p9runp(Xqedii_ev3jp8w var1, Xqedii_ev3jp8w var2, int var3) {
      super(var3);
      this.Xqedii_3pyh46j = var1;
      this.Xqedii_qct7qsp = var2;
   }

   @Override
   public Object Xqedii_28g36j3(Xqedii_y5pr2ec var1) {
      {
         Object var2 = this.Xqedii_3pyh46j.Xqedii_28g36j3(var1);
         Object var3 = this.Xqedii_qct7qsp.Xqedii_28g36j3(var1);
         if (var2 instanceof List var6) {
            int var7 = this.Xqedii_2fum03z(var6.size(), var3);
            return var7 >= 0 && var7 < var6.size() ? var6.get(var7) : null;
         } else if (!(var2 instanceof String var4)) {
            return Xqedii_2d07ocz.get(var1, var2, Xqedii_z4wg071.Xqedii_2mlipa7(var3), this.Xqedii_3dzdvck);
         } else {
            int var5 = this.Xqedii_2fum03z(var4.length(), var3);
            return var5 >= 0 && var5 < var4.length() ? String.valueOf(var4.charAt(var5)) : null;
         }
      }
   }

   public int Xqedii_2fum03z(int var1, Object var2) {
      {
         int var3 = Xqedii_z4wg071.Xqedii_1x518al(var2, this.Xqedii_3dzdvck);
         return var3 < 0 ? var1 + var3 : var3;
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
         Object var3 = this.Xqedii_3pyh46j.Xqedii_28g36j3(var1);
         Object var4 = this.Xqedii_qct7qsp.Xqedii_28g36j3(var1);
         if (!(var3 instanceof List var5)) {
            Xqedii_2d07ocz.Xqedii_2a9tfw6(var1, var3, Xqedii_z4wg071.Xqedii_2mlipa7(var4), var2, this.Xqedii_3dzdvck);
         } else {
            int var6 = this.Xqedii_2fum03z(var5.size(), var4);
            List var7 = var5;

            while (var7.size() <= var6) {
               var7.add(null);
            }

            if (var6 < 0) {
               throw new Xqedii_3vsk7zm(Xqedii_26ewntqdzmu7v(Xqedii_z4wg071.Xqedii_2mlipa7(var4)), this.Xqedii_3dzdvck);
            } else {
               var7.set(var6, var2);
            }
         }
      }
   }

   @Override
   public void Xqedii_2dz6h65(Xqedii_y5pr2ec var1) {
      {
         Object var2 = this.Xqedii_3pyh46j.Xqedii_28g36j3(var1);
         Object var3 = this.Xqedii_qct7qsp.Xqedii_28g36j3(var1);
         if (var2 instanceof List var6) {
            int var5 = this.Xqedii_2fum03z(var6.size(), var3);
            if (var5 >= 0 && var5 < var6.size()) {
               var6.remove(var5);
            }
         } else {
            if (var2 instanceof Map var4) {
               var4.remove(Xqedii_z4wg071.Xqedii_2mlipa7(var3));
            }
         }
      }
   }

   private static /* synthetic */ String Xqedii_26ewntqdzmu7v(String string) {
        return Xqedii_3n9nzjt.Xqedii_1ag86feebnnmm((String)"6ZXeuGJPqTbk/I2AcuSB0FDR6NBe/WBbewnv1eDVM0AMuPTMfmNqjzQezDBCXq5ujJxAGdIB") + string + Xqedii_3n9nzjt.Xqedii_1ag86feebnnmm((String)"knhOxb7aQ86ErV5eOkBQPqhs9M/hVGB2WVmKIM5siWyUc/SjxxKCegimP09X8ims4e0YvFxwZvG3kODuZVFkEArwxYM=");
    }
}
