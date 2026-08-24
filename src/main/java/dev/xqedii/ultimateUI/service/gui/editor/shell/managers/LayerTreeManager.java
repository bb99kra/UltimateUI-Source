package dev.xqedii.ultimateUI.service.gui.editor.shell.managers;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.editor.GuiServiceEditorSidebarSupport;
import dev.xqedii.ultimateUI.service.hud.HudService;

public abstract class LayerTreeManager extends GuiServiceEditorSidebarSupport {
   protected LayerTreeManager(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }
}
