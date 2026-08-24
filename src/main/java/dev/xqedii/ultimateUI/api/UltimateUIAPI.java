package dev.xqedii.ultimateUI.api;

import dev.xqedii.ultimateUI.UltimateUI;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public final class UltimateUIAPI {
   private static volatile UltimateUIAPI instance;
   private final UltimateUI plugin;

   private UltimateUIAPI(UltimateUI var1) {
      this.plugin = Objects.requireNonNull(var1, "plugin");
   }

   public static void init(UltimateUI var0) {
      instance = new UltimateUIAPI(var0);
   }

   public static void shutdown() {
      instance = null;
   }

   public static UltimateUIAPI get() {
      UltimateUIAPI var0 = instance;
      if (var0 == null) {
         throw new IllegalStateException("UltimateUIAPI is not available — UltimateUI has not enabled yet or has been disabled.");
      } else {
         return var0;
      }
   }

   public static boolean isAvailable() {
      return instance != null;
   }

   public boolean openGui(Player var1, String var2) {
      return this.openGui(var1, var2, false, false);
   }

   public boolean openGui(Player var1, String var2, boolean var3, boolean var4) {
      return this.plugin.openUiForPlayer(var1, var2, var3, var4);
   }

   public boolean openGui(Player var1, String var2, boolean var3) {
      return this.openGui(var1, var2, false, var3);
   }

   public boolean openGuiHud(Player var1, String var2) {
      return this.openGui(var1, var2, true, false);
   }

   public boolean openGuiHud(Player var1, String var2, boolean var3) {
      return this.openGui(var1, var2, true, var3);
   }

   public int openGui(Iterable<? extends Player> var1, String var2) {
      return this.openGui(var1, var2, false, false);
   }

   public int openGui(Iterable<? extends Player> var1, String var2, boolean var3) {
      return this.openGui(var1, var2, false, var3);
   }

   public int openGuiHud(Iterable<? extends Player> var1, String var2) {
      return this.openGui(var1, var2, true, false);
   }

   public int openGuiHud(Iterable<? extends Player> var1, String var2, boolean var3) {
      return this.openGui(var1, var2, true, var3);
   }

   public int openGui(Iterable<? extends Player> var1, String var2, boolean var3, boolean var4) {
      if (var1 == null) {
         return 0;
      } else {
         int var5 = 0;

         for (Player var7 : var1) {
            if (var7 != null && var7.isOnline() && this.openGui(var7, var2, var3, var4)) {
               var5++;
            }
         }

         return var5;
      }
   }

   public void closeGui(Player var1) {
      this.plugin.closeUiForPlayer(var1);
   }

   public boolean closeGui(Player var1, String var2) {
      return this.plugin.closeUiForPlayer(var1, var2);
   }

   public int closeGui(Iterable<? extends Player> var1) {
      if (var1 == null) {
         return 0;
      } else {
         int var2 = 0;

         for (Player var4 : var1) {
            if (var4 != null && var4.isOnline() && this.isGuiOpen(var4)) {
               this.closeGui(var4);
               var2++;
            }
         }

         return var2;
      }
   }

   public int closeGui(Iterable<? extends Player> var1, String var2) {
      if (var1 == null) {
         return 0;
      } else {
         int var3 = 0;

         for (Player var5 : var1) {
            if (var5 != null && var5.isOnline() && this.closeGui(var5, var2)) {
               var3++;
            }
         }

         return var3;
      }
   }

   public boolean setElementValue(Player var1, String var2, String var3, Object var4) {
      return var1 != null && this.plugin.getGuiService() != null ? this.plugin.getGuiService().setOpenUiElementValue(var1, var2, var3, var4) : false;
   }

   public boolean setElementText(Player var1, String var2, String var3) {
      return var1 != null && this.plugin.getGuiService() != null ? this.plugin.getGuiService().setOpenUiElementText(var1, var2, var3) : false;
   }

   public boolean setElementColor(Player var1, String var2, String var3) {
      return var1 != null && this.plugin.getGuiService() != null ? this.plugin.getGuiService().setOpenUiElementColor(var1, var2, var3) : false;
   }

   public boolean setElementPosition(Player var1, String var2, double var3, double var5) {
      return var1 != null && this.plugin.getGuiService() != null ? this.plugin.getGuiService().setOpenUiElementPosition(var1, var2, var3, var5) : false;
   }

   public boolean setElementScale(Player var1, String var2, double var3, double var5) {
      return var1 != null && this.plugin.getGuiService() != null ? this.plugin.getGuiService().setOpenUiElementScale(var1, var2, var3, var5) : false;
   }

   public boolean setElementItem(Player var1, String var2, String var3) {
      return var1 != null && this.plugin.getGuiService() != null ? this.plugin.getGuiService().setOpenUiElementItem(var1, var2, var3) : false;
   }

   public boolean setElementItem(Player var1, String var2, Material var3) {
      return var1 != null && this.plugin.getGuiService() != null ? this.plugin.getGuiService().setOpenUiElementItem(var1, var2, var3) : false;
   }

   public boolean isGuiOpen(Player var1) {
      return var1 == null ? false : this.plugin.getGuiService() != null && this.plugin.getGuiService().hasOpenSession(var1);
   }

   public boolean isGuiOpen(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = this.getOpenGuiName(var1);
         return var3 != null && var3.equalsIgnoreCase(var2.trim());
      } else {
         return false;
      }
   }

   public String getOpenGuiName(Player var1) {
      return var1 != null && this.plugin.getGuiService() != null ? this.plugin.getGuiService().getActiveGuiName(var1) : null;
   }

   public UiSession getSession(Player var1) {
      return var1 != null && this.plugin.getGuiService() != null ? this.plugin.getGuiService().getUiSession(var1) : null;
   }

   public List<String> listGuis() {
      return this.plugin.getGuiService() == null ? Collections.emptyList() : Collections.unmodifiableList(this.plugin.getGuiService().getGuiNames());
   }

   public boolean guiExists(String var1) {
      if (var1 != null && !var1.isBlank() && this.plugin.getGuiService() != null) {
         String var2 = var1.trim().toLowerCase();

         for (String var4 : this.plugin.getGuiService().getGuiNames()) {
            if (var4.equalsIgnoreCase(var2)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }
}
