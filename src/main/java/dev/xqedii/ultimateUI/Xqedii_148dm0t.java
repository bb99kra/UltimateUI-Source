package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateui.libs.packetevents.packetevents.PacketEvents;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketSendEvent;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType.Play.Server;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.PacketWrapper;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.server.WrapperPlayServerBossBar;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.server.WrapperPlayServerSystemChatMessage;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.server.WrapperPlayServerBossBar.Action;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import net.kyori.adventure.bossbar.BossBar.Color;
import net.kyori.adventure.bossbar.BossBar.Flag;
import net.kyori.adventure.bossbar.BossBar.Overlay;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public final class Xqedii_148dm0t {
   public static final long Xqedii_j6u78zx = 24019146153017682L;
   public final UltimateUI Xqedii_22nea49;
   public final Xqedii_1o1cvf4 Xqedii_3mi6i2t;
   public final Map<UUID, Xqedii_109qg45> Xqedii_2t443mo = new ConcurrentHashMap<>();
   public final Map<UUID, Component> Xqedii_i1xnec0 = new ConcurrentHashMap<>();
   public final Map<UUID, Integer> Xqedii_1127793 = new ConcurrentHashMap<>();
   public final Set<UUID> Xqedii_3vyacp1 = ConcurrentHashMap.newKeySet();
   public PacketListenerAbstract Xqedii_1sudxi8;

   public Xqedii_148dm0t(UltimateUI var1) {
      this.Xqedii_22nea49 = var1;
      this.Xqedii_3mi6i2t = new Xqedii_11ahxwr(this);
   }

   public Xqedii_148dm0t(UltimateUI var1, Xqedii_1o1cvf4 var2) {
      this.Xqedii_22nea49 = var1;
      this.Xqedii_3mi6i2t = var2;
   }

   public void Xqedii_25q2a19() {
      if (this.Xqedii_1sudxi8 == null) {
         this.Xqedii_1sudxi8 = new PacketListenerAbstract(PacketListenerPriority.HIGHEST) {

            public void onPacketSend(PacketSendEvent var1) {
               if (!var1.isCancelled()) {
                  if (var1.getPacketType() == Server.BOSS_BAR) {
                     Xqedii_148dm0t.this.Xqedii_22ibavs(var1);
                  } else if (var1.getPacketType() == Server.ACTION_BAR || var1.getPacketType() == Server.SYSTEM_CHAT_MESSAGE) {
                     Xqedii_148dm0t.this.Xqedii_1xbo2da(var1);
                  }
               }
            }
         };
         PacketEvents.getAPI().getEventManager().registerListener(this.Xqedii_1sudxi8);
      }
   }

   public void Xqedii_1i1ys2a() {
      if (this.Xqedii_1sudxi8 != null) {
         try {
            PacketEvents.getAPI().getEventManager().unregisterListener(this.Xqedii_1sudxi8);
         } catch (Throwable var2) {
         }

         this.Xqedii_1sudxi8 = null;
      }
   }

   public Xqedii_109qg45 Xqedii_24p21te(UUID var1) {
      {
         return this.Xqedii_2t443mo.computeIfAbsent(var1, var0 -> {
            {
               return new Xqedii_109qg45();
            }
         });
      }
   }

   public void Xqedii_22ibavs(PacketSendEvent var1) {
      if (var1.getPlayer() instanceof Player var3) {
         PacketWrapper var4 = var1.getLastUsedWrapper();
         WrapperPlayServerBossBar var5 = new WrapperPlayServerBossBar(var1);
         var1.setLastUsedWrapper(var4);
         BossbarAction var6 = Xqedii_sewrhnb(var5.getAction());
         if (var5.getUUID() != null && var6 != null) {
            Component var7 = var6 != BossbarAction.ADD && var6 != BossbarAction.UPDATE_TITLE ? null : var5.getTitle();
            float var8 = var6 != BossbarAction.ADD && var6 != BossbarAction.UPDATE_HEALTH
               ? Float.intBitsToFloat(0)
               : var5.getHealth();
            int var9 = var6 != BossbarAction.ADD && var6 != BossbarAction.UPDATE_STYLE
               ? 0
               : 1;
            int var10 = var6 != BossbarAction.ADD && var6 != BossbarAction.UPDATE_FLAGS
               ? 0
               : 1;
            Xqedii_34z2igs var11 = new Xqedii_34z2igs(
               var5.getUUID(), var6, var7, var8, var9 != 0 ? var5.getColor() : null, var9 != 0 ? var5.getOverlay() : null, var10 != 0 ? var5.getFlags() : null
            );
            if (this.Xqedii_ac46bu9(var3, var11)) {
               var1.setCancelled(true);
            }
         }
      }
   }

   public boolean Xqedii_ac46bu9(Player var1, Xqedii_34z2igs var2) {
      {
         UUID var3 = var1.getUniqueId();
         UUID var4 = var2.barId();
         if (this.Xqedii_30oi3c2(var3, var4)) {
            return false;
         } else {
            Xqedii_109qg45 var5 = this.Xqedii_24p21te(var3);
            synchronized (var5) {
               int var7 = var5.Xqedii_px4voub != null && !var5.Xqedii_px4voub.isEmpty()
                  ? 1
                  : 0;
               Xqedii_38cw7ku var8 = var5.Xqedii_2bjpkmq.get(var4);
               if (var2.action() == BossbarAction.REMOVE) {
                  if (var8 == null) {
                     return false;
                  } else if (!var8.Xqedii_cav4kxy) {
                     var5.Xqedii_2bjpkmq.remove(var4);
                     return true;
                  } else if (var7 == 0 && !var5.Xqedii_394arwg.contains(var4)) {
                     var5.Xqedii_2bjpkmq.remove(var4);
                     return false;
                  } else {
                     var5.Xqedii_394arwg.add(var4);
                     var5.Xqedii_hc9001e = true;
                     return true;
                  }
               } else {
                  if (var8 == null) {
                     if (var2.action() != BossbarAction.ADD) {
                        return false;
                     }

                     var8 = new Xqedii_38cw7ku();
                     var8.Xqedii_cav4kxy = ((var7 == 0
                        ? 1
                        : 0) != 0);
                     var5.Xqedii_2bjpkmq.put(var4, var8);
                  }

                  switch (var2.action()) {
                     case ADD:
                        var5.Xqedii_394arwg.remove(var4);
                        var8.title = (Component)(var2.title() == null ? Component.empty() : var2.title());
                        var8.Xqedii_lpfdzop = var2.health();
                        var8.Xqedii_2gslzsd = var2.color() == null ? Color.WHITE : var2.color();
                        var8.Xqedii_2g8wahp = var2.overlay() == null ? Overlay.PROGRESS : var2.overlay();
                        var8.Xqedii_28ahbkr = Xqedii_y6qqz46(var2.flags());
                     case REMOVE:
                     default:
                        break;
                     case UPDATE_HEALTH:
                        var8.Xqedii_lpfdzop = var2.health();
                        break;
                     case UPDATE_TITLE:
                        var8.title = (Component)(var2.title() == null ? Component.empty() : var2.title());
                        break;
                     case UPDATE_STYLE:
                        var8.Xqedii_2gslzsd = var2.color() == null ? var8.Xqedii_2gslzsd : var2.color();
                        var8.Xqedii_2g8wahp = var2.overlay() == null ? var8.Xqedii_2g8wahp : var2.overlay();
                        break;
                     case UPDATE_FLAGS:
                        var8.Xqedii_28ahbkr = Xqedii_y6qqz46(var2.flags());
                  }

                  if (!var8.Xqedii_cav4kxy) {
                     var5.Xqedii_hc9001e = true;
                     return true;
                  } else {
                     return var5.Xqedii_394arwg.contains(var4);
                  }
               }
            }
         }
      }
   }

   public boolean Xqedii_1b9od7q(Player var1) {
      if (var1 == null) {
         return false;
      } else {
         Xqedii_109qg45 var2 = this.Xqedii_2t443mo.get(var1.getUniqueId());
         if (var2 == null) {
            return false;
         } else {
            synchronized (var2) {
               if (!var2.Xqedii_hc9001e) {
                  return false;
               } else {
                  this.Xqedii_3btxv9k(var1, var2);
                  return true;
               }
            }
         }
      }
   }

   public void Xqedii_3btxv9k(Player var1, Xqedii_109qg45 var2) {
      {
         var2.Xqedii_hc9001e = false;

         for (UUID var4 : var2.Xqedii_394arwg) {
            if (var2.Xqedii_2bjpkmq.remove(var4) != null) {
               this.Xqedii_3mi6i2t.Xqedii_s6fwnzl(var1, var4, BossbarAction.REMOVE, null);
            }
         }

         var2.Xqedii_394arwg.clear();
         ArrayList var6 = new ArrayList();

         for (Entry var5 : var2.Xqedii_2bjpkmq.entrySet()) {
            if (!((Xqedii_38cw7ku)var5.getValue()).Xqedii_cav4kxy) {
               var6.add(var5);
            }
         }

         if (!var6.isEmpty()) {
            this.Xqedii_2zlcb1d(var1, var2);

            for (Entry var9 : (Iterable<Entry>) (var6)) {

               ((Xqedii_38cw7ku)var9.getValue()).Xqedii_cav4kxy = true;
               this.Xqedii_3mi6i2t.Xqedii_s6fwnzl(var1, (UUID)var9.getKey(), BossbarAction.ADD, (Xqedii_38cw7ku)var9.getValue());
            }
         }
      }
   }

   public int Xqedii_3csu27k(Player var1) {
      if (var1 == null) {
         return 0;
      } else {
         Xqedii_109qg45 var2 = this.Xqedii_2t443mo.get(var1.getUniqueId());
         if (var2 == null) {
            return 0;
         } else {
            synchronized (var2) {
               int var4 = 0;

               for (Xqedii_38cw7ku var6 : var2.Xqedii_2bjpkmq.values()) {
                  if (var6.Xqedii_cav4kxy) {
                     var4++;
                  }
               }

               return var4;
            }
         }
      }
   }

   public void Xqedii_1xbo2da(PacketSendEvent var1) {
      if (var1.getPlayer() instanceof Player var3) {
         UUID var4 = var3.getUniqueId();
         if (this.Xqedii_i1xnec0.containsKey(var4)) {
            if (var1.getPacketType() == Server.SYSTEM_CHAT_MESSAGE) {
               PacketWrapper var5 = var1.getLastUsedWrapper();
               boolean var6 = new WrapperPlayServerSystemChatMessage(var1).isOverlay();
               var1.setLastUsedWrapper(var5);
               if (!var6) {
                  return;
               }
            }

            var1.setCancelled(true);
            this.Xqedii_3vyacp1.add(var4);
         }
      }
   }

   public static EnumSet<Flag> Xqedii_y6qqz46(Set<Flag> var0) {
      {
         EnumSet var1 = EnumSet.noneOf(Flag.class);
         if (var0 != null) {
            var1.addAll(var0);
         }

         return var1;
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public static BossbarAction Xqedii_sewrhnb(Action var0) {
      if (var0 == null) {
         return null;
      } else {
         return switch (dev.xqedii.ultimateUI.Xqedii_1pfporw.Xqedii_1e66zqy[var0.ordinal()]) {
            case 1 -> BossbarAction.ADD;
            case 2 -> BossbarAction.REMOVE;
            case 3 -> BossbarAction.UPDATE_HEALTH;
            case 4 -> BossbarAction.UPDATE_TITLE;
            case 5 -> BossbarAction.UPDATE_STYLE;
            case 6 -> BossbarAction.UPDATE_FLAGS;
            default -> throw new MatchException(null, null);
         };
      }
   }

   public void Xqedii_3tvakwe(Player var1, Component var2) {
      if (var1 != null && var1.isOnline()) {
         if (var2 == null) {
            this.Xqedii_op9nutr(var1);
         } else {
            UUID var3 = var1.getUniqueId();
            Component var4 = this.Xqedii_i1xnec0.put(var3, var2);
            if (!var2.equals(var4) || this.Xqedii_3vyacp1.contains(var3)) {
               this.Xqedii_1vxjtff(var1, var2);
            }
         }
      }
   }

   public void Xqedii_2svfwuo(Player var1) {
      if (var1 != null) {
         UUID var2 = var1.getUniqueId();
         Component var3 = this.Xqedii_i1xnec0.get(var2);
         if (var3 != null) {
            int var4 = this.Xqedii_1127793.merge(var2, 1, Integer::sum);
            if (var4 >= 20 || this.Xqedii_3vyacp1.contains(var2)) {
               this.Xqedii_1vxjtff(var1, var3);
            }
         }
      }
   }

   public void Xqedii_op9nutr(Player var1) {
      if (var1 != null) {
         UUID var2 = var1.getUniqueId();
         this.Xqedii_1127793.remove(var2);
         this.Xqedii_3vyacp1.remove(var2);
         if (this.Xqedii_i1xnec0.remove(var2) != null && var1.isOnline()) {
            this.Xqedii_3mi6i2t.Xqedii_2pg3xb4(var1, Component.empty());
         }
      }
   }

   public void Xqedii_1vxjtff(Player var1, Component var2) {
      {
         UUID var3 = var1.getUniqueId();
         this.Xqedii_3vyacp1.remove(var3);
         this.Xqedii_1127793.put(var3, 0);
         this.Xqedii_3mi6i2t.Xqedii_2pg3xb4(var1, var2);
      }
   }

   public void Xqedii_19k0276(Player var1) {
      {
         if (var1 != null) {
            this.Xqedii_24p21te(var1.getUniqueId());
         }
      }
   }

   public void Xqedii_ndubsn7(Player var1) {
      if (var1 != null) {
         this.Xqedii_op9nutr(var1);
         Xqedii_109qg45 var2 = this.Xqedii_2t443mo.get(var1.getUniqueId());
         if (var2 != null) {
            synchronized (var2) {
               this.Xqedii_2zlcb1d(var1, var2);
               this.Xqedii_3btxv9k(var1, var2);
            }
         }
      }
   }

   public void Xqedii_2zlcb1d(Player var1, Xqedii_109qg45 var2) {
      {
         List var3 = var2.Xqedii_px4voub;
         var2.Xqedii_px4voub = null;
         if (var3 != null) {
            UUID var4 = var1.getUniqueId();

            for (int var5 = 0; var5 < var3.size(); var5++) {
               this.Xqedii_3mi6i2t.Xqedii_3p9z9ee(var1, Xqedii_1fp5wzn(var4, var5), BossbarAction.REMOVE, null);
            }
         }
      }
   }

   public void Xqedii_18wjn48(Player var1) {
      if (var1 != null) {
         UUID var2 = var1.getUniqueId();
         this.Xqedii_2t443mo.remove(var2);
         this.Xqedii_i1xnec0.remove(var2);
         this.Xqedii_1127793.remove(var2);
         this.Xqedii_3vyacp1.remove(var2);
      }
   }

   public void Xqedii_ai8f92x(Player var1, List<Component> var2) {
      if (var1 != null && var1.isOnline()) {
         UUID var3 = var1.getUniqueId();
         Xqedii_109qg45 var4 = this.Xqedii_24p21te(var3);
         synchronized (var4) {
            List var6 = var4.Xqedii_px4voub;
            int var7 = var6 == null ? 0 : var6.size();

            for (int var8 = 0; var8 < var2.size(); var8++) {
               Component var9 = (Component)var2.get(var8);
               if (var8 < var7) {
                  if (!var9.equals(var6.get(var8))) {
                     this.Xqedii_3mi6i2t.Xqedii_3p9z9ee(var1, Xqedii_1fp5wzn(var3, var8), BossbarAction.UPDATE_TITLE, var9);
                  }
               } else {
                  this.Xqedii_3mi6i2t.Xqedii_3p9z9ee(var1, Xqedii_1fp5wzn(var3, var8), BossbarAction.ADD, var9);
               }
            }

            for (int var12 = var2.size(); var12 < var7; var12++) {
               this.Xqedii_3mi6i2t.Xqedii_3p9z9ee(var1, Xqedii_1fp5wzn(var3, var12), BossbarAction.REMOVE, null);
            }

            var4.Xqedii_px4voub = new ArrayList<>(var2);
         }
      }
   }

   public void Xqedii_26lpujk(Player var1) {
      if (var1 != null) {
         if (this.Xqedii_i1xnec0.containsKey(var1.getUniqueId())) {
            this.Xqedii_3vyacp1.add(var1.getUniqueId());
         }

         Xqedii_109qg45 var2 = this.Xqedii_2t443mo.get(var1.getUniqueId());
         if (var2 != null) {
            synchronized (var2) {
               List var4 = var2.Xqedii_px4voub;
               var2.Xqedii_px4voub = null;
               if (var4 != null && !var4.isEmpty()) {
                  this.Xqedii_ai8f92x(var1, var4);
               }
            }
         }
      }
   }

   public boolean Xqedii_30oi3c2(UUID var1, UUID var2) {
      {
         for (int var3 = 0; var3 < 4; var3++) {
            if (var2.equals(Xqedii_1fp5wzn(var1, var3))) {
               return true;
            }
         }

         return false;
      }
   }

   public static UUID Xqedii_1fp5wzn(UUID var0, int var1) {
      {
         return new UUID(
            var0.getMostSignificantBits() ^ 24019146153017682L,
            var0.getLeastSignificantBits() ^ (4774187523410755584L | var1)
         );
      }
   }
}
