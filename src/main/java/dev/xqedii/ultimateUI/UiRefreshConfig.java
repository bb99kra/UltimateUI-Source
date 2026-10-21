package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;
import org.bukkit.configuration.ConfigurationSection;

public record UiRefreshConfig(boolean enabled, int updateTicks) {
   public static final UiRefreshConfig DISABLED;
   public static final UiRefreshConfig EDITOR;

   public static UiRefreshConfig editor(boolean var0) {
      {
         return var0 ? EDITOR : DISABLED;
      }
   }

   public static UiRefreshConfig read(ConfigurationSection var0) {
      if (var0 == null) {
         return DISABLED;
      } else {
         ConfigurationSection var1 = var0.getConfigurationSection(
            "shaders-rendering"
         );
         if (var1 != null) {
            int var2 = Xqedii_30g0ulj.Xqedii_2o877gp(
               var1.getInt(
                  "update",
                  0
               )
            );
            return new UiRefreshConfig(
               var1.getBoolean(
                  "enabled",
                  true
               ),
               var2
            );
         } else {
            return var0.getBoolean(
                  "shaders-rendering",
                  false
               )
               ? new UiRefreshConfig(true, 0)
               : DISABLED;
         }
      }
   }

   @Override

   public int updateTicks() {
      {
         return this.updateTicks;
      }
   }

   static {
      {
         DISABLED = new UiRefreshConfig(false, 0);
         EDITOR = new UiRefreshConfig(true, 0);
      }
   }
}
