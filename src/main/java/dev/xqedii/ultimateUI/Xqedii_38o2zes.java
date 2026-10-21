package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.util.PlatformCompat;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.PacketEvents;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.server.WrapperPlayServerPlayerPositionAndLook;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.scheduler.BukkitTask;

public final class Xqedii_38o2zes {
   public static volatile Boolean Xqedii_21u50fv;
   public static final boolean Xqedii_3jrjopz;
   public static volatile int Xqedii_2j2spt3;
   public static volatile BukkitTask Xqedii_vvd3vht;
   public static volatile boolean Xqedii_33fwwlu;
   public static volatile GameRule<Boolean> Xqedii_2744hxj;
   public static volatile boolean Xqedii_1gcpkgs;
   public static volatile Attribute Xqedii_taj1wm1;
   public static final byte Xqedii_2ztqvs0 = 7;
   public static volatile boolean Xqedii_2mct6gi;
   public static volatile Method Xqedii_10wf9lh;

   public static boolean Xqedii_4mmto10() {
      {
         Boolean var0 = Xqedii_21u50fv;
         if (var0 != null) {
            return var0;
         } else {
            synchronized (Xqedii_38o2zes.class) {
               if (Xqedii_21u50fv != null) {
                  return Xqedii_21u50fv;
               } else {
                  boolean var2 = false;

                  try {
                     Class.forName(
                        "io.papermc.paper.threadedregions.scheduler.RegionScheduler",
                        false,
                        Xqedii_38o2zes.class.getClassLoader()
                     );
                     var2 = Xqedii_3bgk87b(
                        Bukkit.getServer().getClass(),
                        "getGlobalRegionScheduler"
                     );
                  } catch (Throwable var5) {
                     var2 = false;
                  }

                  Xqedii_21u50fv = Boolean.valueOf(var2);
                  return var2;
               }
            }
         }
      }
   }

   public static int Xqedii_316oplq() {
      {
         if (Xqedii_3jrjopz) {
            try {
               return (int) Bukkit.class.getMethod("getCurrentTick").invoke(null);
            } catch (Throwable ignored) {
               return Xqedii_2j2spt3;
            }
         }
         return Xqedii_2j2spt3;
      }
   }

   public static void Xqedii_2rbu18a(Plugin var0) {
      if (!Xqedii_3jrjopz && var0 != null) {
         BukkitTask var1 = Xqedii_vvd3vht;
         if (var1 == null || var1.isCancelled()) {
            Xqedii_vvd3vht = Bukkit.getScheduler().runTaskTimer(var0, () -> {
               {
                  Xqedii_2j2spt3 = Xqedii_2j2spt3 + 1;
               }
            }, 1L, 1L);
         }
      }
   }

   public static void Xqedii_30bki1a() {
      {
         BukkitTask var0 = Xqedii_vvd3vht;
         Xqedii_vvd3vht = null;
         if (var0 != null) {
            try {
               var0.cancel();
            } catch (Throwable var2) {
            }
         }
      }
   }

   public static PlayerProfile Xqedii_a93o0le(Player var0) {
      if (var0 == null) {
         return null;
      } else {
         try {
            Method var1 = Xqedii_10wf9lh;
            if (var1 == null) {
               var1 = OfflinePlayer.class
                  .getMethod("getPlayerProfile");
               Xqedii_10wf9lh = var1;
            }

            return var1.invoke(var0) instanceof PlayerProfile var2 ? var2 : null;
         } catch (RuntimeException | LinkageError | ReflectiveOperationException var4) {
            return null;
         }
      }
   }

   public static boolean Xqedii_1x72a74(World var0) {
      {
         GameRule var1 = Xqedii_2n35yp2();
         if (var0 != null && var1 != null) {
            try {
               return ((!Boolean.FALSE.equals(var0.getGameRuleValue(var1))
                  ? 1
                  : 0) != 0);
            } catch (Throwable var3) {
               return true;
            }
         } else {
            return true;
         }
      }
   }

