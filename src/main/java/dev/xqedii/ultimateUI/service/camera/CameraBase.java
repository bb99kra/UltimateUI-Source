package dev.xqedii.ultimateUI.service.camera;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.Xqedii_16tq8zh;
import dev.xqedii.ultimateUI.Xqedii_2hd31nw;
import dev.xqedii.ultimateUI.ClockNetworkSnapshot;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.service.npc.CursorNpcService;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerCommon;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.world.clock.WorldClock;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleSupplier;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public abstract class CameraBase {
   public static final String Xqedii_1odgmyzflrx6d = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";
   public static final double Xqedii_3egksez = 0.005;
   public static final String Xqedii_5n40w0o = Xqedii_16tq8zh.Xqedii_24lqvq8vcmbnd(
      "mv2JY8kwAmWPCZ2cZAQXp0HQ+BzeDw2Pd66G0QTbSysMnSemh1pg4PxiIXm8sI/kOt9NE2l9JJi43Vagn5IZBwlJg8PF5g=="
   );
   public static final double Xqedii_2kd9w2z = 0.0025;
   public static final double Xqedii_kgrmxeq = 0.01;
   public static final double Xqedii_uawdwdm = 0.25;
   public static final double Xqedii_3ez1gk5 = 1.5;
   public static final double Xqedii_15jsp6l = 0.0;
   public static final double Xqedii_1r04xyk = -1.5;
   public static final double Xqedii_3s60g2k = 0.0;
   public static final int Xqedii_2pl35b7 = 1;
   public static final int Xqedii_3hn20g0 = 1;
   public static final int Xqedii_2d9zowb = 2;
   public static final int Xqedii_mweg31s = 8;
   public static final int Xqedii_omwsont = 4;
   public static final int Xqedii_1lf6wdh = 3600;
   public static final int Xqedii_1cm20vn = 400;
   public static final long Xqedii_vkci8lp = 24000L;
   public static final long Xqedii_izu3sxb = 102400L;
   public static final int Xqedii_3u5h7z9 = 64;
   public static final double Xqedii_qqalhof = 0.5;
   public static final int Xqedii_12pwiht = 1;
   public static final int Xqedii_pow0q76 = 356;
   public static final double Xqedii_t68xxub = 1920.0;
   public static final double Xqedii_1ayokch = 1080.0;
   public static final double Xqedii_1u3inuv = 0.1111111119389534;
   public static final int Xqedii_12q16yo = 59004;
   public static final float Xqedii_28uqz3u = 0.01F;
   public static final byte Xqedii_jh2dhib = 7;
   public static final double Xqedii_2wopd5r = 0.001;
   public static final int Xqedii_2dcr5pc = 2;
   public static final String INSTANT_CURSOR_SIZE_PATH = Xqedii_16tq8zh.Xqedii_24lqvq8vcmbnd(
      "rTqdLlF5QuAmdWEKnOoUcxD3szK10ji7+F2FH7tZYH5/zJA+zNLB1xY+4x8IqTBSDgRbpUDyIa3JyGzUn9UqC8J0X1JmKt4wkcXRVoIJHfrWxAr0rUnxBT+SGOo="
   );
   public static final double INSTANT_CURSOR_SIZE_DEFAULT = 11.0;
   public static final double INSTANT_CURSOR_SIZE_MIN = 1.0;
   public static final double INSTANT_CURSOR_SIZE_MAX = 100.0;
   public static final double INSTANT_CURSOR_HALF_PX_PER_SIZE = 0.8;
   public static final double INSTANT_CURSOR_REFERENCE_HITBOX_OFFSET_Y = -17.0;
   public static final double INSTANT_CURSOR_SPRITE_DROP_RATIO = 1.0;
   public static final String INSTANT_CURSOR_HITBOX_LIFT_PATH = Xqedii_16tq8zh.Xqedii_24lqvq8vcmbnd(
      "BkvKaMKy/oY9HjRs+uKwVM2ZBxaPGl1byKF9tOWLbMD/zFQbDpgXCWUwR7/JtcQeSI2jBJMRtPU9peTlCLEayOgWEWxsgBi809JSYjpg9BlrYuOUOJ6ANPuFTeGY9hi9P9xeap5+uoBI1Q=="
   );
   public static final double INSTANT_CURSOR_HITBOX_LIFT_DEFAULT = 17.0;
   public static final double INSTANT_CURSOR_HITBOX_LIFT_LIMIT = 540.0;
   public static final double Xqedii_240xnzz = 0.6;
   public static final double Xqedii_192skfn = 1.02;
   public static final double Xqedii_3fcwt5c = 8.0;
   public final UltimateUI Xqedii_11is6fi;
   public final HudService Xqedii_1ilze2o;
   public final CursorNpcService Xqedii_2seki4b;
   public final Map<UUID, Boolean> Xqedii_1npzu61 = new HashMap<>();
   public static final Set<UUID> Xqedii_7ws8hzn;
   public final Map<UUID, Set<Xqedii_2hd31nw>> Xqedii_338djp8 = new HashMap<>();
   public final Map<UUID, Xqedii_2hd31nw> Xqedii_1zrugzw = new HashMap<>();
   public final Map<UUID, Set<UUID>> Xqedii_2wh7n06 = new HashMap<>();
   public final Map<UUID, Location> Xqedii_20i24gg = new HashMap<>();
   public final Map<UUID, Integer> Xqedii_3lrjl61 = new HashMap<>();
   public final Map<UUID, Location> Xqedii_n3z23ui = new HashMap<>();
   public final Map<UUID, Location> Xqedii_6e16db9 = new HashMap<>();
   public final Map<UUID, Double> Xqedii_sa0ugb8 = new HashMap<>();
   public final Map<UUID, Vector> Xqedii_28k36d4 = new HashMap<>();
   public final Map<UUID, Float> Xqedii_2ebsa64 = new HashMap<>();
   public final Map<Class<?>, Method> Xqedii_aybwy8p = new HashMap<>();
   public final Map<UUID, Xqedii_2hd31nw> Xqedii_1elzwyo = new HashMap<>();
   public final Map<UUID, Integer> Xqedii_3d5wwja = new HashMap<>();
   public final Map<UUID, Integer> Xqedii_2qfq33z = new HashMap<>();
   public final Map<UUID, Integer> Xqedii_3hs42n4 = new ConcurrentHashMap<>();
   public final Map<UUID, Integer> Xqedii_375khsg = new ConcurrentHashMap<>();
   public final Map<UUID, Runnable> Xqedii_34owqls = new HashMap<>();
   public final Map<UUID, Map<WorldClock, ClockNetworkSnapshot>> Xqedii_ly8judj = new ConcurrentHashMap<>();
   public final Map<UUID, Boolean> Xqedii_3eelrw1 = new ConcurrentHashMap<>();
   public static final BlockData Xqedii_25bfamp;
   public static final BlockData Xqedii_b6eiiwd;
   public volatile boolean Xqedii_1kofsx0;
   public volatile double Xqedii_3shty0s = Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_3p0qt6c9hpup3(5342793104254746971L, 38));
   public volatile double Xqedii_bbqga21 = Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_2y1dmegxm4qmt(-4145503812356896015L, 9));
   public PacketListenerCommon Xqedii_ub404mm;
   public PacketListenerCommon Xqedii_3oyorda;
   public Method Xqedii_pz5jtze;
   public Field Xqedii_ta19eaj;
   public Method Xqedii_1uhp5aj;
   public Constructor<?> Xqedii_9fc5a0x;
   public boolean Xqedii_373ctmj;
   public boolean Xqedii_2vntyan;
   public CameraBase.CursorListener Xqedii_2g25cps;

   public CameraBase(UltimateUI var1, HudService var2, CursorNpcService var3) {
      this.Xqedii_11is6fi = var1;
      this.Xqedii_1ilze2o = var2;
      this.Xqedii_2seki4b = var3;
   }

   public int Xqedii_139042g() {
      if (((-1464771055 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return Xqedii_16tq8zh.Xqedii_1dndoqm4xbwn4(-1243011789, 14);
      }
   }

   public static double Xqedii_1rcrg7y(DoubleSupplier var0) {
      if ((-308795459 ^ -308795459) != 0) {
         throw new IllegalStateException();
      } else if (var0 == null) {
         return Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_350da5kffknwi(8843928060895234965L, 14));
      } else {
         double var1;
         try {
            var1 = var0.getAsDouble();
         } catch (Throwable var4) {
            return Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_d2sxjj67nucz(2676995279349738274L, 3));
         }

         return Double.isFinite(var1) && var1 > Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_350da5kffknwi(8843928060895247249L, 30))
            ? var1
            : Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_d2sxjj67nucz(-2691295493219554525L, 37));
      }
   }

   public static double resolveInstantCursorSize(FileConfiguration var0) {
      if ((-675871980 * -675871980 - -675871980 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var0 == null) {
         return Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_350da5kffknwi(8843928060735421329L, 29));
      } else {
         double var1 = var0.getDouble(
            Xqedii_16tq8zh.Xqedii_24lqvq8vcmbnd(
               "rTqdLlF5QuAmdWEKnOoUcxD3szK10ji7+F2FH7tZYH5/zJA+zNLB1xY+4x8IqTBSDgRbpUDyIa3JyGzUn9UqC8J0X1JmKt4wkcXRVoIJHfrWxAr0rUnxBT+SGOo="
            ),
            Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_350da5kffknwi(8843928060919698321L, 26))
         );
         return !Double.isFinite(var1)
            ? Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_350da5kffknwi(8843928060919698321L, 26))
            : Xqedii_1z0cuks(
               var1,
               Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_d2sxjj67nucz(2676995279349738274L, 3)),
               Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_2y1dmegxm4qmt(1576872230657077985L, 58))
            );
      }
   }

   public static double resolveInstantCursorHitboxLift(FileConfiguration var0) {
      if (((-882538018 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var0 == null) {
         return Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_2y1dmegxm4qmt(3666235638722620542L, 42));
      } else {
         double var1 = var0.getDouble(
            Xqedii_16tq8zh.Xqedii_24lqvq8vcmbnd(
               "BkvKaMKy/oY9HjRs+uKwVM2ZBxaPGl1byKF9tOWLbMD/zFQbDpgXCWUwR7/JtcQeSI2jBJMRtPU9peTlCLEayOgWEWxsgBi809JSYjpg9BlrYuOUOJ6ANPuFTeGY9hi9P9xeap5+uoBI1Q=="
            ),
            Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_3p0qt6c9hpup3(7865701298350918405L, 32))
         );
         return !Double.isFinite(var1)
            ? Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_2y1dmegxm4qmt(8710872191455770693L, 0))
            : Xqedii_1z0cuks(
               var1,
               Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_350da5kffknwi(8828337948086057873L, 56)),
               Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_3p0qt6c9hpup3(-5632302351199285205L, 9))
            );
      }
   }

   public static double instantCursorHalfPixels(double var0) {
      if ((334463978 * 334463978 - 334463978 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_3p0qt6c9hpup3(-8986583353664830422L, 9)) * var0;
      }
   }

   public static double instantCursorAnchorPixels(double var0) {
      if ((1215485815 * 1215485815 - 1215485815 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_2y1dmegxm4qmt(-4489533800529293773L, 20))
            - instantCursorHalfPixels(var0) * Double.longBitsToDouble(Xqedii_16tq8zh.Xqedii_d2sxjj67nucz(-2691295476467504349L, 25));
      }
   }

   public static double Xqedii_1z0cuks(double var0, double var2, double var4) {
      if ((1799054325 * (1799054325 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return Math.max(var2, Math.min(var4, var0));
      }
   }

   public static long Xqedii_35eqyt5(int var0, int var1) {
      if ((1321978145 * 1321978145 - 1321978145 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return -var0 * Xqedii_16tq8zh.Xqedii_3p0qt6c9hpup3(-3275987415986032554L, 36) - var1 * Xqedii_16tq8zh.Xqedii_2y1dmegxm4qmt(6364585883117680846L, 52);
      }
   }

   public static long Xqedii_yig48yp(long var0, long var2) {
      if ((1106010574 * 1106010574 + 1106010574 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         long var4 = Math.floorMod(var0 - var2, Xqedii_16tq8zh.Xqedii_2y1dmegxm4qmt(2165545078886669417L, 15));
         return Math.min(var4, Xqedii_16tq8zh.Xqedii_350da5kffknwi(-8765148052765096850L, 41) - var4);
      }
   }

   public static int Xqedii_d19amj5(int var0, long var1) {
      if (((-876360501 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         int var3 = Xqedii_16tq8zh.Xqedii_1yofoys97mgzu(-1283901182, 5);
         long var4 = Xqedii_16tq8zh.Xqedii_3p0qt6c9hpup3(3176643721510606176L, 18);

         for (int var6 = Xqedii_16tq8zh.Xqedii_3mpeuere3uqv5(1238912207, 27); var6 < Xqedii_16tq8zh.Xqedii_1dndoqm4xbwn4(-238393493, 23); var6++) {
            long var7 = Xqedii_yig48yp(Xqedii_35eqyt5(var0, var6), var1);
            if (var7 < var4) {
               var4 = var7;
               var3 = var6;
            }
         }

         return var3;
      }
   }

   public static long Xqedii_pqg6glo(long var0) {
      if (((-1519090587 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         long var2 = Xqedii_35eqyt5(Xqedii_16tq8zh.Xqedii_1yofoys97mgzu(-1007077118, 19), Xqedii_16tq8zh.Xqedii_1q1if0je66l3k(255625695, 18));
         long var4 = Xqedii_16tq8zh.Xqedii_2y1dmegxm4qmt(-627999567490838936L, 53);

         for (int var6 = Xqedii_16tq8zh.Xqedii_3mpeuere3uqv5(-1228455120, 12); var6 < Xqedii_16tq8zh.Xqedii_3mpeuere3uqv5(-1239007024, 5); var6++) {
            for (int var7 = Xqedii_16tq8zh.Xqedii_1dndoqm4xbwn4(996052563, 10); var7 < Xqedii_16tq8zh.Xqedii_1q1if0je66l3k(-1014029705, 16); var7++) {
               long var8 = Xqedii_35eqyt5(var6, var7);
               long var10 = Xqedii_yig48yp(var8, var0);
               if (var10 < var4) {
                  var4 = var10;
                  var2 = var8;
                  if (var10 == Xqedii_16tq8zh.Xqedii_2y1dmegxm4qmt(7201703342561183619L, 29)) {
                     return var8;
                  }
               }
            }
         }

         return var2;
      }
   }

   static {
      if (((-1318638379 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_7ws8hzn = ConcurrentHashMap.newKeySet();
         Xqedii_25bfamp = Material.BARRIER.createBlockData();
         Xqedii_b6eiiwd = Material.WATER.createBlockData();
      }
   }

   public interface CursorListener {
      String Xqedii_929tiblxxoxc = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";

      void onCursor(Player var1, double var2, double var4);
   }
}
