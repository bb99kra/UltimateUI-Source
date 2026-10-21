package dev.xqedii.ultimateUI;

public final class FieldAccessExpressionNode extends ExpressionAstNode {
   public final String Xqedii_7d9u7zd;
   public final ExpressionAstNode Xqedii_1txxbrb;

   public FieldAccessExpressionNode(String var1, ExpressionAstNode var2, int var3) {
      super(var3);
      this.Xqedii_7d9u7zd = var1;
      this.Xqedii_1txxbrb = var2;
   }

   @Override
   public Object Xqedii_28g36j3(ScriptExecutionContext var1) {
      {
         Object var2 = this.Xqedii_1txxbrb.Xqedii_28g36j3(var1);
         return !(var2 instanceof String) && !(var2 instanceof UiElementId)
            ? Xqedii_2d07ocz.get(var1, var2, this.Xqedii_7d9u7zd, this.Xqedii_3dzdvck)
            : Xqedii_2d07ocz.Xqedii_1javj6e(var1, UiElementId.idOf(var2, this.Xqedii_3dzdvck), this.Xqedii_7d9u7zd, this.Xqedii_3dzdvck);
      }
   }

   @Override
   public boolean Xqedii_25ddebp() {
      {
         return true;
      }
   }

   @Override
   public void Xqedii_2uxio2i(ScriptExecutionContext var1, Object var2) {
      {
         Object var3 = this.Xqedii_1txxbrb.Xqedii_28g36j3(var1);
         if (!(var3 instanceof String) && !(var3 instanceof UiElementId)) {
            Xqedii_2d07ocz.Xqedii_2a9tfw6(var1, var3, this.Xqedii_7d9u7zd, var2, this.Xqedii_3dzdvck);
         } else {
            Xqedii_2d07ocz.Xqedii_3j2drjj(var1, UiElementId.idOf(var3, this.Xqedii_3dzdvck), this.Xqedii_7d9u7zd, var2, this.Xqedii_3dzdvck);
         }
      }
   }
}
