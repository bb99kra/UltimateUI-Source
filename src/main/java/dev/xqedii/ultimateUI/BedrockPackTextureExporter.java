package dev.xqedii.ultimateUI;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.imageio.ImageIO;

public final class BedrockPackTextureExporter {
   public static final int Xqedii_2902yfg = 8;
   public final File Xqedii_26uwye0;
   public final File Xqedii_czix6ch;
   public int Xqedii_6ya1vvr;
   public int Xqedii_1veedra;
   public final Set<String> Xqedii_1k02n8a = new HashSet<>();
   public static final int Xqedii_2mjif7l = 512;

   public BedrockPackTextureExporter(File var1, File var2) {
      this.Xqedii_26uwye0 = var1;
      this.Xqedii_czix6ch = var2;
   }

   public int Xqedii_i0mc3tn() {
      {
         return this.Xqedii_6ya1vvr;
      }
   }

   public int Xqedii_3b5wyon() {
      {
         return this.Xqedii_1veedra;
      }
   }

   public int Xqedii_lgiu7qj() {
      {
         return this.Xqedii_1k02n8a.size();
      }
   }

   public String Xqedii_2uhab9t(String var1) throws IOException {
      if (var1 != null && !var1.isBlank()) {
         File var2 = this.Xqedii_37cef9s(var1);
         if (var2 == null) {
            return "";
         } else {
            File var3 = new File(this.Xqedii_czix6ch, "img");
            if (!var3.isDirectory() && !var3.mkdirs()) {
               throw new IOException(Xqedii_3isgtnulz59hi(String.valueOf(var3)));
            } else {
               File var4 = new File(var3, Xqedii_1pwry4lrt5d55(var1));
               this.Xqedii_1k02n8a.add(Xqedii_33nbru9hjhlqk(var1));
               if (var4.isFile() && var4.lastModified() >= var2.lastModified()) {
                  return Xqedii_1yai9u4d2twyj(var1);
               } else {
                  if (var2.getName().toLowerCase(Locale.ROOT).endsWith(".png")
                     )
                   {
                     Path var10000 = var2.toPath();
                     Path var10001 = var4.toPath();
                     CopyOption[] var10002 = new CopyOption[1];
                     var10002[0] = StandardCopyOption.REPLACE_EXISTING;
                     Files.copy(var10000, var10001, var10002);
                  } else {
                     BufferedImage var5 = ImageIO.read(var2);
                     if (var5 == null) {
                        return "";
                     }

                     BufferedImage var6 = new BufferedImage(var5.getWidth(), var5.getHeight(), 2);
                     Graphics2D var7 = var6.createGraphics();

                     try {
                        var7.drawImage(var5, 0, 0, null);
                     } finally {
                        var7.dispose();
                     }

                     ImageIO.write(var6, "png", var4);
                  }

                  this.Xqedii_6ya1vvr = this.Xqedii_6ya1vvr + 1;
                  return Xqedii_3rtou2ccxvept(var1);
               }
            }
         }
      } else {
         return "";
      }
   }

   public File Xqedii_37cef9s(String var1) {
      {
         File[] var2 = this.Xqedii_26uwye0 == null ? null : this.Xqedii_26uwye0.listFiles();
         if (var2 == null) {
            return null;
         } else {
            File[] var3 = var2;
            int var4 = var2.length;

            for (int var5 = 0; var5 < var4; var5++) {
               File var6 = var3[var5];
               if (var6 != null && var6.isFile()) {
                  String var7 = var6.getName();
                  int var8 = var7.lastIndexOf(46);
                  if (var8 > 0 && Xqedii_wq55eud.Xqedii_2xjes65(var7.substring(0, var8)).equals(var1)) {
                     return var6;
                  }
               }
            }

            return null;
         }
      }
   }