   public static Attribute Xqedii_36ek0ro() {
      if (Xqedii_1gcpkgs) {
         return Xqedii_taj1wm1;
      } else {
         Attribute var0 = null;
         String[] var10000 = new String[2];
         var10000[0] = "GENERIC_ATTACK_DAMAGE";
         var10000[1] = "ATTACK_DAMAGE";
         String[] var1 = var10000;
         int var2 = var1.length;

         for (int var3 = 0; var3 < var2; var3++) {
            String var4 = var1[var3];
            if (Xqedii_c1smg22(Attribute.class, var4) instanceof Attribute var6) {
               var0 = var6;
               break;
            }
         }

         Xqedii_taj1wm1 = var0;
         Xqedii_1gcpkgs = true;
         return var0;
      }
   }

   public static Sound Xqedii_ruc2zei(String var0) {
      if (var0 != null && !var0.isBlank()) {
         return Xqedii_c1smg22(Sound.class, var0) instanceof Sound var2 ? var2 : null;
      } else {
         return null;
      }
   }

   public static boolean Xqedii_32nlhe4(Player var0, float var1, float var2) {
      if (var0 != null && !Xqedii_2mct6gi) {
         try {
            var0.setRotation(var1, var2);
            return true;
         } catch (UnsupportedOperationException var4) {
            Xqedii_2mct6gi = true;
            return false;
         }
      } else {
         return false;
      }
   }

   public static void Xqedii_1clfsd0(Player var0, float var1, float var2) {
      if (var0 != null && !Xqedii_32nlhe4(var0, var1, var2)) {
         Xqedii_3bqwxha(var0, var1, var2);
      }
   }

   public static void Xqedii_3bqwxha(Player var0, float var1, float var2) {
      {
         try {
            PacketEvents.getAPI()
               .getPlayerManager()
               .sendPacket(
                  var0,
                  new WrapperPlayServerPlayerPositionAndLook(
                     Double.longBitsToDouble(0L),
                     Double.longBitsToDouble(0L),
                     Double.longBitsToDouble(0L),
                     var1,
                     var2,
                     (byte)7,
                     0,
                     false
                  )
               );
         } catch (Throwable var4) {
         }
      }
   }

   public static GameRule<Boolean> Xqedii_2n35yp2() {
      if (Xqedii_33fwwlu) {
         return Xqedii_2744hxj;
      } else {
         GameRule var0 = null;
         String[] var10000 = new String[2];
         var10000[0] = "DO_DAYLIGHT_CYCLE";
         var10000[1] = "ADVANCE_TIME";
         String[] var1 = var10000;
         int var2 = var1.length;

         for (int var3 = 0; var3 < var2; var3++) {
            String var4 = var1[var3];
            if (Xqedii_c1smg22(GameRule.class, var4) instanceof GameRule var6 && Boolean.class.equals(var6.getType())) {
               var0 = var6;
               break;
            }
         }

         Xqedii_2744hxj = var0;
         Xqedii_33fwwlu = true;
         return var0;
      }
   }

   public static Object Xqedii_c1smg22(Class<?> var0, String var1) {
      {
         try {
            Field var2 = var0.getField(var1);
            return var2.get(null);
         } catch (Throwable var3) {
            return null;
         }
      }
   }

   public static boolean Xqedii_2metxxl(Class<?> var0, String var1) {
      {
         try {
            Method var2 = var0.getMethod(var1);
            return Modifier.isStatic(var2.getModifiers());
         } catch (Throwable var3) {
            return false;
         }
      }
   }

   public static boolean Xqedii_269v6yv(Entity var0) {
      if (!Xqedii_4mmto10()) {
         return Bukkit.isPrimaryThread();
      } else if (var0 == null) {
         return false;
      } else {
         try {
            String var10001 = "isOwnedByCurrentRegion";
            Class[] var10002 = new Class[1];
            var10002[0] = Entity.class;
            Method var10000 = Bukkit.class.getMethod(var10001, var10002);
            Object[] var3 = new Object[1];
            var3[0] = var0;
            Object var1 = var10000.invoke(null, var3);
            return Boolean.TRUE.equals(var1);
         } catch (Throwable var2) {
            return false;
         }
      }
   }

   public static void Xqedii_1avvt9e(Plugin var0, Entity var1, Runnable var2) {
      if (var0 != null && var2 != null) {
         if (!Xqedii_11kjq9u(var0, var1, var2, 0L, null)) {
            if (Xqedii_4mmto10()) {
               Xqedii_34mipix(var0, var2);
            } else {
               Bukkit.getScheduler().runTask(var0, var2);
            }
         }
      }
   }

