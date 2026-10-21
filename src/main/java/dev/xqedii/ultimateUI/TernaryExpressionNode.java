package dev.xqedii.ultimateUI;

public final class TernaryExpressionNode extends ExpressionAstNode {
   public final ExpressionAstNode Xqedii_1nl7gqo;
   public final ExpressionAstNode Xqedii_2muelwn;
   public final ExpressionAstNode Xqedii_1bb3uy8;

   public TernaryExpressionNode(ExpressionAstNode var1, ExpressionAstNode var2, ExpressionAstNode var3, int var4) {
      super(var4);
      this.Xqedii_1nl7gqo = var1;
      this.Xqedii_2muelwn = var2;
      this.Xqedii_1bb3uy8 = var3;
   }

   @Override
   public Object Xqedii_28g36j3(ScriptExecutionContext var1) {
      {
         return Xqedii_z4wg071.Xqedii_2od1ceu(this.Xqedii_1nl7gqo.Xqedii_28g36j3(var1))
            ? this.Xqedii_2muelwn.Xqedii_28g36j3(var1)
            : this.Xqedii_1bb3uy8.Xqedii_28g36j3(var1);
      }
   }
}
