package dev.xqedii.ultimateUI.service.gui.editor.shell.managers;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.model.EditorSavePopupField;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.hud.HudService;
import net.kyori.adventure.text.Component;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

public abstract class PopupManager extends NavbarMenuManager {
   protected static final String SAVE_POPUP_ROOT_ID = "popup_save_root";
   protected static final String SAVE_POPUP_NAME_TEXTBOX_ID = "popup_save_textbox_name";
   protected static final String SAVE_POPUP_DESC_TEXTBOX_ID = "popup_save_textbox_desc";
   protected static final String SAVE_POPUP_COMMAND_TEXTBOX_ID = "popup_save_textbox_command";
   protected static final String SAVE_POPUP_CONFIRM_ID = "popup_save_confirm";
   protected static final String SAVE_POPUP_CONFIRM_HITBOX_ID = "popup_save_confirm_hitbox";
   protected static final String SAVE_POPUP_CANCEL_ID = "popup_save_cancel";
   protected static final String SAVE_POPUP_CANCEL_HITBOX_ID = "popup_save_cancel_hitbox";
   protected static final String SAVE_POPUP_CONFIRM_ICON_DEFAULT = "\ueea5";
   protected static final String SAVE_POPUP_CONFIRM_ICON_BLOCKED = "\ueeaa";
   protected static final String SAVE_POPUP_CANCEL_ICON_DEFAULT = "\ueea6";
   protected static final String SAVE_POPUP_CONFIRM_ICON_HOVER = "\ueea8";
   protected static final String SAVE_POPUP_CANCEL_ICON_HOVER = "\ueea9";
   protected static final String PREFERENCES_POPUP_ROOT_ID = "popup_preferences_root";
   protected static final String PREFERENCES_POPUP_CONFIRM_ID = "popup_preferences_confirm";
   protected static final String PREFERENCES_POPUP_CONFIRM_HITBOX_ID = "popup_preferences_confirm_hitbox";
   protected static final String PREFERENCES_POPUP_CANCEL_ID = "popup_preferences_cancel";
   protected static final String PREFERENCES_POPUP_CANCEL_HITBOX_ID = "popup_preferences_cancel_hitbox";
   protected static final String PREFERENCES_POPUP_HOTBAR_HITBOX_ID = "popup_preferences_hotbar_name";
   protected static final String PREFERENCES_POPUP_HAND_HITBOX_ID = "popup_preferences_hand_name";
   protected static final String PREFERENCES_POPUP_OPTIMIZED_HITBOX_ID = "popup_preferences_optimized_name";
   protected static final String PREFERENCES_POPUP_SOUND_HITBOX_ID = "popup_preferences_sound_name";
   protected static final String PREFERENCES_POPUP_CONFIRM_ICON_DEFAULT = "\ueea5";
   protected static final String PREFERENCES_POPUP_CANCEL_ICON_DEFAULT = "\ueea6";
   protected static final String PREFERENCES_POPUP_CONFIRM_ICON_HOVER = "\ueea8";
   protected static final String PREFERENCES_POPUP_CANCEL_ICON_HOVER = "\ueea9";
   protected static final String WELCOME_POPUP_ROOT_ID = "popup_welcome_root";
   protected static final String WELCOME_POPUP_BACKDROP_ID = "popup_welcome_backdrop";
   protected static final String WELCOME_POPUP_CONFIRM_ID = "popup_welcome_confirm";
   protected static final String WELCOME_POPUP_CONFIRM_HITBOX_ID = "popup_welcome_confirm_hitbox";
   protected static final String WELCOME_POPUP_LINK_HITBOX_ID = "popup_welcome_link_hitbox";
   protected static final String WELCOME_POPUP_CONFIRM_ICON_DEFAULT = "\ueea5";
   protected static final String WELCOME_POPUP_CONFIRM_ICON_HOVER = "\ueea8";
   protected static final String WELCOME_POPUP_CONFIRM_SOUND = "ultimateui.extra3";
   protected static final String WELCOME_POPUP_LINK_SOUND = "ultimateui.extra7";

