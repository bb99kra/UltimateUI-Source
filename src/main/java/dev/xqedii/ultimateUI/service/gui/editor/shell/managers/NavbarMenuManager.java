package dev.xqedii.ultimateUI.service.gui.editor.shell.managers;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.hud.HudService;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.bukkit.entity.Player;

public abstract class NavbarMenuManager extends LayerTreeManager {
   private static final String NAVBAR_FILE_HITBOX_ID = "navbar_file";
   private static final String NAVBAR_FILE_TEXT_ID = "navbar_file_text";
   private static final String NAVBAR_EDIT_HITBOX_ID = "navbar_edit";
   private static final String NAVBAR_EDIT_TEXT_ID = "navbar_edit_text";
   private static final String NAVBAR_SELECTION_HITBOX_ID = "navbar_selection";
   private static final String NAVBAR_SELECTION_TEXT_ID = "navbar_selection_text";
   private static final String NAVBAR_LAYER_HITBOX_ID = "navbar_layer";
   private static final String NAVBAR_LAYER_TEXT_ID = "navbar_layer_text";
   private static final String NAVBAR_WINDOW_HITBOX_ID = "navbar_window";
   private static final String NAVBAR_WINDOW_TEXT_ID = "navbar_window_text";
   private static final String NAVBAR_UNDO_TEXT_ID = "editor_navbar_undo_text";
   private static final String NAVBAR_REDO_TEXT_ID = "editor_navbar_redo_text";
   private static final String NAVBAR_UNDO_HITBOX_ID = "editor_navbar_undo";
   private static final String NAVBAR_REDO_HITBOX_ID = "editor_navbar_redo";
   private static final String NAVBAR_HOME_HITBOX_ID = "editor_navbar_home";
   private static final String NAVBAR_EXPORT_HITBOX_ID = "editor_navbar_export";
   private static final String FILE_DROPDOWN_ID = "file_dropdown";
   private static final String EDIT_DROPDOWN_ID = "edit_dropdown";
   private static final String SELECTION_DROPDOWN_ID = "selection_dropdown";
   private static final String LAYER_DROPDOWN_ID = "layer_dropdown";
   private static final String WINDOW_DROPDOWN_ID = "window_dropdown";
   private static final String ANIMATION_TIMELINE_PANEL_ID = "animation_timeline";
   private static final String KEYFRAME_DROPDOWN_ID = "keyframe_dropdown";
   private static final String KEYFRAME_TIMELINE_DROPDOWN_ID = "keyframe_timeline_dropdown";
   private static final String FILE_DROPDOWN_ITEM_TEXT_SUFFIX = "_text";
   private static final String FILE_DROPDOWN_ITEM_HITBOX_SUFFIX = "_hitbox";
   private static final String FILE_DROPDOWN_INACTIVE_COLOR = "0f0f0f";
   private static final String FILE_DROPDOWN_ACTIVE_COLOR = "141414";
   private static final String NAVBAR_FILE_ICON_INACTIVE = "\ue5c1";
   private static final String NAVBAR_FILE_ICON_ACTIVE = "\ue5cb";
   private static final String NAVBAR_EDIT_ICON_INACTIVE = "\ue5c2";
   private static final String NAVBAR_EDIT_ICON_ACTIVE = "\ue5cc";
   private static final String NAVBAR_SELECTION_ICON_INACTIVE = "\ue5c3";
   private static final String NAVBAR_SELECTION_ICON_ACTIVE = "\ue5cd";
   private static final String NAVBAR_LAYER_ICON_INACTIVE = "\ue5c4";
   private static final String NAVBAR_LAYER_ICON_ACTIVE = "\ue5ce";
   private static final String NAVBAR_WINDOW_ICON_INACTIVE = "\ue5c5";
   private static final String NAVBAR_WINDOW_ICON_ACTIVE = "\ue5cf";
   private static final String NAVBAR_REDO_ICON_ENABLED = "\ue5c8";
   private static final String NAVBAR_REDO_ICON_DISABLED = "\ue6a0";
   private static final String NAVBAR_UNDO_ICON_ENABLED = "\ue5c9";
   private static final String NAVBAR_UNDO_ICON_DISABLED = "\ue6a1";
   private static final List<String> FILE_DROPDOWN_ITEM_IDS = List.of(
      "dropdown_file_new", "dropdown_file_open", "dropdown_file_save", "dropdown_file_save_as", "dropdown_file_close_all", "dropdown_file_close"
   );
   private static final Map<String, String[]> FILE_DROPDOWN_ITEM_ICONS = Map.of(
      "dropdown_file_new",
      new String[]{"\uee70", "\uee76"},
      "dropdown_file_open",
      new String[]{"\uee71", "\uee77"},
      "dropdown_file_save",
      new String[]{"\uee72", "\uee78"},
      "dropdown_file_save_as",
      new String[]{"\uee73", "\uee79"},
      "dropdown_file_close_all",
      new String[]{"\uee74", "\uee7a"},
      "dropdown_file_close",
      new String[]{"\uee75", "\uee7b"}
   );
   private static final List<String> EDIT_DROPDOWN_ITEM_IDS = List.of(
      "dropdown_edit_undo",
      "dropdown_edit_redo",
      "dropdown_edit_cut",
      "dropdown_edit_copy",
      "dropdown_edit_paste",
      "dropdown_edit_preferences",
      "dropdown_edit_shortcuts"
   );
   private static final Map<String, String[]> EDIT_DROPDOWN_ITEM_ICONS = Map.of(
      "dropdown_edit_undo",
      new String[]{"\uee80", "\uee87"},
      "dropdown_edit_redo",
      new String[]{"\uee81", "\uee88"},
      "dropdown_edit_cut",
      new String[]{"\uee82", "\uee89"},
      "dropdown_edit_copy",
      new String[]{"\uee83", "\uee8a"},
      "dropdown_edit_paste",
      new String[]{"\uee84", "\uee8b"},
      "dropdown_edit_preferences",
      new String[]{"\uee85", "\uee8c"},
      "dropdown_edit_shortcuts",
      new String[]{"\uee86", "\uee8d"}
   );
   private static final List<String> SELECTION_DROPDOWN_ITEM_IDS = List.of(
      "dropdown_selection_all",
      "dropdown_selection_none",
      "dropdown_selection_reverse",
      "dropdown_selection_hide",
      "dropdown_selection_unhide",
      "dropdown_selection_block",
      "dropdown_selection_unblock"
   );
   private static final Map<String, String[]> SELECTION_DROPDOWN_ITEM_ICONS = Map.of(
      "dropdown_selection_all",
      new String[]{"\uee90", "\uee97"},
      "dropdown_selection_none",
      new String[]{"\uee91", "\uee98"},
      "dropdown_selection_reverse",
      new String[]{"\uee92", "\uee99"},
      "dropdown_selection_hide",
      new String[]{"\uee93", "\uee9a"},
      "dropdown_selection_unhide",
      new String[]{"\uee94", "\uee9b"},
      "dropdown_selection_block",
      new String[]{"\uee95", "\uee9c"},
      "dropdown_selection_unblock",
      new String[]{"\uee96", "\uee9d"}
   );
   private static final List<String> LAYER_DROPDOWN_ITEM_IDS = List.of(
      "dropdown_layer_newblock",
      "dropdown_layer_newtext",
      "dropdown_layer_newitem",
      "dropdown_layer_deletelayer",
      "dropdown_layer_movedown",
      "dropdown_layer_moveup"
   );
   private static final Map<String, String[]> LAYER_DROPDOWN_ITEM_ICONS = Map.of(
      "dropdown_layer_newblock",
      new String[]{"\ueeb0", "\ueeb6"},
      "dropdown_layer_newtext",
      new String[]{"\ueeb1", "\ueeb7"},
      "dropdown_layer_newitem",
      new String[]{"\ueeb2", "\ueeb8"},
      "dropdown_layer_deletelayer",
      new String[]{"\ueeb3", "\ueeb9"},
      "dropdown_layer_movedown",
      new String[]{"\ueeb4", "\ueeba"},
      "dropdown_layer_moveup",
      new String[]{"\ueeb5", "\ueebb"}
   );
   private static final List<String> WINDOW_DROPDOWN_ITEM_IDS = List.of("dropdown_window_hidetools", "dropdown_window_hidepages", "dropdown_window_hidefooter");
   private static final Map<String, String[]> WINDOW_DROPDOWN_ITEM_ICONS = Map.of(
      "dropdown_window_hidetools",
      new String[]{"\ueec0", "\ueec3"},
      "dropdown_window_hidepages",
      new String[]{"\ueec1", "\ueec4"},
      "dropdown_window_hidefooter",
      new String[]{"\ueec2", "\ueec5"}
   );

