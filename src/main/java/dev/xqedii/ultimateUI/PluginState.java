package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.bedrock.BedrockUiService;
import dev.xqedii.ultimateUI.service.bossbar.BossbarRenderService;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.GuiService;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.service.input.AnvilInputService;
import dev.xqedii.ultimateUI.service.keybind.KeybindService;
import dev.xqedii.ultimateUI.service.npc.CursorNpcService;
import dev.xqedii.ultimateUI.service.resourcepack.UiImageAtlasService;
import dev.xqedii.ultimateUI.util.PlatformCompat;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerCommon;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class PluginState extends JavaPlugin {
   public static final String Xqedii_2nvsqlj = "lang.yml";
   public static final String Xqedii_3a4ezh1 = "resourcepack";
   public static final String Xqedii_1tror93 = "pack";
   public static final String Xqedii_2kx7zvn = "generated";
   public static final String Xqedii_2f8ztji = "ultimateui.admin";
   public static final String Xqedii_zz5b27r = "ultimateui.command.use";
   public static final String Xqedii_361md6f = "ultimateui.command.open";
   public static final String Xqedii_1nfuso9 = "ultimateui.command.create";
   public static final String Xqedii_3c46a1x = "ultimateui.command.edit";
   public static final String Xqedii_1bk6hdn = "ultimateui.command.delete";
   public static final String Xqedii_2tj9fkr = "ultimateui.command.close";
   public static final String Xqedii_3spllpc = "ultimateui.command.sensitivity";
   public static final String Xqedii_rluayth = "ultimateui.command.shaders";
   public static final String Xqedii_hb2c383 = "ultimateui.command.shaders.others";
   public static final String Xqedii_147fc6b = "ultimateui.command.cursor";
   public static final String Xqedii_1tk69gi = "ultimateui.command.cursor.others";
   public static final String Xqedii_2ld6mit = "ultimateui.command.world";
   public static final String Xqedii_1bzu2g8 = "ultimateui.command.reload";
   public static final List<Entry<String, String>> Xqedii_2dpr802;
   public static final long UI_SESSION_SWITCH_DELAY_TICKS = 3L;
   public static final Map<String, String> Xqedii_1fxcb09;
   public static final float Xqedii_r0rik1m = 0.05F;
   public static final int Xqedii_o32tqnw = 3;
   public static final double Xqedii_1ft8l7d = 0.01;
   public static final MiniMessage Xqedii_1df0pdx;
   public static final LegacyComponentSerializer Xqedii_1gc2hbe;
   public static int Xqedii_299zx1x;
   public static float Xqedii_32orhhd;
   public HudService Xqedii_1fwpabk;
   public CameraService Xqedii_1tuq2ol;
   public CursorNpcService Xqedii_1a31167;
   public KeybindService Xqedii_f3ftqgq;
   public AnvilInputService Xqedii_2lp9ah4;
   public GuiService Xqedii_2k1vujp;
   public BedrockUiService Xqedii_1yufpo0;
   public Xqedii_2cm61d8 Xqedii_391oru2;
   public BossbarRenderService Xqedii_1h4p7ey;
   public CustomScriptEngine Xqedii_2nbb2r8;
   public YamlConfiguration Xqedii_389zivo;
   public PlatformCompat.CancellableTask Xqedii_1fzr9sp;
   public boolean Xqedii_1lguiuv = false;
   public LicenseRuntimeGuard Xqedii_1zavo1k;
   public boolean Xqedii_3g0zdbr = false;
   public LicenseStatus Xqedii_1mi7xjn = LicenseStatus.ERROR;
   public final Xqedii_2t253qr Xqedii_1e7k2q0 = new Xqedii_2t253qr();
   public Map<String, UiImageAtlasService.GeneratedImage> Xqedii_11hx7yt = new HashMap<>();
   public String Xqedii_3b8f4mz = "";
   public final Map<UUID, Location> Xqedii_1hpn9ga = new HashMap<>();
   public final Map<UUID, List<String>> Xqedii_yy2ps2m = new HashMap<>();
   public final Set<UUID> Xqedii_2t79mgx = ConcurrentHashMap.newKeySet();
   public final Set<UUID> Xqedii_12cf6tq = ConcurrentHashMap.newKeySet();
   public static final long EDITOR_TELEPORT_CLOSE_GUARD_TICKS = 6L;
   public static final long Xqedii_1rxndp1 = 60L;
   public PacketListenerCommon Xqedii_1pe76p2;
   public PacketListenerCommon Xqedii_3bx8dfb;
   public final AtomicBoolean Xqedii_3jm11h0 = new AtomicBoolean(false);

   static {
      {
         Entry[] var10000 = new Entry[12];
         var10000[0] = Map.entry(
            "open",
            "ultimateui.command.open"
         );
         var10000[1] = Map.entry(
            "create",
            "ultimateui.command.create"
         );
         var10000[2] = Map.entry(
            "edit",
            "ultimateui.command.edit"
         );
         var10000[3] = Map.entry(
            "delete",
            "ultimateui.command.delete"
         );
         var10000[4] = Map.entry(
            "close",
            "ultimateui.command.close"
         );
         var10000[5] = Map.entry(
            "menu",
            "ultimateui.command.edit"
         );
         var10000[6] = Map.entry(
            "sensitivity",
            "ultimateui.command.sensitivity"
         );
         var10000[7] = Map.entry(
            "shaders",
            "ultimateui.command.shaders"
         );
         var10000[8] = Map.entry(
            "cursor",
            "ultimateui.command.cursor"
         );
         var10000[9] = Map.entry(
            "world",
            "ultimateui.command.world"
         );
         var10000[10] = Map.entry(
            "reload",
            "ultimateui.command.reload"
         );
         var10000[11] = Map.entry(
            "bedrock",
            "ultimateui.command.reload"
         );
         Xqedii_2dpr802 = List.of(var10000);
         Xqedii_1fxcb09 = Map.of(
            "open",
            "ultimateui.command.open",
            "create",
            "ultimateui.command.create",
            "edit",
            "ultimateui.command.edit",
            "delete",
            "ultimateui.command.delete",
            "close",
            "ultimateui.command.close"
         );
         Xqedii_1df0pdx = MiniMessage.miniMessage();
         Xqedii_1gc2hbe = LegacyComponentSerializer.legacyAmpersand();
         Xqedii_299zx1x = 0;
         Xqedii_32orhhd = Float.intBitsToFloat(0);
      }
   }
}
