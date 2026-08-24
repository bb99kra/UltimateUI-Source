package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import dev.xqedii.ultimateUI.integration.skript.UltimateUiSkriptBridge;
import dev.xqedii.ultimateUI.licensing.LicenseService;
import dev.xqedii.ultimateUI.licensing.LicenseStatus;
import dev.xqedii.ultimateUI.licensing.RuntimeGuard;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.GuiService;
import dev.xqedii.ultimateUI.service.gui.editor.interaction.GuiServiceEditorInteractionOverlaySupport;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.service.resourcepack.ResourcePackHostService;
import dev.xqedii.ultimateUI.service.resourcepack.UiImageAtlasService;
import dev.xqedii.ultimateUI.util.PlatformCompat;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.PacketEventsAPI;
import com.github.retrooper.packetevents.event.PacketListenerAbstract;
import com.github.retrooper.packetevents.event.PacketListenerCommon;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import com.github.retrooper.packetevents.protocol.packettype.PacketType.Play.Client;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.runtime.ObjectMethods;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.CodeSource;
import java.security.MessageDigest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDismountEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.IllegalPluginAccessException;
import org.bukkit.plugin.java.JavaPlugin;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;

public class UltimateUI extends JavaPlugin implements CommandExecutor, TabCompleter, Listener {
   private static final String LANG_FILE_NAME = "lang.yml";
   private static final String RESOURCE_PACK_FOLDER = "resourcepack";
   private static final String RESOURCE_PACK_PACK_FOLDER = "pack";
   private static final String RESOURCE_PACK_GENERATED_FOLDER = "generated";
   private static final String UUI_PERMISSION_ADMIN = "ultimateui.admin";
   private static final String UUI_PERMISSION_USE = "ultimateui.command.use";
   private static final String UUI_PERMISSION_OPEN = "ultimateui.command.open";
   private static final String UUI_PERMISSION_CREATE = "ultimateui.command.create";
   private static final String UUI_PERMISSION_EDIT = "ultimateui.command.edit";
   private static final String UUI_PERMISSION_DELETE = "ultimateui.command.delete";
   private static final String UUI_PERMISSION_CLOSE = "ultimateui.command.close";
   private static final String UUI_PERMISSION_RELOAD = "ultimateui.command.reload";
   private static final MiniMessage MM = MiniMessage.miniMessage();
   private static final LegacyComponentSerializer LEGACY_AMPERSAND = LegacyComponentSerializer.legacyAmpersand();
   private static int AX = 0;
   private static float OLD_YAW = 0.0F;
   private HudService hudService;
   private CameraService cameraService;
   private GuiService guiService;
   private ResourcePackHostService resourcePackHostService;
   private YamlConfiguration langConfig;
   private PlatformCompat.CancellableTask editorAutosaveTask;
   private boolean ownsPacketEventsApi = false;
   private LicenseService licenseService;
   private boolean licenseValid = false;
   private LicenseStatus startupLicenseStatus = LicenseStatus.ERROR;
   private final UiImageAtlasService uiImageAtlasService = new UiImageAtlasService();
   private Map<String, UiImageAtlasService.GeneratedImage> generatedUiImages = new HashMap<>();
   private String lastResourcePackDeliverySignature = "";
   private final Map<UUID, Location> returnLocations = new HashMap<>();
   private final Map<UUID, List<String>> pendingReopenPages = new HashMap<>();
   private PacketListenerCommon selfInteractPacketGuard;
   private final AtomicBoolean reloadInProgress = new AtomicBoolean(false);
   private static final String DEFAULT_PACK_RESOURCE_PREFIX = "pack/";

   public void onLoad() {
      this.saveDefaultConfig();
      this.mergeConfigDefaults();
      this.licenseService = new LicenseService(this);
      this.startupLicenseStatus = this.licenseService.verify();
      this.licenseValid = this.startupLicenseStatus == LicenseStatus.VALID;
      if (this.licenseValid) {
         try {
            PacketEventsAPI var1 = PacketEvents.getAPI();
            if (var1 == null) {
               PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
               this.ownsPacketEventsApi = true;
            }
         } catch (Throwable var2) {
            this.getLogger().severe("Failed to prepare PacketEvents API during onLoad.");
            var2.printStackTrace();
         }
      }
   }

   public void onEnable() {
      if (this.licenseValid && RuntimeGuard.h().accept()) {
         if (!this.isPacketEventsAvailable()) {
            this.printStartupBanner("<#fb5454>Inactive (Connection Error)", this.computeResourcePackBannerStatus());
            this.getServer().getPluginManager().disablePlugin(this);
         } else {
            try {
               PacketEventsAPI var5 = PacketEvents.getAPI();
               if (var5 == null) {
                  PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
                  this.ownsPacketEventsApi = true;
                  var5 = PacketEvents.getAPI();
               }

               if (var5 == null) {
                  throw new IllegalStateException("PacketEvents API is null");
               }

               if (!var5.isLoaded()) {
                  var5.load();
               }

               if (!var5.isInitialized()) {
                  var5.init();
               }

               this.preloadLibClasses();
               if (!this.verifyPacketEventsRuntime()) {
                  this.printStartupBanner("<#00f418>Valid", this.computeResourcePackBannerStatus());
                  this.getServer().getPluginManager().disablePlugin(this);
                  return;
               }

               this.hudService = new HudService(this);
               this.cameraService = new CameraService(this, this.hudService);
               this.guiService = new GuiService(this, this.hudService, this.cameraService);
               this.registerSelfInteractPacketGuard();
            } catch (Throwable var4) {
               this.getLogger().severe("Failed to initialize UltimateUI services: " + var4);
               var4.printStackTrace();
               this.printStartupBanner("<#00f418>Valid", this.computeResourcePackBannerStatus());
               this.getServer().getPluginManager().disablePlugin(this);
               return;
            }

            this.saveDefaultLangFile();
            this.reloadLangConfigFile();
            this.ensureResourcePackFolder();
            this.resourcePackHostService = new ResourcePackHostService(this);
            this.guiService.ensureGuiFoldersAndExample();
            this.rebuildUiImagesIntoResourcePack();
            this.ensureGeneratedResourcePackArchive(false);
            this.syncPackToXqediiSetups();
            String var6 = this.resolveResourcePackUrl();
            this.lastResourcePackDeliverySignature = this.computeResourcePackDeliverySignature(var6);
            this.getServer().getPluginManager().registerEvents(this, this);
            if (this.getCommand("uui") != null) {
               this.getCommand("uui").setExecutor(this);
               this.getCommand("uui").setTabCompleter(this);
            }

            this.scheduleEditorAutosaveTask();
            this.scheduleRuntimeWatchdog();
            this.scheduleLicenseRecheck();
            this.registerSkriptIntegration();

            for (Player var3 : Bukkit.getOnlinePlayers()) {
               this.cleanupPlayerRuntime(var3);
            }

            UltimateUIAPI.init(this);
            this.printStartupBanner("<#00f418>Valid", this.computeResourcePackBannerStatus());
         }
      } else {
         String var1;
         if (this.startupLicenseStatus == LicenseStatus.INVALID) {
            var1 = "<#fb5454>Inactive (Invalid)";
         } else {
            var1 = "<#fb5454>Inactive (Connection Error)";
         }

         this.printStartupBanner(var1, this.computeResourcePackBannerStatus());
         if (this.getCommand("uui") != null) {
            this.getCommand("uui").setExecutor(this);
            this.getCommand("uui").setTabCompleter(this);
         }
      }
   }

   private void preloadLibClasses() {
      try {
         CodeSource var1 = this.getClass().getProtectionDomain().getCodeSource();
         if (var1 == null) {
            return;
         }

         URL var2 = var1.getLocation();
         if (var2 == null) {
            return;
         }

         File var3 = new File(var2.toURI());
         if (!var3.exists()) {
            return;
         }

         ClassLoader var4 = this.getClass().getClassLoader();

         try (ZipFile var5 = new ZipFile(var3)) {
            Enumeration var6 = var5.entries();

            while (var6.hasMoreElements()) {
               ZipEntry var7 = (ZipEntry)var6.nextElement();
               String var8 = var7.getName();
               if (var8.startsWith("dev/xqedii/ultimateui/libs/") && var8.endsWith(".class")) {
                  String var9 = var8.replace('/', '.').replace(".class", "");

                  try {
                     Class.forName(var9, true, var4);
                  } catch (Throwable var12) {
                  }
               }
            }
         }
      } catch (Throwable var14) {
         this.getLogger().warning("[PacketEvents] Class preload failed: " + var14.getMessage());
      }
   }

   private void printStartupBanner(String var1, String var2) {
      ConsoleCommandSender var3 = Bukkit.getConsoleSender();
      var3.sendMessage(Component.empty());
      var3.sendMessage(MM.deserialize("<#ffba46>  _   _ _ _   _                 _         _   _ ___"));
      var3.sendMessage(MM.deserialize("<#ffba46> | | | | | |_(_)_ __ ___   __ _| |_ ___  | | | |_ _|"));
      var3.sendMessage(MM.deserialize("<#ffba46> | | | | | __| | '_ ` _ \\ / _` | __/ _ \\ | | | || |"));
      var3.sendMessage(MM.deserialize("<#ffba46> | |_| | | |_| | | | | | | (_| | ||  __/ | |_| || |"));
      var3.sendMessage(MM.deserialize("<#ffba46>  \\___/|_|\\__|_|_| |_| |_|\\__,_|\\__\\___|  \\___/|___|"));
      var3.sendMessage(Component.empty());
      var3.sendMessage(MM.deserialize("<#ffba46><bold>Thank you for using our products! <#de4d4d><3"));
      var3.sendMessage(MM.deserialize("<#ffc35e> ○ License: " + var1));
      var3.sendMessage(MM.deserialize("<#ffc35e> ○ Resource Pack: " + var2));
      var3.sendMessage(Component.empty());
   }

   private String computeResourcePackBannerStatus() {
      if (this.getConfig().getBoolean("resource-pack.hosting.self-host.enabled", false)) {
         return "<#00f418>Self-Host";
      } else if (this.getConfig().getBoolean("resource-pack.hosting.external-host.enabled", false)) {
         return "<#00f418>External Host";
      } else if (this.getConfig().getBoolean("resource-pack.hosting.external-pack.enabled", false)) {
         String var2 = "Unknown";
         if (this.getServer().getPluginManager().isPluginEnabled("ItemsAdder")) {
            var2 = "ItemsAdder";
         } else if (this.getServer().getPluginManager().isPluginEnabled("Oraxen")) {
            var2 = "Oraxen";
         } else if (this.getServer().getPluginManager().isPluginEnabled("Nexo")) {
            var2 = "Nexo";
         } else if (this.getServer().getPluginManager().isPluginEnabled("CraftEngine")) {
            var2 = "CraftEngine";
         }

         return "<#00f418>External Plugin <#c7c3c3>(" + var2 + ")";
      } else {
         if (this.getConfig().getBoolean("resource-pack.hosting.xqedii-setups.enabled", false)) {
            File var1 = new File(this.getDataFolder().getParentFile(), "XqediiSetups");
            if (var1.exists() && var1.isDirectory()) {
               return "<#00f418>XqediiSetups";
            }
         }

         return this.getConfig().getBoolean("resource-pack.hosting.default-pack.enabled", false) ? "<#00f418>Default Pack" : "<#fb5454>None (Disabled)";
      }
   }

