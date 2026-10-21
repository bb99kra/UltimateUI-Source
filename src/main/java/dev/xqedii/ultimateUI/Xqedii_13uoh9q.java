package dev.xqedii.ultimateUI;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import dev.xqedii.ultimateUI.api.event.UltimateUIBlockClickEvent;
import org.bukkit.event.Event;

public final class Xqedii_13uoh9q extends SkriptEvent {
   public int Xqedii_aiu6lsz;

   public boolean init(Literal<?>[] var1, int var2, ParseResult var3) {
      {
         this.Xqedii_aiu6lsz = var2;
         return true;
      }
   }

   public boolean check(Event var1) {
      if (var1 instanceof UltimateUIBlockClickEvent var2) {
         return switch (this.Xqedii_aiu6lsz) {
             case 3 -> var2.isLeftClick();
             case 4 -> var2.isRightClick();
             case 5 -> var2.isScroll();
             case 6 -> var2.getClickType() == UltimateUIBlockClickEvent.ClickType.SCROLL_UP;
             case 7 -> var2.getClickType() == UltimateUIBlockClickEvent.ClickType.SCROLL_DOWN;
             default -> false;
          };
      } else {
         return false;
      }
   }

   public String toString(Event var1, boolean var2) {
      {
         return "ultimateui block click";
      }
   }
}
