package dev.xqedii.ultimateUI.licensing;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.concurrent.atomic.AtomicLong;

public final class RuntimeGuard {
   private static final RuntimeGuard I = new RuntimeGuard();
   private static final long M0 = 6861051371957195575L;
   private static final long M1 = -7046029254386353131L;
   private static final long M2 = -3750763034362895579L;
   private volatile long a0 = 0L;
   private volatile long a1 = 0L;
   private volatile long a2 = 0L;
   private volatile long a3 = 0L;
   private final AtomicLong tick = new AtomicLong(0L);
   private volatile String t = null;
   private volatile long bornAt = 0L;

   private RuntimeGuard() {
   }

   public static RuntimeGuard h() {
      return I;
   }

   static long mix(String var0, long var1) {
      if (var0 == null) {
         return 0L;
      } else {
         long var3 = 1469598103934665603L;

         for (int var5 = 0; var5 < var0.length(); var5++) {
            var3 ^= (long)var0.charAt(var5);
            var3 *= 1099511628211L;
         }

         return var3 ^ var1 ^ -3750763034362895579L;
      }
   }

   static long classDigest() {
      try {
         MessageDigest var0 = MessageDigest.getInstance("SHA-256");
         ClassLoader var1 = RuntimeGuard.class.getClassLoader();
         String[] var2 = new String[]{RuntimeGuard.class.getName(), LicenseService.class.getName()};

         for (Object var6_raw : var2) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            String var7 = var6.replace('.', '/') + ".class";
            InputStream var8 = var1.getResourceAsStream(var7);
            if (var8 == null) {
               return 0L;
            }

            byte[] var9 = new byte[4096];

            int var10;
            while ((var10 = var8.read(var9)) > 0) {
               var0.update(var9, 0, var10);
            }

            var8.close();
         }

         byte[] var12 = var0.digest();
         long var13 = 0L;

         for (int var14 = 0; var14 < 8; var14++) {
            var13 = var13 << 8 | (long)var12[var14] & 255L;
         }

         return var13;
      } catch (Throwable var11) {
         return 0L;
      }
   }

   void stamp(String var1) {
      if (var1 != null && var1.length() >= 8) {
         long var2 = mix(var1, classDigest());
         long var4 = 6861051371957195575L ^ var2;
         long var6 = var2 * -7046029254386353131L ^ Long.rotateLeft(var2, 17);
         long var8 = Long.reverseBytes(var2) ^ 6861051371957195575L;
         long var10 = var4 ^ var6 ^ var8 ^ expected();
         this.a0 = var4;
         this.a1 = var6;
         this.a2 = var8;
         this.a3 = var10;
         this.t = var1;
         this.bornAt = System.nanoTime();
      } else {
         this.wipe();
      }
   }

   void wipe() {
      this.a0 = 0L;
      this.a1 = 0L;
      this.a2 = 0L;
      this.a3 = 0L;
      this.t = null;
   }

   static long expected() {
      return -6510615554470068497L;
   }

   public boolean accept() {
      long var1 = this.a0 ^ this.a1 ^ this.a2 ^ this.a3;
      if (var1 != expected()) {
         return false;
      } else {
         String var3 = this.t;
         if (var3 != null && var3.length() >= 8) {
            long var4 = mix(var3, classDigest());
            long var6 = 6861051371957195575L ^ var4;
            if (var6 != this.a0) {
               return false;
            } else {
               this.tick.incrementAndGet();
               return true;
            }
         } else {
            return false;
         }
      }
   }

   public boolean rebind() {
      String var1 = this.t;
      if (var1 == null) {
         return false;
      } else {
         long var2 = mix(var1, classDigest());
         long var4 = 6861051371957195575L ^ var2;
         long var6 = var2 * -7046029254386353131L ^ Long.rotateLeft(var2, 17);
         long var8 = Long.reverseBytes(var2) ^ 6861051371957195575L;
         long var10 = var4 ^ var6 ^ var8 ^ expected();
         return var4 == this.a0 && var6 == this.a1 && var8 == this.a2 && var10 == this.a3;
      }
   }

   public long uptimeNanos() {
      return this.bornAt == 0L ? 0L : System.nanoTime() - this.bornAt;
   }

   public static void halt() {
      try {
         Method var0 = Class.forName("java.lang.System").getMethod("exit", int.class);
         var0.invoke(null, 0);
      } catch (Throwable var3) {
         try {
            Runtime.getRuntime().halt(0);
         } catch (Throwable var2) {
         }
      }
   }
}
