package dev.xqedii.ultimateUI.service.gui.editor.shell;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.hud.HudService;

public abstract class EditorShell extends GuiServiceEditorShellInternalsSupport {
   protected EditorShell(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }
}
