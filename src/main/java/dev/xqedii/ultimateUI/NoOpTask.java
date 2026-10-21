package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.util.PlatformCompat;

public enum NoOpTask implements PlatformCompat.CancellableTask {
   INSTANCE;

   @Override
   public void cancel() {
      if (((-1649085423 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      }
   }

   @Override
   public boolean isCancelled() {
      {
         return true;
      }
   }

}
