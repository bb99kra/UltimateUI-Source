package dev.xqedii.ultimateUI;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Xqedii_3s68w2g {
   public static final String Xqedii_3i41w1u = "experimental.betterhud-support";
   public static final String Xqedii_228wccf = "betterhud_support";
   public static final String Xqedii_1eyel6w = "BetterHud";
   public static final String Xqedii_3d363ov = "kr.toxicity.hud.api.bukkit.event.PluginReloadedEvent";
   public static final String Xqedii_13vm4zs = ".source";
   public static final String Xqedii_kvx3wju = ".self-host-disabled";
   public static final String Xqedii_xunv8sf = "config.yml";
   public static final String Xqedii_22djlut = "BetterHud/build";
   public static final Pattern Xqedii_21sohag;
   public static final Pattern Xqedii_3ebn067;
   public static final Pattern Xqedii_3sn0hou;
   public static final Pattern Xqedii_1yr5est;
   public static final Pattern Xqedii_18mqikn;
   public static final String Xqedii_17k72te = "vec3 exp = vec3(128.0, 255.0, 32.0)";
   public static final String Xqedii_zq6856j = "SHADER_VERSION";
   public static final List<String> Xqedii_2711xik;
   public static final String Xqedii_ype0ruy = "void main()";
   public static final String Xqedii_2n7d1d9 = "if (make_hud(";
   public static final int Xqedii_17ixqvv = 56;
   public final JavaPlugin Xqedii_2ds4r1g;

   public Xqedii_3s68w2g(JavaPlugin var1) {
      this.Xqedii_2ds4r1g = var1;
   }

   public boolean Xqedii_3mpa5xq() {
      {
         return this.Xqedii_2ds4r1g
            .getConfig()
            .getBoolean(
               "experimental.betterhud-support",
               false
            );
      }
   }

   public File Xqedii_2wuw87l() {
      {
         File var1 = this.Xqedii_2ds4r1g.getDataFolder().getParentFile();
         return var1 == null ? null : new File(var1, "BetterHud");
      }
   }

   public File Xqedii_2kbxz97(File var1) {
      {
         return var1 == null
            ? null
            : new File(var1, "betterhud_support");
      }
   }

   public void Xqedii_13zsdhk(File var1) {
      {
         File var2 = this.Xqedii_2kbxz97(var1);
         if (var2 != null) {
            if (!this.Xqedii_3mpa5xq()) {
               this.Xqedii_2hclelv(var2);
            } else {
               File var3 = this.Xqedii_2wuw87l();
               if (var3 != null && var3.isDirectory()) {
                  if (!var2.exists()) {
                     var2.mkdirs();
                  }

                  this.Xqedii_1obpfd4(var3, var2);
                  this.Xqedii_1ldniqr(var3, var2);
               }
            }
         }
      }
   }

   public boolean Xqedii_21a7gm4(File var1, Map<String, byte[]> var2) {
      {
         File var3 = this.Xqedii_2kbxz97(var1);
         File var4 = this.Xqedii_2wuw87l();
         if (this.Xqedii_3mpa5xq() && var3 != null && var4 != null && var2 != null && !var2.isEmpty()) {
            boolean var5 = var2.keySet()
               .stream()
               .map(Xqedii_3s68w2g::Xqedii_17hk53d)
               .anyMatch(
                  var0 -> {
                     {
                        return ((!var0.equals(
                                 "pack.mcmeta"
                              )
                              && !var0.endsWith("/pack.mcmeta")
                           ? 0
                           : 1) != 0);
                     }
                  }
               );
            if (!var5) {
               return false;
            } else {
               try {
                  PackSourceFile var6 = this.Xqedii_5xza4zl(var4);
                  String var7 = var6 == null ? Xqedii_3b4y8oj4w98ky(System.currentTimeMillis()) : var6.signature();
                  this.Xqedii_y72mhw6(var3, var2, var7);
                  this.Xqedii_1ldniqr(var4, var3);
                  return true;
               } catch (IOException var8) {
                  this.Xqedii_2ds4r1g.getLogger().warning(Xqedii_8a8y5a27je1p(var8.getMessage()));
                  return false;
               }
            }
         } else {
            return false;
         }
      }
   }

   public boolean Xqedii_3ihj4ot() {
      {
         File var1 = this.Xqedii_2wuw87l();
         if (var1 == null) {
            return false;
         } else {
            File var2 = new File(var1, "config.yml");
            return (!var2.isFile()
               ? false
               : YamlConfiguration.loadConfiguration(var2)
                  .getBoolean(
                     "enable-self-host",
                     false
                  ));
         }
      }
   }

   public long Xqedii_13iri1g(File var1) {
      if (!this.Xqedii_3mpa5xq()) {
         return 0L;
      } else {
         File var2 = this.Xqedii_2kbxz97(var1);
         long var3 = 7919L;

         for (Path var6 : this.Xqedii_29giocr(var2)) {
            File var7 = var6.toFile();
            var3 = 31L * var3 + Xqedii_3c4jmju(var2.toPath(), var6).hashCode();
            var3 = 31L * var3 + Long.hashCode(var7.lastModified());
            var3 = 31L * var3 + Long.hashCode(var7.length());
         }

         return var3;
      }
   }

   public Xqedii_2om8ulg Xqedii_cl3zryp(File var1, File var2) {
      if (!this.Xqedii_3mpa5xq()) {
         return null;
      } else {
         File var3 = this.Xqedii_2kbxz97(var1);
         List var4 = this.Xqedii_29giocr(var3);
         if (var4.isEmpty()) {
            return null;
         } else {
            Set var5 = this.Xqedii_390vif1(
               new File(var3, "pack.mcmeta")
            );
            ShaderConfiguration var6 = this.Xqedii_1m7vvf7(var3.toPath(), var4);
            if (var6 == null) {
               this.Xqedii_2ds4r1g
                  .getLogger()
                  .warning(
                     "[UltimateUI] BetterHud support: no BetterHud text shader with a layout switch was found in betterhud_support, BetterHud HUDs will not be positioned."
                  );
            }

            Set var7 = this.Xqedii_1k1y33b(
               new File(var2, "pack.mcmeta")
            );
            return new Xqedii_2om8ulg(var3.toPath(), var4, var5, var7, var6);
         }
      }
   }

   public static Map<String, byte[]> Xqedii_yudau0p(Object var0) {
      if (var0 == null) {
         return null;
      } else {
         try {
            Method var1 = var0.getClass().getMethod("getState");
            Object var2 = var1.invoke(var0);
            if (var2 == null) {
               return null;
            } else {
               Method var3;
               try {
                  var3 = var2.getClass()
                     .getMethod("resourcePack");
               } catch (NoSuchMethodException var11) {
                  return null;
               }

               if (var3.invoke(var2) instanceof Map var5) {
                  LinkedHashMap var6 = new LinkedHashMap();

                  for (Entry var8 : (Iterable<Entry>) (var5.entrySet())) {

                     if (var8.getKey() != null && var8.getValue() instanceof byte[] var9) {
                        var6.put(var8.getKey().toString(), var9);
                     }
                  }

                  return var6;
               } else {
                  return null;
               }
            }
         } catch (RuntimeException | ReflectiveOperationException var12) {
            return null;
         }
      }
   }

   public void Xqedii_1obpfd4(File var1, File var2) {
      {
         PackSourceFile var3 = this.Xqedii_5xza4zl(var1);
         if (var3 == null) {
            if (this.Xqedii_29giocr(var2).isEmpty()) {
               this.Xqedii_2ds4r1g
                  .getLogger()
                  .warning(
                     "[UltimateUI] BetterHud support: BetterHud has not generated its resource pack yet (expected BetterHud/build.zip or the build folder)."
                  );
            }
         } else {
            String var4 = var3.signature();
            if (!var4.equals(this.Xqedii_2yk5vms(var2, ".source"))
               || this.Xqedii_29giocr(var2).isEmpty()) {
               try {
                  Map var5 = var3.read();
                  if (var5.isEmpty() || !this.Xqedii_y72mhw6(var2, var5, var4)) {
                     return;
                  }

                  this.Xqedii_2ds4r1g.getLogger().info(Xqedii_367l7l3ah05cc(var5.size()));
               } catch (IOException var6) {
                  this.Xqedii_2ds4r1g.getLogger().warning(Xqedii_2ss459xuhxrpl(var6.getMessage()));
               }
            }
         }
      }
   }

   public PackSourceFile Xqedii_5xza4zl(File var1) {
      {
         File var2 = var1.getParentFile();
         if (var2 == null) {
            return null;
         } else {
            YamlConfiguration var3 = YamlConfiguration.loadConfiguration(
               new File(var1, "config.yml")
            );
            String var4 = var3.getString(
               "build-folder-location",
               "BetterHud/build"
            );
            if (var4 == null || var4.isBlank()) {
               var4 = "BetterHud/build";
            }

            File var5 = new File(var2, Xqedii_mua4vden5em3(var4));
            File var6 = new File(var2, var4);
            boolean var7 = "folder"
               .equalsIgnoreCase(
                  var3.getString(
                     "pack-type",
                     "zip"
                  )
               );
            if (var7 && this.Xqedii_leqvfr8(var6)) {
               return new PackSourceFile(var6, false);
            } else if (var5.isFile()) {
               return new PackSourceFile(var5, true);
            } else {
               return this.Xqedii_leqvfr8(var6) ? new PackSourceFile(var6, false) : null;
            }
         }
      }
   }

   public boolean Xqedii_leqvfr8(File var1) {
      {
         return ((var1 != null && var1.isDirectory() && !this.Xqedii_320jcu7(var1).isEmpty()
            ? 1
            : 0) != 0);
      }
   }

   public boolean Xqedii_y72mhw6(File var1, Map<String, byte[]> var2, String var3) throws IOException {
      {
         Map var4 = this.Xqedii_2r8bb1p(var2);
         if (var4.isEmpty()) {
            return false;
         } else {
            Files.createDirectories(var1.toPath());
            if (this.Xqedii_e66zzcu(var1, var4)) {
               this.Xqedii_3iime3y(var1, ".source", var3);
               return false;
            } else {
               this.Xqedii_1inzgp4(var1);
               Path var5 = var1.toPath().toAbsolutePath().normalize();

               for (Entry var7 : (Iterable<Entry>) (var4.entrySet())) {

                  Path var8 = var5.resolve((String)var7.getKey()).normalize();
                  if (var8.startsWith(var5) && !var8.equals(var5)) {
                     Files.createDirectories(var8.getParent());
                     Files.write(var8, (byte[])var7.getValue());
                  }
               }

               this.Xqedii_3iime3y(var1, ".source", var3);
               return true;
            }
         }
      }
   }

   public boolean Xqedii_e66zzcu(File var1, Map<String, byte[]> var2) throws IOException {
      {
         List var3 = this.Xqedii_29giocr(var1);
         if (var3.size() != var2.size()) {
            return false;
         } else {
            Path var4 = var1.toPath();

            for (Path var6 : (Iterable<Path>) (var3)) {

               byte[] var7 = (byte[])var2.get(Xqedii_3c4jmju(var4, var6));
               if (var7 == null || !Arrays.equals(var7, Files.readAllBytes(var6))) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public Map<String, byte[]> Xqedii_2r8bb1p(Map<String, byte[]> var1) {
      {
         String var2 = "";
         int var3 = 2147483647;

         for (String var5 : var1.keySet()) {
            String var6 = Xqedii_17hk53d(var5);
            if ((
                  var6.equals("pack.mcmeta")
                     || var6.endsWith("/pack.mcmeta")
               )
               && var6.length() < var3) {
               var3 = var6.length();
               var2 = var6.substring(
                  0,
                  var6.length() - "pack.mcmeta".length()
               );
            }
         }

         LinkedHashMap var8 = new LinkedHashMap();

         for (Entry var10 : var1.entrySet()) {
            String var7 = Xqedii_17hk53d((String)var10.getKey());
            if (!var2.isEmpty()) {
               if (!var7.startsWith(var2)) {
                  continue;
               }

               var7 = var7.substring(var2.length());
            }

            if (!var7.isBlank() && !var7.endsWith("/") && var10.getValue() != null) {
               var8.put(var7, (byte[])var10.getValue());
            }
         }

         return var8;
      }
   }

   public static String Xqedii_17hk53d(String var0) {
      {
         String var1 = var0 == null
            ? ""
            : var0.replace((char)92, (char)47).trim();

         while (var1.startsWith("/")) {
            var1 = var1.substring(1);
         }

         return var1;
      }
   }

   public void Xqedii_1inzgp4(File var1) throws IOException {
      {
         File[] var2 = var1.listFiles();
         if (var2 != null) {
            File[] var3 = var2;
            int var4 = var2.length;

            for (int var5 = 0; var5 < var4; var5++) {
               File var6 = var3[var5];
               if (!var6.getName().startsWith(".")) {
                  this.Xqedii_vprrvg3(var6.toPath());
               }
            }
         }
      }
   }

   public void Xqedii_vprrvg3(Path var1) throws IOException {
      if (Files.exists(var1)) {
         try (Stream var2 = Files.walk(var1)) {
            for (Path var5 : (Iterable<Path>) (var2.sorted(Comparator.reverseOrder()).toList())) {

               Files.deleteIfExists(var5);
            }
         }
      }
   }

   public List<Path> Xqedii_29giocr(File var1) {
      {
         ArrayList var2 = new ArrayList();
         if (var1 != null && var1.isDirectory()) {
            Path var3 = var1.toPath();

            for (Path var5 : this.Xqedii_320jcu7(var1)) {
               if (!Xqedii_3c4jmju(var3, var5).startsWith(".")) {
                  var2.add(var5);
               }
            }

            return var2;
         } else {
            return var2;
         }
      }
   }

   public List<Path> Xqedii_320jcu7(File var1) {
      if (var1 != null && var1.isDirectory()) {
         try {
            List var3;
            try (Stream<Path> var2 = Files.walk(var1.toPath())) {
               var3 = var2.filter(var0 -> {
                  {
                     return Files.isRegularFile(var0);
                  }
               }).sorted(Comparator.comparing(var0 -> {
                  {
                     return var0.toString().toLowerCase(Locale.ROOT);
                  }
               })).toList();
            }

            return var3;
         } catch (IOException var7) {
            return new ArrayList<>();
         }
      } else {
         return new ArrayList<>();
      }
   }

   public static String Xqedii_3c4jmju(Path var0, Path var1) {
      {
         return var0.relativize(var1)
            .toString()
            .replace((char)92, (char)47);
      }
   }

   public String Xqedii_2yk5vms(File var1, String var2) {
      {
         File var3 = new File(var1, var2);
         if (!var3.isFile()) {
            return "";
         } else {
            try {
               return Files.readString(var3.toPath(), StandardCharsets.UTF_8).trim();
            } catch (IOException var5) {
               return "";
            }
         }
      }
   }

   public void Xqedii_3iime3y(File var1, String var2, String var3) throws IOException {
      {
         Files.writeString(
            new File(var1, var2).toPath(),
            var3 == null ? "" : var3,
            StandardCharsets.UTF_8
         );
      }
   }

   public void Xqedii_1ldniqr(File var1, File var2) {
      {
         File var3 = new File(var1, "config.yml");

         try {
            if (this.Xqedii_3uwlsf9(var3, false)) {
               this.Xqedii_3iime3y(
                  var2,
                  ".self-host-disabled",
                  "true"
               );
               this.Xqedii_2ds4r1g
                  .getLogger()
                  .info(
                     "[UltimateUI] BetterHud support: set enable-self-host to false in BetterHud/config.yml, UltimateUI now sends one pack with both. Run /betterhud reload or restart."
                  );
            }
         } catch (IOException var5) {
            this.Xqedii_2ds4r1g.getLogger().warning(Xqedii_3jm1abql1fe6y(var5.getMessage()));
         }
      }
   }

   public void Xqedii_2hclelv(File var1) {
      {
         File var2 = new File(
            var1, ".self-host-disabled"
         );
         if (var2.isFile()) {
            File var3 = this.Xqedii_2wuw87l();

            try {
               if (var3 != null
                  && this.Xqedii_3uwlsf9(
                     new File(var3, "config.yml"),
                     true
                  )) {
                  this.Xqedii_2ds4r1g
                     .getLogger()
                     .info(
                        "[UltimateUI] BetterHud support is off: restored enable-self-host: true in BetterHud/config.yml. Run /betterhud reload or restart."
                     );
               }

               Files.deleteIfExists(var2.toPath());
            } catch (IOException var5) {
               this.Xqedii_2ds4r1g.getLogger().warning(Xqedii_3y6nf3zehy3o(var5.getMessage()));
            }
         }
      }
   }

   public boolean Xqedii_3uwlsf9(File var1, boolean var2) throws IOException {
      if (var1 != null && var1.isFile()) {
         String var3 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
         String var4 = var3.contains("\r\n")
            ? "\r\n"
            : "\n";
         String[] var5 = var3.split(
            "\r?\n", -1
         );
         String var6 = Boolean.toString(var2);
         int var7 = 0;

         for (int var8 = 0; var8 < var5.length; var8++) {
            Matcher var9 = Xqedii_21sohag.matcher(var5[var8]);
            if (var9.matches()) {
               String var10 = var9.group(1)
                  .replace(
                     "'",
                     ""
                  )
                  .replace(
                     "\"",
                     ""
                  );
               if (!var10.equalsIgnoreCase(var6)) {
                  var5[var8] = Xqedii_2irrdsz2ywswy(var6, var9.group(2));
                  var7 = 1;
               }
               break;
            }
         }

         if (var7 != 0) {
            Files.writeString(var1.toPath(), String.join(var4, var5), StandardCharsets.UTF_8);
         }

         return (var7 != 0);
      } else {
         return false;
      }
   }

   public Set<String> Xqedii_390vif1(File var1) {
      {
         HashSet var2 = new HashSet();
         JsonObject var3 = this.Xqedii_32zucky(var1);
         JsonArray var4 = this.Xqedii_1r7v5u7(var3);
         if (var4 == null) {
            return var2;
         } else {
            for (JsonElement var6 : var4) {
               if (var6.isJsonObject()
                  && var6.getAsJsonObject().has("directory")) {
                  var2.add(
                     var6.getAsJsonObject()
                        .get("directory")
                        .getAsString()
                  );
               }
            }

            return var2;
         }
      }
   }

   public Set<String> Xqedii_1k1y33b(File var1) {
      {
         HashSet var2 = new HashSet();
         JsonArray var3 = this.Xqedii_1r7v5u7(this.Xqedii_32zucky(var1));
         if (var3 == null) {
            return var2;
         } else {
            for (JsonElement var5 : var3) {
               if (var5.isJsonObject()) {
                  JsonObject var6 = var5.getAsJsonObject();
                  if (var6.has("directory")) {
                     int var7 = this.Xqedii_sbqnvji(var6);
                     if (var7 >= 0 && var7 < 56) {
                        var2.add(
                           var6.get("directory").getAsString()
                        );
                     }
                  }
               }
            }

            return var2;
         }
      }
   }

   public int Xqedii_sbqnvji(JsonObject var1) {
      {
         try {
            if (var1.has("max_format")
               && var1.get("max_format").isJsonPrimitive()) {
               return var1.get("max_format").getAsInt();
            }

            JsonElement var2 = var1.get("formats");
            if (var2 == null) {
               return -1;
            }

            if (var2.isJsonPrimitive()) {
               return var2.getAsInt();
            }

            if (var2.isJsonArray() && var2.getAsJsonArray().size() > 0) {
               JsonArray var3 = var2.getAsJsonArray();
               return var3.get(var3.size() - 1).getAsInt();
            }

            if (var2.isJsonObject()
               && var2.getAsJsonObject().has("max_inclusive")) {
               return var2.getAsJsonObject()
                  .get("max_inclusive")
                  .getAsInt();
            }
         } catch (RuntimeException var4) {
         }

         return -1;
      }
   }

   public JsonArray Xqedii_1r7v5u7(JsonObject var1) {
      if (var1 != null
         && var1.has("overlays")
         && var1.get("overlays").isJsonObject()) {
         JsonElement var2 = var1.getAsJsonObject("overlays")
            .get("entries");
         return var2 != null && var2.isJsonArray() ? var2.getAsJsonArray() : null;
      } else {
         return null;
      }
   }

   public JsonObject Xqedii_32zucky(File var1) {
      if (var1 != null && var1.isFile()) {
         try {
            JsonElement var2 = new JsonParser().parse(Files.readString(var1.toPath(), StandardCharsets.UTF_8));
            return var2.isJsonObject() ? var2.getAsJsonObject() : null;
         } catch (RuntimeException | IOException var3) {
            return null;
         }
      } else {
         return null;
      }
   }

   public ShaderConfiguration Xqedii_1m7vvf7(Path var1, List<Path> var2) {
      {
         Iterator var3 = var2.iterator();

         while (true) {
            String var5;
            while (true) {
               if (!var3.hasNext()) {
                  return null;
               }

               Path var4 = (Path)var3.next();
               if (Xqedii_1yr5est.matcher(Xqedii_3c4jmju(var1, var4)).matches()) {
                  try {
                     var5 = Files.readString(var4, StandardCharsets.UTF_8);
                     break;
                  } catch (IOException var15) {
                  }
               }
            }

            String var6 = this.Xqedii_2ok16kq(var5);
            if (var6 != null) {
               ArrayList var7 = new ArrayList();
               HashSet var8 = new HashSet();
               int var9 = 0;
               String[] var10 = var5.split("\r?\n");
               int var11 = var10.length;

               for (int var12 = 0; var12 < var11; var12++) {
                  String var13 = var10[var12];
                  Matcher var14 = Xqedii_3sn0hou.matcher(var13);
                  if (var14.matches()
                     && !"SHADER_VERSION"
                        .equals(var14.group(1))) {
                     var7.add(var13.trim());
                     var8.add(var14.group(1));
                  }

                  if (var13.contains(
                        "vec3 exp = vec3(128.0, 255.0, 32.0)"
                     )
                     && !var13.trim().startsWith("//")) {
                     var9 = 1;
                  }
               }

               if (var8.containsAll(Xqedii_2711xik)) {
                  return new ShaderConfiguration(var7, var6, (var9 != 0));
               }
            }
         }
      }
   }

   public String Xqedii_2ok16kq(String var1) {
      {
         Matcher var2 = Xqedii_3ebn067.matcher(var1);
         if (!var2.find()) {
            return null;
         } else {
            int var3 = var2.end();
            int var4 = 1;

            for (int var5 = var3; var5 < var1.length(); var5++) {
               char var6 = var1.charAt(var5);
               if (var6 == 123) {
                  var4++;
               } else if (var6 == 125) {
                  if (--var4 == 0) {
                     return var1.substring(var3, var5).trim();
                  }
               }
            }

            return null;
         }
      }
   }

   static {
      {
         Xqedii_21sohag = Pattern.compile(
            "^enable-self-host\\s*:\\s*([^#\\s]+)(.*)$"
         );
         Xqedii_3ebn067 = Pattern.compile(
            "switch\\s*\\(\\s*id\\s*\\)\\s*\\{"
         );
         Xqedii_3sn0hou = Pattern.compile(
            "^\\s*#define\\s+([A-Za-z_][A-Za-z0-9_]*)(?:\\s.*)?$"
         );
         Xqedii_1yr5est = Pattern.compile(
            "^(?:[^/]+/)?assets/minecraft/shaders/core/(?:rendertype_text|text)\\.vsh$"
         );
         Xqedii_18mqikn = Pattern.compile(
            "^(?:([^/]+)/)?assets/minecraft/shaders/core/(?:rendertype_text|text)\\.vsh$"
         );
         Xqedii_2711xik = List.of(
            "HEIGHT_BIT",
            "MAX_BIT",
            "ADD_OFFSET",
            "DEFAULT_OFFSET"
         );
      }
   }

   private static /* synthetic */ String Xqedii_3b4y8oj4w98ky(long l) {
        return "event:" + l;
    }

    private static /* synthetic */ String Xqedii_8a8y5a27je1p(String string) {
        return "[UltimateUI] BetterHud support: failed to store the reloaded BetterHud pack: " + string;
    }

    private static /* synthetic */ String Xqedii_367l7l3ah05cc(int n) {
        return "[UltimateUI] BetterHud support: copied the BetterHud resource pack into resourcepack/betterhud_support (" + n + " files).";
    }

    private static /* synthetic */ String Xqedii_2ss459xuhxrpl(String string) {
        return "[UltimateUI] BetterHud support: failed to copy the BetterHud resource pack: " + string;
    }

    private static /* synthetic */ String Xqedii_mua4vden5em3(String string) {
        return string + ".zip";
    }

    private static /* synthetic */ String Xqedii_3jm1abql1fe6y(String string) {
        return "[UltimateUI] BetterHud support: could not turn off BetterHud self-host: " + string;
    }

    private static /* synthetic */ String Xqedii_3y6nf3zehy3o(String string) {
        return "[UltimateUI] BetterHud support: could not restore BetterHud self-host: " + string;
    }

    private static /* synthetic */ String Xqedii_2irrdsz2ywswy(String string, String string2) {
        return "enable-self-host: " + string + string2;
    }
}
