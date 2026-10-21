package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.bossbar.BossbarAssets;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.bukkit.configuration.file.YamlConfiguration;

public final class Xqedii_21nanys {
   public static final String[] Xqedii_1xoisfr;
   public static final String Xqedii_31azpsf = "if (make_hud(";
   public static final String Xqedii_1hk4j9t = "void main()";
   public static final String Xqedii_2tjbow8 = "if (color.a < 0.1)";
   public static final String Xqedii_38lcl4t = "uuiBbCorner";
   public static final String[] Xqedii_38aphfl;
   public static final Set<String> Xqedii_2b9wdyj;
   public final UltimateUI Xqedii_m0nzvfj;
   public final BossbarAssets Xqedii_1thwoqs = new BossbarAssets();
   public final Map<String, Xqedii_3juyhjk> Xqedii_sosos42 = new LinkedHashMap<>();
   public final Map<String, byte[]> Xqedii_3kzv666 = new LinkedHashMap<>();
   public String Xqedii_16gfg65;
   public String Xqedii_2q432gd;
   public volatile boolean Xqedii_271fagi;
   public volatile List<String> Xqedii_qu8zghw;

   public Xqedii_21nanys(UltimateUI var1) {
      this.Xqedii_m0nzvfj = var1;
   }

   public boolean Xqedii_39gjfbc() {
      {
         return ((!this.Xqedii_7bgzqhm() && this.Xqedii_4sqpfdi().isEmpty()
            ? 0
            : 1) != 0);
      }
   }

   public boolean Xqedii_7bgzqhm() {
      {
         return this.Xqedii_m0nzvfj
            .getConfig()
            .getBoolean(
               "experimental.shaders-editor",
               false
            );
      }
   }

   public List<String> Xqedii_4sqpfdi() {
      {
         List var1 = this.Xqedii_qu8zghw;
         if (var1 == null) {
            var1 = this.Xqedii_hirtos6();
            this.Xqedii_qu8zghw = var1;
         }

         return var1;
      }
   }

   public List<String> Xqedii_hirtos6() {
      {
         ArrayList var1 = new ArrayList();
         String[] var2 = Xqedii_38aphfl;
         int var3 = var2.length;

         for (int var4 = 0; var4 < var3; var4++) {
            String var5 = var2[var4];
            File[] var6 = new File(this.Xqedii_m0nzvfj.getDataFolder(), var5).listFiles((var0, var1x) -> {
               {
                  return var1x.toLowerCase(Locale.ROOT).endsWith(".yml");
               }
            });
            if (var6 != null) {
               Arrays.sort((Object[])var6);
               File[] var7 = var6;
               int var8 = var6.length;

               for (int var9 = 0; var9 < var8; var9++) {
                  File var10 = var7[var9];
                  String var11 = var10.getName();
                  String var12 = var11.substring(
                     0, var11.length() - 4
                  );
                  if (!Xqedii_2b9wdyj.contains(var12.toLowerCase(Locale.ROOT))) {
                     String var13 = this.Xqedii_2b5z9sj(var10);
                     if (var13 != null
                        && var13.contains(
                           "shaders-rendering"
                        )) {
                        if (!var13.isEmpty()
                           && var13.charAt(0) == 65279) {
                           var13 = var13.substring(1);
                        }

                        YamlConfiguration var14 = new YamlConfiguration();

                        try {
                           var14.loadFromString(var13);
                        } catch (Exception var16) {
                           continue;
                        }

                        if (UiRefreshConfig.read(var14).enabled()) {
                           var1.add(var12);
                        }
                     }
                  }
               }
            }
         }

         return List.copyOf(var1);
      }
   }

   public BossbarAssets Xqedii_va02owx() {
      {
         return this.Xqedii_1thwoqs;
      }
   }

   public boolean Xqedii_7yidd6b() {
      {
         return ((this.Xqedii_271fagi && this.Xqedii_1thwoqs.isReady()
            ? 1
            : 0) != 0);
      }
   }

