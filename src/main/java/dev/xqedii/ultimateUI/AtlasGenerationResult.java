package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.service.resourcepack.UiImageAtlasService;
import java.lang.runtime.ObjectMethods;
import java.util.Map;

public record AtlasGenerationResult(Map<String, UiImageAtlasService.GeneratedImage> imagesByName, int generatedTiles) {

   @Override

   public int generatedTiles() {
      {
         return this.generatedTiles;
      }
   }
}
