package dev.xqedii.ultimateUI.service.gui.editor;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.api.event.UltimateUIBlockClickEvent;
import dev.xqedii.ultimateUI.gui.model.HoverElement;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.GuiService;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.hud.HudPositionCalculator;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.service.resourcepack.UiImageAtlasService;
import dev.xqedii.ultimateUI.util.PlatformCompat;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.bukkit.inventory.ItemStack;

public abstract class GuiServiceEditorSupportA extends GuiServiceEditorInteractionSupport {
   protected static final LegacyComponentSerializer LEGACY_AMPERSAND = LegacyComponentSerializer.legacyAmpersand();
   protected static final String SAVE_POPUP_TEXT_COLOR = "<#999999>";
   protected static final String SAVE_POPUP_NAME_PLACEHOLDER = "Example name";
   protected static final String SAVE_POPUP_DESC_PLACEHOLDER = "Example description";
   protected static final String SAVE_POPUP_COMMAND_PLACEHOLDER = "<font:default>/<font:editor>example";
   protected static final int SAVE_POPUP_DISPLAY_LIMIT = 28;
   protected static final String PREFERENCES_ENABLED_TEXT = "Enabled";
   protected static final String PREFERENCES_DISABLED_TEXT = "Disabled";
   protected static final String PREFERENCES_DISABLED_COLOR = "141414";
   protected static final String PREFERENCES_ENABLED_COLOR = "262626";
   protected static final String PREFERENCES_DISABLED_TEXT_COLOR = "666666";
   protected static final String PREFERENCES_ENABLED_TEXT_COLOR = "919191";
   protected static final String PREFERENCES_HOTBAR_VALUE_ID = "popup_preferences_hotbar_value";
   protected static final String PREFERENCES_HAND_VALUE_ID = "popup_preferences_hand_value";
   protected static final String PREFERENCES_OPTIMIZED_VALUE_ID = "popup_preferences_optimized_value";
   protected static final String PREFERENCES_SOUND_VALUE_ID = "popup_preferences_sound_value";
   protected static final String PREFERENCES_HOTBAR_BUTTON_ID = "popup_preferences_hotbar_button";
   protected static final String PREFERENCES_HAND_BUTTON_ID = "popup_preferences_hand_button";
   protected static final String PREFERENCES_OPTIMIZED_BUTTON_ID = "popup_preferences_optimized_button";
   protected static final String PREFERENCES_SOUND_BUTTON_ID = "popup_preferences_sound_button";
   protected static final String PREVIEW_UI_ADDONS_HAND_ICON_ID = "preview_ui_addons_hand_icon";
   protected static final String PREVIEW_UI_ADDONS_HOTBAR_ICON_ID = "preview_ui_addons_hotbar_icon";
   protected static final String PREVIEW_UI_ADDONS_HAND_ICON_UNICODE = "\ue1b3";
   protected static final String PREVIEW_UI_ADDONS_HOTBAR_ICON_UNICODE = "\ue1b4";
   protected static final String EDITOR_PAGE_NAME = "editor";
   protected static final String EDITOR_EMPTY_PAGE_NAME = "editor_empty";
   protected static final String EDITOR_MENU_PAGE_NAME = "editor_menu";
   protected static final String UUI_PERMISSION_ADMIN = "ultimateui.admin";
   protected static final String UNTITLED_PAGE_KEY_BASE = "untitled";
   protected static final String UNTITLED_PAGE_DISPLAY_BASE = "Untitled";
   protected static final String PAGE_SWITCH_LEFT_SIDEBAR_ID = "left_sidebar";
   protected static final String PAGE_SWITCH_RIGHT_SIDEBAR_ID = "right_sidebar";
   protected static final String PAGE_SWITCH_FILE_DROPDOWN_ID = "file_dropdown";
   protected static final String PAGE_SWITCH_EDIT_DROPDOWN_ID = "edit_dropdown";
   protected static final String PAGE_SWITCH_SELECTION_DROPDOWN_ID = "selection_dropdown";
   protected static final String PAGE_SWITCH_LAYER_DROPDOWN_ID = "layer_dropdown";
   protected static final String PAGE_SWITCH_WINDOW_DROPDOWN_ID = "window_dropdown";
   protected static final String PAGE_SWITCH_CURSOR_PAGE_DROPDOWN_ID = "cursor_page_dropdown";
   protected static final String PAGE_SWITCH_CURSOR_LAYERS_DROPDOWN_ID = "cursor_layers_dropdown";
   protected static final String PAGE_SWITCH_CURSOR_ACTIONS_DROPDOWN_ID = "cursor_actions_dropdown";
   protected static final String PAGE_SWITCH_CURSOR_ACTIONS_EDIT_DROPDOWN_ID = "cursor_actions_edit_dropdown";
   protected static final String PAGE_SWITCH_FOOTER_ID = "footer";
   protected static final String PAGE_SWITCH_COLOR_PICKER_ID = "colorpicker_ui";
   protected static final String PAGE_SWITCH_PROPERTIES_PANEL_ID = "properties";
   protected static final String PAGE_SWITCH_ACTIONS_PANEL_ID = "actions";
   protected static final String PAGE_SWITCH_DESIGN_PANEL_ID = "design";
   protected static final String PAGE_SWITCH_ITEM_DESIGN_PANEL_ID = "item_design";
   protected static final String PAGE_SWITCH_LAYERS_PANEL_ID = "layers";
   protected static final String PREVIEW_SIZE_TOAST_PANEL_ID = "preview_size";
   protected static final long PREVIEW_SIZE_TOAST_REVEAL_DELAY_TICKS = 1L;
   protected static final long PREVIEW_SIZE_TOAST_DURATION_TICKS = 70L;
   protected static final long[] PREVIEW_SIZE_TOAST_ANCHOR_SYNC_DELAYS = new long[]{1L, 3L, 8L};
   protected static final long[] PREVIEW_UI_ADDONS_ANCHOR_SYNC_DELAYS = new long[]{1L, 3L, 8L};
   protected static final long[] HOTBAR_MASK_REFRESH_DELAYS = new long[]{1L, 3L, 8L};
   protected static final String RUNTIME_ANIMATION_ROW_POSITION = "position";
   protected static final String RUNTIME_ANIMATION_ROW_SCALE = "scale";
   protected static final String RUNTIME_ANIMATION_ROW_ROTATION = "rotation";
   protected static final String RUNTIME_ANIMATION_ROW_OPACITY = "opacity";
   protected static final String RUNTIME_ANIMATION_DELAY_PATH = "editor_animation.delay";
   protected static final Pattern RUNTIME_SOUND_WITH_VOLUME_PITCH_PATTERN = Pattern.compile(
      "^(.*?)(?:\\s*:\\s*|\\s+)([-+]?\\d+(?:[\\.,]\\d+)?)(?:\\s*,\\s*|\\s+)([-+]?\\d+(?:[\\.,]\\d+)?)\\s*$", 2
   );
   protected static final Pattern RUNTIME_MESSAGE_TOOLTIP_LINK_PATTERN = Pattern.compile("(?is)<tooltip:([^>]+)>\\s*<link:([^>]+)>(.*?)<reset>");
   protected static final Pattern INLINE_HEX_GRADIENT_INPUT_PATTERN = Pattern.compile("<#([0-9a-fA-F]{6})>(.*?)</#([0-9a-fA-F]{6})>", 32);
   protected static final Pattern RUNTIME_TELEPORT_X_PATTERN = Pattern.compile("(?i)\\bx\\s*[:=]?\\s*([-+]?\\d+(?:[\\.,]\\d+)?)");
   protected static final Pattern RUNTIME_TELEPORT_Y_PATTERN = Pattern.compile("(?i)\\by\\s*[:=]?\\s*([-+]?\\d+(?:[\\.,]\\d+)?)");
   protected static final Pattern RUNTIME_TELEPORT_Z_PATTERN = Pattern.compile("(?i)\\bz\\s*[:=]?\\s*([-+]?\\d+(?:[\\.,]\\d+)?)");
   protected static final Pattern RUNTIME_TELEPORT_WORLD_PATTERN = Pattern.compile("(?i)\\b(?:world|w|level)\\s*[:=]?\\s*([A-Za-z0-9_\\-./]+)");
   protected static final Pattern RUNTIME_GENERIC_NUMBER_PATTERN = Pattern.compile("[-+]?\\d+(?:[\\.,]\\d+)?");
   protected static final float RUNTIME_ACTION_DEFAULT_SOUND_VOLUME = 1.0F;
   protected static final float RUNTIME_ACTION_DEFAULT_SOUND_PITCH = 1.0F;
   protected static final String EDITOR_SAVED_MESSAGE_PREFIX = "<#e5f8fd>Saved UI ";
   protected static final long RUNTIME_OPEN_ANIMATION_START_DELAY_TICKS = 1L;
   protected static final long RUNTIME_OPEN_ANIMATION_STEP_TICKS = 1L;
   protected static final String CURSOR_PAGE_DROPDOWN_ID = "cursor_page_dropdown";
   protected static final List<String> CURSOR_PAGE_DROPDOWN_ITEM_IDS = List.of(
      "dropdown_cursor_layer_newblock", "dropdown_cursor_layer_newtext", "dropdown_cursor_layer_newitem", "dropdown_cursor_layer_newimage"
   );
   protected static final Map<String, String[]> CURSOR_PAGE_DROPDOWN_ITEM_ICONS = Map.of(
      "dropdown_cursor_layer_newblock",
      new String[]{"\ueeb0", "\ueeb6"},
      "dropdown_cursor_layer_newtext",
      new String[]{"\ueeb1", "\ueeb7"},
      "dropdown_cursor_layer_newitem",
      new String[]{"\ueeb2", "\ueeb8"},
      "dropdown_cursor_layer_newimage",
      new String[]{"\ueebc", "\ueebd"}
   );
   protected static final String CURSOR_PAGE_DROPDOWN_INACTIVE_COLOR = "0f0f0f";
   protected static final String CURSOR_PAGE_DROPDOWN_ACTIVE_COLOR = "141414";
   protected static final String CURSOR_LAYERS_DROPDOWN_ID = "cursor_layers_dropdown";
   protected static final List<String> CURSOR_LAYERS_DROPDOWN_ITEM_IDS = List.of(
      "dropdown_layers_edit_cut",
      "dropdown_layers_edit_copy",
      "dropdown_layers_edit_paste",
      "dropdown_layers_cursor_layer_newblock",
      "dropdown_layers_cursor_layer_newtext",
      "dropdown_layers_cursor_layer_newitem",
      "dropdown_layers_cursor_layer_newimage",
      "dropdown_layers_selection_hide",
      "dropdown_layers_selection_unhide",
      "dropdown_layers_selection_block",
      "dropdown_layers_selection_unblock"
   );
   protected static final List<String> CURSOR_LAYERS_DROPDOWN_DYNAMIC_ITEM_IDS = List.of(
      "dropdown_layers_selection_hide", "dropdown_layers_selection_unhide", "dropdown_layers_selection_block", "dropdown_layers_selection_unblock"
   );
   protected static final Map<String, String[]> CURSOR_LAYERS_DROPDOWN_ITEM_ICONS = Map.ofEntries(
      Map.entry("dropdown_layers_edit_cut", new String[]{"\uee82", "\uee89"}),
      Map.entry("dropdown_layers_edit_copy", new String[]{"\uee83", "\uee8a"}),
      Map.entry("dropdown_layers_edit_paste", new String[]{"\uee84", "\uee8b"}),
      Map.entry("dropdown_layers_cursor_layer_newblock", new String[]{"\ueeb0", "\ueeb6"}),
      Map.entry("dropdown_layers_cursor_layer_newtext", new String[]{"\ueeb1", "\ueeb7"}),
      Map.entry("dropdown_layers_cursor_layer_newitem", new String[]{"\ueeb2", "\ueeb8"}),
      Map.entry("dropdown_layers_cursor_layer_newimage", new String[]{"\ueebc", "\ueebd"}),
      Map.entry("dropdown_layers_selection_hide", new String[]{"\uee93", "\uee9a"}),
      Map.entry("dropdown_layers_selection_unhide", new String[]{"\uee94", "\uee9b"}),
      Map.entry("dropdown_layers_selection_block", new String[]{"\uee95", "\uee9c"}),
      Map.entry("dropdown_layers_selection_unblock", new String[]{"\uee96", "\uee9d"})
   );
   protected static final String CURSOR_LAYERS_DROPDOWN_INACTIVE_COLOR = "0f0f0f";
   protected static final String CURSOR_LAYERS_DROPDOWN_ACTIVE_COLOR = "141414";
   protected static final double CURSOR_LAYERS_DROPDOWN_WALL_MARGIN = 11.0;
   protected static final long CURSOR_LAYERS_ACTION_COOLDOWN_TICKS = 6L;
   protected static final String CURSOR_UNICODE_DEFAULT = "\ue67c";
   protected static final String CURSOR_UNICODE_HOVER = "\ue67d";
   protected static final String CURSOR_UNICODE_DRAG = "\ue67e";
   protected static final String CURSOR_UNICODE_SIDEBAR_DRAG = "\ue67f";
   protected static final String CURSOR_UNICODE_RESIZE_TR_BL = "\ue680";
   protected static final String CURSOR_UNICODE_RESIZE_TL_BR = "\ue681";
   protected static final String CURSOR_UNICODE_RESIZE_VERTICAL = "\ue682";
   protected static final String CURSOR_UNICODE_RESIZE_HORIZONTAL = "\ue683";
   protected static final double CURSOR_VISUAL_CACHE_EPSILON = 1.0E-4;
   protected static final String EDITOR_MENU_EDIT_BUTTON_ID = "editor_menu_button_edit";
   protected static final String EDITOR_MENU_CREATE_BUTTON_ID = "editor_menu_button_create";
   protected static final String EDITOR_MENU_OPEN_BUTTON_ID = "editor_menu_button_open";
   protected static final String EDITOR_MENU_EDIT_HITBOX_ID = "editor_menu_button_edit_hitbox";
   protected static final String EDITOR_MENU_CREATE_HITBOX_ID = "editor_menu_button_create_hitbox";
   protected static final String EDITOR_MENU_OPEN_HITBOX_ID = "editor_menu_button_open_hitbox";
   protected static final String EDITOR_MENU_NAVBAR_CLOSE_ID = "editormenu_navbar_close";
   protected static final String EDITOR_MENU_EDIT_ICON_DEFAULT = "\ue616";
   protected static final String EDITOR_MENU_EDIT_ICON_HOVER = "\ue619";
   protected static final String EDITOR_MENU_CREATE_ICON_DEFAULT = "\ue617";
   protected static final String EDITOR_MENU_CREATE_ICON_HOVER = "\ue61a";
   protected static final String EDITOR_MENU_OPEN_ICON_DEFAULT = "\ue618";
   protected static final String EDITOR_MENU_OPEN_ICON_HOVER = "\ue61b";
   protected static final String EDITOR_MENU_MANAGE_FILE_COMPONENT = "editor_manage_file_popup";
   protected static final String EDITOR_MENU_FILE_ENTRY_COMPONENT = "editor_popup_file_element";
   protected static final String EDITOR_MENU_IMAGE_ENTRY_COMPONENT = "editor_popup_image_element";
   protected static final String EDITOR_MENU_FILE_LIST_BLOCK_ID = "file_popup_files";
   protected static final String EDITOR_MENU_FILE_POPUP_CANCEL_ID = "popup_file_selector_cancel";
   protected static final String EDITOR_MENU_FILE_POPUP_CANCEL_HITBOX_ID = "popup_file_selector_cancel_hitbox";
   protected static final String EDITOR_MENU_FILE_POPUP_CONFIRM_ID = "popup_file_selector_confirm";
   protected static final String EDITOR_MENU_FILE_POPUP_CONFIRM_HITBOX_ID = "popup_file_selector_confirm_hitbox";
   protected static final String EDITOR_MENU_FILE_POPUP_ROOT_ID = "popup_file_selector_root";
   protected static final String EDITOR_MENU_FILE_POPUP_SCROLL_DETECTION_ID = "scroll_detection";
   protected static final String EDITOR_MENU_FILE_POPUP_SCROLLBAR_BG_ID = "scrollbar_bg";
   protected static final String EDITOR_MENU_FILE_POPUP_SCROLLBAR_BAR_ID = "scrollbar_bar";
   protected static final String EDITOR_MENU_FILE_POPUP_NO_FILES_ID = "editor_files_no_files";
   protected static final String EDITOR_MENU_FILE_POPUP_ENTRY_HITBOX_PREFIX = "popup_file_selector_entry_";
   protected static final String EDITOR_MENU_FILE_POPUP_ENTRY_HITBOX_SUFFIX = "_hitbox";
   protected static final int EDITOR_MENU_FILE_POPUP_MAX_VISIBLE = 6;
   protected static final int EDITOR_MENU_IMAGE_POPUP_MAX_VISIBLE = 8;
   protected static final int EDITOR_MENU_FILE_POPUP_ROW_SIZE = 2;
   protected static final int EDITOR_MENU_FILE_POPUP_SCROLLBAR_TRANSITION_TICKS = 1;
   protected static final int EDITOR_MENU_FILE_POPUP_SCROLLBAR_WHEEL_TRANSITION_TICKS = 1;
   protected static final int EDITOR_MENU_FILE_POPUP_PAGE_ELEMENT_HEIGHT = 110;
   protected static final int EDITOR_MENU_FILE_POPUP_PAGE_ELEMENT_GAP = 20;
   protected static final int EDITOR_MENU_FILE_POPUP_IMAGE_ELEMENT_HEIGHT = 75;
   protected static final int EDITOR_MENU_FILE_POPUP_IMAGE_ELEMENT_GAP = 20;
   protected static final int EDITOR_MENU_FILE_NAME_LIMIT = 29;
   protected static final int EDITOR_MENU_FILE_DESC_LINE_LIMIT = 37;
   protected static final int EDITOR_MENU_FILE_COMMAND_LIMIT = 30;
   protected static final int EDITOR_MENU_IMAGE_NAME_LIMIT = 29;
   protected static final int EDITOR_MENU_IMAGE_SCALE_LIMIT = 24;
   protected static final int EDITOR_MENU_FILE_SCROLLBAR_RANGE = 289;
   protected static final String EDITOR_MENU_FILE_DEFAULT_BG = "141414";
   protected static final String EDITOR_MENU_FILE_SELECTED_BG = "1c1c1c";
   protected static final String EDITOR_MENU_FILE_DEFAULT_DESCRIPTION = "None";
   protected static final String EDITOR_MENU_FILE_DEFAULT_COMMAND = "None";
   protected static final String EDITOR_MENU_FILE_CONFIRM_ICON_BLOCKED = "\ueeaa";
   protected static final String EDITOR_META_VISIBLE_KEY = "visible";
   protected static final String EDITOR_META_LOCKED_KEY = "locked";
   protected static final String ACTIVE_PAGES_BLOCK_ID = "active_pages";
   protected static final String ACTIVE_PAGE_ID_PREFIX = "page_";
   protected static final String ACTIVE_PAGE_HITBOX_SUFFIX = "_page_hitbox";
   protected static final String ACTIVE_PAGE_CLOSE_HITBOX_SUFFIX = "_close_hitbox";
   protected static final int ACTIVE_PAGES_MAX_VISIBLE = 7;
   protected static final double ACTIVE_PAGES_REORDER_DRAG_THRESHOLD_PX = 7.0;
   protected static final double ACTIVE_PAGE_CARD_WIDTH = 195.0;
   protected static final double ACTIVE_PAGE_CARD_HEIGHT = 36.0;
   protected static final double ACTIVE_PAGE_DRAG_LAYER = 9330.0;
   protected static final String ACTIVE_PAGES_DRAG_GHOST_BASE_ID = "editor_shell_active_pages_drag_ghost";
   protected static final String ACTIVE_PAGES_DRAG_GHOST_LABEL_ID = "editor_shell_active_pages_drag_ghost_label";
   protected static final String ACTIVE_PAGES_DRAG_GHOST_CLOSE_ICON_ID = "editor_shell_active_pages_drag_ghost_close_icon";
   protected static final String ACTIVE_PAGES_DROP_MARKER_ID = "editor_shell_active_pages_drop_marker";
   protected static final Pattern ACTIVE_PAGES_RUNTIME_HUD_ID_PATTERN = Pattern.compile(
      "^(?:(?:editor_shell_)?.*_)?page_(\\d+)_(?:page_hitbox|close_hitbox|label|close_icon)(?:_.+)?$", 2
   );
   protected static final Pattern UNTITLED_PAGE_KEY_PATTERN = Pattern.compile("^untitled(?:_(\\d+))?$", 2);
   protected static final int EDITOR_HISTORY_DEFAULT_LIMIT = 50;
   protected static final int EDITOR_MAX_PAGE_ELEMENTS_DEFAULT = 500;
   protected static final int PLAYER_INVENTORY_WINDOW_ID = 0;
   protected static final int PLAYER_INVENTORY_STATE_ID = 0;
   protected static final int HOTBAR_SLOT_COUNT = 9;
   protected static final int HOTBAR_WINDOW_SLOT_START = 36;
   protected static final double EDITOR_MOVEMENT_SHORTCUT_DEADZONE = 0.025;
   protected static final double RUNTIME_HUD_SNEAK_VISUAL_SHIFT_Y = -0.08;
   protected static final String RUNTIME_HUD_STACK_PAGE_META_KEY = "xqgui_runtime_hud_stack_page";
   protected static final String EDITOR_HISTORY_BLOCKS_KEY = "blocks";
   protected static final String EDITOR_HISTORY_HIDDEN_KEY = "hidden";
   protected static final String EDITOR_HISTORY_LOCKED_KEY = "locked";
   protected static final String EDITOR_HISTORY_COLLAPSED_KEY = "collapsed";
   protected static final int TEMP_IMAGE_ATLAS_TILE_SIZE = 256;
   protected static final double TEMP_IMAGE_TILE_WIDTH = 64.0;
   protected static final double TEMP_IMAGE_TILE_HEIGHT = 64.0;
   protected static final double TEMP_IMAGE_TILE_OFFSET_X = 256.0;
   protected static final double TEMP_IMAGE_LAST_PARTIAL_TILE_OFFSET_X = 227.0;
   protected static final double TEMP_IMAGE_TILE_OFFSET_Y = 256.0;
   protected static final String IMAGE_BLOCK_TYPE = "image";
   protected static final String IMAGE_BLOCK_IMAGE_KEY = "image";
   protected static final String IMAGE_BLOCK_GLYPH_MATRIX_KEY = "glyph_matrix";
   protected static final String IMAGE_BLOCK_GLYPH_MATRIX_ALT_KEY = "glyphMatrix";
   protected static final String IMAGE_BLOCK_SCALE_X_KEY = "image_scale_x";
   protected static final String IMAGE_BLOCK_SCALE_Y_KEY = "image_scale_y";
   protected static final String IMAGE_BLOCK_OFFSET_X_KEY = "image_offset_x";
   protected static final String IMAGE_BLOCK_OFFSET_Y_KEY = "image_offset_y";
   protected static final String IMAGE_BLOCK_PARTIAL_LAST_COLUMN_KEY = "image_partial_last_column";
   protected static final String EDITOR_IMAGE_LAYOUT_OFFSET_FORMAT_KEY = "editor_meta.image_layout_offset_v1";
   protected static final String EDITOR_IMAGE_LAYOUT_FRAME_OFFSET_FORMAT_KEY = "editor_meta.image_layout_frame_offset_v2";
   protected static final String AUTOSAVE_FOLDER = "autosave";
   protected static final String AUTOSAVE_DEFAULT_PAGE_NAME = "untitled";
   protected static final DateTimeFormatter AUTOSAVE_TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");
   protected static final String PIVOT_DEBUG_MARKER_ID = "editor_shell_pivot_debug_marker";
   protected static final long PIVOT_DEBUG_MARKER_DURATION_TICKS = 80L;
   protected static final double PIVOT_DEBUG_MARKER_SIZE = 8.0;
   protected static final double PIVOT_DEBUG_MARKER_LAYER = 9480.0;
   protected static final String PIVOT_DEBUG_MARKER_TEXT = "<#ff6a3d>+</#ff6a3d>";
   protected static final double PIVOT_DEFAULT_RATIO_X = 0.5075;
   protected static final double PIVOT_DEFAULT_RATIO_Y = 0.469;
   protected static final String[] PIVOT_X_PATHS = new String[]{
      "pivot.x", "pivotX", "rotationPivot.x", "rotation_pivot.x", "params.pivot.x", "params.pivotX", "params.rotationPivot.x", "params.rotation_pivot.x"
   };
   protected static final String[] PIVOT_Y_PATHS = new String[]{
      "pivot.y", "pivotY", "rotationPivot.y", "rotation_pivot.y", "params.pivot.y", "params.pivotY", "params.rotationPivot.y", "params.rotation_pivot.y"
   };
   protected static final String[] PIVOT_NORMALIZED_PATHS = new String[]{
      "pivot.normalized",
      "pivotNormalized",
      "rotationPivot.normalized",
      "rotation_pivot.normalized",
      "params.pivot.normalized",
      "params.pivotNormalized",
      "params.rotationPivot.normalized",
      "params.rotation_pivot.normalized"
   };
   protected static final String[] PIVOT_MODE_PATHS = new String[]{
      "pivot.mode",
      "pivot.units",
      "pivot.unit",
      "rotationPivot.mode",
      "rotation_pivot.mode",
      "params.pivot.mode",
      "params.pivot.units",
      "params.pivot.unit",
      "params.rotationPivot.mode",
      "params.rotation_pivot.mode"
   };
   protected static final String USER_DATA_FOLDER = "userdata";
   protected static final String LEGACY_USER_DATA_FOLDER = "player_data";
   protected final Map<UUID, List<String>> playerEditorOpenPages = new HashMap<>();
   protected final Map<UUID, Map<String, List<Map<String, Object>>>> playerEditorWorkingPages = new HashMap<>();

