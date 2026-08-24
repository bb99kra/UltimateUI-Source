package dev.xqedii.ultimateUI.service.resourcepack;

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

public final class UiImageAtlasService {
   private static final int TILE_SIZE = 256;
   private static final int CODEPOINT_START = 57344;
   private static final int CODEPOINT_END = 63743;
   private static final int ADVANCE_MARKER_ALPHA = 32;
   private static final String CONTENTS_FOLDER = "contents";
   private static final String IMAGES_FOLDER = "images";
   private static final String TARGET_TEXTURES_PATH = "resourcepack/pack/assets/minecraft/textures/font/images";
   private static final String TARGET_FONT_PATH = "resourcepack/pack/assets/minecraft/font/uiimages.json";
   private static final String CODEPOINT_MAP_PATH = "resourcepack/generated/uiimages_codepoints.properties";
   private static final Pattern LEGACY_FONT_PROVIDER_PATTERN = Pattern.compile(
      "\"file\"\\s*:\\s*\"minecraft:font/images/([^\"]+)\"[^\\n\\r]*?\"chars\"\\s*:\\s*\\[\"([^\"]+)\"\\]"
   );
   private static final Set<String> SUPPORTED_INPUT_EXTENSIONS;

   public UiImageAtlasService.BuildResult rebuild(File var1, Logger var2) {
      if (var1 == null) {
         return new UiImageAtlasService.BuildResult(Collections.emptyMap(), 0);
      } else {
         File var3 = new File(new File(var1, "contents"), "images");
         if (!var3.exists()) {
            var3.mkdirs();
         }

         File var4 = new File(var1, "resourcepack/pack/assets/minecraft/textures/font/images");
         if (!var4.exists()) {
            var4.mkdirs();
         }

         File var5 = new File(var1, "resourcepack/pack/assets/minecraft/font/uiimages.json");
         File var6 = var5.getParentFile();
         if (var6 != null && !var6.exists()) {
            var6.mkdirs();
         }

         this.clearDirectory(var4, var2);
         List var7 = this.listSourceImages(var3);
         LinkedHashMap var8 = new LinkedHashMap();
         ArrayList var9 = new ArrayList();
         File var10 = new File(var1, "resourcepack/generated/uiimages_codepoints.properties");
         LinkedHashMap var11 = this.loadCodepointMap(var10, var5, var2);
         LinkedHashSet var12 = new LinkedHashSet(var11.values());
         int var13 = 0;
         boolean var14 = false;

         for (File var16 : (List<File>)(List)var7) {
            if (var16 != null && var16.isFile()) {
               File var17 = this.ensureSourceIsPng(var16, var2);
               if (var17 != null) {
                  var17 = this.normalizeSourceImageFileName(var17, var2);
                  if (var17 != null) {
                     BufferedImage var18;
                     try {
                        var18 = ImageIO.read(var17);
                     } catch (IOException var39) {
                        if (var2 != null) {
                           var2.warning("[UltimateUI] Failed to read image '" + var17.getName() + "': " + var39.getMessage());
                        }
                        continue;
                     }

                     if (var18 == null) {
                        if (var2 != null) {
                           var2.warning("[UltimateUI] Unsupported image format for '" + var17.getName() + "'.");
                        }
                     } else {
                        int var19 = Math.max(1, var18.getWidth());
                        int var20 = Math.max(1, var18.getHeight());
                        int var21 = Math.max(1, (int)Math.ceil((double)var19 / 256.0));
                        int var22 = Math.max(1, (int)Math.ceil((double)var20 / 256.0));
                        String var23 = normalizeImageBaseName(this.stripExtension(var17.getName()));
                        StringBuilder var24 = new StringBuilder();
                        int var25 = 0;
                        ArrayList var26 = new ArrayList(var21 * var22);

                        label118:
                        for (int var27 = 0; var27 < var22; var27++) {
                           if (var27 > 0) {
                              var24.append('\n');
                           }

                           for (int var28 = 0; var28 < var21; var28++) {
                              int var29 = var28 * 256;
                              int var30 = var27 * 256;
                              int var31 = Math.min(256, var19 - var29);
                              int var32 = Math.min(256, var20 - var30);
                              if (var31 > 0 && var32 > 0) {
                                 String var33 = normalizeImageBaseName(var23) + "_" + ++var25 + ".png";
                                 File var34 = new File(var4, var33);
                                 String var35 = var33.toLowerCase(Locale.ROOT);
                                 Integer var36 = (Integer)var11.get(var35);
                                 int var37;
                                 if (var36 != null && var36 >= 57344 && var36 <= 63743) {
                                    var37 = var36;
                                 } else {
                                    Integer var38 = this.allocateCodepoint(var12);
                                    if (var38 == null) {
                                       var14 = true;
                                       break label118;
                                    }

                                    var37 = var38;
                                    var11.put(var35, Integer.valueOf(var37));
                                    var12.add(Integer.valueOf(var37));
                                 }

                                 String var41 = new String(Character.toChars(var37));
                                 var24.append(var41);
                                 var9.add(new UiImageAtlasService.ProviderEntry(var33, var37));
                                 var26.add(new UiImageAtlasService.TileWriteTask(var18, var29, var30, var31, var32, var34));
                              }
                           }
                        }

                        if (!var26.isEmpty()) {
                           var13 += this.writeTilesInParallel(var26, var2);
                        }

                        if (var24.length() > 0) {
                           var8.put(var23.toLowerCase(Locale.ROOT), new UiImageAtlasService.GeneratedImage(var23, var19, var20, var21, var22, var24.toString()));
                        }

                        if (var14) {
                           break;
                        }
                     }
                  }
               }
            }
         }

         if (var14 && var2 != null) {
            var2.warning("[UltimateUI] UI image glyph limit reached (U+E000..U+F8FF). Remaining image tiles were skipped.");
         }

         this.writeFontDefinition(var5, var9, var2);
         this.saveCodepointMap(var10, var11, var2);
         return new UiImageAtlasService.BuildResult(Collections.unmodifiableMap(new LinkedHashMap<>(var8)), var13);
      }
   }

