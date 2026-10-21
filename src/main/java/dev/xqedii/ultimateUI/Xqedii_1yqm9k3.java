package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.api.UiElementCreate;
import dev.xqedii.ultimateUI.api.UiElementWrite;
import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class Xqedii_1yqm9k3 {
   public final Xqedii_2b3rrt2 Xqedii_2fi2lg1;
   public final Xqedii_1kffk3z Xqedii_2q97y6i;
   public final Player Xqedii_1v4ue4a;
   public final CommandSender Xqedii_3cp8md6;
   public final Xqedii_36oytze Xqedii_34yybpr;
   public final List<Object> Xqedii_1v9t8lx;
   public Map<String, Object> Xqedii_107f1nz = new HashMap<>();
   public boolean Xqedii_3dy3w3c;
   public final List<UiElementWrite> Xqedii_3rytfhb = new ArrayList<>();
   public final List<String> Xqedii_i9uy1th = new ArrayList<>();
   public final List<UiElementCreate> Xqedii_2jsr2jw = new ArrayList<>();

   public Xqedii_1yqm9k3(Xqedii_2b3rrt2 var1, Xqedii_1kffk3z var2, Player var3, CommandSender var4, Xqedii_36oytze var5, List<Object> var6) {
      this.Xqedii_2fi2lg1 = var1;
      this.Xqedii_2q97y6i = var2;
      this.Xqedii_1v4ue4a = var3;
      this.Xqedii_3cp8md6 = (CommandSender)(var4 != null ? var4 : var3);
      this.Xqedii_34yybpr = var5;
      this.Xqedii_1v9t8lx = var6;
   }

   public Player Xqedii_25ge4f1(int var1) {
      if (this.Xqedii_1v4ue4a != null && this.Xqedii_1v4ue4a.isOnline()) {
         return this.Xqedii_1v4ue4a;
      } else {
         throw new Xqedii_3vsk7zm(
            "There is no player here (use this inside an event of a player, or a command run by a player)",
            var1
         );
      }
   }

   public void Xqedii_x5301uw(UiElementWrite var1) {
      {
         if (!this.Xqedii_3rytfhb.isEmpty()) {
            UiElementWrite var2 = this.Xqedii_3rytfhb.get(this.Xqedii_3rytfhb.size() - 1);
            if (var2.elementId().equals(var1.elementId()) && var2.property().equals(var1.property())) {
               this.Xqedii_3rytfhb.set(this.Xqedii_3rytfhb.size() - 1, var1);
               return;
            }
         }

         this.Xqedii_3rytfhb.add(var1);
      }
   }

   public void Xqedii_2504ge3(UiElementCreate var1) {
      {
         this.Xqedii_2jsr2jw.add(var1);
      }
   }

   public void Xqedii_o19wdzr(String var1) {
      {
         this.Xqedii_2jsr2jw.removeIf(var1x -> {
            {
               return var1x.elementId().equals(var1);
            }
         });
         this.Xqedii_i9uy1th.add(var1);
      }
   }

   public void Xqedii_3u9m9cl() {
      {
         if (!this.Xqedii_i9uy1th.isEmpty() || !this.Xqedii_2jsr2jw.isEmpty()) {
            ArrayList var1 = new ArrayList<>(this.Xqedii_i9uy1th);
            ArrayList var2 = new ArrayList<>(this.Xqedii_2jsr2jw);
            this.Xqedii_i9uy1th.clear();
            this.Xqedii_2jsr2jw.clear();
            if (this.Xqedii_1v4ue4a != null && this.Xqedii_1v4ue4a.isOnline() && UltimateUIAPI.isAvailable()) {
               UltimateUIAPI.get().applyElementStructure(this.Xqedii_1v4ue4a, var1, var2);
            }
         }

         if (!this.Xqedii_3rytfhb.isEmpty()) {
            ArrayList var3 = new ArrayList<>(this.Xqedii_3rytfhb);
            this.Xqedii_3rytfhb.clear();
            if (this.Xqedii_1v4ue4a != null && this.Xqedii_1v4ue4a.isOnline() && UltimateUIAPI.isAvailable()) {
               UltimateUIAPI.get().applyElementWrites(this.Xqedii_1v4ue4a, var3);
            }
         }
      }
   }

   public String Xqedii_1r9cm9n() {
      if (this.Xqedii_34yybpr != null && !this.Xqedii_34yybpr.Xqedii_3rt5h0f.isEmpty()) {
         return this.Xqedii_34yybpr.Xqedii_3rt5h0f;
      } else if (this.Xqedii_1v4ue4a != null && UltimateUIAPI.isAvailable()) {
         String var1 = UltimateUIAPI.get().getOpenGuiName(this.Xqedii_1v4ue4a);
         return var1 == null ? "" : var1;
      } else {
         return "";
      }
   }
}
