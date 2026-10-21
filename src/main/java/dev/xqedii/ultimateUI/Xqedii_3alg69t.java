package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.input.AnvilInputService;
import dev.xqedii.ultimateUI.service.input.InputCloseReason;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public final class Xqedii_3alg69t implements Listener {
   public final AnvilInputService Xqedii_597hfl7;

   public Xqedii_3alg69t(AnvilInputService var1) {
      this.Xqedii_597hfl7 = var1;
   }

   @EventHandler(
      priority = EventPriority.MONITOR
   )
   public void onPrepareAnvil(PrepareAnvilEvent var1) {
      {
         for (HumanEntity var3 : var1.getViewers()) {
            if (var3 instanceof Player var4 && this.Xqedii_597hfl7.isActive(var4)) {
               try {
                  this.Xqedii_597hfl7.Xqedii_1bipawd(var4, var1);
               } catch (Throwable var6) {
               }
            }
         }
      }
   }

   @EventHandler(
      priority = EventPriority.LOWEST
   )
   public void onClick(InventoryClickEvent var1) {
      if (var1.getWhoClicked() instanceof Player var2 && this.Xqedii_597hfl7.isActive(var2)) {
         var1.setCancelled(true);

         try {
            this.Xqedii_597hfl7.Xqedii_3ajrsnu(var2);
         } catch (Throwable var4) {
         }
      }
   }

   @EventHandler(
      priority = EventPriority.LOWEST
   )
   public void onDrag(InventoryDragEvent var1) {
      if (var1.getWhoClicked() instanceof Player var2 && this.Xqedii_597hfl7.isActive(var2)) {
         var1.setCancelled(true);

         try {
            this.Xqedii_597hfl7.Xqedii_3ajrsnu(var2);
         } catch (Throwable var4) {
         }
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR
   )
   public void onClose(InventoryCloseEvent var1) {
      if (var1.getPlayer() instanceof Player var2 && this.Xqedii_597hfl7.isActive(var2)) {
         try {
            this.Xqedii_597hfl7.Xqedii_1uiqlej(var2, Xqedii_3prcdf5(var1));
         } catch (Throwable var4) {
         }
      }
   }

   public static InputCloseReason Xqedii_3prcdf5(InventoryCloseEvent var0) {
      {
         try {
            Object r = var0.getClass().getMethod("getReason").invoke(var0);
            return r != null && "DISCONNECT".equals(r.toString()) ? InputCloseReason.DISCONNECT : InputCloseReason.ESCAPE;
         } catch (Throwable var2) {
            return InputCloseReason.ESCAPE;
         }
      }
   }

   @EventHandler(
      priority = EventPriority.MONITOR
   )
   public void onQuit(PlayerQuitEvent var1) {
      {
         try {
            this.Xqedii_597hfl7.Xqedii_3ss3yna(var1.getPlayer());
         } catch (Throwable var3) {
         }
      }
   }
}