   protected abstract String normalizePageKey(String var1, String var2);

   protected abstract boolean isInternalEditorPageName(String var1);

   protected abstract boolean isEditorMenuSession(EditorSession var1);

   protected abstract boolean handleEditorMenuClick(Player var1, EditorSession var2, GuiService.ClickType var3);

   protected abstract void rerenderEditableContentForTargets(Player var1, EditorSession var2, List<String> var3, boolean var4);

   protected abstract void rerenderEditableContentForTargets(Player var1, EditorSession var2, List<String> var3, boolean var4, boolean var5);

   protected abstract boolean openGui(Player var1, String var2, boolean var3);

   protected abstract boolean openGui(Player var1, String var2, boolean var3, boolean var4);

   protected abstract boolean openGui(Player var1, String var2, boolean var3, boolean var4, boolean var5);

   protected abstract boolean openGui(Player var1, String var2, boolean var3, boolean var4, boolean var5, boolean var6);

   protected abstract double resolveSessionPageWidth(EditorSession var1);

   protected abstract double resolveSessionPageHeight(EditorSession var1);

   protected abstract boolean handleCursorPageDropdownClick(Player var1, EditorSession var2, GuiService.ClickType var3, double var4, double var6);

   protected abstract boolean handleCursorLayersDropdownClick(Player var1, EditorSession var2, GuiService.ClickType var3, double var4, double var6);

   protected abstract boolean handleActivePagesClick(Player var1, EditorSession var2, GuiService.ClickType var3, double var4, double var6);

   public abstract boolean closeGui(Player var1, String var2);

   public abstract void closeGui(Player var1);

   protected abstract String buildSelectionCreatedElementId(EditorSession var1, String var2);

   protected abstract void renderPage(Player var1, EditorSession var2);

   protected abstract void renderPage(Player var1, EditorSession var2, boolean var3);

   protected abstract void pruneEditorLayerStateTargets(EditorSession var1);

   protected abstract void clearSelectionForStructureEdit(Player var1, EditorSession var2);

   protected abstract void rememberOpenedEditorPage(UUID var1, String var2);

   protected GuiServiceEditorSupportA(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   public void ensureGuiFoldersAndExample() {
      if (!this.plugin.getDataFolder().exists()) {
         this.plugin.getDataFolder().mkdirs();
      }

      File var1 = new File(this.plugin.getDataFolder(), "contents");
      if (!var1.exists()) {
         var1.mkdirs();
      }

      File var2 = new File(this.plugin.getDataFolder(), "contents/pages");
      if (!var2.exists()) {
         var2.mkdirs();
      }

      File var3 = new File(this.plugin.getDataFolder(), "contents/components");
      if (!var3.exists()) {
         var3.mkdirs();
      }

      File var4 = new File(this.plugin.getDataFolder(), "contents/effects");
      if (!var4.exists()) {
         var4.mkdirs();
      }

      this.migrateLegacyContentFolder("pages", var2);
      this.migrateLegacyContentFolder("components", var3);
      this.migrateLegacyContentFolder("effects", var4);
      File var5 = new File(this.plugin.getDataFolder(), "guis");
      if (var5.exists() && var5.isDirectory()) {
         File[] var6 = var5.listFiles((var0, var1x) -> var1x.toLowerCase().endsWith(".yml"));
         if (var6 != null) {
            for (File var10 : var6) {
               File var11 = new File(var2, var10.getName());
               if (!var11.exists()) {
                  var10.renameTo(var11);
               }
            }
         }
      }

      this.saveDefaultResource("contents/pages/example_pro.yml");
      this.saveDefaultResource("contents/pages/text_align.yml");
      this.saveDefaultResource("contents/effects/scale.yml");
      this.saveDefaultResource("contents/effects/transform.yml");
      boolean var12 = this.plugin.getConfig().getBoolean("editor.show-editor-files", false);
      File var13 = new File(this.plugin.getDataFolder(), "editor-defaults/pages");
      File var14 = new File(this.plugin.getDataFolder(), "editor-defaults/components");
      this.applyEditorDefaultFilesMode(var12, var2, var3, var13, var14);
      File var15 = var12 ? var2 : var13;
      File var16 = var12 ? var3 : var14;
      this.migrateEditorLayerComponent(new File(var16, "editor_layer.yml"));
      this.migrateEditorValueComponent(new File(var16, "editor_value.yml"));
      this.migrateNavbarItemComponent(new File(var16, "editor_navbar_item.yml"));
      this.migrateDropdownValueComponent(new File(var16, "editor_dropdown_value.yml"));
      this.migrateEditorSavePopupComponent(new File(var16, "editor_save_popup.yml"));
      this.migrateEditorManageFilePopupComponent(new File(var16, "editor_manage_file_popup.yml"));
      this.migrateEditorPopupFileElementComponent(new File(var16, "editor_popup_file_element.yml"));
      this.migrateEditorPageFile(new File(var15, "editor.yml"));
      this.migrateEditorMenuPageFile(new File(var15, "editor_menu.yml"));
      this.sanitizeYamlTabs("contents/pages");
      this.sanitizeYamlTabs("contents/components");
      this.sanitizeYamlTabs("contents/effects");
      if (!var12) {
         this.sanitizeYamlTabs("editor-defaults/pages");
         this.sanitizeYamlTabs("editor-defaults/components");
      }
   }

   private void applyEditorDefaultFilesMode(boolean var1, File var2, File var3, File var4, File var5) {
      if (var1) {
         this.deleteEditorFilesInFolder(var4, DEFAULT_EDITOR_PAGE_FILES);
         this.deleteEditorFilesInFolder(var5, DEFAULT_EDITOR_COMPONENT_FILES);
         this.deleteFolderIfEmpty(var4);
         this.deleteFolderIfEmpty(var5);
         this.deleteFolderIfEmpty(new File(this.plugin.getDataFolder(), "editor-defaults"));

         for (Object var20_raw : DEFAULT_EDITOR_PAGE_FILES) {
            String var20 = var20_raw != null ? var20_raw.toString() : null;
            this.saveDefaultResource("contents/pages/" + var20);
         }

         for (Object var21_raw : DEFAULT_EDITOR_COMPONENT_FILES) {
            String var21 = var21_raw != null ? var21_raw.toString() : null;
            this.saveDefaultResource("contents/components/" + var21);
         }
      } else {
         this.deleteEditorFilesInFolder(var2, DEFAULT_EDITOR_PAGE_FILES);
         this.deleteEditorFilesInFolder(var3, DEFAULT_EDITOR_COMPONENT_FILES);
         if (!var4.exists()) {
            var4.mkdirs();
         }

         if (!var5.exists()) {
            var5.mkdirs();
         }

         for (Object var9_raw : DEFAULT_EDITOR_PAGE_FILES) {
            String var9 = var9_raw != null ? var9_raw.toString() : null;
            this.extractBundledResourceOverwrite("pages/" + var9, new File(var4, var9));
         }

         for (Object var19_raw : DEFAULT_EDITOR_COMPONENT_FILES) {
            String var19 = var19_raw != null ? var19_raw.toString() : null;
            this.extractBundledResourceOverwrite("components/" + var19, new File(var5, var19));
         }
      }
   }

   private void deleteEditorFilesInFolder(File var1, String[] var2) {
      if (var1 != null && var2 != null && var1.exists() && var1.isDirectory()) {
         for (Object var6_raw : var2) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            if (var6 != null && !var6.isBlank()) {
               File var7 = new File(var1, var6);
               if (var7.exists() && var7.isFile()) {
                  if (!var7.delete()) {
                     this.plugin.getLogger().warning("[UltimateUI] Failed to delete editor default file: " + var7.getAbsolutePath());
                  } else {
                     this.invalidateYamlFileCache(var7);
                  }
               }
            }
         }
      }
   }

   private void deleteFolderIfEmpty(File var1) {
      if (var1 != null && var1.exists() && var1.isDirectory()) {
         String[] var2 = var1.list();
         if (var2 == null || var2.length == 0) {
            var1.delete();
         }
      }
   }

   private void extractBundledResourceOverwrite(String var1, File var2) {
      if (var1 != null && var2 != null) {
         File var3 = var2.getParentFile();
         if (var3 != null && !var3.exists()) {
            var3.mkdirs();
         }

         try {
            try (InputStream var4 = this.plugin.getResource(var1)) {
               if (var4 != null) {
                  Files.copy(var4, var2.toPath(), StandardCopyOption.REPLACE_EXISTING);
                  this.invalidateYamlFileCache(var2);
                  return;
               }

               this.plugin.getLogger().warning("[UltimateUI] Missing bundled editor resource: " + var1);
            }
         } catch (IOException var9) {
            String var5 = var9 instanceof AccessDeniedException
               ? "permission denied — check file ownership/permissions on " + var2.getAbsolutePath()
               : var9.getMessage();
            this.plugin.getLogger().warning("[UltimateUI] Failed to extract editor default '" + var1 + "': " + var5);
         }
      }
   }

   protected void migrateLegacyContentFolder(String var1, File var2) {
      if (var1 != null && !var1.isBlank() && var2 != null) {
         File var3 = new File(this.plugin.getDataFolder(), var1);
         if (var3.exists() && var3.isDirectory()) {
            if (!var2.exists()) {
               var2.mkdirs();
            }

            File[] var4 = var3.listFiles((var0, var1x) -> var1x.toLowerCase(Locale.ROOT).endsWith(".yml"));
            if (var4 != null && var4.length != 0) {
               for (File var8 : var4) {
                  File var9 = new File(var2, var8.getName());
                  if (!var9.exists() && !var8.renameTo(var9)) {
                     try {
                        Files.copy(var8.toPath(), var9.toPath());
                     } catch (IOException var11) {
                        this.plugin
                           .getLogger()
                           .warning(
                              "[UltimateUI] Failed to migrate '" + var8.getName() + "' from " + var1 + " to " + var2.getName() + ": " + var11.getMessage()
                           );
                     }
                  }
               }
            }
         }
      }
   }