   public synchronized void Xqedii_3pw62lv(File var1, File var2) {
      {
         this.Xqedii_3eqofrj(var1, var2, false);
      }
   }

   public synchronized void Xqedii_3eqofrj(File var1, File var2, boolean var3) {
      {
         if (var3) {
            this.Xqedii_qu8zghw = this.Xqedii_hirtos6();
         }

         if (!this.Xqedii_271fagi || var3 || !this.Xqedii_39gjfbc()) {
            this.Xqedii_sosos42.clear();
            this.Xqedii_3kzv666.clear();
            this.Xqedii_271fagi = false;
            if (this.Xqedii_39gjfbc()) {
               if (var1 != null && var1.isDirectory()) {
                  if (!this.Xqedii_1thwoqs
                     .build(
                        var1,
                        var2,
                        this.Xqedii_36yolnk(
                           "bossbar/vanilla_font.txt"
                        ),
                        this.Xqedii_m0nzvfj.getLogger()
                     )) {
                     this.Xqedii_m0nzvfj
                        .getLogger()
                        .warning(
                           "[UltimateUI] bossbar-rendering: resource pack assets could not be generated, feature stays off."
                        );
                  } else {
                     this.Xqedii_16gfg65 = this.Xqedii_36yolnk(
                        "bossbar/uui_bossbar_vsh.glsl"
                     );
                     this.Xqedii_2q432gd = this.Xqedii_36yolnk(
                        "bossbar/uui_bossbar_fsh.glsl"
                     );
                     if (this.Xqedii_16gfg65 != null && this.Xqedii_2q432gd != null) {
                        this.Xqedii_3kzv666.putAll(this.Xqedii_1thwoqs.packEntries());
                        int var4 = this.Xqedii_30ia0p7(var1);
                        if (var4 == 0) {
                           this.Xqedii_m0nzvfj
                              .getLogger()
                              .warning(
                                 "[UltimateUI] bossbar-rendering: no text shader could be patched, feature stays off."
                              );
                           this.Xqedii_3kzv666.clear();
                        } else {
                           this.Xqedii_271fagi = true;
                           this.Xqedii_m0nzvfj
                              .getLogger()
                              .info(
                                 Xqedii_114piqaiyjlqw(
                                    this.Xqedii_3kzv666.size(),
                                    var4,
                                    this.Xqedii_1thwoqs.textClassCount(),
                                    this.Xqedii_1thwoqs.detectedMaskCount(),
                                    String.valueOf(this.Xqedii_4sqpfdi()),
                                    this.Xqedii_7bgzqhm()
                                       ? " and the editor."
                                       : "."
                                 )
                              );
                        }
                     } else {
                        this.Xqedii_m0nzvfj
                           .getLogger()
                           .warning(
                              "[UltimateUI] bossbar-rendering: shader templates missing from the plugin jar, feature stays off."
                           );
                     }
                  }
               } else {
                  this.Xqedii_m0nzvfj.getLogger().warning(Xqedii_f8zd65jtw9gg(String.valueOf(var1)));
               }
            }
         }
      }
   }

   public byte[] Xqedii_ruydpmf(String var1, byte[] var2) {
      if (this.Xqedii_271fagi && var1 != null && var2 != null) {
         Xqedii_3juyhjk var3 = this.Xqedii_sosos42
            .get(var1.replace((char)92, (char)47));
         if (var3 == null) {
            return null;
         } else {
            String var4 = new String(var2, StandardCharsets.UTF_8);
            String var5 = var3.vertex() ? this.Xqedii_19vvqh6(var4, var3.spirv(), var3.location()) : this.Xqedii_1hk8o8g(var4, var3.spirv(), var3.location());
            return var5 == null ? null : var5.getBytes(StandardCharsets.UTF_8);
         }
      } else {
         return null;
      }
   }

   public Map<String, byte[]> Xqedii_1umuut0() {
      {
         return this.Xqedii_271fagi ? this.Xqedii_3kzv666 : Map.of();
      }
   }

