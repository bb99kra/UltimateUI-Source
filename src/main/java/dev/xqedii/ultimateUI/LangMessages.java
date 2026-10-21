package dev.xqedii.ultimateUI;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.YamlConfiguration;

public abstract class LangMessages extends EditorAutosave {

   public void saveDefaultLangFile() {
      {
         File var1 = new File(this.getDataFolder(), "lang.yml");
         if (!var1.exists()) {
            this.saveResource(
               "lang.yml",
               false
            );
         }
      }
   }

   public void reloadLangConfigFile() {
      {
         this.saveDefaultLangFile();
         File var1 = new File(this.getDataFolder(), "lang.yml");
         this.Xqedii_389zivo = YamlConfiguration.loadConfiguration(var1);
      }
   }

   public List<String> getLangStringList(String var1) {
      if (var1 != null && !var1.isBlank() && this.Xqedii_389zivo != null) {
         List var2 = this.Xqedii_389zivo.getStringList(var1);
         if (var2 != null && !var2.isEmpty()) {
            return this.expandLangMessageLines(var2);
         } else {
            String var3 = this.Xqedii_389zivo.getString(var1, "");
            return var3 != null && !var3.isBlank() ? this.expandLangMessageLines(Collections.singletonList(var3)) : Collections.emptyList();
         }
      } else {
         return Collections.emptyList();
      }
   }

   public List<String> expandLangMessageLines(List<String> var1) {
      if (var1 != null && !var1.isEmpty()) {
         ArrayList var2 = new ArrayList();

         for (String var4 : var1) {
            if (var4 != null) {
               String var5 = var4.replace(
                     "\r\n",
                     "\n"
                  )
                  .replace(
                     "\r",
                     "\n"
                  )
                  .replace(
                     "\\n",
                     "\n"
                  );
               String[] var6 = var5.split(
                  "\n", -1
               );
               Collections.addAll(var2, var6);
            }
         }

         return var2;
      } else {
         return Collections.emptyList();
      }
   }

   public Component getLangMessage(String var1, String var2) {
      {
         String var3 = var2;
         if (this.Xqedii_389zivo != null && var1 != null && !var1.isBlank()) {
            var3 = this.Xqedii_389zivo.getString(var1, var2);
         }

         return this.deserializeConfiguredMessage(var3);
      }
   }

   public Component getLangMessageWithPlaceholders(String var1, String var2, Map<String, String> var3) {
      {
         String var4 = var2;
         if (this.Xqedii_389zivo != null && var1 != null && !var1.isBlank()) {
            var4 = this.Xqedii_389zivo.getString(var1, var2);
         }

         if (var4 != null && !var4.isBlank() && var3 != null && !var3.isEmpty()) {
            String var5 = var4;

            for (Entry var7 : var3.entrySet()) {
               if (var7 != null) {
                  String var8 = (String)var7.getKey();
                  if (var8 != null && !var8.isBlank()) {
                     String var9 = var7.getValue() == null
                        ? ""
                        : (String)var7.getValue();
                     var5 = var5.replace(Xqedii_2pmhip7oljcds(var8), var9);
                  }
               }
            }

            var4 = var5;
         }

         return this.deserializeConfiguredMessage(var4);
      }
   }

   public Component deserializeConfiguredMessage(String var1) {
      {
         String var2 = var1 == null ? "" : var1;
         if (var2.contains("<")
            && var2.contains(">")) {
            try {
               return Xqedii_1df0pdx.deserialize(ChatColorUtil.Xqedii_2emodor(var2));
            } catch (Throwable var4) {
            }
         }

         return Xqedii_1gc2hbe.deserialize(var2);
      }
   }

   private static /* synthetic */ String Xqedii_2pmhip7oljcds(String string) {
        return "{" + string + "}";
    }
}
