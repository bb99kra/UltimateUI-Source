package dev.xqedii.ultimateUI;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;

public abstract class UiCommandRegistry extends UiOpenClose {
   public static final String Xqedii_iais8rd = "ultimateui";
   public static final long Xqedii_39vn2j5 = 20L;
   public final Map<String, Xqedii_2pfc09n> Xqedii_o381gdw = new LinkedHashMap<>();
   public final AtomicBoolean Xqedii_1qwyzpk = new AtomicBoolean(false);

   public void scheduleUiCommandRefresh() {
      if (this.isEnabled()
         && this.Xqedii_1qwyzpk
            .compareAndSet(false, true)) {
         Xqedii_38o2zes.Xqedii_3npx6fi(this, () -> {
            {
               this.Xqedii_1qwyzpk.set(false);
               this.refreshUiCommands();
            }
         }, 20L);
      }
   }

   public void refreshUiCommands() {
      if (this.Xqedii_2k1vujp != null && this.isEnabled()) {
         CommandMap var1 = this.resolveCommandMap();
         if (var1 != null) {
            Map var2;
            try {
               var2 = this.Xqedii_2k1vujp.collectPageCommands();
            } catch (Throwable var10) {
               this.getLogger().warning(Xqedii_2aonjtgi1lt4v(var10.getMessage()));
               return;
            }

            int var3 = 0;
            Iterator var4 = this.Xqedii_o381gdw.entrySet().iterator();

            while (var4.hasNext()) {
               Entry var5 = (Entry)var4.next();
               String[] var6 = (String[])var2.get(var5.getKey());
               Xqedii_2pfc09n var7 = (Xqedii_2pfc09n)var5.getValue();
               if (var6 != null && var6[0].equals(var7.Xqedii_25yofvo)) {
                  String[] var10001 = new String[1];
                  var10001[0] = var6[1];
                  String var10000 = this.firstNonBlank(var10001);
                  String[] var10002 = new String[1];
                  var10002[0] = var7.getPermission();
                  if (Objects.equals(var10000, this.firstNonBlank(var10002))) {
                     continue;
                  }
               }

               this.unregisterUiCommand(var1, var7);
               var4.remove();
               var3 = 1;
            }

            for (Entry var12 : (Iterable<Entry>) (var2.entrySet())) {

               if (!this.Xqedii_o381gdw.containsKey(var12.getKey())) {
                  Xqedii_2pfc09n var13 = new Xqedii_2pfc09n(
                     this,
                     (String)var12.getKey(),
                     ((String[])var12.getValue())[0],
                     ((String[])var12.getValue())[1]
                  );

                  try {
                     var1.register("ultimateui", var13);
                     this.Xqedii_o381gdw.put((String)var12.getKey(), var13);
                     var3 = 1;
                  } catch (Throwable var9) {
                     this.getLogger().warning(Xqedii_23xvvj98q2yyl((String)var12.getKey(), var9.getMessage()));
                  }
               }
            }

            if (var3 != 0) {
               this.syncServerCommands();
            }
         }
      }
   }

   public void unregisterAllUiCommands() {
      if (!this.Xqedii_o381gdw.isEmpty()) {
         CommandMap var1 = this.resolveCommandMap();
         if (var1 != null) {
            for (Xqedii_2pfc09n var3 : this.Xqedii_o381gdw.values()) {
               this.unregisterUiCommand(var1, var3);
            }
         }

         this.Xqedii_o381gdw.clear();
         this.syncServerCommands();
      }
   }

   public void unregisterUiCommand(CommandMap var1, Xqedii_2pfc09n var2) {
      if (var1 != null && var2 != null) {
         try {
            var2.unregister(var1);
         } catch (Throwable var7) {
         }

         try {
            Method var3 = var1.getClass()
               .getMethod("getKnownCommands");
            if (var3.invoke(var1) instanceof Map var5) {
               var5.values().removeIf(var1x -> {
                  {
                     return ((var1x == var2 ? 1 : 0) != 0);
                  }
               });
            }
         } catch (Throwable var6) {
         }
      }
   }

   public CommandMap resolveCommandMap() {
      {
         try {
            Server var1 = Bukkit.getServer();
            Method var2 = var1.getClass()
               .getMethod("getCommandMap");
            return var2.invoke(var1) instanceof CommandMap var4 ? var4 : null;
         } catch (Throwable var5) {
            return null;
         }
      }
   }

   public void syncServerCommands() {
      {
         try {
            Server var1 = Bukkit.getServer();
            var1.getClass()
               .getMethod("syncCommands")
               .invoke(var1);
         } catch (Throwable var5) {
         }

         for (Player var2 : Bukkit.getOnlinePlayers()) {
            try {
               var2.updateCommands();
            } catch (Throwable var4) {
            }
         }
      }
   }

   private static /* synthetic */ String Xqedii_2aonjtgi1lt4v(String string) {
        return "[UltimateUI] Could not read UI commands for tab completion: " + string;
    }

    private static /* synthetic */ String Xqedii_23xvvj98q2yyl(String string, String string2) {
        return "[UltimateUI] Could not register the UI command /" + string + ": " + string2;
    }
}