   private void scheduleLicenseRecheck() {
      PlatformCompat.runAsyncTimer(this, 18000L, 18000L, () -> this.licenseService.recheck());
   }

   private void sendLicenseInvalidMessage(CommandSender var1) {
      var1.sendMessage(Component.empty());
      var1.sendMessage(MM.deserialize(" <#ffa13d><bold>Ultimate UI"));
      var1.sendMessage(Component.empty());
      var1.sendMessage(MM.deserialize(" <white>ʏᴏᴜʀ ʟɪᴄᴇɴsᴇ ɪs <#ff645e><underlined>ɴᴏᴛ ᴠᴀʟɪᴅ</underlined><white>! ᴘʟᴇᴀsᴇ ᴇɴᴛᴇʀ"));
      var1.sendMessage(MM.deserialize(" <white>ᴀ ᴠᴀʟɪᴅ ʟɪᴄᴇɴsᴇ ɪɴ ᴛʜᴇ <#ffac59>ᴄᴏɴғɪɢ.ʏᴍʟ<white> ғɪʟᴇ."));
      var1.sendMessage(Component.empty());
      var1.sendMessage(
         MM.deserialize(
            " <white>ɪғ ʏᴏᴜ ɴᴇᴇᴅ ʜᴇʟᴘ, ᴄʜᴇᴄᴋ <yellow><hover:show_text:'<green>Click here to open!'><click:open_url:'https://xqedii.dev/dc'>https://xqedii.dev/dc</click></hover>"
         )
      );
      var1.sendMessage(Component.empty());
      if (var1 instanceof Player var2) {
         try {
            var2.playSound(var2.getLocation(), "ultimateui.extra1", 1.0F, 1.0F);
         } catch (Throwable var4) {
         }
      }
   }

   private boolean handleUuiReloadCommand(CommandSender var1, String[] var2) {
      if (!this.hasCommandPermission(var1, "ultimateui.command.reload", "/uui reload")) {
         return true;
      } else if (var2.length != 1) {
         this.sendUuiCommandError(var1, "The reload command does not accept additional arguments.");
         return true;
      } else if (!this.reloadInProgress.compareAndSet(false, true)) {
         this.sendChat(var1, "<#fb5454>The config is already being refreshed. Please wait a moment...");
         return true;
      } else {
         this.sendChat(var1, "<#eeb408>Refreshing configuration and re-verifying license...");
         if (this.guiService != null) {
            try {
               this.guiService.runEditorAutosave();
            } catch (Throwable var5) {
            }
         }

         for (Player var4 : Bukkit.getOnlinePlayers()) {
            this.cleanupPlayerRuntime(var4);
         }

         this.reloadConfig();
         this.mergeConfigDefaults();
         this.reloadLangConfigFile();
         PlatformCompat.runAsyncTask(this, () -> {
            try {
               LicenseStatus var2x = this.licenseService.verify();
               if (var2x == LicenseStatus.VALID) {
                  PlatformCompat.runGlobalTask(this, () -> {
                     if (this.guiService == null) {
                        try {
                           this.hudService = new HudService(this);
                           this.cameraService = new CameraService(this, this.hudService);
                           this.guiService = new GuiService(this, this.hudService, this.cameraService);
                           this.resourcePackHostService = new ResourcePackHostService(this);
                           this.registerSelfInteractPacketGuard();
                           this.scheduleRuntimeWatchdog();
                           this.scheduleLicenseRecheck();
                        } catch (Throwable var3x) {
                           this.getLogger().severe("[UltimateUI] Failed to initialize services during reload: " + var3x.getMessage());
                        }
                     }

                     this.sendChat(var1, "<#1fed34>Generating resource pack...");
                  });
                  if (this.resourcePackHostService != null) {
                     try {
                        this.resourcePackHostService.stop();
                     } catch (Throwable var10) {
                     }
                  }

                  this.ensureResourcePackFolder();
                  if (this.guiService != null) {
                     try {
                        this.guiService.ensureGuiFoldersAndExample();
                     } catch (Throwable var9) {
                     }
                  }

                  this.rebuildUiImagesIntoResourcePack();
                  this.ensureGeneratedResourcePackArchive(true);
                  this.syncPackToXqediiSetups();
                  PlatformCompat.runGlobalTask(this, () -> {
                     int var2xx = this.generatedUiImages == null ? 0 : this.generatedUiImages.size();
                     this.sendChat(var1, "<#1fed34>Resource pack successfully generated! <#9dbec6>(" + var2xx + " images)");
                     String var3x = this.resolveResourcePackUrl();
                     this.lastResourcePackDeliverySignature = this.computeResourcePackDeliverySignature(var3x);
                     this.resendResourcePackToOnlinePlayers(var3x);
                     this.scheduleEditorAutosaveTask();
                     this.sendChat(var1, "<#00f018>Success! Configuration refreshed and license is valid.");
                  });
                  return;
               }

               String var3 = var2x == LicenseStatus.INVALID ? "Incorrect License Key" : "Connection Error";
               PlatformCompat.runGlobalTask(this, () -> this.sendChat(var1, "<#fb5454><bold>WARNING! </bold><#fb5454>License error: " + var3));
            } catch (Throwable var11) {
               PlatformCompat.runGlobalTask(this, () -> this.sendChat(var1, "<#fb5454><bold>WARNING! </bold><#fb5454>Reload failed: " + var11.getMessage()));
               return;
            } finally {
               this.reloadInProgress.set(false);
            }
         });
         return true;
      }
   }

   private void sendChat(CommandSender var1, String var2) {
      if (var1 instanceof Player) {
         var1.sendMessage(MM.deserialize(var2));
      } else {
         var1.sendMessage(PlainTextComponentSerializer.plainText().serialize(MM.deserialize(var2)));
      }
   }

   private void scheduleRuntimeWatchdog() {
      PlatformCompat.runAsyncTimer(
         this,
         1200L,
         1200L,
         () -> {
            if (!RuntimeGuard.h().accept()) {
               PlatformCompat.runGlobalTask(
                  this,
                  () -> {
                     this.getLogger()
                        .severe(
                           "Runtime integrity drift detected (code 0x"
                              + Integer.toHexString((int)(RuntimeGuard.h().uptimeNanos() & 4294967295L))
                              + "); shutting down."
                        );
                     this.getServer().getPluginManager().disablePlugin(this);
                  }
               );
            }
         }
      );
      PlatformCompat.runAsyncTaskLater(this, () -> {
         if (!RuntimeGuard.h().rebind() || !RuntimeGuard.h().accept()) {
            PlatformCompat.runGlobalTask(this, () -> {
               this.getLogger().severe("Runtime self-test failed (code 0x" + Integer.toHexString(System.identityHashCode(this.licenseService)) + ").");
               this.getServer().getPluginManager().disablePlugin(this);
            });
         }
      }, 1800L);
   }

   private void registerSkriptIntegration() {
      if (Bukkit.getPluginManager().getPlugin("Skript") != null) {
         try {
            UltimateUiSkriptBridge.register(this);
         } catch (Throwable var2) {
         }
      }
   }

   public void onDisable() {
      UltimateUIAPI.shutdown();
      if (this.cameraService != null || this.guiService != null || this.hudService != null) {
         for (Player var2 : Bukkit.getOnlinePlayers()) {
            this.cleanupPlayerRuntime(var2);
         }
      }

      if (this.resourcePackHostService != null) {
         this.resourcePackHostService.stop();
      }

      this.cancelEditorAutosaveTask();
      this.unregisterSelfInteractPacketGuard();
      if (this.hudService != null) {
         this.hudService.unregisterVirtualHudPassengerGuard();
      }

      PacketEventsAPI var4 = PacketEvents.getAPI();
      if (var4 != null) {
         if (this.ownsPacketEventsApi) {
            try {
               if (!var4.isTerminated()) {
                  var4.terminate();
               }
            } catch (Throwable var3) {
               this.getLogger().warning("Failed to terminate PacketEvents cleanly: " + var3.getMessage());
            }
         }
      }
   }

   private boolean isPacketEventsAvailable() {
      try {
         Class.forName("com.github.retrooper.packetevents.wrapper.PacketWrapper", false, this.getClassLoader());
         return true;
      } catch (ClassNotFoundException var2) {
         return false;
      }
   }

   private boolean verifyPacketEventsRuntime() {
      try {
         Class.forName("io.github.retrooper.packetevents.util.mappings.SynchronizedRegistriesHandler", true, this.getClassLoader());
         return true;
      } catch (Throwable var2) {
         this.getLogger().severe("PacketEvents runtime verification failed (likely dependency mismatch in deployed jar).");
         var2.printStackTrace();
         return false;
      }
   }

   private void registerSelfInteractPacketGuard() {
      PacketEventsAPI packetEventsAPI = PacketEvents.getAPI();
      if (packetEventsAPI != null) {
         this.unregisterSelfInteractPacketGuard();
         this.selfInteractPacketGuard = new PacketListenerAbstract(PacketListenerPriority.HIGHEST) {
            @Override
            public void onPacketReceive(PacketReceiveEvent event) {
               if (event != null && event.getPacketType() == com.github.retrooper.packetevents.protocol.packettype.PacketType.Play.Client.INTERACT_ENTITY) {
                  Object pObj = event.getPlayer();
                  if (pObj instanceof Player player) {
                     com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity packet = new com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity(event);
                     if (packet.getEntityId() == player.getEntityId()) {
                        event.setCancelled(true);
                     }
                  }
               }
            }
         };
         packetEventsAPI.getEventManager().registerListener(this.selfInteractPacketGuard);
      }
   }

   private void unregisterSelfInteractPacketGuard() {
      if (this.selfInteractPacketGuard != null) {
         PacketEventsAPI var1 = PacketEvents.getAPI();
         if (var1 != null) {
            try {
               var1.getEventManager().unregisterListener(this.selfInteractPacketGuard);
            } catch (Throwable var3) {
            }
         }

         this.selfInteractPacketGuard = null;
      }
   }

   private String resolveClassSource(Class<?> var1) {
      if (var1 == null) {
         return "unknown";
      } else {
         try {
            CodeSource var2 = var1.getProtectionDomain() == null ? null : var1.getProtectionDomain().getCodeSource();
            URL var3 = var2 == null ? null : var2.getLocation();
            return var3 == null ? "unknown" : var3.toString();
         } catch (Throwable var4) {
            return "unknown";
         }
      }
   }

