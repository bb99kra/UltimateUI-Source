package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import dev.xqedii.ultimateUI.bedrock.BedrockUiService;
import dev.xqedii.ultimateUI.service.bossbar.BossbarRenderService;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.GuiService;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.service.input.AnvilInputService;
import dev.xqedii.ultimateUI.service.keybind.KeybindService;
import dev.xqedii.ultimateUI.service.npc.CursorNpcService;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.PacketEvents;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.PacketEventsAPI;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
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
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.Plugin;

public class UltimateUI extends WorldUiCommands implements CommandExecutor, TabCompleter, Listener {
   public static final int Xqedii_3iujlpq = 10;
   public static final long Xqedii_1fwxu9r = 600L;
   public static final List<String> Xqedii_luybuh9;

   public void onLoad() {
      {
         this.saveDefaultConfig();
         this.mergeConfigDefaults();
         this.Xqedii_1zavo1k = new LicenseRuntimeGuard(this);
         this.Xqedii_1mi7xjn = this.Xqedii_1zavo1k.Xqedii_3w0o3x5();
         this.Xqedii_3g0zdbr = ((this.Xqedii_1mi7xjn == LicenseStatus.VALID
            ? 1
            : 0) != 0);
         if (this.Xqedii_3g0zdbr) {
            try {
               PacketEventsAPI var1 = PacketEvents.getAPI();
               if (var1 == null) {
                  PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
                  this.Xqedii_1lguiuv = true;
               }
            } catch (Throwable var2) {
               this.getLogger()
                  .severe(
                     "Failed to prepare PacketEvents API during onLoad."
                  );
               var2.printStackTrace();
            }
         }
      }
   }

   public void onEnable() {
      if (this.Xqedii_3g0zdbr && Xqedii_3omh7nb.Xqedii_2wauvzv().Xqedii_1u155gu()) {
         this.activatePlugin();
      } else {
         String var1;
         if (this.Xqedii_1mi7xjn == LicenseStatus.INVALID) {
            var1 = "<#fb5454>Inactive (Invalid)";
         } else {
            var1 = "<#fb5454>Inactive (Connection Error)";
         }

         this.printStartupBanner(var1, this.computeResourcePackBannerStatus());
         if (this.getCommand("uui") != null) {
            this.getCommand("uui").setExecutor(this);
            this.getCommand("uui").setTabCompleter(this);
         }

         if (this.Xqedii_1mi7xjn != LicenseStatus.INVALID) {
            this.scheduleStartupLicenseRetry(1);
         }
      }
   }

   public void scheduleStartupLicenseRetry(int var1) {
      if (var1 > 10) {
         this.getLogger()
            .warning(
               "[License] The license server did not answer after 10 retries. Run /uui reload once it is reachable."
            );
      } else {
         Xqedii_38o2zes.Xqedii_3rvp96a(
            this,
            () -> {
               if (this.Xqedii_2k1vujp == null && this.isEnabled()) {
                  LicenseStatus var2 = this.Xqedii_1zavo1k.Xqedii_3w0o3x5();
                  if (var2 == LicenseStatus.VALID && Xqedii_3omh7nb.Xqedii_2wauvzv().Xqedii_1u155gu()) {
                     Xqedii_38o2zes.Xqedii_34mipix(
                        this,
                        () -> {
                           if (this.Xqedii_2k1vujp == null && this.isEnabled()) {
                              this.Xqedii_1mi7xjn = var2;
                              this.Xqedii_3g0zdbr = true;
                              this.getLogger()
                                 .info(
                                    "[License] The license server answered - activating UltimateUI now."
                                 );
                              this.activatePlugin();
                           }
                        }
                     );
                  } else if (var2 == LicenseStatus.INVALID) {
                     this.Xqedii_1mi7xjn = var2;
                     this.getLogger()
                        .warning(
                           "[License] The license key was rejected - UltimateUI stays inactive."
                        );
                  } else {
                     this.getLogger().warning(Xqedii_3amq7q0h5icj6(var1));
                     this.scheduleStartupLicenseRetry(var1 + 1);
                  }
               }
            },
            600L
         );
      }
   }

