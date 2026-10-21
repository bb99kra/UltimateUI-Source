package dev.xqedii.ultimateUI;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;

public final class Xqedii_1cydrw2 extends Xqedii_1ks3mne {

   public Xqedii_1cydrw2(File var1) {
      super(var1);
   }

   public Xqedii_1cydrw2 Xqedii_2vnohf3(double var1, double var3) {
      {
         if (var1 > Double.longBitsToDouble(0L)) {
            this.Xqedii_rnovv49 = var1;
         }

         if (var3 > Double.longBitsToDouble(0L)) {
            this.Xqedii_s1m6hrv = var3;
         }

         return this;
      }
   }

   public Xqedii_1cydrw2 Xqedii_1fyw0cu(double var1) {
      {
         if (var1 > Double.longBitsToDouble(0L)) {
            this.Xqedii_3b5g09f = var1;
         }

         return this;
      }
   }

   public Xqedii_1cydrw2 Xqedii_32wrkf8(boolean var1) {
      {
         this.Xqedii_13gj34d = var1;
         return this;
      }
   }

   public Xqedii_1cydrw2 Xqedii_tc5jj8a(boolean var1) {
      {
         this.Xqedii_3ea0t6n = var1;
         return this;
      }
   }

   public Xqedii_1cydrw2 Xqedii_qa67ajl(boolean var1, Consumer<String> var2) {
      {
         this.Xqedii_1j61zx3 = var1;
         if (var2 != null) {
            this.Xqedii_31y3jzw = var2;
         }

         return this;
      }
   }

   public Xqedii_1cydrw2 Xqedii_ek6kcym(String var1) {
      {
         if (var1 != null && !var1.isBlank()) {
            this.Xqedii_291jtfy = var1;
         }

         return this;
      }
   }

   public Xqedii_1cydrw2 Xqedii_2qflhka(int var1, int var2, int var3) {
      {
         int[] var10001 = new int[3];
         var10001[0] = Math.max(1, var1);
         var10001[1] = Math.max(0, var2);
         var10001[2] = Math.max(0, var3);
         this.Xqedii_3tmaf8b = var10001;
         return this;
      }
   }

