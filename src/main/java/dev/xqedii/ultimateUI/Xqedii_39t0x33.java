package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;
import java.util.List;
import java.util.Map;

public record Xqedii_39t0x33(
   Xqedii_2kb1ns6 page, BedrockPackTextureExporter baker, String solidTexture, List<String> warnings, Map<String, Object> namespace, boolean overlay
) {

   public String collection() {
      {
         return this.overlay
            ? "custom_form"
            : "form_buttons";
      }
   }

   public String textProperty() {
      {
         return this.overlay
            ? "#custom_text"
            : "#form_button_text";
      }
   }

   public int slotIndex(int var1) {
      {
         return this.overlay ? this.page.Xqedii_2813zzv(var1) : var1;
      }
   }

   @Override

   public BedrockPackTextureExporter baker() {
      {
         return this.baker;
      }
   }

   public String solidTexture() {
      {
         return this.solidTexture;
      }
   }

   public List<String> warnings() {
      {
         return this.warnings;
      }
   }

   public Map<String, Object> namespace() {
      {
         return this.namespace;
      }
   }

   public boolean overlay() {
      {
         return this.overlay;
      }
   }
}
