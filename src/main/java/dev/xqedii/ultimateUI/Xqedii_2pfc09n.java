package dev.xqedii.ultimateUI;

import java.util.Collections;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public final class Xqedii_2pfc09n extends Command implements PluginIdentifiableCommand {
   public final String Xqedii_25yofvo;
   public final UiCommandRegistry Xqedii_3qtlnde;

   public Xqedii_2pfc09n(UiCommandRegistry var1, String var2, String var3, String var4) {
      super(var2);
      this.Xqedii_3qtlnde = var1;
      this.Xqedii_25yofvo = var3;
      this.setDescription(Xqedii_1jxsyxw31hgxt(var3));
      this.setUsage(Xqedii_3fu49y7hosdc(var2));
      String[] var10001 = new String[1];
      var10001[0] = var4;
      String var5 = var1.firstNonBlank(var10001).trim();
      if (!var5.isBlank()) {
         this.setPermission(var5);
      }
   }

   public boolean execute(CommandSender var1, String var2, String[] var3) {
      if (var1 instanceof Player var4) {
         if (!this.testPermission(var1)) {
            return true;
         } else {
            this.Xqedii_3qtlnde
               .openUiForPlayer(
                  var4,
                  this.Xqedii_25yofvo,
                  false,
                  false
               );
            return true;
         }
      } else {
         ChatColorUtil.Xqedii_2dtmver(
            var1,
            "This command is for players only!"
         );
         return true;
      }
   }

   public List<String> tabComplete(CommandSender var1, String var2, String[] var3) {
      {
         return Collections.emptyList();
      }
   }

   public Plugin getPlugin() {
      {
         return this.Xqedii_3qtlnde;
      }
   }

   private static /* synthetic */ String Xqedii_1jxsyxw31hgxt(String string) {
        return Xqedii_3e04hhe.Xqedii_2lds6zar20ozv((String)"DRtNJrS5cpg+T0vVJ5a/WX4QaDdFTAEK9NEXCoPQY2UrN7G9kWx14fj/PZGJDMIg") + string + Xqedii_3e04hhe.Xqedii_2lds6zar20ozv((String)"BhFJqCxLhORyWzdt8myLlnckZ5EVytIDv3MQmdJTQjGL6w==");
    }

    private static /* synthetic */ String Xqedii_3fu49y7hosdc(String string) {
        return Xqedii_3e04hhe.Xqedii_2lds6zar20ozv((String)"Hcln6ZnVbqvdaI/daa6bw7yujabtIjPP26wsMb0w") + string;
    }
}
