package dev.xqedii.ultimateUI;

public final class CommandStatementNode extends StatementAstNode {
   public final String Xqedii_ummnths;
   public final ExpressionAstNode Xqedii_ztajunw;

   public CommandStatementNode(String var1, ExpressionAstNode var2, int var3) {
      super(var3);
      this.Xqedii_ummnths = var1;
      this.Xqedii_ztajunw = var2;
   }

   @Override
   public void Xqedii_3egc8pa(ScriptExecutionContext var1) {
      {
         var1.Xqedii_3a9kp91.Xqedii_107f1nz.put(this.Xqedii_ummnths, this.Xqedii_ztajunw == null ? null : this.Xqedii_ztajunw.Xqedii_28g36j3(var1));
      }
   }
}
