package dev.xqedii.ultimateUI.service.gui.editor.shell.managers;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.hud.HudService;
import java.util.Locale;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.entity.Player;

public abstract class EditorMessagingHistoryManager extends PopupManager {
   private static final String EDITOR_MESSAGE_KEEP_MARKER = "saved";
   private static final String EDITOR_MESSAGE_PREFIX_PLAIN = "ultimateui";
   private static final String EDITOR_MESSAGE_PREFIX_BRACKETED = "[ultimateui]";
   private static final String EDITOR_MESSAGE_PREFIX_RAW = "UltimateUI » ";
   private static final Component EDITOR_MESSAGE_PREFIX_COMPONENT = MM.deserialize("<#ffba24><bold>UltimateUI</bold></#ffba24> <#8a989c>»</#8a989c> ");

   protected EditorMessagingHistoryManager(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   @Override
   protected void sendEditorPlayerMessage(Player var1, Component var2) {
      if (var1 != null && var2 != null) {
         String var3 = PlainTextComponentSerializer.plainText().serialize(var2);
         if (!this.shouldSuppressEditorPlayerMessage(var1, var3)) {
            var1.sendMessage(this.withEditorMessagePrefix(var2));
         }
      }
   }

   protected void sendEditorPlayerMessage(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         if (!this.shouldSuppressEditorPlayerMessage(var1, var2)) {
            var1.sendMessage(this.withEditorMessagePrefix(var2));
         }
      }
   }

   protected Component withEditorMessagePrefix(Component var1) {
      if (var1 == null) {
         return Component.empty();
      } else {
         String var2 = PlainTextComponentSerializer.plainText().serialize(var1).trim().toLowerCase(Locale.ROOT);
         return !var2.startsWith("ultimateui") && !var2.startsWith("[ultimateui]") ? EDITOR_MESSAGE_PREFIX_COMPONENT.append(var1) : var1;
      }
   }

   protected String withEditorMessagePrefix(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim();
      if (var2.isEmpty()) {
         return var2;
      } else {
         String var3 = var2.toLowerCase(Locale.ROOT);
         return !var3.startsWith("ultimateui") && !var3.startsWith("[ultimateui]") ? "UltimateUI » " + var2 : var2;
      }
   }

   protected boolean shouldSuppressEditorPlayerMessage(Player var1, String var2) {
      if (var1 == null) {
         return true;
      } else {
         EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
         if (var3 != null && var3.editMode) {
            String var4 = this.firstNonBlank(new String[]{var2}).toLowerCase(Locale.ROOT);
            return !var4.contains("saved");
         } else {
            return false;
         }
      }
   }

   protected void recordEditorMutation(EditorSession var1) {
   }

   protected String captureEditorMutationSnapshot(EditorSession var1) {
      return "";
   }

   protected void recordEditorMutationSnapshot(EditorSession var1, String var2) {
   }

   protected void beginSidebarFieldDragHistory(EditorSession var1) {
      if (var1 != null) {
         if (var1.sidebarFieldDragStartSnapshot != null || var1.sidebarFieldDragMutated) {
            this.finishSidebarFieldDragHistory(var1);
         }

         var1.sidebarFieldDragStartSnapshot = this.captureEditorMutationSnapshot(var1);
         var1.sidebarFieldDragMutated = false;
      }
   }

   protected void finishSidebarFieldDragHistory(EditorSession var1) {
      if (var1 != null) {
         String var2 = var1.sidebarFieldDragStartSnapshot;
         boolean var3 = var1.sidebarFieldDragMutated;
         var1.sidebarFieldDragStartSnapshot = null;
         var1.sidebarFieldDragMutated = false;
         if (var3 && var2 != null && !var2.isBlank()) {
            this.recordEditorMutationSnapshot(var1, var2);
         }
      }
   }

