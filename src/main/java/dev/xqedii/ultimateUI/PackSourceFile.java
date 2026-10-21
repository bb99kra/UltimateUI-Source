package dev.xqedii.ultimateUI;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.runtime.ObjectMethods;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public record PackSourceFile(File source, boolean zip) {

   public String signature() {
      if (this.zip) {
         return Xqedii_toi3u78s0ui9(this.source.getAbsolutePath(), this.source.lastModified(), this.source.length());
      } else {
         long var1 = 17L;

         try (Stream<Path> var3 = Files.walk(this.source.toPath())) {
            for (Path var5 : var3.filter(var0 -> {
               {
                  return Files.isRegularFile(var0);
               }
            }).sorted().toList()) {
               File var6 = var5.toFile();
               var1 = 31L * var1 + var5.toString().hashCode();
               var1 = 31L * var1 + Long.hashCode(var6.lastModified());
               var1 = 31L * var1 + Long.hashCode(var6.length());
            }
         } catch (IOException var9) {
            var1 = System.currentTimeMillis();
         }

         return Xqedii_200x8sd075b7j(this.source.getAbsolutePath(), var1);
      }
   }

   public Map<String, byte[]> read() throws IOException {
      {
         LinkedHashMap var1 = new LinkedHashMap();
         if (this.zip) {
            try (ZipFile var14 = new ZipFile(this.source)) {
               Enumeration var15 = var14.entries();

               while (var15.hasMoreElements()) {
                  ZipEntry var16 = (ZipEntry)var15.nextElement();
                  if (!var16.isDirectory()) {
                     try (InputStream var17 = var14.getInputStream(var16)) {
                        var1.put(var16.getName(), var17.readAllBytes());
                     }
                  }
               }
            }

            return var1;
         } else {
            Path var2 = this.source.toPath();

            try (Stream<Path> var3 = Files.walk(var2)) {
               for (Path var5 : var3.filter(var0 -> {
                  {
                     return Files.isRegularFile(var0);
                  }
               }).toList()) {
                  var1.put(Xqedii_3s68w2g.Xqedii_3c4jmju(var2, var5), Files.readAllBytes(var5));
               }
            }

            return var1;
         }
      }
   }

   @Override

   public boolean zip() {
      {
         return this.zip;
      }
   }

   private static /* synthetic */ String Xqedii_toi3u78s0ui9(String string, long l, long l2) {
        return Xqedii_382spr8.Xqedii_3sd3arclbx0w9((String)"MSAlro2zXD++bMCnCN23avsgyr2dd2BG+Wqf1oYYQwCgHxzS") + string + Xqedii_382spr8.Xqedii_3sd3arclbx0w9((String)"Q2MLY1VjivAxTnTa2edBZmdUZALEZxivr/BUFFQw") + l + Xqedii_382spr8.Xqedii_3sd3arclbx0w9((String)"Q2MLY1VjivAxTnTa2edBZmdUZALEZxivr/BUFFQw") + l2;
    }

    private static /* synthetic */ String Xqedii_200x8sd075b7j(String string, long l) {
        return Xqedii_382spr8.Xqedii_3sd3arclbx0w9((String)"NXFuGvcmPl6auAbWZoxWoWXuNxLHJRD9YThPL9d2CLs9g9Ceau7RtsUE") + string + Xqedii_382spr8.Xqedii_3sd3arclbx0w9((String)"Q2MLY1VjivAxTnTa2edBZmdUZALEZxivr/BUFFQw") + l;
    }
}
