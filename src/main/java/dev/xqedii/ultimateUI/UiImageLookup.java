package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.resourcepack.UiImageAtlasService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public abstract class UiImageLookup extends ResourcePackDelivery {

   public List<UiImageAtlasService.GeneratedImage> getGeneratedUiImagesForEditor() {
      {
         this.rebuildUiImagesIntoResourcePack();
         if (this.Xqedii_11hx7yt != null && !this.Xqedii_11hx7yt.isEmpty()) {
            ArrayList<UiImageAtlasService.GeneratedImage> var1 = new ArrayList<>();

            for (UiImageAtlasService.GeneratedImage var3 : this.Xqedii_11hx7yt.values()) {
               if (var3 != null && var3.name() != null && !var3.name().isBlank()) {
                  var1.add(var3);
               }
            }

            var1.sort((UiImageAtlasService.GeneratedImage var1x, UiImageAtlasService.GeneratedImage var2) -> {
               {
                  Comparator var10000 = String.CASE_INSENSITIVE_ORDER;
                  String[] var10002 = new String[1];
                  var10002[0] = var1x == null ? null : var1x.name();
                  String var10001 = this.firstNonBlank(var10002);
                  String[] var10003 = new String[1];
                  var10003[0] = var2 == null ? null : var2.name();
                  return var10000.compare(var10001, this.firstNonBlank(var10003));
               }
            });
            return var1;
         } else {
            return Collections.emptyList();
         }
      }
   }

   public UiImageAtlasService.GeneratedImage resolveGeneratedUiImageForEditor(String var1) {
      {
         UiImageAtlasService.GeneratedImage var2 = this.resolveGeneratedUiImage(var1);
         if (var2 != null) {
            return var2;
         } else {
            String var3 = this.alternateImageLookupName(var1);
            UiImageAtlasService.GeneratedImage var4 = this.Xqedii_1e7k2q0.Xqedii_1jkbht0(this.getDataFolder(), var1, this.getLogger());
            if (var4 == null && var3 != null) {
               var4 = this.Xqedii_1e7k2q0.Xqedii_1jkbht0(this.getDataFolder(), var3, this.getLogger());
            }

            if (var4 != null) {
               if (this.Xqedii_11hx7yt == null) {
                  this.Xqedii_11hx7yt = new HashMap<>();
               }

               this.Xqedii_11hx7yt.put(this.normalizeImageLookupKey(var1), var4);
               return var4;
            } else {
               return null;
            }
         }
      }
   }

   public UiImageAtlasService.GeneratedImage resolveGeneratedUiImage(String var1) {
      if (var1 != null && !var1.isBlank() && this.Xqedii_11hx7yt != null && !this.Xqedii_11hx7yt.isEmpty()) {
         String var2 = this.normalizeImageLookupKey(var1);
         UiImageAtlasService.GeneratedImage var3 = this.Xqedii_11hx7yt.get(var2);
         if (var3 != null) {
            return var3;
         } else {
            String var4 = this.alternateImageLookupKey(var2);
            return var4 != null ? this.Xqedii_11hx7yt.get(var4) : null;
         }
      } else {
         return null;
      }
   }

   public String alternateImageLookupKey(String var1) {
      if (var1 != null && !var1.isBlank()) {
         return var1.startsWith("img_")
            ? var1.substring(4)
            : Xqedii_mqq0rttm3mpy(var1);
      } else {
         return null;
      }
   }

   public String alternateImageLookupName(String var1) {
      if (var1 != null && !var1.isBlank()) {
         String var2 = this.normalizeImageLookupKey(var1);
         return var2.startsWith("img_")
            ? var2.substring(4)
            : Xqedii_li7vzxmw5n5d(var2);
      } else {
         return null;
      }
   }

   public String normalizeImageLookupKey(String var1) {
      {
         String[] var10001 = new String[1];
         var10001[0] = var1;
         String var2 = this.firstNonBlank(var10001).trim();
         int var3 = var2.lastIndexOf(46);
         if (var3 > 0) {
            var2 = var2.substring(0, var3);
         }

         return Xqedii_2t253qr.Xqedii_376339b(var2);
      }
   }

   private static /* synthetic */ String Xqedii_mqq0rttm3mpy(String string) {
        return "img_" + string;
    }

    private static /* synthetic */ String Xqedii_li7vzxmw5n5d(String string) {
        return "img_" + string;
    }
}