   protected void beginOpacitySliderDragHistory(EditorSession var1) {
      if (var1 != null) {
         if (var1.opacitySliderDragStartSnapshot != null || var1.opacitySliderDragMutated) {
            this.finishOpacitySliderDragHistory(var1);
         }

         var1.opacitySliderDragStartSnapshot = this.captureEditorMutationSnapshot(var1);
         var1.opacitySliderDragMutated = false;
      }
   }

   protected void finishOpacitySliderDragHistory(EditorSession var1) {
      if (var1 != null) {
         String var2 = var1.opacitySliderDragStartSnapshot;
         boolean var3 = var1.opacitySliderDragMutated;
         var1.opacitySliderDragStartSnapshot = null;
         var1.opacitySliderDragMutated = false;
         if (var3 && var2 != null && !var2.isBlank()) {
            this.recordEditorMutationSnapshot(var1, var2);
         }
      }
   }

   protected void stopOpacitySliderDrag(EditorSession var1, boolean var2) {
      if (var1 != null) {
         if (var2) {
            this.finishOpacitySliderDragHistory(var1);
         } else {
            var1.opacitySliderDragStartSnapshot = null;
            var1.opacitySliderDragMutated = false;
         }

         var1.opacitySliderDragActive = false;
         var1.opacitySliderLastValue = null;
         var1.sidebarOpacityRefreshTriggered = false;
      }
   }

   protected void stopAnimationTimelineSliderDrag(EditorSession var1) {
      if (var1 != null) {
         var1.animationTimelineSliderDragActive = false;
         var1.animationTimelineSliderDragTick = null;
      }
   }

   protected void stopAnimationTimelineKeyframeDrag(EditorSession var1, boolean var2) {
      if (var1 != null) {
         if (var2) {
            String var3 = var1.animationTimelineKeyframeDragStartSnapshot;
            boolean var4 = var1.animationTimelineKeyframeDragMutated;
            if (var4 && var3 != null && !var3.isBlank()) {
               this.recordEditorMutationSnapshot(var1, var3);
            }
         }

         var1.animationTimelineKeyframeDragActive = false;
         var1.animationTimelineRenderedSignature = null;
         var1.animationTimelineKeyframeDragRuntimeTick = -1;
         var1.animationTimelineKeyframeDragRuntimeTickByLogicalTick.clear();
         var1.animationTimelineKeyframeDragRuntimeTickByRef.clear();
         var1.animationTimelineKeyframeDragStartSnapshot = null;
         var1.animationTimelineKeyframeDragMutated = false;
         var1.animationTimelineKeyframeDragStartHitX = Double.NaN;
         var1.animationTimelineKeyframeDragHitOffsetX = 0.0;
      }
   }

   protected void clearAnimationTimelineSelectedKeyframe(EditorSession var1) {
      if (var1 != null) {
         var1.animationTimelineSelectedTargetId = null;
         var1.animationTimelineSelectedRow = null;
         var1.animationTimelineSelectedTick = -1;
         this.clearAnimationTimelineAdditionalSelection(var1);
         var1.animationTimelineKeyframeDragRuntimeTick = -1;
         var1.animationTimelineKeyframeDragRuntimeTickByLogicalTick.clear();
         var1.animationTimelineKeyframeDragRuntimeTickByRef.clear();
      }
   }

   protected void clearAnimationTimelineAdditionalSelection(EditorSession var1) {
      if (var1 != null) {
         var1.animationTimelineAdditionalSelectedTicks.clear();
         var1.animationTimelineAdditionalSelectedKeyframeRefs.clear();
      }
   }

   protected void recordEditorMutationForSidebarInteraction(EditorSession var1) {
      if (var1 != null) {
         if (var1.sidebarFieldDragActive) {
            var1.sidebarFieldDragMutated = true;
         } else if (var1.opacitySliderDragActive) {
            var1.opacitySliderDragMutated = true;
         } else {
            this.recordEditorMutation(var1);
         }
      }
   }
}
