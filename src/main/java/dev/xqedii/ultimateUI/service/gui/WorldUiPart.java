package dev.xqedii.ultimateUI.service.gui;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.HudFollowMode;
import dev.xqedii.ultimateUI.Xqedii_1a2mt0h;
import dev.xqedii.ultimateUI.Xqedii_1avxacb;
import dev.xqedii.ultimateUI.AnvilInputState;
import dev.xqedii.ultimateUI.Xqedii_2f78jo3;
import dev.xqedii.ultimateUI.Xqedii_2pbx7s8;
import dev.xqedii.ultimateUI.Xqedii_38o2zes;
import dev.xqedii.ultimateUI.Xqedii_3bglnh9;
import dev.xqedii.ultimateUI.HoverEffectTarget;
import dev.xqedii.ultimateUI.Xqedii_3ep50li;
import dev.xqedii.ultimateUI.ElementBounds;
import dev.xqedii.ultimateUI.Xqedii_3ihqgc8;
import dev.xqedii.ultimateUI.Xqedii_3j2w0l1;
import dev.xqedii.ultimateUI.Xqedii_3kjd61w;
import dev.xqedii.ultimateUI.Xqedii_fg9gmp9;
import dev.xqedii.ultimateUI.Xqedii_irwktj3;
import dev.xqedii.ultimateUI.Xqedii_sq3891d;
import dev.xqedii.ultimateUI.ParsedGuiLayout;
import dev.xqedii.ultimateUI.WorldUiViewerEntity;
import dev.xqedii.ultimateUI.api.event.UltimateUIInputSubmitEvent;
import dev.xqedii.ultimateUI.gui.parser.ScrollViewState;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.editor.shell.managers.EditorTimelineKeyframePart;
import dev.xqedii.ultimateUI.service.hud.HudPositionCalculator;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.service.hud.WorldDisplays;
import dev.xqedii.ultimateUI.service.input.AnvilInputRequest;
import dev.xqedii.ultimateUI.service.input.InputCloseReason;
import dev.xqedii.ultimateUI.service.input.InputFilter;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.PacketEvents;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerCommon;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketReceiveEvent;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType.Play.Client;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import java.io.File;
import java.io.IOException;
import java.lang.runtime.ObjectMethods;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public abstract class WorldUiPart extends ApiElements {
   public static final String Xqedii_32dg4lqhg2bjp = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";
   public static final String WORLD_UI_FOLDER = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
      "lKF8IvNgWpukOwjuLXxCurDTrJdafOpQ/5zojupj8oqS08LWn3GIpk4FsENSUD0V4CIZ/dj8U4k="
   );
   public static final int WORLD_UI_BILLBOARD_FIXED = 0;
   public static final int WORLD_UI_BILLBOARD_VERTICAL = 1;
   public static final int WORLD_UI_BILLBOARD_CENTER = 3;
   public static final double WORLD_UI_MIN_WIDTH = 0.1;
   public static final double WORLD_UI_MAX_WIDTH = 64.0;
   public static final double WORLD_UI_MIN_INTERACT_DISTANCE = 1.0;
   public static final double WORLD_UI_MAX_INTERACT_DISTANCE = 32.0;
   public static final Pattern Xqedii_2bogdsr;
   public static final Pattern Xqedii_8vj0im2;
   public static final int Xqedii_nddt2zu = 10;
   public static final double Xqedii_27p75gf = 4.0;
   public static final double Xqedii_1rzy0r3 = 32.0;
   public static final double Xqedii_1ydho3c = 6.0;
   public static final double Xqedii_3g3eo1k = 0.002;
   public static final double Xqedii_2nngtu2 = 2.5;
   public static final double Xqedii_2gsjrpb = 6.0;
   public static final double Xqedii_2uozvb2 = 0.1;
   public static final double Xqedii_1kda61b = 0.001;
   public static final double Xqedii_291u77t = 0.005;
   public static final double Xqedii_3e17l9d = 0.05;
   public static final int Xqedii_2hayb0g = 8;
   public static final long Xqedii_3q9fxl4 = 4L;
   public static final double Xqedii_1c9a7bm = 2.0;
   public static final double Xqedii_h3b530z = 0.5;
   public static final int Xqedii_3hmzrjr = 2;
   public static final double Xqedii_1ao1v4d = 3.0;
   public static final int Xqedii_2es07ob = 0;
   public static final int Xqedii_hdss4wo = 1;
   public static final int Xqedii_2anuvq0 = 2;
   public static final int Xqedii_2eh61up = 3;
   public static final int Xqedii_1p8npvb = 4;
   public static final int Xqedii_21gdwqy = 5;
   public static final double Xqedii_7tbbsyi = 0.5;
   public static final String Xqedii_1tf0618 = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("C4nlt8undSMjIpk3Durf05ig3KtSM7PyDAKnoKQCa5zPEA7MGOJlh9I53nyb0ZU5");
   public static final String Xqedii_rjix4ac = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("IPubVn+Ji2/FxC+4LLXwUMwADicuyDf71feqHDfhPWTnI0cXIwOs4g==");
   public static final String Xqedii_sfs7pdt = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("sNlApSpfye4lyQPkA1st6o7qJLIEs7hNO9XKo4+d");
   public static final String Xqedii_1myawys = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("T/9eR5AHiRSIrqdpY5Qhi2YkeryuiDtwwN2VYE+j");
   public static final long Xqedii_1nhlf58 = 10L;
   public static final long Xqedii_bdlf1uq = 3L;
   public static final double Xqedii_h9xmlhq = 4.0E-4;
   public static final double Xqedii_2sxwgo7 = 0.5;
   public static final Quaternionf Xqedii_19xeymz;
   public static final List<String> Xqedii_1p37d2s;
   public final Map<String, Xqedii_irwktj3> Xqedii_1yiv0kc = new ConcurrentHashMap<>();
   public final Map<String, Xqedii_1a2mt0h> Xqedii_35obddf = new ConcurrentHashMap<>();
   public final Map<Integer, WorldUiViewerEntity> Xqedii_28h4g3k = new ConcurrentHashMap<>();
   public final Map<UUID, Long> Xqedii_18wixnx = new ConcurrentHashMap<>();
   public final Map<String, Map<String, String>> Xqedii_1exxxow = new ConcurrentHashMap<>();
   public final Map<UUID, Long> Xqedii_useqsj7 = new ConcurrentHashMap<>();
   public final AtomicLong Xqedii_37xasx8 = new AtomicLong();
   public final Set<String> Xqedii_2q6x6jv = ConcurrentHashMap.newKeySet();
   public final AtomicBoolean Xqedii_22k921f = new AtomicBoolean((Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 6) != 0));
   public final AtomicInteger Xqedii_2af7lfn = new AtomicInteger();
   public volatile boolean Xqedii_2dkhpcj;
   public volatile PacketListenerCommon Xqedii_2030na2;

   public WorldUiPart(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   public int reloadWorldUis() {
      if ((-113392922 * (-113392922 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         this.shutdownWorldUis();
         this.Xqedii_2crb1ww();
         this.Xqedii_263cup1();

         for (Xqedii_irwktj3 var2 : this.Xqedii_1yiv0kc.values()) {
            if (var2.Xqedii_1kh6x7z) {
               this.Xqedii_7pfdz1p(var2);
            }
         }

         this.Xqedii_1d1ndqq();
         return this.Xqedii_1yiv0kc.size();
      }
   }

   public void shutdownWorldUis() {
      if (((-544988424 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         this.Xqedii_25jxs4n();

         for (Xqedii_1a2mt0h var2 : new ArrayList<>(this.Xqedii_35obddf.values())) {
            this.Xqedii_gtw7cir(var2);
         }

         this.Xqedii_35obddf.clear();
         this.Xqedii_28h4g3k.clear();
         this.Xqedii_18wixnx.clear();
         this.Xqedii_3l6pre1();
      }
   }

   public void handleWorldUiViewerJoin(Player var1) {
      if (((2018258550 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         for (Xqedii_irwktj3 var3 : this.Xqedii_1yiv0kc.values()) {
            if (var3.Xqedii_1kh6x7z
               && var3.Xqedii_2gkid3z == HudFollowMode.FOLLOW
               && this.Xqedii_iy1ykel(var3, var1)
               && !this.Xqedii_35obddf.containsKey(this.Xqedii_33ry265(var3.Xqedii_1kjhbri, var1.getUniqueId()))) {
               this.Xqedii_1lvabt1(var3, var1);
            }
         }
      }
   }

   public void handleWorldUiViewerQuit(Player var1) {
      if ((-258728074 * (-258728074 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         UUID var2 = var1.getUniqueId();
         this.Xqedii_18wixnx.remove(var2);
         this.Xqedii_useqsj7.remove(var2);
         String var3 = Xqedii_kbjfeiehyldw(String.valueOf(var2));
         String var4 = Xqedii_3vg9mxna3uewu(String.valueOf(var2));
         this.Xqedii_1exxxow
            .keySet()
            .removeIf(
               var2x -> {
                  if (((-2012441487 << 1 | 1) & 1) == 0) {
                     throw new IllegalStateException();
                  } else {
                     return ((!var2x.endsWith(var3) && !var2x.contains(var4)
                        ? Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 24)
                        : Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2065921222, 25)) != 0);
                  }
               }
            );

         for (Xqedii_1a2mt0h var6 : new ArrayList<>(this.Xqedii_35obddf.values())) {
            if (var2.equals(var6.Xqedii_hrnh6v6)) {
               this.Xqedii_35obddf.remove(var6.Xqedii_m23ejo5);
               this.Xqedii_gtw7cir(var6);
            } else {
               Xqedii_2f78jo3 var7 = var6.Xqedii_2zsng32.remove(var2);
               if (var7 != null) {
                  this.Xqedii_3tuhq9d(var7);
               }
            }
         }
      }
   }

   public void handleWorldUiViewerReset(Player var1) {
      if ((1351366193 * (1351366193 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         UUID var2 = var1.getUniqueId();

         for (Xqedii_1a2mt0h var4 : this.Xqedii_35obddf.values()) {
            Xqedii_2f78jo3 var5 = var4.Xqedii_2zsng32.remove(var2);
            if (var5 != null) {
               this.Xqedii_1gftlib(var1, var5);
            }

            var4.Xqedii_3nvqfsp = (Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 8) != 0);
         }
      }
   }

   public void handleWorldUiResourcePackLoaded(Player var1) {
      if ((-969480922 * -969480922 - -969480922 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && this.plugin != null && this.plugin.isEnabled()) {
         Xqedii_38o2zes.Xqedii_3npx6fi(this.plugin, () -> {
            if ((-1005730193 * -1005730193 - -1005730193 & 1) != 0) {
               throw new IllegalStateException();
            } else {
               this.Xqedii_17pa881(var1);
            }
         }, Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-1827220290733965619L, 48));
      }
   }

   public void Xqedii_17pa881(Player var1) {
      if ((-1457339549 * -1457339549 + -1457339549 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1.isOnline()) {
         UUID var2 = var1.getUniqueId();

         for (Xqedii_1a2mt0h var4 : this.Xqedii_35obddf.values()) {
            Xqedii_2f78jo3 var5 = var4.Xqedii_2zsng32.get(var2);
            if (var5 != null) {
               for (Xqedii_3j2w0l1 var7 : var5.Xqedii_2f37hi3.values()) {
                  this.hudService.updateWorldDisplay(var1, var7.Xqedii_2b6hmqn, null, var7.Xqedii_2445hq8, Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 8));
               }
            }
         }
      }
   }

   public List<String> getWorldUiNames() {
      if ((1963806195 ^ 1963806195) != 0) {
         throw new IllegalStateException();
      } else {
         ArrayList<String> var1 = new ArrayList<>(this.Xqedii_1yiv0kc.keySet());
         var1.sort(WorldUiPart::Xqedii_t8r854m);
         return var1;
      }
   }

   public List<WorldUiPart.WorldUiInfo> listWorldUis() {
      if ((1989442908 ^ 1989442908) != 0) {
         throw new IllegalStateException();
      } else {
         ArrayList var1 = new ArrayList();

         for (String var3 : this.getWorldUiNames()) {
            Xqedii_irwktj3 var4 = this.Xqedii_1yiv0kc.get(var3);
            if (var4 != null) {
               int var5 = Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 19);

               for (Xqedii_1a2mt0h var7 : this.Xqedii_35obddf.values()) {
                  if (var7.Xqedii_2u9ms1f.Xqedii_1kjhbri.equals(var3)) {
                     var5++;
                  }
               }

               Location var8 = var4.Xqedii_1extx8d;
               String var9 = var8 != null && var8.getWorld() != null
                  ? Xqedii_jkzc07b1si4s(var8.getWorld().getName(), var8.getBlockX(), var8.getBlockY(), var8.getBlockZ())
                  : Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("jwa9a1iylnPzBuHmSo1HbGAZeTFjlKK/SRBB5/0w");
               var1.add(
                  new WorldUiPart.WorldUiInfo(
                     var3,
                     var4.Xqedii_122m5nm,
                     var4.Xqedii_2gkid3z.name().toLowerCase(Locale.ROOT),
                     describeWorldUiLookAt(var4.Xqedii_1svt1aq),
                     var4.Xqedii_1kh6x7z,
                     var4.Xqedii_1fdepr5,
                     var4.Xqedii_1xd0aa1,
                     var5,
                     var9
                  )
               );
            }
         }

         return var1;
      }
   }

   public boolean hasWorldUi(String var1) {
      if (((278853099 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return ((this.Xqedii_2mx2qgo(var1) != null
            ? Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-187646704, 26)
            : Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(218878223, 30)) != 0);
      }
   }

   public boolean isWorldUiEnabled(String var1) {
      if (((1141403248 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_irwktj3 var2 = this.Xqedii_2mx2qgo(var1);
         return ((var2 != null && var2.Xqedii_1kh6x7z
            ? Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 20)
            : Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 7)) != 0);
      }
   }

   public boolean isWorldUiStatic(String var1) {
      if ((-1559639198 * -1559639198 - -1559639198 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_irwktj3 var2 = this.Xqedii_2mx2qgo(var1);
         return ((var2 != null && var2.Xqedii_2gkid3z == HudFollowMode.STATIC
            ? Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2015589574, 24)
            : Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 4)) != 0);
      }
   }

   public boolean isWorldUiInteractive(String var1) {
      if (((784429128 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_irwktj3 var2 = this.Xqedii_2mx2qgo(var1);
         return ((var2 != null && var2.Xqedii_1fdepr5
            ? Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(607554525, 15)
            : Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 7)) != 0);
      }
   }

   public boolean isWorldUiPerPlayer(String var1) {
      if ((1705251040 * 1705251040 - 1705251040 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_irwktj3 var2 = this.Xqedii_2mx2qgo(var1);
         return ((var2 != null && var2.Xqedii_1xd0aa1
            ? Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-787526389, 14)
            : Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(505026466, 23)) != 0);
      }
   }

   public boolean hasWorldUiLocation(String var1) {
      if ((1953606659 * (1953606659 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_irwktj3 var2 = this.Xqedii_2mx2qgo(var1);
         return ((var2 != null && var2.Xqedii_1extx8d != null && var2.Xqedii_1extx8d.getWorld() != null
            ? Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-187646704, 26)
            : Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 15)) != 0);
      }
   }

   public Location getWorldUiLocation(String var1) {
      if ((-908893360 ^ -908893360) != 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_irwktj3 var2 = this.Xqedii_2mx2qgo(var1);
         return var2 != null && var2.Xqedii_1extx8d != null ? var2.Xqedii_1extx8d.clone() : null;
      }
   }

   public int getWorldUiLookAt(String var1) {
      if (((-709899660 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_irwktj3 var2 = this.Xqedii_2mx2qgo(var1);
         return var2 == null ? Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(284217533, 18) : var2.Xqedii_1svt1aq;
      }
   }

   public double getWorldUiWidth(String var1) {
      if (((625504991 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_irwktj3 var2 = this.Xqedii_2mx2qgo(var1);
         return var2 == null ? Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673804246537L, 12)) : var2.Xqedii_2llkhpy;
      }
   }

   public static boolean isValidWorldUiId(String var0) {
      if ((-1788702080 ^ -1788702080) != 0) {
         throw new IllegalStateException();
      } else {
         return ((var0 != null && Xqedii_2bogdsr.matcher(var0).matches()
            ? Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1751025786, 1)
            : Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 20)) != 0);
      }
   }

   public static int parseWorldUiLookAt(String var0) {
      if ((1397900581 ^ 1397900581) != 0) {
         throw new IllegalStateException();
      } else {
         String var1 = var0 == null ? Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nDGc1imw9eT8I5Ax9Ff6HdZ2P+CrPrIa9F/Iog==") : var0.trim().toLowerCase(Locale.ROOT);
         int var3 = Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1130413523, 19);
         switch (var1.hashCode()) {
            case -1984141450:
               if (var1.equals(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("5naXiZs12p/Y0o15uSf3eMvb7Okk34IY0dGdnwB8q12nV/avZsSCMQDX/nE="))) {
                  var3 = Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2023978182, 22);
               }
               break;
            case -1364013995:
               if (var1.equals(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("SuU9ZJsiBXDXdaH9uik1DsQCHh808Z02toBgfU2cmL4HGoazEkkeuw=="))) {
                  var3 = Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(530192290, 23);
               }
               break;
            case -985752863:
               if (var1.equals(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("u7+HrX33Vy1GkFfkbEGnAfXQMdPsb8DFKczea+BzKL2y4hAFvndxcQ=="))) {
                  var3 = Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(2074379905, 4);
               }
               break;
            case 3551:
               if (var1.equals(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("id7rXXDuBpY7l1olSMLf8aI/+T07zcb0/0WFcAEAV4I="))) {
                  var3 = Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1010052932, 24);
               }
               break;
            case 119407:
               if (var1.equals(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("VLoaO5MO0OxA312i3lQ2fGecnMtggolTnBeo6xNOilESnA=="))) {
                  var3 = Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032202950, 15);
               }
               break;
            case 119527:
               if (var1.equals(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("HefHJmV63JtYTNt2AKjiHoqGwYaaosiTyF3c37TRzkTfcg=="))) {
                  var3 = Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(230881097, 21);
               }
               break;
            case 3154575:
               if (var1.equals(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("ZO7Y4mRYa4q6bxuR3Nva+FI4gUgLIzxy+AcVDzjMy5m9hZ0E"))) {
                  var3 = Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-2004719522, 17);
               }
               break;
            case 3569038:
               if (var1.equals(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("5kWpgFxzuntncNnqXalJ6haV5/pB8GJWdv9C7O8xMW625YI5"))) {
                  var3 = Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 14);
               }
               break;
            case 109432316:
               if (var1.equals(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("S0JotsHUXry4OO85+2p2iLpy8IY/NTwtEHXLhl1EQpBYSwjgnO0="))) {
                  var3 = Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366662, 4);
               }
         }
         return switch (var3) {
            case 0, 1, 2, 3, 4, 5 -> Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-292414523, 6);
            case 6, 7, 8 -> Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(1763931334, 28);
            default -> Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(396500506, 7);
         };
      }
   }

   public static String describeWorldUiLookAt(int var0) {
      if ((1946542960 ^ 1946542960) != 0) {
         throw new IllegalStateException();
      } else if (var0 == Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-487110387, 29)) {
         return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("id7rXXDuBpY7l1olSMLf8aI/+T07zcb0/0WFcAEAV4I=");
      } else {
         return var0 == Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 18)
            ? Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("5naXiZs12p/Y0o15uSf3eMvb7Okk34IY0dGdnwB8q12nV/avZsSCMQDX/nE=")
            : Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("enf1ljwI0KR3vu0QRSvqLiZfz2t8YLSLKqLw3dmIh5A6tA==");
      }
   }

   public String nextWorldUiId() {
      if ((-360732752 * (-360732752 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         File var1 = new File(
            this.plugin.getDataFolder(), Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("lKF8IvNgWpukOwjuLXxCurDTrJdafOpQ/5zojupj8oqS08LWn3GIpk4FsENSUD0V4CIZ/dj8U4k=")
         );
         int var2 = Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-375293407, 27);

         while (this.Xqedii_1yiv0kc.containsKey(String.valueOf(var2)) || new File(var1, Xqedii_35y5pw2xucfj9(var2)).exists()) {
            var2++;
         }

         return String.valueOf(var2);
      }
   }

   public static int Xqedii_t8r854m(String var0, String var1) {
      if ((-1124006019 ^ -1124006019) != 0) {
         throw new IllegalStateException();
      } else {
         boolean var2 = Xqedii_8vj0im2.matcher(var0).matches();
         boolean var3 = Xqedii_8vj0im2.matcher(var1).matches();
         if (var2 && var3) {
            int var4 = Long.compare(Long.parseLong(var0), Long.parseLong(var1));
            return var4 != 0 ? var4 : var0.compareTo(var1);
         } else if (var2 != var3) {
            return var2 ? Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1036724264, 22) : Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 15);
         } else {
            return var0.compareTo(var1);
         }
      }
   }

   public String placeWorldUi(Player var1, String var2, String var3) {
      if ((1761689584 * (1761689584 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && isValidWorldUiId(var3) && !this.Xqedii_1yiv0kc.containsKey(var3)) {
         File var4 = new File(
            this.plugin.getDataFolder(), Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("lKF8IvNgWpukOwjuLXxCurDTrJdafOpQ/5zojupj8oqS08LWn3GIpk4FsENSUD0V4CIZ/dj8U4k=")
         );
         if (!var4.exists() && !var4.mkdirs()) {
            return null;
         } else {
            File var5 = new File(var4, Xqedii_3b551q61k6m7o(var3));
            if (var5.exists()) {
               return null;
            } else {
               YamlConfiguration var6 = new YamlConfiguration();
               var6.options().setHeader(Xqedii_1p37d2s);
               var6.set(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("cqOXpUwitKvVgiI1hY0uAH5wpb9jlMTha6KEoF+Cc0SMSIgK"), var2);
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("+zsCixz35PqN4T7kC7GqVsJKS5I8PYPZDHm/+3ygmzZjb4KNecKZJZPI"),
                  Boolean.valueOf((Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1843706386, 14) != 0))
               );
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("VlqqrTxAcGfEyjaf4Rf5bwjQ8h/3j04KIZfe+f9eowkKILP3"),
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("Lyb8606G4kH99WE/6Z8w1RFpjm7m73+3XHsWDnL248mFkpsD/Au4Rw==")
               );
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("xHASI2oVlqXUWoit5UOz7MYkvKHL/1V8AskOWW4m+1LuKDOcpHqtanLA"),
                  describeWorldUiLookAt(Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 23))
               );
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("jaZ5DIXsKldq96Dtb10TUQI9G7bG2KainQ3YOC/kbN9Vjq94yyhIkq5BW/459sbKkRc="),
                  Boolean.valueOf((Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366788, 1) != 0))
               );
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("no5szW1gQExEyoOcLbZdULMI7tDTAP4bbAPf501lheRxcqFyygbhco0NjU9WIpgS"),
                  Boolean.valueOf((Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-2005243810, 17) != 0))
               );
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("AXKDBN5PfztzV2AfCnCpi0erBdSt6uKZI5Q5HsvNTip9TfPc5pIskoF4xDoPTy9b3scZTIIkDcYYInbIvVI="),
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854204791588153L, 19))
               );
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("g8GtoqXdXB5EO2LPAVxVRCZrSKKIS9Yc8doeqVwZrKAj7WNOoz0="),
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(5874726726367169870L, 1))
               );
               Xqedii_1l03fbc(var6, this.Xqedii_2vwb7zk(var1));
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("rfBohz7G/uTUhvOEK8sPL4p9s7dnxjt2SZuWniWwk1kWaRtD9oaMEcYtA1m70c79"),
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("cilL8wizVUuiIg2BaWu9FDMgaEhb3W3cJBjCdc/DrRSSug==")
               );
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("F7S3Y3UCy8WRkPPNN+rfEQmRic9TDTe+DTmBr1cH7r0DC688D/Apoks/Dsg5Dywe"),
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nDGc1imw9eT8I5Ax9Ff6HdZ2P+CrPrIa9F/Iog==")
               );
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("NwaaLMkiPdtE69zMiZQi/8YYFQ5M2JYu5QBWLKU0AkuI0l2nQUZPHjBCFIxWEmXy2Y1WCiX4"),
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-1300556231443215537L, 47))
               );
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("Wi8Tyz0S9TosTi7UVil+urMRxmiXqgz2PlTtclGfqYFM0A5fu+6SlxLl"),
                  Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-2122603385, 28)
               );
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("FTm7ooEI1ZE9V5nrmKYzTHDBfI1Aj3vyvzdtsz4xnv0xBwyKA9PnqBbYZgCiNsPt"),
                  Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1122947888, 10)
               );
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("/PF8QtmyT85FabKr75xSEWiR2kVjlhVS1tPOTFafP7FaDu22Yi4hxr3fi0ia1Q=="),
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(4310827801796820702L, 25))
               );
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("oBXN7h/KtIX6BdPVytQUZaTOk79Kxb5duCJLCgz9D1I98nhY0mire46GfSTK4A=="),
                  Boolean.valueOf((Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-767334211, 11) != 0))
               );
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("/FxRmkU2za+jjtb2pg66YrjbrT9NYzoYnE6RVAafhgWG0rnmQWaWysv432A9hfD1160RoyvkuD5mMQ=="),
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-2195953370501614722L, 7))
               );
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("wjyHteOmMuJOYlTH7YIzkrsTw2v7YWVNpMCnaPgxmYlX6LJBMDFFVAE/8dlHYkcOY0RNob42"),
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(8898578089475291085L, 1))
               );
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("I0ljkcbbo35LV2s2WLssgaQtntM60nDlPUy6wabcGZiambr/v3u271bK7BbntK0gsTw="),
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854204791454329L, 25))
               );
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("LQMCITEjWEw8Zxe/2+b3OjX0Hs+MgC8Xe6esfZrSqYK+d78+pdQCdj2OBA9KlCXmuqW26bti"),
                  Boolean.valueOf((Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1501173625, 29) != 0))
               );
               var6.set(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("eO2m/O64bfPZhk4fMVTvg5UlMUwVSdsZBNwCxZIib/mh+9EPHJlWYEg8SHx+14cbC/Gw+rND"),
                  Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366822, 3)
               );
               if (!this.Xqedii_1ddg9gf(var6, var5)) {
                  return null;
               } else {
                  Xqedii_irwktj3 var7 = this.Xqedii_2a9oruw(var5);
                  if (var7 == null) {
                     var5.delete();
                     return null;
                  } else {
                     this.Xqedii_1yiv0kc.put(var7.Xqedii_1kjhbri, var7);
                     if (!this.Xqedii_7pfdz1p(var7)) {
                        this.Xqedii_1yiv0kc.remove(var7.Xqedii_1kjhbri);
                        this.Xqedii_20kx0zu(var7.Xqedii_1kjhbri);
                        var5.delete();
                        return null;
                     } else {
                        return var7.Xqedii_1kjhbri;
                     }
                  }
               }
            }
         }
      } else {
         return null;
      }
   }

   public boolean removeWorldUi(String var1) {
      if (((1524360839 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_irwktj3 var2 = this.Xqedii_2mx2qgo(var1);
         if (var2 == null) {
            return (Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1215043514, 16) != 0);
         } else {
            this.Xqedii_20kx0zu(var2.Xqedii_1kjhbri);
            this.Xqedii_1yiv0kc.remove(var2.Xqedii_1kjhbri);
            String var3 = Xqedii_1gmw4bk5uiu0r(var2.Xqedii_1kjhbri);
            String var4 = Xqedii_1q42n2sr2icf9(var2.Xqedii_1kjhbri);
            this.Xqedii_1exxxow
               .keySet()
               .removeIf(
                  var2x -> {
                     if ((573301386 * (573301386 + 1) & 1) != 0) {
                        throw new IllegalStateException();
                     } else {
                        return ((!var2x.startsWith(var3) && !var2x.startsWith(var4)
                           ? Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-2021227032, 21)
                           : Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1122962224, 10)) != 0);
                     }
                  }
               );
            return ((var2.Xqedii_3ukehvr.exists() && !var2.Xqedii_3ukehvr.delete()
               ? Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 18)
               : Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1268848351, 26)) != 0);
         }
      }
   }

   public boolean moveWorldUiHere(Player var1, String var2) {
      if ((-673726836 * -673726836 - -673726836 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(505026466, 23) != 0);
      } else {
         Location var3 = this.Xqedii_2vwb7zk(var1);
         return this.Xqedii_w50v77d(var2, var1x -> {
            if ((640776613 * 640776613 - 640776613 & 1) != 0) {
               throw new IllegalStateException();
            } else {
               Xqedii_1l03fbc(var1x, var3);
            }
         });
      }
   }

   public boolean moveWorldUi(String var1, World var2, double var3, double var5, double var7) {
      if ((-1373979978 * -1373979978 - -1373979978 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_irwktj3 var9 = this.Xqedii_2mx2qgo(var1);
         if (var9 != null && var2 != null) {
            float var10 = var9.Xqedii_1extx8d == null ? Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 5)) : var9.Xqedii_1extx8d.getYaw();
            Location var11 = new Location(var2, var3, var5, var7, var10, Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1225622263, 13)));
            return this.Xqedii_w50v77d(var1, var1x -> {
               if ((565546802 * 565546802 + 565546802 & 1) != 0) {
                  throw new IllegalStateException();
               } else {
                  Xqedii_1l03fbc(var1x, var11);
               }
            });
         } else {
            return (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1136870132, 20) != 0);
         }
      }
   }

   public boolean rotateWorldUi(String var1, double var2) {
      if ((1767916844 ^ 1767916844) != 0) {
         throw new IllegalStateException();
      } else {
         double var4 = Xqedii_1hdxjwv(Xqedii_2ezay7r(var2));
         return this.Xqedii_w50v77d(var1, var2x -> {
            if (((-527630563 << 1 | 1) & 1) == 0) {
               throw new IllegalStateException();
            } else {
               var2x.set(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("0zCM/CFzprpd4JUx+4rNg+ECHl2gCel5OP8sNiAtsZ7qpCu5XsLjuE6370piOm8MlsjBsg=="), var4);
            }
         });
      }
   }

   public boolean setWorldUiLookAt(String var1, int var2) {
      if ((-1562593517 * -1562593517 + -1562593517 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var3 = describeWorldUiLookAt(var2);
         return this.Xqedii_w50v77d(var1, var1x -> {
            if ((-887440607 ^ -887440607) != 0) {
               throw new IllegalStateException();
            } else {
               var1x.set(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("xHASI2oVlqXUWoit5UOz7MYkvKHL/1V8AskOWW4m+1LuKDOcpHqtanLA"), var3);
            }
         });
      }
   }

   public boolean setWorldUiWidth(String var1, double var2) {
      if ((-33434718 ^ -33434718) != 0) {
         throw new IllegalStateException();
      } else {
         double var4 = Xqedii_1hdxjwv(
            Math.max(
               Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-1411186255206577531L, 17)),
               Math.min(Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2370838848871860746L, 56)), var2)
            )
         );
         return this.Xqedii_w50v77d(var1, var2x -> {
            if ((-705413976 * -705413976 + -705413976 & 1) != 0) {
               throw new IllegalStateException();
            } else {
               var2x.set(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("g8GtoqXdXB5EO2LPAVxVRCZrSKKIS9Yc8doeqVwZrKAj7WNOoz0="), var4);
            }
         });
      }
   }

   public boolean setWorldUiMode(String var1, boolean var2) {
      if (((126137252 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_w50v77d(
            var1,
            var1x -> {
               if ((1784151026 * 1784151026 - 1784151026 & 1) != 0) {
                  throw new IllegalStateException();
               } else {
                  var1x.set(
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("VlqqrTxAcGfEyjaf4Rf5bwjQ8h/3j04KIZfe+f9eowkKILP3"),
                     var2
                        ? Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("xrnkkmGDtX3XnXuQpGHE4FlXP5vd6KjRUeVd9DxcXuohHBAG+HC91Q==")
                        : Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("Lyb8606G4kH99WE/6Z8w1RFpjm7m73+3XHsWDnL248mFkpsD/Au4Rw==")
                  );
                  var1x.set(
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("rfBohz7G/uTUhvOEK8sPL4p9s7dnxjt2SZuWniWwk1kWaRtD9oaMEcYtA1m70c79"),
                     var2
                        ? Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("mo7uhzcypq4iFDDJOFeqxNyqLHCTRvkNZDKgqWhEvd7uJATWH6M=")
                        : Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("cilL8wizVUuiIg2BaWu9FDMgaEhb3W3cJBjCdc/DrRSSug==")
                  );
               }
            }
         );
      }
   }

   public Boolean setWorldUiEnabled(String var1, Boolean var2) {
      if ((-1192955705 * -1192955705 - -1192955705 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_irwktj3 var3 = this.Xqedii_2mx2qgo(var1);
         if (var3 == null) {
            return null;
         } else {
            boolean var4 = var2 == null ? !var3.Xqedii_1kh6x7z : var2.booleanValue();
            return this.Xqedii_w50v77d(var1, var1x -> {
               if (((-325172109 << 1 | 1) & 1) == 0) {
                  throw new IllegalStateException();
               } else {
                  var1x.set(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("+zsCixz35PqN4T7kC7GqVsJKS5I8PYPZDHm/+3ygmzZjb4KNecKZJZPI"), var4);
               }
            }) ? Boolean.valueOf(var4) : null;
         }
      }
   }

   public Boolean setWorldUiInteractive(String var1, Boolean var2) {
      if ((-1210361019 ^ -1210361019) != 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_irwktj3 var3 = this.Xqedii_2mx2qgo(var1);
         if (var3 == null) {
            return null;
         } else {
            boolean var4 = var2 == null ? !var3.Xqedii_1fdepr5 : var2.booleanValue();
            return this.Xqedii_w50v77d(var1, var1x -> {
               if ((-51384860 * -51384860 + -51384860 & 1) != 0) {
                  throw new IllegalStateException();
               } else {
                  var1x.set(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("jaZ5DIXsKldq96Dtb10TUQI9G7bG2KainQ3YOC/kbN9Vjq94yyhIkq5BW/459sbKkRc="), var4);
               }
            }) ? Boolean.valueOf(var4) : null;
         }
      }
   }

   public Boolean setWorldUiPerPlayer(String var1, Boolean var2) {
      if ((-355952606 * -355952606 + -355952606 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_irwktj3 var3 = this.Xqedii_2mx2qgo(var1);
         if (var3 == null) {
            return null;
         } else {
            boolean var4 = var2 == null ? !var3.Xqedii_1xd0aa1 : var2.booleanValue();
            return this.Xqedii_w50v77d(var1, var1x -> {
               if ((1567744502 * 1567744502 + 1567744502 & 1) != 0) {
                  throw new IllegalStateException();
               } else {
                  var1x.set(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("no5szW1gQExEyoOcLbZdULMI7tDTAP4bbAPf501lheRxcqFyygbhco0NjU9WIpgS"), var4);
               }
            }) ? Boolean.valueOf(var4) : null;
         }
      }
   }

   public boolean teleportToWorldUi(Player var1, String var2) {
      if (((271732787 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_irwktj3 var3 = this.Xqedii_2mx2qgo(var2);
         if (var1 != null && var3 != null && var3.Xqedii_1extx8d != null && var3.Xqedii_1extx8d.getWorld() != null) {
            Location var4 = var3.Xqedii_1extx8d;
            double var5 = Math.toRadians(var4.getYaw());
            Location var7 = var4.clone()
               .add(
                  Math.sin(var5) * Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352208861289777674L, 51)),
                  -var1.getEyeHeight(),
                  -Math.cos(var5) * Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2316734080692259338L, 57))
               );
            var7.setYaw(var4.getYaw());
            var7.setPitch(Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 19)));
            return Xqedii_38o2zes.Xqedii_fxq2htx(var1, var7);
         } else {
            return (Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1169658857, 8) != 0);
         }
      }
   }

   public boolean isAimingAtWorldUi(Player var1) {
      if ((663320069 * 663320069 - 663320069 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return ((var1 != null && this.Xqedii_1pwcmxy(var1) != null
            ? Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(284479677, 18)
            : Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 30)) != 0);
      }
   }

   public boolean handleWorldUiSwing(Player var1) {
      if (((-515444460 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_2fzgi4z(var1);
      }
   }

   public boolean handleWorldUiUse(Player var1) {
      if ((-1365000546 ^ -1365000546) != 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_2fzgi4z(var1);
      }
   }

   public boolean handleWorldUiScroll(Player var1, int var2) {
      if ((2038768261 * (2038768261 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != 0) {
         Xqedii_3ep50li var3 = this.Xqedii_1pwcmxy(var1);
         if (var3 == null) {
            return (Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 0) != 0);
         } else {
            Xqedii_1avxacb var4 = this.Xqedii_322y3to(var3.instance(), var3.viewer());
            ScrollViewState.Metrics var5 = this.Xqedii_1yf74gi(var4, var3.pageX(), var3.pageY());
            if (var5 == null) {
               return (Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 3) != 0);
            } else {
               int var6 = !var5.horizontal || var5.vertical && !var1.isSneaking()
                  ? Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1019022269, 28)
                  : Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-383667106, 10);
               double var7 = var6 != 0 ? var2 * var5.stepX : Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673804245514L, 59));
               double var9 = var6 != 0 ? Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854204791454329L, 52)) : var2 * var5.stepY;
               this.Xqedii_35fczng(var3.instance(), var3.viewer(), var4, var5, var7, var9);
               return (Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1225630455, 13) != 0);
            }
         }
      } else {
         return (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(396500506, 7) != 0);
      }
   }

   public boolean Xqedii_w50v77d(String var1, Consumer<YamlConfiguration> var2) {
      if ((-784067789 * (-784067789 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_irwktj3 var3 = this.Xqedii_2mx2qgo(var1);
         if (var3 == null) {
            return (Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-383668130, 10) != 0);
         } else {
            YamlConfiguration var4 = YamlConfiguration.loadConfiguration(var3.Xqedii_3ukehvr);
            var2.accept(var4);
            if (!this.Xqedii_1ddg9gf(var4, var3.Xqedii_3ukehvr)) {
               return (Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 24) != 0);
            } else {
               Xqedii_irwktj3 var5 = this.Xqedii_2a9oruw(var3.Xqedii_3ukehvr);
               if (var5 == null) {
                  return (Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-767336259, 11) != 0);
               } else {
                  this.Xqedii_20kx0zu(var5.Xqedii_1kjhbri);
                  this.Xqedii_1yiv0kc.put(var5.Xqedii_1kjhbri, var5);
                  if (var5.Xqedii_1kh6x7z) {
                     this.Xqedii_7pfdz1p(var5);
                  }

                  return (Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032399558, 15) != 0);
               }
            }
         }
      }
   }

   public boolean Xqedii_7pfdz1p(Xqedii_irwktj3 var1) {
      if (((-222339985 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1.Xqedii_2gkid3z == HudFollowMode.STATIC) {
         return this.Xqedii_40udfqo(var1);
      } else if (this.Xqedii_2enuzo1(var1) == null) {
         return (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1586002024, 9) != 0);
      } else {
         this.Xqedii_1v71dhx(var1);
         this.Xqedii_1d1ndqq();
         return (Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(398084205, 0) != 0);
      }
   }

   public Location Xqedii_2vwb7zk(Player var1) {
      if ((976543808 ^ 976543808) != 0) {
         throw new IllegalStateException();
      } else {
         Location var2 = var1.getEyeLocation();
         Location var3 = this.Xqedii_3hd1poy(var1, var2);
         if (var3 != null) {
            return var3;
         } else {
            float var4 = Xqedii_32ncxhq(var2.getYaw());
            double var5 = Math.toRadians(var4);
            Location var7 = var2.clone()
               .add(
                  -Math.sin(var5) * Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882853548399655545L, 40)),
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-3674539282415232016L, 20)),
                  Math.cos(var5) * Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(5097198828032832825L, 3))
               );
            var7.setYaw(var4);
            var7.setPitch(Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 23)));
            return var7;
         }
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public Location Xqedii_3hd1poy(Player var1, Location var2) {
      if (((1069734448 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         World var3 = var2.getWorld();
         if (var3 == null) {
            return null;
         } else {
            RayTraceResult var4;
            try {
               var4 = var3.rayTraceBlocks(
                  var2,
                  var2.getDirection(),
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(4095176806792843671L, 17)),
                  FluidCollisionMode.NEVER,
                  (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-792915724, 2) != 0)
               );
            } catch (Throwable var9) {
               return null;
            }

            if (var4 != null && var4.getHitBlockFace() != null && var4.getHitPosition() != null) {
               BlockFace var5 = var4.getHitBlockFace();
               float var6;
               switch (dev.xqedii.ultimateUI.Xqedii_31ld99d.Xqedii_18v6dwx[var5.ordinal()]) {
                  case 1:
                     var6 = Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-393771387, 13));
                     break;
                  case 2:
                     var6 = Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(438428603, 11));
                     break;
                  case 3:
                     var6 = Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1994605628, 0));
                     break;
                  case 4:
                     var6 = Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032342428, 15));
                     break;
                  default:
                     return null;
               }

               Vector var7 = var5.getDirection();
               Location var8 = var4.getHitPosition()
                  .toLocation(var3)
                  .add(var7.multiply(Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(1316909730194995514L, 55))));
               var8.setYaw(var6);
               var8.setPitch(Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-292414715, 6)));
               return var8;
            } else {
               return null;
            }
         }
      }
   }

   public static void Xqedii_1l03fbc(YamlConfiguration var0, Location var1) {
      if ((-1161967507 ^ -1161967507) != 0) {
         throw new IllegalStateException();
      } else {
         var0.set(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("SpYYiburzVRUchVAGIFvTQPhiFL7OgzuOHhNESTfXyPkaf340qr27ynzWUqfP0HVMPa5urxzq70="),
            var1.getWorld() == null ? Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nDGc1imw9eT8I5Ax9Ff6HdZ2P+CrPrIa9F/Iog==") : var1.getWorld().getName()
         );
         var0.set(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("6y2oua1dEq6mFlp0i+fcQKoSV0BYBTHXz1NvkQI6wGG+dnu9dWtU2d7YMQRW3mGb"), Xqedii_1hdxjwv(var1.getX()));
         var0.set(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("UvC3kpqNSN+qoLUEeygYKmFcpKKWMSCrp5igxkxft6rBcTvj86niVbZPXv2Agjxk"), Xqedii_1hdxjwv(var1.getY()));
         var0.set(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("VRt9DNawcqC6odwNiE+7N6D0mtkkjGsPG2VliPAkkxkk1evxDrYOFihe/Jm8iCtU"), Xqedii_1hdxjwv(var1.getZ()));
         var0.set(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("0zCM/CFzprpd4JUx+4rNg+ECHl2gCel5OP8sNiAtsZ7qpCu5XsLjuE6370piOm8MlsjBsg=="),
            Xqedii_1hdxjwv(Xqedii_2ezay7r(var1.getYaw()))
         );
         var0.set(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("iIH65VWncWWhQtbjI6Hwwq6NvwYI181LxocU0MqdKSojaZ2WA3GqKf39MTdV2k6+xV3g4LOLrb8="), null);
      }
   }

   @Override
   public void onPageFileInvalidated(File var1) {
      if ((1423938188 * (1423938188 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !this.Xqedii_1yiv0kc.isEmpty() && this.plugin != null && this.plugin.isEnabled()) {
         String var2 = var1.getName().toLowerCase(Locale.ROOT);
         if (var2.endsWith(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nOswLvVAVajHlilsT10l1zSZAuCU962X1wsBOvkJliprc84i"))) {
            this.Xqedii_2q6x6jv
               .add(var2.substring(Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 7), var2.length() - Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2099475654, 24)));
            if (this.Xqedii_22k921f
               .compareAndSet((Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 11) != 0), (Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 18) != 0))) {
               Xqedii_38o2zes.Xqedii_3npx6fi(this.plugin, this::Xqedii_13i5oto, Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-273804394910203146L, 10));
            }
         }
      }
   }

   public void Xqedii_13i5oto() {
      if ((1992264062 * (1992264062 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         this.Xqedii_22k921f.set((Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 12) != 0));
         HashSet var1 = new HashSet<>(this.Xqedii_2q6x6jv);
         this.Xqedii_2q6x6jv.removeAll(var1);
         if (!var1.isEmpty()) {
            for (Xqedii_irwktj3 var3 : this.Xqedii_1yiv0kc.values()) {
               if (var1.contains(Xqedii_2j7jnz4(var3.Xqedii_122m5nm))) {
                  this.Xqedii_31wfccr(var3);
               }
            }
         }
      }
   }

   public void Xqedii_31wfccr(Xqedii_irwktj3 var1) {
      if ((-600417269 * (-600417269 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         var1.Xqedii_2q8vz77 = null;

         for (Xqedii_1a2mt0h var3 : this.Xqedii_35obddf.values()) {
            if (var3.Xqedii_2u9ms1f.Xqedii_1kjhbri.equals(var1.Xqedii_1kjhbri)) {
               var3.Xqedii_2u9ms1f.Xqedii_2q8vz77 = null;
               ParsedGuiLayout var4 = this.Xqedii_2enuzo1(var3.Xqedii_2u9ms1f);
               if (var4 != null) {
                  this.Xqedii_gtw7cir(var3);
                  var3.Xqedii_3uo0d9i = var4;
                  var3.Xqedii_342ikl0.Xqedii_2qgqzmn();
                  var3.Xqedii_3nvqfsp = (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1586002536, 9) != 0);
               }
            }
         }
      }
   }

   public static String Xqedii_2j7jnz4(String var0) {
      if (((-1895325825 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String var1 = var0 == null
            ? Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nDGc1imw9eT8I5Ax9Ff6HdZ2P+CrPrIa9F/Iog==")
            : var0.trim()
               .replace((char)Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1021782057, 18), (char)Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1300965418, 27))
               .toLowerCase(Locale.ROOT);
         if (var1.endsWith(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nOswLvVAVajHlilsT10l1zSZAuCU962X1wsBOvkJliprc84i"))) {
            var1 = var1.substring(Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1110293664, 3), var1.length() - Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1028335657, 22));
         }

         int var2 = var1.lastIndexOf(Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(398084163, 0));
         return var2 >= 0 ? var1.substring(var2 + Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1843706386, 14)) : var1;
      }
   }

   public void Xqedii_2crb1ww() {
      if ((-676558746 * (-676558746 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         File var1 = new File(
            this.plugin.getDataFolder(), Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("lKF8IvNgWpukOwjuLXxCurDTrJdafOpQ/5zojupj8oqS08LWn3GIpk4FsENSUD0V4CIZ/dj8U4k=")
         );
         if (!var1.exists()) {
            var1.mkdirs();
         }
      }
   }

   public void Xqedii_263cup1() {
      if ((-1343935361 * (-1343935361 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         this.Xqedii_1yiv0kc.clear();
         File var1 = new File(
            this.plugin.getDataFolder(), Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("lKF8IvNgWpukOwjuLXxCurDTrJdafOpQ/5zojupj8oqS08LWn3GIpk4FsENSUD0V4CIZ/dj8U4k=")
         );
         File[] var2 = var1.listFiles((var0, var1x) -> {
            if ((1312435380 * 1312435380 + 1312435380 & 1) != 0) {
               throw new IllegalStateException();
            } else {
               return var1x.toLowerCase(Locale.ROOT).endsWith(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nOswLvVAVajHlilsT10l1zSZAuCU962X1wsBOvkJliprc84i"));
            }
         });
         if (var2 != null) {
            File[] var3 = var2;
            int var4 = var2.length;

            for (int var5 = Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1010052932, 24); var5 < var4; var5++) {
               File var6 = var3[var5];
               Xqedii_irwktj3 var7 = this.Xqedii_2a9oruw(var6);
               if (var7 != null) {
                  this.Xqedii_1yiv0kc.put(var7.Xqedii_1kjhbri, var7);
               }
            }
         }
      }
   }

   public Xqedii_irwktj3 Xqedii_2a9oruw(File var1) {
      if ((774029834 * 774029834 + 774029834 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         YamlConfiguration var2 = new YamlConfiguration();

         try {
            var2.load(var1);
         } catch (Exception var7) {
            this.plugin.getLogger().warning(Xqedii_191pzkngazh6p(var1.getName(), var7.getMessage()));
            return null;
         }

         Xqedii_irwktj3 var3 = new Xqedii_irwktj3();
         var3.Xqedii_1kjhbri = Xqedii_3fy72wk(var1.getName());
         var3.Xqedii_3ukehvr = var1;
         String[] var10002 = new String[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366791, 0)];
         var10002[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 11)] = var2.getString(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("cqOXpUwitKvVgiI1hY0uAH5wpb9jlMTha6KEoF+Cc0SMSIgK")
         );
         var3.Xqedii_122m5nm = this.firstNonBlank(var10002).trim();
         var3.Xqedii_1kh6x7z = var2.getBoolean(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("+zsCixz35PqN4T7kC7GqVsJKS5I8PYPZDHm/+3ygmzZjb4KNecKZJZPI"),
            (Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366806, 4) != 0)
         );
         String var10001 = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("xrnkkmGDtX3XnXuQpGHE4FlXP5vd6KjRUeVd9DxcXuohHBAG+HC91Q==");
         String[] var10003 = new String[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 25)];
         var10003[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(398084204, 0)] = var2.getString(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("VlqqrTxAcGfEyjaf4Rf5bwjQ8h/3j04KIZfe+f9eowkKILP3")
         );
         var3.Xqedii_2gkid3z = var10001.equalsIgnoreCase(this.firstNonBlank(var10003).trim()) ? HudFollowMode.FOLLOW : HudFollowMode.STATIC;
         var3.Xqedii_1svt1aq = parseWorldUiLookAt(
            var2.getString(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("xHASI2oVlqXUWoit5UOz7MYkvKHL/1V8AskOWW4m+1LuKDOcpHqtanLA"))
         );
         var3.Xqedii_1fdepr5 = var2.getBoolean(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("jaZ5DIXsKldq96Dtb10TUQI9G7bG2KainQ3YOC/kbN9Vjq94yyhIkq5BW/459sbKkRc="),
            (Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1169658601, 8) != 0)
         );
         var3.Xqedii_1xd0aa1 = var2.getBoolean(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("no5szW1gQExEyoOcLbZdULMI7tDTAP4bbAPf501lheRxcqFyygbhco0NjU9WIpgS"),
            (Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 18) != 0)
         );
         var3.Xqedii_27dvef7 = Math.max(
            Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-3452396297510867188L, 24)),
            Math.min(
               Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673804311306L, 18)),
               var2.getDouble(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("AXKDBN5PfztzV2AfCnCpi0erBdSt6uKZI5Q5HsvNTip9TfPc5pIskoF4xDoPTy9b3scZTIIkDcYYInbIvVI="),
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(-6500596501558723975L, 0))
               )
            )
         );
         var3.Xqedii_2llkhpy = Math.max(
            Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-3486540389624731625L, 26)),
            Math.min(
               Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673736940042L, 28)),
               var2.getDouble(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("g8GtoqXdXB5EO2LPAVxVRCZrSKKIS9Yc8doeqVwZrKAj7WNOoz0="),
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2357279671478127114L, 54))
               )
            )
         );
         var3.Xqedii_15csyqs = var2.getDouble(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("/FxRmkU2za+jjtb2pg66YrjbrT9NYzoYnE6RVAafhgWG0rnmQWaWysv432A9hfD1160RoyvkuD5mMQ=="),
            Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673821026826L, 26))
         );
         var3.Xqedii_2fl6qyh = var2.getDouble(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("wjyHteOmMuJOYlTH7YIzkrsTw2v7YWVNpMCnaPgxmYlX6LJBMDFFVAE/8dlHYkcOY0RNob42"),
            Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-4839983001652867111L, 45))
         );
         var3.Xqedii_2gpxojm = var2.getDouble(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("I0ljkcbbo35LV2s2WLssgaQtntM60nDlPUy6wabcGZiambr/v3u271bK7BbntK0gsTw="),
            Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673804245514L, 23))
         );
         var3.Xqedii_2jpa8ne = Math.max(
            Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 31),
            Math.min(
               Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(253981894, 25),
               var2.getInt(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("eO2m/O64bfPZhk4fMVTvg5UlMUwVSdsZBNwCxZIib/mh+9EPHJlWYEg8SHx+14cbC/Gw+rND"),
                  Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-767344451, 11)
               )
            )
         );
         var3.Xqedii_pyyh81w = var2.getBoolean(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("LQMCITEjWEw8Zxe/2+b3OjX0Hs+MgC8Xe6esfZrSqYK+d78+pdQCdj2OBA9KlCXmuqW26bti"),
            (Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 21) != 0)
         );
         var3.Xqedii_oy26rm2 = Math.max(
            Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882852554987141753L, 41)),
            var2.getDouble(
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("NwaaLMkiPdtE69zMiZQi/8YYFQ5M2JYu5QBWLKU0AkuI0l2nQUZPHjBCFIxWEmXy2Y1WCiX4"),
               Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(5026366744696060537L, 63))
            )
         );
         String[] var8 = new String[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032235718, 16)];
         var8[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 8)] = var2.getString(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("rfBohz7G/uTUhvOEK8sPL4p9s7dnxjt2SZuWniWwk1kWaRtD9oaMEcYtA1m70c79")
         );
         var8[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 26)] = var3.Xqedii_2gkid3z == HudFollowMode.FOLLOW
            ? Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("mo7uhzcypq4iFDDJOFeqxNyqLHCTRvkNZDKgqWhEvd7uJATWH6M=")
            : Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("cilL8wizVUuiIg2BaWu9FDMgaEhb3W3cJBjCdc/DrRSSug==");
         String var4 = this.firstNonBlank(var8).trim();
         var3.Xqedii_3rggyzo = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("mo7uhzcypq4iFDDJOFeqxNyqLHCTRvkNZDKgqWhEvd7uJATWH6M=").equalsIgnoreCase(var4);
         var10002 = new String[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(228783945, 21)];
         var10002[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(906747172, 23)] = var2.getString(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("F7S3Y3UCy8WRkPPNN+rfEQmRic9TDTe+DTmBr1cH7r0DC688D/Apoks/Dsg5Dywe")
         );
         var3.Xqedii_89d5zvi = this.firstNonBlank(var10002).trim();
         var3.Xqedii_3p6634s = Math.max(
            Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 11),
            var2.getInt(
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("Wi8Tyz0S9TosTi7UVil+urMRxmiXqgz2PlTtclGfqYFM0A5fu+6SlxLl"),
               Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-2120123870, 27)
            )
         );
         var3.Xqedii_1b6pbfy = Math.max(
            Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854204791454329L, 61)),
            var2.getDouble(
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("/PF8QtmyT85FabKr75xSEWiR2kVjlhVS1tPOTFafP7FaDu22Yi4hxr3fi0ia1Q=="),
               Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(2619499378196789783L, 3))
            )
         );
         var3.Xqedii_m4m2ywj = Math.max(
            Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(2106271784, 30),
            Math.min(
               Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(607226845, 15),
               var2.getInt(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("FTm7ooEI1ZE9V5nrmKYzTHDBfI1Aj3vyvzdtsz4xnv0xBwyKA9PnqBbYZgCiNsPt"),
                  Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-787297013, 14)
               )
            )
         );
         var3.Xqedii_2tf5gsk = var2.getBoolean(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("oBXN7h/KtIX6BdPVytQUZaTOk79Kxb5duCJLCgz9D1I98nhY0mire46GfSTK4A=="),
            (Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 1) != 0)
         );
         String[] var9 = new String[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 30)];
         var9[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(793001012, 8)] = var2.getString(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("SpYYiburzVRUchVAGIFvTQPhiFL7OgzuOHhNESTfXyPkaf340qr27ynzWUqfP0HVMPa5urxzq70=")
         );
         String var5 = this.firstNonBlank(var9).trim();
         World var6 = var5.isBlank() ? null : Bukkit.getWorld(var5);
         if (var6 != null) {
            var3.Xqedii_1extx8d = new Location(
               var6,
               var2.getDouble(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("6y2oua1dEq6mFlp0i+fcQKoSV0BYBTHXz1NvkQI6wGG+dnu9dWtU2d7YMQRW3mGb")),
               var2.getDouble(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("UvC3kpqNSN+qoLUEeygYKmFcpKKWMSCrp5igxkxft6rBcTvj86niVbZPXv2Agjxk")),
               var2.getDouble(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("VRt9DNawcqC6odwNiE+7N6D0mtkkjGsPG2VliPAkkxkk1evxDrYOFihe/Jm8iCtU")),
               (float)var2.getDouble(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("0zCM/CFzprpd4JUx+4rNg+ECHl2gCel5OP8sNiAtsZ7qpCu5XsLjuE6370piOm8MlsjBsg==")),
               Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 29))
            );
         } else if (!var5.isBlank() && var3.Xqedii_1kh6x7z) {
            this.plugin.getLogger().warning(Xqedii_211xn4bchyhoo(var3.Xqedii_1kjhbri, var5));
         }

         if (var3.Xqedii_122m5nm.isBlank()) {
            this.plugin.getLogger().warning(Xqedii_2t58exsf67b6x(var3.Xqedii_1kjhbri));
         }

         return var3;
      }
   }

   public boolean Xqedii_1ddg9gf(YamlConfiguration var1, File var2) {
      if ((2076257062 ^ 2076257062) != 0) {
         throw new IllegalStateException();
      } else {
         try {
            var1.save(var2);
            return (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(2053660296, 25) != 0);
         } catch (IOException var4) {
            this.plugin.getLogger().warning(Xqedii_1wwobnt4l79jz(var2.getName(), var4.getMessage()));
            return (Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 8) != 0);
         }
      }
   }

   public static String Xqedii_3fy72wk(String var0) {
      if ((362333458 * 362333458 - 362333458 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var1 = var0 == null ? Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nDGc1imw9eT8I5Ax9Ff6HdZ2P+CrPrIa9F/Iog==") : var0.trim().toLowerCase(Locale.ROOT);
         if (var1.endsWith(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nOswLvVAVajHlilsT10l1zSZAuCU962X1wsBOvkJliprc84i"))) {
            var1 = var1.substring(Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 28), var1.length() - Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(796168400, 1));
         }

         return var1;
      }
   }

   public Xqedii_irwktj3 Xqedii_2mx2qgo(String var1) {
      if ((1105880081 ^ 1105880081) != 0) {
         throw new IllegalStateException();
      } else {
         String var2 = Xqedii_3fy72wk(var1);
         return var2.isBlank() ? null : this.Xqedii_1yiv0kc.get(var2);
      }
   }

   public static float Xqedii_32ncxhq(float var0) {
      if ((1375496837 ^ 1375496837) != 0) {
         throw new IllegalStateException();
      } else {
         return (float)Xqedii_2ezay7r(
            Math.round(var0 / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(7418364907446220095L, 39)))
               * Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-1940871612071297836L, 61))
         );
      }
   }

   public static double Xqedii_2ezay7r(double var0) {
      if ((1835357493 ^ 1835357493) != 0) {
         throw new IllegalStateException();
      } else {
         double var2 = var0 % Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(-1508356314618529170L, 5));
         if (var2 >= Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-6198299324952947599L, 26))) {
            var2 -= Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-6207852954833390154L, 8));
         } else if (var2 < Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-6927690172309123050L, 40))) {
            var2 += Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(7386102395647291820L, 19));
         }

         return var2;
      }
   }

   public static double Xqedii_1hdxjwv(double var0) {
      if ((-578346817 * -578346817 - -578346817 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return Math.round(var0 * Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771604525877770L, 38)))
            / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-913223372287688858L, 47));
      }
   }

   public String Xqedii_33ry265(String var1, UUID var2) {
      if ((-1919950599 * -1919950599 - -1919950599 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return Xqedii_1qojfk76l8obh(var1, String.valueOf(var2));
      }
   }

   public void Xqedii_3p8vym0(String var1) {
      if (((-1429211188 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_1a2mt0h var2 = this.Xqedii_35obddf.remove(var1);
         if (var2 != null) {
            this.Xqedii_gtw7cir(var2);
         }
      }
   }

   public int Xqedii_20kx0zu(String var1) {
      if ((-1545495881 * -1545495881 + -1545495881 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         int var2 = Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 3);

         for (Xqedii_1a2mt0h var4 : new ArrayList<>(this.Xqedii_35obddf.values())) {
            if (var4.Xqedii_2u9ms1f.Xqedii_1kjhbri.equals(var1)) {
               this.Xqedii_35obddf.remove(var4.Xqedii_m23ejo5);
               this.Xqedii_gtw7cir(var4);
               var2++;
            }
         }

         return var2;
      }
   }

   public boolean Xqedii_40udfqo(Xqedii_irwktj3 var1) {
      if ((464582249 * 464582249 + 464582249 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1.Xqedii_1extx8d != null && var1.Xqedii_1extx8d.getWorld() != null) {
         Xqedii_1a2mt0h var2 = this.Xqedii_e3ny68s(var1.Xqedii_1kjhbri, var1, null);
         if (var2 == null) {
            return (Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 7) != 0);
         } else {
            var2.Xqedii_2lkymh6 = var1.Xqedii_1extx8d.clone();
            this.Xqedii_35obddf.put(var2.Xqedii_m23ejo5, var2);
            this.Xqedii_2v8lhne();
            this.Xqedii_1d1ndqq();
            return (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(568959354, 19) != 0);
         }
      } else {
         this.plugin.getLogger().warning(Xqedii_8hx9a18mojyy(var1.Xqedii_1kjhbri, var1.Xqedii_1kjhbri));
         return (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(793001012, 8) != 0);
      }
   }

   public boolean Xqedii_iy1ykel(Xqedii_irwktj3 var1, Player var2) {
      if (((-460686923 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return ((var2 == null || !var2.isOnline() || !var1.Xqedii_89d5zvi.isBlank() && !var2.hasPermission(var1.Xqedii_89d5zvi)
            ? Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-767336259, 11)
            : Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2015589574, 24)) != 0);
      }
   }

   public int Xqedii_1v71dhx(Xqedii_irwktj3 var1) {
      if ((704492795 * (704492795 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         int var2 = Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 12);

         for (Player var4 : Bukkit.getOnlinePlayers()) {
            if (this.Xqedii_iy1ykel(var1, var4) && this.Xqedii_1lvabt1(var1, var4)) {
               var2++;
            }
         }

         return var2;
      }
   }

   public boolean Xqedii_1lvabt1(Xqedii_irwktj3 var1, Player var2) {
      if (((-1959246294 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String var3 = this.Xqedii_33ry265(var1.Xqedii_1kjhbri, var2.getUniqueId());
         this.Xqedii_3p8vym0(var3);
         Xqedii_1a2mt0h var4 = this.Xqedii_e3ny68s(var3, var1, var2.getUniqueId());
         if (var4 == null) {
            return (Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 18) != 0);
         } else {
            var4.Xqedii_2lkymh6 = this.Xqedii_2rz8vil(var1, var2, null);
            this.Xqedii_35obddf.put(var3, var4);
            this.Xqedii_2v8lhne();
            this.Xqedii_1d1ndqq();
            return (Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 9) != 0);
         }
      }
   }

   public Xqedii_1a2mt0h Xqedii_e3ny68s(String var1, Xqedii_irwktj3 var2, UUID var3) {
      if ((-243667725 * -243667725 - -243667725 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         ParsedGuiLayout var4 = this.Xqedii_2enuzo1(var2);
         return var4 == null ? null : new Xqedii_1a2mt0h(var1, var2, var3, var4);
      }
   }

   public ParsedGuiLayout Xqedii_2enuzo1(Xqedii_irwktj3 var1) {
      if (((789982160 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         ParsedGuiLayout var2 = var1.Xqedii_2q8vz77;
         if (var2 != null) {
            return var2;
         } else {
            ArrayList var3 = new ArrayList();
            Map var4 = this.loadYamlFolder(
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("hHAjlSC9gu1Htg7yKI3SQOZH5sm89D3bZrn4SFJheSQbTYIPTrSWvoxB3RbcbnGbmF2JpVxGovM="), var3
            );
            String var5 = this.normalizePageKey(var1.Xqedii_122m5nm, var1.Xqedii_122m5nm);
            String var6 = this.resolveExistingPageMapKey(var4, var5);
            YamlConfiguration var7 = var6.isBlank() ? null : (YamlConfiguration)var4.get(var6);
            if (var7 == null) {
               this.plugin.getLogger().warning(Xqedii_39ha5k65mj4h2(var1.Xqedii_1kjhbri, var1.Xqedii_122m5nm));
               return null;
            } else {
               Map var8 = this.expandComponentImageBlocks(
                  this.loadYamlFolder(
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("l42Nnm2XrQVeT/u8ULX0OupvA7nC8MyrcK7R3NPOKuJSccOwiTymQOgHfYOAONs80zwR+K6dhP0WbauYnY5Vj5DZ"), var3
                  )
               );
               List var9 = this.expandCompactImageBlocks(
                  this.copyBlocks(var7.getList(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("eUgnA48QUEliZYPJ0rBVVnjRcNByj4NYvV3AnIMBGII/TripzWerew==")))
               );
               this.applyImageLayoutOffsetFormatToLoadedBlocks(var7, var9);
               ParsedGuiLayout var10 = new ParsedGuiLayout(
                  var9,
                  var8,
                  Math.max(
                     Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1889611803255765625L, 53)),
                     var7.getDouble(
                        Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("jFFZTd4uiUOo6t7sPRgIzM49HtyL+IGlEMWnU/93Ea85FidQlgoEehBgENAkDGlvdvjksw=="),
                        Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-1942824344722228012L, 61))
                     )
                  ),
                  Math.max(
                     Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-325139100810476845L, 45)),
                     var7.getDouble(
                        Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("c9TKQZrYBdFVUo2oHeSddpkV8OqzozoLxtFiU5S7O2qRhwOjylOoeUqhvvD66y6rnFLR/tD9"),
                        Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2643533125723097610L, 60))
                     )
                  )
               );
               var1.Xqedii_2q8vz77 = var10;
               return var10;
            }
         }
      }
   }

   public Xqedii_1avxacb Xqedii_322y3to(Xqedii_1a2mt0h var1, Xqedii_2f78jo3 var2) {
      if ((1420253166 ^ 1420253166) != 0) {
         throw new IllegalStateException();
      } else {
         return var1.Xqedii_2u9ms1f.Xqedii_1xd0aa1 ? var2.Xqedii_186erki : var1.Xqedii_342ikl0;
      }
   }

   public void Xqedii_jjzxp6j(Xqedii_1a2mt0h var1, Xqedii_1avxacb var2) {
      if ((-266010203 ^ -266010203) != 0) {
         throw new IllegalStateException();
      } else {
         ParsedGuiLayout var3 = var1.Xqedii_3uo0d9i;
         var2.Xqedii_2q32dop.clearMetrics();
         var2.Xqedii_2q32dop.clearFilterValues();

         for (Entry var5 : this.Xqedii_22qy351(var1.Xqedii_m23ejo5, var2.Xqedii_zayz2bu).entrySet()) {
            var2.Xqedii_2q32dop.putFilterValue((String)var5.getKey(), (String)var5.getValue());
         }

         for (AnvilInputState var11 : var2.Xqedii_h3mynbh.values()) {
            var2.Xqedii_2q32dop.putFilterValue(var11.inputId(), var11.text());
            var2.Xqedii_2q32dop.putFilterValue(var11.variable(), var11.text());
         }

         for (Entry var12 : var2.Xqedii_h8t9zqa.entrySet()) {
            double[] var6 = (double[])var12.getValue();
            if (var6 != null && var6.length >= Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-2005112738, 17)) {
               var2.Xqedii_2q32dop
                  .putOffset(
                     (String)var12.getKey(),
                     var6[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1122963248, 10)],
                     var6[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1757270595, 27)]
                  );
            }
         }

         List var10 = this.copyBlocks(var3.rawBlocks());
         this.synchronizeImageGroupLayers(var10);
         List var13 = this.templateResolver.resolveBlocks(var10, var3.components(), var2.Xqedii_2q32dop);
         this.propagateRuntimeHudStackPage(var10, var13);

         for (ScrollViewState.Metrics var7 : var2.Xqedii_2q32dop.allMetrics().values()) {
            Map var10000 = var2.Xqedii_h8t9zqa;
            String var10001 = var7.containerId;
            double[] var10002 = new double[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(199042103, 31)];
            var10002[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 8)] = var7.offsetX;
            var10002[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(796168410, 1)] = var7.offsetY;
            var10000.put(var10001, var10002);
         }

         var2.Xqedii_17c3k63 = var13 == null ? Collections.emptyList() : var13;
         var2.Xqedii_r93bzs3 = (Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-292414651, 6) != 0);
      }
   }

   public ScrollViewState.Metrics Xqedii_1yf74gi(Xqedii_1avxacb var1, double var2, double var4) {
      if ((130035790 * 130035790 - 130035790 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         ScrollViewState.Metrics var6 = null;

         for (ScrollViewState.Metrics var8 : var1.Xqedii_2q32dop.allMetrics().values()) {
            if (var8.scrollable() && var8.contains(var2, var4) && (var6 == null || var8.viewWidth * var8.viewHeight < var6.viewWidth * var6.viewHeight)) {
               var6 = var8;
            }
         }

         return var6;
      }
   }

   public void Xqedii_35fczng(Xqedii_1a2mt0h var1, Xqedii_2f78jo3 var2, Xqedii_1avxacb var3, ScrollViewState.Metrics var4, double var5, double var7) {
      if (((215404460 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         double[] var9 = var3.Xqedii_h8t9zqa.get(var4.containerId);
         double var10 = var9 != null && var9.length >= Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-584829173, 7)
            ? var9[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 11)]
            : var4.offsetX;
         double var12 = var9 != null && var9.length >= Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1114953687, 31)
            ? var9[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032368838, 11)]
            : var4.offsetY;
         double[] var14 = var3.Xqedii_1c9ttj8.get(var4.containerId);
         double var15 = var14 == null ? var10 : var14[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-383666082, 10)];
         double var17 = var14 == null ? var12 : var14[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1036724265, 21)];
         double var19 = this.clampScrollOffset(var15 + var5, var4.maxOffsetX);
         double var21 = this.clampScrollOffset(var17 + var7, var4.maxOffsetY);
         if (var4.smoothTicks <= 0) {
            var3.Xqedii_1c9ttj8.remove(var4.containerId);
            if (!(Math.abs(var19 - var10) < Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-5932434782836875737L, 55)))
               || !(Math.abs(var21 - var12) < Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-4072832543202786341L, 22)))) {
               Map var23 = var3.Xqedii_h8t9zqa;
               String var24 = var4.containerId;
               double[] var25 = new double[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(461762194, 22)];
               var25[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(793001012, 8)] = var19;
               var25[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032358598, 13)] = var21;
               var23.put(var24, var25);
               this.Xqedii_oncxunw(var1, var2, var3, Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366822, 4));
            }
         } else if (Math.abs(var19 - var10) < Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-8313301864132095979L, 49))
            && Math.abs(var21 - var12) < Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(-6024518711658287222L, 21))) {
            var3.Xqedii_1c9ttj8.remove(var4.containerId);
         } else {
            Map var10000 = var3.Xqedii_1c9ttj8;
            String var10001 = var4.containerId;
            double[] var10002 = new double[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1031874601, 17)];
            var10002[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(793001012, 8)] = var10;
            var10002[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1534668421, 12)] = var12;
            var10002[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-482151357, 28)] = var19;
            var10002[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032170182, 16)] = var21;
            var10002[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1574954473, 15)] = Double.longBitsToDouble(
               Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673804245514L, 1)
            );
            var10002[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2033677510, 18)] = var4.smoothTicks;
            var10000.put(var10001, var10002);
            this.Xqedii_1n1xwfe(var1, var2, var3);
         }
      }
   }

   public void Xqedii_1n1xwfe(Xqedii_1a2mt0h var1, Xqedii_2f78jo3 var2, Xqedii_1avxacb var3) {
      if ((-703213812 ^ -703213812) != 0) {
         throw new IllegalStateException();
      } else if (!var3.Xqedii_1c9ttj8.isEmpty()) {
         Iterator var4 = var3.Xqedii_1c9ttj8.entrySet().iterator();

         while (var4.hasNext()) {
            Entry var5 = (Entry)var4.next();
            double[] var6 = (double[])var5.getValue();
            double var7 = Math.max(
               Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(2566042980547209047L, 8)), var6[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(273484753, 22)]
            );
            var6[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032333353, 16)] = Math.min(
               var7,
               var6[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032399558, 13)]
                  + Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(3036901609305143929L, 62))
            );
            double var9 = var6[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529969, 3)] / var7;
            double var11 = Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771672235575818L, 35))
               - Math.pow(
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(9139921750196219379L, 63)) - var9,
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882577334019684985L, 49))
               );
            double var13 = var6[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 22)]
               + (var6[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1169658345, 8)] - var6[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-509511135, 27)]) * var11;
            double var15 = var6[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-393763195, 13)]
               + (var6[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1225646839, 13)] - var6[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 5)]) * var11;
            ScrollViewState.Metrics var17 = var3.Xqedii_2q32dop.metrics((String)var5.getKey());
            if (var17 != null) {
               var13 = this.clampScrollOffset(var13, var17.maxOffsetX);
               var15 = this.clampScrollOffset(var15, var17.maxOffsetY);
            }

            Map var10000 = var3.Xqedii_h8t9zqa;
            String var10001 = (String)var5.getKey();
            double[] var10002 = new double[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-2048358458, 5)];
            var10002[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 31)] = var13;
            var10002[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1864749196, 17)] = var15;
            var10000.put(var10001, var10002);
            if (var9 >= Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2331027731853347338L, 58))) {
               var4.remove();
            }
         }

         this.Xqedii_oncxunw(var1, var2, var3, Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032367046, 7));
      }
   }

   public void Xqedii_oncxunw(Xqedii_1a2mt0h var1, Xqedii_2f78jo3 var2, Xqedii_1avxacb var3, int var4) {
      if ((1818024761 ^ 1818024761) != 0) {
         throw new IllegalStateException();
      } else {
         this.Xqedii_jjzxp6j(var1, var3);
         if (var1.Xqedii_2u9ms1f.Xqedii_1xd0aa1) {
            Player var8 = var2 == null ? null : Bukkit.getPlayer(var2.Xqedii_rjd91hv);
            if (var8 != null && var8.isOnline() && var1.Xqedii_2zsng32.get(var2.Xqedii_rjd91hv) == var2) {
               this.Xqedii_2nnpmot(var1, var8, var2, var4);
            }
         } else {
            for (Xqedii_2f78jo3 var6 : var1.Xqedii_2zsng32.values()) {
               Player var7 = Bukkit.getPlayer(var6.Xqedii_rjd91hv);
               if (var7 != null && var7.isOnline()) {
                  this.Xqedii_2nnpmot(var1, var7, var6, var4);
               }
            }
         }
      }
   }

   public void Xqedii_1d1ndqq() {
      if (((856097136 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (!this.Xqedii_2dkhpcj && !this.Xqedii_35obddf.isEmpty() && this.plugin != null && this.plugin.isEnabled()) {
         this.Xqedii_2dkhpcj = (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1575052777, 15) != 0);
         int var1 = this.Xqedii_2af7lfn.incrementAndGet();
         Xqedii_38o2zes.Xqedii_3npx6fi(this.plugin, () -> {
            if (((300757485 << 1 | 1) & 1) == 0) {
               throw new IllegalStateException();
            } else {
               this.Xqedii_2d3cbyq(var1);
            }
         }, Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(6870600363050530294L, 63));
      }
   }

   public void Xqedii_25jxs4n() {
      if (((699458816 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         this.Xqedii_2dkhpcj = (Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 24) != 0);
         this.Xqedii_2af7lfn.incrementAndGet();
      }
   }

   public void Xqedii_2d3cbyq(int var1) {
      if ((831861864 * 831861864 + 831861864 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == this.Xqedii_2af7lfn.get()) {
         if (!this.Xqedii_35obddf.isEmpty() && this.plugin.isEnabled()) {
            try {
               this.Xqedii_1t9i0ae();
            } catch (Throwable var3) {
               this.plugin.getLogger().warning(Xqedii_2ekog5ljh3z52(String.valueOf(var3)));
            }

            Xqedii_38o2zes.Xqedii_3npx6fi(this.plugin, () -> {
               if ((1393726524 ^ 1393726524) != 0) {
                  throw new IllegalStateException();
               } else {
                  this.Xqedii_2d3cbyq(var1);
               }
            }, Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(-1882854204791454330L, 62));
         } else {
            this.Xqedii_2dkhpcj = (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(252513233, 22) != 0);
         }
      }
   }

   public void Xqedii_1t9i0ae() {
      if (((-1310608732 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         long var1 = Xqedii_38o2zes.Xqedii_316oplq();
         int var3 = var1 % Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673793759754L, 20) == Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-3674539282415232016L, 20)
            ? Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366822, 5)
            : Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 17);

         for (Xqedii_1a2mt0h var5 : new ArrayList<>(this.Xqedii_35obddf.values())) {
            if (var5.Xqedii_hrnh6v6 != null) {
               Player var6 = Bukkit.getPlayer(var5.Xqedii_hrnh6v6);
               if (var6 == null || !var6.isOnline()) {
                  this.Xqedii_35obddf.remove(var5.Xqedii_m23ejo5);
                  this.Xqedii_gtw7cir(var5);
                  continue;
               }

               this.Xqedii_3ks78ll(var5, var6);
            }

            if (var3 != 0 || var5.Xqedii_3nvqfsp) {
               var5.Xqedii_3nvqfsp = (Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 13) != 0);
               this.Xqedii_2dcj7ta(var5);
            }

            Xqedii_irwktj3 var7 = var5.Xqedii_2u9ms1f;
            if (var7.Xqedii_1svt1aq != 0 && (var7.Xqedii_2tf5gsk || var7.Xqedii_1fdepr5)) {
               this.Xqedii_kgpbq8v(var5);
            }

            if (var7.Xqedii_1fdepr5) {
               this.Xqedii_1uct5er(var5);
               this.Xqedii_2r08i89(var5);
            }

            if (var7.Xqedii_3p6634s > 0 && var1 - var5.Xqedii_2vod7ky >= var7.Xqedii_3p6634s) {
               var5.Xqedii_2vod7ky = var1;
               this.Xqedii_jd99vux(var5);
            }
         }
      }
   }

   public void Xqedii_1uct5er(Xqedii_1a2mt0h var1) {
      if ((918753187 * 918753187 - 918753187 & 1) != 0) {
         throw new IllegalStateException();
      } else if (!var1.Xqedii_2u9ms1f.Xqedii_1xd0aa1) {
         if (!var1.Xqedii_342ikl0.Xqedii_1c9ttj8.isEmpty()) {
            this.Xqedii_1n1xwfe(var1, null, var1.Xqedii_342ikl0);
         }
      } else {
         for (Xqedii_2f78jo3 var3 : var1.Xqedii_2zsng32.values()) {
            if (!var3.Xqedii_186erki.Xqedii_1c9ttj8.isEmpty()) {
               this.Xqedii_1n1xwfe(var1, var3, var3.Xqedii_186erki);
            }
         }
      }
   }

   public Location Xqedii_2rz8vil(Xqedii_irwktj3 var1, Player var2, Location var3) {
      if ((-1005824740 ^ -1005824740) != 0) {
         throw new IllegalStateException();
      } else {
         Location var4 = var2.getEyeLocation();
         float var5 = !var1.Xqedii_pyyh81w && var3 != null ? var3.getYaw() : var4.getYaw();
         double var6 = Math.toRadians(var5);
         double var8 = -Math.sin(var6);
         double var10 = Math.cos(var6);
         double var12 = -Math.cos(var6);
         double var14 = -Math.sin(var6);
         Location var16 = var4.clone()
            .add(var8 * var1.Xqedii_15csyqs + var12 * var1.Xqedii_2gpxojm, var1.Xqedii_2fl6qyh, var10 * var1.Xqedii_15csyqs + var14 * var1.Xqedii_2gpxojm);
         var16.setYaw(var5);
         var16.setPitch(Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 14)));
         return var16;
      }
   }

   public void Xqedii_3ks78ll(Xqedii_1a2mt0h var1, Player var2) {
      if (((-1879798088 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (!this.cameraService.isCameraActive(var2)) {
         Location var3 = this.Xqedii_2rz8vil(var1.Xqedii_2u9ms1f, var2, var1.Xqedii_2lkymh6);
         Location var4 = var1.Xqedii_2lkymh6;
         if (var4 != null && var4.getWorld() != null && var4.getWorld().equals(var3.getWorld())) {
            double var5 = Math.abs(Xqedii_2ezay7r(var3.getYaw() - var4.getYaw()));
            if (!(var4.distanceSquared(var3) < Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(-7722984038027915734L, 42)))
               || !(var5 < Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673804273098L, 17)))) {
               var1.Xqedii_2lkymh6 = var3;

               for (Entry var8 : var1.Xqedii_2zsng32.entrySet()) {
                  Player var9 = Bukkit.getPlayer((UUID)var8.getKey());
                  if (var9 != null && var9.isOnline()) {
                     this.Xqedii_2mnajyt(var1, var9, (Xqedii_2f78jo3)var8.getValue());
                  }
               }
            }
         } else {
            this.Xqedii_gtw7cir(var1);
            var1.Xqedii_2lkymh6 = var3;
            var1.Xqedii_3nvqfsp = (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(513415074, 23) != 0);
         }
      }
   }

   public void Xqedii_2mnajyt(Xqedii_1a2mt0h var1, Player var2, Xqedii_2f78jo3 var3) {
      if ((1731267112 * 1731267112 - 1731267112 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         for (Xqedii_3j2w0l1 var5 : var3.Xqedii_2f37hi3.values()) {
            this.hudService.teleportWorldEntity(var2, var5.Xqedii_2b6hmqn, var1.Xqedii_2lkymh6);
         }

         var3.Xqedii_306vaa6 = this.Xqedii_5cpli6d(var1, var2);

         for (Entry var7 : var3.Xqedii_at2aqft.entrySet()) {
            this.hudService.teleportWorldEntity(var2, (Integer)var7.getKey(), this.Xqedii_1kff9t1(var1, (Xqedii_2pbx7s8)var7.getValue(), var3.Xqedii_306vaa6));
         }
      }
   }

   public void Xqedii_kgpbq8v(Xqedii_1a2mt0h var1) {
      if ((-1399871985 ^ -1399871985) != 0) {
         throw new IllegalStateException();
      } else {
         for (Entry var3 : var1.Xqedii_2zsng32.entrySet()) {
            Xqedii_2f78jo3 var4 = (Xqedii_2f78jo3)var3.getValue();
            if (!var4.Xqedii_at2aqft.isEmpty()) {
               Player var5 = Bukkit.getPlayer((UUID)var3.getKey());
               if (var5 != null && var5.isOnline()) {
                  float var6 = this.Xqedii_5cpli6d(var1, var5);
                  if (!(
                     Math.abs(Xqedii_2ezay7r(var6 - var4.Xqedii_306vaa6))
                        < Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-2326142630924911748L, 61))
                  )) {
                     var4.Xqedii_306vaa6 = var6;

                     for (Entry var8 : var4.Xqedii_at2aqft.entrySet()) {
                        this.hudService.teleportWorldEntity(var5, (Integer)var8.getKey(), this.Xqedii_1kff9t1(var1, (Xqedii_2pbx7s8)var8.getValue(), var6));
                     }
                  }
               }
            }
         }
      }
   }

   public float Xqedii_5cpli6d(Xqedii_1a2mt0h var1, Player var2) {
      if ((-326889674 ^ -326889674) != 0) {
         throw new IllegalStateException();
      } else if (var1.Xqedii_2u9ms1f.Xqedii_1svt1aq != 0 && var2 != null) {
         return var2.getLocation().getYaw();
      } else {
         return var1.Xqedii_2lkymh6 == null ? Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1136870132, 20)) : var1.Xqedii_2lkymh6.getYaw();
      }
   }

   public void Xqedii_2dcj7ta(Xqedii_1a2mt0h var1) {
      if ((1083382264 * 1083382264 - 1083382264 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         HashSet var2 = new HashSet();

         for (Player var4 : Bukkit.getOnlinePlayers()) {
            if (this.Xqedii_iuqegbr(var1, var4)) {
               var2.add(var4.getUniqueId());
            }
         }

         for (UUID var8 : new ArrayList<>(var1.Xqedii_2zsng32.keySet())) {
            if (!var2.contains(var8)) {
               Xqedii_2f78jo3 var5 = var1.Xqedii_2zsng32.remove(var8);
               if (var5 != null) {
                  this.Xqedii_1gftlib(Bukkit.getPlayer(var8), var5);
               }
            }
         }

         for (UUID var9 : (Iterable<UUID>) (var2)) {

            if (!var1.Xqedii_2zsng32.containsKey(var9)) {
               Player var10 = Bukkit.getPlayer(var9);
               if (var10 != null) {
                  this.Xqedii_1zf2i3i(var1, var10);
               }
            }
         }
      }
   }

   public boolean Xqedii_iuqegbr(Xqedii_1a2mt0h var1, Player var2) {
      if ((-1583026363 * -1583026363 - -1583026363 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Location var3 = var1.Xqedii_2lkymh6;
         if (var2 != null && var2.isOnline() && var3 != null && var3.getWorld() != null) {
            if (!var3.getWorld().equals(var2.getWorld())) {
               return (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(218878223, 30) != 0);
            } else {
               Xqedii_irwktj3 var4 = var1.Xqedii_2u9ms1f;
               if (var4.Xqedii_3rggyzo && var1.Xqedii_hrnh6v6 != null && !var1.Xqedii_hrnh6v6.equals(var2.getUniqueId())) {
                  return (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(284217533, 18) != 0);
               } else if (!var4.Xqedii_89d5zvi.isBlank() && !var2.hasPermission(var4.Xqedii_89d5zvi)) {
                  return (Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1955649583, 9) != 0);
               } else {
                  Location var5 = var2.getLocation();
                  if (var5.distanceSquared(var3) > var4.Xqedii_oy26rm2 * var4.Xqedii_oy26rm2) {
                     return (Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 27) != 0);
                  } else {
                     int var6 = Math.max(Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2033415366, 19), Math.min(var2.getClientViewDistance(), Bukkit.getViewDistance()));
                     int var7 = Math.max(
                        Math.abs(
                           (var5.getBlockX() >> Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366786, 0))
                              - (var3.getBlockX() >> Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032301254, 14))
                        ),
                        Math.abs(
                           (var5.getBlockZ() >> Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032528937, 10))
                              - (var3.getBlockZ() >> Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(875512896, 0))
                        )
                     );
                     return ((var7 < var6 ? Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032365766, 10) : Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 8)) != 0);
                  }
               }
            }
         } else {
            return (Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1215043514, 16) != 0);
         }
      }
   }

   public void Xqedii_1zf2i3i(Xqedii_1a2mt0h var1, Player var2) {
      if ((-329725428 * -329725428 - -329725428 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_2f78jo3 var3 = new Xqedii_2f78jo3(var2.getUniqueId());
         var1.Xqedii_2zsng32.put(var2.getUniqueId(), var3);
         this.Xqedii_2nnpmot(var1, var2, var3, Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1123304387, 4));
      }
   }

   public void Xqedii_2nnpmot(Xqedii_1a2mt0h var1, Player var2, Xqedii_2f78jo3 var3, int var4) {
      if (((-1082773658 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         ParsedGuiLayout var5 = var1.Xqedii_3uo0d9i;
         Xqedii_1avxacb var6 = this.Xqedii_322y3to(var1, var3);
         if (!var6.Xqedii_r93bzs3) {
            this.Xqedii_jjzxp6j(var1, var6);
         }

         Xqedii_irwktj3 var7 = var1.Xqedii_2u9ms1f;
         Map var8 = this.Xqedii_22qy351(var1.Xqedii_m23ejo5, var6.Xqedii_zayz2bu);
         int var9 = var1.Xqedii_hrnh6v6 == null ? Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(796168408, 1) : var7.Xqedii_2jpa8ne;
         int var10 = !var3.Xqedii_169nxdk && var7.Xqedii_2tf5gsk && !var7.Xqedii_1fdepr5
            ? Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-2019129880, 21)
            : Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 14);
         LinkedHashMap var11 = new LinkedHashMap();
         HashMap<String, Integer> var12 = new HashMap<>();
         HashMap<String, double[]> var13 = new HashMap<>();
         HashMap var14 = new HashMap();
         LinkedHashMap var15 = new LinkedHashMap();
         ArrayList var16 = new ArrayList();
         double var17 = Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854204791454329L, 33));
         int var19 = Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1813494344, 24);

         for (Map var21 : var6.Xqedii_17c3k63) {
            ConfigurationSection var22 = this.mapToSection(var21);
            if (var22 != null
               && this.parseBooleanFlag(
                  var22.get(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("xgRuw7gIQGcwb0ffrtcFfZnGKq4zKE7ifudLgiKCJ7qvnn9YfSrmSRut")),
                  (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(2049042849, 11) != 0)
               )) {
               String[] var10001 = new String[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032367302, 8)];
               var10001[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(2074379969, 4)] = var22.getString(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("i/RtKce25ylrvsCfcFlGNAEvFfaT/GrAlKdToRvQdld00+yQ")
               );
               var10001[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-393763195, 13)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
                  "QUBOSaLWsd0g7cX/1BXTYkc2EqTkMY4NvgHMmIC6JStkD7Eck7w="
               );
               String var23 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
               if (this.isRenderableBlockType(var23)) {
                  var10001 = new String[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032362694, 12)];
                  var10001[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(252513233, 22)] = var22.getString(
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("7x3XOzzBuxFW7sViXucDUggTqnuo7Kx/yCLLjdWfZ6cfJTUQnKJRx9HFMG7dFRY7aFpsTTfdw6/n1FhMlH4ohelIHlw=")
                  );
                  String var24 = this.firstNonBlank(var10001).trim();
                  boolean var25 = Xqedii_pzw30wg(var22);
                  double[] var26 = this.Xqedii_3sfh1lz(var2, var22, var23);
                  if (Xqedii_b9cpusm(var22, var23)) {
                     double[] var10000 = new double[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(198250509, 6)];
                     var10000[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 21)] = var26[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 21)]
                        - Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-7159673985243072614L, 58));
                     var10000[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 7)] = var26[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 23)]
                        - Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-162569516313935511L, 44));
                     var10000[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(1763931334, 27)] = var26[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032365766, 9)]
                        - Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-51854476682988042L, 63));
                     var10000[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366918, 7)] = var26[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1574987241, 15)]
                        - Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771656516373002L, 36));
                     var26 = var10000;
                     if (!var24.isBlank()) {
                        var13.put(var24, var26);
                     }
                  }

                  double[] var27 = var25 ? var13.getOrDefault(var24, var26) : var26;
                  if (!this.Xqedii_3h4d3xi(var22, var27)) {
                     double var28 = this.readRuntimeNumericValue(
                        var2,
                        null,
                        var22,
                        Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("Xjw6HwVNnNviLcDMVBlD76QuIygQu3Z9CtHDU+Uy5JCRnuAKMjA="),
                        Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("Xjw6HwVNnNviLcDMVBlD76QuIygQu3Z9CtHDU+Uy5JCRnuAKMjA="),
                        this.readRuntimeNumericValue(
                           var2,
                           null,
                           var22,
                           Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("Lw3cWxlsG3zbEwUj3y/M7ggO4MFLVltrCy89U4ZF8QDn9FEnk3M+my3+N0IoBcj2"),
                           Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("djqGak0rta+mQJzXLkES42CiyKYKKPIpSXylxTsivPOMJ7cPTQ8="),
                           Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-910044306318077971L, 55))
                        )
                     );
                     var17 = Math.max(var17, var28);
                     var19++;
                     if (!var25) {
                        var16.add(
                           new ElementBounds(
                              var24,
                              var26[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 30)],
                              var26[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366806, 4)],
                              var26[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-511990650, 28)],
                              var26[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1955651119, 9)],
                              var28,
                              var19
                           )
                        );
                     }

                     Xqedii_3kjd61w var30 = this.Xqedii_3jnizuu(var1, var2, var22, var23, var28, var8, null);
                     if (var30 != null) {
                        String var31 = var24.isBlank() ? Xqedii_2zov4aeogw0cg(var23) : var24;
                        String var32 = Xqedii_ophwaqb4vzuj(
                           var31, String.valueOf(var12.merge(var31, Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 21), Integer::sum))
                        );
                        Xqedii_3j2w0l1 var33 = this.Xqedii_3hxpbic(var1, var2, var3, var32, var30.Xqedii_2g23al3, var9);
                        if (var33 != null) {
                           var33.Xqedii_3u5s934 = var24;
                           var33.Xqedii_2nf1zu3 = var22;
                           var33.Xqedii_o3buktx = var23;
                           var33.Xqedii_tq2w5ap = var30.Xqedii_2g23al3;
                           var33.Xqedii_1p17s7a = var30.Xqedii_2g23al3.isItem() ? null : this.Xqedii_hukkpjv(var2, var22, var8);
                           var33.Xqedii_1n6r5g3 = var25 ? var13.getOrDefault(var24, var26) : var26;
                           var11.put(var32, var33);
                        }
                     }

                     if (this.isInputType(var23)) {
                        Xqedii_3kjd61w var47 = this.Xqedii_3t8puw7(var1, var2, var6, var22, var24, var28);
                        if (var47 != null) {
                           String var48 = Xqedii_fwd2f8nfci4j(var24.isBlank() ? Xqedii_3hfwobsy6rnhr(var23) : var24);
                           String var49 = Xqedii_3j3cdk8zooc0l(
                              var48, String.valueOf(var12.merge(var48, Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 28), Integer::sum))
                           );
                           Xqedii_3j2w0l1 var34 = this.Xqedii_3hxpbic(var1, var2, var3, var49, var47.Xqedii_2g23al3, var9);
                           if (var34 != null) {
                              var34.Xqedii_3u5s934 = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nDGc1imw9eT8I5Ax9Ff6HdZ2P+CrPrIa9F/Iog==");
                              var34.Xqedii_2nf1zu3 = null;
                              var34.Xqedii_o3buktx = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("aAoqzBMiPDCcz//+WzYE1ohaKhOcljxRgstIMTGUV7HMhH4n");
                              var34.Xqedii_tq2w5ap = var47.Xqedii_2g23al3;
                              var34.Xqedii_1p17s7a = null;
                              var34.Xqedii_1n6r5g3 = var26;
                              var11.put(var49, var34);
                           }
                        }
                     }

                     if (var10 != 0 && !var25) {
                        this.Xqedii_vxzcgon(var5, var22, var23, var26, var28, var14, var15);
                     }
                  }
               }
            }
         }

         if (!var3.Xqedii_2f37hi3.isEmpty()) {
            int[] var35 = new int[var3.Xqedii_2f37hi3.size()];
            int var37 = Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 27);

            for (Xqedii_3j2w0l1 var44 : var3.Xqedii_2f37hi3.values()) {
               var35[var37++] = var44.Xqedii_2b6hmqn;
            }

            this.hudService.destroyWorldEntities(var2, var35);
         }

         var3.Xqedii_2f37hi3.clear();
         var3.Xqedii_2f37hi3.putAll(var11);
         var3.Xqedii_2aq4qhk = var16;
         this.Xqedii_1q63spb(var1, var2, var3, var4);
         if (!var3.Xqedii_169nxdk) {
            var3.Xqedii_169nxdk = (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1137918708, 20) != 0);
            var3.Xqedii_306vaa6 = this.Xqedii_5cpli6d(var1, var2);
            ArrayList var36 = new ArrayList();
            if (var7.Xqedii_1fdepr5) {
               Xqedii_3bglnh9 var39 = new Xqedii_3bglnh9(Collections.emptyList(), null);
               double[] var52 = new double[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032350406, 12)];
               var52[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1130413522, 19)] = Double.longBitsToDouble(
                  Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854204791454329L, 39)
               );
               var52[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 3)] = Double.longBitsToDouble(
                  Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(4568606412848614965L, 31)
               );
               var52[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(260901841, 22)] = var5.pageWidth();
               var52[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032527913, 10)] = var5.pageHeight();
               var39.Xqedii_lr3km7o(var52, var17);
               var36.addAll(this.Xqedii_368ahcy(var1, var39, (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1501173625, 29) != 0)));
            } else {
               for (Xqedii_3bglnh9 var42 : (Iterable<Xqedii_3bglnh9>) (var15.values())) {

                  var36.addAll(this.Xqedii_368ahcy(var1, var42, (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1751025784, 1) != 0)));
               }
            }

            for (Xqedii_2pbx7s8 var43 : (Iterable<Xqedii_2pbx7s8>) (var36)) {

               Location var45 = this.Xqedii_1kff9t1(var1, var43, var3.Xqedii_306vaa6);
               int var46 = this.hudService.spawnWorldInteraction(var2, var45, (float)var43.Xqedii_35crh3k, (float)var43.Xqedii_203jf14);
               if (var46 >= 0) {
                  var3.Xqedii_at2aqft.put(var46, var43);
                  this.Xqedii_28h4g3k.put(var46, new WorldUiViewerEntity(var1.Xqedii_m23ejo5, var2.getUniqueId(), var46));
               }
            }
         }
      }
   }

   public Xqedii_3j2w0l1 Xqedii_3hxpbic(Xqedii_1a2mt0h var1, Player var2, Xqedii_2f78jo3 var3, String var4, WorldDisplays.WorldElementSpec var5, int var6) {
      if (((-1808326213 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_3j2w0l1 var7 = var3.Xqedii_2f37hi3.remove(var4);
         if (var7 != null && var7.Xqedii_tq2w5ap.isItem() != var5.isItem()) {
            HudService var10000 = this.hudService;
            int[] var10002 = new int[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-634424176, 25)];
            var10002[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1534672517, 12)] = var7.Xqedii_2b6hmqn;
            var10000.destroyWorldEntities(var2, var10002);
            var7 = null;
         }

         if (var7 != null) {
            return var7;
         } else {
            int var8 = this.hudService.spawnWorldDisplay(var2, var1.Xqedii_2lkymh6, var5, var6);
            if (var8 < 0) {
               return null;
            } else {
               var7 = new Xqedii_3j2w0l1(var8);
               var7.Xqedii_2445hq8 = var5;
               return var7;
            }
         }
      }
   }

   public void Xqedii_1q63spb(Xqedii_1a2mt0h var1, Player var2, Xqedii_2f78jo3 var3, int var4) {
      if (((-218656875 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         HoverEffectTarget var5 = this.Xqedii_3gedfec(var1, var3);
         int var6 = var5 != null && var5.effect() != null
            ? Math.max(Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 19), var5.effect().durationTicks())
            : Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1215043514, 16);
         int var7 = Math.max(var6, var3.Xqedii_3cc6ajn);

         for (Xqedii_3j2w0l1 var9 : var3.Xqedii_2f37hi3.values()) {
            boolean var10 = var5 != null && var5.targetPath().equals(var9.Xqedii_3u5s934);
            WorldDisplays.WorldElementSpec var11 = var10 ? this.Xqedii_kniw8o1(var1, var9, var5) : var9.Xqedii_tq2w5ap;
            boolean var12 = var10 != var9.Xqedii_29wx01t;
            var9.Xqedii_29wx01t = var10;
            if (!Objects.equals(var11, var9.Xqedii_2445hq8)) {
               int var13 = var12 ? Math.max(var4, var7) : var4;
               this.hudService.updateWorldDisplay(var2, var9.Xqedii_2b6hmqn, var9.Xqedii_2445hq8, var11, var13);
               var9.Xqedii_2445hq8 = var11;
            }
         }

         var3.Xqedii_3cc6ajn = var6;
      }
   }

   public HoverEffectTarget Xqedii_3gedfec(Xqedii_1a2mt0h var1, Xqedii_2f78jo3 var2) {
      if ((281316445 * 281316445 + 281316445 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var3 = var2.Xqedii_2xzilb5;
         if (var3 != null && !var3.isBlank()) {
            Map var4 = this.resolveRawMapAtPath(var1.Xqedii_3uo0d9i.rawBlocks(), var3);
            String var10000;
            if (var4 == null) {
               var10000 = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("QUBOSaLWsd0g7cX/1BXTYkc2EqTkMY4NvgHMmIC6JStkD7Eck7w=");
            } else {
               String[] var10001 = new String[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(1495495878, 28)];
               var10001[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 11)] = this.stringValue(
                  this.readMapPathValue(var4, Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("i/RtKce25ylrvsCfcFlGNAEvFfaT/GrAlKdToRvQdld00+yQ"))
               );
               var10001[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1292620047, 30)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
                  "QUBOSaLWsd0g7cX/1BXTYkc2EqTkMY4NvgHMmIC6JStkD7Eck7w="
               );
               var10000 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
            }

            String var5 = var10000;
            if (var4 == null) {
               var10000 = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nDGc1imw9eT8I5Ax9Ff6HdZ2P+CrPrIa9F/Iog==");
            } else {
               String[] var26 = new String[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032530041, 4)];
               var26[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 6)] = this.stringValue(
                  this.readMapPathValue(var4, Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("pZoqreUuCt4sNxqOepJeOjLg05E+ZoIrOUCeeGWBQiukxZUq/ysu4VHt8tdq8wbNkB4="))
               );
               var26[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-292414651, 6)] = this.stringValue(
                  this.readMapPathValue(
                     var4, Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("yJO2ZTMmjpb7taq1sXW/kbTUTfNIPNgLpM5z7E2ekNQpZEhbPJV3+0ywRxugxpMUaKh3wwrfG2KmXFOIsgQ=")
                  )
               );
               var26[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1215174586, 16)] = this.stringValue(
                  this.readMapPathValue(
                     var4, Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("y7E2XQOH/B1oHNbN9rU7ZFjMwrph4gWIiClINW+8WVvI0gwQsQuiAsvG+0I6/nErY35mOQ/xFBk08QOMOwMeAQ==")
                  )
               );
               var26[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1709727201, 31)] = this.stringValue(
                  this.readMapPathValue(
                     var4,
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
                        "DLS6ipd8I18DEL0sRIitaem6uInabjOILrjJbRWeFIQ3EGVrtZe+CBYg4Tp9HLAyXdIHyt8/W9MCjZrAt/P2wgpwB5lXOd5BJsi42Q=="
                     )
                  )
               );
               var26[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032365766, 8)] = this.stringValue(
                  this.readMapPathValue(var4, Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("rJ5KY1f4H1nlpcpgI5fIVSELHaNC+9xPa/4bI4FfGF9OsRhB3hlZ6YQEJqGtkW4M"))
               );
               var26[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(875512897, 0)] = this.stringValue(
                  this.readMapPathValue(
                     var4, Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("FXXZRTCsHxFdde2SqMx2X/kOwAPQSrahWUgB1+CPf/ju57r57B8aOenoJC2Wn8QvGBFssxGjZ32PTXYp+PQ=")
                  )
               );
               var10000 = this.firstNonBlank(var26);
            }

            String var6 = var10000;
            String var7 = var6.isBlank()
               ? (
                  !Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("QUBOSaLWsd0g7cX/1BXTYkc2EqTkMY4NvgHMmIC6JStkD7Eck7w=").equals(var5) && !this.isRoundedType(var5)
                     ? Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("dVKrWpDuiC7eJE129LBcBdp9bCIMPX0PslSZD5+Ufdw6A7sF")
                     : Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("NdHAe7Dbn2K9jE5lqOJoXKtMceh3D08PuuxkazLhvhBoKybi")
               )
               : this.normalizeHoverColorSettingToken(var6);
            if (var4 == null) {
               var10000 = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nDGc1imw9eT8I5Ax9Ff6HdZ2P+CrPrIa9F/Iog==");
            } else {
               String[] var27 = new String[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-2048358330, 5)];
               var27[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(568435066, 19)] = this.stringValue(
                  this.readMapPathValue(var4, Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("p1BdhoSf/4Z/okLzwcJvK0oq/9e7fK0sJSN8Cqp348q3kz4x5ExyeiC++BaGC3zDStYLGQ=="))
               );
               var27[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1757270595, 27)] = this.stringValue(
                  this.readMapPathValue(
                     var4, Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("obYezPeKlV1B8ikjROjf9Yp5elqXaAWWUFvZhbLWMqbBuVkJJHH7VnRiHgnGossWpzfzS9b95lYvDidf2PP0ZQ==")
                  )
               );
               var27[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(198250381, 6)] = this.stringValue(
                  this.readMapPathValue(
                     var4, Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("MlrVBYa7vNQ/XrzLh0dbISbmJuRoGHZ6ghUdcp8Q4lQahlvTtzCT/fD/XS6SFbDHlhtpEPn/0eOxbM6jtn+C185G")
                  )
               );
               var27[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032792105, 17)] = this.stringValue(
                  this.readMapPathValue(
                     var4,
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
                        "mSpDBDJMLWY4dYYIRa+dsB4R675wtylFks1eSkaBE11U+qiMbrZGYj63gBwk82qGYzuOspFVjc4IEyCwinN/bopao/X01V8TUvUxYhd4"
                     )
                  )
               );
               var27[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529969, 3)] = this.stringValue(
                  this.readMapPathValue(var4, Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("tTEK+OzqQ4uwbAegrg2BLhcidAOa7QiHJCbp5Ve7imWb4vC1nqU7rA5doVZAI3qdCkk="))
               );
               var27[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(323155012, 28)] = this.stringValue(
                  this.readMapPathValue(
                     var4, Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("+u2rA2/EZbU4a/ZORVIO9YLp2gcpIqI6iayu4dCALXqFu1iU+2VNa51VpDnNWSzUKvRrUiMc+nhaouBGI/4ueA==")
                  )
               );
               var10000 = this.firstNonBlank(var27);
            }

            String var8 = var10000;
            String var9 = this.normalizeHoverEffectId(var8);
            EditorTimelineKeyframePart.HoverEffectRuntimeConfig var10 = !var9.isBlank()
                  && !Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("NdHAe7Dbn2K9jE5lqOJoXKtMceh3D08PuuxkazLhvhBoKybi").equals(var9)
               ? this.resolveHoverEffectRuntimeConfig(var9)
               : null;
            double var11 = Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-3106669607989028420L, 53));
            double var13 = Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882995010999286393L, 48));
            double var15 = Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-4132073693075901895L, 20));
            double var17 = Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(4637158883649305651L, 26));

            for (Xqedii_3j2w0l1 var20 : var2.Xqedii_2f37hi3.values()) {
               if (var20.Xqedii_1n6r5g3 != null && var3.equals(var20.Xqedii_3u5s934)) {
                  var11 = Math.min(var11, var20.Xqedii_1n6r5g3[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1144796207, 16)]);
                  var13 = Math.min(var13, var20.Xqedii_1n6r5g3[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1751025786, 1)]);
                  var15 = Math.max(var15, var20.Xqedii_1n6r5g3[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1215174586, 16)]);
                  var17 = Math.max(var17, var20.Xqedii_1n6r5g3[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-854863601, 30)]);
               }
            }

            double var23 = Double.isFinite(var11)
               ? (var11 + var15) / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-6397129084974051759L, 9))
               : Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673804245514L, 51));
            double var21 = Double.isFinite(var13)
               ? (var13 + var17) / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-6346453226102113850L, 28))
               : Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-1820088612636155941L, 56));
            return new HoverEffectTarget(var3, var7, var10, var23, var21);
         } else {
            return null;
         }
      }
   }

   public WorldDisplays.WorldElementSpec Xqedii_kniw8o1(Xqedii_1a2mt0h var1, Xqedii_3j2w0l1 var2, HoverEffectTarget var3) {
      if ((-1166230650 * -1166230650 + -1166230650 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         WorldDisplays.WorldElementSpec var4 = var2.Xqedii_tq2w5ap;
         String var5 = var4.text();
         if (var5 != null) {
            int var6 = var2.Xqedii_1p17s7a != null && !var2.Xqedii_1p17s7a.isBlank()
               ? Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1215109050, 16)
               : Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 28);
            String var7 = var6 != 0 ? var2.Xqedii_1p17s7a : var5;
            String[] var10001 = new String[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 16)];
            var10001[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 7)] = var3.colorSetting();
            String var8 = this.firstNonBlank(var10001);
            int var9 = !var8.equalsIgnoreCase(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("NdHAe7Dbn2K9jE5lqOJoXKtMceh3D08PuuxkazLhvhBoKybi"))
                  && !var8.equalsIgnoreCase(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("oMHysd+x7y6xtUV5dgAncW3gymObZ6BPkOf7G7NbMWT5hvRH"))
               ? Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 12)
               : Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-2048358490, 5);
            if (var9 != 0) {
               var5 = var7;
            } else {
               String var10 = this.normalizeHexColor(var8);
               var5 = var10.isBlank() ? this.brightenOrDarken(var7) : this.applyHoverColorToText(var7, var10);
            }
         }

         Vector3f var19 = var4.translation() == null ? new Vector3f() : new Vector3f(var4.translation());
         Vector3f var20 = var4.scale() == null
            ? new Vector3f(
               Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2034021574, 23)),
               Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1031560058, 28)),
               Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-196884678, 12))
            )
            : new Vector3f(var4.scale());
         int var21 = var4.opacity();
         EditorTimelineKeyframePart.HoverEffectRuntimeConfig var22 = var3.effect();
         if (var22 != null) {
            ParsedGuiLayout var23 = var1.Xqedii_3uo0d9i;
            double var11 = var1.Xqedii_2u9ms1f.Xqedii_2llkhpy / var23.pageWidth();
            float var13 = var1.Xqedii_2u9ms1f.Xqedii_1svt1aq != 0
               ? Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1411195958, 31))
               : Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1031560058, 28));
            float var14 = (float)(
               var13 * (var23.pageWidth() / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1878350605164083833L, 54)) - var3.pivotX()) * var11
            );
            float var15 = (float)(
               (var23.pageHeight() / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2370786072313727498L, 56)) - var3.pivotY()) * var11
            );
            float var16 = (float)var22.scaleXMultiplier();
            float var17 = (float)var22.scaleYMultiplier();
            var19.x = var14 + (var19.x - var14) * var16;
            var19.y = var15 + (var19.y - var15) * var17;
            var19.x = var19.x + (float)(-var13 * var22.offsetX() * var11);
            var19.y = var19.y + (float)(-var22.offsetY() * var11);
            var20.x *= var16;
            var20.y *= var17;
            if (!var4.isItem()) {
               int var18 = var22.opacityAbsolute() ? var22.opacityAbsoluteValue() : var21;
               var18 += var22.opacityDelta();
               var18 += (int)Math.round(
                  var21 * (var22.opacityPercentDelta() / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771674881722890L, 32)))
               );
               var21 = this.clampHoverEffectOpacity(var18);
            }
         }

         return new WorldDisplays.WorldElementSpec(
            var5,
            var4.item(),
            var19,
            var20,
            var4.leftRotation(),
            var21,
            var4.lineWidth(),
            var4.alignment(),
            var4.blockLight(),
            var4.skyLight(),
            var4.billboard()
         );
      }
   }

   public void Xqedii_2r08i89(Xqedii_1a2mt0h var1) {
      if ((-1734179160 ^ -1734179160) != 0) {
         throw new IllegalStateException();
      } else {
         for (Xqedii_2f78jo3 var3 : var1.Xqedii_2zsng32.values()) {
            Player var4 = Bukkit.getPlayer(var3.Xqedii_rjd91hv);
            if (var4 != null && var4.isOnline()) {
               double[] var5 = this.Xqedii_2wqyptb(var1, var4);
               String var6 = var5 == null
                  ? null
                  : this.Xqedii_1m21tbj(
                     var3, var5[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1122963248, 10)], var5[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(1495495878, 29)]
                  );
               if (!Objects.equals(var6, var3.Xqedii_2xzilb5)) {
                  var3.Xqedii_2xzilb5 = var6;
                  this.Xqedii_1q63spb(var1, var4, var3, Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-2005374882, 17));
               }
            }
         }
      }
   }

   public double[] Xqedii_2wqyptb(Xqedii_1a2mt0h var1, Player var2) {
      if ((-611846689 * -611846689 + -611846689 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Location var3 = var1.Xqedii_2lkymh6;
         if (var3 != null && var3.getWorld() != null && var3.getWorld().equals(var2.getWorld())) {
            Xqedii_irwktj3 var4 = var1.Xqedii_2u9ms1f;
            ParsedGuiLayout var5 = var1.Xqedii_3uo0d9i;
            Location var6 = var2.getEyeLocation();
            double var7 = var4.Xqedii_2llkhpy / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854153251846777L, 36));
            double var9 = var4.Xqedii_27dvef7 + var7;
            if (var6.distanceSquared(var3) > var9 * var9) {
               return null;
            } else {
               double var11;
               double var13;
               if (var4.Xqedii_1svt1aq == 0) {
                  var11 = var3.getYaw();
                  var13 = Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673804245514L, 18));
               } else if (var4.Xqedii_1svt1aq == Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-780426106, 28)) {
                  var11 = var6.getYaw();
                  var13 = Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-913187932901916826L, 47));
               } else {
                  var11 = var6.getYaw();
                  var13 = var6.getPitch();
               }

               double var15 = Math.toRadians(var11);
               double var17 = Math.toRadians(var13);
               double var19 = Math.sin(var15);
               double var21 = Math.cos(var15);
               double var23 = Math.sin(var17);
               double var25 = Math.cos(var17);
               Vector var27 = new Vector(var21, Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854204791454329L, 20)), var19);
               Vector var28 = new Vector(-var23 * var19, var25, var23 * var21);
               Vector var29 = new Vector(-var25 * var19, -var23, var25 * var21);
               Vector var30 = var6.getDirection();
               double var31 = var30.dot(var29);
               if (var31 <= Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-7172974462836859158L, 48))) {
                  return null;
               } else {
                  Vector var33 = var6.toVector();
                  double var34 = var3.toVector().subtract(var33).dot(var29) / var31;
                  if (!(var34 < Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673804245514L, 59))) && !(var34 > var4.Xqedii_27dvef7)) {
                     Vector var36 = var33.add(var30.multiply(var34)).subtract(var3.toVector());
                     double var37 = var4.Xqedii_2llkhpy / var5.pageWidth();
                     double var39 = var5.pageWidth() / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673804249610L, 14))
                        - var36.dot(var27) / var37;
                     double var41 = var5.pageHeight() / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-6346453226102113850L, 28))
                        - var36.dot(var28) / var37;
                     if (!(var39 < Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854204791454329L, 3)))
                        && !(var39 > var5.pageWidth())
                        && !(var41 < Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854204791454329L, 17)))
                        && !(var41 > var5.pageHeight())) {
                        double[] var10000 = new double[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032364742, 11)];
                        var10000[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(793001012, 8)] = var39;
                        var10000[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366806, 4)] = var41;
                        var10000[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(398084206, 0)] = var34;
                        return var10000;
                     } else {
                        return null;
                     }
                  } else {
                     return null;
                  }
               }
            }
         } else {
            return null;
         }
      }
   }

   public String Xqedii_1m21tbj(Xqedii_2f78jo3 var1, double var2, double var4) {
      if ((1067731638 * (1067731638 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         ElementBounds var6 = null;

         for (ElementBounds var8 : var1.Xqedii_2aq4qhk) {
            if (var8.contains(var2, var4) && (var6 == null || var8.layer() > var6.layer() || var8.layer() == var6.layer() && var8.order() > var6.order())) {
               var6 = var8;
            }
         }

         return var6 != null && !var6.targetPath().isBlank() ? var6.targetPath() : null;
      }
   }

   public Xqedii_3ep50li Xqedii_1pwcmxy(Player var1) {
      if ((289283735 * 289283735 - 289283735 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_3ep50li var2 = null;

         for (Xqedii_1a2mt0h var4 : this.Xqedii_35obddf.values()) {
            if (var4.Xqedii_2u9ms1f.Xqedii_1fdepr5) {
               Xqedii_2f78jo3 var5 = var4.Xqedii_2zsng32.get(var1.getUniqueId());
               if (var5 != null) {
                  double[] var6 = this.Xqedii_2wqyptb(var4, var1);
                  if (var6 != null && (var2 == null || var6[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-2047962597, 30)] < var2.distance())) {
                     var2 = new Xqedii_3ep50li(
                        var4,
                        var5,
                        var6[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-254755568, 26)],
                        var6[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1575052777, 15)],
                        var6[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-383666082, 10)]
                     );
                  }
               }
            }
         }

         return var2;
      }
   }

   public boolean Xqedii_2fzgi4z(Player var1) {
      if ((1717200537 * 1717200537 + 1717200537 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.isOnline()) {
         Xqedii_3ep50li var2 = this.Xqedii_1pwcmxy(var1);
         if (var2 == null) {
            return (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(568435066, 19) != 0);
         } else {
            long var3 = Xqedii_38o2zes.Xqedii_316oplq();
            Long var5 = this.Xqedii_18wixnx.get(var1.getUniqueId());
            if (var5 != null && var3 - var5 < Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-6343607393703480315L, 38)) {
               return (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1585831447, 3) != 0);
            } else {
               this.Xqedii_18wixnx.put(var1.getUniqueId(), var3);
               Xqedii_1a2mt0h var6 = var2.instance();
               String[] var10001 = new String[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 6)];
               var10001[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 4)] = this.Xqedii_1m21tbj(var2.viewer(), var2.pageX(), var2.pageY());

               for (String var7 = this.firstNonBlank(var10001).trim(); !var7.isBlank(); var7 = this.trimTrailingChildrenPathSegment(var7)) {
                  if (this.Xqedii_4ewavsc(var1, var6, var2.viewer(), var7)) {
                     return (Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1110293656, 3) != 0);
                  }

                  List var8 = this.Xqedii_iwuh552(var6.Xqedii_3uo0d9i, var7);
                  if (!var8.isEmpty()) {
                     Xqedii_1avxacb var9 = this.Xqedii_322y3to(var6, var2.viewer());
                     this.executeRuntimeTargetActions(
                        var1,
                        this.Xqedii_3ctvdu4(var8, this.Xqedii_22qy351(var6.Xqedii_m23ejo5, var9.Xqedii_zayz2bu)),
                        Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 21)
                     );
                     return (Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-146207326, 5) != 0);
                  }
               }

               return (Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1843706386, 14) != 0);
            }
         }
      } else {
         return (Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 19) != 0);
      }
   }

   public double[] Xqedii_3sfh1lz(Player var1, ConfigurationSection var2, String var3) {
      if ((2115947524 * (2115947524 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         double var4 = this.readRuntimeNumericValue(
            var1,
            null,
            var2,
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("vEn2zB8nYJHoX05lP56zAfoza9oRury0G3HmdUk5/ThsJTNonjmL37xEraKPnWu4"),
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("Zf54ATWPU77gaSIWdps+ZQ+ptHdu6cvg0DkUUHFa"),
            Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854204791454329L, 9))
         );
         double var6 = this.readRuntimeNumericValue(
            var1,
            null,
            var2,
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("LBEKws5O+yiqhm2o4wD4IpUIdGCQcJenpfs2iVLxpzyCH/HGsMQBUcmaIKrbB0vl"),
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("dAEqJt225Ersk+8JRtlwgDccO5NbebQTGSR2pjIP"),
            Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673804245514L, 1))
         );
         double var8 = Math.max(
            Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854204791847673L, 19)),
            Math.abs(
               this.readRuntimeNumericValue(
                  var1,
                  null,
                  var2,
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("P0WWMxf6yCnoExUlfCcL3lOQ2YHSwsF15BE8gbGlkk9Cs6i6B4oYUyfBIP/R8fyZ"),
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("g8GtoqXdXB5EO2LPAVxVRCZrSKKIS9Yc8doeqVwZrKAj7WNOoz0="),
                  this.readRuntimeNumericValue(
                     var1,
                     null,
                     var2,
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("i8ca8hUrHncCCOZAa8IhsE4ZOhvXSZc+yK0SENKKzQc5yUcLOzWY2QbYHLYRCxa/Kh0="),
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("g8GtoqXdXB5EO2LPAVxVRCZrSKKIS9Yc8doeqVwZrKAj7WNOoz0="),
                     Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854206658706041L, 32))
                  )
               )
            )
         );
         double var10 = Math.max(
            Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(8998110561065686011L, 53)),
            Math.abs(
               this.readRuntimeNumericValue(
                  var1,
                  null,
                  var2,
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("jGiXKspyoCqYF0rvuQyElKnJRW1d/kXtaj64OmHvMpo7MpdDulKzOldlLiVbYj858nw="),
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("g/ideE+bM2slwM/t1l/B8XqY6rFvc9+6/wImKlen4hIqLS35YVnm9A=="),
                  this.readRuntimeNumericValue(
                     var1,
                     null,
                     var2,
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("JH5VPL++EZU6MuQb5Tvg+VYOlNnZgfrVFv/ZEzHFgI6Jy6iE7IDeOcNHGucRaVXkQx2sXw=="),
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("g/ideE+bM2slwM/t1l/B8XqY6rFvc9+6/wImKlen4hIqLS35YVnm9A=="),
                     Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(1630263375280934096L, 32))
                  )
               )
            )
         );
         if (this.isPlayerHeadTextSection(var2)) {
            var10 = var8;
         } else if (Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("aAoqzBMiPDCcz//+WzYE1ohaKhOcljxRgstIMTGUV7HMhH4n").equals(var3)) {
            var10 = Math.max(
               Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673797413386L, 26)),
               var10 * Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(7269651042767507021L, 25))
            );
         }

         double[] var10000 = new double[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032235718, 15)];
         var10000[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-2097723123, 29)] = var4;
         var10000[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1292620047, 30)] = var6;
         var10000[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-120537840, 26)] = var4 + var8;
         var10000[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(875512895, 0)] = var6 + var10;
         return var10000;
      }
   }

   public static boolean Xqedii_pzw30wg(ConfigurationSection var0) {
      if (((1981591564 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String var1 = var0 == null ? null : var0.getString(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("aAoqzBMiPDCcz//+WzYE1ohaKhOcljxRgstIMTGUV7HMhH4n"));
         return ((var1 != null
               && var1.contains(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nnikQX134G13feoBIuG8I4FsKnWfl+vvbATHteePmy1oyJkWRxT6o0SSU6l+Ej35rbBoh5sFZUT72g=="))
            ? Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 30)
            : Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-393771387, 13)) != 0);
      }
   }

   public static boolean Xqedii_b9cpusm(ConfigurationSection var0, String var1) {
      if ((316780552 * (316780552 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return ((var0 == null
               || !Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("X8Ju+s5pZ2WgpSkF1meIUEr8dT1TgGvqudI+5ERbAaVM44tDYcdy5g==").equals(var1)
               || !var0.contains(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("XggXTl1FqH044pcdqbkD0keUp81WicML3raLvVYOM3R6rnRPS1H4Qff56qrT/88qrqBSWg=="))
                  && !var0.contains(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nr20bXfoUcDwOYIRdHLUWlu9GgZV+xoTKO888WVSKxLt846IS4qqJ+wEaHN6b6WWNmY="))
            ? Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 29)
            : Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1130937810, 19)) != 0);
      }
   }

   public boolean Xqedii_3h4d3xi(ConfigurationSection var1, double[] var2) {
      if (((-1700071894 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null
         && var1.contains(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("KuvBTzE3jc2r/4J7aTRjFQdbMY/IQ2h5kd7BKbNyYHPVMrPP/uwi1e5wAbs2rLr5VicO3p32Cj9rZA=="))) {
         double var3 = var1.getDouble(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("cJAUR+VPqH1VxT0Jb1Odq/Vz6B7UOizRqIrI2NxO15BBnZxzMSMNGVkJ3Eh1wmxVi6JGzXWmFbWgL88q"),
            Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854204858546809L, 27))
         );
         double var5 = var1.getDouble(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("Alg7a7EpuDplZPr1cDsnMGTaOx78BhSkcU2T6DuSTM5t+fkpNe4aSP6AWOStyhBbWQ9oVXPAkJQ="),
            Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-7424504822992568465L, 58))
         );
         if (Double.isFinite(var3) && Double.isFinite(var5)) {
            int var7 = var1.getInt(
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("KuvBTzE3jc2r/4J7aTRjFQdbMY/IQ2h5kd7BKbNyYHPVMrPP/uwi1e5wAbs2rLr5VicO3p32Cj9rZA=="),
                     Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(-2032366791, 14)
                  )
                  == Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-2033091676, 20)
               ? Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-780426106, 28)
               : Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 29);
            double var8 = var7 != 0 ? var2[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 8)] : var2[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-2048358490, 5)];
            double var10 = var7 != 0 ? var2[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2030269638, 20)] : var2[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(570007930, 19)];
            return ((!(var8 < Math.min(var3, var5) - Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673803717130L, 23)))
                  && !(var10 > Math.max(var3, var5) + Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-2327268530831754372L, 61)))
               ? Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1335957215, 26)
               : Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-787526389, 14)) != 0);
         } else {
            return (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1010052932, 24) != 0);
         }
      } else {
         return (Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 25) != 0);
      }
   }

   public Xqedii_3kjd61w Xqedii_3jnizuu(
      Xqedii_1a2mt0h var1, Player var2, ConfigurationSection var3, String var4, double var5, Map<String, String> var7, String var8
   ) {
      if ((-1971699705 * -1971699705 + -1971699705 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         boolean var9 = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("aAoqzBMiPDCcz//+WzYE1ohaKhOcljxRgstIMTGUV7HMhH4n").equals(var4);
         boolean var10 = this.isInputType(var4);
         if (!Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("X8Ju+s5pZ2WgpSkF1meIUEr8dT1TgGvqudI+5ERbAaVM44tDYcdy5g==").equals(var4) && !this.isSkinType(var4)) {
            int var11 = this.applyVisibilityConditionOpacity(var2, null, var3, this.readOpacity(var3, Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(308475721, 21)));
            if (var10) {
               this.applyInputBackgroundAlias(var3);
               var11 = this.readInputBackgroundOpacity(var3, var11);
            }

            if (!this.shouldSpawnOpacityVisual(var4, var11)) {
               return null;
            } else {
               ParsedGuiLayout var12 = var1.Xqedii_3uo0d9i;
               double var13 = this.readRuntimeNumericValue(
                  var2,
                  null,
                  var3,
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("vEn2zB8nYJHoX05lP56zAfoza9oRury0G3HmdUk5/ThsJTNonjmL37xEraKPnWu4"),
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("Zf54ATWPU77gaSIWdps+ZQ+ptHdu6cvg0DkUUHFa"),
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-8270360816153367946L, 4))
               );
               double var15 = this.readRuntimeNumericValue(
                  var2,
                  null,
                  var3,
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("LBEKws5O+yiqhm2o4wD4IpUIdGCQcJenpfs2iVLxpzyCH/HGsMQBUcmaIKrbB0vl"),
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("dAEqJt225Ersk+8JRtlwgDccO5NbebQTGSR2pjIP"),
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854204791454329L, 61))
               );
               double var17 = this.readRuntimeNumericValue(
                  var2,
                  null,
                  var3,
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("P0WWMxf6yCnoExUlfCcL3lOQ2YHSwsF15BE8gbGlkk9Cs6i6B4oYUyfBIP/R8fyZ"),
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("g8GtoqXdXB5EO2LPAVxVRCZrSKKIS9Yc8doeqVwZrKAj7WNOoz0="),
                  this.readRuntimeNumericValue(
                     var2,
                     null,
                     var3,
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("i8ca8hUrHncCCOZAa8IhsE4ZOhvXSZc+yK0SENKKzQc5yUcLOzWY2QbYHLYRCxa/Kh0="),
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("g8GtoqXdXB5EO2LPAVxVRCZrSKKIS9Yc8doeqVwZrKAj7WNOoz0="),
                     Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854207735331449L, 33))
                  )
               );
               double var19 = this.readRuntimeNumericValue(
                  var2,
                  null,
                  var3,
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("jGiXKspyoCqYF0rvuQyElKnJRW1d/kXtaj64OmHvMpo7MpdDulKzOldlLiVbYj858nw="),
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("g/ideE+bM2slwM/t1l/B8XqY6rFvc9+6/wImKlen4hIqLS35YVnm9A=="),
                  this.readRuntimeNumericValue(
                     var2,
                     null,
                     var3,
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("JH5VPL++EZU6MuQb5Tvg+VYOlNnZgfrVFv/ZEzHFgI6Jy6iE7IDeOcNHGucRaVXkQx2sXw=="),
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("g/ideE+bM2slwM/t1l/B8XqY6rFvc9+6/wImKlen4hIqLS35YVnm9A=="),
                     Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673804261950L, 16))
                  )
               );
               double var21 = Math.max(Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(923262799455911343L, 16)), Math.abs(var17));
               double var23 = Math.max(Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771681052002826L, 40)), Math.abs(var19));
               if (this.isPlayerHeadTextSection(var3)) {
                  var23 = var21;
               }

               TextAlignment var25 = var9 ? this.readTextAlignment(var3) : TextAlignment.CENTER;
               int var26 = var9 ? this.readTextWrapLineWidth(var3) : Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1979160482, 17);
               double var27 = var9 ? this.applyTextAlignmentOffset(var13, var21, var25) : var13;
               double var29 = var9 ? this.toInternalTextTopY(var15, var23) : var15;
               ItemStack var31 = !var9 && !var10 ? this.resolveItemDisplayStack(var2, var3, var4) : null;
               int var32 = var31 != null && !this.isRoundedType(var4)
                  ? Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(457567890, 22)
                  : Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-2021227032, 21);
               if (var32 != 0) {
                  var29 -= var23 * Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-5371906504275829900L, 60));
                  double[] var33 = this.resolveItemDisplayRotationOffset(
                     Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673804245514L, 45)), var21, var23
                  );
                  var27 += var33[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 24)];
                  var29 += var33[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(915135780, 23)];
                  double[] var34 = this.resolveItemDisplayNonBlockRotationOffset(
                     Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(6031562135333463795L, 55)), var21, var23
                  );
                  var27 += var34[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(198250253, 6)];
                  var29 += var34[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(875512893, 0)];
               }

               HudPositionCalculator.Placement var50 = this.positionCalculator.calculateBoxPlacement(var27, var29, var5, var21, var23);
               Vector var51 = var32 != 0 ? this.resolveItemDisplayScale(var50.scale()) : var50.scale();
               double var35 = var1.Xqedii_2u9ms1f.Xqedii_2llkhpy / var12.pageWidth();
               double var37 = Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854204791454329L, 6));
               if (var32 != 0) {
                  var37 = var31.getType().isBlock()
                     ? var51.getZ() / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771675951729162L, 33))
                     : var51.getZ() / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352762843351484938L, 45));
               }

               int var39 = var1.Xqedii_2u9ms1f.Xqedii_1svt1aq;
               int var40 = var39 != 0 ? Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 31) : Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-2034140252, 20);
               float var41 = var40 != 0
                  ? Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1349816537, 1))
                  : Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1631703619, 27));
               Vector3f var42 = new Vector3f(
                  var41
                     * (float)(
                        (var12.pageWidth() / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2351645773897402890L, 52)) - var50.location().getX())
                           * var35
                     ),
                  (float)(
                     (var12.pageHeight() / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854204816620153L, 25)) - var50.location().getY())
                        * var35
                  ),
                  var41 * (float)(var37 * var35 - var5 * var1.Xqedii_2u9ms1f.Xqedii_1b6pbfy)
               );
               Vector3f var43 = new Vector3f((float)(var51.getX() * var35), (float)(var51.getY() * var35), (float)(var51.getZ() * var35));
               int var44 = var1.Xqedii_2u9ms1f.Xqedii_m4m2ywj;
               if (var32 != 0) {
                  Quaternionf var52 = var40 != 0 ? new Quaternionf(Xqedii_19xeymz) : new Quaternionf();
                  return new Xqedii_3kjd61w(
                     new WorldDisplays.WorldElementSpec(
                        null,
                        var31,
                        var42,
                        var43,
                        var52,
                        Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1116416041, 23),
                        Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 26),
                        null,
                        var44,
                        var44,
                        var39
                     )
                  );
               } else {
                  String var45 = var8 != null
                     ? this.applyPreferredFont(
                        var8,
                        var3,
                        ((!var9 ? Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 18) : Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-393771387, 13)) != 0),
                        ((!var9 ? Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(875512893, 0) : Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(607521757, 15)) != 0)
                     )
                     : this.Xqedii_1q39kc0(var2, var3, var4, var7);
                  if (var45 != null && !var45.isBlank()) {
                     Quaternionf var46 = var40 != 0 ? new Quaternionf() : new Quaternionf(Xqedii_19xeymz);
                     return new Xqedii_3kjd61w(new WorldDisplays.WorldElementSpec(var45, null, var42, var43, var46, var11, var26, var25, var44, var44, var39));
                  } else {
                     return null;
                  }
               }
            }
         } else {
            return null;
         }
      }
   }

   public String Xqedii_1q39kc0(Player var1, ConfigurationSection var2, String var3, Map<String, String> var4) {
      if ((266575765 * 266575765 + 266575765 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         boolean var5 = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("aAoqzBMiPDCcz//+WzYE1ohaKhOcljxRgstIMTGUV7HMhH4n").equals(var3);
         String var6;
         if (var5) {
            String[] var10001 = new String[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2040755398, 22)];
            var10001[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 5)] = var2.getString(
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("aAoqzBMiPDCcz//+WzYE1ohaKhOcljxRgstIMTGUV7HMhH4n")
            );
            var10001[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1110293656, 3)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nDGc1imw9eT8I5Ax9Ff6HdZ2P+CrPrIa9F/Iog==");
            var6 = this.firstNonBlank(var10001);
         } else if (this.isInputType(var3)) {
            String[] var9 = new String[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1575020009, 15)];
            var9[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-509511135, 27)] = var2.getString(
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("h7dstSA1pEhNuICg3ZSxwd7y9QDiEOurrsz3gKf3iiMcjwzOxTb5aEqX")
            );
            var9[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 30)] = this.readInputRounded(var2)
               ? Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("T/9eR5AHiRSIrqdpY5Qhi2YkeryuiDtwwN2VYE+j")
               : Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nDGc1imw9eT8I5Ax9Ff6HdZ2P+CrPrIa9F/Iog==");
            var6 = this.firstNonBlank(var9);
         } else {
            String[] var10 = new String[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1036724265, 21)];
            var10[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1019022269, 28)] = var2.getString(
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("h7dstSA1pEhNuICg3ZSxwd7y9QDiEOurrsz3gKf3iiMcjwzOxTb5aEqX")
            );
            var10[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-792915724, 2)] = var2.getString(
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("aAoqzBMiPDCcz//+WzYE1ohaKhOcljxRgstIMTGUV7HMhH4n")
            );
            var10[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(923524388, 23)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("T/9eR5AHiRSIrqdpY5Qhi2YkeryuiDtwwN2VYE+j");
            var6 = this.firstNonBlank(var10);
         }

         if (var6.isBlank()) {
            return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nDGc1imw9eT8I5Ax9Ff6HdZ2P+CrPrIa9F/Iog==");
         } else {
            String[] var10003 = new String[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1015752745, 24)];
            var10003[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 16)] = var2.getString(
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("rofvh8of6Tr4eDPhgw7dHS+IwvAfBv2VMnwZf4j16cmrg0nVWKg=")
            );
            var10003[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1225630455, 13)] = var2.getString(
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("UAtaWP3mHO0tQd2fK1cQ94wQU45GDXkteF3+RuWpBEnWfGXZnvxNQcN3PUcz4HkGU40=")
            );
            var6 = this.withHexPrefix(var6, this.firstNonBlank(var10003));
            String var7 = this.Xqedii_2b50ydw(this.resolvePlaceholders(var1, var6, (Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(906747172, 23) != 0)), var4);
            return this.applyPreferredFont(
               var7,
               var2,
               ((!var5 ? Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032367302, 9) : Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1592336816, 2)) != 0),
               ((!var5 ? Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 30) : Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1122963248, 10)) != 0)
            );
         }
      }
   }

   public String Xqedii_hukkpjv(Player var1, ConfigurationSection var2, Map<String, String> var3) {
      if (((-1824766319 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String var4 = var2.getString(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("sRse7AQdEtjSCfn9XGZkxRaOMuhNdMwqzRkCtlwWw8Ds7noBmJKTzYbkuRoEtqgh"));
         if (var4 != null && !var4.isBlank()) {
            String[] var10003 = new String[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1033578537, 20)];
            var10003[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(284217533, 18)] = var2.getString(
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("rofvh8of6Tr4eDPhgw7dHS+IwvAfBv2VMnwZf4j16cmrg0nVWKg=")
            );
            var10003[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1268848351, 26)] = var2.getString(
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("UAtaWP3mHO0tQd2fK1cQ94wQU45GDXkteF3+RuWpBEnWfGXZnvxNQcN3PUcz4HkGU40=")
            );
            String var5 = this.withHexPrefix(var4, this.firstNonBlank(var10003));
            return this.Xqedii_2b50ydw(this.resolvePlaceholders(var1, var5, (Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 5) != 0)), var3);
         } else {
            return null;
         }
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public Xqedii_3kjd61w Xqedii_3t8puw7(Xqedii_1a2mt0h var1, Player var2, Xqedii_1avxacb var3, ConfigurationSection var4, String var5, double var6) {
      if ((-1716879132 ^ -1716879132) != 0) {
         throw new IllegalStateException();
      } else {
         int var8 = this.applyVisibilityConditionOpacity(var2, null, var4, this.readOpacity(var4, Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(846518224, 27)));
         int var9 = this.readInputTextOpacity(var4, var8);
         if (!this.shouldSpawnOpacityVisual(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("aAoqzBMiPDCcz//+WzYE1ohaKhOcljxRgstIMTGUV7HMhH4n"), var9)) {
            return null;
         } else {
            double var10 = this.readRuntimeNumericValue(
               var2,
               null,
               var4,
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("vEn2zB8nYJHoX05lP56zAfoza9oRury0G3HmdUk5/ThsJTNonjmL37xEraKPnWu4"),
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("Zf54ATWPU77gaSIWdps+ZQ+ptHdu6cvg0DkUUHFa"),
               Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673804245514L, 50))
            );
            double var12 = this.readRuntimeNumericValue(
               var2,
               null,
               var4,
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("LBEKws5O+yiqhm2o4wD4IpUIdGCQcJenpfs2iVLxpzyCH/HGsMQBUcmaIKrbB0vl"),
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("dAEqJt225Ersk+8JRtlwgDccO5NbebQTGSR2pjIP"),
               Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-689273689260367021L, 35))
            );
            double var14 = Math.max(
               Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-3099149662470387912L, 25)),
               Math.abs(
                  this.readRuntimeNumericValue(
                     var2,
                     null,
                     var4,
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("P0WWMxf6yCnoExUlfCcL3lOQ2YHSwsF15BE8gbGlkk9Cs6i6B4oYUyfBIP/R8fyZ"),
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("g8GtoqXdXB5EO2LPAVxVRCZrSKKIS9Yc8doeqVwZrKAj7WNOoz0="),
                     this.readRuntimeNumericValue(
                        var2,
                        null,
                        var4,
                        Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("i8ca8hUrHncCCOZAa8IhsE4ZOhvXSZc+yK0SENKKzQc5yUcLOzWY2QbYHLYRCxa/Kh0="),
                        Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("g8GtoqXdXB5EO2LPAVxVRCZrSKKIS9Yc8doeqVwZrKAj7WNOoz0="),
                        Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-8761740637126820103L, 15))
                     )
                  )
               )
            );
            double var16 = Math.max(
               Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(8042292847774292607L, 50)),
               Math.abs(
                  this.readRuntimeNumericValue(
                     var2,
                     null,
                     var4,
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("jGiXKspyoCqYF0rvuQyElKnJRW1d/kXtaj64OmHvMpo7MpdDulKzOldlLiVbYj858nw="),
                     Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("g/ideE+bM2slwM/t1l/B8XqY6rFvc9+6/wImKlen4hIqLS35YVnm9A=="),
                     this.readRuntimeNumericValue(
                        var2,
                        null,
                        var4,
                        Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("JH5VPL++EZU6MuQb5Tvg+VYOlNnZgfrVFv/ZEzHFgI6Jy6iE7IDeOcNHGucRaVXkQx2sXw=="),
                        Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("g/ideE+bM2slwM/t1l/B8XqY6rFvc9+6/wImKlen4hIqLS35YVnm9A=="),
                        Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2355030620443511306L, 53))
                     )
                  )
               )
            );
            String var18 = this.Xqedii_3djiqrw(var2, var1, var3, var4, var5, var14);
            if (var18.isBlank()) {
               return null;
            } else {
               double var19 = this.readInputTextSize(var4);
               double var21 = this.readInputTextPadding(var4);
               TextAlignment var23 = this.readInputTextAlignment(var4);
               double var24 = Math.max(
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(8766773208500838323L, 46)),
                  var19 * Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(-2710287579876173733L, 0))
               );

               double var26 = switch (dev.xqedii.ultimateUI.Xqedii_31ld99d.Xqedii_1qqsld8[var23.ordinal()]) {
                  case 1 -> var10 + (var14 - var19) / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(1882854204816620153L, 25));
                  case 2 -> var10 + var14 - var21;
                  default -> var10 + var21;
               };
               LinkedHashMap var28 = new LinkedHashMap();
               var28.put(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("i/RtKce25ylrvsCfcFlGNAEvFfaT/GrAlKdToRvQdld00+yQ"),
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("aAoqzBMiPDCcz//+WzYE1ohaKhOcljxRgstIMTGUV7HMhH4n")
               );
               var28.put(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("aAoqzBMiPDCcz//+WzYE1ohaKhOcljxRgstIMTGUV7HMhH4n"), var18);
               var28.put(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("VnT7z3WbE7MSffp4vG1RH+7K4pIrCnNOeK+JC2XD58KFPe4tfpBEVpip"), var9);
               var28.put(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("w16gsXnfXUVW8hbMMcQpQe7elavEbVh6VRyet2CDVMUwf53BA8s="),
                  var23 == TextAlignment.CENTER
                     ? Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("SuU9ZJsiBXDXdaH9uik1DsQCHh808Z02toBgfU2cmL4HGoazEkkeuw==")
                     : (
                        var23 == TextAlignment.RIGHT
                           ? Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("wyUpKmcWryfy2Yu4OE/kf3AllEp5R+VIH6jvhZTbn0pjb/hEEMQ=")
                           : Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("sszaURBLgzBwZtJV7bKEHGzE568j8sVE4otHvFXzA+Q19fXD")
                     )
               );
               LinkedHashMap var29 = new LinkedHashMap();
               var29.put(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("Zf54ATWPU77gaSIWdps+ZQ+ptHdu6cvg0DkUUHFa"), var26);
               var29.put(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("dAEqJt225Ersk+8JRtlwgDccO5NbebQTGSR2pjIP"),
                  var12 + (var16 - var24) / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(7497173888099344262L, 23))
               );
               var28.put(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("ClWGg82Q16AKaY3NBOSo0SkMpHD9MOAvvACYOMAFie8PNFQoVMdj/GnNVMQ="), var29);
               LinkedHashMap var30 = new LinkedHashMap();
               var30.put(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("g8GtoqXdXB5EO2LPAVxVRCZrSKKIS9Yc8doeqVwZrKAj7WNOoz0="), var19);
               var30.put(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("g/ideE+bM2slwM/t1l/B8XqY6rFvc9+6/wImKlen4hIqLS35YVnm9A=="), var19);
               var28.put(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("eZuG5ut4C3vV2FZNIm/7cZHy+ZyzSd+4P9ELZgM8F3W0WClt"), var30);
               String var31 = this.readInputFont(var4);
               if (!var31.isBlank()) {
                  var28.put(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("6bKy9Wf3Zjd2E6wJ1CO77hF8I40qLZ6KsHLzQ4+l4lpWEjYi"), var31);
               }

               return this.Xqedii_3jnizuu(
                  var1,
                  var2,
                  this.mapToSection(var28),
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("aAoqzBMiPDCcz//+WzYE1ohaKhOcljxRgstIMTGUV7HMhH4n"),
                  var6 + Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(5669582474695252941L, 57)),
                  Collections.emptyMap(),
                  var18
               );
            }
         }
      }
   }

   public String Xqedii_3djiqrw(Player var1, Xqedii_1a2mt0h var2, Xqedii_1avxacb var3, ConfigurationSection var4, String var5, double var6) {
      if ((-558264874 * (-558264874 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var8 = this.resolveInputId(var4, var5);
         String var9 = this.resolveInputVariable(var4, var8);
         AnvilInputState var10 = var3.Xqedii_h3mynbh.get(var5);
         int var11 = var10 != null ? Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 8) : Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 3);
         String var12 = var11 != 0 ? var10.text() : this.Xqedii_je53k53(var1, var2, var3, var4, var8, var9);
         if (var12.isEmpty() && var11 == 0) {
            String[] var20 = new String[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1060384580, 24)];
            var20[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(607521757, 15)] = var4.getString(
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("KxLDg5NJOEfzW45j92Ur7iNbu4VDfyA9hj664EIqw6nm2Gv4y0CLCr9se+QwrluMw4k=")
            );
            var20[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032891078, 19)] = var4.getString(
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("rWUcNLWUheNwp9MUmAMfwJf3/KHxbcokLIKJcbSiDog6D2jSQVoe2+nFDkxnVHQux6u75nlnr9NvdTiv")
            );
            var20[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529929, 5)] = var4.getString(
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("2Slf2Z/YxsfExhbre+aGs/PmtpM7+LXJ9wm9OZooLFi1eWLn")
            );
            String var19 = this.firstNonBlank(var20);
            if (var19.isBlank()) {
               return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nDGc1imw9eT8I5Ax9Ff6HdZ2P+CrPrIa9F/Iog==");
            } else {
               var20 = new String[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366788, 31)];
               var20[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-2021227032, 21)] = var4.getString(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("VLV0lFU9FtS1gPZiAUL/pgqNX7yhlLZWQ1KnLcAVUWTmYfXYMjA8qpzgLWSUcRXsb6btuY/SE70jTKDIo/8=")
               );
               var20[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(565468905, 18)] = var4.getString(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("9o+CLqi0biWnv3b8D0KRI9Jed1FJasU8IKhXpk903/1IIPfkjwOD2sfqXkOkhdLXdFHoAZnZ+T2pPKfpjmaER0XuAAf8eTBz")
               );
               var20[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529929, 5)] = var4.getString(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("IEMwFHUWIm9OY0piWeWzWm1CA+6N54Asrm22pdepZ818/Q4nM6KiJE7rVzPo6t3pGp1adeXfOO4W7/DA")
               );
               var20[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(796168414, 1)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
                  "TbGE/FKaWFjwe9FIPHngFlo8bibgNz3FAbzO6tDMqFO+fuLQY2AMoA=="
               );
               String var14 = this.firstNonBlank(var20);
               String[] var10002 = new String[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032399558, 14)];
               var10002[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 25)] = this.resolvePlaceholders(
                  var1, var19, (Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 18) != 0)
               );
               var10002[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(1763931334, 28)] = var19;
               return this.withHexPrefix(this.firstNonBlank(var10002), var14);
            }
         } else {
            double var13 = this.readInputTextPadding(var4);
            String var15 = var11 != 0 ? this.readInputCaret(var4) : Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nDGc1imw9eT8I5Ax9Ff6HdZ2P+CrPrIa9F/Iog==");
            String var16 = this.fitInputValueToBox(
               var12,
               var15,
               Math.max(
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771673804245514L, 36)),
                  var6 - Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(3835174197325461103L, 51)) * var13
               ),
               this.readInputTextSize(var4)
            );
            String var17 = Xqedii_2c8m20nedlja1(this.escapeInputMarkup(var16), this.escapeInputMarkup(var15));
            if (var17.isEmpty()) {
               return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nDGc1imw9eT8I5Ax9Ff6HdZ2P+CrPrIa9F/Iog==");
            } else {
               String[] var10001 = new String[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(218878224, 30)];
               var10001[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-2048358522, 5)] = var4.getString(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("KvPaCaEH9ecVPT5mE1/deD+RUanDCTblaPxv37W42AM2zhnoqOOUu9HS0LHIhUlF")
               );
               var10001[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-787526389, 14)] = var4.getString(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("9Syd2PvY0za1MAXiM/TF/wUvrg76/6t2sontA7AstNQPFS5mUHWMThF2OGCr8cLx")
               );
               var10001[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032530217, 8)] = var4.getString(
                  Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("b4j6ZvNxFdVHQ9rX6vz7sDe0eNEOXXXbLhZlkJ/pGj4/Mz1WtlHXehvhguBnNg==")
               );
               var10001[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-965421097, 25)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
                  "sat3A41ZA9dmY4SrCyCZDR1EhMlPnPD3kyIybHv2rNLErkxnBesT/g=="
               );
               String var18 = this.firstNonBlank(var10001);
               return this.withHexPrefix(var17, var18);
            }
         }
      }
   }

   public String Xqedii_je53k53(Player var1, Xqedii_1a2mt0h var2, Xqedii_1avxacb var3, ConfigurationSection var4, String var5, String var6) {
      if ((374665214 * 374665214 - 374665214 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Map var7 = this.Xqedii_22qy351(var2.Xqedii_m23ejo5, var3.Xqedii_zayz2bu);
         String var8 = (String)var7.get(var6);
         if (var8 == null) {
            var8 = (String)var7.get(var5);
         }

         if (var8 != null) {
            return var8;
         } else {
            String[] var10001 = new String[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1043607364, 24)];
            var10001[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(2049040801, 11)] = var4.getString(
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("zeqw8f4Cr09UrWXKJr9w9xziA7AsjU1jP9zOrQjSVj1X2G8Cl+f3/fM2")
            );
            var10001[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 31)] = var4.getString(
               Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("FEENICaWg1OuC1u4hZ1XiuO1re8ODquKeUYc7rqRpW//SVQsLx8=")
            );
            String var9 = this.firstNonBlank(var10001);
            if (var9.isBlank()) {
               return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nDGc1imw9eT8I5Ax9Ff6HdZ2P+CrPrIa9F/Iog==");
            } else {
               var10001 = new String[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032367302, 8)];
               var10001[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 24)] = this.resolvePlaceholders(
                  var1, var9, (Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(607521757, 15) != 0)
               );
               var10001[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1592336820, 2)] = var9;
               return this.firstNonBlank(var10001);
            }
         }
      }
   }

   public static String Xqedii_3du3uin(String var0, UUID var1) {
      if ((321185950 * 321185950 + 321185950 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return Xqedii_1nzjrt2tg51cs(
            var0, String.valueOf(var1 == null ? Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("sNlApSpfye4lyQPkA1st6o7qJLIEs7hNO9XKo4+d") : var1)
         );
      }
   }

   public Map<String, String> Xqedii_22qy351(String var1, UUID var2) {
      if ((1204279873 * 1204279873 - 1204279873 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Map var3 = this.Xqedii_1exxxow.get(Xqedii_3du3uin(var1, var2));
         return var3 == null ? Collections.emptyMap() : var3;
      }
   }

   public Map<String, String> Xqedii_31ojm23(String var1, UUID var2) {
      if ((1759343979 ^ 1759343979) != 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_1exxxow.computeIfAbsent(Xqedii_3du3uin(var1, var2), var0 -> {
            if ((-248384877 ^ -248384877) != 0) {
               throw new IllegalStateException();
            } else {
               return new ConcurrentHashMap<>();
            }
         });
      }
   }

   public String Xqedii_2b50ydw(String var1, Map<String, String> var2) {
      if ((865106777 * 865106777 - 865106777 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isEmpty() && var1.indexOf(Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(-1591511867, 27)) >= 0) {
         String var3 = var1;

         for (Entry var5 : var2.entrySet()) {
            String var6 = this.escapeInputMarkup((String)var5.getValue());
            var3 = var3.replace(Xqedii_1fein2t5w3lvf((String)var5.getKey()), var6).replace(Xqedii_15k7b166lamfp((String)var5.getKey()), var6);
         }

         return var3;
      } else {
         return var1;
      }
   }

   public List<Map<String, Object>> Xqedii_3ctvdu4(List<Map<String, Object>> var1, Map<String, String> var2) {
      if ((697251831 ^ 697251831) != 0) {
         throw new IllegalStateException();
      } else if (var2 != null && !var2.isEmpty()) {
         ArrayList var3 = new ArrayList();

         for (Map var5 : var1) {
            Map var6 = this.toStringObjectMap(this.Xqedii_10abs1l(var5, var2));
            if (var6 != null) {
               var3.add(var6);
            }
         }

         return var3;
      } else {
         return this.copyBlocks(var1);
      }
   }

   public Object Xqedii_10abs1l(Object var1, Map<String, String> var2) {
      if ((784494629 * 784494629 + 784494629 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 instanceof String var8) {
         if (var8.indexOf(Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1110294344, 3)) < 0) {
            return var8;
         } else {
            String var10 = var8;

            for (Entry var14 : var2.entrySet()) {
               var10 = var10.replace(Xqedii_37fclerdvwm5j((String)var14.getKey()), (CharSequence)var14.getValue())
                  .replace(Xqedii_1d71achlv0fy8((String)var14.getKey()), (CharSequence)var14.getValue());
            }

            return var10;
         }
      } else if (var1 instanceof Map var7) {
         LinkedHashMap var9 = new LinkedHashMap();

         for (Entry var13 : (Iterable<Entry>) (var7.entrySet())) {

            var9.put(String.valueOf(var13.getKey()), this.Xqedii_10abs1l(var13.getValue(), var2));
         }

         return var9;
      } else if (!(var1 instanceof List var3)) {
         return var1;
      } else {
         ArrayList var4 = new ArrayList(var3.size());

         for (Object var6 : var3) {
            var4.add(this.Xqedii_10abs1l(var6, var2));
         }

         return var4;
      }
   }

   public Map<String, Object> Xqedii_e67l634(Xqedii_1a2mt0h var1, Xqedii_1avxacb var2, String var3) {
      if ((-2018883824 * -2018883824 - -2018883824 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var3 != null && !var3.isBlank()) {
         Map var4 = this.resolveRawMapAtPath(var1.Xqedii_3uo0d9i.rawBlocks(), var3);
         if (var4 != null
            && this.isRuntimeInputType(this.stringValue(var4.get(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("i/RtKce25ylrvsCfcFlGNAEvFfaT/GrAlKdToRvQdld00+yQ"))))) {
            return var4;
         } else {
            for (Map var6 : var2.Xqedii_17c3k63) {
               if (this.isRuntimeInputType(this.stringValue(var6.get(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("i/RtKce25ylrvsCfcFlGNAEvFfaT/GrAlKdToRvQdld00+yQ"))))
                  && var3.equals(
                     this.stringValue(
                        var6.get(
                           Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("7x3XOzzBuxFW7sViXucDUggTqnuo7Kx/yCLLjdWfZ6cfJTUQnKJRx9HFMG7dFRY7aFpsTTfdw6/n1FhMlH4ohelIHlw=")
                        )
                     )
                  )) {
                  return var6;
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   public boolean Xqedii_3e40ptx(ParsedGuiLayout var1, String var2) {
      if ((-712964523 * -712964523 + -712964523 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Map var3 = this.resolveRawMapAtPath(var1.rawBlocks(), var2);
         return ((var3 != null
               && this.isRuntimeInputType(this.stringValue(var3.get(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("i/RtKce25ylrvsCfcFlGNAEvFfaT/GrAlKdToRvQdld00+yQ"))))
            ? Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 12)
            : Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 6)) != 0);
      }
   }

   public boolean Xqedii_4ewavsc(Player var1, Xqedii_1a2mt0h var2, Xqedii_2f78jo3 var3, String var4) {
      if (((-951006258 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_1avxacb var5 = this.Xqedii_322y3to(var2, var3);
         Map var6 = this.Xqedii_e67l634(var2, var5, var4);
         if (var6 == null) {
            return (Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 28) != 0);
         } else if (this.plugin.anvilInput() != null && !this.cameraService.isCameraActive(var1)) {
            String var7 = var2.Xqedii_m23ejo5;
            UUID var8 = var1.getUniqueId();
            UUID var9 = var5.Xqedii_zayz2bu;
            String[] var10001 = new String[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2065921222, 25)];
            var10001[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-767336259, 11)] = var2.Xqedii_2u9ms1f.Xqedii_122m5nm;
            String var10 = this.firstNonBlank(var10001);
            String var11 = this.runtimeInputId(var6, var4);
            var10001 = new String[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1169658345, 8)];
            var10001[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(565206761, 18)] = this.stringValue(
               var6.get(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("SnGN37CU4E8THVBE+rPA+kWi6luqEQKNO2aV79GYgYXK8Fm79/P7hvwXdwI="))
            );
            var10001[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-750586813, 28)] = var11;
            String var12 = this.firstNonBlank(var10001).trim();
            int var13 = this.runtimeInputLength(
               var6.get(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("ju9XJ9hPHMlfLcREG1bWl7afO/oYAcfroaEa3Gf2Se1QKggUcMkZ7WdLK1QvxE8I"))
            );
            Map var14 = this.Xqedii_22qy351(var7, var9);
            String var10000;
            if (var14.containsKey(var12)) {
               var10000 = (String)var14.get(var12);
            } else if (var14.containsKey(var11)) {
               var10000 = (String)var14.get(var11);
            } else {
               String[] var10003 = new String[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(1495495878, 28)];
               var10003[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(607521757, 15)] = this.stringValue(
                  var6.get(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("zeqw8f4Cr09UrWXKJr9w9xziA7AsjU1jP9zOrQjSVj1X2G8Cl+f3/fM2"))
               );
               var10003[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(875512893, 0)] = this.stringValue(
                  var6.get(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("FEENICaWg1OuC1u4hZ1XiuO1re8ODquKeUYc7rqRpW//SVQsLx8="))
               );
               var10000 = this.resolveRuntimeActionValue(var1, this.firstNonBlank(var10003));
            }

            String var15 = var10000;
            String var16 = this.limitRuntimeInputText(var15, var13);
            String[] var29 = new String[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366789, 0)];
            var29[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(398084204, 0)] = this.stringValue(
               var6.get(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("FoVM3r5+1tDP8xmBVkF+DbV24WevWVCyJtA1FzSAiet7kUm8GEQ="))
            );
            var29[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-187646704, 26)] = this.stringValue(
               var6.get(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("9ZMc5rGhKmyU2i9C6NrLFXl9Tg0tXYtqTyeXXMmILigT1hFizlTpdA=="))
            );
            var29[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(796168412, 1)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
               "C4nlt8undSMjIpk3Durf05ig3KtSM7PyDAKnoKQCa5zPEA7MGOJlh9I53nyb0ZU5"
            );
            String var17 = this.resolveRuntimeActionValue(var1, this.firstNonBlank(var29));
            boolean var18 = (!Boolean.FALSE
                     .equals(var6.get(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("lnQIDxTKvstUZflznNAkCWQjjKlihZeb+7xu/gtsmYhLakmfvH/nom3P+TYL5c0tTMo=")))
                  && !Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("1ZmtAuR+1qP4qnlMBzWZYgWuawJG3lmAWLhcaeTnW70V+Ewpgsw=")
                     .equalsIgnoreCase(
                        this.stringValue(var6.get(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("lnQIDxTKvstUZflznNAkCWQjjKlihZeb+7xu/gtsmYhLakmfvH/nom3P+TYL5c0tTMo=")))
                     )
               ? Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-393763195, 13)
               : Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 13)) != 0;
            String var19 = this.stringValue(var6.get(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("fw8lPzETrZIlHYt8Cz+YwYjAulF+W0EyV+/uEThjtaob/yr+68/Xdg==")));
            ArrayList var20 = new ArrayList<>(
               this.readActionBranch(var6, Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("U1ghSgaW3IZ0M0KHNi+sZXhryguSP8ArPTFUhDxxre62c0Nw5ehhHPP6KfltjA=="))
            );
            var20.addAll(this.readActionBranch(var6, Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("2KB21EpL6djNnKJps4+XLqLp26HeV6N1QBHBp3KUg6bh5OYYxVQLQbSa")));
            List var21 = this.readActionBranch(var6, Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("ONYXEdZ65GI/vYMSo5jRbxHAcHadqElWS4db/yscBy3ex7MY6XHd3xaX+aIpGQ=="));
            long var22 = this.Xqedii_37xasx8.incrementAndGet();
            this.Xqedii_useqsj7.put(var8, var22);
            var5.Xqedii_h3mynbh.put(var4, new AnvilInputState(var11, var12, var16, var22));
            this.Xqedii_oncxunw(var2, var3, var5, Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 0));
            Runnable var24 = () -> {
               if ((1137307466 * 1137307466 - 1137307466 & 1) != 0) {
                  throw new IllegalStateException();
               } else {
                  this.Xqedii_3j6xv79(var7, var8, var4, var22);
                  if (!var21.isEmpty() && var1.isOnline()) {
                     this.executeRuntimeTargetActions(
                        var1, this.Xqedii_3ctvdu4(var21, this.Xqedii_22qy351(var7, var9)), Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(218878223, 30)
                     );
                  }
               }
            };
            AnvilInputRequest var25 = AnvilInputRequest.builder()
               .title(var17)
               .initial(var16)
               .maxLength(var13)
               .filter(var19)
               .allowEmpty(var18)
               .closeOnClick(
                  ((!Boolean.FALSE
                        .equals(var6.get(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("9VvlhLGM2olQfeRmNa+lAQfe5eLn47svkeWdLTYeEtycf5jhXfliwYoLij5AGxhGYSdfLDoUEu0=")))
                     ? Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032399558, 15)
                     : Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1215043514, 16)) != 0)
               )
               .closeOnMax(
                  Boolean.TRUE
                     .equals(var6.get(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("iugN+J+/eZeolHqOMZJVkrbiHlrbMg3c9z2yvWsoDxuruDvbjy/T2moaxd183wemobi25w==")))
               )
               .idleTimeoutTicks(
                  this.runtimeInputTimeout(
                     var6.get(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("lQhpgbqUNyy2tV3f4oLiSk7FBcKT25q2MQxEIGdpTbzM5F7pM+ZoaWfp/dQNv0FGvXZaeqQAbcHClcUk+Y37UA=="))
                  )
               )
               .commitEditsOnEscape((Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366791, 0) != 0))
               .onChange(var9x -> {
                  if ((-1403408195 * -1403408195 + -1403408195 & 1) != 0) {
                     throw new IllegalStateException();
                  } else if (this.Xqedii_1uzm0c2(var8, var22)) {
                     Xqedii_1a2mt0h var10x = this.Xqedii_35obddf.get(var7);
                     Xqedii_1avxacb var11x = this.Xqedii_1l1cnj5(var10x, var8);
                     if (var11x != null) {
                        var11x.Xqedii_h3mynbh.put(var4, new AnvilInputState(var11, var12, this.limitRuntimeInputText(var9x, var13), var22));
                        this.Xqedii_oncxunw(var10x, var10x.Xqedii_2zsng32.get(var8), var11x, Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(2020105864, 25));
                     }
                  }
               })
               .onCommit(var17x -> {
                  if ((1379570406 * (1379570406 + 1) & 1) != 0) {
                     throw new IllegalStateException();
                  } else if (this.Xqedii_1uzm0c2(var8, var22) && this.Xqedii_35obddf.get(var7) != null) {
                     String var18x = this.limitRuntimeInputText(var17x, var13);
                     if (!var18 && var18x.isBlank()) {
                        var24.run();
                     } else {
                        UltimateUIInputSubmitEvent var19x = new UltimateUIInputSubmitEvent(var1, var10, var11, var12, var16, var18x);
                        Bukkit.getPluginManager().callEvent(var19x);
                        var18x = this.limitRuntimeInputText(InputFilter.fromId(var19).sanitize(var19x.getValue()), var13);
                        if (!var19x.isCancelled() && (var18 || !var18x.isBlank())) {
                           Map var20x = this.Xqedii_31ojm23(var7, var9);
                           var20x.put(var11, var18x);
                           var20x.put(var12, var18x);
                           this.Xqedii_3j6xv79(var7, var8, var4, var22);
                           if (!var20.isEmpty() && var1.isOnline()) {
                              this.executeRuntimeTargetActions(var1, this.Xqedii_3ctvdu4(var20, var20x), Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 10));
                           }
                        } else {
                           this.Xqedii_3j6xv79(var7, var8, var4, var22);
                        }
                     }
                  } else {
                     this.Xqedii_3j6xv79(var7, var8, var4, var22);
                  }
               })
               .onCancel((var7x, var8x, var9x) -> {
                  if ((-113755876 ^ -113755876) != 0) {
                     throw new IllegalStateException();
                  } else if (var9x != InputCloseReason.PROGRAMMATIC && var9x != InputCloseReason.DISCONNECT) {
                     var24.run();
                  } else {
                     this.Xqedii_3j6xv79(var7, var8, var4, var22);
                  }
               })
               .build();

            try {
               if (this.plugin.anvilInput().open(var1, var25) != null) {
                  return (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(568959354, 19) != 0);
               }
            } catch (RuntimeException var27) {
               this.plugin.getLogger().warning(Xqedii_kht4ngvsbwyy(var11, var2.Xqedii_2u9ms1f.Xqedii_1kjhbri, var27.getMessage()));
            }

            this.Xqedii_3j6xv79(var7, var8, var4, var22);
            return (Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 10) != 0);
         } else {
            return (Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1751025786, 1) != 0);
         }
      }
   }

   public boolean Xqedii_1uzm0c2(UUID var1, long var2) {
      if ((985845298 * (985845298 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Long var4 = this.Xqedii_useqsj7.get(var1);
         return ((var4 != null && var4 == var2
            ? Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529961, 17)
            : Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 2)) != 0);
      }
   }

   public Xqedii_1avxacb Xqedii_1l1cnj5(Xqedii_1a2mt0h var1, UUID var2) {
      if ((-176807943 ^ -176807943) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return null;
      } else if (!var1.Xqedii_2u9ms1f.Xqedii_1xd0aa1) {
         return var1.Xqedii_342ikl0;
      } else {
         Xqedii_2f78jo3 var3 = var1.Xqedii_2zsng32.get(var2);
         return var3 == null ? null : var3.Xqedii_186erki;
      }
   }

   public void Xqedii_3j6xv79(String var1, UUID var2, String var3, long var4) {
      if ((1009631048 * 1009631048 + 1009631048 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         this.Xqedii_useqsj7.remove(var2, var4);
         Xqedii_1a2mt0h var6 = this.Xqedii_35obddf.get(var1);
         Xqedii_1avxacb var7 = this.Xqedii_1l1cnj5(var6, var2);
         if (var7 != null) {
            AnvilInputState var8 = var7.Xqedii_h3mynbh.get(var3);
            if (var8 != null && var8.token() == var4) {
               var7.Xqedii_h3mynbh.remove(var3);
            }

            this.Xqedii_oncxunw(var6, var6.Xqedii_2zsng32.get(var2), var7, Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-787542773, 14));
         }
      }
   }

   public void Xqedii_vxzcgon(
      ParsedGuiLayout var1,
      ConfigurationSection var2,
      String var3,
      double[] var4,
      double var5,
      Map<String, List<Map<String, Object>>> var7,
      Map<String, Xqedii_3bglnh9> var8
   ) {
      if ((368422244 * 368422244 + 368422244 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(396500634, 7)];
         var10001[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-2005374882, 17)] = var2.getString(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("7x3XOzzBuxFW7sViXucDUggTqnuo7Kx/yCLLjdWfZ6cfJTUQnKJRx9HFMG7dFRY7aFpsTTfdw6/n1FhMlH4ohelIHlw=")
         );
         String var9 = this.firstNonBlank(var10001).trim();

         for (int var10 = Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032399558, 15); !var9.isBlank(); var9 = this.trimTrailingChildrenPathSegment(var9)) {
            if (var10 != 0 && this.isRuntimeInputType(var3) || this.Xqedii_3e40ptx(var1, var9)) {
               String var13 = var9;
               var8.computeIfAbsent(Xqedii_3n5r0qdlgozke(var13), var1x -> {
                  if (((1250209624 << 1 | 1) & 1) == 0) {
                     throw new IllegalStateException();
                  } else {
                     return new Xqedii_3bglnh9(Collections.emptyList(), var13);
                  }
               }).Xqedii_lr3km7o(var4, var5);
               return;
            }

            var10 = Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1585831455, 3);
            List var11 = (List)var7.get(var9);
            if (var11 == null) {
               var11 = this.Xqedii_iwuh552(var1, var9);
               var7.put(var9, var11);
            }

            if (!var11.isEmpty()) {
               Xqedii_3bglnh9 var12 = (Xqedii_3bglnh9)var8.get(var9);
               if (var12 == null) {
                  var12 = new Xqedii_3bglnh9(var11, null);
                  var8.put(var9, var12);
               }

               var12.Xqedii_lr3km7o(var4, var5);
               return;
            }
         }
      }
   }

   public List<Map<String, Object>> Xqedii_iwuh552(ParsedGuiLayout var1, String var2) {
      if ((94496722 ^ 94496722) != 0) {
         throw new IllegalStateException();
      } else {
         Map var3 = this.resolveRawMapAtPath(var1.rawBlocks(), var2);
         if (var3 != null
            && var3.get(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("2KB21EpL6djNnKJps4+XLqLp26HeV6N1QBHBp3KUg6bh5OYYxVQLQbSa")) instanceof List var4
            && !var4.isEmpty()) {
            ArrayList var9 = new ArrayList();

            for (Object var7 : var4) {
               Map var8 = this.toStringObjectMap(var7);
               if (var8 != null && !var8.isEmpty()) {
                  this.normalizeActionMapForEditor(var8);
                  var9.add(var8);
               }
            }

            return var9;
         } else {
            return Collections.emptyList();
         }
      }
   }

   public List<Xqedii_2pbx7s8> Xqedii_368ahcy(Xqedii_1a2mt0h var1, Xqedii_3bglnh9 var2, boolean var3) {
      if ((1266133424 * 1266133424 + 1266133424 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         ArrayList var4 = new ArrayList();
         if (Double.isFinite(var2.Xqedii_28fe0kz)
            && Double.isFinite(var2.Xqedii_3e6rt9d)
            && Double.isFinite(var2.Xqedii_4y9g64q)
            && Double.isFinite(var2.Xqedii_qublebm)) {
            ParsedGuiLayout var5 = var1.Xqedii_3uo0d9i;
            double var6 = var1.Xqedii_2u9ms1f.Xqedii_2llkhpy / var5.pageWidth();
            double var8 = Math.max(
               Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-6631010883953778886L, 43)), (var2.Xqedii_4y9g64q - var2.Xqedii_28fe0kz) * var6
            );
            double var10 = Math.max(
               Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(5099052283896949871L, 48)), (var2.Xqedii_qublebm - var2.Xqedii_3e6rt9d) * var6
            );
            int var12 = (int)Math.max(
               Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(-2724328214008563079L, 0)),
               Math.min(
                  Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-4724313457787049475L, 52)),
                  Math.ceil(var8 / var10 - Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(7974629090200102799L, 33)))
               )
            );
            double var13 = var8 / var12;
            double var15 = (
                  var5.pageWidth() / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-46928664590551562L, 63))
                     - (var2.Xqedii_28fe0kz + var2.Xqedii_4y9g64q) / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(3836898602808273358L, 23))
               )
               * var6;
            double var17 = (
                  var5.pageHeight() / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(-7175783633458353965L, 16))
                     - (var2.Xqedii_3e6rt9d + var2.Xqedii_qublebm) / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-3505693178411092490L, 62))
               )
               * var6;
            double var19 = Math.max(Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3u3apuri2wjbb(-3395250152581644328L, 45)), var1.Xqedii_2u9ms1f.Xqedii_1b6pbfy);
            double var21 = -Math.max(Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-6927689899150880746L, 40)), var2.Xqedii_1nljmh4) * var19
               - Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-4501360136416822985L, 50));

            for (int var23 = Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-1843722770, 14); var23 < var12; var23++) {
               double var24 = (
                     var23
                        - (var12 - Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(-1501173625, 29))
                           / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(7497173888099344262L, 23))
                  )
                  * var13;
               var4.add(new Xqedii_2pbx7s8(var15 + var24, var17, var21, var13, var10, var2.Xqedii_ftgh2g1, var2.Xqedii_10dg67g, var3));
            }

            return var4;
         } else {
            return var4;
         }
      }
   }

   public Location Xqedii_1kff9t1(Xqedii_1a2mt0h var1, Xqedii_2pbx7s8 var2, float var3) {
      if ((-224361655 * -224361655 - -224361655 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Location var4 = var1.Xqedii_2lkymh6;
         double var5 = Math.toRadians(var3);
         double var7 = Math.cos(var5);
         double var9 = Math.sin(var5);
         double var11 = var2.Xqedii_lxape50 + var2.Xqedii_35crh3k / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_2t1lvuv30ewth(-2352771675951729162L, 33));
         return new Location(
            var4.getWorld(),
            var4.getX() + var2.Xqedii_m7gc5a1 * var7 - var11 * var9,
            var4.getY() + var2.Xqedii_1il6ylu - var2.Xqedii_203jf14 / Double.longBitsToDouble(Xqedii_3ihqgc8.Xqedii_3q99a8e1nv4ng(5652485903761448099L, 10)),
            var4.getZ() + var2.Xqedii_m7gc5a1 * var9 + var11 * var7
         );
      }
   }

   public void Xqedii_jd99vux(Xqedii_1a2mt0h var1) {
      if ((1847372729 * 1847372729 + 1847372729 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         for (Xqedii_2f78jo3 var3 : var1.Xqedii_2zsng32.values()) {
            Player var4 = Bukkit.getPlayer(var3.Xqedii_rjd91hv);
            if (var4 != null && var4.isOnline()) {
               Map var5 = this.Xqedii_22qy351(var1.Xqedii_m23ejo5, this.Xqedii_322y3to(var1, var3).Xqedii_zayz2bu);
               int var6 = Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 18);

               for (Xqedii_3j2w0l1 var8 : var3.Xqedii_2f37hi3.values()) {
                  if (var8.Xqedii_tq2w5ap != null && !var8.Xqedii_tq2w5ap.isItem() && var8.Xqedii_2nf1zu3 != null) {
                     String var9 = this.Xqedii_1q39kc0(var4, var8.Xqedii_2nf1zu3, var8.Xqedii_o3buktx, var5);
                     if (var9 != null && !var9.isBlank() && !var9.equals(var8.Xqedii_tq2w5ap.text())) {
                        WorldDisplays.WorldElementSpec var10 = var8.Xqedii_tq2w5ap;
                        var8.Xqedii_tq2w5ap = new WorldDisplays.WorldElementSpec(
                           var9,
                           var10.item(),
                           var10.translation(),
                           var10.scale(),
                           var10.leftRotation(),
                           var10.opacity(),
                           var10.lineWidth(),
                           var10.alignment(),
                           var10.blockLight(),
                           var10.skyLight(),
                           var10.billboard()
                        );
                        var8.Xqedii_1p17s7a = this.Xqedii_hukkpjv(var4, var8.Xqedii_2nf1zu3, var5);
                        var6 = Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1173262875, 30);
                     }
                  }
               }

               if (var6 != 0) {
                  this.Xqedii_1q63spb(var1, var4, var3, Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1144796207, 16));
               }
            }
         }
      }
   }

   public void Xqedii_gtw7cir(Xqedii_1a2mt0h var1) {
      if (((-43768028 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         for (Entry var3 : var1.Xqedii_2zsng32.entrySet()) {
            this.Xqedii_1gftlib(Bukkit.getPlayer((UUID)var3.getKey()), (Xqedii_2f78jo3)var3.getValue());
         }

         var1.Xqedii_2zsng32.clear();
      }
   }

   public void Xqedii_1gftlib(Player var1, Xqedii_2f78jo3 var2) {
      if ((-216908220 * -216908220 + -216908220 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         this.Xqedii_3tuhq9d(var2);
         if (var1 != null && var1.isOnline()) {
            ArrayList var3 = new ArrayList();

            for (Xqedii_3j2w0l1 var5 : var2.Xqedii_2f37hi3.values()) {
               var3.add(var5.Xqedii_2b6hmqn);
            }

            var3.addAll(var2.Xqedii_at2aqft.keySet());
            if (!var3.isEmpty()) {
               int[] var6 = new int[var3.size()];

               for (int var7 = Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366790, 2); var7 < var3.size(); var7++) {
                  var6[var7] = (Integer)var3.get(var7);
               }

               this.hudService.destroyWorldEntities(var1, var6);
            }
         }
      }
   }

   public void Xqedii_3tuhq9d(Xqedii_2f78jo3 var1) {
      if ((-487091619 * (-487091619 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         for (Integer var3 : var1.Xqedii_at2aqft.keySet()) {
            this.Xqedii_28h4g3k.remove(var3);
         }
      }
   }

   public void Xqedii_2v8lhne() {
      if (((2087793760 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (this.Xqedii_2030na2 == null) {
         try {
            PacketListenerAbstract var1 = new PacketListenerAbstract(PacketListenerPriority.NORMAL) {
               public static final String Xqedii_20y8jqdv5pcff = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";

               public void onPacketReceive(PacketReceiveEvent var1) {
                  if ((-1530439834 * -1530439834 - -1530439834 & 1) != 0) {
                     throw new IllegalStateException();
                  } else if (var1 != null && var1.getPacketType() == Client.INTERACT_ENTITY) {
                     if (var1.getPlayer() instanceof Player var2x) {
                        WrapperPlayClientInteractEntity var5 = new WrapperPlayClientInteractEntity(var1);
                        WorldUiViewerEntity var4 = WorldUiPart.this.Xqedii_28h4g3k.get(var5.getEntityId());
                        if (var4 != null && var4.viewerId().equals(var2x.getUniqueId())) {
                           var1.setCancelled((Xqedii_sq3891d.Xqedii_2xte153zvmld7(695989739, 7) != 0));
                           Xqedii_38o2zes.Xqedii_1avvt9e(WorldUiPart.Xqedii_lvvjxhd(WorldUiPart.this), var2x, () -> {
                              if ((-1695340855 ^ -1695340855) != 0) {
                                 throw new IllegalStateException();
                              } else {
                                 WorldUiPart.this.Xqedii_14dkzm2(var2x, var4);
                              }
                           });
                        }
                     }
                  }
               }
            };
            PacketEvents.getAPI().getEventManager().registerListener(var1);
            this.Xqedii_2030na2 = var1;
         } catch (Throwable var2) {
            this.Xqedii_2030na2 = null;
            this.plugin.getLogger().warning(Xqedii_347g7h22n2t4w(var2.getMessage()));
         }
      }
   }

   public void Xqedii_3l6pre1() {
      if ((-2006745611 * (-2006745611 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         PacketListenerCommon var1 = this.Xqedii_2030na2;
         this.Xqedii_2030na2 = null;
         if (var1 != null) {
            try {
               PacketEvents.getAPI().getEventManager().unregisterListener(var1);
            } catch (Throwable var3) {
            }
         }
      }
   }

   public void Xqedii_14dkzm2(Player var1, WorldUiViewerEntity var2) {
      if (((1853568347 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.isOnline()) {
         Xqedii_1a2mt0h var3 = this.Xqedii_35obddf.get(var2.instanceKey());
         Xqedii_2f78jo3 var4 = var3 == null ? null : var3.Xqedii_2zsng32.get(var1.getUniqueId());
         Xqedii_2pbx7s8 var5 = var4 == null ? null : var4.Xqedii_at2aqft.get(var2.entityId());
         if (var5 != null) {
            if (var5.Xqedii_32vvizl) {
               this.Xqedii_2fzgi4z(var1);
            } else if (var5.Xqedii_z6rxqsi != null || !var5.Xqedii_2lgc9k8.isEmpty()) {
               long var6 = Xqedii_38o2zes.Xqedii_316oplq();
               Long var8 = this.Xqedii_18wixnx.get(var1.getUniqueId());
               if (var8 == null || var6 - var8 >= Xqedii_3ihqgc8.Xqedii_g4b6gcmn35m1(-7349078564822075423L, 21)) {
                  this.Xqedii_18wixnx.put(var1.getUniqueId(), var6);
                  if (var5.Xqedii_z6rxqsi != null) {
                     this.Xqedii_4ewavsc(var1, var3, var4, var5.Xqedii_z6rxqsi);
                  } else {
                     Xqedii_1avxacb var9 = this.Xqedii_322y3to(var3, var4);
                     this.executeRuntimeTargetActions(
                        var1,
                        this.Xqedii_3ctvdu4(var5.Xqedii_2lgc9k8, this.Xqedii_22qy351(var3.Xqedii_m23ejo5, var9.Xqedii_zayz2bu)),
                        Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(1032529960, 7)
                     );
                  }
               }
            }
         }
      }
   }

   static {
      if ((1968731649 * 1968731649 - 1968731649 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_2bogdsr = Pattern.compile(
            Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("hPFXDiuMtCC98FL29hpGy1CTWHICv2qUKJOIyxweJvsoBbtyoioNYrC/wKRCXNDOlLCIBO3n9Ay6JtQj")
         );
         Xqedii_8vj0im2 = Pattern.compile(Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("F+nqWAMLvwTF45n1PgOxFPAJK3dglZbL+oUhhCtiIMBSBTftZ2z7YSyLYJo="));
         Xqedii_19xeymz = new Quaternionf().rotationY(Float.intBitsToFloat(Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-26675478, 5)));
         String[] var10000 = new String[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(1123304579, 4)];
         var10000[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(1215043514, 16)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
            "h3ge8GnXTub0wPN2xBM8xmsvyl06tpI2xtHWJAXAbDuC4xBEAq3fHdw3QtyV6teUxwIT8vbFGd+kshgJvsIxb2bZUkqTcSUd8O+oTgLO7hSdZz8V3veCYDTDP7CyL+71K9yTfgYghGi9X3mm9vB49865IF7EtXSXvnqKgrpY59vCpSgv+bLnbB9KNtRhLk3GtSGrBZ3qqYIkQ0/U+tiB/4Yzkq++SUTolXAhyWt0nrBoq0vfS/BMBYjQfouGyBOu/8mNrEj3G8qFnQ0wsOnoOkfi+r8FOVzZWDl9o300eoyG8yt+JXSO2pan6W/4Up+KrzXjYbhLQkkoXpIJ3Qc="
         );
         var10000[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032366788, 1)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
            "ZJGfGYVXQ3AWJJmCuZjl0coS7o6S5DucPPO08QEa/NzZvFRUVnKdTTrwvNjGUdAjJVISXOTeAoUA88d5kOZDK4mpe2WngxUFUanb7+GwNdx/UDuXaPGGz27AmyxGzgWO"
         );
         var10000[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032529929, 5)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
            "s8HRwKUmFvVSpLEzUKS4Z5jFkUtT7Yc1IQyUkUI2hVAJMeTk1xdN19Fgq//kHKHsIg9OLPgwDMLp+5zKESnpGVTTAMi0cBwHwNsvXYt4q+zevqTHi+N7lE6NL37n75P/qICxVjIY1I1SiPinpFLZCHfe5m++mEN5BgBXOwm463z767qpVg6yh1Np5sGyewS6DsU973xEg4lRS49TJJpKcYoVG0iLa6pPBGa/gJzf/+gs2bxzs5GBXxKi1XVxb7oJHBJRIA=="
         );
         var10000[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-2106271785, 29)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
            "97wgXjY3+lji5vBvv+tnHci97jZEbHnG930XP/ktOlwEb27jEGgn9OPuQB82geLfcQXebrLEBFAbV8bQZk3E+/d3WPft9Csmz6wy1UHadwmVc5K5yY5Zf4ke7yuve/vkJx2k8s2NIYAKC7BCjt5ipPkbNdxW9g0iVE0PAoOgeNARuSGV/cFFvVkW+Xin49KKkQrm+c+v6BEVYUomyOku0CCq0IwES4BZmVHtdJwV5B/Dp8tyTBSdZ17/DPI2bpXBWyQ="
         );
         var10000[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(269290449, 22)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
            "T00XE+CPRFe3IjfetHoKBfvScKrf2AX4J7trQ4hSEbBAV8lcbAubSRupKDaz9VmVtTb/9cDHFYSJaGCTWljf6oNr1d3zrQXmjW85BhfEWHdafkvWDyByK6iHmDTXXMreyQm/HexScMUseFO5i0dgh2BeAvjt2P3KT/xgyWmYMg0dsz4lDqwTWiFk6utR7SKJecsXso/XLVA0MjIeElkTlIwPIMg1yxaETHZf4/RVs+E0BG83swwzinXCX2fIIWndsWe+xr/VbsJ4Td914/qNFkuKAP3wqTejWBpAlcEGqRiGgNOFu+IJxdqkd3h2gCuPUy2ZnjtlDl8KoqXKBVHo2pCTThC+kjkttMJVqX6veRaZLsPGN8rKcew90AdkKt+4KzOC9UazlZsWzQ=="
         );
         var10000[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032760006, 17)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
            "tYQmTO9JtufAy3u5ii4Gg8RYdE6JfTEmgaZTSZuMmE17bSdFubeNkhpQYH+G6mcapc326Z0atS3I8giMnFipaYmSxQA/i2ZfWj/pLomEuYv6b4bCcVzY3dEwEXkwgGhvYOR7GfRRs1puwcIZcpGsRqd1jpoJ5fAjO89WwIwrNpsa/WXzGaIZFAMW2RC37g2A+9l2q0crjyYg8kYRQvtDW8MgUEtj4kGDgpZ3WWA1PTmtsnZyZ2z2DMPgAOsYM72OIJIWMp0VPVbvFfKz5SwYPqqECwxiTTNNZRkg8uaqhCKgcx8A5GK7HP/I"
         );
         var10000[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2033939654, 18)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
            "MWaE+sFLjyxJj/sq/Zc+C6o7LWgLFsiXzBDVA/tmj9D6Iruc1aAiyJ5mZNBIB36vl1X2309uwjM/lSNBZga/GwKy8cHkISGBiBuEgFhaauD5B4QlfCVBW24yOFOK9NsDHlLaRtazbC12zhDl4IYc4NNsNNgbPZD8YIl/k0ym+WNQF6aHWVbDvZ+pHuWZBYjrflDKyT5AdEyG/VcaSOvwXcmXY8euv65+D7y1qY2RzJvmlDvEPYU="
         );
         var10000[Xqedii_3ihqgc8.Xqedii_1yr2w71y595u6(-2035188828, 20)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
            "DW0q0dxduc8FvxnbELHAqmeqEcMaBS1RkJVuokxF33nORyEpVC/QRLuwg/5xaEoMUXhZR2/3G2W/gVEC+ThwwCK61h1PLkz4H6UTSukU77A8Ttfb8uhm8AFeHwxAcnSzwd0dTVNkkHytL+aLUhwSmQpqEj4trFSeWZ4/8T23T4DSCjV312gSvMem"
         );
         var10000[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(218878225, 30)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
            "yZl8Gj0Dlw9W6BJVUnECYFrtzmcmelkxVzBqVqmlA1q+pqr46/7AzG0P8Dx4JDyS8sEuSqrzXRn5jTXTNYIL25uUdKwSTHV4wer1K5pVmjr2Bjr12/fFP9gZ+92o0pSb8gqfAh+KgqdBOx4ruPgSiwlzpRoEDci9F/kKC2R38bTVrBWHhNFnjgHzBwDHABnVUzUevNlWOzCWYyTzVdRlHjsSdemMUEd7owXJlC/Xy8lSPQ2oddc/tIg91LyHD52pWrvQ7BYUCgX6A6tIcwGbGNpIx/4skjzG9gNbHlQZBv8="
         );
         var10000[Xqedii_3ihqgc8.Xqedii_1y3ssml1szkmh(396501658, 7)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
            "P0TM+j0dVAPyuBzvSrXSfZC1R9NKoKF89n3/vpsI+3VVHzpTP4LlByZTN+BK08oA1z6+KKQj6ZyAEi4lpsABVtStujAJlt6pdxVKP0EeY3ct3djShgnmjYFXFNAvO7iDE2lkXma+squuSjuNC4g3w8n6AHkR6VwBbr122qmovf0TA0qqd+LiYQv+KzLuk5nFAZBQpx9ZuaZIuu6rd8rVh0O8rcPzVvelhSiGsFVI+VZjuizEnth8xP4Ess5KxupmBTqorTOJLA5UG8QE8YKm4+RDR9fWJLA5eVh/Gys1eJjAy5B8nOeoBA=="
         );
         var10000[Xqedii_3ihqgc8.Xqedii_3uwg40ecgal2u(-1032415273, 14)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
            "VQUhtaKBVHdNCiCaNVTvzYUnkdXNLkVi6PF2qF4IpLs2I5yDl8HxcFSr3o+UWeqEoAuvX7I9fG3cybQWkbCl8dZSDUxkQcVga5YcJ0WCIu8nhORV+kHo33JKmi0hWXweGabo6m56sgsJxLpZ5HY54uYfa+D5ypNiloTJYXkKb+gxcDBJPTVrpD3seCskzKLD9GIFDrJlozRH6SJdJSa/0FUDh69QZdzNT2+6ZsyHZXBMQM/fY1ko9aUzOLI1CNndkEY="
         );
         var10000[Xqedii_3ihqgc8.Xqedii_e85v2ofrfaac(2032252102, 14)] = Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb(
            "5U5sE3UtCGq0KLhf+PYziir93m6/7TBYlfUCJz6KAion6zDgbHkcDuVHFt66nwi9l2FMROOAByFXjs/A7m9SUQ5t6HOCQTcU12eJxaKxPXXjVjvQ4xUyJOLFMLsC1i6FDBUNJCa5RaC9TctoFXm2EPkPpXhvyhGFJoXXm9+0dFAjK9vKjCNrd6W1lSUxI9TwXmwHReeDuIQmhubw1pZDbEyW8cgIBLySsNUPfTHGIc3+HKCM5rhR0d4uTAZZg0jSOiU3UHJGY42uom2r/7WTDQseGi2ldQ3FTiCZ0w=="
         );
         Xqedii_1p37d2s = List.of(var10000);
      }
   }

   public record WorldUiInfo(
      String name, String page, String mode, String lookAt, boolean enabled, boolean interactive, boolean perPlayer, int active, String location
   ) {
      public static final String Xqedii_3sltmer3w68le = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";

      @Override

      public String page() {
         if (((-469135429 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
         } else {
            return this.page;
         }
      }

      public String mode() {
         if ((340391607 * 340391607 + 340391607 & 1) != 0) {
            throw new IllegalStateException();
         } else {
            return this.mode;
         }
      }

      public String lookAt() {
         if ((345922784 * 345922784 + 345922784 & 1) != 0) {
            throw new IllegalStateException();
         } else {
            return this.lookAt;
         }
      }

      public boolean enabled() {
         if ((213752180 * (213752180 + 1) & 1) != 0) {
            throw new IllegalStateException();
         } else {
            return this.enabled;
         }
      }

      public boolean interactive() {
         if (((2121424632 | 1) & 1) == 0) {
            throw new IllegalStateException();
         } else {
            return this.interactive;
         }
      }

      public boolean perPlayer() {
         if ((1815513400 * 1815513400 - 1815513400 & 1) != 0) {
            throw new IllegalStateException();
         } else {
            return this.perPlayer;
         }
      }

      public int active() {
         if ((1011750746 * (1011750746 + 1) & 1) != 0) {
            throw new IllegalStateException();
         } else {
            return this.active;
         }
      }

      public String location() {
         if (((106477121 | 1) & 1) == 0) {
            throw new IllegalStateException();
         } else {
            return this.location;
         }
      }
   }

   private static /* synthetic */ String Xqedii_kbjfeiehyldw(String string) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("bucA465KNcQ+inCTiCbYUDOMZsoCgduGmI5mIYPk") + string;
    }

    private static /* synthetic */ String Xqedii_3vg9mxna3uewu(String string) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("AAhKtkkJmDAmQ75C+KOD8tVo/qPz668pgIayiPEF") + string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("bucA465KNcQ+inCTiCbYUDOMZsoCgduGmI5mIYPk");
    }

    private static /* synthetic */ String Xqedii_jkzc07b1si4s(String string, int n, int n2, int n3) {
        return string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("4bdKaYesrqWlQBXVOtFaE87V3Yp5MobTeibkqGzU") + n + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("4bdKaYesrqWlQBXVOtFaE87V3Yp5MobTeibkqGzU") + n2 + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("4bdKaYesrqWlQBXVOtFaE87V3Yp5MobTeibkqGzU") + n3;
    }

    private static /* synthetic */ String Xqedii_35y5pw2xucfj9(int n) {
        return n + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nOswLvVAVajHlilsT10l1zSZAuCU962X1wsBOvkJliprc84i");
    }

    private static /* synthetic */ String Xqedii_3b551q61k6m7o(String string) {
        return string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("nOswLvVAVajHlilsT10l1zSZAuCU962X1wsBOvkJliprc84i");
    }

    private static /* synthetic */ String Xqedii_1gmw4bk5uiu0r(String string) {
        return string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("bucA465KNcQ+inCTiCbYUDOMZsoCgduGmI5mIYPk");
    }

    private static /* synthetic */ String Xqedii_1q42n2sr2icf9(String string) {
        return string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("AAhKtkkJmDAmQ75C+KOD8tVo/qPz668pgIayiPEF");
    }

    private static /* synthetic */ String Xqedii_191pzkngazh6p(String string, String string2) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("I8CWowZZYFNTrDFcliKuZZ24Zh7rZpw31UoIqVP4Q1u4JBAmqCBWsxEjVtJwDRvVtzDvmWws+CjitI0Iy4FIBGDW9lS+4EiWlSk=") + string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("dsA9VDTkotU+tzYG+q/2P4nO8GUygFmgzSGOKz7gC7LJFUa9X9EBCGZ9me52j2COl0T8QiXVFHSsV1t83yu0frlvbjiSoQ==") + string2;
    }

    private static /* synthetic */ String Xqedii_211xn4bchyhoo(String string, String string2) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("I8CWowZZYFNTrDFcliKuZZ24Zh7rZpw31UoIqVP4Q1u4JBAmqCBWsxEjVtJwDRvVtzDvmWws+CjitI0Iy4FIBGDW9lS+4EiWlSk=") + string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("NAM3G2XEFCZKXJuZRuK9ZwhlhDHTauJ3Dz/iNbkz+7X9pHpzuVbseJM6AZ6Hjdzs9FTQlV3OVqUlsmli0AS3IL6I") + string2 + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("TKqMjyiHg0lVRB5lriK6Wh7xoKFF4e7K9vfwESB5I2cYNUgnsSwP2PAJvWOU5/zykcGnfwTQ2zKqvK6iVD3pFRdOdE6YDrQ8KXc=");
    }

    private static /* synthetic */ String Xqedii_2t58exsf67b6x(String string) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("I8CWowZZYFNTrDFcliKuZZ24Zh7rZpw31UoIqVP4Q1u4JBAmqCBWsxEjVtJwDRvVtzDvmWws+CjitI0Iy4FIBGDW9lS+4EiWlSk=") + string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("O/09Mbo8EaJmmoNEjNp0XncNYP1eTrTYBavX9Fzt7wK+9HQSiLWs472CZNccaolg32WL60nSi3cpop5A4aTlj6UYCuWgwQ==");
    }

    private static /* synthetic */ String Xqedii_1wwobnt4l79jz(String string, String string2) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("XNDkQRB3vKDsOUw+DeECBETRPsFTn7m48WmZeEQqkEcKIdhwgY/LhRyYwS51Y8HhTP6uAtMmUAtqdtZbmeYItSroCYYZqt9wC0fPYVn6EaKabhs2iQuDCDOFFbSFbDd/ZTgDBpN0REw=") + string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("yn2vP3lxd9zXK70TNfq0/+YXOVMVUrzurrFZeR/TSIwr9w==") + string2;
    }

    private static /* synthetic */ String Xqedii_1qojfk76l8obh(String string, String string2) {
        return string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("AAhKtkkJmDAmQ75C+KOD8tVo/qPz668pgIayiPEF") + string2;
    }

    private static /* synthetic */ String Xqedii_8hx9a18mojyy(String string, String string2) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("I8CWowZZYFNTrDFcliKuZZ24Zh7rZpw31UoIqVP4Q1u4JBAmqCBWsxEjVtJwDRvVtzDvmWws+CjitI0Iy4FIBGDW9lS+4EiWlSk=") + string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("QqClcVgmQHc1Elex+xehJ9qfCSpIIj9+jSp0jArm8I7zz/w8aUe27Gs6Al+yTaz4IX6rP3Zn4GlXdO4bgJ15pAaKTJi4MLeeUCUDNgf69wWTykLBPPaa7K9SZP+TmcZPnX4Oo0sqZ/FT1ZtGZMk4SyzSeb4BfvRigdNvhBE1fZA=") + string2 + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("YDxNalrtw1CP7f4CvkXvu4kWuM/n/aCtbkVXRrzD");
    }

    private static /* synthetic */ String Xqedii_39ha5k65mj4h2(String string, String string2) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("I8CWowZZYFNTrDFcliKuZZ24Zh7rZpw31UoIqVP4Q1u4JBAmqCBWsxEjVtJwDRvVtzDvmWws+CjitI0Iy4FIBGDW9lS+4EiWlSk=") + string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("EGeOPYn2c04Ukcu65aYQll0S3/Y567k9lkp7b9o9gLDncpBR9/X3DxlcTLz2lwJ7SImwRQ0y") + string2 + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("DeC4DRaRvLFKmStHrg/BHeCXtENG2gabXyzD6leDF55nw8kNdVcnzbmmZg+h95bXk+j+EgsXLta4OvmZ0LHtp6SbBybedjumW6gHsQ==");
    }

    private static /* synthetic */ String Xqedii_2ekog5ljh3z52(String string) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("/1IhOmgghCpiACZ5JJi/aZIQkVUMlII2NdY2LXW4/L4BpV1WyMirUBNE3UeDt5fIUalfRmtzwwXg9YzY4D0Ge37fsf87d18kFQHGgKnG1M0uBOvu4OYffsJiEN4r8vqIkoyzBSDZ") + string;
    }

    private static /* synthetic */ String Xqedii_2zov4aeogw0cg(String string) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("Mrin9HN5SgDUzzay21oRx48TbOrOBWYEepkTK+l3") + string;
    }

    private static /* synthetic */ String Xqedii_ophwaqb4vzuj(String string, String string2) {
        return string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("Mrin9HN5SgDUzzay21oRx48TbOrOBWYEepkTK+l3") + string2;
    }

    private static /* synthetic */ String Xqedii_3hfwobsy6rnhr(String string) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("Mrin9HN5SgDUzzay21oRx48TbOrOBWYEepkTK+l3") + string;
    }

    private static /* synthetic */ String Xqedii_fwd2f8nfci4j(String string) {
        return string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("UoUkYic++V+nKh37mNINjEsh8kjPwHYziFqEX4/QDUJIYWn4A/GAtA==");
    }

    private static /* synthetic */ String Xqedii_3j3cdk8zooc0l(String string, String string2) {
        return string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("Mrin9HN5SgDUzzay21oRx48TbOrOBWYEepkTK+l3") + string2;
    }

    private static /* synthetic */ String Xqedii_2c8m20nedlja1(String string, String string2) {
        return string + string2;
    }

    private static /* synthetic */ String Xqedii_1nzjrt2tg51cs(String string, String string2) {
        return string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("bucA465KNcQ+inCTiCbYUDOMZsoCgduGmI5mIYPk") + string2;
    }

    private static /* synthetic */ String Xqedii_1fein2t5w3lvf(String string) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("CDLOYxPA59TjtNF6qe4pukJMOuK3vvp587AIdvdWi7s=") + string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("iNXVah6RTIDC25U6wWizoaqlIqt4XmzV94Kbt9bU");
    }

    private static /* synthetic */ String Xqedii_15k7b166lamfp(String string) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("FxyXKipZ8NUKz3EVKtJN5v7vYQRlvSeQ0rD7+mAD") + string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("iNXVah6RTIDC25U6wWizoaqlIqt4XmzV94Kbt9bU");
    }

    private static /* synthetic */ String Xqedii_37fclerdvwm5j(String string) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("CDLOYxPA59TjtNF6qe4pukJMOuK3vvp587AIdvdWi7s=") + string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("iNXVah6RTIDC25U6wWizoaqlIqt4XmzV94Kbt9bU");
    }

    private static /* synthetic */ String Xqedii_1d71achlv0fy8(String string) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("FxyXKipZ8NUKz3EVKtJN5v7vYQRlvSeQ0rD7+mAD") + string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("iNXVah6RTIDC25U6wWizoaqlIqt4XmzV94Kbt9bU");
    }

    private static /* synthetic */ String Xqedii_kht4ngvsbwyy(String string, String string2, String string3) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("v27YNxy6y+/CwyjDFLsKd2hQXx1CA7fAVxgBGiEM5GvUjGcGxYgF61309MJrsUC9q/pn5gu6SGAGEyHV6ibltUW/TgTlRJ4UcBciJI6lRu8PAJXjCeyDV5IwgdFV5xKgsqU=") + string + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("oLVLNLXqq7L2NPW7LKuZCTzimPPXjYJQxgn0YJdESu2nSBpRGZMd0f25tJBugoEiw0M0ht6yuuFdsg==") + string2 + Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("yn2vP3lxd9zXK70TNfq0/+YXOVMVUrzurrFZeR/TSIwr9w==") + string3;
    }

    private static /* synthetic */ String Xqedii_3n5r0qdlgozke(String string) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("IPubVn+Ji2/FxC+4LLXwUMwADicuyDf71feqHDfhPWTnI0cXIwOs4g==") + string;
    }

    private static /* synthetic */ String Xqedii_347g7h22n2t4w(String string) {
        return Xqedii_3ihqgc8.Xqedii_1uu5gx5gc3ttb("udlG9ksMdCt6coDcz5IKnPyAK8Syg8nVLTDeniyhKXP4cooOPph3nkEiousPuBZ+7sjmd4dBlJOFxJqFe93JCp0yO5v5ItfnPa0suMWhSeQy7uVqgcH+aiEfG7Tl/jZwa7BJ7qs8SFN4RzhJ+xHStz1OWQgvZFQLILlprQnB4f+lw8AjnU2TbA==") + string;
    }

   public static UltimateUI Xqedii_lvvjxhd(WorldUiPart worldUiPart) {
      return worldUiPart.plugin;
   }
}
