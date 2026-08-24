package dev.xqedii.ultimateUI.service.gui.base;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.gui.parser.GuiTemplateResolver;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.hud.HudPositionCalculator;
import dev.xqedii.ultimateUI.service.hud.HudService;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

public abstract class GuiServiceBaseSupport {
   protected static final String CONTENTS_FOLDER = "contents";
   protected static final String PAGES_FOLDER = "contents/pages";
   protected static final String COMPONENTS_FOLDER = "contents/components";
   protected static final String EFFECTS_FOLDER = "contents/effects";
   protected static final String INTERNAL_EDITOR_DEFAULTS_FOLDER = "editor-defaults";
   protected static final String INTERNAL_PAGES_FOLDER = "editor-defaults/pages";
   protected static final String INTERNAL_COMPONENTS_FOLDER = "editor-defaults/components";
   protected static final String[] DEFAULT_EDITOR_PAGE_FILES = new String[]{"editor.yml", "editor_menu.yml"};
   protected static final String[] DEFAULT_EDITOR_COMPONENT_FILES = new String[]{
      "editor_action.yml",
      "editor_activepage.yml",
      "editor_animation_element.yml",
      "editor_dropdown_value.yml",
      "editor_info_popup.yml",
      "editor_layer.yml",
      "editor_manage_file_popup.yml",
      "editor_navbar_item.yml",
      "editor_popup_file_element.yml",
      "editor_popup_image_element.yml",
      "editor_preferences_popup.yml",
      "editor_save_popup.yml",
      "editor_welcome_popup.yml",
      "editor_tool.yml",
      "editor_value.yml"
   };
   protected static final String EDITOR_PAGE_NAME = "editor";
   protected static final String EDITOR_PREVIEW_ID = "preview";
   protected static final String EDITOR_SHELL_PREFIX = "editor_shell_";
   protected static final String PREVIEW_CONTENT_PREFIX = "preview_content_";
   protected static final MiniMessage MM = MiniMessage.miniMessage();
   protected static final Pattern HEX_PREFIX = Pattern.compile("^<#[0-9a-fA-F]{6}>");
   protected static final String[] EDITOR_OVERLAY_IDS = new String[]{
      "editor_top",
      "editor_bottom",
      "editor_left",
      "editor_right",
      "editor_handle_tl",
      "editor_handle_tr",
      "editor_handle_bl",
      "editor_handle_br",
      "editor_handle_nw",
      "editor_handle_n",
      "editor_handle_ne",
      "editor_handle_w",
      "editor_handle_e",
      "editor_handle_sw",
      "editor_handle_s",
      "editor_handle_se",
      "editor_handle_move_tl",
      "editor_handle_move_tr",
      "editor_handle_move_bl",
      "editor_handle_move_br",
      "editor_marquee_top",
      "editor_marquee_bottom",
      "editor_marquee_left",
      "editor_marquee_right",
      "block_align",
      "block_align_separator",
      "align_lr_left",
      "align_lr_center",
      "align_lr_right",
      "align_tb_top",
      "align_tb_center",
      "align_tb_bottom"
   };
   protected static final double OUTLINE_THICKNESS = 4.0;
   protected static final double HANDLE_SIZE = 15.0;
   protected static final double HANDLE_HOVER_HALF = 7.5;
   protected static final long LEFT_HOLD_TICK_WINDOW = 2L;
   protected static final double MOVE_SNAP_THRESHOLD = 20.0;
   protected static final long SCALE_TOGGLE_COOLDOWN_TICKS = 3L;
   protected static final double TEXT_INTERACTIVE_HEIGHT_FACTOR = 0.11666666666666667;
   protected static final String HANDLE_COLOR = "<#639eff>";
   protected static final String HANDLE_HOVER_COLOR = "<#9fc6ff>";
   protected static final double MOVE_HANDLE_CELL_SIZE = 6.0;
   protected static final double MOVE_HANDLE_CELL_GAP = 4.0;
   protected static final String MARQUEE_OUTLINE_COLOR = "<#84a0c8>";
   protected static final String ROUNDED_CORE_SUFFIX = "_r_core";
   protected static final String ROUNDED_TOP_SUFFIX = "_r_top";
   protected static final String ROUNDED_BOTTOM_SUFFIX = "_r_bottom";
   protected static final String ROUNDED_LEFT_SUFFIX = "_r_left";
   protected static final String ROUNDED_RIGHT_SUFFIX = "_r_right";
   protected static final String ROUNDED_TL_SUFFIX = "_r_tl";
   protected static final String ROUNDED_TR_SUFFIX = "_r_tr";
   protected static final String ROUNDED_BL_SUFFIX = "_r_bl";
   protected static final String ROUNDED_BR_SUFFIX = "_r_br";
   protected static final String ELEMENT_OUTLINE_SUFFIX = "_outline";
   protected static final double ELEMENT_OUTLINE_LAYER_OFFSET = 0.01;
   protected static final String ROUNDED_ROTATION_META = "xqgui_rounded_rotation_z";
   protected static final String ROUNDED_CORNER_UNICODE = "\ue151";
   protected static final String ROUNDED_CORNER_UNICODE_TL = "\uef60";
   protected static final String ROUNDED_CORNER_UNICODE_TR = "\uef61";
   protected static final String ROUNDED_CORNER_UNICODE_BR = "\uef62";
   protected static final String ROUNDED_CORNER_UNICODE_BL = "\uef63";
   protected static final String DEFAULT_HEX_COLOR = "ffffff";
   protected static final int ROUNDED_ROTATE_INTERPOLATION_TICKS = 1;
   protected static final double ROUNDED_TOP_SHIFT_Y = 1.0;
   protected static final double ROUNDED_FIXED_SIZE = 26.0;
   protected static final boolean DEBUG_ROUNDED_LOGS = false;
   protected static final String PAGE_OUTLINE_COLOR = "<#191919>";
   protected static final double PAGE_OUTLINE_THICKNESS = 1.0;
   protected static final String PAGE_OUTLINE_TOP_ID = "editor_page_outline_top";
   protected static final String PAGE_OUTLINE_BOTTOM_ID = "editor_page_outline_bottom";
   protected static final String PAGE_OUTLINE_LEFT_ID = "editor_page_outline_left";
   protected static final String PAGE_OUTLINE_RIGHT_ID = "editor_page_outline_right";
   protected static final String EDITOR_TOOL_CURSOR_BG_ID = "tool_cursor_bg";
   protected static final String EDITOR_TOOL_CURSOR_ICON_ID = "tool_cursor_icon";
   protected static final String EDITOR_TOOL_SCALE_BG_ID = "tool_scale_bg";
   protected static final String EDITOR_TOOL_SCALE_ICON_ID = "tool_scale_icon";
   protected static final String EDITOR_TOOL_ACTIVE_BG = "484848";
   protected static final String EDITOR_TOOL_INACTIVE_BG = "272727";
   protected static final String EDITOR_TOOL_CURSOR_ACTIVE_ICON = "\ue1a8";
   protected static final String EDITOR_TOOL_CURSOR_INACTIVE_ICON = "\ue19f";
   protected static final String EDITOR_TOOL_SCALE_ACTIVE_ICON = "\ue1aa";
   protected static final String EDITOR_TOOL_SCALE_INACTIVE_ICON = "\ue1a0";
   protected static final double PREVIEW_CONTENT_LAYER_OFFSET = 5.0;
   protected static final double PREVIEW_ZOOM_STEP = 1.12;
   protected static final double PREVIEW_ZOOM_MIN = 0.1;
   protected static final double PREVIEW_ZOOM_MAX = 6.0;
   protected static final double DEFAULT_EDITOR_PREVIEW_ZOOM = 0.9;
   protected static final double EDITOR_CANVAS_WIDTH = 1920.0;
   protected static final double EDITOR_CANVAS_HEIGHT = 1080.0;
   protected static final double EDITOR_SELECTION_OUTLINE_LAYER = 8994.0;
   protected static final double EDITOR_SELECTION_HANDLE_LAYER = 8995.0;
   protected static final double EDITOR_PAGE_OUTLINE_LAYER = 8993.8;
   protected static final double EDITOR_ACTIVE_ELEMENT_LAYER = 8993.0;
   protected static final double EDITOR_CONTENT_BASE_LAYER = 8600.0;
   protected static final double EDITOR_CONTENT_MIN_LAYER = 8000.0;
   protected static final double EDITOR_CONTENT_MAX_LAYER = 8992.0;
   protected static final int EDITOR_CURSOR_INTERPOLATION_DURATION_DEFAULT = 1;
   protected static final int EDITOR_CURSOR_TELEPORT_DURATION_DEFAULT = 1;
   protected static final String EDITOR_SFX_CONFIG_ROOT = "editor.sounds";
   protected static final String EDITOR_SFX_ACTION_OBJECT_SELECTED = "object-selected";
   protected static final String EDITOR_SFX_ACTION_TOOL_SELECTED = "tool-selected";
   protected static final String EDITOR_SFX_ACTION_SAVE = "save";
   protected static final String EDITOR_SFX_ACTION_EDITOR_OPENED = "editor-opened";
   protected static final String EDITOR_SFX_ACTION_DROPDOWN_OPENED = "dropdown-opened";
   protected static final String EDITOR_SFX_ACTION_DROPDOWN_ITEM_CLICKED = "dropdown-item-clicked";
   protected static final String EDITOR_SFX_ACTION_KEYFRAME_SELECTED = "keyframe-selected";
   protected static final String EDITOR_SFX_ACTION_COLOR_PICKED = "color-picked";
   protected static final String EDITOR_SFX_ACTION_BUCKET_PAINTED = "bucket-painted";
   protected static final String EDITOR_SFX_ACTION_COLORS_SWAPPED = "colors-swapped";
   protected static final String EDITOR_SFX_ACTION_LAYER_SELECTED = "layer-selected";
   protected static final String EDITOR_SFX_ACTION_TAB_SWITCHED = "tab-switched";
   protected static final String EDITOR_SFX_ACTION_UNDO = "undo";
   protected static final String EDITOR_SFX_ACTION_REDO = "redo";
   protected static final String EDITOR_SFX_ACTION_HOME = "home";
   protected final UltimateUI plugin;
   protected final HudService hudService;
   protected final CameraService cameraService;
   protected final GuiTemplateResolver templateResolver = new GuiTemplateResolver();
   protected final HudPositionCalculator positionCalculator = new HudPositionCalculator();
   protected final Map<UUID, EditorSession> editorSessions = new HashMap<>();

