package dev.xqedii.ultimateUI.service.gui.editor.shell.managers;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.gui.model.HoverElement;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.model.EditorPropertyField;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.gui.model.EditorSidebarTab;
import dev.xqedii.ultimateUI.service.gui.model.EditorTool;
import dev.xqedii.ultimateUI.service.hud.HudPositionCalculator;
import dev.xqedii.ultimateUI.service.hud.HudService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay.TextAlignment;

public abstract class AnimationTimelineOperationsManagerBase extends LayersPanelManager {
   protected final AnimationTimelineManager animationTimelineManager = new AnimationTimelineManager();
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
   protected static final String SIDEBAR_INFO_ANCHOR_VALUE_ID = "editor_val_anchor";
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
   protected static final String SIDEBAR_INFO_ANCHOR_HITBOX_ID = "editor_val_anchor_hitbox";
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
   protected static final double IMAGE_SIDEBAR_LAYOUT_OFFSET_X = 29.0;
   protected static final double IMAGE_SIDEBAR_LAYOUT_OFFSET_Y = 248.0;
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

   protected AnimationTimelineOperationsManagerBase(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   protected abstract void applyAnimationTimelinePositionPreview(Player var1, EditorSession var2);

   protected abstract void clearAnimationTimelinePreviewOffset(Player var1, EditorSession var2);

   protected abstract void setAnimationTimelineKeyframeDropdownVisible(Player var1, EditorSession var2, boolean var3);

   protected abstract void setAnimationTimelineTimelineDropdownVisible(Player var1, EditorSession var2, boolean var3);

   protected abstract String resolveHoveredAnimationTimelineKeyframeDropdownTargetId(EditorSession var1, double var2, double var4);

   protected abstract String resolveHoveredAnimationTimelineTimelineDropdownTargetId(EditorSession var1, double var2, double var4);

   protected abstract void updateAnimationTimelineKeyframeDropdownHover(Player var1, EditorSession var2, double var3, double var5);

   protected abstract void updateAnimationTimelineTimelineDropdownHover(Player var1, EditorSession var2, double var3, double var5);

   protected abstract void moveAnimationTimelineContextDropdownTo(Player var1, EditorSession var2, String var3, double var4, double var6);

   protected abstract boolean isAnimationTimelineForceableSidebarField(EditorPropertyField var1);

   protected abstract String resolveAnimationTimelinePreservedInterpolationMode(Map<String, Object> var1, Object var2, int var3, int var4);

   protected abstract Object resolveAnimationTimelineForcedSidebarValue(
      EditorSession var1, String var2, String var3, int var4, EditorPropertyField var5, double var6, Object var8
   );

   protected abstract String resolveAnimationTimelineRowForSidebarField(EditorPropertyField var1);

   protected abstract void restoreAnimationTimelineRawTargetPositionToImplicitBase(EditorSession var1, String var2);

   protected abstract void restoreAnimationTimelineRawTargetToImplicitBase(EditorSession var1, String var2);

   protected abstract boolean animationTimelineValuesEqual(Object var1, Object var2);

   protected abstract void syncAnimationTimelinePreviewAppliedBoundsToCurrent(EditorSession var1, String var2);

   protected abstract EditorRect resolveAnimationTimelineImageGroupLogicalBounds(EditorSession var1, String var2);

   protected abstract EditorRect resolveAnimationTimelineStableBaseBounds(EditorSession var1, String var2);

   protected abstract double resolveAnimationTimelineStableBaseRotation(EditorSession var1, String var2);

   protected abstract double resolveAnimationTimelineStableBaseOpacity(EditorSession var1, String var2);

   protected abstract Double resolveAnimationTimelineExplicitTickZeroOpacity(EditorSession var1, String var2);

   protected abstract double interpolateAnimationTimelineChannel(TreeMap<Integer, Double> var1, double var2, double var4);

   protected abstract double interpolateAnimationTimelineChannel(TreeMap<Integer, Double> var1, double var2, double var4, Map<Integer, String> var6);

   protected abstract double[] interpolateAnimationTimelineVector2(TreeMap<Integer, double[]> var1, double var2, double var4, double var6);

   protected abstract double[] interpolateAnimationTimelineVector2(
      TreeMap<Integer, double[]> var1, double var2, double var4, double var6, Map<Integer, String> var8
   );

   protected abstract TreeMap<Integer, double[]> extractAnimationTimelineScalePositionPoints(TreeMap<Integer, double[]> var1);

   protected abstract double normalizeSidebarRotation(double var1);

   protected abstract int clampAnimationTimelineOpacityRaw(double var1);

   protected abstract String normalizeAnimationTimelineInterpolationMode(String var1);

   protected abstract String resolveAnimationTimelineKeyframeGlyph(EditorSession var1, String var2, String var3, int var4);

   protected abstract Map<Integer, String> readAnimationTimelineInterpolationModes(EditorSession var1, String var2, String var3);

   protected abstract EditorRect resolveAnimationTimelineImplicitBaseBounds(EditorSession var1, String var2);

   protected abstract void resetSidebarFieldDrag(Player var1, EditorSession var2);

   protected abstract void toggleSidebarItemDesignGlowing(Player var1, EditorSession var2);

   protected abstract void cycleSidebarHoverEffect(Player var1, EditorSession var2, boolean var3);

   protected abstract void toggleSidebarTextAlignment(Player var1, EditorSession var2);

   protected abstract boolean isTextDesignSidebarMode(EditorSession var1);

   protected abstract void cycleSidebarBorderRadiusMode(Player var1, EditorSession var2);

   protected abstract boolean isItemDesignSidebarMode(EditorSession var1);

   @Override
   protected abstract void updateEditorPropertiesSidebar(Player var1, EditorSession var2);

   @Override
   protected abstract void updateEditorKeyframeSidebar(Player var1, EditorSession var2);

   protected abstract void updateEditorDesignSidebar(Player var1, EditorSession var2);

   protected abstract int readTargetOpacity(EditorSession var1, String var2);

   protected abstract double readTargetRotation(EditorSession var1, String var2);

   protected abstract String readTargetColor(EditorSession var1, String var2);

   protected abstract String readTargetOutlineColor(EditorSession var1, String var2);

   protected abstract boolean isTargetOutlineColorAuto(EditorSession var1, String var2);

   protected abstract boolean setTargetOpacity(EditorSession var1, String var2, int var3);

   protected abstract boolean setTargetRotation(EditorSession var1, String var2, double var3);

   protected abstract boolean setTargetColor(EditorSession var1, String var2, String var3);

   protected abstract boolean setTargetHoverColor(EditorSession var1, String var2, String var3);

   protected abstract boolean setTargetOutlineColor(EditorSession var1, String var2, String var3);

   protected abstract boolean setTargetOutlineColorAuto(EditorSession var1, String var2);

   protected abstract AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig resolveHoverEffectRuntimeConfig(String var1);

   protected abstract String readTargetRawType(EditorSession var1, String var2);

   protected abstract String readTargetName(EditorSession var1, String var2);

   protected abstract void clearPendingItemDesignInventoryPick(EditorSession var1);

   protected abstract void setCursorActionsDropdownVisible(Player var1, EditorSession var2, boolean var3);

   protected abstract void setCursorActionsEditDropdownVisible(Player var1, EditorSession var2, boolean var3);

   protected abstract boolean isKeyframePropertiesSidebarMode(EditorSession var1);

   protected abstract boolean isActionsSidebarMode(EditorSession var1);

   protected abstract double snapToGrid(EditorSession var1, double var2);

   protected abstract String resolveRawTargetPathByTargetId(EditorSession var1, String var2);

   protected abstract void setRightSidebarTab(Player var1, EditorSession var2, EditorSidebarTab var3, boolean var4);

   protected abstract void updateSidebarTabVisualState(Player var1, EditorSession var2, boolean var3, boolean var4);

   protected abstract boolean setSidebarPanelVisibleByComponentName(Player var1, EditorSession var2, String var3, boolean var4, boolean var5);

   protected boolean handleAnimationTimelineClick(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 == null || var2 == null || var2.activeTool != EditorTool.ANIMATION) {
         return false;
      } else if (this.handleAnimationTimelineContextDropdownClick(var1, var2, var3, var5)) {
         return true;
      } else {
         boolean var7 = this.isInsideShellBlock(var2, "animation_timeline", var3, var5);
         boolean var8 = this.isInsideShellBlock(var2, "animation_slider_hitbox", var3, var5)
            || this.isInsideShellBlock(var2, "animation_accurate_slider_hitbox", var3, var5)
            || this.isInsideShellBlock(var2, "animation_accurate_slider_element", var3, var5);
         boolean var9 = this.isInsideShellBlock(var2, "animation_plus_hitbox_rotation", var3, var5);
         boolean var10 = this.isInsideShellBlock(var2, "animation_plus_hitbox_position", var3, var5);
         boolean var11 = this.isInsideShellBlock(var2, "animation_plus_hitbox_scale", var3, var5);
         boolean var12 = this.isInsideShellBlock(var2, "animation_plus_hitbox_row4", var3, var5);
         boolean var13 = this.isInsideShellBlock(var2, "animation_plus_hitbox_row5", var3, var5)
            || this.isInsideShellBlock(var2, "animation_plus_hitbox_row6", var3, var5);
         if (!var7 && !var8 && !var9 && !var10 && !var11 && !var12 && !var13) {
            return false;
         } else {
            this.syncAnimationTimelineUi(var1, var2);
            String var14 = this.resolveAnimationTimelineEditableTargetId(var2);
            AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef var15 = this.resolveAnimationTimelineHoveredKeyframe(var2, var3, var5);
            boolean var16 = var1.isSneaking();
            if (var15 != null && var14 != null && var2.animationTimelineComponentVisible) {
               this.stopAnimationTimelineSliderDrag(var2);
               this.stopAnimationTimelineKeyframeDrag(var2, true);
               if (var16) {
                  this.toggleAnimationTimelineKeyframeSelection(var1, var2, var14, var15.rowKey(), var15.tick());
                  return true;
               } else {
                  double var17 = this.sliderTickToX(var2, var15.tick());
                  var2.animationTimelineKeyframeDragStartHitX = var3;
                  var2.animationTimelineKeyframeDragHitOffsetX = Double.isFinite(var17) ? var3 - var17 : 0.0;
                  boolean var19 = this.isAnimationTimelineKeyframeRefSelected(var2, var14, var15.rowKey(), var15.tick());
                  this.selectAnimationTimelineKeyframe(var1, var2, var14, var15.rowKey(), var15.tick(), true, var19);
                  return true;
               }
            } else if (var8) {
               this.clearAnimationTimelineSelectedKeyframeOnPanelClick(var1, var2, var14);
               var2.pendingPropertyField = null;
               this.stopOpacitySliderDrag(var2, true);
               this.resetSidebarFieldDrag(var1, var2);
               this.stopAnimationTimelineKeyframeDrag(var2, true);
               var2.moveDragActive = false;
               var2.previewPanActive = false;
               var2.marqueeSelectActive = false;
               var2.activeHandle = null;
               var2.resizeAnchorValid = false;
               var2.handlesCollapsed = false;
               if (var14 != null && !var14.isBlank() && this.isAnimationTimelineImageGroupRoot(var2, var14)) {
                  this.rerenderAnimationTimelineSelectionTarget(var1, var2, var14);
               }

               var2.animationTimelineSliderDragActive = true;
               this.updateAnimationTimelineSliderDrag(var1, var2, var3);
               return true;
            } else if (var14 == null || !var2.animationTimelineComponentVisible) {
               return var7;
            } else if (var9) {
               this.stopAnimationTimelineKeyframeDrag(var2, true);
               this.addAnimationTimelineKeyframe(var1, var2, var14, "rotation");
               return true;
            } else if (var10) {
               this.stopAnimationTimelineKeyframeDrag(var2, true);
               this.addAnimationTimelineKeyframe(var1, var2, var14, "position");
               return true;
            } else if (var11) {
               this.stopAnimationTimelineKeyframeDrag(var2, true);
               this.addAnimationTimelineKeyframe(var1, var2, var14, "scale");
               return true;
            } else if (var12) {
               this.stopAnimationTimelineKeyframeDrag(var2, true);
               this.addAnimationTimelineKeyframe(var1, var2, var14, "opacity");
               return true;
            } else if (var13) {
               return true;
            } else {
               this.stopAnimationTimelineKeyframeDrag(var2, true);
               this.clearAnimationTimelineSelectedKeyframeOnPanelClick(var1, var2, var14);
               return var7;
            }
         }
      }
   }

