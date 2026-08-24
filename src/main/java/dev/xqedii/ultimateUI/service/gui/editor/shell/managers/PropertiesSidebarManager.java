package dev.xqedii.ultimateUI.service.gui.editor.shell.managers;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.gui.model.HoverElement;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.editor.shell.rendering.EditorHudRenderer;
import dev.xqedii.ultimateUI.service.gui.model.EditorPropertyField;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.gui.model.EditorSidebarTab;
import dev.xqedii.ultimateUI.service.hud.HudService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay.TextAlignment;

public abstract class PropertiesSidebarManager extends PropertiesSidebarTimelineManager {
   private static final String RUNTIME_HUD_STACK_PAGE_META_KEY = "xqgui_runtime_hud_stack_page";
   private static final String RUNTIME_ANIMATION_SCOPE_PREFIX = "__uui_scope__[";
   private static final String RUNTIME_ANIMATION_SCOPE_MARKER = "]::";

   protected PropertiesSidebarManager(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   @Override
   protected void updateEditorPropertiesSidebar(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         this.beginShellOpacityBatch(var2);

         try {
            this.syncAnimationTimelineUi(var1, var2);
            boolean var3 = this.hasLegacySidebarUi(var2);
            if (var3 && var2.rightSidebarTab == EditorSidebarTab.LAYERS) {
               this.clearActionsRuntimeHud(var1, var2);
               this.setShellOpacity(var1, "layers_box", 255);
               this.updateSidebarSelectionPanelVisibility(var1, var2, false);
               this.renderLayersPanel(var1, var2);
               return;
            }

            if (var3 && var2.rightSidebarTab == EditorSidebarTab.PROPERTIES && this.isActionsSidebarMode(var2)) {
               this.clearLayersRuntimeHud(var1, var2);
               this.setShellOpacity(var1, "layers_box", 0);
               this.renderActionsPanel(var1, var2);
               return;
            }

            if (!var3 || var2.rightSidebarTab != EditorSidebarTab.PROPERTIES || !this.isKeyframePropertiesSidebarMode(var2)) {
               if (!var3 || var2.rightSidebarTab != EditorSidebarTab.DESIGN) {
                  this.clearActionsRuntimeHud(var1, var2);
                  this.clearLayersRuntimeHud(var1);
                  this.setShellOpacity(var1, "layers_box", 0);
                  List var4 = this.getSelectedTargetIds(var2);
                  boolean var5 = !var4.isEmpty();
                  if (var5) {
                     String var6 = "---";
                     String var7 = "---";
                     String var8 = "---";
                     String var9 = "---";
                     String var10 = "---";
                     String var11 = "---";
                     String var12 = "---";
                     String var13 = "---";
                     String var14 = "---";
                     String var15 = "---";
                     String var16 = "---";
                     String var17 = "---";
                     String var18 = "---";
                     double var19 = 0.0;
                     String var21 = null;
                     boolean var22 = false;
                     Double var23 = null;
                     Double var24 = null;
                     Double var25 = null;
                     Double var26 = null;
                     Double var27 = null;
                     Double var28 = null;
                     String var29 = null;
                     Double var30 = null;
                     String var31 = null;
                     Boolean var32 = null;
                     Boolean var33 = null;
                     TextAlignment var34 = null;
                     boolean var35 = false;
                     boolean var36 = false;
                     boolean var37 = false;
                     boolean var38 = false;
                     boolean var39 = false;
                     boolean var40 = false;
                     boolean var41 = false;
                     boolean var42 = false;
                     boolean var43 = false;
                     boolean var44 = false;
                     boolean var45 = false;
                     boolean var46 = false;
                     int var47 = 0;

                     for (Object var49_raw : var4) {
                        String var49 = var49_raw != null ? var49_raw.toString() : null;
                        HoverElement var50 = this.findFirstByTargetId(var2, var49);
                        if (var50 != null) {
                           String var51 = var50.type;
                           if (var21 == null) {
                              var21 = var51;
                           } else if (!this.equalsNullable(var21, var51)) {
                              var22 = true;
                           }
                        }

                        EditorRect var65 = this.resolveSidebarEditableBounds(var2, var49);
                        if (var65 != null) {
                           var47++;
                           double var52 = this.toSidebarDisplayPositionValue(var2, var49, EditorPropertyField.POSITION_X, var65.x);
                           double var54 = this.toSidebarDisplayPositionValue(var2, var49, EditorPropertyField.POSITION_Y, var65.y);
                           if (var23 == null) {
                              var23 = var65.width;
                           } else if (Math.abs(var23 - var65.width) > 1.0E-4) {
                              var35 = true;
                           }

                           if (var24 == null) {
                              var24 = var65.height;
                           } else if (Math.abs(var24 - var65.height) > 1.0E-4) {
                              var36 = true;
                           }

                           if (var25 == null) {
                              var25 = var52;
                           } else if (Math.abs(var25 - var52) > 1.0E-4) {
                              var37 = true;
                           }

                           if (var26 == null) {
                              var26 = var54;
                           } else if (Math.abs(var26 - var54) > 1.0E-4) {
                              var38 = true;
                           }
                        }

                        double var66 = this.readTargetRotation(var2, var49);
                        if (var27 == null) {
                           var27 = var66;
                        } else if (Math.abs(var27 - var66) > 1.0E-4) {
                           var39 = true;
                        }

                        int var67 = this.readTargetOpacity(var2, var49);
                        if (var28 == null) {
                           var28 = (double)var67;
                        } else if (Math.abs(var28 - (double)var67) > 1.0E-4) {
                           var40 = true;
                        }

                        String var55 = this.readTargetName(var2, var49);
                        if (var29 == null) {
                           var29 = var55;
                        } else if (!this.equalsNullable(var29, var55)) {
                           var41 = true;
                        }

                        double var56 = this.readTargetLayer(var2, var49);
                        if (var30 == null) {
                           var30 = var56;
                        } else if (Math.abs(var30 - var56) > 1.0E-4) {
                           var42 = true;
                        }

                        String var58 = this.readTargetLogicalId(var2, var49);
                        if (var31 == null) {
                           var31 = var58;
                        } else if (!this.equalsNullable(var31, var58)) {
                           var43 = true;
                        }

                        boolean var59 = this.isTargetVisible(var2, var49);
                        if (var32 == null) {
                           var32 = var59;
                        } else if (var32 != var59) {
                           var44 = true;
                        }

                        boolean var60 = this.isTargetLocked(var2, var49);
                        if (var33 == null) {
                           var33 = var60;
                        } else if (var33 != var60) {
                           var45 = true;
                        }

                        TextAlignment var61 = this.readTargetHudAlignment(var2, var49);
                        if (var34 == null) {
                           var34 = var61;
                        } else if (var34 != var61) {
                           var46 = true;
                        }
                     }

                     if (var4.size() > 1) {
                        var6 = "Mixed";
                     } else if (!var22 && var21 != null) {
                        var6 = this.toSidebarTypeLabel(var21);
                     } else if (var22) {
                        var6 = "Mixed";
                     }

                     if (var47 > 0) {
                        var7 = var35 ? "Mixed" : this.formatPx(var23 == null ? 0.0 : var23);
                        var8 = var36 ? "Mixed" : this.formatPx(var24 == null ? 0.0 : var24);
                        var9 = var37 ? "Mixed" : this.formatPx(var25 == null ? 0.0 : var25);
                        var10 = var38 ? "Mixed" : this.formatPx(var26 == null ? 0.0 : var26);
                     }

                     if (var27 != null) {
                        var11 = var39 ? "Mixed" : String.format(Locale.ROOT, "%.0f°", var27);
                     }

                     if (var28 != null) {
                        var19 = var28;
                        var12 = var40 ? "Mixed" : Math.round(this.opacityRawToPercent(var28)) + "%";
                     }

                     if (var29 != null && !var29.isBlank()) {
                        var13 = var41 ? "Mixed" : var29;
                     }

                     if (var30 != null) {
                        var14 = var42 ? "Mixed" : this.formatLayerValue(var30);
                     }

                     if (var31 != null && !var31.isBlank()) {
                        var15 = var43 ? "Mixed" : var31;
                     }

                     if (var32 != null) {
                        var16 = var44 ? "Mixed" : (var32 ? "Visible" : "Invisible");
                     }

                     if (var33 != null) {
                        var17 = var45 ? "Mixed" : (var33 ? "True" : "False");
                     }

                     if (var34 != null) {
                        var18 = var46 ? "Mixed" : this.formatSidebarHudAlignment(var34);
                     }

                     this.setShellText(var1, "properties_selected_type", this.toSidebarDisplayValue(var6));
                     this.setShellText(var1, "prop_w_value", this.toSidebarDisplayValue(var7));
                     this.setShellText(var1, "prop_h_value", this.toSidebarDisplayValue(var8));
                     this.setShellText(var1, "prop_x_value", this.toSidebarDisplayValue(var9));
                     this.setShellText(var1, "prop_y_value", this.toSidebarDisplayValue(var10));
                     this.setShellText(var1, "prop_r_value", this.toSidebarDisplayValue(var11));
                     this.setShellText(var1, "prop_opacity_value", this.toSidebarDisplayValue(var12));
                     this.setSidebarInfoValues(var1, var9, var10, var7, var8, var11, var13, var14, var15, var16, var17, var18);
                     this.applySidebarPendingInputIndicator(var1, var2);
                     this.updateSidebarOpacitySliderVisual(var1, var2, var19);
                     this.updateSidebarSelectionPanelVisibility(var1, var2, var5);
                     return;
                  }

                  this.setShellText(var1, "properties_selected_type", "---");
                  this.setShellText(var1, "prop_w_value", "---");
                  this.setShellText(var1, "prop_h_value", "---");
                  this.setShellText(var1, "prop_x_value", "---");
                  this.setShellText(var1, "prop_y_value", "---");
                  this.setShellText(var1, "prop_r_value", "---");
                  this.setShellText(var1, "prop_opacity_value", "---");
                  this.setSidebarInfoValues(var1, "---", "---", "---", "---", "---", "---", "---", "---", "---", "---", "---");
                  this.updateSidebarSelectionPanelVisibility(var1, var2, false);
                  return;
               }

               this.clearActionsRuntimeHud(var1, var2);
               this.clearLayersRuntimeHud(var1);
               this.setShellOpacity(var1, "layers_box", 0);
               this.updateEditorDesignSidebar(var1, var2);
               this.applySidebarPendingInputIndicator(var1, var2);
               return;
            }

            this.clearActionsRuntimeHud(var1, var2);
            this.clearLayersRuntimeHud(var1, var2);
            this.setShellOpacity(var1, "layers_box", 0);
            this.updateEditorKeyframeSidebar(var1, var2);
            this.applySidebarPendingInputIndicator(var1, var2);
         } finally {
            this.endShellOpacityBatch(var1, var2);
         }
      }
   }

