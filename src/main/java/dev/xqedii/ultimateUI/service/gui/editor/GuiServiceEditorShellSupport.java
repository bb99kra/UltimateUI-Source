package dev.xqedii.ultimateUI.service.gui.editor;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.editor.shell.EditorShell;
import dev.xqedii.ultimateUI.service.hud.HudService;

public abstract class GuiServiceEditorShellSupport extends EditorShell {
   protected GuiServiceEditorShellSupport(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }
}
