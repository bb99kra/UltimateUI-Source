package dev.xqedii.ultimateUI.gui.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class GuiTemplateResolver {
   public static final String EDITOR_TARGET_ID_KEY = "__editor_target_id";
   public static final String EDITOR_TARGET_KIND_KEY = "__editor_target_kind";
   public static final String EDITOR_TARGET_PATH_KEY = "__editor_target_path";
   public static final String EDITOR_INHERIT_TARGET_TO_CHILDREN_KEY = "__editor_inherit_target_to_children";
   public static final String EDITOR_BLOCK_PATH_KEY = "__editor_block_path";
   public static final String EDITOR_COMPONENT_NAME_KEY = "__editor_component_name";
   public static final String EDITOR_BINDING_X_KEY = "__editor_binding_x";
   public static final String EDITOR_BINDING_Y_KEY = "__editor_binding_y";
   public static final String EDITOR_BINDING_WIDTH_KEY = "__editor_binding_width";
   public static final String EDITOR_BINDING_HEIGHT_KEY = "__editor_binding_height";
   private static final int MAX_DEPTH = 12;
   private static final int MAX_LOOP_ITERATIONS = 2048;
   private static final Pattern PLACEHOLDER = Pattern.compile("\\$\\{([a-zA-Z0-9_.-]+)}|\\{\\{([a-zA-Z0-9_.-]+)}}|\\{([a-zA-Z0-9_.-]+)}");
   private static final Pattern CASE_INLINE_KEY = Pattern.compile("(?i)^case\\s+(.+)$");
   private final Map<YamlConfiguration, Set<String>> componentParamUsageCache = new WeakHashMap<>();
   private static final Map<String, String[]> PATH_SEGMENTS_CACHE = new ConcurrentHashMap<>();

   public List<Map<String, Object>> resolveBlocks(List<?> var1, Map<String, YamlConfiguration> var2) {
      ArrayList var3 = new ArrayList();
      this.expandBlocks(var1, var2 == null ? Collections.emptyMap() : var2, new LinkedHashMap<>(), 0.0, 0.0, var3, 0, "", null, "");
      return var3;
   }

   private void expandBlocks(
      List<?> var1,
      Map<String, YamlConfiguration> var2,
      Map<String, Object> var3,
      double var4,
      double var6,
      List<Map<String, Object>> var8,
      int var9,
      String var10,
      GuiTemplateResolver.EditorTargetMeta var11,
      String var12
   ) {
      if (var1 != null && var9 <= 12) {
         for (int var13 = 0; var13 < var1.size(); var13++) {
            String var14 = this.appendPath(var10, String.valueOf(var13));
            var8.addAll(this.resolveSingleBlock(var1.get(var13), var2, var3, var4, var6, var9, var14, var11, var12));
         }
      }
   }

   private List<Map<String, Object>> resolveSingleBlock(
      Object var1,
      Map<String, YamlConfiguration> var2,
      Map<String, Object> var3,
      double var4,
      double var6,
      int var8,
      String var9,
      GuiTemplateResolver.EditorTargetMeta var10,
      String var11
   ) {
      if (var1 != null && var8 <= 12) {
         Map var12 = this.toStringObjectMap(var1);
         if (var12 == null) {
            return Collections.emptyList();
         } else {
            List var13 = this.resolveLoopBlock(var12, var2, var3, var4, var6, var8, var9, var10, var11);
            if (var13 != null) {
               return var13;
            } else {
               List var14 = this.resolveCaseBlock(var12, var2, var3, var4, var6, var8, var9, var10, var11);
               if (var14 != null) {
                  return var14;
               } else {
                  Map var15 = this.resolveInvocationMapWithParams(var12, var3);
                  if (!this.isBlockEnabled(var15)) {
                     return Collections.emptyList();
                  } else {
                     String var16 = this.extractComponentName(var15);
                     if (var16 != null && !var16.isBlank()) {
                        YamlConfiguration var17 = (YamlConfiguration)var2.get(var16);
                        if (var17 == null) {
                           return Collections.emptyList();
                        } else {
                           Map var18 = this.buildComponentParams(var17, var15, var3);
                           ArrayList var19 = new ArrayList();
                           GuiTemplateResolver.EditorTargetMeta var20 = var10 != null ? var10 : this.buildComponentTargetMeta(var17, var15, var9, var16);
                           String var21 = this.firstNonBlankString(this.readAlignedString(var15), var11);
                           this.expandBlocks(
                              var17.getList("blocks"), var2, var18, var4, var6, var19, var8 + 1, this.appendPath(var9, "component"), var20, var21
                           );
                           this.applyComponentMoveOverride(var19, var15, var9);
                           return var19;
                        }
                     } else {
                        return this.resolveConcreteBlock(var15, var2, var3, var4, var6, var8, var9, var10, var11);
                     }
                  }
               }
            }
         }
      } else {
         return Collections.emptyList();
      }
   }

   private Map<String, Object> resolveInvocationMapWithParams(Map<String, Object> var1, Map<String, Object> var2) {
      LinkedHashMap var3 = new LinkedHashMap();
      if (var1 == null) {
         return var3;
      } else {
         for (Entry var5 : var1.entrySet()) {
            String var6 = (String)var5.getKey();
            Object var7 = var5.getValue();
            if (this.isDeferredContainerKey(var6)) {
               var3.put(var6, this.deepCopyValue(var7));
            } else {
               var3.put(var6, this.resolveValue(var7, var2));
            }
         }

         return var3;
      }
   }

   private boolean isDeferredContainerKey(String var1) {
      if (var1 != null && !var1.isBlank()) {
         String var2 = var1.trim().toLowerCase(Locale.ROOT);
         return var2.equals("children") || var2.equals("block") || var2.equals("blocks");
      } else {
         return false;
      }
   }

   private List<Map<String, Object>> resolveLoopBlock(
      Map<String, Object> var1,
      Map<String, YamlConfiguration> var2,
      Map<String, Object> var3,
      double var4,
      double var6,
      int var8,
      String var9,
      GuiTemplateResolver.EditorTargetMeta var10,
      String var11
   ) {
      if (var1 != null && var1.containsKey("loop")) {
         Object var12 = var1.get("enabled");
         if (var12 != null && !this.parseBooleanFlag(this.resolveValue(var12, var3), true)) {
            return Collections.emptyList();
         } else {
            GuiTemplateResolver.LoopPlan var13 = this.parseLoopPlan(var1.get("loop"), var3);
            if (var13.isEmpty()) {
               return Collections.emptyList();
            } else {
               List var14 = this.extractLoopTemplates(var1);
               if (var14.isEmpty()) {
                  return Collections.emptyList();
               } else {
                  ArrayList var15 = new ArrayList();

                  for (int var16 = 0; var16 < var13.size(); var16++) {
                     LinkedHashMap var17 = new LinkedHashMap();
                     if (var3 != null) {
                        var17.putAll(var3);
                     }

                     this.applyLoopAliases(var17, var13.valueAt(var16));
                     String var18 = this.appendPath(var9, this.appendPath("loop", String.valueOf(var16)));

                     for (int var19 = 0; var19 < var14.size(); var19++) {
                        Object var20 = var14.get(var19);
                        String var21 = var14.size() == 1 ? var18 : this.appendPath(var18, String.valueOf(var19));
                        var15.addAll(this.resolveSingleBlock(var20, var2, var17, var4, var6, var8 + 1, var21, var10, var11));
                     }
                  }

                  return var15;
               }
            }
         }
      } else {
         return null;
      }
   }

   private GuiTemplateResolver.LoopPlan parseLoopPlan(Object var1, Map<String, Object> var2) {
      if (var1 == null) {
         return GuiTemplateResolver.LoopPlan.empty();
      } else {
         Object var3 = this.resolveValue(var1, var2);
         if (var3 instanceof Iterable var5) {
            return GuiTemplateResolver.LoopPlan.fromIterable(var5);
         } else {
            int var4 = this.parseLoopIterations(var3);
            return var4 <= 0 ? GuiTemplateResolver.LoopPlan.empty() : GuiTemplateResolver.LoopPlan.fromRange(var4);
         }
      }
   }

   private int parseLoopIterations(Object var1) {
      double var2;
      if (var1 instanceof Number var4) {
         var2 = var4.doubleValue();
      } else if (var1 instanceof Boolean var5) {
         var2 = var5 ? 1.0 : 0.0;
      } else {
         var2 = this.parseDouble(var1, Double.NaN);
      }

      if (!Double.isFinite(var2)) {
         return 0;
      } else {
         int var6 = (int)Math.floor(var2);
         return var6 <= 0 ? 0 : Math.min(var6, 2048);
      }
   }

   private List<?> extractLoopTemplates(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         Object var2 = var1.get("blocks");
         if (var2 instanceof List) {
            return (List<?>)var2;
         } else {
            Object var3 = var1.get("block");
            if (var3 != null) {
               return Collections.singletonList(var3);
            } else {
               Map var4 = this.deepCopyMap(var1);
               var4.remove("loop");
               return var4.isEmpty() ? Collections.emptyList() : Collections.singletonList(var4);
            }
         }
      } else {
         return Collections.emptyList();
      }
   }

   private void applyLoopAliases(Map<String, Object> var1, Object var2) {
      if (var1 != null) {
         var1.put("counter", var2);
         var1.put("value", var2);
         var1.put("loop-value", var2);
         var1.put("loop_value", var2);
      }
   }

   private List<Map<String, Object>> resolveCaseBlock(
      Map<String, Object> var1,
      Map<String, YamlConfiguration> var2,
      Map<String, Object> var3,
      double var4,
      double var6,
      int var8,
      String var9,
      GuiTemplateResolver.EditorTargetMeta var10,
      String var11
   ) {
      GuiTemplateResolver.CaseDefinition var12 = this.extractCaseDefinition(var1);
      if (var12 == null) {
         return null;
      } else {
         Object var13 = var1.get("enabled");
         if (var13 != null && !this.parseBooleanFlag(this.resolveValue(var13, var3), true)) {
            return Collections.emptyList();
         } else {
            Object var14 = this.resolveValue(var12.selectorExpression, var3);
            Entry var15 = this.selectCaseBranch(var12.branches, var14);
            if (var15 == null) {
               return Collections.emptyList();
            } else {
               List var16 = this.toCaseBlocks(var15.getValue());
               if (var16.isEmpty()) {
                  return Collections.emptyList();
               } else {
                  ArrayList var17 = new ArrayList();
                  String var18 = this.appendPath(var9, this.appendPath("case", this.sanitizeCasePathSegment((String)var15.getKey())));
                  this.expandBlocks(var16, var2, var3, var4, var6, var17, var8 + 1, var18, var10, var11);
                  return var17;
               }
            }
         }
      }
   }

   private GuiTemplateResolver.CaseDefinition extractCaseDefinition(Map<String, Object> var1) {
      if (var1 != null && !var1.isEmpty()) {
         Object var2 = var1.get("case");
         if (var2 != null) {
            LinkedHashMap var9 = new LinkedHashMap();

            for (Entry var11 : var1.entrySet()) {
               String var12 = (String)var11.getKey();
               if (var12 != null && !var12.equalsIgnoreCase("case") && !var12.equalsIgnoreCase("enabled")) {
                  var9.put(var12, var11.getValue());
               }
            }

            return new GuiTemplateResolver.CaseDefinition(var2, var9);
         } else {
            for (Map.Entry var4 : (Set<Map.Entry>)(Set)var1.entrySet()) {
               String var5 = (String)var4.getKey();
               if (var5 != null) {
                  Matcher var6 = CASE_INLINE_KEY.matcher(var5.trim());
                  if (var6.matches()) {
                     Object var7 = var4.getValue();
                     Object var8 = this.toStringObjectMap(var7);
                     if (var8 == null) {
                        var8 = new LinkedHashMap();
                     }

                     return new GuiTemplateResolver.CaseDefinition(var6.group(1).trim(), (Map<String, Object>)var8);
                  }
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   private Entry<String, Object> selectCaseBranch(Map<String, Object> var1, Object var2) {
      if (var1 != null && !var1.isEmpty()) {
         for (String var4 : this.caseSelectorCandidates(var2)) {
            Entry var5 = this.findCaseBranch(var1, var4);
            if (var5 != null) {
               return var5;
            }
         }

         return this.findCaseDefaultBranch(var1);
      } else {
         return null;
      }
   }

   private List<String> caseSelectorCandidates(Object var1) {
      ArrayList var2 = new ArrayList();
      if (var1 == null) {
         this.addCaseCandidate(var2, "null");
         this.addCaseCandidate(var2, "none");
         this.addCaseCandidate(var2, "");
         return var2;
      } else if (var1 instanceof Boolean var8) {
         this.addCaseCandidate(var2, var8.toString());
         return var2;
      } else if (var1 instanceof Number var3) {
         double var4 = var3.doubleValue();
         if (Double.isFinite(var4)) {
            this.addCaseCandidate(var2, this.formatCaseNumber(var4));
            long var6 = Math.round(var4);
            if (Math.abs(var4 - (double)var6) < 1.0E-7) {
               this.addCaseCandidate(var2, Long.toString(var6));
            }
         }

         this.addCaseCandidate(var2, var3.toString());
         return var2;
      } else {
         this.addCaseCandidate(var2, var1.toString());
         return var2;
      }
   }

   private void addCaseCandidate(List<String> var1, String var2) {
      if (var1 != null && var2 != null) {
         String var3 = var2.trim();
         if (!var1.contains(var3)) {
            var1.add(var3);
         }

         String var4 = var3.toLowerCase(Locale.ROOT);
         if (!var4.equals(var3) && !var1.contains(var4)) {
            var1.add(var4);
         }
      }
   }

   private String formatCaseNumber(double var1) {
      long var3 = Math.round(var1);
      return Math.abs(var1 - (double)var3) < 1.0E-7 ? Long.toString(var3) : Double.toString(var1);
   }

   private Entry<String, Object> findCaseBranch(Map<String, Object> var1, String var2) {
      if (var1 != null && !var1.isEmpty() && var2 != null) {
         String var3 = var2.trim();

         for (Entry var5 : var1.entrySet()) {
            String var6 = (String)var5.getKey();
            if (var6 != null && var6.trim().equalsIgnoreCase(var3)) {
               return var5;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   private Entry<String, Object> findCaseDefaultBranch(Map<String, Object> var1) {
      String[] var2 = new String[]{"default", "else", "_", "*"};

      for (Object var6_raw : var2) {
         String var6 = var6_raw != null ? var6_raw.toString() : null;
         Entry var7 = this.findCaseBranch(var1, var6);
         if (var7 != null) {
            return var7;
         }
      }

      return null;
   }

   private List<?> toCaseBlocks(Object var1) {
      if (var1 instanceof List) {
         return (List<?>)var1;
      } else {
         Map var2 = this.toStringObjectMap(var1);
         return var2 != null && !var2.isEmpty() ? Collections.singletonList(var2) : Collections.emptyList();
      }
   }

   private String sanitizeCasePathSegment(String var1) {
      if (var1 != null && !var1.isBlank()) {
         StringBuilder var2 = new StringBuilder();

         for (char var6 : var1.trim().toLowerCase(Locale.ROOT).toCharArray()) {
            if (!Character.isLetterOrDigit(var6) && var6 != '_' && var6 != '-') {
               var2.append('_');
            } else {
               var2.append(var6);
            }
         }

         return var2.isEmpty() ? "default" : var2.toString();
      } else {
         return "default";
      }
   }

   private List<Map<String, Object>> resolveConcreteBlock(
      Map<String, Object> var1,
      Map<String, YamlConfiguration> var2,
      Map<String, Object> var3,
      double var4,
      double var6,
      int var8,
      String var9,
      GuiTemplateResolver.EditorTargetMeta var10,
      String var11
   ) {
      Map var12 = this.deepCopyMap(var1);
      String var13 = this.normalizeType(var12);
      GuiTemplateResolver.EditorTargetMeta var14 = var10 != null ? var10 : this.buildBlockTargetMeta(var9);
      boolean var15 = this.parseBooleanFlag(var12.get("__editor_inherit_target_to_children"), false);
      GuiTemplateResolver.EditorTargetMeta var16 = var15 ? var14 : var10;
      double var17 = this.readPositionX(var12);
      double var19 = this.readPositionY(var12);
      double var21 = var4 + var17;
      double var23 = var6 + var19;
      this.setPosition(var12, var21, var23);
      String var25 = this.readAlignedString(var12);
      String var26 = !var25.isBlank() ? var25 : (var11 != null ? var11 : "");
      if (!var26.isBlank() && var25.isBlank()) {
         var12.put("aligned", var26);
      }

      ArrayList var27 = new ArrayList();
      if (this.shouldRenderSelf(var13)) {
         Map var28 = this.stripContainerKeys(var12);
         this.applyEditorTargetMeta(var28, var14);
         var28.put("__editor_block_path", var9);
         var27.add(var28);
      }

      List var40 = this.extractChildren(var12.get("children"));
      if (var40 != null && !var40.isEmpty()) {
         GuiTemplateResolver.LayoutOptions var29 = this.readLayoutOptions(var12, var13);
         if (!var29.enabled) {
            this.expandBlocks(var40, var2, var3, var21, var23, var27, var8 + 1, this.appendPath(var9, "children"), var16, var26);
            return var27;
         } else {
            ArrayList var30 = new ArrayList();

            for (int var31 = 0; var31 < var40.size(); var31++) {
               String var32 = this.appendPath(this.appendPath(var9, "children"), String.valueOf(var31));
               List var33 = this.resolveSingleBlock(var40.get(var31), var2, var3, 0.0, 0.0, var8 + 1, var32, var16, var26);
               if (!var33.isEmpty()) {
                  GuiTemplateResolver.Bounds var34 = this.computeBounds(var33);
                  GuiTemplateResolver.Bounds var35 = this.computeBoundsForTargetPath(var33, var32);
                  if (var35 == null) {
                     var35 = var34;
                  }

                  this.translateBlocks(var33, -var35.minX, -var35.minY);
                  double var36 = Math.max(var35.width(), var34.maxX - var35.minX);
                  double var38 = Math.max(var35.height(), var34.maxY - var35.minY);
                  var30.add(new GuiTemplateResolver.ChildGroup(var33, var36, var38));
               }
            }

            this.layoutChildGroups(var30, var21, var23, var29, var27);
            return var27;
         }
      } else {
         return var27;
      }
   }

   private GuiTemplateResolver.EditorTargetMeta buildBlockTargetMeta(String var1) {
      return new GuiTemplateResolver.EditorTargetMeta(var1, "block", var1, null, null, null, null, null);
   }

   private GuiTemplateResolver.EditorTargetMeta buildComponentTargetMeta(YamlConfiguration var1, Map<String, Object> var2, String var3, String var4) {
      Set var5 = this.collectUsedComponentParams(var1);
      Map var6 = this.toStringObjectMap(var2 == null ? null : var2.get("params"));
      return new GuiTemplateResolver.EditorTargetMeta(
         var3,
         "component",
         var3,
         var4,
         this.resolveComponentBindingPath(var1, var2, var6, var5, "x"),
         this.resolveComponentBindingPath(var1, var2, var6, var5, "y"),
         this.resolveComponentBindingPath(var1, var2, var6, var5, "width"),
         this.resolveComponentBindingPath(var1, var2, var6, var5, "height")
      );
   }

   private String resolveComponentBindingPath(YamlConfiguration var1, Map<String, Object> var2, Map<String, Object> var3, Set<String> var4, String var5) {
      if (var5 == null || var5.isBlank() || var4 == null || !var4.contains(var5)) {
         return null;
      } else if (var3 != null && var3.containsKey(var5)) {
         return "params." + var5;
      } else if (var2 != null && var2.containsKey(var5)) {
         return var5;
      } else {
         ConfigurationSection var6 = var1.getConfigurationSection("params");
         return var6 != null && var6.contains(var5) ? "params." + var5 : null;
      }
   }

   private Set<String> collectUsedComponentParams(YamlConfiguration var1) {
      if (var1 == null) {
         return Collections.emptySet();
      } else {
         Set var2 = this.componentParamUsageCache.get(var1);
         if (var2 != null) {
            return var2;
         } else {
            LinkedHashSet var3 = new LinkedHashSet();
            this.collectComponentParams(var1.getList("blocks"), var3);
            this.componentParamUsageCache.put(var1, var3);
            return var3;
         }
      }
   }

   private void collectComponentParams(Object var1, Set<String> var2) {
      if (var1 != null && var2 != null) {
         if (var1 instanceof String var8) {
            Matcher var10 = PLACEHOLDER.matcher(var8);

            while (var10.find()) {
               String var12 = this.extractPlaceholderKey(var10);
               if (var12 != null && !var12.isBlank()) {
                  var2.add(var12);
               }
            }
         } else if (var1 instanceof ConfigurationSection var7) {
            this.collectComponentParams(var7.getValues(false), var2);
         } else {
            Map var3 = this.toStringObjectMap(var1);
            if (var3 != null) {
               for (Object var11 : var3.values()) {
                  this.collectComponentParams(var11, var2);
               }
            } else {
               if (var1 instanceof List) {
                  for (Object var6 : (List)var1) {
                     this.collectComponentParams(var6, var2);
                  }
               }
            }
         }
      }
   }

   private boolean componentUsesParam(Object var1, String var2) {
      if (var1 != null && var2 != null && !var2.isBlank()) {
         if (var1 instanceof String var8) {
            Matcher var10 = PLACEHOLDER.matcher(var8);

            while (var10.find()) {
               if (var2.equals(this.extractPlaceholderKey(var10))) {
                  return true;
               }
            }

            return false;
         } else if (var1 instanceof ConfigurationSection var7) {
            return this.componentUsesParam(var7.getValues(false), var2);
         } else {
            Map var3 = this.toStringObjectMap(var1);
            if (var3 != null) {
               for (Object var11 : var3.values()) {
                  if (this.componentUsesParam(var11, var2)) {
                     return true;
                  }
               }

               return false;
            } else {
               if (var1 instanceof List) {
                  for (Object var6 : (List)var1) {
                     if (this.componentUsesParam(var6, var2)) {
                        return true;
                     }
                  }
               }

               return false;
            }
         }
      } else {
         return false;
      }
   }

   private void applyEditorTargetMeta(Map<String, Object> var1, GuiTemplateResolver.EditorTargetMeta var2) {
      if (var1 != null && var2 != null) {
         var1.put("__editor_target_id", var2.targetId);
         var1.put("__editor_target_kind", var2.targetKind);
         var1.put("__editor_target_path", var2.targetPath);
         if (var2.componentName != null) {
            var1.put("__editor_component_name", var2.componentName);
         }

         if (var2.bindingX != null) {
            var1.put("__editor_binding_x", var2.bindingX);
         }

         if (var2.bindingY != null) {
            var1.put("__editor_binding_y", var2.bindingY);
         }

         if (var2.bindingWidth != null) {
            var1.put("__editor_binding_width", var2.bindingWidth);
         }

         if (var2.bindingHeight != null) {
            var1.put("__editor_binding_height", var2.bindingHeight);
         }
      }
   }

   private String appendPath(String var1, String var2) {
      String var3 = var1 == null ? "" : var1.trim();
      String var4 = var2 == null ? "" : var2.trim();
      if (var3.isEmpty()) {
         return var4;
      } else {
         return var4.isEmpty() ? var3 : var3 + "." + var4;
      }
   }

   private Map<String, Object> buildComponentParams(YamlConfiguration var1, Map<String, Object> var2, Map<String, Object> var3) {
      LinkedHashMap var4 = new LinkedHashMap();
      if (var3 != null) {
         var4.putAll(var3);
      }

      ConfigurationSection var5 = var1.getConfigurationSection("params");
      if (var5 != null) {
         var4.putAll(this.resolveMapWithParams(this.toStringObjectMap(var5), var4));
      }

      Map var6 = this.toStringObjectMap(var2.get("params"));
      if (var6 != null) {
         var4.putAll(this.resolveMapWithParams(var6, var4));
      }

      for (Entry var8 : var2.entrySet()) {
         if (!this.isReservedComponentKey((String)var8.getKey())) {
            var4.put((String)var8.getKey(), this.resolveValue(var8.getValue(), var4));
         }
      }

      return var4;
   }

   private boolean isReservedComponentKey(String var1) {
      if (var1 == null) {
         return true;
      } else {
         String var2 = var1.trim().toLowerCase(Locale.ROOT);
         return var2.equals("component")
            || var2.equals("include")
            || var2.equals("type")
            || var2.equals("name")
            || var2.equals("params")
            || var2.equals("children")
            || var2.equals("childrenlayout")
            || var2.equals("__editor_move_x")
            || var2.equals("__editor_move_y");
      }
   }

   private void applyComponentMoveOverride(List<Map<String, Object>> var1, Map<String, Object> var2, String var3) {
      if (var1 != null && !var1.isEmpty() && var2 != null) {
         double var4 = this.parseDouble(var2.get("__editor_move_x"), Double.NaN);
         double var6 = this.parseDouble(var2.get("__editor_move_y"), Double.NaN);
         if (Double.isFinite(var4) || Double.isFinite(var6)) {
            GuiTemplateResolver.Bounds var8 = this.computeBoundsForTargetPath(var1, var3);
            if (var8 == null) {
               var8 = this.computeBounds(var1);
            }

            if (var8 != null) {
               double var9 = Double.isFinite(var4) ? var4 - var8.minX : 0.0;
               double var11 = Double.isFinite(var6) ? var6 - var8.minY : 0.0;
               if (!(Math.abs(var9) < 1.0E-4) || !(Math.abs(var11) < 1.0E-4)) {
                  this.translateBlocks(var1, var9, var11);
               }
            }
         }
      }
   }

   private void layoutChildGroups(
      List<GuiTemplateResolver.ChildGroup> var1, double var2, double var4, GuiTemplateResolver.LayoutOptions var6, List<Map<String, Object>> var7
   ) {
      if (!var1.isEmpty()) {
         if ("column".equals(var6.direction)) {
            this.layoutColumnGroups(var1, var2, var4, var6, var7);
         } else {
            this.layoutRowGroups(var1, var2, var4, var6, var7);
         }
      }
   }

   private void layoutRowGroups(
      List<GuiTemplateResolver.ChildGroup> var1, double var2, double var4, GuiTemplateResolver.LayoutOptions var6, List<Map<String, Object>> var7
   ) {
      double var8 = 0.0;
      double var10 = 0.0;
      double var12 = 0.0;
      int var14 = 0;

      for (GuiTemplateResolver.ChildGroup var16 : var1) {
         if (var14 >= var6.rowSize) {
            var8 = 0.0;
            var10 += var12 + var6.gap;
            var12 = 0.0;
            var14 = 0;
         }

         this.translateBlocks(var16.blocks, var2 + var8, var4 + var10);
         var7.addAll(var16.blocks);
         var8 += var16.width + var6.gap;
         var12 = Math.max(var12, this.resolveGroupElementHeight(var16, var6));
         var14++;
      }
   }

   private void layoutColumnGroups(
      List<GuiTemplateResolver.ChildGroup> var1, double var2, double var4, GuiTemplateResolver.LayoutOptions var6, List<Map<String, Object>> var7
   ) {
      double var8 = 0.0;
      double var10 = 0.0;
      double var12 = 0.0;
      int var14 = 0;

      for (GuiTemplateResolver.ChildGroup var16 : var1) {
         if (var14 >= var6.rowSize) {
            var10 = 0.0;
            var8 += var12 + var6.gap;
            var12 = 0.0;
            var14 = 0;
         }

         this.translateBlocks(var16.blocks, var2 + var8, var4 + var10);
         var7.addAll(var16.blocks);
         var10 += this.resolveGroupElementHeight(var16, var6) + var6.gap;
         var12 = Math.max(var12, var16.width);
         var14++;
      }
   }

   private double resolveGroupElementHeight(GuiTemplateResolver.ChildGroup var1, GuiTemplateResolver.LayoutOptions var2) {
      return var2 != null && Double.isFinite(var2.elementHeight) ? var2.elementHeight : var1.height;
   }

   private GuiTemplateResolver.Bounds computeBounds(List<Map<String, Object>> var1) {
      double var2 = Double.POSITIVE_INFINITY;
      double var4 = Double.POSITIVE_INFINITY;
      double var6 = Double.NEGATIVE_INFINITY;
      double var8 = Double.NEGATIVE_INFINITY;

      for (Map var11 : var1) {
         double var12 = this.readPositionX(var11);
         double var14 = this.readPositionY(var11);
         double var16 = this.readSizeWidth(var11);
         double var18 = this.readSizeHeight(var11);
         var2 = Math.min(var2, var12);
         var4 = Math.min(var4, var14);
         var6 = Math.max(var6, var12 + var16);
         var8 = Math.max(var8, var14 + var18);
      }

      return Double.isFinite(var2) && Double.isFinite(var4) && Double.isFinite(var6) && Double.isFinite(var8)
         ? new GuiTemplateResolver.Bounds(var2, var4, var6, var8)
         : new GuiTemplateResolver.Bounds(0.0, 0.0, 0.0, 0.0);
   }

   private GuiTemplateResolver.Bounds computeBoundsForTargetPath(List<Map<String, Object>> var1, String var2) {
      if (var1 != null && !var1.isEmpty() && var2 != null && !var2.isBlank()) {
         double var3 = Double.POSITIVE_INFINITY;
         double var5 = Double.POSITIVE_INFINITY;
         double var7 = Double.NEGATIVE_INFINITY;
         double var9 = Double.NEGATIVE_INFINITY;

         for (Map var12 : var1) {
            if (var12 != null) {
               Object var13 = var12.get("__editor_target_path");
               if (var13 != null && var2.equals(var13.toString())) {
                  double var14 = this.readPositionX(var12);
                  double var16 = this.readPositionY(var12);
                  double var18 = this.readSizeWidth(var12);
                  double var20 = this.readSizeHeight(var12);
                  var3 = Math.min(var3, var14);
                  var5 = Math.min(var5, var16);
                  var7 = Math.max(var7, var14 + var18);
                  var9 = Math.max(var9, var16 + var20);
               }
            }
         }

         return Double.isFinite(var3) && Double.isFinite(var5) && Double.isFinite(var7) && Double.isFinite(var9)
            ? new GuiTemplateResolver.Bounds(var3, var5, var7, var9)
            : null;
      } else {
         return null;
      }
   }

   private void translateBlocks(List<Map<String, Object>> var1, double var2, double var4) {
      for (Map var7 : var1) {
         this.setPosition(var7, this.readPositionX(var7) + var2, this.readPositionY(var7) + var4);
      }
   }

   private boolean shouldRenderSelf(String var1) {
      return !"grid_block".equals(var1);
   }

   private GuiTemplateResolver.LayoutOptions readLayoutOptions(Map<String, Object> var1, String var2) {
      GuiTemplateResolver.LayoutOptions var3 = new GuiTemplateResolver.LayoutOptions();
      var3.enabled = false;
      var3.direction = "row";
      var3.gap = 0.0;
      var3.rowSize = Integer.MAX_VALUE;
      var3.elementHeight = Double.NaN;
      Map var4;
      if ("grid_block".equals(var2)) {
         var4 = var1;
         var3.enabled = true;
      } else {
         var4 = this.toStringObjectMap(var1.get("childrenLayout"));
         var3.enabled = var4 != null;
      }

      if (var3.enabled && var4 != null) {
         String var5 = this.readString(this.firstNonNull(var4.get("direction"), var4.get("flow")));
         if ("column".equals(var5)) {
            var3.direction = "column";
         }

         var3.gap = this.parseDouble(this.firstNonNull(var4.get("gap"), var4.get("spacing")), 0.0);
         double var6 = this.parseDouble(
            this.firstNonNull(
               var4.get("element_h"), this.firstNonNull(var4.get("elementHeight"), this.firstNonNull(var4.get("element_height"), var4.get("element-height")))
            ),
            Double.NaN
         );
         if (Double.isFinite(var6)) {
            var3.elementHeight = Math.max(0.0, var6);
         }

         var3.rowSize = Math.max(
            1,
            (int)Math.round(
               this.parseDouble(
                  this.firstNonNull(
                     var4.get("row_size"),
                     this.firstNonNull(var4.get("rowSize"), this.firstNonNull(var4.get("maxRow"), this.firstNonNull(var4.get("max_row"), var4.get("max-row"))))
                  ),
                  2.147483647E9
               )
            )
         );
         return var3;
      } else {
         return var3;
      }
   }

   private Map<String, Object> stripContainerKeys(Map<String, Object> var1) {
      Map var2 = this.deepCopyMap(var1);
      var2.remove("children");
      var2.remove("childrenLayout");
      var2.remove("component");
      var2.remove("include");
      var2.remove("params");
      var2.remove("__editor_inherit_target_to_children");
      if ("component".equalsIgnoreCase(this.readString(var2.get("type")))) {
         var2.remove("name");
      }

      return var2;
   }

   private List<?> extractChildren(Object var1) {
      return var1 instanceof List var2 ? var2 : null;
   }

   private String normalizeType(Map<String, Object> var1) {
      return var1 == null ? "block" : this.readString(this.firstNonNull(var1.get("type"), "block"));
   }

   private String extractComponentName(Map<String, Object> var1) {
      Object var2 = var1.get("component");
      if (var2 == null) {
         var2 = var1.get("include");
      }

      if (var2 == null && var1.get("type") instanceof String var4 && var4.equalsIgnoreCase("component")) {
         var2 = var1.get("name");
      }

      return var2 == null ? null : var2.toString().trim();
   }

   private boolean isBlockEnabled(Map<String, Object> var1) {
      return var1 != null && var1.containsKey("enabled") ? this.parseBooleanFlag(var1.get("enabled"), true) : true;
   }

   private boolean parseBooleanFlag(Object var1, boolean var2) {
      if (var1 == null) {
         return var2;
      } else if (var1 instanceof Boolean var10) {
         return var10;
      } else if (var1 instanceof Number var9) {
         return var9.doubleValue() != 0.0;
      } else {
         String var3 = var1.toString().trim();
         if (var3.isEmpty()) {
            return var2;
         } else {
            String var4 = var3.toLowerCase(Locale.ROOT);

            return switch (var4) {
               case "true", "yes", "on", "enabled" -> true;
               case "false", "no", "off", "disabled", "none", "null" -> false;
               default -> {
                  double var7 = this.parseDouble(var3, Double.NaN);
                  yield Double.isFinite(var7) ? var7 != 0.0 : var2;
               }
            };
         }
      }
   }

   private Map<String, Object> resolveMapWithParams(Map<String, Object> var1, Map<String, Object> var2) {
      LinkedHashMap var3 = new LinkedHashMap();
      if (var1 == null) {
         return var3;
      } else {
         for (Entry var5 : var1.entrySet()) {
            var3.put((String)var5.getKey(), this.resolveValue(var5.getValue(), var2));
         }

         return var3;
      }
   }

   private Object resolveValue(Object var1, Map<String, Object> var2) {
      if (var1 instanceof String var8) {
         return this.resolveString(var8, var2);
      } else {
         Map var3 = this.toStringObjectMap(var1);
         if (var3 != null) {
            return this.resolveMapWithParams(var3, var2);
         } else if (!(var1 instanceof List var4)) {
            return var1;
         } else {
            ArrayList var5 = new ArrayList();

            for (Object var7 : var4) {
               var5.add(this.resolveValue(var7, var2));
            }

            return var5;
         }
      }
   }

   private Object resolveString(String var1, Map<String, Object> var2) {
      if (var1 == null) {
         return "";
      } else if (var1.indexOf(123) < 0 && var1.indexOf(36) < 0) {
         return var1;
      } else {
         Matcher var3 = PLACEHOLDER.matcher(var1);
         if (!var3.find()) {
            return var1;
         } else if (var3.start() == 0 && var3.end() == var1.length()) {
            String var7 = this.extractPlaceholderKey(var3);
            Object var8 = var7 != null && var2 != null ? var2.get(var7) : null;
            return var8 == null ? "" : var8;
         } else {
            StringBuffer var4 = new StringBuffer();

            do {
               String var5 = this.extractPlaceholderKey(var3);
               Object var6 = var5 != null && var2 != null ? var2.get(var5) : null;
               var3.appendReplacement(var4, Matcher.quoteReplacement(var6 == null ? "" : var6.toString()));
            } while (var3.find());

            var3.appendTail(var4);
            return var4.toString();
         }
      }
   }

   private String extractPlaceholderKey(Matcher var1) {
      if (var1 == null) {
         return null;
      } else {
         for (int var2 = 1; var2 <= var1.groupCount(); var2++) {
            String var3 = var1.group(var2);
            if (var3 != null) {
               return var3;
            }
         }

         return null;
      }
   }

   private String readAlignedString(Map<String, Object> var1) {
      if (var1 == null) {
         return "";
      } else {
         if (var1.get("aligned") instanceof String var3 && !var3.isBlank()) {
            return var3;
         }

         if (var1.get("position") instanceof Map var4 && var4.get("align") instanceof String var6 && !var6.isBlank()) {
            return var6;
         }

         return "";
      }
   }

   private String firstNonBlankString(String var1, String var2) {
      if (var1 != null && !var1.isBlank()) {
         return var1;
      } else {
         return var2 != null ? var2 : "";
      }
   }

   private double readPositionX(Map<String, Object> var1) {
      return this.readMapDouble(var1, "position.x", "x", 0.0);
   }

   private double readPositionY(Map<String, Object> var1) {
      return this.readMapDouble(var1, "position.y", "y", 0.0);
   }

   private double readSizeWidth(Map<String, Object> var1) {
      return Math.max(1.0, this.readMapDouble(var1, "size.width", "width", this.readMapDouble(var1, "scale.width", "width", 1.0)));
   }

   private double readSizeHeight(Map<String, Object> var1) {
      return Math.max(1.0, this.readMapDouble(var1, "size.height", "height", this.readMapDouble(var1, "scale.height", "height", 1.0)));
   }

   private void setPosition(Map<String, Object> var1, double var2, double var4) {
      Map var7;
      if (var1.get("position") instanceof Map var8) {
         var7 = this.toStringObjectMap(var8);
      } else {
         var7 = new LinkedHashMap();
      }

      var7.put("x", var2);
      var7.put("y", var4);
      var1.put("position", var7);
      var1.remove("x");
      var1.remove("y");
   }

   private double readMapDouble(Map<String, Object> var1, String var2, String var3, double var4) {
      if (var1 == null) {
         return var4;
      } else {
         Object var6 = this.getNestedValue(var1, var2);
         if (var6 != null) {
            return this.parseDouble(var6, var4);
         } else {
            Object var7 = var1.get(var3);
            return var7 != null ? this.parseDouble(var7, var4) : var4;
         }
      }
   }

   private Object getNestedValue(Map<String, Object> var1, String var2) {
      String[] var3 = PATH_SEGMENTS_CACHE.computeIfAbsent(var2, var0 -> var0.split("\\."));
      Object var4 = var1;

      for (Object var8_raw : var3) {
         String var8 = var8_raw != null ? var8_raw.toString() : null;
         if (!(var4 instanceof Map var9)) {
            return null;
         }

         var4 = var9.get(var8);
         if (var4 == null) {
            return null;
         }
      }

      return var4;
   }

   private String readString(Object var1) {
      return var1 == null ? "" : var1.toString().trim().toLowerCase(Locale.ROOT);
   }

   private double parseDouble(Object var1, double var2) {
      if (var1 == null) {
         return var2;
      } else if (var1 instanceof Number var8) {
         return var8.doubleValue();
      } else {
         String var4 = var1.toString().trim();
         if (var4.isEmpty()) {
            return var2;
         } else {
            String var5 = this.normalizeNumericExpressionSeparators(var4);

            try {
               return Double.parseDouble(var5);
            } catch (Exception var7) {
               return this.evaluateNumericExpression(var5, var2);
            }
         }
      }
   }

   private String normalizeNumericExpressionSeparators(String var1) {
      if (var1 != null && !var1.isBlank() && var1.indexOf(44) >= 0) {
         StringBuilder var2 = new StringBuilder(var1.length());

         for (int var3 = 0; var3 < var1.length(); var3++) {
            char var4 = var1.charAt(var3);
            if (var4 != ',') {
               var2.append(var4);
            } else {
               int var5 = var3 - 1;

               while (var5 >= 0 && Character.isWhitespace(var1.charAt(var5))) {
                  var5--;
               }

               int var6 = var3 + 1;

               while (var6 < var1.length() && Character.isWhitespace(var1.charAt(var6))) {
                  var6++;
               }

               boolean var7 = var5 >= 0 && var6 < var1.length() && Character.isDigit(var1.charAt(var5)) && Character.isDigit(var1.charAt(var6));
               var2.append((char)(var7 ? '.' : ','));
            }
         }

         return var2.toString();
      } else {
         return var1;
      }
   }

   private double evaluateNumericExpression(String var1, double var2) {
      try {
         int[] var4 = new int[]{0};
         double var5 = this.parseExpression(var1, var4);
         this.skipWhitespace(var1, var4);
         return var4[0] == var1.length() && Double.isFinite(var5) ? var5 : var2;
      } catch (Exception var7) {
         return var2;
      }
   }

   private double parseExpression(String var1, int[] var2) {
      double var3 = this.parseTerm(var1, var2);

      while (true) {
         this.skipWhitespace(var1, var2);
         if (var2[0] >= var1.length()) {
            return var3;
         }

         char var5 = var1.charAt(var2[0]);
         if (var5 != '+' && var5 != '-') {
            return var3;
         }

         var2[0]++;
         double var6 = this.parseTerm(var1, var2);
         var3 = var5 == '+' ? var3 + var6 : var3 - var6;
      }
   }

   private double parseTerm(String var1, int[] var2) {
      double var3 = this.parseFactor(var1, var2);

      while (true) {
         this.skipWhitespace(var1, var2);
         if (var2[0] >= var1.length()) {
            return var3;
         }

         char var5 = var1.charAt(var2[0]);
         if (var5 != '*' && var5 != '/') {
            return var3;
         }

         var2[0]++;
         double var6 = this.parseFactor(var1, var2);
         if (var5 == '*') {
            var3 *= var6;
         } else {
            if (var6 == 0.0) {
               throw new IllegalArgumentException("Division by zero");
            }

            var3 /= var6;
         }
      }
   }

   private double parseFactor(String var1, int[] var2) {
      this.skipWhitespace(var1, var2);
      if (var2[0] >= var1.length()) {
         throw new IllegalArgumentException("Unexpected end of expression");
      } else {
         char var3 = var1.charAt(var2[0]);
         if (var3 == '+') {
            var2[0]++;
            return this.parseFactor(var1, var2);
         } else if (var3 == '-') {
            var2[0]++;
            return -this.parseFactor(var1, var2);
         } else if (var3 == '(') {
            var2[0]++;
            double var4 = this.parseExpression(var1, var2);
            this.skipWhitespace(var1, var2);
            if (var2[0] < var1.length() && var1.charAt(var2[0]) == ')') {
               var2[0]++;
               return var4;
            } else {
               throw new IllegalArgumentException("Missing closing parenthesis");
            }
         } else {
            return this.parseNumber(var1, var2);
         }
      }
   }

   private double parseNumber(String var1, int[] var2) {
      int var3 = var2[0];
      boolean var4 = false;

      while (var2[0] < var1.length()) {
         char var5 = var1.charAt(var2[0]);
         if (Character.isDigit(var5)) {
            var2[0]++;
         } else {
            if (var5 != '.' || var4) {
               break;
            }

            var4 = true;
            var2[0]++;
         }
      }

      if (var3 == var2[0]) {
         throw new IllegalArgumentException("Expected number");
      } else {
         return Double.parseDouble(var1.substring(var3, var2[0]));
      }
   }

   private void skipWhitespace(String var1, int[] var2) {
      while (var2[0] < var1.length() && Character.isWhitespace(var1.charAt(var2[0]))) {
         var2[0]++;
      }
   }

   private Object firstNonNull(Object var1, Object var2) {
      return var1 != null ? var1 : var2;
   }

   private Map<String, Object> toStringObjectMap(Object var1) {
      if (var1 == null) {
         return null;
      } else {
         Map var2;
         if (var1 instanceof Map var4) {
            var2 = var4;
         } else {
            if (!(var1 instanceof ConfigurationSection var3)) {
               return null;
            }

            var2 = var3.getValues(false);
         }

         LinkedHashMap var7 = new LinkedHashMap();

         for (Map.Entry var6 : (Set<Map.Entry>)(Set)var2.entrySet()) {
            if (var6.getKey() != null) {
               var7.put(var6.getKey().toString(), var6.getValue());
            }
         }

         return var7;
      }
   }

   private Map<String, Object> deepCopyMap(Map<String, Object> var1) {
      LinkedHashMap var2 = new LinkedHashMap();

      for (Map.Entry var4 : (Set<Map.Entry>)(Set)var1.entrySet()) {
         var2.put((String)var4.getKey(), this.deepCopyValue(var4.getValue()));
      }

      return var2;
   }

   private Object deepCopyValue(Object var1) {
      if (var1 instanceof Map var6) {
         LinkedHashMap var7 = new LinkedHashMap();

         for (Map.Entry var9 : (Set<Map.Entry>)(Set)var6.entrySet()) {
            if (var9.getKey() != null) {
               var7.put(var9.getKey().toString(), this.deepCopyValue(var9.getValue()));
            }
         }

         return var7;
      } else if (!(var1 instanceof List var2)) {
         return var1;
      } else {
         ArrayList var3 = new ArrayList();

         for (Object var5 : var2) {
            var3.add(this.deepCopyValue(var5));
         }

         return var3;
      }
   }

   private static final class Bounds {
      private final double minX;
      private final double minY;
      private final double maxX;
      private final double maxY;

      private Bounds(double var1, double var3, double var5, double var7) {
         this.minX = var1;
         this.minY = var3;
         this.maxX = var5;
         this.maxY = var7;
      }

      private double width() {
         return Math.max(0.0, this.maxX - this.minX);
      }

      private double height() {
         return Math.max(0.0, this.maxY - this.minY);
      }
   }

   private static final class CaseDefinition {
      private final Object selectorExpression;
      private final Map<String, Object> branches;

      private CaseDefinition(Object var1, Map<String, Object> var2) {
         this.selectorExpression = var1;
         this.branches = var2 == null ? Collections.emptyMap() : var2;
      }
   }

   private static final class ChildGroup {
      private final List<Map<String, Object>> blocks;
      private final double width;
      private final double height;

      private ChildGroup(List<Map<String, Object>> var1, double var2, double var4) {
         this.blocks = var1;
         this.width = Math.max(0.0, var2);
         this.height = Math.max(0.0, var4);
      }
   }

   private static final class EditorTargetMeta {
      private final String targetId;
      private final String targetKind;
      private final String targetPath;
      private final String componentName;
      private final String bindingX;
      private final String bindingY;
      private final String bindingWidth;
      private final String bindingHeight;

      private EditorTargetMeta(String var1, String var2, String var3, String var4, String var5, String var6, String var7, String var8) {
         this.targetId = var1;
         this.targetKind = var2;
         this.targetPath = var3;
         this.componentName = var4;
         this.bindingX = var5;
         this.bindingY = var6;
         this.bindingWidth = var7;
         this.bindingHeight = var8;
      }
   }

   private static final class LayoutOptions {
      private boolean enabled;
      private String direction;
      private double gap;
      private int rowSize;
      private double elementHeight;
   }

   private static final class LoopPlan {
      private static final GuiTemplateResolver.LoopPlan EMPTY = new GuiTemplateResolver.LoopPlan(Collections.emptyList());
      private final List<Object> values;

      private LoopPlan(List<Object> var1) {
         this.values = var1;
      }

      private static GuiTemplateResolver.LoopPlan empty() {
         return EMPTY;
      }

      private static GuiTemplateResolver.LoopPlan fromRange(int var0) {
         if (var0 <= 0) {
            return EMPTY;
         } else {
            ArrayList var1 = new ArrayList();

            for (int var2 = 0; var2 < var0; var2++) {
               var1.add(var2);
            }

            return new GuiTemplateResolver.LoopPlan(var1);
         }
      }

      private static GuiTemplateResolver.LoopPlan fromIterable(Iterable<?> var0) {
         if (var0 == null) {
            return EMPTY;
         } else {
            ArrayList var1 = new ArrayList();

            for (Object var3 : var0) {
               var1.add(var3);
               if (var1.size() >= 2048) {
                  break;
               }
            }

            return var1.isEmpty() ? EMPTY : new GuiTemplateResolver.LoopPlan(var1);
         }
      }

      private boolean isEmpty() {
         return this.values.isEmpty();
      }

      private int size() {
         return this.values.size();
      }

      private Object valueAt(int var1) {
         return this.values.get(var1);
      }
   }
}
