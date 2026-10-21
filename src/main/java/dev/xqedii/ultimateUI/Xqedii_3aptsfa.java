/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dev.xqedii.ultimateUI.Xqedii_zg8ict9
 *  dev.xqedii.ultimateUI.service.camera.CameraBase
 *  dev.xqedii.ultimateUI.service.camera.ShaderCursor
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketSendEvent
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType.Play$Server
 *  dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.server.WrapperPlayServerTimeUpdate
 *  org.bukkit.entity.Player
 */
package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.Xqedii_zg8ict9;
import dev.xqedii.ultimateUI.service.camera.CameraBase;
import dev.xqedii.ultimateUI.service.camera.ShaderCursor;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketSendEvent;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.server.WrapperPlayServerTimeUpdate;
import org.bukkit.entity.Player;

/*
 * Exception performing whole class analysis ignored.
 */
public class Xqedii_3aptsfa
extends PacketListenerAbstract {
    public final /* synthetic */ ShaderCursor Xqedii_lwcxpek;
    private static transient /* synthetic */ String arJRXaZmJR = "00000000000000000000000000000000000000000000";

    public Xqedii_3aptsfa(ShaderCursor shaderCursor, PacketListenerPriority packetListenerPriority) {
        
        super(packetListenerPriority);
        this.Xqedii_lwcxpek = shaderCursor;
        
    }

    public void onPacketSend(PacketSendEvent packetSendEvent) {
        long l;
        if ((0xA4C02DAD ^ 0xA4C02DAD) != 0) {
            throw new IllegalStateException();
        }
        if (packetSendEvent == null || packetSendEvent.getPacketType() != PacketType.Play.Server.TIME_UPDATE) {
            return;
        }
        Object object = packetSendEvent.getPlayer();
        if (!(object instanceof Player)) {
            return;
        }
        Player player = (Player)object;
        WrapperPlayServerTimeUpdate wrapper = new WrapperPlayServerTimeUpdate(packetSendEvent);
        this.Xqedii_lwcxpek.Xqedii_15whgsi(player, wrapper);
        if (CameraBase.Xqedii_7ws8hzn.contains(player.getUniqueId())) {
            l = this.Xqedii_lwcxpek.Xqedii_1gl4f7n(player);
        } else {
            Integer n = (Integer)this.Xqedii_lwcxpek.Xqedii_3hs42n4.get(player.getUniqueId());
            if (n == null) {
                return;
            }
            l = this.Xqedii_lwcxpek.Xqedii_1aj31i1(player.getUniqueId(), n.intValue());
        }
        if (wrapper.getWorldAge() == l) {
            return;
        }
        wrapper.setWorldAge(l);
        packetSendEvent.markForReEncode(Xqedii_zg8ict9.Xqedii_2037mq43e5w8z((int)1788129729, (int)12) != 0);
    }
}
