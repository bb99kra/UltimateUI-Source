package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;
import java.util.EnumSet;
import java.util.UUID;
import net.kyori.adventure.bossbar.BossBar.Color;
import net.kyori.adventure.bossbar.BossBar.Flag;
import net.kyori.adventure.bossbar.BossBar.Overlay;
import net.kyori.adventure.text.Component;

public record Xqedii_34z2igs(UUID barId, BossbarAction action, Component title, float health, Color color, Overlay overlay, EnumSet<Flag> flags) {

   @Override

   public BossbarAction action() {
      {
         return this.action;
      }
   }

   public Component title() {
      {
         return this.title;
      }
   }

   public float health() {
      {
         return this.health;
      }
   }

   public Color color() {
      {
         return this.color;
      }
   }

   public Overlay overlay() {
      {
         return this.overlay;
      }
   }

   public EnumSet<Flag> flags() {
      {
         return this.flags;
      }
   }
}
