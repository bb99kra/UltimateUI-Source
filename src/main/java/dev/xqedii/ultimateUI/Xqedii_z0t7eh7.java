package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.util.PlatformCompat;
import org.bukkit.scheduler.BukkitTask;

public final class Xqedii_z0t7eh7 implements PlatformCompat.CancellableTask {
   public volatile Object Xqedii_3rh4n53;
   public volatile BukkitTask Xqedii_3mk6dk4;
   public volatile boolean Xqedii_2mrfj8p;

   public void Xqedii_oc0hnm3(Object var1) {
      {
         this.Xqedii_3rh4n53 = var1;
      }
   }

   public void Xqedii_2hpi5bz(BukkitTask var1) {
      {
         this.Xqedii_3mk6dk4 = var1;
      }
   }

   @Override
   public void cancel() {
      {
         this.Xqedii_2mrfj8p = true;
         Object var1 = this.Xqedii_3rh4n53;
         if (var1 != null) {
            try {
               var1.getClass().getMethod("cancel").invoke(var1);
            } catch (Throwable var5) {
            }
         }

         BukkitTask var2 = this.Xqedii_3mk6dk4;
         if (var2 != null) {
            try {
               var2.cancel();
            } catch (Throwable var4) {
            }
         }
      }
   }

   @Override
   public boolean isCancelled() {
      if (this.Xqedii_2mrfj8p) {
         return true;
      } else {
         BukkitTask var1 = this.Xqedii_3mk6dk4;
         if (var1 != null) {
            try {
               return var1.isCancelled();
            } catch (Throwable var3) {
            }
         }

         return false;
      }
   }
}
