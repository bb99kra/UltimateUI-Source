package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import dev.xqedii.ultimateUI.api.event.UltimateUIHoverEvent;
import org.bukkit.event.Event;

public final class EvtUltimateUiHover extends SkriptEvent {
   public boolean Xqedii_3pts0wp;

   public boolean init(Literal<?>[] var1, int var2, ParseResult var3) {
      {
         this.Xqedii_3pts0wp = ((var2 == 1
            ? 1
            : 0) != 0);
         return true;
      }
   }

   public boolean check(Event var1) {
      {
         return ((var1 instanceof UltimateUIHoverEvent var2 && var2.isLeave() == this.Xqedii_3pts0wp
            ? 1
            : 0) != 0);
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return this.Xqedii_3pts0wp
            ? "ultimateui unhover"
            : "ultimateui hover";
      }
   }
}