   public Xqedii_1rr9pk9 Xqedii_29hipmc(List<Xqedii_2kb1ns6> var1, List<String> var2) throws IOException {
      {
         File var3 = new File(
            this.Xqedii_ef13m9a, "bedrock/pack"
         );
         File var4 = new File(var3, "ui");
         File var5 = new File(var4, "uui");
         File var6 = new File(var3, "textures/ui/uui");

         for (File var8 : List.of(var3, var4, var5, var6)) {
            if (!var8.isDirectory() && !var8.mkdirs()) {
               throw new IOException(Xqedii_vflnl51bfyax(String.valueOf(var8)));
            }
         }

         Xqedii_3boar8q var17 = new Xqedii_3boar8q(
            new File(
               this.Xqedii_ef13m9a, "contents/images"
            ),
            var6
         );
         String var18 = var17.Xqedii_12hok2s();
         var17.Xqedii_weheyl6(var3);
         this.Xqedii_3153b6r(
            Xqedii_28rad3ts8dqak(
               String.valueOf(var3), var1.size(), Xqedii_2164tae(this.Xqedii_rnovv49), Xqedii_2164tae(this.Xqedii_s1m6hrv), this.Xqedii_3ea0t6n
            )
         );
         Map var9 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var9.put(
            "namespace",
            "uui"
         );
         ArrayList var10 = new ArrayList();
         ArrayList var11 = new ArrayList();
         int var12 = 0;

         for (Xqedii_2kb1ns6 var14 : var1) {
            String var15 = Xqedii_10r3ywpn999b8(var14.Xqedii_2kz3xjg);
            this.Xqedii_3153b6r(
               Xqedii_1j0ax1day6hfp(
                  var14.Xqedii_3j6s705,
                  var14.Xqedii_2kz3xjg,
                  var14.Xqedii_ggpkjmc.size(),
                  var14.Xqedii_b0fv9fz,
                  var14.Xqedii_1ei4hmh,
                  var14.Xqedii_1jqflox,
                  var14.Xqedii_sytzb79 >= 0
                     ? "+1(state)"
                     : "",
                  var14.Xqedii_1mt8sib(),
                  var14.Xqedii_3omdmsa,
                  var14.Xqedii_3ow3583.size(),
                  Xqedii_28xaqz0(var14.Xqedii_s8xbs95())
               )
            );
            var9.put(var15, this.Xqedii_36hjqhg(var14, var17, var18, var2, var9, false));
            var10.add(Xqedii_2dr0g9h(Xqedii_3imp9514dtcwq(var15, var15), Xqedii_29ge1r6.Xqedii_15tec8i()));
            if (this.Xqedii_3ea0t6n && var14.Xqedii_3w4yc84()) {
               String var16 = Xqedii_1sv7s94na7mey(var14.Xqedii_2kz3xjg);
               var9.put(var16, this.Xqedii_36hjqhg(var14, var17, var18, var2, var9, true));
               var11.add(Xqedii_2dr0g9h(Xqedii_b1s4psiplauj(var16, var16), Xqedii_29ge1r6.Xqedii_15tec8i()));
            }

            var12 += var14.Xqedii_ggpkjmc.size();
         }

         var9.put("long_root", this.Xqedii_1kzuvnb(var10));
         var9.put("input_root", this.Xqedii_1kzuvnb(var11));
         int var19 = var17.Xqedii_2uqsfny();
         if (var19 > 0) {
            this.Xqedii_3153b6r(Xqedii_1ie4bz1reg095(var19));
         }

         this.Xqedii_3297wz8(
            new File(var5, "uui_screens.json"),
            Xqedii_29ge1r6.Xqedii_zaf20q7(var9)
         );
         this.Xqedii_3297wz8(
            new File(var4, "server_form.json"),
            Xqedii_29ge1r6.Xqedii_zaf20q7(this.Xqedii_3o9nf7h())
         );
         this.Xqedii_3297wz8(
            new File(var4, "_ui_defs.json"),
            Xqedii_29ge1r6.Xqedii_zaf20q7(this.Xqedii_3k9g5n8())
         );
         this.Xqedii_3hhz4ql = this.Xqedii_22cnhtn(var3);
         this.Xqedii_3297wz8(
            new File(var3, "manifest.json"),
            Xqedii_29ge1r6.Xqedii_zaf20q7(this.Xqedii_3n33qm0())
         );
         this.Xqedii_1sv0x4x(var1);
         File var20 = this.Xqedii_1sujeju(var3);
         return new Xqedii_1rr9pk9(
            var3,
            var20,
            var1.size(),
            var12,
            var17.Xqedii_lgiu7qj(),
            Xqedii_2142hntwhbxm8(
               this.Xqedii_3hhz4ql[0],
               this.Xqedii_3hhz4ql[1],
               this.Xqedii_3hhz4ql[2]
            ),
            var2
         );
      }
   }

   public void Xqedii_1sv0x4x(List<Xqedii_2kb1ns6> var1) throws IOException {
      {
         File var2 = new File(
            this.Xqedii_ef13m9a, "bedrock/generated"
         );
         if (!var2.isDirectory() && !var2.mkdirs()) {
            throw new IOException(Xqedii_2e9x183nhdx66(String.valueOf(var2)));
         } else {
            Properties var3 = new Properties();

            for (Xqedii_2kb1ns6 var5 : var1) {
               var3.setProperty(
                  var5.Xqedii_3j6s705.toLowerCase(Locale.ROOT),
                  Xqedii_1m597i4nkfkih(
                     var5.Xqedii_2kz3xjg,
                     var5.Xqedii_b0fv9fz,
                     var5.Xqedii_1ei4hmh,
                     var5.Xqedii_1jqflox,
                     var5.Xqedii_12px6oi(),
                     var5.Xqedii_sytzb79,
                     this.Xqedii_3c97ne5(var5) ? 1 : 0
                  )
               );
            }

            try (OutputStream var9 = Files.newOutputStream(
                  new File(var2, "pages.properties")
                     .toPath()
               )) {
               var3.store(
                  var9,
                  "UltimateUI Bedrock page -> index:interactiveButtons:textSlots:imageSlots:slotLayoutHash:stateSlot:inputOverlay"
               );
            }
         }
      }
   }

