package dev.xqedii.ultimateUI;

import java.util.List;
import java.util.Locale;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CommandArguments extends UiCommandRegistry {

   public boolean handleUuiConsoleOpenCommand(CommandSender var1, String[] var2) {
      if (!this.hasCommandPermission(
         var1,
         "ultimateui.command.open",
         "/uui open"
      )) {
         return true;
      } else if (var2.length < 2) {
         this.sendUuiUsage(
            var1,
            "/uui open",
            "[name] [hud] [autoclose] [player]"
         );
         return true;
      } else if (var2.length > 5) {
         this.sendUuiUsage(
            var1,
            "/uui open",
            "[name] [hud] [autoclose] [player]"
         );
         return true;
      } else {
         String[] var10001 = new String[1];
         var10001[0] = var2[1];
         String var3 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
         if (!var3.equals("editor")
            && !var3.equals("editor_menu")) {
            Xqedii_g4i3bi9 var4 = this.parseOpenCommandOptions(var1, var2);
            if (var4 == null) {
               return true;
            } else {
               boolean var5 = this.openUiForPlayer(
                  var4.targetPlayer(), var2[1], var4.hudMode(), var4.autoClose()
               );
               if (!var5) {
                  String var6 = "Could not open {0} for {1}!";
                  Object[] var7 = new Object[2];
                  var7[0] = var2[1];
                  var7[1] = var4.targetPlayer().getName();
                  this.sendUuiCommandError(var1, var6, var7);
               }

               return true;
            }
         } else {
            String var10002 = "UI {0} does not exist!";
            Object[] var10003 = new Object[1];
            var10003[0] = var2[1];
            this.sendUuiCommandError(var1, var10002, var10003);
            return true;
         }
      }
   }

   public boolean handleUuiConsoleCloseCommand(CommandSender var1, String[] var2) {
      if (!this.hasCommandPermission(
         var1,
         "ultimateui.command.close",
         "/uui close"
      )) {
         return true;
      } else if (var2.length != 2) {
         this.sendUuiUsage(
            var1,
            "/uui close",
            "[player]"
         );
         return true;
      } else {
         Player var3 = this.findOnlinePlayerByName(var2[1]);
         if (var3 == null) {
            String var4 = "Player {0} is offline!";
            Object[] var5 = new Object[1];
            var5[0] = var2[1];
            this.sendUuiCommandError(var1, var4, var5);
            return true;
         } else if (this.Xqedii_2k1vujp != null && this.Xqedii_2k1vujp.hasOpenSession(var3)) {
            this.closeUiForPlayer(var3);
            return true;
         } else {
            String var10002 = "Player {0} has no open UI!";
            Object[] var10003 = new Object[1];
            var10003[0] = var3.getName();
            this.sendUuiCommandError(var1, var10002, var10003);
            return true;
         }
      }
   }

   public Xqedii_g4i3bi9 parseOpenCommandOptions(CommandSender var1, String[] var2) {
      if (var1 != null && var2 != null && var2.length >= 2) {
         int var3 = 0;
         int var4 = 0;
         Player var5 = var1 instanceof Player var6 ? var6 : null;
         int var11 = 0;

         for (int var7 = 2; var7 < var2.length; var7++) {
            String[] var10001 = new String[1];
            var10001[0] = var2[var7];
            String var8 = this.firstNonBlank(var10001).trim();
            if (!var8.isBlank()) {
               String var9 = var8.toLowerCase(Locale.ROOT);
               if (var9.equals("hud")) {
                  if (var3 != 0) {
                     String var10002 = "The {0} option can only be used once!";
                     Object[] var10003 = new Object[1];
                     var10003[0] = "hud";
                     this.sendUuiCommandError(var1, var10002, var10003);
                     return null;
                  }

                  var3 = 1;
               } else if (var9.equals("autoclose")) {
                  if (var4 != 0) {
                     String var12 = "The {0} option can only be used once!";
                     Object[] var14 = new Object[1];
                     var14[0] = "autoclose";
                     this.sendUuiCommandError(var1, var12, var14);
                     return null;
                  }

                  var4 = 1;
               } else {
                  Player var10 = this.findOnlinePlayerByName(var8);
                  if (var10 == null) {
                     String var13 = "Unknown option or player {0}!";
                     Object[] var15 = new Object[1];
                     var15[0] = var8;
                     this.sendUuiCommandError(var1, var13, var15);
                     return null;
                  }

                  if (var11 != 0) {
                     this.sendUuiCommandError(
                        var1,
                        "You can only pick one target player!",
                        new Object[0]
                     );
                     return null;
                  }

                  var5 = var10;
                  var11 = 1;
               }
            }
         }

         if (var5 == null) {
            this.sendUuiUsage(
               var1,
               "/uui open",
               "[name] [hud] [autoclose] [player]"
            );
            return null;
         } else {
            return new Xqedii_g4i3bi9(var5, (var3 != 0), (var4 != 0));
         }
      } else {
         return null;
      }
   }

   public boolean handleUuiShadersCommand(CommandSender var1, String[] var2) {
      {
         return this.handleUuiPlayerToggleCommand(var1, var2, Xqedii_16ud823.SHADERS);
      }
   }

   public boolean handleUuiCursorCommand(CommandSender var1, String[] var2) {
      {
         return this.handleUuiPlayerToggleCommand(var1, var2, Xqedii_16ud823.CURSOR);
      }
   }

   public boolean handleUuiPlayerToggleCommand(CommandSender var1, String[] var2, Xqedii_16ud823 var3) {
      if (!this.hasCommandPermission(var1, var3.permission, Xqedii_ipklhefu2b6a(var3.label))) {
         return true;
      } else if (this.Xqedii_2k1vujp != null && this.Xqedii_1fwpabk != null && this.Xqedii_1tuq2ol != null) {
         if (var2.length > 3) {
            this.sendUuiUsage(
               var1,
               Xqedii_mnvbc1kug2v0(var3.label),
               "[on|off|toggle|reset] [player]"
            );
            return true;
         } else {
            String var10000;
            if (var2.length >= 2) {
               String[] var10001 = new String[1];
               var10001[0] = var2[1];
               var10000 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
            } else {
               var10000 = "status";
            }

            String var4 = var10000;
            Player var5 = var1 instanceof Player var6 ? var6 : null;
            if (var2.length == 3) {
               var5 = this.findOnlinePlayerByName(var2[2]);
               if (var5 == null) {
                  String var10002 = "Player {0} is offline!";
                  Object[] var10003 = new Object[1];
                  var10003[0] = var2[2];
                  this.sendUuiCommandError(var1, var10002, var10003);
                  return true;
               }
            } else if (var2.length == 2 && !this.isPlayerToggleCommandAction(var4)) {
               Player var8 = this.findOnlinePlayerByName(var2[1]);
               if (var8 == null) {
                  this.sendUuiUsage(
                     var1,
                     Xqedii_2jignzir33d6k(var3.label),
                     "[on|off|toggle|reset] [player]"
                  );
                  return true;
               }

               var5 = var8;
               var4 = "status";
            }

            if (var5 == null) {
               this.sendUuiUsage(
                  var1,
                  Xqedii_rfaay4m7slu1(var3.label),
                  "[on|off|toggle|reset] [player]"
               );
               return true;
            } else if (!var5.equals(var1) && !this.hasCommandPermissionSilent(var1, var3.othersPermission)) {
               this.sendUuiCommandError(
                  var1,
                  "You can only change this setting for yourself!",
                  new Object[0]
               );
               return true;
            } else {
               if (!var4.equals("status")) {
                  boolean var9 = this.isPlayerToggleEffective(var5, var3);
                  Boolean var7;
                  if (var4.equals("on")
                     || var4.equals("true")
                     || var4.equals("enable")) {
                     var7 = Boolean.TRUE;
                  } else if (var4.equals("off")
                     || var4.equals("false")
                     || var4.equals("disable")) {
                     var7 = Boolean.FALSE;
                  } else if (!var4.equals("toggle")
                     && !var4.equals("switch")) {
                     var7 = null;
                  } else {
                     var7 = Boolean.valueOf(
                        ((!var9 ? 1 : 0) != 0)
                     );
                  }

                  if (var3 == Xqedii_16ud823.CURSOR) {
                     this.Xqedii_2k1vujp.setPlayerInstantCursorOverride(var5, var7);
                  } else {
                     this.Xqedii_2k1vujp.setPlayerFixShadersOverride(var5, var7);
                  }

                  if (this.Xqedii_2k1vujp.hasOpenSession(var5) && this.isPlayerToggleEffective(var5, var3) != var9) {
                     Xqedii_3ul4hqj.Xqedii_1o0kice(
                        var1,
                        "The change applies the next time a UI is opened!"
                     );
                  }

                  if (!var5.equals(var1)) {
                     this.sendPlayerToggleState(var5, var5, true, var3);
                  }
               }

               this.sendPlayerToggleState(var1, var5, var5.equals(var1), var3);
               return true;
            }
         }
      } else {
         this.sendUuiServicesError(var1);
         return true;
      }
   }

   public boolean isPlayerToggleEffective(Player var1, Xqedii_16ud823 var2) {
      {
         return var2 == Xqedii_16ud823.CURSOR ? this.Xqedii_2k1vujp.isInstantCursorEffective(var1) : this.Xqedii_2k1vujp.isFixShadersEffective(var1);
      }
   }

   public boolean isPlayerToggleCommandAction(String var1) {
      {
         return ((!var1.equals("status")
               && !var1.equals("on")
               && !var1.equals("true")
               && !var1.equals("enable")
               && !var1.equals("off")
               && !var1.equals("false")
               && !var1.equals("disable")
               && !var1.equals("toggle")
               && !var1.equals("switch")
               && !var1.equals("reset")
               && !var1.equals("default")
               && !var1.equals("clear")
            ? 0
            : 1) != 0);
      }
   }

   public void sendPlayerToggleState(CommandSender var1, Player var2, boolean var3, Xqedii_16ud823 var4) {
      {
         Boolean var5 = var4 == Xqedii_16ud823.CURSOR
            ? this.Xqedii_2k1vujp.resolvePlayerInstantCursorOverride(var2)
            : this.Xqedii_2k1vujp.resolvePlayerFixShadersOverride(var2);
         boolean var6 = var4 == Xqedii_16ud823.CURSOR
            ? this.Xqedii_2k1vujp.isInstantCursorEnabledGlobally()
            : this.Xqedii_2k1vujp.isFixShadersEnabledGlobally();
         boolean var7 = var5 != null ? var5 : var6;
         String var8 = var7
            ? "enabled"
            : "disabled";
         String var11;
         if (var3) {
            var11 = Xqedii_25x2bu1bkixb3(var4.displayName);
            Object[] var10001 = new Object[1];
            var10001[0] = var8;
            var11 = Xqedii_3ul4hqj.Xqedii_2d5sc5r(var11, var10001);
         } else {
            var11 = Xqedii_3gsqs72mya3v3(var4.displayName);
            Object[] var15 = new Object[2];
            var15[0] = var2.getName();
            var15[1] = var8;
            var11 = Xqedii_3ul4hqj.Xqedii_2d5sc5r(var11, var15);
         }

         String var9 = var11;
         if (var5 == null) {
            var11 = "Using the config default ({0})";
            Object[] var16 = new Object[1];
            var16[0] = var6
               ? "on"
               : "off";
            var11 = Xqedii_3ul4hqj.Xqedii_2d5sc5r(var11, var16);
         } else {
            var11 = Xqedii_3ul4hqj.Xqedii_2d5sc5r(
               var3
                  ? "Set for you only"
                  : "Set for this player only"
            );
         }

         String var10 = var11;
         Xqedii_3ul4hqj.Xqedii_2g7xq9b(var1, var7, var9, List.of(var10));
      }
   }

   public Player findOnlinePlayerByName(String var1) {
      {
         String[] var10001 = new String[1];
         var10001[0] = var1;
         String var2 = this.firstNonBlank(var10001).trim();
         if (var2.isBlank()) {
            return null;
         } else {
            Player var3 = Bukkit.getPlayerExact(var2);
            if (var3 != null) {
               return var3;
            } else {
               for (Player var5 : Bukkit.getOnlinePlayers()) {
                  if (var5 != null && var5.getName().equalsIgnoreCase(var2)) {
                     return var5;
                  }
               }

               return null;
            }
         }
      }
   }

   public String resolveExistingUiName(String var1) {
      if (this.Xqedii_2k1vujp == null) {
         return "";
      } else {
         String[] var10001 = new String[1];
         var10001[0] = var1;
         String var2 = this.firstNonBlank(var10001).trim();
         if (var2.isBlank()) {
            return "";
         } else {
            String var3 = this.normalizeUiNameToken(var2);
            if (var3.isBlank()) {
               return "";
            } else {
               for (String var5 : this.Xqedii_2k1vujp.getGuiNames()) {
                  String var6 = this.normalizeUiNameToken(var5);
                  if (!var6.isBlank() && var6.equalsIgnoreCase(var3)) {
                     return var5;
                  }
               }

               return "";
            }
         }
      }
   }

   public List<String> filterByPrefix(List<String> var1, String var2) {
      {
         String var3 = var2.toLowerCase();
         return var1.stream().filter(var1x -> {
            {
               return var1x.toLowerCase().startsWith(var3);
            }
         }).toList();
      }
   }

   public void sendUuiHelp(Player var1) {
      if (var1 != null) {
         Component[] var10001 = new Component[14];
         var10001[0] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d04\u0280\u1d07\u1d00\u1d1b\u1d07 <#ffbe54>[\u0274\u1d00\u1d0d\u1d07] <#8a989c>\u00bb <gray>Creates a new UI"
         );
         var10001[1] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d05\u1d07\u029f\u1d07\u1d1b\u1d07 <#ffbe54>[\u0274\u1d00\u1d0d\u1d07] <#8a989c>\u00bb <gray>Deletes existing UI"
         );
         var10001[2] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d0f\u1d18\u1d07\u0274 <#ffbe54>[\u0274\u1d00\u1d0d\u1d07] [\u029c\u1d1c\u1d05] [\u1d00\u1d1c\u1d1b\u1d0f\u1d04\u029f\u1d0fs\u1d07] [\u1d18\u029f\u1d00\u028f\u1d07\u0280] <#8a989c>\u00bb <gray>Opens the created UI"
         );
         var10001[3] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a s\u1d07\u0274s\u026a\u1d1b\u026a\u1d20\u026a\u1d1b\u028f <#ffbe54>[\u0274\u1d1c\u1d0d\u0299\u1d07\u0280] <#8a989c>\u00bb <gray>Sets your cursor sensitivity"
         );
         var10001[4] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d04\u029f\u1d0fs\u1d07 <#ffbe54>[\u0274\u1d00\u1d0d\u1d07] <#8a989c>\u00bb <gray>Closes one open UI (or all when omitted)"
         );
         var10001[5] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a s\u029c\u1d00\u1d05\u1d07\u0280s <#ffbe54>[\u1d1b\u1d0f\u0262\u0262\u029f\u1d07] [\u1d18\u029f\u1d00\u028f\u1d07\u0280] <#8a989c>\u00bb <gray>Toggles Shader Fix for player"
         );
         var10001[6] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d04\u1d1c\u0280s\u1d0f\u0280 <#ffbe54>[\u1d1b\u1d0f\u0262\u0262\u029f\u1d07] [\u1d18\u029f\u1d00\u028f\u1d07\u0280] <#8a989c>\u00bb <gray>Toggles the client-sided cursor"
         );
         var10001[7] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d21\u1d0f\u0280\u029f\u1d05 \u029c\u1d07\u029f\u1d18 <#8a989c>\u00bb <gray>Shows how to place UIs in the world"
         );
         var10001[8] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d07\u1d05\u026a\u1d1b <#ffbe54>[\u0274\u1d00\u1d0d\u1d07] <#8a989c>\u00bb <gray>Edits existing UI"
         );
         var10001[9] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d0d\u1d07\u0274\u1d1c <#8a989c>\u00bb <gray>Opens the editor menu"
         );
         var10001[10] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u0280\u1d07\u029f\u1d0f\u1d00\u1d05 <#ffbe54>[\u1d00\u029f\u029f|\u1d04\u1d0f\u0274\ua730\u026a\u0262|\u1d18\u1d00\u1d04\u1d0b|\u1d18\u1d00\u0262\u1d07s] <#8a989c>\u00bb <gray>Reloads the plugin, or one part of it"
         );
         var10001[11] = Component.empty();
         var10001[12] = Xqedii_1df0pdx.deserialize(
            "<white>\u0274\u1d07\u1d07\u1d05 \u029c\u1d07\u029f\u1d18 \u1d21\u026a\u1d1b\u029c \u1d1b\u029c\u1d07 \u1d18\u029f\u1d1c\u0262\u026a\u0274? \u1d04\u029c\u1d07\u1d04\u1d0b \u1d0f\u1d1c\u1d1b \u1d0f\u1d1c\u0280"
         );
         var10001[13] = Xqedii_1df0pdx.deserialize(
            "<white>\u1d05\u1d0f\u1d04\u1d1c\u1d0d\u1d07\u0274\u1d1b\u1d00\u1d1b\u026a\u1d0f\u0274 \u1d00\u1d1b <yellow><hover:show_text:'<green>Click here to open!'><click:open_url:'https://docs.xqedii.dev/'>https://docs.xqedii.dev/</click></hover><reset>"
         );
         Xqedii_3ul4hqj.send(var1, List.of(var10001));

         try {
            var1.playSound(
               var1.getLocation(),
               "ultimateui.extra7",
               Float.intBitsToFloat(1065353216),
               Float.intBitsToFloat(1065353216)
            );
         } catch (Throwable var3) {
         }
      }
   }

   private static /* synthetic */ String Xqedii_ipklhefu2b6a(String string) {
        return "/uui " + string;
    }

    private static /* synthetic */ String Xqedii_mnvbc1kug2v0(String string) {
        return "/uui " + string;
    }

    private static /* synthetic */ String Xqedii_2jignzir33d6k(String string) {
        return "/uui " + string;
    }

    private static /* synthetic */ String Xqedii_rfaay4m7slu1(String string) {
        return "/uui " + string;
    }

    private static /* synthetic */ String Xqedii_25x2bu1bkixb3(String string) {
        return string + " is {0}!";
    }

    private static /* synthetic */ String Xqedii_3gsqs72mya3v3(String string) {
        return string + " for {0} is {1}!";
    }
}
