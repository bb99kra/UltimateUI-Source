package dev.xqedii.ultimateUI.licensing;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.bukkit.plugin.java.JavaPlugin;

public final class LicenseService {
   private final JavaPlugin p;
   private LicenseStatus s = LicenseStatus.ERROR;
   private long t0 = 0L;
   private String cachedToken = null;
   private boolean cacheActive = false;
   private long lastWarnAt = 0L;
   private String cachedPackUrl = null;
   private boolean __decoyValid = true;
   private int __decoyHash = 0;
   private static final long C = 86400000L;
   private static final int K = 85;
   private static final byte[] D0 = new byte[]{
      61, 33, 33, 37, 38, 111, 122, 122, 49, 52, 33, 52, 123, 45, 36, 48, 49, 60, 60, 123, 49, 48, 35, 122, 61, 48, 52, 39, 33, 55, 48, 52, 33
   };
   private static final byte[] D1 = new byte[]{57, 60, 54, 48, 59, 38, 48, 123, 57, 60, 54, 48, 59, 38, 48, 120, 62, 48, 44};
   private static final byte[] D2 = new byte[]{5, 26, 6, 1};
   private static final byte[] D3 = new byte[]{0, 38, 48, 39, 120, 20, 50, 48, 59, 33};
   private static final byte[] D4 = new byte[]{13, 120, 20, 32, 33, 61, 120, 28, 49, 48, 59, 33, 60, 51, 60, 48, 39};
   private static final byte[] D5 = new byte[]{13, 6, 120, 20, 38, 38, 48, 33, 6, 44, 59, 54, 122, 100, 123, 101};
   private static final byte[] D6 = new byte[]{38, 48, 38, 38, 60, 58, 59, 10, 33, 58, 62, 48, 59};
   private static final byte[] D7 = new byte[]{57, 60, 54, 48, 59, 38, 48, 120, 61, 48, 39, 48};
   private static final byte[] D8 = new byte[]{59, 32, 57, 57};

   private static String dx(byte[] var0) {
      byte[] var1 = new byte[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = (byte)(var0[var2] & 255 ^ 85);
      }

      return new String(var1, StandardCharsets.UTF_8);
   }

   public LicenseService(JavaPlugin var1) {
      this.p = var1;
   }

   public LicenseStatus getLastStatus() {
      return this.s;
   }

   public LicenseStatus verify() {
      RuntimeGuard.h().wipe();
      this.s = LicenseStatus.ERROR;
      this.t0 = 0L;
      this.cachedToken = null;
      this.cacheActive = false;
      return this.doHeartbeat(false);
   }

   public LicenseStatus recheck() {
      return this.doHeartbeat(true);
   }

