package dev.xqedii.ultimateUI;

import java.util.ArrayList;
import java.util.List;

public final class BinaryOpExpressionNode extends ExpressionAstNode {
   public static final int Xqedii_mhe2c1u = 1;
   public static final int Xqedii_3amwr65 = 2;
   public static final int Xqedii_28fttdb = 3;
   public static final int Xqedii_2tnao7f = 4;
   public static final int Xqedii_xjn9c8s = 5;
   public static final int Xqedii_3nvqflj = 6;
   public static final int Xqedii_2rk9frz = 7;
   public static final int Xqedii_1uvza11 = 8;
   public static final int Xqedii_q0nlkyg = 9;
   public static final int Xqedii_3a5xucq = 10;
   public static final int Xqedii_1rs9spb = 11;
   public static final int Xqedii_1fg5rdf = 12;
   public static final int Xqedii_13qjm67 = 13;
   public static final int Xqedii_2u6h11j = 14;
   public static final int Xqedii_11er39h = 15;
   public static final int Xqedii_3viw3na = 16;
   public final int Xqedii_31c5fot;
   public final ExpressionAstNode Xqedii_2le8rep;
   public final ExpressionAstNode Xqedii_81aubg7;

   public BinaryOpExpressionNode(int var1, ExpressionAstNode var2, ExpressionAstNode var3, int var4) {
      super(var4);
      this.Xqedii_31c5fot = var1;
      this.Xqedii_2le8rep = var2;
      this.Xqedii_81aubg7 = var3;
   }

   @Override
   public Object Xqedii_28g36j3(ScriptExecutionContext var1) {
      {
         Object var2 = this.Xqedii_2le8rep.Xqedii_28g36j3(var1);
         Object var3 = this.Xqedii_81aubg7.Xqedii_28g36j3(var1);
         return Xqedii_1tkor05(this.Xqedii_31c5fot, var2, var3, this.Xqedii_3dzdvck);
      }
   }

   public static Object Xqedii_1tkor05(int var0, Object var1, Object var2, int var3) {
      {
         switch (var0) {
            case 1:
               return Xqedii_z4wg071.Xqedii_hbc7zbp(var1, var2, var3);
            case 2:
               if (var1 instanceof List var10) {
                  ArrayList var5 = new ArrayList(var10);
                  var5.removeIf(var1x -> {
                     {
                        return Xqedii_z4wg071.Xqedii_zxgkysn(var1x, var2);
                     }
                  });
                  return var5;
               }

               return Xqedii_z4wg071.Xqedii_3c9epia(var1, var3) - Xqedii_z4wg071.Xqedii_3c9epia(var2, var3);
            case 3:
               if (var1 instanceof String var9) {
                  return var9.repeat(Math.max(0, Xqedii_z4wg071.Xqedii_1x518al(var2, var3)));
               }

               return Xqedii_z4wg071.Xqedii_3c9epia(var1, var3) * Xqedii_z4wg071.Xqedii_3c9epia(var2, var3);
            case 4:
               double var8 = Xqedii_z4wg071.Xqedii_3c9epia(var2, var3);
               if (var8 == Double.longBitsToDouble(0L)) {
                  throw new ScriptSyntaxException(
                     "You can't divide by zero",
                     var3
                  );
               }

               return Xqedii_z4wg071.Xqedii_3c9epia(var1, var3) / var8;
            case 5:
               double var4 = Xqedii_z4wg071.Xqedii_3c9epia(var2, var3);
               if (var4 == Double.longBitsToDouble(0L)) {
                  throw new ScriptSyntaxException(
                     "You can't divide by zero",
                     var3
                  );
               }

               double var6 = Xqedii_z4wg071.Xqedii_3c9epia(var1, var3) % var4;
               return var6 < Double.longBitsToDouble(0L) ? var6 + Math.abs(var4) : var6;
            case 6:
               return Xqedii_z4wg071.Xqedii_zxgkysn(var1, var2);
            case 7:
               return Boolean.valueOf(
                  ((!Xqedii_z4wg071.Xqedii_zxgkysn(var1, var2)
                     ? 1
                     : 0) != 0)
               );
            case 8:
               return Boolean.valueOf(
                  ((Xqedii_z4wg071.Xqedii_eqxwxh9(var1, var2, var3) < 0
                     ? 1
                     : 0) != 0)
               );
            case 9:
               return Boolean.valueOf(
                  ((Xqedii_z4wg071.Xqedii_eqxwxh9(var1, var2, var3) > 0
                     ? 1
                     : 0) != 0)
               );
            case 10:
               return Boolean.valueOf(
                  ((Xqedii_z4wg071.Xqedii_eqxwxh9(var1, var2, var3) <= 0
                     ? 1
                     : 0) != 0)
               );
            case 11:
               return Boolean.valueOf(
                  ((Xqedii_z4wg071.Xqedii_eqxwxh9(var1, var2, var3) >= 0
                     ? 1
                     : 0) != 0)
               );
            case 12:
               return Xqedii_z4wg071.Xqedii_2lutf1l(var1, var2);
            case 13:
               return Boolean.valueOf(
                  ((!Xqedii_z4wg071.Xqedii_2lutf1l(var1, var2)
                     ? 1
                     : 0) != 0)
               );
            case 14:
               return Xqedii_z4wg071.Xqedii_1lmv6tt(var1, var2);
            case 15:
               return Xqedii_z4wg071.Xqedii_1lmv6tt(var2, var1);
            case 16:
               return Boolean.valueOf(
                  ((!Xqedii_z4wg071.Xqedii_1lmv6tt(var2, var1)
                     ? 1
                     : 0) != 0)
               );
            default:
               throw new ScriptSyntaxException(
                  "Unknown operator", var3
               );
         }
      }
   }
}
