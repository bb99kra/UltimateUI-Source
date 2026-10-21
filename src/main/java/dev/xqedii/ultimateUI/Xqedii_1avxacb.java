package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.gui.parser.ScrollViewState;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class Xqedii_1avxacb {
   public final UUID Xqedii_zayz2bu;
   public final ScrollViewState Xqedii_2q32dop = new ScrollViewState();
   public final Map<String, double[]> Xqedii_h8t9zqa = new LinkedHashMap<>();
   public final Map<String, double[]> Xqedii_1c9ttj8 = new LinkedHashMap<>();
   public final Map<String, AnvilInputState> Xqedii_h3mynbh = new ConcurrentHashMap<>();
   public List<Map<String, Object>> Xqedii_17c3k63 = Collections.emptyList();
   public boolean Xqedii_r93bzs3;

   public Xqedii_1avxacb(UUID var1) {
      this.Xqedii_zayz2bu = var1;
   }

   public void Xqedii_2qgqzmn() {
      {
         this.Xqedii_h8t9zqa.clear();
         this.Xqedii_1c9ttj8.clear();
         this.Xqedii_17c3k63 = Collections.emptyList();
         this.Xqedii_r93bzs3 = false;
      }
   }
}
