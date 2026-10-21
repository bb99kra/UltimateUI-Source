/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dev.xqedii.ultimateUI.PluginBootstrap
 *  dev.xqedii.ultimateUI.Xqedii_1ad21jv
 *  dev.xqedii.ultimateUI.Xqedii_38o2zes
 *  dev.xqedii.ultimateUI.service.gui.GuiService.ClickType
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketReceiveEvent
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType.Play$Client
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketTypeCommon
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.player.InteractionHand
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.client.WrapperPlayClientPlayerBlockPlacement
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.client.WrapperPlayClientUseItem
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.plugin.Plugin
 */
package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.PluginBootstrap;
import dev.xqedii.ultimateUI.Xqedii_1ad21jv;
import dev.xqedii.ultimateUI.Xqedii_38o2zes;
import dev.xqedii.ultimateUI.service.gui.GuiService;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketReceiveEvent;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketTypeCommon;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.player.InteractionHand;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.client.WrapperPlayClientPlayerBlockPlacement;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.client.WrapperPlayClientUseItem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/*
 * Exception performing whole class analysis ignored.
 */
public class ClientUseItemPacketListener
extends PacketListenerAbstract {
    public final /* synthetic */ PluginBootstrap Xqedii_zgojfjd;
    public final /* synthetic */ PluginBootstrap Xqedii_3hnjzsj;
    private static transient /* synthetic */ String MFrlqmVDRd = "00000000000000000000000000000000000000000000";

    public ClientUseItemPacketListener(PluginBootstrap pluginBootstrap, PacketListenerPriority packetListenerPriority, PluginBootstrap pluginBootstrap2) {
        super(packetListenerPriority);
        this.Xqedii_zgojfjd = pluginBootstrap2;
        this.Xqedii_3hnjzsj = pluginBootstrap;
    }

    public void onPacketReceive(PacketReceiveEvent packetReceiveEvent) {
        int n;
        if (((0xB435FA88 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (packetReceiveEvent == null) {
            return;
        }
        PacketTypeCommon packetTypeCommon = packetReceiveEvent.getPacketType();
        int n2 = n = packetTypeCommon == PacketType.Play.Client.PLAYER_BLOCK_PLACEMENT ? Xqedii_1ad21jv.Xqedii_3okz3tv90j087((int)2061595012, (int)23) : Xqedii_1ad21jv.Xqedii_3tmh54o84be9z((int)-80484834, (int)16);
        if (n == 0 && packetTypeCommon != PacketType.Play.Client.USE_ITEM) {
            return;
        }
        Object object = packetReceiveEvent.getPlayer();
        if (!(object instanceof Player)) {
            return;
        }
        Player player = (Player)object;
        if (this.Xqedii_3hnjzsj.Xqedii_1tuq2ol == null || this.Xqedii_3hnjzsj.Xqedii_2k1vujp == null || !this.Xqedii_3hnjzsj.Xqedii_1tuq2ol.isShaderCursorActive(player)) {
            return;
        }
        Object object2 = object = n != 0 ? new WrapperPlayClientPlayerBlockPlacement(packetReceiveEvent).getHand() : new WrapperPlayClientUseItem(packetReceiveEvent).getHand();
        if (object != InteractionHand.MAIN_HAND) {
            packetReceiveEvent.setCancelled(Xqedii_1ad21jv.Xqedii_3tmh54o84be9z((int)80484833, (int)8) != 0);
            return;
        }
        packetReceiveEvent.setCancelled(Xqedii_1ad21jv.Xqedii_3okz3tv90j087((int)1549868728, (int)5) != 0);
        Xqedii_38o2zes.Xqedii_1avvt9e((Plugin)this.Xqedii_zgojfjd, (Entity)player, () -> {
            if ((300367198 * 300367198 + 300367198 & 1) != 0) {
                throw new IllegalStateException();
            }
            if (!player.isOnline() || this.Xqedii_3hnjzsj.Xqedii_1tuq2ol == null || this.Xqedii_3hnjzsj.Xqedii_2k1vujp == null) {
                return;
            }
            if (!this.Xqedii_3hnjzsj.Xqedii_1tuq2ol.isShaderCursorActive(player)) {
                return;
            }
            this.Xqedii_3hnjzsj.Xqedii_1tuq2ol.refreshClientBarrierCube(player);
            this.Xqedii_3hnjzsj.Xqedii_1tuq2ol.resampleShaderCursor(player);
            this.Xqedii_3hnjzsj.Xqedii_2k1vujp.setPlayerHeldToolAir(player);
            this.Xqedii_3hnjzsj.Xqedii_2k1vujp.clickHoveredElement(player, GuiService.ClickType.RIGHT);
        });
    }
}
