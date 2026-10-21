package dev.xqedii.ultimateUI;

import java.time.Duration;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.json.JSONOptions;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.inventory.meta.ItemMeta;

public final class Xqedii_nd6rkr3 {
   public static final int Xqedii_2ut8cpr = 2724;
   public static final int Xqedii_lkyfrtp = 10;
   public static final int Xqedii_22ip76f = 70;
   public static final int Xqedii_hzawg43 = 20;
   public static final boolean Xqedii_3d42n76;
   public static final LegacyComponentSerializer Xqedii_3rwv2q6;
   public static final LegacyComponentSerializer Xqedii_3eyrbfl;
   public static volatile GsonComponentSerializer Xqedii_2qa1uqz;

   public static boolean Xqedii_37uoku5() {
      {
         return Xqedii_3d42n76;
      }
   }

   public static String Xqedii_sk6nte8() {
      {
         return Xqedii_3d42n76
            ? "Paper"
            : "Spigot";
      }
   }

   public static String Xqedii_3q6gk8o(Component var0) {
      {
         return var0 == null ? "" : Xqedii_3rwv2q6.serialize(var0);
      }
   }

   public static void Xqedii_3s7zz34(CommandSender var0, Component var1) {
      if (var0 != null && var1 != null) {
         if (Xqedii_3d42n76) {
            ((Audience) var0).sendMessage(var1);
         } else if (!(var0 instanceof Player var2)) {
            var0.sendMessage(Xqedii_3eyrbfl.serialize(var1));
         } else {
            BaseComponent[] var3 = Xqedii_3978u2u(var1);
            if (var3 != null) {
               try {
                  var2.spigot().sendMessage(var3);
                  return;
               } catch (Throwable var5) {
               }
            }

            var2.sendMessage(Xqedii_3q6gk8o(var1));
         }
      }
   }

   public static void Xqedii_2n2p2n9(Player var0, Component var1) {
      if (var0 != null && var1 != null) {
         if (Xqedii_3d42n76) {
            ((Audience) var0).sendActionBar(var1);
         } else {
            BaseComponent[] var2 = Xqedii_3978u2u(var1);
            if (var2 != null) {
               try {
                  var0.spigot().sendMessage(ChatMessageType.ACTION_BAR, var2);
               } catch (Throwable var4) {
               }
            }
         }
      }
   }

   public static void Xqedii_303uu75(Player var0, Title var1) {
      if (var0 != null && var1 != null) {
         if (Xqedii_3d42n76) {
            ((Audience) var0).showTitle(var1);
         } else {
            Times var2 = var1.times();
            int var3 = var2 == null ? 10 : Xqedii_15e5mjr(var2.fadeIn());
            int var4 = var2 == null ? 70 : Xqedii_15e5mjr(var2.stay());
            int var5 = var2 == null ? 20 : Xqedii_15e5mjr(var2.fadeOut());
            var0.sendTitle(Xqedii_3q6gk8o(var1.title()), Xqedii_3q6gk8o(var1.subtitle()), var3, var4, var5);
         }
      }
   }

   public static void Xqedii_1blyyfe(Player var0) {
      if (var0 != null) {
         if (Xqedii_3d42n76) {
            ((Audience) var0).clearTitle();
         } else {
            var0.resetTitle();
         }
      }
   }

   public static void Xqedii_37yrqr8(Player var0, Component var1) {
      if (var0 != null) {
         if (Xqedii_3d42n76) {
            try {
               var0.getClass().getMethod("kick", Component.class).invoke(var0, var1);
            } catch (Throwable t) {
               var0.kickPlayer(Xqedii_3q6gk8o(var1));
            }
         } else {
            var0.kickPlayer(Xqedii_3q6gk8o(var1));
         }
      }
   }

   public static void Xqedii_51phqp2(TextDisplay var0, Component var1) {
      if (var0 != null) {
         if (Xqedii_3d42n76) {
            try {
               var0.getClass().getMethod("text", Component.class).invoke(var0, var1);
            } catch (Throwable t) {
               var0.setText(Xqedii_3q6gk8o(var1));
            }
         } else {
            var0.setText(Xqedii_3q6gk8o(var1));
         }
      }
   }

   public static void Xqedii_2mrz7tr(ItemMeta var0, Component var1) {
      if (var0 != null) {
         if (Xqedii_3d42n76) {
            try {
               var0.getClass().getMethod("displayName", Component.class).invoke(var0, var1);
            } catch (Throwable t) {
               var0.setDisplayName(var1 == null ? null : Xqedii_39isgei1nwnbm(String.valueOf(ChatColor.RESET), Xqedii_3q6gk8o(var1)));
            }
         } else {
            var0.setDisplayName(var1 == null ? null : Xqedii_39isgei1nwnbm(String.valueOf(ChatColor.RESET), Xqedii_3q6gk8o(var1)));
         }
      }
   }

   public static void Xqedii_29hmlg6(Player var0, String var1, byte[] var2, Component var3, boolean var4) {
      if (var0 != null && var1 != null) {
         if (Xqedii_3d42n76) {
            var0.setResourcePack(var1, var2, var3 == null ? null : Xqedii_3q6gk8o(var3), var4);
         } else {
            var0.setResourcePack(var1, var2, var3 == null ? null : Xqedii_3q6gk8o(var3), var4);
         }
      }
   }

   public static BaseComponent[] Xqedii_3978u2u(Component var0) {
      {
         try {
            return ComponentSerializer.parse((String)Xqedii_k8orrlz().serialize(var0));
         } catch (Throwable var2) {
            return null;
         }
      }
   }

   public static GsonComponentSerializer Xqedii_k8orrlz() {
      {
         GsonComponentSerializer var0 = Xqedii_2qa1uqz;
         if (var0 == null) {
            var0 = GsonComponentSerializer.builder().options(JSONOptions.byDataVersion().at(2724)).build();
            Xqedii_2qa1uqz = var0;
         }

         return var0;
      }
   }

   public static int Xqedii_15e5mjr(Duration var0) {
      {
         return var0 == null
            ? 0
            : (int)Math.max(
               0L,
               Math.min(
                  2147483647L,
                  var0.toMillis() / 50L
               )
            );
      }
   }

   public static boolean Xqedii_vfky06t() {
      {
         try {
            return ((Audience.class.isAssignableFrom(CommandSender.class) && Audience.class.isAssignableFrom(Player.class)
               ? 1
               : 0) != 0);
         } catch (Throwable var1) {
            return false;
         }
      }
   }

   static {
      {
         Xqedii_3d42n76 = Xqedii_vfky06t();
         Xqedii_3rwv2q6 = LegacyComponentSerializer.builder()
            .character((char)167)
            .hexColors()
            .useUnusualXRepeatedCharacterHexFormat()
            .build();
         Xqedii_3eyrbfl = LegacyComponentSerializer.builder().character((char)167).build();
      }
   }

   private static /* synthetic */ String Xqedii_39isgei1nwnbm(String string, String string2) {
        return string + string2;
    }
}
