package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.gui.GuiService;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.PacketEvents;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.PacketEventsAPI;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerAbstract;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketListenerPriority;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.event.PacketReceiveEvent;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketTypeCommon;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.packettype.PacketType.Play.Client;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.player.InteractionHand;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.client.WrapperPlayClientPlayerBlockPlacement;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.play.client.WrapperPlayClientUseItem;
import java.io.File;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public abstract class PluginBootstrap extends PluginState {
   public static final String Xqedii_mpg1fa3 = "dev/xqedii/ultimateUI/";
   public static final long Xqedii_1noaw2v = 67108864L;

   public void preloadDeepHierarchies() {
      {
         List var1 = this.Xqedii_30hq54i();
         if (!var1.isEmpty()) {
            ClassLoader var2 = this.getClass().getClassLoader();
            AtomicReference var3 = new AtomicReference();
            Runnable var4 = () -> {
               {
                  for (String var4x : (Iterable<String>) (var1)) {

                     try {
                        Class.forName(var4x, false, var2);
                     } catch (StackOverflowError var6x) {
                        var3.set(var6x);
                        return;
                     } catch (Throwable var7x) {
                     }
                  }
               }
            };
            Thread var5 = new Thread(
               null,
               var4,
               "UltimateUI-preload",
               67108864L
            );
            var5.setContextClassLoader(var2);
            var5.setDaemon(true);
            var5.start();

            try {
               var5.join();
            } catch (InterruptedException var7) {
               Thread.currentThread().interrupt();
               throw new IllegalStateException(
                  "Interrupted while preloading UltimateUI classes",
                  var7
               );
            }

            Throwable var6 = (Throwable)var3.get();
            if (var6 != null) {
               throw new IllegalStateException(
                  "UltimateUI class hierarchy did not fit in a 64 MB stack",
                  var6
               );
            }
         }
      }
   }

   public void Xqedii_14r1vgf(File var1, File var2, List<String> var3) {
      {
         File[] var4 = var2.listFiles();
         if (var4 != null) {
            File[] var5 = var4;
            int var6 = var4.length;

            for (int var7 = 0; var7 < var6; var7++) {
               File var8 = var5[var7];
               if (var8.isDirectory()) {
                  this.Xqedii_14r1vgf(var1, var8, var3);
               } else if (var8.getName().endsWith(".class")) {
                  String var9 = var1.toPath()
                     .relativize(var8.toPath())
                     .toString()
                     .replace(File.separatorChar, (char)47);
                  if (var9.startsWith(
                     "dev/xqedii/ultimateUI/"
                  )) {
                     var3.add(
                        var9.substring(
                              0,
                              var9.length() - ".class".length()
                           )
                           .replace((char)47, (char)46)
                     );
                  }
               }
            }
         }
      }
   }

   public List<String> Xqedii_30hq54i() {
      {
         ArrayList var1 = new ArrayList();

         try {
            CodeSource var2 = this.getClass().getProtectionDomain().getCodeSource();
            if (var2 == null || var2.getLocation() == null) {
               return var1;
            }

            File var3 = new File(var2.getLocation().toURI());
            if (var3.isDirectory()) {
               this.Xqedii_14r1vgf(var3, var3, var1);
               return var1;
            }

            try (ZipFile var4 = new ZipFile(var3)) {
               Enumeration var5 = var4.entries();

               while (var5.hasMoreElements()) {
                  String var6 = ((ZipEntry)var5.nextElement()).getName();
                  if (var6.startsWith(
                        "dev/xqedii/ultimateUI/"
                     )
                     && var6.endsWith(".class")) {
                     var1.add(
                        var6.substring(
                              0,
                              var6.length() - ".class".length()
                           )
                           .replace((char)47, (char)46)
                     );
                  }
               }
            }
         } catch (Throwable var9) {
            this.getLogger().warning(Xqedii_ejt86r99uggv(String.valueOf(var9)));
         }

         return var1;
      }
   }

   public void preloadLibClasses() {
      {
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
                  if (var8.startsWith(
                        "dev/xqedii/ultimateui/libs/"
                     )
                     && var8.endsWith(".class")) {
                     String var9 = var8.replace(
                           (char)47, (char)46
                        )
                        .replace(
                           ".class",
                           ""
                        );

                     try {
                        Class.forName(var9, true, var4);
                     } catch (Throwable var12) {
                     }
                  }
               }
            }
         } catch (Throwable var14) {
            this.getLogger().warning(Xqedii_3tja43875epd6(var14.getMessage()));
         }
      }
   }

   public void printStartupBanner(String var1, String var2) {
      {
         ConsoleCommandSender var3 = Bukkit.getConsoleSender();
         Xqedii_nd6rkr3.Xqedii_3s7zz34(var3, Component.empty());
         Xqedii_nd6rkr3.Xqedii_3s7zz34(
            var3,
            Xqedii_1df0pdx.deserialize(
               "<#ffba46>  _   _ _ _   _                 _         _   _ ___"
            )
         );
         Xqedii_nd6rkr3.Xqedii_3s7zz34(
            var3,
            Xqedii_1df0pdx.deserialize(
               "<#ffba46> | | | | | |_(_)_ __ ___   __ _| |_ ___  | | | |_ _|"
            )
         );
         Xqedii_nd6rkr3.Xqedii_3s7zz34(
            var3,
            Xqedii_1df0pdx.deserialize(
               "<#ffba46> | | | | | __| | '_ ` _ \\ / _` | __/ _ \\ | | | || |"
            )
         );
         Xqedii_nd6rkr3.Xqedii_3s7zz34(
            var3,
            Xqedii_1df0pdx.deserialize(
               "<#ffba46> | |_| | | |_| | | | | | | (_| | ||  __/ | |_| || |"
            )
         );
         Xqedii_nd6rkr3.Xqedii_3s7zz34(
            var3,
            Xqedii_1df0pdx.deserialize(
               "<#ffba46>  \\___/|_|\\__|_|_| |_| |_|\\__,_|\\__\\___|  \\___/|___|"
            )
         );
         Xqedii_nd6rkr3.Xqedii_3s7zz34(var3, Component.empty());
         Xqedii_nd6rkr3.Xqedii_3s7zz34(
            var3,
            Xqedii_1df0pdx.deserialize(
               "<#ffba46><bold>Thank you for using our products! <#de4d4d><3"
            )
         );
         Xqedii_nd6rkr3.Xqedii_3s7zz34(var3, Xqedii_1df0pdx.deserialize(Xqedii_118mpvc7w2fyj(var1)));
         Xqedii_nd6rkr3.Xqedii_3s7zz34(var3, Xqedii_1df0pdx.deserialize(Xqedii_2alypice9advc(var2)));
         Xqedii_nd6rkr3.Xqedii_3s7zz34(var3, Component.empty());
      }
   }

   public String computeResourcePackBannerStatus() {
      if (this.getConfig()
         .getBoolean(
            "resource-pack.hosting.self-host.enabled",
            false
         )) {
         return "<#00f418>Self-Host";
      } else if (this.getConfig()
         .getBoolean(
            "resource-pack.hosting.external-host.enabled",
            false
         )) {
         return "<#00f418>External Host";
      } else if (this.getConfig()
         .getBoolean(
            "resource-pack.hosting.external-pack.enabled",
            false
         )) {
         String var2 = "Unknown";
         if (this.getServer()
            .getPluginManager()
            .isPluginEnabled("ItemsAdder")) {
            var2 = "ItemsAdder";
         } else if (this.getServer()
            .getPluginManager()
            .isPluginEnabled("Oraxen")) {
            var2 = "Oraxen";
         } else if (this.getServer()
            .getPluginManager()
            .isPluginEnabled("Nexo")) {
            var2 = "Nexo";
         } else if (this.getServer()
            .getPluginManager()
            .isPluginEnabled("CraftEngine")) {
            var2 = "CraftEngine";
         }

         return Xqedii_2624cyhs0gejw(var2);
      } else {
         if (this.getConfig()
            .getBoolean(
               "resource-pack.hosting.xqedii-setups.enabled",
               false
            )) {
            File var1 = new File(
               this.getDataFolder().getParentFile(),
               "XqediiSetups"
            );
            if (var1.exists() && var1.isDirectory()) {
               return "<#00f418>XqediiSetups";
            }
         }

         return this.getConfig()
               .getBoolean(
                  "resource-pack.hosting.default-pack.enabled",
                  false
               )
            ? "<#00f418>Default Pack"
            : "<#fb5454>None (Disabled)";
      }
   }

   public void scheduleLicenseRecheck() {
      {
         Xqedii_38o2zes.Xqedii_uplb4zf(
            this, 18000L, 18000L, () -> {
               {
                  this.Xqedii_1zavo1k.Xqedii_2fgtzsf();
               }
            }
         );
      }
   }

   public void sendLicenseInvalidMessage(CommandSender var1) {
      {
         String var10001 = "Your license is not valid!";
         String var10002 = "Enter a valid license in the {0} file";
         Object[] var10003 = new Object[1];
         var10003[0] = "config.yml";
         Xqedii_3ul4hqj.Xqedii_wmipi15(
            var1,
            var10001,
            List.of(
               Xqedii_3ul4hqj.Xqedii_l0arb10(var10002, var10003),
               "Need help? <hover:show_text:'<#ff7a7a>Click to open!'><click:open_url:'https://xqedii.dev/dc'><#ffb0b0>xqedii.dev/dc</click></hover>"
            )
         );
         if (var1 instanceof Player var2) {
            try {
               var2.playSound(
                  var2.getLocation(),
                  "ultimateui.extra1",
                  Float.intBitsToFloat(1065353216),
                  Float.intBitsToFloat(1065353216)
               );
            } catch (Throwable var4) {
            }
         }
      }
   }

   public void scheduleRuntimeWatchdog() {
      {
         Xqedii_38o2zes.Xqedii_uplb4zf(
            this,
            1200L,
            1200L,
            () -> {
               {
                  if (!Xqedii_3omh7nb.Xqedii_2wauvzv().Xqedii_1u155gu()) {
                     Xqedii_38o2zes.Xqedii_34mipix(
                        this,
                        () -> {
                           {
                              this.getLogger()
                                 .severe(
                                    Xqedii_1ikuwc112pzo7(
                                       Integer.toHexString(
                                          (int)(
                                             Xqedii_3omh7nb.Xqedii_2wauvzv().Xqedii_1ost86l() & 4294967295L
                                          )
                                       )
                                    )
                                 );
                              this.getServer().getPluginManager().disablePlugin(this);
                           }
                        }
                     );
                  }
               }
            }
         );
         Xqedii_38o2zes.Xqedii_3rvp96a(this, () -> {
            {
               if (!Xqedii_3omh7nb.Xqedii_2wauvzv().Xqedii_2gxnzda() || !Xqedii_3omh7nb.Xqedii_2wauvzv().Xqedii_1u155gu()) {
                  Xqedii_38o2zes.Xqedii_34mipix(this, () -> {
                     {
                        this.getLogger().severe(Xqedii_2uqo0j7h1a54q(Integer.toHexString(System.identityHashCode(this.Xqedii_1zavo1k))));
                        this.getServer().getPluginManager().disablePlugin(this);
                     }
                  });
               }
            }
         }, 1800L);
      }
   }

   public boolean isPacketEventsAvailable() {
      {
         try {
            Class.forName(
               "dev.xqedii.ultimateui.libs.packetevents.packetevents.wrapper.PacketWrapper",
               false,
               this.getClassLoader()
            );
            return true;
         } catch (ClassNotFoundException var2) {
            return false;
         }
      }
   }

   public boolean verifyPacketEventsRuntime() {
      {
         try {
            Class.forName(
               "dev.xqedii.ultimateui.libs.packetevents.packetevents.util.mappings.SynchronizedRegistriesHandler",
               true,
               this.getClassLoader()
            );
            return true;
         } catch (Throwable var2) {
            this.getLogger()
               .severe(
                  "PacketEvents runtime verification failed (likely dependency mismatch in deployed jar)."
               );
            var2.printStackTrace();
            return false;
         }
      }
   }

   public void registerSelfInteractPacketGuard() {
      {
         PacketEventsAPI var1 = PacketEvents.getAPI();
         if (var1 != null) {
            this.unregisterSelfInteractPacketGuard();
            this.Xqedii_1pe76p2 = new PacketListenerAbstract(PacketListenerPriority.HIGHEST) {

               public void onPacketReceive(PacketReceiveEvent var1) {
                  if (var1 != null && var1.getPacketType() == Client.INTERACT_ENTITY) {
                     if (var1.getPlayer() instanceof Player var3) {
                        WrapperPlayClientInteractEntity var4 = new WrapperPlayClientInteractEntity(var1);
                        if (var4.getEntityId() == var3.getEntityId()) {
                           var1.setCancelled(true);
                        }
                     }
                  }
               }
            };
            var1.getEventManager().registerListener(this.Xqedii_1pe76p2);
         }
      }
   }

   public void suppressEditorTeleportClose(Player var1, long var2) {
      if (var1 != null) {
         UUID var4 = var1.getUniqueId();
         this.Xqedii_12cf6tq.add(var4);
         Xqedii_38o2zes.Xqedii_2sj63w1(this, var1, () -> {
            {
               this.Xqedii_12cf6tq.remove(var4);
            }
         }, Math.max(1L, var2));
      }
   }

   public boolean isEditorTeleportCloseSuppressed(Player var1) {
      {
         return ((var1 != null && this.Xqedii_12cf6tq.contains(var1.getUniqueId())
            ? 1
            : 0) != 0);
      }
   }

   public void registerShaderCursorUsePacketGuard() {
      {
         PacketEventsAPI var1 = PacketEvents.getAPI();
         if (var1 != null) {
            this.unregisterShaderCursorUsePacketGuard();
            this.Xqedii_3bx8dfb = new PacketListenerAbstract(PacketListenerPriority.HIGHEST) {

               public void onPacketReceive(PacketReceiveEvent var1) {
                  if (var1 != null) {
                     PacketTypeCommon var2 = var1.getPacketType();
                     int var3 = var2 == Client.PLAYER_BLOCK_PLACEMENT
                        ? 1
                        : 0;
                     if (var3 != 0 || var2 == Client.USE_ITEM) {
                        if (var1.getPlayer() instanceof Player var4) {
                           if (PluginBootstrap.this.Xqedii_1tuq2ol != null
                              && PluginBootstrap.this.Xqedii_2k1vujp != null
                              && PluginBootstrap.this.Xqedii_1tuq2ol.isShaderCursorActive(var4)) {
                              InteractionHand var6 = var3 != 0
                                 ? new WrapperPlayClientPlayerBlockPlacement(var1).getHand()
                                 : new WrapperPlayClientUseItem(var1).getHand();
                              if (var6 != InteractionHand.MAIN_HAND) {
                                 var1.setCancelled(true);
                              } else {
                                 var1.setCancelled(true);
                                 Xqedii_38o2zes.Xqedii_1avvt9e(PluginBootstrap.this, var4, () -> {
                                    if (var4.isOnline() && PluginBootstrap.this.Xqedii_1tuq2ol != null && PluginBootstrap.this.Xqedii_2k1vujp != null) {
                                       if (PluginBootstrap.this.Xqedii_1tuq2ol.isShaderCursorActive(var4)) {
                                          PluginBootstrap.this.Xqedii_1tuq2ol.refreshClientBarrierCube(var4);
                                          PluginBootstrap.this.Xqedii_1tuq2ol.resampleShaderCursor(var4);
                                          PluginBootstrap.this.Xqedii_2k1vujp.setPlayerHeldToolAir(var4);
                                          PluginBootstrap.this.Xqedii_2k1vujp.clickHoveredElement(var4, GuiService.ClickType.RIGHT);
                                       }
                                    }
                                 });
                              }
                           }
                        }
                     }
                  }
               }
            };
            var1.getEventManager().registerListener(this.Xqedii_3bx8dfb);
         }
      }
   }

   public void unregisterShaderCursorUsePacketGuard() {
      if (this.Xqedii_3bx8dfb != null) {
         PacketEventsAPI var1 = PacketEvents.getAPI();
         if (var1 != null) {
            try {
               var1.getEventManager().unregisterListener(this.Xqedii_3bx8dfb);
            } catch (Throwable var3) {
            }
         }

         this.Xqedii_3bx8dfb = null;
      }
   }

   public void unregisterSelfInteractPacketGuard() {
      if (this.Xqedii_1pe76p2 != null) {
         PacketEventsAPI var1 = PacketEvents.getAPI();
         if (var1 != null) {
            try {
               var1.getEventManager().unregisterListener(this.Xqedii_1pe76p2);
            } catch (Throwable var3) {
            }
         }

         this.Xqedii_1pe76p2 = null;
      }
   }

   public String resolveClassSource(Class<?> var1) {
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

   public Class<?> resolveClassIfAvailable(String var1) {
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

   public String resolveLocalPluginJarName() {
      {
         try {
            File var1 = this.getFile();
            return var1 == null ? "unknown" : var1.getName();
         } catch (Throwable var2) {
            return "unknown";
         }
      }
   }

   private static /* synthetic */ String Xqedii_ejt86r99uggv(String string) {
        return "Could not enumerate UltimateUI classes for preload: " + string;
    }

    private static /* synthetic */ String Xqedii_3tja43875epd6(String string) {
        return "[PacketEvents] Class preload failed: " + string;
    }

    private static /* synthetic */ String Xqedii_118mpvc7w2fyj(String string) {
        return "<#ffc35e> \u25cb License: " + string;
    }

    private static /* synthetic */ String Xqedii_2alypice9advc(String string) {
        return "<#ffc35e> \u25cb Resource Pack: " + string;
    }

    private static /* synthetic */ String Xqedii_2624cyhs0gejw(String string) {
        return "<#00f418>External Plugin <#c7c3c3>(" + string + ")";
    }

    private static /* synthetic */ String Xqedii_2uqo0j7h1a54q(String string) {
        return "Runtime self-test failed (code 0x" + string + ").";
    }

    private static /* synthetic */ String Xqedii_1ikuwc112pzo7(String string) {
        return "Runtime integrity drift detected (code 0x" + string + "); shutting down.";
    }
}
