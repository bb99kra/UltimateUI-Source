package dev.xqedii.ultimateUI.service.hud;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.Xqedii_2t8d8j4;
import dev.xqedii.ultimateUI.Xqedii_3ennv92;
import dev.xqedii.ultimateUI.PlayerElementId;
import dev.xqedii.ultimateUI.service.bossbar.BossbarRenderService;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerCommon;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Display.Brightness;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.bukkit.util.Vector;

public abstract class HudBase {
   public static final String Xqedii_2zp96xftlu1ys = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";
   public static final String Xqedii_3j8p5ia = Xqedii_3ennv92.Xqedii_jfst5gaiayhp("uLpsz9eDl85URBdHvklNkmgF6DmbNfScHYt0eHpAVZOblk5s");
   public static final String Xqedii_23f62zr = Xqedii_3ennv92.Xqedii_jfst5gaiayhp("6H1YU5Iaq6JfESDd07hZOF9H4k039VWUWsBWfVusrjeNmmC1ixaKp6N5tTUlvg==");
   public static final String Xqedii_3asppgu;
   public static final String Xqedii_3nu2pkf = Xqedii_3ennv92.Xqedii_jfst5gaiayhp(
      "o9OoAK5LE2xIHGafiqsvVLJ3o9sxzuDxwG0aD34p2rC6yDTliCr+u/gZshDphxbKXZcmADvlfuI="
   );
   public static final String Xqedii_2xrpv1a = Xqedii_3ennv92.Xqedii_jfst5gaiayhp(
      "9/Yp5BKpaddtZPm2qVBuJekjFGK70e3dsP35vt6mTLnT4kp/zfKe9HXQ2oydGOsr+stW4H36KYPtM+Vh"
   );
   public static final String Xqedii_rejax5d = Xqedii_3ennv92.Xqedii_jfst5gaiayhp(
      "KDX66FHKK5Yw6dSzVEgj54L4chz6G3dYma9nGJ9MSL+jm/SVppWAo8rYFoavtXvn8Fl0tT0y8/Lf29N1"
   );
   public static final String Xqedii_kkv73m3 = Xqedii_3ennv92.Xqedii_jfst5gaiayhp(
      "1Xmpr9Uar08PurWCsLkJlcG18GQ1UZIw0ORYuNkt2wfESG1HUFT2btCwAxT2Njj0AqeLgPmTZOxsCVV1BaM="
   );
   public static final String UUI_PART_META = Xqedii_3ennv92.Xqedii_jfst5gaiayhp("fcSuWbqPUAMowNJ8MQzXgfCBzn5MQtQNW1KpltG7AJLusQZNgmkMsxFyroQ=");
   public static final String UUI_SKIN_PART_META = Xqedii_3ennv92.Xqedii_jfst5gaiayhp(
      "AkUMbNoI1iVIpAgkukoBH0ytAeWO7YmUidqB1giVbGdEvSnfXpmCY7ym7j5T5XY8DBGjXmUA"
   );
   public static final String UUI_SKIN_DEPTH_META = Xqedii_3ennv92.Xqedii_jfst5gaiayhp(
      "vm31YI1VxNjgyq03F6Vis/Qes10xMf6e/ADL3ineOLdIvDRTIhR9EaC6NPFTQhIJs2WhFijBZMw="
   );
   public static final double Xqedii_3b5vqez = 8.0;
   public static final MiniMessage Xqedii_174tdpp;
   public static final GsonComponentSerializer Xqedii_3a0pbug;
   public static final Pattern Xqedii_1e8ic6a;
   public static final int Xqedii_30o2xwu = 8;
   public static final int Xqedii_22e6x4w = 9;
   public static final int Xqedii_38cxc4d = 10;
   public static final int Xqedii_1an780g = 11;
   public static final int Xqedii_maazajm = 12;
   public static final int Xqedii_6wb50c6 = 13;
   public static final int Xqedii_3cz12ue = 14;
   public static final int Xqedii_9fsvban = 15;
   public static final int Xqedii_1jdiahd = 16;
   public static final int Xqedii_3qnv3mj = 23;
   public static final int Xqedii_29eqj2u = 24;
   public static final int Xqedii_2uux2di = 25;
   public static final int Xqedii_2hsuo9m = 26;
   public static final int Xqedii_207wtr3 = 27;
   public static final int Xqedii_tt0v86l = 15728880;
   public static final int Xqedii_2qd87hr = 15;
   public static final Brightness Xqedii_2sd94th;
   public static final int Xqedii_2yh6mso = 1;
   public static final int Xqedii_29qiytc = 200;
   public static final int Xqedii_31x68zb = 1073741824;
   public static final int Xqedii_u9rfiss = 0;
   public static final byte Xqedii_1rmwadu = 4;
   public static final byte Xqedii_2oohzms = 8;
   public static final byte Xqedii_lrmqtyg = 16;
   public static final Method Xqedii_21uwrul;
   public static final double Xqedii_61v9i7o = 0.015025041736227046;
   public static final String Xqedii_22fi6ml = Xqedii_3ennv92.Xqedii_jfst5gaiayhp(
      "3ztSidN2Bc6O4RrhaodtERi+aA2gW5JBcAeZXwItRTYO6xq2bydme5XeXTE2EB6jjUCkPj74VXy7FnIHl7FyAybRBMRx/4nmJqqCbeLoi5RcqowI"
   );
   public static final int Xqedii_1i64er2 = 1;
   public static final String Xqedii_6vm709q = Xqedii_3ennv92.Xqedii_jfst5gaiayhp(
      "DhFdZW9Dy5r01+YguAZI2DHfHj0qNUxc6vHtzsvhh9L5QrmA7ZHZsode70fOAfnhif5jnsvp2c7XdIZ/gOpiHacN4RhqvuR5AlC9IH3FHVyBQIXGEzjR25wf3GEHN0nR/ujfMLIQ"
   );
   public static final double Xqedii_xrou9g7 = 1.5;
   public static final String Xqedii_3ce9xgj = Xqedii_3ennv92.Xqedii_jfst5gaiayhp(
      "bdE/6N6Lh2YLSuhFjFe3x7rCbXY58KSxJFU1pw/vsEMXrT5wuLxvSxEye14iX7hi3EpmAblaCTR7ogZK45GCDQcsPvHUsd4HEMviNTtvuA/FYF9dJLtQa1zsxm9b86rUEppgvpSHpjfrmTWPK8JRazDx"
   );
   public static final double Xqedii_38v4js1 = 3.0;
   public static final String Xqedii_259flmf = Xqedii_3ennv92.Xqedii_jfst5gaiayhp(
      "qF7P3Q64+gBp51GfRGDNepVTNSzwDFNxw11UhKJNkQOt8aWPtAPbF3WsbRWqck+8nZVgcQKb2AH9QErR15WfJdVe64HDhCpe5rP7ToGi3yJpryc7hlKTFZhuGKYi+j5v"
   );
   public static final double Xqedii_381912n = 10000.0;
   public static final String Xqedii_7g6pok6 = Xqedii_3ennv92.Xqedii_jfst5gaiayhp(
      "p63zogEK0gQUhytm1V+OG7Z8ZCS+wOfXpelKzYXJBzfbJnpOvop2Slh/fhyV0QAg8RThUqFsLq9D5UngBEHe6QoNgzTa0qtyDdO2Hfmc0TOl293zxs9EVB92r8z2s9n/wKUyMFjOZcU="
   );
   public static final double Xqedii_nn45cy2 = 2.0E-5;
   public static final double Xqedii_109kymm = 6000.0;
   public static final double Xqedii_2bc15ix = 7400.0;
   public static final ThreadLocal<int[]> Xqedii_1fkakfc;
   public static final int Xqedii_qxrwtrj = 4;
   public static final String Xqedii_3r04d8j = Xqedii_3ennv92.Xqedii_jfst5gaiayhp("om+6krM4LEJV8uCZMVODfBTHTrNEUBSikDsD/9sbxY7YMVy76pdzVhfvlhI=");
   public static final String Xqedii_3s6fk67 = Xqedii_3ennv92.Xqedii_jfst5gaiayhp("JL+su8OyW3zh5xjZvKkYodBJgzZaOfQgqLlHjyBTECk+QmYF3YIlNYPyLWXiMJcef1TSfscv");
   public static final String Xqedii_1jd30qv = Xqedii_3ennv92.Xqedii_jfst5gaiayhp(
      "n6m1U0yxKsL+CHjgwuhglcAAT++U7hP/snhU2zFVAMP+dlDJeVNlh4oLhBICx58dMkmDecf9Q/A+FyAj"
   );
   public final UltimateUI Xqedii_rrdyomu;
   public final HudPositionCalculator Xqedii_ou3vyi6 = new HudPositionCalculator();
   public final AtomicInteger Xqedii_31fmop3 = new AtomicInteger(Xqedii_3ennv92.Xqedii_23eqbr36nssuk(-1791678544, 18));
   public final AtomicInteger Xqedii_2pqqofd = new AtomicInteger(Xqedii_3ennv92.Xqedii_23eqbr36nssuk(986383513, 21));
   public final Map<UUID, Map<String, Xqedii_2t8d8j4>> Xqedii_7rxyqib = new ConcurrentHashMap<>();
   public final Map<UUID, PlayerElementId> Xqedii_ffdt8d5 = new HashMap<>();
   public final LinkedHashSet<UUID> Xqedii_2ruo9yk = new LinkedHashSet<>();
   public final Map<UUID, int[]> Xqedii_v8ssh03 = new ConcurrentHashMap<>();
   public final Map<UUID, Entity> Xqedii_3ediaz4 = new HashMap<>();
   public final Map<UUID, Set<Entity>> Xqedii_52xxigy = new ConcurrentHashMap<>();
   public final Map<UUID, Location> Xqedii_2apkuy1 = new ConcurrentHashMap<>();
   public final Map<UUID, Location> Xqedii_141s1c0 = new ConcurrentHashMap<>();
   public final Set<UUID> Xqedii_28kfl6q = ConcurrentHashMap.newKeySet();
   public final Map<UUID, Boolean> Xqedii_1q9cawx = new ConcurrentHashMap<>();
   public final Set<UUID> Xqedii_ukv6r1x = ConcurrentHashMap.newKeySet();
   public final Map<UUID, Set<Entity>> Xqedii_2e9emiq = new ConcurrentHashMap<>();
   public PacketListenerCommon Xqedii_3rko2lp;
   public BossbarRenderService Xqedii_tfaowu4;

