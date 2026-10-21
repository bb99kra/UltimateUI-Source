package dev.xqedii.ultimateUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent.Builder;
import net.kyori.adventure.text.format.ShadowColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

public final class Xqedii_3bbigfb {
   public static final int Xqedii_2ifpwpx = -1;
   public static final Map<String, Key> Xqedii_ru1wbyc;
   public static final Style Xqedii_1gw4fqo;

   public static List<Component> Xqedii_1acvk8o(List<Xqedii_1il8kkh> var0, int var1, int var2) {
      {
         List var3 = Xqedii_f528w4h(var0, var1);
         ArrayList var4 = new ArrayList(var3.size());

         for (int var5 = 0; var5 < var3.size(); var5++) {
            var4.add(Xqedii_2w1nq1n((List<Xqedii_1il8kkh>)var3.get(var5), var2 + var5));
         }

         return var4;
      }
   }

   public static Component Xqedii_1izsph2(List<Xqedii_1il8kkh> var0, int var1) {
      {
         return Xqedii_2w1nq1n(var0 == null ? List.of() : var0, var1);
      }
   }

   public static Component Xqedii_3aye30f(List<Xqedii_1il8kkh> var0) {
      {
         return Xqedii_2w1nq1n(var0 == null ? List.of() : var0, -1);
      }
   }

   public static boolean Xqedii_2hskppl(List<Xqedii_1il8kkh> var0) {
      if (var0 != null && !var0.isEmpty()) {
         for (Xqedii_1il8kkh var2 : var0) {
            if (var2.Xqedii_22d03wg()) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static List<List<Xqedii_1il8kkh>> Xqedii_f528w4h(List<Xqedii_1il8kkh> var0, int var1) {
      {
         ArrayList var2 = new ArrayList();
         if (var0 != null && !var0.isEmpty()) {
            int var3 = Math.max(1, var1);
            int var4 = Math.max(
               6000, (var0.size() + var3 - 1) / var3
            );

            for (int var5 = 0; var5 < var0.size(); var5 += var4) {
               var2.add(new ArrayList(var0.subList(var5, Math.min(var0.size(), var5 + var4))));
            }

            return var2;
         } else {
            return var2;
         }
      }
   }

   public static Component Xqedii_2w1nq1n(List<Xqedii_1il8kkh> var0, int var1) {
      {
         ArrayList var2 = new ArrayList();
         StringBuilder var3 = new StringBuilder(256);
         String var4 = "uui:bbs";
         int var5 = 16777215;
         boolean var6 = false;
         boolean var7 = false;
         var3.appendCodePoint(327680);
         long var8 = Xqedii_30g0ulj.Xqedii_mrpmvc3();
         int var10 = var1 == -1
            ? 1
            : 0;
         int var11 = var10 != 0 ? 0 : Xqedii_30g0ulj.Xqedii_59uga9c(var1);

         for (Xqedii_1il8kkh var13 : var0) {
            long var14;
            if (var13.Xqedii_22d03wg()) {
               int var16 = var13.Xqedii_qw73drj - var11;
               if (var10 != 0 || !Xqedii_30g0ulj.Xqedii_26vb70e(var13.Xqedii_1t89g9g, var16)) {
                  continue;
               }

               var14 = Xqedii_30g0ulj.Xqedii_2jcnpyt(var13.Xqedii_1t89g9g, var16);
            } else {
               var14 = var13.Xqedii_19hg2b0();
            }

            if (var14 >= 0L
               && var14 <= 16769024L) {
               int var18 = var13.Xqedii_2llkml4();
               if (!var13.Xqedii_2d9hu5h.equals(var4)
                  || var18 != var5
                  || var13.Xqedii_2lfvdxd != var6
                  || var13.Xqedii_1ruao0x != var7
                  || var3.length() >= 4096) {
                  Xqedii_3su27gp(var2, var3, var4, var5, var6, var7);
                  var4 = var13.Xqedii_2d9hu5h;
                  var5 = var18;
                  var6 = var13.Xqedii_2lfvdxd;
                  var7 = var13.Xqedii_1ruao0x;
               }

               int var17 = var3.length();
               if (!Xqedii_2dq9nbq.Xqedii_gkeh9a2(var3, var14 - var8)) {
                  var3.setLength(var17);
               } else {
                  var3.appendCodePoint(var13.Xqedii_18nmhy9);
                  var8 = var14 + var13.Xqedii_1f9t2xs;
               }
            }
         }

         if (var8 != 0L) {
            Xqedii_2dq9nbq.Xqedii_gkeh9a2(var3, -var8);
         }

         Xqedii_3su27gp(var2, var3, var4, var5, var6, var7);
         return ((Builder)((Builder)Component.text().style(Xqedii_1gw4fqo)).append(var2)).build();
      }
   }

   public static void Xqedii_3su27gp(List<Component> var0, StringBuilder var1, String var2, int var3, boolean var4, boolean var5) {
      if (var1.length() != 0) {
         net.kyori.adventure.text.format.Style.Builder var6 = Style.style().color(TextColor.color(var3 & 16777215));
         if (var2 != null) {
            var6.font(Xqedii_3qbb71z(var2));
         }

         if (var4) {
            var6.decoration(TextDecoration.BOLD, true);
         }

         if (var5) {
            var6.decoration(TextDecoration.ITALIC, true);
         }

         var0.add(Component.text(var1.toString()).style(var6.build()));
         var1.setLength(0);
      }
   }

   public static Key Xqedii_3qbb71z(String var0) {
      {
         return Xqedii_ru1wbyc.computeIfAbsent(var0, Key::key);
      }
   }

   static {
      {
         Xqedii_ru1wbyc = new ConcurrentHashMap<>();
         Xqedii_1gw4fqo = Style.style().apply(ShadowColor.none())
            .decoration(TextDecoration.BOLD, false)
            .decoration(TextDecoration.ITALIC, false)
            .decoration(TextDecoration.UNDERLINED, false)
            .decoration(TextDecoration.STRIKETHROUGH, false)
            .decoration(TextDecoration.OBFUSCATED, false)
            .build();
      }
   }
}
