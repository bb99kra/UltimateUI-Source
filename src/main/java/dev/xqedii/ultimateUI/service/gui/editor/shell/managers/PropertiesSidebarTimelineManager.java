package dev.xqedii.ultimateUI.service.gui.editor.shell.managers;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.editor.shell.rendering.EditorHudRenderer;
import dev.xqedii.ultimateUI.service.gui.editor.shell.utils.ColorUtils;
import dev.xqedii.ultimateUI.service.gui.model.EditorPropertyField;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.gui.model.EditorSidebarTab;
import dev.xqedii.ultimateUI.service.gui.model.EditorTool;
import dev.xqedii.ultimateUI.service.hud.HudPositionCalculator;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.util.PlatformCompat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.bukkit.util.Vector;

public abstract class PropertiesSidebarTimelineManager extends AnimationTimelineOperationsManager {
   private final ColorPickerManager colorPickerManager = new ColorPickerManager();
   protected static final String EDITOR_IMAGE_POPUP_COMPONENT_NAME = "editor_manage_file_popup";
   protected static final String EDITOR_IMAGE_POPUP_ROOT_ID = "popup_file_selector_root";
   protected static final String SIDEBAR_TYPE_ID = "properties_selected_type";
   protected static final String NAVBAR_CONTAINER_ID = "navbar";
   protected static final String NAVBAR_FILE_HITBOX_ID = "navbar_file";
   protected static final String NAVBAR_FILE_TEXT_ID = "navbar_file_text";
   protected static final String NAVBAR_EDIT_HITBOX_ID = "navbar_edit";
   protected static final String NAVBAR_EDIT_TEXT_ID = "navbar_edit_text";
   protected static final String NAVBAR_SELECTION_HITBOX_ID = "navbar_selection";
   protected static final String NAVBAR_SELECTION_TEXT_ID = "navbar_selection_text";
   protected static final String NAVBAR_LAYER_HITBOX_ID = "navbar_layer";
   protected static final String NAVBAR_LAYER_TEXT_ID = "navbar_layer_text";
   protected static final String NAVBAR_WINDOW_HITBOX_ID = "navbar_window";
   protected static final String NAVBAR_WINDOW_TEXT_ID = "navbar_window_text";
   protected static final String NAVBAR_UNDO_TEXT_ID = "editor_navbar_undo_text";
   protected static final String NAVBAR_REDO_TEXT_ID = "editor_navbar_redo_text";
   protected static final String NAVBAR_UNDO_HITBOX_ID = "editor_navbar_undo";
   protected static final String NAVBAR_REDO_HITBOX_ID = "editor_navbar_redo";
   protected static final String NAVBAR_HOME_HITBOX_ID = "editor_navbar_home";
   protected static final String NAVBAR_EXPORT_HITBOX_ID = "editor_navbar_export";
   protected static final String LEFT_SIDEBAR_CONTAINER_ID = "left_sidebar";
   protected static final String LEFT_SIDEBAR_LEGACY_CONTAINER_ID = "leftsidebar";
   protected static final String FOOTER_CONTAINER_ID = "footer";
   protected static final String FILE_DROPDOWN_ID = "file_dropdown";
   protected static final String EDIT_DROPDOWN_ID = "edit_dropdown";
   protected static final String SELECTION_DROPDOWN_ID = "selection_dropdown";
   protected static final String LAYER_DROPDOWN_ID = "layer_dropdown";
   protected static final String WINDOW_DROPDOWN_ID = "window_dropdown";
   protected static final String CURSOR_PAGE_DROPDOWN_ID = "cursor_page_dropdown";
   protected static final String CURSOR_LAYERS_DROPDOWN_ID = "cursor_layers_dropdown";
   protected static final String CURSOR_ACTIONS_DROPDOWN_ID = "cursor_actions_dropdown";
   protected static final String CURSOR_ACTIONS_EDIT_DROPDOWN_ID = "cursor_actions_edit_dropdown";
   protected static final String KEYFRAME_DROPDOWN_ID = "keyframe_dropdown";
   protected static final String KEYFRAME_TIMELINE_DROPDOWN_ID = "keyframe_timeline_dropdown";
   protected static final String PREVIEW_SIZE_PANEL_ID = "preview_size";
   protected static final String PREVIEW_UI_ADDONS_PANEL_ID = "preview_ui_addons";
   protected static final String PREVIEW_EMPTY_INFO_ID = "addelements_info";
   protected static final String PREVIEW_UI_ADDONS_HAND_ICON_ID = "preview_ui_addons_hand_icon";
   protected static final String PREVIEW_UI_ADDONS_HOTBAR_ICON_ID = "preview_ui_addons_hotbar_icon";
   protected static final String PREVIEW_UI_ADDONS_HAND_ICON_UNICODE = "\ue1b3";
   protected static final String PREVIEW_UI_ADDONS_HOTBAR_ICON_UNICODE = "\ue1b4";
   protected static final String FILE_DROPDOWN_ITEM_TEXT_SUFFIX = "_text";
   protected static final String FILE_DROPDOWN_ITEM_HITBOX_SUFFIX = "_hitbox";
   protected static final String FILE_DROPDOWN_INACTIVE_COLOR = "0f0f0f";
   protected static final String FILE_DROPDOWN_ACTIVE_COLOR = "141414";
   protected static final String NAVBAR_FILE_ICON_INACTIVE = "\ue5c1";
   protected static final String NAVBAR_FILE_ICON_ACTIVE = "\ue5cb";
   protected static final String NAVBAR_EDIT_ICON_INACTIVE = "\ue5c2";
   protected static final String NAVBAR_EDIT_ICON_ACTIVE = "\ue5cc";
   protected static final String NAVBAR_SELECTION_ICON_INACTIVE = "\ue5c3";
   protected static final String NAVBAR_SELECTION_ICON_ACTIVE = "\ue5cd";
   protected static final String NAVBAR_LAYER_ICON_INACTIVE = "\ue5c4";
   protected static final String NAVBAR_LAYER_ICON_ACTIVE = "\ue5ce";
   protected static final String NAVBAR_WINDOW_ICON_INACTIVE = "\ue5c5";
   protected static final String NAVBAR_WINDOW_ICON_ACTIVE = "\ue5cf";
   protected static final String NAVBAR_REDO_ICON_ENABLED = "\ue5c8";
   protected static final String NAVBAR_REDO_ICON_DISABLED = "\ue6a0";
   protected static final String NAVBAR_UNDO_ICON_ENABLED = "\ue5c9";
   protected static final String NAVBAR_UNDO_ICON_DISABLED = "\ue6a1";
   protected static final List<String> FILE_DROPDOWN_ITEM_IDS = List.of(
      "dropdown_file_new", "dropdown_file_open", "dropdown_file_save", "dropdown_file_save_as", "dropdown_file_close_all", "dropdown_file_close"
   );
   protected static final Map<String, String[]> FILE_DROPDOWN_ITEM_ICONS = Map.of(
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
   protected static final List<String> EDIT_DROPDOWN_ITEM_IDS = List.of(
      "dropdown_edit_undo",
      "dropdown_edit_redo",
      "dropdown_edit_cut",
      "dropdown_edit_copy",
      "dropdown_edit_paste",
      "dropdown_edit_preferences",
      "dropdown_edit_shortcuts"
   );
   protected static final Map<String, String[]> EDIT_DROPDOWN_ITEM_ICONS = Map.of(
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
   protected static final List<String> SELECTION_DROPDOWN_ITEM_IDS = List.of(
      "dropdown_selection_all",
      "dropdown_selection_none",
      "dropdown_selection_reverse",
      "dropdown_selection_hide",
      "dropdown_selection_unhide",
      "dropdown_selection_block",
      "dropdown_selection_unblock"
   );
   protected static final Map<String, String[]> SELECTION_DROPDOWN_ITEM_ICONS = Map.of(
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
   protected static final List<String> LAYER_DROPDOWN_ITEM_IDS = List.of(
      "dropdown_layer_newblock",
      "dropdown_layer_newtext",
      "dropdown_layer_newitem",
      "dropdown_layer_deletelayer",
      "dropdown_layer_movedown",
      "dropdown_layer_moveup"
   );
   protected static final Map<String, String[]> LAYER_DROPDOWN_ITEM_ICONS = Map.of(
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
   protected static final List<String> WINDOW_DROPDOWN_ITEM_IDS = List.of(
      "dropdown_window_hidetools", "dropdown_window_hidepages", "dropdown_window_hidefooter"
   );
   protected static final Map<String, String[]> WINDOW_DROPDOWN_ITEM_ICONS = Map.of(
      "dropdown_window_hidetools",
      new String[]{"\ueec0", "\ueec3"},
      "dropdown_window_hidepages",
      new String[]{"\ueec1", "\ueec4"},
      "dropdown_window_hidefooter",
      new String[]{"\ueec2", "\ueec5"}
   );
   protected static final List<String> CURSOR_ACTIONS_DROPDOWN_ITEM_IDS = List.of(
      "dropdown_actions_command",
      "dropdown_actions_console",
      "dropdown_actions_message",
      "dropdown_actions_redirect",
      "dropdown_actions_teleport",
      "dropdown_actions_sound",
      "dropdown_actions_delay",
      "dropdown_actions_close",
      "dropdown_actions_animation"
   );
   protected static final Map<String, String[]> CURSOR_ACTIONS_DROPDOWN_ITEM_ICONS = Map.of(
      "dropdown_actions_command",
      new String[]{"\uef10", "\uef16"},
      "dropdown_actions_console",
      new String[]{"\uef24", "\uef25"},
      "dropdown_actions_message",
      new String[]{"\uef11", "\uef17"},
      "dropdown_actions_redirect",
      new String[]{"\uef12", "\uef18"},
      "dropdown_actions_teleport",
      new String[]{"\uef20", "\uef21"},
      "dropdown_actions_sound",
      new String[]{"\uef22", "\uef23"},
      "dropdown_actions_delay",
      new String[]{"\uef15", "\uef1b"},
      "dropdown_actions_close",
      new String[]{"\uef13", "\uef19"},
      "dropdown_actions_animation",
      new String[]{"\uef14", "\uef1a"}
   );
   protected static final List<String> CURSOR_ACTIONS_EDIT_DROPDOWN_ITEM_IDS = List.of(
      "dropdown_actions_edit_cut", "dropdown_actions_edit_copy", "dropdown_actions_edit_paste"
   );
   protected static final Map<String, String[]> CURSOR_ACTIONS_EDIT_DROPDOWN_ITEM_ICONS = Map.of(
      "dropdown_actions_edit_cut",
      new String[]{"\uef1c", "\uef1d"},
      "dropdown_actions_edit_copy",
      new String[]{"\uee83", "\uee8a"},
      "dropdown_actions_edit_paste",
      new String[]{"\uee84", "\uee8b"}
   );
   protected static final String KEYFRAME_DROPDOWN_ACTION_COPY = "dropdown_keyframe_edit_copy";
   protected static final String KEYFRAME_DROPDOWN_ACTION_PASTE = "dropdown_keyframe_edit_paste";
   protected static final String KEYFRAME_DROPDOWN_ACTION_DELETE = "dropdown_keyframe_delete";
   protected static final String KEYFRAME_DROPDOWN_ACTION_PROPERTIES = "dropdown_keyframe_properties";
   protected static final String KEYFRAME_TIMELINE_DROPDOWN_ACTION_PASTE = "dropdown_keyframe_timeline_paste";
   protected static final List<String> KEYFRAME_DROPDOWN_ITEM_IDS = List.of(
      "dropdown_keyframe_edit_copy", "dropdown_keyframe_edit_paste", "dropdown_keyframe_delete", "dropdown_keyframe_properties"
   );
   protected static final List<String> KEYFRAME_TIMELINE_DROPDOWN_ITEM_IDS = List.of("dropdown_keyframe_timeline_paste");
   protected static final Map<String, String[]> KEYFRAME_DROPDOWN_ITEM_ICONS = Map.of(
      "dropdown_keyframe_edit_copy",
      new String[]{"\uee83", "\uee8a"},
      "dropdown_keyframe_edit_paste",
      new String[]{"\uee84", "\uee8b"},
      "dropdown_keyframe_delete",
      new String[]{"\uef1c", "\uef1d"},
      "dropdown_keyframe_properties",
      new String[]{"\uef1e", "\uef1f"}
   );
   protected static final Map<String, String[]> KEYFRAME_TIMELINE_DROPDOWN_ITEM_ICONS = Map.of(
      "dropdown_keyframe_timeline_paste", new String[]{"\uee84", "\uee8b"}
   );
   protected static final String CURSOR_ACTIONS_DROPDOWN_INACTIVE_COLOR = "0f0f0f";
   protected static final String CURSOR_ACTIONS_DROPDOWN_ACTIVE_COLOR = "141414";
   protected static final String CURSOR_ACTIONS_EDIT_DROPDOWN_INACTIVE_COLOR = "0f0f0f";
   protected static final String CURSOR_ACTIONS_EDIT_DROPDOWN_ACTIVE_COLOR = "141414";
   protected static final String KEYFRAME_DROPDOWN_INACTIVE_COLOR = "0f0f0f";
   protected static final String KEYFRAME_DROPDOWN_ACTIVE_COLOR = "141414";
   protected static final String SAVE_POPUP_ROOT_ID = "popup_save_root";
   protected static final String SAVE_POPUP_NAME_TEXTBOX_ID = "popup_save_textbox_name";
   protected static final String SAVE_POPUP_DESC_TEXTBOX_ID = "popup_save_textbox_desc";
   protected static final String SAVE_POPUP_COMMAND_TEXTBOX_ID = "popup_save_textbox_command";
   protected static final String SAVE_POPUP_NAME_VALUE_ID = "popup_save_name_value";
   protected static final String SAVE_POPUP_DESC_VALUE_ID = "popup_save_desc_value";
   protected static final String SAVE_POPUP_COMMAND_VALUE_ID = "popup_save_command_value";
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
   protected static final String WELCOME_POPUP_CONFIRM_ID = "popup_welcome_confirm";
   protected static final String WELCOME_POPUP_CONFIRM_HITBOX_ID = "popup_welcome_confirm_hitbox";
   protected static final String WELCOME_POPUP_LINK_HITBOX_ID = "popup_welcome_link_hitbox";
   protected static final String WELCOME_POPUP_CONFIRM_ICON_DEFAULT = "\ueea5";
   protected static final String WELCOME_POPUP_CONFIRM_ICON_HOVER = "\ueea8";
   protected static final String SIDEBAR_CONTAINER_ID = "rightsidebar";
   protected static final String SIDEBAR_RIGHT_CONTAINER_ID = "right_sidebar";
   protected static final String SIDEBAR_PROPERTIES_HITBOX_ID = "properties_hitbox";
   protected static final String SIDEBAR_DESIGN_HITBOX_ID = "design_hitbox";
   protected static final String SIDEBAR_LAYERS_HITBOX_ID = "layers_hitbox";
   protected static final String SIDEBAR_LAYERS_HITBOX_LEGACY_ID = "layer_hitbox";
   protected static final String SIDEBAR_PROPERTIES_TEXT_ID = "editor_properties_text";
   protected static final String SIDEBAR_PROPERTIES_TEXT_LEGACY_ID = "properties_text";
   protected static final String SIDEBAR_DESIGN_TEXT_ID = "design_text";
   protected static final String SIDEBAR_LAYERS_TEXT_ID = "layers_text";
   protected static final String SIDEBAR_CURRENT_OPTION_ID = "selected_underline";
   protected static final String SIDEBAR_CURRENT_OPTION_LEGACY_ID = "current_option";
   protected static final String SIDEBAR_PROPERTIES_PANEL_ID = "properties";
   protected static final String SIDEBAR_KEYFRAME_PANEL_ID = "keyframe_properties";
   protected static final String SIDEBAR_KEYFRAME_ROTATION_PANEL_ID = "keyframe_properties_rotation";
   protected static final String SIDEBAR_KEYFRAME_OPACITY_PANEL_ID = "keyframe_properties_opacity";
   protected static final String SIDEBAR_ACTIONS_PANEL_ID = "actions";
   protected static final String SIDEBAR_DESIGN_PANEL_ID = "design";
   protected static final String SIDEBAR_ITEM_DESIGN_PANEL_ID = "item_design";
   protected static final String SIDEBAR_LAYERS_PANEL_ID = "layers";
   protected static final String SIDEBAR_ACTIONS_NEW_BUTTON_ID = "actions_new_button";
   protected static final String SIDEBAR_ACTIONS_NEW_HITBOX_ID = "actions_new_hitbox";
   protected static final String SIDEBAR_ACTIONS_LIST_ID = "actions_list";
   protected static final String SIDEBAR_LAYERS_BOX_ID = "layers_box";
   protected static final String SIDEBAR_WIDTH_BOX_ID = "prop_w_box";
   protected static final String SIDEBAR_HEIGHT_BOX_ID = "prop_h_box";
   protected static final String SIDEBAR_X_BOX_ID = "prop_x_box";
   protected static final String SIDEBAR_Y_BOX_ID = "prop_y_box";
   protected static final String SIDEBAR_ROTATION_BOX_ID = "prop_r_box";
   protected static final String SIDEBAR_OPACITY_BOX_ID = "prop_opacity_box";
   protected static final String SIDEBAR_FLIP_X_BUTTON_ID = "prop_flip_x_btn";
   protected static final String SIDEBAR_FLIP_Y_BUTTON_ID = "prop_flip_y_btn";
   protected static final String SIDEBAR_OPACITY_TRACK_ID = "opacity_slider_track";
   protected static final String SIDEBAR_OPACITY_FILL_ID = "opacity_slider_fill";
   protected static final String SIDEBAR_OPACITY_CIRCLE_ID = "opacity_circle";
   protected static final String SIDEBAR_WIDTH_VALUE_ID = "prop_w_value";
   protected static final String SIDEBAR_HEIGHT_VALUE_ID = "prop_h_value";
   protected static final String SIDEBAR_X_VALUE_ID = "prop_x_value";
   protected static final String SIDEBAR_Y_VALUE_ID = "prop_y_value";
   protected static final String SIDEBAR_ROTATION_VALUE_ID = "prop_r_value";
   protected static final String SIDEBAR_OPACITY_VALUE_ID = "prop_opacity_value";
   protected static final String SIDEBAR_INFO_X_VALUE_ID = "editor_val_x";
   protected static final String SIDEBAR_INFO_Y_VALUE_ID = "editor_val_y";
   protected static final String SIDEBAR_INFO_WIDTH_VALUE_ID = "editor_val_width";
   protected static final String SIDEBAR_INFO_HEIGHT_VALUE_ID = "editor_val_height";
   protected static final String SIDEBAR_INFO_ROTATION_VALUE_ID = "editor_val_rotation";
   protected static final String SIDEBAR_INFO_NAME_VALUE_ID = "editor_val_name";
   protected static final String SIDEBAR_INFO_LAYER_VALUE_ID = "editor_val_layer";
   protected static final String SIDEBAR_INFO_ID_VALUE_ID = "editor_val_id";
   protected static final String SIDEBAR_INFO_VISIBLE_VALUE_ID = "editor_val_visible";
   protected static final String SIDEBAR_INFO_LOCKED_VALUE_ID = "editor_val_locked";
   protected static final String SIDEBAR_INFO_X_HITBOX_ID = "editor_val_x_hitbox";
   protected static final String SIDEBAR_INFO_Y_HITBOX_ID = "editor_val_y_hitbox";
   protected static final String SIDEBAR_INFO_WIDTH_HITBOX_ID = "editor_val_width_hitbox";
   protected static final String SIDEBAR_INFO_HEIGHT_HITBOX_ID = "editor_val_height_hitbox";
   protected static final String SIDEBAR_INFO_ROTATION_HITBOX_ID = "editor_val_rotation_hitbox";
   protected static final String SIDEBAR_INFO_LAYER_HITBOX_ID = "editor_val_layer_hitbox";
   protected static final String SIDEBAR_INFO_NAME_HITBOX_ID = "editor_val_name_hitbox";
   protected static final String SIDEBAR_INFO_ID_HITBOX_ID = "editor_val_id_hitbox";
   protected static final String SIDEBAR_INFO_VISIBLE_HITBOX_ID = "editor_val_visible_hitbox";
   protected static final String SIDEBAR_INFO_LOCKED_HITBOX_ID = "editor_val_locked_hitbox";
   protected static final String SIDEBAR_DESIGN_FILL_COLOR_VALUE_ID = "editor_val_design_fill_color";
   protected static final String SIDEBAR_DESIGN_FILL_STYLE_VALUE_ID = "editor_val_design_fill_style";
   protected static final String SIDEBAR_DESIGN_FILL_OPACITY_VALUE_ID = "editor_val_design_fill_opacity";
   protected static final String SIDEBAR_DESIGN_BORDER_RADIUS_VALUE_ID = "editor_val_design_border_radius";
   protected static final String SIDEBAR_DESIGN_BORDER_COLOR_VALUE_ID = "editor_val_design_border_color";
   protected static final String SIDEBAR_DESIGN_HOVER_COLOR_VALUE_ID = "editor_val_design_hover_color";
   protected static final String SIDEBAR_DESIGN_HOVER_EFFECT_VALUE_ID = "editor_val_design_hover_effect";
   protected static final String SIDEBAR_DESIGN_TEXT_SETTINGS_ROOT_ID = "text_settings";
   protected static final String SIDEBAR_DESIGN_TEXT_ALIGN_ROW_1_ID = "text_align_1";
   protected static final String SIDEBAR_DESIGN_TEXT_ALIGN_ROW_2_ID = "text_align_2";
   protected static final String SIDEBAR_DESIGN_TEXT_ALIGN_ROW_3_ID = "text_align_3";
   protected static final String SIDEBAR_DESIGN_TEXT_ALIGN_VALUE_ID = "editor_val_design_text_align";
   protected static final String SIDEBAR_DESIGN_TEXT_ALIGN_HITBOX_ID = "editor_val_design_text_align_hitbox";
   protected static final String SIDEBAR_DESIGN_TEXT_WRAP_VALUE_ID = "editor_val_design_text_wrapping";
   protected static final String SIDEBAR_DESIGN_TEXT_WRAP_HITBOX_ID = "editor_val_design_text_wrapping_hitbox";
   protected static final String SIDEBAR_ITEM_DESIGN_ITEM_VALUE_ID = "editor_val_item_design_item";
   protected static final String SIDEBAR_ITEM_DESIGN_GLOWING_VALUE_ID = "editor_val_item_design_glowing";
   protected static final String SIDEBAR_ITEM_DESIGN_HOVER_EFFECT_VALUE_ID = "editor_val_item_design_hovereffect";
   protected static final String SIDEBAR_DESIGN_FILL_COLOR_HITBOX_ID = "editor_val_design_fill_color_hitbox";
   protected static final String SIDEBAR_DESIGN_FILL_STYLE_HITBOX_ID = "editor_val_design_fill_style_hitbox";
   protected static final String SIDEBAR_DESIGN_FILL_OPACITY_HITBOX_ID = "editor_val_design_fill_opacity_hitbox";
   protected static final String SIDEBAR_DESIGN_BORDER_RADIUS_HITBOX_ID = "editor_val_design_border_radius_hitbox";
   protected static final String SIDEBAR_DESIGN_BORDER_COLOR_HITBOX_ID = "editor_val_design_border_color_hitbox";
   protected static final String SIDEBAR_DESIGN_HOVER_COLOR_HITBOX_ID = "editor_val_design_hover_color_hitbox";
   protected static final String SIDEBAR_DESIGN_HOVER_EFFECT_HITBOX_ID = "editor_val_design_hover_effect_hitbox";
   protected static final String SIDEBAR_ITEM_DESIGN_ITEM_HITBOX_ID = "editor_val_item_design_item_hitbox";
   protected static final String SIDEBAR_ITEM_DESIGN_GLOWING_HITBOX_ID = "editor_val_item_design_glowing_hitbox";
   protected static final String SIDEBAR_ITEM_DESIGN_HOVER_EFFECT_HITBOX_ID = "editor_val_item_design_hovereffect_hitbox";
   protected static final String SIDEBAR_KEYFRAME_TYPE_ID = "keyframe_properties_type";
   protected static final String SIDEBAR_KEYFRAME_X_LABEL_ID = "keyframe_x_label";
   protected static final String SIDEBAR_KEYFRAME_Y_LABEL_ID = "keyframe_y_label";
   protected static final String SIDEBAR_KEYFRAME_X_VALUE_ID = "editor_val_keyframe_x";
   protected static final String SIDEBAR_KEYFRAME_Y_VALUE_ID = "editor_val_keyframe_y";
   protected static final String SIDEBAR_KEYFRAME_X_VALUE_LEGACY_ID = "keyframe_x";
   protected static final String SIDEBAR_KEYFRAME_Y_VALUE_LEGACY_ID = "keyframe_y";
   protected static final String SIDEBAR_KEYFRAME_X_HITBOX_ID = "editor_val_keyframe_x_hitbox";
   protected static final String SIDEBAR_KEYFRAME_Y_HITBOX_ID = "editor_val_keyframe_y_hitbox";
   protected static final String SIDEBAR_KEYFRAME_X_HITBOX_LEGACY_ID = "keyframe_x_hitbox";
   protected static final String SIDEBAR_KEYFRAME_Y_HITBOX_LEGACY_ID = "keyframe_y_hitbox";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_LABEL_ID = "keyframe_interpolation_label";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_VALUE_ID = "editor_val_keyframe_interpolation";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_VALUE_LEGACY_ID = "keyframe_interpolation";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_HITBOX_ID = "editor_val_keyframe_interpolation_hitbox";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_HITBOX_LEGACY_ID = "keyframe_interpolation_hitbox";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_ROTATION_VALUE_ID = "editor_val_keyframe_interpolation_rotation";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_ROTATION_VALUE_LEGACY_ID = "keyframe_interpolation_rotation";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_ROTATION_HITBOX_ID = "editor_val_keyframe_interpolation_rotation_hitbox";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_ROTATION_HITBOX_LEGACY_ID = "keyframe_interpolation_rotation_hitbox";
   protected static final String SIDEBAR_KEYFRAME_ROTATION_VALUE_ID = "editor_val_keyframe_rotation";
   protected static final String SIDEBAR_KEYFRAME_ROTATION_VALUE_LEGACY_ID = "keyframe_rotation";
   protected static final String SIDEBAR_KEYFRAME_ROTATION_HITBOX_ID = "editor_val_keyframe_rotation_hitbox";
   protected static final String SIDEBAR_KEYFRAME_ROTATION_HITBOX_LEGACY_ID = "keyframe_rotation_hitbox";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_OPACITY_VALUE_ID = "editor_val_keyframe_interpolation_opacity";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_OPACITY_VALUE_LEGACY_ID = "keyframe_interpolation_opacity";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_OPACITY_HITBOX_ID = "editor_val_keyframe_interpolation_opacity_hitbox";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_OPACITY_HITBOX_LEGACY_ID = "keyframe_interpolation_opacity_hitbox";
   protected static final String SIDEBAR_KEYFRAME_OPACITY_VALUE_ID = "editor_val_keyframe_opacity";
   protected static final String SIDEBAR_KEYFRAME_OPACITY_VALUE_LEGACY_ID = "keyframe_opacity";
   protected static final String SIDEBAR_KEYFRAME_OPACITY_HITBOX_ID = "editor_val_keyframe_opacity_hitbox";
   protected static final String SIDEBAR_KEYFRAME_OPACITY_HITBOX_LEGACY_ID = "keyframe_opacity_hitbox";
   protected static final String SIDEBAR_EMPTY_VALUE = "---";
   protected static final String SIDEBAR_PENDING_VALUE = "...";
   protected static final double SIDEBAR_OPACITY_SLIDER_MAX = 135.0;
   protected static final double SIDEBAR_OPACITY_CIRCLE_OFFSET = 8.0;
   protected static final int SIDEBAR_OPACITY_MIN_RAW = 25;
   protected static final int SIDEBAR_OPACITY_MAX_RAW = 255;
   protected static final int ANIMATION_TIMELINE_OPACITY_MIN_RAW = 5;
   protected static final double SIDEBAR_DRAG_DIMENSION_SENSITIVITY = 0.25;
   protected static final double SIDEBAR_DRAG_LAYER_SENSITIVITY = 0.04;
   protected static final double SIDEBAR_DRAG_ROTATION_SENSITIVITY = 0.35;
   protected static final double SIDEBAR_DRAG_OPACITY_PERCENT_SENSITIVITY = 0.2;
   protected static final double SIDEBAR_DRAG_COLOR_HUE_SENSITIVITY = 0.25;
   protected static final double SIDEBAR_DRAG_ACTIVATION_DISTANCE = 1.0;
   protected static final double SIDEBAR_DRAG_COLOR_ACTIVATION_DISTANCE = 6.0;
   protected static final String SIDEBAR_AUTO_VALUE = "Auto";
   protected static final String SIDEBAR_NONE_VALUE = "None";
   protected static final String HOVER_EFFECT_NONE_ID = "none";
   protected static final String HOVER_EFFECT_SCALE_ID = "scale";
   protected static final int HOVER_EFFECT_SCALE_DEFAULT_DURATION_MS = 250;
   protected static final double HOVER_EFFECT_SCALE_DEFAULT_PERCENT = 5.0;
   protected static final String HOVER_EFFECT_INTERPOLATION_EASE_IN_OUT = "ease-in-out";
   protected static final String SIDEBAR_BORDER_RADIUS_SMALL_VALUE = "Small";
   protected static final String SIDEBAR_BORDER_RADIUS_REGULAR_VALUE = "Regular";
   protected static final String SIDEBAR_BORDER_RADIUS_MEDIUM_VALUE = "Medium";
   protected static final String SIDEBAR_BORDER_RADIUS_LARGE_VALUE = "Large";
   protected static final String[] SIDEBAR_ROUNDED_CORNER_KEYS = new String[]{"tl", "tr", "br", "bl"};
   protected static final String[] SIDEBAR_ROUNDED_GLYPHS_SMALL = new String[]{"\uef64", "\uef65", "\uef66", "\uef67"};
   protected static final String[] SIDEBAR_ROUNDED_GLYPHS_REGULAR = new String[]{"\uef60", "\uef61", "\uef62", "\uef63"};
   protected static final String[] SIDEBAR_ROUNDED_GLYPHS_MEDIUM = new String[]{"\uef68", "\uef69", "\uef6a", "\uef6b"};
   protected static final String[] SIDEBAR_ROUNDED_GLYPHS_LARGE = new String[]{"\uef6c", "\uef6d", "\uef6e", "\uef6f"};
   protected static final String SIDEBAR_MIXED_VALUE = "Mixed";
   protected static final String SIDEBAR_ENABLED_VALUE = "Enabled";
   protected static final String SIDEBAR_DISABLED_VALUE = "Disabled";
   protected static final String SIDEBAR_TEXT_ACTIVE = "<#ffffff>";
   protected static final String SIDEBAR_TEXT_INACTIVE = "<#bdbdbd>";
   protected static final String ANIMATION_TIMELINE_PANEL_ID = "animation_timeline";
   protected static final String ANIMATION_TIMELINE_COMPONENT_NAME = "editor_animation_element";
   protected static final String ANIMATION_ELEMENT_NAME_LABEL_ID = "animation_element_name";
   protected static final String ANIMATION_ROW_LABEL_ROTATION_ID = "animation_row_label_rotation";
   protected static final String ANIMATION_ROW_LABEL_POSITION_ID = "animation_row_label_position";
   protected static final String ANIMATION_ROW_LABEL_SCALE_ID = "animation_row_label_scale";
   protected static final String ANIMATION_TIMELINE_TRACK_HITBOX_ID = "animation_slider_hitbox";
   protected static final String ANIMATION_TIMELINE_SLIDER_HITBOX_ID = "animation_accurate_slider_hitbox";
   protected static final String ANIMATION_TIMELINE_SLIDER_ELEMENT_ID = "animation_accurate_slider_element";
   protected static final String ANIMATION_PLUS_ROTATION_HITBOX_ID = "animation_plus_hitbox_rotation";
   protected static final String ANIMATION_PLUS_POSITION_HITBOX_ID = "animation_plus_hitbox_position";
   protected static final String ANIMATION_PLUS_SCALE_HITBOX_ID = "animation_plus_hitbox_scale";
   protected static final String ANIMATION_PLUS_ROW4_HITBOX_ID = "animation_plus_hitbox_row4";
   protected static final String ANIMATION_PLUS_ROW5_HITBOX_ID = "animation_plus_hitbox_row5";
   protected static final String ANIMATION_PLUS_ROW6_HITBOX_ID = "animation_plus_hitbox_row6";
   protected static final String ANIMATION_ROW_ROTATION_BOX_ID = "animation_box_rotation";
   protected static final String ANIMATION_ROW_POSITION_BOX_ID = "animation_box_position";
   protected static final String ANIMATION_ROW_SCALE_BOX_ID = "animation_box_scale";
   protected static final String ANIMATION_ROW_OPACITY_BOX_ID = "animation_box_row4";
   protected static final String ANIMATION_DATA_ROOT_KEY = "editor_animation";
   protected static final String ANIMATION_DATA_KEYFRAMES_KEY = "keyframes";
   protected static final String ANIMATION_KEYFRAME_INTERPOLATION_KEY = "interpolation";
   protected static final String ANIMATION_ROW_ROTATION = "rotation";
   protected static final String ANIMATION_ROW_POSITION = "position";
   protected static final String ANIMATION_ROW_SCALE = "scale";
   protected static final String ANIMATION_ROW_OPACITY = "opacity";
   protected static final String ANIMATION_INTERPOLATION_LINEAR = "linear";
   protected static final String ANIMATION_INTERPOLATION_SMOOTH = "smooth";
   protected static final String ANIMATION_INTERPOLATION_EASE_IN = "ease-in";
   protected static final String ANIMATION_INTERPOLATION_EASE_OUT = "ease-out";
   protected static final String ANIMATION_INTERPOLATION_BEZIER = "bezier";
   protected static final String ANIMATION_INTERPOLATION_BOUNCE = "bounce";
   protected static final String ANIMATION_INTERPOLATION_BOUNCE_IN = "bounce-in";
   protected static final String ANIMATION_INTERPOLATION_BOUNCE_OUT = "bounce-out";
   protected static final String ANIMATION_INTERPOLATION_BACK = "back";
   protected static final String ANIMATION_INTERPOLATION_BACK_IN = "back-in";
   protected static final String ANIMATION_INTERPOLATION_BACK_OUT = "back-out";
   protected static final String ANIMATION_INTERPOLATION_LEGACY_SMOOTH_IN = "smooth-in";
   protected static final String ANIMATION_INTERPOLATION_LEGACY_SMOOTH_OUT = "smooth-out";
   protected static final String ANIMATION_SCALE_OFFSET_X_KEY = "offsetX";
   protected static final String ANIMATION_SCALE_OFFSET_Y_KEY = "offsetY";
   protected static final int ANIMATION_TIMELINE_MAX_SECONDS = 20;
   protected static final int ANIMATION_TIMELINE_TICKS_PER_SECOND = 20;
   protected static final int ANIMATION_TIMELINE_MAX_TICK = 400;
   protected static final double ANIMATION_TIMELINE_PIXELS_PER_SECOND = 61.0;
   protected static final double ANIMATION_TIMELINE_PIXELS_PER_TICK = 3.05;
   protected static final double ANIMATION_TIMELINE_TRACK_TO_SLIDER_X_OFFSET = -2.0;
   protected static final double ANIMATION_TIMELINE_KEYFRAME_X_OFFSET = 50.0;
   protected static final double ANIMATION_TIMELINE_KEYFRAME_Y_OFFSET = 14.0;
   protected static final double ANIMATION_TIMELINE_KEYFRAME_SIZE = 64.0;
   protected static final double ANIMATION_TIMELINE_KEYFRAME_LAYER = 9214.0;
   protected static final double ANIMATION_TIMELINE_KEYFRAME_HITBOX_OFFSET_X = 0.0;
   protected static final double ANIMATION_TIMELINE_KEYFRAME_HITBOX_OFFSET_Y = -8.0;
   protected static final double ANIMATION_TIMELINE_KEYFRAME_HITBOX_WIDTH = 16.0;
   protected static final double ANIMATION_TIMELINE_KEYFRAME_HITBOX_HEIGHT = 16.0;
   protected static final double ANIMATION_TIMELINE_KEYFRAME_DRAG_ACTIVATION_DISTANCE = 2.0;
   protected static final String ANIMATION_TIMELINE_KEYFRAME_RUNTIME_PREFIX = "animation_timeline_keyframe_";
   protected static final String ANIMATION_TIMELINE_KEYFRAME_HITBOX_SUFFIX = "_hitbox";
   protected static final String ANIMATION_TIMELINE_KEYFRAME_GLYPH_LINEAR = "\uef31";
   protected static final String ANIMATION_TIMELINE_KEYFRAME_GLYPH_SMOOTH = "\uef35";
   protected static final String ANIMATION_TIMELINE_KEYFRAME_GLYPH_BEZIER = "\uef36";
   protected static final String ANIMATION_TIMELINE_KEYFRAME_GLYPH = "\uef31";
   protected static final String ANIMATION_TIMELINE_KEYFRAME_HITBOX_GLYPH = "";
   protected static final String ANIMATION_TIMELINE_KEYFRAME_COLOR_DEFAULT = "999999";
   protected static final String ANIMATION_TIMELINE_KEYFRAME_COLOR_SELECTED = "3e90ff";
   protected static final double ANIMATION_TIMELINE_EPSILON = 1.0E-4;
   protected static final Map<String, String> ANIMATION_ROW_TO_BOX_ID = Map.of(
      "rotation", "animation_box_rotation", "position", "animation_box_position", "scale", "animation_box_scale", "opacity", "animation_box_row4"
   );
   protected static final String SIDEBAR_PROPERTIES_ICON = "\ue600";
   protected static final String SIDEBAR_KEYFRAME_ICON = "\ue60a";
   protected static final String SIDEBAR_ACTIONS_ICON = "\ueef0";
   protected static final String SIDEBAR_KEYFRAME_TYPE_ROTATION_ICON = "\ue641";
   protected static final String SIDEBAR_KEYFRAME_TYPE_POSITION_ICON = "\ue63e";
   protected static final String SIDEBAR_KEYFRAME_TYPE_SCALE_ICON = "\ue640";
   protected static final String SIDEBAR_KEYFRAME_TYPE_OPACITY_ICON = "\ue642";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_LINEAR = "Linear";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_SMOOTH = "Smooth";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_EASE_IN = "Ease-In";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_EASE_OUT = "Ease-Out";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_BEZIER = "Bezier";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_BOUNCE = "Bounce";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_BOUNCE_IN = "Bounce-In";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_BOUNCE_OUT = "Bounce-Out";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_BACK = "Back";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_BACK_IN = "Back-In";
   protected static final String SIDEBAR_KEYFRAME_INTERPOLATION_BACK_OUT = "Back-Out";
   protected static final double SIDEBAR_OPTION_PROPERTIES_X = 45.0;
   protected static final double SIDEBAR_OPTION_DESIGN_X = 149.0;
   protected static final double SIDEBAR_OPTION_LAYERS_X = 240.0;
   protected static final double SIDEBAR_OPTION_Y = 45.0;
   protected static final double SIDEBAR_OPTION_LEGACY_PROPERTIES_X = 1678.0;
   protected static final double SIDEBAR_OPTION_LEGACY_LAYERS_X = 1800.0;
   protected static final double SIDEBAR_OPTION_LEGACY_Y = 140.0;
   protected static final int SIDEBAR_TAB_ANIMATION_STEPS = 6;
   protected static final int LAYERS_VISIBLE_ROW_COUNT = 17;
   protected static final double LAYERS_ROW_START_X_OFFSET = 24.0;
   protected static final double LAYERS_ROW_START_Y_OFFSET = 130.0;
   protected static final double LAYERS_ROW_WIDTH = 289.0;
   protected static final double LAYERS_ROW_HEIGHT = 45.0;
   protected static final double LAYERS_DYNAMIC_GRID_GAP = -51.0;
   protected static final String LAYERS_RUNTIME_ID_PREFIX = "layers_runtime_";
   protected static final String LAYERS_SLOT_ID_PREFIX = "layer_slot_";
   protected static final String LAYERS_ROW_ID_SUFFIX = "_row";
   protected static final String LAYERS_NAME_ID_SUFFIX = "_name";
   protected static final String LAYERS_DROPDOWN_HITBOX_SUFFIX = "_dropdownhitbox";
   protected static final String LAYERS_DROPDOWN_ICON_SUFFIX = "_dropdown_icon";
   protected static final String LAYERS_STATIC_BOX1_SUFFIX = "_box1";
   protected static final String LAYERS_STATIC_BOX2_SUFFIX = "_box2";
   protected static final String LAYERS_STATIC_OPACITY_SUFFIX = "_opacity";
   protected static final String LAYERS_STATIC_OPACITY_HITBOX_SUFFIX = "_opacityhitbox";
   protected static final String LAYERS_STATIC_DIVIDER_SUFFIX = "_divider";
   protected static final String LAYERS_STATIC_HITBOX_SUFFIX = "_hitbox";
   protected static final String LAYERS_SCROLL_TOP_ID = "scroll_top";
   protected static final String LAYERS_SCROLL_BOTTOM_ID = "scroll_bottom";
   protected static final int LAYERS_SCROLL_EDGE_FLASH_TICKS = 3;
   protected static final double LAYERS_REORDER_DRAG_THRESHOLD_PX = 5.0;
   protected static final double LAYERS_REORDER_EDGE_ZONE_PX = 18.0;
   protected static final int LAYERS_REORDER_AUTOSCROLL_PERIOD_TICKS = 4;
   protected static final String LAYERS_REORDER_GHOST_RUNTIME_PREFIX = "layers_drag_runtime_";
   protected static final String LAYERS_REORDER_GHOST_SLOT_BASE = "layer_drag_slot";
   protected static final double ACTIONS_ROW_START_X_OFFSET = 24.0;
   protected static final double ACTIONS_ROW_START_Y_OFFSET = 130.0;
   protected static final double ACTIONS_DYNAMIC_GRID_GAP = -51.0;
   protected static final double ACTIONS_REORDER_DRAG_THRESHOLD_PX = 5.0;
   protected static final double ACTIONS_REORDER_DRAG_VERTICAL_THRESHOLD_PX = 4.0;
   protected static final double ACTIONS_REORDER_EDIT_MOVEMENT_TOLERANCE_PX = 2.5;
   protected static final long ACTIONS_REORDER_EDIT_MIN_SECOND_CLICK_TICKS = 2L;
   protected static final long ACTIONS_REORDER_EDIT_DOUBLE_CLICK_TICKS = 10L;
   protected static final String ACTIONS_REORDER_GHOST_RUNTIME_PREFIX = "actions_drag_runtime_";
   protected static final String ACTIONS_REORDER_GHOST_SLOT_BASE = "action_drag_slot";
   protected static final double CURSOR_ACTIONS_DROPDOWN_WALL_MARGIN = 11.0;
   protected static final double CURSOR_ACTIONS_EDIT_DROPDOWN_WALL_MARGIN = 11.0;
   protected static final double ANIMATION_KEYFRAME_DROPDOWN_WALL_MARGIN = 11.0;
   protected static final String ACTION_TYPE_COMMAND = "command";
   protected static final String ACTION_TYPE_CONSOLE = "console";
   protected static final String ACTION_TYPE_MESSAGE = "message";
   protected static final String ACTION_TYPE_REDIRECT = "redirect";
   protected static final String ACTION_TYPE_CLOSE = "close";
   protected static final String ACTION_TYPE_ANIMATION = "animation";
   protected static final String ACTION_TYPE_DELAY = "delay";
   protected static final String ACTION_TYPE_SOUND = "sound";
   protected static final String ACTION_TYPE_TELEPORT = "teleport";
   protected static final Map<String, String> ACTION_TYPE_ICONS = Map.of(
      "command",
      "\uef00",
      "console",
      "\uef40",
      "message",
      "\uef01",
      "redirect",
      "\uef02",
      "close",
      "\uef03",
      "animation",
      "\uef04",
      "delay",
      "\uef05",
      "sound",
      "\uef0c",
      "teleport",
      "\uef0e"
   );
   protected static final Map<String, String> ACTION_TYPE_ACTIVE_ICONS = Map.of(
      "command",
      "\uef06",
      "console",
      "\uef41",
      "message",
      "\uef07",
      "redirect",
      "\uef08",
      "close",
      "\uef09",
      "animation",
      "\uef0a",
      "delay",
      "\uef0b",
      "sound",
      "\uef0d",
      "teleport",
      "\uef0f"
   );
   protected static final String ACTION_DEFAULT_VALUE = "---";
   protected static final String ACTION_DEFAULT_CONSOLE_VALUE = "say Hello";
   protected static final String ACTION_DEFAULT_CLOSE_VALUE = "Close";
   protected static final String ACTION_DEFAULT_DELAY_VALUE = "1000ms";
   protected static final String ACTION_DEFAULT_SOUND_VALUE = "ENTITY_PLAYER_LEVELUP 1,2";
   protected static final String ACTION_DEFAULT_TELEPORT_VALUE = "0 64 0";
   protected static final int LAYER_TEXT_LABEL_MAX_CHARS = 26;
   protected static final int LAYER_CHILD_LABEL_DEPTH_PENALTY_CHARS = 3;
   protected static final int SIDEBAR_VALUE_MAX_LENGTH = 18;
   protected static final int SIDEBAR_VALUE_VISIBLE_PREFIX_LENGTH = 17;
   protected static final String SHELL_SPAWN_OPACITY_TARGET_PREFIX = "__shell__.";
   protected static final Map<String, String[]> MAP_PATH_SEGMENTS_CACHE = new ConcurrentHashMap<>();
   protected static final String COLOR_PICKER_UI_ID = "colorpicker_ui";
   protected static final String COLOR_PICKER_GRAD_ID = "colorpicker_colorchange1";
   protected static final String COLOR_PICKER_GRAD_CORE_ID = "colorpicker_colorchange1_core";
   protected static final String COLOR_PICKER_GRAD_HITBOX_ID = "colorpicker_colorchange1_hitbox";
   protected static final String COLOR_PICKER_HUE_ID = "colorpicker_colorchange2";
   protected static final String COLOR_PICKER_HUE_HITBOX_ID = "colorpicker_colorchange2_hitbox";
   protected static final String COLOR_PICKER_MAIN_COLOR_ID = "colorpicker_maincolor";
   protected static final String COLOR_PICKER_HEX_VALUE_ID = "colorpicker_hexcolor";
   protected static final String COLOR_PICKER_HEX_BOX_ID = "colorpicker_hexcolor_box";
   protected static final String COLOR_PICKER_DISPLAY_ID = "colorpicker_colordisplay";
   protected static final String COLOR_SELECTOR_COLOR_PICKER_ID = "colorselector_colorpicker";
   protected static final String EDITOR_COLOR_1_ID = "editor_color_1";
   protected static final String EDITOR_COLOR_2_ID = "editor_color_2";
   protected static final String EDITOR_COLOR_1_HITBOX_ID = "editor_color_1_hitbox";
   protected static final String EDITOR_COLOR_2_HITBOX_ID = "editor_color_2_hitbox";
   protected static final String EDITOR_COLOR_SWITCH_HITBOX_ID = "editor_color_switch_hitbox";
   protected static final double CP_GRAD_ABS_X_MIN = 106.0;
   protected static final double CP_GRAD_ABS_X_MAX = 304.0;
   protected static final double CP_GRAD_ABS_Y_MIN = 651.0;
   protected static final double CP_GRAD_ABS_Y_MAX = 907.0;
   protected static final double CP_HUE_ABS_X = 325.0;
   protected static final double CP_HUE_ABS_Y_MIN = 647.0;
   protected static final double CP_HUE_ABS_Y_MAX = 899.0;
   protected static final double CP_HUE_CLICK_X_MIN = 325.0;
   protected static final double CP_HUE_CLICK_X_MAX = 389.0;
   protected static final double CP_GRAD_HITBOX_OFFSET_Y = -11.0;
   protected static final double CP_HUE_HITBOX_OFFSET_Y = -3.0;
   protected static final double CP_GRAD_VISUAL_OFFSET_X = -12.0;
   protected static final double CP_GRAD_VISUAL_OFFSET_Y = 3.0;
   protected static final double CP_HUE_VISUAL_OFFSET_Y = -4.0;
   protected static final double CP_GRAD_HITBOX_W = 19.0;
   protected static final double CP_GRAD_HITBOX_H = 19.0;
   protected static final double CP_HUE_HITBOX_W = 14.0;
   protected static final double CP_HUE_HITBOX_H = 16.0;
   protected static final double CP_INDICATOR_SIZE = 64.0;

   protected PropertiesSidebarTimelineManager(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   protected abstract boolean isValidActionsSecondClickEditAttempt(EditorSession var1, double var2, double var4, long var6);

   protected abstract boolean hasActionsEditMovementExceeded(EditorSession var1, int var2, double var3, double var5);

   protected abstract boolean isActionsReorderDragStartSatisfied(EditorSession var1, double var2, double var4);

   protected abstract void resetActionsEditClickTracker(EditorSession var1);

   protected abstract boolean handleCursorActionsDropdownAction(Player var1, EditorSession var2, String var3);

   protected abstract boolean handleCursorActionsEditDropdownAction(Player var1, EditorSession var2, String var3);

   protected abstract boolean handleActionsEditCopy(EditorSession var1);

   protected abstract boolean handleActionsEditCut(Player var1, EditorSession var2);

   protected abstract boolean handleActionsEditPaste(Player var1, EditorSession var2);

   protected abstract int resolveSelectedActionRawIndex(EditorSession var1, List<Map<String, Object>> var2);

   protected abstract String resolveCursorActionsTypeByDropdownId(String var1);

   protected abstract String resolveSelectedActionsTargetId(EditorSession var1);

   protected abstract List<Map<String, Object>> resolveTargetActionsRawList(EditorSession var1, String var2, boolean var3);

   protected abstract void normalizeActionMapForEditor(Map<String, Object> var1);

   protected abstract Map<String, Object> buildDefaultActionMap(String var1);

   protected abstract String normalizeActionType(String var1);

   protected abstract String resolveDefaultActionValue(String var1);

   protected abstract String resolveActionDisplayValue(Map<String, Object> var1, String var2);

   protected abstract boolean isEditableActionType(String var1);

   protected abstract void startSidebarActionInputPrompt(Player var1, EditorSession var2, String var3, int var4, String var5, String var6);

   protected abstract void armActionsReorder(EditorSession var1, int var2, double var3, double var5);

   protected abstract void clearActionsReorderState(EditorSession var1);

   protected abstract boolean isActionsReorderLeftHeld(EditorSession var1);

   protected abstract boolean isActionsReorderActive(EditorSession var1);

   protected abstract boolean handleActionsReorderCursorMove(Player var1, EditorSession var2, double var3, double var5);

   protected abstract void moveActionInRawList(List<Map<String, Object>> var1, int var2, int var3);

   protected abstract boolean handleActionsPanelClick(Player var1, EditorSession var2, double var3, double var5);

   protected abstract boolean handleActionsPanelRightClick(Player var1, EditorSession var2, double var3, double var5);

   protected abstract boolean handleActionsScroll(Player var1, EditorSession var2, double var3, double var5, int var7);

   protected abstract void flashActionsScrollEdge(Player var1, EditorSession var2, boolean var3);

   @Override
   protected abstract boolean isActionsSidebarMode(EditorSession var1);

   protected abstract boolean isActionsShellBlockId(String var1);

   protected abstract boolean isCursorActionsCachedPanelBlock(EditorSession var1, ConfigurationSection var2);

   @Override
   protected abstract void setCursorActionsEditDropdownVisible(Player var1, EditorSession var2, boolean var3);

   protected abstract void updateCursorActionsEditDropdownHover(Player var1, EditorSession var2, double var3, double var5);

   protected abstract void setCursorActionsEditDropdownItemVisual(Player var1, EditorSession var2, String var3, boolean var4);

   protected abstract String resolveHoveredCursorActionsEditDropdownTargetId(EditorSession var1, double var2, double var4);

   protected abstract void moveCursorActionsEditDropdownTo(Player var1, EditorSession var2, double var3, double var5);

   protected abstract void moveCursorActionsDropdownTo(Player var1, EditorSession var2, double var3, double var5);

   @Override
   protected abstract void setCursorActionsDropdownVisible(Player var1, EditorSession var2, boolean var3);

   protected abstract void updateCursorActionsDropdownHover(Player var1, EditorSession var2, double var3, double var5);

   protected abstract void setCursorActionsDropdownItemVisual(Player var1, EditorSession var2, String var3, boolean var4);

   protected abstract String resolveHoveredCursorActionsDropdownTargetId(EditorSession var1, double var2, double var4);

   protected abstract void renderActionsPanel(Player var1, EditorSession var2);

   protected abstract void setActionsNewButtonVisible(Player var1, EditorSession var2, boolean var3);

   protected abstract boolean isActionsPanelRuntimeAvailable(EditorSession var1);

   protected abstract void renderActionsRuntimeRows(Player var1, EditorSession var2, List<PropertiesSidebarTimelineManager.ActionRowEntry> var3);

   protected abstract List<Map<String, Object>> buildActionsRuntimeGridBlocks(EditorSession var1, List<PropertiesSidebarTimelineManager.ActionRowEntry> var2);

   protected abstract double resolveActionsGridStartX(EditorSession var1);

   protected abstract double resolveActionsGridStartY(EditorSession var1);

   protected abstract double resolveActionsGridGap(EditorSession var1);

   protected abstract Double resolveActionsGridElementHeight(EditorSession var1);

   protected abstract Map<String, Object> findActionsGridBlockMap(EditorSession var1);

   protected abstract EditorRect resolveActionsListRect(EditorSession var1, int var2);

   protected abstract int resolveActionSlotByHit(EditorSession var1, double var2, double var4, int var6);

   protected abstract double resolveActionsRowStep(EditorSession var1);

   protected abstract void clearStaleActionRuntimeShellIds(Player var1, EditorSession var2, LinkedHashSet<String> var3);

   protected abstract void removeActionRuntimeShellElement(Player var1, String var2);

   protected abstract void clearActionsRuntimeHud(Player var1);

   protected abstract void clearActionsRuntimeHud(Player var1, EditorSession var2);

   protected abstract void renderActionsReorderGhostHud(Player var1, EditorSession var2, double var3, double var5);

   protected abstract List<Map<String, Object>> buildActionsReorderGhostRuntimeBlocks(
      EditorSession var1, PropertiesSidebarTimelineManager.ActionRowEntry var2, double var3, double var5
   );

   protected abstract void clearActionsReorderGhostHud(Player var1);

   protected abstract void clearActionsReorderGhostHud(Player var1, EditorSession var2);

   protected abstract void clearStaleActionsReorderGhostShellIds(Player var1, EditorSession var2, LinkedHashSet<String> var3);

   protected abstract String actionRuntimeLogicalId(String var1);

   protected abstract String actionSlotBaseId(int var1);

   protected abstract String actionSlotId(int var1);

   protected abstract String actionNameId(int var1);

   protected abstract String actionIconId(int var1);

   protected abstract String resolveDesignTextSettingsInvocationPath(EditorSession var1);

   protected abstract boolean isSidebarItemDesignTarget(EditorSession var1, String var2);

   protected abstract void syncDesignTextAlignControlsVisibility(Player var1, EditorSession var2, boolean var3);

   @Override
   protected abstract void toggleSidebarItemDesignGlowing(Player var1, EditorSession var2);

   @Override
   protected abstract void cycleSidebarHoverEffect(Player var1, EditorSession var2, boolean var3);

   @Override
   protected abstract void toggleSidebarTextAlignment(Player var1, EditorSession var2);

   @Override
   protected abstract boolean isTextDesignSidebarMode(EditorSession var1);

   @Override
   protected abstract void cycleSidebarBorderRadiusMode(Player var1, EditorSession var2);

   @Override
   protected abstract void updateEditorPropertiesSidebar(Player var1, EditorSession var2);

   @Override
   protected abstract void updateEditorKeyframeSidebar(Player var1, EditorSession var2);

   @Override
   protected abstract void updateEditorDesignSidebar(Player var1, EditorSession var2);

   protected boolean handleSidebarClick(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 == null || var2 == null || !var2.editMode || !var2.previewMode) {
         return false;
      } else if (!this.hasLegacySidebarUi(var2)) {
         return false;
      } else {
         if (var2.colorPickerVisible && !this.isInsideShellBlock(var2, "colorpicker_ui", var3, var5)) {
            this.closeColorPicker(var1, var2);
         }

         if (this.handleAnimationTimelineClick(var1, var2, var3, var5)) {
            return true;
         } else if (this.isInsideShellBlock(var2, "editor_color_1_hitbox", var3, var5)) {
            this.openColorPickerForSlot(var1, var2, 1);
            return true;
         } else if (this.isInsideShellBlock(var2, "editor_color_2_hitbox", var3, var5)) {
            this.openColorPickerForSlot(var1, var2, 2);
            return true;
         } else if (this.isInsideShellBlock(var2, "editor_color_switch_hitbox", var3, var5)) {
            String var15 = var2.editorColor1;
            var2.editorColor1 = var2.editorColor2;
            var2.editorColor2 = var15;
            this.setShellColor(var1, var2, "editor_color_1", var2.editorColor1);
            this.setShellColor(var1, var2, "editor_color_2", var2.editorColor2);
            this.notifyColorPickerColorsChanged(var1, var2);
            this.playEditorSfx(var1, var2, "colors-swapped");
            return true;
         } else if (!var2.colorPickerVisible) {
            EditorPropertyField var14 = this.resolveSidebarValueHitboxField(var2, var3, var5);
            if (var14 != null) {
               if (var14 == EditorPropertyField.VISIBLE) {
                  this.toggleSidebarSelectionVisibility(var1, var2);
                  return true;
               } else if (var14 == EditorPropertyField.LOCKED) {
                  this.toggleSidebarSelectionLocked(var1, var2);
                  return true;
               } else if (var14 == EditorPropertyField.ANCHOR) {
                  this.cycleSidebarSelectionAnchor(var1, var2);
                  return true;
               } else if (var14 == EditorPropertyField.ITEM_DESIGN_GLOWING) {
                  this.toggleSidebarItemDesignGlowing(var1, var2);
                  return true;
               } else {
                  if (this.isDimensionSidebarField(var14)) {
                     this.startSidebarFieldDrag(var1, var2, var14, var3);
                  } else {
                     this.startSidebarInputPrompt(var1, var2, var14);
                  }

                  return true;
               }
            } else if (this.handleKeyframeInterpolationSidebarClick(var1, var2, var3, var5)) {
               return true;
            } else {
               boolean var8 = this.isInsideShellBlock(var2, "properties_hitbox", var3, var5);
               boolean var9 = this.isInsideShellBlock(var2, "design_hitbox", var3, var5);
               boolean var10 = this.isInsideSidebarLayersHitbox(var2, var3, var5);
               if (var2.rightSidebarTab == EditorSidebarTab.PROPERTIES
                  && this.isActionsSidebarMode(var2)
                  && !var8
                  && !var9
                  && !var10
                  && this.handleActionsPanelClick(var1, var2, var3, var5)) {
                  return true;
               } else if (var8) {
                  this.finishLayersReorderDrag(var1, var2, false);
                  this.setRightSidebarTab(var1, var2, EditorSidebarTab.PROPERTIES, true);
                  return true;
               } else if (var9) {
                  this.finishLayersReorderDrag(var1, var2, false);
                  this.setRightSidebarTab(var1, var2, EditorSidebarTab.DESIGN, true);
                  return true;
               } else if (var10) {
                  this.setRightSidebarTab(var1, var2, EditorSidebarTab.LAYERS, true);
                  return true;
               } else if (var2.rightSidebarTab == EditorSidebarTab.LAYERS) {
                  return this.handleLayersPanelClick(var1, var2, var3, var5);
               } else if (var2.rightSidebarTab == EditorSidebarTab.PROPERTIES && this.isActionsSidebarMode(var2)) {
                  return this.handleActionsPanelClick(var1, var2, var3, var5);
               } else if (var2.rightSidebarTab == EditorSidebarTab.DESIGN) {
                  if (this.isItemDesignSidebarMode(var2)) {
                     if (this.isInsideShellBlock(var2, "editor_val_item_design_item", var3, var5)
                        || this.isInsideShellBlock(var2, "editor_val_item_design_item_hitbox", var3, var5)) {
                        this.startSidebarInputPrompt(var1, var2, EditorPropertyField.ITEM_DESIGN_ITEM);
                        return true;
                     } else if (this.isInsideShellBlock(var2, "editor_val_item_design_glowing", var3, var5)
                        || this.isInsideShellBlock(var2, "editor_val_item_design_glowing_hitbox", var3, var5)) {
                        this.toggleSidebarItemDesignGlowing(var1, var2);
                        return true;
                     } else if (!this.isInsideShellBlock(var2, "editor_val_item_design_hovereffect", var3, var5)
                        && !this.isInsideShellBlock(var2, "editor_val_item_design_hovereffect_hitbox", var3, var5)) {
                        return false;
                     } else {
                        this.cycleSidebarHoverEffect(var1, var2, true);
                        return true;
                     }
                  } else if (!this.isTextDesignSidebarMode(var2)
                     || !this.isInsideShellBlock(var2, "text_align_1", var3, var5)
                        && !this.isInsideShellBlock(var2, "editor_val_design_text_align_hitbox", var3, var5)) {
                     if (!this.isTextDesignSidebarMode(var2)
                        || !this.isInsideShellBlock(var2, "text_align_2", var3, var5)
                           && !this.isInsideShellBlock(var2, "editor_val_design_text_wrapping", var3, var5)
                           && !this.isInsideShellBlock(var2, "editor_val_design_text_wrapping_hitbox", var3, var5)) {
                        if (this.isInsideShellBlock(var2, "editor_val_design_fill_style_hitbox", var3, var5)
                           || this.isInsideShellBlock(var2, "editor_val_design_fill_style", var3, var5)) {
                           return false;
                        } else if (this.isInsideShellBlock(var2, "editor_val_design_border_radius_hitbox", var3, var5)
                           || this.isInsideShellBlock(var2, "editor_val_design_border_radius", var3, var5)) {
                           this.cycleSidebarBorderRadiusMode(var1, var2);
                           return true;
                        } else if (this.isInsideShellBlock(var2, "editor_val_design_hover_effect_hitbox", var3, var5)
                           || this.isInsideShellBlock(var2, "editor_val_design_hover_effect", var3, var5)) {
                           this.cycleSidebarHoverEffect(var1, var2, false);
                           return true;
                        } else if (this.isInsideShellBlock(var2, "editor_val_design_hover_color_hitbox", var3, var5)
                           || this.isInsideShellBlock(var2, "editor_val_design_hover_color", var3, var5)) {
                           this.startSidebarInputPrompt(var1, var2, EditorPropertyField.DESIGN_HOVER_COLOR);
                           return true;
                        } else if (this.isInsideShellBlock(var2, "editor_val_design_fill_color", var3, var5)) {
                           this.startSidebarFieldDrag(var1, var2, EditorPropertyField.DESIGN_COLOR, var3);
                           return true;
                        } else if (this.isInsideShellBlock(var2, "editor_val_design_fill_opacity", var3, var5)) {
                           this.startSidebarFieldDrag(var1, var2, EditorPropertyField.OPACITY, var3);
                           return true;
                        } else if (this.isInsideShellBlock(var2, "editor_val_design_border_color", var3, var5)) {
                           this.startSidebarFieldDrag(var1, var2, EditorPropertyField.DESIGN_BORDER_COLOR, var3);
                           return true;
                        } else {
                           return false;
                        }
                     } else {
                        this.startSidebarFieldDrag(var1, var2, EditorPropertyField.DESIGN_TEXT_WRAP, var3);
                        return true;
                     }
                  } else {
                     this.toggleSidebarTextAlignment(var1, var2);
                     return true;
                  }
               } else if (var2.rightSidebarTab != EditorSidebarTab.PROPERTIES) {
                  return false;
               } else if (this.isInsideShellBlock(var2, "prop_w_box", var3, var5)) {
                  this.startSidebarFieldDrag(var1, var2, EditorPropertyField.WIDTH, var3);
                  return true;
               } else if (this.isInsideShellBlock(var2, "prop_h_box", var3, var5)) {
                  this.startSidebarFieldDrag(var1, var2, EditorPropertyField.HEIGHT, var3);
                  return true;
               } else if (this.isInsideShellBlock(var2, "prop_x_box", var3, var5)) {
                  this.startSidebarFieldDrag(var1, var2, EditorPropertyField.POSITION_X, var3);
                  return true;
               } else if (this.isInsideShellBlock(var2, "prop_y_box", var3, var5)) {
                  this.startSidebarFieldDrag(var1, var2, EditorPropertyField.POSITION_Y, var3);
                  return true;
               } else if (this.isInsideShellBlock(var2, "prop_r_box", var3, var5)) {
                  this.startSidebarFieldDrag(var1, var2, EditorPropertyField.ROTATION, var3);
                  return true;
               } else if (this.isInsideShellBlock(var2, "prop_opacity_box", var3, var5)) {
                  this.startSidebarFieldDrag(var1, var2, EditorPropertyField.OPACITY, var3);
                  return true;
               } else if (this.isInsideShellBlock(var2, "prop_flip_x_btn", var3, var5)) {
                  String var16 = this.getSidebarEditableTargetId(var2);
                  EditorRect var17 = var16 == null ? null : this.getTargetBounds(var2, var16);
                  EditorRect var18 = this.resolveFlippedBounds(var2, var17, true);
                  if (this.applyFlipSelection(var2, true)) {
                     this.applySidebarFlipVisualMove(var1, var2, var16, var17, var18);
                  }

                  this.updateEditorPropertiesSidebar(var1, var2);
                  return true;
               } else if (this.isInsideShellBlock(var2, "prop_flip_y_btn", var3, var5)) {
                  String var11 = this.getSidebarEditableTargetId(var2);
                  EditorRect var12 = var11 == null ? null : this.getTargetBounds(var2, var11);
                  EditorRect var13 = this.resolveFlippedBounds(var2, var12, false);
                  if (this.applyFlipSelection(var2, false)) {
                     this.applySidebarFlipVisualMove(var1, var2, var11, var12, var13);
                  }

                  this.updateEditorPropertiesSidebar(var1, var2);
                  return true;
               } else if (!this.isInsideShellBlock(var2, "opacity_slider_track", var3, var5) && !this.isInsideShellBlock(var2, "opacity_circle", var3, var5)) {
                  return false;
               } else {
                  if (this.getSidebarEditableTargetId(var2) != null) {
                     var2.pendingPropertyField = null;
                     this.stopOpacitySliderDrag(var2, true);
                     this.resetSidebarFieldDrag(var1, var2);
                     var2.opacitySliderDragActive = true;
                     var2.opacitySliderLastValue = null;
                     var2.sidebarOpacityRefreshTriggered = false;
                     this.beginOpacitySliderDragHistory(var2);
                     this.updateOpacitySliderDrag(var1, var2, var3);
                  }

                  return true;
               }
            }
         } else if (this.isInsideShellBlock(var2, "colorselector_colorpicker", var3, var5)) {
            this.setActiveEditorTool(var1, var2, EditorTool.COLOR_PICKER);
            return true;
         } else if (this.isInsideShellBlock(var2, "colorpicker_hexcolor_box", var3, var5)) {
            var2.pendingActionTargetId = null;
            var2.pendingActionIndex = -1;
            var2.colorPickerHexInputPending = true;
            String var7 = var2.colorPickerSlot == 1 ? var2.editorColor1 : var2.editorColor2;
            this.updateColorPickerDisplay(var1, var2, var7);
            this.sendEditorPlayerMessage(var1, MM.deserialize("<#84a0c8>Hex color <gray>- type a HEX color in chat or <white>cancel</white>.</gray>"));
            return true;
         } else if (this.isInsideShellBlock(var2, "colorpicker_colorchange1_hitbox", var3, var5) || this.isInsideColorPickerGradientArea(var3, var5)) {
            var2.colorPickerGradDragActive = true;
            this.updateColorPickerGradientDrag(var1, var2, var3, var5);
            return true;
         } else if (!this.isInsideShellBlock(var2, "colorpicker_colorchange2_hitbox", var3, var5) && !this.isInsideColorPickerHueArea(var3, var5)) {
            return true;
         } else {
            var2.colorPickerHueDragActive = true;
            this.updateColorPickerHueDrag(var1, var2, var3, var5);
            return true;
         }
      }
   }

   @Override
   protected boolean isInsideShellBlock(EditorSession var1, String var2, double var3, double var5) {
      EditorRect var7 = this.findShellBlockRect(var1, var2);
      return var7 == null ? false : var3 >= var7.x && var3 <= var7.maxX() && var5 >= var7.y && var5 <= var7.maxY();
   }

   protected boolean isInsideEditorChromeBlock(EditorSession var1, double var2, double var4) {
      return var1 == null
         ? false
         : this.isInsideShellBlock(var1, "navbar", var2, var4)
            || this.isInsideShellBlock(var1, "left_sidebar", var2, var4)
            || this.isInsideShellBlock(var1, "leftsidebar", var2, var4)
            || this.isInsideShellBlock(var1, "rightsidebar", var2, var4)
            || this.isInsideShellBlock(var1, "right_sidebar", var2, var4)
            || this.isInsideShellBlock(var1, "properties_hitbox", var2, var4)
            || this.isInsideShellBlock(var1, "design_hitbox", var2, var4)
            || this.isInsideShellBlock(var1, "layers_hitbox", var2, var4)
            || this.isInsideShellBlock(var1, "layer_hitbox", var2, var4)
            || this.isInsideShellBlock(var1, "properties", var2, var4)
            || this.isInsideShellBlock(var1, "actions", var2, var4)
            || this.isInsideShellBlock(var1, "design", var2, var4)
            || this.isInsideShellBlock(var1, "item_design", var2, var4)
            || this.isInsideShellBlock(var1, "layers", var2, var4)
            || var1.fileDropdownVisible && this.isInsideShellBlock(var1, "file_dropdown", var2, var4)
            || var1.editDropdownVisible && this.isInsideShellBlock(var1, "edit_dropdown", var2, var4)
            || var1.selectionDropdownVisible && this.isInsideShellBlock(var1, "selection_dropdown", var2, var4)
            || var1.layerDropdownVisible && this.isInsideShellBlock(var1, "layer_dropdown", var2, var4)
            || var1.windowDropdownVisible && this.isInsideShellBlock(var1, "window_dropdown", var2, var4)
            || var1.keyframeDropdownVisible && this.isInsideShellBlock(var1, "keyframe_dropdown", var2, var4)
            || var1.keyframeTimelineDropdownVisible && this.isInsideShellBlock(var1, "keyframe_timeline_dropdown", var2, var4)
            || var1.cursorPageDropdownVisible && this.isInsideShellBlock(var1, "cursor_page_dropdown", var2, var4)
            || var1.cursorLayersDropdownVisible && this.isInsideShellBlock(var1, "cursor_layers_dropdown", var2, var4)
            || var1.cursorActionsDropdownVisible && this.isInsideShellBlock(var1, "cursor_actions_dropdown", var2, var4)
            || var1.cursorActionsEditDropdownVisible && this.isInsideShellBlock(var1, "cursor_actions_edit_dropdown", var2, var4)
            || var1.colorPickerVisible && this.isInsideShellBlock(var1, "colorpicker_ui", var2, var4)
            || var1.welcomePopupVisible && this.isInsideShellBlock(var1, "popup_welcome_root", var2, var4)
            || var1.savePopupVisible && this.isInsideShellBlock(var1, "popup_save_root", var2, var4)
            || var1.preferencesPopupVisible && this.isInsideShellBlock(var1, "popup_preferences_root", var2, var4)
            || var1.activeTool == EditorTool.ANIMATION && this.isInsideShellBlock(var1, "animation_timeline", var2, var4)
            || this.isInsideShellBlock(var1, "footer", var2, var4);
   }

   protected String shellSpawnOpacityRetoggleTargetId(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).toLowerCase(Locale.ROOT);
      return var2.isBlank() ? "" : "__shell__." + var2;
   }

   protected boolean isShellSpawnOpacityRetoggleTargetId(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).toLowerCase(Locale.ROOT);
      return !var2.isBlank() && var2.startsWith("__shell__.");
   }

   @Override
   protected boolean shouldSkipEditorShellBlockForSpawnRetoggle(EditorSession var1, String var2) {
      return var1 == null ? false : var1.hiddenLayerTargetIds.contains(this.shellSpawnOpacityRetoggleTargetId(var2));
   }

   @Override
   protected int resolveShellBlockInitialOpacity(EditorSession var1, String var2, ConfigurationSection var3, int var4) {
      if (var1 != null && var3 != null && var1.editMode && var1.previewMode) {
         if (var1.hiddenLayerTargetIds.contains(this.shellSpawnOpacityRetoggleTargetId(var2))) {
            return 0;
         } else {
            EditorSidebarTab var5 = var1.rightSidebarTab == null ? EditorSidebarTab.LAYERS : var1.rightSidebarTab;
            String var6 = this.resolvePropertiesTabPanelId(var1);
            String var7 = this.firstNonBlank(new String[]{var3.getString("__editor_target_path")});
            String var8 = this.firstNonBlank(new String[]{var3.getString("__editor_component_name")});
            boolean var9 = var1.activeTool == EditorTool.ANIMATION;
            if ("editor_animation_element".equalsIgnoreCase(var8)) {
               String var10 = this.resolveAnimationTimelineEditableTargetId(var1);
               if (!var9 || var10 == null || var10.isBlank()) {
                  return 0;
               }
            }

            ConfigurationSection var67 = this.findShellBlockSection(var1, "popup_save_root");
            String var11 = this.firstNonBlank(new String[]{var67 == null ? null : var67.getString("__editor_target_path")});
            String var12 = this.resolvePanelComponentNameFallback("popup_save_root");
            boolean var13 = !var11.isBlank() && this.belongsToSidebarPanel(var11, var7) || !var12.isBlank() && var12.equalsIgnoreCase(var8);
            if (var13 && !var1.savePopupVisible) {
               return 0;
            } else {
               ConfigurationSection var14 = this.findShellBlockSection(var1, "popup_preferences_root");
               String var15 = this.firstNonBlank(new String[]{var14 == null ? null : var14.getString("__editor_target_path")});
               String var16 = this.resolvePanelComponentNameFallback("popup_preferences_root");
               boolean var17 = !var15.isBlank() && this.belongsToSidebarPanel(var15, var7) || !var16.isBlank() && var16.equalsIgnoreCase(var8);
               if (var17 && !var1.preferencesPopupVisible) {
                  return 0;
               } else {
                  ConfigurationSection var18 = this.findShellBlockSection(var1, "popup_welcome_root");
                  String var19 = this.firstNonBlank(new String[]{var18 == null ? null : var18.getString("__editor_target_path")});
                  String var20 = this.resolvePanelComponentNameFallback("popup_welcome_root");
                  boolean var21 = !var19.isBlank() && this.belongsToSidebarPanel(var19, var7) || !var20.isBlank() && var20.equalsIgnoreCase(var8);
                  if (var21 && !var1.welcomePopupVisible) {
                     return 0;
                  } else {
                     ConfigurationSection var22 = this.findShellBlockSection(var1, "popup_file_selector_root");
                     String var23 = this.firstNonBlank(new String[]{var22 == null ? null : var22.getString("__editor_target_path")});
                     boolean var24 = !var23.isBlank() && this.belongsToSidebarPanel(var23, var7) || "editor_manage_file_popup".equalsIgnoreCase(var8);
                     if (var24 && (!var1.menuFilePopupImageMode || !var1.menuFilePopupVisible)) {
                        return 0;
                     } else if (var7.isBlank()) {
                        return var4;
                     } else {
                        ConfigurationSection var25 = this.findShellBlockSection(var1, "animation_timeline");
                        String var26 = this.firstNonBlank(new String[]{var25 == null ? null : var25.getString("__editor_target_path")});
                        if (!var26.isBlank() && this.belongsToSidebarPanel(var26, var7) && !var9) {
                           return 0;
                        } else {
                           ConfigurationSection var27 = this.findShellBlockSection(var1, "properties");
                           String var28 = this.firstNonBlank(new String[]{var27 == null ? null : var27.getString("__editor_target_path")});
                           if (var28.isBlank() || !this.belongsToSidebarPanel(var28, var7) || var5 == EditorSidebarTab.PROPERTIES && "properties".equals(var6)) {
                              String[] var29 = new String[]{"keyframe_properties", "keyframe_properties_rotation", "keyframe_properties_opacity"};

                              for (Object var71_raw : var29) {
                                 String var71 = var71_raw != null ? var71_raw.toString() : null;
                                 ConfigurationSection var74 = this.findShellBlockSection(var1, var71);
                                 String var76 = this.firstNonBlank(new String[]{var74 == null ? null : var74.getString("__editor_target_path")});
                                 if (!var76.isBlank()
                                    && this.belongsToSidebarPanel(var76, var7)
                                    && (var5 != EditorSidebarTab.PROPERTIES || !var71.equals(var6))) {
                                    return 0;
                                 }
                              }

                              ConfigurationSection var68 = this.findShellBlockSection(var1, "actions");
                              String var69 = this.firstNonBlank(new String[]{var68 == null ? null : var68.getString("__editor_target_path")});
                              if (var69.isBlank() || !this.belongsToSidebarPanel(var69, var7) || var5 == EditorSidebarTab.PROPERTIES && "actions".equals(var6)) {
                                 String var70 = this.resolveDesignTabPanelId(var1);
                                 String[] var33 = new String[]{"design", "item_design"};

                                 for (Object var78_raw : var33) {
                                    String var78 = var78_raw != null ? var78_raw.toString() : null;
                                    ConfigurationSection var79 = this.findShellBlockSection(var1, var78);
                                    String var80 = this.firstNonBlank(new String[]{var79 == null ? null : var79.getString("__editor_target_path")});
                                    if (!var80.isBlank()
                                       && this.belongsToSidebarPanel(var80, var7)
                                       && (var5 != EditorSidebarTab.DESIGN || !var78.equals(var70))) {
                                       return 0;
                                    }
                                 }

                                 if (var1.rightSidebarTab != EditorSidebarTab.DESIGN || !this.isTextDesignSidebarMode(var1)) {
                                    String var72 = this.resolveDesignTextSettingsInvocationPath(var1);
                                    if (this.isPanelCachedBlock(var1, var3, "text_settings") || !var72.isBlank() && this.belongsToSidebarPanel(var72, var7)) {
                                       return 0;
                                    }
                                 }

                                 ConfigurationSection var73 = this.findShellBlockSection(var1, "layers");
                                 String var75 = this.firstNonBlank(new String[]{var73 == null ? null : var73.getString("__editor_target_path")});
                                 if (!var75.isBlank() && this.belongsToSidebarPanel(var75, var7) && var5 != EditorSidebarTab.LAYERS) {
                                    return 0;
                                 } else {
                                    ConfigurationSection var77 = this.findShellBlockSection(var1, "file_dropdown");
                                    String var37 = this.firstNonBlank(new String[]{var77 == null ? null : var77.getString("__editor_target_path")});
                                    if (!var37.isBlank() && this.belongsToSidebarPanel(var37, var7) && !var1.fileDropdownVisible) {
                                       return 0;
                                    } else {
                                       ConfigurationSection var38 = this.findShellBlockSection(var1, "edit_dropdown");
                                       String var39 = this.firstNonBlank(new String[]{var38 == null ? null : var38.getString("__editor_target_path")});
                                       if (!var39.isBlank() && this.belongsToSidebarPanel(var39, var7) && !var1.editDropdownVisible) {
                                          return 0;
                                       } else {
                                          ConfigurationSection var40 = this.findShellBlockSection(var1, "selection_dropdown");
                                          String var41 = this.firstNonBlank(new String[]{var40 == null ? null : var40.getString("__editor_target_path")});
                                          if (!var41.isBlank() && this.belongsToSidebarPanel(var41, var7) && !var1.selectionDropdownVisible) {
                                             return 0;
                                          } else {
                                             ConfigurationSection var42 = this.findShellBlockSection(var1, "layer_dropdown");
                                             String var43 = this.firstNonBlank(new String[]{var42 == null ? null : var42.getString("__editor_target_path")});
                                             if (!var43.isBlank() && this.belongsToSidebarPanel(var43, var7) && !var1.layerDropdownVisible) {
                                                return 0;
                                             } else {
                                                ConfigurationSection var44 = this.findShellBlockSection(var1, "window_dropdown");
                                                String var45 = this.firstNonBlank(new String[]{var44 == null ? null : var44.getString("__editor_target_path")});
                                                if (!var45.isBlank() && this.belongsToSidebarPanel(var45, var7) && !var1.windowDropdownVisible) {
                                                   return 0;
                                                } else {
                                                   ConfigurationSection var46 = this.findShellBlockSection(var1, "keyframe_dropdown");
                                                   String var47 = this.firstNonBlank(
                                                      new String[]{var46 == null ? null : var46.getString("__editor_target_path")}
                                                   );
                                                   if (!var47.isBlank() && this.belongsToSidebarPanel(var47, var7) && !var1.keyframeDropdownVisible) {
                                                      return 0;
                                                   } else {
                                                      ConfigurationSection var48 = this.findShellBlockSection(var1, "keyframe_timeline_dropdown");
                                                      String var49 = this.firstNonBlank(
                                                         new String[]{var48 == null ? null : var48.getString("__editor_target_path")}
                                                      );
                                                      if (!var49.isBlank() && this.belongsToSidebarPanel(var49, var7) && !var1.keyframeTimelineDropdownVisible) {
                                                         return 0;
                                                      } else {
                                                         ConfigurationSection var50 = this.findShellBlockSection(var1, "colorpicker_ui");
                                                         String var51 = this.firstNonBlank(
                                                            new String[]{var50 == null ? null : var50.getString("__editor_target_path")}
                                                         );
                                                         if (!var51.isBlank() && this.belongsToSidebarPanel(var51, var7) && !var1.colorPickerVisible) {
                                                            return 0;
                                                         } else {
                                                            ConfigurationSection var52 = this.findShellBlockSection(var1, "cursor_page_dropdown");
                                                            String var53 = this.firstNonBlank(
                                                               new String[]{var52 == null ? null : var52.getString("__editor_target_path")}
                                                            );
                                                            if (!var53.isBlank() && this.belongsToSidebarPanel(var53, var7) && !var1.cursorPageDropdownVisible) {
                                                               return 0;
                                                            } else {
                                                               ConfigurationSection var54 = this.findShellBlockSection(var1, "cursor_layers_dropdown");
                                                               String var55 = this.firstNonBlank(
                                                                  new String[]{var54 == null ? null : var54.getString("__editor_target_path")}
                                                               );
                                                               if (!var55.isBlank()
                                                                  && this.belongsToSidebarPanel(var55, var7)
                                                                  && !var1.cursorLayersDropdownVisible) {
                                                                  return 0;
                                                               } else {
                                                                  ConfigurationSection var56 = this.findShellBlockSection(var1, "cursor_actions_dropdown");
                                                                  String var57 = this.firstNonBlank(
                                                                     new String[]{var56 == null ? null : var56.getString("__editor_target_path")}
                                                                  );
                                                                  if (!var57.isBlank()
                                                                     && this.belongsToSidebarPanel(var57, var7)
                                                                     && !var1.cursorActionsDropdownVisible) {
                                                                     return 0;
                                                                  } else {
                                                                     ConfigurationSection var58 = this.findShellBlockSection(
                                                                        var1, "cursor_actions_edit_dropdown"
                                                                     );
                                                                     String var59 = this.firstNonBlank(
                                                                        new String[]{var58 == null ? null : var58.getString("__editor_target_path")}
                                                                     );
                                                                     if (!var59.isBlank()
                                                                        && this.belongsToSidebarPanel(var59, var7)
                                                                        && !var1.cursorActionsEditDropdownVisible) {
                                                                        return 0;
                                                                     } else {
                                                                        ConfigurationSection var60 = this.findShellBlockSection(var1, "preview_size");
                                                                        String var61 = this.firstNonBlank(
                                                                           new String[]{var60 == null ? null : var60.getString("__editor_target_path")}
                                                                        );
                                                                        if (!var61.isBlank()
                                                                           && this.belongsToSidebarPanel(var61, var7)
                                                                           && !var1.previewSizeToastVisible) {
                                                                           return 0;
                                                                        } else {
                                                                           ConfigurationSection var62 = this.findShellBlockSection(var1, "preview_ui_addons");
                                                                           String var63 = this.firstNonBlank(
                                                                              new String[]{var62 == null ? null : var62.getString("__editor_target_path")}
                                                                           );
                                                                           if (!var63.isBlank() && this.belongsToSidebarPanel(var63, var7)) {
                                                                              if (!var1.displayHand && !var1.displayHotbar) {
                                                                                 return 0;
                                                                              }

                                                                              String var64 = this.firstNonBlank(
                                                                                 new String[]{var3.getString("text"), var3.getString("unicode")}
                                                                              );
                                                                              if ("preview_ui_addons_hand_icon".equals(var2) || "\ue1b3".equals(var64)) {
                                                                                 return var1.displayHand ? var4 : 0;
                                                                              }

                                                                              if ("preview_ui_addons_hotbar_icon".equals(var2) || "\ue1b4".equals(var64)) {
                                                                                 return var1.displayHotbar ? var4 : 0;
                                                                              }
                                                                           }

                                                                           if (this.isPreviewEmptyInfoShellBlock(var2, var3)) {
                                                                              return this.shouldShowPreviewEmptyInfo(var1) ? var4 : 0;
                                                                           } else {
                                                                              ConfigurationSection var81 = this.findShellBlockSection(var1, "block_align");
                                                                              String var65 = this.firstNonBlank(
                                                                                 new String[]{var81 == null ? null : var81.getString("__editor_target_path")}
                                                                              );
                                                                              if (!var65.isBlank() && this.belongsToSidebarPanel(var65, var7)) {
                                                                                 boolean var66 = var1.selectedElementId != null
                                                                                    || !var1.additionalSelectedElementIds.isEmpty();
                                                                                 if (!var1.alignToolbarVisible || !var66) {
                                                                                    return 0;
                                                                                 }
                                                                              }

                                                                              return var4;
                                                                           }
                                                                        }
                                                                     }
                                                                  }
                                                               }
                                                            }
                                                         }
                                                      }
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              } else {
                                 return 0;
                              }
                           } else {
                              return 0;
                           }
                        }
                     }
                  }
               }
            }
         }
      } else {
         return var4;
      }
   }

   @Override
   protected boolean shouldRenderEditorShellBlock(EditorSession var1, String var2, ConfigurationSection var3) {
      if (var1 == null || var3 == null || !var1.editMode || !var1.previewMode) {
         return true;
      } else if (this.resolveShellBlockInitialOpacity(var1, var2, var3, 255) > 0) {
         return true;
      } else {
         return this.isPreviewEmptyInfoShellBlock(var2, var3) ? true : this.isShellCachedPanelBlock(var1, var3);
      }
   }

   @Override
   protected boolean shouldKeepHiddenShellBlockSpawned(EditorSession var1, String var2, ConfigurationSection var3, String var4) {
      String var5 = this.firstNonBlank(new String[]{var3 == null ? null : var3.getString("__editor_target_path")});
      String var6 = this.resolveDesignTextSettingsInvocationPath(var1);
      return this.isPanelCachedBlock(var1, var3, "text_settings")
         || !var6.isBlank() && this.belongsToSidebarPanel(var6, var5)
         || this.isPreviewEmptyInfoShellBlock(var2, var3);
   }

   protected boolean isPreviewEmptyInfoShellBlock(String var1, ConfigurationSection var2) {
      if ("addelements_info".equals(var1)) {
         return true;
      } else if (var2 == null) {
         return false;
      } else {
         String var3 = this.firstNonBlank(new String[]{var2.getString("id")});
         return "addelements_info".equals(var3);
      }
   }

   protected boolean shouldShowPreviewEmptyInfo(EditorSession var1) {
      return !this.hasAnyPreviewPageBlocks(var1 == null ? null : var1.rawBlocks);
   }

   protected boolean hasAnyPreviewPageBlocks(List<Map<String, Object>> var1) {
      if (var1 != null && !var1.isEmpty()) {
         for (Map var3 : var1) {
            if (var3 != null && !var3.isEmpty()) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   protected boolean isShellCachedPanelBlock(EditorSession var1, ConfigurationSection var2) {
      return this.isRightSidebarCachedPanelBlock(var1, var2)
         || this.isCursorPageCachedPanelBlock(var1, var2)
         || this.isCursorActionsCachedPanelBlock(var1, var2)
         || this.isColorPickerCachedPanelBlock(var1, var2)
         || this.isTopNavbarDropdownCachedPanelBlock(var1, var2)
         || this.isWelcomePopupCachedPanelBlock(var1, var2)
         || this.isSavePopupCachedPanelBlock(var1, var2)
         || this.isPreferencesPopupCachedPanelBlock(var1, var2)
         || this.isAnimationTimelineCachedPanelBlock(var2);
   }

   protected boolean isAnimationTimelineCachedPanelBlock(ConfigurationSection var1) {
      if (var1 == null) {
         return false;
      } else {
         String var2 = this.firstNonBlank(new String[]{var1.getString("__editor_component_name")});
         return "editor_animation_element".equalsIgnoreCase(var2);
      }
   }

   protected boolean isPanelCachedBlock(EditorSession var1, ConfigurationSection var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String var4 = this.firstNonBlank(new String[]{var2.getString("__editor_target_path")});
         ConfigurationSection var5 = this.findShellBlockSection(var1, var3);
         String var6 = this.firstNonBlank(new String[]{var5 == null ? null : var5.getString("__editor_target_path")});
         if (!var6.isBlank() && this.belongsToSidebarPanel(var6, var4)) {
            return true;
         } else {
            String var7 = this.resolvePanelComponentNameFallback(var3);
            if (var7.isBlank()) {
               return false;
            } else {
               String var8 = this.firstNonBlank(new String[]{var2.getString("__editor_component_name")});
               return !var8.isBlank() && var7.equalsIgnoreCase(var8);
            }
         }
      } else {
         return false;
      }
   }

   protected boolean isTopNavbarDropdownCachedPanelBlock(EditorSession var1, ConfigurationSection var2) {
      return this.isPanelCachedBlock(var1, var2, "file_dropdown")
         || this.isPanelCachedBlock(var1, var2, "edit_dropdown")
         || this.isPanelCachedBlock(var1, var2, "selection_dropdown")
         || this.isPanelCachedBlock(var1, var2, "layer_dropdown")
         || this.isPanelCachedBlock(var1, var2, "window_dropdown");
   }

   protected boolean isColorPickerCachedPanelBlock(EditorSession var1, ConfigurationSection var2) {
      return this.isPanelCachedBlock(var1, var2, "colorpicker_ui");
   }

   protected boolean isSavePopupCachedPanelBlock(EditorSession var1, ConfigurationSection var2) {
      return this.isPanelCachedBlock(var1, var2, "popup_save_root");
   }

   protected boolean isWelcomePopupCachedPanelBlock(EditorSession var1, ConfigurationSection var2) {
      return this.isPanelCachedBlock(var1, var2, "popup_welcome_root");
   }

   protected boolean isPreferencesPopupCachedPanelBlock(EditorSession var1, ConfigurationSection var2) {
      return this.isPanelCachedBlock(var1, var2, "popup_preferences_root");
   }

   protected boolean isCursorPageCachedPanelBlock(EditorSession var1, ConfigurationSection var2) {
      if (var1 != null && var2 != null) {
         String var3 = this.firstNonBlank(new String[]{var2.getString("__editor_target_path")});
         if (var3.isBlank()) {
            return false;
         } else {
            ConfigurationSection var4 = this.findShellBlockSection(var1, "cursor_page_dropdown");
            String var5 = this.firstNonBlank(new String[]{var4 == null ? null : var4.getString("__editor_target_path")});
            return !var5.isBlank() && this.belongsToSidebarPanel(var5, var3);
         }
      } else {
         return false;
      }
   }

   protected boolean isRightSidebarCachedPanelBlock(EditorSession var1, ConfigurationSection var2) {
      if (var1 != null && var2 != null) {
         String var3 = this.firstNonBlank(new String[]{var2.getString("__editor_target_path")});
         if (var3.isBlank()) {
            return false;
         } else {
            ConfigurationSection var4 = this.findShellBlockSection(var1, "properties");
            String var5 = this.firstNonBlank(new String[]{var4 == null ? null : var4.getString("__editor_target_path")});
            if (!var5.isBlank() && this.belongsToSidebarPanel(var5, var3)) {
               return true;
            } else {
               String[] var6 = new String[]{"keyframe_properties", "keyframe_properties_rotation", "keyframe_properties_opacity"};

               for (Object var10_raw : var6) {
                  String var10 = var10_raw != null ? var10_raw.toString() : null;
                  ConfigurationSection var11 = this.findShellBlockSection(var1, var10);
                  String var12 = this.firstNonBlank(new String[]{var11 == null ? null : var11.getString("__editor_target_path")});
                  if (!var12.isBlank() && this.belongsToSidebarPanel(var12, var3)) {
                     return true;
                  }
               }

               String[] var14 = new String[]{"design", "item_design"};

               for (Object var21_raw : var14) {
                  String var21 = var21_raw != null ? var21_raw.toString() : null;
                  ConfigurationSection var23 = this.findShellBlockSection(var1, var21);
                  String var13 = this.firstNonBlank(new String[]{var23 == null ? null : var23.getString("__editor_target_path")});
                  if (!var13.isBlank() && this.belongsToSidebarPanel(var13, var3)) {
                     return true;
                  }
               }

               ConfigurationSection var16 = this.findShellBlockSection(var1, "layers");
               String var18 = this.firstNonBlank(new String[]{var16 == null ? null : var16.getString("__editor_target_path")});
               if (!var18.isBlank() && this.belongsToSidebarPanel(var18, var3)) {
                  return true;
               } else {
                  ConfigurationSection var20 = this.findShellBlockSection(var1, "actions");
                  String var22 = this.firstNonBlank(new String[]{var20 == null ? null : var20.getString("__editor_target_path")});
                  return !var22.isBlank() && this.belongsToSidebarPanel(var22, var3);
               }
            }
         }
      } else {
         return false;
      }
   }

   protected boolean shouldKeepShellPanelSpawned(String var1) {
      return false;
   }

   protected String resolvePanelComponentNameFallback(String var1) {
      if (var1 == null || var1.isBlank()) {
         return "";
      } else if ("popup_save_root".equals(var1)) {
         return "editor_save_popup";
      } else if ("popup_preferences_root".equals(var1)) {
         return "editor_preferences_popup";
      } else {
         return "popup_welcome_root".equals(var1) ? "editor_welcome_popup" : "";
      }
   }

   @Override
   protected boolean setSidebarPanelVisibleByComponentName(Player var1, EditorSession var2, String var3, boolean var4, boolean var5) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         boolean var6 = false;
         HashMap var7 = new HashMap();
         int var8 = 0;

         for (Map var10 : var2.shellBlocks) {
            var8++;
            ConfigurationSection var11 = this.mapToSection(var10);
            if (var11 != null) {
               String var12 = this.firstNonBlank(new String[]{var11.getString("__editor_component_name")});
               if (var3.equalsIgnoreCase(var12)) {
                  String var13 = this.resolveElementId(var11, var8, var7);
                  var6 = true;
                  if (!var4 && !var5) {
                     this.clearShellElementHud(var1, var2, var13);
                  } else {
                     int var14 = this.readOpacity(var11, 255);
                     this.setShellOpacity(var1, var13, var4 ? var14 : 0);
                  }
               }
            }
         }

         return var6;
      } else {
         return false;
      }
   }

   @Override
   protected boolean isKeyframePropertiesSidebarMode(EditorSession var1) {
      if (var1 == null || var1.activeTool != EditorTool.ANIMATION) {
         return false;
      } else if (!this.isAnimationTimelinePositionKeyframeSelected(var1)
         && !this.isAnimationTimelineScaleKeyframeSelected(var1)
         && !this.isAnimationTimelineRotationKeyframeSelected(var1)
         && !this.isAnimationTimelineOpacityKeyframeSelected(var1)) {
         return false;
      } else {
         String var2 = this.firstNonBlank(new String[]{var1.animationTimelineSelectedTargetId});
         String var3 = this.firstNonBlank(new String[]{this.resolveAnimationTimelineEditableTargetId(var1)});
         return !var2.isBlank() && !var3.isBlank() && var2.equals(var3);
      }
   }

   protected String resolvePropertiesTabPanelId(EditorSession var1) {
      if (this.isActionsSidebarMode(var1)) {
         return "actions";
      } else {
         return this.isKeyframePropertiesSidebarMode(var1) ? this.resolveKeyframePropertiesPanelId(var1) : "properties";
      }
   }

   protected String resolveKeyframePropertiesPanelId(EditorSession var1) {
      if (this.isAnimationTimelineRotationKeyframeSelected(var1)) {
         return "keyframe_properties_rotation";
      } else {
         return this.isAnimationTimelineOpacityKeyframeSelected(var1) ? "keyframe_properties_opacity" : "keyframe_properties";
      }
   }

   protected String resolveDesignTabPanelId(EditorSession var1) {
      return this.isItemDesignSidebarMode(var1) ? "item_design" : "design";
   }

   @Override
   protected boolean isItemDesignSidebarMode(EditorSession var1) {
      String var2 = this.firstNonBlank(new String[]{this.getSidebarEditableTargetId(var1)});
      return var2.isBlank() ? false : this.isSidebarItemDesignTarget(var1, var2);
   }

   @Override
   protected void clearPendingItemDesignInventoryPick(EditorSession var1) {
      if (var1 != null) {
         var1.pendingItemDesignInventoryTargetId = null;
      }
   }

   @Override
   protected EditorRect findShellBlockRect(EditorSession var1, String var2) {
      if (var1 != null && var1.shellRuntimeRects != null) {
         EditorRect var3 = var1.shellRuntimeRects.get(var2);
         if (var3 != null) {
            return var3;
         }
      }

      if (var1 != null && var2 != null && !var2.isBlank()) {
         this.ensureShellBlockCache(var1);
         EditorRect var4 = var1.shellStaticRectCache.get(var2);
         if (var4 != null && var1.shellRuntimeRects != null) {
            var1.shellRuntimeRects.put(var2, var4);
         }

         return var4;
      } else {
         return null;
      }
   }

   @Override
   protected void ensureShellBlockCache(EditorSession var1) {
      if (var1 != null && !var1.shellCacheBuilt) {
         var1.shellSectionCache.clear();
         var1.shellBlockMapCache.clear();
         var1.shellStaticRectCache.clear();
         var1.sidebarPanelVisibilityStates.clear();
         if (var1.shellBlocks != null && !var1.shellBlocks.isEmpty()) {
            HashMap var2 = new HashMap();
            int var3 = 0;

            for (Map var5 : var1.shellBlocks) {
               var3++;
               ConfigurationSection var6 = this.mapToSection(var5);
               if (var6 != null) {
                  String var7 = this.resolveElementId(var6, var3, var2);
                  if (!var7.isBlank() && !var1.shellSectionCache.containsKey(var7)) {
                     var1.shellSectionCache.put(var7, var6);
                     var1.shellBlockMapCache.put(var7, var5);
                     double var8 = this.readDouble(var6, "position.x", "x", 0.0);
                     double var10 = this.readDouble(var6, "position.y", "y", 0.0);
                     double var12 = Math.max(1.0, Math.abs(this.readDouble(var6, "size.width", "width", this.readDouble(var6, "scale.width", "width", 1.0))));
                     double var14 = Math.max(
                        1.0, Math.abs(this.readDouble(var6, "size.height", "height", this.readDouble(var6, "scale.height", "height", 1.0)))
                     );
                     var1.shellStaticRectCache.put(var7, new EditorRect(var8, var10, var12, var14));
                  }
               }
            }

            var1.shellCacheBuilt = true;
         } else {
            var1.shellCacheBuilt = true;
         }
      }
   }

   @Override
   protected ConfigurationSection findShellBlockSection(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         this.ensureShellBlockCache(var1);
         return var1.shellSectionCache.get(var2);
      } else {
         return null;
      }
   }

   @Override
   protected void moveShellElement(Player var1, EditorSession var2, String var3, double var4, double var6, double var8, double var10) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         ConfigurationSection var12 = this.findShellBlockSection(var2, var3);
         if (var12 != null) {
            String var13 = this.firstNonBlank(new String[]{var12.getString("type"), "block"}).toLowerCase(Locale.ROOT);
            double var14 = this.readDouble(var12, "layer", "layer", this.readDouble(var12, "size.depth", "depth", 0.0));
            double var16 = this.readDouble(var12, "size.width", "width", var8);
            double var18 = this.readDouble(var12, "size.height", "height", var10);
            boolean var20 = var16 < 0.0;
            boolean var21 = var18 < 0.0;
            double var22 = this.readDouble(var12, "rotation", "rotate", 0.0);
            double var24 = "text".equals(var13) ? 0.0 : this.readOutlineSize(var12);
            String var26 = "text".equals(var13) ? "" : this.readOutlineColor(var12);
            String var27 = null;
            if (!"text".equals(var13)) {
               String var28 = this.firstNonBlank(new String[]{var12.getString("unicode"), var12.getString("text"), "█"});
               String var29 = this.firstNonBlank(new String[]{var12.getString("color"), var12.getString("style.color")});
               String var30 = this.applyPlaceholderApi(var1, this.withHexPrefix(var28, var29));
               var30 = this.applyPreferredFont(var30, var12, true);
               if (var24 > 1.0E-4) {
                  var27 = this.applyOutlineColor(var30, var26);
               }
            }

            if (this.isRoundedType(var13)) {
               boolean var61 = this.isDirectionalRoundedType(var13);
               int var63 = this.readOpacity(var12, 255);
               String var66 = this.firstNonBlank(new String[]{var12.getString("unicode"), var12.getString("text"), "█"});
               String var31 = this.firstNonBlank(new String[]{var12.getString("color"), var12.getString("style.color")});
               String var67 = this.applyPlaceholderApi(var1, this.withHexPrefix(var66, var31));
               var67 = this.applyPreferredFont(var67, var12, true);
               double var33 = this.readRoundedRadius(var12, var8, var10);
               String var69 = this.readRoundedCornerUnicode(var12);
               String var70 = var61 ? this.readRoundedCornerUnicodeTopLeft(var12) : "";
               String var71 = var61 ? this.readRoundedCornerUnicodeTopRight(var12) : "";
               String var38 = var61 ? this.readRoundedCornerUnicodeBottomRight(var12) : "";
               String var72 = var61 ? this.readRoundedCornerUnicodeBottomLeft(var12) : "";
               double var40 = this.readRoundedTopLeftOffsetX(var12);
               double var42 = this.readRoundedTopLeftOffsetY(var12);
               double var44 = this.readRoundedTopRightOffsetX(var12);
               double var46 = this.readRoundedTopRightOffsetY(var12);
               double var48 = this.readRoundedBottomRightOffsetX(var12);
               double var50 = this.readRoundedBottomRightOffsetY(var12);
               double var52 = this.readRoundedBottomLeftOffsetX(var12);
               double var54 = this.readRoundedBottomLeftOffsetY(var12);
               double var56 = var4 + Math.max(1.0, var8) / 2.0;
               double var58 = var6 + Math.max(1.0, var10) / 2.0;
               this.renderRoundedOutline(
                  var1,
                  "editor_shell_" + var3,
                  var4,
                  var6,
                  var14,
                  Math.max(1.0, var8),
                  Math.max(1.0, var10),
                  var27,
                  var67,
                  var63,
                  var33,
                  var69,
                  var70,
                  var71,
                  var38,
                  var72,
                  var40,
                  var42,
                  var44,
                  var46,
                  var48,
                  var50,
                  var52,
                  var54,
                  var22,
                  var20,
                  var21,
                  var24,
                  var56,
                  var58
               );
               this.renderRoundedBlock(
                  var1,
                  "editor_shell_" + var3,
                  var4,
                  var6,
                  var14,
                  Math.max(1.0, var8),
                  Math.max(1.0, var10),
                  var67,
                  var63,
                  var33,
                  var69,
                  var70,
                  var71,
                  var38,
                  var72,
                  var40,
                  var42,
                  var44,
                  var46,
                  var48,
                  var50,
                  var52,
                  var54,
                  var22,
                  var20,
                  var21,
                  var56,
                  var58
               );
               var2.shellRuntimeRects.put(var3, new EditorRect(var4, var6, Math.max(1.0, var8), Math.max(1.0, var10)));
            } else {
               TextAlignment var60 = "text".equals(var13) ? this.readTextAlignment(var12) : TextAlignment.CENTER;
               boolean var62 = "opacity_circle".equals(var3) && "text".equals(var13);
               double var65 = "text".equals(var13) && !var62 ? this.toInternalTextTopY(var6, var10) : var6;
               double var32 = "text".equals(var13) ? this.applyTextAlignmentOffset(var4, var8, var60) : var4;
               HudPositionCalculator.Placement var34 = this.positionCalculator
                  .calculateBoxPlacement(var32, var65, var14, Math.max(1.0, var8), Math.max(1.0, var10));
               Entity var35 = this.hudService.getHud(var1, "editor_shell_" + var3);
               if (var35 == null) {
                  if ("hitbox".equals(var13)) {
                     var2.shellRuntimeRects.put(var3, new EditorRect(var4, var6, Math.max(1.0, var8), Math.max(1.0, var10)));
                  }
               } else {
                  int var36 = this.resolveEditorHudTransitionTicks(var2);
                  this.hudService.moveHud(var35, var34.location(), var36, var36);
                  this.hudService.setHudScale(var35, var34.scale(), var36, false);
                  if ("text".equals(var13)) {
                     this.hudService.setTextAlignment(var35, var60);
                     this.clearOutlineHud(var1, "editor_shell_" + var3);
                  } else {
                     double var37 = var32 + Math.max(1.0, var8) / 2.0;
                     double var39 = var65 + Math.max(1.0, var10) / 2.0;
                     this.renderSimpleOutline(
                        var1,
                        "editor_shell_" + var3,
                        var32,
                        var65,
                        var14,
                        Math.max(1.0, var8),
                        Math.max(1.0, var10),
                        var27,
                        this.readOpacity(var12, 255),
                        var60,
                        var22,
                        var20,
                        var21,
                        var24,
                        var37,
                        var39
                     );
                  }

                  this.applyElementTransform(var35, var22, var20, var21);
                  var2.shellRuntimeRects.put(var3, new EditorRect(var4, var6, Math.max(1.0, var8), Math.max(1.0, var10)));
               }
            }
         }
      }
   }

   @Override
   protected void setShellText(Player var1, String var2, String var3) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         EditorSession var4 = this.editorSessions.get(var1.getUniqueId());
         ConfigurationSection var5 = this.findShellBlockSection(var4, var2);
         String var6 = var3 == null ? "" : var3;
         if (var5 != null && !var6.isBlank() && !var6.contains("<#")) {
            String var7 = this.firstNonBlank(new String[]{var5.getString("color"), var5.getString("style.color")});
            if (!var7.isBlank()) {
               var6 = this.withHexPrefix(var6, var7);
            }
         }

         boolean var10 = var5 != null && !this.firstNonBlank(new String[]{var5.getString("unicode")}).isBlank();
         String var8 = this.applyPreferredFont(var6, var5, var10);
         String var9 = "editor_shell_" + var2;
         if (this.hudService.getHud(var1, var9) == null) {
            this.cacheShellTextForDeferredSpawn(var4, var2, var6);
         }

         this.setHudTextIfExists(var1, var9, var8);
      }
   }

   @Override
   protected void cacheShellTextForDeferredSpawn(EditorSession var1, String var2, String var3) {
      if (var1 != null && var2 != null && !var2.isBlank() && var1.shellBlocks != null && !var1.shellBlocks.isEmpty()) {
         this.ensureShellBlockCache(var1);
         ConfigurationSection var4 = var1.shellSectionCache.get(var2);
         if (var4 != null) {
            String var5 = this.firstNonBlank(new String[]{var4.getString("type"), "block"}).toLowerCase(Locale.ROOT);
            if ("text".equals(var5)) {
               String var6 = var3 == null ? "" : var3;
               Map var7 = var1.shellBlockMapCache.get(var2);
               if (var7 != null) {
                  var7.put("text", var6);
               }

               var4.set("text", var6);
            }
         }
      }
   }

   @Override
   protected void setShellColor(Player var1, EditorSession var2, String var3, String var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         ConfigurationSection var5 = this.findShellBlockSection(var2, var3);
         if (var5 != null) {
            String var6 = "editor_shell_" + var3;
            String var7 = this.firstNonBlank(new String[]{var4, "ffffff"});
            String var8 = this.firstNonBlank(new String[]{var5.getString("unicode"), var5.getString("text"), "\ue67b"});
            String var9 = this.applyPreferredFont(this.withHexPrefix(var8, var7), var5, true);
            String var10 = this.firstNonBlank(new String[]{var5.getString("type"), "block"}).toLowerCase(Locale.ROOT);
            if (!this.isRoundedType(var10)) {
               this.setHudTextIfExists(var1, var6, var9);
            } else {
               this.setHudTextIfExists(var1, var6, var9);
               this.setHudTextIfExists(var1, var6 + "_r_core", var9);
               this.setHudTextIfExists(var1, var6 + "_r_top", var9);
               this.setHudTextIfExists(var1, var6 + "_r_bottom", var9);
               this.setHudTextIfExists(var1, var6 + "_r_left", var9);
               this.setHudTextIfExists(var1, var6 + "_r_right", var9);
               boolean var11 = this.isDirectionalRoundedType(var10);
               String var12 = this.firstNonBlank(new String[]{this.readRoundedCornerUnicode(var5)}).replaceAll("<#[0-9a-fA-F]{6}>", "");
               String var13 = var11 ? this.firstNonBlank(new String[]{this.readRoundedCornerUnicodeTopLeft(var5)}).replaceAll("<#[0-9a-fA-F]{6}>", "") : var12;
               String var14 = var11 ? this.firstNonBlank(new String[]{this.readRoundedCornerUnicodeTopRight(var5)}).replaceAll("<#[0-9a-fA-F]{6}>", "") : var12;
               String var15 = var11
                  ? this.firstNonBlank(new String[]{this.readRoundedCornerUnicodeBottomLeft(var5)}).replaceAll("<#[0-9a-fA-F]{6}>", "")
                  : var12;
               String var16 = var11
                  ? this.firstNonBlank(new String[]{this.readRoundedCornerUnicodeBottomRight(var5)}).replaceAll("<#[0-9a-fA-F]{6}>", "")
                  : var12;
               String var17 = this.applyPreferredFont(this.withHexPrefix(this.firstNonBlank(new String[]{var13, var12, "\uef60"}), var7), var5, true);
               String var18 = this.applyPreferredFont(this.withHexPrefix(this.firstNonBlank(new String[]{var14, var12, "\uef61"}), var7), var5, true);
               String var19 = this.applyPreferredFont(this.withHexPrefix(this.firstNonBlank(new String[]{var15, var12, "\uef63"}), var7), var5, true);
               String var20 = this.applyPreferredFont(this.withHexPrefix(this.firstNonBlank(new String[]{var16, var12, "\uef62"}), var7), var5, true);
               this.setHudTextIfExists(var1, var6 + "_r_tl", var17);
               this.setHudTextIfExists(var1, var6 + "_r_tr", var18);
               this.setHudTextIfExists(var1, var6 + "_r_bl", var19);
               this.setHudTextIfExists(var1, var6 + "_r_br", var20);
            }
         }
      }
   }

   @Override
   protected void setShellOpacity(Player var1, String var2, int var3) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         int var4 = Math.max(5, Math.min(255, var3));
         EditorSession var5 = this.editorSessions.get(var1.getUniqueId());
         if (var5 != null && var5.shellOpacityBatchDepth > 0) {
            var5.pendingShellOpacity.put(var2, Integer.valueOf(var4));
         } else {
            this.applyShellOpacityNow(var1, var2, var4);
         }
      }
   }

   @Override
   protected void applyShellOpacityNow(Player var1, String var2, int var3) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var4 = "editor_shell_" + var2;
         String var5 = var4 + "_outline";
         int var6 = Math.max(5, Math.min(255, var3));
         this.setHudOpacityIfExists(var1, var4, var6);
         this.setHudOpacityIfExists(var1, var4 + "_r_core", var6);
         this.setHudOpacityIfExists(var1, var4 + "_r_top", var6);
         this.setHudOpacityIfExists(var1, var4 + "_r_bottom", var6);
         this.setHudOpacityIfExists(var1, var4 + "_r_left", var6);
         this.setHudOpacityIfExists(var1, var4 + "_r_right", var6);
         this.setHudOpacityIfExists(var1, var4 + "_r_tl", var6);
         this.setHudOpacityIfExists(var1, var4 + "_r_tr", var6);
         this.setHudOpacityIfExists(var1, var4 + "_r_bl", var6);
         this.setHudOpacityIfExists(var1, var4 + "_r_br", var6);
         this.setHudOpacityIfExists(var1, var5, var6);
         this.setHudOpacityIfExists(var1, var5 + "_r_core", var6);
         this.setHudOpacityIfExists(var1, var5 + "_r_top", var6);
         this.setHudOpacityIfExists(var1, var5 + "_r_bottom", var6);
         this.setHudOpacityIfExists(var1, var5 + "_r_left", var6);
         this.setHudOpacityIfExists(var1, var5 + "_r_right", var6);
         this.setHudOpacityIfExists(var1, var5 + "_r_tl", var6);
         this.setHudOpacityIfExists(var1, var5 + "_r_tr", var6);
         this.setHudOpacityIfExists(var1, var5 + "_r_bl", var6);
         this.setHudOpacityIfExists(var1, var5 + "_r_br", var6);
      }
   }

   @Override
   protected void beginShellOpacityBatch(EditorSession var1) {
      if (var1 != null) {
         var1.shellOpacityBatchDepth++;
      }
   }

   @Override
   protected void endShellOpacityBatch(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.shellOpacityBatchDepth > 0) {
         var2.shellOpacityBatchDepth--;
         if (var2.shellOpacityBatchDepth <= 0) {
            if (!var2.pendingShellOpacity.isEmpty()) {
               LinkedHashMap var3 = new LinkedHashMap<>(var2.pendingShellOpacity);
               var2.pendingShellOpacity.clear();

               for (Map.Entry<?, ?> var5 : ((Map<?, ?>)var3).entrySet()) {
                  Integer var6 = (Integer)var5.getValue();
                  this.applyShellOpacityNow(var1, (String)var5.getKey(), var6 == null ? 0 : var6);
               }
            }
         }
      }
   }

   @Override
   protected void setHudOpacityIfExists(Player var1, String var2, int var3) {
      EditorHudRenderer.setHudOpacityIfExists(this.hudService, var1, var2, var3);
   }

   @Override
   protected int getShellOpacity(Player var1, EditorSession var2, String var3, int var4) {
      int var5 = Math.max(5, Math.min(255, var4));
      if (var1 != null && var3 != null && !var3.isBlank()) {
         if (var2 != null) {
            Integer var6 = var2.pendingShellOpacity.get(var3);
            if (var6 != null) {
               return Math.max(5, Math.min(255, var6));
            }
         }

         String var8 = "editor_shell_" + var3;
         Entity var7 = this.hudService.getHud(var1, var8);
         return var7 != null ? Math.max(5, Math.min(255, this.hudService.getHudOpacity(var7))) : var5;
      } else {
         return var5;
      }
   }

   protected void updateSidebarTabHover(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         EditorSidebarTab var7 = null;
         if (this.isInsideShellBlock(var2, "properties_hitbox", var3, var5)) {
            var7 = EditorSidebarTab.PROPERTIES;
         } else if (this.isInsideShellBlock(var2, "design_hitbox", var3, var5)) {
            var7 = EditorSidebarTab.DESIGN;
         } else if (this.isInsideSidebarLayersHitbox(var2, var3, var5)) {
            var7 = EditorSidebarTab.LAYERS;
         }

         if (var2.rightSidebarHoverTab != var7) {
            var2.rightSidebarHoverTab = var7;
            this.updateSidebarTabVisualState(var1, var2, false, false);
         }
      }
   }

   @Override
   protected void setRightSidebarTab(Player var1, EditorSession var2, EditorSidebarTab var3, boolean var4) {
      if (var1 != null && var2 != null) {
         EditorSidebarTab var5 = var3 == null ? EditorSidebarTab.LAYERS : var3;
         boolean var6 = var2.rightSidebarTab != var5;
         var2.rightSidebarTab = var5;
         var2.rightSidebarHoverTab = null;
         if (var6) {
            var2.pendingPropertyField = null;
            this.clearPendingItemDesignInventoryPick(var2);
            var2.pendingActionTargetId = null;
            var2.pendingActionIndex = -1;
            this.stopOpacitySliderDrag(var2, true);
            this.resetSidebarFieldDrag(var1, var2);
            this.clearActionsReorderState(var2);
            this.clearActionsReorderGhostHud(var1, var2);
            if (var2.cursorActionsDropdownVisible) {
               this.setCursorActionsDropdownVisible(var1, var2, false);
            }

            if (var2.cursorActionsEditDropdownVisible) {
               this.setCursorActionsEditDropdownVisible(var1, var2, false);
            }

            if (var2.keyframeDropdownVisible) {
               this.setAnimationTimelineKeyframeDropdownVisible(var1, var2, false);
            }

            if (var2.keyframeTimelineDropdownVisible) {
               this.setAnimationTimelineTimelineDropdownVisible(var1, var2, false);
            }

            this.playEditorSfx(var1, var2, "tab-switched");
         }

         this.beginShellOpacityBatch(var2);

         try {
            this.updateSidebarTabVisualState(var1, var2, var4 && var6, false);
            this.updateEditorPropertiesSidebar(var1, var2);
         } finally {
            this.endShellOpacityBatch(var1, var2);
         }
      }
   }

   @Override
   protected void updateSidebarTabVisualState(Player var1, EditorSession var2, boolean var3, boolean var4) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         EditorSidebarTab var5 = var2.rightSidebarTab == null ? EditorSidebarTab.LAYERS : var2.rightSidebarTab;
         var2.rightSidebarTab = var5;
         EditorSidebarTab var6 = var2.rightSidebarHoverTab;
         String var7 = "<#ffffff>";
         String var8 = var5 != EditorSidebarTab.DESIGN && var6 != EditorSidebarTab.DESIGN ? "<#bdbdbd>" : "<#ffffff>";
         String var9 = var5 != EditorSidebarTab.LAYERS && var6 != EditorSidebarTab.LAYERS ? "<#bdbdbd>" : "<#ffffff>";
         String var10;
         if (this.isActionsSidebarMode(var2)) {
            var10 = var7 + "\ueef0";
         } else if (this.isKeyframePropertiesSidebarMode(var2)) {
            var10 = var7 + "\ue60a";
         } else {
            var10 = var7 + "\ue600";
         }

         this.setShellText(var1, "editor_properties_text", var10);
         this.setShellText(var1, "properties_text", var10);
         this.setShellText(var1, "design_text", var8 + "Design");
         this.setShellText(var1, "layers_text", var9 + "Layers");
         if (var6 == null) {
            this.updateSidebarTabPanels(var1, var2, var5, var4);
         }

         boolean var11 = var5 == EditorSidebarTab.DESIGN && this.isTextDesignSidebarMode(var2);
         this.syncDesignTextAlignControlsVisibility(var1, var2, var11);
         String var12 = this.resolveSidebarIndicatorId(var2);
         EditorRect var13 = this.findShellBlockRect(var2, var12);
         double var14 = this.resolveSidebarIndicatorX(var2, var5, var12);
         double var16 = var13 == null ? this.resolveSidebarIndicatorY(var12) : var13.y;
         double var18 = var13 == null ? 40.0 : var13.width;
         double var20 = var13 == null ? 2.0 : var13.height;
         if (var13 == null || Math.abs(var13.x - var14) > 1.0E-4) {
            if (var3) {
               this.animateSidebarCurrentOption(var1, var2, var12, var14, var16);
            } else if (var2.rightSidebarHoverTab == null) {
               this.moveShellElement(var1, var2, var12, var14, var16, var18, var20);
            }
         }
      }
   }

   protected void animateSidebarCurrentOption(Player var1, EditorSession var2, String var3, double var4, double var6) {
      if (var1 != null && var2 != null) {
         EditorRect var8 = this.findShellBlockRect(var2, var3);
         double var9 = var8 == null ? var4 : var8.x;
         double var11 = var8 == null ? var6 : var8.y;
         double var13 = var8 == null ? 40.0 : var8.width;
         double var15 = var8 == null ? 2.0 : var8.height;
         if (Math.abs(var9 - var4) < 1.0E-4) {
            this.moveShellElement(var1, var2, var3, var4, var11, var13, var15);
         } else {
            int var17 = ++var2.rightSidebarIndicatorAnimationToken;
            int[] var18 = new int[]{0};
            PlatformCompat.runEntityTimer(this.plugin, var1, 1L, 1L, var16 -> {
               if (!var1.isOnline()) {
                  var16.cancel();
               } else {
                  EditorSession var17x = this.editorSessions.get(var1.getUniqueId());
                  if (var17x == var2 && var2.rightSidebarIndicatorAnimationToken == var17) {
                     var18[0]++;
                     double var18x = Math.min(1.0, (double)var18[0] / 6.0);
                     double var20 = 1.0 - Math.pow(1.0 - var18x, 3.0);
                     double var22 = var9 + (var4 - var9) * var20;
                     this.moveShellElement(var1, var2, var3, var22, var11, var13, var15);
                     if (var18[0] >= 6) {
                        var16.cancel();
                     }
                  } else {
                     var16.cancel();
                  }
               }
            });
         }
      }
   }

   protected void updateSidebarTabPanels(Player var1, EditorSession var2, EditorSidebarTab var3, boolean var4) {
      if (var1 != null && var2 != null && var3 != null) {
         int var5 = ++var2.rightSidebarPanelTransitionToken;
         String var6 = this.resolvePropertiesTabPanelId(var2);
         String var7 = this.resolveDesignTabPanelId(var2);
         boolean var8 = false;
         if (var3 != EditorSidebarTab.PROPERTIES || !"properties".equals(var6)) {
            var8 |= this.setSidebarPanelVisibleTracked(var1, var2, "properties", false);
         }

         if (var3 != EditorSidebarTab.PROPERTIES || !"keyframe_properties".equals(var6)) {
            var8 |= this.setSidebarPanelVisibleTracked(var1, var2, "keyframe_properties", false);
         }

         if (var3 != EditorSidebarTab.PROPERTIES || !"keyframe_properties_rotation".equals(var6)) {
            var8 |= this.setSidebarPanelVisibleTracked(var1, var2, "keyframe_properties_rotation", false);
         }

         if (var3 != EditorSidebarTab.PROPERTIES || !"keyframe_properties_opacity".equals(var6)) {
            var8 |= this.setSidebarPanelVisibleTracked(var1, var2, "keyframe_properties_opacity", false);
         }

         if (var3 != EditorSidebarTab.PROPERTIES || !"actions".equals(var6)) {
            var8 |= this.setSidebarPanelVisibleTracked(var1, var2, "actions", false);
         }

         if (var3 != EditorSidebarTab.DESIGN || !"design".equals(var7)) {
            var8 |= this.setSidebarPanelVisibleTracked(var1, var2, "design", false);
         }

         if (var3 != EditorSidebarTab.DESIGN || !"item_design".equals(var7)) {
            var8 |= this.setSidebarPanelVisibleTracked(var1, var2, "item_design", false);
         }

         if (var3 != EditorSidebarTab.LAYERS) {
            var8 |= this.setSidebarPanelVisibleTracked(var1, var2, "layers", false);
         }
         String var9 = switch (var3) {
            case PROPERTIES -> var6;
            case DESIGN -> var7;
            case LAYERS -> "layers";
         };
         if (!var4) {
            var8 |= this.setSidebarPanelVisibleTracked(var1, var2, var9, true);
            if (var8) {
               this.updateEditorPropertiesSidebar(var1, var2);
            }
         } else {
            PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
               if (var1.isOnline()) {
                  EditorSession var4x = this.editorSessions.get(var1.getUniqueId());
                  if (var4x == var2 && var2.rightSidebarPanelTransitionToken == var5) {
                     if (var2.editMode && var2.previewMode) {
                        EditorSidebarTab var5x = var2.rightSidebarTab == null ? EditorSidebarTab.LAYERS : var2.rightSidebarTab;
                        String var6x = this.resolvePropertiesTabPanelId(var2);
                        String var7x = this.resolveDesignTabPanelId(var2);

                        String var8x = switch (var5x) {
                           case PROPERTIES -> var6x;
                           case DESIGN -> var7x;
                           case LAYERS -> "layers";
                        };
                        this.beginShellOpacityBatch(var2);

                        try {
                           boolean var9x = this.setSidebarPanelVisibleTracked(var1, var2, var8x, true);
                           if (var9x) {
                              this.updateEditorPropertiesSidebar(var1, var2);
                           }
                        } finally {
                           this.endShellOpacityBatch(var1, var2);
                        }
                     }
                  }
               }
            }, 1L);
         }
      }
   }

   @Override
   protected void setSidebarPanelVisible(Player var1, EditorSession var2, String var3, boolean var4) {
      this.setSidebarPanelVisibleTracked(var1, var2, var3, var4);
   }

   @Override
   protected boolean setSidebarPanelVisibleTracked(Player var1, EditorSession var2, String var3, boolean var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         boolean var5 = this.shouldKeepShellPanelSpawned(var3);
         String var6 = this.resolvePanelComponentNameFallback(var3);
         boolean var7 = !var6.isBlank();
         Boolean var8 = var2.sidebarPanelVisibilityStates.get(var3);
         boolean var9 = var8 == null || var8 != var4;
         if (!var9) {
            return false;
         } else {
            ConfigurationSection var10 = this.findShellBlockSection(var2, var3);
            if (var10 == null) {
               if (!var7) {
                  return false;
               } else {
                  this.setSidebarPanelVisibleByComponentName(var1, var2, var6, var4, var5);
                  var2.sidebarPanelVisibilityStates.put(var3, Boolean.valueOf(var4));
                  return true;
               }
            } else {
               boolean var11 = false;
               if (var4 && (var7 || !this.isShellPanelSpawned(var1, var2, var10, var3))) {
                  this.spawnShellPanelHud(var1, var2, var10, var3);
                  var11 = true;
               }

               String var12 = this.firstNonBlank(new String[]{var10.getString("__editor_target_path")});
               if (var12.isBlank()) {
                  if (this.setSidebarPanelVisibleByComponentName(var1, var2, var6, var4, var5)) {
                     var2.sidebarPanelVisibilityStates.put(var3, Boolean.valueOf(var4));
                     return var9 || var11;
                  } else {
                     if (!var4 && !var5) {
                        this.clearShellElementHud(var1, var2, var3);
                     } else {
                        int var20 = this.readOpacity(var10, 255);
                        this.setShellOpacity(var1, var3, var4 ? var20 : 0);
                     }

                     var2.sidebarPanelVisibilityStates.put(var3, Boolean.valueOf(var4));
                     return var9 || var11;
                  }
               } else {
                  this.ensureShellBlockCache(var2);
                  boolean var13 = false;

                  for (Map.Entry<?, ?> var15 : ((Map<?, ?>)var2.shellSectionCache).entrySet()) {
                     if (var15 != null && var15.getKey() != null && var15.getValue() != null) {
                        String var16 = (String)var15.getKey();
                        ConfigurationSection var17 = (ConfigurationSection)var15.getValue();
                        String var18 = this.firstNonBlank(new String[]{var17.getString("__editor_target_path")});
                        if (this.belongsToSidebarPanel(var12, var18)) {
                           var13 = true;
                           if (!var4 && !var5) {
                              this.clearShellElementHud(var1, var2, var16);
                           } else {
                              int var19 = this.readOpacity(var17, 255);
                              this.setShellOpacity(var1, var16, var4 ? var19 : 0);
                           }
                        }
                     }
                  }

                  if (!var13) {
                     this.setSidebarPanelVisibleByComponentName(var1, var2, var6, var4, var5);
                  }

                  var2.sidebarPanelVisibilityStates.put(var3, Boolean.valueOf(var4));
                  return var9 || var11;
               }
            }
         }
      } else {
         return false;
      }
   }

   @Override
   protected void spawnShellPanelHud(Player var1, EditorSession var2, ConfigurationSection var3, String var4) {
      if (var1 != null && var2 != null && var3 != null && var4 != null && !var4.isBlank() && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         String var5 = this.firstNonBlank(new String[]{var3.getString("__editor_target_path")});
         String var6 = this.resolvePanelComponentNameFallback(var4);
         this.ensureShellBlockCache(var2);

         for (Map.Entry<?, ?> var8 : ((Map<?, ?>)var2.shellSectionCache).entrySet()) {
            if (var8 != null && var8.getKey() != null && var8.getValue() != null) {
               String var9 = (String)var8.getKey();
               ConfigurationSection var10 = (ConfigurationSection)var8.getValue();
               String var11 = this.firstNonBlank(new String[]{var10.getString("type"), "block"}).toLowerCase(Locale.ROOT);
               if (this.isRenderableBlockType(var11) && !this.isEditorToolShellBlockId(var9)) {
                  String var12 = this.firstNonBlank(new String[]{var10.getString("__editor_target_path")});
                  String var13 = this.firstNonBlank(new String[]{var10.getString("__editor_component_name")});
                  boolean var14 = var5.isBlank() ? var4.equals(var9) : this.belongsToSidebarPanel(var5, var12);
                  boolean var15 = !var6.isBlank() && var6.equalsIgnoreCase(var13);
                  if (var14 || var15) {
                     String var16 = "editor_shell_" + var9;
                     if (this.shouldSkipEditorShellBlockForSpawnRetoggle(var2, var9)) {
                        this.clearOutlineHud(var1, var16);
                        this.removeBaseHud(var1, var16);
                        this.removeRoundedParts(var1, var16);
                        if (var2.shellRuntimeRects != null) {
                           var2.shellRuntimeRects.remove(var9);
                        }
                     } else if (!this.shouldRenderEditorShellBlock(var2, var9, var10)) {
                        this.clearOutlineHud(var1, var16);
                        this.removeBaseHud(var1, var16);
                        this.removeRoundedParts(var1, var16);
                        if (var2.shellRuntimeRects != null) {
                           var2.shellRuntimeRects.remove(var9);
                        }
                     } else {
                        double var17 = this.readDouble(var10, "position.x", "x", 0.0);
                        double var19 = this.readDouble(var10, "position.y", "y", 0.0);
                        double var21 = this.readDouble(var10, "layer", "layer", this.readDouble(var10, "size.depth", "depth", 0.0));
                        double var23 = this.readDouble(var10, "size.width", "width", this.readDouble(var10, "scale.width", "width", 20.0));
                        double var25 = this.readDouble(var10, "size.height", "height", this.readDouble(var10, "scale.height", "height", 20.0));
                        boolean var27 = var23 < 0.0;
                        boolean var28 = var25 < 0.0;
                        double var29 = Math.max(1.0, Math.abs(var23));
                        double var31 = Math.max(1.0, Math.abs(var25));
                        double var33 = this.readDouble(var10, "rotation", "rotate", 0.0);
                        int var35 = this.readOpacity(var10, 255);
                        var35 = this.resolveShellBlockInitialOpacity(var2, var9, var10, var35);
                        TextAlignment var36 = "text".equals(var11) ? this.readTextAlignment(var10) : TextAlignment.CENTER;
                        int var37 = "text".equals(var11) ? this.readTextWrapLineWidth(var10) : 200;
                        boolean var38 = "text".equals(var11) && "opacity_circle".equals(var9);
                        double var39 = "text".equals(var11) && !var38 ? this.toInternalTextTopY(var19, var31) : var19;
                        if ("hitbox".equals(var11)) {
                           this.clearOutlineHud(var1, var16);
                           this.removeBaseHud(var1, var16);
                           this.removeRoundedParts(var1, var16);
                           if (var2.shellRuntimeRects != null) {
                              var2.shellRuntimeRects.put(var9, new EditorRect(var17, var19, var29, var31));
                           }
                        } else {
                           boolean var41 = this.shouldSpawnOpacityVisual(var11, var35);
                           boolean var42 = !var41 && this.shouldKeepHiddenShellBlockSpawned(var2, var9, var10, var11);
                           if (!var41 && !var42) {
                              this.clearOutlineHud(var1, var16);
                              this.removeBaseHud(var1, var16);
                              this.removeRoundedParts(var1, var16);
                              if (var2.shellRuntimeRects != null) {
                                 var2.shellRuntimeRects.put(var9, new EditorRect(var17, var19, var29, var31));
                              }
                           } else {
                              double var43 = "text".equals(var11) ? this.applyTextAlignmentOffset(var17, var29, var36) : var17;
                              HudPositionCalculator.Placement var45 = this.positionCalculator.calculateBoxPlacement(var43, var39, var21, var29, var31);
                              Vector var46 = var45.location();
                              Vector var47 = var45.scale();
                              String var48;
                              if ("text".equals(var11)) {
                                 var48 = this.firstNonBlank(new String[]{var10.getString("text"), ""});
                              } else {
                                 var48 = this.firstNonBlank(new String[]{var10.getString("unicode"), var10.getString("text"), "█"});
                              }

                              String var49 = this.firstNonBlank(new String[]{var10.getString("color"), var10.getString("style.color")});
                              if ("editor_color_1".equalsIgnoreCase(var9)) {
                                 var49 = this.firstNonBlank(new String[]{var2.editorColor1, var49});
                              } else if ("editor_color_2".equalsIgnoreCase(var9)) {
                                 var49 = this.firstNonBlank(new String[]{var2.editorColor2, var49});
                              }

                              var48 = this.withHexPrefix(var48, var49);
                              String var50 = this.applyPlaceholderApi(var1, var48);
                              var50 = this.applyPreferredFont(var50, var10, !"text".equals(var11));
                              double var51 = "text".equals(var11) ? 0.0 : this.readOutlineSize(var10);
                              String var53 = "text".equals(var11) ? "" : this.readOutlineColor(var10);
                              String var54 = !"text".equals(var11) && var51 > 1.0E-4 ? this.applyOutlineColor(var50, var53) : null;
                              if (this.isRoundedType(var11)) {
                                 boolean var55 = this.isDirectionalRoundedType(var11);
                                 double var56 = this.readRoundedRadius(var10, var29, var31);
                                 String var58 = this.readRoundedCornerUnicode(var10);
                                 String var59 = var55 ? this.readRoundedCornerUnicodeTopLeft(var10) : "";
                                 String var60 = var55 ? this.readRoundedCornerUnicodeTopRight(var10) : "";
                                 String var61 = var55 ? this.readRoundedCornerUnicodeBottomRight(var10) : "";
                                 String var62 = var55 ? this.readRoundedCornerUnicodeBottomLeft(var10) : "";
                                 double var63 = this.readRoundedTopLeftOffsetX(var10);
                                 double var65 = this.readRoundedTopLeftOffsetY(var10);
                                 double var67 = this.readRoundedTopRightOffsetX(var10);
                                 double var69 = this.readRoundedTopRightOffsetY(var10);
                                 double var71 = this.readRoundedBottomRightOffsetX(var10);
                                 double var73 = this.readRoundedBottomRightOffsetY(var10);
                                 double var75 = this.readRoundedBottomLeftOffsetX(var10);
                                 double var77 = this.readRoundedBottomLeftOffsetY(var10);
                                 double var79 = var17 + var29 / 2.0;
                                 double var81 = var39 + var31 / 2.0;
                                 this.renderRoundedOutline(
                                    var1,
                                    var16,
                                    var17,
                                    var39,
                                    var21,
                                    var29,
                                    var31,
                                    var54,
                                    var50,
                                    var35,
                                    var56,
                                    var58,
                                    var59,
                                    var60,
                                    var61,
                                    var62,
                                    var63,
                                    var65,
                                    var67,
                                    var69,
                                    var71,
                                    var73,
                                    var75,
                                    var77,
                                    var33,
                                    var27,
                                    var28,
                                    var51,
                                    var79,
                                    var81
                                 );
                                 this.renderRoundedBlock(
                                    var1,
                                    var16,
                                    var17,
                                    var39,
                                    var21,
                                    var29,
                                    var31,
                                    var50,
                                    var35,
                                    var56,
                                    var58,
                                    var59,
                                    var60,
                                    var61,
                                    var62,
                                    var63,
                                    var65,
                                    var67,
                                    var69,
                                    var71,
                                    var73,
                                    var75,
                                    var77,
                                    var33,
                                    var27,
                                    var28,
                                    var79,
                                    var81
                                 );
                              } else {
                                 this.upsertHud(var1, var16, var46, var47, var50, var35, var36, var37);
                                 this.applyElementTransformById(var1, var16, var33, var27, var28);
                                 if ("text".equals(var11)) {
                                    this.clearOutlineHud(var1, var16);
                                 } else {
                                    double var86 = var43 + var29 / 2.0;
                                    double var57 = var39 + var31 / 2.0;
                                    this.renderSimpleOutline(
                                       var1, var16, var43, var39, var21, var29, var31, var54, var35, var36, var33, var27, var28, var51, var86, var57
                                    );
                                 }
                              }

                              if (var2.shellRuntimeRects != null) {
                                 var2.shellRuntimeRects.put(var9, new EditorRect(var17, var19, var29, var31));
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   protected boolean isShellElementHudPresent(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = "editor_shell_" + var2;
         return this.hudService.getHud(var1, var3) != null || this.hudService.getHud(var1, var3 + "_r_core") != null;
      } else {
         return false;
      }
   }

   @Override
   protected boolean isShellPanelSpawned(Player var1, EditorSession var2, ConfigurationSection var3, String var4) {
      if (var1 == null || var2 == null || var3 == null || var4 == null || var4.isBlank()) {
         return false;
      } else if (this.isShellElementHudPresent(var1, var4)) {
         return true;
      } else {
         String var5 = this.firstNonBlank(new String[]{var3.getString("__editor_target_path")});
         return var5.isBlank() ? false : this.hasShellPanelMemberHud(var1, var2, var5);
      }
   }

   protected boolean hasShellPanelMemberHud(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         this.ensureShellBlockCache(var2);

         for (Map.Entry<?, ?> var5 : ((Map<?, ?>)var2.shellSectionCache).entrySet()) {
            if (var5 != null && var5.getKey() != null && var5.getValue() != null) {
               String var6 = (String)var5.getKey();
               ConfigurationSection var7 = (ConfigurationSection)var5.getValue();
               String var8 = this.firstNonBlank(new String[]{var7.getString("__editor_target_path")});
               if (this.belongsToSidebarPanel(var3, var8)) {
                  String var9 = this.firstNonBlank(new String[]{var7.getString("type"), "block"}).toLowerCase(Locale.ROOT);
                  if (!"hitbox".equals(var9) && this.isShellElementHudPresent(var1, var6)) {
                     return true;
                  }
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   @Override
   protected void clearShellElementHud(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var3 != null && !var3.isBlank()) {
         String var4 = "editor_shell_" + var3;
         this.clearOutlineHud(var1, var4);
         this.removeBaseHud(var1, var4);
         this.removeRoundedParts(var1, var4);
         if (var2 != null && var2.shellRuntimeRects != null) {
            var2.shellRuntimeRects.remove(var3);
         }
      }
   }

   @Override
   protected boolean belongsToSidebarPanel(String var1, String var2) {
      return var1 != null && !var1.isBlank() && var2 != null && !var2.isBlank()
         ? var2.equals(var1) || var2.startsWith(var1 + ".children") || var2.startsWith(var1 + ".component")
         : false;
   }

   protected String resolveSidebarIndicatorId(EditorSession var1) {
      return this.findShellBlockSection(var1, "selected_underline") != null ? "selected_underline" : "current_option";
   }

   protected double resolveSidebarIndicatorX(EditorSession var1, EditorSidebarTab var2, String var3) {
      if (!"selected_underline".equals(var3)) {
         return var2 == EditorSidebarTab.LAYERS ? 1800.0 : 1678.0;
      } else {
         double var4 = switch (var2) {
            case PROPERTIES -> 45.0;
            case DESIGN -> 149.0;
            case LAYERS -> 240.0;
         };
         EditorRect var6 = this.findShellBlockRect(var1, "right_sidebar");
         return var6 == null ? var4 : var6.x + var4;
      }
   }

   protected double resolveSidebarIndicatorY(String var1) {
      return "selected_underline".equals(var1) ? 45.0 : 140.0;
   }

   protected double resolveStoredSign(Map<String, Object> var1, String... var2) {
      if (var1 != null && var2 != null && var2.length != 0) {
         for (Object var6_raw : var2) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            if (var6 != null && !var6.isBlank() && this.hasMapPath(var1, var6)) {
               double var7 = this.readMapPathDouble(var1, var6, Double.NaN);
               if (Double.isFinite(var7) && Math.abs(var7) > 1.0E-4) {
                  return var7 < 0.0 ? -1.0 : 1.0;
               }
            }
         }

         return 1.0;
      } else {
         return 1.0;
      }
   }

   @Override
   protected boolean hasMapPath(Map<String, Object> var1, String var2) {
      return this.readMapPathValue(var1, var2) != null;
   }

   @Override
   protected double readMapPathDouble(Map<String, Object> var1, String var2, double var3) {
      Object var5 = this.readMapPathValue(var1, var2);
      return this.parseDouble(var5, var3);
   }

   @Override
   protected Object readMapPathValue(Map<String, Object> var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String[] var3 = MAP_PATH_SEGMENTS_CACHE.computeIfAbsent(var2, var0 -> var0.split("\\."));
         Object var4 = var1;

         for (Object var8_raw : var3) {
            String var8 = var8_raw != null ? var8_raw.toString() : null;
            if (!(var4 instanceof Map var9)) {
               return null;
            }

            Object var10 = var9.get(var8);
            if (var10 == null) {
               return null;
            }

            var4 = var10;
         }

         return var4;
      } else {
         return null;
      }
   }

   @Override
   protected double snapToGrid(EditorSession var1, double var2) {
      return this.snap1(var2);
   }

   protected void openColorPickerForSlot(Player var1, EditorSession var2, int var3) {
      if (var1 != null && var2 != null) {
         var2.colorPickerSlot = var3;
         String var4 = var3 == 1 ? var2.editorColor1 : var2.editorColor2;
         var4 = this.firstNonBlank(new String[]{var4, "ffffff"});
         boolean var5 = var2.colorPickerVisible;
         var2.colorPickerVisible = true;
         if (!var5) {
            this.beginShellOpacityBatch(var2);

            try {
               this.setSidebarPanelVisible(var1, var2, "colorpicker_ui", true);
               this.updateSidebarTabVisualState(var1, var2, false, false);
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }
         }

         this.syncColorPickerToColor(var1, var2, var4);
      }
   }

   protected void closeColorPicker(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.colorPickerVisible) {
         var2.colorPickerVisible = false;
         var2.colorPickerGradDragActive = false;
         var2.colorPickerHueDragActive = false;
         var2.colorPickerHexInputPending = false;
         this.beginShellOpacityBatch(var2);

         try {
            this.setSidebarPanelVisible(var1, var2, "colorpicker_ui", false);
            this.updateSidebarTabVisualState(var1, var2, false, false);
         } finally {
            this.endShellOpacityBatch(var1, var2);
         }
      }
   }

   protected void syncColorPickerToColor(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String var4 = this.normalizeHexColor(var3);
         if (var4.length() != 6) {
            var4 = "ffffff";
         }

         ColorPickerManager.PickerCoordinates var5 = this.colorPickerManager.resolvePickerCoordinatesFromHex(var4);
         var2.colorPickerHueAbsY = var5.hueAbsY();
         var2.colorPickerGradAbsX = var5.gradAbsX();
         var2.colorPickerGradAbsY = var5.gradAbsY();
         this.moveColorPickerGradientElements(var1, var2, var2.colorPickerGradAbsX, var2.colorPickerGradAbsY);
         this.moveColorPickerHueElements(var1, var2, var2.colorPickerHueAbsY);
         this.updateColorPickerDisplay(var1, var2, var4);
      }
   }

   protected void updateColorPickerDisplay(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null) {
         String var4 = this.normalizeHexColor(this.firstNonBlank(new String[]{var3, "ffffff"}));
         if (var4.length() != 6) {
            var4 = "ffffff";
         }

         String var5 = this.colorPickerManager.resolveHueHex(var2.colorPickerHueAbsY);
         this.setShellText(var1, "colorpicker_maincolor", "<#" + var5 + ">\ue1a6");
         this.setShellText(var1, "colorpicker_colorchange1_core", "<#" + var4 + ">\ue1a5");
         String var6 = var2.colorPickerHexInputPending ? "..." : "#" + var4.toUpperCase(Locale.ROOT);
         this.setShellText(var1, "colorpicker_hexcolor", var6);
         this.setShellColor(var1, var2, "colorpicker_colordisplay", var4);
      }
   }

   protected void updateColorPickerGradientDrag(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         var2.colorPickerGradAbsX = this.colorPickerManager.clampGradientLogicalX(var3);
         var2.colorPickerGradAbsY = this.colorPickerManager.clampGradientLogicalY(var5);
         this.moveColorPickerGradientElements(var1, var2, var2.colorPickerGradAbsX, var2.colorPickerGradAbsY);
         this.applyColorPickerResult(var1, var2);
      }
   }

   protected void updateColorPickerHueDrag(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         var2.colorPickerHueAbsY = this.colorPickerManager.clampHueLogicalY(var5);
         this.moveColorPickerHueElements(var1, var2, var2.colorPickerHueAbsY);
         this.applyColorPickerResult(var1, var2);
      }
   }

   protected void moveColorPickerGradientElements(Player var1, EditorSession var2, double var3, double var5) {
      double var7 = var3 + -12.0;
      double var9 = var5 + 3.0;
      this.moveShellElement(var1, var2, "colorpicker_colorchange1", var7, var9, 64.0, 64.0);
      this.moveShellElement(var1, var2, "colorpicker_colorchange1_core", var7, var9, 64.0, 64.0);
      this.moveShellElement(var1, var2, "colorpicker_colorchange1_hitbox", var7, var9 + -11.0, 19.0, 19.0);
   }

   protected void moveColorPickerHueElements(Player var1, EditorSession var2, double var3) {
      double var5 = var3 + -4.0;
      this.moveShellElement(var1, var2, "colorpicker_colorchange2", 325.0, var5, 64.0, 64.0);
      this.moveShellElement(var1, var2, "colorpicker_colorchange2_hitbox", 325.0, var5 + -3.0, 14.0, 16.0);
   }

   protected boolean isInsideColorPickerGradientArea(double var1, double var3) {
      return this.colorPickerManager.isInsideGradientArea(var1, var3);
   }

   protected boolean isInsideColorPickerHueArea(double var1, double var3) {
      return this.colorPickerManager.isInsideHueArea(var1, var3);
   }

   protected void applyColorPickerResult(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         String var3 = this.computeColorFromPickerIndicators(var2);
         if (var2.colorPickerSlot == 1) {
            var2.editorColor1 = var3;
         } else {
            var2.editorColor2 = var3;
         }

         this.updateColorPickerDisplay(var1, var2, var3);
         this.setShellColor(var1, var2, var2.colorPickerSlot == 1 ? "editor_color_1" : "editor_color_2", var3);
         this.notifyColorPickerColorsChanged(var1, var2);
      }
   }

   protected String computeColorFromPickerIndicators(EditorSession var1) {
      return this.colorPickerManager.computeColorFromIndicators(var1);
   }

   protected void notifyColorPickerColorsChanged(Player var1, EditorSession var2) {
   }

   protected double[] hexToHsv(String var1) {
      return ColorUtils.hexToHsv(this.firstNonBlank(new String[]{var1}));
   }

   protected String hsvToHex(double var1, double var3, double var5) {
      return ColorUtils.hsvToHex(var1, var3, var5);
   }

   protected static final class ActionRowEntry {
      protected final int rawIndex;
      protected final String actionType;
      protected final String displayValue;
      protected final boolean dragPlaceholder;

      protected ActionRowEntry(int var1, String var2, String var3) {
         this(var1, var2, var3, false);
      }

      protected ActionRowEntry(int var1, String var2, String var3, boolean var4) {
         this.rawIndex = var1;
         this.actionType = var2 != null && !var2.isBlank() ? var2 : "command";
         this.displayValue = var3 != null && !var3.isBlank() ? var3 : "---";
         this.dragPlaceholder = var4;
      }
   }
}
