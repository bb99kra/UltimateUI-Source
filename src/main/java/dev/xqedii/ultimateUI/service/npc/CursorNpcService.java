package dev.xqedii.ultimateUI.service.npc;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.Xqedii_1ino5c0;
import dev.xqedii.ultimateUI.Xqedii_1uzjgh7;
import dev.xqedii.ultimateUI.Xqedii_38o2zes;
import dev.xqedii.ultimateUI.NpcPluginType;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.PacketEvents;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.PacketEventsAPI;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerCommon;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketReceiveEvent;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType.Play.Client;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity.InteractAction;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class CursorNpcService {
   public static final String Xqedii_1hikexruw1ay2 = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";
   public static final long Xqedii_fkxkrsm = 10L;
   public static final double Xqedii_2tuhjn4 = 48.0;
   public static final double Xqedii_3nqk1oy = 6.0;
   public static final double Xqedii_tqanmht = 1.0;
   public static final int Xqedii_1sukq6r = 5;
   public static final String Xqedii_24q46dq = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
      "7goOHpxftmHDL1oFmSMIUJ3gS09BB4ISz2249Ft9hQXGgOZJ4Y9REcr9i9phlgkO24G/0eKWkN4CNeSq4K+UpFaCJAU="
   );
   public final UltimateUI Xqedii_fqsswts;
   public final Map<UUID, Xqedii_1uzjgh7> Xqedii_bujm9m1 = new ConcurrentHashMap<>();
   public final Map<Integer, UUID> Xqedii_2apu34a = new ConcurrentHashMap<>();
   public final Set<UUID> Xqedii_3e2vkr1 = ConcurrentHashMap.newKeySet();
   public PacketListenerCommon Xqedii_2rymnfq;
   public volatile boolean Xqedii_17xnycq = (Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-142414698, 7) != 0);
   public volatile NpcPluginType Xqedii_1cvkbow = NpcPluginType.NONE;
   public volatile boolean Xqedii_3ho74ti;
   public volatile Object Xqedii_2r435rn;
   public volatile Object Xqedii_16ifz9w;
   public volatile Method Xqedii_1v0m5hk;
   public volatile Method Xqedii_2qwwfvm;

   public CursorNpcService(UltimateUI var1) {
      this.Xqedii_fqsswts = var1;
   }

   public boolean isEnabledInConfig() {
      if ((1236711863 * 1236711863 + 1236711863 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_fqsswts != null && this.Xqedii_fqsswts
            .getConfig()
            .getBoolean(
               Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("TLLt8CNoYg7/qrz+R3bEnz7TEbOm5ks3NgCevtnLg161lvT8mHute7Iqr2R0FTBvZZ7bwPqI+MCpmf0mppZ7DH1caSeQ9RKQoqk="),
               this.Xqedii_fqsswts
                  .getConfig()
                  .getBoolean(
                     Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
                        "ulHoXpfnBaXyhpklTeSQ5Lrp6jUrRHYURorC9XO2C0nEYntN8zoVVOk0Y6F8BE9HDf0z2tfNxCvGMAXYCBhsk+4c/pdwPlrxtJtpQY8W"
                     ),
                     (Xqedii_1ino5c0.Xqedii_2smplobxkxptv(633200827, 29) != 0)
                  )
            );
      }
   }

   public void enable() {
      if ((1003272442 ^ 1003272442) != 0) {
         throw new IllegalStateException();
      } else {
         this.Xqedii_1un38qu();
         this.Xqedii_362w6al();
      }
   }

   public void disable() {
      if (((-1842824457 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         this.Xqedii_17xnycq = (Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 4) != 0);

         for (UUID var2 : new ArrayList<>(this.Xqedii_bujm9m1.keySet())) {
            this.despawn(var2);
         }

         this.Xqedii_bujm9m1.clear();
         this.Xqedii_2apu34a.clear();
         this.Xqedii_3e2vkr1.clear();
         this.Xqedii_lk646ha();
         this.Xqedii_37o32ns();
      }
   }

   public void Xqedii_362w6al() {
      if ((-844141219 * -844141219 + -844141219 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         this.Xqedii_1cvkbow = NpcPluginType.NONE;
         Plugin var1 = Bukkit.getPluginManager().getPlugin(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("DJusKY04Mf++gqAMZte6RBIEYKoN3tLQPEYYWJsw6SE32hmsdp9d8NQT+F4="));
         if (var1 != null && var1.isEnabled() && this.Xqedii_q1xajp8()) {
            this.Xqedii_1cvkbow = NpcPluginType.CITIZENS;
            this.Xqedii_fqsswts
               .getLogger()
               .info(
                  Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
                     "B8D1By4OZG78fs3qTmrXuTwc0IUE/cjMmGboKajfpXRORYZdQ2A/SX44/1OGKUQXYyuxuxYC9zg6+6FzAaPOeCtAciuD44fmmXC3y9LNqIIc/Z01JiHukYzTUQxhPOQjKTnWzerbHzsivlxslV0a28Sn1yhdxTnlNDPq5T2M7WLp0HMB"
                  )
               );
         } else {
            Plugin var2 = Bukkit.getPluginManager()
               .getPlugin(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("zrEbxbVyrAkHTPrT/Q72dCFBA/sQVBLT4W6ITQCZAUJ/b4GqGltYbncVvHupdA=="));
            if (var2 != null && var2.isEnabled() && this.Xqedii_2idfv38()) {
               this.Xqedii_1cvkbow = NpcPluginType.FANCYNPCS;
               this.Xqedii_fqsswts
                  .getLogger()
                  .info(
                     Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
                        "kMWopGhBwkk0mU0MqxMK5u8SrL9frXWbSKTIxe9ocrAssUG0TFo1ZRXVH0+T0t7shQvSs5hyRBJVi7v2G04361fPlExeUNhaDfj3yexUoSRAM9Y9huyg2ClMhCjnmSMwJDdqw5PUOmANcLg+FJcZKUJF8uVjwuGveKq3NaMTazprzWM4PfE="
                     )
                  );
            } else {
               if (this.isEnabledInConfig()) {
                  this.Xqedii_fqsswts
                     .getLogger()
                     .warning(
                        Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
                           "cv+h4AB+q+1V6+JN6WVzs+px12JaiH2L67TdZ7SlCivPadElR7EXR4gBVDKlxP+ZfP2+Vrysp5m8kNR5URrUCQASzAP/rX/gAKrrS1XlsuOebi/LPHldHt1k9Z1T8K+YDOuIc9XqyIXpxIt6OoxtMuI6CaZtdvrmvfHgDJn/n2OE411VtYSjYe07CWv4H7GpubiGmMHC3r3QugEXFI7TjNG5yy6EzgS7xB3xoO80mRAiwn5M3AD9ih24wgSUnc2SgfY0Y2NUwNAHRv15rTm/vWo/Vuy0OKP5b74DCgnnRp4xZyWKetDeIkbQK/gEmew9MFyXk7IjybLTs4SQdcc92RrRxiVO71BDr0LHWnPIqOOkFbMZ/CbXFM/EVmfhCR+tZDibrIhoxjEuIUwzRT/2veIPvkLynzxa6OjTWwLhYfhrW88iFlEjXaqJS1UwPS6SK74AWlsfz6AvVDxPU5JD1fVJF1p1h15o/JQbjm7N0IHqz+tR1s3vaQgDnIgSRWbS3i42rbZndhWTZYIHOvMfxuRcTxZh8Ja37OG6kav85aoc6pK5KXuSdWGXatv9FaLtlbym9+HWbUvPN8VC+ZJTvvx/tUh6N78Mb5tMuw=="
                        )
                     );
               }
            }
         }
      }
   }

   public boolean Xqedii_q1xajp8() {
      if ((391887828 * 391887828 - 391887828 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         try {
            Class var1 = Class.forName(
               Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
                  "THXODUeG9/Qi1O9rPu1lQ9MxBvoNI+CTqGGmjF1bMJTJyhMuDGGhudwo++bj5HXt6LUfBAkfWh3e6ZNkZcB7dJ1Xu7w0NU5G7SE92TGVIV4CAJ3+qioMK96rUKg="
               )
            );
            String var10001 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
               "ev2tsENNsTsuion2JgZD+MFi4/l1WIFOzfKv1+CjCO3v8/Y9KPtkn8CSUx0RikYMC1zu9jWpj32LFu9YhI3oPNrbqLwRBUwDKhZHDrdM"
            );
            Class[] var10002 = new Class[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532793510, 16)];
            var10002[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 14)] = String.class;
            Method var2 = var1.getMethod(var10001, var10002);
            Object[] var10003 = new Object[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532793510, 1)];
            var10003[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(1885474528, 30)] = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
               "7goOHpxftmHDL1oFmSMIUJ3gS09BB4ISz2249Ft9hQXGgOZJ4Y9REcr9i9phlgkO24G/0eKWkN4CNeSq4K+UpFaCJAU="
            );
            this.Xqedii_2r435rn = var2.invoke(null, var10003);
            return ((this.Xqedii_2r435rn != null
               ? Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797748, 2)
               : Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(102821383, 2)) != 0);
         } catch (Throwable var3) {
            this.Xqedii_fqsswts.getLogger().warning(Xqedii_uen67ktco2a3(var3.getMessage()));
            this.Xqedii_2r435rn = null;
            return (Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(-524018240, 31) != 0);
         }
      }
   }

   public void Xqedii_37o32ns() {
      if (((-814638738 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Object var1 = this.Xqedii_2r435rn;
         this.Xqedii_2r435rn = null;
         if (var1 != null) {
            try {
               var1.getClass()
                  .getMethod(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("CJHU3nAfMwaFOieatHaESQDWS/GXDRiGYG1Ua5CMWrZtvPLwCvpQDAwOqOZpqIsb33SZFuPB"))
                  .invoke(var1);
            } catch (Throwable var3) {
            }
         }
      }
   }

   public boolean Xqedii_2idfv38() {
      if (((-1109791354 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         try {
            Class var1 = Class.forName(
               Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
                  "qm4m4zeJ7KG9z62TualL0jtqMv4SYXU/J9eMENXZXgBft6IrlN/LTt2C4i73EUIUrCExdyUGQVxBngJVhN73E8R98Vh36zdMNnalWcvMYLXzdyzJ5q0tLJwunFyUlC0yvBsb0h9KiNjZhQ=="
               )
            );
            Object var2 = var1.getMethod(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("Z5SeLUJNo72hAtrRZ78JGuLm1rmSJ5tBTyA2+gPr59o59w==")).invoke(null);
            if (var2 == null) {
               return (Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(1532793509, 18) != 0);
            } else {
               this.Xqedii_1v0m5hk = var1.getMethod(
                  Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("W3qAoIImiHneQRZ4pHurSo2WtLL0tCxqzS0PPYVDWIzxWxDuWFfPaI53T/UuIZwRuVfQ3tPy")
               );
               this.Xqedii_2qwwfvm = var1.getMethod(
                  Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("xjDLkp9GK1G7QPyZX2Odsoiv9t7yAEA9B+kCQAfrZR9SIkmycuL4vvfH1OvIvBSChgdLdOqX")
               );
               this.Xqedii_16ifz9w = var2;
               return (Xqedii_1ino5c0.Xqedii_dku2we86xfqb(2134233200, 28) != 0);
            }
         } catch (Throwable var3) {
            this.Xqedii_fqsswts.getLogger().warning(Xqedii_o4newan0klc6(var3.getMessage()));
            this.Xqedii_16ifz9w = null;
            return (Xqedii_1ino5c0.Xqedii_2smplobxkxptv(1541278642, 1) != 0);
         }
      }
   }

   public static Object Xqedii_12szsum(Class<?> var0, String var1) {
      if ((91665495 * (91665495 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var0 != null && var0.isEnum()) {
         Object[] var2 = var0.getEnumConstants();
         int var3 = var2.length;

         for (int var4 = Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-325650558, 23); var4 < var3; var4++) {
            Object var5 = var2[var4];
            if (var5 instanceof Enum var6 && var6.name().equals(var1)) {
               return var5;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public boolean isSpawned(UUID var1) {
      if ((1426268288 ^ 1426268288) != 0) {
         throw new IllegalStateException();
      } else {
         return ((var1 != null && this.Xqedii_bujm9m1.containsKey(var1)
            ? Xqedii_1ino5c0.Xqedii_2smplobxkxptv(1541278644, 1)
            : Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 16)) != 0);
      }
   }

   public void spawn(Player var1, Location var2) {
      if (((-1031359846 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.isOnline() && var2 != null && var2.getWorld() != null) {
         if (this.Xqedii_1cvkbow == NpcPluginType.NONE) {
            if (!this.Xqedii_3ho74ti) {
               this.Xqedii_3ho74ti = (Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(202933176, 28) != 0);
               this.Xqedii_fqsswts
                  .getLogger()
                  .warning(
                     Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
                        "IQOCNK8nz84iKUMxcB9ADJt1PeRio7c+RetFM3U4yOTSsqCzsbS1ZPOyH5dc3N9R1ll326bhaxv9SPkY6tQs3ou2BTnNs1MoaTdt2M2oKldtwHqTaWoy0I+9k909EUIC6eF1Dzr4PaKUGyDXmjF+RAFBKJCqPDXdyXiIKPBLdF51WDsEqdmt1l1BrqrEk/AkBcW488o7EoZgAtfb+HjrUoGUcu+76iEm07iGx/Mf+Jzvx4A8WFYeWZonaDKrcptpwSef0oXvVTUMMlzCEYF2rEMxuqtO03ZEgL1kTM3BeGM7g4EbZf2IN7UzzPRvzeiFBTHTiIoLQUxt8vNVf2oUu30bLq11UZVtYJID4cbaC8iQEY55ZGyiHhDwI9gMvA3YpeCYIajtskzv9Q=="
                     )
                  );
            }
         } else {
            this.despawn(var1.getUniqueId());

            Xqedii_1uzjgh7 var3 = switch (this.Xqedii_1cvkbow) {
               case NONE -> null;
               case CITIZENS -> this.Xqedii_1l0c6ud(var1, var2);
               case FANCYNPCS -> this.Xqedii_id6jy9p(var1, var2);
            };
            if (var3 != null) {
               this.Xqedii_bujm9m1.put(var1.getUniqueId(), var3);
               this.Xqedii_2apu34a.put(var3.Xqedii_23stnyr, var1.getUniqueId());
               this.Xqedii_abgu8mb(var1, var3);
               this.Xqedii_3spsnxx();
            }
         }
      }
   }

   public void despawn(Player var1) {
      if ((1490599901 * (1490599901 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         if (var1 != null) {
            this.despawn(var1.getUniqueId());
         }
      }
   }

   public void despawn(UUID var1) {
      if ((-1522731518 * (-1522731518 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         Xqedii_1uzjgh7 var2 = this.Xqedii_bujm9m1.remove(var1);
         this.Xqedii_3e2vkr1.remove(var1);
         if (var2 != null) {
            this.Xqedii_2apu34a.remove(var2.Xqedii_23stnyr);
            switch (var2.Xqedii_3ctqsuy) {
               case NONE:
               default:
                  break;
               case CITIZENS:
                  this.Xqedii_3afwrnm(var2.Xqedii_t5beb8k);
                  break;
               case FANCYNPCS:
                  this.Xqedii_308gjym(var2.Xqedii_t5beb8k);
            }

            var2.Xqedii_1yo3gt3.clear();
         }
      }
   }

   public boolean consumePendingForcedClose(UUID var1) {
      if ((1418620817 ^ 1418620817) != 0) {
         throw new IllegalStateException();
      } else {
         return ((var1 != null && this.Xqedii_3e2vkr1.remove(var1)
            ? Xqedii_1ino5c0.Xqedii_2smplobxkxptv(1170071739, 29)
            : Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(124794929, 13)) != 0);
      }
   }

   public Xqedii_1uzjgh7 Xqedii_1l0c6ud(Player var1, Location var2) {
      if (((-36763989 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Object var3 = this.Xqedii_2r435rn;
         if (var3 == null) {
            return null;
         } else {
            Object var4 = null;

            try {
               Class var10000 = var3.getClass();
               String var10001 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("efeRnbf8Dcydau0mNMfzfHI5ngALgIW41hRS7tvkSjZTWcTNG1QfCdif0XkHwg==");
               Class[] var10002 = new Class[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(126029943, 25)];
               var10002[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 23)] = EntityType.class;
               var10002[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797748, 2)] = String.class;
               Method var5 = var10000.getMethod(var10001, var10002);
               Object[] var13 = new Object[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-569657765, 9)];
               var13[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 27)] = EntityType.PLAYER;
               var13[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865798000, 8)] = var1.getName();
               var4 = var5.invoke(var3, var13);
               if (var4 == null) {
                  return null;
               } else {
                  this.Xqedii_b547vco(var4);
                  this.Xqedii_15htsj0(var4, var1);
                  this.Xqedii_3ac1x8t(var4, var1);
                  var10000 = var4.getClass();
                  var10001 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("vUjdlqdeQ29B+TobCyeKAi04jfgHSXeY0dAydFT34/qmtUs7/Os=");
                  var10002 = new Class[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(385319661, 31)];
                  var10002[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-284829395, 8)] = Location.class;
                  Method var6 = var10000.getMethod(var10001, var10002);
                  Object[] var15 = new Object[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532793510, 20)];
                  var15[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(-1059011555, 23)] = var2.clone();
                  Object var7 = var6.invoke(var4, var15);
                  if (Boolean.FALSE.equals(var7)) {
                     this.Xqedii_3afwrnm(var4);
                     return null;
                  } else if (var4.getClass()
                     .getMethod(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("zAaxAeKBs6XzqR/UVzeUq2cllSkejaSEzldS/OK60shg35+bpaXFwuPEaSVbWQ=="))
                     .invoke(var4) instanceof Entity var9) {
                     var1.hideEntity(this.Xqedii_fqsswts, var9);
                     return new Xqedii_1uzjgh7(var9.getEntityId(), var2.clone(), NpcPluginType.CITIZENS, var4);
                  } else {
                     this.Xqedii_3afwrnm(var4);
                     return null;
                  }
               }
            } catch (Throwable var10) {
               this.Xqedii_fqsswts.getLogger().warning(Xqedii_2j7urjtl4ml5d(var1.getName(), var10.getMessage()));
               if (var4 != null) {
                  this.Xqedii_3afwrnm(var4);
               }

               return null;
            }
         }
      }
   }

   public void Xqedii_b547vco(Object var1) {
      if ((-590613177 * -590613177 + -590613177 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         try {
            Class var10000 = var1.getClass();
            String var10001 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("Sq4K9gBpOsrAdNe0m6v+li/fA+jCr3VobpG8PzIASHSDho0T9H1fN9vTmcDgXoN4ZCjINQ==");
            Class[] var10002 = new Class[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(1756871703, 26)];
            var10002[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-1302602229, 25)] = boolean.class;
            Method var7 = var10000.getMethod(var10001, var10002);
            Object[] var10 = new Object[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532793510, 24)];
            var10[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-1139317577, 10)] = Boolean.valueOf((Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(-2134800325, 24) != 0));
            var7.invoke(var1, var10);
         } catch (Throwable var6) {
         }

         try {
            Object var2 = var1.getClass().getMethod(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("+Y1I85ftJytHccYxvpG4c4xuiWL+qpGxlCd3VyVMhFKHY2HN")).invoke(var1);
            Class var3 = Class.forName(
               Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
                  "O5PQdp37flcVm3gfyKObcPwkChq+CkgJaXnAh0jVgA324IAdfyBP/cOipeEGYDIKep+SmkDO2PRC/IXbZEAVCoJQCStQpYpQ7NPZRASC0NzivJ/Lis4QlC8lUkLMWgUW4LL3YtL7"
               )
            );
            Class var8 = var2.getClass();
            String var9 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("Fw2pR+p5aWSv6P7i38gl9DhxFZ+W0BAmt5BDBvBe+06FLA==");
            Class[] var11 = new Class[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865793648, 11)];
            var11[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(1532793509, 21)] = var3;
            var11[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532793510, 13)] = Object.class;
            Method var4 = var8.getMethod(var9, var11);
            this.Xqedii_30on786(
               var2,
               var4,
               var3,
               Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("aYx3QooWFw81biD8QnGIfjhJRXfcyX2hOfPUEkYl1ZhlcupWlF17SRoL1WQdF2mGiLO+4bvmdfIAICNV3/xRJVUd"),
               Boolean.valueOf((Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532793510, 28) != 0))
            );
            this.Xqedii_30on786(
               var2,
               var4,
               var3,
               Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("XXSUZpT2BPytJMGGBqPRWH8ZpeSnxWhjr+knj4yHlckl3/Q+DpyP6vNtCM/QYIxNJTikr7DNoN2jLu4AiG+dEWZLAD1sTvx4"),
               Boolean.valueOf((Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-781223890, 27) != 0))
            );
            this.Xqedii_30on786(
               var2,
               var4,
               var3,
               Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("mysKRLLpVkX4mlbmLFqFmypgt5Wo9w/hI4lQBQ4bW2tFVXU6aUw3E/uh5IFYZ70MFeQ="),
               Boolean.valueOf((Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(1532793509, 3) != 0))
            );
            this.Xqedii_30on786(
               var2,
               var4,
               var3,
               Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("5pD7lkjh9NI3AypdRnk5uvBkssKJj3EGOvtdn09yD3Yf/+CuRl76cNPSiEh8ct+y"),
               Boolean.valueOf((Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(1885474528, 30) != 0))
            );
            this.Xqedii_30on786(
               var2,
               var4,
               var3,
               Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("KzmRKAjFincJLzoSsV3tsPpIOoyPZZNcEaG60KEGtUVwytQ1lvAcUYXRiwosjpCKuXBCkD0zUKU="),
               (int)Math.ceil(Double.longBitsToDouble(Xqedii_1ino5c0.Xqedii_3p7j6na9w09z8(-2588242845620422742L, 50)))
            );
         } catch (Throwable var5) {
         }
      }
   }

   public void Xqedii_30on786(Object var1, Method var2, Class<?> var3, String var4, Object var5) {
      if (((-126719431 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         try {
            Object var6 = Xqedii_12szsum(var3, var4);
            if (var6 != null) {
               Object[] var10002 = new Object[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532859046, 16)];
               var10002[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(1870147273, 3)] = var6;
               var10002[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(2134233200, 28)] = var5;
               var2.invoke(var1, var10002);
            }
         } catch (Throwable var7) {
         }
      }
   }

   public void Xqedii_15htsj0(Object var1, Player var2) {
      if ((-62008766 * -62008766 - -62008766 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         try {
            Class var3 = Class.forName(
               Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
                  "A5v9UczqQ6yc+Dn46+nbZOJthXvAADnsltHDR30fu+ZZIbOMvVpnbhpvKHMeLXmTY/GkK6Em26MAZs5+ADi0xkQ5s9z3p7iGxR+q7RmuGfpsYMRBnVMAjaRe+Kw="
               )
            );
            Class var10000 = var1.getClass();
            String var10001 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("zWGAOKV0xikF41DpuqRQ/FsY9W6TXr9CKFrZlOkLNXb8Jci3/0NYcAGCQrgH2OmEQ3uRXT38");
            Class[] var10002 = new Class[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(822571032, 5)];
            var10002[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(1532793509, 13)] = Class.class;
            Method var6 = var10000.getMethod(var10001, var10002);
            Object[] var9 = new Object[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(2134233200, 28)];
            var9[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-142414698, 7)] = var3;
            Object var4 = var6.invoke(var1, var9);
            var10001 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("QSxn/NWgJJp7DTVJYX8KnyUjUjEEhJ8K6EkGDuwCrF4Zvoppe/5Jq9aqrjBDZ7DzGysUMBTIqoxKFY4ho5U=");
            var10002 = new Class[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532793510, 13)];
            var10002[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-1762163988, 31)] = Player.class;
            Method var7 = var3.getMethod(var10001, var10002);
            Object[] var11 = new Object[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(1570954992, 20)];
            var11[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-569658789, 9)] = var2;
            var7.invoke(var4, var11);
         } catch (Throwable var5) {
            this.Xqedii_fqsswts.getLogger().warning(Xqedii_2s39c0r3017kv(var2.getName(), var5.getMessage()));
         }
      }
   }

   public void Xqedii_3ac1x8t(Object var1, Player var2) {
      if ((1559343907 * (1559343907 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         try {
            Class var3 = Class.forName(
               Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
                  "hDB+TXIkicT7pzjYOoUydaHUJ6DpoYrzkyLnQpfWB+e1duWZOMJTN1snyIrXMvmFWSnD97gb3s4gdcflSc8qWAnefrOh4w7ao3wFCsJCHzaTkmx5wIdajyG+pT0d+Tdxt1nWYrZi2xBl4vrxjcLQ4g=="
               )
            );
            Class var4 = Class.forName(
               Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
                  "d0EQGZ8qz83eZDSXKuw9M3yZJAmMleeKeP1uTYXnli4+lAMOlWER4Ngyp5qLetG+A6KJxr4efZ2zxQelIcfIWnkvrkcKFrt0sqvW2lYTqOskZaCzBk4JspeArhbJTSa3kq7VHRalucGTx/kt8oUXNqdlzRWWDKhS2mmcOQnjgy1KNDCzC8J3O8zsqLc="
               )
            );
            Class var10000 = var1.getClass();
            String var10001 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("zWGAOKV0xikF41DpuqRQ/FsY9W6TXr9CKFrZlOkLNXb8Jci3/0NYcAGCQrgH2OmEQ3uRXT38");
            Class[] var10002 = new Class[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532793510, 26)];
            var10002[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 19)] = Class.class;
            Method var11 = var10000.getMethod(var10001, var10002);
            Object[] var13 = new Object[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797680, 6)];
            var13[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-2098424073, 15)] = var3;
            Object var5 = var11.invoke(var1, var13);
            var10001 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("Fw2pR+p5aWSv6P7i38gl9DhxFZ+W0BAmt5BDBvBe+06FLA==");
            var10002 = new Class[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532801702, 13)];
            var10002[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(1532793509, 27)] = var4;
            var10002[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(1880633607, 21)] = ItemStack.class;
            Method var6 = var3.getMethod(var10001, var10002);

            for (Entry var8 : this.Xqedii_32aawdz().entrySet()) {
               Object var9 = Xqedii_12szsum(var4, (String)var8.getKey());
               if (var9 != null) {
                  Object[] var15 = new Object[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-881081994, 30)];
                  var15[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 7)] = var9;
                  var15[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(811732704, 30)] = this.Xqedii_1zbr8kp(var2, (EquipmentSlot)var8.getValue());
                  var6.invoke(var5, var15);
               }
            }
         } catch (Throwable var10) {
            this.Xqedii_fqsswts.getLogger().warning(Xqedii_2slqv976uy4fk(var2.getName(), var10.getMessage()));
         }
      }
   }

   public Map<String, EquipmentSlot> Xqedii_32aawdz() {
      if ((-726604874 * -726604874 + -726604874 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         LinkedHashMap var1 = new LinkedHashMap();
         var1.put(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("U9Oj7XAYqRf7WjXAEfmErZjJsljoNwYKIzVTn2vL2v1Y6PeB"), EquipmentSlot.HAND);
         var1.put(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("CohDascLUsbo0W+TbBbmR8bHty5fEfAOr8C55EQ4xwhVjfONI5bQAgLv2xM="), EquipmentSlot.OFF_HAND);
         var1.put(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("JwrldFDAHzohqN+/dC+hqiYAJu/rATrPEGh7RuVDcKRmbvz6c1rN+g=="), EquipmentSlot.HEAD);
         var1.put(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("kFmbPI823QRn1TBcsChLa5etn3MueXxEk6PLxe5Jppsp9VS2GOZp874KxQ0qrVj/"), EquipmentSlot.CHEST);
         var1.put(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("gnLQ1mzwwqgSjWfOn2A/8zKcbTyhE5tz2s1mmHiyD7ayRpI/11GH7V2GaFg="), EquipmentSlot.LEGS);
         var1.put(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("k/lo21xq2Eqn+VT1yZD7iEieCrGSCn1LlarUov/GgJFvRpdBm8Q="), EquipmentSlot.FEET);
         return var1;
      }
   }

   public Map<String, EquipmentSlot> Xqedii_2oionzx() {
      if ((920697288 * (920697288 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         LinkedHashMap var1 = new LinkedHashMap();
         var1.put(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("pTWbi68VC5Y6Jwf83tBcJ1ZPW1Uxg0rqwMB+STDfDli+x9Bx89BRTebUjvw="), EquipmentSlot.HAND);
         var1.put(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("f/pRnswTDrsB4dbKeTJ3baUhWdMxYorAI9CGj2Cvu24waOMNFMwAih3o"), EquipmentSlot.OFF_HAND);
         var1.put(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("R4YycDIQylFcoY8AbfJGSKpj0BErjo+XymjJLE3Ubbyr/RCI"), EquipmentSlot.HEAD);
         var1.put(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("7ul7VGUdDkK9GgCC8hfEOqpOYRJEZXl+KeirrQe6JMeHmYif+4M="), EquipmentSlot.CHEST);
         var1.put(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("HnSYjtx1xwy4cXtCuNOo1is+RPzpazvNoGoFG+8mmFXTjYnF"), EquipmentSlot.LEGS);
         var1.put(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("b7DN92j3ZG30/CRIIu+w9AuHskKIOo0otlSOoc31Twp4rmXs"), EquipmentSlot.FEET);
         return var1;
      }
   }

   public ItemStack Xqedii_1zbr8kp(Player var1, EquipmentSlot var2) {
      if ((-1034346378 * -1034346378 + -1034346378 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         EntityEquipment var3 = var1.getEquipment();
         ItemStack var4 = var3 == null ? null : var3.getItem(var2);
         return var4 != null && !var4.getType().isAir() ? var4.clone() : new ItemStack(Material.AIR);
      }
   }

   public void Xqedii_3vodak3(Class<?> var1, Object var2, Player var3) {
      if (((433915083 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         try {
            Class var4 = null;
            String[] var10000 = new String[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532793478, 5)];
            var10000[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-1155154464, 21)] = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
               "8stk69vqzOjaBtJuWYuz0wGTx5cg0DRxqwucEnu2B0NufxAq2oOSlQWCGA5dThpIk3sLouL7rHGc0OwFdNwxivRyU5Vz2ovUdl3DQ6c3kCA6vLFz3jZVayH+b7MA0cU0KeEJmOs7qidyH+zpLMAvWHEbIqxkjLiU"
            );
            var10000[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-1562447779, 28)] = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
               "L+xhaOXpkj9MFfNZ1lNbFBwRCN379f/xANeVau2lwUTdEyBBAHfMYtywtgpZXcICwYTjrYu8Vu3nEpkR3scglDOAJlML6baUOHq96gF3abAVn3KnOJNNxKsszpW/P223gHSlkFfFgFnL/fU9"
            );
            String[] var5 = var10000;
            int var6 = var5.length;

            for (int var7 = Xqedii_1ino5c0.Xqedii_2smplobxkxptv(2076276299, 6); var7 < var6; var7++) {
               String var8 = var5[var7];

               try {
                  var4 = Class.forName(var8);
                  break;
               } catch (ClassNotFoundException var10) {
               }
            }

            if (var4 == null) {
               return;
            }

            String var10001 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("gNMVqGxVlIDjGkxOj/zjrHgWH7zUoRBJuYdVswUGgLtlKkET/OfidEstzKDOO+DSu1+Ihw==");
            Class[] var10002 = new Class[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532269222, 19)];
            var10002[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(1645142128, 6)] = var4;
            var10002[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797728, 4)] = ItemStack.class;
            Method var12 = var1.getMethod(var10001, var10002);

            for (Entry var14 : this.Xqedii_2oionzx().entrySet()) {
               ItemStack var15 = this.Xqedii_1zbr8kp(var3, (EquipmentSlot)var14.getValue());
               Object var9 = Xqedii_12szsum(var4, (String)var14.getKey());
               if (var9 != null && !var15.getType().isAir()) {
                  Object[] var16 = new Object[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-262294818, 12)];
                  var16[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 15)] = var9;
                  var16[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(-2134800325, 24)] = var15;
                  var12.invoke(var2, var16);
               }
            }
         } catch (Throwable var11) {
            this.Xqedii_fqsswts.getLogger().warning(Xqedii_2be6on02owlsq(var3.getName(), var11.getMessage()));
         }
      }
   }

   public void Xqedii_3afwrnm(Object var1) {
      if (((-2095741905 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         try {
            var1.getClass().getMethod(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("2RLZBRvLXnunCW6hWgTw3zFe/EkSEe+uLmS/D8sIAOXGa/MHVf0m11B4")).invoke(var1);
         } catch (Throwable var3) {
         }
      }
   }

   public void Xqedii_2okdf6y(Xqedii_1uzjgh7 var1, Player var2) {
      if (((68836320 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Entity var3 = this.Xqedii_2u2uieb(var1);
         if (var3 != null) {
            var2.showEntity(this.Xqedii_fqsswts, var3);
         }
      }
   }

   public void Xqedii_d47y92s(Xqedii_1uzjgh7 var1, Player var2) {
      if (((1692805380 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Entity var3 = this.Xqedii_2u2uieb(var1);
         if (var3 != null) {
            var2.hideEntity(this.Xqedii_fqsswts, var3);
         }
      }
   }

   public Entity Xqedii_2u2uieb(Xqedii_1uzjgh7 var1) {
      if (((-1874322398 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         try {
            return var1.Xqedii_t5beb8k
                  .getClass()
                  .getMethod(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("zAaxAeKBs6XzqR/UVzeUq2cllSkejaSEzldS/OK60shg35+bpaXFwuPEaSVbWQ=="))
                  .invoke(var1.Xqedii_t5beb8k) instanceof Entity var3
               ? var3
               : null;
         } catch (Throwable var4) {
            return null;
         }
      }
   }

   public Xqedii_1uzjgh7 Xqedii_id6jy9p(Player var1, Location var2) {
      if ((-1171250136 ^ -1171250136) != 0) {
         throw new IllegalStateException();
      } else if (this.Xqedii_16ifz9w != null && this.Xqedii_1v0m5hk != null && this.Xqedii_2qwwfvm != null) {
         Object var3 = null;

         try {
            Class var4 = Class.forName(
               Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
                  "hLWv1jGQS4GSwE3/ZWLEXX5HvSfniOX750nGvUluE7wZsmSJU/zFMDjYkzygu8sfFc/85kE2iGfoy/5Z401sP26BaTi/k9sQ6rs6yBS7HcBf/JoWorHU0+4V"
               )
            );
            Class[] var10001 = new Class[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532924582, 16)];
            var10001[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(822571064, 5)] = String.class;
            var10001[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(50733294, 26)] = UUID.class;
            var10001[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1869992048, 21)] = Location.class;
            Constructor var5 = var4.getConstructor(var10001);
            String var6 = Xqedii_2pit0iwxbsxj4(
               UUID.randomUUID()
                  .toString()
                  .replace(
                     Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("xThVSzD9h0H6IbK2XnXgPbwADUrNWa2ISuP42/Fo"),
                     Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("4X+KNUvho5vitAQSwWzlAO4RXqmLlBltNztobQ==")
                  )
                  .substring(Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(-2085086184, 12), Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1534104230, 18))
            );
            Object[] var24 = new Object[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-284828627, 8)];
            var24[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(-603059135, 19)] = var6;
            var24[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532793510, 9)] = var1.getUniqueId();
            var24[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-1139315529, 10)] = var2.clone();
            Object var7 = var5.newInstance(var24);
            String var25 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("EZ/CpKYHg1HRPbIVH6/VX6tSJIDHke07yJ+P7Sbi/OjBZns6gM9HQ9Ki");
            Class[] var10002 = new Class[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532793510, 15)];
            var10002[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 28)] = String.class;
            Method var10000 = var4.getMethod(var25, var10002);
            Object[] var33 = new Object[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865928816, 17)];
            var33[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(1882730759, 21)] = var1.getName();
            var10000.invoke(var7, var33);
            String var26 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("sGfrPwpACea37jj5SjeMTCgZyJItwfwUKnOHGr8uB7gYlV7f8zcEcKRlT2IFQIil8aW5cpUI");
            var10002 = new Class[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865798000, 8)];
            var10002[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 2)] = boolean.class;
            var10000 = var4.getMethod(var26, var10002);
            Object[] var35 = new Object[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865793648, 12)];
            var35[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(784953208, 19)] = Boolean.valueOf((Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-1954823818, 30) != 0));
            var10000.invoke(var7, var35);
            String var27 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("tSN7+sQ9QaoN78toS0JCLSiHiKmZN9yAFlbXd296IwzJit9rHJbFRPw8vOwL4qxzWMjdaBYJP74=");
            var10002 = new Class[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1731580016, 27)];
            var10002[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 0)] = String.class;
            var10000 = var4.getMethod(var27, var10002);
            Object[] var37 = new Object[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797728, 4)];
            var37[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(1532793509, 14)] = var1.getName();
            var10000.invoke(var7, var37);
            String var28 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("/CSUAIzeK5Ou61FUfYkxbufXWzrmwgkShUhqqEko1URKA6TAJjlqdC13SvN2eVqs4A0T5A==");
            var10002 = new Class[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-1562447779, 28)];
            var10002[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 25)] = boolean.class;
            var10000 = var4.getMethod(var28, var10002);
            Object[] var39 = new Object[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532793510, 20)];
            var39[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(-2085086184, 12)] = Boolean.valueOf((Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 11) != 0));
            var10000.invoke(var7, var39);
            String var29 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("goL4mzEKyr1Sy7oAefSzHAvE4zoF5wCAbgLImg/9+mzs2FiuGxRyYeRmhYedjo9ABRDhLPc1");
            var10002 = new Class[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(202933176, 28)];
            var10002[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(1532793509, 7)] = boolean.class;
            var10000 = var4.getMethod(var29, var10002);
            Object[] var41 = new Object[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(405866352, 29)];
            var41[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-554672750, 4)] = Boolean.valueOf((Xqedii_1ino5c0.Xqedii_2smplobxkxptv(196238302, 17) != 0));
            var10000.invoke(var7, var41);
            this.Xqedii_3vodak3(var4, var7, var1);
            Class var8 = Class.forName(
               Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
                  "5nIO5kK4Vv+vh89OFiTv8AKk+xgm9N3m+kMCqicwugkr7nx0DWfEi7kelpsdvC9uFRj1E8u5UTpPrBM3k2U7kTBWmVX9MGYtYJCTT5AJaq1on/P1lFINTFOQW3LBLGtPUbJhqYexKJnsDl7tgkoc32pX0tPRAgZG+JumRC37SY5qMg=="
               )
            );
            Object var9 = Xqedii_12szsum(var8, Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("BdFatteITgQnZkd7hWAdKqi93O6296M8gl/poh5Tu8EcTaFHjfK+aw=="));
            if (var9 != null) {
               String var30 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("v2ZfohyjRJox2y2P+ni6th9b1nJHRe0CTmSvSXIB3BRCircw5wdL2O2KlEJppmAk9b9a0tMP");
               var10002 = new Class[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865928816, 17)];
               var10002[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 15)] = var8;
               var10000 = var4.getMethod(var30, var10002);
               Object[] var43 = new Object[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532793510, 29)];
               var43[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(1532793509, 20)] = var9;
               var10000.invoke(var7, var43);
            }

            Object var10 = this.Xqedii_1v0m5hk.invoke(this.Xqedii_16ifz9w);
            var3 = ((Function)var10).apply(var7);
            if (var3 == null) {
               return null;
            } else {
               Class var11 = var3.getClass();
               String var31 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("GoRQlxmDbN2WeefCd45LF89qWYusXW8AifskBh1OlAFhC8KKTXOj7wpZho8sT4As3RDDBGZl");
               var10002 = new Class[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(196369374, 17)];
               var10002[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(1266401654, 30)] = boolean.class;
               var10000 = var11.getMethod(var31, var10002);
               Object[] var45 = new Object[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(792055920, 30)];
               var45[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(-301529568, 18)] = Boolean.valueOf((Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-1139317577, 10) != 0));
               var10000.invoke(var3, var45);
               var11.getMethod(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("8MujCtsB7S59c1SbNpKuwmTo1E/rhgxRYtF+dXkRkWVbIwfcwF0xZQ==")).invoke(var3);
               Object var12 = this.Xqedii_2qwwfvm.invoke(this.Xqedii_16ifz9w);
               Class var13 = Class.forName(
                  Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
                     "gukTf7a0p23w+0tGHjwYi4U27ccOJ0jVZVwnoTj9n9lMue79zCFDDHc8hmI5OUYU0A0hjJ33QPeHaf1FmnTRPAvPGFk5/+E2RyXzrppC3SYdGQ=="
                  )
               );
               Class var22 = var12.getClass();
               String var32 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("pV4zOrE9j/2RJPI6rER3VgrRGCaSlYZZeLJzSGrx4pAUQkFjMtUX4n1g5CjTiWHe2tw=");
               var10002 = new Class[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(822571032, 5)];
               var10002[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-569658789, 9)] = var13;
               var10000 = var22.getMethod(var32, var10002);
               Object[] var47 = new Object[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-317261950, 23)];
               var47[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 15)] = var3;
               var10000.invoke(var12, var47);
               int var14 = (Integer)var11.getMethod(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("a5jX7Wpd2aS2btAzpxUZwCpF41K4fkA5GWPk2rOImIBq9vJctFu7qnpdoZ9N9R0/QtQ="))
                  .invoke(var3);
               return new Xqedii_1uzjgh7(var14, var2.clone(), NpcPluginType.FANCYNPCS, var3);
            }
         } catch (Throwable var15) {
            this.Xqedii_fqsswts.getLogger().warning(Xqedii_dq1j6im1wy3j(var1.getName(), var15.getMessage()));
            this.Xqedii_308gjym(var3);
            return null;
         }
      } else {
         return null;
      }
   }

   public void Xqedii_308gjym(Object var1) {
      if (((-849416274 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         try {
            var1.getClass()
               .getMethod(Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("vtTt4SGvN7UTsy1WXXI/3B/cT4s4k+LM0Ei/hYsZb9twoHqaEgS8lakEJm4w/PeIu2cGDw=="))
               .invoke(var1);
         } catch (Throwable var5) {
         }

         try {
            Object var2 = this.Xqedii_2qwwfvm.invoke(this.Xqedii_16ifz9w);
            Class var3 = Class.forName(
               Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb(
                  "gukTf7a0p23w+0tGHjwYi4U27ccOJ0jVZVwnoTj9n9lMue79zCFDDHc8hmI5OUYU0A0hjJ33QPeHaf1FmnTRPAvPGFk5/+E2RyXzrppC3SYdGQ=="
               )
            );
            Class var10000 = var2.getClass();
            String var10001 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("GH1sII+vhK+hxp9O8EYp6yL6NVX7ifz+iwrfwOrbTzu9ToeuJHv6Ulc5N2jQ6g==");
            Class[] var10002 = new Class[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1863700592, 21)];
            var10002[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 2)] = var3;
            Method var6 = var10000.getMethod(var10001, var10002);
            Object[] var7 = new Object[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797748, 2)];
            var7[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(1532793509, 25)] = var1;
            var6.invoke(var2, var7);
         } catch (Throwable var4) {
         }
      }
   }

   public void Xqedii_1fvzdcm(Xqedii_1uzjgh7 var1, Player var2) {
      if ((-437233349 * -437233349 - -437233349 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         try {
            Class var10000 = var1.Xqedii_t5beb8k.getClass();
            String var10001 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("vUjdlqdeQ29B+TobCyeKAi04jfgHSXeY0dAydFT34/qmtUs7/Os=");
            Class[] var10002 = new Class[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-524597827, 13)];
            var10002[Xqedii_1ino5c0.Xqedii_2smplobxkxptv(-554672750, 4)] = Player.class;
            Method var5 = var10000.getMethod(var10001, var10002);
            var10001 = (String)var1.Xqedii_t5beb8k;
            Object[] var7 = new Object[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(-1207166845, 20)];
            var7[Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 9)] = var2;
            var5.invoke(var10001, var7);
         } catch (Throwable var4) {
            this.Xqedii_fqsswts.getLogger().warning(Xqedii_kmuriq9zn9s6(var2.getName(), var4.getMessage()));
         }
      }
   }

   public void Xqedii_1vgztwh(Xqedii_1uzjgh7 var1, Player var2) {
      if ((-1779967310 ^ -1779967310) != 0) {
         throw new IllegalStateException();
      } else {
         try {
            Class var10000 = var1.Xqedii_t5beb8k.getClass();
            String var10001 = Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("ANyLX+Vit9rLbwQgsdAxR8eSuujJICThT6k8R2we5FlGdkOFr+YSGQ==");
            Class[] var10002 = new Class[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(-1532793510, 28)];
            var10002[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(1532793509, 27)] = Player.class;
            Method var5 = var10000.getMethod(var10001, var10002);
            var10001 = (String)var1.Xqedii_t5beb8k;
            Object[] var7 = new Object[Xqedii_1ino5c0.Xqedii_2ajizx00etb0b(-1048036480, 0)];
            var7[Xqedii_1ino5c0.Xqedii_2w6fuvtdhtkdh(1532793509, 23)] = var2;
            var5.invoke(var10001, var7);
         } catch (Throwable var4) {
         }
      }
   }

   public void Xqedii_3spsnxx() {
      if ((2016484403 ^ 2016484403) != 0) {
         throw new IllegalStateException();
      } else if (!this.Xqedii_17xnycq) {
         this.Xqedii_17xnycq = (Xqedii_1ino5c0.Xqedii_dku2we86xfqb(792055920, 30) != 0);
         Xqedii_38o2zes.Xqedii_3npx6fi(this.Xqedii_fqsswts, this::Xqedii_75ffuub, Xqedii_1ino5c0.Xqedii_b4vefeokp070(3642549316149452517L, 61));
      }
   }

   public void Xqedii_75ffuub() {
      if ((962108804 ^ 962108804) != 0) {
         throw new IllegalStateException();
      } else if (this.Xqedii_17xnycq) {
         try {
            this.Xqedii_39jdso5();
         } catch (Throwable var2) {
            this.Xqedii_fqsswts.getLogger().warning(Xqedii_246zihrp5vw0b(var2.getMessage()));
         }

         if (this.Xqedii_17xnycq) {
            if (this.Xqedii_bujm9m1.isEmpty()) {
               this.Xqedii_17xnycq = (Xqedii_1ino5c0.Xqedii_dku2we86xfqb(1865797744, 3) != 0);
            } else {
               Xqedii_38o2zes.Xqedii_3npx6fi(this.Xqedii_fqsswts, this::Xqedii_75ffuub, Xqedii_1ino5c0.Xqedii_b4vefeokp070(-969136702277935386L, 62));
            }
         }
      }
   }

   public void Xqedii_39jdso5() {
      if ((-60405151 ^ -60405151) != 0) {
         throw new IllegalStateException();
      } else if (!this.Xqedii_bujm9m1.isEmpty()) {
         for (Entry var2 : this.Xqedii_bujm9m1.entrySet()) {
            UUID var3 = (UUID)var2.getKey();
            Player var4 = Bukkit.getPlayer(var3);
            if (var4 != null && var4.isOnline()) {
               this.Xqedii_abgu8mb(var4, (Xqedii_1uzjgh7)var2.getValue());
            } else {
               this.despawn(var3);
            }
         }
      }
   }

   public void Xqedii_abgu8mb(Player var1, Xqedii_1uzjgh7 var2) {
      if ((161578829 ^ 161578829) != 0) {
         throw new IllegalStateException();
      } else {
         double var3 = Double.longBitsToDouble(Xqedii_1ino5c0.Xqedii_2xgpnapbvtb1n(6533944070378703901L, 58));
         HashSet var5 = new HashSet();

         for (Player var7 : Bukkit.getOnlinePlayers()) {
            if (var7 != null
               && var7.isOnline()
               && !var7.getUniqueId().equals(var1.getUniqueId())
               && var7.getWorld() != null
               && var2.Xqedii_3gmr9uu.getWorld() != null
               && var7.getWorld().equals(var2.Xqedii_3gmr9uu.getWorld())
               && !(var7.getLocation().distanceSquared(var2.Xqedii_3gmr9uu) > var3)) {
               var5.add(var7.getUniqueId());
            }
         }

         for (UUID var11 : (Iterable<UUID>) (var5)) {

            if (var2.Xqedii_1yo3gt3.add(var11)) {
               Player var8 = Bukkit.getPlayer(var11);
               if (var8 != null && var8.isOnline()) {
                  this.Xqedii_1z2mtv2(var2, var8);
               }
            }
         }

         Iterator var10 = var2.Xqedii_1yo3gt3.iterator();

         while (var10.hasNext()) {
            UUID var12 = (UUID)var10.next();
            if (!var5.contains(var12)) {
               var10.remove();
               Player var13 = Bukkit.getPlayer(var12);
               if (var13 != null && var13.isOnline()) {
                  this.Xqedii_3ah85cu(var2, var13);
               }
            }
         }
      }
   }

   public void Xqedii_1z2mtv2(Xqedii_1uzjgh7 var1, Player var2) {
      if (((682981085 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         switch (var1.Xqedii_3ctqsuy) {
            case NONE:
            default:
               break;
            case CITIZENS:
               this.Xqedii_2okdf6y(var1, var2);
               break;
            case FANCYNPCS:
               this.Xqedii_1fvzdcm(var1, var2);
         }
      }
   }

   public void Xqedii_3ah85cu(Xqedii_1uzjgh7 var1, Player var2) {
      if (((-1608845405 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         switch (var1.Xqedii_3ctqsuy) {
            case NONE:
            default:
               break;
            case CITIZENS:
               this.Xqedii_d47y92s(var1, var2);
               break;
            case FANCYNPCS:
               this.Xqedii_1vgztwh(var1, var2);
         }
      }
   }

   public void Xqedii_1un38qu() {
      if ((-44213261 ^ -44213261) != 0) {
         throw new IllegalStateException();
      } else {
         this.Xqedii_lk646ha();
         PacketEventsAPI var1 = PacketEvents.getAPI();
         if (var1 != null) {
            this.Xqedii_2rymnfq = new PacketListenerAbstract(PacketListenerPriority.NORMAL) {
               public static final String Xqedii_3f9pp0zf31qn3 = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";

               public void onPacketReceive(PacketReceiveEvent var1) {
                  if ((-1608034838 * (-1608034838 + 1) & 1) != 0) {
                     throw new IllegalStateException();
                  } else if (var1 != null && var1.getPacketType() == Client.INTERACT_ENTITY) {
                     if (var1.getPlayer() instanceof Player var3) {
                        WrapperPlayClientInteractEntity var4 = new WrapperPlayClientInteractEntity(var1);
                        if (var4.getAction() == InteractAction.ATTACK) {
                           CursorNpcService.this.Xqedii_1uicwm3(var3, var4.getEntityId());
                        }
                     }
                  }
               }
            };
            var1.getEventManager().registerListener(this.Xqedii_2rymnfq);
         }
      }
   }

   public void Xqedii_lk646ha() {
      if ((1773563488 ^ 1773563488) != 0) {
         throw new IllegalStateException();
      } else if (this.Xqedii_2rymnfq != null) {
         PacketEventsAPI var1 = PacketEvents.getAPI();
         if (var1 != null) {
            try {
               var1.getEventManager().unregisterListener(this.Xqedii_2rymnfq);
            } catch (Throwable var3) {
            }
         }

         this.Xqedii_2rymnfq = null;
      }
   }

   public void Xqedii_1uicwm3(Player var1, int var2) {
      if (((32239712 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         UUID var3 = this.Xqedii_2apu34a.get(var2);
         if (var3 != null && var1 != null && !var1.getUniqueId().equals(var3)) {
            Player var4 = Bukkit.getPlayer(var3);
            if (var4 != null && var4.isOnline()) {
               Xqedii_1uzjgh7 var5 = this.Xqedii_bujm9m1.get(var3);
               if (var5 != null) {
                  Xqedii_38o2zes.Xqedii_1avvt9e(this.Xqedii_fqsswts, var4, () -> {
                     if ((-1462748531 ^ -1462748531) != 0) {
                        throw new IllegalStateException();
                     } else {
                        this.Xqedii_3ig5z4y(var1, var4, var5);
                     }
                  });
               }
            }
         }
      }
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public void Xqedii_3ig5z4y(Player var1, Player var2, Xqedii_1uzjgh7 var3) {
      if ((-39876302 * -39876302 + -39876302 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.isOnline() && var2 != null && var2.isOnline()) {
         if (this.Xqedii_bujm9m1.get(var2.getUniqueId()) == var3) {
            if (var1.getWorld() != null && var3.Xqedii_3gmr9uu.getWorld() != null && var1.getWorld().equals(var3.Xqedii_3gmr9uu.getWorld())) {
               if (var1.getWorld().getPVP()) {
                  if (!(
                     var1.getLocation().distanceSquared(var3.Xqedii_3gmr9uu)
                        > Double.longBitsToDouble(Xqedii_1ino5c0.Xqedii_b4vefeokp070(8254235334309486308L, 30))
                  )) {
                     double var4 = this.Xqedii_3xs32aw(var1);
                     this.Xqedii_3e2vkr1.add(var2.getUniqueId());
                     boolean var8 = false /* VF: Semaphore variable */;

                     try {
                        var8 = true;
                        var2.damage(var4, var1);
                        var8 = false;
                     } finally {
                        if (var8) {
                           Xqedii_38o2zes.Xqedii_2sj63w1(this.Xqedii_fqsswts, var2, () -> {
                              if ((1679381500 * 1679381500 - 1679381500 & 1) != 0) {
                                 throw new IllegalStateException();
                              } else {
                                 this.Xqedii_3e2vkr1.remove(var2.getUniqueId());
                              }
                           }, Xqedii_1ino5c0.Xqedii_3p7j6na9w09z8(-766878337841083610L, 39));
                        }
                     }

                     Xqedii_38o2zes.Xqedii_2sj63w1(this.Xqedii_fqsswts, var2, () -> {
                        if ((1679381500 * 1679381500 - 1679381500 & 1) != 0) {
                           throw new IllegalStateException();
                        } else {
                           this.Xqedii_3e2vkr1.remove(var2.getUniqueId());
                        }
                     }, Xqedii_1ino5c0.Xqedii_b4vefeokp070(8254235333234663140L, 28));
                  }
               }
            }
         }
      }
   }

   public double Xqedii_3xs32aw(Player var1) {
      if ((545631117 * 545631117 + 545631117 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         try {
            Attribute var2 = Xqedii_38o2zes.Xqedii_36ek0ro();
            AttributeInstance var3 = var2 == null ? null : var1.getAttribute(var2);
            if (var3 != null) {
               double var4 = var3.getValue();
               if (var4 > Double.longBitsToDouble(Xqedii_1ino5c0.Xqedii_b4vefeokp070(8254235334576840420L, 53))) {
                  return var4;
               }
            }
         } catch (Throwable var6) {
         }

         return Double.longBitsToDouble(Xqedii_1ino5c0.Xqedii_7kpwicvro229(2414436559913264988L, 12));
      }
   }

   private static /* synthetic */ String Xqedii_uen67ktco2a3(String string) {
        return Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("PusTszTnGJfFgGV6o6EbLepkfJuzxKtKkMUk+Twmer4uJOF9PqZca5CqJQ6wCgCQvwD6bMFGSAjfKXXeDjB2/SKORrJWtduQYBSRtg6mBdb0QiWvhzOL0Upj/ARiuSLrVtW6JeLQCRuwpwGVXxB1vxReZ+LwPAOEfNzvOXMOVstP03bIjBiAnAIz1qNyvhvhkFNOeX8FtsUeKhzH3c7Arc+5eOThVwpKn1VvHmGTlmaM2r8jPQ2KFb4p/9h9rvBo") + string;
    }

    private static /* synthetic */ String Xqedii_o4newan0klc6(String string) {
        return Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("NVtrUyfK5yJYpNlyqoWnjEe3elPmPzVdWkybk/SPodC5YWkYdDOQ2HOmcPu6IBZRyezQmhGvvuOLvk7N9OZZck81KSDeMs8cf/Eg2o9VAMSab4I57cr5UMA7alzl8QzYGD9UjxZGKsSX0EwWABe0EyIy32X5ZXgT3ZIcWa6a5fPyKXkj+1TwIdM2fbcwPC4kGDZuJib0OKMtX15T8DtjnhR1K37/H9Vd43A+KRASxmOLQIMS+kkWSZfdcPu/OtoRFM8=") + string;
    }

    private static /* synthetic */ String Xqedii_2j7urjtl4ml5d(String string, String string2) {
        return Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("EPhktYc/fz2ElihYRvLe2f3WMoZ0e2+boHMHEPcjRrewC20kHRaI98aKJpPacgHxO4TrsH3vk659yqcHQhHXkz1E/WyUk4nQwZHFMUOZtGQfF6w5mLk3u8grJieaevyxx02czshNlpM3Kss2oLe2REsmE8INiiwVKL1qGOBNHdKs5ypnS4D9exDdBr6TXnf/") + string + Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("CwsyfpPwhXzbDHfkbYCMK70C8xcceqfA8YF5h9Ui5iM=") + string2;
    }

    private static /* synthetic */ String Xqedii_2s39c0r3017kv(String string, String string2) {
        return Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("q+Ue9nhGfbzloALT9DNoVJ7xR5+yozHRUiNGH2Dax69nnjE9up8QBW+jU+TKiqWBToy5+0AF9tmeJlhtDnVZd+bdaZD/5o1xxoHyNMN+Uq1zpa7MAoa6mGcr170WSwhf890uysJ11vCYjOxp5veqIjFSeQWaok71/yiLvpzWOfh9CK/5OAxTNr5X17rfQ81fWnoELot2eQ95ADCvMxr0Vg==") + string + Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("CwsyfpPwhXzbDHfkbYCMK70C8xcceqfA8YF5h9Ui5iM=") + string2;
    }

    private static /* synthetic */ String Xqedii_2slqv976uy4fk(String string, String string2) {
        return Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("iMT9488Y8NNkTcl7Lv2nlrsd4+lKwNjStYe3woPMGyfLeUFeGLKN27baGQY00R389jL/mLJmKqFJrFUzfrFh/95Zl+whHNWGvV4pgQkF5z/iwIkyMG8Dld+PhgOUFF1MrbkYZHppKW69gBC6cAgK6VLoMf23Lvzop7l3WXnKzyb4oSehAZQ0HC++/0TiMmVUVcvFwGTdy3pxwAFIub1nTdMAQiLcmh5B") + string + Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("CwsyfpPwhXzbDHfkbYCMK70C8xcceqfA8YF5h9Ui5iM=") + string2;
    }

    private static /* synthetic */ String Xqedii_2be6on02owlsq(String string, String string2) {
        return Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("+mFzHXO/7e2cR6JT5zYPGMI8Dc1xyCSDye7iUEbPrQmvoqTdGPQ7Ir6lGx5snFnDCUpf8iz5+SOHaGIbSBsXPzkEeXOchz7QVzLl5y+SCyxcatHY/r6DwJSVdC+Kk80oJjXCewQu4sdPzWXdgMejVU3OAKG5LGzyKvJMeFSqwfVOyBcLf4Omre2na1HxJvuQ6a1Ig/+u45LLdO5CEwFBI4u8hTMxHFznrBw=") + string + Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("CwsyfpPwhXzbDHfkbYCMK70C8xcceqfA8YF5h9Ui5iM=") + string2;
    }

    private static /* synthetic */ String Xqedii_2pit0iwxbsxj4(String string) {
        return Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("WMoR74Nu6O8A2m6+6annQto11OXs+h4uIJZ9Y/oWw2BTR0AQ") + string;
    }

    private static /* synthetic */ String Xqedii_dq1j6im1wy3j(String string, String string2) {
        return Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("58+0L5OKq+LG9iewjvaGtvlCyCbIkxitac5n8BoQ+ZdB53D7qvoC+6Sr8sfZBed2kjbb6YH6BROtgZd1AxVlxAaAU9dusr7bjuPu6ikxk4auT9OdGuMMwH19tmdQQdPUNHp+w2LfWhZ/whir/5GXEE/hsitFwAMkXdQjr+fiklPQzqyxSC9HEX6aClyt/dr3kMQ=") + string + Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("CwsyfpPwhXzbDHfkbYCMK70C8xcceqfA8YF5h9Ui5iM=") + string2;
    }

    private static /* synthetic */ String Xqedii_kmuriq9zn9s6(String string, String string2) {
        return Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("izmHRZ2M2NEECRgI6W6oxFpHqKe8L3mOt3kUT47tTv1ZuA4ExKfBLzveiqeQmbiOc9rRhlT2yLc1CswjuBg36+Dcn3hTaTEqOgzJOqWMPMDnNDD3yP/ESJgCVRVGHqTjlyhXzDMMlvfimaIfkbtZo6zxuoclxxQKsTYhgi48YO25DXBv3Ytw1Le/0UIzlA==") + string + Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("CwsyfpPwhXzbDHfkbYCMK70C8xcceqfA8YF5h9Ui5iM=") + string2;
    }

    private static /* synthetic */ String Xqedii_246zihrp5vw0b(String string) {
        return Xqedii_1ino5c0.Xqedii_2df1de1vt0vwb("yVcqF1fclLOmFx8gR9WswXL6ya1g5qGN3mmAR4tPtbSO9QoTXnjuw6Q6HIhHIQSm/8p/q3m3J8aDP629tKz3EIYZTIdFL49c0EQQvXFRx8aexnrtFX+T9Cq9C4RaSUgHH5Cqqy2/VcWqpi0LvJrFfE/xQ3gu7vneIsajvFyVoopPMDKzPxI=") + string;
    }
}
