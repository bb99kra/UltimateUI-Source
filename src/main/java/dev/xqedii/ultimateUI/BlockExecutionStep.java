package dev.xqedii.ultimateUI;

public final class BlockExecutionStep extends ScriptExecutionStep {
   public final StatementAstNode[] Xqedii_236rf6i;
   public int Xqedii_skvaocs;

   public BlockExecutionStep(StatementAstNode[] var1) {
      this.Xqedii_236rf6i = var1;
   }

   @Override
   public boolean Xqedii_j55az06(ScriptExecutionContext var1) {
      if (this.Xqedii_skvaocs >= this.Xqedii_236rf6i.length) {
         return false;
      } else {
         StatementAstNode[] var10000 = this.Xqedii_236rf6i;
         int var10001 = this.Xqedii_skvaocs;
         this.Xqedii_skvaocs = this.Xqedii_skvaocs + 1;
         StatementAstNode var2 = var10000[var10001];
         var1.Xqedii_18k2h4u = var2.Xqedii_2lldvpl;
         var2.Xqedii_3egc8pa(var1);
         return true;
      }
   }
}
