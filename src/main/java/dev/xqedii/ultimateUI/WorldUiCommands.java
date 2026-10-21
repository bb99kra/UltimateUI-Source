package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.gui.WorldUiPart;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class WorldUiCommands extends CommandArguments {
   public static final List<String> Xqedii_1rrce4w;
   public static final List<String> Xqedii_3uuzz0v;
   public static final int Xqedii_2gk10es = -2;
   public static final int Xqedii_iwoenvc = -1;
   public static final int Xqedii_16fahzo = 0;
   public static final int Xqedii_aje2tsv = 1;

   public boolean handleUuiWorldCommand(CommandSender var1, String[] var2) {
      if (!this.hasCommandPermission(
         var1,
         "ultimateui.command.world",
         "/uui world"
      )) {
         return true;
      } else if (this.Xqedii_2k1vujp == null) {
         this.sendUuiServicesError(var1);
         return true;
      } else {
         String var10000;
         if (var2.length >= 2) {
            String[] var10001 = new String[1];
            var10001[0] = var2[1];
            var10000 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
         } else {
            var10000 = "help";
         }

         String var3 = var10000;
         int var5 = -1;
         switch (var3.hashCode()) {
            case -1360201941:
               if (var3.equals("teleport")) {
                  var5 = 24;
               }
               break;
            case -1352294148:
               if (var3.equals("create")) {
                  var5 = 4;
               }
               break;
            case -1335458389:
               if (var3.equals("delete")) {
                  var5 = 7;
               }
               break;
            case -1289860719:
               if (var3.equals("per-player")) {
                  var5 = 16;
               }
               break;
            case -1097095406:
               if (var3.equals("lookat")) {
                  var5 = 12;
               }
               break;
            case -934641255:
               if (var3.equals("reload")) {
                  var5 = 25;
               }
               break;
            case -934610812:
               if (var3.equals("remove")) {
                  var5 = 6;
               }
               break;
            case -925180581:
               if (var3.equals("rotate")) {
                  var5 = 11;
               }
               break;
            case -868304044:
               if (var3.equals("toggle")) {
                  var5 = 20;
               }
               break;
            case -103826623:
               if (var3.equals("movehere")) {
                  var5 = 8;
               }
               break;
            case 63:
               if (var3.equals("?")) {
                  var5 = 1;
               }
               break;
            case 3708:
               if (var3.equals("tp")) {
                  var5 = 23;
               }
               break;
            case 96417:
               if (var3.equals("add")) {
                  var5 = 5;
               }
               break;
            case 3198785:
               if (var3.equals("help")) {
                  var5 = 0;
               }
               break;
            case 3198960:
               if (var3.equals("here")) {
                  var5 = 9;
               }
               break;
            case 3202370:
               if (var3.equals("hide")) {
                  var5 = 21;
               }
               break;
            case 3322014:
               if (var3.equals("list")) {
                  var5 = 2;
               }
               break;
            case 3357091:
               if (var3.equals("mode")) {
                  var5 = 19;
               }
               break;
            case 3357649:
               if (var3.equals("move")) {
                  var5 = 10;
               }
               break;
            case 3529469:
               if (var3.equals("show")) {
                  var5 = 22;
               }
               break;
            case 3530753:
               if (var3.equals("size")) {
                  var5 = 17;
               }
               break;
            case 106748167:
               if (var3.equals("place")) {
                  var5 = 3;
               }
               break;
            case 113126854:
               if (var3.equals("width")) {
                  var5 = 18;
               }
               break;
            case 349730337:
               if (var3.equals("look-at")) {
                  var5 = 13;
               }
               break;
            case 764166718:
               if (var3.equals("perplayer")) {
                  var5 = 15;
               }
               break;
            case 1844104930:
               if (var3.equals("interactive")) {
                  var5 = 14;
               }
         }

         switch (var5) {
            case 0:
            case 1:
               this.sendUuiWorldHelp(var1);
               break;
            case 2:
               this.sendWorldUiList(var1);
               break;
            case 3:
            case 4:
            case 5:
               this.handleWorldUiPlace(var1, var2);
               break;
            case 6:
            case 7:
               this.handleWorldUiRemove(var1, var2);
               break;
            case 8:
            case 9:
               this.handleWorldUiMoveHere(var1, var2);
               break;
            case 10:
               this.handleWorldUiMove(var1, var2);
               break;
            case 11:
               this.handleWorldUiRotate(var1, var2);
               break;
            case 12:
            case 13:
               this.handleWorldUiLookAt(var1, var2);
               break;
            case 14:
               this.handleWorldUiInteractive(var1, var2);
               break;
            case 15:
            case 16:
               this.handleWorldUiPerPlayer(var1, var2);
               break;
            case 17:
            case 18:
               this.handleWorldUiSize(var1, var2);
               break;
            case 19:
               this.handleWorldUiMode(var1, var2);
               break;
            case 20:
            case 21:
            case 22:
               this.handleWorldUiToggle(var1, var2, var3);
               break;
            case 23:
            case 24:
               this.handleWorldUiTeleport(var1, var2);
               break;
            case 25:
               int var6 = this.Xqedii_2k1vujp.reloadWorldUis();
               String var7 = "Reloaded {0} world UIs!";
               Object[] var8 = new Object[1];
               var8[0] = var6;
               Xqedii_3ul4hqj.Xqedii_2pfjv2g(var1, var7, var8);
               break;
            default:
               String var10002 = "Unknown option {0}! Use {1} to see all options.";
               Object[] var10003 = new Object[2];
               var10003[0] = var2[1];
               var10003[1] = "/uui world help";
               this.sendUuiCommandError(var1, var10002, var10003);
         }

         return true;
      }
   }

   public void sendUuiWorldHelp(CommandSender var1) {
      {
         Component[] var10001 = new Component[17];
         var10001[0] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d21\u1d0f\u0280\u029f\u1d05 \u1d18\u029f\u1d00\u1d04\u1d07 <#ffbe54>[\u1d18\u1d00\u0262\u1d07] [\u026a\u1d05] <#8a989c>\u00bb <gray>Spawn new UI"
         );
         var10001[1] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d21\u1d0f\u0280\u029f\u1d05 \u029f\u026as\u1d1b <#8a989c>\u00bb <gray>List of UIs"
         );
         var10001[2] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d21\u1d0f\u0280\u029f\u1d05 \u1d0d\u1d0f\u1d20\u1d07\u029c\u1d07\u0280\u1d07 <#ffbe54>[\u026a\u1d05] <#8a989c>\u00bb <gray>Moves UI"
         );
         var10001[3] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d21\u1d0f\u0280\u029f\u1d05 \u1d0d\u1d0f\u1d20\u1d07 <#ffbe54>[\u026a\u1d05] [x] [\u028f] [\u1d22] <#8a989c>\u00bb <gray>Move UI by X, Y, Z"
         );
         var10001[4] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d21\u1d0f\u0280\u029f\u1d05 \u0280\u1d0f\u1d1b\u1d00\u1d1b\u1d07 <#ffbe54>[\u026a\u1d05] [\u028f\u1d00\u1d21] <#8a989c>\u00bb <gray>Rotate UI"
         );
         var10001[5] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d21\u1d0f\u0280\u029f\u1d05 \u029f\u1d0f\u1d0f\u1d0b\u1d00\u1d1b <#ffbe54>[\u026a\u1d05] [\u1d0f\u0274|\u1d0f\ua730\ua730|\u1d20\u1d07\u0280\u1d1b\u026a\u1d04\u1d00\u029f] <#8a989c>\u00bb <gray>Make UI Look at player"
         );
         var10001[6] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d21\u1d0f\u0280\u029f\u1d05 \u026a\u0274\u1d1b\u1d07\u0280\u1d00\u1d04\u1d1b\u026a\u1d20\u1d07 <#ffbe54>[\u026a\u1d05] [\u1d0f\u0274|\u1d0f\ua730\ua730] <#8a989c>\u00bb <gray>Make UI Interactive"
         );
         var10001[7] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d21\u1d0f\u0280\u029f\u1d05 \u1d18\u1d07\u0280\u1d18\u029f\u1d00\u028f\u1d07\u0280 <#ffbe54>[\u026a\u1d05] [\u1d0f\u0274|\u1d0f\ua730\ua730] <#8a989c>\u00bb <gray>UI per player"
         );
         var10001[8] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d21\u1d0f\u0280\u029f\u1d05 s\u026a\u1d22\u1d07 <#ffbe54>[\u026a\u1d05] [\u0299\u029f\u1d0f\u1d04\u1d0bs] <#8a989c>\u00bb <gray>Chance UI Size"
         );
         var10001[9] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d21\u1d0f\u0280\u029f\u1d05 \u1d0d\u1d0f\u1d05\u1d07 <#ffbe54>[\u026a\u1d05] [s\u1d1b\u1d00\u1d1b\u026a\u1d04|\ua730\u1d0f\u029f\u029f\u1d0f\u1d21] <#8a989c>\u00bb <gray>Change UI mode"
         );
         var10001[10] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d21\u1d0f\u0280\u029f\u1d05 \u1d1b\u1d0f\u0262\u0262\u029f\u1d07 <#ffbe54>[\u026a\u1d05] <#8a989c>\u00bb <gray>Toggle UI visibility"
         );
         var10001[11] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d21\u1d0f\u0280\u029f\u1d05 \u0280\u1d07\u1d0d\u1d0f\u1d20\u1d07 <#ffbe54>[\u026a\u1d05] <#8a989c>\u00bb <gray>Remove UI"
         );
         var10001[12] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d21\u1d0f\u0280\u029f\u1d05 \u1d1b\u1d18 <#ffbe54>[\u026a\u1d05] <#8a989c>\u00bb <gray>Teleports to UI"
         );
         var10001[13] = Xqedii_1df0pdx.deserialize(
            "<white>/\u1d1c\u1d1c\u026a \u1d21\u1d0f\u0280\u029f\u1d05 \u0280\u1d07\u029f\u1d0f\u1d00\u1d05 <#8a989c>\u00bb <gray>Reloads UIs"
         );
         var10001[14] = Component.empty();
         var10001[15] = Xqedii_1df0pdx.deserialize(
            "<white>\u0274\u1d07\u1d07\u1d05 \u029c\u1d07\u029f\u1d18 \u1d21\u026a\u1d1b\u029c \u1d1b\u029c\u1d07 \u1d18\u029f\u1d1c\u0262\u026a\u0274? \u1d04\u029c\u1d07\u1d04\u1d0b \u1d0f\u1d1c\u1d1b \u1d0f\u1d1c\u0280"
         );
         var10001[16] = Xqedii_1df0pdx.deserialize(
            "<white>\u1d05\u1d0f\u1d04\u1d1c\u1d0d\u1d07\u0274\u1d1b\u1d00\u1d1b\u026a\u1d0f\u0274 \u1d00\u1d1b <yellow><hover:show_text:'<green>Click here to open!'><click:open_url:'https://docs.xqedii.dev/'>https://docs.xqedii.dev/</click></hover><reset>"
         );
         Xqedii_3ul4hqj.send(var1, List.of(var10001));
      }
   }

   public void sendWorldUiList(CommandSender var1) {
      {
         List var2 = this.Xqedii_2k1vujp.listWorldUis();
         if (var2.isEmpty()) {
            String var10001 = "No UIs are placed in the world yet! Use {0} to place one.";
            Object[] var10002 = new Object[1];
            var10002[0] = "/uui world place [page]";
            Xqedii_3ul4hqj.Xqedii_2dtmver(var1, var10001, var10002);
         } else {
            ArrayList var3 = new ArrayList();

            for (WorldUiPart.WorldUiInfo var5 : (Iterable<WorldUiPart.WorldUiInfo>) (var2)) {

               String var6 = "static".equals(var5.mode())
                  ? Xqedii_3ul4hqj.Xqedii_1u4gly2(var5.location())
                  : "follows players";
               var3.add(
                  Xqedii_38nv8jyc47ufz(
                     Xqedii_3ul4hqj.Xqedii_1u4gly2(var5.name()),
                     Xqedii_3ul4hqj.Xqedii_1u4gly2(var5.page()),
                     var5.enabled()
                        ? "shown"
                        : "hidden",
                     var5.interactive()
                        ? (
                           var5.perPlayer()
                              ? "interactive per player"
                              : "interactive shared"
                        )
                        : "display only",
                     Xqedii_3ul4hqj.Xqedii_1u4gly2(var5.lookAt()),
                     var6
                  )
               );
            }

            Xqedii_3ul4hqj.Xqedii_3crurrx(var1, "World UIs", var3);
         }
      }
   }

   public void handleWorldUiPlace(CommandSender var1, String[] var2) {
      if (!(var1 instanceof Player var3)) {
         this.sendUuiCommandError(
            var1,
            "This command is for players only!",
            new Object[0]
         );
      } else {
         if (var2.length >= 3) {
            String[] var10001 = new String[1];
            var10001[0] = var2[2];
            if (!this.firstNonBlank(var10001).isBlank()) {
               String var4 = var2[2].trim();
               String var5 = var4.toLowerCase(Locale.ROOT);
               String var6 = !var5.equals("editor")
                     && !var5.equals("editor_menu")
                  ? this.resolveExistingUiName(var4)
                  : "";
               if (var6.isBlank()) {
                  String var14 = "UI {0} does not exist!";
                  Object[] var19 = new Object[1];
                  var19[0] = var4;
                  this.sendUuiCommandError(var1, var14, var19);
                  return;
               }

               String var7;
               if (var2.length >= 4) {
                  var7 = var2[3].trim().toLowerCase(Locale.ROOT);
                  if (!WorldUiPart.isValidWorldUiId(var7)) {
                     String var10 = "The id can only use {0} and up to 48 characters!";
                     Object[] var15 = new Object[1];
                     var15[0] = "a-z 0-9 _ -";
                     this.sendUuiCommandError(var1, var10, var15);
                     return;
                  }

                  if (this.Xqedii_2k1vujp.hasWorldUi(var7)) {
                     String var10002 = "World UI {0} already exists!";
                     Object[] var10003 = new Object[1];
                     var10003[0] = var7;
                     this.sendUuiCommandError(var1, var10002, var10003);
                     return;
                  }
               } else {
                  var7 = this.Xqedii_2k1vujp.nextWorldUiId();
               }

               String var8 = this.Xqedii_2k1vujp.placeWorldUi(var3, var6, var7);
               if (var8 == null) {
                  String var13 = "Could not place {0}! Check the console for details.";
                  Object[] var18 = new Object[1];
                  var18[0] = var6;
                  this.sendUuiCommandError(var1, var13, var18);
                  return;
               }

               int var9 = 1;
               String var11 = "Placed {0} in the world!";
               Object[] var16 = new Object[1];
               var16[0] = var6;
               var11 = Xqedii_3ul4hqj.Xqedii_2d5sc5r(var11, var16);
               String var17 = "Saved as ID {0}";
               Object[] var10004 = new Object[1];
               var10004[0] = var8;
               Xqedii_3ul4hqj.Xqedii_2g7xq9b(
                  var1,
                  (var9 != 0),
                  var11,
                  List.of(
                     Xqedii_3ul4hqj.Xqedii_2d5sc5r(var17, var10004),
                     "Adjust it with <#d1ff96>/uui world help"
                  )
               );
               return;
            }
         }

         this.sendUuiUsage(
            var1,
            "/uui world place",
            "[page] [id]"
         );
      }
   }

   public void handleWorldUiRemove(CommandSender var1, String[] var2) {
      {
         String var3 = this.requireWorldUiId(
            var1, var2, "/uui world remove [id]"
         );
         if (var3 != null) {
            if (!this.Xqedii_2k1vujp.removeWorldUi(var3)) {
               String var4 = "Could not delete the file of {0}!";
               Object[] var10003 = new Object[1];
               var10003[0] = var3;
               this.sendUuiCommandError(var1, var4, var10003);
            } else {
               String var10001 = "World UI {0} has been removed!";
               Object[] var10002 = new Object[1];
               var10002[0] = var3;
               Xqedii_3ul4hqj.Xqedii_3458djb(var1, var10001, var10002);
            }
         }
      }
   }

   public void handleWorldUiMoveHere(CommandSender var1, String[] var2) {
      if (var1 instanceof Player var3) {
         String var4 = this.requireWorldUiId(
            var1,
            var2,
            "/uui world movehere [id]"
         );
         if (var4 != null) {
            if (!this.Xqedii_2k1vujp.moveWorldUiHere(var3, var4)) {
               String var6 = "Could not move {0}!";
               Object[] var7 = new Object[1];
               var7[0] = var4;
               this.sendUuiCommandError(var1, var6, var7);
            } else {
               String var10001 = "World UI {0} has been moved in front of you!";
               Object[] var5 = new Object[1];
               var5[0] = var4;
               Xqedii_3ul4hqj.Xqedii_2pfjv2g(var1, var10001, var5);
            }
         }
      } else {
         String var10002 = "This command is for players only! Use {0} instead.";
         Object[] var10003 = new Object[1];
         var10003[0] = "/uui world move";
         this.sendUuiCommandError(var1, var10002, var10003);
      }
   }

   public void handleWorldUiMove(CommandSender var1, String[] var2) {
      {
         String var3 = this.requireWorldUiId(
            var1,
            var2,
            "/uui world move [id] [x] [y] [z]"
         );
         if (var3 != null) {
            if (var2.length != 6) {
               this.sendUuiUsage(
                  var1, Xqedii_ix16dvmxyaik(var3), "[x] [y] [z]"
               );
            } else {
               Location var4 = this.Xqedii_2k1vujp.getWorldUiLocation(var3);
               World var5 = var4 == null ? null : var4.getWorld();
               if (var5 == null && var1 instanceof Player var6) {
                  var4 = var6.getLocation();
                  var5 = var6.getWorld();
               }

               if (var5 == null) {
                  String var15 = "World UI {0} has no location yet! Use {1} first.";
                  Object[] var17 = new Object[2];
                  var17[0] = var3;
                  var17[1] = Xqedii_28xquck5gx92v(var3);
                  this.sendUuiCommandError(var1, var15, var17);
               } else {
                  double var12 = Xqedii_27rdo84(var2[3], var4.getX());
                  double var8 = Xqedii_27rdo84(var2[4], var4.getY());
                  double var10 = Xqedii_27rdo84(var2[5], var4.getZ());
                  if (!Double.isFinite(var12) || !Double.isFinite(var8) || !Double.isFinite(var10)) {
                     String var14 = "Coordinates must be numbers or {0} values!";
                     Object[] var16 = new Object[1];
                     var16[0] = "~";
                     this.sendUuiCommandError(var1, var14, var16);
                  } else if (!this.Xqedii_2k1vujp.moveWorldUi(var3, var5, var12, var8, var10)) {
                     String var13 = "Could not move {0}!";
                     Object[] var10003 = new Object[1];
                     var10003[0] = var3;
                     this.sendUuiCommandError(var1, var13, var10003);
                  } else {
                     String var10001 = "World UI {0} has been moved to {1}!";
                     Object[] var10002 = new Object[2];
                     var10002[0] = var3;
                     var10002[1] = Xqedii_101gcmngr2rmr(
                        Xqedii_v420beu(var12), Xqedii_v420beu(var8), Xqedii_v420beu(var10)
                     );
                     Xqedii_3ul4hqj.Xqedii_2pfjv2g(var1, var10001, var10002);
                  }
               }
            }
         }
      }
   }

   public void handleWorldUiRotate(CommandSender var1, String[] var2) {
      {
         String var3 = this.requireWorldUiId(
            var1,
            var2,
            "/uui world rotate [id] [yaw]"
         );
         if (var3 != null) {
            if (var2.length != 4) {
               this.sendUuiUsage(var1, Xqedii_2jpyit8m8ri64(var3), "[yaw]");
            } else {
               Location var4 = this.Xqedii_2k1vujp.getWorldUiLocation(var3);
               double var5 = Xqedii_27rdo84(
                  var2[3],
                  var4 == null ? Double.longBitsToDouble(0L) : var4.getYaw()
               );
               if (!Double.isFinite(var5)) {
                  String var13 = "The yaw must be a number or a {0} value!";
                  Object[] var15 = new Object[1];
                  var15[0] = "~";
                  this.sendUuiCommandError(var1, var13, var15);
               } else if (!this.Xqedii_2k1vujp.rotateWorldUi(var3, var5)) {
                  String var12 = "Could not rotate {0}!";
                  Object[] var14 = new Object[1];
                  var14[0] = var3;
                  this.sendUuiCommandError(var1, var12, var14);
               } else {
                  Location var7 = this.Xqedii_2k1vujp.getWorldUiLocation(var3);
                  double var8 = var7 == null ? var5 : var7.getYaw();
                  if (this.Xqedii_2k1vujp.getWorldUiLookAt(var3) == 0) {
                     String var10001 = "World UI {0} now faces yaw {1}!";
                     Object[] var10002 = new Object[2];
                     var10002[0] = var3;
                     var10002[1] = Xqedii_v420beu(var8);
                     Xqedii_3ul4hqj.Xqedii_2pfjv2g(var1, var10001, var10002);
                  } else {
                     int var10 = 1;
                     String var11 = "World UI {0} now faces yaw {1}!";
                     Object[] var10003 = new Object[2];
                     var10003[0] = var3;
                     var10003[1] = Xqedii_v420beu(var8);
                     Xqedii_3ul4hqj.Xqedii_2g7xq9b(
                        var1,
                        (var10 != 0),
                        Xqedii_3ul4hqj.Xqedii_2d5sc5r(var11, var10003),
                        List.of(
                           "Look-at is on, so it still turns to the player"
                        )
                     );
                  }
               }
            }
         }
      }
   }

   public void handleWorldUiLookAt(CommandSender var1, String[] var2) {
      {
         String var3 = this.requireWorldUiId(
            var1,
            var2,
            "/uui world lookat [id] [on|off|vertical]"
         );
         if (var3 != null) {
            int var4 = this.Xqedii_2k1vujp.getWorldUiLookAt(var3);
            int var5;
            if (var2.length >= 4) {
               String[] var10001 = new String[1];
               var10001[0] = var2[3];
               String var6 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
               if (var6.equals("toggle")) {
                  var5 = var4 == 0 ? 3 : 0;
               } else {
                  if (!var6.equals("on")
                     && !var6.equals("off")
                     && !var6.equals("vertical")
                     && !var6.equals("true")
                     && !var6.equals("false")) {
                     String var10002 = "Unknown value {0}! Use on, off or vertical.";
                     Object[] var10003 = new Object[1];
                     var10003[0] = var2[3];
                     this.sendUuiCommandError(var1, var10002, var10003);
                     return;
                  }

                  var5 = WorldUiPart.parseWorldUiLookAt(var6);
               }
            } else {
               var5 = var4 == 0 ? 3 : 0;
            }

            if (!this.Xqedii_2k1vujp.setWorldUiLookAt(var3, var5)) {
               String var13 = "Could not change {0}!";
               Object[] var14 = new Object[1];
               var14[0] = var3;
               this.sendUuiCommandError(var1, var13, var14);
            } else {
               if (var5 == 3) {
                  String var7 = "World UI {0} now turns to face the player!";
                  Object[] var10 = new Object[1];
                  var10[0] = var3;
                  Xqedii_3ul4hqj.Xqedii_2pfjv2g(var1, var7, var10);
               } else if (var5 == 1) {
                  String var8 = "World UI {0} now turns left and right to the player!";
                  Object[] var11 = new Object[1];
                  var11[0] = var3;
                  Xqedii_3ul4hqj.Xqedii_2pfjv2g(var1, var8, var11);
               } else {
                  String var9 = "World UI {0} no longer turns to the player!";
                  Object[] var12 = new Object[1];
                  var12[0] = var3;
                  Xqedii_3ul4hqj.Xqedii_3458djb(var1, var9, var12);
               }
            }
         }
      }
   }

   public void handleWorldUiInteractive(CommandSender var1, String[] var2) {
      {
         String var3 = this.requireWorldUiId(
            var1,
            var2,
            "/uui world interactive [id] [on|off]"
         );
         if (var3 != null) {
            int var4 = var2.length >= 4
               ? Xqedii_35wdwsa(var2[3])
               : -1;
            if (var4 == -2) {
               String var9 = "Unknown value {0}! Use on or off.";
               Object[] var11 = new Object[1];
               var11[0] = var2[3];
               this.sendUuiCommandError(var1, var9, var11);
            } else {
               Boolean var5 = this.Xqedii_2k1vujp
                  .setWorldUiInteractive(
                     var3,
                     var4 == -1
                        ? null
                        : Boolean.valueOf(
                           ((var4 == 1
                              ? 1
                              : 0) != 0)
                        )
                  );
               if (var5 == null) {
                  String var8 = "Could not change {0}!";
                  Object[] var10 = new Object[1];
                  var10[0] = var3;
                  this.sendUuiCommandError(var1, var8, var10);
               } else {
                  if (var5) {
                     int var10001 = 1;
                     String var10002 = "World UI {0} is now interactive!";
                     Object[] var10003 = new Object[1];
                     var10003[0] = var3;
                     Xqedii_3ul4hqj.Xqedii_2g7xq9b(
                        var1,
                        (var10001 != 0),
                        Xqedii_3ul4hqj.Xqedii_2d5sc5r(var10002, var10003),
                        List.of(
                           "Look at it and hit to click, use the hotbar wheel to scroll"
                        )
                     );
                  } else {
                     String var6 = "World UI {0} is no longer interactive!";
                     Object[] var7 = new Object[1];
                     var7[0] = var3;
                     Xqedii_3ul4hqj.Xqedii_3458djb(var1, var6, var7);
                  }
               }
            }
         }
      }
   }

   public void handleWorldUiPerPlayer(CommandSender var1, String[] var2) {
      {
         String var3 = this.requireWorldUiId(
            var1,
            var2,
            "/uui world perplayer [id] [on|off]"
         );
         if (var3 != null) {
            int var4 = var2.length >= 4
               ? Xqedii_35wdwsa(var2[3])
               : -1;
            if (var4 == -2) {
               String var9 = "Unknown value {0}! Use on or off.";
               Object[] var12 = new Object[1];
               var12[0] = var2[3];
               this.sendUuiCommandError(var1, var9, var12);
            } else {
               Boolean var5 = this.Xqedii_2k1vujp
                  .setWorldUiPerPlayer(
                     var3,
                     var4 == -1
                        ? null
                        : Boolean.valueOf(
                           ((var4 == 1
                              ? 1
                              : 0) != 0)
                        )
                  );
               if (var5 == null) {
                  String var8 = "Could not change {0}!";
                  Object[] var11 = new Object[1];
                  var11[0] = var3;
                  this.sendUuiCommandError(var1, var8, var11);
               } else {
                  if (var5) {
                     int var10001 = 1;
                     String var10002 = "World UI {0} is now per player!";
                     Object[] var10003 = new Object[1];
                     var10003[0] = var3;
                     Xqedii_3ul4hqj.Xqedii_2g7xq9b(
                        var1,
                        (var10001 != 0),
                        Xqedii_3ul4hqj.Xqedii_2d5sc5r(var10002, var10003),
                        List.of(
                           "Every player scrolls and types in their own copy"
                        )
                     );
                  } else {
                     int var6 = 1;
                     String var7 = "World UI {0} is now shared!";
                     Object[] var10 = new Object[1];
                     var10[0] = var3;
                     Xqedii_3ul4hqj.Xqedii_2g7xq9b(
                        var1,
                        (var6 != 0),
                        Xqedii_3ul4hqj.Xqedii_2d5sc5r(var7, var10),
                        List.of(
                           "Scrolling or typing in it changes it for everyone"
                        )
                     );
                  }
               }
            }
         }
      }
   }

   public static int Xqedii_35wdwsa(String var0) {
      {
         String var1 = var0 == null ? "" : var0.trim().toLowerCase(Locale.ROOT);
         int var3 = -1;
         switch (var1.hashCode()) {
            case -1609594047:
               if (var1.equals("enabled")) {
                  var3 = 4;
               }
               break;
            case -1298848381:
               if (var1.equals("enable")) {
                  var3 = 3;
               }
               break;
            case -889473228:
               if (var1.equals("switch")) {
                  var3 = 11;
               }
               break;
            case -868304044:
               if (var1.equals("toggle")) {
                  var3 = 10;
               }
               break;
            case 3521:
               if (var1.equals("no")) {
                  var3 = 7;
               }
               break;
            case 3551:
               if (var1.equals("on")) {
                  var3 = 0;
               }
               break;
            case 109935:
               if (var1.equals("off")) {
                  var3 = 5;
               }
               break;
            case 119527:
               if (var1.equals("yes")) {
                  var3 = 2;
               }
               break;
            case 3569038:
               if (var1.equals("true")) {
                  var3 = 1;
               }
               break;
            case 97196323:
               if (var1.equals("false")) {
                  var3 = 6;
               }
               break;
            case 270940796:
               if (var1.equals("disabled")) {
                  var3 = 9;
               }
               break;
            case 1671308008:
               if (var1.equals("disable")) {
                  var3 = 8;
               }
         }
         return switch (var3) {
            case 0, 1, 2, 3, 4 -> 1;
            case 5, 6, 7, 8, 9 -> 0;
            case 10, 11 -> -1;
            default -> -2;
         };
      }
   }

   public void handleWorldUiSize(CommandSender var1, String[] var2) {
      {
         String var3 = this.requireWorldUiId(
            var1,
            var2,
            "/uui world size [id] [blocks]"
         );
         if (var3 != null) {
            if (var2.length != 4) {
               this.sendUuiUsage(
                  var1, Xqedii_1bbmhgl38amgn(var3), "[blocks]"
               );
            } else {
               double var4 = Xqedii_27rdo84(var2[3], this.Xqedii_2k1vujp.getWorldUiWidth(var3));
               if (!Double.isFinite(var4)
                  || var4 < Double.longBitsToDouble(4591870180066957722L)
                  || var4 > Double.longBitsToDouble(4634204016564240384L)) {
                  String var7 = "The width must be between {0} and {1} blocks!";
                  Object[] var8 = new Object[2];
                  var8[0] = Xqedii_v420beu(
                     Double.longBitsToDouble(4591870180066957722L)
                  );
                  var8[1] = Xqedii_v420beu(
                     Double.longBitsToDouble(4634204016564240384L)
                  );
                  this.sendUuiCommandError(var1, var7, var8);
               } else if (!this.Xqedii_2k1vujp.setWorldUiWidth(var3, var4)) {
                  String var6 = "Could not resize {0}!";
                  Object[] var10003 = new Object[1];
                  var10003[0] = var3;
                  this.sendUuiCommandError(var1, var6, var10003);
               } else {
                  String var10001 = "World UI {0} is now {1} blocks wide!";
                  Object[] var10002 = new Object[2];
                  var10002[0] = var3;
                  var10002[1] = Xqedii_v420beu(var4);
                  Xqedii_3ul4hqj.Xqedii_2pfjv2g(var1, var10001, var10002);
               }
            }
         }
      }
   }

   public void handleWorldUiMode(CommandSender var1, String[] var2) {
      {
         String var3 = this.requireWorldUiId(
            var1,
            var2,
            "/uui world mode [id] [static|follow]"
         );
         if (var3 != null) {
            String var10000;
            if (var2.length >= 4) {
               String[] var10001 = new String[1];
               var10001[0] = var2[3];
               var10000 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
            } else {
               var10000 = "";
            }

            String var4 = var10000;
            if (!var4.equals("static")
               && !var4.equals("follow")) {
               this.sendUuiUsage(
                  var1,
                  Xqedii_xajp865masqy(var3),
                  "[static|follow]"
               );
            } else {
               boolean var5 = var4.equals("follow");
               if (!var5 && !this.Xqedii_2k1vujp.hasWorldUiLocation(var3)) {
                  String var8 = "World UI {0} has no location yet! Use {1} first.";
                  Object[] var9 = new Object[2];
                  var9[0] = var3;
                  var9[1] = Xqedii_r0zd7zk3xa25(var3);
                  this.sendUuiCommandError(var1, var8, var9);
               } else if (!this.Xqedii_2k1vujp.setWorldUiMode(var3, var5)) {
                  String var7 = "Could not change {0}!";
                  Object[] var10003 = new Object[1];
                  var10003[0] = var3;
                  this.sendUuiCommandError(var1, var7, var10003);
               } else {
                  String var6 = var5
                     ? "World UI {0} now floats in front of every player!"
                     : "World UI {0} now stands in one place!";
                  Object[] var10002 = new Object[1];
                  var10002[0] = var3;
                  Xqedii_3ul4hqj.Xqedii_2pfjv2g(var1, var6, var10002);
               }
            }
         }
      }
   }

   public void handleWorldUiToggle(CommandSender var1, String[] var2, String var3) {
      {
         String var4 = this.requireWorldUiId(
            var1, var2, "/uui world toggle [id]"
         );
         if (var4 != null) {
            Boolean var5 = var3.equals("hide")
               ? Boolean.FALSE
               : (var3.equals("show") ? Boolean.TRUE : null);
            boolean var6 = var5 == null ? !this.Xqedii_2k1vujp.isWorldUiEnabled(var4) : var5.booleanValue();
            if (var6 && this.Xqedii_2k1vujp.isWorldUiStatic(var4) && !this.Xqedii_2k1vujp.hasWorldUiLocation(var4)) {
               String var11 = "World UI {0} has no location yet! Use {1} first.";
               Object[] var12 = new Object[2];
               var12[0] = var4;
               var12[1] = Xqedii_16wh7oanplfsi(var4);
               this.sendUuiCommandError(var1, var11, var12);
            } else {
               Boolean var7 = this.Xqedii_2k1vujp.setWorldUiEnabled(var4, var5);
               if (var7 == null) {
                  String var10 = "Could not change {0}!";
                  Object[] var10003 = new Object[1];
                  var10003[0] = var4;
                  this.sendUuiCommandError(var1, var10, var10003);
               } else {
                  if (var7) {
                     String var10001 = "World UI {0} is now shown!";
                     Object[] var10002 = new Object[1];
                     var10002[0] = var4;
                     Xqedii_3ul4hqj.Xqedii_2pfjv2g(var1, var10001, var10002);
                  } else {
                     String var8 = "World UI {0} is now hidden!";
                     Object[] var9 = new Object[1];
                     var9[0] = var4;
                     Xqedii_3ul4hqj.Xqedii_3458djb(var1, var8, var9);
                  }
               }
            }
         }
      }
   }

   public void handleWorldUiTeleport(CommandSender var1, String[] var2) {
      if (var1 instanceof Player var3) {
         String var4 = this.requireWorldUiId(
            var1, var2, "/uui world tp [id]"
         );
         if (var4 != null) {
            if (!this.Xqedii_2k1vujp.hasWorldUiLocation(var4)) {
               String var5 = "World UI {0} has no location to teleport to!";
               Object[] var6 = new Object[1];
               var6[0] = var4;
               this.sendUuiCommandError(var1, var5, var6);
            } else {
               if (!this.Xqedii_2k1vujp.teleportToWorldUi(var3, var4)) {
                  String var10002 = "Could not teleport to {0}!";
                  Object[] var10003 = new Object[1];
                  var10003[0] = var4;
                  this.sendUuiCommandError(var1, var10002, var10003);
               }
            }
         }
      } else {
         this.sendUuiCommandError(
            var1,
            "This command is for players only!",
            new Object[0]
         );
      }
   }

   public String requireWorldUiId(CommandSender var1, String[] var2, String var3) {
      {
         if (var2.length >= 3) {
            String[] var10001 = new String[1];
            var10001[0] = var2[2];
            if (!this.firstNonBlank(var10001).isBlank()) {
               String var5 = var2[2].trim().toLowerCase(Locale.ROOT);
               if (!this.Xqedii_2k1vujp.hasWorldUi(var5)) {
                  String var10002 = "World UI {0} does not exist!";
                  Object[] var10003 = new Object[1];
                  var10003[0] = var2[2];
                  this.sendUuiCommandError(var1, var10002, var10003);
                  return null;
               }

               return var5;
            }
         }

         int var4 = var3.indexOf(91);
         this.sendUuiUsage(
            var1,
            var4 > 0 ? var3.substring(0, var4).trim() : var3,
            var4 > 0 ? var3.substring(var4) : ""
         );
         return null;
      }
   }

   public static double Xqedii_27rdo84(String var0, double var1) {
      {
         String var3 = var0 == null ? "" : var0.trim();
         boolean var4 = var3.startsWith("~");
         String var5 = var4 ? var3.substring(1) : var3;
         if (var4 && var5.isEmpty()) {
            return var1;
         } else {
            try {
               double var6 = Double.parseDouble(
                  var5.replace((char)44, (char)46)
               );
               if (!Double.isFinite(var6)) {
                  return Double.longBitsToDouble(9221120237041090560L);
               } else {
                  return var4 ? var1 + var6 : var6;
               }
            } catch (NumberFormatException var8) {
               return Double.longBitsToDouble(9221120237041090560L);
            }
         }
      }
   }

   public static String Xqedii_v420beu(double var0) {
      {
         double var2 = Math.round(var0 * Double.longBitsToDouble(4636737291354636288L))
            / Double.longBitsToDouble(4636737291354636288L);
         return var2 == Math.rint(var2) ? String.valueOf((long)var2) : String.valueOf(var2);
      }
   }

   public List<String> completeUuiWorldCommand(String[] var1) {
      if (var1.length <= 2) {
         return this.filterByPrefix(
            Xqedii_1rrce4w,
            var1.length == 2
               ? var1[1]
               : ""
         );
      } else if (this.Xqedii_2k1vujp == null) {
         return List.of();
      } else {
         String[] var10001 = new String[1];
         var10001[0] = var1[1];
         String var2 = this.firstNonBlank(var10001).trim().toLowerCase(Locale.ROOT);
         if (var1.length == 3) {
            if (!var2.equals("place")
               && !var2.equals("create")
               && !var2.equals("add")) {
               return Xqedii_3uuzz0v.contains(var2)
                  ? this.filterByPrefix(this.Xqedii_2k1vujp.getWorldUiNames(), var1[2])
                  : List.of();
            } else {
               ArrayList var7 = new ArrayList();

               for (String var8 : this.Xqedii_2k1vujp.getGuiNames()) {
                  var10001 = new String[1];
                  var10001[0] = var8;
                  String var6 = this.firstNonBlank(var10001).toLowerCase(Locale.ROOT);
                  if (!var6.isBlank()
                     && !var6.equals("editor")
                     && !var6.equals("editor_menu")) {
                     var7.add(var8);
                  }
               }

               return this.filterByPrefix(var7, var1[2]);
            }
         } else {
            String var3 = var1[var1.length - 1];
            int var5 = -1;
            switch (var2.hashCode()) {
               case -1289860719:
                  if (var2.equals("per-player")) {
                     var5 = 4;
                  }
                  break;
               case -1097095406:
                  if (var2.equals("lookat")) {
                     var5 = 0;
                  }
                  break;
               case -925180581:
                  if (var2.equals("rotate")) {
                     var5 = 7;
                  }
                  break;
               case 3357091:
                  if (var2.equals("mode")) {
                     var5 = 5;
                  }
                  break;
               case 3357649:
                  if (var2.equals("move")) {
                     var5 = 6;
                  }
                  break;
               case 3530753:
                  if (var2.equals("size")) {
                     var5 = 8;
                  }
                  break;
               case 113126854:
                  if (var2.equals("width")) {
                     var5 = 9;
                  }
                  break;
               case 349730337:
                  if (var2.equals("look-at")) {
                     var5 = 1;
                  }
                  break;
               case 764166718:
                  if (var2.equals("perplayer")) {
                     var5 = 3;
                  }
                  break;
               case 1844104930:
                  if (var2.equals("interactive")) {
                     var5 = 2;
                  }
            }
            return switch (var5) {
               case 0, 1 -> var1.length == 4
                  ? this.filterByPrefix(
                     List.of(
                        "on",
                        "off",
                        "vertical"
                     ),
                     var3
                  )
                  : List.of();
               case 2, 3, 4 -> var1.length == 4
                  ? this.filterByPrefix(
                     List.of(
                        "on",
                        "off"
                     ),
                     var3
                  )
                  : List.of();
               case 5 -> var1.length == 4
                  ? this.filterByPrefix(
                     List.of(
                        "static",
                        "follow"
                     ),
                     var3
                  )
                  : List.of();
               case 6 -> var1.length <= 6
                  ? this.filterByPrefix(List.of("~"), var3)
                  : List.of();
               case 7 -> var1.length == 4
                  ? this.filterByPrefix(
                     List.of(
                        "~",
                        "0",
                        "90",
                        "180",
                        "-90",
                        "~90",
                        "~-90"
                     ),
                     var3
                  )
                  : List.of();
               case 8, 9 -> var1.length == 4
                  ? this.filterByPrefix(
                     List.of(
                        "1",
                        "2",
                        "3",
                        "4",
                        "6",
                        "8"
                     ),
                     var3
                  )
                  : List.of();
               default -> List.of();
            };
         }
      }
   }

   static {
      {
         String[] var10000 = new String[15];
         var10000[0] = "help";
         var10000[1] = "place";
         var10000[2] = "list";
         var10000[3] = "remove";
         var10000[4] = "movehere";
         var10000[5] = "move";
         var10000[6] = "rotate";
         var10000[7] = "lookat";
         var10000[8] = "interactive";
         var10000[9] = "perplayer";
         var10000[10] = "size";
         var10000[11] = "mode";
         var10000[12] = "toggle";
         var10000[13] = "tp";
         var10000[14] = "reload";
         Xqedii_1rrce4w = List.of(var10000);
         var10000 = new String[19];
         var10000[0] = "remove";
         var10000[1] = "delete";
         var10000[2] = "movehere";
         var10000[3] = "here";
         var10000[4] = "move";
         var10000[5] = "rotate";
         var10000[6] = "lookat";
         var10000[7] = "look-at";
         var10000[8] = "interactive";
         var10000[9] = "perplayer";
         var10000[10] = "per-player";
         var10000[11] = "size";
         var10000[12] = "width";
         var10000[13] = "mode";
         var10000[14] = "toggle";
         var10000[15] = "hide";
         var10000[16] = "show";
         var10000[17] = "tp";
         var10000[18] = "teleport";
         Xqedii_3uuzz0v = List.of(var10000);
      }
   }

   private static /* synthetic */ String Xqedii_38nv8jyc47ufz(String string, String string2, String string3, String string4, String string5, String string6) {
        return " <#8dfc00>" + string + " <#bbff63>" + string2 + " <dark_aqua>- <gray>" + string3 + ", " + string4 + ", look-at " + string5 + ", " + string6;
    }

    private static /* synthetic */ String Xqedii_ix16dvmxyaik(String string) {
        return "/uui world move " + string;
    }

    private static /* synthetic */ String Xqedii_28xquck5gx92v(String string) {
        return "/uui world movehere " + string;
    }

    private static /* synthetic */ String Xqedii_101gcmngr2rmr(String string, String string2, String string3) {
        return string + " " + string2 + " " + string3;
    }

    private static /* synthetic */ String Xqedii_2jpyit8m8ri64(String string) {
        return "/uui world rotate " + string;
    }

    private static /* synthetic */ String Xqedii_1bbmhgl38amgn(String string) {
        return "/uui world size " + string;
    }

    private static /* synthetic */ String Xqedii_xajp865masqy(String string) {
        return "/uui world mode " + string;
    }

    private static /* synthetic */ String Xqedii_r0zd7zk3xa25(String string) {
        return "/uui world movehere " + string;
    }

    private static /* synthetic */ String Xqedii_16wh7oanplfsi(String string) {
        return "/uui world movehere " + string;
    }
}
