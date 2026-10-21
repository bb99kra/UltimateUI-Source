package dev.xqedii.ultimateUI;

public final class ExpressionStatementNode extends StatementAstNode {
   public final ExpressionAstNode Xqedii_1n8nk52;

   public ExpressionStatementNode(ExpressionAstNode var1, int var2) {
      super(var2);
      this.Xqedii_1n8nk52 = var1;
   }

   @Override
   public void Xqedii_3egc8pa(ScriptExecutionContext var1) {
      {
         if (this.Xqedii_1n8nk52 instanceof SpecialIdentifierExpressionNode var2) {
            ScriptFunctionDef var4 = var2.Xqedii_huubs12(var1);
            if (var4 != null) {
               var1.Xqedii_1lbtp57(var4, var2.Xqedii_3fcv7sv(var1));
               return;
            }
         }

         this.Xqedii_1n8nk52.Xqedii_28g36j3(var1);
      }
   }
}
