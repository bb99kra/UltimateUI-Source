package dev.xqedii.ultimateUI.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class UltimateUIBlockClickEvent extends Event implements Cancellable {
   private static final HandlerList HANDLERS = new HandlerList();
   private final Player player;
   private final String pageName;
   private final String blockId;
   private final double blockLayer;
   private final double cursorX;
   private final double cursorY;
   private final UltimateUIBlockClickEvent.ClickType clickType;
   private boolean cancelled;

   public UltimateUIBlockClickEvent(Player var1, String var2, String var3, double var4, double var6, double var8, UltimateUIBlockClickEvent.ClickType var10) {
      this.player = var1;
      this.pageName = var2 == null ? "" : var2;
      this.blockId = var3 == null ? "" : var3;
      this.blockLayer = Double.isFinite(var4) ? var4 : 0.0;
      this.cursorX = Double.isFinite(var6) ? var6 : 0.0;
      this.cursorY = Double.isFinite(var8) ? var8 : 0.0;
      this.clickType = var10 == null ? UltimateUIBlockClickEvent.ClickType.LEFT : var10;
   }

   public Player getPlayer() {
      return this.player;
   }

   public String getPageName() {
      return this.pageName;
   }

   public String getBlockId() {
      return this.blockId;
   }

   public double getBlockLayer() {
      return this.blockLayer;
   }

   public double getCursorX() {
      return this.cursorX;
   }

   public double getCursorY() {
      return this.cursorY;
   }

   public UltimateUIBlockClickEvent.ClickType getClickType() {
      return this.clickType;
   }

   public boolean isCancelled() {
      return this.cancelled;
   }

   public void setCancelled(boolean var1) {
      this.cancelled = var1;
   }

   public HandlerList getHandlers() {
      return HANDLERS;
   }

   public static HandlerList getHandlerList() {
      return HANDLERS;
   }

   public static enum ClickType {
      LEFT,
      RIGHT,
      SCROLL_UP,
      SCROLL_DOWN;
   }
}
