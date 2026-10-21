package dev.xqedii.ultimateUI.service.camera;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.Xqedii_2fwne6w;
import dev.xqedii.ultimateUI.Xqedii_2hd31nw;
import dev.xqedii.ultimateUI.Xqedii_38o2zes;
import dev.xqedii.ultimateUI.ClockNetworkSnapshot;
import dev.xqedii.ultimateUI.Xqedii_7q5l4nt;
import dev.xqedii.ultimateUI.Xqedii_zg8ict9;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.service.npc.CursorNpcService;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.PacketEvents;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketSendEvent;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.manager.server.ServerVersion;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType.Play.Server;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.world.clock.ClockNetworkState;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.world.clock.WorldClock;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.world.clock.WorldClocks;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.server.WrapperPlayServerPlayerPositionAndLook;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.server.WrapperPlayServerTimeUpdate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;

public abstract class ShaderCursor extends CameraSoundMask {
   public static final String Xqedii_292jz4q08lw5s = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";

   public ShaderCursor(UltimateUI var1, HudService var2, CursorNpcService var3) {
      super(var1, var2, var3);
   }

   public double getShaderCursorHitboxLift() {
      if ((586139512 * 586139512 + 586139512 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_bbqga21;
      }
   }

   public boolean isShaderCursorEnabled() {
      if ((1195982415 * 1195982415 - 1195982415 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_1kofsx0;
      }
   }

   public boolean isShaderCursorWanted(Player var1) {
      if ((1896178942 * 1896178942 - 1896178942 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return ((!this.isShaderCursorEnabled(var1) || this.Xqedii_1ilze2o != null && this.Xqedii_1ilze2o.isBossbarSession(var1)
            ? Xqedii_7q5l4nt.Xqedii_ks3nc41dsn6q(1169076620, 0)
            : Xqedii_7q5l4nt.Xqedii_mhrnam3yf82v(2094331235, 1)) != 0);
      }
   }

   public boolean isShaderCursorEnabled(Player var1) {
      if (((-2020295462 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return this.Xqedii_1kofsx0;
      } else {
         Boolean var2 = this.Xqedii_3eelrw1.get(var1.getUniqueId());
         return var2 != null ? var2 : this.Xqedii_1kofsx0;
      }
   }

   public void setShaderCursorOverride(UUID var1, Boolean var2) {
      if ((2117095597 * (2117095597 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         if (var2 == null) {
            this.Xqedii_3eelrw1.remove(var1);
         } else {
            this.Xqedii_3eelrw1.put(var1, var2);
         }
      }
   }

   public Boolean getShaderCursorOverride(UUID var1) {
      if ((396514407 * 396514407 - 396514407 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return var1 == null ? null : this.Xqedii_3eelrw1.get(var1);
      }
   }

   public boolean isShaderCursorActive(Player var1) {
      if (((1171628203 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return ((var1 != null && this.Xqedii_3d5wwja.containsKey(var1.getUniqueId())
            ? Xqedii_7q5l4nt.Xqedii_fgx4u9p369dg(-539000161, 22)
            : Xqedii_7q5l4nt.Xqedii_ks3nc41dsn6q(1169076620, 14)) != 0);
      }
   }

   public void setShaderCursorSprite(Player var1, int var2) {
      if ((695669079 * 695669079 - 695669079 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         UUID var3 = var1.getUniqueId();
         if (this.Xqedii_3d5wwja.containsKey(var3)) {
            int var4 = Math.max(Xqedii_7q5l4nt.Xqedii_ks3nc41dsn6q(1169076620, 29), Math.min(Xqedii_7q5l4nt.Xqedii_mhrnam3yf82v(2078713771, 28), var2));
            Integer var5 = this.Xqedii_2qfq33z.put(var3, var4);
            if (var5 == null || var5 != var4) {
               this.Xqedii_qzeaul5(var1);
            }
         }
      }
   }

   public static int shaderCursorSpriteOf(String var0) {
      if ((-2042603500 * (-2042603500 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var0 != null && !var0.isEmpty()) {
         for (int var1 = Xqedii_7q5l4nt.Xqedii_1f197xxzobjv1(1363508861, 0); var1 < var0.length(); var1++) {
            int var2 = var0.charAt(var1) - Xqedii_7q5l4nt.Xqedii_fgx4u9p369dg(-46201422, 23);
            if (var2 >= 0 && var2 < Xqedii_7q5l4nt.Xqedii_fgx4u9p369dg(88064831, 7)) {
               return var2;
            }
         }

         return Xqedii_7q5l4nt.Xqedii_1f197xxzobjv1(1363508861, 18);
      } else {
         return Xqedii_7q5l4nt.Xqedii_1f197xxzobjv1(1363508861, 25);
      }
   }

   public static int Xqedii_31s8nnd(double var0) {
      if (((1385801568 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         double var2 = Double.isFinite(var0) && var0 > Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_3te5em37hvhmg(-2625449803082636015L, 60))
            ? var0
            : Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_6hobr8b0qtan(-3377439474917884176L, 12));
         double var4 = Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_uoep3qsikdbr(6858666606416283200L, 2)) / var2;
         int var6 = (int)Math.round(var4 / Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_uoep3qsikdbr(-6824944708666079678L, 44)));
         return Math.max(Xqedii_7q5l4nt.Xqedii_1f197xxzobjv1(-1363508862, 21), Math.min(Xqedii_7q5l4nt.Xqedii_mhrnam3yf82v(-725718405, 24), var6));
      }
   }

   public static double Xqedii_2pakyt3(int var0) {
      if ((-1988141338 * -1988141338 - -1988141338 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return var0 * Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_3te5em37hvhmg(-485132998797943320L, 42));
      }
   }

   public static double Xqedii_2ul52kn(int var0) {
      if (((-2000533400 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return Xqedii_2pakyt3(var0) * Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_uoep3qsikdbr(-6822701713535351230L, 53));
      }
   }

   public static float Xqedii_1pc3vv6(float var0) {
      if (((-705281558 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         float var1 = var0 % Float.intBitsToFloat(Xqedii_7q5l4nt.Xqedii_ks3nc41dsn6q(1170103436, 22));
         if (var1 >= Float.intBitsToFloat(Xqedii_7q5l4nt.Xqedii_fgx4u9p369dg(-167119877, 27))) {
            var1 -= Float.intBitsToFloat(Xqedii_7q5l4nt.Xqedii_1f197xxzobjv1(1375285885, 24));
         } else if (var1 < Float.intBitsToFloat(Xqedii_7q5l4nt.Xqedii_1f197xxzobjv1(1631944334, 10))) {
            var1 += Float.intBitsToFloat(Xqedii_7q5l4nt.Xqedii_mhrnam3yf82v(-204109842, 11));
         }

         return var1;
      }
   }

   public static double Xqedii_95gphhd(double var0, float var2, int var3) {
      if (((-1751700372 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         double var4 = Xqedii_2pakyt3(var3);
         return Xqedii_1z0cuks(var0 + var2, -var4, var4);
      }
   }

   public static double Xqedii_2okyqdr(float var0, int var1) {
      if (((-2025839438 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         double var2 = Xqedii_2ul52kn(var1);
         return Xqedii_1z0cuks(var0, -var2, var2);
      }
   }

   public static double Xqedii_3s259ju(double var0, int var2) {
      if ((-934462609 * -934462609 + -934462609 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         double var3 = Xqedii_1z0cuks(
            var0 / Xqedii_2pakyt3(var2),
            Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_6hobr8b0qtan(-4376228134071268133L, 6)),
            Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_3te5em37hvhmg(-485132997724201496L, 42))
         );
         return Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_3te5em37hvhmg(7033850617169987454L, 6))
            * (Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_2a18qapwj3f1m(-9004247039633348049L, 34)) + var3);
      }
   }

   public static double Xqedii_2atcf61(double var0, int var2) {
      if ((2115067106 * 2115067106 + 2115067106 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         double var3 = Xqedii_1z0cuks(
            var0 / Xqedii_2ul52kn(var2),
            Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_6hobr8b0qtan(5640316399185418594L, 45)),
            Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_6hobr8b0qtan(9070709528204561990L, 61))
         );
         return Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_2a18qapwj3f1m(-9004247002483611089L, 37))
            * (Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_uoep3qsikdbr(-6824957911395546557L, 12)) + var3);
      }
   }

   public void Xqedii_29t98z3(Player var1, int var2) {
      if ((1763404076 * 1763404076 + 1763404076 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         UUID var3 = var1.getUniqueId();
         this.Xqedii_3d5wwja.put(var3, var2);
         this.Xqedii_2qfq33z.put(var3, Xqedii_7q5l4nt.Xqedii_1f197xxzobjv1(1363508861, 20));
         this.Xqedii_3hs42n4.remove(var3);
         this.Xqedii_375khsg.remove(var3);
         this.Xqedii_qzeaul5(var1);
         this.Xqedii_1tn6s50(
            var1,
            Float.intBitsToFloat(Xqedii_7q5l4nt.Xqedii_fgx4u9p369dg(-543194465, 22)),
            Float.intBitsToFloat(Xqedii_7q5l4nt.Xqedii_fgx4u9p369dg(352255228, 9))
         );
         this.Xqedii_ma0sujh(var1);
      }
   }

   public void Xqedii_1bh5wam(Player var1, int var2) {
      if (((-876011252 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         UUID var3 = var1.getUniqueId();
         Integer var4 = this.Xqedii_3d5wwja.get(var3);
         if (var4 != null && var4 != var2) {
            this.Xqedii_3d5wwja.put(var3, var2);
            this.Xqedii_qzeaul5(var1);
         }
      }
   }

   public void Xqedii_qzeaul5(Player var1) {
      if ((-1538921432 * -1538921432 + -1538921432 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.isOnline()) {
         UUID var2 = var1.getUniqueId();
         Integer var3 = this.Xqedii_3d5wwja.get(var2);
         if (var3 != null) {
            int var4 = this.Xqedii_2qfq33z.getOrDefault(var2, Xqedii_7q5l4nt.Xqedii_fgx4u9p369dg(-1086388929, 23));
            int var5 = var3 * Xqedii_7q5l4nt.Xqedii_fgx4u9p369dg(2139520522, 16) + var4;
            this.Xqedii_3hs42n4.put(var2, var5);
            this.Xqedii_375khsg.computeIfAbsent(var2, var2x -> {
               if ((-742742984 * -742742984 + -742742984 & 1) != 0) {
                  throw new IllegalStateException();
               } else {
                  return Xqedii_d19amj5(var5, var1.getWorld().getGameTime());
               }
            });
            this.Xqedii_3ut80ql(var1, this.Xqedii_1aj31i1(var2, var5));
         }
      }
   }

   public long Xqedii_1aj31i1(UUID var1, int var2) {
      if ((-208126694 ^ -208126694) != 0) {
         throw new IllegalStateException();
      } else {
         return Xqedii_35eqyt5(var2, this.Xqedii_375khsg.getOrDefault(var1, Xqedii_7q5l4nt.Xqedii_mhrnam3yf82v(2094331233, 1)));
      }
   }

   public void Xqedii_3siyzd1(Player var1) {
      if ((708928489 * 708928489 - 708928489 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.isOnline()) {
         Integer var2 = this.Xqedii_3hs42n4.get(var1.getUniqueId());
         if (var2 != null) {
            this.Xqedii_3ut80ql(var1, this.Xqedii_1aj31i1(var1.getUniqueId(), var2));
         }
      }
   }

   public boolean Xqedii_wnk0odx(Player var1, float var2, float var3, double var4, double var6) {
      if (((-1963240400 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         float var8 = (float)var4;
         float var9 = (float)var6;
         if (Math.abs(Xqedii_1pc3vv6(var8 - var2)) < Float.intBitsToFloat(Xqedii_7q5l4nt.Xqedii_1f197xxzobjv1(-1341994909, 11))
            && Math.abs(var9 - var3) < Float.intBitsToFloat(Xqedii_7q5l4nt.Xqedii_1f197xxzobjv1(-384527366, 9))) {
            return (Xqedii_7q5l4nt.Xqedii_fgx4u9p369dg(1409020912, 11) != 0);
         } else {
            Xqedii_38o2zes.Xqedii_32nlhe4(var1, var8, var9);
            this.Xqedii_1tn6s50(var1, var8, var9);
            return (Xqedii_7q5l4nt.Xqedii_fgx4u9p369dg(-68135941, 27) != 0);
         }
      }
   }

   public void Xqedii_1tn6s50(Player var1, float var2, float var3) {
      if (((-1235786980 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         try {
            PacketEvents.getAPI()
               .getPlayerManager()
               .sendPacket(
                  var1,
                  new WrapperPlayServerPlayerPositionAndLook(
                     Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_3te5em37hvhmg(5172986391429030127L, 37)),
                     Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_3te5em37hvhmg(-7762114202512007537L, 46)),
                     Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_6hobr8b0qtan(-7954068426615007085L, 51)),
                     var2,
                     var3,
                     (byte)Xqedii_7q5l4nt.Xqedii_ks3nc41dsn6q(1169076748, 7),
                     Xqedii_7q5l4nt.Xqedii_fgx4u9p369dg(-1618829345, 30),
                     (Xqedii_7q5l4nt.Xqedii_1f197xxzobjv1(1363508861, 2) != 0)
                  )
               );
         } catch (Throwable var5) {
         }
      }
   }

   public long Xqedii_1gl4f7n(Player var1) {
      if ((967744092 ^ 967744092) != 0) {
         throw new IllegalStateException();
      } else {
         UUID var2 = var1.getUniqueId();
         Integer var3 = this.Xqedii_3hs42n4.get(var2);
         long var4 = var3 == null ? var1.getWorld().getGameTime() : this.Xqedii_1aj31i1(var2, var3);
         return Xqedii_pqg6glo(var4);
      }
   }

   public void Xqedii_3ut80ql(Player var1, long var2) {
      if ((-2114324177 * (-2114324177 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         if (Xqedii_7ws8hzn.contains(var1.getUniqueId())) {
            var2 = this.Xqedii_1gl4f7n(var1);
         }

         try {
            PacketEvents.getAPI().getPlayerManager().sendPacket(var1, this.Xqedii_4cc771i(var1, var2));
         } catch (Throwable var5) {
            this.Xqedii_3d5wwja.remove(var1.getUniqueId());
            this.Xqedii_3hs42n4.remove(var1.getUniqueId());
            this.Xqedii_11is6fi.getLogger().warning(Xqedii_3ags7cvzryq24(var1.getName(), String.valueOf(var5)));
         }
      }
   }

   public WrapperPlayServerTimeUpdate Xqedii_4cc771i(Player var1, long var2) {
      if ((-254152861 ^ -254152861) != 0) {
         throw new IllegalStateException();
      } else {
         World var4 = var1.getWorld();
         long var5 = var1.getPlayerTime();
         int var7 = Xqedii_38o2zes.Xqedii_1x72a74(var4) && var1.isPlayerTimeRelative()
            ? Xqedii_7q5l4nt.Xqedii_ks3nc41dsn6q(1160688012, 23)
            : Xqedii_7q5l4nt.Xqedii_ks3nc41dsn6q(1169076620, 28);
         ServerVersion var8 = PacketEvents.getAPI().getServerManager().getVersion();
         HashMap var9 = new HashMap();
         if (!var8.isNewerThanOrEquals(ServerVersion.V_26_1)) {
            long var15 = var5;
            if (var7 == 0 && var8.isOlderThan(ServerVersion.V_1_21_2)) {
               var15 = -var5;
               if (var15 == Xqedii_7q5l4nt.Xqedii_3te5em37hvhmg(-1940528550628001885L, 44)) {
                  var15 = Xqedii_7q5l4nt.Xqedii_uoep3qsikdbr(-6824957911393450430L, 21);
               }
            }

            var9.put(
               WorldClocks.OVERWORLD,
               new ClockNetworkState(
                  var15,
                  Float.intBitsToFloat(Xqedii_7q5l4nt.Xqedii_1f197xxzobjv1(1363508861, 16)),
                  var7 != 0
                     ? Float.intBitsToFloat(Xqedii_7q5l4nt.Xqedii_mhrnam3yf82v(-1481963490, 12))
                     : Float.intBitsToFloat(Xqedii_7q5l4nt.Xqedii_1f197xxzobjv1(1363508861, 3))
               )
            );
            return new WrapperPlayServerTimeUpdate(var2, var9);
         } else {
            Map var10 = this.Xqedii_ly8judj.get(var1.getUniqueId());
            if (var10 != null && !var10.isEmpty()) {
               long var16 = var4.getGameTime();

               for (Entry var14 : (Iterable<Entry>) (var10.entrySet())) {

                  var9.put((WorldClock)var14.getKey(), Xqedii_10yqyjp((ClockNetworkSnapshot)var14.getValue(), var16));
               }
            } else {
               WorldClock var11 = Xqedii_1d6ww9x(var4);
               if (var11 != null) {
                  var9.put(
                     var11,
                     new ClockNetworkState(
                        var5,
                        Float.intBitsToFloat(Xqedii_7q5l4nt.Xqedii_fgx4u9p369dg(1341116353, 13)),
                        var7 != 0
                           ? Float.intBitsToFloat(Xqedii_7q5l4nt.Xqedii_1f197xxzobjv1(289766941, 7))
                           : Float.intBitsToFloat(Xqedii_7q5l4nt.Xqedii_1f197xxzobjv1(1363508861, 16))
                     )
                  );
               }
            }

            return new WrapperPlayServerTimeUpdate(var2, var9);
         }
      }
   }

   public static ClockNetworkState Xqedii_10yqyjp(ClockNetworkSnapshot var0, long var1) {
      if ((276720098 * 276720098 - 276720098 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         ClockNetworkState var3 = var0.state();
         long var4 = var1 - var0.gameTime();
         if (var4 > Xqedii_7q5l4nt.Xqedii_3te5em37hvhmg(7944944861379007559L, 62)
            && var3.getRate() != Float.intBitsToFloat(Xqedii_7q5l4nt.Xqedii_fgx4u9p369dg(-271597233, 21))) {
            double var6 = var3.getPartialTick() + (double)var4 * var3.getRate();
            long var8 = (long)Math.floor(var6);
            return new ClockNetworkState(var3.getTotalTicks() + var8, (float)(var6 - var8), var3.getRate());
         } else {
            return var3;
         }
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static WorldClock Xqedii_1d6ww9x(World var0) {
      if ((294756140 * 294756140 - 294756140 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var0 == null) {
         return null;
      } else {
         return switch (dev.xqedii.ultimateUI.Xqedii_37vakyu.Xqedii_2n2ajii[var0.getEnvironment().ordinal()]) {
            case 1 -> WorldClocks.OVERWORLD;
            case 2 -> WorldClocks.THE_END;
            default -> null;
         };
      }
   }

   public void Xqedii_15whgsi(Player var1, WrapperPlayServerTimeUpdate var2) {
      if (((-1128393442 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.getWorldAge() >= Xqedii_7q5l4nt.Xqedii_6hobr8b0qtan(4744931770740630434L, 21)) {
         Map var3 = var2.getClockUpdates();
         if (var3 != null && !var3.isEmpty()) {
            if (PacketEvents.getAPI().getServerManager().getVersion().isNewerThanOrEquals(ServerVersion.V_26_1)) {
               Map var4 = this.Xqedii_ly8judj.computeIfAbsent(var1.getUniqueId(), var0 -> {
                  if ((-1903291648 * -1903291648 - -1903291648 & 1) != 0) {
                     throw new IllegalStateException();
                  } else {
                     return new ConcurrentHashMap<>();
                  }
               });

               for (Entry var6 : (Iterable<Entry>) (var3.entrySet())) {

                  if (var6.getKey() != null && var6.getValue() != null) {
                     var4.put((WorldClock)var6.getKey(), new ClockNetworkSnapshot((ClockNetworkState)var6.getValue(), var2.getWorldAge()));
                  }
               }
            }
         }
      }
   }

   public void forgetServerClockStates(Player var1) {
      if ((673122078 * 673122078 - 673122078 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         if (var1 != null) {
            this.Xqedii_ly8judj.remove(var1.getUniqueId());
         }
      }
   }

   public void Xqedii_ma0sujh(Player var1) {
      if (((998135923 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.isOnline()) {
         UUID var2 = var1.getUniqueId();
         if (this.Xqedii_3d5wwja.containsKey(var2)) {
            Xqedii_2hd31nw var3 = this.Xqedii_1ia3sp0(var1);
            Xqedii_2hd31nw var4 = this.Xqedii_1elzwyo.get(var2);
            if (!var3.equals(var4)) {
               this.Xqedii_1m6degf(var1);
               this.Xqedii_1elzwyo.put(var2, var3);
               World var5 = var1.getWorld();

               for (int var6 = Xqedii_7q5l4nt.Xqedii_ks3nc41dsn6q(1169076620, 9); var6 < Xqedii_7q5l4nt.Xqedii_fgx4u9p369dg(-1612701822, 14); var6++) {
                  var1.sendBlockChange(new Location(var5, var3.Xqedii_3u2rq5h, var3.Xqedii_1kayom0 + var6, var3.Xqedii_1t1x42s), Xqedii_b6eiiwd);
               }

               this.Xqedii_3o9p0ox(var1);
            }
         }
      }
   }

   public void Xqedii_1m6degf(Player var1) {
      if ((1157065707 * 1157065707 - 1157065707 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         Xqedii_2hd31nw var2 = this.Xqedii_1elzwyo.remove(var1.getUniqueId());
         if (var2 != null && var1.isOnline()) {
            World var3 = var1.getWorld();

            for (int var4 = Xqedii_7q5l4nt.Xqedii_mhrnam3yf82v(-425219317, 4); var4 < Xqedii_7q5l4nt.Xqedii_mhrnam3yf82v(1499411773, 23); var4++) {
               int var5 = var2.Xqedii_1kayom0 + var4;
               var1.sendBlockChange(
                  new Location(var3, var2.Xqedii_3u2rq5h, var5, var2.Xqedii_1t1x42s),
                  var3.getBlockAt(var2.Xqedii_3u2rq5h, var5, var2.Xqedii_1t1x42s).getBlockData()
               );
            }

            this.Xqedii_1v9jfqb(var1);
         }
      }
   }

   public void Xqedii_2dp5ib3(Player var1) {
      if (((-1797072470 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         UUID var2 = var1.getUniqueId();
         int var3 = this.Xqedii_3d5wwja.remove(var2) != null
            ? Xqedii_7q5l4nt.Xqedii_fgx4u9p369dg(-134750041, 20)
            : Xqedii_7q5l4nt.Xqedii_1f197xxzobjv1(1363508861, 13);
         this.Xqedii_34owqls.remove(var2);
         this.Xqedii_2qfq33z.remove(var2);
         this.Xqedii_3hs42n4.remove(var2);
         this.Xqedii_375khsg.remove(var2);
         this.Xqedii_1m6degf(var1);
         if (var3 != 0 && var1.isOnline() && !Xqedii_7ws8hzn.contains(var2)) {
            this.Xqedii_3ut80ql(var1, var1.getWorld().getGameTime());
         }
      }
   }

   public void registerShaderCursorTimeGuard() {
      if ((601677065 * 601677065 + 601677065 & 1) != 0) {
         throw new IllegalStateException();
      } else if (this.Xqedii_ub404mm == null) {
         this.Xqedii_ub404mm = new PacketListenerAbstract(PacketListenerPriority.HIGHEST) {
            public static final String Xqedii_3cv6nup2tlctq = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";

            public void onPacketSend(PacketSendEvent var1) {
               if ((-1530909267 ^ -1530909267) != 0) {
                  throw new IllegalStateException();
               } else if (var1 != null && var1.getPacketType() == Server.TIME_UPDATE) {
                  if (var1.getPlayer() instanceof Player var2) {
                     WrapperPlayServerTimeUpdate var7 = new WrapperPlayServerTimeUpdate(var1);
                     ShaderCursor.this.Xqedii_15whgsi(var2, var7);
                     long var4;
                     if (CameraBase.Xqedii_7ws8hzn.contains(var2.getUniqueId())) {
                        var4 = ShaderCursor.this.Xqedii_1gl4f7n(var2);
                     } else {
                        Integer var6 = ShaderCursor.this.Xqedii_3hs42n4.get(var2.getUniqueId());
                        if (var6 == null) {
                           return;
                        }

                        var4 = ShaderCursor.this.Xqedii_1aj31i1(var2.getUniqueId(), var6);
                     }

                     if (var7.getWorldAge() != var4) {
                        var7.setWorldAge(var4);
                        var1.markForReEncode((Xqedii_zg8ict9.Xqedii_2037mq43e5w8z(1788129729, 12) != 0));
                     }
                  }
               }
            }
         };
         this.Xqedii_pstua9f();

         try {
            PacketEvents.getAPI().getEventManager().registerListener(this.Xqedii_ub404mm);
         } catch (Throwable var2) {
            this.Xqedii_ub404mm = null;
            this.Xqedii_11is6fi.getLogger().warning(Xqedii_35qebravglkzq(String.valueOf(var2)));
         }
      }
   }

   public abstract void Xqedii_pstua9f();

   public abstract void Xqedii_3t21d04();

   public void unregisterShaderCursorTimeGuard() {
      if ((2130883265 ^ 2130883265) != 0) {
         throw new IllegalStateException();
      } else {
         this.Xqedii_3t21d04();
         this.Xqedii_34hj3gy();
         if (this.Xqedii_ub404mm != null) {
            try {
               PacketEvents.getAPI().getEventManager().unregisterListener(this.Xqedii_ub404mm);
            } catch (Throwable var2) {
            }

            this.Xqedii_ub404mm = null;
         }
      }
   }

   public void Xqedii_3hb6nik(Player var1, Runnable var2) {
      if ((190318544 ^ 190318544) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         this.Xqedii_34owqls.put(var1.getUniqueId(), var2);
      }
   }

   public void resampleShaderCursor(Player var1) {
      if ((980218481 * 980218481 - 980218481 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.isOnline()) {
         Runnable var2 = this.Xqedii_34owqls.get(var1.getUniqueId());
         if (var2 != null) {
            try {
               var2.run();
            } catch (Throwable var4) {
               this.Xqedii_34owqls.remove(var1.getUniqueId());
            }
         }
      }
   }

   public Location Xqedii_f9unzdz(Player var1) {
      if (((-1430570398 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Location var2 = var1.getLocation();
         if (!this.isShaderCursorActive(var1)) {
            return var2;
         } else {
            Location var3 = this.Xqedii_20i24gg.get(var1.getUniqueId());
            return var3 != null && var3.getWorld() != null
               ? var3.clone()
                  .add(
                     Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_6hobr8b0qtan(-1457120622000431988L, 62)),
                     Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_3te5em37hvhmg(-1659219713646799843L, 34)),
                     Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_6hobr8b0qtan(-7099669105391135855L, 59))
                  )
               : var2;
         }
      }
   }

   public double Xqedii_axq4lm3(Location var1, double var2) {
      if ((1452468472 * (1452468472 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         World var4 = var1.getWorld();
         if (var4 == null) {
            return var1.getY();
         } else {
            int var5 = var1.getBlockX();
            int var6 = var1.getBlockZ();
            double var7 = Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_uoep3qsikdbr(-5390238176095385460L, 43));
            int var9 = (int)Math.floor(var2 + Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_2a18qapwj3f1m(-4383553818893571537L, 0)) + var7);
            int var10 = (int)Math.floor(var2) + Xqedii_7q5l4nt.Xqedii_1f197xxzobjv1(-1363508862, 21);

            for (int var11 = var9; var11 >= var10; var11--) {
               if (this.Xqedii_3qcyck2(var4, var5, var11, var6)) {
                  return var11 + Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_6hobr8b0qtan(-844359868729471045L, 10)) - var7;
               }
            }

            return var1.getY();
         }
      }
   }

   public boolean Xqedii_3qcyck2(World var1, int var2, int var3, int var4) {
      if ((-595758846 * -595758846 + -595758846 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         for (int var5 = var3; var5 < var3 + Xqedii_7q5l4nt.Xqedii_ks3nc41dsn6q(1168945548, 16); var5++) {
            if (var5 < var1.getMinHeight() || var5 >= var1.getMaxHeight()) {
               return (Xqedii_7q5l4nt.Xqedii_ks3nc41dsn6q(1169076620, 1) != 0);
            }

            if (!var1.getBlockAt(var2, var5, var4).isPassable()) {
               return (Xqedii_7q5l4nt.Xqedii_mhrnam3yf82v(-1100318032, 0) != 0);
            }
         }

         return (Xqedii_7q5l4nt.Xqedii_1f197xxzobjv1(-1363508862, 20) != 0);
      }
   }

   public Xqedii_2hd31nw Xqedii_1ia3sp0(Player var1) {
      if ((-34294611 * -34294611 + -34294611 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Location var2 = this.Xqedii_20rrzrv(var1);
         return new Xqedii_2hd31nw(
            (int)Math.floor(var2.getX()),
            (int)Math.floor(var2.getY() - Double.longBitsToDouble(Xqedii_7q5l4nt.Xqedii_3te5em37hvhmg(7728260547676017112L, 4))),
            (int)Math.floor(var2.getZ())
         );
      }
   }

   public Location Xqedii_20rrzrv(Player var1) {
      if ((-522166649 * (-522166649 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return var1.getEyeLocation();
      }
   }

   private static /* synthetic */ String Xqedii_3ags7cvzryq24(String string, String string2) {
        return Xqedii_7q5l4nt.Xqedii_jm156fuutc39("T80fkTIt8Fds0kehSIhxy2x51PZqNgkVMl57V7KvP6aMtqRo1j5PnEXWhz0VkCef6UAxTmHQ2+vCYK2J6TInGW+FQlXlBZX/A47Al5mGi6ug9o2CNiKbq7HtNce+pBwK3smUuT8Z5HssuzzdyR0=") + string + Xqedii_7q5l4nt.Xqedii_jm156fuutc39("Fyl/WeAcpvvcJl28U+J4AY4FTtQQQ4PpnIV9whW+poPZQ88zghZO2MFm82KoSC2FNuyPhpXB1rPjYHNx8h3EXxg9zgHwT5deYmIK2D2wD52xFktlQeTg1bDYXQDbN53C9QIH4CkyabTil5HDggw7HSD4who=") + string2 + Xqedii_7q5l4nt.Xqedii_jm156fuutc39("u6h930qgyKptm+lzLhDrRw4V4dTtKZILS3OKSv5E");
    }

    private static /* synthetic */ String Xqedii_35qebravglkzq(String string) {
        return Xqedii_7q5l4nt.Xqedii_jm156fuutc39("bUq8QaWaCavqSKcmHHGgyqFBBy8T9qLRritYoqT+MsoN+01wF8gIirSffWA+4zTWq1X2YYfyqiqnY3bA3rRxcf7+o9tZY2zyPeAhHT5n/jkWliDabw1LMhsIhpSyNhiMEOGjBLW2QI46P1SBjzWXGv7pAoKIOCldBCEOMjW14gzEE7FQrv/pqo4WSxms5Vni28fOOMOdpVyWNTAq5AoZdeKe6DaEHe0YjOuC7bblmgzh18fO") + string + Xqedii_7q5l4nt.Xqedii_jm156fuutc39("u6h930qgyKptm+lzLhDrRw4V4dTtKZILS3OKSv5E");
    }
}
