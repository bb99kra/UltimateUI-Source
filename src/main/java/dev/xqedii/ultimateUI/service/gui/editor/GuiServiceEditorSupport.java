package dev.xqedii.ultimateUI.service.gui.editor;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.api.UiSession;
import dev.xqedii.ultimateUI.gui.model.HoverElement;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.GuiService;
import dev.xqedii.ultimateUI.service.gui.editor.shell.managers.AnimationTimelineOperationsManagerBase;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.gui.model.EditorSidebarTab;
import dev.xqedii.ultimateUI.service.gui.model.EditorTool;
import dev.xqedii.ultimateUI.service.gui.model.PreviewViewport;
import dev.xqedii.ultimateUI.service.hud.HudPositionCalculator;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.util.PlatformCompat;
import com.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.util.SpigotConversionUtil;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSetSlot;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.util.Vector;

public abstract class GuiServiceEditorSupport extends GuiServiceEditorSupportB {
   protected GuiServiceEditorSupport(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   @Override
   protected ItemStack resolveInventoryPickSource(ItemStack... var1) {
      if (var1 != null && var1.length != 0) {
         for (ItemStack var5 : var1) {
            if (var5 != null && var5.getType() != Material.AIR) {
               return var5;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   @Override
   protected Map<String, Integer> serializeItemSelectionEnchantments(ItemStack var1) {
      if (var1 != null && var1.getType() != Material.AIR) {
         Map var2 = var1.getEnchantments();
         if (var2 != null && !var2.isEmpty()) {
            LinkedHashMap<String, Integer> var3 = new LinkedHashMap<>();

            for (Map.Entry<?, ?> var5 : ((Map<?, ?>)var2).entrySet()) {
               Enchantment var6 = (Enchantment)var5.getKey();
               if (var6 != null) {
                  Integer var7 = (Integer)var5.getValue();
                  if (var7 != null && var7 > 0) {
                     String var8 = var6.getKey() == null ? "" : var6.getKey().toString();
                     var8 = this.firstNonBlank(new String[]{var8, var6.getName()}).toLowerCase(Locale.ROOT);
                     if (!var8.isBlank()) {
                        var3.put(var8, var7);
                     }
                  }
               }
            }

            return (Map<String, Integer>)(var3.isEmpty() ? Collections.emptyMap() : var3);
         } else {
            return Collections.emptyMap();
         }
      } else {
         return Collections.emptyMap();
      }
   }

   @Override
   protected boolean openGui(Player var1, String var2, boolean var3) {
      return this.openGui(var1, var2, var3, false);
   }

   @Override
   protected boolean openGui(Player var1, String var2, boolean var3, boolean var4) {
      return this.openGui(var1, var2, var3, var4, false, false);
   }

   @Override
   protected boolean openGui(Player var1, String var2, boolean var3, boolean var4, boolean var5) {
      return this.openGui(var1, var2, var3, var4, var5, false);
   }

   @Override
   protected boolean openGui(Player var1, String var2, boolean var3, boolean var4, boolean var5, boolean var6) {
      ArrayList var7 = new ArrayList();
      Map var8 = this.loadYamlFolder("contents/pages", var7);
      Map var9 = this.loadYamlFolder("contents/components", var7);
      if (!var7.isEmpty()) {
         this.reportYamlIssues(var7);
         this.sendEditorPlayerMessage(
            var1,
            MM.deserialize("<red><bold>UltimateUI</bold></red> <#8a989c>»</#8a989c> <yellow>YAML syntax errors found. Check console for details.</yellow>")
         );
         return false;
      } else {
         String var10 = this.normalizePageKey(var2, var2);
         String var11 = this.resolveExistingPageMapKey(var8, var10);
         YamlConfiguration var12 = var11.isBlank() ? null : (YamlConfiguration)var8.get(var11);
         if (var12 == null) {
            this.sendEditorPlayerMessage(
               var1, MM.deserialize("<red><bold>UltimateUI</bold></red> <#8a989c>»</#8a989c> <yellow>Page not found:</yellow> <white>" + var2 + "</white>")
            );
            return false;
         } else {
            String var13 = this.normalizePageKey(var11, var10);
            if (!var3) {
               String var14 = this.resolvePageOpenPermission(var12);
               if (!var14.isBlank() && !this.hasPageOpenPermission(var1, var14)) {
                  this.sendEditorPlayerMessage(
                     var1, this.plugin.getLangMessageWithPlaceholders("gui.no-perm", "&cYou don't have permission {PERMISSION}", Map.of("PERMISSION", var14))
                  );
                  return false;
               }
            }

            if (var3 && var12.getBoolean("page-hidden", false)) {
               this.sendEditorPlayerMessage(var1, MM.deserialize("<yellow>This page cannot be edited.</yellow>"));
               return false;
            } else {
               File var59 = new File(new File(this.plugin.getDataFolder(), "contents/pages"), var13 + ".yml");
               if (var3) {
                  UUID var15 = this.findActiveEditorOwnerForPage(var13, var1.getUniqueId());
                  if (var15 != null) {
                     return this.notifyEditorPageLocked(var1, var13, var15);
                  }
               }

               if (var3) {
                  EditorSession var60 = this.editorSessions.get(var1.getUniqueId());
                  if (var60 != null && var60.editMode && var60.previewMode) {
                     this.snapshotCurrentEditorPage(var1.getUniqueId(), var60);
                  }
               }

               if (!var3) {
                  EditorSession var61 = this.editorSessions.get(var1.getUniqueId());
                  if (var61 != null && !var61.editMode && var61.pendingCloseEffect) {
                     var61.pendingCloseEffect = false;
                  }
               }

               YamlConfiguration var62 = var12;
               List var16 = this.expandCompactImageBlocks(this.copyBlocks(var12.getList("blocks")));
               this.applyImageLayoutOffsetFormatToLoadedBlocks(var12, var16);
               List var17 = this.resolveRenderableBlocks(var16, var9);
               if (!var3 && var5) {
                  this.markRuntimeHudStackPage(var16, var13);
                  this.markRuntimeHudStackPage(var17, var13);
               }

               EditorSession var18 = this.resolveStackableRuntimeHudSession(var1, var3, var5);
               boolean var19 = !var3 && var5 && var12.getBoolean("behavior.keep-open", false);
               if (var18 == null && var19) {
                  EditorSession var20 = this.editorSessions.get(var1.getUniqueId());
                  if (var20 != null && !var20.editMode) {
                     var18 = var20;
                  }
               }

               EditorSession var63 = var18;
               if (var63 != null) {
                  this.stackRuntimeHudSession(var63, var16, var17, var13);
                  PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
                     if (var1.isOnline()) {
                        EditorSession var7x = this.editorSessions.get(var1.getUniqueId());
                        if (var7x != null && var7x == var63 && !var7x.editMode && (var7x.hudTextOnlyMode || var19)) {
                           this.renderStackedPageElements(var1, var7x, var13);
                           if (var12.getBoolean("animation-loop", false) || var12.getBoolean("animationLoop", false)) {
                              var7x.runtimeAnimationLoop = true;
                           }

                           var7x.runtimePageKeepOpen.put(var13, var19);
                           var7x.runtimePageReopen.put(var13, var12.getBoolean("behavior.reopen", false));
                           var7x.runtimePageCloseOnDeath.put(var13, var12.getBoolean("behavior.close-on-death", false));
                           var7x.runtimePageCloseOnDamage.put(var13, var12.getBoolean("behavior.close-on-damage", false));
                           this.startRuntimeOpenAnimations(var1, var7x, var6, var13);
                        }
                     }
                  }, 1L);
                  return true;
               } else {
                  List var21 = Collections.emptyList();
                  PreviewViewport var22 = null;
                  double var23 = 0.9;
                  if (var3) {
                     YamlConfiguration var25 = (YamlConfiguration)var8.get("editor");
                     if (var25 == null) {
                        this.sendEditorPlayerMessage(
                           var1,
                           MM.deserialize("<red><bold>UltimateUI</bold></red> <#8a989c>»</#8a989c> <yellow>Missing page:</yellow> <white>editor.yml</white>")
                        );
                        return false;
                     }

                     var62 = var25;
                     List var26 = this.copyBlocks(var25.getList("blocks"));
                     List var27 = this.resolveOpenEditorPagesForPlayer(var1.getUniqueId(), var13);
                     int var28 = this.resolveInitialActivePagesScrollOffset(var27, var13);
                     var21 = this.buildEditorShellBlocksWithActivePages(var26, var9, var27, var28, var13);
                     var23 = this.readDouble(var25, "screen.preview.defaultZoom", "screen.previewDefaultZoom", 0.9);
                     var22 = this.resolvePreviewViewport(var21, var23);
                     if (var22 == null) {
                        this.sendEditorPlayerMessage(
                           var1,
                           MM.deserialize(
                              "<red><bold>UltimateUI</bold></red> <#8a989c>»</#8a989c> <yellow>editor.yml must contain a preview block with id:</yellow> <white>preview</white>"
                           )
                        );
                        return false;
                     }
                  }

                  double var64 = var62.getDouble("screen.width", 1920.0);
                  double var65 = var62.getDouble("screen.height", 1060.0);
                  double var29 = var62.getDouble("screen.offsetX", 0.0);
                  double var31 = var62.getDouble("screen.offsetY", 20.0);
                  double var33 = var62.getDouble("screen.cursorSize", var62.getDouble("screen.cursor.size", 10.0));
                  double var35 = var62.getDouble("screen.cursorSpeed", var62.getDouble("screen.cursor.speed", 1.0));
                  String var37 = this.readCursorUnicodeConfig(var62, "\ue67c", new String[]{"screen.cursorUnicode", "screen.cursor.unicode"});
                  String var38 = this.readCursorUnicodeConfig(
                     var62,
                     "\ue67d",
                     new String[]{"screen.cursorHoverUnicode", "screen.cursorUnicodeHover", "screen.cursor.hoverUnicode", "screen.cursor.unicodeHover"}
                  );
                  String var39 = this.readCursorUnicodeConfig(
                     var62,
                     "\ue67e",
                     new String[]{"screen.cursorDragUnicode", "screen.cursorUnicodeDrag", "screen.cursor.dragUnicode", "screen.cursor.unicodeDrag"}
                  );
                  String var40 = this.readCursorUnicodeConfig(
                     var62,
                     "\ue67f",
                     new String[]{
                        "screen.cursorSliderDragUnicode",
                        "screen.cursorUnicodeSliderDrag",
                        "screen.cursorSliderUnicode",
                        "screen.cursorUnicodeSlider",
                        "screen.cursor.sliderDragUnicode",
                        "screen.cursor.sliderUnicode"
                     }
                  );
                  String var41 = this.readCursorUnicodeConfig(
                     var62,
                     "\ue680",
                     new String[]{
                        "screen.cursorResizeTrBlUnicode",
                        "screen.cursorUnicodeResizeTrBl",
                        "screen.cursor.resizeTrBlUnicode",
                        "screen.cursor.unicodeResizeTrBl"
                     }
                  );
                  String var42 = this.readCursorUnicodeConfig(
                     var62,
                     "\ue681",
                     new String[]{
                        "screen.cursorResizeTlBrUnicode",
                        "screen.cursorUnicodeResizeTlBr",
                        "screen.cursor.resizeTlBrUnicode",
                        "screen.cursor.unicodeResizeTlBr"
                     }
                  );
                  String var43 = this.readCursorUnicodeConfig(
                     var62,
                     "\ue682",
                     new String[]{
                        "screen.cursorResizeVerticalUnicode",
                        "screen.cursorUnicodeResizeVertical",
                        "screen.cursor.resizeVerticalUnicode",
                        "screen.cursor.unicodeResizeVertical"
                     }
                  );
                  String var44 = this.readCursorUnicodeConfig(
                     var62,
                     "\ue683",
                     new String[]{
                        "screen.cursorResizeHorizontalUnicode",
                        "screen.cursorUnicodeResizeHorizontal",
                        "screen.cursor.resizeHorizontalUnicode",
                        "screen.cursor.unicodeResizeHorizontal"
                     }
                  );
                  String var45 = this.firstNonBlank(new String[]{var62.getString("screen.cursorColor"), var62.getString("screen.cursor.color"), "ffffff"});
                  String var46 = this.firstNonBlank(new String[]{this.normalizeHexColor(var45), "ffffff"});
                  String var47 = this.applyPreferredFont(this.withHexPrefix(var37, var46), null, true);
                  double var48 = this.readDouble(var62, "screen.cursorLayer", "screen.cursor.layer", Double.NaN);
                  double var51 = this.findMaxLayer(var17) + 2.0;
                  if (var3 && var22 != null) {
                     double var53 = this.findMaxLayer(var21);
                     double var55 = var22.layerBase + this.findMaxLayer(var17);
                     var51 = Math.max(var53, var55) + 2.0;
                  }

                  double var66 = var51;
                  if (Double.isFinite(var48)) {
                     var66 = Math.max(var48, var51);
                  }

                  boolean var67 = !var5 && var4 && this.cameraService.isCameraActive(var1);
                  if (var67) {
                     Entity var56 = this.hudService.getHud(var1, "cursor");
                     Vector var57 = this.hudService.getHudLocation(var56);
                     if (var57 == null) {
                        var67 = false;
                     }
                  }

                  if (var5) {
                     this.hudService.clearHuds(var1);
                     this.cameraService.setCameraActive(var1, false);
                     this.cameraService.forceStop(var1);
                  } else if (!var67) {
                     this.hudService.clearHuds(var1);
                     this.cameraService.setCameraActive(var1, false);
                     this.cameraService.start(var1, var64, var65, var29, var31, var33, var35, var47, var66);
                  } else {
                     this.syncSeamlessCursorHud(var1, var33, var66);
                  }

                  if (!var5) {
                     this.clearGuiRideActionBar(var1);
                     PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> this.clearGuiRideActionBar(var1), 2L);
                     PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> this.clearGuiRideActionBar(var1), 8L);
                  }

                  boolean var68 = var67;
                  EditorSession var69 = new EditorSession();
                  var69.pageName = var13;
                  var69.pageFile = var59;
                  var69.yaml = var3 ? this.cloneYamlConfiguration(var12) : var12;
                  if (var3) {
                     this.ensurePageDisplayName(var69);
                  }

                  var69.rawBlocks = var16;
                  var69.renderBlocks = var17;
                  var69.components = var9;
                  var69.shellBlocks = var21;
                  var69.previewViewport = var22;
                  var69.previewDefaultZoom = var23;
                  var69.previewMode = var3 && var22 != null;
                  var69.editMode = var3;
                  var69.hudTextOnlyMode = var5 && !var3;
                  var69.runtimeAutoCloseAfterAnimations = !var3 && var6;
                  var69.runtimeAnimationLoop = !var3 && (var12.getBoolean("animation-loop", false) || var12.getBoolean("animationLoop", false));
                  if (!var3) {
                     boolean var58 = !var5;
                     var69.runtimePageKeepOpen.put(var13, var5 && var12.getBoolean("behavior.keep-open", false));
                     var69.runtimePageReopen.put(var13, var5 && var12.getBoolean("behavior.reopen", false));
                     var69.runtimePageCloseOnDeath.put(var13, var12.getBoolean("behavior.close-on-death", var58));
                     var69.runtimePageCloseOnDamage.put(var13, var12.getBoolean("behavior.close-on-damage", false));
                     var69.pageOpenEffect = this.firstNonBlank(new String[]{var12.getString("animation.open.effect"), var12.getString("animation.openEffect")})
                        .trim();
                     var69.pageCloseEffect = this.firstNonBlank(
                           new String[]{var12.getString("animation.close.effect"), var12.getString("animation.closeEffect")}
                        )
                        .trim();
                     var69.pageCloseUseHud = var12.getBoolean("animation.close.usehud", false);
                  }

                  var69.previewSizeToastVisible = false;
                  var69.previewSizeToastPendingReveal = var69.editMode && var69.previewMode;
                  var69.hitboxOffsetX = this.readDouble(var62, "screen.hitboxOffsetX", "screen.hitboxOffsetX", 0.0);
                  var69.hitboxOffsetY = this.readDouble(var62, "screen.hitboxOffsetY", "screen.hitboxOffsetY", -17.0);
                  var69.cursorColor = var46;
                  var69.cursorDefaultUnicode = this.cursorUnicodeValue(var37, "\ue67c");
                  var69.cursorHoverUnicode = this.cursorUnicodeValue(var38, "\ue67d");
                  var69.cursorDragUnicode = this.cursorUnicodeValue(var39, "\ue67e");
                  var69.cursorSliderDragUnicode = this.cursorUnicodeValue(var40, "\ue67f");
                  var69.cursorResizeTrBlUnicode = this.cursorUnicodeValue(var41, "\ue680");
                  var69.cursorResizeTlBrUnicode = this.cursorUnicodeValue(var42, "\ue681");
                  var69.cursorResizeVerticalUnicode = this.cursorUnicodeValue(var43, "\ue682");
                  var69.cursorResizeHorizontalUnicode = this.cursorUnicodeValue(var44, "\ue683");
                  var69.cursorRuntimeUnicode = var69.cursorDefaultUnicode;
                  var69.savePopupVisible = false;
                  var69.preferencesPopupVisible = false;
                  var69.welcomePopupVisible = false;
                  var69.welcomePopupAcknowledged = false;
                  if (var3) {
                     this.ensureEditorManageFilePopupHiddenByDefault(var69);
                  }

                  if (!var3 && "editor_menu".equalsIgnoreCase(var13)) {
                     this.initializeEditorMenuFilePopupState(var69, var8);
                  }

                  if (var3) {
                     this.populateSavePopupDefaults(var69);
                     this.restoreEditorLayerStateMetadata(var69);
                     var69.editorShellTemplateBlocks = this.copyBlocks(var62.getList("blocks"));
                     var69.openEditorPages.clear();
                     var69.openEditorPages.addAll(this.resolveOpenEditorPagesForPlayer(var1.getUniqueId(), var13));
                     var69.activePagesScrollOffset = this.resolveInitialActivePagesScrollOffset(var69.openEditorPages, var13);
                     var69.shellBlocks = this.buildEditorShellBlocksWithActivePages(
                        var69.editorShellTemplateBlocks, var69.components, var69.openEditorPages, var69.activePagesScrollOffset, var13
                     );
                  }

                  if (!var3) {
                     this.applyRuntimeOpenInitialZeroTickPose(var69);
                  }

                  this.editorSessions.put(var1.getUniqueId(), var69);
                  if (var3) {
                     Map var70 = this.playerEditorWorkingPages.computeIfAbsent(var1.getUniqueId(), var0 -> new HashMap<>());
                     this.rememberEditorWorkingPage(var1.getUniqueId(), var13, var69.rawBlocks);
                     this.syncEditorWorkingPagesToOpenPages(var1.getUniqueId(), var69.openEditorPages);
                  } else {
                     this.playerEditorWorkingPages.remove(var1.getUniqueId());
                  }

                  this.applyCursorVisualUnicode(var1, var69, var69.cursorRuntimeUnicode);
                  if (var3) {
                     this.loadPlayerColorPrefs(var1, var69);
                     this.applyEditorDisplayPreferences(var1, var69);
                  } else if (!var5) {
                     this.setClientHotbarVisibleAsAir(var1);
                  } else {
                     this.restoreClientHotbarFromServerInventory(var1);
                     this.restoreHeldHotbarSlotFromServerInventory(var1);
                  }

                  if (!var5) {
                     this.scheduleClientHotbarMaskRefresh(var1, var69);
                  }

                  Runnable var71 = () -> {
                     if (var68) {
                        this.hudService.clearHudsKeepCursor(var1);
                     }

                     if (!var69.editMode) {
                        String var5x = var69.pageOpenEffect;
                        if (var5x != null && !var5x.isBlank()) {
                           AnimationTimelineOperationsManagerBase.HoverEffectRuntimeConfig var6x = this.resolveHoverEffectRuntimeConfig(var5x);
                           if (var6x != null && Math.abs(var6x.startScaleXMultiplier() - 1.0) < 0.001 && Math.abs(var6x.startScaleYMultiplier() - 1.0) < 0.001) {
                              var69.pageOpenSpawnOffsetX = var6x.startOffsetX();
                              var69.pageOpenSpawnOffsetY = var6x.startOffsetY();
                           }
                        }
                     }

                     this.renderPage(var1, var69, false);
                     var69.pageOpenSpawnOffsetX = 0.0;
                     var69.pageOpenSpawnOffsetY = 0.0;
                     if (var69.editMode && var69.previewMode) {
                        LinkedHashSet var10x = this.resolveAnimationTimelinePreviewTargetIds(var69, "");
                        if (!var10x.isEmpty()) {
                           LinkedHashSet var12x = new LinkedHashSet(var10x);
                           this.applyRuntimeOpenAnimationTick(var1, var69, var12x, 0.0);
                           PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
                              EditorSession var4xx = this.editorSessions.get(var1.getUniqueId());
                              if (var4xx != null && var4xx == var69 && var4xx.editMode && var4xx.previewMode) {
                                 this.applyRuntimeOpenAnimationTick(var1, var4xx, var12x, 0.0);
                              }
                           }, 1L);
                           PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
                              EditorSession var4xx = this.editorSessions.get(var1.getUniqueId());
                              if (var4xx != null && var4xx == var69 && var4xx.editMode && var4xx.previewMode) {
                                 this.applyRuntimeOpenAnimationTick(var1, var4xx, var12x, 0.0);
                              }
                           }, 3L);
                        }

                        this.schedulePreviewSizeToastHide(var1, var69);
                        this.startEditorShellOpenAnimations(var1, var69);
                        if (!var69.welcomePopupAcknowledged) {
                           this.setSavePopupVisible(var1, var69, false);
                           this.setPreferencesPopupVisible(var1, var69, false);
                           this.setWelcomePopupVisible(var1, var69, true);
                           double var13x = var69.cursorX + var69.hitboxOffsetX;
                           double var8x = var69.cursorY + var69.hitboxOffsetY;
                           this.updateWelcomePopupHover(var1, var69, var13x, var8x);
                        }
                     }

                     if (!var69.editMode) {
                        this.startRuntimeOpenAnimations(var1, var69, var6);
                        this.applyPageOpenEffect(var1, var69);
                        this.scheduleRuntimeNumericPlaceholderRefresh(var1, var69);
                        this.scheduleHudTextPlaceholderRefresh(var1, var69);
                     }

                     if (var69.editMode) {
                        String var11x = this.firstNonBlank(new String[]{var69.pageName}).trim();
                        if (!var11x.isEmpty()) {
                           this.sendEditorPlayerMessage(
                              var1,
                              MM.deserialize(
                                 "<green><bold>UltimateUI</bold></green> <#8a989c>»</#8a989c> <white>Edit mode:</white> <aqua>" + var11x + "</aqua>"
                              )
                           );
                        }
                     }
                  };
                  if (var68) {
                     PlatformCompat.runEntityTaskLater(this.plugin, var1, var71, 1L);
                  } else {
                     PlatformCompat.runEntityTaskLater(this.plugin, var1, var71, 3L);
                  }

                  return true;
               }
            }
         }
      }
   }

   protected EditorSession resolveStackableRuntimeHudSession(Player var1, boolean var2, boolean var3) {
      if (var1 != null && !var2 && var3) {
         EditorSession var4 = this.editorSessions.get(var1.getUniqueId());
         return var4 != null && !var4.editMode && var4.hudTextOnlyMode ? var4 : null;
      } else {
         return null;
      }
   }

   protected void stackRuntimeHudSession(EditorSession var1, List<Map<String, Object>> var2, List<Map<String, Object>> var3, String var4) {
      if (var1 != null) {
         String var5 = this.normalizePageKey(var4, "");
         this.invalidateRuntimeOpenAnimationToken(var1, var5);
         ArrayList var6 = new ArrayList();

         for (Map var8 : this.copyBlocks(var1.rawBlocks)) {
            if (!this.isRuntimeHudStackPageMatch(var8, var5)) {
               var6.add(var8);
            }
         }

         int var13 = var6.size();
         List var14 = this.copyBlocks(var2);
         this.markRuntimeHudStackPage(var14, var5);
         var6.addAll(var14);
         var1.rawBlocks = var6;
         ArrayList var9 = new ArrayList();

         for (Map var11 : this.copyBlocks(var1.renderBlocks)) {
            if (!this.isRuntimeHudStackPageMatch(var11, var5)) {
               var9.add(var11);
            }
         }

         List var15 = this.copyBlocks(var3);
         this.markRuntimeHudStackPage(var15, var5);
         if (var13 > 0) {
            for (Map var12 : (List<Map>)(List<?>)var15) {
               this.applyStackedRenderBlockPathOffset(var12, var13);
            }
         }

         var9.addAll(var15);
         var1.renderBlocks = var9;
         var1.runtimeAutoCloseAfterAnimations = false;
      }
   }

   private void applyStackedRenderBlockPathOffset(Map<String, Object> var1, int var2) {
      if (var1 != null && var2 > 0) {
         Object var3 = var1.get("__editor_target_path");
         if (var3 instanceof String) {
            String var4 = this.offsetRawBlockPath((String)var3, var2);
            if (var4 != null) {
               var1.put("__editor_target_path", var4);
            }
         }

         Object var6 = var1.get("__editor_block_path");
         if (var6 instanceof String) {
            String var5 = this.offsetRawBlockPath((String)var6, var2);
            if (var5 != null) {
               var1.put("__editor_block_path", var5);
            }
         }
      }
   }

   private String offsetRawBlockPath(String var1, int var2) {
      if (var1 != null && !var1.isBlank() && var2 != 0) {
         int var3 = var1.indexOf(46);
         String var4 = var3 < 0 ? var1 : var1.substring(0, var3);
         String var5 = var3 < 0 ? "" : var1.substring(var3);

         try {
            int var6 = Integer.parseInt(var4);
            return var6 + var2 + var5;
         } catch (NumberFormatException var7) {
            return var1;
         }
      } else {
         return var1;
      }
   }

   protected void markRuntimeHudStackPage(List<Map<String, Object>> var1, String var2) {
      if (var1 != null && !var1.isEmpty()) {
         String var3 = this.normalizePageKey(var2, "");
         if (!var3.isBlank()) {
            for (Map var5 : var1) {
               if (var5 != null && !var5.isEmpty()) {
                  var5.put("xqgui_runtime_hud_stack_page", var3);
               }
            }
         }
      }
   }

   @Override
   protected boolean isRuntimeHudStackPageMatch(Map<String, Object> var1, String var2) {
      if (var1 != null && !var1.isEmpty()) {
         String var3 = this.normalizePageKey(var2, "");
         if (var3.isBlank()) {
            return false;
         } else {
            String var4 = this.stringValue(var1.get("xqgui_runtime_hud_stack_page"));
            String var5 = this.normalizePageKey(var4, "");
            return var5.isBlank() ? false : var5.equalsIgnoreCase(var3);
         }
      } else {
         return false;
      }
   }

   protected String buildUiImagesFontText(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).replace("\r\n", "\n").replace('\r', '\n');
      if (var2.isBlank()) {
         return "";
      } else {
         String[] var3 = var2.split("\n", -1);
         StringBuilder var4 = new StringBuilder(var2.length() + var3.length * 16);

         for (int var5 = 0; var5 < var3.length; var5++) {
            if (var5 > 0) {
               var4.append('\n');
            }

            var4.append("<font:uiimages>").append(var3[var5]);
         }

         return var4.toString();
      }
   }

   @Override
   protected double resolveSessionPageWidth(EditorSession var1) {
      if (var1 == null) {
         return 1920.0;
      } else if (var1.previewViewport != null) {
         return var1.previewViewport.pageWidth;
      } else {
         return var1.yaml != null ? Math.max(1.0, var1.yaml.getDouble("screen.width", 1920.0)) : 1920.0;
      }
   }

   @Override
   protected double resolveSessionPageHeight(EditorSession var1) {
      if (var1 == null) {
         return 1080.0;
      } else if (var1.previewViewport != null) {
         return var1.previewViewport.pageHeight;
      } else {
         return var1.yaml != null ? Math.max(1.0, var1.yaml.getDouble("screen.height", 1080.0)) : 1080.0;
      }
   }

   protected List<Map<String, Object>> filterHudTextRenderableBlocks(List<Map<String, Object>> var1) {
      if (var1 != null && !var1.isEmpty()) {
         ArrayList var2 = new ArrayList();

         for (Map var4 : var1) {
            ConfigurationSection var5 = this.mapToSection(var4);
            if (var5 != null) {
               String var6 = this.firstNonBlank(new String[]{var5.getString("type"), "block"}).toLowerCase(Locale.ROOT);
               if ("text".equals(var6)) {
                  var2.add(this.deepCopyMap(var4));
               }
            }
         }

         return var2;
      } else {
         return Collections.emptyList();
      }
   }

   protected void scheduleHudTextPlaceholderRefresh(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && !var2.editMode) {
         if (this.hasRuntimeTextPlaceholderBindings(var2.renderBlocks)) {
            long var3 = this.resolveHudTextRefreshTicks();
            if (var3 > 0L) {
               PlatformCompat.runEntityTimer(this.plugin, var1, var3, var3, var3x -> {
                  if (!var1.isOnline()) {
                     var3x.cancel();
                  } else {
                     EditorSession var4 = this.editorSessions.get(var1.getUniqueId());
                     if (var4 != null && var4 == var2 && !var4.editMode) {
                        this.refreshHudTextPlaceholderContent(var1, var4);
                     } else {
                        var3x.cancel();
                     }
                  }
               });
            }
         }
      }
   }

   protected boolean hasRuntimeTextPlaceholderBindings(List<Map<String, Object>> var1) {
      if (var1 != null && !var1.isEmpty()) {
         for (Map var3 : var1) {
            if (var3 != null && !var3.isEmpty()) {
               String var4 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var3, "type")), "block"}).toLowerCase(Locale.ROOT);
               if ("text".equals(var4) && this.containsRuntimeTextPlaceholderValue(this.readMapPathValue(var3, "text"))) {
                  return true;
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }

   protected boolean containsRuntimeTextPlaceholderValue(Object var1) {
      return var1 instanceof String var2 ? var2.indexOf(37) >= 0 : false;
   }

   protected void scheduleRuntimeNumericPlaceholderRefresh(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && !var2.editMode && !var2.hudTextOnlyMode) {
         if (this.hasRuntimeNumericPlaceholderBindings(var2.renderBlocks)) {
            long var3 = this.resolveRuntimeNumericPlaceholderRefreshTicks();
            if (var3 > 0L) {
               PlatformCompat.runEntityTimer(this.plugin, var1, var3, var3, var3x -> {
                  if (!var1.isOnline()) {
                     var3x.cancel();
                  } else {
                     EditorSession var4 = this.editorSessions.get(var1.getUniqueId());
                     if (var4 != null && var4 == var2 && !var4.editMode && !var4.hudTextOnlyMode) {
                        this.refreshRuntimeNumericPlaceholderContent(var1, var4);
                     } else {
                        var3x.cancel();
                     }
                  }
               });
            }
         }
      }
   }

   protected long resolveRuntimeNumericPlaceholderRefreshTicks() {
      if (this.plugin == null) {
         return 20L;
      } else {
         return this.plugin.getConfig().contains("editor.placeholders.numeric-refresh")
            ? Math.max(0L, this.plugin.getConfig().getLong("editor.placeholders.numeric-refresh", 20L))
            : this.resolveHudTextRefreshTicks();
      }
   }

   protected boolean hasRuntimeNumericPlaceholderBindings(List<Map<String, Object>> var1) {
      if (var1 != null && !var1.isEmpty()) {
         for (Map var3 : var1) {
            if (var3 != null
               && !var3.isEmpty()
               && (
                  this.containsRuntimeNumericPlaceholderValue(this.readMapPathValue(var3, "position.x"))
                     || this.containsRuntimeNumericPlaceholderValue(this.readMapPathValue(var3, "x"))
                     || this.containsRuntimeNumericPlaceholderValue(this.readMapPathValue(var3, "position.y"))
                     || this.containsRuntimeNumericPlaceholderValue(this.readMapPathValue(var3, "y"))
                     || this.containsRuntimeNumericPlaceholderValue(this.readMapPathValue(var3, "layer"))
                     || this.containsRuntimeNumericPlaceholderValue(this.readMapPathValue(var3, "size.depth"))
                     || this.containsRuntimeNumericPlaceholderValue(this.readMapPathValue(var3, "depth"))
                     || this.containsRuntimeNumericPlaceholderValue(this.readMapPathValue(var3, "size.width"))
                     || this.containsRuntimeNumericPlaceholderValue(this.readMapPathValue(var3, "width"))
                     || this.containsRuntimeNumericPlaceholderValue(this.readMapPathValue(var3, "scale.width"))
                     || this.containsRuntimeNumericPlaceholderValue(this.readMapPathValue(var3, "size.height"))
                     || this.containsRuntimeNumericPlaceholderValue(this.readMapPathValue(var3, "height"))
                     || this.containsRuntimeNumericPlaceholderValue(this.readMapPathValue(var3, "scale.height"))
                     || this.containsRuntimeNumericPlaceholderValue(this.readMapPathValue(var3, "rotation"))
                     || this.containsRuntimeNumericPlaceholderValue(this.readMapPathValue(var3, "rotate"))
                     || this.containsRuntimeNumericPlaceholderValue(this.readMapPathValue(var3, "visibility.placeholder"))
               )) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   protected boolean containsRuntimeNumericPlaceholderValue(Object var1) {
      return var1 instanceof String var2 ? var2.indexOf(37) >= 0 : false;
   }

   protected void refreshRuntimeNumericPlaceholderContent(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && !var2.editMode && !var2.hudTextOnlyMode) {
         if (var2.renderBlocks != null && !var2.renderBlocks.isEmpty()) {
            this.renderResolvedContent(var1, var2);
         }
      }
   }

   protected long resolveHudTextRefreshTicks() {
      return this.plugin == null ? 20L : Math.max(0L, this.plugin.getConfig().getLong("editor.placeholders.text-refresh", 20L));
   }

   protected void refreshHudTextPlaceholderContent(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && !var2.editMode) {
         if (var2.renderBlocks != null && !var2.renderBlocks.isEmpty() && this.hasRuntimeTextPlaceholderBindings(var2.renderBlocks)) {
            HashMap var3 = new HashMap();
            int var4 = 0;

            for (Map var6 : var2.renderBlocks) {
               var4++;
               String var7 = this.firstNonBlank(new String[]{this.stringValue(this.readMapPathValue(var6, "type")), "block"}).toLowerCase(Locale.ROOT);
               if (this.isRenderableBlockType(var7)) {
                  String var8 = this.resolveElementId(var6, var4, var3);
                  if ("text".equals(var7)) {
                     Object var9 = this.readMapPathValue(var6, "text");
                     if (this.containsRuntimeTextPlaceholderValue(var9)) {
                        String var10 = this.runtimeElementId(var2, var8);
                        Entity var11 = this.hudService.getHud(var1, var10);
                        if (var11 != null) {
                           String var12 = this.firstNonBlank(new String[]{this.stringValue(var9), ""});
                           String var13 = this.firstNonBlank(
                              new String[]{
                                 this.stringValue(this.readMapPathValue(var6, "color")), this.stringValue(this.readMapPathValue(var6, "style.color"))
                              }
                           );
                           String var14 = this.withHexPrefix(var12, var13);
                           String var15 = this.applyPlaceholderApiIgnoringEditorMode(var1, var14);
                           String var16 = this.applyPreferredFontFromBlockMap(var15, var6, false, this.isEditorContextSession(var2));
                           this.hudService.setHudText(var11, var16, null, false);
                           this.syncRuntimeElementText(var2, var10, var16);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected String applyPreferredFontFromBlockMap(String var1, Map<String, Object> var2, boolean var3) {
      return this.applyPreferredFontFromBlockMap(var1, var2, var3, true);
   }

   protected String applyPreferredFontFromBlockMap(String var1, Map<String, Object> var2, boolean var3, boolean var4) {
      if (var1 != null && !var1.isBlank() && !var1.contains("<font:")) {
         String var5 = this.firstNonBlank(
            new String[]{
               this.stringValue(this.readMapPathValue(var2, "font")),
               this.stringValue(this.readMapPathValue(var2, "style.font")),
               this.stringValue(this.readMapPathValue(var2, "text.font"))
            }
         );
         String var6;
         if (!var5.isBlank()) {
            var6 = this.normalizeFontKey(var5);
         } else if (var4) {
            var6 = "editor";
         } else {
            var6 = "default";
         }

         return this.withFont(var1, var6);
      } else {
         return var1;
      }
   }

   protected void syncRuntimeElementText(EditorSession var1, String var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && var1.elements != null && !var1.elements.isEmpty()) {
         for (Object var5_raw : var1.elements) {
            HoverElement var5 = (HoverElement)var5_raw;
            if (var5 != null && var2.equals(var5.id)) {
               var5.text = var3;
               return;
            }
         }
      }
   }

   @Override
   protected void renderPage(Player var1, EditorSession var2) {
      this.renderPage(var1, var2, true);
   }

   @Override
   protected void renderPage(Player var1, EditorSession var2, boolean var3) {
      if (var2.previewMode) {
         this.renderEditorShell(var1, var2);
         this.renderEditorToolSidebar(var1, var2);
         this.renderPreviewPageOutline(var1, var2);
         this.setWelcomePopupVisible(var1, var2, false);
         this.setSavePopupVisible(var1, var2, false);
         this.setPreferencesPopupVisible(var1, var2, false);
         this.refreshSavePopupFields(var1, var2);
         this.refreshPreferencesPopupFields(var1, var2);
         this.setShellColor(var1, var2, "editor_color_1", var2.editorColor1);
         this.setShellColor(var1, var2, "editor_color_2", var2.editorColor2);
         this.syncActivePagesRenderedSlotStates(var2);
      }

      if (var3 || var2.renderBlocks == null) {
         var2.renderBlocks = this.resolveRenderableBlocks(var2.rawBlocks, var2.components);
      }

      this.renderResolvedContent(var1, var2);
      this.updatePageInfoReadout(var1, var2);
      this.updateSidebarTabVisualState(var1, var2, false, false);
      this.updateEditorPropertiesSidebar(var1, var2);
      this.queueEditorShellSpawnOpacityRetoggles(var1, var2);
      if (var2.hudTextOnlyMode && !var2.editMode) {
         this.syncRuntimeHudSneakVisualOffset(var1, var2);
      }
   }

   protected void schedulePreviewSizeToastHide(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         int var3 = ++var2.previewSizeToastToken;
         var2.previewSizeToastVisible = false;
         var2.previewSizeToastPendingReveal = true;
         this.schedulePreviewSizeToastAnchorStabilization(var1, var2, var3);
         PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
            if (var1.isOnline()) {
               EditorSession var4 = this.editorSessions.get(var1.getUniqueId());
               if (var4 != null && var4 == var2 && var3 == var4.previewSizeToastToken) {
                  if (var4.previewSizeToastPendingReveal) {
                     this.updatePageInfoReadout(var1, var4);
                     var4.previewSizeToastVisible = true;
                     var4.previewSizeToastPendingReveal = false;
                     this.beginShellOpacityBatch(var4);

                     try {
                        this.setSidebarPanelVisible(var1, var4, "preview_size", true);
                     } finally {
                        this.endShellOpacityBatch(var1, var4);
                     }

                     this.updatePageInfoReadout(var1, var4);
                     PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
                        if (var1.isOnline()) {
                           EditorSession var4x = this.editorSessions.get(var1.getUniqueId());
                           if (var4x != null && var4x == var4 && var3 == var4x.previewSizeToastToken) {
                              if (var4x.previewSizeToastVisible) {
                                 var4x.previewSizeToastVisible = false;
                                 var4x.previewSizeToastPendingReveal = false;
                                 this.beginShellOpacityBatch(var4x);

                                 try {
                                    this.setSidebarPanelVisible(var1, var4x, "preview_size", false);
                                 } finally {
                                    this.endShellOpacityBatch(var1, var4x);
                                 }
                              }
                           }
                        }
                     }, 70L);
                  }
               }
            }
         }, 1L);
      }
   }

   protected void schedulePreviewSizeToastAnchorStabilization(Player var1, EditorSession var2, int var3) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         for (long var7 : PREVIEW_SIZE_TOAST_ANCHOR_SYNC_DELAYS) {
            if (var7 > 0L) {
               PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
                  if (var1.isOnline()) {
                     EditorSession var4 = this.editorSessions.get(var1.getUniqueId());
                     if (var4 != null && var4 == var2 && var3 == var4.previewSizeToastToken) {
                        if (var4.previewSizeToastVisible || var4.previewSizeToastPendingReveal) {
                           this.updatePageInfoReadout(var1, var4);
                        }
                     }
                  }
               }, var7);
            }
         }
      }
   }

   protected void queueEditorShellSpawnOpacityRetoggles(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         if (var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
            HashMap var3 = new HashMap();
            int var4 = 0;

            for (Map var6 : var2.shellBlocks) {
               var4++;
               ConfigurationSection var7 = this.mapToSection(var6);
               if (var7 != null) {
                  String var8 = this.firstNonBlank(new String[]{var7.getString("type"), "block"}).toLowerCase(Locale.ROOT);
                  if ("block".equals(var8) || "text".equals(var8) || this.isRoundedType(var8)) {
                     String var9 = this.resolveElementId(var7, var4, var3);
                     if (!this.isEditorToolShellBlockId(var9)) {
                        int var10 = this.readOpacity(var7, 255);
                        int var11 = this.resolveShellBlockInitialOpacity(var2, var9, var7, var10);
                        String var12 = this.shellSpawnOpacityRetoggleTargetId(var9);
                        if (!var12.isBlank()) {
                           boolean var13 = this.readRefreshOpacityFlag(var7);
                           this.queueSpawnOpacityRetoggle(var1, var2, var12, var9, var11, var13);
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected void clearGuiRideActionBar(Player var1) {
      if (var1 != null) {
         var1.sendActionBar(MM.deserialize("<gray> </gray>"));
      }
   }

   protected void renderStackedPageElements(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String var4 = this.normalizePageKey(var3, "");
         if (!var4.isBlank()) {
            ArrayList var5 = new ArrayList<>(var2.elements);
            HashSet var6 = new HashSet();

            for (Object var8_raw : var5) {
               HoverElement var8 = (HoverElement)var8_raw;
               if (var8 != null && var8.id != null && !var8.id.isBlank()) {
                  var6.add(var8.id);
               }
            }

            HashSet var23 = new HashSet();
            HashSet var24 = new HashSet();
            ArrayList var9 = new ArrayList();
            HashMap var10 = new HashMap();
            int var11 = 0;

            for (Map var13 : var2.renderBlocks) {
               var11++;
               ConfigurationSection var14 = this.mapToSection(var13);
               if (var14 != null) {
                  String var15 = this.firstNonBlank(new String[]{var14.getString("type"), "block"}).toLowerCase(Locale.ROOT);
                  if (this.isRenderableBlockType(var15)) {
                     String var16 = this.resolveElementId(var14, var11, var10);
                     String var17 = this.runtimeElementId(var2, var16);
                     boolean var18 = this.isRuntimeHudStackPageMatch(var13, var4);
                     if (var18) {
                        var23.add(var17);
                     }

                     if (var18) {
                        String var19 = this.firstNonBlank(new String[]{var14.getString("__editor_target_id"), var17});
                        if (this.isTargetVisible(var2, var19)) {
                           boolean var20 = !var6.contains(var17);
                           HoverElement var21 = this.renderResolvedElement(var1, var2, var14, var17, var15);
                           var24.add(var17);
                           var9.add(var21);
                           if (var20) {
                              boolean var22 = this.readRefreshOpacityFlag(var14);
                              this.queueSpawnOpacityRetoggle(var1, var2, var19, var16, var21.opacity, var22);
                           }
                        }
                     }
                  }
               }
            }

            ArrayList var25 = new ArrayList();

            for (Object var27_raw : var5) {
               HoverElement var27 = (HoverElement)var27_raw;
               if (var27 != null && !var24.contains(var27.id)) {
                  if (var23.contains(var27.id)) {
                     this.removeRenderedElementHud(var1, var27);
                  } else {
                     var25.add(var27);
                  }
               }
            }

            var25.addAll(var9);
            var2.elements.clear();
            var2.elements.addAll(var25);
            this.normalizeSelectionState(var2);
            if (var2.hudTextOnlyMode && !var2.editMode) {
               this.syncRuntimeHudSneakVisualOffset(var1, var2);
            }
         }
      }
   }

   @Override
   protected void renderResolvedContent(Player var1, EditorSession var2) {
      String var3 = this.firstNonBlank(new String[]{var2.hoveredElementId});
      ArrayList var4 = new ArrayList<>(var2.elements);
      HashSet var5 = new HashSet();

      for (Object var7_raw : var4) {
         HoverElement var7 = (HoverElement)var7_raw;
         if (var7 != null && var7.id != null && !var7.id.isBlank()) {
            var5.add(var7.id);
         }
      }

      HashSet var20 = new HashSet();
      ArrayList var21 = new ArrayList();
      HashMap var8 = new HashMap();
      int var9 = 0;

      for (Map var11 : var2.renderBlocks) {
         var9++;
         ConfigurationSection var12 = this.mapToSection(var11);
         if (var12 != null) {
            String var13 = this.firstNonBlank(new String[]{var12.getString("type"), "block"}).toLowerCase(Locale.ROOT);
            if (this.isRenderableBlockType(var13)) {
               String var14 = this.resolveElementId(var12, var9, var8);
               String var15 = this.runtimeElementId(var2, var14);
               String var16 = this.firstNonBlank(new String[]{var12.getString("__editor_target_id"), var15});
               if (this.isTargetVisible(var2, var16)) {
                  boolean var17 = !var5.contains(var15);
                  HoverElement var18 = this.renderResolvedElement(var1, var2, var12, var15, var13);
                  var20.add(var15);
                  var21.add(var18);
                  if (var17) {
                     boolean var19 = this.readRefreshOpacityFlag(var12);
                     this.queueSpawnOpacityRetoggle(var1, var2, var16, var14, var18.opacity, var19);
                  }
               }
            }
         }
      }

      if (var2.previewMode) {
         this.renderEditorTransparencyOverlay(var1, var2);
      }

      for (Object var24_raw : var4) {
         HoverElement var24 = (HoverElement)var24_raw;
         if (!var20.contains(var24.id)) {
            this.removeRenderedElementHud(var1, var24);
         }
      }

      var2.elements.clear();
      var2.elements.addAll(var21);
      this.normalizeSelectionState(var2);
      String var23 = this.firstNonBlank(new String[]{var2.hoveredElementId});
      if (!var3.isBlank() && this.equalsNullable(var3, var23)) {
         this.updateHoveredHighlight(var1, var2, null, var23);
      }
   }

   protected void queueSpawnOpacityRetoggle(Player var1, EditorSession var2, String var3, String var4, int var5, boolean var6) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode && var3 != null && !var3.isBlank()) {
         if (!var2.spawnOpacityRetoggledTargetIds.contains(var3) && this.isTargetVisible(var2, var3)) {
            if (var6 || var5 < 255) {
               var2.spawnOpacityRetoggledTargetIds.add(var3);
               var2.pendingSpawnOpacityRetoggleTargetIds.add(var3);
               if (!var2.spawnOpacityRetoggleTaskQueued) {
                  var2.spawnOpacityRetoggleTaskQueued = true;
                  PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> this.runSpawnOpacityRetoggleBatch(var1, var2), 1L);
               }
            }
         }
      }
   }

   protected boolean readRefreshOpacityFlag(ConfigurationSection var1) {
      if (var1 == null) {
         return false;
      } else {
         Object var2 = var1.get("refreshopacity");
         if (var2 == null) {
            var2 = var1.get("refreshOpacity");
         }

         if (var2 == null) {
            var2 = var1.get("refresh_opacity");
         }

         if (var2 == null) {
            var2 = var1.get("refresh-opacity");
         }

         return this.parseBooleanFlag(var2, false);
      }
   }

   protected void runSpawnOpacityRetoggleBatch(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
         if (var3 != null && var3 == var2 && var1.isOnline()) {
            var3.spawnOpacityRetoggleTaskQueued = false;
            if (!var3.pendingSpawnOpacityRetoggleTargetIds.isEmpty()) {
               LinkedHashSet var4 = new LinkedHashSet<>(var3.pendingSpawnOpacityRetoggleTargetIds);
               var3.pendingSpawnOpacityRetoggleTargetIds.clear();
               LinkedHashSet var5 = new LinkedHashSet();

               for (Object var7_raw : var4) {
                  String var7 = var7_raw != null ? var7_raw.toString() : null;
                  if (var7 != null && !var7.isBlank() && this.isTargetVisible(var3, var7)) {
                     var5.addAll(this.resolveVisibilityCascadeTargetIds(var3, var7));
                  }
               }

               if (!var5.isEmpty()) {
                  LinkedHashSet var8 = new LinkedHashSet<>(var3.hiddenLayerTargetIds);
                  var3.hiddenLayerTargetIds.addAll(var5);
                  this.rerenderSpawnOpacityRetoggle(var1, var3);
                  var3.hiddenLayerTargetIds.clear();
                  var3.hiddenLayerTargetIds.addAll(var8);
                  this.rerenderSpawnOpacityRetoggle(var1, var3);
               }

               if (!var3.pendingSpawnOpacityRetoggleTargetIds.isEmpty() && !var3.spawnOpacityRetoggleTaskQueued) {
                  var3.spawnOpacityRetoggleTaskQueued = true;
                  PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> this.runSpawnOpacityRetoggleBatch(var1, var3), 1L);
               }
            }
         } else {
            var2.spawnOpacityRetoggleTaskQueued = false;
         }
      }
   }

   private void rerenderSpawnOpacityRetoggle(Player var1, EditorSession var2) {
      boolean var3 = var2.moveDragActive;

      try {
         var2.moveDragActive = true;
         this.rerenderEditableSelection(var1, var2);
         if (var2.previewMode) {
            this.renderEditorShell(var1, var2);
            this.updatePageInfoReadout(var1, var2);
            this.setShellColor(var1, var2, "editor_color_1", var2.editorColor1);
            this.setShellColor(var1, var2, "editor_color_2", var2.editorColor2);
         }
      } finally {
         var2.moveDragActive = var3;
      }
   }

   @Override
   protected void rememberOpenedEditorPage(UUID var1, String var2) {
      if (var1 != null && this.isEligibleEditorTabPage(var2)) {
         String var3 = this.normalizePageKey(var2, "");
         if (!var3.isBlank()) {
            List var4 = this.playerEditorOpenPages.computeIfAbsent(var1, var0 -> new ArrayList<>());
            var4.removeIf(var1x -> var3.equalsIgnoreCase((String)var1x));
            var4.add(var3);
         }
      }
   }

   @Override
   protected void rememberEditorWorkingPage(UUID var1, String var2, List<Map<String, Object>> var3) {
      if (var1 != null && this.isEligibleEditorTabPage(var2)) {
         String var4 = this.normalizePageKey(var2, "");
         if (!var4.isBlank()) {
            Map var5 = this.playerEditorWorkingPages.computeIfAbsent(var1, var0 -> new HashMap<>());
            var5.put(var4, this.copyBlocks(var3));
         }
      }
   }

   protected List<Map<String, Object>> resolveEditorWorkingPageSnapshot(UUID var1, String var2) {
      if (var1 != null && this.isEligibleEditorTabPage(var2)) {
         String var3 = this.normalizePageKey(var2, "");
         if (var3.isBlank()) {
            return null;
         } else {
            Map var4 = this.playerEditorWorkingPages.get(var1);
            if (var4 != null && !var4.isEmpty()) {
               List var5 = (List)var4.get(var3);
               return var5 == null ? null : this.copyBlocks(var5);
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   protected void forgetEditorWorkingPage(UUID var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var3 = this.playerEditorWorkingPages.get(var1);
         if (var3 != null && !var3.isEmpty()) {
            String var4 = this.normalizePageKey(var2, "");
            if (!var4.isBlank()) {
               var3.remove(var4);
               if (var3.isEmpty()) {
                  this.playerEditorWorkingPages.remove(var1);
               }
            }
         }
      }
   }

   protected void syncEditorWorkingPagesToOpenPages(UUID var1, List<String> var2) {
      if (var1 != null) {
         Map var3 = this.playerEditorWorkingPages.get(var1);
         if (var3 != null && !var3.isEmpty()) {
            if (var2 != null && !var2.isEmpty()) {
               HashSet var4 = new HashSet();

               for (Object var6_raw : var2) {
                  String var6 = var6_raw != null ? var6_raw.toString() : null;
                  if (this.isEligibleEditorTabPage(var6)) {
                     String var7 = this.normalizePageKey(var6, "");
                     if (!var7.isBlank()) {
                        var4.add(var7);
                     }
                  }
               }

               if (var3 != null && !var3.isEmpty()) { HashSet toRem = new HashSet(); for (Object k : var3.keySet()) { if (!var4.contains(k)) toRem.add(k); } for (Object k : toRem) var3.remove(k); }
               if (var3.isEmpty()) {
                  this.playerEditorWorkingPages.remove(var1);
               }
            } else {
               var3.clear();
               this.playerEditorWorkingPages.remove(var1);
            }
         }
      }
   }

   protected boolean shouldPersistCurrentEditorPageSnapshot(UUID var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         String var3 = this.normalizePageKey(var2.pageName, "");
         if (!var3.isBlank() && !this.isInternalEditorPageName(var3)) {
            List var4 = this.playerEditorOpenPages.get(var1);
            if (var4 != null && !var4.isEmpty()) {
               for (Object var6_raw : var4) {
                  String var6 = var6_raw != null ? var6_raw.toString() : null;
                  if (var3.equalsIgnoreCase(this.normalizePageKey(var6, ""))) {
                     return true;
                  }
               }

               return false;
            } else {
               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @Override
   protected void snapshotCurrentEditorPage(UUID var1, EditorSession var2) {
      if (this.shouldPersistCurrentEditorPageSnapshot(var1, var2)) {
         this.rememberEditorWorkingPage(var1, var2.pageName, var2.rawBlocks);
      }
   }

   @Override
   protected List<String> resolveOpenEditorPagesForPlayer(UUID var1, String var2) {
      if (var1 == null) {
         return new ArrayList<>();
      } else {
         List var3 = this.playerEditorOpenPages.computeIfAbsent(var1, var0 -> new ArrayList<>());
         ArrayList var4 = new ArrayList();

         for (Object var6_raw : var3) {
            String var6 = var6_raw != null ? var6_raw.toString() : null;
            String var7 = this.normalizePageKey(var6, "");
            if (!var7.isBlank() && !this.isInternalEditorPageName(var7) && !var4.contains(var7)) {
               var4.add(var7);
            }
         }

         if (this.isEligibleEditorTabPage(var2)) {
            String var9 = this.normalizePageKey(var2, "");
            boolean var10 = false;

            for (Object var8_raw : var4) {
               String var8 = var8_raw != null ? var8_raw.toString() : null;
               if (var9.equalsIgnoreCase(var8)) {
                  var10 = true;
                  break;
               }
            }

            if (!var10) {
               var4.add(var9);
            }
         }

         var3.clear();
         var3.addAll(var4);
         return new ArrayList<>(var3);
      }
   }

   protected boolean isEligibleEditorTabPage(String var1) {
      String var2 = this.normalizePageKey(var1, "");
      return !var2.isBlank() && !this.isInternalEditorPageName(var2);
   }

   @Override
   protected int resolveInitialActivePagesScrollOffset(List<String> var1, String var2) {
      if (var1 != null && !var1.isEmpty()) {
         int var3 = Math.max(0, var1.size() - 7);
         String var4 = this.normalizePageKey(var2, "");
         int var5 = var4.isBlank() ? -1 : var1.indexOf(var4);
         if (var5 < 0) {
            return var3;
         } else {
            return var5 < 7 ? 0 : Math.max(0, Math.min(var3, var5 - 6));
         }
      } else {
         return 0;
      }
   }

   @Override
   protected List<Map<String, Object>> buildEditorShellBlocksWithActivePages(
      List<Map<String, Object>> var1, Map<String, YamlConfiguration> var2, List<String> var3, int var4, String var5
   ) {
      List var6 = this.copyBlocks(var1);
      ArrayList var7 = var3 == null ? new ArrayList() : new ArrayList(var3);
      int var8 = Math.max(0, var7.size() - 7);
      int var9 = Math.max(0, Math.min(var8, var4));
      ArrayList var10 = new ArrayList();
      String var11 = this.normalizePageKey(var5, "");
      LinkedHashMap var12 = this.resolveActivePageDisplayNames(var7);
      int var13 = Math.min(7, Math.max(0, var7.size() - var9));

      for (int var14 = 1; var14 <= var13; var14++) {
         String var15 = (String)var7.get(var9 + var14 - 1);
         String var16 = this.firstNonBlank(new String[]{(String)var12.get(this.normalizePageKey(var15, "")), var15});
         var10.add(this.buildActivePageComponentEntry(var14, var15, var16, var15.equalsIgnoreCase(var11)));
      }

      this.replaceActivePagesChildren(var6, var10);
      return this.resolveRenderableBlocksNoCopy(var6, var2);
   }

   protected Map<String, Object> buildActivePageComponentEntry(int var1, String var2, String var3, boolean var4) {
      HashMap var5 = new HashMap();
      var5.put("component", "editor_activepage");
      var5.put("id", Integer.toString(var1));
      var5.put("active", var4);
      var5.put("pagename", this.formatActivePageDisplayName(this.firstNonBlank(new String[]{var3, var2})));
      return var5;
   }

   protected LinkedHashMap<String, String> resolveActivePageDisplayNames(List<String> var1) {
      LinkedHashMap var2 = new LinkedHashMap();
      if (var1 != null && !var1.isEmpty()) {
         Map var3 = this.loadYamlFolder("contents/pages", new ArrayList<>());

         for (Object var5_raw : var1) {
            String var5 = var5_raw != null ? var5_raw.toString() : null;
            String var6 = this.normalizePageKey(var5, "");
            if (!var6.isBlank() && !var2.containsKey(var6)) {
               var2.put(var6, this.resolveActivePageDisplayName(var6, var3));
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   protected String resolveActivePageDisplayName(String var1, Map<String, YamlConfiguration> var2) {
      String var3 = this.normalizePageKey(var1, "");
      if (var3.isBlank()) {
         return "";
      } else {
         String var4 = this.firstNonBlank(new String[]{this.formatUntitledPageDisplayName(var3), var3}).trim();
         if (var4.isBlank()) {
            var4 = var3;
         }

         String var5 = this.resolveExistingPageMapKey(var2, var3);
         if (var5.isBlank()) {
            return var4;
         } else {
            YamlConfiguration var6 = (YamlConfiguration)var2.get(var5);
            String var7 = this.firstNonBlank(new String[]{var6 == null ? null : var6.getString("name")}).trim();
            return var7.isBlank() ? var4 : var7;
         }
      }
   }

   protected void replaceActivePagesChildren(List<Map<String, Object>> var1, List<Map<String, Object>> var2) {
      if (var1 != null) {
         for (Map var4 : var1) {
            if (var4 != null && !var4.isEmpty()) {
               String var5 = this.firstNonBlank(new String[]{this.stringValue(var4.get("id"))});
               if ("active_pages".equalsIgnoreCase(var5)) {
                  var4.put("children", new ArrayList(var2));
                  return;
               }

               Object var6 = var4.get("children");
               if (var6 instanceof List) {
                  List var7 = (List)var6;
                  ArrayList var8 = new ArrayList();

                  for (Object var10 : var7) {
                     if (var10 instanceof Map var11) {
                        var8.add(var11);
                     }
                  }

                  this.replaceActivePagesChildren(var8, var2);
               }
            }
         }
      }
   }

   protected String formatActivePageDisplayName(String var1) {
      String var2 = this.firstNonBlank(new String[]{this.formatUntitledPageDisplayName(var1), var1}).trim();
      return var2.length() <= 16 ? var2 : var2.substring(0, 15) + "...";
   }

   @Override
   protected String resolveNextUntitledPageKey(Player var1, EditorSession var2) {
      LinkedHashSet var3 = new LinkedHashSet();

      for (String var5 : this.getGuiNames()) {
         String var6 = this.normalizePageKey(var5, "");
         if (!var6.isBlank()) {
            var3.add(var6);
         }
      }

      if (var1 != null) {
         for (String var12 : this.resolveOpenEditorPagesForPlayer(var1.getUniqueId(), var2 == null ? null : var2.pageName)) {
            String var7 = this.normalizePageKey(var12, "");
            if (!var7.isBlank()) {
               var3.add(var7);
            }
         }
      }

      String var9 = this.normalizePageKey(var2 == null ? null : var2.pageName, "");
      if (!var9.isBlank()) {
         var3.add(var9);
      }

      for (int var11 = 0; var11 < 10000; var11++) {
         String var13 = var11 == 0 ? "untitled" : "untitled_" + var11;
         if (!this.isInternalEditorPageName(var13) && !this.containsPageKeyIgnoreCase(var3, var13)) {
            return var13;
         }
      }

      return "untitled_" + System.currentTimeMillis();
   }

   protected boolean containsPageKeyIgnoreCase(Set<String> var1, String var2) {
      if (var1 != null && !var1.isEmpty() && var2 != null && !var2.isBlank()) {
         for (Object var4_raw : var1) {
            String var4 = var4_raw != null ? var4_raw.toString() : null;
            if (var2.equalsIgnoreCase(this.firstNonBlank(new String[]{var4}))) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   @Override
   protected String formatUntitledPageDisplayName(String var1) {
      String var2 = this.normalizePageKey(var1, "");
      if (var2.isBlank()) {
         return "";
      } else {
         Matcher var3 = UNTITLED_PAGE_KEY_PATTERN.matcher(var2);
         if (!var3.matches()) {
            return "";
         } else {
            String var4 = this.firstNonBlank(new String[]{var3.group(1)});
            if (var4.isBlank()) {
               return "Untitled";
            } else {
               try {
                  int var5 = Integer.parseInt(var4);
                  return var5 <= 0 ? "Untitled" : "Untitled #" + var5;
               } catch (NumberFormatException var6) {
                  return "Untitled";
               }
            }
         }
      }
   }

   protected boolean isUntitledPageKey(String var1) {
      String var2 = this.normalizePageKey(var1, "");
      return var2.isBlank() ? false : UNTITLED_PAGE_KEY_PATTERN.matcher(var2).matches();
   }

   @Override
   protected boolean handleCursorPageDropdownClick(Player var1, EditorSession var2, GuiService.ClickType var3, double var4, double var6) {
      if (var1 == null || var2 == null || !var2.editMode || !var2.previewMode || var3 == null) {
         return false;
      } else if (var2.welcomePopupVisible || var2.savePopupVisible || var2.preferencesPopupVisible) {
         return false;
      } else if (var2.activeTool == EditorTool.ZOOM && var3 == GuiService.ClickType.RIGHT) {
         this.cancelCursorPageDropdownPendingOpen(var2);
         if (var2.cursorPageDropdownVisible) {
            this.setCursorPageDropdownVisible(var1, var2, false);
         }

         return false;
      } else {
         double var8 = var4 - var2.hitboxOffsetX;
         double var10 = var6 - var2.hitboxOffsetY;
         boolean var12 = var2.cursorPageDropdownVisible;
         boolean var13 = this.isInsideShellBlock(var2, "cursor_page_dropdown", var8, var10)
            || this.isInsideShellBlock(var2, "cursor_page_dropdown", var4, var6);
         if (var3 == GuiService.ClickType.LEFT) {
            if (!var12) {
               this.cancelCursorPageDropdownPendingOpen(var2);
               return false;
            } else {
               String var16 = this.resolveHoveredCursorPageDropdownTargetId(var2, var4, var6);
               if (var16 != null) {
                  this.cancelCursorPageDropdownPendingOpen(var2);
                  this.setCursorPageDropdownVisible(var1, var2, false);
                  this.playEditorSfx(var1, var2, "dropdown-item-clicked");
                  this.handleCursorPageDropdownAction(var1, var2, var16);
                  return true;
               } else if (var13) {
                  return true;
               } else {
                  this.cancelCursorPageDropdownPendingOpen(var2);
                  this.setCursorPageDropdownVisible(var1, var2, false);
                  return false;
               }
            }
         } else if (var3 != GuiService.ClickType.RIGHT) {
            return false;
         } else {
            if (var2.activeTool == EditorTool.ANIMATION) {
               boolean var14 = this.isInsideShellBlock(var2, "animation_timeline", var4, var6)
                  || this.isInsideShellBlock(var2, "animation_timeline", var8, var10)
                  || this.isInsideShellBlock(var2, "keyframe_dropdown", var4, var6)
                  || this.isInsideShellBlock(var2, "keyframe_dropdown", var8, var10)
                  || this.isInsideShellBlock(var2, "keyframe_timeline_dropdown", var4, var6)
                  || this.isInsideShellBlock(var2, "keyframe_timeline_dropdown", var8, var10);
               if (var14) {
                  this.cancelCursorPageDropdownPendingOpen(var2);
                  if (var12 && !var13) {
                     this.setCursorPageDropdownVisible(var1, var2, false);
                  }

                  return false;
               }
            }

            if (!this.isInsidePreviewArea(var2, var4, var6)) {
               this.cancelCursorPageDropdownPendingOpen(var2);
               if (var12) {
                  this.setCursorPageDropdownVisible(var1, var2, false);
               }

               return false;
            } else if (!var2.cursorLayersDropdownVisible
               || !this.isInsideShellBlock(var2, "cursor_layers_dropdown", var8, var10) && !this.isInsideShellBlock(var2, "cursor_layers_dropdown", var4, var6)
               )
             {
               String var15 = this.resolvePreviewContextTargetByHit(var2, var4, var6);
               if (var15 != null && !var15.isBlank()) {
                  this.cancelCursorPageDropdownPendingOpen(var2);
                  if (var2.cursorPageDropdownVisible) {
                     this.setCursorPageDropdownVisible(var1, var2, false);
                  }

                  return false;
               } else {
                  this.setFileDropdownVisible(var1, var2, false);
                  this.setEditDropdownVisible(var1, var2, false);
                  this.setSelectionDropdownVisible(var1, var2, false);
                  this.setLayerDropdownVisible(var1, var2, false);
                  this.setWindowDropdownVisible(var1, var2, false);
                  this.cancelCursorLayersDropdownPendingOpen(var2);
                  if (var2.cursorLayersDropdownVisible) {
                     this.setCursorLayersDropdownVisible(var1, var2, false);
                  }

                  this.cancelCursorPageDropdownPendingOpen(var2);
                  if (var2.cursorPageDropdownVisible) {
                     this.setCursorPageDropdownVisible(var1, var2, false);
                  }

                  this.moveCursorPageDropdownTo(var1, var2, var4, var6);
                  this.scheduleCursorPageDropdownOpen(var1, var2, var4, var6);
                  return true;
               }
            } else {
               this.cancelCursorPageDropdownPendingOpen(var2);
               if (var12) {
                  this.setCursorPageDropdownVisible(var1, var2, false);
               }

               return false;
            }
         }
      }
   }

   protected String resolvePreviewContextTargetByHit(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.previewMode) {
         if (!this.isInsideEditorChromeBlock(var1, var2, var4) && this.isInsidePreviewArea(var1, var2, var4)) {
            HoverElement var6 = null;

            for (Object var8_raw : var1.elements) {
               HoverElement var8 = (HoverElement)var8_raw;
               String var9 = this.targetIdOf(var8);
               if (var9 != null && !var9.isBlank()) {
                  EditorRect var10 = this.getHoverBounds(var1, var8);
                  if (var10 != null) {
                     double var11 = var10.x;
                     double var13 = var10.y;
                     double var15 = var10.maxX();
                     double var17 = var10.maxY();
                     if (var2 >= var11 && var2 <= var15 && var4 >= var13 && var4 <= var17 && (var6 == null || var8.runtimeZ >= var6.runtimeZ)) {
                        var6 = var8;
                     }
                  }
               }
            }

            return var6 == null ? null : this.targetIdOf(var6);
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   protected void cancelCursorPageDropdownPendingOpen(EditorSession var1) {
      if (var1 != null) {
         var1.cursorPageDropdownOpenToken++;
      }
   }

   protected void scheduleCursorPageDropdownOpen(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         int var7 = ++var2.cursorPageDropdownOpenToken;
         PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
            if (var1.isOnline()) {
               EditorSession var8 = this.editorSessions.get(var1.getUniqueId());
               if (var8 != null && var8 == var2 && var7 == var8.cursorPageDropdownOpenToken) {
                  if (var8.editMode && var8.previewMode) {
                     this.moveCursorPageDropdownTo(var1, var8, var3, var5);
                     this.setCursorPageDropdownVisible(var1, var8, true);
                     this.moveCursorPageDropdownTo(var1, var8, var3, var5);
                     this.updateCursorPageDropdownHover(var1, var8, var3, var5);
                  }
               }
            }
         }, 1L);
      }
   }

   protected boolean handleCursorPageDropdownAction(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         return switch (var3) {
            case "dropdown_cursor_layer_newblock" -> this.handleEditorSelectionNewBlock(var1, var2);
            case "dropdown_cursor_layer_newtext" -> this.handleEditorSelectionNewText(var1, var2);
            case "dropdown_cursor_layer_newitem" -> this.handleEditorSelectionNewItem(var1, var2);
            case "dropdown_cursor_layer_newimage" -> this.openEditorImageFilePopup(var1, var2);
            default -> false;
         };
      } else {
         return false;
      }
   }

   protected void setCursorPageDropdownVisible(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         if (var2.cursorPageDropdownVisible == var3) {
            if (var3) {
               double var12 = var2.cursorX + var2.hitboxOffsetX;
               double var13 = var2.cursorY + var2.hitboxOffsetY;
               this.moveCursorPageDropdownTo(var1, var2, var12, var13);
               this.updateCursorPageDropdownHover(var1, var2, var12, var13);
            }
         } else {
            double var4 = var2.cursorX + var2.hitboxOffsetX;
            double var6 = var2.cursorY + var2.hitboxOffsetY;
            var2.cursorPageDropdownVisible = var3;
            if (!var3) {
               var2.cursorPageDropdownHoverTargetId = null;
            } else {
               this.playEditorSfx(var1, var2, "dropdown-opened");
            }

            this.beginShellOpacityBatch(var2);

            try {
               this.setSidebarPanelVisible(var1, var2, "cursor_page_dropdown", var3);
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }

            if (var3) {
               this.updateCursorPageDropdownHover(var1, var2, var4, var6);
            } else {
               for (Object var9_raw : CURSOR_PAGE_DROPDOWN_ITEM_IDS) {
                  String var9 = var9_raw != null ? var9_raw.toString() : null;
                  this.setCursorPageDropdownItemVisual(var1, var2, var9, false);
               }
            }
         }
      }
   }

   @Override
   protected void updateCursorPageDropdownHover(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         String var7 = var2.cursorPageDropdownVisible ? this.resolveHoveredCursorPageDropdownTargetId(var2, var3, var5) : null;
         if (!this.equalsNullable(var2.cursorPageDropdownHoverTargetId, var7)) {
            var2.cursorPageDropdownHoverTargetId = var7;

            for (Object var9_raw : CURSOR_PAGE_DROPDOWN_ITEM_IDS) {
               String var9 = var9_raw != null ? var9_raw.toString() : null;
               this.setCursorPageDropdownItemVisual(var1, var2, var9, this.equalsNullable(var9, var7));
            }
         }
      }
   }

   protected void setCursorPageDropdownItemVisual(Player var1, EditorSession var2, String var3, boolean var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String[] var5 = CURSOR_PAGE_DROPDOWN_ITEM_ICONS.get(var3);
         if (var5 != null && var5.length >= 2) {
            this.setShellText(var1, var3 + "_text", var4 ? var5[1] : var5[0]);
         }

         this.setShellColor(var1, var2, var3, var4 ? "141414" : "0f0f0f");
      }
   }

   protected String resolveHoveredCursorPageDropdownTargetId(EditorSession var1, double var2, double var4) {
      if (var1 != null && var1.cursorPageDropdownVisible) {
         double var6 = var2 - var1.hitboxOffsetX;
         double var8 = var4 - var1.hitboxOffsetY;
         int var10 = 0;

         for (Object var12_raw : CURSOR_PAGE_DROPDOWN_ITEM_IDS) {
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

         if (var10 == CURSOR_PAGE_DROPDOWN_ITEM_IDS.size()) {
            return null;
         } else {
            EditorRect var15 = this.findShellBlockRect(var1, "cursor_page_dropdown");
            if (var15 != null && var6 >= var15.x && var6 <= var15.maxX() && var8 >= var15.y && var8 <= var15.maxY()) {
               double var16 = 32.0;
               int var19 = (int)Math.floor((var8 - var15.y) / 32.0);
               if (var19 >= 0 && var19 < CURSOR_PAGE_DROPDOWN_ITEM_IDS.size()) {
                  return CURSOR_PAGE_DROPDOWN_ITEM_IDS.get(var19);
               }
            }

            for (Object var18_raw : CURSOR_PAGE_DROPDOWN_ITEM_IDS) {
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

   protected void moveCursorPageDropdownTo(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         ConfigurationSection var7 = this.findShellBlockSection(var2, "cursor_page_dropdown");
         EditorRect var8 = this.findShellBlockRect(var2, "cursor_page_dropdown");
         if (var7 != null && var8 != null) {
            double var9 = var3 - var2.hitboxOffsetX;
            double var11 = var5 - var2.hitboxOffsetY;
            double var13 = var9;
            double var15 = var11;
            EditorRect var17 = this.findShellBlockRect(var2, "preview");
            if (var17 != null) {
               var13 = Math.max(var17.x, Math.min(var17.maxX() - var8.width, var9));
               var15 = Math.max(var17.y, Math.min(var17.maxY() - var8.height, var11));
            }

            double var18 = var13 - var8.x;
            double var20 = var15 - var8.y;
            if (!(Math.abs(var18) < 1.0E-4) || !(Math.abs(var20) < 1.0E-4)) {
               String var22 = this.firstNonBlank(new String[]{var7.getString("__editor_target_path")});
               if (var22.isBlank()) {
                  this.applyCursorPageDropdownTeleportDurationZero(var1, "cursor_page_dropdown");
                  this.moveShellElement(var1, var2, "cursor_page_dropdown", var13, var15, var8.width, var8.height);
               } else {
                  EditorRect var23 = var2.shellStaticRectCache == null ? null : var2.shellStaticRectCache.get("cursor_page_dropdown");
                  HashMap var24 = new HashMap();
                  int var25 = 0;

                  for (Map var27 : var2.shellBlocks) {
                     var25++;
                     ConfigurationSection var28 = this.mapToSection(var27);
                     if (var28 != null) {
                        String var29 = this.firstNonBlank(new String[]{var28.getString("__editor_target_path")});
                        if (this.belongsToSidebarPanel(var22, var29)) {
                           String var30 = this.resolveElementId(var28, var25, var24);
                           if (!var30.isBlank()) {
                              EditorRect var31 = this.findShellBlockRect(var2, var30);
                              if (var31 != null) {
                                 double var32 = var31.x + var18;
                                 double var34 = var31.y + var20;
                                 if (var23 != null && var2.shellStaticRectCache != null) {
                                    EditorRect var36 = var2.shellStaticRectCache.get(var30);
                                    if (var36 != null) {
                                       var32 = var13 + (var36.x - var23.x);
                                       var34 = var15 + (var36.y - var23.y);
                                    }
                                 }

                                 this.applyCursorPageDropdownTeleportDurationZero(var1, var30);
                                 this.moveShellElement(var1, var2, var30, var32, var34, var31.width, var31.height);
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
   protected boolean handleCursorLayersDropdownClick(Player var1, EditorSession var2, GuiService.ClickType var3, double var4, double var6) {
      if (var1 == null || var2 == null || !var2.editMode || !var2.previewMode || var3 == null) {
         return false;
      } else if (!var2.welcomePopupVisible && !var2.savePopupVisible && !var2.preferencesPopupVisible) {
         double var8 = var4 - var2.hitboxOffsetX;
         double var10 = var6 - var2.hitboxOffsetY;
         boolean var12 = var2.cursorLayersDropdownVisible;
         boolean var13 = var12
            && (this.isInsideShellBlock(var2, "cursor_layers_dropdown", var8, var10) || this.isInsideShellBlock(var2, "cursor_layers_dropdown", var4, var6));
         if (var3 == GuiService.ClickType.LEFT) {
            if (!var12) {
               this.cancelCursorLayersDropdownPendingOpen(var2);
               return false;
            } else if (!var13) {
               this.cancelCursorLayersDropdownPendingOpen(var2);
               this.setCursorLayersDropdownVisible(var1, var2, false);
               return false;
            } else {
               String var20 = this.resolveHoveredCursorLayersDropdownTargetId(var2, var4, var6);
               if (var20 != null) {
                  String var21 = this.stripDuplicateRuntimeSuffix(var20);
                  long var22 = (long)Bukkit.getCurrentTick();
                  if (var21.equals(var2.cursorLayersDropdownLastActionId) && var22 - var2.cursorLayersDropdownLastActionTick <= 6L) {
                     return true;
                  } else {
                     String var25 = this.firstNonBlank(new String[]{var2.cursorLayersDropdownContextTargetId});
                     String var26 = this.firstNonBlank(new String[]{var2.cursorLayersDropdownContextTargetPath});
                     var2.cursorLayersDropdownLastActionId = var21;
                     var2.cursorLayersDropdownLastActionTick = var22;
                     this.cancelCursorLayersDropdownPendingOpen(var2);
                     this.setCursorLayersDropdownVisible(var1, var2, false);
                     this.playEditorSfx(var1, var2, "dropdown-item-clicked");
                     this.handleCursorLayersDropdownAction(var1, var2, var20, var25, var26);
                     return true;
                  }
               } else {
                  return true;
               }
            }
         } else if (var3 != GuiService.ClickType.RIGHT) {
            return false;
         } else if (this.handleActionsPanelRightClick(var1, var2, var4, var6)) {
            this.cancelCursorLayersDropdownPendingOpen(var2);
            if (var12 && !var13) {
               this.setCursorLayersDropdownVisible(var1, var2, false);
            }

            return true;
         } else {
            boolean var14 = var2.rightSidebarTab == EditorSidebarTab.LAYERS;
            String var15 = var14 ? this.resolveLayerSelectionTargetByHit(var2, var4, var6, true) : null;
            String var16 = var14 ? this.resolveLayerSelectionTargetPathByHit(var2, var4, var6, true) : "";
            if (var15 == null || var15.isBlank()) {
               var15 = this.resolvePreviewContextTargetByHit(var2, var4, var6);
               var16 = this.resolveEditorTargetPath(var2, var15);
            }

            boolean var17 = false;
            if (var14 && (var15 == null || var15.isBlank())) {
               EditorRect var18 = this.resolveLayersListRect(var2);
               if (var18 != null && this.isInsideRect(var18, var4, var6)) {
                  var15 = this.resolveLayerSelectionTargetByHit(var2, var18.x + 1.0, var6, true);
                  var16 = this.resolveLayerSelectionTargetPathByHit(var2, var18.x + 1.0, var6, true);
                  var17 = var15 == null || var15.isBlank();
               }
            }

            if ((var15 == null || var15.isBlank()) && var13 && var12) {
               this.cancelCursorLayersDropdownPendingOpen(var2);
               this.setCursorLayersDropdownVisible(var1, var2, false);
               var12 = false;
               var13 = false;
               var15 = this.resolvePreviewContextTargetByHit(var2, var4, var6);
               var16 = this.resolveEditorTargetPath(var2, var15);
            }

            if (var15 != null && !var15.isBlank()) {
               this.finishLayersReorderDrag(var1, var2, false);
               this.stopCursorToolDrag(var2);
               if (this.isSelectedTarget(var2, var15) && var2.selectedElementId != null && !var2.selectedElementId.isBlank()) {
                  this.normalizeSelectionState(var2);
               } else {
                  this.selectLayerTargetForContextDropdown(var1, var2, var15);
               }

               String var24 = this.firstNonBlank(new String[]{var15, var2.selectedElementId});
               String var19 = this.firstNonBlank(new String[]{var16, this.resolveEditorTargetPath(var2, var24)});
               var2.cursorLayersDropdownContextTargetId = var24.isBlank() ? null : var24;
               var2.cursorLayersDropdownContextTargetPath = var19.isBlank() ? null : var19;
               if (var2.fileDropdownVisible) {
                  this.setFileDropdownVisible(var1, var2, false);
               }

               if (var2.editDropdownVisible) {
                  this.setEditDropdownVisible(var1, var2, false);
               }

               if (var2.selectionDropdownVisible) {
                  this.setSelectionDropdownVisible(var1, var2, false);
               }

               if (var2.layerDropdownVisible) {
                  this.setLayerDropdownVisible(var1, var2, false);
               }

               if (var2.windowDropdownVisible) {
                  this.setWindowDropdownVisible(var1, var2, false);
               }

               this.cancelCursorPageDropdownPendingOpen(var2);
               if (var2.cursorPageDropdownVisible) {
                  this.setCursorPageDropdownVisible(var1, var2, false);
               }

               this.cancelCursorLayersDropdownPendingOpen(var2);
               this.moveCursorLayersDropdownTo(var1, var2, var4, var6);
               if (!var2.cursorLayersDropdownVisible) {
                  this.setCursorLayersDropdownVisible(var1, var2, true);
                  this.moveCursorLayersDropdownTo(var1, var2, var4, var6);
                  this.updateCursorLayersDropdownActionVisibility(var1, var2);
                  this.updateCursorLayersDropdownHover(var1, var2, var4, var6);
               } else {
                  this.updateCursorLayersDropdownActionVisibility(var1, var2);
                  this.updateCursorLayersDropdownHover(var1, var2, var4, var6);
               }

               return true;
            } else {
               boolean var23 = var17 || var14 && (this.isInsideShellBlock(var2, "layers", var8, var10) || this.isInsideShellBlock(var2, "layers", var4, var6));
               this.cancelCursorLayersDropdownPendingOpen(var2);
               if (var12 && !var13) {
                  this.setCursorLayersDropdownVisible(var1, var2, false);
               }

               return var13 || var23;
            }
         }
      } else {
         return false;
      }
   }

   protected void cancelCursorLayersDropdownPendingOpen(EditorSession var1) {
      if (var1 != null) {
         var1.cursorLayersDropdownOpenToken++;
      }
   }

   protected void scheduleCursorLayersDropdownOpen(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         int var3 = ++var2.cursorLayersDropdownOpenToken;
         PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
            if (var1.isOnline()) {
               EditorSession var4 = this.editorSessions.get(var1.getUniqueId());
               if (var4 != null && var4 == var2 && var3 == var4.cursorLayersDropdownOpenToken) {
                  if (var4.editMode && var4.previewMode) {
                     this.setCursorLayersDropdownVisible(var1, var4, true);
                  }
               }
            }
         }, 1L);
      }
   }

   protected boolean handleCursorLayersDropdownAction(Player var1, EditorSession var2, String var3) {
      return this.handleCursorLayersDropdownAction(var1, var2, var3, null, null);
   }

   protected boolean handleCursorLayersDropdownAction(Player var1, EditorSession var2, String var3, String var4, String var5) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String var6 = this.stripDuplicateRuntimeSuffix(var3);
         String var7 = this.firstNonBlank(new String[]{var4, var2.cursorLayersDropdownContextTargetId});
         String var8 = this.firstNonBlank(new String[]{var5, var2.cursorLayersDropdownContextTargetPath});
         List var9 = this.resolveCursorLayersDropdownContextTargetPaths(var2, var8, var7);
         if (this.shouldApplyCursorLayersDropdownContextSelection(var6) && !var7.isBlank()) {
            this.selectLayerTargetForContextDropdown(var1, var2, var7);
         }
         boolean var10 = switch (var6) {
            case "dropdown_layers_edit_cut" -> this.handleEditorEditCut(var1, var2, var9);
            case "dropdown_layers_edit_copy" -> this.handleEditorEditCopy(var1, var2, var9);
            case "dropdown_layers_edit_paste" -> this.handleEditorEditPaste(var1, var2);
            case "dropdown_layers_cursor_layer_newblock" -> this.handleEditorSelectionNewBlock(var1, var2);
            case "dropdown_layers_cursor_layer_newtext" -> this.handleEditorSelectionNewText(var1, var2);
            case "dropdown_layers_cursor_layer_newitem" -> this.handleEditorSelectionNewItem(var1, var2);
            case "dropdown_layers_cursor_layer_newimage" -> this.openEditorImageFilePopup(var1, var2);
            case "dropdown_layers_selection_hide" -> this.handleEditorSelectionVisibility(var1, var2, false);
            case "dropdown_layers_selection_unhide" -> this.handleEditorSelectionVisibility(var1, var2, true);
            case "dropdown_layers_selection_block" -> this.handleEditorSelectionLock(var1, var2, true);
            case "dropdown_layers_selection_unblock" -> this.handleEditorSelectionLock(var1, var2, false);
            default -> false;
         };
         var2.cursorLayersDropdownContextTargetId = null;
         var2.cursorLayersDropdownContextTargetPath = null;
         return var10;
      } else {
         return false;
      }
   }

   protected List<String> resolveCursorLayersDropdownContextTargetPaths(EditorSession var1, String var2, String var3) {
      if (var1 == null) {
         return Collections.emptyList();
      } else {
         String var4 = this.firstNonBlank(new String[]{var2});
         if (!var4.isBlank()) {
            return Collections.singletonList(var4);
         } else {
            String var5 = this.firstNonBlank(new String[]{var3, var1.cursorLayersDropdownContextTargetId, var1.selectedElementId});
            String var6 = this.resolveEditorTargetPath(var1, var5);
            return !var6.isBlank() ? Collections.singletonList(var6) : Collections.emptyList();
         }
      }
   }

   protected boolean shouldApplyCursorLayersDropdownContextSelection(String var1) {
      if (var1 != null && !var1.isBlank()) {
         return switch (var1) {
            case "dropdown_layers_edit_cut", "dropdown_layers_edit_copy", "dropdown_layers_selection_hide", "dropdown_layers_selection_unhide", "dropdown_layers_selection_block", "dropdown_layers_selection_unblock" -> true;
            default -> false;
         };
      } else {
         return false;
      }
   }

   protected void setCursorLayersDropdownVisible(Player var1, EditorSession var2, boolean var3) {
      if (var1 != null && var2 != null) {
         if (var2.cursorLayersDropdownVisible == var3) {
            if (var3) {
               this.updateCursorLayersDropdownActionVisibility(var1, var2);
               this.updateCursorLayersDropdownHover(var1, var2, var2.cursorX + var2.hitboxOffsetX, var2.cursorY + var2.hitboxOffsetY);
            }
         } else {
            double var4 = var2.cursorX + var2.hitboxOffsetX;
            double var6 = var2.cursorY + var2.hitboxOffsetY;
            String var8 = var2.cursorLayersDropdownHoverTargetId;
            var2.cursorLayersDropdownVisible = var3;
            if (!var3) {
               var2.cursorLayersDropdownHoverTargetId = null;
               var2.cursorLayersDropdownContextTargetId = null;
               var2.cursorLayersDropdownContextTargetPath = null;
            } else {
               this.playEditorSfx(var1, var2, "dropdown-opened");
            }

            this.beginShellOpacityBatch(var2);

            try {
               this.setSidebarPanelVisible(var1, var2, "cursor_layers_dropdown", var3);
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }

            if (!var3) {
               if (var8 != null && !var8.isBlank()) {
                  this.setCursorLayersDropdownItemVisual(var1, var2, var8, false);
               }
            } else {
               this.updateCursorLayersDropdownActionVisibility(var1, var2);
               this.updateCursorLayersDropdownHover(var1, var2, var4, var6);
            }
         }
      }
   }

   @Override
   protected void updateCursorLayersDropdownHover(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         if (!var2.cursorLayersDropdownVisible) {
            if (var2.cursorLayersDropdownHoverTargetId != null) {
               var2.cursorLayersDropdownHoverTargetId = null;
            }
         } else {
            List var7 = this.resolveCursorLayersDropdownItemIds(var2);
            List var8 = this.resolveVisibleCursorLayersDropdownItemIds(var2);
            String var9 = this.resolveHoveredCursorLayersDropdownTargetId(var2, var3, var5, var7, var8);
            if (var9 != null && !var8.contains(var9)) {
               var9 = null;
            }

            String var10 = var2.cursorLayersDropdownHoverTargetId;
            if (!this.equalsNullable(var10, var9)) {
               var2.cursorLayersDropdownHoverTargetId = var9;
               if (var10 != null && !var10.isBlank()) {
                  this.setCursorLayersDropdownItemVisual(var1, var2, var10, false);
               }

               if (var9 != null && !var9.isBlank()) {
                  this.setCursorLayersDropdownItemVisual(var1, var2, var9, true);
               }
            }
         }
      }
   }

   protected void setCursorLayersDropdownItemVisual(Player var1, EditorSession var2, String var3, boolean var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         Boolean var5 = var2.cursorLayersDropdownItemActiveStates.get(var3);
         if (var5 == null || var5 != var4) {
            var2.cursorLayersDropdownItemActiveStates.put(var3, Boolean.valueOf(var4));
            String var6 = this.stripDuplicateRuntimeSuffix(var3);
            String[] var7 = CURSOR_LAYERS_DROPDOWN_ITEM_ICONS.get(var6);
            if (var7 != null && var7.length >= 2) {
               this.setShellText(var1, var3 + "_text", var4 ? var7[1] : var7[0]);
            }

            this.setShellColor(var1, var2, var3, var4 ? "141414" : "0f0f0f");
         }
      }
   }

   protected String resolveHoveredCursorLayersDropdownTargetId(EditorSession var1, double var2, double var4) {
      if (var1 == null) {
         return null;
      } else {
         List var6 = this.resolveCursorLayersDropdownItemIds(var1);
         List var7 = this.resolveVisibleCursorLayersDropdownItemIds(var1);
         return this.resolveHoveredCursorLayersDropdownTargetId(var1, var2, var4, var6, var7);
      }
   }

   protected String resolveHoveredCursorLayersDropdownTargetId(EditorSession var1, double var2, double var4, List<String> var6, List<String> var7) {
      if (var1 != null && var1.cursorLayersDropdownVisible) {
         if (var7.isEmpty()) {
            return null;
         } else {
            double var8 = var2 - var1.hitboxOffsetX;
            double var10 = var4 - var1.hitboxOffsetY;
            int var12 = 0;

            for (Object var14_raw : var7) {
               String var14 = var14_raw != null ? var14_raw.toString() : null;
               String var15 = var14 + "_hitbox";
               EditorRect var16 = this.findShellBlockRect(var1, var15);
               if (var16 != null) {
                  var12++;
                  if (var8 >= var16.x && var8 <= var16.maxX() && var10 >= var16.y && var10 <= var16.maxY()) {
                     return var14;
                  }
               }
            }

            if (var12 == var7.size()) {
               return null;
            } else {
               EditorRect var18 = this.findShellBlockRect(var1, "cursor_layers_dropdown");
               if (var18 != null && var8 >= var18.x && var8 <= var18.maxX() && var10 >= var18.y && var10 <= var18.maxY()) {
                  double var19 = 32.0;
                  int var22 = (int)Math.floor((var10 - var18.y) / 32.0);
                  if (var22 >= 0 && var22 < var6.size()) {
                     String var17 = (String)var6.get(var22);
                     if (var7.contains(var17)) {
                        return var17;
                     }
                  }
               }

               for (Object var21_raw : var7) {
                  String var21 = var21_raw != null ? var21_raw.toString() : null;
                  if (this.isInsideShellBlock(var1, var21, var8, var10)) {
                     return var21;
                  }
               }

               return null;
            }
         }
      } else {
         return null;
      }
   }

   protected List<String> resolveVisibleCursorLayersDropdownItemIds(EditorSession var1) {
      return this.resolveCursorLayersDropdownItemIds(var1);
   }

   protected List<String> resolveCursorLayersDropdownItemIds(EditorSession var1) {
      if (var1 == null) {
         return Collections.emptyList();
      } else {
         this.ensureCursorLayersDropdownRuntimeCache(var1);
         return var1.cursorLayersDropdownResolvedItemIds;
      }
   }

   protected List<String> resolveCursorLayersDropdownDynamicItemIds(EditorSession var1) {
      if (var1 == null) {
         return Collections.emptyList();
      } else {
         this.ensureCursorLayersDropdownRuntimeCache(var1);
         return var1.cursorLayersDropdownResolvedDynamicItemIds;
      }
   }

   protected Map<String, String> resolveCursorLayersDropdownRuntimeIds(EditorSession var1) {
      if (var1 == null) {
         return Collections.emptyMap();
      } else {
         this.ensureCursorLayersDropdownRuntimeCache(var1);
         return var1.cursorLayersDropdownRuntimeIdsByBase;
      }
   }

   protected void ensureCursorLayersDropdownRuntimeCache(EditorSession var1) {
      if (var1 != null) {
         int var2 = var1.shellBlocks == null ? 0 : System.identityHashCode(var1.shellBlocks);
         int var3 = var1.shellBlocks == null ? 0 : var1.shellBlocks.size();
         ConfigurationSection var4 = this.findShellBlockSection(var1, "cursor_layers_dropdown");
         String var5 = this.firstNonBlank(new String[]{var4 == null ? null : var4.getString("__editor_target_path")});
         var1.cursorLayersDropdownResolvedShellIdentity = var2;
         var1.cursorLayersDropdownResolvedShellSize = var3;
         var1.cursorLayersDropdownResolvedPanelPath = var5;
         var1.cursorLayersDropdownRuntimeIdsByBase.clear();
         var1.cursorLayersDropdownResolvedItemIds.clear();
         var1.cursorLayersDropdownResolvedDynamicItemIds.clear();
         var1.cursorLayersDropdownResolvedPanelMemberIds.clear();
         var1.cursorLayersDropdownActionVisibilityStates.clear();
         var1.cursorLayersDropdownItemActiveStates.clear();
         if (!var5.isBlank() && var1.shellBlocks != null && !var1.shellBlocks.isEmpty()) {
            HashMap var6 = new HashMap();
            int var7 = 0;

            for (Map var9 : var1.shellBlocks) {
               var7++;
               ConfigurationSection var10 = this.mapToSection(var9);
               if (var10 != null) {
                  String var11 = this.resolveElementId(var10, var7, var6);
                  if (!var11.isBlank()) {
                     String var12 = this.firstNonBlank(new String[]{var10.getString("__editor_target_path")});
                     if (this.belongsToSidebarPanel(var5, var12)) {
                        var1.cursorLayersDropdownResolvedPanelMemberIds.add(var11);
                        String var13 = this.stripDuplicateRuntimeSuffix(var11);
                        if (CURSOR_LAYERS_DROPDOWN_ITEM_IDS.contains(var13) && !var1.cursorLayersDropdownRuntimeIdsByBase.containsKey(var13)) {
                           var1.cursorLayersDropdownRuntimeIdsByBase.put(var13, var11);
                        }
                     }
                  }
               }
            }
         }

         for (Object var16_raw : CURSOR_LAYERS_DROPDOWN_ITEM_IDS) {
            String var16 = var16_raw != null ? var16_raw.toString() : null;
            var1.cursorLayersDropdownResolvedItemIds.add(this.firstNonBlank(new String[]{var1.cursorLayersDropdownRuntimeIdsByBase.get(var16), var16}));
         }

         for (Object var17_raw : CURSOR_LAYERS_DROPDOWN_DYNAMIC_ITEM_IDS) {
            String var17 = var17_raw != null ? var17_raw.toString() : null;
            var1.cursorLayersDropdownResolvedDynamicItemIds.add(this.firstNonBlank(new String[]{var1.cursorLayersDropdownRuntimeIdsByBase.get(var17), var17}));
         }
      }
   }

   protected String stripDuplicateRuntimeSuffix(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).trim();
      if (var2.isBlank()) {
         return "";
      } else {
         int var3 = var2.lastIndexOf(95);
         if (var3 > 0 && var3 < var2.length() - 1) {
            String var4 = var2.substring(var3 + 1);

            for (int var5 = 0; var5 < var4.length(); var5++) {
               if (!Character.isDigit(var4.charAt(var5))) {
                  return var2;
               }
            }

            return var2.substring(0, var3);
         } else {
            return var2;
         }
      }
   }

   protected void updateCursorLayersDropdownActionVisibility(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         List var3 = this.resolveVisibleCursorLayersDropdownItemIds(var2);
         List var4 = this.resolveCursorLayersDropdownDynamicItemIds(var2);
         LinkedHashMap var5 = new LinkedHashMap();

         for (Object var7_raw : var4) {
            String var7 = var7_raw != null ? var7_raw.toString() : null;
            boolean var8 = var3.contains(var7);
            Boolean var9 = var2.cursorLayersDropdownActionVisibilityStates.get(var7);
            if (var9 == null || var9 != var8) {
               var5.put(var7, Boolean.valueOf(var8));
            }
         }

         if (!var5.isEmpty()) {
            this.beginShellOpacityBatch(var2);

            try {
               for (Map.Entry<?, ?> var14 : ((Map<?, ?>)var5).entrySet()) {
                  String var15 = (String)var14.getKey();
                  boolean var16 = (Boolean)var14.getValue();
                  this.setCursorLayersDropdownActionVisible(var1, var2, var15, var16);
                  var2.cursorLayersDropdownActionVisibilityStates.put(var15, Boolean.valueOf(var16));
               }
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }
         }

         if (var2.cursorLayersDropdownHoverTargetId != null && !var3.contains(var2.cursorLayersDropdownHoverTargetId)) {
            this.setCursorLayersDropdownItemVisual(var1, var2, var2.cursorLayersDropdownHoverTargetId, false);
            var2.cursorLayersDropdownHoverTargetId = null;
         }
      }
   }

   protected void setCursorLayersDropdownActionVisible(Player var1, EditorSession var2, String var3, boolean var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         this.setSidebarPanelVisible(var1, var2, var3, var4);
      }
   }

   protected void moveCursorLayersDropdownTo(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 != null && var2 != null && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         this.ensureCursorLayersDropdownRuntimeCache(var2);
         ConfigurationSection var7 = this.findShellBlockSection(var2, "cursor_layers_dropdown");
         EditorRect var8 = this.findShellBlockRect(var2, "cursor_layers_dropdown");
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
               double var34 = var2.yaml == null ? 1920.0 : var2.yaml.getDouble("screen.width", 1920.0);
               double var20 = var2.yaml == null ? 1080.0 : var2.yaml.getDouble("screen.height", 1080.0);
               double var22 = Math.max(0.0, var34 - var8.width);
               double var24 = Math.max(0.0, var20 - var8.height);
               boolean var26 = var9 > var22;
               var13 = Math.max(0.0, Math.min(var22, var9));
               var15 = Math.max(0.0, Math.min(var24, var11));
               if (var26) {
                  var13 = Math.max(0.0, var13 - 11.0);
               }
            }

            double var35 = var13 - var8.x;
            double var36 = var15 - var8.y;
            if (!(Math.abs(var35) < 1.0E-4) || !(Math.abs(var36) < 1.0E-4)) {
               String var37 = this.firstNonBlank(new String[]{var7.getString("__editor_target_path")});
               if (var37.isBlank()) {
                  this.applyCursorPageDropdownTeleportDurationZero(var1, "cursor_layers_dropdown");
                  this.moveShellElement(var1, var2, "cursor_layers_dropdown", var13, var15, var8.width, var8.height);
               } else {
                  EditorRect var23 = var2.shellStaticRectCache == null ? null : var2.shellStaticRectCache.get("cursor_layers_dropdown");

                  for (Object var25_raw : var2.cursorLayersDropdownResolvedPanelMemberIds) {
                     String var25 = var25_raw != null ? var25_raw.toString() : null;
                     if (var25 != null && !var25.isBlank()) {
                        ConfigurationSection var39 = this.findShellBlockSection(var2, var25);
                        String var27 = this.firstNonBlank(new String[]{var39 == null ? null : var39.getString("__editor_target_path")});
                        if (this.belongsToSidebarPanel(var37, var27)) {
                           EditorRect var28 = this.findShellBlockRect(var2, var25);
                           if (var28 != null) {
                              double var29 = var28.x + var35;
                              double var31 = var28.y + var36;
                              if (var23 != null && var2.shellStaticRectCache != null) {
                                 EditorRect var33 = var2.shellStaticRectCache.get(var25);
                                 if (var33 != null) {
                                    var29 = var13 + (var33.x - var23.x);
                                    var31 = var15 + (var33.y - var23.y);
                                 }
                              }

                              this.applyCursorPageDropdownTeleportDurationZero(var1, var25);
                              this.moveShellElement(var1, var2, var25, var29, var31, var28.width, var28.height);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected boolean selectLayerTargetForContextDropdown(Player var1, EditorSession var2, String var3) {
      if (var1 == null || var2 == null || var3 == null || var3.isBlank()) {
         return false;
      } else if (this.isSelectedTarget(var2, var3)) {
         return false;
      } else {
         boolean var4 = !this.equalsNullable(var2.selectedElementId, var3) || !var2.additionalSelectedElementIds.isEmpty();
         if (!var4) {
            return false;
         } else {
            var2.selectedElementId = var3;
            var2.additionalSelectedElementIds.clear();
            this.normalizeSelectionState(var2);
            var2.activeHandle = null;
            var2.resizeAnchorValid = false;
            var2.handlesCollapsed = false;
            this.updateHoveredHighlight(var1, var2, var2.hoveredElementId, null);
            var2.hoveredElementId = null;
            this.clearEditorOverlaysOnly(var1, var2);
            if (var2.selectedElementId != null) {
               this.showSelectionOverlay(var1, var2);
            }

            this.updateEditorPropertiesSidebar(var1, var2);
            this.renderLayersPanel(var1, var2);
            return var4;
         }
      }
   }

   protected void applyCursorPageDropdownTeleportDurationZero(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = "editor_shell_" + var2;
         this.applyRuntimeHudNoTransition(var1, var3);
      }
   }

   @Override
   protected void applyRuntimeHudNoTransition(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         this.setHudTeleportDurationZeroIfExists(var1, var2);
         this.setHudTeleportDurationZeroIfExists(var1, var2 + "_outline");
         this.setHudTeleportDurationZeroIfExists(var1, var2 + "_r_core");
         this.setHudTeleportDurationZeroIfExists(var1, var2 + "_r_top");
         this.setHudTeleportDurationZeroIfExists(var1, var2 + "_r_bottom");
         this.setHudTeleportDurationZeroIfExists(var1, var2 + "_r_left");
         this.setHudTeleportDurationZeroIfExists(var1, var2 + "_r_right");
         this.setHudTeleportDurationZeroIfExists(var1, var2 + "_r_tl");
         this.setHudTeleportDurationZeroIfExists(var1, var2 + "_r_tr");
         this.setHudTeleportDurationZeroIfExists(var1, var2 + "_r_bl");
         this.setHudTeleportDurationZeroIfExists(var1, var2 + "_r_br");
         String var3 = var2 + "_outline";
         this.setHudTeleportDurationZeroIfExists(var1, var3 + "_r_core");
         this.setHudTeleportDurationZeroIfExists(var1, var3 + "_r_top");
         this.setHudTeleportDurationZeroIfExists(var1, var3 + "_r_bottom");
         this.setHudTeleportDurationZeroIfExists(var1, var3 + "_r_left");
         this.setHudTeleportDurationZeroIfExists(var1, var3 + "_r_right");
         this.setHudTeleportDurationZeroIfExists(var1, var3 + "_r_tl");
         this.setHudTeleportDurationZeroIfExists(var1, var3 + "_r_tr");
         this.setHudTeleportDurationZeroIfExists(var1, var3 + "_r_bl");
         this.setHudTeleportDurationZeroIfExists(var1, var3 + "_r_br");
      }
   }

   @Override
   protected void applyRuntimeHudTransitionTicks(Player var1, String var2, int var3, int var4) {
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

   protected void setHudTeleportDurationZeroIfExists(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Entity var3 = this.hudService.getHud(var1, var2);
         if (var3 != null) {
            this.hudService.setHudNoTransition(var3);
         }
      }
   }

   @Override
   protected void setHudTransitionTicksIfExists(Player var1, String var2, int var3, int var4) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         Entity var5 = this.hudService.getHud(var1, var2);
         if (var5 != null) {
            this.hudService.setHudTransitionTicks(var5, var3, var4);
         }
      }
   }

   @Override
   protected boolean handleActivePagesClick(Player var1, EditorSession var2, GuiService.ClickType var3, double var4, double var6) {
      if (var1 == null || var2 == null || !var2.editMode || !var2.previewMode) {
         return false;
      } else if (this.isPreviewPointerInteractionActive(var2)) {
         return false;
      } else {
         int var8 = this.resolveHoveredActivePageCloseSlot(var2, var4, var6);
         int var9 = this.resolveHoveredActivePageSlot(var2, var4, var6);
         if (var3 == GuiService.ClickType.RIGHT) {
            return var8 > 0 || var9 > 0;
         } else if (var3 != GuiService.ClickType.LEFT) {
            return false;
         } else {
            boolean var10 = var2.activePagesArmedPageKey != null && !var2.activePagesArmedPageKey.isBlank();
            if (!var2.activePagesReorderActive && (!var10 || !this.isLeftHeld(var2))) {
               if (var10) {
                  this.resetActivePagesDragState(var2);
               }

               boolean var11 = var8 > 0 || var9 > 0;
               if (!var11) {
                  return false;
               } else {
                  var2.lastLeftClickTick = (long)Bukkit.getCurrentTick();
                  this.resetActivePagesDragState(var2);
                  if (var8 > 0) {
                     return this.handleActivePageCloseClick(var1, var2, var8);
                  } else {
                     String var12 = this.resolveOpenPageAtVisibleSlot(var2, var9);
                     if (var12 != null && !var12.isBlank()) {
                        var2.activePagesArmedPageKey = var12;
                        var2.activePagesReorderPageKey = var12;
                        var2.activePagesArmedStartHitX = var4;
                        var2.activePagesArmedStartHitY = var6;
                        var2.activePagesVisibleHoveredSlot = var9;
                        var2.activePagesReorderInsertIndex = -1;
                        return true;
                     } else {
                        return false;
                     }
                  }
               }
            } else {
               var2.lastLeftClickTick = (long)Bukkit.getCurrentTick();
               return true;
            }
         }
      }
   }

   @Override
   protected boolean handleActivePagesCursorMove(Player var1, EditorSession var2, double var3, double var5) {
      if (var1 == null || var2 == null || !var2.editMode || !var2.previewMode) {
         return false;
      } else if (this.isPreviewPointerInteractionActive(var2)) {
         if (var2.activePagesReorderActive || var2.activePagesArmedPageKey != null && !var2.activePagesArmedPageKey.isBlank()) {
            this.clearActivePagesDragPreview(var1);
            this.resetActivePagesDragState(var2);
         }

         return false;
      } else {
         int var7 = this.resolveHoveredActivePageSlot(var2, var3, var5);
         var2.activePagesVisibleHoveredSlot = var7;
         boolean var8 = var2.activePagesArmedPageKey != null && !var2.activePagesArmedPageKey.isBlank();
         boolean var9 = this.isLeftHeld(var2);
         if (!var9) {
            if (var2.activePagesReorderActive) {
               this.applyActivePagesReorderDrop(var1, var2);
               this.clearActivePagesDragPreview(var1);
               this.resetActivePagesDragState(var2);
               return true;
            } else if (var8 && !var2.activePagesReorderActive) {
               String var15 = var2.activePagesArmedPageKey;
               this.clearActivePagesDragPreview(var1);
               this.resetActivePagesDragState(var2);
               return this.openActiveEditorPage(var1, var15);
            } else if (var8) {
               this.clearActivePagesDragPreview(var1);
               this.resetActivePagesDragState(var2);
               return true;
            } else {
               this.clearActivePagesDragPreview(var1);
               return false;
            }
         } else if (!var8) {
            return false;
         } else {
            if (!var2.activePagesReorderActive) {
               double var10 = Math.abs(var3 - var2.activePagesArmedStartHitX);
               double var12 = Math.abs(var5 - var2.activePagesArmedStartHitY);
               if (var10 >= 7.0 || var12 >= 7.0) {
                  var2.activePagesReorderActive = true;
                  var2.activePagesReorderInsertIndex = this.resolveActivePagesDropTarget(var2, var3).insertIndex;
                  this.startActivePagesReorderReleaseWatch(var1, var2);
               }
            }

            if (!var2.activePagesReorderActive) {
               return true;
            } else {
               GuiServiceEditorSupport.ActivePagesDropTarget var14 = this.resolveActivePagesDropTarget(var2, var3);
               var2.activePagesReorderInsertIndex = var14.insertIndex;
               this.renderActivePagesDragPreview(var1, var2, var3, var5, var14);
               return true;
            }
         }
      }
   }

   protected boolean handleActivePagesScroll(Player var1, EditorSession var2, double var3, double var5, int var7) {
      if (var1 != null && var2 != null && var7 != 0 && var2.editMode && var2.previewMode) {
         int var8 = this.resolveHoveredActivePageSlot(var2, var3, var5);
         int var9 = this.resolveHoveredActivePageCloseSlot(var2, var3, var5);
         if (var8 <= 0 && var9 <= 0) {
            return false;
         } else {
            List var10 = this.resolveOpenEditorPagesForPlayer(var1.getUniqueId(), var2.pageName);
            if (var10.size() <= 7) {
               return false;
            } else {
               int var11 = Math.max(0, var10.size() - 7);
               int var12 = var7 > 0 ? 1 : -1;
               int var13 = Math.max(0, Math.min(var11, var2.activePagesScrollOffset + var12));
               if (var13 == var2.activePagesScrollOffset) {
                  return true;
               } else {
                  var2.activePagesScrollOffset = var13;
                  var2.openEditorPages.clear();
                  var2.openEditorPages.addAll(var10);
                  this.rerenderActivePagesShell(var1, var2);
                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   protected int resolveHoveredActivePageSlot(EditorSession var1, double var2, double var4) {
      int var6 = this.resolveVisibleActivePagesCount(var1);

      for (int var7 = 1; var7 <= var6; var7++) {
         String var8 = "page_" + var7 + "_page_hitbox";
         if (this.isInsideShellBlock(var1, var8, var2, var4)) {
            return var7;
         }
      }

      return -1;
   }

   protected int resolveHoveredActivePageCloseSlot(EditorSession var1, double var2, double var4) {
      int var6 = this.resolveVisibleActivePagesCount(var1);

      for (int var7 = 1; var7 <= var6; var7++) {
         String var8 = "page_" + var7 + "_close_hitbox";
         if (this.isInsideShellBlock(var1, var8, var2, var4)) {
            return var7;
         }
      }

      return -1;
   }

   protected int resolveVisibleActivePagesCount(EditorSession var1) {
      if (var1 != null && var1.openEditorPages != null && !var1.openEditorPages.isEmpty()) {
         int var2 = var1.openEditorPages.size() - Math.max(0, var1.activePagesScrollOffset);
         return Math.max(0, Math.min(7, var2));
      } else {
         return 0;
      }
   }

   protected String resolveOpenPageAtVisibleSlot(EditorSession var1, int var2) {
      if (var1 != null && var1.openEditorPages != null && var2 > 0) {
         int var3 = var1.activePagesScrollOffset + var2 - 1;
         return var3 >= 0 && var3 < var1.openEditorPages.size() ? var1.openEditorPages.get(var3) : null;
      } else {
         return null;
      }
   }

   protected boolean handleActivePageCloseClick(Player var1, EditorSession var2, int var3) {
      this.clearActivePagesDragPreview(var1);
      this.resetActivePagesDragState(var2);
      UUID var4 = var1.getUniqueId();
      String var5 = this.resolveOpenPageAtVisibleSlot(var2, var3);
      if (var5 != null && !var5.isBlank()) {
         List var6 = this.resolveOpenEditorPagesForPlayer(var4, var2.pageName);
         int var7 = var6.indexOf(var5);
         if (var7 < 0) {
            return false;
         } else {
            boolean var8 = var5.equalsIgnoreCase(this.normalizePageKey(var2.pageName, ""));
            var6.remove(var7);
            this.playerEditorOpenPages.put(var4, new ArrayList<>(var6));
            this.forgetEditorWorkingPage(var4, var5);
            if (var6.isEmpty()) {
               this.playerEditorOpenPages.remove(var4);
               this.playerEditorWorkingPages.remove(var4);
               return this.openGui(var1, "editor_menu", false, true);
            } else {
               this.syncEditorWorkingPagesToOpenPages(var4, var6);
               if (var8) {
                  int var11 = Math.max(0, var7 - 1);
                  String var10 = (String)var6.get(var11);
                  return this.switchActiveEditorPageInPlace(var1, var2, var10);
               } else {
                  var2.openEditorPages.clear();
                  var2.openEditorPages.addAll(var6);
                  int var9 = Math.max(0, var6.size() - 7);
                  var2.activePagesScrollOffset = Math.max(0, Math.min(var9, var2.activePagesScrollOffset));
                  this.rerenderActivePagesShell(var1, var2);
                  return true;
               }
            }
         }
      } else {
         return false;
      }
   }

   protected boolean openActiveEditorPage(Player var1, String var2) {
      String var3 = this.normalizePageKey(var2, "");
      if (!var3.isBlank() && !this.isInternalEditorPageName(var3)) {
         EditorSession var4 = this.editorSessions.get(var1.getUniqueId());
         if (var4 == null || !var4.editMode || !var4.previewMode) {
            return false;
         } else {
            return var3.equalsIgnoreCase(this.normalizePageKey(var4.pageName, "")) ? true : this.switchActiveEditorPageInPlace(var1, var4, var3);
         }
      } else {
         return false;
      }
   }

   protected boolean switchActiveEditorPageInPlace(Player var1, EditorSession var2, String var3) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         String var4 = this.normalizePageKey(var3, "");
         if (!var4.isBlank() && !this.isInternalEditorPageName(var4)) {
            UUID var5 = this.findActiveEditorOwnerForPage(var4, var1.getUniqueId());
            if (var5 != null) {
               return this.notifyEditorPageLocked(var1, var4, var5);
            } else {
               UUID var6 = var1.getUniqueId();
               this.snapshotCurrentEditorPage(var6, var2);
               ArrayList var7 = new ArrayList();
               Map var8 = this.loadYamlFolder("contents/pages", var7);
               if (!var7.isEmpty()) {
                  this.reportYamlIssues(var7);
                  this.sendEditorPlayerMessage(
                     var1,
                     MM.deserialize(
                        "<red><bold>UltimateUI</bold></red> <#8a989c>»</#8a989c> <yellow>YAML syntax errors found. Check console for details.</yellow>"
                     )
                  );
                  return false;
               } else {
                  String var9 = this.resolveExistingPageMapKey(var8, var4);
                  YamlConfiguration var10 = var9.isBlank() ? null : (YamlConfiguration)var8.get(var9);
                  List var11 = this.resolveEditorWorkingPageSnapshot(var6, var4);
                  if (var10 == null && var11 == null && !this.isUntitledPageKey(var4)) {
                     this.sendEditorPlayerMessage(
                        var1,
                        MM.deserialize("<red><bold>UltimateUI</bold></red> <#8a989c>»</#8a989c> <yellow>Page not found:</yellow> <white>" + var4 + "</white>")
                     );
                     return false;
                  } else {
                     this.finishLayersReorderDrag(var1, var2, false);
                     this.resetActivePagesDragState(var2);
                     this.clearActivePagesDragPreview(var1);
                     this.stopCursorToolDrag(var2);
                     this.resetEditorTransientPanelsForPageSwitch(var1, var2);
                     var2.pageName = var4;
                     var2.pageFile = var10 == null ? null : new File(new File(this.plugin.getDataFolder(), "contents/pages"), var4 + ".yml");
                     var2.yaml = var10 == null ? new YamlConfiguration() : this.cloneYamlConfiguration(var10);
                     if (var10 == null) {
                        var2.yaml.set("name", this.formatUntitledPageDisplayName(var4));
                     }

                     this.ensurePageDisplayName(var2);
                     boolean var12 = var11 == null;
                     var2.rawBlocks = (List<Map<String, Object>>)(var12
                        ? (var10 == null ? new ArrayList<>() : this.copyBlocks(var10.getList("blocks")))
                        : var11);
                     var2.rawBlocks = this.expandCompactImageBlocks(var2.rawBlocks);
                     if (var12) {
                        this.applyImageLayoutOffsetFormatToLoadedBlocks(var10, var2.rawBlocks);
                     }

                     var2.yaml.set("blocks", this.copyBlocks(var2.rawBlocks));
                     var2.renderBlocks = this.resolveRenderableBlocks(var2.rawBlocks, var2.components);
                     this.rememberEditorWorkingPage(var6, var4, var2.rawBlocks);
                     this.restoreEditorLayerStateMetadata(var2);
                     var2.spawnOpacityRetoggledTargetIds.clear();
                     var2.pendingSpawnOpacityRetoggleTargetIds.clear();
                     var2.spawnOpacityRetoggleTaskQueued = false;
                     List var13 = this.resolveOpenEditorPagesForPlayer(var6, var4);
                     var2.openEditorPages.clear();
                     var2.openEditorPages.addAll(var13);
                     int var14 = Math.max(0, var13.size() - 7);
                     int var15 = var13.indexOf(var4);
                     if (var15 >= 0) {
                        if (var15 < var2.activePagesScrollOffset) {
                           var2.activePagesScrollOffset = var15;
                        } else if (var15 >= var2.activePagesScrollOffset + 7) {
                           var2.activePagesScrollOffset = var15 - 6;
                        }
                     }

                     var2.activePagesScrollOffset = Math.max(0, Math.min(var14, var2.activePagesScrollOffset));
                     this.rerenderActivePagesShell(var1, var2, false, true);
                     this.renderPreviewPageOutline(var1, var2);
                     this.rerenderEditableContent(var1, var2);
                     var2.rightSidebarTab = EditorSidebarTab.LAYERS;
                     var2.rightSidebarHoverTab = null;
                     this.rerenderEditorShellForPageSwitch(var1, var2);
                     this.setRightSidebarTab(var1, var2, EditorSidebarTab.LAYERS, false);
                     this.renderEditorTransparencyOverlay(var1, var2);
                     this.updatePageInfoReadout(var1, var2);
                     double var16 = var2.cursorX + var2.hitboxOffsetX;
                     double var18 = var2.cursorY + var2.hitboxOffsetY;
                     this.updateEditorNavbarHover(var1, var2, var16, var18);
                     this.updateSidebarTabHover(var1, var2, var16, var18);
                     this.updateCursorPositionReadout(var1, var2, var16, var18);
                     return true;
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

   protected void applyActivePagesReorderDrop(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.activePagesReorderActive) {
         List var3 = this.resolveOpenEditorPagesForPlayer(var1.getUniqueId(), var2.pageName);
         if (var3.size() > 1) {
            String var4 = this.firstNonBlank(new String[]{var2.activePagesReorderPageKey});
            if (!var4.isBlank()) {
               int var5 = var3.indexOf(var4);
               if (var5 >= 0) {
                  int var6 = var2.activePagesReorderInsertIndex;
                  if (var6 < 0) {
                     var6 = var5;
                  }

                  var6 = Math.max(0, Math.min(var3.size(), var6));
                  if (var6 > var5) {
                     var6--;
                  }

                  if (var6 != var5) {
                     String var7 = (String)var3.remove(var5);
                     var3.add(var6, var7);
                     this.playerEditorOpenPages.put(var1.getUniqueId(), new ArrayList<>(var3));
                     var2.openEditorPages.clear();
                     var2.openEditorPages.addAll(var3);
                     int var8 = Math.max(0, var3.size() - 7);
                     var2.activePagesScrollOffset = Math.max(0, Math.min(var8, var2.activePagesScrollOffset));
                     this.rerenderActivePagesShell(var1, var2);
                  }
               }
            }
         }
      }
   }

   protected void startActivePagesReorderReleaseWatch(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.activePagesReorderActive) {
         int var3 = ++var2.activePagesReorderReleaseWatchToken;
         PlatformCompat.runEntityTimer(this.plugin, var1, 1L, 1L, var4 -> {
            if (!var1.isOnline()) {
               var4.cancel();
            } else {
               EditorSession var5 = this.editorSessions.get(var1.getUniqueId());
               if (var5 == null || var5 != var2 || var3 != var5.activePagesReorderReleaseWatchToken) {
                  var4.cancel();
               } else if (!var5.activePagesReorderActive) {
                  var4.cancel();
               } else if (!this.isLeftHeld(var5)) {
                  this.applyActivePagesReorderDrop(var1, var5);
                  this.clearActivePagesDragPreview(var1);
                  this.resetActivePagesDragState(var5);
                  var4.cancel();
               }
            }
         });
      }
   }

   protected GuiServiceEditorSupport.ActivePagesDropTarget resolveActivePagesDropTarget(EditorSession var1, double var2) {
      ArrayList var4 = new ArrayList();
      int var5 = this.resolveVisibleActivePagesCount(var1);

      for (int var6 = 1; var6 <= var5; var6++) {
         EditorRect var7 = this.findShellBlockRect(var1, "page_" + var6 + "_page_hitbox");
         if (var7 != null) {
            var4.add(var7);
         }
      }

      if (var4.isEmpty()) {
         return new GuiServiceEditorSupport.ActivePagesDropTarget(0, var2, 0.0, 0.0);
      } else {
         int var11 = var4.size();

         for (int var12 = 0; var12 < var4.size(); var12++) {
            EditorRect var8 = (EditorRect)var4.get(var12);
            double var9 = var8.x + var8.width / 2.0;
            if (var2 < var9) {
               var11 = var12;
               break;
            }
         }

         double var13;
         if (var11 <= 0) {
            var13 = ((EditorRect)var4.getFirst()).x;
         } else if (var11 >= var4.size()) {
            EditorRect var14 = (EditorRect)var4.getLast();
            var13 = var14.x + var14.width;
         } else {
            EditorRect var15 = (EditorRect)var4.get(var11 - 1);
            EditorRect var10 = (EditorRect)var4.get(var11);
            var13 = (var15.x + var15.width + var10.x) / 2.0;
         }

         EditorRect var16 = (EditorRect)var4.getFirst();
         int var17 = Math.max(0, Math.min(var1.openEditorPages.size(), var1.activePagesScrollOffset + var11));
         return new GuiServiceEditorSupport.ActivePagesDropTarget(var17, var13, var16.y, var16.height);
      }
   }

   protected void renderActivePagesDragPreview(Player var1, EditorSession var2, double var3, double var5, GuiServiceEditorSupport.ActivePagesDropTarget var7) {
      if (var1 != null && var2 != null && var7 != null && var2.activePagesReorderActive) {
         String var8 = this.firstNonBlank(new String[]{var2.activePagesReorderPageKey});
         if (var8.isBlank()) {
            this.clearActivePagesDragPreview(var1);
         } else {
            boolean var9 = var8.equalsIgnoreCase(this.normalizePageKey(var2.pageName, ""));
            String var10 = var9 ? "141414" : "0f0f0f";
            String var11 = var9 ? "ffffff" : "999999";
            double var12 = var3 - 97.5;
            double var14 = var5 - 18.0;
            String var16 = this.withEditorUiFont(this.withHexPrefix("\ue67b", var10));
            this.renderRoundedBlock(
               var1,
               "editor_shell_active_pages_drag_ghost",
               var12,
               var14,
               9330.0,
               195.0,
               36.0,
               var16,
               220,
               6.0,
               "\ue16a",
               0.0,
               0.0,
               -1.0,
               1.0,
               -1.0,
               1.0,
               0.0,
               1.0,
               0.0,
               false,
               false,
               var12 + 97.5,
               var14 + 18.0
            );
            double var17 = 82.0;
            double var19 = 82.0;
            double var21 = this.toInternalTextTopY(var14 + 13.0, var19);
            HudPositionCalculator.Placement var23 = this.positionCalculator
               .calculateBoxPlacement(this.applyTextAlignmentOffset(var12 + 12.0, var17, TextAlignment.LEFT), var21, 9331.0, var17, var19);
            this.upsertHud(
               var1,
               "editor_shell_active_pages_drag_ghost_label",
               var23.location(),
               var23.scale(),
               this.withEditorUiFont(this.withHexPrefix(this.formatActivePageDisplayName(var8), var11)),
               255,
               TextAlignment.LEFT
            );
            double var24 = 64.0;
            double var26 = 64.0;
            double var28 = this.toInternalTextTopY(var14 + 15.0, var26);
            HudPositionCalculator.Placement var30 = this.positionCalculator
               .calculateBoxPlacement(this.applyTextAlignmentOffset(var12 + 171.0, var24, TextAlignment.LEFT), var28, 9331.0, var24, var26);
            this.upsertHud(
               var1,
               "editor_shell_active_pages_drag_ghost_close_icon",
               var30.location(),
               var30.scale(),
               this.withEditorUiFont(this.withHexPrefix("\ue5e0", "ffffff")),
               255,
               TextAlignment.LEFT
            );
            this.removeHudById(var1, "editor_shell_active_pages_drop_marker");
         }
      } else {
         this.clearActivePagesDragPreview(var1);
      }
   }

   @Override
   protected void clearActivePagesDragPreview(Player var1) {
      if (var1 != null) {
         this.clearOutlineHud(var1, "editor_shell_active_pages_drag_ghost");
         this.removeBaseHud(var1, "editor_shell_active_pages_drag_ghost");
         this.removeRoundedParts(var1, "editor_shell_active_pages_drag_ghost");
         this.removeHudById(var1, "editor_shell_active_pages_drag_ghost_label");
         this.removeHudById(var1, "editor_shell_active_pages_drag_ghost_close_icon");
         this.removeHudById(var1, "editor_shell_active_pages_drop_marker");
      }
   }

   @Override
   protected void resetEditorTransientPanelsForPageSwitch(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         var2.fileDropdownVisible = false;
         var2.fileDropdownHoverTargetId = null;
         var2.fileNavbarVisualActive = false;
         var2.editDropdownVisible = false;
         var2.editDropdownHoverTargetId = null;
         var2.editNavbarVisualActive = false;
         var2.selectionDropdownVisible = false;
         var2.selectionDropdownHoverTargetId = null;
         var2.selectionNavbarVisualActive = false;
         var2.layerDropdownVisible = false;
         var2.layerDropdownHoverTargetId = null;
         var2.layerNavbarVisualActive = false;
         var2.windowDropdownVisible = false;
         var2.windowDropdownHoverTargetId = null;
         var2.windowNavbarVisualActive = false;
         var2.cursorPageDropdownVisible = false;
         var2.cursorPageDropdownHoverTargetId = null;
         var2.cursorPageDropdownOpenToken++;
         var2.cursorLayersDropdownVisible = false;
         var2.cursorLayersDropdownHoverTargetId = null;
         var2.cursorLayersDropdownContextTargetId = null;
         var2.cursorLayersDropdownContextTargetPath = null;
         var2.cursorLayersDropdownOpenToken++;
         var2.cursorActionsDropdownVisible = false;
         var2.cursorActionsDropdownHoverTargetId = null;
         var2.cursorActionsEditDropdownVisible = false;
         var2.cursorActionsEditDropdownHoverTargetId = null;
         var2.welcomePopupVisible = false;
         var2.savePopupVisible = false;
         var2.preferencesPopupVisible = false;
         var2.pendingSavePopupField = null;
         this.clearPendingTextToolEdit(var1, var2, true);
         var2.colorPickerVisible = false;
         var2.colorPickerGradDragActive = false;
         var2.colorPickerHueDragActive = false;
         var2.colorPickerHexInputPending = false;
         var2.pendingPropertyField = null;
         this.clearPendingItemDesignInventoryPick(var2);
         this.stopOpacitySliderDrag(var2, true);
         var2.rightSidebarHoverTab = null;
         this.clearAlignToolbarOverlay(var1, var2);
         this.resetSidebarFieldDrag(var1, var2);
         this.beginShellOpacityBatch(var2);

         try {
            this.setSidebarPanelVisible(var1, var2, "file_dropdown", false);
            this.setSidebarPanelVisible(var1, var2, "edit_dropdown", false);
            this.setSidebarPanelVisible(var1, var2, "selection_dropdown", false);
            this.setSidebarPanelVisible(var1, var2, "layer_dropdown", false);
            this.setSidebarPanelVisible(var1, var2, "window_dropdown", false);
            this.setSidebarPanelVisible(var1, var2, "cursor_page_dropdown", false);
            this.setSidebarPanelVisible(var1, var2, "cursor_layers_dropdown", false);
            this.setSidebarPanelVisible(var1, var2, "cursor_actions_dropdown", false);
            this.setSidebarPanelVisible(var1, var2, "cursor_actions_edit_dropdown", false);
            this.setSidebarPanelVisible(var1, var2, "popup_welcome_backdrop", false);
            this.setSidebarPanelVisible(var1, var2, "popup_welcome_root", false);
            this.setSidebarPanelVisible(var1, var2, "popup_save_root", false);
            this.setSidebarPanelVisible(var1, var2, "popup_preferences_root", false);
            this.setSidebarPanelVisible(var1, var2, "colorpicker_ui", false);
            this.setSidebarPanelVisible(var1, var2, "properties", false);
            this.setSidebarPanelVisible(var1, var2, "actions", false);
            this.setSidebarPanelVisible(var1, var2, "design", false);
            this.setSidebarPanelVisible(var1, var2, "item_design", false);
            this.setSidebarPanelVisible(var1, var2, "layers", false);
         } finally {
            this.endShellOpacityBatch(var1, var2);
         }
      }
   }

   protected List<Map<String, Object>> collectPageSwitchShellRefreshBlocks(List<Map<String, Object>> var1) {
      ArrayList var2 = new ArrayList();
      if (var1 != null && !var1.isEmpty()) {
         HashMap var3 = new HashMap();
         int var4 = 0;

         for (Map var6 : var1) {
            var4++;
            ConfigurationSection var7 = this.mapToSection(var6);
            if (var7 != null) {
               String var8 = this.firstNonBlank(new String[]{var7.getString("type"), "block"}).toLowerCase(Locale.ROOT);
               if ("block".equals(var8) || "text".equals(var8) || this.isRoundedType(var8)) {
                  String var9 = this.resolveElementId(var7, var4, var3);
                  String var10 = this.firstNonBlank(new String[]{var9}).toLowerCase(Locale.ROOT);
                  if (!"left_sidebar".equals(var10)) {
                     var2.add(var6);
                  }
               }
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   @Override
   protected void rerenderEditorShellForPageSwitch(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         if (var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
            List var3 = var2.shellBlocks;
            List var4 = this.collectPageSwitchShellRefreshBlocks(var3);
            if (!var4.isEmpty()) {
               var2.shellCacheBuilt = false;
               var2.shellSectionCache.clear();
               var2.shellStaticRectCache.clear();
               var2.shellRuntimeRects.clear();
               var2.shellBlocks = var4;

               try {
                  this.renderEditorShell(var1, var2);
               } finally {
                  var2.shellBlocks = var3;
                  var2.shellCacheBuilt = false;
                  var2.shellSectionCache.clear();
                  var2.shellStaticRectCache.clear();
                  var2.shellRuntimeRects.clear();
               }
            }
         }
      }
   }

   protected LinkedHashSet<String> collectActivePagesShellLogicalIds(List<Map<String, Object>> var1) {
      LinkedHashSet var2 = new LinkedHashSet();
      this.collectActivePagesShellLogicalIds(var1, false, var2);
      return var2;
   }

   protected void collectActivePagesShellLogicalIds(List<Map<String, Object>> var1, boolean var2, Set<String> var3) {
      if (var1 != null && !var1.isEmpty() && var3 != null) {
         for (Map var5 : var1) {
            if (var5 != null && !var5.isEmpty()) {
               String var6 = this.firstNonBlank(new String[]{this.stringValue(var5.get("id"))});
               boolean var7 = var2 || "active_pages".equalsIgnoreCase(var6);
               if (var7 && !var6.isBlank()) {
                  var3.add(var6);
               }

               if (var5.get("children") instanceof List var9 && !var9.isEmpty()) {
                  ArrayList var10 = new ArrayList();

                  for (Object var12 : var9) {
                     if (var12 instanceof Map var13) {
                        var10.add(var13);
                     }
                  }

                  this.collectActivePagesShellLogicalIds(var10, var7, var3);
               }
            }
         }
      }
   }

   protected void clearActivePagesRuntimeHudByLogicalId(Player var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = "editor_shell_" + var2;
         this.clearOutlineHud(var1, var3);
         this.removeBaseHud(var1, var3);
         this.removeRoundedParts(var1, var3);
         this.clearOutlineHud(var1, var2);
         this.removeBaseHud(var1, var2);
         this.removeRoundedParts(var1, var2);
      }
   }

   @Override
   protected void clearActivePagesRuntimeSlots(Player var1, Set<String> var2) {
      if (var1 != null) {
         if (var2 != null && !var2.isEmpty()) {
            LinkedHashSet var3 = new LinkedHashSet();

            for (Object var5_raw : var2) {
               String var5 = var5_raw != null ? var5_raw.toString() : null;
               this.clearActivePagesRuntimeHudByLogicalId(var1, var5);
               int var6 = this.resolveActivePageSlotFromLogicalId(var5);
               if (var6 > 0) {
                  var3.add(Integer.valueOf(var6));
               }
            }

            this.clearActivePagesRuntimeSlotsByIndex(var1, var3);
         }
      }
   }

   protected boolean isActivePagesRuntimeHudId(String var1) {
      return var1 != null && !var1.isBlank() ? ACTIVE_PAGES_RUNTIME_HUD_ID_PATTERN.matcher(var1).matches() : false;
   }

   protected int resolveActivePageSlotFromRuntimeHudId(String var1) {
      if (var1 != null && !var1.isBlank()) {
         Matcher var2 = ACTIVE_PAGES_RUNTIME_HUD_ID_PATTERN.matcher(var1);
         if (!var2.matches()) {
            return -1;
         } else {
            try {
               return Integer.parseInt(this.firstNonBlank(new String[]{var2.group(1), "-1"}));
            } catch (NumberFormatException var4) {
               return -1;
            }
         }
      } else {
         return -1;
      }
   }

   protected void clearActivePagesRuntimeSlotsByIndex(Player var1, Set<Integer> var2) {
      if (var1 != null && var2 != null && !var2.isEmpty()) {
         LinkedHashSet var3 = new LinkedHashSet();

         for (Object var5_raw : var2) {
            int var5 = ((Number)var5_raw).intValue();
            if (var5 > 0) {
               var3.add(Integer.valueOf(var5));
            }
         }

         if (!var3.isEmpty()) {
            for (Object var11_raw : var3) {
               int var11 = ((Number)var11_raw).intValue();
               this.clearActivePagesRuntimeHudByLogicalId(var1, "page_" + var11 + "_page_hitbox");
               this.clearActivePagesRuntimeHudByLogicalId(var1, "page_" + var11 + "_close_hitbox");
               this.clearActivePagesRuntimeHudByLogicalId(var1, "page_" + var11 + "_label");
               this.clearActivePagesRuntimeHudByLogicalId(var1, "page_" + var11 + "_close_icon");
            }

            ArrayList var10 = new ArrayList();

            for (Entity var6 : this.hudService.getAllHuds(var1)) {
               if (var6 != null && var6.hasMetadata("id")) {
                  String var7 = ((MetadataValue)var6.getMetadata("id").get(0)).asString();
                  int var8 = this.resolveActivePageSlotFromRuntimeHudId(var7);
                  if (var8 > 0 && var3.contains(Integer.valueOf(var8))) {
                     var10.add(var6);
                  }
               }
            }

            if (!var10.isEmpty()) {
               this.hudService.removeHudX(var10, false);
            }
         }
      }
   }

   @Override
   protected boolean isActivePagesShellLogicalId(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).toLowerCase(Locale.ROOT);
      return !var2.isBlank() && var2.startsWith("page_")
         ? var2.endsWith("_page_hitbox") || var2.endsWith("_close_hitbox") || var2.endsWith("_label") || var2.endsWith("_close_icon")
         : false;
   }

   protected int resolveActivePageSlotFromLogicalId(String var1) {
      String var2 = this.firstNonBlank(new String[]{var1}).toLowerCase(Locale.ROOT);
      if (!var2.isBlank() && var2.startsWith("page_")) {
         int var3 = var2.indexOf(95, "page_".length());
         if (var3 <= "page_".length()) {
            return -1;
         } else {
            String var4 = var2.substring("page_".length(), var3);

            try {
               return Integer.parseInt(var4);
            } catch (NumberFormatException var6) {
               return -1;
            }
         }
      } else {
         return -1;
      }
   }

   protected LinkedHashMap<Integer, String> resolveVisibleActivePageSlotStates(List<String> var1, int var2, String var3) {
      LinkedHashMap var4 = new LinkedHashMap();
      if (var1 != null && !var1.isEmpty()) {
         int var5 = Math.max(0, var1.size() - 7);
         int var6 = Math.max(0, Math.min(var5, var2));
         String var7 = this.normalizePageKey(var3, "");
         int var8 = Math.min(7, Math.max(0, var1.size() - var6));

         for (int var9 = 1; var9 <= var8; var9++) {
            String var10 = (String)var1.get(var6 + var9 - 1);
            boolean var11 = !var7.isBlank() && var7.equalsIgnoreCase(var10);
            var4.put(Integer.valueOf(var9), this.encodeActivePageSlotState(var10, var11));
         }

         return var4;
      } else {
         return var4;
      }
   }

   protected String encodeActivePageSlotState(String var1, boolean var2) {
      String var3 = this.normalizePageKey(var1, "").toLowerCase(Locale.ROOT);
      return var3.isBlank() ? "" : var3 + "|" + (var2 ? "1" : "0");
   }

   protected LinkedHashSet<Integer> resolveChangedActivePageSlots(Map<Integer, String> var1, Map<Integer, String> var2) {
      LinkedHashSet var3 = new LinkedHashSet();

      for (int var4 = 1; var4 <= 7; var4++) {
         String var5 = var1 == null ? null : (String)var1.get(var4);
         String var6 = var2 == null ? null : (String)var2.get(var4);
         if (!this.equalsNullable(var5, var6)) {
            var3.add(Integer.valueOf(var4));
         }
      }

      return var3;
   }

   @Override
   protected void syncActivePagesRenderedSlotStates(EditorSession var1) {
      if (var1 != null) {
         var1.activePagesRenderedSlotStates.clear();
         var1.activePagesRenderedSlotStates.putAll(this.resolveVisibleActivePageSlotStates(var1.openEditorPages, var1.activePagesScrollOffset, var1.pageName));
      }
   }

   protected List<Map<String, Object>> collectActivePagesRenderableShellBlocks(List<Map<String, Object>> var1) {
      return this.collectActivePagesRenderableShellBlocks(var1, null);
   }

   protected List<Map<String, Object>> collectActivePagesRenderableShellBlocks(List<Map<String, Object>> var1, Set<Integer> var2) {
      ArrayList var3 = new ArrayList();
      if (var1 != null && !var1.isEmpty()) {
         HashMap var4 = new HashMap();
         int var5 = 0;

         for (Map var7 : var1) {
            var5++;
            ConfigurationSection var8 = this.mapToSection(var7);
            if (var8 != null) {
               String var9 = this.firstNonBlank(new String[]{var8.getString("type"), "block"}).toLowerCase(Locale.ROOT);
               if ("block".equals(var9) || "text".equals(var9) || this.isRoundedType(var9)) {
                  String var10 = this.resolveElementId(var8, var5, var4);
                  if (this.isActivePagesShellLogicalId(var10)) {
                     int var11 = this.resolveActivePageSlotFromLogicalId(var10);
                     if (var2 == null || var2.isEmpty() || var2.contains(var11)) {
                        var3.add(var7);
                     }
                  }
               }
            }
         }

         return var3;
      } else {
         return var3;
      }
   }

   protected void clearActivePagesRuntimeRectCache(EditorSession var1) {
      if (var1 != null && var1.shellRuntimeRects != null && !var1.shellRuntimeRects.isEmpty()) {
         var1.shellRuntimeRects.entrySet().removeIf(var1x -> this.isActivePagesShellLogicalId(var1x.getKey()));
      }
   }

   @Override
   protected void rerenderActivePagesShell(Player var1, EditorSession var2) {
      this.rerenderActivePagesShell(var1, var2, false, false);
   }

   @Override
   protected void rerenderActivePagesShell(Player var1, EditorSession var2, boolean var3) {
      this.rerenderActivePagesShell(var1, var2, var3, false);
   }

   @Override
   protected void rerenderActivePagesShell(Player var1, EditorSession var2, boolean var3, boolean var4) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         LinkedHashMap var5 = new LinkedHashMap<>(var2.activePagesRenderedSlotStates);
         LinkedHashSet var6 = this.collectActivePagesShellLogicalIds(var2.shellBlocks);
         List var7 = this.resolveOpenEditorPagesForPlayer(var1.getUniqueId(), var2.pageName);
         var2.openEditorPages.clear();
         var2.openEditorPages.addAll(var7);
         int var8 = Math.max(0, var7.size() - 7);
         var2.activePagesScrollOffset = Math.max(0, Math.min(var8, var2.activePagesScrollOffset));
         var2.shellBlocks = this.buildEditorShellBlocksWithActivePages(
            var2.editorShellTemplateBlocks, var2.components, var2.openEditorPages, var2.activePagesScrollOffset, var2.pageName
         );
         LinkedHashSet var9 = this.collectActivePagesShellLogicalIds(var2.shellBlocks);
         var6.removeAll(var9);
         LinkedHashMap var10 = this.resolveVisibleActivePageSlotStates(var2.openEditorPages, var2.activePagesScrollOffset, var2.pageName);
         LinkedHashSet var11 = this.resolveChangedActivePageSlots(var5, var10);
         this.clearActivePagesRuntimeRectCache(var2);
         var2.shellCacheBuilt = false;
         var2.shellSectionCache.clear();
         var2.shellStaticRectCache.clear();
         this.clearActivePagesRuntimeSlots(var1, var6);
         this.clearActivePagesRuntimeSlotsByIndex(var1, var11);
         if (!var11.isEmpty()) {
            List var12 = var2.shellBlocks;
            List var13 = this.collectActivePagesRenderableShellBlocks(var12, var11);
            var2.shellBlocks = var13;

            try {
               this.renderEditorShell(var1, var2);
            } finally {
               var2.shellBlocks = var12;
            }
         }

         var2.activePagesRenderedSlotStates.clear();
         var2.activePagesRenderedSlotStates.putAll(var10);
         if (var3) {
            this.renderEditorToolSidebar(var1, var2);
         }

         if (var4) {
            this.updateEditorPropertiesSidebar(var1, var2);
         }

         this.updatePageInfoReadout(var1, var2);
         this.setShellColor(var1, var2, "editor_color_1", var2.editorColor1);
         this.setShellColor(var1, var2, "editor_color_2", var2.editorColor2);
      }
   }

   @Override
   protected void resetActivePagesDragState(EditorSession var1) {
      if (var1 != null) {
         var1.activePagesReorderReleaseWatchToken++;
         var1.activePagesArmedPageKey = null;
         var1.activePagesReorderPageKey = null;
         var1.activePagesReorderActive = false;
         var1.activePagesReorderInsertIndex = -1;
         var1.activePagesArmedStartHitX = 0.0;
         var1.activePagesArmedStartHitY = 0.0;
      }
   }

   protected boolean isPreviewPointerInteractionActive(EditorSession var1) {
      return var1 == null ? false : var1.moveDragActive || var1.previewPanActive || var1.marqueeSelectActive;
   }

   public List<String> getGuiNames() {
      File var1 = new File(this.plugin.getDataFolder(), "contents/pages");
      if (var1.exists() && var1.isDirectory()) {
         File[] var2 = var1.listFiles((var0, var1x) -> var1x.toLowerCase().endsWith(".yml"));
         if (var2 == null) {
            return Collections.emptyList();
         } else {
            ArrayList var3 = new ArrayList();

            for (File var7 : var2) {
               String var8 = var7.getName();
               var3.add(var8.substring(0, var8.length() - 4));
            }

            return var3;
         }
      } else {
         return Collections.emptyList();
      }
   }

   public boolean hasOpenSession(Player var1) {
      return var1 == null ? false : this.editorSessions.containsKey(var1.getUniqueId());
   }

   public void restoreRuntimeHudSession(Player var1) {
      if (var1 != null) {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && !var2.editMode && var2.hudTextOnlyMode) {
            this.cameraService.forceStop(var1);
            this.hudService.clearHuds(var1);
            this.restoreClientHotbarFromServerInventory(var1);
            this.restoreHeldHotbarSlotFromServerInventory(var1);
            this.renderPage(var1, var2, false);
         }
      }
   }

   public String getActiveGuiName(Player var1) {
      if (var1 == null) {
         return null;
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         return var2 != null ? var2.pageName : null;
      }
   }

   public UiSession getUiSession(Player var1) {
      if (var1 == null) {
         return null;
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         return var2 == null
            ? null
            : new UiSession(
               var2.pageName != null ? var2.pageName : "", var2.hudTextOnlyMode && !var2.editMode, var2.editMode, var2.runtimeAutoCloseAfterAnimations
            );
      }
   }

   @Override
   public boolean closeGui(Player var1, String var2) {
      if (var1 == null) {
         return false;
      } else {
         String var3 = this.normalizePageKey(var2, "");
         if (var3.isBlank()) {
            return false;
         } else {
            EditorSession var4 = this.editorSessions.get(var1.getUniqueId());
            if (var4 == null) {
               return false;
            } else if (var4.hudTextOnlyMode && !var4.editMode) {
               boolean var7 = this.removeRuntimeHudStackPage(var4, var3);
               if (!var7) {
                  String var6 = this.normalizePageKey(var4.pageName, "");
                  if (!var3.equalsIgnoreCase(var6)) {
                     return false;
                  } else {
                     this.closeGui(var1);
                     return true;
                  }
               } else {
                  this.invalidateRuntimeOpenAnimationToken(var4, var3);
                  if (var4.rawBlocks != null && !var4.rawBlocks.isEmpty() && var4.renderBlocks != null && !var4.renderBlocks.isEmpty()) {
                     this.renderPage(var1, var4, false);
                     return true;
                  } else {
                     this.closeGui(var1);
                     return true;
                  }
               }
            } else {
               String var5 = this.normalizePageKey(var4.pageName, "");
               if (!var3.equalsIgnoreCase(var5)) {
                  return false;
               } else {
                  this.closeGui(var1);
                  return true;
               }
            }
         }
      }
   }

   protected boolean removeRuntimeHudStackPage(EditorSession var1, String var2) {
      if (var1 == null) {
         return false;
      } else {
         String var3 = this.normalizePageKey(var2, "");
         if (var3.isBlank()) {
            return false;
         } else {
            ArrayList var4 = new ArrayList();
            boolean var5 = false;

            for (Map var7 : this.copyBlocks(var1.rawBlocks)) {
               if (this.isRuntimeHudStackPageMatch(var7, var3)) {
                  var5 = true;
               } else {
                  var4.add(var7);
               }
            }

            ArrayList var10 = new ArrayList();
            boolean var11 = false;

            for (Map var9 : this.copyBlocks(var1.renderBlocks)) {
               if (this.isRuntimeHudStackPageMatch(var9, var3)) {
                  var11 = true;
               } else {
                  var10.add(var9);
               }
            }

            if (!var5 && !var11) {
               return false;
            } else {
               var1.rawBlocks = var4;
               var1.renderBlocks = var10;
               var1.runtimePageKeepOpen.remove(var3);
               var1.runtimePageReopen.remove(var3);
               var1.runtimePageCloseOnDeath.remove(var3);
               var1.runtimePageCloseOnDamage.remove(var3);
               return true;
            }
         }
      }
   }

   @Override
   public void closeGui(Player var1) {
      if (var1 != null) {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && var2.editMode) {
            this.clearAnimationTimelinePreviewOffset(var1, var2);
            this.clearAnimationTimelineRuntimeKeyframes(var1, var2);
         }

         if (var2 == null || var2.editMode || !this.applyPageCloseEffect(var1, var2)) {
            this.cameraService.forceStop(var1);
            EditorSession var3 = this.editorSessions.remove(var1.getUniqueId());
            this.playerEditorOpenPages.remove(var1.getUniqueId());
            this.playerEditorWorkingPages.remove(var1.getUniqueId());
            this.clearActivePagesDragPreview(var1);
            if (var3 != null) {
               if (var3.editMode) {
                  this.savePlayerColorPrefs(var1, var3);
               }

               this.clearEditorOverlays(var1);
               this.removeHudById(var1, "editor_page_outline_top");
               this.removeHudById(var1, "editor_page_outline_bottom");
               this.removeHudById(var1, "editor_page_outline_left");
               this.removeHudById(var1, "editor_page_outline_right");
               this.clearLayersRuntimeHud(var1, var3);
            }

            this.hudService.clearHuds(var1);
            this.restoreClientHotbarFromServerInventory(var1);
         }
      }
   }

   @Override
   protected void applyEditorDisplayPreferences(Player var1) {
      if (var1 != null) {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && var2.editMode && var2.previewMode) {
            this.applyEditorDisplayPreferences(var1, var2);
         } else {
            this.setClientHotbarVisibleAsAir(var1);
         }
      }
   }

   @Override
   protected void applyEditorDisplayPreferences(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         if (var2.displayHotbar) {
            this.restoreClientHotbarFromServerInventory(var1);
         } else {
            this.setClientHotbarVisibleAsAir(var1);
         }

         if (var2.displayHand) {
            this.restoreHeldHotbarSlotFromServerInventory(var1);
         } else {
            this.setHeldHotbarSlotAsAir(var1);
         }

         this.syncPreviewUiAddonsVisibility(var1, var2);
      }
   }

   protected void scheduleClientHotbarMaskRefresh(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         UUID var3 = var1.getUniqueId();

         for (long var7 : HOTBAR_MASK_REFRESH_DELAYS) {
            if (var7 > 0L) {
               PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
                  if (var1.isOnline()) {
                     EditorSession var4 = this.editorSessions.get(var3);
                     if (var4 != null && var4 == var2) {
                        this.applyEditorDisplayPreferences(var1, var4);
                     }
                  }
               }, var7);
            }
         }
      }
   }

   protected void syncPreviewUiAddonsVisibility(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         boolean var3 = var2.displayHand || var2.displayHotbar;
         ConfigurationSection var4 = this.findShellBlockSection(var2, "preview_ui_addons");
         this.beginShellOpacityBatch(var2);

         try {
            if (var3 && var4 != null) {
               this.spawnShellPanelHud(var1, var2, var4, "preview_ui_addons");
            }

            this.setSidebarPanelVisible(var1, var2, "preview_ui_addons", var3);
            if (!var3) {
               return;
            }

            this.setShellOpacity(var1, "preview_ui_addons_hand_icon", var2.displayHand ? 255 : 0);
            this.setShellOpacity(var1, "preview_ui_addons_hotbar_icon", var2.displayHotbar ? 255 : 0);
            this.setPreviewUiAddonGlyphOpacity(var1, var2, "\ue1b3", var2.displayHand ? 255 : 0);
            this.setPreviewUiAddonGlyphOpacity(var1, var2, "\ue1b4", var2.displayHotbar ? 255 : 0);
         } finally {
            this.endShellOpacityBatch(var1, var2);
         }

         if (var3) {
            this.updatePreviewUiAddonsAnchor(var1, var2);
            this.schedulePreviewUiAddonsAnchorStabilization(var1, var2);
         }
      }
   }

   protected void schedulePreviewUiAddonsAnchorStabilization(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         UUID var3 = var1.getUniqueId();

         for (long var7 : PREVIEW_UI_ADDONS_ANCHOR_SYNC_DELAYS) {
            if (var7 > 0L) {
               PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
                  if (var1.isOnline()) {
                     EditorSession var4 = this.editorSessions.get(var3);
                     if (var4 != null && var4 == var2 && var4.editMode && var4.previewMode) {
                        if (var4.displayHand || var4.displayHotbar) {
                           this.updatePreviewUiAddonsAnchor(var1, var4);
                        }
                     }
                  }
               }, var7);
            }
         }
      }
   }

   protected void setPreviewUiAddonGlyphOpacity(Player var1, EditorSession var2, String var3, int var4) {
      if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         ConfigurationSection var5 = this.findShellBlockSection(var2, "preview_ui_addons");
         String var6 = this.firstNonBlank(new String[]{var5 == null ? null : var5.getString("__editor_target_path")});
         if (!var6.isBlank()) {
            int var7 = Math.max(0, Math.min(255, var4));
            HashMap var8 = new HashMap();
            int var9 = 0;

            for (Map var11 : var2.shellBlocks) {
               var9++;
               ConfigurationSection var12 = this.mapToSection(var11);
               if (var12 != null) {
                  String var13 = this.firstNonBlank(new String[]{var12.getString("__editor_target_path")});
                  if (this.belongsToSidebarPanel(var6, var13)) {
                     String var14 = this.firstNonBlank(new String[]{var12.getString("type"), "block"}).toLowerCase(Locale.ROOT);
                     if ("text".equals(var14)) {
                        String var15 = this.firstNonBlank(new String[]{var12.getString("text"), var12.getString("unicode")});
                        if (var3.equals(var15)) {
                           String var16 = this.resolveElementId(var12, var9, var8);
                           if (var16 != null && !var16.isBlank()) {
                              this.setShellOpacity(var1, var16, var7);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   protected void applyEditorHeldToolMask(Player var1) {
      if (var1 != null) {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && var2.editMode && var2.previewMode && var2.displayHand) {
            this.restoreHeldHotbarSlotFromServerInventory(var1);
         } else {
            this.setHeldHotbarSlotAsAir(var1);
         }
      }
   }

   protected void setClientHotbarVisibleAsAir(Player var1) {
      if (var1 != null) {
         ItemStack var2 = new ItemStack(Material.AIR);

         for (int var3 = 0; var3 < 9; var3++) {
            this.sendClientHotbarSlot(var1, var3, var2);
         }
      }
   }

   protected void setHeldHotbarSlotAsAir(Player var1) {
      if (var1 != null) {
         int var2 = var1.getInventory().getHeldItemSlot();
         if (var2 >= 0 && var2 < 9) {
            ItemStack var3 = new ItemStack(Material.AIR);
            this.sendClientHotbarSlot(var1, var2, var3);
         }
      }
   }

   protected void restoreHeldHotbarSlotFromServerInventory(Player var1) {
      if (var1 != null) {
         int var2 = var1.getInventory().getHeldItemSlot();
         if (var2 >= 0 && var2 < 9) {
            ItemStack var3 = var1.getInventory().getItem(var2);
            this.sendClientHotbarSlot(var1, var2, var3);
         }
      }
   }

   protected void restoreClientHotbarFromServerInventory(Player var1) {
      if (var1 != null) {
         for (int var2 = 0; var2 < 9; var2++) {
            ItemStack var3 = var1.getInventory().getItem(var2);
            this.sendClientHotbarSlot(var1, var2, var3);
         }
      }
   }

   protected void sendClientHotbarSlot(Player var1, int var2, ItemStack var3) {
      if (var1 != null && var2 >= 0 && var2 < 9) {
         ItemStack var4 = var3 == null ? new ItemStack(Material.AIR) : var3.clone();
         com.github.retrooper.packetevents.protocol.item.ItemStack var5 = SpigotConversionUtil.fromBukkitItemStack(var4);
         int var6 = 36 + var2;
         WrapperPlayServerSetSlot var7 = new WrapperPlayServerSetSlot(0, 0, var6, var5);

         try {
            PacketEvents.getAPI().getPlayerManager().sendPacket(var1, var7);
         } catch (Throwable var9) {
            this.plugin
               .getLogger()
               .warning(String.format(Locale.ROOT, "[UltimateUI] Failed to send hotbar slot %d packet to %s: %s", var2, var1.getName(), var9.getMessage()));
         }
      }
   }

   public boolean handleScroll(Player var1, int var2) {
      EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
      if (var3 != null && var3.editMode && var3.previewMode && var2 != 0) {
         double var4 = var3.cursorX + var3.hitboxOffsetX;
         double var6 = var3.cursorY + var3.hitboxOffsetY;
         if (this.isEditorImageFilePopupVisible(var3)) {
            return this.handleEditorImageFilePopupScroll(var1, var3, var2, var4, var6);
         } else if (this.isAnyEditorPopupVisible(var3)) {
            return true;
         } else if (this.handleActivePagesScroll(var1, var3, var4, var6, var2)) {
            return true;
         } else if (var3.rightSidebarTab == EditorSidebarTab.PROPERTIES
            && this.isActionsSidebarMode(var3)
            && this.handleActionsScroll(var1, var3, var4, var6, var2)) {
            return true;
         } else if (var3.rightSidebarTab == EditorSidebarTab.LAYERS && this.handleLayersScroll(var1, var3, var4, var6, var2)) {
            return true;
         } else if (this.isAnyEditorDropdownVisible(var3)) {
            return true;
         } else {
            double var8 = Math.pow(1.12, (double)Math.abs(var2));
            if (var2 > 0) {
               var8 = 1.0 / var8;
            }

            double var10 = var4;
            double var12 = var6;
            Entity var14 = this.hudService.getHud(var1, "cursor");
            if (var14 != null) {
               Vector var15 = this.hudService.getHudLocation(var14);
               if (var15 != null) {
                  var10 = var15.getX() + var3.hitboxOffsetX;
                  var12 = var15.getY() + var3.hitboxOffsetY;
                  var3.cursorX = var15.getX();
                  var3.cursorY = var15.getY();
               }
            }

            boolean var16 = this.applyPreviewZoom(var3, var10, var12, var8);
            if (!var16) {
               return true;
            } else if (var3.activeHandle != null && var3.selectedElementId != null) {
               var3.resizeLastCursorX = this.toLogicalCursorX(var3, var10);
               var3.resizeLastCursorY = this.toLogicalCursorY(var3, var12);
               this.renderPreviewPageOutline(var1, var3);
               this.rerenderEditableContent(var1, var3);
               this.renderEditorTransparencyOverlay(var1, var3);
               this.updatePageInfoReadout(var1, var3);
               this.updateCursorPositionReadout(var1, var3, var10, var12);
               this.updateAlignToolbarOverlay(var1, var3, var10, var12, true);
               return true;
            } else {
               this.refreshPreviewProjection(var1, var3);
               this.updateCursorPositionReadout(var1, var3, var10, var12);
               this.updateAlignToolbarOverlay(var1, var3, var10, var12, true);
               return true;
            }
         }
      } else {
         return false;
      }
   }

   public boolean handleEditorMovementShortcut(Player var1, double var2, double var4, float var6) {
      if (var1 == null) {
         return false;
      } else {
         EditorSession var7 = this.editorSessions.get(var1.getUniqueId());
         if (var7 == null || !var7.editMode || !var7.previewMode) {
            return false;
         } else if (this.isAnyEditorDropdownVisible(var7)) {
            return false;
         } else if (var7.activeTool == EditorTool.COMMANDS) {
            return false;
         } else {
            double var8 = Math.abs(var2) + Math.abs(var4);
            if (var8 < 0.025) {
               return false;
            } else {
               double var10 = Math.toRadians((double)var6);
               double var12 = -Math.sin(var10);
               double var14 = Math.cos(var10);
               double var16 = Math.cos(var10);
               double var18 = Math.sin(var10);
               double var20 = var2 * var12 + var4 * var14;
               double var22 = var2 * var16 + var4 * var18;
               double var24 = 0.025;
               if (Math.abs(var20) >= Math.abs(var22)) {
                  if (var20 > var24) {
                     this.setActiveTool(var1, var7, EditorTool.CURSOR);
                     return true;
                  } else if (var20 < -var24) {
                     this.setActiveTool(var1, var7, EditorTool.SCALE);
                     return true;
                  } else {
                     return false;
                  }
               } else if (var22 > var24) {
                  this.setRightSidebarTab(var1, var7, EditorSidebarTab.LAYERS, true);
                  return true;
               } else if (var22 < -var24) {
                  this.setRightSidebarTab(var1, var7, EditorSidebarTab.PROPERTIES, true);
                  return true;
               } else {
                  return false;
               }
            }
         }
      }
   }

   public void handleRuntimeHudFollowMove(Player var1, double var2, double var4, double var6) {
      if (var1 != null) {
         if (Double.isFinite(var2) && Double.isFinite(var4) && Double.isFinite(var6)) {
            if (!(Math.abs(var2) + Math.abs(var4) + Math.abs(var6) <= 1.0E-5)) {
               EditorSession var8 = this.editorSessions.get(var1.getUniqueId());
               if (var8 != null && !var8.editMode && var8.hudTextOnlyMode) {
                  LinkedHashSet var9 = this.collectRuntimeHudFollowRuntimeIds(var8);
                  int var10 = this.resolveEditorHudTransitionTicks(var8);
                  if (var10 <= 0) {
                     var10 = Math.max(1, this.resolveEditorCursorInterpolationDurationTicks());
                  }

                  this.hudService.refreshRuntimeHudAnchoring(var1, new ArrayList<>(var9), new Vector(var2, var4, var6), var10, true);
               }
            }
         }
      }
   }

   public void handleRuntimeHudSneakToggle(Player var1, boolean var2) {
      if (var1 != null) {
         EditorSession var3 = this.editorSessions.get(var1.getUniqueId());
         if (var3 != null && !var3.editMode && var3.hudTextOnlyMode) {
            double var4 = var2 ? -0.08 : 0.0;
            this.applyRuntimeHudSneakVisualOffset(var1, var3, var4);
         }
      }
   }

   protected void syncRuntimeHudSneakVisualOffset(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && !var2.editMode && var2.hudTextOnlyMode) {
         double var3 = var1.isSneaking() ? -0.08 : 0.0;
         this.applyRuntimeHudSneakVisualOffset(var1, var2, var3);
      }
   }

   protected void applyRuntimeHudSneakVisualOffset(Player var1, EditorSession var2, double var3) {
      if (var1 != null && var2 != null) {
         double var5 = Double.isFinite(var2.runtimeHudSneakVisualOffsetY) ? var2.runtimeHudSneakVisualOffsetY : 0.0;
         double var7 = Double.isFinite(var3) ? var3 : 0.0;
         double var9 = var7 - var5;
         if (!(Math.abs(var9) <= 1.0E-4)) {
            LinkedHashSet var11 = this.collectRuntimeHudFollowRuntimeIds(var2);
            if (!var11.isEmpty()) {
               int var12 = this.resolveEditorHudTransitionTicks(var2);
               if (var12 <= 0) {
                  var12 = Math.max(1, this.resolveEditorCursorInterpolationDurationTicks());
               }

               boolean var13 = false;

               for (Object var15_raw : var11) {
                  String var15 = var15_raw != null ? var15_raw.toString() : null;
                  if (var15 != null && !var15.isBlank()) {
                     Entity var16 = this.hudService.getHud(var1, var15);
                     if (var16 != null) {
                        Vector var17 = this.hudService.getHudLocation(var16);
                        if (var17 != null) {
                           Vector var18 = var17.clone();
                           var18.setY(var18.getY() + var9);
                           this.hudService.moveHud(var16, var18, var12);
                           var13 = true;
                        }
                     }
                  }
               }

               if (var13) {
                  var2.runtimeHudSneakVisualOffsetY = var7;
               }
            }
         }
      }
   }

   protected LinkedHashSet<String> collectRuntimeHudFollowRuntimeIds(EditorSession var1) {
      LinkedHashSet var2 = new LinkedHashSet();
      if (var1 != null && var1.elements != null && !var1.elements.isEmpty()) {
         for (Object var4_raw : var1.elements) {
            HoverElement var4 = (HoverElement)var4_raw;
            if (var4 != null) {
               String var5 = this.firstNonBlank(new String[]{var4.id});
               if (!var5.isBlank()) {
                  this.appendRuntimeHudFollowIds(var2, var5);
               }
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   protected void appendRuntimeHudFollowIds(LinkedHashSet<String> var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         var1.add(var2);
         var1.add(var2 + "_outline");
         this.appendRoundedRuntimeHudFollowIds(var1, var2);
         String var3 = var2 + "_outline";
         this.appendRoundedRuntimeHudFollowIds(var1, var3);
      }
   }

   protected void appendRoundedRuntimeHudFollowIds(LinkedHashSet<String> var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         var1.add(var2 + "_r_core");
         var1.add(var2 + "_r_top");
         var1.add(var2 + "_r_bottom");
         var1.add(var2 + "_r_left");
         var1.add(var2 + "_r_right");
         var1.add(var2 + "_r_tl");
         var1.add(var2 + "_r_tr");
         var1.add(var2 + "_r_bl");
         var1.add(var2 + "_r_br");
      }
   }

   @Override
   protected void renderEditorTransparencyOverlay(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null && var2.previewMode) {
         double var3 = 202.0;
         double var5 = 1511.0;
         double var7 = 487.5;
         double var9 = 480.0;
         double var11 = -10000.0;
         short var13 = 255;
         double var14 = this.toRenderX(var2, var3);
         double var16 = this.toRenderY(var2, this.toInternalTextTopY(var5, var9));
         double var18 = Math.max(1.0, this.toRenderSize(var2, var7));
         double var20 = Math.max(1.0, this.toRenderSize(var2, var9));
         double var22 = this.toRuntimeLayer(var2, var11);
         TextAlignment var24 = TextAlignment.LEFT;
         double var25 = this.applyTextAlignmentOffset(var14, var18, var24);
         HudPositionCalculator.Placement var27 = this.positionCalculator.calculateBoxPlacement(var25, var16, var22, var18, var20);
         this.upsertHud(
            var1, "preview_content_editor_transparency", var27.location(), var27.scale(), this.ensureRoundedCornerEditorFont("<#ffffff>\ue1b2"), var13, var24
         );
      }
   }

   protected void loadPlayerColorPrefs(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         File var3 = this.resolveUserDataFolder(false);
         File var4 = new File(var3, var1.getUniqueId() + ".yml");
         if (!var4.exists()) {
            File var5 = this.resolveLegacyPlayerDataFile(var1);
            if (var5 == null || !var5.exists()) {
               return;
            }

            var4 = var5;
         }

         try {
            YamlConfiguration var9 = YamlConfiguration.loadConfiguration(var4);
            String var6 = this.firstNonBlank(new String[]{var9.getString("editorColor1"), var2.editorColor1});
            String var7 = this.firstNonBlank(new String[]{var9.getString("editorColor2"), var2.editorColor2});
            var6 = this.normalizeHexColor(var6);
            var7 = this.normalizeHexColor(var7);
            if (var6.length() == 6) {
               var2.editorColor1 = var6;
            }

            if (var7.length() == 6) {
               var2.editorColor2 = var7;
            }

            var2.displayHotbar = var9.getBoolean("preferences.displayHotbar", var9.getBoolean("displayHotbar", var2.displayHotbar));
            var2.displayHand = var9.getBoolean("preferences.displayHand", var9.getBoolean("displayHand", var2.displayHand));
            var2.optimizedEditor = var9.getBoolean("preferences.optimizedEditor", var9.getBoolean("optimizedEditor", var2.optimizedEditor));
            var2.editorSounds = var9.getBoolean("preferences.editorSounds", var9.getBoolean("editorSounds", var2.editorSounds));
            var2.welcomePopupAcknowledged = var9.getBoolean(
               "preferences.editorWelcomeSeen", var9.getBoolean("editorWelcomeSeen", var2.welcomePopupAcknowledged)
            );
         } catch (Exception var8) {
            this.plugin.getLogger().warning("[UltimateUI] Failed to load player color prefs for " + var1.getName() + ": " + var8.getMessage());
         }
      }
   }

   @Override
   protected void notifyColorPickerColorsChanged(Player var1, EditorSession var2) {
      this.savePlayerColorPrefs(var1, var2);
   }

   @Override
   protected void savePlayerColorPrefs(Player var1, EditorSession var2) {
      if (var1 != null && var2 != null) {
         try {
            File var3 = this.resolveUserDataFolder(true);
            if (!var3.exists()) {
               var3.mkdirs();
            }

            File var4 = new File(var3, var1.getUniqueId() + ".yml");
            YamlConfiguration var5 = var4.exists() ? YamlConfiguration.loadConfiguration(var4) : new YamlConfiguration();
            var5.set("editorColor1", var2.editorColor1);
            var5.set("editorColor2", var2.editorColor2);
            var5.set("preferences.displayHotbar", var2.displayHotbar);
            var5.set("preferences.displayHand", var2.displayHand);
            var5.set("preferences.optimizedEditor", var2.optimizedEditor);
            var5.set("preferences.editorSounds", var2.editorSounds);
            var5.set("preferences.editorWelcomeSeen", var2.welcomePopupAcknowledged);
            String var6 = var5.saveToString();
            Files.writeString(var4.toPath(), var6.replace("\n", System.lineSeparator()), StandardCharsets.UTF_8);
         } catch (Exception var7) {
            this.plugin.getLogger().warning("[UltimateUI] Failed to save player color prefs for " + var1.getName() + ": " + var7.getMessage());
         }
      }
   }

   protected File resolveUserDataFolder(boolean var1) {
      File var2 = new File(this.plugin.getDataFolder(), "userdata");
      if (var1 && !var2.exists()) {
         var2.mkdirs();
      }

      this.migrateLegacyUserDataFolder(var2);
      return var2;
   }

   protected File resolveLegacyPlayerDataFolder() {
      return new File(this.plugin.getDataFolder(), "player_data");
   }

   protected File resolveLegacyPlayerDataFile(Player var1) {
      if (var1 == null) {
         return null;
      } else {
         File var2 = this.resolveLegacyPlayerDataFolder();
         return new File(var2, var1.getUniqueId() + ".yml");
      }
   }

   protected void migrateLegacyUserDataFolder(File var1) {
      File var2 = this.resolveLegacyPlayerDataFolder();
      if (var2.exists() && var2.isDirectory()) {
         if (var1 != null && !var1.exists()) {
            var1.mkdirs();
         }

         if (var1 != null && var1.exists()) {
            File[] var3 = var2.listFiles((var0, var1x) -> var1x.toLowerCase(Locale.ROOT).endsWith(".yml"));
            if (var3 != null && var3.length != 0) {
               for (File var7 : var3) {
                  File var8 = new File(var1, var7.getName());
                  if (!var8.exists() && !var7.renameTo(var8)) {
                     try {
                        Files.copy(var7.toPath(), var8.toPath());
                     } catch (IOException var10) {
                        this.plugin.getLogger().warning("[UltimateUI] Failed to migrate player data file '" + var7.getName() + "': " + var10.getMessage());
                     }
                  }
               }
            }
         }
      }
   }

   public List<String> getKeepOpenPageKeys(Player var1) {
      if (var1 == null) {
         return Collections.emptyList();
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && !var2.editMode && var2.hudTextOnlyMode) {
            ArrayList var3 = new ArrayList();

            for (Map.Entry<?, ?> var5 : ((Map<?, ?>)var2.runtimePageKeepOpen).entrySet()) {
               if (Boolean.TRUE.equals(var5.getValue())) {
                  var3.add((String)var5.getKey());
               }
            }

            return var3;
         } else {
            return Collections.emptyList();
         }
      }
   }

   public List<String> getReopenPageKeys(Player var1) {
      if (var1 == null) {
         return Collections.emptyList();
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && !var2.editMode && var2.hudTextOnlyMode) {
            ArrayList var3 = new ArrayList();

            for (Map.Entry<?, ?> var5 : ((Map<?, ?>)var2.runtimePageReopen).entrySet()) {
               if (Boolean.TRUE.equals(var5.getValue())) {
                  var3.add((String)var5.getKey());
               }
            }

            return var3;
         } else {
            return Collections.emptyList();
         }
      }
   }

   public boolean isAnyPageCloseOnDeath(Player var1) {
      if (var1 == null) {
         return false;
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && !var2.editMode) {
            for (Boolean var4 : var2.runtimePageCloseOnDeath.values()) {
               if (Boolean.TRUE.equals(var4)) {
                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      }
   }

   public boolean isAnyPageCloseOnDamage(Player var1) {
      if (var1 == null) {
         return false;
      } else {
         EditorSession var2 = this.editorSessions.get(var1.getUniqueId());
         if (var2 != null && !var2.editMode) {
            for (Boolean var4 : var2.runtimePageCloseOnDamage.values()) {
               if (Boolean.TRUE.equals(var4)) {
                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      }
   }

   protected static final class ActivePagesDropTarget {
      private final int insertIndex;
      private final double markerX;
      private final double markerY;
      private final double markerHeight;

      private ActivePagesDropTarget(int var1, double var2, double var4, double var6) {
         this.insertIndex = var1;
         this.markerX = var2;
         this.markerY = var4;
         this.markerHeight = var6;
      }
   }
}
