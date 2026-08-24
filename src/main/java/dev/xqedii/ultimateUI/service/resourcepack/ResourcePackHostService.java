package dev.xqedii.ultimateUI.service.resourcepack;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import dev.xqedii.ultimateUI.UltimateUI;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

public final class ResourcePackHostService {
   private static final String RESOURCE_PACK_ENDPOINT = "/pack";
   private static final String PACK_FOLDER_NAME = "pack";
   private static final String GENERATED_FOLDER_NAME = "generated";
   private static final String GENERATED_ARCHIVE_NAME = "resourcepack.zip";
   private static final String HUD_SPRITES_PATH = "assets/minecraft/textures/gui/sprites/hud/";
   private static final String[] HUD_SPRITE_FILES = new String[]{
      "air.png",
      "air_bursting.png",
      "air_empty.png",
      "armor_empty.png",
      "armor_full.png",
      "armor_half.png",
      "experience_bar_background.png",
      "experience_bar_progress.png",
      "food_empty.png",
      "food_empty_hunger.png",
      "food_full.png",
      "food_full_hunger.png",
      "food_half.png",
      "food_half_hunger.png",
      "hotbar.png",
      "hotbar_attack_indicator_background.png",
      "hotbar_attack_indicator_progress.png",
      "hotbar_offhand_left.png",
      "hotbar_offhand_right.png",
      "hotbar_selection.png",
      "jump_bar_background.png",
      "jump_bar_cooldown.png",
      "jump_bar_progress.png",
      "locator_bar_arrow_down.png",
      "locator_bar_arrow_up.png",
      "locator_bar_background.png",
      "heart/absorbing_full.png",
      "heart/absorbing_full_blinking.png",
      "heart/absorbing_half.png",
      "heart/absorbing_half_blinking.png",
      "heart/absorbing_hardcore_full.png",
      "heart/absorbing_hardcore_full_blinking.png",
      "heart/absorbing_hardcore_half.png",
      "heart/absorbing_hardcore_half_blinking.png",
      "heart/container.png",
      "heart/container_blinking.png",
      "heart/container_hardcore.png",
      "heart/container_hardcore_blinking.png",
      "heart/frozen_full.png",
      "heart/frozen_full_blinking.png",
      "heart/frozen_half.png",
      "heart/frozen_half_blinking.png",
      "heart/frozen_hardcore_full.png",
      "heart/frozen_hardcore_full_blinking.png",
      "heart/frozen_hardcore_half.png",
      "heart/frozen_hardcore_half_blinking.png",
      "heart/full.png",
      "heart/full_blinking.png",
      "heart/half.png",
      "heart/half_blinking.png",
      "heart/hardcore_full.png",
      "heart/hardcore_full_blinking.png",
      "heart/hardcore_half.png",
      "heart/hardcore_half_blinking.png",
      "heart/poisoned_full.png",
      "heart/poisoned_full_blinking.png",
      "heart/poisoned_half.png",
      "heart/poisoned_half_blinking.png",
      "heart/poisoned_hardcore_full.png",
      "heart/poisoned_hardcore_full_blinking.png",
      "heart/poisoned_hardcore_half.png",
      "heart/poisoned_hardcore_half_blinking.png",
      "heart/vehicle_container.png",
      "heart/vehicle_full.png",
      "heart/vehicle_half.png",
      "heart/withered_full.png",
      "heart/withered_full_blinking.png",
      "heart/withered_half.png",
      "heart/withered_half_blinking.png",
      "heart/withered_hardcore_full.png",
      "heart/withered_hardcore_full_blinking.png",
      "heart/withered_hardcore_half.png",
      "heart/withered_hardcore_half_blinking.png",
      "locator_bar_dot/bowtie.png",
      "locator_bar_dot/default_0.png",
      "locator_bar_dot/default_1.png",
      "locator_bar_dot/default_2.png",
      "locator_bar_dot/default_3.png"
   };
   private final UltimateUI plugin;
   private volatile HttpServer server;
   private volatile byte[] payload = new byte[0];
   private String bindAddress = "";
   private int bindPort = -1;
   private File hostedFile;
   private long hostedLastModified = Long.MIN_VALUE;
   private long hostedLength = Long.MIN_VALUE;
   private long generatedSourceFingerprint = Long.MIN_VALUE;

