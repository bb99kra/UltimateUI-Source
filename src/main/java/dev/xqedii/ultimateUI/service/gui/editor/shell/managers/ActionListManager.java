package dev.xqedii.ultimateUI.service.gui.editor.shell.managers;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.gui.model.EditorSidebarTab;
import dev.xqedii.ultimateUI.service.gui.model.EditorTool;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.util.PlatformCompat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public abstract class ActionListManager extends PropertiesSidebarManager {
   private static final int ACTIONS_VISIBLE_ROW_COUNT = 17;
   private static final double ACTIONS_REORDER_DRAG_THRESHOLD_PX = 5.0;
   private static final double ACTIONS_REORDER_DRAG_VERTICAL_THRESHOLD_PX = 4.0;
   private static final double ACTIONS_REORDER_EDIT_MOVEMENT_TOLERANCE_PX = 2.5;
   private static final long ACTIONS_REORDER_EDIT_MIN_SECOND_CLICK_TICKS = 2L;
   private static final long ACTIONS_REORDER_EDIT_DOUBLE_CLICK_TICKS = 10L;
   private static final long ACTIONS_REORDER_HOLD_GRACE_TICKS = 2L;
   private static final String SIDEBAR_ACTIONS_PANEL_ID = "actions";
   private static final String SIDEBAR_RIGHT_CONTAINER_ID = "right_sidebar";
   private static final String SIDEBAR_ACTIONS_NEW_BUTTON_ID = "actions_new_button";
   private static final String SIDEBAR_ACTIONS_NEW_HITBOX_ID = "actions_new_hitbox";
   private static final String SIDEBAR_ACTIONS_LIST_ID = "actions_list";
   private static final String CURSOR_ACTIONS_DROPDOWN_ID = "cursor_actions_dropdown";
   private static final String CURSOR_ACTIONS_EDIT_DROPDOWN_ID = "cursor_actions_edit_dropdown";
   private static final String ACTIONS_SCROLL_TOP_ID = "actions_scroll_top";
   private static final String ACTIONS_SCROLL_BOTTOM_ID = "actions_scroll_bottom";
   private static final int ACTIONS_SCROLL_EDGE_FLASH_TICKS = 3;
   private static final String ACTIONS_REORDER_GHOST_RUNTIME_PREFIX = "actions_drag_runtime_";
   private static final String ACTIONS_REORDER_GHOST_SLOT_BASE = "action_drag_slot";
   private static final String ACTIONS_RUNTIME_ID_PREFIX = "actions_runtime_";
   private static final String ACTIONS_SLOT_ID_PREFIX = "action_slot_";
   private static final String ACTIONS_ROW_ID_SUFFIX = "_row";
   private static final String ACTIONS_NAME_ID_SUFFIX = "_name";
   private static final String ACTIONS_ICON_ID_SUFFIX = "_opacity";
   private static final double ACTIONS_ROW_START_X_OFFSET = 24.0;
   private static final double ACTIONS_ROW_START_Y_OFFSET = 130.0;
   private static final double ACTIONS_DYNAMIC_GRID_GAP = -51.0;
   private static final double LAYERS_ROW_WIDTH = 289.0;
   private static final double LAYERS_ROW_HEIGHT = 45.0;
   private static final List<String> CURSOR_ACTIONS_DROPDOWN_ITEM_IDS = List.of(
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
   private static final Map<String, String[]> CURSOR_ACTIONS_DROPDOWN_ITEM_ICONS = Map.of(
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
   private static final List<String> CURSOR_ACTIONS_EDIT_DROPDOWN_ITEM_IDS = List.of(
      "dropdown_actions_edit_cut", "dropdown_actions_edit_copy", "dropdown_actions_edit_paste"
   );
   private static final Map<String, String[]> CURSOR_ACTIONS_EDIT_DROPDOWN_ITEM_ICONS = Map.of(
      "dropdown_actions_edit_cut",
      new String[]{"\uef1c", "\uef1d"},
      "dropdown_actions_edit_copy",
      new String[]{"\uee83", "\uee8a"},
      "dropdown_actions_edit_paste",
      new String[]{"\uee84", "\uee8b"}
   );
   private static final String CURSOR_ACTIONS_DROPDOWN_INACTIVE_COLOR = "0f0f0f";
   private static final String CURSOR_ACTIONS_DROPDOWN_ACTIVE_COLOR = "141414";
   private static final String CURSOR_ACTIONS_EDIT_DROPDOWN_INACTIVE_COLOR = "0f0f0f";
   private static final String CURSOR_ACTIONS_EDIT_DROPDOWN_ACTIVE_COLOR = "141414";
   private static final double CURSOR_ACTIONS_DROPDOWN_WALL_MARGIN = 11.0;
   private static final double CURSOR_ACTIONS_EDIT_DROPDOWN_WALL_MARGIN = 11.0;
   private static final String ACTION_TYPE_COMMAND = "command";
   private static final String ACTION_TYPE_CONSOLE = "console";
   private static final String ACTION_TYPE_MESSAGE = "message";
   private static final String ACTION_TYPE_REDIRECT = "redirect";
   private static final String ACTION_TYPE_CLOSE = "close";
   private static final String ACTION_TYPE_ANIMATION = "animation";
   private static final String ACTION_TYPE_DELAY = "delay";
   private static final String ACTION_TYPE_SOUND = "sound";
   private static final String ACTION_TYPE_TELEPORT = "teleport";
   private static final Map<String, String> ACTION_TYPE_ICONS = Map.of(
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
   private static final Map<String, String> ACTION_TYPE_ACTIVE_ICONS = Map.of(
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
   private static final String ACTION_DEFAULT_VALUE = "---";
   private static final String ACTIONS_PENDING_VALUE = "...";
   private static final String ACTION_DEFAULT_CONSOLE_VALUE = "say Hello";
   private static final String ACTION_DEFAULT_CLOSE_VALUE = "Close";
   private static final String ACTION_DEFAULT_DELAY_VALUE = "1000ms";
   private static final String ACTION_DEFAULT_SOUND_VALUE = "ENTITY_PLAYER_LEVELUP 1,2";
   private static final String ACTION_DEFAULT_TELEPORT_VALUE = "0 64 0";

   protected ActionListManager(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   @Override
   protected boolean isActionsSidebarMode(EditorSession var1) {
      return var1 != null && var1.activeTool == EditorTool.COMMANDS;
   }

   @Override
   protected boolean isActionsShellBlockId(String var1) {
      return var1 != null && !var1.isBlank() ? var1.startsWith("actions_runtime_") || var1.startsWith("action_slot_") : false;
   }

   @Override
   protected boolean isCursorActionsCachedPanelBlock(EditorSession var1, ConfigurationSection var2) {
      if (var1 != null && var2 != null) {
         String var3 = this.firstNonBlank(new String[]{var2.getString("__editor_target_path")});
         if (var3.isBlank()) {
            return false;
         } else {
            ConfigurationSection var4 = this.findShellBlockSection(var1, "cursor_actions_dropdown");
            String var5 = this.firstNonBlank(new String[]{var4 == null ? null : var4.getString("__editor_target_path")});
            if (!var5.isBlank() && this.belongsToSidebarPanel(var5, var3)) {
               return true;
            } else {
               ConfigurationSection var6 = this.findShellBlockSection(var1, "cursor_actions_edit_dropdown");
               String var7 = this.firstNonBlank(new String[]{var6 == null ? null : var6.getString("__editor_target_path")});
               return !var7.isBlank() && this.belongsToSidebarPanel(var7, var3);
            }
         }
      } else {
         return false;
      }
   }

   @Override
   protected String actionRuntimeLogicalId(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1});
      return var2.startsWith("actions_runtime_") ? var2 : "actions_runtime_" + var2;
   }

   @Override
   protected String actionSlotBaseId(int var1) {
      return "actions_runtime_action_slot_" + var1;
   }

   @Override
   protected String actionSlotId(int var1) {
      return this.actionSlotBaseId(var1) + "_row";
   }

   @Override
   protected String actionNameId(int var1) {
      return this.actionSlotBaseId(var1) + "_name";
   }

   @Override
   protected String actionIconId(int var1) {
      return this.actionSlotBaseId(var1) + "_opacity";
   }

   @Override
   protected boolean isValidActionsSecondClickEditAttempt(EditorSession var1, double var2, double var4, long var6) {
      if (var1 == null || var6 <= 0L) {
         return false;
      } else if (var1.rightSidebarTab == EditorSidebarTab.PROPERTIES && this.isActionsSidebarMode(var1)) {
         String var8 = this.resolveSelectedActionsTargetId(var1);
         if (var8.isBlank()) {
            return false;
         } else {
            int var9 = var1.actionsRenderedRawIndexes.size();
            int var10 = this.resolveActionSlotByHit(var1, var2, var4, var9);
            if (var10 >= 1 && var10 <= var9) {
               int var11 = var1.actionsRenderedRawIndexes.get(var10 - 1);
               long var12 = var6 - var1.actionsLastClickTick;
               if (var11 < 0 || var11 != var1.actionsSelectedIndex || var11 != var1.actionsLastClickIndex || var12 < 2L || var12 > 10L) {
                  return false;
               } else if (!this.isActionsReorderActive(var1) && !this.hasActionsEditMovementExceeded(var1, var11, var2, var4)) {
                  List var14 = this.resolveTargetActionsRawList(var1, var8, false);
                  if (var14 != null && var11 < var14.size()) {
                     String var15 = this.normalizeActionType(this.stringValue(((Map)var14.get(var11)).get("type")));
                     return this.isEditableActionType(var15);
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   @Override
   protected boolean hasActionsEditMovementExceeded(EditorSession var1, int var2, double var3, double var5) {
      if (var1 != null && var2 >= 0 && var1.actionsReorderArmedIndex == var2) {
         double var7 = Math.hypot(var3 - var1.actionsReorderStartHitX, var5 - var1.actionsReorderStartHitY);
         return var7 >= 2.5;
      } else {
         return false;
      }
   }

   @Override
   protected boolean isActionsReorderDragStartSatisfied(EditorSession var1, double var2, double var4) {
      if (var1 == null) {
         return false;
      } else {
         double var6 = var2 - var1.actionsReorderStartHitX;
         double var8 = var4 - var1.actionsReorderStartHitY;
         double var10 = Math.hypot(var6, var8);
         if (var10 < 5.0) {
            return false;
         } else {
            double var12 = Math.abs(var8);
            double var14 = Math.abs(var6);
            return var12 >= 4.0 && var12 >= var14;
         }
      }
   }

   @Override
   protected void resetActionsEditClickTracker(EditorSession var1) {
      if (var1 != null) {
         var1.actionsLastClickIndex = -1;
         var1.actionsLastClickTick = -2305843009213693952L;
      }
   }

   @Override
   protected boolean handleCursorActionsDropdownAction(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String var4 = this.resolveSelectedActionsTargetId(var2);
         if (var4.isBlank()) {
            this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>Select an element first.</yellow>"));
            return false;
         } else {
            String var5 = this.resolveCursorActionsTypeByDropdownId(var3);
            if (var5.isBlank()) {
               return false;
            } else {
               List var6 = this.resolveTargetActionsRawList(var2, var4, true);
               if (var6 == null) {
                  return false;
               } else {
                  this.recordEditorMutation(var2);
                  var6.add(this.buildDefaultActionMap(var5));
                  var2.actionsSelectedIndex = Math.max(0, var6.size() - 1);
                  var2.pendingActionTargetId = null;
                  var2.pendingActionIndex = -1;
                  this.clearActionsReorderState(var2);
                  this.updateEditorPropertiesSidebar(var1, var2);
                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   @Override
   protected boolean handleCursorActionsEditDropdownAction(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String var4 = this.firstNonBlank(new String[]{var3}).trim().toLowerCase(Locale.ROOT);

         return switch (var4) {
            case "dropdown_actions_edit_cut" -> this.handleActionsEditCut(var1, var2);
            case "dropdown_actions_edit_copy" -> this.handleActionsEditCopy(var2);
            case "dropdown_actions_edit_paste" -> this.handleActionsEditPaste(var1, var2);
            default -> false;
         };
      } else {
         return false;
      }
   }

   @Override
   protected boolean handleActionsEditCopy(EditorSession var1) {
      if (var1 == null) {
         return false;
      } else {
         String var2 = this.resolveSelectedActionsTargetId(var1);
         if (var2.isBlank()) {
            return false;
         } else {
            List var3 = this.resolveTargetActionsRawList(var1, var2, false);
            int var4 = this.resolveSelectedActionRawIndex(var1, var3);
            if (var4 >= 0 && var3 != null && var4 < var3.size()) {
               Map var5 = this.toStringObjectMap(var3.get(var4));
               if (var5 != null && !var5.isEmpty()) {
                  Map var6 = this.deepCopyMap(var5);
                  this.normalizeActionMapForEditor(var6);
                  var1.actionsClipboardAction = var6;
                  return true;
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      }
   }

   @Override
   protected boolean handleActionsEditCut(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         String var3 = this.resolveSelectedActionsTargetId(var2);
         if (var3.isBlank()) {
            return false;
         } else {
            List var4 = this.resolveTargetActionsRawList(var2, var3, false);
            int var5 = this.resolveSelectedActionRawIndex(var2, var4);
            if (var5 >= 0 && var4 != null && var5 < var4.size()) {
               Map var6 = this.toStringObjectMap(var4.get(var5));
               if (var6 != null && !var6.isEmpty()) {
                  Map var7 = this.deepCopyMap(var6);
                  this.normalizeActionMapForEditor(var7);
                  var2.actionsClipboardAction = var7;
                  this.recordEditorMutation(var2);
                  var4.remove(var5);
                  if (var4.isEmpty()) {
                     var2.actionsSelectedIndex = -1;
                  } else {
                     var2.actionsSelectedIndex = Math.max(0, Math.min(var5, var4.size() - 1));
                  }

                  var2.pendingActionTargetId = null;
                  var2.pendingActionIndex = -1;
                  this.clearActionsReorderState(var2);
                  this.updateEditorPropertiesSidebar(var1, var2);
                  return true;
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   @Override
   protected boolean handleActionsEditPaste(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.actionsClipboardAction != null && !var2.actionsClipboardAction.isEmpty()) {
         String var3 = this.resolveSelectedActionsTargetId(var2);
         if (var3.isBlank()) {
            return false;
         } else {
            List var4 = this.resolveTargetActionsRawList(var2, var3, true);
            if (var4 == null) {
               return false;
            } else {
               Map var5 = this.deepCopyMap(var2.actionsClipboardAction);
               this.normalizeActionMapForEditor(var5);
               int var6 = this.resolveSelectedActionRawIndex(var2, var4);
               int var7 = var6 >= 0 ? Math.min(var4.size(), var6 + 1) : var4.size();
               this.recordEditorMutation(var2);
               var4.add(var7, var5);
               var2.actionsSelectedIndex = var7;
               var2.pendingActionTargetId = null;
               var2.pendingActionIndex = -1;
               this.clearActionsReorderState(var2);
               this.updateEditorPropertiesSidebar(var1, var2);
               return true;
            }
         }
      } else {
         return false;
      }
   }

   @Override
   protected int resolveSelectedActionRawIndex(EditorSession var1, List<Map<String, Object>> var2) {
      if (var1 != null && var2 != null && !var2.isEmpty()) {
         return var1.actionsSelectedIndex >= 0 && var1.actionsSelectedIndex < var2.size() ? var1.actionsSelectedIndex : var2.size() - 1;
      } else {
         return -1;
      }
   }

   @Override
   protected String resolveCursorActionsTypeByDropdownId(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim().toLowerCase(Locale.ROOT);
      if (var2.isBlank()) {
         return "";
      } else {
         return switch (var2) {
            case "dropdown_actions_command" -> "command";
            case "dropdown_actions_console", "dropdown_console" -> "console";
            case "dropdown_actions_message", "dropdown_message" -> "message";
            case "dropdown_actions_redirect", "dropdown_redirect" -> "redirect";
            case "dropdown_actions_close", "dropdown_close" -> "close";
            case "dropdown_actions_animation", "dropdown_animation" -> "animation";
            case "dropdown_actions_delay", "dropdown_delay" -> "delay";
            case "dropdown_actions_sound", "dropdown_sound" -> "sound";
            case "dropdown_actions_teleport", "dropdown_teleport", "dropdown_tp" -> "teleport";
            default -> "";
         };
      }
   }

   @Override
   protected String resolveSelectedActionsTargetId(EditorSession var1) {
      return var1 == null ? "" : this.firstNonBlank(new String[]{var1.selectedElementId}).trim();
   }

   @Override
   protected List<Map<String, Object>> resolveTargetActionsRawList(EditorSession var1, String var2, boolean var3) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var4 = this.resolveRawTargetByTargetId(var1, var2);
         if (var4 == null) {
            return null;
         } else if (var4.get("actions") instanceof List var6) {
            ArrayList var11 = new ArrayList();

            for (Object var9 : var6) {
               Map var10 = this.toStringObjectMap(var9);
               if (var10 != null && !var10.isEmpty()) {
                  this.normalizeActionMapForEditor(var10);
                  var11.add(var10);
               }
            }

            var4.put("actions", var11);
            return var11;
         } else if (!var3) {
            return new ArrayList<>();
         } else {
            ArrayList var7 = new ArrayList();
            var4.put("actions", var7);
            return var7;
         }
      } else {
         return null;
      }
   }

   @Override
   protected void normalizeActionMapForEditor(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         String var2 = this.normalizeActionType(this.stringValue(var1.get("type")));
         String var3 = this.resolveActionDisplayValue(var1, var2);
         var1.put("type", var2);
         var1.put("value", var3);
         var1.put("name", var3);
      }
   }

   @Override
   protected Map<String, Object> buildDefaultActionMap(String var1) {
      LinkedHashMap var2 = new LinkedHashMap();
      String var3 = this.normalizeActionType(var1);
      String var4 = this.resolveDefaultActionValue(var3);
      var2.put("type", var3);
      var2.put("value", var4);
      var2.put("name", var4);
      return var2;
   }

   @Override
   protected String normalizeActionType(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim().toLowerCase(Locale.ROOT);

      return switch (var2) {
         case "command", "console", "message", "redirect", "close", "animation", "delay", "sound", "teleport" -> var2;
         case "playsound" -> "sound";
         case "tp" -> "teleport";
         case "server", "console_command", "server_command" -> "console";
         default -> "command";
      };
   }

   @Override
   protected String resolveDefaultActionValue(String var1) {
      String var2 = this.normalizeActionType(var1);

      return switch (var2) {
         case "console" -> "say Hello";
         case "close" -> "Close";
         case "delay" -> "1000ms";
         case "sound" -> "ENTITY_PLAYER_LEVELUP 1,2";
         case "teleport" -> "0 64 0";
         default -> "---";
      };
   }

   @Override
   protected String resolveActionDisplayValue(Map<String, Object> var1, String var2) {
      if (var1 != null && !var1.isEmpty()) {
         String var3 = this.firstNonBlank(
               new String[]{this.stringValue(var1.get("value")), this.stringValue(var1.get("name")), this.stringValue(var1.get("data"))}
            )
            .trim();
         return var3.isBlank() ? this.resolveDefaultActionValue(var2) : var3;
      } else {
         return this.resolveDefaultActionValue(var2);
      }
   }

   @Override
   protected boolean isEditableActionType(String var1) {
      return !"close".equals(this.normalizeActionType(var1));
   }

   @Override
   protected void startSidebarActionInputPrompt(Player var1, EditorSession var2, String var3, int var4, String var5, String var6) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var4 >= 0) {
         String var7 = this.normalizeActionType(var5);
         if (this.isEditableActionType(var7)) {
            String var8 = switch (var7) {
               case "command" -> "/say Hello";
               case "console" -> "say Hello from console";
               case "message" -> "<green>Hello";
               case "redirect" -> "example_page";
               case "animation" -> "fade_in";
               case "delay" -> "1000ms";
               case "sound" -> "ENTITY_PLAYER_LEVELUP 1,2";
               case "teleport" -> "5 2 1";
               default -> "---";
            };
            String var9 = this.firstNonBlank(new String[]{var6, this.resolveDefaultActionValue(var7)});
            var2.pendingPropertyField = null;
            var2.pendingActionTargetId = var3;
            var2.pendingActionIndex = var4;
            this.updateEditorPropertiesSidebar(var1, var2);
            this.sendEditorPlayerMessage(
               var1,
               MM.deserialize(
                  "<#84a0c8>Action "
                     + var7
                     + " <gray>- type a new value (<white>"
                     + var8
                     + "</white>) or <white>cancel</white>. Current: <white>"
                     + var9
                     + "</white>.</gray>"
               )
            );
         }
      }
   }

   @Override
   protected void armActionsReorder(EditorSession var1, int var2, double var3, double var5) {
      if (var1 != null && var2 >= 0) {
         var1.actionsReorderArmedIndex = var2;
         var1.actionsReorderCurrentIndex = var2;
         var1.actionsReorderStartHitX = var3;
         var1.actionsReorderStartHitY = var5;
         var1.actionsReorderActive = false;
         var1.actionsReorderHistoryRecorded = false;
      } else {
         this.clearActionsReorderState(var1);
      }
   }

   @Override
   protected void clearActionsReorderState(EditorSession var1) {
      if (var1 != null) {
         var1.actionsReorderArmedIndex = -1;
         var1.actionsReorderCurrentIndex = -1;
         var1.actionsReorderStartHitX = 0.0;
         var1.actionsReorderStartHitY = 0.0;
         var1.actionsReorderActive = false;
         var1.actionsReorderHistoryRecorded = false;
      }
   }

   @Override
   protected boolean isActionsReorderLeftHeld(EditorSession var1) {
      if (var1 == null) {
         return false;
      } else {
         long var2 = Math.max(2L, 2L);
         return (long)Bukkit.getCurrentTick() - var1.lastLeftClickTick <= var2;
      }
   }

   @Override
   protected boolean isActionsReorderActive(EditorSession var1) {
      return var1 != null && var1.actionsReorderActive && var1.actionsReorderArmedIndex >= 0;
   }

   @Override
   protected boolean handleActionsReorderCursorMove(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 == null || var2 == null) {
         return false;
      } else if (var2.rightSidebarTab != EditorSidebarTab.PROPERTIES || !this.isActionsSidebarMode(var2)) {
         this.clearActionsReorderState(var2);
         this.clearActionsReorderGhostHud(var1, var2);
         return false;
      } else if (var2.actionsReorderArmedIndex < 0) {
         this.clearActionsReorderGhostHud(var1, var2);
         return false;
      } else if (!this.isActionsReorderLeftHeld(var2)) {
         boolean var14 = this.isActionsReorderActive(var2);
         this.clearActionsReorderState(var2);
         this.clearActionsReorderGhostHud(var1, var2);
         if (var14) {
            this.updateEditorPropertiesSidebar(var1, var2);
         }

         return var14;
      } else {
         if (!this.isActionsReorderActive(var2)) {
            if (!this.isActionsReorderDragStartSatisfied(var2, var3, var5)) {
               this.clearActionsReorderGhostHud(var1, var2);
               return false;
            }

            int var7 = var2.actionsRenderedRawIndexes.size();
            int var8 = this.resolveActionSlotByHit(var2, var3, var5, var7);
            if (var8 < 1 || var8 > var7) {
               this.clearActionsReorderGhostHud(var1, var2);
               return false;
            }

            int var9 = var2.actionsReorderArmedIndex;
            int var10 = var2.actionsRenderedRawIndexes.get(var8 - 1);
            if (var10 < 0 || var10 == var9) {
               this.clearActionsReorderGhostHud(var1, var2);
               return false;
            }

            var2.actionsReorderActive = true;
            this.updateEditorPropertiesSidebar(var1, var2);
         }

         String var13 = this.resolveSelectedActionsTargetId(var2);
         if (var13.isBlank()) {
            this.clearActionsReorderState(var2);
            this.clearActionsReorderGhostHud(var1, var2);
            return false;
         } else {
            List var15 = this.resolveTargetActionsRawList(var2, var13, false);
            if (var15 != null && var15.size() > 1) {
               int var16 = var2.actionsRenderedRawIndexes.size();
               int var17 = this.resolveActionSlotByHit(var2, var3, var5, var16);
               if (var17 >= 1 && var17 <= var16) {
                  int var11 = var2.actionsRenderedRawIndexes.get(var17 - 1);
                  int var12 = var2.actionsReorderCurrentIndex < 0 ? var2.actionsReorderArmedIndex : var2.actionsReorderCurrentIndex;
                  if (var12 < 0 || var12 >= var15.size() || var11 < 0 || var11 >= var15.size()) {
                     this.clearActionsReorderState(var2);
                     this.clearActionsReorderGhostHud(var1, var2);
                     return false;
                  } else if (var12 == var11) {
                     this.renderActionsReorderGhostHud(var1, var2, var3, var5);
                     return true;
                  } else {
                     if (!var2.actionsReorderHistoryRecorded) {
                        this.recordEditorMutation(var2);
                        var2.actionsReorderHistoryRecorded = true;
                     }

                     this.moveActionInRawList(var15, var12, var11);
                     var2.actionsReorderCurrentIndex = var11;
                     var2.actionsSelectedIndex = var11;
                     var2.actionsReorderStartHitX = var3;
                     var2.actionsReorderStartHitY = var5;
                     this.updateEditorPropertiesSidebar(var1, var2);
                     this.renderActionsReorderGhostHud(var1, var2, var3, var5);
                     return true;
                  }
               } else {
                  this.renderActionsReorderGhostHud(var1, var2, var3, var5);
                  return true;
               }
            } else {
               this.clearActionsReorderState(var2);
               this.clearActionsReorderGhostHud(var1, var2);
               return false;
            }
         }
      }
   }

   @Override
   protected void moveActionInRawList(List<Map<String, Object>> var1, int var2, int var3) {
      if (var1 != null && !var1.isEmpty() && var2 != var3) {
         int var4 = Math.max(0, Math.min(var1.size() - 1, var2));
         int var5 = Math.max(0, Math.min(var1.size() - 1, var3));
         Map var6 = (Map)var1.remove(var4);
         var1.add(var5, var6);
      }
   }

   @Override
   protected boolean handleActionsPanelClick(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         long var7 = (long)Bukkit.getCurrentTick();
         String var9 = this.resolveSelectedActionsTargetId(var2);
         if (var2.cursorActionsEditDropdownVisible) {
            String var10 = this.resolveHoveredCursorActionsEditDropdownTargetId(var2, var3, var5);
            if (!var10.isBlank()) {
               this.setCursorActionsEditDropdownVisible(var1, var2, false);
               this.playEditorSfx(var1, var2, "dropdown-item-clicked");
               this.handleCursorActionsEditDropdownAction(var1, var2, var10);
               return true;
            }

            double var11 = var3 - var2.hitboxOffsetX;
            double var13 = var5 - var2.hitboxOffsetY;
            if (this.isInsideShellBlock(var2, "cursor_actions_edit_dropdown", var11, var13)) {
               return true;
            }

            this.setCursorActionsEditDropdownVisible(var1, var2, false);
         }

         if (var2.cursorActionsDropdownVisible) {
            String var24 = this.resolveHoveredCursorActionsDropdownTargetId(var2, var3, var5);
            if (!var24.isBlank()) {
               this.setCursorActionsDropdownVisible(var1, var2, false);
               this.playEditorSfx(var1, var2, "dropdown-item-clicked");
               this.handleCursorActionsDropdownAction(var1, var2, var24);
               return true;
            }

            double var27 = var3 - var2.hitboxOffsetX;
            double var29 = var5 - var2.hitboxOffsetY;
            if (this.isInsideShellBlock(var2, "cursor_actions_dropdown", var27, var29)) {
               return true;
            }

            this.setCursorActionsDropdownVisible(var1, var2, false);
         }

         if (!var9.isBlank() && this.isInsideShellBlock(var2, "actions_new_hitbox", var3, var5)) {
            boolean var26 = !var2.cursorActionsDropdownVisible;
            if (var26) {
               this.setFileDropdownVisible(var1, var2, false);
               this.setEditDropdownVisible(var1, var2, false);
               this.setSelectionDropdownVisible(var1, var2, false);
               this.setLayerDropdownVisible(var1, var2, false);
               this.setWindowDropdownVisible(var1, var2, false);
            }

            if (var2.cursorActionsEditDropdownVisible) {
               this.setCursorActionsEditDropdownVisible(var1, var2, false);
            }

            if (!var26) {
               this.setCursorActionsDropdownVisible(var1, var2, false);
               return true;
            } else {
               this.moveCursorActionsDropdownTo(var1, var2, var3, var5);
               this.setCursorActionsDropdownVisible(var1, var2, true);
               this.moveCursorActionsDropdownTo(var1, var2, var3, var5);
               this.updateCursorActionsDropdownHover(var1, var2, var3, var5);
               return true;
            }
         } else {
            int var25 = var2.actionsRenderedRawIndexes.size();
            int var28 = this.resolveActionSlotByHit(var2, var3, var5, var25);
            EditorRect var12 = this.resolveActionsListRect(var2, var25);
            if (var28 < 1 || var28 > var25) {
               boolean var31 = this.isInsideRect(var12, var3, var5);
               boolean var32 = this.isInsideShellBlock(var2, "actions", var3, var5);
               if (!var31 && !var32) {
                  return false;
               } else {
                  boolean var33 = var2.actionsSelectedIndex >= 0 || var2.pendingActionIndex >= 0 || var2.cursorActionsEditDropdownVisible;
                  this.clearActionsReorderState(var2);
                  this.clearActionsReorderGhostHud(var1, var2);
                  if (var33) {
                     var2.actionsSelectedIndex = -1;
                     var2.pendingActionTargetId = null;
                     var2.pendingActionIndex = -1;
                     this.resetActionsEditClickTracker(var2);
                     if (var2.cursorActionsEditDropdownVisible) {
                        this.setCursorActionsEditDropdownVisible(var1, var2, false);
                     }

                     this.updateEditorPropertiesSidebar(var1, var2);
                  }

                  return true;
               }
            } else if (var9.isBlank()) {
               return true;
            } else {
               List var30 = this.resolveTargetActionsRawList(var2, var9, false);
               int var14 = var2.actionsRenderedRawIndexes.get(var28 - 1);
               if (var30 != null && var14 >= 0 && var14 < var30.size()) {
                  Map var15 = (Map)var30.get(var14);
                  String var16 = this.normalizeActionType(this.stringValue(var15.get("type")));
                  boolean var17 = var2.actionsSelectedIndex == var14;
                  boolean var18 = this.isEditableActionType(var16);
                  long var19 = var7 - var2.actionsLastClickTick;
                  boolean var21 = var19 >= 2L && var19 <= 10L;
                  boolean var22 = this.hasActionsEditMovementExceeded(var2, var14, var3, var5);
                  boolean var23 = var18 && var17 && var2.actionsLastClickIndex == var14 && var21 && !this.isActionsReorderActive(var2) && !var22;
                  if (var23) {
                     this.clearActionsReorderState(var2);
                     this.clearActionsReorderGhostHud(var1, var2);
                     this.resetActionsEditClickTracker(var2);
                     this.startSidebarActionInputPrompt(var1, var2, var9, var14, var16, this.resolveActionDisplayValue(var15, var16));
                     return true;
                  } else {
                     var2.actionsSelectedIndex = var14;
                     var2.pendingActionTargetId = null;
                     var2.pendingActionIndex = -1;
                     if (var2.cursorActionsEditDropdownVisible) {
                        this.setCursorActionsEditDropdownVisible(var1, var2, false);
                     }

                     var2.actionsLastClickIndex = var14;
                     var2.actionsLastClickTick = var7;
                     this.armActionsReorder(var2, var14, var3, var5);
                     this.clearActionsReorderGhostHud(var1, var2);
                     this.updateEditorPropertiesSidebar(var1, var2);
                     return true;
                  }
               } else {
                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   @Override
   protected boolean handleActionsPanelRightClick(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 == null || var2 == null) {
         return false;
      } else if (var2.rightSidebarTab == EditorSidebarTab.PROPERTIES && this.isActionsSidebarMode(var2)) {
         String var7 = this.resolveSelectedActionsTargetId(var2);
         if (!var7.isBlank() && this.isInsideShellBlock(var2, "actions_new_hitbox", var3, var5)) {
            this.setFileDropdownVisible(var1, var2, false);
            this.setEditDropdownVisible(var1, var2, false);
            this.setSelectionDropdownVisible(var1, var2, false);
            this.setLayerDropdownVisible(var1, var2, false);
            this.setWindowDropdownVisible(var1, var2, false);
            this.moveCursorActionsDropdownTo(var1, var2, var3, var5);
            this.setCursorActionsDropdownVisible(var1, var2, true);
            this.moveCursorActionsDropdownTo(var1, var2, var3, var5);
            this.updateCursorActionsDropdownHover(var1, var2, var3, var5);
            return true;
         } else {
            if (var2.cursorActionsEditDropdownVisible) {
               String var8 = this.resolveHoveredCursorActionsEditDropdownTargetId(var2, var3, var5);
               if (!var8.isBlank()) {
                  this.setCursorActionsEditDropdownVisible(var1, var2, false);
                  this.playEditorSfx(var1, var2, "dropdown-item-clicked");
                  this.handleCursorActionsEditDropdownAction(var1, var2, var8);
                  return true;
               }

               double var9 = var3 - var2.hitboxOffsetX;
               double var11 = var5 - var2.hitboxOffsetY;
               if (this.isInsideShellBlock(var2, "cursor_actions_edit_dropdown", var9, var11)) {
                  return true;
               }

               this.setCursorActionsEditDropdownVisible(var1, var2, false);
            }

            int var13 = var2.actionsRenderedRawIndexes.size();
            int var14 = this.resolveActionSlotByHit(var2, var3, var5, var13);
            EditorRect var10 = this.resolveActionsListRect(var2, var13);
            if (var14 < 1 || var14 > var13) {
               return this.isInsideRect(var10, var3, var5);
            } else if (var7.isBlank()) {
               return true;
            } else {
               List var15 = this.resolveTargetActionsRawList(var2, var7, false);
               int var12 = var2.actionsRenderedRawIndexes.get(var14 - 1);
               if (var15 != null && var12 >= 0 && var12 < var15.size()) {
                  var2.actionsSelectedIndex = var12;
                  var2.pendingActionTargetId = null;
                  var2.pendingActionIndex = -1;
                  this.clearActionsReorderState(var2);
                  this.setFileDropdownVisible(var1, var2, false);
                  this.setEditDropdownVisible(var1, var2, false);
                  this.setSelectionDropdownVisible(var1, var2, false);
                  this.setLayerDropdownVisible(var1, var2, false);
                  this.setWindowDropdownVisible(var1, var2, false);
                  if (var2.cursorActionsDropdownVisible) {
                     this.setCursorActionsDropdownVisible(var1, var2, false);
                  }

                  this.updateEditorPropertiesSidebar(var1, var2);
                  this.moveCursorActionsEditDropdownTo(var1, var2, var3, var5);
                  this.setCursorActionsEditDropdownVisible(var1, var2, true);
                  this.moveCursorActionsEditDropdownTo(var1, var2, var3, var5);
                  this.updateCursorActionsEditDropdownHover(var1, var2, var3, var5);
                  return true;
               } else {
                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   @Override
   protected boolean handleActionsScroll(Player var1, EditorSession var2, double var3, double var5, int var7) {
      if (var1 != null && var2 != null && var7 != 0 && var2.rightSidebarTab == EditorSidebarTab.PROPERTIES && this.isActionsSidebarMode(var2)) {
         String var8 = this.resolveSelectedActionsTargetId(var2);
         List var9 = this.resolveTargetActionsRawList(var2, var8, false);
         int var10 = var9 == null ? 0 : var9.size();
         int var11 = Math.max(1, Math.min(17, Math.max(1, var10)));
         EditorRect var12 = this.resolveActionsListRect(var2, var11);
         if (!this.isInsideRect(var12, var3, var5)) {
            return false;
         } else {
            int var13 = var7 > 0 ? 1 : -1;
            int var14 = Math.max(0, var10 - 17);
            if (var14 <= 0) {
               var2.actionsScrollOffset = 0;
               this.flashActionsScrollEdge(var1, var2, var13 < 0);
               return true;
            } else {
               int var15 = (int)Math.floor(this.clampLayersScrollValue((double)var2.actionsScrollOffset, var14) + 1.0E-4);
               int var16 = (int)Math.floor(this.clampLayersScrollValue((double)(var15 + var13), var14) + 1.0E-4);
               if (var16 == var15) {
                  this.flashActionsScrollEdge(var1, var2, var13 < 0);
                  return true;
               } else {
                  var2.actionsScrollOffset = var16;
                  this.renderActionsPanel(var1, var2);
                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   @Override
   protected void flashActionsScrollEdge(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         String var4 = var3 ? "actions_scroll_top" : "actions_scroll_bottom";
         int var5 = this.getShellOpacity(var1, var2, var4, 0);
         int var6 = var3 ? ++var2.actionsScrollTopFlashToken : ++var2.actionsScrollBottomFlashToken;
         int[] var7 = new int[]{0};
         PlatformCompat.runEntityTimer(this.plugin, var1, 0L, 1L, var8 -> {
            if (!var1.isOnline()) {
               var8.cancel();
            } else {
               EditorSession var9 = this.editorSessions.get(var1.getUniqueId());
               if (var9 != var2) {
                  var8.cancel();
               } else {
                  int var10 = var3 ? var2.actionsScrollTopFlashToken : var2.actionsScrollBottomFlashToken;
                  if (var10 != var6) {
                     var8.cancel();
                  } else if (var2.rightSidebarTab == EditorSidebarTab.PROPERTIES && this.isActionsSidebarMode(var2)) {
                     var7[0]++;
                     int var11;
                     if (var7[0] <= 3) {
                        double var12 = (double)var7[0] / 3.0;
                        var11 = (int)Math.round((double)var5 + (255.0 - (double)var5) * var12);
                     } else {
                        int var14 = var7[0] - 3;
                        var11 = (int)Math.round(255.0 * (1.0 - (double)var14 / 3.0));
                     }

                     this.setShellOpacity(var1, var4, Math.max(0, Math.min(255, var11)));
                     if (var7[0] >= 6) {
                        this.setShellOpacity(var1, var4, 0);
                        var8.cancel();
                     }
                  } else {
                     this.setShellOpacity(var1, var4, 0);
                     var8.cancel();
                  }
               }
            }
         });
      }
   }

   @Override
   protected void setCursorActionsEditDropdownVisible(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         if (var2.cursorActionsEditDropdownVisible == var3) {
            if (var3) {
               double var12 = var2.cursorX + var2.hitboxOffsetX;
               double var13 = var2.cursorY + var2.hitboxOffsetY;
               this.moveCursorActionsEditDropdownTo(var1, var2, var12, var13);
               this.updateCursorActionsEditDropdownHover(var1, var2, var12, var13);
            }
         } else {
            double var4 = var2.cursorX + var2.hitboxOffsetX;
            double var6 = var2.cursorY + var2.hitboxOffsetY;
            var2.cursorActionsEditDropdownVisible = var3;
            if (!var3) {
               var2.cursorActionsEditDropdownHoverTargetId = null;
            } else {
               this.playEditorSfx(var1, var2, "dropdown-opened");
            }

            this.beginShellOpacityBatch(var2);

            try {
               this.setSidebarPanelVisible(var1, var2, "cursor_actions_edit_dropdown", var3);
               this.updateSidebarTabVisualState(var1, var2, false, false);
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }

            if (var3) {
               this.updateCursorActionsEditDropdownHover(var1, var2, var4, var6);
            } else {
               for (Object var9_raw : CURSOR_ACTIONS_EDIT_DROPDOWN_ITEM_IDS) {
                  String var9 = var9_raw != null ? var9_raw.toString() : null;
                  this.setCursorActionsEditDropdownItemVisual(var1, var2, var9, false);
               }
            }
         }
      }
   }

   @Override
   protected void updateCursorActionsEditDropdownHover(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         String var7 = var2.cursorActionsEditDropdownVisible ? this.resolveHoveredCursorActionsEditDropdownTargetId(var2, var3, var5) : null;
         if (!this.equalsNullable(var2.cursorActionsEditDropdownHoverTargetId, var7)) {
            var2.cursorActionsEditDropdownHoverTargetId = var7;

            for (Object var9_raw : CURSOR_ACTIONS_EDIT_DROPDOWN_ITEM_IDS) {
               String var9 = var9_raw != null ? var9_raw.toString() : null;
               this.setCursorActionsEditDropdownItemVisual(var1, var2, var9, this.equalsNullable(var9, var7));
            }
         }
      }
   }

   @Override
   protected void setCursorActionsEditDropdownItemVisual(Player var1, EditorSession var2, String var3, boolean var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String[] var5 = CURSOR_ACTIONS_EDIT_DROPDOWN_ITEM_ICONS.get(var3);
         if (var5 != null && var5.length >= 2) {
            this.setShellText(var1, var3 + "_text", var4 ? var5[1] : var5[0]);
         }

         this.setShellColor(var1, var2, var3, var4 ? "141414" : "0f0f0f");
      }
   }

   @Override
   protected String resolveHoveredCursorActionsEditDropdownTargetId(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.cursorActionsEditDropdownVisible) {
         double var6 = var2 - var1.hitboxOffsetX;
         double var8 = var4 - var1.hitboxOffsetY;
         int var10 = 0;

         for (Object var12_raw : CURSOR_ACTIONS_EDIT_DROPDOWN_ITEM_IDS) {
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

         if (var10 == CURSOR_ACTIONS_EDIT_DROPDOWN_ITEM_IDS.size()) {
            return "";
         } else {
            EditorRect var15 = this.findShellBlockRect(var1, "cursor_actions_edit_dropdown");
            if (var15 != null && var6 >= var15.x && var6 <= var15.maxX() && var8 >= var15.y && var8 <= var15.maxY()) {
               double var16 = 32.0;
               int var19 = (int)Math.floor((var8 - var15.y) / 32.0);
               if (var19 >= 0 && var19 < CURSOR_ACTIONS_EDIT_DROPDOWN_ITEM_IDS.size()) {
                  return CURSOR_ACTIONS_EDIT_DROPDOWN_ITEM_IDS.get(var19);
               }
            }

            for (Object var18_raw : CURSOR_ACTIONS_EDIT_DROPDOWN_ITEM_IDS) {
               String var18 = var18_raw != null ? var18_raw.toString() : null;
               if (this.isInsideShellBlock(var1, var18, var6, var8)) {
                  return var18;
               }
            }

            return "";
         }
      } else {
         return "";
      }
   }

   @Override
   protected void moveCursorActionsEditDropdownTo(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         ConfigurationSection var7 = this.findShellBlockSection(var2, "cursor_actions_edit_dropdown");
         EditorRect var8 = this.findShellBlockRect(var2, "cursor_actions_edit_dropdown");
         if (var7 != null && var8 != null) {
            double var9 = var3 - var2.hitboxOffsetX;
            double var11 = var5 - var2.hitboxOffsetY;
            double var13 = var9;
            double var15 = var11;
            boolean var17 = this.isInsidePreviewArea(var2, var3, var5);
            if (var17) {
               EditorRect var18 = this.findShellBlockRect(var2, "preview");
               if (var18 != null) {
                  double var19 = var18.maxX() - var8.width;
                  boolean var21 = var9 > var19;
                  var13 = Math.max(var18.x, Math.min(var19, var9));
                  var15 = Math.max(var18.y, Math.min(var18.maxY() - var8.height, var11));
                  if (var21) {
                     var13 = Math.max(var18.x, var13 - 11.0);
                  }
               }
            } else {
               double var31 = var2.yaml == null ? 1920.0 : var2.yaml.getDouble("screen.width", 1920.0);
               double var20 = var2.yaml == null ? 1080.0 : var2.yaml.getDouble("screen.height", 1080.0);
               double var22 = Math.max(0.0, var31 - var8.width);
               double var24 = Math.max(0.0, var20 - var8.height);
               boolean var26 = var9 > var22;
               var13 = Math.max(0.0, Math.min(var22, var9));
               var15 = Math.max(0.0, Math.min(var24, var11));
               if (var26) {
                  var13 = Math.max(0.0, var13 - 11.0);
               }
            }

            double var32 = var13 - var8.x;
            double var33 = var15 - var8.y;
            if (!(Math.abs(var32) < 1.0E-4) || !(Math.abs(var33) < 1.0E-4)) {
               String var34 = this.firstNonBlank(new String[]{var7.getString("__editor_target_path")});
               if (var34.isBlank()) {
                  this.moveShellElement(var1, var2, "cursor_actions_edit_dropdown", var13, var15, var8.width, var8.height);
               } else {
                  HashMap var23 = new HashMap();
                  int var35 = 0;

                  for (Map var36 : var2.shellBlocks) {
                     var35++;
                     ConfigurationSection var27 = this.mapToSection(var36);
                     if (var27 != null) {
                        String var28 = this.firstNonBlank(new String[]{var27.getString("__editor_target_path")});
                        if (this.belongsToSidebarPanel(var34, var28)) {
                           String var29 = this.resolveElementId(var27, var35, var23);
                           if (!var29.isBlank()) {
                              EditorRect var30 = this.findShellBlockRect(var2, var29);
                              if (var30 != null) {
                                 this.moveShellElement(var1, var2, var29, var30.x + var32, var30.y + var33, var30.width, var30.height);
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
   protected void moveCursorActionsDropdownTo(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         ConfigurationSection var7 = this.findShellBlockSection(var2, "cursor_actions_dropdown");
         EditorRect var8 = this.findShellBlockRect(var2, "cursor_actions_dropdown");
         if (var7 != null && var8 != null) {
            double var9 = var3 - var2.hitboxOffsetX;
            double var11 = var5 - var2.hitboxOffsetY;
            double var13 = var9;
            double var15 = var11;
            boolean var17 = this.isInsidePreviewArea(var2, var3, var5);
            if (var17) {
               EditorRect var18 = this.findShellBlockRect(var2, "preview");
               if (var18 != null) {
                  double var19 = var18.maxX() - var8.width;
                  boolean var21 = var9 > var19;
                  var13 = Math.max(var18.x, Math.min(var19, var9));
                  var15 = Math.max(var18.y, Math.min(var18.maxY() - var8.height, var11));
                  if (var21) {
                     var13 = Math.max(var18.x, var13 - 11.0);
                  }
               }
            } else {
               double var31 = var2.yaml == null ? 1920.0 : var2.yaml.getDouble("screen.width", 1920.0);
               double var20 = var2.yaml == null ? 1080.0 : var2.yaml.getDouble("screen.height", 1080.0);
               double var22 = Math.max(0.0, var31 - var8.width);
               double var24 = Math.max(0.0, var20 - var8.height);
               boolean var26 = var9 > var22;
               var13 = Math.max(0.0, Math.min(var22, var9));
               var15 = Math.max(0.0, Math.min(var24, var11));
               if (var26) {
                  var13 = Math.max(0.0, var13 - 11.0);
               }
            }

            double var32 = var13 - var8.x;
            double var33 = var15 - var8.y;
            if (!(Math.abs(var32) < 1.0E-4) || !(Math.abs(var33) < 1.0E-4)) {
               String var34 = this.firstNonBlank(new String[]{var7.getString("__editor_target_path")});
               if (var34.isBlank()) {
                  this.moveShellElement(var1, var2, "cursor_actions_dropdown", var13, var15, var8.width, var8.height);
               } else {
                  HashMap var23 = new HashMap();
                  int var35 = 0;

                  for (Map var36 : var2.shellBlocks) {
                     var35++;
                     ConfigurationSection var27 = this.mapToSection(var36);
                     if (var27 != null) {
                        String var28 = this.firstNonBlank(new String[]{var27.getString("__editor_target_path")});
                        if (this.belongsToSidebarPanel(var34, var28)) {
                           String var29 = this.resolveElementId(var27, var35, var23);
                           if (!var29.isBlank()) {
                              EditorRect var30 = this.findShellBlockRect(var2, var29);
                              if (var30 != null) {
                                 this.moveShellElement(var1, var2, var29, var30.x + var32, var30.y + var33, var30.width, var30.height);
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
   protected void setCursorActionsDropdownVisible(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         if (var3 && var2.cursorActionsEditDropdownVisible) {
            this.setCursorActionsEditDropdownVisible(var1, var2, false);
         }

         if (var2.cursorActionsDropdownVisible == var3) {
            if (var3) {
               this.updateCursorActionsDropdownHover(var1, var2, var2.cursorX + var2.hitboxOffsetX, var2.cursorY + var2.hitboxOffsetY);
            }
         } else {
            var2.cursorActionsDropdownVisible = var3;
            if (!var3) {
               var2.cursorActionsDropdownHoverTargetId = null;
            } else {
               this.playEditorSfx(var1, var2, "dropdown-opened");
            }

            this.beginShellOpacityBatch(var2);

            try {
               this.setSidebarPanelVisible(var1, var2, "cursor_actions_dropdown", var3);
               this.updateSidebarTabVisualState(var1, var2, false, false);
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }

            if (var3) {
               this.updateCursorActionsDropdownHover(var1, var2, var2.cursorX + var2.hitboxOffsetX, var2.cursorY + var2.hitboxOffsetY);
            } else {
               for (Object var5_raw : CURSOR_ACTIONS_DROPDOWN_ITEM_IDS) {
                  String var5 = var5_raw != null ? var5_raw.toString() : null;
                  this.setCursorActionsDropdownItemVisual(var1, var2, var5, false);
               }
            }
         }
      }
   }

   @Override
   protected void updateCursorActionsDropdownHover(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         String var7 = var2.cursorActionsDropdownVisible ? this.resolveHoveredCursorActionsDropdownTargetId(var2, var3, var5) : null;
         if (!this.equalsNullable(var2.cursorActionsDropdownHoverTargetId, var7)) {
            var2.cursorActionsDropdownHoverTargetId = var7;

            for (Object var9_raw : CURSOR_ACTIONS_DROPDOWN_ITEM_IDS) {
               String var9 = var9_raw != null ? var9_raw.toString() : null;
               this.setCursorActionsDropdownItemVisual(var1, var2, var9, this.equalsNullable(var9, var7));
            }
         }
      }
   }

   @Override
   protected void setCursorActionsDropdownItemVisual(Player var1, EditorSession var2, String var3, boolean var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String[] var5 = CURSOR_ACTIONS_DROPDOWN_ITEM_ICONS.get(var3);
         if (var5 != null && var5.length >= 2) {
            this.setShellText(var1, var3 + "_text", var4 ? var5[1] : var5[0]);
         }

         this.setShellColor(var1, var2, var3, var4 ? "141414" : "0f0f0f");
      }
   }

   @Override
   protected String resolveHoveredCursorActionsDropdownTargetId(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.cursorActionsDropdownVisible) {
         double var6 = var2 - var1.hitboxOffsetX;
         double var8 = var4 - var1.hitboxOffsetY;
         int var10 = 0;

         for (Object var12_raw : CURSOR_ACTIONS_DROPDOWN_ITEM_IDS) {
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

         if (var10 == CURSOR_ACTIONS_DROPDOWN_ITEM_IDS.size()) {
            return "";
         } else {
            EditorRect var15 = this.findShellBlockRect(var1, "cursor_actions_dropdown");
            if (var15 != null && var6 >= var15.x && var6 <= var15.maxX() && var8 >= var15.y && var8 <= var15.maxY()) {
               double var16 = 32.0;
               int var19 = (int)Math.floor((var8 - var15.y) / 32.0);
               if (var19 >= 0 && var19 < CURSOR_ACTIONS_DROPDOWN_ITEM_IDS.size()) {
                  return CURSOR_ACTIONS_DROPDOWN_ITEM_IDS.get(var19);
               }
            }

            for (Object var18_raw : CURSOR_ACTIONS_DROPDOWN_ITEM_IDS) {
               String var18 = var18_raw != null ? var18_raw.toString() : null;
               if (this.isInsideShellBlock(var1, var18, var6, var8)) {
                  return var18;
               }
            }

            return "";
         }
      } else {
         return "";
      }
   }

   @Override
   protected void renderActionsPanel(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         if (this.isActionsPanelRuntimeAvailable(var2) && var2.rightSidebarTab == EditorSidebarTab.PROPERTIES && this.isActionsSidebarMode(var2)) {
            String var3 = this.resolveSelectedActionsTargetId(var2);
            boolean var4 = !var3.isBlank();
            this.setActionsNewButtonVisible(var1, var2, var4);
            if (!var4 && var2.cursorActionsDropdownVisible) {
               this.setCursorActionsDropdownVisible(var1, var2, false);
            }

            if (!var4 && var2.cursorActionsEditDropdownVisible) {
               this.setCursorActionsEditDropdownVisible(var1, var2, false);
            }

            if (!var4) {
               var2.actionsScrollOffset = 0;
            }

            List var5 = this.resolveTargetActionsRawList(var2, var3, false);
            if (var5 == null) {
               var5 = new ArrayList();
            }

            ArrayList var6 = new ArrayList();
            boolean var7 = this.isActionsReorderActive(var2) && this.isActionsReorderLeftHeld(var2);
            int var8 = -1;
            if (var7) {
               var8 = var2.actionsReorderCurrentIndex >= 0 ? var2.actionsReorderCurrentIndex : var2.actionsReorderArmedIndex;
               if (var8 < 0 || var8 >= var5.size()) {
                  var7 = false;
               }
            }

            for (int var9 = 0; var9 < var5.size(); var9++) {
               Map var10 = (Map)var5.get(var9);
               String var11 = this.normalizeActionType(this.stringValue(var10.get("type")));
               boolean var12 = var2.pendingPropertyField == null
                  && var9 == var2.pendingActionIndex
                  && this.equalsNullable(var3, this.firstNonBlank(new String[]{var2.pendingActionTargetId}).trim());
               String var13 = var12 ? "..." : this.resolveActionDisplayValue(var10, var11);
               boolean var14 = var7 && var9 == var8;
               var6.add(new PropertiesSidebarTimelineManager.ActionRowEntry(var9, var11, var13, var14));
            }

            if (var6.isEmpty()) {
               var2.actionsSelectedIndex = -1;
            } else if (var2.actionsSelectedIndex < 0 || var2.actionsSelectedIndex >= var6.size()) {
               var2.actionsSelectedIndex = Math.max(0, var6.size() - 1);
            }

            int var15 = Math.max(0, var6.size() - 17);
            int var16 = (int)Math.floor(this.clampLayersScrollValue((double)var2.actionsScrollOffset, var15) + 1.0E-4);
            int var17 = Math.min(var6.size(), var16 + 17);
            var2.actionsScrollOffset = var16;
            List var18 = var17 <= var16 ? Collections.emptyList() : var6.subList(var16, var17);
            this.renderActionsRuntimeRows(var1, var2, var18);
            if (var2.actionsReorderArmedIndex >= 0 && !this.isActionsReorderLeftHeld(var2)) {
               this.clearActionsReorderState(var2);
            }

            if (var2.actionsReorderArmedIndex < 0 || !this.isActionsReorderLeftHeld(var2)) {
               this.clearActionsReorderGhostHud(var1, var2);
            }
         } else {
            this.setShellOpacity(var1, "actions_scroll_top", 0);
            this.setShellOpacity(var1, "actions_scroll_bottom", 0);
            if (var2.cursorActionsDropdownVisible) {
               this.setCursorActionsDropdownVisible(var1, var2, false);
            }

            if (var2.cursorActionsEditDropdownVisible) {
               this.setCursorActionsEditDropdownVisible(var1, var2, false);
            }

            this.clearActionsRuntimeHud(var1, var2);
         }
      }
   }

   @Override
   protected void setActionsNewButtonVisible(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         this.setShellOpacity(var1, "actions_new_button", var3 ? 255 : 0);
      }
   }

   @Override
   protected boolean isActionsPanelRuntimeAvailable(EditorSession var1) {
      return var1 == null ? false : this.findShellBlockSection(var1, "actions") != null;
   }

   @Override
   protected void renderActionsRuntimeRows(Player var1, EditorSession var2, List<PropertiesSidebarTimelineManager.ActionRowEntry> var3) {
      if (var1 != null && var2 != null) {
         List var4 = this.buildActionsRuntimeGridBlocks(var2, var3);
         List var5 = this.resolveRenderableBlocksNoCopy(var4, var2.components);
         HashMap var6 = new HashMap();
         LinkedHashSet var7 = new LinkedHashSet();
         int var8 = 0;

         for (Map var10 : (List<Map>)(List<?>)var5) {
            var8++;
            String var11 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var10, "type")), "block"}).toLowerCase(Locale.ROOT);
            if (this.isRenderableBlockType(var11)) {
               String var12 = this.resolveElementId(var10, var8, var6);
               String var13 = this.actionRuntimeLogicalId(var12);
               if (this.canUseFastLayerRuntimeRender(var10, var11)) {
                  this.renderLayerRuntimeShellElementFast(var1, var2, var10, var13, var11, false);
               } else {
                  ConfigurationSection var14 = this.mapToSection(var10);
                  if (var14 == null) {
                     continue;
                  }

                  this.renderLayerRuntimeShellElement(var1, var2, var14, var13, var11, false);
               }

               var7.add(var13);
            }
         }

         this.clearStaleActionRuntimeShellIds(var1, var2, var7);
         var2.runtimeActionShellIds.clear();
         var2.runtimeActionShellIds.addAll(var7);
         var2.actionsRenderedRawIndexes.clear();
         if (var3 != null && !var3.isEmpty()) {
            for (PropertiesSidebarTimelineManager.ActionRowEntry var16 : var3) {
               var2.actionsRenderedRawIndexes.add(var16.rawIndex);
            }
         }
      }
   }

   @Override
   protected List<Map<String, Object>> buildActionsRuntimeGridBlocks(EditorSession var1, List<PropertiesSidebarTimelineManager.ActionRowEntry> var2) {
      ArrayList var3 = new ArrayList();
      if (var2 != null) {
         for (int var4 = 1; var4 <= var2.size(); var4++) {
            PropertiesSidebarTimelineManager.ActionRowEntry var5 = (PropertiesSidebarTimelineManager.ActionRowEntry)var2.get(var4 - 1);
            boolean var6 = var1 != null && var5.rawIndex == var1.actionsSelectedIndex;
            boolean var7 = var5.dragPlaceholder;
            LinkedHashMap var8 = new LinkedHashMap();
            var8.put("id", this.actionSlotBaseId(var4));
            var8.put("slot", this.actionSlotBaseId(var4));
            var8.put("name", this.truncateLayerDisplayNameForDepth(this.firstNonBlank(new String[]{var5.displayValue, "---"}), 0));
            var8.put("row_opacity", 0);
            if (var7) {
               var8.put("name", " ");
               var8.put("name_color", "666666");
               var8.put("box1_icon", "");
               var8.put("box2_icon", "");
               var8.put("action_icon", "");
            } else {
               var8.put("name_color", var6 ? "ffffff" : "666666");
               var8.put("box1_icon", var6 ? "\ue63c" : "\ue638");
               var8.put("box2_icon", var6 ? "\ue63d" : "\ue639");
               String var9 = var6
                  ? this.firstNonBlank(new String[]{ACTION_TYPE_ACTIVE_ICONS.get(var5.actionType), ACTION_TYPE_ACTIVE_ICONS.get("command"), "\uef06"})
                  : this.firstNonBlank(new String[]{ACTION_TYPE_ICONS.get(var5.actionType), ACTION_TYPE_ICONS.get("command"), "\uef00"});
               var8.put("action_icon", var9);
            }

            LinkedHashMap var17 = new LinkedHashMap();
            var17.put("component", "editor_action");
            var17.put("params", var8);
            var3.add(var17);
         }
      }

      double var14 = this.resolveActionsGridStartX(var1);
      double var15 = this.resolveActionsGridStartY(var1);
      double var16 = this.resolveActionsGridGap(var1);
      Double var10 = this.resolveActionsGridElementHeight(var1);
      LinkedHashMap var11 = new LinkedHashMap();
      var11.put("x", var14);
      var11.put("y", var15);
      LinkedHashMap var12 = new LinkedHashMap();
      var12.put("type", "grid_block");
      var12.put("direction", "column");
      var12.put("gap", var16);
      if (var10 != null) {
         var12.put("element_h", var10);
      }

      var12.put("position", var11);
      var12.put("children", var3);
      ArrayList var13 = new ArrayList();
      var13.add(var12);
      return var13;
   }

   @Override
   protected double resolveActionsGridStartX(EditorSession var1) {
      Map var2 = this.findActionsGridBlockMap(var1);
      if (var2 != null) {
         return this.readMapPathDouble(var2, "position.x", this.readMapPathDouble(var2, "x", 24.0));
      } else {
         EditorRect var3 = this.findShellBlockRect(var1, "actions");
         if (var3 != null) {
            return var3.x + 24.0;
         } else {
            EditorRect var4 = this.findShellBlockRect(var1, "right_sidebar");
            return var4 != null ? var4.x + 24.0 : 24.0;
         }
      }
   }

   @Override
   protected double resolveActionsGridStartY(EditorSession var1) {
      Map var2 = this.findActionsGridBlockMap(var1);
      if (var2 != null) {
         return this.readMapPathDouble(var2, "position.y", this.readMapPathDouble(var2, "y", 130.0));
      } else {
         EditorRect var3 = this.findShellBlockRect(var1, "actions");
         if (var3 != null) {
            return var3.y + 130.0;
         } else {
            EditorRect var4 = this.findShellBlockRect(var1, "right_sidebar");
            return var4 != null ? var4.y + 130.0 : 130.0;
         }
      }
   }

   @Override
   protected double resolveActionsGridGap(EditorSession var1) {
      Map var2 = this.findActionsGridBlockMap(var1);
      return var2 != null ? this.readMapPathDouble(var2, "gap", this.readMapPathDouble(var2, "spacing", -51.0)) : -51.0;
   }

   @Override
   protected Double resolveActionsGridElementHeight(EditorSession var1) {
      Map var2 = this.findActionsGridBlockMap(var1);
      if (var2 == null) {
         return null;
      } else {
         double var3 = this.readMapPathDouble(
            var2,
            "element_h",
            this.readMapPathDouble(
               var2, "elementHeight", this.readMapPathDouble(var2, "element_height", this.readMapPathDouble(var2, "element-height", Double.NaN))
            )
         );
         return !Double.isFinite(var3) ? null : Math.max(0.0, var3);
      }
   }

   @Override
   protected Map<String, Object> findActionsGridBlockMap(EditorSession var1) {
      if (var1 != null && var1.shellBlocks != null && !var1.shellBlocks.isEmpty()) {
         ConfigurationSection var2 = this.findShellBlockSection(var1, "actions");
         String var3 = this.firstNonBlank(new String[]{var2 == null ? null : var2.getString("__editor_target_path")});
         if (var3.isBlank()) {
            return null;
         } else {
            for (Map var5 : var1.shellBlocks) {
               if (var5 != null && !var5.isEmpty()) {
                  String var6 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var5, "type")), "block"}).toLowerCase(Locale.ROOT);
                  if ("grid_block".equals(var6)) {
                     String var7 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var5, "id"))});
                     if ("actions_list".equals(var7)) {
                        String var8 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var5, "__editor_target_path"))});
                        if (this.belongsToSidebarPanel(var3, var8)) {
                           return var5;
                        }
                     }
                  }
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   @Override
   protected EditorRect resolveActionsListRect(EditorSession var1, int var2) {
      if (var1 == null) {
         return null;
      } else {
         EditorRect var3 = this.findShellBlockRect(var1, this.actionSlotId(1));
         if (var3 != null) {
            double var11 = this.resolveActionsRowStep(var1);
            int var12 = Math.max(1, var2);
            double var7 = var3.height + var11 * (double)Math.max(0, var12 - 1);
            return new EditorRect(var3.x, var3.y, var3.width, Math.max(var3.height, var7));
         } else {
            double var4 = this.resolveActionsRowStep(var1);
            double var6 = this.resolveActionsGridStartX(var1);
            double var8 = this.resolveActionsGridStartY(var1);
            int var10 = Math.max(1, var2);
            return new EditorRect(var6, var8, 289.0, var4 * (double)var10);
         }
      }
   }

   @Override
   protected int resolveActionSlotByHit(EditorSession var1, double var2, double var4, int var6) {
      if (var1 != null && var6 > 0) {
         int var7 = Math.max(0, Math.min(17, var6));
         if (var7 <= 0) {
            return -1;
         } else {
            for (int var8 = 1; var8 <= var7; var8++) {
               if (this.isInsideShellBlock(var1, this.actionSlotId(var8), var2, var4)) {
                  EditorRect var9 = this.findShellBlockRect(var1, this.actionSlotId(var8));
                  if (var9 != null && var4 >= var9.y && var4 <= var9.maxY()) {
                     return var8;
                  }
               }
            }

            EditorRect var14 = this.findShellBlockRect(var1, this.actionSlotId(1));
            if (var14 == null) {
               return -1;
            } else {
               double var15 = this.resolveActionsRowStep(var1);
               if (var15 <= 0.0) {
                  var15 = var14.height;
               }

               double var11 = var4 - var14.y;
               int var13 = (int)Math.floor(var11 / var15) + 1;
               return var13 >= 1 && var13 <= var7 ? var13 : -1;
            }
         }
      } else {
         return -1;
      }
   }

   @Override
   protected double resolveActionsRowStep(EditorSession var1) {
      EditorRect var2 = this.findShellBlockRect(var1, this.actionSlotId(1));
      EditorRect var3 = this.findShellBlockRect(var1, this.actionSlotId(2));
      if (var2 != null && var3 != null) {
         double var4 = Math.abs(var3.y - var2.y);
         if (Double.isFinite(var4) && var4 > 1.0E-4) {
            return var4;
         }
      }

      Double var7 = this.resolveActionsGridElementHeight(var1);
      if (var7 != null) {
         double var5 = Math.abs(var7 + this.resolveActionsGridGap(var1));
         if (Double.isFinite(var5) && var5 > 1.0E-4) {
            return var5;
         }

         if (var7 > 1.0E-4) {
            return var7;
         }
      }

      return var2 != null && Double.isFinite(var2.height) && var2.height > 1.0E-4 ? var2.height : 45.0;
   }

   @Override
   protected void clearStaleActionRuntimeShellIds(Player var1, EditorSession var2, LinkedHashSet<String> var3) {
      if (var1 != null && var2 != null && !var2.runtimeActionShellIds.isEmpty()) {
         LinkedHashSet var4 = new LinkedHashSet<>(var2.runtimeActionShellIds);
         var4.removeAll(var3);

         for (Object var6_raw : var4) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            this.removeActionRuntimeShellElement(var1, var6);
            var2.shellRuntimeRects.remove(var6);
         }
      }
   }

   @Override
   protected void removeActionRuntimeShellElement(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = "editor_shell_" + var2;
         this.clearOutlineHud(var1, var3);
         this.removeBaseHud(var1, var3);
         this.removeRoundedParts(var1, var3);
      }
   }

   @Override
   protected void clearActionsRuntimeHud(Player var1) {
      EditorSession var2 = var1 == null ? null : this.editorSessions.get(var1.getUniqueId());
      this.clearActionsRuntimeHud(var1, var2);
   }

   @Override
   protected void clearActionsRuntimeHud(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         if (var2.runtimeActionShellIds.isEmpty()) {
            var2.actionsRenderedRawIndexes.clear();
            this.clearActionsReorderGhostHud(var1, var2);
         } else {
            for (String var5 : new ArrayList<>(var2.runtimeActionShellIds)) {
               this.removeActionRuntimeShellElement(var1, var5);
               var2.shellRuntimeRects.remove(var5);
            }

            var2.runtimeActionShellIds.clear();
            var2.actionsRenderedRawIndexes.clear();
            this.clearActionsReorderGhostHud(var1, var2);
         }
      }
   }

   @Override
   protected void renderActionsReorderGhostHud(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.actionsReorderArmedIndex >= 0) {
         String var7 = this.resolveSelectedActionsTargetId(var2);
         if (var7.isBlank()) {
            this.clearActionsReorderGhostHud(var1, var2);
         } else {
            List var8 = this.resolveTargetActionsRawList(var2, var7, false);
            if (var8 != null && !var8.isEmpty()) {
               int var9 = var2.actionsReorderCurrentIndex >= 0 ? var2.actionsReorderCurrentIndex : var2.actionsReorderArmedIndex;
               if (var9 >= 0 && var9 < var8.size()) {
                  Map var10 = (Map)var8.get(var9);
                  String var11 = this.normalizeActionType(this.stringValue(var10.get("type")));
                  String var12 = this.resolveActionDisplayValue(var10, var11);
                  PropertiesSidebarTimelineManager.ActionRowEntry var13 = new PropertiesSidebarTimelineManager.ActionRowEntry(var9, var11, var12);
                  int var14 = Math.max(var2.actionsRenderedRawIndexes.size(), Math.min(17, var8.size()));
                  if (var14 <= 0) {
                     this.clearActionsReorderGhostHud(var1, var2);
                  } else {
                     EditorRect var15 = this.resolveActionsListRect(var2, var14);
                     if (var15 == null) {
                        this.clearActionsReorderGhostHud(var1, var2);
                     } else {
                        double var16 = Math.max(1.0, this.resolveActionsRowStep(var2));
                        double var18 = Math.max(1.0, var16);
                        double var20 = var5 - var18 / 2.0;
                        double var22 = var15.y - var18 * 0.1;
                        double var24 = var15.maxY() - var18 * 0.9;
                        var20 = Math.max(var22, Math.min(var24, var20));
                        List var26 = this.buildActionsReorderGhostRuntimeBlocks(var2, var13, var15.x, var20);
                        List var27 = this.resolveRenderableBlocksNoCopy(var26, var2.components);
                        HashMap var28 = new HashMap();
                        LinkedHashSet var29 = new LinkedHashSet();
                        int var30 = 0;

                        for (Map var32 : (List<Map>)(List<?>)var27) {
                           var30++;
                           String var33 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var32, "type")), "block"})
                              .toLowerCase(Locale.ROOT);
                           if (this.isRenderableBlockType(var33)) {
                              String var34 = this.resolveElementId(var32, var30, var28);
                              String var35 = this.actionRuntimeLogicalId("actions_drag_runtime_" + var34);
                              if (this.canUseFastLayerRuntimeRender(var32, var33)) {
                                 this.renderLayerRuntimeShellElementFast(var1, var2, var32, var35, var33, false);
                              } else {
                                 ConfigurationSection var36 = this.mapToSection(var32);
                                 if (var36 == null) {
                                    continue;
                                 }

                                 this.renderLayerRuntimeShellElement(var1, var2, var36, var35, var33, false);
                              }

                              var29.add(var35);
                           }
                        }

                        this.clearStaleActionsReorderGhostShellIds(var1, var2, var29);
                        var2.runtimeActionReorderGhostShellIds.clear();
                        var2.runtimeActionReorderGhostShellIds.addAll(var29);
                     }
                  }
               } else {
                  this.clearActionsReorderGhostHud(var1, var2);
               }
            } else {
               this.clearActionsReorderGhostHud(var1, var2);
            }
         }
      } else {
         this.clearActionsReorderGhostHud(var1, var2);
      }
   }

   @Override
   protected List<Map<String, Object>> buildActionsReorderGhostRuntimeBlocks(
      EditorSession var1, PropertiesSidebarTimelineManager.ActionRowEntry var2, double var3, double var5
   ) {
      ArrayList var7 = new ArrayList();
      if (var2 != null) {
         LinkedHashMap var8 = new LinkedHashMap();
         var8.put("id", "action_drag_slot");
         var8.put("slot", "action_drag_slot");
         var8.put("name", this.truncateLayerDisplayNameForDepth(this.firstNonBlank(new String[]{var2.displayValue, "---"}), 0));
         var8.put("name_color", "ffffff");
         var8.put("row_opacity", 0);
         var8.put("box1_icon", "\ue63c");
         var8.put("box2_icon", "\ue63d");
         var8.put(
            "action_icon", this.firstNonBlank(new String[]{ACTION_TYPE_ACTIVE_ICONS.get(var2.actionType), ACTION_TYPE_ACTIVE_ICONS.get("command"), "\uef06"})
         );
         LinkedHashMap var9 = new LinkedHashMap();
         var9.put("component", "editor_action");
         var9.put("params", var8);
         var7.add(var9);
      }

      LinkedHashMap var11 = new LinkedHashMap();
      var11.put("x", var3);
      var11.put("y", var5);
      LinkedHashMap var12 = new LinkedHashMap();
      var12.put("type", "grid_block");
      var12.put("direction", "column");
      var12.put("gap", -51.0);
      var12.put("position", var11);
      var12.put("children", var7);
      ArrayList var10 = new ArrayList();
      var10.add(var12);
      return var10;
   }

   @Override
   protected void clearActionsReorderGhostHud(Player var1) {
      EditorSession var2 = var1 == null ? null : this.editorSessions.get(var1.getUniqueId());
      this.clearActionsReorderGhostHud(var1, var2);
   }

   @Override
   protected void clearActionsReorderGhostHud(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && !var2.runtimeActionReorderGhostShellIds.isEmpty()) {
         for (String var5 : new ArrayList<>(var2.runtimeActionReorderGhostShellIds)) {
            this.removeActionRuntimeShellElement(var1, var5);
            var2.shellRuntimeRects.remove(var5);
         }

         var2.runtimeActionReorderGhostShellIds.clear();
      }
   }

   @Override
   protected void clearStaleActionsReorderGhostShellIds(Player var1, EditorSession var2, LinkedHashSet<String> var3) {
      if (var1 != null && var2 != null && !var2.runtimeActionReorderGhostShellIds.isEmpty()) {
         LinkedHashSet var4 = new LinkedHashSet<>(var2.runtimeActionReorderGhostShellIds);
         var4.removeAll(var3);

         for (Object var6_raw : var4) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            this.removeActionRuntimeShellElement(var1, var6);
            var2.shellRuntimeRects.remove(var6);
         }
      }
   }
}
