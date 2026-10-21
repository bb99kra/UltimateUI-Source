/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dev.xqedii.ultimateUI.service.npc.CursorNpcService
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketReceiveEvent
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType.Play$Client
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity.InteractAction
 *  org.bukkit.entity.Player
 */
package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.npc.CursorNpcService;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketReceiveEvent;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import org.bukkit.entity.Player;

/*
 * Exception performing whole class analysis ignored.
 */
public class CursorNpcPacketListener
extends PacketListenerAbstract {
    public final /* synthetic */ CursorNpcService Xqedii_276d0jk;
    private static transient /* synthetic */ String pnMswaDlHq = "00000000000000000000000000000000000000000000";

    public CursorNpcPacketListener(CursorNpcService cursorNpcService, PacketListenerPriority packetListenerPriority) {
        
        super(packetListenerPriority);
        this.Xqedii_276d0jk = cursorNpcService;
        
    }

    public void onPacketReceive(PacketReceiveEvent packetReceiveEvent) {
        if ((-1608034838 * (-1608034838 + 1) & 1) != 0) {
            throw new IllegalStateException();
        }
        if (packetReceiveEvent == null || packetReceiveEvent.getPacketType() != PacketType.Play.Client.INTERACT_ENTITY) {
            return;
        }
        Object object = packetReceiveEvent.getPlayer();
        if (!(object instanceof Player)) {
            return;
        }
        Player player = (Player)object;
        WrapperPlayClientInteractEntity wrapperPlayClientInteractEntity = new WrapperPlayClientInteractEntity(packetReceiveEvent);
        if (wrapperPlayClientInteractEntity.getAction() != WrapperPlayClientInteractEntity.InteractAction.ATTACK) {
            return;
        }
        this.Xqedii_276d0jk.Xqedii_1uicwm3(player, wrapperPlayClientInteractEntity.getEntityId());
    }
}
