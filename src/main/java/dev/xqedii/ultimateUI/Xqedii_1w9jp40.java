package dev.xqedii.ultimateUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Xqedii_1w9jp40 {
   public static final Xqedii_1w9jp40 Xqedii_2juqnqj;
   public final List<Object> Xqedii_2pgxi4t;

   public Xqedii_1w9jp40(List<Object> var1) {
      this.Xqedii_2pgxi4t = var1;
   }

   public static Xqedii_1w9jp40 Xqedii_230714b() {
      {
         return Xqedii_2juqnqj;
      }
   }

   public static Xqedii_1w9jp40 Xqedii_30u3vow(int var0) {
      if (var0 <= 0) {
         return Xqedii_2juqnqj;
      } else {
         ArrayList var1 = new ArrayList();

         for (int var2 = 0; var2 < var0; var2++) {
            var1.add(var2);
         }

         return new Xqedii_1w9jp40(var1);
      }
   }

   public static Xqedii_1w9jp40 Xqedii_1pluau6(Iterable<?> var0) {
      if (var0 == null) {
         return Xqedii_2juqnqj;
      } else {
         ArrayList var1 = new ArrayList();

         for (Object var3 : var0) {
            var1.add(var3);
            if (var1.size() >= 2048) {
               break;
            }
         }

         return var1.isEmpty() ? Xqedii_2juqnqj : new Xqedii_1w9jp40(var1);
      }
   }

   public boolean Xqedii_28g4212() {
      {
         return this.Xqedii_2pgxi4t.isEmpty();
      }
   }

   public int Xqedii_12y9lp5() {
      {
         return this.Xqedii_2pgxi4t.size();
      }
   }

   public Object Xqedii_h0g3k9q(int var1) {
      {
         return this.Xqedii_2pgxi4t.get(var1);
      }
   }

   static {
      {
         Xqedii_2juqnqj = new Xqedii_1w9jp40(Collections.emptyList());
      }
   }
}
