package dev.xqedii.ultimateUI;

import java.util.List;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

public final class ScriptEventContext {
   public final String Xqedii_1x9ad7s;
   public final Event Xqedii_11wuen3;
   public String Xqedii_3rt5h0f = "";
   public String Xqedii_lc1i2g8 = "";
   public String Xqedii_1z2rxkd = "";
   public double Xqedii_vkzqb40 = Double.longBitsToDouble(9221120237041090560L);
   public double Xqedii_yhwhrno = Double.longBitsToDouble(9221120237041090560L);
   public double Xqedii_2rqqsu9 = Double.longBitsToDouble(9221120237041090560L);
   public double Xqedii_2qxc6az = Double.longBitsToDouble(9221120237041090560L);
   public double Xqedii_pxsnga0 = Double.longBitsToDouble(9221120237041090560L);
   public double Xqedii_25xm6yv = Double.longBitsToDouble(9221120237041090560L);
   public double Xqedii_jq9ubxh;
   public double Xqedii_1t9vphc;
   public String Xqedii_16npco2 = "";
   public boolean Xqedii_19v5iy3;
   public String Xqedii_2afs9at = "";
   public String Xqedii_a9in0yy = "";
   public String Xqedii_1p91ipf = "";
   public String Xqedii_3464tdf = "";
   public String Xqedii_35rn1ti = "";
   public String Xqedii_17k9l69 = "";
   public String Xqedii_12aqqzl = "";
   public String Xqedii_2f19s3n = "";
   public boolean Xqedii_1zx8kgx;
   public List<String> Xqedii_2f8w5mf;

   public ScriptEventContext(String var1, Event var2) {
      this.Xqedii_1x9ad7s = var1;
      this.Xqedii_11wuen3 = var2;
   }

   public boolean Xqedii_3ojra6l() {
      {
         return this.Xqedii_11wuen3 instanceof Cancellable;
      }
   }

   public void cancel(boolean var1) {
      {
         if (this.Xqedii_11wuen3 instanceof Cancellable var2) {
            var2.setCancelled(var1);
         }
      }
   }

   public boolean Xqedii_2edjbb0() {
      {
         return ((this.Xqedii_11wuen3 instanceof Cancellable var1 && var1.isCancelled()
            ? 1
            : 0) != 0);
      }
   }
}