   public boolean activatePlugin() {
      if (!this.isPacketEventsAvailable()) {
         this.printStartupBanner(
            "<#fb5454>Inactive (Connection Error)",
            this.computeResourcePackBannerStatus()
         );
         this.getServer().getPluginManager().disablePlugin(this);
         return false;
      } else {
         Xqedii_38o2zes.Xqedii_2rbu18a(this);
         if (!Xqedii_nd6rkr3.Xqedii_37uoku5()) {
            this.getLogger().info(Xqedii_3qaz0gljyg3p3(Bukkit.getName()));
         }

         try {
            PacketEventsAPI var1 = PacketEvents.getAPI();
            if (var1 == null) {
               PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
               this.Xqedii_1lguiuv = true;
               var1 = PacketEvents.getAPI();
            }

            if (var1 == null) {
               throw new IllegalStateException(
                  "PacketEvents API is null"
               );
            }

            if (!var1.isLoaded()) {
               var1.load();
            }

            if (!var1.isInitialized()) {
               var1.init();
            }

            this.preloadLibClasses();
            this.preloadDeepHierarchies();
            if (!this.verifyPacketEventsRuntime()) {
               this.printStartupBanner(
                  "<#00f418>Valid",
                  this.computeResourcePackBannerStatus()
               );
               this.getServer().getPluginManager().disablePlugin(this);
               return false;
            }

            if (this.Xqedii_1tuq2ol != null) {
               this.Xqedii_1tuq2ol.unregisterShaderCursorTimeGuard();
            }

            this.Xqedii_1fwpabk = new HudService(this);
            if (this.Xqedii_1h4p7ey != null) {
               this.Xqedii_1h4p7ey.disable();
            }

            this.Xqedii_1h4p7ey = new BossbarRenderService(this);
            this.Xqedii_1fwpabk.attachBossbarRenderService(this.Xqedii_1h4p7ey);
            this.Xqedii_1a31167 = new CursorNpcService(this);
            this.Xqedii_1tuq2ol = new CameraService(this, this.Xqedii_1fwpabk, this.Xqedii_1a31167);
            this.Xqedii_2k1vujp = new GuiService(this, this.Xqedii_1fwpabk, this.Xqedii_1tuq2ol);
            if (this.Xqedii_f3ftqgq != null) {
               this.Xqedii_f3ftqgq.disable();
            }

            this.Xqedii_f3ftqgq = new KeybindService(this, this.Xqedii_2k1vujp);
            this.Xqedii_2lp9ah4 = new AnvilInputService(this);
            this.Xqedii_1yufpo0 = new BedrockUiService(this);
            this.Xqedii_1yufpo0.reload();
            this.registerSelfInteractPacketGuard();
            this.registerShaderCursorUsePacketGuard();
            this.Xqedii_1a31167.enable();
            this.Xqedii_f3ftqgq.enable();
         } catch (Throwable var4) {
            this.getLogger().severe(Xqedii_2idxgs0nwv405(String.valueOf(var4)));
            var4.printStackTrace();
            this.printStartupBanner(
               "<#00f418>Valid",
               this.computeResourcePackBannerStatus()
            );
            this.getServer().getPluginManager().disablePlugin(this);
            return false;
         }

         this.saveDefaultLangFile();
         this.reloadLangConfigFile();
         this.ensureResourcePackFolder();
         this.Xqedii_391oru2 = new Xqedii_2cm61d8(this);
         this.Xqedii_1h4p7ey.bind(() -> {
            {
               return this.Xqedii_391oru2 == null ? null : this.Xqedii_391oru2.Xqedii_291hfa0().Xqedii_va02owx();
            }
         }, this.Xqedii_1fwpabk);
         this.Xqedii_1h4p7ey.enable();
         this.Xqedii_2k1vujp.ensureGuiFoldersAndExample();
         this.rebuildUiImagesIntoResourcePack();
         this.ensureGeneratedResourcePackArchive(false);
         this.syncPackToXqediiSetups();
         String var5 = this.resolveResourcePackUrl();
         this.Xqedii_3b8f4mz = this.computeResourcePackDeliverySignature(var5);
         this.getServer().getPluginManager().registerEvents(this, this);
         this.registerBetterHudReloadHook();
         this.Xqedii_2lp9ah4.enable();
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
            this.Xqedii_2k1vujp.applyStoredFixShadersOverride(var3);
            this.Xqedii_2k1vujp.applyStoredInstantCursorOverride(var3);
         }

         Xqedii_38o2zes.Xqedii_3npx6fi(this, () -> {
            {
               this.refreshUiCommands();
               if (this.Xqedii_2k1vujp != null) {
                  this.Xqedii_2k1vujp.reloadWorldUis();
               }
            }
         }, 1L);
         UltimateUIAPI.init(this);
         this.loadScripts(null);
         this.printStartupBanner(
            "<#00f418>Valid",
            this.computeResourcePackBannerStatus()
         );
         return true;
      }
   }

   public boolean handleUuiReloadCommand(CommandSender var1, String[] var2) {
      if (!this.hasCommandPermission(
         var1,
         "ultimateui.command.reload",
         "/uui reload"
      )) {
         return true;
      } else {
         String var3 = var2.length >= 2
            ? var2[1].toLowerCase(Locale.ROOT)
            : "all";
         if (var2.length > 2 || !Xqedii_luybuh9.contains(var3)) {
            this.sendUuiUsage(
               var1,
               "/uui reload",
               "[all|config|pack|pages]"
            );
            return true;
         } else if (!var3.equals("all") && this.Xqedii_2k1vujp == null) {
            String var10001 = "UltimateUI is not running yet, use {0} first!";
            Object[] var10002 = new Object[1];
            var10002[0] = "/uui reload";
            ChatColorUtil.Xqedii_2dtmver(var1, var10001, var10002);
            return true;
         } else {
            int var5 = -1;
            switch (var3.hashCode()) {
               case -1354792126:
                  if (var3.equals("config")) {
                     var5 = 0;
                  }
                  break;
               case 3432985:
                  if (var3.equals("pack")) {
                     var5 = 1;
                  }
                  break;
               case 106426308:
                  if (var3.equals("pages")) {
                     var5 = 2;
                  }
            }
            return switch (var5) {
               case 0 -> this.reloadConfigOnly(var1);
               case 1 -> this.reloadPackOnly(var1);
               case 2 -> this.reloadPagesOnly(var1);
               default -> this.reloadEverything(var1);
            };
         }
      }
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public boolean reloadConfigOnly(CommandSender var1) {
      if (!this.Xqedii_3jm11h0
         .compareAndSet(false, true)) {
         ChatColorUtil.Xqedii_2dtmver(
            var1,
            "The config is already reloading, please wait!"
         );
         return true;
      } else {
         boolean var8 = false /* VF: Semaphore variable */;

         label96: {
            try {
               try {
                  var8 = true;
                  this.Xqedii_2k1vujp.runEditorAutosave();
               } catch (Throwable var9) {
               }

               for (Player var3 : Bukkit.getOnlinePlayers()) {
                  this.cleanupPlayerRuntimeAndRestoreLocation(var3);
               }

               this.reloadConfig();
               this.mergeConfigDefaults();
               if (this.Xqedii_1tuq2ol != null) {
                  this.Xqedii_1tuq2ol.reloadConfiguration();
               }

               if (this.Xqedii_1yufpo0 != null) {
                  this.Xqedii_1yufpo0.reload();
               }

               this.reloadLangConfigFile();
               this.scheduleEditorAutosaveTask();
               this.refreshUiCommands();
               int var12 = this.Xqedii_2k1vujp.reloadWorldUis();
               ArrayList var13 = new ArrayList();
               String var17 = "World UIs reloaded: {0}";
               Object[] var18 = new Object[1];
               var18[0] = var12;
               var13.add(ChatColorUtil.Xqedii_2d5sc5r(var17, var18));
               ChatColorUtil.Xqedii_2g7xq9b(
                  var1,
                  true,
                  ChatColorUtil.Xqedii_2d5sc5r(
                     "The configuration has been reloaded!"
                  ),
                  var13
               );
               var8 = false;
               break label96;
            } catch (Throwable var10) {
               String var10001 = "Reload failed: {0}";
               Object[] var10002 = new Object[1];
               var10002[0] = String.valueOf(var10.getMessage());
               ChatColorUtil.Xqedii_2dtmver(var1, var10001, var10002);
               var8 = false;
            } finally {
               if (var8) {
                  this.Xqedii_3jm11h0.set(false);
               }
            }

            this.Xqedii_3jm11h0.set(false);
            return true;
         }

         this.Xqedii_3jm11h0.set(false);
         return true;
      }
   }

   public boolean reloadPackOnly(CommandSender var1) {
      if (!this.Xqedii_3jm11h0
         .compareAndSet(false, true)) {
         ChatColorUtil.Xqedii_2dtmver(
            var1,
            "The config is already reloading, please wait!"
         );
         return true;
      } else {
         ChatColorUtil.Xqedii_3m0zjou(
            var1,
            "Rebuilding the resource pack..."
         );
         Xqedii_38o2zes.Xqedii_u043a3q(
            this,
            () -> {
               {
                  boolean var8 = false /* VF: Semaphore variable */;

                  label75: {
                     try {
                        var8 = true;
                        if (this.Xqedii_391oru2 != null) {
                           try {
                              this.Xqedii_391oru2.Xqedii_3d0prc5();
                           } catch (Throwable var10) {
                           }
                        }

                        this.ensureResourcePackFolder();

                        try {
                           this.Xqedii_2k1vujp.ensureGuiFoldersAndExample();
                        } catch (Throwable var9) {
                        }

                        this.rebuildUiImagesIntoResourcePack();
                        this.ensureGeneratedResourcePackArchive(true);
                        this.syncPackToXqediiSetups();
                        Xqedii_1pwigla var2 = this.deployPackToExternalPlugin();
                        Xqedii_38o2zes.Xqedii_34mipix(
                           this,
                           () -> {
                              {
                                 int var3 = this.Xqedii_11hx7yt == null ? 0 : this.Xqedii_11hx7yt.size();
                                 ArrayList var4 = new ArrayList();
                                 String var10001 = "Resource pack generated with {0} images";
                                 Object[] var10002 = new Object[1];
                                 var10002[0] = var3;
                                 var4.add(ChatColorUtil.Xqedii_2d5sc5r(var10001, var10002));
                                 this.finishExternalPackDeploy(var4, var2);
                                 String var5 = this.resolveResourcePackUrl();
                                 this.Xqedii_3b8f4mz = this.computeResourcePackDeliverySignature(var5);
                                 this.resendResourcePackToOnlinePlayers(var5);
                                 var10001 = "Sent to {0} online players";
                                 var10002 = new Object[1];
                                 var10002[0] = Bukkit.getOnlinePlayers().size();
                                 var4.add(ChatColorUtil.Xqedii_2d5sc5r(var10001, var10002));
                                 ChatColorUtil.Xqedii_2g7xq9b(
                                    var1,
                                    true,
                                    ChatColorUtil.Xqedii_2d5sc5r(
                                       "The resource pack has been updated!"
                                    ),
                                    var4
                                 );
                              }
                           }
                        );
                        var8 = false;
                        break label75;
                     } catch (Throwable var11) {
                        Xqedii_38o2zes.Xqedii_34mipix(
                           this,
                           () -> {
                              {
                                 String var10001 = "Reload failed: {0}";
                                 Object[] var10002 = new Object[1];
                                 var10002[0] = String.valueOf(var11.getMessage());
                                 ChatColorUtil.Xqedii_2dtmver(var1, var10001, var10002);
                              }
                           }
                        );
                        var8 = false;
                     } finally {
                        if (var8) {
                           this.Xqedii_3jm11h0.set(false);
                        }
                     }

                     this.Xqedii_3jm11h0.set(false);
                     return;
                  }

                  this.Xqedii_3jm11h0.set(false);
               }
            }
         );
         return true;
      }
   }

   public boolean reloadPagesOnly(CommandSender var1) {
      {
         Xqedii_1zgvf22 var2 = this.loadScripts(null);
         this.refreshUiCommands();
         if (var2 == null) {
            ChatColorUtil.Xqedii_2dtmver(
               var1,
               "The scripts could not be loaded, see the console!"
            );
            return true;
         } else {
            ArrayList var3 = new ArrayList();
            String var10001 = "Scripts compiled: {0}";
            Object[] var10002 = new Object[1];
            var10002[0] = var2.scripts();
            var3.add(ChatColorUtil.Xqedii_2d5sc5r(var10001, var10002));
            var10001 = "Script events: {0}";
            var10002 = new Object[1];
            var10002[0] = var2.handlers();
            var3.add(ChatColorUtil.Xqedii_2d5sc5r(var10001, var10002));
            ChatColorUtil.Xqedii_2g7xq9b(
               var1,
               var2.errors().isEmpty(),
               ChatColorUtil.Xqedii_2d5sc5r(
                  "The pages have been reloaded!"
               ),
               var3
            );
            this.Xqedii_2nbb2r8.Xqedii_1wahnsk(var1, var2);
            return true;
         }
      }
   }

   public boolean reloadEverything(CommandSender var1) {
      if (!this.Xqedii_3jm11h0
         .compareAndSet(false, true)) {
         ChatColorUtil.Xqedii_2dtmver(
            var1,
            "The config is already reloading, please wait!"
         );
         return true;
      } else {
         ChatColorUtil.Xqedii_3m0zjou(
            var1,
            "Reloading the configuration..."
         );
         if (this.Xqedii_2k1vujp != null) {
            try {
               this.Xqedii_2k1vujp.runEditorAutosave();
            } catch (Throwable var4) {
            }
         }

         for (Player var3 : Bukkit.getOnlinePlayers()) {
            this.cleanupPlayerRuntimeAndRestoreLocation(var3);
         }

         this.reloadConfig();
         this.mergeConfigDefaults();
         if (this.Xqedii_1tuq2ol != null) {
            this.Xqedii_1tuq2ol.reloadConfiguration();
         }

         if (this.Xqedii_1yufpo0 != null) {
            this.Xqedii_1yufpo0.reload();
         }

         this.reloadLangConfigFile();
         Xqedii_38o2zes.Xqedii_u043a3q(
            this,
            () -> {
               {
                  boolean var9 = false /* VF: Semaphore variable */;

                  label100: {
                     label101: {
                        try {
                           var9 = true;
                           LicenseStatus var2 = this.Xqedii_1zavo1k.Xqedii_3w0o3x5();
                           if (var2 != LicenseStatus.VALID) {
                              String var14 = var2 == LicenseStatus.INVALID
                                 ? "Incorrect License Key"
                                 : "Connection Error";
                              Xqedii_38o2zes.Xqedii_34mipix(
                                 this,
                                 () -> {
                                    {
                                       String var10001 = "License error: {0}";
                                       Object[] var10002 = new Object[1];
                                       var10002[0] = var14;
                                       ChatColorUtil.Xqedii_2dtmver(var1, var10001, var10002);
                                    }
                                 }
                              );
                              var9 = false;
                              break label100;
                           }

                           Xqedii_38o2zes.Xqedii_34mipix(this, () -> {
                              {
                                 if (this.Xqedii_2k1vujp == null) {
                                    this.Xqedii_1mi7xjn = LicenseStatus.VALID;
                                    this.Xqedii_3g0zdbr = true;
                                    if (this.Xqedii_2lp9ah4 != null) {
                                       this.Xqedii_2lp9ah4.disable();
                                    }

                                    this.activatePlugin();
                                 }
                              }
                           });
                           if (this.Xqedii_391oru2 != null) {
                              try {
                                 this.Xqedii_391oru2.Xqedii_3d0prc5();
                              } catch (Throwable var11) {
                              }
                           }

                           this.ensureResourcePackFolder();
                           if (this.Xqedii_2k1vujp != null) {
                              try {
                                 this.Xqedii_2k1vujp.ensureGuiFoldersAndExample();
                              } catch (Throwable var10) {
                              }
                           }

                           this.rebuildUiImagesIntoResourcePack();
                           this.ensureGeneratedResourcePackArchive(true);
                           this.syncPackToXqediiSetups();
                           Xqedii_1pwigla var3x = this.deployPackToExternalPlugin();
                           Xqedii_38o2zes.Xqedii_34mipix(
                              this,
                              () -> {
                                 {
                                    int var3xx = this.Xqedii_11hx7yt == null ? 0 : this.Xqedii_11hx7yt.size();
                                    ArrayList var4x = new ArrayList();
                                    String var10001 = "Resource pack generated with {0} images";
                                    Object[] var10002 = new Object[1];
                                    var10002[0] = var3xx;
                                    var4x.add(ChatColorUtil.Xqedii_2d5sc5r(var10001, var10002));
                                    this.finishExternalPackDeploy(var4x, var3x);
                                    String var5 = this.resolveResourcePackUrl();
                                    this.Xqedii_3b8f4mz = this.computeResourcePackDeliverySignature(var5);
                                    this.resendResourcePackToOnlinePlayers(var5);
                                    this.scheduleEditorAutosaveTask();
                                    this.refreshUiCommands();
                                    if (this.Xqedii_2k1vujp != null) {
                                       int var6 = this.Xqedii_2k1vujp.reloadWorldUis();
                                       var10001 = "World UIs reloaded: {0}";
                                       var10002 = new Object[1];
                                       var10002[0] = var6;
                                       var4x.add(ChatColorUtil.Xqedii_2d5sc5r(var10001, var10002));
                                    }

                                    Xqedii_1zgvf22 var7 = this.loadScripts(null);
                                    if (var7 != null) {
                                       var10001 = "Scripts compiled: {0}";
                                       var10002 = new Object[1];
                                       var10002[0] = var7.scripts();
                                       var4x.add(ChatColorUtil.Xqedii_2d5sc5r(var10001, var10002));
                                    }

                                    var4x.add(
                                       ChatColorUtil.Xqedii_2d5sc5r(
                                          "License is valid"
                                       )
                                    );
                                    ChatColorUtil.Xqedii_2g7xq9b(
                                       var1,
                                       true,
                                       ChatColorUtil.Xqedii_2d5sc5r(
                                          "The configuration has been reloaded!"
                                       ),
                                       var4x
                                    );
                                    if (var7 != null) {
                                       this.Xqedii_2nbb2r8.Xqedii_1wahnsk(var1, var7);
                                    }
                                 }
                              }
                           );
                           var9 = false;
                           break label101;
                        } catch (Throwable var12) {
                           Xqedii_38o2zes.Xqedii_34mipix(
                              this,
                              () -> {
                                 {
                                    String var10001 = "Reload failed: {0}";
                                    Object[] var10002 = new Object[1];
                                    var10002[0] = String.valueOf(var12.getMessage());
                                    ChatColorUtil.Xqedii_2dtmver(var1, var10001, var10002);
                                 }
                              }
                           );
                           var9 = false;
                        } finally {
                           if (var9) {
                              this.Xqedii_3jm11h0.set(false);
                           }
                        }

                        this.Xqedii_3jm11h0.set(false);
                        return;
                     }

                     this.Xqedii_3jm11h0.set(false);
                     return;
                  }

                  this.Xqedii_3jm11h0.set(false);
               }
            }
         );
         return true;
      }
   }

   public void finishExternalPackDeploy(List<String> var1, Xqedii_1pwigla var2) {
      if (var2 != null) {
         String var3 = var2.target().Xqedii_lho1nuv();

         for (String var5 : var2.changedSettings()) {
            String var10001 = "Changed {0} because it conflicts with the UltimateUI pack";
            Object[] var10002 = new Object[1];
            var10002[0] = var5;
            var1.add(ChatColorUtil.Xqedii_39ytrlf(var10001, var10002));
         }

         String var9 = "Resource pack deployed to {0}";
         Object[] var11 = new Object[1];
         var11[0] = var3;
         var1.add(ChatColorUtil.Xqedii_2d5sc5r(var9, var11));
         if (!this.getServer().getPluginManager().isPluginEnabled(var3)) {
            var9 = "{0} is not enabled, reload it yourself once it runs";
            var11 = new Object[1];
            var11[0] = var3;
            var1.add(ChatColorUtil.Xqedii_39ytrlf(var9, var11));
         } else {
            long var8 = 1L;

            for (String var7 : var2.reloadCommands()) {
               Xqedii_38o2zes.Xqedii_3npx6fi(this, () -> {
                  {
                     this.getServer().dispatchCommand(this.getServer().getConsoleSender(), var7);
                  }
               }, var8);
               var8 += 60L;
            }
         }
      }
   }

   public void registerBetterHudReloadHook() {
      {
         Plugin var1 = this.getServer()
            .getPluginManager()
            .getPlugin("BetterHud");
         if (var1 != null) {
            Class var2;
            try {
               var2 = Class.forName(
                     "kr.toxicity.hud.api.bukkit.event.PluginReloadedEvent",
                     true,
                     var1.getClass().getClassLoader()
                  )
                  .asSubclass(Event.class);
            } catch (Throwable var4) {
               this.getLogger()
                  .warning(
                     "[UltimateUI] BetterHud support: BetterHud reload event not found, the merged pack is only refreshed on /uui reload."
                  );
               return;
            }

            this.getServer().getPluginManager().registerEvent(var2, new Listener() {
            }, EventPriority.MONITOR, (var2x, var3) -> {
               {
                  if (var2.isInstance(var3)) {
                     this.Xqedii_3to0a23(var3);
                  }
               }
            }, this);
         }
      }
   }

   public void Xqedii_3to0a23(Object var1) {
      {
         Xqedii_2cm61d8 var2 = this.Xqedii_391oru2;
         if (var2 != null
            && this.getConfig()
               .getBoolean(
                  "experimental.betterhud-support",
                  false
               )) {
            Map var3 = Xqedii_3s68w2g.Xqedii_yudau0p(var1);
            Xqedii_38o2zes.Xqedii_u043a3q(this, () -> {
               {
                  this.Xqedii_3gjv9vm(var2, var3);
               }
            });
         }
      }
   }

   public void Xqedii_3gjv9vm(Xqedii_2cm61d8 var1, Map<String, byte[]> var2) {
      {
         try {
            boolean var3 = var1.Xqedii_11np8af();
            String var4 = this.Xqedii_7h12qe4();
            boolean var5 = var1.Xqedii_2i2n99y(this.ensureResourcePackFolder(), var2);
            this.ensureGeneratedResourcePackArchive(
               ((!var5 ? 1 : 0) != 0)
            );
            int var6 = !var4.equals(this.Xqedii_7h12qe4())
               ? 1
               : 0;
            if (var6 == 0 && !var3) {
               return;
            }

            Xqedii_1pwigla var7 = var6 != 0 ? this.deployPackToExternalPlugin() : null;
            Xqedii_38o2zes.Xqedii_34mipix(
               this,
               () -> {
                  {
                     ArrayList var2x = new ArrayList();
                     this.finishExternalPackDeploy(var2x, var7);
                     if (!var2x.isEmpty()) {
                        ChatColorUtil.Xqedii_2g7xq9b(
                           this.getServer().getConsoleSender(),
                           true,
                           ChatColorUtil.Xqedii_2d5sc5r(
                              "The rebuilt resource pack has been deployed!"
                           ),
                           var2x
                        );
                     }

                     String var3x = this.resolveResourcePackUrl();
                     this.Xqedii_3b8f4mz = this.computeResourcePackDeliverySignature(var3x);
                     this.resendResourcePackToOnlinePlayers(var3x);
                     this.getLogger()
                        .info(
                           "[UltimateUI] BetterHud support: rebuilt the merged resource pack after a BetterHud reload."
                        );
                  }
               }
            );
         } catch (Throwable var8) {
            this.getLogger().warning(Xqedii_1vkyerbk1rqvc(var8.getMessage()));
         }
      }
   }

   public String Xqedii_7h12qe4() {
      {
         File var1 = this.resolveGeneratedPackArchiveFile();
         return var1 != null && var1.isFile()
            ? Xqedii_c3hg0t2xpkxt(var1.lastModified(), var1.length())
            : "";
      }
   }

   public void registerSkriptIntegration() {
      if (Bukkit.getPluginManager().getPlugin("Skript") != null) {
         try {
            UltimateUiSkriptBridge.Xqedii_2s3fucr(this);
         } catch (Throwable var2) {
            this.getLogger().warning(Xqedii_3tvu4ro8z2mdg(String.valueOf(var2)));
         }
      }
   }

   public Xqedii_1zgvf22 loadScripts(CommandSender var1) {
      {
         if (this.Xqedii_2nbb2r8 == null) {
            this.Xqedii_2nbb2r8 = new Xqedii_2b3rrt2(this);
         }

         Xqedii_1zgvf22 var2;
         try {
            var2 = this.Xqedii_2nbb2r8.Xqedii_7murfs0();
         } catch (Throwable var4) {
            this.getLogger().warning(Xqedii_2qq9jl4jy5l69(String.valueOf(var4)));
            return null;
         }

         this.Xqedii_2nbb2r8.Xqedii_woa4jl4(var2);
         if (var1 != null) {
            this.Xqedii_2nbb2r8.Xqedii_1wahnsk(var1, var2);
         }

         return var2;
      }
   }

   public void onDisable() {
      {
         if (this.Xqedii_2nbb2r8 != null) {
            try {
               this.Xqedii_2nbb2r8.Xqedii_oe61gxm();
            } catch (Throwable var6) {
            }
         }

         UltimateUIAPI.shutdown();

         try {
            this.unregisterAllUiCommands();
         } catch (Throwable var5) {
         }

         if (this.Xqedii_2k1vujp != null) {
            try {
               this.Xqedii_2k1vujp.shutdownWorldUis();
            } catch (Throwable var4) {
            }
         }

         if (this.Xqedii_1tuq2ol != null || this.Xqedii_2k1vujp != null || this.Xqedii_1fwpabk != null) {
            for (Player var2 : Bukkit.getOnlinePlayers()) {
               this.cleanupPlayerRuntimeAndRestoreLocation(var2);
            }
         }

         if (this.Xqedii_391oru2 != null) {
            this.Xqedii_391oru2.Xqedii_3d0prc5();
         }

         this.cancelEditorAutosaveTask();
         this.unregisterSelfInteractPacketGuard();
         this.unregisterShaderCursorUsePacketGuard();
         if (this.Xqedii_1a31167 != null) {
            this.Xqedii_1a31167.disable();
         }

         if (this.Xqedii_f3ftqgq != null) {
            this.Xqedii_f3ftqgq.disable();
         }

         if (this.Xqedii_2lp9ah4 != null) {
            this.Xqedii_2lp9ah4.disable();
         }

         if (this.Xqedii_1fwpabk != null) {
            this.Xqedii_1fwpabk.unregisterVirtualHudPassengerGuard();
         }

         if (this.Xqedii_1tuq2ol != null) {
            this.Xqedii_1tuq2ol.unregisterShaderCursorTimeGuard();
         }

         Xqedii_38o2zes.Xqedii_30bki1a();
         PacketEventsAPI var7 = PacketEvents.getAPI();
         if (var7 != null) {
            if (this.Xqedii_1lguiuv) {
               try {
                  if (!var7.isTerminated()) {
                     var7.terminate();
                  }
               } catch (Throwable var3) {
                  this.getLogger().warning(Xqedii_2vpb0tk3l0yks(var3.getMessage()));
               }
            }
         }
      }
   }

   @EventHandler
   public void onDismount(EntityDismountEvent var1) {
      {
         Entity var2 = var1.getEntity();
         if (var2.hasMetadata("hud")) {
            if (!var2.isDead() && var2.isValid()) {
               var1.setCancelled(true);
            }
         } else {
            if (var2 instanceof Player var3 && this.Xqedii_1tuq2ol.isCameraActive(var3) && !this.Xqedii_2t79mgx.contains(var3.getUniqueId())) {
               var1.setCancelled(true);
            }
         }
      }
   }

   @EventHandler
   public void onJoin(PlayerJoinEvent var1) {
      {
         for (Player var3 : Bukkit.getOnlinePlayers()) {
            if (!var3.equals(var1.getPlayer())) {
               for (Entity var5 : this.Xqedii_1fwpabk.getAllHuds(var3)) {
                  var1.getPlayer().hideEntity(this, var5);
               }
            }
         }

         this.scheduleJoinResourcePackApply(var1.getPlayer());
         if (this.Xqedii_2k1vujp != null) {
            this.Xqedii_2k1vujp.applyStoredFixShadersOverride(var1.getPlayer());
            this.Xqedii_2k1vujp.applyStoredInstantCursorOverride(var1.getPlayer());
            this.Xqedii_2k1vujp.handleWorldUiViewerJoin(var1.getPlayer());
         }

         if (this.Xqedii_2k1vujp != null) {
            Player var6 = var1.getPlayer();
            Xqedii_38o2zes.Xqedii_2sj63w1(this, var6, () -> {
               {
                  if (var6.isOnline()) {
                     this.Xqedii_2k1vujp.openOnJoinForPlayer(var6);
                  }
               }
            }, 1L);
         }
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR
   )
   public void onResourcePackStatus(PlayerResourcePackStatusEvent var1) {
      if (var1 != null) {
         Player var2 = var1.getPlayer();
         if (var2 != null) {
            if (var2.isOnline()) {
               String var3 = var1.getStatus().name();
               if ("ACCEPTED".equals(var3)) {
                  if (this.isResourcePackChatMessagesEnabled()) {
                     ChatColorUtil.send(
                        var2,
                        this.getLangMessage(
                           "resource-pack.messages.chat.loading",
                           "<#8dfc00>Loading the resource pack..."
                        )
                     );
                  }

                  if (this.isResourcePackTitleMessagesEnabled()) {
                     Component var7 = this.getLangMessage(
                        "resource-pack.messages.title.title",
                        "<#dedb24><bold>Resource pack is loading!"
                     );
                     Component var5 = this.getLangMessage(
                        "resource-pack.messages.title.subtitle",
                        "<#80fbc6>Your resource pack will be loaded soon..."
                     );
                     Times var6 = Times.times(
                        Duration.ofMillis(500L),
                        Duration.ofSeconds(10L),
                        Duration.ofMillis(500L)
                     );
                     Xqedii_nd6rkr3.Xqedii_303uu75(var2, Title.title(var7, var5, var6));
                  }

                  if (this.isResourcePackSoundEnabled()) {
                     this.playLoadingSounds(var2);
                  }
               } else if ("SUCCESSFULLY_LOADED"
                  .equals(var3)) {
                  this.forceClearTitle(var2);
                  Xqedii_38o2zes.Xqedii_2sj63w1(this, var2, () -> {
                     {
                        if (var2.isOnline()) {
                           this.Xqedii_1fwpabk.resendAllHudPositions(var2);
                        }
                     }
                  }, 3L);
                  if (this.Xqedii_2k1vujp != null) {
                     this.Xqedii_2k1vujp.handleWorldUiResourcePackLoaded(var2);
                  }

                  if (this.isResourcePackChatMessagesEnabled()) {
                     ChatColorUtil.send(
                        var2,
                        this.getLangMessage(
                           "resource-pack.messages.chat.success",
                           "<#8bf800>\u2714 <#b8fb62>The resource pack has been loaded!"
                        )
                     );
                  }
               } else if ("DECLINED".equals(var3)) {
                  this.forceClearTitle(var2);
                  if (this.getConfig()
                     .getBoolean(
                        "resource-pack.kick-on-decline",
                        false
                     )) {
                     Component var4 = this.getLangMessage(
                        "resource-pack.messages.kick.decline-message",
                        "&cYou didn't accept the Texture Pack!"
                     );
                     Xqedii_38o2zes.Xqedii_2sj63w1(this, var2, () -> {
                        {
                           if (var2.isOnline()) {
                              Xqedii_nd6rkr3.Xqedii_37yrqr8(var2, var4);
                           }
                        }
                     }, 1L);
                  }
               } else {
                  if ("FAILED_DOWNLOAD".equals(var3)
                     || "FAILED_RELOAD".equals(var3)) {
                     this.forceClearTitle(var2);
                     Xqedii_38o2zes.Xqedii_2sj63w1(
                        this,
                        var2,
                        () -> {
                           if (var2.isOnline()) {
                              this.forceClearTitle(var2);
                              if (this.isResourcePackChatMessagesEnabled()) {
                                 ChatColorUtil.send(
                                    var2,
                                    this.getLangMessage(
                                       "resource-pack.messages.chat.failed",
                                       "&cThe resource pack could not be loaded!"
                                    )
                                 );
                              }

                              if (this.getConfig()
                                 .getBoolean(
                                    "resource-pack.kick-on-fail",
                                    false
                                 )) {
                                 Component var2x = this.getLangMessage(
                                    "resource-pack.messages.kick.fail-message",
                                    "&cResource Pack download Failed!"
                                 );
                                 Xqedii_nd6rkr3.Xqedii_37yrqr8(var2, var2x);
                              }
                           }
                        },
                        5L
                     );
                  }
               }
            }
         }
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR
   )
   public void onQuit(PlayerQuitEvent var1) {
      {
         Player var2 = var1.getPlayer();
         this.cleanupPlayerRuntimeAndRestoreLocation(var2);
         if (this.Xqedii_1yufpo0 != null) {
            this.Xqedii_1yufpo0.handleQuit(var2.getUniqueId());
         }

         if (this.Xqedii_2k1vujp != null) {
            try {
               this.Xqedii_2k1vujp.forgetPlayerFixShadersOverride(var2);
               this.Xqedii_2k1vujp.forgetPlayerInstantCursorOverride(var2);
               this.Xqedii_2k1vujp.handleWorldUiViewerQuit(var2);
            } catch (Throwable var4) {
            }
         }

         if (this.Xqedii_1tuq2ol != null) {
            this.Xqedii_1tuq2ol.forgetServerClockStates(var2);
         }

         if (this.Xqedii_1h4p7ey != null) {
            this.Xqedii_1h4p7ey.forget(var2);
         }
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR,
      ignoreCancelled = true
   )
   public void onKick(PlayerKickEvent var1) {
      {
         this.cleanupPlayerRuntimeAndRestoreLocation(var1.getPlayer());
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR
   )
   public void onPlayerDeath(PlayerDeathEvent var1) {
      if (var1 != null && this.Xqedii_2k1vujp != null) {
         Player var2 = var1.getEntity();
         if (this.Xqedii_2k1vujp.isAnyPageCloseOnDeath(var2)) {
            this.Xqedii_yy2ps2m.remove(var2.getUniqueId());
            this.closeUiForPlayer(var2);
         }
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR,
      ignoreCancelled = true
   )
   public void onEntityDamage(EntityDamageEvent var1) {
      if (var1 != null && this.Xqedii_2k1vujp != null) {
         if (var1.getEntity() instanceof Player var2) {
            int var4 = this.Xqedii_1a31167 != null && this.Xqedii_1a31167.consumePendingForcedClose(var2.getUniqueId())
               ? 1
               : 0;
            if (var4 != 0 || this.Xqedii_2k1vujp.isAnyPageCloseOnDamage(var2)) {
               this.Xqedii_yy2ps2m.remove(var2.getUniqueId());
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
      {
         Action var2 = var1.getAction();
         if (var1.getHand() == EquipmentSlot.HAND) {
            Player var3 = var1.getPlayer();
            if (this.Xqedii_1tuq2ol.isCameraActive(var3)) {
               if (var2 == Action.LEFT_CLICK_AIR || var2 == Action.LEFT_CLICK_BLOCK) {
                  this.Xqedii_1tuq2ol.suppressStoneBreakAndHitSounds(var3);
                  var1.setCancelled(true);
               } else if (var2 == Action.RIGHT_CLICK_AIR || var2 == Action.RIGHT_CLICK_BLOCK) {
                  this.Xqedii_1tuq2ol.refreshClientBarrierCube(var3);
                  this.Xqedii_1tuq2ol.resampleShaderCursor(var3);
                  this.Xqedii_2k1vujp.setPlayerHeldToolAir(var3);
                  this.Xqedii_2k1vujp.clickHoveredElement(var3, GuiService.ClickType.RIGHT);
                  var1.setCancelled(true);
                  Xqedii_38o2zes.Xqedii_1avvt9e(this, var3, () -> {
                     if (var3.isOnline() && this.Xqedii_1tuq2ol.isCameraActive(var3)) {
                        this.Xqedii_2k1vujp.setPlayerHeldToolAir(var3);
                     }
                  });
                  Xqedii_38o2zes.Xqedii_2sj63w1(this, var3, () -> {
                     if (var3.isOnline() && this.Xqedii_1tuq2ol.isCameraActive(var3)) {
                        this.Xqedii_2k1vujp.setPlayerHeldToolAir(var3);
                     }
                  }, 1L);
               }
            } else {
               if (this.Xqedii_2k1vujp != null && this.Xqedii_2k1vujp.isAimingAtWorldUi(var3)) {
                  var1.setCancelled(true);
                  if (var2 == Action.RIGHT_CLICK_AIR || var2 == Action.RIGHT_CLICK_BLOCK) {
                     this.Xqedii_2k1vujp.handleWorldUiUse(var3);
                  }
               }
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
         if (!this.Xqedii_1tuq2ol.isCameraActive(var2)) {
            if (this.Xqedii_2k1vujp != null) {
               this.Xqedii_2k1vujp.handleWorldUiSwing(var2);
            }
         } else {
            this.Xqedii_1tuq2ol.refreshClientBarrierCube(var2);
            this.Xqedii_1tuq2ol.resampleShaderCursor(var2);
            this.Xqedii_2k1vujp.clickHoveredElement(var2, GuiService.ClickType.LEFT);
         }
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onBlockPlace(BlockPlaceEvent var1) {
      {
         if (this.Xqedii_1tuq2ol.isCameraActive(var1.getPlayer())) {
            var1.setCancelled(true);
         }
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onBlockBreak(BlockBreakEvent var1) {
      {
         if (this.Xqedii_1tuq2ol.isCameraActive(var1.getPlayer())) {
            this.Xqedii_1tuq2ol.suppressStoneBreakAndHitSounds(var1.getPlayer());
            var1.setCancelled(true);
         }
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onBlockDamage(BlockDamageEvent var1) {
      {
         if (this.Xqedii_1tuq2ol.isCameraActive(var1.getPlayer())) {
            this.Xqedii_1tuq2ol.suppressStoneBreakAndHitSounds(var1.getPlayer());
            var1.setCancelled(true);
         }
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onItemDrop(PlayerDropItemEvent var1) {
      {
         if (this.Xqedii_1tuq2ol.isCameraActive(var1.getPlayer())) {
            var1.setCancelled(true);
         }
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onSwapHandItems(PlayerSwapHandItemsEvent var1) {
      {
         Player var2 = var1.getPlayer();
         if (this.Xqedii_2k1vujp != null && this.Xqedii_2k1vujp.hasOpenSession(var2)) {
            if (this.Xqedii_f3ftqgq != null) {
               String var3 = this.Xqedii_f3ftqgq.resolveKeyId(var2, "F");
               if (this.Xqedii_2k1vujp.handleRuntimeKeybind(var2, var3)) {
                  var1.setCancelled(true);
                  return;
               }
            }

            boolean var4 = var2.isSneaking() ? this.Xqedii_2k1vujp.isAnyPageCloseOnShiftF(var2) : this.Xqedii_2k1vujp.isAnyPageCloseOnF(var2);
            if (var4) {
               var1.setCancelled(true);
               this.closeUiForPlayer(var2);
            }
         }
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onHotbarScroll(PlayerItemHeldEvent var1) {
      {
         Player var2 = var1.getPlayer();
         if (!this.Xqedii_1tuq2ol.isCameraActive(var2)) {
            if (this.Xqedii_2k1vujp != null) {
               int var6 = var1.getNewSlot() - var1.getPreviousSlot();
               if (var6 > 4) {
                  var6 -= 9;
               } else if (var6 < -4) {
                  var6 += 9;
               }

               if (this.Xqedii_2k1vujp.handleWorldUiScroll(var2, var6)) {
                  var1.setCancelled(true);
               }
            }
         } else {
            int var3 = var1.getNewSlot();
            if (this.Xqedii_f3ftqgq != null && var3 >= 0 && var3 <= 8) {
               String var4 = this.Xqedii_f3ftqgq.resolveKeyId(var2, String.valueOf(var3 + 1));
               if (this.Xqedii_2k1vujp.handleRuntimeKeybind(var2, var4)) {
                  var1.setCancelled(true);
                  return;
               }
            }

            int var7 = var3 - var1.getPreviousSlot();
            if (var7 > 4) {
               var7 -= 9;
            } else if (var7 < -4) {
               var7 += 9;
            }

            boolean var5 = this.Xqedii_2k1vujp.handleScroll(var2, var7);
            if (!var5) {
               this.Xqedii_2k1vujp.handleRuntimeScrollClick(var2, var7);
            }

            var1.setCancelled(true);
         }
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onMove(PlayerMoveEvent var1) {
      {
         Player var2 = var1.getPlayer();
         Location var3 = var1.getFrom();
         Location var4 = var1.getTo();
         if (var4 != null) {
            double var5 = var4.getX() - var3.getX();
            double var7 = var4.getY() - var3.getY();
            double var9 = var4.getZ() - var3.getZ();
            int var11 = !(Math.abs(var5) > Double.longBitsToDouble(4547007122018943789L))
                  && !(Math.abs(var7) > Double.longBitsToDouble(4547007122018943789L))
                  && !(Math.abs(var9) > Double.longBitsToDouble(4547007122018943789L))
               ? 0
               : 1;
            if (!this.Xqedii_1tuq2ol.isCameraActive(var2)) {
               if (var11 != 0) {
                  this.Xqedii_2k1vujp.handleRuntimeHudFollowMove(var2, var5, var7, var9);
               }
            } else {
               if (var11 != 0) {
                  this.Xqedii_2k1vujp.handleEditorMovementShortcut(var2, var5, var9, var3.getYaw());
                  var1.setTo(new Location(var3.getWorld(), var3.getX(), var3.getY(), var3.getZ(), var4.getYaw(), var4.getPitch()));
               }
            }
         }
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR,
      ignoreCancelled = true
   )
   public void onToggleSneak(PlayerToggleSneakEvent var1) {
      if (var1 != null && this.Xqedii_2k1vujp != null) {
         Player var2 = var1.getPlayer();
         if (var2 != null) {
            this.Xqedii_2k1vujp.handleRuntimeHudSneakToggle(var2, var1.isSneaking());
         }
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR
   )
   public void onRespawn(PlayerRespawnEvent var1) {
      if (var1 != null && this.Xqedii_2k1vujp != null) {
         Player var2 = var1.getPlayer();
         this.Xqedii_2k1vujp.handleWorldUiViewerReset(var2);
         Xqedii_38o2zes.Xqedii_2sj63w1(this, var2, () -> {
            if (var2.isOnline()) {
               this.Xqedii_2k1vujp.restoreRuntimeHudSession(var2);
               if (this.Xqedii_1h4p7ey != null) {
                  this.Xqedii_1h4p7ey.resend(var2);
               }
            }
         }, 2L);
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR
   )
   public void onChangedWorld(PlayerChangedWorldEvent var1) {
      if (var1 != null && this.Xqedii_2k1vujp != null) {
         Player var2 = var1.getPlayer();
         this.Xqedii_2k1vujp.handleWorldUiViewerReset(var2);
         Xqedii_38o2zes.Xqedii_2sj63w1(this, var2, () -> {
            if (var2.isOnline()) {
               this.Xqedii_2k1vujp.restoreRuntimeHudSession(var2);
               if (this.Xqedii_1h4p7ey != null) {
                  this.Xqedii_1h4p7ey.resend(var2);
               }
            }
         }, 1L);
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR,
      ignoreCancelled = true
   )
   public void onTeleport(PlayerTeleportEvent var1) {
      if (var1 != null && this.Xqedii_2k1vujp != null) {
         Player var2 = var1.getPlayer();
         UUID var3 = var2.getUniqueId();
         int var4 = this.Xqedii_1tuq2ol != null && this.Xqedii_1tuq2ol.isCameraActive(var2)
            ? 1
            : 0;
         if (var4 != 0) {
            this.Xqedii_2t79mgx.add(var3);
            Xqedii_38o2zes.Xqedii_2sj63w1(this, var2, () -> {
               {
                  this.Xqedii_2t79mgx.remove(var3);
               }
            }, 2L);
         }

         Location var5 = var1.getFrom();
         Location var6 = var1.getTo();
         if (var5 != null && var6 != null) {
            int var7 = var5.getWorld() != null && var6.getWorld() != null && var5.getWorld().getUID().equals(var6.getWorld().getUID())
               ? 0
               : 1;
            TeleportCause var8 = var1.getCause();
            int var9 = var8 != TeleportCause.ENDER_PEARL && var8 != TeleportCause.CHORUS_FRUIT
               ? 0
               : 1;
            boolean var10 = this.Xqedii_2k1vujp.isHudTextOnlySession(var2);
            double var11 = var7 != 0 ? Double.longBitsToDouble(9218868437227405311L) : var5.distanceSquared(var6);
            double var13 = var10
               ? Double.longBitsToDouble(4576918229304087675L)
               : Double.longBitsToDouble(4634204016564240384L);
            if (var9 != 0 || !(var11 < var13)) {
               long var15 = var9 != 0
                  ? 2L
                  : 1L;
               Xqedii_38o2zes.Xqedii_2sj63w1(this, var2, () -> {
                  if (var2.isOnline()) {
                     if (this.Xqedii_2k1vujp.isHudTextOnlySession(var2)) {
                        if (var7 != 0) {
                           this.Xqedii_2k1vujp.restoreRuntimeHudSession(var2);
                        } else {
                           this.Xqedii_2k1vujp.reanchorRuntimeHudSession(var2);
                        }
                     } else if (var4 != 0 && !this.isEditorTeleportCloseSuppressed(var2)) {
                        if (this.Xqedii_1tuq2ol.isCameraActive(var2)) {
                           this.Xqedii_2k1vujp.closeGui(var2);
                        }
                     }
                  }
               }, var15);
            }
         }
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST
   )
   public void onInventoryClick(InventoryClickEvent var1) {
      {
         if (var1.getWhoClicked() instanceof Player var2 && this.Xqedii_1tuq2ol.isCameraActive(var2)) {
            if (this.Xqedii_7yqoxd9(var2)) {
               var1.setCancelled(true);
               return;
            }

            this.Xqedii_2k1vujp.handleItemDesignInventoryClick(var2, var1.getCurrentItem(), var1.getCursor(), var2.getItemOnCursor());
            var1.setCancelled(true);
         }
      }
   }

   public AnvilInputService anvilInput() {
      {
         return this.Xqedii_2lp9ah4;
      }
   }

   public boolean Xqedii_7yqoxd9(Player var1) {
      {
         return ((this.Xqedii_2lp9ah4 != null && this.Xqedii_2lp9ah4.isActive(var1)
            ? 1
            : 0) != 0);
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST
   )
   public void onInventoryDrag(InventoryDragEvent var1) {
      {
         if (var1.getWhoClicked() instanceof Player var2 && this.Xqedii_1tuq2ol.isCameraActive(var2)) {
            if (this.Xqedii_7yqoxd9(var2)) {
               var1.setCancelled(true);
               return;
            }

            this.Xqedii_2k1vujp.handleItemDesignInventoryClick(var2, var1.getOldCursor(), var1.getCursor(), var2.getItemOnCursor());
            var1.setCancelled(true);
         }
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR
   )
   public void onInventoryClose(InventoryCloseEvent var1) {
      if (var1.getPlayer() instanceof Player var2) {
         if (this.Xqedii_1tuq2ol.isCameraActive(var2)) {
            Xqedii_38o2zes.Xqedii_2sj63w1(this, var2, () -> {
               if (var2.isOnline() && this.Xqedii_1tuq2ol.isCameraActive(var2)) {
                  this.Xqedii_2k1vujp.reapplyClientHotbarAir(var2);
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
      {
         Player var2 = var1.getPlayer();
         if (this.Xqedii_1tuq2ol.isCameraActive(var2)) {
            if (this.Xqedii_2k1vujp.isAwaitingSidebarInput(var2)) {
               var1.setCancelled(true);
               String var3 = var1.getMessage();
               Xqedii_38o2zes.Xqedii_1avvt9e(this, var2, () -> {
                  {
                     this.Xqedii_2k1vujp.handleSidebarChatInput(var2, var3);
                  }
               });
            }
         }
      }
   }

   @EventHandler(
      priority = EventPriority.LOWEST,
      ignoreCancelled = true
   )
   public void onUuiCommandWithoutPermission(PlayerCommandPreprocessEvent var1) {
      {
         String var2 = var1.getMessage();
         if (var2 != null && var2.startsWith("/")) {
            String var3 = var2.substring(1).trim();
            int var4 = var3.indexOf(32);
            if (var4 >= 0) {
               var3 = var3.substring(0, var4);
            }

            var3 = var3.toLowerCase(Locale.ROOT);
            if (var3.equals("uui")
               || var3.equals("ultimateui:uui")) {
               Player var5 = var1.getPlayer();
               if (!this.hasCommandPermissionSilent(
                  var5, "ultimateui.command.use"
               )) {
                  var1.setCancelled(true);
                  this.sendUuiCommandError(
                     var5,
                     "You don't have permission to use this command!",
                     new Object[0]
                  );
               }
            }
         }
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = true
   )
   public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent var1) {
      {
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

               if (!var5.equalsIgnoreCase("uui")
                  && !var5.toLowerCase(Locale.ROOT).startsWith("uui:")) {
                  if (this.Xqedii_2k1vujp.isAwaitingSidebarInput(var2)) {
                     var1.setCancelled(true);
                     Xqedii_38o2zes.Xqedii_1avvt9e(this, var2, () -> {
                        {
                           this.Xqedii_2k1vujp.handleSidebarChatInput(var2, var3);
                        }
                     });
                  } else {
                     String var7 = this.Xqedii_2k1vujp.resolveGuiByCustomCommand(var3);
                     if (var7 != null && !var7.isBlank()) {
                        var1.setCancelled(true);
                        this.openUiForPlayer(
                           var2, var7, false, false
                        );
                     }
                  }
               }
            }
         }
      }
   }

   public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
      if (var2.getName().equalsIgnoreCase("uui")
         && var4.length >= 1
         && var4[0]
            .equalsIgnoreCase("reload")) {
         return this.handleUuiReloadCommand(var1, var4);
      } else if (var2.getName().equalsIgnoreCase("uui")
         && var4.length >= 1
         && var4[0]
            .equalsIgnoreCase("bedrock")) {
         return this.handleUuiBedrockCommand(var1, var4);
      } else if (this.Xqedii_1zavo1k == null || !this.Xqedii_1zavo1k.Xqedii_16aanrd()) {
         this.sendLicenseInvalidMessage(var1);
         return true;
      } else if (this.Xqedii_2k1vujp == null) {
         this.sendUuiServicesError(var1);
         return true;
      } else if (!var2.getName().equalsIgnoreCase("uui")) {
         return true;
      } else if (!(var1 instanceof Player var5)) {
         if (var4.length >= 1
            && var4[0]
               .equalsIgnoreCase("open")) {
            return this.handleUuiConsoleOpenCommand(var1, var4);
         } else if (var4.length >= 1
            && var4[0]
               .equalsIgnoreCase("close")) {
            return this.handleUuiConsoleCloseCommand(var1, var4);
         } else if (var4.length >= 1
            && var4[0]
               .equalsIgnoreCase("shaders")) {
            return this.handleUuiShadersCommand(var1, var4);
         } else if (var4.length >= 1
            && var4[0]
               .equalsIgnoreCase("cursor")) {
            return this.handleUuiCursorCommand(var1, var4);
         } else if (var4.length >= 1
            && var4[0]
               .equalsIgnoreCase("world")) {
            return this.handleUuiWorldCommand(var1, var4);
         } else {
            this.sendUuiCommandError(
               var1,
               "This command is for players only!",
               new Object[0]
            );
            return true;
         }
      } else if (var4.length < 1) {
         if (!this.hasCommandPermissionSilent(
            var5, "ultimateui.command.use"
         )) {
            ChatColorUtil.send(
               var5,
               this.getLangMessage(
                  "gui.cant-do-this",
                  "&cYou can't do this!"
               )
            );
         } else {
            this.sendUuiHelp(var5);
         }

         return true;
      } else if (!this.hasCommandPermission(
         var5,
         "ultimateui.command.use",
         "/uui"
      )) {
         return true;
      } else if (this.Xqedii_1yufpo0 == null
         || !this.Xqedii_1yufpo0.isBedrockPlayer(var5)
         || !var4[0]
               .equalsIgnoreCase("edit")
            && !var4[0]
               .equalsIgnoreCase("create")
            && !var4[0]
               .equalsIgnoreCase("menu")) {
         if (var4[0]
            .equalsIgnoreCase("create")) {
            if (!this.hasCommandPermission(
               var5,
               "ultimateui.command.create",
               "/uui create"
            )) {
               return true;
            }

            if (var4.length != 2) {
               this.sendUuiUsage(
                  var5,
                  "/uui create",
                  "[name]"
               );
               return true;
            }

            if (this.Xqedii_2k1vujp.hasOpenSession(var5) && !this.Xqedii_2k1vujp.isEditorOpen(var5)) {
               this.closeUiForPlayer(var5);
            }

            Location var6 = this.resolveReturnLocationBeforeOpen(var5, false);
            boolean var7 = this.Xqedii_2k1vujp.createGuiAndStartEditor(var5, var4[1]);
            this.updateReturnLocationAfterOpen(var5, var7, false, var6);
         } else if (var4[0]
            .equalsIgnoreCase("open")) {
            if (!this.hasCommandPermission(
               var5,
               "ultimateui.command.open",
               "/uui open"
            )) {
               return true;
            }

            if (var4.length < 2) {
               this.sendUuiUsage(
                  var5,
                  "/uui open",
                  "[name] [hud] [autoclose] [player]"
               );
               return true;
            }

            if (var4.length > 5) {
               this.sendUuiUsage(
                  var5,
                  "/uui open",
                  "[name] [hud] [autoclose] [player]"
               );
               return true;
            }

            String[] var10001 = new String[1];
            var10001[0] = var4[1];
            String var16 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
            if (var16.equals("editor")
               || var16.equals("editor_menu")) {
               String var10002 = "UI {0} does not exist!";
               Object[] var10003 = new Object[1];
               var10003[0] = var4[1];
               this.sendUuiCommandError(var5, var10002, var10003);
               return true;
            }

            UiOpenOptions var23 = this.parseOpenCommandOptions(var5, var4);
            if (var23 == null) {
               return true;
            }

            this.openUiForPlayer(var23.targetPlayer(), var4[1], var23.hudMode(), var23.autoClose());
         } else if (var4[0]
            .equalsIgnoreCase("edit")) {
            if (!this.hasCommandPermission(
               var5,
               "ultimateui.command.edit",
               "/uui edit"
            )) {
               return true;
            }

            if (var4.length != 2) {
               this.sendUuiUsage(
                  var5,
                  "/uui edit",
                  "[name]"
               );
               return true;
            }

            String[] var27 = new String[1];
            var27[0] = var4[1];
            String var17 = this.firstNonBlank(var27).trim();
            String var24 = var17.toLowerCase(Locale.ROOT);
            if (var24.equals("editor")
               || var24.equals("editor_menu")) {
               String var32 = "UI {0} does not exist!";
               Object[] var41 = new Object[1];
               var41[0] = var17;
               this.sendUuiCommandError(var5, var32, var41);
               return true;
            }

            String var8 = this.resolveExistingUiName(var17);
            if (var8.isBlank()) {
               String var31 = "UI {0} does not exist!";
               Object[] var40 = new Object[1];
               var40[0] = var17;
               this.sendUuiCommandError(var5, var31, var40);
               return true;
            }

            int var9 = this.Xqedii_2k1vujp.hasOpenSession(var5) && !this.Xqedii_2k1vujp.isEditorOpen(var5)
               ? 1
               : 0;
            Location var10 = this.resolveReturnLocationBeforeOpen(var5, false);
            if (var9 != 0) {
               this.closeUiForPlayer(var5);
            }

            int var11 = this.Xqedii_2k1vujp != null && this.Xqedii_2k1vujp.isEditorOpen(var5)
               ? 1
               : 0;
            if (var9 != 0) {
               Xqedii_38o2zes.Xqedii_2sj63w1(
                  this,
                  var5,
                  () -> {
                     if (var5.isOnline()) {
                        this.restorePreservedReturnLocation(var5, var10);
                        boolean var5x = this.Xqedii_2k1vujp.startEditor(var5, var8);
                        this.updateReturnLocationAfterOpen(var5, var5x, false, var10);
                        if (!var5x && var11 != 0) {
                           boolean var6x = this.Xqedii_2k1vujp.sendPageLockedMessageIfPresent(var5, var8);
                           if (!var6x) {
                              String var10002x = "Could not open {0} in the editor!";
                              Object[] var10003x = new Object[1];
                              var10003x[0] = var8;
                              this.sendUuiCommandError(var5, var10002x, var10003x);
                           }
                        }
                     }
                  },
                  3L
               );
               return true;
            }

            boolean var12 = this.Xqedii_2k1vujp.startEditor(var5, var8);
            this.updateReturnLocationAfterOpen(var5, var12, false, var10);
            if (!var12 && var11 != 0) {
               int var13 = this.Xqedii_2k1vujp != null && this.Xqedii_2k1vujp.sendPageLockedMessageIfPresent(var5, var8)
                  ? 1
                  : 0;
               if (var13 == 0) {
                  String var30 = "Could not open {0} in the editor!";
                  Object[] var39 = new Object[1];
                  var39[0] = var8;
                  this.sendUuiCommandError(var5, var30, var39);
               }
            }
         } else if (var4[0]
            .equalsIgnoreCase("delete")) {
            if (!this.hasCommandPermission(
               var5,
               "ultimateui.command.delete",
               "/uui delete"
            )) {
               return true;
            }

            if (var4.length != 2) {
               this.sendUuiUsage(
                  var5,
                  "/uui delete",
                  "[name]"
               );
               return true;
            }

            String[] var28 = new String[1];
            var28[0] = var4[1];
            String var18 = this.firstNonBlank(var28).trim().toLowerCase(Locale.ROOT);
            if (var18.equals("editor")
               || var18.equals("editor_menu")) {
               String var33 = "UI {0} does not exist!";
               Object[] var42 = new Object[1];
               var42[0] = var4[1];
               this.sendUuiCommandError(var5, var33, var42);
               return true;
            }

            this.Xqedii_2k1vujp.deleteGui(var5, var4[1]);
         } else if (var4[0]
            .equalsIgnoreCase("close")) {
            if (!this.hasCommandPermission(
               var5,
               "ultimateui.command.close",
               "/uui close"
            )) {
               return true;
            }

            if (var4.length > 2) {
               this.sendUuiUsage(
                  var5,
                  "/uui close",
                  "[name]"
               );
               return true;
            }

            if (var4.length == 2) {
               boolean var19 = this.closeUiForPlayer(var5, var4[1]);
               if (!var19) {
                  String var34 = "You have no open UI named {0}!";
                  Object[] var43 = new Object[1];
                  var43[0] = var4[1];
                  this.sendUuiCommandError(var5, var34, var43);
               }

               return true;
            }

            this.closeUiForPlayer(var5);
         } else if (var4[0]
            .equalsIgnoreCase("menu")) {
            if (!this.hasCommandPermission(
               var5,
               "ultimateui.command.edit",
               "/uui menu"
            )) {
               return true;
            }

            if (var4.length > 1) {
               this.sendUuiUsage(
                  var5,
                  "/uui menu",
                  ""
               );
               return true;
            }

            if (this.Xqedii_2k1vujp.hasOpenSession(var5) && !this.Xqedii_2k1vujp.isEditorOpen(var5)) {
               this.closeUiForPlayer(var5);
            }

            Location var20 = this.resolveReturnLocationBeforeOpen(var5, false);
            boolean var25 = this.Xqedii_2k1vujp.openEditorMenu(var5);
            this.updateReturnLocationAfterOpen(var5, var25, false, var20);
            if (!var25) {
               this.sendUuiCommandError(
                  var5,
                  "Could not open the editor menu!",
                  new Object[0]
               );
            }
         } else if (var4[0]
            .equalsIgnoreCase("sensitivity")) {
            if (!this.hasCommandPermission(
               var5,
               "ultimateui.command.sensitivity",
               "/uui sensitivity"
            )) {
               return true;
            }

            if (var4.length != 2) {
               this.sendUuiUsage(
                  var5,
                  "/uui sensitivity",
                  "[0.1-5]"
               );
               return true;
            }

            double var21;
            try {
               var21 = Double.parseDouble(var4[1].trim());
            } catch (NumberFormatException var15) {
               String var35 = "Sensitivity must be between {0} and {1}!";
               Object[] var44 = new Object[2];
               var44[0] = "0.1";
               var44[1] = "5";
               this.sendUuiCommandError(var5, var35, var44);
               return true;
            }

            if (Double.isNaN(var21)
               || var21 < Double.longBitsToDouble(4591870180066957722L)
               || var21 > Double.longBitsToDouble(4617315517961601024L)) {
               String var37 = "Sensitivity must be between {0} and {1}!";
               Object[] var45 = new Object[2];
               var45[0] = "0.1";
               var45[1] = "5";
               this.sendUuiCommandError(var5, var37, var45);
               return true;
            }

            this.Xqedii_2k1vujp.setPlayerCursorSensitivity(var5, var21);
            String var29 = "Cursor sensitivity set to {0}!";
            Object[] var36 = new Object[1];
            var36[0] = var21;
            ChatColorUtil.Xqedii_2pfjv2g(var5, var29, var36);
         } else {
            if (var4[0]
               .equalsIgnoreCase("shaders")) {
               return this.handleUuiShadersCommand(var5, var4);
            }

            if (var4[0]
               .equalsIgnoreCase("cursor")) {
               return this.handleUuiCursorCommand(var5, var4);
            }

            if (var4[0]
               .equalsIgnoreCase("world")) {
               return this.handleUuiWorldCommand(var5, var4);
            }

            if (!var4[0]
               .equalsIgnoreCase("text")) {
               String var38 = "Unknown command! Use {0} to see all commands.";
               Object[] var46 = new Object[1];
               var46[0] = "/uui";
               this.sendUuiCommandError(var5, var38, var46);
               return true;
            }

            if (!this.hasCommandPermissionSilent(
               var5,
               "ultimateui.command.edit"
            )) {
               return true;
            }

            StringBuilder var22 = new StringBuilder();

            for (int var26 = 1; var26 < var4.length; var26++) {
               if (var26 > 1) {
                  var22.append((char)32);
               }

               var22.append(var4[var26]);
            }

            this.Xqedii_2k1vujp.handleEditorTextCommand(var5, var22.toString());
         }

         return true;
      } else {
         ChatColorUtil.send(
            var5,
            this.getLangMessage(
               "gui.editor-not-on-bedrock",
               "&cThe editor is not available on Bedrock!"
            )
         );
         return true;
      }
   }

   public List<String> onTabComplete(CommandSender var1, Command var2, String var3, String[] var4) {
      if (!var2.getName().equalsIgnoreCase("uui")) {
         return Collections.emptyList();
      } else if (var4.length == 1) {
         List var14 = Xqedii_2dpr802.stream().filter(var2x -> {
            {
               return this.hasCommandPermissionSilent(var1, var2x.getValue());
            }
         }).map(Entry::getKey).toList();
         return this.filterByPrefix(var14, var4[0]);
      } else if (var4.length == 2
         && var4[0]
            .equalsIgnoreCase("reload")
         && this.hasCommandPermissionSilent(
            var1,
            "ultimateui.command.reload"
         )) {
         return this.filterByPrefix(Xqedii_luybuh9, var4[1]);
      } else if (var4.length == 2
         && var4[0]
            .equalsIgnoreCase("bedrock")
         && this.hasCommandPermissionSilent(
            var1,
            "ultimateui.command.reload"
         )) {
         return this.filterByPrefix(
            List.of(
               "build",
               "pages",
               "status"
            ),
            var4[1]
         );
      } else {
         if (var4.length == 2) {
            String var5 = Xqedii_1fxcb09.get(var4[0].toLowerCase(Locale.ROOT));
            if (var5 != null && this.hasCommandPermissionSilent(var1, var5)) {
               return this.Xqedii_2k1vujp == null
                  ? Collections.emptyList()
                  : this.filterByPrefix(this.Xqedii_2k1vujp.getGuiNames(), var4[1]);
            }
         }

         if (var4.length == 2
            && var4[0]
               .equalsIgnoreCase("sensitivity")
            && this.hasCommandPermissionSilent(
               var1,
               "ultimateui.command.sensitivity"
            )) {
            return this.filterByPrefix(
               List.of(
                  "0.5",
                  "1",
                  "1.5",
                  "2",
                  "3"
               ),
               var4[1]
            );
         } else {
            ClientFeatureToggle var13 = var4[0]
                  .equalsIgnoreCase("shaders")
               ? ClientFeatureToggle.SHADERS
               : (
                  var4[0]
                        .equalsIgnoreCase("cursor")
                     ? ClientFeatureToggle.CURSOR
                     : null
               );
            if (var13 == null
               || !this.hasCommandPermissionSilent(var1, var13.permission)
               || var4.length != 2 && var4.length != 3) {
               if (var4[0]
                     .equalsIgnoreCase("world")
                  && this.hasCommandPermissionSilent(
                     var1,
                     "ultimateui.command.world"
                  )) {
                  return this.completeUuiWorldCommand(var4);
               } else if (var4[0]
                     .equalsIgnoreCase("open")
                  && this.hasCommandPermissionSilent(
                     var1,
                     "ultimateui.command.open"
                  )
                  && var4.length >= 3
                  && var4.length <= 5) {
                  int var15 = 0;
                  int var16 = 0;
                  int var17 = 0;

                  for (int var18 = 2;
                     var18 < var4.length - 1;
                     var18++
                  ) {
                     String[] var22 = new String[1];
                     var22[0] = var4[var18];
                     String var10 = this.firstNonBlank(var22).trim();
                     if (!var10.isBlank()) {
                        String var11 = var10.toLowerCase(Locale.ROOT);
                        if (var11.equals("hud")) {
                           var15 = 1;
                        } else if (var11.equals("autoclose")) {
                           var16 = 1;
                        } else if (this.findOnlinePlayerByName(var10) != null) {
                           var17 = 1;
                        }
                     }
                  }

                  ArrayList var19 = new ArrayList();
                  if (var15 == 0) {
                     var19.add("hud");
                  }

                  if (var16 == 0) {
                     var19.add("autoclose");
                  }

                  if (var17 == 0) {
                     for (Player var21 : Bukkit.getOnlinePlayers()) {
                        if (var21 != null) {
                           String[] var23 = new String[1];
                           var23[0] = var21.getName();
                           String var12 = this.firstNonBlank(var23).trim();
                           if (!var12.isBlank()) {
                              var19.add(var12);
                           }
                        }
                     }
                  }

                  return this.filterByPrefix(var19, var4[var4.length - 1]);
               } else {
                  return Collections.emptyList();
               }
            } else {
               ArrayList var6 = new ArrayList();
               if (var4.length == 2) {
                  var6.add("on");
                  var6.add("off");
                  var6.add("toggle");
                  var6.add("reset");
                  var6.add("status");
               }

               if (this.hasCommandPermissionSilent(var1, var13.othersPermission)) {
                  for (Player var8 : Bukkit.getOnlinePlayers()) {
                     if (var8 != null) {
                        String[] var10001 = new String[1];
                        var10001[0] = var8.getName();
                        String var9 = this.firstNonBlank(var10001).trim();
                        if (!var9.isBlank()) {
                           var6.add(var9);
                        }
                     }
                  }
               }

               return this.filterByPrefix(var6, var4[var4.length - 1]);
            }
         }
      }
   }

   static {
      {
         Xqedii_luybuh9 = List.of(
            "all",
            "config",
            "pack",
            "pages"
         );
      }
   }

   private static /* synthetic */ String Xqedii_3qaz0gljyg3p3(String string) {
        return "Running on " + string + " without native Adventure support, using Spigot compatibility mode.";
    }

    private static /* synthetic */ String Xqedii_2idxgs0nwv405(String string) {
        return "Failed to initialize UltimateUI services: " + string;
    }

    private static /* synthetic */ String Xqedii_1vkyerbk1rqvc(String string) {
        return "[UltimateUI] BetterHud support: rebuilding after a BetterHud reload failed: " + string;
    }

    private static /* synthetic */ String Xqedii_c3hg0t2xpkxt(long l, long l2) {
        return l + ":" + l2;
    }

    private static /* synthetic */ String Xqedii_3tvu4ro8z2mdg(String string) {
        return "Failed to register Skript integration: " + string;
    }

    private static /* synthetic */ String Xqedii_2qq9jl4jy5l69(String string) {
        return "Could not load the scripts: " + string;
    }

    private static /* synthetic */ String Xqedii_2vpb0tk3l0yks(String string) {
        return "Failed to terminate PacketEvents cleanly: " + string;
    }

    private static /* synthetic */ String Xqedii_3amq7q0h5icj6(int n) {
        return "[License] The license server is still unreachable (retry " + n + "/10), trying again in 30 seconds.";
    }
}
