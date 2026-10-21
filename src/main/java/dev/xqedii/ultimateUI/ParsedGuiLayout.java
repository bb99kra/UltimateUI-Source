package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;
import java.util.List;
import java.util.Map;
import org.bukkit.configuration.file.YamlConfiguration;

public record ParsedGuiLayout(List<Map<String, Object>> rawBlocks, Map<String, YamlConfiguration> components, double pageWidth, double pageHeight) {

   @Override

   public Map<String, YamlConfiguration> components() {
      {
         return this.components;
      }
   }

   public double pageWidth() {
      {
         return this.pageWidth;
      }
   }

   public double pageHeight() {
      {
         return this.pageHeight;
      }
   }
}
