package dev.xqedii.ultimateUI;

import java.util.ArrayList;
import java.util.Collections;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public final class Xqedii_bflxzg9 extends Command implements PluginIdentifiableCommand {
   public final ScriptEventHandlerDef Xqedii_26ue1tp;
   public final CustomScriptEngine Xqedii_2l6uj6z;

   public Xqedii_bflxzg9(CustomScriptEngine var1, String var2, ScriptEventHandlerDef var3) {
      super(var2);
      this.Xqedii_2l6uj6z = var1;
      this.Xqedii_26ue1tp = var3;
      this.setDescription(Xqedii_zq1a907nq5ps(var3.Xqedii_1swqvpn.Xqedii_1gnbwtp));
      this.setUsage(Xqedii_323zq0d3xjzam(var2));
      if (!var3.Xqedii_3al7qe9.isBlank()) {
         this.setPermission(var3.Xqedii_3al7qe9);
      }
   }

   public boolean execute(CommandSender var1, String var2, String[] var3) {
      if (!this.testPermission(var1)) {
         return true;
      } else {
         ArrayList var4 = new ArrayList(var3.length);
         Collections.addAll(var4, var3);
         Player var5 = var1 instanceof Player var6 ? var6 : null;
         ScriptEventContext var7 = new ScriptEventContext("command", null);
         var7.Xqedii_a9in0yy = String.join(" ", var3);
         if (var5 != null && !Xqedii_38o2zes.Xqedii_269v6yv(var5)) {
            Xqedii_38o2zes.Xqedii_1avvt9e(this.Xqedii_2l6uj6z.Xqedii_1bs4d2x, var5, () -> {
               {
                  this.Xqedii_2l6uj6z.run(this.Xqedii_26ue1tp, var5, var1, var7, var4);
               }
            });
         } else {
            this.Xqedii_2l6uj6z.run(this.Xqedii_26ue1tp, var5, var1, var7, var4);
         }

         return true;
      }
   }

   public Plugin getPlugin() {
      {
         return this.Xqedii_2l6uj6z.Xqedii_1bs4d2x;
      }
   }

   private static /* synthetic */ String Xqedii_zq1a907nq5ps(String string) {
        return Xqedii_ouwx8f5.Xqedii_1tl3q7b0fdd4c((String)"XJkj79Fy89jj8vo8OvXTkPqZ2xC8o8D3F1kNfMC5aS0OBzfDqt6tpCEvAxNLcI4zb5xMvaWP7BrdwKaRa11Z2NAdPOQ=") + string;
    }

    private static /* synthetic */ String Xqedii_323zq0d3xjzam(String string) {
        return Xqedii_ouwx8f5.Xqedii_1tl3q7b0fdd4c((String)"fovr1w8EUFQXEWYgXZv/vp04OB6psJroZ62+gsjJ") + string;
    }
}