   protected GuiServiceBaseSupport(UltimateUI var1, HudService var2, CameraService var3) {
      this.plugin = var1;
      this.hudService = var2;
      this.cameraService = var3;
   }

   protected int resolveEditorCursorInterpolationDurationTicks() {
      return 1;
   }

   protected int resolveEditorCursorTeleportDurationTicks() {
      return this.resolveEditorCursorInterpolationDurationTicks();
   }

   protected int resolveEditorHudTransitionTicks(EditorSession var1) {
      return var1 != null && var1.editMode && var1.previewMode ? this.resolveEditorCursorInterpolationDurationTicks() : 0;
   }

   protected void playEditorSfx(Player var1, String var2) {
      if (var1 != null) {
         this.playEditorSfx(var1, this.editorSessions.get(var1.getUniqueId()), var2);
      }
   }

   protected void playEditorSfx(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var3 != null && !var3.isBlank()) {
         if (this.plugin.getConfig().getBoolean("editor.sounds.enabled", true)) {
            if (var2 == null || var2.editorSounds) {
               String var4 = this.plugin.getConfig().getString("editor.sounds.sound", "");
               if (var4 == null || var4.isBlank()) {
                  var4 = this.defaultEditorSfxName(var3);
               }

               if (var4 != null && !var4.isBlank()) {
                  float var5 = (float)Math.max(0.0, this.plugin.getConfig().getDouble("editor.sounds.volume", 1.0));
                  float var6 = 1.0F;

                  try {
                     var1.playSound(var1.getLocation(), var4, SoundCategory.MASTER, var5, var6);
                  } catch (RuntimeException var8) {
                  }
               }
            }
         }
      }
   }

   protected String defaultEditorSfxName(String var1) {
      if (var1 == null) {
         return "";
      } else {
         return switch (var1) {
            case "object-selected" -> "ultimateui.click";
            case "tool-selected" -> "ultimateui.selecttool";
            case "save" -> "ultimateui.extra7";
            case "editor-opened" -> "ultimateui.extra6";
            case "dropdown-opened" -> "ultimateui.extra4";
            case "dropdown-item-clicked" -> "ultimateui.extra5";
            case "keyframe-selected" -> "ultimateui.click";
            case "color-picked" -> "ultimateui.extra6";
            case "bucket-painted" -> "ultimateui.extra2";
            case "colors-swapped" -> "ultimateui.extra4";
            case "layer-selected" -> "ultimateui.click";
            case "tab-switched" -> "ultimateui.click";
            case "undo" -> "ultimateui.extra4";
            case "redo" -> "ultimateui.extra5";
            case "home" -> "ultimateui.extra7";
            default -> "";
         };
      }
   }
}
