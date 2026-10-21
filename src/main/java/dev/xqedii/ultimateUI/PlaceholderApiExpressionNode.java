package dev.xqedii.ultimateUI;

public final class PlaceholderApiExpressionNode extends ExpressionAstNode {
   public final String Xqedii_35dmu88;
   public final boolean Xqedii_xu2lx8u;

   public PlaceholderApiExpressionNode(String var1, int var2) {
      super(var2);
      this.Xqedii_35dmu88 = var1;
      this.Xqedii_xu2lx8u = ((var1.indexOf(37) >= 0
         ? 1
         : 0) != 0);
   }

   @Override
   public Object Xqedii_28g36j3(ScriptExecutionContext var1) {
      {
         return this.Xqedii_xu2lx8u && var1.Xqedii_3a9kp91.Xqedii_2fi2lg1 != null
            ? var1.Xqedii_3a9kp91.Xqedii_2fi2lg1.Xqedii_14ikmfz(var1.Xqedii_3a9kp91.Xqedii_1v4ue4a, this.Xqedii_35dmu88)
            : this.Xqedii_35dmu88;
      }
   }
}
