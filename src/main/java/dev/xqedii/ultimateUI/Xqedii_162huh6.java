package dev.xqedii.ultimateUI;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public abstract class Xqedii_162huh6 extends Xqedii_2olys1t {

   public Xqedii_162huh6(File var1) {
      super(var1);
   }

   public int[] Xqedii_22cnhtn(File var1) throws IOException {
      {
         long var2 = this.Xqedii_se5h7oc(var1);
         File var4 = new File(
            this.Xqedii_ef13m9a, "bedrock/generated"
         );
         if (!var4.isDirectory() && !var4.mkdirs()) {
            throw new IOException(Xqedii_33xflr4tczg8v(String.valueOf(var4)));
         } else {
            File var5 = new File(
               var4, "pack-version.properties"
            );
            Properties var6 = new Properties();
            if (var5.isFile()) {
               try (InputStream var7 = Files.newInputStream(var5.toPath())) {
                  var6.load(var7);
               } catch (IOException var17) {
               }
            }

            long var18 = Xqedii_yq23l46(
               var6.getProperty("fingerprint"),
               -9223372036854775808L
            );
            int var9 = (int)Math.max(
               0L,
               Xqedii_yq23l46(
                  var6.getProperty("build"),
                  0L
               )
            );
            if (var18 != var2) {
               var9++;
               var6.setProperty("fingerprint", Long.toString(var2));
               var6.setProperty("build", Integer.toString(var9));

               try (OutputStream var10 = Files.newOutputStream(var5.toPath())) {
                  var6.store(
                     var10,
                     "UltimateUI Bedrock pack content fingerprint and build counter"
                  );
               }
            }

            int[] var10000 = new int[3];
            var10000[0] = 1;
            var10000[1] = var9 / 100;
            var10000[2] = var9 % 100;
            return var10000;
         }
      }
   }

   public long Xqedii_se5h7oc(File var1) throws IOException {
      {
         ArrayList var2 = new ArrayList();
         this.Xqedii_1ti13cf(var1, var2);
         var2.sort(Comparator.comparing(File::getAbsolutePath, String.CASE_INSENSITIVE_ORDER));
         Path var3 = var1.toPath();
         long var4 = 1125899906842597L;
         byte[] var6 = new byte[8192];

         for (File var8 : (Iterable<File>) (var2)) {

            String var9 = var3.relativize(var8.toPath())
               .toString()
               .replace((char)92, (char)47);
            if (!"manifest.json".equalsIgnoreCase(var9)) {
               var4 = 31L * var4 + var9.hashCode();

               int var11;
               try (InputStream var10 = Files.newInputStream(var8.toPath())) {
                  while ((var11 = var10.read(var6)) != -1) {
                     for (int var12 = 0; var12 < var11; var12++) {
                        var4 = 31L * var4 + var6[var12];
                     }
                  }
               }
            }
         }

         return var4;
      }
   }

   public static long Xqedii_yq23l46(String var0, long var1) {
      if (var0 != null && !var0.isBlank()) {
         try {
            return Long.parseLong(var0.trim());
         } catch (NumberFormatException var4) {
            return var1;
         }
      } else {
         return var1;
      }
   }

   public File Xqedii_1sujeju(File var1) throws IOException {
      {
         File var2 = new File(
            this.Xqedii_ef13m9a, "bedrock/generated"
         );
         if (!var2.isDirectory() && !var2.mkdirs()) {
            throw new IOException(Xqedii_7dahvdkc9k44(String.valueOf(var2)));
         } else {
            File var3 = new File(
               var2,
               "UltimateUI-Bedrock.mcpack"
            );
            File var4 = new File(
               var2,
               "UltimateUI-Bedrock.mcpack.tmp"
            );
            Path var5 = var1.toPath();
            ArrayList var6 = new ArrayList();
            this.Xqedii_1ti13cf(var1, var6);
            var6.sort(Comparator.comparing(File::getAbsolutePath, String.CASE_INSENSITIVE_ORDER));

            try (ZipOutputStream var7 = new ZipOutputStream(Files.newOutputStream(var4.toPath()))) {
               byte[] var8 = new byte[8192];

               for (File var10 : (Iterable<File>) (var6)) {

                  String var11 = var5.relativize(var10.toPath())
                     .toString()
                     .replace((char)92, (char)47);
                  if (!var11.isBlank()) {
                     ZipEntry var12 = new ZipEntry(var11);
                     var12.setTime(var10.lastModified());
                     var7.putNextEntry(var12);

                     int var14;
                     try (InputStream var13 = Files.newInputStream(var10.toPath())) {
                        while ((var14 = var13.read(var8)) != -1) {
                           if (var14 > 0) {
                              var7.write(var8, 0, var14);
                           }
                        }
                     }

                     var7.closeEntry();
                  }
               }
            } catch (IOException var20) {
               Files.deleteIfExists(var4.toPath());
               throw var20;
            }

            Path var10000 = var4.toPath();
            Path var10001 = var3.toPath();
            CopyOption[] var10002 = new CopyOption[1];
            var10002[0] = StandardCopyOption.REPLACE_EXISTING;
            Files.move(var10000, var10001, var10002);
            return var3;
         }
      }
   }

   public void Xqedii_1ti13cf(File var1, List<File> var2) {
      {
         File[] var3 = var1.listFiles();
         if (var3 != null) {
            File[] var4 = var3;
            int var5 = var3.length;

            for (int var6 = 0; var6 < var5; var6++) {
               File var7 = var4[var6];
               if (var7 != null) {
                  if (var7.isDirectory()) {
                     this.Xqedii_1ti13cf(var7, var2);
                  } else if (var7.isFile()) {
                     var2.add(var7);
                  }
               }
            }
         }
      }
   }

   public void Xqedii_3297wz8(File var1, String var2) throws IOException {
      {
         File var3 = var1.getParentFile();
         if (var3 != null && !var3.isDirectory() && !var3.mkdirs()) {
            throw new IOException(Xqedii_27y8lb0g1mo3(String.valueOf(var3)));
         } else {
            Files.writeString(var1.toPath(), var2, StandardCharsets.UTF_8);
         }
      }
   }

   private static /* synthetic */ String Xqedii_33xflr4tczg8v(String string) {
        return "Could not create " + string;
    }

    private static /* synthetic */ String Xqedii_7dahvdkc9k44(String string) {
        return "Could not create " + string;
    }

    private static /* synthetic */ String Xqedii_27y8lb0g1mo3(String string) {
        return "Could not create " + string;
    }
}