   private Class<?> resolveClassIfAvailable(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         try {
            return Class.forName(var1, false, this.getClassLoader());
         } catch (Throwable var3) {
            return null;
         }
      } else {
         return null;
      }
   }

   private String resolveLocalPluginJarName() {
      try {
         File var1 = this.getFile();
         return var1 == null ? "unknown" : var1.getName();
      } catch (Throwable var2) {
         return "unknown";
      }
   }

   private void cleanupPlayerRuntime(Player var1) {
      if (var1 != null) {
         if (this.cameraService != null) {
            try {
               this.cameraService.forceStop(var1);
            } catch (IllegalPluginAccessException var8) {
            } catch (Throwable var9) {
               if (this.isEnabled()) {
                  this.getLogger().warning("Failed to force-stop camera for " + var1.getName() + ": " + var9.getMessage());
               }
            }
         }

         if (this.guiService != null) {
            try {
               this.guiService.closeGui(var1);
            } catch (IllegalPluginAccessException var6) {
            } catch (Throwable var7) {
               if (this.isEnabled()) {
                  this.getLogger().warning("Failed to close GUI for " + var1.getName() + ": " + var7.getMessage());
               }
            }
         }

         if (this.hudService != null) {
            try {
               this.hudService.clearHuds(var1);
            } catch (IllegalPluginAccessException var4) {
            } catch (Throwable var5) {
               if (this.isEnabled()) {
                  this.getLogger().warning("Failed to clear HUDs for " + var1.getName() + ": " + var5.getMessage());
               }
            }

            try {
               this.hudService.clearPassengerQueue(var1);
            } catch (Throwable var3) {
            }
         }

         this.returnLocations.remove(var1.getUniqueId());
         this.pendingReopenPages.remove(var1.getUniqueId());
      }
   }

   public void teleportToReturnLocationAndClear(Player var1) {
      if (var1 != null) {
         Location var2 = this.resolvePreferredReturnLocation(var1);
         this.returnLocations.remove(var1.getUniqueId());
         if (var2 != null) {
            PlatformCompat.teleportSafely(var1, var2);
         }
      }
   }

   private Location resolvePreferredReturnLocation(Player var1) {
      if (var1 == null) {
         return null;
      } else {
         if (this.cameraService != null && this.cameraService.isCameraActive(var1)) {
            Location var2 = this.cameraService.getCameraBaseLocation(var1);
            if (var2 != null && var2.getWorld() != null) {
               return var2.clone();
            }
         }

         Location var3 = this.returnLocations.get(var1.getUniqueId());
         return var3 != null && var3.getWorld() != null ? var3.clone() : null;
      }
   }

   private Location resolveReturnLocationBeforeOpen(Player var1, boolean var2) {
      if (var1 != null && !var2) {
         Location var3 = this.resolvePreferredReturnLocation(var1);
         return var3 != null ? var3 : var1.getLocation().clone();
      } else {
         return null;
      }
   }

   private void updateReturnLocationAfterOpen(Player var1, boolean var2, boolean var3, Location var4) {
      if (var1 != null && var2) {
         UUID var5 = var1.getUniqueId();
         if (var3) {
            this.returnLocations.remove(var5);
         } else {
            Location var6 = var4;
            if (var4 == null || var4.getWorld() == null) {
               var6 = var1.getLocation().clone();
            }

            this.returnLocations.put(var5, var6.clone());
         }
      }
   }

   public GuiService getGuiService() {
      return this.guiService;
   }

   public boolean openUiForPlayer(Player var1, String var2, boolean var3, boolean var4) {
      if (var1 != null && var2 != null && !var2.isBlank() && this.guiService != null) {
         List var5 = var3 ? Collections.emptyList() : this.guiService.getKeepOpenPageKeys(var1);
         List var6 = this.guiService.getReopenPageKeys(var1);
         if (!var6.isEmpty()) {
            this.pendingReopenPages.put(var1.getUniqueId(), new ArrayList<>(var6));
         }

         if (var3) {
            if (this.guiService.isEditorOpen(var1)) {
               this.closeUiForPlayer(var1);
            } else {
               this.closeUiForPlayer(var1, var2);
            }
         }

         if (!var3 && this.guiService.isEditorOpen(var1)) {
            this.closeUiForPlayer(var1);
         }

         Location var7 = this.resolveReturnLocationBeforeOpen(var1, var3);
         boolean var8 = var3 ? this.guiService.startGuiHud(var1, var2, var4) : this.guiService.startGui(var1, var2, var4);
         this.updateReturnLocationAfterOpen(var1, var8, var3, var7);
         if (!var3 && var8 && !var5.isEmpty()) {
            ArrayList var9 = new ArrayList(var5);
            PlatformCompat.runEntityTaskLater(this, var1, () -> {
               if (var1.isOnline()) {
                  for (Object var4x_raw : var9) {
                     String var4x = var4x_raw != null ? var4x_raw.toString() : null;
                     this.guiService.startGuiHud(var1, var4x, false);
                  }
               }
            }, 2L);
         }

         return var8;
      } else {
         return false;
      }
   }

   public void closeUiForPlayer(Player var1) {
      if (var1 != null) {
         if (this.guiService != null && this.guiService.hasOpenSession(var1)) {
            this.guiService.closeGui(var1);
            if (this.guiService.hasOpenSession(var1)) {
               return;
            }
         }

         this.closeUiForPlayerImmediate(var1);
      }
   }

   public void closeUiForPlayerImmediate(Player var1) {
      if (var1 != null) {
         List var2 = this.pendingReopenPages.remove(var1.getUniqueId());
         Location var3 = this.resolvePreferredReturnLocation(var1);
         this.cleanupPlayerRuntime(var1);
         if (var3 != null) {
            PlatformCompat.teleportSafely(var1, var3);
         }

         if (var2 != null && !var2.isEmpty() && var1.isOnline()) {
            ArrayList var4 = new ArrayList(var2);
            PlatformCompat.runEntityTaskLater(this, var1, () -> {
               if (var1.isOnline()) {
                  for (Object var4x_raw : var4) {
                     String var4x = var4x_raw != null ? var4x_raw.toString() : null;
                     this.openUiForPlayer(var1, var4x, true, false);
                  }
               }
            }, 2L);
         }
      }
   }

   public void closeUiForPlayerAndTeleport(Player var1, Location var2) {
      if (var1 != null) {
         this.pendingReopenPages.remove(var1.getUniqueId());
         this.returnLocations.remove(var1.getUniqueId());
         this.cleanupPlayerRuntime(var1);
         if (var2 != null) {
            PlatformCompat.runEntityTaskLater(this, var1, () -> {
               if (var1.isOnline()) {
                  PlatformCompat.teleportSafely(var1, var2);
               }
            }, 1L);
         }
      }
   }

   public boolean closeUiForPlayer(Player var1, String var2) {
      if (var1 != null && this.guiService != null) {
         String var3 = this.firstNonBlank(var2).trim();
         if (var3.isBlank()) {
            this.closeUiForPlayer(var1);
            return true;
         } else {
            String var4 = this.normalizeUiNameToken(var3);
            if (var4.isBlank()) {
               return false;
            } else {
               UUID var5 = var1.getUniqueId();
               Location var6 = this.resolvePreferredReturnLocation(var1);
               boolean var7 = this.guiService.closeGui(var1, var3);
               if (!var7) {
                  String var8 = this.firstNonBlank(this.guiService.getActiveGuiName(var1)).trim();
                  if (!var8.isBlank()) {
                     String var9 = this.normalizeUiNameToken(var8);
                     if (!var9.isBlank() && var9.equalsIgnoreCase(var4)) {
                        this.closeUiForPlayer(var1);
                        return true;
                     }
                  }

                  return false;
               } else {
                  if (!this.guiService.hasOpenSession(var1)) {
                     this.returnLocations.remove(var5);
                     if (var6 != null) {
                        PlatformCompat.teleportSafely(var1, var6);
                     }
                  }

                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   private String normalizeUiNameToken(String var1) {
      String var2 = this.firstNonBlank(var1).trim();
      if (var2.toLowerCase(Locale.ROOT).endsWith(".yml")) {
         var2 = var2.substring(0, var2.length() - 4);
      }

      var2 = var2.replace('\\', '_').replace('/', '_');
      var2 = var2.replaceAll("\\s+", "_");
      var2 = var2.replaceAll("[^a-zA-Z0-9_-]", "");
      var2 = var2.replaceAll("_+", "_");
      var2 = var2.replaceAll("^-+", "");
      var2 = var2.replaceAll("-+$", "");
      return var2.isBlank() ? "" : var2.toLowerCase(Locale.ROOT);
   }

   @EventHandler
   public void onDismount(EntityDismountEvent var1) {
      Entity var2 = var1.getEntity();
      if (var2.hasMetadata("hud")) {
         if (!var2.isDead() && var2.isValid()) {
            var1.setCancelled(true);
         }
      } else {
         if (var2 instanceof Player var3 && this.cameraService.isCameraActive(var3)) {
            var1.setCancelled(true);
         }
      }
   }

   @EventHandler
   public void onJoin(PlayerJoinEvent var1) {
      for (Player var3 : Bukkit.getOnlinePlayers()) {
         if (!var3.equals(var1.getPlayer())) {
            for (Entity var5 : this.hudService.getAllHuds(var3)) {
               var1.getPlayer().hideEntity(this, var5);
            }
         }
      }

      this.scheduleJoinResourcePackApply(var1.getPlayer());
      if (this.guiService != null) {
         Player var6 = var1.getPlayer();
         PlatformCompat.runEntityTaskLater(this, var6, () -> {
            if (var6.isOnline()) {
               this.guiService.openOnJoinForPlayer(var6);
            }
         }, 1L);
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR
   )
   public void onResourcePackStatus(PlayerResourcePackStatusEvent var1) {
      if (var1 != null) {
         Player var2 = var1.getPlayer();
         if (var2 != null) {
            String var3 = var1.getStatus().name();
            if ("ACCEPTED".equals(var3)) {
               if (this.isResourcePackChatMessagesEnabled()) {
                  var2.sendMessage(this.getLangMessage("resource-pack.messages.chat.loading", "<#dedb24>Resource pack is loading!"));
               }

               if (this.isResourcePackTitleMessagesEnabled()) {
                  Component var7 = this.getLangMessage("resource-pack.messages.title.title", "<#dedb24><bold>Resource pack is loading!");
                  Component var5 = this.getLangMessage("resource-pack.messages.title.subtitle", "<#80fbc6>Your resource pack will be loaded soon...");
                  Times var6 = Times.times(Duration.ofMillis(500L), Duration.ofHours(1L), Duration.ZERO);
                  var2.showTitle(Title.title(var7, var5, var6));
               }

               if (this.isResourcePackSoundEnabled()) {
                  this.playLoadingSounds(var2);
               }
            } else if ("SUCCESSFULLY_LOADED".equals(var3)) {
               this.forceClearTitle(var2);
               PlatformCompat.runEntityTaskLater(this, var2, () -> {
                  if (var2.isOnline()) {
                     this.hudService.resendAllHudPositions(var2);
                  }
               }, 3L);
               if (this.isResourcePackChatMessagesEnabled()) {
                  var2.sendMessage(this.getLangMessage("resource-pack.messages.chat.success", "<#42f032>Resource pack loaded successfully!"));
               }
            } else if ("DECLINED".equals(var3)) {
               this.forceClearTitle(var2);
               if (this.getConfig().getBoolean("resource-pack.kick-on-decline", false)) {
                  Component var4 = this.getLangMessage("resource-pack.messages.kick.decline-message", "&cYou didn't accept the Texture Pack!");
                  PlatformCompat.runEntityTaskLater(this, var2, () -> {
                     if (var2.isOnline()) {
                        var2.kick(var4);
                     }
                  }, 1L);
               }
            } else {
               if ("FAILED_DOWNLOAD".equals(var3) || "FAILED_RELOAD".equals(var3)) {
                  this.forceClearTitle(var2);
                  PlatformCompat.runEntityTaskLater(this, var2, () -> {
                     if (var2.isOnline()) {
                        this.forceClearTitle(var2);
                        if (this.isResourcePackChatMessagesEnabled()) {
                           var2.sendMessage(this.getLangMessage("resource-pack.messages.chat.failed", "<#f04f32>Resource pack was not loaded correctly!"));
                        }

                        if (this.getConfig().getBoolean("resource-pack.kick-on-fail", false)) {
                           Component var2x = this.getLangMessage("resource-pack.messages.kick.fail-message", "&cResource Pack download Failed!");
                           var2.kick(var2x);
                        }
                     }
                  }, 5L);
               }
            }
         }
      }
   }

   private void scheduleJoinResourcePackApply(Player var1) {
      if (var1 != null && var1.isOnline()) {
         if (this.getConfig().getBoolean("resource-pack.apply-on-join", true)) {
            String var2 = this.resolveResourcePackUrl();
            if (var2 != null && !var2.isBlank()) {
               this.sendResolvedResourcePack(var1, var2, 10L);
            }
         }
      }
   }

   private String resolveResourcePackUrl() {
      if (this.getConfig().getBoolean("resource-pack.hosting.xqedii-setups.enabled", false)) {
         File var1 = new File(this.getDataFolder().getParentFile(), "XqediiSetups");
         if (var1.exists() && var1.isDirectory()) {
            if (this.resourcePackHostService != null) {
               this.resourcePackHostService.stop();
            }

            return null;
         }
      }

      if (this.getConfig().getBoolean("resource-pack.hosting.external-pack.enabled", false)) {
         if (this.resourcePackHostService != null) {
            this.resourcePackHostService.stop();
         }

         return null;
      } else {
         if (this.getConfig().getBoolean("resource-pack.hosting.self-host.enabled", false)) {
            String var2 = this.resolveSelfHostedResourcePackUrl();
            if (var2 != null && !var2.isBlank()) {
               return var2;
            }
         }

         if (this.resourcePackHostService != null) {
            this.resourcePackHostService.stop();
         }

         if (this.getConfig().getBoolean("resource-pack.hosting.external-host.enabled", false)) {
            return this.normalizeResourcePackUrl(this.getConfig().getString("resource-pack.hosting.external-host.url", ""));
         } else {
            if (this.getConfig().getBoolean("resource-pack.hosting.default-pack.enabled", false)) {
               String var3 = this.licenseService != null ? this.licenseService.getPackUrl() : null;
               if (var3 != null && !var3.isBlank()) {
                  return this.normalizeResourcePackUrl(var3);
               }
            }

            return null;
         }
      }
   }

   private String resolveSelfHostedResourcePackUrl() {
      if (this.resourcePackHostService == null) {
         return null;
      } else {
         File var1 = this.ensureResourcePackFolder();
         String var2 = this.getConfig().getString("resource-pack.hosting.self-host.server-ip", "127.0.0.1");
         int var3 = this.getConfig().getInt("resource-pack.hosting.self-host.pack-port", 8123);
         String var4 = this.resourcePackHostService.resolveHostedPackUrl(var1, var2, var3);
         if (var4 != null && !var4.isBlank()) {
            return this.normalizeResourcePackUrl(var4);
         } else {
            File var5 = new File(var1, "pack");
            if (this.countFilesRecursively(var5) <= 0L) {
               this.getLogger().warning("[UltimateUI] Self-host resource pack is enabled, but no files were found in /resourcepack/pack to generate a zip.");
            }

            return null;
         }
      }
   }

   private void mergeConfigDefaults() {
      InputStream var1 = this.getResource("config.yml");
      if (var1 != null) {
         YamlConfiguration var2;
         try (InputStreamReader var3 = new InputStreamReader(var1, StandardCharsets.UTF_8)) {
            var2 = YamlConfiguration.loadConfiguration(var3);
         } catch (IOException var10) {
            return;
         }

         boolean var11 = false;

         for (Object var7_raw : new String[]{"self-host", "external-host", "external-pack", "default-pack", "xqedii-setups"}) {
            String var7 = var7_raw != null ? var7_raw.toString() : null;
            if (this.getConfig().contains("resource-pack.hosting." + var7 + ".enabled")
               && this.getConfig().getBoolean("resource-pack.hosting." + var7 + ".enabled", false)) {
               var11 = true;
               break;
            }
         }

         boolean var12 = false;

         for (String var14 : var2.getKeys(true)) {
            if (!var2.isConfigurationSection(var14) && !this.getConfig().contains(var14)) {
               Object var15 = var2.get(var14);
               if (Boolean.TRUE.equals(var15) && var11 && var14.startsWith("resource-pack.hosting.") && var14.endsWith(".enabled")) {
                  this.getConfig().set(var14, false);
               } else {
                  this.getConfig().set(var14, var15);
               }

               var12 = true;
            }
         }

         if (var12) {
            this.saveConfig();
         }
      }
   }

   private String normalizeResourcePackUrl(String var1) {
      if (var1 == null) {
         return null;
      } else {
         String var2 = var1.trim();
         if (var2.isBlank()) {
            return null;
         } else {
            String var3 = var2.toLowerCase(Locale.ROOT);
            return !var3.startsWith("http://") && !var3.startsWith("https://") ? null : var2;
         }
      }
   }

   private Component resolveResourcePackPromptComponent() {
      List var1 = this.getLangStringList("resource-pack.messages.prompt");
      if (var1.isEmpty()) {
         return null;
      } else {
         Component var2 = null;

         for (Object var4_raw : var1) {
            String var4 = var4_raw != null ? var4_raw.toString() : null;
            Component var5 = this.deserializeConfiguredMessage(var4);
            if (var2 == null) {
               var2 = var5;
            } else {
               var2 = var2.append(Component.newline()).append(var5);
            }
         }

         return var2;
      }
   }

   public List<UiImageAtlasService.GeneratedImage> getGeneratedUiImagesForEditor() {
      this.rebuildUiImagesIntoResourcePack();
      if (this.generatedUiImages != null && !this.generatedUiImages.isEmpty()) {
         ArrayList<UiImageAtlasService.GeneratedImage> var1 = new ArrayList<>();

         for (UiImageAtlasService.GeneratedImage var3 : this.generatedUiImages.values()) {
            if (var3 != null && var3.name() != null && !var3.name().isBlank()) {
               var1.add(var3);
            }
         }

         var1.sort(
            (var1x, var2) -> String.CASE_INSENSITIVE_ORDER
                  .compare(this.firstNonBlank(var1x == null ? null : var1x.name()), this.firstNonBlank(var2 == null ? null : var2.name()))
         );
         return var1;
      } else {
         return Collections.emptyList();
      }
   }

   public UiImageAtlasService.GeneratedImage resolveGeneratedUiImageForEditor(String var1) {
      UiImageAtlasService.GeneratedImage var2 = this.resolveGeneratedUiImage(var1);
      if (var2 != null) {
         return var2;
      } else {
         String var3 = this.alternateImageLookupName(var1);
         UiImageAtlasService.GeneratedImage var4 = this.uiImageAtlasService.resolveFromPersistedCodepoints(this.getDataFolder(), var1, this.getLogger());
         if (var4 == null && var3 != null) {
            var4 = this.uiImageAtlasService.resolveFromPersistedCodepoints(this.getDataFolder(), var3, this.getLogger());
         }

         if (var4 != null) {
            if (this.generatedUiImages == null) {
               this.generatedUiImages = new HashMap<>();
            }

            this.generatedUiImages.put(this.normalizeImageLookupKey(var1), var4);
            return var4;
         } else {
            return null;
         }
      }
   }

   private UiImageAtlasService.GeneratedImage resolveGeneratedUiImage(String var1) {
      if (var1 != null && !var1.isBlank() && this.generatedUiImages != null && !this.generatedUiImages.isEmpty()) {
         String var2 = this.normalizeImageLookupKey(var1);
         UiImageAtlasService.GeneratedImage var3 = this.generatedUiImages.get(var2);
         if (var3 != null) {
            return var3;
         } else {
            String var4 = this.alternateImageLookupKey(var2);
            return var4 != null ? this.generatedUiImages.get(var4) : null;
         }
      } else {
         return null;
      }
   }

   private String alternateImageLookupKey(String var1) {
      if (var1 != null && !var1.isBlank()) {
         return var1.startsWith("img_") ? var1.substring(4) : "img_" + var1;
      } else {
         return null;
      }
   }

   private String alternateImageLookupName(String var1) {
      if (var1 != null && !var1.isBlank()) {
         String var2 = this.normalizeImageLookupKey(var1);
         return var2.startsWith("img_") ? var2.substring(4) : "img_" + var2;
      } else {
         return null;
      }
   }

   private String normalizeImageLookupKey(String var1) {
      String var2 = this.firstNonBlank(var1).trim();
      int var3 = var2.lastIndexOf(46);
      if (var3 > 0) {
         var2 = var2.substring(0, var3);
      }

      return UiImageAtlasService.normalizeImageBaseName(var2);
   }

   private void rebuildUiImagesIntoResourcePack() {
      UiImageAtlasService.BuildResult var1 = this.uiImageAtlasService.rebuild(this.getDataFolder(), this.getLogger());
      if (var1 != null && var1.imagesByName() != null) {
         this.generatedUiImages = new HashMap<>(var1.imagesByName());
      } else {
         this.generatedUiImages = new HashMap<>();
      }
   }

   private void ensureGeneratedResourcePackArchive(boolean var1) {
      if (this.resourcePackHostService != null) {
         File var2 = this.ensureResourcePackFolder();
         File var3 = this.resourcePackHostService.ensureGeneratedArchive(var2, var1);
         if (var3 == null || !var3.exists() || !var3.isFile()) {
            File var4 = new File(var2, "pack");
            long var5 = this.countFilesRecursively(var4);
            if (var5 <= 0L) {
               this.getLogger().warning("[UltimateUI] resourcepack/pack is empty. Put your pack files there and run /uui reload.");
            } else {
               this.getLogger().warning("[UltimateUI] Failed to generate resourcepack/generated/resourcepack.zip from resourcepack/pack.");
            }
         }
      }
   }

   private void syncPackToXqediiSetups() {
      if (this.getConfig().getBoolean("resource-pack.hosting.xqedii-setups.enabled", false)) {
         File var1 = this.getDataFolder().getParentFile();
         if (var1 != null) {
            File var2 = new File(var1, "XqediiSetups");
            if (var2.exists() && var2.isDirectory()) {
               File var3 = new File(this.getDataFolder(), "resourcepack" + File.separator + "pack");
               File var4 = new File(var2, "contents" + File.separator + "pack");
               File var5 = new File(var3, "assets" + File.separator + "minecraft");
               if (var5.exists() && var5.isDirectory()) {
                  File var6 = new File(var4, "assets" + File.separator + "minecraft");
                  File var7 = new File(var6, "textures" + File.separator + "font" + File.separator + "images");
                  if (var7.exists() && var7.isDirectory()) {
                     File[] var8 = var7.listFiles();
                     if (var8 != null) {
                        for (File var12 : var8) {
                           if (var12.isFile()) {
                              var12.delete();
                           }
                        }
                     }
                  }

                  this.syncFolderToXqediiSetups(var5.toPath(), var6.toPath());
               }

               File var13 = new File(var3, "shaders");
               if (var13.exists() && var13.isDirectory()) {
                  this.syncFolderToXqediiSetups(var13.toPath(), new File(var4, "shaders").toPath());
               }

               this.getLogger().info("[UltimateUI] Resource pack synced to XqediiSetups.");
            }
         }
      }
   }

   private void syncFolderToXqediiSetups(Path var1, Path var2) {
      try (Stream<Path> var3 = Files.walk(var1)) {
         var3.forEach((Path var3x) -> {
            try {
               Path var4 = var1.relativize(var3x);
               Path var5 = var2.resolve(var4);
               if (Files.isDirectory(var3x)) {
                  if (!Files.exists(var5)) {
                     Files.createDirectories(var5);
                  }
               } else {
                  Files.createDirectories(var5.getParent());
                  Files.copy(var3x, var5, StandardCopyOption.REPLACE_EXISTING);
               }
            } catch (IOException var6) {
               this.getLogger().warning("[UltimateUI] XqediiSetups sync: failed to copy " + var3x.getFileName() + ": " + var6.getMessage());
            }
         });
      } catch (IOException var8) {
         this.getLogger().warning("[UltimateUI] Failed to sync folder to XqediiSetups: " + var8.getMessage());
      }
   }

   private long countFilesRecursively(File var1) {
      if (var1 == null || !var1.exists()) {
         return 0L;
      } else if (var1.isFile()) {
         return 1L;
      } else {
         File[] var2 = var1.listFiles();
         if (var2 != null && var2.length != 0) {
            long var3 = 0L;

            for (File var8 : var2) {
               var3 += this.countFilesRecursively(var8);
            }

            return var3;
         } else {
            return 0L;
         }
      }
   }

   private void resendResourcePackToOnlinePlayers(String var1) {
      if (var1 != null && !var1.isBlank()) {
         for (Player var3 : Bukkit.getOnlinePlayers()) {
            this.sendResolvedResourcePack(var3, var1, 1L);
         }
      }
   }

   private void sendResolvedResourcePack(Player var1, String var2, long var3) {
      if (var1 != null && var1.isOnline()) {
         String var5 = this.firstNonBlank(var2);
         if (!var5.isBlank()) {
            byte[] var6 = this.resolveResourcePackHashBytes();
            PlatformCompat.runEntityTaskLater(this, var1, () -> {
               if (var1.isOnline()) {
                  Component var4 = this.resolveResourcePackPromptComponent();

                  try {
                     if (var4 == null) {
                        if (var6 != null && var6.length != 0) {
                           var1.setResourcePack(var5, var6);
                        } else {
                           var1.setResourcePack(var5);
                        }
                     } else {
                        var1.setResourcePack(var5, var6, var4, false);
                     }
                  } catch (Throwable var6x) {
                     this.getLogger().warning("Failed to send resource pack to " + var1.getName() + ": " + var6x.getMessage());
                  }
               }
            }, Math.max(0L, var3));
         }
      }
   }

   private String computeResourcePackDeliverySignature(String var1) {
      String var2 = this.resolveResourcePackHostingModeKey();
      String var3 = this.firstNonBlank(var1);
      StringBuilder var4 = new StringBuilder();
      var4.append("mode=").append(var2);
      var4.append("|url=").append(var3);
      if ("self-host".equals(var2)) {
         File var5 = this.resolveGeneratedPackArchiveFile();
         if (var5 != null && var5.exists() && var5.isFile()) {
            var4.append("|zip-sha256=").append(this.computeFileSha256(var5));
         } else {
            var4.append("|zip=missing");
         }
      }

      return var4.toString();
   }

   private String resolveResourcePackHostingModeKey() {
      if (this.getConfig().getBoolean("resource-pack.hosting.external-pack.enabled", false)) {
         return "external-pack";
      } else if (this.getConfig().getBoolean("resource-pack.hosting.self-host.enabled", false)) {
         return "self-host";
      } else {
         return this.getConfig().getBoolean("resource-pack.hosting.external-host.enabled", false) ? "external-host" : "disabled";
      }
   }

   private File resolveGeneratedPackArchiveFile() {
      File var1 = new File(this.getDataFolder(), "resourcepack");
      File var2 = new File(var1, "generated");
      return new File(var2, "resourcepack.zip");
   }

   private byte[] resolveResourcePackHashBytes() {
      if (!"self-host".equals(this.resolveResourcePackHostingModeKey())) {
         return null;
      } else {
         File var1 = this.resolveGeneratedPackArchiveFile();
         if (var1 != null && var1.exists() && var1.isFile()) {
            byte[] var2 = this.computeFileDigest(var1, "SHA-1");
            return var2 != null && var2.length == 20 ? var2 : null;
         } else {
            return null;
         }
      }
   }

   private String computeFileSha256(File var1) {
      if (var1 != null && var1.exists() && var1.isFile()) {
         byte[] var2 = this.computeFileDigest(var1, "SHA-256");
         return var2 != null && var2.length != 0 ? this.toLowerHex(var2) : "sha256-error";
      } else {
         return "";
      }
   }

   private byte[] computeFileDigest(File var1, String var2) {
      if (var1 != null && var1.exists() && var1.isFile()) {
         String var3 = this.firstNonBlank(var2);
         if (var3.isBlank()) {
            return null;
         } else {
            try {
               MessageDigest var4 = MessageDigest.getInstance(var3);
               byte[] var5 = new byte[8192];

               int var7;
               try (InputStream var6 = Files.newInputStream(var1.toPath())) {
                  while ((var7 = var6.read(var5)) != -1) {
                     if (var7 > 0) {
                        var4.update(var5, 0, var7);
                     }
                  }
               }

               return var4.digest();
            } catch (Throwable var11) {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   private String toLowerHex(byte[] var1) {
      if (var1 != null && var1.length != 0) {
         StringBuilder var2 = new StringBuilder(var1.length * 2);

         for (byte var6 : var1) {
            var2.append(Character.forDigit(var6 >> 4 & 15, 16));
            var2.append(Character.forDigit(var6 & 15, 16));
         }

         return var2.toString();
      } else {
         return "";
      }
   }

   private String firstNonBlank(String... var1) {
      if (var1 != null && var1.length != 0) {
         for (Object var5_raw : var1) {
            String var5 = var5_raw != null ? var5_raw.toString() : null;
            if (var5 != null && !var5.isBlank()) {
               return var5;
            }
         }

         return "";
      } else {
         return "";
      }
   }

   private void playLoadingSounds(Player var1) {
      if (var1 != null) {
         float var2 = (float)Math.max(0.0, this.getConfig().getDouble("resource-pack.messages.sound.volume", 1.0));
         var1.playSound(var1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, var2, 1.0F);
         PlatformCompat.runEntityTaskLater(this, var1, () -> {
            if (var1.isOnline()) {
               var1.playSound(var1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, var2, 1.2F);
            }
         }, 2L);
         PlatformCompat.runEntityTaskLater(this, var1, () -> {
            if (var1.isOnline()) {
               var1.playSound(var1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, var2, 1.5F);
            }
         }, 4L);
      }
   }

   private void forceClearTitle(Player var1) {
      if (var1 != null) {
         var1.resetTitle();
         var1.clearTitle();
         Component var2 = MM.deserialize("<gray> ");
         Times var3 = Times.times(Duration.ZERO, Duration.ofMillis(50L), Duration.ZERO);
         var1.showTitle(Title.title(var2, var2, var3));
      }
   }

   private boolean isResourcePackChatMessagesEnabled() {
      return this.getConfig().getBoolean("resource-pack.messages.chat.enabled", false);
   }

   private boolean isResourcePackTitleMessagesEnabled() {
      return this.getConfig().getBoolean("resource-pack.messages.title.enabled", true);
   }

   private boolean isResourcePackSoundEnabled() {
      return this.getConfig().getBoolean("resource-pack.messages.sound.enabled", true);
   }

   private void scheduleEditorAutosaveTask() {
      this.cancelEditorAutosaveTask();
      if (this.guiService != null) {
         if (this.getConfig().getBoolean("editor.autosave.enabled", true)) {
            long var1 = this.resolveEditorAutosaveIntervalTicks();
            this.editorAutosaveTask = PlatformCompat.runAsyncTimer(this, var1, var1, () -> {
               try {
                  this.guiService.runEditorAutosave();
               } catch (Throwable var2) {
                  this.getLogger().warning("[UltimateUI] Editor autosave task failed: " + var2.getMessage());
               }
            });
         }
      }
   }

   private void cancelEditorAutosaveTask() {
      if (this.editorAutosaveTask != null) {
         this.editorAutosaveTask.cancel();
         this.editorAutosaveTask = null;
      }
   }

   private long resolveEditorAutosaveIntervalTicks() {
      long var1 = Math.max(1L, this.getConfig().getLong("editor.autosave.interval-seconds", 300L));
      return Math.max(20L, var1 * 20L);
   }

   private File ensureResourcePackFolder() {
      File var1 = new File(this.getDataFolder(), "resourcepack");
      if (!var1.exists()) {
         var1.mkdirs();
      }

      File var2 = new File(var1, "pack");
      boolean var3 = !var2.exists();
      if (var3) {
         var2.mkdirs();
      }

      File var4 = new File(var1, "generated");
      if (!var4.exists()) {
         var4.mkdirs();
      }

      if (!new File(var2, "pack.mcmeta").exists()) {
         this.extractDefaultPackFiles(var2);
      }

      this.migrateLegacyResourcePackLayout(this.getDataFolder(), var2);
      return var1;
   }

   private void extractDefaultPackFiles(File var1) {
      File var2 = this.getFile();
      if (var2 != null && var2.isFile()) {
         int var3 = 0;

         try (ZipFile var4 = new ZipFile(var2)) {
            Enumeration var5 = var4.entries();

            while (var5.hasMoreElements()) {
               ZipEntry var6 = (ZipEntry)var5.nextElement();
               String var7 = var6.getName();
               if (var7.startsWith("pack/")) {
                  String var8 = var7.substring("pack/".length());
                  if (!var8.isEmpty()) {
                     File var9 = new File(var1, var8.replace('/', File.separatorChar));
                     if (var6.isDirectory()) {
                        var9.mkdirs();
                     } else {
                        var9.getParentFile().mkdirs();
                        if (!var9.exists()) {
                           try (InputStream var10 = var4.getInputStream(var6)) {
                              Files.copy(var10, var9.toPath(), StandardCopyOption.REPLACE_EXISTING);
                              var3++;
                           }
                        }
                     }
                  }
               }
            }
         } catch (IOException var17) {
            this.getLogger().warning("[UltimateUI] Failed to extract default pack files: " + var17.getMessage());
         }

         if (var3 > 0) {
            this.getLogger().info("[UltimateUI] Extracted " + var3 + " default resource pack files into /resourcepack/pack.");
         }
      }
   }

   private void migrateLegacyResourcePackLayout(File var1, File var2) {
      if (var1 != null && var2 != null) {
         if (!this.hasCanonicalResourcePackLayout(var2)) {
            File[] var3 = var1.listFiles();
            if (var3 != null && var3.length != 0) {
               boolean var4 = false;

               for (File var8 : var3) {
                  if (this.isLegacyResourcePackRootEntry(var8)) {
                     File var9 = new File(var2, var8.getName());

                     try {
                        this.copyLegacyResourcePackEntry(var8, var9);
                        var4 = true;
                     } catch (IOException var11) {
                        this.getLogger().warning("[UltimateUI] Failed to migrate legacy resource pack entry '" + var8.getName() + "': " + var11.getMessage());
                     }
                  }
               }

               if (var4) {
                  this.getLogger().info("[UltimateUI] Migrated legacy resource pack files into /resourcepack/pack.");
               }
            }
         }
      }
   }

   private boolean hasCanonicalResourcePackLayout(File var1) {
      if (var1 == null) {
         return false;
      } else {
         File var2 = new File(var1, "pack.mcmeta");
         return var2.exists() && var2.isFile();
      }
   }

   private boolean isLegacyResourcePackRootEntry(File var1) {
      if (var1 != null && var1.exists()) {
         String var2 = var1.getName();
         if (var2 == null || var2.isBlank()) {
            return false;
         } else if (var1.isFile()) {
            return "pack.mcmeta".equalsIgnoreCase(var2) || "pack.png".equalsIgnoreCase(var2);
         } else {
            return !var1.isDirectory() ? false : "assets".equalsIgnoreCase(var2) || this.isLegacyVersionOverlayFolder(var2);
         }
      } else {
         return false;
      }
   }

   private boolean isLegacyVersionOverlayFolder(String var1) {
      return var1 != null && !var1.isBlank() ? var1.matches("\\d+_[0-9A-Za-z_]+$") : false;
   }

   private void copyLegacyResourcePackEntry(File var1, File var2) throws IOException {
      if (var1 != null && var2 != null) {
         if (var1.isDirectory()) {
            this.copyLegacyResourcePackDirectory(var1, var2);
         } else {
            File var3 = var2.getParentFile();
            if (var3 != null && !var3.exists()) {
               var3.mkdirs();
            }

            Files.copy(var1.toPath(), var2.toPath(), StandardCopyOption.REPLACE_EXISTING);
         }
      }
   }

   private void copyLegacyResourcePackDirectory(File var1, File var2) throws IOException {
      if (var1 != null && var2 != null && var1.exists() && var1.isDirectory()) {
         if (!var2.exists()) {
            var2.mkdirs();
         }

         File[] var3 = var1.listFiles();
         if (var3 != null && var3.length != 0) {
            for (File var7 : var3) {
               if (var7 != null) {
                  this.copyLegacyResourcePackEntry(var7, new File(var2, var7.getName()));
               }
            }
         }
      }
   }

   private void saveDefaultLangFile() {
      File var1 = new File(this.getDataFolder(), "lang.yml");
      if (!var1.exists()) {
         this.saveResource("lang.yml", false);
      }
   }

   private void reloadLangConfigFile() {
      this.saveDefaultLangFile();
      File var1 = new File(this.getDataFolder(), "lang.yml");
      this.langConfig = YamlConfiguration.loadConfiguration(var1);
   }

   private List<String> getLangStringList(String var1) {
      if (var1 != null && !var1.isBlank() && this.langConfig != null) {
         List var2 = this.langConfig.getStringList(var1);
         if (var2 != null && !var2.isEmpty()) {
            return this.expandLangMessageLines(var2);
         } else {
            String var3 = this.langConfig.getString(var1, "");
            return var3 != null && !var3.isBlank() ? this.expandLangMessageLines(Collections.singletonList(var3)) : Collections.emptyList();
         }
      } else {
         return Collections.emptyList();
      }
   }

   private List<String> expandLangMessageLines(List<String> var1) {
      if (var1 != null && !var1.isEmpty()) {
         ArrayList var2 = new ArrayList();

         for (Object var4_raw : var1) {
            String var4 = var4_raw != null ? var4_raw.toString() : null;
            if (var4 != null) {
               String var5 = var4.replace("\r\n", "\n").replace("\r", "\n").replace("\\n", "\n");
               String[] var6 = var5.split("\n", -1);
               Collections.addAll(var2, var6);
            }
         }

         return var2;
      } else {
         return Collections.emptyList();
      }
   }

   private Component getLangMessage(String var1, String var2) {
      String var3 = var2;
      if (this.langConfig != null && var1 != null && !var1.isBlank()) {
         var3 = this.langConfig.getString(var1, var2);
      }

      return this.deserializeConfiguredMessage(var3);
   }

   public Component getLangMessageWithPlaceholders(String var1, String var2, Map<String, String> var3) {
      String var4 = var2;
      if (this.langConfig != null && var1 != null && !var1.isBlank()) {
         var4 = this.langConfig.getString(var1, var2);
      }

      if (var4 != null && !var4.isBlank() && var3 != null && !var3.isEmpty()) {
         String var5 = var4;

         for (Entry var7 : var3.entrySet()) {
            if (var7 != null) {
               String var8 = (String)var7.getKey();
               if (var8 != null && !var8.isBlank()) {
                  String var9 = var7.getValue() == null ? "" : (String)var7.getValue();
                  var5 = var5.replace("{" + var8 + "}", var9);
               }
            }
         }

         var4 = var5;
      }

      return this.deserializeConfiguredMessage(var4);
   }

   private Component deserializeConfiguredMessage(String var1) {
      String var2 = var1 == null ? "" : var1;
      if (var2.contains("<") && var2.contains(">")) {
         try {
            return MM.deserialize(var2);
         } catch (Throwable var4) {
         }
      }

      return LEGACY_AMPERSAND.deserialize(var2);
   }

   @EventHandler(
      priority = EventPriority.MONITOR
   )
   public void onQuit(PlayerQuitEvent var1) {
      this.cleanupPlayerRuntime(var1.getPlayer());
   }

   @EventHandler(
      priority = EventPriority.MONITOR,
      ignoreCancelled = true
   )
   public void onKick(PlayerKickEvent var1) {
      this.cleanupPlayerRuntime(var1.getPlayer());
   }

   @EventHandler(
      priority = EventPriority.MONITOR
   )
   public void onPlayerDeath(PlayerDeathEvent var1) {
      if (var1 != null && this.guiService != null) {
         Player var2 = var1.getEntity();
         if (this.guiService.isAnyPageCloseOnDeath(var2)) {
            this.pendingReopenPages.remove(var2.getUniqueId());
            this.closeUiForPlayer(var2);
         }
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR,
      ignoreCancelled = true
   )
   public void onEntityDamage(EntityDamageEvent var1) {
      if (var1 != null && this.guiService != null) {
         if (var1.getEntity() instanceof Player var2) {
            if (this.guiService.isAnyPageCloseOnDamage(var2)) {
               this.pendingReopenPages.remove(var2.getUniqueId());
               this.closeUiForPlayer(var2);
            }
         }
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onInteract(PlayerInteractEvent var1) {
      Action var2 = var1.getAction();
      if (var1.getHand() == EquipmentSlot.HAND) {
         Player var3 = var1.getPlayer();
         if (this.cameraService.isCameraActive(var3)) {
            if (var2 == Action.LEFT_CLICK_AIR || var2 == Action.LEFT_CLICK_BLOCK) {
               this.cameraService.suppressStoneBreakAndHitSounds(var3);
               var1.setCancelled(true);
            } else if (var2 == Action.RIGHT_CLICK_AIR || var2 == Action.RIGHT_CLICK_BLOCK) {
               this.cameraService.refreshClientBarrierCube(var3);
               this.guiService.setPlayerHeldToolAir(var3);
               this.guiService.clickHoveredElement(var3, GuiService.ClickType.RIGHT);
               var1.setCancelled(true);
               PlatformCompat.runEntityTask(this, var3, () -> {
                  if (var3.isOnline() && this.cameraService.isCameraActive(var3)) {
                     this.guiService.setPlayerHeldToolAir(var3);
                  }
               });
               PlatformCompat.runEntityTaskLater(this, var3, () -> {
                  if (var3.isOnline() && this.cameraService.isCameraActive(var3)) {
                     this.guiService.setPlayerHeldToolAir(var3);
                  }
               }, 1L);
            }
         }
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onAnimation(PlayerAnimationEvent var1) {
      if (var1.getAnimationType() == PlayerAnimationType.ARM_SWING) {
         Player var2 = var1.getPlayer();
         if (this.cameraService.isCameraActive(var2)) {
            this.cameraService.refreshClientBarrierCube(var2);
            this.guiService.clickHoveredElement(var2, GuiService.ClickType.LEFT);
         }
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onBlockPlace(BlockPlaceEvent var1) {
      if (this.cameraService.isCameraActive(var1.getPlayer())) {
         var1.setCancelled(true);
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onBlockBreak(BlockBreakEvent var1) {
      if (this.cameraService.isCameraActive(var1.getPlayer())) {
         this.cameraService.suppressStoneBreakAndHitSounds(var1.getPlayer());
         var1.setCancelled(true);
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onBlockDamage(BlockDamageEvent var1) {
      if (this.cameraService.isCameraActive(var1.getPlayer())) {
         this.cameraService.suppressStoneBreakAndHitSounds(var1.getPlayer());
         var1.setCancelled(true);
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onItemDrop(PlayerDropItemEvent var1) {
      if (this.cameraService.isCameraActive(var1.getPlayer())) {
         var1.setCancelled(true);
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onHotbarScroll(PlayerItemHeldEvent var1) {
      Player var2 = var1.getPlayer();
      if (this.cameraService.isCameraActive(var2)) {
         int var3 = var1.getNewSlot() - var1.getPreviousSlot();
         if (var3 > 4) {
            var3 -= 9;
         } else if (var3 < -4) {
            var3 += 9;
         }

         boolean var4 = this.guiService.handleScroll(var2, var3);
         if (!var4) {
            this.guiService.handleRuntimeScrollClick(var2, var3);
         }

         var1.setCancelled(true);
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onMove(PlayerMoveEvent var1) {
      Player var2 = var1.getPlayer();
      Location var3 = var1.getFrom();
      Location var4 = var1.getTo();
      if (var4 != null) {
         double var5 = var4.getX() - var3.getX();
         double var7 = var4.getY() - var3.getY();
         double var9 = var4.getZ() - var3.getZ();
         boolean var11 = Math.abs(var5) > 1.0E-4 || Math.abs(var7) > 1.0E-4 || Math.abs(var9) > 1.0E-4;
         if (!this.cameraService.isCameraActive(var2)) {
            if (var11) {
               this.guiService.handleRuntimeHudFollowMove(var2, var5, var7, var9);
            }
         } else {
            if (var11) {
               this.guiService.handleEditorMovementShortcut(var2, var5, var9, var3.getYaw());
               var1.setTo(new Location(var3.getWorld(), var3.getX(), var3.getY(), var3.getZ(), var4.getYaw(), var4.getPitch()));
            }
         }
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR,
      ignoreCancelled = true
   )
   public void onToggleSneak(PlayerToggleSneakEvent var1) {
      if (var1 != null && this.guiService != null) {
         Player var2 = var1.getPlayer();
         if (var2 != null) {
            this.guiService.handleRuntimeHudSneakToggle(var2, var1.isSneaking());
         }
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR
   )
   public void onRespawn(PlayerRespawnEvent var1) {
      if (var1 != null && this.guiService != null) {
         Player var2 = var1.getPlayer();
         PlatformCompat.runEntityTaskLater(this, var2, () -> {
            if (var2.isOnline()) {
               this.guiService.restoreRuntimeHudSession(var2);
            }
         }, 2L);
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR
   )
   public void onChangedWorld(PlayerChangedWorldEvent var1) {
      if (var1 != null && this.guiService != null) {
         Player var2 = var1.getPlayer();
         PlatformCompat.runEntityTaskLater(this, var2, () -> {
            if (var2.isOnline()) {
               this.guiService.restoreRuntimeHudSession(var2);
            }
         }, 1L);
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR,
      ignoreCancelled = true
   )
   public void onTeleport(PlayerTeleportEvent var1) {
      if (var1 != null && this.guiService != null) {
         Location var2 = var1.getFrom();
         Location var3 = var1.getTo();
         if (var2 != null && var3 != null) {
            if (var2.getWorld() != null && var3.getWorld() != null && var2.getWorld().getUID().equals(var3.getWorld().getUID())) {
               if (!(var2.distanceSquared(var3) < 64.0)) {
                  Player var4 = var1.getPlayer();
                  PlatformCompat.runEntityTaskLater(this, var4, () -> {
                     if (var4.isOnline()) {
                        this.guiService.restoreRuntimeHudSession(var4);
                     }
                  }, 1L);
               }
            }
         }
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST
   )
   public void onInventoryClick(InventoryClickEvent var1) {
      if (var1.getWhoClicked() instanceof Player var2 && this.cameraService.isCameraActive(var2)) {
         this.guiService.handleItemDesignInventoryClick(var2, var1.getCurrentItem(), var1.getCursor(), var2.getItemOnCursor());
         var1.setCancelled(true);
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST
   )
   public void onInventoryDrag(InventoryDragEvent var1) {
      if (var1.getWhoClicked() instanceof Player var2 && this.cameraService.isCameraActive(var2)) {
         this.guiService.handleItemDesignInventoryClick(var2, var1.getOldCursor(), var1.getCursor(), var2.getItemOnCursor());
         var1.setCancelled(true);
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR
   )
   public void onInventoryClose(InventoryCloseEvent var1) {
      if (var1.getPlayer() instanceof Player var2) {
         if (this.cameraService.isCameraActive(var2)) {
            PlatformCompat.runEntityTaskLater(this, var2, () -> {
               if (var2.isOnline() && this.cameraService.isCameraActive(var2)) {
                  this.guiService.reapplyClientHotbarAir(var2);
               }
            }, 1L);
         }
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onAsyncChat(AsyncPlayerChatEvent var1) {
      Player var2 = var1.getPlayer();
      if (this.cameraService.isCameraActive(var2)) {
         if (this.guiService.isAwaitingSidebarInput(var2)) {
            var1.setCancelled(true);
            String var3 = var1.getMessage();
            PlatformCompat.runEntityTask(this, var2, () -> this.guiService.handleSidebarChatInput(var2, var3));
         }
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent var1) {
      Player var2 = var1.getPlayer();
      String var3 = var1.getMessage();
      if (var3 != null && !var3.isBlank()) {
         String var4 = var3.trim();
         if (var4.startsWith("/")) {
            var4 = var4.substring(1).trim();
         }

         if (!var4.isBlank()) {
            String var5 = var4;
            int var6 = var4.indexOf(32);
            if (var6 >= 0) {
               var5 = var4.substring(0, var6);
            }

            if (!var5.equalsIgnoreCase("uui") && !var5.toLowerCase(Locale.ROOT).startsWith("uui:")) {
               String var7 = this.guiService.resolveGuiByCustomCommand(var3);
               if (var7 != null && !var7.isBlank()) {
                  var1.setCancelled(true);
                  this.openUiForPlayer(var2, var7, false, false);
               }
            }
         }
      }
   }

   public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
      if (var2.getName().equalsIgnoreCase("uui") && var4.length >= 1 && var4[0].equalsIgnoreCase("reload")) {
         return this.handleUuiReloadCommand(var1, var4);
      } else if (var2.getName().equalsIgnoreCase("uui") && var4.length >= 1 && var4[0].equalsIgnoreCase("imgcurve")) {
         if (var4.length >= 3) {
            try {
               double var14 = Double.parseDouble(var4[1].replace(',', '.'));
               double var23 = Double.parseDouble(var4[2].replace(',', '.'));
               GuiServiceEditorInteractionOverlaySupport.IMAGE_PARTIAL_GLYPH_X_SLOPE = var14;
               GuiServiceEditorInteractionOverlaySupport.IMAGE_PARTIAL_GLYPH_X_CAP = var23;
               var1.sendMessage("§a[UltimateUI] img curve slope=" + var14 + " cap=" + var23 + " §7- re-place / nudge-resize the image to apply.");
            } catch (NumberFormatException var13) {
               var1.sendMessage("§c[UltimateUI] Invalid numbers. Usage: /uui imgcurve <slope> <cap>");
            }
         } else {
            var1.sendMessage(
               "§e[UltimateUI] img curve slope="
                  + GuiServiceEditorInteractionOverlaySupport.IMAGE_PARTIAL_GLYPH_X_SLOPE
                  + " cap="
                  + GuiServiceEditorInteractionOverlaySupport.IMAGE_PARTIAL_GLYPH_X_CAP
            );
         }

         return true;
      } else if (this.licenseService == null || !this.licenseService.isValid()) {
         this.sendLicenseInvalidMessage(var1);
         return true;
      } else if (this.guiService == null) {
         this.sendUuiCommandError(var1, "Plugin services failed to initialize on startup. Run /uui reload to retry.");
         return true;
      } else if (!var2.getName().equalsIgnoreCase("uui")) {
         return true;
      } else if (!(var1 instanceof Player var5)) {
         this.sendUuiCommandError(var1, "Only players can use this subcommand.");
         return true;
      } else if (var4.length < 1) {
         if (!this.hasCommandPermissionSilent(var5, "ultimateui.command.use")) {
            var5.sendMessage(this.getLangMessage("gui.cant-do-this", "&cYou can't do this!"));
         } else {
            this.sendUuiHelp(var5);
         }

         return true;
      } else if (!this.hasCommandPermission(var5, "ultimateui.command.use", "/uui")) {
         return true;
      } else {
         if (var4[0].equalsIgnoreCase("create")) {
            if (!this.hasCommandPermission(var5, "ultimateui.command.create", "/uui create")) {
               return true;
            }

            if (var4.length != 2) {
               this.sendUuiCommandError(var5, "Create requires exactly one UI name. Example: /uui create [name].");
               return true;
            }

            if (this.guiService.hasOpenSession(var5) && !this.guiService.isEditorOpen(var5)) {
               this.closeUiForPlayer(var5);
            }

            Location var6 = this.resolveReturnLocationBeforeOpen(var5, false);
            boolean var7 = this.guiService.createGuiAndStartEditor(var5, var4[1]);
            this.updateReturnLocationAfterOpen(var5, var7, false, var6);
         } else if (var4[0].equalsIgnoreCase("open")) {
            if (!this.hasCommandPermission(var5, "ultimateui.command.open", "/uui open")) {
               return true;
            }

            if (var4.length < 2) {
               this.sendUuiCommandError(var5, "Open requires a UI name. Example: /uui open [name] [hud] [autoclose] [player].");
               return true;
            }

            if (var4.length > 5) {
               this.sendUuiCommandError(var5, "Too many arguments for open. Allowed options are hud, autoclose, and one player.");
               return true;
            }

            String var15 = this.firstNonBlank(var4[1]).trim().toLowerCase(Locale.ROOT);
            if (var15.equals("editor") || var15.equals("editor_menu")) {
               this.sendUuiCommandError(var5, "UI '" + var4[1] + "' does not exist.");
               return true;
            }

            UltimateUI.OpenCommandOptions var20 = this.parseOpenCommandOptions(var5, var4);
            if (var20 == null) {
               return true;
            }

            this.openUiForPlayer(var20.targetPlayer(), var4[1], var20.hudMode(), var20.autoClose());
         } else if (var4[0].equalsIgnoreCase("edit")) {
            if (!this.hasCommandPermission(var5, "ultimateui.command.edit", "/uui edit")) {
               return true;
            }

            if (var4.length != 2) {
               this.sendUuiCommandError(var5, "Edit requires exactly one UI name. Example: /uui edit [name].");
               return true;
            }

            String var16 = this.firstNonBlank(var4[1]).trim();
            String var21 = var16.toLowerCase(Locale.ROOT);
            if (var21.equals("editor") || var21.equals("editor_menu")) {
               this.sendUuiCommandError(var5, "UI '" + var16 + "' does not exist.");
               return true;
            }

            String var8 = this.resolveExistingUiName(var16);
            if (var8.isBlank()) {
               this.sendUuiCommandError(var5, "UI '" + var16 + "' does not exist.");
               return true;
            }

            if (this.guiService.hasOpenSession(var5) && !this.guiService.isEditorOpen(var5)) {
               this.closeUiForPlayer(var5);
            }

            boolean var9 = this.guiService != null && this.guiService.isEditorOpen(var5);
            Location var10 = this.resolveReturnLocationBeforeOpen(var5, false);
            boolean var11 = this.guiService.startEditor(var5, var8);
            this.updateReturnLocationAfterOpen(var5, var11, false, var10);
            if (!var11 && var9) {
               boolean var12 = this.guiService != null && this.guiService.sendEditorPageLockedMessageIfPresent(var5, var8);
               if (!var12) {
                  this.sendUuiCommandError(var5, "Could not open UI '" + var8 + "' in editor.");
               }
            }
         } else if (var4[0].equalsIgnoreCase("delete")) {
            if (!this.hasCommandPermission(var5, "ultimateui.command.delete", "/uui delete")) {
               return true;
            }

            if (var4.length != 2) {
               this.sendUuiCommandError(var5, "Delete requires exactly one UI name. Example: /uui delete [name].");
               return true;
            }

            String var17 = this.firstNonBlank(var4[1]).trim().toLowerCase(Locale.ROOT);
            if (var17.equals("editor") || var17.equals("editor_menu")) {
               this.sendUuiCommandError(var5, "UI '" + var4[1] + "' does not exist.");
               return true;
            }

            this.guiService.deleteGui(var5, var4[1]);
         } else if (var4[0].equalsIgnoreCase("close")) {
            if (!this.hasCommandPermission(var5, "ultimateui.command.close", "/uui close")) {
               return true;
            }

            if (var4.length > 2) {
               this.sendUuiCommandError(var5, "Close accepts zero or one UI name. Examples: /uui close or /uui close [name].");
               return true;
            }

            if (var4.length == 2) {
               boolean var18 = this.closeUiForPlayer(var5, var4[1]);
               if (!var18) {
                  this.sendUuiCommandError(var5, "No open UI matched '" + var4[1] + "'.");
               }

               return true;
            }

            this.closeUiForPlayer(var5);
         } else {
            if (!var4[0].equalsIgnoreCase("menu")) {
               this.sendUuiCommandError(var5, "Unknown subcommand. Use /uui to see the command list.");
               return true;
            }

            if (!this.hasCommandPermission(var5, "ultimateui.command.edit", "/uui menu")) {
               return true;
            }

            if (var4.length > 1) {
               this.sendUuiCommandError(var5, "Menu does not accept additional arguments. Use: /uui menu.");
               return true;
            }

            if (this.guiService.hasOpenSession(var5) && !this.guiService.isEditorOpen(var5)) {
               this.closeUiForPlayer(var5);
            }

            Location var19 = this.resolveReturnLocationBeforeOpen(var5, false);
            boolean var22 = this.guiService.openEditorMenu(var5);
            this.updateReturnLocationAfterOpen(var5, var22, false, var19);
            if (!var22) {
               this.sendUuiCommandError(var5, "Could not open the editor menu.");
            }
         }

         return true;
      }
   }

   public List<String> onTabComplete(CommandSender var1, Command var2, String var3, String[] var4) {
      if (!var2.getName().equalsIgnoreCase("uui")) {
         return Collections.emptyList();
      } else if (var4.length == 1) {
         ArrayList var12 = new ArrayList();
         if (this.hasCommandPermissionSilent(var1, "ultimateui.command.open")) {
            var12.add("open");
         }

         if (this.hasCommandPermissionSilent(var1, "ultimateui.command.create")) {
            var12.add("create");
         }

         if (this.hasCommandPermissionSilent(var1, "ultimateui.command.edit")) {
            var12.add("edit");
         }

         if (this.hasCommandPermissionSilent(var1, "ultimateui.command.delete")) {
            var12.add("delete");
         }

         if (this.hasCommandPermissionSilent(var1, "ultimateui.command.close")) {
            var12.add("close");
         }

         if (this.hasCommandPermissionSilent(var1, "ultimateui.command.edit")) {
            var12.add("menu");
         }

         if (this.hasCommandPermissionSilent(var1, "ultimateui.command.reload")) {
            var12.add("reload");
         }

         return this.filterByPrefix(var12, var4[0]);
      } else if (var4.length != 2
         || (!var4[0].equalsIgnoreCase("open") || !this.hasCommandPermissionSilent(var1, "ultimateui.command.open"))
            && (!var4[0].equalsIgnoreCase("create") || !this.hasCommandPermissionSilent(var1, "ultimateui.command.create"))
            && (!var4[0].equalsIgnoreCase("edit") || !this.hasCommandPermissionSilent(var1, "ultimateui.command.edit"))
            && (!var4[0].equalsIgnoreCase("delete") || !this.hasCommandPermissionSilent(var1, "ultimateui.command.delete"))
            && (!var4[0].equalsIgnoreCase("close") || !this.hasCommandPermissionSilent(var1, "ultimateui.command.close"))) {
         if (var4[0].equalsIgnoreCase("open") && this.hasCommandPermissionSilent(var1, "ultimateui.command.open") && var4.length >= 3 && var4.length <= 5) {
            boolean var5 = false;
            boolean var6 = false;
            boolean var7 = false;

            for (int var8 = 2; var8 < var4.length - 1; var8++) {
               String var9 = this.firstNonBlank(var4[var8]).trim();
               if (!var9.isBlank()) {
                  String var10 = var9.toLowerCase(Locale.ROOT);
                  if (var10.equals("hud")) {
                     var5 = true;
                  } else if (var10.equals("autoclose")) {
                     var6 = true;
                  } else if (this.findOnlinePlayerByName(var9) != null) {
                     var7 = true;
                  }
               }
            }

            ArrayList var13 = new ArrayList();
            if (!var5) {
               var13.add("hud");
            }

            if (!var6) {
               var13.add("autoclose");
            }

            if (!var7) {
               for (Player var15 : Bukkit.getOnlinePlayers()) {
                  if (var15 != null) {
                     String var11 = this.firstNonBlank(var15.getName()).trim();
                     if (!var11.isBlank()) {
                        var13.add(var11);
                     }
                  }
               }
            }

            return this.filterByPrefix(var13, var4[var4.length - 1]);
         } else {
            return Collections.emptyList();
         }
      } else {
         return this.filterByPrefix(this.guiService.getGuiNames(), var4[1]);
      }
   }

   private UltimateUI.OpenCommandOptions parseOpenCommandOptions(Player var1, String[] var2) {
      if (var1 != null && var2 != null && var2.length >= 2) {
         boolean var3 = false;
         boolean var4 = false;
         Player var5 = var1;
         boolean var6 = false;

         for (int var7 = 2; var7 < var2.length; var7++) {
            String var8 = this.firstNonBlank(var2[var7]).trim();
            if (!var8.isBlank()) {
               String var9 = var8.toLowerCase(Locale.ROOT);
               if (var9.equals("hud")) {
                  if (var3) {
                     this.sendUuiCommandError(var1, "The option 'hud' can only be used once.");
                     return null;
                  }

                  var3 = true;
               } else if (var9.equals("autoclose")) {
                  if (var4) {
                     this.sendUuiCommandError(var1, "The option 'autoclose' can only be used once.");
                     return null;
                  }

                  var4 = true;
               } else {
                  Player var10 = this.findOnlinePlayerByName(var8);
                  if (var10 == null) {
                     this.sendUuiCommandError(var1, "Unknown open option/player '" + var8 + "'. Allowed options are hud, autoclose, and one online player.");
                     return null;
                  }

                  if (var6) {
                     this.sendUuiCommandError(var1, "Only one target player can be used with /uui open.");
                     return null;
                  }

                  var5 = var10;
                  var6 = true;
               }
            }
         }

         return new UltimateUI.OpenCommandOptions(var5, var3, var4);
      } else {
         return null;
      }
   }

   private Player findOnlinePlayerByName(String var1) {
      String var2 = this.firstNonBlank(var1).trim();
      if (var2.isBlank()) {
         return null;
      } else {
         Player var3 = Bukkit.getPlayerExact(var2);
         if (var3 != null) {
            return var3;
         } else {
            for (Player var5 : Bukkit.getOnlinePlayers()) {
               if (var5 != null && var5.getName().equalsIgnoreCase(var2)) {
                  return var5;
               }
            }

            return null;
         }
      }
   }

   private String resolveExistingUiName(String var1) {
      if (this.guiService == null) {
         return "";
      } else {
         String var2 = this.firstNonBlank(var1).trim();
         if (var2.isBlank()) {
            return "";
         } else {
            String var3 = this.normalizeUiNameToken(var2);
            if (var3.isBlank()) {
               return "";
            } else {
               for (String var5 : this.guiService.getGuiNames()) {
                  String var6 = this.normalizeUiNameToken(var5);
                  if (!var6.isBlank() && var6.equalsIgnoreCase(var3)) {
                     return var5;
                  }
               }

               return "";
            }
         }
      }
   }

   private List<String> filterByPrefix(List<String> var1, String var2) {
      ArrayList var3 = new ArrayList();

      for (Object var5_raw : var1) {
         String var5 = var5_raw != null ? var5_raw.toString() : null;
         if (var5.toLowerCase().startsWith(var2.toLowerCase())) {
            var3.add(var5);
         }
      }

      return var3;
   }

   private boolean hasCommandPermission(CommandSender var1, String var2, String var3) {
      if (var1 == null) {
         return false;
      } else if (this.hasCommandPermissionSilent(var1, var2)) {
         return true;
      } else {
         String var4 = this.firstNonBlank(var3, "this command");
         this.sendUuiCommandError(var1, "You do not have permission to use " + var4 + ".");
         return false;
      }
   }

   private boolean hasCommandPermissionSilent(CommandSender var1, String var2) {
      if (var1 == null) {
         return false;
      } else {
         String var3 = this.firstNonBlank(var2);
         if (var3.isBlank()) {
            return true;
         } else {
            return !(var1 instanceof Player) ? true : var1.hasPermission("ultimateui.admin") || var1.hasPermission(var3);
         }
      }
   }

   private void sendUuiCommandError(CommandSender var1, String var2) {
      if (var1 != null) {
         String var3 = var2 == null ? "Invalid command arguments." : var2.trim();
         if (var3.isEmpty()) {
            var3 = "Invalid command arguments.";
         }

         if (var1 instanceof Player) {
            var1.sendMessage("§c" + var3);
         } else {
            var1.sendMessage("[UltimateUI] " + var3);
         }
      }
   }

   private void sendUuiHelp(Player var1) {
      if (var1 != null) {
         var1.sendMessage("");
         var1.sendMessage(MM.deserialize(" <#fbb724><bold>UltimateUI</bold>"));
         var1.sendMessage("");
         var1.sendMessage(MM.deserialize(" <white>/ᴜᴜɪ ᴄʀᴇᴀᴛᴇ <#ffbe54>[ɴᴀᴍᴇ] <#8a989c>» <gray>Creates a new UI"));
         var1.sendMessage(MM.deserialize(" <white>/ᴜᴜɪ ᴅᴇʟᴇᴛᴇ <#ffbe54>[ɴᴀᴍᴇ] <#8a989c>» <gray>Deletes existing UI"));
         var1.sendMessage(MM.deserialize(" <white>/ᴜᴜɪ ᴏᴘᴇɴ <#ffbe54>[ɴᴀᴍᴇ] [ʜᴜᴅ] [ᴀᴜᴛᴏᴄʟᴏsᴇ] [ᴘʟᴀʏᴇʀ] <#8a989c>» <gray>Opens the created UI"));
         var1.sendMessage(MM.deserialize(" <white>/ᴜᴜɪ ᴄʟᴏsᴇ <#ffbe54>[ɴᴀᴍᴇ] <#8a989c>» <gray>Closes one open UI (or all when omitted)"));
         var1.sendMessage(MM.deserialize(" <white>/ᴜᴜɪ ᴇᴅɪᴛ <#ffbe54>[ɴᴀᴍᴇ] <#8a989c>» <gray>Edits existing UI"));
         var1.sendMessage(MM.deserialize(" <white>/ᴜᴜɪ ᴍᴇɴᴜ <#8a989c>» <gray>Opens the editor menu"));
         var1.sendMessage(MM.deserialize(" <white>/ᴜᴜɪ ʀᴇʟᴏᴀᴅ <#8a989c>» <gray>Reloads the config"));
         var1.sendMessage("");
         var1.sendMessage(MM.deserialize(" <white>ɴᴇᴇᴅ ʜᴇʟᴘ ᴡɪᴛʜ ᴛʜᴇ ᴘʟᴜɢɪɴ? ᴄʜᴇᴄᴋ ᴏᴜᴛ ᴏᴜʀ"));
         var1.sendMessage(
            MM.deserialize(
               " <white>ᴅᴏᴄᴜᴍᴇɴᴛᴀᴛɪᴏɴ ᴀᴛ <yellow><hover:show_text:'<green>Click here to open!'><click:open_url:'https://docs.xqedii.dev/'>https://docs.xqedii.dev/</click></hover><reset>"
            )
         );
         var1.sendMessage("");

         try {
            var1.playSound(var1.getLocation(), "ultimateui.extra7", 1.0F, 1.0F);
         } catch (Throwable var3) {
         }
      }
   }

   public static record OpenCommandOptions(Player targetPlayer, boolean hudMode, boolean autoClose) {
   }
}