   protected PopupManager(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   protected abstract void startSavePopupInputPrompt(Player var1, EditorSession var2, EditorSavePopupField var3);

   protected abstract boolean handleSavePopupConfirm(Player var1, EditorSession var2);

   protected abstract void refreshSavePopupFields(Player var1, EditorSession var2);

   protected boolean handlePreferencesPopupConfirm(Player var1, EditorSession var2) {
      return true;
   }

   protected boolean handlePreferencesPopupToggle(Player var1, EditorSession var2, String var3) {
      return false;
   }

   protected boolean handleWelcomePopupConfirm(Player var1, EditorSession var2) {
      return true;
   }

   protected void refreshPreferencesPopupFields(Player var1, EditorSession var2) {
   }

   protected void onPreferencesPopupVisibilityChanged(Player var1, EditorSession var2, boolean var3) {
   }

   protected abstract void sendEditorPlayerMessage(Player var1, Component var2);

   @Override
   protected abstract boolean isInsideShellBlock(EditorSession var1, String var2, double var3, double var5);

   @Override
   protected abstract void setShellText(Player var1, String var2, String var3);

   @Override
   protected abstract void beginShellOpacityBatch(EditorSession var1);

   @Override
   protected abstract void endShellOpacityBatch(Player var1, EditorSession var2);

   @Override
   protected abstract void setSidebarPanelVisible(Player var1, EditorSession var2, String var3, boolean var4);

   protected void setSavePopupVisible(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         var2.savePopupVisible = var3;
         if (!var3) {
            var2.pendingSavePopupField = null;
         }

         this.beginShellOpacityBatch(var2);

         try {
            this.setSidebarPanelVisible(var1, var2, "popup_save_root", var3);
         } finally {
            this.endShellOpacityBatch(var1, var2);
         }

         double var4 = var2.cursorX + var2.hitboxOffsetX;
         double var6 = var2.cursorY + var2.hitboxOffsetY;
         this.updateSavePopupActionIcons(var1, var2, var4, var6);
      }
   }

