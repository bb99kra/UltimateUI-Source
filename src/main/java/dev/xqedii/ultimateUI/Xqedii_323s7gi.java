/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dev.xqedii.ultimateUI.service.camera.CameraSoundMask
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketReceiveEvent
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType.Play$Client
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketTypeCommon
 *  org.bukkit.SoundCategory
 *  org.bukkit.entity.Player
 */
package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.camera.CameraSoundMask;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketReceiveEvent;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketTypeCommon;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

/*
 * Exception performing whole class analysis ignored.
 */
public class Xqedii_323s7gi
extends PacketListenerAbstract {
    public final /* synthetic */ CameraSoundMask Xqedii_2dsptlo;
    private static transient /* synthetic */ String nyjSbtQsuE = "00000000000000000000000000000000000000000000";

    public Xqedii_323s7gi(CameraSoundMask cameraSoundMask, PacketListenerPriority packetListenerPriority) {
        
        super(packetListenerPriority);
        this.Xqedii_2dsptlo = cameraSoundMask;
        
    }

    public void onPacketReceive(PacketReceiveEvent packetReceiveEvent) {
        Player player;
        if ((-231662848 * (-231662848 + 1) & 1) != 0) {
            throw new IllegalStateException();
        }
        if (packetReceiveEvent == null) {
            return;
        }
        PacketTypeCommon packetTypeCommon = packetReceiveEvent.getPacketType();
        if (packetTypeCommon != PacketType.Play.Client.ANIMATION && packetTypeCommon != PacketType.Play.Client.PLAYER_DIGGING) {
            return;
        }
        Object object = packetReceiveEvent.getPlayer();
        if (!(object instanceof Player) || !this.Xqedii_2dsptlo.Xqedii_1x43la0.contains((player = (Player)object).getUniqueId())) {
            return;
        }
        CameraSoundMask.Xqedii_h2zy5sr((Player)player, (String[])CameraSoundMask.Xqedii_qwpf507, (SoundCategory)SoundCategory.BLOCKS);
    }
}
