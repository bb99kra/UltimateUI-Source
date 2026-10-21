package dev.xqedii.ultimateUI;

import java.util.ArrayDeque;
import java.util.Map;

public final class ScriptExecutionContext {
   public static final int Xqedii_2rdi64b = 250000;
   public final ScriptScopeContext Xqedii_3a9kp91;
   public final ArrayDeque<ScriptExecutionStep> Xqedii_27ujvby = new ArrayDeque<>();
   public final boolean Xqedii_1gcmf2y;
   public int Xqedii_2vw59nq;
   public int Xqedii_18k2h4u;
   public long Xqedii_1gzo1er = -1L;
   public Object Xqedii_1k6htot;

   public ScriptExecutionContext(ScriptScopeContext var1, StatementAstNode[] var2, boolean var3) {
      this.Xqedii_3a9kp91 = var1;
      this.Xqedii_1gcmf2y = var3;
      this.Xqedii_27ujvby.push(new BlockExecutionStep(var2));
   }

   public boolean run() {
      {
         this.Xqedii_1gzo1er = -1L;
         this.Xqedii_2vw59nq = 0;

         while (!this.Xqedii_27ujvby.isEmpty()) {
            if ((this.Xqedii_2vw59nq = this.Xqedii_2vw59nq + 1)
               > 250000) {
               throw new ScriptSyntaxException(
                  "The script ran 250000 steps without a wait, is a loop running forever?",
                  this.Xqedii_18k2h4u
               );
            }

            ScriptExecutionStep var1 = this.Xqedii_27ujvby.peek();
            if (!var1.Xqedii_j55az06(this) && this.Xqedii_27ujvby.peek() == var1) {
               this.Xqedii_27ujvby.pop();
            }

            if (this.Xqedii_1gzo1er >= 0L) {
               return false;
            }
         }

         return true;
      }
   }

   public void Xqedii_158uidw(StatementAstNode[] var1) {
      {
         this.Xqedii_27ujvby.push(new BlockExecutionStep(var1));
      }
   }

   public void Xqedii_1w2dte6(ScriptExecutionStep var1) {
      {
         this.Xqedii_27ujvby.push(var1);
      }
   }

   public void Xqedii_eaezyvm(long var1, int var3) {
      if (!this.Xqedii_1gcmf2y) {
         throw new ScriptSyntaxException(
            "wait can't be used inside a function whose result is used as a value",
            var3
         );
      } else {
         this.Xqedii_1gzo1er = Math.max(0L, var1);
      }
   }

   public void Xqedii_uj5flsr(int var1) {
      {
         while (!this.Xqedii_27ujvby.isEmpty()) {
            ScriptExecutionStep var2 = this.Xqedii_27ujvby.pop();
            if (var2 instanceof LoopExecutionStep) {
               return;
            }

            if (var2 instanceof ScopeRestoreExecutionStep var3) {
               var3.Xqedii_3uc4si5(this);
               break;
            }
         }

         throw new ScriptSyntaxException(
            "break can only be used inside a loop",
            var1
         );
      }
   }

   public void Xqedii_6e74nb9(int var1) {
      {
         while (!this.Xqedii_27ujvby.isEmpty()) {
            ScriptExecutionStep var2 = this.Xqedii_27ujvby.peek();
            if (var2 instanceof LoopExecutionStep) {
               return;
            }

            if (var2 instanceof ScopeRestoreExecutionStep) {
               break;
            }

            this.Xqedii_27ujvby.pop();
         }

         throw new ScriptSyntaxException(
            "continue can only be used inside a loop",
            var1
         );
      }
   }

   public void Xqedii_3tytdhk(Object var1) {
      {
         this.Xqedii_1k6htot = var1;

         while (!this.Xqedii_27ujvby.isEmpty()) {
            ScriptExecutionStep var2 = this.Xqedii_27ujvby.pop();
            if (var2 instanceof ScopeRestoreExecutionStep var3) {
               var3.Xqedii_3uc4si5(this);
               return;
            }
         }
      }
   }

   public void Xqedii_1lbtp57(ScriptFunctionDef var1, Object[] var2) {
      {
         ScopeRestoreExecutionStep var3 = new ScopeRestoreExecutionStep(this.Xqedii_3a9kp91.Xqedii_107f1nz);
         this.Xqedii_3a9kp91.Xqedii_107f1nz = var1.Xqedii_nid43j8(var2);
         this.Xqedii_27ujvby.push(var3);
         this.Xqedii_27ujvby.push(new BlockExecutionStep(var1.Xqedii_3476x5s));
      }
   }

   public Object Xqedii_1kj5m0d(ScriptFunctionDef var1, Object[] var2, int var3) {
      {
         Map var4 = this.Xqedii_3a9kp91.Xqedii_107f1nz;
         this.Xqedii_3a9kp91.Xqedii_107f1nz = var1.Xqedii_nid43j8(var2);

         Object var6;
         try {
            ScriptExecutionContext var5 = new ScriptExecutionContext(this.Xqedii_3a9kp91, var1.Xqedii_3476x5s, false);
            var5.Xqedii_18k2h4u = var3;
            var5.Xqedii_2vw59nq = this.Xqedii_2vw59nq;
            var5.run();
            this.Xqedii_2vw59nq = var5.Xqedii_2vw59nq;
            var6 = var5.Xqedii_1k6htot;
         } finally {
            this.Xqedii_3a9kp91.Xqedii_107f1nz = var4;
         }

         return var6;
      }
   }
}
