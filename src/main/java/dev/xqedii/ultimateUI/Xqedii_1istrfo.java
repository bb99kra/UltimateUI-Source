/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dev.xqedii.ultimateUI.service.camera.AnvilScreenMask
 *  dev.xqedii.ultimateUI.service.camera.CameraBase
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketSendEvent
 *  org.bukkit.entity.Player
 */
package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.camera.AnvilScreenMask;
import dev.xqedii.ultimateUI.service.camera.CameraBase;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketSendEvent;
import org.bukkit.entity.Player;

/*
 * Exception performing whole class analysis ignored.
 */
public class Xqedii_1istrfo
extends PacketListenerAbstract {
    public final /* synthetic */ AnvilScreenMask Xqedii_3h3yt4y;
    private static transient /* synthetic */ String GzjGfIVFkL = "00000000000000000000000000000000000000000000";

    public Xqedii_1istrfo(AnvilScreenMask anvilScreenMask, PacketListenerPriority packetListenerPriority) {
        
        super(packetListenerPriority);
        this.Xqedii_3h3yt4y = anvilScreenMask;
        
    }

    public void onPacketSend(PacketSendEvent packetSendEvent) {
        Player player;
        Object object;
        if (((0x80BABE95 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (packetSendEvent == null || !((object = packetSendEvent.getPlayer()) instanceof Player) || !CameraBase.Xqedii_7ws8hzn.contains((player = (Player)object).getUniqueId())) {
            return;
        }
        this.Xqedii_3h3yt4y.Xqedii_pqsflim(packetSendEvent);
    }
}
