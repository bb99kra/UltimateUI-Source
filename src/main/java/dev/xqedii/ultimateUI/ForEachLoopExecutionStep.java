package dev.xqedii.ultimateUI;

import java.util.Iterator;

public final class ForEachLoopExecutionStep extends LoopExecutionStep {
   public final String Xqedii_30kcy1x;
   public final Iterator<?> Xqedii_st7tj6j;
   public final StatementAstNode[] Xqedii_4aadd4j;

   public ForEachLoopExecutionStep(String var1, Iterator<?> var2, StatementAstNode[] var3) {
      this.Xqedii_30kcy1x = var1;
      this.Xqedii_st7tj6j = var2;
      this.Xqedii_4aadd4j = var3;
   }

   @Override
   public boolean Xqedii_j55az06(ScriptExecutionContext var1) {
      if (!this.Xqedii_st7tj6j.hasNext()) {
         return false;
      } else {
         var1.Xqedii_3a9kp91.Xqedii_107f1nz.put(this.Xqedii_30kcy1x, this.Xqedii_st7tj6j.next());
         var1.Xqedii_158uidw(this.Xqedii_4aadd4j);
         return true;
      }
   }
}
