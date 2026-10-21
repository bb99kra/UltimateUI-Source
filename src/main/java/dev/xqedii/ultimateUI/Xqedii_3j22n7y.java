/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dev.xqedii.ultimateUI.service.keybind.KeybindService
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketReceiveEvent
 *  org.bukkit.entity.Player
 */
package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.keybind.KeybindService;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketReceiveEvent;
import org.bukkit.entity.Player;

/*
 * Exception performing whole class analysis ignored.
 */
public class Xqedii_3j22n7y
extends PacketListenerAbstract {
    public final /* synthetic */ KeybindService Xqedii_2llqk7m;
    private static transient /* synthetic */ String vLzsZfmsdL = "00000000000000000000000000000000000000000000";

    public Xqedii_3j22n7y(KeybindService keybindService, PacketListenerPriority packetListenerPriority) {
        
        super(packetListenerPriority);
        this.Xqedii_2llqk7m = keybindService;
        
    }

    public void onPacketReceive(PacketReceiveEvent packetReceiveEvent) {
        Object object;
        if ((882397572 * 882397572 + 882397572 & 1) != 0) {
            throw new IllegalStateException();
        }
        if (packetReceiveEvent == null || !((object = packetReceiveEvent.getPlayer()) instanceof Player)) {
            return;
        }
        Player player = (Player)object;
        try {
            this.Xqedii_2llqk7m.Xqedii_16a4j29(packetReceiveEvent, player);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }
}
