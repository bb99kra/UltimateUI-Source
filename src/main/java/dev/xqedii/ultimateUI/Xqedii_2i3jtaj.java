/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dev.xqedii.ultimateUI.PluginBootstrap
 *  dev.xqedii.ultimateUI.Xqedii_voo3zj9
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketReceiveEvent
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType.Play$Client
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity
 *  org.bukkit.entity.Player
 */
package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.PluginBootstrap;
import dev.xqedii.ultimateUI.Xqedii_voo3zj9;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketReceiveEvent;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import org.bukkit.entity.Player;

/*
 * Exception performing whole class analysis ignored.
 */
public class Xqedii_2i3jtaj
extends PacketListenerAbstract {
    private static transient /* synthetic */ String TsfJihyOCf = "00000000000000000000000000000000000000000000";

    public Xqedii_2i3jtaj(PluginBootstrap pluginBootstrap, PacketListenerPriority packetListenerPriority) {
        super(packetListenerPriority);
    }

    public void onPacketReceive(PacketReceiveEvent packetReceiveEvent) {
        if (((126497337 << 1 | 1) & 1) == 0) {
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
        if (wrapperPlayClientInteractEntity.getEntityId() == player.getEntityId()) {
            packetReceiveEvent.setCancelled(Xqedii_voo3zj9.Xqedii_2ogp6qa8eoarl((int)204576444, (int)2) != 0);
        }
    }
}
