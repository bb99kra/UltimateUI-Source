package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.gui.WorldUiPart;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketReceiveEvent;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType.Play.Client;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import org.bukkit.entity.Player;

public class WorldUiInteractPacketListener extends PacketListenerAbstract {
   public final WorldUiPart Xqedii_1ac9x13;

   public WorldUiInteractPacketListener(WorldUiPart var1, PacketListenerPriority var2) {
      super(var2);
      this.Xqedii_1ac9x13 = var1;
   }

   public void onPacketReceive(PacketReceiveEvent var1) {
      if (var1 != null && var1.getPacketType() == Client.INTERACT_ENTITY) {
         if (var1.getPlayer() instanceof Player var2) {
            WrapperPlayClientInteractEntity var5 = new WrapperPlayClientInteractEntity(var1);
            WorldUiViewerEntity var4 = (WorldUiViewerEntity)this.Xqedii_1ac9x13.Xqedii_28h4g3k.get(var5.getEntityId());
            if (var4 != null && var4.viewerId().equals(var2.getUniqueId())) {
               var1.setCancelled(true);
               Xqedii_38o2zes.Xqedii_1avvt9e(WorldUiPart.Xqedii_lvvjxhd(this.Xqedii_1ac9x13), var2, () -> {
                  {
                     this.Xqedii_1ac9x13.Xqedii_14dkzm2(var2, var4);
                  }
               });
            }
         }
      }
   }
}
