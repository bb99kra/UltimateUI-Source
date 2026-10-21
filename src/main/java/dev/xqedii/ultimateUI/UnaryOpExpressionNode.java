package dev.xqedii.ultimateUI;

public final class UnaryOpExpressionNode extends ExpressionAstNode {
   public final boolean Xqedii_sqm35ia;
   public final ExpressionAstNode Xqedii_3qszppj;

   public UnaryOpExpressionNode(boolean var1, ExpressionAstNode var2, int var3) {
      super(var3);
      this.Xqedii_sqm35ia = var1;
      this.Xqedii_3qszppj = var2;
   }

   @Override
   public Object Xqedii_28g36j3(ScriptExecutionContext var1) {
      {
         Object var2 = this.Xqedii_3qszppj.Xqedii_28g36j3(var1);
         return this.Xqedii_sqm35ia
            ? -Xqedii_z4wg071.Xqedii_3c9epia(var2, this.Xqedii_3dzdvck)
            : Boolean.valueOf(
               ((!Xqedii_z4wg071.Xqedii_2od1ceu(var2)
                  ? 1
                  : 0) != 0)
            );
      }
   }
}
