package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import dev.xqedii.ultimateUI.api.event.UltimateUIDragEvent;
import org.bukkit.event.Event;

public final class EvtUltimateUiDrag extends SkriptEvent {
   public UltimateUIDragEvent.Phase Xqedii_1l0qniq;

   public boolean init(Literal<?>[] var1, int var2, ParseResult var3) {
      {
         this.Xqedii_1l0qniq = switch (var2) {
            case 0 -> UltimateUIDragEvent.Phase.START;
            case 2 -> UltimateUIDragEvent.Phase.END;
            default -> UltimateUIDragEvent.Phase.MOVE;
         };
         return true;
      }
   }

   public boolean check(Event var1) {
      {
         return ((var1 instanceof UltimateUIDragEvent var2 && var2.getPhase() == this.Xqedii_1l0qniq
            ? 1
            : 0) != 0);
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return Xqedii_3mgvlmj96v95a(this.Xqedii_1l0qniq.name().toLowerCase());
      }
   }

   private static /* synthetic */ String Xqedii_3mgvlmj96v95a(String string) {
        return "ultimateui drag " + string;
    }
}
