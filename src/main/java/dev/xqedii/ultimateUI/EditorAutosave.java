package dev.xqedii.ultimateUI;

public abstract class EditorAutosave extends PlayerSessions {

   public void scheduleEditorAutosaveTask() {
      {
         this.cancelEditorAutosaveTask();
         if (this.Xqedii_2k1vujp != null) {
            if (this.getConfig()
               .getBoolean(
                  "editor.autosave.enabled",
                  true
               )) {
               long var1 = this.resolveAutosaveIntervalTicks();
               this.Xqedii_1fzr9sp = Xqedii_38o2zes.Xqedii_uplb4zf(this, var1, var1, () -> {
                  {
                     try {
                        this.Xqedii_2k1vujp.runEditorAutosave();
                     } catch (Throwable var2) {
                        this.getLogger().warning(Xqedii_itnzm9extu1a(var2.getMessage()));
                     }
                  }
               });
            }
         }
      }
   }

   public void cancelEditorAutosaveTask() {
      {
         if (this.Xqedii_1fzr9sp != null) {
            this.Xqedii_1fzr9sp.cancel();
            this.Xqedii_1fzr9sp = null;
         }
      }
   }

   public long resolveAutosaveIntervalTicks() {
      {
         long var1 = Math.max(
            1L,
            this.getConfig()
               .getLong(
                  "editor.autosave.interval-seconds",
                  300L
               )
         );
         return Math.max(20L, var1 * 20L);
      }
   }

   private static /* synthetic */ String Xqedii_itnzm9extu1a(String string) {
        return "[UltimateUI] Editor autosave task failed: " + string;
    }
}
