package dev.xqedii.ultimateUI;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Xqedii_12bi5q4 {
   public static final Pattern Xqedii_yovgrx8;
   public static final String Xqedii_3vkx8q9 = "contents/ultimateui/resourcepack";
   public static final String Xqedii_24nvj29 = "pack";
   public static final String Xqedii_1i3rtx0 = "pack/external_packs";
   public static final String Xqedii_22f2a4h = "resourcepack.zip";
   public final JavaPlugin Xqedii_1p68vxq;

   public Xqedii_12bi5q4(JavaPlugin var1) {
      this.Xqedii_1p68vxq = var1;
   }

   public File Xqedii_1kxrs0m(CustomItemPlugin var1) {
      {
         File var2 = this.Xqedii_1p68vxq.getDataFolder().getParentFile();
         return var2 == null ? null : new File(var2, var1.Xqedii_lho1nuv());
      }
   }

   public CustomItemHookResult Xqedii_278kok9(CustomItemPlugin var1, File var2) throws IOException {
      {
         File var3 = this.Xqedii_1kxrs0m(var1);
         if (var3 != null && var3.isDirectory()) {
            ArrayList var4 = new ArrayList();
            switch (var1) {
               case ITEMSADDER:
                  File var8 = new File(var3, "config.yml");
                  String[] var12 = new String[2];
                  var12[0] = "resource-pack.zip.extract_internal_utility_assets";
                  var12[1] = "zip.extract_internal_utility_assets";
                  this.Xqedii_1oajq4y(var1, var8, var4, var12);
                  var12 = new String[1];
                  var12[0] = "text_effects.enabled";
                  this.Xqedii_1oajq4y(var1, var8, var4, var12);
                  Path var9 = var3.toPath()
                     .resolve(
                        "contents/ultimateui/resourcepack"
                     );
                  this.Xqedii_26ro3w8(var9);
                  this.Xqedii_14n5qio(var2, var9, false);
                  return new CustomItemHookResult(
                     var1,
                     var4,
                     List.of(
                        "iareload",
                        "iazip"
                     )
                  );
               case NEXO:
                  File var7 = new File(var3, "settings.yml");
                  String[] var11 = new String[1];
                  var11[0] = "import.default_assets";
                  this.Xqedii_1oajq4y(var1, var7, var4, var11);
                  Path var6 = var3.toPath()
                     .resolve("pack/external_packs");
                  Files.createDirectories(var6);
                  Path var10000 = var2.toPath();
                  Path var10001 = var6.resolve(
                     "resourcepack.zip"
                  );
                  CopyOption[] var10002 = new CopyOption[1];
                  var10002[0] = StandardCopyOption.REPLACE_EXISTING;
                  Files.copy(var10000, var10001, var10002);
                  return new CustomItemHookResult(
                     var1, var4, List.of("nexo reload")
                  );
               case ORAXEN:
                  File var5 = new File(var3, "settings.yml");
                  String[] var10004 = new String[1];
                  var10004[0] = "generation.default_assets";
                  this.Xqedii_1oajq4y(var1, var5, var4, var10004);
                  var10004 = new String[1];
                  var10004[0] = "generation.default_configs";
                  this.Xqedii_1oajq4y(var1, var5, var4, var10004);
                  this.Xqedii_14n5qio(
                     var2,
                     var3.toPath().resolve("pack"),
                     true
                  );
                  return new CustomItemHookResult(
                     var1, var4, List.of("oraxen reload")
                  );
               default:
                  return null;
            }
         } else {
            return null;
         }
      }
   }

   public void Xqedii_1oajq4y(CustomItemPlugin var1, File var2, List<String> var3, String... var4) throws IOException {
      if (var2 != null && var2.isFile() && var4.length != 0) {
         String var5 = Files.readString(var2.toPath(), StandardCharsets.UTF_8);
         String var6 = var5.contains("\r\n")
            ? "\r\n"
            : "\n";
         String[] var7 = var5.split(
            "\r?\n", -1
         );
         String[] var8 = var4;
         int var9 = var4.length;

         for (int var10 = 0; var10 < var9; var10++) {
            String var11 = var8[var10];
            int var12 = this.Xqedii_2itmi4u(var7, var11);
            if (var12 >= 0) {
               Matcher var13 = Xqedii_yovgrx8.matcher(var7[var12]);
               if (var13.matches()) {
                  String var14 = var13.group(3);
                  int var15 = this.Xqedii_2o7kh0a(var14);
                  String var16 = (var15 >= 0 ? var14.substring(0, var15) : var14).trim();
                  if (!var16.isEmpty()) {
                     if (var16.equalsIgnoreCase("false")) {
                        return;
                     }

                     String var17 = var15 >= 0
                        ? Xqedii_2vpm7fh1fjy5p(var14.substring(var15).trim())
                        : "";
                     var7[var12] = Xqedii_1p717hep0rprc(
                        var13.group(1),
                        var13.group(2),
                        var17
                     );
                     Files.writeString(var2.toPath(), String.join(var6, var7), StandardCharsets.UTF_8);
                     var3.add(Xqedii_22x5timzseqmt(var1.Xqedii_lho1nuv(), var2.getName(), var11));
                     return;
                  }
               }
            }
         }

         YamlConfiguration var18 = YamlConfiguration.loadConfiguration(var2);
         var18.set(var4[0], Boolean.valueOf(false));
         var18.save(var2);
         var3.add(Xqedii_37cggqre3i9m5(var1.Xqedii_lho1nuv(), var2.getName(), var4[0]));
      }
   }

   public int Xqedii_2itmi4u(String[] var1, String var2) {
      {
         ArrayList<Integer> var3 = new ArrayList<>();
         ArrayList var4 = new ArrayList();

         for (int var5 = 0; var5 < var1.length; var5++) {
            String var6 = var1[var5];
            String var7 = var6.trim();
            if (!var7.isEmpty()
               && !var7.startsWith("#")
               && !var7.startsWith("-")) {
               Matcher var8 = Xqedii_yovgrx8.matcher(var6);
               if (var8.matches()) {
                  int var9 = var8.group(1).length();

                  while (!var3.isEmpty() && var3.get(var3.size() - 1) >= var9) {
                     var3.remove(var3.size() - 1);
                     var4.remove(var4.size() - 1);
                  }

                  var3.add(var9);
                  var4.add(this.Xqedii_2la92pk(var8.group(2)));
                  if (String.join(".", var4).equals(var2)) {
                     return var5;
                  }
               }
            }
         }

         return -1;
      }
   }

   public int Xqedii_2o7kh0a(String var1) {
      {
         for (int var2 = 0; var2 < var1.length(); var2++) {
            if (var1.charAt(var2) == 35
               && (var2 == 0 || Character.isWhitespace(var1.charAt(var2 - 1)))) {
               return var2;
            }
         }

         return -1;
      }
   }

   public String Xqedii_2la92pk(String var1) {
      {
         return var1.length() < 2
               || (
                     !var1.startsWith("'")
                        || !var1.endsWith("'")
                  )
                  && (
                     !var1.startsWith("\"")
                        || !var1.endsWith("\"")
                  )
            ? var1
            : var1.substring(1, var1.length() - 1);
      }
   }

   public void Xqedii_14n5qio(File var1, Path var2, boolean var3) throws IOException {
      {
         Path var4 = var2.toAbsolutePath().normalize();
         Files.createDirectories(var4);

         try (ZipFile var5 = new ZipFile(var1)) {
            Enumeration var6 = var5.entries();

            while (var6.hasMoreElements()) {
               ZipEntry var7 = (ZipEntry)var6.nextElement();
               String var8 = var7.getName();
               if (!var3
                  || !var8.equals("pack.mcmeta")
                     && !var8.equals("pack.png")) {
                  Path var9 = var4.resolve(var8).normalize();
                  if (var9.startsWith(var4)) {
                     if (var7.isDirectory()) {
                        Files.createDirectories(var9);
                     } else {
                        Files.createDirectories(var9.getParent());

                        try (InputStream var10 = var5.getInputStream(var7)) {
                           CopyOption[] var10002 = new CopyOption[1];
                           var10002[0] = StandardCopyOption.REPLACE_EXISTING;
                           Files.copy(var10, var9, var10002);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public void Xqedii_26ro3w8(Path var1) throws IOException {
      if (Files.exists(var1)) {
         try (Stream var2 = Files.walk(var1)) {
            for (Path var4 : (Iterable<Path>) (var2.sorted(Comparator.reverseOrder()).toList())) {

               Files.deleteIfExists(var4);
            }
         }
      }
   }

   static {
      {
         Xqedii_yovgrx8 = Pattern.compile(
            "^(\\s*)([A-Za-z0-9_.\\-]+|'[^']*'|\"[^\"]*\")\\s*:(.*)$"
         );
      }
   }

   private static /* synthetic */ String Xqedii_2vpm7fh1fjy5p(String string) {
        return " " + string;
    }

    private static /* synthetic */ String Xqedii_1p717hep0rprc(String string, String string2, String string3) {
        return string + string2 + ": false" + string3;
    }

    private static /* synthetic */ String Xqedii_22x5timzseqmt(String string, String string2, String string3) {
        return string + "/" + string2 + " -> " + string3 + ": false";
    }

    private static /* synthetic */ String Xqedii_37cggqre3i9m5(String string, String string2, String string3) {
        return string + "/" + string2 + " -> " + string3 + ": false";
    }
}
