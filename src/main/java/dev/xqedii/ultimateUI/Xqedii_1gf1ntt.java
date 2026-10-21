package dev.xqedii.ultimateUI;

import java.util.List;
import net.kyori.adventure.text.Component;

public final class Xqedii_1gf1ntt {

   public final long signature;
   public final long structure;
   public final boolean reserveCursor;
   public final int rowOffset;
   public final List<Xqedii_1il8kkh> glyphs;
   public final int contentBars;
   public final List<Component> titles;

   public Xqedii_1gf1ntt(long signature, long structure, boolean reserveCursor, int rowOffset, List<Xqedii_1il8kkh> glyphs, int contentBars, List<Component> titles) {
      this.signature = signature;
      this.structure = structure;
      this.reserveCursor = reserveCursor;
      this.rowOffset = rowOffset;
      this.glyphs = glyphs;
      this.contentBars = contentBars;
      this.titles = titles;
   }

   public long signature() { return this.signature; }
   public long structure() { return this.structure; }
   public boolean reserveCursor() { return this.reserveCursor; }
   public int rowOffset() { return this.rowOffset; }
   public List<Xqedii_1il8kkh> glyphs() { return this.glyphs; }
   public int contentBars() { return this.contentBars; }
   public List<Component> titles() { return this.titles; }
}
