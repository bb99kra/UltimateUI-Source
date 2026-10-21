package dev.xqedii.ultimateUI;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class Xqedii_2om8ulg {
   public final Path Xqedii_2hw69rw;
   public final List<Path> Xqedii_23z7ald;
   public final Set<String> Xqedii_3eq8bxc;
   public final Set<String> Xqedii_18wf8zl;
   public final Xqedii_3vpi3ac Xqedii_1qtmyyn;
   public int Xqedii_2j5wggn;
   public int Xqedii_q9b6pe4;

   public Xqedii_2om8ulg(Path var1, List<Path> var2, Set<String> var3, Set<String> var4, Xqedii_3vpi3ac var5) {
      this.Xqedii_2hw69rw = var1;
      this.Xqedii_23z7ald = var2;
      this.Xqedii_3eq8bxc = var3;
      this.Xqedii_18wf8zl = var4;
      this.Xqedii_1qtmyyn = var5;
   }

   public byte[] Xqedii_2r3ltno(String var1, File var2) {
      if (this.Xqedii_1qtmyyn == null) {
         return null;
      } else {
         Matcher var3 = Xqedii_3s68w2g.Xqedii_18mqikn.matcher(var1);
         if (!var3.matches()) {
            return null;
         } else {
            String var4;
            try {
               var4 = Files.readString(var2.toPath(), StandardCharsets.UTF_8);
            } catch (IOException var10) {
               return null;
            }

            int var5 = Xqedii_37mmpgf(
               var4, var4.indexOf("void main()")
            );
            int var6 = Xqedii_37mmpgf(
               var4, var4.indexOf("if (make_hud(")
            );
            if (var5 >= 0 && var6 >= 0 && var6 > var5) {
               String var7 = var3.group(1);
               int var8 = var7 != null && this.Xqedii_18wf8zl.contains(var7)
                  ? 1
                  : 0;
               String var9 = Xqedii_3lbaun6o6q9m5(
                  var4.substring(0, var5),
                  this.Xqedii_1qtmyyn.functions((var8 != 0)),
                  var4.substring(var5, var6),
                  var4.substring(var6)
               );
               this.Xqedii_2j5wggn = this.Xqedii_2j5wggn + 1;
               return var9.getBytes(StandardCharsets.UTF_8);
            } else {
               return null;
            }
         }
      }
   }

   public void Xqedii_1bv0otb(ZipOutputStream var1, Set<String> var2) throws IOException {
      {
         for (Path var4 : this.Xqedii_23z7ald) {
            String var5 = Xqedii_3s68w2g.Xqedii_3c4jmju(this.Xqedii_2hw69rw, var4);
            if (!this.Xqedii_2uhmyhc(var5) && (var2 == null || var2.add(var5.toLowerCase(Locale.ROOT)))) {
               ZipEntry var6 = new ZipEntry(var5);
               var6.setTime(var4.toFile().lastModified());
               var1.putNextEntry(var6);
               var1.write(Files.readAllBytes(var4));
               var1.closeEntry();
               this.Xqedii_q9b6pe4 = this.Xqedii_q9b6pe4 + 1;
            }
         }
      }
   }

   public int Xqedii_255fjeb() {
      {
         return this.Xqedii_2j5wggn;
      }
   }

   public int Xqedii_km8lvcg() {
      {
         return this.Xqedii_q9b6pe4;
      }
   }

   public boolean Xqedii_ivak95i() {
      {
         return ((this.Xqedii_1qtmyyn != null
            ? 1
            : 0) != 0);
      }
   }

   public boolean Xqedii_2uhmyhc(String var1) {
      {
         String var2 = var1.toLowerCase(Locale.ROOT);
         if (!var2.equals("pack.mcmeta")
            && !var2.equals("pack.png")
            && !var2.contains("/shaders/")
            && !var2.startsWith("shaders/")) {
            int var3 = var1.indexOf(47);
            return ((var3 > 0 && this.Xqedii_3eq8bxc.contains(var1.substring(0, var3))
               ? 1
               : 0) != 0);
         } else {
            return true;
         }
      }
   }

   public static int Xqedii_37mmpgf(String var0, int var1) {
      if (var1 < 0) {
         return -1;
      } else {
         int var2 = var0.lastIndexOf(10, var1);
         return var2 < 0 ? 0 : var2 + 1;
      }
   }

   private static /* synthetic */ String Xqedii_3lbaun6o6q9m5(String string, String string2, String string3, String string4) {
        return string + string2 + string3 + Xqedii_9b0c7uf.Xqedii_25ggc5kf08bci((String)"v03v9kB1eIFmKHMriZvb8WYA4oTgMXZ4Ula7BDYz+pssN/fI4OxGk/R9mt76SwCI6X42rUplURnlkDx4hcxn7kfnCZoTgel4CXsgBX/gd0j11ARSRz/xZChbBlblsOVJkopcd8EjfFVgTp6jHcN4VxaCeusxxRuVoFvHXYTw86E=") + string4;
    }
}
