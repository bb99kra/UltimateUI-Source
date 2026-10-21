package dev.xqedii.ultimateUI;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public abstract class ResourcePackDelivery extends LangMessages {
   public static final String Xqedii_17jv3dj = "pack/";
   public static final String Xqedii_2u1t1st = "assets/minecraft/shaders/";

   public void scheduleJoinResourcePackApply(Player var1) {
      if (var1 != null && var1.isOnline()) {
         if (this.getConfig()
            .getBoolean(
               "resource-pack.apply-on-join",
               true
            )) {
            String var2 = this.resolveResourcePackUrl();
            if (var2 != null && !var2.isBlank()) {
               this.sendResolvedResourcePack(var1, var2, 10L);
            }
         }
      }
   }

   public String resolveResourcePackUrl() {
      {
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
               if (this.Xqedii_391oru2 != null) {
                  this.Xqedii_391oru2.Xqedii_3d0prc5();
               }

               return null;
            }
         }

         if (this.getConfig()
            .getBoolean(
               "resource-pack.hosting.external-pack.enabled",
               false
            )) {
            if (this.Xqedii_391oru2 != null) {
               this.Xqedii_391oru2.Xqedii_3d0prc5();
            }

            return null;
         } else {
            if (this.getConfig()
               .getBoolean(
                  "resource-pack.hosting.self-host.enabled",
                  false
               )) {
               String var2 = this.resolveSelfHostedResourcePackUrl();
               if (var2 != null && !var2.isBlank()) {
                  return var2;
               }
            }

            if (this.Xqedii_391oru2 != null) {
               this.Xqedii_391oru2.Xqedii_3d0prc5();
            }

            if (this.getConfig()
               .getBoolean(
                  "resource-pack.hosting.external-host.enabled",
                  false
               )) {
               return this.normalizeResourcePackUrl(
                  this.getConfig()
                     .getString(
                        "resource-pack.hosting.external-host.url",
                        ""
                     )
               );
            } else {
               if (this.getConfig()
                  .getBoolean(
                     "resource-pack.hosting.default-pack.enabled",
                     false
                  )) {
                  String var3 = this.Xqedii_1zavo1k != null ? this.Xqedii_1zavo1k.Xqedii_3c3zwyj() : null;
                  if (var3 != null && !var3.isBlank()) {
                     return this.normalizeResourcePackUrl(var3);
                  }
               }

               return null;
            }
         }
      }
   }

   public String resolveSelfHostedResourcePackUrl() {
      if (this.Xqedii_391oru2 == null) {
         return null;
      } else {
         File var1 = this.ensureResourcePackFolder();
         String var2 = this.getConfig()
            .getString(
               "resource-pack.hosting.self-host.server-ip",
               ""
            );
         int var3 = this.resolveSelfHostPort();
         String var4 = this.Xqedii_391oru2.Xqedii_3y0jx5p(var1, var2, var3);
         if (var4 != null && !var4.isBlank()) {
            return this.normalizeResourcePackUrl(var4);
         } else {
            File var5 = new File(var1, "pack");
            if (this.countFilesRecursively(var5) <= 0L) {
               this.getLogger()
                  .warning(
                     "[UltimateUI] Self-host resource pack is enabled, but no files were found in /resourcepack/pack to generate a zip."
                  );
            }

            return null;
         }
      }
   }

   public int resolveSelfHostPort() {
      {
         Object var1 = this.getConfig()
            .get(
               "resource-pack.hosting.self-host.pack-port"
            );
         if (var1 instanceof Number var2) {
            return var2.intValue();
         } else {
            if (var1 != null) {
               try {
                  return Integer.parseInt(var1.toString().trim());
               } catch (NumberFormatException var3) {
                  this.getLogger().warning(Xqedii_2b3hebqk0jdzw(String.valueOf(var1)));
               }
            }

            return 8123;
         }
      }
   }

   public void mergeConfigDefaults() {
      {
         InputStream var1 = this.getResource("config.yml");
         if (var1 == null) {
            this.getLogger()
               .warning(
                  "[UltimateUI] Could not merge config.yml defaults: bundled config.yml resource is missing from the plugin jar."
               );
         } else {
            YamlConfiguration var2;
            try (InputStreamReader var3 = new InputStreamReader(var1, StandardCharsets.UTF_8)) {
               var2 = YamlConfiguration.loadConfiguration(var3);
            } catch (IOException var11) {
               this.getLogger().warning(Xqedii_2u8wzaffphh50(var11.getMessage()));
               return;
            }

            int var12 = 0;
            String[] var10000 = new String[5];
            var10000[0] = "self-host";
            var10000[1] = "external-host";
            var10000[2] = "external-pack";
            var10000[3] = "default-pack";
            var10000[4] = "xqedii-setups";
            String[] var4 = var10000;
            int var5 = var4.length;

            for (int var6 = 0; var6 < var5; var6++) {
               String var7 = var4[var6];
               if (this.getConfig().contains(Xqedii_1si5bkmp4ti2h(var7))
                  && this.getConfig().getBoolean(Xqedii_ziqarhyoy509(var7), false)) {
                  var12 = 1;
                  break;
               }
            }

            int var13 = 0;
            ArrayList var14 = new ArrayList();

            for (String var16 : var2.getKeys(true)) {
               if (!var2.isConfigurationSection(var16) && !this.getConfig().contains(var16, true)) {
                  Object var8 = var2.get(var16);
                  if (Boolean.TRUE.equals(var8)
                     && var12 != 0
                     && var16.startsWith(
                        "resource-pack.hosting."
                     )
                     && var16.endsWith(".enabled")) {
                     this.getConfig().set(var16, Boolean.valueOf(false));
                  } else {
                     this.getConfig().set(var16, var8);
                  }

                  var13 = 1;
                  var14.add(var16);
               }
            }

            if (var13 != 0) {
               this.saveConfig();
               this.getLogger()
                  .info(
                     Xqedii_exea7urlkrn(var14.size(), String.join(", ", var14))
                  );
            }
         }
      }
   }

   public String normalizeResourcePackUrl(String var1) {
      if (var1 == null) {
         return null;
      } else {
         String var2 = var1.trim();
         if (var2.isBlank()) {
            return null;
         } else {
            String var3 = var2.toLowerCase(Locale.ROOT);
            return !var3.startsWith("http://")
                  && !var3.startsWith("https://")
               ? null
               : var2;
         }
      }
   }

   public Component resolveResourcePackPromptComponent() {
      {
         List var1 = this.getLangStringList(
            "resource-pack.messages.prompt"
         );
         if (var1.isEmpty()) {
            return null;
         } else {
            Component var2 = null;

            for (String var4 : (Iterable<String>) (var1)) {

               Component var5 = this.deserializeConfiguredMessage(var4);
               if (var2 == null) {
                  var2 = var5;
               } else {
                  var2 = var2.append(Component.newline()).append(var5);
               }
            }

            return var2;
         }
      }
   }

   public void rebuildUiImagesIntoResourcePack() {
      {
         AtlasGenerationResult var1 = this.Xqedii_1e7k2q0.Xqedii_1hvwbjh(this.getDataFolder(), this.getLogger());
         if (var1 != null && var1.imagesByName() != null) {
            this.Xqedii_11hx7yt = new HashMap<>(var1.imagesByName());
         } else {
            this.Xqedii_11hx7yt = new HashMap<>();
         }
      }
   }

   public void ensureGeneratedResourcePackArchive(boolean var1) {
      if (this.Xqedii_391oru2 != null) {
         File var2 = this.ensureResourcePackFolder();
         File var3 = this.Xqedii_391oru2.Xqedii_ec1q5pv(var2, var1);
         if (var3 == null || !var3.exists() || !var3.isFile()) {
            File var4 = new File(var2, "pack");
            long var5 = this.countFilesRecursively(var4);
            if (var5 <= 0L) {
               this.getLogger()
                  .warning(
                     "[UltimateUI] resourcepack/pack is empty. Put your pack files there and run /uui reload."
                  );
            } else {
               this.getLogger()
                  .warning(
                     "[UltimateUI] Failed to generate resourcepack/generated/resourcepack.zip from resourcepack/pack."
                  );
            }
         }
      }
   }

   public void syncPackToXqediiSetups() {
      if (this.getConfig()
         .getBoolean(
            "resource-pack.hosting.xqedii-setups.enabled",
            false
         )) {
         File var1 = this.getDataFolder().getParentFile();
         if (var1 != null) {
            File var2 = new File(var1, "XqediiSetups");
            if (var2.exists() && var2.isDirectory()) {
               File var3 = new File(this.getDataFolder(), Xqedii_tas30m367lbi(File.separator));
               File var4 = new File(var2, Xqedii_3v61hj4hww2az(File.separator));
               File var5 = new File(var3, Xqedii_2vz74886luw46(File.separator));
               if (var5.exists() && var5.isDirectory()) {
                  File var6 = new File(var4, Xqedii_u24d1rsvn4cq(File.separator));
                  File var7 = new File(var6, Xqedii_3rg4n9f91g3fc(File.separator, File.separator));
                  if (var7.exists() && var7.isDirectory()) {
                     File[] var8 = var7.listFiles();
                     if (var8 != null) {
                        File[] var9 = var8;
                        int var10 = var8.length;

                        for (int var11 = 0; var11 < var10; var11++) {
                           File var12 = var9[var11];
                           if (var12.isFile()) {
                              var12.delete();
                           }
                        }
                     }
                  }

                  this.syncFolderToXqediiSetups(var5.toPath(), var6.toPath());
               }

               File var13 = new File(var3, "shaders");
               if (var13.exists() && var13.isDirectory()) {
                  this.syncFolderToXqediiSetups(
                     var13.toPath(), new File(var4, "shaders").toPath()
                  );
               }

               this.getLogger()
                  .info(
                     "[UltimateUI] Resource pack synced to XqediiSetups."
                  );
            }
         }
      }
   }

   public CustomItemHookResult deployPackToExternalPlugin() {
      if (!this.getConfig()
         .getBoolean(
            "resource-pack.hosting.external-pack.enabled",
            false
         )) {
         return null;
      } else {
         CustomItemPlugin var1 = CustomItemPlugin.Xqedii_3mduzsu(
            this.getConfig()
               .getString(
                  "resource-pack.hosting.external-pack.auto-deploy",
                  "None"
               )
         );
         if (var1 == null) {
            return null;
         } else {
            Xqedii_12bi5q4 var2 = new Xqedii_12bi5q4(this);
            File var3 = var2.Xqedii_1kxrs0m(var1);
            if (var3 != null && var3.isDirectory()) {
               File var4 = this.resolveGeneratedPackArchiveFile();
               if (var4 != null && var4.isFile()) {
                  try {
                     return var2.Xqedii_278kok9(var1, var4);
                  } catch (RuntimeException | IOException var6) {
                     this.getLogger().warning(Xqedii_2kdtvuoiyu3jh(var1.Xqedii_lho1nuv(), var6.getMessage()));
                     return null;
                  }
               } else {
                  this.getLogger().warning(Xqedii_2z4y4sef6b43h(var1.Xqedii_lho1nuv()));
                  return null;
               }
            } else {
               return null;
            }
         }
      }
   }

   public void syncFolderToXqediiSetups(Path var1, Path var2) {
      {
         try (Stream<Path> var3 = Files.walk(var1)) {
            var3.forEach(var3x -> {
               {
                  try {
                     Path var4 = var1.relativize(var3x);
                     Path var5 = var2.resolve(var4);
                     if (Files.isDirectory(var3x)) {
                        if (!Files.exists(var5)) {
                           Files.createDirectories(var5);
                        }
                     } else {
                        Files.createDirectories(var5.getParent());
                        CopyOption[] var10002 = new CopyOption[1];
                        var10002[0] = StandardCopyOption.REPLACE_EXISTING;
                        Files.copy(var3x, var5, var10002);
                     }
                  } catch (IOException var6) {
                     this.getLogger().warning(Xqedii_3s3atyp10h79f(String.valueOf(var3x.getFileName()), var6.getMessage()));
                  }
               }
            });
         } catch (IOException var8) {
            this.getLogger().warning(Xqedii_21broykjb7wt3(var8.getMessage()));
         }
      }
   }

   public long countFilesRecursively(File var1) {
      if (var1 == null || !var1.exists()) {
         return 0L;
      } else if (var1.isFile()) {
         return 1L;
      } else {
         File[] var2 = var1.listFiles();
         if (var2 != null && var2.length != 0) {
            long var3 = 0L;
            File[] var5 = var2;
            int var6 = var2.length;

            for (int var7 = 0; var7 < var6; var7++) {
               File var8 = var5[var7];
               var3 += this.countFilesRecursively(var8);
            }

            return var3;
         } else {
            return 0L;
         }
      }
   }

   public void resendResourcePackToOnlinePlayers(String var1) {
      if (var1 != null && !var1.isBlank()) {
         for (Player var3 : Bukkit.getOnlinePlayers()) {
            this.sendResolvedResourcePack(var3, var1, 1L);
         }
      }
   }

   public void sendResolvedResourcePack(Player var1, String var2, long var3) {
      if (var1 != null && var1.isOnline()) {
         String[] var10001 = new String[1];
         var10001[0] = var2;
         String var5 = this.firstNonBlank(var10001);
         if (!var5.isBlank()) {
            byte[] var6 = this.resolveResourcePackHashBytes();
            Xqedii_38o2zes.Xqedii_2sj63w1(this, var1, () -> {
               if (var1.isOnline()) {
                  Component var4 = this.resolveResourcePackPromptComponent();

                  try {
                     if (var4 == null) {
                        if (var6 != null && var6.length != 0) {
                           var1.setResourcePack(var5, var6);
                        } else {
                           var1.setResourcePack(var5);
                        }
                     } else {
                        Xqedii_nd6rkr3.Xqedii_29hmlg6(var1, var5, var6, var4, false);
                     }
                  } catch (Throwable var6x) {
                     this.getLogger().warning(Xqedii_f0k0z3esnc4g(var1.getName(), var6x.getMessage()));
                  }
               }
            }, Math.max(0L, var3));
         }
      }
   }

   public String computeResourcePackDeliverySignature(String var1) {
      {
         String var2 = this.resolveResourcePackHostingModeKey();
         String[] var10001 = new String[1];
         var10001[0] = var1;
         String var3 = this.firstNonBlank(var10001);
         StringBuilder var4 = new StringBuilder();
         var4.append("mode=").append(var2);
         var4.append("|url=").append(var3);
         if ("self-host".equals(var2)) {
            File var5 = this.resolveGeneratedPackArchiveFile();
            if (var5 != null && var5.exists() && var5.isFile()) {
               var4.append("|zip-sha256=")
                  .append(this.computeFileSha256(var5));
            } else {
               var4.append("|zip=missing");
            }
         }

         return var4.toString();
      }
   }

   public String resolveResourcePackHostingModeKey() {
      if (this.getConfig()
         .getBoolean(
            "resource-pack.hosting.external-pack.enabled",
            false
         )) {
         return "external-pack";
      } else if (this.getConfig()
         .getBoolean(
            "resource-pack.hosting.self-host.enabled",
            false
         )) {
         return "self-host";
      } else {
         return this.getConfig()
               .getBoolean(
                  "resource-pack.hosting.external-host.enabled",
                  false
               )
            ? "external-host"
            : "disabled";
      }
   }

   public File resolveGeneratedPackArchiveFile() {
      {
         File var1 = new File(
            this.getDataFolder(), "resourcepack"
         );
         File var2 = new File(var1, "generated");
         return new File(var2, "resourcepack.zip");
      }
   }

   public byte[] resolveResourcePackHashBytes() {
      if (!"self-host"
         .equals(this.resolveResourcePackHostingModeKey())) {
         return null;
      } else {
         File var1 = this.resolveGeneratedPackArchiveFile();
         if (var1 != null && var1.exists() && var1.isFile()) {
            byte[] var2 = this.computeFileDigest(var1, "SHA-1");
            return var2 != null && var2.length == 20 ? var2 : null;
         } else {
            return null;
         }
      }
   }

   public String computeFileSha256(File var1) {
      if (var1 != null && var1.exists() && var1.isFile()) {
         byte[] var2 = this.computeFileDigest(var1, "SHA-256");
         return var2 != null && var2.length != 0
            ? this.toLowerHex(var2)
            : "sha256-error";
      } else {
         return "";
      }
   }

   public byte[] computeFileDigest(File var1, String var2) {
      if (var1 != null && var1.exists() && var1.isFile()) {
         String[] var10001 = new String[1];
         var10001[0] = var2;
         String var3 = this.firstNonBlank(var10001);
         if (var3.isBlank()) {
            return null;
         } else {
            try {
               MessageDigest var4 = MessageDigest.getInstance(var3);
               byte[] var5 = new byte[8192];

               int var7;
               try (InputStream var6 = Files.newInputStream(var1.toPath())) {
                  while ((var7 = var6.read(var5)) != -1) {
                     if (var7 > 0) {
                        var4.update(var5, 0, var7);
                     }
                  }
               }

               return var4.digest();
            } catch (Throwable var11) {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   public String toLowerHex(byte[] var1) {
      if (var1 != null && var1.length != 0) {
         StringBuilder var2 = new StringBuilder(var1.length * 2);
         byte[] var3 = var1;
         int var4 = var1.length;

         for (int var5 = 0; var5 < var4; var5++) {
            byte var6 = var3[var5];
            var2.append(
               Character.forDigit(
                  var6 >> 4 & 15,
                  16
               )
            );
            var2.append(Character.forDigit(var6 & 15, 16));
         }

         return var2.toString();
      } else {
         return "";
      }
   }

   public String firstNonBlank(String... var1) {
      if (var1 != null && var1.length != 0) {
         String[] var2 = var1;
         int var3 = var1.length;

         for (int var4 = 0; var4 < var3; var4++) {
            String var5 = var2[var4];
            if (var5 != null && !var5.isBlank()) {
               return var5;
            }
         }

         return "";
      } else {
         return "";
      }
   }

   public void playLoadingSounds(Player var1) {
      if (var1 != null && var1.isOnline()) {
         float var2 = (float)Math.max(
            Double.longBitsToDouble(0L),
            this.getConfig()
               .getDouble(
                  "resource-pack.messages.sound.volume",
                  Double.longBitsToDouble(4607182418800017408L)
               )
         );
         var1.playSound(var1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, var2, Float.intBitsToFloat(1065353216));
         Xqedii_38o2zes.Xqedii_2sj63w1(
            this,
            var1,
            () -> {
               {
                  if (var1.isOnline()) {
                     var1.playSound(
                        var1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, var2, Float.intBitsToFloat(1067030938)
                     );
                  }
               }
            },
            2L
         );
         Xqedii_38o2zes.Xqedii_2sj63w1(
            this,
            var1,
            () -> {
               {
                  if (var1.isOnline()) {
                     var1.playSound(
                        var1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, var2, Float.intBitsToFloat(1069547520)
                     );
                  }
               }
            },
            4L
         );
      }
   }

   public void forceClearTitle(Player var1) {
      if (var1 != null && var1.isOnline()) {
         var1.resetTitle();
         Xqedii_nd6rkr3.Xqedii_1blyyfe(var1);
         Component var2 = Xqedii_1df0pdx.deserialize("<gray> ");
         Times var3 = Times.times(Duration.ZERO, Duration.ofMillis(50L), Duration.ZERO);
         Xqedii_nd6rkr3.Xqedii_303uu75(var1, Title.title(var2, var2, var3));
      }
   }

   public boolean isResourcePackChatMessagesEnabled() {
      {
         return this.getConfig()
            .getBoolean(
               "resource-pack.messages.chat.enabled",
               false
            );
      }
   }

   public boolean isResourcePackTitleMessagesEnabled() {
      {
         return this.getConfig()
            .getBoolean(
               "resource-pack.messages.title.enabled",
               true
            );
      }
   }

   public boolean isResourcePackSoundEnabled() {
      {
         return this.getConfig()
            .getBoolean(
               "resource-pack.messages.sound.enabled",
               true
            );
      }
   }

   public File ensureResourcePackFolder() {
      {
         File var1 = new File(
            this.getDataFolder(), "resourcepack"
         );
         if (!var1.exists()) {
            var1.mkdirs();
         }

         File var2 = new File(var1, "pack");
         int var3 = !var2.exists() ? 1 : 0;
         if (var3 != 0) {
            var2.mkdirs();
         }

         File var4 = new File(var1, "generated");
         if (!var4.exists()) {
            var4.mkdirs();
         }

         this.extractDefaultPackFiles(var2);
         this.migrateLegacyResourcePackLayout(this.getDataFolder(), var2);
         return var1;
      }
   }

   public void extractDefaultPackFiles(File var1) {
      {
         File var2 = this.getFile();
         if (var2 != null && var2.isFile()) {
            int var3 = 0;
            int var4 = 0;

            try (ZipFile var5 = new ZipFile(var2)) {
               Enumeration var6 = var5.entries();

               while (var6.hasMoreElements()) {
                  ZipEntry var7 = (ZipEntry)var6.nextElement();
                  String var8 = var7.getName();
                  if (var8.startsWith("pack/")) {
                     String var9 = var8.substring("pack/".length());
                     if (!var9.isEmpty()) {
                        File var10 = new File(var1, var9.replace((char)47, File.separatorChar));
                        if (var7.isDirectory()) {
                           var10.mkdirs();
                        } else {
                           var10.getParentFile().mkdirs();
                           if (!var10.exists()) {
                              try (InputStream var11 = var5.getInputStream(var7)) {
                                 Path var10001 = var10.toPath();
                                 CopyOption[] var10002 = new CopyOption[1];
                                 var10002[0] = StandardCopyOption.REPLACE_EXISTING;
                                 Files.copy(var11, var10001, var10002);
                                 var3++;
                              }
                           } else if (Xqedii_ydayab7(var9) && !Xqedii_3w4snhi(var5, var7, var10)) {
                              try (InputStream var21 = var5.getInputStream(var7)) {
                                 Path var22 = var10.toPath();
                                 CopyOption[] var23 = new CopyOption[1];
                                 var23[0] = StandardCopyOption.REPLACE_EXISTING;
                                 Files.copy(var21, var22, var23);
                                 var4++;
                              }
                           }
                        }
                     }
                  }
               }
            } catch (IOException var20) {
               this.getLogger().warning(Xqedii_350e0mqakgaqt(var20.getMessage()));
            }

            if (var3 > 0) {
               this.getLogger().info(Xqedii_2qby8gxm75i38(var3));
            }

            if (var4 > 0) {
               this.getLogger().info(Xqedii_1tdoqtp90yl6r(var4));
            }
         }
      }
   }

   public static boolean Xqedii_ydayab7(String var0) {
      {
         return var0.replace(File.separatorChar, (char)47)
            .contains(
               "assets/minecraft/shaders/"
            );
      }
   }

   public static boolean Xqedii_3w4snhi(ZipFile var0, ZipEntry var1, File var2) {
      {
         long var3 = var1.getSize();
         if (var3 >= 0L && var3 != var2.length()) {
            return false;
         } else {
            try {
               boolean var8;
               try (InputStream var5 = var0.getInputStream(var1)) {
                  byte[] var6 = var5.readAllBytes();
                  byte[] var7 = Files.readAllBytes(var2.toPath());
                  var8 = Arrays.equals(var6, var7);
               }

               return var8;
            } catch (IOException var11) {
               return true;
            }
         }
      }
   }

   public void migrateLegacyResourcePackLayout(File var1, File var2) {
      if (var1 != null && var2 != null) {
         if (!this.hasCanonicalResourcePackLayout(var2)) {
            File[] var3 = var1.listFiles();
            if (var3 != null && var3.length != 0) {
               int var4 = 0;
               File[] var5 = var3;
               int var6 = var3.length;

               for (int var7 = 0; var7 < var6; var7++) {
                  File var8 = var5[var7];
                  if (this.isLegacyResourcePackRootEntry(var8)) {
                     File var9 = new File(var2, var8.getName());

                     try {
                        this.copyLegacyResourcePackEntry(var8, var9);
                        var4 = 1;
                     } catch (IOException var11) {
                        this.getLogger().warning(Xqedii_kjv979sripvu(var8.getName(), var11.getMessage()));
                     }
                  }
               }

               if (var4 != 0) {
                  this.getLogger()
                     .info(
                        "[UltimateUI] Migrated legacy resource pack files into /resourcepack/pack."
                     );
               }
            }
         }
      }
   }

   public boolean hasCanonicalResourcePackLayout(File var1) {
      if (var1 == null) {
         return false;
      } else {
         File var2 = new File(var1, "pack.mcmeta");
         return ((var2.exists() && var2.isFile()
            ? 1
            : 0) != 0);
      }
   }

   public boolean isLegacyResourcePackRootEntry(File var1) {
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
                  && !this.isLegacyVersionOverlayFolder(var2)
               ? 0
               : 1) != 0);
         }
      } else {
         return false;
      }
   }

   public boolean isLegacyVersionOverlayFolder(String var1) {
      {
         return var1 != null && !var1.isBlank() && var1.matches("\\d+_[0-9A-Za-z_]+$");
      }
   }

   public void copyLegacyResourcePackEntry(File var1, File var2) throws IOException {
      if (var1 != null && var2 != null) {
         if (var1.isDirectory()) {
            this.copyLegacyResourcePackDirectory(var1, var2);
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

   public void copyLegacyResourcePackDirectory(File var1, File var2) throws IOException {
      if (var1 != null && var2 != null && var1.exists() && var1.isDirectory()) {
         if (!var2.exists()) {
            var2.mkdirs();
         }

         File[] var3 = var1.listFiles();
         if (var3 != null && var3.length != 0) {
            File[] var4 = var3;
            int var5 = var3.length;

            for (int var6 = 0; var6 < var5; var6++) {
               File var7 = var4[var6];
               if (var7 != null) {
                  this.copyLegacyResourcePackEntry(var7, new File(var2, var7.getName()));
               }
            }
         }
      }
   }

   private static /* synthetic */ String Xqedii_2b3hebqk0jdzw(String string) {
        return "[UltimateUI] resource-pack.hosting.self-host.pack-port '" + string + "' is not a number, falling back to 8123.";
    }

    private static /* synthetic */ String Xqedii_2u8wzaffphh50(String string) {
        return "[UltimateUI] Could not merge config.yml defaults: failed to read the bundled config.yml resource (" + string + ").";
    }

    private static /* synthetic */ String Xqedii_1si5bkmp4ti2h(String string) {
        return "resource-pack.hosting." + string + ".enabled";
    }

    private static /* synthetic */ String Xqedii_ziqarhyoy509(String string) {
        return "resource-pack.hosting." + string + ".enabled";
    }

    private static /* synthetic */ String Xqedii_exea7urlkrn(int n, String string) {
        return "[UltimateUI] Added " + n + " missing config.yml key(s): " + string;
    }

    private static /* synthetic */ String Xqedii_tas30m367lbi(String string) {
        return "resourcepack" + string + "pack";
    }

    private static /* synthetic */ String Xqedii_3v61hj4hww2az(String string) {
        return "contents" + string + "pack";
    }

    private static /* synthetic */ String Xqedii_2vz74886luw46(String string) {
        return "assets" + string + "minecraft";
    }

    private static /* synthetic */ String Xqedii_u24d1rsvn4cq(String string) {
        return "assets" + string + "minecraft";
    }

    private static /* synthetic */ String Xqedii_3rg4n9f91g3fc(String string, String string2) {
        return "textures" + string + "font" + string2 + "images";
    }

    private static /* synthetic */ String Xqedii_2z4y4sef6b43h(String string) {
        return "[UltimateUI] Auto-deploy to " + string + " skipped: resourcepack/generated/resourcepack.zip does not exist.";
    }

    private static /* synthetic */ String Xqedii_2kdtvuoiyu3jh(String string, String string2) {
        return "[UltimateUI] Auto-deploy to " + string + " failed: " + string2;
    }

    private static /* synthetic */ String Xqedii_21broykjb7wt3(String string) {
        return "[UltimateUI] Failed to sync folder to XqediiSetups: " + string;
    }

    private static /* synthetic */ String Xqedii_350e0mqakgaqt(String string) {
        return "[UltimateUI] Failed to extract default pack files: " + string;
    }

    private static /* synthetic */ String Xqedii_2qby8gxm75i38(int n) {
        return "[UltimateUI] Extracted " + n + " default resource pack files into /resourcepack/pack.";
    }

    private static /* synthetic */ String Xqedii_1tdoqtp90yl6r(int n) {
        return "[UltimateUI] Refreshed " + n + " shader file(s) in /resourcepack/pack to match this build. Everything else in that folder is left untouched.";
    }

    private static /* synthetic */ String Xqedii_kjv979sripvu(String string, String string2) {
        return "[UltimateUI] Failed to migrate legacy resource pack entry '" + string + "': " + string2;
    }

    private static /* synthetic */ String Xqedii_f0k0z3esnc4g(String string, String string2) {
        return "Failed to send resource pack to " + string + ": " + string2;
    }

    private static /* synthetic */ String Xqedii_3s3atyp10h79f(String string, String string2) {
        return "[UltimateUI] XqediiSetups sync: failed to copy " + string + ": " + string2;
    }
}