   protected void migrateEditorLayerComponent(File var1) {
      if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = var2.replace("${id}_hitbox", "${slot}_hitbox");
            var3 = var3.replace("${id}_box1", "${slot}_box1");
            var3 = var3.replace("${id}_box2", "${slot}_box2");
            var3 = var3.replace("${id}_opacity", "${slot}_opacity");
            var3 = var3.replace("${id}_divider", "${slot}_divider");
            var3 = var3.replace("${id}_name", "${slot}_name");
            var3 = var3.replace("${id}_dropdown_icon", "${slot}_dropdown_icon");
            var3 = var3.replace("${id}_dropdownhitbox", "${slot}_dropdownhitbox");
            var3 = var3.replace("text: \ue638", "text: ${box1_icon}");
            var3 = var3.replace("text: \ue639", "text: ${box2_icon}");
            if (!var3.contains("slot: \"\"")) {
               if (var3.contains("params:\r\n  id: \"\"\r\n")) {
                  var3 = var3.replace("params:\r\n  id: \"\"\r\n", "params:\r\n  id: \"\"\r\n  slot: \"\"\r\n");
               } else if (var3.contains("params:\n  id: \"\"\n")) {
                  var3 = var3.replace("params:\n  id: \"\"\n", "params:\n  id: \"\"\n  slot: \"\"\n");
               }
            }

            if (!var3.contains("box1_icon:")) {
               if (var3.contains("  slot: \"\"\r\n")) {
                  var3 = var3.replace("  slot: \"\"\r\n", "  slot: \"\"\r\n  box1_icon: \"\ue638\"\r\n  box2_icon: \"\ue639\"\r\n");
               } else if (var3.contains("  slot: \"\"\n")) {
                  var3 = var3.replace("  slot: \"\"\n", "  slot: \"\"\n  box1_icon: \"\ue638\"\n  box2_icon: \"\ue639\"\n");
               }
            }

            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var4) {
            this.plugin.getLogger().warning("Failed to migrate components/editor_layer.yml: " + var4.getMessage());
         }
      }
   }

   protected void migrateEditorValueComponent(File var1) {
      if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = var2;
            if (!var2.contains("editor_val_${id}_hitbox")) {
               var3 = var2.replace(
                  "blocks:\r\n  - type: text\r\n",
                  "blocks:\r\n  - type: block\r\n    id: \"editor_val_${id}_hitbox\"\r\n    layer: 9203.0\r\n    opacity: 0\r\n    color: ff0000\r\n    align: center\r\n    position:\r\n      x: 0\r\n      y: 0\r\n    size:\r\n      width: 184\r\n      height: 32\r\n    unicode: \ue67b\r\n  - type: text\r\n"
               );
               var3 = var3.replace(
                  "blocks:\n  - type: text\n",
                  "blocks:\n  - type: block\n    id: \"editor_val_${id}_hitbox\"\n    layer: 9203.0\n    opacity: 0\n    color: ff0000\n    align: center\n    position:\n      x: 0\n      y: 0\n    size:\n      width: 184\n      height: 32\n    unicode: \ue67b\n  - type: text\n"
               );
            }

            if (!var3.contains("id: \"editor_val_${id}\"")) {
               var3 = var3.replace("children:\r\n      - type: text\r\n", "children:\r\n      - type: text\r\n        id: \"editor_val_${id}\"\r\n");
               var3 = var3.replace("children:\n      - type: text\n", "children:\n      - type: text\n        id: \"editor_val_${id}\"\n");
            }

            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var4) {
            this.plugin.getLogger().warning("Failed to migrate components/editor_value.yml: " + var4.getMessage());
         }
      }
   }

   protected void migrateNavbarItemComponent(File var1) {
      if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = var2;
            if (!var2.contains("${id}_text")) {
               var3 = var2.replace("    true:\r\n      - type: text\r\n", "    true:\r\n      - type: text\r\n        id: ${id}_text\r\n");
               var3 = var3.replace("    true:\n      - type: text\n", "    true:\n      - type: text\n        id: ${id}_text\n");
               var3 = var3.replace("    false:\r\n      - type: text\r\n", "    false:\r\n      - type: text\r\n        id: ${id}_text\r\n");
               var3 = var3.replace("    false:\n      - type: text\n", "    false:\n      - type: text\n        id: ${id}_text\n");
            }

            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var4) {
            this.plugin.getLogger().warning("Failed to migrate components/editor_navbar_item.yml: " + var4.getMessage());
         }
      }
   }

   protected void migrateDropdownValueComponent(File var1) {
      if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = var2;
            if (!var2.contains("dropdown_${id}_hitbox")) {
               var3 = var2.replace(
                  "blocks:\r\n  - case: \"{active}\"\r\n",
                  "blocks:\r\n  - type: block\r\n    id: dropdown_${id}_hitbox\r\n    layer: 9205.0\r\n    opacity: 0\r\n    color: ff0000\r\n    position:\r\n      x: 0\r\n      y: 34\r\n    size:\r\n      width: 210\r\n      height: 32\r\n    unicode: \ue67b\r\n  - case: \"{active}\"\r\n"
               );
               var3 = var3.replace(
                  "blocks:\n  - case: \"{active}\"\n",
                  "blocks:\n  - type: block\n    id: dropdown_${id}_hitbox\n    layer: 9205.0\n    opacity: 0\n    color: ff0000\n    position:\n      x: 0\n      y: 34\n    size:\n      width: 210\n      height: 32\n    unicode: \ue67b\n  - case: \"{active}\"\n"
               );
            }

            var3 = var3.replace(
               "id: dropdown_${id}_hitbox\r\n    layer: 9205.0\r\n    opacity: 0\r\n    color: ff0000\r\n    position:\r\n      x: 0\r\n      y: 0\r\n",
               "id: dropdown_${id}_hitbox\r\n    layer: 9205.0\r\n    opacity: 0\r\n    color: ff0000\r\n    position:\r\n      x: 0\r\n      y: 34\r\n"
            );
            var3 = var3.replace(
               "id: dropdown_${id}_hitbox\r\n    layer: 9205.0\r\n    opacity: 0\r\n    color: ff0000\r\n    position:\r\n      x: 0\r\n      y: 17\r\n",
               "id: dropdown_${id}_hitbox\r\n    layer: 9205.0\r\n    opacity: 0\r\n    color: ff0000\r\n    position:\r\n      x: 0\r\n      y: 34\r\n"
            );
            var3 = var3.replace(
               "id: dropdown_${id}_hitbox\n    layer: 9205.0\n    opacity: 0\n    color: ff0000\n    position:\n      x: 0\n      y: 0\n",
               "id: dropdown_${id}_hitbox\n    layer: 9205.0\n    opacity: 0\n    color: ff0000\n    position:\n      x: 0\n      y: 34\n"
            );
            var3 = var3.replace(
               "id: dropdown_${id}_hitbox\n    layer: 9205.0\n    opacity: 0\n    color: ff0000\n    position:\n      x: 0\n      y: 17\n",
               "id: dropdown_${id}_hitbox\n    layer: 9205.0\n    opacity: 0\n    color: ff0000\n    position:\n      x: 0\n      y: 34\n"
            );
            if (!var3.contains("dropdown_${id}_text")) {
               var3 = var3.replace(
                  "            children:\r\n              - type: text\r\n",
                  "            children:\r\n              - type: text\r\n                id: dropdown_${id}_text\r\n"
               );
               var3 = var3.replace(
                  "            children:\n              - type: text\n",
                  "            children:\n              - type: text\n                id: dropdown_${id}_text\n"
               );
               var3 = var3.replace(
                  "        children:\r\n          - type: text\r\n", "        children:\r\n          - type: text\r\n            id: dropdown_${id}_text\r\n"
               );
               var3 = var3.replace(
                  "        children:\n          - type: text\n", "        children:\n          - type: text\n            id: dropdown_${id}_text\n"
               );
            }

            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var4) {
            this.plugin.getLogger().warning("Failed to migrate components/editor_dropdown_value.yml: " + var4.getMessage());
         }
      }
   }

   protected void migrateEditorSavePopupComponent(File var1) {
      if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = var2.replace("id: right_sidebar", "id: popup_save_root");
            var3 = var3.replace(
               "              - type: text\r\n                layer: 9608.0\r\n                opacity: 255\r\n                color: 666666\r\n                align: left\r\n                text-wrap: 500\r\n                position:\r\n                  x: 192+15\r\n                  y: 20\r\n                size:\r\n                  width: 82\r\n                  height: 82\r\n                text: Example name\r\n",
               "              - type: text\r\n                id: popup_save_name_value\r\n                layer: 9608.0\r\n                opacity: 255\r\n                color: 666666\r\n                align: left\r\n                text-wrap: 500\r\n                position:\r\n                  x: 192+15\r\n                  y: 20\r\n                size:\r\n                  width: 82\r\n                  height: 82\r\n                text: Example name\r\n"
            );
            var3 = var3.replace(
               "              - type: text\n                layer: 9608.0\n                opacity: 255\n                color: 666666\n                align: left\n                text-wrap: 500\n                position:\n                  x: 192+15\n                  y: 20\n                size:\n                  width: 82\n                  height: 82\n                text: Example name\n",
               "              - type: text\n                id: popup_save_name_value\n                layer: 9608.0\n                opacity: 255\n                color: 666666\n                align: left\n                text-wrap: 500\n                position:\n                  x: 192+15\n                  y: 20\n                size:\n                  width: 82\n                  height: 82\n                text: Example name\n"
            );
            var3 = var3.replace(
               "              - type: text\r\n                layer: 9608.0\r\n                opacity: 255\r\n                color: 666666\r\n                align: left\r\n                text-wrap: 500\r\n                position:\r\n                  x: 192+15\r\n                  y: 20\r\n                size:\r\n                  width: 82\r\n                  height: 82\r\n                text: Example description\r\n",
               "              - type: text\r\n                id: popup_save_desc_value\r\n                layer: 9608.0\r\n                opacity: 255\r\n                color: 666666\r\n                align: left\r\n                text-wrap: 500\r\n                position:\r\n                  x: 192+15\r\n                  y: 20\r\n                size:\r\n                  width: 82\r\n                  height: 82\r\n                text: Example description\r\n"
            );
            var3 = var3.replace(
               "              - type: text\n                layer: 9608.0\n                opacity: 255\n                color: 666666\n                align: left\n                text-wrap: 500\n                position:\n                  x: 192+15\n                  y: 20\n                size:\n                  width: 82\n                  height: 82\n                text: Example description\n",
               "              - type: text\n                id: popup_save_desc_value\n                layer: 9608.0\n                opacity: 255\n                color: 666666\n                align: left\n                text-wrap: 500\n                position:\n                  x: 192+15\n                  y: 20\n                size:\n                  width: 82\n                  height: 82\n                text: Example description\n"
            );
            var3 = var3.replace(
               "              - type: text\r\n                layer: 9608.0\r\n                opacity: 255\r\n                color: 666666\r\n                align: left\r\n                text-wrap: 500\r\n                position:\r\n                  x: 192+15\r\n                  y: 20\r\n                size:\r\n                  width: 82\r\n                  height: 82\r\n                text: <font:default>/<font:editor>example\r\n",
               "              - type: text\r\n                id: popup_save_command_value\r\n                layer: 9608.0\r\n                opacity: 255\r\n                color: 666666\r\n                align: left\r\n                text-wrap: 500\r\n                position:\r\n                  x: 192+15\r\n                  y: 20\r\n                size:\r\n                  width: 82\r\n                  height: 82\r\n                text: <font:default>/<font:editor>example\r\n"
            );
            var3 = var3.replace(
               "              - type: text\n                layer: 9608.0\n                opacity: 255\n                color: 666666\n                align: left\n                text-wrap: 500\n                position:\n                  x: 192+15\n                  y: 20\n                size:\n                  width: 82\n                  height: 82\n                text: <font:default>/<font:editor>example\n",
               "              - type: text\n                id: popup_save_command_value\n                layer: 9608.0\n                opacity: 255\n                color: 666666\n                align: left\n                text-wrap: 500\n                position:\n                  x: 192+15\n                  y: 20\n                size:\n                  width: 82\n                  height: 82\n                text: <font:default>/<font:editor>example\n"
            );
            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var4) {
            this.plugin.getLogger().warning("Failed to migrate components/editor_save_popup.yml: " + var4.getMessage());
         }
      }
   }

   protected void migrateEditorManageFilePopupComponent(File var1) {
      if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = var2.replace("id: popup_preferences_root", "id: popup_file_selector_root");
            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var4) {
            this.plugin.getLogger().warning("Failed to migrate components/editor_manage_file_popup.yml: " + var4.getMessage());
         }
      }
   }

   protected void migrateEditorPopupFileElementComponent(File var1) {
      if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = var2;
            if (!var2.contains("hitbox_id:")) {
               var3 = var2.replace("  bg: \"141414\"\r\n", "  bg: \"141414\"\r\n  hitbox_id: \"popup_file_selector_entry_hitbox\"\r\n");
               var3 = var3.replace("  bg: \"141414\"\n", "  bg: \"141414\"\n  hitbox_id: \"popup_file_selector_entry_hitbox\"\n");
            }

            if (!var3.contains("${hitbox_id}")) {
               String var4 = this.detectLineSeparator(var3);
               if (!var3.endsWith("\n") && !var3.endsWith("\r")) {
                  var3 = var3 + var4;
               }

               var3 = var3
                  + "      - type: hitbox"
                  + var4
                  + "        id: ${hitbox_id}"
                  + var4
                  + "        layer: 9612"
                  + var4
                  + "        opacity: 0"
                  + var4
                  + "        color: ff0000"
                  + var4
                  + "        align: left"
                  + var4
                  + "        position:"
                  + var4
                  + "          x: 0"
                  + var4
                  + "          y: 0"
                  + var4
                  + "        size:"
                  + var4
                  + "          width: 380"
                  + var4
                  + "          height: 108"
                  + var4
                  + "        unicode: \ue67b"
                  + var4;
            }

            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var5) {
            this.plugin.getLogger().warning("Failed to migrate components/editor_popup_file_element.yml: " + var5.getMessage());
         }
      }
   }

   protected void migrateEditorPageFile(File var1) {
      if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = var2.replace(
               "  - type: block\r\n    id: file_dropdown\r\n    layer: 9200.0\r\n    opacity: 0\r\n",
               "  - type: block\r\n    id: file_dropdown\r\n    layer: 9200.0\r\n    opacity: 255\r\n"
            );
            var3 = var3.replace(
               "  - type: block\n    id: file_dropdown\n    layer: 9200.0\n    opacity: 0\n",
               "  - type: block\n    id: file_dropdown\n    layer: 9200.0\n    opacity: 255\n"
            );
            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var4) {
            this.plugin.getLogger().warning("Failed to migrate pages/editor.yml: " + var4.getMessage());
         }
      }
   }

   protected void migrateEditorMenuPageFile(File var1) {
      if (var1 != null && var1.exists() && var1.isFile()) {
         try {
            String var2 = Files.readString(var1.toPath(), StandardCharsets.UTF_8);
            String var3 = this.ensureEditorMenuButtonTextId(var2, "\ue616", "editor_menu_button_edit");
            var3 = this.ensureEditorMenuButtonTextId(var3, "\ue617", "editor_menu_button_create");
            var3 = this.ensureEditorMenuButtonTextId(var3, "\ue618", "editor_menu_button_open");
            var3 = this.ensureEditorMenuButtonHitbox(var3, "\ue616", "editor_menu_button_edit_hitbox");
            var3 = this.ensureEditorMenuButtonHitbox(var3, "\ue617", "editor_menu_button_create_hitbox");
            var3 = this.ensureEditorMenuButtonHitbox(var3, "\ue618", "editor_menu_button_open_hitbox");
            var3 = var3.replace("- { component: editor_create_popup }\r\n", "");
            var3 = var3.replace("\n- { component: editor_create_popup }", "");
            var3 = var3.replace("- { component: editor_create_popup }", "");
            var3 = var3.replace("- { component: editor_manage_file_popup, enabled: true }", "- { component: editor_manage_file_popup, enabled: false }");
            var3 = var3.replace("- { component: editor_manage_file_popup }", "- { component: editor_manage_file_popup, enabled: false }");
            if (!var3.contains("cursorLayer:")) {
               var3 = var3.replace("  cursorUnicode: \ue67c\r\n", "  cursorUnicode: \ue67c\r\n  cursorLayer: 9700\r\n");
               var3 = var3.replace("  cursorUnicode: \ue67c\n", "  cursorUnicode: \ue67c\n  cursorLayer: 9700\n");
            }

            if (!var3.equals(var2)) {
               Files.writeString(var1.toPath(), var3, StandardCharsets.UTF_8);
               this.invalidateYamlFileCache(var1);
            }
         } catch (IOException var4) {
            this.plugin.getLogger().warning("Failed to migrate pages/editor_menu.yml: " + var4.getMessage());
         }
      }
   }

   protected String ensureEditorMenuButtonTextId(String var1, String var2, String var3) {
      if (var1 == null || var1.isBlank() || var2 == null || var2.isBlank() || var3 == null || var3.isBlank()) {
         return var1;
      } else if (var1.contains("id: " + var3)) {
         return var1;
      } else {
         int var4 = var1.indexOf("text: " + var2);
         if (var4 < 0) {
            return var1;
         } else {
            int var5 = var1.lastIndexOf("- type: text", var4);
            if (var5 < 0) {
               return var1;
            } else {
               int var6 = var1.lastIndexOf(10, var5);
               var6 = var6 < 0 ? 0 : var6 + 1;
               String var7 = var1.substring(var6, var5);
               int var8 = var1.indexOf(10, var5);
               if (var8 < 0) {
                  var8 = var1.length();
               }

               String var9 = this.detectLineSeparator(var1);
               int var10 = var8 < var1.length() ? var8 + 1 : var8;
               String var11 = var7 + "  id: " + var3 + var9;
               return var1.substring(0, var10) + var11 + var1.substring(var10);
            }
         }
      }
   }

   protected String ensureEditorMenuButtonHitbox(String var1, String var2, String var3) {
      if (var1 == null || var1.isBlank() || var2 == null || var2.isBlank() || var3 == null || var3.isBlank()) {
         return var1;
      } else if (var1.contains("id: " + var3)) {
         return var1;
      } else {
         int var4 = var1.indexOf("text: " + var2);
         if (var4 < 0) {
            return var1;
         } else {
            int var5 = var1.lastIndexOf("- type: text", var4);
            if (var5 < 0) {
               return var1;
            } else {
               int var6 = var1.lastIndexOf(10, var5);
               var6 = var6 < 0 ? 0 : var6 + 1;
               String var7 = var1.substring(var6, var5);
               String var8 = var7 + "  ";
               int var9 = var1.indexOf(10, var4);
               boolean var10 = var9 >= 0;
               if (!var10) {
                  var9 = var1.length();
               }

               String var11 = this.detectLineSeparator(var1);
               String var12 = var7
                  + "- type: block"
                  + var11
                  + var8
                  + "id: "
                  + var3
                  + var11
                  + var8
                  + "layer: 9201.0"
                  + var11
                  + var8
                  + "opacity: 0"
                  + var11
                  + var8
                  + "color: ff0000"
                  + var11
                  + var8
                  + "align: left"
                  + var11
                  + var8
                  + "position:"
                  + var11
                  + var8
                  + "  x: 59"
                  + var11
                  + var8
                  + "  y: 594"
                  + var11
                  + var8
                  + "size:"
                  + var11
                  + var8
                  + "  width: 243"
                  + var11
                  + var8
                  + "  height: 64"
                  + var11
                  + var8
                  + "text: \ue67b";
               int var13 = var10 ? var9 + 1 : var9;
               return var1.substring(0, var13) + var12 + var1.substring(var13);
            }
         }
      }
   }

   protected String detectLineSeparator(String var1) {
      return var1 != null && var1.contains("\r\n") ? "\r\n" : "\n";
   }

   public boolean startGui(Player var1, String var2) {
      return this.startGui(var1, var2, false);
   }

   public boolean startGui(Player var1, String var2, boolean var3) {
      return this.openGui(var1, var2, false, true, false, var3);
   }

   public boolean startGuiHud(Player var1, String var2) {
      return this.startGuiHud(var1, var2, false);
   }

   public boolean startGuiHud(Player var1, String var2, boolean var3) {
      return this.openGui(var1, var2, false, false, true, var3);
   }

   public boolean insertTemporaryImageElement(Player var1, String var2, int var3, int var4, String var5) {
      if (var1 == null) {
         return false;
      } else {
         EditorSession var6 = this.editorSessions.get(var1.getUniqueId());
         if (var6 != null && var6.rawBlocks != null) {
            String var7 = this.firstNonBlank(new String[]{var2}).trim();
            if (var7.isBlank()) {
               var7 = "image";
            }

            String var8 = this.firstNonBlank(new String[]{var5});
            if (var8.isBlank()) {
               return false;
            } else {
               String var9 = this.sanitizeGeneratedRuntimeId(var7.toLowerCase(Locale.ROOT));
               if (var9.isBlank()) {
                  var9 = "image";
               }

               String var10 = var8.replace("\r\n", "\n").replace('\r', '\n');
               List var11 = this.parseGlyphMatrixRows(var10);
               int var12 = this.resolveGlyphMatrixMaxColumns(var11);
               if (!var11.isEmpty() && var12 > 0) {
                  double var13 = 64.0;
                  double var15 = 64.0;
                  double var17 = 256.0;
                  double var19 = 256.0;
                  int var21 = Math.max(0, var3 % 256);
                  boolean var22 = var21 > 0;
                  double var23 = this.resolveSessionPageWidth(var6);
                  double var25 = this.resolveSessionPageHeight(var6);
                  double var27 = Math.max(1.0, (double)var3);
                  double var29 = Math.max(1.0, (double)var4);
                  double var31 = Math.max(0.0, var23 / 2.0 - var27 / 2.0);
                  double var33 = Math.max(0.0, var25 / 2.0 - var29 / 2.0);
                  double var35 = this.findMaxLayer(var6.renderBlocks) + 1.0;
                  double var37 = var35;
                  String var39 = this.buildSelectionCreatedElementId(var6, "img_" + var9);
                  String var40 = this.buildUniqueTargetDisplayName(var6, var7, "");
                  LinkedHashMap var41 = new LinkedHashMap();
                  var41.put("type", "hitbox");
                  var41.put("id", var39);
                  var41.put("name", var40);
                  var41.put("layer", var35);
                  var41.put("opacity", 0);
                  var41.put("color", "ffffff");
                  var41.put("align", "left");
                  var41.put("visible", true);
                  var41.put("locked", false);
                  var41.put("__editor_inherit_target_to_children", true);
                  var41.put("image", var7);
                  var41.put("glyph_matrix", var10);
                  LinkedHashMap var42 = new LinkedHashMap();
                  var42.put("x", var31);
                  var42.put("y", var33);
                  var41.put("position", var42);
                  LinkedHashMap var43 = new LinkedHashMap();
                  var43.put("width", Math.max(1.0, var27));
                  var43.put("height", Math.max(1.0, var29));
                  var41.put("size", var43);
                  ArrayList var44 = new ArrayList();
                  if (var6.editMode) {
                     this.recordEditorMutation(var6);
                  }

                  int var45 = var6.rawBlocks.size();
                  int var46 = 0;

                  for (int var47 = 0; var47 < var11.size(); var47++) {
                     List var48 = (List)var11.get(var47);
                     if (var48 != null && !var48.isEmpty()) {
                        for (int var49 = 0; var49 < var48.size(); var49++) {
                           String var50 = (String)var48.get(var49);
                           if (var50 != null && !var50.isBlank()) {
                              var46++;
                              LinkedHashMap var51 = new LinkedHashMap();
                              var51.put("type", "text");
                              var51.put("id", this.buildSelectionCreatedElementId(var6, "img_" + var9 + "_" + var46));
                              var51.put("name", this.buildUniqueTargetDisplayName(var6, var7 + " " + var46, ""));
                              var51.put("layer", var37);
                              var51.put("opacity", 255);
                              var51.put("color", "ffffff");
                              var51.put("align", "left");
                              var51.put("text", "<font:uiimages>" + var50);
                              var51.put("visible", true);
                              var51.put("locked", false);
                              LinkedHashMap var52 = new LinkedHashMap();
                              double var53 = (double)var49 * 256.0;
                              if (var22 && var48.size() > 1 && var49 == var48.size() - 1) {
                                 var53 = (double)(var49 - 1) * 256.0 + 227.0;
                              }

                              var52.put("x", var53);
                              var52.put("y", (double)var47 * 256.0);
                              var51.put("position", var52);
                              LinkedHashMap var55 = new LinkedHashMap();
                              var55.put("width", 64.0);
                              var55.put("height", 64.0);
                              var51.put("size", var55);
                              var44.add(var51);
                           }
                        }
                     }
                  }

                  if (var44.isEmpty()) {
                     return false;
                  } else {
                     this.applyImageGroupSingleTilePartialCorrection(var44, var27, var29);
                     int var56 = this.resolveMaxPageElements();
                     if (var6.rawBlocks.size() >= var56) {
                        this.sendEditorPlayerMessage(
                           var1,
                           this.plugin != null
                              ? this.plugin.getLangMessageWithPlaceholders("gui.limit-reached", "&cReached page limit!", Map.of())
                              : MM.deserialize("<yellow>Page element limit reached (" + var56 + ").</yellow>")
                        );
                        return false;
                     } else {
                        var41.put("children", var44);
                        var6.rawBlocks.add(var41);
                        var6.renderBlocks = this.resolveRenderableBlocks(var6.rawBlocks, var6.components);
                        if (!var6.editMode) {
                           this.renderPage(var1, var6, false);
                           return true;
                        } else {
                           this.pruneEditorLayerStateTargets(var6);
                           this.clearSelectionForStructureEdit(var1, var6);
                           String var57 = String.valueOf(var45);
                           this.rerenderEditableContentForTargets(var1, var6, List.of(var57), true, false);
                           String var58 = this.firstNonBlank(new String[]{this.findTargetIdByPath(var6, var57), var39});
                           var6.selectedElementId = var58;
                           var6.additionalSelectedElementIds.clear();
                           var6.selectionOutlineVisible = true;
                           this.normalizeSelectionState(var6);
                           this.rerenderEditableSelection(var1, var6);
                           this.renderLayersPanel(var1, var6);
                           this.updatePageInfoReadout(var1, var6);
                           return true;
                        }
                     }
                  }
               } else {
                  return false;
               }
            }
         } else {
            return false;
         }
      }
   }

   protected List<List<String>> parseGlyphMatrixRows(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).replace("\r\n", "\n").replace('\r', '\n');
      if (var2.isBlank()) {
         return Collections.emptyList();
      } else {
         ArrayList var3 = new ArrayList();
         ArrayList var4 = new ArrayList();
         String[] var5 = var2.split("\n", -1);

         for (Object var9_raw : var5) {
            String var9 = var9_raw != null ? var9_raw.toString() : null;
            if (var9 != null && !var9.isEmpty()) {
               ArrayList var10 = new ArrayList();
               boolean var11 = false;
               int var12 = 0;

               while (var12 < var9.length()) {
                  int var13 = var9.codePointAt(var12);
                  String var14 = new String(Character.toChars(var13));
                  var10.add(var14);
                  if (!var14.isBlank()) {
                     var11 = true;
                  }

                  var12 += Character.charCount(var13);
               }

               if (!var10.isEmpty()) {
                  var3.add(var10);
                  var4.add(var11);
               }
            }
         }

         if (var3.isEmpty()) {
            return var3;
         } else {
            int var15 = 0;

            while (var15 < var4.size() && !Boolean.TRUE.equals(var4.get(var15))) {
               var15++;
            }

            if (var15 >= var4.size()) {
               return Collections.emptyList();
            } else {
               int var16 = var4.size() - 1;

               while (var16 >= var15 && !Boolean.TRUE.equals(var4.get(var16))) {
                  var16--;
               }

               return var15 == 0 && var16 == var3.size() - 1 ? var3 : new ArrayList<>(var3.subList(var15, var16 + 1));
            }
         }
      }
   }

   protected int resolveGlyphMatrixMaxColumns(List<List<String>> var1) {
      if (var1 != null && !var1.isEmpty()) {
         int var2 = 0;

         for (List var4 : var1) {
            if (var4 != null && !var4.isEmpty()) {
               var2 = Math.max(var2, var4.size());
            }
         }

         return var2;
      } else {
         return 0;
      }
   }

   protected List<Map<String, Object>> expandCompactImageBlocks(List<Map<String, Object>> var1) {
      if (var1 != null && !var1.isEmpty()) {
         for (int var2 = 0; var2 < var1.size(); var2++) {
            Map var3 = (Map)var1.get(var2);
            if (var3 != null && !var3.isEmpty()) {
               var1.set(var2, this.expandCompactImageBlock(var3));
            }
         }

         return var1;
      } else {
         return (List<Map<String, Object>>)(var1 == null ? new ArrayList<>() : var1);
      }
   }

   protected boolean hasImageLayoutOffsetSaveFormat(YamlConfiguration var1) {
      return true;
   }

   protected boolean hasImageLayoutFrameOffsetSaveFormat(YamlConfiguration var1) {
      return true;
   }

   protected void markImageLayoutOffsetSaveFormat(YamlConfiguration var1) {
   }

   protected void markImageLayoutFrameOffsetSaveFormat(YamlConfiguration var1) {
   }

   protected void markImageLayoutSaveFormats(YamlConfiguration var1) {
      this.markImageLayoutOffsetSaveFormat(var1);
      this.markImageLayoutFrameOffsetSaveFormat(var1);
   }

   protected void applyImageLayoutOffsetFormatToLoadedBlocks(YamlConfiguration var1, List<Map<String, Object>> var2) {
      if (this.hasImageLayoutOffsetSaveFormat(var1)) {
         this.applyImageLayoutOffsetToBlocks(var2, 29.0, 248.0);
      }
   }

   protected void applyImageGroupFrameOffsetToBlocks(List<Map<String, Object>> var1, double var2) {
      if (var1 != null && !var1.isEmpty() && Double.isFinite(var2) && !(Math.abs(var2) < 1.0E-4)) {
         for (Map var5 : var1) {
            this.applyImageGroupFrameOffsetToBlock(var5, var2);
         }
      }
   }

   protected void applyImageGroupFrameOffsetToBlock(Map<String, Object> var1, double var2) {
      if (var1 != null && !var1.isEmpty()) {
         if (this.isImageGroupRoot(var1)) {
            double var4 = Math.abs(this.parseDouble(this.readMapPathValue(var1, "size.height"), this.parseDouble(this.readMapPathValue(var1, "height"), 0.0)));
            if (!Double.isFinite(var4) || var4 <= 0.0) {
               var4 = this.resolveImageSizeDimension(var1, false, 256.0);
            }

            double var6 = this.resolveImageGroupOutlineToRootYOffset(var1, var4);
            if (Double.isFinite(var6) && Math.abs(var6) > 1.0E-4) {
               double var8 = this.readRawTargetLocalX(var1);
               double var10 = this.readRawTargetLocalY(var1);
               double var12 = var6 * var2;
               this.setRawTargetLocalPosition(var1, var8, var10 + var12);
               this.applyImageLayoutOffsetToPositionRow(var1, 0.0, var12);
            }
         }

         if (var1.get("children") instanceof List var5 && !var5.isEmpty()) {
            for (Object var7 : var5) {
               if (var7 instanceof Map var16) {
                  this.applyImageGroupFrameOffsetToBlock(var16, var2);
               }
            }

            return;
         }
      }
   }

   protected void applyImageLayoutOffsetToBlocks(List<Map<String, Object>> var1, double var2, double var4) {
      if (var1 != null && !var1.isEmpty() && (Double.isFinite(var2) || Double.isFinite(var4))) {
         for (Map var7 : var1) {
            this.applyImageLayoutOffsetToBlock(var7, var2, var4);
         }
      }
   }

   protected void applyImageLayoutOffsetToBlock(Map<String, Object> var1, double var2, double var4) {
      if (var1 != null && !var1.isEmpty()) {
         if (this.isImageGroupRoot(var1)) {
            double var6 = this.readRawTargetLocalX(var1);
            double var8 = this.readRawTargetLocalY(var1);
            this.setRawTargetLocalPosition(var1, var6 + var2, var8 + var4);
            this.applyImageLayoutOffsetToPositionRow(var1, var2, var4);
         }

         if (var1.get("children") instanceof List var7 && !var7.isEmpty()) {
            for (Object var9 : var7) {
               if (var9 instanceof Map var10) {
                  this.applyImageLayoutOffsetToBlock(var10, var2, var4);
               }
            }

            return;
         }
      }
   }

   protected void applyImageLayoutOffsetToPositionRow(Map<String, Object> var1, double var2, double var4) {
      Map var6 = this.readAnimationTimelineRowMap(var1, "position");
      if (var6 != null && !var6.isEmpty()) {
         for (Map.Entry<?, ?> var8 : ((Map<?, ?>)var6).entrySet()) {
            if (var8 != null) {
               Object var10 = var8.getValue();
               if (var10 instanceof Map) {
                  Map var9 = (Map)var10;
                  double var11 = this.parseDouble(var9.get("x"), Double.NaN);
                  double var13 = this.parseDouble(var9.get("y"), Double.NaN);
                  if (Double.isFinite(var11)) {
                     var9.put("x", var11 + var2);
                  }

                  if (Double.isFinite(var13)) {
                     var9.put("y", var13 + var4);
                  }
               }
            }
         }
      }
   }

   protected Map<String, Object> expandCompactImageBlock(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         if (var1.get("children") instanceof List var3 && !var3.isEmpty()) {
            ArrayList var4 = new ArrayList();

            for (Object var6 : var3) {
               Map var7 = this.toStringObjectMap(var6);
               if (var7 != null && !var7.isEmpty()) {
                  var4.add(this.expandCompactImageBlock(var7));
               }
            }

            var1.put("children", var4);
         }

         if (!this.isCompactImageBlock(var1)) {
            return var1;
         } else {
            Map var8 = this.buildLegacyImageBlockFromCompact(var1);
            return var8 == null ? var1 : var8;
         }
      } else {
         return var1;
      }
   }

   protected boolean isCompactImageBlock(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         String var2 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "type"))}).trim().toLowerCase(Locale.ROOT);
         return "image".equals(var2);
      } else {
         return false;
      }
   }

   protected Map<String, Object> buildLegacyImageBlockFromCompact(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         String var2 = this.resolveCompactImageGlyphMatrix(var1);
         List var3 = this.parseGlyphMatrixRows(var2);
         int var4 = this.resolveGlyphMatrixMaxColumns(var3);
         if (!var3.isEmpty() && var4 > 0) {
            String var48 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "hover.image"))}).trim();
            List var6 = Collections.emptyList();
            if (!var48.isBlank()) {
               UiImageAtlasService.GeneratedImage var7 = this.plugin.resolveGeneratedUiImageForEditor(var48);
               if (var7 != null) {
                  String var8 = this.firstNonBlank(new String[]{var7.glyphMatrix()}).trim().replace("\r\n", "\n").replace('\r', '\n');
                  if (!var8.isBlank()) {
                     var6 = this.parseGlyphMatrixRows(var8);
                  }
               }
            }

            Map var49 = this.deepCopyMap(var1);
            var49.put("type", "hitbox");
            var49.put("__editor_inherit_target_to_children", true);
            var49.put("glyph_matrix", var2);
            if (!var49.containsKey("opacity")) {
               var49.put("opacity", 0);
            }

            if (!var49.containsKey("color")) {
               var49.put("color", "ffffff");
            }

            if (!var49.containsKey("align")) {
               var49.put("align", "left");
            }

            if (!var49.containsKey("visible")) {
               var49.put("visible", true);
            }

            if (!var49.containsKey("locked")) {
               var49.put("locked", false);
            }

            String var50 = this.deriveImageBlockName(var1);
            if (!var50.isBlank()) {
               var49.put("image", var50);
            }

            String var9 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var49, "id"))}).trim();
            if (var9.isBlank()) {
               String var10 = this.sanitizeGeneratedRuntimeId(var50.toLowerCase(Locale.ROOT));
               var9 = var10.isBlank() ? "img_image" : "img_" + var10;
               var49.put("id", var9);
            }

            String var51 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var49, "name")), var50}).trim();
            if (!var51.isBlank()) {
               var49.put("name", var51);
            }

            double var11 = this.resolveImageSizeDimension(var49, true, Math.max(1.0, (double)(var4 * 256)));
            double var13 = this.resolveImageSizeDimension(var49, false, Math.max(1.0, (double)(var3.size() * 256)));
            Map var15 = this.toStringObjectMap(var49.get("size"));
            if (var15 == null) {
               var15 = new LinkedHashMap();
            }

            var15.put("width", var11);
            var15.put("height", var13);
            var49.put("size", var15);
            double var16 = this.parseDouble(this.readMapPathValue(var49, "layer"), 1.0);
            double var18 = this.resolveCompactImageLayoutScale(var1, true);
            double var20 = this.resolveCompactImageLayoutScale(var1, false);
            double var22 = this.resolveCompactImageLayoutOffset(var1, true);
            double var24 = this.resolveCompactImageLayoutOffset(var1, false);
            boolean var26 = this.resolveCompactImageHasPartialLastColumn(var1, var11);
            double var27 = Math.max(1.0E-4, 64.0 * var18);
            double var29 = Math.max(1.0E-4, 64.0 * var20);
            double var31 = 256.0 * var18;
            double var33 = 256.0 * var20;
            var24 = this.normalizeImageLayoutOffsetY(var24, var33);
            double var35 = 227.0 * var18;
            ArrayList var37 = new ArrayList();
            int var38 = 0;

            for (int var39 = 0; var39 < var3.size(); var39++) {
               List var40 = (List)var3.get(var39);
               if (var40 != null && !var40.isEmpty()) {
                  for (int var41 = 0; var41 < var40.size(); var41++) {
                     String var42 = (String)var40.get(var41);
                     if (var42 != null && !var42.isBlank()) {
                        var38++;
                        LinkedHashMap var43 = new LinkedHashMap();
                        var43.put("type", "text");
                        var43.put("id", var9 + "_" + var38);
                        var43.put("name", this.firstNonBlank(new String[]{var51, var50, var9}) + " " + var38);
                        var43.put("layer", var16);
                        var43.put("opacity", 255);
                        var43.put("color", "ffffff");
                        var43.put("align", "left");
                        var43.put("text", "<font:uiimages>" + var42);
                        var43.put("visible", true);
                        var43.put("locked", false);
                        if (!var48.isBlank()) {
                           var43.put("hover_image_source", var48);
                           var43.put("hover_image_row", var39);
                           var43.put("hover_image_col", var41);
                           if (!var6.isEmpty() && var39 < var6.size()) {
                              List var44 = (List)var6.get(var39);
                              if (var44 != null && var41 < var44.size()) {
                                 String var45 = (String)var44.get(var41);
                                 if (!this.firstNonBlank(new String[]{var45}).isBlank()) {
                                    var43.put("hover_text", "<font:uiimages>" + var45);
                                 }
                              }
                           }
                        }

                        LinkedHashMap var58 = new LinkedHashMap();
                        double var60 = (double)var41 * var31;
                        if (var26 && var40.size() > 1 && var41 == var40.size() - 1) {
                           var60 = (double)(var41 - 1) * var31 + var35;
                        }

                        var58.put("x", var22 + var60);
                        var58.put("y", var24 + (double)var39 * var33);
                        var43.put("position", var58);
                        LinkedHashMap var47 = new LinkedHashMap();
                        var47.put("width", var27);
                        var47.put("height", var29);
                        var43.put("size", var47);
                        var37.add(var43);
                     }
                  }
               }
            }

            Object var53 = var1.get("children");
            if (var53 instanceof List) {
               for (Object var56 : (List)var53) {
                  Map var57 = this.toStringObjectMap(var56);
                  if (var57 != null && !var57.isEmpty()) {
                     String var59 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var57, "text"))}).trim();
                     if (this.extractUiImageGlyphToken(var59).isBlank()) {
                        var37.add(this.deepCopyMap(var57));
                     }
                  }
               }
            }

            if (!var37.isEmpty()) {
               this.anchorImageGroupChildrenVerticallyOnLoad(var37, var13);
               this.anchorImageGroupChildrenHorizontallyOnLoad(var37);
               this.applyImageGroupSingleTilePartialCorrection(var37, var11, var13);
               var49.put("children", var37);
            }

            return var49;
         } else {
            Map var5 = this.deepCopyMap(var1);
            var5.put("type", "hitbox");
            var5.put("__editor_inherit_target_to_children", true);
            if (!var5.containsKey("opacity")) {
               var5.put("opacity", 0);
            }

            if (!var5.containsKey("visible")) {
               var5.put("visible", true);
            }

            if (!var5.containsKey("locked")) {
               var5.put("locked", false);
            }

            return var5;
         }
      } else {
         return null;
      }
   }

   protected void anchorImageGroupChildrenHorizontallyOnLoad(List<Map<String, Object>> var1) {
      if (var1 != null && !var1.isEmpty()) {
         double var2 = Double.POSITIVE_INFINITY;
         double var4 = Double.NaN;
         boolean var6 = false;

         for (Map var8 : var1) {
            if (var8 != null && !var8.isEmpty()) {
               String var9 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var8, "text"))}).trim();
               if (!this.extractUiImageGlyphToken(var9).isBlank()) {
                  double var10 = this.parseDouble(this.readMapPathValue(var8, "position.x"), this.parseDouble(this.readMapPathValue(var8, "x"), Double.NaN));
                  if (Double.isFinite(var10)) {
                     double var12 = Math.abs(
                        this.parseDouble(this.readMapPathValue(var8, "size.width"), this.parseDouble(this.readMapPathValue(var8, "width"), Double.NaN))
                     );
                     if (var10 < var2) {
                        var2 = var10;
                        var4 = var12;
                     }

                     var6 = true;
                  }
               }
            }
         }

         if (var6 && Double.isFinite(var2) && Double.isFinite(var4)) {
            double var18 = 0.45281250000000006;
            double var19 = var4 * 0.45281250000000006 - 29.0;
            double var11 = var19 - var2;
            if (Double.isFinite(var11) && !(Math.abs(var11) < 1.0E-5)) {
               for (Map var14 : var1) {
                  if (var14 != null && !var14.isEmpty()) {
                     String var15 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var14, "text"))}).trim();
                     if (!this.extractUiImageGlyphToken(var15).isBlank()) {
                        double var16 = this.parseDouble(
                           this.readMapPathValue(var14, "position.x"), this.parseDouble(this.readMapPathValue(var14, "x"), Double.NaN)
                        );
                        if (Double.isFinite(var16)) {
                           this.setMapPathValue(var14, "position.x", Double.valueOf(var16 + var11));
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected void anchorImageGroupChildrenVerticallyOnLoad(List<Map<String, Object>> var1, double var2) {
      if (var1 != null && !var1.isEmpty()) {
         double var4 = Double.POSITIVE_INFINITY;
         double var6 = Double.NaN;
         boolean var8 = false;

         for (Map var10 : var1) {
            if (var10 != null && !var10.isEmpty()) {
               String var11 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var10, "text"))}).trim();
               if (!this.extractUiImageGlyphToken(var11).isBlank()) {
                  double var12 = this.parseDouble(this.readMapPathValue(var10, "position.y"), this.parseDouble(this.readMapPathValue(var10, "y"), Double.NaN));
                  if (Double.isFinite(var12)) {
                     if (var12 < var4) {
                        var4 = var12;
                        var6 = Math.abs(
                           this.parseDouble(this.readMapPathValue(var10, "size.height"), this.parseDouble(this.readMapPathValue(var10, "height"), Double.NaN))
                        );
                     }

                     var8 = true;
                  }
               }
            }
         }

         if (var8 && Double.isFinite(var4)) {
            double var18 = Double.isFinite(var6) ? IMAGE_GLYPH_SCALE_DRIFT_FACTOR * (var6 - 64.0) : 0.0;
            double var19 = var18 - var4;
            if (Double.isFinite(var19) && !(Math.abs(var19) < 1.0E-5)) {
               for (Map var14 : var1) {
                  if (var14 != null && !var14.isEmpty()) {
                     String var15 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var14, "text"))}).trim();
                     if (!this.extractUiImageGlyphToken(var15).isBlank()) {
                        double var16 = this.parseDouble(
                           this.readMapPathValue(var14, "position.y"), this.parseDouble(this.readMapPathValue(var14, "y"), Double.NaN)
                        );
                        if (Double.isFinite(var16)) {
                           this.setMapPathValue(var14, "position.y", Double.valueOf(var16 + var19));
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected String resolveCompactImageGlyphMatrix(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         String var2 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "glyph_matrix"))}).trim();
         if (var2.isBlank()) {
            var2 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "glyphMatrix"))}).trim();
         }

         if (var2.isBlank()) {
            var2 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "matrix"))}).trim();
         }

         if (var2.isBlank()) {
            String var3 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "image"))}).trim();
            if (!var3.isBlank()) {
               UiImageAtlasService.GeneratedImage var4 = this.plugin.resolveGeneratedUiImageForEditor(var3);
               if (var4 != null) {
                  String var5 = this.firstNonBlank(new String[]{var4.glyphMatrix()}).trim();
                  if (!var5.isBlank()) {
                     var2 = var5;
                  }
               }
            }
         }

         return var2.replace("\r\n", "\n").replace('\r', '\n');
      } else {
         return "";
      }
   }

   protected double resolveCompactImageLayoutScale(Map<String, Object> var1, boolean var2) {
      if (var1 != null && !var1.isEmpty()) {
         String var3 = var2 ? "image_scale_x" : "image_scale_y";
         String var4 = var2 ? "imageScaleX" : "imageScaleY";
         double var5 = this.parseDouble(this.readMapPathValue(var1, var3), this.parseDouble(this.readMapPathValue(var1, var4), 1.0));
         return Double.isFinite(var5) && !(Math.abs(var5) < 1.0E-4) ? Math.abs(var5) : 1.0;
      } else {
         return 1.0;
      }
   }

   protected double resolveCompactImageLayoutOffset(Map<String, Object> var1, boolean var2) {
      if (var1 != null && !var1.isEmpty()) {
         String var3 = var2 ? "image_offset_x" : "image_offset_y";
         String var4 = var2 ? "imageOffsetX" : "imageOffsetY";
         double var5 = this.parseDouble(this.readMapPathValue(var1, var3), this.parseDouble(this.readMapPathValue(var1, var4), 0.0));
         return !Double.isFinite(var5) ? 0.0 : var5;
      } else {
         return 0.0;
      }
   }

   protected boolean resolveCompactImageHasPartialLastColumn(Map<String, Object> var1, double var2) {
      if (var1 != null && !var1.isEmpty()) {
         Object var4 = this.readMapPathValue(var1, "image_partial_last_column");
         if (var4 != null) {
            return this.parseBooleanFlag(var4, false);
         }

         Object var5 = this.readMapPathValue(var1, "imagePartialLastColumn");
         if (var5 != null) {
            return this.parseBooleanFlag(var5, false);
         }
      }

      int var6 = Math.max(0, (int)Math.round(var2) % 256);
      return var6 > 0;
   }

   protected double normalizeImageLayoutOffsetY(double var1, double var3) {
      if (!Double.isFinite(var1)) {
         return 0.0;
      } else {
         double var5 = Math.abs(var1);
         double var7 = Math.abs(var3);
         if (!(var5 <= 1.0E-4) && !(var7 <= 1.0E-4)) {
            double var9 = var5 / var7;
            double var11 = Math.rint(var9);
            return var11 >= 1.0 && Math.abs(var9 - var11) <= 0.01 ? 0.0 : var1;
         } else {
            return var1;
         }
      }
   }

   protected double resolveImageSizeDimension(Map<String, Object> var1, boolean var2, double var3) {
      if (var1 != null && !var1.isEmpty()) {
         String var5 = var2 ? "width" : "height";
         String var6 = var2 ? "image_width" : "image_height";
         String var7 = var2 ? "imageWidth" : "imageHeight";
         double var8 = this.parseDouble(this.readMapPathValue(var1, "size." + var5), Double.NaN);
         if (!Double.isFinite(var8)) {
            var8 = this.parseDouble(this.readMapPathValue(var1, var5), Double.NaN);
         }

         if (!Double.isFinite(var8)) {
            var8 = this.parseDouble(this.readMapPathValue(var1, var6), Double.NaN);
         }

         if (!Double.isFinite(var8)) {
            var8 = this.parseDouble(this.readMapPathValue(var1, var7), Double.NaN);
         }

         if (!Double.isFinite(var8)) {
            var8 = var3;
         }

         return Math.max(1.0, var8);
      } else {
         return Math.max(1.0, var3);
      }
   }

   protected List<Map<String, Object>> compactImageBlocksForSave(List<Map<String, Object>> var1) {
      if (var1 != null && !var1.isEmpty()) {
         ArrayList var2 = new ArrayList();

         for (Map var4 : var1) {
            if (var4 != null && !var4.isEmpty()) {
               var2.add(this.compactImageBlockForSave(this.deepCopyMap(var4)));
            }
         }

         return var2;
      } else {
         return Collections.emptyList();
      }
   }

   protected Map<String, Object> compactImageBlockForSave(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         if (var1.get("children") instanceof List var3 && !var3.isEmpty()) {
            ArrayList var4 = new ArrayList();

            for (Object var6 : var3) {
               Map var7 = this.toStringObjectMap(var6);
               if (var7 != null && !var7.isEmpty()) {
                  var4.add(this.compactImageBlockForSave(this.deepCopyMap(var7)));
               }
            }

            var1.put("children", var4);
         }

         Map var8 = this.buildCompactImageBlockFromLegacy(var1);
         return var8 == null ? var1 : var8;
      } else {
         return var1;
      }
   }

   protected Map<String, Object> buildCompactImageBlockFromLegacy(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty() && this.isImageGroupRoot(var1)) {
         if (var1.get("children") instanceof List var3 && !var3.isEmpty()) {
            String var4 = this.buildGlyphMatrixFromLegacyChildren(var3);
            if (var4.isBlank()) {
               return null;
            }

            ArrayList var5 = new ArrayList();

            for (Object var7 : var3) {
               Map var8 = this.toStringObjectMap(var7);
               if (var8 != null && !var8.isEmpty()) {
                  String var9 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var8, "text"))}).trim();
                  if (this.extractUiImageGlyphToken(var9).isBlank()) {
                     var5.add(this.deepCopyMap(var8));
                  }
               }
            }

            Map var10 = this.deepCopyMap(var1);
            var10.put("type", "image");
            var10.remove("glyph_matrix");
            var10.remove("glyphMatrix");
            var10.remove("matrix");
            var10.remove("children");
            var10.remove("__editor_inherit_target_to_children");
            String var11 = this.deriveImageBlockName(var1);
            if (!var11.isBlank()) {
               var10.put("image", var11);
            }

            this.applyCompactImageLayoutMetadata(var10, var3);
            if (!var5.isEmpty()) {
               var10.put("children", var5);
            }

            return var10;
         }

         return null;
      } else {
         return null;
      }
   }

   protected void applyCompactImageLayoutMetadata(Map<String, Object> var1, List<?> var2) {
      if (var1 != null && !var1.isEmpty() && var2 != null && !var2.isEmpty()) {
         double var3 = Double.NaN;
         double var5 = Double.NaN;
         double var7 = Double.NaN;
         double var9 = Double.NaN;
         long var11 = Long.MIN_VALUE;
         TreeMap<Long, List<Double>> var13 = new TreeMap<>();

         for (Object var15 : var2) {
            Map var16 = this.toStringObjectMap(var15);
            if (var16 != null && !var16.isEmpty()) {
               String var17 = this.extractUiImageGlyphToken(this.stringValue(this.readMapPathValue(var16, "text")));
               if (!var17.isBlank()) {
                  double var18 = this.parseDouble(this.readMapPathValue(var16, "position.x"), this.parseDouble(this.readMapPathValue(var16, "x"), Double.NaN));
                  double var20 = this.parseDouble(this.readMapPathValue(var16, "position.y"), this.parseDouble(this.readMapPathValue(var16, "y"), Double.NaN));
                  if (Double.isFinite(var18) && Double.isFinite(var20)) {
                     double var22 = Math.abs(
                        this.parseDouble(this.readMapPathValue(var16, "size.width"), this.parseDouble(this.readMapPathValue(var16, "width"), 64.0))
                     );
                     double var24 = Math.abs(
                        this.parseDouble(this.readMapPathValue(var16, "size.height"), this.parseDouble(this.readMapPathValue(var16, "height"), 64.0))
                     );
                     long var26 = Math.round(var20 * 1000.0);
                     var13.computeIfAbsent(var26, var0 -> new ArrayList<>()).add(var18);
                     if (!Double.isFinite(var5) || var20 < var5 || Math.abs(var20 - var5) < 1.0E-4 && var18 < var3) {
                        var3 = var18;
                        var5 = var20;
                        var7 = Math.max(1.0E-4, var22);
                        var9 = Math.max(1.0E-4, var24);
                        var11 = var26;
                     }
                  }
               }
            }
         }

         if (Double.isFinite(var3) && Double.isFinite(var5)) {
            double var28 = var7 / 64.0;
            double var30 = var9 / 64.0;
            if (!Double.isFinite(var28) || Math.abs(var28) < 1.0E-4) {
               var28 = 1.0;
            }

            if (!Double.isFinite(var30) || Math.abs(var30) < 1.0E-4) {
               var30 = 1.0;
            }

            var28 = Math.abs(var28);
            var30 = Math.abs(var30);
            double var32 = this.normalizeImageLayoutOffsetY(var5, 256.0 * var30);
            boolean var33 = false;
            List<Double> var21 = var13.get(var11);
            if (var21 != null && var21.size() > 1) {
               var21.sort(Double::compareTo);
               double var34 = 256.0 * var28;
               double var35 = var3 + (double)(var21.size() - 1) * var34;
               double var36 = (Double)var21.get(var21.size() - 1);
               var33 = Math.abs(var36 - var35) > 0.5;
            }

            if (Math.abs(var28 - 1.0) > 1.0E-4) {
               var1.put("image_scale_x", var28);
            } else {
               var1.remove("image_scale_x");
               var1.remove("imageScaleX");
            }

            if (Math.abs(var30 - 1.0) > 1.0E-4) {
               var1.put("image_scale_y", var30);
            } else {
               var1.remove("image_scale_y");
               var1.remove("imageScaleY");
            }

            if (Math.abs(var3) > 1.0E-4) {
               var1.put("image_offset_x", var3);
            } else {
               var1.remove("image_offset_x");
               var1.remove("imageOffsetX");
            }

            if (Math.abs(var32) > 1.0E-4) {
               var1.put("image_offset_y", var32);
            } else {
               var1.remove("image_offset_y");
               var1.remove("imageOffsetY");
            }

            if (var33) {
               var1.put("image_partial_last_column", true);
            } else {
               var1.remove("image_partial_last_column");
               var1.remove("imagePartialLastColumn");
            }
         }
      }
   }

   protected String buildGlyphMatrixFromLegacyChildren(List<?> var1) {
      if (var1 != null && !var1.isEmpty()) {
         TreeMap<Long, List<Object[]>> var2 = new TreeMap<>();

         for (Object var4 : var1) {
            Map var5 = this.toStringObjectMap(var4);
            if (var5 != null && !var5.isEmpty()) {
               String var6 = this.extractUiImageGlyphToken(this.stringValue(this.readMapPathValue(var5, "text")));
               if (!var6.isBlank()) {
                  double var7 = this.parseDouble(this.readMapPathValue(var5, "position.x"), this.parseDouble(this.readMapPathValue(var5, "x"), 0.0));
                  double var9 = this.parseDouble(this.readMapPathValue(var5, "position.y"), this.parseDouble(this.readMapPathValue(var5, "y"), 0.0));
                  long var11 = Math.round(var9 * 1000.0);
                  List<Object[]> var13 = var2.computeIfAbsent(var11, var0 -> new ArrayList<>());
                  var13.add(new Object[]{var7, var6});
               }
            }
         }

         if (var2.isEmpty()) {
            return "";
         } else {
            StringBuilder var14 = new StringBuilder();
            int var15 = 0;

            for (List<Object[]> var17 : var2.values()) {
               if (var17 != null && !var17.isEmpty()) {
                  var17.sort((var1x, var2x) -> Double.compare(this.parseDouble(var1x[0], 0.0), this.parseDouble(var2x[0], 0.0)));
                  StringBuilder var18 = new StringBuilder();

                  for (Object[] var19 : var17) {
                     String var10 = var19[1] == null ? "" : var19[1].toString();
                     if (!var10.isBlank()) {
                        var18.append(var10);
                     }
                  }

                  if (var18.length() != 0) {
                     if (var15 > 0) {
                        var14.append('\n');
                     }

                     var14.append((CharSequence)var18);
                     var15++;
                  }
               }
            }

            return var14.toString();
         }
      } else {
         return "";
      }
   }

   protected String extractUiImageGlyphToken(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).replace("\r\n", "\n").replace('\r', '\n').trim();
      if (var2.isBlank()) {
         return "";
      } else {
         String var3 = var2.toLowerCase(Locale.ROOT);
         int var4 = var3.lastIndexOf("<font:uiimages>");
         if (var4 >= 0) {
            var2 = var2.substring(var4 + "<font:uiimages>".length()).trim();
         }

         int var5 = var2.indexOf(10);
         if (var5 >= 0) {
            var2 = var2.substring(0, var5).trim();
         }

         if (var2.isBlank()) {
            return "";
         } else if (var2.toLowerCase(Locale.ROOT).startsWith("%img_")) {
            return var2;
         } else {
            int var6 = var2.codePointAt(0);
            return new String(Character.toChars(var6));
         }
      }
   }

   @Override
   protected boolean setTargetText(EditorSession var1, String var2, String var3) {
      String var4 = this.normalizeInlineHexGradientInput(var3);
      boolean var5 = super.setTargetText(var1, var2, var4);
      boolean var6 = this.ensureTextTargetDefaultFont(var1, var2);
      return var5 || var6;
   }

   protected String normalizeInlineHexGradientInput(String var1) {
      String var2 = var1 == null ? "" : var1;
      Matcher var3 = INLINE_HEX_GRADIENT_INPUT_PATTERN.matcher(var2);
      if (!var3.find()) {
         return var2;
      } else {
         var3.reset();
         StringBuffer var4 = new StringBuffer(var2.length() + 32);

         while (var3.find()) {
            String var5 = this.firstNonBlank(new String[]{var3.group(1)}).toLowerCase(Locale.ROOT);
            String var6 = this.firstNonBlank(new String[]{var3.group(2)});
            String var7 = this.firstNonBlank(new String[]{var3.group(3)}).toLowerCase(Locale.ROOT);
            String var8 = "<gradient:#" + var5 + ":#" + var7 + ">" + var6 + "</gradient>";
            var3.appendReplacement(var4, Matcher.quoteReplacement(var8));
         }

         var3.appendTail(var4);
         return var4.toString();
      }
   }

   protected boolean ensureTextTargetDefaultFont(EditorSession var1, String var2) {
      Map var3 = this.resolveRawTargetByTargetId(var1, var2);
      if (var3 != null && !var3.isEmpty()) {
         String var4 = this.firstNonBlank(
            new String[]{
               this.stringValue(this.readMapPathValue(var3, "font")),
               this.stringValue(this.readMapPathValue(var3, "style.font")),
               this.stringValue(this.readMapPathValue(var3, "text.font")),
               this.stringValue(this.readMapPathValue(var3, "params.font")),
               this.stringValue(this.readMapPathValue(var3, "params.style.font")),
               this.stringValue(this.readMapPathValue(var3, "params.text.font"))
            }
         );
         if (!var4.isBlank()) {
            return false;
         } else {
            String var5 = var3.containsKey("params") ? "params.font" : "font";
            this.setMapPathValue(var3, var5, "default");
            return true;
         }
      } else {
         return false;
      }
   }

   protected String deriveImageBlockName(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         String var2 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "image"))}).trim();
         if (!var2.isBlank()) {
            return var2;
         } else {
            String var3 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "name"))}).trim();
            if (!var3.isBlank()) {
               return var3;
            } else {
               String var4 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "id"))}).trim();
               if (var4.toLowerCase(Locale.ROOT).startsWith("img_")) {
                  var4 = var4.substring(4);
               }

               return var4;
            }
         }
      } else {
         return "";
      }
   }

   public boolean createGuiAndStartEditor(Player var1, String var2) {
      if (var1 == null) {
         return false;
      } else {
         String var3 = this.normalizePageKey(var2, "");
         if (var3.isBlank()) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Enter a valid page name.</yellow>"));
            return false;
         } else if (this.isInternalEditorPageName(var3)) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>This name is reserved. Choose a different one.</yellow>"));
            return false;
         } else {
            File var4 = new File(this.plugin.getDataFolder(), "contents/pages");
            if (!var4.exists() && !var4.mkdirs()) {
               this.sendEditorPlayerMessage(var1, MM.deserialize("<red>Failed to create pages folder.</red>"));
               return false;
            } else {
               File var5 = new File(var4, var3 + ".yml");
               if (var5.exists()) {
                  this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Page already exists:</yellow> <white>" + var3 + "</white>"));
                  return false;
               } else {
                  ArrayList var6 = new ArrayList();
                  Map var7 = this.loadYamlFolder("contents/pages", var6);
                  if (!var6.isEmpty()) {
                     this.reportYamlIssues(var6);
                     this.sendEditorPlayerMessage(
                        var1,
                        MM.deserialize(
                           "<red><bold>UltimateUI</bold></red> <#8a989c>»</#8a989c> <yellow>YAML syntax errors found. Check console for details.</yellow>"
                        )
                     );
                     return false;
                  } else {
                     List var8 = Collections.emptyList();
                     YamlConfiguration var9 = (YamlConfiguration)var7.get("editor_empty");
                     if (var9 != null) {
                        var8 = this.copyBlocks(var9.getList("blocks"));
                     }

                     YamlConfiguration var10 = new YamlConfiguration();
                     var10.set("name", var3);
                     var10.set("blocks", var8);

                     try {
                        var10.save(var5);
                        this.invalidateYamlFileCache(var5);
                     } catch (IOException var12) {
                        this.plugin.getLogger().warning("Failed to create page '" + var3 + "': " + var12.getMessage());
                        this.sendEditorPlayerMessage(var1, MM.deserialize("<red>Failed to create page:</red> <white>" + var3 + "</white>"));
                        return false;
                     }

                     return this.startEditor(var1, var3);
                  }
               }
            }
         }
      }
   }

   public boolean deleteGui(Player var1, String var2) {
      if (var1 == null) {
         return false;
      } else {
         String var3 = this.normalizePageKey(var2, "");
         if (var3.isBlank()) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Enter a valid page name.</yellow>"));
            return false;
         } else if (this.isInternalEditorPageName(var3)) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Cannot delete internal editor pages.</yellow>"));
            return false;
         } else {
            for (EditorSession var5 : this.editorSessions.values()) {
               if (var5 != null && var5.editMode) {
                  String var6 = this.normalizePageKey(var5.pageName, "");
                  if (var3.equalsIgnoreCase(var6)) {
                     this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>This page is currently open in the editor. Close it first.</yellow>"));
                     return false;
                  }
               }
            }

            File var8 = new File(new File(this.plugin.getDataFolder(), "contents/pages"), var3 + ".yml");
            if (var8.exists() && var8.isFile()) {
               try {
                  Files.delete(var8.toPath());
                  this.invalidateYamlFileCache(var8);

                  for (List var10 : this.playerEditorOpenPages.values()) {
                     var10.removeIf(var2x -> var3.equalsIgnoreCase(this.normalizePageKey((String)var2x, "")));
                  }

                  this.sendEditorPlayerMessage(var1, MM.deserialize("<green>Deleted page:</green> <white>" + var3 + "</white>"));
                  return true;
               } catch (IOException var7) {
                  this.plugin.getLogger().warning("Failed to delete page '" + var3 + "': " + var7.getMessage());
                  this.sendEditorPlayerMessage(var1, MM.deserialize("<red>Failed to delete page:</red> <white>" + var3 + "</white>"));
                  return false;
               }
            } else {
               this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Page not found:</yellow> <white>" + var3 + "</white>"));
               return false;
            }
         }
      }
   }

   public boolean startEditor(Player var1, String var2) {
      if (var1 != null) {
         UUID var3 = this.findActiveEditorOwnerForPage(var2, var1.getUniqueId());
         if (var3 != null) {
            return this.notifyEditorPageLocked(var1, var2, var3);
         }
      }

      if (var1 != null && this.isEditorOpen(var1) && this.cameraService.isCameraActive(var1)) {
         Location var7 = this.cameraService.getCameraBaseLocation(var1);
         this.cameraService.forceStop(var1);
         if (var7 != null && var7.getWorld() != null) {
            PlatformCompat.teleportSafely(var1, var7);
         }
      }

      this.normalizeEditorStartPitch(var1);
      boolean var8 = var1 != null && this.isEditorOpen(var1) && this.cameraService.isCameraActive(var1);
      boolean var4 = this.openGui(var1, var2, true, var8);
      if (var4 && var1 != null) {
         EditorSession var5 = this.editorSessions.get(var1.getUniqueId());
         String var6 = var5 == null ? this.normalizePageKey(var2, var2) : var5.pageName;
         this.rememberOpenedEditorPage(var1.getUniqueId(), var6);
         this.playEditorSfx(var1, "editor-opened");
      }

      return var4;
   }

   protected void normalizeEditorStartPitch(Player var1) {
      if (var1 != null) {
         Location var2 = var1.getLocation();
         if (var2 != null && var2.getWorld() != null) {
            float var3 = var2.getPitch();
            if (Float.isFinite(var3) && !(var3 <= 45.0F)) {
               var1.setRotation(var2.getYaw(), 40.0F);
            }
         }
      }
   }

   public boolean isEditorOpen(Player var1) {
      if (var1 == null) {
         return false;
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         return var2 != null && var2.editMode && var2.previewMode;
      }
   }

   protected UUID findActiveEditorOwnerForPage(String var1, UUID var2) {
      String var3 = this.normalizePageKey(var1, "");
      if (!var3.isBlank() && !this.isInternalEditorPageName(var3) && !this.editorSessions.isEmpty()) {
         for (Map.Entry<?, ?> var5 : ((Map<?, ?>)this.editorSessions).entrySet()) {
            UUID var6 = (UUID)var5.getKey();
            if (var6 != null && (var2 == null || !var2.equals(var6))) {
               EditorSession var7 = (EditorSession)var5.getValue();
               if (var7 != null && var7.editMode && var7.previewMode) {
                  String var8 = this.normalizePageKey(var7.pageName, "");
                  if (var3.equalsIgnoreCase(var8)) {
                     return var6;
                  }
               }
            }
         }

         return null;
      } else {
         return null;
      }
   }

   protected String resolveActiveEditorOwnerName(UUID var1) {
      if (var1 == null) {
         return "another player";
      } else {
         Player var2 = Bukkit.getPlayer(var1);
         String var3 = var2 == null ? "" : this.firstNonBlank(new String[]{var2.getName()}).trim();
         return var3.isBlank() ? "another player" : var3;
      }
   }

   protected String buildEditorPageLockedMessage(String var1, UUID var2) {
      String var3 = this.normalizePageKey(var1, var1);
      String var4 = this.resolveActiveEditorOwnerName(var2);
      return "<yellow>UI <white>"
         + var3
         + "</white> is already being edited by <aqua>"
         + var4
         + "</aqua><yellow>. Ask them to close the editor first.</yellow>";
   }

   public boolean sendEditorPageLockedMessageIfPresent(Player var1, String var2) {
      if (var1 == null) {
         return false;
      } else {
         UUID var3 = this.findActiveEditorOwnerForPage(var2, var1.getUniqueId());
         if (var3 == null) {
            return false;
         } else {
            String var4 = this.buildEditorPageLockedMessage(var2, var3);
            var1.sendMessage(this.withEditorMessagePrefix(MM.deserialize(var4)));
            return true;
         }
      }
   }

   protected boolean notifyEditorPageLocked(Player var1, String var2, UUID var3) {
      if (var1 == null) {
         return false;
      } else {
         this.sendEditorPlayerMessage(var1, MM.deserialize(this.buildEditorPageLockedMessage(var2, var3)));
         return false;
      }
   }

   public boolean showSelectedPivot(Player var1) {
      if (var1 == null) {
         return false;
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && var2.editMode) {
            String var3 = this.resolvePivotCommandTargetId(var2);
            if (var3.isBlank()) {
               this.removeHudById(var1, "editor_shell_pivot_debug_marker");
               this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Select an element first.</yellow>"));
               return true;
            } else {
               GuiServiceEditorSupportA.PivotTargetData var4 = this.resolvePivotTargetData(var2, var3);
               if (var4 == null) {
                  this.removeHudById(var1, "editor_shell_pivot_debug_marker");
                  this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Failed to read the pivot of the selected element.</yellow>"));
                  return true;
               } else {
                  this.renderPivotDebugMarker(var1, var2, var4.absoluteX, var4.absoluteY);
                  this.sendEditorPlayerMessage(
                     var1,
                     MM.deserialize(
                        "<gray>Pivot ratio:</gray> <white>"
                           + this.formatPivotRatio(var4.ratioX)
                           + ", "
                           + this.formatPivotRatio(var4.ratioY)
                           + "</white> <gray>(offset: "
                           + this.formatPx(var4.offsetX)
                           + ", "
                           + this.formatPx(var4.offsetY)
                           + ", position: "
                           + this.formatPx(var4.absoluteX)
                           + ", "
                           + this.formatPx(var4.absoluteY)
                           + ", mode: "
                           + (var4.normalized ? "normalized" : "absolute")
                           + ")</gray>"
                     )
                  );
                  return true;
               }
            }
         } else {
            this.removeHudById(var1, "editor_shell_pivot_debug_marker");
            this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Open the editor first with /uui edit <name>.</yellow>"));
            return true;
         }
      }
   }

   public boolean setSelectedPivot(Player var1, String[] var2) {
      if (var1 == null) {
         return false;
      } else {
         EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
         if (var3 != null && var3.editMode) {
            String var4 = this.resolvePivotCommandTargetId(var3);
            if (var4.isBlank()) {
               this.removeHudById(var1, "editor_shell_pivot_debug_marker");
               this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Select an element first.</yellow>"));
               return true;
            } else {
               GuiServiceEditorSupportA.PivotTargetData var5 = this.resolvePivotTargetData(var3, var4);
               if (var5 == null) {
                  this.removeHudById(var1, "editor_shell_pivot_debug_marker");
                  this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Failed to read the pivot of the selected element.</yellow>"));
                  return true;
               } else {
                  GuiServiceEditorSupportA.ParsedPivotInput var6 = this.parsePivotInputFromArgs(var2);
                  if (var6 == null) {
                     this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Usage: /uui pivotset <x> <y> | <x,y> | center | abs <x> <y></yellow>"));
                     return true;
                  } else {
                     double var7 = var6.normalized ? var6.valueX * var5.width : var6.valueX;
                     double var9 = var6.normalized ? var6.valueY * var5.height : var6.valueY;
                     if (Double.isFinite(var7) && Double.isFinite(var9)) {
                        Map var11 = this.resolveRawTargetByTargetId(var3, var4);
                        if (var11 == null) {
                           this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Failed to find data for the selected element.</yellow>"));
                           return true;
                        } else if (Math.abs(var5.offsetX - var7) < 1.0E-4 && Math.abs(var5.offsetY - var9) < 1.0E-4) {
                           this.renderPivotDebugMarker(var1, var3, var5.absoluteX, var5.absoluteY);
                           this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>No changes to apply.</yellow>"));
                           return true;
                        } else {
                           this.recordEditorMutation(var3);
                           this.setMapPathValue(var11, this.resolvePivotWritePath(var11, true), Double.valueOf(var6.valueX));
                           this.setMapPathValue(var11, this.resolvePivotWritePath(var11, false), Double.valueOf(var6.valueY));
                           this.setMapPathValue(var11, this.resolvePivotNormalizedWritePath(var11), Boolean.valueOf(var6.normalized));
                           this.rerenderEditableSelection(var1, var3);
                           GuiServiceEditorSupportA.PivotTargetData var12 = this.resolvePivotTargetData(var3, var4);
                           if (var12 != null) {
                              this.renderPivotDebugMarker(var1, var3, var12.absoluteX, var12.absoluteY);
                              this.sendEditorPlayerMessage(
                                 var1,
                                 MM.deserialize(
                                    "<gray>Pivot set to:</gray> <white>"
                                       + this.formatPivotRatio(var12.ratioX)
                                       + ", "
                                       + this.formatPivotRatio(var12.ratioY)
                                       + "</white> <gray>(offset: "
                                       + this.formatPx(var12.offsetX)
                                       + ", "
                                       + this.formatPx(var12.offsetY)
                                       + ", position: "
                                       + this.formatPx(var12.absoluteX)
                                       + ", "
                                       + this.formatPx(var12.absoluteY)
                                       + ", mode: "
                                       + (var12.normalized ? "normalized" : "absolute")
                                       + ")</gray>"
                                 )
                              );
                              return true;
                           } else {
                              String var13 = this.formatPivotRatio(var5.width <= 1.0E-4 ? 0.5 : var7 / var5.width);
                              String var14 = this.formatPivotRatio(var5.height <= 1.0E-4 ? 0.5 : var9 / var5.height);
                              this.sendEditorPlayerMessage(
                                 var1,
                                 MM.deserialize(
                                    "<gray>Pivot set to:</gray> <white>"
                                       + var13
                                       + ", "
                                       + var14
                                       + "</white> <gray>(offset: "
                                       + this.formatPx(var7)
                                       + ", "
                                       + this.formatPx(var9)
                                       + ", mode: "
                                       + (var6.normalized ? "normalized" : "absolute")
                                       + ")</gray>"
                                 )
                              );
                              return true;
                           }
                        }
                     } else {
                        this.sendEditorPlayerMessage(var1, MM.deserialize("<red>Invalid pivot coordinates.</red>"));
                        return true;
                     }
                  }
               }
            }
         } else {
            this.removeHudById(var1, "editor_shell_pivot_debug_marker");
            this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Open the editor first with /uui edit <name>.</yellow>"));
            return true;
         }
      }
   }

   protected String resolvePivotCommandTargetId(EditorSession var1) {
      if (var1 == null) {
         return "";
      } else {
         String var2 = this.firstNonBlank(new String[]{this.getSidebarEditableTargetId(var1)});
         if (!var2.isBlank()) {
            return var2;
         } else {
            List var3 = this.getSelectedTargetIds(var1);
            return var3.isEmpty() ? "" : this.firstNonBlank(new String[]{(String)var3.getFirst()});
         }
      }
   }

   protected GuiServiceEditorSupportA.PivotTargetData resolvePivotTargetData(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         double var3 = 0.0;
         double var5 = 0.0;
         double var7 = 1.0;
         double var9 = 1.0;
         HoverElement var11 = this.findFirstByTargetId(var1, var2);
         if (var11 != null && var11.baseLocation != null && var11.baseScale != null) {
            var3 = var11.baseLocation.getX();
            var5 = var11.baseLocation.getY();
            var7 = Math.max(1.0, var11.baseScale.getX());
            var9 = Math.max(1.0, var11.baseScale.getY());
         } else {
            EditorRect var12 = this.getTargetBounds(var1, var2);
            if (var12 == null) {
               return null;
            }

            var3 = var12.x;
            var5 = var12.y;
            var7 = Math.max(1.0, var12.width);
            var9 = Math.max(1.0, var12.height);
         }

         Map var38 = this.resolveRawTargetByTargetId(var1, var2);
         double var13 = var7 * 0.5075;
         double var15 = var9 * 0.469;
         boolean var17 = this.hasPivotRawValue(var38, true);
         boolean var18 = this.hasPivotRawValue(var38, false);
         double var19 = this.readPivotValueFromRaw(var38, var13, true);
         double var21 = this.readPivotValueFromRaw(var38, var15, false);
         Boolean var23 = this.readPivotNormalizedSettingFromRaw(var38);
         boolean var24 = this.isPivotAxisNormalized(var19, var17, var23);
         boolean var25 = this.isPivotAxisNormalized(var21, var18, var23);
         double var26 = var24 ? var19 * var7 : var19;
         double var28 = var25 ? var21 * var9 : var21;
         double var30 = var7 <= 1.0E-4 ? 0.5075 : var26 / var7;
         double var32 = var9 <= 1.0E-4 ? 0.469 : var28 / var9;
         return new GuiServiceEditorSupportA.PivotTargetData(var7, var9, var19, var21, var24 || var25, var30, var32, var26, var28, var3 + var26, var5 + var28);
      } else {
         return null;
      }
   }

   protected double readPivotValueFromRaw(Map<String, Object> var1, double var2, boolean var4) {
      if (var1 == null) {
         return var2;
      } else {
         String[] var5 = var4 ? PIVOT_X_PATHS : PIVOT_Y_PATHS;

         for (Object var9_raw : var5) {
            String var9 = var9_raw != null ? var9_raw.toString() : null;
            if (this.hasMapPath(var1, var9)) {
               double var10 = this.readMapPathDouble(var1, var9, Double.NaN);
               if (Double.isFinite(var10)) {
                  return var10;
               }
            }
         }

         return var2;
      }
   }

   protected boolean hasPivotRawValue(Map<String, Object> var1, boolean var2) {
      if (var1 == null) {
         return false;
      } else {
         String[] var3 = var2 ? PIVOT_X_PATHS : PIVOT_Y_PATHS;

         for (Object var7_raw : var3) {
            String var7 = var7_raw != null ? var7_raw.toString() : null;
            if (this.hasMapPath(var1, var7)) {
               return true;
            }
         }

         return false;
      }
   }

   protected Boolean readPivotNormalizedSettingFromRaw(Map<String, Object> var1) {
      if (var1 == null) {
         return null;
      } else {
         for (Object var5_raw : PIVOT_NORMALIZED_PATHS) {
            String var5 = var5_raw != null ? var5_raw.toString() : null;
            if (this.hasMapPath(var1, var5)) {
               Object var6 = this.readMapPathValue(var1, var5);
               return this.parseBooleanFlag(var6, false);
            }
         }

         for (Object var10_raw : PIVOT_MODE_PATHS) {
            String var10 = var10_raw != null ? var10_raw.toString() : null;
            if (this.hasMapPath(var1, var10)) {
               String var11 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, var10))}).trim().toLowerCase(Locale.ROOT);
               if (!var11.isBlank()) {
                  if (var11.contains("norm") || var11.contains("ratio") || var11.contains("percent")) {
                     return true;
                  }

                  if (var11.contains("abs") || var11.contains("px") || var11.contains("pixel") || var11.contains("offset")) {
                     return false;
                  }
               }
            }
         }

         return null;
      }
   }

   @Override
   protected boolean isPivotAxisNormalized(double var1, boolean var3, Boolean var4) {
      if (var4 != null) {
         return var4;
      } else {
         return var3 && Double.isFinite(var1) ? var1 >= 0.0 && var1 <= 1.0 : false;
      }
   }

   protected String resolvePivotWritePath(Map<String, Object> var1, boolean var2) {
      if (var1 == null) {
         return var2 ? "pivot.x" : "pivot.y";
      } else {
         String[] var3 = var2 ? PIVOT_X_PATHS : PIVOT_Y_PATHS;

         for (Object var7_raw : var3) {
            String var7 = var7_raw != null ? var7_raw.toString() : null;
            if (this.hasMapPath(var1, var7)) {
               return var7;
            }
         }

         if (var1.containsKey("params")) {
            return var2 ? "params.pivot.x" : "params.pivot.y";
         } else {
            return var2 ? "pivot.x" : "pivot.y";
         }
      }
   }

   protected String resolvePivotNormalizedWritePath(Map<String, Object> var1) {
      if (var1 == null) {
         return "pivot.normalized";
      } else {
         for (Object var5_raw : PIVOT_NORMALIZED_PATHS) {
            String var5 = var5_raw != null ? var5_raw.toString() : null;
            if (this.hasMapPath(var1, var5)) {
               return var5;
            }
         }

         return var1.containsKey("params") ? "params.pivot.normalized" : "pivot.normalized";
      }
   }

   protected GuiServiceEditorSupportA.ParsedPivotInput parsePivotInputFromArgs(String[] var1) {
      if (var1 != null && var1.length >= 2) {
         String var2 = this.firstNonBlank(new String[]{var1[1]}).trim();
         if (var2.isBlank()) {
            return null;
         } else if (var2.equalsIgnoreCase("center") || var2.equalsIgnoreCase("default") || var2.equalsIgnoreCase("reset")) {
            return new GuiServiceEditorSupportA.ParsedPivotInput(0.5075, 0.469, true);
         } else if (var2.equalsIgnoreCase("normalized") || var2.equalsIgnoreCase("ratio") || var2.equalsIgnoreCase("rel")) {
            double[] var6 = this.parsePivotPairFromArgs(var1, 2);
            return var6 == null ? null : new GuiServiceEditorSupportA.ParsedPivotInput(var6[0], var6[1], true);
         } else if (!var2.equalsIgnoreCase("abs") && !var2.equalsIgnoreCase("absolute") && !var2.equalsIgnoreCase("px") && !var2.equalsIgnoreCase("offset")) {
            double[] var5 = this.parsePivotPairFromArgs(var1, 1);
            if (var5 == null) {
               return null;
            } else {
               boolean var4 = this.shouldTreatPivotInputAsNormalized(var1, 1, var5[0], var5[1]);
               return new GuiServiceEditorSupportA.ParsedPivotInput(var5[0], var5[1], var4);
            }
         } else {
            double[] var3 = this.parsePivotPairFromArgs(var1, 2);
            return var3 == null ? null : new GuiServiceEditorSupportA.ParsedPivotInput(var3[0], var3[1], false);
         }
      } else {
         return null;
      }
   }

   protected double[] parsePivotPairFromArgs(String[] var1, int var2) {
      if (var1 != null && var2 < var1.length) {
         String var3 = this.firstNonBlank(new String[]{var1[var2]}).trim();
         if (var3.isBlank()) {
            return null;
         } else {
            if (var1.length > var2 + 1) {
               String var4 = this.firstNonBlank(new String[]{var1[var2 + 1]}).trim();
               if (!var4.isBlank()) {
                  double var5 = this.parsePivotInputNumber(var3);
                  double var7 = this.parsePivotInputNumber(var4);
                  if (Double.isFinite(var5) && Double.isFinite(var7)) {
                     return new double[]{var5, var7};
                  }
               }
            }

            String var10 = var3.replace("(", "").replace(")", "").replace(";", ",").replace(":", ",");
            if (var10.contains(",")) {
               String[] var11 = var10.split(",", -1);
               if (var11.length == 2) {
                  double var6 = this.parsePivotInputNumber(var11[0]);
                  double var8 = this.parsePivotInputNumber(var11[1]);
                  if (Double.isFinite(var6) && Double.isFinite(var8)) {
                     return new double[]{var6, var8};
                  }
               }
            }

            String[] var12 = var3.split("\\s+");
            if (var12.length == 2) {
               double var13 = this.parsePivotInputNumber(var12[0]);
               double var14 = this.parsePivotInputNumber(var12[1]);
               if (Double.isFinite(var13) && Double.isFinite(var14)) {
                  return new double[]{var13, var14};
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   protected boolean shouldTreatPivotInputAsNormalized(String[] var1, int var2, double var3, double var5) {
      if ((var1 == null || var1.length <= var2 || !this.firstNonBlank(new String[]{var1[var2]}).contains("%"))
         && (var1 == null || var1.length <= var2 + 1 || !this.firstNonBlank(new String[]{var1[var2 + 1]}).contains("%"))) {
         return Double.isFinite(var3) && Double.isFinite(var5) ? var3 >= 0.0 && var3 <= 1.0 && var5 >= 0.0 && var5 <= 1.0 : false;
      } else {
         return true;
      }
   }

   protected double parsePivotInputNumber(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim();
      if (var2.isEmpty()) {
         return Double.NaN;
      } else {
         boolean var3 = var2.endsWith("%");
         if (var3) {
            var2 = var2.substring(0, var2.length() - 1).trim();
         }

         double var4 = this.parseDouble(var2, Double.NaN);
         if (!Double.isFinite(var4)) {
            return Double.NaN;
         } else {
            return var3 ? var4 / 100.0 : var4;
         }
      }
   }

   protected String formatPivotRatio(double var1) {
      return !Double.isFinite(var1) ? "0.500" : String.format(Locale.ROOT, "%.3f", var1);
   }

   protected void renderPivotDebugMarker(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && Double.isFinite(var3) && Double.isFinite(var5)) {
         double var7 = this.toRenderSize(var2, 8.0);
         double var9 = this.toRenderX(var2, var3) - var7 / 2.0;
         double var11 = this.toRenderY(var2, var5) - var7 / 2.0;
         double var13 = this.toRuntimeLayer(var2, 9480.0);
         HudPositionCalculator.Placement var15 = this.positionCalculator.calculateBoxPlacement(var9, var11, var13, var7, var7);
         this.upsertHud(var1, "editor_shell_pivot_debug_marker", var15.location(), var15.scale(), "<#ff6a3d>+</#ff6a3d>", 255, TextAlignment.CENTER);
         int var16 = ++var2.pivotDebugMarkerToken;
         PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
            if (var1.isOnline()) {
               EditorSession var4 = this.editorSessions.get(var1.getUniqueId());
               if (var4 != null && var4 == var2 && var4.pivotDebugMarkerToken == var16) {
                  this.removeHudById(var1, "editor_shell_pivot_debug_marker");
               }
            }
         }, 80L);
      }
   }

   @Override
   public boolean clickHoveredElement(Player var1, GuiService.ClickType var2) {
      if (var1 == null) {
         return false;
      } else {
         EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
         if (var3 != null && !var3.editMode && this.isEditorMenuSession(var3)) {
            return this.handleEditorMenuClick(var1, var3, var2);
         } else if (var3 != null && !var3.editMode) {
            return this.handleRuntimeGuiActionClick(var1, var3, var2);
         } else {
            if (var3 != null && var3.editMode && var3.previewMode) {
               double var4 = var3.cursorX + var3.hitboxOffsetX;
               double var6 = var3.cursorY + var3.hitboxOffsetY;
               if (this.handleCursorPageDropdownClick(var1, var3, var2, var4, var6)) {
                  return true;
               }

               if (this.handleCursorLayersDropdownClick(var1, var3, var2, var4, var6)) {
                  return true;
               }

               if (this.handleActivePagesClick(var1, var3, var2, var4, var6)) {
                  return true;
               }
            }

            return super.clickHoveredElement(var1, var2);
         }
      }
   }

   protected boolean handleRuntimeGuiActionClick(Player var1, EditorSession var2, GuiService.ClickType var3) {
      if (var1 != null && var2 != null && var3 != null) {
         long var4 = (long)Bukkit.getCurrentTick();
         if (var3 == GuiService.ClickType.LEFT) {
            boolean var6 = var4 - var2.lastLeftClickTick <= 2L;
            if (var2.runtimeActionAwaitingLeftRelease) {
               if (var6) {
                  var2.lastLeftClickTick = var4;
                  return true;
               }

               var2.runtimeActionAwaitingLeftRelease = false;
            }
         }

         if (var2.runtimeActionChainLocked) {
            return true;
         } else {
            double var19 = var2.cursorX + var2.hitboxOffsetX;
            double var8 = var2.cursorY + var2.hitboxOffsetY;
            HoverElement var10 = this.findHoveredRuntimeElement(var2, var19, var8);
            UltimateUIBlockClickEvent.ClickType var11 = var3 == GuiService.ClickType.LEFT
               ? UltimateUIBlockClickEvent.ClickType.LEFT
               : UltimateUIBlockClickEvent.ClickType.RIGHT;
            if (this.fireRuntimeClickEvent(var1, var2, var10, var11)) {
               if (var3 == GuiService.ClickType.LEFT) {
                  var2.lastLeftClickTick = var4;
                  var2.runtimeActionAwaitingLeftRelease = true;
               }

               return true;
            } else if (var10 == null) {
               return false;
            } else {
               if (var10.clickEffect != null && !var10.clickEffect.isBlank()) {
                  this.applyClickEffectForTarget(var1, var2, this.firstNonBlank(new String[]{var10.targetId}), var10.clickEffect);
               }

               for (String var14 : this.resolveRuntimeActionTargetPathCandidates(var10)) {
                  List var15 = this.resolveRuntimeActionsByTargetPath(var2, var14);
                  if (var15 != null && !var15.isEmpty()) {
                     long var16 = this.beginRuntimeActionChain(var2);
                     if (var3 == GuiService.ClickType.LEFT) {
                        var2.lastLeftClickTick = var4;
                        var2.runtimeActionAwaitingLeftRelease = true;
                     }

                     this.executeRuntimeTargetActions(var1, var2, var16, var15, 0);
                     return true;
                  }
               }

               for (String var22 : this.resolveRuntimeActionTargetCandidates(var2, var10)) {
                  List var23 = this.resolveTargetActionsRawList(var2, var22, false);
                  if (var23 != null && !var23.isEmpty()) {
                     long var17 = this.beginRuntimeActionChain(var2);
                     if (var3 == GuiService.ClickType.LEFT) {
                        var2.lastLeftClickTick = var4;
                        var2.runtimeActionAwaitingLeftRelease = true;
                     }

                     this.executeRuntimeTargetActions(var1, var2, var17, var23, 0);
                     return true;
                  }
               }

               return false;
            }
         }
      } else {
         return false;
      }
   }

   protected boolean fireRuntimeClickEvent(Player var1, EditorSession var2, HoverElement var3, UltimateUIBlockClickEvent.ClickType var4) {
      if (var1 != null && var2 != null && var4 != null) {
         String var5 = var3 == null ? "" : this.firstNonBlank(new String[]{var3.id, var3.targetId, this.targetIdOf(var3)});
         double var6 = var3 == null ? 0.0 : var3.z;
         UltimateUIBlockClickEvent var8 = new UltimateUIBlockClickEvent(
            var1, this.firstNonBlank(new String[]{var2.pageName}), var5, var6, var2.cursorX, var2.cursorY, var4
         );
         Bukkit.getPluginManager().callEvent(var8);
         return var8.isCancelled();
      } else {
         return false;
      }
   }

   public boolean handleRuntimeScrollClick(Player var1, int var2) {
      if (var1 != null && var2 != 0) {
         EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
         if (var3 != null && !var3.editMode) {
            double var4 = var3.cursorX + var3.hitboxOffsetX;
            double var6 = var3.cursorY + var3.hitboxOffsetY;
            HoverElement var8 = this.findHoveredRuntimeElement(var3, var4, var6);
            UltimateUIBlockClickEvent.ClickType var9 = var2 < 0
               ? UltimateUIBlockClickEvent.ClickType.SCROLL_UP
               : UltimateUIBlockClickEvent.ClickType.SCROLL_DOWN;
            this.fireRuntimeClickEvent(var1, var3, var8, var9);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public boolean hasActiveRuntimeHudSession(Player var1) {
      if (var1 == null) {
         return false;
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         return var2 != null && !var2.editMode && !this.isEditorMenuSession(var2);
      }
   }

   public boolean setOpenUiElementValue(Player var1, String var2, String var3, Object var4) {
      String var5 = this.firstNonBlank(new String[]{var3}).trim();
      if (var5.isBlank()) {
         return false;
      } else {
         Object var6 = this.normalizeOpenUiElementValue(var4);
         return var6 == null ? false : this.applyOpenUiElementMutation(var1, var2, (var3x, var4x) -> {
            Object var5x = this.readMapPathValue(var4x, var5);
            if (this.areOpenUiMutationValuesEqual(var5x, var6)) {
               return false;
            } else {
               this.setMapPathValue(var4x, var5, var6);
               return true;
            }
         });
      }
   }

   public boolean setOpenUiElementText(Player var1, String var2, String var3) {
      String var4 = var3 == null ? "" : var3;
      return this.applyOpenUiElementMutation(var1, var2, (var2x, var3x) -> {
         String var4x = this.resolvePreferredOpenUiWritePath(var3x, "text", "text", "params.text", "unicode", "params.unicode");
         String var5 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var3x, var4x))});
         if (var5.equals(var4)) {
            return false;
         } else {
            this.setMapPathValue(var3x, var4x, var4);
            return true;
         }
      });
   }

   public boolean setOpenUiElementColor(Player var1, String var2, String var3) {
      String var4 = this.normalizeHexColor(var3);
      return var4.isBlank() ? false : this.applyOpenUiElementMutation(var1, var2, (var2x, var3x) -> {
         String var4x = this.resolvePreferredOpenUiWritePath(var3x, "color", "color", "style.color", "params.color", "params.style.color");
         String var5 = this.normalizeHexColor(this.stringValue(this.readMapPathValue(var3x, var4x)));
         if (var4.equalsIgnoreCase(var5)) {
            return false;
         } else {
            this.setMapPathValue(var3x, var4x, var4);
            return true;
         }
      });
   }

   public boolean setOpenUiElementPosition(Player var1, String var2, double var3, double var5) {
      return Double.isFinite(var3) && Double.isFinite(var5) ? this.applyOpenUiElementMutation(var1, var2, (var5x, var6) -> {
         String var7 = this.resolvePreferredOpenUiWritePath(var6, "position.x", "position.x", "x", "params.position.x", "params.x");
         String var8 = this.resolvePreferredOpenUiWritePath(var6, "position.y", "position.y", "y", "params.position.y", "params.y");
         boolean var9 = this.writeOpenUiDoubleIfChanged(var6, var7, var3);
         boolean var10 = this.writeOpenUiDoubleIfChanged(var6, var8, var5);
         return var9 || var10;
      }) : false;
   }

   public boolean setOpenUiElementScale(Player var1, String var2, double var3, double var5) {
      return Double.isFinite(var3) && Double.isFinite(var5)
         ? this.applyOpenUiElementMutation(
            var1,
            var2,
            (var5x, var6) -> {
               String var7 = this.resolvePreferredOpenUiWritePath(
                  var6, "size.width", "size.width", "width", "scale.width", "params.size.width", "params.width", "params.scale.width"
               );
               String var8 = this.resolvePreferredOpenUiWritePath(
                  var6, "size.height", "size.height", "height", "scale.height", "params.size.height", "params.height", "params.scale.height"
               );
               boolean var9 = this.writeOpenUiDoubleIfChanged(var6, var7, var3);
               boolean var10 = this.writeOpenUiDoubleIfChanged(var6, var8, var5);
               return var9 || var10;
            }
         )
         : false;
   }

   public boolean setOpenUiElementItem(Player var1, String var2, String var3) {
      String var4 = this.firstNonBlank(new String[]{var3}).trim();
      return var4.isBlank()
         ? false
         : this.applyOpenUiElementMutation(
            var1,
            var2,
            (var2x, var3x) -> {
               String var4x = this.resolvePreferredOpenUiWritePath(
                  var3x, "item", "item", "item.material", "material", "params.item", "params.item.material", "params.material"
               );
               String var5 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var3x, var4x))}).trim();
               if (var5.equalsIgnoreCase(var4)) {
                  return false;
               } else {
                  this.setMapPathValue(var3x, var4x, var4);
                  return true;
               }
            }
         );
   }

   public boolean setOpenUiElementItem(Player var1, String var2, Material var3) {
      return var3 != null && var3 != Material.AIR ? this.setOpenUiElementItem(var1, var2, var3.name()) : false;
   }

   protected boolean applyOpenUiElementMutation(Player var1, String var2, BiFunction<EditorSession, Map<String, Object>, Boolean> var3) {
      if (var1 != null && var3 != null && this.hasActiveRuntimeHudSession(var1)) {
         String var4 = this.firstNonBlank(new String[]{var2}).trim();
         if (var4.isBlank()) {
            return false;
         } else {
            EditorSession var5 = this.editorSessions.get(var1.getUniqueId());
            if (var5 != null && var5.rawBlocks != null && !var5.rawBlocks.isEmpty()) {
               Map var6 = this.resolveRawTargetByTargetId(var5, var4);
               if (var6 == null) {
                  var6 = this.resolveRawMapAtPath(var5.rawBlocks, var4);
               }

               if (var6 == null) {
                  return false;
               } else {
                  boolean var7 = Boolean.TRUE.equals(var3.apply(var5, var6));
                  if (!var7) {
                     return false;
                  } else {
                     var5.renderBlocks = this.resolveRenderableBlocks(var5.rawBlocks, var5.components);
                     this.rerenderEditableContentForTargets(var1, var5, List.of(var4), true, false);
                     return true;
                  }
               }
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   protected String resolvePreferredOpenUiWritePath(Map<String, Object> var1, String var2, String... var3) {
      if (var1 == null) {
         return var2;
      } else {
         if (var3 != null) {
            for (Object var7_raw : var3) {
               String var7 = var7_raw != null ? var7_raw.toString() : null;
               if (var7 != null && !var7.isBlank() && this.hasMapPath(var1, var7)) {
                  return var7;
               }
            }
         }

         return var2;
      }
   }

   protected boolean writeOpenUiDoubleIfChanged(Map<String, Object> var1, String var2, double var3) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         double var5 = this.parseDouble(this.readMapPathValue(var1, var2), Double.NaN);
         if (Double.isFinite(var5) && Math.abs(var5 - var3) <= 1.0E-5) {
            return false;
         } else {
            this.setMapPathValue(var1, var2, Double.valueOf(var3));
            return true;
         }
      } else {
         return false;
      }
   }

   protected Object normalizeOpenUiElementValue(Object var1) {
      if (var1 == null) {
         return null;
      } else if (var1 instanceof Material var3) {
         return var3.name();
      } else {
         return var1 instanceof ItemStack var2 ? var2.getType().name() : var1;
      }
   }

   protected boolean areOpenUiMutationValuesEqual(Object var1, Object var2) {
      if (var1 == var2) {
         return true;
      } else if (var1 == null || var2 == null) {
         return false;
      } else if (var1 instanceof Number || var2 instanceof Number) {
         double var7 = this.parseDouble(var1, Double.NaN);
         double var5 = this.parseDouble(var2, Double.NaN);
         return Double.isFinite(var7) && Double.isFinite(var5) && Math.abs(var7 - var5) <= 1.0E-5;
      } else if (!(var1 instanceof Boolean) && !(var2 instanceof Boolean)) {
         return var1.equals(var2);
      } else {
         boolean var3 = this.parseBooleanFlag(var1, false);
         boolean var4 = this.parseBooleanFlag(var2, false);
         return var3 == var4;
      }
   }

   public boolean hasActiveEditorSelectionSession(Player var1) {
      if (var1 == null) {
         return false;
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         return var2 != null && var2.editMode && var2.previewMode && !this.isEditorMenuSession(var2) ? !this.getSelectedTargetIds(var2).isEmpty() : false;
      }
   }

   public boolean alignSelectedEditorHudElement(Player var1, boolean var2) {
      if (var1 == null) {
         return false;
      } else {
         EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
         if (var3 != null && var3.editMode && var3.previewMode && !this.isEditorMenuSession(var3)) {
            List var4 = this.getSelectedTargetIds(var3);
            if (var4.isEmpty()) {
               return false;
            } else {
               TextAlignment var5 = var2 ? TextAlignment.RIGHT : TextAlignment.LEFT;
               String var6 = var2 ? "right" : "left";
               LinkedHashSet var7 = new LinkedHashSet();
               LinkedHashSet var8 = new LinkedHashSet();
               boolean var9 = false;

               for (Object var11_raw : var4) {
                  String var11 = var11_raw != null ? var11_raw.toString() : null;
                  String var12 = this.firstNonBlank(new String[]{var11});
                  if (!var12.isBlank()) {
                     LinkedHashSet var13 = this.collectRawTargetCandidatePathsByTargetId(var3, var12);
                     if (var13.isEmpty()) {
                        String var14 = this.resolveRawTargetPathByTargetId(var3, var12);
                        if (!var14.isBlank()) {
                           var13.add(var14);
                        }
                     }

                     if (!var13.isEmpty()) {
                        for (Object var15_raw : var13) {
                           String var15 = var15_raw != null ? var15_raw.toString() : null;
                           String var16 = this.firstNonBlank(new String[]{var15});
                           if (!var16.isBlank()) {
                              Map var17 = this.resolveRawMapAtPath(var3.rawBlocks, var16);
                              if (var17 != null && !var17.isEmpty()) {
                                 var8.add(var16);
                                 var7.add(this.firstNonBlank(new String[]{this.findTargetIdByPath(var3, var16), var12}));
                                 if (this.readRawHudAlignment(var17) != var5) {
                                    if (!var9) {
                                       this.recordEditorMutation(var3);
                                    }

                                    this.setMapPathValue(var17, this.resolveHudAlignedWritePath(var17), var6);
                                    var9 = true;
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               if (var7.isEmpty() && var8.isEmpty()) {
                  return false;
               } else {
                  if (var9) {
                     var3.renderBlocks = this.resolveRenderableBlocks(var3.rawBlocks, var3.components);
                     ArrayList var18 = new ArrayList(var8);
                     if (var18.isEmpty()) {
                        var18.addAll(var7);
                     }

                     this.rerenderEditableContentForTargets(var1, var3, var18, true);
                     this.rerenderEditableSelection(var1, var3);
                     this.renderLayersPanel(var1, var3);
                  }

                  return true;
               }
            }
         } else {
            return false;
         }
      }
   }

   private String resolveHudAlignedWritePath(Map<String, Object> var1) {
      if (var1 == null) {
         return "aligned";
      } else if (this.hasMapPath(var1, "aligned")) {
         return "aligned";
      } else if (this.hasMapPath(var1, "hud.aligned")) {
         return "hud.aligned";
      } else if (this.hasMapPath(var1, "hudAligned")) {
         return "hudAligned";
      } else if (this.hasMapPath(var1, "params.aligned")) {
         return "params.aligned";
      } else if (this.hasMapPath(var1, "params.hud.aligned")) {
         return "params.hud.aligned";
      } else if (this.hasMapPath(var1, "params.hudAligned")) {
         return "params.hudAligned";
      } else {
         return var1.containsKey("params") ? "params.aligned" : "aligned";
      }
   }

   private TextAlignment readRawHudAlignment(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         String var2 = this.firstNonBlank(
            new String[]{
               this.stringValue(this.readMapPathValue(var1, "aligned")),
               this.stringValue(this.readMapPathValue(var1, "hud.aligned")),
               this.stringValue(this.readMapPathValue(var1, "hudAligned")),
               this.stringValue(this.readMapPathValue(var1, "params.aligned")),
               this.stringValue(this.readMapPathValue(var1, "params.hud.aligned")),
               this.stringValue(this.readMapPathValue(var1, "params.hudAligned"))
            }
         );
         if (var2.isBlank()) {
            return TextAlignment.CENTER;
         } else {
            String var3 = var2.trim().toLowerCase(Locale.ROOT).replace('_', ' ').replace('-', ' ');
            if (var3.contains("left")) {
               return TextAlignment.LEFT;
            } else if (var3.contains("right")) {
               return TextAlignment.RIGHT;
            } else if (!var3.contains("center") && !var3.contains("middle")) {
               try {
                  int var4 = (int)Math.round(Double.parseDouble(var3));
                  if (var4 == 10000) {
                     return TextAlignment.LEFT;
                  }

                  if (var4 == 30000) {
                     return TextAlignment.RIGHT;
                  }
               } catch (NumberFormatException var5) {
               }

               return TextAlignment.CENTER;
            } else {
               return TextAlignment.CENTER;
            }
         }
      } else {
         return TextAlignment.CENTER;
      }
   }

   public boolean setSelectedTextElementLineWidth(Player var1, int var2) {
      if (var1 != null && var2 > 0) {
         EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
         if (var3 != null && var3.editMode && var3.previewMode && !this.isEditorMenuSession(var3)) {
            List var4 = this.getSelectedTargetIds(var3);
            if (var4.isEmpty()) {
               return false;
            } else {
               boolean var5 = false;

               for (Object var7_raw : var4) {
                  String var7 = var7_raw != null ? var7_raw.toString() : null;
                  String var8 = this.firstNonBlank(new String[]{var7});
                  if (!var8.isBlank() && this.isTextTarget(var3, var8)) {
                     Map var9 = this.resolveRawTargetByTargetId(var3, var8);
                     if (var9 != null) {
                        if (!var5) {
                           this.recordEditorMutation(var3);
                        }

                        this.setMapPathValue(var9, this.resolveTextWrapWritePath(var9), Integer.valueOf(var2));
                        var5 = true;
                     }
                  }
               }

               if (!var5) {
                  return false;
               } else {
                  var3.renderBlocks = this.resolveRenderableBlocks(var3.rawBlocks, var3.components);
                  this.rerenderEditableContentForTargets(var1, var3, new ArrayList<>(var4), true);
                  this.rerenderEditableSelection(var1, var3);
                  this.renderLayersPanel(var1, var3);
                  return true;
               }
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private String resolveTextWrapWritePath(Map<String, Object> var1) {
      if (var1 != null) {
         if (this.hasMapPath(var1, "text-wrap")) {
            return "text-wrap";
         }

         if (this.hasMapPath(var1, "textWrap")) {
            return "textWrap";
         }

         if (this.hasMapPath(var1, "text.wrap")) {
            return "text.wrap";
         }

         if (this.hasMapPath(var1, "params.text-wrap")) {
            return "params.text-wrap";
         }
      }

      return "text-wrap";
   }

   public boolean alignHoveredRuntimeHudElement(Player var1, boolean var2) {
      if (!this.hasActiveRuntimeHudSession(var1)) {
         return false;
      } else {
         EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
         String var4 = this.firstNonBlank(new String[]{var3.hoveredElementId, var3.selectedElementId});
         if (!var4.isBlank() && this.findFirstByTargetId(var3, var4) == null) {
            var4 = "";
         }

         if (var4.isBlank()) {
            double var5 = var3.cursorX + var3.hitboxOffsetX;
            double var7 = var3.cursorY + var3.hitboxOffsetY;
            HoverElement var9 = this.findHoveredRuntimeElement(var3, var5, var7);
            var4 = var9 == null ? "" : this.firstNonBlank(new String[]{this.targetIdOf(var9), var9.targetId});
         }

         if (var4.isBlank()) {
            return false;
         } else {
            TextAlignment var12 = var2 ? TextAlignment.RIGHT : TextAlignment.LEFT;
            boolean var6 = false;
            LinkedHashSet var13 = new LinkedHashSet();

            for (HoverElement var14 : this.findTargetElements(var3, var4)) {
               String var10 = this.firstNonBlank(new String[]{var14 == null ? null : var14.id});
               if (!var10.isBlank() && var13.add(var10)) {
                  Entity var11 = this.hudService.getHud(var1, var10);
                  if (var11 != null) {
                     var6 = true;
                     this.hudService.setHudAligned(var11, var12);
                  }
               }
            }

            return var6;
         }
      }
   }

   protected HoverElement findHoveredRuntimeElement(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.elements != null && !var1.elements.isEmpty()) {
         HoverElement var6 = null;

         for (Object var8_raw : var1.elements) {
            HoverElement var8 = (HoverElement)var8_raw;
            if (var8 != null && var8.baseLocation != null && !var8.disableHitbox) {
               EditorRect var9 = this.getHoverBounds(var1, var8);
               if (var9 != null) {
                  double var10 = var9.x;
                  double var12 = var9.y;
                  double var14 = var9.maxX();
                  double var16 = var9.maxY();
                  if (var2 >= var10 && var2 <= var14 && var4 >= var12 && var4 <= var16 && (var6 == null || var8.runtimeZ >= var6.runtimeZ)) {
                     var6 = var8;
                  }
               }
            }
         }

         return var6;
      } else {
         return null;
      }
   }

   protected List<String> resolveRuntimeActionTargetCandidates(EditorSession var1, HoverElement var2) {
      LinkedHashSet var3 = new LinkedHashSet();
      if (var2 == null) {
         return new ArrayList<>();
      } else {
         String var4 = this.firstNonBlank(new String[]{this.targetIdOf(var2), var2.targetId});
         if (!var4.isBlank()) {
            var3.add(var4);
         }

         String var5 = this.firstNonBlank(new String[]{var2.targetPath});
         this.collectRuntimeActionTargetIdsForPath(var1, var5, var3);
         String var6 = var5;

         while (!var6.isBlank()) {
            var6 = this.trimTrailingChildrenPathSegment(var6);
            if (var6.isBlank()) {
               break;
            }

            this.collectRuntimeActionTargetIdsForPath(var1, var6, var3);
         }

         return new ArrayList<>(var3);
      }
   }

   protected List<String> resolveRuntimeActionTargetPathCandidates(HoverElement var1) {
      LinkedHashSet var2 = new LinkedHashSet();
      if (var1 == null) {
         return new ArrayList<>();
      } else {
         for (String var3 = this.firstNonBlank(new String[]{var1.targetPath}); !var3.isBlank(); var3 = this.trimTrailingChildrenPathSegment(var3)) {
            var2.add(var3);
         }

         return new ArrayList<>(var2);
      }
   }

   protected List<Map<String, Object>> resolveRuntimeActionsByTargetPath(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var3 = this.resolveRawMapAtPath(var1.rawBlocks, var2);
         if (var3 == null) {
            return Collections.emptyList();
         } else if (var3.get("actions") instanceof List var5) {
            ArrayList var6 = new ArrayList();

            for (Object var8 : var5) {
               Map var9 = this.toStringObjectMap(var8);
               if (var9 != null && !var9.isEmpty()) {
                  this.normalizeActionMapForEditor(var9);
                  var6.add(var9);
               }
            }

            var3.put("actions", var6);
            return var6;
         } else {
            return Collections.emptyList();
         }
      } else {
         return Collections.emptyList();
      }
   }

   protected void collectRuntimeActionTargetIdsForPath(EditorSession var1, String var2, LinkedHashSet<String> var3) {
      if (var1 != null && var3 != null && var2 != null && !var2.isBlank() && var1.elements != null) {
         for (Object var5_raw : var1.elements) {
            HoverElement var5 = (HoverElement)var5_raw;
            if (var5 != null && var2.equals(this.firstNonBlank(new String[]{var5.targetPath}))) {
               String var6 = this.firstNonBlank(new String[]{this.targetIdOf(var5), var5.targetId});
               if (!var6.isBlank()) {
                  var3.add(var6);
               }
            }
         }
      }
   }

   protected String trimTrailingChildrenPathSegment(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim();
      if (var2.isBlank()) {
         return "";
      } else {
         int var3 = var2.lastIndexOf(".children.");
         if (var3 < 0) {
            return "";
         } else {
            String var4 = var2.substring(var3 + ".children.".length());
            if (var4.isBlank()) {
               return "";
            } else {
               for (int var5 = 0; var5 < var4.length(); var5++) {
                  if (!Character.isDigit(var4.charAt(var5))) {
                     return "";
                  }
               }

               return var2.substring(0, var3);
            }
         }
      }
   }

   protected long beginRuntimeActionChain(EditorSession var1) {
      if (var1 == null) {
         return 0L;
      } else {
         long var2 = var1.runtimeActionChainToken + 1L;
         if (var2 <= 0L) {
            var2 = 1L;
         }

         var1.runtimeActionChainToken = var2;
         var1.runtimeActionChainLocked = true;
         return var2;
      }
   }

   protected boolean isRuntimeActionChainActive(EditorSession var1, long var2) {
      return var1 != null && var2 > 0L && var1.runtimeActionChainLocked && var1.runtimeActionChainToken == var2;
   }

   protected void finishRuntimeActionChain(EditorSession var1, long var2) {
      if (var1 != null && var2 > 0L) {
         if (var1.runtimeActionChainToken == var2) {
            var1.runtimeActionChainLocked = false;
         }
      }
   }

   protected void executeRuntimeTargetActions(Player var1, List<Map<String, Object>> var2, int var3) {
      this.executeRuntimeTargetActions(var1, null, 0L, var2, var3);
   }

   protected void executeRuntimeTargetActions(Player var1, EditorSession var2, long var3, List<Map<String, Object>> var5, int var6) {
      if (var2 == null || this.isRuntimeActionChainActive(var2, var3)) {
         if (var1 != null && var1.isOnline() && var5 != null && var6 >= 0 && var6 < var5.size()) {
            try {
               Map var7 = (Map)var5.get(var6);
               String var8 = this.normalizeActionType(this.stringValue(var7 == null ? null : var7.get("type")));
               String var9 = this.resolveRuntimeActionValue(var1, this.resolveActionDisplayValue(var7, var8));
               if ("delay".equals(var8)) {
                  long var13 = this.parseRuntimeActionDelayTicks(var9);
                  if (var13 <= 0L) {
                     this.executeRuntimeTargetActions(var1, var2, var3, var5, var6 + 1);
                     return;
                  }

                  PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
                     if (!var1.isOnline()) {
                        this.finishRuntimeActionChain(var2, var3);
                     } else {
                        this.executeRuntimeTargetActions(var1, var2, var3, var5, var6 + 1);
                     }
                  }, var13);
                  return;
               }

               if ("command".equals(var8)) {
                  this.executeRuntimeCommandAction(var1, var9);
                  this.executeRuntimeTargetActions(var1, var2, var3, var5, var6 + 1);
                  return;
               }

               if ("console".equals(var8)) {
                  this.executeRuntimeConsoleAction(var1, var9);
                  this.executeRuntimeTargetActions(var1, var2, var3, var5, var6 + 1);
                  return;
               }

               if ("message".equals(var8)) {
                  this.executeRuntimeMessageAction(var1, var9);
                  this.executeRuntimeTargetActions(var1, var2, var3, var5, var6 + 1);
                  return;
               }

               if ("sound".equals(var8)) {
                  this.executeRuntimeSoundAction(var1, var9);
                  this.executeRuntimeTargetActions(var1, var2, var3, var5, var6 + 1);
                  return;
               }

               if ("teleport".equals(var8)) {
                  this.executeRuntimeTeleportAction(var1, var9);
                  this.executeRuntimeTargetActions(var1, var2, var3, var5, var6 + 1);
                  return;
               }

               if ("animation".equals(var8)) {
                  this.executeRuntimeTargetActions(var1, var2, var3, var5, var6 + 1);
                  return;
               }

               if ("redirect".equals(var8)) {
                  boolean var10 = this.executeRuntimeRedirectAction(var1, var9);
                  if (!var10) {
                     this.executeRuntimeTargetActions(var1, var2, var3, var5, var6 + 1);
                  } else {
                     this.finishRuntimeActionChain(var2, var3);
                  }

                  return;
               }

               if ("close".equals(var8)) {
                  this.executeRuntimeCloseAction(var1);
                  this.executeRuntimeTargetActions(var1, var2, var3, var5, var6 + 1);
                  return;
               }

               this.executeRuntimeTargetActions(var1, var2, var3, var5, var6 + 1);
            } catch (RuntimeException var12) {
               this.finishRuntimeActionChain(var2, var3);
            }
         } else {
            this.finishRuntimeActionChain(var2, var3);
         }
      }
   }

   protected String resolveRuntimeActionValue(Player var1, String var2) {
      String var3 = this.firstNonBlank(new String[]{var2});
      if (!var3.isBlank() && var1 != null) {
         try {
            return this.firstNonBlank(new String[]{this.resolvePlaceholders(var1, var3, false), var3});
         } catch (Exception var5) {
            return var3;
         }
      } else {
         return var3;
      }
   }

   protected void executeRuntimeCommandAction(Player var1, String var2) {
      if (var1 != null) {
         String var3 = this.firstNonBlank(new String[]{var2}).trim();
         if (var3.startsWith("/")) {
            var3 = var3.substring(1).trim();
         }

         if (!var3.isBlank()) {
            var1.performCommand(var3);
         }
      }
   }

   protected void executeRuntimeConsoleAction(Player var1, String var2) {
      if (var1 != null) {
         String var3 = this.firstNonBlank(new String[]{var2}).trim();
         if (var3.startsWith("/")) {
            var3 = var3.substring(1).trim();
         }

         if (!var3.isBlank()) {
            try {
               Bukkit.dispatchCommand(Bukkit.getConsoleSender(), var3);
            } catch (RuntimeException var5) {
            }
         }
      }
   }

   protected void executeRuntimeMessageAction(Player var1, String var2) {
      if (var1 != null) {
         String var3 = this.firstNonBlank(new String[]{var2});
         if (!var3.isBlank()) {
            Component var4 = this.resolveRuntimeMessageComponent(var3);
            if (var4 != null) {
               var1.sendMessage(var4);
            }
         }
      }
   }

   protected Component resolveRuntimeMessageComponent(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1});
      if (var2.isBlank()) {
         return Component.empty();
      } else {
         Matcher var3 = RUNTIME_MESSAGE_TOOLTIP_LINK_PATTERN.matcher(var2);
         if (!var3.find()) {
            return this.deserializeRuntimeMessageFragment(var2);
         } else {
            var3.reset();
            Component var4 = Component.empty();

            int var5;
            for (var5 = 0; var3.find(); var5 = var3.end()) {
               if (var3.start() > var5) {
                  String var6 = var2.substring(var5, var3.start());
                  var4 = var4.append(this.deserializeRuntimeMessageFragment(var6));
               }

               String var13 = this.firstNonBlank(new String[]{var3.group(1)});
               String var7 = this.firstNonBlank(new String[]{var3.group(2)}).trim();
               String var8 = this.firstNonBlank(new String[]{var3.group(3)});
               Component var9 = this.deserializeRuntimeMessageFragment(var8);
               Component var10 = this.deserializeRuntimeTooltipComponent(var13);
               if (var10 != null) {
                  var9 = var9.hoverEvent(HoverEvent.showText(var10));
               }

               if (!var7.isBlank()) {
                  try {
                     var9 = var9.clickEvent(ClickEvent.openUrl(var7));
                  } catch (IllegalArgumentException var12) {
                  }
               }

               var4 = var4.append(var9);
            }

            if (var5 < var2.length()) {
               var4 = var4.append(this.deserializeRuntimeMessageFragment(var2.substring(var5)));
            }

            return (Component)var4;
         }
      }
   }

   protected Component deserializeRuntimeTooltipComponent(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1});
      if (var2.isBlank()) {
         return Component.empty();
      } else {
         return (Component)(var2.indexOf(38) >= 0 ? LEGACY_AMPERSAND.deserialize(var2) : this.deserializeRuntimeMessageFragment(var2));
      }
   }

   protected Component deserializeRuntimeMessageFragment(String var1) {
      String var2 = var1 == null ? "" : var1;
      if (var2.isBlank()) {
         return Component.empty();
      } else {
         boolean var3 = var2.indexOf(38) >= 0 && (!var2.contains("<") || !var2.contains(">"));
         if (var3) {
            return LEGACY_AMPERSAND.deserialize(var2);
         } else {
            try {
               return MM.deserialize(var2);
            } catch (Exception var5) {
               return LEGACY_AMPERSAND.deserialize(var2);
            }
         }
      }
   }

   protected void executeRuntimeSoundAction(Player var1, String var2) {
      if (var1 != null) {
         GuiServiceEditorSupportA.RuntimeSoundSpec var3 = this.resolveRuntimeSoundSpec(var2);
         if (var3 != null && !var3.soundToken().isBlank()) {
            float var4 = (float)Math.max(0.0, var3.volume());
            float var5 = (float)Math.max(0.0, var3.pitch());
            String var6 = var3.soundToken();
            String var7 = var6.trim().replace('.', '_').replace('-', '_').replace(' ', '_').toUpperCase(Locale.ROOT);

            try {
               Sound var14 = Sound.valueOf(var7);
               var1.playSound(var1.getLocation(), var14, SoundCategory.MASTER, var4, var5);
            } catch (IllegalArgumentException var12) {
               String var8 = var6.trim().replace(' ', '.').replace('_', '.').toLowerCase(Locale.ROOT);

               while (var8.contains("..")) {
                  var8 = var8.replace("..", ".");
               }

               if (var8.startsWith(".")) {
                  var8 = var8.substring(1);
               }

               if (var8.endsWith(".")) {
                  var8 = var8.substring(0, var8.length() - 1);
               }

               if (!var8.isBlank()) {
                  try {
                     var1.playSound(var1.getLocation(), var8, SoundCategory.MASTER, var4, var5);
                  } catch (RuntimeException var11) {
                     try {
                        var1.playSound(var1.getLocation(), var6, SoundCategory.MASTER, var4, var5);
                     } catch (RuntimeException var10) {
                     }
                  }
               }
            } catch (RuntimeException var13) {
            }
         }
      }
   }

   protected GuiServiceEditorSupportA.RuntimeSoundSpec resolveRuntimeSoundSpec(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim();
      if (var2.isBlank()) {
         return null;
      } else {
         Matcher var3 = RUNTIME_SOUND_WITH_VOLUME_PITCH_PATTERN.matcher(var2);
         if (!var3.matches()) {
            return new GuiServiceEditorSupportA.RuntimeSoundSpec(var2, 1.0, 1.0);
         } else {
            String var4 = this.firstNonBlank(new String[]{var3.group(1)}).trim();
            Double var5 = this.tryParseRuntimeFlexibleDouble(var3.group(2));
            Double var6 = this.tryParseRuntimeFlexibleDouble(var3.group(3));
            double var7 = var5 == null ? 1.0 : var5;
            double var9 = var6 == null ? 1.0 : var6;
            return new GuiServiceEditorSupportA.RuntimeSoundSpec(var4, var7, var9);
         }
      }
   }

   protected void executeRuntimeTeleportAction(Player var1, String var2) {
      if (var1 != null) {
         GuiServiceEditorSupportA.RuntimeTeleportSpec var3 = this.parseRuntimeTeleportSpec(var2);
         if (var3 != null) {
            Location var4 = var1.getLocation();
            if (var4.getWorld() != null) {
               World var5 = var4.getWorld();
               String var6 = this.firstNonBlank(new String[]{var3.worldName()}).trim();
               if (!var6.isBlank()) {
                  World var7 = Bukkit.getWorld(var6);
                  if (var7 == null) {
                     return;
                  }

                  var5 = var7;
               }

               Location var11 = new Location(var5, var3.x(), var3.y(), var3.z(), var4.getYaw(), var4.getPitch());
               if (this.plugin != null) {
                  this.plugin.closeUiForPlayerAndTeleport(var1, var11);
               } else {
                  this.cameraService.forceStop(var1);
                  this.closeGui(var1);
                  this.hudService.clearHuds(var1);
                  Location var8 = var11;

                  try {
                     PlatformCompat.teleportSafely(var1, var8);
                  } catch (RuntimeException var10) {
                  }
               }
            }
         }
      }
   }

   protected GuiServiceEditorSupportA.RuntimeTeleportSpec parseRuntimeTeleportSpec(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim();
      if (var2.isBlank()) {
         return null;
      } else {
         String var3 = "";
         String var4 = var2;
         Matcher var5 = RUNTIME_TELEPORT_WORLD_PATTERN.matcher(var2);
         if (var5.find()) {
            var3 = this.firstNonBlank(new String[]{var5.group(1)}).trim();
            var4 = var5.replaceFirst(" ");
         } else {
            String[] var6 = var2.split("\\s+");
            if (var6.length >= 4) {
               String var7 = this.firstNonBlank(new String[]{var6[0]}).trim();
               if (!var7.isBlank() && this.tryParseRuntimeFlexibleDouble(var7) == null) {
                  var3 = var7;
                  var4 = var2.substring(var2.indexOf(var7) + var7.length()).trim();
               }
            }
         }

         double[] var8 = this.parseRuntimeTeleportCoordinates(var4);
         return var8 != null && var8.length >= 3 ? new GuiServiceEditorSupportA.RuntimeTeleportSpec(var3, var8[0], var8[1], var8[2]) : null;
      }
   }

   protected double[] parseRuntimeTeleportCoordinates(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim();
      if (var2.isBlank()) {
         return null;
      } else {
         Matcher var3 = RUNTIME_TELEPORT_X_PATTERN.matcher(var2);
         Matcher var4 = RUNTIME_TELEPORT_Y_PATTERN.matcher(var2);
         Matcher var5 = RUNTIME_TELEPORT_Z_PATTERN.matcher(var2);
         if (var3.find() && var4.find() && var5.find()) {
            Double var6 = this.tryParseRuntimeFlexibleDouble(var3.group(1));
            Double var7 = this.tryParseRuntimeFlexibleDouble(var4.group(1));
            Double var8 = this.tryParseRuntimeFlexibleDouble(var5.group(1));
            if (var6 != null && var7 != null && var8 != null) {
               return new double[]{var6, var7, var8};
            }
         }

         ArrayList var9 = new ArrayList(3);
         Matcher var10 = RUNTIME_GENERIC_NUMBER_PATTERN.matcher(var2);

         while (var10.find()) {
            Double var11 = this.tryParseRuntimeFlexibleDouble(var10.group());
            if (var11 != null) {
               var9.add(var11);
               if (var9.size() >= 3) {
                  break;
               }
            }
         }

         return var9.size() < 3 ? null : new double[]{(Double)var9.get(0), (Double)var9.get(1), (Double)var9.get(2)};
      }
   }

   protected Double tryParseRuntimeFlexibleDouble(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim();
      if (var2.isBlank()) {
         return null;
      } else {
         String var3 = var2.replace(',', '.');
         if (var3.isBlank()) {
            return null;
         } else {
            try {
               return Double.parseDouble(var3);
            } catch (NumberFormatException var5) {
               return null;
            }
         }
      }
   }

   protected boolean executeRuntimeRedirectAction(Player var1, String var2) {
      if (var1 == null) {
         return false;
      } else {
         String var3 = this.normalizePageKey(var2, "");
         return var3.isBlank() ? false : this.openGui(var1, var3, false, true);
      }
   }

   protected void executeRuntimeCloseAction(Player var1) {
      if (var1 != null) {
         if (this.plugin != null) {
            this.plugin.closeUiForPlayer(var1);
         } else {
            this.cameraService.forceStop(var1);
            this.closeGui(var1);
            this.hudService.clearHuds(var1);
         }
      }
   }

   protected long parseRuntimeActionDelayTicks(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim().toLowerCase(Locale.ROOT);
      if (var2.isBlank()) {
         var2 = "1000ms";
      }

      try {
         if (var2.endsWith("ticks")) {
            double var10 = Double.parseDouble(var2.substring(0, var2.length() - 5).trim());
            return Math.max(0L, Math.round(var10));
         } else if (var2.endsWith("tick")) {
            double var9 = Double.parseDouble(var2.substring(0, var2.length() - 4).trim());
            return Math.max(0L, Math.round(var9));
         } else if (var2.endsWith("t")) {
            double var8 = Double.parseDouble(var2.substring(0, var2.length() - 1).trim());
            return Math.max(0L, Math.round(var8));
         } else if (var2.endsWith("ms")) {
            double var7 = Double.parseDouble(var2.substring(0, var2.length() - 2).trim());
            return Math.max(0L, Math.round(var7 / 50.0));
         } else if (var2.endsWith("s")) {
            double var6 = Double.parseDouble(var2.substring(0, var2.length() - 1).trim());
            return Math.max(0L, Math.round(var6 * 1000.0 / 50.0));
         } else {
            double var3 = Double.parseDouble(var2);
            return Math.max(0L, Math.round(var3 / 50.0));
         }
      } catch (NumberFormatException var5) {
         return Math.max(0L, Math.round(20.0));
      }
   }

   protected int resolveRuntimeOpenAnimationDelayTicks(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var3 = this.resolveRawTargetByTargetId(var1, var2);
         if (var3 != null && !var3.isEmpty()) {
            Object var4 = this.readMapPathValue(var3, "editor_animation.delay");
            return this.parseRuntimeOpenAnimationDelayTicks(var4);
         } else {
            return 0;
         }
      } else {
         return 0;
      }
   }

   protected int parseRuntimeOpenAnimationDelayTicks(Object var1) {
      if (var1 == null) {
         return 0;
      } else if (var1 instanceof Number var6) {
         double var3 = var6.doubleValue();
         return !Double.isFinite(var3) ? 0 : Math.max(0, (int)Math.round(var3));
      } else {
         String var2 = this.firstNonBlank(new String[]{var1.toString()}).trim().toLowerCase(Locale.ROOT);
         if (var2.isBlank()) {
            return 0;
         } else {
            try {
               if (var2.endsWith("ticks")) {
                  return Math.max(0, (int)Math.round(Double.parseDouble(var2.substring(0, var2.length() - 5).trim())));
               } else if (var2.endsWith("tick")) {
                  return Math.max(0, (int)Math.round(Double.parseDouble(var2.substring(0, var2.length() - 4).trim())));
               } else if (var2.endsWith("t")) {
                  return Math.max(0, (int)Math.round(Double.parseDouble(var2.substring(0, var2.length() - 1).trim())));
               } else if (var2.endsWith("ms")) {
                  return Math.max(0, (int)Math.round(Double.parseDouble(var2.substring(0, var2.length() - 2).trim()) / 50.0));
               } else {
                  return var2.endsWith("s")
                     ? Math.max(0, (int)Math.round(Double.parseDouble(var2.substring(0, var2.length() - 1).trim()) * 20.0))
                     : Math.max(0, (int)Math.round(Double.parseDouble(var2)));
               }
            } catch (NumberFormatException var5) {
               return 0;
            }
         }
      }
   }

   protected int resolveMaxPageElements() {
      return this.plugin == null ? 500 : Math.max(1, this.plugin.getConfig().getInt("editor.max-page-elements", 500));
   }

   protected void applyRuntimeOpenInitialZeroTickPose(EditorSession var1) {
      if (var1 != null && !var1.editMode && var1.rawBlocks != null && !var1.rawBlocks.isEmpty()) {
         LinkedHashSet var2 = this.resolveAnimationTimelinePreviewTargetIds(var1, "");
         if (!var2.isEmpty()) {
            boolean var3 = false;

            for (Object var5_raw : var2) {
               String var5 = var5_raw != null ? var5_raw.toString() : null;
               String var6 = this.firstNonBlank(new String[]{var5});
               if (!var6.isBlank() && this.resolveRuntimeOpenAnimationDelayTicks(var1, var6) <= 0) {
                  Map var7 = this.resolveRawTargetByTargetId(var1, var6);
                  if (var7 != null && !var7.isEmpty()) {
                     boolean var8 = this.isAnimationTimelineImageGroupRoot(var1, var6);
                     double var9 = this.readMapPathDouble(var7, "position.x", this.readMapPathDouble(var7, "x", 0.0));
                     double var11 = this.readMapPathDouble(var7, "position.y", this.readMapPathDouble(var7, "y", 0.0));
                     boolean var13 = false;
                     boolean var14 = false;
                     Map var15 = this.readAnimationTimelineRowMap(var7, "position");
                     Object var16 = this.readAnimationTimelineTickValue(var15, 0);
                     if (var16 instanceof Map) {
                        Map var17 = (Map)var16;
                        double var19 = this.parseDouble(var17.get("x"), Double.NaN);
                        double var21 = this.parseDouble(var17.get("y"), Double.NaN);
                        double var23 = this.parseDouble(var17.get("addx"), this.parseDouble(var17.get("addX"), Double.NaN));
                        double var25 = this.parseDouble(var17.get("addy"), this.parseDouble(var17.get("addY"), Double.NaN));
                        if (!Double.isFinite(var19) && Double.isFinite(var23)) {
                           var19 = var9 + var23;
                        }

                        if (!Double.isFinite(var21) && Double.isFinite(var25)) {
                           var21 = var11 + var25;
                        }

                        if (Double.isFinite(var19)) {
                           var9 = var19;
                           var3 = true;
                           var13 = true;
                        }

                        if (Double.isFinite(var21)) {
                           var11 = var21;
                           var3 = true;
                           var14 = true;
                        }
                     }

                     Map var33 = this.readAnimationTimelineRowMap(var7, "scale");
                     Object var18 = this.readAnimationTimelineTickValue(var33, 0);
                     if (var18 instanceof Map) {
                        Map var34 = (Map)var18;
                        double var36 = this.parseDouble(var34.get("width"), Double.NaN);
                        double var38 = this.parseDouble(var34.get("height"), Double.NaN);
                        if (Double.isFinite(var36)) {
                           this.setNested(var7, "size", "width", var36);
                           var3 = true;
                        }

                        if (Double.isFinite(var38)) {
                           this.setNested(var7, "size", "height", var38);
                           var3 = true;
                        }

                        double var40 = this.parseDouble(var34.get("x"), Double.NaN);
                        double var27 = this.parseDouble(var34.get("y"), Double.NaN);
                        double var29 = this.parseDouble(var34.get("offsetX"), Double.NaN);
                        double var31 = this.parseDouble(var34.get("offsetY"), Double.NaN);
                        if (var8 && var13) {
                           var40 = Double.isFinite(var29) ? var9 + var29 : Double.NaN;
                        } else if (!Double.isFinite(var40) && Double.isFinite(var29)) {
                           var40 = var9 + var29;
                        }

                        if (var8 && var14) {
                           var27 = Double.isFinite(var31) ? var11 + var31 : Double.NaN;
                        } else if (!Double.isFinite(var27) && Double.isFinite(var31)) {
                           var27 = var11 + var31;
                        }

                        if (Double.isFinite(var40)) {
                           var9 = var40;
                           var3 = true;
                        }

                        if (Double.isFinite(var27)) {
                           var11 = var27;
                           var3 = true;
                        }
                     }

                     Map var35 = this.readAnimationTimelineRowMap(var7, "opacity");
                     Object var20 = this.readAnimationTimelineTickValue(var35, 0);
                     double var37 = (double)this.readTargetOpacity(var1, var6);
                     double var39 = this.resolveRuntimeAnimationOpacityValue(var20, var37);
                     if (Double.isFinite(var39)) {
                        var3 |= this.setTargetOpacity(var1, var6, this.clampAnimationTimelineOpacityRaw(var39));
                     }

                     this.setNested(var7, "position", "x", var9);
                     this.setNested(var7, "position", "y", var11);
                  }
               }
            }

            if (var3) {
               var1.renderBlocks = this.resolveRenderableBlocks(var1.rawBlocks, var1.components);
            }
         }
      }
   }

   protected void startEditorShellOpenAnimations(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         if (var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
            LinkedHashMap var3 = this.collectEditorShellOpenAnimationTargets(var2);
            if (!var3.isEmpty()) {
               LinkedHashMap var4 = new LinkedHashMap();
               LinkedHashMap var5 = new LinkedHashMap();

               for (Map.Entry<?, ?> var7 : ((Map<?, ?>)var3).entrySet()) {
                  String var8 = this.firstNonBlank(new String[]{(String)var7.getKey()});
                  if (!var8.isBlank() && var7.getValue() != null && !((Map)var7.getValue()).isEmpty()) {
                     EditorRect var9 = this.findShellBlockRect(var2, var8);
                     if (var9 != null) {
                        var4.put(var8, (Map)var7.getValue());
                        var5.put(var8, new EditorRect(var9.x, var9.y, var9.width, var9.height));
                     }
                  }
               }

               if (!var4.isEmpty() && !var5.isEmpty()) {
                  LinkedHashMap var13 = this.collectEditorShellOpenAnimationFollowerRects(var2, var4.keySet());
                  int var14 = this.resolveEditorShellOpenAnimationMaxTick(var4);
                  if (var14 >= 0) {
                     this.applyEditorShellOpenAnimationTick(var1, var2, var4, var5, var13, 0.0);
                     if (var14 > 0) {
                        LinkedHashMap var15 = new LinkedHashMap(var4);
                        LinkedHashMap var10 = new LinkedHashMap(var5);
                        LinkedHashMap var11 = new LinkedHashMap(var13);
                        int[] var12 = new int[]{1};
                        PlatformCompat.runEntityTimer(this.plugin, var1, 1L, 1L, var8x -> {
                           if (!var1.isOnline()) {
                              var8x.cancel();
                           } else {
                              EditorSession var9x = this.editorSessions.get(var1.getUniqueId());
                              if (var9x != null && var9x == var2 && var9x.editMode && var9x.previewMode) {
                                 if (var12[0] > var14) {
                                    var8x.cancel();
                                 } else {
                                    this.applyEditorShellOpenAnimationTick(var1, var9x, var15, var10, var11, (double)var12[0]);
                                    var12[0]++;
                                 }
                              } else {
                                 var8x.cancel();
                              }
                           }
                        });
                     }
                  }
               }
            }
         }
      }
   }

   protected LinkedHashMap<String, Map<String, Object>> collectEditorShellOpenAnimationTargets(EditorSession var1) {
      LinkedHashMap var2 = new LinkedHashMap();
      if (var1 != null && var1.shellBlocks != null && !var1.shellBlocks.isEmpty()) {
         this.ensureShellBlockCache(var1);
         if (var1.shellSectionCache.isEmpty()) {
            return var2;
         } else {
            for (Map.Entry<?, ?> var4 : ((Map<?, ?>)var1.shellSectionCache).entrySet()) {
               String var5 = this.firstNonBlank(new String[]{(String)var4.getKey()});
               ConfigurationSection var6 = (ConfigurationSection)var4.getValue();
               if (!var5.isBlank() && var6 != null) {
                  Map var7 = this.toDeepStringObjectMap(var6);
                  if (var7 != null && !var7.isEmpty()) {
                     Map var8 = this.readAnimationTimelineRowMap(var7, "position");
                     Map var9 = this.readAnimationTimelineRowMap(var7, "scale");
                     boolean var10 = var8 != null && !var8.isEmpty();
                     boolean var11 = var9 != null && !var9.isEmpty();
                     if (var10 || var11) {
                        var2.put(var5, var7);
                     }
                  }
               }
            }

            return var2;
         }
      } else {
         return var2;
      }
   }

   protected int resolveEditorShellOpenAnimationMaxTick(Map<String, Map<String, Object>> var1) {
      if (var1 != null && !var1.isEmpty()) {
         int var2 = -1;

         for (Map var4 : var1.values()) {
            if (var4 != null && !var4.isEmpty()) {
               int var5 = this.resolveEditorShellOpenAnimationDelayTicks(var4);
               Map var6 = this.readAnimationTimelineRowMap(var4, "position");
               Map var7 = this.readAnimationTimelineRowMap(var4, "scale");

               for (int var9 : (List<Integer>)(List<?>)this.readAnimationTimelineTicks(var6)) {
                  var2 = Math.max(var2, var5 + this.clampAnimationTimelineTick(var9));
               }

               for (int var11 : (List<Integer>)(List<?>)this.readAnimationTimelineTicks(var7)) {
                  var2 = Math.max(var2, var5 + this.clampAnimationTimelineTick(var11));
               }
            }
         }

         return var2;
      } else {
         return -1;
      }
   }

   protected int resolveEditorShellOpenAnimationDelayTicks(Map<String, Object> var1) {
      Object var2 = this.readMapPathValue(var1, "editor_animation.delay");
      return this.parseRuntimeOpenAnimationDelayTicks(var2);
   }

   protected LinkedHashMap<String, LinkedHashMap<String, EditorRect>> collectEditorShellOpenAnimationFollowerRects(EditorSession var1, Set<String> var2) {
      LinkedHashMap var3 = new LinkedHashMap();
      if (var1 != null && var2 != null && !var2.isEmpty()) {
         LinkedHashSet var4 = new LinkedHashSet();

         for (Object var6_raw : var2) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            String var7 = this.firstNonBlank(new String[]{var6});
            if (!var7.isBlank()) {
               var4.add(var7);
            }
         }

         if (var4.isEmpty()) {
            return var3;
         } else {
            for (Object var14_raw : var4) {
               String var14 = var14_raw != null ? var14_raw.toString() : null;
               LinkedHashSet var15 = this.resolveEditorShellAnimationMemberIds(var1, var14);
               if (!var15.isEmpty()) {
                  LinkedHashMap var8 = new LinkedHashMap();

                  for (Object var10_raw : var15) {
                     String var10 = var10_raw != null ? var10_raw.toString() : null;
                     String var11 = this.firstNonBlank(new String[]{var10});
                     if (!var11.isBlank() && !var11.equals(var14) && !var4.contains(var11)) {
                        EditorRect var12 = this.findShellBlockRect(var1, var11);
                        if (var12 != null) {
                           var8.put(var11, new EditorRect(var12.x, var12.y, var12.width, var12.height));
                        }
                     }
                  }

                  if (!var8.isEmpty()) {
                     var3.put(var14, var8);
                  }
               }
            }

            return var3;
         }
      } else {
         return var3;
      }
   }

   protected LinkedHashSet<String> resolveEditorShellAnimationMemberIds(EditorSession var1, String var2) {
      LinkedHashSet var3 = new LinkedHashSet();
      if (var1 != null && var1.shellBlocks != null && !var1.shellBlocks.isEmpty()) {
         String var4 = this.firstNonBlank(new String[]{var2});
         if (var4.isBlank()) {
            return var3;
         } else {
            ConfigurationSection var5 = this.findShellBlockSection(var1, var4);
            if (var5 == null) {
               var3.add(var4);
               return var3;
            } else {
               String var6 = this.firstNonBlank(new String[]{var5.getString("__editor_target_path")});
               if (var6.isBlank()) {
                  var3.add(var4);
                  return var3;
               } else {
                  HashMap var7 = new HashMap();
                  int var8 = 0;

                  for (Map var10 : var1.shellBlocks) {
                     var8++;
                     ConfigurationSection var11 = this.mapToSection(var10);
                     if (var11 != null) {
                        String var12 = this.firstNonBlank(new String[]{var11.getString("__editor_target_path")});
                        if (this.belongsToSidebarPanel(var6, var12)) {
                           String var13 = this.resolveElementId(var11, var8, var7);
                           String var14 = this.firstNonBlank(new String[]{var13});
                           if (!var14.isBlank()) {
                              var3.add(var14);
                           }
                        }
                     }
                  }

                  if (var3.isEmpty() || !var3.contains(var4)) {
                     var3.add(var4);
                  }

                  return var3;
               }
            }
         }
      } else {
         return var3;
      }
   }

   protected void applyEditorShellOpenAnimationTick(
      Player var1,
      EditorSession var2,
      Map<String, Map<String, Object>> var3,
      Map<String, EditorRect> var4,
      Map<String, LinkedHashMap<String, EditorRect>> var5,
      double var6
   ) {
      if (var1 != null && var2 != null && var3 != null && !var3.isEmpty() && var4 != null && !var4.isEmpty()) {
         for (Map.Entry<?, ?> var9 : ((Map<?, ?>)var3).entrySet()) {
            String var10 = this.firstNonBlank(new String[]{(String)var9.getKey()});
            if (!var10.isBlank()) {
               Map var11 = (Map)var9.getValue();
               EditorRect var12 = (EditorRect)var4.get(var10);
               if (var11 != null && !var11.isEmpty() && var12 != null) {
                  int var13 = this.resolveEditorShellOpenAnimationDelayTicks(var11);
                  double var14 = var6 - (double)var13;
                  if (!(var14 < 0.0)) {
                     double[] var16 = this.resolveEditorShellOpenAnimationState(var11, var12, var14);
                     if (var16 != null && var16.length >= 4) {
                        this.moveShellElement(var1, var2, var10, var16[0], var16[1], var16[2], var16[3]);
                        if (var5 != null && !var5.isEmpty()) {
                           Map var17 = (Map)var5.get(var10);
                           if (var17 != null && !var17.isEmpty()) {
                              double var18 = var16[0] - var12.x;
                              double var20 = var16[1] - var12.y;
                              if (Double.isFinite(var18) && Double.isFinite(var20)) {
                                 for (Map.Entry<?, ?> var23 : ((Map<?, ?>)var17).entrySet()) {
                                    String var24 = this.firstNonBlank(new String[]{(String)var23.getKey()});
                                    EditorRect var25 = (EditorRect)var23.getValue();
                                    if (!var24.isBlank() && var25 != null) {
                                       this.moveShellElement(var1, var2, var24, var25.x + var18, var25.y + var20, var25.width, var25.height);
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

   protected double[] resolveEditorShellOpenAnimationState(Map<String, Object> var1, EditorRect var2, double var3) {
      if (var1 != null && !var1.isEmpty() && var2 != null) {
         TreeMap var5 = this.readAnimationTimelinePositionPoints(var1, var2.x, var2.y);
         TreeMap var6 = this.readAnimationTimelineScalePoints(var1, var2.x, var2.y, var2.width, var2.height);
         if (var5.isEmpty() && var6.isEmpty()) {
            return null;
         } else {
            Map var7 = this.readAnimationTimelineRowMap(var1, "position");
            Map var8 = this.readAnimationTimelineRowMap(var1, "scale");
            Map var9 = this.readAnimationTimelineInterpolationModes(var7);
            Map var10 = this.readAnimationTimelineInterpolationModes(var8);
            double var11 = var2.x;
            double var13 = var2.y;
            if (!var5.isEmpty()) {
               double[] var15 = this.interpolateAnimationTimelineVector2(var5, var3, var2.x, var2.y, var9);
               var11 = var15[0];
               var13 = var15[1];
            }

            double var26 = Math.max(1.0, var2.width);
            double var17 = Math.max(1.0, var2.height);
            if (!var6.isEmpty()) {
               TreeMap var19 = new TreeMap();
               TreeMap var20 = new TreeMap();
               TreeMap var21 = new TreeMap();

               for (Map.Entry<?, ?> var23 : ((Map<?, ?>)var6).entrySet()) {
                  if (var23 != null && var23.getKey() != null && var23.getValue() != null && ((double[])var23.getValue()).length >= 4) {
                     int var24 = this.clampAnimationTimelineTick((Integer)var23.getKey());
                     double[] var25 = (double[])var23.getValue();
                     var19.put(var24, new double[]{var25[0], var25[1]});
                     var20.put(var24, var25[2]);
                     var21.put(var24, var25[3]);
                  }
               }

               var26 = Math.max(1.0, this.interpolateAnimationTimelineChannel(var20, var3, Math.max(1.0, var2.width), var10));
               var17 = Math.max(1.0, this.interpolateAnimationTimelineChannel(var21, var3, Math.max(1.0, var2.height), var10));
               if (!var19.isEmpty()) {
                  double[] var27 = this.interpolateAnimationTimelineVector2(var19, var3, var11, var13, var10);
                  var11 = var27[0];
                  var13 = var27[1];
               }
            }

            return new double[]{var11, var13, var26, var17};
         }
      } else {
         return null;
      }
   }

   protected double resolveRuntimeAnimationOpacityValue(Object var1, double var2) {
      double var4 = (double)this.clampAnimationTimelineOpacityRaw(var2);
      if (var1 == null) {
         return var4;
      } else if (var1 instanceof Number var11) {
         return (double)this.clampAnimationTimelineOpacityRaw(var11.doubleValue());
      } else {
         Map var6 = this.toStringObjectMap(var1);
         if (var6 != null && !var6.isEmpty()) {
            double var7 = this.readMapPathDouble(var6, "value", this.readMapPathDouble(var6, "opacity", Double.NaN));
            if (Double.isFinite(var7)) {
               return (double)this.clampAnimationTimelineOpacityRaw(var7);
            } else {
               double var9 = this.parseDouble(var6.get("add"), this.parseDouble(var6.get("addOpacity"), this.parseDouble(var6.get("addopacity"), Double.NaN)));
               return Double.isFinite(var9) ? (double)this.clampAnimationTimelineOpacityRaw(var4 + var9) : var4;
            }
         } else {
            return var4;
         }
      }
   }

   protected List<Integer> readAnimationTimelineTicks(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         TreeMap<Integer, Boolean> var2 = new TreeMap<>();

         for (String var4 : var1.keySet()) {
            double var5 = this.parseDouble(var4, Double.NaN);
            if (Double.isFinite(var5)) {
               var2.put(this.clampAnimationTimelineTick((int)Math.round(var5)), Boolean.TRUE);
            }
         }

         return (List<Integer>)(var2.isEmpty() ? Collections.emptyList() : new ArrayList<>(var2.keySet()));
      } else {
         return Collections.emptyList();
      }
   }

   protected Map<Integer, String> readAnimationTimelineInterpolationModes(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         TreeMap<Integer, String> var2 = new TreeMap<>();

         for (Map.Entry<?, ?> var4 : ((Map<?, ?>)var1).entrySet()) {
            if (var4 != null && var4.getKey() != null && var4.getValue() != null) {
               double var5 = this.parseDouble(var4.getKey(), Double.NaN);
               if (Double.isFinite(var5)) {
                  int var7 = this.clampAnimationTimelineTick((int)Math.round(var5));
                  Map var8 = this.toStringObjectMap(var4.getValue());
                  if (var8 != null && !var8.isEmpty()) {
                     String var9 = this.normalizeAnimationTimelineInterpolationMode(this.stringValue(var8.get("interpolation")));
                     if (!"linear".equals(var9)) {
                        var2.put(var7, var9);
                     }
                  }
               }
            }
         }

         return (Map<Integer, String>)(var2.isEmpty() ? Collections.emptyMap() : var2);
      } else {
         return Collections.emptyMap();
      }
   }

   protected TreeMap<Integer, double[]> readAnimationTimelinePositionPoints(Map<String, Object> var1, double var2, double var4) {
      TreeMap var6 = new TreeMap();
      if (var1 != null && !var1.isEmpty()) {
         Map var7 = this.readAnimationTimelineRowMap(var1, "position");
         if (var7 != null && !var7.isEmpty()) {
            TreeMap var8 = new TreeMap();

            for (Map.Entry<?, ?> var10 : ((Map<?, ?>)var7).entrySet()) {
               if (var10 != null && var10.getValue() != null) {
                  double var11 = this.parseDouble(var10.getKey(), Double.NaN);
                  if (Double.isFinite(var11)) {
                     Map var13 = this.toStringObjectMap(var10.getValue());
                     if (var13 != null && !var13.isEmpty()) {
                        int var14 = this.clampAnimationTimelineTick((int)Math.round(var11));
                        var8.put(var14, var13);
                     }
                  }
               }
            }

            if (var8.isEmpty()) {
               return var6;
            } else {
               for (Map.Entry<?, ?> var27 : ((Map<?, ?>)var8).entrySet()) {
                  int var28 = this.clampAnimationTimelineTick((Integer)var27.getKey());
                  Map var12 = (Map)var27.getValue();
                  if (var12 != null && !var12.isEmpty()) {
                     double[] var29 = this.interpolateAnimationTimelineVector2(var6, (double)var28, var2, var4);
                     double var30 = this.parseDouble(var12.get("x"), Double.NaN);
                     double var16 = this.parseDouble(var12.get("y"), Double.NaN);
                     double var18 = this.parseDouble(var12.get("addx"), this.parseDouble(var12.get("addX"), Double.NaN));
                     double var20 = this.parseDouble(var12.get("addy"), this.parseDouble(var12.get("addY"), Double.NaN));
                     double var22 = var29[0];
                     if (Double.isFinite(var30)) {
                        var22 = var30;
                     } else if (Double.isFinite(var18)) {
                        var22 = var29[0] + var18;
                     }

                     double var24 = var29[1];
                     if (Double.isFinite(var16)) {
                        var24 = var16;
                     } else if (Double.isFinite(var20)) {
                        var24 = var29[1] + var20;
                     }

                     var6.put(var28, new double[]{var22, var24});
                  }
               }

               return var6;
            }
         } else {
            return var6;
         }
      } else {
         return var6;
      }
   }

   protected TreeMap<Integer, double[]> readAnimationTimelineScalePoints(Map<String, Object> var1, double var2, double var4, double var6, double var8) {
      TreeMap var10 = new TreeMap();
      if (var1 != null && !var1.isEmpty()) {
         Map var11 = this.readAnimationTimelineRowMap(var1, "scale");
         if (var11 != null && !var11.isEmpty()) {
            TreeMap var12 = this.readAnimationTimelinePositionPoints(var1, var2, var4);
            Map var13 = this.readAnimationTimelineRowMap(var1, "position");
            Map var14 = this.readAnimationTimelineInterpolationModes(var13);

            for (Map.Entry<?, ?> var16 : ((Map<?, ?>)var11).entrySet()) {
               if (var16 != null && var16.getValue() != null) {
                  double var17 = this.parseDouble(var16.getKey(), Double.NaN);
                  if (Double.isFinite(var17)) {
                     int var19 = this.clampAnimationTimelineTick((int)Math.round(var17));
                     Map var20 = this.toStringObjectMap(var16.getValue());
                     if (var20 != null && !var20.isEmpty()) {
                        double var21 = this.parseDouble(var20.get("width"), Double.NaN);
                        double var23 = this.parseDouble(var20.get("height"), Double.NaN);
                        if (Double.isFinite(var21) && Double.isFinite(var23)) {
                           double var25 = this.parseDouble(var20.get("offsetX"), Double.NaN);
                           double var27 = this.parseDouble(var20.get("offsetY"), Double.NaN);
                           if (!Double.isFinite(var25) || !Double.isFinite(var27)) {
                              double var29 = this.parseDouble(var20.get("x"), Double.NaN);
                              double var31 = this.parseDouble(var20.get("y"), Double.NaN);
                              if (Double.isFinite(var29) && Double.isFinite(var31)) {
                                 double[] var33 = this.interpolateAnimationTimelineVector2(var12, (double)var19, var2, var4, var14);
                                 var25 = var29 - var33[0];
                                 var27 = var31 - var33[1];
                              }
                           }

                           if (Double.isFinite(var25) && Double.isFinite(var27)) {
                              double[] var34 = this.interpolateAnimationTimelineVector2(var12, (double)var19, var2, var4, var14);
                              double var30 = var34[0];
                              double var32 = var34[1];
                              var10.put(var19, new double[]{var30 + var25, var32 + var27, var21, var23});
                           }
                        }
                     }
                  }
               }
            }

            return var10;
         } else {
            return var10;
         }
      } else {
         return var10;
      }
   }

   protected Map<String, Object> toDeepStringObjectMap(ConfigurationSection var1) {
      if (var1 == null) {
         return null;
      } else {
         LinkedHashMap var2 = new LinkedHashMap();

         for (String var4 : var1.getKeys(false)) {
            if (var4 != null && !var4.isBlank()) {
               var2.put(var4, this.toDeepStringObjectValue(var1.get(var4)));
            }
         }

         return var2;
      }
   }

   protected Object toDeepStringObjectValue(Object var1) {
      if (var1 instanceof ConfigurationSection var7) {
         return this.toDeepStringObjectMap(var7);
      } else if (var1 instanceof Map var6) {
         LinkedHashMap var8 = new LinkedHashMap();

         for (Map.Entry<?, ?> var10 : ((Map<?, ?>)var6).entrySet()) {
            if (var10 != null && var10.getKey() != null) {
               var8.put(var10.getKey().toString(), this.toDeepStringObjectValue(var10.getValue()));
            }
         }

         return var8;
      } else if (!(var1 instanceof List var2)) {
         return var1;
      } else {
         ArrayList var3 = new ArrayList(var2.size());

         for (Object var5 : var2) {
            var3.add(this.toDeepStringObjectValue(var5));
         }

         return var3;
      }
   }

   protected static final class EditorRawTargetIdentity {
      final String targetId;
      final String targetPath;
      final String logicalId;
      final String displayName;

      EditorRawTargetIdentity(String var1, String var2, String var3, String var4) {
         this.targetId = var1 == null ? "" : var1.trim();
         this.targetPath = var2 == null ? "" : var2.trim();
         this.logicalId = var3 == null ? "" : var3.trim();
         this.displayName = var4 == null ? "" : var4.trim();
      }
   }

   protected static final class ParsedPivotInput {
      private final double valueX;
      private final double valueY;
      private final boolean normalized;

      private ParsedPivotInput(double var1, double var3, boolean var5) {
         this.valueX = var1;
         this.valueY = var3;
         this.normalized = var5;
      }
   }

   protected static final class PivotTargetData {
      private final double width;
      private final double height;
      private final double rawX;
      private final double rawY;
      private final boolean normalized;
      private final double ratioX;
      private final double ratioY;
      private final double offsetX;
      private final double offsetY;
      private final double absoluteX;
      private final double absoluteY;

      private PivotTargetData(
         double var1, double var3, double var5, double var7, boolean var9, double var10, double var12, double var14, double var16, double var18, double var20
      ) {
         this.width = var1;
         this.height = var3;
         this.rawX = var5;
         this.rawY = var7;
         this.normalized = var9;
         this.ratioX = var10;
         this.ratioY = var12;
         this.offsetX = var14;
         this.offsetY = var16;
         this.absoluteX = var18;
         this.absoluteY = var20;
      }
   }

   protected static record RuntimeSoundSpec(String soundToken, double volume, double pitch) {
   }

   protected static record RuntimeTeleportSpec(String worldName, double x, double y, double z) {
   }
}
