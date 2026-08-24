package dev.xqedii.ultimateUI.api;

import java.util.Objects;

public final class UiSession {
   private final String guiName;
   private final boolean hudMode;
   private final boolean editMode;
   private final boolean autoClose;

   public UiSession(String var1, boolean var2, boolean var3, boolean var4) {
      this.guiName = Objects.requireNonNull(var1, "guiName");
      this.hudMode = var2;
      this.editMode = var3;
      this.autoClose = var4;
   }

   public String getGuiName() {
      return this.guiName;
   }

   public boolean isHudMode() {
      return this.hudMode;
   }

   public boolean isEditMode() {
      return this.editMode;
   }

   public boolean isAutoClose() {
      return this.autoClose;
   }

   @Override
   public String toString() {
      return "UiSession{guiName='" + this.guiName + "', hudMode=" + this.hudMode + ", editMode=" + this.editMode + ", autoClose=" + this.autoClose + "}";
   }
}