   public String Xqedii_12hok2s() throws IOException {
      {
         File var1 = new File(this.Xqedii_czix6ch, "solid.png");
         this.Xqedii_1k02n8a.add("solid.png");
         if (!var1.isFile()) {
            BufferedImage var2 = new BufferedImage(
               8,
               8,
               2
            );
            Graphics2D var3 = var2.createGraphics();

            try {
               var3.setColor(Color.WHITE);
               var3.fillRect(
                  0,
                  0,
                  8,
                  8
               );
            } finally {
               var3.dispose();
            }

            this.Xqedii_2cmz1id(var1);
            ImageIO.write(var2, "png", var1);
            this.Xqedii_1veedra = this.Xqedii_1veedra + 1;
         }

         return "textures/ui/uui/solid";
      }
   }

   public String Xqedii_3hykzkc(double var1, double var3, double var5, boolean var7, boolean var8, boolean var9, boolean var10) throws IOException {
      {
         double var11 = Math.max(Double.longBitsToDouble(4607182418800017408L), var1);
         double var13 = Math.max(Double.longBitsToDouble(4607182418800017408L), var3);
         double var15 = Double.longBitsToDouble(4647714815446351872L) / Math.max(var11, var13);
         int var17 = Math.max(2, (int)Math.round(var11 * var15));
         int var18 = Math.max(2, (int)Math.round(var13 * var15));
         int var19 = (int)Math.round(
            Math.min(var5, Math.min(var11, var13) / Double.longBitsToDouble(4611686018427387904L)) * var15
         );
         var19 = Math.max(
            1, Math.min(var19, Math.min(var17, var18) / 2)
         );
         String var20 = Xqedii_1b3b9rfp0y7k1(
            var7
               ? "1"
               : "0",
            var8
               ? "1"
               : "0",
            var9
               ? "1"
               : "0",
            var10
               ? "1"
               : "0"
         );
         String var21 = Xqedii_32ym35xbh2isj(var17, var18, var19, var20);
         String var22 = Xqedii_2fo7m9ov7asl9(var21);
         File var23 = new File(this.Xqedii_czix6ch, Xqedii_9ijys8dxytwb(var21));
         this.Xqedii_1k02n8a.add(Xqedii_3tjnfvh0h8cty(var21));
         if (var23.isFile()) {
            return var22;
         } else {
            BufferedImage var24 = new BufferedImage(var17, var18, 2);
            Graphics2D var25 = var24.createGraphics();

            try {
               var25.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
               var25.setColor(Color.WHITE);
               Area var26 = new Area(
                  new java.awt.geom.RoundRectangle2D.Double(
                     Double.longBitsToDouble(0L),
                     Double.longBitsToDouble(0L),
                     var17,
                     var18,
                     var19 * Double.longBitsToDouble(4611686018427387904L),
                     var19 * Double.longBitsToDouble(4611686018427387904L)
                  )
               );
               if (!var7) {
                  var26.add(
                     new Area(
                        new java.awt.geom.Rectangle2D.Double(
                           Double.longBitsToDouble(0L),
                           Double.longBitsToDouble(0L),
                           var19,
                           var19
                        )
                     )
                  );
               }

               if (!var8) {
                  var26.add(
                     new Area(
                        new java.awt.geom.Rectangle2D.Double(
                           var17 - var19, Double.longBitsToDouble(0L), var19, var19
                        )
                     )
                  );
               }

               if (!var9) {
                  var26.add(new Area(new java.awt.geom.Rectangle2D.Double(var17 - var19, var18 - var19, var19, var19)));
               }

               if (!var10) {
                  var26.add(
                     new Area(
                        new java.awt.geom.Rectangle2D.Double(
                           Double.longBitsToDouble(0L), var18 - var19, var19, var19
                        )
                     )
                  );
               }

               var25.fill(var26);
            } finally {
               var25.dispose();
            }

            this.Xqedii_2cmz1id(var23);
            ImageIO.write(var24, "png", var23);
            this.Xqedii_1veedra = this.Xqedii_1veedra + 1;
            return var22;
         }
      }
   }

