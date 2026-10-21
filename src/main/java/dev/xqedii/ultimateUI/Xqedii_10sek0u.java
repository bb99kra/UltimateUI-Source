/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dev.xqedii.ultimateUI.Xqedii_148dm0t
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketSendEvent
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType.Play$Server
 */
package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.Xqedii_148dm0t;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketSendEvent;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType;

/*
 * Exception performing whole class analysis ignored.
 */
public class Xqedii_10sek0u
extends PacketListenerAbstract {
    public final /* synthetic */ Xqedii_148dm0t Xqedii_300c5ku;
    private static transient /* synthetic */ String vsaCZmiFwr = "00000000000000000000000000000000000000000000";

    public Xqedii_10sek0u(Xqedii_148dm0t xqedii_148dm0t, PacketListenerPriority packetListenerPriority) {
        
        super(packetListenerPriority);
        this.Xqedii_300c5ku = xqedii_148dm0t;
        
    }

    public void onPacketSend(PacketSendEvent packetSendEvent) {
        if ((0xE731DC9A ^ 0xE731DC9A) != 0) {
            throw new IllegalStateException();
        }
        if (packetSendEvent.isCancelled()) {
            return;
        }
        if (packetSendEvent.getPacketType() == PacketType.Play.Server.BOSS_BAR) {
            this.Xqedii_300c5ku.Xqedii_22ibavs(packetSendEvent);
        } else if (packetSendEvent.getPacketType() == PacketType.Play.Server.ACTION_BAR || packetSendEvent.getPacketType() == PacketType.Play.Server.SYSTEM_CHAT_MESSAGE) {
            this.Xqedii_300c5ku.Xqedii_1xbo2da(packetSendEvent);
        }
    }
}
