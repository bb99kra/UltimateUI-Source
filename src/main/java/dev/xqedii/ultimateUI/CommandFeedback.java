package dev.xqedii.ultimateUI;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CommandFeedback extends UiImageLookup {

   public boolean hasCommandPermission(CommandSender var1, String var2, String var3) {
      if (var1 == null) {
         return false;
      } else if (this.hasCommandPermissionSilent(var1, var2)) {
         return true;
      } else {
         this.sendUuiCommandError(
            var1,
            "You don't have permission to use this command!"
         );
         return false;
      }
   }

   public boolean hasCommandPermissionSilent(CommandSender var1, String var2) {
      if (var1 == null) {
         return false;
      } else {
         String[] var10001 = new String[1];
         var10001[0] = var2;
         String var3 = this.firstNonBlank(var10001);
         if (var3.isBlank()) {
            return true;
         } else if (!(var1 instanceof Player)) {
            return true;
         } else {
            return ((!var1.hasPermission(
                     "ultimateui.admin"
                  )
                  && !var1.hasPermission(var3)
               ? 0
               : 1) != 0);
         }
      }
   }

   public void sendUuiCommandError(CommandSender var1, String var2, Object... var3) {
      if (var1 != null) {
         String var4 = var2 == null ? "" : var2.trim();
         ChatColorUtil.Xqedii_2dtmver(
            var1,
            var4.isEmpty()
               ? "Something went wrong!"
               : var4,
            var3
         );
      }
   }

   public void sendUuiUsage(CommandSender var1, String var2, String var3) {
      {
         ChatColorUtil.Xqedii_2olsc6h(var1, var2, var3);
      }
   }

   public void sendUuiServicesError(CommandSender var1) {
      {
         String var10002 = "UltimateUI failed to start! Use {0} to try again.";
         Object[] var10003 = new Object[1];
         var10003[0] = "/uui reload";
         this.sendUuiCommandError(var1, var10002, var10003);
      }
   }
}
