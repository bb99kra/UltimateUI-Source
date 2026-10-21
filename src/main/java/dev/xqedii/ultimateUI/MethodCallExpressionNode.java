package dev.xqedii.ultimateUI;

public final class MethodCallExpressionNode extends ExpressionAstNode {
   public final ExpressionAstNode Xqedii_3uinj53;
   public final String Xqedii_2ddyr4b;
   public final ExpressionAstNode[] Xqedii_1myzzn8;

   public MethodCallExpressionNode(ExpressionAstNode var1, String var2, ExpressionAstNode[] var3, int var4) {
      super(var4);
      this.Xqedii_3uinj53 = var1;
      this.Xqedii_2ddyr4b = var2;
      this.Xqedii_1myzzn8 = var3;
   }

   @Override
   public Object Xqedii_28g36j3(ScriptExecutionContext var1) {
      {
         Object var2 = this.Xqedii_3uinj53.Xqedii_28g36j3(var1);
         Object[] var3 = new Object[this.Xqedii_1myzzn8.length];

         for (int var4 = 0; var4 < this.Xqedii_1myzzn8.length; var4++) {
            var3[var4] = this.Xqedii_1myzzn8[var4].Xqedii_28g36j3(var1);
         }

         return Xqedii_2d07ocz.Xqedii_1sr2hld(var1, var2, this.Xqedii_2ddyr4b, var3, this.Xqedii_3dzdvck);
      }
   }
}
