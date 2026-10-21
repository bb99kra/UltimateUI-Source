package dev.xqedii.ultimateUI;

public final class Xqedii_vc9adda extends StatementAstNode {
   public static final int Xqedii_1cmfp8n = 1;
   public static final int Xqedii_10pftf5 = 2;
   public static final int Xqedii_1dg5bnd = 3;
   public static final int Xqedii_2i85rju = 4;
   public static final int Xqedii_2e65vu2 = 5;
   public static final int Xqedii_3hlauih = 6;
   public static final int Xqedii_3dvvpea = 7;
   public static final int Xqedii_31tuqpt = 8;
   public static final int Xqedii_2gqlnwu = 9;
   public static final int Xqedii_15ltnjk = 10;
   public static final int Xqedii_2hskn60 = 11;
   public static final int Xqedii_3nz440l = 12;
   public static final int Xqedii_ecvgn5s = 13;
   public static final int Xqedii_y4irjx6 = 14;
   public static final int Xqedii_1vzxpy9 = 15;
   public static final int Xqedii_n5g23ya = 16;
   public static final int Xqedii_103pgzt = 17;
   public static final int Xqedii_2xm6kol = 18;
   public static final int Xqedii_3s4zbny = 19;
   public static final int Xqedii_1dcdwya = 20;
   public static final int Xqedii_1kzt7p7 = 21;
   public static final int Xqedii_3hs535y = 22;
   public static final int Xqedii_2hvptbu = 23;
   public static final int Xqedii_2mo58v9 = 24;
   public static final int Xqedii_2567a55 = 25;
   public static final int Xqedii_3s5w8dg = 26;
   public static final int Xqedii_336pgj2 = 27;
   public static final int Xqedii_h385foj = 28;
   public static final int Xqedii_8uc8uxh = 29;
   public static final int Xqedii_3bzjzgf = 30;
   public static final int Xqedii_2q21e1w = 31;
   public static final int Xqedii_2cuafyz = 32;
   public static final int Xqedii_1szsoe5 = 33;
   public static final int Xqedii_2u05z2i = 34;
   public final int Xqedii_38q6lwp;
   public final ExpressionAstNode[] Xqedii_2uctw9o;

   public Xqedii_vc9adda(int var1, ExpressionAstNode[] var2, int var3) {
      super(var3);
      this.Xqedii_38q6lwp = var1;
      this.Xqedii_2uctw9o = var2;
   }

   @Override
   public void Xqedii_3egc8pa(ScriptExecutionContext var1) {
      {
         Object[] var2 = new Object[this.Xqedii_2uctw9o.length];

         for (int var3 = 0; var3 < this.Xqedii_2uctw9o.length; var3++) {
            var2[var3] = this.Xqedii_2uctw9o[var3] == null ? null : this.Xqedii_2uctw9o[var3].Xqedii_28g36j3(var1);
         }

         Xqedii_2d07ocz.Xqedii_37nn7q1(var1, this.Xqedii_38q6lwp, var2, this.Xqedii_2lldvpl);
      }
   }
}