   protected void setPreferencesPopupVisible(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         var2.preferencesPopupVisible = var3;
         this.beginShellOpacityBatch(var2);

         try {
            this.setSidebarPanelVisible(var1, var2, "popup_preferences_root", var3);
         } finally {
            this.endShellOpacityBatch(var1, var2);
         }

         if (var3) {
            this.refreshPreferencesPopupFields(var1, var2);
         }

         this.onPreferencesPopupVisibilityChanged(var1, var2, var3);
         double var4 = var2.cursorX + var2.hitboxOffsetX;
         double var6 = var2.cursorY + var2.hitboxOffsetY;
         this.updatePreferencesPopupActionIcons(var1, var2, var4, var6);
      }
   }

   protected void setWelcomePopupVisible(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         var2.welcomePopupVisible = var3;
         this.beginShellOpacityBatch(var2);

         try {
            this.setSidebarPanelVisible(var1, var2, "popup_welcome_backdrop", var3);
            this.setSidebarPanelVisible(var1, var2, "popup_welcome_root", var3);
         } finally {
            this.endShellOpacityBatch(var1, var2);
         }

         double var4 = var2.cursorX + var2.hitboxOffsetX;
         double var6 = var2.cursorY + var2.hitboxOffsetY;
         this.updateWelcomePopupActionIcons(var1, var2, var4, var6);
      }
   }

   protected void handleSavePopupClick(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode && var2.savePopupVisible) {
         if (this.isInsideShellBlock(var2, "popup_save_textbox_name", var3, var5)) {
            this.startSavePopupInputPrompt(var1, var2, EditorSavePopupField.NAME);
         } else if (this.isInsideShellBlock(var2, "popup_save_textbox_desc", var3, var5)) {
            this.startSavePopupInputPrompt(var1, var2, EditorSavePopupField.DESCRIPTION);
         } else if (this.isInsideShellBlock(var2, "popup_save_textbox_command", var3, var5)) {
            this.startSavePopupInputPrompt(var1, var2, EditorSavePopupField.COMMAND);
         } else if (this.isInsideShellBlock(var2, "popup_save_cancel_hitbox", var3, var5)) {
            this.setSavePopupVisible(var1, var2, false);
         } else if (!this.isInsideShellBlock(var2, "popup_save_confirm_hitbox", var3, var5)) {
            this.updateSavePopupActionIcons(var1, var2, var3, var5);
         } else if (this.isSavePopupNameMissing(var2)) {
            this.updateSavePopupActionIcons(var1, var2, var3, var5);
            this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Name cannot be empty.</yellow>"));
         } else {
            this.handleSavePopupConfirm(var1, var2);
            EditorSession var7 = this.editorSessions.get(var1.getUniqueId());
            if (var7 == var2) {
               this.updateSavePopupActionIcons(var1, var2, var3, var5);
            }
         }
      }
   }

   protected void handlePreferencesPopupClick(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode && var2.preferencesPopupVisible) {
         if (this.isInsideShellBlock(var2, "popup_preferences_hotbar_name", var3, var5)) {
            if (this.handlePreferencesPopupToggle(var1, var2, "popup_preferences_hotbar_name")) {
               this.refreshPreferencesPopupFields(var1, var2);
            }

            this.updatePreferencesPopupActionIcons(var1, var2, var3, var5);
         } else if (this.isInsideShellBlock(var2, "popup_preferences_hand_name", var3, var5)) {
            if (this.handlePreferencesPopupToggle(var1, var2, "popup_preferences_hand_name")) {
               this.refreshPreferencesPopupFields(var1, var2);
            }

            this.updatePreferencesPopupActionIcons(var1, var2, var3, var5);
         } else if (this.isInsideShellBlock(var2, "popup_preferences_optimized_name", var3, var5)) {
            if (this.handlePreferencesPopupToggle(var1, var2, "popup_preferences_optimized_name")) {
               this.refreshPreferencesPopupFields(var1, var2);
            }

            this.updatePreferencesPopupActionIcons(var1, var2, var3, var5);
         } else if (this.isInsideShellBlock(var2, "popup_preferences_sound_name", var3, var5)) {
            if (this.handlePreferencesPopupToggle(var1, var2, "popup_preferences_sound_name")) {
               this.refreshPreferencesPopupFields(var1, var2);
            }

            this.updatePreferencesPopupActionIcons(var1, var2, var3, var5);
         } else if (this.isInsideShellBlock(var2, "popup_preferences_cancel_hitbox", var3, var5)) {
            this.setPreferencesPopupVisible(var1, var2, false);
         } else if (this.isInsideShellBlock(var2, "popup_preferences_confirm_hitbox", var3, var5) && this.handlePreferencesPopupConfirm(var1, var2)) {
            this.setPreferencesPopupVisible(var1, var2, false);
         } else {
            this.updatePreferencesPopupActionIcons(var1, var2, var3, var5);
         }
      }
   }

   protected void handleWelcomePopupClick(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode && var2.welcomePopupVisible) {
         if (this.isInsideShellBlock(var2, "popup_welcome_link_hitbox", var3, var5)) {
            this.sendWelcomePopupDocsMessage(var1);
            this.playWelcomePopupSound(var1, var2, "ultimateui.extra7");
            this.updateWelcomePopupActionIcons(var1, var2, var3, var5);
         } else if (this.isInsideShellBlock(var2, "popup_welcome_confirm_hitbox", var3, var5) && this.handleWelcomePopupConfirm(var1, var2)) {
            this.setWelcomePopupVisible(var1, var2, false);
            this.playWelcomePopupSound(var1, var2, "ultimateui.extra3");
         } else {
            this.updateWelcomePopupActionIcons(var1, var2, var3, var5);
         }
      }
   }

   protected void updateSavePopupHover(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode && var2.savePopupVisible) {
         this.updateSavePopupActionIcons(var1, var2, var3, var5);
      }
   }

   protected void updatePreferencesPopupHover(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode && var2.preferencesPopupVisible) {
         this.updatePreferencesPopupActionIcons(var1, var2, var3, var5);
      }
   }

   protected void updateWelcomePopupHover(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode && var2.welcomePopupVisible) {
         this.updateWelcomePopupActionIcons(var1, var2, var3, var5);
      }
   }

   protected void updateSavePopupActionIcons(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         boolean var7 = var2.savePopupVisible && this.isInsideShellBlock(var2, "popup_save_confirm_hitbox", var3, var5);
         boolean var8 = var2.savePopupVisible && this.isInsideShellBlock(var2, "popup_save_cancel_hitbox", var3, var5);
         boolean var9 = this.isSavePopupNameMissing(var2);
         String var10;
         if (var9) {
            var10 = "\ueeaa";
         } else {
            var10 = var7 ? "\ueea8" : "\ueea5";
         }

         String var11 = var8 ? "\ueea9" : "\ueea6";
         this.setShellText(var1, "popup_save_confirm", var10);
         this.setShellText(var1, "popup_save_cancel", var11);
      }
   }

   protected void updatePreferencesPopupActionIcons(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         boolean var7 = var2.preferencesPopupVisible && this.isInsideShellBlock(var2, "popup_preferences_confirm_hitbox", var3, var5);
         boolean var8 = var2.preferencesPopupVisible && this.isInsideShellBlock(var2, "popup_preferences_cancel_hitbox", var3, var5);
         String var9 = var7 ? "\ueea8" : "\ueea5";
         String var10 = var8 ? "\ueea9" : "\ueea6";
         this.setShellText(var1, "popup_preferences_confirm", var9);
         this.setShellText(var1, "popup_preferences_cancel", var10);
      }
   }

   protected void updateWelcomePopupActionIcons(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         boolean var7 = var2.welcomePopupVisible && this.isInsideShellBlock(var2, "popup_welcome_confirm_hitbox", var3, var5);
         String var8 = var7 ? "\ueea8" : "\ueea5";
         this.setShellText(var1, "popup_welcome_confirm", var8);
      }
   }

   protected void sendWelcomePopupDocsMessage(Player var1) {
      if (var1 != null) {
         var1.sendMessage("");
         var1.sendMessage(MM.deserialize(" <white>ɴᴇᴇᴅ ʜᴇʟᴘ ᴡɪᴛʜ ᴛʜᴇ ᴘʟᴜɢɪɴ? ᴄʜᴇᴄᴋ ᴏᴜᴛ ᴏᴜʀ"));
         var1.sendMessage(
            MM.deserialize(
               " <white>ᴅᴏᴄᴜᴍᴇɴᴛᴀᴛɪᴏɴ ᴀᴛ <yellow><hover:show_text:'<green>Click here to open!'><click:open_url:'https://xqedii.dev/docs/plugins/ultimate-ui'>https://docs.xqedii.dev/</click></hover><reset>"
            )
         );
         var1.sendMessage("");
      }
   }

   protected void playWelcomePopupSound(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var3 != null && !var3.isBlank()) {
         if (this.plugin.getConfig().getBoolean("editor.sounds.enabled", true)) {
            if (var2 == null || var2.editorSounds) {
               float var4 = (float)Math.max(0.0, this.plugin.getConfig().getDouble("editor.sounds.volume", 1.0));

               try {
                  var1.playSound(var1.getLocation(), var3, SoundCategory.MASTER, var4, 1.0F);
               } catch (RuntimeException var6) {
               }
            }
         }
      }
   }

   protected boolean isSavePopupNameMissing(EditorSession var1) {
      return var1 == null ? true : this.firstNonBlank(new String[]{var1.savePopupName}).trim().isEmpty();
   }
}
