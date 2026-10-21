package dev.xqedii.ultimateUI;

public final class Xqedii_3hb1htw extends LoopExecutionStep {
   public final ExpressionAstNode Xqedii_3kowdkb;
   public final StatementAstNode[] Xqedii_mdxoltc;

   public Xqedii_3hb1htw(ExpressionAstNode var1, StatementAstNode[] var2) {
      this.Xqedii_3kowdkb = var1;
      this.Xqedii_mdxoltc = var2;
   }

   @Override
   public boolean Xqedii_j55az06(ScriptExecutionContext var1) {
      if (!Xqedii_z4wg071.Xqedii_2od1ceu(this.Xqedii_3kowdkb.Xqedii_28g36j3(var1))) {
         return false;
      } else {
         var1.Xqedii_158uidw(this.Xqedii_mdxoltc);
         return true;
      }
   }
}
