package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.resourcepack.UiImageAtlasService;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

public final class Xqedii_2t253qr {
   public static final int Xqedii_iv9vf5i = 256;
   public static final int Xqedii_3et65s7 = 8;
   public static final int Xqedii_2opok8n = 57344;
   public static final int Xqedii_1wm1jgv = 63743;
   public static final int Xqedii_v420h8w = 32;
   public static final String Xqedii_32tkywz = "contents";
   public static final String Xqedii_1fba608 = "images";
   public static final String Xqedii_3287heg = "resourcepack/pack/assets/minecraft/textures/font/images";
   public static final String Xqedii_2grproe = "resourcepack/pack/assets/minecraft/font/uiimages.json";
   public static final String Xqedii_qkamv9f = "resourcepack/generated/uiimages_codepoints.properties";
   public static final Pattern Xqedii_2jae8sc;
   public static final Set<String> Xqedii_pi7q4dz;
   public String Xqedii_1woh7lz;
   public AtlasGenerationResult Xqedii_r5cqo88;

   public String Xqedii_3rv9moh(List<File> var1) {
      {
         StringBuilder var2 = new StringBuilder();
         if (var1 != null) {
            for (File var4 : var1) {
               if (var4 != null) {
                  var2.append(var4.getAbsolutePath())
                     .append((char)124)
                     .append(var4.length())
                     .append((char)124)
                     .append(var4.lastModified())
                     .append((char)10);
               }
            }
         }

         return var2.toString();
      }
   }

   public void Xqedii_a33f2m6() {
      {
         this.Xqedii_1woh7lz = null;
         this.Xqedii_r5cqo88 = null;
      }
   }

