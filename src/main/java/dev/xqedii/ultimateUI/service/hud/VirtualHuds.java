package dev.xqedii.ultimateUI.service.hud;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.Xqedii_2bch2cw;
import dev.xqedii.ultimateUI.Xqedii_2t8d8j4;
import dev.xqedii.ultimateUI.Xqedii_38o2zes;
import dev.xqedii.ultimateUI.PlayerElementId;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.PacketEvents;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.entity.data.EntityData;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.entity.data.EntityDataTypes;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.entity.type.EntityTypes;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.player.ClientVersion;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.util.Quaternion4f;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.server.WrapperPlayServerDestroyEntities;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.server.WrapperPlayServerEntityMetadata;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.server.WrapperPlayServerSpawnEntity;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.entity.Display.Brightness;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public abstract class VirtualHuds extends HudVectors {
   public static final String Xqedii_1lou0kd2sxt9d = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";

   public VirtualHuds(UltimateUI var1) {
      super(var1);
   }

   public Entity Xqedii_3lomkmt(Player var1, String var2, Vector var3, Vector var4, int var5, int var6, TextAlignment var7) {
      if ((1687355598 * 1687355598 + 1687355598 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Entity var8 = this.Xqedii_2i4mlqg(var1, var2, this.Xqedii_1w6w2sl());
         var8.setMetadata(
            Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("EbzMfRQiWbnxZRWTtkeYIp9pHaCWkV7m0OHXQLl+r5sAh8MedZJIXJObwTOaus/ciM/EYMh6Tdo="),
            new FixedMetadataValue(this.Xqedii_rrdyomu, this.Xqedii_3r2d8xo(var7).name())
         );
         var8.setRotation(
            Float.intBitsToFloat(Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(16871702, 1)), Float.intBitsToFloat(Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(-2143265723, 31))
         );
         var8.setMetadata(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("gmHyh/mKhjqOpODgsE9W89Go8ZRabbDm26FiiShAGgk="), new FixedMetadataValue(this.Xqedii_rrdyomu, var2));
         var8.setMetadata(
            Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("F0vZe6iHKjOW5Okn5RmpV2WRMj8KlmNffU6VncNnpeIXkQ=="),
            new FixedMetadataValue(this.Xqedii_rrdyomu, Boolean.valueOf((Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241461988, 14) != 0)))
         );
         var8.setMetadata(
            Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("8A5MIKQT1N8b+RtEWky6Nh52ywvmvb4JdG61TqcnbaK+6Vjx7mnIU5Vt0PpTgw=="),
            new FixedMetadataValue(this.Xqedii_rrdyomu, var1.getUniqueId().toString())
         );
         var8.setMetadata(
            Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("rmNixt8Q7SX6GxsytMDDX+AkKGd3jZOpXD0VS4nGBjeDnKuxR74lTo3unwY="),
            new FixedMetadataValue(this.Xqedii_rrdyomu, var3.clone())
         );
         var8.setMetadata(
            Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("W8UJIhOqYGkfV0MKiB2j89u5DZIayNZqIEiqWFtcphMHPei/9yo="),
            new FixedMetadataValue(this.Xqedii_rrdyomu, var4.clone())
         );
         var8.setMetadata(
            Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("jWiuj2eip1lp4Lz/is6ORJ/q18c2RXtrQiv0ua8Ed9I9XvRkjhqCEf/I"), new FixedMetadataValue(this.Xqedii_rrdyomu, var6)
         );
         var8.setMetadata(
            Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("yRBMi0A32n1H/i7+W4EjDtEntdIFaaJNCR56f+k273zu/52BstcfPhh3"), new FixedMetadataValue(this.Xqedii_rrdyomu, var5)
         );
         var8.setMetadata(
            Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("W5Uou0/xldu59dySks+o9GtFSBCAy1TBAneY+C36vrtp/FEU4d+BKUwtMYhz/pTpvU0D/puhD5ArSfLE"),
            new FixedMetadataValue(this.Xqedii_rrdyomu, Boolean.valueOf((Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241461988, 9) != 0)))
         );
         var1.setMetadata(Xqedii_3t98giw946skd(var2), new FixedMetadataValue(this.Xqedii_rrdyomu, var8));
         return var8;
      }
   }

   public int Xqedii_1w6w2sl() {
      if ((415660698 ^ 415660698) != 0) {
         throw new IllegalStateException();
      } else {
         int var1 = this.Xqedii_2pqqofd.getAndDecrement();
         if (var1 == Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(-2039734159, 8)) {
            this.Xqedii_2pqqofd.set(Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(-2052935737, 16));
            var1 = this.Xqedii_2pqqofd.getAndDecrement();
         }

         return var1;
      }
   }

   public int Xqedii_1lphdnr() {
      if ((1765401620 ^ 1765401620) != 0) {
         throw new IllegalStateException();
      } else {
         int var1 = this.Xqedii_31fmop3.getAndIncrement();
         if (var1 == Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(-241460965, 11)) {
            this.Xqedii_31fmop3.set(Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(282492676, 29));
            var1 = this.Xqedii_31fmop3.getAndIncrement();
         }

         return var1;
      }
   }

   @Override
   public Xqedii_2t8d8j4 Xqedii_18xpusv(Player var1, String var2) {
      if ((-102811549 * -102811549 - -102811549 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var3 = this.Xqedii_7rxyqib.get(var1.getUniqueId());
         return var3 == null ? null : (Xqedii_2t8d8j4)var3.get(var2);
      } else {
         return null;
      }
   }

   public Xqedii_2t8d8j4 Xqedii_2jnbe8d(Entity var1) {
      if ((2097665710 * 2097665710 - 2097665710 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return null;
      } else {
         PlayerElementId var2 = this.Xqedii_ffdt8d5.get(var1.getUniqueId());
         if (var2 == null) {
            return null;
         } else {
            Map var3 = this.Xqedii_7rxyqib.get(var2.playerId());
            return var3 == null ? null : (Xqedii_2t8d8j4)var3.get(var2.id());
         }
      }
   }

   public Player Xqedii_3u2qg5l(Entity var1) {
      if ((-1572350226 ^ -1572350226) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return null;
      } else {
         PlayerElementId var2 = this.Xqedii_ffdt8d5.get(var1.getUniqueId());
         return var2 == null ? null : Bukkit.getPlayer(var2.playerId());
      }
   }

   public void Xqedii_1gcmu53(Player var1, Xqedii_2t8d8j4 var2) {
      if ((-932700795 * (-932700795 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         this.Xqedii_7rxyqib.computeIfAbsent(var1.getUniqueId(), var0 -> {
            if ((1326393600 * 1326393600 - 1326393600 & 1) != 0) {
               throw new IllegalStateException();
            } else {
               return new ConcurrentHashMap<>();
            }
         }).put(var2.Xqedii_26svack, var2);
         this.Xqedii_ffdt8d5.put(var2.Xqedii_216y0r3.getUniqueId(), new PlayerElementId(var1.getUniqueId(), var2.Xqedii_26svack));
         var2.Xqedii_216y0r3
            .setMetadata(
               Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("EbzMfRQiWbnxZRWTtkeYIp9pHaCWkV7m0OHXQLl+r5sAh8MedZJIXJObwTOaus/ciM/EYMh6Tdo="),
               new FixedMetadataValue(this.Xqedii_rrdyomu, var2.Xqedii_1xdxr33.name())
            );
      }
   }

   @Override
   public void Xqedii_32sczwk(Player var1, String var2, boolean var3) {
      if ((1291096397 * (1291096397 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var4 = this.Xqedii_7rxyqib.get(var1.getUniqueId());
         if (var4 == null) {
            var1.removeMetadata(Xqedii_2k6237uggi4ju(var2), this.Xqedii_rrdyomu);
         } else {
            Xqedii_2t8d8j4 var5 = (Xqedii_2t8d8j4)var4.remove(var2);
            if (var5 == null) {
               var1.removeMetadata(Xqedii_2tgbwxg6tgxh8(var2), this.Xqedii_rrdyomu);
               if (var4.isEmpty()) {
                  this.Xqedii_7rxyqib.remove(var1.getUniqueId());
               }
            } else {
               this.Xqedii_ffdt8d5.remove(var5.Xqedii_216y0r3.getUniqueId());
               if (this.isBossbarSession(var1)) {
                  this.Xqedii_335nuvf(var1);
                  this.Xqedii_1ofz50z(var1, var5);
               } else {
                  var5.Xqedii_1co5qbi = (Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(369164657, 25) != 0);
                  this.Xqedii_202hfuk(var1, new WrapperPlayServerDestroyEntities(var5.Xqedii_1qxgv1w));
               }

               if (var3 && var5.Xqedii_216y0r3 != null && var5.Xqedii_216y0r3.isValid() && !var5.Xqedii_216y0r3.isDead()) {
                  var5.Xqedii_216y0r3.remove();
               }

               var1.removeMetadata(Xqedii_2998mo3638fy1(var2), this.Xqedii_rrdyomu);
               if (var4.isEmpty()) {
                  this.Xqedii_7rxyqib.remove(var1.getUniqueId());
               }

               this.Xqedii_2ebi45u(var1);
            }
         }
      }
   }

   @Override
   public void Xqedii_32cmird(Player var1, Xqedii_2t8d8j4 var2) {
      if ((1265772141 * (1265772141 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         if (this.isBossbarSession(var1)) {
            this.Xqedii_335nuvf(var1);
            if (!var2.Xqedii_1sln62g) {
               return;
            }
         }

         Location var3 = this.Xqedii_1ew3c3r(var1);
         WrapperPlayServerSpawnEntity var4 = new WrapperPlayServerSpawnEntity(
            var2.Xqedii_1qxgv1w,
            var2.Xqedii_2pz488q,
            EntityTypes.TEXT_DISPLAY,
            new dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.world.Location(
               var3.getX(),
               var3.getY(),
               var3.getZ(),
               Float.intBitsToFloat(Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(-241461989, 16)),
               Float.intBitsToFloat(Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(-2135389440, 8))
            ),
            Float.intBitsToFloat(Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(-1942789881, 3)),
            Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2052935736, 9),
            null
         );
         this.Xqedii_202hfuk(var1, var4);
         var2.Xqedii_1co5qbi = (Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(409387551, 4) != 0);
         this.Xqedii_lm8qrbj(var1, var2);
      }
   }

   @Override
   public void Xqedii_3m5yvig(Player var1, Xqedii_2t8d8j4 var2) {
      if ((-352809279 * (-352809279 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         int var3 = var2.Xqedii_1aoqfs7;
         int var4 = var2.Xqedii_1d8vptj;
         var2.Xqedii_1aoqfs7 = this.Xqedii_1h50evl(var1);
         var2.Xqedii_1d8vptj = Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(552699500, 24);
         this.Xqedii_32cmird(var1, var2);
         var2.Xqedii_1aoqfs7 = var3;
         var2.Xqedii_1d8vptj = var4;
      }
   }

   @Override
   public void Xqedii_1ofz50z(Player var1, Xqedii_2t8d8j4 var2) {
      if ((-1976245468 * -1976245468 + -1976245468 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.Xqedii_1co5qbi) {
         var2.Xqedii_1co5qbi = (Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(-666770676, 17) != 0);
         this.Xqedii_202hfuk(var1, new WrapperPlayServerDestroyEntities(var2.Xqedii_1qxgv1w));
      }
   }

   public void Xqedii_1cm7rtz(Player var1, Set<String> var2) {
      if ((640293867 * 640293867 + 640293867 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && this.isBossbarSession(var1)) {
         Map var3 = this.Xqedii_7rxyqib.get(var1.getUniqueId());
         if (var3 != null && !var3.isEmpty()) {
            int var4 = Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(583016494, 22);

            for (Xqedii_2t8d8j4 var6 : (Iterable<Xqedii_2t8d8j4>) (var3.values())) {

               int var7 = var2 != null && var2.contains(var6.Xqedii_26svack)
                  ? Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(269947264, 5)
                  : Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(-241461989, 26);
               if ((var7 != 0) != var6.Xqedii_1sln62g) {
                  var6.Xqedii_1sln62g = (var7 != 0);
                  if (var7 != 0) {
                     this.Xqedii_3m5yvig(var1, var6);
                     var4 = Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(24188929, 9);
                  } else {
                     this.Xqedii_1ofz50z(var1, var6);
                  }
               }
            }

            if (var4 != 0) {
               this.Xqedii_2ebi45u(var1);
            }
         }
      }
   }

   public void Xqedii_lm8qrbj(Player var1, Xqedii_2t8d8j4 var2) {
      if ((-1800587737 * (-1800587737 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         if (this.isBossbarSession(var1)) {
            this.Xqedii_335nuvf(var1);
            if (!var2.Xqedii_1sln62g) {
               return;
            }
         }

         List var3 = this.Xqedii_11uv5iw(var1, var2);
         this.Xqedii_202hfuk(var1, new WrapperPlayServerEntityMetadata(var2.Xqedii_1qxgv1w, var3));
      }
   }

   public void Xqedii_19o8tyr(Player var1, Xqedii_2t8d8j4 var2) {
      if ((310615112 * 310615112 + 310615112 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         if (this.isBossbarSession(var1)) {
            this.Xqedii_335nuvf(var1);
            if (!var2.Xqedii_1sln62g) {
               return;
            }
         }

         List var3 = this.Xqedii_1r5oi3u(var1, var2);
         this.Xqedii_202hfuk(var1, new WrapperPlayServerEntityMetadata(var2.Xqedii_1qxgv1w, var3));
      }
   }

   public void Xqedii_32k228o(Player var1, Xqedii_2t8d8j4 var2) {
      if ((16550792 * 16550792 - 16550792 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         if (this.isBossbarSession(var1)) {
            this.Xqedii_335nuvf(var1);
            if (!var2.Xqedii_1sln62g) {
               return;
            }
         }

         ArrayList var3 = new ArrayList(Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241461988, 25));
         var3.add(this.Xqedii_3j2r6hy(var2));
         this.Xqedii_202hfuk(var1, new WrapperPlayServerEntityMetadata(var2.Xqedii_1qxgv1w, var3));
      }
   }

   public void Xqedii_3un4hkd(Player var1, Xqedii_2t8d8j4 var2) {
      if ((-1051897738 ^ -1051897738) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         if (this.isBossbarSession(var1)) {
            this.Xqedii_335nuvf(var1);
            if (!var2.Xqedii_1sln62g) {
               return;
            }
         }

         ArrayList var3 = new ArrayList(Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(48377858, 10));
         var3.add(this.Xqedii_29tn4kf(var2));
         this.Xqedii_202hfuk(var1, new WrapperPlayServerEntityMetadata(var2.Xqedii_1qxgv1w, var3));
      }
   }

   public EntityData<?> Xqedii_29tn4kf(Xqedii_2t8d8j4 var1) {
      if ((1552472596 * 1552472596 - 1552472596 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         int var2 = HudPositionCalculator.clipOpacity(this.Xqedii_3clos9k(var1.Xqedii_n6wcxc4), var1.Xqedii_g4y6hze);
         return new EntityData(Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(1724100376, 12), EntityDataTypes.BYTE, (byte)var2);
      }
   }

   public EntityData<?> Xqedii_3j2r6hy(Xqedii_2t8d8j4 var1) {
      if ((-2129280761 * -2129280761 - -2129280761 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var2 = this.Xqedii_227rzha(var1);
         var1.Xqedii_z1s1km7 = var1.Xqedii_x7si9k7;
         return new EntityData(Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(-1977612277, 20), EntityDataTypes.COMPONENT, var2);
      }
   }

   public static int Xqedii_1r00iks(int var0) {
      if ((1714820115 * 1714820115 + 1714820115 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         int var1 = HudPositionCalculator.clipRedBit(var0);
         int var2 = HudPositionCalculator.clipGreenBit(var0);
         return var1 >= 0 && var2 >= 0 ? var1 << Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(430999494, 10) | var2 : Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(269947200, 5);
      }
   }

   public static Component Xqedii_3a37jq3(Component var0, int var1, int var2) {
      if ((692807071 ^ 692807071) != 0) {
         throw new IllegalStateException();
      } else {
         TextColor var3 = var0.color();
         Component var4 = var3 == null ? var0 : var0.color(Xqedii_1n9fb1g(var3.value(), var1, var2));
         List var5 = var4.children();
         if (var5.isEmpty()) {
            return var4;
         } else {
            ArrayList var6 = new ArrayList(var5.size());

            for (Component var8 : (Iterable<Component>) (var5)) {

               var6.add(Xqedii_3a37jq3(var8, var1, var2));
            }

            return var4.children(var6);
         }
      }
   }

   public static TextColor Xqedii_1n9fb1g(int var0, int var1, int var2) {
      if ((1731576539 * 1731576539 + 1731576539 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         int var3 = var0 >> Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(979193912, 26) & Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(389095440, 13) | var1;
         int var4 = var0 >> Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2052936760, 7) & Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(-141806746, 27) | var2;
         return TextColor.color(var3, var4, var0 & Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(-1705160665, 29));
      }
   }

   public void Xqedii_2d84u6r(Player var1, Xqedii_2t8d8j4 var2) {
      if ((1581007200 * (1581007200 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         if (this.isBossbarSession(var1)) {
            this.Xqedii_335nuvf(var1);
            if (!var2.Xqedii_1sln62g) {
               return;
            }
         }

         ArrayList var3 = new ArrayList(Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2052931640, 12));
         var3.add(
            new EntityData(
               Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(24200705, 9),
               EntityDataTypes.INT,
               var2.Xqedii_1rxjdzu > 0 ? var2.Xqedii_1rxjdzu : Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(979193918, 27)
            )
         );
         this.Xqedii_202hfuk(var1, new WrapperPlayServerEntityMetadata(var2.Xqedii_1qxgv1w, var3));
      }
   }

   public void Xqedii_31dtdwb(Player var1, Xqedii_2t8d8j4 var2) {
      if ((529590315 * 529590315 - 529590315 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         if (this.isBossbarSession(var1)) {
            this.Xqedii_335nuvf(var1);
            if (!var2.Xqedii_1sln62g) {
               return;
            }
         }

         ArrayList var3 = new ArrayList(Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(4217926, 31));
         var3.add(
            new EntityData(
               Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(-1019863560, 7), EntityDataTypes.BYTE, this.Xqedii_1n1qloj(var1, this.Xqedii_3r2d8xo(var2.Xqedii_1xdxr33))
            )
         );
         this.Xqedii_202hfuk(var1, new WrapperPlayServerEntityMetadata(var2.Xqedii_1qxgv1w, var3));
      }
   }

   public String Xqedii_227rzha(Xqedii_2t8d8j4 var1) {
      if ((-1590210492 * -1590210492 - -1590210492 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var2 = var1.Xqedii_22p5vll == null ? Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("h9MubXKHdBnmapjBCAovVxgCnxJJS+yY3i2U/A==") : var1.Xqedii_22p5vll;
         int var3 = Xqedii_1r00iks(var1.Xqedii_g4y6hze);
         if (var1.Xqedii_2upyzdn != null && var2.equals(var1.Xqedii_1tg0aim) && var1.Xqedii_x7si9k7 == var3) {
            return var1.Xqedii_2upyzdn;
         } else {
            Component var4 = Xqedii_174tdpp.deserialize(this.Xqedii_wgkgkwo(var2));
            if (var3 >= 0) {
               int var5 = var3 >> Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241461988, 29) & Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241461988, 10);
               int var6 = var3 & Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241461988, 17);
               var4 = Component.text(
                     Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("h9MubXKHdBnmapjBCAovVxgCnxJJS+yY3i2U/A=="),
                     Xqedii_1n9fb1g(Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(387006512, 13), var5, var6)
                  )
                  .append(Xqedii_3a37jq3(var4, var5, var6));
            }

            String var7 = (String)Xqedii_3a0pbug.serialize(var4);
            var1.Xqedii_1tg0aim = var2;
            var1.Xqedii_2upyzdn = var7;
            var1.Xqedii_x7si9k7 = var3;
            return var7;
         }
      }
   }

   public List<EntityData<?>> Xqedii_1r5oi3u(Player var1, Xqedii_2t8d8j4 var2) {
      if (((-1287113874 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Quaternionf var3 = var2.Xqedii_3uxvg68 == null ? this.Xqedii_1gh33bq(var1) : new Quaternionf(var2.Xqedii_3uxvg68);
         Quaternionf var4 = var2.Xqedii_2cxxagp == null
            ? new Quaternionf(
               Float.intBitsToFloat(Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2052935736, 2)),
               Float.intBitsToFloat(Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2052935736, 10)),
               Float.intBitsToFloat(Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(33743404, 2)),
               Float.intBitsToFloat(Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(1906021851, 8))
            )
            : new Quaternionf(var2.Xqedii_2cxxagp);
         Vector var5 = var2.Xqedii_2e2q47u == null ? new Vector() : var2.Xqedii_2e2q47u;
         if (this.Xqedii_37f0irz(var1)) {
            var5 = this.Xqedii_2t8xfmi(var5, var2.Xqedii_1pjv5dx);
         }

         Quaternionf var6 = var2.Xqedii_6apav31 == null ? this.Xqedii_4zg6flq(var1) : var2.Xqedii_6apav31;
         Vector3f var7 = this.getLocationVector(var5, var2.Xqedii_1pjv5dx, null, var2.Xqedii_1wwp4eo, var1);
         int var8 = HudPositionCalculator.clipBand(var2.Xqedii_g4y6hze);
         if (var8 > 0) {
            var7.x = var7.x + (float)(var8 * Double.longBitsToDouble(Xqedii_2bch2cw.Xqedii_2wk5193jkv3hy(8434498949765042528L, 27)));
         }

         Vector3f var9 = var6.transform(this.Xqedii_1iem95w(var7, var1));
         Vector3f var10 = this.Xqedii_1iem95w(this.getScaleVector(var2.Xqedii_1pjv5dx), var1);
         int var11 = var8 > 0 ? HudPositionCalculator.clipPackedLight(var2.Xqedii_g4y6hze) : Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(-1168207813, 26);
         int var12 = Xqedii_38o2zes.Xqedii_316oplq() <= var2.Xqedii_k1vor1v
            ? Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2052935720, 4)
            : Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2052935736, 15);
         ArrayList var13 = new ArrayList();
         var13.add(
            new EntityData(
               Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241396452, 16),
               EntityDataTypes.INT,
               var12 != 0
                  ? Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(-241461989, 27)
                  : Math.max(Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(1105399000, 25), var2.Xqedii_1d8vptj)
            )
         );
         var13.add(
            new EntityData(
               Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2052921400, 11),
               EntityDataTypes.INT,
               var12 != 0
                  ? Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(409387535, 4)
                  : Math.max(Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(1814098310, 16), var2.Xqedii_1aoqfs7)
            )
         );
         var13.add(
            new EntityData(Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(-1357355326, 28), EntityDataTypes.INT, Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(774029344, 14))
         );
         var13.add(
            new EntityData(
               Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(774209568, 14),
               EntityDataTypes.VECTOR3F,
               new dev.xqedii.ultimateui.libs.packetevents.packetevents.util.Vector3f(var9.x, var9.y, var9.z)
            )
         );
         var13.add(
            new EntityData(
               Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(1508052184, 25),
               EntityDataTypes.VECTOR3F,
               new dev.xqedii.ultimateui.libs.packetevents.packetevents.util.Vector3f(var10.x, var10.y, var10.z)
            )
         );
         var13.add(
            new EntityData(Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2052929592, 11), EntityDataTypes.QUATERNION, new Quaternion4f(var3.x, var3.y, var3.z, var3.w))
         );
         var13.add(
            new EntityData(Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(-1019865224, 7), EntityDataTypes.QUATERNION, new Quaternion4f(var4.x, var4.y, var4.z, var4.w))
         );
         var13.add(
            new EntityData(
               Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(1744962274, 26),
               EntityDataTypes.BYTE,
               Byte.valueOf((byte)Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(369164657, 25))
            )
         );
         var13.add(new EntityData(Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(-242848720, 0), EntityDataTypes.INT, var11));
         if (var2.Xqedii_bl9xu45) {
            var2.Xqedii_bl9xu45 = (Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(193507336, 12) != 0);
            var13.add(this.Xqedii_29tn4kf(var2));
            if (Xqedii_1r00iks(var2.Xqedii_g4y6hze) != var2.Xqedii_z1s1km7) {
               var13.add(this.Xqedii_3j2r6hy(var2));
            }
         }

         return var13;
      }
   }

   public boolean Xqedii_37f0irz(Player var1) {
      if (((1508565796 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(-1333541351, 18) != 0);
      } else {
         try {
            ClientVersion var2 = PacketEvents.getAPI().getPlayerManager().getClientVersion(var1);
            return ((var2 == ClientVersion.V_1_21
               ? Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(402719089, 25)
               : Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(193507336, 12)) != 0);
         } catch (Throwable var3) {
            return (Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(-241461989, 9) != 0);
         }
      }
   }

   public Vector Xqedii_2t8xfmi(Vector var1, Vector var2) {
      if (((-881314340 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return new Vector();
      } else {
         double var3 = var2 == null
            ? Double.longBitsToDouble(Xqedii_2bch2cw.Xqedii_2wk5193jkv3hy(8434498949731062112L, 31))
            : Math.max(
               Double.longBitsToDouble(Xqedii_2bch2cw.Xqedii_3ojd45vbzm5hf(8445646055835567085L, 44)),
               var2.getY() * Double.longBitsToDouble(Xqedii_2bch2cw.Xqedii_2q59rxnk2xns2(2794342208991821045L, 58))
            );
         double var5 = var3 / Double.longBitsToDouble(Xqedii_2bch2cw.Xqedii_1wd3osxg7nync(3895250221228074122L, 29));
         double var7 = var5 * Double.longBitsToDouble(Xqedii_2bch2cw.Xqedii_2q59rxnk2xns2(5968173685856574710L, 39));
         if (Double.isFinite(var7) && !(var7 <= Double.longBitsToDouble(Xqedii_2bch2cw.Xqedii_2wk5193jkv3hy(8434498949731062112L, 2)))) {
            Vector var9 = var1.clone();
            var9.setY(var9.getY() + var7);
            return var9;
         } else {
            return var1;
         }
      }
   }

   public List<EntityData<?>> Xqedii_11uv5iw(Player var1, Xqedii_2t8d8j4 var2) {
      if ((-2084106787 * (-2084106787 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         TextAlignment var3 = this.Xqedii_3r2d8xo(var2.Xqedii_1xdxr33);
         var2.Xqedii_bl9xu45 = (Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(1611667217, 29) != 0);
         List var4 = this.Xqedii_1r5oi3u(var1, var2);
         var4.add(this.Xqedii_3j2r6hy(var2));
         var4.add(
            new EntityData(
               Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(-242848712, 0),
               EntityDataTypes.INT,
               var2.Xqedii_1rxjdzu > 0 ? var2.Xqedii_1rxjdzu : Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(215601635, 9)
            )
         );
         var4.add(new EntityData(Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(1944259800, 25), EntityDataTypes.INT, Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(-241461989, 25)));
         var4.add(this.Xqedii_29tn4kf(var2));
         var4.add(new EntityData(Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(1275134321, 25), EntityDataTypes.BYTE, this.Xqedii_1n1qloj(var1, var3)));
         return var4;
      }
   }

   public byte Xqedii_1n1qloj(Player var1, TextAlignment var2) {
      if ((1816819396 * (1816819396 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         int var3 = Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(-846979536, 13);
         if (var2 == TextAlignment.LEFT) {
            var3 = (byte)(var3 | Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241455844, 11));
         } else if (var2 == TextAlignment.RIGHT) {
            var3 = (byte)(var3 | Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241460708, 8));
         }

         return (byte)var3;
      }
   }

   public Entity Xqedii_2i4mlqg(Player var1, String var2, int var3) {
      if ((-1182838226 * -1182838226 - -1182838226 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         UUID var4 = UUID.randomUUID();
         HashMap var5 = new HashMap();
         AtomicBoolean var6 = new AtomicBoolean((Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(1174421596, 23) != 0));
         InvocationHandler var7 = (var7x, var8, var9) -> {
            if ((1597758188 * 1597758188 + 1597758188 & 1) != 0) {
               throw new IllegalStateException();
            } else {
               String var10 = var8.getName();
               int var12 = Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(-2026059281, 31);
               switch (var10.hashCode()) {
                  case -2044455981:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("x50ON47l/WzdQgN/NuRuYUKXhbx0khPbQaZS/mCZzJDMpa62KGtYRptDUyUwQMJSpx25qNs+6nk="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(1315203812, 29);
                     }
                     break;
                  case -1870514199:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("qt67xOlXP617XLdyQs/NSmxgQtuCKblA/x1tN7gfJsWgy5OcBdl86bt0vmH3YbijO8w="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(-1037100954, 20);
                     }
                     break;
                  case -1776922004:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("tnuPjCQFMmNuTQ1jat8CYA4DxAvGEU8IMRcAMhanw5KgN4d8u4Mer83IbuM="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241232612, 14);
                     }
                     break;
                  case -1295482945:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("wYMznVC5aXOpJ06Lotk4pOmACN5NkzqEu6Y3WJ2/09Ezx6lCh6rYsA=="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(905021036, 24);
                     }
                     break;
                  case -1180575570:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("cwbik77ZnMSAsyPFCQzXeSOEFIaSqd/k6ZkrFO2GbwGAesSF1WC9LA=="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241462012, 2);
                     }
                     break;
                  case -1082711815:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("yATnPI4koGd0BV9OGaLSah/YZaGeX5heUrj0i8gmMcUCFRKFZYNRv+Jrpt4MwrHbKy4gnw=="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(1074796307, 29);
                     }
                     break;
                  case -934610812:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("FNmwS/V5Ns12d66byGG9viu0EW7liULLQYDPVgVtPaW0w+wFQalF9A=="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241396452, 16);
                     }
                     break;
                  case -316023509:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("GuxniSKrUAbNXbNn253zB3TnBctMDaPmQoRuBqNlwCWurH26jJVGNDNTA9hP1Ha8wyg="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(-402389564, 27);
                     }
                     break;
                  case -75106384:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("KyB4j7Qy41hY4KdrVVX5BHs6X5b1UTETUoYarakPYHzBGgeEDqXCxxl3"))) {
                        var12 = Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2052935696, 2);
                     }
                     break;
                  case -24199724:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("PictvIya5C+hc9krqn8VnEGWW9nJHkduvfB4k7N5tHVk1tvJOIaYbNtqcDV2bk1pOqg="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(2108964, 30);
                     }
                     break;
                  case 96533713:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("/C5LBnbJtxEamyjwV71Rq+tt2AUM4PiWdar4CJIeTXEvMtNS80M="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241461860, 3);
                     }
                     break;
                  case 147696667:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("1aN5/FWSp28LuODHG//VZqfrKr/Z722qc/ZsGhsbPOL4ubVIaNgr8sSQNm8="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(818775710, 5);
                     }
                     break;
                  case 375126662:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("EnkeLblepP1JkklBdSwum5nGUt5Go2dMSX/IhLnNPuibnUQ97H+hnNDTEb+zYJkNbof8LkFWOdALbQ=="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(243034852, 17);
                     }
                     break;
                  case 477886223:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("muDFTbOHPcbZejZEchTkUgTfDz6n2WWI2T1YQKe4ev4EdTS6oR4YGhUwLha7li/HlJ31EBIv"))) {
                        var12 = Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(235694820, 19);
                     }
                     break;
                  case 1166790774:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("prxws4GGx6JNbZMlF/FvtsNppUh6TbucXGoVdDWFP54MIZ6UkqMgH6VOGWTon+XIWhOqFoB4iPPZAQ=="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2055032888, 17);
                     }
                     break;
                  case 1378929398:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("IevgRHj9ld53MieYmkB0RHz3fJX4BplmC19P+eK0Wfj1EtUhaeDmdjxR2WZICD4b"))) {
                        var12 = Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241502948, 13);
                     }
                     break;
                  case 1539316145:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("zKcDIR+VFhjfdKovzs0ODf07NtiNFP4zgNNIuXJcDf9hFYd7ko8FxgVXC8z4cTXfmX4="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(-241461989, 30);
                     }
                     break;
                  case 1627895973:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("LCYn3ErvFSOzPsF6WSWBciTtvLDOj7RE6lVvZUE7j1hnd5t1xYqIB65AtOXQ/uLsmuY="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241461988, 18);
                     }
                     break;
                  case 1783439938:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("CQ2ZhUSt5uA+5vkzxSl9Fq79+6S0WDFDhv08L3NUZ8nEXNOo6oOyiwR57z3HEPU3yn4="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(243034852, 19);
                     }
                     break;
                  case 1949019648:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("Chs7kNF5E7M4oUAgYgYsyU8ECggpyI3+8/fQVYdPRMsJ5cac4IazOo7EHKZv9oiNgHY="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(8435860, 0);
                     }
                     break;
                  case 1969144284:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("7vvyOVqIPKZonGLuAIvXZ0JnvQHMiB/tb5yK0RHfyNLQCAggcBZxrPwbOUA="))) {
                        var12 = Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(442323001, 29);
                     }
                     break;
                  case 2073378034:
                     if (var10.equals(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("td++M5gUydNOQrz8jbF1PgkKZ5fFw5uyi+cEKd+UQPwyXQK81NhodwjQ"))) {
                        var12 = Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(1548255296, 15);
                     }
               }
               return switch (var12) {
                  case 0 -> {
                     if (var9 != null
                        && var9.length == Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(431000518, 10)
                        && var9[Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2052935736, 0)] instanceof String var18
                        && var9[Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(409387551, 4)] instanceof MetadataValue var22) {
                        var5.put(var18, var22);
                     }

                     yield null;
                  }
                  case 1 -> {
                     if (var9 != null
                        && var9.length == Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2048741432, 22)
                        && var9[Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(539894464, 6)] instanceof String var17) {
                        MetadataValue var21 = (MetadataValue)var5.get(var17);
                        yield var21 == null ? Collections.emptyList() : Collections.singletonList(var21);
                     }

                     yield Collections.emptyList();
                  }
                  case 2 -> Boolean.valueOf(
                     ((var9 != null
                           && var9.length == Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2052919352, 14)
                           && var9[Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(430998470, 10)] instanceof String var16
                           && var5.containsKey(var16)
                        ? Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(8435852, 0)
                        : Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2052935736, 15)) != 0)
                  );
                  case 3 -> {
                     if (var9 != null
                        && var9.length == Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2069712952, 23)
                        && var9[Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2052935736, 21)] instanceof String var13
                        && var9[Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241461988, 9)] instanceof Plugin) {
                        var5.remove(var13);
                     }

                     yield null;
                  }
                  case 4 -> var4;
                  case 5 -> var3;
                  case 6 -> var6.get();
                  case 7 -> Boolean.valueOf(
                     ((!var6.get() ? Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(215499747, 9) : Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(-241461989, 3)) != 0)
                  );
                  case 8 -> {
                     var6.set((Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(-846979536, 13) != 0));
                     yield null;
                  }
                  case 9 -> null;
                  case 10 -> EntityType.MARKER;
                  case 11 -> var1.getWorld();
                  case 12 -> null;
                  case 13 -> Boolean.valueOf((Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(96753668, 11) != 0));
                  case 14 -> Collections.emptyList();
                  case 15, 16, 17 -> Boolean.valueOf((Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(369164657, 25) != 0));
                  case 18 -> var1.getLocation().clone();
                  case 19 -> Xqedii_gcwvm18ucj0k(var2, String.valueOf(var4));
                  case 20 -> var4.hashCode();
                  case 21 -> Boolean.valueOf(
                     ((var7x == (var9 != null && var9.length != 0 ? var9[Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(-241461989, 12)] : null)
                        ? Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(-242848735, 0)
                        : Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(-2001729525, 20)) != 0)
                  );
                  default -> this.Xqedii_ihvde5n(var8.getReturnType());
               };
            }
         };
         ClassLoader var10000 = Entity.class.getClassLoader();
         Class[] var10001 = new Class[Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241461988, 30)];
         var10001[Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2052935736, 29)] = Entity.class;
         return (Entity)Proxy.newProxyInstance(var10000, var10001, var7);
      }
   }

   public Object Xqedii_ihvde5n(Class<?> var1) {
      if (((102368678 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (!var1.isPrimitive()) {
         return null;
      } else if (var1 == boolean.class) {
         return Boolean.valueOf((Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(-2039734031, 8) != 0));
      } else if (var1 == char.class) {
         return Character.valueOf((char)Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(134973616, 4));
      } else if (var1 == byte.class) {
         return Byte.valueOf((byte)Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2052935736, 26));
      } else if (var1 == short.class) {
         return Short.valueOf((short)Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(1013029640, 30));
      } else if (var1 == int.class) {
         return Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(-241461989, 2);
      } else if (var1 == long.class) {
         return Xqedii_2bch2cw.Xqedii_3ojd45vbzm5hf(-1722555948923969846L, 9);
      } else if (var1 == float.class) {
         return Float.intBitsToFloat(Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(430998470, 10));
      } else {
         return var1 == double.class ? Double.longBitsToDouble(Xqedii_2bch2cw.Xqedii_2wk5193jkv3hy(8434498949731062112L, 15)) : null;
      }
   }

   public void Xqedii_38awrd9(Display var1) {
      if ((552248284 ^ 552248284) != 0) {
         throw new IllegalStateException();
      } else if (var1 instanceof ItemDisplay && var1.hasMetadata(Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("F0vZe6iHKjOW5Okn5RmpV2WRMj8KlmNffU6VncNnpeIXkQ=="))) {
         Player var2 = this.Xqedii_20ozntw(var1);
         if (var2 != null && var2.isOnline()) {
            Transformation var3 = var1.getTransformation();
            Vector3f var4 = var3.getTranslation();
            Vector3f var5 = var3.getScale();
            Quaternionf var6 = var3.getLeftRotation();
            Quaternionf var7 = var3.getRightRotation();
            ArrayList var8 = new ArrayList();
            var8.add(new EntityData(Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(1548320832, 15), EntityDataTypes.INT, var1.getInterpolationDelay()));
            var8.add(new EntityData(Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241593060, 14), EntityDataTypes.INT, var1.getInterpolationDuration()));
            var8.add(
               new EntityData(
                  Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(134973792, 4),
                  EntityDataTypes.VECTOR3F,
                  new dev.xqedii.ultimateui.libs.packetevents.packetevents.util.Vector3f(var4.x, var4.y, var4.z)
               )
            );
            var8.add(
               new EntityData(
                  Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(241490660, 12),
                  EntityDataTypes.VECTOR3F,
                  new dev.xqedii.ultimateui.libs.packetevents.packetevents.util.Vector3f(var5.x, var5.y, var5.z)
               )
            );
            var8.add(
               new EntityData(
                  Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(-1988098037, 20), EntityDataTypes.QUATERNION, new Quaternion4f(var6.x, var6.y, var6.z, var6.w)
               )
            );
            var8.add(
               new EntityData(Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(243165924, 17), EntityDataTypes.QUATERNION, new Quaternion4f(var7.x, var7.y, var7.z, var7.w))
            );
            Brightness var9 = var1.getBrightness();
            if (var9 != null) {
               var8.add(
                  new EntityData(
                     Xqedii_2bch2cw.Xqedii_2frdxp78jgfv8(506514822, 29),
                     EntityDataTypes.INT,
                     var9.getBlockLight() << Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2052935864, 5)
                        | var9.getSkyLight() << Xqedii_2bch2cw.Xqedii_3v10ikgkh1ng0(2136821816, 22)
                  )
               );
            }

            this.Xqedii_202hfuk(var2, new WrapperPlayServerEntityMetadata(var1.getEntityId(), var8));
         }
      }
   }

   public void Xqedii_1m1yjkp(Display var1, int var2) {
      if (((1546911064 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && Xqedii_21uwrul != null) {
         try {
            Method var10000 = Xqedii_21uwrul;
            Object[] var10002 = new Object[Xqedii_2bch2cw.Xqedii_1xiq3xptfseou(-1198784384, 16)];
            var10002[Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(-241461989, 9)] = Math.max(Xqedii_2bch2cw.Xqedii_2lrvp1s00qucf(-241461989, 13), var2);
            var10000.invoke(var1, var10002);
         } catch (Throwable var4) {
         }
      }
   }

   private static /* synthetic */ String Xqedii_3t98giw946skd(String string) {
        return Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("BhDBE2TUeG0f8wW9rW1KnECNxNMRKqBs29kMLl2nrjklFUfb") + string;
    }

    private static /* synthetic */ String Xqedii_2k6237uggi4ju(String string) {
        return Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("BhDBE2TUeG0f8wW9rW1KnECNxNMRKqBs29kMLl2nrjklFUfb") + string;
    }

    private static /* synthetic */ String Xqedii_2tgbwxg6tgxh8(String string) {
        return Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("BhDBE2TUeG0f8wW9rW1KnECNxNMRKqBs29kMLl2nrjklFUfb") + string;
    }

    private static /* synthetic */ String Xqedii_2998mo3638fy1(String string) {
        return Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("BhDBE2TUeG0f8wW9rW1KnECNxNMRKqBs29kMLl2nrjklFUfb") + string;
    }

    private static /* synthetic */ String Xqedii_gcwvm18ucj0k(String string, String string2) {
        return Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("ZYwFnuskwA4+Yv+SKJBwDoMyS+yvB5UB1HbGBOQmodBMopucYkZbmv7xYU5gm+L9b3p8FQ6yIjYu/0ytP9U=") + string + Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("+WVAsZqr1cQ8KyUT3czRnyvcxYP8RyWUsKyxORuc") + string2 + Xqedii_2bch2cw.Xqedii_sqklmrx8mjk8("6KKEeQ/XE+Jv0asqI3IOFXRTWArTNnXLwaRt1fTB");
    }
}
