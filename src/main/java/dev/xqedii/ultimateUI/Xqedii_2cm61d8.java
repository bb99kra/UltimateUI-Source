package dev.xqedii.ultimateUI;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import dev.xqedii.ultimateUI.service.camera.CameraBase;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public final class Xqedii_2cm61d8 {
   public static final String Xqedii_3oiikh7 = "/pack";
   public static final String Xqedii_2zhqc8n = "pack";
   public static final String Xqedii_3qexbse = "generated";
   public static final String Xqedii_3ohqjuj = "resourcepack.zip";
   public static final String Xqedii_3hu1qy2 = "assets/minecraft/textures/gui/sprites/hud/";
   public static final String[] Xqedii_311p58w;
   public static final String Xqedii_379sysr = "0.0.0.0";
   public final UltimateUI Xqedii_1shmcss;
   public final Xqedii_3s68w2g Xqedii_1keyqoh;
   public final Xqedii_21nanys Xqedii_3vyyt4t;
   public boolean Xqedii_2niafqi;
   public int Xqedii_1whojth = -1;
   public volatile HttpServer Xqedii_3uhsxl7;
   public volatile byte[] Xqedii_3e0dnrt = new byte[0];
   public String Xqedii_3pdoav9 = "";
   public int Xqedii_15ekavo = -1;
   public File Xqedii_2os4ady;
   public long Xqedii_1du0frk = -9223372036854775808L;
   public long Xqedii_3ggm0me = -9223372036854775808L;
   public long Xqedii_15ynjyo = -9223372036854775808L;
   public static final String Xqedii_zkljs90 = "assets/minecraft/shaders/include/uui_cursor.glsl";
   public static final String Xqedii_mthkv20 = "#define UUI_CURSOR_HALF_PX ";
   public static final String Xqedii_smvv0xt = "#define UUI_CURSOR_ANCHOR_PX ";

   public Xqedii_2cm61d8(UltimateUI var1) {
      this.Xqedii_1shmcss = var1;
      this.Xqedii_1keyqoh = new Xqedii_3s68w2g(var1);
      this.Xqedii_3vyyt4t = new Xqedii_21nanys(var1);
   }

   public Xqedii_21nanys Xqedii_291hfa0() {
      {
         return this.Xqedii_3vyyt4t;
      }
   }

   public synchronized boolean Xqedii_2i2n99y(File var1, Map<String, byte[]> var2) {
      {
         return this.Xqedii_1keyqoh.Xqedii_21a7gm4(var1, var2);
      }
   }

   public boolean Xqedii_11np8af() {
      {
         return this.Xqedii_1keyqoh.Xqedii_3ihj4ot();
      }
   }

   public synchronized File Xqedii_3hzgm9l(File var1) {
      {
         return this.Xqedii_ec1q5pv(var1, false);
      }
   }

   public synchronized File Xqedii_ec1q5pv(File var1, boolean var2) {
      if (var1 == null) {
         return null;
      } else {
         if (!var1.exists()) {
            var1.mkdirs();
         }

         File var3 = new File(var1, "pack");
         if (!var3.exists()) {
            var3.mkdirs();
         }

         File var4 = new File(var1, "generated");
         if (!var4.exists()) {
            var4.mkdirs();
         }

         this.Xqedii_3682ove(var1, var3);
         if (var2 || this.Xqedii_15ynjyo == -9223372036854775808L) {
            this.Xqedii_1keyqoh.Xqedii_13zsdhk(var1);
            this.Xqedii_3vyyt4t
               .Xqedii_3eqofrj(
                  var3,
                  new File(
                     this.Xqedii_1shmcss.getDataFolder(), "contents"
                  ),
                  true
               );
         }

         File var5 = this.Xqedii_19nub8y(var3, var4, var2);
         return var5 != null && var5.exists() && var5.isFile() ? var5 : null;
      }
   }

   public synchronized String Xqedii_3y0jx5p(File var1, String var2, int var3) {
      {
         String var4 = this.Xqedii_3i96c4b(var2);
         int var5 = this.Xqedii_3982wq2(var3);
         int var6 = this.Xqedii_1dvqiyl(var4);
         if (var6 > 0) {
            var5 = var6;
         }

         var4 = this.Xqedii_3ej5w8v(var4);
         String var7 = "0.0.0.0";
         File var8 = this.Xqedii_ec1q5pv(var1, false);
         if (var8 == null || !var8.exists() || !var8.isFile()) {
            this.Xqedii_3d0prc5();
            return null;
         } else if (!this.Xqedii_3mxjrnz(var8, var7, var5)) {
            return this.Xqedii_8luxcgl(var4, var5);
         } else {
            byte[] var9;
            try {
               var9 = Files.readAllBytes(var8.toPath());
            } catch (IOException var11) {
               this.Xqedii_3d0prc5();
               this.Xqedii_1shmcss.getLogger().warning(Xqedii_2nf4jtbwzcda2(var11.getMessage()));
               return null;
            }

            this.Xqedii_3d0prc5();
            int var10 = var5 == this.Xqedii_1whojth ? 1 : 0;
            if (!this.Xqedii_1pstf7z(var5, var9, (var10 != 0))) {
               this.Xqedii_1whojth = var5;
               return null;
            } else {
               this.Xqedii_1whojth = -1;
               this.Xqedii_2os4ady = var8;
               this.Xqedii_1du0frk = var8.lastModified();
               this.Xqedii_3ggm0me = var8.length();
               this.Xqedii_3pdoav9 = var7;
               this.Xqedii_15ekavo = var5;
               return this.Xqedii_8luxcgl(var4, var5);
            }
         }
      }
   }

   public boolean Xqedii_1pstf7z(int var1, byte[] var2, boolean var3) {
      {
         if (!var3 && var1 == this.Xqedii_1shmcss.getServer().getPort()) {
            this.Xqedii_1shmcss.getLogger().warning(Xqedii_126v5ll9lwg94(var1));
         }

         try {
            this.Xqedii_1zra67v("0.0.0.0", var1, var2);
            return true;
         } catch (IOException var5) {
            this.Xqedii_3d0prc5();
            if (!var3) {
               this.Xqedii_1shmcss.getLogger().warning(Xqedii_3h28hl7vcr7yo(var1, this.Xqedii_1se4hzp(var5)));
            }

            return false;
         }
      }
   }

   public String Xqedii_1se4hzp(IOException var1) {
      {
         String var2 = var1.getMessage() == null ? var1.getClass().getSimpleName() : var1.getMessage();
         return var1 instanceof BindException
               && var2.toLowerCase(Locale.ROOT).contains("in use")
            ? Xqedii_2ertqmlrf3nxa(var2)
            : var2;
      }
   }

   public synchronized void Xqedii_3d0prc5() {
      {
         HttpServer var1 = this.Xqedii_3uhsxl7;
         if (var1 != null) {
            var1.stop(0);
         }

         this.Xqedii_3uhsxl7 = null;
         this.Xqedii_3e0dnrt = new byte[0];
         this.Xqedii_3pdoav9 = "";
         this.Xqedii_15ekavo = -1;
         this.Xqedii_2os4ady = null;
         this.Xqedii_1du0frk = -9223372036854775808L;
         this.Xqedii_3ggm0me = -9223372036854775808L;
      }
   }

   public void Xqedii_1zra67v(String var1, int var2, byte[] var3) throws IOException {
      {
         InetSocketAddress var4 = new InetSocketAddress(var1, var2);
         HttpServer var5 = HttpServer.create(var4, 0);
         this.Xqedii_3e0dnrt = var3 == null ? new byte[0] : var3;
         var5.createContext("/pack", this::Xqedii_yymehol);
         var5.setExecutor(null);
         var5.start();
         this.Xqedii_3uhsxl7 = var5;
      }
   }

   public void Xqedii_yymehol(HttpExchange var1) throws IOException {
      if (var1 != null) {
         try {
            String var2 = var1.getRequestMethod();
            if ("GET".equalsIgnoreCase(var2)) {
               byte[] var3 = this.Xqedii_3e0dnrt;
               if (var3 == null) {
                  var3 = new byte[0];
               }

               var1.getResponseHeaders()
                  .set(
                     "Content-Type",
                     "application/zip"
                  );
               var1.getResponseHeaders()
                  .set(
                     "Cache-Control",
                     "no-store, no-cache, must-revalidate"
                  );
               var1.sendResponseHeaders(200, var3.length);

               try (OutputStream var4 = var1.getResponseBody()) {
                  var4.write(var3);
                  return;
               }
            }

            var1.sendResponseHeaders(405, -1L);
         } finally {
            var1.close();
         }
      }
   }

   public File Xqedii_19nub8y(File var1, File var2, boolean var3) {
      {
         List var4 = this.Xqedii_2ovtox7(var1);
         if (var4.isEmpty()) {
            return null;
         } else {
            File var5 = new File(var2, "resourcepack.zip");
            long var6 = this.Xqedii_3cfr9y5(var4, var1.toPath());
            if (this.Xqedii_1shmcss
               .getConfig()
               .getBoolean(
                  "resource-pack.remove-default-hotbar",
                  false
               )) {
               var6 ^= 7823459261849L;
            }

            var6 = 31L * var6
               + Double.hashCode(CameraBase.resolveInstantCursorSize(this.Xqedii_1shmcss.getConfig()));
            var6 = 31L * var6 + this.Xqedii_1keyqoh.Xqedii_13iri1g(var1.getParentFile());
            var6 = 31L * var6
               + (
                  this.Xqedii_3vyyt4t.Xqedii_39gjfbc()
                     ? 1111642673L
                     : 0L
               );
            if (!var3 && var6 == this.Xqedii_15ynjyo && var5.exists() && var5.isFile()) {
               return var5;
            } else if (!this.Xqedii_2us1vfr(var1, var4, var5)) {
               return null;
            } else {
               this.Xqedii_15ynjyo = var6;
               return var5;
            }
         }
      }
   }

   public void Xqedii_3682ove(File var1, File var2) {
      if (var2 != null) {
         if (this.Xqedii_2ovtox7(var2).isEmpty()) {
            File var3 = var1 == null ? null : var1.getParentFile();
            if (var3 != null && var3.exists() && var3.isDirectory()) {
               File[] var4 = var3.listFiles();
               if (var4 != null && var4.length != 0) {
                  int var5 = 0;
                  File[] var6 = var4;
                  int var7 = var4.length;

                  for (int var8 = 0; var8 < var7; var8++) {
                     File var9 = var6[var8];
                     if (this.Xqedii_2f19xds(var9)) {
                        File var10 = new File(var2, var9.getName());

                        try {
                           this.Xqedii_3uhhr8q(var9, var10);
                           var5 = 1;
                        } catch (IOException var12) {
                           this.Xqedii_1shmcss.getLogger().warning(Xqedii_1nu8wy3z4d1iu(var9.getName(), var12.getMessage()));
                        }
                     }
                  }

                  if (var5 != 0) {
                     this.Xqedii_1shmcss
                        .getLogger()
                        .info(
                           "[UltimateUI] Seeded resourcepack/pack from legacy root layout."
                        );
                  }
               }
            }
         }
      }
   }

   public boolean Xqedii_2f19xds(File var1) {
      if (var1 != null && var1.exists()) {
         String var2 = var1.getName();
         if (var2 == null || var2.isBlank()) {
            return false;
         } else if (var1.isFile()) {
            return ((!"pack.mcmeta"
                     .equalsIgnoreCase(var2)
                  && !"pack.png".equalsIgnoreCase(var2)
               ? 0
               : 1) != 0);
         } else if (!var1.isDirectory()) {
            return false;
         } else {
            return ((!"assets".equalsIgnoreCase(var2)
                  && !var2.matches(
                     "\\d+_[0-9A-Za-z_]+$"
                  )
               ? 0
               : 1) != 0);
         }
      } else {
         return false;
      }
   }

   public void Xqedii_3uhhr8q(File var1, File var2) throws IOException {
      if (var1 != null && var2 != null) {
         if (var1.isDirectory()) {
            if (!var2.exists()) {
               var2.mkdirs();
            }

            File[] var8 = var1.listFiles();
            if (var8 != null && var8.length != 0) {
               File[] var4 = var8;
               int var5 = var8.length;

               for (int var6 = 0; var6 < var5; var6++) {
                  File var7 = var4[var6];
                  if (var7 != null) {
                     this.Xqedii_3uhhr8q(var7, new File(var2, var7.getName()));
                  }
               }
            }
         } else {
            File var3 = var2.getParentFile();
            if (var3 != null && !var3.exists()) {
               var3.mkdirs();
            }

            Path var10000 = var1.toPath();
            Path var10001 = var2.toPath();
            CopyOption[] var10002 = new CopyOption[1];
            var10002[0] = StandardCopyOption.REPLACE_EXISTING;
            Files.copy(var10000, var10001, var10002);
         }
      }
   }

   public List<File> Xqedii_2ovtox7(File var1) {
      if (var1 != null && var1.exists() && var1.isDirectory()) {
         ArrayList var2 = new ArrayList();
         this.Xqedii_198o9eo(var1, var2);
         var2.sort(Comparator.comparing(File::getAbsolutePath, String.CASE_INSENSITIVE_ORDER));
         return var2;
      } else {
         return Collections.emptyList();
      }
   }

   public void Xqedii_198o9eo(File var1, List<File> var2) {
      if (var1 != null && var2 != null) {
         File[] var3 = var1.listFiles();
         if (var3 != null && var3.length != 0) {
            File[] var4 = var3;
            int var5 = var3.length;

            for (int var6 = 0; var6 < var5; var6++) {
               File var7 = var4[var6];
               if (var7 != null) {
                  if (var7.isDirectory()) {
                     this.Xqedii_198o9eo(var7, var2);
                  } else if (var7.isFile()) {
                     var2.add(var7);
                  }
               }
            }
         }
      }
   }

   public long Xqedii_3cfr9y5(List<File> var1, Path var2) {
      {
         long var3 = 1125899906842597L;
         if (var1 != null && !var1.isEmpty() && var2 != null) {
            for (File var6 : var1) {
               if (var6 != null) {
                  String var7;
                  try {
                     var7 = var2.relativize(var6.toPath())
                        .toString()
                        .replace((char)92, (char)47)
                        .toLowerCase(Locale.ROOT);
                  } catch (Exception var9) {
                     var7 = var6.getName().toLowerCase(Locale.ROOT);
                  }

                  var3 = 31L * var3 + var7.hashCode();
                  var3 = 31L * var3 + Long.hashCode(var6.lastModified());
                  var3 = 31L * var3 + Long.hashCode(var6.length());
               }
            }

            return var3;
         } else {
            return var3;
         }
      }
   }

   public static byte[] Xqedii_83krgtf(String var0, File var1) {
      {
         String var2 = var0.toLowerCase(Locale.ROOT);
         if (!var2.endsWith(".vsh")
            && !var2.endsWith(".fsh")) {
            return null;
         } else {
            try {
               return Files.readAllBytes(var1.toPath());
            } catch (IOException var4) {
               return null;
            }
         }
      }
   }

   public byte[] Xqedii_3b1ykur(File var1) {
      {
         List var2;
         try {
            var2 = Files.readAllLines(var1.toPath(), StandardCharsets.UTF_8);
         } catch (IOException var13) {
            return null;
         }

         double var3 = CameraBase.resolveInstantCursorSize(this.Xqedii_1shmcss.getConfig());
         String var5 = Xqedii_34v9ng3qrjwa8(Xqedii_2i3563w(CameraBase.instantCursorHalfPixels(var3)));
         String var6 = Xqedii_1wlh58c3nfcnt(Xqedii_2i3563w(CameraBase.instantCursorAnchorPixels(var3)));
         int var7 = 0;
         int var8 = 0;
         StringBuilder var9 = new StringBuilder();

         for (String var11 : (Iterable<String>) (var2)) {

            String var12 = var11;
            if (var7 == 0
               && var11.startsWith(
                  "#define UUI_CURSOR_HALF_PX "
               )) {
               var12 = var5;
               var7 = 1;
            } else if (var8 == 0
               && var11.startsWith(
                  "#define UUI_CURSOR_ANCHOR_PX "
               )) {
               var12 = var6;
               var8 = 1;
            }

            var9.append(var12).append("\n");
         }

         return var7 != 0 && var8 != 0 ? var9.toString().getBytes(StandardCharsets.UTF_8) : null;
      }
   }

   public static String Xqedii_2i3563w(double var0) {
      {
         Locale var10000 = Locale.ROOT;
         String var10001 = "%.4f";
         Object[] var10002 = new Object[1];
         var10002[0] = var0;
         return String.format(var10000, var10001, var10002);
      }
   }

   public boolean Xqedii_2us1vfr(File var1, List<File> var2, File var3) {
      if (var1 != null && var2 != null && !var2.isEmpty() && var3 != null) {
         File var4 = var3.getParentFile();
         if (var4 != null && !var4.exists()) {
            var4.mkdirs();
         }

         File var5 = new File(var4 == null ? var3.getParent() : var4.getAbsolutePath(), Xqedii_msqw27el950g(var3.getName()));
         Path var6 = var1.toPath();
         boolean var7 = this.Xqedii_1shmcss
            .getConfig()
            .getBoolean(
               "resource-pack.compress-images",
               false
            );
         boolean var8 = this.Xqedii_1shmcss
            .getConfig()
            .getBoolean(
               "resource-pack.remove-default-hotbar",
               false
            );
         this.Xqedii_3vyyt4t
            .Xqedii_3pw62lv(
               var1,
               new File(
                  this.Xqedii_1shmcss.getDataFolder(), "contents"
               )
            );
         Xqedii_2om8ulg var9 = this.Xqedii_1keyqoh.Xqedii_cl3zryp(var1.getParentFile(), var1);
         HashSet var10 = var9 == null ? null : new HashSet();

         try (ZipOutputStream var11 = new ZipOutputStream(Files.newOutputStream(var5.toPath()))) {
            byte[] var12 = new byte[8192];
            HashSet var13 = var8 ? new HashSet() : null;
            HashSet var14 = new HashSet();
            HashSet var15 = var8 ? new HashSet() : null;

            for (File var17 : var2) {
               if (var17 != null && var17.exists() && var17.isFile()) {
                  String var18 = var6.relativize(var17.toPath())
                     .toString()
                     .replace((char)92, (char)47);
                  if (!var18.isBlank()) {
                     var14.add(var18.toLowerCase(Locale.ROOT));
                     if (var10 != null) {
                        var10.add(var18.toLowerCase(Locale.ROOT));
                     }

                     if (var13 != null) {
                        String var19 = var18.toLowerCase(Locale.ROOT);
                        var13.add(var19);
                        int var20 = var19.indexOf(
                           "assets/minecraft/textures/gui/sprites/hud/"
                        );
                        if (var20 >= 0) {
                           var15.add(var19.substring(var20));
                        }
                     }

                     ZipEntry var37 = new ZipEntry(var18);
                     var37.setTime(var17.lastModified());
                     byte[] var40 = var9 == null ? null : var9.Xqedii_2r3ltno(var18, var17);
                     if (this.Xqedii_3vyyt4t.Xqedii_7yidd6b()) {
                        byte[] var21 = var40 != null ? var40 : Xqedii_83krgtf(var18, var17);
                        byte[] var22 = this.Xqedii_3vyyt4t.Xqedii_ruydpmf(var18, var21);
                        if (var22 != null) {
                           var40 = var22;
                        }
                     }

                     if (var40 != null) {
                        var11.putNextEntry(var37);
                        var11.write(var40);
                        var11.closeEntry();
                     } else {
                        if (var18.toLowerCase(Locale.ROOT)
                           .endsWith(
                              "assets/minecraft/shaders/include/uui_cursor.glsl"
                           )) {
                           byte[] var42 = this.Xqedii_3b1ykur(var17);
                           if (var42 != null) {
                              var11.putNextEntry(var37);
                              var11.write(var42);
                              var11.closeEntry();
                              continue;
                           }
                        }

                        if (var7
                           && var18.toLowerCase(Locale.ROOT).endsWith(".png")) {
                           byte[] var43 = this.Xqedii_o7wyc7k(var17);
                           if (var43 != null) {
                              var11.putNextEntry(var37);
                              var11.write(var43);
                              var11.closeEntry();
                              continue;
                           }
                        }

                        var11.putNextEntry(var37);

                        int var46;
                        try (InputStream var44 = Files.newInputStream(var17.toPath())) {
                           while ((var46 = var44.read(var12)) != -1) {
                              if (var46 > 0) {
                                 var11.write(var12, 0, var46);
                              }
                           }
                        }

                        var11.closeEntry();
                     }
                  }
               }
            }

            if (var8) {
               byte[] var31 = this.Xqedii_2wbre7l();
               if (var31 != null) {
                  String[] var33 = Xqedii_311p58w;
                  int var35 = var33.length;

                  for (int var38 = 0; var38 < var35; var38++) {
                     String var41 = var33[var38];
                     String var45 = Xqedii_y4rwgmn5at72(var41);
                     String var47 = var45.toLowerCase(Locale.ROOT);
                     int var23 = var13 == null || !var13.contains(var47) && !var15.contains(var47)
                        ? 0
                        : 1;
                     if (var23 == 0) {
                        ZipEntry var24 = new ZipEntry(var45);
                        var11.putNextEntry(var24);
                        var11.write(var31);
                        var11.closeEntry();
                        if (var10 != null) {
                           var10.add(var47);
                        }
                     }
                  }
               }
            }

            for (Entry var34 : this.Xqedii_3vyyt4t.Xqedii_1umuut0().entrySet()) {
               String var36 = (String)var34.getKey();
               if (var14.add(var36.toLowerCase(Locale.ROOT))) {
                  ZipEntry var39 = new ZipEntry(var36);
                  var11.putNextEntry(var39);
                  var11.write((byte[])var34.getValue());
                  var11.closeEntry();
                  if (var10 != null) {
                     var10.add(var36.toLowerCase(Locale.ROOT));
                  }
               }
            }

            if (var9 != null) {
               var9.Xqedii_1bv0otb(var11, var10);
            }
         } catch (IOException var30) {
            var5.delete();
            this.Xqedii_1shmcss.getLogger().warning(Xqedii_2qxjwhx5v0kmy(var30.getMessage()));
            return false;
         }

         try {
            Path var10000 = var5.toPath();
            Path var10001 = var3.toPath();
            CopyOption[] var10002 = new CopyOption[1];
            var10002[0] = StandardCopyOption.REPLACE_EXISTING;
            Files.move(var10000, var10001, var10002);
            this.Xqedii_9z4s7md(var9);
            return true;
         } catch (IOException var27) {
            var5.delete();
            this.Xqedii_1shmcss.getLogger().warning(Xqedii_68u5gh7danz6(var27.getMessage()));
            return false;
         }
      } else {
         return false;
      }
   }

   public void Xqedii_9z4s7md(Xqedii_2om8ulg var1) {
      if (var1 != null) {
         this.Xqedii_1shmcss.getLogger().info(Xqedii_2vzsl9dkxees6(var1.Xqedii_km8lvcg(), var1.Xqedii_255fjeb()));
         if (var1.Xqedii_ivak95i() && var1.Xqedii_255fjeb() == 0) {
            this.Xqedii_1shmcss
               .getLogger()
               .warning(
                  "[UltimateUI] BetterHud support: no UltimateUI text shader could be patched, BetterHud HUDs will not be positioned."
               );
         }
      }
   }

   public byte[] Xqedii_2wbre7l() {
      {
         try {
            BufferedImage var1 = new BufferedImage(
               1,
               1,
               2
            );
            var1.setRGB(
               0,
               0,
               0
            );
            ByteArrayOutputStream var2 = new ByteArrayOutputStream(256);
            ImageIO.write(var1, "png", var2);
            return var2.toByteArray();
         } catch (Exception var3) {
            return null;
         }
      }
   }

   public byte[] Xqedii_o7wyc7k(File var1) {
      {
         try {
            BufferedImage var2 = ImageIO.read(var1);
            if (var2 == null) {
               return null;
            } else {
               Iterator var3 = ImageIO.getImageWritersByFormatName("png");
               if (!var3.hasNext()) {
                  return null;
               } else {
                  ImageWriter var4 = (ImageWriter)var3.next();
                  ByteArrayOutputStream var5 = new ByteArrayOutputStream();

                  try (ImageOutputStream var6 = ImageIO.createImageOutputStream(var5)) {
                     var4.setOutput(var6);
                     ImageWriteParam var7 = var4.getDefaultWriteParam();
                     if (var7.canWriteCompressed()) {
                        var7.setCompressionMode(2);
                        var7.setCompressionQuality(Float.intBitsToFloat(0));
                     }

                     var4.write(null, new IIOImage(var2, null, null), var7);
                  } finally {
                     var4.dispose();
                  }

                  return var5.toByteArray();
               }
            }
         } catch (Exception var17) {
            return null;
         }
      }
   }

   public boolean Xqedii_3mxjrnz(File var1, String var2, int var3) {
      if (this.Xqedii_3uhsxl7 == null) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (!var2.equals(this.Xqedii_3pdoav9) || var3 != this.Xqedii_15ekavo) {
         return true;
      } else if (this.Xqedii_2os4ady != null && this.Xqedii_2os4ady.equals(var1)) {
         return ((this.Xqedii_1du0frk == var1.lastModified() && this.Xqedii_3ggm0me == var1.length()
            ? 0
            : 1) != 0);
      } else {
         return true;
      }
   }

   public String Xqedii_3i96c4b(String var1) {
      {
         String var2 = var1 == null ? "" : var1.trim();
         String var3 = var2.toLowerCase(Locale.ROOT);
         if (var3.startsWith("http://")) {
            var2 = var2.substring(7);
         } else if (var3.startsWith("https://")) {
            var2 = var2.substring(8);
         }

         int var4 = var2.indexOf(47);
         if (var4 >= 0) {
            var2 = var2.substring(0, var4);
         }

         return var2.trim();
      }
   }

   public int Xqedii_1dvqiyl(String var1) {
      {
         int var2 = var1.lastIndexOf(58);
         if (var2 >= 0 && var2 == var1.indexOf(58)) {
            try {
               int var3 = Integer.parseInt(var1.substring(var2 + 1).trim());
               return var3 >= 1 && var3 <= 65535
                  ? var3
                  : -1;
            } catch (NumberFormatException var4) {
               return -1;
            }
         } else {
            return -1;
         }
      }
   }

   public String Xqedii_3ej5w8v(String var1) {
      {
         int var2 = var1.lastIndexOf(58);
         return var2 >= 0 && var2 == var1.indexOf(58)
            ? var1.substring(0, var2).trim()
            : var1;
      }
   }

   public int Xqedii_3982wq2(int var1) {
      {
         return var1 >= 1 && var1 <= 65535
            ? var1
            : 8123;
      }
   }

   public String Xqedii_8luxcgl(String var1, int var2) {
      {
         String var3 = var1;
         if (var1 == null || var1.isEmpty() || "0.0.0.0".equals(var1)) {
            String var4 = this.Xqedii_1shmcss.getServer().getIp();
            var3 = var4 != null
                  && !var4.isBlank()
                  && !"0.0.0.0".equals(var4.trim())
               ? var4.trim()
               : "127.0.0.1";
            if (!this.Xqedii_2niafqi) {
               this.Xqedii_2niafqi = true;
               this.Xqedii_1shmcss.getLogger().warning(Xqedii_23ipzr75jmy5e(var3));
            }
         }

         return Xqedii_1hlkrolbl4nwl(var3, var2);
      }
   }

   static {
      {
         String[] var10000 = new String[78];
         var10000[0] = "air.png";
         var10000[1] = "air_bursting.png";
         var10000[2] = "air_empty.png";
         var10000[3] = "armor_empty.png";
         var10000[4] = "armor_full.png";
         var10000[5] = "armor_half.png";
         var10000[6] = "experience_bar_background.png";
         var10000[7] = "experience_bar_progress.png";
         var10000[8] = "food_empty.png";
         var10000[9] = "food_empty_hunger.png";
         var10000[10] = "food_full.png";
         var10000[11] = "food_full_hunger.png";
         var10000[12] = "food_half.png";
         var10000[13] = "food_half_hunger.png";
         var10000[14] = "hotbar.png";
         var10000[15] = "hotbar_attack_indicator_background.png";
         var10000[16] = "hotbar_attack_indicator_progress.png";
         var10000[17] = "hotbar_offhand_left.png";
         var10000[18] = "hotbar_offhand_right.png";
         var10000[19] = "hotbar_selection.png";
         var10000[20] = "jump_bar_background.png";
         var10000[21] = "jump_bar_cooldown.png";
         var10000[22] = "jump_bar_progress.png";
         var10000[23] = "locator_bar_arrow_down.png";
         var10000[24] = "locator_bar_arrow_up.png";
         var10000[25] = "locator_bar_background.png";
         var10000[26] = "heart/absorbing_full.png";
         var10000[27] = "heart/absorbing_full_blinking.png";
         var10000[28] = "heart/absorbing_half.png";
         var10000[29] = "heart/absorbing_half_blinking.png";
         var10000[30] = "heart/absorbing_hardcore_full.png";
         var10000[31] = "heart/absorbing_hardcore_full_blinking.png";
         var10000[32] = "heart/absorbing_hardcore_half.png";
         var10000[33] = "heart/absorbing_hardcore_half_blinking.png";
         var10000[34] = "heart/container.png";
         var10000[35] = "heart/container_blinking.png";
         var10000[36] = "heart/container_hardcore.png";
         var10000[37] = "heart/container_hardcore_blinking.png";
         var10000[38] = "heart/frozen_full.png";
         var10000[39] = "heart/frozen_full_blinking.png";
         var10000[40] = "heart/frozen_half.png";
         var10000[41] = "heart/frozen_half_blinking.png";
         var10000[42] = "heart/frozen_hardcore_full.png";
         var10000[43] = "heart/frozen_hardcore_full_blinking.png";
         var10000[44] = "heart/frozen_hardcore_half.png";
         var10000[45] = "heart/frozen_hardcore_half_blinking.png";
         var10000[46] = "heart/full.png";
         var10000[47] = "heart/full_blinking.png";
         var10000[48] = "heart/half.png";
         var10000[49] = "heart/half_blinking.png";
         var10000[50] = "heart/hardcore_full.png";
         var10000[51] = "heart/hardcore_full_blinking.png";
         var10000[52] = "heart/hardcore_half.png";
         var10000[53] = "heart/hardcore_half_blinking.png";
         var10000[54] = "heart/poisoned_full.png";
         var10000[55] = "heart/poisoned_full_blinking.png";
         var10000[56] = "heart/poisoned_half.png";
         var10000[57] = "heart/poisoned_half_blinking.png";
         var10000[58] = "heart/poisoned_hardcore_full.png";
         var10000[59] = "heart/poisoned_hardcore_full_blinking.png";
         var10000[60] = "heart/poisoned_hardcore_half.png";
         var10000[61] = "heart/poisoned_hardcore_half_blinking.png";
         var10000[62] = "heart/vehicle_container.png";
         var10000[63] = "heart/vehicle_full.png";
         var10000[64] = "heart/vehicle_half.png";
         var10000[65] = "heart/withered_full.png";
         var10000[66] = "heart/withered_full_blinking.png";
         var10000[67] = "heart/withered_half.png";
         var10000[68] = "heart/withered_half_blinking.png";
         var10000[69] = "heart/withered_hardcore_full.png";
         var10000[70] = "heart/withered_hardcore_full_blinking.png";
         var10000[71] = "heart/withered_hardcore_half.png";
         var10000[72] = "heart/withered_hardcore_half_blinking.png";
         var10000[73] = "locator_bar_dot/bowtie.png";
         var10000[74] = "locator_bar_dot/default_0.png";
         var10000[75] = "locator_bar_dot/default_1.png";
         var10000[76] = "locator_bar_dot/default_2.png";
         var10000[77] = "locator_bar_dot/default_3.png";
         Xqedii_311p58w = var10000;
      }
   }

   private static /* synthetic */ String Xqedii_2nf4jtbwzcda2(String string) {
        return "[UltimateUI] Failed to read resource pack archive: " + string;
    }

    private static /* synthetic */ String Xqedii_126v5ll9lwg94(int n) {
        return "[UltimateUI] Self-host pack-port " + n + " is the Minecraft server port. Allocate a second port (e.g. in Pterodactyl > Network) and put that one in pack-port.";
    }

    private static /* synthetic */ String Xqedii_3h28hl7vcr7yo(int n, String string) {
        return "[UltimateUI] Failed to host resource pack on port " + n + ": " + string;
    }

    private static /* synthetic */ String Xqedii_2ertqmlrf3nxa(String string) {
        return string + " - another process (or the Minecraft server itself) already uses this port";
    }

    private static /* synthetic */ String Xqedii_1nu8wy3z4d1iu(String string, String string2) {
        return "[UltimateUI] Failed to seed resourcepack/pack from legacy entry '" + string + "': " + string2;
    }

    private static /* synthetic */ String Xqedii_34v9ng3qrjwa8(String string) {
        return "#define UUI_CURSOR_HALF_PX " + string;
    }

    private static /* synthetic */ String Xqedii_1wlh58c3nfcnt(String string) {
        return "#define UUI_CURSOR_ANCHOR_PX " + string;
    }

    private static /* synthetic */ String Xqedii_msqw27el950g(String string) {
        return string + ".tmp";
    }

    private static /* synthetic */ String Xqedii_y4rwgmn5at72(String string) {
        return "assets/minecraft/textures/gui/sprites/hud/" + string;
    }

    private static /* synthetic */ String Xqedii_2qxjwhx5v0kmy(String string) {
        return "[UltimateUI] Failed to generate resource pack archive: " + string;
    }

    private static /* synthetic */ String Xqedii_68u5gh7danz6(String string) {
        return "[UltimateUI] Failed to move generated resource pack archive: " + string;
    }

    private static /* synthetic */ String Xqedii_2vzsl9dkxees6(int n, int n2) {
        return "[UltimateUI] BetterHud support: merged " + n + " BetterHud files into resourcepack/generated, patched " + n2 + " text shaders.";
    }

    private static /* synthetic */ String Xqedii_23ipzr75jmy5e(String string) {
        return "[UltimateUI] resource-pack.hosting.self-host.server-ip is not set, using " + string + ". Set it to the public IP or domain players connect to.";
    }

    private static /* synthetic */ String Xqedii_1hlkrolbl4nwl(String string, int n) {
        return "http://" + string + ":" + n + "/pack";
    }
}