   public HudBase(UltimateUI var1) {
      this.Xqedii_rrdyomu = var1;
   }

   public abstract Xqedii_2t8d8j4 Xqedii_18xpusv(Player var1, String var2);

   public abstract void Xqedii_32sczwk(Player var1, String var2, boolean var3);

   public abstract void Xqedii_32cmird(Player var1, Xqedii_2t8d8j4 var2);

   public abstract void Xqedii_3m5yvig(Player var1, Xqedii_2t8d8j4 var2);

   public abstract void Xqedii_1ofz50z(Player var1, Xqedii_2t8d8j4 var2);

   public abstract void Xqedii_2ebi45u(Player var1);

   public abstract void animScale(Entity var1, double var2, double var4, double var6, boolean var8);

   public abstract boolean Xqedii_3jt8515(Entity var1);

   public abstract int Xqedii_3u778qc(int var1, boolean var2);

   public abstract void moveHud(Entity var1, Vector var2, int var3);

   public abstract void moveHud(Entity var1, Vector var2, int var3, int var4);

   public abstract void moveHud(Entity var1, Vector var2, int var3, int var4, boolean var5);

   public void attachBossbarRenderService(BossbarRenderService var1) {
      if ((1979080136 * 1979080136 + 1979080136 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         this.Xqedii_tfaowu4 = var1;
      }
   }

   public BossbarRenderService bossbarRenderService() {
      if ((-245777204 * (-245777204 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_tfaowu4;
      }
   }

   public boolean isBossbarSession(Player var1) {
      if (((1169121678 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return ((var1 != null && this.Xqedii_ukv6r1x.contains(var1.getUniqueId())
            ? Xqedii_3ennv92.Xqedii_6x3zcrub9zkr(-1649507338, 6)
            : Xqedii_3ennv92.Xqedii_23eqbr36nssuk(-986383514, 28)) != 0);
      }
   }

   public boolean Xqedii_1c8js4j(Player var1, Xqedii_2t8d8j4 var2) {
      if (((-444714565 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return ((var2 != null && !var2.Xqedii_1sln62g && this.isBossbarSession(var1)
            ? Xqedii_3ennv92.Xqedii_6x3zcrub9zkr(1560800571, 15)
            : Xqedii_3ennv92.Xqedii_fdl17q4p0w7i(-1792113477, 0)) != 0);
      }
   }

   public boolean beginBossbarSession(Player var1) {
      if ((-61831108 * -61831108 - -61831108 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.beginBossbarSession(var1, Xqedii_3ennv92.Xqedii_23eqbr36nssuk(-986383514, 12));
      }
   }

   public boolean beginBossbarSession(Player var1, int var2) {
      if (((-2131146309 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && this.Xqedii_tfaowu4 != null && this.Xqedii_tfaowu4.start(var1, var2)) {
         if (this.Xqedii_ukv6r1x.add(var1.getUniqueId())) {
            Map var3 = this.Xqedii_7rxyqib.get(var1.getUniqueId());
            if (var3 != null) {
               for (Xqedii_2t8d8j4 var5 : (Iterable<Xqedii_2t8d8j4>) (var3.values())) {

                  var5.Xqedii_1sln62g = (Xqedii_3ennv92.Xqedii_23eqbr36nssuk(-986383514, 3) != 0);
                  this.Xqedii_1ofz50z(var1, var5);
               }
            }
         }

         this.Xqedii_335nuvf(var1);
         return (Xqedii_3ennv92.Xqedii_9aky99x6vx8g(1607744779, 1) != 0);
      } else {
         return (Xqedii_3ennv92.Xqedii_6x3zcrub9zkr(-1367099747, 14) != 0);
      }
   }

   public void endBossbarSession(Player var1) {
      if (((1948612860 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         boolean var2 = this.Xqedii_ukv6r1x.remove(var1.getUniqueId());
         if (this.Xqedii_tfaowu4 != null) {
            this.Xqedii_tfaowu4.stop(var1);
         }

         if (var2) {
            this.Xqedii_2n9hlqi(var1);
         }
      }
   }

   public boolean applyBossbarRendering(Player var1, boolean var2, int var3) {
      if (((-1418657734 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var2) {
         return this.beginBossbarSession(var1, var3);
      } else {
         this.endBossbarSession(var1);
         return (Xqedii_3ennv92.Xqedii_23eqbr36nssuk(-986383514, 18) != 0);
      }
   }

   public int bossbarUpdateTicks(Player var1) {
      if ((-1818535170 ^ -1818535170) != 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_tfaowu4 == null ? Xqedii_3ennv92.Xqedii_9aky99x6vx8g(1475678018, 31) : this.Xqedii_tfaowu4.updateInterval(var1);
      }
   }

   public boolean bossbarSessionMatches(Player var1, boolean var2) {
      if (((2069266214 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         int var3 = var2 && this.Xqedii_tfaowu4 != null && this.Xqedii_tfaowu4.supports(var1)
            ? Xqedii_3ennv92.Xqedii_23eqbr36nssuk(-988480666, 21)
            : Xqedii_3ennv92.Xqedii_9aky99x6vx8g(737839009, 30);
         return ((this.isBossbarSession(var1) == (var3 != 0)
            ? Xqedii_3ennv92.Xqedii_23eqbr36nssuk(-986383578, 6)
            : Xqedii_3ennv92.Xqedii_23eqbr36nssuk(-986383514, 29)) != 0);
      }
   }

   public void Xqedii_335nuvf(Player var1) {
      if ((4955721 ^ 4955721) != 0) {
         throw new IllegalStateException();
      } else {
         if (this.Xqedii_tfaowu4 != null && this.isBossbarSession(var1)) {
            this.Xqedii_tfaowu4.markDirty(var1);
         }
      }
   }

   public void Xqedii_2n9hlqi(Player var1) {
      if ((1607459596 ^ 1607459596) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         Map var2 = this.Xqedii_7rxyqib.get(var1.getUniqueId());
         if (var2 != null && !var2.isEmpty()) {
            int var3 = Xqedii_3ennv92.Xqedii_23eqbr36nssuk(-986383514, 13);

            for (Xqedii_2t8d8j4 var5 : (Iterable<Xqedii_2t8d8j4>) (var2.values())) {

               var5.Xqedii_1sln62g = (Xqedii_3ennv92.Xqedii_9aky99x6vx8g(-795476669, 21) != 0);
               if (!var5.Xqedii_1co5qbi) {
                  this.Xqedii_3m5yvig(var1, var5);
                  var3 = Xqedii_3ennv92.Xqedii_6x3zcrub9zkr(-622313623, 10);
               }
            }

            if (var3 != 0) {
               this.Xqedii_2ebi45u(var1);
            }
         }
      }
   }

   public void Xqedii_c9jj34a(Player var1, Entity var2) {
      if ((-1130508644 * -1130508644 + -1130508644 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         this.Xqedii_2e9emiq.computeIfAbsent(var1.getUniqueId(), var0 -> {
            if (((2060067281 << 1 | 1) & 1) == 0) {
               throw new IllegalStateException();
            } else {
               return ConcurrentHashMap.newKeySet();
            }
         }).add(var2);
         this.Xqedii_335nuvf(var1);
      }
   }

   public void Xqedii_3ijxsfp(Player var1, Entity var2) {
      if (((-149870343 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         Set var3 = this.Xqedii_2e9emiq.get(var1.getUniqueId());
         if (var3 != null && var3.remove(var2)) {
            this.Xqedii_335nuvf(var1);
         }
      }
   }

   public int Xqedii_3clos9k(int var1) {
      if ((-375400576 * (-375400576 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return Math.max(Xqedii_3ennv92.Xqedii_9aky99x6vx8g(1607744771, 1), Math.min(Xqedii_3ennv92.Xqedii_6x3zcrub9zkr(-1259506149, 1), var1));
      }
   }

   public TextAlignment Xqedii_3r2d8xo(TextAlignment var1) {
      if ((913033420 * 913033420 + 913033420 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return var1 == null ? TextAlignment.CENTER : var1;
      }
   }

   public static Method Xqedii_f9wyey3() {
      if ((1197489293 * 1197489293 - 1197489293 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         try {
            String var10001 = Xqedii_3ennv92.Xqedii_jfst5gaiayhp("7A3esSccKEMAtaD43yuWudg5uJfg7dhe0M53ACqhya5ARdPhK+qEjNfcZMkqJWEL9DkejPdF2DDkg+daoK2aSBU8");
            Class[] var10002 = new Class[Xqedii_3ennv92.Xqedii_23eqbr36nssuk(-986383513, 0)];
            var10002[Xqedii_3ennv92.Xqedii_9aky99x6vx8g(-367098705, 8)] = int.class;
            return Display.class.getMethod(var10001, var10002);
         } catch (Throwable var1) {
            return null;
         }
      }
   }

   static {
      if ((-433445507 ^ -433445507) != 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_3asppgu = Xqedii_1765bvcl8iygb(
            Xqedii_3ennv92.Xqedii_jfst5gaiayhp("Kub41knjofwxBc/Vt6/3HaTP17Fyz5wE7rxFGNjc").repeat(Xqedii_3ennv92.Xqedii_fdl17q4p0w7i(1792114044, 3))
         );
         Xqedii_174tdpp = MiniMessage.miniMessage();
         Xqedii_3a0pbug = GsonComponentSerializer.gson();
         Xqedii_1e8ic6a = Pattern.compile(
            Xqedii_3ennv92.Xqedii_jfst5gaiayhp(
               "xgXX1OMGyV373NerO0sr5233LGSrNipZfc9GIl71Qgq1Pap7CLssGd1fdIBZw0iLBYIQ6rzIk4pzXD0N01WBdWsCauXFOHHUd98WZ9qGNW+kgkTiT/qiE6+D+zxKNMGGw2lbbi/2qwvQoWmr2BxLkkcew2g="
            ),
            Xqedii_3ennv92.Xqedii_9aky99x6vx8g(-1778564140, 29)
         );
         Xqedii_2sd94th = new Brightness(Xqedii_3ennv92.Xqedii_9aky99x6vx8g(392156221, 25), Xqedii_3ennv92.Xqedii_23eqbr36nssuk(-986396826, 10));
         Xqedii_21uwrul = Xqedii_f9wyey3();
         Xqedii_1fkakfc = ThreadLocal.withInitial(() -> {
            if ((210206251 * (210206251 + 1) & 1) != 0) {
               throw new IllegalStateException();
            } else {
               return new int[Xqedii_3ennv92.Xqedii_9aky99x6vx8g(-1079477738, 2)];
            }
         });
      }
   }

   private static /* synthetic */ String Xqedii_1765bvcl8iygb(String string) {
        return string + Xqedii_3ennv92.Xqedii_jfst5gaiayhp("PR5sc4ZIqKigrgm3YmvpKXiqBXGNc2t4DbIV8rag");
    }
}
