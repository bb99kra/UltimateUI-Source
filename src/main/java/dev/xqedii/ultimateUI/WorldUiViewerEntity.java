package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;
import java.util.UUID;

public record WorldUiViewerEntity(String instanceKey, UUID viewerId, int entityId) {

   @Override

   public UUID viewerId() {
      {
         return this.viewerId;
      }
   }

   public int entityId() {
      {
         return this.entityId;
      }
   }
}
