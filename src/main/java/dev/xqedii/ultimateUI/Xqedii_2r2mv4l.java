package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;
import org.bukkit.configuration.ConfigurationSection;

public record Xqedii_2r2mv4l(boolean enabled, int updateTicks) {
   public static final Xqedii_2r2mv4l DISABLED;
   public static final Xqedii_2r2mv4l EDITOR;

   public static Xqedii_2r2mv4l editor(boolean var0) {
      {
         return var0 ? EDITOR : DISABLED;
      }
   }

   public static Xqedii_2r2mv4l read(ConfigurationSection var0) {
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
            return new Xqedii_2r2mv4l(
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
               ? new Xqedii_2r2mv4l(true, 0)
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
         DISABLED = new Xqedii_2r2mv4l(false, 0);
         EDITOR = new Xqedii_2r2mv4l(true, 0);
      }
   }
}