   public AtlasGenerationResult Xqedii_1hvwbjh(File var1, Logger var2) {
      if (var1 == null) {
         return new AtlasGenerationResult(Collections.emptyMap(), 0);
      } else {
         File var3 = new File(
            new File(var1, "contents"),
            "images"
         );
         if (!var3.exists()) {
            var3.mkdirs();
         }

         File var4 = new File(
            var1,
            "resourcepack/pack/assets/minecraft/textures/font/images"
         );
         if (!var4.exists()) {
            var4.mkdirs();
         }

         File var5 = new File(
            var1,
            "resourcepack/pack/assets/minecraft/font/uiimages.json"
         );
         File var6 = var5.getParentFile();
         if (var6 != null && !var6.exists()) {
            var6.mkdirs();
         }

         List var7 = this.Xqedii_uickl93(var3);
         String var8 = this.Xqedii_3rv9moh(var7);
         if (this.Xqedii_r5cqo88 != null && var8.equals(this.Xqedii_1woh7lz) && var5.isFile() && var4.isDirectory()) {
            return this.Xqedii_r5cqo88;
         } else {
            this.Xqedii_380zhrl(var4, var2);
            LinkedHashMap var9 = new LinkedHashMap();
            ArrayList var10 = new ArrayList();
            HashSet var11 = new HashSet();
            File var12 = new File(
               var1,
               "resourcepack/generated/uiimages_codepoints.properties"
            );
            LinkedHashMap var13 = this.Xqedii_1owjnro(var12, var5, var2);
            LinkedHashSet var14 = new LinkedHashSet(var13.values());
            int var15 = 0;
            int var16 = 0;

            for (File var18 : (Iterable<File>) (var7)) {

               if (var18 != null && var18.isFile()) {
                  File var19 = this.Xqedii_yju3bt4(var18, var2);
                  if (var19 != null) {
                     var19 = this.Xqedii_2uw1jty(var19, var2);
                     if (var19 != null) {
                        BufferedImage var20;
                        try {
                           var20 = ImageIO.read(var19);
                        } catch (IOException var41) {
                           if (var2 != null) {
                              var2.warning(Xqedii_26v0bzknu4t8h(var19.getName(), var41.getMessage()));
                           }
                           continue;
                        }

                        if (var20 == null) {
                           if (var2 != null) {
                              var2.warning(Xqedii_3pnhqoz3u9s3c(var19.getName()));
                           }
                        } else {
                           int var21 = Math.max(1, var20.getWidth());
                           int var22 = Math.max(1, var20.getHeight());
                           int var23 = Math.max(
                              1,
                              (int)Math.ceil(var21 / Double.longBitsToDouble(4643211215818981376L))
                           );
                           int var24 = Math.max(
                              1,
                              (int)Math.ceil(var22 / Double.longBitsToDouble(4643211215818981376L))
                           );
                           String var25 = this.Xqedii_3kqaz8t(var3, var19, var11, var2);
                           StringBuilder var26 = new StringBuilder();
                           int var27 = 0;
                           ArrayList var28 = new ArrayList(var23 * var24);

                           label142:
                           for (int var29 = 0; var29 < var24; var29++) {
                              if (var29 > 0) {
                                 var26.append((char)10);
                              }

                              for (int var30 = 0; var30 < var23; var30++) {
                                 int var31 = var30 * 256;
                                 int var32 = var29 * 256;
                                 int var33 = Math.min(256, var21 - var31);
                                 int var34 = Math.min(256, var22 - var32);
                                 if (var33 > 0 && var34 > 0) {
                                    String var35 = Xqedii_3uic2k4nmo67k(Xqedii_376339b(var25), ++var27);
                                    File var36 = new File(var4, var35);
                                    String var37 = var35.toLowerCase(Locale.ROOT);
                                    Integer var38 = (Integer)var13.get(var37);
                                    int var39;
                                    if (var38 != null
                                       && var38 >= 57344
                                       && var38 <= 63743) {
                                       var39 = var38;
                                    } else {
                                       Integer var40 = this.Xqedii_2nwjwe8(var14);
                                       if (var40 == null) {
                                          var16 = 1;
                                          break label142;
                                       }

                                       var39 = var40;
                                       var13.put(var37, var39);
                                       var14.add(var39);
                                    }

                                    String var44 = new String(Character.toChars(var39));
                                    var26.append(var44);
                                    var10.add(new FontGlyphCodepoint(var35, var39));
                                    var28.add(new ImageCropTask(var20, var31, var32, var33, var34, var36));
                                 }
                              }
                           }

                           if (!var28.isEmpty()) {
                              var15 += this.Xqedii_2zmj771(var28, var2);
                           }

                           if (var26.length() > 0) {
                              var9.put(
                                 var25.toLowerCase(Locale.ROOT), new UiImageAtlasService.GeneratedImage(var25, var21, var22, var23, var24, var26.toString())
                              );
                           }

                           if (var16 != 0) {
                              break;
                           }
                        }
                     }
                  }
               }
            }

            if (var16 != 0 && var2 != null) {
               var2.warning(
                  "[UltimateUI] UI image glyph limit reached (U+E000..U+F8FF). Remaining image tiles were skipped."
               );
            }

            this.Xqedii_1pslcum(var5, var10, var2);
            this.Xqedii_188508q(var12, var13, var2);
            AtlasGenerationResult var42 = new AtlasGenerationResult(Collections.unmodifiableMap(new LinkedHashMap<>(var9)), var15);
            this.Xqedii_1woh7lz = var16 != 0 ? null : var8;
            this.Xqedii_r5cqo88 = var16 != 0 ? null : var42;
            return var42;
         }
      }
   }