   protected boolean handleAnimationTimelineContextClick(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.activeTool == EditorTool.ANIMATION) {
         double var7 = var3 - var2.hitboxOffsetX;
         double var9 = var5 - var2.hitboxOffsetY;
         boolean var11 = this.isInsideShellBlock(var2, "animation_timeline", var3, var5) || this.isInsideShellBlock(var2, "animation_timeline", var7, var9);
         if (!var11) {
            this.closeAnimationTimelineContextDropdowns(var1, var2);
            return false;
         } else {
            this.syncAnimationTimelineUi(var1, var2);
            String var12 = this.resolveAnimationTimelineEditableTargetId(var2);
            if (var12 != null && var2.animationTimelineComponentVisible) {
               String var13 = this.resolveHoveredAnimationTimelineKeyframeDropdownTargetId(var2, var3, var5);
               if (!var13.isBlank()) {
                  return true;
               } else {
                  String var14 = this.resolveHoveredAnimationTimelineTimelineDropdownTargetId(var2, var3, var5);
                  if (!var14.isBlank()) {
                     return true;
                  } else if ((
                        !var2.keyframeDropdownVisible
                           || !this.isInsideShellBlock(var2, "keyframe_dropdown", var7, var9)
                              && !this.isInsideShellBlock(var2, "keyframe_dropdown", var3, var5)
                     )
                     && (
                        !var2.keyframeTimelineDropdownVisible
                           || !this.isInsideShellBlock(var2, "keyframe_timeline_dropdown", var7, var9)
                              && !this.isInsideShellBlock(var2, "keyframe_timeline_dropdown", var3, var5)
                     )) {
                     this.setFileDropdownVisible(var1, var2, false);
                     this.setEditDropdownVisible(var1, var2, false);
                     this.setSelectionDropdownVisible(var1, var2, false);
                     this.setLayerDropdownVisible(var1, var2, false);
                     this.setWindowDropdownVisible(var1, var2, false);
                     if (var2.cursorActionsDropdownVisible) {
                        this.setCursorActionsDropdownVisible(var1, var2, false);
                     }

                     if (var2.cursorActionsEditDropdownVisible) {
                        this.setCursorActionsEditDropdownVisible(var1, var2, false);
                     }

                     AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef var15 = this.resolveAnimationTimelineHoveredKeyframe(var2, var3, var5);
                     if (var15 != null) {
                        this.stopAnimationTimelineSliderDrag(var2);
                        this.stopAnimationTimelineKeyframeDrag(var2, true);
                        boolean var17 = this.isAnimationTimelineKeyframeRefSelected(var2, var12, var15.rowKey(), var15.tick());
                        this.selectAnimationTimelineKeyframe(var1, var2, var12, var15.rowKey(), var15.tick(), false, var17);
                        var2.animationTimelineContextTargetId = var12;
                        var2.animationTimelineContextRow = var15.rowKey();
                        var2.animationTimelineContextTick = this.clampAnimationTimelineTick(var15.tick());
                        if (var2.keyframeTimelineDropdownVisible) {
                           this.setAnimationTimelineTimelineDropdownVisible(var1, var2, false);
                        }

                        this.setAnimationTimelineKeyframeDropdownVisible(var1, var2, true);
                        this.moveAnimationTimelineContextDropdownTo(var1, var2, "keyframe_dropdown", var3, var5);
                        this.updateAnimationTimelineKeyframeDropdownHover(var1, var2, var3, var5);
                        return true;
                     } else {
                        String var16 = this.resolveAnimationTimelineContextRowByHit(var2, var3, var5);
                        if (var16.isBlank()) {
                           this.closeAnimationTimelineContextDropdowns(var1, var2);
                           this.clearAnimationTimelineSelectedKeyframeOnPanelClick(var1, var2, var12);
                           return true;
                        } else {
                           this.closeAnimationTimelineContextDropdowns(var1, var2);
                           this.clearAnimationTimelineSelectedKeyframeOnPanelClick(var1, var2, var12);
                           if (!this.hasAnimationTimelineKeyframeClipboard(var2)) {
                              return true;
                           } else {
                              var2.animationTimelineContextTargetId = var12;
                              var2.animationTimelineContextRow = var16;
                              var2.animationTimelineContextTick = this.sliderXToTick(var2, var3);
                              this.setAnimationTimelineTimelineDropdownVisible(var1, var2, true);
                              this.moveAnimationTimelineContextDropdownTo(var1, var2, "keyframe_timeline_dropdown", var3, var5);
                              this.updateAnimationTimelineTimelineDropdownHover(var1, var2, var3, var5);
                              return true;
                           }
                        }
                     }
                  } else {
                     return true;
                  }
               }
            } else {
               this.closeAnimationTimelineContextDropdowns(var1, var2);
               return true;
            }
         }
      } else {
         return false;
      }
   }

   protected boolean handleAnimationTimelineContextDropdownClick(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         double var7 = var3 - var2.hitboxOffsetX;
         double var9 = var5 - var2.hitboxOffsetY;
         if (var2.keyframeDropdownVisible) {
            String var11 = this.resolveHoveredAnimationTimelineKeyframeDropdownTargetId(var2, var3, var5);
            if (!var11.isBlank()) {
               this.setAnimationTimelineKeyframeDropdownVisible(var1, var2, false);
               this.playEditorSfx(var1, var2, "dropdown-item-clicked");
               this.handleAnimationTimelineContextDropdownAction(var1, var2, var11, false);
               return true;
            }

            if (this.isInsideShellBlock(var2, "keyframe_dropdown", var7, var9) || this.isInsideShellBlock(var2, "keyframe_dropdown", var3, var5)) {
               return true;
            }

            this.setAnimationTimelineKeyframeDropdownVisible(var1, var2, false);
         }

         if (var2.keyframeTimelineDropdownVisible) {
            String var12 = this.resolveHoveredAnimationTimelineTimelineDropdownTargetId(var2, var3, var5);
            if (!var12.isBlank()) {
               this.setAnimationTimelineTimelineDropdownVisible(var1, var2, false);
               this.playEditorSfx(var1, var2, "dropdown-item-clicked");
               this.handleAnimationTimelineContextDropdownAction(var1, var2, var12, true);
               return true;
            }

            if (this.isInsideShellBlock(var2, "keyframe_timeline_dropdown", var7, var9)
               || this.isInsideShellBlock(var2, "keyframe_timeline_dropdown", var3, var5)) {
               return true;
            }

            this.setAnimationTimelineTimelineDropdownVisible(var1, var2, false);
         }

         return false;
      } else {
         return false;
      }
   }

   protected boolean handleAnimationTimelineContextDropdownAction(Player var1, EditorSession var2, String var3, boolean var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String var5 = this.firstNonBlank(new String[]{var3}).trim().toLowerCase(Locale.ROOT);
         String var6 = var5.startsWith("dropdown_") ? var5.substring("dropdown_".length()) : var5;

         return switch (var6) {
            case "keyframe_edit_copy" -> this.copyAnimationTimelineSelectedKeyframe(var2);
            case "keyframe_edit_paste", "keyframe_timeline_paste" -> var4
            ? this.pasteAnimationTimelineClipboardToContext(var1, var2)
            : this.pasteAnimationTimelineClipboardToSelectedKeyframe(var1, var2);
            case "keyframe_delete" -> this.deleteAnimationTimelineSelectedKeyframes(var1, var2);
            case "keyframe_properties" -> {
               if (var2.rightSidebarTab != EditorSidebarTab.PROPERTIES) {
                  this.setRightSidebarTab(var1, var2, EditorSidebarTab.PROPERTIES, true);
               } else {
                  this.refreshSidebarAfterTimelineSelectionChange(var1, var2);
               }

               yield true;
            }
            default -> false;
         };
      } else {
         return false;
      }
   }

   @Override
   protected void closeAnimationTimelineContextDropdowns(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         if (var2.keyframeDropdownVisible) {
            this.setAnimationTimelineKeyframeDropdownVisible(var1, var2, false);
         }

         if (var2.keyframeTimelineDropdownVisible) {
            this.setAnimationTimelineTimelineDropdownVisible(var1, var2, false);
         }

         var2.animationTimelineContextTargetId = null;
         var2.animationTimelineContextRow = null;
         var2.animationTimelineContextTick = -1;
      }
   }

   protected boolean hasAnimationTimelineKeyframeClipboard(EditorSession var1) {
      if (var1 == null) {
         return false;
      } else {
         return !this.resolveAnimationTimelineClipboardValuesByRef(var1).isEmpty()
            ? true
            : this.cloneAnimationTimelineKeyframeValue(var1.animationTimelineKeyframeClipboardValue) != null;
      }
   }

   protected LinkedHashMap<Integer, Object> resolveAnimationTimelineClipboardValuesByTick(EditorSession var1) {
      LinkedHashMap var2 = new LinkedHashMap();
      if (var1 == null) {
         return var2;
      } else {
         if (!var1.animationTimelineKeyframeClipboardValuesByTick.isEmpty()) {
            TreeMap var3 = new TreeMap();

            for (Map.Entry<?, ?> var5 : ((Map<?, ?>)var1.animationTimelineKeyframeClipboardValuesByTick).entrySet()) {
               if (var5 != null && var5.getKey() != null) {
                  Object var6 = this.cloneAnimationTimelineKeyframeValue(var5.getValue());
                  if (var6 != null) {
                     var3.put(this.clampAnimationTimelineTick((Integer)var5.getKey()), var6);
                  }
               }
            }

            var2.putAll(var3);
            if (!var2.isEmpty()) {
               return var2;
            }
         }

         Object var7 = this.cloneAnimationTimelineKeyframeValue(var1.animationTimelineKeyframeClipboardValue);
         if (var7 != null) {
            var2.put(Integer.valueOf(this.clampAnimationTimelineTick(var1.animationTimelineKeyframeClipboardAnchorTick)), var7);
         }

         return var2;
      }
   }

   protected LinkedHashMap<String, Object> resolveAnimationTimelineClipboardValuesByRef(EditorSession var1) {
      LinkedHashMap var2 = new LinkedHashMap();
      if (var1 == null) {
         return var2;
      } else {
         if (!var1.animationTimelineKeyframeClipboardValuesByRef.isEmpty()) {
            TreeMap var3 = new TreeMap();

            for (Map.Entry<?, ?> var5 : ((Map<?, ?>)var1.animationTimelineKeyframeClipboardValuesByRef).entrySet()) {
               if (var5 != null) {
                  AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef var6 = this.parseAnimationTimelineSelectionRef((String)var5.getKey());
                  if (var6 != null) {
                     Object var7 = this.cloneAnimationTimelineKeyframeValue(var5.getValue());
                     if (var7 != null) {
                        TreeMap var8 = (TreeMap)var3.computeIfAbsent(var6.rowKey(), var0 -> new TreeMap());
                        var8.put(this.clampAnimationTimelineTick(var6.tick()), var7);
                     }
                  }
               }
            }

            for (Map.Entry<?, ?> var12 : ((Map<?, ?>)var3).entrySet()) {
               if (var12 != null && var12.getKey() != null && var12.getValue() != null && !((TreeMap)var12.getValue()).isEmpty()) {
                  for (Map.Entry<?, ?> var16 : ((Map<?, ?>)((TreeMap)var12.getValue())).entrySet()) {
                     if (var16 != null && var16.getKey() != null) {
                        String var17 = this.buildAnimationTimelineKeyframeRef((String)var12.getKey(), (Integer)var16.getKey());
                        if (!var17.isBlank()) {
                           var2.put(var17, var16.getValue());
                        }
                     }
                  }
               }
            }

            if (!var2.isEmpty()) {
               return var2;
            }
         }

         String var9 = this.firstNonBlank(new String[]{var1.animationTimelineKeyframeClipboardRow}).toLowerCase(Locale.ROOT);
         if (!ANIMATION_ROW_TO_BOX_ID.containsKey(var9)) {
            return var2;
         } else {
            for (Map.Entry<?, ?> var13 : ((Map<?, ?>)this.resolveAnimationTimelineClipboardValuesByTick(var1)).entrySet()) {
               if (var13 != null && var13.getKey() != null) {
                  String var15 = this.buildAnimationTimelineKeyframeRef(var9, (Integer)var13.getKey());
                  if (!var15.isBlank()) {
                     var2.put(var15, var13.getValue());
                  }
               }
            }

            return var2;
         }
      }
   }

   protected boolean copyAnimationTimelineSelectedKeyframe(EditorSession var1) {
      if (var1 != null && this.isAnimationTimelineKeyframeSelected(var1)) {
         String var2 = this.firstNonBlank(new String[]{var1.animationTimelineSelectedTargetId});
         String var3 = this.firstNonBlank(new String[]{var1.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT);
         int var4 = this.clampAnimationTimelineTick(var1.animationTimelineSelectedTick);
         if (!var2.isBlank() && ANIMATION_ROW_TO_BOX_ID.containsKey(var3)) {
            Map var5 = this.resolveRawTargetByTargetId(var1, var2);
            if (var5 == null) {
               return false;
            } else {
               LinkedHashMap var6 = this.resolveAnimationTimelineSelectedTicksByRow(var1, var2);
               if (var6.isEmpty()) {
                  var6.put(var3, List.of(var4));
               }

               TreeMap var7 = new TreeMap();

               for (Map.Entry<?, ?> var9 : ((Map<?, ?>)var6).entrySet()) {
                  if (var9 != null && var9.getKey() != null && var9.getValue() != null && !((List)var9.getValue()).isEmpty()) {
                     String var10 = this.firstNonBlank(new String[]{(String)var9.getKey()}).toLowerCase(Locale.ROOT);
                     if (ANIMATION_ROW_TO_BOX_ID.containsKey(var10)) {
                        Map var11 = this.readAnimationTimelineRowMap(var5, var10);
                        if (var11 != null && !var11.isEmpty()) {
                           TreeMap var12 = (TreeMap)var7.computeIfAbsent(var10, var0 -> new TreeMap());

                           for (int var14 : (List<Integer>)(List)var9.getValue()) {
                              Object var15 = this.readAnimationTimelineTickValue(var11, var14);
                              Object var16 = this.cloneAnimationTimelineKeyframeValue(var15);
                              if (var16 != null) {
                                 var12.put(this.clampAnimationTimelineTick(var14), var16);
                              }
                           }

                           if (var12.isEmpty()) {
                              var7.remove(var10);
                           }
                        }
                     }
                  }
               }

               LinkedHashMap var18 = new LinkedHashMap();

               for (Map.Entry<?, ?> var21 : ((Map<?, ?>)var7).entrySet()) {
                  if (var21 != null && var21.getKey() != null && var21.getValue() != null && !((TreeMap)var21.getValue()).isEmpty()) {
                     for (Map.Entry<?, ?> var25 : ((Map<?, ?>)((TreeMap)var21.getValue())).entrySet()) {
                        if (var25 != null && var25.getKey() != null) {
                           String var27 = this.buildAnimationTimelineKeyframeRef((String)var21.getKey(), (Integer)var25.getKey());
                           if (!var27.isBlank()) {
                              var18.put(var27, var25.getValue());
                           }
                        }
                     }
                  }
               }

               if (var18.isEmpty()) {
                  return false;
               } else {
                  String var20 = this.buildAnimationTimelineKeyframeRef(var3, var4);
                  if (var20.isBlank() || !var18.containsKey(var20)) {
                     var20 = (String)var18.keySet().iterator().next();
                  }

                  AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef var22 = this.parseAnimationTimelineSelectionRef(var20);
                  if (var22 == null) {
                     return false;
                  } else {
                     Object var24 = this.cloneAnimationTimelineKeyframeValue(var18.get(var20));
                     if (var24 == null) {
                        return false;
                     } else {
                        String var26 = this.firstNonBlank(new String[]{var22.rowKey()}).toLowerCase(Locale.ROOT);
                        int var28 = this.clampAnimationTimelineTick(var22.tick());
                        var1.animationTimelineKeyframeClipboardRow = var26;
                        var1.animationTimelineKeyframeClipboardValue = var24;
                        var1.animationTimelineKeyframeClipboardValuesByTick.clear();
                        var1.animationTimelineKeyframeClipboardValuesByRef.clear();
                        var1.animationTimelineKeyframeClipboardAnchorTick = var28;
                        var1.animationTimelineKeyframeClipboardAnchorRef = var20;

                        for (Map.Entry<?, ?> var30 : ((Map<?, ?>)var18).entrySet()) {
                           if (var30 != null && var30.getKey() != null) {
                              Object var31 = this.cloneAnimationTimelineKeyframeValue(var30.getValue());
                              if (var31 != null) {
                                 var1.animationTimelineKeyframeClipboardValuesByRef.put((String)var30.getKey(), var31);
                                 AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef var17 = this.parseAnimationTimelineSelectionRef(
                                    (String)var30.getKey()
                                 );
                                 if (var17 != null && this.equalsNullable(var17.rowKey(), var26)) {
                                    var1.animationTimelineKeyframeClipboardValuesByTick
                                       .put(Integer.valueOf(this.clampAnimationTimelineTick(var17.tick())), this.cloneAnimationTimelineKeyframeValue(var31));
                                 }
                              }
                           }
                        }

                        return true;
                     }
                  }
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   protected boolean pasteAnimationTimelineClipboardToSelectedKeyframe(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && this.isAnimationTimelineKeyframeSelected(var2)) {
         String var3 = this.firstNonBlank(new String[]{var2.animationTimelineSelectedTargetId});
         String var4 = this.firstNonBlank(new String[]{var2.animationTimelineKeyframeClipboardRow}).toLowerCase(Locale.ROOT);
         int var5 = this.clampAnimationTimelineTick(var2.animationTimelineSelectedTick);
         return this.applyAnimationTimelineClipboardToKeyframe(var1, var2, var3, var4, var5);
      } else {
         return false;
      }
   }

   protected boolean pasteAnimationTimelineClipboardToContext(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         String var3 = this.firstNonBlank(new String[]{var2.animationTimelineContextTargetId});
         String var4 = this.firstNonBlank(new String[]{var2.animationTimelineKeyframeClipboardRow}).toLowerCase(Locale.ROOT);
         if (this.isAnimationTimelineKeyframeSelected(var2)) {
            String var5 = this.firstNonBlank(new String[]{var2.animationTimelineSelectedTargetId});
            if (var3.isBlank() && !var5.isBlank()) {
               var3 = var5;
            }
         }

         int var6 = this.clampAnimationTimelineTick(var2.animationTimelineContextTick);
         return this.applyAnimationTimelineClipboardToKeyframe(var1, var2, var3, var4, var6);
      } else {
         return false;
      }
   }

   protected boolean applyAnimationTimelineClipboardToKeyframe(Player var1, EditorSession var2, String var3, String var4, int var5) {
      if (var1 != null && var2 != null && this.hasAnimationTimelineKeyframeClipboard(var2)) {
         String var6 = this.firstNonBlank(new String[]{var3});
         String var7 = this.firstNonBlank(new String[]{var4}).toLowerCase(Locale.ROOT);
         if (var6.isBlank()) {
            return false;
         } else {
            if (!ANIMATION_ROW_TO_BOX_ID.containsKey(var7)) {
               var7 = "";
            }

            LinkedHashMap var8 = this.resolveAnimationTimelineClipboardValuesByRef(var2);
            if (var8.isEmpty()) {
               return false;
            } else {
               int var9 = this.clampAnimationTimelineTick(var5);
               AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef var10 = this.parseAnimationTimelineSelectionRef(
                  var2.animationTimelineKeyframeClipboardAnchorRef
               );
               String var11 = var10 == null ? "" : this.buildAnimationTimelineKeyframeRef(var10.rowKey(), var10.tick());
               if (var11.isBlank() || !var8.containsKey(var11)) {
                  String var12 = this.firstNonBlank(new String[]{var2.animationTimelineKeyframeClipboardRow}).toLowerCase(Locale.ROOT);
                  String var13 = this.buildAnimationTimelineKeyframeRef(var12, var2.animationTimelineKeyframeClipboardAnchorTick);
                  if (!var13.isBlank() && var8.containsKey(var13)) {
                     var10 = this.parseAnimationTimelineSelectionRef(var13);
                  } else {
                     var10 = this.parseAnimationTimelineSelectionRef((String)var8.keySet().iterator().next());
                  }
               }

               if (var10 == null) {
                  return false;
               } else {
                  int var25 = this.clampAnimationTimelineTick(var10.tick());
                  int var26 = var9 - var25;
                  Map var14 = this.resolveRawTargetByTargetId(var2, var6);
                  if (var14 == null) {
                     return false;
                  } else {
                     LinkedHashMap var15 = new LinkedHashMap();

                     for (Map.Entry<?, ?> var17 : ((Map<?, ?>)var8).entrySet()) {
                        if (var17 != null && var17.getKey() != null) {
                           AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef var18 = this.parseAnimationTimelineSelectionRef(
                              (String)var17.getKey()
                           );
                           if (var18 != null) {
                              String var19 = this.firstNonBlank(new String[]{var18.rowKey()}).toLowerCase(Locale.ROOT);
                              if (ANIMATION_ROW_TO_BOX_ID.containsKey(var19)) {
                                 Object var20 = this.cloneAnimationTimelineKeyframeValue(var17.getValue());
                                 if (var20 != null) {
                                    Map var21 = this.ensureAnimationTimelineRowMap(var14, var19);
                                    int var22 = this.clampAnimationTimelineTick(var18.tick() + var26);
                                    Object var23 = this.readAnimationTimelineTickValue(var21, var22);
                                    if (!this.animationTimelineValuesEqual(var23, var20)) {
                                       LinkedHashMap var24 = (LinkedHashMap)var15.computeIfAbsent(var19, var0 -> new LinkedHashMap());
                                       var24.put(Integer.valueOf(var22), var20);
                                    }
                                 }
                              }
                           }
                        }
                     }

                     String var27 = !var7.isBlank() ? var7 : this.firstNonBlank(new String[]{var10.rowKey()}).toLowerCase(Locale.ROOT);
                     if (!ANIMATION_ROW_TO_BOX_ID.containsKey(var27)) {
                        var27 = "position";
                     }

                     if (var15.isEmpty()) {
                        this.selectAnimationTimelineKeyframe(var1, var2, var6, var27, var9, false);
                        return true;
                     } else {
                        this.recordEditorMutation(var2);

                        for (Map.Entry<?, ?> var29 : ((Map<?, ?>)var15).entrySet()) {
                           if (var29 != null && var29.getKey() != null && var29.getValue() != null && !((LinkedHashMap)var29.getValue()).isEmpty()) {
                              String var30 = this.firstNonBlank(new String[]{(String)var29.getKey()}).toLowerCase(Locale.ROOT);
                              Map var31 = this.ensureAnimationTimelineRowMap(var14, var30);

                              for (Map.Entry<?, ?> var34 : ((Map<?, ?>)((LinkedHashMap)var29.getValue())).entrySet()) {
                                 if (var34 != null && var34.getKey() != null) {
                                    int var35 = this.clampAnimationTimelineTick((Integer)var34.getKey());
                                    this.removeAnimationTimelineTickValue(var31, var35);
                                    var31.put(Integer.toString(var35), var34.getValue());
                                 }
                              }

                              Map var33 = this.sortAnimationTimelineTickMap(var31);
                              var31.clear();
                              var31.putAll(var33);
                           }
                        }

                        this.selectAnimationTimelinePastedKeyframes(var1, var2, var6, var27, var9, var15);
                        var2.animationTimelineRenderedSignature = null;
                        return true;
                     }
                  }
               }
            }
         }
      } else {
         return false;
      }
   }

   protected void selectAnimationTimelinePastedKeyframes(
      Player var1, EditorSession var2, String var3, String var4, int var5, LinkedHashMap<String, LinkedHashMap<Integer, Object>> var6
   ) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var6 != null && !var6.isEmpty()) {
         LinkedHashMap var7 = new LinkedHashMap();

         for (Map.Entry<?, ?> var9 : ((Map<?, ?>)var6).entrySet()) {
            if (var9 != null && var9.getKey() != null && var9.getValue() != null && !((LinkedHashMap)var9.getValue()).isEmpty()) {
               String var10 = this.firstNonBlank(new String[]{(String)var9.getKey()}).toLowerCase(Locale.ROOT);
               if (ANIMATION_ROW_TO_BOX_ID.containsKey(var10)) {
                  TreeSet var11 = new TreeSet();

                  for (Integer var13 : (Set<Integer>)(Set)((LinkedHashMap)var9.getValue()).keySet()) {
                     if (var13 != null) {
                        var11.add(this.clampAnimationTimelineTick(var13));
                     }
                  }

                  if (!var11.isEmpty()) {
                     var7.put(var10, new ArrayList(var11));
                  }
               }
            }
         }

         if (!var7.isEmpty()) {
            String var17 = this.firstNonBlank(new String[]{var4}).toLowerCase(Locale.ROOT);
            int var18 = this.clampAnimationTimelineTick(var5);
            List var19 = (List)var7.get(var17);
            if (var19 == null || var19.isEmpty()) {
               Entry var20 = (Entry)var7.entrySet().iterator().next();
               var17 = (String)var20.getKey();
               var18 = (Integer)((List)var20.getValue()).get(0);
            } else if (!var19.contains(var18)) {
               var18 = (Integer)var19.get(0);
            }

            this.selectAnimationTimelineKeyframe(var1, var2, var3, var17, var18, false);
            var2.animationTimelineAdditionalSelectedTicks.clear();
            var2.animationTimelineAdditionalSelectedKeyframeRefs.clear();

            for (Map.Entry<?, ?> var22 : ((Map<?, ?>)var7).entrySet()) {
               if (var22 != null && var22.getKey() != null && var22.getValue() != null && !((List)var22.getValue()).isEmpty()) {
                  String var23 = this.firstNonBlank(new String[]{(String)var22.getKey()}).toLowerCase(Locale.ROOT);

                  for (int var15 : (List<Integer>)(List)var22.getValue()) {
                     int var16 = this.clampAnimationTimelineTick(var15);
                     if (!this.equalsNullable(var17, var23) || var18 != var16) {
                        if (this.equalsNullable(var17, var23)) {
                           var2.animationTimelineAdditionalSelectedTicks.add(Integer.valueOf(var16));
                        } else {
                           this.addAnimationTimelineAdditionalSelectionRef(var2, var23, var16);
                        }
                     }
                  }
               }
            }

            this.sanitizeAnimationTimelineAdditionalSelection(var2);
            var2.animationTimelineRenderedSignature = null;
            this.renderAnimationTimelineKeyframes(var1, var2, var3);
            this.applyAnimationTimelinePositionPreview(var1, var2);
            if (var2.selectionOutlineVisible && this.isSelectedTarget(var2, var3)) {
               this.renderSelectionOverlay(var1, var2);
            }

            this.refreshSidebarAfterTimelineSelectionChange(var1, var2);
         }
      }
   }

   protected boolean deleteAnimationTimelineSelectedKeyframes(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && this.isAnimationTimelineKeyframeSelected(var2)) {
         String var3 = this.firstNonBlank(new String[]{var2.animationTimelineSelectedTargetId});
         if (var3.isBlank()) {
            return false;
         } else {
            Map var4 = this.resolveRawTargetByTargetId(var2, var3);
            if (var4 == null) {
               return false;
            } else {
               LinkedHashMap var5 = this.resolveAnimationTimelineSelectedTicksByRow(var2, var3);
               if (var5.isEmpty()) {
                  String var6 = this.firstNonBlank(new String[]{var2.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT);
                  if (!ANIMATION_ROW_TO_BOX_ID.containsKey(var6)) {
                     return false;
                  }

                  var5.put(var6, List.of(this.clampAnimationTimelineTick(var2.animationTimelineSelectedTick)));
               }

               boolean var15 = false;

               for (Map.Entry<?, ?> var8 : ((Map<?, ?>)var5).entrySet()) {
                  if (var8 != null && var8.getKey() != null && var8.getValue() != null && !((List)var8.getValue()).isEmpty()) {
                     String var9 = this.firstNonBlank(new String[]{(String)var8.getKey()}).toLowerCase(Locale.ROOT);
                     if (ANIMATION_ROW_TO_BOX_ID.containsKey(var9)) {
                        Map var10 = this.readAnimationTimelineRowMap(var4, var9);
                        if (var10 != null && !var10.isEmpty()) {
                           boolean var11 = false;

                           for (int var13 : (List<Integer>)(List)var8.getValue()) {
                              Object var14 = this.removeAnimationTimelineTickValue(var10, var13);
                              if (var14 != null) {
                                 var15 = true;
                                 var11 = true;
                              }
                           }

                           if (var11) {
                              Map var16 = this.sortAnimationTimelineTickMap(var10);
                              var10.clear();
                              var10.putAll(var16);
                           }
                        }
                     }
                  }
               }

               if (!var15) {
                  return false;
               } else {
                  this.recordEditorMutation(var2);
                  this.clearAnimationTimelineSelectedKeyframe(var2);
                  var2.animationTimelineRenderedSignature = null;
                  this.stopAnimationTimelineKeyframeDrag(var2, true);
                  this.renderAnimationTimelineKeyframes(var1, var2, var3);
                  this.applyAnimationTimelinePositionPreview(var1, var2);
                  if (var2.selectionOutlineVisible && var2.selectedElementId != null) {
                     this.renderSelectionOverlay(var1, var2);
                  }

                  this.refreshSidebarAfterTimelineSelectionChange(var1, var2);
                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   protected void clearAnimationTimelineSelectedKeyframeOnPanelClick(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null) {
         if (this.isAnimationTimelineKeyframeSelected(var2)) {
            this.clearAnimationTimelineSelectedKeyframe(var2);
            var2.animationTimelineRenderedSignature = null;
            String var4 = this.firstNonBlank(new String[]{var3, var2.animationTimelineTargetId});
            if (!var4.isBlank()) {
               this.renderAnimationTimelineKeyframes(var1, var2, var4);
            }

            this.applyAnimationTimelinePositionPreview(var1, var2);
            if (var2.selectionOutlineVisible && var2.selectedElementId != null) {
               this.renderSelectionOverlay(var1, var2);
            }

            this.refreshSidebarAfterTimelineSelectionChange(var1, var2);
         }
      }
   }

   protected void refreshSidebarAfterTimelineSelectionChange(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         var2.rightSidebarHoverTab = null;
         this.updateSidebarTabVisualState(var1, var2, false, false);
         this.updateEditorPropertiesSidebar(var1, var2);
      }
   }

   protected void syncAnimationTimelineUi(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         boolean var3 = var2.activeTool == EditorTool.ANIMATION;
         if (var2.animationTimelinePanelVisible != var3) {
            this.setSidebarPanelVisible(var1, var2, "animation_timeline", var3);
            var2.animationTimelinePanelVisible = var3;
         }

         if (!var3) {
            this.closeAnimationTimelineContextDropdowns(var1, var2);
            this.stopAnimationTimelineSliderDrag(var2);
            this.stopAnimationTimelineKeyframeDrag(var2, true);
            this.clearAnimationTimelineSelectedKeyframe(var2);
            var2.animationTimelineTick = 0;
            var2.animationTimelineSliderDragTick = null;
            this.clearAnimationTimelinePreviewOffset(var1, var2);
            if (var2.selectionOutlineVisible) {
               this.renderSelectionOverlay(var1, var2);
            }

            this.clearAnimationTimelineRuntimeKeyframes(var1, var2);
            var2.animationTimelineTargetId = null;
            var2.animationTimelineComponentVisible = false;
            var2.animationTimelineRenderedSignature = null;
            var2.animationTimelineImplicitBaseTargetId = null;
            var2.animationTimelineImplicitBaseBounds = null;
            var2.animationTimelineImplicitBaseBoundsByTarget.clear();
         } else {
            String var4 = this.resolveAnimationTimelineEditableTargetId(var2);
            if (!this.equalsNullable(var2.animationTimelineTargetId, var4)) {
               this.closeAnimationTimelineContextDropdowns(var1, var2);
               this.stopAnimationTimelineKeyframeDrag(var2, true);
               this.clearAnimationTimelineSelectedKeyframe(var2);
               boolean var5 = var4 == null || var4.isBlank();
               if (!var5) {
                  this.clearAnimationTimelinePreviewOffset(var1, var2);
               }

               if (var2.selectionOutlineVisible) {
                  this.renderSelectionOverlay(var1, var2);
               }

               var2.animationTimelineTargetId = var4;
               if (var4 != null && !var4.isBlank()) {
                  this.resolveAnimationTimelineImplicitBaseBounds(var2, var4);
               }
            }

            boolean var8 = var4 != null;
            if (var2.animationTimelineComponentVisible != var8) {
               this.setSidebarPanelVisibleByComponentName(var1, var2, "editor_animation_element", var8, true);
               if (var8) {
                  ConfigurationSection var6 = this.findShellBlockSection(var2, "animation_timeline");
                  if (var6 != null) {
                     this.spawnShellPanelHud(var1, var2, var6, "animation_timeline");
                  }
               }

               var2.animationTimelineComponentVisible = var8;
            }

            if (var2.animationTimelineSliderDragActive && var2.animationTimelineSliderDragTick != null && Double.isFinite(var2.animationTimelineSliderDragTick)
               )
             {
               double var9 = this.sliderTickToX(var2, var2.animationTimelineSliderDragTick);
               if (Double.isFinite(var9)) {
                  this.updateAnimationTimelineSliderVisualByX(var1, var2, var9);
               } else {
                  this.updateAnimationTimelineSliderVisual(var1, var2, var2.animationTimelineTick);
               }
            } else {
               this.updateAnimationTimelineSliderVisual(var1, var2, var2.animationTimelineTick);
            }

            if (!var8) {
               this.closeAnimationTimelineContextDropdowns(var1, var2);
               this.stopAnimationTimelineKeyframeDrag(var2, true);
               this.clearAnimationTimelineSelectedKeyframe(var2);
               if (var2.selectionOutlineVisible) {
                  this.renderSelectionOverlay(var1, var2);
               }

               this.clearAnimationTimelineRuntimeKeyframes(var1, var2);
               var2.animationTimelineRenderedSignature = null;
            } else {
               this.setShellText(var1, "animation_element_name", "<#969696>" + this.resolveAnimationTimelineTargetLabel(var2, var4));
               this.setShellText(var1, "animation_row_label_rotation", "<#969696>Rotation");
               this.setShellText(var1, "animation_row_label_position", "<#969696>Position");
               this.setShellText(var1, "animation_row_label_scale", "<#969696>Scale");
               this.renderAnimationTimelineKeyframes(var1, var2, var4);
               this.applyAnimationTimelinePositionPreview(var1, var2);
            }
         }
      }
   }

   protected String resolveAnimationTimelineEditableTargetId(EditorSession var1) {
      if (var1 != null && var1.activeTool == EditorTool.ANIMATION) {
         List var2 = this.getSelectedTargetIds(var1);
         if (var2.size() != 1) {
            return null;
         } else {
            String var3 = this.firstNonBlank(new String[]{(String)var2.get(0)});
            if (var3.isBlank()) {
               return null;
            } else {
               HoverElement var4 = this.findFirstByTargetId(var1, var3);
               if (var4 == null) {
                  return null;
               } else if ("component".equalsIgnoreCase(this.firstNonBlank(new String[]{var4.targetKind}))) {
                  return null;
               } else {
                  String var5 = this.firstNonBlank(new String[]{var4.type}).toLowerCase(Locale.ROOT);
                  boolean var6 = "hitbox".equals(var5) && this.isAnimationTimelineImageGroupRoot(var1, var3);
                  return !var6 && !"block".equals(var5) && !"text".equals(var5) && !this.isRoundedType(var5) ? null : var3;
               }
            }
         }
      } else {
         return null;
      }
   }

   protected boolean isAnimationTimelineImageGroupRoot(EditorSession var1, String var2) {
      Map var3 = this.resolveRawTargetByTargetId(var1, var2);
      if (var3 != null && !var3.isEmpty()) {
         Object var4 = this.readMapPathValue(var3, "__editor_inherit_target_to_children");
         if (this.parseBooleanFlag(var4, false)) {
            return true;
         } else {
            String var5;
            boolean var10000;
            label35: {
               var5 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var3, "id"))}).toLowerCase(Locale.ROOT);
               if (this.readMapPathValue(var3, "children") instanceof List var8 && !var8.isEmpty()) {
                  var10000 = true;
                  break label35;
               }

               var10000 = false;
            }

            boolean var7 = var10000;
            if (!var7) {
               return false;
            } else if (var5.startsWith("img_")) {
               return true;
            } else {
               String var9 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var3, "type"))}).toLowerCase(Locale.ROOT);
               return "hitbox".equals(var9) && this.hasAnimationTimelineImageGroupGlyphChildren(var3);
            }
         }
      } else {
         return false;
      }
   }

   protected boolean hasAnimationTimelineImageGroupGlyphChildren(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         if (this.readMapPathValue(var1, "children") instanceof List var3 && !var3.isEmpty()) {
            for (Object var5 : var3) {
               Map var6 = this.toStringObjectMap(var5);
               if (var6 != null && !var6.isEmpty()) {
                  String var7 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var6, "text"))}).toLowerCase(Locale.ROOT);
                  if (!var7.isBlank() && (var7.contains("<font:uiimages>") || var7.contains("%img_"))) {
                     return true;
                  }

                  String var8 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var6, "id"))}).toLowerCase(Locale.ROOT);
                  if (!var8.isBlank() && var8.startsWith("img_")) {
                     return true;
                  }
               }
            }

            return false;
         }

         return false;
      } else {
         return false;
      }
   }

   protected String resolveAnimationTimelineTargetLabel(EditorSession var1, String var2) {
      String var3 = this.firstNonBlank(new String[]{var2});
      if (var3.isBlank()) {
         return "Block";
      } else if (this.isAnimationTimelineImageGroupRoot(var1, var3)) {
         return "Image";
      } else {
         HoverElement var4 = this.findFirstByTargetId(var1, var3);
         String var5 = this.firstNonBlank(new String[]{var4 == null ? null : var4.type}).toLowerCase(Locale.ROOT);
         return "text".equals(var5) ? "Text" : "Block";
      }
   }

   protected void updateAnimationTimelineSliderDrag(Player var1, EditorSession var2, double var3) {
      if (var1 != null && var2 != null && var2.animationTimelinePanelVisible) {
         double var5 = this.resolveAnimationTimelineSliderStartX(var2);
         if (!Double.isFinite(var5)) {
            this.stopAnimationTimelineSliderDrag(var2);
         } else {
            double var7 = var5 + 1220.0;
            double var9 = Math.max(var5, Math.min(var7, var3));
            double var11 = this.sliderXToPreciseTick(var2, var9);
            int var13 = this.clampAnimationTimelineTick((int)Math.round(var11));
            var2.animationTimelineTick = var13;
            var2.animationTimelineSliderDragTick = var11;
            this.updateAnimationTimelineSliderVisualByX(var1, var2, var9);
            this.applyAnimationTimelinePositionPreview(var1, var2);
            if (var2.selectionOutlineVisible && var2.selectedElementId != null && !var2.selectedElementId.isBlank()) {
               this.renderSelectionOverlay(var1, var2);
            }
         }
      }
   }

   protected void updateAnimationTimelineSliderVisual(Player var1, EditorSession var2, int var3) {
      if (var1 != null && var2 != null && var2.animationTimelinePanelVisible) {
         double var4 = this.resolveAnimationTimelineSliderStartX(var2);
         if (Double.isFinite(var4)) {
            int var6 = this.clampAnimationTimelineTick(var3);
            var2.animationTimelineTick = var6;
            var2.animationTimelineSliderDragTick = null;
            double var7 = this.sliderTickToX(var2, var6);
            if (Double.isFinite(var7)) {
               this.updateAnimationTimelineSliderVisualByX(var1, var2, var7);
            }
         }
      }
   }

   protected void updateAnimationTimelineSliderVisualByX(Player var1, EditorSession var2, double var3) {
      if (var1 != null && var2 != null && var2.animationTimelinePanelVisible && Double.isFinite(var3)) {
         EditorRect var5 = this.findShellBlockRect(var2, "animation_accurate_slider_element");
         if (var5 != null) {
            EditorRect var6 = new EditorRect(var3, var5.y, var5.width, var5.height);
            if (!this.sameRect(var5, var6)) {
               this.moveShellElement(var1, var2, "animation_accurate_slider_element", var6.x, var6.y, var6.width, var6.height);
            }
         }

         EditorRect var8 = this.findShellBlockRect(var2, "animation_accurate_slider_hitbox");
         if (var8 != null) {
            EditorRect var7 = new EditorRect(var3, var8.y, var8.width, var8.height);
            if (!this.sameRect(var8, var7)) {
               this.moveShellElement(var1, var2, "animation_accurate_slider_hitbox", var7.x, var7.y, var7.width, var7.height);
            }
         }
      }
   }

   protected double resolveAnimationTimelineSliderStartX(EditorSession var1) {
      if (var1 == null) {
         return Double.NaN;
      } else if (var1.animationTimelineSliderStartX != null && Double.isFinite(var1.animationTimelineSliderStartX)) {
         return var1.animationTimelineSliderStartX;
      } else {
         EditorRect var2 = this.findShellBlockRect(var1, "animation_accurate_slider_element");
         if (var2 == null) {
            return Double.NaN;
         } else {
            double var3 = (double)this.clampAnimationTimelineTick(var1.animationTimelineTick);
            if (var1.animationTimelineSliderDragTick != null && Double.isFinite(var1.animationTimelineSliderDragTick)) {
               var3 = Math.max(0.0, Math.min(400.0, var1.animationTimelineSliderDragTick));
            }

            double var5 = var2.x - 3.05 * var3;
            var1.animationTimelineSliderStartX = var5;
            return var5;
         }
      }
   }

   protected double sliderTickToX(EditorSession var1, int var2) {
      return this.sliderTickToX(var1, (double)this.clampAnimationTimelineTick(var2));
   }

   protected double sliderTickToX(EditorSession var1, double var2) {
      double var4 = this.resolveAnimationTimelineSliderStartX(var1);
      if (!Double.isFinite(var4)) {
         return Double.NaN;
      } else {
         double var6 = Math.max(0.0, Math.min(400.0, var2));
         return var4 + 3.05 * var6;
      }
   }

   protected int sliderXToTick(EditorSession var1, double var2) {
      return this.clampAnimationTimelineTick((int)Math.round(this.sliderXToPreciseTick(var1, var2)));
   }

   protected double sliderXToPreciseTick(EditorSession var1, double var2) {
      double var4 = this.resolveAnimationTimelineSliderStartX(var1);
      if (Double.isFinite(var4) && Double.isFinite(var2)) {
         double var6 = (var2 - var4) / 3.05;
         return Math.max(0.0, Math.min(400.0, var6));
      } else {
         return 0.0;
      }
   }

   @Override
   protected int clampAnimationTimelineTick(int var1) {
      return Math.max(0, Math.min(400, var1));
   }

   protected void addAnimationTimelineKeyframe(Player var1, EditorSession var2, String var3, String var4) {
      if (var2 != null && var3 != null && !var3.isBlank() && var4 != null && !var4.isBlank()) {
         Map var5 = this.resolveRawTargetByTargetId(var2, var3);
         if (var5 != null) {
            int var6 = this.clampAnimationTimelineTick(var2.animationTimelineTick);
            Map var7 = this.ensureAnimationTimelineRowMap(var5, var4);
            if (this.containsAnimationTimelineTick(var7, var6)) {
               this.selectAnimationTimelineKeyframe(var1, var2, var3, var4, var6, false);
            } else {
               Object var8 = this.resolveAnimationTimelineSeedValueForNewKeyframe(var2, var3, var4, var7, var6);
               if (var8 != null) {
                  var7.put(Integer.toString(var6), var8);
                  Map var9 = this.sortAnimationTimelineTickMap(var7);
                  var7.clear();
                  var7.putAll(var9);
                  var2.animationTimelineSelectedTargetId = var3;
                  var2.animationTimelineSelectedRow = var4;
                  var2.animationTimelineSelectedTick = var6;
                  this.clearAnimationTimelineAdditionalSelection(var2);
                  var2.animationTimelineRenderedSignature = null;
                  var2.activeHandle = null;
                  var2.resizeAnchorValid = false;
                  var2.handlesCollapsed = false;
                  var2.hoveredHandle = null;
                  var2.moveDragActive = false;
                  var2.previewPanActive = false;
                  var2.moveStartBounds.clear();
                  this.recordEditorMutation(var2);
                  this.renderAnimationTimelineKeyframes(var1, var2, var3);
                  this.applyAnimationTimelinePositionPreview(var1, var2);
                  if (var2.selectionOutlineVisible && this.isSelectedTarget(var2, var3)) {
                     this.renderSelectionOverlay(var1, var2);
                  }

                  this.playEditorSfx(var1, var2, "keyframe-selected");
                  this.refreshSidebarAfterTimelineSelectionChange(var1, var2);
               }
            }
         }
      }
   }

   protected Object resolveAnimationTimelineSeedValueForNewKeyframe(EditorSession var1, String var2, String var3, Map<String, Object> var4, int var5) {
      Object var6 = this.readAnimationTimelinePreviousTickValue(var4, var5);
      Object var7 = this.cloneAnimationTimelineKeyframeValue(var6);
      if (var7 != null) {
         return var7;
      } else if ("opacity".equals(this.firstNonBlank(new String[]{var3}).toLowerCase(Locale.ROOT))) {
         Double var8 = this.resolveAnimationTimelineExplicitTickZeroOpacity(var1, var2);
         return var8 != null ? this.clampAnimationTimelineOpacityRaw(var8) : 255;
      } else {
         return this.buildAnimationTimelineValueForRow(var1, var2, var3);
      }
   }

   protected Object readAnimationTimelinePreviousTickValue(Map<String, Object> var1, int var2) {
      if (var1 != null && !var1.isEmpty()) {
         int var3 = this.clampAnimationTimelineTick(var2);
         int var4 = -1;
         Object var5 = null;

         for (Map.Entry<?, ?> var7 : ((Map<?, ?>)var1).entrySet()) {
            if (var7 != null && var7.getKey() != null) {
               double var8 = this.parseDouble(var7.getKey(), Double.NaN);
               if (Double.isFinite(var8)) {
                  int var10 = this.clampAnimationTimelineTick((int)Math.round(var8));
                  if (var10 < var3 && var10 > var4) {
                     var4 = var10;
                     var5 = var7.getValue();
                  }
               }
            }
         }

         return var5;
      } else {
         return null;
      }
   }

   protected Object cloneAnimationTimelineKeyframeValue(Object var1) {
      if (var1 == null) {
         return null;
      } else if (var1 instanceof Map var4) {
         Map var3 = this.toStringObjectMap(var4);
         return var3 == null ? null : this.deepCopyMap(var3);
      } else if (var1 instanceof Number var2) {
         return var2.doubleValue();
      } else {
         return var1 instanceof String ? (String)var1 : null;
      }
   }

   protected void selectAnimationTimelineKeyframe(Player var1, EditorSession var2, String var3, String var4, int var5, boolean var6) {
      this.selectAnimationTimelineKeyframe(var1, var2, var3, var4, var5, var6, false);
   }

   protected void selectAnimationTimelineKeyframe(Player var1, EditorSession var2, String var3, String var4, int var5, boolean var6, boolean var7) {
      if (var2 != null && var3 != null && !var3.isBlank() && var4 != null && !var4.isBlank()) {
         String var8 = var4.toLowerCase(Locale.ROOT);
         if (ANIMATION_ROW_TO_BOX_ID.containsKey(var8)) {
            int var9 = this.clampAnimationTimelineTick(var5);
            boolean var10 = this.isAnimationTimelineKeyframeSelected(var2);
            String var11 = this.firstNonBlank(new String[]{var2.animationTimelineSelectedTargetId});
            String var12 = this.firstNonBlank(new String[]{var2.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT);
            int var13 = this.clampAnimationTimelineTick(var2.animationTimelineSelectedTick);
            boolean var14 = var10 && this.equalsNullable(var11, var3);
            boolean var15 = var14 && this.equalsNullable(var12, var8);
            if (!var7 || !var10 || !var14) {
               this.clearAnimationTimelineAdditionalSelection(var2);
            } else if (var15) {
               var2.animationTimelineAdditionalSelectedTicks.remove(Integer.valueOf(var9));
               this.removeAnimationTimelineAdditionalSelectionRef(var2, var8, var9);
               if (var13 != var9) {
                  var2.animationTimelineAdditionalSelectedTicks.add(Integer.valueOf(var13));
               }
            } else {
               this.movePrimaryAnimationTimelineSelectionIntoAdditionalRefs(var2);
            }

            var2.animationTimelineSelectedTargetId = var3;
            var2.animationTimelineSelectedRow = var8;
            var2.animationTimelineSelectedTick = var9;
            var2.animationTimelineTick = var9;
            this.removeAnimationTimelineAdditionalSelectionRef(var2, var8, var9);
            this.sanitizeAnimationTimelineAdditionalSelection(var2);
            var2.animationTimelineRenderedSignature = null;
            var2.activeHandle = null;
            var2.resizeAnchorValid = false;
            var2.handlesCollapsed = false;
            var2.hoveredHandle = null;
            var2.moveDragActive = false;
            var2.previewPanActive = false;
            var2.moveStartBounds.clear();
            if (var6) {
               if (!var2.animationTimelineKeyframeDragActive) {
                  var2.animationTimelineKeyframeDragStartSnapshot = this.captureEditorMutationSnapshot(var2);
                  var2.animationTimelineKeyframeDragMutated = false;
               }

               List var16 = this.resolveAnimationTimelineSelectedTicks(var2, var3, var8);
               if (var16.isEmpty()) {
                  var16 = List.of(var9);
               }

               LinkedHashMap var17 = this.resolveAnimationTimelineSelectedTicksByRow(var2, var3);
               if (var17.isEmpty()) {
                  var17.put(var8, new ArrayList(var16));
               }

               var2.animationTimelineKeyframeDragRuntimeTickByLogicalTick.clear();

               for (int var19 : (List<Integer>)(List)var16) {
                  var2.animationTimelineKeyframeDragRuntimeTickByLogicalTick.put(Integer.valueOf(var19), Integer.valueOf(var19));
               }

               var2.animationTimelineKeyframeDragRuntimeTickByRef.clear();

               for (Map.Entry<?, ?> var27 : ((Map<?, ?>)var17).entrySet()) {
                  if (var27 != null && var27.getKey() != null && var27.getValue() != null && !((List)var27.getValue()).isEmpty()) {
                     String var20 = this.firstNonBlank(new String[]{(String)var27.getKey()}).toLowerCase(Locale.ROOT);
                     if (!var20.isBlank()) {
                        for (Integer var22 : (List<Integer>)(List)var27.getValue()) {
                           if (var22 != null) {
                              int var23 = this.clampAnimationTimelineTick(var22);
                              String var24 = this.buildAnimationTimelineKeyframeRef(var20, var23);
                              if (!var24.isBlank()) {
                                 var2.animationTimelineKeyframeDragRuntimeTickByRef.put(var24, Integer.valueOf(var23));
                              }
                           }
                        }
                     }
                  }
               }

               double var26 = var2.animationTimelineKeyframeDragHitOffsetX;
               if (!Double.isFinite(var26)) {
                  var26 = 0.0;
               }

               if (!Double.isFinite(var2.animationTimelineKeyframeDragStartHitX)) {
                  double var28 = this.sliderTickToX(var2, var9);
                  if (Double.isFinite(var28)) {
                     var2.animationTimelineKeyframeDragStartHitX = var28 + var26;
                  }
               }

               var2.animationTimelineKeyframeDragHitOffsetX = var26;
               var2.animationTimelineKeyframeDragActive = true;
               var2.animationTimelineKeyframeDragRuntimeTick = var2.animationTimelineKeyframeDragRuntimeTickByLogicalTick.size() == 1
                  ? var2.animationTimelineKeyframeDragRuntimeTickByLogicalTick.values().iterator().next()
                  : -1;
            } else {
               this.stopAnimationTimelineKeyframeDrag(var2, true);
            }

            this.rerenderAnimationTimelineSelectionTarget(var1, var2, var3);
            this.updateAnimationTimelineSliderVisual(var1, var2, var9);
            this.renderAnimationTimelineKeyframes(var1, var2, var3);
            this.applyAnimationTimelinePositionPreview(var1, var2);
            if (var2.selectionOutlineVisible && this.isSelectedTarget(var2, var3)) {
               this.renderSelectionOverlay(var1, var2);
            }

            this.playEditorSfx(var1, var2, "keyframe-selected");
            this.refreshSidebarAfterTimelineSelectionChange(var1, var2);
         }
      }
   }

   protected void toggleAnimationTimelineKeyframeSelection(Player var1, EditorSession var2, String var3, String var4, int var5) {
      if (var2 != null && var3 != null && !var3.isBlank() && var4 != null && !var4.isBlank()) {
         String var6 = var4.toLowerCase(Locale.ROOT);
         if (ANIMATION_ROW_TO_BOX_ID.containsKey(var6)) {
            int var7 = this.clampAnimationTimelineTick(var5);
            boolean var8 = this.isAnimationTimelineKeyframeSelected(var2);
            String var9 = this.firstNonBlank(new String[]{var2.animationTimelineSelectedTargetId});
            String var10 = this.firstNonBlank(new String[]{var2.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT);
            boolean var11 = var8 && this.equalsNullable(var9, var3);
            boolean var12 = var11 && this.equalsNullable(var10, var6);
            if (!var11) {
               this.clearAnimationTimelineAdditionalSelection(var2);
               var2.animationTimelineSelectedTargetId = var3;
               var2.animationTimelineSelectedRow = var6;
               var2.animationTimelineSelectedTick = var7;
               var2.animationTimelineTick = var7;
            } else if (!var12) {
               this.movePrimaryAnimationTimelineSelectionIntoAdditionalRefs(var2);
               var2.animationTimelineSelectedTargetId = var3;
               var2.animationTimelineSelectedRow = var6;
               var2.animationTimelineSelectedTick = var7;
               var2.animationTimelineTick = var7;
            } else if (var2.animationTimelineSelectedTick != var7 && !var2.animationTimelineAdditionalSelectedTicks.remove(Integer.valueOf(var7))) {
               var2.animationTimelineAdditionalSelectedTicks.add(Integer.valueOf(var7));
            }

            this.removeAnimationTimelineAdditionalSelectionRef(var2, var6, var7);
            this.sanitizeAnimationTimelineAdditionalSelection(var2);
            var2.animationTimelineRenderedSignature = null;
            this.stopAnimationTimelineKeyframeDrag(var2, true);
            this.rerenderAnimationTimelineSelectionTarget(var1, var2, var3);
            this.updateAnimationTimelineSliderVisual(var1, var2, var2.animationTimelineSelectedTick);
            this.renderAnimationTimelineKeyframes(var1, var2, var3);
            this.applyAnimationTimelinePositionPreview(var1, var2);
            if (var2.selectionOutlineVisible && this.isSelectedTarget(var2, var3)) {
               this.renderSelectionOverlay(var1, var2);
            }

            this.refreshSidebarAfterTimelineSelectionChange(var1, var2);
         }
      }
   }

   protected boolean isAnimationTimelineKeyframeRefSelected(EditorSession var1, String var2, String var3, int var4) {
      if (var1 == null || var2 == null || var2.isBlank() || var3 == null || var3.isBlank()) {
         return false;
      } else if (!this.isAnimationTimelineKeyframeSelected(var1)) {
         return false;
      } else {
         String var5 = this.firstNonBlank(new String[]{var1.animationTimelineSelectedTargetId});
         if (!this.equalsNullable(var5, var2)) {
            return false;
         } else {
            String var6 = var3.toLowerCase(Locale.ROOT);
            int var7 = this.clampAnimationTimelineTick(var4);
            String var8 = this.firstNonBlank(new String[]{var1.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT);
            return !this.equalsNullable(var8, var6)
                  || var1.animationTimelineSelectedTick != var7 && !var1.animationTimelineAdditionalSelectedTicks.contains(Integer.valueOf(var7))
               ? this.containsAnimationTimelineAdditionalSelectionRef(var1, var6, var7)
               : true;
         }
      }
   }

   protected List<Integer> resolveAnimationTimelineSelectedTicks(EditorSession var1, String var2, String var3) {
      if (var1 == null || var2 == null || var2.isBlank() || var3 == null || var3.isBlank()) {
         return Collections.emptyList();
      } else if (!this.isAnimationTimelineKeyframeSelected(var1)) {
         return Collections.emptyList();
      } else {
         String var4 = var3.toLowerCase(Locale.ROOT);
         if (this.equalsNullable(this.firstNonBlank(new String[]{var1.animationTimelineSelectedTargetId}), var2)
            && this.equalsNullable(this.firstNonBlank(new String[]{var1.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT), var4)) {
            this.sanitizeAnimationTimelineAdditionalSelection(var1);
            TreeMap var5 = new TreeMap();
            var5.put(this.clampAnimationTimelineTick(var1.animationTimelineSelectedTick), Boolean.TRUE);

            for (Object var7_raw : var1.animationTimelineAdditionalSelectedTicks) {
               int var7 = ((Number)var7_raw).intValue(); {
                  var5.put(this.clampAnimationTimelineTick(var7), Boolean.TRUE);
               }
            }

            for (Object var10_raw : var1.animationTimelineAdditionalSelectedKeyframeRefs) {
               String var10 = var10_raw != null ? var10_raw.toString() : null;
               AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef var8 = this.parseAnimationTimelineSelectionRef(var10);
               if (var8 != null && this.equalsNullable(var8.rowKey(), var4)) {
                  var5.put(this.clampAnimationTimelineTick(var8.tick()), Boolean.TRUE);
               }
            }

            return new ArrayList<>(var5.keySet());
         } else {
            return Collections.emptyList();
         }
      }
   }

   protected LinkedHashMap<String, List<Integer>> resolveAnimationTimelineSelectedTicksByRow(EditorSession var1, String var2) {
      LinkedHashMap var3 = new LinkedHashMap();
      if (var1 == null || var2 == null || var2.isBlank()) {
         return var3;
      } else if (!this.isAnimationTimelineKeyframeSelected(var1)) {
         return var3;
      } else if (!this.equalsNullable(this.firstNonBlank(new String[]{var1.animationTimelineSelectedTargetId}), var2)) {
         return var3;
      } else {
         this.sanitizeAnimationTimelineAdditionalSelection(var1);
         String var4 = this.firstNonBlank(new String[]{var1.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT);
         if (!var4.isBlank() && ANIMATION_ROW_TO_BOX_ID.containsKey(var4)) {
            LinkedHashMap var5 = new LinkedHashMap();
            TreeMap var6 = (TreeMap)var5.computeIfAbsent(var4, var0 -> new TreeMap());
            var6.put(this.clampAnimationTimelineTick(var1.animationTimelineSelectedTick), Boolean.TRUE);

            for (Object var8_raw : var1.animationTimelineAdditionalSelectedTicks) {
               int var8 = ((Number)var8_raw).intValue(); {
                  var6.put(this.clampAnimationTimelineTick(var8), Boolean.TRUE);
               }
            }

            for (Object var13_raw : var1.animationTimelineAdditionalSelectedKeyframeRefs) {
               String var13 = var13_raw != null ? var13_raw.toString() : null;
               AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef var9 = this.parseAnimationTimelineSelectionRef(var13);
               if (var9 != null) {
                  TreeMap var10 = (TreeMap)var5.computeIfAbsent(var9.rowKey(), var0 -> new TreeMap());
                  var10.put(this.clampAnimationTimelineTick(var9.tick()), Boolean.TRUE);
               }
            }

            for (Map.Entry<?, ?> var14 : ((Map<?, ?>)var5).entrySet()) {
               if (var14 != null && var14.getKey() != null && var14.getValue() != null && !((TreeMap)var14.getValue()).isEmpty()) {
                  var3.put((String)var14.getKey(), new ArrayList(((TreeMap)var14.getValue()).keySet()));
               }
            }

            return var3;
         } else {
            return var3;
         }
      }
   }

   protected void sanitizeAnimationTimelineAdditionalSelection(EditorSession var1) {
      if (var1 != null) {
         if (!this.isAnimationTimelineKeyframeSelected(var1)) {
            this.clearAnimationTimelineAdditionalSelection(var1);
         } else {
            String var2 = this.firstNonBlank(new String[]{var1.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT);
            int var3 = this.clampAnimationTimelineTick(var1.animationTimelineSelectedTick);
            LinkedHashSet var4 = new LinkedHashSet();

            for (Object var6_raw : var1.animationTimelineAdditionalSelectedTicks) {
               int var6 = ((Number)var6_raw).intValue(); {
                  int var7 = this.clampAnimationTimelineTick(var6);
                  if (var7 != var3) {
                     var4.add(Integer.valueOf(var7));
                  }
               }
            }

            var1.animationTimelineAdditionalSelectedTicks.clear();
            var1.animationTimelineAdditionalSelectedTicks.addAll(var4);
            LinkedHashSet var11 = new LinkedHashSet();

            for (Object var13_raw : var1.animationTimelineAdditionalSelectedKeyframeRefs) {
               String var13 = var13_raw != null ? var13_raw.toString() : null;
               AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef var8 = this.parseAnimationTimelineSelectionRef(var13);
               if (var8 != null) {
                  int var9 = this.clampAnimationTimelineTick(var8.tick());
                  if (!this.equalsNullable(var2, var8.rowKey()) || var9 != var3) {
                     String var10 = this.buildAnimationTimelineKeyframeRef(var8.rowKey(), var9);
                     if (!var10.isBlank()) {
                        var11.add(var10);
                     }
                  }
               }
            }

            var1.animationTimelineAdditionalSelectedKeyframeRefs.clear();
            var1.animationTimelineAdditionalSelectedKeyframeRefs.addAll(var11);
         }
      }
   }

   protected void updateAnimationTimelineKeyframeDrag(Player var1, EditorSession var2, double var3) {
      if (var1 != null && var2 != null && var2.animationTimelineKeyframeDragActive && this.isAnimationTimelineKeyframeSelected(var2)) {
         String var5 = this.firstNonBlank(new String[]{var2.animationTimelineSelectedTargetId});
         String var6 = this.firstNonBlank(new String[]{var2.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT);
         if (!var5.isBlank() && !var6.isBlank()) {
            String var7 = this.resolveAnimationTimelineEditableTargetId(var2);
            if (this.equalsNullable(var5, var7)) {
               Map var8 = this.resolveRawTargetByTargetId(var2, var5);
               if (var8 != null) {
                  if (!Double.isFinite(var2.animationTimelineKeyframeDragStartHitX) || !(Math.abs(var3 - var2.animationTimelineKeyframeDragStartHitX) < 2.0)) {
                     double var9 = var2.animationTimelineKeyframeDragHitOffsetX;
                     if (!Double.isFinite(var9)) {
                        var9 = 0.0;
                     }

                     Map var11 = this.ensureAnimationTimelineRowMap(var8, var6);
                     LinkedHashMap var12 = this.resolveAnimationTimelineSelectedTicksByRow(var2, var5);
                     List var13 = (List)var12.get(var6);
                     if (var13 != null && !var13.isEmpty()) {
                        boolean var14 = var12.size() > 1;
                        LinkedHashMap var15 = new LinkedHashMap();
                        if (var2.animationTimelineKeyframeDragRuntimeTickByLogicalTick.isEmpty()) {
                           for (int var17 : (List<Integer>)(List)var13) {
                              var15.put(Integer.valueOf(var17), Integer.valueOf(var17));
                           }
                        } else {
                           var15.putAll(var2.animationTimelineKeyframeDragRuntimeTickByLogicalTick);
                        }

                        LinkedHashMap var36 = new LinkedHashMap();
                        if (var2.animationTimelineKeyframeDragRuntimeTickByRef.isEmpty()) {
                           for (Map.Entry<?, ?> var18 : ((Map<?, ?>)var12).entrySet()) {
                              if (var18 != null && var18.getKey() != null && var18.getValue() != null && !((List)var18.getValue()).isEmpty()) {
                                 String var19 = this.firstNonBlank(new String[]{(String)var18.getKey()}).toLowerCase(Locale.ROOT);
                                 if (!var19.isBlank()) {
                                    for (Integer var21 : (List<Integer>)(List)var18.getValue()) {
                                       if (var21 != null) {
                                          int var22 = this.clampAnimationTimelineTick(var21);
                                          String var23 = this.buildAnimationTimelineKeyframeRef(var19, var22);
                                          if (!var23.isBlank()) {
                                             var36.put(var23, Integer.valueOf(var22));
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        } else {
                           var36.putAll(var2.animationTimelineKeyframeDragRuntimeTickByRef);
                        }

                        int var38 = this.clampAnimationTimelineTick(var2.animationTimelineSelectedTick);
                        int var39 = this.sliderXToTick(var2, var3 - var9);
                        int var40 = var39 - var38;
                        if (var40 != 0) {
                           if (this.canShiftAnimationTimelineKeyframes(var11, var13, var40)) {
                              if (var14) {
                                 LinkedHashMap var42 = new LinkedHashMap();
                                 LinkedHashMap var46 = new LinkedHashMap();

                                 for (Map.Entry<?, ?> var56 : ((Map<?, ?>)var12).entrySet()) {
                                    if (var56 != null && var56.getKey() != null && var56.getValue() != null && !((List)var56.getValue()).isEmpty()) {
                                       String var62 = this.firstNonBlank(new String[]{(String)var56.getKey()}).toLowerCase(Locale.ROOT);
                                       List var70 = (List)var56.getValue();
                                       if (!var62.isBlank()) {
                                          Map var79 = this.ensureAnimationTimelineRowMap(var8, var62);
                                          if (!this.canShiftAnimationTimelineKeyframes(var79, var70, var40)) {
                                             return;
                                          }

                                          LinkedHashMap var88 = new LinkedHashMap();

                                          for (int var29 : (List<Integer>)(List)var70) {
                                             Object var30 = this.readAnimationTimelineTickValue(var79, var29);
                                             if (var30 == null) {
                                                return;
                                             }

                                             var88.put(Integer.valueOf(var29), var30);
                                          }

                                          if (!var88.isEmpty()) {
                                             var42.put(var62, var79);
                                             var46.put(var62, var88);
                                          }
                                       }
                                    }
                                 }

                                 if (!var46.isEmpty()) {
                                    int var51 = 0;

                                    for (Map.Entry<?, ?> var63 : ((Map<?, ?>)var46).entrySet()) {
                                       if (var63 != null && var63.getValue() != null) {
                                          var51 += ((LinkedHashMap)var63.getValue()).size();
                                       }
                                    }

                                    for (Map.Entry<?, ?> var64 : ((Map<?, ?>)var46).entrySet()) {
                                       if (var64 != null && var64.getValue() != null && !((LinkedHashMap)var64.getValue()).isEmpty()) {
                                          Map var71 = (Map)var42.get(var64.getKey());
                                          if (var71 != null) {
                                             for (int var89 : (Set<Integer>)(Set)((LinkedHashMap)var64.getValue()).keySet()) {
                                                this.removeAnimationTimelineTickValue(var71, var89);
                                             }

                                             for (Map.Entry<?, ?> var90 : ((Map<?, ?>)((LinkedHashMap)var64.getValue())).entrySet()) {
                                                int var96 = this.clampAnimationTimelineTick((Integer)var90.getKey() + var40);
                                                var71.put(Integer.toString(var96), var90.getValue());
                                             }

                                             Map var82 = this.sortAnimationTimelineTickMap(var71);
                                             var71.clear();
                                             var71.putAll(var82);
                                          }
                                       }
                                    }

                                    int var59 = this.clampAnimationTimelineTick(var38 + var40);
                                    var2.animationTimelineSelectedTick = var59;
                                    var2.animationTimelineTick = var59;
                                    var2.animationTimelineAdditionalSelectedTicks.clear();
                                    var2.animationTimelineAdditionalSelectedKeyframeRefs.clear();
                                    LinkedHashMap var65 = (LinkedHashMap)var46.get(var6);
                                    if (var65 != null) {
                                       for (int var83 : (Set<Integer>)(Set)var65.keySet()) {
                                          int var91 = this.clampAnimationTimelineTick(var83 + var40);
                                          if (var91 != var59) {
                                             var2.animationTimelineAdditionalSelectedTicks.add(Integer.valueOf(var91));
                                          }
                                       }
                                    }

                                    for (Map.Entry<?, ?> var84 : ((Map<?, ?>)var46).entrySet()) {
                                       if (var84 != null && var84.getValue() != null && !((LinkedHashMap)var84.getValue()).isEmpty()) {
                                          String var92 = this.firstNonBlank(new String[]{(String)var84.getKey()}).toLowerCase(Locale.ROOT);
                                          if (!var92.isBlank() && !this.equalsNullable(var92, var6)) {
                                             for (int var101 : (Set<Integer>)(Set)((LinkedHashMap)var84.getValue()).keySet()) {
                                                int var105 = this.clampAnimationTimelineTick(var101 + var40);
                                                this.addAnimationTimelineAdditionalSelectionRef(var2, var92, var105);
                                             }
                                          }
                                       }
                                    }

                                    this.sanitizeAnimationTimelineAdditionalSelection(var2);
                                    LinkedHashMap var74 = new LinkedHashMap();
                                    boolean var85 = true;

                                    label306:
                                    for (Map.Entry<?, ?> var98 : ((Map<?, ?>)var46).entrySet()) {
                                       if (var98 != null && var98.getKey() != null && var98.getValue() != null && !((LinkedHashMap)var98.getValue()).isEmpty()) {
                                          String var102 = this.firstNonBlank(new String[]{(String)var98.getKey()}).toLowerCase(Locale.ROOT);
                                          if (!var102.isBlank()) {
                                             Iterator var106 = ((LinkedHashMap)var98.getValue()).keySet().iterator();

                                             while (true) {
                                                if (var106.hasNext()) {
                                                   int var31 = (Integer)var106.next();
                                                   int var32 = this.clampAnimationTimelineTick(var31 + var40);
                                                   String var33 = this.buildAnimationTimelineKeyframeRef(var102, var31);
                                                   int var34 = var33.isBlank() ? var31 : ((Number)var36.getOrDefault(var33, var31)).intValue();
                                                   if (this.retimeAnimationTimelineRuntimeKeyframe(var1, var2, var5, var102, var34, var32, false)) {
                                                      String var35 = this.buildAnimationTimelineKeyframeRef(var102, var32);
                                                      if (!var35.isBlank()) {
                                                         var74.put(var35, Integer.valueOf(var34));
                                                      }
                                                      continue;
                                                   }

                                                   var85 = false;
                                                }

                                                if (!var85) {
                                                   break label306;
                                                }
                                                break;
                                             }
                                          }
                                       }
                                    }

                                    if (!var85) {
                                       this.renderAnimationTimelineKeyframes(var1, var2, var5);
                                       var74.clear();

                                       for (Map.Entry<?, ?> var99 : ((Map<?, ?>)var46).entrySet()) {
                                          if (var99 != null
                                             && var99.getKey() != null
                                             && var99.getValue() != null
                                             && !((LinkedHashMap)var99.getValue()).isEmpty()) {
                                             String var103 = this.firstNonBlank(new String[]{(String)var99.getKey()}).toLowerCase(Locale.ROOT);
                                             if (!var103.isBlank()) {
                                                for (Object var109_o : ((LinkedHashMap)var99.getValue()).keySet()) { int var109 = ((Number)var109_o).intValue();
                                                   int var110 = this.clampAnimationTimelineTick(var109 + var40);
                                                   String var111 = this.buildAnimationTimelineKeyframeRef(var103, var110);
                                                   if (!var111.isBlank()) {
                                                      var74.put(var111, Integer.valueOf(var110));
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }

                                    var2.animationTimelineKeyframeDragRuntimeTickByRef.clear();
                                    var2.animationTimelineKeyframeDragRuntimeTickByRef.putAll(var74);
                                    var2.animationTimelineKeyframeDragRuntimeTickByLogicalTick.clear();

                                    for (int var100 : (List<Integer>)(List)this.resolveAnimationTimelineSelectedTicks(var2, var5, var6)) {
                                       String var104 = this.buildAnimationTimelineKeyframeRef(var6, var100);
                                       int var108 = var104.isBlank() ? var100 : ((Number)var74.getOrDefault(var104, var100)).intValue();
                                       var2.animationTimelineKeyframeDragRuntimeTickByLogicalTick.put(Integer.valueOf(var100), Integer.valueOf(var108));
                                    }

                                    var2.animationTimelineKeyframeDragRuntimeTick = var2.animationTimelineKeyframeDragRuntimeTickByLogicalTick.size() == 1
                                       ? var2.animationTimelineKeyframeDragRuntimeTickByLogicalTick.values().iterator().next()
                                       : -1;
                                    var2.animationTimelineKeyframeDragMutated = true;
                                    this.updateAnimationTimelineSliderVisual(var1, var2, var59);
                                    var2.animationTimelineRenderedSignature = this.buildAnimationTimelineRenderedSignature(var2, var5);
                                    if (var51 <= 1) {
                                       this.applyAnimationTimelinePositionPreview(var1, var2);
                                       this.updateEditorPropertiesSidebar(var1, var2);
                                    }
                                 }
                              } else {
                                 LinkedHashMap var41 = new LinkedHashMap();

                                 for (int var47 : (List<Integer>)(List)var13) {
                                    Object var52 = this.removeAnimationTimelineTickValue(var11, var47);
                                    if (var52 != null) {
                                       var41.put(var47, var52);
                                    }
                                 }

                                 if (!var41.isEmpty()) {
                                    for (Map.Entry<?, ?> var48 : ((Map<?, ?>)var41).entrySet()) {
                                       int var53 = this.clampAnimationTimelineTick((Integer)var48.getKey() + var40);
                                       var11.put(Integer.toString(var53), var48.getValue());
                                    }

                                    Map var45 = this.sortAnimationTimelineTickMap(var11);
                                    var11.clear();
                                    var11.putAll(var45);
                                    LinkedHashMap var49 = new LinkedHashMap();

                                    for (int var24 : (List<Integer>)(List)var13) {
                                       int var25 = this.clampAnimationTimelineTick(var24 + var40);
                                       int var26 = ((Number)var15.getOrDefault(var24, var24)).intValue();
                                       var49.put(Integer.valueOf(var25), Integer.valueOf(var26));
                                    }

                                    int var55 = this.clampAnimationTimelineTick(var38 + var40);
                                    var2.animationTimelineSelectedTick = var55;
                                    var2.animationTimelineTick = var55;
                                    var2.animationTimelineAdditionalSelectedKeyframeRefs.removeIf(var2x -> {
                                       AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef var3x = this.parseAnimationTimelineSelectionRef(var2x);
                                       return var3x != null && this.equalsNullable(var3x.rowKey(), var6);
                                    });
                                    var2.animationTimelineAdditionalSelectedTicks.clear();

                                    for (Map.Entry<?, ?> var66 : ((Map<?, ?>)var41).entrySet()) {
                                       int var75 = this.clampAnimationTimelineTick((Integer)var66.getKey() + var40);
                                       if (var75 != var55) {
                                          var2.animationTimelineAdditionalSelectedTicks.add(Integer.valueOf(var75));
                                       }
                                    }

                                    this.sanitizeAnimationTimelineAdditionalSelection(var2);
                                    boolean var61;
                                    if (var49.size() == 1) {
                                       Entry var67 = (Entry)var49.entrySet().iterator().next();
                                       int var76 = (Integer)var67.getKey();
                                       int var27 = (Integer)var67.getValue();
                                       var61 = this.retimeAnimationTimelineRuntimeKeyframe(var1, var2, var5, var6, var27, var76, true);
                                    } else {
                                       var61 = this.retimeAnimationTimelineRuntimeKeyframes(var1, var2, var5, var6, var49);
                                    }

                                    if (!var61) {
                                       this.renderAnimationTimelineKeyframes(var1, var2, var5);
                                       var49.clear();

                                       for (int var77 : (List<Integer>)(List)var13) {
                                          int var86 = this.clampAnimationTimelineTick(var77 + var40);
                                          var49.put(Integer.valueOf(var86), Integer.valueOf(var86));
                                       }
                                    }

                                    var2.animationTimelineKeyframeDragRuntimeTickByLogicalTick.clear();
                                    var2.animationTimelineKeyframeDragRuntimeTickByLogicalTick.putAll(var49);
                                    var2.animationTimelineKeyframeDragRuntimeTickByRef.clear();

                                    for (Map.Entry<?, ?> var78 : ((Map<?, ?>)var49).entrySet()) {
                                       if (var78 != null) {
                                          String var87 = this.buildAnimationTimelineKeyframeRef(var6, (Integer)var78.getKey());
                                          if (!var87.isBlank()) {
                                             var2.animationTimelineKeyframeDragRuntimeTickByRef.put(var87, (Integer)var78.getValue());
                                          }
                                       }
                                    }

                                    var2.animationTimelineKeyframeDragRuntimeTick = var2.animationTimelineKeyframeDragRuntimeTickByLogicalTick.size() == 1
                                       ? var2.animationTimelineKeyframeDragRuntimeTickByLogicalTick.values().iterator().next()
                                       : -1;
                                    var2.animationTimelineKeyframeDragMutated = true;
                                    this.updateAnimationTimelineSliderVisual(var1, var2, var55);
                                    var2.animationTimelineRenderedSignature = this.buildAnimationTimelineRenderedSignature(var2, var5);
                                    if (var41.size() <= 1) {
                                       this.applyAnimationTimelinePositionPreview(var1, var2);
                                       this.updateEditorPropertiesSidebar(var1, var2);
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

   protected boolean canShiftAnimationTimelineKeyframes(Map<String, Object> var1, List<Integer> var2, int var3) {
      if (var1 != null && var2 != null && !var2.isEmpty() && var3 != 0) {
         LinkedHashSet var4 = new LinkedHashSet();

         for (Integer var6 : (List<Integer>)(List)var2) {
            if (var6 != null) {
               var4.add(Integer.valueOf(this.clampAnimationTimelineTick(var6)));
            }
         }

         if (var4.isEmpty()) {
            return false;
         } else {
            LinkedHashSet var9 = new LinkedHashSet();

            for (int var7 : (List<Integer>)(List)var4) {
               int var8 = var7 + var3;
               if (var8 < 0 || var8 > 400) {
                  return false;
               }

               if (!var9.add(Integer.valueOf(var8))) {
                  return false;
               }

               if (!var4.contains(Integer.valueOf(var8)) && this.containsAnimationTimelineTick(var1, var8)) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   protected void rerenderAnimationTimelineSelectionTarget(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         this.clearAnimationTimelinePreviewOffset(var1, var2);
         this.rerenderEditableContent(var1, var2);
      }
   }

   protected boolean retimeAnimationTimelineRuntimeKeyframes(Player var1, EditorSession var2, String var3, String var4, Map<Integer, Integer> var5) {
      if (var5 != null && !var5.isEmpty()) {
         for (Map.Entry<?, ?> var7 : ((Map<?, ?>)var5).entrySet()) {
            if (var7 != null) {
               int var8 = this.clampAnimationTimelineTick((Integer)var7.getKey());
               int var9 = this.clampAnimationTimelineTick((Integer)var7.getValue());
               if (!this.retimeAnimationTimelineRuntimeKeyframe(var1, var2, var3, var4, var9, var8, false)) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   protected void retimeAnimationTimelineRuntimeKeyframe(Player var1, EditorSession var2, String var3, String var4, int var5, int var6) {
      this.retimeAnimationTimelineRuntimeKeyframe(var1, var2, var3, var4, var5, var6, true);
   }

   protected boolean retimeAnimationTimelineRuntimeKeyframe(Player var1, EditorSession var2, String var3, String var4, int var5, int var6, boolean var7) {
      if (var1 == null || var2 == null || var3 == null || var3.isBlank() || var4 == null || var4.isBlank()) {
         return false;
      } else if (var5 == var6) {
         return true;
      } else {
         String var8 = ANIMATION_ROW_TO_BOX_ID.get(var4);
         EditorRect var9 = var8 == null ? null : this.findShellBlockRect(var2, var8);
         if (var9 == null) {
            return false;
         } else {
            int var10 = this.clampAnimationTimelineTick(var5);
            if (var7 && var2.animationTimelineKeyframeDragRuntimeTick >= 0) {
               var10 = var2.animationTimelineKeyframeDragRuntimeTick;
            }

            String var11 = "animation_timeline_keyframe_" + var4 + "_" + var10;
            String var12 = "editor_shell_" + var11;
            String var13 = var11 + "_hitbox";
            String var14 = "editor_shell_" + var13;
            if (this.hudService.getHud(var1, var12) != null && this.hudService.getHud(var1, var14) != null) {
               double var15 = this.resolveAnimationTimelineKeyframeX(var2, var9, var6);
               if (!Double.isFinite(var15)) {
                  return false;
               } else {
                  double var17 = var9.y + 14.0;
                  double var19 = this.toInternalTextTopY(var17, 64.0);
                  double var21 = this.applyTextAlignmentOffset(var15, 64.0, TextAlignment.LEFT);
                  HudPositionCalculator.Placement var23 = this.positionCalculator.calculateBoxPlacement(var21, var19, 9214.0, 64.0, 64.0);
                  boolean var24 = this.isAnimationTimelineKeyframeRefSelected(var2, var3, var4, var6);
                  String var25 = var24 ? "3e90ff" : "999999";
                  String var26 = this.resolveAnimationTimelineKeyframeGlyph(var2, var3, var4, var6);
                  this.upsertHud(
                     var1, var12, var23.location(), var23.scale(), this.ensureRoundedCornerEditorFont("<#" + var25 + ">" + var26), 255, TextAlignment.LEFT, 200
                  );
                  this.clearOutlineHud(var1, var12);
                  double var27 = var15 + 0.0;
                  double var29 = var17 + -8.0;
                  HudPositionCalculator.Placement var31 = this.positionCalculator.calculateBoxPlacement(var27, var29, 9215.0, 16.0, 16.0);
                  this.upsertHud(var1, var14, var31.location(), var31.scale(), "", 0, TextAlignment.LEFT, 200);
                  this.clearOutlineHud(var1, var14);
                  var2.runtimeAnimationTimelineKeyframeIds.add(var11);
                  var2.runtimeAnimationTimelineKeyframeIds.add(var13);
                  var2.shellRuntimeRects.put(var11, new EditorRect(var15, var17, 64.0, 64.0));
                  var2.shellRuntimeRects.put(var13, new EditorRect(var27, var29, 16.0, 16.0));
                  if (var7) {
                     var2.animationTimelineKeyframeDragRuntimeTick = var10;
                  }

                  return true;
               }
            } else {
               return false;
            }
         }
      }
   }

   protected Object buildAnimationTimelineValueForRow(EditorSession var1, String var2, String var3) {
      if ("rotation".equals(var3)) {
         return this.snap1(this.readTargetRotation(var1, var2));
      } else if ("opacity".equals(var3)) {
         return this.clampAnimationTimelineOpacityRaw((double)this.readTargetOpacity(var1, var2));
      } else {
         EditorRect var4 = null;
         if ("position".equals(var3) && this.isAnimationTimelineImageGroupRoot(var1, var2)) {
            var4 = this.resolveAnimationTimelineImageGroupLogicalBounds(var1, var2);
         }

         if (var4 == null) {
            var4 = this.getTargetBounds(var1, var2);
         }

         if (var4 == null) {
            return null;
         } else if ("position".equals(var3)) {
            int var12 = var1 == null ? 0 : this.clampAnimationTimelineTick(var1.animationTimelineTick);
            double[] var6 = this.resolveAnimationTimelineScaleOffsetAtTick(var1, var2, (double)var12);
            double var7 = var4.x;
            double var9 = var4.y;
            if (var6 != null && var6.length >= 2) {
               if (Double.isFinite(var6[0])) {
                  var7 -= var6[0];
               }

               if (Double.isFinite(var6[1])) {
                  var9 -= var6[1];
               }
            }

            LinkedHashMap var11 = new LinkedHashMap();
            var11.put("x", this.snap1(var7));
            var11.put("y", this.snap1(var9));
            return var11;
         } else if ("scale".equals(var3)) {
            int var5 = var1 == null ? 0 : this.clampAnimationTimelineTick(var1.animationTimelineTick);
            return this.buildAnimationTimelineScaleValue(var1, var2, var5);
         } else {
            return null;
         }
      }
   }

   protected Map<String, Object> buildAnimationTimelineScaleValue(EditorSession var1, String var2, int var3) {
      boolean var4 = this.isAnimationTimelineImageGroupRoot(var1, var2);
      EditorRect var5 = var4 ? this.resolveAnimationTimelineImageGroupLogicalBounds(var1, var2) : null;
      if (var5 == null) {
         var5 = this.getTargetBounds(var1, var2);
      }

      if (var5 == null) {
         return null;
      } else {
         double var6 = var5.width;
         double var8 = var5.height;
         Map var10 = this.resolveRawTargetByTargetId(var1, var2);
         if (var10 != null && !var4) {
            double var11 = this.readMapPathDouble(
               var10, "size.width", this.readMapPathDouble(var10, "width", this.readMapPathDouble(var10, "scale.width", Double.NaN))
            );
            if (Double.isFinite(var11)) {
               var6 = Math.abs(var11);
            }

            double var13 = this.readMapPathDouble(
               var10, "size.height", this.readMapPathDouble(var10, "height", this.readMapPathDouble(var10, "scale.height", Double.NaN))
            );
            if (Double.isFinite(var13)) {
               var8 = Math.abs(var13);
            }
         }

         int var21 = this.clampAnimationTimelineTick(var3);
         EditorRect var12 = this.resolveAnimationTimelineStableBaseBounds(var1, var2);
         if (var12 == null) {
            var12 = new EditorRect(var5.x, var5.y, var5.width, var5.height);
         }

         TreeMap var22 = this.readAnimationTimelinePositionPoints(var1, var2);
         Map var14 = this.readAnimationTimelineInterpolationModes(var1, var2, "position");
         double[] var15 = this.interpolateAnimationTimelineVector2(var22, (double)var21, var12.x, var12.y, var14);
         double var16 = var5.x - var15[0];
         double var18 = var5.y - var15[1];
         LinkedHashMap var20 = new LinkedHashMap();
         var20.put("offsetX", var16);
         var20.put("offsetY", var18);
         var20.put("x", var16);
         var20.put("y", var18);
         var20.put("width", Math.max(0.0, var6));
         var20.put("height", Math.max(0.0, var8));
         return var20;
      }
   }

   protected double[] resolveAnimationTimelineScaleOffsetAtTick(EditorSession var1, String var2, double var3) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         TreeMap var5 = this.readAnimationTimelineScalePoints(var1, var2);
         if (var5.isEmpty()) {
            return null;
         } else {
            TreeMap var6 = this.extractAnimationTimelineScalePositionPoints(var5);
            if (var6.isEmpty()) {
               return null;
            } else {
               Map var7 = this.readAnimationTimelineInterpolationModes(var1, var2, "scale");
               double var8 = Math.max(0.0, Math.min(400.0, var3));
               return this.interpolateAnimationTimelineVector2(var6, var8, 0.0, 0.0, var7);
            }
         }
      } else {
         return null;
      }
   }

   protected Map<String, Object> ensureAnimationTimelineRowMap(Map<String, Object> var1, String var2) {
      Map var3 = this.ensureChildMap(var1, "editor_animation");
      Map var4 = this.ensureChildMap(var3, "keyframes");
      return this.ensureChildMap(var4, var2);
   }

   protected Map<String, Object> readAnimationTimelineRowMap(Map<String, Object> var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         if (var1.get("editor_animation") instanceof Map var4) {
            if (var4.get("keyframes") instanceof Map var6) {
               Object var7 = var6.get(var2);
               return !(var7 instanceof Map) ? null : (Map)var7;
            } else {
               return null;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   protected Map<String, Object> ensureChildMap(Map<String, Object> var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Object var3 = var1.get(var2);
         if (var3 instanceof Map) {
            return (Map<String, Object>)var3;
         } else {
            LinkedHashMap var4 = new LinkedHashMap();
            var1.put(var2, var4);
            return var4;
         }
      } else {
         return new LinkedHashMap<>();
      }
   }

   protected Map<String, Object> sortAnimationTimelineTickMap(Map<String, Object> var1) {
      TreeMap var2 = new TreeMap();
      if (var1 != null) {
         for (Map.Entry<?, ?> var4 : ((Map<?, ?>)var1).entrySet()) {
            if (var4 != null && var4.getKey() != null) {
               double var5 = this.parseDouble(var4.getKey(), Double.NaN);
               if (Double.isFinite(var5)) {
                  int var7 = this.clampAnimationTimelineTick((int)Math.round(var5));
                  var2.putIfAbsent(var7, var4.getValue());
               }
            }
         }
      }

      LinkedHashMap var8 = new LinkedHashMap();

      for (Map.Entry<?, ?> var10 : ((Map<?, ?>)var2).entrySet()) {
         var8.put(Integer.toString((Integer)var10.getKey()), var10.getValue());
      }

      return var8;
   }

   protected boolean containsAnimationTimelineTick(Map<String, Object> var1, int var2) {
      return this.readAnimationTimelineTickValue(var1, var2) != null;
   }

   protected Object readAnimationTimelineTickValue(Map<String, Object> var1, int var2) {
      if (var1 != null && !var1.isEmpty()) {
         int var3 = this.clampAnimationTimelineTick(var2);

         for (Map.Entry<?, ?> var5 : ((Map<?, ?>)var1).entrySet()) {
            if (var5 != null && var5.getKey() != null) {
               double var6 = this.parseDouble(var5.getKey(), Double.NaN);
               if (Double.isFinite(var6) && this.clampAnimationTimelineTick((int)Math.round(var6)) == var3) {
                  return var5.getValue();
               }
            }
         }

         return null;
      } else {
         return null;
      }
   }

   protected Object removeAnimationTimelineTickValue(Map<String, Object> var1, int var2) {
      if (var1 != null && !var1.isEmpty()) {
         int var3 = this.clampAnimationTimelineTick(var2);
         String var4 = null;

         for (String var6 : var1.keySet()) {
            if (var6 != null) {
               double var7 = this.parseDouble(var6, Double.NaN);
               if (Double.isFinite(var7) && this.clampAnimationTimelineTick((int)Math.round(var7)) == var3) {
                  var4 = var6;
                  break;
               }
            }
         }

         return var4 == null ? null : var1.remove(var4);
      } else {
         return null;
      }
   }

   protected AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef resolveAnimationTimelineHoveredKeyframe(
      EditorSession var1, double var2, double var4
   ) {
      if (var1 != null && !var1.runtimeAnimationTimelineKeyframeIds.isEmpty()) {
         HashSet var6 = new HashSet();
         AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef var7 = null;
         double var8 = Double.MAX_VALUE;

         for (Object var11_raw : var1.runtimeAnimationTimelineKeyframeIds) {
            String var11 = var11_raw != null ? var11_raw.toString() : null;
            AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef var12 = this.parseAnimationTimelineKeyframeRef(var11);
            if (var12 != null) {
               String var13 = var12.rowKey() + ":" + var12.tick();
               if (var6.add(var13)) {
                  String var14 = "animation_timeline_keyframe_" + var12.rowKey() + "_" + var12.tick();
                  EditorRect var15 = this.findShellBlockRect(var1, var14 + "_hitbox");
                  if (this.isInsideRect(var15, var2, var4)) {
                     return var12;
                  }

                  if (var15 == null) {
                     EditorRect var16 = this.findShellBlockRect(var1, var14);
                     if (this.isInsideRect(var16, var2, var4)) {
                        double var17 = var16.x + var16.width / 2.0;
                        double var19 = var16.y + var16.height / 2.0;
                        double var21 = var2 - var17;
                        double var23 = var4 - var19;
                        double var25 = var21 * var21 + var23 * var23;
                        if (var7 == null || var25 < var8) {
                           var7 = var12;
                           var8 = var25;
                        }
                     }
                  }
               }
            }
         }

         return var7;
      } else {
         return null;
      }
   }

   protected AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef parseAnimationTimelineKeyframeRef(String var1) {
      if (var1 != null && !var1.isBlank()) {
         String var2 = var1;
         if (var1.endsWith("_hitbox")) {
            var2 = var1.substring(0, var1.length() - "_hitbox".length());
         }

         if (!var2.startsWith("animation_timeline_keyframe_")) {
            return null;
         } else {
            String var3 = var2.substring("animation_timeline_keyframe_".length());
            int var4 = var3.lastIndexOf(95);
            if (var4 > 0 && var4 < var3.length() - 1) {
               String var5 = var3.substring(0, var4).toLowerCase(Locale.ROOT);
               if (!ANIMATION_ROW_TO_BOX_ID.containsKey(var5)) {
                  return null;
               } else {
                  double var6 = this.parseDouble(var3.substring(var4 + 1), Double.NaN);
                  if (!Double.isFinite(var6)) {
                     return null;
                  } else {
                     int var8 = this.clampAnimationTimelineTick((int)Math.round(var6));
                     return new AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef(var5, var8);
                  }
               }
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   protected List<Integer> readAnimationTimelineTicks(EditorSession var1, String var2, String var3) {
      if (var1 != null && var2 != null && !var2.isBlank() && var3 != null && !var3.isBlank()) {
         Map var4 = this.resolveRawTargetByTargetId(var1, var2);
         Map var5 = this.readAnimationTimelineRowMap(var4, var3);
         if (var5 != null && !var5.isEmpty()) {
            TreeMap var6 = new TreeMap();

            for (String var8 : (Set<String>)(Set)var5.keySet()) {
               double var9 = this.parseDouble(var8, Double.NaN);
               if (Double.isFinite(var9)) {
                  var6.put(this.clampAnimationTimelineTick((int)Math.round(var9)), Boolean.TRUE);
               }
            }

            return new ArrayList<>(var6.keySet());
         } else {
            return Collections.emptyList();
         }
      } else {
         return Collections.emptyList();
      }
   }

   protected TreeMap<Integer, double[]> readAnimationTimelinePositionPoints(EditorSession var1, String var2) {
      TreeMap var3 = new TreeMap();
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var4 = this.resolveRawTargetByTargetId(var1, var2);
         Map var5 = this.readAnimationTimelineRowMap(var4, "position");
         if (var5 != null && !var5.isEmpty()) {
            TreeMap var6 = new TreeMap();

            for (Map.Entry<?, ?> var8 : ((Map<?, ?>)var5).entrySet()) {
               if (var8 != null && var8.getValue() != null) {
                  double var9 = this.parseDouble(var8.getKey(), Double.NaN);
                  if (Double.isFinite(var9) && var8.getValue() instanceof Map var11) {
                     int var35 = this.clampAnimationTimelineTick((int)Math.round(var9));
                     var6.put(var35, var11);
                  }
               }
            }

            if (var6.isEmpty()) {
               return var3;
            } else {
               EditorRect var33 = this.resolveAnimationTimelineStableBaseBounds(var1, var2);
               double var34 = 0.0;
               double var10 = 0.0;
               if (var33 != null) {
                  var34 = var33.x;
                  var10 = var33.y;
               } else {
                  EditorRect var36 = this.getTargetBounds(var1, var2);
                  if (var36 != null) {
                     var34 = var36.x;
                     var10 = var36.y;
                  }
               }

               for (Map.Entry<?, ?> var13 : ((Map<?, ?>)var6).entrySet()) {
                  int var14 = (Integer)var13.getKey();
                  Map var15 = (Map)var13.getValue();
                  if (var15 != null && !var15.isEmpty()) {
                     double[] var16 = this.interpolateAnimationTimelineVector2(var3, (double)var14, var34, var10);
                     double var17 = this.parseDouble(var15.get("x"), Double.NaN);
                     double var19 = this.parseDouble(var15.get("y"), Double.NaN);
                     double var21 = this.parseDouble(var15.get("addx"), this.parseDouble(var15.get("addX"), Double.NaN));
                     double var23 = this.parseDouble(var15.get("addy"), this.parseDouble(var15.get("addY"), Double.NaN));
                     boolean var25 = Double.isFinite(var17);
                     boolean var26 = Double.isFinite(var19);
                     boolean var27 = Double.isFinite(var21);
                     boolean var28 = Double.isFinite(var23);
                     if (var25 || var26 || var27 || var28) {
                        double var29 = var16[0];
                        double var31 = var16[1];
                        if (var25) {
                           var29 = var17;
                        } else if (var27) {
                           var29 = var16[0] + var21;
                        }

                        if (var26) {
                           var31 = var19;
                        } else if (var28) {
                           var31 = var16[1] + var23;
                        }

                        if (Double.isFinite(var29) && Double.isFinite(var31)) {
                           var3.put(var14, new double[]{var29, var31});
                        }
                     }
                  }
               }

               return var3;
            }
         } else {
            return var3;
         }
      } else {
         return var3;
      }
   }

   protected TreeMap<Integer, double[]> readAnimationTimelineScalePoints(EditorSession var1, String var2) {
      TreeMap var3 = new TreeMap();
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var4 = this.resolveRawTargetByTargetId(var1, var2);
         Map var5 = this.readAnimationTimelineRowMap(var4, "scale");
         if (var5 != null && !var5.isEmpty()) {
            TreeMap var6 = this.readAnimationTimelinePositionPoints(var1, var2);
            Map var7 = this.readAnimationTimelineInterpolationModes(var1, var2, "position");
            EditorRect var8 = this.resolveAnimationTimelineStableBaseBounds(var1, var2);
            double var9 = 0.0;
            double var11 = 0.0;
            if (var8 != null) {
               var9 = var8.x;
               var11 = var8.y;
            } else {
               EditorRect var13 = this.isAnimationTimelineImageGroupRoot(var1, var2) ? this.resolveAnimationTimelineImageGroupLogicalBounds(var1, var2) : null;
               if (var13 == null) {
                  var13 = this.getTargetBounds(var1, var2);
               }

               if (var13 != null) {
                  var9 = var13.x;
                  var11 = var13.y;
               }
            }

            for (Map.Entry<?, ?> var14 : ((Map<?, ?>)var5).entrySet()) {
               double var15 = this.parseDouble(var14.getKey(), Double.NaN);
               if (Double.isFinite(var15)) {
                  int var17 = this.clampAnimationTimelineTick((int)Math.round(var15));
                  Object var19 = var14.getValue();
                  if (var19 instanceof Map) {
                     Map var18 = (Map)var19;
                     double var20 = this.parseDouble(var18.get("width"), Double.NaN);
                     double var22 = this.parseDouble(var18.get("height"), Double.NaN);
                     if (Double.isFinite(var20) && Double.isFinite(var22)) {
                        double var24 = this.parseDouble(var18.get("offsetX"), Double.NaN);
                        double var26 = this.parseDouble(var18.get("offsetY"), Double.NaN);
                        if (!Double.isFinite(var24) || !Double.isFinite(var26)) {
                           double var28 = this.parseDouble(var18.get("x"), Double.NaN);
                           double var30 = this.parseDouble(var18.get("y"), Double.NaN);
                           if (Double.isFinite(var28) && Double.isFinite(var30)) {
                              double[] var32 = this.interpolateAnimationTimelineVector2(var6, (double)var17, var9, var11, var7);
                              var24 = var28 - var32[0];
                              var26 = var30 - var32[1];
                           }
                        }

                        if (!Double.isFinite(var24)) {
                           var24 = 0.0;
                        }

                        if (!Double.isFinite(var26)) {
                           var26 = 0.0;
                        }

                        var3.put(var17, new double[]{var24, var26, var20, var22});
                     }
                  }
               }
            }

            return var3;
         } else {
            return var3;
         }
      } else {
         return var3;
      }
   }

   protected TreeMap<Integer, Double> readAnimationTimelineRotationPoints(EditorSession var1, String var2) {
      TreeMap var3 = new TreeMap();
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var4 = this.resolveRawTargetByTargetId(var1, var2);
         Map var5 = this.readAnimationTimelineRowMap(var4, "rotation");
         if (var5 != null && !var5.isEmpty()) {
            double var6 = this.resolveAnimationTimelineStableBaseRotation(var1, var2);

            for (Map.Entry<?, ?> var9 : ((Map<?, ?>)var5).entrySet()) {
               if (var9 != null) {
                  double var10 = this.parseDouble(var9.getKey(), Double.NaN);
                  if (Double.isFinite(var10)) {
                     int var12 = this.clampAnimationTimelineTick((int)Math.round(var10));
                     Object var13 = var9.getValue();
                     if (var13 != null) {
                        double var14 = Double.NaN;
                        if (var13 instanceof Number var16) {
                           var14 = var16.doubleValue();
                        } else if (var13 instanceof Map) {
                           Map var17 = (Map)var13;
                           double var19 = this.readMapPathDouble(var17, "value", this.readMapPathDouble(var17, "rotation", Double.NaN));
                           if (Double.isFinite(var19)) {
                              var14 = var19;
                           } else {
                              double var21 = this.parseDouble(
                                 var17.get("add"), this.parseDouble(var17.get("addRotation"), this.parseDouble(var17.get("addrotation"), Double.NaN))
                              );
                              if (Double.isFinite(var21)) {
                                 double var23 = this.interpolateAnimationTimelineChannel(var3, (double)var12, var6);
                                 var14 = var23 + var21;
                              }
                           }
                        }

                        if (Double.isFinite(var14)) {
                           var3.put(var12, this.normalizeSidebarRotation(var14));
                        }
                     }
                  }
               }
            }

            return var3;
         } else {
            return var3;
         }
      } else {
         return var3;
      }
   }

   protected TreeMap<Integer, Double> readAnimationTimelineOpacityPoints(EditorSession var1, String var2) {
      TreeMap var3 = new TreeMap();
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var4 = this.resolveRawTargetByTargetId(var1, var2);
         Map var5 = this.readAnimationTimelineRowMap(var4, "opacity");
         if (var5 != null && !var5.isEmpty()) {
            double var6 = this.resolveAnimationTimelineStableBaseOpacity(var1, var2);

            for (Map.Entry<?, ?> var9 : ((Map<?, ?>)var5).entrySet()) {
               if (var9 != null) {
                  double var10 = this.parseDouble(var9.getKey(), Double.NaN);
                  if (Double.isFinite(var10)) {
                     int var12 = this.clampAnimationTimelineTick((int)Math.round(var10));
                     Object var13 = var9.getValue();
                     if (var13 != null) {
                        double var14 = Double.NaN;
                        if (var13 instanceof Number var16) {
                           var14 = var16.doubleValue();
                        } else if (var13 instanceof Map) {
                           Map var17 = (Map)var13;
                           double var19 = this.readMapPathDouble(var17, "value", this.readMapPathDouble(var17, "opacity", Double.NaN));
                           if (Double.isFinite(var19)) {
                              var14 = var19;
                           } else {
                              double var21 = this.parseDouble(
                                 var17.get("add"), this.parseDouble(var17.get("addOpacity"), this.parseDouble(var17.get("addopacity"), Double.NaN))
                              );
                              if (Double.isFinite(var21)) {
                                 double var23 = this.interpolateAnimationTimelineChannel(var3, (double)var12, var6);
                                 var14 = var23 + var21;
                              }
                           }
                        }

                        if (Double.isFinite(var14)) {
                           var3.put(var12, (double)this.clampAnimationTimelineOpacityRaw(var14));
                        }
                     }
                  }
               }
            }

            return var3;
         } else {
            return var3;
         }
      } else {
         return var3;
      }
   }

   protected void renderAnimationTimelineKeyframes(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String var4 = this.buildAnimationTimelineRenderedSignature(var2, var3);
         if (this.equalsNullable(var2.animationTimelineRenderedSignature, var4)) {
            if (var2.runtimeAnimationTimelineKeyframeIds.isEmpty()) {
               return;
            }

            boolean var5 = false;

            for (Object var7_raw : var2.runtimeAnimationTimelineKeyframeIds) {
               String var7 = var7_raw != null ? var7_raw.toString() : null;
               if (var7 != null && !var7.isBlank() && this.hudService.getHud(var1, "editor_shell_" + var7) == null) {
                  var5 = true;
                  break;
               }
            }

            if (!var5) {
               return;
            }
         }

         LinkedHashSet var9 = new LinkedHashSet();
         this.renderAnimationTimelineKeyframesRow(var1, var2, var3, "rotation", var9);
         this.renderAnimationTimelineKeyframesRow(var1, var2, var3, "position", var9);
         this.renderAnimationTimelineKeyframesRow(var1, var2, var3, "scale", var9);
         this.renderAnimationTimelineKeyframesRow(var1, var2, var3, "opacity", var9);
         if (!var2.runtimeAnimationTimelineKeyframeIds.isEmpty()) {
            ArrayList var10 = new ArrayList();

            for (Object var8_raw : var2.runtimeAnimationTimelineKeyframeIds) {
               String var8 = var8_raw != null ? var8_raw.toString() : null;
               if (var8 != null && !var8.isBlank() && !var9.contains(var8)) {
                  var10.add(var8);
               }
            }

            for (Object var13_raw : var10) {
               String var13 = var13_raw != null ? var13_raw.toString() : null;
               this.removeAnimationTimelineRuntimeKeyframe(var1, var2, var13);
            }
         }

         var2.runtimeAnimationTimelineKeyframeIds.clear();
         var2.runtimeAnimationTimelineKeyframeIds.addAll(var9);
         var2.animationTimelineRenderedSignature = var4;
      } else {
         this.clearAnimationTimelineRuntimeKeyframes(var1, var2);
      }
   }

   protected void renderAnimationTimelineKeyframesRow(Player var1, EditorSession var2, String var3, String var4, Set<String> var5) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var4 != null && !var4.isBlank()) {
         String var6 = ANIMATION_ROW_TO_BOX_ID.get(var4);
         if (var6 != null && !var6.isBlank()) {
            EditorRect var7 = this.findShellBlockRect(var2, var6);
            if (var7 != null) {
               for (int var9 : this.readAnimationTimelineTicks(var2, var3, var4)) {
                  String var10 = "animation_timeline_keyframe_" + var4 + "_" + var9;
                  String var11 = "editor_shell_" + var10;
                  double var12 = this.resolveAnimationTimelineKeyframeX(var2, var7, var9);
                  if (Double.isFinite(var12)) {
                     double var14 = var7.y + 14.0;
                     double var16 = this.toInternalTextTopY(var14, 64.0);
                     double var18 = this.applyTextAlignmentOffset(var12, 64.0, TextAlignment.LEFT);
                     boolean var20 = this.isAnimationTimelineKeyframeRefSelected(var2, var3, var4, var9);
                     String var21 = var20 ? "3e90ff" : "999999";
                     String var22 = this.resolveAnimationTimelineKeyframeGlyph(var2, var3, var4, var9);
                     HudPositionCalculator.Placement var23 = this.positionCalculator.calculateBoxPlacement(var18, var16, 9214.0, 64.0, 64.0);
                     this.upsertHud(
                        var1,
                        var11,
                        var23.location(),
                        var23.scale(),
                        this.ensureRoundedCornerEditorFont("<#" + var21 + ">" + var22),
                        255,
                        TextAlignment.LEFT,
                        200
                     );
                     this.clearOutlineHud(var1, var11);
                     if (var5 != null) {
                        var5.add(var10);
                     }

                     var2.shellRuntimeRects.put(var10, new EditorRect(var12, var14, 64.0, 64.0));
                     String var24 = var10 + "_hitbox";
                     String var25 = "editor_shell_" + var24;
                     double var26 = var12 + 0.0;
                     double var28 = var14 + -8.0;
                     HudPositionCalculator.Placement var30 = this.positionCalculator.calculateBoxPlacement(var26, var28, 9215.0, 16.0, 16.0);
                     this.upsertHud(var1, var25, var30.location(), var30.scale(), "", 0, TextAlignment.LEFT, 200);
                     this.clearOutlineHud(var1, var25);
                     if (var5 != null) {
                        var5.add(var24);
                     }

                     var2.shellRuntimeRects.put(var24, new EditorRect(var26, var28, 16.0, 16.0));
                  }
               }
            }
         }
      }
   }

   protected double resolveAnimationTimelineKeyframeX(EditorSession var1, EditorRect var2, int var3) {
      double var4 = this.sliderTickToX(var1, var3);
      if (Double.isFinite(var4)) {
         return var4;
      } else {
         if (var1 != null) {
            EditorRect var6 = this.findShellBlockRect(var1, "animation_slider_hitbox");
            if (var6 != null) {
               int var7 = this.clampAnimationTimelineTick(var3);
               return var6.x + -2.0 + 3.05 * (double)var7;
            }
         }

         return Double.NaN;
      }
   }

   protected String buildAnimationTimelineRenderedSignature(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         StringBuilder var3 = new StringBuilder(var2);
         this.appendAnimationTimelineSignatureRow(var3, var1, var2, "rotation");
         this.appendAnimationTimelineSignatureRow(var3, var1, var2, "position");
         this.appendAnimationTimelineSignatureRow(var3, var1, var2, "scale");
         this.appendAnimationTimelineSignatureRow(var3, var1, var2, "opacity");
         TreeSet var4 = new TreeSet();
         if (this.isAnimationTimelineKeyframeSelected(var1)
            && this.equalsNullable(this.firstNonBlank(new String[]{var1.animationTimelineSelectedTargetId}), var2)) {
            String var5 = this.firstNonBlank(new String[]{var1.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT);
            String var6 = this.buildAnimationTimelineKeyframeRef(var5, var1.animationTimelineSelectedTick);
            if (!var6.isBlank()) {
               var4.add(var6);
            }

            for (Object var8_raw : var1.animationTimelineAdditionalSelectedTicks) {
               int var8 = ((Number)var8_raw).intValue(); {
                  String var9 = this.buildAnimationTimelineKeyframeRef(var5, var8);
                  if (!var9.isBlank()) {
                     var4.add(var9);
                  }
               }
            }

            for (Object var14_raw : var1.animationTimelineAdditionalSelectedKeyframeRefs) {
               String var14 = var14_raw != null ? var14_raw.toString() : null;
               AnimationTimelineSelectionManager.AnimationTimelineKeyframeRef var15 = this.parseAnimationTimelineSelectionRef(var14);
               if (var15 != null) {
                  String var10 = this.buildAnimationTimelineKeyframeRef(var15.rowKey(), var15.tick());
                  if (!var10.isBlank()) {
                     var4.add(var10);
                  }
               }
            }
         }

         if (!var4.isEmpty()) {
            var3.append("|selected:");

            for (Object var12_raw : var4) {
               String var12 = var12_raw != null ? var12_raw.toString() : null;
               var3.append(var12).append(',');
            }
         } else {
            var3.append("|selected:none");
         }

         return var3.toString();
      } else {
         return "";
      }
   }

   protected void appendAnimationTimelineSignatureRow(StringBuilder var1, EditorSession var2, String var3, String var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var4 != null && !var4.isBlank()) {
         var1.append('|').append(var4).append(':');
         Map var5 = this.readAnimationTimelineInterpolationModes(var2, var3, var4);

         for (int var7 : this.readAnimationTimelineTicks(var2, var3, var4)) {
            var1.append(var7);
            String var8 = this.normalizeAnimationTimelineInterpolationMode((String)var5.get(var7));
            if (!"linear".equals(var8)) {
               var1.append('@').append(var8);
            }

            var1.append(',');
         }
      }
   }

   protected void clearAnimationTimelineRuntimeKeyframes(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         if (!var2.runtimeAnimationTimelineKeyframeIds.isEmpty()) {
            for (String var5 : new ArrayList<>(var2.runtimeAnimationTimelineKeyframeIds)) {
               this.removeAnimationTimelineRuntimeKeyframe(var1, var2, var5);
            }

            var2.runtimeAnimationTimelineKeyframeIds.clear();
         }

         var2.animationTimelineRenderedSignature = null;
      }
   }

   protected void removeAnimationTimelineRuntimeKeyframe(Player var1, EditorSession var2, String var3) {
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

   protected void syncAnimationTimelineSelectedKeyframeValueFromTarget(EditorSession var1) {
      this.syncAnimationTimelineSelectedKeyframeValueFromTarget(var1, null, null);
   }

   protected void syncAnimationTimelineSelectedKeyframeValueFromTarget(EditorSession var1, Double var2) {
      this.syncAnimationTimelineSelectedKeyframeValueFromTarget(var1, EditorPropertyField.ROTATION, var2);
   }

   protected void syncAnimationTimelineSelectedKeyframeValueFromTarget(EditorSession var1, EditorPropertyField var2, Double var3) {
      if (this.isAnimationTimelineKeyframeSelected(var1)) {
         String var4 = this.firstNonBlank(new String[]{var1.animationTimelineSelectedTargetId});
         String var5 = this.firstNonBlank(new String[]{var1.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT);
         if (!var4.isBlank() && !var5.isBlank()) {
            String var6 = this.resolveAnimationTimelineEditableTargetId(var1);
            if (this.equalsNullable(var4, var6)) {
               Map var7 = this.resolveRawTargetByTargetId(var1, var4);
               if (var7 != null) {
                  Map var8 = this.ensureAnimationTimelineRowMap(var7, var5);
                  int var9 = this.clampAnimationTimelineTick(var1.animationTimelineSelectedTick);
                  int var11 = this.clampAnimationTimelineTick(var1.animationTimelineTick);
                  int var12 = var9;
                  boolean var13 = false;
                  boolean var14 = var2 != null && var3 != null && Double.isFinite(var3) && this.isAnimationTimelineForceableSidebarField(var2);
                  String var15 = var14 ? this.firstNonBlank(new String[]{this.resolveAnimationTimelineRowForSidebarField(var2)}).toLowerCase(Locale.ROOT) : "";
                  boolean var16 = var14 && this.equalsNullable(var15, var5);
                  if (!var14 || var16) {
                     boolean var17 = !var1.moveDragActive && !var1.sidebarFieldDragActive && var1.activeHandle == null && !var16 && !"scale".equals(var5);
                     if (var11 != var9 && var17) {
                        var12 = var11;
                        var1.animationTimelineSelectedTick = var11;
                        var1.animationTimelineTick = var11;
                        this.clearAnimationTimelineAdditionalSelection(var1);
                        var13 = true;
                     }

                     Object var18 = this.readAnimationTimelineTickValue(var8, var12);
                     Object var19 = null;
                     if (var16) {
                        var19 = this.resolveAnimationTimelineForcedSidebarValue(var1, var4, var5, var12, var2, var3, var18);
                     }

                     if (var19 == null) {
                        var19 = "scale".equals(var5)
                           ? this.buildAnimationTimelineScaleValue(var1, var4, var12)
                           : this.buildAnimationTimelineValueForRow(var1, var4, var5);
                     }

                     if (var19 != null) {
                        String var20 = this.resolveAnimationTimelinePreservedInterpolationMode(var8, var18, var9, var12);
                        if (!"linear".equals(var20)) {
                           if ("rotation".equals(var5) && var19 instanceof Number var21) {
                              LinkedHashMap var26 = new LinkedHashMap();
                              var26.put("value", this.normalizeSidebarRotation(var21.doubleValue()));
                              var26.put("interpolation", var20);
                              var19 = var26;
                           } else if (var19 instanceof Map var22) {
                              Map var23 = this.toStringObjectMap(var22);
                              if (var23 != null && !var23.isEmpty()) {
                                 var23.put("interpolation", var20);
                                 var19 = var23;
                              }
                           }
                        }

                        if (this.animationTimelineValuesEqual(var18, var19)) {
                           if (var13) {
                              var1.animationTimelineRenderedSignature = null;
                           }
                        } else {
                           this.removeAnimationTimelineTickValue(var8, var12);
                           var8.put(Integer.toString(var12), var19);
                           Map var24 = this.sortAnimationTimelineTickMap(var8);
                           var8.clear();
                           var8.putAll(var24);
                           var1.animationTimelineRenderedSignature = null;
                           boolean var25 = var12 > 0
                              && ("position".equals(var5) || "scale".equals(var5))
                              && !var1.moveDragActive
                              && !var1.sidebarFieldDragActive
                              && !this.isLayersLeftHeld(var1);
                           if (var25) {
                              if ("position".equals(var5)) {
                                 this.restoreAnimationTimelineRawTargetPositionToImplicitBase(var1, var4);
                              } else {
                                 this.restoreAnimationTimelineRawTargetToImplicitBase(var1, var4);
                              }

                              this.syncAnimationTimelinePreviewAppliedBoundsToCurrent(var1, var4);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected static record HoverEffectRuntimeConfig(
      double scaleXMultiplier,
      double scaleYMultiplier,
      double offsetX,
      double offsetY,
      boolean opacityAbsolute,
      int opacityAbsoluteValue,
      int opacityDelta,
      double opacityPercentDelta,
      double rotationDeltaDeg,
      int durationTicks,
      String interpolationMode,
      double startOffsetX,
      double startOffsetY,
      double startScaleXMultiplier,
      double startScaleYMultiplier,
      boolean closeReversed
   ) {
   }
}
