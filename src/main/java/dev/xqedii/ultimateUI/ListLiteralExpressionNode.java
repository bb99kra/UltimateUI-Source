package dev.xqedii.ultimateUI;

import java.util.ArrayList;

public final class ListLiteralExpressionNode extends ExpressionAstNode {
   public final ExpressionAstNode[] Xqedii_w7rakxn;

   public ListLiteralExpressionNode(ExpressionAstNode[] var1, int var2) {
      super(var2);
      this.Xqedii_w7rakxn = var1;
   }

   @Override
   public Object Xqedii_28g36j3(ScriptExecutionContext var1) {
      {
         ArrayList var2 = new ArrayList(this.Xqedii_w7rakxn.length);
         ExpressionAstNode[] var3 = this.Xqedii_w7rakxn;
         int var4 = var3.length;

         for (int var5 = 0; var5 < var4; var5++) {
            ExpressionAstNode var6 = var3[var5];
            var2.add(var6.Xqedii_28g36j3(var1));
         }

         return var2;
      }
   }
}
