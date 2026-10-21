package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.util.PlatformCompat;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

public final class Xqedii_18e36hc implements PlatformCompat.CancellableTask {
   public final Plugin Xqedii_3tyv4fs;
   public final Entity Xqedii_2yllj1m;
   public final long Xqedii_2m5i5qg;
   public final Consumer<PlatformCompat.CancellableTask> Xqedii_2d9umx7;
   public volatile boolean Xqedii_1buqi96;

   public Xqedii_18e36hc(Plugin var1, Entity var2, long var3, Consumer<PlatformCompat.CancellableTask> var5) {
      this.Xqedii_3tyv4fs = var1;
      this.Xqedii_2yllj1m = var2;
      this.Xqedii_2m5i5qg = var3;
      this.Xqedii_2d9umx7 = var5;
   }

   @Override
   public void cancel() {
      {
         this.Xqedii_1buqi96 = true;
      }
   }

   @Override
   public boolean isCancelled() {
      {
         return this.Xqedii_1buqi96;
      }
   }

   public void Xqedii_h4h1qfo() {
      if (!this.Xqedii_1buqi96) {
         try {
            this.Xqedii_2d9umx7.accept(this);
         } catch (Throwable var2) {
            this.cancel();
         }

         if (!this.Xqedii_1buqi96) {
            this.Xqedii_2xhbu2t(this.Xqedii_2m5i5qg);
         }
      }
   }

   public void Xqedii_2xhbu2t(long var1) {
      if (!this.Xqedii_1buqi96) {
         if (!Xqedii_38o2zes.Xqedii_11kjq9u(this.Xqedii_3tyv4fs, this.Xqedii_2yllj1m, this::Xqedii_h4h1qfo, var1, this::Xqedii_m373bmz)) {
            if (Xqedii_38o2zes.Xqedii_4mmto10()) {
               Xqedii_38o2zes.Xqedii_3npx6fi(this.Xqedii_3tyv4fs, this::Xqedii_h4h1qfo, var1);
            } else {
               Bukkit.getScheduler().runTaskLater(this.Xqedii_3tyv4fs, this::Xqedii_h4h1qfo, var1);
            }
         }
      }
   }

   public void Xqedii_m373bmz() {
      if (!this.Xqedii_1buqi96) {
         if (!this.Xqedii_2yllj1m.isValid()) {
            this.cancel();
         } else {
            this.Xqedii_2xhbu2t(1L);
         }
      }
   }
}
