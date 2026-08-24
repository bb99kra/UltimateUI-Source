package dev.xqedii.ultimateUI.integration.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.api.event.UltimateUIBlockClickEvent;
import org.bukkit.entity.Player;

public final class UltimateUiSkriptBridge {
   private static UltimateUI plugin;
   private static boolean registered;

   private UltimateUiSkriptBridge() {
   }

   public static void register(UltimateUI var0) {
      if (var0 != null && !registered) {
         plugin = var0;
         Skript.registerEffect(
            EffOpenUi.class,
            new String[]{
               "open ui %string% for %players%",
               "open ui %string% with hud for %players%",
               "open ui %string% with autoclose for %players%",
               "open ui %string% with autoclose and hud for %players%",
               "open ui %string% with hud and autoclose for %players%"
            }
         );
         Skript.registerEffect(
            EffCloseUi.class,
            new String[]{"close ui for %players%", "close ui for %player%", "close ui %string% for %players%", "close ui %string% for %player%"}
         );
         Skript.registerEffect(
            EffSetUiElement.class,
            new String[]{
               "set [ultimate[ ]ui] element [id] %string% text to %string% for %players%",
               "set [ultimate[ ]ui] element [id] %string% color to %string% for %players%",
               "set [ultimate[ ]ui] element [id] %string% position to %number%, %number% for %players%",
               "set [ultimate[ ]ui] element [id] %string% scale to %number%, %number% for %players%",
               "set [ultimate[ ]ui] element [id] %string% item to %string% for %players%",
               "set [ultimate[ ]ui] element [id] %string% path %string% to %string% for %players%",
               "set [ultimate[ ]ui] element [id] %string% path %string% to %number% for %players%",
               "set [ultimate[ ]ui] element [id] %string% path %string% to %boolean% for %players%"
            }
         );
         Skript.registerCondition(
            CondUltimateUiOpen.class,
            new String[]{
               "%players% (has|have) [a] [ultimate[ ]ui] ui open",
               "%players% (doesn't have|does not have|don't have|do not have) [a] [ultimate[ ]ui] ui open",
               "%players% (has|have) [ultimate[ ]ui] ui %string% open",
               "%players% (doesn't have|does not have|don't have|do not have) [ultimate[ ]ui] ui %string% open"
            }
         );
         Skript.registerEvent(
            "UltimateUI Click",
            EvtUltimateUiBlockClick.class,
            UltimateUIBlockClickEvent.class,
            new String[]{"[ultimate[ ]ui] block click", "[ultimate[ ]ui] click", "[ultimater[ ]ui] click"}
         );
         EventValues.registerEventValue(UltimateUIBlockClickEvent.class, Player.class, new Getter<Player, UltimateUIBlockClickEvent>() {
            public Player get(UltimateUIBlockClickEvent var1) {
               return var1 == null ? null : var1.getPlayer();
            }
         }, 0);
         Skript.registerExpression(
            ExprUltimateUiEventPage.class,
            String.class,
            ExpressionType.SIMPLE,
            new String[]{"[the] [ultimate[ ]ui] event page[ name]", "[the] [ultimate[ ]ui] clicked page[ name]"}
         );
         Skript.registerExpression(
            ExprUltimateUiEventBlockId.class,
            String.class,
            ExpressionType.SIMPLE,
            new String[]{"[the] [ultimate[ ]ui] event block id", "[the] [ultimate[ ]ui] clicked block id"}
         );
         Skript.registerExpression(
            ExprUltimateUiEventBlockLayer.class,
            Number.class,
            ExpressionType.SIMPLE,
            new String[]{"[the] [ultimate[ ]ui] event block layer", "[the] [ultimate[ ]ui] clicked block layer"}
         );
         Skript.registerExpression(
            ExprUltimateUiEventClickType.class,
            String.class,
            ExpressionType.SIMPLE,
            new String[]{"[the] [ultimate[ ]ui] event interaction type", "[the] [ultimate[ ]ui] interaction type"}
         );
         Skript.registerExpression(
            ExprUltimateUiEventCursorX.class,
            Number.class,
            ExpressionType.SIMPLE,
            new String[]{"[the] [ultimate[ ]ui] event cursor x", "[the] [ultimate[ ]ui] cursor x"}
         );
         Skript.registerExpression(
            ExprUltimateUiEventCursorY.class,
            Number.class,
            ExpressionType.SIMPLE,
            new String[]{"[the] [ultimate[ ]ui] event cursor y", "[the] [ultimate[ ]ui] cursor y"}
         );
         registered = true;
      }
   }

   public static UltimateUI getPlugin() {
      return plugin;
   }
}
