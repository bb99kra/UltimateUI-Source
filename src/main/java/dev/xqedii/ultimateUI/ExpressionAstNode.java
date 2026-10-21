package dev.xqedii.ultimateUI;

public abstract class ExpressionAstNode {
   public final int Xqedii_3dzdvck;

   public ExpressionAstNode(int var1) {
      this.Xqedii_3dzdvck = var1;
   }

   public abstract Object Xqedii_28g36j3(ScriptExecutionContext var1);

   public boolean Xqedii_25ddebp() {
      {
         return false;
      }
   }

   public void Xqedii_2uxio2i(ScriptExecutionContext var1, Object var2) {
      {
         throw new ScriptSyntaxException(
            "You can't change this value",
            this.Xqedii_3dzdvck
         );
      }
   }

   public void Xqedii_2dz6h65(ScriptExecutionContext var1) {
      {
         throw new ScriptSyntaxException(
            "You can't delete this value",
            this.Xqedii_3dzdvck
         );
      }
   }
}
