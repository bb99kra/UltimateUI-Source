package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.api.event.UltimateUIOpenEvent;
import dev.xqedii.ultimateUI.bedrock.BedrockUiService;
import dev.xqedii.ultimateUI.service.keybind.KeybindService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class UiOpenClose extends CommandFeedback {

   public boolean handleUuiBedrockCommand(CommandSender var1, String[] var2) {
      if (!var1.hasPermission("ultimateui.admin")
         && !var1.hasPermission(
            "ultimateui.command.reload"
         )) {
         this.sendUuiCommandError(
            var1,
            "You don't have permission to use this command!",
            new Object[0]
         );
         return true;
      } else if (this.Xqedii_1yufpo0 == null) {
         String var10002 = "Bedrock support failed to start! Check the console and use {0}.";
         Object[] var10003 = new Object[1];
         var10003[0] = "/uui reload";
         this.sendUuiCommandError(var1, var10002, var10003);
         return true;
      } else {
         String var3 = var2.length >= 2
            ? var2[1].toLowerCase(Locale.ROOT)
            : "";
         int var5 = -1;
         switch (var3.hashCode()) {
            case -892481550:
               if (var3.equals("status")) {
                  var5 = 2;
               }
               break;
            case 94094958:
               if (var3.equals("build")) {
                  var5 = 0;
               }
               break;
            case 106426308:
               if (var3.equals("pages")) {
                  var5 = 1;
               }
         }

         switch (var5) {
            case 0:
               Xqedii_3ul4hqj.Xqedii_3m0zjou(
                  var1,
                  "Building the Bedrock pack..."
               );
               this.Xqedii_1yufpo0.buildPackAsync(var1x -> {
                  {
                     ArrayList var2x = new ArrayList();

                     for (String var4 : var1x.warnings()) {
                        var2x.add(Xqedii_3ul4hqj.Xqedii_1u4gly2(var4));
                     }

                     String var5x = Xqedii_3ul4hqj.Xqedii_1u4gly2(Xqedii_3ul4hqj.Xqedii_2qnwa4c(var1x.message()));
                     if (var1x.success()) {
                        Xqedii_3ul4hqj.Xqedii_2g7xq9b(var1, true, var5x, var2x);
                     } else {
                        Xqedii_3ul4hqj.Xqedii_wmipi15(var1, var5x, var2x);
                     }
                  }
               });
               break;
            case 1:
               List var10 = this.Xqedii_1yufpo0.pageNames();
               ArrayList var11 = new ArrayList();

               for (String var13 : (Iterable<String>) (var10)) {

                  var11.add(Xqedii_3ul4hqj.Xqedii_2133eaa(Xqedii_3ul4hqj.Xqedii_1u4gly2(var13)));
               }

               Xqedii_3ul4hqj.Xqedii_3crurrx(var1, Xqedii_1hoyqbtsoi0u(var10.size()), var11);
               break;
            case 2:
               ArrayList var6 = new ArrayList();

               for (String var8 : this.Xqedii_1yufpo0.status()) {
                  int var9 = var8.indexOf(": ");
                  var6.add(
                     var9 > 0
                        ? Xqedii_3ul4hqj.Xqedii_1oxsv35(
                           var8.substring(0, var9).trim(),
                           Xqedii_3ul4hqj.Xqedii_1u4gly2(var8.substring(var9 + 2).trim())
                        )
                        : Xqedii_3ul4hqj.Xqedii_2133eaa(Xqedii_3ul4hqj.Xqedii_1u4gly2(var8))
                  );
               }

               Xqedii_3ul4hqj.Xqedii_3crurrx(var1, "Bedrock", var6);
               break;
            default:
               Xqedii_3ul4hqj.Xqedii_3crurrx(
                  var1,
                  "Bedrock",
                  List.of(
                     Xqedii_3ul4hqj.Xqedii_15zzeiq(
                        "/uui bedrock",
                        "build",
                        "Builds the Bedrock resource pack"
                     ),
                     Xqedii_3ul4hqj.Xqedii_15zzeiq(
                        "/uui bedrock",
                        "pages",
                        "Lists the pages available on Bedrock"
                     ),
                     Xqedii_3ul4hqj.Xqedii_15zzeiq(
                        "/uui bedrock",
                        "status",
                        "Shows the state of Bedrock support"
                     )
                  )
               );
         }

         return true;
      }
   }

   public KeybindService getKeybindService() {
      {
         return this.Xqedii_f3ftqgq;
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public boolean openUiForPlayer(Player var1, String var2, boolean var3, boolean var4) {
      if (var1 != null && var2 != null && !var2.isBlank() && this.Xqedii_2k1vujp != null) {
         UltimateUIOpenEvent var5 = new UltimateUIOpenEvent(var1, var2.trim(), var3, var4);
         this.getServer().getPluginManager().callEvent(var5);
         if (var5.isCancelled()) {
            return false;
         } else {
            if (this.Xqedii_1yufpo0 != null) {
               BedrockUiService.OpenResult var6 = this.Xqedii_1yufpo0.tryOpen(var1, var2, var3);
               switch (dev.xqedii.ultimateUI.Xqedii_os41zqy.Xqedii_359kwn6[var6.ordinal()]) {
                  case 1:
                     return true;
                  case 2:
                     return false;
                  case 3:
                     return false;
                  case 4:
               }
            }

            List var10 = var3 ? Collections.emptyList() : this.Xqedii_2k1vujp.getKeepOpenPageKeys(var1);
            List var7 = this.Xqedii_2k1vujp.getReopenPageKeys(var1);
            if (!var7.isEmpty()) {
               this.Xqedii_yy2ps2m.put(var1.getUniqueId(), new ArrayList<>(var7));
            }

            boolean var8 = this.Xqedii_2k1vujp.isEditorOpen(var1);
            Location var9 = var8 && !var3 ? this.resolvePreferredReturnLocation(var1) : null;
            if (var3) {
               if (var8) {
                  this.closeUiForPlayer(var1);
               } else {
                  this.closeUiForPlayer(var1, var2);
               }
            } else if (var8) {
               this.closeUiForPlayer(var1);
            }

            if (var8) {
               Xqedii_38o2zes.Xqedii_2sj63w1(this, var1, () -> {
                  {
                     if (var1.isOnline()) {
                        this.restorePreservedReturnLocation(var1, var9);
                        this.startUiForPlayer(var1, var2, var3, var4, var10, 0, var9);
                     }
                  }
               }, 3L);
               return true;
            } else {
               return this.startUiForPlayer(var1, var2, var3, var4, var10, 0);
            }
         }
      } else {
         return false;
      }
   }

   public boolean startUiForPlayer(Player var1, String var2, boolean var3, boolean var4, List<String> var5, int var6) {
      {
         return this.startUiForPlayer(var1, var2, var3, var4, var5, var6, null);
      }
   }

   public void restorePreservedReturnLocation(Player var1, Location var2) {
      if (var1 != null && var2 != null && var2.getWorld() != null) {
         if (var1.isInsideVehicle()) {
            var1.leaveVehicle();
         }

         Location var3 = var1.getLocation();
         if (!var2.getWorld().equals(var3.getWorld())
            || var3.distanceSquared(var2) > Double.longBitsToDouble(4576918229304087675L)) {
            this.suppressEditorTeleportClose(var1, 6L);
            Xqedii_38o2zes.Xqedii_2pl8m41(var1, var2);
         }
      }
   }

   public boolean startUiForPlayer(Player var1, String var2, boolean var3, boolean var4, List<String> var5, int var6, Location var7) {
      if (this.shouldLevelPitchBeforeOpen(var1, var3, var6)) {
         Location var11 = var1.getLocation();
         var11.setPitch(Float.intBitsToFloat(0));
         Xqedii_38o2zes.Xqedii_fxq2htx(var1, var11);
         int var12 = var6 + 1;
         Xqedii_38o2zes.Xqedii_2sj63w1(this, var1, () -> {
            {
               if (var1.isOnline()) {
                  this.startUiForPlayer(var1, var2, var3, var4, var5, var12, var7);
               }
            }
         }, 1L);
         return true;
      } else {
         Location var8 = var7 != null && !var3 ? var7.clone() : this.resolveReturnLocationBeforeOpen(var1, var3);
         boolean var9 = var3 ? this.Xqedii_2k1vujp.startGuiHud(var1, var2, var4) : this.Xqedii_2k1vujp.startGui(var1, var2, var4);
         this.updateReturnLocationAfterOpen(var1, var9, var3, var8);
         if (!var3 && var9 && !var5.isEmpty()) {
            ArrayList var10 = new ArrayList(var5);
            Xqedii_38o2zes.Xqedii_2sj63w1(this, var1, () -> {
               if (var1.isOnline()) {
                  for (String var4x : (Iterable<String>) (var10)) {

                     this.Xqedii_2k1vujp.startGuiHud(var1, var4x, false);
                  }
               }
            }, 2L);
         }

         return var9;
      }
   }

   public boolean shouldLevelPitchBeforeOpen(Player var1, boolean var2, int var3) {
      if (var2 || this.Xqedii_1fwpabk == null || !this.Xqedii_1fwpabk.isFixShadersEnabled(var1)) {
         return false;
      } else if (this.Xqedii_1tuq2ol != null && this.Xqedii_1tuq2ol.isCameraActive(var1)) {
         return false;
      } else if (var3 >= 3) {
         return false;
      } else {
         return ((Math.abs(var1.getLocation().getPitch()) > Float.intBitsToFloat(1028443341)
            ? 1
            : 0) != 0);
      }
   }

   public void closeUiForPlayer(Player var1) {
      if (var1 != null) {
         if (this.Xqedii_1yufpo0 != null && this.Xqedii_1yufpo0.hasOpenScreen(var1)) {
            this.Xqedii_1yufpo0.closeScreen(var1);
         } else {
            if (this.Xqedii_2k1vujp != null && this.Xqedii_2k1vujp.hasOpenSession(var1)) {
               this.Xqedii_2k1vujp.closeGui(var1);
               if (this.Xqedii_2k1vujp.hasOpenSession(var1)) {
                  return;
               }
            }

            this.closeUiForPlayerImmediate(var1);
         }
      }
   }

   public void closeUiForPlayerImmediate(Player var1) {
      if (var1 != null) {
         List var2 = this.Xqedii_yy2ps2m.remove(var1.getUniqueId());
         Location var3 = this.resolvePreferredReturnLocation(var1);
         this.cleanupPlayerRuntime(var1);
         if (var3 != null) {
            Xqedii_38o2zes.Xqedii_fxq2htx(var1, var3);
         }

         if (var2 != null && !var2.isEmpty() && var1.isOnline()) {
            ArrayList var4 = new ArrayList(var2);
            Xqedii_38o2zes.Xqedii_2sj63w1(
               this,
               var1,
               () -> {
                  if (var1.isOnline()) {
                     for (String var4x : (Iterable<String>) (var4)) {

                        this.openUiForPlayer(
                           var1,
                           var4x,
                           true,
                           false
                        );
                     }
                  }
               },
               2L
            );
         }
      }
   }

   public void closeUiForPlayerAndTeleport(Player var1, Location var2) {
      if (var1 != null) {
         this.Xqedii_yy2ps2m.remove(var1.getUniqueId());
         this.Xqedii_1hpn9ga.remove(var1.getUniqueId());
         this.cleanupPlayerRuntime(var1);
         if (var2 != null) {
            Xqedii_38o2zes.Xqedii_2sj63w1(this, var1, () -> {
               if (var1.isOnline()) {
                  Xqedii_38o2zes.Xqedii_fxq2htx(var1, var2);
               }
            }, 1L);
         }
      }
   }

   public boolean closeUiForPlayer(Player var1, String var2) {
      if (var1 != null && this.Xqedii_2k1vujp != null) {
         String[] var10001 = new String[1];
         var10001[0] = var2;
         String var3 = this.firstNonBlank(var10001).trim();
         if (var3.isBlank()) {
            this.closeUiForPlayer(var1);
            return true;
         } else {
            String var4 = this.normalizeUiNameToken(var3);
            if (var4.isBlank()) {
               return false;
            } else {
               UUID var5 = var1.getUniqueId();
               Location var6 = this.resolvePreferredReturnLocation(var1);
               boolean var7 = this.Xqedii_2k1vujp.closeGui(var1, var3);
               if (!var7) {
                  var10001 = new String[1];
                  var10001[0] = this.Xqedii_2k1vujp.getActiveGuiName(var1);
                  String var8 = this.firstNonBlank(var10001).trim();
                  if (!var8.isBlank()) {
                     String var9 = this.normalizeUiNameToken(var8);
                     if (!var9.isBlank() && var9.equalsIgnoreCase(var4)) {
                        this.closeUiForPlayer(var1);
                        return true;
                     }
                  }

                  return false;
               } else {
                  if (!this.Xqedii_2k1vujp.hasOpenSession(var1)) {
                     this.Xqedii_1hpn9ga.remove(var5);
                     if (var6 != null) {
                        Xqedii_38o2zes.Xqedii_fxq2htx(var1, var6);
                     }
                  }

                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   public String normalizeUiNameToken(String var1) {
      {
         String[] var10001 = new String[1];
         var10001[0] = var1;
         String var2 = this.firstNonBlank(var10001).trim();
         if (var2.toLowerCase(Locale.ROOT).endsWith(".yml")) {
            var2 = var2.substring(0, var2.length() - 4);
         }

         var2 = var2.replace((char)92, (char)95)
            .replace((char)47, (char)95);
         var2 = var2.replaceAll(
            "\\s+",
            "_"
         );
         var2 = var2.replaceAll(
            "[^a-zA-Z0-9_-]",
            ""
         );
         var2 = var2.replaceAll(
            "_+",
            "_"
         );
         var2 = var2.replaceAll(
            "^-+",
            ""
         );
         var2 = var2.replaceAll(
            "-+$",
            ""
         );
         return var2.isBlank() ? "" : var2.toLowerCase(Locale.ROOT);
      }
   }

   private static /* synthetic */ String Xqedii_1hoyqbtsoi0u(int n) {
        return "Bedrock pages (" + n + ")";
    }
}
