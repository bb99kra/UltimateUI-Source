package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.bedrock.BedrockUiService;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.GuiService;
import dev.xqedii.ultimateUI.service.input.InputCloseReason;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.IllegalPluginAccessException;

public abstract class PlayerSessions extends PluginBootstrap {

   public void cleanupPlayerRuntime(Player var1) {
      if (var1 != null) {
         if (this.Xqedii_2lp9ah4 != null) {
            try {
               this.Xqedii_2lp9ah4.close(var1, InputCloseReason.PROGRAMMATIC);
            } catch (Throwable var6) {
            }
         }

         if (this.Xqedii_1tuq2ol != null) {
            try {
               this.Xqedii_1tuq2ol.forceStop(var1);
            } catch (IllegalPluginAccessException var11) {
            } catch (Throwable var12) {
               if (this.isEnabled()) {
                  this.getLogger().warning(Xqedii_3gob49j32gn7a(var1.getName(), var12.getMessage()));
               }
            }
         }

         if (this.Xqedii_2k1vujp != null) {
            try {
               this.Xqedii_2k1vujp.closeGui(var1);
            } catch (IllegalPluginAccessException var9) {
            } catch (Throwable var10) {
               if (this.isEnabled()) {
                  this.getLogger().warning(Xqedii_f63wj0qi41dm(var1.getName(), var10.getMessage()));
               }
            }
         }

         if (this.Xqedii_1fwpabk != null) {
            try {
               this.Xqedii_1fwpabk.clearHuds(var1);
            } catch (IllegalPluginAccessException var7) {
            } catch (Throwable var8) {
               if (this.isEnabled()) {
                  this.getLogger().warning(Xqedii_ntr9o0o1jk6(var1.getName(), var8.getMessage()));
               }
            }

            try {
               this.Xqedii_1fwpabk.clearPassengerQueue(var1);
            } catch (Throwable var5) {
            }
         }

         if (this.Xqedii_2k1vujp != null) {
            try {
               this.Xqedii_2k1vujp.forgetPlayerHeadCache(var1);
            } catch (Throwable var4) {
            }
         }

         if (this.Xqedii_f3ftqgq != null) {
            try {
               this.Xqedii_f3ftqgq.forgetPlayer(var1);
            } catch (Throwable var3) {
            }
         }

         this.Xqedii_1hpn9ga.remove(var1.getUniqueId());
         this.Xqedii_yy2ps2m.remove(var1.getUniqueId());
      }
   }

   public void cleanupPlayerRuntimeAndRestoreLocation(Player var1) {
      if (var1 != null) {
         Location var2 = this.resolvePreferredReturnLocation(var1);
         this.cleanupPlayerRuntime(var1);
         this.restoreSessionLocation(var1, var2);
      }
   }

   public void restoreSessionLocation(Player var1, Location var2) {
      if (var1 != null && var2 != null && var2.getWorld() != null) {
         Location var3 = var1.getLocation();
         if (!var2.getWorld().equals(var3.getWorld())
            || !(var3.distanceSquared(var2) <= Double.longBitsToDouble(4576918229304087675L))) {
            Xqedii_38o2zes.Xqedii_2pl8m41(var1, var2);
         }
      }
   }

   public void teleportToReturnLocationAndClear(Player var1) {
      if (var1 != null) {
         Location var2 = this.resolvePreferredReturnLocation(var1);
         this.Xqedii_1hpn9ga.remove(var1.getUniqueId());
         if (var2 != null) {
            Xqedii_38o2zes.Xqedii_fxq2htx(var1, var2);
         }
      }
   }

   public Location resolvePreferredReturnLocation(Player var1) {
      if (var1 == null) {
         return null;
      } else {
         if (this.Xqedii_1tuq2ol != null && this.Xqedii_1tuq2ol.isCameraActive(var1)) {
            Location var2 = this.Xqedii_1tuq2ol.getCameraBaseLocation(var1);
            if (var2 != null && var2.getWorld() != null) {
               return var2.clone();
            }
         }

         Location var3 = this.Xqedii_1hpn9ga.get(var1.getUniqueId());
         return var3 != null && var3.getWorld() != null ? var3.clone() : null;
      }
   }

   public Location resolveReturnLocationBeforeOpen(Player var1, boolean var2) {
      if (var1 != null && !var2) {
         Location var3 = this.resolvePreferredReturnLocation(var1);
         return var3 != null ? var3 : var1.getLocation().clone();
      } else {
         return null;
      }
   }

   public void updateReturnLocationAfterOpen(Player var1, boolean var2, boolean var3, Location var4) {
      if (var1 != null && var2) {
         UUID var5 = var1.getUniqueId();
         if (var3) {
            this.Xqedii_1hpn9ga.remove(var5);
         } else {
            Location var6 = var4;
            if (var4 == null || var4.getWorld() == null) {
               var6 = var1.getLocation().clone();
            }

            this.Xqedii_1hpn9ga.put(var5, var6.clone());
         }
      }
   }

   public GuiService getGuiService() {
      {
         return this.Xqedii_2k1vujp;
      }
   }

   public CameraService getCameraService() {
      {
         return this.Xqedii_1tuq2ol;
      }
   }

   public BedrockUiService getBedrockUiService() {
      {
         return this.Xqedii_1yufpo0;
      }
   }

   private static /* synthetic */ String Xqedii_3gob49j32gn7a(String string, String string2) {
        return "Failed to force-stop camera for " + string + ": " + string2;
    }

    private static /* synthetic */ String Xqedii_f63wj0qi41dm(String string, String string2) {
        return "Failed to close GUI for " + string + ": " + string2;
    }

    private static /* synthetic */ String Xqedii_ntr9o0o1jk6(String string, String string2) {
        return "Failed to clear HUDs for " + string + ": " + string2;
    }
}
