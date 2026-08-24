package dev.xqedii.ultimateUI.util;

import java.lang.reflect.Method;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public final class PlatformCompat {
   private static volatile Boolean foliaDetected;

   private PlatformCompat() {
   }

   public static boolean isFolia() {
      Boolean var0 = foliaDetected;
      if (var0 != null) {
         return var0;
      } else {
         synchronized (PlatformCompat.class) {
            if (foliaDetected != null) {
               return foliaDetected;
            } else {
               boolean var2 = false;

               try {
                  Class.forName("io.papermc.paper.threadedregions.scheduler.RegionScheduler", false, PlatformCompat.class.getClassLoader());
                  var2 = hasMethod(Bukkit.getServer().getClass(), "getGlobalRegionScheduler");
               } catch (Throwable var5) {
                  var2 = false;
               }

               foliaDetected = var2;
               return var2;
            }
         }
      }
   }

   public static void runEntityTask(Plugin var0, Entity var1, Runnable var2) {
      if (var0 != null && var2 != null) {
         if (!tryRunEntityTask(var0, var1, var2, 0L)) {
            if (isFolia()) {
               runGlobalTask(var0, var2);
            } else {
               Bukkit.getScheduler().runTask(var0, var2);
            }
         }
      }
   }

   public static void runEntityTaskLater(Plugin var0, Entity var1, Runnable var2, long var3) {
      if (var0 != null && var2 != null) {
         long var5 = Math.max(0L, var3);
         if (var5 <= 0L) {
            runEntityTask(var0, var1, var2);
         } else if (!tryRunEntityTask(var0, var1, var2, var5)) {
            if (isFolia()) {
               runGlobalTaskLater(var0, var2, var5);
            } else {
               Bukkit.getScheduler().runTaskLater(var0, var2, var5);
            }
         }
      }
   }

   public static void runGlobalTask(Plugin var0, Runnable var1) {
      if (var0 != null && var1 != null) {
         if (!tryRunGlobalTask(var0, var1, 0L)) {
            Bukkit.getScheduler().runTask(var0, var1);
         }
      }
   }

   public static void runGlobalTaskLater(Plugin var0, Runnable var1, long var2) {
      if (var0 != null && var1 != null) {
         long var4 = Math.max(0L, var2);
         if (var4 <= 0L) {
            runGlobalTask(var0, var1);
         } else if (!tryRunGlobalTask(var0, var1, var4)) {
            Bukkit.getScheduler().runTaskLater(var0, var1, var4);
         }
      }
   }

   public static void runAsyncTask(Plugin var0, Runnable var1) {
      if (var0 != null && var1 != null) {
         if (!isFolia() || !tryRunFoliaAsyncImmediate(var0, var1)) {
            Bukkit.getScheduler().runTaskAsynchronously(var0, var1);
         }
      }
   }

   public static PlatformCompat.CancellableTask runAsyncTimer(Plugin var0, long var1, long var3, Runnable var5) {
      if (var0 != null && var5 != null) {
         long var6 = Math.max(1L, var3);
         long var8 = Math.max(0L, var1);
         if (isFolia()) {
            PlatformCompat.CancellableTask var10 = tryRunFoliaAsyncTimer(var0, var8, var6, var5);
            if (var10 != null) {
               return var10;
            }
         }

         PlatformCompat.FoliaAsyncTimerTask var12 = new PlatformCompat.FoliaAsyncTimerTask();
         BukkitTask var11 = Bukkit.getScheduler().runTaskTimerAsynchronously(var0, var5, var8, var6);
         var12.setBukkitTask(var11);
         return var12;
      } else {
         return PlatformCompat.NoopCancellableTask.INSTANCE;
      }
   }

   public static void runAsyncTaskLater(Plugin var0, Runnable var1, long var2) {
      if (var0 != null && var1 != null) {
         long var4 = Math.max(1L, var2);
         if (!isFolia() || !tryRunFoliaAsyncDelayed(var0, var1, var4)) {
            Bukkit.getScheduler().runTaskLaterAsynchronously(var0, var1, var4);
         }
      }
   }

   public static PlatformCompat.CancellableTask runEntityTimer(Plugin var0, Entity var1, long var2, long var4, Consumer<PlatformCompat.CancellableTask> var6) {
      if (var0 != null && var1 != null && var6 != null) {
         long var7 = Math.max(0L, var2);
         long var9 = Math.max(1L, var4);
         PlatformCompat.EntityTimerTask var11 = new PlatformCompat.EntityTimerTask(var0, var1, var9, var6);
         runEntityTaskLater(var0, var1, var11::runOnce, var7);
         return var11;
      } else {
         return PlatformCompat.NoopCancellableTask.INSTANCE;
      }
   }

   public static boolean teleportSafely(Entity var0, Location var1) {
      if (var0 == null || var1 == null || var1.getWorld() == null) {
         return false;
      } else if (isFolia() && tryTeleportAsync(var0, var1)) {
         return true;
      } else {
         try {
            return var0.teleport(var1);
         } catch (Throwable var3) {
            return false;
         }
      }
   }

   private static boolean tryRunEntityTask(Plugin var0, Entity var1, Runnable var2, long var3) {
      if (isFolia() && var1 != null) {
         try {
            Method var5 = var1.getClass().getMethod("getScheduler");
            Object var6 = var5.invoke(var1);
            if (var6 == null) {
               return false;
            } else {
               Consumer var7 = var1x -> var2.run();
               Runnable var8 = () -> {
               };
               if (var3 <= 0L) {
                  Method var11 = var6.getClass().getMethod("run", Plugin.class, Consumer.class, Runnable.class);
                  var11.invoke(var6, var0, var7, var8);
                  return true;
               } else {
                  Method var9 = var6.getClass().getMethod("runDelayed", Plugin.class, Consumer.class, Runnable.class, long.class);
                  var9.invoke(var6, var0, var7, var8, var3);
                  return true;
               }
            }
         } catch (Throwable var10) {
            return false;
         }
      } else {
         return false;
      }
   }

   private static boolean tryRunFoliaAsyncImmediate(Plugin var0, Runnable var1) {
      try {
         Server var2 = Bukkit.getServer();
         Method var3 = var2.getClass().getMethod("getAsyncScheduler");
         Object var4 = var3.invoke(var2);
         if (var4 == null) {
            return false;
         } else {
            Consumer var5 = var1x -> var1.run();
            Method var6 = var4.getClass().getMethod("runNow", Plugin.class, Consumer.class);
            var6.invoke(var4, var0, var5);
            return true;
         }
      } catch (Throwable var7) {
         return false;
      }
   }

   private static PlatformCompat.CancellableTask tryRunFoliaAsyncTimer(Plugin var0, long var1, long var3, Runnable var5) {
      try {
         Server var6 = Bukkit.getServer();
         Method var7 = var6.getClass().getMethod("getAsyncScheduler");
         Object var8 = var7.invoke(var6);
         if (var8 == null) {
            return null;
         } else {
            long var9 = Math.max(1L, var1 * 50L);
            long var11 = Math.max(1L, var3 * 50L);
            TimeUnit var13 = TimeUnit.MILLISECONDS;
            PlatformCompat.FoliaAsyncTimerTask var14 = new PlatformCompat.FoliaAsyncTimerTask();
            Consumer var15 = var2 -> {
               if (!var14.isCancelled()) {
                  var5.run();
               }
            };
            Method var16 = null;

            for (Method var20 : var8.getClass().getMethods()) {
               if ("runAtFixedRate".equals(var20.getName()) && var20.getParameterCount() == 5) {
                  var16 = var20;
                  break;
               }
            }

            if (var16 == null) {
               return null;
            } else {
               Object var22 = var16.invoke(var8, var0, var15, var9, var11, var13);
               var14.setScheduledTask(var22);
               return var14;
            }
         }
      } catch (Throwable var21) {
         return null;
      }
   }

   private static boolean tryRunFoliaAsyncDelayed(Plugin var0, Runnable var1, long var2) {
      try {
         Server var4 = Bukkit.getServer();
         Method var5 = var4.getClass().getMethod("getAsyncScheduler");
         Object var6 = var5.invoke(var4);
         if (var6 == null) {
            return false;
         } else {
            long var7 = Math.max(1L, var2 * 50L);
            TimeUnit var9 = TimeUnit.MILLISECONDS;
            Consumer var10 = var1x -> var1.run();
            Method var11 = null;

            for (Method var15 : var6.getClass().getMethods()) {
               if ("runDelayed".equals(var15.getName()) && var15.getParameterCount() == 4) {
                  var11 = var15;
                  break;
               }
            }

            if (var11 == null) {
               return false;
            } else {
               var11.invoke(var6, var0, var10, var7, var9);
               return true;
            }
         }
      } catch (Throwable var16) {
         return false;
      }
   }

   private static boolean tryRunGlobalTask(Plugin var0, Runnable var1, long var2) {
      if (!isFolia()) {
         return false;
      } else {
         try {
            Server var4 = Bukkit.getServer();
            Method var5 = var4.getClass().getMethod("getGlobalRegionScheduler");
            Object var6 = var5.invoke(var4);
            if (var6 == null) {
               return false;
            } else {
               Consumer var7 = var1x -> var1.run();
               if (var2 <= 0L) {
                  Method var10 = var6.getClass().getMethod("run", Plugin.class, Consumer.class);
                  var10.invoke(var6, var0, var7);
                  return true;
               } else {
                  Method var8 = var6.getClass().getMethod("runDelayed", Plugin.class, Consumer.class, long.class);
                  var8.invoke(var6, var0, var7, var2);
                  return true;
               }
            }
         } catch (Throwable var9) {
            return false;
         }
      }
   }

   private static boolean tryTeleportAsync(Entity var0, Location var1) {
      try {
         Method var2 = var0.getClass().getMethod("teleportAsync", Location.class);
         if (var2.invoke(var0, var1) instanceof CompletableFuture var4) {
            var4.exceptionally(var0x -> null);
         }

         return true;
      } catch (NoSuchMethodException var5) {
         return false;
      } catch (Throwable var6) {
         return false;
      }
   }

   private static boolean hasMethod(Class<?> var0, String var1) {
      if (var0 != null && var1 != null && !var1.isBlank()) {
         for (Method var5 : var0.getMethods()) {
            if (var1.equals(var5.getName())) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   public interface CancellableTask {
      void cancel();

      boolean isCancelled();
   }

   private static final class EntityTimerTask implements PlatformCompat.CancellableTask {
      private final Plugin plugin;
      private final Entity entity;
      private final long periodTicks;
      private final Consumer<PlatformCompat.CancellableTask> tick;
      private volatile boolean cancelled;

      private EntityTimerTask(Plugin var1, Entity var2, long var3, Consumer<PlatformCompat.CancellableTask> var5) {
         this.plugin = var1;
         this.entity = var2;
         this.periodTicks = var3;
         this.tick = var5;
      }

      @Override
      public void cancel() {
         this.cancelled = true;
      }

      @Override
      public boolean isCancelled() {
         return this.cancelled;
      }

      private void runOnce() {
         if (!this.cancelled) {
            try {
               this.tick.accept(this);
            } catch (Throwable var2) {
               this.cancel();
            }

            if (!this.cancelled) {
               PlatformCompat.runEntityTaskLater(this.plugin, this.entity, this::runOnce, this.periodTicks);
            }
         }
      }
   }

   private static final class FoliaAsyncTimerTask implements PlatformCompat.CancellableTask {
      private volatile Object scheduledTask;
      private volatile BukkitTask bukkitTask;
      private volatile boolean cancelledFlag;

      void setScheduledTask(Object var1) {
         this.scheduledTask = var1;
      }

      void setBukkitTask(BukkitTask var1) {
         this.bukkitTask = var1;
      }

      @Override
      public void cancel() {
         this.cancelledFlag = true;
         Object var1 = this.scheduledTask;
         if (var1 != null) {
            try {
               var1.getClass().getMethod("cancel").invoke(var1);
            } catch (Throwable var5) {
            }
         }

         BukkitTask var2 = this.bukkitTask;
         if (var2 != null) {
            try {
               var2.cancel();
            } catch (Throwable var4) {
            }
         }
      }

      @Override
      public boolean isCancelled() {
         if (this.cancelledFlag) {
            return true;
         } else {
            BukkitTask var1 = this.bukkitTask;
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

   private static enum NoopCancellableTask implements PlatformCompat.CancellableTask {
      INSTANCE;

      @Override
      public void cancel() {
      }

      @Override
      public boolean isCancelled() {
         return true;
      }
   }
}
