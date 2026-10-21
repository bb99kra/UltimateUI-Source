package dev.xqedii.ultimateUI;

import java.util.LinkedHashMap;

public final class MapLiteralExpressionNode extends ExpressionAstNode {
   public final String[] Xqedii_3nt983w;
   public final ExpressionAstNode[] Xqedii_2bpzrzr;

   public MapLiteralExpressionNode(String[] var1, ExpressionAstNode[] var2, int var3) {
      super(var3);
      this.Xqedii_3nt983w = var1;
      this.Xqedii_2bpzrzr = var2;
   }

   @Override
   public Object Xqedii_28g36j3(ScriptExecutionContext var1) {
      {
         LinkedHashMap var2 = new LinkedHashMap();

         for (int var3 = 0; var3 < this.Xqedii_3nt983w.length; var3++) {
            var2.put(this.Xqedii_3nt983w[var3], this.Xqedii_2bpzrzr[var3].Xqedii_28g36j3(var1));
         }

         return var2;
      }
   }
}