   private LicenseStatus doHeartbeat(boolean var1) {
      String var2 = dx(D1);
      String var3 = this.p.getConfig().getString(var2, "").trim();
      if (!var3.isEmpty() && var3.length() >= 4 && !var3.equalsIgnoreCase(dx(D7)) && !var3.equalsIgnoreCase(dx(D8)) && !var3.startsWith("%%__")) {
         LicenseStatus var4 = this.s;
         long var5 = this.t0;
         String var7 = this.cachedToken;

         try {
            HttpURLConnection var8 = (HttpURLConnection)new URL(dx(D0)).openConnection();
            var8.setRequestMethod(dx(D2));
            var8.setRequestProperty(dx(D3), dx(D5));
            var8.setRequestProperty(dx(D4), var3);
            var8.setConnectTimeout(5000);
            var8.setReadTimeout(5000);
            int var21 = var8.getResponseCode();
            if (var21 >= 500 && var21 <= 599) {
               throw new RuntimeException("upstream " + var21);
            } else if (var21 != 200) {
               this.s = LicenseStatus.INVALID;
               this.cacheActive = false;
               this.cachedToken = null;
               RuntimeGuard.h().wipe();
               return this.s;
            } else {
               InputStream var10 = var8.getInputStream();
               StringBuilder var22 = new StringBuilder();
               BufferedReader var12 = new BufferedReader(new InputStreamReader(var10, StandardCharsets.UTF_8));

               String var13;
               try {
                  while ((var13 = var12.readLine()) != null) {
                     var22.append(var13);
                  }
               } catch (Throwable var19) {
                  try {
                     var12.close();
                  } catch (Throwable var17) {
                     var19.addSuppressed(var17);
                  }

                  throw var19;
               }

               var12.close();
               JsonObject var23 = JsonParser.parseString(var22.toString()).getAsJsonObject();
               var13 = dx(D6);
               if (!var23.has(var13)) {
                  this.s = LicenseStatus.INVALID;
                  this.cacheActive = false;
                  this.cachedToken = null;
                  RuntimeGuard.h().wipe();
                  return this.s;
               } else {
                  String var14 = var23.get(var13).getAsString();
                  if (var14 != null && var14.length() >= 8) {
                     RuntimeGuard.h().stamp(var14);
                     if (!RuntimeGuard.h().accept()) {
                        this.s = LicenseStatus.INVALID;
                        this.cacheActive = false;
                        this.cachedToken = null;
                        RuntimeGuard.h().wipe();
                        return this.s;
                     } else {
                        this.t0 = System.currentTimeMillis();
                        this.cachedToken = var14;
                        this.cacheActive = false;
                        this.__decoyHash = var14.hashCode();
                        this.s = LicenseStatus.VALID;

                        try {
                           if (var23.has("urls") && var23.get("urls").isJsonObject()) {
                              JsonObject var15 = var23.getAsJsonObject("urls");
                              if (var15.has("url_new") && !var15.get("url_new").isJsonNull()) {
                                 String var16 = var15.get("url_new").getAsString();
                                 this.cachedPackUrl = var16 != null && !var16.isBlank() ? var16.trim() : null;
                              }
                           }
                        } catch (Throwable var18) {
                        }

                        return this.s;
                     }
                  } else {
                     this.s = LicenseStatus.INVALID;
                     this.cacheActive = false;
                     this.cachedToken = null;
                     RuntimeGuard.h().wipe();
                     return this.s;
                  }
               }
            }
         } catch (Throwable var20) {
            long var9 = System.currentTimeMillis();
            if (var1 && var4 == LicenseStatus.VALID && var7 != null && var9 - var5 < 86400000L) {
               this.s = LicenseStatus.VALID;
               this.cachedToken = var7;
               this.cacheActive = true;
               RuntimeGuard.h().stamp(var7);
               if (var9 - this.lastWarnAt > 3600000L) {
                  long var11 = (86400000L - (var9 - var5)) / 3600000L;
                  this.p
                     .getLogger()
                     .warning("License heartbeat failed (" + var20.getClass().getSimpleName() + "). Plugin will keep running on cache (~" + var11 + "h left).");
                  this.lastWarnAt = var9;
               }

               return this.s;
            } else {
               this.s = LicenseStatus.ERROR;
               this.cacheActive = false;
               this.cachedToken = null;
               if (var1) {
                  this.p.getLogger().severe("License heartbeat failed and cache expired - plugin no longer authorized.");
               } else {
                  this.p.getLogger().warning("[License] verify() threw: " + var20.getClass().getName() + ": " + var20.getMessage());
               }

               return this.s;
            }
         }
      } else {
         this.s = LicenseStatus.INVALID;
         this.cacheActive = false;
         this.cachedToken = null;
         RuntimeGuard.h().wipe();
         return this.s;
      }
   }

   public boolean isValid() {
      return this.s == LicenseStatus.VALID && RuntimeGuard.h().accept();
   }

   public String getPackUrl() {
      return this.cachedPackUrl;
   }

   public boolean isCacheActive() {
      return this.cacheActive;
   }

   public long getLastValidCheck() {
      return this.t0;
   }

   public static long cacheWindow() {
      return 86400000L;
   }
}