   public static void Xqedii_2sj63w1(Plugin var0, Entity var1, Runnable var2, long var3) {
      if (var0 != null && var2 != null) {
         long var5 = Math.max(0L, var3);
         if (var5 <= 0L) {
            Xqedii_1avvt9e(var0, var1, var2);
         } else if (!Xqedii_11kjq9u(var0, var1, var2, var5, null)) {
            if (Xqedii_4mmto10()) {
               Xqedii_3npx6fi(var0, var2, var5);
            } else {
               Bukkit.getScheduler().runTaskLater(var0, var2, var5);
            }
         }
      }
   }

   public static void Xqedii_34mipix(Plugin var0, Runnable var1) {
      if (var0 != null && var1 != null) {
         if (!Xqedii_27hssek(var0, var1, 0L)) {
            Bukkit.getScheduler().runTask(var0, var1);
         }
      }
   }

   public static void Xqedii_3npx6fi(Plugin var0, Runnable var1, long var2) {
      if (var0 != null && var1 != null) {
         long var4 = Math.max(0L, var2);
         if (var4 <= 0L) {
            Xqedii_34mipix(var0, var1);
         } else if (!Xqedii_27hssek(var0, var1, var4)) {
            Bukkit.getScheduler().runTaskLater(var0, var1, var4);
         }
      }
   }

   public static void Xqedii_u043a3q(Plugin var0, Runnable var1) {
      if (var0 != null && var1 != null) {
         if (!Xqedii_4mmto10() || !Xqedii_35rg8b0(var0, var1)) {
            Bukkit.getScheduler().runTaskAsynchronously(var0, var1);
         }
      }
   }

   public static PlatformCompat.CancellableTask Xqedii_uplb4zf(Plugin var0, long var1, long var3, Runnable var5) {
      if (var0 != null && var5 != null) {
         long var6 = Math.max(1L, var3);
         long var8 = Math.max(0L, var1);
         if (Xqedii_4mmto10()) {
            PlatformCompat.CancellableTask var10 = Xqedii_3nnzlsr(var0, var8, var6, var5);
            if (var10 != null) {
               return var10;
            }
         }

         Xqedii_z0t7eh7 var12 = new Xqedii_z0t7eh7();
         BukkitTask var11 = Bukkit.getScheduler().runTaskTimerAsynchronously(var0, var5, var8, var6);
         var12.Xqedii_2hpi5bz(var11);
         return var12;
      } else {
         return NoOpTask.INSTANCE;
      }
   }

   public static void Xqedii_3rvp96a(Plugin var0, Runnable var1, long var2) {
      if (var0 != null && var1 != null) {
         long var4 = Math.max(1L, var2);
         if (!Xqedii_4mmto10() || !Xqedii_3kq4z78(var0, var1, var4)) {
            Bukkit.getScheduler().runTaskLaterAsynchronously(var0, var1, var4);
         }
      }
   }

   public static PlatformCompat.CancellableTask Xqedii_j3mggbv(Plugin var0, Entity var1, long var2, long var4, Consumer<PlatformCompat.CancellableTask> var6) {
      if (var0 != null && var1 != null && var6 != null) {
         long var7 = Math.max(0L, var2);
         long var9 = Math.max(1L, var4);
         Xqedii_18e36hc var11 = new Xqedii_18e36hc(var0, var1, var9, var6);
         Xqedii_2sj63w1(var0, var1, var11::Xqedii_h4h1qfo, var7);
         return var11;
      } else {
         return NoOpTask.INSTANCE;
      }
   }

   public static boolean Xqedii_2pl8m41(Entity var0, Location var1) {
      if (var0 != null && var1 != null && var1.getWorld() != null) {
         try {
            if (var0.teleport(var1)) {
               return true;
            }
         } catch (Throwable var3) {
         }

         return ((Xqedii_4mmto10() && Xqedii_uy34zfv(var0, var1)
            ? 1
            : 0) != 0);
      } else {
         return false;
      }
   }

   public static boolean Xqedii_fxq2htx(Entity var0, Location var1) {
      if (var0 != null && var1 != null && var1.getWorld() != null) {
         try {
            return var0.teleport(var1);
         } catch (Throwable var3) {
            return ((Xqedii_4mmto10() && Xqedii_uy34zfv(var0, var1)
               ? 1
               : 0) != 0);
         }
      } else {
         return false;
      }
   }

