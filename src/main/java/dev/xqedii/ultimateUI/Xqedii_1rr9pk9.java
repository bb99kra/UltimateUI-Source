package dev.xqedii.ultimateUI;

import java.io.File;
import java.lang.runtime.ObjectMethods;
import java.util.List;

public record Xqedii_1rr9pk9(File packFolder, File mcpack, int pages, int elements, int textures, String version, List<String> warnings) {

   @Override

   public File mcpack() {
      {
         return this.mcpack;
      }
   }

   public int pages() {
      {
         return this.pages;
      }
   }

   public int elements() {
      {
         return this.elements;
      }
   }

   public int textures() {
      {
         return this.textures;
      }
   }

   public String version() {
      {
         return this.version;
      }
   }

   public List<String> warnings() {
      {
         return this.warnings;
      }
   }
}
