package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.bedrock.BedrockUiService;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import org.bukkit.entity.Player;

public final class Xqedii_3vb5gzo implements Xqedii_1aatyko {
   public final BedrockUiService Xqedii_107tkg3;

   public Xqedii_3vb5gzo(BedrockUiService var1) {
      this.Xqedii_107tkg3 = var1;
   }

   @Override
   public void Xqedii_m5xpibc(Player var1, String var2) {
      {
         this.Xqedii_107tkg3.openPage(var1, var2);
      }
   }

   @Override
   public void close(Player var1) {
      {
         this.Xqedii_107tkg3.closeScreen(var1);
      }
   }

   @Override
   public void input(Player var1, Map<String, Object> var2, Consumer<Boolean> var3) {
      {
         Xqedii_23ssj1h var4 = this.Xqedii_107tkg3.Xqedii_1ebrd9y.get(var1.getUniqueId());
         this.Xqedii_107tkg3
            .Xqedii_mqop2nv(
               var1, var4 == null ? "" : var4.Xqedii_3fdq4vw, var2, null, var3
            );
      }
   }

   @Override
   public void Xqedii_1ob6onh(Player var1, List<String> var2, boolean var3) {
      {
         for (String var5 : var2) {
            this.Xqedii_107tkg3.Xqedii_3k25vjd(var1, var5, var3);
         }
      }
   }

   @Override
   public void toggle(Player var1, List<String> var2, List<String> var3) {
      {
         for (String var5 : var2) {
            this.Xqedii_107tkg3.Xqedii_3k25vjd(var1, var5, false);
         }

         for (String var7 : var3) {
            this.Xqedii_107tkg3.Xqedii_3k25vjd(var1, var7, true);
         }
      }
   }

   @Override
   public Map<String, Object> Xqedii_69nk63w(Player var1, String var2) {
      {
         Xqedii_23ssj1h var3 = this.Xqedii_107tkg3.Xqedii_1ebrd9y.get(var1.getUniqueId());
         Xqedii_2kb1ns6 var4 = var3 == null ? null : this.Xqedii_107tkg3.Xqedii_2vtp3jf.get(var3.Xqedii_3fdq4vw);
         return var4 != null && var2 != null ? var4.Xqedii_p5zrifg.get(var2.trim()) : null;
      }
   }
}
