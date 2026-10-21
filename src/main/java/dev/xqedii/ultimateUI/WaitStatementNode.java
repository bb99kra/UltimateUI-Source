package dev.xqedii.ultimateUI;

public final class WaitStatementNode extends StatementAstNode {
   public final ExpressionAstNode Xqedii_1iktzc7;
   public final double Xqedii_1ndnfno;

   public WaitStatementNode(ExpressionAstNode var1, double var2, int var4) {
      super(var4);
      this.Xqedii_1iktzc7 = var1;
      this.Xqedii_1ndnfno = var2;
   }

   @Override
   public void Xqedii_3egc8pa(ScriptExecutionContext var1) {
      {
         double var2 = Xqedii_z4wg071.Xqedii_3c9epia(this.Xqedii_1iktzc7.Xqedii_28g36j3(var1), this.Xqedii_2lldvpl) * this.Xqedii_1ndnfno;
         var1.Xqedii_eaezyvm(
            Math.round(Math.max(Double.longBitsToDouble(0L), var2)), this.Xqedii_2lldvpl
         );
      }
   }
}