   protected NavbarMenuManager(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   protected boolean onEditorNavbarUndoClick(Player var1, EditorSession var2) {
      return false;
   }

   protected boolean onEditorNavbarRedoClick(Player var1, EditorSession var2) {
      return false;
   }

   protected boolean onEditorNavbarHomeClick(Player var1, EditorSession var2) {
      return false;
   }

   protected boolean onEditorNavbarExportClick(Player var1, EditorSession var2) {
      return false;
   }

   protected boolean canEditorNavbarUndo(EditorSession var1) {
      return false;
   }

   protected boolean canEditorNavbarRedo(EditorSession var1) {
      return false;
   }

   protected abstract boolean handleEditorFileDropdownAction(Player var1, EditorSession var2, String var3);

   protected abstract boolean handleEditorEditDropdownAction(Player var1, EditorSession var2, String var3);

   protected abstract boolean handleEditorSelectionDropdownAction(Player var1, EditorSession var2, String var3);

   protected abstract boolean handleEditorLayerDropdownAction(Player var1, EditorSession var2, String var3);

   protected abstract boolean handleEditorWindowDropdownAction(Player var1, EditorSession var2, String var3);

   protected abstract void closeAnimationTimelineContextDropdowns(Player var1, EditorSession var2);

   protected abstract boolean isInsideShellBlock(EditorSession var1, String var2, double var3, double var5);

   protected abstract void beginShellOpacityBatch(EditorSession var1);

   protected abstract void endShellOpacityBatch(Player var1, EditorSession var2);

   protected abstract void setSidebarPanelVisible(Player var1, EditorSession var2, String var3, boolean var4);

   protected abstract void setShellText(Player var1, String var2, String var3);

   protected abstract void setShellColor(Player var1, EditorSession var2, String var3, String var4);

   protected abstract EditorRect findShellBlockRect(EditorSession var1, String var2);

   protected boolean handleEditorNavbarClick(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         if (var2.keyframeDropdownVisible || var2.keyframeTimelineDropdownVisible) {
            boolean var7 = this.isInsideShellBlock(var2, "animation_timeline", var3, var5);
            boolean var8 = this.isInsideShellBlock(var2, "keyframe_dropdown", var3, var5)
               || this.isInsideShellBlock(var2, "keyframe_timeline_dropdown", var3, var5)
               || this.isInsideShellBlock(var2, "keyframe_dropdown", var3 - var2.hitboxOffsetX, var5 - var2.hitboxOffsetY)
               || this.isInsideShellBlock(var2, "keyframe_timeline_dropdown", var3 - var2.hitboxOffsetX, var5 - var2.hitboxOffsetY);
            if (!var7 && !var8) {
               this.closeAnimationTimelineContextDropdowns(var1, var2);
            }
         }

         if (this.isInsideShellBlock(var2, "editor_navbar_undo", var3, var5)) {
            this.setFileDropdownVisible(var1, var2, false);
            this.setEditDropdownVisible(var1, var2, false);
            this.setSelectionDropdownVisible(var1, var2, false);
            this.setLayerDropdownVisible(var1, var2, false);
            this.setWindowDropdownVisible(var1, var2, false);
            this.updateEditorNavbarHover(var1, var2, var3, var5);
            boolean var20 = this.onEditorNavbarUndoClick(var1, var2);
            EditorSession var27 = this.editorSessions.get(var1.getUniqueId());
            if (var27 == var2) {
               this.updateEditorNavbarHover(var1, var2, var3, var5);
            }

            return var20;
         } else if (this.isInsideShellBlock(var2, "editor_navbar_redo", var3, var5)) {
            this.setFileDropdownVisible(var1, var2, false);
            this.setEditDropdownVisible(var1, var2, false);
            this.setSelectionDropdownVisible(var1, var2, false);
            this.setLayerDropdownVisible(var1, var2, false);
            this.setWindowDropdownVisible(var1, var2, false);
            this.updateEditorNavbarHover(var1, var2, var3, var5);
            boolean var19 = this.onEditorNavbarRedoClick(var1, var2);
            EditorSession var26 = this.editorSessions.get(var1.getUniqueId());
            if (var26 == var2) {
               this.updateEditorNavbarHover(var1, var2, var3, var5);
            }

            return var19;
         } else if (this.isInsideShellBlock(var2, "editor_navbar_home", var3, var5)) {
            this.setFileDropdownVisible(var1, var2, false);
            this.setEditDropdownVisible(var1, var2, false);
            this.setSelectionDropdownVisible(var1, var2, false);
            this.setLayerDropdownVisible(var1, var2, false);
            this.setWindowDropdownVisible(var1, var2, false);
            this.updateEditorNavbarHover(var1, var2, var3, var5);
            return this.onEditorNavbarHomeClick(var1, var2);
         } else if (this.isInsideShellBlock(var2, "editor_navbar_export", var3, var5)) {
            this.setFileDropdownVisible(var1, var2, false);
            this.setEditDropdownVisible(var1, var2, false);
            this.setSelectionDropdownVisible(var1, var2, false);
            this.setLayerDropdownVisible(var1, var2, false);
            this.setWindowDropdownVisible(var1, var2, false);
            this.updateEditorNavbarHover(var1, var2, var3, var5);
            return this.onEditorNavbarExportClick(var1, var2);
         } else {
            if (var2.fileDropdownVisible) {
               String var9 = this.resolveHoveredFileDropdownTargetId(var2, var3, var5);
               if (var9 != null) {
                  this.setFileDropdownVisible(var1, var2, false);
                  this.playEditorSfx(var1, var2, "dropdown-item-clicked");
                  this.handleEditorFileDropdownAction(var1, var2, var9);
                  EditorSession var25 = this.editorSessions.get(var1.getUniqueId());
                  if (var25 == var2) {
                     this.updateEditorNavbarHover(var1, var2, var3, var5);
                  }

                  return true;
               }

               if (this.isInsideShellBlock(var2, "file_dropdown", var3, var5)) {
                  return true;
               }
            }

            if (var2.editDropdownVisible) {
               String var10 = this.resolveHoveredEditDropdownTargetId(var2, var3, var5);
               if (var10 != null) {
                  this.setEditDropdownVisible(var1, var2, false);
                  this.playEditorSfx(var1, var2, "dropdown-item-clicked");
                  this.handleEditorEditDropdownAction(var1, var2, var10);
                  EditorSession var24 = this.editorSessions.get(var1.getUniqueId());
                  if (var24 == var2) {
                     this.updateEditorNavbarHover(var1, var2, var3, var5);
                  }

                  return true;
               }

               if (this.isInsideShellBlock(var2, "edit_dropdown", var3, var5)) {
                  return true;
               }
            }

            if (var2.selectionDropdownVisible) {
               String var11 = this.resolveHoveredSelectionDropdownTargetId(var2, var3, var5);
               if (var11 != null) {
                  this.setSelectionDropdownVisible(var1, var2, false);
                  this.playEditorSfx(var1, var2, "dropdown-item-clicked");
                  this.handleEditorSelectionDropdownAction(var1, var2, var11);
                  EditorSession var23 = this.editorSessions.get(var1.getUniqueId());
                  if (var23 == var2) {
                     this.updateEditorNavbarHover(var1, var2, var3, var5);
                  }

                  return true;
               }

               if (this.isInsideShellBlock(var2, "selection_dropdown", var3, var5)) {
                  return true;
               }
            }

            if (var2.layerDropdownVisible) {
               String var12 = this.resolveHoveredLayerDropdownTargetId(var2, var3, var5);
               if (var12 != null) {
                  this.setLayerDropdownVisible(var1, var2, false);
                  this.playEditorSfx(var1, var2, "dropdown-item-clicked");
                  this.handleEditorLayerDropdownAction(var1, var2, var12);
                  EditorSession var22 = this.editorSessions.get(var1.getUniqueId());
                  if (var22 == var2) {
                     this.updateEditorNavbarHover(var1, var2, var3, var5);
                  }

                  return true;
               }

               if (this.isInsideShellBlock(var2, "layer_dropdown", var3, var5)) {
                  return true;
               }
            }

            if (var2.windowDropdownVisible) {
               String var13 = this.resolveHoveredWindowDropdownTargetId(var2, var3, var5);
               if (var13 != null) {
                  this.setWindowDropdownVisible(var1, var2, false);
                  this.playEditorSfx(var1, var2, "dropdown-item-clicked");
                  this.handleEditorWindowDropdownAction(var1, var2, var13);
                  EditorSession var21 = this.editorSessions.get(var1.getUniqueId());
                  if (var21 == var2) {
                     this.updateEditorNavbarHover(var1, var2, var3, var5);
                  }

                  return true;
               }

               if (this.isInsideShellBlock(var2, "window_dropdown", var3, var5)) {
                  return true;
               }
            }

            if (this.isInsideShellBlock(var2, "navbar_file", var3, var5)) {
               boolean var18 = !var2.fileDropdownVisible;
               this.setEditDropdownVisible(var1, var2, false);
               this.setSelectionDropdownVisible(var1, var2, false);
               this.setLayerDropdownVisible(var1, var2, false);
               this.setWindowDropdownVisible(var1, var2, false);
               this.setFileDropdownVisible(var1, var2, var18);
               this.updateEditorNavbarHover(var1, var2, var3, var5);
               return true;
            } else if (this.isInsideShellBlock(var2, "navbar_edit", var3, var5)) {
               boolean var17 = !var2.editDropdownVisible;
               this.setFileDropdownVisible(var1, var2, false);
               this.setSelectionDropdownVisible(var1, var2, false);
               this.setLayerDropdownVisible(var1, var2, false);
               this.setWindowDropdownVisible(var1, var2, false);
               this.setEditDropdownVisible(var1, var2, var17);
               this.updateEditorNavbarHover(var1, var2, var3, var5);
               return true;
            } else if (this.isInsideShellBlock(var2, "navbar_selection", var3, var5)) {
               boolean var16 = !var2.selectionDropdownVisible;
               this.setFileDropdownVisible(var1, var2, false);
               this.setEditDropdownVisible(var1, var2, false);
               this.setLayerDropdownVisible(var1, var2, false);
               this.setWindowDropdownVisible(var1, var2, false);
               this.setSelectionDropdownVisible(var1, var2, var16);
               this.updateEditorNavbarHover(var1, var2, var3, var5);
               return true;
            } else if (this.isInsideShellBlock(var2, "navbar_layer", var3, var5)) {
               boolean var15 = !var2.layerDropdownVisible;
               this.setFileDropdownVisible(var1, var2, false);
               this.setEditDropdownVisible(var1, var2, false);
               this.setSelectionDropdownVisible(var1, var2, false);
               this.setWindowDropdownVisible(var1, var2, false);
               this.setLayerDropdownVisible(var1, var2, var15);
               this.updateEditorNavbarHover(var1, var2, var3, var5);
               return true;
            } else if (this.isInsideShellBlock(var2, "navbar_window", var3, var5)) {
               boolean var14 = !var2.windowDropdownVisible;
               this.setFileDropdownVisible(var1, var2, false);
               this.setEditDropdownVisible(var1, var2, false);
               this.setSelectionDropdownVisible(var1, var2, false);
               this.setLayerDropdownVisible(var1, var2, false);
               this.setWindowDropdownVisible(var1, var2, var14);
               this.updateEditorNavbarHover(var1, var2, var3, var5);
               return true;
            } else {
               if (var2.fileDropdownVisible
                  || var2.editDropdownVisible
                  || var2.selectionDropdownVisible
                  || var2.layerDropdownVisible
                  || var2.windowDropdownVisible) {
                  this.setFileDropdownVisible(var1, var2, false);
                  this.setEditDropdownVisible(var1, var2, false);
                  this.setSelectionDropdownVisible(var1, var2, false);
                  this.setLayerDropdownVisible(var1, var2, false);
                  this.setWindowDropdownVisible(var1, var2, false);
                  this.updateEditorNavbarHover(var1, var2, var3, var5);
               }

               return false;
            }
         }
      } else {
         return false;
      }
   }

