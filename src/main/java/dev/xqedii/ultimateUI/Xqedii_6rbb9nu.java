package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateui.libs.packetevents.packetevents.protocol.world.clock.ClockNetworkState;
import java.lang.runtime.ObjectMethods;

public record Xqedii_6rbb9nu(ClockNetworkState state, long gameTime) {

   @Override

   public long gameTime() {
      {
         return this.gameTime;
      }
   }
}
