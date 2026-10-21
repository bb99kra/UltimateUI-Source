package dev.xqedii.ultimateUI;

public final class JumpStatementNode extends StatementAstNode {
   public static final int Xqedii_3aqux6b = 1;
   public static final int Xqedii_jzipmcq = 2;
   public static final int Xqedii_1zodb39 = 3;
   public final int Xqedii_cyq1yw4;
   public final ExpressionAstNode Xqedii_1ptz4h8;

   public JumpStatementNode(int var1, ExpressionAstNode var2, int var3) {
      super(var3);
      this.Xqedii_cyq1yw4 = var1;
      this.Xqedii_1ptz4h8 = var2;
   }

   @Override
   public void Xqedii_3egc8pa(ScriptExecutionContext var1) {
      {
         switch (this.Xqedii_cyq1yw4) {
            case 1:
               var1.Xqedii_uj5flsr(this.Xqedii_2lldvpl);
               break;
            case 2:
               var1.Xqedii_6e74nb9(this.Xqedii_2lldvpl);
               break;
            default:
               var1.Xqedii_3tytdhk(this.Xqedii_1ptz4h8 == null ? null : this.Xqedii_1ptz4h8.Xqedii_28g36j3(var1));
         }
      }
   }
}
