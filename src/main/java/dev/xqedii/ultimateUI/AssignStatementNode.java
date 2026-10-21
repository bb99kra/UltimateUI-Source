package dev.xqedii.ultimateUI;

import java.util.ArrayList;
import java.util.List;

public final class AssignStatementNode extends StatementAstNode {
   public final boolean Xqedii_5ak6wpd;
   public final ExpressionAstNode Xqedii_2qxtnhn;
   public final ExpressionAstNode Xqedii_2xcaz6m;

   public AssignStatementNode(boolean var1, ExpressionAstNode var2, ExpressionAstNode var3, int var4) {
      super(var4);
      this.Xqedii_5ak6wpd = var1;
      this.Xqedii_2qxtnhn = var2;
      this.Xqedii_2xcaz6m = var3;
   }

   @Override
   public void Xqedii_3egc8pa(ScriptExecutionContext var1) {
      {
         Object var2 = this.Xqedii_2qxtnhn.Xqedii_28g36j3(var1);
         Object var3 = this.Xqedii_2xcaz6m.Xqedii_28g36j3(var1);
         if (!(var3 instanceof List var4)) {
            if (var3 == null && this.Xqedii_5ak6wpd && !(var2 instanceof Number)) {
               ArrayList var8 = new ArrayList();
               var8.add(var2);
               this.Xqedii_2xcaz6m.Xqedii_2uxio2i(var1, var8);
            } else {
               Object var7 = this.Xqedii_5ak6wpd
                  ? Xqedii_z4wg071.Xqedii_hbc7zbp(
                     var3 == null ? Double.longBitsToDouble(0L) : var3, var2, this.Xqedii_2lldvpl
                  )
                  : Xqedii_z4wg071.Xqedii_3c9epia(var3, this.Xqedii_2lldvpl) - Xqedii_z4wg071.Xqedii_3c9epia(var2, this.Xqedii_2lldvpl);
               this.Xqedii_2xcaz6m.Xqedii_2uxio2i(var1, var7);
            }
         } else {
            List var5 = var4;
            if (this.Xqedii_5ak6wpd) {
               var4.add(var2);
            } else {
               for (int var6 = 0; var6 < var5.size(); var6++) {
                  if (Xqedii_z4wg071.Xqedii_zxgkysn(var5.get(var6), var2)) {
                     var5.remove(var6);
                     break;
                  }
               }
            }
         }
      }
   }
}