   public UiImageAtlasService.GeneratedImage Xqedii_1jkbht0(File var1, String var2, Logger var3) {
      if (var2 != null && !var2.isBlank()) {
         File var4 = new File(
            var1,
            "resourcepack/generated/uiimages_codepoints.properties"
         );
         if (!var4.exists()) {
            return null;
         } else {
            LinkedHashMap var5 = this.Xqedii_1owjnro(var4, null, var3);
            if (var5 != null && !var5.isEmpty()) {
               String var6 = Xqedii_376339b(var2);
               File var7 = new File(
                  new File(var1, "contents"),
                  "images"
               );
               File var8 = null;

               for (File var10 : this.Xqedii_uickl93(var7)) {
                  if (var10 != null && var10.isFile() && Xqedii_376339b(this.Xqedii_1lwws2m(var10.getName())).equals(var6)) {
                     var8 = var10;
                     break;
                  }
               }

               if (var8 == null) {
                  return null;
               } else {
                  BufferedImage var21;
                  try {
                     var21 = ImageIO.read(var8);
                  } catch (IOException var20) {
                     return null;
                  }

                  if (var21 == null) {
                     return null;
                  } else {
                     int var22 = Math.max(1, var21.getWidth());
                     int var11 = Math.max(1, var21.getHeight());
                     int var12 = Math.max(
                        1,
                        (int)Math.ceil(var22 / Double.longBitsToDouble(4643211215818981376L))
                     );
                     int var13 = Math.max(
                        1,
                        (int)Math.ceil(var11 / Double.longBitsToDouble(4643211215818981376L))
                     );
                     StringBuilder var14 = new StringBuilder();
                     int var15 = 0;

                     for (int var16 = 0; var16 < var13; var16++) {
                        if (var16 > 0) {
                           var14.append((char)10);
                        }

                        for (int var17 = 0; var17 < var12; var17++) {
                           String var18 = Xqedii_2qt4fvnh3isk5(var6, ++var15).toLowerCase(Locale.ROOT);
                           Integer var19 = (Integer)var5.get(var18);
                           if (var19 == null) {
                              return null;
                           }

                           var14.append(new String(Character.toChars(var19)));
                        }
                     }

                     return new UiImageAtlasService.GeneratedImage(var6, var22, var11, var12, var13, var14.toString());
                  }
               }
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   public Integer Xqedii_2nwjwe8(LinkedHashSet<Integer> var1) {
      {
         for (int var2 = 57344; var2 <= 63743; var2++) {
            if (!var1.contains(var2)) {
               return var2;
            }
         }

         return null;
      }
   }

   public LinkedHashMap<String, Integer> Xqedii_1owjnro(File var1, File var2, Logger var3) {
      {
         LinkedHashMap var4 = new LinkedHashMap();
         if (var1 != null && var1.exists() && var1.isFile()) {
            Properties var5 = new Properties();

            try (InputStream var6 = Files.newInputStream(var1.toPath())) {
               var5.load(var6);
            } catch (Exception var16) {
               if (var3 != null) {
                  var3.warning(Xqedii_2s7pw19rius57(var16.getMessage()));
               }

               return var4;
            }

            ArrayList var17 = new ArrayList<>(var5.stringPropertyNames());
            var17.sort(String.CASE_INSENSITIVE_ORDER);
            LinkedHashSet var7 = new LinkedHashSet();

            for (String var9 : (Iterable<String>) (var17)) {

               if (var9 != null && !var9.isBlank()) {
                  String var10 = var5.getProperty(var9);
                  if (var10 != null && !var10.isBlank()) {
                     int var11;
                     try {
                        var11 = Integer.parseInt(var10.trim());
                     } catch (NumberFormatException var15) {
                        continue;
                     }

                     if (var11 >= 57344
                        && var11 <= 63743
                        && var7.add(var11)) {
                        var4.put(var9, var11);
                     }
                  }
               }
            }

            if (var4.isEmpty()) {
               var4.putAll(this.Xqedii_1ib56zk(var2, var3));
            }

            return var4;
         } else {
            var4.putAll(this.Xqedii_1ib56zk(var2, var3));
            return var4;
         }
      }
   }

   public LinkedHashMap<String, Integer> Xqedii_1ib56zk(File var1, Logger var2) {
      {
         LinkedHashMap var3 = new LinkedHashMap();
         if (var1 != null && var1.exists() && var1.isFile()) {
            String var4;
            try {
               var4 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            } catch (IOException var9) {
               if (var2 != null) {
                  var2.warning(Xqedii_276ulliianf8n(var9.getMessage()));
               }

               return var3;
            }

            LinkedHashSet var5 = new LinkedHashSet();
            Matcher var6 = Xqedii_2jae8sc.matcher(var4);

            while (var6.find()) {
               String[] var10001 = new String[1];
               var10001[0] = var6.group(1);
               String var7 = this.Xqedii_1dcr7gg(var10001).trim().toLowerCase(Locale.ROOT);
               if (!var7.isBlank()) {
                  String[] var10002 = new String[1];
                  var10002[0] = var6.group(2);
                  Integer var8 = this.Xqedii_3ib5sdb(this.Xqedii_1dcr7gg(var10002));
                  if (var8 != null
                     && var8 >= 57344
                     && var8 <= 63743
                     && var5.add(var8)) {
                     var3.put(var7, var8);
                  }
               }
            }

            return var3;
         } else {
            return var3;
         }
      }
   }

   public Integer Xqedii_3ib5sdb(String var1) {
      {
         String[] var10001 = new String[1];
         var10001[0] = var1;
         String var2 = this.Xqedii_1dcr7gg(var10001);
         if (var2.isBlank()) {
            return null;
         } else {
            StringBuilder var3 = new StringBuilder(var2.length());

            for (int var4 = 0; var4 < var2.length(); var4++) {
               char var5 = var2.charAt(var4);
               if (var5 == 92
                  && var4 + 5 < var2.length()
                  && var2.charAt(var4 + 1) == 117) {
                  String var6 = var2.substring(
                     var4 + 2, var4 + 6
                  );

                  try {
                     int var7 = Integer.parseInt(var6, 16);
                     var3.append((char)var7);
                     var4 += 5;
                     continue;
                  } catch (NumberFormatException var8) {
                  }
               }

               var3.append(var5);
            }

            return var3.length() == 0 ? null : var3.codePointAt(0);
         }
      }
   }

   public String Xqedii_1dcr7gg(String... var1) {
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

   public void Xqedii_188508q(File var1, Map<String, Integer> var2, Logger var3) {
      if (var1 != null && var2 != null) {
         File var4 = var1.getParentFile();
         if (var4 != null && !var4.exists()) {
            var4.mkdirs();
         }

         Properties var5 = new Properties();
         ArrayList var6 = new ArrayList(var2.keySet());
         var6.sort(String.CASE_INSENSITIVE_ORDER);

         for (String var8 : (Iterable<String>) (var6)) {

            if (var8 != null && !var8.isBlank()) {
               Integer var9 = (Integer)var2.get(var8);
               if (var9 != null) {
                  var5.setProperty(var8, Integer.toString(var9));
               }
            }
         }

         try (OutputStream var13 = Files.newOutputStream(var1.toPath())) {
            var5.store(
               var13,
               "UltimateUI ui image tile -> codepoint map"
            );
         } catch (Exception var12) {
            if (var3 != null) {
               var3.warning(Xqedii_qbfmparc9pmj(var12.getMessage()));
            }
         }
      }
   }

   public List<File> Xqedii_uickl93(File var1) {
      {
         ArrayList var2 = new ArrayList();
         this.Xqedii_2jk852a(var1, var2, 0);
         var2.sort(Comparator.comparing(File::getAbsolutePath, String.CASE_INSENSITIVE_ORDER));
         return var2;
      }
   }

   public void Xqedii_2jk852a(File var1, List<File> var2, int var3) {
      if (var1 != null && var2 != null && var3 <= 8 && var1.isDirectory()) {
         File[] var4 = var1.listFiles();
         if (var4 != null && var4.length != 0) {
            ArrayList var5 = new ArrayList();
            File[] var6 = var4;
            int var7 = var4.length;

            for (int var8 = 0; var8 < var7; var8++) {
               File var9 = var6[var8];
               if (var9 != null) {
                  if (var9.isDirectory()) {
                     if (!var9.isHidden() && !var9.getName().startsWith(".")) {
                        var5.add(var9);
                     }
                  } else if (var9.isFile() && this.Xqedii_1yty2s4(var9)) {
                     var2.add(var9);
                  }
               }
            }

            var5.sort(Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER));

            for (File var11 : (Iterable<File>) (var5)) {

               this.Xqedii_2jk852a(var11, var2, var3 + 1);
            }
         }
      }
   }

   public boolean Xqedii_1yty2s4(File var1) {
      if (var1 == null) {
         return false;
      } else {
         String var2 = var1.getName().toLowerCase(Locale.ROOT);
         int var3 = var2.lastIndexOf(46);
         return var3 > 0 && var3 < var2.length() - 1 && Xqedii_pi7q4dz.contains(var2.substring(var3 + 1));
      }
   }

   public static String Xqedii_32qc6t2(File var0, File var1) {
      if (var0 != null && var1 != null) {
         File var2 = var1.getParentFile();
         if (var2 == null) {
            return "";
         } else {
            String var3 = var0.getAbsolutePath();
            String var4 = var2.getAbsolutePath();
            if (var4.equalsIgnoreCase(var3)) {
               return "";
            } else {
               return var4.length() > var3.length() + 1
                     && var4.substring(0, var3.length()).equalsIgnoreCase(var3)
                  ? var4.substring(var3.length() + 1)
                     .replace((char)92, (char)47)
                  : "";
            }
         }
      } else {
         return "";
      }
   }

   public String Xqedii_3kqaz8t(File var1, File var2, Set<String> var3, Logger var4) {
      {
         String var5 = Xqedii_376339b(this.Xqedii_1lwws2m(var2.getName()));
         if (var3.add(var5.toLowerCase(Locale.ROOT))) {
            return var5;
         } else {
            String var6 = Xqedii_32qc6t2(var1, var2);
            String var7 = var6.isBlank()
               ? var5
               : Xqedii_376339b(
                  Xqedii_ai78xbs9iq9l(
                     var6.replace((char)47, (char)95), var5
                  )
               );
            if (var3.add(var7.toLowerCase(Locale.ROOT))) {
               if (var4 != null) {
                  var4.warning(Xqedii_258r0mb47rmib(var5, var7, var2.getPath()));
               }

               return var7;
            } else {
               int var8 = 2;
               String var9 = Xqedii_8q8noy3zr1k3(var7, var8);

               while (!var3.add(var9.toLowerCase(Locale.ROOT))) {
                  var9 = Xqedii_1421ikqj10oct(var7, ++var8);
               }

               if (var4 != null) {
                  var4.warning(Xqedii_2lwdi6stw6j4g(var5, var9, var2.getPath()));
               }

               return var9;
            }
         }
      }
   }

   public File Xqedii_yju3bt4(File var1, Logger var2) {
      if (var1 != null && var1.isFile()) {
         String var3 = var1.getName();
         int var4 = var3.lastIndexOf(46);
         String var5 = var4 > 0
            ? var3.substring(var4 + 1).toLowerCase(Locale.ROOT)
            : "";
         if (var5.equals("png")) {
            return var1;
         } else {
            BufferedImage var6;
            try {
               var6 = ImageIO.read(var1);
            } catch (IOException var17) {
               if (var2 != null) {
                  var2.warning(Xqedii_1sg8akn48hfdf(var3, var17.getMessage()));
               }

               return null;
            }

            if (var6 == null) {
               if (var2 != null) {
                  var2.warning(Xqedii_1xky3184ayo26(var3));
               }

               return null;
            } else {
               String var7 = var4 > 0 ? var3.substring(0, var4) : var3;
               File var8 = new File(var1.getParentFile(), Xqedii_1oxis0ciolfxi(var7));
               BufferedImage var9;
               if (var6.getType() == 2) {
                  var9 = var6;
               } else {
                  var9 = new BufferedImage(var6.getWidth(), var6.getHeight(), 2);
                  Graphics2D var10 = var9.createGraphics();

                  try {
                     var10.setComposite(AlphaComposite.Src);
                     var10.drawImage(var6, 0, 0, null);
                  } finally {
                     var10.dispose();
                  }
               }

               try {
                  ImageIO.write(var9, "png", var8);
               } catch (IOException var16) {
                  if (var2 != null) {
                     var2.warning(Xqedii_rgok6a4z7hk5(var8.getName(), var16.getMessage()));
                  }

                  return null;
               }

               if (!var1.delete() && var2 != null) {
                  var2.warning(Xqedii_2l1s8ebl2pmfw(var3));
               }

               if (var2 != null) {
                  var2.info(Xqedii_aoyt7f6y1jaj(var3, var8.getName()));
               }

               return var8;
            }
         }
      } else {
         return null;
      }
   }

   public File Xqedii_2uw1jty(File var1, Logger var2) {
      if (var1 != null && var1.isFile()) {
         String[] var10001 = new String[1];
         var10001[0] = var1.getName();
         String var3 = this.Xqedii_1dcr7gg(var10001);
         if (var3.isBlank()) {
            return var1;
         } else {
            int var4 = var3.lastIndexOf(46);
            String var5 = var4 > 0 ? var3.substring(0, var4) : var3;
            String var6 = var4 > 0 && var4 < var3.length() - 1
               ? var3.substring(var4 + 1).toLowerCase(Locale.ROOT)
               : "png";
            String var7 = Xqedii_376339b(var5);
            File var8 = this.Xqedii_2kw0xer(var1.getParentFile(), var7, var6, var1);
            if (var8 == null) {
               return var1;
            } else if (var8.getAbsolutePath().equalsIgnoreCase(var1.getAbsolutePath())) {
               return var1;
            } else {
               try {
                  Files.move(var1.toPath(), var8.toPath());
                  if (var2 != null) {
                     var2.info(Xqedii_98lx2t4ot9xk(var1.getName(), var8.getName()));
                  }

                  return var8;
               } catch (IOException var10) {
                  if (var2 != null) {
                     var2.warning(Xqedii_4a7xmad7xsvb(var1.getName(), var10.getMessage()));
                  }

                  return var1;
               }
            }
         }
      } else {
         return null;
      }
   }

   public File Xqedii_2kw0xer(File var1, String var2, String var3, File var4) {
      if (var1 == null) {
         return var4;
      } else {
         String[] var10001 = new String[1];
         var10001[0] = var3;
         String var5 = this.Xqedii_1dcr7gg(var10001).toLowerCase(Locale.ROOT);
         if (var5.isBlank()) {
            var5 = "png";
         }

         String var6 = Xqedii_376339b(var2);
         File var7 = new File(var1, Xqedii_38ztl35dkkdrl(var6, var5));

         for (int var8 = 2;
            var7.exists() && !var7.getAbsolutePath().equalsIgnoreCase(var4.getAbsolutePath());
            var8++
         ) {
            var7 = new File(var1, Xqedii_1cqb4mpe5nrbu(var6, var8, var5));
         }

         return var7;
      }
   }

   public int Xqedii_2zmj771(List<ImageCropTask> var1, Logger var2) {
      if (var1 != null && !var1.isEmpty()) {
         AtomicInteger var3 = new AtomicInteger(0);
         var1.parallelStream().forEach(var3x -> {
            {
               if (this.Xqedii_1kaqubp(var3x.source(), var3x.sourceX(), var3x.sourceY(), var3x.copyWidth(), var3x.copyHeight(), var3x.destination(), var2)) {
                  var3.incrementAndGet();
               }
            }
         });
         return var3.get();
      } else {
         return 0;
      }
   }

   public void Xqedii_380zhrl(File var1, Logger var2) {
      if (var1 != null) {
         if (!var1.exists()) {
            var1.mkdirs();
         } else {
            File[] var3 = var1.listFiles();
            if (var3 != null) {
               File[] var4 = var3;
               int var5 = var3.length;

               for (int var6 = 0; var6 < var5; var6++) {
                  File var7 = var4[var6];
                  if (var7 != null) {
                     if (var7.isDirectory()) {
                        this.Xqedii_380zhrl(var7, var2);
                     }

                     if (!var7.delete() && var2 != null) {
                        var2.warning(Xqedii_33afhzhtidchh(var7.getAbsolutePath()));
                     }
                  }
               }
            }
         }
      }
   }

   public boolean Xqedii_1kaqubp(BufferedImage var1, int var2, int var3, int var4, int var5, File var6, Logger var7) {
      {
         int[] var8 = new int[var4 * var5];
         var1.getRGB(var2, var3, var4, var5, var8, 0, var4);
         BufferedImage var9 = new BufferedImage(
            256,
            256,
            2
         );
         var9.setRGB(
            0,
            0,
            var4,
            var5,
            var8,
            0,
            var4
         );
         int var10 = Math.max(
            0,
            Math.min(255, var4 - 1)
         );
         int var11 = Math.max(
            0,
            Math.min(255, var5 - 1)
         );
         int var12 = var9.getRGB(var10, var11);
         int var13 = var12 & 16777215;
         int var14 = var12 >>> 24 & 255;
         int var15 = Math.max(var14, 32);
         var9.setRGB(var10, var11, var15 << 24 | var13);

         try {
            ImageIO.write(var9, "png", var6);
            return true;
         } catch (IOException var17) {
            if (var7 != null) {
               var7.warning(Xqedii_epp9yhdp0hwo(var6.getName(), var17.getMessage()));
            }

            return false;
         }
      }
   }

   public void Xqedii_1pslcum(File var1, List<FontGlyphCodepoint> var2, Logger var3) {
      {
         StringBuilder var4 = new StringBuilder();
         var4.append("{\n");
         var4.append("  \"providers\": [\n");
         if (var2 != null) {
            for (int var5 = 0; var5 < var2.size(); var5++) {
               FontGlyphCodepoint var6 = (FontGlyphCodepoint)var2.get(var5);
               var4.append(
                     "    {\"type\": \"bitmap\", \"file\": \"minecraft:font/images/"
                  )
                  .append(this.Xqedii_253p1ce(var6.fileName()))
                  .append("\", \"ascent\": ")
                  .append(256)
                  .append(", \"height\": ")
                  .append(256)
                  .append(", \"chars\": [\"")
                  .append(this.Xqedii_2zo5t1v(var6.codepoint()))
                  .append("\"]}");
               if (var5 < var2.size() - 1) {
                  var4.append((char)44);
               }

               var4.append((char)10);
            }
         }

         var4.append("  ]\n");
         var4.append("}\n");

         try {
            Files.writeString(var1.toPath(), var4.toString(), StandardCharsets.UTF_8);
         } catch (IOException var7) {
            if (var3 != null) {
               var3.warning(Xqedii_1x3ecg2wj70z2(var7.getMessage()));
            }
         }
      }
   }

   public String Xqedii_1lwws2m(String var1) {
      if (var1 != null && !var1.isBlank()) {
         int var2 = var1.lastIndexOf(46);
         return var2 <= 0 ? var1 : var1.substring(0, var2);
      } else {
         return "image";
      }
   }

   public static String Xqedii_376339b(String var0) {
      if (var0 != null && !var0.isBlank()) {
         StringBuilder var1 = new StringBuilder(var0.length());
         int var2 = 0;
         char[] var3 = var0.toCharArray();
         int var4 = var3.length;

         for (int var5 = 0; var5 < var4; var5++) {
            char var6 = var3[var5];
            if (!Character.isWhitespace(var6)
               && var6 != 95
               && var6 != 45) {
               char var7 = Character.toLowerCase(var6);
               if (var7 >= 97 && var7 <= 122
                  || Character.isDigit(var7)) {
                  var1.append(var7);
                  var2 = 0;
               }
            } else if (var2 == 0) {
               var1.append((char)95);
               var2 = 1;
            }
         }

         String var8 = var1.toString();

         while (var8.startsWith("_")) {
            var8 = var8.substring(1);
         }

         while (var8.endsWith("_")) {
            var8 = var8.substring(0, var8.length() - 1);
         }

         return var8.isBlank() ? "image" : var8;
      } else {
         return "image";
      }
   }

   public String Xqedii_253p1ce(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         StringBuilder var2 = new StringBuilder(var1.length() + 8);
         char[] var3 = var1.toCharArray();
         int var4 = var3.length;

         for (int var5 = 0; var5 < var4; var5++) {
            char var6 = var3[var5];
            switch (var6) {
               case '\b':
                  var2.append("\\b");
                  break;
               case '\t':
                  var2.append("\\t");
                  break;
               case '\n':
                  var2.append("\\n");
                  break;
               case '\f':
                  var2.append("\\f");
                  break;
               case '\r':
                  var2.append("\\r");
                  break;
               case '"':
                  var2.append("\\\"");
                  break;
               case '\\':
                  var2.append("\\\\");
                  break;
               default:
                  if (var6 < 32) {
                     String var10001 = "\\u%04X";
                     Object[] var10002 = new Object[1];
                     var10002[0] = Integer.valueOf(var6);
                     var2.append(String.format(var10001, var10002));
                  } else {
                     var2.append(var6);
                  }
            }
         }

         return var2.toString();
      } else {
         return "";
      }
   }

   public String Xqedii_2zo5t1v(int var1) {
      if (Character.isBmpCodePoint(var1)) {
         String var3 = "\\u%04X";
         Object[] var4 = new Object[1];
         var4[0] = var1;
         return String.format(var3, var4);
      } else {
         char[] var2 = Character.toChars(var1);
         String var10000 = "\\u%04X\\u%04X";
         Object[] var10001 = new Object[2];
         var10001[0] = Integer.valueOf(var2[0]);
         var10001[1] = Integer.valueOf(var2[1]);
         return String.format(var10000, var10001);
      }
   }

   static {
      {
         Xqedii_2jae8sc = Pattern.compile(
            "\"file\"\\s*:\\s*\"minecraft:font/images/([^\"]+)\"[^\\n\\r]*?\"chars\"\\s*:\\s*\\[\"([^\"]+)\"\\]"
         );
         HashSet var0 = new HashSet();
         var0.add("png");
         var0.add("jpg");
         var0.add("jpeg");
         var0.add("bmp");
         var0.add("gif");
         var0.add("webp");
         String[] var1 = ImageIO.getReaderFileSuffixes();
         int var2 = var1.length;

         for (int var3 = 0; var3 < var2; var3++) {
            String var4 = var1[var3];
            if (var4 != null && !var4.isBlank()) {
               var0.add(var4.toLowerCase(Locale.ROOT));
            }
         }

         Xqedii_pi7q4dz = Collections.unmodifiableSet(var0);
      }
   }

   private static /* synthetic */ String Xqedii_26v0bzknu4t8h(String string, String string2) {
        return "[UltimateUI] Failed to read image '" + string + "': " + string2;
    }

    private static /* synthetic */ String Xqedii_3pnhqoz3u9s3c(String string) {
        return "[UltimateUI] Unsupported image format for '" + string + "'.";
    }

    private static /* synthetic */ String Xqedii_3uic2k4nmo67k(String string, int n) {
        return string + "_" + n + ".png";
    }

    private static /* synthetic */ String Xqedii_2qt4fvnh3isk5(String string, int n) {
        return string + "_" + n + ".png";
    }

    private static /* synthetic */ String Xqedii_2s7pw19rius57(String string) {
        return "[UltimateUI] Failed to read ui image codepoint map: " + string;
    }

    private static /* synthetic */ String Xqedii_276ulliianf8n(String string) {
        return "[UltimateUI] Failed to read existing uiimages font file: " + string;
    }

    private static /* synthetic */ String Xqedii_qbfmparc9pmj(String string) {
        return "[UltimateUI] Failed to write ui image codepoint map: " + string;
    }

    private static /* synthetic */ String Xqedii_ai78xbs9iq9l(String string, String string2) {
        return string + "_" + string2;
    }

    private static /* synthetic */ String Xqedii_258r0mb47rmib(String string, String string2, String string3) {
        return "[UltimateUI] Duplicate ui image name '" + string + "'; using '" + string2 + "' for " + string3 + ".";
    }

    private static /* synthetic */ String Xqedii_8q8noy3zr1k3(String string, int n) {
        return string + "_" + n;
    }

    private static /* synthetic */ String Xqedii_1421ikqj10oct(String string, int n) {
        return string + "_" + n;
    }

    private static /* synthetic */ String Xqedii_2lwdi6stw6j4g(String string, String string2, String string3) {
        return "[UltimateUI] Duplicate ui image name '" + string + "'; using '" + string2 + "' for " + string3 + ".";
    }

    private static /* synthetic */ String Xqedii_1sg8akn48hfdf(String string, String string2) {
        return "[UltimateUI] Failed to decode image '" + string + "' for PNG conversion: " + string2;
    }

    private static /* synthetic */ String Xqedii_1xky3184ayo26(String string) {
        return "[UltimateUI] No ImageIO reader available for '" + string + "' (unsupported format). Skipping.";
    }

    private static /* synthetic */ String Xqedii_1oxis0ciolfxi(String string) {
        return string + ".png";
    }

    private static /* synthetic */ String Xqedii_rgok6a4z7hk5(String string, String string2) {
        return "[UltimateUI] Failed to write converted PNG '" + string + "': " + string2;
    }

    private static /* synthetic */ String Xqedii_2l1s8ebl2pmfw(String string) {
        return "[UltimateUI] Converted '" + string + "' to PNG but failed to delete the original file.";
    }

    private static /* synthetic */ String Xqedii_aoyt7f6y1jaj(String string, String string2) {
        return "[UltimateUI] Auto-converted '" + string + "' -> '" + string2 + "'.";
    }

    private static /* synthetic */ String Xqedii_98lx2t4ot9xk(String string, String string2) {
        return "[UltimateUI] Normalized image file name '" + string + "' -> '" + string2 + "'.";
    }

    private static /* synthetic */ String Xqedii_4a7xmad7xsvb(String string, String string2) {
        return "[UltimateUI] Failed to normalize image file name '" + string + "': " + string2;
    }

    private static /* synthetic */ String Xqedii_38ztl35dkkdrl(String string, String string2) {
        return string + "." + string2;
    }

    private static /* synthetic */ String Xqedii_1cqb4mpe5nrbu(String string, int n, String string2) {
        return string + "_" + n + "." + string2;
    }

    private static /* synthetic */ String Xqedii_33afhzhtidchh(String string) {
        return "[UltimateUI] Failed to delete old generated image file: " + string;
    }

    private static /* synthetic */ String Xqedii_epp9yhdp0hwo(String string, String string2) {
        return "[UltimateUI] Failed to write generated tile '" + string + "': " + string2;
    }

    private static /* synthetic */ String Xqedii_1x3ecg2wj70z2(String string) {
        return "[UltimateUI] Failed to write uiimages font file: " + string;
    }
}