   public int Xqedii_30ia0p7(File var1) {
      {
         int var2 = 0;
         String[] var3 = Xqedii_1xoisfr;
         int var4 = var3.length;

         for (int var5 = 0; var5 < var4; var5++) {
            String var6 = var3[var5];
            File var7 = new File(var1, Xqedii_6o99cck1bz6n(var6));
            if (var7.isDirectory()) {
               File[] var8 = var7.listFiles((var0, var1x) -> {
                  {
                     return var1x.toLowerCase(Locale.ROOT).endsWith(".vsh");
                  }
               });
               if (var8 != null) {
                  File[] var9 = var8;
                  int var10 = var8.length;

                  for (int var11 = 0; var11 < var10; var11++) {
                     File var12 = var9[var11];
                     String var13 = var12.getName();
                     String var14 = var13.substring(
                        0, var13.length() - 4
                     );
                     if (var14.equals("text")
                        || var14.startsWith(
                           "rendertype_text"
                        )) {
                        File var15 = new File(var7, Xqedii_23mxvha99u0is(var14));
                        if (var15.isFile()) {
                           String var16 = this.Xqedii_2b5z9sj(var12);
                           String var17 = this.Xqedii_2b5z9sj(var15);
                           if (var16 != null
                              && var17 != null
                              && var16.contains("if (make_hud(")
                              && var17.contains(
                                 "if (color.a < 0.1)"
                              )) {
                              if (!var16.contains("uuiBbCorner")
                                 && !var17.contains("uuiBbCorner")
                                 )
                               {
                                 boolean var18 = Xqedii_1wyzus8.Xqedii_1br3yjd(var16);
                                 int var19 = Xqedii_1wyzus8.Xqedii_23ul7l2(var16, var17);
                                 String var20 = this.Xqedii_19vvqh6(var16, var18, var19);
                                 String var21 = this.Xqedii_1hk8o8g(var17, var18, var19);
                                 if (var20 != null && var21 != null) {
                                    String var22 = Xqedii_3k1a84c0azg9p(var6);
                                    this.Xqedii_sosos42
                                       .put(
                                          Xqedii_1nv86mq4z43ul(var22, var14),
                                          new Xqedii_3juyhjk(true, var18, var19)
                                       );
                                    this.Xqedii_sosos42
                                       .put(
                                          Xqedii_3k4exle0bb180(var22, var14),
                                          new Xqedii_3juyhjk(false, var18, var19)
                                       );
                                    var2++;
                                 }
                              } else {
                                 this.Xqedii_m0nzvfj.getLogger().warning(Xqedii_3aydlws6rvgpo(var6, var14));
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         return var2;
      }
   }

   public String Xqedii_19vvqh6(String var1, boolean var2, int var3) {
      {
         return Xqedii_1wyzus8.Xqedii_2e9uhb4(var1, this.Xqedii_16gfg65, var2, var3);
      }
   }

   public String Xqedii_1hk8o8g(String var1, boolean var2, int var3) {
      {
         return Xqedii_1wyzus8.Xqedii_20ik0bw(
            var1, this.Xqedii_2q432gd, this.Xqedii_1thwoqs.maskLeftProfiles(), this.Xqedii_1thwoqs.maskRightProfiles(), var2, var3
         );
      }
   }

   public String Xqedii_2b5z9sj(File var1) {
      {
         try {
            return new String(Files.readAllBytes(var1.toPath()), StandardCharsets.UTF_8);
         } catch (Exception var3) {
            return null;
         }
      }
   }

   public String Xqedii_36yolnk(String var1) {
      {
         try {
            String var3;
            try (InputStream var2 = this.Xqedii_m0nzvfj.getResource(var1)) {
               if (var2 == null) {
                  return null;
               }

               var3 = new String(var2.readAllBytes(), StandardCharsets.UTF_8);
            }

            return var3;
         } catch (Exception var7) {
            return null;
         }
      }
   }

   public List<String> Xqedii_39g88j9() {
      {
         ArrayList var1 = new ArrayList();
         var1.add(Xqedii_2i3miknwpn6ow(String.valueOf(this.Xqedii_4sqpfdi())));
         var1.add(Xqedii_d3d74v4e0ek0(this.Xqedii_7bgzqhm()));
         var1.add(Xqedii_15now4vyxobaa(this.Xqedii_271fagi, this.Xqedii_1thwoqs.isReady()));
         var1.add(Xqedii_d6lf3zaq63wk(this.Xqedii_3kzv666.size()));
         var1.add(Xqedii_21w1fy4s0z2eb(this.Xqedii_sosos42.size() / 2));
         var1.add(Xqedii_3ns5g8i4hwy5c(this.Xqedii_1thwoqs.textClassCount()));
         return var1;
      }
   }

   static {
      {
         String[] var10000 = new String[6];
         var10000[0] = "uui_1_21_4";
         var10000[1] = "uui_1_21_5";
         var10000[2] = "uui_1_21_x";
         var10000[3] = "uui_26_1";
         var10000[4] = "uui_26_2";
         var10000[5] = "uui_26_3";
         Xqedii_1xoisfr = var10000;
         var10000 = new String[2];
         var10000[0] = "contents/pages";
         var10000[1] = "editor-defaults/pages";
         Xqedii_38aphfl = var10000;
         Xqedii_2b9wdyj = Set.of(
            "editor",
            "editor_empty",
            "editor_menu"
         );
      }
   }

   private static /* synthetic */ String Xqedii_f8zd65jtw9gg(String string) {
        return "[UltimateUI] bossbar-rendering: resourcepack/pack folder is missing (" + string + "), feature stays off.";
    }

    private static /* synthetic */ String Xqedii_114piqaiyjlqw(int n, int n2, int n3, int n4, String string, String string2) {
        return "[UltimateUI] bossbar-rendering: prepared " + n + " generated pack files, patched " + n2 + " text shaders, " + n3 + " text size classes, " + n4 + " tintable shapes for shaders-rendering pages " + string + string2;
    }

    private static /* synthetic */ String Xqedii_6o99cck1bz6n(String string) {
        return string + "/assets/minecraft/shaders/core";
    }

    private static /* synthetic */ String Xqedii_23mxvha99u0is(String string) {
        return string + ".fsh";
    }

    private static /* synthetic */ String Xqedii_3aydlws6rvgpo(String string, String string2) {
        return "[UltimateUI] bossbar-rendering: " + string + "/" + string2 + " in resourcepack/pack is already patched, skipping it (restore the original shader there).";
    }

    private static /* synthetic */ String Xqedii_3k1a84c0azg9p(String string) {
        return string + "/assets/minecraft/shaders/core/";
    }

    private static /* synthetic */ String Xqedii_1nv86mq4z43ul(String string, String string2) {
        return string + string2 + ".vsh";
    }

    private static /* synthetic */ String Xqedii_3k4exle0bb180(String string, String string2) {
        return string + string2 + ".fsh";
    }

    private static /* synthetic */ String Xqedii_2i3miknwpn6ow(String string) {
        return "pages with shaders-rendering: true = " + string;
    }

    private static /* synthetic */ String Xqedii_d3d74v4e0ek0(boolean bl) {
        return "experimental.shaders-editor = " + bl;
    }

    private static /* synthetic */ String Xqedii_15now4vyxobaa(boolean bl, boolean bl2) {
        return "pack prepared = " + bl + ", assets ready = " + bl2;
    }

    private static /* synthetic */ String Xqedii_d6lf3zaq63wk(int n) {
        return "generated pack files = " + n;
    }

    private static /* synthetic */ String Xqedii_21w1fy4s0z2eb(int n) {
        return "patched text shaders = " + n;
    }

    private static /* synthetic */ String Xqedii_3ns5g8i4hwy5c(int n) {
        return "text size classes = " + n;
    }
}