   public int Xqedii_2uqsfny() {
      {
         ArrayList var1 = new ArrayList();
         Xqedii_1g1e2p1(this.Xqedii_czix6ch, var1);
         int var2 = 0;

         for (File var4 : (Iterable<File>) (var1)) {

            String var5 = this.Xqedii_czix6ch
               .toPath()
               .relativize(var4.toPath())
               .toString()
               .replace(File.separatorChar, (char)47);
            if (!this.Xqedii_1k02n8a.contains(var5) && var4.delete()) {
               var2++;
            }
         }

         return var2;
      }
   }

   public static void Xqedii_1g1e2p1(File var0, List<File> var1) {
      {
         File[] var2 = var0 == null ? null : var0.listFiles();
         if (var2 != null) {
            File[] var3 = var2;
            int var4 = var2.length;

            for (int var5 = 0; var5 < var4; var5++) {
               File var6 = var3[var5];
               if (var6.isDirectory()) {
                  Xqedii_1g1e2p1(var6, var1);
               } else if (var6.isFile()) {
                  var1.add(var6);
               }
            }
         }
      }
   }

   public void Xqedii_weheyl6(File var1) throws IOException {
      {
         File var2 = new File(var1, "pack_icon.png");
         if (!var2.isFile()) {
            int var3 = 64;
            BufferedImage var4 = new BufferedImage(var3, var3, 2);
            Graphics2D var5 = var4.createGraphics();

            try {
               var5.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
               var5.setColor(
                  new Color(
                     30,
                     30,
                     34
                  )
               );
               var5.fillRoundRect(
                  0,
                  0,
                  var3,
                  var3,
                  12,
                  12
               );
               var5.setColor(
                  new Color(
                     39,
                     148,
                     255
                  )
               );
               var5.fillRoundRect(
                  12,
                  12,
                  var3 - 24,
                  var3 - 24,
                  8,
                  8
               );
            } finally {
               var5.dispose();
            }

            this.Xqedii_2cmz1id(var2);
            ImageIO.write(var4, "png", var2);
         }
      }
   }

   public void Xqedii_2cmz1id(File var1) throws IOException {
      {
         File var2 = var1.getParentFile();
         if (var2 != null && !var2.isDirectory() && !var2.mkdirs()) {
            throw new IOException(Xqedii_2u7w9ycixey0p(String.valueOf(var2)));
         }
      }
   }

   private static /* synthetic */ String Xqedii_3isgtnulz59hi(String string) {
        return "Could not create " + string;
    }

    private static /* synthetic */ String Xqedii_1pwry4lrt5d55(String string) {
        return string + ".png";
    }

    private static /* synthetic */ String Xqedii_33nbru9hjhlqk(String string) {
        return "img/" + string + ".png";
    }

    private static /* synthetic */ String Xqedii_1yai9u4d2twyj(String string) {
        return "textures/ui/uui/img/" + string;
    }

    private static /* synthetic */ String Xqedii_3rtou2ccxvept(String string) {
        return "textures/ui/uui/img/" + string;
    }

    private static /* synthetic */ String Xqedii_1b3b9rfp0y7k1(String string, String string2, String string3, String string4) {
        return string + string2 + string3 + string4;
    }

    private static /* synthetic */ String Xqedii_32ym35xbh2isj(int n, int n2, int n3, String string) {
        return "shape_" + n + "x" + n2 + "_" + n3 + "_" + string;
    }

    private static /* synthetic */ String Xqedii_2fo7m9ov7asl9(String string) {
        return "textures/ui/uui/" + string;
    }

    private static /* synthetic */ String Xqedii_9ijys8dxytwb(String string) {
        return string + ".png";
    }

    private static /* synthetic */ String Xqedii_3tjnfvh0h8cty(String string) {
        return string + ".png";
    }

    private static /* synthetic */ String Xqedii_2u7w9ycixey0p(String string) {
        return "Could not create " + string;
    }
}
