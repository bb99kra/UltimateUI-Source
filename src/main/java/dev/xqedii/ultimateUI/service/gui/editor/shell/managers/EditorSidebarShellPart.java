package dev.xqedii.ultimateUI.service.gui.editor.shell.managers;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.Xqedii_170zfgx;
import dev.xqedii.ultimateUI.Xqedii_20ef81w;
import dev.xqedii.ultimateUI.Xqedii_25i7cuk;
import dev.xqedii.ultimateUI.Xqedii_2ze73vg;
import dev.xqedii.ultimateUI.Xqedii_38o2zes;
import dev.xqedii.ultimateUI.Xqedii_3ana5qf;
import dev.xqedii.ultimateUI.Xqedii_3cny9rh;
import dev.xqedii.ultimateUI.Xqedii_3gdqlgv;
import dev.xqedii.ultimateUI.Xqedii_3uxgwyg;
import dev.xqedii.ultimateUI.ColorPickerCoords;
import dev.xqedii.ultimateUI.gui.model.HoverElement;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.model.EditorPropertyField;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.gui.model.EditorSidebarTab;
import dev.xqedii.ultimateUI.service.gui.model.EditorTool;
import dev.xqedii.ultimateUI.service.gui.model.RoundedBox;
import dev.xqedii.ultimateUI.service.gui.model.RoundedCornerGlyphs;
import dev.xqedii.ultimateUI.service.gui.model.RoundedCornerOffsets;
import dev.xqedii.ultimateUI.service.gui.model.RoundedTransform;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.service.hud.HudPositionCalculator.Placement;
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
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.bukkit.util.Vector;

public abstract class EditorSidebarShellPart extends EditorPreviewSidebarPart {
   public static final String Xqedii_277x1bswdyipu = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";
   public final Xqedii_25i7cuk Xqedii_l6v4fuu = new Xqedii_25i7cuk();
   public static final String Xqedii_2d4a2at = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
      "IUkFZkPQUMYu1kThXQZOMSXAzHi/FGUBUukaWwyU0j8z16tKvy7UhwBXkOChL8sXmYEJFywZPElyjxchzpSxKLnlOaDilQ8q8gA1qZQoy8Af9Xdx"
   );
   public static final String Xqedii_38kvz4o = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
      "hbFONQjhgTPyJnxjXerm3pFWQoydu+8G+EMzegBMyu/h+YPFaxHQbU3Un/DtrmpLzb7cSRC4RSE="
   );
   public static final String Xqedii_3goopwt = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vW2jgyKWH9d1OmHJSZvO48q0/pRvyv3K4igrDtMeBFApQQ==");

   public EditorSidebarShellPart(UltimateUI var1, HudService var2, CameraService var3) {
      super(var1, var2, var3);
   }

   public abstract boolean isValidActionsSecondClickEditAttempt(EditorSession var1, double var2, double var4, long var6);

   public abstract boolean hasActionsEditMovementExceeded(EditorSession var1, int var2, double var3, double var5);

   public abstract boolean isActionsReorderDragStartSatisfied(EditorSession var1, double var2, double var4);

   public abstract void resetActionsEditClickTracker(EditorSession var1);

   public abstract boolean handleCursorActionsDropdownAction(Player var1, EditorSession var2, String var3);

   public abstract boolean handleCursorActionsEditDropdownAction(Player var1, EditorSession var2, String var3);

   public abstract boolean handleActionsEditCopy(EditorSession var1);

   public abstract boolean handleActionsEditCut(Player var1, EditorSession var2);

   public abstract boolean handleActionsEditPaste(Player var1, EditorSession var2);

   public abstract int resolveSelectedActionRawIndex(EditorSession var1, List<Map<String, Object>> var2);

   public abstract String resolveCursorActionsTypeByDropdownId(String var1);

   public abstract String resolveSelectedActionsTargetId(EditorSession var1);

   public abstract List<Map<String, Object>> resolveTargetActionsRawList(EditorSession var1, String var2, boolean var3);

   public abstract void normalizeActionMapForEditor(Map<String, Object> var1);

   public abstract Map<String, Object> buildDefaultActionMap(String var1);

   public abstract String normalizeActionType(String var1);

   public abstract String resolveDefaultActionValue(String var1);

   public abstract String resolveActionDisplayValue(Map<String, Object> var1, String var2);

   public abstract boolean isEditableActionType(String var1);

   public abstract void startSidebarActionInputPrompt(Player var1, EditorSession var2, String var3, int var4, String var5, String var6);

   public abstract void armActionsReorder(EditorSession var1, int var2, double var3, double var5);

   public abstract void clearActionsReorderState(EditorSession var1);

   public abstract boolean isActionsReorderLeftHeld(EditorSession var1);

   public abstract boolean isActionsReorderActive(EditorSession var1);

   public abstract boolean handleActionsReorderCursorMove(Player var1, EditorSession var2, double var3, double var5);

   public abstract void moveActionInRawList(List<Map<String, Object>> var1, int var2, int var3);

   public abstract boolean handleActionsPanelClick(Player var1, EditorSession var2, double var3, double var5);

   public abstract boolean handleActionsPanelRightClick(Player var1, EditorSession var2, double var3, double var5);

   public abstract boolean handleActionsScroll(Player var1, EditorSession var2, double var3, double var5, int var7);

   public abstract void flashActionsScrollEdge(Player var1, EditorSession var2, boolean var3);

   @Override
   public abstract boolean isActionsSidebarMode(EditorSession var1);

   public abstract boolean isActionsShellBlockId(String var1);

   public abstract boolean isCursorActionsCachedPanelBlock(EditorSession var1, ConfigurationSection var2);

   @Override
   public abstract void setCursorActionsEditDropdownVisible(Player var1, EditorSession var2, boolean var3);

   public abstract void updateCursorActionsEditDropdownHover(Player var1, EditorSession var2, double var3, double var5);

   public abstract void setCursorActionsEditDropdownItemVisual(Player var1, EditorSession var2, String var3, boolean var4);

   public abstract String resolveHoveredCursorActionsEditDropdownTargetId(EditorSession var1, double var2, double var4);

   public abstract void moveCursorActionsEditDropdownTo(Player var1, EditorSession var2, double var3, double var5);

   public abstract void moveCursorActionsDropdownTo(Player var1, EditorSession var2, double var3, double var5);

   @Override
   public abstract void setCursorActionsDropdownVisible(Player var1, EditorSession var2, boolean var3);

   public abstract void updateCursorActionsDropdownHover(Player var1, EditorSession var2, double var3, double var5);

   public abstract void setCursorActionsDropdownItemVisual(Player var1, EditorSession var2, String var3, boolean var4);

   public abstract String resolveHoveredCursorActionsDropdownTargetId(EditorSession var1, double var2, double var4);

   public abstract void renderActionsPanel(Player var1, EditorSession var2);

   public abstract void setActionsNewButtonVisible(Player var1, EditorSession var2, boolean var3);

   public abstract boolean isActionsPanelRuntimeAvailable(EditorSession var1);

   public abstract void renderActionsRuntimeRows(Player var1, EditorSession var2, List<EditorSidebarShellPart.ActionRowEntry> var3);

   public abstract List<Map<String, Object>> buildActionsRuntimeGridBlocks(EditorSession var1, List<EditorSidebarShellPart.ActionRowEntry> var2);

   public abstract double resolveActionsGridStartX(EditorSession var1);

   public abstract double resolveActionsGridStartY(EditorSession var1);

   public abstract double resolveActionsGridGap(EditorSession var1);

   public abstract Double resolveActionsGridElementHeight(EditorSession var1);

   public abstract Map<String, Object> findActionsGridBlockMap(EditorSession var1);

   public abstract EditorRect resolveActionsListRect(EditorSession var1, int var2);

   public abstract int resolveActionSlotByHit(EditorSession var1, double var2, double var4, int var6);

   public abstract double resolveActionsRowStep(EditorSession var1);

   public abstract void clearStaleActionRuntimeShellIds(Player var1, EditorSession var2, LinkedHashSet<String> var3);

   public abstract void removeActionRuntimeShellElement(Player var1, String var2);

   public abstract void clearActionsRuntimeHud(Player var1);

   public abstract void clearActionsRuntimeHud(Player var1, EditorSession var2);

   public abstract void renderActionsReorderGhostHud(Player var1, EditorSession var2, double var3, double var5);

   public abstract List<Map<String, Object>> buildActionsReorderGhostBlocks(
      EditorSession var1, EditorSidebarShellPart.ActionRowEntry var2, double var3, double var5
   );

   public abstract void clearActionsReorderGhostHud(Player var1);

   public abstract void clearActionsReorderGhostHud(Player var1, EditorSession var2);

   public abstract void clearStaleActionsReorderGhostShellIds(Player var1, EditorSession var2, LinkedHashSet<String> var3);

   public abstract String actionRuntimeLogicalId(String var1);

   public abstract String actionSlotBaseId(int var1);

   public abstract String actionSlotId(int var1);

   public abstract String actionNameId(int var1);

   public abstract String actionIconId(int var1);

   public abstract void syncDesignTextAlignControlsVisibility(Player var1, EditorSession var2, boolean var3);

   @Override
   public abstract void cycleSidebarHoverEffect(Player var1, EditorSession var2, boolean var3);

   @Override
   public abstract void cycleSidebarBorderRadiusMode(Player var1, EditorSession var2);

   @Override
   public abstract void updateEditorPropertiesSidebar(Player var1, EditorSession var2);

   @Override
   public abstract void updateEditorKeyframeSidebar(Player var1, EditorSession var2);

   @Override
   public abstract void updateEditorDesignSidebar(Player var1, EditorSession var2);

   @Override
   public boolean isInsideShellBlock(EditorSession var1, String var2, double var3, double var5) {
      if (((634936266 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         EditorRect var7 = this.findShellBlockRect(var1, var2);
         return var7 != null && var7.contains(var3, var5);
      }
   }

   public boolean isInsideEditorChromeBlock(EditorSession var1, double var2, double var4) {
      if ((1563265601 * 1563265601 - 1563265601 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 4) != 0);
      } else {
         return ((!this.isInsideShellBlock(
                  var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("XTrTYGlWkhqCMv27IhufU3iVPxOH1mrd9K3zNkXYgL/CChND2KnPjA=="), var2, var4
               )
               && !this.isInsideShellBlock(
                  var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("VKHOIW1r8UeT9DPTsYSzAdgLtlBQuGNmx50Nfjyzh8fdUuz9CvxQg86ocqwN1veMFIU90g=="), var2, var4
               )
               && !this.isInsideShellBlock(
                  var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Pr8Z2YvNQu0WAz1C/llWC3p7Rrl69+gK5zxY2DWX6zq4rMafS/FKgXS8LCuWVBt/noc="), var2, var4
               )
               && !this.isInsideShellBlock(
                  var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("3DOSGSr6/DWBD9eO7KbMZ1wq3yAFJD8DqRZbpGhYe2J6ju7+lrAy2yG4XBtDaDePFJYL5w=="), var2, var4
               )
               && !this.isInsideShellBlock(
                  var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("CJiPqlbitXVxk6499QyDktG//JOWTvJIaxqT1uAelUBDg5vPZ+tfCRMuEd13VllrxhOWLqz7"), var2, var4
               )
               && !this.isInsideShellBlock(
                  var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("yZaKwA5SBtBc8wzcraLlL9/PCsHVTR1ap6VSY+Vxef05aT2yxJ7KatU173rV3vwRfLtC7490JzeHvSEd9rg="), var2, var4
               )
               && !this.isInsideShellBlock(
                  var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LS5FDBAH9o1Gly8uyCMRz18locvp5848O1hQKQL3EsDSj/HNp6C1ciYdyT8D3fVZCElUbhZp"), var2, var4
               )
               && !this.isInsideShellBlock(
                  var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("cJmDcgmFyaCZR4sfPG6rWeJuVzE9xBpHm1jM4HfPWxPD8sNqmuEdno9ODFbBH1aGCfIVQ7J8"), var2, var4
               )
               && !this.isInsideShellBlock(
                  var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xFsQv+0+N2ClObG2XsA606zdP8snPsgvwhCuB6eOTGqQic3d8klJHUjFphuBuf1Oxcg0JQ=="), var2, var4
               )
               && !this.isInsideShellBlock(
                  var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("UgE4PMnp3atRdkYttcLut9QbmWTSURFSNpGa7gY6jv3ITNAkfm2SEjbBuaLJzaUT"), var2, var4
               )
               && !this.isInsideShellBlock(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nrDCb8u2IRVW7diturNy8OQL5M0gBLm2kAEXT9bUnzlOFP4aKFKJG6We"), var2, var4)
               && !this.isInsideShellBlock(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dLBTXhefcEPrcSXhmHJD/6fEh6cCmaJ3OfGFi+8wXVIIbIITPC91Jg=="), var2, var4)
               && !this.isInsideShellBlock(
                  var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Tmk6maJJiUdZL41WMUKwQLv8atBVVq1wG1xxoatmbe2RaVLPurbW6tU/ScEB6QIw2CQ="), var2, var4
               )
               && !this.isInsideShellBlock(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("o429iR3IMzWbbKoUvWl74/Goj1ew0Xzk6LJIkVibIUdWFyBKcTF4vA=="), var2, var4)
               && (
                  !var1.fileDropdownVisible
                     || !this.isInsideShellBlock(
                        var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("iWUpOOlpU4NuO1QayaunqZ9FEoRljhPAWreqwvhVqgUyDZDr5PCNQQZu0ebcMz5WKe8rF5xv"), var2, var4
                     )
               )
               && (
                  !var1.editDropdownVisible
                     || !this.isInsideShellBlock(
                        var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("kG6Th83fXVg483cPDjeTNFFZyEtjDPucbaGA6MJCWQrEzXoCflOUzff61cXD3YIluUhHEvsk"), var2, var4
                     )
               )
               && (
                  !var1.selectionDropdownVisible
                     || !this.isInsideShellBlock(
                        var1,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("UOp1DWK2/SGNxB4We6RVgCp3wVvQMeScBVLdivY41AGQ+0p2yNlDBB4O95UtKNhns3ufXnTls3tsU1EevBPt5A=="),
                        var2,
                        var4
                     )
               )
               && (
                  !var1.layerDropdownVisible
                     || !this.isInsideShellBlock(
                        var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("9epKzkLEl9sqUNEI8eLHGq6S+FMlftDELM4EtzrK0VJD1fmcinkqpvMzAotTL73xAkPSoLl/E8I="), var2, var4
                     )
               )
               && (
                  !var1.windowDropdownVisible
                     || !this.isInsideShellBlock(
                        var1,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("DK1RAmGfKVGQd+Hei+4Y38pv3PzLJVcGi3xcGL4aqZZchbHRirj/os72/NWMxAiuJsDXU8Ls5CRaqg=="),
                        var2,
                        var4
                     )
               )
               && (
                  !var1.keyframeDropdownVisible
                     || !this.isInsideShellBlock(
                        var1,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("WPhIvlGP9F3hHjOrNE9p+XtjyeacX13LMlNfLCdYpIIAaLka8puFxVJJ8VcSGzZxbtc5YG6vIAD9+5I2KLE="),
                        var2,
                        var4
                     )
               )
               && (
                  !var1.keyframeTimelineDropdownVisible
                     || !this.isInsideShellBlock(
                        var1,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                           "lJWtjd/SgnfKPbXiqubMEkSf1ZW3aeeuit1InTNzFUvvcCKiWzIBfWD50ow6XDG98delk+M/KHVY8z+EzhVOEHs3TpwrRSlbRCzHT+cOf+M="
                        ),
                        var2,
                        var4
                     )
               )
               && (
                  !var1.cursorPageDropdownVisible
                     || !this.isInsideShellBlock(
                        var1,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("pZnjcqJiibqKspZhZuFlO+OLl4/mv6SB8vsG4QOPRqskp2vwJyiEgElbDTioaZWEE2q3eaPY+BFqpG6xun7Edriq1BQ="),
                        var2,
                        var4
                     )
               )
               && (
                  !var1.cursorLayersDropdownVisible
                     || !this.isInsideShellBlock(
                        var1,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("x9JOLpSdGvuS78Od1OfQKPOUAdSviNrYYAooGJQclunUVMrpVvLPYWroqBkHVlkOaH3FPOpD/r7Lu9yGV+cc7YGbq7KVeUEN"),
                        var2,
                        var4
                     )
               )
               && (
                  !var1.cursorActionsDropdownVisible
                     || !this.isInsideShellBlock(
                        var1,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                           "xUMz3AQ5wfJPU57tgCaG/iVEdJtXXaI0eZlKTXLsNHh+djY4GFYHMgxD82kjH+C+U53WVe4I3MUwz/eP9c1mP6PMn5dI4NMSo9w="
                        ),
                        var2,
                        var4
                     )
               )
               && (
                  !var1.cursorActionsEditDropdownVisible
                     || !this.isInsideShellBlock(
                        var1,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                           "s90mMorE/n6mOHgB1UvX00BgZDA50P1/IkQFhbujYWODFvEjPH8+n+5kdV83BiOem72YM9RLDJMOs9jwgzHieJc40/v6A3slf1UE1SupdP/TWIRn"
                        ),
                        var2,
                        var4
                     )
               )
               && (
                  !var1.colorPickerVisible
                     || !this.isInsideShellBlock(
                        var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6+efmR6mabscwEG9NImK9ODLlHtEjA90Y8trT7cJKCP/quEWebPDViXjsqtzOJfp8KgJbaW5T30="), var2, var4
                     )
               )
               && (
                  !var1.welcomePopupVisible
                     || !this.isInsideShellBlock(
                        var1,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZBGeBy4SDJBlOH50wIJBTnXsa6NgLGjL1xwXuo8LoY3BCp7MwW08akFLZirBHXRGVi/4xumKT6zCQ1ivXYLYBw=="),
                        var2,
                        var4
                     )
               )
               && (
                  !var1.savePopupVisible
                     || !this.isInsideShellBlock(
                        var1,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nAcU4tL91QWYK+Uvu+T8r6MA3yhFh6qoOXxAV9iGowlZSaKTylOzWbl1fKjfDUd3WzNAtuFzqV5gbw=="),
                        var2,
                        var4
                     )
               )
               && (
                  !var1.preferencesPopupVisible
                     || !this.isInsideShellBlock(
                        var1,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("y/QA5wxIglExUTtdYVKOfNY5CAxznmUcouYtDcNitMGVRvLRjGtlsMBNRF9UBB1emizQ+XFxLoAti6Zq8J39FFxoWYpEL1CM"),
                        var2,
                        var4
                     )
               )
               && (
                  var1.activeTool != EditorTool.ANIMATION
                     || !this.isInsideShellBlock(
                        var1,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("zySxGqRMVxBqJbv7W8ssKIQESqau0NT1Vp8IoB+28fAvn3OwtNkzYlenMKWXtCtC//mCogggG5lfY4OHCBBM6Q=="),
                        var2,
                        var4
                     )
               )
               && !this.isInsideShellBlock(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("NG/uSq9Vr89XmzfJc47KHQlvwmW1CMbi8kW5Yr+Qr/4+kb6E+wjGCQ=="), var2, var4)
            ? Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 22)
            : Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 28)) != 0);
      }
   }

   public String shellSpawnOpacityRetoggleTargetId(String var1) {
      if ((1082000007 ^ 1082000007) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 2)];
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1968988613, 5)] = var1;
         String var2 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
         return var2.isBlank() ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==") : Xqedii_26jubhuuqlahz(var2);
      }
   }

   public boolean isShellSpawnOpacityRetoggleTargetId(String var1) {
      if ((657668503 * 657668503 + 657668503 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1679124054, 21)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 9)] = var1;
         String var2 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
         return ((!var2.isBlank()
               && var2.startsWith(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("uJqTmH12VkpCt6yV/Nzf6Ij9j5L9jFZpEn/3JzXbvzNYsRqoyms3oAVNW4tlEvZq"))
            ? Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-598401808, 0)
            : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1968988613, 5)) != 0);
      }
   }

   @Override
   public EditorRect findShellBlockRect(EditorSession var1, String var2) {
      if (((2121119067 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         if (var1 != null && var1.shellRuntimeRects != null) {
            EditorRect var3 = (EditorRect)var1.shellRuntimeRects.get(var2);
            if (var3 != null) {
               return var3;
            }
         }

         if (var1 != null && var2 != null && !var2.isBlank()) {
            this.ensureShellBlockCache(var1);
            EditorRect var4 = (EditorRect)var1.shellStaticRectCache.get(var2);
            if (var4 != null && var1.shellRuntimeRects != null) {
               var1.shellRuntimeRects.put(var2, var4);
            }

            return var4;
         } else {
            return null;
         }
      }
   }

   @Override
   public void ensureShellBlockCache(EditorSession var1) {
      if ((-431786459 * (-431786459 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.shellCacheBuilt) {
         var1.shellSectionCache.clear();
         var1.shellBlockMapCache.clear();
         var1.shellStaticRectCache.clear();
         var1.sidebarPanelVisibilityStates.clear();
         if (var1.shellBlocks != null && !var1.shellBlocks.isEmpty()) {
            HashMap var2 = new HashMap();
            int var3 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-410066264, 19);

            for (Map var5 : var1.shellBlocks) {
               var3++;
               ConfigurationSection var6 = this.mapToSection(var5);
               if (var6 != null) {
                  String var7 = this.resolveElementId(var6, var3, var2);
                  if (!var7.isBlank() && !var1.shellSectionCache.containsKey(var7)) {
                     var1.shellSectionCache.put(var7, var6);
                     var1.shellBlockMapCache.put(var7, var5);
                     double var8 = this.readDouble(
                        var6,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vKKayzv129fu+soeD6bfYdPwcE2kYsAjeOWIibaiwxa/r2AA1MZmuL+SyruKUhve"),
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dMemVQei62r6NkNu8BqxHTzcBg7yzM+XaIb3LIsc"),
                        Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699518298545L, 32))
                     );
                     double var10 = this.readDouble(
                        var6,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6l2WF3VPN37KDzXw/G9b5TL+6GD2Z7ti90KnCY4iv1P1TeFGrB4AUOB3jzUJXHwM"),
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xm8RisRfJosJsXxzE9Kmi51eIt5xrdF3Z/Mg7m1j"),
                        Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699518298545L, 40))
                     );
                     double var12 = Math.max(
                        Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-6779088726948702641L, 62)),
                        Math.abs(
                           this.readDouble(
                              var6,
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dD1V5iTP6Cs1T70nZx7JxW7+VGRP/INn/TyRub+RmwTrKSQrCShArllQuVCb8eP6"),
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("FPKgmoGqqfsf/TAZ7Kaoe8WTiOLLnI5xMvuM1lCli1sVmL5BN/Y="),
                              this.readDouble(
                                 var6,
                                 Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lErzeT8UmqIwoEIshJ7Fi16pNxEDG1a+KfX0vnDs6TaHlTzfg02UWVVtPRWM4P1OEu0="),
                                 Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("FPKgmoGqqfsf/TAZ7Kaoe8WTiOLLnI5xMvuM1lCli1sVmL5BN/Y="),
                                 Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(3717311297780793371L, 57))
                              )
                           )
                        )
                     );
                     double var14 = Math.max(
                        Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(8396584987801734170L, 61)),
                        Math.abs(
                           this.readDouble(
                              var6,
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GZNbFT1z5fLTz5m0iQOw7lZE3qkAFFk1rZ1X4zeaKsm9AlkyCXgpj7Bck6mfKr66AX4="),
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("CP4szv29CrqB0R19unEkQAr7B5MVCDB7QeO/jqmjBwdbqF4gfuQqjw=="),
                              this.readDouble(
                                 var6,
                                 Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZdWikxTMRP2zcCTxHiJM36vJxKMhxOMZKnTT9mzDzJ4GFejuYy6wyZRUTyGCY+QV/NvSjA=="),
                                 Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("CP4szv29CrqB0R19unEkQAr7B5MVCDB7QeO/jqmjBwdbqF4gfuQqjw=="),
                                 Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544840202L, 16))
                              )
                           )
                        )
                     );
                     var1.shellStaticRectCache.put(var7, new EditorRect(var8, var10, var12, var14));
                  }
               }
            }

            var1.shellCacheBuilt = (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1904710102, 15) != 0);
         } else {
            var1.shellCacheBuilt = (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681352278, 17) != 0);
         }
      }
   }

   @Override
   public ConfigurationSection findShellBlockSection(EditorSession var1, String var2) {
      if ((-1418488606 * -1418488606 - -1418488606 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         this.ensureShellBlockCache(var1);
         return (ConfigurationSection)var1.shellSectionCache.get(var2);
      } else {
         return null;
      }
   }

   public double resolveStoredSign(Map<String, Object> var1, String... var2) {
      if (((1619805349 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.length != 0) {
         String[] var3 = var2;
         int var4 = var2.length;

         for (int var5 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 29); var5 < var4; var5++) {
            String var6 = var3[var5];
            if (var6 != null && !var6.isBlank() && this.hasMapPath(var1, var6)) {
               double var7 = this.readMapPathDouble(var1, var6, Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(3207875267117500442L, 11)));
               if (Double.isFinite(var7) && Math.abs(var7) > Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-8945816301060122786L, 17))) {
                  return var7 < Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-3676526384308465658L, 55))
                     ? Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-5699651820714264481L, 36))
                     : Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-1058338435336324217L, 31));
               }
            }
         }

         return Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285511302170L, 27));
      } else {
         return Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285549087770L, 24));
      }
   }

   @Override
   public boolean hasMapPath(Map<String, Object> var1, String var2) {
      if ((1521636965 * 1521636965 + 1521636965 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return ((this.readMapPathValue(var1, var2) != null
            ? Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 7)
            : Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 16)) != 0);
      }
   }

   @Override
   public double readMapPathDouble(Map<String, Object> var1, String var2, double var3) {
      if ((1210403056 ^ 1210403056) != 0) {
         throw new IllegalStateException();
      } else {
         Object var5 = this.readMapPathValue(var1, var2);
         return this.parseDouble(var5, var3);
      }
   }

   @Override
   public Object readMapPathValue(Map<String, Object> var1, String var2) {
      if ((-33941927 * (-33941927 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         String[] var3 = Xqedii_3gdqlgv.Xqedii_1cqbfgv.computeIfAbsent(var2, var0 -> {
            if (((-1870936268 << 1 | 1) & 1) == 0) {
               throw new IllegalStateException();
            } else {
               return var0.split(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("DYfJV+YS5fTQ2lEzJcHsc9w4Spql3cnZUjjDT3QyOWA="));
            }
         });
         Object var4 = var1;
         String[] var5 = var3;
         int var6 = var3.length;

         for (int var7 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 1); var7 < var6; var7++) {
            String var8 = var5[var7];
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
   public double snapToGrid(EditorSession var1, double var2) {
      if (((1115047607 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return this.snap1(var2);
      }
   }

   public boolean isPreviewEmptyInfoShellBlock(String var1, ConfigurationSection var2) {
      if (((1482069373 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("2IE3rLNs6i7eVsF7emG9Uqhc1VlJg2QOGB2wRzJ03LHdW2SqdHdVyhesBckijFHq8UPCD+DksTKT0sr5").equals(var1)) {
         return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-675553426, 18) != 0);
      } else if (var2 == null) {
         return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-575705154, 11) != 0);
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 26)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 5)] = var2.getString(
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8L3tcEhVVFqLxabYTyk8caw0mwVPZNvm57Sm1TVaS/w=")
         );
         String var3 = this.firstNonBlank(var10001);
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("2IE3rLNs6i7eVsF7emG9Uqhc1VlJg2QOGB2wRzJ03LHdW2SqdHdVyhesBckijFHq8UPCD+DksTKT0sr5").equals(var3);
      }
   }

   public boolean shouldShowPreviewEmptyInfo(EditorSession var1) {
      if ((-102771052 * -102771052 + -102771052 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return ((!this.hasAnyPreviewPageBlocks(var1 == null ? null : var1.rawBlocks)
            ? Xqedii_2ze73vg.Xqedii_18u831nn38lo(1689609814, 23)
            : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1968988613, 5)) != 0);
      }
   }

   public boolean hasAnyPreviewPageBlocks(List<Map<String, Object>> var1) {
      if ((1917233928 * 1917233928 + 1917233928 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         for (Map var3 : var1) {
            if (var3 != null && !var3.isEmpty()) {
               return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1109460106, 21) != 0);
            }
         }

         return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 13) != 0);
      } else {
         return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 1) != 0);
      }
   }

   public boolean isShellCachedPanelBlock(EditorSession var1, ConfigurationSection var2) {
      if (((841862125 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return ((!this.isRightSidebarCachedPanelBlock(var1, var2)
               && !this.isCursorPageCachedPanelBlock(var1, var2)
               && !this.isCursorActionsCachedPanelBlock(var1, var2)
               && !this.isColorPickerCachedPanelBlock(var1, var2)
               && !this.isTopNavbarDropdownCachedPanelBlock(var1, var2)
               && !this.isWelcomePopupCachedPanelBlock(var1, var2)
               && !this.isSavePopupCachedPanelBlock(var1, var2)
               && !this.isPreferencesPopupCachedPanelBlock(var1, var2)
               && !this.isTimelineCachedPanelBlock(var2)
            ? Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-545408162, 28)
            : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1196803615, 1)) != 0);
      }
   }

   public boolean isTimelineCachedPanelBlock(ConfigurationSection var1) {
      if ((-1359705086 * -1359705086 - -1359705086 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-820132527, 20) != 0);
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1689609814, 23)];
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(713980142, 7)] = var1.getString(
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("kSak0NGp9a3iUDbWM46cF9uOrJkURi0/e2/40DWXlx59Gyo745YamD4IdJZU2eeeuf2tTsiQDwr/6MT+TBnnnRe9MQvof4JjCjc=")
         );
         String var2 = this.firstNonBlank(var10001);
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ghIh9CNN8HvfoZDrg1yGLAEOcO5Sf4q3zloV6aKq7UKkp3cL75kMz4iqM/kZ7RGvnWyR4RRInxtBz//dGflMUdrXYr6UvL+7eYF4OA==")
            .equalsIgnoreCase(var2);
      }
   }

   public boolean isPanelCachedBlock(EditorSession var1, ConfigurationSection var2, String var3) {
      if ((-1296661592 * -1296661592 + -1296661592 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-142873126, 23)];
         var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 8)] = var2.getString(
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
         );
         String var4 = this.firstNonBlank(var10001);
         ConfigurationSection var5 = this.findShellBlockSection(var1, var3);
         var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681223254, 11)];
         var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2113334651, 30)] = var5 == null
            ? null
            : var5.getString(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="));
         String var6 = this.firstNonBlank(var10001);
         if (!var6.isBlank() && this.belongsToSidebarPanel(var6, var4)) {
            return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 10) != 0);
         } else {
            String var7 = this.resolvePanelComponentNameFallback(var3);
            if (var7.isBlank()) {
               return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1439046728, 9) != 0);
            } else {
               var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 0)];
               var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 12)] = var2.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("kSak0NGp9a3iUDbWM46cF9uOrJkURi0/e2/40DWXlx59Gyo745YamD4IdJZU2eeeuf2tTsiQDwr/6MT+TBnnnRe9MQvof4JjCjc=")
               );
               String var8 = this.firstNonBlank(var10001);
               return ((!var8.isBlank() && var7.equalsIgnoreCase(var8)
                  ? Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1461221662, 11)
                  : Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 24)) != 0);
            }
         }
      } else {
         return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-984494307, 4) != 0);
      }
   }

   public boolean isTopNavbarDropdownCachedPanelBlock(EditorSession var1, ConfigurationSection var2) {
      if ((1331760583 * (1331760583 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return ((!this.isPanelCachedBlock(
                  var1, var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("iWUpOOlpU4NuO1QayaunqZ9FEoRljhPAWreqwvhVqgUyDZDr5PCNQQZu0ebcMz5WKe8rF5xv")
               )
               && !this.isPanelCachedBlock(
                  var1, var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("kG6Th83fXVg483cPDjeTNFFZyEtjDPucbaGA6MJCWQrEzXoCflOUzff61cXD3YIluUhHEvsk")
               )
               && !this.isPanelCachedBlock(
                  var1, var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("UOp1DWK2/SGNxB4We6RVgCp3wVvQMeScBVLdivY41AGQ+0p2yNlDBB4O95UtKNhns3ufXnTls3tsU1EevBPt5A==")
               )
               && !this.isPanelCachedBlock(
                  var1, var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("9epKzkLEl9sqUNEI8eLHGq6S+FMlftDELM4EtzrK0VJD1fmcinkqpvMzAotTL73xAkPSoLl/E8I=")
               )
               && !this.isPanelCachedBlock(
                  var1, var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("DK1RAmGfKVGQd+Hei+4Y38pv3PzLJVcGi3xcGL4aqZZchbHRirj/os72/NWMxAiuJsDXU8Ls5CRaqg==")
               )
            ? Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-575705154, 11)
            : Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221204, 1)) != 0);
      }
   }

   public boolean isColorPickerCachedPanelBlock(EditorSession var1, ConfigurationSection var2) {
      if (((1165561982 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return this.isPanelCachedBlock(
            var1, var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6+efmR6mabscwEG9NImK9ODLlHtEjA90Y8trT7cJKCP/quEWebPDViXjsqtzOJfp8KgJbaW5T30=")
         );
      }
   }

   public boolean isSavePopupCachedPanelBlock(EditorSession var1, ConfigurationSection var2) {
      if (((228806836 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return this.isPanelCachedBlock(
            var1, var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nAcU4tL91QWYK+Uvu+T8r6MA3yhFh6qoOXxAV9iGowlZSaKTylOzWbl1fKjfDUd3WzNAtuFzqV5gbw==")
         );
      }
   }

   public boolean isWelcomePopupCachedPanelBlock(EditorSession var1, ConfigurationSection var2) {
      if ((-1888253337 * -1888253337 - -1888253337 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.isPanelCachedBlock(
            var1, var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZBGeBy4SDJBlOH50wIJBTnXsa6NgLGjL1xwXuo8LoY3BCp7MwW08akFLZirBHXRGVi/4xumKT6zCQ1ivXYLYBw==")
         );
      }
   }

   public boolean isPreferencesPopupCachedPanelBlock(EditorSession var1, ConfigurationSection var2) {
      if ((-383900310 ^ -383900310) != 0) {
         throw new IllegalStateException();
      } else {
         return this.isPanelCachedBlock(
            var1, var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("y/QA5wxIglExUTtdYVKOfNY5CAxznmUcouYtDcNitMGVRvLRjGtlsMBNRF9UBB1emizQ+XFxLoAti6Zq8J39FFxoWYpEL1CM")
         );
      }
   }

   public boolean isCursorPageCachedPanelBlock(EditorSession var1, ConfigurationSection var2) {
      if ((2131373650 * 2131373650 + 2131373650 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1949656662, 28)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 2)] = var2.getString(
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
         );
         String var3 = this.firstNonBlank(var10001);
         if (var3.isBlank()) {
            return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-304417029, 7) != 0);
         } else {
            ConfigurationSection var4 = this.findShellBlockSection(
               var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("pZnjcqJiibqKspZhZuFlO+OLl4/mv6SB8vsG4QOPRqskp2vwJyiEgElbDTioaZWEE2q3eaPY+BFqpG6xun7Edriq1BQ=")
            );
            var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1689609814, 23)];
            var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1897748249, 27)] = var4 == null
               ? null
               : var4.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
               );
            String var5 = this.firstNonBlank(var10001);
            return ((!var5.isBlank() && this.belongsToSidebarPanel(var5, var3)
               ? Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 23)
               : Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-152208515, 6)) != 0);
         }
      } else {
         return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1439046728, 9) != 0);
      }
   }

   public boolean isRightSidebarCachedPanelBlock(EditorSession var1, ConfigurationSection var2) {
      if ((701931403 * 701931403 + 701931403 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 28)];
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1942450516, 18)] = var2.getString(
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
         );
         String var3 = this.firstNonBlank(var10001);
         if (var3.isBlank()) {
            return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 24) != 0);
         } else {
            ConfigurationSection var4 = this.findShellBlockSection(
               var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("UgE4PMnp3atRdkYttcLut9QbmWTSURFSNpGa7gY6jv3ITNAkfm2SEjbBuaLJzaUT")
            );
            var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1680959062, 18)];
            var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 0)] = var4 == null
               ? null
               : var4.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
               );
            String var5 = this.firstNonBlank(var10001);
            if (!var5.isBlank() && this.belongsToSidebarPanel(var5, var3)) {
               return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221202, 2) != 0);
            } else {
               String[] var10000 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-273191967, 1)];
               var10000[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 10)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "P0mummFeX5hU+GKirxsbWkDizX8HiN8BdGiqk53odp0uvcLNATb8kcLtG+UuTPFfHsmpfOxmsU+lbOH6+kLCHdY7"
               );
               var10000[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221204, 1)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "C3E7uC5v15XNR9R7JoecnF8RZw/D0LJu2fD7uUMgMri1uyHjajX6sxWdH+Pi+zsH4CR3fTj3l2iDKgxq6JUJF7Ub7nWgsuj9i5aWH88ZIyQ5jNS0"
               );
               var10000[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1107362954, 21)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "hDOd0CDDqHaE6jOXE4Z8iYuFNs5vcNYYMdACAcYYPYAhV/gCyU5fDjbbadqQtTP33gzWtlWVmmJSrxSOPKJjvsp4EZZnrEm08W1THB/VhQplVg=="
               );
               String[] var6 = var10000;
               String[] var7 = var6;
               int var8 = var6.length;

               for (int var9 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1151410307, 12); var9 < var8; var9++) {
                  String var10 = var7[var9];
                  ConfigurationSection var11 = this.findShellBlockSection(var1, var10);
                  var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(231035343, 28)];
                  var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 21)] = var11 == null
                     ? null
                     : var11.getString(
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
                     );
                  String var12 = this.firstNonBlank(var10001);
                  if (!var12.isBlank() && this.belongsToSidebarPanel(var12, var3)) {
                     return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(713980014, 7) != 0);
                  }
               }

               var10000 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(713980398, 7)];
               var10000[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 0)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "dLBTXhefcEPrcSXhmHJD/6fEh6cCmaJ3OfGFi+8wXVIIbIITPC91Jg=="
               );
               var10000[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681223254, 11)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "Tmk6maJJiUdZL41WMUKwQLv8atBVVq1wG1xxoatmbe2RaVLPurbW6tU/ScEB6QIw2CQ="
               );
               var7 = var10000;
               String[] var15 = var7;
               int var17 = var7.length;

               for (int var19 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-304417029, 7); var19 < var17; var19++) {
                  String var21 = var15[var19];
                  ConfigurationSection var23 = this.findShellBlockSection(var1, var21);
                  var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681220182, 10)];
                  var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 14)] = var23 == null
                     ? null
                     : var23.getString(
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
                     );
                  String var13 = this.firstNonBlank(var10001);
                  if (!var13.isBlank() && this.belongsToSidebarPanel(var13, var3)) {
                     return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-276972706, 28) != 0);
                  }
               }

               ConfigurationSection var16 = this.findShellBlockSection(
                  var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("o429iR3IMzWbbKoUvWl74/Goj1ew0Xzk6LJIkVibIUdWFyBKcTF4vA==")
               );
               var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1195128597, 14)];
               var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-948874125, 26)] = var16 == null
                  ? null
                  : var16.getString(
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
                  );
               String var18 = this.firstNonBlank(var10001);
               if (!var18.isBlank() && this.belongsToSidebarPanel(var18, var3)) {
                  return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1859632095, 10) != 0);
               } else {
                  ConfigurationSection var20 = this.findShellBlockSection(
                     var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nrDCb8u2IRVW7diturNy8OQL5M0gBLm2kAEXT9bUnzlOFP4aKFKJG6We")
                  );
                  var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 4)];
                  var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 29)] = var20 == null
                     ? null
                     : var20.getString(
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
                     );
                  String var22 = this.firstNonBlank(var10001);
                  return ((!var22.isBlank() && this.belongsToSidebarPanel(var22, var3)
                     ? Xqedii_2ze73vg.Xqedii_18u831nn38lo(1680172630, 20)
                     : Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 18)) != 0);
               }
            }
         }
      } else {
         return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(998941598, 29) != 0);
      }
   }

   public boolean shouldKeepShellPanelSpawned(String var1) {
      if (((1005335443 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 18) != 0);
      }
   }

   public String resolvePanelComponentNameFallback(String var1) {
      if ((1973363644 ^ 1973363644) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isBlank()) {
         if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nAcU4tL91QWYK+Uvu+T8r6MA3yhFh6qoOXxAV9iGowlZSaKTylOzWbl1fKjfDUd3WzNAtuFzqV5gbw==").equals(var1)) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("fynjfGUCcNiNbQrQkwETZEjNJpQB/T4zMsRA1JGg4MZrR0lqwDmcBAzBbIpOCQUVbpU6QKfyTiUtyDtdNHc=");
         } else if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("y/QA5wxIglExUTtdYVKOfNY5CAxznmUcouYtDcNitMGVRvLRjGtlsMBNRF9UBB1emizQ+XFxLoAti6Zq8J39FFxoWYpEL1CM")
            .equals(var1)) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "zpXK27Ld2Ku/uHS/UUsA5Ci4wBh/gIysslJLruwJ4FsYZRzhChjJIGcBL+qtGsXe3d1D5qfn3kLrJActyUvi1yXHMKq2BSyOgLH8rg=="
            );
         } else {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZBGeBy4SDJBlOH50wIJBTnXsa6NgLGjL1xwXuo8LoY3BCp7MwW08akFLZirBHXRGVi/4xumKT6zCQ1ivXYLYBw==").equals(var1)
               ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("rqxNI8akPAby/wWAHVDKl+b69w47uEAOcUtmMO4MPiUAegTREJMHJOROD9Td184GZQ9HcPmOCa3GohD2h/wKRK9sG08=")
               : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
         }
      } else {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      }
   }

   @Override
   public boolean belongsToSidebarPanel(String var1, String var2) {
      if (((-114792535 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isBlank() && var2 != null && !var2.isBlank()) {
         return ((!var2.equals(var1) && !var2.startsWith(Xqedii_1z7vetggpafzc(var1)) && !var2.startsWith(Xqedii_ix38tvi4728g(var1))
            ? Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 5)
            : Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 11)) != 0);
      } else {
         return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 6) != 0);
      }
   }

   @Override
   public void moveShellElement(Player var1, EditorSession var2, String var3, double var4, double var6, double var8, double var10) {
      if ((-1900403062 * -1900403062 + -1900403062 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         ConfigurationSection var12 = this.findShellBlockSection(var2, var3);
         if (var12 != null) {
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1036341711, 28)];
            var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1372528069, 12)] = var12.getString(
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("wnj7uF3hcOoAks+8+3wFksoVbSbHn47JPMeDyYMSgiPoAf2O")
            );
            var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221202, 2)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "phMhQ5z6/nUG7x9rIjeqZ9DFFsF7Num7Ht8iWfSlIm4ZUFO5Sic="
            );
            String var13 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
            double var14 = this.readDouble(
               var12,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("p0bKKg1zr+Q4vpPJ9KjRLcwqYACILFzzF/R6St5o6lwHP51RRYE="),
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("p0bKKg1zr+Q4vpPJ9KjRLcwqYACILFzzF/R6St5o6lwHP51RRYE="),
               this.readDouble(
                  var12,
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Ih2CJAtB1fwFkJFw79T3T4TtLXyOpSiUw/W6FBg5ChxZS4iqL6/K+4122C0MReDT"),
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("yQ7PCHPzn8xjeFfnHE0oVP5u4w1vD+sj0OA61xSAE3FhIPv2J0E="),
                  Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544889370L, 3))
               )
            );
            double var16 = this.readDouble(
               var12,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dD1V5iTP6Cs1T70nZx7JxW7+VGRP/INn/TyRub+RmwTrKSQrCShArllQuVCb8eP6"),
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("FPKgmoGqqfsf/TAZ7Kaoe8WTiOLLnI5xMvuM1lCli1sVmL5BN/Y="),
               var8
            );
            double var18 = this.readDouble(
               var12,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GZNbFT1z5fLTz5m0iQOw7lZE3qkAFFk1rZ1X4zeaKsm9AlkyCXgpj7Bck6mfKr66AX4="),
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("CP4szv29CrqB0R19unEkQAr7B5MVCDB7QeO/jqmjBwdbqF4gfuQqjw=="),
               var10
            );
            int var20 = var16 < Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544889370L, 2))
               ? Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221142, 6)
               : Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 4);
            int var21 = var18 < Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(6756517266481118509L, 14))
               ? Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 10)
               : Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 13);
            double var22 = this.readDouble(
               var12,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("N9hWuxfafgycFZhRTf5tv+Fmz6rj6W0fWy/6yRy1v5qdFcaOH/RQXL6nFjk="),
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("QjsgMwj6B9RrJgbvgc4YY8K6Z7Rz5+bTWheudMklIRkeQg92yr5bCw=="),
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544889370L, 48))
            );
            double var24 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var13)
               ? Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544889370L, 25))
               : this.readOutlineSize(var12);
            String var26 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var13)
               ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==")
               : this.readOutlineColor(var12);
            String var27 = null;
            if (!Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var13)) {
               var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1731552854, 24)];
               var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(485612629, 16)] = var12.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("BrdgWIszSU3U/XFEaOSUGc2PW1tRG9lV9bA74jsfQ5hSzR7pFRHtM79t")
               );
               var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1680959062, 18)] = var12.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8")
               );
               var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855682, 9)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("TCFgWP1KbdJkq2C4EKLfW6R7wsu7d7bxgC5pGJg1");
               String var28 = this.firstNonBlank(var10001);
               var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830838786, 14)];
               var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-575705154, 11)] = var12.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SXnbKYNGrvJiwTC16d7CCneH8Zgjcjc0A8yLx/7xI0FG0Tc/Fws=")
               );
               var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1092767881, 3)] = var12.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Tz330h7gwv5vcG1MWRF2ngkGZFlHwxIvEnBSVPWE4ks8fk5KtG6eKmD7oj3tu8tP41I=")
               );
               String var29 = this.firstNonBlank(var10001);
               String var30 = this.applyPlaceholderApi(var1, this.withHexPrefix(var28, var29));
               var30 = this.applyPreferredFont(var30, var12, (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 17) != 0));
               if (var24 > Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-1861482555852134808L, 32))) {
                  var27 = this.applyOutlineColor(var30, var26);
               }
            }

            if (this.isRoundedType(var13)) {
               boolean var61 = this.isDirectionalRoundedType(var13);
               int var63 = this.readOpacity(var12, Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-305835584, 28));
               var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855202, 4)];
               var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-302523467, 24)] = var12.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("BrdgWIszSU3U/XFEaOSUGc2PW1tRG9lV9bA74jsfQ5hSzR7pFRHtM79t")
               );
               var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 29)] = var12.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8")
               );
               var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221202, 1)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("TCFgWP1KbdJkq2C4EKLfW6R7wsu7d7bxgC5pGJg1");
               String var66 = this.firstNonBlank(var10001);
               var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830822402, 15)];
               var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-136595987, 0)] = var12.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SXnbKYNGrvJiwTC16d7CCneH8Zgjcjc0A8yLx/7xI0FG0Tc/Fws=")
               );
               var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-621315091, 15)] = var12.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Tz330h7gwv5vcG1MWRF2ngkGZFlHwxIvEnBSVPWE4ks8fk5KtG6eKmD7oj3tu8tP41I=")
               );
               String var31 = this.firstNonBlank(var10001);
               String var67 = this.applyPlaceholderApi(var1, this.withHexPrefix(var66, var31));
               var67 = this.applyPreferredFont(var67, var12, (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1144350294, 29) != 0));
               double var33 = this.readRoundedRadius(var12, var8, var10);
               String var69 = this.readRoundedCornerUnicode(var12);
               String var70 = var61
                  ? this.readRoundedCornerUnicodeTopLeft(var12)
                  : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
               String var71 = var61
                  ? this.readRoundedCornerUnicodeTopRight(var12)
                  : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
               String var38 = var61
                  ? this.readRoundedCornerUnicodeBottomRight(var12)
                  : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
               String var72 = var61
                  ? this.readRoundedCornerUnicodeBottomLeft(var12)
                  : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
               double var40 = this.readRoundedTopLeftOffsetX(var12);
               double var42 = this.readRoundedTopLeftOffsetY(var12);
               double var44 = this.readRoundedTopRightOffsetX(var12);
               double var46 = this.readRoundedTopRightOffsetY(var12);
               double var48 = this.readRoundedBottomRightOffsetX(var12);
               double var50 = this.readRoundedBottomRightOffsetY(var12);
               double var52 = this.readRoundedBottomLeftOffsetX(var12);
               double var54 = this.readRoundedBottomLeftOffsetY(var12);
               double var56 = var4
                  + Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(122504736642563156L, 45)), var8)
                     / Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-178114120470538302L, 31));
               double var58 = var6
                  + Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-5532275566922935295L, 53)), var10)
                     / Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-8207855708731992497L, 63));
               this.renderRoundedOutline(
                  var1,
                  new RoundedBox(
                     Xqedii_ilptmb6y2aiy(var3),
                     var4,
                     var6,
                     var14,
                     Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-9208058944774455414L, 42)), var8),
                     Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819702160472198170L, 49)), var10)
                  ),
                  var27,
                  var63,
                  var33,
                  new RoundedCornerGlyphs(var69, var70, var71, var38, var72),
                  new RoundedCornerOffsets(var40, var42, var44, var46, var48, var50, var52, var54),
                  new RoundedTransform(var22, (var20 != 0), (var21 != 0), var56, var58),
                  var24,
                  TextAlignment.CENTER,
                  Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-8408934019120493159L, 0))
               );
               this.renderRoundedBlock(
                  var1,
                  new RoundedBox(
                     Xqedii_1nxr69lv8zjrk(var3),
                     var4,
                     var6,
                     var14,
                     Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012771056347569L, 39)), var8),
                     Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5915242023423699377L, 60)), var10)
                  ),
                  var67,
                  var63,
                  var33,
                  new RoundedCornerGlyphs(var69, var70, var71, var38, var72),
                  new RoundedCornerOffsets(var40, var42, var44, var46, var48, var50, var52, var54),
                  new RoundedTransform(var22, (var20 != 0), (var21 != 0), var56, var58),
                  TextAlignment.CENTER
               );
               var2.shellRuntimeRects
                  .put(
                     var3,
                     new EditorRect(
                        var4,
                        var6,
                        Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(7840303145124041984L, 51)), var8),
                        Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-183103664901889430L, 3)), var10)
                     )
                  );
            } else {
               TextAlignment var60 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var13)
                  ? this.readTextAlignment(var12)
                  : TextAlignment.CENTER;
               int var62 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("r90V0RFIMf5iidoh4OODRrWi2QSLYudTZptR4qXllAkYQe2BcMn07bnnMiPNrdMhBUBItpfOgO4=").equals(var3)
                     && Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var13)
                  ? Xqedii_2ze73vg.Xqedii_18u831nn38lo(-466262442, 31)
                  : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-474437063, 25);
               double var65 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var13) && var62 == 0
                  ? this.toInternalTextTopY(var6, var10)
                  : var6;
               double var32 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var13)
                  ? this.applyTextAlignmentOffset(var4, var8, var60)
                  : var4;
               Placement var34 = this.positionCalculator
                  .calculateBoxPlacement(
                     var32,
                     var65,
                     var14,
                     Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699520143793L, 23)), var8),
                     Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(3901999466588339591L, 47)), var10)
                  );
               Entity var35 = this.hudService.getHud(var1, Xqedii_3k161uslz21ln(var3));
               if (var35 == null) {
                  if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ekGHv3Apflq5i4KuttMa5RzRKWdKp+P89TX0lFMatimR3CqLCunVGg==").equals(var13)) {
                     var2.shellRuntimeRects
                        .put(
                           var3,
                           new EditorRect(
                              var4,
                              var6,
                              Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-6055698893950038647L, 61)), var8),
                              Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7855554898191764506L, 57)), var10)
                           )
                        );
                  }
               } else {
                  int var36 = this.resolveEditorHudTransitionTicks(var2);
                  this.hudService.moveHud(var35, var34.location(), var36, var36);
                  this.hudService.setHudScale(var35, var34.scale(), var36, (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1897748249, 27) != 0));
                  if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var13)) {
                     this.hudService.setTextAlignment(var35, var60);
                     this.clearOutlineHud(var1, Xqedii_2wcy2znnhoo2u(var3));
                  } else {
                     double var37 = var32
                        + Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(1960075786281010496L, 49)), var8)
                           / Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(7709588050855621474L, 59));
                     double var39 = var65
                        + Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-1288094089279727990L, 17)), var10)
                           / Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(4578612942317213148L, 26));
                     this.renderSimpleOutline(
                        var1,
                        new RoundedBox(
                           Xqedii_1lg829djassmg(var3),
                           var32,
                           var65,
                           var14,
                           Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544901662L, 14)), var8),
                           Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699518048689L, 24)), var10)
                        ),
                        var27,
                        this.readOpacity(var12, Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1223342333, 30)),
                        TextAlignment.CENTER,
                        var60,
                        new RoundedTransform(var22, (var20 != 0), (var21 != 0), var37, var39),
                        var24,
                        Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5904173789622692273L, 52))
                     );
                  }

                  this.applyElementTransform(var35, var22, (var20 != 0), (var21 != 0));
                  var2.shellRuntimeRects
                     .put(
                        var3,
                        new EditorRect(
                           var4,
                           var6,
                           Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819556883203410970L, 44)), var8),
                           Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561284739320858L, 30)), var10)
                        )
                     );
               }
            }
         }
      }
   }

   @Override
   public void setShellText(Player var1, String var2, String var3) {
      if ((-36391746 ^ -36391746) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         EditorSession var4 = (EditorSession)this.editorSessions.get(var1.getUniqueId());
         ConfigurationSection var5 = this.findShellBlockSection(var4, var2);
         String var6 = var3 == null ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==") : var3;
         if (var5 != null && !var6.isBlank() && !var6.contains(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("4bu5sBVsok/jNrgzZJnrnFGcUW7Sa+K4WaIdex0kPPU="))) {
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221204, 0)];
            var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-136595987, 0)] = var5.getString(
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SXnbKYNGrvJiwTC16d7CCneH8Zgjcjc0A8yLx/7xI0FG0Tc/Fws=")
            );
            var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-507991495, 25)] = var5.getString(
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Tz330h7gwv5vcG1MWRF2ngkGZFlHwxIvEnBSVPWE4ks8fk5KtG6eKmD7oj3tu8tP41I=")
            );
            String var7 = this.firstNonBlank(var10001);
            if (!var7.isBlank()) {
               var6 = this.withHexPrefix(var6, var7);
            }
         }

         int var10000;
         label37: {
            if (var5 != null) {
               String[] var11 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-276972706, 28)];
               var11[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 25)] = var5.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("BrdgWIszSU3U/XFEaOSUGc2PW1tRG9lV9bA74jsfQ5hSzR7pFRHtM79t")
               );
               if (!this.firstNonBlank(var11).isBlank()) {
                  var10000 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 26);
                  break label37;
               }
            }

            var10000 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1111557258, 21);
         }

         int var10 = var10000;
         String var8 = this.applyPreferredFont(var6, var5, (var10 != 0));
         String var9 = Xqedii_2056zd14ezcb0(var2);
         if (this.hudService.getHud(var1, var9) == null) {
            this.cacheShellTextForDeferredSpawn(var4, var2, var6);
         }

         this.setHudTextIfExists(var1, var9, var8);
      }
   }

   @Override
   public void cacheShellTextForDeferredSpawn(EditorSession var1, String var2, String var3) {
      if ((-589891292 * (-589891292 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank() && var1.shellBlocks != null && !var1.shellBlocks.isEmpty()) {
         this.ensureShellBlockCache(var1);
         ConfigurationSection var4 = (ConfigurationSection)var1.shellSectionCache.get(var2);
         if (var4 != null) {
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1680172630, 19)];
            var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 3)] = var4.getString(
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("wnj7uF3hcOoAks+8+3wFksoVbSbHn47JPMeDyYMSgiPoAf2O")
            );
            var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1107890821, 30)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "phMhQ5z6/nUG7x9rIjeqZ9DFFsF7Num7Ht8iWfSlIm4ZUFO5Sic="
            );
            String var5 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
            if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var5)) {
               String var6 = var3 == null ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==") : var3;
               Map var7 = (Map)var1.shellBlockMapCache.get(var2);
               if (var7 != null) {
                  var7.put(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8"), var6);
               }

               var4.set(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8"), var6);
            }
         }
      }
   }

   @Override
   public void setShellColor(Player var1, EditorSession var2, String var3, String var4) {
      if ((1053926157 * (1053926157 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         ConfigurationSection var5 = this.findShellBlockSection(var2, var3);
         if (var5 != null) {
            String var6 = Xqedii_387vn7cd3s7zq(var3);
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1022825799, 22)];
            var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 17)] = var4;
            var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1859632095, 10)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "C+852nfKg74J9IxxMl6Vi+EbG1EnpZVNBgPAGWI4a4FtcTSQ4F7jvA=="
            );
            String var7 = this.firstNonBlank(var10001);
            var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1748330070, 26)];
            var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1196803613, 1)] = var5.getString(
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("BrdgWIszSU3U/XFEaOSUGc2PW1tRG9lV9bA74jsfQ5hSzR7pFRHtM79t")
            );
            var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1685415510, 22)] = var5.getString(
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8")
            );
            var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1904611798, 15)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("TCFgWP1KbdJkq2C4EKLfW6R7wsu7d7bxgC5pGJg1");
            String var8 = this.firstNonBlank(var10001);
            String var9 = this.applyPreferredFont(this.withHexPrefix(var8, var7), var5, (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1142985001, 26) != 0));
            var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1416875889, 10)];
            var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1242695717, 16)] = var5.getString(
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("wnj7uF3hcOoAks+8+3wFksoVbSbHn47JPMeDyYMSgiPoAf2O")
            );
            var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 2)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "phMhQ5z6/nUG7x9rIjeqZ9DFFsF7Num7Ht8iWfSlIm4ZUFO5Sic="
            );
            String var10 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
            if (!this.isRoundedType(var10)) {
               this.setHudTextIfExists(var1, var6, var9);
            } else {
               this.setHudTextIfExists(var1, var6, var9);
               this.setHudTextIfExists(var1, Xqedii_3a3tsku0ra70q(var6), var9);
               this.setHudTextIfExists(var1, Xqedii_1zhg4h0mcm30c(var6), var9);
               this.setHudTextIfExists(var1, Xqedii_1ceewn3wqdkjs(var6), var9);
               this.setHudTextIfExists(var1, Xqedii_3gj4irfx1d7sy(var6), var9);
               this.setHudTextIfExists(var1, Xqedii_2k7nlhme0jbl5(var6), var9);
               boolean var11 = this.isDirectionalRoundedType(var10);
               var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-608833801, 8)];
               var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1195144981, 14)] = this.readRoundedCornerUnicode(var5);
               String var12 = this.firstNonBlank(var10001)
                  .replaceAll(
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SoSIVpoN7hN6dmq8JUS0Bvdxn899QfrJscZsso9IPt+W1sf104dC/MHGdTFC8/kVUMo0pMTRcrhF7llHwd4="),
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==")
                  );
               String var10000;
               if (var11) {
                  var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 20)];
                  var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 19)] = this.readRoundedCornerUnicodeTopLeft(var5);
                  var10000 = this.firstNonBlank(var10001)
                     .replaceAll(
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SoSIVpoN7hN6dmq8JUS0Bvdxn899QfrJscZsso9IPt+W1sf104dC/MHGdTFC8/kVUMo0pMTRcrhF7llHwd4="),
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==")
                     );
               } else {
                  var10000 = var12;
               }

               String var13 = var10000;
               if (var11) {
                  var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1949656662, 28)];
                  var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-76104258, 5)] = this.readRoundedCornerUnicodeTopRight(var5);
                  var10000 = this.firstNonBlank(var10001)
                     .replaceAll(
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SoSIVpoN7hN6dmq8JUS0Bvdxn899QfrJscZsso9IPt+W1sf104dC/MHGdTFC8/kVUMo0pMTRcrhF7llHwd4="),
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==")
                     );
               } else {
                  var10000 = var12;
               }

               String var14 = var10000;
               if (var11) {
                  var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1107890821, 30)];
                  var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1874779567, 27)] = this.readRoundedCornerUnicodeBottomLeft(var5);
                  var10000 = this.firstNonBlank(var10001)
                     .replaceAll(
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SoSIVpoN7hN6dmq8JUS0Bvdxn899QfrJscZsso9IPt+W1sf104dC/MHGdTFC8/kVUMo0pMTRcrhF7llHwd4="),
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==")
                     );
               } else {
                  var10000 = var12;
               }

               String var15 = var10000;
               if (var11) {
                  var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-507991495, 25)];
                  var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 23)] = this.readRoundedCornerUnicodeBottomRight(var5);
                  var10000 = this.firstNonBlank(var10001)
                     .replaceAll(
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SoSIVpoN7hN6dmq8JUS0Bvdxn899QfrJscZsso9IPt+W1sf104dC/MHGdTFC8/kVUMo0pMTRcrhF7llHwd4="),
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==")
                     );
               } else {
                  var10000 = var12;
               }

               String var16 = var10000;
               String[] var10003 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1350058275, 19)];
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 26)] = var13;
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-304416901, 7)] = var12;
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1949656662, 27)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lbA1TdrcUErDDrIQT2jgS2YK8HL/IBec1yzhvXQY");
               String var17 = this.applyPreferredFont(
                  this.withHexPrefix(this.firstNonBlank(var10003), var7), var5, (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 8) != 0)
               );
               var10003 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221198, 3)];
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-984494307, 4)] = var14;
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(485547093, 16)] = var12;
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1416875889, 10)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("DqUf9AtL/yoXOkpXkOKpWLsapRsMxPvZo/IALDlZ");
               String var18 = this.applyPreferredFont(
                  this.withHexPrefix(this.firstNonBlank(var10003), var7), var5, (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 27) != 0)
               );
               var10003 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681220950, 8)];
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2071852781, 22)] = var15;
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1439047240, 9)] = var12;
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1416875889, 10)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("2+6CDW0v24bikPmUTwB8hXjo5aUoJyiw2He6WEGK");
               String var19 = this.applyPreferredFont(
                  this.withHexPrefix(this.firstNonBlank(var10003), var7), var5, (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1372523973, 12) != 0)
               );
               var10003 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221200, 1)];
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-675815570, 18)] = var16;
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1942188372, 18)] = var12;
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681352278, 16)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vhI37+a93fagokloE5VprLrfAxWs2GM6R1dYkql0");
               String var20 = this.applyPreferredFont(
                  this.withHexPrefix(this.firstNonBlank(var10003), var7), var5, (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-152208451, 6) != 0)
               );
               this.setHudTextIfExists(var1, Xqedii_1ctosa0pdohhi(var6), var17);
               this.setHudTextIfExists(var1, Xqedii_37yr1m6slqlte(var6), var18);
               this.setHudTextIfExists(var1, Xqedii_33iuq7ev3z5a(var6), var19);
               this.setHudTextIfExists(var1, Xqedii_1sybz57ajtv3p(var6), var20);
            }
         }
      }
   }

   @Override
   public void setShellOpacity(Player var1, String var2, int var3) {
      if ((-1563932702 * -1563932702 + -1563932702 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         int var4 = Math.max(Xqedii_2ze73vg.Xqedii_18u831nn38lo(1684366934, 20), Math.min(Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681222054, 4), var3));
         EditorSession var5 = (EditorSession)this.editorSessions.get(var1.getUniqueId());
         if (var5 != null && var5.shellOpacityBatchDepth > 0) {
            var5.pendingShellOpacity.put(var2, var4);
         } else {
            this.applyShellOpacityNow(var1, var2, var4);
         }
      }
   }

   @Override
   public void applyShellOpacityNow(Player var1, String var2, int var3) {
      if (((500693638 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         String var4 = Xqedii_1nx09v5sg2cb6(var2);
         String var5 = Xqedii_2niwlc6zofezy(var4);
         int var6 = Math.max(Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855202, 3), Math.min(Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(838522882, 15), var3));
         this.setHudOpacityIfExists(var1, var4, var6);
         this.setHudOpacityIfExists(var1, Xqedii_2fb6tujftcq0c(var4), var6);
         this.setHudOpacityIfExists(var1, Xqedii_ye5r3nmtqizq(var4), var6);
         this.setHudOpacityIfExists(var1, Xqedii_33hnavf59d2iv(var4), var6);
         this.setHudOpacityIfExists(var1, Xqedii_3ffor1ag6xckp(var4), var6);
         this.setHudOpacityIfExists(var1, Xqedii_3ju7f0dq6j77q(var4), var6);
         this.setHudOpacityIfExists(var1, Xqedii_1f603i66pcac6(var4), var6);
         this.setHudOpacityIfExists(var1, Xqedii_2sg7k1awoi9hl(var4), var6);
         this.setHudOpacityIfExists(var1, Xqedii_3akjfzx7hrhnz(var4), var6);
         this.setHudOpacityIfExists(var1, Xqedii_sz99ul5p5yja(var4), var6);
         this.setHudOpacityIfExists(var1, var5, var6);
         this.setHudOpacityIfExists(var1, Xqedii_3fdmnv2uv0aso(var5), var6);
         this.setHudOpacityIfExists(var1, Xqedii_3c5to6aaszkg8(var5), var6);
         this.setHudOpacityIfExists(var1, Xqedii_26rnd9u6r5zi9(var5), var6);
         this.setHudOpacityIfExists(var1, Xqedii_1u2r13mquugg7(var5), var6);
         this.setHudOpacityIfExists(var1, Xqedii_q5pum5s9zbo6(var5), var6);
         this.setHudOpacityIfExists(var1, Xqedii_35ebcunxsxo1l(var5), var6);
         this.setHudOpacityIfExists(var1, Xqedii_38q9xl651wcdx(var5), var6);
         this.setHudOpacityIfExists(var1, Xqedii_1dwpbdvcpjliv(var5), var6);
         this.setHudOpacityIfExists(var1, Xqedii_3rjxhutj1vwfl(var5), var6);
      }
   }

   @Override
   public void beginShellOpacityBatch(EditorSession var1) {
      if ((2020607178 * 2020607178 + 2020607178 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         var1.shellOpacityBatchDepth = var1.shellOpacityBatchDepth + Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 26);
      }
   }

   @Override
   public void endShellOpacityBatch(Player var1, EditorSession var2) {
      if ((-835342930 * -835342930 + -835342930 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.shellOpacityBatchDepth > 0) {
         var2.shellOpacityBatchDepth = var2.shellOpacityBatchDepth - Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 21);
         if (var2.shellOpacityBatchDepth <= 0) {
            if (!var2.pendingShellOpacity.isEmpty()) {
               LinkedHashMap var3 = new LinkedHashMap(var2.pendingShellOpacity);
               var2.pendingShellOpacity.clear();

               for (Map.Entry<?, ?> var5 : ((Map<?, ?>)var3).entrySet()) {
                  Integer var6 = (Integer)var5.getValue();
                  this.applyShellOpacityNow(var1, (String)var5.getKey(), var6 == null ? Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 8) : var6);
               }
            }
         }
      }
   }

   @Override
   public void setHudOpacityIfExists(Player var1, String var2, int var3) {
      if ((-525002275 * (-525002275 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_3cny9rh.Xqedii_2bwaggn(this.hudService, var1, var2, var3);
      }
   }

   @Override
   public int getShellOpacity(Player var1, EditorSession var2, String var3, int var4) {
      if ((478779239 * (478779239 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         int var5 = Math.max(Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830822402, 13), Math.min(Xqedii_2ze73vg.Xqedii_18u831nn38lo(1808098902, 20), var4));
         if (var1 != null && var3 != null && !var3.isBlank()) {
            if (var2 != null) {
               Integer var6 = (Integer)var2.pendingShellOpacity.get(var3);
               if (var6 != null) {
                  return Math.max(Xqedii_2ze73vg.Xqedii_13jq3c36au68f(796769119, 28), Math.min(Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-304384389, 7), var6));
               }
            }

            String var8 = Xqedii_3r4gol7w1k66q(var3);
            Entity var7 = this.hudService.getHud(var1, var8);
            return var7 != null
               ? Math.max(
                  Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1596947899, 20),
                  Math.min(Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(776329218, 21), this.hudService.getHudOpacity(var7))
               )
               : var5;
         } else {
            return var5;
         }
      }
   }

   @Override
   public void spawnShellPanelHud(Player var1, EditorSession var2, ConfigurationSection var3, String var4) {
      if ((1351432681 * 1351432681 + 1351432681 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && var4 != null && !var4.isBlank() && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-553945411, 29)];
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1461219614, 11)] = var3.getString(
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
         );
         String var5 = this.firstNonBlank(var10001);
         String var6 = this.resolvePanelComponentNameFallback(var4);
         this.ensureShellBlockCache(var2);

         for (Entry var8 : var2.shellSectionCache.entrySet()) {
            if (var8 != null && var8.getKey() != null && var8.getValue() != null) {
               String var9 = (String)var8.getKey();
               ConfigurationSection var10 = (ConfigurationSection)var8.getValue();
               var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-407328199, 25)];
               var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 30)] = var10.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("wnj7uF3hcOoAks+8+3wFksoVbSbHn47JPMeDyYMSgiPoAf2O")
               );
               var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(485547093, 16)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "phMhQ5z6/nUG7x9rIjeqZ9DFFsF7Num7Ht8iWfSlIm4ZUFO5Sic="
               );
               String var11 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
               if (this.isRenderableBlockType(var11) && !this.isEditorToolShellBlockId(var9)) {
                  var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 21)];
                  var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 5)] = var10.getString(
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
                  );
                  String var12 = this.firstNonBlank(var10001);
                  var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-304416901, 7)];
                  var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 14)] = var10.getString(
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("kSak0NGp9a3iUDbWM46cF9uOrJkURi0/e2/40DWXlx59Gyo745YamD4IdJZU2eeeuf2tTsiQDwr/6MT+TBnnnRe9MQvof4JjCjc=")
                  );
                  String var13 = this.firstNonBlank(var10001);
                  boolean var14 = var5.isBlank() ? var4.equals(var9) : this.belongsToSidebarPanel(var5, var12);
                  int var15 = !var6.isBlank() && var6.equalsIgnoreCase(var13)
                     ? Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 16)
                     : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1014437191, 22);
                  if (var14 || var15 != 0) {
                     String var16 = Xqedii_3efl66alkva40(var9);
                     if (this.shouldSkipShellBlockForSpawnRetoggle(var2, var9)) {
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
                        double var17 = this.readDouble(
                           var10,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vKKayzv129fu+soeD6bfYdPwcE2kYsAjeOWIibaiwxa/r2AA1MZmuL+SyruKUhve"),
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dMemVQei62r6NkNu8BqxHTzcBg7yzM+XaIb3LIsc"),
                           Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544889370L, 5))
                        );
                        double var19 = this.readDouble(
                           var10,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6l2WF3VPN37KDzXw/G9b5TL+6GD2Z7ti90KnCY4iv1P1TeFGrB4AUOB3jzUJXHwM"),
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xm8RisRfJosJsXxzE9Kmi51eIt5xrdF3Z/Mg7m1j"),
                           Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-8528700771070418483L, 53))
                        );
                        double var21 = this.readDouble(
                           var10,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("p0bKKg1zr+Q4vpPJ9KjRLcwqYACILFzzF/R6St5o6lwHP51RRYE="),
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("p0bKKg1zr+Q4vpPJ9KjRLcwqYACILFzzF/R6St5o6lwHP51RRYE="),
                           this.readDouble(
                              var10,
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Ih2CJAtB1fwFkJFw79T3T4TtLXyOpSiUw/W6FBg5ChxZS4iqL6/K+4122C0MReDT"),
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("yQ7PCHPzn8xjeFfnHE0oVP5u4w1vD+sj0OA61xSAE3FhIPv2J0E="),
                              Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(4509984362482368947L, 61))
                           )
                        );
                        double var23 = this.readDouble(
                           var10,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dD1V5iTP6Cs1T70nZx7JxW7+VGRP/INn/TyRub+RmwTrKSQrCShArllQuVCb8eP6"),
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("FPKgmoGqqfsf/TAZ7Kaoe8WTiOLLnI5xMvuM1lCli1sVmL5BN/Y="),
                           this.readDouble(
                              var10,
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lErzeT8UmqIwoEIshJ7Fi16pNxEDG1a+KfX0vnDs6TaHlTzfg02UWVVtPRWM4P1OEu0="),
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("FPKgmoGqqfsf/TAZ7Kaoe8WTiOLLnI5xMvuM1lCli1sVmL5BN/Y="),
                              Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285735943194L, 28))
                           )
                        );
                        double var25 = this.readDouble(
                           var10,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GZNbFT1z5fLTz5m0iQOw7lZE3qkAFFk1rZ1X4zeaKsm9AlkyCXgpj7Bck6mfKr66AX4="),
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("CP4szv29CrqB0R19unEkQAr7B5MVCDB7QeO/jqmjBwdbqF4gfuQqjw=="),
                           this.readDouble(
                              var10,
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZdWikxTMRP2zcCTxHiJM36vJxKMhxOMZKnTT9mzDzJ4GFejuYy6wyZRUTyGCY+QV/NvSjA=="),
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("CP4szv29CrqB0R19unEkQAr7B5MVCDB7QeO/jqmjBwdbqF4gfuQqjw=="),
                              Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-3837603529125448321L, 10))
                           )
                        );
                        int var27 = var23 < Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544889370L, 23))
                           ? Xqedii_2ze73vg.Xqedii_18u831nn38lo(1815438934, 27)
                           : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1461219614, 11);
                        int var28 = var25 < Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699518298545L, 27))
                           ? Xqedii_2ze73vg.Xqedii_18u831nn38lo(1815438934, 27)
                           : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1968988613, 5);
                        double var29 = Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-3636186151780303007L, 45)), Math.abs(var23));
                        double var31 = Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5904174339378506161L, 53)), Math.abs(var25));
                        double var33 = this.readDouble(
                           var10,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("N9hWuxfafgycFZhRTf5tv+Fmz6rj6W0fWy/6yRy1v5qdFcaOH/RQXL6nFjk="),
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("QjsgMwj6B9RrJgbvgc4YY8K6Z7Rz5+bTWheudMklIRkeQg92yr5bCw=="),
                           Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-2838886807405595108L, 49))
                        );
                        int var35 = this.readOpacity(var10, Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1627687203, 29));
                        var35 = this.resolveShellBlockInitialOpacity(var2, var9, var10, var35);
                        TextAlignment var36 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var11)
                           ? this.readTextAlignment(var10)
                           : TextAlignment.CENTER;
                        int var37 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var11)
                           ? this.readTextWrapLineWidth(var10)
                           : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(947108010, 17);
                        int var38 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var11)
                              && Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("r90V0RFIMf5iidoh4OODRrWi2QSLYudTZptR4qXllAkYQe2BcMn07bnnMiPNrdMhBUBItpfOgO4=")
                                 .equals(var9)
                           ? Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 6)
                           : Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 23);
                        double var39 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var11) && var38 == 0
                           ? this.toInternalTextTopY(var19, var31)
                           : var19;
                        if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ekGHv3Apflq5i4KuttMa5RzRKWdKp+P89TX0lFMatimR3CqLCunVGg==").equals(var11)) {
                           this.clearOutlineHud(var1, var16);
                           this.removeBaseHud(var1, var16);
                           this.removeRoundedParts(var1, var16);
                           if (var2.shellRuntimeRects != null) {
                              var2.shellRuntimeRects.put(var9, new EditorRect(var17, var19, var29, var31));
                           }
                        } else {
                           boolean var41 = this.shouldSpawnOpacityVisual(var11, var35);
                           int var42 = !var41 && this.shouldKeepHiddenShellBlockSpawned(var2, var9, var10, var11)
                              ? Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 11)
                              : Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 25);
                           if (!var41 && var42 == 0) {
                              this.clearOutlineHud(var1, var16);
                              this.removeBaseHud(var1, var16);
                              this.removeRoundedParts(var1, var16);
                              if (var2.shellRuntimeRects != null) {
                                 var2.shellRuntimeRects.put(var9, new EditorRect(var17, var19, var29, var31));
                              }
                           } else {
                              double var43 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var11)
                                 ? this.applyTextAlignmentOffset(var17, var29, var36)
                                 : var17;
                              Placement var45 = this.positionCalculator.calculateBoxPlacement(var43, var39, var21, var29, var31);
                              Vector var46 = var45.location();
                              Vector var47 = var45.scale();
                              String var48;
                              if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var11)) {
                                 var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(864409602, 25)];
                                 var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 21)] = var10.getString(
                                    Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8")
                                 );
                                 var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(713980014, 7)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                    "MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ=="
                                 );
                                 var48 = this.firstNonBlank(var10001);
                              } else {
                                 var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681237590, 14)];
                                 var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 14)] = var10.getString(
                                    Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("BrdgWIszSU3U/XFEaOSUGc2PW1tRG9lV9bA74jsfQ5hSzR7pFRHtM79t")
                                 );
                                 var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2079185655, 31)] = var10.getString(
                                    Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8")
                                 );
                                 var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-675291282, 18)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                    "TCFgWP1KbdJkq2C4EKLfW6R7wsu7d7bxgC5pGJg1"
                                 );
                                 var48 = this.firstNonBlank(var10001);
                              }

                              var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855202, 5)];
                              var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-136595987, 0)] = var10.getString(
                                 Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SXnbKYNGrvJiwTC16d7CCneH8Zgjcjc0A8yLx/7xI0FG0Tc/Fws=")
                              );
                              var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2109431535, 4)] = var10.getString(
                                 Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Tz330h7gwv5vcG1MWRF2ngkGZFlHwxIvEnBSVPWE4ks8fk5KtG6eKmD7oj3tu8tP41I=")
                              );
                              String var49 = this.firstNonBlank(var10001);
                              if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dZeuVOMGyPOiH0WGkLv0oEg/zkVRrV9BOGRAVVNvybF8/ehg9fM1wjcxlRP4HPnETp8ZBOS5eD4=")
                                 .equalsIgnoreCase(var9)) {
                                 var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(713980398, 7)];
                                 var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 6)] = var2.editorColor1;
                                 var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221190, 4)] = var49;
                                 var49 = this.firstNonBlank(var10001);
                              } else if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZsdWq9E+CHoMzcApJzv1op6hgcbg/WFGpHDnrOZd2ZCkO2gyXknoiZXa7yzxBfq2t9dmsWyZiF4=")
                                 .equalsIgnoreCase(var9)) {
                                 var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855168, 1)];
                                 var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 24)] = var2.editorColor2;
                                 var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 14)] = var49;
                                 var49 = this.firstNonBlank(var10001);
                              }

                              var48 = this.withHexPrefix(var48, var49);
                              String var50 = this.applyPlaceholderApi(var1, var48);
                              var50 = this.applyPreferredFont(
                                 var50,
                                 var10,
                                 ((!Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var11)
                                    ? Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1439047240, 9)
                                    : Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 18)) != 0)
                              );
                              double var51 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var11)
                                 ? Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(5766521748530628593L, 39))
                                 : this.readOutlineSize(var10);
                              String var53 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var11)
                                 ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==")
                                 : this.readOutlineColor(var10);
                              String var54 = !Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var11)
                                    && var51 > Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(5803720582860306391L, 60))
                                 ? this.applyOutlineColor(var50, var53)
                                 : null;
                              if (this.isRoundedType(var11)) {
                                 boolean var55 = this.isDirectionalRoundedType(var11);
                                 double var56 = this.readRoundedRadius(var10, var29, var31);
                                 String var58 = this.readRoundedCornerUnicode(var10);
                                 String var59 = var55
                                    ? this.readRoundedCornerUnicodeTopLeft(var10)
                                    : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
                                 String var60 = var55
                                    ? this.readRoundedCornerUnicodeTopRight(var10)
                                    : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
                                 String var61 = var55
                                    ? this.readRoundedCornerUnicodeBottomRight(var10)
                                    : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
                                 String var62 = var55
                                    ? this.readRoundedCornerUnicodeBottomLeft(var10)
                                    : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
                                 double var63 = this.readRoundedTopLeftOffsetX(var10);
                                 double var65 = this.readRoundedTopLeftOffsetY(var10);
                                 double var67 = this.readRoundedTopRightOffsetX(var10);
                                 double var69 = this.readRoundedTopRightOffsetY(var10);
                                 double var71 = this.readRoundedBottomRightOffsetX(var10);
                                 double var73 = this.readRoundedBottomRightOffsetY(var10);
                                 double var75 = this.readRoundedBottomLeftOffsetX(var10);
                                 double var77 = this.readRoundedBottomLeftOffsetY(var10);
                                 double var79 = var17 + var29 / Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(6954870157089754138L, 60));
                                 double var81 = var39 + var31 / Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-3685533583563206650L, 55));
                                 this.renderRoundedOutline(
                                    var1,
                                    new RoundedBox(var16, var17, var39, var21, var29, var31),
                                    var54,
                                    var35,
                                    var56,
                                    new RoundedCornerGlyphs(var58, var59, var60, var61, var62),
                                    new RoundedCornerOffsets(var63, var65, var67, var69, var71, var73, var75, var77),
                                    new RoundedTransform(var33, (var27 != 0), (var28 != 0), var79, var81),
                                    var51,
                                    TextAlignment.CENTER,
                                    Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-712456479735718133L, 33))
                                 );
                                 this.renderRoundedBlock(
                                    var1,
                                    new RoundedBox(var16, var17, var39, var21, var29, var31),
                                    var50,
                                    var35,
                                    var56,
                                    new RoundedCornerGlyphs(var58, var59, var60, var61, var62),
                                    new RoundedCornerOffsets(var63, var65, var67, var69, var71, var73, var75, var77),
                                    new RoundedTransform(var33, (var27 != 0), (var28 != 0), var79, var81),
                                    TextAlignment.CENTER
                                 );
                              } else {
                                 this.upsertHud(var1, var16, var46, var47, var50, var35, var36, var37);
                                 this.applyElementTransformById(var1, var16, var33, (var27 != 0), (var28 != 0));
                                 if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(var11)) {
                                    this.clearOutlineHud(var1, var16);
                                 } else {
                                    double var86 = var43 + var29 / Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544889346L, 5));
                                    double var57 = var39 + var31 / Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012698981427633L, 31));
                                    this.renderSimpleOutline(
                                       var1,
                                       new RoundedBox(var16, var43, var39, var21, var29, var31),
                                       var54,
                                       var35,
                                       TextAlignment.CENTER,
                                       var36,
                                       new RoundedTransform(var33, (var27 != 0), (var28 != 0), var86, var57),
                                       var51,
                                       Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(1039290962375837179L, 40))
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
   public boolean isShellElementHudPresent(Player var1, String var2) {
      if ((-127890900 ^ -127890900) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = Xqedii_93se9g48zijx(var2);
         return ((this.hudService.getHud(var1, var3) == null && this.hudService.getHud(var1, Xqedii_2obeztrbg3p4y(var3)) == null
            ? Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-152208515, 6)
            : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(971094186, 17)) != 0);
      } else {
         return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1640265053, 21) != 0);
      }
   }

   @Override
   public boolean isShellPanelSpawned(Player var1, EditorSession var2, ConfigurationSection var3, String var4) {
      if ((721659329 * 721659329 + 721659329 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var2 == null || var3 == null || var4 == null || var4.isBlank()) {
         return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-136595987, 0) != 0);
      } else if (this.isShellElementHudPresent(var1, var4)) {
         return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-507991495, 25) != 0);
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221202, 2)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 31)] = var3.getString(
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
         );
         String var5 = this.firstNonBlank(var10001);
         return !var5.isBlank() && this.hasShellPanelMemberHud(var1, var2, var5);
      }
   }

   public boolean hasShellPanelMemberHud(Player var1, EditorSession var2, String var3) {
      if ((-1777847658 ^ -1777847658) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         this.ensureShellBlockCache(var2);

         for (Entry var5 : var2.shellSectionCache.entrySet()) {
            if (var5 != null && var5.getKey() != null && var5.getValue() != null) {
               String var6 = (String)var5.getKey();
               ConfigurationSection var7 = (ConfigurationSection)var5.getValue();
               String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 7)];
               var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-545408162, 28)] = var7.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
               );
               String var8 = this.firstNonBlank(var10001);
               if (this.belongsToSidebarPanel(var3, var8)) {
                  var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2080241389, 22)];
                  var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1591705019, 20)] = var7.getString(
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("wnj7uF3hcOoAks+8+3wFksoVbSbHn47JPMeDyYMSgiPoAf2O")
                  );
                  var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221190, 4)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                     "phMhQ5z6/nUG7x9rIjeqZ9DFFsF7Num7Ht8iWfSlIm4ZUFO5Sic="
                  );
                  String var9 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
                  if (!Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ekGHv3Apflq5i4KuttMa5RzRKWdKp+P89TX0lFMatimR3CqLCunVGg==").equals(var9)
                     && this.isShellElementHudPresent(var1, var6)) {
                     return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1942188372, 18) != 0);
                  }
               }
            }
         }

         return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1901360071, 2) != 0);
      } else {
         return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 10) != 0);
      }
   }

   @Override
   public void clearShellElementHud(Player var1, EditorSession var2, String var3) {
      if ((1872502681 * 1872502681 + 1872502681 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var3 != null && !var3.isBlank()) {
         String var4 = Xqedii_rqy12ohk1wuw(var3);
         this.clearOutlineHud(var1, var4);
         this.removeBaseHud(var1, var4);
         this.removeRoundedParts(var1, var4);
         if (var2 != null && var2.shellRuntimeRects != null) {
            var2.shellRuntimeRects.remove(var3);
         }
      }
   }

   public String resolveSidebarIndicatorId(EditorSession var1) {
      if ((1304835885 * 1304835885 + 1304835885 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.findShellBlockSection(
                  var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("az0n+01M1EB/DQG7N+OpGDgCZoHNc4YonrisZIe0LN6b+lMDBxEoKShhroGaGkAsSBVsdPGZjhZ23CRHza6Ysg==")
               )
               != null
            ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("az0n+01M1EB/DQG7N+OpGDgCZoHNc4YonrisZIe0LN6b+lMDBxEoKShhroGaGkAsSBVsdPGZjhZ23CRHza6Ysg==")
            : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("BW/6pd+wVGIp5X49DHX7ypDM263QlQMxa/oqFy26S25VI3DGsXWE1sl2TEDGko/1ZdjGSLOad/g=");
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public double resolveSidebarIndicatorX(EditorSession var1, EditorSidebarTab var2, String var3) {
      if ((7433304 * (7433304 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (!Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("az0n+01M1EB/DQG7N+OpGDgCZoHNc4YonrisZIe0LN6b+lMDBxEoKShhroGaGkAsSBVsdPGZjhZ23CRHza6Ysg==").equals(var3)) {
         return var2 == EditorSidebarTab.LAYERS
            ? Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(3271798048458942128L, 7))
            : Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819400051533073434L, 48));
      } else {
         double var4 = switch (Xqedii_3ana5qf.Xqedii_1xzbiek[var2.ordinal()]) {
            case 1 -> Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-4325522709532558083L, 22));
            case 2 -> Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(4474304487346241669L, 18));
            case 3 -> Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(1393876367765848987L, 54));
            default -> throw new MatchException(null, null);
         };
         EditorRect var6 = this.findShellBlockRect(
            var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("CJiPqlbitXVxk6499QyDktG//JOWTvJIaxqT1uAelUBDg5vPZ+tfCRMuEd13VllrxhOWLqz7")
         );
         return var6 == null ? var4 : var6.x + var4;
      }
   }

   public double resolveSidebarIndicatorY(String var1) {
      if ((650523620 * 650523620 + 650523620 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("az0n+01M1EB/DQG7N+OpGDgCZoHNc4YonrisZIe0LN6b+lMDBxEoKShhroGaGkAsSBVsdPGZjhZ23CRHza6Ysg==").equals(var1)
            ? Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(245003338461214888L, 46))
            : Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819560879511096346L, 40));
      }
   }

   @Override
   public boolean setPanelVisibleByComponentName(Player var1, EditorSession var2, String var3, boolean var4, boolean var5) {
      if (((-906420512 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         int var6 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2109431519, 4);
         HashMap var7 = new HashMap();
         int var8 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-410066264, 19);

         for (Map var10 : var2.shellBlocks) {
            var8++;
            ConfigurationSection var11 = this.mapToSection(var10);
            if (var11 != null) {
               String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-410590552, 19)];
               var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-68297994, 31)] = var11.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("kSak0NGp9a3iUDbWM46cF9uOrJkURi0/e2/40DWXlx59Gyo745YamD4IdJZU2eeeuf2tTsiQDwr/6MT+TBnnnRe9MQvof4JjCjc=")
               );
               String var12 = this.firstNonBlank(var10001);
               if (var3.equalsIgnoreCase(var12)) {
                  String var13 = this.resolveElementId(var11, var8, var7);
                  var6 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1901360067, 2);
                  if (!var4 && !var5) {
                     this.clearShellElementHud(var1, var2, var13);
                  } else {
                     int var14 = this.readOpacity(var11, Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830851010, 5));
                     this.setShellOpacity(var1, var13, var4 ? var14 : Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 23));
                  }
               }
            }
         }

         return (var6 != 0);
      } else {
         return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 15) != 0);
      }
   }

   @Override
   public boolean isKeyframePropertiesSidebarMode(EditorSession var1) {
      if ((-1870771823 * -1870771823 + -1870771823 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var1.activeTool != EditorTool.ANIMATION) {
         return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 5) != 0);
      } else if (!this.isTimelinePositionKeyframeSelected(var1)
         && !this.isTimelineScaleKeyframeSelected(var1)
         && !this.isTimelineRotationKeyframeSelected(var1)
         && !this.isTimelineOpacityKeyframeSelected(var1)) {
         return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1416873841, 10) != 0);
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(356990007, 6)];
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(499470799, 28)] = var1.animationTimelineSelectedTargetId;
         String var2 = this.firstNonBlank(var10001);
         var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681253974, 15)];
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(713980142, 7)] = this.resolveTimelineEditableTargetId(var1);
         String var3 = this.firstNonBlank(var10001);
         return ((!var2.isBlank() && !var3.isBlank() && var2.equals(var3)
            ? Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-304416901, 7)
            : Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 16)) != 0);
      }
   }

   public String resolvePropertiesTabPanelId(EditorSession var1) {
      if (((-940017339 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (this.isActionsSidebarMode(var1)) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nrDCb8u2IRVW7diturNy8OQL5M0gBLm2kAEXT9bUnzlOFP4aKFKJG6We");
      } else {
         return this.isKeyframePropertiesSidebarMode(var1)
            ? this.resolveKeyframePropertiesPanelId(var1)
            : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("UgE4PMnp3atRdkYttcLut9QbmWTSURFSNpGa7gY6jv3ITNAkfm2SEjbBuaLJzaUT");
      }
   }

   public String resolveKeyframePropertiesPanelId(EditorSession var1) {
      if (((-522741035 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (this.isTimelineRotationKeyframeSelected(var1)) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
            "C3E7uC5v15XNR9R7JoecnF8RZw/D0LJu2fD7uUMgMri1uyHjajX6sxWdH+Pi+zsH4CR3fTj3l2iDKgxq6JUJF7Ub7nWgsuj9i5aWH88ZIyQ5jNS0"
         );
      } else {
         return this.isTimelineOpacityKeyframeSelected(var1)
            ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "hDOd0CDDqHaE6jOXE4Z8iYuFNs5vcNYYMdACAcYYPYAhV/gCyU5fDjbbadqQtTP33gzWtlWVmmJSrxSOPKJjvsp4EZZnrEm08W1THB/VhQplVg=="
            )
            : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("P0mummFeX5hU+GKirxsbWkDizX8HiN8BdGiqk53odp0uvcLNATb8kcLtG+UuTPFfHsmpfOxmsU+lbOH6+kLCHdY7");
      }
   }

   public String resolveDesignTabPanelId(EditorSession var1) {
      if ((-1208768006 * -1208768006 - -1208768006 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.isItemDesignSidebarMode(var1)
            ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Tmk6maJJiUdZL41WMUKwQLv8atBVVq1wG1xxoatmbe2RaVLPurbW6tU/ScEB6QIw2CQ=")
            : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dLBTXhefcEPrcSXhmHJD/6fEh6cCmaJ3OfGFi+8wXVIIbIITPC91Jg==");
      }
   }

   public String resolveShellPassDesignTabPanelId(EditorSession var1) {
      if (((685046326 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (!var1.shellRenderPassActive) {
         return this.resolveDesignTabPanelId(var1);
      } else {
         if (var1.shellRenderPassDesignPanelId == null) {
            var1.shellRenderPassDesignPanelId = this.resolveDesignTabPanelId(var1);
         }

         return var1.shellRenderPassDesignPanelId;
      }
   }

   public String resolveShellPassPropertiesTabPanelId(EditorSession var1) {
      if ((-303606613 * -303606613 - -303606613 & 1) != 0) {
         throw new IllegalStateException();
      } else if (!var1.shellRenderPassActive) {
         return this.resolvePropertiesTabPanelId(var1);
      } else {
         if (var1.shellRenderPassPropertiesPanelId == null) {
            var1.shellRenderPassPropertiesPanelId = this.resolvePropertiesTabPanelId(var1);
         }

         return var1.shellRenderPassPropertiesPanelId;
      }
   }

   public boolean resolveShellPassTextDesignMode(EditorSession var1) {
      if ((-113799938 * -113799938 - -113799938 & 1) != 0) {
         throw new IllegalStateException();
      } else if (!var1.shellRenderPassActive) {
         return this.isTextDesignSidebarMode(var1);
      } else {
         if (var1.shellRenderPassTextDesignMode == null) {
            var1.shellRenderPassTextDesignMode = this.isTextDesignSidebarMode(var1);
         }

         return var1.shellRenderPassTextDesignMode;
      }
   }

   @Override
   public boolean isItemDesignSidebarMode(EditorSession var1) {
      if ((751854293 * (751854293 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1142985001, 26)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 22)] = this.getSidebarEditableTargetId(var1);
         String var2 = this.firstNonBlank(var10001);
         return !var2.isBlank() && this.isSidebarItemDesignTarget(var1, var2);
      }
   }

   @Override
   public void clearPendingItemDesignInventoryPick(EditorSession var1) {
      if ((1918199204 * 1918199204 + 1918199204 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         var1.pendingItemDesignInventoryTargetId = null;
      }
   }

   public int resolveShellBlockInitialOpacity(EditorSession var1, String var2, ConfigurationSection var3, int var4) {
      if ((-1248620782 * (-1248620782 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var3 != null && var1.editMode && var1.previewMode) {
         if (var1.hiddenLayerTargetIds.contains(this.shellSpawnOpacityRetoggleTargetId(var2))) {
            return Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 27);
         } else {
            EditorSidebarTab var5 = var1.rightSidebarTab == null ? EditorSidebarTab.LAYERS : var1.rightSidebarTab;
            String var6 = this.resolveShellPassPropertiesTabPanelId(var1);
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-621315091, 15)];
            var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2071852781, 22)] = var3.getString(
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
            );
            String var7 = this.firstNonBlank(var10001);
            var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681352278, 17)];
            var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 7)] = var3.getString(
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("kSak0NGp9a3iUDbWM46cF9uOrJkURi0/e2/40DWXlx59Gyo745YamD4IdJZU2eeeuf2tTsiQDwr/6MT+TBnnnRe9MQvof4JjCjc=")
            );
            String var8 = this.firstNonBlank(var10001);
            int var9 = var1.activeTool == EditorTool.ANIMATION
               ? Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 27)
               : Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1591705019, 20);
            if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ghIh9CNN8HvfoZDrg1yGLAEOcO5Sf4q3zloV6aKq7UKkp3cL75kMz4iqM/kZ7RGvnWyR4RRInxtBz//dGflMUdrXYr6UvL+7eYF4OA==")
               .equalsIgnoreCase(var8)) {
               String var10 = this.resolveTimelineEditableTargetId(var1);
               if (var9 == 0 || var10 == null || var10.isBlank()) {
                  return Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 22);
               }
            }

            ConfigurationSection var67 = this.findShellBlockSection(
               var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nAcU4tL91QWYK+Uvu+T8r6MA3yhFh6qoOXxAV9iGowlZSaKTylOzWbl1fKjfDUd3WzNAtuFzqV5gbw==")
            );
            var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(607479382, 30)];
            var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 15)] = var67 == null
               ? null
               : var67.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
               );
            String var11 = this.firstNonBlank(var10001);
            String var12 = this.resolvePanelComponentNameFallback(
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nAcU4tL91QWYK+Uvu+T8r6MA3yhFh6qoOXxAV9iGowlZSaKTylOzWbl1fKjfDUd3WzNAtuFzqV5gbw==")
            );
            int var13 = (var11.isBlank() || !this.belongsToSidebarPanel(var11, var7)) && (var12.isBlank() || !var12.equalsIgnoreCase(var8))
               ? Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 11)
               : Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681204822, 14);
            if (var13 != 0 && !var1.savePopupVisible) {
               return Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-492247154, 3);
            } else {
               ConfigurationSection var14 = this.findShellBlockSection(
                  var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("y/QA5wxIglExUTtdYVKOfNY5CAxznmUcouYtDcNitMGVRvLRjGtlsMBNRF9UBB1emizQ+XFxLoAti6Zq8J39FFxoWYpEL1CM")
               );
               var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-142873126, 23)];
               var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2109431519, 4)] = var14 == null
                  ? null
                  : var14.getString(
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
                  );
               String var15 = this.firstNonBlank(var10001);
               String var16 = this.resolvePanelComponentNameFallback(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("y/QA5wxIglExUTtdYVKOfNY5CAxznmUcouYtDcNitMGVRvLRjGtlsMBNRF9UBB1emizQ+XFxLoAti6Zq8J39FFxoWYpEL1CM")
               );
               int var17 = (var15.isBlank() || !this.belongsToSidebarPanel(var15, var7)) && (var16.isBlank() || !var16.equalsIgnoreCase(var8))
                  ? Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 5)
                  : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1901360067, 2);
               if (var17 != 0 && !var1.preferencesPopupVisible) {
                  return Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 11);
               } else {
                  ConfigurationSection var18 = this.findShellBlockSection(
                     var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZBGeBy4SDJBlOH50wIJBTnXsa6NgLGjL1xwXuo8LoY3BCp7MwW08akFLZirBHXRGVi/4xumKT6zCQ1ivXYLYBw==")
                  );
                  var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1010242887, 22)];
                  var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-237218532, 24)] = var18 == null
                     ? null
                     : var18.getString(
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
                     );
                  String var19 = this.firstNonBlank(var10001);
                  String var20 = this.resolvePanelComponentNameFallback(
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZBGeBy4SDJBlOH50wIJBTnXsa6NgLGjL1xwXuo8LoY3BCp7MwW08akFLZirBHXRGVi/4xumKT6zCQ1ivXYLYBw==")
                  );
                  int var21 = (var19.isBlank() || !this.belongsToSidebarPanel(var19, var7)) && (var20.isBlank() || !var20.equalsIgnoreCase(var8))
                     ? Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 1)
                     : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(2020485774, 23);
                  if (var21 != 0 && !var1.welcomePopupVisible) {
                     return Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1859631071, 10);
                  } else {
                     ConfigurationSection var22 = this.findShellBlockSection(
                        var1,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                           "9WgOWzpZbsobITSyp7VrkCNFKIqtc6jWj7ihpBP3NjmDhb8z0c4KaURYvugyWqKHARYCSOlXqFlvod0DobylMJV0ivE+/xs7qT0ipg=="
                        )
                     );
                     var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-675553426, 18)];
                     var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 10)] = var22 == null
                        ? null
                        : var22.getString(
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
                        );
                     String var23 = this.firstNonBlank(var10001);
                     int var24 = (var23.isBlank() || !this.belongsToSidebarPanel(var23, var7))
                           && !Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                 "tD3aZk9O/ucCDeSgYEsr7j8XALikm5J0C+gwl0OINiqZ4NsTVA43GHPV6TJKMmvaB4ZOBdhxhIQIpSpeH4yQzxl0mb3lOiigsTYZ7w=="
                              )
                              .equalsIgnoreCase(var8)
                        ? Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-546383945, 2)
                        : Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-273191971, 1);
                     if (var24 != 0 && (!var1.menuFilePopupImageMode || !var1.menuFilePopupVisible)) {
                        return Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 28);
                     } else if (var7.isBlank()) {
                        return var4;
                     } else {
                        ConfigurationSection var25 = this.findShellBlockSection(
                           var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("zySxGqRMVxBqJbv7W8ssKIQESqau0NT1Vp8IoB+28fAvn3OwtNkzYlenMKWXtCtC//mCogggG5lfY4OHCBBM6Q==")
                        );
                        var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(462070686, 29)];
                        var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 6)] = var25 == null
                           ? null
                           : var25.getString(
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
                           );
                        String var26 = this.firstNonBlank(var10001);
                        if (!var26.isBlank() && this.belongsToSidebarPanel(var26, var7) && var9 == 0) {
                           return Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 0);
                        } else {
                           ConfigurationSection var27 = this.findShellBlockSection(
                              var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("UgE4PMnp3atRdkYttcLut9QbmWTSURFSNpGa7gY6jv3ITNAkfm2SEjbBuaLJzaUT")
                           );
                           var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1196803615, 1)];
                           var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1372528069, 12)] = var27 == null
                              ? null
                              : var27.getString(
                                 Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                    "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                 )
                              );
                           String var28 = this.firstNonBlank(var10001);
                           if (var28.isBlank()
                              || !this.belongsToSidebarPanel(var28, var7)
                              || var5 == EditorSidebarTab.PROPERTIES
                                 && Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("UgE4PMnp3atRdkYttcLut9QbmWTSURFSNpGa7gY6jv3ITNAkfm2SEjbBuaLJzaUT").equals(var6)) {
                              String[] var10000 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-819083951, 20)];
                              var10000[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 17)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                 "P0mummFeX5hU+GKirxsbWkDizX8HiN8BdGiqk53odp0uvcLNATb8kcLtG+UuTPFfHsmpfOxmsU+lbOH6+kLCHdY7"
                              );
                              var10000[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1372523973, 12)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                 "C3E7uC5v15XNR9R7JoecnF8RZw/D0LJu2fD7uUMgMri1uyHjajX6sxWdH+Pi+zsH4CR3fTj3l2iDKgxq6JUJF7Ub7nWgsuj9i5aWH88ZIyQ5jNS0"
                              );
                              var10000[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1416875889, 10)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                 "hDOd0CDDqHaE6jOXE4Z8iYuFNs5vcNYYMdACAcYYPYAhV/gCyU5fDjbbadqQtTP33gzWtlWVmmJSrxSOPKJjvsp4EZZnrEm08W1THB/VhQplVg=="
                              );
                              String[] var29 = var10000;
                              String[] var30 = var29;
                              int var31 = var29.length;

                              for (int var32 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 21); var32 < var31; var32++) {
                                 String var71 = var30[var32];
                                 ConfigurationSection var74 = this.findShellBlockSection(var1, var71);
                                 var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 7)];
                                 var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1901360071, 2)] = var74 == null
                                    ? null
                                    : var74.getString(
                                       Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                          "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                       )
                                    );
                                 String var76 = this.firstNonBlank(var10001);
                                 if (!var76.isBlank()
                                    && this.belongsToSidebarPanel(var76, var7)
                                    && (var5 != EditorSidebarTab.PROPERTIES || !var71.equals(var6))) {
                                    return Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1372528069, 12);
                                 }
                              }

                              ConfigurationSection var68 = this.findShellBlockSection(
                                 var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nrDCb8u2IRVW7diturNy8OQL5M0gBLm2kAEXT9bUnzlOFP4aKFKJG6We")
                              );
                              var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 6)];
                              var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(356990071, 6)] = var68 == null
                                 ? null
                                 : var68.getString(
                                    Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                       "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                    )
                                 );
                              String var69 = this.firstNonBlank(var10001);
                              if (var69.isBlank()
                                 || !this.belongsToSidebarPanel(var69, var7)
                                 || var5 == EditorSidebarTab.PROPERTIES
                                    && Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nrDCb8u2IRVW7diturNy8OQL5M0gBLm2kAEXT9bUnzlOFP4aKFKJG6We").equals(var6)) {
                                 String var70 = this.resolveShellPassDesignTabPanelId(var1);
                                 var10000 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855168, 1)];
                                 var10000[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-675815570, 18)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                    "dLBTXhefcEPrcSXhmHJD/6fEh6cCmaJ3OfGFi+8wXVIIbIITPC91Jg=="
                                 );
                                 var10000[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1142985001, 26)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                    "Tmk6maJJiUdZL41WMUKwQLv8atBVVq1wG1xxoatmbe2RaVLPurbW6tU/ScEB6QIw2CQ="
                                 );
                                 String[] var33 = var10000;
                                 String[] var34 = var33;
                                 int var35 = var33.length;

                                 for (int var36 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(971225258, 17); var36 < var35; var36++) {
                                    String var78 = var34[var36];
                                    ConfigurationSection var79 = this.findShellBlockSection(var1, var78);
                                    var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-136595986, 0)];
                                    var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 16)] = var79 == null
                                       ? null
                                       : var79.getString(
                                          Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                             "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                          )
                                       );
                                    String var80 = this.firstNonBlank(var10001);
                                    if (!var80.isBlank()
                                       && this.belongsToSidebarPanel(var80, var7)
                                       && (var5 != EditorSidebarTab.DESIGN || !var78.equals(var70))) {
                                       return Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1111557258, 21);
                                    }
                                 }

                                 if (var1.rightSidebarTab != EditorSidebarTab.DESIGN || !this.resolveShellPassTextDesignMode(var1)) {
                                    String var72 = this.resolveDesignTextSettingsInvocationPath(var1);
                                    if (this.isPanelCachedBlock(
                                          var1,
                                          var3,
                                          Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("f6AiMw3McftGQY8Mbc8+r7Z1WPMdkbzsgBYgb0JU+w2x2/xYfVOmmp/pVs0X21oXdCHcSYpy")
                                       )
                                       || !var72.isBlank() && this.belongsToSidebarPanel(var72, var7)) {
                                       return Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 2);
                                    }
                                 }

                                 ConfigurationSection var73 = this.findShellBlockSection(
                                    var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("o429iR3IMzWbbKoUvWl74/Goj1ew0Xzk6LJIkVibIUdWFyBKcTF4vA==")
                                 );
                                 var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 11)];
                                 var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1549911158, 13)] = var73 == null
                                    ? null
                                    : var73.getString(
                                       Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                          "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                       )
                                    );
                                 String var75 = this.firstNonBlank(var10001);
                                 if (!var75.isBlank() && this.belongsToSidebarPanel(var75, var7) && var5 != EditorSidebarTab.LAYERS) {
                                    return Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 5);
                                 } else {
                                    ConfigurationSection var77 = this.findShellBlockSection(
                                       var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("iWUpOOlpU4NuO1QayaunqZ9FEoRljhPAWreqwvhVqgUyDZDr5PCNQQZu0ebcMz5WKe8rF5xv")
                                    );
                                    var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681223254, 11)];
                                    var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-273191973, 1)] = var77 == null
                                       ? null
                                       : var77.getString(
                                          Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                             "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                          )
                                       );
                                    String var37 = this.firstNonBlank(var10001);
                                    if (!var37.isBlank() && this.belongsToSidebarPanel(var37, var7) && !var1.fileDropdownVisible) {
                                       return Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1372528069, 12);
                                    } else {
                                       ConfigurationSection var38 = this.findShellBlockSection(
                                          var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("kG6Th83fXVg483cPDjeTNFFZyEtjDPucbaGA6MJCWQrEzXoCflOUzff61cXD3YIluUhHEvsk")
                                       );
                                       var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 27)];
                                       var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-304417029, 7)] = var38 == null
                                          ? null
                                          : var38.getString(
                                             Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                             )
                                          );
                                       String var39 = this.firstNonBlank(var10001);
                                       if (!var39.isBlank() && this.belongsToSidebarPanel(var39, var7) && !var1.editDropdownVisible) {
                                          return Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 24);
                                       } else {
                                          ConfigurationSection var40 = this.findShellBlockSection(
                                             var1,
                                             Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                "UOp1DWK2/SGNxB4We6RVgCp3wVvQMeScBVLdivY41AGQ+0p2yNlDBB4O95UtKNhns3ufXnTls3tsU1EevBPt5A=="
                                             )
                                          );
                                          var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(713980014, 7)];
                                          var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 31)] = var40 == null
                                             ? null
                                             : var40.getString(
                                                Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                   "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                                )
                                             );
                                          String var41 = this.firstNonBlank(var10001);
                                          if (!var41.isBlank() && this.belongsToSidebarPanel(var41, var7) && !var1.selectionDropdownVisible) {
                                             return Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2071852781, 22);
                                          } else {
                                             ConfigurationSection var42 = this.findShellBlockSection(
                                                var1,
                                                Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                   "9epKzkLEl9sqUNEI8eLHGq6S+FMlftDELM4EtzrK0VJD1fmcinkqpvMzAotTL73xAkPSoLl/E8I="
                                                )
                                             );
                                             var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681225302, 12)];
                                             var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-605046933, 25)] = var42 == null
                                                ? null
                                                : var42.getString(
                                                   Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                      "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                                   )
                                                );
                                             String var43 = this.firstNonBlank(var10001);
                                             if (!var43.isBlank() && this.belongsToSidebarPanel(var43, var7) && !var1.layerDropdownVisible) {
                                                return Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1640265053, 21);
                                             } else {
                                                ConfigurationSection var44 = this.findShellBlockSection(
                                                   var1,
                                                   Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                      "DK1RAmGfKVGQd+Hei+4Y38pv3PzLJVcGi3xcGL4aqZZchbHRirj/os72/NWMxAiuJsDXU8Ls5CRaqg=="
                                                   )
                                                );
                                                var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2008997295, 27)];
                                                var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-299200904, 31)] = var44 == null
                                                   ? null
                                                   : var44.getString(
                                                      Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                         "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                                      )
                                                   );
                                                String var45 = this.firstNonBlank(var10001);
                                                if (!var45.isBlank() && this.belongsToSidebarPanel(var45, var7) && !var1.windowDropdownVisible) {
                                                   return Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 9);
                                                } else {
                                                   ConfigurationSection var46 = this.findShellBlockSection(
                                                      var1,
                                                      Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                         "WPhIvlGP9F3hHjOrNE9p+XtjyeacX13LMlNfLCdYpIIAaLka8puFxVJJ8VcSGzZxbtc5YG6vIAD9+5I2KLE="
                                                      )
                                                   );
                                                   var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-507991495, 25)];
                                                   var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1549911158, 13)] = var46 == null
                                                      ? null
                                                      : var46.getString(
                                                         Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                            "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                                         )
                                                      );
                                                   String var47 = this.firstNonBlank(var10001);
                                                   if (!var47.isBlank() && this.belongsToSidebarPanel(var47, var7) && !var1.keyframeDropdownVisible) {
                                                      return Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-575705154, 11);
                                                   } else {
                                                      ConfigurationSection var48 = this.findShellBlockSection(
                                                         var1,
                                                         Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                            "lJWtjd/SgnfKPbXiqubMEkSf1ZW3aeeuit1InTNzFUvvcCKiWzIBfWD50ow6XDG98delk+M/KHVY8z+EzhVOEHs3TpwrRSlbRCzHT+cOf+M="
                                                         )
                                                      );
                                                      var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-492247162, 3)];
                                                      var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 26)] = var48 == null
                                                         ? null
                                                         : var48.getString(
                                                            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                               "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                                            )
                                                         );
                                                      String var49 = this.firstNonBlank(var10001);
                                                      if (!var49.isBlank() && this.belongsToSidebarPanel(var49, var7) && !var1.keyframeTimelineDropdownVisible) {
                                                         return Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 3);
                                                      } else {
                                                         ConfigurationSection var50 = this.findShellBlockSection(
                                                            var1,
                                                            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                               "6+efmR6mabscwEG9NImK9ODLlHtEjA90Y8trT7cJKCP/quEWebPDViXjsqtzOJfp8KgJbaW5T30="
                                                            )
                                                         );
                                                         var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681155670, 16)];
                                                         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1014437191, 22)] = var50 == null
                                                            ? null
                                                            : var50.getString(
                                                               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                  "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                                               )
                                                            );
                                                         String var51 = this.firstNonBlank(var10001);
                                                         if (!var51.isBlank() && this.belongsToSidebarPanel(var51, var7) && !var1.colorPickerVisible) {
                                                            return Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1196803613, 1);
                                                         } else {
                                                            ConfigurationSection var52 = this.findShellBlockSection(
                                                               var1,
                                                               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                  "pZnjcqJiibqKspZhZuFlO+OLl4/mv6SB8vsG4QOPRqskp2vwJyiEgElbDTioaZWEE2q3eaPY+BFqpG6xun7Edriq1BQ="
                                                               )
                                                            );
                                                            var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-821181103, 20)];
                                                            var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 15)] = var52 == null
                                                               ? null
                                                               : var52.getString(
                                                                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                     "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                                                  )
                                                               );
                                                            String var53 = this.firstNonBlank(var10001);
                                                            if (!var53.isBlank() && this.belongsToSidebarPanel(var53, var7) && !var1.cursorPageDropdownVisible) {
                                                               return Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 28);
                                                            } else {
                                                               ConfigurationSection var54 = this.findShellBlockSection(
                                                                  var1,
                                                                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                     "x9JOLpSdGvuS78Od1OfQKPOUAdSviNrYYAooGJQclunUVMrpVvLPYWroqBkHVlkOaH3FPOpD/r7Lu9yGV+cc7YGbq7KVeUEN"
                                                                  )
                                                               );
                                                               var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1689609814, 23)];
                                                               var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-948874125, 26)] = var54 == null
                                                                  ? null
                                                                  : var54.getString(
                                                                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                        "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                                                     )
                                                                  );
                                                               String var55 = this.firstNonBlank(var10001);
                                                               if (!var55.isBlank()
                                                                  && this.belongsToSidebarPanel(var55, var7)
                                                                  && !var1.cursorLayersDropdownVisible) {
                                                                  return Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1111557258, 21);
                                                               } else {
                                                                  ConfigurationSection var56 = this.findShellBlockSection(
                                                                     var1,
                                                                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                        "xUMz3AQ5wfJPU57tgCaG/iVEdJtXXaI0eZlKTXLsNHh+djY4GFYHMgxD82kjH+C+U53WVe4I3MUwz/eP9c1mP6PMn5dI4NMSo9w="
                                                                     )
                                                                  );
                                                                  var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-276972706, 28)];
                                                                  var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-545408162, 28)] = var56 == null
                                                                     ? null
                                                                     : var56.getString(
                                                                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                           "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                                                        )
                                                                     );
                                                                  String var57 = this.firstNonBlank(var10001);
                                                                  if (!var57.isBlank()
                                                                     && this.belongsToSidebarPanel(var57, var7)
                                                                     && !var1.cursorActionsDropdownVisible) {
                                                                     return Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1591705019, 20);
                                                                  } else {
                                                                     ConfigurationSection var58 = this.findShellBlockSection(
                                                                        var1,
                                                                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                           "s90mMorE/n6mOHgB1UvX00BgZDA50P1/IkQFhbujYWODFvEjPH8+n+5kdV83BiOem72YM9RLDJMOs9jwgzHieJc40/v6A3slf1UE1SupdP/TWIRn"
                                                                        )
                                                                     );
                                                                     var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(356990007, 6)];
                                                                     var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(485612629, 16)] = var58 == null
                                                                        ? null
                                                                        : var58.getString(
                                                                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                              "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                                                           )
                                                                        );
                                                                     String var59 = this.firstNonBlank(var10001);
                                                                     if (!var59.isBlank()
                                                                        && this.belongsToSidebarPanel(var59, var7)
                                                                        && !var1.cursorActionsEditDropdownVisible) {
                                                                        return Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 29);
                                                                     } else {
                                                                        ConfigurationSection var60 = this.findShellBlockSection(
                                                                           var1,
                                                                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                              "hqd5rprT/D9A6GgVBKKjL5+bePxEFBh6lbKMg+0lFsZSMHakCAuEYaWH6SuyaSzkfUoJFA=="
                                                                           )
                                                                        );
                                                                        var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1015982989, 26)];
                                                                        var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1904677334, 15)] = var60 == null
                                                                           ? null
                                                                           : var60.getString(
                                                                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                                 "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                                                              )
                                                                           );
                                                                        String var61 = this.firstNonBlank(var10001);
                                                                        if (!var61.isBlank()
                                                                           && this.belongsToSidebarPanel(var61, var7)
                                                                           && !var1.previewSizeToastVisible) {
                                                                           return Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 19);
                                                                        } else {
                                                                           ConfigurationSection var62 = this.findShellBlockSection(
                                                                              var1,
                                                                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                                 "boMw715DyK+DZkENPZO5+jM/hcFr4pxjq1LH/2ekFbvoVVq8siJvRn1UTinlq/vl+RtWpp2TdcLvy0uUneI="
                                                                              )
                                                                           );
                                                                           var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1859632095, 10)];
                                                                           var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 25)] = var62 == null
                                                                              ? null
                                                                              : var62.getString(
                                                                                 Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                                    "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                                                                 )
                                                                              );
                                                                           String var63 = this.firstNonBlank(var10001);
                                                                           if (!var63.isBlank() && this.belongsToSidebarPanel(var63, var7)) {
                                                                              if (!var1.displayHand && !var1.displayHotbar) {
                                                                                 return Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1942450516, 18);
                                                                              }

                                                                              var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1697998422, 23)];
                                                                              var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 4)] = var3.getString(
                                                                                 Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                                    "lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8"
                                                                                 )
                                                                              );
                                                                              var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1092767881, 3)] = var3.getString(
                                                                                 Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                                    "BrdgWIszSU3U/XFEaOSUGc2PW1tRG9lV9bA74jsfQ5hSzR7pFRHtM79t"
                                                                                 )
                                                                              );
                                                                              String var64 = this.firstNonBlank(var10001);
                                                                              if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                                       "ByA653rE/m0F6zPs71eS6RdbXLRdTKAuKU6SpYM0va3bKdml6DTqlH+3Q9tg2lTgnSvtWjAzOewh673Wo0+GFoAGXv8zaRmEAwf31NZ7UUy7iQ=="
                                                                                    )
                                                                                    .equals(var2)
                                                                                 || Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                                       "MKH6ifn2KjHpJ0JEo9etEfTpKAma/CBv2/7uugAI"
                                                                                    )
                                                                                    .equals(var64)) {
                                                                                 return var1.displayHand
                                                                                    ? var4
                                                                                    : Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 31);
                                                                              }

                                                                              if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                                       "vas8dRmut+s8oVFR0KqxVmydqICDaevtnh5+yt7Xq1Ot/n4tg2qhL+ZAqlRlQIYVaxRq2qaFIEOwNnEQjzce076p50Jxmeh/4EHOYGauGSwtAJZ7MqI="
                                                                                    )
                                                                                    .equals(var2)
                                                                                 || Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                                       "T6gEEEohd4g5IqfQ7x/qgA+N8CoA70Boo/21Ppr0"
                                                                                    )
                                                                                    .equals(var64)) {
                                                                                 return var1.displayHotbar
                                                                                    ? var4
                                                                                    : Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 15);
                                                                              }
                                                                           }

                                                                           if (this.isPreviewEmptyInfoShellBlock(var2, var3)) {
                                                                              return this.shouldShowPreviewEmptyInfo(var1)
                                                                                 ? var4
                                                                                 : Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 11);
                                                                           } else {
                                                                              ConfigurationSection var81 = this.findShellBlockSection(
                                                                                 var1,
                                                                                 Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                                    "vR3VD7zf00/83O3JPD3qIBFwXS2eAPj729pGoQd9G3JJoR26DqwfKc8vV2RuLxv40Uk="
                                                                                 )
                                                                              );
                                                                              var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221214, 3)];
                                                                              var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1901360071, 2)] = var81 == null
                                                                                 ? null
                                                                                 : var81.getString(
                                                                                    Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                                                                       "vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="
                                                                                    )
                                                                                 );
                                                                              String var65 = this.firstNonBlank(var10001);
                                                                              if (!var65.isBlank() && this.belongsToSidebarPanel(var65, var7)) {
                                                                                 int var66 = var1.selectedElementId == null
                                                                                       && var1.additionalSelectedElementIds.isEmpty()
                                                                                    ? Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 9)
                                                                                    : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(971094186, 17);
                                                                                 if (!var1.alignToolbarVisible || var66 == 0) {
                                                                                    return Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1439046728, 9);
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
                                 return Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1090816323, 29);
                              }
                           } else {
                              return Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-152208515, 6);
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

   public boolean shouldRenderEditorShellBlock(EditorSession var1, String var2, ConfigurationSection var3) {
      if ((651399737 * 651399737 + 651399737 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var3 == null || !var1.editMode || !var1.previewMode) {
         return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221207, 0) != 0);
      } else if (this.resolveShellBlockInitialOpacity(var1, var2, var3, Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1225984037, 16)) > 0) {
         return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221190, 4) != 0);
      } else {
         return (this.isPreviewEmptyInfoShellBlock(var2, var3)
            ? (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 27) != 0)
            : this.isShellCachedPanelBlock(var1, var3));
      }
   }

   public boolean shouldKeepHiddenShellBlockSpawned(EditorSession var1, String var2, ConfigurationSection var3, String var4) {
      if (((-841999414 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 7)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 8)] = var3 == null
            ? null
            : var3.getString(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="));
         String var5 = this.firstNonBlank(var10001);
         String var6 = this.resolveDesignTextSettingsInvocationPath(var1);
         return ((!this.isPanelCachedBlock(
                  var1, var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("f6AiMw3McftGQY8Mbc8+r7Z1WPMdkbzsgBYgb0JU+w2x2/xYfVOmmp/pVs0X21oXdCHcSYpy")
               )
               && (var6.isBlank() || !this.belongsToSidebarPanel(var6, var5))
               && !this.isPreviewEmptyInfoShellBlock(var2, var3)
            ? Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1997883196, 30)
            : Xqedii_2ze73vg.Xqedii_18u831nn38lo(1689609814, 23)) != 0);
      }
   }

   @Override
   public void setSidebarPanelVisible(Player var1, EditorSession var2, String var3, boolean var4) {
      if (((-1071634455 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         this.setSidebarPanelVisibleTracked(var1, var2, var3, var4);
      }
   }

   @Override
   public boolean setSidebarPanelVisibleTracked(Player var1, EditorSession var2, String var3, boolean var4) {
      if ((-1690747601 ^ -1690747601) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         boolean var5 = this.shouldKeepShellPanelSpawned(var3);
         String var6 = this.resolvePanelComponentNameFallback(var3);
         int var7 = !var6.isBlank() ? Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1142985001, 26) : Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-621347859, 15);
         Boolean var8 = (Boolean)var2.sidebarPanelVisibilityStates.get(var3);
         int var9 = var8 != null && var8 == var4 ? Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 29) : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-2031965977, 27);
         if (var9 == 0) {
            return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 18) != 0);
         } else {
            ConfigurationSection var10 = this.findShellBlockSection(var2, var3);
            if (var10 == null) {
               if (var7 == 0) {
                  return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 10) != 0);
               } else {
                  this.setPanelVisibleByComponentName(var1, var2, var6, var4, var5);
                  var2.sidebarPanelVisibilityStates.put(var3, var4);
                  return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 8) != 0);
               }
            } else {
               int var11 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 18);
               if (var4 && (var7 != 0 || !this.isShellPanelSpawned(var1, var2, var10, var3))) {
                  this.spawnShellPanelHud(var1, var2, var10, var3);
                  var11 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-984494323, 4);
               }

               String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1680959062, 18)];
               var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-675815570, 18)] = var10.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
               );
               String var12 = this.firstNonBlank(var10001);
               if (var12.isBlank()) {
                  if (this.setPanelVisibleByComponentName(var1, var2, var6, var4, var5)) {
                     var2.sidebarPanelVisibilityStates.put(var3, var4);
                     return ((var9 == 0 && var11 == 0
                        ? Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-675815570, 18)
                        : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1195128597, 14)) != 0);
                  } else {
                     if (!var4 && !var5) {
                        this.clearShellElementHud(var1, var2, var3);
                     } else {
                        int var20 = this.readOpacity(var10, Xqedii_2ze73vg.Xqedii_18u831nn38lo(2077582934, 21));
                        this.setShellOpacity(var1, var3, var4 ? var20 : Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 3));
                     }

                     var2.sidebarPanelVisibilityStates.put(var3, var4);
                     return ((var9 == 0 && var11 == 0
                        ? Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-68297994, 31)
                        : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1416872817, 10)) != 0);
                  }
               } else {
                  this.ensureShellBlockCache(var2);
                  int var13 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 28);

                  for (Entry var15 : var2.shellSectionCache.entrySet()) {
                     if (var15 != null && var15.getKey() != null && var15.getValue() != null) {
                        String var16 = (String)var15.getKey();
                        ConfigurationSection var17 = (ConfigurationSection)var15.getValue();
                        var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 26)];
                        var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 7)] = var17.getString(
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
                        );
                        String var18 = this.firstNonBlank(var10001);
                        if (this.belongsToSidebarPanel(var12, var18)) {
                           var13 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1151406211, 12);
                           if (!var4 && !var5) {
                              this.clearShellElementHud(var1, var2, var16);
                           } else {
                              int var19 = this.readOpacity(var17, Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-575182914, 11));
                              this.setShellOpacity(var1, var16, var4 ? var19 : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(356990071, 6));
                           }
                        }
                     }
                  }

                  if (var13 == 0) {
                     this.setPanelVisibleByComponentName(var1, var2, var6, var4, var5);
                  }

                  var2.sidebarPanelVisibilityStates.put(var3, var4);
                  return ((var9 == 0 && var11 == 0
                     ? Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1997883196, 30)
                     : Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681213014, 13)) != 0);
               }
            }
         }
      } else {
         return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-545408162, 28) != 0);
      }
   }

   public void updateSidebarTabHover(Player var1, EditorSession var2, double var3, double var5) {
      if ((2084828538 * 2084828538 + 2084828538 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         EditorSidebarTab var7 = null;
         if (this.isInsideShellBlock(
            var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("yZaKwA5SBtBc8wzcraLlL9/PCsHVTR1ap6VSY+Vxef05aT2yxJ7KatU173rV3vwRfLtC7490JzeHvSEd9rg="), var3, var5
         )) {
            var7 = EditorSidebarTab.PROPERTIES;
         } else if (this.isInsideShellBlock(
            var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LS5FDBAH9o1Gly8uyCMRz18locvp5848O1hQKQL3EsDSj/HNp6C1ciYdyT8D3fVZCElUbhZp"), var3, var5
         )) {
            var7 = EditorSidebarTab.DESIGN;
         } else if (this.isInsideSidebarLayersHitbox(var2, var3, var5)) {
            var7 = EditorSidebarTab.LAYERS;
         }

         if (var2.rightSidebarHoverTab != var7) {
            var2.rightSidebarHoverTab = var7;
            this.updateSidebarTabVisualState(
               var1, var2, (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-605046933, 25) != 0), (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 12) != 0)
            );
         }
      }
   }

   @Override
   public void setRightSidebarTab(Player var1, EditorSession var2, EditorSidebarTab var3, boolean var4) {
      if ((-1263629509 ^ -1263629509) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         EditorSidebarTab var5 = var3 == null ? EditorSidebarTab.LAYERS : var3;
         int var6 = var2.rightSidebarTab != var5 ? Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 6) : Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 11);
         var2.rightSidebarTab = var5;
         var2.rightSidebarHoverTab = null;
         if (var6 != 0) {
            var2.pendingPropertyField = null;
            this.clearPendingItemDesignInventoryPick(var2);
            var2.pendingActionTargetId = null;
            var2.pendingActionIndex = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(237218531, 24);
            this.stopOpacitySliderDrag(var2, (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 24) != 0));
            this.resetSidebarFieldDrag(var1, var2);
            this.clearActionsReorderState(var2);
            this.clearActionsReorderGhostHud(var1, var2);
            if (var2.cursorActionsDropdownVisible) {
               this.setCursorActionsDropdownVisible(var1, var2, (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 17) != 0));
            }

            if (var2.cursorActionsEditDropdownVisible) {
               this.setCursorActionsEditDropdownVisible(var1, var2, (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 27) != 0));
            }

            if (var2.keyframeDropdownVisible) {
               this.setTimelineKeyframeDropdownVisible(var1, var2, (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1217668113, 9) != 0));
            }

            if (var2.keyframeTimelineDropdownVisible) {
               this.setTimelineTimelineDropdownVisible(var1, var2, (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 23) != 0));
            }

            this.playEditorSfx(var1, var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("JKS8vK6ZYhaWN3Bmw5a97yBFnmoV/TEbLrIaVk1k+XN2f0ZJTxXdadGeRRW7wHhckEUdKw=="));
         }

         this.beginShellOpacityBatch(var2);

         try {
            this.updateSidebarTabVisualState(
               var1,
               var2,
               ((var4 && var6 != 0 ? Xqedii_2ze73vg.Xqedii_18u831nn38lo(1679124054, 21) : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-410066264, 19)) != 0),
               (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-575705154, 11) != 0)
            );
            this.updateEditorPropertiesSidebar(var1, var2);
         } finally {
            this.endShellOpacityBatch(var1, var2);
         }
      }
   }

   @Override
   public void updateSidebarTabVisualState(Player var1, EditorSession var2, boolean var3, boolean var4) {
      if ((1833463740 * 1833463740 - 1833463740 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.editMode && var2.previewMode) {
         EditorSidebarTab var5 = var2.rightSidebarTab == null ? EditorSidebarTab.LAYERS : var2.rightSidebarTab;
         var2.rightSidebarTab = var5;
         EditorSidebarTab var6 = var2.rightSidebarHoverTab;
         String var7 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("VEQdEBXxmfsIcQ5luyy6Cl4GEJpCdExBo2NKopgdnGY6I4QCjh0oUB5D9gAdaw==");
         String var8 = var5 != EditorSidebarTab.DESIGN && var6 != EditorSidebarTab.DESIGN
            ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("wbpZEhKF8jQ4PNoiSKDd4IHXK7uU+5TBxybN0vQ6+nEmwop7lFXdWO/bY1sWiQ==")
            : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("VEQdEBXxmfsIcQ5luyy6Cl4GEJpCdExBo2NKopgdnGY6I4QCjh0oUB5D9gAdaw==");
         String var9 = var5 != EditorSidebarTab.LAYERS && var6 != EditorSidebarTab.LAYERS
            ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("wbpZEhKF8jQ4PNoiSKDd4IHXK7uU+5TBxybN0vQ6+nEmwop7lFXdWO/bY1sWiQ==")
            : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("VEQdEBXxmfsIcQ5luyy6Cl4GEJpCdExBo2NKopgdnGY6I4QCjh0oUB5D9gAdaw==");
         String var10;
         if (this.isActionsSidebarMode(var2)) {
            var10 = Xqedii_1p15vtlvprfjw(var7);
         } else if (this.isKeyframePropertiesSidebarMode(var2)) {
            var10 = Xqedii_1s7zvhathqo0o(var7);
         } else {
            var10 = Xqedii_1iiaqucatgum(var7);
         }

         this.setShellText(
            var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("XU1cM4rUHWTMwn0ltI6r+85iNRgQVKuuHeBgYRdaQWKrfUaYfFjTgB9+CM29LC2dMcY+n8ZakieR2OdgavIZdGVktlK0kcVN"), var10
         );
         this.setShellText(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("eds5HuIGxBmrUJRG96IU4oZjzoRQHgahB2Fdtw9Tor10odD7cHHvllt6Lez/uJ4ohwfFcCs8eusYng=="), var10);
         this.setShellText(
            var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ERyCUziCWr82cX3BkwH9z/cXdUkxRU4M+3WkvTMAisNxDbwxdD7xjKDxee+PmT0meWY="), Xqedii_1pyz7hacstbxc(var8)
         );
         this.setShellText(
            var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("wlVma62O0f3k0of+7JQ2cKf5Np2+EU0ES+cT4gNvgAMYOLfBViTnAaR9qjkfOOdMvvs="), Xqedii_3ld6yo0724ksi(var9)
         );
         if (var6 == null) {
            this.updateSidebarTabPanels(var1, var2, var5, var4);
         }

         int var11 = var5 == EditorSidebarTab.DESIGN && this.isTextDesignSidebarMode(var2)
            ? Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1427960028, 8)
            : Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-545408162, 28);
         this.syncDesignTextAlignControlsVisibility(var1, var2, (var11 != 0));
         String var12 = this.resolveSidebarIndicatorId(var2);
         EditorRect var13 = this.findShellBlockRect(var2, var12);
         double var14 = this.resolveSidebarIndicatorX(var2, var5, var12);
         double var16 = var13 == null ? this.resolveSidebarIndicatorY(var12) : var13.y;
         double var18 = var13 == null ? Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(4157162755091994604L, 42)) : var13.width;
         double var20 = var13 == null ? Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-5845113403614218602L, 13)) : var13.height;
         if (var13 == null || Math.abs(var13.x - var14) > Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-6143497600502226024L, 42))) {
            if (var3) {
               this.animateSidebarCurrentOption(var1, var2, var12, var14, var16);
            } else if (var2.rightSidebarHoverTab == null) {
               this.moveShellElement(var1, var2, var12, var14, var16, var18, var20);
            }
         }
      }
   }

   public void animateSidebarCurrentOption(Player var1, EditorSession var2, String var3, double var4, double var6) {
      if ((-835046984 ^ -835046984) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         EditorRect var8 = this.findShellBlockRect(var2, var3);
         double var9 = var8 == null ? var4 : var8.x;
         double var11 = var8 == null ? var6 : var8.y;
         double var13 = var8 == null ? Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-1099052248649170859L, 21)) : var8.width;
         double var15 = var8 == null ? Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561010666982426L, 40)) : var8.height;
         if (Math.abs(var9 - var4) < Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(2560083818419536617L, 58))) {
            this.moveShellElement(var1, var2, var3, var4, var11, var13, var15);
         } else {
            int var17 = var2.rightIndicatorAnimationToken = var2.rightIndicatorAnimationToken + Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 24);
            int[] var10000 = new int[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 4)];
            var10000[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 22)] = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2071852781, 22);
            int[] var18 = var10000;
            Xqedii_38o2zes.Xqedii_j3mggbv(
               this.plugin,
               var1,
               Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-7588879848215301346L, 5),
               Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902083068262476209L, 46),
               var16 -> {
                  if (((1411561066 | 1) & 1) == 0) {
                     throw new IllegalStateException();
                  } else if (!var1.isOnline()) {
                     var16.cancel();
                  } else {
                     EditorSession var17x = (EditorSession)this.editorSessions.get(var1.getUniqueId());
                     if (var17x == var2 && var2.rightIndicatorAnimationToken == var17) {
                        var18[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2071852781, 22)] += Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1242630181, 16);
                        double var18x = Math.min(
                           Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544922170L, 17)),
                           var18[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1351631139, 19)]
                              / Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(1960026102099330368L, 49))
                        );
                        double var20 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561234022059034L, 36))
                           - Math.pow(
                              Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-5532275566922935295L, 53)) - var18x,
                              Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(1144653235579305335L, 24))
                           );
                        double var22 = var9 + (var4 - var9) * var20;
                        this.moveShellElement(var1, var2, var3, var22, var11, var13, var15);
                        if (var18[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1992146683, 13)] >= Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1904480726, 15)) {
                           var16.cancel();
                        }
                     } else {
                        var16.cancel();
                     }
                  }
               }
            );
         }
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public void updateSidebarTabPanels(Player var1, EditorSession var2, EditorSidebarTab var3, boolean var4) {
      if (((2050509557 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null) {
         int var5 = var2.rightSidebarPanelTransitionToken = var2.rightSidebarPanelTransitionToken + Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 16);
         String var6 = this.resolvePropertiesTabPanelId(var2);
         String var7 = this.resolveDesignTabPanelId(var2);
         boolean var8 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 20) != 0;
         if (var3 != EditorSidebarTab.PROPERTIES
            || !Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("UgE4PMnp3atRdkYttcLut9QbmWTSURFSNpGa7gY6jv3ITNAkfm2SEjbBuaLJzaUT").equals(var6)) {
            var8 |= this.setSidebarPanelVisibleTracked(
               var1,
               var2,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("UgE4PMnp3atRdkYttcLut9QbmWTSURFSNpGa7gY6jv3ITNAkfm2SEjbBuaLJzaUT"),
               (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 5) != 0)
            );
         }

         if (var3 != EditorSidebarTab.PROPERTIES
            || !Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("P0mummFeX5hU+GKirxsbWkDizX8HiN8BdGiqk53odp0uvcLNATb8kcLtG+UuTPFfHsmpfOxmsU+lbOH6+kLCHdY7").equals(var6)) {
            var8 |= this.setSidebarPanelVisibleTracked(
               var1,
               var2,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("P0mummFeX5hU+GKirxsbWkDizX8HiN8BdGiqk53odp0uvcLNATb8kcLtG+UuTPFfHsmpfOxmsU+lbOH6+kLCHdY7"),
               (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-304417029, 7) != 0)
            );
         }

         if (var3 != EditorSidebarTab.PROPERTIES
            || !Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "C3E7uC5v15XNR9R7JoecnF8RZw/D0LJu2fD7uUMgMri1uyHjajX6sxWdH+Pi+zsH4CR3fTj3l2iDKgxq6JUJF7Ub7nWgsuj9i5aWH88ZIyQ5jNS0"
               )
               .equals(var6)) {
            var8 |= this.setSidebarPanelVisibleTracked(
               var1,
               var2,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "C3E7uC5v15XNR9R7JoecnF8RZw/D0LJu2fD7uUMgMri1uyHjajX6sxWdH+Pi+zsH4CR3fTj3l2iDKgxq6JUJF7Ub7nWgsuj9i5aWH88ZIyQ5jNS0"
               ),
               (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 2) != 0)
            );
         }

         if (var3 != EditorSidebarTab.PROPERTIES
            || !Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "hDOd0CDDqHaE6jOXE4Z8iYuFNs5vcNYYMdACAcYYPYAhV/gCyU5fDjbbadqQtTP33gzWtlWVmmJSrxSOPKJjvsp4EZZnrEm08W1THB/VhQplVg=="
               )
               .equals(var6)) {
            var8 |= this.setSidebarPanelVisibleTracked(
               var1,
               var2,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "hDOd0CDDqHaE6jOXE4Z8iYuFNs5vcNYYMdACAcYYPYAhV/gCyU5fDjbbadqQtTP33gzWtlWVmmJSrxSOPKJjvsp4EZZnrEm08W1THB/VhQplVg=="
               ),
               (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 0) != 0)
            );
         }

         if (var3 != EditorSidebarTab.PROPERTIES
            || !Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nrDCb8u2IRVW7diturNy8OQL5M0gBLm2kAEXT9bUnzlOFP4aKFKJG6We").equals(var6)) {
            var8 |= this.setSidebarPanelVisibleTracked(
               var1,
               var2,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nrDCb8u2IRVW7diturNy8OQL5M0gBLm2kAEXT9bUnzlOFP4aKFKJG6We"),
               (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 18) != 0)
            );
         }

         if (var3 != EditorSidebarTab.DESIGN || !Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dLBTXhefcEPrcSXhmHJD/6fEh6cCmaJ3OfGFi+8wXVIIbIITPC91Jg==").equals(var7)) {
            var8 |= this.setSidebarPanelVisibleTracked(
               var1,
               var2,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dLBTXhefcEPrcSXhmHJD/6fEh6cCmaJ3OfGFi+8wXVIIbIITPC91Jg=="),
               (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2071852781, 22) != 0)
            );
         }

         if (var3 != EditorSidebarTab.DESIGN
            || !Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Tmk6maJJiUdZL41WMUKwQLv8atBVVq1wG1xxoatmbe2RaVLPurbW6tU/ScEB6QIw2CQ=").equals(var7)) {
            var8 |= this.setSidebarPanelVisibleTracked(
               var1,
               var2,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Tmk6maJJiUdZL41WMUKwQLv8atBVVq1wG1xxoatmbe2RaVLPurbW6tU/ScEB6QIw2CQ="),
               (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 15) != 0)
            );
         }

         if (var3 != EditorSidebarTab.LAYERS) {
            var8 |= this.setSidebarPanelVisibleTracked(
               var1,
               var2,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("o429iR3IMzWbbKoUvWl74/Goj1ew0Xzk6LJIkVibIUdWFyBKcTF4vA=="),
               (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 26) != 0)
            );
         }
         String var9 = switch (Xqedii_3ana5qf.Xqedii_1xzbiek[var3.ordinal()]) {
            case 1 -> var6;
            case 2 -> var7;
            case 3 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("o429iR3IMzWbbKoUvWl74/Goj1ew0Xzk6LJIkVibIUdWFyBKcTF4vA==");
            default -> throw new MatchException(null, null);
         };
         if (!var4) {
            var8 |= this.setSidebarPanelVisibleTracked(var1, var2, var9, (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1427960028, 8) != 0));
            if (var8) {
               this.updateEditorPropertiesSidebar(var1, var2);
            }
         } else {
            Xqedii_38o2zes.Xqedii_2sj63w1(this.plugin, var1, () -> {
               if ((2129973394 ^ 2129973394) != 0) {
                  throw new IllegalStateException();
               } else if (var1.isOnline()) {
                  EditorSession var4x = (EditorSession)this.editorSessions.get(var1.getUniqueId());
                  if (var4x == var2 && var2.rightSidebarPanelTransitionToken == var5) {
                     if (var2.editMode && var2.previewMode) {
                        EditorSidebarTab var5x = var2.rightSidebarTab == null ? EditorSidebarTab.LAYERS : var2.rightSidebarTab;
                        String var6x = this.resolvePropertiesTabPanelId(var2);
                        String var7x = this.resolveDesignTabPanelId(var2);

                        String var8x = switch (Xqedii_3ana5qf.Xqedii_1xzbiek[var5x.ordinal()]) {
                           case 1 -> var6x;
                           case 2 -> var7x;
                           case 3 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("o429iR3IMzWbbKoUvWl74/Goj1ew0Xzk6LJIkVibIUdWFyBKcTF4vA==");
                           default -> throw new MatchException(null, null);
                        };
                        this.beginShellOpacityBatch(var2);

                        try {
                           boolean var9x = this.setSidebarPanelVisibleTracked(var1, var2, var8x, (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-492247162, 3) != 0));
                           if (var9x) {
                              this.updateEditorPropertiesSidebar(var1, var2);
                           }
                        } finally {
                           this.endShellOpacityBatch(var1, var2);
                        }
                     }
                  }
               }
            }, Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(244950494794217640L, 46));
         }
      }
   }

   public void openColorPickerForSlot(Player var1, EditorSession var2, int var3) {
      if (((-518416572 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         var2.colorPickerSlot = var3;
         String var4 = var3 == Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1195128597, 14) ? var2.editorColor1 : var2.editorColor2;
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221207, 31)];
         var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1217668113, 9)] = var4;
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(485547093, 16)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
            "C+852nfKg74J9IxxMl6Vi+EbG1EnpZVNBgPAGWI4a4FtcTSQ4F7jvA=="
         );
         var4 = this.firstNonBlank(var10001);
         boolean var5 = var2.colorPickerVisible;
         var2.colorPickerVisible = (Xqedii_2ze73vg.Xqedii_18u831nn38lo(607479382, 30) != 0);
         if (!var5) {
            this.beginShellOpacityBatch(var2);

            try {
               this.setSidebarPanelVisible(
                  var1,
                  var2,
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6+efmR6mabscwEG9NImK9ODLlHtEjA90Y8trT7cJKCP/quEWebPDViXjsqtzOJfp8KgJbaW5T30="),
                  (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-821181103, 20) != 0)
               );
               this.updateSidebarTabVisualState(
                  var1, var2, (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 7) != 0), (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 20) != 0)
               );
            } finally {
               this.endShellOpacityBatch(var1, var2);
            }
         }

         this.syncColorPickerToColor(var1, var2, var4);
      }
   }

   public void closeColorPicker(Player var1, EditorSession var2) {
      if (((1420614481 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.colorPickerVisible) {
         var2.colorPickerVisible = (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 13) != 0);
         var2.colorPickerGradDragActive = (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 6) != 0);
         var2.colorPickerHueDragActive = (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1014437191, 22) != 0);
         var2.colorPickerHexInputPending = (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1992146683, 13) != 0);
         this.beginShellOpacityBatch(var2);

         try {
            this.setSidebarPanelVisible(
               var1,
               var2,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6+efmR6mabscwEG9NImK9ODLlHtEjA90Y8trT7cJKCP/quEWebPDViXjsqtzOJfp8KgJbaW5T30="),
               (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-675815570, 18) != 0)
            );
            this.updateSidebarTabVisualState(
               var1, var2, (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1351631139, 19) != 0), (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-152208515, 6) != 0)
            );
         } finally {
            this.endShellOpacityBatch(var1, var2);
         }
      }
   }

   public void syncColorPickerToColor(Player var1, EditorSession var2, String var3) {
      if ((279334096 * 279334096 - 279334096 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isBlank()) {
         String var4 = this.normalizeHexColor(var3);
         if (var4.length() != Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830953474, 15)) {
            var4 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("C+852nfKg74J9IxxMl6Vi+EbG1EnpZVNBgPAGWI4a4FtcTSQ4F7jvA==");
         }

         ColorPickerCoords var5 = this.Xqedii_l6v4fuu.Xqedii_g5n84hy(var4);
         var2.colorPickerHueAbsY = var5.hueAbsY();
         var2.colorPickerGradAbsX = var5.gradAbsX();
         var2.colorPickerGradAbsY = var5.gradAbsY();
         this.moveColorPickerGradientElements(var1, var2, var2.colorPickerGradAbsX, var2.colorPickerGradAbsY);
         this.moveColorPickerHueElements(var1, var2, var2.colorPickerHueAbsY);
         this.updateColorPickerDisplay(var1, var2, var4);
      }
   }

   public void updateColorPickerDisplay(Player var1, EditorSession var2, String var3) {
      if (((1398735847 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         String[] var10002 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-8537250, 28)];
         var10002[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2109431519, 4)] = var3;
         var10002[Xqedii_2ze73vg.Xqedii_18u831nn38lo(607479382, 30)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
            "C+852nfKg74J9IxxMl6Vi+EbG1EnpZVNBgPAGWI4a4FtcTSQ4F7jvA=="
         );
         String var4 = this.normalizeHexColor(this.firstNonBlank(var10002));
         if (var4.length() != Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-100930086, 23)) {
            var4 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("C+852nfKg74J9IxxMl6Vi+EbG1EnpZVNBgPAGWI4a4FtcTSQ4F7jvA==");
         }

         String var5 = this.Xqedii_l6v4fuu.Xqedii_27vc477(var2.colorPickerHueAbsY);
         this.setShellText(
            var1,
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("U6nPhmAhRObiNR+oxf1zG6CWzhjXmSW9bUCJUEwm64DiJhZ9jBBXvskwLLwDm7LswXOdLWkj3GzpwLgT+RjNRiFzqUP/LQ=="),
            Xqedii_353j9h79g1j2y(var5)
         );
         this.setShellText(
            var1,
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "cCcQBm+aw1mQJSoXye8Z3uecjmDFt3jfvgbAbXInPGTlA3/oqsSGN8/eXGAcfmC8Ji/Kc8cx6gLz0futN5aFXjVJdlEEf1i4cokzz9SmCKlcIvHhjjU="
            ),
            Xqedii_tma1ota5wrbx(var4)
         );
         String var6 = var2.colorPickerHexInputPending
            ? this.highlightSidebarEditingText(
               var2,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("uya0Eu1gXqgoWFALwtzFftv0/JCavLoI49le3brhhs/E70gq5sJSOULSHC50fY09QYMEwvSZjujn5IlMBF5xe++KrMI="),
               this.resolveEditorPromptDraft(var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LS1+t14VZvpK4Nq4tSNxPVI6QfbRaqikVEy/jghWErxDOQ=="))
            )
            : Xqedii_3ghbtbihrgigj(var4.toUpperCase(Locale.ROOT));
         this.setShellText(
            var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("uya0Eu1gXqgoWFALwtzFftv0/JCavLoI49le3brhhs/E70gq5sJSOULSHC50fY09QYMEwvSZjujn5IlMBF5xe++KrMI="), var6
         );
         this.setShellColor(
            var1,
            var2,
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("hdzrXNgD0UPaFTKBlW9fZqZvh7mNUXwuYbQkUobZwT75ZMy11dSEgqbwqjYYT27kzj2DqqDFsJCdFgFioPnNOZWL0COLg5YO5wc4Gw=="),
            var4
         );
      }
   }

   public void updateColorPickerGradientDrag(Player var1, EditorSession var2, double var3, double var5) {
      if ((-354621571 * -354621571 + -354621571 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         var2.colorPickerGradAbsX = this.Xqedii_l6v4fuu.Xqedii_2eyc1hk(var3);
         var2.colorPickerGradAbsY = this.Xqedii_l6v4fuu.Xqedii_30c6las(var5);
         this.moveColorPickerGradientElements(var1, var2, var2.colorPickerGradAbsX, var2.colorPickerGradAbsY);
         this.applyColorPickerResult(var1, var2);
      }
   }

   public void updateColorPickerHueDrag(Player var1, EditorSession var2, double var3, double var5) {
      if ((-559354073 * -559354073 - -559354073 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         var2.colorPickerHueAbsY = this.Xqedii_l6v4fuu.Xqedii_2whi73a(var5);
         this.moveColorPickerHueElements(var1, var2, var2.colorPickerHueAbsY);
         this.applyColorPickerResult(var1, var2);
      }
   }

   public void moveColorPickerGradientElements(Player var1, EditorSession var2, double var3, double var5) {
      if (((-370956154 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         double var7 = var3 + Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561275168181274L, 36));
         double var9 = var5 + Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699518290357L, 15));
         this.moveShellElement(
            var1,
            var2,
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("wuxUQA4UOKphD+jhAixiWJN8LsdglEowKFb6CIAGB/gxTlTiaauO0pQESLYBBTxpcgfNBCHHjp1dqzOjrKd14RvImmLKmnvYaDydLw=="),
            var7,
            var9,
            Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5903141897960024497L, 52)),
            Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-2162761354766279506L, 21))
         );
         this.moveShellElement(
            var1,
            var2,
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "cCcQBm+aw1mQJSoXye8Z3uecjmDFt3jfvgbAbXInPGTlA3/oqsSGN8/eXGAcfmC8Ji/Kc8cx6gLz0futN5aFXjVJdlEEf1i4cokzz9SmCKlcIvHhjjU="
            ),
            var7,
            var9,
            Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(8141991359471636055L, 20)),
            Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561279475731482L, 34))
         );
         this.moveShellElement(
            var1,
            var2,
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "RiHLr9ci+mV8jrlz2296oILq6KMPFMYIej4U8ZEGSxJZMstQcwLh0gKJ3/DXYAMIgS5q37eIL+iTqBecM+UXBzJAm9rmzR3a/FMdWxPOyZiwwFF9EJMi2D2M"
            ),
            var7,
            var9 + Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699501490609L, 26)),
            Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-356228240937734267L, 32)),
            Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544521930L, 20))
         );
      }
   }

   public void moveColorPickerHueElements(Player var1, EditorSession var2, double var3) {
      if (((1504033601 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         double var5 = var3 + Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(3919489116806285952L, 50));
         this.moveShellElement(
            var1,
            var2,
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("imikLhpu00M8CBqY//pETthIG5+A6ypIM5uca0d2G9z5dOvGTyWj1SaKhsTkhgC/0dqp3763M1U78nEm+7kOtgg+Fe+wVWO+SE71jA=="),
            Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-137405176484047301L, 16)),
            var5,
            Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(33069117211894379L, 3)),
            Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5906538289378224561L, 54))
         );
         this.moveShellElement(
            var1,
            var2,
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "iYFFxIRuRphW3BX5zp7qSFB5Vi9nlrNTNbhxvIZ6IjD3kw9y4BKbujQ8ON4rMEfHVWxH5sP2BIBI/mWOQ5U/TeXZJ3bKvJ/1BlMOGtvH0O2T2QonKi0MVI/u"
            ),
            Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(861908095322842623L, 25)),
            var5 + Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5903701274500655537L, 51)),
            Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-3071723269169571137L, 9)),
            Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(-7961051808761328623L, 4))
         );
      }
   }

   public boolean isInsideColorPickerGradientArea(double var1, double var3) {
      if ((294237358 * 294237358 - 294237358 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_l6v4fuu.Xqedii_reufgab(var1, var3);
      }
   }

   public boolean isInsideColorPickerHueArea(double var1, double var3) {
      if ((-1647584566 ^ -1647584566) != 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_l6v4fuu.Xqedii_3w514cm(var1, var3);
      }
   }

   public void applyColorPickerResult(Player var1, EditorSession var2) {
      if (((1934472907 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         String var3 = this.computeColorFromPickerIndicators(var2);
         if (var2.colorPickerSlot == Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 5)) {
            var2.editorColor1 = var3;
         } else {
            var2.editorColor2 = var3;
         }

         this.updateColorPickerDisplay(var1, var2, var3);
         this.setShellColor(
            var1,
            var2,
            var2.colorPickerSlot == Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221190, 4)
               ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dZeuVOMGyPOiH0WGkLv0oEg/zkVRrV9BOGRAVVNvybF8/ehg9fM1wjcxlRP4HPnETp8ZBOS5eD4=")
               : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZsdWq9E+CHoMzcApJzv1op6hgcbg/WFGpHDnrOZd2ZCkO2gyXknoiZXa7yzxBfq2t9dmsWyZiF4="),
            var3
         );
         this.notifyColorPickerColorsChanged(var1, var2);
      }
   }

   public String computeColorFromPickerIndicators(EditorSession var1) {
      if ((1030999091 ^ 1030999091) != 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_l6v4fuu.Xqedii_orkmhot(var1);
      }
   }

   public void notifyColorPickerColorsChanged(Player var1, EditorSession var2) {
      if ((212271040 * 212271040 + 212271040 & 1) != 0) {
         throw new IllegalStateException();
      }
   }

   public double[] hexToHsv(String var1) {
      if ((1692840210 * 1692840210 + 1692840210 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 19)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 10)] = var1;
         return Xqedii_170zfgx.Xqedii_3c7y13v(this.firstNonBlank(var10001));
      }
   }

   public String hsvToHex(double var1, double var3, double var5) {
      if ((-635920539 * -635920539 - -635920539 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return Xqedii_170zfgx.Xqedii_1273r4j(var1, var3, var5);
      }
   }

   public boolean handleSidebarClick(Player var1, EditorSession var2, double var3, double var5) {
      if ((358418173 * 358418173 + 358418173 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var2 == null || !var2.editMode || !var2.previewMode) {
         return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-76104258, 5) != 0);
      } else if (!this.hasLegacySidebarUi(var2)) {
         return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1968988613, 5) != 0);
      } else {
         if (var2.colorPickerVisible
            && !this.isInsideShellBlock(
               var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6+efmR6mabscwEG9NImK9ODLlHtEjA90Y8trT7cJKCP/quEWebPDViXjsqtzOJfp8KgJbaW5T30="), var3, var5
            )) {
            this.closeColorPicker(var1, var2);
         }

         if (this.handleAnimationTimelineClick(var1, var2, var3, var5)) {
            return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1689609814, 23) != 0);
         } else if (this.isInsideShellBlock(
            var2,
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("HiyzeOvgqmpub8QCeWEoPoZ5LVbvPM34EKxXZyL4ovPxWQ1bEImusfrt/GvBwDvAMvECDQhs7ORj1ruaDmBQ/6yAmp/VLg=="),
            var3,
            var5
         )) {
            this.openColorPickerForSlot(var1, var2, Xqedii_2ze73vg.Xqedii_18u831nn38lo(-466262442, 31));
            return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(2020485774, 23) != 0);
         } else if (this.isInsideShellBlock(
            var2,
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("BhJTC1nhiZ5CRR12sxFqg+fXqrk5xk4SOkKihS9iuERUKkBBxZ+5NXAfAaeUcg3swrQ0pNQZJanx53wztdOX/rqYdyXZVw=="),
            var3,
            var5
         )) {
            this.openColorPickerForSlot(var1, var2, Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-304416773, 7));
            return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1685415510, 22) != 0);
         } else if (this.isInsideShellBlock(
            var2,
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("o7JMgftWw1LfcUafLwGQCq6Tk0TE4GR8ZGH7F2Ii52UaYtuHBCj831OTvOxKwt3UbgXyV1TzLJSJm2FaecOrzrIGBCFbi0n2q4lQN0+x1rc="),
            var3,
            var5
         )) {
            String var15 = var2.editorColor1;
            var2.editorColor1 = var2.editorColor2;
            var2.editorColor2 = var15;
            this.setShellColor(
               var1,
               var2,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dZeuVOMGyPOiH0WGkLv0oEg/zkVRrV9BOGRAVVNvybF8/ehg9fM1wjcxlRP4HPnETp8ZBOS5eD4="),
               var2.editorColor1
            );
            this.setShellColor(
               var1,
               var2,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZsdWq9E+CHoMzcApJzv1op6hgcbg/WFGpHDnrOZd2ZCkO2gyXknoiZXa7yzxBfq2t9dmsWyZiF4="),
               var2.editorColor2
            );
            this.notifyColorPickerColorsChanged(var1, var2);
            this.playEditorSfx(var1, var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("U4JGae+tbRMFpfjI7xl0SE1bB8BZYV2EJ+4tW5YolmaMNV4zXqCy0H8pa/42GU4qycBQBOuolgQ="));
            return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1217667601, 9) != 0);
         } else if (var2.colorPickerVisible) {
            if (this.isInsideShellBlock(
               var2,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("EdZl7Mfdr+VS3/IXXtdso8dfcRirxTfDXXmjqtJqMOEVttQiP7GJlztcfeYbMPYPXzGVbVX7cM09Zm4bwTRlxXuKEkphCYiec/V5S3vG"),
               var3,
               var5
            )) {
               this.setActiveEditorTool(var1, var2, EditorTool.COLOR_PICKER);
               return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1144350294, 29) != 0);
            } else if (!this.isInsideShellBlock(
               var2,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("AYCC9Hihq4nsQBkAJn1nSgoFteGHublqLntceGLJhb/Ck8oY2num9cC1++wlLOm8E8ECabNlJoltBRfJxIsonN6ZCa0kGieqHymrMQ=="),
               var3,
               var5
            )) {
               if (!this.isInsideShellBlock(
                     var2,
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                        "RiHLr9ci+mV8jrlz2296oILq6KMPFMYIej4U8ZEGSxJZMstQcwLh0gKJ3/DXYAMIgS5q37eIL+iTqBecM+UXBzJAm9rmzR3a/FMdWxPOyZiwwFF9EJMi2D2M"
                     ),
                     var3,
                     var5
                  )
                  && !this.isInsideColorPickerGradientArea(var3, var5)) {
                  if (!this.isInsideShellBlock(
                        var2,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                           "iYFFxIRuRphW3BX5zp7qSFB5Vi9nlrNTNbhxvIZ6IjD3kw9y4BKbujQ8ON4rMEfHVWxH5sP2BIBI/mWOQ5U/TeXZJ3bKvJ/1BlMOGtvH0O2T2QonKi0MVI/u"
                        ),
                        var3,
                        var5
                     )
                     && !this.isInsideColorPickerHueArea(var3, var5)) {
                     return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 16) != 0);
                  } else {
                     var2.colorPickerHueDragActive = (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-76104226, 5) != 0);
                     this.updateColorPickerHueDrag(var1, var2, var3, var5);
                     return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-571492501, 25) != 0);
                  }
               } else {
                  var2.colorPickerGradDragActive = (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 30) != 0);
                  this.updateColorPickerGradientDrag(var1, var2, var3, var5);
                  return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-546383941, 2) != 0);
               }
            } else if (this.isEditorTextPromptCoolingDown(var2)) {
               return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-546383941, 2) != 0);
            } else {
               var2.pendingActionTargetId = null;
               var2.pendingActionIndex = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-897964035, 26);
               var2.colorPickerHexInputPending = (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(971094186, 17) != 0);
               String var14 = var2.colorPickerSlot == Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-136595986, 0) ? var2.editorColor1 : var2.editorColor2;
               this.updateColorPickerDisplay(var1, var2, var14);
               if (!this.openEditorTextPrompt(
                  var1, var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("PwLSjZ1GDsDHHJM2poh84e1AduQ1+H1+SR7qEDkMXuq4tqKM5zenDD8Q2m+ltA=="), var14
               )) {
                  String var10002 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                     "5ZABo2x8qYHdnzE0M4mvj3c6JxiQg4WucLL5X0K72Y/Y5wSsA/v9kMiAxzsPiVAFqf6GBy//aIcvi/PjcGYuTo6STkvy9Ssxs071UUAekqXAemra9bfI0/emijTUVtjfeYm10SqXcEKskCXb"
                  );
                  Object[] var10003 = new Object[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 16)];
                  var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 15)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                     "e4AJBvpBvlH6eQvu700rMgS0sOKzJLo73nt7eG++CnYnOTqAZ3Prdg=="
                  );
                  this.sendEditorInfo(var1, var10002, var10003);
               }

               return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 22) != 0);
            }
         } else {
            EditorPropertyField var7 = this.resolveSidebarValueHitboxField(var2, var3, var5);
            if (var7 != null) {
               if (var7 == EditorPropertyField.VISIBLE) {
                  this.toggleSidebarSelectionVisibility(var1, var2);
                  return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(-466262442, 31) != 0);
               } else if (var7 == EditorPropertyField.LOCKED) {
                  this.toggleSidebarSelectionLocked(var1, var2);
                  return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1815438934, 27) != 0);
               } else if (var7 == EditorPropertyField.ANCHOR) {
                  this.cycleSidebarSelectionAnchor(var1, var2);
                  return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-608833801, 8) != 0);
               } else if (var7 == EditorPropertyField.ITEM_DESIGN_GLOWING) {
                  this.toggleSidebarItemDesignGlowing(var1, var2);
                  return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221207, 0) != 0);
               } else {
                  if (this.isDimensionSidebarField(var7)) {
                     this.startSidebarFieldDrag(var1, var2, var7, var3);
                  } else {
                     this.startSidebarInputPrompt(var1, var2, var7);
                  }

                  return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 12) != 0);
               }
            } else if (this.handleKeyframeInterpSidebarClick(var1, var2, var3, var5)) {
               return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(485547093, 16) != 0);
            } else {
               boolean var8 = this.isInsideShellBlock(
                  var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("yZaKwA5SBtBc8wzcraLlL9/PCsHVTR1ap6VSY+Vxef05aT2yxJ7KatU173rV3vwRfLtC7490JzeHvSEd9rg="), var3, var5
               );
               boolean var9 = this.isInsideShellBlock(
                  var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LS5FDBAH9o1Gly8uyCMRz18locvp5848O1hQKQL3EsDSj/HNp6C1ciYdyT8D3fVZCElUbhZp"), var3, var5
               );
               boolean var10 = this.isInsideSidebarLayersHitbox(var2, var3, var5);
               if (var2.rightSidebarTab == EditorSidebarTab.PROPERTIES
                  && this.isActionsSidebarMode(var2)
                  && !var8
                  && !var9
                  && !var10
                  && this.handleActionsPanelClick(var1, var2, var3, var5)) {
                  return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 27) != 0);
               } else if (var8) {
                  this.finishLayersReorderDrag(var1, var2, (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2113334651, 30) != 0));
                  this.setRightSidebarTab(var1, var2, EditorSidebarTab.PROPERTIES, (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1107890821, 30) != 0));
                  return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-136595986, 0) != 0);
               } else if (var9) {
                  this.finishLayersReorderDrag(var1, var2, (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-546383945, 2) != 0));
                  this.setRightSidebarTab(var1, var2, EditorSidebarTab.DESIGN, (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1901360067, 2) != 0));
                  return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 0) != 0);
               } else if (var10) {
                  this.setRightSidebarTab(var1, var2, EditorSidebarTab.LAYERS, (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-675553426, 18) != 0));
                  return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2076047085, 22) != 0);
               } else if (var2.rightSidebarTab == EditorSidebarTab.LAYERS) {
                  return this.handleLayersPanelClick(var1, var2, var3, var5);
               } else if (var2.rightSidebarTab == EditorSidebarTab.PROPERTIES && this.isActionsSidebarMode(var2)) {
                  return this.handleActionsPanelClick(var1, var2, var3, var5);
               } else if (var2.rightSidebarTab == EditorSidebarTab.DESIGN) {
                  if (this.isItemDesignSidebarMode(var2)) {
                     if (this.isInsideShellBlock(
                           var2,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                              "H3hpOBp0ahgZHGiW54dO2FfJ0uT00OYb6vUScL2OS1bMrFHLjRFlN1ArFoKlqvLzLRuSxpfKUP2rIjjifGKewC1KM45aIws42Lqdvyf878UKvw=="
                           ),
                           var3,
                           var5
                        )
                        || this.isInsideShellBlock(
                           var2,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                              "3B0fDnJinnRLEUD5j293IQ/+5hyqKXHXQwU+nWQVvulWPKWw937XS2cAe4HyKGB0TgjY6oo8ZvQJhYsbfzVGc91ZboV8WPUZPhdP0OqMhOR+/ytnLOpdbN0yAV4KBGSq"
                           ),
                           var3,
                           var5
                        )) {
                        this.startSidebarInputPrompt(var1, var2, EditorPropertyField.ITEM_DESIGN_ITEM);
                        return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-821181103, 20) != 0);
                     } else if (this.isInsideShellBlock(
                           var2,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                              "ZgR3tY3c0o31DY1RwyfZ4m2s8/ipvnmRc506KLdtJNV53G3YRiDVhxDV6ajbR5YwvnEbqqIqRP67xmHOFiiF28DQsnffjj3RKCVzmk/r0nJ2BJg6E5ioMQ=="
                           ),
                           var3,
                           var5
                        )
                        || this.isInsideShellBlock(
                           var2,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                              "URSxw3iLN1MuHMYsAf/ploW08LUoFvDUuyq9cvwg1n0bbEZz+NfdsvhuMWp2XsqKe0mPTDk8YWvgSvCM4xYT6bI85+zjj2+rxagyBcv1tFkTixNMtrNxvVD9/wMFZb3SQbvF9+OH"
                           ),
                           var3,
                           var5
                        )) {
                        this.toggleSidebarItemDesignGlowing(var1, var2);
                        return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 10) != 0);
                     } else if (!this.isInsideShellBlock(
                           var2,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                              "7WYnv9esM+ZSvamYSZAl7N4Up9PSGx4M/M3vfuvItatOAXSY1nilkIEqXGzY+ERwaaPK5gdk/zeir8ubF/8B6as25+RkyFbF52Yksl+6qbzFi4UZaaM56gV+Qb/9ElDu"
                           ),
                           var3,
                           var5
                        )
                        && !this.isInsideShellBlock(
                           var2,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                              "+bkN8dUm1Hl3bkIb8REjw7zUT8oMRlOElCGJb3kRJEn5vLMDekWLn+fbfuTsMjqTNFbcpGc754/mB9QZxDNzolM9lWog4tX47vVjthvAarPm6937eKsSXCYBpAPjSSGKGe/sVCoBeV32Ipsxcos="
                           ),
                           var3,
                           var5
                        )) {
                        return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 11) != 0);
                     } else {
                        this.cycleSidebarHoverEffect(var1, var2, (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-492247162, 3) != 0));
                        return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1217667601, 9) != 0);
                     }
                  } else if (!this.isTextDesignSidebarMode(var2)
                     || !this.isInsideShellBlock(
                           var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("rcnDcdJkzHJzzIyULy5JTX4+y4RMTPk66IuGIHl8soIpsS5MR9yz4SqB+V881R38NYV/rA=="), var3, var5
                        )
                        && !this.isInsideShellBlock(
                           var2,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                              "KsjT60Szq++wdh5QwMewtRepRvbVjtYa+yug6+yqnGgqCgdt1cet2/y1w3hOsDq6TDGrzfGgpakNSNbLoT7P7FLlK2CcwsIHGaRyhNyadL3wMqcnS5kFkMiR1JnAPRlCFEA="
                           ),
                           var3,
                           var5
                        )) {
                     if (!this.isTextDesignSidebarMode(var2)
                        || !this.isInsideShellBlock(
                              var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("oSbsz1aswTxgAK0yHzZuAcPQqwum08UK3CzQFe4KwOB3hzSU8weEQQSfoDhvAtjuKXvJEw=="), var3, var5
                           )
                           && !this.isInsideShellBlock(
                              var2,
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                 "X0Hb6BwZNUulX3+wca38JMrV31cL1ABDc+YI0/ffszPnE9SPbVPfYBheWO1hRZzkGnA0C+i7XzyZLCSEBSDFR5Psz+JVtRu4T1Tq1q6akwd8aIiXw8hgYMx6"
                              ),
                              var3,
                              var5
                           )
                           && !this.isInsideShellBlock(
                              var2,
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                 "tXSLKtZt3kGrBT7arg2nmtJBXLyLWjMAKeQAGge8FKvRoy5+ZSytXAIVlaALzOVihSt6VeZ/ts9BbfBlBWsOpK0QCbIMtI6Z41P7B7TFpvgM01xbjA+/CFc+Y0ct9pOF5cbqVKWounw="
                              ),
                              var3,
                              var5
                           )) {
                        if (this.isInsideShellBlock(
                              var2,
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                 "gVM43d0Jbf4pdPhP/TWVR+0tnjv0f06+V6srIInnIdMK5lgoI+JTfkiAOjTMTkC9fYBoRx6qylrRIIJHQONyhnGDXGHUyc8L9gjhhd/g/NuXtH2Q15UYgZqYb8vxHhZ90NQ="
                              ),
                              var3,
                              var5
                           )
                           || this.isInsideShellBlock(
                              var2,
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                 "hNUox5PngPxS28evg6XQMw6tyN9zxuK4HobnJyDk4h0OeioZM3qo984XI6LfqQBeD73M0i9Lrx9REQ9cnsBW8Mi052tMllk+mpyrWS7yvoN6Ou5+"
                              ),
                              var3,
                              var5
                           )) {
                           return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1904677334, 15) != 0);
                        } else if (this.isInsideShellBlock(
                              var2,
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                 "XhGODVG7Cw7JT3grUHueiygAPD/YKqaqvx6IdIQvhTUY458dMpca+3nvNS3vFHLPIAgKQZkg8rul4etfgg6aE5MA0HJ+p7ojFlSMEXgcTCa2/2CVxxH+z1EodRU0gvHROrGluc1+/Wk="
                              ),
                              var3,
                              var5
                           )
                           || this.isInsideShellBlock(
                              var2,
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                 "xb9XfCvnnNGkam7kcOabIQAT4ckTHyWtYBO9S2XtSD6LYbNnB14E87zqkAZ72IGANuenaJnLRsRZhcAKD9dCkyCheJgUrK/FgQuyWJAIYNHcx5C3dGz1G8eW"
                              ),
                              var3,
                              var5
                           )) {
                           this.cycleSidebarBorderRadiusMode(var1, var2);
                           return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221202, 2) != 0);
                        } else if (this.isInsideShellBlock(
                              var2,
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                 "orkq97XSKv+/cJnFzKT0OB21j25F6AxXAAD9MPhHosf/Q7v+GXkmfuLbiYb3DJiacYhcXwhHJH2t6tweR0z7b7h8Wf//HS6bU7BPe/FzLOX8jeV+o9sVSg/JxxnWhJRVnkzTU3Z9"
                              ),
                              var3,
                              var5
                           )
                           || this.isInsideShellBlock(
                              var2,
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                 "SeL024X9txyfSjgpynUEVZ9XA5UqwQ2tG0A1kUc13HQmnSC1lhiTKgUEBTLP+yGZ7QgOz+2BgbjAiYcQ2Ifzy/N5HZro6/nZ405zIE6clmKIp2PyMrtQ1w=="
                              ),
                              var3,
                              var5
                           )) {
                           this.cycleSidebarHoverEffect(var1, var2, (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-984494307, 4) != 0));
                           return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-273191971, 1) != 0);
                        } else if (this.isInsideShellBlock(
                              var2,
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                 "YgWvT3Pf+ZuO+Dt1PsvErfZARhV2eQxUWZc7xKar3IfG7SmjM7qI5hEYNdiKWxFTol3ArG1GIFOhjpw9louwSClHpn+JjRL5oajFEzxEP+HiYlbri4d5c352nvI8lex/SAOyLw=="
                              ),
                              var3,
                              var5
                           )
                           || this.isInsideShellBlock(
                              var2,
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                                 "84q2Hep1WN2wom4zXFe3nR2YlSQ/lq8WxmXIcfKr2UU3RRR/IY6kLPzosrtw9l+U2Au0+ORi7USv/WthhbT5CfTFL75HjWG2V7bBniBHa9IpQHUjMXU="
                              ),
                              var3,
                              var5
                           )) {
                           this.startSidebarInputPrompt(var1, var2, EditorPropertyField.DESIGN_HOVER_COLOR);
                           return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 23) != 0);
                        } else if (this.isInsideShellBlock(
                           var2,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                              "ju4CVu6BNA3Z07odV/jE9v4BERvi0SlzaKbcnjo8Kgv739g/2CvhshZrZs052X5WB1NHPrywu8IQb3N3TvC0LovpjMkRrTekCTOIZ3P+tZu1xkiO"
                           ),
                           var3,
                           var5
                        )) {
                           this.startSidebarFieldDrag(var1, var2, EditorPropertyField.DESIGN_COLOR, var3);
                           return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-304416901, 7) != 0);
                        } else if (this.isInsideShellBlock(
                           var2,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                              "lQBuKF+UFD9bJzI1hObl+KnNS7M0kAPq/wG/g7jHOnn9Lj9UW2bSwHQ5wn7PjT7liGURvxsPJxslDrH7wOJ7JEi9GGDiXv+sSWzbz8n35jzO4VPJ5Z3p+A=="
                           ),
                           var3,
                           var5
                        )) {
                           this.startSidebarFieldDrag(var1, var2, EditorPropertyField.OPACITY, var3);
                           return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 21) != 0);
                        } else if (this.isInsideShellBlock(
                           var2,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                              "1aR4buwAz4GnvxTRQTy/mTLvPdWeY4/jbFSazWeBr/p2nyr0Wr9SZBwZo6SYLmAoZCUi/qPL0A/PblalmkSQoHHZbEH56SOGIraJb4qF0Po9Vs+b2vqAcg=="
                           ),
                           var3,
                           var5
                        )) {
                           this.startSidebarFieldDrag(var1, var2, EditorPropertyField.DESIGN_BORDER_COLOR, var3);
                           return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 29) != 0);
                        } else {
                           return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 29) != 0);
                        }
                     } else {
                        this.startSidebarFieldDrag(var1, var2, EditorPropertyField.DESIGN_TEXT_WRAP, var3);
                        return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(231035343, 28) != 0);
                     }
                  } else {
                     this.toggleSidebarTextAlignment(var1, var2);
                     return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 28) != 0);
                  }
               } else if (var2.rightSidebarTab != EditorSidebarTab.PROPERTIES) {
                  return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 7) != 0);
               } else if (this.isInsideShellBlock(
                  var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("1yOIXiTH75uNb2juZ7gdAzxIV8UqG71PfHo0l1CI6w8MIt/Z99QIozJgsh9sqx2e"), var3, var5
               )) {
                  this.startSidebarFieldDrag(var1, var2, EditorPropertyField.WIDTH, var3);
                  return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 7) != 0);
               } else if (this.isInsideShellBlock(
                  var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("R4ruZdl46Adnd8/Sm16FT1GsWCvPAkO772MSx94BLkrU3Iv/ljlk+wBwmNSiqnea"), var3, var5
               )) {
                  this.startSidebarFieldDrag(var1, var2, EditorPropertyField.HEIGHT, var3);
                  return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681223254, 11) != 0);
               } else if (this.isInsideShellBlock(
                  var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("1epyCKtOrEOGQERu19OHllOTy60iaFbPdU1Qg1WBXcmK8PTd98nCGMB/+RPyXBrZ"), var3, var5
               )) {
                  this.startSidebarFieldDrag(var1, var2, EditorPropertyField.POSITION_X, var3);
                  return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1697998422, 24) != 0);
               } else if (this.isInsideShellBlock(
                  var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LsUb+tv3arNzixpgRSMitsLB8W5Ao/YJu/TpmkwLgzWSGXjMtz8mkRZSdjwHWQkC"), var3, var5
               )) {
                  this.startSidebarFieldDrag(var1, var2, EditorPropertyField.POSITION_Y, var3);
                  return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-142873126, 23) != 0);
               } else if (this.isInsideShellBlock(
                  var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("5XrmeBD+ZrxTcB5BRclNCY7OuV74kHylO70x0DoeIB6mgnh8g0Ayao4/Uj6X1jds"), var3, var5
               )) {
                  this.startSidebarFieldDrag(var1, var2, EditorPropertyField.ROTATION, var3);
                  return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-410590552, 19) != 0);
               } else if (this.isInsideShellBlock(
                  var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("L4R8uy/DgR7qsZE6cDUsOab7ZMjadIEr3nKkYRcQxd42w57Vbl6WlSmW21zdrHrCv9R2rSIqB3G25UA8"), var3, var5
               )) {
                  this.startSidebarFieldDrag(var1, var2, EditorPropertyField.OPACITY, var3);
                  return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1142985001, 26) != 0);
               } else if (this.isInsideShellBlock(
                  var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("rB4qHn7WdUlOzezT4RfOVEeQsV0FzCbxo0YMxKi/G1TWnqmh3otHbw9xlquZ9iO87nSGgKVhWci5hA=="), var3, var5
               )) {
                  String var16 = this.getSidebarEditableTargetId(var2);
                  EditorRect var17 = var16 == null ? null : this.getTargetBounds(var2, var16);
                  EditorRect var18 = this.resolveFlippedBounds(var2, var17, (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221207, 0) != 0));
                  if (this.applyFlipSelection(var2, (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(485547093, 16) != 0))) {
                     this.applySidebarFlipVisualMove(var1, var2, var16, var17, var18);
                  }

                  this.updateEditorPropertiesSidebar(var1, var2);
                  return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1439047240, 9) != 0);
               } else if (this.isInsideShellBlock(
                  var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("QI0enQ/vnH1AZTSTxX7c5reo/VSgj16lY6ASQi35aI+KAltUO/d0i871JMK7us6K9JJsca39H1FfMw=="), var3, var5
               )) {
                  String var11 = this.getSidebarEditableTargetId(var2);
                  EditorRect var12 = var11 == null ? null : this.getTargetBounds(var2, var11);
                  EditorRect var13 = this.resolveFlippedBounds(var2, var12, (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-492247154, 3) != 0));
                  if (this.applyFlipSelection(var2, (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 29) != 0))) {
                     this.applySidebarFlipVisualMove(var1, var2, var11, var12, var13);
                  }

                  this.updateEditorPropertiesSidebar(var1, var2);
                  return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 10) != 0);
               } else if (!this.isInsideShellBlock(
                     var2,
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("mgdL+z1tLVj3Iwm6Cca4EyTXtErtPJy8d3ngpnURTOSHlY0erLmY5ZrQwa9fSaAyDHDWA+o1PIqkxZ4+0MFWXioiWX4="),
                     var3,
                     var5
                  )
                  && !this.isInsideShellBlock(
                     var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("r90V0RFIMf5iidoh4OODRrWi2QSLYudTZptR4qXllAkYQe2BcMn07bnnMiPNrdMhBUBItpfOgO4="), var3, var5
                  )) {
                  return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 15) != 0);
               } else {
                  if (this.getSidebarEditableTargetId(var2) != null) {
                     var2.pendingPropertyField = null;
                     this.stopOpacitySliderDrag(var2, (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1427960028, 8) != 0));
                     this.resetSidebarFieldDrag(var1, var2);
                     var2.opacitySliderDragActive = (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-285746251, 24) != 0);
                     var2.opacitySliderLastValue = null;
                     var2.sidebarOpacityRefreshTriggered = (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 12) != 0);
                     this.beginOpacitySliderDragHistory(var2);
                     this.updateOpacitySliderDrag(var1, var2, var3);
                  }

                  return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1107890821, 30) != 0);
               }
            }
         }
      }
   }

   public boolean shouldSkipShellBlockForSpawnRetoggle(EditorSession var1, String var2) {
      if ((-1118463122 * -1118463122 + -1118463122 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return (var1 != null && var1.hiddenLayerTargetIds.contains(this.shellSpawnOpacityRetoggleTargetId(var2)));
      }
   }

   public String resolveDesignTextSettingsInvocationPath(EditorSession var1) {
      if ((2042987324 ^ 2042987324) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      } else {
         ConfigurationSection var2 = this.findShellBlockSection(
            var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("f6AiMw3McftGQY8Mbc8+r7Z1WPMdkbzsgBYgb0JU+w2x2/xYfVOmmp/pVs0X21oXdCHcSYpy")
         );
         String[] var10002 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(-466262442, 31)];
         var10002[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 2)] = var2 == null
            ? null
            : var2.getString(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts="));
         String var3 = this.normalizeComponentInvocationPath(this.firstNonBlank(var10002));
         if (!var3.isBlank()) {
            return var3;
         } else {
            ConfigurationSection var4 = this.findShellBlockSection(
               var1,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "XgSVErNijxuiznvORmqJi1ocHzvTE7S/ZHjP/c3w5qGbVUAb5/WfFEVTX23YQMp09W7A7CpwHLQvom5DG+GfQgmQPicOreOa7WooiyOHa+Wn8sxf"
               )
            );
            var10002 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 18)];
            var10002[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 20)] = var4 == null
               ? null
               : var4.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
               );
            return this.normalizeComponentInvocationPath(this.firstNonBlank(var10002));
         }
      }
   }

   public void setShellBlockTreeVisibility(Player var1, EditorSession var2, String var3, boolean var4) {
      if ((-227180274 * -227180274 - -227180274 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isBlank() && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         ConfigurationSection var5 = this.findShellBlockSection(var2, var3);
         if (var5 == null) {
            this.setShellOpacity(var1, var3, var4 ? Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(913692674, 19) : Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-605046933, 25));
         } else {
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 31)];
            var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(2028874382, 23)] = var5.getString(
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
            );
            String var6 = this.firstNonBlank(var10001);
            var6 = this.normalizeComponentInvocationPath(var6);
            if (var6.isBlank() && Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("f6AiMw3McftGQY8Mbc8+r7Z1WPMdkbzsgBYgb0JU+w2x2/xYfVOmmp/pVs0X21oXdCHcSYpy").equals(var3)) {
               var6 = this.resolveDesignTextSettingsInvocationPath(var2);
            }

            if (var6.isBlank()) {
               this.setShellOpacity(
                  var1,
                  var3,
                  var4 ? this.readOpacity(var5, Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1225984037, 16)) : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(2028874382, 23)
               );
            } else {
               HashMap var7 = new HashMap();
               int var8 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1196803613, 1);
               int var9 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 6);

               for (Map var11 : var2.shellBlocks) {
                  var9++;
                  ConfigurationSection var12 = this.mapToSection(var11);
                  if (var12 != null) {
                     var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 29)];
                     var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 16)] = var12.getString(
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
                     );
                     String var13 = this.firstNonBlank(var10001);
                     if (this.belongsToSidebarPanel(var6, var13)) {
                        String var14 = this.resolveElementId(var12, var9, var7);
                        if (!var14.isBlank()) {
                           var8 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221214, 3);
                           int var15 = var4
                              ? this.readOpacity(var12, Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1740561847, 27))
                              : Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1090816323, 29);
                           this.setShellOpacity(var1, var14, var15);
                        }
                     }
                  }
               }

               if (var8 == 0) {
                  this.setShellOpacity(
                     var1,
                     var3,
                     var4 ? this.readOpacity(var5, Xqedii_2ze73vg.Xqedii_18u831nn38lo(1682630230, 15)) : Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 17)
                  );
               }
            }
         }
      }
   }

   public String normalizeComponentInvocationPath(String var1) {
      if ((-1596895331 * -1596895331 - -1596895331 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-310657546, 14)];
         var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 12)] = var1;
         String var2 = this.firstNonBlank(var10001);
         if (var2.isBlank()) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
         } else {
            int var3 = var2.indexOf(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("R3l9sYOMP2Q3ME3eQ2L7q2EDLpjfVJCOTsTcLEKwibRiIWu+ZJ9glIWQaqRC1x36Qao="));
            if (var3 < 0 && var2.endsWith(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lkdT5TaomtvEcTg357s4M4JmdbxiOnKGNEm7IzgJMz+LJmCuuyrR6zPeRxaUxqzs"))) {
               var3 = var2.length() - Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lkdT5TaomtvEcTg357s4M4JmdbxiOnKGNEm7IzgJMz+LJmCuuyrR6zPeRxaUxqzs").length();
            }

            if (var3 >= 0) {
               var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(713980014, 7)];
               var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1874779567, 27)] = var2.substring(Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 22), var3);
               return this.firstNonBlank(var10001);
            } else {
               return var2;
            }
         }
      }
   }

   public String toSidebarDisplayValue(String var1) {
      if ((1206676080 * 1206676080 + 1206676080 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-299200903, 31)];
         var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-151261734, 23)] = var1;
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1942188372, 18)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("I62rfw30pge96bThRU3YSmK6U1KxwpgJtXFIL2IdiovQ7w==");
         String var2 = this.firstNonBlank(var10001);
         if (var2.length() <= Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855200, 1)) {
            return var2;
         } else {
            int var3 = Math.max(Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 31), Math.min(Xqedii_2ze73vg.Xqedii_18u831nn38lo(540370518, 26), var2.length()));
            return Xqedii_a4ki2jg8ojd9(var2.substring(Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 29), var3));
         }
      }
   }

   public String resolveEditorPromptDraft(EditorSession var1, String var2) {
      if ((-1754327598 ^ -1754327598) != 0) {
         throw new IllegalStateException();
      } else {
         String var3 = var1 == null ? null : var1.editorTextPromptDraft;
         if (var3 != null && !var3.isBlank()) {
            if (var3.length() <= Xqedii_2ze73vg.Xqedii_18u831nn38lo(1678861910, 17)) {
               return var3;
            } else {
               int var4 = Math.max(
                  Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 9), Math.min(Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1342718243, 19), var3.length())
               );
               return Xqedii_srnc7rrajczt(var3.substring(var3.length() - var4));
            }
         } else {
            return var2;
         }
      }
   }

   public void setSidebarValueText(Player var1, String var2, String var3) {
      if ((2042225725 * 2042225725 - 2042225725 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         EditorSession var4 = var1 == null ? null : (EditorSession)this.editorSessions.get(var1.getUniqueId());
         if (var4 != null && var2 != null) {
            var4.sidebarRawValues.put(var2, var3 == null ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==") : var3);
         }

         this.setShellText(var1, var2, this.toSidebarDisplayValue(var3));
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public String readSidebarFieldRawValue(EditorSession var1, EditorPropertyField var2) {
      if ((1249453337 * 1249453337 - 1249453337 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         String var3 = switch (Xqedii_3ana5qf.Xqedii_32fmpb0[var2.ordinal()]) {
            case 1 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("eKU/4NAtPPGnIuy4+2kDWxuKju2nfoWUZAFRLSVMwC9qt2FjNBDAIVqGlgVYgnmhYt0NpOecJNWPrA==");
            case 2 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("XzZZJoPcDRPsq7+GHd5ru9Xucjkycyk6TQr1wdK2H2v6BXfPYsh4jvS2IwQ2hptkAoOi942j");
            case 3 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("YNkIeSu3M3+wC8Kri3vuSwGdlSt+zQk/5Wlql/rWpN/1j3GRenB6si8ZTCudLCeE82p4sPqIlD1OYaHw52Q/kQ==");
            case 4 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6YIKj93VeW6GHrkPq8mk3ANMG7lkgJMwOeDaUyNF1M5etEe1Ane2ej3/dr6St1HjqgdYoU/U/bLi1Medpr4=");
            case 5 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GlFScvp2SawV4ojxtO5Q+TnMBml6bCm/JHoN6cvSI2bELmn0zI9OwQHtKBfYCqQ+08j5B3TH+6EQreF0Me8=");
            case 6 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("kCTVkMkWgVPSl7kwdINGQ2D5Dyu2gGUOQghuOL+Vn42wgX3MDnKsz1oX07x+Q2lvB7DTjw==");
            case 7 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("rbC7FHog0G3F2ZwxmzWTRvT4bFygYH68iLdELdUP+0+z3vBRiTp5GWSayuM/k0nJJLfgLQ==");
            case 8 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("2GUyh4XDn9HhA/KgCSBWL+jzW/hT0mVGGlmOuo8trgmN2jGM42fcC/2QabAglczg4+XSag==");
            case 9 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("pwbb1DhYCKWGtWLgScf/elztdxWarQYULJFAmcvkNR0Hi4/IasPnyn7xa47TWT7+aA/xtA==");
            case 10 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("+PYMxlo93J6oBiaCSocir8K3MGjS+qFqFujDqA5K03emu+VHDu8KTkh7M6nhuT05HkVb+A==");
            case 11 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("zD85LYQBqTn9dG/TGyHumlDnx9S4H0kt9NTTs3bF90MZ54Gi5R47RBLteNmihX16JyG5U0/Z3cg0i9Kn");
            case 12 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("/QC0IOzyJbYp6DfDH/G1c0msRVj5u5MUmlSvR6LavN+jm/6uN4bF71seSGppHqopeu1qM+D0AVEGMzBGicWhNQ==");
            case 13 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "ju4CVu6BNA3Z07odV/jE9v4BERvi0SlzaKbcnjo8Kgv739g/2CvhshZrZs052X5WB1NHPrywu8IQb3N3TvC0LovpjMkRrTekCTOIZ3P+tZu1xkiO"
            );
            case 14 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "1aR4buwAz4GnvxTRQTy/mTLvPdWeY4/jbFSazWeBr/p2nyr0Wr9SZBwZo6SYLmAoZCUi/qPL0A/PblalmkSQoHHZbEH56SOGIraJb4qF0Po9Vs+b2vqAcg=="
            );
            case 15 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "84q2Hep1WN2wom4zXFe3nR2YlSQ/lq8WxmXIcfKr2UU3RRR/IY6kLPzosrtw9l+U2Au0+ORi7USv/WthhbT5CfTFL75HjWG2V7bBniBHa9IpQHUjMXU="
            );
            case 16 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "X0Hb6BwZNUulX3+wca38JMrV31cL1ABDc+YI0/ffszPnE9SPbVPfYBheWO1hRZzkGnA0C+i7XzyZLCSEBSDFR5Psz+JVtRu4T1Tq1q6akwd8aIiXw8hgYMx6"
            );
            case 17 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "H3hpOBp0ahgZHGiW54dO2FfJ0uT00OYb6vUScL2OS1bMrFHLjRFlN1ArFoKlqvLzLRuSxpfKUP2rIjjifGKewC1KM45aIws42Lqdvyf878UKvw=="
            );
            case 18 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "ZgR3tY3c0o31DY1RwyfZ4m2s8/ipvnmRc506KLdtJNV53G3YRiDVhxDV6ajbR5YwvnEbqqIqRP67xmHOFiiF28DQsnffjj3RKCVzmk/r0nJ2BJg6E5ioMQ=="
            );
            default -> throw new MatchException(null, null);
         };
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 8)];
         var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 25)] = (String)var1.sidebarRawValues.get(var3);
         String var4 = this.firstNonBlank(var10001).trim();
         return !var4.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("I62rfw30pge96bThRU3YSmK6U1KxwpgJtXFIL2IdiovQ7w=="))
               && !var4.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("eqNAehaXOFYJ2ofSF83q5aLzYRAcGJIaJNQxpEvBG5dhnRrfP+g="))
               && !var4.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("By5q5besaxSFT9aOarh+VnLDM10y2NZAT30OXaYpHiq4ZAKe"))
               && !var4.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("I62rfw30pge96bThRU3YSmK6U1KxwpgJtXFIL2IdiovQ7w=="))
            ? this.stripSidebarUnitSuffix(var2, var4)
            : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      } else {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      }
   }

   public String stripSidebarUnitSuffix(EditorPropertyField var1, String var2) {
      if (((1489478452 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 6)];
         var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 27)] = var2;
         String var3 = this.firstNonBlank(var10001).trim();
         if (var1 != null && !var3.isEmpty()) {
            String[] var4 = this.sidebarFieldUnitSuffixes(var1);
            int var5 = var4.length;

            for (int var6 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-621347859, 15); var6 < var5; var6++) {
               String var7 = var4[var6];
               if (var3.length() > var7.length() && var3.toLowerCase(Locale.ROOT).endsWith(var7)) {
                  return var3.substring(Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1904677334, 15), var3.length() - var7.length()).trim();
               }
            }

            return var3;
         } else {
            return var3;
         }
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public String[] sidebarFieldUnitSuffixes(EditorPropertyField var1) {
      if ((-250397495 * -250397495 - -250397495 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1090816323, 29)];
      } else {
         String[] var10000;
         switch (Xqedii_3ana5qf.Xqedii_32fmpb0[var1.ordinal()]) {
            case 6:
            case 7:
            case 8:
            case 9:
               var10000 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(485547093, 16)];
               var10000[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 27)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("+uR3xIXN4lOaGO5OAxuq2kzaw9D3l4ipOxhHaAQEIog=");
               break;
            case 10:
               var10000 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830863362, 13)];
               var10000[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 9)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("fpKP3GJKnhj4fHzuhHvC2YU9ggBPeTbEcEre9YfL");
               var10000[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221462, 8)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "ND4RTzWwUEj79Y6+Mk56M7opuqr44+qNkabeedG64H0s2w=="
               );
               break;
            case 11:
            default:
               var10000 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 7)];
               break;
            case 12:
               var10000 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-507991495, 25)];
               var10000[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 29)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("O1EWNrm0Ht10ZXBde+b0it76MEXqMeCqmpnY03Vr");
         }

         return var10000;
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public void applySidebarPendingInputIndicator(Player var1, EditorSession var2) {
      if (((863358695 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.pendingPropertyField != null) {
         String var3 = this.resolveEditorPromptDraft(
            var2, this.toSidebarDisplayValue(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LS1+t14VZvpK4Nq4tSNxPVI6QfbRaqikVEy/jghWErxDOQ=="))
         );
         switch (Xqedii_3ana5qf.Xqedii_32fmpb0[var2.pendingPropertyField.ordinal()]) {
            case 1:
               String[] var20 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-142873126, 23)];
               var20[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1427960284, 8)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "eKU/4NAtPPGnIuy4+2kDWxuKju2nfoWUZAFRLSVMwC9qt2FjNBDAIVqGlgVYgnmhYt0NpOecJNWPrA=="
               );
               this.setSidebarPendingValue(var1, var3, var20);
               break;
            case 2:
               String[] var19 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-598401808, 0)];
               var19[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-608834057, 8)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "XzZZJoPcDRPsq7+GHd5ru9Xucjkycyk6TQr1wdK2H2v6BXfPYsh4jvS2IwQ2hptkAoOi942j"
               );
               this.setSidebarPendingValue(var1, var3, var19);
               break;
            case 3:
               String[] var18 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221207, 0)];
               var18[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 19)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "YNkIeSu3M3+wC8Kri3vuSwGdlSt+zQk/5Wlql/rWpN/1j3GRenB6si8ZTCudLCeE82p4sPqIlD1OYaHw52Q/kQ=="
               );
               this.setSidebarPendingValue(var1, var3, var18);
               break;
            case 4:
               String[] var17 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(-466262442, 31)];
               var17[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1195144981, 14)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "6YIKj93VeW6GHrkPq8mk3ANMG7lkgJMwOeDaUyNF1M5etEe1Ane2ej3/dr6St1HjqgdYoU/U/bLi1Medpr4="
               );
               this.setSidebarPendingValue(var1, var3, var17);
               break;
            case 5:
               String[] var16 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-253995748, 24)];
               var16[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 17)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "GlFScvp2SawV4ojxtO5Q+TnMBml6bCm/JHoN6cvSI2bELmn0zI9OwQHtKBfYCqQ+08j5B3TH+6EQreF0Me8="
               );
               this.setSidebarPendingValue(var1, var3, var16);
               break;
            case 6:
               String[] var15 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-76104194, 5)];
               var15[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-273191973, 1)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "kCTVkMkWgVPSl7kwdINGQ2D5Dyu2gGUOQghuOL+Vn42wgX3MDnKsz1oX07x+Q2lvB7DTjw=="
               );
               var15[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1142985001, 26)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "nviJdHsZLXHEh01U90XOYN92UPQmDCvje0Ih9Lh/oKO92ETqfvSXoG1bfA4aN9A0SxWKmbSpPOy9rqTU"
               );
               this.setSidebarPendingValue(var1, var3, var15);
               break;
            case 7:
               String[] var14 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855682, 9)];
               var14[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-621347859, 15)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "rbC7FHog0G3F2ZwxmzWTRvT4bFygYH68iLdELdUP+0+z3vBRiTp5GWSayuM/k0nJJLfgLQ=="
               );
               var14[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 8)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "uvenH6xYVP0pOC8m3CgRG3pUtarpgKHXFZ/gCzpBcLc69Lrg5W2FL+op0qpaBhAH2bc71EDHdhjryCdHsIY="
               );
               this.setSidebarPendingValue(var1, var3, var14);
               break;
            case 8:
               String[] var13 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221202, 0)];
               var13[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 12)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "2GUyh4XDn9HhA/KgCSBWL+jzW/hT0mVGGlmOuo8trgmN2jGM42fcC/2QabAglczg4+XSag=="
               );
               var13[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1942188372, 18)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "ClU188mV98i3uVzSOpYabNgNUWngnaProQUh4yWOFYcec+Jv/VKfCiqcfOmxpruStqUP/w=="
               );
               var13[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1427960796, 8)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "pn1iNlf/o1S2pit8MlwnP2S5rkjqxjzBKatVLnuU4pABMo3r/aGXZ9cnQj2WVB+/9/Rt8ASlcvpyW0ErgHQryLViWpYiew=="
               );
               var13[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1372532165, 12)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "QsRld6UD07RhIy8lVjKnzcXD85NrcXuQePF5+qaPeOQE7Ct3cg2sWtclysSX77H9"
               );
               this.setSidebarPendingValue(var1, var3, var13);
               break;
            case 9:
               String[] var12 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1372544453, 12)];
               var12[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 9)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "pwbb1DhYCKWGtWLgScf/elztdxWarQYULJFAmcvkNR0Hi4/IasPnyn7xa47TWT7+aA/xtA=="
               );
               var12[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1680959062, 18)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "3lpeAsCdTPSL9JFLIMzzN8JhNF2fy28rdNxUVfxqWpQ/zWOK+d5a1Ubjy5+g/pbtMPc4iA=="
               );
               var12[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(822466562, 23)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "CdQMyvoLcqhnxT118oRzXZ37sCZUxY9dhbmkyeMTZaQCPX7evyV7YMyJMd4Y18F4271D89NaYi7ZTsd28QktCG6nRcAWvA=="
               );
               var12[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830856194, 9)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "jM5YQ6JJKHZy+jbiGAN8NlKgYCH8qn7BaaMcX0PsZAgI5CW2PveMvouTzBQMZlbf"
               );
               this.setSidebarPendingValue(var1, var3, var12);
               break;
            case 10:
               String[] var11 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-299200902, 31)];
               var11[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 7)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "+PYMxlo93J6oBiaCSocir8K3MGjS+qFqFujDqA5K03emu+VHDu8KTkh7M6nhuT05HkVb+A=="
               );
               var11[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-285746251, 24)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "VeUseNh4yOt+M7ijJB9Cooiv5LG0oP4BXGLNSEZSldD1wBDZitGH11UGj8f1/ban1ntvoUlvB6as2j3fnXeJkJtR"
               );
               var11[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1151402115, 12)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "ywwXiF7n+HqQsPjfaqWLc0sAR2nPVcdZQb8s4+paRLVN/CJWPQBFTRztkOFiQWF/o7FZJNNFrDnjLRVqsUftIp5V4GYfM9Il6VQNFdIv97WheC8q"
               );
               var11[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1350058275, 19)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "6E2OuSjF90P2NAhPAYElDSgX/Z7AiJD62NWdcmdtfg84671Y+OyNxt97iEZyPcdDhC37WYoa8tcpB4JPalg="
               );
               this.setSidebarPendingValue(var1, var3, var11);
               break;
            case 11:
               String[] var10 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(607479382, 30)];
               var10[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 23)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "zD85LYQBqTn9dG/TGyHumlDnx9S4H0kt9NTTs3bF90MZ54Gi5R47RBLteNmihX16JyG5U0/Z3cg0i9Kn"
               );
               this.setSidebarPendingValue(var1, var3, var10);
               break;
            case 12:
               String[] var9 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(25548802, 28)];
               var9[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-546383945, 2)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "/QC0IOzyJbYp6DfDH/G1c0msRVj5u5MUmlSvR6LavN+jm/6uN4bF71seSGppHqopeu1qM+D0AVEGMzBGicWhNQ=="
               );
               var9[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 14)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "lQBuKF+UFD9bJzI1hObl+KnNS7M0kAPq/wG/g7jHOnn9Lj9UW2bSwHQ5wn7PjT7liGURvxsPJxslDrH7wOJ7JEi9GGDiXv+sSWzbz8n35jzO4VPJ5Z3p+A=="
               );
               var9[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1217667089, 9)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "N2XM1V/adNO7aBWayYMNoGo3om2iWtRAuTkqLQFO58vBC0vroTvJY7CWtPUd0N0Ur+2ifDBgOEIf4p1uVYZwGJ1HVaApNS3txa/cRhgnxVWyOA=="
               );
               var9[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1748330070, 26)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "x1/Ho2oYcflIUQHgL1o/jd+SnLWZE69bylGE/1J4wGcSF/My4G9znmGL9fGfzBRp0kZHQHVzNp+yVbqt"
               );
               this.setSidebarPendingValue(var1, var3, var9);
               break;
            case 13:
               String[] var8 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 19)];
               var8[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1210093865, 26)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "ju4CVu6BNA3Z07odV/jE9v4BERvi0SlzaKbcnjo8Kgv739g/2CvhshZrZs052X5WB1NHPrywu8IQb3N3TvC0LovpjMkRrTekCTOIZ3P+tZu1xkiO"
               );
               this.setSidebarPendingValue(var1, var3, var8);
               break;
            case 14:
               String[] var7 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1217667601, 9)];
               var7[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-575705154, 11)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "1aR4buwAz4GnvxTRQTy/mTLvPdWeY4/jbFSazWeBr/p2nyr0Wr9SZBwZo6SYLmAoZCUi/qPL0A/PblalmkSQoHHZbEH56SOGIraJb4qF0Po9Vs+b2vqAcg=="
               );
               this.setSidebarPendingValue(var1, var3, var7);
               break;
            case 15:
               String[] var6 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 15)];
               var6[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1874779567, 27)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "84q2Hep1WN2wom4zXFe3nR2YlSQ/lq8WxmXIcfKr2UU3RRR/IY6kLPzosrtw9l+U2Au0+ORi7USv/WthhbT5CfTFL75HjWG2V7bBniBHa9IpQHUjMXU="
               );
               this.setSidebarPendingValue(var1, var3, var6);
               break;
            case 16:
               String[] var5 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1642362205, 21)];
               var5[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1014437191, 22)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "X0Hb6BwZNUulX3+wca38JMrV31cL1ABDc+YI0/ffszPnE9SPbVPfYBheWO1hRZzkGnA0C+i7XzyZLCSEBSDFR5Psz+JVtRu4T1Tq1q6akwd8aIiXw8hgYMx6"
               );
               this.setSidebarPendingValue(var1, var3, var5);
               break;
            case 17:
               String[] var4 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1549919350, 13)];
               var4[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 29)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "H3hpOBp0ahgZHGiW54dO2FfJ0uT00OYb6vUScL2OS1bMrFHLjRFlN1ArFoKlqvLzLRuSxpfKUP2rIjjifGKewC1KM45aIws42Lqdvyf878UKvw=="
               );
               this.setSidebarPendingValue(var1, var3, var4);
               break;
            case 18:
               String[] var10003 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681745494, 19)];
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1242695717, 16)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "ZgR3tY3c0o31DY1RwyfZ4m2s8/ipvnmRc506KLdtJNV53G3YRiDVhxDV6ajbR5YwvnEbqqIqRP67xmHOFiiF28DQsnffjj3RKCVzmk/r0nJ2BJg6E5ioMQ=="
               );
               this.setSidebarPendingValue(var1, var3, var10003);
         }
      }
   }

   public void setSidebarPendingValue(Player var1, String var2, String... var3) {
      if (((1247100575 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var3 != null && var3.length != 0) {
         EditorSession var4 = (EditorSession)this.editorSessions.get(var1.getUniqueId());
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1992163067, 13)];
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1904677334, 15)] = var2;
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1461221662, 11)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LS1+t14VZvpK4Nq4tSNxPVI6QfbRaqikVEy/jghWErxDOQ==");
         String var5 = this.firstNonBlank(var10001);
         String[] var6 = var3;
         int var7 = var3.length;

         for (int var8 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 13); var8 < var7; var8++) {
            String var9 = var6[var8];
            if (var9 != null && !var9.isBlank()) {
               this.setShellText(var1, var9, this.highlightSidebarEditingText(var4, var9, var5));
            }
         }
      }
   }

   public String highlightSidebarEditingText(EditorSession var1, String var2, String var3) {
      if (((-447178886 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null
         && var3 != null
         && !var3.isBlank()
         && !var3.contains(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("4bu5sBVsok/jNrgzZJnrnFGcUW7Sa+K4WaIdex0kPPU="))) {
         ConfigurationSection var4 = this.findShellBlockSection(var1, var2);
         if (var4 == null) {
            return var3;
         } else {
            String[] var10002 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-299200903, 31)];
            var10002[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 10)] = var4.getString(
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SXnbKYNGrvJiwTC16d7CCneH8Zgjcjc0A8yLx/7xI0FG0Tc/Fws=")
            );
            var10002[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(485547093, 16)] = var4.getString(
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Tz330h7gwv5vcG1MWRF2ngkGZFlHwxIvEnBSVPWE4ks8fk5KtG6eKmD7oj3tu8tP41I=")
            );
            String var5 = this.normalizeHexColor(this.firstNonBlank(var10002));
            return var5.isBlank()
               ? var3
               : this.withHexPrefix(var3, this.brightenHexColor(var5, Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(8830862173149319425L, 43))));
         }
      } else {
         return var3;
      }
   }

   public String brightenHexColor(String var1, double var2) {
      if ((-1568421454 * -1568421454 + -1568421454 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var4 = this.normalizeHexColor(var1);
         if (var4.isBlank()) {
            return var4;
         } else {
            double var5 = Math.max(
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544889370L, 42)),
               Math.min(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-8526451170279988787L, 53)), var2)
            );
            int var7 = Integer.parseInt(
               var4.substring(Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1416873841, 10), Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2080241389, 22)),
               Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1997883192, 30)
            );
            int var8 = Integer.parseInt(
               var4.substring(Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-814656397, 26), Xqedii_2ze73vg.Xqedii_18u831nn38lo(1680172630, 18)),
               Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-2022615949, 26)
            );
            int var9 = Integer.parseInt(
               var4.substring(Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(805689346, 23), Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-76104066, 5)),
               Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1997883192, 30)
            );
            var7 = Math.min(
               Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1223342333, 30), (int)Math.round(var7 + (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(1046861826, 20) - var7) * var5)
            );
            var8 = Math.min(
               Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681226166, 5), (int)Math.round(var8 + (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1848282631, 31) - var8) * var5)
            );
            var9 = Math.min(
               Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1994235643, 13), (int)Math.round(var9 + (Xqedii_2ze73vg.Xqedii_18u831nn38lo(-1808439719, 28) - var9) * var5)
            );
            Locale var10000 = Locale.ROOT;
            String var10001 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("KO/7zNuVhPhEj0gB7/Nt4caCJiCMCIAF89ITpRa94I9JRoMiNweRVW8juWj6Pdbuzxa0VA==");
            Object[] var10002 = new Object[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-2017534545, 27)];
            var10002[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 28)] = var7;
            var10002[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1689609814, 23)] = var8;
            var10002[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(971487402, 17)] = var9;
            return String.format(var10000, var10001, var10002);
         }
      }
   }

   public void updateSelectionPanelVisibility(Player var1, EditorSession var2, boolean var3) {
      if (((2104304059 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.shellBlocks != null && !var2.shellBlocks.isEmpty()) {
         ConfigurationSection var4 = this.findShellBlockSection(
            var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("3DOSGSr6/DWBD9eO7KbMZ1wq3yAFJD8DqRZbpGhYe2J6ju7+lrAy2yG4XBtDaDePFJYL5w==")
         );
         if (var4 != null) {
            ConfigurationSection var5 = this.findShellBlockSection(
               var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("UgE4PMnp3atRdkYttcLut9QbmWTSURFSNpGa7gY6jv3ITNAkfm2SEjbBuaLJzaUT")
            );
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681213014, 13)];
            var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-948874125, 26)] = var5 == null
               ? null
               : var5.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
               );
            String var6 = this.firstNonBlank(var10001);
            double var7 = this.readDouble(
               var4,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vKKayzv129fu+soeD6bfYdPwcE2kYsAjeOWIibaiwxa/r2AA1MZmuL+SyruKUhve"),
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dMemVQei62r6NkNu8BqxHTzcBg7yzM+XaIb3LIsc"),
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-132292304373590160L, 28))
            );
            double var9 = this.readDouble(
               var4,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6l2WF3VPN37KDzXw/G9b5TL+6GD2Z7ti90KnCY4iv1P1TeFGrB4AUOB3jzUJXHwM"),
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xm8RisRfJosJsXxzE9Kmi51eIt5xrdF3Z/Mg7m1j"),
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544889370L, 61))
            );
            double var11 = Math.max(
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819702160472198170L, 49)),
               Math.abs(
                  this.readDouble(
                     var4,
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dD1V5iTP6Cs1T70nZx7JxW7+VGRP/INn/TyRub+RmwTrKSQrCShArllQuVCb8eP6"),
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("FPKgmoGqqfsf/TAZ7Kaoe8WTiOLLnI5xMvuM1lCli1sVmL5BN/Y="),
                     this.readDouble(
                        var4,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lErzeT8UmqIwoEIshJ7Fi16pNxEDG1a+KfX0vnDs6TaHlTzfg02UWVVtPRWM4P1OEu0="),
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("FPKgmoGqqfsf/TAZ7Kaoe8WTiOLLnI5xMvuM1lCli1sVmL5BN/Y="),
                        Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(8314325498909700056L, 43))
                     )
                  )
               )
            );
            double var13 = Math.max(
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012083190491569L, 43)),
               Math.abs(
                  this.readDouble(
                     var4,
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GZNbFT1z5fLTz5m0iQOw7lZE3qkAFFk1rZ1X4zeaKsm9AlkyCXgpj7Bck6mfKr66AX4="),
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("CP4szv29CrqB0R19unEkQAr7B5MVCDB7QeO/jqmjBwdbqF4gfuQqjw=="),
                     this.readDouble(
                        var4,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZdWikxTMRP2zcCTxHiJM36vJxKMhxOMZKnTT9mzDzJ4GFejuYy6wyZRUTyGCY+QV/NvSjA=="),
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("CP4szv29CrqB0R19unEkQAr7B5MVCDB7QeO/jqmjBwdbqF4gfuQqjw=="),
                        Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-4657461934652860262L, 1))
                     )
                  )
               )
            );
            double var15 = var9 + Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(245003346514278568L, 46));
            int var17 = var3 ? Xqedii_2ze73vg.Xqedii_18u831nn38lo(1707828822, 17) : Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1992146683, 13);
            HashMap var18 = new HashMap();
            int var19 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 28);

            for (Map var21 : var2.shellBlocks) {
               var19++;
               ConfigurationSection var22 = this.mapToSection(var21);
               if (var22 != null) {
                  var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-304416773, 7)];
                  var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-675815570, 18)] = var22.getString(
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("wnj7uF3hcOoAks+8+3wFksoVbSbHn47JPMeDyYMSgiPoAf2O")
                  );
                  var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-571492501, 25)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                     "phMhQ5z6/nUG7x9rIjeqZ9DFFsF7Num7Ht8iWfSlIm4ZUFO5Sic="
                  );
                  String var23 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
                  if (this.isRenderableBlockType(var23)) {
                     String var24 = this.resolveElementId(var22, var19, var18);
                     var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(924141372, 30)];
                     var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1859631071, 10)] = var22.getString(
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
                     );
                     String var25 = this.firstNonBlank(var10001);
                     if ((var6.isBlank() || this.belongsToSidebarPanel(var6, var25))
                        && var24 != null
                        && !var24.isBlank()
                        && !this.isEditorToolShellBlockId(var24)
                        && !Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("3DOSGSr6/DWBD9eO7KbMZ1wq3yAFJD8DqRZbpGhYe2J6ju7+lrAy2yG4XBtDaDePFJYL5w==").equals(var24)
                        && !this.isLayersShellBlockId(var24)
                        && !this.isActionsShellBlockId(var24)) {
                        double var26 = this.readDouble(
                           var22,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vKKayzv129fu+soeD6bfYdPwcE2kYsAjeOWIibaiwxa/r2AA1MZmuL+SyruKUhve"),
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dMemVQei62r6NkNu8BqxHTzcBg7yzM+XaIb3LIsc"),
                           Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(1960166908307162432L, 49))
                        );
                        double var28 = this.readDouble(
                           var22,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6l2WF3VPN37KDzXw/G9b5TL+6GD2Z7ti90KnCY4iv1P1TeFGrB4AUOB3jzUJXHwM"),
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xm8RisRfJosJsXxzE9Kmi51eIt5xrdF3Z/Mg7m1j"),
                           Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544889370L, 31))
                        );
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

   public boolean isLayersShellBlockId(String var1) {
      if (((-1923654028 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isBlank()) {
         return ((!Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("BAfA50WMJFobv0hYukeMUxX9MaGBJ/CJFtS6+SPvxjYD3a77yAcRAn3gh34PyKfC").equals(var1)
               && !var1.startsWith(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ettrzwsihtbJ03OYjsPDUZ9Hq5FKc91zkasyFaTU2DKbZhp43RlaWIBqpCkCLHtZiLTt4ACUW+UoxA=="))
               && !var1.startsWith(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("+5IIBx+Xtq96aUo1PcFE5CFFbTwHxoU594GS7Lrlr3XtzxJt74WvAsDDQjAXxN0w8Lw="))
            ? Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1968988613, 5)
            : Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-142873126, 23)) != 0);
      } else {
         return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(485612629, 16) != 0);
      }
   }

   public String readTargetBorderRadiusMode(EditorSession var1, String var2) {
      if (((1202724654 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
            Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
            if (var4 == null) {
               return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("By5q5besaxSFT9aOarh+VnLDM10y2NZAT30OXaYpHiq4ZAKe");
            } else if (!this.isRoundedRawTarget(var4, var3)) {
               return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("By5q5besaxSFT9aOarh+VnLDM10y2NZAT30OXaYpHiq4ZAKe");
            } else {
               String var5 = this.resolveBorderRadiusModeFromStoredValue(
                  this.stringValue(
                     this.readMapPathValue(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("QEjLyJuikMqC9mFbuvAjqj5cLN9O+2FjgDfY3zIW+qabQlx7c5prevlCAXk="))
                  )
               );
               if (!var5.isBlank()) {
                  return var5;
               } else {
                  String var6 = this.stripRoundedCornerColorTags(
                     this.readRoundedCornerUnicodeByKey(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("9BVEJ+ZwzivK6604vuQuQWQMXMmJfi3qA2EUwjiTyzY="))
                  );
                  String var7 = this.stripRoundedCornerColorTags(
                     this.readRoundedCornerUnicodeByKey(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("KNrbB/C5LG/kXZVWteEA9A3dZF4y6T1QLeEGyrjeocw="))
                  );
                  String var8 = this.stripRoundedCornerColorTags(
                     this.readRoundedCornerUnicodeByKey(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("FfiMj2HvpWUtfX2ZAW8rmWbo1eOyQ/QU8LIyu2AXtfs="))
                  );
                  String var9 = this.stripRoundedCornerColorTags(
                     this.readRoundedCornerUnicodeByKey(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("VaimAdLl7RN7od+4TXJsgOstRvS8VS/Xwnee/mBwxho="))
                  );
                  if (var6.isBlank() && var7.isBlank() && var8.isBlank() && var9.isBlank()) {
                     return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tQUBuNFFR3dkiQoobi1iwA9omebwzhjFH8DgaWNSN4IAmVgHoZk6jTlP");
                  } else if (this.matchesRoundedCornerGlyphSet(var6, var7, var8, var9, Xqedii_3gdqlgv.Xqedii_e72f1bi)) {
                     return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("IadFesVYEZZtFORzN8INi8+7UGwr+UJ+BouazrVx8cs1MUH4P4M=");
                  } else if (this.matchesRoundedCornerGlyphSet(var6, var7, var8, var9, Xqedii_3gdqlgv.Xqedii_1lyarcw)) {
                     return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tnKpWUguYkjE2P9RIgMF0B0TcIfmMNkg/N5l3B1DIrV76U24xBgWaA==");
                  } else if (this.matchesRoundedCornerGlyphSet(var6, var7, var8, var9, Xqedii_3gdqlgv.Xqedii_3vlra9i)) {
                     return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("/OmGSb+OJu4cUI8Nf7pwEupK6Tz+NpV+jhNdMFOYBpRdEBBk4sM=");
                  } else {
                     return this.matchesRoundedCornerGlyphSet(var6, var7, var8, var9, Xqedii_3gdqlgv.Xqedii_4tbiq25)
                        ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tQUBuNFFR3dkiQoobi1iwA9omebwzhjFH8DgaWNSN4IAmVgHoZk6jTlP")
                        : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tQUBuNFFR3dkiQoobi1iwA9omebwzhjFH8DgaWNSN4IAmVgHoZk6jTlP");
                  }
               }
            }
         } else {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("By5q5besaxSFT9aOarh+VnLDM10y2NZAT30OXaYpHiq4ZAKe");
         }
      }
   }

   public boolean matchesRoundedCornerGlyphSet(String var1, String var2, String var3, String var4, String[] var5) {
      if ((1589142813 * 1589142813 + 1589142813 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return ((var5 != null
               && var5.length >= Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830846978, 13)
               && this.equalsNullable(this.stripRoundedCornerColorTags(var1), var5[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 19)])
               && this.equalsNullable(this.stripRoundedCornerColorTags(var2), var5[Xqedii_2ze73vg.Xqedii_18u831nn38lo(-466262442, 31)])
               && this.equalsNullable(this.stripRoundedCornerColorTags(var3), var5[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(356990199, 6)])
               && this.equalsNullable(this.stripRoundedCornerColorTags(var4), var5[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830851074, 11)])
            ? Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-273191971, 1)
            : Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-621347859, 15)) != 0);
      }
   }

   public String readRoundedCornerUnicodeByKey(Map<String, Object> var1, String var2) {
      if ((2083193429 ^ 2083193429) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = var2.trim().toLowerCase(Locale.ROOT);
         int var6 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(-1681221207, 17);
         switch (var3.hashCode()) {
            case 3146:
               if (var3.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("VaimAdLl7RN7od+4TXJsgOstRvS8VS/Xwnee/mBwxho="))) {
                  var6 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(767906255, 28);
               }
               break;
            case 3152:
               if (var3.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("FfiMj2HvpWUtfX2ZAW8rmWbo1eOyQ/QU8LIyu2AXtfs="))) {
                  var6 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221214, 2);
               }
               break;
            case 3704:
               if (var3.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("9BVEJ+ZwzivK6604vuQuQWQMXMmJfi3qA2EUwjiTyzY="))) {
                  var6 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-546383945, 2);
               }
               break;
            case 3710:
               if (var3.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("KNrbB/C5LG/kXZVWteEA9A3dZF4y6T1QLeEGyrjeocw="))) {
                  var6 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1859632095, 10);
               }
         }

         String[] var10000;
         switch (var6) {
            case 0:
               var10000 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1144350294, 27)];
               var10000[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 21)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("9BVEJ+ZwzivK6604vuQuQWQMXMmJfi3qA2EUwjiTyzY=");
               var10000[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2008997295, 27)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "aGf6jbHx4dhYInmxEJHKiJF43jhcXHl4cAe5RJSOIOcSi3a3aHqaXcHp7Dg="
               );
               var10000[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1614112342, 25)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "QIz1/FAMOAkP0zxOvHZ1/OmO655RFswV4vaGO36F3UrgHsAl3tPvYHlA"
               );
               var10000[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(356990135, 6)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "LN59w5UU5i/o2JylCTiCEMzrdbYovZRu2GvsJ35vz+Hrs+/1cplOx0C/yKs="
               );
               break;
            case 1:
               var10000 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1195210517, 14)];
               var10000[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 19)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("KNrbB/C5LG/kXZVWteEA9A3dZF4y6T1QLeEGyrjeocw=");
               var10000[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-553945411, 29)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "pZQ3eNNDPNHWREc65k4O7J/eSpxlXPcLOUjIcloy+QLQoOCB7lcduBRY3kGEqQ=="
               );
               var10000[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1144350294, 28)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "i/+1qWQewdx1GgSLUBmMYtjd1y4vXrWQOaQiOJDUu1onx2GjA7BzAGBN7Iw="
               );
               var10000[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1092767865, 3)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "6oZKf3alM7Rlm0XDy7qnstXQuRYKS49ePeXFyGkaTiW/6Ab3FIB/bKhAzChYmg=="
               );
               break;
            case 2:
               var10000 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(25548802, 28)];
               var10000[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(998941598, 29)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("FfiMj2HvpWUtfX2ZAW8rmWbo1eOyQ/QU8LIyu2AXtfs=");
               var10000[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 20)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "grMK2yTaAFpVNkUq984j0hqtee8NSMZK4CP44ynYkNp1Rk7SMUEGECdtSJwbetKVIpAMpw=="
               );
               var10000[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-608833545, 8)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "GHEyRA4792HswNzjUa8D9HOLU0t8aphcc1CVac34NooH95Z/ujSltvEj0pud3qbtmvU="
               );
               var10000[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855682, 8)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "f3ZOxcvVo+ZJmZjQycCCPRN+Zg0PTXFsIDVE/lNcGszcM4zQFTWWrKrpokZEReoyPbvwuA=="
               );
               break;
            case 3:
               var10000 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(834000898, 20)];
               var10000[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 11)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("VaimAdLl7RN7od+4TXJsgOstRvS8VS/Xwnee/mBwxho=");
               var10000[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1109460106, 21)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "pfkzfUu3WFBSElCwICfnyndZJJjAKPRRQnNBCh2rZ+rItRB6B7kiTIhhwzBuPagD4YE="
               );
               var10000[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-984494275, 4)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "B4f8C1X82rwrPcpL5fUDcMGZmU4TraiP0ECgwfTYKBSMShJNgPzpF2YSUdGXxyzt"
               );
               var10000[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681237590, 14)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "FGdq8YwMtnymFe1UYKht/98rYazAvU0TWKtR9yPiesg+0sZaYKEVvwL+IPKoSiUJzEk="
               );
               break;
            default:
               var10000 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-598401808, 0)];
               var10000[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 14)] = var3;
         }

         String[] var4 = var10000;
         String[] var5 = var4;
         var6 = var4.length;

         for (int var7 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 4); var7 < var6; var7++) {
            String var8 = var5[var7];
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-412003213, 26)];
            var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 30)] = this.stringValue(this.readMapPathValue(var1, Xqedii_32w4b336vvjr9(var8)));
            var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1809706935, 17)] = this.stringValue(this.readMapPathValue(var1, Xqedii_1t2h8emvk15u2(var8)));
            var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(832952322, 21)] = this.stringValue(this.readMapPathValue(var1, Xqedii_2gr1jv2dq802(var8)));
            var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-152208323, 6)] = this.stringValue(this.readMapPathValue(var1, Xqedii_1qx7sd8fmnlu3(var8)));
            var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1859635167, 10)] = this.stringValue(this.readMapPathValue(var1, Xqedii_27tvw5bedp3s(var8)));
            var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221398, 6)] = this.stringValue(this.readMapPathValue(var1, Xqedii_1cffkkk8rqll3(var8)));
            var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830527490, 16)] = this.stringValue(this.readMapPathValue(var1, Xqedii_3q7le3zxl8kfm(var8)));
            var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1968988453, 5)] = this.stringValue(this.readMapPathValue(var1, Xqedii_u37xk4lj61ok(var8)));
            String var9 = this.firstNonBlank(var10001);
            if (!var9.isBlank()) {
               return var9;
            }
         }

         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      } else {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      }
   }

   public String resolveBorderRadiusModeFromStoredValue(String var1) {
      if ((1108783645 ^ 1108783645) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681225302, 12)];
         var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-302523467, 24)] = var1;
         String var2 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
         if (var2.isBlank()) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
         } else {
            int var4 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(-1681221207, 25);
            switch (var2.hashCode()) {
               case -1078030475:
                  if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Sl08+Tlk9f1u7AhCkiA/66tND+R4fP+qRfJlvKxYuNomfcurD2UPdA=="))) {
                     var4 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1092682890, 21);
                  }
                  break;
               case -1039745817:
                  if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("e3OXTh8Zq9lM+0eJHmmx3r8LjdnNgCFqjGvg+eeaoFa4HtNiCbbSnQ=="))) {
                     var4 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830814210, 13);
                  }
                  break;
               case 48:
                  if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("L6UzhEzoDhva1My7Wrjku+iIwFdufeAIg2JqL/aL"))) {
                     var4 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(837146626, 21);
                  }
                  break;
               case 109935:
                  if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("1OA05c11S8cu+YFUNX214QJngu4yum754DofpBctdKvzsA=="))) {
                     var4 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1010242887, 22);
                  }
                  break;
               case 3387192:
                  if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LKKcXIHpjgBU+nETsXnA8WA6AHKFNlJKiSkJACNDtoY3zlWO"))) {
                     var4 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 1);
                  }
                  break;
               case 97196323:
                  if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SCatopZurbMRujZ4WYzxyO7SZCzGA02BnizuPZ/5tt4elw6oDGc="))) {
                     var4 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221205, 0);
                  }
                  break;
               case 102742843:
                  if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("gZNPrDFVihdF3nJHgHVgbJGTiSRXuMKAW1WnI3cxjl86CIDxgT0="))) {
                     var4 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-76103938, 5);
                  }
                  break;
               case 109548807:
                  if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Goyvn541xP8yvl+3w5cRWWzgYyYzxjxZD3XPtIvQkVJxhYKQ4yk="))) {
                     var4 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221366, 5);
                  }
                  break;
               case 270940796:
                  if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("NozKOJHEbgbF4e1S9dw09P2PyzXZSw8oRVFhBmAkzlLFacYuasZE1SLbtPc="))) {
                     var4 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-76104194, 5);
                  }
                  break;
               case 1086463900:
                  if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("M1mRgpiLNHfDDC0Go3mIu5J7LcVHbVE9Nqib6zG3D3+K9YmoE9SgSGeT"))) {
                     var4 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1242302501, 16);
                  }
                  break;
               case 1544803905:
                  if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("4vZQ2j0sOoVLQ9JMbncTDtN54RwTYBY8oI/rDgRXgGh0p8VWr/9TjgZx"))) {
                     var4 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221230, 3);
                  }
            }
            return switch (var4) {
               case 0, 1, 2, 3, 4 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("By5q5besaxSFT9aOarh+VnLDM10y2NZAT30OXaYpHiq4ZAKe");
               case 5 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("IadFesVYEZZtFORzN8INi8+7UGwr+UJ+BouazrVx8cs1MUH4P4M=");
               case 6, 7, 8 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tQUBuNFFR3dkiQoobi1iwA9omebwzhjFH8DgaWNSN4IAmVgHoZk6jTlP");
               case 9 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tnKpWUguYkjE2P9RIgMF0B0TcIfmMNkg/N5l3B1DIrV76U24xBgWaA==");
               case 10 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("/OmGSb+OJu4cUI8Nf7pwEupK6Tz+NpV+jhNdMFOYBpRdEBBk4sM=");
               default -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
            };
         }
      }
   }

   public String serializeSidebarBorderRadiusMode(String var1) {
      if (((-758087944 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String var2 = this.normalizeSidebarBorderRadiusMode(var1);
         int var3 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1352155427, 19);
         switch (var2.hashCode()) {
            case -1994163307:
               if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tnKpWUguYkjE2P9RIgMF0B0TcIfmMNkg/N5l3B1DIrV76U24xBgWaA=="))) {
                  var3 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(713980398, 7);
               }
               break;
            case -1543850116:
               if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tQUBuNFFR3dkiQoobi1iwA9omebwzhjFH8DgaWNSN4IAmVgHoZk6jTlP"))) {
                  var3 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1809706935, 17);
               }
               break;
            case 73190171:
               if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("/OmGSb+OJu4cUI8Nf7pwEupK6Tz+NpV+jhNdMFOYBpRdEBBk4sM="))) {
                  var3 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-546383933, 2);
               }
               break;
            case 79996135:
               if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("IadFesVYEZZtFORzN8INi8+7UGwr+UJ+BouazrVx8cs1MUH4P4M="))) {
                  var3 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1997883196, 30);
               }
         }
         return switch (var3) {
            case 0 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Goyvn541xP8yvl+3w5cRWWzgYyYzxjxZD3XPtIvQkVJxhYKQ4yk=");
            case 1 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("M1mRgpiLNHfDDC0Go3mIu5J7LcVHbVE9Nqib6zG3D3+K9YmoE9SgSGeT");
            case 2 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Sl08+Tlk9f1u7AhCkiA/66tND+R4fP+qRfJlvKxYuNomfcurD2UPdA==");
            case 3 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("gZNPrDFVihdF3nJHgHVgbJGTiSRXuMKAW1WnI3cxjl86CIDxgT0=");
            default -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LKKcXIHpjgBU+nETsXnA8WA6AHKFNlJKiSkJACNDtoY3zlWO");
         };
      }
   }

   public String normalizeSidebarBorderRadiusMode(String var1) {
      if (((-1092177683 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2109431535, 4)];
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1014437191, 22)] = var1;
         String var2 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
         if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("IadFesVYEZZtFORzN8INi8+7UGwr+UJ+BouazrVx8cs1MUH4P4M=").toLowerCase(Locale.ROOT))) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("IadFesVYEZZtFORzN8INi8+7UGwr+UJ+BouazrVx8cs1MUH4P4M=");
         } else if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tQUBuNFFR3dkiQoobi1iwA9omebwzhjFH8DgaWNSN4IAmVgHoZk6jTlP").toLowerCase(Locale.ROOT))) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tQUBuNFFR3dkiQoobi1iwA9omebwzhjFH8DgaWNSN4IAmVgHoZk6jTlP");
         } else if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tnKpWUguYkjE2P9RIgMF0B0TcIfmMNkg/N5l3B1DIrV76U24xBgWaA==").toLowerCase(Locale.ROOT))) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tnKpWUguYkjE2P9RIgMF0B0TcIfmMNkg/N5l3B1DIrV76U24xBgWaA==");
         } else {
            return var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("/OmGSb+OJu4cUI8Nf7pwEupK6Tz+NpV+jhNdMFOYBpRdEBBk4sM=").toLowerCase(Locale.ROOT))
               ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("/OmGSb+OJu4cUI8Nf7pwEupK6Tz+NpV+jhNdMFOYBpRdEBBk4sM=")
               : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("By5q5besaxSFT9aOarh+VnLDM10y2NZAT30OXaYpHiq4ZAKe");
         }
      }
   }

   public String nextSidebarBorderRadiusMode(String var1) {
      if ((-969070970 * (-969070970 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var2 = this.normalizeSidebarBorderRadiusMode(var1);
         int var4 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-293984259, 29);
         switch (var2.hashCode()) {
            case -1994163307:
               if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tnKpWUguYkjE2P9RIgMF0B0TcIfmMNkg/N5l3B1DIrV76U24xBgWaA=="))) {
                  var4 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-598401805, 0);
               }
               break;
            case -1543850116:
               if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tQUBuNFFR3dkiQoobi1iwA9omebwzhjFH8DgaWNSN4IAmVgHoZk6jTlP"))) {
                  var4 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(971094186, 17);
               }
               break;
            case 73190171:
               if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("/OmGSb+OJu4cUI8Nf7pwEupK6Tz+NpV+jhNdMFOYBpRdEBBk4sM="))) {
                  var4 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(713980270, 7);
               }
               break;
            case 79996135:
               if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("IadFesVYEZZtFORzN8INi8+7UGwr+UJ+BouazrVx8cs1MUH4P4M="))) {
                  var4 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 28);
               }
         }
         return switch (var4) {
            case 0 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tQUBuNFFR3dkiQoobi1iwA9omebwzhjFH8DgaWNSN4IAmVgHoZk6jTlP");
            case 1 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tnKpWUguYkjE2P9RIgMF0B0TcIfmMNkg/N5l3B1DIrV76U24xBgWaA==");
            case 2 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("/OmGSb+OJu4cUI8Nf7pwEupK6Tz+NpV+jhNdMFOYBpRdEBBk4sM=");
            case 3 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("By5q5besaxSFT9aOarh+VnLDM10y2NZAT30OXaYpHiq4ZAKe");
            default -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("IadFesVYEZZtFORzN8INi8+7UGwr+UJ+BouazrVx8cs1MUH4P4M=");
         };
      }
   }

   public boolean isBorderRadiusApplicableType(String var1) {
      if ((1065932723 * (1065932723 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1614112342, 26)];
         var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 22)] = var1;
         String var2 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
         return ((!Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("phMhQ5z6/nUG7x9rIjeqZ9DFFsF7Num7Ht8iWfSlIm4ZUFO5Sic=").equals(var2)
               && !this.isRoundedType(var2)
               && !this.isInputType(var2)
            ? Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 5)
            : Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 25)) != 0);
      }
   }

   public boolean isRoundedRawTarget(Map<String, Object> var1, HoverElement var2) {
      if ((-1505324649 * -1505324649 - -1505324649 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-68297994, 31) != 0);
      } else {
         String var3 = this.resolveRawTargetType(var1, var2);
         return ((!this.isRoundedType(var3) && (!this.isInputType(var3) || !this.readInputRounded(this.mapToSection(var1)))
            ? Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-575705154, 11)
            : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1196803615, 1)) != 0);
      }
   }

   public String resolveRawTargetType(Map<String, Object> var1, HoverElement var2) {
      if ((-482682424 * (-482682424 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 0)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 31)] = this.stringValue(
            this.readMapPathValue(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("wnj7uF3hcOoAks+8+3wFksoVbSbHn47JPMeDyYMSgiPoAf2O"))
         );
         String var3 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
         if (!var3.isBlank()) {
            return var3;
         } else {
            var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-253995748, 24)];
            var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1217668113, 9)] = var2 == null
               ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==")
               : var2.type;
            return this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
         }
      }
   }

   public String[] resolveBorderRadiusGlyphSet(String var1) {
      if (((-1011795399 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String var2 = this.normalizeSidebarBorderRadiusMode(var1);
         int var3 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(-1681221207, 11);
         switch (var2.hashCode()) {
            case -1994163307:
               if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tnKpWUguYkjE2P9RIgMF0B0TcIfmMNkg/N5l3B1DIrV76U24xBgWaA=="))) {
                  var3 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1901360067, 2);
               }
               break;
            case 73190171:
               if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("/OmGSb+OJu4cUI8Nf7pwEupK6Tz+NpV+jhNdMFOYBpRdEBBk4sM="))) {
                  var3 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221214, 2);
               }
               break;
            case 79996135:
               if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("IadFesVYEZZtFORzN8INi8+7UGwr+UJ+BouazrVx8cs1MUH4P4M="))) {
                  var3 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 22);
               }
         }
         return switch (var3) {
            case 0 -> Xqedii_3gdqlgv.Xqedii_e72f1bi;
            case 1 -> Xqedii_3gdqlgv.Xqedii_1lyarcw;
            case 2 -> Xqedii_3gdqlgv.Xqedii_3vlra9i;
            default -> Xqedii_3gdqlgv.Xqedii_4tbiq25;
         };
      }
   }

   public String resolveRoundedCornerUnicodeWritePath(String var1) {
      if (((640329675 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-285746251, 24)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 2)] = var1;
         return Xqedii_3df5beqifmqto(this.firstNonBlank(var10001).toLowerCase(Locale.ROOT));
      }
   }

   public boolean setMapPathStringValue(Map<String, Object> var1, String var2, String var3) {
      if (((-1482704598 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         String var4 = var3 == null ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==") : var3;
         String var5 = this.stringValue(this.readMapPathValue(var1, var2));
         if (this.equalsNullable(var5, var4)) {
            return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 15) != 0);
         } else {
            this.setMapPathValue(var1, var2, var4);
            return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 5) != 0);
         }
      } else {
         return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 17) != 0);
      }
   }

   @Override
   public String stringValue(Object var1) {
      if ((-584121358 ^ -584121358) != 0) {
         throw new IllegalStateException();
      } else {
         return var1 == null ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==") : var1.toString().trim();
      }
   }

   public String composeAnimationScopedTargetId(String var1, String var2) {
      if ((1641439840 * 1641439840 + 1641439840 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 19)];
         var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-136595987, 0)] = var1;
         String var3 = this.firstNonBlank(var10001);
         if (var3.isBlank()) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
         } else {
            String var4 = this.normalizeRuntimeAnimationPageKey(var2);
            return var4.isBlank() ? var3 : Xqedii_2zwnxcmic0ish(var4, var3);
         }
      }
   }

   public String resolveAnimationTargetScopePageKey(String var1) {
      if (((-1338158258 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1679124054, 21)];
         var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 22)] = var1;
         String var2 = this.firstNonBlank(var10001);
         if (!var2.startsWith(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("hbFONQjhgTPyJnxjXerm3pFWQoydu+8G+EMzegBMyu/h+YPFaxHQbU3Un/DtrmpLzb7cSRC4RSE="))) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
         } else {
            int var3 = var2.indexOf(
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vW2jgyKWH9d1OmHJSZvO48q0/pRvyv3K4igrDtMeBFApQQ=="),
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("hbFONQjhgTPyJnxjXerm3pFWQoydu+8G+EMzegBMyu/h+YPFaxHQbU3Un/DtrmpLzb7cSRC4RSE=").length()
            );
            if (var3 < 0) {
               return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
            } else {
               String var4 = var2.substring(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("hbFONQjhgTPyJnxjXerm3pFWQoydu+8G+EMzegBMyu/h+YPFaxHQbU3Un/DtrmpLzb7cSRC4RSE=").length(), var3
               );
               return this.normalizeRuntimeAnimationPageKey(var4);
            }
         }
      }
   }

   @Override
   public String resolveAnimationBaseTargetId(String var1) {
      if (((-1962875043 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1680959062, 18)];
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(713980142, 7)] = var1;
         String var2 = this.firstNonBlank(var10001);
         if (!var2.startsWith(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("hbFONQjhgTPyJnxjXerm3pFWQoydu+8G+EMzegBMyu/h+YPFaxHQbU3Un/DtrmpLzb7cSRC4RSE="))) {
            return var2;
         } else {
            int var3 = var2.indexOf(
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vW2jgyKWH9d1OmHJSZvO48q0/pRvyv3K4igrDtMeBFApQQ=="),
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("hbFONQjhgTPyJnxjXerm3pFWQoydu+8G+EMzegBMyu/h+YPFaxHQbU3Un/DtrmpLzb7cSRC4RSE=").length()
            );
            if (var3 < 0) {
               return var2;
            } else {
               String var4 = var2.substring(var3 + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vW2jgyKWH9d1OmHJSZvO48q0/pRvyv3K4igrDtMeBFApQQ==").length());
               var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681204822, 13)];
               var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 14)] = var4;
               var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-410590552, 19)] = var2;
               return this.firstNonBlank(var10001);
            }
         }
      }
   }

   public String resolveAnimationEffectiveScopePageKey(EditorSession var1, String var2) {
      if ((-411972008 * -411972008 - -411972008 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var3 = this.normalizeRuntimeAnimationPageKey(this.resolveAnimationTargetScopePageKey(var2));
         if (!var3.isBlank()) {
            return var3;
         } else {
            return var1 == null
               ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==")
               : this.normalizeRuntimeAnimationPageKey(var1.runtimeAnimationScopePageKey);
         }
      }
   }

   public String normalizeRuntimeAnimationPageKey(String var1) {
      if ((-1936692411 * (-1936692411 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-410590552, 19)];
         var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 17)] = var1;
         String var2 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
         if (var2.endsWith(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("4/9sCKco6NxydC5WYFl3S7vpIOaWRMrYQOwSFsE7OfC1OJ8T"))) {
            var2 = var2.substring(Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1968988613, 5), var2.length() - Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221202, 0))
               .trim();
         }

         return var2;
      }
   }

   public boolean doesTargetPathMatchAnimationScope(EditorSession var1, String var2, String var3) {
      if ((-1876724933 * -1876724933 - -1876724933 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var4 = this.resolveAnimationEffectiveScopePageKey(var1, var3);
         if (var4.isBlank()) {
            return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 16) != 0);
         } else if (var1 == null) {
            return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(356990071, 6) != 0);
         } else {
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1685415510, 22)];
            var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-675815570, 18)] = var2;
            String var5 = this.firstNonBlank(var10001);
            if (var5.isBlank()) {
               return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2109431519, 4) != 0);
            } else {
               String var6 = this.resolveAnimationOwnerPageKeyForTargetPath(var1, var5);
               return ((!var6.isBlank() && var6.equals(var4)
                  ? Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221214, 3)
                  : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1372528069, 12)) != 0);
            }
         }
      }
   }

   public String resolveAnimationOwnerPageKeyForTargetPath(EditorSession var1, String var2) {
      if ((-1504156173 * -1504156173 + -1504156173 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 19)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 19)] = var2;
         String var3 = this.firstNonBlank(var10001);

         while (!var3.isBlank()) {
            Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3);
            if (var4 != null && !var4.isEmpty()) {
               String var5 = this.normalizeRuntimeAnimationPageKey(
                  this.stringValue(
                     var4.get(
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                           "IUkFZkPQUMYu1kThXQZOMSXAzHi/FGUBUukaWwyU0j8z16tKvy7UhwBXkOChL8sXmYEJFywZPElyjxchzpSxKLnlOaDilQ8q8gA1qZQoy8Af9Xdx"
                        )
                     )
                  )
               );
               if (!var5.isBlank()) {
                  return var5;
               }
            }

            int var6 = var3.lastIndexOf(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("k3GrTmS6Pq3iYG2Wrb3BT0AZfFRxDlQvHtcN7tj7/HyTH6UkraJgvDZWfFa1nPTW"));
            if (var6 < 0) {
               break;
            }

            var3 = var3.substring(Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-608834057, 8), var6);
         }

         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      }
   }

   public String readRawTargetOutlineColor(Map<String, Object> var1) {
      if ((-701272770 * -701272770 - -701272770 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("C+852nfKg74J9IxxMl6Vi+EbG1EnpZVNBgPAGWI4a4FtcTSQ4F7jvA==");
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(826660866, 22)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 4)] = this.stringValue(
            this.readMapPathValue(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZiCT1Cbhw0BkCBETRETw6GisNl3+G+LgZPihItcIXd4xV4JTvBFwiJ6326hO5MjV/BpgqGnG"))
         );
         var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681204822, 14)] = this.stringValue(
            this.readMapPathValue(
               var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LNJMlIiLSnsf58YEWnIG54/ly13i1o5Ur4EC9vb7vhlPv9KAFb3WbhghYhblsLPQHFco5cBVJ7J2Rq+sHDwyJhfq")
            )
         );
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855186, 4)] = this.stringValue(
            this.readMapPathValue(
               var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ddL/lp3n1H+FLyWbUI6K+PjQ3DOshWJ8F7GX9QCQM8yDta6IG4YSRSIArvp82mHMVYWdjoHUvpUJY++1CfSL0oI4wuY=")
            )
         );
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(356990135, 6)] = this.stringValue(
            this.readMapPathValue(
               var1,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "ZZuIbR0lzMIB/LDQor+a7lBOrJtlpgvs7Efr6BRak2nH0xsG/OaMAyVY4A86LUFdTlx2ih+BLWjmdxR/39YzApR8itdAV0gEN22Ky1JMnRQ="
               )
            )
         );
         String var2 = this.firstNonBlank(var10001);
         String var3 = this.normalizeHexColor(var2);
         return !var3.isBlank() ? var3 : this.readRawTargetFillColor(var1);
      }
   }

   public String readRawTargetFillColor(Map<String, Object> var1) {
      if ((-2113574207 ^ -2113574207) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("C+852nfKg74J9IxxMl6Vi+EbG1EnpZVNBgPAGWI4a4FtcTSQ4F7jvA==");
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-408493400, 19)];
         var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1874779567, 27)] = this.stringValue(
            this.readMapPathValue(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SXnbKYNGrvJiwTC16d7CCneH8Zgjcjc0A8yLx/7xI0FG0Tc/Fws="))
         );
         var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-608833801, 8)] = this.stringValue(
            this.readMapPathValue(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Tz330h7gwv5vcG1MWRF2ngkGZFlHwxIvEnBSVPWE4ks8fk5KtG6eKmD7oj3tu8tP41I="))
         );
         var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-273191969, 1)] = this.stringValue(
            this.readMapPathValue(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("h0gPJe6jG+lWwulKcDHMI9L0oUb7KKoT6OAQM9ee/tixUYWoEkjaCNFNSabhY8iFZCS6tg=="))
         );
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(831903746, 19)] = this.stringValue(
            this.readMapPathValue(
               var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("WjvZ1pFhIMvHfu4sF01a2VoQYTzifZcuYZy5aBU7QCttHz/y7gLbXjSuDqbhLwfETk0lUZSX7MWVWQZQLZmYhQ==")
            )
         );
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(931518466, 25)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
            "C+852nfKg74J9IxxMl6Vi+EbG1EnpZVNBgPAGWI4a4FtcTSQ4F7jvA=="
         );
         String var2 = this.firstNonBlank(var10001);
         String var3 = this.normalizeHexColor(var2);
         return var3.isBlank() ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("C+852nfKg74J9IxxMl6Vi+EbG1EnpZVNBgPAGWI4a4FtcTSQ4F7jvA==") : var3;
      }
   }

   public boolean isRawTargetOutlineColorAuto(Map<String, Object> var1) {
      if ((-382144052 * (-382144052 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-76104226, 5) != 0);
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830849026, 11)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 24)] = this.stringValue(
            this.readMapPathValue(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZiCT1Cbhw0BkCBETRETw6GisNl3+G+LgZPihItcIXd4xV4JTvBFwiJ6326hO5MjV/BpgqGnG"))
         );
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1848282744, 31)] = this.stringValue(
            this.readMapPathValue(
               var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LNJMlIiLSnsf58YEWnIG54/ly13i1o5Ur4EC9vb7vhlPv9KAFb3WbhghYhblsLPQHFco5cBVJ7J2Rq+sHDwyJhfq")
            )
         );
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1901360079, 2)] = this.stringValue(
            this.readMapPathValue(
               var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ddL/lp3n1H+FLyWbUI6K+PjQ3DOshWJ8F7GX9QCQM8yDta6IG4YSRSIArvp82mHMVYWdjoHUvpUJY++1CfSL0oI4wuY=")
            )
         );
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1763530521, 27)] = this.stringValue(
            this.readMapPathValue(
               var1,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "ZZuIbR0lzMIB/LDQor+a7lBOrJtlpgvs7Efr6BRak2nH0xsG/OaMAyVY4A86LUFdTlx2ih+BLWjmdxR/39YzApR8itdAV0gEN22Ky1JMnRQ="
               )
            )
         );
         String var2 = this.firstNonBlank(var10001);
         if (var2.isBlank()) {
            return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 7) != 0);
         } else {
            String var3 = this.normalizeHexColor(var2);
            return var3.isBlank();
         }
      }
   }

   public boolean clearRawTargetOutlineColor(Map<String, Object> var1) {
      if (((1870996994 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 4) != 0);
      } else {
         boolean var2 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 30) != 0;
         var2 |= this.setMapPathStringValue(
            var1,
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZiCT1Cbhw0BkCBETRETw6GisNl3+G+LgZPihItcIXd4xV4JTvBFwiJ6326hO5MjV/BpgqGnG"),
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==")
         );
         var2 |= this.setMapPathStringValue(
            var1,
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LNJMlIiLSnsf58YEWnIG54/ly13i1o5Ur4EC9vb7vhlPv9KAFb3WbhghYhblsLPQHFco5cBVJ7J2Rq+sHDwyJhfq"),
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==")
         );
         var2 |= this.setMapPathStringValue(
            var1,
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ddL/lp3n1H+FLyWbUI6K+PjQ3DOshWJ8F7GX9QCQM8yDta6IG4YSRSIArvp82mHMVYWdjoHUvpUJY++1CfSL0oI4wuY="),
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==")
         );
         return var2
            | this.setMapPathStringValue(
               var1,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "ZZuIbR0lzMIB/LDQor+a7lBOrJtlpgvs7Efr6BRak2nH0xsG/OaMAyVY4A86LUFdTlx2ih+BLWjmdxR/39YzApR8itdAV0gEN22Ky1JMnRQ="
               ),
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==")
            );
      }
   }

   public String formatRoundedCornerUnicodeWithColor(String var1, String var2) {
      if (((-379715762 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String var3 = this.stripRoundedCornerColorTags(var2);
         if (var3.isBlank()) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
         } else {
            String var4 = this.normalizeHexColor(var1);
            return var4.isBlank() ? var3 : Xqedii_2pkhablxqui9m(var4, var3);
         }
      }
   }

   public boolean applyRoundedCornerUnicodeColor(Map<String, Object> var1, String var2) {
      if ((1040887054 * (1040887054 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (!this.isRoundedRawTarget(var1, null)) {
         return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 25) != 0);
      } else {
         String var3 = this.resolveBorderRadiusModeFromStoredValue(
            this.stringValue(this.readMapPathValue(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("QEjLyJuikMqC9mFbuvAjqj5cLN9O+2FjgDfY3zIW+qabQlx7c5prevlCAXk=")))
         );
         String var4 = var3.isBlank() ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("tQUBuNFFR3dkiQoobi1iwA9omebwzhjFH8DgaWNSN4IAmVgHoZk6jTlP") : var3;
         String[] var5 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("By5q5besaxSFT9aOarh+VnLDM10y2NZAT30OXaYpHiq4ZAKe").equals(var4)
            ? new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 26)]
            : this.resolveBorderRadiusGlyphSet(var4);
         boolean var6 = false;

         for (int var7 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1968988613, 5); var7 < Xqedii_3gdqlgv.Xqedii_219lyve.length; var7++) {
            String var8 = Xqedii_3gdqlgv.Xqedii_219lyve[var7];
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
      }
   }

   public boolean syncRoundedCornerUnicodeColorInheritance(Map<String, Object> var1) {
      if ((1932117690 ^ 1932117690) != 0) {
         throw new IllegalStateException();
      } else if (!this.isRoundedRawTarget(var1, null)) {
         return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 7) != 0);
      } else {
         boolean var2 = false;
         String[] var3 = Xqedii_3gdqlgv.Xqedii_219lyve;
         int var4 = var3.length;

         for (int var5 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1461219614, 11); var5 < var4; var5++) {
            String var6 = var3[var5];
            String var7 = this.stripRoundedCornerColorTags(this.readRoundedCornerUnicodeByKey(var1, var6));
            if (!var7.isBlank()) {
               var2 |= this.setMapPathStringValue(var1, this.resolveRoundedCornerUnicodeWritePath(var6), var7);
            }
         }

         return var2;
      }
   }

   public String resolveTargetHoverColorWritePath(Map<String, Object> var1) {
      if (((992439776 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MWsvgonQkTTAA5craeHteqFSriKo8OoOYbPKrKpM5aIJkF+dzA0dv72xznD0/jW+AwU="))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MWsvgonQkTTAA5craeHteqFSriKo8OoOYbPKrKpM5aIJkF+dzA0dv72xznD0/jW+AwU=");
      } else if (this.hasMapPath(
         var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("bNKIsEslxjlPzYhcw9OQk/RCEtzLd/4a1HZuu3w9yJcEsYHBHaiao1974hLqPykY+xtJokHocYFesyPZwCc=")
      )) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("bNKIsEslxjlPzYhcw9OQk/RCEtzLd/4a1HZuu3w9yJcEsYHBHaiao1974hLqPykY+xtJokHocYFesyPZwCc=");
      } else if (this.hasMapPath(
         var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("KdQbvCCNkxbcYDcPVf2xWRaVg8bPMe3v3T4Py5KLTyx84GFwZA5Hgf9JNkzIjwIH6qHETevSRVl3Q5F5D79UOQ==")
      )) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("KdQbvCCNkxbcYDcPVf2xWRaVg8bPMe3v3T4Py5KLTyx84GFwZA5Hgf9JNkzIjwIH6qHETevSRVl3Q5F5D79UOQ==");
      } else if (this.hasMapPath(
         var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("2eO8TupijXQhOp+SZGejxGVTrYcxoJk5OFtIGvTUXUWU1HCswQt6s/oiyDp2JecKIYvbuhtwR8/5axAiq7XeJ48BhdKeaMzLzTPShQ==")
      )) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("2eO8TupijXQhOp+SZGejxGVTrYcxoJk5OFtIGvTUXUWU1HCswQt6s/oiyDp2JecKIYvbuhtwR8/5axAiq7XeJ48BhdKeaMzLzTPShQ==");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("5PqHhLUnJdzxrUuJleATTyGs+S0kVZXyiuaU90lvCAHk9cCE2HX173Nad62FkiuO"))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("5PqHhLUnJdzxrUuJleATTyGs+S0kVZXyiuaU90lvCAHk9cCE2HX173Nad62FkiuO");
      } else if (this.hasMapPath(
         var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("FTxIHTbTNGLP8hlKFzYg/MOyHeS6MGeuDr07It7gEVNiaNVuiz/TZ0YesYJq2OhBVuNb/+xN+XcmeLnNZ7g=")
      )) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("FTxIHTbTNGLP8hlKFzYg/MOyHeS6MGeuDr07It7gEVNiaNVuiz/TZ0YesYJq2OhBVuNb/+xN+XcmeLnNZ7g=");
      } else {
         return var1.containsKey(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nZrCmUaxPCoJmHcSl1ZTpM5VHcQFSUq4a+UfqKAR8KHPSOuZjA76XA=="))
            ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("KdQbvCCNkxbcYDcPVf2xWRaVg8bPMe3v3T4Py5KLTyx84GFwZA5Hgf9JNkzIjwIH6qHETevSRVl3Q5F5D79UOQ==")
            : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MWsvgonQkTTAA5craeHteqFSriKo8OoOYbPKrKpM5aIJkF+dzA0dv72xznD0/jW+AwU=");
      }
   }

   public String resolveTargetHoverEffectWritePath(Map<String, Object> var1) {
      if (((2056409148 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("rmx0ivpbDl1DlLnzJ/YpSRgN0qokprkmDFRbSCPQwTALMZasNeC+MtKW5HACBBzz9zWGFQ=="))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("rmx0ivpbDl1DlLnzJ/YpSRgN0qokprkmDFRbSCPQwTALMZasNeC+MtKW5HACBBzz9zWGFQ==");
      } else if (this.hasMapPath(
         var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xvTZluU8JTd4E0pJEJmW6Nsheq+DC2X+R1pUk2ikwmXD9Eec8LtWEtEmycl6oiVgW43u9kugZWYaTXxZkD8MSg==")
      )) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xvTZluU8JTd4E0pJEJmW6Nsheq+DC2X+R1pUk2ikwmXD9Eec8LtWEtEmycl6oiVgW43u9kugZWYaTXxZkD8MSg==");
      } else if (this.hasMapPath(
         var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("t5eaJChVFTRYXms6AXsWdesxxIimaqcAn8m7huK9/bUIe2bB2QjqL6SXSmGCXVLPmaIaRj2uY9w4AsXIw1zN11uo")
      )) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("t5eaJChVFTRYXms6AXsWdesxxIimaqcAn8m7huK9/bUIe2bB2QjqL6SXSmGCXVLPmaIaRj2uY9w4AsXIw1zN11uo");
      } else if (this.hasMapPath(
         var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("oziN/R+FGKJL0j33L6sPWlogc+OGyt+fJqWHwEEV1ghzgl7QGI43sK/FLmR2UsHxZg2PGP2HdwZXyWR/PzfO9o4CAZf1tzN2EUKhrSjt")
      )) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("oziN/R+FGKJL0j33L6sPWlogc+OGyt+fJqWHwEEV1ghzgl7QGI43sK/FLmR2UsHxZg2PGP2HdwZXyWR/PzfO9o4CAZf1tzN2EUKhrSjt");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("EI1gjoZIcnO9iHv9+2oh8CfLB5OgyE9XRQFGO1j9pX6zpv5u959wXfqXuih18f3XZsA="))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("EI1gjoZIcnO9iHv9+2oh8CfLB5OgyE9XRQFGO1j9pX6zpv5u959wXfqXuih18f3XZsA=");
      } else if (this.hasMapPath(
         var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("F7edfx3sRUZoicd0zcaE0/TH/9WOymvlLUVnP7dntmbxyJhm4MUQA2UpYNuf3YqNdHVDaNQn38hajoSPcrxn+g==")
      )) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("F7edfx3sRUZoicd0zcaE0/TH/9WOymvlLUVnP7dntmbxyJhm4MUQA2UpYNuf3YqNdHVDaNQn38hajoSPcrxn+g==");
      } else {
         return var1.containsKey(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nZrCmUaxPCoJmHcSl1ZTpM5VHcQFSUq4a+UfqKAR8KHPSOuZjA76XA=="))
            ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("t5eaJChVFTRYXms6AXsWdesxxIimaqcAn8m7huK9/bUIe2bB2QjqL6SXSmGCXVLPmaIaRj2uY9w4AsXIw1zN11uo")
            : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("rmx0ivpbDl1DlLnzJ/YpSRgN0qokprkmDFRbSCPQwTALMZasNeC+MtKW5HACBBzz9zWGFQ==");
      }
   }

   @Override
   public Map<String, Object> resolveRawTargetByTargetId(EditorSession var1, String var2) {
      if ((-1798748871 * -1798748871 - -1798748871 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var3 = this.resolveRawTargetPathByTargetId(var1, var2);
         return var3.isBlank() ? null : this.resolveRawMapAtPath(var1.rawBlocks, var3);
      }
   }

   @Override
   public String resolveRawTargetPathByTargetId(EditorSession var1, String var2) {
      if ((-1016357809 ^ -1016357809) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 18)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 24)] = this.resolveAnimationBaseTargetId(var2);
         String var3 = this.firstNonBlank(var10001);
         String var4 = this.resolveAnimationEffectiveScopePageKey(var1, var2);
         if (var1 != null && !var3.isBlank()) {
            LinkedHashSet var5 = this.collectRawTargetCandidatePathsByTargetId(var1, var2);
            if (var5.isEmpty()) {
               return this.resolveRawMapAtPath(var1.rawBlocks, var3) != null
                  ? var3
                  : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
            } else {
               String var6 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
               int var7 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1242728485, 16);
               int var8 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-847632387, 25);

               for (String var10 : (Iterable<String>) (var5)) {

                  var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2079185655, 31)];
                  var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 17)] = var10;
                  String var11 = this.firstNonBlank(var10001);
                  if (!var11.isBlank() && (var4.isBlank() || this.doesTargetPathMatchAnimationScope(var1, var11, var2))) {
                     Map var12 = this.resolveRawMapAtPath(var1.rawBlocks, var11);
                     if (var12 != null && !var12.isEmpty()) {
                        int var13 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-76104258, 5);
                        if (this.hasAnimationTimelineData(var12)) {
                           var13 += 100;
                        }

                        if (this.isRawTargetInheritedRoot(var12)) {
                           var13 += 10;
                        }

                        int var14 = this.resolveLayerTargetDepth(var11);
                        if (var6.isBlank() || var13 > var7 || var13 == var7 && var14 < var8 || var13 == var7 && var14 == var8 && var11.length() < var6.length()
                           )
                         {
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
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
         }
      }
   }

   public LinkedHashSet<String> collectRawTargetCandidatePathsByTargetId(EditorSession var1, String var2) {
      if (((1049416439 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         LinkedHashSet var3 = new LinkedHashSet();
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-310657546, 14)];
         var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1092767889, 3)] = this.resolveAnimationBaseTargetId(var2);
         String var4 = this.firstNonBlank(var10001);
         if (var1 != null && !var4.isBlank()) {
            HoverElement var5 = this.findFirstByTargetId(var1, var2);
            var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1859632095, 10)];
            var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-299200904, 31)] = var5 == null ? null : var5.targetPath;
            String var6 = this.firstNonBlank(var10001);
            if (!var6.isBlank()) {
               var3.add(var6);
            }

            if (var1.elements != null) {
               for (HoverElement var8 : var1.elements) {
                  if (var8 != null) {
                     var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-984494275, 4)];
                     var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 15)] = this.targetIdOf(var8);
                     var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 9)] = var8.targetId;
                     String var9 = this.firstNonBlank(var10001);
                     if (this.equalsNullable(var9, var4)) {
                        var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-310657546, 14)];
                        var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 9)] = var8.targetPath;
                        String var10 = this.firstNonBlank(var10001);
                        if (!var10.isBlank() && this.doesTargetPathMatchAnimationScope(var1, var10, var2)) {
                           var3.add(var10);
                        }
                     }
                  }
               }
            }

            List var12 = var1.renderBlocks;
            if ((var12 == null || var12.isEmpty()) && var1.rawBlocks != null && !var1.rawBlocks.isEmpty()) {
               var12 = (List)this.resolveRenderableBlocks(var1.rawBlocks, var1.components, var1);
            }

            if (var12 != null) {
               for (Map var15 : (Iterable<Map>) (var12)) {

                  if (var15 != null && !var15.isEmpty()) {
                     var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1614112342, 26)];
                     var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1090816323, 29)] = this.stringValue(
                        this.readMapPathValue(
                           var15,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("1EPBMuh826D9CIl1FZUN2Kl20JDWGGZ6MqLq6FLgQ7OikdkEZFnM0OysFdvfM7Pt86Tjl+YiyYoe1hQ0T2NUiw==")
                        )
                     );
                     String var17 = this.firstNonBlank(var10001);
                     if (this.equalsNullable(var17, var4)) {
                        var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1968988645, 5)];
                        var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 22)] = this.stringValue(
                           this.readMapPathValue(
                              var15,
                              Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vTZxD2wT3CYYdd00eOZqNNVTLVvaAYwtTM95mppxLUsjhGdYarCKKwOfOYle7+LgC37Bm4lVrGhq7GBDm8a7dg6CTts=")
                           )
                        );
                        String var11 = this.firstNonBlank(var10001);
                        if (!var11.isBlank() && this.doesTargetPathMatchAnimationScope(var1, var11, var2)) {
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

               for (String var18 : (Iterable<String>) (var3)) {

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
   }

   public String resolveRawTargetInheritedRootPath(EditorSession var1, String var2) {
      if (((502422340 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         String var3 = var2;

         while (!var3.isBlank()) {
            Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3);
            if (this.isRawTargetInheritedRoot(var4)) {
               return var3;
            }

            if (var4 != null && !this.isRawUiImageGlyphBlock(var4)) {
               break;
            }

            int var5 = var3.lastIndexOf(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("k3GrTmS6Pq3iYG2Wrb3BT0AZfFRxDlQvHtcN7tj7/HyTH6UkraJgvDZWfFa1nPTW"));
            if (var5 < 0) {
               break;
            }

            var3 = var3.substring(Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(356990071, 6), var5);
         }

         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      } else {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      }
   }

   public boolean hasAnimationTimelineData(Map<String, Object> var1) {
      if ((-1423841602 * -1423841602 + -1423841602 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         Map var2 = this.readAnimationTimelineRowMap(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SyYgQWvxrTbODwScq4q4JZdgYE9MbJQPXifJ7T83s4gBV2T549leLMwzc94="));
         if (var2 != null && !var2.isEmpty()) {
            return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221334, 7) != 0);
         } else {
            Map var3 = this.readAnimationTimelineRowMap(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("sCidJbYqpqxS46UGiXHbmgSjw8fK0m3q8EeScdz2ke/qZUrRi5s="));
            if (var3 != null && !var3.isEmpty()) {
               return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1416872817, 10) != 0);
            } else {
               Map var4 = this.readAnimationTimelineRowMap(
                  var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("N9hWuxfafgycFZhRTf5tv+Fmz6rj6W0fWy/6yRy1v5qdFcaOH/RQXL6nFjk=")
               );
               if (var4 != null && !var4.isEmpty()) {
                  return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1592753595, 20) != 0);
               } else {
                  Map var5 = this.readAnimationTimelineRowMap(
                     var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vi7g6UwkyiEgaqiv1agk8wWQSzLI0FcJCZb5vu6RL4Xh+np6OGZtkO1W")
                  );
                  return ((var5 != null && !var5.isEmpty()
                     ? Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 17)
                     : Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-605046933, 25)) != 0);
               }
            }
         }
      } else {
         return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-474437063, 25) != 0);
      }
   }

   public boolean isRawTargetInheritedRoot(Map<String, Object> var1) {
      if ((873661031 * (873661031 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         Object var2 = this.readMapPathValue(
            var1,
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "gNnVrYRQV+IroREmT3SKURL4oQ4TOMVWGSaBxv2bbv29xHSh+x7HCFEdDoNq3MqAGgUSRqdACDX2kmI6ASdI3e3ma+NfLx9Q2orVzBzr5hfoXVSAKh+xnt5yVVNvie0cFVk="
            )
         );
         return ((!this.parseBooleanFlag(var2, (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2109431519, 4) != 0)) && !this.isRawImageGroupRoot(var1)
            ? Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 26)
            : Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-152208451, 6)) != 0);
      } else {
         return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 21) != 0);
      }
   }

   @Override
   public String readTargetRawType(EditorSession var1, String var2) {
      if ((202461223 * 202461223 + 202461223 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Map var3 = this.resolveRawTargetByTargetId(var1, var2);
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1968988645, 5)];
         var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-76104258, 5)] = this.stringValue(
            this.readMapPathValue(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("wnj7uF3hcOoAks+8+3wFksoVbSbHn47JPMeDyYMSgiPoAf2O"))
         );
         String var4 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
         if (!var4.isBlank()) {
            return var4;
         } else {
            HoverElement var5 = this.findFirstByTargetId(var1, var2);
            var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1680959062, 18)];
            var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-68297994, 31)] = var5 == null ? null : var5.type;
            return this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
         }
      }
   }

   public Material resolveSidebarMaterialInput(String var1) {
      if ((-2048456858 * (-2048456858 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isBlank()) {
         String var2 = this.stripSidebarCustomModelDataSuffix(var1);
         if (var2.isBlank()) {
            return null;
         } else if (this.isSidebarPlayerHeadToken(var2)) {
            return Material.PLAYER_HEAD;
         } else {
            Material var3 = Material.matchMaterial(var2);
            if (var3 == null && var2.contains(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("K56WV4jVkppwd+cowiUsJYs+mONZ72f5SHG4GZbh"))) {
               var3 = Material.matchMaterial(
                  var2.substring(var2.indexOf(Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-598401845, 0)) + Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221202, 2))
               );
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

   public int findCustomModelDataSeparator(String var1) {
      if ((524848163 * 524848163 + 524848163 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Xqedii_2ze73vg.Xqedii_18u831nn38lo(-1681221207, 11);
      } else {
         String var2 = var1.trim();
         if (var2.isBlank()) {
            return Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855203, 5);
         } else {
            int var3 = var2.lastIndexOf(Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-666640530, 18));
            return var3 > 0 && var3 < var2.length() - Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1015982989, 26)
               ? var3
               : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(474437062, 25);
         }
      }
   }

   public Integer parseSidebarCustomModelDataValue(String var1) {
      if ((-1578532350 * -1578532350 + -1578532350 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isBlank()) {
         double var2 = this.parseDouble(var1, Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(6203663098853594672L, 8)));
         if (!Double.isFinite(var2)) {
            return null;
         } else {
            long var4 = Math.round(var2);
            if (var4 >= Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-2198104497299394645L, 22) && var4 <= Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819751370452585498L, 48)) {
               return Math.abs(var2 - var4) > Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-5465415862814065401L, 26)) ? null : (int)var4;
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   public Integer readCustomModelDataFromToken(String var1) {
      if (((489975702 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         int var2 = this.findCustomModelDataSeparator(var1);
         if (var2 < 0) {
            return null;
         } else {
            String var3 = var1.trim();
            String var4 = var3.substring(var2 + Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1195128597, 14)).trim();
            return this.parseSidebarCustomModelDataValue(var4);
         }
      }
   }

   public String stripSidebarCustomModelDataSuffix(String var1) {
      if (((-1890402783 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      } else {
         String var2 = var1.trim();
         if (var2.isBlank()) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
         } else {
            int var3 = this.findCustomModelDataSeparator(var2);
            return var3 < 0 ? var2 : var2.substring(Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 27), var3).trim();
         }
      }
   }

   public boolean isSidebarPlayerHeadToken(String var1) {
      if ((-1883155223 * -1883155223 + -1883155223 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var2 = this.stripSidebarCustomModelDataSuffix(var1);
         if (var2.isBlank()) {
            return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-605046933, 25) != 0);
         } else {
            return (var2.equalsIgnoreCase(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("HG1TxzoiLVGaajgrMOLCVc9LMTYLz7DC5MUjBlbvDLHnQ+1V0S07n9jPgiIZp53EMwk="))
               ? (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-142873126, 23) != 0)
               : var2.regionMatches(
                  (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-273191971, 1) != 0),
                  Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-545408162, 28),
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Z/dgkp0iyApEBaP2ljs6F5XEk3Gnt2JHY8tUQh0uBrENf+4L7HmnsmMTA2I+doYbgeIZXg=="),
                  Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 4),
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Z/dgkp0iyApEBaP2ljs6F5XEk3Gnt2JHY8tUQh0uBrENf+4L7HmnsmMTA2I+doYbgeIZXg==").length()
               ));
         }
      }
   }

   public String extractSidebarPlayerHeadOwner(String var1) {
      if ((-380525704 ^ -380525704) != 0) {
         throw new IllegalStateException();
      } else {
         String var2 = this.stripSidebarCustomModelDataSuffix(var1);
         return !var2.regionMatches(
               (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(924141372, 30) != 0),
               Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-575705154, 11),
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Z/dgkp0iyApEBaP2ljs6F5XEk3Gnt2JHY8tUQh0uBrENf+4L7HmnsmMTA2I+doYbgeIZXg=="),
               Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1372528069, 12),
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Z/dgkp0iyApEBaP2ljs6F5XEk3Gnt2JHY8tUQh0uBrENf+4L7HmnsmMTA2I+doYbgeIZXg==").length()
            )
            ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==")
            : var2.substring(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Z/dgkp0iyApEBaP2ljs6F5XEk3Gnt2JHY8tUQh0uBrENf+4L7HmnsmMTA2I+doYbgeIZXg==").length()).trim();
      }
   }

   public String buildSidebarItemToken(Material var1, String var2, Integer var3) {
      if ((-912541188 * -912541188 - -912541188 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1 != Material.AIR) {
         String var4;
         if (var1 == Material.PLAYER_HEAD) {
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1195128597, 14)];
            var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 27)] = var2;
            String var5 = this.firstNonBlank(var10001);
            var4 = var5.isBlank()
               ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("HG1TxzoiLVGaajgrMOLCVc9LMTYLz7DC5MUjBlbvDLHnQ+1V0S07n9jPgiIZp53EMwk=")
               : Xqedii_3mrld3r9peffd(var5);
         } else {
            var4 = var1.name();
         }

         return var3 != null && var3 >= 0 ? Xqedii_3rrusnakidpfz(var4, var3) : var4;
      } else {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      }
   }

   public String normalizeSidebarItemTokenInput(String var1) {
      if ((1861858186 * 1861858186 + 1861858186 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isBlank()) {
         String var2 = var1.trim();
         String var3 = this.stripSidebarCustomModelDataSuffix(var2);
         Integer var4 = this.readCustomModelDataFromToken(var2);
         if (this.findCustomModelDataSeparator(var2) >= 0 && var4 == null) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
         } else {
            Material var5 = this.resolveSidebarMaterialInput(var3);
            if (var5 != null && var5 != Material.AIR) {
               String var6 = var5 == Material.PLAYER_HEAD
                  ? this.extractSidebarPlayerHeadOwner(var3)
                  : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
               return this.buildSidebarItemToken(var5, var6, var4);
            } else {
               return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
            }
         }
      } else {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      }
   }

   public String readTargetItemToken(EditorSession var1, String var2) {
      if (((758296783 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Map var3 = this.resolveRawTargetByTargetId(var1, var2);
         String var4 = this.readTargetItemMaterialToken(var3);
         String var5 = this.normalizeSidebarItemTokenInput(var4);
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830856194, 10)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 7)] = var5;
         var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221207, 0)] = var4;
         return this.firstNonBlank(var10001);
      }
   }

   public Material readTargetItemMaterial(EditorSession var1, String var2) {
      if ((1708622130 * (1708622130 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Map var3 = this.resolveRawTargetByTargetId(var1, var2);
         String var4 = this.readTargetItemMaterialToken(var3);
         return this.resolveSidebarMaterialInput(var4);
      }
   }

   public String readTargetStringValue(Map<String, Object> var1, String var2) {
      if (((-439132189 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Object var3 = this.readMapPathValue(var1, var2);
         return !(var3 instanceof Map) && !(var3 instanceof Collection)
            ? this.stringValue(var3)
            : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      }
   }

   public String readTargetItemMaterialToken(Map<String, Object> var1) {
      if ((1229491648 * 1229491648 - 1229491648 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-1316628477, 31)];
         var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 5)] = this.readTargetStringValue(
            var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("m20dwIWjyf0aDtr4zwdfCm0mupLuImzkF/CHhq0sUWxhFRANXu1F9BG61n0E1vByBLFD7aRF")
         );
         var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221214, 3)] = this.readTargetStringValue(
            var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Vpy0z1b20Ppa2SxhvPnE5i7oHIHHkX44YX7RRkf6a+RTaYLsyZG72dmyssM=")
         );
         var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221202, 1)] = this.readTargetStringValue(
            var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("3c30+jCGGXJcJMBz3blK7qCbbQVRQuGZx9oU2urGZ62Fw2XimiV6kcedIbVH9WUY2SpB2Mv1mSmu1a49oGh4fy4B3qM=")
         );
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(767906255, 28)] = this.readTargetStringValue(
            var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nzOViiG66pH5ju9GzQDVv+mL6F41jU1DPV25wSl6y5/+/pf/QfynY/ZoGOlS/tGpwdQekvd3zBd5zw==")
         );
         String var2 = this.firstNonBlank(var10001);
         if (!var2.isBlank()) {
            return var2;
         } else {
            Object var3 = this.readMapPathValue(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8y2oGcYV9iDNWyOGTUS+o03pzLZnRSDoG8yvUuICa0B8Id7y"));
            if (var3 instanceof Map var4) {
               String var6 = this.stringValue(var4.get(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Vpy0z1b20Ppa2SxhvPnE5i7oHIHHkX44YX7RRkf6a+RTaYLsyZG72dmyssM=")));
               if (!var6.isBlank()) {
                  return var6;
               }
            } else {
               String var5 = this.stringValue(var3);
               if (!var5.isBlank()) {
                  return var5;
               }
            }

            Object var7 = this.readMapPathValue(
               var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("s7tNCiCq8nDcX1cnRt1hMnUFsO+CNrXGgacY+E8178BPUAr3HHJ9oXCGn2R0VYgm0xI=")
            );
            return var7 instanceof Map var8
               ? this.stringValue(var8.get(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Vpy0z1b20Ppa2SxhvPnE5i7oHIHHkX44YX7RRkf6a+RTaYLsyZG72dmyssM=")))
               : this.stringValue(var7);
         }
      } else {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      }
   }

   @Override
   public String readTargetCustomName(EditorSession var1, String var2) {
      if ((729722118 ^ 729722118) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         Map var3 = this.resolveRawMapAtPath(var1.rawBlocks, var2);
         if (var3 == null) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
         } else {
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(562419714, 28)];
            var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 14)] = this.stringValue(
               this.readMapPathValue(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("e9gpcfIE+hfP84a4kSt5a9LuiQ3YEe3sMF+i+dqNmR0STtoV"))
            );
            var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1680959062, 18)] = this.stringValue(
               this.readMapPathValue(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("mon9QKxvJAJEB7F68AmsdYGfbDgdcEaU6Usgc1/IKnFbhp9lzfo8NviEjCEYPGX4Ass="))
            );
            return this.firstNonBlank(var10001);
         }
      } else {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      }
   }

   public boolean setTargetName(EditorSession var1, String var2, String var3) {
      if ((551016287 * (551016287 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         HoverElement var4 = this.findFirstByTargetId(var1, var2);
         if (var4 != null && var4.targetPath != null && !var4.targetPath.isBlank()) {
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 18)];
            var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1195144981, 14)] = var3;
            String var5 = this.firstNonBlank(var10001).trim();
            if (var5.isEmpty()) {
               return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1992146683, 13) != 0);
            } else {
               String var6 = this.buildUniqueTargetDisplayName(var1, var5, var2);
               if (var6.isBlank()) {
                  return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-410066264, 19) != 0);
               } else {
                  Map var7 = this.resolveRawMapAtPath(var1.rawBlocks, var4.targetPath);
                  if (var7 == null) {
                     return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 23) != 0);
                  } else {
                     String var8 = !this.hasMapPath(var7, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("e9gpcfIE+hfP84a4kSt5a9LuiQ3YEe3sMF+i+dqNmR0STtoV"))
                           && var7.containsKey(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nZrCmUaxPCoJmHcSl1ZTpM5VHcQFSUq4a+UfqKAR8KHPSOuZjA76XA=="))
                        ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("mon9QKxvJAJEB7F68AmsdYGfbDgdcEaU6Usgc1/IKnFbhp9lzfo8NviEjCEYPGX4Ass=")
                        : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("e9gpcfIE+hfP84a4kSt5a9LuiQ3YEe3sMF+i+dqNmR0STtoV");
                     String var9 = this.stringValue(this.readMapPathValue(var7, var8));
                     if (var9.equals(var6)) {
                        return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1195144981, 14) != 0);
                     } else {
                        this.setMapPathValue(var7, var8, var6);
                        return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1689609814, 23) != 0);
                     }
                  }
               }
            }
         } else {
            return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 16) != 0);
         }
      }
   }

   public String buildUniqueTargetDisplayName(EditorSession var1, String var2, String var3) {
      if (((1855445296 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 26)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 7)] = var2;
         String var4 = this.firstNonBlank(var10001).trim();
         if (var4.isEmpty()) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
         } else if (var1 == null) {
            return var4;
         } else {
            var1.renderBlocks = this.resolveRenderableBlocks(var1.rawBlocks, var1.components, var1);
            List var5 = this.collectLayerEntries(var1);
            if (var5 != null && !var5.isEmpty()) {
               var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 2)];
               var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1874779567, 27)] = var3;
               String var6 = this.firstNonBlank(var10001).trim();
               HashSet var7 = new HashSet();

               for (EditorLayersLayerPart.LayerEntry var9 : (Iterable<EditorLayersLayerPart.LayerEntry>) (var5)) {

                  if (var9 != null && var9.displayName != null && !var9.displayName.isBlank()) {
                     if (!var6.isBlank()) {
                        String[] var10002 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1010242887, 22)];
                        var10002[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-474437063, 25)] = var9.targetId;
                        if (var6.equals(this.firstNonBlank(var10002))) {
                           continue;
                        }
                     }

                     var7.add(var9.displayName.trim().toLowerCase(Locale.ROOT));
                  }
               }

               String var10 = var4;

               for (int var11 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1809706935, 17); var7.contains(var10.toLowerCase(Locale.ROOT)); var11++) {
                  var10 = Xqedii_3r7vnca00nkjb(var4, var11);
               }

               return var10;
            } else {
               return var4;
            }
         }
      }
   }

   public boolean setTargetLogicalId(EditorSession var1, String var2, String var3) {
      if (((-872439586 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681352278, 17)];
         var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2071852781, 22)] = var3;
         String var4 = this.firstNonBlank(var10001).trim();
         if (var4.isEmpty()) {
            return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 23) != 0);
         } else {
            Map var5 = this.resolveRawTargetByTargetId(var1, var2);
            if (var5 == null) {
               return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(2028874382, 23) != 0);
            } else {
               var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681217110, 12)];
               var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-545408162, 28)] = this.stringValue(
                  this.readMapPathValue(var5, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8L3tcEhVVFqLxabYTyk8caw0mwVPZNvm57Sm1TVaS/w="))
               );
               var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 17)] = this.stringValue(
                  this.readMapPathValue(var5, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Fvjt5nsIrc3HIzAq1PAh7tmiEmHAXomEQNHPOMc/beUMMCYbYUzXSNvPbyHvfQ=="))
               );
               int var10003 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855682, 9);
               String[] var10005 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1680959062, 18)];
               var10005[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-410066264, 19)] = var2;
               var10001[var10003] = this.firstNonBlank(var10005);
               String var6 = this.firstNonBlank(var10001);
               if (var6.equals(var4)) {
                  return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 4) != 0);
               } else {
                  String var7 = !this.hasMapPath(var5, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8L3tcEhVVFqLxabYTyk8caw0mwVPZNvm57Sm1TVaS/w="))
                        && var5.containsKey(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nZrCmUaxPCoJmHcSl1ZTpM5VHcQFSUq4a+UfqKAR8KHPSOuZjA76XA=="))
                     ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Fvjt5nsIrc3HIzAq1PAh7tmiEmHAXomEQNHPOMc/beUMMCYbYUzXSNvPbyHvfQ==")
                     : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8L3tcEhVVFqLxabYTyk8caw0mwVPZNvm57Sm1TVaS/w=");
                  this.setMapPathValue(var5, var7, var4);
                  return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1144350294, 29) != 0);
               }
            }
         }
      }
   }

   @Override
   public String findTargetIdByPath(EditorSession var1, String var2) {
      if ((-2142861373 * (-2142861373 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         for (HoverElement var4 : var1.elements) {
            if (var4 != null && var4.targetPath != null && var2.equals(var4.targetPath)) {
               String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221204, 1)];
               var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-273191973, 1)] = var4.targetId;
               String var5 = this.firstNonBlank(var10001);
               if (!var5.isBlank()) {
                  return var5;
               }
            }
         }

         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      } else {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      }
   }

   public boolean isReservedEditorTargetId(EditorSession var1, String var2, String var3) {
      if (((1120977139 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681223254, 11)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 3)] = var2;
         String var4 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
         if (var4.isBlank()) {
            return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1815438934, 27) != 0);
         } else {
            String[] var10002 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 10)];
            var10002[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 30)] = var3;
            if (var4.equals(this.firstNonBlank(var10002).trim().toLowerCase(Locale.ROOT))) {
               return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 0) != 0);
            } else if (!var4.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("if8rnc7NwJuhpoMI5NrBK4D/NpZ+8iXKwzpYj6oUyyQURsy5bd1A5rTv"))
               && !var4.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("/zNLiYJjKfHLOlQY7mci2eIvuJ5M6fNSy3s+e5QuUOcIm+m1jzU8Qw=="))
               && !var4.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("cE0Lu8NCGT47TS9KoYJO6U70uKIlFZubNXSCZfJkVFgK9w8ElkjVdpVIw7h2U5/y1WMc4w=="))
               && !var4.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("czphWAaKvZOzPWY8FmvQTynKnIYVsH5E6JHHvBJlbB8Ys/x/YFmJ/bBCaONgxwgcgaY="))
               && !var4.startsWith(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("5f7djRo3vwXmtAvZLf3Dk5R4CNBGK9TK1yD4lCLzyiZEtVAxkH5CV5Zofqui6XE8ei5PImc2t0ZpT//M"))
               && !var4.startsWith(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GKkzeBnad1nLsE0XPi46ZYsXf0MMpaaiuIdikrVXtusQi/1buD3DgrzzR5MgdjXkJzpuxOoY"))) {
               String[] var5 = Xqedii_3uxgwyg.Xqedii_3mtt67t;
               int var6 = var5.length;

               for (int var7 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-948874125, 26); var7 < var6; var7++) {
                  String var8 = var5[var7];
                  var10002 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681204822, 14)];
                  var10002[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1942450516, 18)] = var8;
                  if (var4.equals(this.firstNonBlank(var10002).toLowerCase(Locale.ROOT))) {
                     return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 17) != 0);
                  }
               }

               if (var1 != null && var1.shellBlocks != null) {
                  for (Map var10 : var1.shellBlocks) {
                     var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-136595986, 0)];
                     var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 19)] = this.stringValue(
                        this.readMapPathValue(var10, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8L3tcEhVVFqLxabYTyk8caw0mwVPZNvm57Sm1TVaS/w="))
                     );
                     String var11 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
                     if (!var11.isBlank() && var4.equals(var11)) {
                        return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1697998422, 24) != 0);
                     }
                  }
               }

               return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 26) != 0);
            } else {
               return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(485547093, 16) != 0);
            }
         }
      }
   }

   public boolean hasConflictingContentTargetId(EditorSession var1, String var2, String var3) {
      if ((-1180246834 * -1180246834 + -1180246834 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(356990007, 6)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 23)] = var2;
         String var4 = this.firstNonBlank(var10001).trim();
         if (var4.isBlank()) {
            return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-507991495, 25) != 0);
         } else {
            for (EditorLayersLayerPart.LayerEntry var6 : this.collectLayerEntries(var1)) {
               if (var6 != null && var6.targetId != null && !var6.targetId.isBlank() && !var6.targetId.equals(var3) && var6.targetId.equalsIgnoreCase(var4)) {
                  return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1242630181, 16) != 0);
               }
            }

            return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1092767889, 3) != 0);
         }
      }
   }

   public boolean setTargetText(EditorSession var1, String var2, String var3) {
      if ((-568321697 ^ -568321697) != 0) {
         throw new IllegalStateException();
      } else {
         HoverElement var4 = this.findFirstByTargetId(var1, var2);
         if (var4 != null && var4.targetPath != null && !var4.targetPath.isBlank()) {
            String var10000 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8");
            String[] var10002 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681223254, 11)];
            var10002[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 10)] = var4.type;
            if (!var10000.equalsIgnoreCase(this.firstNonBlank(var10002))) {
               return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(971225258, 17) != 0);
            } else {
               Map var5 = this.resolveRawMapAtPath(var1.rawBlocks, var4.targetPath);
               if (var5 == null) {
                  return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1439046728, 9) != 0);
               } else {
                  String var6;
                  if (this.hasMapPath(var5, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8"))) {
                     var6 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8");
                  } else if (this.hasMapPath(var5, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xWcpDG4PrUbIwE8MfEYzqIDAMW3M0LXhbXRrt4thZ4Afl9FmLbvhhvBz68uP0gSXq/E="))) {
                     var6 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xWcpDG4PrUbIwE8MfEYzqIDAMW3M0LXhbXRrt4thZ4Afl9FmLbvhhvBz68uP0gSXq/E=");
                  } else {
                     var6 = var5.containsKey(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nZrCmUaxPCoJmHcSl1ZTpM5VHcQFSUq4a+UfqKAR8KHPSOuZjA76XA=="))
                        ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xWcpDG4PrUbIwE8MfEYzqIDAMW3M0LXhbXRrt4thZ4Afl9FmLbvhhvBz68uP0gSXq/E=")
                        : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8");
                  }

                  String var7 = var3 == null ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==") : var3;
                  String var8 = this.stringValue(this.readMapPathValue(var5, var6));
                  if (this.equalsNullable(var8, var7)) {
                     return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-474437063, 25) != 0);
                  } else {
                     this.setMapPathValue(var5, var6, var7);
                     return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-152208451, 6) != 0);
                  }
               }
            }
         } else {
            return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2113334651, 30) != 0);
         }
      }
   }

   @Override
   public boolean setTargetOutlineColor(EditorSession var1, String var2, String var3) {
      if (((-1361495296 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         HoverElement var4 = this.findFirstByTargetId(var1, var2);
         if (var4 != null && var4.targetPath != null && !var4.targetPath.isBlank()) {
            Map var5 = this.resolveRawMapAtPath(var1.rawBlocks, var4.targetPath);
            if (var5 == null) {
               return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 23) != 0);
            } else {
               String var6 = this.normalizeHexColor(var3);
               if (var6.isBlank()) {
                  return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 24) != 0);
               } else {
                  String var7;
                  if (this.hasMapPath(var5, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZiCT1Cbhw0BkCBETRETw6GisNl3+G+LgZPihItcIXd4xV4JTvBFwiJ6326hO5MjV/BpgqGnG"))) {
                     var7 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZiCT1Cbhw0BkCBETRETw6GisNl3+G+LgZPihItcIXd4xV4JTvBFwiJ6326hO5MjV/BpgqGnG");
                  } else if (this.hasMapPath(
                     var5, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LNJMlIiLSnsf58YEWnIG54/ly13i1o5Ur4EC9vb7vhlPv9KAFb3WbhghYhblsLPQHFco5cBVJ7J2Rq+sHDwyJhfq")
                  )) {
                     var7 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LNJMlIiLSnsf58YEWnIG54/ly13i1o5Ur4EC9vb7vhlPv9KAFb3WbhghYhblsLPQHFco5cBVJ7J2Rq+sHDwyJhfq");
                  } else if (this.hasMapPath(
                     var5, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ddL/lp3n1H+FLyWbUI6K+PjQ3DOshWJ8F7GX9QCQM8yDta6IG4YSRSIArvp82mHMVYWdjoHUvpUJY++1CfSL0oI4wuY=")
                  )) {
                     var7 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ddL/lp3n1H+FLyWbUI6K+PjQ3DOshWJ8F7GX9QCQM8yDta6IG4YSRSIArvp82mHMVYWdjoHUvpUJY++1CfSL0oI4wuY=");
                  } else if (this.hasMapPath(
                     var5,
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                        "ZZuIbR0lzMIB/LDQor+a7lBOrJtlpgvs7Efr6BRak2nH0xsG/OaMAyVY4A86LUFdTlx2ih+BLWjmdxR/39YzApR8itdAV0gEN22Ky1JMnRQ="
                     )
                  )) {
                     var7 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                        "ZZuIbR0lzMIB/LDQor+a7lBOrJtlpgvs7Efr6BRak2nH0xsG/OaMAyVY4A86LUFdTlx2ih+BLWjmdxR/39YzApR8itdAV0gEN22Ky1JMnRQ="
                     );
                  } else {
                     var7 = var5.containsKey(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nZrCmUaxPCoJmHcSl1ZTpM5VHcQFSUq4a+UfqKAR8KHPSOuZjA76XA=="))
                        ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ddL/lp3n1H+FLyWbUI6K+PjQ3DOshWJ8F7GX9QCQM8yDta6IG4YSRSIArvp82mHMVYWdjoHUvpUJY++1CfSL0oI4wuY=")
                        : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZiCT1Cbhw0BkCBETRETw6GisNl3+G+LgZPihItcIXd4xV4JTvBFwiJ6326hO5MjV/BpgqGnG");
                  }

                  String var8 = this.readRawTargetOutlineColor(var5);
                  if (var8.equalsIgnoreCase(var6)) {
                     return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 18) != 0);
                  } else {
                     this.setMapPathValue(var5, var7, var6);
                     this.applyRoundedCornerUnicodeColor(var5, var6);
                     return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-984494323, 4) != 0);
                  }
               }
            }
         } else {
            return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 5) != 0);
         }
      }
   }

   @Override
   public boolean setTargetOutlineColorAuto(EditorSession var1, String var2) {
      if ((1161080512 * 1161080512 + 1161080512 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
            Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
            if (var4 == null) {
               return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1901360071, 2) != 0);
            } else {
               boolean var5 = this.clearRawTargetOutlineColor(var4);
               return var5 | this.applyRoundedCornerUnicodeColor(var4, this.readRawTargetFillColor(var4));
            }
         } else {
            return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 2) != 0);
         }
      }
   }

   @Override
   public double readTargetRotation(EditorSession var1, String var2) {
      if (((-645322734 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Map var3 = this.resolveRawTargetByTargetId(var1, var2);
         if (var3 == null) {
            return Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(3901964316575989127L, 47));
         } else if (this.hasMapPath(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("N9hWuxfafgycFZhRTf5tv+Fmz6rj6W0fWy/6yRy1v5qdFcaOH/RQXL6nFjk="))) {
            return this.readMapPathDouble(
               var3,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("N9hWuxfafgycFZhRTf5tv+Fmz6rj6W0fWy/6yRy1v5qdFcaOH/RQXL6nFjk="),
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(8579324992214922421L, 16))
            );
         } else {
            return this.hasMapPath(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("QjsgMwj6B9RrJgbvgc4YY8K6Z7Rz5+bTWheudMklIRkeQg92yr5bCw=="))
               ? this.readMapPathDouble(
                  var3,
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("QjsgMwj6B9RrJgbvgc4YY8K6Z7Rz5+bTWheudMklIRkeQg92yr5bCw=="),
                  Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699518298545L, 22))
               )
               : Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544889370L, 36));
         }
      }
   }

   @Override
   public int readTargetOpacity(EditorSession var1, String var2) {
      if ((2033489581 ^ 2033489581) != 0) {
         throw new IllegalStateException();
      } else {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
            Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
            return var4 == null
               ? Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830743554, 9)
               : Math.max(
                  Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 2),
                  Math.min(
                     Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(831141890, 12),
                     (int)Math.round(
                        this.readMapPathDouble(
                           var4,
                           Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vi7g6UwkyiEgaqiv1agk8wWQSzLI0FcJCZb5vu6RL4Xh+np6OGZtkO1W"),
                           Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-6051199692369179255L, 61))
                        )
                     )
                  )
               );
         } else {
            return Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1859892191, 10);
         }
      }
   }

   public String formatSidebarHexValue(String var1) {
      if ((1321506493 * 1321506493 - 1321506493 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var2 = this.normalizeHexColor(var1);
         return var2.isBlank()
            ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("I62rfw30pge96bThRU3YSmK6U1KxwpgJtXFIL2IdiovQ7w==")
            : Xqedii_1uq0mhw37w1gu(var2.toUpperCase(Locale.ROOT));
      }
   }

   public String formatSidebarHoverColorValue(String var1) {
      if ((159781585 * 159781585 - 159781585 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var2 = this.normalizeHoverColorSettingToken(var1);
         if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LKKcXIHpjgBU+nETsXnA8WA6AHKFNlJKiSkJACNDtoY3zlWO").equalsIgnoreCase(var2)) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("By5q5besaxSFT9aOarh+VnLDM10y2NZAT30OXaYpHiq4ZAKe");
         } else {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("uT3jAPh+lYslZqvREZ8+PjW97XbUEMEreDRB2DUqH4z9GoUy").equalsIgnoreCase(var2)
               ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("En4qasj/b28yZUu5oK1T64n790iRL8cdAXNtE7DV7HbtcsnW")
               : Xqedii_p2f6dfrvydt2(var2.toUpperCase(Locale.ROOT));
         }
      }
   }

   public String normalizeHoverColorSettingToken(String var1) {
      if (((750813599 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 29)];
         var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 28)] = var1;
         String var2 = this.firstNonBlank(var10001).trim();
         if (var2.isBlank()) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("uT3jAPh+lYslZqvREZ8+PjW97XbUEMEreDRB2DUqH4z9GoUy");
         } else if (var2.equalsIgnoreCase(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LKKcXIHpjgBU+nETsXnA8WA6AHKFNlJKiSkJACNDtoY3zlWO"))
            || var2.equalsIgnoreCase(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("By5q5besaxSFT9aOarh+VnLDM10y2NZAT30OXaYpHiq4ZAKe"))) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LKKcXIHpjgBU+nETsXnA8WA6AHKFNlJKiSkJACNDtoY3zlWO");
         } else if (!var2.equalsIgnoreCase(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("uT3jAPh+lYslZqvREZ8+PjW97XbUEMEreDRB2DUqH4z9GoUy"))
            && !var2.equalsIgnoreCase(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("En4qasj/b28yZUu5oK1T64n790iRL8cdAXNtE7DV7HbtcsnW"))) {
            String var3 = this.normalizeHexColor(var2);
            return var3.isBlank() ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("uT3jAPh+lYslZqvREZ8+PjW97XbUEMEreDRB2DUqH4z9GoUy") : var3;
         } else {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("uT3jAPh+lYslZqvREZ8+PjW97XbUEMEreDRB2DUqH4z9GoUy");
         }
      }
   }

   public void updateSidebarOpacitySliderVisual(Player var1, EditorSession var2, double var3) {
      if ((2106942157 * 2106942157 - 2106942157 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         ConfigurationSection var5 = this.findShellBlockSection(
            var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("mgdL+z1tLVj3Iwm6Cca4EyTXtErtPJy8d3ngpnURTOSHlY0erLmY5ZrQwa9fSaAyDHDWA+o1PIqkxZ4+0MFWXioiWX4=")
         );
         ConfigurationSection var6 = this.findShellBlockSection(
            var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("r90V0RFIMf5iidoh4OODRrWi2QSLYudTZptR4qXllAkYQe2BcMn07bnnMiPNrdMhBUBItpfOgO4=")
         );
         if (var5 != null && var6 != null) {
            EditorRect var7 = this.findShellBlockRect(
               var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MFx9DIpQ8cCi5msSNPlqHZGA+yOmnGcmvRMtzvDz6/EV8sdBhZnTYjF4WAE5Vp9FGDloydf0IPPnQbXkRC0/7C88")
            );
            EditorRect var8 = this.findShellBlockRect(
               var2, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("r90V0RFIMf5iidoh4OODRrWi2QSLYudTZptR4qXllAkYQe2BcMn07bnnMiPNrdMhBUBItpfOgO4=")
            );
            double var9 = this.readDouble(
               var5,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vKKayzv129fu+soeD6bfYdPwcE2kYsAjeOWIibaiwxa/r2AA1MZmuL+SyruKUhve"),
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dMemVQei62r6NkNu8BqxHTzcBg7yzM+XaIb3LIsc"),
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-5798952905804537922L, 16))
            );
            double var11 = this.readDouble(
               var5,
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6l2WF3VPN37KDzXw/G9b5TL+6GD2Z7ti90KnCY4iv1P1TeFGrB4AUOB3jzUJXHwM"),
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xm8RisRfJosJsXxzE9Kmi51eIt5xrdF3Z/Mg7m1j"),
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699518298545L, 9))
            );
            double var13 = Math.max(
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(3717311297780793371L, 57)),
               Math.abs(
                  this.readDouble(
                     var5,
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dD1V5iTP6Cs1T70nZx7JxW7+VGRP/INn/TyRub+RmwTrKSQrCShArllQuVCb8eP6"),
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("FPKgmoGqqfsf/TAZ7Kaoe8WTiOLLnI5xMvuM1lCli1sVmL5BN/Y="),
                     this.readDouble(
                        var5,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lErzeT8UmqIwoEIshJ7Fi16pNxEDG1a+KfX0vnDs6TaHlTzfg02UWVVtPRWM4P1OEu0="),
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("FPKgmoGqqfsf/TAZ7Kaoe8WTiOLLnI5xMvuM1lCli1sVmL5BN/Y="),
                        Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(8273497086728078182L, 62))
                     )
                  )
               )
            );
            double var15 = Math.max(
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(5575366932598296172L, 56)),
               Math.abs(
                  this.readDouble(
                     var5,
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GZNbFT1z5fLTz5m0iQOw7lZE3qkAFFk1rZ1X4zeaKsm9AlkyCXgpj7Bck6mfKr66AX4="),
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("CP4szv29CrqB0R19unEkQAr7B5MVCDB7QeO/jqmjBwdbqF4gfuQqjw=="),
                     this.readDouble(
                        var5,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZdWikxTMRP2zcCTxHiJM36vJxKMhxOMZKnTT9mzDzJ4GFejuYy6wyZRUTyGCY+QV/NvSjA=="),
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("CP4szv29CrqB0R19unEkQAr7B5MVCDB7QeO/jqmjBwdbqF4gfuQqjw=="),
                        Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285548034074L, 22))
                     )
                  )
               )
            );
            double var17 = var8 != null
               ? var8.y
               : this.readDouble(
                  var6,
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6l2WF3VPN37KDzXw/G9b5TL+6GD2Z7ti90KnCY4iv1P1TeFGrB4AUOB3jzUJXHwM"),
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xm8RisRfJosJsXxzE9Kmi51eIt5xrdF3Z/Mg7m1j"),
                  Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819564454559665178L, 43))
               );
            double var19 = Math.max(
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-1099052248649173547L, 21)),
               Math.abs(
                  this.readDouble(
                     var6,
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dD1V5iTP6Cs1T70nZx7JxW7+VGRP/INn/TyRub+RmwTrKSQrCShArllQuVCb8eP6"),
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("FPKgmoGqqfsf/TAZ7Kaoe8WTiOLLnI5xMvuM1lCli1sVmL5BN/Y="),
                     this.readDouble(
                        var6,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lErzeT8UmqIwoEIshJ7Fi16pNxEDG1a+KfX0vnDs6TaHlTzfg02UWVVtPRWM4P1OEu0="),
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("FPKgmoGqqfsf/TAZ7Kaoe8WTiOLLnI5xMvuM1lCli1sVmL5BN/Y="),
                        Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7783532488525925404L, 3))
                     )
                  )
               )
            );
            double var21 = Math.max(
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699530652081L, 27)),
               Math.abs(
                  this.readDouble(
                     var6,
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GZNbFT1z5fLTz5m0iQOw7lZE3qkAFFk1rZ1X4zeaKsm9AlkyCXgpj7Bck6mfKr66AX4="),
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("CP4szv29CrqB0R19unEkQAr7B5MVCDB7QeO/jqmjBwdbqF4gfuQqjw=="),
                     this.readDouble(
                        var6,
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZdWikxTMRP2zcCTxHiJM36vJxKMhxOMZKnTT9mzDzJ4GFejuYy6wyZRUTyGCY+QV/NvSjA=="),
                        Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("CP4szv29CrqB0R19unEkQAr7B5MVCDB7QeO/jqmjBwdbqF4gfuQqjw=="),
                        Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7820686085940104218L, 52))
                     )
                  )
               )
            );
            double var23 = this.clampSidebarOpacityRaw(var3);
            double var25 = this.opacityRawToPercent(var23) / Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-1818093047704428624L, 44));
            double var27 = Math.max(
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-3682358193982189562L, 55)),
               Math.min(var13, Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819584296301939738L, 45)))
            );
            double var29 = Math.max(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(6323895583952515031L, 15)), var27 * var25);
            double var31 = var9 + var27 * var25 - Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-2849825927511835601L, 35));
            EditorRect var33 = new EditorRect(var9, var11, var29, var15);
            EditorRect var34 = new EditorRect(var31, var17, var19, var21);
            if (var7 == null || !this.sameRect(var7, var33)) {
               this.moveShellElement(
                  var1,
                  var2,
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MFx9DIpQ8cCi5msSNPlqHZGA+yOmnGcmvRMtzvDz6/EV8sdBhZnTYjF4WAE5Vp9FGDloydf0IPPnQbXkRC0/7C88"),
                  var33.x,
                  var33.y,
                  var33.width,
                  var33.height
               );
            }

            if (var8 == null || !this.sameRect(var8, var34)) {
               this.moveShellElement(
                  var1,
                  var2,
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("r90V0RFIMf5iidoh4OODRrWi2QSLYudTZptR4qXllAkYQe2BcMn07bnnMiPNrdMhBUBItpfOgO4="),
                  var34.x,
                  var34.y,
                  var34.width,
                  var34.height
               );
            }
         }
      }
   }

   @Override
   public String toSidebarTypeLabel(String var1) {
      if ((1230496094 * 1230496094 + 1230496094 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isBlank()) {
         String var2 = var1.toLowerCase(Locale.ROOT);
         int var3 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-713980143, 7);
         switch (var2.hashCode()) {
            case -1217012392:
               if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ekGHv3Apflq5i4KuttMa5RzRKWdKp+P89TX0lFMatimR3CqLCunVGg=="))) {
                  var3 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-814656397, 26);
               }
               break;
            case 3242771:
               if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8y2oGcYV9iDNWyOGTUS+o03pzLZnRSDoG8yvUuICa0B8Id7y"))) {
                  var3 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(931518466, 25);
               }
               break;
            case 3556653:
               if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8"))) {
                  var3 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855234, 5);
               }
               break;
            case 1385468589:
               if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("5tvkzLh7R8onFdJjINuGrmqPy2s3O9naukhGW7gdy0ALye3w2R3J1ATz"))) {
                  var3 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1848282744, 31);
               }
               break;
            case 1818829051:
               if (var2.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("+TXd79UVRHRBxDoUpISxO7SlE33Wjf27Na+tcLsIE+qsJMDcV1QKxJfnyj0oQ8Ax+OLNfa4b"))) {
                  var3 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 25);
               }
         }
         return switch (var3) {
            case 0 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("CSRixo8MiHgeH46dQlmVVuhzXEyQWS6cqX11MRcf34bTmbcClH/CuXgzLDEKiLy6l8oZHVT1");
            case 1 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Sf+G0tz0xyAWe8Od0x39z8t0468CFB3IhP0ybxM3WrkVo8445wsji9l7");
            case 2 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("pndQIecNCB6MtLho7E8SEReGc+dIyWNovmYah+BOSeLVNpRmRktisg==");
            case 3 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("VLKGZuJOalYEVkku0pOBbcqp7ATKtqeJmYx3oFB8msta+SGe");
            case 4 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("bLAf0EXJBD3Msplpk0b1PYvSFDRbGMkDHlMZ9Ddpsjq3fu/F");
            default -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Qbs4slFFaW+JG1KOViNV83cdHy5faPWZgiCj1blNs0+ma+OqC8w=");
         };
      } else {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Qbs4slFFaW+JG1KOViNV83cdHy5faPWZgiCj1blNs0+ma+OqC8w=");
      }
   }

   public String formatPx(double var1) {
      if (((1942059473 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (!Double.isFinite(var1)) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("JAfaGIIHb78pBNaXh4fuFm0O16qgGsz22cwgdkHtYB87qw==");
      } else {
         double var3 = Math.rint(var1);
         if (Math.abs(var1 - var3) < Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(5129975440860432179L, 27))) {
            Locale var5 = Locale.ROOT;
            String var6 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("P4HJhW6sHsmx4cCNe/iFHuiA2QHMb8XDWfi0VID/LLPw1DNZ");
            Object[] var7 = new Object[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-276972706, 28)];
            var7[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 26)] = (int)var3;
            return String.format(var5, var6, var7);
         } else {
            Locale var10000 = Locale.ROOT;
            String var10001 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("hQ2KaRyn6CezIPu2wTpHGZljQirk0czGdY5gQPLnxiSR3EbPJ/dwVg==");
            Object[] var10002 = new Object[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(231035343, 28)];
            var10002[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 2)] = var1;
            return String.format(var10000, var10001, var10002);
         }
      }
   }

   public String formatLayerValue(double var1) {
      if ((85913500 * (85913500 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (!Double.isFinite(var1)) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("L6UzhEzoDhva1My7Wrjku+iIwFdufeAIg2JqL/aL");
      } else {
         double var3 = Math.rint(var1);
         if (Math.abs(var1 - var3) < Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(-6513967112168469249L, 16))) {
            Locale var5 = Locale.ROOT;
            String var6 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("rgoIiyOVJALx86DxoZgiJXBhWvdEGXLpoL1UdT3OEJs=");
            Object[] var7 = new Object[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 29)];
            var7[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 29)] = (int)var3;
            return String.format(var5, var6, var7);
         } else {
            Locale var10000 = Locale.ROOT;
            String var10001 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("5F7ixD673HeEVvcOF4PlATnaFqBMggjmGQ0NJ6Cxw1+TyDqS");
            Object[] var10002 = new Object[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681352278, 17)];
            var10002[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 5)] = var1;
            return String.format(var10000, var10001, var10002);
         }
      }
   }

   @Override
   public String readTargetName(EditorSession var1, String var2) {
      if (((1013535293 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
            String var4 = this.readTargetCustomName(var1, var3.targetPath);
            if (!var4.isBlank()) {
               return var4;
            }
         }

         for (EditorLayersLayerPart.LayerEntry var5 : this.collectLayerEntries(var1)) {
            if (var2.equals(var5.targetId) && var5.displayName != null && !var5.displayName.isBlank()) {
               return var5.displayName;
            }
         }

         if (var3 == null) {
            String[] var7 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 18)];
            var7[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 8)] = var2;
            return this.firstNonBlank(var7);
         } else {
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1008767273, 26)];
            var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(998941598, 29)] = var3.targetId;
            var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1901360067, 2)] = var2;
            var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855202, 5)] = this.toSidebarTypeLabel(var3.type);
            return this.firstNonBlank(var10001);
         }
      } else {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==");
      }
   }

   public String readTargetLogicalId(EditorSession var1, String var2) {
      if (((1831772228 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Map var3 = this.resolveRawTargetByTargetId(var1, var2);
         if (var3 != null) {
            String var4 = this.stringValue(this.readMapPathValue(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8L3tcEhVVFqLxabYTyk8caw0mwVPZNvm57Sm1TVaS/w=")));
            if (!var4.isBlank()) {
               return var4;
            }
         }

         HoverElement var5 = this.findFirstByTargetId(var1, var2);
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221204, 0)];
         var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1549911158, 13)] = var5 == null ? null : var5.targetId;
         var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1680959062, 18)] = var2;
         return this.firstNonBlank(var10001);
      }
   }

   public boolean isSidebarItemDesignTarget(EditorSession var1, String var2) {
      if (((1230750843 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String var3 = this.readTargetRawType(var1, var2);
         if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8y2oGcYV9iDNWyOGTUS+o03pzLZnRSDoG8yvUuICa0B8Id7y").equals(var3)) {
            return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 21) != 0);
         } else {
            Map var4 = this.resolveRawTargetByTargetId(var1, var2);
            String var5 = this.readTargetItemMaterialToken(var4);
            if (var5.isBlank()) {
               return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(2028874382, 23) != 0);
            } else {
               return ((this.resolveSidebarMaterialInput(var5) != null
                  ? Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 16)
                  : Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 6)) != 0);
            }
         }
      }
   }

   public boolean isSidebarTextDesignTarget(EditorSession var1, String var2) {
      if (((-985949955 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8").equals(this.readTargetRawType(var1, var2))) {
         return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-2031965977, 27) != 0);
      } else {
         Map var3 = this.resolveRawTargetByTargetId(var1, var2);
         if (var3 != null && !var3.isEmpty()) {
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221590, 6)];
            var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(356990071, 6)] = this.stringValue(
               this.readMapPathValue(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Hmt9HNa/Bsm8kGjqsqafiARSNEYaWIT7n0zG7tqPtX/uum5VQ3s="))
            );
            var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681253974, 15)] = this.stringValue(
               this.readMapPathValue(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("kQAP3oRPQh+1FE9TWzDKCrUYpdUrPUmUakROmyqM2vB+17s2o7W/qJA5ZmW+UXZa"))
            );
            var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2080241389, 22)] = this.stringValue(
               this.readMapPathValue(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MGZFR45mJj7tIy9ISf2R/rrGSbDmzVEODAYioIu+sAXpAddNsS+BxXydhEEuSC27juQ="))
            );
            var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1683318358, 21)] = this.stringValue(
               this.readMapPathValue(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("m8QjWipj/DjmxzCBbI8Nd/IobJkjn+UUyp6pi/+Q2IXCYLjtOrpdJ7/cJVb5K1xm1gpQcg=="))
            );
            var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1144350294, 27)] = this.stringValue(
               this.readMapPathValue(
                  var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MqD5FAS97Yte2ic0tXk4daaKm91nVd/tF0tKmRw3U9Nx2x4Y4Ofs+y6EnzfaJCe2IPOaLZ+x0nZK1l0ViK8=")
               )
            );
            var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1848282746, 31)] = this.stringValue(
               this.readMapPathValue(
                  var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("1zui1M23FqxwknJIEFd/H7VpFf/YfMkT8MnvgIk9uv/n3ipSP8AsE6WYcJFBpSK5XMqszot2fVwXA8WYbUwWJA==")
               )
            );
            String var4 = this.firstNonBlank(var10001);
            if (!var4.isBlank()) {
               return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 18) != 0);
            } else {
               var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1036341711, 28)];
               var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 21)] = this.stringValue(
                  this.readMapPathValue(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lZHM1S98rYZKtNpWYW9UqW8REBlgSkz2t5+t5Lb6lldMKRa8"))
               );
               var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-507991495, 25)] = this.stringValue(
                  this.readMapPathValue(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xWcpDG4PrUbIwE8MfEYzqIDAMW3M0LXhbXRrt4thZ4Afl9FmLbvhhvBz68uP0gSXq/E="))
               );
               String var5 = this.firstNonBlank(var10001);
               return ((!var5.isBlank() ? Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 27) : Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1549911158, 13)) != 0);
            }
         } else {
            return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 8) != 0);
         }
      }
   }

   @Override
   public boolean isTextDesignSidebarMode(EditorSession var1) {
      if (((1840761829 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         List var2 = this.getSidebarEditableTargetIds(var1);
         if (var2.isEmpty()) {
            return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 31) != 0);
         } else {
            for (String var4 : (Iterable<String>) (var2)) {

               if (this.isSidebarTextDesignTarget(var1, var4)) {
                  return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-310657546, 14) != 0);
               }
            }

            return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-152208515, 6) != 0);
         }
      }
   }

   public String normalizeHoverEffectId(String var1) {
      if ((571703286 * 571703286 - 571703286 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2076047085, 22)];
         var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1092767889, 3)] = var1;
         String var2 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
         if (!var2.isBlank()
            && !Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("By5q5besaxSFT9aOarh+VnLDM10y2NZAT30OXaYpHiq4ZAKe").equalsIgnoreCase(var2)
            && !Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("UK0FSbdOgpYgHrnaXzNoFEtW/NQxqUtPi0NaJqfq0rsppRpB").equals(var2)) {
            var2 = var2.replaceAll(
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lPWb8skGPgh5vmXlSBc07mwsQaxjPjm8JOxty+mXlKETIY9ernNXRTfEkOIV3QYhetlnPw=="),
               Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==")
            );
            return var2.isBlank() ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LKKcXIHpjgBU+nETsXnA8WA6AHKFNlJKiSkJACNDtoY3zlWO") : var2;
         } else {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LKKcXIHpjgBU+nETsXnA8WA6AHKFNlJKiSkJACNDtoY3zlWO");
         }
      }
   }

   public String nextSidebarHoverEffect(String var1, List<String> var2) {
      if ((192047286 * 192047286 - 192047286 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var2 != null && !var2.isEmpty()) {
         String var3 = this.normalizeHoverEffectId(var1);
         int var4 = var2.indexOf(var3);
         return var4 < 0
            ? (String)var2.get(Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 24))
            : (String)var2.get((var4 + Xqedii_2ze73vg.Xqedii_18u831nn38lo(1679124054, 21)) % var2.size());
      } else {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LKKcXIHpjgBU+nETsXnA8WA6AHKFNlJKiSkJACNDtoY3zlWO");
      }
   }

   public List<String> resolveHoverEffectOptionIds() {
      if ((826107107 ^ 826107107) != 0) {
         throw new IllegalStateException();
      } else {
         TreeSet var1 = new TreeSet();
         Map<?, ?> var2 = this.loadHoverEffectConfigurations();

         for (Map.Entry<?, ?> var4 : var2.entrySet()) {
            YamlConfiguration var5 = (YamlConfiguration)var4.getValue();
            String[] var10002 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-621282323, 15)];
            var10002[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 10)] = var5 == null
               ? null
               : var5.getString(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8L3tcEhVVFqLxabYTyk8caw0mwVPZNvm57Sm1TVaS/w="));
            var10002[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-2031965977, 27)] = (String)var4.getKey();
            String var6 = this.normalizeHoverEffectId(this.firstNonBlank(var10002));
            if (!var6.isBlank() && !Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LKKcXIHpjgBU+nETsXnA8WA6AHKFNlJKiSkJACNDtoY3zlWO").equals(var6)) {
               var1.add(var6);
            }
         }

         if (var1.isEmpty()) {
            var1.add(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("sCidJbYqpqxS46UGiXHbmgSjw8fK0m3q8EeScdz2ke/qZUrRi5s="));
         }

         ArrayList var7 = new ArrayList(var1.size() + Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1968988645, 5));
         var7.add(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LKKcXIHpjgBU+nETsXnA8WA6AHKFNlJKiSkJACNDtoY3zlWO"));
         var7.addAll(var1);
         return var7;
      }
   }

   public Map<String, YamlConfiguration> loadHoverEffectConfigurations() {
      if ((1093080649 * 1093080649 - 1093080649 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Map<?, ?> var1 = this.loadYamlFolder(
            Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dlOKj/+HoAGq0l7X1NAJpOCpxTIWfUZetxPdiDle8S5Tu6vy9BX+kvPkoBTHotm4qfVd24NUAWVyimUU"), new ArrayList()
         );
         Map<String, YamlConfiguration> var2 = new LinkedHashMap<>();

         for (Map.Entry<?, ?> var4 : var1.entrySet()) {
            YamlConfiguration var5 = (YamlConfiguration)var4.getValue();
            String[] var10002 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-34148997, 30)];
            var10002[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1640265053, 21)] = var5 == null
               ? null
               : var5.getString(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8L3tcEhVVFqLxabYTyk8caw0mwVPZNvm57Sm1TVaS/w="));
            var10002[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 23)] = (String)var4.getKey();
            String var6 = this.normalizeHoverEffectId(this.firstNonBlank(var10002));
            if (!var6.isBlank() && !Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LKKcXIHpjgBU+nETsXnA8WA6AHKFNlJKiSkJACNDtoY3zlWO").equals(var6)) {
               var2.putIfAbsent(var6, var5);
            }
         }

         return var2;
      }
   }

   public YamlConfiguration resolveHoverEffectConfiguration(String var1) {
      if ((1484023896 * 1484023896 + 1484023896 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var2 = this.normalizeHoverEffectId(var1);
         if (!var2.isBlank() && !Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LKKcXIHpjgBU+nETsXnA8WA6AHKFNlJKiSkJACNDtoY3zlWO").equals(var2)) {
            YamlConfiguration var3 = this.loadHoverEffectConfigurations().get(var2);
            if (var3 != null) {
               return var3;
            } else if (Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("sCidJbYqpqxS46UGiXHbmgSjw8fK0m3q8EeScdz2ke/qZUrRi5s=").equals(var2)) {
               YamlConfiguration var4 = new YamlConfiguration();
               var4.set(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8L3tcEhVVFqLxabYTyk8caw0mwVPZNvm57Sm1TVaS/w="),
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("sCidJbYqpqxS46UGiXHbmgSjw8fK0m3q8EeScdz2ke/qZUrRi5s=")
               );
               var4.set(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("e9gpcfIE+hfP84a4kSt5a9LuiQ3YEe3sMF+i+dqNmR0STtoV"),
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("InpLRdbc3KLAZn7cUXQJSek6Q6D8/KWdkOZ52vcP3wbYer5+MIE=")
               );
               var4.set(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("wnj7uF3hcOoAks+8+3wFksoVbSbHn47JPMeDyYMSgiPoAf2O"),
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("sCidJbYqpqxS46UGiXHbmgSjw8fK0m3q8EeScdz2ke/qZUrRi5s=")
               );
               var4.set(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("rVnyK1JOYmTG+wwEH0GVKk0E6HeOss4fLQzxIQrZhONTTjI4F/Fus3dl3ADXZPhgeRwJNyfNdig="),
                  Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285559981082L, 25))
               );
               var4.set(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ilcs14c4M4cew08BC+4CKGLOZsQN7t7bPgDJ+Ze9JGwWSTiLB5n9Cune9/igrJAnVVg="),
                  Xqedii_2ze73vg.Xqedii_18u831nn38lo(607479369, 29)
               );
               var4.set(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GjxEQP7aFovzfMABP81/xGkGNUq1E14Eah5M0q9sic7tO1qv8j5F/uL2cnymCpN4kItziItq"),
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ayxWcSEcssw876mJbt+i3fbFjAu6L2a8/++ToYZILia8dKrMoPzUk6CN9ehAR30PKM4=")
               );
               return var4;
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   @Override
   public EditorTimelineKeyframePart.HoverEffectRuntimeConfig resolveHoverEffectRuntimeConfig(String var1) {
      if ((-1311160027 * (-1311160027 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var2 = this.normalizeHoverEffectId(var1);
         if (!var2.isBlank() && !Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LKKcXIHpjgBU+nETsXnA8WA6AHKFNlJKiSkJACNDtoY3zlWO").equals(var2)) {
            YamlConfiguration var3 = this.resolveHoverEffectConfiguration(var2);
            if (var3 == null) {
               return null;
            } else {
               String[] var10002 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1461215518, 11)];
               var10002[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 16)] = var3.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("wnj7uF3hcOoAks+8+3wFksoVbSbHn47JPMeDyYMSgiPoAf2O")
               );
               var10002[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1680172630, 20)] = var2;
               String var4 = this.normalizeHoverEffectId(this.firstNonBlank(var10002));
               double var5 = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("sCidJbYqpqxS46UGiXHbmgSjw8fK0m3q8EeScdz2ke/qZUrRi5s=").equals(var4)
                  ? Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902003892687858097L, 45))
                  : Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(3026658227780022526L, 36));
               String[] var10003 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(356990263, 6)];
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 31)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "sCidJbYqpqxS46UGiXHbmgSjw8fK0m3q8EeScdz2ke/qZUrRi5s="
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 27)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "JnXhf0iRJcLrbegiW47CcP/+HALV/+F/b0pmY81lBe7CK9/mbpTMp1s7CWggsC4njR4ZGd4Q"
               );
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2109431551, 4)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "Ei6pvVWFxelFZWK15PyN8Uhmuvlw3Q5Eg/FLHBGpv5ckEdF0X4u9ayc0/0hzVJ1OSQGqJg=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855202, 4)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "UlxDvwQN02l4WtMjt3M/swdsIkSBkkcN2DOCCS9S1NFyU7gv/8+Jq7IJngEMQ+Rsqc1OfpAo"
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830806018, 14)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "uXrMRkovZRy9lJo4kwPNHjJuUDeU2Y6wQbja5YsmLbltiVed+/U9zT+w"
               );
               double var7 = this.readHoverEffectPercentNumeric(var3, var5, var10003);
               double var64 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699518301775L, 14));
               var10003 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-403720341, 25)];
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-575705154, 11)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "+oqNj/HzZOEt6mVlKY9zFSlWW26WkKCjncLccPKAutSYZrRmzkoa+qs+"
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1614112342, 26)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "7SoWqJ80//3utLGw78/XE8bGfpc97NFQFFLNjPN+0qyaSQI5VPwghA=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855298, 7)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "+K9ojUaEG4u03t2+SZcVCCVzSaxi0YiZFmv2XyyH51myAxdTq40fLWeHxYNyq+EIi0EOn60dMbZOBw=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681222230, 10)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "OY6uNjO/5TyV6cwu8bgKdsarzHvnIg0yh5mctP0faYzAykMmeZrgEhb25xu4aUWmxbN3P28R"
               );
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-680438669, 26)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "/SebMPUmY5sGB1TammQY6iQhdRAiBu8krm3b/h3LerDq4xDvKsSMfFabhjNcunzI+4vl4ZnEBFGw4A=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1495095065, 27)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "dKVxI+FtnNs92xz+ES4XDWjxI1lQEFPCQDfov3kVlvp/MUB/sA6wBwYZ"
               );
               double var9 = this.readHoverEffectPercentNumeric(var3, var64, var10003);
               double var65 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(861908095331167583L, 25));
               var10003 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1196803601, 1)];
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 29)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "iKd9I0AUPKlkVLhuxCuinyRb/DTFQQLupV5P5RU+IkCP2QZA/Xe1C0Wq"
               );
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1416872817, 10)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "jxlhNVfnKRWLHZ2J2Kf84cdEevMOb/XiMttANnzmP70cg3NNpJ+y+w=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221334, 6)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "Ptj8KO3zwNoKxgv5cM8st5l/qZl5vs1lzifgZI8Ja32xULOchkAATEf1nxgmM2iDq8nZCqVn1u869g=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(831903746, 19)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "bBdBzsnAt971Z1mDD5b3DFjg9TvZiw+9fiielizPU4t7bx350DWyMIQ3Gbp+KhLxnhb1ZO04"
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855938, 8)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "mmgIL/negTeri5n8v+grZntPElbw4xHGQ+qUAmDzA99V6njga2u+9dRwJ1Re3gSvDlrB5oS8BCqYuQ=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830838786, 12)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "DzjIW3pKrYdvR9nIp8cXZ5Nx848vupRMOmIyi5wMyMUswDqKBnclU4I2"
               );
               double var11 = this.readHoverEffectPercentNumeric(var3, var65, var10003);
               double var13 = Double.isFinite(var9) ? var9 : var7;
               double var15 = Double.isFinite(var11) ? var11 : var7;
               var13 = this.clampHoverEffectScalePercent(var13);
               var15 = this.clampHoverEffectScalePercent(var15);
               double var17 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(122504736642563156L, 45))
                  + var13 / Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902578157732620721L, 51));
               double var19 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-3316946513407633841L, 1))
                  + var15 / Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819560603819494426L, 41));
               if (!Double.isFinite(var17) || var17 <= Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(4157161651016964076L, 42))) {
                  var17 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(7933045355763865264L, 10));
               }

               if (!Double.isFinite(var19) || var19 <= Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(4619342920412962757L, 41))) {
                  var19 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544840202L, 16));
               }

               double var66 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-2838886807405595108L, 49));
               var10003 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1439043144, 9)];
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-310673930, 14)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "meuibYGtqdE8Yo1PJ0imE+7RiUAxY5jZZbzzVq4flRk2qObTDRGiOg=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1815438934, 27)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "tG9O5Ce9epTTg9BNqHV1a5G1HCQyHLqCxINFaZHWhir1Lzja+l8="
               );
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-818035375, 20)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "e8u+lWgvoPV2m1HnrsORKv797yl3Pwva+SwzJoxAne0QoNGx45NWLbcqH+8="
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1680696918, 19)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "76nwm+P2siYCdJzxmpp3VoUePSb8HUlChPxVzmXSyVTBLFg68RfdwpQA"
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855938, 8)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "b886wwAgSBJjnAy2qa7G0dP8u7+p1gvjYzQHuG34ADJpBi40AVTZWnwhFodLAkcp/NE="
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1278568022, 27)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "SLve9DsfPCApApIotMnfOanHo7+FiK42qolOEvYpaJFV1T2wW9Oz8R64FySSrTKl"
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(-466262441, 30)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "0RFN/OKrT595qCLPMmQE+yvskjnmoZXbyMqAqYDZ3DtifYjYpovVGg=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-598401802, 0)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "yg/qkjybvZN6x7yD4NyC/1DeJwhUTCdOgghoy5voJIghTZ957fMkXPeaY5A="
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1614112342, 23)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dMemVQei62r6NkNu8BqxHTzcBg7yzM+XaIb3LIsc");
               double var21 = this.readHoverEffectPercentNumeric(var3, var66, var10003);
               double var67 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(3920333816614324864L, 50));
               var10003 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681206870, 11)];
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 28)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "CcbyStQc0krD1t6K9HyLnxcVapaI5q1/ZwM581YhhlgaQ/V01t1jdw=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2109431535, 4)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "BC/cx1f1gSN9RBRRH+cGRgwWxvVCBesYSDHfSUELo1sSbAdsMiA="
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(293984258, 29)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "ao3B731MDUJ4DEzGSXdKMN11MuGX/SZj0soXG8Izb0uGWLdY8d8s2c5zwLk="
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(293984258, 28)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "6sfK2gmy9I0pZtd5vs/8dMaYhNelb9iiNVUokDr9j2AUy40J6xtS0WGz"
               );
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1427961308, 8)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "MfFNweYxIXnydP30jEgmyRp2t9qgPB3y1mv+LrsTRikaK0mE+kxtQK30XGcj59asoV8="
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1278568022, 27)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "jrJ32SfBtVhmLM4CdbhaS3P5gdrb7M5B/AnjMYnLYM5DrFRTaObspN03ZjD2Dz14"
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(1636161538, 28)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "HslgqOV08GO6q/rRrhkD9EyMVCLQNWj3kSmgXFGTYeTucSm1RyPSUA=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681200726, 12)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "lvjkbBnFLjXfhd0ohkLiiF9u0/AVdR85yCk0TtLMePRtKfhhr4L3p8aC7Pg="
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1679124054, 18)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xm8RisRfJosJsXxzE9Kmi51eIt5xrdF3Z/Mg7m1j");
               double var23 = this.readHoverEffectPercentNumeric(var3, var67, var10003);
               double var68 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-2116676869977442545L, 32));
               var10003 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-152208323, 6)];
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 28)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "evQWnnTyPcAn8YKNNvM8Ewe84pfFyaq1YEgCKl3wsh7NwENL"
               );
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-571492501, 25)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "Es406rwVq5e1mo7Dsgzp8joXAqPOOm5YAjE55JiEQbmIpY2RXJ3MszB4ySExdA=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221462, 7)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "HihI+RL+96IsiZIf2GgEBslcVDQlTiW7opme/oxU0B+WXr0m9WNFp4Qfy92yETcZ5mk="
               );
               double var25 = this.readHoverEffectPercentNumeric(var3, var68, var10003);
               double var69 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-2353197886126807409L, 7));
               var10003 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1217666577, 9)];
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 25)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "gdwVEDk0V5Oe8GsA+xiimE3lrDDsKj5fuGaWBjbR/VhryCu2C0k="
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1689609814, 23)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "YUmOSNdr4OrxVuLgNR96rPzqy/ZzazAtxCWjHdohev9q+uiNhZFEgZvt1QeuMmw3"
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1697998422, 23)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "+kqNrQoOBDsnBN5/4Vmk6O+zr+lx8wYOCXvVFsd/YIjotgpY/v6y0DDEH+xe7wvrH0VFLA=="
               );
               double var27 = this.readHoverEffectPercentNumeric(var3, var69, var10003);
               double var70 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(6848838262100475773L, 17));
               var10003 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221210, 2)];
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1809575863, 17)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "s59QXqvsoccYJPKx2KRGGlu3r1mHiUHswOiAOmRthaU="
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681155670, 16)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "MDJpl7KPr876GboP16L+yQYGY7DmIn6nEjOLlTBV9z+NhXUIWn7r4LeB"
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855168, 1)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "nCtN6+U1H+QiikjjDnPfoU/wUNK6JFc9X/Yawoa7kiGWmZi4swvxirAg8aLbkg=="
               );
               double var29 = this.readHoverEffectPercentNumeric(var3, var70, var10003);
               double var71 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699518298545L, 19));
               var10003 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(822466562, 22)];
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 20)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "YuOg6pvY9bqyYZmvD7YcdzPVnizVrUtI1POxt4sDtYEEYf0m"
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681155670, 16)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "pUj7jkQtU4sZZRt0IELp6RLqcQBKi+WVQdCQ3FMlf2broaiqebwVj3SuGw8KQg=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(971487402, 17)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "PGdqp0txPsq6e9nE2rt0ax0Fx4VOOweaOE3ttEJkZo8H0SzzA1w9fFIDp9+BFLi/KEA="
               );
               double var31 = this.readHoverEffectPercentNumeric(var3, var71, var10003);
               var21 += var27 - var25;
               var23 += var31 - var29;
               var10002 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(839243778, 23)];
               var10002[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 5)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "vi7g6UwkyiEgaqiv1agk8wWQSzLI0FcJCZb5vu6RL4Xh+np6OGZtkO1W"
               );
               var10002[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 20)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "/FPIwTROa/3U2jPI2Btof4B0u0J6bxdM15fmhj7rEwEgNz3ps64="
               );
               var10002[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681204822, 13)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "+KWQ7oCg2rVBtg0S9p02lT4AK8AV9ov/BKsy8FIoORjYq+EX05XvLv8NUwVcl7N5sOw7sxGb3kSeBa/b"
               );
               var10002[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830986242, 16)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "Mk6uuriLN8SCAcs6nhri/WdZNQC9fgEfFFwDPXbYH5rqCiAw9fJe/XoCXc0Kx8Z2q6q+fW14bBrDyw=="
               );
               var10002[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1217666065, 9)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "eoGlaZhRFrh7wYRK9ZG9qEqwo4xApIk75pHkCHhzBzi2dPsic/ZFriWcC+hAU4gOeGodBgO4E9E="
               );
               var10002[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-546383925, 2)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "wJ+jrO8fQwT/k7xMifEZcS/XngNAvkJ5X+tqARJZLaeSp0PZUOEmka4T9U65GuQ805IS+7BQ"
               );
               var10002[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1904480726, 15)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "oR+ewECIbhoSX+TEu3kV0VnuHvdKtxoQsupuFujzf1YM9vkEM79hr6Ip62rz5g1VX2m6UVQg9gyK6ON/"
               );
               var10002[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1195226901, 14)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "E/iaPwZorJe/BtV0hkSwnflCMPKhcWfIXJyoemlXd94yb4XeyFMi4MhZQUuhBs1R9+DBJq0znZU="
               );
               boolean var33 = this.hasHoverEffectValue(var3, var10002);
               double var73 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(3355047560739573088L, 8));
               var10003 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-621085715, 15)];
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-608834057, 8)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "vi7g6UwkyiEgaqiv1agk8wWQSzLI0FcJCZb5vu6RL4Xh+np6OGZtkO1W"
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 19)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "/FPIwTROa/3U2jPI2Btof4B0u0J6bxdM15fmhj7rEwEgNz3ps64="
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221202, 1)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "+KWQ7oCg2rVBtg0S9p02lT4AK8AV9ov/BKsy8FIoORjYq+EX05XvLv8NUwVcl7N5sOw7sxGb3kSeBa/b"
               );
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1638167901, 21)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "Mk6uuriLN8SCAcs6nhri/WdZNQC9fgEfFFwDPXbYH5rqCiAw9fJe/XoCXc0Kx8Z2q6q+fW14bBrDyw=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(25548802, 28)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "eoGlaZhRFrh7wYRK9ZG9qEqwo4xApIk75pHkCHhzBzi2dPsic/ZFriWcC+hAU4gOeGodBgO4E9E="
               );
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(356990263, 6)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "wJ+jrO8fQwT/k7xMifEZcS/XngNAvkJ5X+tqARJZLaeSp0PZUOEmka4T9U65GuQ805IS+7BQ"
               );
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1627682141, 21)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "oR+ewECIbhoSX+TEu3kV0VnuHvdKtxoQsupuFujzf1YM9vkEM79hr6Ip62rz5g1VX2m6UVQg9gyK6ON/"
               );
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1461209374, 11)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "E/iaPwZorJe/BtV0hkSwnflCMPKhcWfIXJyoemlXd94yb4XeyFMi4MhZQUuhBs1R9+DBJq0znZU="
               );
               double var34 = this.readHoverEffectNumeric(var3, var73, var10003);
               int var36 = this.clampHoverEffectOpacity(
                  var34 >= Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-588299471531701853L, 5))
                        && var34 <= Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(7382192939863681027L, 54))
                     ? (int)Math.round(var34 * Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-3212653956199074202L, 2)))
                     : (int)Math.round(var34)
               );
               double var74 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544889370L, 27));
               var10003 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830861314, 11)];
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 9)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "63Gp2j2BZqid9dTbHSkqx8kjOr72SnvOerGn4MSznRIlRd9Zxdmp3vC4dQnU8xPR1fUb5luT"
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1685415510, 22)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "68MQB2kPttTrnfVfkE7mV8y3M8elOfy8JKzKX5D8XnJ/k2wrcuu+qjGOiQZM8Dj0cb+v9A=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1092767873, 3)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "2H5q82eDKXSvvt2a8D64l85DA54UB9RhBdajjIQ5rLlF1Ad/ScIaypaP2KdbqmzaOBo="
               );
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-621249555, 15)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "xL4uledhxNcIcINwalNXBMIZfLLKrgCLkd3/ecYKBL1LhHvOQ0IQ9YzHOza8Dy4Q"
               );
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-136595983, 0)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "GKeLNeaVwMyRs6YgQQ6OcJMKVAcIP2tJWp/ZoqYfE0Y5gjhs2Vw36nK6X32d6Q2uf8VaCZJL"
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-1316628478, 29)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "z331o2yYKYqiZoUZHvTtvPnfhLpawbgfb1fZDFYsRb5NLMavl7bTDOwSr7gwt4jI+6w="
               );
               int var37 = (int)Math.round(this.readHoverEffectNumeric(var3, var74, var10003));
               var37 = Math.max(Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(846866430, 26), Math.min(Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-304384389, 7), var37));
               double var75 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699518298545L, 45));
               var10003 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-598401801, 0)];
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 10)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "SfRrba2Z0W41D62JQGlkAY3OJcBRwbRoQRYht3LXDzCD5gsySObX0SnLL6h+tuzbjREH4FoNGKA3Dw=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1010242887, 22)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "x4V2lHkfZbjXkgm3zQu8QOx5I5Gs2P+ImJ3Io7geDsWNYpsPulNP0sOoh2XY7eR/u6gCU17MfQ0="
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855298, 7)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "pQ8mSvJzj8ELoA810qWV/TzNUkNHzqWdQNfIMEuJDQIY03Mz7Mp00aX6j2/C5EC4sBWRD2Bg"
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1412785750, 28)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "HS4GBqAw+KIHHYXCzzlqtsnraXJGCPG3U6J0TKTHbTk6wsSPg8eJv/n2dilu5TWdICMBQA=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1685415510, 20)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "s2auSn/UJP60SvFzEmvhn40/wRJ8ElYy5Tzryk7Lfm+HTS0Am/znKLvhYUY6R0R5ijPvPmh0boujsg=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1101071498, 21)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "DQm3WsKeyJUrx6eQcYict7cKhtS2GHNg1XWzmG9J54/ertPH72NtCtENh+uGt2Pvp+5vMfhz"
               );
               double var38 = this.readHoverEffectPercentNumeric(var3, var75, var10003);
               if (!Double.isFinite(var38)) {
                  var38 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544889370L, 42));
               }

               double var76 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-3030712122439261193L, 13));
               var10003 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1901360095, 2)];
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 10)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "qkj7U0Zu5/q+mY1UHAuQZqJZcsV9WAsXW1PYg0ToU7pQVeADXNWklQdKREleWCGgSrIaDg=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681225302, 12)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "lk2TQPmfNXmqPRBj5CQXunKxgLx+dEZKpTO+bSSFY4Ib9P/8BNIq2k6gtbb5c25f8Hc="
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855682, 9)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "QjsgMwj6B9RrJgbvgc4YY8K6Z7Rz5+bTWheudMklIRkeQg92yr5bCw=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681196630, 13)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "N9hWuxfafgycFZhRTf5tv+Fmz6rj6W0fWy/6yRy1v5qdFcaOH/RQXL6nFjk="
               );
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(485874773, 16)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "WoUkcuJB2Umwo6qMdarcFhHMQ+Lngn/38YVPER0/w/96chayHC7vYeZKSDqCL6l4EJm0Zg=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221366, 5)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "/zlfrS+lsDZbPfIs0yZ7sONgkkTNGPgySINUjsre4Tb+4gUWcNN+2RB1AoAZ9vB0"
               );
               double var40 = this.readHoverEffectNumeric(var3, var76, var10003);
               if (!Double.isFinite(var40)) {
                  var40 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699518298545L, 16));
               }

               double var77 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819645639239453722L, 47));
               var10003 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221174, 5)];
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 17)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "ilcs14c4M4cew08BC+4CKGLOZsQN7t7bPgDJ+Ze9JGwWSTiLB5n9Cune9/igrJAnVVg="
               );
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-276972706, 28)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "CI78Qo1SMfIe0BA9s+V/MlJsixbXXGKHMXtG/jO80sO7wo+jS5Fm6q7NQIxj2pHJ"
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1949656662, 27)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "YJ0vBzFdxngUntBnwQbnLrH53VWlQ+CYeVDmlF9X9HMzwUtgG1VmoC2M9OY="
               );
               double var42 = this.readHoverEffectNumeric(var3, var77, var10003);
               if (!Double.isFinite(var42) || var42 < Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544889370L, 12))) {
                  var42 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-5677490228574032839L, 50));
               }

               int var44 = Math.max(
                  Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-410590552, 19),
                  (int)Math.round(var42 / Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5866144431196989873L, 57)))
               );
               var10002 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221207, 31)];
               var10002[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1997883196, 30)] = var3.getString(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GjxEQP7aFovzfMABP81/xGkGNUq1E14Eah5M0q9sic7tO1qv8j5F/uL2cnymCpN4kItziItq")
               );
               var10002[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 27)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "ayxWcSEcssw876mJbt+i3fbFjAu6L2a8/++ToYZILia8dKrMoPzUk6CN9ehAR30PKM4="
               );
               String var45 = this.normalizeTimelineInterpMode(this.firstNonBlank(var10002));
               double var79 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-1176598943063403705L, 6));
               var10003 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681286742, 16)];
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 27)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "QGnkF78YRQHSyP2LYRqVikKQWUguB0GInVNEo2k/UmiePt8yx8nIJ/+3"
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(-466262442, 31)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "rS6b36TrBUXhorsjQ0xBFVJIU+J+LtSHq3LUHM4afFZNptgNxe2Z7Q=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855174, 2)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "Irtid91wNqVTAn8g/kAplUjxz9awjDxNfL0x2LoQrKNY12SDwtKDDQhl"
               );
               double var46 = this.readHoverEffectPercentNumeric(var3, var79, var10003);
               double var80 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544889370L, 30));
               var10003 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1372532165, 12)];
               var10003[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1549911158, 13)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "XCjgPDJ3ZdsHDm+HRcM08wiF10RPvZCMwIJwIxtRIR4qcH7sZXSr1NO4"
               );
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1151406211, 12)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "FlvJfgJaJKPSTJenj2TZEwBsDKc8ZgA3r2iug4TbDmVRkl/Dn8dU1A=="
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830986242, 17)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "dNM2ffq6hL7zvan/F/jr17j/EelFRRDZu8WfKi+YnQY/6OPiqXdd66Cp"
               );
               double var48 = this.readHoverEffectPercentNumeric(var3, var80, var10003);
               double var81 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012693656758705L, 34));
               var10003 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-76104130, 5)];
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-152208515, 6)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "cQ7EJ0VqCcW6cTRerzO1KTc1l7H8mckzg+6Y+wseprJ2txHRfIgFWmzuhcGaII9lC9Z5/QM6"
               );
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1109460106, 21)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "t2IWi7FYr4BV1/sVd0AWZ34Ei4IszwM+2HQcqlK7GAgxHBM/efHoj6ZB5WBmb4PGmss="
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830851074, 12)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "rf51KSgmb+htbq8FkWnph8yK6AJKU8+fK2FVHIe0KDbBM5kqEbuJUKGjsIKvt0hTUP5lPrNK"
               );
               var10003[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(519796414, 29)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "O5PzllYD/sbRPmW2qtqnUJrWli/uATaLCDriGW3O26buYdYho0om7kbIaPmEHMgk7PExAXJq"
               );
               double var50 = this.readHoverEffectPercentNumeric(var3, var81, var10003);
               double var82 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-165089266392407444L, 3));
               var10003 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1549878390, 13)];
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 9)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "O209PmfkvjCAAWcyOjNEr/Cv+5viZbDxiuqjQ5RVMpUZLApa3/Cs2IfHKZmkJ3bzIBVYq7P2"
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681352278, 17)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "fBUmlZEfoclwK1TppGjJOEiEksKZedmq6Bz8vXs6bCSR9yw87WakLphtz/JUlTRWn1A="
               );
               var10003[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(831379458, 19)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "ehlXfcdXv08Xlqb7ED71i3UZIjpyX/tn08UDgUY29+zI4869d/XANvxxo8tbpqDCIiHRTylW"
               );
               var10003[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1706387030, 23)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "Zx5QrhKiqC2VJEHu+eSN6CitNv3EOe1dcvm9z2U6spCg3g2Lq3NEeynmp4PCtJV0ebkYgdWo"
               );
               double var52 = this.readHoverEffectPercentNumeric(var3, var82, var10003);
               double var54 = Double.isFinite(var50)
                  ? Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5771267572836198833L, 59))
                     + var50 / Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(3026658210657825022L, 36))
                  : Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561284739320858L, 30));
               double var56 = Double.isFinite(var52)
                  ? Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-6061424244878518293L, 14))
                     + var52 / Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-6541523846604908985L, 5))
                  : Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699430087089L, 29));
               if (!Double.isFinite(var54) || var54 <= Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-6340111162589461512L, 38))) {
                  var54 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-2116676870672648433L, 32));
               }

               if (!Double.isFinite(var56) || var56 <= Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699518298545L, 56))) {
                  var56 = Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-4351863346586459778L, 38));
               }

               boolean var58 = var3.getBoolean(
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("cdsVEYhCTVpfH/Vu8Uod3Mxi03865P4j83EkTiYVDKUh2MpKqaTQMtGDFYOVjPjyYc/XiyQvX4s="),
                  (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 23) != 0)
               );
               return new EditorTimelineKeyframePart.HoverEffectRuntimeConfig(
                  var17, var19, var21, var23, var33, var36, var37, var38, var40, var44, var45, var46, var48, var54, var56, var58
               );
            }
         } else {
            return null;
         }
      }
   }

   public EditorTimelineKeyframePart.HoverEffectRuntimeConfig buildStartPositionConfig(EditorTimelineKeyframePart.HoverEffectRuntimeConfig var1) {
      if ((-274868485 * -274868485 - -274868485 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return var1 == null
            ? null
            : new EditorTimelineKeyframePart.HoverEffectRuntimeConfig(
               var1.startScaleXMultiplier(),
               var1.startScaleYMultiplier(),
               var1.startOffsetX(),
               var1.startOffsetY(),
               var1.opacityAbsolute(),
               var1.opacityAbsoluteValue(),
               var1.opacityDelta(),
               var1.opacityPercentDelta(),
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(6848838262100475773L, 17)),
               Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221142, 6),
               var1.interpolationMode(),
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-2353197886126807409L, 7)),
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-264584608747180319L, 29)),
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-5677492414712386503L, 50)),
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5904174339378506161L, 53)),
               (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-948874125, 26) != 0)
            );
      }
   }

   public EditorTimelineKeyframePart.HoverEffectRuntimeConfig buildReversedCloseConfig(EditorTimelineKeyframePart.HoverEffectRuntimeConfig var1) {
      if ((-150848944 * (-150848944 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return var1 == null
            ? null
            : new EditorTimelineKeyframePart.HoverEffectRuntimeConfig(
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-8466707484204737473L, 34)) - var1.scaleXMultiplier(),
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(8948608974692482549L, 19)) - var1.scaleYMultiplier(),
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
               (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 30) != 0)
            );
      }
   }

   public double readHoverEffectNumeric(YamlConfiguration var1, double var2, String... var4) {
      if ((472885693 * (472885693 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var4 != null && var4.length != 0) {
         String[] var5 = var4;
         int var6 = var4.length;

         for (int var7 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 31); var7 < var6; var7++) {
            String var8 = var5[var7];
            if (var8 != null && !var8.isBlank()) {
               Object var9 = var1.get(var8);
               if (var9 != null) {
                  double var10 = this.parseDouble(this.stringValue(var9), Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(2796695063600400182L, 55)));
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

   public double readHoverEffectPercentNumeric(YamlConfiguration var1, double var2, String... var4) {
      if ((34762981 * 34762981 + 34762981 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var4 != null && var4.length != 0) {
         String[] var5 = var4;
         int var6 = var4.length;

         for (int var7 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-545408162, 28); var7 < var6; var7++) {
            String var8 = var5[var7];
            if (var8 != null && !var8.isBlank()) {
               Object var9 = var1.get(var8);
               if (var9 != null) {
                  double var10 = this.parseHoverEffectPercentValue(var9, Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-330178532784814887L, 4)));
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

   public double parseHoverEffectPercentValue(Object var1, double var2) {
      if (((-1517580776 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return var2;
      } else if (var1 instanceof Number var7) {
         return var7.doubleValue();
      } else {
         String var4 = this.stringValue(var1).trim();
         if (var4.isBlank()) {
            return var2;
         } else {
            if (var4.endsWith(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("O1EWNrm0Ht10ZXBde+b0it76MEXqMeCqmpnY03Vr"))) {
               var4 = var4.substring(Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1992146683, 13), var4.length() - Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(971094186, 17))
                  .trim();
            }

            if (var4.isBlank()) {
               return var2;
            } else {
               double var5 = this.parseDouble(var4, Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-1288094089279760742L, 17)));
               return Double.isFinite(var5) ? var5 : var2;
            }
         }
      }
   }

   public boolean hasHoverEffectValue(YamlConfiguration var1, String... var2) {
      if (((-1429387075 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var2.length != 0) {
         String[] var3 = var2;
         int var4 = var2.length;

         for (int var5 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-621347859, 15); var5 < var4; var5++) {
            String var6 = var3[var5];
            if (var6 != null && !var6.isBlank() && (var1.contains(var6) || var1.get(var6) != null)) {
               return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681204822, 14) != 0);
            }
         }

         return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2071852781, 22) != 0);
      } else {
         return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 19) != 0);
      }
   }

   public double clampHoverEffectScalePercent(double var1) {
      if (((-428521506 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return !Double.isFinite(var1)
            ? Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(1960166908307162432L, 49))
            : Math.max(
               Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_ma8as72z1ida(-2849825910302737361L, 35)),
               Math.min(Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(-7256245193904881447L, 60)), var1)
            );
      }
   }

   public int clampHoverEffectOpacity(int var1) {
      if ((323006259 * 323006259 + 323006259 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return Math.max(Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 10), Math.min(Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1225984037, 16), var1));
      }
   }

   public TextAlignment readTargetTextAlignment(EditorSession var1, String var2) {
      if ((189458465 * 189458465 - 189458465 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Map var3 = this.resolveRawTargetByTargetId(var1, var2);
         if (var3 == null) {
            return TextAlignment.CENTER;
         } else {
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221205, 31)];
            var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 14)] = this.stringValue(
               this.readMapPathValue(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Hmt9HNa/Bsm8kGjqsqafiARSNEYaWIT7n0zG7tqPtX/uum5VQ3s="))
            );
            var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2079185655, 31)] = this.stringValue(
               this.readMapPathValue(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("kQAP3oRPQh+1FE9TWzDKCrUYpdUrPUmUakROmyqM2vB+17s2o7W/qJA5ZmW+UXZa"))
            );
            var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1901360079, 2)] = this.stringValue(
               this.readMapPathValue(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MGZFR45mJj7tIy9ISf2R/rrGSbDmzVEODAYioIu+sAXpAddNsS+BxXydhEEuSC27juQ="))
            );
            var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-1316628478, 30)] = this.stringValue(
               this.readMapPathValue(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("m8QjWipj/DjmxzCBbI8Nd/IobJkjn+UUyp6pi/+Q2IXCYLjtOrpdJ7/cJVb5K1xm1gpQcg=="))
            );
            var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-136595983, 0)] = this.stringValue(
               this.readMapPathValue(
                  var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MqD5FAS97Yte2ic0tXk4daaKm91nVd/tF0tKmRw3U9Nx2x4Y4Ofs+y6EnzfaJCe2IPOaLZ+x0nZK1l0ViK8=")
               )
            );
            var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681220438, 8)] = this.stringValue(
               this.readMapPathValue(
                  var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("1zui1M23FqxwknJIEFd/H7VpFf/YfMkT8MnvgIk9uv/n3ipSP8AsE6WYcJFBpSK5XMqszot2fVwXA8WYbUwWJA==")
               )
            );
            String var4 = this.firstNonBlank(var10001);
            if (var4.isBlank()) {
               return TextAlignment.CENTER;
            } else {
               String var5 = var4.toLowerCase(Locale.ROOT);
               int var6 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-822466563, 23);
               switch (var5.hashCode()) {
                  case 100571:
                     if (var5.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("XyAcrKeeO3zDUrsJ0Mq5Y4y7rITb9BHzuZ4Y/fCqcxtoDA=="))) {
                        var6 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681196630, 13);
                     }
                     break;
                  case 3317767:
                     if (var5.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("evQWnnTyPcAn8YKNNvM8Ewe84pfFyaq1YEgCKl3wsh7NwENL"))) {
                        var6 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 13);
                     }
                     break;
                  case 108511772:
                     if (var5.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("gdwVEDk0V5Oe8GsA+xiimE3lrDDsKj5fuGaWBjbR/VhryCu2C0k="))) {
                        var6 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-203664100, 24);
                     }
                     break;
                  case 109757538:
                     if (var5.equals(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("BcEk6iT0kMXkuECXmmSUtTQ/fvRXr6LY4bW6BETO/qopWrQ+H14="))) {
                        var6 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-546383941, 2);
                     }
               }
               return switch (var6) {
                  case 0, 1 -> TextAlignment.LEFT;
                  case 2, 3 -> TextAlignment.RIGHT;
                  default -> TextAlignment.CENTER;
               };
            }
         }
      }
   }

   public int readTargetTextWrapLineWidth(EditorSession var1, String var2) {
      if ((-218133443 ^ -218133443) != 0) {
         throw new IllegalStateException();
      } else {
         Map var3 = this.resolveRawTargetByTargetId(var1, var2);
         if (var3 == null) {
            return Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2109434719, 4);
         } else {
            Object var4 = this.readMapPathValue(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("WGRndTgqTXz8atn3Kjjl8rc0maesgf9yW+Ns9t9o5eJ8SHdnT3Sk4OloPKsQPQ=="));
            if (var4 == null) {
               var4 = this.readMapPathValue(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("3cQeD9RY3w7/cjlKz8qlo8Jimtcfw/Kmv1Hb/1L8EOERt8QPGYOB6HrrSSo="));
            }

            if (var4 == null) {
               var4 = this.readMapPathValue(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("fcmGh/OpqV8K6+vVcNxijuz56s+HVA4M2TwV3a9ql1hV2Tdx8/MjsKkNSyMqGg=="));
            }

            if (var4 == null) {
               var4 = this.readMapPathValue(
                  var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Veby0BVg8D5eb0XCKk8XH158rw6/WSU2SKQWPXhiHAqMvYgObMxx+CO8n3WGk4EMES9oSIBGjPNdZcM5")
               );
            }

            return this.normalizeTextWrapLineWidth(var4);
         }
      }
   }

   public String resolveTargetTextAlignmentWritePath(Map<String, Object> var1) {
      if ((615554319 * 615554319 - 615554319 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null || var1.isEmpty()) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Hmt9HNa/Bsm8kGjqsqafiARSNEYaWIT7n0zG7tqPtX/uum5VQ3s=");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Hmt9HNa/Bsm8kGjqsqafiARSNEYaWIT7n0zG7tqPtX/uum5VQ3s="))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Hmt9HNa/Bsm8kGjqsqafiARSNEYaWIT7n0zG7tqPtX/uum5VQ3s=");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("kQAP3oRPQh+1FE9TWzDKCrUYpdUrPUmUakROmyqM2vB+17s2o7W/qJA5ZmW+UXZa"))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("kQAP3oRPQh+1FE9TWzDKCrUYpdUrPUmUakROmyqM2vB+17s2o7W/qJA5ZmW+UXZa");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MGZFR45mJj7tIy9ISf2R/rrGSbDmzVEODAYioIu+sAXpAddNsS+BxXydhEEuSC27juQ="))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MGZFR45mJj7tIy9ISf2R/rrGSbDmzVEODAYioIu+sAXpAddNsS+BxXydhEEuSC27juQ=");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("m8QjWipj/DjmxzCBbI8Nd/IobJkjn+UUyp6pi/+Q2IXCYLjtOrpdJ7/cJVb5K1xm1gpQcg=="))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("m8QjWipj/DjmxzCBbI8Nd/IobJkjn+UUyp6pi/+Q2IXCYLjtOrpdJ7/cJVb5K1xm1gpQcg==");
      } else if (this.hasMapPath(
         var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MqD5FAS97Yte2ic0tXk4daaKm91nVd/tF0tKmRw3U9Nx2x4Y4Ofs+y6EnzfaJCe2IPOaLZ+x0nZK1l0ViK8=")
      )) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MqD5FAS97Yte2ic0tXk4daaKm91nVd/tF0tKmRw3U9Nx2x4Y4Ofs+y6EnzfaJCe2IPOaLZ+x0nZK1l0ViK8=");
      } else if (this.hasMapPath(
         var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("1zui1M23FqxwknJIEFd/H7VpFf/YfMkT8MnvgIk9uv/n3ipSP8AsE6WYcJFBpSK5XMqszot2fVwXA8WYbUwWJA==")
      )) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("1zui1M23FqxwknJIEFd/H7VpFf/YfMkT8MnvgIk9uv/n3ipSP8AsE6WYcJFBpSK5XMqszot2fVwXA8WYbUwWJA==");
      } else {
         return var1.containsKey(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nZrCmUaxPCoJmHcSl1ZTpM5VHcQFSUq4a+UfqKAR8KHPSOuZjA76XA=="))
            ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("m8QjWipj/DjmxzCBbI8Nd/IobJkjn+UUyp6pi/+Q2IXCYLjtOrpdJ7/cJVb5K1xm1gpQcg==")
            : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Hmt9HNa/Bsm8kGjqsqafiARSNEYaWIT7n0zG7tqPtX/uum5VQ3s=");
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public boolean setTargetTextAlignment(EditorSession var1, String var2, TextAlignment var3) {
      if ((-1655556974 * (-1655556974 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (!this.isSidebarTextDesignTarget(var1, var2)) {
         return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 7) != 0);
      } else {
         Map var4 = this.resolveRawTargetByTargetId(var1, var2);
         if (var4 == null) {
            return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 9) != 0);
         } else {
            TextAlignment var5 = var3 == null ? TextAlignment.LEFT : var3;
            TextAlignment var6 = this.readTargetTextAlignment(var1, var2);
            if (var6 == var5) {
               return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 17) != 0);
            } else {
               String var7 = this.resolveTargetTextAlignmentWritePath(var4);

               String var8 = switch (Xqedii_3ana5qf.Xqedii_26vvd4l[var5.ordinal()]) {
                  case 1 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("evQWnnTyPcAn8YKNNvM8Ewe84pfFyaq1YEgCKl3wsh7NwENL");
                  case 2 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("gdwVEDk0V5Oe8GsA+xiimE3lrDDsKj5fuGaWBjbR/VhryCu2C0k=");
                  default -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("50qgjH+lXJCGpnbpHBqcd97iWBurPZKk6bg3ri2Rwd9QAxHdcCK/gw==");
               };
               this.setMapPathValue(var4, var7, var8);
               return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1679124054, 21) != 0);
            }
         }
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public String formatSidebarTextAlignment(TextAlignment var1) {
      if (((-2139299221 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         TextAlignment var2 = var1 == null ? TextAlignment.LEFT : var1;

         return switch (Xqedii_3ana5qf.Xqedii_26vvd4l[var2.ordinal()]) {
            case 1 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZqtkiBePmwLudsxjZDX8e+gm3k1L+ekYJOd4OtTlpTw1FvmN");
            case 2 -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("z3LwrXcKKh2moXgXZaRoMUGOM0YLSwP3sVayFRcUH9nE4KspX58=");
            default -> Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nfxgVBx1jr0k35D8dfhLTNfWimV3pF2/bOl4/E9RsY9vZvLZS4zvJA==");
         };
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public void toggleSidebarTextAlignment(Player var1, EditorSession var2) {
      if ((2087351928 * 2087351928 + 2087351928 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         List var3 = this.getSidebarEditableTargetIds(var2);
         if (var3.isEmpty()) {
            this.updateEditorPropertiesSidebar(var1, var2);
         } else {
            TextAlignment var4 = null;
            int var5 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 20);
            int var6 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-304417029, 7);

            for (String var8 : (Iterable<String>) (var3)) {

               if (this.isSidebarTextDesignTarget(var2, var8)) {
                  var6 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-410590552, 19);
                  TextAlignment var9 = this.readTargetTextAlignment(var2, var8);
                  if (var4 == null) {
                     var4 = var9;
                  } else if (var4 != var9) {
                     var5 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 9);
                  }
               }
            }

            if (var6 == 0) {
               this.updateEditorPropertiesSidebar(var1, var2);
            } else {
               TextAlignment var16;
               if (var5 == 0 && var4 != null) {
                  var16 = switch (Xqedii_3ana5qf.Xqedii_26vvd4l[var4.ordinal()]) {
                     case 1 -> TextAlignment.CENTER;
                     case 2 -> TextAlignment.LEFT;
                     case 3 -> TextAlignment.RIGHT;
                     default -> throw new MatchException(null, null);
                  };
               } else {
                  var16 = TextAlignment.LEFT;
               }

               int var17 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 2);
               int var18 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1640265053, 21);

               for (String var11 : (Iterable<String>) (var3)) {

                  if (this.isSidebarTextDesignTarget(var2, var11)) {
                     boolean var12 = this.setTargetTextAlignment(var2, var11, var16);
                     if (var12 && var18 == 0) {
                        this.recordMutationForInteraction(var2);
                        var18 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1992154875, 13);
                     }

                     if (var12) var17 = 1;
                  }
               }

               if (var17 == 0) {
                  this.updateEditorPropertiesSidebar(var1, var2);
               } else {
                  this.applySidebarTextAlignNoTransition(var1, var2, var3);

                  try {
                     this.rerenderEditableSelection(var1, var2);
                  } finally {
                     this.restoreTextAlignTransitionTicks(var1, var2, var3);
                  }
               }
            }
         }
      }
   }

   public void applySidebarTextAlignNoTransition(Player var1, EditorSession var2, List<String> var3) {
      if ((5928313 * (5928313 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isEmpty()) {
         for (String var6 : this.collectSidebarTextTargetIds(var2, var3)) {
            this.applyHudNoTransitionForSidebar(var1, var6);
         }
      }
   }

   public void restoreTextAlignTransitionTicks(Player var1, EditorSession var2, List<String> var3) {
      if ((924490038 * 924490038 - 924490038 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && var3 != null && !var3.isEmpty()) {
         int var4 = Math.max(Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1195128597, 14), this.resolveCursorInterpDurationTicks());
         int var5 = Math.max(Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1217667601, 9), this.resolveCursorTeleportDurationTicks());

         for (String var8 : this.collectSidebarTextTargetIds(var2, var3)) {
            this.applyHudTransitionTicksForSidebar(var1, var8, var4, var5);
         }
      }
   }

   public Set<String> collectSidebarTextTargetIds(EditorSession var1, List<String> var2) {
      if ((-1705963063 * (-1705963063 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         LinkedHashSet var3 = new LinkedHashSet();
         if (var1 != null && var2 != null && !var2.isEmpty()) {
            for (String var5 : var2) {
               if (this.isSidebarTextDesignTarget(var1, var5)) {
                  for (HoverElement var7 : this.findTargetElements(var1, var5)) {
                     String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1642362205, 21)];
                     var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 4)] = var7 == null
                        ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ==")
                        : var7.id;
                     String var8 = this.firstNonBlank(var10001);
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
   }

   public void applyHudNoTransitionForSidebar(Player var1, String var2) {
      if (((1132234110 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         this.setHudNoTransitionIfExists(var1, var2);
         this.setHudNoTransitionIfExists(var1, Xqedii_37nzqy5chby1i(var2));
         this.setHudNoTransitionIfExists(var1, Xqedii_2xnmlrjy640n5(var2));
         this.setHudNoTransitionIfExists(var1, Xqedii_2nmp5zf8ge91z(var2));
         this.setHudNoTransitionIfExists(var1, Xqedii_3dp4h48p3aqet(var2));
         this.setHudNoTransitionIfExists(var1, Xqedii_1z22ep7dgwlfj(var2));
         this.setHudNoTransitionIfExists(var1, Xqedii_mg07sci3mpdm(var2));
         this.setHudNoTransitionIfExists(var1, Xqedii_11v4uezsfzju8(var2));
         this.setHudNoTransitionIfExists(var1, Xqedii_dm7c0bn8j520(var2));
         this.setHudNoTransitionIfExists(var1, Xqedii_77n2nc3dg5wb(var2));
         this.setHudNoTransitionIfExists(var1, Xqedii_7883ag7pm2a(var2));
         String var3 = Xqedii_t8pu4biq1qgd(var2);
         this.setHudNoTransitionIfExists(var1, Xqedii_3bc1ijohhe3tr(var3));
         this.setHudNoTransitionIfExists(var1, Xqedii_eectgynm596w(var3));
         this.setHudNoTransitionIfExists(var1, Xqedii_449odd3qmfeg(var3));
         this.setHudNoTransitionIfExists(var1, Xqedii_2wi55awp11qut(var3));
         this.setHudNoTransitionIfExists(var1, Xqedii_1zc5f4xc5oc4z(var3));
         this.setHudNoTransitionIfExists(var1, Xqedii_s15mmjgiawwp(var3));
         this.setHudNoTransitionIfExists(var1, Xqedii_156adwyruqsds(var3));
         this.setHudNoTransitionIfExists(var1, Xqedii_jgv7q4e2al5z(var3));
         this.setHudNoTransitionIfExists(var1, Xqedii_cmestleknbyq(var3));
      }
   }

   public void applyHudTransitionTicksForSidebar(Player var1, String var2, int var3, int var4) {
      if (((1002826771 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null && !var2.isBlank()) {
         this.setHudTransitionTicksIfExists(var1, var2, var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_qmcjl8gqouqa(var2), var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_jbd5hp30vbo3(var2), var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_of9q4yonrnpr(var2), var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_34wjuc6jfgo53(var2), var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_175nuh0hl99wo(var2), var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_1s487is45mrgc(var2), var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_2xvw4tz3d3pam(var2), var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_25fwvo59hblt9(var2), var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_gbvuznsbj2s(var2), var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_2ef9s3h0t07rx(var2), var3, var4);
         String var5 = Xqedii_4nmlouglwx1l(var2);
         this.setHudTransitionTicksIfExists(var1, Xqedii_1e1muuc5u1fix(var5), var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_3hvgzin7rjv48(var5), var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_1t8qu557rtlsw(var5), var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_36qf4c1ue71sd(var5), var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_y4qgzgf3lwur(var5), var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_3aekza0b9w9or(var5), var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_3r81r0081xrhk(var5), var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_1qn4hu273whrx(var5), var3, var4);
         this.setHudTransitionTicksIfExists(var1, Xqedii_pzrk3gzqzqw4(var5), var3, var4);
      }
   }

   public void setHudNoTransitionIfExists(Player var1, String var2) {
      if (((1263366572 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_3cny9rh.Xqedii_1tc6luf(this.hudService, var1, var2);
      }
   }

   public void setHudTransitionTicksIfExists(Player var1, String var2, int var3, int var4) {
      if (((-735290288 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_3cny9rh.Xqedii_21i7jf4(this.hudService, var1, var2, var3, var4);
      }
   }

   public String resolveTargetItemMaterialWritePath(Map<String, Object> var1) {
      if ((-1101277293 * (-1101277293 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8y2oGcYV9iDNWyOGTUS+o03pzLZnRSDoG8yvUuICa0B8Id7y");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("m20dwIWjyf0aDtr4zwdfCm0mupLuImzkF/CHhq0sUWxhFRANXu1F9BG61n0E1vByBLFD7aRF"))
         || this.readMapPathValue(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8y2oGcYV9iDNWyOGTUS+o03pzLZnRSDoG8yvUuICa0B8Id7y")) instanceof Map) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("m20dwIWjyf0aDtr4zwdfCm0mupLuImzkF/CHhq0sUWxhFRANXu1F9BG61n0E1vByBLFD7aRF");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8y2oGcYV9iDNWyOGTUS+o03pzLZnRSDoG8yvUuICa0B8Id7y"))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8y2oGcYV9iDNWyOGTUS+o03pzLZnRSDoG8yvUuICa0B8Id7y");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Vpy0z1b20Ppa2SxhvPnE5i7oHIHHkX44YX7RRkf6a+RTaYLsyZG72dmyssM="))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Vpy0z1b20Ppa2SxhvPnE5i7oHIHHkX44YX7RRkf6a+RTaYLsyZG72dmyssM=");
      } else if (this.hasMapPath(
            var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("3c30+jCGGXJcJMBz3blK7qCbbQVRQuGZx9oU2urGZ62Fw2XimiV6kcedIbVH9WUY2SpB2Mv1mSmu1a49oGh4fy4B3qM=")
         )
         || this.readMapPathValue(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("s7tNCiCq8nDcX1cnRt1hMnUFsO+CNrXGgacY+E8178BPUAr3HHJ9oXCGn2R0VYgm0xI=")) instanceof Map
         )
       {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("3c30+jCGGXJcJMBz3blK7qCbbQVRQuGZx9oU2urGZ62Fw2XimiV6kcedIbVH9WUY2SpB2Mv1mSmu1a49oGh4fy4B3qM=");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("s7tNCiCq8nDcX1cnRt1hMnUFsO+CNrXGgacY+E8178BPUAr3HHJ9oXCGn2R0VYgm0xI="))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("s7tNCiCq8nDcX1cnRt1hMnUFsO+CNrXGgacY+E8178BPUAr3HHJ9oXCGn2R0VYgm0xI=");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nzOViiG66pH5ju9GzQDVv+mL6F41jU1DPV25wSl6y5/+/pf/QfynY/ZoGOlS/tGpwdQekvd3zBd5zw=="))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nzOViiG66pH5ju9GzQDVv+mL6F41jU1DPV25wSl6y5/+/pf/QfynY/ZoGOlS/tGpwdQekvd3zBd5zw==");
      } else {
         return var1.containsKey(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nZrCmUaxPCoJmHcSl1ZTpM5VHcQFSUq4a+UfqKAR8KHPSOuZjA76XA=="))
            ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("s7tNCiCq8nDcX1cnRt1hMnUFsO+CNrXGgacY+E8178BPUAr3HHJ9oXCGn2R0VYgm0xI=")
            : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8y2oGcYV9iDNWyOGTUS+o03pzLZnRSDoG8yvUuICa0B8Id7y");
      }
   }

   public boolean setTargetItemMaterial(EditorSession var1, String var2, Material var3) {
      if (((707748170 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return var3 != null && var3 != Material.AIR && this.setTargetItemToken(var1, var2, this.buildSidebarItemToken(var3, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MsgEbSK15vB4IsW8hLgdl1LbX1CDAsGPf3twwQ=="), null));
      }
   }

   public boolean setTargetItemToken(EditorSession var1, String var2, String var3) {
      if (((1490011398 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         String var4 = this.normalizeSidebarItemTokenInput(var3);
         if (var4.isBlank()) {
            return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-304417029, 7) != 0);
         } else {
            Map var5 = this.resolveRawTargetByTargetId(var1, var2);
            if (var5 == null) {
               return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 20) != 0);
            } else {
               String var6 = this.readTargetItemMaterialToken(var5);
               String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1636070749, 21)];
               var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1416873841, 10)] = this.normalizeSidebarItemTokenInput(var6);
               var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-76104226, 5)] = var6;
               String var7 = this.firstNonBlank(var10001);
               if (var4.equals(var7)) {
                  return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1416873841, 10) != 0);
               } else {
                  String var8 = this.resolveTargetItemMaterialWritePath(var5);
                  this.setMapPathValue(var5, var8, var4);
                  this.removeTargetItemData(var5);
                  return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-285746251, 24) != 0);
               }
            }
         }
      }
   }

   public void removeTargetItemData(Map<String, Object> var1) {
      if (((-1277680623 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         var1.remove(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ht/FzAtZZxur0PuX84XCdK+iNv5KWPVwrD0mTbXci8/kUErDMHTLOaQ8RqT3Lw=="));
         if (var1.get(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nZrCmUaxPCoJmHcSl1ZTpM5VHcQFSUq4a+UfqKAR8KHPSOuZjA76XA==")) instanceof Map var3) {
            var3.remove(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ht/FzAtZZxur0PuX84XCdK+iNv5KWPVwrD0mTbXci8/kUErDMHTLOaQ8RqT3Lw=="));
         }

         if (var1.get(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8y2oGcYV9iDNWyOGTUS+o03pzLZnRSDoG8yvUuICa0B8Id7y")) instanceof Map var4) {
            var4.remove(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZGPSlDo6n/qLfzO+ife9VlOiRCvExPcR/Uix2PnmUaDaWO6G"));
         }
      }
   }

   public boolean setTargetItemData(EditorSession var1, String var2, String var3) {
      if ((-570452588 ^ -570452588) != 0) {
         throw new IllegalStateException();
      } else {
         Map var4 = this.resolveRawTargetByTargetId(var1, var2);
         if (var4 == null) {
            return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-273191973, 1) != 0);
         } else {
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681220694, 9)];
            var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1416873841, 10)] = var3;
            String var5 = this.firstNonBlank(var10001).trim();
            Object var6 = var4.get(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ht/FzAtZZxur0PuX84XCdK+iNv5KWPVwrD0mTbXci8/kUErDMHTLOaQ8RqT3Lw=="));
            if (var5.isBlank()) {
               if (var6 == null) {
                  return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(356990071, 6) != 0);
               } else {
                  this.removeTargetItemData(var4);
                  return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1685415510, 22) != 0);
               }
            } else if (var5.equals(this.stringValue(var6))) {
               return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1874779567, 27) != 0);
            } else {
               var4.put(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ht/FzAtZZxur0PuX84XCdK+iNv5KWPVwrD0mTbXci8/kUErDMHTLOaQ8RqT3Lw=="), var5);
               return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(485547093, 16) != 0);
            }
         }
      }
   }

   public Map<String, Integer> parseTargetItemEnchantmentsMap(Object var1) {
      if (((-384452636 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 instanceof Map var2) {
         Map<String, Integer> var3 = new LinkedHashMap<>();

         for (Map.Entry<?, ?> var5 : ((Map<?, ?>)var2).entrySet()) {
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-253995748, 24)];
            var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-474437063, 25)] = this.stringValue(var5.getKey());
            String var6 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
            if (!var6.isBlank()) {
               double var7 = this.parseDouble(var5.getValue(), Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(6515504141874261196L, 3)));
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

   public Map<String, Integer> normalizeTargetItemEnchantments(Map<String, Integer> var1) {
      if ((-1411606416 * -1411606416 - -1411606416 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         Map<String, Integer> var2 = new LinkedHashMap<>();

         for (Map.Entry<String, Integer> var4 : var1.entrySet()) {
            String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1614112342, 26)];
            var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 15)] = (String)var4.getKey();
            String var5 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
            if (!var5.isBlank()) {
               Integer var6 = (Integer)var4.getValue();
               if (var6 != null && var6 > 0) {
                  var2.put(var5, var6);
               }
            }
         }

         return var2.isEmpty() ? Collections.emptyMap() : var2;
      } else {
         return Collections.emptyMap();
      }
   }

   public Map<String, Integer> readTargetItemEnchantments(Map<String, Object> var1) {
      if ((118136537 * (118136537 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && !var1.isEmpty()) {
         String[] var10000 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830658562, 16)];
         var10000[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1416873841, 10)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
            "qqeMo91tFgcVPMqub8gKk0TsVDU4dP2kaZc1LPkYfVpfqc1BnW6GEiJ09H3I6FQaBR/Qf7Le"
         );
         var10000[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221214, 3)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
            "q38jTcmnDaNcSvSdq2IVZ252TP7McI8QgTV3DCVxALFlpU72jyKVHBcExoA="
         );
         var10000[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(713980398, 7)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
            "SRrXTHf5h2Ot0S65lTp5XHWQXXAimzTtrRZEPJEP+NA0GSytfXXejzI0cV9gGk42c3IA9q1n0oxwvhXRAf5+0UtnEck="
         );
         var10000[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1242499109, 16)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
            "30/lXKAaEgCCKooVZwWN3Y+u+343k+DWAYGRz1UUpi1zWXi4NP6hnJw0fL8kDvqDX/6TNi1J/y3Lpg=="
         );
         var10000[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(356990327, 6)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
            "IDBP5qbxrnfi3oDqzSMjM7QjzRfc1AVkznavxo7FCf2FniyfH/oCNMq0+CaQo/6Hs/G9akJ4t2mHh3/Xaik="
         );
         var10000[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-674504850, 18)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
            "p5UzFLdaNxWl5pEL+C3R49YuXRFQREAjIqGtbCBBbiEW2J9LJs7y/MEhUU0JxgoTrzCNjw=="
         );
         var10000[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1549862006, 13)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
            "JlvgXC9VuY9d4tM4WPgUcWAdf0us+/RtmN8U7drlp+jI7CPkLc2DU0Gtd58nTTXCvmCidLxtm96dRlJ7ZWzGd08m7DKli/AnQvTN/Q=="
         );
         var10000[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1599045051, 20)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
            "YRKM6NhnNs76MffS+zJykhm/F9Hj4twcufOSvOKfbXRfMkOKkLdSMLt+sOAgw1XUsr2r7ufN0SG9DWI6dNf4O1la"
         );
         String[] var2 = var10000;
         String[] var3 = var2;
         int var4 = var2.length;

         for (int var5 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1416873841, 10); var5 < var4; var5++) {
            String var6 = var3[var5];
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

   public String resolveTargetItemEnchantmentsWritePath(Map<String, Object> var1) {
      if ((1446640912 * (1446640912 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("q38jTcmnDaNcSvSdq2IVZ252TP7McI8QgTV3DCVxALFlpU72jyKVHBcExoA=");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("qqeMo91tFgcVPMqub8gKk0TsVDU4dP2kaZc1LPkYfVpfqc1BnW6GEiJ09H3I6FQaBR/Qf7Le"))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("qqeMo91tFgcVPMqub8gKk0TsVDU4dP2kaZc1LPkYfVpfqc1BnW6GEiJ09H3I6FQaBR/Qf7Le");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("q38jTcmnDaNcSvSdq2IVZ252TP7McI8QgTV3DCVxALFlpU72jyKVHBcExoA="))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("q38jTcmnDaNcSvSdq2IVZ252TP7McI8QgTV3DCVxALFlpU72jyKVHBcExoA=");
      } else if (this.hasMapPath(
         var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SRrXTHf5h2Ot0S65lTp5XHWQXXAimzTtrRZEPJEP+NA0GSytfXXejzI0cV9gGk42c3IA9q1n0oxwvhXRAf5+0UtnEck=")
      )) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SRrXTHf5h2Ot0S65lTp5XHWQXXAimzTtrRZEPJEP+NA0GSytfXXejzI0cV9gGk42c3IA9q1n0oxwvhXRAf5+0UtnEck=");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("30/lXKAaEgCCKooVZwWN3Y+u+343k+DWAYGRz1UUpi1zWXi4NP6hnJw0fL8kDvqDX/6TNi1J/y3Lpg=="))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("30/lXKAaEgCCKooVZwWN3Y+u+343k+DWAYGRz1UUpi1zWXi4NP6hnJw0fL8kDvqDX/6TNi1J/y3Lpg==");
      } else if (this.hasMapPath(
         var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("IDBP5qbxrnfi3oDqzSMjM7QjzRfc1AVkznavxo7FCf2FniyfH/oCNMq0+CaQo/6Hs/G9akJ4t2mHh3/Xaik=")
      )) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("IDBP5qbxrnfi3oDqzSMjM7QjzRfc1AVkznavxo7FCf2FniyfH/oCNMq0+CaQo/6Hs/G9akJ4t2mHh3/Xaik=");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("p5UzFLdaNxWl5pEL+C3R49YuXRFQREAjIqGtbCBBbiEW2J9LJs7y/MEhUU0JxgoTrzCNjw=="))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("p5UzFLdaNxWl5pEL+C3R49YuXRFQREAjIqGtbCBBbiEW2J9LJs7y/MEhUU0JxgoTrzCNjw==");
      } else if (this.hasMapPath(
         var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("JlvgXC9VuY9d4tM4WPgUcWAdf0us+/RtmN8U7drlp+jI7CPkLc2DU0Gtd58nTTXCvmCidLxtm96dRlJ7ZWzGd08m7DKli/AnQvTN/Q==")
      )) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("JlvgXC9VuY9d4tM4WPgUcWAdf0us+/RtmN8U7drlp+jI7CPkLc2DU0Gtd58nTTXCvmCidLxtm96dRlJ7ZWzGd08m7DKli/AnQvTN/Q==");
      } else if (this.hasMapPath(
         var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("YRKM6NhnNs76MffS+zJykhm/F9Hj4twcufOSvOKfbXRfMkOKkLdSMLt+sOAgw1XUsr2r7ufN0SG9DWI6dNf4O1la")
      )) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("YRKM6NhnNs76MffS+zJykhm/F9Hj4twcufOSvOKfbXRfMkOKkLdSMLt+sOAgw1XUsr2r7ufN0SG9DWI6dNf4O1la");
      } else {
         Object var2 = this.readMapPathValue(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8y2oGcYV9iDNWyOGTUS+o03pzLZnRSDoG8yvUuICa0B8Id7y"));
         if (var2 instanceof Map
            || this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("m20dwIWjyf0aDtr4zwdfCm0mupLuImzkF/CHhq0sUWxhFRANXu1F9BG61n0E1vByBLFD7aRF"))) {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("qqeMo91tFgcVPMqub8gKk0TsVDU4dP2kaZc1LPkYfVpfqc1BnW6GEiJ09H3I6FQaBR/Qf7Le");
         } else if (!this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8y2oGcYV9iDNWyOGTUS+o03pzLZnRSDoG8yvUuICa0B8Id7y"))
            && !this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Vpy0z1b20Ppa2SxhvPnE5i7oHIHHkX44YX7RRkf6a+RTaYLsyZG72dmyssM="))) {
            Object var3 = this.readMapPathValue(
               var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("s7tNCiCq8nDcX1cnRt1hMnUFsO+CNrXGgacY+E8178BPUAr3HHJ9oXCGn2R0VYgm0xI=")
            );
            if (var3 instanceof Map
               || this.hasMapPath(
                  var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("3c30+jCGGXJcJMBz3blK7qCbbQVRQuGZx9oU2urGZ62Fw2XimiV6kcedIbVH9WUY2SpB2Mv1mSmu1a49oGh4fy4B3qM=")
               )) {
               return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SRrXTHf5h2Ot0S65lTp5XHWQXXAimzTtrRZEPJEP+NA0GSytfXXejzI0cV9gGk42c3IA9q1n0oxwvhXRAf5+0UtnEck=");
            } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("s7tNCiCq8nDcX1cnRt1hMnUFsO+CNrXGgacY+E8178BPUAr3HHJ9oXCGn2R0VYgm0xI="))
               || this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nzOViiG66pH5ju9GzQDVv+mL6F41jU1DPV25wSl6y5/+/pf/QfynY/ZoGOlS/tGpwdQekvd3zBd5zw=="))
               )
             {
               return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("30/lXKAaEgCCKooVZwWN3Y+u+343k+DWAYGRz1UUpi1zWXi4NP6hnJw0fL8kDvqDX/6TNi1J/y3Lpg==");
            } else {
               return var1.containsKey(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nZrCmUaxPCoJmHcSl1ZTpM5VHcQFSUq4a+UfqKAR8KHPSOuZjA76XA=="))
                  ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("30/lXKAaEgCCKooVZwWN3Y+u+343k+DWAYGRz1UUpi1zWXi4NP6hnJw0fL8kDvqDX/6TNi1J/y3Lpg==")
                  : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("q38jTcmnDaNcSvSdq2IVZ252TP7McI8QgTV3DCVxALFlpU72jyKVHBcExoA=");
            }
         } else {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("q38jTcmnDaNcSvSdq2IVZ252TP7McI8QgTV3DCVxALFlpU72jyKVHBcExoA=");
         }
      }
   }

   public boolean setTargetItemEnchantments(EditorSession var1, String var2, Map<String, Integer> var3) {
      if (((-1612146450 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Map var4 = this.resolveRawTargetByTargetId(var1, var2);
         if (var4 == null) {
            return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 19) != 0);
         } else {
            Map var5 = this.normalizeTargetItemEnchantments(var3);
            Map var6 = this.normalizeTargetItemEnchantments(this.readTargetItemEnchantments(var4));
            if (var6.equals(var5)) {
               return (Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 16) != 0);
            } else {
               String var7 = this.resolveTargetItemEnchantmentsWritePath(var4);
               LinkedHashMap var8 = new LinkedHashMap();

               for (Map.Entry<?, ?> var10 : ((Map<?, ?>)var5).entrySet()) {
                  var8.put((String)var10.getKey(), var10.getValue());
               }

               this.setMapPathValue(var4, var7, var8);
               return (Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(971094186, 17) != 0);
            }
         }
      }
   }

   public boolean readTargetItemGlowing(EditorSession var1, String var2) {
      if ((-1475911987 * -1475911987 - -1475911987 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Map var3 = this.resolveRawTargetByTargetId(var1, var2);
         if (var3 != null && !var3.isEmpty()) {
            String[] var10000 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-299200901, 31)];
            var10000[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 14)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "xZGOVvtJQz2Xl88tOrjdcG/JT4Tb0MRp7zTKk04/HDDp6R0xnzDwKKb9"
            );
            var10000[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 12)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "o6tWrgkGfenX9xDlv9S8WgLyHSCDN/Jxbhv8dyXUa54JRaaPIbnvXw20KzilHr5PXupcOg=="
            );
            var10000[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(2143215023, 27)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "nMMIbqkDiVqm2RzNOlFpNWrkkpkBkm9BEZItBtRuzYzt3lb8JI48PP0N5GCjFHc7"
            );
            var10000[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-2017534545, 27)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "glsW7/0EKjJ0q9emo3of1UqqsEXjDMv2WJtq/zAEcqstNsHYMv104EKMLiUCkTvzwpThd4cWmyk="
            );
            var10000[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681223254, 9)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "B1oJP65Lxwb73Ywn7mQRUw9dSBI7OnVD1K3ZI3VlsAVC+xWIXBuoCxmw/CEC2Oj8JPKSdol5J7BolQMPeDWq0ySO"
            );
            var10000[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1372540357, 12)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
               "+X1jJh3NlhdvVpfdU2rFeAykM+YhyafPBU/NB6mtIWtaXyEuWlwASRHdXEI4DzMTM+EmsjsxnKVTzY7t5Dg="
            );
            String[] var4 = var10000;
            String[] var5 = var4;
            int var6 = var4.length;

            for (int var7 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 0); var7 < var6; var7++) {
               String var8 = var5[var7];
               Object var9 = this.readMapPathValue(var3, var8);
               if (var9 != null) {
                  return this.parseBooleanFlag(var9, (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 29) != 0));
               }
            }

            return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 7) != 0);
         } else {
            return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 2) != 0);
         }
      }
   }

   public String resolveTargetItemGlowingWritePath(Map<String, Object> var1) {
      if ((679593529 * 679593529 - 679593529 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 == null) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xZGOVvtJQz2Xl88tOrjdcG/JT4Tb0MRp7zTKk04/HDDp6R0xnzDwKKb9");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xZGOVvtJQz2Xl88tOrjdcG/JT4Tb0MRp7zTKk04/HDDp6R0xnzDwKKb9"))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xZGOVvtJQz2Xl88tOrjdcG/JT4Tb0MRp7zTKk04/HDDp6R0xnzDwKKb9");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("o6tWrgkGfenX9xDlv9S8WgLyHSCDN/Jxbhv8dyXUa54JRaaPIbnvXw20KzilHr5PXupcOg=="))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("o6tWrgkGfenX9xDlv9S8WgLyHSCDN/Jxbhv8dyXUa54JRaaPIbnvXw20KzilHr5PXupcOg==");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nMMIbqkDiVqm2RzNOlFpNWrkkpkBkm9BEZItBtRuzYzt3lb8JI48PP0N5GCjFHc7"))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nMMIbqkDiVqm2RzNOlFpNWrkkpkBkm9BEZItBtRuzYzt3lb8JI48PP0N5GCjFHc7");
      } else if (this.hasMapPath(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("glsW7/0EKjJ0q9emo3of1UqqsEXjDMv2WJtq/zAEcqstNsHYMv104EKMLiUCkTvzwpThd4cWmyk="))) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("glsW7/0EKjJ0q9emo3of1UqqsEXjDMv2WJtq/zAEcqstNsHYMv104EKMLiUCkTvzwpThd4cWmyk=");
      } else if (this.hasMapPath(
         var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("B1oJP65Lxwb73Ywn7mQRUw9dSBI7OnVD1K3ZI3VlsAVC+xWIXBuoCxmw/CEC2Oj8JPKSdol5J7BolQMPeDWq0ySO")
      )) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("B1oJP65Lxwb73Ywn7mQRUw9dSBI7OnVD1K3ZI3VlsAVC+xWIXBuoCxmw/CEC2Oj8JPKSdol5J7BolQMPeDWq0ySO");
      } else if (this.hasMapPath(
         var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("+X1jJh3NlhdvVpfdU2rFeAykM+YhyafPBU/NB6mtIWtaXyEuWlwASRHdXEI4DzMTM+EmsjsxnKVTzY7t5Dg=")
      )) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("+X1jJh3NlhdvVpfdU2rFeAykM+YhyafPBU/NB6mtIWtaXyEuWlwASRHdXEI4DzMTM+EmsjsxnKVTzY7t5Dg=");
      } else if (this.readMapPathValue(var1, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("8y2oGcYV9iDNWyOGTUS+o03pzLZnRSDoG8yvUuICa0B8Id7y")) instanceof Map) {
         return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("o6tWrgkGfenX9xDlv9S8WgLyHSCDN/Jxbhv8dyXUa54JRaaPIbnvXw20KzilHr5PXupcOg==");
      } else {
         return var1.containsKey(Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("nZrCmUaxPCoJmHcSl1ZTpM5VHcQFSUq4a+UfqKAR8KHPSOuZjA76XA=="))
            ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("glsW7/0EKjJ0q9emo3of1UqqsEXjDMv2WJtq/zAEcqstNsHYMv104EKMLiUCkTvzwpThd4cWmyk=")
            : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xZGOVvtJQz2Xl88tOrjdcG/JT4Tb0MRp7zTKk04/HDDp6R0xnzDwKKb9");
      }
   }

   public boolean setTargetItemGlowing(EditorSession var1, String var2, boolean var3) {
      if ((-1930109222 * -1930109222 - -1930109222 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Map var4 = this.resolveRawTargetByTargetId(var1, var2);
         if (var4 == null) {
            return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 13) != 0);
         } else {
            boolean var5 = this.readTargetItemGlowing(var1, var2);
            if (var5 == var3) {
               return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1809575863, 17) != 0);
            } else {
               String var6 = this.resolveTargetItemGlowingWritePath(var4);
               this.setMapPathValue(var4, var6, var3);
               return (Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1151406211, 12) != 0);
            }
         }
      }
   }

   @Override
   public void toggleSidebarItemDesignGlowing(Player var1, EditorSession var2) {
      if ((863944445 * 863944445 + 863944445 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         List var3 = this.getSidebarEditableTargetIds(var2);
         if (var3.isEmpty()) {
            this.updateEditorPropertiesSidebar(var1, var2);
         } else {
            boolean var4 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 18) != 0;
            int var5 = Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 27);
            int var6 = Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-598401807, 0);

            for (String var8 : (Iterable<String>) (var3)) {

               if (this.isSidebarItemDesignTarget(var2, var8)) {
                  var6 = Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-546383941, 2);
                  boolean var9 = this.setTargetItemGlowing(
                     var2,
                     var8,
                     ((!this.readTargetItemGlowing(var2, var8)
                        ? Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-675553426, 18)
                        : Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 18)) != 0)
                  );
                  if (var9 && var5 == 0) {
                     this.recordMutationForInteraction(var2);
                     var5 = Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 29);
                  }

                  var4 |= var9;
               }
            }

            if (var6 != 0 && var4) {
               this.rerenderEditableSelection(var1, var2);
            } else {
               this.updateEditorPropertiesSidebar(var1, var2);
            }
         }
      }
   }

   @Override
   public double readTargetLayer(EditorSession var1, String var2) {
      if (((-943976899 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         if (var3 != null && Double.isFinite(var3.z)) {
            return var3.z;
         } else {
            Map var4 = this.resolveRawTargetByTargetId(var1, var2);
            if (var4 == null) {
               return Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699518298545L, 60));
            } else if (this.hasMapPath(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("p0bKKg1zr+Q4vpPJ9KjRLcwqYACILFzzF/R6St5o6lwHP51RRYE="))) {
               return this.readMapPathDouble(
                  var4,
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("p0bKKg1zr+Q4vpPJ9KjRLcwqYACILFzzF/R6St5o6lwHP51RRYE="),
                  Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699518298545L, 50))
               );
            } else if (this.hasMapPath(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Ih2CJAtB1fwFkJFw79T3T4TtLXyOpSiUw/W6FBg5ChxZS4iqL6/K+4122C0MReDT"))) {
               return this.readMapPathDouble(
                  var4,
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Ih2CJAtB1fwFkJFw79T3T4TtLXyOpSiUw/W6FBg5ChxZS4iqL6/K+4122C0MReDT"),
                  Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_1rl77af7rf40c(7819561285544889370L, 24))
               );
            } else if (this.hasMapPath(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("yQ7PCHPzn8xjeFfnHE0oVP5u4w1vD+sj0OA61xSAE3FhIPv2J0E="))) {
               return this.readMapPathDouble(
                  var4,
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("yQ7PCHPzn8xjeFfnHE0oVP5u4w1vD+sj0OA61xSAE3FhIPv2J0E="),
                  Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699518298545L, 15))
               );
            } else if (this.hasMapPath(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("jhx24v6l7Ce5kRguaddPtnagmyPoWZmbFC7LH8Bzh3vRmIMb8SnUA7DzEw7qAzpDL6p7zg=="))) {
               return this.readMapPathDouble(
                  var4,
                  Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("jhx24v6l7Ce5kRguaddPtnagmyPoWZmbFC7LH8Bzh3vRmIMb8SnUA7DzEw7qAzpDL6p7zg=="),
                  Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699518298545L, 25))
               );
            } else {
               return this.hasMapPath(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("byTU+0gkr2U0u9hKM5oMA++J+oMFO7mszBk+3f4RBd40IOHi7n9VD/2Xj/OQLc0YXKBZ1w=="))
                  ? this.readMapPathDouble(
                     var4,
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("byTU+0gkr2U0u9hKM5oMA++J+oMFO7mszBk+3f4RBd40IOHi7n9VD/2Xj/OQLc0YXKBZ1w=="),
                     Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_33sqz5sig1kb9(-5902012699518298545L, 4))
                  )
                  : Double.longBitsToDouble(Xqedii_2ze73vg.Xqedii_24apgtc0fhlub(9019968724964737894L, 62));
            }
         }
      }
   }

   @Override
   public String readTargetColor(EditorSession var1, String var2) {
      if ((830367426 * 830367426 + 830367426 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
            Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
            if (var4 == null) {
               return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("C+852nfKg74J9IxxMl6Vi+EbG1EnpZVNBgPAGWI4a4FtcTSQ4F7jvA==");
            } else {
               String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1304777167, 28)];
               var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 6)] = this.stringValue(
                  this.readMapPathValue(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SXnbKYNGrvJiwTC16d7CCneH8Zgjcjc0A8yLx/7xI0FG0Tc/Fws="))
               );
               var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1144350294, 29)] = this.stringValue(
                  this.readMapPathValue(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Tz330h7gwv5vcG1MWRF2ngkGZFlHwxIvEnBSVPWE4ks8fk5KtG6eKmD7oj3tu8tP41I="))
               );
               var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830789634, 16)] = this.stringValue(
                  this.readMapPathValue(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("h0gPJe6jG+lWwulKcDHMI9L0oUb7KKoT6OAQM9ee/tixUYWoEkjaCNFNSabhY8iFZCS6tg=="))
               );
               var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1968988581, 5)] = this.stringValue(
                  this.readMapPathValue(
                     var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("WjvZ1pFhIMvHfu4sF01a2VoQYTzifZcuYZy5aBU7QCttHz/y7gLbXjSuDqbhLwfETk0lUZSX7MWVWQZQLZmYhQ==")
                  )
               );
               var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-1151393923, 12)] = Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                  "C+852nfKg74J9IxxMl6Vi+EbG1EnpZVNBgPAGWI4a4FtcTSQ4F7jvA=="
               );
               String var5 = this.firstNonBlank(var10001);
               String var6 = this.normalizeHexColor(var5);
               return var6.isBlank() ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("C+852nfKg74J9IxxMl6Vi+EbG1EnpZVNBgPAGWI4a4FtcTSQ4F7jvA==") : var6;
            }
         } else {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("C+852nfKg74J9IxxMl6Vi+EbG1EnpZVNBgPAGWI4a4FtcTSQ4F7jvA==");
         }
      }
   }

   @Override
   public String readTargetOutlineColor(EditorSession var1, String var2) {
      if ((-1545173899 ^ -1545173899) != 0) {
         throw new IllegalStateException();
      } else {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
            Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
            if (var4 == null) {
               return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("C+852nfKg74J9IxxMl6Vi+EbG1EnpZVNBgPAGWI4a4FtcTSQ4F7jvA==");
            } else {
               String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1714775638, 23)];
               var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 4)] = this.stringValue(
                  this.readMapPathValue(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZiCT1Cbhw0BkCBETRETw6GisNl3+G+LgZPihItcIXd4xV4JTvBFwiJ6326hO5MjV/BpgqGnG"))
               );
               var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1942188372, 18)] = this.stringValue(
                  this.readMapPathValue(
                     var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LNJMlIiLSnsf58YEWnIG54/ly13i1o5Ur4EC9vb7vhlPv9KAFb3WbhghYhblsLPQHFco5cBVJ7J2Rq+sHDwyJhfq")
                  )
               );
               var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1461215518, 11)] = this.stringValue(
                  this.readMapPathValue(
                     var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ddL/lp3n1H+FLyWbUI6K+PjQ3DOshWJ8F7GX9QCQM8yDta6IG4YSRSIArvp82mHMVYWdjoHUvpUJY++1CfSL0oI4wuY=")
                  )
               );
               var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681483350, 18)] = this.stringValue(
                  this.readMapPathValue(
                     var4,
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                        "ZZuIbR0lzMIB/LDQor+a7lBOrJtlpgvs7Efr6BRak2nH0xsG/OaMAyVY4A86LUFdTlx2ih+BLWjmdxR/39YzApR8itdAV0gEN22Ky1JMnRQ="
                     )
                  )
               );
               String var5 = this.firstNonBlank(var10001);
               String var6 = this.normalizeHexColor(var5);
               if (!var6.isBlank()) {
                  return var6;
               } else {
                  String var7 = this.readTargetColor(var1, var2);
                  return var7.isBlank() ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("C+852nfKg74J9IxxMl6Vi+EbG1EnpZVNBgPAGWI4a4FtcTSQ4F7jvA==") : var7;
               }
            }
         } else {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("C+852nfKg74J9IxxMl6Vi+EbG1EnpZVNBgPAGWI4a4FtcTSQ4F7jvA==");
         }
      }
   }

   @Override
   public boolean isTargetOutlineColorAuto(EditorSession var1, String var2) {
      if (((-1086971630 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
            Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
            return var4 != null && this.isRawTargetOutlineColorAuto(var4);
         } else {
            return (Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221206, 20) != 0);
         }
      }
   }

   public String readTargetOutlineSidebarValue(EditorSession var1, String var2) {
      if (((-93139484 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return this.isTargetOutlineColorAuto(var1, var2)
            ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("En4qasj/b28yZUu5oK1T64n790iRL8cdAXNtE7DV7HbtcsnW")
            : this.formatSidebarHexValue(this.readTargetOutlineColor(var1, var2));
      }
   }

   public String readTargetHoverColor(EditorSession var1, String var2) {
      if (((1667733998 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
            Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
            if (var4 == null) {
               return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("uT3jAPh+lYslZqvREZ8+PjW97XbUEMEreDRB2DUqH4z9GoUy");
            } else {
               String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(1597996475, 20)];
               var10001[Xqedii_2ze73vg.Xqedii_13jq3c36au68f(-621347859, 15)] = this.stringValue(
                  this.readMapPathValue(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MWsvgonQkTTAA5craeHteqFSriKo8OoOYbPKrKpM5aIJkF+dzA0dv72xznD0/jW+AwU="))
               );
               var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830855170, 7)] = this.stringValue(
                  this.readMapPathValue(
                     var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("bNKIsEslxjlPzYhcw9OQk/RCEtzLd/4a1HZuu3w9yJcEsYHBHaiao1974hLqPykY+xtJokHocYFesyPZwCc=")
                  )
               );
               var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1372536261, 12)] = this.stringValue(
                  this.readMapPathValue(
                     var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("KdQbvCCNkxbcYDcPVf2xWRaVg8bPMe3v3T4Py5KLTyx84GFwZA5Hgf9JNkzIjwIH6qHETevSRVl3Q5F5D79UOQ==")
                  )
               );
               var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-1439046216, 9)] = this.stringValue(
                  this.readMapPathValue(
                     var4,
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                        "2eO8TupijXQhOp+SZGejxGVTrYcxoJk5OFtIGvTUXUWU1HCswQt6s/oiyDp2JecKIYvbuhtwR8/5axAiq7XeJ48BhdKeaMzLzTPShQ=="
                     )
                  )
               );
               var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(830920706, 16)] = this.stringValue(
                  this.readMapPathValue(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("5PqHhLUnJdzxrUuJleATTyGs+S0kVZXyiuaU90lvCAHk9cCE2HX173Nad62FkiuO"))
               );
               var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(-492247130, 3)] = this.stringValue(
                  this.readMapPathValue(
                     var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("FTxIHTbTNGLP8hlKFzYg/MOyHeS6MGeuDr07It7gEVNiaNVuiz/TZ0YesYJq2OhBVuNb/+xN+XcmeLnNZ7g=")
                  )
               );
               String var5 = this.firstNonBlank(var10001);
               if (!var5.isBlank()) {
                  return this.normalizeHoverColorSettingToken(var5);
               } else {
                  String var6 = this.resolveRawTargetType(var4, var3);
                  return !Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("phMhQ5z6/nUG7x9rIjeqZ9DFFsF7Num7Ht8iWfSlIm4ZUFO5Sic=").equals(var6) && !this.isRoundedType(var6)
                     ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("uT3jAPh+lYslZqvREZ8+PjW97XbUEMEreDRB2DUqH4z9GoUy")
                     : Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LKKcXIHpjgBU+nETsXnA8WA6AHKFNlJKiSkJACNDtoY3zlWO");
               }
            }
         } else {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("uT3jAPh+lYslZqvREZ8+PjW97XbUEMEreDRB2DUqH4z9GoUy");
         }
      }
   }

   public String readTargetHoverEffect(EditorSession var1, String var2) {
      if ((-760017253 ^ -760017253) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var1.activeEffectOverride != null && !var1.activeEffectOverride.isBlank()) {
         return var1.activeEffectOverride;
      } else {
         HoverElement var3 = this.findFirstByTargetId(var1, var2);
         if (var3 != null && var3.targetPath != null && !var3.targetPath.isBlank()) {
            Map var4 = this.resolveRawMapAtPath(var1.rawBlocks, var3.targetPath);
            if (var4 == null) {
               return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LKKcXIHpjgBU+nETsXnA8WA6AHKFNlJKiSkJACNDtoY3zlWO");
            } else {
               String[] var10001 = new String[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(428201986, 27)];
               var10001[Xqedii_2ze73vg.Xqedii_9l9vlchy5lmc(-830855171, 21)] = this.stringValue(
                  this.readMapPathValue(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("rmx0ivpbDl1DlLnzJ/YpSRgN0qokprkmDFRbSCPQwTALMZasNeC+MtKW5HACBBzz9zWGFQ=="))
               );
               var10001[Xqedii_2ze73vg.Xqedii_14pdpm3hddrn8(1848282744, 31)] = this.stringValue(
                  this.readMapPathValue(
                     var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xvTZluU8JTd4E0pJEJmW6Nsheq+DC2X+R1pUk2ikwmXD9Eec8LtWEtEmycl6oiVgW43u9kugZWYaTXxZkD8MSg==")
                  )
               );
               var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221238, 4)] = this.stringValue(
                  this.readMapPathValue(
                     var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("t5eaJChVFTRYXms6AXsWdesxxIimaqcAn8m7huK9/bUIe2bB2QjqL6SXSmGCXVLPmaIaRj2uY9w4AsXIw1zN11uo")
                  )
               );
               var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681221200, 1)] = this.stringValue(
                  this.readMapPathValue(
                     var4,
                     Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq(
                        "oziN/R+FGKJL0j33L6sPWlogc+OGyt+fJqWHwEEV1ghzgl7QGI43sK/FLmR2UsHxZg2PGP2HdwZXyWR/PzfO9o4CAZf1tzN2EUKhrSjt"
                     )
                  )
               );
               var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681220182, 8)] = this.stringValue(
                  this.readMapPathValue(var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("EI1gjoZIcnO9iHv9+2oh8CfLB5OgyE9XRQFGO1j9pX6zpv5u959wXfqXuih18f3XZsA="))
               );
               var10001[Xqedii_2ze73vg.Xqedii_18u831nn38lo(1681245782, 13)] = this.stringValue(
                  this.readMapPathValue(
                     var4, Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("F7edfx3sRUZoicd0zcaE0/TH/9WOymvlLUVnP7dntmbxyJhm4MUQA2UpYNuf3YqNdHVDaNQn38hajoSPcrxn+g==")
                  )
               );
               String var5 = this.firstNonBlank(var10001);
               String var6 = this.normalizeHoverEffectId(var5);
               return var6.isBlank() ? Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LKKcXIHpjgBU+nETsXnA8WA6AHKFNlJKiSkJACNDtoY3zlWO") : var6;
            }
         } else {
            return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LKKcXIHpjgBU+nETsXnA8WA6AHKFNlJKiSkJACNDtoY3zlWO");
         }
      }
   }

   public String readTargetRadiusLabel(EditorSession var1, String var2) {
      if (((-719425867 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return this.readTargetBorderRadiusMode(var1, var2);
      }
   }


   private static /* synthetic */ String Xqedii_26jubhuuqlahz(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("uJqTmH12VkpCt6yV/Nzf6Ij9j5L9jFZpEn/3JzXbvzNYsRqoyms3oAVNW4tlEvZq") + string;
    }

    private static /* synthetic */ String Xqedii_1z7vetggpafzc(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("NSeN2NkHKcEmFi+l4Kv0xRte+34WkDNiurvYkY0/N18QeADNVkcEi4pxY4Msyw==");
    }

    private static /* synthetic */ String Xqedii_ix38tvi4728g(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lkdT5TaomtvEcTg357s4M4JmdbxiOnKGNEm7IzgJMz+LJmCuuyrR6zPeRxaUxqzs");
    }

    private static /* synthetic */ String Xqedii_ilptmb6y2aiy(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GKkzeBnad1nLsE0XPi46ZYsXf0MMpaaiuIdikrVXtusQi/1buD3DgrzzR5MgdjXkJzpuxOoY") + string;
    }

    private static /* synthetic */ String Xqedii_1nxr69lv8zjrk(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GKkzeBnad1nLsE0XPi46ZYsXf0MMpaaiuIdikrVXtusQi/1buD3DgrzzR5MgdjXkJzpuxOoY") + string;
    }

    private static /* synthetic */ String Xqedii_3k161uslz21ln(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GKkzeBnad1nLsE0XPi46ZYsXf0MMpaaiuIdikrVXtusQi/1buD3DgrzzR5MgdjXkJzpuxOoY") + string;
    }

    private static /* synthetic */ String Xqedii_2wcy2znnhoo2u(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GKkzeBnad1nLsE0XPi46ZYsXf0MMpaaiuIdikrVXtusQi/1buD3DgrzzR5MgdjXkJzpuxOoY") + string;
    }

    private static /* synthetic */ String Xqedii_1lg829djassmg(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GKkzeBnad1nLsE0XPi46ZYsXf0MMpaaiuIdikrVXtusQi/1buD3DgrzzR5MgdjXkJzpuxOoY") + string;
    }

    private static /* synthetic */ String Xqedii_2056zd14ezcb0(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GKkzeBnad1nLsE0XPi46ZYsXf0MMpaaiuIdikrVXtusQi/1buD3DgrzzR5MgdjXkJzpuxOoY") + string;
    }

    private static /* synthetic */ String Xqedii_387vn7cd3s7zq(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GKkzeBnad1nLsE0XPi46ZYsXf0MMpaaiuIdikrVXtusQi/1buD3DgrzzR5MgdjXkJzpuxOoY") + string;
    }

    private static /* synthetic */ String Xqedii_3a3tsku0ra70q(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("bwRVpIs6Cl2PbfFhXyAmJpxGE9+a8WawrzFk7ZDSBp1mTj4SB3LaiGCQ");
    }

    private static /* synthetic */ String Xqedii_1zhg4h0mcm30c(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("RH4wsLcxAzHL4w7a38n8FYCLoCYLCOMWLVSPuAXkH1uiO0FK8+m/vQ==");
    }

    private static /* synthetic */ String Xqedii_1ceewn3wqdkjs(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("WDhavipo8h4OO0K4RtBNW7ByiUr2aPewIFZQ2Sboai4Iz/l9yO68vkJVMD22OA==");
    }

    private static /* synthetic */ String Xqedii_3gj4irfx1d7sy(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("5ZRIL2ovXP74xDJslgE3m76hRh/j7zFOxNgriltuaZ6se0Relmxc/s0M");
    }

    private static /* synthetic */ String Xqedii_2k7nlhme0jbl5(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SuGXy7zvR4SIHpQ7A5gYbW/s2mVvXKvebk52EdUpzN0N43Rz3F0wYb+QWJs=");
    }

    private static /* synthetic */ String Xqedii_1ctosa0pdohhi(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("9fH+gr71CiHr3IK98NlKMpnwTcgKVO4eF8y2id7BXYubU/0EhS8=");
    }

    private static /* synthetic */ String Xqedii_37yr1m6slqlte(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6Z8ToO5HXhmlZChhXH9ayAm7rSI/roq815yjgxPDg5bn8rHN8jY=");
    }

    private static /* synthetic */ String Xqedii_33iuq7ev3z5a(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("AUvef/Feb8SfhVqTwlZ2rgnUatGd9YczJFPz6vnZTWd6hPpd0Gw=");
    }

    private static /* synthetic */ String Xqedii_1sybz57ajtv3p(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("b0c8aq4L0diqFDaTNaikjv5firp13hfoqsik2LOBbMLcB1nRNT8=");
    }

    private static /* synthetic */ String Xqedii_1nx09v5sg2cb6(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GKkzeBnad1nLsE0XPi46ZYsXf0MMpaaiuIdikrVXtusQi/1buD3DgrzzR5MgdjXkJzpuxOoY") + string;
    }

    private static /* synthetic */ String Xqedii_2niwlc6zofezy(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("/XmaddYd6UPYBQ68EJrfX43XEzKr+/PnIeF6FPvHyNNEay2J5N4FBQbwf64=");
    }

    private static /* synthetic */ String Xqedii_2fb6tujftcq0c(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("bwRVpIs6Cl2PbfFhXyAmJpxGE9+a8WawrzFk7ZDSBp1mTj4SB3LaiGCQ");
    }

    private static /* synthetic */ String Xqedii_ye5r3nmtqizq(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("RH4wsLcxAzHL4w7a38n8FYCLoCYLCOMWLVSPuAXkH1uiO0FK8+m/vQ==");
    }

    private static /* synthetic */ String Xqedii_33hnavf59d2iv(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("WDhavipo8h4OO0K4RtBNW7ByiUr2aPewIFZQ2Sboai4Iz/l9yO68vkJVMD22OA==");
    }

    private static /* synthetic */ String Xqedii_3ffor1ag6xckp(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("5ZRIL2ovXP74xDJslgE3m76hRh/j7zFOxNgriltuaZ6se0Relmxc/s0M");
    }

    private static /* synthetic */ String Xqedii_3ju7f0dq6j77q(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SuGXy7zvR4SIHpQ7A5gYbW/s2mVvXKvebk52EdUpzN0N43Rz3F0wYb+QWJs=");
    }

    private static /* synthetic */ String Xqedii_1f603i66pcac6(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("9fH+gr71CiHr3IK98NlKMpnwTcgKVO4eF8y2id7BXYubU/0EhS8=");
    }

    private static /* synthetic */ String Xqedii_2sg7k1awoi9hl(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6Z8ToO5HXhmlZChhXH9ayAm7rSI/roq815yjgxPDg5bn8rHN8jY=");
    }

    private static /* synthetic */ String Xqedii_3akjfzx7hrhnz(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("AUvef/Feb8SfhVqTwlZ2rgnUatGd9YczJFPz6vnZTWd6hPpd0Gw=");
    }

    private static /* synthetic */ String Xqedii_sz99ul5p5yja(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("b0c8aq4L0diqFDaTNaikjv5firp13hfoqsik2LOBbMLcB1nRNT8=");
    }

    private static /* synthetic */ String Xqedii_3fdmnv2uv0aso(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("bwRVpIs6Cl2PbfFhXyAmJpxGE9+a8WawrzFk7ZDSBp1mTj4SB3LaiGCQ");
    }

    private static /* synthetic */ String Xqedii_3c5to6aaszkg8(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("RH4wsLcxAzHL4w7a38n8FYCLoCYLCOMWLVSPuAXkH1uiO0FK8+m/vQ==");
    }

    private static /* synthetic */ String Xqedii_26rnd9u6r5zi9(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("WDhavipo8h4OO0K4RtBNW7ByiUr2aPewIFZQ2Sboai4Iz/l9yO68vkJVMD22OA==");
    }

    private static /* synthetic */ String Xqedii_1u2r13mquugg7(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("5ZRIL2ovXP74xDJslgE3m76hRh/j7zFOxNgriltuaZ6se0Relmxc/s0M");
    }

    private static /* synthetic */ String Xqedii_q5pum5s9zbo6(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SuGXy7zvR4SIHpQ7A5gYbW/s2mVvXKvebk52EdUpzN0N43Rz3F0wYb+QWJs=");
    }

    private static /* synthetic */ String Xqedii_35ebcunxsxo1l(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("9fH+gr71CiHr3IK98NlKMpnwTcgKVO4eF8y2id7BXYubU/0EhS8=");
    }

    private static /* synthetic */ String Xqedii_38q9xl651wcdx(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6Z8ToO5HXhmlZChhXH9ayAm7rSI/roq815yjgxPDg5bn8rHN8jY=");
    }

    private static /* synthetic */ String Xqedii_1dwpbdvcpjliv(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("AUvef/Feb8SfhVqTwlZ2rgnUatGd9YczJFPz6vnZTWd6hPpd0Gw=");
    }

    private static /* synthetic */ String Xqedii_3rjxhutj1vwfl(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("b0c8aq4L0diqFDaTNaikjv5firp13hfoqsik2LOBbMLcB1nRNT8=");
    }

    private static /* synthetic */ String Xqedii_3r4gol7w1k66q(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GKkzeBnad1nLsE0XPi46ZYsXf0MMpaaiuIdikrVXtusQi/1buD3DgrzzR5MgdjXkJzpuxOoY") + string;
    }

    private static /* synthetic */ String Xqedii_3efl66alkva40(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GKkzeBnad1nLsE0XPi46ZYsXf0MMpaaiuIdikrVXtusQi/1buD3DgrzzR5MgdjXkJzpuxOoY") + string;
    }

    private static /* synthetic */ String Xqedii_93se9g48zijx(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GKkzeBnad1nLsE0XPi46ZYsXf0MMpaaiuIdikrVXtusQi/1buD3DgrzzR5MgdjXkJzpuxOoY") + string;
    }

    private static /* synthetic */ String Xqedii_2obeztrbg3p4y(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("bwRVpIs6Cl2PbfFhXyAmJpxGE9+a8WawrzFk7ZDSBp1mTj4SB3LaiGCQ");
    }

    private static /* synthetic */ String Xqedii_rqy12ohk1wuw(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("GKkzeBnad1nLsE0XPi46ZYsXf0MMpaaiuIdikrVXtusQi/1buD3DgrzzR5MgdjXkJzpuxOoY") + string;
    }

    private static /* synthetic */ String Xqedii_1p15vtlvprfjw(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("23d0G6hnkPiqieKMQFnPc3PpRmYeCRf0Y1ZJCm8C");
    }

    private static /* synthetic */ String Xqedii_1s7zvhathqo0o(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("/t/lM0OYToreKVJOXT876+n+wyDPC9SztI2JIaIC");
    }

    private static /* synthetic */ String Xqedii_1iiaqucatgum(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SxJkd8u3xtenig58Y6bQLZ3Eoxwmydwm449KIo5h");
    }

    private static /* synthetic */ String Xqedii_1pyz7hacstbxc(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("hku58spNZ6BXqYarhXyVa/291l0udXiozgRO12w0yW3lNUp4yASKAg==");
    }

    private static /* synthetic */ String Xqedii_3ld6yo0724ksi(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Cx2ACqcnUxQ8Asj++8YlBUnmKUW7KgvZHsJKdVao9Esb+XDTAZb5Qg==");
    }

    private static /* synthetic */ String Xqedii_353j9h79g1j2y(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("4bu5sBVsok/jNrgzZJnrnFGcUW7Sa+K4WaIdex0kPPU=") + string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("cM8SHoSqTVXPZRvJtdATlLhaVeR4zJe7AijiJUm76Pg=");
    }

    private static /* synthetic */ String Xqedii_tma1ota5wrbx(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("4bu5sBVsok/jNrgzZJnrnFGcUW7Sa+K4WaIdex0kPPU=") + string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("lbYy8CDFyTgsmdPS3d6O62kzYLJC1jWwp7WUwWpNY28=");
    }

    private static /* synthetic */ String Xqedii_3ghbtbihrgigj(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("peoMjmRIHSTFl7Fyj5+qRk+22sgw75cYUSvd4HYo") + string;
    }

    private static /* synthetic */ String Xqedii_a4ki2jg8ojd9(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LS1+t14VZvpK4Nq4tSNxPVI6QfbRaqikVEy/jghWErxDOQ==");
    }

    private static /* synthetic */ String Xqedii_srnc7rrajczt(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("LS1+t14VZvpK4Nq4tSNxPVI6QfbRaqikVEy/jghWErxDOQ==") + string;
    }

    private static /* synthetic */ String Xqedii_32w4b336vvjr9(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MzSEEiJ5PDZbngOYU1AoR+BS4wsHbXNLDNWKDrISczY7+X35ktmSkXoCTIbTYPpoPk44pikY7bUt1cCYAI4=") + string;
    }

    private static /* synthetic */ String Xqedii_1t2h8emvk15u2(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Il00aQ/OWS4DObnj0X66pAUQGnEC7EyZPNiPzU/uIGHYAwvO0aw2pPrdXwWkWi2VIEv5vf2y9C7YeodG") + string;
    }

    private static /* synthetic */ String Xqedii_2gr1jv2dq802(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("xAHc6wvHa/cvKQUnGJAxSQy6etrb/Agaog3QBrkxkLOcjw8g2xLfbxGL2EXbFEh9BY+0uoiL7DIhH3+hSok=") + string;
    }

    private static /* synthetic */ String Xqedii_1qx7sd8fmnlu3(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("dL0RkJsC9RywALxlXKy8Xx7UqJWH1B8u58MiqoMGFu5t3cWWZ3710G+rc+Aoy7WF6guhiuQ/Y299lRvS") + string;
    }

    private static /* synthetic */ String Xqedii_27tvw5bedp3s(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("ZwdCuWZIhE0gtQx4F2bnU1Wamm8YtmBiAg1F4/mU4321R7LRrWjWT2YWq5QMZ0Ptg7LVNiYKPjg9zS9z") + string;
    }

    private static /* synthetic */ String Xqedii_1cffkkk8rqll3(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("WynKwLucMGqeHVyln+KgWsBADam+u/nWYVvzbRtaJrbvk6Cs1rnR6zefs7ou3QhynFXAa/jJ5P+jBA==") + string;
    }

    private static /* synthetic */ String Xqedii_3q7le3zxl8kfm(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("mGxrrEBfNEAbAzFY/X33R8eyzl5PHmwdaYcwyic7tQM7BBd0KUfsQS+RUUA0soHdy25uJhO8R2QWrjDO") + string;
    }

    private static /* synthetic */ String Xqedii_u37xk4lj61ok(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("RpN6xK5/uubEmsMHiuOFT84tUB4HlxK5J8gT/C5h2dg9yjjpV0qDk4WEC0v6BsOH6pfWPVFKdWg=") + string;
    }

    private static /* synthetic */ String Xqedii_3df5beqifmqto(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("MzSEEiJ5PDZbngOYU1AoR+BS4wsHbXNLDNWKDrISczY7+X35ktmSkXoCTIbTYPpoPk44pikY7bUt1cCYAI4=") + string;
    }

    private static /* synthetic */ String Xqedii_2zwnxcmic0ish(String string, String string2) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("hbFONQjhgTPyJnxjXerm3pFWQoydu+8G+EMzegBMyu/h+YPFaxHQbU3Un/DtrmpLzb7cSRC4RSE=") + string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vW2jgyKWH9d1OmHJSZvO48q0/pRvyv3K4igrDtMeBFApQQ==") + string2;
    }

    private static /* synthetic */ String Xqedii_2pkhablxqui9m(String string, String string2) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("4bu5sBVsok/jNrgzZJnrnFGcUW7Sa+K4WaIdex0kPPU=") + string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("9+6OQ+rpW2Y0sQALpXZuXifW1tUzYsg5wJSvXHb5") + string2;
    }

    private static /* synthetic */ String Xqedii_3mrld3r9peffd(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("Z/dgkp0iyApEBaP2ljs6F5XEk3Gnt2JHY8tUQh0uBrENf+4L7HmnsmMTA2I+doYbgeIZXg==") + string;
    }

    private static /* synthetic */ String Xqedii_3rrusnakidpfz(String string, Integer n) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("peoMjmRIHSTFl7Fyj5+qRk+22sgw75cYUSvd4HYo") + n;
    }

    private static /* synthetic */ String Xqedii_3r7vnca00nkjb(String string, int n) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("vBPfObFDfdBwe1mwA5OC8uqb275of7o/jdZoeILoJRg=") + n;
    }

    private static /* synthetic */ String Xqedii_1uq0mhw37w1gu(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("peoMjmRIHSTFl7Fyj5+qRk+22sgw75cYUSvd4HYo") + string;
    }

    private static /* synthetic */ String Xqedii_p2f6dfrvydt2(String string) {
        return Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("peoMjmRIHSTFl7Fyj5+qRk+22sgw75cYUSvd4HYo") + string;
    }

    private static /* synthetic */ String Xqedii_37nzqy5chby1i(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("/XmaddYd6UPYBQ68EJrfX43XEzKr+/PnIeF6FPvHyNNEay2J5N4FBQbwf64=");
    }

    private static /* synthetic */ String Xqedii_2xnmlrjy640n5(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("bwRVpIs6Cl2PbfFhXyAmJpxGE9+a8WawrzFk7ZDSBp1mTj4SB3LaiGCQ");
    }

    private static /* synthetic */ String Xqedii_2nmp5zf8ge91z(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("RH4wsLcxAzHL4w7a38n8FYCLoCYLCOMWLVSPuAXkH1uiO0FK8+m/vQ==");
    }

    private static /* synthetic */ String Xqedii_3dp4h48p3aqet(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("WDhavipo8h4OO0K4RtBNW7ByiUr2aPewIFZQ2Sboai4Iz/l9yO68vkJVMD22OA==");
    }

    private static /* synthetic */ String Xqedii_1z22ep7dgwlfj(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("5ZRIL2ovXP74xDJslgE3m76hRh/j7zFOxNgriltuaZ6se0Relmxc/s0M");
    }

    private static /* synthetic */ String Xqedii_mg07sci3mpdm(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SuGXy7zvR4SIHpQ7A5gYbW/s2mVvXKvebk52EdUpzN0N43Rz3F0wYb+QWJs=");
    }

    private static /* synthetic */ String Xqedii_11v4uezsfzju8(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("9fH+gr71CiHr3IK98NlKMpnwTcgKVO4eF8y2id7BXYubU/0EhS8=");
    }

    private static /* synthetic */ String Xqedii_dm7c0bn8j520(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6Z8ToO5HXhmlZChhXH9ayAm7rSI/roq815yjgxPDg5bn8rHN8jY=");
    }

    private static /* synthetic */ String Xqedii_77n2nc3dg5wb(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("AUvef/Feb8SfhVqTwlZ2rgnUatGd9YczJFPz6vnZTWd6hPpd0Gw=");
    }

    private static /* synthetic */ String Xqedii_7883ag7pm2a(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("b0c8aq4L0diqFDaTNaikjv5firp13hfoqsik2LOBbMLcB1nRNT8=");
    }

    private static /* synthetic */ String Xqedii_t8pu4biq1qgd(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("/XmaddYd6UPYBQ68EJrfX43XEzKr+/PnIeF6FPvHyNNEay2J5N4FBQbwf64=");
    }

    private static /* synthetic */ String Xqedii_3bc1ijohhe3tr(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("bwRVpIs6Cl2PbfFhXyAmJpxGE9+a8WawrzFk7ZDSBp1mTj4SB3LaiGCQ");
    }

    private static /* synthetic */ String Xqedii_eectgynm596w(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("RH4wsLcxAzHL4w7a38n8FYCLoCYLCOMWLVSPuAXkH1uiO0FK8+m/vQ==");
    }

    private static /* synthetic */ String Xqedii_449odd3qmfeg(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("WDhavipo8h4OO0K4RtBNW7ByiUr2aPewIFZQ2Sboai4Iz/l9yO68vkJVMD22OA==");
    }

    private static /* synthetic */ String Xqedii_2wi55awp11qut(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("5ZRIL2ovXP74xDJslgE3m76hRh/j7zFOxNgriltuaZ6se0Relmxc/s0M");
    }

    private static /* synthetic */ String Xqedii_1zc5f4xc5oc4z(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SuGXy7zvR4SIHpQ7A5gYbW/s2mVvXKvebk52EdUpzN0N43Rz3F0wYb+QWJs=");
    }

    private static /* synthetic */ String Xqedii_s15mmjgiawwp(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("9fH+gr71CiHr3IK98NlKMpnwTcgKVO4eF8y2id7BXYubU/0EhS8=");
    }

    private static /* synthetic */ String Xqedii_156adwyruqsds(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6Z8ToO5HXhmlZChhXH9ayAm7rSI/roq815yjgxPDg5bn8rHN8jY=");
    }

    private static /* synthetic */ String Xqedii_jgv7q4e2al5z(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("AUvef/Feb8SfhVqTwlZ2rgnUatGd9YczJFPz6vnZTWd6hPpd0Gw=");
    }

    private static /* synthetic */ String Xqedii_cmestleknbyq(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("b0c8aq4L0diqFDaTNaikjv5firp13hfoqsik2LOBbMLcB1nRNT8=");
    }

    private static /* synthetic */ String Xqedii_qmcjl8gqouqa(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("/XmaddYd6UPYBQ68EJrfX43XEzKr+/PnIeF6FPvHyNNEay2J5N4FBQbwf64=");
    }

    private static /* synthetic */ String Xqedii_jbd5hp30vbo3(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("bwRVpIs6Cl2PbfFhXyAmJpxGE9+a8WawrzFk7ZDSBp1mTj4SB3LaiGCQ");
    }

    private static /* synthetic */ String Xqedii_of9q4yonrnpr(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("RH4wsLcxAzHL4w7a38n8FYCLoCYLCOMWLVSPuAXkH1uiO0FK8+m/vQ==");
    }

    private static /* synthetic */ String Xqedii_34wjuc6jfgo53(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("WDhavipo8h4OO0K4RtBNW7ByiUr2aPewIFZQ2Sboai4Iz/l9yO68vkJVMD22OA==");
    }

    private static /* synthetic */ String Xqedii_175nuh0hl99wo(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("5ZRIL2ovXP74xDJslgE3m76hRh/j7zFOxNgriltuaZ6se0Relmxc/s0M");
    }

    private static /* synthetic */ String Xqedii_1s487is45mrgc(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SuGXy7zvR4SIHpQ7A5gYbW/s2mVvXKvebk52EdUpzN0N43Rz3F0wYb+QWJs=");
    }

    private static /* synthetic */ String Xqedii_2xvw4tz3d3pam(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("9fH+gr71CiHr3IK98NlKMpnwTcgKVO4eF8y2id7BXYubU/0EhS8=");
    }

    private static /* synthetic */ String Xqedii_25fwvo59hblt9(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6Z8ToO5HXhmlZChhXH9ayAm7rSI/roq815yjgxPDg5bn8rHN8jY=");
    }

    private static /* synthetic */ String Xqedii_gbvuznsbj2s(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("AUvef/Feb8SfhVqTwlZ2rgnUatGd9YczJFPz6vnZTWd6hPpd0Gw=");
    }

    private static /* synthetic */ String Xqedii_2ef9s3h0t07rx(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("b0c8aq4L0diqFDaTNaikjv5firp13hfoqsik2LOBbMLcB1nRNT8=");
    }

    private static /* synthetic */ String Xqedii_4nmlouglwx1l(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("/XmaddYd6UPYBQ68EJrfX43XEzKr+/PnIeF6FPvHyNNEay2J5N4FBQbwf64=");
    }

    private static /* synthetic */ String Xqedii_1e1muuc5u1fix(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("bwRVpIs6Cl2PbfFhXyAmJpxGE9+a8WawrzFk7ZDSBp1mTj4SB3LaiGCQ");
    }

    private static /* synthetic */ String Xqedii_3hvgzin7rjv48(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("RH4wsLcxAzHL4w7a38n8FYCLoCYLCOMWLVSPuAXkH1uiO0FK8+m/vQ==");
    }

    private static /* synthetic */ String Xqedii_1t8qu557rtlsw(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("WDhavipo8h4OO0K4RtBNW7ByiUr2aPewIFZQ2Sboai4Iz/l9yO68vkJVMD22OA==");
    }

    private static /* synthetic */ String Xqedii_36qf4c1ue71sd(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("5ZRIL2ovXP74xDJslgE3m76hRh/j7zFOxNgriltuaZ6se0Relmxc/s0M");
    }

    private static /* synthetic */ String Xqedii_y4qgzgf3lwur(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("SuGXy7zvR4SIHpQ7A5gYbW/s2mVvXKvebk52EdUpzN0N43Rz3F0wYb+QWJs=");
    }

    private static /* synthetic */ String Xqedii_3aekza0b9w9or(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("9fH+gr71CiHr3IK98NlKMpnwTcgKVO4eF8y2id7BXYubU/0EhS8=");
    }

    private static /* synthetic */ String Xqedii_3r81r0081xrhk(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("6Z8ToO5HXhmlZChhXH9ayAm7rSI/roq815yjgxPDg5bn8rHN8jY=");
    }

    private static /* synthetic */ String Xqedii_1qn4hu273whrx(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("AUvef/Feb8SfhVqTwlZ2rgnUatGd9YczJFPz6vnZTWd6hPpd0Gw=");
    }

    private static /* synthetic */ String Xqedii_pzrk3gzqzqw4(String string) {
        return string + Xqedii_2ze73vg.Xqedii_2lfqnb3fhydq("b0c8aq4L0diqFDaTNaikjv5firp13hfoqsik2LOBbMLcB1nRNT8=");
    }

   public static final class ActionRowEntry {
      public static final String Xqedii_wz2oetidfb3z = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";
      public final int rawIndex;
      public final String actionType;
      public final String displayValue;
      public final boolean dragPlaceholder;

      public ActionRowEntry(int var1, String var2, String var3) {
         this(var1, var2, var3, (Xqedii_20ef81w.Xqedii_3mfa6a1bt7ytf(2080725388, 2) != 0));
      }

      public ActionRowEntry(int var1, String var2, String var3, boolean var4) {
         this.rawIndex = var1;
         this.actionType = var2 != null && !var2.isBlank()
            ? var2
            : Xqedii_20ef81w.Xqedii_1aq6ghlygecy5("+48pzu6BKymcHEIJJAVbY9g0a0EWXeRf5KjGZF5GedQwiTA2J8IHg+5S");
         this.displayValue = var3 != null && !var3.isBlank() ? var3 : Xqedii_20ef81w.Xqedii_1aq6ghlygecy5("7G8JLqGi7EEN8UP55vgHNN6R7CHFQpuNSx9Vms5yj3fIpQ==");
         this.dragPlaceholder = var4;
      }
   }
}