   public static boolean Xqedii_11kjq9u(Plugin var0, Entity var1, Runnable var2, long var3, Runnable var5) {
      if (Xqedii_4mmto10() && var1 != null) {
         try {
            Method var6 = var1.getClass()
               .getMethod("getScheduler");
            Object var7 = var6.invoke(var1);
            if (var7 == null) {
               return false;
            } else {
               Consumer var8 = var1x -> {
                  {
                     var2.run();
                  }
               };
               Runnable var9 = var5 != null ? var5 : () -> {
                  if ((-1914593687 * -1914593687 + -1914593687 & 1) != 0) {
                     throw new IllegalStateException();
                  }
               };
               if (var3 <= 0L) {
                  Class var13 = var7.getClass();
                  String var14 = "run";
                  Class[] var16 = new Class[3];
                  var16[0] = Plugin.class;
                  var16[1] = Consumer.class;
                  var16[2] = Runnable.class;
                  Method var12 = var13.getMethod(var14, var16);
                  Object[] var17 = new Object[3];
                  var17[0] = var0;
                  var17[1] = var8;
                  var17[2] = var9;
                  var12.invoke(var7, var17);
                  return true;
               } else {
                  Class var10000 = var7.getClass();
                  String var10001 = "runDelayed";
                  Class[] var10002 = new Class[4];
                  var10002[0] = Plugin.class;
                  var10002[1] = Consumer.class;
                  var10002[2] = Runnable.class;
                  var10002[3] = long.class;
                  Method var10 = var10000.getMethod(var10001, var10002);
                  Object[] var15 = new Object[4];
                  var15[0] = var0;
                  var15[1] = var8;
                  var15[2] = var9;
                  var15[3] = var3;
                  var10.invoke(var7, var15);
                  return true;
               }
            }
         } catch (Throwable var11) {
            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean Xqedii_35rg8b0(Plugin var0, Runnable var1) {
      {
         try {
            Server var2 = Bukkit.getServer();
            Method var3 = var2.getClass()
               .getMethod("getAsyncScheduler");
            Object var4 = var3.invoke(var2);
            if (var4 == null) {
               return false;
            } else {
               Consumer var5 = var1x -> {
                  {
                     var1.run();
                  }
               };
               Class var10000 = var4.getClass();
               String var10001 = "runNow";
               Class[] var10002 = new Class[2];
               var10002[0] = Plugin.class;
               var10002[1] = Consumer.class;
               Method var6 = var10000.getMethod(var10001, var10002);
               Object[] var8 = new Object[2];
               var8[0] = var0;
               var8[1] = var5;
               var6.invoke(var4, var8);
               return true;
            }
         } catch (Throwable var7) {
            return false;
         }
      }
   }

   public static PlatformCompat.CancellableTask Xqedii_3nnzlsr(Plugin var0, long var1, long var3, Runnable var5) {
      {
         try {
            Server var6 = Bukkit.getServer();
            Method var7 = var6.getClass()
               .getMethod("getAsyncScheduler");
            Object var8 = var7.invoke(var6);
            if (var8 == null) {
               return null;
            } else {
               long var9 = Math.max(
                  1L, var1 * 50L
               );
               long var11 = Math.max(
                  1L, var3 * 50L
               );
               TimeUnit var13 = TimeUnit.MILLISECONDS;
               Xqedii_z0t7eh7 var14 = new Xqedii_z0t7eh7();
               Consumer var15 = var2 -> {
                  {
                     if (!var14.isCancelled()) {
                        var5.run();
                     }
                  }
               };
               Method var16 = null;
               Method[] var17 = var8.getClass().getMethods();
               int var18 = var17.length;

               for (int var19 = 0; var19 < var18; var19++) {
                  Method var20 = var17[var19];
                  if ("runAtFixedRate"
                        .equals(var20.getName())
                     && var20.getParameterCount() == 5) {
                     var16 = var20;
                     break;
                  }
               }

               if (var16 == null) {
                  return null;
               } else {
                  Object[] var10002 = new Object[5];
                  var10002[0] = var0;
                  var10002[1] = var15;
                  var10002[2] = var9;
                  var10002[3] = var11;
                  var10002[4] = var13;
                  var17 = (Method[])var16.invoke(var8, var10002);
                  var14.Xqedii_oc0hnm3(var17);
                  return var14;
               }
            }
         } catch (Throwable var21) {
            return null;
         }
      }
   }

   public static boolean Xqedii_3kq4z78(Plugin var0, Runnable var1, long var2) {
      {
         try {
            Server var4 = Bukkit.getServer();
            Method var5 = var4.getClass()
               .getMethod("getAsyncScheduler");
            Object var6 = var5.invoke(var4);
            if (var6 == null) {
               return false;
            } else {
               long var7 = Math.max(
                  1L, var2 * 50L
               );
               TimeUnit var9 = TimeUnit.MILLISECONDS;
               Consumer var10 = var1x -> {
                  {
                     var1.run();
                  }
               };
               Method var11 = null;
               Method[] var12 = var6.getClass().getMethods();
               int var13 = var12.length;

               for (int var14 = 0; var14 < var13; var14++) {
                  Method var15 = var12[var14];
                  if ("runDelayed".equals(var15.getName())
                     && var15.getParameterCount() == 4) {
                     var11 = var15;
                     break;
                  }
               }

               if (var11 == null) {
                  return false;
               } else {
                  Object[] var10002 = new Object[4];
                  var10002[0] = var0;
                  var10002[1] = var10;
                  var10002[2] = var7;
                  var10002[3] = var9;
                  var11.invoke(var6, var10002);
                  return true;
               }
            }
         } catch (Throwable var16) {
            return false;
         }
      }
   }

   public static boolean Xqedii_27hssek(Plugin var0, Runnable var1, long var2) {
      if (!Xqedii_4mmto10()) {
         return false;
      } else {
         try {
            Server var4 = Bukkit.getServer();
            Method var5 = var4.getClass()
               .getMethod(
                  "getGlobalRegionScheduler"
               );
            Object var6 = var5.invoke(var4);
            if (var6 == null) {
               return false;
            } else {
               Consumer var7 = var1x -> {
                  {
                     var1.run();
                  }
               };
               if (var2 <= 0L) {
                  Class var11 = var6.getClass();
                  String var12 = "run";
                  Class[] var14 = new Class[2];
                  var14[0] = Plugin.class;
                  var14[1] = Consumer.class;
                  Method var10 = var11.getMethod(var12, var14);
                  Object[] var15 = new Object[2];
                  var15[0] = var0;
                  var15[1] = var7;
                  var10.invoke(var6, var15);
                  return true;
               } else {
                  Class var10000 = var6.getClass();
                  String var10001 = "runDelayed";
                  Class[] var10002 = new Class[3];
                  var10002[0] = Plugin.class;
                  var10002[1] = Consumer.class;
                  var10002[2] = long.class;
                  Method var8 = var10000.getMethod(var10001, var10002);
                  Object[] var13 = new Object[3];
                  var13[0] = var0;
                  var13[1] = var7;
                  var13[2] = var2;
                  var8.invoke(var6, var13);
                  return true;
               }
            }
         } catch (Throwable var9) {
            return false;
         }
      }
   }

   public static boolean Xqedii_uy34zfv(Entity var0, Location var1) {
      {
         try {
            Class var10000 = var0.getClass();
            String var10001 = "teleportAsync";
            Class[] var10002 = new Class[1];
            var10002[0] = Location.class;
            Method var2 = var10000.getMethod(var10001, var10002);
            Object[] var7 = new Object[1];
            var7[0] = var1;
            if (var2.invoke(var0, var7) instanceof CompletableFuture var4) {
               var4.exceptionally(var0x -> {
                  {
                     return null;
                  }
               });
            }

            return true;
         } catch (NoSuchMethodException var5) {
            return false;
         } catch (Throwable var6) {
            return false;
         }
      }
   }

   public static boolean Xqedii_3bgk87b(Class<?> var0, String var1) {
      if (var0 != null && var1 != null && !var1.isBlank()) {
         Method[] var2 = var0.getMethods();
         int var3 = var2.length;

         for (int var4 = 0; var4 < var3; var4++) {
            Method var5 = var2[var4];
            if (var1.equals(var5.getName())) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   static {
      {
         Xqedii_3jrjopz = Xqedii_2metxxl(
            Bukkit.class, "getCurrentTick"
         );
      }
   }
}