   public UiImageAtlasService.GeneratedImage resolveFromPersistedCodepoints(File var1, String var2, Logger var3) {
      if (var2 != null && !var2.isBlank()) {
         File var4 = new File(var1, "resourcepack/generated/uiimages_codepoints.properties");
         if (!var4.exists()) {
            return null;
         } else {
            LinkedHashMap var5 = this.loadCodepointMap(var4, null, var3);
            if (var5 != null && !var5.isEmpty()) {
               String var6 = normalizeImageBaseName(var2);
               File var7 = new File(new File(var1, "contents"), "images");
               File var8 = null;
               File[] var9 = var7.listFiles();
               if (var9 != null) {
                  for (File var13 : var9) {
                     if (var13.isFile()) {
                        String var14 = var13.getName();
                        int var15 = var14.lastIndexOf(46);
                        if (var15 > 0) {
                           String var16 = var14.substring(var15 + 1).toLowerCase(Locale.ROOT);
                           if (SUPPORTED_INPUT_EXTENSIONS.contains(var16)) {
                              String var17 = var14.substring(0, var15);
                              if (normalizeImageBaseName(var17).equals(var6)) {
                                 var8 = var13;
                                 break;
                              }
                           }
                        }
                     }
                  }
               }

               if (var8 == null) {
                  return null;
               } else {
                  BufferedImage var22;
                  try {
                     var22 = ImageIO.read(var8);
                  } catch (IOException var21) {
                     return null;
                  }

                  if (var22 == null) {
                     return null;
                  } else {
                     int var23 = Math.max(1, var22.getWidth());
                     int var24 = Math.max(1, var22.getHeight());
                     int var25 = Math.max(1, (int)Math.ceil((double)var23 / 256.0));
                     int var26 = Math.max(1, (int)Math.ceil((double)var24 / 256.0));
                     StringBuilder var27 = new StringBuilder();
                     int var28 = 0;

                     for (int var29 = 0; var29 < var26; var29++) {
                        if (var29 > 0) {
                           var27.append('\n');
                        }

                        for (int var18 = 0; var18 < var25; var18++) {
                           String var19 = (var6 + "_" + ++var28 + ".png").toLowerCase(Locale.ROOT);
                           Integer var20 = (Integer)var5.get(var19);
                           if (var20 == null) {
                              return null;
                           }

                           var27.append(new String(Character.toChars(var20)));
                        }
                     }

                     return new UiImageAtlasService.GeneratedImage(var6, var23, var24, var25, var26, var27.toString());
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

   private Integer allocateCodepoint(LinkedHashSet<Integer> var1) {
      for (int var2 = 57344; var2 <= 63743; var2++) {
         if (!var1.contains(Integer.valueOf(var2))) {
            return var2;
         }
      }

      return null;
   }

   private LinkedHashMap<String, Integer> loadCodepointMap(File var1, File var2, Logger var3) {
      LinkedHashMap var4 = new LinkedHashMap();
      if (var1 != null && var1.exists() && var1.isFile()) {
         Properties var5 = new Properties();

         try (InputStream var6 = Files.newInputStream(var1.toPath())) {
            var5.load(var6);
         } catch (Exception var16) {
            if (var3 != null) {
               var3.warning("[UltimateUI] Failed to read ui image codepoint map: " + var16.getMessage());
            }

            return var4;
         }

         ArrayList var17 = new ArrayList<>(var5.stringPropertyNames());
         var17.sort(String.CASE_INSENSITIVE_ORDER);
         LinkedHashSet var7 = new LinkedHashSet();

         for (Object var9_raw : var17) {
            String var9 = var9_raw != null ? var9_raw.toString() : null;
            if (var9 != null && !var9.isBlank()) {
               String var10 = var5.getProperty(var9);
               if (var10 != null && !var10.isBlank()) {
                  int var11;
                  try {
                     var11 = Integer.parseInt(var10.trim());
                  } catch (NumberFormatException var15) {
                     continue;
                  }

                  if (var11 >= 57344 && var11 <= 63743 && var7.add(Integer.valueOf(var11))) {
                     var4.put(var9, Integer.valueOf(var11));
                  }
               }
            }
         }

         if (var4.isEmpty()) {
            var4.putAll(this.loadCodepointMapFromExistingFont(var2, var3));
         }

         return var4;
      } else {
         var4.putAll(this.loadCodepointMapFromExistingFont(var2, var3));
         return var4;
      }
   }

   private LinkedHashMap<String, Integer> loadCodepointMapFromExistingFont(File var1, Logger var2) {
      LinkedHashMap var3 = new LinkedHashMap();
      if (var1 != null && var1.exists() && var1.isFile()) {
         String var4;
         try {
            var4 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
         } catch (IOException var9) {
            if (var2 != null) {
               var2.warning("[UltimateUI] Failed to read existing uiimages font file: " + var9.getMessage());
            }

            return var3;
         }

         LinkedHashSet var5 = new LinkedHashSet();
         Matcher var6 = LEGACY_FONT_PROVIDER_PATTERN.matcher(var4);

         while (var6.find()) {
            String var7 = this.firstNonBlank(var6.group(1)).trim().toLowerCase(Locale.ROOT);
            if (!var7.isBlank()) {
               Integer var8 = this.decodeEscapedGlyphCodepoint(this.firstNonBlank(var6.group(2)));
               if (var8 != null && var8 >= 57344 && var8 <= 63743 && var5.add(var8)) {
                  var3.put(var7, var8);
               }
            }
         }

         return var3;
      } else {
         return var3;
      }
   }

   private Integer decodeEscapedGlyphCodepoint(String var1) {
      String var2 = this.firstNonBlank(var1);
      if (var2.isBlank()) {
         return null;
      } else {
         StringBuilder var3 = new StringBuilder(var2.length());

         for (int var4 = 0; var4 < var2.length(); var4++) {
            char var5 = var2.charAt(var4);
            if (var5 == '\\' && var4 + 5 < var2.length() && var2.charAt(var4 + 1) == 'u') {
               String var6 = var2.substring(var4 + 2, var4 + 6);

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

   private String firstNonBlank(String... var1) {
      if (var1 != null && var1.length != 0) {
         for (Object var5_raw : var1) {
            String var5 = var5_raw != null ? var5_raw.toString() : null;
            if (var5 != null && !var5.isBlank()) {
               return var5;
            }
         }

         return "";
      } else {
         return "";
      }
   }

   private void saveCodepointMap(File var1, Map<String, Integer> var2, Logger var3) {
      if (var1 != null && var2 != null) {
         File var4 = var1.getParentFile();
         if (var4 != null && !var4.exists()) {
            var4.mkdirs();
         }

         Properties var5 = new Properties();
         ArrayList var6 = new ArrayList(var2.keySet());
         var6.sort(String.CASE_INSENSITIVE_ORDER);

         for (Object var8_raw : var6) {
            String var8 = var8_raw != null ? var8_raw.toString() : null;
            if (var8 != null && !var8.isBlank()) {
               Integer var9 = (Integer)var2.get(var8);
               if (var9 != null) {
                  var5.setProperty(var8, Integer.toString(var9));
               }
            }
         }

         try (OutputStream var13 = Files.newOutputStream(var1.toPath())) {
            var5.store(var13, "UltimateUI ui image tile -> codepoint map");
         } catch (Exception var12) {
            if (var3 != null) {
               var3.warning("[UltimateUI] Failed to write ui image codepoint map: " + var12.getMessage());
            }
         }
      }
   }

   private List<File> listSourceImages(File var1) {
      if (var1 != null && var1.exists() && var1.isDirectory()) {
         File[] var2 = var1.listFiles(var0 -> {
            if (var0 != null && var0.isFile()) {
               String var1x = var0.getName().toLowerCase(Locale.ROOT);
               int var2x = var1x.lastIndexOf(46);
               if (var2x > 0 && var2x < var1x.length() - 1) {
                  String var3x = var1x.substring(var2x + 1);
                  return SUPPORTED_INPUT_EXTENSIONS.contains(var3x);
               } else {
                  return false;
               }
            } else {
               return false;
            }
         });
         if (var2 != null && var2.length != 0) {
            ArrayList var3 = new ArrayList();
            Collections.addAll(var3, var2);
            var3.sort(Comparator.comparing(File::getName, String.CASE_INSENSITIVE_ORDER));
            return var3;
         } else {
            return Collections.emptyList();
         }
      } else {
         return Collections.emptyList();
      }
   }

   private File ensureSourceIsPng(File var1, Logger var2) {
      if (var1 != null && var1.isFile()) {
         String var3 = var1.getName();
         int var4 = var3.lastIndexOf(46);
         String var5 = var4 > 0 ? var3.substring(var4 + 1).toLowerCase(Locale.ROOT) : "";
         if (var5.equals("png")) {
            return var1;
         } else {
            BufferedImage var6;
            try {
               var6 = ImageIO.read(var1);
            } catch (IOException var17) {
               if (var2 != null) {
                  var2.warning("[UltimateUI] Failed to decode image '" + var3 + "' for PNG conversion: " + var17.getMessage());
               }

               return null;
            }

            if (var6 == null) {
               if (var2 != null) {
                  var2.warning("[UltimateUI] No ImageIO reader available for '" + var3 + "' (unsupported format). Skipping.");
               }

               return null;
            } else {
               String var7 = var4 > 0 ? var3.substring(0, var4) : var3;
               File var8 = new File(var1.getParentFile(), var7 + ".png");
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
                     var2.warning("[UltimateUI] Failed to write converted PNG '" + var8.getName() + "': " + var16.getMessage());
                  }

                  return null;
               }

               if (!var1.delete() && var2 != null) {
                  var2.warning("[UltimateUI] Converted '" + var3 + "' to PNG but failed to delete the original file.");
               }

               if (var2 != null) {
                  var2.info("[UltimateUI] Auto-converted '" + var3 + "' -> '" + var8.getName() + "'.");
               }

               return var8;
            }
         }
      } else {
         return null;
      }
   }

   private File normalizeSourceImageFileName(File var1, Logger var2) {
      if (var1 != null && var1.isFile()) {
         String var3 = this.firstNonBlank(var1.getName());
         if (var3.isBlank()) {
            return var1;
         } else {
            int var4 = var3.lastIndexOf(46);
            String var5 = var4 > 0 ? var3.substring(0, var4) : var3;
            String var6 = var4 > 0 && var4 < var3.length() - 1 ? var3.substring(var4 + 1).toLowerCase(Locale.ROOT) : "png";
            String var7 = normalizeImageBaseName(var5);
            File var8 = this.resolveUniqueImageFile(var1.getParentFile(), var7, var6, var1);
            if (var8 == null) {
               return var1;
            } else if (var8.getAbsolutePath().equalsIgnoreCase(var1.getAbsolutePath())) {
               return var1;
            } else {
               try {
                  Files.move(var1.toPath(), var8.toPath());
                  if (var2 != null) {
                     var2.info("[UltimateUI] Normalized image file name '" + var1.getName() + "' -> '" + var8.getName() + "'.");
                  }

                  return var8;
               } catch (IOException var10) {
                  if (var2 != null) {
                     var2.warning("[UltimateUI] Failed to normalize image file name '" + var1.getName() + "': " + var10.getMessage());
                  }

                  return var1;
               }
            }
         }
      } else {
         return null;
      }
   }

   private File resolveUniqueImageFile(File var1, String var2, String var3, File var4) {
      if (var1 == null) {
         return var4;
      } else {
         String var5 = this.firstNonBlank(var3).toLowerCase(Locale.ROOT);
         if (var5.isBlank()) {
            var5 = "png";
         }

         String var6 = normalizeImageBaseName(var2);
         File var7 = new File(var1, var6 + "." + var5);

         for (int var8 = 2; var7.exists() && !var7.getAbsolutePath().equalsIgnoreCase(var4.getAbsolutePath()); var8++) {
            var7 = new File(var1, var6 + "_" + var8 + "." + var5);
         }

         return var7;
      }
   }

   private int writeTilesInParallel(List<UiImageAtlasService.TileWriteTask> var1, Logger var2) {
      if (var1 != null && !var1.isEmpty()) {
         AtomicInteger var3 = new AtomicInteger(0);
         var1.parallelStream().forEach(var3x -> {
            if (this.writeTile(var3x.source(), var3x.sourceX(), var3x.sourceY(), var3x.copyWidth(), var3x.copyHeight(), var3x.destination(), var2)) {
               var3.incrementAndGet();
            }
         });
         return var3.get();
      } else {
         return 0;
      }
   }

   private void clearDirectory(File var1, Logger var2) {
      if (var1 != null) {
         if (!var1.exists()) {
            var1.mkdirs();
         } else {
            File[] var3 = var1.listFiles();
            if (var3 != null) {
               for (File var7 : var3) {
                  if (var7 != null) {
                     if (var7.isDirectory()) {
                        this.clearDirectory(var7, var2);
                     }

                     if (!var7.delete() && var2 != null) {
                        var2.warning("[UltimateUI] Failed to delete old generated image file: " + var7.getAbsolutePath());
                     }
                  }
               }
            }
         }
      }
   }

   private boolean writeTile(BufferedImage var1, int var2, int var3, int var4, int var5, File var6, Logger var7) {
      int[] var8 = new int[var4 * var5];
      var1.getRGB(var2, var3, var4, var5, var8, 0, var4);
      BufferedImage var9 = new BufferedImage(256, 256, 2);
      var9.setRGB(0, 0, var4, var5, var8, 0, var4);
      int var10 = Math.max(0, Math.min(255, var4 - 1));
      int var11 = Math.max(0, Math.min(255, var5 - 1));
      int var12 = var9.getRGB(var10, var11);
      int var13 = var12 & 16777215;
      int var14 = var12 >>> 24 & 0xFF;
      int var15 = Math.max(var14, 32);
      var9.setRGB(var10, var11, var15 << 24 | var13);

      try {
         ImageIO.write(var9, "png", var6);
         return true;
      } catch (IOException var17) {
         if (var7 != null) {
            var7.warning("[UltimateUI] Failed to write generated tile '" + var6.getName() + "': " + var17.getMessage());
         }

         return false;
      }
   }

   private void writeFontDefinition(File var1, List<UiImageAtlasService.ProviderEntry> var2, Logger var3) {
      StringBuilder var4 = new StringBuilder();
      var4.append("{\n");
      var4.append("  \"providers\": [\n");
      if (var2 != null) {
         for (int var5 = 0; var5 < var2.size(); var5++) {
            UiImageAtlasService.ProviderEntry var6 = (UiImageAtlasService.ProviderEntry)var2.get(var5);
            var4.append("    {\"type\": \"bitmap\", \"file\": \"minecraft:font/images/")
               .append(this.escapeJson(var6.fileName()))
               .append("\", \"ascent\": ")
               .append(256)
               .append(", \"height\": ")
               .append(256)
               .append(", \"chars\": [\"")
               .append(this.unicodeEscape(var6.codepoint()))
               .append("\"]}");
            if (var5 < var2.size() - 1) {
               var4.append(',');
            }

            var4.append('\n');
         }
      }

      var4.append("  ]\n");
      var4.append("}\n");

      try {
         Files.writeString(var1.toPath(), var4.toString(), StandardCharsets.UTF_8);
      } catch (IOException var7) {
         if (var3 != null) {
            var3.warning("[UltimateUI] Failed to write uiimages font file: " + var7.getMessage());
         }
      }
   }

   private String stripExtension(String var1) {
      if (var1 != null && !var1.isBlank()) {
         int var2 = var1.lastIndexOf(46);
         return var2 <= 0 ? var1 : var1.substring(0, var2);
      } else {
         return "image";
      }
   }

   public static String normalizeImageBaseName(String var0) {
      if (var0 != null && !var0.isBlank()) {
         StringBuilder var1 = new StringBuilder(var0.length());
         boolean var2 = false;

         for (char var6 : var0.toCharArray()) {
            if (!Character.isWhitespace(var6) && var6 != '_' && var6 != '-') {
               char var7 = Character.toLowerCase(var6);
               if (var7 >= 'a' && var7 <= 'z' || Character.isDigit(var7)) {
                  var1.append(var7);
                  var2 = false;
               }
            } else if (!var2) {
               var1.append('_');
               var2 = true;
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

   private String escapeJson(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         StringBuilder var2 = new StringBuilder(var1.length() + 8);

         for (char var6 : var1.toCharArray()) {
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
                  if (var6 < ' ') {
                     var2.append(String.format("\\u%04X", Integer.valueOf(var6)));
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

   private String unicodeEscape(int var1) {
      if (Character.isBmpCodePoint(var1)) {
         return String.format("\\u%04X", var1);
      } else {
         char[] var2 = Character.toChars(var1);
         return String.format("\\u%04X\\u%04X", Integer.valueOf(var2[0]), Integer.valueOf(var2[1]));
      }
   }

   static {
      HashSet var0 = new HashSet();
      var0.add("png");
      var0.add("jpg");
      var0.add("jpeg");
      var0.add("bmp");
      var0.add("gif");
      var0.add("webp");

      for (String var4 : ImageIO.getReaderFileSuffixes()) {
         if (var4 != null && !var4.isBlank()) {
            var0.add(var4.toLowerCase(Locale.ROOT));
         }
      }

      SUPPORTED_INPUT_EXTENSIONS = Collections.unmodifiableSet(var0);
   }

   public static record BuildResult(Map<String, UiImageAtlasService.GeneratedImage> imagesByName, int generatedTiles) {
   }

   public static record GeneratedImage(String name, int width, int height, int tilesX, int tilesY, String glyphMatrix) {
   }

   private static record ProviderEntry(String fileName, int codepoint) {
   }

   private static record TileWriteTask(BufferedImage source, int sourceX, int sourceY, int copyWidth, int copyHeight, File destination) {
   }
}
