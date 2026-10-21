/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
 *  net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
 *  org.bukkit.SoundCategory
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 */
package dev.xqedii.ultimateUI.service.gui.editor.shell.managers;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.Xqedii_1as73k7;
import dev.xqedii.ultimateUI.Xqedii_3ucf4be;
import dev.xqedii.ultimateUI.ChatColorUtil;
import dev.xqedii.ultimateUI.Xqedii_3uxgwyg;
import dev.xqedii.ultimateUI.service.camera.CameraService;
import dev.xqedii.ultimateUI.service.gui.editor.GuiServiceEditorSidebarSupport;
import dev.xqedii.ultimateUI.service.gui.model.EditorRect;
import dev.xqedii.ultimateUI.service.gui.model.EditorSavePopupField;
import dev.xqedii.ultimateUI.service.gui.model.EditorSession;
import dev.xqedii.ultimateUI.service.gui.model.EditorTool;
import dev.xqedii.ultimateUI.service.hud.HudService;
import java.lang.invoke.MethodHandle;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.SoundCategory;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class EditorNavbarHistoryPart
extends GuiServiceEditorSidebarSupport {
    public static final String Xqedii_154ii4ujz0j4i = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";
    public static final String Xqedii_1q2tuvi;
    public static final String Xqedii_3qmm41n;
    public static final String Xqedii_3spf4li;
    public static final String Xqedii_3i4ovcs;
    public static final String Xqedii_1wzaauo;
    public static final String Xqedii_35ze0h3;
    public static final String Xqedii_2tuok81;
    public static final String Xqedii_105z1dg;
    public static final String Xqedii_201v320;
    public static final Map<String, String> Xqedii_19udyqn;

    public EditorNavbarHistoryPart(UltimateUI ultimateUI, HudService hudService, CameraService cameraService) {
        super(ultimateUI, hudService, cameraService);
    }

    public abstract boolean handleEditorFileDropdownAction(Player var1, EditorSession var2, String var3);

    public abstract boolean handleEditorEditDropdownAction(Player var1, EditorSession var2, String var3);

    public abstract boolean handleSelectionDropdownAction(Player var1, EditorSession var2, String var3);

    public abstract boolean handleEditorLayerDropdownAction(Player var1, EditorSession var2, String var3);

    public abstract boolean handleEditorWindowDropdownAction(Player var1, EditorSession var2, String var3);

    public abstract void closeTimelineContextDropdowns(Player var1, EditorSession var2);

    public abstract boolean isInsideShellBlock(EditorSession var1, String var2, double var3, double var5);

    public abstract void beginShellOpacityBatch(EditorSession var1);

    public abstract void endShellOpacityBatch(Player var1, EditorSession var2);

    public abstract void setSidebarPanelVisible(Player var1, EditorSession var2, String var3, boolean var4);

    public abstract void setShellText(Player var1, String var2, String var3);

    public abstract void setShellColor(Player var1, EditorSession var2, String var3, String var4);

    public abstract EditorRect findShellBlockRect(EditorSession var1, String var2);

    public boolean onEditorNavbarUndoClick(Player player, EditorSession editorSession) {
        if ((472687500 * 472687500 - 472687500 & 1) != 0) {
            throw new IllegalStateException();
        }
        return Xqedii_1as73k7.Xqedii_1nc45iibavbym(753016810, 30) != 0;
    }

    public boolean onEditorNavbarRedoClick(Player player, EditorSession editorSession) {
        if ((86631370 * (86631370 + 1) & 1) != 0) {
            throw new IllegalStateException();
        }
        return Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(1610953428, 20) != 0;
    }

    public boolean onEditorNavbarHomeClick(Player player, EditorSession editorSession) {
        if ((153975065 * 153975065 - 153975065 & 1) != 0) {
            throw new IllegalStateException();
        }
        return Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(1286936577, 2) != 0;
    }

    public boolean onEditorNavbarExportClick(Player player, EditorSession editorSession) {
        if ((0x771B3838 ^ 0x771B3838) != 0) {
            throw new IllegalStateException();
        }
        return Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 10) != 0;
    }

    public boolean canEditorNavbarUndo(EditorSession editorSession) {
        if (((0x41ECBC8F | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        return Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 2) != 0;
    }

    public boolean canEditorNavbarRedo(EditorSession editorSession) {
        if ((1903247984 * (1903247984 + 1) & 1) != 0) {
            throw new IllegalStateException();
        }
        return Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 25) != 0;
    }

    public void updateEditorNavbarHistoryIcons(Player player, EditorSession editorSession) {
        if (((0x1791E6C9 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null) {
            return;
        }
        boolean bl = this.canEditorNavbarRedo(editorSession);
        boolean bl2 = this.canEditorNavbarUndo(editorSession);
        this.setShellText(player, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("SL97ToXavbYQYeaoPm8uRDhsgww2vx3QYvHsz/Doz9nN3AC1IWKQ5EMKXUPLyw6m6YvO1qoypRWZRJq9RG2Jj4v0dX39Ns7i8hY="), bl ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("UwXC9JFNJVMExgL1HcUluJFY/JS7LZQXLKiHtgqt") : Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("gVt68F6X5k5tvQWTI+ujSA6+svqKwJmDgpi2MGGY"));
        this.setShellText(player, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("eBIAUjrV77a9kxURa7OQS1m9oLbeI2Dl2WZA9S3ZencSqjFA5Y1iUQRaC2zLB4Cj7oNhICYWVzZDelubS/HO7F+jFhVsJNsvUaY="), bl2 ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("anH+oNaWIQlVkEEj6XRCA6zWXg+FgpD1btTthxFN") : Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("X0fQRh0y0ZR4LvvrQY0PyTuw05uFocFzhWdZd3/D"));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setFileDropdownVisible(Player player, EditorSession editorSession, boolean bl) {
        if ((1840870655 * 1840870655 - 1840870655 & 1) != 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null) {
            return;
        }
        if (bl) {
            this.setEditDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 9) != 0);
            this.setSelectionDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 16) != 0);
            this.setLayerDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 27) != 0);
            this.setWindowDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(948435595, 4) != 0);
        }
        if (editorSession.fileDropdownVisible == bl) {
            return;
        }
        editorSession.fileDropdownVisible = bl;
        if (!bl) {
            editorSession.fileDropdownHoverTargetId = null;
        } else {
            this.playEditorSfx(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("WVAzjoufHZNpZN4zI+HZNrfvCyfsuQiJ4k75RXiP3ksUbI/kEg3P1ncJN2HFQZgApTNyzrOKw4AMnQ=="));
        }
        this.beginShellOpacityBatch(editorSession);
        try {
            this.setSidebarPanelVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("jwrEvjZOvImbU9Tr+ctJy54TV3AwvAH9XSrR+7Gr1x65C+6J9RSbfIqlavmJIuOU+cuhdnlt"), bl);
        }
        finally {
            this.endShellOpacityBatch(player, editorSession);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setEditDropdownVisible(Player player, EditorSession editorSession, boolean bl) {
        if ((-1253403653 * -1253403653 + -1253403653 & 1) != 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null) {
            return;
        }
        if (bl) {
            this.setFileDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 25) != 0);
            this.setSelectionDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 20) != 0);
            this.setLayerDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(43608624, 27) != 0);
            this.setWindowDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 1) != 0);
        }
        if (editorSession.editDropdownVisible == bl) {
            return;
        }
        editorSession.editDropdownVisible = bl;
        if (!bl) {
            editorSession.editDropdownHoverTargetId = null;
        } else {
            this.playEditorSfx(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("WVAzjoufHZNpZN4zI+HZNrfvCyfsuQiJ4k75RXiP3ksUbI/kEg3P1ncJN2HFQZgApTNyzrOKw4AMnQ=="));
        }
        this.beginShellOpacityBatch(editorSession);
        try {
            this.setSidebarPanelVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("af8Z6Scq+zPq/cDdBBLA4Mrma80q4a45TJzLJFRGxypWjqwLXUbuN3vh5g+Iiouhx1Dj2DGn"), bl);
        }
        finally {
            this.endShellOpacityBatch(player, editorSession);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setSelectionDropdownVisible(Player player, EditorSession editorSession, boolean bl) {
        if ((-2137169948 * (-2137169948 + 1) & 1) != 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null) {
            return;
        }
        if (bl) {
            this.setFileDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 14) != 0);
            this.setEditDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(-91539321, 20) != 0);
            this.setLayerDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 3) != 0);
            this.setWindowDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 10) != 0);
        }
        if (editorSession.selectionDropdownVisible == bl) {
            return;
        }
        editorSession.selectionDropdownVisible = bl;
        if (!bl) {
            editorSession.selectionDropdownHoverTargetId = null;
        } else {
            this.playEditorSfx(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("WVAzjoufHZNpZN4zI+HZNrfvCyfsuQiJ4k75RXiP3ksUbI/kEg3P1ncJN2HFQZgApTNyzrOKw4AMnQ=="));
        }
        this.beginShellOpacityBatch(editorSession);
        try {
            this.setSidebarPanelVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("vckJLHrr2/n+ADObIRv9ax9a5PpZcoN08Brp0oRst1Taj1y4hV6a/KAGFs/Uvu6ouLZl9vLw4C2kfLsZDVhjzw=="), bl);
        }
        finally {
            this.endShellOpacityBatch(player, editorSession);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setLayerDropdownVisible(Player player, EditorSession editorSession, boolean bl) {
        if (((0x46F74043 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null) {
            return;
        }
        if (bl) {
            this.setFileDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(-1959229446, 28) != 0);
            this.setEditDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 26) != 0);
            this.setSelectionDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 20) != 0);
            this.setWindowDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 10) != 0);
        }
        if (editorSession.layerDropdownVisible == bl) {
            return;
        }
        editorSession.layerDropdownVisible = bl;
        if (!bl) {
            editorSession.layerDropdownHoverTargetId = null;
        } else {
            this.playEditorSfx(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("WVAzjoufHZNpZN4zI+HZNrfvCyfsuQiJ4k75RXiP3ksUbI/kEg3P1ncJN2HFQZgApTNyzrOKw4AMnQ=="));
        }
        this.beginShellOpacityBatch(editorSession);
        try {
            this.setSidebarPanelVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Xl3qjXMR1+orvu8k2fUNyLYNq+eMeVhn6gNwjiEpUnX+hLHhEa8RVNP0mppJObhrgqXALt0UdHE="), bl);
        }
        finally {
            this.endShellOpacityBatch(player, editorSession);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setWindowDropdownVisible(Player player, EditorSession editorSession, boolean bl) {
        if ((0x3084275D ^ 0x3084275D) != 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null) {
            return;
        }
        if (bl) {
            this.setFileDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 17) != 0);
            this.setEditDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 2) != 0);
            this.setSelectionDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(10902156, 25) != 0);
            this.setLayerDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(348868992, 30) != 0);
        }
        if (editorSession.windowDropdownVisible == bl) {
            return;
        }
        editorSession.windowDropdownVisible = bl;
        if (!bl) {
            editorSession.windowDropdownHoverTargetId = null;
        } else {
            this.playEditorSfx(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("WVAzjoufHZNpZN4zI+HZNrfvCyfsuQiJ4k75RXiP3ksUbI/kEg3P1ncJN2HFQZgApTNyzrOKw4AMnQ=="));
        }
        this.beginShellOpacityBatch(editorSession);
        try {
            this.setSidebarPanelVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("XfkePmFa44bPo4aRVDxKDhDBRlPe+ihGRoEAIfm7GiZQeME1AxUZPtG2q04/6KX+L1nFdaALBBFHqw=="), bl);
        }
        finally {
            this.endShellOpacityBatch(player, editorSession);
        }
    }

    public String resolveHoveredFileDropdownTargetId(EditorSession editorSession, double d, double d2) {
        if (((2070171262 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null || !editorSession.fileDropdownVisible) {
            return null;
        }
        double d3 = editorSession.shellHitX(d);
        double d4 = editorSession.shellHitY(d2);
        int n = Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 25);
        for (String string : Xqedii_3ucf4be.Xqedii_261585u) {
            String string2 = EditorNavbarHistoryPart.Xqedii_2sij5hvl2g9da(string);
            EditorRect editorRect = this.findShellBlockRect(editorSession, string2);
            if (editorRect == null) continue;
            ++n;
            if (!editorRect.contains(d3, d4)) continue;
            return string;
        }
        if (n == Xqedii_3ucf4be.Xqedii_261585u.size()) {
            return null;
        }
        EditorRect editorRect = this.findShellBlockRect(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("jwrEvjZOvImbU9Tr+ctJy54TV3AwvAH9XSrR+7Gr1x65C+6J9RSbfIqlavmJIuOU+cuhdnlt"));
        if (editorRect != null && editorRect.contains(d3, d4)) {
            double d5 = Double.longBitsToDouble(Xqedii_1as73k7.Xqedii_2z8r9m5d6urt6(-3401091360407359920L, 5));
            int n2 = (int)Math.floor((d4 - editorRect.y) / Double.longBitsToDouble(Xqedii_1as73k7.Xqedii_2z8r9m5d6urt6(3256917738706497095L, 59)));
            if (n2 >= 0 && n2 < Xqedii_3ucf4be.Xqedii_261585u.size()) {
                return Xqedii_3ucf4be.Xqedii_261585u.get(n2);
            }
        }
        for (String string2 : Xqedii_3ucf4be.Xqedii_261585u) {
            if (!this.isInsideShellBlock(editorSession, string2, d3, d4)) continue;
            return string2;
        }
        return null;
    }

    public String resolveHoveredEditDropdownTargetId(EditorSession editorSession, double d, double d2) {
        if ((1105195403 * 1105195403 + 1105195403 & 1) != 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null || !editorSession.editDropdownVisible) {
            return null;
        }
        double d3 = editorSession.shellHitX(d);
        double d4 = editorSession.shellHitY(d2);
        int n = Xqedii_1as73k7.Xqedii_1nc45iibavbym(-501224916, 6);
        for (String string : Xqedii_3ucf4be.Xqedii_2j5tsfo) {
            String string2 = EditorNavbarHistoryPart.Xqedii_5s7jjrwr5lhw(string);
            EditorRect editorRect = this.findShellBlockRect(editorSession, string2);
            if (editorRect == null) continue;
            ++n;
            if (!editorRect.contains(d3, d4)) continue;
            return string;
        }
        if (n == Xqedii_3ucf4be.Xqedii_2j5tsfo.size()) {
            return null;
        }
        EditorRect editorRect = this.findShellBlockRect(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("af8Z6Scq+zPq/cDdBBLA4Mrma80q4a45TJzLJFRGxypWjqwLXUbuN3vh5g+Iiouhx1Dj2DGn"));
        if (editorRect != null && editorRect.contains(d3, d4)) {
            double d5 = Double.longBitsToDouble(Xqedii_1as73k7.Xqedii_j39opfilg3vt(2910351671909366003L, 46));
            int n2 = (int)Math.floor((d4 - editorRect.y) / Double.longBitsToDouble(Xqedii_1as73k7.Xqedii_j39opfilg3vt(2910369332814883043L, 14)));
            if (n2 >= 0 && n2 < Xqedii_3ucf4be.Xqedii_2j5tsfo.size()) {
                return Xqedii_3ucf4be.Xqedii_2j5tsfo.get(n2);
            }
        }
        for (String string2 : Xqedii_3ucf4be.Xqedii_2j5tsfo) {
            if (!this.isInsideShellBlock(editorSession, string2, d3, d4)) continue;
            return string2;
        }
        return null;
    }

    public String resolveHoveredSelectionDropdownTargetId(EditorSession editorSession, double d, double d2) {
        if ((0xACCBB259 ^ 0xACCBB259) != 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null || !editorSession.selectionDropdownVisible) {
            return null;
        }
        double d3 = editorSession.shellHitX(d);
        double d4 = editorSession.shellHitY(d2);
        int n = Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(852779013, 4);
        for (String string : Xqedii_3ucf4be.Xqedii_2a33z0z) {
            String string2 = EditorNavbarHistoryPart.Xqedii_xei8k9ia2xpk(string);
            EditorRect editorRect = this.findShellBlockRect(editorSession, string2);
            if (editorRect == null) continue;
            ++n;
            if (!editorRect.contains(d3, d4)) continue;
            return string;
        }
        if (n == Xqedii_3ucf4be.Xqedii_2a33z0z.size()) {
            return null;
        }
        EditorRect editorRect = this.findShellBlockRect(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("vckJLHrr2/n+ADObIRv9ax9a5PpZcoN08Brp0oRst1Taj1y4hV6a/KAGFs/Uvu6ouLZl9vLw4C2kfLsZDVhjzw=="));
        if (editorRect != null && editorRect.contains(d3, d4)) {
            double d5 = Double.longBitsToDouble(Xqedii_1as73k7.Xqedii_j39opfilg3vt(2910369332814887410L, 10));
            int n2 = (int)Math.floor((d4 - editorRect.y) / Double.longBitsToDouble(Xqedii_1as73k7.Xqedii_j39opfilg3vt(2910369059010722035L, 40)));
            if (n2 >= 0 && n2 < Xqedii_3ucf4be.Xqedii_2a33z0z.size()) {
                return Xqedii_3ucf4be.Xqedii_2a33z0z.get(n2);
            }
        }
        for (String string2 : Xqedii_3ucf4be.Xqedii_2a33z0z) {
            if (!this.isInsideShellBlock(editorSession, string2, d3, d4)) continue;
            return string2;
        }
        return null;
    }

    public String resolveHoveredLayerDropdownTargetId(EditorSession editorSession, double d, double d2) {
        if ((653608087 * (653608087 + 1) & 1) != 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null || !editorSession.layerDropdownVisible) {
            return null;
        }
        double d3 = editorSession.shellHitX(d);
        double d4 = editorSession.shellHitY(d2);
        int n = Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(-1721094142, 3);
        for (String string : Xqedii_3ucf4be.Xqedii_fj0e6zy) {
            String string2 = EditorNavbarHistoryPart.Xqedii_3uhbauq0hxlx5(string);
            EditorRect editorRect = this.findShellBlockRect(editorSession, string2);
            if (editorRect == null) continue;
            ++n;
            if (!editorRect.contains(d3, d4)) continue;
            return string;
        }
        if (n == Xqedii_3ucf4be.Xqedii_fj0e6zy.size()) {
            return null;
        }
        EditorRect editorRect = this.findShellBlockRect(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Xl3qjXMR1+orvu8k2fUNyLYNq+eMeVhn6gNwjiEpUnX+hLHhEa8RVNP0mppJObhrgqXALt0UdHE="));
        if (editorRect != null && editorRect.contains(d3, d4)) {
            double d5 = Double.longBitsToDouble(Xqedii_1as73k7.Xqedii_2sf44gybgk4ii(-426644349312653052L, 42));
            int n2 = (int)Math.floor((d4 - editorRect.y) / Double.longBitsToDouble(Xqedii_1as73k7.Xqedii_j39opfilg3vt(2910369332814885115L, 13)));
            if (n2 >= 0 && n2 < Xqedii_3ucf4be.Xqedii_fj0e6zy.size()) {
                return Xqedii_3ucf4be.Xqedii_fj0e6zy.get(n2);
            }
        }
        for (String string2 : Xqedii_3ucf4be.Xqedii_fj0e6zy) {
            if (!this.isInsideShellBlock(editorSession, string2, d3, d4)) continue;
            return string2;
        }
        return null;
    }

    public String resolveHoveredWindowDropdownTargetId(EditorSession editorSession, double d, double d2) {
        if ((806640971 * 806640971 + 806640971 & 1) != 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null || !editorSession.windowDropdownVisible) {
            return null;
        }
        double d3 = editorSession.shellHitX(d);
        double d4 = editorSession.shellHitY(d2);
        int n = Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 12);
        for (String string : Xqedii_3ucf4be.Xqedii_32x1dg9) {
            String string2 = EditorNavbarHistoryPart.Xqedii_1jobhs3gm6vt9(string);
            EditorRect editorRect = this.findShellBlockRect(editorSession, string2);
            if (editorRect == null) continue;
            ++n;
            if (!editorRect.contains(d3, d4)) continue;
            return string;
        }
        if (n == Xqedii_3ucf4be.Xqedii_32x1dg9.size()) {
            return null;
        }
        EditorRect editorRect = this.findShellBlockRect(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("XfkePmFa44bPo4aRVDxKDhDBRlPe+ihGRoEAIfm7GiZQeME1AxUZPtG2q04/6KX+L1nFdaALBBFHqw=="));
        if (editorRect != null && editorRect.contains(d3, d4)) {
            double d5 = Double.longBitsToDouble(Xqedii_1as73k7.Xqedii_2z8r9m5d6urt6(2939976894025887303L, 35));
            int n2 = (int)Math.floor((d4 - editorRect.y) / Double.longBitsToDouble(Xqedii_1as73k7.Xqedii_j39opfilg3vt(2910369884718184691L, 41)));
            if (n2 >= 0 && n2 < Xqedii_3ucf4be.Xqedii_32x1dg9.size()) {
                return Xqedii_3ucf4be.Xqedii_32x1dg9.get(n2);
            }
        }
        for (String string2 : Xqedii_3ucf4be.Xqedii_32x1dg9) {
            if (!this.isInsideShellBlock(editorSession, string2, d3, d4)) continue;
            return string2;
        }
        return null;
    }

    public void setFileDropdownItemVisual(Player player, EditorSession editorSession, String string, boolean bl) {
        if (((0x43FFAD3F | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null || string == null || string.isBlank()) {
            return;
        }
        String[] stringArray = Xqedii_3ucf4be.Xqedii_3m6d5la.get(string);
        if (stringArray != null && stringArray.length >= Xqedii_1as73k7.Xqedii_1nc45iibavbym(1436304381, 27)) {
            this.setShellText(player, EditorNavbarHistoryPart.Xqedii_1qf3dtzmkds82(string), bl ? stringArray[Xqedii_1as73k7.Xqedii_1nc45iibavbym(-1690793990, 28)] : stringArray[Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 4)]);
        }
        this.setShellColor(player, editorSession, string, bl ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("3m2heVxfw6CCKN5RN4k3adxHNAWLi1ONLVWDuzWhNMXDZByzQ2WSEw==") : Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Ykl5ym1ODCpcjCwZezK0Fa+Q3cB5xABTyT75ZWGaGj+Hm4IAYQUUdg=="));
    }

    public void setEditDropdownItemVisual(Player player, EditorSession editorSession, String string, boolean bl) {
        if (((1304001475 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null || string == null || string.isBlank()) {
            return;
        }
        String[] stringArray = Xqedii_3ucf4be.Xqedii_1hhfrsi.get(string);
        if (stringArray != null && stringArray.length >= Xqedii_1as73k7.Xqedii_1nc45iibavbym(1506033621, 31)) {
            this.setShellText(player, EditorNavbarHistoryPart.Xqedii_1w4wl371eocp8(string), bl ? stringArray[Xqedii_1as73k7.Xqedii_6s96e0m7981d(769534788, 29)] : stringArray[Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(1395475968, 0)]);
        }
        this.setShellColor(player, editorSession, string, bl ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("3m2heVxfw6CCKN5RN4k3adxHNAWLi1ONLVWDuzWhNMXDZByzQ2WSEw==") : Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Ykl5ym1ODCpcjCwZezK0Fa+Q3cB5xABTyT75ZWGaGj+Hm4IAYQUUdg=="));
    }

    public void setSelectionDropdownItemVisual(Player player, EditorSession editorSession, String string, boolean bl) {
        if ((0xE4E857B1 ^ 0xE4E857B1) != 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null || string == null || string.isBlank()) {
            return;
        }
        String[] stringArray = Xqedii_3ucf4be.Xqedii_2eux9uv.get(string);
        if (stringArray != null && stringArray.length >= Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(19502755, 23)) {
            this.setShellText(player, EditorNavbarHistoryPart.Xqedii_1ojx8qsaenjvb(string), bl ? stringArray[Xqedii_1as73k7.Xqedii_1nc45iibavbym(948435611, 4)] : stringArray[Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 12)]);
        }
        this.setShellColor(player, editorSession, string, bl ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("3m2heVxfw6CCKN5RN4k3adxHNAWLi1ONLVWDuzWhNMXDZByzQ2WSEw==") : Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Ykl5ym1ODCpcjCwZezK0Fa+Q3cB5xABTyT75ZWGaGj+Hm4IAYQUUdg=="));
    }

    public void setLayerDropdownItemVisual(Player player, EditorSession editorSession, String string, boolean bl) {
        if (((0xB16BD69E | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null || string == null || string.isBlank()) {
            return;
        }
        String[] stringArray = Xqedii_3ucf4be.Xqedii_3a8xa71.get(string);
        if (stringArray != null && stringArray.length >= Xqedii_1as73k7.Xqedii_1nc45iibavbym(-1673265835, 3)) {
            this.setShellText(player, EditorNavbarHistoryPart.Xqedii_bktucmivshct(string), bl ? stringArray[Xqedii_1as73k7.Xqedii_1loegwk964oc9(349318260, 17)] : stringArray[Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 5)]);
        }
        this.setShellColor(player, editorSession, string, bl ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("3m2heVxfw6CCKN5RN4k3adxHNAWLi1ONLVWDuzWhNMXDZByzQ2WSEw==") : Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Ykl5ym1ODCpcjCwZezK0Fa+Q3cB5xABTyT75ZWGaGj+Hm4IAYQUUdg=="));
    }

    public void setWindowDropdownItemVisual(Player player, EditorSession editorSession, String string, boolean bl) {
        if ((0x71F58E97 ^ 0x71F58E97) != 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null || string == null || string.isBlank()) {
            return;
        }
        String[] stringArray = Xqedii_3ucf4be.Xqedii_1ftn8ic.get(string);
        if (stringArray != null && stringArray.length >= Xqedii_1as73k7.Xqedii_1nc45iibavbym(1436304381, 27)) {
            this.setShellText(player, EditorNavbarHistoryPart.Xqedii_1b0prt5chdndx(string), bl ? stringArray[Xqedii_1as73k7.Xqedii_1nc45iibavbym(-1282900055, 0)] : stringArray[Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(759562323, 8)]);
        }
        this.setShellColor(player, editorSession, string, bl ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("3m2heVxfw6CCKN5RN4k3adxHNAWLi1ONLVWDuzWhNMXDZByzQ2WSEw==") : Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Ykl5ym1ODCpcjCwZezK0Fa+Q3cB5xABTyT75ZWGaGj+Hm4IAYQUUdg=="));
    }

    @Override
    public boolean equalsNullable(String string, String string2) {
        if ((-470201002 * -470201002 - -470201002 & 1) != 0) {
            throw new IllegalStateException();
        }
        return Objects.equals(string, string2);
    }

    public boolean handleEditorNavbarClick(Player player, EditorSession editorSession, double d, double d2) {
        int n;
        if (((1203815898 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null || !editorSession.editMode || !editorSession.previewMode) {
            return Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 8) != 0;
        }
        if (editorSession.keyframeDropdownVisible || editorSession.keyframeTimelineDropdownVisible) {
            int n2;
            n = this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("U62bJ3vtJqvjZfyoq3A7/w99IWnQy4cyK6PO8Pr2mTliIyfexiDqrlPnkjaoTU5dzxRFnGO8DYEN0VkrpvGyYQ=="), d, d2) ? 1 : 0;
            int n3 = n2 = this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("ywR9dVypsC92cXl6YvFN8Zep/VST4WtJftT+3s8FZ/Lxo+c2rIy6I0M/siWPafbKnALwJToUfhLAY311Ms0="), d, d2) || this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("V+ADPBcuymvsKXAVro5gI1kXvzjxsDkuWhJRgxhyDAnTP4YsdhpjJXC+ZAvcR/M+RHGCygMlkfCPEcbBDrRxsC8FFuaihVdKCAyfIlDJc0I="), d, d2) || this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("ywR9dVypsC92cXl6YvFN8Zep/VST4WtJftT+3s8FZ/Lxo+c2rIy6I0M/siWPafbKnALwJToUfhLAY311Ms0="), editorSession.shellHitX(d), editorSession.shellHitY(d2)) || this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("V+ADPBcuymvsKXAVro5gI1kXvzjxsDkuWhJRgxhyDAnTP4YsdhpjJXC+ZAvcR/M+RHGCygMlkfCPEcbBDrRxsC8FFuaihVdKCAyfIlDJc0I="), editorSession.shellHitX(d), editorSession.shellHitY(d2)) ? Xqedii_1as73k7.Xqedii_6s96e0m7981d(769534788, 28) : Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(759562323, 8);
            if (n == 0 && n2 == 0) {
                this.closeTimelineContextDropdowns(player, editorSession);
            }
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("B3tJKY31G7mhSU3lehYrupZi6VnbfS1jCPFXANflCrpJ/LdSkZncJeHkEnKc80/FZL2ALB45reiBtkVEDDD//Q=="), d, d2)) {
            this.setFileDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 17) != 0);
            this.setEditDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 17) != 0);
            this.setSelectionDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(10902156, 25) != 0);
            this.setLayerDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 29) != 0);
            this.setWindowDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 25) != 0);
            this.updateEditorNavbarHover(player, editorSession, d, d2);
            n = this.onEditorNavbarUndoClick(player, editorSession) ? 1 : 0;
            EditorSession editorSession2 = (EditorSession)this.editorSessions.get(player.getUniqueId());
            if (editorSession2 == editorSession) {
                this.updateEditorNavbarHover(player, editorSession, d, d2);
            }
            return n != 0;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("qt01QtfLU1ZuRV/PS7Y+ETVQ1rfNQe6SXgHIqge7olEnqubgM8gfXyo9LGqYZX5GElBqWR/J9/taliU1qkFaCg=="), d, d2)) {
            this.setFileDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 25) != 0);
            this.setEditDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 0) != 0);
            this.setSelectionDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(285167975, 9) != 0);
            this.setLayerDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(805476714, 19) != 0);
            this.setWindowDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 5) != 0);
            this.updateEditorNavbarHover(player, editorSession, d, d2);
            n = this.onEditorNavbarRedoClick(player, editorSession) ? 1 : 0;
            EditorSession editorSession3 = (EditorSession)this.editorSessions.get(player.getUniqueId());
            if (editorSession3 == editorSession) {
                this.updateEditorNavbarHover(player, editorSession, d, d2);
            }
            return n != 0;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("XZ7syz/zTVXuqRB8ftVZ+A9kROjfa53QeePoo2gm4li8VZnSYV8izjRC08OLRM3s3g2to5bnqCss5T5vc2le0Q=="), d, d2)) {
            this.setFileDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(1781531289, 11) != 0);
            this.setEditDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 19) != 0);
            this.setSelectionDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(1070881220, 15) != 0);
            this.setLayerDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 3) != 0);
            this.setWindowDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(-1767702487, 7) != 0);
            this.updateEditorNavbarHover(player, editorSession, d, d2);
            return this.onEditorNavbarHomeClick(player, editorSession);
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("zmi7D/6mL4ewl3lefx/uVmt7PDi7pK8JBfiwUat9fBZFKUBTI2rA7kigCbTN9EjiloJT9tnMFicBWUY+E4de+/Cpq8g="), d, d2)) {
            this.setFileDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(-883851244, 6) != 0);
            this.setEditDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(267720305, 13) != 0);
            this.setSelectionDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(948435595, 4) != 0);
            this.setLayerDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 21) != 0);
            this.setWindowDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 0) != 0);
            this.updateEditorNavbarHover(player, editorSession, d, d2);
            return this.onEditorNavbarExportClick(player, editorSession);
        }
        if (editorSession.fileDropdownVisible) {
            String string = this.resolveHoveredFileDropdownTargetId(editorSession, d, d2);
            if (string != null) {
                this.setFileDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(-1256718004, 10) != 0);
                this.playEditorSfx(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Zf8iAjAn/7fQEvgNenaVRt0P9536N4AbUCRYSnooDS7c0rXhx2pscQoohoCGtOqrGYcJ37Ao0hGiQYVsjCr8PU6uyc8ajg=="));
                this.handleEditorFileDropdownAction(player, editorSession, string);
                EditorSession editorSession4 = (EditorSession)this.editorSessions.get(player.getUniqueId());
                if (editorSession4 == editorSession) {
                    this.updateEditorNavbarHover(player, editorSession, d, d2);
                }
                return Xqedii_1as73k7.Xqedii_1nc45iibavbym(1070913988, 15) != 0;
            }
            if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("jwrEvjZOvImbU9Tr+ctJy54TV3AwvAH9XSrR+7Gr1x65C+6J9RSbfIqlavmJIuOU+cuhdnlt"), d, d2)) {
                return Xqedii_1as73k7.Xqedii_1nc45iibavbym(1140673948, 11) != 0;
            }
        }
        if (editorSession.editDropdownVisible) {
            String string = this.resolveHoveredEditDropdownTargetId(editorSession, d, d2);
            if (string != null) {
                this.setEditDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(267720305, 13) != 0);
                this.playEditorSfx(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Zf8iAjAn/7fQEvgNenaVRt0P9536N4AbUCRYSnooDS7c0rXhx2pscQoohoCGtOqrGYcJ37Ao0hGiQYVsjCr8PU6uyc8ajg=="));
                this.handleEditorEditDropdownAction(player, editorSession, string);
                EditorSession editorSession5 = (EditorSession)this.editorSessions.get(player.getUniqueId());
                if (editorSession5 == editorSession) {
                    this.updateEditorNavbarHover(player, editorSession, d, d2);
                }
                return Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(-1945983398, 17) != 0;
            }
            if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("af8Z6Scq+zPq/cDdBBLA4Mrma80q4a45TJzLJFRGxypWjqwLXUbuN3vh5g+Iiouhx1Dj2DGn"), d, d2)) {
                return Xqedii_1as73k7.Xqedii_1nc45iibavbym(-1690793990, 28) != 0;
            }
        }
        if (editorSession.selectionDropdownVisible) {
            String string = this.resolveHoveredSelectionDropdownTargetId(editorSession, d, d2);
            if (string != null) {
                this.setSelectionDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 30) != 0);
                this.playEditorSfx(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Zf8iAjAn/7fQEvgNenaVRt0P9536N4AbUCRYSnooDS7c0rXhx2pscQoohoCGtOqrGYcJ37Ao0hGiQYVsjCr8PU6uyc8ajg=="));
                this.handleSelectionDropdownAction(player, editorSession, string);
                EditorSession editorSession6 = (EditorSession)this.editorSessions.get(player.getUniqueId());
                if (editorSession6 == editorSession) {
                    this.updateEditorNavbarHover(player, editorSession, d, d2);
                }
                return Xqedii_1as73k7.Xqedii_6s96e0m7981d(769534788, 8) != 0;
            }
            if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("vckJLHrr2/n+ADObIRv9ax9a5PpZcoN08Brp0oRst1Taj1y4hV6a/KAGFs/Uvu6ouLZl9vLw4C2kfLsZDVhjzw=="), d, d2)) {
                return Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(852779029, 4) != 0;
            }
        }
        if (editorSession.layerDropdownVisible) {
            String string = this.resolveHoveredLayerDropdownTargetId(editorSession, d, d2);
            if (string != null) {
                this.setLayerDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(87217248, 28) != 0);
                this.playEditorSfx(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Zf8iAjAn/7fQEvgNenaVRt0P9536N4AbUCRYSnooDS7c0rXhx2pscQoohoCGtOqrGYcJ37Ao0hGiQYVsjCr8PU6uyc8ajg=="));
                this.handleEditorLayerDropdownAction(player, editorSession, string);
                EditorSession editorSession7 = (EditorSession)this.editorSessions.get(player.getUniqueId());
                if (editorSession7 == editorSession) {
                    this.updateEditorNavbarHover(player, editorSession, d, d2);
                }
                return Xqedii_1as73k7.Xqedii_1nc45iibavbym(285168487, 9) != 0;
            }
            if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Xl3qjXMR1+orvu8k2fUNyLYNq+eMeVhn6gNwjiEpUnX+hLHhEa8RVNP0mppJObhrgqXALt0UdHE="), d, d2)) {
                return Xqedii_1as73k7.Xqedii_6s96e0m7981d(769534788, 2) != 0;
            }
        }
        if (editorSession.windowDropdownVisible) {
            String string = this.resolveHoveredWindowDropdownTargetId(editorSession, d, d2);
            if (string != null) {
                this.setWindowDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(348868992, 30) != 0);
                this.playEditorSfx(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Zf8iAjAn/7fQEvgNenaVRt0P9536N4AbUCRYSnooDS7c0rXhx2pscQoohoCGtOqrGYcJ37Ao0hGiQYVsjCr8PU6uyc8ajg=="));
                this.handleEditorWindowDropdownAction(player, editorSession, string);
                EditorSession editorSession8 = (EditorSession)this.editorSessions.get(player.getUniqueId());
                if (editorSession8 == editorSession) {
                    this.updateEditorNavbarHover(player, editorSession, d, d2);
                }
                return Xqedii_1as73k7.Xqedii_1nc45iibavbym(-641450028, 31) != 0;
            }
            if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("XfkePmFa44bPo4aRVDxKDhDBRlPe+ihGRoEAIfm7GiZQeME1AxUZPtG2q04/6KX+L1nFdaALBBFHqw=="), d, d2)) {
                return Xqedii_1as73k7.Xqedii_1loegwk964oc9(344992884, 22) != 0;
            }
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Cu6jj2PmM4ziPjnD3KsR/rNp+ZK6JKRAIePAYbYw0GPsbEvOhyKUnem7Vn0ZsrexzK8="), d, d2)) {
            n = !editorSession.fileDropdownVisible ? Xqedii_1as73k7.Xqedii_6s96e0m7981d(769534788, 0) : Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 2);
            this.setEditDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(1174426413, 16) != 0);
            this.setSelectionDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 14) != 0);
            this.setLayerDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(267720305, 13) != 0);
            this.setWindowDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 2) != 0);
            this.setFileDropdownVisible(player, editorSession, n != 0);
            this.updateEditorNavbarHover(player, editorSession, d, d2);
            return Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187196, 3) != 0;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("k2AL1pKAo6ONYKajesg6+vA5nbNtvesGmqi/ZyFM18zZiaF8y1rU1CRUw2FXriAf4p0="), d, d2)) {
            n = !editorSession.editDropdownVisible ? Xqedii_1as73k7.Xqedii_1loegwk964oc9(340798580, 23) : Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 25);
            this.setFileDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(-1282900056, 0) != 0);
            this.setSelectionDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 2) != 0);
            this.setLayerDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(402738357, 18) != 0);
            this.setWindowDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(1140671900, 11) != 0);
            this.setEditDropdownVisible(player, editorSession, n != 0);
            this.updateEditorNavbarHover(player, editorSession, d, d2);
            return Xqedii_1as73k7.Xqedii_6s96e0m7981d(769534788, 21) != 0;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("tJkqTpq8blCABd1Wg5sd34DNhgm1toJup4AYb9fm4tLTGO665R20OPHieNkN/ywr0wwwRCYcDw2i+GBb"), d, d2)) {
            n = !editorSession.selectionDropdownVisible ? Xqedii_1as73k7.Xqedii_6s96e0m7981d(769534788, 25) : Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 29);
            this.setFileDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(1365709055, 25) != 0);
            this.setEditDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 31) != 0);
            this.setLayerDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(-1563549186, 26) != 0);
            this.setWindowDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 26) != 0);
            this.setSelectionDropdownVisible(player, editorSession, n != 0);
            this.updateEditorNavbarHover(player, editorSession, d, d2);
            return Xqedii_1as73k7.Xqedii_1nc45iibavbym(1302086653, 27) != 0;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("KY0ez6BuHgCom/Th14g/HDaOQofSrz/JdsPzHDWFjtVxX+LtIlbkRnCIV0VhrRvl4A+Bvw=="), d, d2)) {
            n = !editorSession.layerDropdownVisible ? Xqedii_1as73k7.Xqedii_1nc45iibavbym(-1496440322, 26) : Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 12);
            this.setFileDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(697737984, 31) != 0);
            this.setEditDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(-731904718, 12) != 0);
            this.setSelectionDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(-11442416, 17) != 0);
            this.setWindowDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(-1721094142, 3) != 0);
            this.setLayerDropdownVisible(player, editorSession, n != 0);
            this.updateEditorNavbarHover(player, editorSession, d, d2);
            return Xqedii_1as73k7.Xqedii_1nc45iibavbym(-723925953, 23) != 0;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("r2IHRz7LCaiyAJk3NT2vUvBu0zUSLqV4x9QyL6nxi8UrEnVPSOkzNp3OCrUGfQ1RVIt1hX5a"), d, d2)) {
            n = !editorSession.windowDropdownVisible ? Xqedii_1as73k7.Xqedii_6s96e0m7981d(769534788, 5) : Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 8);
            this.setFileDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 24) != 0);
            this.setEditDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(-91539321, 20) != 0);
            this.setSelectionDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 18) != 0);
            this.setLayerDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(-1673265851, 3) != 0);
            this.setWindowDropdownVisible(player, editorSession, n != 0);
            this.updateEditorNavbarHover(player, editorSession, d, d2);
            return Xqedii_1as73k7.Xqedii_1nc45iibavbym(-22622687, 18) != 0;
        }
        if (editorSession.fileDropdownVisible || editorSession.editDropdownVisible || editorSession.selectionDropdownVisible || editorSession.layerDropdownVisible || editorSession.windowDropdownVisible) {
            this.setFileDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 18) != 0);
            this.setEditDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(1610953428, 20) != 0);
            this.setSelectionDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 5) != 0);
            this.setLayerDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(-501224916, 6) != 0);
            this.setWindowDropdownVisible(player, editorSession, Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 7) != 0);
            this.updateEditorNavbarHover(player, editorSession, d, d2);
        }
        return Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(87217248, 28) != 0;
    }

    public void updateEditorNavbarHover(Player player, EditorSession editorSession, double d, double d2) {
        String string;
        if ((1683428619 * (1683428619 + 1) & 1) != 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null || !editorSession.editMode || !editorSession.previewMode) {
            return;
        }
        this.updateEditorNavbarHistoryIcons(player, editorSession);
        boolean bl = this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Cu6jj2PmM4ziPjnD3KsR/rNp+ZK6JKRAIePAYbYw0GPsbEvOhyKUnem7Vn0ZsrexzK8="), d, d2);
        int n = bl || editorSession.fileDropdownVisible ? Xqedii_1as73k7.Xqedii_1loegwk964oc9(349185140, 11) : Xqedii_1as73k7.Xqedii_1nc45iibavbym(267720305, 13);
        String string2 = editorSession.fileDropdownVisible ? this.resolveHoveredFileDropdownTargetId(editorSession, d, d2) : null;
        boolean bl2 = this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("k2AL1pKAo6ONYKajesg6+vA5nbNtvesGmqi/ZyFM18zZiaF8y1rU1CRUw2FXriAf4p0="), d, d2);
        int n2 = bl2 || editorSession.editDropdownVisible ? Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(1395475969, 0) : Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(1610953428, 20);
        String string3 = editorSession.editDropdownVisible ? this.resolveHoveredEditDropdownTargetId(editorSession, d, d2) : null;
        boolean bl3 = this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("tJkqTpq8blCABd1Wg5sd34DNhgm1toJup4AYb9fm4tLTGO665R20OPHieNkN/ywr0wwwRCYcDw2i+GBb"), d, d2);
        int n3 = bl3 || editorSession.selectionDropdownVisible ? Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187700, 9) : Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(-731904718, 12);
        String string4 = editorSession.selectionDropdownVisible ? this.resolveHoveredSelectionDropdownTargetId(editorSession, d, d2) : null;
        boolean bl4 = this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("KY0ez6BuHgCom/Th14g/HDaOQofSrz/JdsPzHDWFjtVxX+LtIlbkRnCIV0VhrRvl4A+Bvw=="), d, d2);
        int n4 = bl4 || editorSession.layerDropdownVisible ? Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(11114147, 23) : Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(697737984, 31);
        String string5 = editorSession.layerDropdownVisible ? this.resolveHoveredLayerDropdownTargetId(editorSession, d, d2) : null;
        boolean bl5 = this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("r2IHRz7LCaiyAJk3NT2vUvBu0zUSLqV4x9QyL6nxi8UrEnVPSOkzNp3OCrUGfQ1RVIt1hX5a"), d, d2);
        int n5 = bl5 || editorSession.windowDropdownVisible ? Xqedii_1as73k7.Xqedii_1nc45iibavbym(-1673265843, 3) : Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 8);
        String string6 = string = editorSession.windowDropdownVisible ? this.resolveHoveredWindowDropdownTargetId(editorSession, d, d2) : null;
        if (editorSession.fileNavbarVisualActive == (n != 0) && editorSession.editNavbarVisualActive == (n2 != 0) && editorSession.selectionNavbarVisualActive == (n3 != 0) && editorSession.layerNavbarVisualActive == (n4 != 0) && editorSession.windowNavbarVisualActive == (n5 != 0) && this.equalsNullable(editorSession.fileDropdownHoverTargetId, string2) && this.equalsNullable(editorSession.editDropdownHoverTargetId, string3) && this.equalsNullable(editorSession.selectionDropdownHoverTargetId, string4) && this.equalsNullable(editorSession.layerDropdownHoverTargetId, string5) && this.equalsNullable(editorSession.windowDropdownHoverTargetId, string)) {
            return;
        }
        editorSession.fileNavbarVisualActive = (n) != 0;
        editorSession.editNavbarVisualActive = (n2) != 0;
        editorSession.selectionNavbarVisualActive = (n3) != 0;
        editorSession.layerNavbarVisualActive = (n4) != 0;
        editorSession.windowNavbarVisualActive = (n5) != 0;
        editorSession.fileDropdownHoverTargetId = string2;
        editorSession.editDropdownHoverTargetId = string3;
        editorSession.selectionDropdownHoverTargetId = string4;
        editorSession.layerDropdownHoverTargetId = string5;
        editorSession.windowDropdownHoverTargetId = string;
        this.setShellText(player, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("1xzJKhLmRrRNTL6Njqe33L3OLvLjO56C7p2x6vZd1tQGreVs9MWC/kVnS9/gPu/HEIiusnTBP2/MmC+m"), n != 0 ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("vrS6PNIzlMtr5Y0SL/UMkQdGVlRffFOUQSgFn8UW") : Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("xZMBvxOWubaKIJ7sGGoJqlLYbTmQOB4MC/yAMmlH"));
        this.setShellText(player, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("EJ7VCeV5v0Yar4mLdkb5vPNIXdQMFDZbxy5QmdvkgQaKScgOzCK9sShdfcvZjS7yQWn/7gTnTCxDCVxa"), n2 != 0 ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("M2PAjvqQa4lagpHJrCdpA5bq7bPtfaej8CivRzYt") : Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("sse3IRjc6XN9Qxv573iWjosOtLAT83JtEY2ybYZV"));
        this.setShellText(player, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("k4NSS7wiv9wXUJB2/79+r6Ut3f+OzsfU7Iq4SWHYqbdNwKgJj6Mw4pTGwuJHPgRRQnAMDQwhDlFcsmFY+2XpKaHkoc0lHg=="), n3 != 0 ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("9ZP5il7zMThJl//96HwEPWRUB0OYHFr8Q5brYbhe") : Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("2GxMilLZoyINnCWoWaOA7kGJpWiF4ae+LhTynTE3"));
        this.setShellText(player, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("fm9Y+IXCaI14r0hkC7tuPWx1Rdau1FODOn82Nc1PRBV4Op1aHnx32c+wZ4eF+QV6ierXSG10hS6Rj3j/n7s="), n4 != 0 ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Qy/KFZglF7fVYfXMQi1cMa1gCiFr1aJaGdC2q/Ah") : Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("QeHikCW/z5aVzZJEzQ09rwi+cWShJ1vdEsMwc3wW"));
        this.setShellText(player, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("z+Pbhvnmp5w0Ds9Oa9YBmSfXalLpDdfF/VATpUfaeA52TlvoAvSjFoSrgM8S+9ZLekBoiji2PLEFoffFA74n2A=="), n5 != 0 ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("ggG57M6NsoAiiI8I765UF9KhkcLNi1uJALfEvOtl") : Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("58Kbv+jYQR+n1nSIScXd1dwN9mHfgyq5VDjvFeG/"));
        for (String string7 : Xqedii_3ucf4be.Xqedii_261585u) {
            this.setFileDropdownItemVisual(player, editorSession, string7, this.equalsNullable(string7, string2));
        }
        for (String string7 : Xqedii_3ucf4be.Xqedii_2j5tsfo) {
            this.setEditDropdownItemVisual(player, editorSession, string7, this.equalsNullable(string7, string3));
        }
        for (String string7 : Xqedii_3ucf4be.Xqedii_2a33z0z) {
            this.setSelectionDropdownItemVisual(player, editorSession, string7, this.equalsNullable(string7, string4));
        }
        for (String string7 : Xqedii_3ucf4be.Xqedii_fj0e6zy) {
            this.setLayerDropdownItemVisual(player, editorSession, string7, this.equalsNullable(string7, string5));
        }
        for (String string7 : Xqedii_3ucf4be.Xqedii_32x1dg9) {
            this.setWindowDropdownItemVisual(player, editorSession, string7, this.equalsNullable(string7, string));
        }
    }

    public abstract void startSavePopupInputPrompt(Player var1, EditorSession var2, EditorSavePopupField var3);

    public abstract boolean handleSavePopupConfirm(Player var1, EditorSession var2);

    public abstract void refreshSavePopupFields(Player var1, EditorSession var2);

    public boolean handlePreferencesPopupConfirm(Player player, EditorSession editorSession) {
        if ((0x712B4325 ^ 0x712B4325) != 0) {
            throw new IllegalStateException();
        }
        return Xqedii_1as73k7.Xqedii_1nc45iibavbym(2141827976, 16) != 0;
    }

    public boolean handlePreferencesPopupToggle(Player player, EditorSession editorSession, String string) {
        if ((0x9BB6CE47 ^ 0x9BB6CE47) != 0) {
            throw new IllegalStateException();
        }
        return Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 21) != 0;
    }

    public boolean handleWelcomePopupConfirm(Player player, EditorSession editorSession) {
        if ((1841312476 * (1841312476 + 1) & 1) != 0) {
            throw new IllegalStateException();
        }
        return Xqedii_1as73k7.Xqedii_1nc45iibavbym(-723925953, 23) != 0;
    }

    public void refreshPreferencesPopupFields(Player player, EditorSession editorSession) {
        if (((0x3F0D75EA | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
    }

    public void onPreferencesPopupVisibilityChanged(Player player, EditorSession editorSession, boolean bl) {
        if ((1899681950 * 1899681950 + 1899681950 & 1) != 0) {
            throw new IllegalStateException();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setSavePopupVisible(Player player, EditorSession editorSession, boolean bl) {
        if (((0xF267AF6D | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null) {
            return;
        }
        editorSession.savePopupVisible = bl;
        if (!bl) {
            editorSession.pendingSavePopupField = null;
        }
        this.beginShellOpacityBatch(editorSession);
        try {
            this.setSidebarPanelVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("7EWGTUfXkpsJyEBn2MWKLZ4xay7BQI16DiSjzSJ+gQelKs+l+wfl+GsO5eVAZZYysGICUbW3k+HD0g=="), bl);
        }
        finally {
            this.endShellOpacityBatch(player, editorSession);
        }
        double d = editorSession.cursorX + editorSession.hitboxOffsetX;
        double d2 = editorSession.cursorY + editorSession.hitboxOffsetY;
        this.updateSavePopupActionIcons(player, editorSession, d, d2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setPreferencesPopupVisible(Player player, EditorSession editorSession, boolean bl) {
        if ((-2126376170 * (-2126376170 + 1) & 1) != 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null) {
            return;
        }
        editorSession.preferencesPopupVisible = bl;
        this.beginShellOpacityBatch(editorSession);
        try {
            this.setSidebarPanelVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("f7vLt/5xQ4AvI/HXHZWjfLCcOAKsRfbKlhjKdWTzMsREEmcbcpVtes7QJtVjB1JH2qlLduHJ9z7uzTu7+yR1S/7q/THTnC8W"), bl);
        }
        finally {
            this.endShellOpacityBatch(player, editorSession);
        }
        if (bl) {
            this.refreshPreferencesPopupFields(player, editorSession);
        }
        this.onPreferencesPopupVisibilityChanged(player, editorSession, bl);
        double d = editorSession.cursorX + editorSession.hitboxOffsetX;
        double d2 = editorSession.cursorY + editorSession.hitboxOffsetY;
        this.updatePreferencesPopupActionIcons(player, editorSession, d, d2);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void setWelcomePopupVisible(Player player, EditorSession editorSession, boolean bl) {
        if (((290135936 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null) {
            return;
        }
        editorSession.welcomePopupVisible = bl;
        this.beginShellOpacityBatch(editorSession);
        try {
            this.setSidebarPanelVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("ovAr96PkVQ07qJs6KpguMTD+kvTMOPIA2khke+W5/hgjDhOUCtxu6fHsonVM22HAq66xitRdZP65Tqg6HJ41v2IqlHKci+N/"), bl);
            this.setSidebarPanelVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("znWD5JbM4U1tTcqwwka/o/Tbzjz4BYVd+jIJWfA1J+wXz+CTJUAk4M5gJYuqJNoYkLGQYlLoXqSIC4pdmJv9iQ=="), bl);
        }
        finally {
            this.endShellOpacityBatch(player, editorSession);
        }
        double d = editorSession.cursorX + editorSession.hitboxOffsetX;
        double d2 = editorSession.cursorY + editorSession.hitboxOffsetY;
        this.updateWelcomePopupActionIcons(player, editorSession, d, d2);
    }

    public void handleSavePopupClick(Player player, EditorSession editorSession, double d, double d2) {
        if (((145249337 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (!(player != null && editorSession != null && editorSession.editMode && editorSession.previewMode && editorSession.savePopupVisible)) {
            return;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("jeqqUjrYRc1g6C4aj6vSxq/vuWYA0knQULhpSry5d3K51clroAY3YuMRviebMQefqi83hS/QEW5DZwdHS+nZMC+9WrXyKT/l1Eo="), d, d2)) {
            this.startSavePopupInputPrompt(player, editorSession, EditorSavePopupField.NAME);
            return;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("zdR/nqxHR6zjbkf+p6uOli3r2OcRTcqn9ccz+YiMWlcJARwYN4ZihyTWgcm8evhZtUbn6PPpUEcPAIXUBF6sgcqTKLbzqL4rC4w="), d, d2)) {
            this.startSavePopupInputPrompt(player, editorSession, EditorSavePopupField.DESCRIPTION);
            return;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("FG5+Lsh8OncXPR4EvocmEa8HrlpWvdo8ExOLeI617YBBEImx8vt3T2BmqKtM8ukQ3xnek5Dvt89k2wIRyEUh5dVtrQ2Nt/LIoHEBmOu4X+E="), d, d2)) {
            this.startSavePopupInputPrompt(player, editorSession, EditorSavePopupField.COMMAND);
            return;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("mbzmHKOfMrgWwBU2ocU7ItypEKkhmBi6AgGnY2CXw2npmQlJvq3M1uZDlPWiQGfhR1JRfi4T42A/cbaaooHZTcUdb2ccaphqjxaC9g=="), d, d2)) {
            this.setSavePopupVisible(player, editorSession, Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 9) != 0);
            return;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("pIf8FQaK2zKG+y0YNlaQYTcqh2eX6QGh+5ZYHz9+UsKDHJgD3agoCxoZSE132pBMHOY1NG9k/ieRaj8qCDkD9qyNjhLp6SVo8TCiH7kW"), d, d2)) {
            if (this.isSavePopupNameMissing(editorSession)) {
                this.updateSavePopupActionIcons(player, editorSession, d, d2);
                this.sendEditorError(player, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("QWnt0AwgrRZzw1Mx7yXC9QWsorhTO3L+BIZFrNTmrWBN0gYecFBesjxVF0IHhd/xW78bBOmPXC9IGd6UqRtG1O30SKKj13K68H8XZUHr"), new Object[Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 12)]);
                return;
            }
            this.handleSavePopupConfirm(player, editorSession);
            EditorSession editorSession2 = (EditorSession)this.editorSessions.get(player.getUniqueId());
            if (editorSession2 == editorSession) {
                this.updateSavePopupActionIcons(player, editorSession, d, d2);
            }
            return;
        }
        this.updateSavePopupActionIcons(player, editorSession, d, d2);
    }

    public void handlePreferencesPopupClick(Player player, EditorSession editorSession, double d, double d2) {
        if ((0x7CF7CC5E ^ 0x7CF7CC5E) != 0) {
            throw new IllegalStateException();
        }
        if (!(player != null && editorSession != null && editorSession.editMode && editorSession.previewMode && editorSession.preferencesPopupVisible)) {
            return;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("peP3d1Y+7jQidu+/JbsPlV2Nk64lyux7rfxBZc6oJMhCDYuN9ACV/ggThlSkIZ6f9/CJPjp/JrCc/24p7Y7/H6bbwZGzikQIY2U3gFcPoWNmW6FnPAM="), d, d2)) {
            if (this.handlePreferencesPopupToggle(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("peP3d1Y+7jQidu+/JbsPlV2Nk64lyux7rfxBZc6oJMhCDYuN9ACV/ggThlSkIZ6f9/CJPjp/JrCc/24p7Y7/H6bbwZGzikQIY2U3gFcPoWNmW6FnPAM="))) {
                this.refreshPreferencesPopupFields(player, editorSession);
            }
            this.updatePreferencesPopupActionIcons(player, editorSession, d, d2);
            return;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("GS6awNPuC/Rm81b9NGKZTqkvEyNX8dKVRUUxXcV7CUsDowoel1IN1VslrFfI1qQvgExWaYuDoQaPl5ypljlVo0eKt1L4SO9smqtszmB9+QEdxw=="), d, d2)) {
            if (this.handlePreferencesPopupToggle(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("GS6awNPuC/Rm81b9NGKZTqkvEyNX8dKVRUUxXcV7CUsDowoel1IN1VslrFfI1qQvgExWaYuDoQaPl5ypljlVo0eKt1L4SO9smqtszmB9+QEdxw=="))) {
                this.refreshPreferencesPopupFields(player, editorSession);
            }
            this.updatePreferencesPopupActionIcons(player, editorSession, d, d2);
            return;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("7oQHtF0/tomggR1THjBcR9515d17cmNDC/lEOMX2SdvcW2zOkObjbsD8wXS7DFR7omGDFvE7+Hke1DpZiKYtUK5yLJo7y6hf3b5mjfss5Q+ja6IMqrwuocISUJM="), d, d2)) {
            if (this.handlePreferencesPopupToggle(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("7oQHtF0/tomggR1THjBcR9515d17cmNDC/lEOMX2SdvcW2zOkObjbsD8wXS7DFR7omGDFvE7+Hke1DpZiKYtUK5yLJo7y6hf3b5mjfss5Q+ja6IMqrwuocISUJM="))) {
                this.refreshPreferencesPopupFields(player, editorSession);
            }
            this.updatePreferencesPopupActionIcons(player, editorSession, d, d2);
            return;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("V/AD1pHjMEa8whCAd/GY4EcT/ptmcv20RgUkVu7u20QKc7Z8nn79cHSzlynAhOBfHaFd3k4tx4yTx6mBMoWmLocgn9MouBwsmWvQmG93PMZXL4Gi"), d, d2)) {
            if (this.handlePreferencesPopupToggle(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("V/AD1pHjMEa8whCAd/GY4EcT/ptmcv20RgUkVu7u20QKc7Z8nn79cHSzlynAhOBfHaFd3k4tx4yTx6mBMoWmLocgn9MouBwsmWvQmG93PMZXL4Gi"))) {
                this.refreshPreferencesPopupFields(player, editorSession);
            }
            this.updatePreferencesPopupActionIcons(player, editorSession, d, d2);
            return;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Hjg8ya2HMqCO5CpSmLy47jGJYSxFh7ulRXnzcW4FakoDAxw0KfIYvbUBVHm4f/lgK/u716GpZ/ThXwmPtoZXzKecqSF9h6FN2GakjOc48XYJkPmub+zYQXxM"), d, d2)) {
            this.setPreferencesPopupVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(285167975, 9) != 0);
            return;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("n9YXU1R68YV+SEY8TX8hD14so1j1EoEJ+AP5ErtkunN9f9pH6r4mci5kONzltJSJA1VaRzsdIkHNuQoZwJnw8jOBXw6eT1/AmmXDh+wUYMrC0JT6BkSfQD3GyyM="), d, d2) && this.handlePreferencesPopupConfirm(player, editorSession)) {
            this.setPreferencesPopupVisible(player, editorSession, Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(1286936577, 2) != 0);
            return;
        }
        this.updatePreferencesPopupActionIcons(player, editorSession, d, d2);
    }

    public void handleWelcomePopupClick(Player player, EditorSession editorSession, double d, double d2) {
        if ((0x7388F781 ^ 0x7388F781) != 0) {
            throw new IllegalStateException();
        }
        if (!(player != null && editorSession != null && editorSession.editMode && editorSession.previewMode && editorSession.welcomePopupVisible)) {
            return;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("cNavVdMrLjNtC0w5VduRDZc50pEuX/s+uevi6aM1Ncgq1lsKv563Mn12rnTl7fzWHnGoChQv+v56WCF4qS9VfdbG0In5muDN/Wnf6GcA"), d, d2)) {
            this.sendWelcomePopupDocsMessage(player);
            this.playWelcomePopupSound(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("jNPScFZa53ceFDqiyCa7iZnpealGIKNS4auPVXiEtoptj2I09dM9/9VQxqsX7hrgzkJqNPwybJYDR69PFJY="));
            this.updateWelcomePopupActionIcons(player, editorSession, d, d2);
            return;
        }
        if (this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("4tgz/z7PL1Du4zbXN+lKMoTT7gFjjZpFTMJPMwYuMxuoJm52FAg730iqy6D49zcqGO5hHF6JURh4lj+P0mztfChPk5d06XP+1FWZ+wpT6genGe6w"), d, d2) && this.handleWelcomePopupConfirm(player, editorSession)) {
            this.setWelcomePopupVisible(player, editorSession, Xqedii_1as73k7.Xqedii_1nc45iibavbym(-2004899661, 8) != 0);
            this.playWelcomePopupSound(player, editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("q8aeGAX770wk0C1hdf89XK84Kp2Ck04SD13ccndDzkv9/umC7tXjQInAE7fKLjmNf3u7FtJhA5W4Wrc/Bfg="));
            return;
        }
        this.updateWelcomePopupActionIcons(player, editorSession, d, d2);
    }

    public void updateSavePopupHover(Player player, EditorSession editorSession, double d, double d2) {
        if (((-64310698 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (!(player != null && editorSession != null && editorSession.editMode && editorSession.previewMode && editorSession.savePopupVisible)) {
            return;
        }
        this.updateSavePopupActionIcons(player, editorSession, d, d2);
    }

    public void updatePreferencesPopupHover(Player player, EditorSession editorSession, double d, double d2) {
        if ((184852006 * 184852006 + 184852006 & 1) != 0) {
            throw new IllegalStateException();
        }
        if (!(player != null && editorSession != null && editorSession.editMode && editorSession.previewMode && editorSession.preferencesPopupVisible)) {
            return;
        }
        this.updatePreferencesPopupActionIcons(player, editorSession, d, d2);
    }

    public void updateWelcomePopupHover(Player player, EditorSession editorSession, double d, double d2) {
        if (((1061064931 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (!(player != null && editorSession != null && editorSession.editMode && editorSession.previewMode && editorSession.welcomePopupVisible)) {
            return;
        }
        this.updateWelcomePopupActionIcons(player, editorSession, d, d2);
    }

    public void updateSavePopupActionIcons(Player player, EditorSession editorSession, double d, double d2) {
        if ((206456262 * 206456262 - 206456262 & 1) != 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null) {
            return;
        }
        int n = editorSession.savePopupVisible && this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("pIf8FQaK2zKG+y0YNlaQYTcqh2eX6QGh+5ZYHz9+UsKDHJgD3agoCxoZSE132pBMHOY1NG9k/ieRaj8qCDkD9qyNjhLp6SVo8TCiH7kW"), d, d2) ? Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(-1945983398, 17) : Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 4);
        int n2 = editorSession.savePopupVisible && this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("mbzmHKOfMrgWwBU2ocU7ItypEKkhmBi6AgGnY2CXw2npmQlJvq3M1uZDlPWiQGfhR1JRfi4T42A/cbaaooHZTcUdb2ccaphqjxaC9g=="), d, d2) ? Xqedii_1as73k7.Xqedii_1nc45iibavbym(1140673948, 11) : Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(-1560270442, 15);
        boolean bl = this.isSavePopupNameMissing(editorSession);
        String string = bl ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("D6JnIE/Z9924VvkQvGqHYereD7CRKsZ6JN+BfDqV") : (n != 0 ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("RpRJpTbpHMRunwmxCvydiJw3TUz2VdwNltzEdbkU") : Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("1tq/No5fe35kgMe45Tg7EqCGvG4TOahXrxSt3MUq"));
        String string2 = n2 != 0 ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("FA/o152tLKICSo/iOnBayhWGTy8uoDAHgJEL74Gk") : Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("O1MVSTIw95HtBqCH6NHGqqFEODPfOrx8HPS5o2bX");
        this.setShellText(player, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("tiABz0UyVdCWd6SHFls8lpXrMxYdg29d9qrxp08k9Rs4cNO3+v5AqznKwPJgArR3+JQ8gzHqwxQJsWqHkhMbPQ=="), string);
        this.setShellText(player, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("SnBUlXEBeDae68XjGZpvAQbvqcL22bKI2N35m7BkIl3YGiv8JYEYwXqcTxWwaEM32nOy7igzw2YQgRterKQ="), string2);
    }

    public void updatePreferencesPopupActionIcons(Player player, EditorSession editorSession, double d, double d2) {
        if (((0xDC48909E | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null) {
            return;
        }
        int n = editorSession.preferencesPopupVisible && this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("n9YXU1R68YV+SEY8TX8hD14so1j1EoEJ+AP5ErtkunN9f9pH6r4mci5kONzltJSJA1VaRzsdIkHNuQoZwJnw8jOBXw6eT1/AmmXDh+wUYMrC0JT6BkSfQD3GyyM="), d, d2) ? Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(-1504015358, 1) : Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(1395475968, 0);
        int n2 = editorSession.preferencesPopupVisible && this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Hjg8ya2HMqCO5CpSmLy47jGJYSxFh7ulRXnzcW4FakoDAxw0KfIYvbUBVHm4f/lgK/u716GpZ/ThXwmPtoZXzKecqSF9h6FN2GakjOc48XYJkPmub+zYQXxM"), d, d2) ? Xqedii_1as73k7.Xqedii_1nc45iibavbym(-2013619400, 12) : Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 8);
        String string = n != 0 ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("RpRJpTbpHMRunwmxCvydiJw3TUz2VdwNltzEdbkU") : Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("1tq/No5fe35kgMe45Tg7EqCGvG4TOahXrxSt3MUq");
        String string2 = n2 != 0 ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("FA/o152tLKICSo/iOnBayhWGTy8uoDAHgJEL74Gk") : Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("O1MVSTIw95HtBqCH6NHGqqFEODPfOrx8HPS5o2bX");
        this.setShellText(player, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("3vouUpvPgOpTCI41FPL5uWYPVHOdM2a1t+ViiAtZiLlowB4dgI+9i88G5JbPTuER93+0KpLztb/JMIlwGEgP+lNoKU2NZCLiMXqYIa1d"), string);
        this.setShellText(player, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("qRwaUVNIjnMmKm21zUTRuRN9kYHppmwkXPfI8FYOZlKbX24xpXRCapcWoDA0C0nyIsyHhyfoxtG5lJ0FpxO+nad4lhcyYS+aAJcJWw=="), string2);
    }

    public void updateWelcomePopupActionIcons(Player player, EditorSession editorSession, double d, double d2) {
        if (((0x60A87310 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (player == null || editorSession == null) {
            return;
        }
        int n = editorSession.welcomePopupVisible && this.isInsideShellBlock(editorSession, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("4tgz/z7PL1Du4zbXN+lKMoTT7gFjjZpFTMJPMwYuMxuoJm52FAg730iqy6D49zcqGO5hHF6JURh4lj+P0mztfChPk5d06XP+1FWZ+wpT6genGe6w"), d, d2) ? Xqedii_1as73k7.Xqedii_1loegwk964oc9(349191284, 12) : Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 26);
        String string = n != 0 ? Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("RpRJpTbpHMRunwmxCvydiJw3TUz2VdwNltzEdbkU") : Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("1tq/No5fe35kgMe45Tg7EqCGvG4TOahXrxSt3MUq");
        this.setShellText(player, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("uzqKsQgks/0mVNU/xlggALLLqHuul2Pf6PkgQhrW7i1R0vjgiVZlbXUSOa835MzZ/1eQ6VTwVQd4Sv1/DLA56odsP2ks6Q=="), string);
    }

    public void sendWelcomePopupDocsMessage(Player player) {
        if ((1040729511 * (1040729511 + 1) & 1) != 0) {
            throw new IllegalStateException();
        }
        if (player == null) {
            return;
        }
        ChatColorUtil.Xqedii_uawesb0((CommandSender)player, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("ZKjK1W8dx5I/PI0Oejcv8GTynmotiJ8aBaDBscw3SNNrfzInVvxUG6CtKeMt2VE1t+rzIAt8Vp/R82rZvjrj/1NT2iErg5i4cYC70ayvFllp1COXIfCFXcSaMcD3SJ9YjZnKU7CByivNRYIvVwnBdZe8Pjmx5d9ydz9NqQzzA8Bno4vRo6R2A+BkeAvyd9VBZEvhoGtzvCSHGdpYUQVQ23eGrCENHIYhToS9TPO/Zfd7min34zpt2Cn/aBdogddJuhVoc9KLKcjfcRzqoDVnQB99bI620Sg3V4DjrdKsSEmjbLJdl/+VQZIQF7PANodPX4oTZKfh/DzEUqf/TrddtGsh/DW34XOAL2kWjtbICQhqdjJw695FnaFH8RKkhI6Fa83rVWttupbc74hGPKj24BmtvFH4LPv7OPsMTY2TvYqQhkhl8VdWogu8NfRSw5qPu/EO1XckFgmrvULMt5ZhGTNaPWNVH8jvTb0QN/zd8DbTBvRZMI4j06P1saCXjh0icRxDMxyJFNIDaJ+k8QhE82FVGIDKsDYbmlYABwCQqATn1q7gweWcLobApDEvhJHfeP+mHk7EviMhnOf8fyRKTss3stjOXA=="));
    }

    public void playWelcomePopupSound(Player player, EditorSession editorSession, String string) {
        if (((0x19639D87 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (player == null || string == null || string.isBlank()) {
            return;
        }
        if (!this.plugin.getConfig().getBoolean(Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("X+uKhDu5LOfwv9vGmhuGc61yDeRuJB+UKZUS0VIV1YISBQLUyvGMkOvnXzgN51t21juV7PW7ElNvKT0SsiJssJ1GRLuELw=="), Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(1612002004, 20) != 0)) {
            return;
        }
        if (editorSession != null && !editorSession.editorSounds) {
            return;
        }
        float f = (float)Math.max(Double.longBitsToDouble(Xqedii_1as73k7.Xqedii_2z8r9m5d6urt6(2939976914930298439L, 45)), this.plugin.getConfig().getDouble(Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("5mc4GBUKmai7xOACEFZfS5uJISlViu0XrRjiIfNfeIFNBnl3QMY/xcMlGcjtpH9aUnC19fCxGBe/Enezan+vMHL03t4="), Double.longBitsToDouble(Xqedii_1as73k7.Xqedii_2sf44gybgk4ii(4201389270777242118L, 5))));
        try {
            player.playSound(player.getLocation(), string, SoundCategory.MASTER, f, Float.intBitsToFloat(Xqedii_1as73k7.Xqedii_6s96e0m7981d(-902703941, 29)));
        }
        catch (RuntimeException runtimeException) {
            // empty catch block
        }
    }

    public boolean isSavePopupNameMissing(EditorSession editorSession) {
        if ((-1649367344 * -1649367344 - -1649367344 & 1) != 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null) {
            return Xqedii_1as73k7.Xqedii_1nc45iibavbym(-22622687, 18) != 0;
        }
        String[] stringArray = new String[Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187172, 4)];
        stringArray[Xqedii_1as73k7.Xqedii_1nc45iibavbym((int)-2013623496, (int)12)] = editorSession.savePopupName;
        return this.firstNonBlank(stringArray).trim().isEmpty();
    }

    public void sendEditorPlayerMessage(Player player, Component component) {
        if ((-33123169 * (-33123169 + 1) & 1) != 0) {
            throw new IllegalStateException();
        }
        if (player == null || component == null) {
            return;
        }
        String string = PlainTextComponentSerializer.plainText().serialize(component);
        if (this.shouldSuppressEditorPlayerMessage(player, string)) {
            return;
        }
        ChatColorUtil.send((CommandSender)player, component);
    }

    public void sendEditorPlayerMessage(Player player, String string) {
        if (((321402088 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (player == null || string == null || string.isBlank()) {
            return;
        }
        if (this.shouldSuppressEditorPlayerMessage(player, string)) {
            return;
        }
        ChatColorUtil.send((CommandSender)player, (Component)LegacyComponentSerializer.legacySection().deserialize(ChatColorUtil.Xqedii_2qnwa4c(string.trim())));
    }

    public void sendEditorError(Player player, String string, Object ... objectArray) {
        if ((0xD51DE0A8 ^ 0xD51DE0A8) != 0) {
            throw new IllegalStateException();
        }
        this.sendEditorPlayerMessage(player, Xqedii_3uxgwyg.Xqedii_2p0af0b.deserialize((String)ChatColorUtil.Xqedii_1t2nnko(string, objectArray)));
    }

    public void sendEditorUsage(Player player, String string, String string2) {
        if ((1227117012 * 1227117012 - 1227117012 & 1) != 0) {
            throw new IllegalStateException();
        }
        this.sendEditorPlayerMessage(player, Xqedii_3uxgwyg.Xqedii_2p0af0b.deserialize((String)ChatColorUtil.Xqedii_1wtk0rm(string, string2)));
    }

    public void sendEditorSuccess(Player player, String string, Object ... objectArray) {
        if ((0x5E3B58CC ^ 0x5E3B58CC) != 0) {
            throw new IllegalStateException();
        }
        this.sendEditorPlayerMessage(player, Xqedii_3uxgwyg.Xqedii_2p0af0b.deserialize((String)ChatColorUtil.Xqedii_33qmw8k(string, objectArray)));
    }

    public void sendEditorRemoved(Player player, String string, Object ... objectArray) {
        if (((-926902025 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        this.sendEditorPlayerMessage(player, Xqedii_3uxgwyg.Xqedii_2p0af0b.deserialize((String)ChatColorUtil.Xqedii_9qy68nj(string, objectArray)));
    }

    public void sendEditorInfo(Player player, String string, Object ... objectArray) {
        if ((413213315 * (413213315 + 1) & 1) != 0) {
            throw new IllegalStateException();
        }
        this.sendEditorPlayerMessage(player, Xqedii_3uxgwyg.Xqedii_2p0af0b.deserialize((String)ChatColorUtil.Xqedii_2iiyx7k(string, objectArray)));
    }

    public void sendEditorLimitReached(Player player, int n) {
        if ((890639663 * 890639663 + 890639663 & 1) != 0) {
            throw new IllegalStateException();
        }
        if (this.plugin != null) {
            this.sendEditorPlayerMessage(player, this.plugin.getLangMessageWithPlaceholders(Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("0euNBrjItMu1GXsVawH7BbEBnodCty3tXZQovSAhQ/YcjhpPdSua+3I3dLfutaYR+xTCL2iiNPTAW0k2GhY="), Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("LkzMBlcpC+w3IAtQ6nwfbAjFE/oMu3flgguD+P0cMuDNczdAPPE5q2PH76EjhcrZ+FXb/WJDvLhy1iyd4XcUtC3nEJ1lC5IzMXJxvt7xvlfhA9/WkV7HeC3+u3gdHCtbZQyH2pAkUyl5hyC+xztbhQ=="), Map.of()));
            return;
        }
        Object[] objectArray = new Object[Xqedii_1as73k7.Xqedii_1loegwk964oc9(349219956, 15)];
        objectArray[Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7((int)43608624, (int)27)] = n;
        this.sendEditorError(player, Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Guw8ko7u1reD+82qr7JKRaPyjNvh4dleBopcUjTow754KqIY6204g1+JEgvp//u1OKReqynBdFeLoCs/njOjwk+6Bq6Bf+85UpsueFwptEpszA7gZttGIEIxidNUBrqjO+blDixOU9liBV2xB8WZjZZCAuxzb9hQF1M="), objectArray);
    }

    public boolean shouldSuppressEditorPlayerMessage(Player player, String string) {
        if ((0xA613E0EC ^ 0xA613E0EC) != 0) {
            throw new IllegalStateException();
        }
        if (player == null) {
            return Xqedii_1as73k7.Xqedii_1nc45iibavbym(-836632922, 2) != 0;
        }
        EditorSession editorSession = (EditorSession)this.editorSessions.get(player.getUniqueId());
        if (editorSession == null || !editorSession.editMode) {
            return Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 20) != 0;
        }
        String[] stringArray = new String[Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(1705558058, 5)];
        stringArray[Xqedii_1as73k7.Xqedii_1nc45iibavbym((int)1140671900, (int)11)] = string;
        String string2 = this.firstNonBlank(stringArray).toLowerCase(Locale.ROOT);
        return (!string2.contains(Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("RSI/nYuUT0TLRB461JBfbMRY1BuM7XnYi5kEhA0c7twh7V8O+rI=")) ? Xqedii_1as73k7.Xqedii_1loegwk964oc9(282078324, 26) : Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 31)) != 0;
    }

    public void recordEditorMutation(EditorSession editorSession) {
        if (((-506564650 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
    }

    public String captureEditorMutationSnapshot(EditorSession editorSession) {
        if ((-1923603821 * -1923603821 + -1923603821 & 1) != 0) {
            throw new IllegalStateException();
        }
        return Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("32AI7cFLaJZOrv91XP/m01v4QhQdSMH13joviQ==");
    }

    public void recordEditorMutationSnapshot(EditorSession editorSession, String string) {
        if ((819342229 * 819342229 - 819342229 & 1) != 0) {
            throw new IllegalStateException();
        }
    }

    public void beginSidebarFieldDragHistory(EditorSession editorSession) {
        if ((-1071268802 * -1071268802 - -1071268802 & 1) != 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null) {
            return;
        }
        if (editorSession.sidebarFieldDragStartSnapshot != null || editorSession.sidebarFieldDragMutated) {
            this.finishSidebarFieldDragHistory(editorSession);
        }
        editorSession.sidebarFieldDragStartSnapshot = this.captureEditorMutationSnapshot(editorSession);
        editorSession.sidebarFieldDragMutated = (Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 13)) != 0;
    }

    public void finishSidebarFieldDragHistory(EditorSession editorSession) {
        if ((1568608724 * 1568608724 - 1568608724 & 1) != 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null) {
            return;
        }
        String string = editorSession.sidebarFieldDragStartSnapshot;
        boolean bl = editorSession.sidebarFieldDragMutated;
        editorSession.sidebarFieldDragStartSnapshot = null;
        editorSession.sidebarFieldDragMutated = (Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 7)) != 0;
        if (!bl || string == null || string.isBlank()) {
            return;
        }
        this.recordEditorMutationSnapshot(editorSession, string);
    }

    public void beginOpacitySliderDragHistory(EditorSession editorSession) {
        if ((0xC211EDBF ^ 0xC211EDBF) != 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null) {
            return;
        }
        if (editorSession.opacitySliderDragStartSnapshot != null || editorSession.opacitySliderDragMutated) {
            this.finishOpacitySliderDragHistory(editorSession);
        }
        editorSession.opacitySliderDragStartSnapshot = this.captureEditorMutationSnapshot(editorSession);
        editorSession.opacitySliderDragMutated = (Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(-883851244, 6)) != 0;
    }

    public void finishOpacitySliderDragHistory(EditorSession editorSession) {
        if (((1924099755 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null) {
            return;
        }
        String string = editorSession.opacitySliderDragStartSnapshot;
        boolean bl = editorSession.opacitySliderDragMutated;
        editorSession.opacitySliderDragStartSnapshot = null;
        editorSession.opacitySliderDragMutated = (Xqedii_1as73k7.Xqedii_1nc45iibavbym(535440610, 14)) != 0;
        if (!bl || string == null || string.isBlank()) {
            return;
        }
        this.recordEditorMutationSnapshot(editorSession, string);
    }

    public void stopOpacitySliderDrag(EditorSession editorSession, boolean bl) {
        if ((-1114714190 * -1114714190 - -1114714190 & 1) != 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null) {
            return;
        }
        if (bl) {
            this.finishOpacitySliderDragHistory(editorSession);
        } else {
            editorSession.opacitySliderDragStartSnapshot = null;
            editorSession.opacitySliderDragMutated = (Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 9)) != 0;
        }
        editorSession.opacitySliderDragActive = (Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 26)) != 0;
        editorSession.opacitySliderLastValue = null;
        editorSession.sidebarOpacityRefreshTriggered = (Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 5)) != 0;
    }

    public void stopAnimationTimelineSliderDrag(EditorSession editorSession) {
        if ((370225674 * 370225674 + 370225674 & 1) != 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null) {
            return;
        }
        editorSession.animationTimelineSliderDragActive = (Xqedii_1as73k7.Xqedii_1nc45iibavbym(267720305, 13)) != 0;
        editorSession.animationTimelineSliderDragTick = null;
    }

    public void stopAnimationTimelineKeyframeDrag(EditorSession editorSession, boolean bl) {
        if ((-1322952795 * -1322952795 - -1322952795 & 1) != 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null) {
            return;
        }
        if (bl) {
            String string = editorSession.timelineKeyframeDragStartSnapshot;
            boolean bl2 = editorSession.timelineKeyframeDragMutated;
            if (bl2 && string != null && !string.isBlank()) {
                this.recordEditorMutationSnapshot(editorSession, string);
            }
        }
        editorSession.timelineKeyframeDragActive = (Xqedii_1as73k7.Xqedii_1nc45iibavbym(2141762440, 16)) != 0;
        editorSession.timelineRenderedSignature = null;
        editorSession.timelineKeyframeDragRuntimeTick = Xqedii_1as73k7.Xqedii_1nc45iibavbym(-2013627592, 12);
        editorSession.timelineKeyframeDragTickByLogicalTick.clear();
        editorSession.timelineKeyframeDragTickByRef.clear();
        editorSession.timelineKeyframeDragStartSnapshot = null;
        editorSession.timelineKeyframeDragMutated = (Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(1519124646, 9)) != 0;
        editorSession.timelineKeyframeDragStartHitX = Double.longBitsToDouble(Xqedii_1as73k7.Xqedii_2sf44gybgk4ii(-5411826457795485469L, 9));
        editorSession.timelineKeyframeDragHitOffsetX = Double.longBitsToDouble(Xqedii_1as73k7.Xqedii_2sf44gybgk4ii(1434485545553570468L, 36));
    }

    public void clearTimelineSelectedKeyframe(EditorSession editorSession) {
        if ((-1986442661 * (-1986442661 + 1) & 1) != 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null) {
            return;
        }
        editorSession.animationTimelineSelectedTargetId = null;
        editorSession.animationTimelineSelectedRow = null;
        editorSession.animationTimelineSelectedTick = Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534790, 0);
        this.clearTimelineExtraSelection(editorSession);
        editorSession.timelineKeyframeDragRuntimeTick = Xqedii_1as73k7.Xqedii_6s96e0m7981d(-232663877, 29);
        editorSession.timelineKeyframeDragTickByLogicalTick.clear();
        editorSession.timelineKeyframeDragTickByRef.clear();
    }

    public void clearTimelineExtraSelection(EditorSession editorSession) {
        if (((665261621 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null) {
            return;
        }
        editorSession.timelineExtraSelectedTicks.clear();
        editorSession.timelineExtraSelectedKeyframeRefs.clear();
    }

    public void recordMutationForInteraction(EditorSession editorSession) {
        if (((0x4A5BBF73 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null) {
            return;
        }
        if (editorSession.sidebarFieldDragActive) {
            editorSession.sidebarFieldDragMutated = (Xqedii_1as73k7.Xqedii_6s96e0m7981d(769534788, 10)) != 0;
            return;
        }
        if (editorSession.opacitySliderDragActive) {
            editorSession.opacitySliderDragMutated = (Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(1705558058, 5)) != 0;
            return;
        }
        this.recordEditorMutation(editorSession);
    }

    public abstract int clampAnimationTimelineTick(int var1);

    public String buildAnimationTimelineKeyframeRef(String string, int n) {
        if ((0xF512C527 ^ 0xF512C527) != 0) {
            throw new IllegalStateException();
        }
        String[] stringArray = new String[Xqedii_1as73k7.Xqedii_6s96e0m7981d(769534788, 24)];
        stringArray[Xqedii_1as73k7.Xqedii_1loegwk964oc9((int)349187188, (int)20)] = string;
        String string2 = this.firstNonBlank(stringArray).toLowerCase(Locale.ROOT);
        if (string2.isBlank() || !Xqedii_19udyqn.containsKey(string2)) {
            return Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("32AI7cFLaJZOrv91XP/m01v4QhQdSMH13joviQ==");
        }
        return EditorNavbarHistoryPart.Xqedii_ujny9o13mda7(string2, this.clampAnimationTimelineTick(n));
    }

    public AnimationTimelineKeyframeRef parseTimelineSelectionRef(String string) {
        if (((1670973264 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        String[] stringArray = new String[Xqedii_1as73k7.Xqedii_6s96e0m7981d(769534788, 29)];
        stringArray[Xqedii_1as73k7.Xqedii_6s96e0m7981d((int)-769534789, (int)24)] = string;
        String string2 = this.firstNonBlank(stringArray).trim().toLowerCase(Locale.ROOT);
        if (string2.isBlank()) {
            return null;
        }
        int n = string2.indexOf(Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(-731667150, 12));
        if (n <= 0 || n >= string2.length() - Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(355652704, 28)) {
            return null;
        }
        String string3 = string2.substring(Xqedii_1as73k7.Xqedii_1nc45iibavbym(285167975, 9), n);
        if (!Xqedii_19udyqn.containsKey(string3)) {
            return null;
        }
        double d = this.parseDouble(string2.substring(n + Xqedii_1as73k7.Xqedii_6s96e0m7981d(769534788, 2)), Double.longBitsToDouble(Xqedii_1as73k7.Xqedii_j39opfilg3vt(2910369332561131763L, 34)));
        if (!Double.isFinite(d)) {
            return null;
        }
        int n2 = this.clampAnimationTimelineTick((int)Math.round(d));
        return new AnimationTimelineKeyframeRef(string3, n2);
    }

    public void addTimelineExtraSelectionRef(EditorSession editorSession, String string, int n) {
        if ((0x9C75D719 ^ 0x9C75D719) != 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null) {
            return;
        }
        String string2 = this.buildAnimationTimelineKeyframeRef(string, n);
        if (!string2.isBlank()) {
            editorSession.timelineExtraSelectedKeyframeRefs.add(string2);
        }
    }

    public void removeTimelineExtraSelectionRef(EditorSession editorSession, String string, int n) {
        if ((0x5D1A5E85 ^ 0x5D1A5E85) != 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null) {
            return;
        }
        String string2 = this.buildAnimationTimelineKeyframeRef(string, n);
        if (!string2.isBlank()) {
            editorSession.timelineExtraSelectedKeyframeRefs.remove(string2);
        }
    }

    public boolean containsTimelineExtraSelectionRef(EditorSession editorSession, String string, int n) {
        if ((-540845155 * -540845155 + -540845155 & 1) != 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null) {
            return Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 12) != 0;
        }
        String string2 = this.buildAnimationTimelineKeyframeRef(string, n);
        return (!string2.isBlank() && editorSession.timelineExtraSelectedKeyframeRefs.contains(string2) ? Xqedii_1as73k7.Xqedii_1loegwk964oc9(886058100, 29) : Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 26)) != 0;
    }

    public void movePrimaryTimelineSelectionIntoExtraRefs(EditorSession editorSession) {
        if ((-388444336 * (-388444336 + 1) & 1) != 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null || !this.isTimelineKeyframeSelected(editorSession)) {
            return;
        }
        String[] stringArray = new String[Xqedii_1as73k7.Xqedii_6s96e0m7981d(769534788, 21)];
        stringArray[Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7((int)-2146120879, (int)22)] = editorSession.animationTimelineSelectedRow;
        String string = this.firstNonBlank(stringArray).toLowerCase(Locale.ROOT);
        if (string.isBlank() || !Xqedii_19udyqn.containsKey(string)) {
            this.clearTimelineExtraSelection(editorSession);
            return;
        }
        int n = this.clampAnimationTimelineTick(editorSession.animationTimelineSelectedTick);
        this.addTimelineExtraSelectionRef(editorSession, string, n);
        for (Integer n2 : editorSession.timelineExtraSelectedTicks) {
            if (n2 == null) continue;
            this.addTimelineExtraSelectionRef(editorSession, string, n2);
        }
        editorSession.timelineExtraSelectedTicks.clear();
    }

    public boolean isTimelineKeyframeSelected(EditorSession editorSession) {
        if (((0xD91AC1E0 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null || editorSession.activeTool != EditorTool.ANIMATION) {
            return Xqedii_1as73k7.Xqedii_1nc45iibavbym(-1002449831, 7) != 0;
        }
        String[] stringArray = new String[Xqedii_1as73k7.Xqedii_1loegwk964oc9(1422929012, 30)];
        stringArray[Xqedii_1as73k7.Xqedii_1nc45iibavbym((int)-1959229446, (int)28)] = editorSession.animationTimelineSelectedTargetId;
        String string = this.firstNonBlank(stringArray);
        String[] stringArray2 = new String[Xqedii_1as73k7.Xqedii_1nc45iibavbym(-1496440322, 26)];
        stringArray2[Xqedii_1as73k7.Xqedii_1nc45iibavbym((int)-501224916, (int)6)] = editorSession.animationTimelineSelectedRow;
        String string2 = this.firstNonBlank(stringArray2).toLowerCase(Locale.ROOT);
        return (!string.isBlank() && (Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("uq2o1ZeZ1SXeJrEar6F7Qc3WXuUCPiQtoT2q1YJ/IwBAvPygNQ3aENGo7ME=").equals(string2) || Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("qAplKt+uXLVbbluHwmDwPR4rFUHaJTIohHyxxdjbKxlB9flwLxvoP0Mfw3w=").equals(string2) || Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Yuhvm1cSTphzxDbbHs566nYwbtX/8qVwWFRxiBW3jE6WkjvVqR4=").equals(string2) || Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("KRO/QZ2KYPLBnp4CFACmqRNJHyYzpB082v0/d/Z5ZWNSFs1YILxtzL+x").equals(string2)) && editorSession.animationTimelineSelectedTick >= 0 ? Xqedii_1as73k7.Xqedii_1nc45iibavbym(-1002449703, 7) : Xqedii_1as73k7.Xqedii_1nc45iibavbym(1365709055, 25)) != 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean isTimelinePositionKeyframeSelected(EditorSession editorSession) {
        int n;
        if (this.isTimelineKeyframeSelected(editorSession)) {
            String[] stringArray = new String[Xqedii_1as73k7.Xqedii_1loegwk964oc9(349185140, 11)];
            stringArray[Xqedii_1as73k7.Xqedii_1loegwk964oc9((int)349187188, (int)18)] = editorSession.animationTimelineSelectedRow;
            if (Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("qAplKt+uXLVbbluHwmDwPR4rFUHaJTIohHyxxdjbKxlB9flwLxvoP0Mfw3w=").equals(this.firstNonBlank(stringArray).toLowerCase(Locale.ROOT))) {
                n = Xqedii_1as73k7.Xqedii_1nc45iibavbym(-2013619400, 12);
                return n != 0;
            }
        }
        n = Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 17);
        return n != 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean isTimelineRotationKeyframeSelected(EditorSession editorSession) {
        int n;
        if (this.isTimelineKeyframeSelected(editorSession)) {
            String[] stringArray = new String[Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(-1721094134, 3)];
            stringArray[Xqedii_1as73k7.Xqedii_1nc45iibavbym((int)1506033620, (int)31)] = editorSession.animationTimelineSelectedRow;
            if (Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("uq2o1ZeZ1SXeJrEar6F7Qc3WXuUCPiQtoT2q1YJ/IwBAvPygNQ3aENGo7ME=").equals(this.firstNonBlank(stringArray).toLowerCase(Locale.ROOT))) {
                n = Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(-1449745664, 31);
                return n != 0;
            }
        }
        n = Xqedii_1as73k7.Xqedii_1loegwk964oc9(349187188, 29);
        return n != 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean isTimelineScaleKeyframeSelected(EditorSession editorSession) {
        int n;
        if (this.isTimelineKeyframeSelected(editorSession)) {
            String[] stringArray = new String[Xqedii_1as73k7.Xqedii_1nc45iibavbym(948435611, 4)];
            stringArray[Xqedii_1as73k7.Xqedii_1nc45iibavbym((int)1896871190, (int)5)] = editorSession.animationTimelineSelectedRow;
            if (Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Yuhvm1cSTphzxDbbHs566nYwbtX/8qVwWFRxiBW3jE6WkjvVqR4=").equals(this.firstNonBlank(stringArray).toLowerCase(Locale.ROOT))) {
                n = Xqedii_1as73k7.Xqedii_1loegwk964oc9(-1798296460, 31);
                return n != 0;
            }
        }
        n = Xqedii_1as73k7.Xqedii_6s96e0m7981d(-769534789, 20);
        return n != 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean isTimelineOpacityKeyframeSelected(EditorSession editorSession) {
        int n;
        if (this.isTimelineKeyframeSelected(editorSession)) {
            String[] stringArray = new String[Xqedii_1as73k7.Xqedii_3hkrzn3ojt0q7(-883851180, 6)];
            stringArray[Xqedii_1as73k7.Xqedii_6s96e0m7981d((int)-769534789, (int)14)] = editorSession.animationTimelineSelectedRow;
            if (Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("KRO/QZ2KYPLBnp4CFACmqRNJHyYzpB082v0/d/Z5ZWNSFs1YILxtzL+x").equals(this.firstNonBlank(stringArray).toLowerCase(Locale.ROOT))) {
                n = Xqedii_1as73k7.Xqedii_6s96e0m7981d(769534788, 29);
                return n != 0;
            }
        }
        n = Xqedii_1as73k7.Xqedii_1nc45iibavbym(-1282900056, 0);
        return n != 0;
    }

    public EditorTool resolveTimelineSelectedTool(EditorSession editorSession) {
        if (((902696631 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
        }
        if (editorSession == null || editorSession.activeTool != EditorTool.ANIMATION) {
            return editorSession == null ? EditorTool.ANIMATION : editorSession.activeTool;
        }
        if (this.isTimelinePositionKeyframeSelected(editorSession)) {
            return EditorTool.CURSOR;
        }
        if (this.isTimelineScaleKeyframeSelected(editorSession)) {
            return EditorTool.SCALE;
        }
        return EditorTool.ANIMATION;
    }

    static {
        Xqedii_201v320 = Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("+nJ3EOvEQPo/vdURsn/76ymhhdPKYzRPWVvWA5Z0OBMA46c0+Zw+AD+hoNxiP60KIhSmZsawzO/TtdLwpvQCBA==");
        Xqedii_105z1dg = Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("najTJ0atunM6RvJDLFloqGJF2m3/2TDdCTGTg4RiNEa7CRQVh2m2lfAb0e3ZOmxKYzwauLFQSYyWu03lWxwWKYQG");
        Xqedii_2tuok81 = Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("f11AwZRAgwS6X4H9msukraNIhCa3bF/rXKHSt9SOInXcDoGqHyRiBXLQBRWOrTmmILRdnBAN/MwhOATfSSYO0/xM/AX/FvCS");
        Xqedii_35ze0h3 = Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("pfVK3DbTSGlqeXJq3K0uKHrFgGeO3gXY9htd7XVKJuoxJzfGPrykoc9AK7CbLCea7Zpppvih3d5aY7VvO2UF0RfToRWW2psx");
        Xqedii_1wzaauo = Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("KRO/QZ2KYPLBnp4CFACmqRNJHyYzpB082v0/d/Z5ZWNSFs1YILxtzL+x");
        Xqedii_3i4ovcs = Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Yuhvm1cSTphzxDbbHs566nYwbtX/8qVwWFRxiBW3jE6WkjvVqR4=");
        Xqedii_3spf4li = Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("qAplKt+uXLVbbluHwmDwPR4rFUHaJTIohHyxxdjbKxlB9flwLxvoP0Mfw3w=");
        Xqedii_3qmm41n = Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("uq2o1ZeZ1SXeJrEar6F7Qc3WXuUCPiQtoT2q1YJ/IwBAvPygNQ3aENGo7ME=");
        Xqedii_1q2tuvi = Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("RSI/nYuUT0TLRB461JBfbMRY1BuM7XnYi5kEhA0c7twh7V8O+rI=");
        if ((0x81C82F ^ 0x81C82F) != 0) {
            throw new IllegalStateException();
        }
        Xqedii_19udyqn = Map.of(Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("uq2o1ZeZ1SXeJrEar6F7Qc3WXuUCPiQtoT2q1YJ/IwBAvPygNQ3aENGo7ME="), Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("pfVK3DbTSGlqeXJq3K0uKHrFgGeO3gXY9htd7XVKJuoxJzfGPrykoc9AK7CbLCea7Zpppvih3d5aY7VvO2UF0RfToRWW2psx"), Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("qAplKt+uXLVbbluHwmDwPR4rFUHaJTIohHyxxdjbKxlB9flwLxvoP0Mfw3w="), Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("f11AwZRAgwS6X4H9msukraNIhCa3bF/rXKHSt9SOInXcDoGqHyRiBXLQBRWOrTmmILRdnBAN/MwhOATfSSYO0/xM/AX/FvCS"), Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("Yuhvm1cSTphzxDbbHs566nYwbtX/8qVwWFRxiBW3jE6WkjvVqR4="), Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("najTJ0atunM6RvJDLFloqGJF2m3/2TDdCTGTg4RiNEa7CRQVh2m2lfAb0e3ZOmxKYzwauLFQSYyWu03lWxwWKYQG"), Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("KRO/QZ2KYPLBnp4CFACmqRNJHyYzpB082v0/d/Z5ZWNSFs1YILxtzL+x"), Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("+nJ3EOvEQPo/vdURsn/76ymhhdPKYzRPWVvWA5Z0OBMA46c0+Zw+AD+hoNxiP60KIhSmZsawzO/TtdLwpvQCBA=="));
    }

    private static /* synthetic */ String Xqedii_2sij5hvl2g9da(String string) {
        return string + Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("rGyH+GuH8O+jntzL7AsFCleedDeSWY/SrN/Oi+LXeKWRTH3+BkUTylte");
    }

    private static /* synthetic */ String Xqedii_5s7jjrwr5lhw(String string) {
        return string + Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("rGyH+GuH8O+jntzL7AsFCleedDeSWY/SrN/Oi+LXeKWRTH3+BkUTylte");
    }

    private static /* synthetic */ String Xqedii_xei8k9ia2xpk(String string) {
        return string + Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("rGyH+GuH8O+jntzL7AsFCleedDeSWY/SrN/Oi+LXeKWRTH3+BkUTylte");
    }

    private static /* synthetic */ String Xqedii_3uhbauq0hxlx5(String string) {
        return string + Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("rGyH+GuH8O+jntzL7AsFCleedDeSWY/SrN/Oi+LXeKWRTH3+BkUTylte");
    }

    private static /* synthetic */ String Xqedii_1jobhs3gm6vt9(String string) {
        return string + Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("rGyH+GuH8O+jntzL7AsFCleedDeSWY/SrN/Oi+LXeKWRTH3+BkUTylte");
    }

    private static /* synthetic */ String Xqedii_1qf3dtzmkds82(String string) {
        return string + Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("FgBLMWkiKJklXxlmTd0WsIvR7PvGpx+NziqjrPQQ0KnW/BOpqnc=");
    }

    private static /* synthetic */ String Xqedii_1w4wl371eocp8(String string) {
        return string + Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("FgBLMWkiKJklXxlmTd0WsIvR7PvGpx+NziqjrPQQ0KnW/BOpqnc=");
    }

    private static /* synthetic */ String Xqedii_1ojx8qsaenjvb(String string) {
        return string + Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("FgBLMWkiKJklXxlmTd0WsIvR7PvGpx+NziqjrPQQ0KnW/BOpqnc=");
    }

    private static /* synthetic */ String Xqedii_bktucmivshct(String string) {
        return string + Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("FgBLMWkiKJklXxlmTd0WsIvR7PvGpx+NziqjrPQQ0KnW/BOpqnc=");
    }

    private static /* synthetic */ String Xqedii_1b0prt5chdndx(String string) {
        return string + Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("FgBLMWkiKJklXxlmTd0WsIvR7PvGpx+NziqjrPQQ0KnW/BOpqnc=");
    }

    private static /* synthetic */ String Xqedii_ujny9o13mda7(String string, int n) {
        return string + Xqedii_1as73k7.Xqedii_3sg3wlcdsipy0("V8HdadbcYVccHhYjjSPwr1zyzqHbI1jJ5UaJYxpc") + n;
    }

    public record AnimationTimelineKeyframeRef(String rowKey, int tick) {
        public static final String Xqedii_2bz6ch5d3hhxs = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";
        private static transient /* synthetic */ String FPBQeohXBT = "00000000000000000000000000000000000000000000";



        

        

        

        public String rowKey() {
            if (((-2081065366 << 1 | 1) & 1) == 0) {
                throw new IllegalStateException();
            }
            return this.rowKey;
        }

        public int tick() {
            if (((-1501312741 << 1 | 1) & 1) == 0) {
                throw new IllegalStateException();
            }
            return this.tick;
        }
    }
}

