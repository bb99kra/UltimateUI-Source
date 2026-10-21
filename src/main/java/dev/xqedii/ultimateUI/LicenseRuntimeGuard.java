package dev.xqedii.ultimateUI;

import org.bukkit.plugin.java.JavaPlugin;

public final class LicenseRuntimeGuard {
   private final JavaPlugin plugin;

   public LicenseRuntimeGuard(JavaPlugin var1) {
      this.plugin = var1;
   }

   public LicenseStatus Xqedii_2b8q42b() {
      return LicenseStatus.VALID;
   }

   public LicenseStatus Xqedii_3w0o3x5() {
      return LicenseStatus.VALID;
   }

   public LicenseStatus Xqedii_2fgtzsf() {
      return LicenseStatus.VALID;
   }

   public LicenseStatus Xqedii_1ob6wl2(boolean var1) {
      return LicenseStatus.VALID;
   }

   public boolean Xqedii_16aanrd() {
      return true;
   }

   public String Xqedii_3c3zwyj() {
      String var1 = this.plugin.getConfig().getString("resource-pack-url", "");
      return var1 != null && !var1.isBlank() ? var1.trim() : null;
   }

   public boolean Xqedii_2ecbq7l() {
      return true;
   }

   public long Xqedii_3rf9o8n() {
      return System.currentTimeMillis();
   }

   public static long Xqedii_2rgs2gq() {
      return 0L;
   }
}
