package dev.xqedii.ultimateUI.service.gui.util;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.gui.model.YamlIssue;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.base.GuiServiceBaseSupport;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.hud.HudService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public abstract class GuiServiceUtilitySupport extends GuiServiceBaseSupport {
   private static final long YAML_FOLDER_RESCAN_INTERVAL_MILLIS = 1000L;
   private static final Pattern HEX_COLOR_TAG_PATTERN = Pattern.compile("<#[0-9a-fA-F]{6}>");
   private static final Pattern DOUBLE_PERCENT_PLACEHOLDER_PATTERN = Pattern.compile("%%([A-Za-z0-9_:\\-.]+)%%");
   private static final Pattern CRAFT_ENGINE_IMAGE_PATTERN = Pattern.compile("<image:([^<>]+)>", 2);
   private static final String[] ROUNDED_MODE_GLYPHS_SMALL = new String[]{"\uef64", "\uef65", "\uef66", "\uef67"};
   private static final String[] ROUNDED_MODE_GLYPHS_REGULAR = new String[]{"\uef60", "\uef61", "\uef62", "\uef63"};
   private static final String[] ROUNDED_MODE_GLYPHS_MEDIUM = new String[]{"\uef68", "\uef69", "\uef6a", "\uef6b"};
   private static final String[] ROUNDED_MODE_GLYPHS_LARGE = new String[]{"\uef6c", "\uef6d", "\uef6e", "\uef6f"};
   private final Map<String, Map<String, GuiServiceUtilitySupport.CachedYamlFile>> yamlFolderCache = new HashMap<>();
   private final Map<String, Long> yamlFolderLastScanMillis = new HashMap<>();
   private final Set<String> dirtyYamlFolders = new HashSet<>();

   protected GuiServiceUtilitySupport(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   protected void setNested(Map<String, Object> var1, String var2, String var3, double var4) {
      Map var7;
      if (var1.get(var2) instanceof Map var8) {
         var7 = var8;
      } else {
         var7 = new LinkedHashMap();
         var1.put(var2, var7);
      }

      var7.put(var3, var4);
   }

   protected List<Map<String, Object>> copyBlocks(List<?> var1) {
      ArrayList var2 = new ArrayList();
      if (var1 == null) {
         return var2;
      } else {
         for (Object var4 : var1) {
            Map var5 = this.toStringObjectMap(var4);
            if (var5 != null) {
               var2.add(this.deepCopyMap(var5));
            }
         }

         return var2;
      }
   }

   protected List<Map<String, Object>> resolveRenderableBlocks(List<Map<String, Object>> var1, Map<String, YamlConfiguration> var2) {
      List var3 = this.copyBlocks(var1);
      this.synchronizeImageGroupLayers(var3);
      return this.templateResolver.resolveBlocks(var3, var2);
   }

   protected List<Map<String, Object>> resolveRenderableBlocksNoCopy(List<Map<String, Object>> var1, Map<String, YamlConfiguration> var2) {
      return var1 == null ? Collections.emptyList() : this.templateResolver.resolveBlocks(var1, var2);
   }

   protected void synchronizeImageGroupLayers(List<Map<String, Object>> var1) {
      if (var1 != null && !var1.isEmpty()) {
         for (Map var3 : var1) {
            this.synchronizeImageGroupLayerNode(var3);
         }
      }
   }

   protected void synchronizeImageGroupLayerNode(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         List var2 = this.readChildRawNodes(var1);
         if (!var2.isEmpty()) {
            if (this.isImageGroupRootNode(var1, var2)) {
               double var3 = this.readRawNodeLayer(var1);
               if (Double.isFinite(var3)) {
                  for (Map var6 : (List<Map>)(List)var2) {
                     this.applyLayerRecursively(var6, var3);
                  }
               }
            }

            for (Map var4 : (List<Map>)(List)var2) {
               this.synchronizeImageGroupLayerNode(var4);
            }
         }
      }
   }

   protected List<Map<String, Object>> readChildRawNodes(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         if (var1.get("children") instanceof List var3 && !var3.isEmpty()) {
            ArrayList var4 = new ArrayList();

            for (Object var6 : var3) {
               Map var7 = this.toStringObjectMap(var6);
               if (var7 != null && !var7.isEmpty()) {
                  var4.add(var7);
               }
            }

            return var4;
         }

         return Collections.emptyList();
      } else {
         return Collections.emptyList();
      }
   }

   protected boolean isImageGroupRootNode(Map<String, Object> var1, List<Map<String, Object>> var2) {
      if (var1 != null && !var1.isEmpty() && var2 != null && !var2.isEmpty()) {
         String var3 = this.firstNonBlank(var1.get("id") == null ? "" : var1.get("id").toString(), this.readNestedId(var1)).toLowerCase(Locale.ROOT);
         if (!var3.isBlank() && var3.startsWith("img_")) {
            return true;
         } else {
            String var4 = this.firstNonBlank(var1.get("type") == null ? "" : var1.get("type").toString()).toLowerCase(Locale.ROOT);
            return "hitbox".equals(var4)
               ? this.hasDirectUiImageGlyphChildNode(var2)
               : "image".equals(var4) && (var1.containsKey("glyph_matrix") || var1.containsKey("glyphMatrix") || var1.containsKey("image"));
         }
      } else {
         return false;
      }
   }

   protected boolean hasDirectUiImageGlyphChildNode(List<Map<String, Object>> var1) {
      if (var1 != null && !var1.isEmpty()) {
         for (Map var3 : var1) {
            if (this.isUiImageGlyphNode(var3)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   protected boolean isUiImageGlyphNode(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         String var2 = this.firstNonBlank(var1.get("text") == null ? "" : var1.get("text").toString()).toLowerCase(Locale.ROOT);
         if (var2.isBlank() || !var2.contains("<font:uiimages>") && !var2.contains("%img_")) {
            String var3 = this.firstNonBlank(var1.get("id") == null ? "" : var1.get("id").toString(), this.readNestedId(var1)).toLowerCase(Locale.ROOT);
            return !var3.isBlank() && var3.startsWith("img_");
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   protected String readNestedId(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         Object var2 = var1.get("params");
         Map var3 = this.toStringObjectMap(var2);
         if (var3 != null && !var3.isEmpty()) {
            Object var4 = var3.get("id");
            return var4 == null ? "" : var4.toString();
         } else {
            return "";
         }
      } else {
         return "";
      }
   }

   protected double readRawNodeLayer(Map<String, Object> var1) {
      if (var1 == null || var1.isEmpty()) {
         return Double.NaN;
      } else if (var1.containsKey("layer")) {
         return this.parseDouble(var1.get("layer"), Double.NaN);
      } else {
         Object var2 = var1.get("size");
         Map var3 = this.toStringObjectMap(var2);
         if (var3 != null && var3.containsKey("depth")) {
            return this.parseDouble(var3.get("depth"), Double.NaN);
         } else if (var1.containsKey("depth")) {
            return this.parseDouble(var1.get("depth"), Double.NaN);
         } else {
            Object var4 = var1.get("params");
            Map var5 = this.toStringObjectMap(var4);
            if (var5 != null) {
               if (var5.containsKey("layer")) {
                  return this.parseDouble(var5.get("layer"), Double.NaN);
               }

               if (var5.containsKey("depth")) {
                  return this.parseDouble(var5.get("depth"), Double.NaN);
               }
            }

            return Double.NaN;
         }
      }
   }

   protected void applyLayerRecursively(Map<String, Object> var1, double var2) {
      if (var1 != null && !var1.isEmpty() && Double.isFinite(var2)) {
         if (var1.containsKey("layer")) {
            var1.put("layer", var2);
         } else {
            Object var4 = var1.get("size");
            Map var5 = this.toStringObjectMap(var4);
            if (var5 != null && var5.containsKey("depth")) {
               var5.put("depth", var2);
               var1.put("size", var5);
            } else if (var1.containsKey("depth")) {
               var1.put("depth", var2);
            } else {
               Object var6 = var1.get("params");
               Map var7 = this.toStringObjectMap(var6);
               if (var7 != null && var7.containsKey("layer")) {
                  var7.put("layer", var2);
                  var1.put("params", var7);
               } else if (var7 != null && var7.containsKey("depth")) {
                  var7.put("depth", var2);
                  var1.put("params", var7);
               } else {
                  var1.put("layer", var2);
               }
            }
         }

         for (Map var10 : this.readChildRawNodes(var1)) {
            this.applyLayerRecursively(var10, var2);
         }
      }
   }

   protected Map<String, Object> deepCopyMap(Map<String, Object> var1) {
      LinkedHashMap var2 = new LinkedHashMap();

      for (Entry var4 : var1.entrySet()) {
         var2.put((String)var4.getKey(), this.deepCopyValue(var4.getValue()));
      }

      return var2;
   }

   protected Object deepCopyValue(Object var1) {
      if (var1 instanceof Map var6) {
         LinkedHashMap var7 = new LinkedHashMap();

         for (Map.Entry var9 : (Set<Map.Entry>)(Set)var6.entrySet()) {
            if (var9.getKey() != null) {
               var7.put(var9.getKey().toString(), this.deepCopyValue(var9.getValue()));
            }
         }

         return this.deepCopyMap(var7);
      } else if (!(var1 instanceof List var2)) {
         return var1;
      } else {
         ArrayList var3 = new ArrayList();

         for (Object var5 : var2) {
            var3.add(this.deepCopyValue(var5));
         }

         return var3;
      }
   }

   protected YamlConfiguration cloneYamlConfiguration(YamlConfiguration var1) {
      YamlConfiguration var2 = new YamlConfiguration();
      if (var1 == null) {
         return var2;
      } else {
         for (String var4 : var1.getKeys(false)) {
            var2.set(var4, this.deepCopyValue(var1.get(var4)));
         }

         return var2;
      }
   }

   protected double findMaxLayer(List<Map<String, Object>> var1) {
      if (var1 != null && !var1.isEmpty()) {
         double var2 = 0.0;

         for (Map var5 : var1) {
            var2 = Math.max(var2, this.findMaxLayerInNode(var5));
         }

         return var2;
      } else {
         return 0.0;
      }
   }

   private double findMaxLayerInNode(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         double var2 = 0.0;
         Object var4 = this.readNestedMapPathValue(var1, "type");
         String var5 = this.firstNonBlank(var4 == null ? "" : var4.toString(), "block").toLowerCase(Locale.ROOT);
         if ("block".equals(var5) || "text".equals(var5) || this.isRoundedType(var5) || "hitbox".equals(var5)) {
            double var6 = this.readNestedMapPathDouble(var1, 0.0, "layer", "size.depth", "depth");
            if (var6 > var2) {
               var2 = var6;
            }
         }

         Object var11 = var1.get("children");
         if (var11 instanceof List) {
            for (Object var9 : (List)var11) {
               Map var10 = this.toStringObjectMap(var9);
               if (var10 != null) {
                  var2 = Math.max(var2, this.findMaxLayerInNode(var10));
               }
            }
         }

         return var2;
      } else {
         return 0.0;
      }
   }

   private Object readNestedMapPathValue(Map<String, Object> var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Object var3 = var1;
         String[] var4 = var2.split("\\.");

         for (Object var8_raw : var4) {
            String var8 = var8_raw != null ? var8_raw.toString() : null;
            if (!(var3 instanceof Map var9)) {
               return null;
            }

            var3 = var9.get(var8);
            if (var3 == null) {
               return null;
            }
         }

         return var3;
      } else {
         return null;
      }
   }

   private double readNestedMapPathDouble(Map<String, Object> var1, double var2, String... var4) {
      if (var1 != null && var4 != null && var4.length != 0) {
         for (Object var8_raw : var4) {
            String var8 = var8_raw != null ? var8_raw.toString() : null;
            Object var9 = this.readNestedMapPathValue(var1, var8);
            if (var9 != null) {
               double var10 = this.parseDouble(var9, Double.NaN);
               if (Double.isFinite(var10)) {
                  return var10;
               }
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   protected boolean equalsNullable(String var1, String var2) {
      if (var1 == null && var2 == null) {
         return true;
      } else {
         return var1 != null && var2 != null ? var1.equals(var2) : false;
      }
   }

   protected void saveDefaultResource(String var1) {
      File var2 = new File(this.plugin.getDataFolder(), var1);
      if (!var2.exists()) {
         File var3 = var2.getParentFile();
         if (var3 != null && !var3.exists()) {
            var3.mkdirs();
         }

         try {
            if (this.plugin.getResource(var1) != null) {
               this.plugin.saveResource(var1, false);
            } else {
               String var4 = this.resolveLegacyDefaultResourcePath(var1);
               if (var4 != null && !var4.isBlank()) {
                  try (InputStream var5 = this.plugin.getResource(var4)) {
                     if (var5 != null) {
                        Files.copy(var5, var2.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        return;
                     }
                  }
               }
            }
         } catch (IOException var10) {
            this.plugin.getLogger().warning("[UltimateUI] Failed to save default resource '" + var1 + "': " + var10.getMessage());
         }
      }
   }

   protected String resolveLegacyDefaultResourcePath(String var1) {
      if (var1 != null && !var1.isBlank()) {
         String var2 = var1.replace('\\', '/');
         return var2.startsWith("contents/") ? var2.substring("contents/".length()) : null;
      } else {
         return null;
      }
   }

   protected Map<String, YamlConfiguration> loadYamlFolder(String var1, List<YamlIssue> var2) {
      Map var3 = this.loadYamlFolderRaw(var1, var2);
      String var4 = this.resolveEditorDefaultsFallbackFolder(var1);
      if (var4 == null) {
         return var3;
      } else {
         Map var5 = this.loadYamlFolderRaw(var4, var2);
         if (var5.isEmpty()) {
            return var3;
         } else {
            HashMap var6 = var3.isEmpty() ? new HashMap(var5) : new HashMap(var3);
            if (!var3.isEmpty()) {
               for (Map.Entry var8 : (Set<Map.Entry>)(Set)var5.entrySet()) {
                  var6.putIfAbsent((String)var8.getKey(), (YamlConfiguration)var8.getValue());
               }
            }

            return var6;
         }
      }
   }

   private String resolveEditorDefaultsFallbackFolder(String var1) {
      if (var1 == null) {
         return null;
      } else if (var1.equals("contents/pages")) {
         return "editor-defaults/pages";
      } else {
         return var1.equals("contents/components") ? "editor-defaults/components" : null;
      }
   }

   private Map<String, YamlConfiguration> loadYamlFolderRaw(String var1, List<YamlIssue> var2) {
      File var3 = new File(this.plugin.getDataFolder(), var1);
      String var4 = this.yamlFolderCacheKey(var3);
      Map var5 = this.yamlFolderCache.get(var4);
      long var6 = System.currentTimeMillis();
      long var8 = this.yamlFolderLastScanMillis.getOrDefault(var4, Long.MIN_VALUE);
      boolean var10 = this.dirtyYamlFolders.remove(var4);
      boolean var11 = var5 != null && !var5.isEmpty() && !var10 && var6 - var8 < 1000L;
      if (var11) {
         return this.buildYamlFolderResultFromCache(var3, var5, var2);
      } else if (var3.exists() && var3.isDirectory()) {
         if (var5 == null) {
            var5 = this.yamlFolderCache.computeIfAbsent(var4, var0 -> new HashMap<>());
         }

         boolean var12 = var5.isEmpty() || var10 || var6 - var8 >= 1000L;
         if (!var12) {
            return this.buildYamlFolderResultFromCache(var3, var5, var2);
         } else {
            File[] var13 = var3.listFiles((var0, var1x) -> var1x.toLowerCase(Locale.ROOT).endsWith(".yml"));
            this.yamlFolderLastScanMillis.put(var4, var6);
            if (var13 == null) {
               var5.clear();
               return Collections.emptyMap();
            } else {
               HashSet var14 = new HashSet();
               HashMap var15 = new HashMap();

               for (File var19 : var13) {
                  String var20 = this.yamlFileCacheKey(var19);
                  var14.add(var20);
                  GuiServiceUtilitySupport.CachedYamlFile var21 = (GuiServiceUtilitySupport.CachedYamlFile)var5.computeIfAbsent(var20, var0 -> new GuiServiceUtilitySupport.CachedYamlFile());
                  var21.fileName = var19.getName();
                  var21.mapKey = this.fileBaseName(var21.fileName);
                  YamlConfiguration var22 = this.loadYamlFile(var19, var2, var21);
                  if (var22 != null) {
                     var15.put(var21.mapKey, var22);
                  }
               }

               var5.keySet().removeIf(var1x -> !var14.contains(var1x));
               if (var5.isEmpty()) {
                  this.yamlFolderCache.remove(var4);
               }

               return var15;
            }
         }
      } else {
         this.yamlFolderCache.remove(var4);
         this.yamlFolderLastScanMillis.remove(var4);
         this.dirtyYamlFolders.remove(var4);
         return Collections.emptyMap();
      }
   }

   private Map<String, YamlConfiguration> buildYamlFolderResultFromCache(
      File var1, Map<String, GuiServiceUtilitySupport.CachedYamlFile> var2, List<YamlIssue> var3
   ) {
      if (var2.isEmpty()) {
         return Collections.emptyMap();
      } else {
         HashMap var4 = new HashMap();

         for (GuiServiceUtilitySupport.CachedYamlFile var6 : var2.values()) {
            if (var6 != null) {
               String var7 = this.firstNonBlank(var6.mapKey).trim();
               if (!var7.isBlank()) {
                  if (var6.invalid) {
                     this.addYamlIssue(var3, new File(var1, this.firstNonBlank(var6.fileName, var7 + ".yml")), var6.errorDetails);
                  } else if (var6.yaml != null) {
                     var4.put(var7, var6.yaml);
                  }
               }
            }
         }

         return var4;
      }
   }

   protected YamlConfiguration loadYamlFile(File var1, List<YamlIssue> var2) {
      if (var1 == null) {
         return null;
      } else {
         File var3 = var1.getParentFile();
         if (var3 == null) {
            return this.loadYamlFile(var1, var2, null);
         } else {
            String var4 = this.yamlFolderCacheKey(var3);
            Map var5 = this.yamlFolderCache.computeIfAbsent(var4, var0 -> new HashMap<>());
            String var6 = this.yamlFileCacheKey(var1);
            GuiServiceUtilitySupport.CachedYamlFile var7 = (GuiServiceUtilitySupport.CachedYamlFile)var5.computeIfAbsent(var6, var0 -> new GuiServiceUtilitySupport.CachedYamlFile());
            return this.loadYamlFile(var1, var2, var7);
         }
      }
   }

   private YamlConfiguration loadYamlFile(File var1, List<YamlIssue> var2, GuiServiceUtilitySupport.CachedYamlFile var3) {
      if (var3 != null) {
         var3.fileName = this.firstNonBlank(var3.fileName, var1.getName());
         var3.mapKey = this.firstNonBlank(var3.mapKey, this.fileBaseName(var3.fileName));
      }

      long var4 = var1.lastModified();
      long var6 = var1.length();
      if (var3 == null || var3.lastModified != var4 || var3.length != var6) {
         YamlConfiguration var8 = new YamlConfiguration();

         try {
            var8.load(var1);
            if (var3 != null) {
               var3.lastModified = var4;
               var3.length = var6;
               var3.invalid = false;
               var3.errorDetails = "";
               var3.yaml = var8;
            }

            return var8;
         } catch (Exception var11) {
            String var10 = var11.getMessage() == null ? "Unknown YAML parse error" : var11.getMessage();
            if (var3 != null) {
               var3.lastModified = var4;
               var3.length = var6;
               var3.invalid = true;
               var3.errorDetails = var10;
               var3.yaml = null;
            }

            this.addYamlIssue(var2, var1, var10);
            return null;
         }
      } else if (var3.invalid) {
         this.addYamlIssue(var2, var1, var3.errorDetails);
         return null;
      } else {
         return var3.yaml;
      }
   }

   private void addYamlIssue(List<YamlIssue> var1, File var2, String var3) {
      if (var1 != null && var2 != null) {
         YamlIssue var4 = new YamlIssue();
         var4.file = this.toDataRelativePath(var2);
         var4.details = this.firstNonBlank(var3, "Unknown YAML parse error");
         var1.add(var4);
      }
   }

   private String yamlFolderCacheKey(File var1) {
      return var1 == null ? "" : var1.getAbsolutePath();
   }

   private String yamlFileCacheKey(File var1) {
      return var1 == null ? "" : var1.getName().toLowerCase(Locale.ROOT);
   }

   private String fileBaseName(String var1) {
      String var2 = this.firstNonBlank(var1).trim();
      return var2.toLowerCase(Locale.ROOT).endsWith(".yml") && var2.length() > 4 ? var2.substring(0, var2.length() - 4) : var2;
   }

   protected void invalidateYamlFileCache(File var1) {
      if (var1 != null) {
         File var2 = var1.getParentFile();
         if (var2 != null) {
            String var3 = this.yamlFolderCacheKey(var2);
            this.dirtyYamlFolders.add(var3);
            this.yamlFolderLastScanMillis.remove(var3);
            Map var4 = this.yamlFolderCache.get(var3);
            if (var4 != null && !var4.isEmpty()) {
               var4.remove(this.yamlFileCacheKey(var1));
               if (var4.isEmpty()) {
                  this.yamlFolderCache.remove(var3);
               }
            }
         }
      }
   }

   protected void reportYamlIssues(List<YamlIssue> var1) {
      this.plugin.getLogger().severe("[UltimateUI] YAML validation failed: " + var1.size() + " issue(s) found.");

      for (YamlIssue var3 : var1) {
         this.plugin.getLogger().severe("[UltimateUI] " + var3.file + " -> " + var3.details);
      }
   }

   protected String toDataRelativePath(File var1) {
      String var2 = this.plugin.getDataFolder().getAbsolutePath();
      String var3 = var1.getAbsolutePath();
      if (var3.startsWith(var2)) {
         var3 = var3.substring(var2.length());
         if (var3.startsWith(File.separator)) {
            var3 = var3.substring(1);
         }
      }

      return var3.replace('\\', '/');
   }

   protected void sanitizeYamlTabs(String var1) {
      File var2 = new File(this.plugin.getDataFolder(), var1);
      if (var2.exists() && var2.isDirectory()) {
         File[] var3 = var2.listFiles((var0, var1x) -> var1x.toLowerCase().endsWith(".yml"));
         if (var3 != null) {
            for (File var7 : var3) {
               try {
                  String var8 = Files.readString(var7.toPath(), StandardCharsets.UTF_8);
                  if (var8.contains("\t")) {
                     Files.writeString(var7.toPath(), var8.replace("\t", "  "), StandardCharsets.UTF_8);
                     this.invalidateYamlFileCache(var7);
                     this.plugin.getLogger().warning("[UltimateUI] Replaced TAB indentation in " + this.toDataRelativePath(var7));
                  }
               } catch (IOException var9) {
                  this.plugin.getLogger().warning("[UltimateUI] Cannot sanitize " + this.toDataRelativePath(var7) + ": " + var9.getMessage());
               }
            }
         }
      }
   }

   protected void saveBlocksPreservingYamlComments(File var1, List<Map<String, Object>> var2) throws IOException {
      String var3 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
      List var4 = var2 == null ? Collections.emptyList() : var2;
      YamlConfiguration var5 = new YamlConfiguration();
      var5.set("blocks", var4);
      String var6 = var5.saveToString();
      String var7 = var3.replace("\r\n", "\n");
      String var8 = var6.replace("\r\n", "\n");
      int var9 = this.findTopLevelKeyStart(var7, "blocks:");
      String var10 = this.extractBlocksSection(var8);
      if (var10 != null && !var10.isBlank()) {
         String var11;
         if (var9 < 0) {
            String var12 = var7;
            if (!var7.endsWith("\n") && !var7.isEmpty()) {
               var12 = var7 + "\n";
            }

            var11 = var12 + "\n" + var10;
         } else {
            int var16 = this.findNextTopLevelKeyStart(var7, var9 + "blocks:".length());
            if (var16 < 0) {
               var16 = var7.length();
            }

            String var13 = var7.substring(0, var9);
            String var14 = var7.substring(var16);
            if (!var13.isEmpty() && !var13.endsWith("\n")) {
               var13 = var13 + "\n";
            }

            if (!var14.isEmpty() && !var14.startsWith("\n")) {
               var14 = "\n" + var14;
            }

            var11 = var13 + var10 + var14;
         }

         if (!var4.isEmpty()) {
            YamlConfiguration var17 = new YamlConfiguration();

            try {
               var17.loadFromString(var11);
            } catch (InvalidConfigurationException var15) {
               this.writeBlocksWithFullYamlFallback(var1, var3, var4);
               return;
            }

            List var18 = var17.getList("blocks");
            if (var18 == null || var18.isEmpty()) {
               this.writeBlocksWithFullYamlFallback(var1, var3, var4);
               return;
            }
         }

         Files.writeString(var1.toPath(), var11.replace("\n", System.lineSeparator()), StandardCharsets.UTF_8);
         this.invalidateYamlFileCache(var1);
      } else {
         this.writeBlocksWithFullYamlFallback(var1, var3, var4);
      }
   }

   protected void writeBlocksWithFullYamlFallback(File var1, String var2, List<Map<String, Object>> var3) throws IOException {
      YamlConfiguration var4 = new YamlConfiguration();
      if (var2 != null && !var2.isBlank()) {
         try {
            var4.loadFromString(var2);
         } catch (InvalidConfigurationException var6) {
         }
      }

      var4.set("blocks", var3 == null ? Collections.emptyList() : var3);
      String var5 = var4.saveToString();
      Files.writeString(var1.toPath(), var5.replace("\n", System.lineSeparator()), StandardCharsets.UTF_8);
      this.invalidateYamlFileCache(var1);
   }

   protected int findTopLevelKeyStart(String var1, String var2) {
      String[] var3 = var1.split("\n", -1);
      int var4 = 0;

      for (Object var8_raw : var3) {
         String var8 = var8_raw != null ? var8_raw.toString() : null;
         String var9 = var8.trim();
         if (!var8.startsWith(" ") && !var8.startsWith("\t") && var9.startsWith(var2)) {
            return var4;
         }

         var4 += var8.length() + 1;
      }

      return -1;
   }

   protected int findNextTopLevelKeyStart(String var1, int var2) {
      int var3 = Math.max(0, var2);
      if (var3 > var1.length()) {
         return -1;
      } else {
         int var4 = var1.lastIndexOf(10, var3);
         var4 = var4 < 0 ? 0 : var4 + 1;
         if (var4 <= var3) {
            int var5 = var1.indexOf(10, var4);
            if (var5 < 0) {
               return -1;
            }

            var4 = var5 + 1;
         }

         while (var4 < var1.length()) {
            int var9 = var1.indexOf(10, var4);
            if (var9 < 0) {
               var9 = var1.length();
            }

            String var6 = var1.substring(var4, var9);
            String var7 = var6.trim();
            if (!var7.isEmpty() && !var7.startsWith("#") && !var6.startsWith(" ") && !var6.startsWith("\t") && var6.contains(":")) {
               return var4;
            }

            var4 = var9 + 1;
         }

         return -1;
      }
   }

   protected String extractBlocksSection(String var1) {
      int var2 = this.findTopLevelKeyStart(var1, "blocks:");
      if (var2 < 0) {
         return null;
      } else {
         int var3 = this.findNextTopLevelKeyStart(var1, var2 + "blocks:".length());
         if (var3 < 0) {
            var3 = var1.length();
         }

         String var4 = var1.substring(var2, var3).trim();
         return var4 + "\n";
      }
   }

   protected double readDouble(YamlConfiguration var1, String var2, String var3, double var4) {
      if (var1.contains(var2)) {
         return this.parseDouble(var1.get(var2), var4);
      } else {
         return var1.contains(var3) ? this.parseDouble(var1.get(var3), var4) : var4;
      }
   }

   protected double snap1(double var1) {
      return (double)Math.round(var1);
   }

   protected int readOpacity(ConfigurationSection var1, int var2) {
      int var3 = (int)Math.round(this.readDouble(var1, "opacity", "opacity", (double)var2));
      return Math.max(0, Math.min(255, var3));
   }

   protected double readOutlineSize(ConfigurationSection var1) {
      if (var1 == null) {
         return 0.0;
      } else {
         double var2 = this.firstPresentDouble(var1, 0.0, "outline.size", "outline.width", "outline.thickness", "outline.stroke", "stroke.size", "stroke.width");
         return !Double.isFinite(var2) ? 0.0 : Math.max(0.0, var2);
      }
   }

   protected String readOutlineColor(ConfigurationSection var1) {
      if (var1 == null) {
         return "ffffff";
      } else {
         String var2 = this.firstNonBlank(var1.getString("outline.color"), var1.getString("outline.hex"), var1.getString("outline.style.color"));
         String var3 = this.normalizeHexColor(var2);
         return var3.isBlank() ? "ffffff" : var3;
      }
   }

   protected double readRoundedRadius(ConfigurationSection var1, double var2, double var4) {
      return 26.0;
   }

   protected double readDouble(ConfigurationSection var1, String var2, String var3, double var4) {
      if (var1.contains(var2)) {
         return this.parseDouble(var1.get(var2), var4);
      } else {
         return var1.contains(var3) ? this.parseDouble(var1.get(var3), var4) : var4;
      }
   }

   protected double parseDouble(Object var1, double var2) {
      if (var1 == null) {
         return var2;
      } else if (var1 instanceof Number var8) {
         return var8.doubleValue();
      } else {
         String var4 = var1.toString().trim();
         if (var4.isEmpty()) {
            return var2;
         } else {
            String var5 = this.normalizeNumericExpressionSeparators(var4);

            try {
               return Double.parseDouble(var5);
            } catch (Exception var7) {
               return this.evaluateNumericExpression(var5, var2);
            }
         }
      }
   }

   protected String normalizeNumericExpressionSeparators(String var1) {
      if (var1 != null && !var1.isBlank() && var1.indexOf(44) >= 0) {
         StringBuilder var2 = new StringBuilder(var1.length());

         for (int var3 = 0; var3 < var1.length(); var3++) {
            char var4 = var1.charAt(var3);
            if (var4 != ',') {
               var2.append(var4);
            } else {
               int var5 = var3 - 1;

               while (var5 >= 0 && Character.isWhitespace(var1.charAt(var5))) {
                  var5--;
               }

               int var6 = var3 + 1;

               while (var6 < var1.length() && Character.isWhitespace(var1.charAt(var6))) {
                  var6++;
               }

               boolean var7 = var5 >= 0 && var6 < var1.length() && Character.isDigit(var1.charAt(var5)) && Character.isDigit(var1.charAt(var6));
               var2.append((char)(var7 ? '.' : ','));
            }
         }

         return var2.toString();
      } else {
         return var1;
      }
   }

   protected double evaluateNumericExpression(String var1, double var2) {
      try {
         int[] var4 = new int[]{0};
         double var5 = this.parseExpression(var1, var4);
         this.skipWhitespace(var1, var4);
         return var4[0] == var1.length() && Double.isFinite(var5) ? var5 : var2;
      } catch (Exception var7) {
         return var2;
      }
   }

   protected double parseExpression(String var1, int[] var2) {
      double var3 = this.parseTerm(var1, var2);

      while (true) {
         this.skipWhitespace(var1, var2);
         if (var2[0] >= var1.length()) {
            return var3;
         }

         char var5 = var1.charAt(var2[0]);
         if (var5 != '+' && var5 != '-') {
            return var3;
         }

         var2[0]++;
         double var6 = this.parseTerm(var1, var2);
         var3 = var5 == '+' ? var3 + var6 : var3 - var6;
      }
   }

   protected double parseTerm(String var1, int[] var2) {
      double var3 = this.parseFactor(var1, var2);

      while (true) {
         this.skipWhitespace(var1, var2);
         if (var2[0] >= var1.length()) {
            return var3;
         }

         char var5 = var1.charAt(var2[0]);
         if (var5 != '*' && var5 != '/') {
            return var3;
         }

         var2[0]++;
         double var6 = this.parseFactor(var1, var2);
         if (var5 == '*') {
            var3 *= var6;
         } else {
            if (var6 == 0.0) {
               throw new IllegalArgumentException("Division by zero");
            }

            var3 /= var6;
         }
      }
   }

   protected double parseFactor(String var1, int[] var2) {
      this.skipWhitespace(var1, var2);
      if (var2[0] >= var1.length()) {
         throw new IllegalArgumentException("Unexpected end of expression");
      } else {
         char var3 = var1.charAt(var2[0]);
         if (var3 == '+') {
            var2[0]++;
            return this.parseFactor(var1, var2);
         } else if (var3 == '-') {
            var2[0]++;
            return -this.parseFactor(var1, var2);
         } else if (var3 == '(') {
            var2[0]++;
            double var4 = this.parseExpression(var1, var2);
            this.skipWhitespace(var1, var2);
            if (var2[0] < var1.length() && var1.charAt(var2[0]) == ')') {
               var2[0]++;
               return var4;
            } else {
               throw new IllegalArgumentException("Missing closing parenthesis");
            }
         } else {
            return this.parseNumber(var1, var2);
         }
      }
   }

   protected double parseNumber(String var1, int[] var2) {
      int var3 = var2[0];
      boolean var4 = false;

      while (var2[0] < var1.length()) {
         char var5 = var1.charAt(var2[0]);
         if (Character.isDigit(var5)) {
            var2[0]++;
         } else {
            if (var5 != '.' || var4) {
               break;
            }

            var4 = true;
            var2[0]++;
         }
      }

      if (var3 == var2[0]) {
         throw new IllegalArgumentException("Expected number");
      } else {
         return Double.parseDouble(var1.substring(var3, var2[0]));
      }
   }

   protected void skipWhitespace(String var1, int[] var2) {
      while (var2[0] < var1.length() && Character.isWhitespace(var1.charAt(var2[0]))) {
         var2[0]++;
      }
   }

   protected String firstNonBlank(String... var1) {
      if (var1 == null) {
         return "";
      } else {
         for (Object var5_raw : var1) {
            String var5 = var5_raw != null ? var5_raw.toString() : null;
            if (var5 != null && !var5.isBlank()) {
               return var5.trim();
            }
         }

         return "";
      }
   }

   protected String applyPlaceholderApi(Player var1, String var2) {
      return this.resolvePlaceholders(var1, var2, true);
   }

   protected String resolvePlaceholders(Player var1, String var2, boolean var3) {
      if (var2 != null && !var2.isBlank()) {
         if (var3 && var1 != null) {
            EditorSession var4 = this.editorSessions.get(var1.getUniqueId());
            if (var4 != null && var4.editMode) {
               return var2;
            }
         }

         String var6 = this.normalizeDoublePercentPlaceholders(var2);
         String var5 = var6;
         if (var6.contains("%")) {
            var5 = this.applyPlaceholderApiWithPapi(var1, var6);
            if (var1 != null && var5.contains("%player%")) {
               var5 = var5.replace("%player%", var1.getName());
            }
         }

         return this.applyCraftEngineFontImages(this.applyItemsAdderFontImages(var5));
      } else {
         return var2;
      }
   }

   protected String normalizeDoublePercentPlaceholders(String var1) {
      if (var1 != null && !var1.isBlank() && var1.contains("%%")) {
         Matcher var2 = DOUBLE_PERCENT_PLACEHOLDER_PATTERN.matcher(var1);
         StringBuffer var3 = new StringBuffer();
         boolean var4 = false;

         while (var2.find()) {
            var4 = true;
            String var5 = var2.group(1);
            var2.appendReplacement(var3, Matcher.quoteReplacement("%" + var5 + "%"));
         }

         if (!var4) {
            return var1;
         } else {
            var2.appendTail(var3);
            return var3.toString();
         }
      } else {
         return var1;
      }
   }

   protected String applyPlaceholderApiWithPapi(Player var1, String var2) {
      if (var2 != null && !var2.isBlank() && var2.contains("%")) {
         Plugin var3 = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");
         if (var3 != null && var3.isEnabled()) {
            try {
               Class var4 = Class.forName("me.clip.placeholderapi.PlaceholderAPI");
               Method var5 = var4.getMethod("setPlaceholders", Player.class, String.class);
               Object var6 = var5.invoke(null, var1, var2);
               return var6 == null ? var2 : var6.toString();
            } catch (Throwable var7) {
               return var2;
            }
         } else {
            return var2;
         }
      } else {
         return var2;
      }
   }

   protected String applyItemsAdderFontImages(String var1) {
      if (var1 != null && !var1.isBlank()) {
         String var2 = var1.toLowerCase(Locale.ROOT);
         if (!var1.contains(":") && !var2.contains("%img_")) {
            return var1;
         } else {
            Plugin var3 = Bukkit.getPluginManager().getPlugin("ItemsAdder");
            if (var3 != null && var3.isEnabled()) {
               try {
                  Class var4 = Class.forName("dev.lone.itemsadder.api.FontImages.FontImageWrapper");
                  Method var5 = var4.getMethod("replaceFontImages", String.class);
                  Object var6 = var5.invoke(null, var1);
                  return var6 == null ? var1 : var6.toString();
               } catch (Throwable var7) {
                  return var1;
               }
            } else {
               return var1;
            }
         }
      } else {
         return var1;
      }
   }

   protected String applyCraftEngineFontImages(String var1) {
      if (var1 != null && !var1.isBlank() && var1.contains("<image:")) {
         Plugin var2 = Bukkit.getPluginManager().getPlugin("CraftEngine");
         if (var2 != null && var2.isEnabled()) {
            try {
               Class var3 = Class.forName("net.momirealms.craftengine.core.util.Key");
               Method var4 = var3.getMethod("of", String.class);
               Class var5 = Class.forName("net.momirealms.craftengine.bukkit.api.CraftEngineImages");
               Method var6 = var5.getMethod("byId", var3);
               Matcher var7 = CRAFT_ENGINE_IMAGE_PATTERN.matcher(var1);
               StringBuffer var8 = new StringBuffer(var1.length());
               boolean var9 = false;

               while (var7.find()) {
                  String var10 = var7.group(0);
                  String var11 = this.resolveCraftEngineImageTag(var7.group(1), var4, var6);
                  if (var11 != null) {
                     var10 = var11;
                     var9 = true;
                  }

                  var7.appendReplacement(var8, Matcher.quoteReplacement(var10));
               }

               if (!var9) {
                  return var1;
               } else {
                  var7.appendTail(var8);
                  return var8.toString();
               }
            } catch (Throwable var12) {
               return var1;
            }
         } else {
            return var1;
         }
      } else {
         return var1;
      }
   }

   private String resolveCraftEngineImageTag(String var1, Method var2, Method var3) {
      if (var1 != null && !var1.isBlank()) {
         try {
            String[] var4 = var1.trim().split(":");
            if (var4.length < 2) {
               return null;
            } else {
               String var5 = var4[0] + ":" + var4[1];
               int var6 = var4.length >= 3 ? this.parseIntSafe(var4[2], 0) : 0;
               int var7 = var4.length >= 4 ? this.parseIntSafe(var4[3], 0) : 0;
               Object var8 = var2.invoke(null, var5);
               Object var9 = var3.invoke(null, var8);
               if (var9 == null) {
                  return null;
               } else {
                  Method var10 = var9.getClass().getMethod("miniMessageAt", int.class, int.class);
                  var10.setAccessible(true);
                  Object var11 = var10.invoke(var9, var6, var7);
                  return var11 == null ? null : var11.toString();
               }
            }
         } catch (Throwable var12) {
            return null;
         }
      } else {
         return null;
      }
   }

   private int parseIntSafe(String var1, int var2) {
      try {
         return Integer.parseInt(var1.trim());
      } catch (Throwable var4) {
         return var2;
      }
   }

   protected String withHexPrefix(String var1, String var2) {
      if (var1 != null && !var1.isBlank()) {
         Matcher var3 = HEX_PREFIX.matcher(var1);
         if (var3.find()) {
            return var1;
         } else {
            Matcher var4 = Pattern.compile("^(?:\\s*<font:[^>]+>\\s*)+<#[0-9a-fA-F]{6}>").matcher(var1);
            if (var4.find()) {
               return var1;
            } else {
               String var5 = this.normalizeHexColor(var2);
               return var5.isBlank() ? var1 : "<#" + var5 + ">" + var1;
            }
         }
      } else {
         return var1;
      }
   }

   protected String applyOutlineColor(String var1, String var2) {
      if (var1 != null && !var1.isBlank()) {
         String var3 = this.normalizeHexColor(var2);
         if (var3.isBlank()) {
            var3 = "ffffff";
         }

         Matcher var4 = HEX_COLOR_TAG_PATTERN.matcher(var1);
         return var4.find() ? var4.replaceFirst("<#" + var3 + ">") : "<#" + var3 + ">" + var1;
      } else {
         return var1;
      }
   }

   protected String withFont(String var1, String var2) {
      if (var1 == null || var1.isBlank()) {
         return var1;
      } else if (var2 == null || var2.isBlank()) {
         return var1;
      } else {
         return var1.contains("<font:") ? var1 : "<font:" + var2 + ">" + var1 + "</font>";
      }
   }

   protected String withEditorUiFont(String var1) {
      return this.withFont(var1, "editor");
   }

   protected String withDefaultFont(String var1) {
      return this.withFont(var1, "default");
   }

   protected String applyPreferredFont(String var1, ConfigurationSection var2, boolean var3) {
      return this.applyPreferredFont(var1, var2, var3, true);
   }

   protected String applyPreferredFont(String var1, ConfigurationSection var2, boolean var3, boolean var4) {
      if (var1 != null && !var1.isBlank() && !var1.contains("<font:")) {
         String var5 = this.resolvePreferredFont(var2, var3, var1, var4);
         return this.withFont(var1, var5);
      } else {
         return var1;
      }
   }

   protected String resolvePreferredFont(ConfigurationSection var1, boolean var2, String var3) {
      return this.resolvePreferredFont(var1, var2, var3, true);
   }

   protected String resolvePreferredFont(ConfigurationSection var1, boolean var2, String var3, boolean var4) {
      String var5 = this.firstNonBlank(
         var1 == null ? null : var1.getString("font"), var1 == null ? null : var1.getString("style.font"), var1 == null ? null : var1.getString("text.font")
      );
      if (!var5.isBlank()) {
         return this.normalizeFontKey(var5);
      } else {
         return var4 ? "editor" : "default";
      }
   }

   protected boolean isEditorContextSession(EditorSession var1) {
      if (var1 == null) {
         return false;
      } else {
         String var2 = this.firstNonBlank(var1.pageName).trim().toLowerCase(Locale.ROOT);
         return var2.isEmpty() ? false : "editor".equals(var2) || "editor_menu".equals(var2) || "editor_empty".equals(var2);
      }
   }

   protected String normalizeFontKey(String var1) {
      String var2 = this.firstNonBlank(var1);
      if (var2.isBlank()) {
         return "default";
      } else {
         String var3 = var2.toLowerCase(Locale.ROOT);
         if ("default".equals(var3) || "minecraft:default".equals(var3)) {
            return "default";
         } else {
            return !"editor".equals(var3) && !"minecraft:editor".equals(var3) ? var2 : "editor";
         }
      }
   }

   protected boolean containsPrivateUseGlyph(String var1) {
      if (var1 != null && !var1.isBlank()) {
         int var2 = 0;

         while (var2 < var1.length()) {
            int var3 = var1.codePointAt(var2);
            if (var3 >= 57344 && var3 <= 63743) {
               return true;
            }

            var2 += Character.charCount(var3);
         }

         return false;
      } else {
         return false;
      }
   }

   protected String roundedCornerTextFromBody(String var1, String var2) {
      String var3 = this.firstHexColorPrefix(var1);
      String var4 = this.firstNonBlank(var2);
      String var5 = HEX_COLOR_TAG_PATTERN.matcher(var4).replaceAll("").trim();
      if (var5.isBlank()) {
         var5 = "\ue151";
      }

      String var6 = this.firstHexColorPrefix(var4);
      String var7;
      if (!var6.isBlank()) {
         var7 = var6 + var5;
      } else if (!var3.isBlank()) {
         var7 = var3 + var5;
      } else if (!var4.isBlank()) {
         var7 = var4;
      } else {
         var7 = var5;
      }

      return this.ensureRoundedCornerEditorFont(var7);
   }

   protected String firstHexColorPrefix(String var1) {
      if (var1 != null && !var1.isBlank()) {
         Matcher var2 = HEX_COLOR_TAG_PATTERN.matcher(var1);
         return !var2.find() ? "" : var2.group();
      } else {
         return "";
      }
   }

   protected String withRoundedCornerBaseColor(String var1, String var2) {
      String var3 = this.firstHexColorPrefix(var1);
      String var4 = this.firstNonBlank(var2);
      String var5 = HEX_COLOR_TAG_PATTERN.matcher(var4).replaceAll("").trim();
      if (var5.isBlank()) {
         var5 = "\ue151";
      }

      String var6;
      if (var3.isBlank()) {
         var6 = var4.isBlank() ? var5 : var4;
      } else {
         var6 = var3 + var5;
      }

      return this.ensureRoundedCornerEditorFont(var6);
   }

   protected String ensureRoundedCornerEditorFont(String var1) {
      if (var1 == null || var1.isEmpty()) {
         return var1;
      } else {
         return var1.contains("<font:") ? var1 : "<font:editor>" + var1 + "</font>";
      }
   }

   protected String readRoundedCornerUnicode(ConfigurationSection var1) {
      return var1 == null
         ? ""
         : this.firstNonBlank(
            this.readRoundedCornerUnicodeByMode(var1, "tl"),
            var1.getString("rounding.unicode"),
            var1.getString("rounding.unicode.value"),
            var1.getString("rounding.unicode.char"),
            var1.getString("rounding.unicode.glyph"),
            var1.getString("rounded.unicode"),
            var1.getString("roundingUnicode"),
            var1.getString("cornerUnicode")
         );
   }

   protected String readRoundedCornerUnicodeTopLeft(ConfigurationSection var1) {
      return this.firstNonBlank(
         this.readRoundedCornerUnicodeByKey(var1, "tl"),
         this.readRoundedCornerUnicodeByKey(var1, "top_left"),
         this.readRoundedCornerUnicodeByKey(var1, "topLeft"),
         this.readRoundedCornerUnicodeByKey(var1, "top-left"),
         this.readRoundedCornerUnicodeByMode(var1, "tl"),
         this.readRoundedCornerUnicode(var1),
         "\uef60"
      );
   }

   protected String readRoundedCornerUnicodeTopRight(ConfigurationSection var1) {
      return this.firstNonBlank(
         this.readRoundedCornerUnicodeByKey(var1, "tr"),
         this.readRoundedCornerUnicodeByKey(var1, "top_right"),
         this.readRoundedCornerUnicodeByKey(var1, "topRight"),
         this.readRoundedCornerUnicodeByKey(var1, "top-right"),
         this.readRoundedCornerUnicodeByMode(var1, "tr"),
         this.readRoundedCornerUnicode(var1),
         "\uef61"
      );
   }

   protected String readRoundedCornerUnicodeBottomRight(ConfigurationSection var1) {
      return this.firstNonBlank(
         this.readRoundedCornerUnicodeByKey(var1, "br"),
         this.readRoundedCornerUnicodeByKey(var1, "bottom_right"),
         this.readRoundedCornerUnicodeByKey(var1, "bottomRight"),
         this.readRoundedCornerUnicodeByKey(var1, "bottom-right"),
         this.readRoundedCornerUnicodeByMode(var1, "br"),
         this.readRoundedCornerUnicode(var1),
         "\uef62"
      );
   }

   protected String readRoundedCornerUnicodeBottomLeft(ConfigurationSection var1) {
      return this.firstNonBlank(
         this.readRoundedCornerUnicodeByKey(var1, "bl"),
         this.readRoundedCornerUnicodeByKey(var1, "bottom_left"),
         this.readRoundedCornerUnicodeByKey(var1, "bottomLeft"),
         this.readRoundedCornerUnicodeByKey(var1, "bottom-left"),
         this.readRoundedCornerUnicodeByMode(var1, "bl"),
         this.readRoundedCornerUnicode(var1),
         "\uef63"
      );
   }

   protected String readRoundedCornerUnicodeByMode(ConfigurationSection var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = this.normalizeRoundedMode(var1.getString("rounding"));
         if (!var3.isBlank() && !"none".equals(var3)) {
            String var5 = var2.trim().toLowerCase(Locale.ROOT);

            byte var4 = switch (var5) {
               case "tl", "top_left", "topleft", "top-left" -> 0;
               case "tr", "top_right", "topright", "top-right" -> 1;
               case "br", "bottom_right", "bottomright", "bottom-right" -> 2;
               case "bl", "bottom_left", "bottomleft", "bottom-left" -> 3;
               default -> -1;
            };
            if (var4 < 0) {
               return "";
            } else {
               String[] var8 = switch (var3) {
                  case "small" -> ROUNDED_MODE_GLYPHS_SMALL;
                  case "medium" -> ROUNDED_MODE_GLYPHS_MEDIUM;
                  case "large" -> ROUNDED_MODE_GLYPHS_LARGE;
                  default -> ROUNDED_MODE_GLYPHS_REGULAR;
               };
               return var4 < var8.length ? this.firstNonBlank(var8[var4]) : "";
            }
         } else {
            return "";
         }
      } else {
         return "";
      }
   }

   protected String normalizeRoundedMode(String var1) {
      String var2 = this.firstNonBlank(var1).trim().toLowerCase(Locale.ROOT);
      if (var2.isBlank()) {
         return "";
      } else {
         return switch (var2) {
            case "small" -> "small";
            case "regular", "default", "normal" -> "regular";
            case "medium" -> "medium";
            case "large" -> "large";
            case "none", "off", "disabled", "false", "0" -> "none";
            default -> "";
         };
      }
   }

   protected String readRoundedCornerUnicodeByKey(ConfigurationSection var1, String var2) {
      return var1 != null && var2 != null && !var2.isBlank()
         ? this.firstNonBlank(
            var1.getString("rounding.unicode." + var2),
            var1.getString("rounded.unicode." + var2),
            var1.getString("rounding.corners." + var2),
            var1.getString("rounded.corners." + var2),
            var1.getString("rounding.corner." + var2),
            var1.getString("rounded.corner." + var2),
            var1.getString("roundingUnicode." + var2),
            var1.getString("cornerUnicode." + var2)
         )
         : "";
   }

   protected boolean isRoundedType(String var1) {
      String var2 = this.firstNonBlank(var1).toLowerCase(Locale.ROOT);
      return "block_rounded".equals(var2) || "rounded".equals(var2);
   }

   protected boolean isDirectionalRoundedType(String var1) {
      String var2 = this.firstNonBlank(var1).toLowerCase(Locale.ROOT);
      return "rounded".equals(var2);
   }

   protected double readRoundedTopRightOffsetX(ConfigurationSection var1) {
      return this.readRoundedCornerOffsetX(var1, "tr");
   }

   protected double readRoundedTopRightOffsetY(ConfigurationSection var1) {
      return this.readRoundedCornerOffsetY(var1, "tr");
   }

   protected double readRoundedTopLeftOffsetX(ConfigurationSection var1) {
      return this.readRoundedCornerOffsetX(var1, "tl");
   }

   protected double readRoundedTopLeftOffsetY(ConfigurationSection var1) {
      return this.readRoundedCornerOffsetY(var1, "tl");
   }

   protected double readRoundedBottomRightOffsetX(ConfigurationSection var1) {
      return this.readRoundedCornerOffsetX(var1, "br");
   }

   protected double readRoundedBottomRightOffsetY(ConfigurationSection var1) {
      return this.readRoundedCornerOffsetY(var1, "br");
   }

   protected double readRoundedBottomLeftOffsetX(ConfigurationSection var1) {
      return this.readRoundedCornerOffsetX(var1, "bl");
   }

   protected double readRoundedBottomLeftOffsetY(ConfigurationSection var1) {
      return this.readRoundedCornerOffsetY(var1, "bl");
   }

   protected double readRoundedCornerOffsetX(ConfigurationSection var1, String var2) {
      return var1 == null ? 0.0 : this.firstPresentDouble(var1, 0.0, this.roundedCornerOffsetPathCandidates(var2, "x"));
   }

   protected double readRoundedCornerOffsetY(ConfigurationSection var1, String var2) {
      return var1 == null ? 0.0 : this.firstPresentDouble(var1, 0.0, this.roundedCornerOffsetPathCandidates(var2, "y"));
   }

   protected String[] roundedCornerOffsetPathCandidates(String var1, String var2) {
      String var3 = this.firstNonBlank(var1).toLowerCase(Locale.ROOT);
      String var4 = this.firstNonBlank(var2).toLowerCase(Locale.ROOT);

      String var5 = switch (var3) {
         case "tl" -> "topLeft";
         case "tr" -> "topRight";
         case "br" -> "bottomRight";
         case "bl" -> "bottomLeft";
         default -> var3;
      };
      String var6 = "x".equals(var4) ? "OffsetX" : "OffsetY";
      return new String[]{
         "rounding.unicode." + var3 + "." + var4,
         "rounding." + var3 + "." + var4,
         "rounding." + var5 + "." + var4,
         "rounding." + var3 + var6,
         "rounded." + var3 + "." + var4,
         "rounded." + var5 + "." + var4
      };
   }

   protected double firstPresentDouble(ConfigurationSection var1, double var2, String... var4) {
      if (var1 != null && var4 != null && var4.length != 0) {
         for (Object var8_raw : var4) {
            String var8 = var8_raw != null ? var8_raw.toString() : null;
            if (var8 != null && !var8.isBlank() && var1.contains(var8)) {
               return this.parseDouble(var1.get(var8), var2);
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   protected String normalizeHexColor(String var1) {
      if (var1 == null) {
         return "";
      } else {
         String var2 = var1.trim();
         if (var2.startsWith("<#") && var2.endsWith(">") && var2.length() == 10) {
            var2 = var2.substring(2, 8);
         } else if (var2.startsWith("&#") && var2.length() == 8) {
            var2 = var2.substring(2);
         } else if (var2.startsWith("#") && var2.length() == 7) {
            var2 = var2.substring(1);
         }

         if (this.isSixDigitHexColor(var2)) {
            return var2.toLowerCase(Locale.ROOT);
         } else {
            if (this.isDecimalDigits(var2)) {
               try {
                  long var3 = Long.parseLong(var2);
                  if (var3 < 0L) {
                     return "";
                  }

                  if (var3 <= 262143L) {
                     String var5 = Long.toOctalString(var3);
                     if (var5.length() <= 6) {
                        return String.format(Locale.ROOT, "%6s", var5).replace(' ', '0');
                     }
                  }

                  if (var3 <= 16777215L) {
                     return String.format(Locale.ROOT, "%06x", var3);
                  }
               } catch (NumberFormatException var6) {
                  return "";
               }
            }

            return "";
         }
      }
   }

   private boolean isSixDigitHexColor(String var1) {
      if (var1 != null && var1.length() == 6) {
         for (int var2 = 0; var2 < var1.length(); var2++) {
            char var3 = var1.charAt(var2);
            boolean var4 = var3 >= '0' && var3 <= '9';
            boolean var5 = var3 >= 'a' && var3 <= 'f';
            boolean var6 = var3 >= 'A' && var3 <= 'F';
            if (!var4 && !var5 && !var6) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   private boolean isDecimalDigits(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         for (int var2 = 0; var2 < var1.length(); var2++) {
            char var3 = var1.charAt(var2);
            if (var3 < '0' || var3 > '9') {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   protected Map<String, Object> toStringObjectMap(Object var1) {
      if (var1 == null) {
         return null;
      } else {
         Map var2;
         if (var1 instanceof Map var4) {
            var2 = var4;
         } else {
            if (!(var1 instanceof ConfigurationSection var3)) {
               return null;
            }

            var2 = var3.getValues(false);
         }

         LinkedHashMap var7 = new LinkedHashMap();

         for (Map.Entry var6 : (Set<Map.Entry>)(Set)var2.entrySet()) {
            if (var6.getKey() != null) {
               var7.put(var6.getKey().toString(), var6.getValue());
            }
         }

         return var7;
      }
   }

   protected ConfigurationSection mapToSection(Map<?, ?> var1) {
      YamlConfiguration var2 = new YamlConfiguration();
      this.writeMapToSection(var2, "", var1);
      return var2;
   }

   protected void writeMapToSection(YamlConfiguration var1, String var2, Map<?, ?> var3) {
      for (Entry var5 : var3.entrySet()) {
         if (var5.getKey() != null) {
            String var6 = var5.getKey().toString();
            String var7 = var2.isEmpty() ? var6 : var2 + "." + var6;
            Object var8 = var5.getValue();
            if (var8 instanceof Map var9) {
               this.writeMapToSection(var1, var7, var9);
            } else {
               var1.set(var7, var8);
            }
         }
      }
   }

   protected void setBindingValue(Map<String, Object> var1, String var2, double var3) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         this.setMapPathValue(var1, var2, var3);
      }
   }

   protected Map<String, Object> resolveRawMapAtPath(List<Map<String, Object>> var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Object var3 = var1;
         String[] var4 = var2.split("\\.");

         for (Object var8_raw : var4) {
            String var8 = var8_raw != null ? var8_raw.toString() : null;
            if (var3 instanceof List var9) {
               int var10 = this.parsePathIndex(var8);
               if (var10 < 0 || var10 >= var9.size()) {
                  return null;
               }

               var3 = var9.get(var10);
            } else {
               if (!(var3 instanceof Map var11)) {
                  return null;
               }

               var3 = var11.get(var8);
            }
         }

         return var3 instanceof Map ? (Map)var3 : null;
      } else {
         return null;
      }
   }

   protected int parsePathIndex(String var1) {
      try {
         return Integer.parseInt(var1);
      } catch (Exception var3) {
         return -1;
      }
   }

   protected void setMapPathValue(Map<String, Object> var1, String var2, Object var3) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String[] var4 = var2.split("\\.");
         Map var5 = var1;

         for (int var6 = 0; var6 < var4.length - 1; var6++) {
            String var7 = var4[var6];
            if (var5.get(var7) instanceof Map var9) {
               var5 = var9;
            } else {
               LinkedHashMap var11 = new LinkedHashMap();
               var5.put(var7, var11);
               var5 = var11;
            }
         }

         var5.put(var4[var4.length - 1], var3);
      }
   }

   private static final class CachedYamlFile {
      private long lastModified = Long.MIN_VALUE;
      private long length = Long.MIN_VALUE;
      private boolean invalid;
      private String errorDetails = "";
      private String mapKey = "";
      private String fileName = "";
      private YamlConfiguration yaml;
   }
}
