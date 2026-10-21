package dev.xqedii.ultimateUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class Xqedii_2uv99kq extends Xqedii_3nlck21 {
   public final String Xqedii_10smn4x;
   public final Xqedii_ev3jp8w Xqedii_32hqeou;
   public final Xqedii_ev3jp8w Xqedii_u1uqwmc;
   public final Xqedii_3nlck21[] Xqedii_3sgqz32;

   public Xqedii_2uv99kq(String var1, Xqedii_ev3jp8w var2, Xqedii_ev3jp8w var3, Xqedii_3nlck21[] var4, int var5) {
      super(var5);
      this.Xqedii_10smn4x = var1;
      this.Xqedii_32hqeou = var2;
      this.Xqedii_u1uqwmc = var3;
      this.Xqedii_3sgqz32 = var4;
   }

   @Override
   public void Xqedii_3egc8pa(Xqedii_y5pr2ec var1) {
      {
         Object var2 = this.Xqedii_32hqeou.Xqedii_28g36j3(var1);
         Iterator var3;
         if (this.Xqedii_u1uqwmc != null) {
            var3 = Xqedii_rni9q9a(
               Xqedii_z4wg071.Xqedii_3c9epia(var2, this.Xqedii_2lldvpl),
               Xqedii_z4wg071.Xqedii_3c9epia(this.Xqedii_u1uqwmc.Xqedii_28g36j3(var1), this.Xqedii_2lldvpl)
            );
         } else if (var2 instanceof List var4) {
            var3 = new ArrayList(var4).iterator();
         } else if (var2 instanceof Map var5) {
            var3 = new ArrayList(var5.keySet()).iterator();
         } else if (var2 instanceof Number var6) {
            var3 = Xqedii_rni9q9a(Double.longBitsToDouble(4607182418800017408L), var6.doubleValue());
         } else if (var2 instanceof String var7) {
            ArrayList var8 = new ArrayList(var7.length());
            char[] var9 = var7.toCharArray();
            int var10 = var9.length;

            for (int var11 = 0; var11 < var10; var11++) {
               char var12 = var9[var11];
               var8.add(String.valueOf(var12));
            }

            var3 = var8.iterator();
         } else {
            if (var2 != null) {
               throw new Xqedii_3vsk7zm(Xqedii_194ykutf7nvme(Xqedii_z4wg071.Xqedii_yl2qf35(var2)), this.Xqedii_2lldvpl);
            }

            var3 = Collections.emptyIterator();
         }

         var1.Xqedii_1w2dte6(new Xqedii_2rmul6z(this.Xqedii_10smn4x, var3, this.Xqedii_3sgqz32));
      }
   }

   public static Iterator<Object> Xqedii_rni9q9a(final double var0, final double var2) {
      {
         final double var4 = var2 >= var0
            ? Double.longBitsToDouble(4607182418800017408L)
            : Double.longBitsToDouble(-4616189618054758400L);
         return new Iterator<Object>() {
            public double Xqedii_1kfgyla = var0;

            @Override
            public boolean hasNext() {
               {
                  return ((var4 > Double.longBitsToDouble(0L)
                     ? (
                        this.Xqedii_1kfgyla <= var2 + Double.longBitsToDouble(4472406533629990549L)
                           ? 1
                           : 0
                     )
                     : (
                        this.Xqedii_1kfgyla >= var2 - Double.longBitsToDouble(4472406533629990549L)
                           ? 1
                           : 0
                     )) != 0);
               }
            }

            @Override
            public Object next() {
               {
                  double var1 = this.Xqedii_1kfgyla;
                  this.Xqedii_1kfgyla = this.Xqedii_1kfgyla + var4;
                  return var1;
               }
            }
         };
      }
   }

   private static /* synthetic */ String Xqedii_194ykutf7nvme(String string) {
        return Xqedii_jfz4ff4.Xqedii_18hfvc9bf6ans((String)"O3lPhIUwzGSTy1sCtK/gcNoGeBvz245WyJRyotas0ENB5s24Eq2Vx7K5h8Cab2Yiucdna0XW4erMvrpLnCSR0RUysJo=") + string;
    }
}
