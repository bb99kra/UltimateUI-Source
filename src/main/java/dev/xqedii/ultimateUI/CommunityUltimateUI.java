package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateui.libs.packetevents.packetevents.PacketEvents;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.PacketEventsAPI;
import dev.xqedii.ultimateui.libs.packetevents.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import java.util.Locale;
import org.bukkit.Bukkit;

public final class CommunityUltimateUI extends UltimateUI {
   @Override
   public void onLoad() {
      this.saveDefaultConfig();
      this.mergeConfigDefaults();
      this.migrateLegacyResourcePackUrl();
      this.Xqedii_1zavo1k = new LicenseRuntimeGuard(this);
      this.Xqedii_1mi7xjn = LicenseStatus.VALID;
      this.Xqedii_3g0zdbr = true;

      try {
         PacketEventsAPI var1 = PacketEvents.getAPI();
         if (var1 == null) {
            PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
            this.Xqedii_1lguiuv = true;
         }
      } catch (Throwable var2) {
         this.getLogger().severe("[UltimateUI] PacketEvents bootstrap failed during onLoad.");
         var2.printStackTrace();
      }
   }

   @Override
   public void onEnable() {
      this.activatePlugin();
   }

   @Override
   public void scheduleLicenseRecheck() {
   }

   @Override
   public void scheduleRuntimeWatchdog() {
   }

   @Override
   public void printStartupBanner(String var1, String var2) {
      this.getLogger().info("UltimateUI " + this.getDescription().getVersion());
      if (!var1.toLowerCase(Locale.ROOT).contains("license")) {
         Xqedii_nd6rkr3.Xqedii_3s7zz34(Bukkit.getConsoleSender(), Xqedii_1df0pdx.deserialize(var1));
      }

      Xqedii_nd6rkr3.Xqedii_3s7zz34(Bukkit.getConsoleSender(), Xqedii_1df0pdx.deserialize(var2));
   }

   private void migrateLegacyResourcePackUrl() {
      String var1 = this.getConfig().getString("resource-pack-url", "");
      if (var1.equals("") || var1.equals("")) {
         this.getConfig().set("resource-pack-url", "");
         this.saveConfig();
      }
   }
}
