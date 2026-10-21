package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.hud.HudPackets;
import dev.xqedii.ultimateUI.service.hud.HudPassengerGuard;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketSendEvent;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType.Play.Server;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.server.WrapperPlayServerSetPassengers;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;

public class Xqedii_3rn3b4o extends PacketListenerAbstract {
   public final HudPassengerGuard Xqedii_2fgadxf;

   public Xqedii_3rn3b4o(HudPassengerGuard var1, PacketListenerPriority var2) {
      super(var2);
      this.Xqedii_2fgadxf = var1;
   }

   public void onPacketSend(PacketSendEvent var1) {
      if (var1 != null && var1.getPacketType() == Server.SET_PASSENGERS) {
         if (var1.getPlayer() instanceof Player var3) {
            WrapperPlayServerSetPassengers var4 = new WrapperPlayServerSetPassengers(var1);
            if (var4.getEntityId() == var3.getEntityId()) {
               UUID var5 = var3.getUniqueId();
               Map var6 = (Map)this.Xqedii_2fgadxf.Xqedii_7rxyqib.get(var5);
               LinkedHashSet var7 = new LinkedHashSet();
               if (!this.Xqedii_2fgadxf.isFixShadersEnabled(var3) && var6 != null) {
                  for (Xqedii_2t8d8j4 var9 : (Iterable<Xqedii_2t8d8j4>) (var6.values())) {
                     var7.add(var9.Xqedii_1qxgv1w);
                  }
               }

               this.Xqedii_2fgadxf.Xqedii_2tx2x1f(var5, var7);
               int[] var14 = var4.getPassengers();
               LinkedHashSet var15 = new LinkedHashSet();
               if (var14 != null) {
                  int[] var10 = var14;
                  int var11 = var14.length;

                  for (int var12 = 0; var12 < var11; var12++) {
                     int var13 = var10[var12];
                     if (!var7.contains(var13)) {
                        var15.add(var13);
                     }
                  }
               }

               this.Xqedii_2fgadxf.Xqedii_v8ssh03.put(var5, HudPackets.Xqedii_14vespo(var15));
               if (!var7.isEmpty()) {
                  LinkedHashSet var16 = new LinkedHashSet(var15);
                  var16.addAll(var7);
                  var4.setPassengers(HudPackets.Xqedii_14vespo(var16));
                  var1.markForReEncode(true);
               }
            }
         }
      }
   }
}
