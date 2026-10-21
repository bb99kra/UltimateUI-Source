package dev.xqedii.ultimateUI;

public final class CompoundAssignStatementNode extends StatementAstNode {
   public final ExpressionAstNode Xqedii_j05xg6e;
   public final int Xqedii_jmz3iko;
   public final ExpressionAstNode Xqedii_3fdby0w;

   public CompoundAssignStatementNode(ExpressionAstNode var1, int var2, ExpressionAstNode var3, int var4) {
      super(var4);
      this.Xqedii_j05xg6e = var1;
      this.Xqedii_jmz3iko = var2;
      this.Xqedii_3fdby0w = var3;
   }

   @Override
   public void Xqedii_3egc8pa(ScriptExecutionContext var1) {
      {
         Object var2 = this.Xqedii_3fdby0w.Xqedii_28g36j3(var1);
         if (this.Xqedii_jmz3iko != 0) {
            var2 = BinaryOpExpressionNode.Xqedii_1tkor05(this.Xqedii_jmz3iko, this.Xqedii_j05xg6e.Xqedii_28g36j3(var1), var2, this.Xqedii_2lldvpl);
         }

         this.Xqedii_j05xg6e.Xqedii_2uxio2i(var1, var2);
      }
   }
}
