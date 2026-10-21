package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;
import java.util.UUID;

public record PlayerElementId(UUID playerId, String id) {

   @Override

   public String id() {
      {
         return this.id;
      }
   }
}