   @Override
   protected void updateEditorKeyframeSidebar(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         String var3 = "---";
         String var4 = "---";
         String var5 = "---";
         String var6 = "---";
         String var7 = "---";
         String var8 = "---";
         String var9 = "---";
         String var10 = "Linear";
         if (this.isAnimationTimelineKeyframeSelected(var2)) {
            String var11 = this.firstNonBlank(new String[]{var2.animationTimelineSelectedTargetId});
            String var12 = this.firstNonBlank(new String[]{var2.animationTimelineSelectedRow}).toLowerCase(Locale.ROOT);
            int var13 = this.clampAnimationTimelineTick(var2.animationTimelineSelectedTick);
            Map var14 = this.resolveRawTargetByTargetId(var2, var11);
            Map var15 = this.readAnimationTimelineRowMap(var14, var12);
            Object var16 = this.readAnimationTimelineTickValue(var15, var13);
            Map var17 = var16 instanceof Map var18 ? var18 : null;
            var10 = this.formatAnimationTimelineInterpolationMode(this.resolveAnimationTimelineKeyframeInterpolationMode(var15, var13));
            if ("position".equals(var12)) {
               var3 = "\ue63e";
               var4 = "X position";
               var5 = "Y position";
               if (var17 != null) {
                  double var22 = this.readMapPathDouble(var17, "x", Double.NaN);
                  double var20 = this.readMapPathDouble(var17, "y", Double.NaN);
                  if (Double.isFinite(var22)) {
                     var22 = this.toSidebarDisplayPositionValue(var2, var11, EditorPropertyField.POSITION_X, var22);
                  }

                  if (Double.isFinite(var20)) {
                     var20 = this.toSidebarDisplayPositionValue(var2, var11, EditorPropertyField.POSITION_Y, var20);
                  }

                  var6 = Double.isFinite(var22) ? this.formatPx(var22) : "---";
                  var7 = Double.isFinite(var20) ? this.formatPx(var20) : "---";
               }
            } else if ("scale".equals(var12)) {
               var3 = "\ue640";
               var4 = "Width";
               var5 = "Height";
               if (var17 != null) {
                  double var23 = this.readMapPathDouble(var17, "width", Double.NaN);
                  double var26 = this.readMapPathDouble(var17, "height", Double.NaN);
                  var6 = Double.isFinite(var23) ? this.formatPx(var23) : "---";
                  var7 = Double.isFinite(var26) ? this.formatPx(var26) : "---";
               }
            } else if ("rotation".equals(var12)) {
               var3 = "\ue641";
               var4 = "Rotation";
               var5 = "---";
               double var24 = var16 instanceof Number var27
                  ? var27.doubleValue()
                  : this.readMapPathDouble(var17, "value", this.readMapPathDouble(var17, "rotation", Double.NaN));
               var8 = Double.isFinite(var24) ? String.format(Locale.ROOT, "%.0f°", this.normalizeSidebarRotation(var24)) : "---";
            } else if ("opacity".equals(var12)) {
               var3 = "\ue642";
               var4 = "Opacity";
               var5 = "---";
               double var25 = var16 instanceof Number var28
                  ? var28.doubleValue()
                  : this.readMapPathDouble(var17, "value", this.readMapPathDouble(var17, "opacity", Double.NaN));
               var9 = Double.isFinite(var25)
                  ? Math.round(this.opacityRawToAnimationPercent((double)this.clampAnimationTimelineOpacityRaw(var25))) + "%"
                  : "---";
            }
         }

         this.setShellText(var1, "keyframe_properties_type", this.toSidebarDisplayValue(var3));
         this.setShellText(var1, "keyframe_x_label", this.toSidebarDisplayValue(var4));
         this.setShellText(var1, "keyframe_y_label", this.toSidebarDisplayValue(var5));
         this.setShellText(var1, "editor_val_keyframe_x", this.toSidebarDisplayValue(var6));
         this.setShellText(var1, "editor_val_keyframe_y", this.toSidebarDisplayValue(var7));
         this.setShellText(var1, "keyframe_x", this.toSidebarDisplayValue(var6));
         this.setShellText(var1, "keyframe_y", this.toSidebarDisplayValue(var7));
         this.setShellText(var1, "editor_val_keyframe_rotation", this.toSidebarDisplayValue(var8));
         this.setShellText(var1, "keyframe_rotation", this.toSidebarDisplayValue(var8));
         this.setShellText(var1, "editor_val_keyframe_opacity", this.toSidebarDisplayValue(var9));
         this.setShellText(var1, "keyframe_opacity", this.toSidebarDisplayValue(var9));
         this.setShellText(var1, "keyframe_interpolation_label", this.toSidebarDisplayValue("Interpolation"));
         this.setShellText(var1, "editor_val_keyframe_interpolation", this.toSidebarDisplayValue(var10));
         this.setShellText(var1, "keyframe_interpolation", this.toSidebarDisplayValue(var10));
         this.setShellText(var1, "editor_val_keyframe_interpolation_rotation", this.toSidebarDisplayValue(var10));
         this.setShellText(var1, "keyframe_interpolation_rotation", this.toSidebarDisplayValue(var10));
         this.setShellText(var1, "editor_val_keyframe_interpolation_opacity", this.toSidebarDisplayValue(var10));
         this.setShellText(var1, "keyframe_interpolation_opacity", this.toSidebarDisplayValue(var10));
      }
   }

   protected void setSidebarInfoValues(
      Player var1,
      String var2,
      String var3,
      String var4,
      String var5,
      String var6,
      String var7,
      String var8,
      String var9,
      String var10,
      String var11,
      String var12
   ) {
      this.setShellText(var1, "editor_val_x", this.toSidebarDisplayValue(var2));
      this.setShellText(var1, "editor_val_y", this.toSidebarDisplayValue(var3));
      this.setShellText(var1, "editor_val_width", this.toSidebarDisplayValue(var4));
      this.setShellText(var1, "editor_val_height", this.toSidebarDisplayValue(var5));
      this.setShellText(var1, "editor_val_rotation", this.toSidebarDisplayValue(var6));
      this.setShellText(var1, "editor_val_name", this.toSidebarDisplayValue(var7));
      this.setShellText(var1, "editor_val_layer", this.toSidebarDisplayValue(var8));
      this.setShellText(var1, "editor_val_id", this.toSidebarDisplayValue(var9));
      this.setShellText(var1, "editor_val_visible", this.toSidebarDisplayValue(var10));
      this.setShellText(var1, "editor_val_locked", this.toSidebarDisplayValue(var11));
      this.setShellText(var1, "editor_val_anchor", this.toSidebarDisplayValue(var12));
   }

   @Override
   protected void updateEditorDesignSidebar(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         this.syncDesignSidebarPanelVisibility(var1, var2);
         if (this.isItemDesignSidebarMode(var2)) {
            this.syncDesignTextAlignControlsVisibility(var1, var2, false);
            this.updateEditorItemDesignSidebar(var1, var2);
         } else {
            boolean var3 = this.isTextDesignSidebarMode(var2);
            this.syncDesignTextAlignControlsVisibility(var1, var2, var3);
            List var4 = this.getSelectedTargetIds(var2);
            if (var4.isEmpty()) {
               this.setDesignSidebarValues(var1, "---", "---", "---", "---", "---", "---", "---");
               this.setDesignTextAlignSidebarValue(var1, "---");
               this.setDesignTextWrapSidebarValue(var1, "---");
            } else {
               String var5 = null;
               String var6 = null;
               Integer var7 = null;
               String var8 = null;
               String var9 = null;
               String var10 = null;
               String var11 = null;
               boolean var12 = false;
               boolean var13 = false;
               boolean var14 = false;
               boolean var15 = false;
               boolean var16 = false;
               boolean var17 = false;
               boolean var18 = false;

               for (Object var20_raw : var4) {
                  String var20 = var20_raw != null ? var20_raw.toString() : null;
                  String var21 = this.formatSidebarHexValue(this.readTargetColor(var2, var20));
                  if (var5 == null) {
                     var5 = var21;
                  } else if (!this.equalsNullable(var5, var21)) {
                     var12 = true;
                  }

                  String var22 = this.readTargetRadiusLabel(var2, var20);
                  String var23 = "None".equals(var22) ? "Solid" : "Rounded";
                  if (var6 == null) {
                     var6 = var23;
                  } else if (!this.equalsNullable(var6, var23)) {
                     var13 = true;
                  }

                  int var24 = this.readTargetOpacity(var2, var20);
                  if (var7 == null) {
                     var7 = var24;
                  } else if (var7 != var24) {
                     var14 = true;
                  }

                  if (var8 == null) {
                     var8 = var22;
                  } else if (!this.equalsNullable(var8, var22)) {
                     var15 = true;
                  }

                  String var25 = this.readTargetOutlineSidebarValue(var2, var20);
                  if (var9 == null) {
                     var9 = var25;
                  } else if (!this.equalsNullable(var9, var25)) {
                     var16 = true;
                  }

                  String var26 = this.formatSidebarHoverColorValue(this.readTargetHoverColor(var2, var20));
                  if (var10 == null) {
                     var10 = var26;
                  } else if (!this.equalsNullable(var10, var26)) {
                     var17 = true;
                  }

                  String var27 = this.formatSidebarHoverEffectValue(this.readTargetHoverEffect(var2, var20));
                  if (var11 == null) {
                     var11 = var27;
                  } else if (!this.equalsNullable(var11, var27)) {
                     var18 = true;
                  }
               }

               String var36 = var12 ? "Mixed" : this.firstNonBlank(new String[]{var5, "---"});
               String var37 = var13 ? "Mixed" : this.firstNonBlank(new String[]{var6, "---"});
               String var38;
               if (var14) {
                  var38 = "Mixed";
               } else if (var7 == null) {
                  var38 = "---";
               } else {
                  var38 = Math.round(this.opacityRawToPercent((double)var7.intValue())) + "%";
               }

               String var39 = var15 ? "Mixed" : this.firstNonBlank(new String[]{var8, "None"});
               String var40 = var16 ? "Mixed" : this.firstNonBlank(new String[]{var9, "---"});
               String var41 = var17 ? "Mixed" : this.firstNonBlank(new String[]{var10, "None"});
               String var42 = var18 ? "Mixed" : this.firstNonBlank(new String[]{var11, "None"});
               String var43 = "---";
               String var44 = "---";
               if (var3) {
                  TextAlignment var28 = null;
                  boolean var29 = false;
                  Integer var30 = null;
                  boolean var31 = false;

                  for (Object var33_raw : var4) {
                     String var33 = var33_raw != null ? var33_raw.toString() : null;
                     if (this.isSidebarTextDesignTarget(var2, var33)) {
                        TextAlignment var34 = this.readTargetTextAlignment(var2, var33);
                        if (var28 == null) {
                           var28 = var34;
                        } else if (var28 != var34) {
                           var29 = true;
                        }

                        int var35 = this.readTargetTextWrapLineWidth(var2, var33);
                        if (var30 == null) {
                           var30 = var35;
                        } else if (var30 != var35) {
                           var31 = true;
                        }
                     }
                  }

                  if (var29) {
                     var43 = "Mixed";
                  } else {
                     var43 = this.formatSidebarTextAlignment(var28);
                  }

                  if (var31) {
                     var44 = "Mixed";
                  } else if (var30 != null) {
                     var44 = String.valueOf(var30);
                  }
               }

               this.setDesignSidebarValues(var1, var36, var37, var38, var39, var40, var41, var42);
               this.setDesignTextAlignSidebarValue(var1, var43);
               this.setDesignTextWrapSidebarValue(var1, var44);
            }
         }
      }
   }

   protected void updateEditorItemDesignSidebar(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         List var3 = this.getSelectedTargetIds(var2);
         if (var3.isEmpty()) {
            this.setItemDesignSidebarValues(var1, "---", "---", "---");
         } else {
            String var4 = null;
            Boolean var5 = null;
            String var6 = null;
            boolean var7 = false;
            boolean var8 = false;
            boolean var9 = false;

            for (Object var11_raw : var3) {
               String var11 = var11_raw != null ? var11_raw.toString() : null;
               if (!this.isSidebarItemDesignTarget(var2, var11)) {
                  var7 = true;
                  var8 = true;
                  var9 = true;
               } else {
                  String var12 = this.firstNonBlank(new String[]{this.readTargetItemToken(var2, var11), "---"});
                  if (var4 == null) {
                     var4 = var12;
                  } else if (!this.equalsNullable(var4, var12)) {
                     var7 = true;
                  }

                  boolean var13 = this.readTargetItemGlowing(var2, var11);
                  if (var5 == null) {
                     var5 = var13;
                  } else if (var5 != var13) {
                     var8 = true;
                  }

                  String var14 = this.formatSidebarHoverEffectValue(this.readTargetHoverEffect(var2, var11));
                  if (var6 == null) {
                     var6 = var14;
                  } else if (!this.equalsNullable(var6, var14)) {
                     var9 = true;
                  }
               }
            }

            String var15 = var7 ? "Mixed" : this.firstNonBlank(new String[]{var4, "---"});
            String var16;
            if (var8) {
               var16 = "Mixed";
            } else if (var5 == null) {
               var16 = "---";
            } else {
               var16 = var5 ? "Enabled" : "Disabled";
            }

            String var17 = var9 ? "Mixed" : this.firstNonBlank(new String[]{var6, "None"});
            this.setItemDesignSidebarValues(var1, var15, var16, var17);
         }
      }
   }

   protected void syncDesignSidebarPanelVisibility(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.rightSidebarTab == EditorSidebarTab.DESIGN) {
         String var3 = this.resolveDesignTabPanelId(var2);
         this.setSidebarPanelVisible(var1, var2, "design", "design".equals(var3));
         this.setSidebarPanelVisible(var1, var2, "item_design", "item_design".equals(var3));
      }
   }

   protected String formatSidebarHexValue(String var1) {
      String var2 = this.normalizeHexColor(var1);
      return var2.isBlank() ? "---" : "#" + var2.toUpperCase(Locale.ROOT);
   }

   protected String formatSidebarHoverColorValue(String var1) {
      String var2 = this.normalizeHoverColorSettingToken(var1);
      if ("none".equalsIgnoreCase(var2)) {
         return "None";
      } else {
         return "auto".equalsIgnoreCase(var2) ? "Auto" : "#" + var2.toUpperCase(Locale.ROOT);
      }
   }

   protected String normalizeHoverColorSettingToken(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim();
      if (var2.isBlank()) {
         return "auto";
      } else if (var2.equalsIgnoreCase("none") || var2.equalsIgnoreCase("None")) {
         return "none";
      } else if (!var2.equalsIgnoreCase("auto") && !var2.equalsIgnoreCase("Auto")) {
         String var3 = this.normalizeHexColor(var2);
         return var3.isBlank() ? "auto" : var3;
      } else {
         return "auto";
      }
   }

   protected String formatSidebarHoverEffectValue(String var1) {
      String var2 = this.normalizeHoverEffectId(var1);
      if (!var2.isBlank() && !"none".equals(var2)) {
         YamlConfiguration var3 = this.resolveHoverEffectConfiguration(var2);
         String var4 = this.firstNonBlank(new String[]{var3 == null ? null : var3.getString("name"), var3 == null ? null : var3.getString("display"), var2});
         String[] var5 = var4.replace('-', ' ').replace('_', ' ').trim().split("\\s+");
         StringBuilder var6 = new StringBuilder();

         for (Object var10_raw : var5) {
            String var10 = var10_raw != null ? var10_raw.toString() : null;
            if (var10 != null && !var10.isBlank()) {
               if (!var6.isEmpty()) {
                  var6.append(' ');
               }

               String var11 = var10.toLowerCase(Locale.ROOT);
               var6.append(Character.toUpperCase(var11.charAt(0))).append(var11.substring(1));
            }
         }

         return var6.isEmpty() ? "None" : var6.toString();
      } else {
         return "None";
      }
   }

   protected void setDesignSidebarValues(Player var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8) {
      this.setShellText(var1, "editor_val_design_fill_color", this.toSidebarDisplayValue(this.firstNonBlank(new String[]{var2, "---"})));
      this.setShellText(var1, "editor_val_design_fill_style", this.toSidebarDisplayValue(this.firstNonBlank(new String[]{var3, "---"})));
      this.setShellText(var1, "editor_val_design_fill_opacity", this.toSidebarDisplayValue(this.firstNonBlank(new String[]{var4, "---"})));
      this.setShellText(var1, "editor_val_design_border_radius", this.toSidebarDisplayValue(this.firstNonBlank(new String[]{var5, "---"})));
      this.setShellText(var1, "editor_val_design_border_color", this.toSidebarDisplayValue(this.firstNonBlank(new String[]{var6, "---"})));
      this.setShellText(var1, "editor_val_design_hover_color", this.toSidebarDisplayValue(this.firstNonBlank(new String[]{var7, "---"})));
      this.setShellText(var1, "editor_val_design_hover_effect", this.toSidebarDisplayValue(this.firstNonBlank(new String[]{var8, "None"})));
   }

   protected void setItemDesignSidebarValues(Player var1, String var2, String var3, String var4) {
      this.setShellText(var1, "editor_val_item_design_item", this.toSidebarDisplayValue(this.firstNonBlank(new String[]{var2, "---"})));
      this.setShellText(var1, "editor_val_item_design_glowing", this.toSidebarDisplayValue(this.firstNonBlank(new String[]{var3, "---"})));
      this.setShellText(var1, "editor_val_item_design_hovereffect", this.toSidebarDisplayValue(this.firstNonBlank(new String[]{var4, "None"})));
   }

   protected void setDesignTextAlignSidebarValue(Player var1, String var2) {
      this.setShellText(var1, "editor_val_design_text_align", this.toSidebarDisplayValue(this.firstNonBlank(new String[]{var2, "---"})));
   }

   protected void setDesignTextWrapSidebarValue(Player var1, String var2) {
      this.setShellText(var1, "editor_val_design_text_wrapping", this.toSidebarDisplayValue(this.firstNonBlank(new String[]{var2, "---"})));
   }

   @Override
   protected void syncDesignTextAlignControlsVisibility(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         this.beginShellOpacityBatch(var2);

         try {
            this.setSidebarPanelVisible(var1, var2, "text_settings", var3);
         } finally {
            this.endShellOpacityBatch(var1, var2);
         }
      }
   }

   @Override
   protected String resolveDesignTextSettingsInvocationPath(EditorSession var1) {
      if (var1 == null) {
         return "";
      } else {
         ConfigurationSection var2 = this.findShellBlockSection(var1, "text_settings");
         String var3 = this.normalizeComponentInvocationPath(this.firstNonBlank(new String[]{var2 == null ? null : var2.getString("__editor_target_path")}));
         if (!var3.isBlank()) {
            return var3;
         } else {
            ConfigurationSection var4 = this.findShellBlockSection(var1, "editor_val_design_text_align");
            return this.normalizeComponentInvocationPath(this.firstNonBlank(new String[]{var4 == null ? null : var4.getString("__editor_target_path")}));
         }
      }
   }

   protected void setShellBlockTreeVisibility(Player var1, EditorSession var2, String var3, boolean var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         ConfigurationSection var5 = this.findShellBlockSection(var2, var3);
         if (var5 == null) {
            this.setShellOpacity(var1, var3, var4 ? 255 : 0);
         } else {
            String var6 = this.firstNonBlank(new String[]{var5.getString("__editor_target_path")});
            var6 = this.normalizeComponentInvocationPath(var6);
            if (var6.isBlank() && "text_settings".equals(var3)) {
               var6 = this.resolveDesignTextSettingsInvocationPath(var2);
            }

            if (var6.isBlank()) {
               this.setShellOpacity(var1, var3, var4 ? this.readOpacity(var5, 255) : 0);
            } else {
               HashMap var7 = new HashMap();
               boolean var8 = false;
               int var9 = 0;

               for (Map var11 : var2.shellBlocks) {
                  var9++;
                  ConfigurationSection var12 = this.mapToSection(var11);
                  if (var12 != null) {
                     String var13 = this.firstNonBlank(new String[]{var12.getString("__editor_target_path")});
                     if (this.belongsToSidebarPanel(var6, var13)) {
                        String var14 = this.resolveElementId(var12, var9, var7);
                        if (!var14.isBlank()) {
                           var8 = true;
                           int var15 = var4 ? this.readOpacity(var12, 255) : 0;
                           this.setShellOpacity(var1, var14, var15);
                        }
                     }
                  }
               }

               if (!var8) {
                  this.setShellOpacity(var1, var3, var4 ? this.readOpacity(var5, 255) : 0);
               }
            }
         }
      }
   }

   protected String normalizeComponentInvocationPath(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1});
      if (var2.isBlank()) {
         return "";
      } else {
         int var3 = var2.indexOf(".component.");
         if (var3 < 0 && var2.endsWith(".component")) {
            var3 = var2.length() - ".component".length();
         }

         return var3 >= 0 ? this.firstNonBlank(new String[]{var2.substring(0, var3)}) : var2;
      }
   }

   protected String toSidebarDisplayValue(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1, "---"});
      if (var2.length() <= 18) {
         return var2;
      } else {
         int var3 = Math.max(0, Math.min(17, var2.length()));
         return var2.substring(0, var3) + "...";
      }
   }

   protected void applySidebarPendingInputIndicator(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.pendingPropertyField != null) {
         String var3 = this.toSidebarDisplayValue("...");
         switch (var2.pendingPropertyField) {
            case NAME:
               this.setSidebarPendingValue(var1, var3, "editor_val_name");
               break;
            case ID:
               this.setSidebarPendingValue(var1, var3, "editor_val_id");
               break;
            case VISIBLE:
               this.setSidebarPendingValue(var1, var3, "editor_val_visible");
               break;
            case LOCKED:
               this.setSidebarPendingValue(var1, var3, "editor_val_locked");
               break;
            case ANCHOR:
               this.setSidebarPendingValue(var1, var3, "editor_val_anchor");
               break;
            case WIDTH:
               this.setSidebarPendingValue(var1, var3, "prop_w_value", "editor_val_width");
               break;
            case HEIGHT:
               this.setSidebarPendingValue(var1, var3, "prop_h_value", "editor_val_height");
               break;
            case POSITION_X:
               this.setSidebarPendingValue(var1, var3, "prop_x_value", "editor_val_x", "editor_val_keyframe_x", "keyframe_x");
               break;
            case POSITION_Y:
               this.setSidebarPendingValue(var1, var3, "prop_y_value", "editor_val_y", "editor_val_keyframe_y", "keyframe_y");
               break;
            case ROTATION:
               this.setSidebarPendingValue(var1, var3, "prop_r_value", "editor_val_rotation", "editor_val_keyframe_rotation", "keyframe_rotation");
               break;
            case LAYER:
               this.setSidebarPendingValue(var1, var3, "editor_val_layer");
               break;
            case OPACITY:
               this.setSidebarPendingValue(
                  var1, var3, "prop_opacity_value", "editor_val_design_fill_opacity", "editor_val_keyframe_opacity", "keyframe_opacity"
               );
               break;
            case DESIGN_COLOR:
               this.setSidebarPendingValue(var1, var3, "editor_val_design_fill_color");
               break;
            case DESIGN_BORDER_COLOR:
               this.setSidebarPendingValue(var1, var3, "editor_val_design_border_color");
               break;
            case DESIGN_HOVER_COLOR:
               this.setSidebarPendingValue(var1, var3, "editor_val_design_hover_color");
               break;
            case DESIGN_TEXT_WRAP:
               this.setSidebarPendingValue(var1, var3, "editor_val_design_text_wrapping");
               break;
            case ITEM_DESIGN_ITEM:
               this.setSidebarPendingValue(var1, var3, "editor_val_item_design_item");
               break;
            case ITEM_DESIGN_GLOWING:
               this.setSidebarPendingValue(var1, var3, "editor_val_item_design_glowing");
         }
      }
   }

   protected void setSidebarPendingValue(Player var1, String var2, String... var3) {
      if (var1 != null && var3 != null && var3.length != 0) {
         String var4 = this.firstNonBlank(new String[]{var2, "..."});

         for (Object var8_raw : var3) {
            String var8 = var8_raw != null ? var8_raw.toString() : null;
            if (var8 != null && !var8.isBlank()) {
               this.setShellText(var1, var8, var4);
            }
         }
      }
   }

   protected void updateSidebarSelectionPanelVisibility(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         ConfigurationSection var4 = this.findShellBlockSection(var2, "rightsidebar");
         if (var4 != null) {
            ConfigurationSection var5 = this.findShellBlockSection(var2, "properties");
            String var6 = this.firstNonBlank(new String[]{var5 == null ? null : var5.getString("__editor_target_path")});
            double var7 = this.readDouble(var4, "position.x", "x", 0.0);
            double var9 = this.readDouble(var4, "position.y", "y", 0.0);
            double var11 = Math.max(1.0, Math.abs(this.readDouble(var4, "size.width", "width", this.readDouble(var4, "scale.width", "width", 1.0))));
            double var13 = Math.max(1.0, Math.abs(this.readDouble(var4, "size.height", "height", this.readDouble(var4, "scale.height", "height", 1.0))));
            double var15 = var9 + 60.0;
            int var17 = var3 ? 255 : 0;
            HashMap var18 = new HashMap();
            int var19 = 0;

            for (Map var21 : var2.shellBlocks) {
               var19++;
               ConfigurationSection var22 = this.mapToSection(var21);
               if (var22 != null) {
                  String var23 = this.firstNonBlank(new String[]{var22.getString("type"), "block"}).toLowerCase(Locale.ROOT);
                  if (this.isRenderableBlockType(var23)) {
                     String var24 = this.resolveElementId(var22, var19, var18);
                     String var25 = this.firstNonBlank(new String[]{var22.getString("__editor_target_path")});
                     if ((var6.isBlank() || this.belongsToSidebarPanel(var6, var25))
                        && var24 != null
                        && !var24.isBlank()
                        && !this.isEditorToolShellBlockId(var24)
                        && !"rightsidebar".equals(var24)
                        && !this.isLayersShellBlockId(var24)
                        && !this.isActionsShellBlockId(var24)) {
                        double var26 = this.readDouble(var22, "position.x", "x", 0.0);
                        double var28 = this.readDouble(var22, "position.y", "y", 0.0);
                        if (!(var26 < var7) && !(var26 > var7 + var11) && !(var28 < var9) && !(var28 > var9 + var13) && !(var28 <= var15)) {
                           this.setShellOpacity(var1, var24, var17);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected boolean isLayersShellBlockId(String var1) {
      return var1 != null && !var1.isBlank() ? "layers_box".equals(var1) || var1.startsWith("layers_runtime_") || var1.startsWith("layer_slot_") : false;
   }

   protected void updateSidebarOpacitySliderVisual(Player var1, EditorSession var2, double var3) {
      ConfigurationSection var5 = this.findShellBlockSection(var2, "opacity_slider_track");
      ConfigurationSection var6 = this.findShellBlockSection(var2, "opacity_circle");
      if (var5 != null && var6 != null) {
         EditorRect var7 = this.findShellBlockRect(var2, "opacity_slider_fill");
         EditorRect var8 = this.findShellBlockRect(var2, "opacity_circle");
         double var9 = this.readDouble(var5, "position.x", "x", 0.0);
         double var11 = this.readDouble(var5, "position.y", "y", 0.0);
         double var13 = Math.max(1.0, Math.abs(this.readDouble(var5, "size.width", "width", this.readDouble(var5, "scale.width", "width", 1.0))));
         double var15 = Math.max(1.0, Math.abs(this.readDouble(var5, "size.height", "height", this.readDouble(var5, "scale.height", "height", 1.0))));
         double var17 = var8 != null ? var8.y : this.readDouble(var6, "position.y", "y", 25.0);
         double var19 = Math.max(1.0, Math.abs(this.readDouble(var6, "size.width", "width", this.readDouble(var6, "scale.width", "width", 1.0))));
         double var21 = Math.max(1.0, Math.abs(this.readDouble(var6, "size.height", "height", this.readDouble(var6, "scale.height", "height", 1.0))));
         double var23 = (double)this.clampSidebarOpacityRaw(var3);
         double var25 = this.opacityRawToPercent(var23) / 100.0;
         double var27 = Math.max(1.0, Math.min(var13, 135.0));
         double var29 = Math.max(1.0, var27 * var25);
         double var31 = var9 + var27 * var25 - 8.0;
         EditorRect var33 = new EditorRect(var9, var11, var29, var15);
         EditorRect var34 = new EditorRect(var31, var17, var19, var21);
         if (var7 == null || !this.sameRect(var7, var33)) {
            this.moveShellElement(var1, var2, "opacity_slider_fill", var33.x, var33.y, var33.width, var33.height);
         }

         if (var8 == null || !this.sameRect(var8, var34)) {
            this.moveShellElement(var1, var2, "opacity_circle", var34.x, var34.y, var34.width, var34.height);
         }
      }
   }

   @Override
   protected String toSidebarTypeLabel(String var1) {
      if (var1 != null && !var1.isBlank()) {
         String var2 = var1.toLowerCase(Locale.ROOT);

         return switch (var2) {
            case "block_rounded" -> "Block Rounded";
            case "rounded" -> "Rounded";
            case "hitbox" -> "Hitbox";
            case "text" -> "Text";
            case "item" -> "Item";
            default -> "Block";
         };
      } else {
         return "Block";
      }
   }

   protected String formatPx(double var1) {
      if (!Double.isFinite(var1)) {
         return "0px";
      } else {
         double var3 = Math.rint(var1);
         return Math.abs(var1 - var3) < 1.0E-4 ? String.format(Locale.ROOT, "%dpx", (int)var3) : String.format(Locale.ROOT, "%.2fpx", var1);
      }
   }

   protected String formatLayerValue(double var1) {
      if (!Double.isFinite(var1)) {
         return "0";
      } else {
         double var3 = Math.rint(var1);
         return Math.abs(var1 - var3) < 1.0E-4 ? String.format(Locale.ROOT, "%d", (int)var3) : String.format(Locale.ROOT, "%.2f", var1);
      }
   }

   @Override
   protected String readTargetName(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
            String var4 = this.readTargetCustomName(var1, var3.targetPath);
            if (!var4.isBlank()) {
               return var4;
            }
         }

         for (LayersPanelManager.LayerEntry var5 : this.collectLayerEntries(var1)) {
            if (var2.equals(var5.targetId) && var5.displayName != null && !var5.displayName.isBlank()) {
               return var5.displayName;
            }
         }

         return var3 == null
            ? this.firstNonBlank(new String[]{var2})
            : this.firstNonBlank(new String[]{var3.targetId, var2, this.toSidebarTypeLabel(var3.type)});
      } else {
         return "";
      }
   }

   protected String readTargetLogicalId(EditorSession var1, String var2) {
      Map var3 = this.resolveRawTargetByTargetId(var1, var2);
      if (var3 != null) {
         String var4 = this.stringValue(this.readMapPathValue(var3, "id"));
         if (!var4.isBlank()) {
            return var4;
         }
      }

      HoverElement var5 = this.findFirstByTargetId(var1, var2);
      return this.firstNonBlank(new String[]{var5 == null ? null : var5.targetId, var2});
   }

   @Override
   protected boolean isSidebarItemDesignTarget(EditorSession var1, String var2) {
      String var3 = this.readTargetRawType(var1, var2);
      if ("item".equals(var3)) {
         return true;
      } else {
         Map var4 = this.resolveRawTargetByTargetId(var1, var2);
         String var5 = this.readTargetItemMaterialToken(var4);
         return var5.isBlank() ? false : this.resolveSidebarMaterialInput(var5) != null;
      }
   }

   protected boolean isSidebarTextDesignTarget(EditorSession var1, String var2) {
      if ("text".equals(this.readTargetRawType(var1, var2))) {
         return true;
      } else {
         Map var3 = this.resolveRawTargetByTargetId(var1, var2);
         if (var3 != null && !var3.isEmpty()) {
            String var4 = this.firstNonBlank(
               new String[]{
                  this.stringValue(this.readMapPathValue(var3, "align")),
                  this.stringValue(this.readMapPathValue(var3, "text.align")),
                  this.stringValue(this.readMapPathValue(var3, "style.align")),
                  this.stringValue(this.readMapPathValue(var3, "params.align")),
                  this.stringValue(this.readMapPathValue(var3, "params.text.align")),
                  this.stringValue(this.readMapPathValue(var3, "params.style.align"))
               }
            );
            if (!var4.isBlank()) {
               return true;
            } else {
               String var5 = this.firstNonBlank(
                  new String[]{this.stringValue(this.readMapPathValue(var3, "text")), this.stringValue(this.readMapPathValue(var3, "params.text"))}
               );
               return !var5.isBlank();
            }
         } else {
            return false;
         }
      }
   }

   @Override
   protected boolean isTextDesignSidebarMode(EditorSession var1) {
      List var2 = this.getSidebarEditableTargetIds(var1);
      if (var2.isEmpty()) {
         return false;
      } else {
         for (Object var4_raw : var2) {
            String var4 = var4_raw != null ? var4_raw.toString() : null;
            if (this.isSidebarTextDesignTarget(var1, var4)) {
               return true;
            }
         }

         return false;
      }
   }

   protected TextAlignment readTargetTextAlignment(EditorSession var1, String var2) {
      Map var3 = this.resolveRawTargetByTargetId(var1, var2);
      if (var3 == null) {
         return TextAlignment.CENTER;
      } else {
         String var4 = this.firstNonBlank(
            new String[]{
               this.stringValue(this.readMapPathValue(var3, "align")),
               this.stringValue(this.readMapPathValue(var3, "text.align")),
               this.stringValue(this.readMapPathValue(var3, "style.align")),
               this.stringValue(this.readMapPathValue(var3, "params.align")),
               this.stringValue(this.readMapPathValue(var3, "params.text.align")),
               this.stringValue(this.readMapPathValue(var3, "params.style.align"))
            }
         );
         if (var4.isBlank()) {
            return TextAlignment.CENTER;
         } else {
            String var5 = var4.toLowerCase(Locale.ROOT);

            return switch (var5) {
               case "left", "start" -> TextAlignment.LEFT;
               case "right", "end" -> TextAlignment.RIGHT;
               default -> TextAlignment.CENTER;
            };
         }
      }
   }

   protected int readTargetTextWrapLineWidth(EditorSession var1, String var2) {
      Map var3 = this.resolveRawTargetByTargetId(var1, var2);
      if (var3 == null) {
         return 200;
      } else {
         Object var4 = this.readMapPathValue(var3, "text-wrap");
         if (var4 == null) {
            var4 = this.readMapPathValue(var3, "textWrap");
         }

         if (var4 == null) {
            var4 = this.readMapPathValue(var3, "text.wrap");
         }

         if (var4 == null) {
            var4 = this.readMapPathValue(var3, "params.text-wrap");
         }

         return this.normalizeTextWrapLineWidth(var4);
      }
   }

   protected String resolveTargetTextAlignmentWritePath(Map<String, Object> var1) {
      if (var1 == null || var1.isEmpty()) {
         return "align";
      } else if (this.hasMapPath(var1, "align")) {
         return "align";
      } else if (this.hasMapPath(var1, "text.align")) {
         return "text.align";
      } else if (this.hasMapPath(var1, "style.align")) {
         return "style.align";
      } else if (this.hasMapPath(var1, "params.align")) {
         return "params.align";
      } else if (this.hasMapPath(var1, "params.text.align")) {
         return "params.text.align";
      } else if (this.hasMapPath(var1, "params.style.align")) {
         return "params.style.align";
      } else {
         return var1.containsKey("params") ? "params.align" : "align";
      }
   }

   protected boolean setTargetTextAlignment(EditorSession var1, String var2, TextAlignment var3) {
      if (!this.isSidebarTextDesignTarget(var1, var2)) {
         return false;
      } else {
         Map var4 = this.resolveRawTargetByTargetId(var1, var2);
         if (var4 == null) {
            return false;
         } else {
            TextAlignment var5 = var3 == null ? TextAlignment.LEFT : var3;
            TextAlignment var6 = this.readTargetTextAlignment(var1, var2);
            if (var6 == var5) {
               return false;
            } else {
               String var7 = this.resolveTargetTextAlignmentWritePath(var4);

               String var8 = switch (var5) {
                  case LEFT -> "left";
                  case RIGHT -> "right";
                  default -> "center";
               };
               this.setMapPathValue(var4, var7, var8);
               return true;
            }
         }
      }
   }

   protected String formatSidebarTextAlignment(TextAlignment var1) {
      TextAlignment var2 = var1 == null ? TextAlignment.LEFT : var1;

      return switch (var2) {
         case LEFT -> "Left";
         case RIGHT -> "Right";
         default -> "Center";
      };
   }

   @Override
   protected void toggleSidebarTextAlignment(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         List var3 = this.getSidebarEditableTargetIds(var2);
         if (var3.isEmpty()) {
            this.updateEditorPropertiesSidebar(var1, var2);
         } else {
            TextAlignment var4 = null;
            boolean var5 = false;
            boolean var6 = false;

            for (Object var8_raw : var3) {
               String var8 = var8_raw != null ? var8_raw.toString() : null;
               if (this.isSidebarTextDesignTarget(var2, var8)) {
                  var6 = true;
                  TextAlignment var9 = this.readTargetTextAlignment(var2, var8);
                  if (var4 == null) {
                     var4 = var9;
                  } else if (var4 != var9) {
                     var5 = true;
                  }
               }
            }

            if (!var6) {
               this.updateEditorPropertiesSidebar(var1, var2);
            } else {
               TextAlignment var16;
               if (!var5 && var4 != null) {
                  var16 = switch (var4) {
                     case LEFT -> TextAlignment.CENTER;
                     case RIGHT -> TextAlignment.LEFT;
                     case CENTER -> TextAlignment.RIGHT;
                     default -> throw new MatchException(null, null);
                  };
               } else {
                  var16 = TextAlignment.LEFT;
               }

               boolean var17 = false;
               boolean var18 = false;

               for (Object var11_raw : var3) {
                  String var11 = var11_raw != null ? var11_raw.toString() : null;
                  if (this.isSidebarTextDesignTarget(var2, var11)) {
                     boolean var12 = this.setTargetTextAlignment(var2, var11, var16);
                     if (var12 && !var18) {
                        this.recordEditorMutationForSidebarInteraction(var2);
                        var18 = true;
                     }

                     var17 |= var12;
                  }
               }

               if (!var17) {
                  this.updateEditorPropertiesSidebar(var1, var2);
               } else {
                  this.applySidebarTextAlignNoTransition(var1, var2, var3);

                  try {
                     this.rerenderEditableSelection(var1, var2);
                  } finally {
                     this.restoreSidebarTextAlignTransitionTicks(var1, var2, var3);
                  }
               }
            }
         }
      }
   }

   protected void applySidebarTextAlignNoTransition(Player var1, EditorSession var2, List<String> var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isEmpty()) {
         for (String var6 : this.collectSidebarTextTargetRuntimeIds(var2, var3)) {
            this.applyRuntimeHudNoTransitionForSidebar(var1, var6);
         }
      }
   }

   protected void restoreSidebarTextAlignTransitionTicks(Player var1, EditorSession var2, List<String> var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isEmpty()) {
         int var4 = Math.max(1, this.resolveEditorCursorInterpolationDurationTicks());
         int var5 = Math.max(1, this.resolveEditorCursorTeleportDurationTicks());

         for (String var8 : this.collectSidebarTextTargetRuntimeIds(var2, var3)) {
            this.applyRuntimeHudTransitionTicksForSidebar(var1, var8, var4, var5);
         }
      }
   }

   protected Set<String> collectSidebarTextTargetRuntimeIds(EditorSession var1, List<String> var2) {
      LinkedHashSet var3 = new LinkedHashSet();
      if (var1 != null && var2 != null && !var2.isEmpty()) {
         for (Object var5_raw : var2) {
            String var5 = var5_raw != null ? var5_raw.toString() : null;
            if (this.isSidebarTextDesignTarget(var1, var5)) {
               for (HoverElement var7 : this.findTargetElements(var1, var5)) {
                  String var8 = this.firstNonBlank(new String[]{var7 == null ? "" : var7.id});
                  if (!var8.isBlank()) {
                     var3.add(var8);
                  }
               }
            }
         }

         return var3;
      } else {
         return var3;
      }
   }

   protected void applyRuntimeHudNoTransitionForSidebar(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         this.setHudNoTransitionIfExists(var1, var2);
         this.setHudNoTransitionIfExists(var1, var2 + "_outline");
         this.setHudNoTransitionIfExists(var1, var2 + "_r_core");
         this.setHudNoTransitionIfExists(var1, var2 + "_r_top");
         this.setHudNoTransitionIfExists(var1, var2 + "_r_bottom");
         this.setHudNoTransitionIfExists(var1, var2 + "_r_left");
         this.setHudNoTransitionIfExists(var1, var2 + "_r_right");
         this.setHudNoTransitionIfExists(var1, var2 + "_r_tl");
         this.setHudNoTransitionIfExists(var1, var2 + "_r_tr");
         this.setHudNoTransitionIfExists(var1, var2 + "_r_bl");
         this.setHudNoTransitionIfExists(var1, var2 + "_r_br");
         String var3 = var2 + "_outline";
         this.setHudNoTransitionIfExists(var1, var3 + "_r_core");
         this.setHudNoTransitionIfExists(var1, var3 + "_r_top");
         this.setHudNoTransitionIfExists(var1, var3 + "_r_bottom");
         this.setHudNoTransitionIfExists(var1, var3 + "_r_left");
         this.setHudNoTransitionIfExists(var1, var3 + "_r_right");
         this.setHudNoTransitionIfExists(var1, var3 + "_r_tl");
         this.setHudNoTransitionIfExists(var1, var3 + "_r_tr");
         this.setHudNoTransitionIfExists(var1, var3 + "_r_bl");
         this.setHudNoTransitionIfExists(var1, var3 + "_r_br");
      }
   }

   protected void applyRuntimeHudTransitionTicksForSidebar(Player var1, String var2, int var3, int var4) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         this.setHudTransitionTicksIfExists(var1, var2, var3, var4);
         this.setHudTransitionTicksIfExists(var1, var2 + "_outline", var3, var4);
         this.setHudTransitionTicksIfExists(var1, var2 + "_r_core", var3, var4);
         this.setHudTransitionTicksIfExists(var1, var2 + "_r_top", var3, var4);
         this.setHudTransitionTicksIfExists(var1, var2 + "_r_bottom", var3, var4);
         this.setHudTransitionTicksIfExists(var1, var2 + "_r_left", var3, var4);
         this.setHudTransitionTicksIfExists(var1, var2 + "_r_right", var3, var4);
         this.setHudTransitionTicksIfExists(var1, var2 + "_r_tl", var3, var4);
         this.setHudTransitionTicksIfExists(var1, var2 + "_r_tr", var3, var4);
         this.setHudTransitionTicksIfExists(var1, var2 + "_r_bl", var3, var4);
         this.setHudTransitionTicksIfExists(var1, var2 + "_r_br", var3, var4);
         String var5 = var2 + "_outline";
         this.setHudTransitionTicksIfExists(var1, var5 + "_r_core", var3, var4);
         this.setHudTransitionTicksIfExists(var1, var5 + "_r_top", var3, var4);
         this.setHudTransitionTicksIfExists(var1, var5 + "_r_bottom", var3, var4);
         this.setHudTransitionTicksIfExists(var1, var5 + "_r_left", var3, var4);
         this.setHudTransitionTicksIfExists(var1, var5 + "_r_right", var3, var4);
         this.setHudTransitionTicksIfExists(var1, var5 + "_r_tl", var3, var4);
         this.setHudTransitionTicksIfExists(var1, var5 + "_r_tr", var3, var4);
         this.setHudTransitionTicksIfExists(var1, var5 + "_r_bl", var3, var4);
         this.setHudTransitionTicksIfExists(var1, var5 + "_r_br", var3, var4);
      }
   }

   protected void setHudNoTransitionIfExists(Player var1, String var2) {
      EditorHudRenderer.setHudNoTransitionIfExists(this.hudService, var1, var2);
   }

   protected void setHudTransitionTicksIfExists(Player var1, String var2, int var3, int var4) {
      EditorHudRenderer.setHudTransitionTicksIfExists(this.hudService, var1, var2, var3, var4);
   }

   @Override
   protected void cycleSidebarBorderRadiusMode(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         List var3 = this.getSidebarEditableTargetIds(var2);
         if (var3.isEmpty()) {
            this.updateEditorPropertiesSidebar(var1, var2);
         } else {
            String var4 = null;
            boolean var5 = false;
            boolean var6 = false;

            for (Object var8_raw : var3) {
               String var8 = var8_raw != null ? var8_raw.toString() : null;
               String var9 = this.readTargetRawType(var2, var8);
               if (this.isSidebarBorderRadiusApplicableType(var9)) {
                  var6 = true;
                  String var10 = this.readTargetBorderRadiusMode(var2, var8);
                  if (var4 == null) {
                     var4 = var10;
                  } else if (!this.equalsNullable(var4, var10)) {
                     var5 = true;
                  }
               }
            }

            if (!var6) {
               this.updateEditorPropertiesSidebar(var1, var2);
            } else {
               String var14 = this.nextSidebarBorderRadiusMode(var5 ? "None" : var4);
               boolean var15 = false;
               boolean var16 = false;

               for (Object var11_raw : var3) {
                  String var11 = var11_raw != null ? var11_raw.toString() : null;
                  String var12 = this.readTargetRawType(var2, var11);
                  if (this.isSidebarBorderRadiusApplicableType(var12)) {
                     boolean var13 = this.setTargetBorderRadiusMode(var2, var11, var14);
                     if (var13 && !var16) {
                        this.recordEditorMutationForSidebarInteraction(var2);
                        var16 = true;
                     }

                     var15 |= var13;
                  }
               }

               if (!var15) {
                  this.updateEditorPropertiesSidebar(var1, var2);
               } else {
                  this.rerenderEditableSelection(var1, var2);
               }
            }
         }
      }
   }

   @Override
   protected void cycleSidebarHoverEffect(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         List var4 = this.getSidebarEditableTargetIds(var2);
         if (var4.isEmpty()) {
            this.updateEditorPropertiesSidebar(var1, var2);
         } else {
            String var5 = null;
            boolean var6 = false;
            boolean var7 = false;

            for (Object var9_raw : var4) {
               String var9 = var9_raw != null ? var9_raw.toString() : null;
               if (!var3 || this.isSidebarItemDesignTarget(var2, var9)) {
                  var7 = true;
                  String var10 = this.normalizeHoverEffectId(this.readTargetHoverEffect(var2, var9));
                  if (var5 == null) {
                     var5 = var10;
                  } else if (!this.equalsNullable(var5, var10)) {
                     var6 = true;
                  }
               }
            }

            if (!var7) {
               this.updateEditorPropertiesSidebar(var1, var2);
            } else {
               List var15 = this.resolveSidebarHoverEffectOptionIds();
               String var16 = this.nextSidebarHoverEffect(var6 ? "none" : var5, var15);
               boolean var17 = false;
               boolean var11 = false;

               for (Object var13_raw : var4) {
                  String var13 = var13_raw != null ? var13_raw.toString() : null;
                  if (!var3 || this.isSidebarItemDesignTarget(var2, var13)) {
                     boolean var14 = this.setTargetHoverEffect(var2, var13, var16);
                     if (var14 && !var11) {
                        this.recordEditorMutationForSidebarInteraction(var2);
                        var11 = true;
                     }

                     var17 |= var14;
                  }
               }

               if (!var17) {
                  this.updateEditorPropertiesSidebar(var1, var2);
               } else {
                  this.rerenderEditableSelection(var1, var2);
               }
            }
         }
      }
   }

   protected String nextSidebarHoverEffect(String var1, List<String> var2) {
      if (var2 != null && !var2.isEmpty()) {
         String var3 = this.normalizeHoverEffectId(var1);
         int var4 = var2.indexOf(var3);
         return var4 < 0 ? (String)var2.get(0) : (String)var2.get((var4 + 1) % var2.size());
      } else {
         return "none";
      }
   }

   protected String normalizeHoverEffectId(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim().toLowerCase(Locale.ROOT);
      if (!var2.isBlank() && !"None".equalsIgnoreCase(var2) && !"null".equals(var2)) {
         var2 = var2.replaceAll("[^a-z0-9_\\-]", "");
         return var2.isBlank() ? "none" : var2;
      } else {
         return "none";
      }
   }

   protected List<String> resolveSidebarHoverEffectOptionIds() {
      TreeSet var1 = new TreeSet();
      Map var2 = this.loadHoverEffectConfigurations();

      for (Map.Entry<?, ?> var4 : ((Map<?, ?>)var2).entrySet()) {
         YamlConfiguration var5 = (YamlConfiguration)var4.getValue();
         String var6 = this.normalizeHoverEffectId(this.firstNonBlank(new String[]{var5 == null ? null : var5.getString("id"), (String)var4.getKey()}));
         if (!var6.isBlank() && !"none".equals(var6)) {
            var1.add(var6);
         }
      }

      if (var1.isEmpty()) {
         var1.add("scale");
      }

      ArrayList var7 = new ArrayList(var1.size() + 1);
      var7.add("none");
      var7.addAll(var1);
      return var7;
   }

   protected Map<String, YamlConfiguration> loadHoverEffectConfigurations() {
      Map var1 = this.loadYamlFolder("contents/effects", new ArrayList<>());
      LinkedHashMap var2 = new LinkedHashMap();

      for (Map.Entry<?, ?> var4 : ((Map<?, ?>)var1).entrySet()) {
         YamlConfiguration var5 = (YamlConfiguration)var4.getValue();
         String var6 = this.normalizeHoverEffectId(this.firstNonBlank(new String[]{var5 == null ? null : var5.getString("id"), (String)var4.getKey()}));
         if (!var6.isBlank() && !"none".equals(var6)) {
            var2.putIfAbsent(var6, var5);
         }
      }

      return var2;
   }

   protected YamlConfiguration resolveHoverEffectConfiguration(String var1) {
      String var2 = this.normalizeHoverEffectId(var1);
      if (!var2.isBlank() && !"none".equals(var2)) {
         YamlConfiguration var3 = this.loadHoverEffectConfigurations().get(var2);
         if (var3 != null) {
            return var3;
         } else if ("scale".equals(var2)) {
            YamlConfiguration var4 = new YamlConfiguration();
            var4.set("id", "scale");
            var4.set("name", "Scale");
            var4.set("type", "scale");
            var4.set("amount-percent", 5.0);
            var4.set("duration-ms", 250);
            var4.set("interpolation", "ease-in-out");
            return var4;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   @Override
   protected AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig resolveHoverEffectRuntimeConfig(String var1) {
      String var2 = this.normalizeHoverEffectId(var1);
      if (!var2.isBlank() && !"none".equals(var2)) {
         YamlConfiguration var3 = this.resolveHoverEffectConfiguration(var2);
         if (var3 == null) {
            return null;
         } else {
            String var4 = this.normalizeHoverEffectId(this.firstNonBlank(new String[]{var3.getString("type"), var2}));
            double var5 = "scale".equals(var4) ? 5.0 : 0.0;
            double var7 = this.readHoverEffectPercentNumeric(var3, var5, "scale", "scale-percent", "scalePercent", "scale.percent", "percent");
            double var9 = this.readHoverEffectPercentNumeric(
               var3, Double.NaN, "scale-x", "scaleX", "scale-x-percent", "scaleXPercent", "scale.x-percent", "scale.x"
            );
            double var11 = this.readHoverEffectPercentNumeric(
               var3, Double.NaN, "scale-y", "scaleY", "scale-y-percent", "scaleYPercent", "scale.y-percent", "scale.y"
            );
            double var13 = Double.isFinite(var9) ? var9 : var7;
            double var15 = Double.isFinite(var11) ? var11 : var7;
            var13 = this.clampHoverEffectScalePercent(var13);
            var15 = this.clampHoverEffectScalePercent(var15);
            double var17 = 1.0 + var13 / 100.0;
            double var19 = 1.0 + var15 / 100.0;
            if (!Double.isFinite(var17) || var17 <= 0.0) {
               var17 = 1.0;
            }

            if (!Double.isFinite(var19) || var19 <= 0.0) {
               var19 = 1.0;
            }

            double var21 = this.readHoverEffectPercentNumeric(
               var3, 0.0, "move-x", "moveX", "offset-x", "offsetX", "translate-x", "translateX", "move.x", "offset.x", "x"
            );
            double var23 = this.readHoverEffectPercentNumeric(
               var3, 0.0, "move-y", "moveY", "offset-y", "offsetY", "translate-y", "translateY", "move.y", "offset.y", "y"
            );
            double var25 = this.readHoverEffectPercentNumeric(var3, 0.0, "left", "move-left", "offset-left");
            double var27 = this.readHoverEffectPercentNumeric(var3, 0.0, "right", "move-right", "offset-right");
            double var29 = this.readHoverEffectPercentNumeric(var3, 0.0, "up", "move-up", "offset-up");
            double var31 = this.readHoverEffectPercentNumeric(var3, 0.0, "down", "move-down", "offset-down");
            var21 += var27 - var25;
            var23 += var31 - var29;
            boolean var33 = this.hasHoverEffectValue(
               var3, "opacity", "alpha", "opacity-absolute", "opacityAbsolute", "alpha-absolute", "alphaAbsolute", "opacity.absolute", "alpha.absolute"
            );
            double var34 = this.readHoverEffectNumeric(
               var3, 255.0, "opacity", "alpha", "opacity-absolute", "opacityAbsolute", "alpha-absolute", "alphaAbsolute", "opacity.absolute", "alpha.absolute"
            );
            int var36 = this.clampHoverEffectOpacity(var34 >= 0.0 && var34 <= 1.0 ? (int)Math.round(var34 * 255.0) : (int)Math.round(var34));
            int var37 = (int)Math.round(
               this.readHoverEffectNumeric(var3, 0.0, "opacity-delta", "opacityDelta", "alpha-delta", "alphaDelta", "opacity.delta", "alpha.delta")
            );
            var37 = Math.max(-255, Math.min(255, var37));
            double var38 = this.readHoverEffectPercentNumeric(
               var3, 0.0, "opacity-percent", "opacityPercent", "alpha-percent", "alphaPercent", "opacity.percent", "alpha.percent"
            );
            if (!Double.isFinite(var38)) {
               var38 = 0.0;
            }

            double var40 = this.readHoverEffectNumeric(var3, 0.0, "rotation-deg", "rotationDeg", "rotate", "rotation", "rotation.deg", "rotate-deg");
            if (!Double.isFinite(var40)) {
               var40 = 0.0;
            }

            double var42 = this.readHoverEffectNumeric(var3, 250.0, "duration-ms", "durationMs", "duration");
            if (!Double.isFinite(var42) || var42 < 0.0) {
               var42 = 250.0;
            }

            int var44 = Math.max(1, (int)Math.round(var42 / 50.0));
            String var45 = this.normalizeAnimationTimelineInterpolationMode(this.firstNonBlank(new String[]{var3.getString("interpolation"), "ease-in-out"}));
            double var46 = this.readHoverEffectPercentNumeric(var3, 0.0, "start-x", "startX", "start.x");
            double var48 = this.readHoverEffectPercentNumeric(var3, 0.0, "start-y", "startY", "start.y");
            double var50 = this.readHoverEffectPercentNumeric(var3, Double.NaN, "start-scale-x", "startScaleX", "start.scale-x", "start.scale.x");
            double var52 = this.readHoverEffectPercentNumeric(var3, Double.NaN, "start-scale-y", "startScaleY", "start.scale-y", "start.scale.y");
            double var54 = Double.isFinite(var50) ? 1.0 + var50 / 100.0 : 1.0;
            double var56 = Double.isFinite(var52) ? 1.0 + var52 / 100.0 : 1.0;
            if (!Double.isFinite(var54) || var54 <= 0.0) {
               var54 = 1.0;
            }

            if (!Double.isFinite(var56) || var56 <= 0.0) {
               var56 = 1.0;
            }

            boolean var58 = var3.getBoolean("close-reversed", false);
            return new AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig(
               var17, var19, var21, var23, var33, var36, var37, var38, var40, var44, var45, var46, var48, var54, var56, var58
            );
         }
      } else {
         return null;
      }
   }

   protected AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig buildStartPositionConfig(
      AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig var1
   ) {
      return var1 == null
         ? null
         : new AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig(
            var1.startScaleXMultiplier(),
            var1.startScaleYMultiplier(),
            var1.startOffsetX(),
            var1.startOffsetY(),
            var1.opacityAbsolute(),
            var1.opacityAbsoluteValue(),
            var1.opacityDelta(),
            var1.opacityPercentDelta(),
            0.0,
            1,
            var1.interpolationMode(),
            0.0,
            0.0,
            1.0,
            1.0,
            false
         );
   }

   protected AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig buildReversedCloseConfig(
      AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig var1
   ) {
      return var1 == null
         ? null
         : new AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig(
            2.0 - var1.scaleXMultiplier(),
            2.0 - var1.scaleYMultiplier(),
            -var1.offsetX(),
            -var1.offsetY(),
            var1.opacityAbsolute(),
            var1.opacityAbsoluteValue(),
            var1.opacityDelta(),
            var1.opacityPercentDelta(),
            -var1.rotationDeltaDeg(),
            var1.durationTicks(),
            var1.interpolationMode(),
            var1.startOffsetX(),
            var1.startOffsetY(),
            var1.startScaleXMultiplier(),
            var1.startScaleYMultiplier(),
            false
         );
   }

   protected double readHoverEffectNumeric(YamlConfiguration var1, double var2, String... var4) {
      if (var1 != null && var4 != null && var4.length != 0) {
         for (Object var8_raw : var4) {
            String var8 = var8_raw != null ? var8_raw.toString() : null;
            if (var8 != null && !var8.isBlank()) {
               Object var9 = var1.get(var8);
               if (var9 != null) {
                  double var10 = this.parseDouble(this.stringValue(var9), Double.NaN);
                  if (Double.isFinite(var10)) {
                     return var10;
                  }
               }
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   protected double readHoverEffectPercentNumeric(YamlConfiguration var1, double var2, String... var4) {
      if (var1 != null && var4 != null && var4.length != 0) {
         for (Object var8_raw : var4) {
            String var8 = var8_raw != null ? var8_raw.toString() : null;
            if (var8 != null && !var8.isBlank()) {
               Object var9 = var1.get(var8);
               if (var9 != null) {
                  double var10 = this.parseHoverEffectPercentValue(var9, Double.NaN);
                  if (Double.isFinite(var10)) {
                     return var10;
                  }
               }
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   protected double parseHoverEffectPercentValue(Object var1, double var2) {
      if (var1 == null) {
         return var2;
      } else if (var1 instanceof Number var7) {
         return var7.doubleValue();
      } else {
         String var4 = this.stringValue(var1).trim();
         if (var4.isBlank()) {
            return var2;
         } else {
            if (var4.endsWith("%")) {
               var4 = var4.substring(0, var4.length() - 1).trim();
            }

            if (var4.isBlank()) {
               return var2;
            } else {
               double var5 = this.parseDouble(var4, Double.NaN);
               return Double.isFinite(var5) ? var5 : var2;
            }
         }
      }
   }

   protected boolean hasHoverEffectValue(YamlConfiguration var1, String... var2) {
      if (var1 != null && var2 != null && var2.length != 0) {
         for (Object var6_raw : var2) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            if (var6 != null && !var6.isBlank() && (var1.contains(var6) || var1.get(var6) != null)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   protected double clampHoverEffectScalePercent(double var1) {
      return !Double.isFinite(var1) ? 0.0 : Math.max(-95.0, Math.min(500.0, var1));
   }

   protected int clampHoverEffectOpacity(int var1) {
      return Math.max(0, Math.min(255, var1));
   }

   @Override
   protected String readTargetRawType(EditorSession var1, String var2) {
      Map var3 = this.resolveRawTargetByTargetId(var1, var2);
      String var4 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var3, "type"))}).toLowerCase(Locale.ROOT);
      if (!var4.isBlank()) {
         return var4;
      } else {
         HoverElement var5 = this.findFirstByTargetId(var1, var2);
         return this.firstNonBlank(new String[]{var5 == null ? null : var5.type}).toLowerCase(Locale.ROOT);
      }
   }

   protected Material resolveSidebarMaterialInput(String var1) {
      if (var1 != null && !var1.isBlank()) {
         String var2 = this.stripSidebarCustomModelDataSuffix(var1);
         if (var2.isBlank()) {
            return null;
         } else if (this.isSidebarPlayerHeadToken(var2)) {
            return Material.PLAYER_HEAD;
         } else {
            Material var3 = Material.matchMaterial(var2);
            if (var3 == null && var2.contains(":")) {
               var3 = Material.matchMaterial(var2.substring(var2.indexOf(58) + 1));
            }

            if (var3 == null) {
               var3 = Material.matchMaterial(var2.toUpperCase(Locale.ROOT));
            }

            return var3 != null && var3 != Material.AIR ? var3 : null;
         }
      } else {
         return null;
      }
   }

   protected int findSidebarCustomModelDataSeparator(String var1) {
      if (var1 == null) {
         return -1;
      } else {
         String var2 = var1.trim();
         if (var2.isBlank()) {
            return -1;
         } else {
            int var3 = var2.lastIndexOf(35);
            return var3 > 0 && var3 < var2.length() - 1 ? var3 : -1;
         }
      }
   }

   protected Integer parseSidebarCustomModelDataValue(String var1) {
      if (var1 != null && !var1.isBlank()) {
         double var2 = this.parseDouble(var1, Double.NaN);
         if (!Double.isFinite(var2)) {
            return null;
         } else {
            long var4 = Math.round(var2);
            if (var4 >= 0L && var4 <= 2147483647L) {
               return Math.abs(var2 - (double)var4) > 1.0E-4 ? null : (int)var4;
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   protected Integer readSidebarCustomModelDataFromToken(String var1) {
      int var2 = this.findSidebarCustomModelDataSeparator(var1);
      if (var2 < 0) {
         return null;
      } else {
         String var3 = var1.trim();
         String var4 = var3.substring(var2 + 1).trim();
         return this.parseSidebarCustomModelDataValue(var4);
      }
   }

   protected String stripSidebarCustomModelDataSuffix(String var1) {
      if (var1 == null) {
         return "";
      } else {
         String var2 = var1.trim();
         if (var2.isBlank()) {
            return "";
         } else {
            int var3 = this.findSidebarCustomModelDataSeparator(var2);
            return var3 < 0 ? var2 : var2.substring(0, var3).trim();
         }
      }
   }

   protected boolean isSidebarPlayerHeadToken(String var1) {
      String var2 = this.stripSidebarCustomModelDataSuffix(var1);
      if (var2.isBlank()) {
         return false;
      } else {
         return var2.equalsIgnoreCase("PLAYER_HEAD") ? true : var2.regionMatches(true, 0, "PLAYER_HEAD:", 0, "PLAYER_HEAD:".length());
      }
   }

   protected String extractSidebarPlayerHeadOwner(String var1) {
      String var2 = this.stripSidebarCustomModelDataSuffix(var1);
      return !var2.regionMatches(true, 0, "PLAYER_HEAD:", 0, "PLAYER_HEAD:".length()) ? "" : var2.substring("PLAYER_HEAD:".length()).trim();
   }

   protected String buildSidebarItemToken(Material var1, String var2, Integer var3) {
      if (var1 != null && var1 != Material.AIR) {
         String var4;
         if (var1 == Material.PLAYER_HEAD) {
            String var5 = this.firstNonBlank(new String[]{var2});
            var4 = var5.isBlank() ? "PLAYER_HEAD" : "PLAYER_HEAD:" + var5;
         } else {
            var4 = var1.name();
         }

         return var3 != null && var3 >= 0 ? var4 + "#" + var3 : var4;
      } else {
         return "";
      }
   }

   protected String normalizeSidebarItemTokenInput(String var1) {
      if (var1 != null && !var1.isBlank()) {
         String var2 = var1.trim();
         String var3 = this.stripSidebarCustomModelDataSuffix(var2);
         Integer var4 = this.readSidebarCustomModelDataFromToken(var2);
         if (this.findSidebarCustomModelDataSeparator(var2) >= 0 && var4 == null) {
            return "";
         } else {
            Material var5 = this.resolveSidebarMaterialInput(var3);
            if (var5 != null && var5 != Material.AIR) {
               String var6 = var5 == Material.PLAYER_HEAD ? this.extractSidebarPlayerHeadOwner(var3) : "";
               return this.buildSidebarItemToken(var5, var6, var4);
            } else {
               return "";
            }
         }
      } else {
         return "";
      }
   }

   protected String readTargetItemToken(EditorSession var1, String var2) {
      Map var3 = this.resolveRawTargetByTargetId(var1, var2);
      String var4 = this.readTargetItemMaterialToken(var3);
      String var5 = this.normalizeSidebarItemTokenInput(var4);
      return this.firstNonBlank(new String[]{var5, var4});
   }

   protected Material readTargetItemMaterial(EditorSession var1, String var2) {
      Map var3 = this.resolveRawTargetByTargetId(var1, var2);
      String var4 = this.readTargetItemMaterialToken(var3);
      return this.resolveSidebarMaterialInput(var4);
   }

   protected String readTargetStringValue(Map<String, Object> var1, String var2) {
      Object var3 = this.readMapPathValue(var1, var2);
      return !(var3 instanceof Map) && !(var3 instanceof Collection) ? this.stringValue(var3) : "";
   }

   protected String readTargetItemMaterialToken(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         String var2 = this.firstNonBlank(
            new String[]{
               this.readTargetStringValue(var1, "item.material"),
               this.readTargetStringValue(var1, "material"),
               this.readTargetStringValue(var1, "params.item.material"),
               this.readTargetStringValue(var1, "params.material")
            }
         );
         if (!var2.isBlank()) {
            return var2;
         } else {
            Object var3 = this.readMapPathValue(var1, "item");
            if (var3 instanceof Map var4) {
               String var6 = this.stringValue(var4.get("material"));
               if (!var6.isBlank()) {
                  return var6;
               }
            } else {
               String var5 = this.stringValue(var3);
               if (!var5.isBlank()) {
                  return var5;
               }
            }

            Object var7 = this.readMapPathValue(var1, "params.item");
            return var7 instanceof Map var8 ? this.stringValue(var8.get("material")) : this.stringValue(var7);
         }
      } else {
         return "";
      }
   }

   protected String resolveTargetItemMaterialWritePath(Map<String, Object> var1) {
      if (var1 == null) {
         return "item";
      } else if (this.hasMapPath(var1, "item.material") || this.readMapPathValue(var1, "item") instanceof Map) {
         return "item.material";
      } else if (this.hasMapPath(var1, "item")) {
         return "item";
      } else if (this.hasMapPath(var1, "material")) {
         return "material";
      } else if (this.hasMapPath(var1, "params.item.material") || this.readMapPathValue(var1, "params.item") instanceof Map) {
         return "params.item.material";
      } else if (this.hasMapPath(var1, "params.item")) {
         return "params.item";
      } else if (this.hasMapPath(var1, "params.material")) {
         return "params.material";
      } else {
         return var1.containsKey("params") ? "params.item" : "item";
      }
   }

   protected boolean setTargetItemMaterial(EditorSession var1, String var2, Material var3) {
      return var3 != null && var3 != Material.AIR ? this.setTargetItemToken(var1, var2, this.buildSidebarItemToken(var3, "", null)) : false;
   }

   protected boolean setTargetItemToken(EditorSession var1, String var2, String var3) {
      String var4 = this.normalizeSidebarItemTokenInput(var3);
      if (var4.isBlank()) {
         return false;
      } else {
         Map var5 = this.resolveRawTargetByTargetId(var1, var2);
         if (var5 == null) {
            return false;
         } else {
            String var6 = this.readTargetItemMaterialToken(var5);
            String var7 = this.firstNonBlank(new String[]{this.normalizeSidebarItemTokenInput(var6), var6});
            if (var4.equals(var7)) {
               return false;
            } else {
               String var8 = this.resolveTargetItemMaterialWritePath(var5);
               this.setMapPathValue(var5, var8, var4);
               return true;
            }
         }
      }
   }

   protected Map<String, Integer> parseTargetItemEnchantmentsMap(Object var1) {
      if (var1 instanceof Map var2) {
         LinkedHashMap var3 = new LinkedHashMap();

         for (Map.Entry<?, ?> var5 : ((Map<?, ?>)var2).entrySet()) {
            String var6 = this.firstNonBlank(new String[]{this.stringValue(var5.getKey())}).toLowerCase(Locale.ROOT);
            if (!var6.isBlank()) {
               double var7 = this.parseDouble(var5.getValue(), Double.NaN);
               if (Double.isFinite(var7)) {
                  int var9 = (int)Math.round(var7);
                  if (var9 > 0) {
                     var3.put(var6, var9);
                  }
               }
            }
         }

         return var3;
      } else {
         return null;
      }
   }

   protected Map<String, Integer> normalizeTargetItemEnchantments(Map<String, Integer> var1) {
      if (var1 != null && !var1.isEmpty()) {
         LinkedHashMap<String, Integer> var2 = new LinkedHashMap<>();

         for (Map.Entry<?, ?> var4 : ((Map<?, ?>)var1).entrySet()) {
            String var5 = this.firstNonBlank(new String[]{(String)var4.getKey()}).toLowerCase(Locale.ROOT);
            if (!var5.isBlank()) {
               Integer var6 = (Integer)var4.getValue();
               if (var6 != null && var6 > 0) {
                  var2.put(var5, var6);
               }
            }
         }

         return (Map<String, Integer>)(var2.isEmpty() ? Collections.emptyMap() : var2);
      } else {
         return Collections.emptyMap();
      }
   }

   protected Map<String, Integer> readTargetItemEnchantments(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         String[] var2 = new String[]{
            "item.enchants",
            "enchants",
            "params.item.enchants",
            "params.enchants",
            "item.enchantments",
            "enchantments",
            "params.item.enchantments",
            "params.enchantments"
         };

         for (Object var6_raw : var2) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            if (this.hasMapPath(var1, var6)) {
               Map var7 = this.parseTargetItemEnchantmentsMap(this.readMapPathValue(var1, var6));
               if (var7 != null) {
                  return var7;
               }
            }
         }

         return Collections.emptyMap();
      } else {
         return Collections.emptyMap();
      }
   }

   protected String resolveTargetItemEnchantmentsWritePath(Map<String, Object> var1) {
      if (var1 == null) {
         return "enchants";
      } else if (this.hasMapPath(var1, "item.enchants")) {
         return "item.enchants";
      } else if (this.hasMapPath(var1, "enchants")) {
         return "enchants";
      } else if (this.hasMapPath(var1, "params.item.enchants")) {
         return "params.item.enchants";
      } else if (this.hasMapPath(var1, "params.enchants")) {
         return "params.enchants";
      } else if (this.hasMapPath(var1, "item.enchantments")) {
         return "item.enchantments";
      } else if (this.hasMapPath(var1, "enchantments")) {
         return "enchantments";
      } else if (this.hasMapPath(var1, "params.item.enchantments")) {
         return "params.item.enchantments";
      } else if (this.hasMapPath(var1, "params.enchantments")) {
         return "params.enchantments";
      } else {
         Object var2 = this.readMapPathValue(var1, "item");
         if (var2 instanceof Map || this.hasMapPath(var1, "item.material")) {
            return "item.enchants";
         } else if (!this.hasMapPath(var1, "item") && !this.hasMapPath(var1, "material")) {
            Object var3 = this.readMapPathValue(var1, "params.item");
            if (var3 instanceof Map || this.hasMapPath(var1, "params.item.material")) {
               return "params.item.enchants";
            } else if (this.hasMapPath(var1, "params.item") || this.hasMapPath(var1, "params.material")) {
               return "params.enchants";
            } else {
               return var1.containsKey("params") ? "params.enchants" : "enchants";
            }
         } else {
            return "enchants";
         }
      }
   }

   protected boolean setTargetItemEnchantments(EditorSession var1, String var2, Map<String, Integer> var3) {
      Map var4 = this.resolveRawTargetByTargetId(var1, var2);
      if (var4 == null) {
         return false;
      } else {
         Map var5 = this.normalizeTargetItemEnchantments(var3);
         Map var6 = this.normalizeTargetItemEnchantments(this.readTargetItemEnchantments(var4));
         if (var6.equals(var5)) {
            return false;
         } else {
            String var7 = this.resolveTargetItemEnchantmentsWritePath(var4);
            LinkedHashMap var8 = new LinkedHashMap();

            for (Map.Entry<?, ?> var10 : ((Map<?, ?>)var5).entrySet()) {
               var8.put((String)var10.getKey(), var10.getValue());
            }

            this.setMapPathValue(var4, var7, var8);
            return true;
         }
      }
   }

   protected boolean readTargetItemGlowing(EditorSession var1, String var2) {
      Map var3 = this.resolveRawTargetByTargetId(var1, var2);
      if (var3 != null && !var3.isEmpty()) {
         String[] var4 = new String[]{"glowing", "item.glowing", "item.glint", "params.glowing", "params.item.glowing", "params.item.glint"};

         for (Object var8_raw : var4) {
            String var8 = var8_raw != null ? var8_raw.toString() : null;
            Object var9 = this.readMapPathValue(var3, var8);
            if (var9 != null) {
               return this.parseBooleanFlag(var9, false);
            }
         }

         return false;
      } else {
         return false;
      }
   }

   protected String resolveTargetItemGlowingWritePath(Map<String, Object> var1) {
      if (var1 == null) {
         return "glowing";
      } else if (this.hasMapPath(var1, "glowing")) {
         return "glowing";
      } else if (this.hasMapPath(var1, "item.glowing")) {
         return "item.glowing";
      } else if (this.hasMapPath(var1, "item.glint")) {
         return "item.glint";
      } else if (this.hasMapPath(var1, "params.glowing")) {
         return "params.glowing";
      } else if (this.hasMapPath(var1, "params.item.glowing")) {
         return "params.item.glowing";
      } else if (this.hasMapPath(var1, "params.item.glint")) {
         return "params.item.glint";
      } else if (this.readMapPathValue(var1, "item") instanceof Map) {
         return "item.glowing";
      } else {
         return var1.containsKey("params") ? "params.glowing" : "glowing";
      }
   }

   protected boolean setTargetItemGlowing(EditorSession var1, String var2, boolean var3) {
      Map var4 = this.resolveRawTargetByTargetId(var1, var2);
      if (var4 == null) {
         return false;
      } else {
         boolean var5 = this.readTargetItemGlowing(var1, var2);
         if (var5 == var3) {
            return false;
         } else {
            String var6 = this.resolveTargetItemGlowingWritePath(var4);
            this.setMapPathValue(var4, var6, Boolean.valueOf(var3));
            return true;
         }
      }
   }

   @Override
   protected void toggleSidebarItemDesignGlowing(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         List var3 = this.getSidebarEditableTargetIds(var2);
         if (var3.isEmpty()) {
            this.updateEditorPropertiesSidebar(var1, var2);
         } else {
            boolean var4 = false;
            boolean var5 = false;
            boolean var6 = false;

            for (Object var8_raw : var3) {
               String var8 = var8_raw != null ? var8_raw.toString() : null;
               if (this.isSidebarItemDesignTarget(var2, var8)) {
                  var6 = true;
                  boolean var9 = this.setTargetItemGlowing(var2, var8, !this.readTargetItemGlowing(var2, var8));
                  if (var9 && !var5) {
                     this.recordEditorMutationForSidebarInteraction(var2);
                     var5 = true;
                  }

                  var4 |= var9;
               }
            }

            if (var6 && var4) {
               this.rerenderEditableSelection(var1, var2);
            } else {
               this.updateEditorPropertiesSidebar(var1, var2);
            }
         }
      }
   }

   protected String composeRuntimeAnimationScopedTargetId(String var1, String var2) {
      String var3 = this.firstNonBlank(new String[]{var1});
      if (var3.isBlank()) {
         return "";
      } else {
         String var4 = this.normalizeRuntimeAnimationPageKey(var2);
         return var4.isBlank() ? var3 : "__uui_scope__[" + var4 + "]::" + var3;
      }
   }

   protected String resolveRuntimeAnimationTargetScopePageKey(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1});
      if (!var2.startsWith("__uui_scope__[")) {
         return "";
      } else {
         int var3 = var2.indexOf("]::", "__uui_scope__[".length());
         if (var3 < 0) {
            return "";
         } else {
            String var4 = var2.substring("__uui_scope__[".length(), var3);
            return this.normalizeRuntimeAnimationPageKey(var4);
         }
      }
   }

   protected String resolveRuntimeAnimationBaseTargetId(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1});
      if (!var2.startsWith("__uui_scope__[")) {
         return var2;
      } else {
         int var3 = var2.indexOf("]::", "__uui_scope__[".length());
         if (var3 < 0) {
            return var2;
         } else {
            String var4 = var2.substring(var3 + "]::".length());
            return this.firstNonBlank(new String[]{var4, var2});
         }
      }
   }

   protected String resolveRuntimeAnimationEffectiveScopePageKey(EditorSession var1, String var2) {
      String var3 = this.normalizeRuntimeAnimationPageKey(this.resolveRuntimeAnimationTargetScopePageKey(var2));
      if (!var3.isBlank()) {
         return var3;
      } else {
         return var1 == null ? "" : this.normalizeRuntimeAnimationPageKey(var1.runtimeAnimationScopePageKey);
      }
   }

   protected String normalizeRuntimeAnimationPageKey(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim().toLowerCase(Locale.ROOT);
      if (var2.endsWith(".yml")) {
         var2 = var2.substring(0, var2.length() - 4).trim();
      }

      return var2;
   }

   protected boolean doesTargetPathMatchRuntimeAnimationScope(EditorSession var1, String var2, String var3) {
      String var4 = this.resolveRuntimeAnimationEffectiveScopePageKey(var1, var3);
      if (var4.isBlank()) {
         return true;
      } else if (var1 == null) {
         return false;
      } else {
         String var5 = this.firstNonBlank(new String[]{var2});
         if (var5.isBlank()) {
            return false;
         } else {
            String var6 = this.resolveRuntimeAnimationOwnerPageKeyForTargetPath(var1, var5);
            return !var6.isBlank() && var6.equals(var4);
         }
      }
   }

   protected String resolveRuntimeAnimationOwnerPageKeyForTargetPath(EditorSession var1, String var2) {
      if (var1 == null) {
         return "";
      } else {
         String var3 = this.firstNonBlank(new String[]{var2});

         while (!var3.isBlank()) {
            Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3);
            if (var4 != null && !var4.isEmpty()) {
               String var5 = this.normalizeRuntimeAnimationPageKey(this.stringValue(var4.get("xqgui_runtime_hud_stack_page")));
               if (!var5.isBlank()) {
                  return var5;
               }
            }

            int var6 = var3.lastIndexOf(".children.");
            if (var6 < 0) {
               break;
            }

            var3 = var3.substring(0, var6);
         }

         return "";
      }
   }

   @Override
   protected Map<String, Object> resolveRawTargetByTargetId(EditorSession var1, String var2) {
      String var3 = this.resolveRawTargetPathByTargetId(var1, var2);
      return var3.isBlank() ? null : this.resolveRawMapAtPath(var1.rawBlocks, var3);
   }

   @Override
   protected String resolveRawTargetPathByTargetId(EditorSession var1, String var2) {
      String var3 = this.firstNonBlank(new String[]{this.resolveRuntimeAnimationBaseTargetId(var2)});
      String var4 = this.resolveRuntimeAnimationEffectiveScopePageKey(var1, var2);
      if (var1 != null && !var3.isBlank()) {
         LinkedHashSet var5 = this.collectRawTargetCandidatePathsByTargetId(var1, var2);
         if (var5.isEmpty()) {
            return this.resolveRawMapAtPath(var1.rawBlocks, var3) != null ? var3 : "";
         } else {
            String var6 = "";
            int var7 = Integer.MIN_VALUE;
            int var8 = Integer.MAX_VALUE;

            for (Object var10_raw : var5) {
               String var10 = var10_raw != null ? var10_raw.toString() : null;
               String var11 = this.firstNonBlank(new String[]{var10});
               if (!var11.isBlank() && (var4.isBlank() || this.doesTargetPathMatchRuntimeAnimationScope(var1, var11, var2))) {
                  Map var12 = this.resolveRawMapAtPath(var1.rawBlocks, var11);
                  if (var12 != null && !var12.isEmpty()) {
                     byte var13 = 0;
                     if (this.hasAnimationTimelineData(var12)) {
                        var13 += 100;
                     }

                     if (this.isRawTargetInheritedRoot(var12)) {
                        var13 += 10;
                     }

                     int var14 = this.resolveLayerTargetDepth(var11);
                     if (var6.isBlank() || var13 > var7 || var13 == var7 && var14 < var8 || var13 == var7 && var14 == var8 && var11.length() < var6.length()) {
                        var6 = var11;
                        var7 = var13;
                        var8 = var14;
                     }
                  }
               }
            }

            return var6;
         }
      } else {
         return "";
      }
   }

   protected LinkedHashSet<String> collectRawTargetCandidatePathsByTargetId(EditorSession var1, String var2) {
      LinkedHashSet var3 = new LinkedHashSet();
      String var4 = this.firstNonBlank(new String[]{this.resolveRuntimeAnimationBaseTargetId(var2)});
      if (var1 != null && !var4.isBlank()) {
         HoverElement var5 = this.findFirstByTargetId(var1, var2);
         String var6 = this.firstNonBlank(new String[]{var5 == null ? null : var5.targetPath});
         if (!var6.isBlank()) {
            var3.add(var6);
         }

         if (var1.elements != null) {
            for (Object var8_raw : var1.elements) {
               HoverElement var8 = (HoverElement)var8_raw;
               if (var8 != null) {
                  String var9 = this.firstNonBlank(new String[]{this.targetIdOf(var8), var8.targetId});
                  if (this.equalsNullable(var9, var4)) {
                     String var10 = this.firstNonBlank(new String[]{var8.targetPath});
                     if (!var10.isBlank() && this.doesTargetPathMatchRuntimeAnimationScope(var1, var10, var2)) {
                        var3.add(var10);
                     }
                  }
               }
            }
         }

         List var12 = var1.renderBlocks;
         if ((var12 == null || var12.isEmpty()) && var1.rawBlocks != null && !var1.rawBlocks.isEmpty()) {
            var12 = this.resolveRenderableBlocks(var1.rawBlocks, var1.components);
         }

         if (var12 != null) {
            for (Map var15 : (List<Map>)(List<?>)var12) {
               if (var15 != null && !var15.isEmpty()) {
                  String var17 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var15, "__editor_target_id"))});
                  if (this.equalsNullable(var17, var4)) {
                     String var11 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var15, "__editor_target_path"))});
                     if (!var11.isBlank() && this.doesTargetPathMatchRuntimeAnimationScope(var1, var11, var2)) {
                        var3.add(var11);
                     }
                  }
               }
            }
         }

         if (var3.isEmpty()) {
            return var3;
         } else {
            LinkedHashSet var14 = new LinkedHashSet(var3);

            for (Object var18_raw : var3) {
               String var18 = var18_raw != null ? var18_raw.toString() : null;
               String var19 = this.resolveRawTargetInheritedRootPath(var1, var18);
               if (!var19.isBlank()) {
                  var14.add(var19);
               }
            }

            return var14;
         }
      } else {
         return var3;
      }
   }

   protected String resolveRawTargetInheritedRootPath(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = var2;

         while (!var3.isBlank()) {
            Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3);
            if (this.isRawTargetInheritedRoot(var4)) {
               return var3;
            }

            int var5 = var3.lastIndexOf(".children.");
            if (var5 < 0) {
               break;
            }

            var3 = var3.substring(0, var5);
         }

         return "";
      } else {
         return "";
      }
   }

   protected boolean hasAnimationTimelineData(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         Map var2 = this.readAnimationTimelineRowMap(var1, "position");
         if (var2 != null && !var2.isEmpty()) {
            return true;
         } else {
            Map var3 = this.readAnimationTimelineRowMap(var1, "scale");
            if (var3 != null && !var3.isEmpty()) {
               return true;
            } else {
               Map var4 = this.readAnimationTimelineRowMap(var1, "rotation");
               if (var4 != null && !var4.isEmpty()) {
                  return true;
               } else {
                  Map var5 = this.readAnimationTimelineRowMap(var1, "opacity");
                  return var5 != null && !var5.isEmpty();
               }
            }
         }
      } else {
         return false;
      }
   }

   protected boolean isRawTargetInheritedRoot(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         Object var2 = this.readMapPathValue(var1, "__editor_inherit_target_to_children");
         return this.parseBooleanFlag(var2, false) || this.isRawImageGroupRoot(var1);
      } else {
         return false;
      }
   }

   @Override
   protected String readTargetCustomName(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var3 = this.resolveRawMapAtPath(var1.rawBlocks, var2);
         return var3 == null
            ? ""
            : this.firstNonBlank(
               new String[]{this.stringValue(this.readMapPathValue(var3, "name")), this.stringValue(this.readMapPathValue(var3, "params.name"))}
            );
      } else {
         return "";
      }
   }

   protected boolean setTargetName(EditorSession var1, String var2, String var3) {
      HoverElement var4 = this.findFirstByTargetId(var1, var2);
      if (var4 != null && var4.targetPath != null && !var4.targetPath.isBlank()) {
         String var5 = this.firstNonBlank(new String[]{var3}).trim();
         if (var5.isEmpty()) {
            return false;
         } else {
            String var6 = this.buildUniqueTargetDisplayName(var1, var5, var2);
            if (var6.isBlank()) {
               return false;
            } else {
               Map var7 = this.resolveRawMapAtPath(var1.rawBlocks, var4.targetPath);
               if (var7 == null) {
                  return false;
               } else {
                  String var8 = !this.hasMapPath(var7, "name") && var7.containsKey("params") ? "params.name" : "name";
                  String var9 = this.stringValue(this.readMapPathValue(var7, var8));
                  if (var9.equals(var6)) {
                     return false;
                  } else {
                     this.setMapPathValue(var7, var8, var6);
                     return true;
                  }
               }
            }
         }
      } else {
         return false;
      }
   }

   protected String buildUniqueTargetDisplayName(EditorSession var1, String var2, String var3) {
      String var4 = this.firstNonBlank(new String[]{var2}).trim();
      if (var4.isEmpty()) {
         return "";
      } else if (var1 == null) {
         return var4;
      } else {
         var1.renderBlocks = this.resolveRenderableBlocks(var1.rawBlocks, var1.components);
         List var5 = this.collectLayerEntries(var1);
         if (var5 != null && !var5.isEmpty()) {
            String var6 = this.firstNonBlank(new String[]{var3}).trim();
            HashSet var7 = new HashSet();

            for (Object var9_raw : var5) {
               LayersPanelManager.LayerEntry var9 = (LayersPanelManager.LayerEntry)var9_raw;
               if (var9 != null
                  && var9.displayName != null
                  && !var9.displayName.isBlank()
                  && (var6.isBlank() || !var6.equals(this.firstNonBlank(new String[]{var9.targetId})))) {
                  var7.add(var9.displayName.trim().toLowerCase(Locale.ROOT));
               }
            }

            String var10 = var4;

            for (int var11 = 1; var7.contains(var10.toLowerCase(Locale.ROOT)); var11++) {
               var10 = var4 + " #" + var11;
            }

            return var10;
         } else {
            return var4;
         }
      }
   }

   protected boolean setTargetLogicalId(EditorSession var1, String var2, String var3) {
      String var4 = this.firstNonBlank(new String[]{var3}).trim();
      if (var4.isEmpty()) {
         return false;
      } else {
         Map var5 = this.resolveRawTargetByTargetId(var1, var2);
         if (var5 == null) {
            return false;
         } else {
            String var6 = this.firstNonBlank(
               new String[]{
                  this.stringValue(this.readMapPathValue(var5, "id")),
                  this.stringValue(this.readMapPathValue(var5, "params.id")),
                  this.firstNonBlank(new String[]{var2})
               }
            );
            if (var6.equals(var4)) {
               return false;
            } else {
               String var7 = !this.hasMapPath(var5, "id") && var5.containsKey("params") ? "params.id" : "id";
               this.setMapPathValue(var5, var7, var4);
               return true;
            }
         }
      }
   }

   @Override
   protected String findTargetIdByPath(EditorSession var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         for (Object var4_raw : var1.elements) {
            HoverElement var4 = (HoverElement)var4_raw;
            if (var4 != null && var4.targetPath != null && var2.equals(var4.targetPath)) {
               String var5 = this.firstNonBlank(new String[]{var4.targetId});
               if (!var5.isBlank()) {
                  return var5;
               }
            }
         }

         return "";
      } else {
         return "";
      }
   }

   protected boolean isReservedEditorTargetId(EditorSession var1, String var2, String var3) {
      String var4 = this.firstNonBlank(new String[]{var2}).trim().toLowerCase(Locale.ROOT);
      if (var4.isBlank()) {
         return true;
      } else if (var4.equals(this.firstNonBlank(new String[]{var3}).trim().toLowerCase(Locale.ROOT))) {
         return false;
      } else if (!var4.equals("preview")
         && !var4.equals("editor")
         && !var4.equals("editor_empty")
         && !var4.equals("editor_menu")
         && !var4.startsWith("preview_content_")
         && !var4.startsWith("editor_shell_")) {
         for (Object var8_raw : EDITOR_OVERLAY_IDS) {
            String var8 = var8_raw != null ? var8_raw.toString() : null;
            if (var4.equals(this.firstNonBlank(new String[]{var8}).toLowerCase(Locale.ROOT))) {
               return true;
            }
         }

         if (var1 != null && var1.shellBlocks != null) {
            for (Map var10 : var1.shellBlocks) {
               String var11 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var10, "id"))}).toLowerCase(Locale.ROOT);
               if (!var11.isBlank() && var4.equals(var11)) {
                  return true;
               }
            }
         }

         return false;
      } else {
         return true;
      }
   }

   protected boolean hasConflictingContentTargetId(EditorSession var1, String var2, String var3) {
      String var4 = this.firstNonBlank(new String[]{var2}).trim();
      if (var4.isBlank()) {
         return true;
      } else {
         for (LayersPanelManager.LayerEntry var6 : this.collectLayerEntries(var1)) {
            if (var6 != null && var6.targetId != null && !var6.targetId.isBlank() && !var6.targetId.equals(var3) && var6.targetId.equalsIgnoreCase(var4)) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   protected double readTargetLayer(EditorSession var1, String var2) {
      HoverElement var3 = this.findFirstByTargetId(var1, var2);
      if (var3 != null && Double.isFinite(var3.z)) {
         return var3.z;
      } else {
         Map var4 = this.resolveRawTargetByTargetId(var1, var2);
         if (var4 == null) {
            return 0.0;
         } else if (this.hasMapPath(var4, "layer")) {
            return this.readMapPathDouble(var4, "layer", 0.0);
         } else if (this.hasMapPath(var4, "size.depth")) {
            return this.readMapPathDouble(var4, "size.depth", 0.0);
         } else if (this.hasMapPath(var4, "depth")) {
            return this.readMapPathDouble(var4, "depth", 0.0);
         } else if (this.hasMapPath(var4, "params.layer")) {
            return this.readMapPathDouble(var4, "params.layer", 0.0);
         } else {
            return this.hasMapPath(var4, "params.depth") ? this.readMapPathDouble(var4, "params.depth", 0.0) : 0.0;
         }
      }
   }

   @Override
   protected String readTargetColor(EditorSession var1, String var2) {
      HoverElement var3 = this.findFirstByTargetId(var1, var2);
      if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
         Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
         if (var4 == null) {
            return "ffffff";
         } else {
            String var5 = this.firstNonBlank(
               new String[]{
                  this.stringValue(this.readMapPathValue(var4, "color")),
                  this.stringValue(this.readMapPathValue(var4, "style.color")),
                  this.stringValue(this.readMapPathValue(var4, "params.color")),
                  this.stringValue(this.readMapPathValue(var4, "params.style.color")),
                  "ffffff"
               }
            );
            String var6 = this.normalizeHexColor(var5);
            return var6.isBlank() ? "ffffff" : var6;
         }
      } else {
         return "ffffff";
      }
   }

   @Override
   protected String readTargetOutlineColor(EditorSession var1, String var2) {
      HoverElement var3 = this.findFirstByTargetId(var1, var2);
      if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
         Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
         if (var4 == null) {
            return "ffffff";
         } else {
            String var5 = this.firstNonBlank(
               new String[]{
                  this.stringValue(this.readMapPathValue(var4, "outline.color")),
                  this.stringValue(this.readMapPathValue(var4, "outline.style.color")),
                  this.stringValue(this.readMapPathValue(var4, "params.outline.color")),
                  this.stringValue(this.readMapPathValue(var4, "params.outline.style.color"))
               }
            );
            String var6 = this.normalizeHexColor(var5);
            if (!var6.isBlank()) {
               return var6;
            } else {
               String var7 = this.readTargetColor(var1, var2);
               return var7.isBlank() ? "ffffff" : var7;
            }
         }
      } else {
         return "ffffff";
      }
   }

   @Override
   protected boolean isTargetOutlineColorAuto(EditorSession var1, String var2) {
      HoverElement var3 = this.findFirstByTargetId(var1, var2);
      if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
         Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
         return var4 == null ? false : this.isRawTargetOutlineColorAuto(var4);
      } else {
         return false;
      }
   }

   protected String readTargetOutlineSidebarValue(EditorSession var1, String var2) {
      return this.isTargetOutlineColorAuto(var1, var2) ? "Auto" : this.formatSidebarHexValue(this.readTargetOutlineColor(var1, var2));
   }

   protected String readTargetHoverColor(EditorSession var1, String var2) {
      HoverElement var3 = this.findFirstByTargetId(var1, var2);
      if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
         Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
         if (var4 == null) {
            return "auto";
         } else {
            String var5 = this.firstNonBlank(
               new String[]{
                  this.stringValue(this.readMapPathValue(var4, "hover.color")),
                  this.stringValue(this.readMapPathValue(var4, "style.hover.color")),
                  this.stringValue(this.readMapPathValue(var4, "params.hover.color")),
                  this.stringValue(this.readMapPathValue(var4, "params.style.hover.color")),
                  this.stringValue(this.readMapPathValue(var4, "hoverColor")),
                  this.stringValue(this.readMapPathValue(var4, "params.hoverColor"))
               }
            );
            if (var5.isBlank()) {
               return "block".equals(this.resolveRawTargetType(var4, var3)) ? "none" : "auto";
            } else {
               return this.normalizeHoverColorSettingToken(var5);
            }
         }
      } else {
         return "auto";
      }
   }

   protected String readTargetHoverEffect(EditorSession var1, String var2) {
      if (var1 != null && var1.activeEffectOverride != null && !var1.activeEffectOverride.isBlank()) {
         return var1.activeEffectOverride;
      } else {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
            Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
            if (var4 == null) {
               return "none";
            } else {
               String var5 = this.firstNonBlank(
                  new String[]{
                     this.stringValue(this.readMapPathValue(var4, "hover.effect")),
                     this.stringValue(this.readMapPathValue(var4, "style.hover.effect")),
                     this.stringValue(this.readMapPathValue(var4, "params.hover.effect")),
                     this.stringValue(this.readMapPathValue(var4, "params.style.hover.effect")),
                     this.stringValue(this.readMapPathValue(var4, "hoverEffect")),
                     this.stringValue(this.readMapPathValue(var4, "params.hoverEffect"))
                  }
               );
               String var6 = this.normalizeHoverEffectId(var5);
               return var6.isBlank() ? "none" : var6;
            }
         } else {
            return "none";
         }
      }
   }

   protected String readTargetRadiusLabel(EditorSession var1, String var2) {
      return this.readTargetBorderRadiusMode(var1, var2);
   }

   protected String readTargetBorderRadiusMode(EditorSession var1, String var2) {
      HoverElement var3 = this.findFirstByTargetId(var1, var2);
      if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
         Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
         if (var4 == null) {
            return "None";
         } else {
            String var5 = this.resolveRawTargetType(var4, var3);
            if (!this.isRoundedType(var5)) {
               return "None";
            } else {
               String var6 = this.resolveSidebarBorderRadiusModeFromStoredValue(this.stringValue(this.readMapPathValue(var4, "rounding")));
               if (!var6.isBlank()) {
                  return var6;
               } else {
                  String var7 = this.stripRoundedCornerColorTags(this.readRoundedCornerUnicodeByKey(var4, "tl"));
                  String var8 = this.stripRoundedCornerColorTags(this.readRoundedCornerUnicodeByKey(var4, "tr"));
                  String var9 = this.stripRoundedCornerColorTags(this.readRoundedCornerUnicodeByKey(var4, "br"));
                  String var10 = this.stripRoundedCornerColorTags(this.readRoundedCornerUnicodeByKey(var4, "bl"));
                  if (var7.isBlank() && var8.isBlank() && var9.isBlank() && var10.isBlank()) {
                     return "Regular";
                  } else if (this.matchesRoundedCornerGlyphSet(var7, var8, var9, var10, SIDEBAR_ROUNDED_GLYPHS_SMALL)) {
                     return "Small";
                  } else if (this.matchesRoundedCornerGlyphSet(var7, var8, var9, var10, SIDEBAR_ROUNDED_GLYPHS_MEDIUM)) {
                     return "Medium";
                  } else if (this.matchesRoundedCornerGlyphSet(var7, var8, var9, var10, SIDEBAR_ROUNDED_GLYPHS_LARGE)) {
                     return "Large";
                  } else {
                     return this.matchesRoundedCornerGlyphSet(var7, var8, var9, var10, SIDEBAR_ROUNDED_GLYPHS_REGULAR) ? "Regular" : "Regular";
                  }
               }
            }
         }
      } else {
         return "None";
      }
   }

   protected boolean matchesRoundedCornerGlyphSet(String var1, String var2, String var3, String var4, String[] var5) {
      return var5 != null
         && var5.length >= 4
         && this.equalsNullable(this.stripRoundedCornerColorTags(var1), var5[0])
         && this.equalsNullable(this.stripRoundedCornerColorTags(var2), var5[1])
         && this.equalsNullable(this.stripRoundedCornerColorTags(var3), var5[2])
         && this.equalsNullable(this.stripRoundedCornerColorTags(var4), var5[3]);
   }

   protected String readRoundedCornerUnicodeByKey(Map<String, Object> var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = var2.trim().toLowerCase(Locale.ROOT);

         String[] var4 = switch (var3) {
            case "tl" -> new String[]{"tl", "top_left", "topLeft", "top-left"};
            case "tr" -> new String[]{"tr", "top_right", "topRight", "top-right"};
            case "br" -> new String[]{"br", "bottom_right", "bottomRight", "bottom-right"};
            case "bl" -> new String[]{"bl", "bottom_left", "bottomLeft", "bottom-left"};
            default -> new String[]{var3};
         };

         for (Object var8_raw : var4) {
            String var8 = var8_raw != null ? var8_raw.toString() : null;
            String var9 = this.firstNonBlank(
               new String[]{
                  this.stringValue(this.readMapPathValue(var1, "rounding.unicode." + var8)),
                  this.stringValue(this.readMapPathValue(var1, "rounded.unicode." + var8)),
                  this.stringValue(this.readMapPathValue(var1, "rounding.corners." + var8)),
                  this.stringValue(this.readMapPathValue(var1, "rounded.corners." + var8)),
                  this.stringValue(this.readMapPathValue(var1, "rounding.corner." + var8)),
                  this.stringValue(this.readMapPathValue(var1, "rounded.corner." + var8)),
                  this.stringValue(this.readMapPathValue(var1, "roundingUnicode." + var8)),
                  this.stringValue(this.readMapPathValue(var1, "cornerUnicode." + var8))
               }
            );
            if (!var9.isBlank()) {
               return var9;
            }
         }

         return "";
      } else {
         return "";
      }
   }

   protected String stripRoundedCornerColorTags(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1});
      return var2.isBlank() ? "" : var2.replaceAll("(?i)<#[0-9a-f]{6}>", "").trim();
   }

   protected String resolveSidebarBorderRadiusModeFromStoredValue(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim().toLowerCase(Locale.ROOT);
      if (var2.isBlank()) {
         return "";
      } else {
         return switch (var2) {
            case "none", "off", "disabled", "false", "0" -> "None";
            case "small" -> "Small";
            case "regular", "default", "normal" -> "Regular";
            case "medium" -> "Medium";
            case "large" -> "Large";
            default -> "";
         };
      }
   }

   protected String serializeSidebarBorderRadiusMode(String var1) {
      String var2 = this.normalizeSidebarBorderRadiusMode(var1);

      return switch (var2) {
         case "Small" -> "small";
         case "Regular" -> "regular";
         case "Medium" -> "medium";
         case "Large" -> "large";
         default -> "none";
      };
   }

   protected String normalizeSidebarBorderRadiusMode(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim().toLowerCase(Locale.ROOT);
      if (var2.equals("Small".toLowerCase(Locale.ROOT))) {
         return "Small";
      } else if (var2.equals("Regular".toLowerCase(Locale.ROOT))) {
         return "Regular";
      } else if (var2.equals("Medium".toLowerCase(Locale.ROOT))) {
         return "Medium";
      } else {
         return var2.equals("Large".toLowerCase(Locale.ROOT)) ? "Large" : "None";
      }
   }

   protected String nextSidebarBorderRadiusMode(String var1) {
      String var2 = this.normalizeSidebarBorderRadiusMode(var1);

      return switch (var2) {
         case "Small" -> "Regular";
         case "Regular" -> "Medium";
         case "Medium" -> "Large";
         case "Large" -> "None";
         default -> "Small";
      };
   }

   protected boolean isSidebarBorderRadiusApplicableType(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).toLowerCase(Locale.ROOT);
      return "block".equals(var2) || this.isRoundedType(var2);
   }

   protected String resolveRawTargetType(Map<String, Object> var1, HoverElement var2) {
      String var3 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var1, "type"))}).toLowerCase(Locale.ROOT);
      return !var3.isBlank() ? var3 : this.firstNonBlank(new String[]{var2 == null ? "" : var2.type}).toLowerCase(Locale.ROOT);
   }

   protected String[] resolveSidebarBorderRadiusGlyphSet(String var1) {
      String var2 = this.normalizeSidebarBorderRadiusMode(var1);

      return switch (var2) {
         case "Small" -> SIDEBAR_ROUNDED_GLYPHS_SMALL;
         case "Medium" -> SIDEBAR_ROUNDED_GLYPHS_MEDIUM;
         case "Large" -> SIDEBAR_ROUNDED_GLYPHS_LARGE;
         default -> SIDEBAR_ROUNDED_GLYPHS_REGULAR;
      };
   }

   protected String resolveRoundedCornerUnicodeWritePath(String var1) {
      return "rounding.unicode." + this.firstNonBlank(new String[]{var1}).toLowerCase(Locale.ROOT);
   }

   protected boolean setMapPathStringValue(Map<String, Object> var1, String var2, String var3) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var4 = var3 == null ? "" : var3;
         String var5 = this.stringValue(this.readMapPathValue(var1, var2));
         if (this.equalsNullable(var5, var4)) {
            return false;
         } else {
            this.setMapPathValue(var1, var2, var4);
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   protected String stringValue(Object var1) {
      return var1 == null ? "" : var1.toString().trim();
   }

   @Override
   protected boolean setTargetRotation(EditorSession var1, String var2, double var3) {
      Map var5 = this.resolveRawTargetByTargetId(var1, var2);
      if (var5 == null) {
         return false;
      } else {
         String var6 = this.resolveRawTargetType(var5, this.findFirstByTargetId(var1, var2));
         if (this.isRoundedType(var6)) {
            return false;
         } else {
            var3 = this.snap1(this.normalizeSidebarRotation(var3));
            String var7 = var5.containsKey("rotation") ? "rotation" : (var5.containsKey("rotate") ? "rotate" : "rotation");
            double var8 = this.readMapPathDouble(var5, var7, 0.0);
            if (Math.abs(var8 - var3) < 1.0E-4) {
               return false;
            } else {
               this.setMapPathValue(var5, var7, Double.valueOf(var3));
               return true;
            }
         }
      }
   }

   @Override
   protected boolean setTargetOpacity(EditorSession var1, String var2, int var3) {
      String var4 = this.resolveRawTargetPathByTargetId(var1, var2);
      if (var4.isBlank()) {
         return false;
      } else {
         Map var5 = this.resolveRawMapAtPath(var1.rawBlocks, var4);
         if (var5 == null) {
            return false;
         } else {
            LinkedHashSet var6 = new LinkedHashSet();
            if (this.isRawTargetInheritedRoot(var5)) {
               this.collectRawLayerTargetsRecursive(var5, var6);
            } else {
               var6.add(var5);
            }

            if (var6.isEmpty()) {
               return false;
            } else {
               int var7 = Math.max(0, Math.min(255, var3));
               boolean var8 = false;

               for (Map var10 : (List<Map>)(List<?>)var6) {
                  if (var10 != null && !var10.isEmpty()) {
                     int var11 = (int)Math.round(this.readMapPathDouble(var10, "opacity", 255.0));
                     if (var11 != var7) {
                        this.setMapPathValue(var10, "opacity", Integer.valueOf(var7));
                        var8 = true;
                     }
                  }
               }

               return var8;
            }
         }
      }
   }

   @Override
   protected boolean setTargetColor(EditorSession var1, String var2, String var3) {
      HoverElement var4 = this.findFirstByTargetId(var1, var2);
      if (var4 != null && var4.targetPath != null && !var4.targetPath.isBlank()) {
         Map var5 = this.resolveRawMapAtPath(var1.rawBlocks, var4.targetPath);
         if (var5 == null) {
            return false;
         } else {
            String var6 = this.normalizeHexColor(var3);
            if (var6.isBlank()) {
               return false;
            } else {
               String var7;
               if (this.hasMapPath(var5, "color")) {
                  var7 = "color";
               } else if (this.hasMapPath(var5, "style.color")) {
                  var7 = "style.color";
               } else if (this.hasMapPath(var5, "params.color")) {
                  var7 = "params.color";
               } else if (this.hasMapPath(var5, "params.style.color")) {
                  var7 = "params.style.color";
               } else {
                  var7 = var5.containsKey("params") ? "params.color" : "color";
               }

               String var8 = this.normalizeHexColor(this.stringValue(this.readMapPathValue(var5, var7)));
               if (var8.isBlank()) {
                  var8 = "ffffff";
               }

               if (var8.equalsIgnoreCase(var6)) {
                  return false;
               } else {
                  this.setMapPathValue(var5, var7, var6);
                  if (this.isRawTargetOutlineColorAuto(var5)) {
                     this.applyRoundedCornerUnicodeColor(var5, var6);
                  }

                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   @Override
   protected boolean setTargetHoverColor(EditorSession var1, String var2, String var3) {
      HoverElement var4 = this.findFirstByTargetId(var1, var2);
      if (var4 != null && var4.targetPath != null && !var4.targetPath.isBlank()) {
         Map var5 = this.resolveRawMapAtPath(var1.rawBlocks, var4.targetPath);
         if (var5 == null) {
            return false;
         } else {
            String var6 = this.normalizeHoverColorSettingToken(var3);
            String var7 = this.resolveTargetHoverColorWritePath(var5);
            String var8 = this.stringValue(this.readMapPathValue(var5, var7));
            String var9 = this.normalizeHoverColorSettingToken(var8);
            if (this.equalsNullable(var9, var6)) {
               return false;
            } else {
               this.setMapPathValue(var5, var7, var6);
               return true;
            }
         }
      } else {
         return false;
      }
   }

   protected boolean setTargetHoverEffect(EditorSession var1, String var2, String var3) {
      HoverElement var4 = this.findFirstByTargetId(var1, var2);
      if (var4 != null && var4.targetPath != null && !var4.targetPath.isBlank()) {
         Map var5 = this.resolveRawMapAtPath(var1.rawBlocks, var4.targetPath);
         if (var5 == null) {
            return false;
         } else {
            String var6 = this.normalizeHoverEffectId(var3);
            String var7 = this.resolveTargetHoverEffectWritePath(var5);
            String var8 = this.stringValue(this.readMapPathValue(var5, var7));
            String var9 = this.normalizeHoverEffectId(var8);
            if (this.equalsNullable(var9, var6)) {
               return false;
            } else {
               this.setMapPathValue(var5, var7, var6);
               return true;
            }
         }
      } else {
         return false;
      }
   }

   protected boolean setTargetBorderRadiusMode(EditorSession var1, String var2, String var3) {
      HoverElement var4 = this.findFirstByTargetId(var1, var2);
      if (var4 != null && var4.targetPath != null && !var4.targetPath.isBlank()) {
         Map var5 = this.resolveRawMapAtPath(var1.rawBlocks, var4.targetPath);
         if (var5 == null) {
            return false;
         } else {
            String var6 = this.resolveRawTargetType(var5, var4);
            if (!this.isSidebarBorderRadiusApplicableType(var6)) {
               return false;
            } else {
               String var7 = this.normalizeSidebarBorderRadiusMode(var3);
               boolean var8 = false;
               if ("None".equals(var7)) {
                  var8 |= this.setMapPathStringValue(var5, "type", "block");
                  return var8 | this.setMapPathStringValue(var5, "rounding", "none");
               } else {
                  var8 |= this.setMapPathStringValue(var5, "type", "rounded");
                  var8 |= this.setMapPathStringValue(var5, "rounding", this.serializeSidebarBorderRadiusMode(var7));
                  String var9 = this.readRawTargetOutlineColor(var5);
                  String[] var10 = this.resolveSidebarBorderRadiusGlyphSet(var7);

                  for (int var11 = 0; var11 < SIDEBAR_ROUNDED_CORNER_KEYS.length; var11++) {
                     String var12 = SIDEBAR_ROUNDED_CORNER_KEYS[var11];
                     String var13 = var10 != null && var11 < var10.length ? this.firstNonBlank(new String[]{var10[var11]}) : "";
                     if (var13.isBlank()) {
                        var13 = this.stripRoundedCornerColorTags(this.readRoundedCornerUnicodeByKey(var5, var12));
                     }

                     if (!var13.isBlank()) {
                        String var14 = this.formatRoundedCornerUnicodeWithColor(var9, var13);
                        var8 |= this.setMapPathStringValue(var5, this.resolveRoundedCornerUnicodeWritePath(var12), var14);
                     }
                  }

                  return var8;
               }
            }
         }
      } else {
         return false;
      }
   }

   protected String readRawTargetOutlineColor(Map<String, Object> var1) {
      if (var1 == null) {
         return "ffffff";
      } else {
         String var2 = this.firstNonBlank(
            new String[]{
               this.stringValue(this.readMapPathValue(var1, "outline.color")),
               this.stringValue(this.readMapPathValue(var1, "outline.style.color")),
               this.stringValue(this.readMapPathValue(var1, "params.outline.color")),
               this.stringValue(this.readMapPathValue(var1, "params.outline.style.color"))
            }
         );
         String var3 = this.normalizeHexColor(var2);
         return !var3.isBlank() ? var3 : this.readRawTargetFillColor(var1);
      }
   }

   protected String readRawTargetFillColor(Map<String, Object> var1) {
      if (var1 == null) {
         return "ffffff";
      } else {
         String var2 = this.firstNonBlank(
            new String[]{
               this.stringValue(this.readMapPathValue(var1, "color")),
               this.stringValue(this.readMapPathValue(var1, "style.color")),
               this.stringValue(this.readMapPathValue(var1, "params.color")),
               this.stringValue(this.readMapPathValue(var1, "params.style.color")),
               "ffffff"
            }
         );
         String var3 = this.normalizeHexColor(var2);
         return var3.isBlank() ? "ffffff" : var3;
      }
   }

   protected boolean isRawTargetOutlineColorAuto(Map<String, Object> var1) {
      if (var1 == null) {
         return true;
      } else {
         String var2 = this.firstNonBlank(
            new String[]{
               this.stringValue(this.readMapPathValue(var1, "outline.color")),
               this.stringValue(this.readMapPathValue(var1, "outline.style.color")),
               this.stringValue(this.readMapPathValue(var1, "params.outline.color")),
               this.stringValue(this.readMapPathValue(var1, "params.outline.style.color"))
            }
         );
         if (var2.isBlank()) {
            return true;
         } else {
            String var3 = this.normalizeHexColor(var2);
            return var3.isBlank();
         }
      }
   }

   protected boolean clearRawTargetOutlineColor(Map<String, Object> var1) {
      if (var1 == null) {
         return false;
      } else {
         boolean var2 = false;
         var2 |= this.setMapPathStringValue(var1, "outline.color", "");
         var2 |= this.setMapPathStringValue(var1, "outline.style.color", "");
         var2 |= this.setMapPathStringValue(var1, "params.outline.color", "");
         return var2 | this.setMapPathStringValue(var1, "params.outline.style.color", "");
      }
   }

   protected String formatRoundedCornerUnicodeWithColor(String var1, String var2) {
      String var3 = this.stripRoundedCornerColorTags(var2);
      if (var3.isBlank()) {
         return "";
      } else {
         String var4 = this.normalizeHexColor(var1);
         return var4.isBlank() ? var3 : "<#" + var4 + ">" + var3;
      }
   }

   protected boolean applyRoundedCornerUnicodeColor(Map<String, Object> var1, String var2) {
      if (var1 != null && this.isRoundedType(this.resolveRawTargetType(var1, null))) {
         String var3 = this.resolveSidebarBorderRadiusModeFromStoredValue(this.stringValue(this.readMapPathValue(var1, "rounding")));
         String var4 = var3.isBlank() ? "Regular" : var3;
         String[] var5 = "None".equals(var4) ? new String[0] : this.resolveSidebarBorderRadiusGlyphSet(var4);
         boolean var6 = false;

         for (int var7 = 0; var7 < SIDEBAR_ROUNDED_CORNER_KEYS.length; var7++) {
            String var8 = SIDEBAR_ROUNDED_CORNER_KEYS[var7];
            String var9 = this.stripRoundedCornerColorTags(this.readRoundedCornerUnicodeByKey(var1, var8));
            if (var9.isBlank() && var5 != null && var7 < var5.length) {
               var9 = this.stripRoundedCornerColorTags(var5[var7]);
            }

            if (!var9.isBlank()) {
               String var10 = this.formatRoundedCornerUnicodeWithColor(var2, var9);
               var6 |= this.setMapPathStringValue(var1, this.resolveRoundedCornerUnicodeWritePath(var8), var10);
            }
         }

         return var6;
      } else {
         return false;
      }
   }

   protected boolean syncRoundedCornerUnicodeColorInheritance(Map<String, Object> var1) {
      if (var1 != null && this.isRoundedType(this.resolveRawTargetType(var1, null))) {
         boolean var2 = false;

         for (Object var6_raw : SIDEBAR_ROUNDED_CORNER_KEYS) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            String var7 = this.stripRoundedCornerColorTags(this.readRoundedCornerUnicodeByKey(var1, var6));
            if (!var7.isBlank()) {
               var2 |= this.setMapPathStringValue(var1, this.resolveRoundedCornerUnicodeWritePath(var6), var7);
            }
         }

         return var2;
      } else {
         return false;
      }
   }

   protected String resolveTargetHoverColorWritePath(Map<String, Object> var1) {
      if (this.hasMapPath(var1, "hover.color")) {
         return "hover.color";
      } else if (this.hasMapPath(var1, "style.hover.color")) {
         return "style.hover.color";
      } else if (this.hasMapPath(var1, "params.hover.color")) {
         return "params.hover.color";
      } else if (this.hasMapPath(var1, "params.style.hover.color")) {
         return "params.style.hover.color";
      } else if (this.hasMapPath(var1, "hoverColor")) {
         return "hoverColor";
      } else if (this.hasMapPath(var1, "params.hoverColor")) {
         return "params.hoverColor";
      } else {
         return var1.containsKey("params") ? "params.hover.color" : "hover.color";
      }
   }

   protected String resolveTargetHoverEffectWritePath(Map<String, Object> var1) {
      if (this.hasMapPath(var1, "hover.effect")) {
         return "hover.effect";
      } else if (this.hasMapPath(var1, "style.hover.effect")) {
         return "style.hover.effect";
      } else if (this.hasMapPath(var1, "params.hover.effect")) {
         return "params.hover.effect";
      } else if (this.hasMapPath(var1, "params.style.hover.effect")) {
         return "params.style.hover.effect";
      } else if (this.hasMapPath(var1, "hoverEffect")) {
         return "hoverEffect";
      } else if (this.hasMapPath(var1, "params.hoverEffect")) {
         return "params.hoverEffect";
      } else {
         return var1.containsKey("params") ? "params.hover.effect" : "hover.effect";
      }
   }

   protected boolean setTargetText(EditorSession var1, String var2, String var3) {
      HoverElement var4 = this.findFirstByTargetId(var1, var2);
      if (var4 != null && var4.targetPath != null && !var4.targetPath.isBlank()) {
         if (!"text".equalsIgnoreCase(this.firstNonBlank(new String[]{var4.type}))) {
            return false;
         } else {
            Map var5 = this.resolveRawMapAtPath(var1.rawBlocks, var4.targetPath);
            if (var5 == null) {
               return false;
            } else {
               String var6;
               if (this.hasMapPath(var5, "text")) {
                  var6 = "text";
               } else if (this.hasMapPath(var5, "params.text")) {
                  var6 = "params.text";
               } else {
                  var6 = var5.containsKey("params") ? "params.text" : "text";
               }

               String var7 = var3 == null ? "" : var3;
               String var8 = this.stringValue(this.readMapPathValue(var5, var6));
               if (this.equalsNullable(var8, var7)) {
                  return false;
               } else {
                  this.setMapPathValue(var5, var6, var7);
                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   @Override
   protected boolean setTargetOutlineColor(EditorSession var1, String var2, String var3) {
      HoverElement var4 = this.findFirstByTargetId(var1, var2);
      if (var4 != null && var4.targetPath != null && !var4.targetPath.isBlank()) {
         Map var5 = this.resolveRawMapAtPath(var1.rawBlocks, var4.targetPath);
         if (var5 == null) {
            return false;
         } else {
            String var6 = this.normalizeHexColor(var3);
            if (var6.isBlank()) {
               return false;
            } else {
               String var7;
               if (this.hasMapPath(var5, "outline.color")) {
                  var7 = "outline.color";
               } else if (this.hasMapPath(var5, "outline.style.color")) {
                  var7 = "outline.style.color";
               } else if (this.hasMapPath(var5, "params.outline.color")) {
                  var7 = "params.outline.color";
               } else if (this.hasMapPath(var5, "params.outline.style.color")) {
                  var7 = "params.outline.style.color";
               } else {
                  var7 = var5.containsKey("params") ? "params.outline.color" : "outline.color";
               }

               String var8 = this.readRawTargetOutlineColor(var5);
               if (var8.equalsIgnoreCase(var6)) {
                  return false;
               } else {
                  this.setMapPathValue(var5, var7, var6);
                  this.applyRoundedCornerUnicodeColor(var5, var6);
                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   @Override
   protected boolean setTargetOutlineColorAuto(EditorSession var1, String var2) {
      HoverElement var3 = this.findFirstByTargetId(var1, var2);
      if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
         Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
         if (var4 == null) {
            return false;
         } else {
            boolean var5 = this.clearRawTargetOutlineColor(var4);
            return var5 | this.applyRoundedCornerUnicodeColor(var4, this.readRawTargetFillColor(var4));
         }
      } else {
         return false;
      }
   }

   @Override
   protected double readTargetRotation(EditorSession var1, String var2) {
      Map var3 = this.resolveRawTargetByTargetId(var1, var2);
      if (var3 == null) {
         return 0.0;
      } else if (this.hasMapPath(var3, "rotation")) {
         return this.readMapPathDouble(var3, "rotation", 0.0);
      } else {
         return this.hasMapPath(var3, "rotate") ? this.readMapPathDouble(var3, "rotate", 0.0) : 0.0;
      }
   }

   @Override
   protected int readTargetOpacity(EditorSession var1, String var2) {
      HoverElement var3 = this.findFirstByTargetId(var1, var2);
      if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
         Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
         return var4 == null ? 255 : Math.max(0, Math.min(255, (int)Math.round(this.readMapPathDouble(var4, "opacity", 255.0))));
      } else {
         return 255;
      }
   }
}
