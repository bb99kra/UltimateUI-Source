package dev.xqedii.ultimateUI;

public final class WhileStatementNode extends StatementAstNode {
   public final ExpressionAstNode Xqedii_2m37uza;
   public final StatementAstNode[] Xqedii_31h3f8u;

   public WhileStatementNode(ExpressionAstNode var1, StatementAstNode[] var2, int var3) {
      super(var3);
      this.Xqedii_2m37uza = var1;
      this.Xqedii_31h3f8u = var2;
   }

   @Override
   public void Xqedii_3egc8pa(ScriptExecutionContext var1) {
      {
         long var2 = Math.max(
            0L,
            Math.round(Xqedii_z4wg071.Xqedii_3c9epia(this.Xqedii_2m37uza.Xqedii_28g36j3(var1), this.Xqedii_2lldvpl))
         );
         var1.Xqedii_1w2dte6(new Xqedii_z0y7rwq(var2, this.Xqedii_31h3f8u));
      }
   }
}
