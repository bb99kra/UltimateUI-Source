package dev.xqedii.ultimateUI;

import java.util.Map;

public final class ScopeRestoreExecutionStep extends ScriptExecutionStep {
   public final Map<String, Object> Xqedii_3b570u6;

   public ScopeRestoreExecutionStep(Map<String, Object> var1) {
      this.Xqedii_3b570u6 = var1;
   }

   public void Xqedii_3uc4si5(ScriptExecutionContext var1) {
      {
         var1.Xqedii_3a9kp91.Xqedii_107f1nz = this.Xqedii_3b570u6;
      }
   }

   @Override
   public boolean Xqedii_j55az06(ScriptExecutionContext var1) {
      {
         this.Xqedii_3uc4si5(var1);
         return false;
      }
   }
}
