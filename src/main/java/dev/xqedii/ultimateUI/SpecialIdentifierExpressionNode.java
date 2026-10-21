package dev.xqedii.ultimateUI;

public final class SpecialIdentifierExpressionNode extends ExpressionAstNode {
   public final String Xqedii_3aepu2p;
   public final ExpressionAstNode[] Xqedii_1tnml5z;

   public SpecialIdentifierExpressionNode(String var1, ExpressionAstNode[] var2, int var3) {
      super(var3);
      this.Xqedii_3aepu2p = var1;
      this.Xqedii_1tnml5z = var2;
   }

   public ScriptFunctionDef Xqedii_huubs12(ScriptExecutionContext var1) {
      {
         return var1.Xqedii_3a9kp91.Xqedii_2q97y6i.Xqedii_3j5g4ep.get(this.Xqedii_3aepu2p);
      }
   }

   public Object[] Xqedii_3fcv7sv(ScriptExecutionContext var1) {
      {
         Object[] var2 = new Object[this.Xqedii_1tnml5z.length];

         for (int var3 = 0; var3 < this.Xqedii_1tnml5z.length; var3++) {
            var2[var3] = this.Xqedii_1tnml5z[var3].Xqedii_28g36j3(var1);
         }

         return var2;
      }
   }

   @Override
   public Object Xqedii_28g36j3(ScriptExecutionContext var1) {
      {
         ScriptFunctionDef var2 = this.Xqedii_huubs12(var1);
         Object[] var3 = this.Xqedii_3fcv7sv(var1);
         return var2 != null
            ? var1.Xqedii_1kj5m0d(var2, var3, this.Xqedii_3dzdvck)
            : Xqedii_1iw7g71.Xqedii_2xeqrwc(var1, this.Xqedii_3aepu2p, var3, this.Xqedii_3dzdvck);
      }
   }
}
