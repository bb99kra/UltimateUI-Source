package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;
import java.util.List;

public record CustomItemHookResult(CustomItemPlugin target, List<String> changedSettings, List<String> reloadCommands) {

   @Override

   public List<String> changedSettings() {
      {
         return this.changedSettings;
      }
   }

   public List<String> reloadCommands() {
      {
         return this.reloadCommands;
      }
   }
}