   protected void updateEditorNavbarHover(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         this.updateEditorNavbarHistoryIcons(var1, var2);
         boolean var7 = this.isInsideShellBlock(var2, "navbar_file", var3, var5);
         boolean var8 = var7 || var2.fileDropdownVisible;
         String var9 = var2.fileDropdownVisible ? this.resolveHoveredFileDropdownTargetId(var2, var3, var5) : null;
         boolean var10 = this.isInsideShellBlock(var2, "navbar_edit", var3, var5);
         boolean var11 = var10 || var2.editDropdownVisible;
         String var12 = var2.editDropdownVisible ? this.resolveHoveredEditDropdownTargetId(var2, var3, var5) : null;
         boolean var13 = this.isInsideShellBlock(var2, "navbar_selection", var3, var5);
         boolean var14 = var13 || var2.selectionDropdownVisible;
         String var15 = var2.selectionDropdownVisible ? this.resolveHoveredSelectionDropdownTargetId(var2, var3, var5) : null;
         boolean var16 = this.isInsideShellBlock(var2, "navbar_layer", var3, var5);
         boolean var17 = var16 || var2.layerDropdownVisible;
         String var18 = var2.layerDropdownVisible ? this.resolveHoveredLayerDropdownTargetId(var2, var3, var5) : null;
         boolean var19 = this.isInsideShellBlock(var2, "navbar_window", var3, var5);
         boolean var20 = var19 || var2.windowDropdownVisible;
         String var21 = var2.windowDropdownVisible ? this.resolveHoveredWindowDropdownTargetId(var2, var3, var5) : null;
         if (var2.fileNavbarVisualActive != var8
            || var2.editNavbarVisualActive != var11
            || var2.selectionNavbarVisualActive != var14
            || var2.layerNavbarVisualActive != var17
            || var2.windowNavbarVisualActive != var20
            || !this.equalsNullable(var2.fileDropdownHoverTargetId, var9)
            || !this.equalsNullable(var2.editDropdownHoverTargetId, var12)
            || !this.equalsNullable(var2.selectionDropdownHoverTargetId, var15)
            || !this.equalsNullable(var2.layerDropdownHoverTargetId, var18)
            || !this.equalsNullable(var2.windowDropdownHoverTargetId, var21)) {
            var2.fileNavbarVisualActive = var8;
            var2.editNavbarVisualActive = var11;
            var2.selectionNavbarVisualActive = var14;
            var2.layerNavbarVisualActive = var17;
            var2.windowNavbarVisualActive = var20;
            var2.fileDropdownHoverTargetId = var9;
            var2.editDropdownHoverTargetId = var12;
            var2.selectionDropdownHoverTargetId = var15;
            var2.layerDropdownHoverTargetId = var18;
            var2.windowDropdownHoverTargetId = var21;
            this.setShellText(var1, "navbar_file_text", var8 ? "\ue5cb" : "\ue5c1");
            this.setShellText(var1, "navbar_edit_text", var11 ? "\ue5cc" : "\ue5c2");
            this.setShellText(var1, "navbar_selection_text", var14 ? "\ue5cd" : "\ue5c3");
            this.setShellText(var1, "navbar_layer_text", var17 ? "\ue5ce" : "\ue5c4");
            this.setShellText(var1, "navbar_window_text", var20 ? "\ue5cf" : "\ue5c5");

            for (Object var23_raw : FILE_DROPDOWN_ITEM_IDS) {
               String var23 = var23_raw != null ? var23_raw.toString() : null;
               this.setFileDropdownItemVisual(var1, var2, var23, this.equalsNullable(var23, var9));
            }

            for (Object var28_raw : EDIT_DROPDOWN_ITEM_IDS) {
               String var28 = var28_raw != null ? var28_raw.toString() : null;
               this.setEditDropdownItemVisual(var1, var2, var28, this.equalsNullable(var28, var12));
            }

            for (Object var29_raw : SELECTION_DROPDOWN_ITEM_IDS) {
               String var29 = var29_raw != null ? var29_raw.toString() : null;
               this.setSelectionDropdownItemVisual(var1, var2, var29, this.equalsNullable(var29, var15));
            }

            for (Object var30_raw : LAYER_DROPDOWN_ITEM_IDS) {
               String var30 = var30_raw != null ? var30_raw.toString() : null;
               this.setLayerDropdownItemVisual(var1, var2, var30, this.equalsNullable(var30, var18));
            }

            for (Object var31_raw : WINDOW_DROPDOWN_ITEM_IDS) {
               String var31 = var31_raw != null ? var31_raw.toString() : null;
               this.setWindowDropdownItemVisual(var1, var2, var31, this.equalsNullable(var31, var21));
            }
         }
      }
   }

   protected void updateEditorNavbarHistoryIcons(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         boolean var3 = this.canEditorNavbarRedo(var2);
         boolean var4 = this.canEditorNavbarUndo(var2);
         this.setShellText(var1, "editor_navbar_redo_text", var3 ? "\ue5c8" : "\ue6a0");
         this.setShellText(var1, "editor_navbar_undo_text", var4 ? "\ue5c9" : "\ue6a1");
      }
   }

   protected void setFileDropdownVisible(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         if (var3) {
            this.setEditDropdownVisible(var1, var2, false);
            this.setSelectionDropdownVisible(var1, var2, false);
            this.setLayerDropdownVisible(var1, var2, false);
            this.setWindowDropdownVisible(var1, var2, false);
         }

         if (var2.fileDropdownVisible != var3) {
            var2.fileDropdownVisible = var3;
            if (!var3) {
               var2.fileDropdownHoverTargetId = null;
            } else {
               this.playEditorSfx(var1, var2, "dropdown-opened");
            }

            this.beginShellOpacityBatch(var2);

            try {
               this.setSidebarPanelVisible(var1, var2, "file_dropdown", var3);
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }
         }
      }
   }

   protected void setEditDropdownVisible(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         if (var3) {
            this.setFileDropdownVisible(var1, var2, false);
            this.setSelectionDropdownVisible(var1, var2, false);
            this.setLayerDropdownVisible(var1, var2, false);
            this.setWindowDropdownVisible(var1, var2, false);
         }

         if (var2.editDropdownVisible != var3) {
            var2.editDropdownVisible = var3;
            if (!var3) {
               var2.editDropdownHoverTargetId = null;
            } else {
               this.playEditorSfx(var1, var2, "dropdown-opened");
            }

            this.beginShellOpacityBatch(var2);

            try {
               this.setSidebarPanelVisible(var1, var2, "edit_dropdown", var3);
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }
         }
      }
   }

   protected void setSelectionDropdownVisible(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         if (var3) {
            this.setFileDropdownVisible(var1, var2, false);
            this.setEditDropdownVisible(var1, var2, false);
            this.setLayerDropdownVisible(var1, var2, false);
            this.setWindowDropdownVisible(var1, var2, false);
         }

         if (var2.selectionDropdownVisible != var3) {
            var2.selectionDropdownVisible = var3;
            if (!var3) {
               var2.selectionDropdownHoverTargetId = null;
            } else {
               this.playEditorSfx(var1, var2, "dropdown-opened");
            }

            this.beginShellOpacityBatch(var2);

            try {
               this.setSidebarPanelVisible(var1, var2, "selection_dropdown", var3);
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }
         }
      }
   }

   protected void setLayerDropdownVisible(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         if (var3) {
            this.setFileDropdownVisible(var1, var2, false);
            this.setEditDropdownVisible(var1, var2, false);
            this.setSelectionDropdownVisible(var1, var2, false);
            this.setWindowDropdownVisible(var1, var2, false);
         }

         if (var2.layerDropdownVisible != var3) {
            var2.layerDropdownVisible = var3;
            if (!var3) {
               var2.layerDropdownHoverTargetId = null;
            } else {
               this.playEditorSfx(var1, var2, "dropdown-opened");
            }

            this.beginShellOpacityBatch(var2);

            try {
               this.setSidebarPanelVisible(var1, var2, "layer_dropdown", var3);
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }
         }
      }
   }

   protected void setWindowDropdownVisible(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         if (var3) {
            this.setFileDropdownVisible(var1, var2, false);
            this.setEditDropdownVisible(var1, var2, false);
            this.setSelectionDropdownVisible(var1, var2, false);
            this.setLayerDropdownVisible(var1, var2, false);
         }

         if (var2.windowDropdownVisible != var3) {
            var2.windowDropdownVisible = var3;
            if (!var3) {
               var2.windowDropdownHoverTargetId = null;
            } else {
               this.playEditorSfx(var1, var2, "dropdown-opened");
            }

            this.beginShellOpacityBatch(var2);

            try {
               this.setSidebarPanelVisible(var1, var2, "window_dropdown", var3);
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }
         }
      }
   }

   protected String resolveHoveredFileDropdownTargetId(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.fileDropdownVisible) {
         double var6 = var2 - var1.hitboxOffsetX;
         double var8 = var4 - var1.hitboxOffsetY;
         int var10 = 0;

         for (Object var12_raw : FILE_DROPDOWN_ITEM_IDS) {
            String var12 = var12_raw != null ? var12_raw.toString() : null;
            String var13 = var12 + "_hitbox";
            EditorRect var14 = this.findShellBlockRect(var1, var13);
            if (var14 != null) {
               var10++;
               if (var6 >= var14.x && var6 <= var14.maxX() && var8 >= var14.y && var8 <= var14.maxY()) {
                  return var12;
               }
            }
         }

         if (var10 == FILE_DROPDOWN_ITEM_IDS.size()) {
            return null;
         } else {
            EditorRect var15 = this.findShellBlockRect(var1, "file_dropdown");
            if (var15 != null && var6 >= var15.x && var6 <= var15.maxX() && var8 >= var15.y && var8 <= var15.maxY()) {
               double var16 = 32.0;
               int var19 = (int)Math.floor((var8 - var15.y) / 32.0);
               if (var19 >= 0 && var19 < FILE_DROPDOWN_ITEM_IDS.size()) {
                  return FILE_DROPDOWN_ITEM_IDS.get(var19);
               }
            }

            for (Object var18_raw : FILE_DROPDOWN_ITEM_IDS) {
               String var18 = var18_raw != null ? var18_raw.toString() : null;
               if (this.isInsideShellBlock(var1, var18, var6, var8)) {
                  return var18;
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   protected String resolveHoveredEditDropdownTargetId(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.editDropdownVisible) {
         double var6 = var2 - var1.hitboxOffsetX;
         double var8 = var4 - var1.hitboxOffsetY;
         int var10 = 0;

         for (Object var12_raw : EDIT_DROPDOWN_ITEM_IDS) {
            String var12 = var12_raw != null ? var12_raw.toString() : null;
            String var13 = var12 + "_hitbox";
            EditorRect var14 = this.findShellBlockRect(var1, var13);
            if (var14 != null) {
               var10++;
               if (var6 >= var14.x && var6 <= var14.maxX() && var8 >= var14.y && var8 <= var14.maxY()) {
                  return var12;
               }
            }
         }

         if (var10 == EDIT_DROPDOWN_ITEM_IDS.size()) {
            return null;
         } else {
            EditorRect var15 = this.findShellBlockRect(var1, "edit_dropdown");
            if (var15 != null && var6 >= var15.x && var6 <= var15.maxX() && var8 >= var15.y && var8 <= var15.maxY()) {
               double var16 = 32.0;
               int var19 = (int)Math.floor((var8 - var15.y) / 32.0);
               if (var19 >= 0 && var19 < EDIT_DROPDOWN_ITEM_IDS.size()) {
                  return EDIT_DROPDOWN_ITEM_IDS.get(var19);
               }
            }

            for (Object var18_raw : EDIT_DROPDOWN_ITEM_IDS) {
               String var18 = var18_raw != null ? var18_raw.toString() : null;
               if (this.isInsideShellBlock(var1, var18, var6, var8)) {
                  return var18;
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   protected String resolveHoveredSelectionDropdownTargetId(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.selectionDropdownVisible) {
         double var6 = var2 - var1.hitboxOffsetX;
         double var8 = var4 - var1.hitboxOffsetY;
         int var10 = 0;

         for (Object var12_raw : SELECTION_DROPDOWN_ITEM_IDS) {
            String var12 = var12_raw != null ? var12_raw.toString() : null;
            String var13 = var12 + "_hitbox";
            EditorRect var14 = this.findShellBlockRect(var1, var13);
            if (var14 != null) {
               var10++;
               if (var6 >= var14.x && var6 <= var14.maxX() && var8 >= var14.y && var8 <= var14.maxY()) {
                  return var12;
               }
            }
         }

         if (var10 == SELECTION_DROPDOWN_ITEM_IDS.size()) {
            return null;
         } else {
            EditorRect var15 = this.findShellBlockRect(var1, "selection_dropdown");
            if (var15 != null && var6 >= var15.x && var6 <= var15.maxX() && var8 >= var15.y && var8 <= var15.maxY()) {
               double var16 = 32.0;
               int var19 = (int)Math.floor((var8 - var15.y) / 32.0);
               if (var19 >= 0 && var19 < SELECTION_DROPDOWN_ITEM_IDS.size()) {
                  return SELECTION_DROPDOWN_ITEM_IDS.get(var19);
               }
            }

            for (Object var18_raw : SELECTION_DROPDOWN_ITEM_IDS) {
               String var18 = var18_raw != null ? var18_raw.toString() : null;
               if (this.isInsideShellBlock(var1, var18, var6, var8)) {
                  return var18;
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   protected String resolveHoveredLayerDropdownTargetId(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.layerDropdownVisible) {
         double var6 = var2 - var1.hitboxOffsetX;
         double var8 = var4 - var1.hitboxOffsetY;
         int var10 = 0;

         for (Object var12_raw : LAYER_DROPDOWN_ITEM_IDS) {
            String var12 = var12_raw != null ? var12_raw.toString() : null;
            String var13 = var12 + "_hitbox";
            EditorRect var14 = this.findShellBlockRect(var1, var13);
            if (var14 != null) {
               var10++;
               if (var6 >= var14.x && var6 <= var14.maxX() && var8 >= var14.y && var8 <= var14.maxY()) {
                  return var12;
               }
            }
         }

         if (var10 == LAYER_DROPDOWN_ITEM_IDS.size()) {
            return null;
         } else {
            EditorRect var15 = this.findShellBlockRect(var1, "layer_dropdown");
            if (var15 != null && var6 >= var15.x && var6 <= var15.maxX() && var8 >= var15.y && var8 <= var15.maxY()) {
               double var16 = 32.0;
               int var19 = (int)Math.floor((var8 - var15.y) / 32.0);
               if (var19 >= 0 && var19 < LAYER_DROPDOWN_ITEM_IDS.size()) {
                  return LAYER_DROPDOWN_ITEM_IDS.get(var19);
               }
            }

            for (Object var18_raw : LAYER_DROPDOWN_ITEM_IDS) {
               String var18 = var18_raw != null ? var18_raw.toString() : null;
               if (this.isInsideShellBlock(var1, var18, var6, var8)) {
                  return var18;
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   protected String resolveHoveredWindowDropdownTargetId(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.windowDropdownVisible) {
         double var6 = var2 - var1.hitboxOffsetX;
         double var8 = var4 - var1.hitboxOffsetY;
         int var10 = 0;

         for (Object var12_raw : WINDOW_DROPDOWN_ITEM_IDS) {
            String var12 = var12_raw != null ? var12_raw.toString() : null;
            String var13 = var12 + "_hitbox";
            EditorRect var14 = this.findShellBlockRect(var1, var13);
            if (var14 != null) {
               var10++;
               if (var6 >= var14.x && var6 <= var14.maxX() && var8 >= var14.y && var8 <= var14.maxY()) {
                  return var12;
               }
            }
         }

         if (var10 == WINDOW_DROPDOWN_ITEM_IDS.size()) {
            return null;
         } else {
            EditorRect var15 = this.findShellBlockRect(var1, "window_dropdown");
            if (var15 != null && var6 >= var15.x && var6 <= var15.maxX() && var8 >= var15.y && var8 <= var15.maxY()) {
               double var16 = 32.0;
               int var19 = (int)Math.floor((var8 - var15.y) / 32.0);
               if (var19 >= 0 && var19 < WINDOW_DROPDOWN_ITEM_IDS.size()) {
                  return WINDOW_DROPDOWN_ITEM_IDS.get(var19);
               }
            }

            for (Object var18_raw : WINDOW_DROPDOWN_ITEM_IDS) {
               String var18 = var18_raw != null ? var18_raw.toString() : null;
               if (this.isInsideShellBlock(var1, var18, var6, var8)) {
                  return var18;
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   protected void setFileDropdownItemVisual(Player var1, EditorSession var2, String var3, boolean var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String[] var5 = FILE_DROPDOWN_ITEM_ICONS.get(var3);
         if (var5 != null && var5.length >= 2) {
            this.setShellText(var1, var3 + "_text", var4 ? var5[1] : var5[0]);
         }

         this.setShellColor(var1, var2, var3, var4 ? "141414" : "0f0f0f");
      }
   }

   protected void setEditDropdownItemVisual(Player var1, EditorSession var2, String var3, boolean var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String[] var5 = EDIT_DROPDOWN_ITEM_ICONS.get(var3);
         if (var5 != null && var5.length >= 2) {
            this.setShellText(var1, var3 + "_text", var4 ? var5[1] : var5[0]);
         }

         this.setShellColor(var1, var2, var3, var4 ? "141414" : "0f0f0f");
      }
   }

   protected void setSelectionDropdownItemVisual(Player var1, EditorSession var2, String var3, boolean var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String[] var5 = SELECTION_DROPDOWN_ITEM_ICONS.get(var3);
         if (var5 != null && var5.length >= 2) {
            this.setShellText(var1, var3 + "_text", var4 ? var5[1] : var5[0]);
         }

         this.setShellColor(var1, var2, var3, var4 ? "141414" : "0f0f0f");
      }
   }

   protected void setLayerDropdownItemVisual(Player var1, EditorSession var2, String var3, boolean var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String[] var5 = LAYER_DROPDOWN_ITEM_ICONS.get(var3);
         if (var5 != null && var5.length >= 2) {
            this.setShellText(var1, var3 + "_text", var4 ? var5[1] : var5[0]);
         }

         this.setShellColor(var1, var2, var3, var4 ? "141414" : "0f0f0f");
      }
   }

   protected void setWindowDropdownItemVisual(Player var1, EditorSession var2, String var3, boolean var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String[] var5 = WINDOW_DROPDOWN_ITEM_ICONS.get(var3);
         if (var5 != null && var5.length >= 2) {
            this.setShellText(var1, var3 + "_text", var4 ? var5[1] : var5[0]);
         }

         this.setShellColor(var1, var2, var3, var4 ? "141414" : "0f0f0f");
      }
   }

   @Override
   protected boolean equalsNullable(String var1, String var2) {
      return Objects.equals(var1, var2);
   }
}