   public boolean Xqedii_3c97ne5(Xqedii_2kb1ns6 var1) {
      {
         return ((this.Xqedii_3ea0t6n && var1 != null && var1.Xqedii_3w4yc84()
            ? 1
            : 0) != 0);
      }
   }

   public Map<String, int[]> Xqedii_25ymzyo() {
      {
         LinkedHashMap var1 = new LinkedHashMap();
         File var2 = new File(
            new File(
               this.Xqedii_ef13m9a, "bedrock/generated"
            ),
            "pages.properties"
         );
         if (!var2.isFile()) {
            return var1;
         } else {
            Properties var3 = new Properties();

            try (InputStream var4 = Files.newInputStream(var2.toPath())) {
               var3.load(var4);
            } catch (IOException var11) {
               return var1;
            }

            for (String var5 : var3.stringPropertyNames()) {
               String[] var6 = var3.getProperty(var5, "")
                  .split(":");
               if (var6.length >= 4 && var6.length <= 7) {
                  try {
                     String var10001 = var5.toLowerCase(Locale.ROOT);
                     int[] var10002 = new int[7];
                     var10002[0] = Integer.parseInt(
                        var6[0].trim()
                     );
                     var10002[1] = Integer.parseInt(
                        var6[1].trim()
                     );
                     var10002[2] = Integer.parseInt(
                        var6[2].trim()
                     );
                     var10002[3] = Integer.parseInt(
                        var6[3].trim()
                     );
                     var10002[4] = var6.length >= 5
                        ? Integer.parseInt(var6[4].trim())
                        : -2147483648;
                     var10002[5] = var6.length >= 6
                        ? Integer.parseInt(var6[5].trim())
                        : -2147483648;
                     var10002[6] = var6.length >= 7
                        ? Integer.parseInt(var6[6].trim())
                        : -2147483648;
                     var1.put(var10001, var10002);
                  } catch (NumberFormatException var9) {
                  }
               }
            }

            return var1;
         }
      }
   }

   private static /* synthetic */ String Xqedii_vflnl51bfyax(String string) {
        return "Could not create " + string;
    }

    private static /* synthetic */ String Xqedii_28rad3ts8dqak(String string, int n, String string2, String string3, boolean bl) {
        return "compile start: pack=" + string + " pages=" + n + " reference=" + string2 + "x" + string3 + " inlineInputs=" + bl;
    }

    private static /* synthetic */ String Xqedii_10r3ywpn999b8(int n) {
        return "page_" + n;
    }

    private static /* synthetic */ String Xqedii_1j0ax1day6hfp(String string, int n, int n2, int n3, int n4, int n5, String string2, int n6, int n7, int n8, String string3) {
        return "page '" + string + "' index=" + n + " elements=" + n2 + " buttons=" + n3 + "(click)+" + n4 + "(text)+" + n5 + "(image)" + string2 + " = " + n6 + " inputs=" + n7 + " scrollBoxes=" + n8 + " marker=" + string3;
    }

    private static /* synthetic */ String Xqedii_3imp9514dtcwq(String string, String string2) {
        return string + "_inst@uui." + string2;
    }

    private static /* synthetic */ String Xqedii_1sv7s94na7mey(int n) {
        return "input_page_" + n;
    }

    private static /* synthetic */ String Xqedii_b1s4psiplauj(String string, String string2) {
        return string + "_inst@uui." + string2;
    }

    private static /* synthetic */ String Xqedii_1ie4bz1reg095(int n) {
        return "removed " + n + " unused texture(s) from earlier builds";
    }

    private static /* synthetic */ String Xqedii_2142hntwhbxm8(int n, int n2, int n3) {
        return n + "." + n2 + "." + n3;
    }

    private static /* synthetic */ String Xqedii_2e9x183nhdx66(String string) {
        return "Could not create " + string;
    }

    private static /* synthetic */ String Xqedii_1m597i4nkfkih(int n, int n2, int n3, int n4, int n5, int n6, int n7) {
        return n + ":" + n2 + ":" + n3 + ":" + n4 + ":" + n5 + ":" + n6 + ":" + n7;
    }
}
