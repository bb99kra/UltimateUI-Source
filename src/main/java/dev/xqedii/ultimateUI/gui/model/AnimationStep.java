package dev.xqedii.ultimateUI.gui.model;

import java.util.Collections;
import java.util.List;

public class AnimationStep {
   public String target;
   public String axis;
   public String easing;
   public double from;
   public double to;
   public List<Double> values = Collections.emptyList();
}
