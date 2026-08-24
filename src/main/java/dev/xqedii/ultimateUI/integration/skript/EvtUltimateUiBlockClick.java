package dev.xqedii.ultimateUI.integration.skript;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import dev.xqedii.ultimateUI.api.event.UltimateUIBlockClickEvent;
import org.bukkit.event.Event;

public final class EvtUltimateUiBlockClick extends SkriptEvent {
   public boolean init(Literal<?>[] var1, int var2, ParseResult var3) {
      return true;
   }

   public boolean check(Event var1) {
      return var1 instanceof UltimateUIBlockClickEvent;
   }

   public String toString(Event var1, boolean var2) {
      return "ultimateui block click";
   }
}
