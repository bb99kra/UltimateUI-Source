package dev.xqedii.ultimateUI.service.gui;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.editor.GuiServiceEditorSupport;
import dev.xqedii.ultimateUI.service.hud.HudService;
import org.bukkit.entity.Player;

public class GuiService extends GuiServiceEditorSupport {
   public GuiService(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   public void reapplyClientHotbarAir(Player var1) {
      this.applyEditorDisplayPreferences(var1);
   }

   public void setPlayerHeldToolAir(Player var1) {
      this.applyEditorHeldToolMask(var1);
   }

   public static enum ClickType {
      LEFT,
      RIGHT;
   }
}