   public ResourcePackHostService(UltimateUI var1) {
      this.plugin = var1;
   }

   public synchronized File ensureGeneratedArchive(File var1) {
      return this.ensureGeneratedArchive(var1, false);
   }

   public synchronized File ensureGeneratedArchive(File var1, boolean var2) {
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

         this.ensurePackFolderHasSources(var1, var3);
         File var5 = this.resolveGeneratedArchive(var3, var4, var2);
         return var5 != null && var5.exists() && var5.isFile() ? var5 : null;
      }
   }

   public synchronized String resolveHostedPackUrl(File var1, String var2, int var3) {
      String var4 = this.normalizeAddress(var2);
      int var5 = this.normalizePort(var3);
      File var6 = this.ensureGeneratedArchive(var1, false);
      if (var6 == null || !var6.exists() || !var6.isFile()) {
         this.stop();
         return null;
      } else if (!this.needsRestart(var6, var4, var5)) {
         return this.buildPublicUrl(var4, var5);
      } else {
         try {
            byte[] var7 = Files.readAllBytes(var6.toPath());
            this.stop();
            this.startServer(var4, var5, var7);
            this.hostedFile = var6;
            this.hostedLastModified = var6.lastModified();
            this.hostedLength = var6.length();
            this.bindAddress = var4;
            this.bindPort = var5;
            return this.buildPublicUrl(var4, var5);
         } catch (IOException var8) {
            this.stop();
            this.plugin.getLogger().warning("[UltimateUI] Failed to host resource pack: " + var8.getMessage());
            return null;
         }
      }
   }

   public synchronized void stop() {
      HttpServer var1 = this.server;
      if (var1 != null) {
         var1.stop(0);
      }

      this.server = null;
      this.payload = new byte[0];
      this.bindAddress = "";
      this.bindPort = -1;
      this.hostedFile = null;
      this.hostedLastModified = Long.MIN_VALUE;
      this.hostedLength = Long.MIN_VALUE;
   }

   private void startServer(String var1, int var2, byte[] var3) throws IOException {
      InetSocketAddress var4 = new InetSocketAddress(var1, var2);
      HttpServer var5 = HttpServer.create(var4, 0);
      this.payload = var3 == null ? new byte[0] : var3;
      var5.createContext("/pack", this::handlePackRequest);
      var5.setExecutor(null);
      var5.start();
      this.server = var5;
   }

   private void handlePackRequest(HttpExchange var1) throws IOException {
      if (var1 != null) {
         try {
            String var2 = var1.getRequestMethod();
            if ("GET".equalsIgnoreCase(var2)) {
               byte[] var3 = this.payload;
               if (var3 == null) {
                  var3 = new byte[0];
               }

               var1.getResponseHeaders().set("Content-Type", "application/zip");
               var1.getResponseHeaders().set("Cache-Control", "no-store, no-cache, must-revalidate");
               var1.sendResponseHeaders(200, (long)var3.length);

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

   private File resolveGeneratedArchive(File var1, File var2, boolean var3) {
      List var4 = this.collectSourceFiles(var1);
      if (var4.isEmpty()) {
         return null;
      } else {
         File var5 = new File(var2, "resourcepack.zip");
         long var6 = this.computeSourceFingerprint(var4, var1.toPath());
         if (this.plugin.getConfig().getBoolean("resource-pack.remove-default-hotbar", false)) {
            var6 ^= 7823459261849L;
         }

         if (!var3 && var6 == this.generatedSourceFingerprint && var5.exists() && var5.isFile()) {
            return var5;
         } else if (!this.generateArchiveFromPackFolder(var1, var4, var5)) {
            return null;
         } else {
            this.generatedSourceFingerprint = var6;
            return var5;
         }
      }
   }

   private void ensurePackFolderHasSources(File var1, File var2) {
      if (var2 != null) {
         if (this.collectSourceFiles(var2).isEmpty()) {
            File var3 = var1 == null ? null : var1.getParentFile();
            if (var3 != null && var3.exists() && var3.isDirectory()) {
               File[] var4 = var3.listFiles();
               if (var4 != null && var4.length != 0) {
                  boolean var5 = false;

                  for (File var9 : var4) {
                     if (this.isLegacyRootPackEntry(var9)) {
                        File var10 = new File(var2, var9.getName());

                        try {
                           this.copyRecursively(var9, var10);
                           var5 = true;
                        } catch (IOException var12) {
                           this.plugin
                              .getLogger()
                              .warning("[UltimateUI] Failed to seed resourcepack/pack from legacy entry '" + var9.getName() + "': " + var12.getMessage());
                        }
                     }
                  }

                  if (var5) {
                     this.plugin.getLogger().info("[UltimateUI] Seeded resourcepack/pack from legacy root layout.");
                  }
               }
            }
         }
      }
   }

   private boolean isLegacyRootPackEntry(File var1) {
      if (var1 != null && var1.exists()) {
         String var2 = var1.getName();
         if (var2 == null || var2.isBlank()) {
            return false;
         } else if (var1.isFile()) {
            return "pack.mcmeta".equalsIgnoreCase(var2) || "pack.png".equalsIgnoreCase(var2);
         } else {
            return !var1.isDirectory() ? false : "assets".equalsIgnoreCase(var2) || var2.matches("\\d+_[0-9A-Za-z_]+$");
         }
      } else {
         return false;
      }
   }

   private void copyRecursively(File var1, File var2) throws IOException {
      if (var1 != null && var2 != null) {
         if (var1.isDirectory()) {
            if (!var2.exists()) {
               var2.mkdirs();
            }

            File[] var8 = var1.listFiles();
            if (var8 != null && var8.length != 0) {
               for (File var7 : var8) {
                  if (var7 != null) {
                     this.copyRecursively(var7, new File(var2, var7.getName()));
                  }
               }
            }
         } else {
            File var3 = var2.getParentFile();
            if (var3 != null && !var3.exists()) {
               var3.mkdirs();
            }

            Files.copy(var1.toPath(), var2.toPath(), StandardCopyOption.REPLACE_EXISTING);
         }
      }
   }

   private List<File> collectSourceFiles(File var1) {
      if (var1 != null && var1.exists() && var1.isDirectory()) {
         ArrayList var2 = new ArrayList();
         this.collectSourceFilesRecursive(var1, var2);
         var2.sort(Comparator.comparing(File::getAbsolutePath, String.CASE_INSENSITIVE_ORDER));
         return var2;
      } else {
         return Collections.emptyList();
      }
   }

   private void collectSourceFilesRecursive(File var1, List<File> var2) {
      if (var1 != null && var2 != null) {
         File[] var3 = var1.listFiles();
         if (var3 != null && var3.length != 0) {
            for (File var7 : var3) {
               if (var7 != null) {
                  if (var7.isDirectory()) {
                     this.collectSourceFilesRecursive(var7, var2);
                  } else if (var7.isFile()) {
                     var2.add(var7);
                  }
               }
            }
         }
      }
   }

   private long computeSourceFingerprint(List<File> var1, Path var2) {
      long var3 = 1125899906842597L;
      if (var1 != null && !var1.isEmpty() && var2 != null) {
         for (File var6 : var1) {
            if (var6 != null) {
               String var7;
               try {
                  var7 = var2.relativize(var6.toPath()).toString().replace('\\', '/').toLowerCase(Locale.ROOT);
               } catch (Exception var9) {
                  var7 = var6.getName().toLowerCase(Locale.ROOT);
               }

               var3 = 31L * var3 + (long)var7.hashCode();
               var3 = 31L * var3 + (long)Long.hashCode(var6.lastModified());
               var3 = 31L * var3 + (long)Long.hashCode(var6.length());
            }
         }

         return var3;
      } else {
         return var3;
      }
   }

   private boolean generateArchiveFromPackFolder(File var1, List<File> var2, File var3) {
      if (var1 != null && var2 != null && !var2.isEmpty() && var3 != null) {
         File var4 = var3.getParentFile();
         if (var4 != null && !var4.exists()) {
            var4.mkdirs();
         }

         File var5 = new File(var4 == null ? var3.getParent() : var4.getAbsolutePath(), var3.getName() + ".tmp");
         Path var6 = var1.toPath();
         boolean var7 = this.plugin.getConfig().getBoolean("resource-pack.compress-images", false);
         boolean var8 = this.plugin.getConfig().getBoolean("resource-pack.remove-default-hotbar", false);

         try (ZipOutputStream var9 = new ZipOutputStream(Files.newOutputStream(var5.toPath()))) {
            byte[] var10 = new byte[8192];
            HashSet var11 = var8 ? new HashSet() : null;
            HashSet var12 = var8 ? new HashSet() : null;

            for (File var14 : var2) {
               if (var14 != null && var14.exists() && var14.isFile()) {
                  String var15 = var6.relativize(var14.toPath()).toString().replace('\\', '/');
                  if (!var15.isBlank()) {
                     if (var11 != null) {
                        String var16 = var15.toLowerCase(Locale.ROOT);
                        var11.add(var16);
                        int var17 = var16.indexOf("assets/minecraft/textures/gui/sprites/hud/");
                        if (var17 >= 0) {
                           var12.add(var16.substring(var17));
                        }
                     }

                     ZipEntry var31 = new ZipEntry(var15);
                     var31.setTime(var14.lastModified());
                     if (var7 && var15.toLowerCase(Locale.ROOT).endsWith(".png")) {
                        byte[] var33 = this.compressPngLossless(var14);
                        if (var33 != null) {
                           var9.putNextEntry(var31);
                           var9.write(var33);
                           var9.closeEntry();
                           continue;
                        }
                     }

                     var9.putNextEntry(var31);

                     int var18;
                     try (InputStream var34 = Files.newInputStream(var14.toPath())) {
                        while ((var18 = var34.read(var10)) != -1) {
                           if (var18 > 0) {
                              var9.write(var10, 0, var18);
                           }
                        }
                     }

                     var9.closeEntry();
                  }
               }
            }

            if (var8) {
               byte[] var28 = this.buildTransparent1x1Png();
               if (var28 != null) {
                  for (Object var35_raw : HUD_SPRITE_FILES) {
                     String var35 = var35_raw != null ? var35_raw.toString() : null;
                     String var36 = "assets/minecraft/textures/gui/sprites/hud/" + var35;
                     String var19 = var36.toLowerCase(Locale.ROOT);
                     boolean var20 = var11 != null && (var11.contains(var19) || var12.contains(var19));
                     if (!var20) {
                        ZipEntry var21 = new ZipEntry(var36);
                        var9.putNextEntry(var21);
                        var9.write(var28);
                        var9.closeEntry();
                     }
                  }
               }
            }
         } catch (IOException var27) {
            var5.delete();
            this.plugin.getLogger().warning("[UltimateUI] Failed to generate resource pack archive: " + var27.getMessage());
            return false;
         }

         try {
            Files.move(var5.toPath(), var3.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
         } catch (IOException var24) {
            var5.delete();
            this.plugin.getLogger().warning("[UltimateUI] Failed to move generated resource pack archive: " + var24.getMessage());
            return false;
         }
      } else {
         return false;
      }
   }

   private byte[] buildTransparent1x1Png() {
      try {
         BufferedImage var1 = new BufferedImage(1, 1, 2);
         var1.setRGB(0, 0, 0);
         ByteArrayOutputStream var2 = new ByteArrayOutputStream(256);
         ImageIO.write(var1, "png", var2);
         return var2.toByteArray();
      } catch (Exception var3) {
         return null;
      }
   }

   private byte[] compressPngLossless(File var1) {
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
                     var7.setCompressionQuality(0.0F);
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

   private boolean needsRestart(File var1, String var2, int var3) {
      if (this.server == null) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (!var2.equals(this.bindAddress) || var3 != this.bindPort) {
         return true;
      } else {
         return this.hostedFile != null && this.hostedFile.equals(var1)
            ? this.hostedLastModified != var1.lastModified() || this.hostedLength != var1.length()
            : true;
      }
   }

   private String normalizeAddress(String var1) {
      String var2 = var1 == null ? "" : var1.trim();
      return var2.isEmpty() ? "127.0.0.1" : var2;
   }

   private int normalizePort(int var1) {
      return var1 >= 1 && var1 <= 65535 ? var1 : 8123;
   }

   private String buildPublicUrl(String var1, int var2) {
      String var3 = "0.0.0.0".equals(var1) ? "127.0.0.1" : var1;
      return "http://" + var3 + ":" + var2 + "/pack";
   }
}
