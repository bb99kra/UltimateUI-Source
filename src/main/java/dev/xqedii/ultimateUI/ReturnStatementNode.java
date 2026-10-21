package dev.xqedii.ultimateUI;

public final class ReturnStatementNode extends StatementAstNode {
   public final ExpressionAstNode Xqedii_3j4l94e;

   public ReturnStatementNode(ExpressionAstNode var1, int var2) {
      super(var2);
      this.Xqedii_3j4l94e = var1;
   }

   @Override
   public void Xqedii_3egc8pa(ScriptExecutionContext var1) {
      if (this.Xqedii_3j4l94e.Xqedii_25ddebp()) {
         this.Xqedii_3j4l94e.Xqedii_2dz6h65(var1);
      } else {
         Xqedii_2d07ocz.Xqedii_1rx201x(var1, UiElementId.idOf(this.Xqedii_3j4l94e.Xqedii_28g36j3(var1), this.Xqedii_2lldvpl), this.Xqedii_2lldvpl);
      }
   }
}
